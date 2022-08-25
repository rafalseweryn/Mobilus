package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.MetadataValueReader;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@Deprecated
@KeepForSdk
public final class GoogleServices {
  private static final Object sLock = new Object();
  
  @GuardedBy("sLock")
  private static GoogleServices zzku;
  
  private final String zzkv;
  
  private final Status zzkw;
  
  private final boolean zzkx;
  
  private final boolean zzky;
  
  @KeepForSdk
  @VisibleForTesting
  GoogleServices(Context paramContext) {
    Resources resources = paramContext.getResources();
    int i = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
    boolean bool1 = true;
    boolean bool2 = true;
    if (i != 0) {
      if (resources.getInteger(i) == 0)
        bool2 = false; 
      this.zzky = bool2 ^ true;
    } else {
      this.zzky = false;
      bool2 = bool1;
    } 
    this.zzkx = bool2;
    String str2 = MetadataValueReader.getGoogleAppId(paramContext);
    String str1 = str2;
    if (str2 == null)
      str1 = (new StringResourceValueReader(paramContext)).getString("google_app_id"); 
    if (TextUtils.isEmpty(str1)) {
      this.zzkw = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
      this.zzkv = null;
      return;
    } 
    this.zzkv = str1;
    this.zzkw = Status.RESULT_SUCCESS;
  }
  
  @KeepForSdk
  @VisibleForTesting
  GoogleServices(String paramString, boolean paramBoolean) {
    this.zzkv = paramString;
    this.zzkw = Status.RESULT_SUCCESS;
    this.zzkx = paramBoolean;
    this.zzky = paramBoolean ^ true;
  }
  
  @KeepForSdk
  private static GoogleServices checkInitialized(String paramString) {
    synchronized (sLock) {
      if (zzku == null) {
        IllegalStateException illegalStateException = new IllegalStateException();
        int i = String.valueOf(paramString).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 34);
        stringBuilder.append("Initialize must be called before ");
        stringBuilder.append(paramString);
        stringBuilder.append(".");
        this(stringBuilder.toString());
        throw illegalStateException;
      } 
      return zzku;
    } 
  }
  
  @KeepForSdk
  @VisibleForTesting
  static void clearInstanceForTest() {
    synchronized (sLock) {
      zzku = null;
      return;
    } 
  }
  
  @KeepForSdk
  public static String getGoogleAppId() {
    return (checkInitialized("getGoogleAppId")).zzkv;
  }
  
  @KeepForSdk
  public static Status initialize(Context paramContext) {
    Preconditions.checkNotNull(paramContext, "Context must not be null.");
    synchronized (sLock) {
      if (zzku == null) {
        GoogleServices googleServices = new GoogleServices();
        this(paramContext);
        zzku = googleServices;
      } 
      return zzku.zzkw;
    } 
  }
  
  @KeepForSdk
  public static Status initialize(Context paramContext, String paramString, boolean paramBoolean) {
    Preconditions.checkNotNull(paramContext, "Context must not be null.");
    Preconditions.checkNotEmpty(paramString, "App ID must be nonempty.");
    synchronized (sLock) {
      if (zzku != null) {
        status = zzku.checkGoogleAppId(paramString);
        return status;
      } 
      GoogleServices googleServices = new GoogleServices();
      this((String)status, paramBoolean);
      zzku = googleServices;
      Status status = googleServices.zzkw;
      return status;
    } 
  }
  
  @KeepForSdk
  public static boolean isMeasurementEnabled() {
    GoogleServices googleServices = checkInitialized("isMeasurementEnabled");
    return (googleServices.zzkw.isSuccess() && googleServices.zzkx);
  }
  
  @KeepForSdk
  public static boolean isMeasurementExplicitlyDisabled() {
    return (checkInitialized("isMeasurementExplicitlyDisabled")).zzky;
  }
  
  @KeepForSdk
  @VisibleForTesting
  final Status checkGoogleAppId(String paramString) {
    if (this.zzkv != null && !this.zzkv.equals(paramString)) {
      paramString = this.zzkv;
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 97);
      stringBuilder.append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '");
      stringBuilder.append(paramString);
      stringBuilder.append("'.");
      return new Status(10, stringBuilder.toString());
    } 
    return Status.RESULT_SUCCESS;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\GoogleServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */