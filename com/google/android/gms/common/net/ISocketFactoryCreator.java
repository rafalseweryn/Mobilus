package com.google.android.gms.common.net;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISocketFactoryCreator extends IInterface {
  IObjectWrapper newSocketFactory(IObjectWrapper paramIObjectWrapper1, IObjectWrapper paramIObjectWrapper2, IObjectWrapper paramIObjectWrapper3, boolean paramBoolean) throws RemoteException;
  
  IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper paramIObjectWrapper1, IObjectWrapper paramIObjectWrapper2, IObjectWrapper paramIObjectWrapper3, String paramString) throws RemoteException;
  
  public static abstract class Stub extends zzb implements ISocketFactoryCreator {
    public Stub() {
      super("com.google.android.gms.common.net.ISocketFactoryCreator");
    }
    
    public static ISocketFactoryCreator asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.net.ISocketFactoryCreator");
      return (iInterface instanceof ISocketFactoryCreator) ? (ISocketFactoryCreator)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 2:
          iObjectWrapper = newSocketFactoryWithCacheDir(IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()), IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()), IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString());
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
          return true;
        case 1:
          break;
      } 
      IObjectWrapper iObjectWrapper = newSocketFactory(IObjectWrapper.Stub.asInterface(iObjectWrapper.readStrongBinder()), IObjectWrapper.Stub.asInterface(iObjectWrapper.readStrongBinder()), IObjectWrapper.Stub.asInterface(iObjectWrapper.readStrongBinder()), zzc.zza((Parcel)iObjectWrapper));
      param1Parcel2.writeNoException();
      zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
      return true;
    }
    
    public static class Proxy extends zza implements ISocketFactoryCreator {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.net.ISocketFactoryCreator");
      }
      
      public IObjectWrapper newSocketFactory(IObjectWrapper param2IObjectWrapper1, IObjectWrapper param2IObjectWrapper2, IObjectWrapper param2IObjectWrapper3, boolean param2Boolean) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper1);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper2);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper3);
        zzc.zza(parcel2, param2Boolean);
        Parcel parcel1 = transactAndReadException(1, parcel2);
        param2IObjectWrapper2 = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return param2IObjectWrapper2;
      }
      
      public IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper param2IObjectWrapper1, IObjectWrapper param2IObjectWrapper2, IObjectWrapper param2IObjectWrapper3, String param2String) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper1);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper2);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper3);
        parcel2.writeString(param2String);
        Parcel parcel1 = transactAndReadException(2, parcel2);
        param2IObjectWrapper2 = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return param2IObjectWrapper2;
      }
    }
  }
  
  public static class Proxy extends zza implements ISocketFactoryCreator {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.net.ISocketFactoryCreator");
    }
    
    public IObjectWrapper newSocketFactory(IObjectWrapper param1IObjectWrapper1, IObjectWrapper param1IObjectWrapper2, IObjectWrapper param1IObjectWrapper3, boolean param1Boolean) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper1);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper2);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper3);
      zzc.zza(parcel2, param1Boolean);
      Parcel parcel1 = transactAndReadException(1, parcel2);
      param1IObjectWrapper2 = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
      parcel1.recycle();
      return param1IObjectWrapper2;
    }
    
    public IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper param1IObjectWrapper1, IObjectWrapper param1IObjectWrapper2, IObjectWrapper param1IObjectWrapper3, String param1String) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper1);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper2);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper3);
      parcel2.writeString(param1String);
      Parcel parcel1 = transactAndReadException(2, parcel2);
      param1IObjectWrapper2 = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
      parcel1.recycle();
      return param1IObjectWrapper2;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\net\ISocketFactoryCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */