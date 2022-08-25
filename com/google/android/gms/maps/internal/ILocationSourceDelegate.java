package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public interface ILocationSourceDelegate extends IInterface {
  void activate(zzah paramzzah) throws RemoteException;
  
  void deactivate() throws RemoteException;
  
  public static abstract class zza extends zzb implements ILocationSourceDelegate {
    public zza() {
      super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
    }
    
    protected final boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      zzah zzah;
      switch (param1Int1) {
        default:
          return false;
        case 2:
          deactivate();
          param1Parcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      IBinder iBinder = param1Parcel1.readStrongBinder();
      if (iBinder == null) {
        iBinder = null;
      } else {
        IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
        if (iInterface instanceof zzah) {
          zzah = (zzah)iInterface;
        } else {
          zzah = new zzai((IBinder)zzah);
        } 
      } 
      activate(zzah);
      param1Parcel2.writeNoException();
      return true;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\ILocationSourceDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */