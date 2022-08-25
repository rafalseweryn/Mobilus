package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzcd implements Runnable {
  zzcd(zzcc paramzzcc, LifecycleCallback paramLifecycleCallback, String paramString) {}
  
  public final void run() {
    if (zzcc.zza(this.zzly) > 0) {
      Bundle bundle;
      LifecycleCallback lifecycleCallback = this.zzle;
      if (zzcc.zzb(this.zzly) != null) {
        bundle = zzcc.zzb(this.zzly).getBundle(this.zzlf);
      } else {
        bundle = null;
      } 
      lifecycleCallback.onCreate(bundle);
    } 
    if (zzcc.zza(this.zzly) >= 2)
      this.zzle.onStart(); 
    if (zzcc.zza(this.zzly) >= 3)
      this.zzle.onResume(); 
    if (zzcc.zza(this.zzly) >= 4)
      this.zzle.onStop(); 
    if (zzcc.zza(this.zzly) >= 5)
      this.zzle.onDestroy(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzcd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */