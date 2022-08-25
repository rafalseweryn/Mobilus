package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzi extends zzb implements zzh {
  public zzi() {
    super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    switch (paramInt1) {
      default:
        return false;
      case 2:
        iObjectWrapper = zzi(zzu.zzg(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        zzc.zza(paramParcel2, (IInterface)iObjectWrapper);
        return true;
      case 1:
        break;
    } 
    IObjectWrapper iObjectWrapper = zzh(zzu.zzg(iObjectWrapper.readStrongBinder()));
    paramParcel2.writeNoException();
    zzc.zza(paramParcel2, (IInterface)iObjectWrapper);
    return true;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */