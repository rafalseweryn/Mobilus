package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzd extends zzb implements zzc {
  public zzd() {
    super("com.google.android.gms.maps.internal.ICancelableCallback");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    switch (paramInt1) {
      default:
        return false;
      case 2:
        onCancel();
        paramParcel2.writeNoException();
        return true;
      case 1:
        break;
    } 
    onFinish();
    paramParcel2.writeNoException();
    return true;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */