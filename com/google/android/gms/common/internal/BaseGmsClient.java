package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

public abstract class BaseGmsClient<T extends IInterface> {
  public static final int CONNECT_STATE_CONNECTED = 4;
  
  public static final int CONNECT_STATE_DISCONNECTED = 1;
  
  public static final int CONNECT_STATE_DISCONNECTING = 5;
  
  public static final int CONNECT_STATE_LOCAL_CONNECTING = 3;
  
  public static final int CONNECT_STATE_REMOTE_CONNECTING = 2;
  
  public static final String DEFAULT_ACCOUNT = "<<default account>>";
  
  public static final String FEATURE_GOOGLE_ME = "service_googleme";
  
  public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES;
  
  public static final String KEY_PENDING_INTENT = "pendingIntent";
  
  private static final Feature[] zzqz = new Feature[0];
  
  @VisibleForTesting
  protected ConnectionProgressReportCallbacks mConnectionProgressReportCallbacks;
  
  private final Context mContext;
  
  @VisibleForTesting
  protected AtomicInteger mDisconnectCount = new AtomicInteger(0);
  
  final Handler mHandler;
  
  private final Object mLock = new Object();
  
  private final Looper zzcn;
  
  private final GoogleApiAvailabilityLight zzgk;
  
  private int zzra;
  
  private long zzrb;
  
  private long zzrc;
  
  private int zzrd;
  
  private long zzre;
  
  @VisibleForTesting
  private GmsServiceEndpoint zzrf;
  
  private final GmsClientSupervisor zzrg;
  
  private final Object zzrh = new Object();
  
  @GuardedBy("mServiceBrokerLock")
  private IGmsServiceBroker zzri;
  
  @GuardedBy("mLock")
  private T zzrj;
  
  private final ArrayList<CallbackProxy<?>> zzrk = new ArrayList<>();
  
  @GuardedBy("mLock")
  private GmsServiceConnection zzrl;
  
  @GuardedBy("mLock")
  private int zzrm = 1;
  
  private final BaseConnectionCallbacks zzrn;
  
  private final BaseOnConnectionFailedListener zzro;
  
  private final int zzrp;
  
  private final String zzrq;
  
  private ConnectionResult zzrr = null;
  
  private boolean zzrs = false;
  
  private volatile ConnectionInfo zzrt = null;
  
  static {
    GOOGLE_PLUS_REQUIRED_FEATURES = new String[] { "service_esmobile", "service_googleme" };
  }
  
  @VisibleForTesting
  protected BaseGmsClient(Context paramContext, Handler paramHandler, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener) {
    this.mContext = Preconditions.<Context>checkNotNull(paramContext, "Context must not be null");
    this.mHandler = Preconditions.<Handler>checkNotNull(paramHandler, "Handler must not be null");
    this.zzcn = paramHandler.getLooper();
    this.zzrg = Preconditions.<GmsClientSupervisor>checkNotNull(paramGmsClientSupervisor, "Supervisor must not be null");
    this.zzgk = Preconditions.<GoogleApiAvailabilityLight>checkNotNull(paramGoogleApiAvailabilityLight, "API availability must not be null");
    this.zzrp = paramInt;
    this.zzrn = paramBaseConnectionCallbacks;
    this.zzro = paramBaseOnConnectionFailedListener;
    this.zzrq = null;
  }
  
  protected BaseGmsClient(Context paramContext, Looper paramLooper, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener, String paramString) {
    this(paramContext, paramLooper, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailabilityLight.getInstance(), paramInt, Preconditions.<BaseConnectionCallbacks>checkNotNull(paramBaseConnectionCallbacks), Preconditions.<BaseOnConnectionFailedListener>checkNotNull(paramBaseOnConnectionFailedListener), paramString);
  }
  
  @VisibleForTesting
  protected BaseGmsClient(Context paramContext, Looper paramLooper, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener, String paramString) {
    this.mContext = Preconditions.<Context>checkNotNull(paramContext, "Context must not be null");
    this.zzcn = Preconditions.<Looper>checkNotNull(paramLooper, "Looper must not be null");
    this.zzrg = Preconditions.<GmsClientSupervisor>checkNotNull(paramGmsClientSupervisor, "Supervisor must not be null");
    this.zzgk = Preconditions.<GoogleApiAvailabilityLight>checkNotNull(paramGoogleApiAvailabilityLight, "API availability must not be null");
    this.mHandler = new zzb(this, paramLooper);
    this.zzrp = paramInt;
    this.zzrn = paramBaseConnectionCallbacks;
    this.zzro = paramBaseOnConnectionFailedListener;
    this.zzrq = paramString;
  }
  
  private final void zza(int paramInt, T paramT) {
    int i;
    byte b;
    boolean bool;
    if (paramInt == 4) {
      i = 1;
    } else {
      i = 0;
    } 
    if (paramT != null) {
      b = 1;
    } else {
      b = 0;
    } 
    if (i == b) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    synchronized (this.mLock) {
      GmsServiceConnection gmsServiceConnection;
      GmsServiceEndpoint gmsServiceEndpoint;
      this.zzrm = paramInt;
      this.zzrj = paramT;
      onSetConnectState(paramInt, paramT);
      switch (paramInt) {
        case 4:
          onConnectedLocked(paramT);
          break;
        case 2:
        case 3:
          if (this.zzrl != null && this.zzrf != null) {
            String str1 = this.zzrf.zzcw();
            String str2 = this.zzrf.getPackageName();
            i = String.valueOf(str1).length();
            paramInt = String.valueOf(str2).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(i + 70 + paramInt);
            stringBuilder.append("Calling connect() while still connected, missing disconnect() for ");
            stringBuilder.append(str1);
            stringBuilder.append(" on ");
            stringBuilder.append(str2);
            Log.e("GmsClient", stringBuilder.toString());
            this.zzrg.unbindService(this.zzrf.zzcw(), this.zzrf.getPackageName(), this.zzrf.getBindFlags(), this.zzrl, getRealClientName());
            this.mDisconnectCount.incrementAndGet();
          } 
          gmsServiceConnection = new GmsServiceConnection();
          this(this, this.mDisconnectCount.get());
          this.zzrl = gmsServiceConnection;
          if (this.zzrm == 3 && getLocalStartServiceAction() != null) {
            gmsServiceEndpoint = new GmsServiceEndpoint();
            this(getContext().getPackageName(), getLocalStartServiceAction(), true, getServiceBindFlags());
          } else {
            gmsServiceEndpoint = new GmsServiceEndpoint(getStartServicePackage(), getStartServiceAction(), false, getServiceBindFlags());
          } 
          this.zzrf = gmsServiceEndpoint;
          if (!this.zzrg.bindService(this.zzrf.zzcw(), this.zzrf.getPackageName(), this.zzrf.getBindFlags(), this.zzrl, getRealClientName())) {
            String str2 = this.zzrf.zzcw();
            String str1 = this.zzrf.getPackageName();
            i = String.valueOf(str2).length();
            paramInt = String.valueOf(str1).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(i + 34 + paramInt);
            stringBuilder.append("unable to connect to service: ");
            stringBuilder.append(str2);
            stringBuilder.append(" on ");
            stringBuilder.append(str1);
            Log.e("GmsClient", stringBuilder.toString());
            onPostServiceBindingHandler(16, null, this.mDisconnectCount.get());
          } 
          break;
        case 1:
          if (this.zzrl != null) {
            this.zzrg.unbindService(getStartServiceAction(), getStartServicePackage(), getServiceBindFlags(), this.zzrl, getRealClientName());
            this.zzrl = null;
          } 
          break;
      } 
      return;
    } 
  }
  
  private final void zza(ConnectionInfo paramConnectionInfo) {
    this.zzrt = paramConnectionInfo;
  }
  
  private final boolean zza(int paramInt1, int paramInt2, T paramT) {
    synchronized (this.mLock) {
      if (this.zzrm != paramInt1)
        return false; 
      zza(paramInt2, paramT);
      return true;
    } 
  }
  
  private final boolean zzcq() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzrm == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  private final boolean zzcr() {
    if (this.zzrs)
      return false; 
    if (TextUtils.isEmpty(getServiceDescriptor()))
      return false; 
    if (TextUtils.isEmpty(getLocalStartServiceAction()))
      return false; 
    try {
      Class.forName(getServiceDescriptor());
      return true;
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  private final void zzj(int paramInt) {
    if (zzcq()) {
      paramInt = 5;
      this.zzrs = true;
    } else {
      paramInt = 4;
    } 
    this.mHandler.sendMessage(this.mHandler.obtainMessage(paramInt, this.mDisconnectCount.get(), 16));
  }
  
  public void checkAvailabilityAndConnect() {
    int i = this.zzgk.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
    if (i != 0) {
      zza(1, (T)null);
      triggerNotAvailable(new LegacyClientCallbackAdapter(this), i, null);
      return;
    } 
    connect(new LegacyClientCallbackAdapter(this));
  }
  
  protected final void checkConnected() {
    if (!isConnected())
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called."); 
  }
  
  public void connect(@NonNull ConnectionProgressReportCallbacks paramConnectionProgressReportCallbacks) {
    this.mConnectionProgressReportCallbacks = Preconditions.<ConnectionProgressReportCallbacks>checkNotNull(paramConnectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
    zza(2, (T)null);
  }
  
  @Nullable
  protected abstract T createServiceInterface(IBinder paramIBinder);
  
  public void disconnect() {
    this.mDisconnectCount.incrementAndGet();
    synchronized (this.zzrk) {
      int i = this.zzrk.size();
      for (byte b = 0; b < i; b++)
        ((CallbackProxy)this.zzrk.get(b)).removeListener(); 
      this.zzrk.clear();
      synchronized (this.zzrh) {
        this.zzri = null;
        zza(1, (T)null);
        return;
      } 
    } 
  }
  
  @Deprecated
  public final void doCallbackDEPRECATED(CallbackProxy<?> paramCallbackProxy) {
    synchronized (this.zzrk) {
      this.zzrk.add(paramCallbackProxy);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(2, this.mDisconnectCount.get(), -1, paramCallbackProxy));
      return;
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    synchronized (this.mLock) {
      int i = this.zzrm;
      T t = this.zzrj;
      synchronized (this.zzrh) {
        IGmsServiceBroker iGmsServiceBroker = this.zzri;
        paramPrintWriter.append(paramString).append("mConnectState=");
        switch (i) {
          default:
            null = "UNKNOWN";
            paramPrintWriter.print((String)null);
            break;
          case 5:
            null = "DISCONNECTING";
            paramPrintWriter.print((String)null);
            break;
          case 4:
            null = "CONNECTED";
            paramPrintWriter.print((String)null);
            break;
          case 3:
            null = "LOCAL_CONNECTING";
            paramPrintWriter.print((String)null);
            break;
          case 2:
            null = "REMOTE_CONNECTING";
            paramPrintWriter.print((String)null);
            break;
          case 1:
            null = "DISCONNECTED";
            paramPrintWriter.print((String)null);
            break;
        } 
        paramPrintWriter.append(" mService=");
        if (t == null) {
          paramPrintWriter.append("null");
        } else {
          paramPrintWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        } 
        paramPrintWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
          paramPrintWriter.println("null");
        } else {
          paramPrintWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        } 
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzrc > 0L) {
          PrintWriter printWriter = paramPrintWriter.append(paramString).append("lastConnectedTime=");
          long l = this.zzrc;
          null = simpleDateFormat.format(new Date(this.zzrc));
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(null).length() + 21);
          stringBuilder.append(l);
          stringBuilder.append(" ");
          stringBuilder.append((String)null);
          printWriter.println(stringBuilder.toString());
        } 
        if (this.zzrb > 0L) {
          paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          switch (this.zzra) {
            default:
              null = String.valueOf(this.zzra);
              paramPrintWriter.append((CharSequence)null);
              break;
            case 2:
              null = "CAUSE_NETWORK_LOST";
              paramPrintWriter.append((CharSequence)null);
              break;
            case 1:
              null = "CAUSE_SERVICE_DISCONNECTED";
              paramPrintWriter.append((CharSequence)null);
              break;
          } 
          null = paramPrintWriter.append(" lastSuspendedTime=");
          long l = this.zzrb;
          String str = simpleDateFormat.format(new Date(this.zzrb));
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 21);
          stringBuilder.append(l);
          stringBuilder.append(" ");
          stringBuilder.append(str);
          null.println(stringBuilder.toString());
        } 
        if (this.zzre > 0L) {
          paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzrd));
          PrintWriter printWriter = paramPrintWriter.append(" lastFailedTime=");
          long l = this.zzre;
          String str = simpleDateFormat.format(new Date(this.zzre));
          null = new StringBuilder(String.valueOf(str).length() + 21);
          null.append(l);
          null.append(" ");
          null.append(str);
          printWriter.println(null.toString());
        } 
        return;
      } 
    } 
  }
  
  public Account getAccount() {
    return null;
  }
  
  public final Account getAccountOrDefault() {
    return (getAccount() != null) ? getAccount() : new Account("<<default account>>", "com.google");
  }
  
  public Feature[] getApiFeatures() {
    return zzqz;
  }
  
  @Nullable
  public final Feature[] getAvailableFeatures() {
    ConnectionInfo connectionInfo = this.zzrt;
    return (connectionInfo == null) ? null : connectionInfo.getAvailableFeatures();
  }
  
  public Bundle getConnectionHint() {
    return null;
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  public String getEndpointPackageName() {
    if (isConnected() && this.zzrf != null)
      return this.zzrf.getPackageName(); 
    throw new RuntimeException("Failed to connect when checking package");
  }
  
  protected Bundle getGetServiceRequestExtraArgs() {
    return new Bundle();
  }
  
  @VisibleForTesting
  public final Handler getHandlerForTesting() {
    return this.mHandler;
  }
  
  @Nullable
  protected String getLocalStartServiceAction() {
    return null;
  }
  
  public final Looper getLooper() {
    return this.zzcn;
  }
  
  public int getMinApkVersion() {
    return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  }
  
  @Nullable
  protected final String getRealClientName() {
    return (this.zzrq == null) ? this.mContext.getClass().getName() : this.zzrq;
  }
  
  @WorkerThread
  public void getRemoteService(IAccountAccessor paramIAccountAccessor, Set<Scope> paramSet) {
    Bundle bundle = getGetServiceRequestExtraArgs();
    GetServiceRequest getServiceRequest = (new GetServiceRequest(this.zzrp)).setCallingPackage(this.mContext.getPackageName()).setExtraArgs(bundle);
    if (paramSet != null)
      getServiceRequest.setScopes(paramSet); 
    if (requiresSignIn()) {
      getServiceRequest.setClientRequestedAccount(getAccountOrDefault()).setAuthenticatedAccount(paramIAccountAccessor);
    } else if (requiresAccount()) {
      getServiceRequest.setClientRequestedAccount(getAccount());
    } 
    getServiceRequest.setClientRequiredFeatures(getRequiredFeatures());
    getServiceRequest.setClientApiFeatures(getApiFeatures());
    try {
      synchronized (this.zzrh) {
        if (this.zzri != null) {
          IGmsServiceBroker iGmsServiceBroker = this.zzri;
          GmsCallbacks gmsCallbacks = new GmsCallbacks();
          this(this, this.mDisconnectCount.get());
          iGmsServiceBroker.getService(gmsCallbacks, getServiceRequest);
        } else {
          Log.w("GmsClient", "mServiceBroker is null, client disconnected");
        } 
        return;
      } 
    } catch (DeadObjectException deadObjectException) {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", (Throwable)deadObjectException);
      triggerConnectionSuspended(1);
      return;
    } catch (SecurityException securityException) {
      throw securityException;
    } catch (RemoteException|RuntimeException remoteException) {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", (Throwable)remoteException);
      onPostInitHandler(8, null, null, this.mDisconnectCount.get());
      return;
    } 
  }
  
  public Feature[] getRequiredFeatures() {
    return zzqz;
  }
  
  protected Set<Scope> getScopes() {
    return Collections.EMPTY_SET;
  }
  
  public final T getService() throws DeadObjectException {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzrm == 5) {
        DeadObjectException deadObjectException = new DeadObjectException();
        this();
        throw deadObjectException;
      } 
      checkConnected();
      if (this.zzrj != null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "Client is connected but service is null");
      return this.zzrj;
    } 
  }
  
  protected int getServiceBindFlags() {
    return 129;
  }
  
  @Nullable
  public IBinder getServiceBrokerBinder() {
    synchronized (this.zzrh) {
      if (this.zzri == null)
        return null; 
      return this.zzri.asBinder();
    } 
  }
  
  @VisibleForTesting
  public final IGmsServiceBroker getServiceBrokerForTesting() {
    synchronized (this.zzrh) {
      return this.zzri;
    } 
  }
  
  @NonNull
  protected abstract String getServiceDescriptor();
  
  public Intent getSignInIntent() {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  @NonNull
  protected abstract String getStartServiceAction();
  
  protected String getStartServicePackage() {
    return "com.google.android.gms";
  }
  
  public boolean isConnected() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzrm == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public boolean isConnecting() {
    synchronized (this.mLock) {
      if (this.zzrm == 2 || this.zzrm == 3)
        return true; 
      return false;
    } 
  }
  
  @CallSuper
  protected void onConnectedLocked(@NonNull T paramT) {
    this.zzrc = System.currentTimeMillis();
  }
  
  @CallSuper
  protected void onConnectionFailed(ConnectionResult paramConnectionResult) {
    this.zzrd = paramConnectionResult.getErrorCode();
    this.zzre = System.currentTimeMillis();
  }
  
  @CallSuper
  protected void onConnectionSuspended(int paramInt) {
    this.zzra = paramInt;
    this.zzrb = System.currentTimeMillis();
  }
  
  protected void onPostInitHandler(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2) {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramInt2, -1, new PostInitCallback(this, paramInt1, paramIBinder, paramBundle)));
  }
  
  protected void onPostServiceBindingHandler(int paramInt1, @Nullable Bundle paramBundle, int paramInt2) {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(7, paramInt2, -1, new PostServiceBindingCallback(this, paramInt1, paramBundle)));
  }
  
  void onSetConnectState(int paramInt, T paramT) {}
  
  public void onUserSignOut(@NonNull SignOutCallbacks paramSignOutCallbacks) {
    paramSignOutCallbacks.onSignOutComplete();
  }
  
  public boolean providesSignIn() {
    return false;
  }
  
  public boolean requiresAccount() {
    return false;
  }
  
  public boolean requiresGooglePlayServices() {
    return true;
  }
  
  public boolean requiresSignIn() {
    return false;
  }
  
  @VisibleForTesting
  public void setConnectionInfoForTesting(ConnectionInfo paramConnectionInfo) {
    this.zzrt = paramConnectionInfo;
  }
  
  @VisibleForTesting
  public final void setServiceBrokerForTesting(IGmsServiceBroker paramIGmsServiceBroker) {
    synchronized (this.zzrh) {
      this.zzri = paramIGmsServiceBroker;
      return;
    } 
  }
  
  @VisibleForTesting
  public final void setServiceForTesting(T paramT) {
    boolean bool;
    if (paramT != null) {
      bool = true;
    } else {
      bool = true;
    } 
    zza(bool, paramT);
  }
  
  public void triggerConnectionSuspended(int paramInt) {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.mDisconnectCount.get(), paramInt));
  }
  
  @VisibleForTesting
  protected void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks paramConnectionProgressReportCallbacks, int paramInt, @Nullable PendingIntent paramPendingIntent) {
    this.mConnectionProgressReportCallbacks = Preconditions.<ConnectionProgressReportCallbacks>checkNotNull(paramConnectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.mDisconnectCount.get(), paramInt, paramPendingIntent));
  }
  
  public static interface BaseConnectionCallbacks {
    public static final int CAUSE_NETWORK_LOST = 2;
    
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    void onConnected(@Nullable Bundle param1Bundle);
    
    void onConnectionSuspended(int param1Int);
  }
  
  public static interface BaseOnConnectionFailedListener {
    void onConnectionFailed(@NonNull ConnectionResult param1ConnectionResult);
  }
  
  protected abstract class CallbackProxy<TListener> {
    private TListener zzli;
    
    private boolean zzrv;
    
    public CallbackProxy(BaseGmsClient this$0, TListener param1TListener) {
      this.zzli = param1TListener;
      this.zzrv = false;
    }
    
    public void deliverCallback() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield zzli : Ljava/lang/Object;
      //   6: astore_1
      //   7: aload_0
      //   8: getfield zzrv : Z
      //   11: ifeq -> 75
      //   14: aload_0
      //   15: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   18: astore_2
      //   19: aload_2
      //   20: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   23: invokevirtual length : ()I
      //   26: istore_3
      //   27: new java/lang/StringBuilder
      //   30: astore #4
      //   32: aload #4
      //   34: iload_3
      //   35: bipush #47
      //   37: iadd
      //   38: invokespecial <init> : (I)V
      //   41: aload #4
      //   43: ldc 'Callback proxy '
      //   45: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   48: pop
      //   49: aload #4
      //   51: aload_2
      //   52: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   55: pop
      //   56: aload #4
      //   58: ldc ' being reused. This is not safe.'
      //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   63: pop
      //   64: ldc 'GmsClient'
      //   66: aload #4
      //   68: invokevirtual toString : ()Ljava/lang/String;
      //   71: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
      //   74: pop
      //   75: aload_0
      //   76: monitorexit
      //   77: aload_1
      //   78: ifnull -> 96
      //   81: aload_0
      //   82: aload_1
      //   83: invokevirtual deliverCallback : (Ljava/lang/Object;)V
      //   86: goto -> 100
      //   89: astore_1
      //   90: aload_0
      //   91: invokevirtual onDeliverCallbackFailed : ()V
      //   94: aload_1
      //   95: athrow
      //   96: aload_0
      //   97: invokevirtual onDeliverCallbackFailed : ()V
      //   100: aload_0
      //   101: monitorenter
      //   102: aload_0
      //   103: iconst_1
      //   104: putfield zzrv : Z
      //   107: aload_0
      //   108: monitorexit
      //   109: aload_0
      //   110: invokevirtual unregister : ()V
      //   113: return
      //   114: astore_1
      //   115: aload_0
      //   116: monitorexit
      //   117: aload_1
      //   118: athrow
      //   119: astore_1
      //   120: aload_0
      //   121: monitorexit
      //   122: aload_1
      //   123: athrow
      // Exception table:
      //   from	to	target	type
      //   2	75	119	finally
      //   75	77	119	finally
      //   81	86	89	java/lang/RuntimeException
      //   102	109	114	finally
      //   115	117	114	finally
      //   120	122	119	finally
    }
    
    protected abstract void deliverCallback(TListener param1TListener);
    
    protected abstract void onDeliverCallbackFailed();
    
    public void removeListener() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: aconst_null
      //   4: putfield zzli : Ljava/lang/Object;
      //   7: aload_0
      //   8: monitorexit
      //   9: return
      //   10: astore_1
      //   11: aload_0
      //   12: monitorexit
      //   13: aload_1
      //   14: athrow
      // Exception table:
      //   from	to	target	type
      //   2	9	10	finally
      //   11	13	10	finally
    }
    
    public void unregister() {
      removeListener();
      synchronized (BaseGmsClient.zzf(this.zzru)) {
        BaseGmsClient.zzf(this.zzru).remove(this);
        return;
      } 
    }
  }
  
  public static interface ConnectionProgressReportCallbacks {
    void onReportServiceBinding(@NonNull ConnectionResult param1ConnectionResult);
  }
  
  @VisibleForTesting
  public static final class GmsCallbacks extends IGmsCallbacks.Stub {
    private BaseGmsClient zzrw;
    
    private final int zzrx;
    
    public GmsCallbacks(@NonNull BaseGmsClient param1BaseGmsClient, int param1Int) {
      this.zzrw = param1BaseGmsClient;
      this.zzrx = param1Int;
    }
    
    @BinderThread
    public final void onAccountValidationComplete(int param1Int, @Nullable Bundle param1Bundle) {
      Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }
    
    @BinderThread
    public final void onPostInitComplete(int param1Int, @NonNull IBinder param1IBinder, @Nullable Bundle param1Bundle) {
      Preconditions.checkNotNull(this.zzrw, "onPostInitComplete can be called only once per call to getRemoteService");
      this.zzrw.onPostInitHandler(param1Int, param1IBinder, param1Bundle, this.zzrx);
      this.zzrw = null;
    }
    
    @BinderThread
    public final void onPostInitCompleteWithConnectionInfo(int param1Int, @NonNull IBinder param1IBinder, @NonNull ConnectionInfo param1ConnectionInfo) {
      Preconditions.checkNotNull(this.zzrw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
      Preconditions.checkNotNull(param1ConnectionInfo);
      BaseGmsClient.zza(this.zzrw, param1ConnectionInfo);
      onPostInitComplete(param1Int, param1IBinder, param1ConnectionInfo.getResolutionBundle());
    }
  }
  
  @VisibleForTesting
  public final class GmsServiceConnection implements ServiceConnection {
    private final int zzrx;
    
    public GmsServiceConnection(BaseGmsClient this$0, int param1Int) {
      this.zzrx = param1Int;
    }
    
    public final void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
      if (param1IBinder == null) {
        BaseGmsClient.zza(this.zzru, 16);
        return;
      } 
      synchronized (BaseGmsClient.zza(this.zzru)) {
        BaseGmsClient.zza(this.zzru, IGmsServiceBroker.Stub.asInterface(param1IBinder));
        this.zzru.onPostServiceBindingHandler(0, null, this.zzrx);
        return;
      } 
    }
    
    public final void onServiceDisconnected(ComponentName param1ComponentName) {
      synchronized (BaseGmsClient.zza(this.zzru)) {
        BaseGmsClient.zza(this.zzru, (IGmsServiceBroker)null);
        this.zzru.mHandler.sendMessage(this.zzru.mHandler.obtainMessage(6, this.zzrx, 1));
        return;
      } 
    }
  }
  
  protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
    public LegacyClientCallbackAdapter(BaseGmsClient this$0) {}
    
    public void onReportServiceBinding(@NonNull ConnectionResult param1ConnectionResult) {
      if (param1ConnectionResult.isSuccess()) {
        this.zzru.getRemoteService(null, this.zzru.getScopes());
        return;
      } 
      if (BaseGmsClient.zzg(this.zzru) != null)
        BaseGmsClient.zzg(this.zzru).onConnectionFailed(param1ConnectionResult); 
    }
  }
  
  protected final class PostInitCallback extends zza {
    public final IBinder service;
    
    @BinderThread
    public PostInitCallback(BaseGmsClient this$0, int param1Int, IBinder param1IBinder, Bundle param1Bundle) {
      super(this$0, param1Int, param1Bundle);
      this.service = param1IBinder;
    }
    
    protected final void handleServiceFailure(ConnectionResult param1ConnectionResult) {
      if (BaseGmsClient.zzg(this.zzru) != null)
        BaseGmsClient.zzg(this.zzru).onConnectionFailed(param1ConnectionResult); 
      this.zzru.onConnectionFailed(param1ConnectionResult);
    }
    
    protected final boolean handleServiceSuccess() {
      try {
        String str = this.service.getInterfaceDescriptor();
        if (!this.zzru.getServiceDescriptor().equals(str)) {
          String str1 = this.zzru.getServiceDescriptor();
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 34 + String.valueOf(str).length());
          stringBuilder.append("service descriptor mismatch: ");
          stringBuilder.append(str1);
          stringBuilder.append(" vs. ");
          stringBuilder.append(str);
          Log.e("GmsClient", stringBuilder.toString());
          return false;
        } 
        str = this.zzru.createServiceInterface(this.service);
        if (str != null && (BaseGmsClient.zza(this.zzru, 2, 4, (IInterface)str) || BaseGmsClient.zza(this.zzru, 3, 4, (IInterface)str))) {
          BaseGmsClient.zza(this.zzru, (ConnectionResult)null);
          Bundle bundle = this.zzru.getConnectionHint();
          if (BaseGmsClient.zze(this.zzru) != null)
            BaseGmsClient.zze(this.zzru).onConnected(bundle); 
          return true;
        } 
        return false;
      } catch (RemoteException remoteException) {
        Log.w("GmsClient", "service probably died");
        return false;
      } 
    }
  }
  
  protected final class PostServiceBindingCallback extends zza {
    @BinderThread
    public PostServiceBindingCallback(BaseGmsClient this$0, @Nullable int param1Int, Bundle param1Bundle) {
      super(this$0, param1Int, param1Bundle);
    }
    
    protected final void handleServiceFailure(ConnectionResult param1ConnectionResult) {
      this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(param1ConnectionResult);
      this.zzru.onConnectionFailed(param1ConnectionResult);
    }
    
    protected final boolean handleServiceSuccess() {
      this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
      return true;
    }
  }
  
  public static interface SignOutCallbacks {
    void onSignOutComplete();
  }
  
  private abstract class zza extends CallbackProxy<Boolean> {
    public final Bundle resolution;
    
    public final int statusCode;
    
    @BinderThread
    protected zza(BaseGmsClient this$0, int param1Int, Bundle param1Bundle) {
      super(this$0, Boolean.valueOf(true));
      this.statusCode = param1Int;
      this.resolution = param1Bundle;
    }
    
    protected void deliverCallback(Boolean param1Boolean) {
      Boolean bool = null;
      if (param1Boolean == null) {
        BaseGmsClient.zza(this.zzru, 1, (IInterface)null);
        return;
      } 
      int i = this.statusCode;
      if (i != 0) {
        if (i != 10) {
          PendingIntent pendingIntent;
          BaseGmsClient.zza(this.zzru, 1, (IInterface)null);
          param1Boolean = bool;
          if (this.resolution != null)
            pendingIntent = (PendingIntent)this.resolution.getParcelable("pendingIntent"); 
          handleServiceFailure(new ConnectionResult(this.statusCode, pendingIntent));
          return;
        } 
        BaseGmsClient.zza(this.zzru, 1, (IInterface)null);
        throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
      } 
      if (!handleServiceSuccess()) {
        BaseGmsClient.zza(this.zzru, 1, (IInterface)null);
        handleServiceFailure(new ConnectionResult(8, null));
      } 
    }
    
    protected abstract void handleServiceFailure(ConnectionResult param1ConnectionResult);
    
    protected abstract boolean handleServiceSuccess();
    
    protected void onDeliverCallbackFailed() {}
  }
  
  final class zzb extends Handler {
    public zzb(BaseGmsClient this$0, Looper param1Looper) {
      super(param1Looper);
    }
    
    private static void zza(Message param1Message) {
      BaseGmsClient.CallbackProxy callbackProxy = (BaseGmsClient.CallbackProxy)param1Message.obj;
      callbackProxy.onDeliverCallbackFailed();
      callbackProxy.unregister();
    }
    
    private static boolean zzb(Message param1Message) {
      return (param1Message.what != 2 && param1Message.what != 1) ? ((param1Message.what == 7)) : true;
    }
    
    public final void handleMessage(Message param1Message) {
      ConnectionResult connectionResult;
      if (this.zzru.mDisconnectCount.get() != param1Message.arg1) {
        if (zzb(param1Message))
          zza(param1Message); 
        return;
      } 
      if ((param1Message.what == 1 || param1Message.what == 7 || param1Message.what == 4 || param1Message.what == 5) && !this.zzru.isConnecting()) {
        zza(param1Message);
        return;
      } 
      int i = param1Message.what;
      PendingIntent pendingIntent = null;
      if (i == 4) {
        BaseGmsClient.zza(this.zzru, new ConnectionResult(param1Message.arg2));
        if (BaseGmsClient.zzb(this.zzru) && !BaseGmsClient.zzc(this.zzru)) {
          BaseGmsClient.zza(this.zzru, 3, (IInterface)null);
          return;
        } 
        if (BaseGmsClient.zzd(this.zzru) != null) {
          connectionResult = BaseGmsClient.zzd(this.zzru);
        } else {
          connectionResult = new ConnectionResult(8);
        } 
        this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
        this.zzru.onConnectionFailed(connectionResult);
        return;
      } 
      if (((Message)connectionResult).what == 5) {
        if (BaseGmsClient.zzd(this.zzru) != null) {
          connectionResult = BaseGmsClient.zzd(this.zzru);
        } else {
          connectionResult = new ConnectionResult(8);
        } 
        this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
        this.zzru.onConnectionFailed(connectionResult);
        return;
      } 
      if (((Message)connectionResult).what == 3) {
        if (((Message)connectionResult).obj instanceof PendingIntent)
          pendingIntent = (PendingIntent)((Message)connectionResult).obj; 
        connectionResult = new ConnectionResult(((Message)connectionResult).arg2, pendingIntent);
        this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
        this.zzru.onConnectionFailed(connectionResult);
        return;
      } 
      if (((Message)connectionResult).what == 6) {
        BaseGmsClient.zza(this.zzru, 5, (IInterface)null);
        if (BaseGmsClient.zze(this.zzru) != null)
          BaseGmsClient.zze(this.zzru).onConnectionSuspended(((Message)connectionResult).arg2); 
        this.zzru.onConnectionSuspended(((Message)connectionResult).arg2);
        BaseGmsClient.zza(this.zzru, 5, 1, null);
        return;
      } 
      if (((Message)connectionResult).what == 2 && !this.zzru.isConnected()) {
        zza((Message)connectionResult);
        return;
      } 
      if (zzb((Message)connectionResult)) {
        ((BaseGmsClient.CallbackProxy)((Message)connectionResult).obj).deliverCallback();
        return;
      } 
      i = ((Message)connectionResult).what;
      StringBuilder stringBuilder = new StringBuilder(45);
      stringBuilder.append("Don't know how to handle message: ");
      stringBuilder.append(i);
      Log.wtf("GmsClient", stringBuilder.toString(), new Exception());
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\BaseGmsClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */