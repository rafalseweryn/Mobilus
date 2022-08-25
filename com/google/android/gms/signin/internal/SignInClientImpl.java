package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;

public class SignInClientImpl extends GmsClient<ISignInService> implements SignInClient {
  public static final String ACTION_START_SERVICE = "com.google.android.gms.signin.service.START";
  
  public static final String INTERNAL_ACTION_START_SERVICE = "com.google.android.gms.signin.service.INTERNAL_START";
  
  public static final String KEY_AUTH_API_SIGN_IN_MODULE_VERSION = "com.google.android.gms.signin.internal.authApiSignInModuleVersion";
  
  public static final String KEY_CLIENT_REQUESTED_ACCOUNT = "com.google.android.gms.signin.internal.clientRequestedAccount";
  
  public static final String KEY_FORCE_CODE_FOR_REFRESH_TOKEN = "com.google.android.gms.signin.internal.forceCodeForRefreshToken";
  
  public static final String KEY_HOSTED_DOMAIN = "com.google.android.gms.signin.internal.hostedDomain";
  
  public static final String KEY_ID_TOKEN_REQUESTED = "com.google.android.gms.signin.internal.idTokenRequested";
  
  @Deprecated
  public static final String KEY_OFFLINE_ACCESS_CALLBACKS = "com.google.android.gms.signin.internal.signInCallbacks";
  
  public static final String KEY_OFFLINE_ACCESS_REQUESTED = "com.google.android.gms.signin.internal.offlineAccessRequested";
  
  public static final String KEY_REAL_CLIENT_LIBRARY_VERSION = "com.google.android.gms.signin.internal.realClientLibraryVersion";
  
  public static final String KEY_REAL_CLIENT_PACKAGE_NAME = "com.google.android.gms.signin.internal.realClientPackageName";
  
  public static final String KEY_SERVER_CLIENT_ID = "com.google.android.gms.signin.internal.serverClientId";
  
  public static final String KEY_USE_PROMPT_MODE_FOR_AUTH_CODE = "com.google.android.gms.signin.internal.usePromptModeForAuthCode";
  
  public static final String KEY_WAIT_FOR_ACCESS_TOKEN_REFRESH = "com.google.android.gms.signin.internal.waitForAccessTokenRefresh";
  
  private final Bundle zzada;
  
  private final boolean zzads;
  
  private final ClientSettings zzgf;
  
  private Integer zzsc;
  
  public SignInClientImpl(Context paramContext, Looper paramLooper, boolean paramBoolean, ClientSettings paramClientSettings, Bundle paramBundle, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, 44, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzads = paramBoolean;
    this.zzgf = paramClientSettings;
    this.zzada = paramBundle;
    this.zzsc = paramClientSettings.getClientSessionId();
  }
  
  public SignInClientImpl(Context paramContext, Looper paramLooper, boolean paramBoolean, ClientSettings paramClientSettings, SignInOptions paramSignInOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this(paramContext, paramLooper, paramBoolean, paramClientSettings, createBundleFromClientSettings(paramClientSettings), paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public static Bundle createBundleFromClientSettings(ClientSettings paramClientSettings) {
    SignInOptions signInOptions = paramClientSettings.getSignInOptions();
    Integer integer = paramClientSettings.getClientSessionId();
    Bundle bundle = new Bundle();
    bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable)paramClientSettings.getAccount());
    if (integer != null)
      bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", integer.intValue()); 
    if (signInOptions != null) {
      bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", signInOptions.isOfflineAccessRequested());
      bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", signInOptions.isIdTokenRequested());
      bundle.putString("com.google.android.gms.signin.internal.serverClientId", signInOptions.getServerClientId());
      bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
      bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", signInOptions.isForceCodeForRefreshToken());
      bundle.putString("com.google.android.gms.signin.internal.hostedDomain", signInOptions.getHostedDomain());
      bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", signInOptions.waitForAccessTokenRefresh());
      if (signInOptions.getAuthApiSignInModuleVersion() != null)
        bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", signInOptions.getAuthApiSignInModuleVersion().longValue()); 
      if (signInOptions.getRealClientLibraryVersion() != null)
        bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", signInOptions.getRealClientLibraryVersion().longValue()); 
    } 
    return bundle;
  }
  
  public void clearAccountFromSessionStore() {
    try {
      ((ISignInService)getService()).clearAccountFromSessionStore(this.zzsc.intValue());
      return;
    } catch (RemoteException remoteException) {
      Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
      return;
    } 
  }
  
  public void connect() {
    connect((BaseGmsClient.ConnectionProgressReportCallbacks)new BaseGmsClient.LegacyClientCallbackAdapter((BaseGmsClient)this));
  }
  
  protected ISignInService createServiceInterface(IBinder paramIBinder) {
    return ISignInService.Stub.asInterface(paramIBinder);
  }
  
  protected Bundle getGetServiceRequestExtraArgs() {
    String str = this.zzgf.getRealClientPackageName();
    if (!getContext().getPackageName().equals(str))
      this.zzada.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzgf.getRealClientPackageName()); 
    return this.zzada;
  }
  
  public int getMinApkVersion() {
    return 12451000;
  }
  
  protected String getServiceDescriptor() {
    return "com.google.android.gms.signin.internal.ISignInService";
  }
  
  protected String getStartServiceAction() {
    return "com.google.android.gms.signin.service.START";
  }
  
  public boolean requiresSignIn() {
    return this.zzads;
  }
  
  public void saveDefaultAccount(IAccountAccessor paramIAccountAccessor, boolean paramBoolean) {
    try {
      ((ISignInService)getService()).saveDefaultAccountToSharedPref(paramIAccountAccessor, this.zzsc.intValue(), paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
      return;
    } 
  }
  
  public void signIn(ISignInCallbacks paramISignInCallbacks) {
    Preconditions.checkNotNull(paramISignInCallbacks, "Expecting a valid ISignInCallbacks");
    try {
      Account account = this.zzgf.getAccountOrDefault();
      GoogleSignInAccount googleSignInAccount = null;
      if ("<<default account>>".equals(account.name))
        googleSignInAccount = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount(); 
      ResolveAccountRequest resolveAccountRequest = new ResolveAccountRequest();
      this(account, this.zzsc.intValue(), googleSignInAccount);
      ISignInService iSignInService = (ISignInService)getService();
      SignInRequest signInRequest = new SignInRequest();
      this(resolveAccountRequest);
      iSignInService.signIn(signInRequest, paramISignInCallbacks);
      return;
    } catch (RemoteException remoteException) {
      Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
      try {
        SignInResponse signInResponse = new SignInResponse();
        this(8);
        paramISignInCallbacks.onSignInComplete(signInResponse);
        return;
      } catch (RemoteException remoteException1) {
        Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", (Throwable)remoteException);
        return;
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\SignInClientImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */