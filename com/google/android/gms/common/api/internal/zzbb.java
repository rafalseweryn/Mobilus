package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zzbb extends GooglePlayServicesUpdatedReceiver.Callback {
  private WeakReference<zzav> zziy;
  
  zzbb(zzav paramzzav) {
    this.zziy = new WeakReference<>(paramzzav);
  }
  
  public final void zzv() {
    zzav zzav = this.zziy.get();
    if (zzav == null)
      return; 
    zzav.zza(zzav);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */