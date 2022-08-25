package com.google.android.gms.internal.stable;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzb extends Binder implements IInterface {
  private static zzd zzc;
  
  protected zzb(String paramString) {
    attachInterface(this, paramString);
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  protected boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    return routeToSuperOrEnforceInterface(paramInt1, paramParcel1, paramParcel2, paramInt2) ? true : dispatchTransaction(paramInt1, paramParcel1, paramParcel2, paramInt2);
  }
  
  protected boolean routeToSuperOrEnforceInterface(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 > 16777215)
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
    paramParcel1.enforceInterface(getInterfaceDescriptor());
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */