package com.google.android.gms.common.api;

final class zza implements PendingResult.StatusListener {
  zza(Batch paramBatch) {}
  
  public final void onComplete(Status paramStatus) {
    synchronized (Batch.zza(this.zzch)) {
      if (this.zzch.isCanceled())
        return; 
      if (paramStatus.isCanceled()) {
        Batch.zza(this.zzch, true);
      } else if (!paramStatus.isSuccess()) {
        Batch.zzb(this.zzch, true);
      } 
      Batch.zzb(this.zzch);
      if (Batch.zzc(this.zzch) == 0)
        if (Batch.zzd(this.zzch)) {
          Batch.zze(this.zzch);
        } else {
          if (Batch.zzf(this.zzch)) {
            paramStatus = new Status();
            this(13);
          } else {
            paramStatus = Status.RESULT_SUCCESS;
          } 
          Batch batch = this.zzch;
          BatchResult batchResult = new BatchResult();
          this(paramStatus, (PendingResult<?>[])Batch.zzg(this.zzch));
          batch.setResult(batchResult);
        }  
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */