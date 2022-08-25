package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISignInButtonCreator extends IInterface {
  IObjectWrapper newSignInButton(IObjectWrapper paramIObjectWrapper, int paramInt1, int paramInt2) throws RemoteException;
  
  IObjectWrapper newSignInButtonFromConfig(IObjectWrapper paramIObjectWrapper, SignInButtonConfig paramSignInButtonConfig) throws RemoteException;
  
  public static abstract class Stub extends zzb implements ISignInButtonCreator {
    public Stub() {
      super("com.google.android.gms.common.internal.ISignInButtonCreator");
    }
    
    public static ISignInButtonCreator asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
      return (iInterface instanceof ISignInButtonCreator) ? (ISignInButtonCreator)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 2:
          iObjectWrapper = newSignInButtonFromConfig(IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()), (SignInButtonConfig)zzc.zza(param1Parcel1, SignInButtonConfig.CREATOR));
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
          return true;
        case 1:
          break;
      } 
      IObjectWrapper iObjectWrapper = newSignInButton(IObjectWrapper.Stub.asInterface(iObjectWrapper.readStrongBinder()), iObjectWrapper.readInt(), iObjectWrapper.readInt());
      param1Parcel2.writeNoException();
      zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
      return true;
    }
    
    public static class Proxy extends zza implements ISignInButtonCreator {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
      }
      
      public IObjectWrapper newSignInButton(IObjectWrapper param2IObjectWrapper, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (IInterface)param2IObjectWrapper);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel = transactAndReadException(1, parcel);
        param2IObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return param2IObjectWrapper;
      }
      
      public IObjectWrapper newSignInButtonFromConfig(IObjectWrapper param2IObjectWrapper, SignInButtonConfig param2SignInButtonConfig) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        zzc.zza(parcel2, (Parcelable)param2SignInButtonConfig);
        Parcel parcel1 = transactAndReadException(2, parcel2);
        param2IObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return param2IObjectWrapper;
      }
    }
  }
  
  public static class Proxy extends zza implements ISignInButtonCreator {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }
    
    public IObjectWrapper newSignInButton(IObjectWrapper param1IObjectWrapper, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (IInterface)param1IObjectWrapper);
      parcel.writeInt(param1Int1);
      parcel.writeInt(param1Int2);
      parcel = transactAndReadException(1, parcel);
      param1IObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return param1IObjectWrapper;
    }
    
    public IObjectWrapper newSignInButtonFromConfig(IObjectWrapper param1IObjectWrapper, SignInButtonConfig param1SignInButtonConfig) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      zzc.zza(parcel2, (Parcelable)param1SignInButtonConfig);
      Parcel parcel1 = transactAndReadException(2, parcel2);
      param1IObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
      parcel1.recycle();
      return param1IObjectWrapper;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ISignInButtonCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */