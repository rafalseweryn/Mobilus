package com.google.android.gms.maps;

import android.location.Location;

public interface LocationSource {
  void activate(OnLocationChangedListener paramOnLocationChangedListener);
  
  void deactivate();
  
  public static interface OnLocationChangedListener {
    void onLocationChanged(Location param1Location);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\LocationSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */