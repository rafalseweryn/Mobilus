package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.internal.zzba;

final class zzj extends zzba {
  zzj(GoogleMap paramGoogleMap, GoogleMap.OnMyLocationClickListener paramOnMyLocationClickListener) {}
  
  public final void onMyLocationClick(@NonNull Location paramLocation) throws RemoteException {
    this.zzr.onMyLocationClick(paramLocation);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\zzj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */