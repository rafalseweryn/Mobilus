package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzl extends zzb implements zzk {
  public static zzk zzd(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
    return (iInterface instanceof zzk) ? (zzk)iInterface : new zzm(paramIBinder);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */