package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzk<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzafk;
  
  @GuardedBy("mLock")
  private OnFailureListener zzafu;
  
  public zzk(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener) {
    this.zzafk = paramExecutor;
    this.zzafu = paramOnFailureListener;
  }
  
  public final void cancel() {
    synchronized (this.mLock) {
      this.zzafu = null;
      return;
    } 
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    if (!paramTask.isSuccessful() && !paramTask.isCanceled())
      synchronized (this.mLock) {
        if (this.zzafu == null)
          return; 
        this.zzafk.execute(new zzl(this, paramTask));
        return;
      }  
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */