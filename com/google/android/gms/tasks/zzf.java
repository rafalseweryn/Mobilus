package com.google.android.gms.tasks;

final class zzf implements Runnable {
  zzf(zze paramzze, Task paramTask) {}
  
  public final void run() {
    try {
      Task task = (Task)zze.zza(this.zzafp).then(this.zzafn);
      if (task == null) {
        this.zzafp.onFailure(new NullPointerException("Continuation returned null"));
        return;
      } 
      task.addOnSuccessListener(TaskExecutors.zzagd, this.zzafp);
      task.addOnFailureListener(TaskExecutors.zzagd, this.zzafp);
      task.addOnCanceledListener(TaskExecutors.zzagd, this.zzafp);
      return;
    } catch (RuntimeExecutionException runtimeExecutionException) {
      if (runtimeExecutionException.getCause() instanceof Exception) {
        zze.zzb(this.zzafp).setException((Exception)runtimeExecutionException.getCause());
        return;
      } 
      zze.zzb(this.zzafp).setException(runtimeExecutionException);
      return;
    } catch (Exception exception) {
      zze.zzb(this.zzafp).setException(exception);
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */