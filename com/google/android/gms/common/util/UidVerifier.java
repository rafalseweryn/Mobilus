package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.wrappers.Wrappers;

public final class UidVerifier {
  public static boolean isGooglePlayServicesUid(Context paramContext, int paramInt) {
    if (!uidHasPackageName(paramContext, paramInt, "com.google.android.gms"))
      return false; 
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
      return GoogleSignatureVerifier.getInstance(paramContext).isGooglePublicSignedPackage(packageInfo);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      if (Log.isLoggable("UidVerifier", 3))
        Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false"); 
      return false;
    } 
  }
  
  @TargetApi(19)
  public static boolean uidHasPackageName(Context paramContext, int paramInt, String paramString) {
    return Wrappers.packageManager(paramContext).uidHasPackageName(paramInt, paramString);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\UidVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */