package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.zzah;

final class zzl extends ILocationSourceDelegate.zza {
  zzl(GoogleMap paramGoogleMap, LocationSource paramLocationSource) {}
  
  public final void activate(zzah paramzzah) {
    this.zzt.activate(new zzm(this, paramzzah));
  }
  
  public final void deactivate() {
    this.zzt.deactivate();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\zzl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */