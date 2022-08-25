package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

final class zzp implements Runnable {
  zzp(zzo paramzzo, Task paramTask) {}
  
  public final void run() {
    try {
      Task task = zzo.zza(this.zzafz).then(this.zzafn.getResult());
      if (task == null) {
        this.zzafz.onFailure(new NullPointerException("Continuation returned null"));
        return;
      } 
      task.addOnSuccessListener(TaskExecutors.zzagd, this.zzafz);
      task.addOnFailureListener(TaskExecutors.zzagd, this.zzafz);
      task.addOnCanceledListener(TaskExecutors.zzagd, this.zzafz);
      return;
    } catch (RuntimeExecutionException runtimeExecutionException) {
      if (runtimeExecutionException.getCause() instanceof Exception) {
        this.zzafz.onFailure((Exception)runtimeExecutionException.getCause());
        return;
      } 
      this.zzafz.onFailure(runtimeExecutionException);
      return;
    } catch (CancellationException cancellationException) {
      this.zzafz.onCanceled();
      return;
    } catch (Exception exception) {
      this.zzafz.onFailure(exception);
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */