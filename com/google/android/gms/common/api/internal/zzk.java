package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzk extends LifecycleCallback implements DialogInterface.OnCancelListener {
  protected volatile boolean mStarted;
  
  protected final GoogleApiAvailability zzdg;
  
  protected final AtomicReference<zzl> zzer = new AtomicReference<>(null);
  
  private final Handler zzes = new Handler(Looper.getMainLooper());
  
  protected zzk(LifecycleFragment paramLifecycleFragment) {
    this(paramLifecycleFragment, GoogleApiAvailability.getInstance());
  }
  
  @VisibleForTesting
  private zzk(LifecycleFragment paramLifecycleFragment, GoogleApiAvailability paramGoogleApiAvailability) {
    super(paramLifecycleFragment);
    this.zzdg = paramGoogleApiAvailability;
  }
  
  private static int zza(@Nullable zzl paramzzl) {
    return (paramzzl == null) ? -1 : paramzzl.zzu();
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    zzl zzl2;
    zzl zzl1 = this.zzer.get();
    int i = 1;
    boolean bool = true;
    switch (paramInt1) {
      default:
        zzl2 = zzl1;
        paramInt2 = 0;
        break;
      case 2:
        i = this.zzdg.isGooglePlayServicesAvailable((Context)getActivity());
        if (i == 0) {
          paramInt1 = bool;
        } else {
          paramInt1 = 0;
        } 
        if (zzl1 == null)
          return; 
        zzl2 = zzl1;
        paramInt2 = paramInt1;
        if (zzl1.getConnectionResult().getErrorCode() == 18) {
          zzl2 = zzl1;
          paramInt2 = paramInt1;
          if (i == 18)
            return; 
        } 
        break;
      case 1:
        if (paramInt2 == -1) {
          zzl2 = zzl1;
          paramInt2 = i;
          break;
        } 
        zzl2 = zzl1;
        if (paramInt2 == 0) {
          paramInt1 = 13;
          if (paramIntent != null)
            paramInt1 = paramIntent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13); 
          zzl2 = new zzl(new ConnectionResult(paramInt1, null), zza(zzl1));
          this.zzer.set(zzl2);
        } 
        paramInt2 = 0;
        break;
    } 
    if (paramInt2 != 0) {
      zzt();
      return;
    } 
    if (zzl2 != null)
      zza(zzl2.getConnectionResult(), zzl2.zzu()); 
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {
    zza(new ConnectionResult(13, null), zza(this.zzer.get()));
    zzt();
  }
  
  public final void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (paramBundle != null) {
      AtomicReference<zzl> atomicReference = this.zzer;
      if (paramBundle.getBoolean("resolving_error", false)) {
        zzl zzl = new zzl(new ConnectionResult(paramBundle.getInt("failed_status"), (PendingIntent)paramBundle.getParcelable("failed_resolution")), paramBundle.getInt("failed_client_id", -1));
      } else {
        paramBundle = null;
      } 
      atomicReference.set(paramBundle);
    } 
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    zzl zzl = this.zzer.get();
    if (zzl != null) {
      paramBundle.putBoolean("resolving_error", true);
      paramBundle.putInt("failed_client_id", zzl.zzu());
      paramBundle.putInt("failed_status", zzl.getConnectionResult().getErrorCode());
      paramBundle.putParcelable("failed_resolution", (Parcelable)zzl.getConnectionResult().getResolution());
    } 
  }
  
  public void onStart() {
    super.onStart();
    this.mStarted = true;
  }
  
  public void onStop() {
    super.onStop();
    this.mStarted = false;
  }
  
  protected abstract void zza(ConnectionResult paramConnectionResult, int paramInt);
  
  public final void zzb(ConnectionResult paramConnectionResult, int paramInt) {
    zzl zzl = new zzl(paramConnectionResult, paramInt);
    if (this.zzer.compareAndSet(null, zzl))
      this.zzes.post(new zzm(this, zzl)); 
  }
  
  protected abstract void zzr();
  
  protected final void zzt() {
    this.zzer.set(null);
    zzr();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */