package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzab extends zza implements zzz {
  zzab(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
  }
  
  public final int getColor() throws RemoteException {
    Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final Cap getEndCap() throws RemoteException {
    Parcel parcel = transactAndReadException(22, obtainAndWriteInterfaceToken());
    Cap cap = zzc.<Cap>zza(parcel, Cap.CREATOR);
    parcel.recycle();
    return cap;
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final int getJointType() throws RemoteException {
    Parcel parcel = transactAndReadException(24, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final List<PatternItem> getPattern() throws RemoteException {
    Parcel parcel = transactAndReadException(26, obtainAndWriteInterfaceToken());
    ArrayList<PatternItem> arrayList = parcel.createTypedArrayList(PatternItem.CREATOR);
    parcel.recycle();
    return arrayList;
  }
  
  public final List<LatLng> getPoints() throws RemoteException {
    Parcel parcel = transactAndReadException(4, obtainAndWriteInterfaceToken());
    ArrayList<LatLng> arrayList = parcel.createTypedArrayList(LatLng.CREATOR);
    parcel.recycle();
    return arrayList;
  }
  
  public final Cap getStartCap() throws RemoteException {
    Parcel parcel = transactAndReadException(20, obtainAndWriteInterfaceToken());
    Cap cap = zzc.<Cap>zza(parcel, Cap.CREATOR);
    parcel.recycle();
    return cap;
  }
  
  public final float getWidth() throws RemoteException {
    Parcel parcel = transactAndReadException(6, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getZIndex() throws RemoteException {
    Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final boolean isClickable() throws RemoteException {
    Parcel parcel = transactAndReadException(18, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isGeodesic() throws RemoteException {
    Parcel parcel = transactAndReadException(14, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isVisible() throws RemoteException {
    Parcel parcel = transactAndReadException(12, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void remove() throws RemoteException {
    transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
  }
  
  public final void setClickable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(17, parcel);
  }
  
  public final void setColor(int paramInt) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeInt(paramInt);
    transactAndReadExceptionReturnVoid(7, parcel);
  }
  
  public final void setEndCap(Cap paramCap) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramCap);
    transactAndReadExceptionReturnVoid(21, parcel);
  }
  
  public final void setGeodesic(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(13, parcel);
  }
  
  public final void setJointType(int paramInt) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeInt(paramInt);
    transactAndReadExceptionReturnVoid(23, parcel);
  }
  
  public final void setPattern(List<PatternItem> paramList) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeTypedList(paramList);
    transactAndReadExceptionReturnVoid(25, parcel);
  }
  
  public final void setPoints(List<LatLng> paramList) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeTypedList(paramList);
    transactAndReadExceptionReturnVoid(3, parcel);
  }
  
  public final void setStartCap(Cap paramCap) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramCap);
    transactAndReadExceptionReturnVoid(19, parcel);
  }
  
  public final void setVisible(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(11, parcel);
  }
  
  public final void setWidth(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(5, parcel);
  }
  
  public final void setZIndex(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(9, parcel);
  }
  
  public final boolean zzb(zzz paramzzz) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, paramzzz);
    Parcel parcel1 = transactAndReadException(15, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final void zze(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(27, parcel);
  }
  
  public final int zzi() throws RemoteException {
    Parcel parcel = transactAndReadException(16, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final IObjectWrapper zzj() throws RemoteException {
    Parcel parcel = transactAndReadException(28, obtainAndWriteInterfaceToken());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */