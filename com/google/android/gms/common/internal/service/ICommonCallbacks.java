package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface ICommonCallbacks extends IInterface {
  void onDefaultAccountCleared(int paramInt) throws RemoteException;
  
  public static abstract class Stub extends zzb implements ICommonCallbacks {
    public Stub() {
      super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }
    
    public static ICommonCallbacks asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
      return (iInterface instanceof ICommonCallbacks) ? (ICommonCallbacks)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 == 1) {
        onDefaultAccountCleared(param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      return false;
    }
    
    public static class Proxy extends zza implements ICommonCallbacks {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.internal.service.ICommonCallbacks");
      }
      
      public void onDefaultAccountCleared(int param2Int) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeInt(param2Int);
        transactAndReadExceptionReturnVoid(1, parcel);
      }
    }
  }
  
  public static class Proxy extends zza implements ICommonCallbacks {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.internal.service.ICommonCallbacks");
    }
    
    public void onDefaultAccountCleared(int param1Int) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeInt(param1Int);
      transactAndReadExceptionReturnVoid(1, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\service\ICommonCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */