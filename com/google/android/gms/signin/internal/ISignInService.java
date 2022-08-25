package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.IResolveAccountCallbacks;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISignInService extends IInterface {
  void authAccount(AuthAccountRequest paramAuthAccountRequest, ISignInCallbacks paramISignInCallbacks) throws RemoteException;
  
  void clearAccountFromSessionStore(int paramInt) throws RemoteException;
  
  void getCurrentAccount(ISignInCallbacks paramISignInCallbacks) throws RemoteException;
  
  void onCheckServerAuthorization(CheckServerAuthResult paramCheckServerAuthResult) throws RemoteException;
  
  void onUploadServerAuthCode(boolean paramBoolean) throws RemoteException;
  
  void recordConsent(RecordConsentRequest paramRecordConsentRequest, ISignInCallbacks paramISignInCallbacks) throws RemoteException;
  
  void resolveAccount(ResolveAccountRequest paramResolveAccountRequest, IResolveAccountCallbacks paramIResolveAccountCallbacks) throws RemoteException;
  
  void saveAccountToSessionStore(int paramInt, Account paramAccount, ISignInCallbacks paramISignInCallbacks) throws RemoteException;
  
  void saveDefaultAccountToSharedPref(IAccountAccessor paramIAccountAccessor, int paramInt, boolean paramBoolean) throws RemoteException;
  
  void setGamesHasBeenGreeted(boolean paramBoolean) throws RemoteException;
  
  void signIn(SignInRequest paramSignInRequest, ISignInCallbacks paramISignInCallbacks) throws RemoteException;
  
  public static abstract class Stub extends zzb implements ISignInService {
    public Stub() {
      super("com.google.android.gms.signin.internal.ISignInService");
    }
    
    public static ISignInService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
      return (iInterface instanceof ISignInService) ? (ISignInService)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 13:
          setGamesHasBeenGreeted(zzc.zza(param1Parcel1));
          param1Parcel2.writeNoException();
          return true;
        case 12:
          signIn((SignInRequest)zzc.zza(param1Parcel1, SignInRequest.CREATOR), ISignInCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 11:
          getCurrentAccount(ISignInCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 10:
          recordConsent((RecordConsentRequest)zzc.zza(param1Parcel1, RecordConsentRequest.CREATOR), ISignInCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 9:
          saveDefaultAccountToSharedPref(IAccountAccessor.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt(), zzc.zza(param1Parcel1));
          param1Parcel2.writeNoException();
          return true;
        case 8:
          saveAccountToSessionStore(param1Parcel1.readInt(), (Account)zzc.zza(param1Parcel1, Account.CREATOR), ISignInCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 7:
          clearAccountFromSessionStore(param1Parcel1.readInt());
          param1Parcel2.writeNoException();
          return true;
        case 5:
          resolveAccount((ResolveAccountRequest)zzc.zza(param1Parcel1, ResolveAccountRequest.CREATOR), IResolveAccountCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 4:
          onUploadServerAuthCode(zzc.zza(param1Parcel1));
          param1Parcel2.writeNoException();
          return true;
        case 3:
          onCheckServerAuthorization((CheckServerAuthResult)zzc.zza(param1Parcel1, CheckServerAuthResult.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 2:
          break;
      } 
      authAccount((AuthAccountRequest)zzc.zza(param1Parcel1, AuthAccountRequest.CREATOR), ISignInCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()));
      param1Parcel2.writeNoException();
      return true;
    }
    
    public static class Proxy extends zza implements ISignInService {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.signin.internal.ISignInService");
      }
      
      public void authAccount(AuthAccountRequest param2AuthAccountRequest, ISignInCallbacks param2ISignInCallbacks) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2AuthAccountRequest);
        zzc.zza(parcel, param2ISignInCallbacks);
        transactAndReadExceptionReturnVoid(2, parcel);
      }
      
      public void clearAccountFromSessionStore(int param2Int) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeInt(param2Int);
        transactAndReadExceptionReturnVoid(7, parcel);
      }
      
      public void getCurrentAccount(ISignInCallbacks param2ISignInCallbacks) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2ISignInCallbacks);
        transactAndReadExceptionReturnVoid(11, parcel);
      }
      
      public void onCheckServerAuthorization(CheckServerAuthResult param2CheckServerAuthResult) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2CheckServerAuthResult);
        transactAndReadExceptionReturnVoid(3, parcel);
      }
      
      public void onUploadServerAuthCode(boolean param2Boolean) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2Boolean);
        transactAndReadExceptionReturnVoid(4, parcel);
      }
      
      public void recordConsent(RecordConsentRequest param2RecordConsentRequest, ISignInCallbacks param2ISignInCallbacks) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2RecordConsentRequest);
        zzc.zza(parcel, param2ISignInCallbacks);
        transactAndReadExceptionReturnVoid(10, parcel);
      }
      
      public void resolveAccount(ResolveAccountRequest param2ResolveAccountRequest, IResolveAccountCallbacks param2IResolveAccountCallbacks) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2ResolveAccountRequest);
        zzc.zza(parcel, (IInterface)param2IResolveAccountCallbacks);
        transactAndReadExceptionReturnVoid(5, parcel);
      }
      
      public void saveAccountToSessionStore(int param2Int, Account param2Account, ISignInCallbacks param2ISignInCallbacks) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeInt(param2Int);
        zzc.zza(parcel, (Parcelable)param2Account);
        zzc.zza(parcel, param2ISignInCallbacks);
        transactAndReadExceptionReturnVoid(8, parcel);
      }
      
      public void saveDefaultAccountToSharedPref(IAccountAccessor param2IAccountAccessor, int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (IInterface)param2IAccountAccessor);
        parcel.writeInt(param2Int);
        zzc.zza(parcel, param2Boolean);
        transactAndReadExceptionReturnVoid(9, parcel);
      }
      
      public void setGamesHasBeenGreeted(boolean param2Boolean) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2Boolean);
        transactAndReadExceptionReturnVoid(13, parcel);
      }
      
      public void signIn(SignInRequest param2SignInRequest, ISignInCallbacks param2ISignInCallbacks) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2SignInRequest);
        zzc.zza(parcel, param2ISignInCallbacks);
        transactAndReadExceptionReturnVoid(12, parcel);
      }
    }
  }
  
  public static class Proxy extends zza implements ISignInService {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.signin.internal.ISignInService");
    }
    
    public void authAccount(AuthAccountRequest param1AuthAccountRequest, ISignInCallbacks param1ISignInCallbacks) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1AuthAccountRequest);
      zzc.zza(parcel, param1ISignInCallbacks);
      transactAndReadExceptionReturnVoid(2, parcel);
    }
    
    public void clearAccountFromSessionStore(int param1Int) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeInt(param1Int);
      transactAndReadExceptionReturnVoid(7, parcel);
    }
    
    public void getCurrentAccount(ISignInCallbacks param1ISignInCallbacks) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1ISignInCallbacks);
      transactAndReadExceptionReturnVoid(11, parcel);
    }
    
    public void onCheckServerAuthorization(CheckServerAuthResult param1CheckServerAuthResult) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1CheckServerAuthResult);
      transactAndReadExceptionReturnVoid(3, parcel);
    }
    
    public void onUploadServerAuthCode(boolean param1Boolean) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1Boolean);
      transactAndReadExceptionReturnVoid(4, parcel);
    }
    
    public void recordConsent(RecordConsentRequest param1RecordConsentRequest, ISignInCallbacks param1ISignInCallbacks) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1RecordConsentRequest);
      zzc.zza(parcel, param1ISignInCallbacks);
      transactAndReadExceptionReturnVoid(10, parcel);
    }
    
    public void resolveAccount(ResolveAccountRequest param1ResolveAccountRequest, IResolveAccountCallbacks param1IResolveAccountCallbacks) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1ResolveAccountRequest);
      zzc.zza(parcel, (IInterface)param1IResolveAccountCallbacks);
      transactAndReadExceptionReturnVoid(5, parcel);
    }
    
    public void saveAccountToSessionStore(int param1Int, Account param1Account, ISignInCallbacks param1ISignInCallbacks) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeInt(param1Int);
      zzc.zza(parcel, (Parcelable)param1Account);
      zzc.zza(parcel, param1ISignInCallbacks);
      transactAndReadExceptionReturnVoid(8, parcel);
    }
    
    public void saveDefaultAccountToSharedPref(IAccountAccessor param1IAccountAccessor, int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (IInterface)param1IAccountAccessor);
      parcel.writeInt(param1Int);
      zzc.zza(parcel, param1Boolean);
      transactAndReadExceptionReturnVoid(9, parcel);
    }
    
    public void setGamesHasBeenGreeted(boolean param1Boolean) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1Boolean);
      transactAndReadExceptionReturnVoid(13, parcel);
    }
    
    public void signIn(SignInRequest param1SignInRequest, ISignInCallbacks param1ISignInCallbacks) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1SignInRequest);
      zzc.zza(parcel, param1ISignInCallbacks);
      transactAndReadExceptionReturnVoid(12, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\ISignInService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */