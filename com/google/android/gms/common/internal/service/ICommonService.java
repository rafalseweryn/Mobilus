package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ICommonService extends IInterface {
  void clearDefaultAccount(ICommonCallbacks paramICommonCallbacks) throws RemoteException;
  
  public static abstract class Stub extends zzb implements ICommonService {
    public Stub() {
      super("com.google.android.gms.common.internal.service.ICommonService");
    }
    
    public static ICommonService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService");
      return (iInterface instanceof ICommonService) ? (ICommonService)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 == 1) {
        clearDefaultAccount(ICommonCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()));
        return true;
      } 
      return false;
    }
    
    public static class Proxy extends zza implements ICommonService {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.internal.service.ICommonService");
      }
      
      public void clearDefaultAccount(ICommonCallbacks param2ICommonCallbacks) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2ICommonCallbacks);
        transactOneway(1, parcel);
      }
    }
  }
  
  public static class Proxy extends zza implements ICommonService {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }
    
    public void clearDefaultAccount(ICommonCallbacks param1ICommonCallbacks) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1ICommonCallbacks);
      transactOneway(1, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\service\ICommonService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */