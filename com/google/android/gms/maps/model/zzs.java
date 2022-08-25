package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzaf;

final class zzs implements TileProvider {
  private final zzaf zzek = TileOverlayOptions.zza(this.zzel);
  
  zzs(TileOverlayOptions paramTileOverlayOptions) {}
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3) {
    try {
      return this.zzek.getTile(paramInt1, paramInt2, paramInt3);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\zzs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */