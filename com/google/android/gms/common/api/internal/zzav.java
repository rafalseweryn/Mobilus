package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzav extends GoogleApiClient implements zzbq {
  private final Context mContext;
  
  private final Looper zzcn;
  
  private final int zzde;
  
  private final GoogleApiAvailability zzdg;
  
  private final Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> zzdh;
  
  private boolean zzdk;
  
  private final Lock zzga;
  
  private final ClientSettings zzgf;
  
  private final Map<Api<?>, Boolean> zzgi;
  
  @VisibleForTesting
  final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zzgo = new LinkedList<>();
  
  private final GmsClientEventManager zzie;
  
  private zzbp zzif = null;
  
  private volatile boolean zzig;
  
  private long zzih = 120000L;
  
  private long zzii = 5000L;
  
  private final zzba zzij;
  
  @VisibleForTesting
  private GooglePlayServicesUpdatedReceiver zzik;
  
  final Map<Api.AnyClientKey<?>, Api.Client> zzil;
  
  Set<Scope> zzim = new HashSet<>();
  
  private final ListenerHolders zzin = new ListenerHolders();
  
  private final ArrayList<zzp> zzio;
  
  private Integer zzip = null;
  
  Set<zzch> zziq = null;
  
  final zzck zzir;
  
  private final GmsClientEventManager.GmsClientEventState zzis = new zzaw(this);
  
  public zzav(Context paramContext, Lock paramLock, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiAvailability paramGoogleApiAvailability, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> paramAbstractClientBuilder, Map<Api<?>, Boolean> paramMap, List<GoogleApiClient.ConnectionCallbacks> paramList, List<GoogleApiClient.OnConnectionFailedListener> paramList1, Map<Api.AnyClientKey<?>, Api.Client> paramMap1, int paramInt1, int paramInt2, ArrayList<zzp> paramArrayList, boolean paramBoolean) {
    this.mContext = paramContext;
    this.zzga = paramLock;
    this.zzdk = false;
    this.zzie = new GmsClientEventManager(paramLooper, this.zzis);
    this.zzcn = paramLooper;
    this.zzij = new zzba(this, paramLooper);
    this.zzdg = paramGoogleApiAvailability;
    this.zzde = paramInt1;
    if (this.zzde >= 0)
      this.zzip = Integer.valueOf(paramInt2); 
    this.zzgi = paramMap;
    this.zzil = paramMap1;
    this.zzio = paramArrayList;
    this.zzir = new zzck(this.zzil);
    for (GoogleApiClient.ConnectionCallbacks connectionCallbacks : paramList)
      this.zzie.registerConnectionCallbacks(connectionCallbacks); 
    for (GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener : paramList1)
      this.zzie.registerConnectionFailedListener(onConnectionFailedListener); 
    this.zzgf = paramClientSettings;
    this.zzdh = paramAbstractClientBuilder;
  }
  
  private final void resume() {
    this.zzga.lock();
    try {
      if (this.zzig)
        zzax(); 
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public static int zza(Iterable<Api.Client> paramIterable, boolean paramBoolean) {
    Iterator<Api.Client> iterator = paramIterable.iterator();
    boolean bool1 = false;
    boolean bool2 = false;
    while (iterator.hasNext()) {
      Api.Client client = iterator.next();
      boolean bool = bool1;
      if (client.requiresSignIn())
        bool = true; 
      bool1 = bool;
      if (client.providesSignIn()) {
        bool2 = true;
        bool1 = bool;
      } 
    } 
    return bool1 ? ((bool2 && paramBoolean) ? 2 : 1) : 3;
  }
  
  private final void zza(GoogleApiClient paramGoogleApiClient, StatusPendingResult paramStatusPendingResult, boolean paramBoolean) {
    Common.CommonApi.clearDefaultAccount(paramGoogleApiClient).setResultCallback(new zzaz(this, paramStatusPendingResult, paramBoolean, paramGoogleApiClient));
  }
  
  @GuardedBy("mLock")
  private final void zzax() {
    this.zzie.enableCallbacks();
    this.zzif.connect();
  }
  
  private final void zzay() {
    this.zzga.lock();
    try {
      if (zzaz())
        zzax(); 
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  private final void zzg(int paramInt) {
    if (this.zzip == null) {
      this.zzip = Integer.valueOf(paramInt);
    } else if (this.zzip.intValue() != paramInt) {
      String str1 = zzh(paramInt);
      String str2 = zzh(this.zzip.intValue());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 51 + String.valueOf(str2).length());
      stringBuilder.append("Cannot use sign-in mode: ");
      stringBuilder.append(str1);
      stringBuilder.append(". Mode was already set to ");
      stringBuilder.append(str2);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    if (this.zzif != null)
      return; 
    Iterator<Api.Client> iterator = this.zzil.values().iterator();
    boolean bool = false;
    paramInt = 0;
    while (iterator.hasNext()) {
      Api.Client client = iterator.next();
      boolean bool1 = bool;
      if (client.requiresSignIn())
        bool1 = true; 
      bool = bool1;
      if (client.providesSignIn()) {
        paramInt = 1;
        bool = bool1;
      } 
    } 
    switch (this.zzip.intValue()) {
      case 2:
        if (bool) {
          if (this.zzdk) {
            this.zzif = new zzw(this.mContext, this.zzga, this.zzcn, (GoogleApiAvailabilityLight)this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio, this, true);
            return;
          } 
          this.zzif = zzr.zza(this.mContext, this, this.zzga, this.zzcn, (GoogleApiAvailabilityLight)this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio);
          return;
        } 
        break;
      case 1:
        if (!bool)
          throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead."); 
        if (paramInt != 0)
          throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead."); 
        break;
    } 
    if (this.zzdk && paramInt == 0) {
      this.zzif = new zzw(this.mContext, this.zzga, this.zzcn, (GoogleApiAvailabilityLight)this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio, this, false);
      return;
    } 
    this.zzif = new zzbd(this.mContext, this, this.zzga, this.zzcn, (GoogleApiAvailabilityLight)this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio, this);
  }
  
  private static String zzh(int paramInt) {
    switch (paramInt) {
      default:
        return "UNKNOWN";
      case 3:
        return "SIGN_IN_MODE_NONE";
      case 2:
        return "SIGN_IN_MODE_OPTIONAL";
      case 1:
        break;
    } 
    return "SIGN_IN_MODE_REQUIRED";
  }
  
  public final ConnectionResult blockingConnect() {
    boolean bool2;
    null = Looper.myLooper();
    Looper looper = Looper.getMainLooper();
    boolean bool1 = true;
    if (null != looper) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkState(bool2, "blockingConnect must not be called on the UI thread");
    this.zzga.lock();
    try {
      if (this.zzde >= 0) {
        if (this.zzip != null) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        Preconditions.checkState(bool2, "Sign-in mode should have been set explicitly by auto-manage.");
      } else if (this.zzip == null) {
        this.zzip = Integer.valueOf(zza(this.zzil.values(), false));
      } else if (this.zzip.intValue() == 2) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        throw illegalStateException;
      } 
      zzg(this.zzip.intValue());
      this.zzie.enableCallbacks();
      return this.zzif.blockingConnect();
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit) {
    boolean bool;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "blockingConnect must not be called on the UI thread");
    Preconditions.checkNotNull(paramTimeUnit, "TimeUnit must not be null");
    this.zzga.lock();
    try {
      IllegalStateException illegalStateException;
      if (this.zzip == null) {
        this.zzip = Integer.valueOf(zza(this.zzil.values(), false));
      } else if (this.zzip.intValue() == 2) {
        illegalStateException = new IllegalStateException();
        this("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        throw illegalStateException;
      } 
      zzg(this.zzip.intValue());
      this.zzie.enableCallbacks();
      return this.zzif.blockingConnect(paramLong, (TimeUnit)illegalStateException);
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final PendingResult<Status> clearDefaultAccountAndReconnect() {
    boolean bool;
    Preconditions.checkState(super.isConnected(), "GoogleApiClient is not connected yet.");
    if (this.zzip.intValue() != 2) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
    StatusPendingResult statusPendingResult = new StatusPendingResult(this);
    if (this.zzil.containsKey(Common.CLIENT_KEY)) {
      zza(this, statusPendingResult, false);
      return statusPendingResult;
    } 
    AtomicReference<GoogleApiClient> atomicReference = new AtomicReference();
    zzax zzax = new zzax(this, atomicReference, statusPendingResult);
    zzay zzay = new zzay(this, statusPendingResult);
    GoogleApiClient googleApiClient = (new GoogleApiClient.Builder(this.mContext)).addApi(Common.API).addConnectionCallbacks(zzax).addOnConnectionFailedListener(zzay).setHandler(this.zzij).build();
    atomicReference.set(googleApiClient);
    googleApiClient.connect();
    return statusPendingResult;
  }
  
  public final void connect() {
    this.zzga.lock();
    try {
      int i = this.zzde;
      boolean bool = false;
      if (i >= 0) {
        if (this.zzip != null)
          bool = true; 
        Preconditions.checkState(bool, "Sign-in mode should have been set explicitly by auto-manage.");
      } else if (this.zzip == null) {
        this.zzip = Integer.valueOf(zza(this.zzil.values(), false));
      } else if (this.zzip.intValue() == 2) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        throw illegalStateException;
      } 
      super.connect(this.zzip.intValue());
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void connect(int paramInt) {
    this.zzga.lock();
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 3) {
      bool2 = bool1;
      if (paramInt != 1)
        if (paramInt == 2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
    } 
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this(33);
      stringBuilder.append("Illegal sign-in mode: ");
      stringBuilder.append(paramInt);
      Preconditions.checkArgument(bool2, stringBuilder.toString());
      zzg(paramInt);
      zzax();
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void disconnect() {
    this.zzga.lock();
    try {
      this.zzir.release();
      if (this.zzif != null)
        this.zzif.disconnect(); 
      this.zzin.release();
      for (BaseImplementation.ApiMethodImpl<?, ?> apiMethodImpl : this.zzgo) {
        apiMethodImpl.zza((zzcn)null);
        apiMethodImpl.cancel();
      } 
      this.zzgo.clear();
      zzbp zzbp1 = this.zzif;
      if (zzbp1 != null) {
        zzaz();
        this.zzie.disableCallbacks();
      } 
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.append(paramString).append("mContext=").println(this.mContext);
    paramPrintWriter.append(paramString).append("mResuming=").print(this.zzig);
    paramPrintWriter.append(" mWorkQueue.size()=").print(this.zzgo.size());
    zzck zzck1 = this.zzir;
    paramPrintWriter.append(" mUnconsumedApiCalls.size()=").println(zzck1.zzmo.size());
    if (this.zzif != null)
      this.zzif.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
  }
  
  public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T paramT) {
    String str;
    if (paramT.getClientKey() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "This task can not be enqueued (it's probably a Batch or malformed)");
    boolean bool = this.zzil.containsKey(paramT.getClientKey());
    if (paramT.getApi() != null) {
      str = paramT.getApi().getName();
    } else {
      str = "the API";
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 65);
    stringBuilder.append("GoogleApiClient is not configured to use ");
    stringBuilder.append(str);
    stringBuilder.append(" required for this call.");
    Preconditions.checkArgument(bool, stringBuilder.toString());
    this.zzga.lock();
    try {
      if (this.zzif == null) {
        this.zzgo.add((BaseImplementation.ApiMethodImpl<?, ?>)paramT);
        return paramT;
      } 
      paramT = this.zzif.enqueue(paramT);
      return paramT;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T paramT) {
    String str;
    if (paramT.getClientKey() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "This task can not be executed (it's probably a Batch or malformed)");
    boolean bool = this.zzil.containsKey(paramT.getClientKey());
    if (paramT.getApi() != null) {
      str = paramT.getApi().getName();
    } else {
      str = "the API";
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 65);
    stringBuilder.append("GoogleApiClient is not configured to use ");
    stringBuilder.append(str);
    stringBuilder.append(" required for this call.");
    Preconditions.checkArgument(bool, stringBuilder.toString());
    this.zzga.lock();
    try {
      IllegalStateException illegalStateException;
      if (this.zzif == null) {
        illegalStateException = new IllegalStateException();
        this("GoogleApiClient is not connected yet.");
        throw illegalStateException;
      } 
      if (this.zzig) {
        BaseImplementation.ApiMethodImpl<? extends Result> apiMethodImpl;
        this.zzgo.add(illegalStateException);
        while (true) {
          IllegalStateException illegalStateException1 = illegalStateException;
          if (!this.zzgo.isEmpty()) {
            apiMethodImpl = (BaseImplementation.ApiMethodImpl)this.zzgo.remove();
            this.zzir.zzb(apiMethodImpl);
            apiMethodImpl.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            continue;
          } 
          break;
        } 
        return (T)apiMethodImpl;
      } 
      str = this.zzif.execute(illegalStateException);
      return (T)str;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  @NonNull
  public final <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> paramAnyClientKey) {
    Api.Client client = this.zzil.get(paramAnyClientKey);
    Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
    return (C)client;
  }
  
  @NonNull
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    this.zzga.lock();
    try {
      IllegalStateException illegalStateException;
      ConnectionResult connectionResult;
      if (!super.isConnected() && !this.zzig) {
        illegalStateException = new IllegalStateException();
        this("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
        throw illegalStateException;
      } 
      if (this.zzil.containsKey(illegalStateException.getClientKey())) {
        String str;
        ConnectionResult connectionResult1 = this.zzif.getConnectionResult((Api<?>)illegalStateException);
        if (connectionResult1 == null) {
          ConnectionResult connectionResult2;
          if (this.zzig) {
            connectionResult2 = ConnectionResult.RESULT_SUCCESS;
            return connectionResult2;
          } 
          Log.w("GoogleApiClientImpl", zzbb());
          str = String.valueOf(connectionResult2.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map");
          Exception exception = new Exception();
          this();
          Log.wtf("GoogleApiClientImpl", str, exception);
          connectionResult = new ConnectionResult(8, null);
          return connectionResult;
        } 
        return (ConnectionResult)str;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this(String.valueOf(connectionResult.getName()).concat(" was never registered with GoogleApiClient"));
      throw illegalArgumentException;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  public final Looper getLooper() {
    return this.zzcn;
  }
  
  public final boolean hasApi(@NonNull Api<?> paramApi) {
    return this.zzil.containsKey(paramApi.getClientKey());
  }
  
  public final boolean hasConnectedApi(@NonNull Api<?> paramApi) {
    if (!super.isConnected())
      return false; 
    Api.Client client = this.zzil.get(paramApi.getClientKey());
    return (client != null && client.isConnected());
  }
  
  public final boolean isConnected() {
    return (this.zzif != null && this.zzif.isConnected());
  }
  
  public final boolean isConnecting() {
    return (this.zzif != null && this.zzif.isConnecting());
  }
  
  public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    return this.zzie.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    return this.zzie.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public final boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener) {
    return (this.zzif != null && this.zzif.maybeSignIn(paramSignInConnectionListener));
  }
  
  public final void maybeSignOut() {
    if (this.zzif != null)
      this.zzif.maybeSignOut(); 
  }
  
  public final void reconnect() {
    super.disconnect();
    super.connect();
  }
  
  public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    this.zzie.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this.zzie.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public final <L> ListenerHolder<L> registerListener(@NonNull L paramL) {
    this.zzga.lock();
    try {
      return this.zzin.zza(paramL, this.zzcn, "NO_TYPE");
    } finally {
      this.zzga.unlock();
    } 
  }
  
  public final void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity) {
    LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity)paramFragmentActivity);
    if (this.zzde >= 0) {
      zzi.zza(lifecycleActivity).zzc(this.zzde);
      return;
    } 
    throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
  }
  
  public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    this.zzie.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this.zzie.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public final void zza(zzch paramzzch) {
    this.zzga.lock();
    try {
      if (this.zziq == null) {
        HashSet<zzch> hashSet = new HashSet();
        this();
        this.zziq = hashSet;
      } 
      this.zziq.add(paramzzch);
      return;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  @GuardedBy("mLock")
  final boolean zzaz() {
    if (!this.zzig)
      return false; 
    this.zzig = false;
    this.zzij.removeMessages(2);
    this.zzij.removeMessages(1);
    if (this.zzik != null) {
      this.zzik.unregister();
      this.zzik = null;
    } 
    return true;
  }
  
  @GuardedBy("mLock")
  public final void zzb(int paramInt, boolean paramBoolean) {
    if (paramInt == 1 && !paramBoolean && !this.zzig) {
      this.zzig = true;
      if (this.zzik == null)
        this.zzik = this.zzdg.registerCallbackOnUpdate(this.mContext.getApplicationContext(), new zzbb(this)); 
      this.zzij.sendMessageDelayed(this.zzij.obtainMessage(1), this.zzih);
      this.zzij.sendMessageDelayed(this.zzij.obtainMessage(2), this.zzii);
    } 
    this.zzir.zzce();
    this.zzie.onUnintentionalDisconnection(paramInt);
    this.zzie.disableCallbacks();
    if (paramInt == 2)
      zzax(); 
  }
  
  @GuardedBy("mLock")
  public final void zzb(Bundle paramBundle) {
    while (!this.zzgo.isEmpty())
      super.execute(this.zzgo.remove()); 
    this.zzie.onConnectionSuccess(paramBundle);
  }
  
  public final void zzb(zzch paramzzch) {
    this.zzga.lock();
    try {
      Exception exception;
      String str;
      if (this.zziq == null) {
        str = "Attempted to remove pending transform when no transforms are registered.";
        exception = new Exception();
        this();
      } else if (!this.zziq.remove(exception)) {
        str = "Failed to remove pending transform - this may lead to memory leaks!";
        exception = new Exception();
      } else {
        if (!zzba())
          this.zzif.zzz(); 
        this.zzga.unlock();
      } 
      Log.wtf("GoogleApiClientImpl", str, exception);
    } finally {
      this.zzga.unlock();
    } 
  }
  
  final boolean zzba() {
    this.zzga.lock();
    try {
      Set<zzch> set = this.zziq;
      if (set == null)
        return false; 
      boolean bool = this.zziq.isEmpty();
      return bool ^ true;
    } finally {
      this.zzga.unlock();
    } 
  }
  
  final String zzbb() {
    StringWriter stringWriter = new StringWriter();
    super.dump("", null, new PrintWriter(stringWriter), null);
    return stringWriter.toString();
  }
  
  @GuardedBy("mLock")
  public final void zzc(ConnectionResult paramConnectionResult) {
    if (!this.zzdg.isPlayServicesPossiblyUpdating(this.mContext, paramConnectionResult.getErrorCode()))
      zzaz(); 
    if (!this.zzig) {
      this.zzie.onConnectionFailure(paramConnectionResult);
      this.zzie.disableCallbacks();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzav.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */