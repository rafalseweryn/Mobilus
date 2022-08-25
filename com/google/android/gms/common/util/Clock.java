package com.google.android.gms.common.util;

public interface Clock {
  long currentThreadTimeMillis();
  
  long currentTimeMillis();
  
  long elapsedRealtime();
  
  long nanoTime();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\Clock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */