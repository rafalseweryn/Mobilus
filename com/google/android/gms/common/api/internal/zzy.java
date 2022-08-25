package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

final class zzy implements OnCompleteListener<Map<zzh<?>, String>> {
  private zzy(zzw paramzzw) {}
  
  public final void onComplete(@NonNull Task<Map<zzh<?>, String>> paramTask) {
    zzw.zza(this.zzgu).lock();
    try {
      boolean bool = zzw.zzb(this.zzgu);
      if (bool) {
        if (paramTask.isSuccessful()) {
          zzw zzw1 = this.zzgu;
          ArrayMap arrayMap = new ArrayMap();
          this(zzw.zzc(this.zzgu).size());
          zzw.zza(zzw1, (Map)arrayMap);
          for (zzv zzv : zzw.zzc(this.zzgu).values())
            zzw.zzd(this.zzgu).put(zzv.zzm(), ConnectionResult.RESULT_SUCCESS); 
        } else {
          zzw zzw1;
          ConnectionResult connectionResult;
          if (zzv.getException() instanceof AvailabilityException) {
            AvailabilityException availabilityException = (AvailabilityException)zzv.getException();
            if (zzw.zze(this.zzgu)) {
              zzw zzw2 = this.zzgu;
              ArrayMap arrayMap = new ArrayMap();
              this(zzw.zzc(this.zzgu).size());
              zzw.zza(zzw2, (Map)arrayMap);
              for (zzv zzv1 : zzw.zzc(this.zzgu).values()) {
                Map<zzh, ConnectionResult> map;
                zzh zzh = zzv1.zzm();
                ConnectionResult connectionResult1 = availabilityException.getConnectionResult(zzv1);
                if (zzw.zza(this.zzgu, zzv1, connectionResult1)) {
                  map = zzw.zzd(this.zzgu);
                  connectionResult1 = new ConnectionResult();
                  this(16);
                } else {
                  map = zzw.zzd(this.zzgu);
                } 
                map.put(zzh, connectionResult1);
              } 
            } else {
              zzw.zza(this.zzgu, (Map)availabilityException.zzl());
            } 
            zzw1 = this.zzgu;
            connectionResult = zzw.zzf(this.zzgu);
          } else {
            Log.e("ConnectionlessGAC", "Unexpected availability exception", zzw1.getException());
            zzw.zza(this.zzgu, Collections.emptyMap());
            zzw1 = this.zzgu;
            connectionResult = new ConnectionResult(8);
          } 
          zzw.zza(zzw1, connectionResult);
        } 
        if (zzw.zzg(this.zzgu) != null) {
          zzw.zzd(this.zzgu).putAll(zzw.zzg(this.zzgu));
          zzw.zza(this.zzgu, zzw.zzf(this.zzgu));
        } 
        if (zzw.zzh(this.zzgu) == null) {
          zzw.zzi(this.zzgu);
          zzw.zzj(this.zzgu);
        } else {
          zzw.zza(this.zzgu, false);
          zzw.zzk(this.zzgu).zzc(zzw.zzh(this.zzgu));
        } 
        zzw.zzl(this.zzgu).signalAll();
      } 
      return;
    } finally {
      zzw.zza(this.zzgu).unlock();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */