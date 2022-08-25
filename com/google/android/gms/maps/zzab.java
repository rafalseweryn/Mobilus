package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzab extends zzaq {
  zzab(MapFragment.zza paramzza, OnMapReadyCallback paramOnMapReadyCallback) {}
  
  public final void zza(IGoogleMapDelegate paramIGoogleMapDelegate) throws RemoteException {
    this.zzbb.onMapReady(new GoogleMap(paramIGoogleMapDelegate));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\zzab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */