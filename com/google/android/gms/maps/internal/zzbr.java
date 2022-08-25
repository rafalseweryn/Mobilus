package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbr extends zza implements IProjectionDelegate {
  zzbr(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
  }
  
  public final LatLng fromScreenLocation(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper);
    Parcel parcel1 = transactAndReadException(1, parcel2);
    LatLng latLng = (LatLng)zzc.zza(parcel1, LatLng.CREATOR);
    parcel1.recycle();
    return latLng;
  }
  
  public final VisibleRegion getVisibleRegion() throws RemoteException {
    Parcel parcel = transactAndReadException(3, obtainAndWriteInterfaceToken());
    VisibleRegion visibleRegion = (VisibleRegion)zzc.zza(parcel, VisibleRegion.CREATOR);
    parcel.recycle();
    return visibleRegion;
  }
  
  public final IObjectWrapper toScreenLocation(LatLng paramLatLng) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramLatLng);
    Parcel parcel1 = transactAndReadException(2, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzbr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */