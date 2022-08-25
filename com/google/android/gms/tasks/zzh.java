package com.google.android.gms.tasks;

final class zzh implements Runnable {
  zzh(zzg paramzzg) {}
  
  public final void run() {
    synchronized (zzg.zza(this.zzafr)) {
      if (zzg.zzb(this.zzafr) != null)
        zzg.zzb(this.zzafr).onCanceled(); 
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */