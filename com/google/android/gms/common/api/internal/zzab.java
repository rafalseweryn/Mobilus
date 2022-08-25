package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

final class zzab implements PendingResult.StatusListener {
  zzab(zzaa paramzzaa, BasePendingResult paramBasePendingResult) {}
  
  public final void onComplete(Status paramStatus) {
    zzaa.zza(this.zzgz).remove(this.zzgy);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */