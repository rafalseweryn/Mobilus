package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

final class zzr implements zzbp {
  private final Context mContext;
  
  private final Looper zzcn;
  
  private final zzav zzfq;
  
  private final zzbd zzfr;
  
  private final zzbd zzfs;
  
  private final Map<Api.AnyClientKey<?>, zzbd> zzft;
  
  private final Set<SignInConnectionListener> zzfu = Collections.newSetFromMap(new WeakHashMap<>());
  
  private final Api.Client zzfv;
  
  private Bundle zzfw;
  
  private ConnectionResult zzfx = null;
  
  private ConnectionResult zzfy = null;
  
  private boolean zzfz = false;
  
  private final Lock zzga;
  
  @GuardedBy("mLock")
  private int zzgb = 0;
  
  private zzr(Context paramContext, zzav paramzzav, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap1, Map<Api.AnyClientKey<?>, Api.Client> paramMap2, ClientSettings paramClientSettings, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> paramAbstractClientBuilder, Api.Client paramClient, ArrayList<zzp> paramArrayList1, ArrayList<zzp> paramArrayList2, Map<Api<?>, Boolean> paramMap3, Map<Api<?>, Boolean> paramMap4) {
    this.mContext = paramContext;
    this.zzfq = paramzzav;
    this.zzga = paramLock;
    this.zzcn = paramLooper;
    this.zzfv = paramClient;
    this.zzfr = new zzbd(paramContext, this.zzfq, paramLock, paramLooper, paramGoogleApiAvailabilityLight, paramMap2, null, paramMap4, null, paramArrayList2, new zzt(this, null));
    this.zzfs = new zzbd(paramContext, this.zzfq, paramLock, paramLooper, paramGoogleApiAvailabilityLight, paramMap1, paramClientSettings, paramMap3, paramAbstractClientBuilder, paramArrayList1, new zzu(this, null));
    ArrayMap arrayMap = new ArrayMap();
    Iterator<Api.AnyClientKey> iterator = paramMap2.keySet().iterator();
    while (iterator.hasNext())
      arrayMap.put(iterator.next(), this.zzfr); 
    iterator = paramMap1.keySet().iterator();
    while (iterator.hasNext())
      arrayMap.put(iterator.next(), this.zzfs); 
    this.zzft = Collections.unmodifiableMap((Map<? extends Api.AnyClientKey<?>, ? extends zzbd>)arrayMap);
  }
  
  public static zzr zza(Context paramContext, zzav paramzzav, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap1, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> paramAbstractClientBuilder, ArrayList<zzp> paramArrayList) {
    Api.Client client;
    ArrayMap<Api.AnyClientKey, Api.Client> arrayMap1 = new ArrayMap();
    ArrayMap<Api.AnyClientKey, Api.Client> arrayMap2 = new ArrayMap();
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    paramMap = null;
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      Api.Client client1 = (Api.Client)entry.getValue();
      if (client1.providesSignIn())
        client = client1; 
      if (client1.requiresSignIn()) {
        arrayMap1.put((Api.AnyClientKey)entry.getKey(), client1);
        continue;
      } 
      arrayMap2.put((Api.AnyClientKey)entry.getKey(), client1);
    } 
    Preconditions.checkState(arrayMap1.isEmpty() ^ true, "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
    ArrayMap<Api, Boolean> arrayMap3 = new ArrayMap();
    ArrayMap<Api, Boolean> arrayMap4 = new ArrayMap();
    for (Api<?> api : paramMap1.keySet()) {
      Api.AnyClientKey anyClientKey = api.getClientKey();
      if (arrayMap1.containsKey(anyClientKey)) {
        arrayMap3.put(api, paramMap1.get(api));
        continue;
      } 
      if (arrayMap2.containsKey(anyClientKey)) {
        arrayMap4.put(api, paramMap1.get(api));
        continue;
      } 
      throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
    } 
    ArrayList<zzp> arrayList2 = new ArrayList();
    ArrayList<zzp> arrayList1 = new ArrayList();
    paramArrayList = paramArrayList;
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      zzp zzp = (zzp)paramArrayList.get(b);
      b++;
      zzp = zzp;
      if (arrayMap3.containsKey(zzp.mApi)) {
        arrayList2.add(zzp);
        continue;
      } 
      if (arrayMap4.containsKey(zzp.mApi)) {
        arrayList1.add(zzp);
        continue;
      } 
      throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
    } 
    return new zzr(paramContext, paramzzav, paramLock, paramLooper, paramGoogleApiAvailabilityLight, (Map)arrayMap1, (Map)arrayMap2, paramClientSettings, paramAbstractClientBuilder, client, arrayList2, arrayList1, (Map)arrayMap3, (Map)arrayMap4);
  }
  
  @GuardedBy("mLock")
  private final void zza(int paramInt, boolean paramBoolean) {
    this.zzfq.zzb(paramInt, paramBoolean);
    this.zzfy = null;
    this.zzfx = null;
  }
  
  private final void zza(Bundle paramBundle) {
    if (this.zzfw == null) {
      this.zzfw = paramBundle;
      return;
    } 
    if (paramBundle != null)
      this.zzfw.putAll(paramBundle); 
  }
  
  @GuardedBy("mLock")
  private final void zza(ConnectionResult paramConnectionResult) {
    switch (this.zzgb) {
      default:
        Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
        break;
      case 2:
        this.zzfq.zzc(paramConnectionResult);
      case 1:
        zzab();
        break;
    } 
    this.zzgb = 0;
  }
  
  private final boolean zza(BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient> paramApiMethodImpl) {
    Api.AnyClientKey<? extends Api.AnyClient> anyClientKey = paramApiMethodImpl.getClientKey();
    Preconditions.checkArgument(this.zzft.containsKey(anyClientKey), "GoogleApiClient is not configured to use the API required for this call.");
    return ((zzbd)this.zzft.get(anyClientKey)).equals(this.zzfs);
  }
  
  @GuardedBy("mLock")
  private final void zzaa() {
    if (zzb(this.zzfx)) {
      if (zzb(this.zzfy) || zzac()) {
        switch (this.zzgb) {
          default:
            Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
            break;
          case 2:
            this.zzfq.zzb(this.zzfw);
          case 1:
            zzab();
            break;
        } 
        this.zzgb = 0;
        return;
      } 
      if (this.zzfy != null) {
        if (this.zzgb == 1) {
          zzab();
          return;
        } 
        zza(this.zzfy);
        this.zzfr.disconnect();
        return;
      } 
    } else {
      if (this.zzfx != null && zzb(this.zzfy)) {
        this.zzfs.disconnect();
        zza(this.zzfx);
        return;
      } 
      if (this.zzfx != null && this.zzfy != null) {
        ConnectionResult connectionResult = this.zzfx;
        if (this.zzfs.zzje < this.zzfr.zzje)
          connectionResult = this.zzfy; 
        zza(connectionResult);
      } 
    } 
  }
  
  @GuardedBy("mLock")
  private final void zzab() {
    Iterator<SignInConnectionListener> iterator = this.zzfu.iterator();
    while (iterator.hasNext())
      ((SignInConnectionListener)iterator.next()).onComplete(); 
    this.zzfu.clear();
  }
  
  @GuardedBy("mLock")
  private final boolean zzac() {
    return (this.zzfy != null && this.zzfy.getErrorCode() == 4);
  }
  
  @Nullable
  private final PendingIntent zzad() {
    return (this.zzfv == null) ? null : PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzfq), this.zzfv.getSignInIntent(), 134217728);
  }
  
  private static boolean zzb(ConnectionResult paramConnectionResult) {
    return (paramConnectionResult != null && paramConnectionResult.isSuccess());
  }
  
  @GuardedBy("mLock")
  public final ConnectionResult blockingConnect() {
    throw new UnsupportedOperationException();
  }
  
  @GuardedBy("mLock")
  public final ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit) {
    throw new UnsupportedOperationException();
  }
  
  @GuardedBy("mLock")
  public final void connect() {
    this.zzgb = 2;
    this.zzfz = false;
    this.zzfy = null;
    this.zzfx = null;
    this.zzfr.connect();
    this.zzfs.connect();
  }
  
  @GuardedBy("mLock")
  public final void disconnect() {
    this.zzfy = null;
    this.zzfx = null;
    this.zzgb = 0;
    this.zzfr.disconnect();
    this.zzfs.disconnect();
    zzab();
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.append(paramString).append("authClient").println(":");
    this.zzfs.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.append(paramString).append("anonClient").println(":");
    this.zzfr.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  @GuardedBy("mLock")
  public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T paramT) {
    if (zza((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>)paramT)) {
      if (zzac()) {
        paramT.setFailedResult(new Status(4, null, zzad()));
        return paramT;
      } 
      zzbd zzbd2 = this.zzfs;
      return zzbd2.enqueue(paramT);
    } 
    zzbd zzbd1 = this.zzfr;
    return zzbd1.enqueue(paramT);
  }
  
  @GuardedBy("mLock")
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T paramT) {
    if (zza((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>)paramT)) {
      if (zzac()) {
        paramT.setFailedResult(new Status(4, null, zzad()));
        return paramT;
      } 
      zzbd zzbd2 = this.zzfs;
      return zzbd2.execute(paramT);
    } 
    zzbd zzbd1 = this.zzfr;
    return zzbd1.execute(paramT);
  }
  
  @Nullable
  @GuardedBy("mLock")
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    if (((zzbd)this.zzft.get(paramApi.getClientKey())).equals(this.zzfs)) {
      if (zzac())
        return new ConnectionResult(4, zzad()); 
      zzbd zzbd2 = this.zzfs;
      return zzbd2.getConnectionResult(paramApi);
    } 
    zzbd zzbd1 = this.zzfr;
    return zzbd1.getConnectionResult(paramApi);
  }
  
  public final boolean isConnected() {
    this.zzga.lock();
    try {
      boolean bool = this.zzfr.isConnected();
      boolean bool1 = true;
      if (bool) {
        bool = bool1;
        if (!this.zzfs.isConnected()) {
          bool = bool1;
          if (!zzac()) {
            int i = this.zzgb;
            if (i == 1) {
              bool = bool1;
              return bool;
            } 
          } else {
            return bool;
          } 
        } else {
          return bool;
        } 
      } 
      bool = false;
      return bool;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final boolean isConnecting() {
    this.zzga.lock();
    try {
      boolean bool;
      int i = this.zzgb;
      if (i == 2) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener) {
    this.zzga.lock();
    try {
      if ((isConnecting() || isConnected()) && !this.zzfs.isConnected()) {
        this.zzfu.add(paramSignInConnectionListener);
        if (this.zzgb == 0)
          this.zzgb = 1; 
        this.zzfy = null;
        this.zzfs.connect();
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
      boolean bool = isConnecting();
      this.zzfs.disconnect();
      ConnectionResult connectionResult = new ConnectionResult();
      this(4);
      this.zzfy = connectionResult;
      if (bool) {
        Handler handler = new Handler();
        this(this.zzcn);
        zzs zzs = new zzs();
        this(this);
        handler.post(zzs);
      } else {
        zzab();
      } 
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  @GuardedBy("mLock")
  public final void zzz() {
    this.zzfr.zzz();
    this.zzfs.zzz();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */