package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

final class zzr<TResult> {
  private final Object mLock = new Object();
  
  @GuardedBy("mLock")
  private Queue<zzq<TResult>> zzaga;
  
  @GuardedBy("mLock")
  private boolean zzagb;
  
  public final void zza(@NonNull Task<TResult> paramTask) {
    synchronized (this.mLock) {
      if (this.zzaga == null || this.zzagb)
        return; 
      this.zzagb = true;
      while (true) {
        synchronized (this.mLock) {
          zzq<TResult> zzq = this.zzaga.poll();
          if (zzq == null) {
            this.zzagb = false;
            return;
          } 
          zzq.onComplete(paramTask);
        } 
      } 
    } 
  }
  
  public final void zza(@NonNull zzq<TResult> paramzzq) {
    synchronized (this.mLock) {
      if (this.zzaga == null) {
        ArrayDeque<zzq<TResult>> arrayDeque = new ArrayDeque();
        this();
        this.zzaga = arrayDeque;
      } 
      this.zzaga.add(paramzzq);
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */