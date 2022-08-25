package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzq extends zzb implements zzp {
  public zzq() {
    super("com.google.android.gms.maps.internal.IOnCameraMoveCanceledListener");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 == 1) {
      onCameraMoveCanceled();
      paramParcel2.writeNoException();
      return true;
    } 
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */