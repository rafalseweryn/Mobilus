package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zzat implements Runnable {
  private zzat(zzaj paramzzaj) {}
  
  @WorkerThread
  public void run() {
    zzaj.zzc(this.zzhv).lock();
    try {
      boolean bool = Thread.interrupted();
      if (!bool)
        zzaq(); 
    } catch (RuntimeException runtimeException) {
    
    } finally {
      Exception exception;
    } 
    zzaj.zzc(this.zzhv).unlock();
  }
  
  @WorkerThread
  protected abstract void zzaq();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */