package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;
import java.util.List;

public interface IOfflineAccessCallbacks extends IInterface {
  void checkServerAuthorization(String paramString, List<Scope> paramList, ISignInService paramISignInService) throws RemoteException;
  
  void uploadServerAuthCode(String paramString1, String paramString2, ISignInService paramISignInService) throws RemoteException;
  
  public static abstract class Stub extends zzb implements IOfflineAccessCallbacks {
    public Stub() {
      super("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
    }
    
    public static IOfflineAccessCallbacks asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
      return (iInterface instanceof IOfflineAccessCallbacks) ? (IOfflineAccessCallbacks)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 3:
          uploadServerAuthCode(param1Parcel1.readString(), param1Parcel1.readString(), ISignInService.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 2:
          break;
      } 
      checkServerAuthorization(param1Parcel1.readString(), param1Parcel1.createTypedArrayList(Scope.CREATOR), ISignInService.Stub.asInterface(param1Parcel1.readStrongBinder()));
      param1Parcel2.writeNoException();
      return true;
    }
    
    public static class Proxy extends zza implements IOfflineAccessCallbacks {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
      }
      
      public void checkServerAuthorization(String param2String, List<Scope> param2List, ISignInService param2ISignInService) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeString(param2String);
        parcel.writeTypedList(param2List);
        zzc.zza(parcel, param2ISignInService);
        transactAndReadExceptionReturnVoid(2, parcel);
      }
      
      public void uploadServerAuthCode(String param2String1, String param2String2, ISignInService param2ISignInService) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        zzc.zza(parcel, param2ISignInService);
        transactAndReadExceptionReturnVoid(3, parcel);
      }
    }
  }
  
  public static class Proxy extends zza implements IOfflineAccessCallbacks {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
    }
    
    public void checkServerAuthorization(String param1String, List<Scope> param1List, ISignInService param1ISignInService) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeString(param1String);
      parcel.writeTypedList(param1List);
      zzc.zza(parcel, param1ISignInService);
      transactAndReadExceptionReturnVoid(2, parcel);
    }
    
    public void uploadServerAuthCode(String param1String1, String param1String2, ISignInService param1ISignInService) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeString(param1String1);
      parcel.writeString(param1String2);
      zzc.zza(parcel, param1ISignInService);
      transactAndReadExceptionReturnVoid(3, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\IOfflineAccessCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */