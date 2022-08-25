package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ICertData extends IInterface {
  IObjectWrapper getBytesWrapped() throws RemoteException;
  
  int getHashCode() throws RemoteException;
  
  public static abstract class Stub extends zzb implements ICertData {
    public Stub() {
      super("com.google.android.gms.common.internal.ICertData");
    }
    
    public static ICertData asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
      return (iInterface instanceof ICertData) ? (ICertData)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 2:
          param1Int1 = getHashCode();
          param1Parcel2.writeNoException();
          param1Parcel2.writeInt(param1Int1);
          return true;
        case 1:
          break;
      } 
      IObjectWrapper iObjectWrapper = getBytesWrapped();
      param1Parcel2.writeNoException();
      zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
      return true;
    }
    
    public static class Proxy extends zza implements ICertData {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.internal.ICertData");
      }
      
      public IObjectWrapper getBytesWrapped() throws RemoteException {
        Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iObjectWrapper;
      }
      
      public int getHashCode() throws RemoteException {
        Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
        int i = parcel.readInt();
        parcel.recycle();
        return i;
      }
    }
  }
  
  public static class Proxy extends zza implements ICertData {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.internal.ICertData");
    }
    
    public IObjectWrapper getBytesWrapped() throws RemoteException {
      Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iObjectWrapper;
    }
    
    public int getHashCode() throws RemoteException {
      Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
      int i = parcel.readInt();
      parcel.recycle();
      return i;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ICertData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */