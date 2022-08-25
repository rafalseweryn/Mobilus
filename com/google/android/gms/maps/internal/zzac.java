package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzac extends zzb implements zzab {
  public zzac() {
    super("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 == 1) {
      zze(zzu.zzg(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    } 
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzac.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */