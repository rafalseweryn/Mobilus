package com.google.android.gms.signin;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.SignInClientImpl;

public final class SignIn {
  public static final Api<SignInOptions> API;
  
  public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> CLIENT_BUILDER;
  
  public static final Api.ClientKey<SignInClientImpl> CLIENT_KEY = new Api.ClientKey();
  
  public static final Api<SignInOptionsInternal> INTERNAL_API;
  
  public static final Api.ClientKey<SignInClientImpl> INTERNAL_CLIENT_KEY = new Api.ClientKey();
  
  public static final Scope SCOPE_EMAIL;
  
  public static final Scope SCOPE_PROFILE;
  
  private static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptionsInternal> zzacz;
  
  static {
    CLIENT_BUILDER = new zza();
    zzacz = new zzb();
    SCOPE_PROFILE = new Scope("profile");
    SCOPE_EMAIL = new Scope("email");
    API = new Api("SignIn.API", CLIENT_BUILDER, CLIENT_KEY);
    INTERNAL_API = new Api("SignIn.INTERNAL_API", zzacz, INTERNAL_CLIENT_KEY);
  }
  
  public static class SignInOptionsInternal implements Api.ApiOptions.HasOptions {
    private final Bundle zzada;
    
    private SignInOptionsInternal(Bundle param1Bundle) {
      if (param1Bundle == null)
        param1Bundle = new Bundle(); 
      this.zzada = param1Bundle;
    }
    
    public static SignInOptionsInternal create(Bundle param1Bundle) {
      return new SignInOptionsInternal(param1Bundle);
    }
    
    public Bundle getSignInOptionsBundle() {
      return this.zzada;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\SignIn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */