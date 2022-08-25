package com.google.android.gms.tasks;

final class zzd implements Runnable {
  zzd(zzc paramzzc, Task paramTask) {}
  
  public final void run() {
    if (this.zzafn.isCanceled()) {
      zzc.zza(this.zzafo).zzdp();
      return;
    } 
    try {
      Object object = zzc.zzb(this.zzafo).then(this.zzafn);
      zzc.zza(this.zzafo).setResult(object);
      return;
    } catch (RuntimeExecutionException runtimeExecutionException) {
      if (runtimeExecutionException.getCause() instanceof Exception) {
        zzc.zza(this.zzafo).setException((Exception)runtimeExecutionException.getCause());
        return;
      } 
      zzc.zza(this.zzafo).setException(runtimeExecutionException);
      return;
    } catch (Exception exception) {
      zzc.zza(this.zzafo).setException(exception);
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */