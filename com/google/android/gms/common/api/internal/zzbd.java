package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzbd implements zzbp, zzq {
  private final Context mContext;
  
  private final Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> zzdh;
  
  final zzav zzfq;
  
  private final Lock zzga;
  
  private final ClientSettings zzgf;
  
  private final Map<Api<?>, Boolean> zzgi;
  
  private final GoogleApiAvailabilityLight zzgk;
  
  final Map<Api.AnyClientKey<?>, Api.Client> zzil;
  
  private final Condition zziz;
  
  private final zzbf zzja;
  
  final Map<Api.AnyClientKey<?>, ConnectionResult> zzjb = new HashMap<>();
  
  private volatile zzbc zzjc;
  
  private ConnectionResult zzjd = null;
  
  int zzje;
  
  final zzbq zzjf;
  
  public zzbd(Context paramContext, zzav paramzzav, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap1, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> paramAbstractClientBuilder, ArrayList<zzp> paramArrayList, zzbq paramzzbq) {
    this.mContext = paramContext;
    this.zzga = paramLock;
    this.zzgk = paramGoogleApiAvailabilityLight;
    this.zzil = paramMap;
    this.zzgf = paramClientSettings;
    this.zzgi = paramMap1;
    this.zzdh = paramAbstractClientBuilder;
    this.zzfq = paramzzav;
    this.zzjf = paramzzbq;
    ArrayList<zzp> arrayList = paramArrayList;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      paramContext = (Context)arrayList.get(b);
      b++;
      ((zzp)paramContext).zza(this);
    } 
    this.zzja = new zzbf(this, paramLooper);
    this.zziz = paramLock.newCondition();
    this.zzjc = new zzau(this);
  }
  
  @GuardedBy("mLock")
  public final ConnectionResult blockingConnect() {
    connect();
    while (isConnecting()) {
      try {
        this.zziz.await();
      } catch (InterruptedException interruptedException) {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      } 
    } 
    return isConnected() ? ConnectionResult.RESULT_SUCCESS : ((this.zzjd != null) ? this.zzjd : new ConnectionResult(13, null));
  }
  
  @GuardedBy("mLock")
  public final ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit) {
    connect();
    for (paramLong = paramTimeUnit.toNanos(paramLong); isConnecting(); paramLong = this.zziz.awaitNanos(paramLong)) {
      if (paramLong <= 0L)
        try {
          disconnect();
          return new ConnectionResult(14, null);
        } catch (InterruptedException interruptedException) {
          Thread.currentThread().interrupt();
          return new ConnectionResult(15, null);
        }  
    } 
    return isConnected() ? ConnectionResult.RESULT_SUCCESS : ((this.zzjd != null) ? this.zzjd : new ConnectionResult(13, null));
  }
  
  @GuardedBy("mLock")
  public final void connect() {
    this.zzjc.connect();
  }
  
  @GuardedBy("mLock")
  public final void disconnect() {
    if (this.zzjc.disconnect())
      this.zzjb.clear(); 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    String str = String.valueOf(paramString).concat("  ");
    paramPrintWriter.append(paramString).append("mState=").println(this.zzjc);
    for (Api<?> api : this.zzgi.keySet()) {
      paramPrintWriter.append(paramString).append(api.getName()).println(":");
      ((Api.Client)this.zzil.get(api.getClientKey())).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  @GuardedBy("mLock")
  public final <A extends Api.AnyClient, R extends com.google.android.gms.common.api.Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T paramT) {
    paramT.zzx();
    return this.zzjc.enqueue(paramT);
  }
  
  @GuardedBy("mLock")
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(@NonNull T paramT) {
    paramT.zzx();
    return this.zzjc.execute(paramT);
  }
  
  @Nullable
  @GuardedBy("mLock")
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    Api.AnyClientKey anyClientKey = paramApi.getClientKey();
    if (this.zzil.containsKey(anyClientKey)) {
      if (((Api.Client)this.zzil.get(anyClientKey)).isConnected())
        return ConnectionResult.RESULT_SUCCESS; 
      if (this.zzjb.containsKey(anyClientKey))
        return this.zzjb.get(anyClientKey); 
    } 
    return null;
  }
  
  public final boolean isConnected() {
    return this.zzjc instanceof zzag;
  }
  
  public final boolean isConnecting() {
    return this.zzjc instanceof zzaj;
  }
  
  public final boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener) {
    return false;
  }
  
  public final void maybeSignOut() {}
  
  public final void onConnected(@Nullable Bundle paramBundle) {
    this.zzga.lock();
    try {
      this.zzjc.onConnected(paramBundle);
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void onConnectionSuspended(int paramInt) {
    this.zzga.lock();
    try {
      this.zzjc.onConnectionSuspended(paramInt);
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void zza(@NonNull ConnectionResult paramConnectionResult, @NonNull Api<?> paramApi, boolean paramBoolean) {
    this.zzga.lock();
    try {
      this.zzjc.zza(paramConnectionResult, paramApi, paramBoolean);
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  final void zza(zzbe paramzzbe) {
    Message message = this.zzja.obtainMessage(1, paramzzbe);
    this.zzja.sendMessage(message);
  }
  
  final void zzb(RuntimeException paramRuntimeException) {
    Message message = this.zzja.obtainMessage(2, paramRuntimeException);
    this.zzja.sendMessage(message);
  }
  
  final void zzbc() {
    this.zzga.lock();
    try {
      zzaj zzaj = new zzaj();
      this(this, this.zzgf, this.zzgi, this.zzgk, this.zzdh, this.zzga, this.mContext);
      this.zzjc = zzaj;
      this.zzjc.begin();
      this.zziz.signalAll();
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  final void zzbd() {
    this.zzga.lock();
    try {
      this.zzfq.zzaz();
      zzag zzag = new zzag();
      this(this);
      this.zzjc = zzag;
      this.zzjc.begin();
      this.zziz.signalAll();
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  final void zzf(ConnectionResult paramConnectionResult) {
    this.zzga.lock();
    try {
      this.zzjd = paramConnectionResult;
      zzau zzau = new zzau();
      this(this);
      this.zzjc = zzau;
      this.zzjc.begin();
      this.zziz.signalAll();
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  @GuardedBy("mLock")
  public final void zzz() {
    if (isConnected())
      ((zzag)this.zzjc).zzap(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */