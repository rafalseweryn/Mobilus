package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public final class zzp extends zza implements zzn {
  zzp(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
  }
  
  public final int getActiveLevelIndex() throws RemoteException {
    Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final int getDefaultLevelIndex() throws RemoteException {
    Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final List<IBinder> getLevels() throws RemoteException {
    Parcel parcel = transactAndReadException(3, obtainAndWriteInterfaceToken());
    ArrayList<IBinder> arrayList = parcel.createBinderArrayList();
    parcel.recycle();
    return arrayList;
  }
  
  public final boolean isUnderground() throws RemoteException {
    Parcel parcel = transactAndReadException(4, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean zzb(zzn paramzzn) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, paramzzn);
    Parcel parcel1 = transactAndReadException(5, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final int zzi() throws RemoteException {
    Parcel parcel = transactAndReadException(6, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */