package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzg<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzafk;
  
  @GuardedBy("mLock")
  private OnCanceledListener zzafq;
  
  public zzg(@NonNull Executor paramExecutor, @NonNull OnCanceledListener paramOnCanceledListener) {
    this.zzafk = paramExecutor;
    this.zzafq = paramOnCanceledListener;
  }
  
  public final void cancel() {
    synchronized (this.mLock) {
      this.zzafq = null;
      return;
    } 
  }
  
  public final void onComplete(@NonNull Task paramTask) {
    if (paramTask.isCanceled())
      synchronized (this.mLock) {
        if (this.zzafq == null)
          return; 
        this.zzafk.execute(new zzh(this));
        return;
      }  
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */