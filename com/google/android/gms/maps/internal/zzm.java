package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.CameraPosition;

public abstract class zzm extends zzb implements zzl {
  public zzm() {
    super("com.google.android.gms.maps.internal.IOnCameraChangeListener");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 == 1) {
      onCameraChange((CameraPosition)zzc.zza(paramParcel1, CameraPosition.CREATOR));
      paramParcel2.writeNoException();
      return true;
    } 
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */