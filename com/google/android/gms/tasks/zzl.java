package com.google.android.gms.tasks;

final class zzl implements Runnable {
  zzl(zzk paramzzk, Task paramTask) {}
  
  public final void run() {
    synchronized (zzk.zza(this.zzafv)) {
      if (zzk.zzb(this.zzafv) != null)
        zzk.zzb(this.zzafv).onFailure(this.zzafn.getException()); 
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */