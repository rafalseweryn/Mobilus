package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class zza extends ActivityLifecycleObserver {
  private final WeakReference<zza> zzds;
  
  public zza(Activity paramActivity) {
    this(zza.zzb(paramActivity));
  }
  
  @VisibleForTesting(otherwise = 2)
  private zza(zza paramzza) {
    this.zzds = new WeakReference<>(paramzza);
  }
  
  public final ActivityLifecycleObserver onStopCallOnce(Runnable paramRunnable) {
    zza zza1 = this.zzds.get();
    if (zza1 == null)
      throw new IllegalStateException("The target activity has already been GC'd"); 
    zza.zza(zza1, paramRunnable);
    return this;
  }
  
  @VisibleForTesting(otherwise = 2)
  static class zza extends LifecycleCallback {
    private List<Runnable> zzdt = new ArrayList<>();
    
    private zza(LifecycleFragment param1LifecycleFragment) {
      super(param1LifecycleFragment);
      this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
    }
    
    private static zza zza(Activity param1Activity) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: invokestatic getFragment : (Landroid/app/Activity;)Lcom/google/android/gms/common/api/internal/LifecycleFragment;
      //   6: astore_1
      //   7: aload_1
      //   8: ldc 'LifecycleObserverOnStop'
      //   10: ldc com/google/android/gms/common/api/internal/zza$zza
      //   12: invokeinterface getCallbackOrNull : (Ljava/lang/String;Ljava/lang/Class;)Lcom/google/android/gms/common/api/internal/LifecycleCallback;
      //   17: checkcast com/google/android/gms/common/api/internal/zza$zza
      //   20: astore_2
      //   21: aload_2
      //   22: astore_3
      //   23: aload_2
      //   24: ifnonnull -> 36
      //   27: new com/google/android/gms/common/api/internal/zza$zza
      //   30: astore_3
      //   31: aload_3
      //   32: aload_1
      //   33: invokespecial <init> : (Lcom/google/android/gms/common/api/internal/LifecycleFragment;)V
      //   36: aload_0
      //   37: monitorexit
      //   38: aload_3
      //   39: areturn
      //   40: astore_3
      //   41: aload_0
      //   42: monitorexit
      //   43: aload_3
      //   44: athrow
      // Exception table:
      //   from	to	target	type
      //   2	21	40	finally
      //   27	36	40	finally
      //   36	38	40	finally
      //   41	43	40	finally
    }
    
    private final void zza(Runnable param1Runnable) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield zzdt : Ljava/util/List;
      //   6: aload_1
      //   7: invokeinterface add : (Ljava/lang/Object;)Z
      //   12: pop
      //   13: aload_0
      //   14: monitorexit
      //   15: return
      //   16: astore_1
      //   17: aload_0
      //   18: monitorexit
      //   19: aload_1
      //   20: athrow
      // Exception table:
      //   from	to	target	type
      //   2	13	16	finally
    }
    
    @MainThread
    public void onStop() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield zzdt : Ljava/util/List;
      //   6: astore_1
      //   7: new java/util/ArrayList
      //   10: astore_2
      //   11: aload_2
      //   12: invokespecial <init> : ()V
      //   15: aload_0
      //   16: aload_2
      //   17: putfield zzdt : Ljava/util/List;
      //   20: aload_0
      //   21: monitorexit
      //   22: aload_1
      //   23: invokeinterface iterator : ()Ljava/util/Iterator;
      //   28: astore_1
      //   29: aload_1
      //   30: invokeinterface hasNext : ()Z
      //   35: ifeq -> 55
      //   38: aload_1
      //   39: invokeinterface next : ()Ljava/lang/Object;
      //   44: checkcast java/lang/Runnable
      //   47: invokeinterface run : ()V
      //   52: goto -> 29
      //   55: return
      //   56: astore_1
      //   57: aload_0
      //   58: monitorexit
      //   59: aload_1
      //   60: athrow
      // Exception table:
      //   from	to	target	type
      //   2	22	56	finally
      //   57	59	56	finally
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */