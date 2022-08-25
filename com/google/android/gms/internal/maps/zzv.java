package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public final class zzv extends zza implements zzt {
  zzv(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
  }
  
  public final float getAlpha() throws RemoteException {
    Parcel parcel = transactAndReadException(26, obtainAndWriteInterfaceToken());
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
  
  public final float getRotation() throws RemoteException {
    Parcel parcel = transactAndReadException(23, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final String getSnippet() throws RemoteException {
    Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final String getTitle() throws RemoteException {
    Parcel parcel = transactAndReadException(6, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final float getZIndex() throws RemoteException {
    Parcel parcel = transactAndReadException(28, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final void hideInfoWindow() throws RemoteException {
    transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken());
  }
  
  public final boolean isDraggable() throws RemoteException {
    Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isFlat() throws RemoteException {
    Parcel parcel = transactAndReadException(21, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isInfoWindowShown() throws RemoteException {
    Parcel parcel = transactAndReadException(13, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isVisible() throws RemoteException {
    Parcel parcel = transactAndReadException(15, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void remove() throws RemoteException {
    transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
  }
  
  public final void setAlpha(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(25, parcel);
  }
  
  public final void setAnchor(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat1);
    parcel.writeFloat(paramFloat2);
    transactAndReadExceptionReturnVoid(19, parcel);
  }
  
  public final void setDraggable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(9, parcel);
  }
  
  public final void setFlat(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(20, parcel);
  }
  
  public final void setInfoWindowAnchor(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat1);
    parcel.writeFloat(paramFloat2);
    transactAndReadExceptionReturnVoid(24, parcel);
  }
  
  public final void setPosition(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLng);
    transactAndReadExceptionReturnVoid(3, parcel);
  }
  
  public final void setRotation(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(22, parcel);
  }
  
  public final void setSnippet(String paramString) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeString(paramString);
    transactAndReadExceptionReturnVoid(7, parcel);
  }
  
  public final void setTitle(String paramString) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeString(paramString);
    transactAndReadExceptionReturnVoid(5, parcel);
  }
  
  public final void setVisible(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(14, parcel);
  }
  
  public final void setZIndex(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(27, parcel);
  }
  
  public final void showInfoWindow() throws RemoteException {
    transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken());
  }
  
  public final void zze(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(29, parcel);
  }
  
  public final void zzg(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(18, parcel);
  }
  
  public final int zzi() throws RemoteException {
    Parcel parcel = transactAndReadException(17, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final IObjectWrapper zzj() throws RemoteException {
    Parcel parcel = transactAndReadException(30, obtainAndWriteInterfaceToken());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final boolean zzj(zzt paramzzt) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, paramzzt);
    Parcel parcel1 = transactAndReadException(16, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */