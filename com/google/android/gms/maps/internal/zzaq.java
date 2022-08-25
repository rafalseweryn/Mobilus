package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzaq extends zzb implements zzap {
  public zzaq() {
    super("com.google.android.gms.maps.internal.IOnMapReadyCallback");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 == 1) {
      IGoogleMapDelegate iGoogleMapDelegate;
      IBinder iBinder = paramParcel1.readStrongBinder();
      if (iBinder == null) {
        iBinder = null;
      } else {
        IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (iInterface instanceof IGoogleMapDelegate) {
          iGoogleMapDelegate = (IGoogleMapDelegate)iInterface;
        } else {
          iGoogleMapDelegate = new zzg((IBinder)iGoogleMapDelegate);
        } 
      } 
      zza(iGoogleMapDelegate);
      paramParcel2.writeNoException();
      return true;
    } 
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzaq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */