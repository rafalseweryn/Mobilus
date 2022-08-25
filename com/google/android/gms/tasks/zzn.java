package com.google.android.gms.tasks;

final class zzn implements Runnable {
  zzn(zzm paramzzm, Task paramTask) {}
  
  public final void run() {
    synchronized (zzm.zza(this.zzafx)) {
      if (zzm.zzb(this.zzafx) != null)
        zzm.zzb(this.zzafx).onSuccess(this.zzafn.getResult()); 
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */