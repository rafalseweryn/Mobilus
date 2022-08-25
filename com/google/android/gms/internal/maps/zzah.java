package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

public final class zzah extends zza implements zzaf {
  zzah(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
  }
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = obtainAndWriteInterfaceToken();
    parcel1.writeInt(paramInt1);
    parcel1.writeInt(paramInt2);
    parcel1.writeInt(paramInt3);
    Parcel parcel2 = transactAndReadException(1, parcel1);
    Tile tile = zzc.<Tile>zza(parcel2, Tile.CREATOR);
    parcel2.recycle();
    return tile;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzah.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */