package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

public class Wrappers {
  private static Wrappers zzabb = new Wrappers();
  
  private PackageManagerWrapper zzaba = null;
  
  public static PackageManagerWrapper packageManager(Context paramContext) {
    return zzabb.getPackageManagerWrapper(paramContext);
  }
  
  @VisibleForTesting
  public static void resetForTests() {
    zzabb = new Wrappers();
  }
  
  public static void setWrappers(Wrappers paramWrappers) {
    zzabb = paramWrappers;
  }
  
  @VisibleForTesting
  public PackageManagerWrapper getPackageManagerWrapper(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzaba : Lcom/google/android/gms/common/wrappers/PackageManagerWrapper;
    //   6: ifnonnull -> 38
    //   9: aload_1
    //   10: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   13: ifnonnull -> 19
    //   16: goto -> 24
    //   19: aload_1
    //   20: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   23: astore_1
    //   24: new com/google/android/gms/common/wrappers/PackageManagerWrapper
    //   27: astore_2
    //   28: aload_2
    //   29: aload_1
    //   30: invokespecial <init> : (Landroid/content/Context;)V
    //   33: aload_0
    //   34: aload_2
    //   35: putfield zzaba : Lcom/google/android/gms/common/wrappers/PackageManagerWrapper;
    //   38: aload_0
    //   39: getfield zzaba : Lcom/google/android/gms/common/wrappers/PackageManagerWrapper;
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: areturn
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	47	finally
    //   19	24	47	finally
    //   24	38	47	finally
    //   38	43	47	finally
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\wrappers\Wrappers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */