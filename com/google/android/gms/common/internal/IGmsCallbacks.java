package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IGmsCallbacks extends IInterface {
  void onAccountValidationComplete(int paramInt, Bundle paramBundle) throws RemoteException;
  
  void onPostInitComplete(int paramInt, IBinder paramIBinder, Bundle paramBundle) throws RemoteException;
  
  void onPostInitCompleteWithConnectionInfo(int paramInt, IBinder paramIBinder, ConnectionInfo paramConnectionInfo) throws RemoteException;
  
  public static abstract class Stub extends zzb implements IGmsCallbacks {
    public Stub() {
      super("com.google.android.gms.common.internal.IGmsCallbacks");
    }
    
    public static IGmsCallbacks asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
      return (iInterface instanceof IGmsCallbacks) ? (IGmsCallbacks)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      switch (param1Int1) {
        default:
          return false;
        case 3:
          onPostInitCompleteWithConnectionInfo(param1Parcel1.readInt(), param1Parcel1.readStrongBinder(), (ConnectionInfo)zzc.zza(param1Parcel1, ConnectionInfo.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 2:
          onAccountValidationComplete(param1Parcel1.readInt(), (Bundle)zzc.zza(param1Parcel1, Bundle.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      onPostInitComplete(param1Parcel1.readInt(), param1Parcel1.readStrongBinder(), (Bundle)zzc.zza(param1Parcel1, Bundle.CREATOR));
      param1Parcel2.writeNoException();
      return true;
    }
    
    public static class Proxy extends zza implements IGmsCallbacks {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
      }
      
      public void onAccountValidationComplete(int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeInt(param2Int);
        zzc.zza(parcel, (Parcelable)param2Bundle);
        transactAndReadExceptionReturnVoid(2, parcel);
      }
      
      public void onPostInitComplete(int param2Int, IBinder param2IBinder, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeInt(param2Int);
        parcel.writeStrongBinder(param2IBinder);
        zzc.zza(parcel, (Parcelable)param2Bundle);
        transactAndReadExceptionReturnVoid(1, parcel);
      }
      
      public void onPostInitCompleteWithConnectionInfo(int param2Int, IBinder param2IBinder, ConnectionInfo param2ConnectionInfo) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        parcel.writeInt(param2Int);
        parcel.writeStrongBinder(param2IBinder);
        zzc.zza(parcel, (Parcelable)param2ConnectionInfo);
        transactAndReadExceptionReturnVoid(3, parcel);
      }
    }
  }
  
  public static class Proxy extends zza implements IGmsCallbacks {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }
    
    public void onAccountValidationComplete(int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeInt(param1Int);
      zzc.zza(parcel, (Parcelable)param1Bundle);
      transactAndReadExceptionReturnVoid(2, parcel);
    }
    
    public void onPostInitComplete(int param1Int, IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeInt(param1Int);
      parcel.writeStrongBinder(param1IBinder);
      zzc.zza(parcel, (Parcelable)param1Bundle);
      transactAndReadExceptionReturnVoid(1, parcel);
    }
    
    public void onPostInitCompleteWithConnectionInfo(int param1Int, IBinder param1IBinder, ConnectionInfo param1ConnectionInfo) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      parcel.writeInt(param1Int);
      parcel.writeStrongBinder(param1IBinder);
      zzc.zza(parcel, (Parcelable)param1ConnectionInfo);
      transactAndReadExceptionReturnVoid(3, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\IGmsCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */