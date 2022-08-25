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
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public final class zzbu extends zza implements IStreetViewPanoramaDelegate {
  zzbu(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
  }
  
  public final void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramStreetViewPanoramaCamera);
    parcel.writeLong(paramLong);
    transactAndReadExceptionReturnVoid(9, parcel);
  }
  
  public final void enablePanning(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(2, parcel);
  }
  
  public final void enableStreetNames(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(4, parcel);
  }
  
  public final void enableUserNavigation(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(3, parcel);
  }
  
  public final void enableZoom(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(1, parcel);
  }
  
  public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
    Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
    StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera)zzc.zza(parcel, StreetViewPanoramaCamera.CREATOR);
    parcel.recycle();
    return streetViewPanoramaCamera;
  }
  
  public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
    Parcel parcel = transactAndReadException(14, obtainAndWriteInterfaceToken());
    StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation)zzc.zza(parcel, StreetViewPanoramaLocation.CREATOR);
    parcel.recycle();
    return streetViewPanoramaLocation;
  }
  
  public final boolean isPanningGesturesEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(6, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isStreetNamesEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isUserNavigationEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(7, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isZoomGesturesEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(5, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramStreetViewPanoramaOrientation);
    Parcel parcel1 = transactAndReadException(19, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper);
    Parcel parcel1 = transactAndReadException(18, parcel2);
    StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation)zzc.zza(parcel1, StreetViewPanoramaOrientation.CREATOR);
    parcel1.recycle();
    return streetViewPanoramaOrientation;
  }
  
  public final void setOnStreetViewPanoramaCameraChangeListener(zzbh paramzzbh) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbh);
    transactAndReadExceptionReturnVoid(16, parcel);
  }
  
  public final void setOnStreetViewPanoramaChangeListener(zzbj paramzzbj) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbj);
    transactAndReadExceptionReturnVoid(15, parcel);
  }
  
  public final void setOnStreetViewPanoramaClickListener(zzbl paramzzbl) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbl);
    transactAndReadExceptionReturnVoid(17, parcel);
  }
  
  public final void setOnStreetViewPanoramaLongClickListener(zzbn paramzzbn) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbn);
    transactAndReadExceptionReturnVoid(20, parcel);
  }
  
  public final void setPosition(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLng);
    transactAndReadExceptionReturnVoid(12, parcel);
  }
  
  public final void setPositionWithID(String paramString) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeString(paramString);
    transactAndReadExceptionReturnVoid(11, parcel);
  }
  
  public final void setPositionWithRadius(LatLng paramLatLng, int paramInt) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLng);
    parcel.writeInt(paramInt);
    transactAndReadExceptionReturnVoid(13, parcel);
  }
  
  public final void setPositionWithRadiusAndSource(LatLng paramLatLng, int paramInt, StreetViewSource paramStreetViewSource) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLng);
    parcel.writeInt(paramInt);
    zzc.zza(parcel, (Parcelable)paramStreetViewSource);
    transactAndReadExceptionReturnVoid(22, parcel);
  }
  
  public final void setPositionWithSource(LatLng paramLatLng, StreetViewSource paramStreetViewSource) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLng);
    zzc.zza(parcel, (Parcelable)paramStreetViewSource);
    transactAndReadExceptionReturnVoid(21, parcel);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzbu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */