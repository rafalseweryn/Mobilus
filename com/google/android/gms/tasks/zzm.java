package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzm<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzafk;
  
  @GuardedBy("mLock")
  private OnSuccessListener<? super TResult> zzafw;
  
  public zzm(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    this.zzafk = paramExecutor;
    this.zzafw = paramOnSuccessListener;
  }
  
  public final void cancel() {
    synchronized (this.mLock) {
      this.zzafw = null;
      return;
    } 
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    if (paramTask.isSuccessful())
      synchronized (this.mLock) {
        if (this.zzafw == null)
          return; 
        this.zzafk.execute(new zzn(this, paramTask));
        return;
      }  
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */