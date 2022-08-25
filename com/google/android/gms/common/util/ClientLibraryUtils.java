package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;

public class ClientLibraryUtils {
  public static final int GMS_CLIENT_VERSION_UNKNOWN = -1;
  
  public static int getClientVersion(Context paramContext, String paramString) {
    return getClientVersion(getPackageInfo(paramContext, paramString));
  }
  
  public static int getClientVersion(PackageInfo paramPackageInfo) {
    if (paramPackageInfo != null) {
      if (paramPackageInfo.applicationInfo == null)
        return -1; 
      Bundle bundle = paramPackageInfo.applicationInfo.metaData;
      return (bundle == null) ? -1 : bundle.getInt("com.google.android.gms.version", -1);
    } 
    return -1;
  }
  
  @Nullable
  public static PackageInfo getPackageInfo(Context paramContext, String paramString) {
    try {
      return Wrappers.packageManager(paramContext).getPackageInfo(paramString, 128);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  public static boolean isPackageSide() {
    return false;
  }
  
  public static boolean isPackageStopped(Context paramContext, String paramString) {
    "com.google.android.gms".equals(paramString);
    try {
      int i = (Wrappers.packageManager(paramContext).getApplicationInfo(paramString, 0)).flags;
      if ((i & 0x200000) != 0)
        return true; 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\ClientLibraryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */