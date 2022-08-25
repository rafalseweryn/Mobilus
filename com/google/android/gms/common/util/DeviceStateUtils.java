package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import com.google.android.gms.internal.stable.zzg;

public final class DeviceStateUtils {
  private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
  
  private static long zzzw = 0L;
  
  private static float zzzx = NaNF;
  
  @TargetApi(20)
  public static int getDeviceState(Context paramContext) {
    if (paramContext != null) {
      int i;
      if (paramContext.getApplicationContext() == null)
        return -1; 
      Intent intent = paramContext.getApplicationContext().registerReceiver(null, filter);
      boolean bool = false;
      if (intent == null) {
        i = 0;
      } else {
        i = intent.getIntExtra("plugged", 0);
      } 
      if ((i & 0x7) != 0)
        bool = true; 
      PowerManager powerManager = (PowerManager)paramContext.getSystemService("power");
      return (powerManager == null) ? -1 : (isInteractive(powerManager) << 1 | bool);
    } 
    return -1;
  }
  
  public static float getPowerPercentage(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/util/DeviceStateUtils
    //   2: monitorenter
    //   3: invokestatic elapsedRealtime : ()J
    //   6: getstatic com/google/android/gms/common/util/DeviceStateUtils.zzzw : J
    //   9: lsub
    //   10: ldc2_w 60000
    //   13: lcmp
    //   14: ifge -> 35
    //   17: getstatic com/google/android/gms/common/util/DeviceStateUtils.zzzx : F
    //   20: invokestatic isNaN : (F)Z
    //   23: ifne -> 35
    //   26: getstatic com/google/android/gms/common/util/DeviceStateUtils.zzzx : F
    //   29: fstore_1
    //   30: ldc com/google/android/gms/common/util/DeviceStateUtils
    //   32: monitorexit
    //   33: fload_1
    //   34: freturn
    //   35: aload_0
    //   36: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   39: aconst_null
    //   40: getstatic com/google/android/gms/common/util/DeviceStateUtils.filter : Landroid/content/IntentFilter;
    //   43: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   46: astore_0
    //   47: aload_0
    //   48: ifnull -> 75
    //   51: aload_0
    //   52: ldc 'level'
    //   54: iconst_m1
    //   55: invokevirtual getIntExtra : (Ljava/lang/String;I)I
    //   58: istore_2
    //   59: aload_0
    //   60: ldc 'scale'
    //   62: iconst_m1
    //   63: invokevirtual getIntExtra : (Ljava/lang/String;I)I
    //   66: istore_3
    //   67: iload_2
    //   68: i2f
    //   69: iload_3
    //   70: i2f
    //   71: fdiv
    //   72: putstatic com/google/android/gms/common/util/DeviceStateUtils.zzzx : F
    //   75: invokestatic elapsedRealtime : ()J
    //   78: putstatic com/google/android/gms/common/util/DeviceStateUtils.zzzw : J
    //   81: getstatic com/google/android/gms/common/util/DeviceStateUtils.zzzx : F
    //   84: fstore_1
    //   85: ldc com/google/android/gms/common/util/DeviceStateUtils
    //   87: monitorexit
    //   88: fload_1
    //   89: freturn
    //   90: astore_0
    //   91: ldc com/google/android/gms/common/util/DeviceStateUtils
    //   93: monitorexit
    //   94: aload_0
    //   95: athrow
    // Exception table:
    //   from	to	target	type
    //   3	30	90	finally
    //   35	47	90	finally
    //   51	75	90	finally
    //   75	85	90	finally
  }
  
  public static boolean hasConsentedNlp(Context paramContext) {
    return (zzg.getInt(paramContext.getContentResolver(), "network_location_opt_in", -1) == 1);
  }
  
  public static boolean isCallActive(Context paramContext) {
    return (((AudioManager)paramContext.getSystemService("audio")).getMode() == 2);
  }
  
  public static boolean isInteractive(Context paramContext) {
    return isInteractive((PowerManager)paramContext.getSystemService("power"));
  }
  
  @TargetApi(20)
  public static boolean isInteractive(PowerManager paramPowerManager) {
    return PlatformVersion.isAtLeastKitKatWatch() ? paramPowerManager.isInteractive() : paramPowerManager.isScreenOn();
  }
  
  public static boolean isUserSetupComplete(Context paramContext) {
    return (Build.VERSION.SDK_INT >= 23) ? ((Settings.Secure.getInt(paramContext.getContentResolver(), "user_setup_complete", -1) == 1)) : true;
  }
  
  @VisibleForTesting
  public static void resetForTest() {
    // Byte code:
    //   0: ldc com/google/android/gms/common/util/DeviceStateUtils
    //   2: monitorenter
    //   3: lconst_0
    //   4: putstatic com/google/android/gms/common/util/DeviceStateUtils.zzzw : J
    //   7: ldc NaN
    //   9: putstatic com/google/android/gms/common/util/DeviceStateUtils.zzzx : F
    //   12: ldc com/google/android/gms/common/util/DeviceStateUtils
    //   14: monitorexit
    //   15: return
    //   16: astore_0
    //   17: ldc com/google/android/gms/common/util/DeviceStateUtils
    //   19: monitorexit
    //   20: aload_0
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   3	12	16	finally
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\DeviceStateUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */