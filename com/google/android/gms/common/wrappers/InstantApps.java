package com.google.android.gms.common.wrappers;

import android.content.Context;

public class InstantApps {
  private static Context zzaay;
  
  private static Boolean zzaaz;
  
  public static boolean isInstantApp(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/wrappers/InstantApps
    //   2: monitorenter
    //   3: aload_0
    //   4: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   7: astore_1
    //   8: getstatic com/google/android/gms/common/wrappers/InstantApps.zzaay : Landroid/content/Context;
    //   11: ifnull -> 39
    //   14: getstatic com/google/android/gms/common/wrappers/InstantApps.zzaaz : Ljava/lang/Boolean;
    //   17: ifnull -> 39
    //   20: getstatic com/google/android/gms/common/wrappers/InstantApps.zzaay : Landroid/content/Context;
    //   23: aload_1
    //   24: if_acmpne -> 39
    //   27: getstatic com/google/android/gms/common/wrappers/InstantApps.zzaaz : Ljava/lang/Boolean;
    //   30: invokevirtual booleanValue : ()Z
    //   33: istore_2
    //   34: ldc com/google/android/gms/common/wrappers/InstantApps
    //   36: monitorexit
    //   37: iload_2
    //   38: ireturn
    //   39: aconst_null
    //   40: putstatic com/google/android/gms/common/wrappers/InstantApps.zzaaz : Ljava/lang/Boolean;
    //   43: invokestatic isAtLeastO : ()Z
    //   46: ifeq -> 67
    //   49: aload_1
    //   50: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   53: invokevirtual isInstantApp : ()Z
    //   56: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   59: astore_0
    //   60: aload_0
    //   61: putstatic com/google/android/gms/common/wrappers/InstantApps.zzaaz : Ljava/lang/Boolean;
    //   64: goto -> 96
    //   67: aload_0
    //   68: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   71: ldc 'com.google.android.instantapps.supervisor.InstantAppsRuntime'
    //   73: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   76: pop
    //   77: iconst_1
    //   78: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   81: putstatic com/google/android/gms/common/wrappers/InstantApps.zzaaz : Ljava/lang/Boolean;
    //   84: goto -> 96
    //   87: astore_0
    //   88: iconst_0
    //   89: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   92: astore_0
    //   93: goto -> 60
    //   96: aload_1
    //   97: putstatic com/google/android/gms/common/wrappers/InstantApps.zzaay : Landroid/content/Context;
    //   100: getstatic com/google/android/gms/common/wrappers/InstantApps.zzaaz : Ljava/lang/Boolean;
    //   103: invokevirtual booleanValue : ()Z
    //   106: istore_2
    //   107: ldc com/google/android/gms/common/wrappers/InstantApps
    //   109: monitorexit
    //   110: iload_2
    //   111: ireturn
    //   112: astore_0
    //   113: ldc com/google/android/gms/common/wrappers/InstantApps
    //   115: monitorexit
    //   116: aload_0
    //   117: athrow
    // Exception table:
    //   from	to	target	type
    //   3	34	112	finally
    //   39	60	112	finally
    //   60	64	112	finally
    //   67	84	87	java/lang/ClassNotFoundException
    //   67	84	112	finally
    //   88	93	112	finally
    //   96	107	112	finally
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\wrappers\InstantApps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */