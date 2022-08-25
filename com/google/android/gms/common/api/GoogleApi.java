package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zzad;
import com.google.android.gms.common.api.internal.zzbo;
import com.google.android.gms.common.api.internal.zzby;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class GoogleApi<O extends Api.ApiOptions> {
  private final Api<O> mApi;
  
  private final Context mContext;
  
  private final int mId;
  
  private final O zzcl;
  
  private final zzh<O> zzcm;
  
  private final Looper zzcn;
  
  private final GoogleApiClient zzco;
  
  private final StatusExceptionMapper zzcp;
  
  protected final GoogleApiManager zzcq;
  
  @MainThread
  @KeepForSdk
  public GoogleApi(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, Settings paramSettings) {
    Preconditions.checkNotNull(paramActivity, "Null activity is not permitted.");
    Preconditions.checkNotNull(paramApi, "Api must not be null.");
    Preconditions.checkNotNull(paramSettings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    this.mContext = paramActivity.getApplicationContext();
    this.mApi = paramApi;
    this.zzcl = paramO;
    this.zzcn = paramSettings.zzcs;
    this.zzcm = zzh.zza(this.mApi, (Api.ApiOptions)this.zzcl);
    this.zzco = (GoogleApiClient)new zzbo(this);
    this.zzcq = GoogleApiManager.zzb(this.mContext);
    this.mId = this.zzcq.zzbg();
    this.zzcp = paramSettings.zzcr;
    zzad.zza(paramActivity, this.zzcq, this.zzcm);
    this.zzcq.zza(this);
  }
  
  @Deprecated
  @KeepForSdk
  public GoogleApi(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, StatusExceptionMapper paramStatusExceptionMapper) {
    this(paramActivity, paramApi, paramO, (new Settings.Builder()).setMapper(paramStatusExceptionMapper).setLooper(paramActivity.getMainLooper()).build());
  }
  
  @KeepForSdk
  protected GoogleApi(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper) {
    Preconditions.checkNotNull(paramContext, "Null context is not permitted.");
    Preconditions.checkNotNull(paramApi, "Api must not be null.");
    Preconditions.checkNotNull(paramLooper, "Looper must not be null.");
    this.mContext = paramContext.getApplicationContext();
    this.mApi = paramApi;
    this.zzcl = null;
    this.zzcn = paramLooper;
    this.zzcm = zzh.zza(paramApi);
    this.zzco = (GoogleApiClient)new zzbo(this);
    this.zzcq = GoogleApiManager.zzb(this.mContext);
    this.mId = this.zzcq.zzbg();
    this.zzcp = (StatusExceptionMapper)new ApiExceptionMapper();
  }
  
  @Deprecated
  @KeepForSdk
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, Looper paramLooper, StatusExceptionMapper paramStatusExceptionMapper) {
    this(paramContext, paramApi, paramO, (new Settings.Builder()).setLooper(paramLooper).setMapper(paramStatusExceptionMapper).build());
  }
  
  @KeepForSdk
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, Settings paramSettings) {
    Preconditions.checkNotNull(paramContext, "Null context is not permitted.");
    Preconditions.checkNotNull(paramApi, "Api must not be null.");
    Preconditions.checkNotNull(paramSettings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    this.mContext = paramContext.getApplicationContext();
    this.mApi = paramApi;
    this.zzcl = paramO;
    this.zzcn = paramSettings.zzcs;
    this.zzcm = zzh.zza(this.mApi, (Api.ApiOptions)this.zzcl);
    this.zzco = (GoogleApiClient)new zzbo(this);
    this.zzcq = GoogleApiManager.zzb(this.mContext);
    this.mId = this.zzcq.zzbg();
    this.zzcp = paramSettings.zzcr;
    this.zzcq.zza(this);
  }
  
  @Deprecated
  @KeepForSdk
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, StatusExceptionMapper paramStatusExceptionMapper) {
    this(paramContext, paramApi, paramO, (new Settings.Builder()).setMapper(paramStatusExceptionMapper).build());
  }
  
  private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zza(int paramInt, @NonNull T paramT) {
    paramT.zzx();
    this.zzcq.zza(this, paramInt, (BaseImplementation.ApiMethodImpl)paramT);
    return paramT;
  }
  
  private final <TResult, A extends Api.AnyClient> Task<TResult> zza(int paramInt, @NonNull TaskApiCall<A, TResult> paramTaskApiCall) {
    TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
    this.zzcq.zza(this, paramInt, paramTaskApiCall, taskCompletionSource, this.zzcp);
    return taskCompletionSource.getTask();
  }
  
  @KeepForSdk
  public GoogleApiClient asGoogleApiClient() {
    return this.zzco;
  }
  
  @KeepForSdk
  protected ClientSettings.Builder createClientSettingsBuilder() {
    // Byte code:
    //   0: new com/google/android/gms/common/internal/ClientSettings$Builder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield zzcl : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   12: instanceof com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   15: ifeq -> 43
    //   18: aload_0
    //   19: getfield zzcl : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   22: checkcast com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   25: invokeinterface getGoogleSignInAccount : ()Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;
    //   30: astore_2
    //   31: aload_2
    //   32: ifnull -> 43
    //   35: aload_2
    //   36: invokevirtual getAccount : ()Landroid/accounts/Account;
    //   39: astore_2
    //   40: goto -> 71
    //   43: aload_0
    //   44: getfield zzcl : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   47: instanceof com/google/android/gms/common/api/Api$ApiOptions$HasAccountOptions
    //   50: ifeq -> 69
    //   53: aload_0
    //   54: getfield zzcl : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   57: checkcast com/google/android/gms/common/api/Api$ApiOptions$HasAccountOptions
    //   60: invokeinterface getAccount : ()Landroid/accounts/Account;
    //   65: astore_2
    //   66: goto -> 71
    //   69: aconst_null
    //   70: astore_2
    //   71: aload_1
    //   72: aload_2
    //   73: invokevirtual setAccount : (Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   76: astore_1
    //   77: aload_0
    //   78: getfield zzcl : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   81: instanceof com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   84: ifeq -> 112
    //   87: aload_0
    //   88: getfield zzcl : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   91: checkcast com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   94: invokeinterface getGoogleSignInAccount : ()Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;
    //   99: astore_2
    //   100: aload_2
    //   101: ifnull -> 112
    //   104: aload_2
    //   105: invokevirtual getRequestedScopes : ()Ljava/util/Set;
    //   108: astore_2
    //   109: goto -> 116
    //   112: invokestatic emptySet : ()Ljava/util/Set;
    //   115: astore_2
    //   116: aload_1
    //   117: aload_2
    //   118: invokevirtual addAllRequiredScopes : (Ljava/util/Collection;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   121: aload_0
    //   122: getfield mContext : Landroid/content/Context;
    //   125: invokevirtual getClass : ()Ljava/lang/Class;
    //   128: invokevirtual getName : ()Ljava/lang/String;
    //   131: invokevirtual setRealClientClassName : (Ljava/lang/String;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   134: aload_0
    //   135: getfield mContext : Landroid/content/Context;
    //   138: invokevirtual getPackageName : ()Ljava/lang/String;
    //   141: invokevirtual setRealClientPackageName : (Ljava/lang/String;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   144: areturn
  }
  
  @KeepForSdk
  protected Task<Boolean> disconnectService() {
    return this.zzcq.zzc(this);
  }
  
  @KeepForSdk
  public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(@NonNull T paramT) {
    return zza(2, paramT);
  }
  
  @KeepForSdk
  public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(TaskApiCall<A, TResult> paramTaskApiCall) {
    return zza(2, paramTaskApiCall);
  }
  
  @KeepForSdk
  public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(@NonNull T paramT) {
    return zza(0, paramT);
  }
  
  @KeepForSdk
  public <TResult, A extends Api.AnyClient> Task<TResult> doRead(TaskApiCall<A, TResult> paramTaskApiCall) {
    return zza(0, paramTaskApiCall);
  }
  
  @KeepForSdk
  public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(@NonNull T paramT, U paramU) {
    Preconditions.checkNotNull(paramT);
    Preconditions.checkNotNull(paramU);
    Preconditions.checkNotNull(paramT.getListenerKey(), "Listener has already been released.");
    Preconditions.checkNotNull(paramU.getListenerKey(), "Listener has already been released.");
    Preconditions.checkArgument(paramT.getListenerKey().equals(paramU.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
    return this.zzcq.zza(this, (RegisterListenerMethod)paramT, (UnregisterListenerMethod)paramU);
  }
  
  @KeepForSdk
  public Task<Boolean> doUnregisterEventListener(@NonNull ListenerHolder.ListenerKey<?> paramListenerKey) {
    Preconditions.checkNotNull(paramListenerKey, "Listener key cannot be null.");
    return this.zzcq.zza(this, paramListenerKey);
  }
  
  @KeepForSdk
  public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(@NonNull T paramT) {
    return zza(1, paramT);
  }
  
  @KeepForSdk
  public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(TaskApiCall<A, TResult> paramTaskApiCall) {
    return zza(1, paramTaskApiCall);
  }
  
  public final Api<O> getApi() {
    return this.mApi;
  }
  
  @KeepForSdk
  public O getApiOptions() {
    return this.zzcl;
  }
  
  @KeepForSdk
  public Context getApplicationContext() {
    return this.mContext;
  }
  
  public final int getInstanceId() {
    return this.mId;
  }
  
  @KeepForSdk
  public Looper getLooper() {
    return this.zzcn;
  }
  
  @KeepForSdk
  public <L> ListenerHolder<L> registerListener(@NonNull L paramL, String paramString) {
    return ListenerHolders.createListenerHolder(paramL, this.zzcn, paramString);
  }
  
  @WorkerThread
  public Api.Client zza(Looper paramLooper, GoogleApiManager.zza<O> paramzza) {
    ClientSettings clientSettings = createClientSettingsBuilder().build();
    return this.mApi.zzk().buildClient(this.mContext, paramLooper, clientSettings, this.zzcl, (GoogleApiClient.ConnectionCallbacks)paramzza, (GoogleApiClient.OnConnectionFailedListener)paramzza);
  }
  
  public zzby zza(Context paramContext, Handler paramHandler) {
    return new zzby(paramContext, paramHandler, createClientSettingsBuilder().build());
  }
  
  public final zzh<O> zzm() {
    return this.zzcm;
  }
  
  @KeepForSdk
  public static class Settings {
    @KeepForSdk
    public static final Settings DEFAULT_SETTINGS = (new Builder()).build();
    
    public final StatusExceptionMapper zzcr;
    
    public final Looper zzcs;
    
    @KeepForSdk
    private Settings(StatusExceptionMapper param1StatusExceptionMapper, Account param1Account, Looper param1Looper) {
      this.zzcr = param1StatusExceptionMapper;
      this.zzcs = param1Looper;
    }
    
    @KeepForSdk
    public static class Builder {
      private Looper zzcn;
      
      private StatusExceptionMapper zzcp;
      
      @KeepForSdk
      public GoogleApi.Settings build() {
        if (this.zzcp == null)
          this.zzcp = (StatusExceptionMapper)new ApiExceptionMapper(); 
        if (this.zzcn == null)
          this.zzcn = Looper.getMainLooper(); 
        return new GoogleApi.Settings(this.zzcp, null, this.zzcn, null);
      }
      
      @KeepForSdk
      public Builder setLooper(Looper param2Looper) {
        Preconditions.checkNotNull(param2Looper, "Looper must not be null.");
        this.zzcn = param2Looper;
        return this;
      }
      
      @KeepForSdk
      public Builder setMapper(StatusExceptionMapper param2StatusExceptionMapper) {
        Preconditions.checkNotNull(param2StatusExceptionMapper, "StatusExceptionMapper must not be null.");
        this.zzcp = param2StatusExceptionMapper;
        return this;
      }
    }
  }
  
  @KeepForSdk
  public static class Builder {
    private Looper zzcn;
    
    private StatusExceptionMapper zzcp;
    
    @KeepForSdk
    public GoogleApi.Settings build() {
      if (this.zzcp == null)
        this.zzcp = (StatusExceptionMapper)new ApiExceptionMapper(); 
      if (this.zzcn == null)
        this.zzcn = Looper.getMainLooper(); 
      return new GoogleApi.Settings(this.zzcp, null, this.zzcn, null);
    }
    
    @KeepForSdk
    public Builder setLooper(Looper param1Looper) {
      Preconditions.checkNotNull(param1Looper, "Looper must not be null.");
      this.zzcn = param1Looper;
      return this;
    }
    
    @KeepForSdk
    public Builder setMapper(StatusExceptionMapper param1StatusExceptionMapper) {
      Preconditions.checkNotNull(param1StatusExceptionMapper, "StatusExceptionMapper must not be null.");
      this.zzcp = param1StatusExceptionMapper;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\GoogleApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */