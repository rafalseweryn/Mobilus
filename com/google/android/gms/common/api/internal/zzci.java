package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

final class zzci implements Runnable {
  zzci(zzch paramzzch, Result paramResult) {}
  
  @WorkerThread
  public final void run() {
    Exception exception;
    try {
      BasePendingResult.zzez.set(Boolean.valueOf(true));
      PendingResult pendingResult = zzch.zzc(this.zzml).onSuccess(this.zzmk);
      zzch.zzd(this.zzml).sendMessage(zzch.zzd(this.zzml).obtainMessage(0, pendingResult));
      BasePendingResult.zzez.set(Boolean.valueOf(false));
      zzch.zza(this.zzml, this.zzmk);
      GoogleApiClient googleApiClient1 = zzch.zze(this.zzml).get();
      if (googleApiClient1 != null)
        googleApiClient1.zzb(this.zzml); 
      return;
    } catch (RuntimeException runtimeException) {
      zzch.zzd(this.zzml).sendMessage(zzch.zzd(this.zzml).obtainMessage(1, runtimeException));
      BasePendingResult.zzez.set(Boolean.valueOf(false));
      zzch.zza(this.zzml, this.zzmk);
      GoogleApiClient googleApiClient1 = zzch.zze(this.zzml).get();
      if (googleApiClient1 != null)
        googleApiClient1.zzb(this.zzml); 
      return;
    } finally {}
    BasePendingResult.zzez.set(Boolean.valueOf(false));
    zzch.zza(this.zzml, this.zzmk);
    GoogleApiClient googleApiClient = zzch.zze(this.zzml).get();
    if (googleApiClient != null)
      googleApiClient.zzb(this.zzml); 
    throw exception;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzci.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */