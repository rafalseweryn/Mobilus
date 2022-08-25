package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.signin.internal.ISignInCallbacks;

public interface SignInClient extends Api.Client {
  void clearAccountFromSessionStore();
  
  void connect();
  
  void saveDefaultAccount(IAccountAccessor paramIAccountAccessor, boolean paramBoolean);
  
  void signIn(ISignInCallbacks paramISignInCallbacks);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\SignInClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */