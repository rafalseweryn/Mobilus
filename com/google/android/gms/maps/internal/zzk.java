package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzk extends zza implements IMapViewDelegate {
  zzk(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
  }
  
  public final IGoogleMapDelegate getMap() throws RemoteException {
    IGoogleMapDelegate iGoogleMapDelegate;
    Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
      if (iInterface instanceof IGoogleMapDelegate) {
        iGoogleMapDelegate = (IGoogleMapDelegate)iInterface;
      } else {
        iGoogleMapDelegate = new zzg((IBinder)iGoogleMapDelegate);
      } 
    } 
    parcel.recycle();
    return iGoogleMapDelegate;
  }
  
  public final void getMapAsync(zzap paramzzap) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzap);
    transactAndReadExceptionReturnVoid(9, parcel);
  }
  
  public final IObjectWrapper getView() throws RemoteException {
    Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final void onCreate(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    transactAndReadExceptionReturnVoid(2, parcel);
  }
  
  public final void onDestroy() throws RemoteException {
    transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken());
  }
  
  public final void onEnterAmbient(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    transactAndReadExceptionReturnVoid(10, parcel);
  }
  
  public final void onExitAmbient() throws RemoteException {
    transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken());
  }
  
  public final void onLowMemory() throws RemoteException {
    transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
  }
  
  public final void onPause() throws RemoteException {
    transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken());
  }
  
  public final void onResume() throws RemoteException {
    transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    parcel = transactAndReadException(7, parcel);
    if (parcel.readInt() != 0)
      paramBundle.readFromParcel(parcel); 
    parcel.recycle();
  }
  
  public final void onStart() throws RemoteException {
    transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken());
  }
  
  public final void onStop() throws RemoteException {
    transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */