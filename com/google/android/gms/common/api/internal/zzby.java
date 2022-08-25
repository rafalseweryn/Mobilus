package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.BaseSignInCallbacks;
import com.google.android.gms.signin.internal.ISignInCallbacks;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.Set;

public final class zzby extends BaseSignInCallbacks implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  private static Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> zzlv = SignIn.CLIENT_BUILDER;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private Set<Scope> mScopes;
  
  private final Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> zzby;
  
  private ClientSettings zzgf;
  
  private SignInClient zzhn;
  
  private zzcb zzlw;
  
  @WorkerThread
  public zzby(Context paramContext, Handler paramHandler, @NonNull ClientSettings paramClientSettings) {
    this(paramContext, paramHandler, paramClientSettings, zzlv);
  }
  
  @WorkerThread
  public zzby(Context paramContext, Handler paramHandler, @NonNull ClientSettings paramClientSettings, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> paramAbstractClientBuilder) {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.zzgf = (ClientSettings)Preconditions.checkNotNull(paramClientSettings, "ClientSettings must not be null");
    this.mScopes = paramClientSettings.getRequiredScopes();
    this.zzby = paramAbstractClientBuilder;
  }
  
  @WorkerThread
  private final void zzb(SignInResponse paramSignInResponse) {
    ConnectionResult connectionResult1 = paramSignInResponse.getConnectionResult();
    ConnectionResult connectionResult2 = connectionResult1;
    if (connectionResult1.isSuccess()) {
      String str;
      ResolveAccountResponse resolveAccountResponse = paramSignInResponse.getResolveAccountResponse();
      connectionResult2 = resolveAccountResponse.getConnectionResult();
      if (!connectionResult2.isSuccess()) {
        str = String.valueOf(connectionResult2);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 48);
        stringBuilder.append("Sign-in succeeded with resolve account failure: ");
        stringBuilder.append(str);
        Log.wtf("SignInCoordinator", stringBuilder.toString(), new Exception());
      } else {
        this.zzlw.zza(str.getAccountAccessor(), this.mScopes);
        this.zzhn.disconnect();
      } 
    } 
    this.zzlw.zzg(connectionResult2);
    this.zzhn.disconnect();
  }
  
  @WorkerThread
  public final void onConnected(@Nullable Bundle paramBundle) {
    this.zzhn.signIn((ISignInCallbacks)this);
  }
  
  @WorkerThread
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult) {
    this.zzlw.zzg(paramConnectionResult);
  }
  
  @WorkerThread
  public final void onConnectionSuspended(int paramInt) {
    this.zzhn.disconnect();
  }
  
  @BinderThread
  public final void onSignInComplete(SignInResponse paramSignInResponse) {
    this.mHandler.post(new zzca(this, paramSignInResponse));
  }
  
  @WorkerThread
  public final void zza(zzcb paramzzcb) {
    if (this.zzhn != null)
      this.zzhn.disconnect(); 
    this.zzgf.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
    this.zzhn = (SignInClient)this.zzby.buildClient(this.mContext, this.mHandler.getLooper(), this.zzgf, this.zzgf.getSignInOptions(), this, this);
    this.zzlw = paramzzcb;
    if (this.mScopes == null || this.mScopes.isEmpty()) {
      this.mHandler.post(new zzbz(this));
      return;
    } 
    this.zzhn.connect();
  }
  
  public final SignInClient zzbt() {
    return this.zzhn;
  }
  
  public final void zzbz() {
    if (this.zzhn != null)
      this.zzhn.disconnect(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzby.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */