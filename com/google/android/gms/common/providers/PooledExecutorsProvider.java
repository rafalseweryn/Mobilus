package com.google.android.gms.common.providers;

import java.util.concurrent.ScheduledExecutorService;

public class PooledExecutorsProvider {
  private static PooledExecutorFactory zzvs;
  
  public static PooledExecutorFactory createDefaultFactory() {
    return new zza();
  }
  
  public static PooledExecutorFactory getInstance() {
    // Byte code:
    //   0: ldc com/google/android/gms/common/providers/PooledExecutorsProvider
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/common/providers/PooledExecutorsProvider.zzvs : Lcom/google/android/gms/common/providers/PooledExecutorsProvider$PooledExecutorFactory;
    //   6: ifnonnull -> 15
    //   9: invokestatic createDefaultFactory : ()Lcom/google/android/gms/common/providers/PooledExecutorsProvider$PooledExecutorFactory;
    //   12: putstatic com/google/android/gms/common/providers/PooledExecutorsProvider.zzvs : Lcom/google/android/gms/common/providers/PooledExecutorsProvider$PooledExecutorFactory;
    //   15: getstatic com/google/android/gms/common/providers/PooledExecutorsProvider.zzvs : Lcom/google/android/gms/common/providers/PooledExecutorsProvider$PooledExecutorFactory;
    //   18: astore_0
    //   19: ldc com/google/android/gms/common/providers/PooledExecutorsProvider
    //   21: monitorexit
    //   22: aload_0
    //   23: areturn
    //   24: astore_0
    //   25: ldc com/google/android/gms/common/providers/PooledExecutorsProvider
    //   27: monitorexit
    //   28: aload_0
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   3	15	24	finally
    //   15	19	24	finally
  }
  
  public static void setInstance(PooledExecutorFactory paramPooledExecutorFactory) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/providers/PooledExecutorsProvider
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/common/providers/PooledExecutorsProvider.zzvs : Lcom/google/android/gms/common/providers/PooledExecutorsProvider$PooledExecutorFactory;
    //   6: ifnull -> 17
    //   9: ldc 'PooledExecutorsProvider'
    //   11: ldc 'setInstance called when instance was already set.'
    //   13: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: aload_0
    //   18: putstatic com/google/android/gms/common/providers/PooledExecutorsProvider.zzvs : Lcom/google/android/gms/common/providers/PooledExecutorsProvider$PooledExecutorFactory;
    //   21: ldc com/google/android/gms/common/providers/PooledExecutorsProvider
    //   23: monitorexit
    //   24: return
    //   25: astore_0
    //   26: ldc com/google/android/gms/common/providers/PooledExecutorsProvider
    //   28: monitorexit
    //   29: aload_0
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   3	17	25	finally
    //   17	21	25	finally
  }
  
  public static interface PooledExecutorFactory {
    ScheduledExecutorService newSingleThreadScheduledExecutor();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\providers\PooledExecutorsProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */