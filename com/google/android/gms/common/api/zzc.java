package com.google.android.gms.common.api;

import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

public abstract class zzc {
  private static final Object sLock;
  
  @GuardedBy("sLock")
  private static final Map<Object, zzc> zzdo = new WeakHashMap<>();
  
  static {
    sLock = new Object();
  }
  
  public abstract void remove(int paramInt);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\zzc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */