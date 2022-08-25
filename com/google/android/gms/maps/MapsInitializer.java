package com.google.android.gms.maps;

import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

public final class MapsInitializer {
  @GuardedBy("MapsInitializer.class")
  private static boolean zzbl = false;
  
  public static int initialize(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/maps/MapsInitializer
    //   2: monitorenter
    //   3: aload_0
    //   4: ldc 'Context is null'
    //   6: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   9: pop
    //   10: getstatic com/google/android/gms/maps/MapsInitializer.zzbl : Z
    //   13: istore_1
    //   14: iload_1
    //   15: ifeq -> 23
    //   18: ldc com/google/android/gms/maps/MapsInitializer
    //   20: monitorexit
    //   21: iconst_0
    //   22: ireturn
    //   23: aload_0
    //   24: invokestatic zza : (Landroid/content/Context;)Lcom/google/android/gms/maps/internal/zze;
    //   27: astore_0
    //   28: aload_0
    //   29: invokeinterface zzd : ()Lcom/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate;
    //   34: invokestatic zza : (Lcom/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate;)V
    //   37: aload_0
    //   38: invokeinterface zze : ()Lcom/google/android/gms/internal/maps/zze;
    //   43: invokestatic zza : (Lcom/google/android/gms/internal/maps/zze;)V
    //   46: iconst_1
    //   47: putstatic com/google/android/gms/maps/MapsInitializer.zzbl : Z
    //   50: ldc com/google/android/gms/maps/MapsInitializer
    //   52: monitorexit
    //   53: iconst_0
    //   54: ireturn
    //   55: astore_0
    //   56: new com/google/android/gms/maps/model/RuntimeRemoteException
    //   59: astore_2
    //   60: aload_2
    //   61: aload_0
    //   62: invokespecial <init> : (Landroid/os/RemoteException;)V
    //   65: aload_2
    //   66: athrow
    //   67: astore_0
    //   68: aload_0
    //   69: getfield errorCode : I
    //   72: istore_3
    //   73: ldc com/google/android/gms/maps/MapsInitializer
    //   75: monitorexit
    //   76: iload_3
    //   77: ireturn
    //   78: astore_0
    //   79: ldc com/google/android/gms/maps/MapsInitializer
    //   81: monitorexit
    //   82: aload_0
    //   83: athrow
    // Exception table:
    //   from	to	target	type
    //   3	14	78	finally
    //   23	28	67	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   23	28	78	finally
    //   28	46	55	android/os/RemoteException
    //   28	46	78	finally
    //   46	50	78	finally
    //   56	67	78	finally
    //   68	73	78	finally
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\MapsInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */