package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzbs implements Runnable {
  zzbs(zzbr paramzzbr, LifecycleCallback paramLifecycleCallback, String paramString) {}
  
  public final void run() {
    if (zzbr.zza(this.zzlg) > 0) {
      Bundle bundle;
      LifecycleCallback lifecycleCallback = this.zzle;
      if (zzbr.zzb(this.zzlg) != null) {
        bundle = zzbr.zzb(this.zzlg).getBundle(this.zzlf);
      } else {
        bundle = null;
      } 
      lifecycleCallback.onCreate(bundle);
    } 
    if (zzbr.zza(this.zzlg) >= 2)
      this.zzle.onStart(); 
    if (zzbr.zza(this.zzlg) >= 3)
      this.zzle.onResume(); 
    if (zzbr.zza(this.zzlg) >= 4)
      this.zzle.onStop(); 
    if (zzbr.zza(this.zzlg) >= 5)
      this.zzle.onDestroy(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */