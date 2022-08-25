package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzb extends zza implements ICameraUpdateFactoryDelegate {
  zzb(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
  }
  
  public final IObjectWrapper newCameraPosition(CameraPosition paramCameraPosition) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramCameraPosition);
    parcel = transactAndReadException(7, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLng(LatLng paramLatLng) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramLatLng);
    Parcel parcel1 = transactAndReadException(8, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramLatLngBounds);
    parcel2.writeInt(paramInt);
    Parcel parcel1 = transactAndReadException(10, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramLatLngBounds);
    parcel2.writeInt(paramInt1);
    parcel2.writeInt(paramInt2);
    parcel2.writeInt(paramInt3);
    Parcel parcel1 = transactAndReadException(11, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLngZoom(LatLng paramLatLng, float paramFloat) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramLatLng);
    parcel2.writeFloat(paramFloat);
    Parcel parcel1 = transactAndReadException(9, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper scrollBy(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel1 = obtainAndWriteInterfaceToken();
    parcel1.writeFloat(paramFloat1);
    parcel1.writeFloat(paramFloat2);
    Parcel parcel2 = transactAndReadException(3, parcel1);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel2.readStrongBinder());
    parcel2.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomBy(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    parcel = transactAndReadException(5, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomByWithFocus(float paramFloat, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    parcel.writeInt(paramInt1);
    parcel.writeInt(paramInt2);
    parcel = transactAndReadException(6, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomIn() throws RemoteException {
    Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomOut() throws RemoteException {
    Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomTo(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    parcel = transactAndReadException(4, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */