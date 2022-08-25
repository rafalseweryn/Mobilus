package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Process;
import android.support.v4.content.PermissionChecker;
import android.support.v4.util.Pair;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;

public class PackageManagerWrapper {
  private final Context zzjp;
  
  public PackageManagerWrapper(Context paramContext) {
    this.zzjp = paramContext;
  }
  
  public boolean allowApiAccess(String paramString, int paramInt) {
    return true;
  }
  
  public void checkCallerIsNotInstantApp() {
    if (isCallerInstantApp())
      throw new SecurityException("This operation is not supported for instant apps."); 
  }
  
  public int checkCallingOrSelfPermission(String paramString) {
    return this.zzjp.checkCallingOrSelfPermission(paramString);
  }
  
  public int checkCallingOrSelfPermissionAndAppOps(String paramString) {
    return PermissionChecker.checkCallingOrSelfPermission(this.zzjp, paramString);
  }
  
  public int checkCallingPermission(String paramString) {
    return this.zzjp.checkCallingPermission(paramString);
  }
  
  @Deprecated
  public int checkCallingPermission(String paramString1, String paramString2) {
    return checkPermission(paramString1, paramString2);
  }
  
  public int checkCallingPermissionAndAppOps(String paramString1, String paramString2) {
    return PermissionChecker.checkCallingPermission(this.zzjp, paramString1, paramString2);
  }
  
  public void checkPackage(int paramInt, String paramString) {
    if (!uidHasPackageName(paramInt, paramString)) {
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 39);
      stringBuilder.append("Package ");
      stringBuilder.append(paramString);
      stringBuilder.append(" does not belong to ");
      stringBuilder.append(paramInt);
      throw new SecurityException(stringBuilder.toString());
    } 
  }
  
  public int checkPermission(String paramString, int paramInt1, int paramInt2) {
    return this.zzjp.checkPermission(paramString, paramInt1, paramInt2);
  }
  
  @Deprecated
  public int checkPermission(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    return checkPermission(paramString1, paramInt1, paramInt2);
  }
  
  public int checkPermission(String paramString1, String paramString2) {
    return this.zzjp.getPackageManager().checkPermission(paramString1, paramString2);
  }
  
  public int checkPermissionAndAppOps(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    return PermissionChecker.checkPermission(this.zzjp, paramString1, paramInt1, paramInt2, paramString2);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return this.zzjp.getPackageManager().getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(String paramString) throws PackageManager.NameNotFoundException {
    return this.zzjp.getPackageManager().getApplicationLabel(this.zzjp.getPackageManager().getApplicationInfo(paramString, 0));
  }
  
  public Pair<CharSequence, Drawable> getApplicationLabelAndIcon(String paramString) throws PackageManager.NameNotFoundException {
    ApplicationInfo applicationInfo = this.zzjp.getPackageManager().getApplicationInfo(paramString, 0);
    return Pair.create(this.zzjp.getPackageManager().getApplicationLabel(applicationInfo), this.zzjp.getPackageManager().getApplicationIcon(applicationInfo));
  }
  
  public ComponentName getCallingActivity(Activity paramActivity) {
    return paramActivity.getCallingActivity();
  }
  
  public String getCallingPackage(Activity paramActivity) {
    StringBuilder stringBuilder;
    ComponentName componentName = paramActivity.getCallingActivity();
    if (componentName == null)
      return null; 
    String str = componentName.getPackageName();
    if (str == null) {
      String str1 = String.valueOf(componentName);
      stringBuilder = new StringBuilder(String.valueOf(str1).length() + 54);
      stringBuilder.append("getCallingPackage(): Couldn't get a package name from ");
      stringBuilder.append(str1);
      Log.e("PackageManagerWrapper", stringBuilder.toString());
      return null;
    } 
    return (String)stringBuilder;
  }
  
  protected Context getContext() {
    return this.zzjp;
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return this.zzjp.getPackageManager().getPackageInfo(paramString, paramInt);
  }
  
  public String[] getPackagesForUid(int paramInt) {
    return this.zzjp.getPackageManager().getPackagesForUid(paramInt);
  }
  
  public boolean isCallerInstantApp() {
    if (Binder.getCallingUid() == Process.myUid())
      return InstantApps.isInstantApp(this.zzjp); 
    if (PlatformVersion.isAtLeastO()) {
      String str = this.zzjp.getPackageManager().getNameForUid(Binder.getCallingUid());
      if (str != null)
        return this.zzjp.getPackageManager().isInstantApp(str); 
    } 
    return false;
  }
  
  public boolean isInstantAppUid(int paramInt) {
    return false;
  }
  
  @TargetApi(19)
  public boolean uidHasPackageName(int paramInt, String paramString) {
    if (PlatformVersion.isAtLeastKitKat())
      try {
        ((AppOpsManager)this.zzjp.getSystemService("appops")).checkPackage(paramInt, paramString);
        return true;
      } catch (SecurityException securityException) {
        return false;
      }  
    String[] arrayOfString = this.zzjp.getPackageManager().getPackagesForUid(paramInt);
    if (securityException != null && arrayOfString != null)
      for (paramInt = 0; paramInt < arrayOfString.length; paramInt++) {
        if (securityException.equals(arrayOfString[paramInt]))
          return true; 
      }  
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\wrappers\PackageManagerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */