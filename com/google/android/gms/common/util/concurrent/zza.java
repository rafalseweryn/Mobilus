package com.google.android.gms.common.util.concurrent;

import android.os.Process;

final class zza implements Runnable {
  private final int priority;
  
  private final Runnable zzaax;
  
  public zza(Runnable paramRunnable, int paramInt) {
    this.zzaax = paramRunnable;
    this.priority = paramInt;
  }
  
  public final void run() {
    Process.setThreadPriority(this.priority);
    this.zzaax.run();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\concurrent\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */