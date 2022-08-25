package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zzt implements zzbq {
  private zzt(zzr paramzzr) {}
  
  public final void zzb(int paramInt, boolean paramBoolean) {
    zzr.zza(this.zzgc).lock();
    try {
      if (zzr.zzc(this.zzgc) || zzr.zzd(this.zzgc) == null || !zzr.zzd(this.zzgc).isSuccess()) {
        zzr.zza(this.zzgc, false);
        zzr.zza(this.zzgc, paramInt, paramBoolean);
      } else {
        zzr.zza(this.zzgc, true);
        zzr.zze(this.zzgc).onConnectionSuspended(paramInt);
      } 
      return;
    } finally {
      zzr.zza(this.zzgc).unlock();
    } 
  }
  
  public final void zzb(@Nullable Bundle paramBundle) {
    zzr.zza(this.zzgc).lock();
    try {
      zzr.zza(this.zzgc, paramBundle);
      zzr.zza(this.zzgc, ConnectionResult.RESULT_SUCCESS);
      zzr.zzb(this.zzgc);
      return;
    } finally {
      zzr.zza(this.zzgc).unlock();
    } 
  }
  
  public final void zzc(@NonNull ConnectionResult paramConnectionResult) {
    zzr.zza(this.zzgc).lock();
    try {
      zzr.zza(this.zzgc, paramConnectionResult);
      zzr.zzb(this.zzgc);
      return;
    } finally {
      zzr.zza(this.zzgc).unlock();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */