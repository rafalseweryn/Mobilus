package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IDynamiteLoader extends IInterface {
  IObjectWrapper createModuleContext(IObjectWrapper paramIObjectWrapper, String paramString, int paramInt) throws RemoteException;
  
  int getModuleVersion(IObjectWrapper paramIObjectWrapper, String paramString) throws RemoteException;
  
  int getModuleVersion2(IObjectWrapper paramIObjectWrapper, String paramString, boolean paramBoolean) throws RemoteException;
  
  public static abstract class Stub extends zzb implements IDynamiteLoader {
    public Stub() {
      super("com.google.android.gms.dynamite.IDynamiteLoader");
    }
    
    public static IDynamiteLoader asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
      return (iInterface instanceof IDynamiteLoader) ? (IDynamiteLoader)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      IObjectWrapper iObjectWrapper;
      switch (param1Int1) {
        default:
          return false;
        case 3:
          param1Int1 = getModuleVersion2(IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString(), zzc.zza(param1Parcel1));
          break;
        case 2:
          iObjectWrapper = createModuleContext(IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString(), param1Parcel1.readInt());
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
          return true;
        case 1:
          param1Int1 = getModuleVersion(IObjectWrapper.Stub.asInterface(iObjectWrapper.readStrongBinder()), iObjectWrapper.readString());
          break;
      } 
      param1Parcel2.writeNoException();
      param1Parcel2.writeInt(param1Int1);
      return true;
    }
    
    public static class Proxy extends zza implements IDynamiteLoader {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
      }
      
      public IObjectWrapper createModuleContext(IObjectWrapper param2IObjectWrapper, String param2String, int param2Int) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        parcel2.writeString(param2String);
        parcel2.writeInt(param2Int);
        Parcel parcel1 = transactAndReadException(2, parcel2);
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper;
      }
      
      public int getModuleVersion(IObjectWrapper param2IObjectWrapper, String param2String) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        parcel2.writeString(param2String);
        Parcel parcel1 = transactAndReadException(1, parcel2);
        int i = parcel1.readInt();
        parcel1.recycle();
        return i;
      }
      
      public int getModuleVersion2(IObjectWrapper param2IObjectWrapper, String param2String, boolean param2Boolean) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        parcel2.writeString(param2String);
        zzc.zza(parcel2, param2Boolean);
        Parcel parcel1 = transactAndReadException(3, parcel2);
        int i = parcel1.readInt();
        parcel1.recycle();
        return i;
      }
    }
  }
  
  public static class Proxy extends zza implements IDynamiteLoader {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }
    
    public IObjectWrapper createModuleContext(IObjectWrapper param1IObjectWrapper, String param1String, int param1Int) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      parcel2.writeString(param1String);
      parcel2.writeInt(param1Int);
      Parcel parcel1 = transactAndReadException(2, parcel2);
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
      parcel1.recycle();
      return iObjectWrapper;
    }
    
    public int getModuleVersion(IObjectWrapper param1IObjectWrapper, String param1String) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      parcel2.writeString(param1String);
      Parcel parcel1 = transactAndReadException(1, parcel2);
      int i = parcel1.readInt();
      parcel1.recycle();
      return i;
    }
    
    public int getModuleVersion2(IObjectWrapper param1IObjectWrapper, String param1String, boolean param1Boolean) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      parcel2.writeString(param1String);
      zzc.zza(parcel2, param1Boolean);
      Parcel parcel1 = transactAndReadException(3, parcel2);
      int i = parcel1.readInt();
      parcel1.recycle();
      return i;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamite\IDynamiteLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */