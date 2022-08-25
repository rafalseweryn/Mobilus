package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzm extends zza implements zzk {
  zzm(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
  }
  
  public final float getBearing() throws RemoteException {
    Parcel parcel = transactAndReadException(12, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final LatLngBounds getBounds() throws RemoteException {
    Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
    LatLngBounds latLngBounds = zzc.<LatLngBounds>zza(parcel, LatLngBounds.CREATOR);
    parcel.recycle();
    return latLngBounds;
  }
  
  public final float getHeight() throws RemoteException {
    Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final LatLng getPosition() throws RemoteException {
    Parcel parcel = transactAndReadException(4, obtainAndWriteInterfaceToken());
    LatLng latLng = zzc.<LatLng>zza(parcel, LatLng.CREATOR);
    parcel.recycle();
    return latLng;
  }
  
  public final float getTransparency() throws RemoteException {
    Parcel parcel = transactAndReadException(18, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getWidth() throws RemoteException {
    Parcel parcel = transactAndReadException(7, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getZIndex() throws RemoteException {
    Parcel parcel = transactAndReadException(14, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final boolean isClickable() throws RemoteException {
    Parcel parcel = transactAndReadException(23, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isVisible() throws RemoteException {
    Parcel parcel = transactAndReadException(16, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void remove() throws RemoteException {
    transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
  }
  
  public final void setBearing(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(11, parcel);
  }
  
  public final void setClickable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(22, parcel);
  }
  
  public final void setDimensions(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(5, parcel);
  }
  
  public final void setPosition(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLng);
    transactAndReadExceptionReturnVoid(3, parcel);
  }
  
  public final void setPositionFromBounds(LatLngBounds paramLatLngBounds) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLngBounds);
    transactAndReadExceptionReturnVoid(9, parcel);
  }
  
  public final void setTransparency(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(17, parcel);
  }
  
  public final void setVisible(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(15, parcel);
  }
  
  public final void setZIndex(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(13, parcel);
  }
  
  public final void zza(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat1);
    parcel.writeFloat(paramFloat2);
    transactAndReadExceptionReturnVoid(6, parcel);
  }
  
  public final boolean zzb(zzk paramzzk) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, paramzzk);
    Parcel parcel1 = transactAndReadException(19, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final void zze(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(24, parcel);
  }
  
  public final void zzf(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(21, parcel);
  }
  
  public final int zzi() throws RemoteException {
    Parcel parcel = transactAndReadException(20, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final IObjectWrapper zzj() throws RemoteException {
    Parcel parcel = transactAndReadException(25, obtainAndWriteInterfaceToken());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */