package com.google.android.gms.maps;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class CameraUpdate {
  private final IObjectWrapper zze;
  
  CameraUpdate(IObjectWrapper paramIObjectWrapper) {
    this.zze = (IObjectWrapper)Preconditions.checkNotNull(paramIObjectWrapper);
  }
  
  public final IObjectWrapper zza() {
    return this.zze;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\CameraUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */