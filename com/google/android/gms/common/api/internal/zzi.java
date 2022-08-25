package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzi extends zzk {
  private final SparseArray<zza> zzed = new SparseArray();
  
  private zzi(LifecycleFragment paramLifecycleFragment) {
    super(paramLifecycleFragment);
    this.mLifecycleFragment.addCallback("AutoManageHelper", this);
  }
  
  public static zzi zza(LifecycleActivity paramLifecycleActivity) {
    LifecycleFragment lifecycleFragment = getFragment(paramLifecycleActivity);
    zzi zzi1 = lifecycleFragment.<zzi>getCallbackOrNull("AutoManageHelper", zzi.class);
    return (zzi1 != null) ? zzi1 : new zzi(lifecycleFragment);
  }
  
  @Nullable
  private final zza zzd(int paramInt) {
    return (this.zzed.size() <= paramInt) ? null : (zza)this.zzed.get(this.zzed.keyAt(paramInt));
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    for (byte b = 0; b < this.zzed.size(); b++) {
      zza zza = zzd(b);
      if (zza != null) {
        paramPrintWriter.append(paramString).append("GoogleApiClient #").print(zza.zzee);
        paramPrintWriter.println(":");
        zza.zzef.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      } 
    } 
  }
  
  public final void onStart() {
    super.onStart();
    boolean bool = this.mStarted;
    String str = String.valueOf(this.zzed);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 14);
    stringBuilder.append("onStart ");
    stringBuilder.append(bool);
    stringBuilder.append(" ");
    stringBuilder.append(str);
    Log.d("AutoManageHelper", stringBuilder.toString());
    if (this.zzer.get() == null)
      for (byte b = 0; b < this.zzed.size(); b++) {
        zza zza = zzd(b);
        if (zza != null)
          zza.zzef.connect(); 
      }  
  }
  
  public void onStop() {
    super.onStop();
    for (byte b = 0; b < this.zzed.size(); b++) {
      zza zza = zzd(b);
      if (zza != null)
        zza.zzef.disconnect(); 
    } 
  }
  
  public final void zza(int paramInt, GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    Preconditions.checkNotNull(paramGoogleApiClient, "GoogleApiClient instance cannot be null");
    if (this.zzed.indexOfKey(paramInt) < 0) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder1 = new StringBuilder(54);
    stringBuilder1.append("Already managing a GoogleApiClient with id ");
    stringBuilder1.append(paramInt);
    Preconditions.checkState(bool, stringBuilder1.toString());
    zzl zzl = this.zzer.get();
    boolean bool = this.mStarted;
    String str = String.valueOf(zzl);
    StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(str).length() + 49);
    stringBuilder2.append("starting AutoManage for client ");
    stringBuilder2.append(paramInt);
    stringBuilder2.append(" ");
    stringBuilder2.append(bool);
    stringBuilder2.append(" ");
    stringBuilder2.append(str);
    Log.d("AutoManageHelper", stringBuilder2.toString());
    paramOnConnectionFailedListener = new zza(this, paramInt, paramGoogleApiClient, paramOnConnectionFailedListener);
    this.zzed.put(paramInt, paramOnConnectionFailedListener);
    if (this.mStarted && zzl == null) {
      String str1 = String.valueOf(paramGoogleApiClient);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 11);
      stringBuilder.append("connecting ");
      stringBuilder.append(str1);
      Log.d("AutoManageHelper", stringBuilder.toString());
      paramGoogleApiClient.connect();
    } 
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
    if (paramInt < 0) {
      Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
      return;
    } 
    zza zza = (zza)this.zzed.get(paramInt);
    if (zza != null) {
      zzc(paramInt);
      GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zza.zzeg;
      if (onConnectionFailedListener != null)
        onConnectionFailedListener.onConnectionFailed(paramConnectionResult); 
    } 
  }
  
  public final void zzc(int paramInt) {
    zza zza = (zza)this.zzed.get(paramInt);
    this.zzed.remove(paramInt);
    if (zza != null) {
      zza.zzef.unregisterConnectionFailedListener(zza);
      zza.zzef.disconnect();
    } 
  }
  
  protected final void zzr() {
    for (byte b = 0; b < this.zzed.size(); b++) {
      zza zza = zzd(b);
      if (zza != null)
        zza.zzef.connect(); 
    } 
  }
  
  private final class zza implements GoogleApiClient.OnConnectionFailedListener {
    public final int zzee;
    
    public final GoogleApiClient zzef;
    
    public final GoogleApiClient.OnConnectionFailedListener zzeg;
    
    public zza(zzi this$0, int param1Int, GoogleApiClient param1GoogleApiClient, GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      this.zzee = param1Int;
      this.zzef = param1GoogleApiClient;
      this.zzeg = param1OnConnectionFailedListener;
      param1GoogleApiClient.registerConnectionFailedListener(this);
    }
    
    public final void onConnectionFailed(@NonNull ConnectionResult param1ConnectionResult) {
      String str = String.valueOf(param1ConnectionResult);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 27);
      stringBuilder.append("beginFailureResolution for ");
      stringBuilder.append(str);
      Log.d("AutoManageHelper", stringBuilder.toString());
      this.zzeh.zzb(param1ConnectionResult, this.zzee);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */