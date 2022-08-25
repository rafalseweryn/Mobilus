package com.google.android.gms.tasks;

final class zzj implements Runnable {
  zzj(zzi paramzzi, Task paramTask) {}
  
  public final void run() {
    synchronized (zzi.zza(this.zzaft)) {
      if (zzi.zzb(this.zzaft) != null)
        zzi.zzb(this.zzaft).onComplete(this.zzafn); 
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */