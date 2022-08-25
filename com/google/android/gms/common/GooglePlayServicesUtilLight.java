package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.internal.MetadataValueReader;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.GmsVersionParser;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class GooglePlayServicesUtilLight {
  public static final String FEATURE_SIDEWINDER = "cn.google";
  
  public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
  
  @Deprecated
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
  
  public static final String GOOGLE_PLAY_STORE_GAMES_URI_STRING = "http://play.google.com/store/apps/category/GAME";
  
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  
  public static final String GOOGLE_PLAY_STORE_URI_STRING = "http://play.google.com/store/apps/";
  
  public static final boolean HONOR_DEBUG_CERTIFICATES = false;
  
  public static final String KEY_METADATA_GOOGLE_PLAY_SERVICES_VERSION = "com.google.android.gms.version";
  
  public static final int MAX_ATTEMPTS_NO_SUCH_ALGORITHM = 2;
  
  @VisibleForTesting
  public static boolean sIsTestMode = false;
  
  @VisibleForTesting
  public static boolean sTestIsUserBuild = false;
  
  private static boolean zzbr = false;
  
  @VisibleForTesting
  private static boolean zzbs = false;
  
  @VisibleForTesting
  static final AtomicBoolean zzbt = new AtomicBoolean();
  
  private static final AtomicBoolean zzbu = new AtomicBoolean();
  
  @Deprecated
  public static void cancelAvailabilityErrorNotifications(Context paramContext) {
    if (zzbt.getAndSet(true))
      return; 
    try {
      NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
      if (notificationManager != null)
        notificationManager.cancel(10436); 
    } catch (SecurityException securityException) {}
  }
  
  public static void enableUsingApkIndependentContext() {
    zzbu.set(true);
  }
  
  @Deprecated
  public static void ensurePlayServicesAvailable(Context paramContext) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
    ensurePlayServicesAvailable(paramContext, GOOGLE_PLAY_SERVICES_VERSION_CODE);
  }
  
  @Deprecated
  public static void ensurePlayServicesAvailable(Context paramContext, int paramInt) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
    paramInt = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(paramContext, paramInt);
    if (paramInt != 0) {
      Intent intent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(paramContext, paramInt, "e");
      StringBuilder stringBuilder = new StringBuilder(57);
      stringBuilder.append("GooglePlayServices not available due to error ");
      stringBuilder.append(paramInt);
      Log.e("GooglePlayServicesUtil", stringBuilder.toString());
      if (intent == null)
        throw new GooglePlayServicesNotAvailableException(paramInt); 
      throw new GooglePlayServicesRepairableException(paramInt, "Google Play Services not available", intent);
    } 
  }
  
  @Deprecated
  public static int getApkVersion(Context paramContext) {
    try {
      PackageInfo packageInfo = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      return packageInfo.versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
      return 0;
    } 
  }
  
  @Deprecated
  public static int getClientVersion(Context paramContext) {
    Preconditions.checkState(true);
    return ClientLibraryUtils.getClientVersion(paramContext, paramContext.getPackageName());
  }
  
  @Deprecated
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2) {
    return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Deprecated
  @VisibleForTesting
  public static String getErrorString(int paramInt) {
    return ConnectionResult.zza(paramInt);
  }
  
  @Deprecated
  public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int paramInt) {
    return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, paramInt, null);
  }
  
  @Deprecated
  public static String getOpenSourceSoftwareLicenseInfo(Context paramContext) {
    Uri uri = (new Uri.Builder()).scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
    try {
      InputStream inputStream = paramContext.getContentResolver().openInputStream(uri);
      try {
        Scanner scanner = new Scanner();
        this(inputStream);
        return scanner.useDelimiter("\\A").next();
      } catch (NoSuchElementException noSuchElementException) {
      
      } finally {
        if (inputStream != null)
          inputStream.close(); 
      } 
    } catch (Exception exception) {}
    return null;
  }
  
  public static Context getRemoteContext(Context paramContext) {
    try {
      return paramContext.createPackageContext("com.google.android.gms", 3);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  public static Resources getRemoteResource(Context paramContext) {
    try {
      return paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  public static boolean honorsDebugCertificates(Context paramContext) {
    return (isTestKeysBuild(paramContext) || !isUserBuildDevice());
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext) {
    return isGooglePlayServicesAvailable(paramContext, GOOGLE_PLAY_SERVICES_VERSION_CODE);
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext, int paramInt) {
    StringBuilder stringBuilder;
    boolean bool;
    try {
      paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
    } catch (Throwable throwable) {
      Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
    } 
    if (!"com.google.android.gms".equals(paramContext.getPackageName()) && !zzbu.get()) {
      int i = MetadataValueReader.getGooglePlayServicesVersion(paramContext);
      if (i == 0)
        throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />"); 
      if (i != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
        paramInt = GOOGLE_PLAY_SERVICES_VERSION_CODE;
        stringBuilder = new StringBuilder(320);
        stringBuilder.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
        stringBuilder.append(paramInt);
        stringBuilder.append(" but found ");
        stringBuilder.append(i);
        stringBuilder.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        throw new IllegalStateException(stringBuilder.toString());
      } 
    } 
    if (!DeviceProperties.isWearableWithoutPlayStore((Context)stringBuilder) && !DeviceProperties.isIoT((Context)stringBuilder)) {
      bool = true;
    } else {
      bool = false;
    } 
    return zza((Context)stringBuilder, bool, paramInt);
  }
  
  @Deprecated
  public static boolean isGooglePlayServicesUid(Context paramContext, int paramInt) {
    return UidVerifier.isGooglePlayServicesUid(paramContext, paramInt);
  }
  
  @Deprecated
  public static boolean isPlayServicesPossiblyUpdating(Context paramContext, int paramInt) {
    return (paramInt == 18) ? true : ((paramInt == 1) ? isUninstalledAppPossiblyUpdating(paramContext, "com.google.android.gms") : false);
  }
  
  @Deprecated
  public static boolean isPlayStorePossiblyUpdating(Context paramContext, int paramInt) {
    return (paramInt == 9) ? isUninstalledAppPossiblyUpdating(paramContext, "com.android.vending") : false;
  }
  
  @TargetApi(18)
  public static boolean isRestrictedUserProfile(Context paramContext) {
    if (PlatformVersion.isAtLeastJellyBeanMR2()) {
      Bundle bundle = ((UserManager)paramContext.getSystemService("user")).getApplicationRestrictions(paramContext.getPackageName());
      if (bundle != null && "true".equals(bundle.getString("restricted_profile")))
        return true; 
    } 
    return false;
  }
  
  @Deprecated
  @VisibleForTesting
  public static boolean isSidewinderDevice(Context paramContext) {
    return DeviceProperties.isSidewinder(paramContext);
  }
  
  public static boolean isTestKeysBuild(Context paramContext) {
    if (!zzbs) {
      try {
        PackageInfo packageInfo = Wrappers.packageManager(paramContext).getPackageInfo("com.google.android.gms", 64);
        GoogleSignatureVerifier googleSignatureVerifier = GoogleSignatureVerifier.getInstance(paramContext);
        if (packageInfo != null && !googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, false) && googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, true)) {
          zzbr = true;
        } else {
          zzbr = false;
        } 
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", (Throwable)nameNotFoundException);
      } finally {}
      zzbs = true;
    } 
    return zzbr;
  }
  
  @TargetApi(21)
  static boolean isUninstalledAppPossiblyUpdating(Context paramContext, String paramString) {
    boolean bool = paramString.equals("com.google.android.gms");
    if (PlatformVersion.isAtLeastLollipop())
      try {
        List list = paramContext.getPackageManager().getPackageInstaller().getAllSessions();
        Iterator<PackageInstaller.SessionInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
          if (paramString.equals(((PackageInstaller.SessionInfo)iterator.next()).getAppPackageName()))
            return true; 
        } 
      } catch (Exception exception) {
        return false;
      }  
    PackageManager packageManager = exception.getPackageManager();
    try {
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(paramString, 8192);
      if (bool)
        return applicationInfo.enabled; 
      if (applicationInfo.enabled) {
        bool = isRestrictedUserProfile((Context)exception);
        if (!bool)
          return true; 
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return false;
  }
  
  @Deprecated
  public static boolean isUserBuildDevice() {
    return DeviceProperties.isUserBuild();
  }
  
  @Deprecated
  public static boolean isUserRecoverableError(int paramInt) {
    if (paramInt != 9)
      switch (paramInt) {
        default:
          return false;
        case 1:
        case 2:
        case 3:
          break;
      }  
    return true;
  }
  
  @Deprecated
  @TargetApi(19)
  public static boolean uidHasPackageName(Context paramContext, int paramInt, String paramString) {
    return UidVerifier.uidHasPackageName(paramContext, paramInt, paramString);
  }
  
  @VisibleForTesting
  private static int zza(Context paramContext, boolean paramBoolean, int paramInt) {
    String str;
    boolean bool;
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    PackageManager packageManager = paramContext.getPackageManager();
    PackageInfo packageInfo = null;
    if (paramBoolean)
      try {
        packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        str = "Google Play Store is missing.";
        Log.w("GooglePlayServicesUtil", str);
        return 9;
      }  
    try {
      String str1;
      PackageInfo packageInfo1 = packageManager.getPackageInfo("com.google.android.gms", 64);
      GoogleSignatureVerifier googleSignatureVerifier = GoogleSignatureVerifier.getInstance((Context)str);
      if (!googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo1, true)) {
        str1 = "Google Play services signature invalid.";
        Log.w("GooglePlayServicesUtil", str1);
        return 9;
      } 
      if (paramBoolean && (!str1.isGooglePublicSignedPackage(packageInfo, true) || !packageInfo.signatures[0].equals(packageInfo1.signatures[0]))) {
        str1 = "Google Play Store signature invalid.";
        Log.w("GooglePlayServicesUtil", str1);
        return 9;
      } 
      if (GmsVersionParser.parseBuildVersion(packageInfo1.versionCode) < GmsVersionParser.parseBuildVersion(paramInt)) {
        int i = packageInfo1.versionCode;
        StringBuilder stringBuilder = new StringBuilder(77);
        stringBuilder.append("Google Play services out of date.  Requires ");
        stringBuilder.append(paramInt);
        stringBuilder.append(" but found ");
        stringBuilder.append(i);
        Log.w("GooglePlayServicesUtil", stringBuilder.toString());
        return 2;
      } 
      ApplicationInfo applicationInfo2 = packageInfo1.applicationInfo;
      ApplicationInfo applicationInfo1 = applicationInfo2;
      if (applicationInfo2 == null)
        try {
          applicationInfo1 = packageManager.getApplicationInfo("com.google.android.gms", 0);
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", (Throwable)nameNotFoundException);
          return 1;
        }  
      return !((ApplicationInfo)nameNotFoundException).enabled ? 3 : 0;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
      return 1;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GooglePlayServicesUtilLight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */