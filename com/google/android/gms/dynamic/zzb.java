package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

final class zzb implements DeferredLifecycleHelper.zza {
  zzb(DeferredLifecycleHelper paramDeferredLifecycleHelper, Activity paramActivity, Bundle paramBundle1) {}
  
  public final int getState() {
    return 0;
  }
  
  public final void zza(LifecycleDelegate paramLifecycleDelegate) {
    DeferredLifecycleHelper.zzb(this.zzabg).onInflate(activity, this.zzabh, this.zzabi);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */