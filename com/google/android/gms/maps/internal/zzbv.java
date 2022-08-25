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
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public final class zzbv extends zza implements IStreetViewPanoramaFragmentDelegate {
  zzbv(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
  }
  
  public final IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException {
    IInterface iInterface;
    Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iInterface = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
      if (iInterface instanceof IStreetViewPanoramaDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzbu(iBinder);
      } 
    } 
    parcel.recycle();
    return (IStreetViewPanoramaDelegate)iInterface;
  }
  
  public final void getStreetViewPanoramaAsync(zzbp paramzzbp) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbp);
    transactAndReadExceptionReturnVoid(12, parcel);
  }
  
  public final boolean isReady() throws RemoteException {
    Parcel parcel = transactAndReadException(11, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void onCreate(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    transactAndReadExceptionReturnVoid(3, parcel);
  }
  
  public final IObjectWrapper onCreateView(IObjectWrapper paramIObjectWrapper1, IObjectWrapper paramIObjectWrapper2, Bundle paramBundle) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper1);
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper2);
    zzc.zza(parcel2, (Parcelable)paramBundle);
    Parcel parcel1 = transactAndReadException(4, parcel2);
    paramIObjectWrapper1 = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return paramIObjectWrapper1;
  }
  
  public final void onDestroy() throws RemoteException {
    transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken());
  }
  
  public final void onDestroyView() throws RemoteException {
    transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken());
  }
  
  public final void onInflate(IObjectWrapper paramIObjectWrapper, StreetViewPanoramaOptions paramStreetViewPanoramaOptions, Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    zzc.zza(parcel, (Parcelable)paramStreetViewPanoramaOptions);
    zzc.zza(parcel, (Parcelable)paramBundle);
    transactAndReadExceptionReturnVoid(2, parcel);
  }
  
  public final void onLowMemory() throws RemoteException {
    transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken());
  }
  
  public final void onPause() throws RemoteException {
    transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
  }
  
  public final void onResume() throws RemoteException {
    transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    parcel = transactAndReadException(10, parcel);
    if (parcel.readInt() != 0)
      paramBundle.readFromParcel(parcel); 
    parcel.recycle();
  }
  
  public final void onStart() throws RemoteException {
    transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken());
  }
  
  public final void onStop() throws RemoteException {
    transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzbv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */