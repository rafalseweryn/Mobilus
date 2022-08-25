package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISignInCallbacks extends IInterface {
  void onAuthAccountComplete(ConnectionResult paramConnectionResult, AuthAccountResult paramAuthAccountResult) throws RemoteException;
  
  void onGetCurrentAccountComplete(Status paramStatus, GoogleSignInAccount paramGoogleSignInAccount) throws RemoteException;
  
  void onRecordConsentComplete(Status paramStatus) throws RemoteException;
  
  void onSaveAccountToSessionStoreComplete(Status paramStatus) throws RemoteException;
  
  void onSignInComplete(SignInResponse paramSignInResponse) throws RemoteException;
  
  public static abstract class Stub extends zzb implements ISignInCallbacks {
    public Stub() {
      super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }
    
    public static ISignInCallbacks asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
      return (iInterface instanceof ISignInCallbacks) ? (ISignInCallbacks)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 8:
          onSignInComplete((SignInResponse)zzc.zza(param1Parcel1, SignInResponse.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 7:
          onGetCurrentAccountComplete((Status)zzc.zza(param1Parcel1, Status.CREATOR), (GoogleSignInAccount)zzc.zza(param1Parcel1, GoogleSignInAccount.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 6:
          onRecordConsentComplete((Status)zzc.zza(param1Parcel1, Status.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 4:
          onSaveAccountToSessionStoreComplete((Status)zzc.zza(param1Parcel1, Status.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 3:
          break;
      } 
      onAuthAccountComplete((ConnectionResult)zzc.zza(param1Parcel1, ConnectionResult.CREATOR), (AuthAccountResult)zzc.zza(param1Parcel1, AuthAccountResult.CREATOR));
      param1Parcel2.writeNoException();
      return true;
    }
    
    public static class Proxy extends zza implements ISignInCallbacks {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.signin.internal.ISignInCallbacks");
      }
      
      public void onAuthAccountComplete(ConnectionResult param2ConnectionResult, AuthAccountResult param2AuthAccountResult) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2ConnectionResult);
        zzc.zza(parcel, (Parcelable)param2AuthAccountResult);
        transactAndReadExceptionReturnVoid(3, parcel);
      }
      
      public void onGetCurrentAccountComplete(Status param2Status, GoogleSignInAccount param2GoogleSignInAccount) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2Status);
        zzc.zza(parcel, (Parcelable)param2GoogleSignInAccount);
        transactAndReadExceptionReturnVoid(7, parcel);
      }
      
      public void onRecordConsentComplete(Status param2Status) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2Status);
        transactAndReadExceptionReturnVoid(6, parcel);
      }
      
      public void onSaveAccountToSessionStoreComplete(Status param2Status) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2Status);
        transactAndReadExceptionReturnVoid(4, parcel);
      }
      
      public void onSignInComplete(SignInResponse param2SignInResponse) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2SignInResponse);
        transactAndReadExceptionReturnVoid(8, parcel);
      }
    }
  }
  
  public static class Proxy extends zza implements ISignInCallbacks {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.signin.internal.ISignInCallbacks");
    }
    
    public void onAuthAccountComplete(ConnectionResult param1ConnectionResult, AuthAccountResult param1AuthAccountResult) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1ConnectionResult);
      zzc.zza(parcel, (Parcelable)param1AuthAccountResult);
      transactAndReadExceptionReturnVoid(3, parcel);
    }
    
    public void onGetCurrentAccountComplete(Status param1Status, GoogleSignInAccount param1GoogleSignInAccount) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1Status);
      zzc.zza(parcel, (Parcelable)param1GoogleSignInAccount);
      transactAndReadExceptionReturnVoid(7, parcel);
    }
    
    public void onRecordConsentComplete(Status param1Status) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1Status);
      transactAndReadExceptionReturnVoid(6, parcel);
    }
    
    public void onSaveAccountToSessionStoreComplete(Status param1Status) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1Status);
      transactAndReadExceptionReturnVoid(4, parcel);
    }
    
    public void onSignInComplete(SignInResponse param1SignInResponse) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1SignInResponse);
      transactAndReadExceptionReturnVoid(8, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\ISignInCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */