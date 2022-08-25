package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

final class zzbn implements Runnable {
  zzbn(GoogleApiManager.zzc paramzzc, ConnectionResult paramConnectionResult) {}
  
  public final void run() {
    if (this.zzkl.isSuccess()) {
      GoogleApiManager.zzc.zza(this.zzkr, true);
      if (GoogleApiManager.zzc.zza(this.zzkr).requiresSignIn()) {
        GoogleApiManager.zzc.zzb(this.zzkr);
        return;
      } 
      GoogleApiManager.zzc.zza(this.zzkr).getRemoteService(null, Collections.emptySet());
      return;
    } 
    ((GoogleApiManager.zza)GoogleApiManager.zzj(this.zzkr.zzjy).get(GoogleApiManager.zzc.zzc(this.zzkr))).onConnectionFailed(this.zzkl);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */