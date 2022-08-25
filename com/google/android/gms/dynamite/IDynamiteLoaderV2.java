package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IDynamiteLoaderV2 extends IInterface {
  IObjectWrapper loadModule(IObjectWrapper paramIObjectWrapper, String paramString, byte[] paramArrayOfbyte) throws RemoteException;
  
  IObjectWrapper loadModule2(IObjectWrapper paramIObjectWrapper1, String paramString, int paramInt, IObjectWrapper paramIObjectWrapper2) throws RemoteException;
  
  public static abstract class Stub extends zzb implements IDynamiteLoaderV2 {
    public Stub() {
      super("com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }
    
    public static IDynamiteLoaderV2 asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
      return (iInterface instanceof IDynamiteLoaderV2) ? (IDynamiteLoaderV2)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 2:
          iObjectWrapper = loadModule2(IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString(), param1Parcel1.readInt(), IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
          return true;
        case 1:
          break;
      } 
      IObjectWrapper iObjectWrapper = loadModule(IObjectWrapper.Stub.asInterface(iObjectWrapper.readStrongBinder()), iObjectWrapper.readString(), iObjectWrapper.createByteArray());
      param1Parcel2.writeNoException();
      zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
      return true;
    }
    
    public static class Proxy extends zza implements IDynamiteLoaderV2 {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
      }
      
      public IObjectWrapper loadModule(IObjectWrapper param2IObjectWrapper, String param2String, byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        parcel2.writeString(param2String);
        parcel2.writeByteArray(param2ArrayOfbyte);
        Parcel parcel1 = transactAndReadException(1, parcel2);
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper;
      }
      
      public IObjectWrapper loadModule2(IObjectWrapper param2IObjectWrapper1, String param2String, int param2Int, IObjectWrapper param2IObjectWrapper2) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper1);
        parcel2.writeString(param2String);
        parcel2.writeInt(param2Int);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper2);
        Parcel parcel1 = transactAndReadException(2, parcel2);
        param2IObjectWrapper1 = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return param2IObjectWrapper1;
      }
    }
  }
  
  public static class Proxy extends zza implements IDynamiteLoaderV2 {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }
    
    public IObjectWrapper loadModule(IObjectWrapper param1IObjectWrapper, String param1String, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      parcel2.writeString(param1String);
      parcel2.writeByteArray(param1ArrayOfbyte);
      Parcel parcel1 = transactAndReadException(1, parcel2);
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
      parcel1.recycle();
      return iObjectWrapper;
    }
    
    public IObjectWrapper loadModule2(IObjectWrapper param1IObjectWrapper1, String param1String, int param1Int, IObjectWrapper param1IObjectWrapper2) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper1);
      parcel2.writeString(param1String);
      parcel2.writeInt(param1Int);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper2);
      Parcel parcel1 = transactAndReadException(2, parcel2);
      param1IObjectWrapper1 = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
      parcel1.recycle();
      return param1IObjectWrapper1;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamite\IDynamiteLoaderV2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */