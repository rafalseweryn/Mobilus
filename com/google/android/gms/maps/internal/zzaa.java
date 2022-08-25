package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzo;

public abstract class zzaa extends zzb implements zzz {
  public zzaa() {
    super("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    switch (paramInt1) {
      default:
        return false;
      case 2:
        zza(zzo.zze(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 1:
        break;
    } 
    onIndoorBuildingFocused();
    paramParcel2.writeNoException();
    return true;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzaa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */