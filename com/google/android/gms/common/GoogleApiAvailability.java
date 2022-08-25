package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.base.R;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zzbt;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
  
  private static final Object mLock = new Object();
  
  private static final GoogleApiAvailability zzas = new GoogleApiAvailability();
  
  @GuardedBy("mLock")
  private String zzat;
  
  static {
    GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  }
  
  public static GoogleApiAvailability getInstance() {
    return zzas;
  }
  
  static Dialog zza(Context paramContext, int paramInt, DialogRedirect paramDialogRedirect, DialogInterface.OnCancelListener paramOnCancelListener) {
    AlertDialog.Builder builder1 = null;
    if (paramInt == 0)
      return null; 
    TypedValue typedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(16843529, typedValue, true);
    if ("Theme.Dialog.Alert".equals(paramContext.getResources().getResourceEntryName(typedValue.resourceId)))
      builder1 = new AlertDialog.Builder(paramContext, 5); 
    AlertDialog.Builder builder2 = builder1;
    if (builder1 == null)
      builder2 = new AlertDialog.Builder(paramContext); 
    builder2.setMessage(ConnectionErrorMessages.getErrorMessage(paramContext, paramInt));
    if (paramOnCancelListener != null)
      builder2.setOnCancelListener(paramOnCancelListener); 
    String str2 = ConnectionErrorMessages.getErrorDialogButtonMessage(paramContext, paramInt);
    if (str2 != null)
      builder2.setPositiveButton(str2, (DialogInterface.OnClickListener)paramDialogRedirect); 
    String str1 = ConnectionErrorMessages.getErrorTitle(paramContext, paramInt);
    if (str1 != null)
      builder2.setTitle(str1); 
    return (Dialog)builder2.create();
  }
  
  @TargetApi(26)
  private final String zza(Context paramContext, NotificationManager paramNotificationManager) {
    Preconditions.checkState(PlatformVersion.isAtLeastO());
    String str1 = zzb();
    String str2 = str1;
    if (str1 == null) {
      NotificationChannel notificationChannel1;
      str2 = "com.google.android.gms.availability";
      NotificationChannel notificationChannel2 = paramNotificationManager.getNotificationChannel("com.google.android.gms.availability");
      String str = ConnectionErrorMessages.getDefaultNotificationChannelName(paramContext);
      if (notificationChannel2 == null) {
        notificationChannel1 = new NotificationChannel("com.google.android.gms.availability", str, 4);
        paramNotificationManager.createNotificationChannel(notificationChannel1);
        return "com.google.android.gms.availability";
      } 
      if (!notificationChannel1.equals(notificationChannel2.getName())) {
        notificationChannel2.setName((CharSequence)notificationChannel1);
        notificationChannel1 = notificationChannel2;
        paramNotificationManager.createNotificationChannel(notificationChannel1);
        return "com.google.android.gms.availability";
      } 
    } 
    return str2;
  }
  
  static void zza(Activity paramActivity, Dialog paramDialog, String paramString, DialogInterface.OnCancelListener paramOnCancelListener) {
    FragmentManager fragmentManager1;
    if (paramActivity instanceof FragmentActivity) {
      fragmentManager1 = ((FragmentActivity)paramActivity).getSupportFragmentManager();
      SupportErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(fragmentManager1, paramString);
      return;
    } 
    FragmentManager fragmentManager = fragmentManager1.getFragmentManager();
    ErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(fragmentManager, paramString);
  }
  
  @TargetApi(20)
  private final void zza(Context paramContext, int paramInt, String paramString, PendingIntent paramPendingIntent) {
    Notification notification;
    Notification.Builder builder;
    if (paramInt == 18) {
      zza(paramContext);
      return;
    } 
    if (paramPendingIntent == null) {
      if (paramInt == 6)
        Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead."); 
      return;
    } 
    String str1 = ConnectionErrorMessages.getErrorNotificationTitle(paramContext, paramInt);
    String str2 = ConnectionErrorMessages.getErrorNotificationMessage(paramContext, paramInt);
    Resources resources = paramContext.getResources();
    NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
    if (DeviceProperties.isWearable(paramContext)) {
      Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
      builder = (new Notification.Builder(paramContext)).setSmallIcon((paramContext.getApplicationInfo()).icon).setPriority(2).setAutoCancel(true).setContentTitle(str1).setStyle((Notification.Style)(new Notification.BigTextStyle()).bigText(str2));
      if (DeviceProperties.isWearableWithoutPlayStore(paramContext)) {
        builder.addAction(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), paramPendingIntent);
      } else {
        builder.setContentIntent(paramPendingIntent);
      } 
      if (PlatformVersion.isAtLeastO() && PlatformVersion.isAtLeastO())
        builder.setChannelId(zza(paramContext, notificationManager)); 
      notification = builder.build();
    } else {
      NotificationCompat.Builder builder1 = (new NotificationCompat.Builder((Context)notification)).setSmallIcon(17301642).setTicker(resources.getString(R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(paramPendingIntent).setContentTitle((CharSequence)builder).setContentText(str2).setLocalOnly(true).setStyle((NotificationCompat.Style)(new NotificationCompat.BigTextStyle()).bigText(str2));
      if (PlatformVersion.isAtLeastO() && PlatformVersion.isAtLeastO())
        builder1.setChannelId(zza((Context)notification, notificationManager)); 
      notification = builder1.build();
    } 
    switch (paramInt) {
      default:
        paramInt = 39789;
        break;
      case 1:
      case 2:
      case 3:
        paramInt = 10436;
        GooglePlayServicesUtilLight.zzbt.set(false);
        break;
    } 
    if (paramString == null) {
      notificationManager.notify(paramInt, notification);
      return;
    } 
    notificationManager.notify(paramString, paramInt, notification);
  }
  
  @VisibleForTesting(otherwise = 2)
  private final String zzb() {
    synchronized (mLock) {
      return this.zzat;
    } 
  }
  
  public Task<Void> checkApiAvailability(GoogleApi<?> paramGoogleApi, GoogleApi<?>... paramVarArgs) {
    return checkApiAvailabilityAndPackages(paramGoogleApi, paramVarArgs).continueWith(new zza(this));
  }
  
  public Task<Map<zzh<?>, String>> checkApiAvailabilityAndPackages(GoogleApi<?> paramGoogleApi, GoogleApi<?>... paramVarArgs) {
    Preconditions.checkNotNull(paramGoogleApi, "Requested API must not be null.");
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++)
      Preconditions.checkNotNull(paramVarArgs[b], "Requested API must not be null."); 
    ArrayList<GoogleApi<?>> arrayList = new ArrayList(paramVarArgs.length + 1);
    arrayList.add(paramGoogleApi);
    arrayList.addAll(Arrays.asList(paramVarArgs));
    return GoogleApiManager.zzbf().zza(arrayList);
  }
  
  public int getApkVersion(Context paramContext) {
    return super.getApkVersion(paramContext);
  }
  
  public int getClientVersion(Context paramContext) {
    return super.getClientVersion(paramContext);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2) {
    return getErrorDialog(paramActivity, paramInt1, paramInt2, null);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    return zza((Context)paramActivity, paramInt1, DialogRedirect.getInstance(paramActivity, super.getErrorResolutionIntent((Context)paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
  }
  
  @Deprecated
  @Nullable
  public Intent getErrorResolutionIntent(int paramInt) {
    return super.getErrorResolutionIntent(paramInt);
  }
  
  @Nullable
  public Intent getErrorResolutionIntent(Context paramContext, int paramInt, @Nullable String paramString) {
    return super.getErrorResolutionIntent(paramContext, paramInt, paramString);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2) {
    return super.getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2, @Nullable String paramString) {
    return super.getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2, paramString);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, ConnectionResult paramConnectionResult) {
    return paramConnectionResult.hasResolution() ? paramConnectionResult.getResolution() : super.getErrorResolutionPendingIntent(paramContext, paramConnectionResult.getErrorCode(), 0);
  }
  
  public final String getErrorString(int paramInt) {
    return super.getErrorString(paramInt);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext) {
    return super.isGooglePlayServicesAvailable(paramContext);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext, int paramInt) {
    return super.isGooglePlayServicesAvailable(paramContext, paramInt);
  }
  
  public boolean isPlayServicesPossiblyUpdating(Context paramContext, int paramInt) {
    return super.isPlayServicesPossiblyUpdating(paramContext, paramInt);
  }
  
  public boolean isPlayStorePossiblyUpdating(Context paramContext, int paramInt) {
    return super.isPlayStorePossiblyUpdating(paramContext, paramInt);
  }
  
  public final boolean isUserResolvableError(int paramInt) {
    return super.isUserResolvableError(paramInt);
  }
  
  @MainThread
  public Task<Void> makeGooglePlayServicesAvailable(Activity paramActivity) {
    Preconditions.checkMainThread("makeGooglePlayServicesAvailable must be called from the main thread");
    int i = super.isGooglePlayServicesAvailable((Context)paramActivity);
    if (i == 0)
      return Tasks.forResult(null); 
    zzbt zzbt = zzbt.zzd(paramActivity);
    zzbt.zzb(new ConnectionResult(i, null), 0);
    return zzbt.getTask();
  }
  
  @Nullable
  public GooglePlayServicesUpdatedReceiver registerCallbackOnUpdate(Context paramContext, GooglePlayServicesUpdatedReceiver.Callback paramCallback) {
    IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    intentFilter.addDataScheme("package");
    GooglePlayServicesUpdatedReceiver googlePlayServicesUpdatedReceiver = new GooglePlayServicesUpdatedReceiver(paramCallback);
    paramContext.registerReceiver((BroadcastReceiver)googlePlayServicesUpdatedReceiver, intentFilter);
    googlePlayServicesUpdatedReceiver.zzc(paramContext);
    if (!isUninstalledAppPossiblyUpdating(paramContext, "com.google.android.gms")) {
      paramCallback.zzv();
      googlePlayServicesUpdatedReceiver.unregister();
      return null;
    } 
    return googlePlayServicesUpdatedReceiver;
  }
  
  @TargetApi(26)
  public void setDefaultNotificationChannelId(@NonNull Context paramContext, @NonNull String paramString) {
    if (PlatformVersion.isAtLeastO())
      Preconditions.checkNotNull(((NotificationManager)paramContext.getSystemService("notification")).getNotificationChannel(paramString)); 
    synchronized (mLock) {
      this.zzat = paramString;
      return;
    } 
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2) {
    return showErrorDialogFragment(paramActivity, paramInt1, paramInt2, null);
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    Dialog dialog = getErrorDialog(paramActivity, paramInt1, paramInt2, paramOnCancelListener);
    if (dialog == null)
      return false; 
    zza(paramActivity, dialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, @NonNull LifecycleFragment paramLifecycleFragment, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    Dialog dialog = zza((Context)paramActivity, paramInt1, DialogRedirect.getInstance(paramLifecycleFragment, super.getErrorResolutionIntent((Context)paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
    if (dialog == null)
      return false; 
    zza(paramActivity, dialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public void showErrorNotification(Context paramContext, int paramInt) {
    showErrorNotification(paramContext, paramInt, null);
  }
  
  public void showErrorNotification(Context paramContext, int paramInt, String paramString) {
    zza(paramContext, paramInt, paramString, super.getErrorResolutionPendingIntent(paramContext, paramInt, 0, "n"));
  }
  
  public void showErrorNotification(Context paramContext, ConnectionResult paramConnectionResult) {
    PendingIntent pendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    zza(paramContext, paramConnectionResult.getErrorCode(), (String)null, pendingIntent);
  }
  
  public Dialog showUpdatingDialog(Activity paramActivity, DialogInterface.OnCancelListener paramOnCancelListener) {
    ProgressBar progressBar = new ProgressBar((Context)paramActivity, null, 16842874);
    progressBar.setIndeterminate(true);
    progressBar.setVisibility(0);
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)paramActivity);
    builder.setView((View)progressBar);
    builder.setMessage(ConnectionErrorMessages.getErrorMessage((Context)paramActivity, 18));
    builder.setPositiveButton("", null);
    AlertDialog alertDialog = builder.create();
    zza(paramActivity, (Dialog)alertDialog, "GooglePlayServicesUpdatingDialog", paramOnCancelListener);
    return (Dialog)alertDialog;
  }
  
  public boolean showWrappedErrorNotification(Context paramContext, ConnectionResult paramConnectionResult, int paramInt) {
    PendingIntent pendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    if (pendingIntent != null) {
      zza(paramContext, paramConnectionResult.getErrorCode(), (String)null, GoogleApiActivity.zza(paramContext, pendingIntent, paramInt));
      return true;
    } 
    return false;
  }
  
  final void zza(Context paramContext) {
    (new zza(this, paramContext)).sendEmptyMessageDelayed(1, 120000L);
  }
  
  @SuppressLint({"HandlerLeak"})
  private final class zza extends Handler {
    private final Context zzau;
    
    public zza(GoogleApiAvailability this$0, Context param1Context) {
      super(looper);
      Looper looper;
      this.zzau = param1Context.getApplicationContext();
    }
    
    public final void handleMessage(Message param1Message) {
      if (param1Message.what != 1) {
        int j = param1Message.what;
        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append("Don't know how to handle this message: ");
        stringBuilder.append(j);
        Log.w("GoogleApiAvailability", stringBuilder.toString());
        return;
      } 
      int i = this.zzav.isGooglePlayServicesAvailable(this.zzau);
      if (this.zzav.isUserResolvableError(i))
        this.zzav.showErrorNotification(this.zzau, i); 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GoogleApiAvailability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */