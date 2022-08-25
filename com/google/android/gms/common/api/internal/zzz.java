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

final class zzz implements OnCompleteListener<Map<zzh<?>, String>> {
  private SignInConnectionListener zzgv;
  
  zzz(zzw paramzzw, SignInConnectionListener paramSignInConnectionListener) {
    this.zzgv = paramSignInConnectionListener;
  }
  
  final void cancel() {
    this.zzgv.onComplete();
  }
  
  public final void onComplete(@NonNull Task<Map<zzh<?>, String>> paramTask) {
    zzw.zza(this.zzgu).lock();
    try {
      SignInConnectionListener signInConnectionListener;
      if (!zzw.zzb(this.zzgu)) {
        signInConnectionListener = this.zzgv;
      } else {
        if (signInConnectionListener.isSuccessful()) {
          zzw zzw1 = this.zzgu;
          ArrayMap arrayMap = new ArrayMap();
          this(zzw.zzm(this.zzgu).size());
          zzw.zzb(zzw1, (Map)arrayMap);
          for (zzv zzv : zzw.zzm(this.zzgu).values())
            zzw.zzg(this.zzgu).put(zzv.zzm(), ConnectionResult.RESULT_SUCCESS); 
        } else {
          ConnectionResult connectionResult;
          if (signInConnectionListener.getException() instanceof AvailabilityException) {
            AvailabilityException availabilityException = (AvailabilityException)signInConnectionListener.getException();
            if (zzw.zze(this.zzgu)) {
              zzw zzw1 = this.zzgu;
              ArrayMap arrayMap = new ArrayMap();
              this(zzw.zzm(this.zzgu).size());
              zzw.zzb(zzw1, (Map)arrayMap);
              for (zzv zzv : zzw.zzm(this.zzgu).values()) {
                Map<zzh, ConnectionResult> map;
                zzh zzh = zzv.zzm();
                connectionResult = availabilityException.getConnectionResult(zzv);
                if (zzw.zza(this.zzgu, zzv, connectionResult)) {
                  map = zzw.zzg(this.zzgu);
                  connectionResult = new ConnectionResult();
                  this(16);
                } else {
                  map = zzw.zzg(this.zzgu);
                } 
                map.put(zzh, connectionResult);
              } 
            } else {
              zzw.zzb(this.zzgu, (Map)availabilityException.zzl());
            } 
          } else {
            Log.e("ConnectionlessGAC", "Unexpected availability exception", connectionResult.getException());
            zzw.zzb(this.zzgu, Collections.emptyMap());
          } 
        } 
        if (this.zzgu.isConnected()) {
          zzw.zzd(this.zzgu).putAll(zzw.zzg(this.zzgu));
          if (zzw.zzf(this.zzgu) == null) {
            zzw.zzi(this.zzgu);
            zzw.zzj(this.zzgu);
            zzw.zzl(this.zzgu).signalAll();
          } 
        } 
        signInConnectionListener = this.zzgv;
      } 
      signInConnectionListener.onComplete();
      return;
    } finally {
      zzw.zza(this.zzgu).unlock();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */