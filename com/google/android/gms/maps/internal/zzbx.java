package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzbx extends zza implements IUiSettingsDelegate {
  zzbx(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
  }
  
  public final boolean isCompassEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isIndoorLevelPickerEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(17, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isMapToolbarEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(19, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isMyLocationButtonEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(11, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isRotateGesturesEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(15, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isScrollGesturesEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(12, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isTiltGesturesEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(14, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isZoomControlsEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(9, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isZoomGesturesEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(13, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void setAllGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(8, parcel);
  }
  
  public final void setCompassEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(2, parcel);
  }
  
  public final void setIndoorLevelPickerEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(16, parcel);
  }
  
  public final void setMapToolbarEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(18, parcel);
  }
  
  public final void setMyLocationButtonEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(3, parcel);
  }
  
  public final void setRotateGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(7, parcel);
  }
  
  public final void setScrollGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(4, parcel);
  }
  
  public final void setTiltGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(6, parcel);
  }
  
  public final void setZoomControlsEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(1, parcel);
  }
  
  public final void setZoomGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(5, parcel);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzbx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */