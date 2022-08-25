package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzw implements zzbp {
  private final Looper zzcn;
  
  private final GoogleApiManager zzcq;
  
  private final Lock zzga;
  
  private final ClientSettings zzgf;
  
  private final Map<Api.AnyClientKey<?>, zzv<?>> zzgg = new HashMap<>();
  
  private final Map<Api.AnyClientKey<?>, zzv<?>> zzgh = new HashMap<>();
  
  private final Map<Api<?>, Boolean> zzgi;
  
  private final zzav zzgj;
  
  private final GoogleApiAvailabilityLight zzgk;
  
  private final Condition zzgl;
  
  private final boolean zzgm;
  
  private final boolean zzgn;
  
  private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zzgo = new LinkedList<>();
  
  @GuardedBy("mLock")
  private boolean zzgp;
  
  @GuardedBy("mLock")
  private Map<zzh<?>, ConnectionResult> zzgq;
  
  @GuardedBy("mLock")
  private Map<zzh<?>, ConnectionResult> zzgr;
  
  @GuardedBy("mLock")
  private zzz zzgs;
  
  @GuardedBy("mLock")
  private ConnectionResult zzgt;
  
  public zzw(Context paramContext, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap1, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> paramAbstractClientBuilder, ArrayList<zzp> paramArrayList, zzav paramzzav, boolean paramBoolean) {
    this.zzga = paramLock;
    this.zzcn = paramLooper;
    this.zzgl = paramLock.newCondition();
    this.zzgk = paramGoogleApiAvailabilityLight;
    this.zzgj = paramzzav;
    this.zzgi = paramMap1;
    this.zzgf = paramClientSettings;
    this.zzgm = paramBoolean;
    HashMap<Object, Object> hashMap1 = new HashMap<>();
    for (Api<?> api : paramMap1.keySet())
      hashMap1.put(api.getClientKey(), api); 
    HashMap<Object, Object> hashMap2 = new HashMap<>();
    ArrayList<zzp> arrayList = paramArrayList;
    int i = arrayList.size();
    int j = 0;
    while (j < i) {
      paramArrayList = (ArrayList<zzp>)arrayList.get(j);
      j++;
      zzp zzp = (zzp)paramArrayList;
      hashMap2.put(zzp.mApi, zzp);
    } 
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    int k = 0;
    i = 1;
    for (j = 0; iterator.hasNext(); j = m) {
      Map.Entry entry = iterator.next();
      Api<Api.ApiOptions> api = (Api)hashMap1.get(entry.getKey());
      Api.Client client = (Api.Client)entry.getValue();
      if (client.requiresGooglePlayServices()) {
        if (!((Boolean)this.zzgi.get(api)).booleanValue()) {
          k = 1;
        } else {
          k = j;
        } 
        j = 1;
      } else {
        i = k;
        k = j;
        boolean bool = false;
        j = i;
        i = bool;
      } 
      zzv<Api.ApiOptions> zzv = new zzv<>(paramContext, api, paramLooper, client, (zzp)hashMap2.get(api), paramClientSettings, paramAbstractClientBuilder);
      this.zzgg.put((Api.AnyClientKey)entry.getKey(), zzv);
      if (client.requiresSignIn())
        this.zzgh.put((Api.AnyClientKey)entry.getKey(), zzv); 
      int m = k;
      k = j;
    } 
    if (k != 0 && i == 0 && j == 0) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    this.zzgn = paramBoolean;
    this.zzcq = GoogleApiManager.zzbf();
  }
  
  @Nullable
  private final ConnectionResult zza(@NonNull Api.AnyClientKey<?> paramAnyClientKey) {
    this.zzga.lock();
    try {
      zzv zzv = this.zzgg.get(paramAnyClientKey);
      if (this.zzgq != null && zzv != null)
        return this.zzgq.get(zzv.zzm()); 
      return null;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  private final boolean zza(zzv<?> paramzzv, ConnectionResult paramConnectionResult) {
    return (!paramConnectionResult.isSuccess() && !paramConnectionResult.hasResolution() && ((Boolean)this.zzgi.get(paramzzv.getApi())).booleanValue() && paramzzv.zzae().requiresGooglePlayServices() && this.zzgk.isUserResolvableError(paramConnectionResult.getErrorCode()));
  }
  
  private final boolean zzaf() {
    this.zzga.lock();
    try {
      if (!this.zzgp || !this.zzgm)
        return false; 
      Iterator<Api.AnyClientKey> iterator = this.zzgh.keySet().iterator();
      while (iterator.hasNext()) {
        ConnectionResult connectionResult = zza(iterator.next());
        if (connectionResult != null) {
          boolean bool = connectionResult.isSuccess();
          if (!bool)
            continue; 
          continue;
        } 
        continue;
      } 
      return true;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  @GuardedBy("mLock")
  private final void zzag() {
    zzav zzav1;
    Set<?> set;
    if (this.zzgf == null) {
      zzav1 = this.zzgj;
      set = Collections.emptySet();
    } else {
      set = new HashSet(this.zzgf.getRequiredScopes());
      Map map = this.zzgf.getOptionalApiSettings();
      for (Api<?> api : (Iterable<Api<?>>)map.keySet()) {
        ConnectionResult connectionResult = getConnectionResult(api);
        if (connectionResult != null && connectionResult.isSuccess())
          set.addAll(((ClientSettings.OptionalApiSettings)map.get(api)).mScopes); 
      } 
      zzav1 = this.zzgj;
    } 
    zzav1.zzim = (Set)set;
  }
  
  @GuardedBy("mLock")
  private final void zzah() {
    while (!this.zzgo.isEmpty())
      execute(this.zzgo.remove()); 
    this.zzgj.zzb((Bundle)null);
  }
  
  @Nullable
  @GuardedBy("mLock")
  private final ConnectionResult zzai() {
    Iterator<zzv> iterator = this.zzgg.values().iterator();
    ConnectionResult connectionResult1 = null;
    int i = 0;
    int j = i;
    ConnectionResult connectionResult2 = null;
    while (iterator.hasNext()) {
      zzv zzv = iterator.next();
      Api api = zzv.getApi();
      zzh zzh = zzv.zzm();
      ConnectionResult connectionResult = this.zzgq.get(zzh);
      if (!connectionResult.isSuccess() && (!((Boolean)this.zzgi.get(api)).booleanValue() || connectionResult.hasResolution() || this.zzgk.isUserResolvableError(connectionResult.getErrorCode()))) {
        if (connectionResult.getErrorCode() == 4 && this.zzgm) {
          int m = api.zzj().getPriority();
          if (connectionResult2 == null || j > m) {
            connectionResult2 = connectionResult;
            j = m;
          } 
          continue;
        } 
        int k = api.zzj().getPriority();
        if (connectionResult1 == null || i > k) {
          connectionResult1 = connectionResult;
          i = k;
        } 
      } 
    } 
    return (connectionResult1 != null && connectionResult2 != null && i > j) ? connectionResult2 : connectionResult1;
  }
  
  private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zzb(@NonNull T paramT) {
    Api.AnyClientKey<?> anyClientKey = paramT.getClientKey();
    ConnectionResult connectionResult = zza(anyClientKey);
    if (connectionResult != null && connectionResult.getErrorCode() == 4) {
      paramT.setFailedResult(new Status(4, null, this.zzcq.zza(((zzv)this.zzgg.get(anyClientKey)).zzm(), System.identityHashCode(this.zzgj))));
      return true;
    } 
    return false;
  }
  
  @GuardedBy("mLock")
  public final ConnectionResult blockingConnect() {
    connect();
    while (isConnecting()) {
      try {
        this.zzgl.await();
      } catch (InterruptedException interruptedException) {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      } 
    } 
    return isConnected() ? ConnectionResult.RESULT_SUCCESS : ((this.zzgt != null) ? this.zzgt : new ConnectionResult(13, null));
  }
  
  @GuardedBy("mLock")
  public final ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit) {
    connect();
    for (paramLong = paramTimeUnit.toNanos(paramLong); isConnecting(); paramLong = this.zzgl.awaitNanos(paramLong)) {
      if (paramLong <= 0L)
        try {
          disconnect();
          return new ConnectionResult(14, null);
        } catch (InterruptedException interruptedException) {
          Thread.currentThread().interrupt();
          return new ConnectionResult(15, null);
        }  
    } 
    return isConnected() ? ConnectionResult.RESULT_SUCCESS : ((this.zzgt != null) ? this.zzgt : new ConnectionResult(13, null));
  }
  
  public final void connect() {
    this.zzga.lock();
    try {
      boolean bool = this.zzgp;
      if (!bool) {
        this.zzgp = true;
        this.zzgq = null;
        this.zzgr = null;
        this.zzgs = null;
        this.zzgt = null;
        this.zzcq.zzr();
        Task<Map<zzh<?>, String>> task = this.zzcq.zza(this.zzgg.values());
        HandlerExecutor handlerExecutor = new HandlerExecutor();
        this(this.zzcn);
        zzy zzy = new zzy();
        this(this, null);
        task.addOnCompleteListener((Executor)handlerExecutor, zzy);
      } 
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void disconnect() {
    this.zzga.lock();
    try {
      this.zzgp = false;
      this.zzgq = null;
      this.zzgr = null;
      if (this.zzgs != null) {
        this.zzgs.cancel();
        this.zzgs = null;
      } 
      this.zzgt = null;
      while (!this.zzgo.isEmpty()) {
        BaseImplementation.ApiMethodImpl apiMethodImpl = this.zzgo.remove();
        apiMethodImpl.zza((zzcn)null);
        apiMethodImpl.cancel();
      } 
      this.zzgl.signalAll();
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T paramT) {
    if (this.zzgm && zzb((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>)paramT))
      return paramT; 
    if (!isConnected()) {
      this.zzgo.add((BaseImplementation.ApiMethodImpl<?, ?>)paramT);
      return paramT;
    } 
    this.zzgj.zzir.zzb((BasePendingResult<? extends Result>)paramT);
    return (T)((zzv)this.zzgg.get(paramT.getClientKey())).doRead((BaseImplementation.ApiMethodImpl)paramT);
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T paramT) {
    Api.AnyClientKey anyClientKey = paramT.getClientKey();
    if (this.zzgm && zzb((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>)paramT))
      return paramT; 
    this.zzgj.zzir.zzb((BasePendingResult<? extends Result>)paramT);
    return (T)((zzv)this.zzgg.get(anyClientKey)).doWrite((BaseImplementation.ApiMethodImpl)paramT);
  }
  
  @Nullable
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    return zza(paramApi.getClientKey());
  }
  
  public final boolean isConnected() {
    this.zzga.lock();
    try {
      if (this.zzgq != null) {
        ConnectionResult connectionResult = this.zzgt;
        if (connectionResult == null)
          return true; 
      } 
      return false;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final boolean isConnecting() {
    this.zzga.lock();
    try {
      if (this.zzgq == null) {
        boolean bool = this.zzgp;
        if (bool) {
          bool = true;
          return bool;
        } 
      } 
      return false;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener) {
    this.zzga.lock();
    try {
      if (this.zzgp && !zzaf()) {
        this.zzcq.zzr();
        zzz zzz1 = new zzz();
        this(this, paramSignInConnectionListener);
        this.zzgs = zzz1;
        Task<Map<zzh<?>, String>> task = this.zzcq.zza(this.zzgh.values());
        HandlerExecutor handlerExecutor = new HandlerExecutor();
        this(this.zzcn);
        task.addOnCompleteListener((Executor)handlerExecutor, this.zzgs);
        return true;
      } 
      return false;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void maybeSignOut() {
    this.zzga.lock();
    try {
      this.zzcq.maybeSignOut();
      if (this.zzgs != null) {
        this.zzgs.cancel();
        this.zzgs = null;
      } 
      if (this.zzgr == null) {
        ArrayMap arrayMap = new ArrayMap();
        this(this.zzgh.size());
        this.zzgr = (Map<zzh<?>, ConnectionResult>)arrayMap;
      } 
      ConnectionResult connectionResult = new ConnectionResult();
      this(4);
      for (zzv<?> zzv : this.zzgh.values())
        this.zzgr.put(zzv.zzm(), connectionResult); 
      if (this.zzgq != null)
        this.zzgq.putAll(this.zzgr); 
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void zzz() {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */