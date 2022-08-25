package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.concurrent.GuardedBy;

public class MetadataValueReader {
  public static final String KEY_METADATA_APP_ID = "com.google.app.id";
  
  private static Object sLock = new Object();
  
  @GuardedBy("sLock")
  private static boolean zzui;
  
  private static String zzuj;
  
  private static int zzuk;
  
  public static String getGoogleAppId(Context paramContext) {
    zze(paramContext);
    return zzuj;
  }
  
  public static int getGooglePlayServicesVersion(Context paramContext) {
    zze(paramContext);
    return zzuk;
  }
  
  @VisibleForTesting
  public static void resetForTesting() {
    synchronized (sLock) {
      zzui = false;
      return;
    } 
  }
  
  @VisibleForTesting
  public static void setValuesForTesting(String paramString, int paramInt) {
    synchronized (sLock) {
      zzuj = paramString;
      zzuk = paramInt;
      zzui = true;
      return;
    } 
  }
  
  private static void zze(Context paramContext) {
    synchronized (sLock) {
      if (zzui)
        return; 
      zzui = true;
      String str = paramContext.getPackageName();
      PackageManagerWrapper packageManagerWrapper = Wrappers.packageManager(paramContext);
      try {
        Bundle bundle = (packageManagerWrapper.getApplicationInfo(str, 128)).metaData;
        if (bundle == null)
          return; 
        zzuj = bundle.getString("com.google.app.id");
        zzuk = bundle.getInt("com.google.android.gms.version");
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        Log.wtf("MetadataValueReader", "This should never happen.", (Throwable)nameNotFoundException);
      } 
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\MetadataValueReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */