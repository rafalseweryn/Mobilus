package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzay;

final class zzh extends zzay {
  zzh(GoogleMap paramGoogleMap, GoogleMap.OnMyLocationChangeListener paramOnMyLocationChangeListener) {}
  
  public final void zza(IObjectWrapper paramIObjectWrapper) {
    this.zzp.onMyLocationChange((Location)ObjectWrapper.unwrap(paramIObjectWrapper));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\zzh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */