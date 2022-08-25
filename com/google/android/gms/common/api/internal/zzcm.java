package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.zzc;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zzcm implements IBinder.DeathRecipient, zzcn {
  private final WeakReference<BasePendingResult<?>> zzmr;
  
  private final WeakReference<zzc> zzms;
  
  private final WeakReference<IBinder> zzmt;
  
  private zzcm(BasePendingResult<?> paramBasePendingResult, zzc paramzzc, IBinder paramIBinder) {
    this.zzms = new WeakReference<>(paramzzc);
    this.zzmr = new WeakReference<>(paramBasePendingResult);
    this.zzmt = new WeakReference<>(paramIBinder);
  }
  
  private final void zzcf() {
    BasePendingResult basePendingResult = this.zzmr.get();
    zzc zzc = this.zzms.get();
    if (zzc != null && basePendingResult != null)
      zzc.remove(basePendingResult.zzo().intValue()); 
    IBinder iBinder = this.zzmt.get();
    if (iBinder != null)
      try {
        iBinder.unlinkToDeath(this, 0);
      } catch (NoSuchElementException noSuchElementException) {} 
  }
  
  public final void binderDied() {
    zzcf();
  }
  
  public final void zzc(BasePendingResult<?> paramBasePendingResult) {
    zzcf();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzcm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */