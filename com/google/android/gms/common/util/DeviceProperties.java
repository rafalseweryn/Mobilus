package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Objects;

public final class DeviceProperties {
  public static final String FEATURE_AUTO = "android.hardware.type.automotive";
  
  public static final String FEATURE_CHROME_OS = "org.chromium.arc";
  
  public static final String FEATURE_EMBEDDED = "android.hardware.type.embedded";
  
  public static final String FEATURE_IOT = "android.hardware.type.iot";
  
  public static final String FEATURE_LATCHSKY = "cn.google.services";
  
  public static final String FEATURE_PIXEL = "com.google.android.feature.PIXEL_EXPERIENCE";
  
  public static final String FEATURE_SIDEWINDER = "cn.google";
  
  public static final String FEATURE_TV_1 = "com.google.android.tv";
  
  public static final String FEATURE_TV_2 = "android.hardware.type.television";
  
  public static final String FEATURE_TV_3 = "android.software.leanback";
  
  private static Boolean zzzl;
  
  private static Boolean zzzm;
  
  private static Boolean zzzn;
  
  private static Boolean zzzo;
  
  private static Boolean zzzp;
  
  private static Boolean zzzq;
  
  private static Boolean zzzr;
  
  private static Boolean zzzs;
  
  private static Boolean zzzt;
  
  private static Boolean zzzu;
  
  private static Boolean zzzv;
  
  public static boolean isAuto(Context paramContext) {
    if (zzzt == null) {
      boolean bool;
      if (PlatformVersion.isAtLeastO() && paramContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzzt = Boolean.valueOf(bool);
    } 
    return zzzt.booleanValue();
  }
  
  public static boolean isChromeOsDevice(Context paramContext) {
    if (zzzs == null)
      zzzs = Boolean.valueOf(paramContext.getPackageManager().hasSystemFeature("org.chromium.arc")); 
    return zzzs.booleanValue();
  }
  
  public static boolean isIoT(Context paramContext) {
    if (zzzr == null) {
      boolean bool;
      if (paramContext.getPackageManager().hasSystemFeature("android.hardware.type.iot") || paramContext.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzzr = Boolean.valueOf(bool);
    } 
    return zzzr.booleanValue();
  }
  
  public static boolean isLatchsky(Context paramContext) {
    if (zzzp == null) {
      boolean bool;
      if (PlatformVersion.isAtLeastM() && paramContext.getPackageManager().hasSystemFeature("cn.google.services")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzzp = Boolean.valueOf(bool);
    } 
    return zzzp.booleanValue();
  }
  
  public static boolean isLowRamOrPreKitKat(Context paramContext) {
    if (Build.VERSION.SDK_INT < 19)
      return true; 
    if (zzzq == null) {
      ActivityManager activityManager = (ActivityManager)paramContext.getSystemService("activity");
      if (activityManager != null)
        zzzq = Boolean.valueOf(activityManager.isLowRamDevice()); 
    } 
    return Objects.equal(zzzq, Boolean.TRUE);
  }
  
  public static boolean isPixelDevice(Context paramContext) {
    if (zzzv == null)
      zzzv = Boolean.valueOf(paramContext.getPackageManager().hasSystemFeature("com.google.android.feature.PIXEL_EXPERIENCE")); 
    return zzzv.booleanValue();
  }
  
  @TargetApi(21)
  public static boolean isSidewinder(Context paramContext) {
    if (zzzo == null) {
      boolean bool;
      if (PlatformVersion.isAtLeastLollipop() && paramContext.getPackageManager().hasSystemFeature("cn.google")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzzo = Boolean.valueOf(bool);
    } 
    return zzzo.booleanValue();
  }
  
  public static boolean isTablet(Resources paramResources) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: ifnonnull -> 8
    //   6: iconst_0
    //   7: ireturn
    //   8: getstatic com/google/android/gms/common/util/DeviceProperties.zzzl : Ljava/lang/Boolean;
    //   11: ifnonnull -> 105
    //   14: aload_0
    //   15: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   18: getfield screenLayout : I
    //   21: bipush #15
    //   23: iand
    //   24: iconst_3
    //   25: if_icmple -> 33
    //   28: iconst_1
    //   29: istore_2
    //   30: goto -> 35
    //   33: iconst_0
    //   34: istore_2
    //   35: iload_2
    //   36: ifne -> 96
    //   39: getstatic com/google/android/gms/common/util/DeviceProperties.zzzm : Ljava/lang/Boolean;
    //   42: ifnonnull -> 85
    //   45: aload_0
    //   46: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   49: astore_0
    //   50: aload_0
    //   51: getfield screenLayout : I
    //   54: bipush #15
    //   56: iand
    //   57: iconst_3
    //   58: if_icmpgt -> 76
    //   61: aload_0
    //   62: getfield smallestScreenWidthDp : I
    //   65: sipush #600
    //   68: if_icmplt -> 76
    //   71: iconst_1
    //   72: istore_3
    //   73: goto -> 78
    //   76: iconst_0
    //   77: istore_3
    //   78: iload_3
    //   79: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   82: putstatic com/google/android/gms/common/util/DeviceProperties.zzzm : Ljava/lang/Boolean;
    //   85: iload_1
    //   86: istore_3
    //   87: getstatic com/google/android/gms/common/util/DeviceProperties.zzzm : Ljava/lang/Boolean;
    //   90: invokevirtual booleanValue : ()Z
    //   93: ifeq -> 98
    //   96: iconst_1
    //   97: istore_3
    //   98: iload_3
    //   99: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   102: putstatic com/google/android/gms/common/util/DeviceProperties.zzzl : Ljava/lang/Boolean;
    //   105: getstatic com/google/android/gms/common/util/DeviceProperties.zzzl : Ljava/lang/Boolean;
    //   108: invokevirtual booleanValue : ()Z
    //   111: ireturn
  }
  
  public static boolean isTv(Context paramContext) {
    if (zzzu == null) {
      boolean bool;
      PackageManager packageManager = paramContext.getPackageManager();
      if (packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzzu = Boolean.valueOf(bool);
    } 
    return zzzu.booleanValue();
  }
  
  public static boolean isUserBuild() {
    return GooglePlayServicesUtilLight.sIsTestMode ? GooglePlayServicesUtilLight.sTestIsUserBuild : "user".equals(Build.TYPE);
  }
  
  @TargetApi(20)
  public static boolean isWearable(Context paramContext) {
    if (zzzn == null) {
      boolean bool;
      if (PlatformVersion.isAtLeastKitKatWatch() && paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzzn = Boolean.valueOf(bool);
    } 
    return zzzn.booleanValue();
  }
  
  @TargetApi(24)
  public static boolean isWearableWithoutPlayStore(Context paramContext) {
    return ((!PlatformVersion.isAtLeastN() || isSidewinder(paramContext)) && isWearable(paramContext));
  }
  
  @VisibleForTesting
  public static void resetForTest() {
    zzzm = null;
    zzzl = null;
    zzzn = null;
    zzzo = null;
    zzzp = null;
    zzzq = null;
    zzzr = null;
    zzzs = null;
    zzzt = null;
    zzzu = null;
    zzzv = null;
  }
  
  @VisibleForTesting
  public static void setIsAutoForTest(boolean paramBoolean) {
    zzzt = Boolean.valueOf(paramBoolean);
  }
  
  @VisibleForTesting
  public static void setIsIoTForTest(boolean paramBoolean) {
    zzzr = Boolean.valueOf(paramBoolean);
  }
  
  @VisibleForTesting
  public static void setIsLatchskyForTest(boolean paramBoolean) {
    zzzp = Boolean.valueOf(paramBoolean);
  }
  
  @VisibleForTesting
  public static void setIsLowRamForTest(boolean paramBoolean) {
    zzzq = Boolean.valueOf(paramBoolean);
  }
  
  @VisibleForTesting
  public static void setIsPixelForTest(boolean paramBoolean) {
    zzzv = Boolean.valueOf(paramBoolean);
  }
  
  @VisibleForTesting
  public static void setIsSideWinderForTest(boolean paramBoolean) {
    zzzo = Boolean.valueOf(paramBoolean);
  }
  
  @VisibleForTesting
  public static void setIsTvForTest(boolean paramBoolean) {
    zzzu = Boolean.valueOf(paramBoolean);
  }
  
  @VisibleForTesting
  public static void setIsWearableForTest(boolean paramBoolean) {
    zzzn = Boolean.valueOf(paramBoolean);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\DeviceProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */