package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzi<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzafk;
  
  @GuardedBy("mLock")
  private OnCompleteListener<TResult> zzafs;
  
  public zzi(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    this.zzafk = paramExecutor;
    this.zzafs = paramOnCompleteListener;
  }
  
  public final void cancel() {
    synchronized (this.mLock) {
      this.zzafs = null;
      return;
    } 
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    synchronized (this.mLock) {
      if (this.zzafs == null)
        return; 
      this.zzafk.execute(new zzj(this, paramTask));
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */