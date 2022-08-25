package com.google.android.gms.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public class BaseSignInCallbacks extends ISignInCallbacks.Stub {
  public void onAuthAccountComplete(ConnectionResult paramConnectionResult, AuthAccountResult paramAuthAccountResult) throws RemoteException {}
  
  public void onGetCurrentAccountComplete(Status paramStatus, GoogleSignInAccount paramGoogleSignInAccount) throws RemoteException {}
  
  public void onRecordConsentComplete(Status paramStatus) throws RemoteException {}
  
  public void onSaveAccountToSessionStoreComplete(Status paramStatus) throws RemoteException {}
  
  public void onSignInComplete(SignInResponse paramSignInResponse) throws RemoteException {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\BaseSignInCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */