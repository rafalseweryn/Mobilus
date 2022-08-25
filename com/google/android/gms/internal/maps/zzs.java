package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzs extends zza implements zzq {
  zzs(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
  }
  
  public final void activate() throws RemoteException {
    transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
  }
  
  public final String getName() throws RemoteException {
    Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final String getShortName() throws RemoteException {
    Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final boolean zzb(zzq paramzzq) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, paramzzq);
    Parcel parcel1 = transactAndReadException(4, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final int zzi() throws RemoteException {
    Parcel parcel = transactAndReadException(5, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */