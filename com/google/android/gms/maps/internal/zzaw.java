package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzaw extends zzb implements zzav {
  public zzaw() {
    super("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 == 1) {
      boolean bool = onMyLocationButtonClick();
      paramParcel2.writeNoException();
      zzc.zza(paramParcel2, bool);
      return true;
    } 
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */