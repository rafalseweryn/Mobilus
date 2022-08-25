package com.google.android.gms.common.util;

import android.os.SystemClock;

public class DefaultClock implements Clock {
  private static final DefaultClock zzzk = new DefaultClock();
  
  public static Clock getInstance() {
    return zzzk;
  }
  
  public long currentThreadTimeMillis() {
    return SystemClock.currentThreadTimeMillis();
  }
  
  public long currentTimeMillis() {
    return System.currentTimeMillis();
  }
  
  public long elapsedRealtime() {
    return SystemClock.elapsedRealtime();
  }
  
  public long nanoTime() {
    return System.nanoTime();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\DefaultClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */