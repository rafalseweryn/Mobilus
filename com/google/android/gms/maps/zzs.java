package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;

final class zzs extends zzbc {
  zzs(GoogleMap paramGoogleMap, GoogleMap.OnPoiClickListener paramOnPoiClickListener) {}
  
  public final void zza(PointOfInterest paramPointOfInterest) throws RemoteException {
    this.zzaa.onPoiClick(paramPointOfInterest);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\zzs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */