package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzj extends zza implements zzh {
  zzj(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
  }
  
  public final LatLng getCenter() throws RemoteException {
    Parcel parcel = transactAndReadException(4, obtainAndWriteInterfaceToken());
    LatLng latLng = zzc.<LatLng>zza(parcel, LatLng.CREATOR);
    parcel.recycle();
    return latLng;
  }
  
  public final int getFillColor() throws RemoteException {
    Parcel parcel = transactAndReadException(12, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final double getRadius() throws RemoteException {
    Parcel parcel = transactAndReadException(6, obtainAndWriteInterfaceToken());
    double d = parcel.readDouble();
    parcel.recycle();
    return d;
  }
  
  public final int getStrokeColor() throws RemoteException {
    Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final List<PatternItem> getStrokePattern() throws RemoteException {
    Parcel parcel = transactAndReadException(22, obtainAndWriteInterfaceToken());
    ArrayList<PatternItem> arrayList = parcel.createTypedArrayList(PatternItem.CREATOR);
    parcel.recycle();
    return arrayList;
  }
  
  public final float getStrokeWidth() throws RemoteException {
    Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
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
    Parcel parcel = transactAndReadException(20, obtainAndWriteInterfaceToken());
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
  
  public final void setCenter(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLng);
    transactAndReadExceptionReturnVoid(3, parcel);
  }
  
  public final void setClickable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(19, parcel);
  }
  
  public final void setFillColor(int paramInt) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeInt(paramInt);
    transactAndReadExceptionReturnVoid(11, parcel);
  }
  
  public final void setRadius(double paramDouble) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeDouble(paramDouble);
    transactAndReadExceptionReturnVoid(5, parcel);
  }
  
  public final void setStrokeColor(int paramInt) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeInt(paramInt);
    transactAndReadExceptionReturnVoid(9, parcel);
  }
  
  public final void setStrokePattern(List<PatternItem> paramList) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeTypedList(paramList);
    transactAndReadExceptionReturnVoid(21, parcel);
  }
  
  public final void setStrokeWidth(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(7, parcel);
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
  
  public final boolean zzb(zzh paramzzh) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, paramzzh);
    Parcel parcel1 = transactAndReadException(17, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final void zze(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(23, parcel);
  }
  
  public final int zzi() throws RemoteException {
    Parcel parcel = transactAndReadException(18, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final IObjectWrapper zzj() throws RemoteException {
    Parcel parcel = transactAndReadException(24, obtainAndWriteInterfaceToken());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */