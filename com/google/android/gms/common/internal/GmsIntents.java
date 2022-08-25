package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.api.Scope;
import java.util.Locale;

public class GmsIntents {
  public static final String ACTION_FITNESS_APP_DISCONNECTED = "com.google.android.gms.fitness.app_disconnected";
  
  public static final String ACTION_ICING_CONTACT_CHANGED = "com.google.android.gms.icing.action.CONTACT_CHANGED";
  
  public static final String ACTION_SET_GMS_ACCOUNT = "com.google.android.gms.common.SET_GMS_ACCOUNT";
  
  public static final String ACTION_UDC_SETTING_CHANGED = "com.google.android.gms.udc.action.SETTING_CHANGED";
  
  public static final String BROADCAST_CIRCLES_CHANGED = "com.google.android.gms.people.BROADCAST_CIRCLES_CHANGED";
  
  public static final String COMMON_SIGN_IN_EXTRA_PACKAGE_NAME = "SIGN_IN_PACKAGE_NAME";
  
  public static final String COMMON_SIGN_IN_EXTRA_SAVE_DEFAULT_ACCOUNT = "SIGN_IN_SAVE_DEFAULT_ACCOUNT";
  
  public static final String COMMON_SIGN_IN_EXTRA_SCOPE_ARRAY = "SIGN_IN_SCOPE_ARRAY";
  
  public static final String EXTRA_ACCOUNT = "com.google.android.gms.fitness.disconnected_account";
  
  public static final String EXTRA_APP = "com.google.android.gms.fitness.disconnected_app";
  
  public static final String EXTRA_ICING_CONTACT_CHANGED_IS_SIGNIFICANT = "com.google.android.gms.icing.extra.isSignificant";
  
  public static final String EXTRA_SET_GMS_ACCOUNT_NAME = "ACCOUNT_NAME";
  
  public static final String EXTRA_SET_GMS_ACCOUNT_PACKAGE_NAME = "PACKAGE_NAME";
  
  public static final String EXTRA_UDC_ACCOUNT_NAME = "com.google.android.gms.udc.extra.accountName";
  
  public static final String EXTRA_UDC_SETTING_ID_LIST = "com.google.android.gms.udc.extra.settingIdList";
  
  public static final String GOOGLE_NOW_PACKAGE_NAME = "com.google.android.googlequicksearchbox";
  
  public static final String MIME_ACTIVITY_DISCONNECT_TYPE = "vnd.google.android.fitness/app_disconnect";
  
  public static final String PERMISSION_GMS_INTERNAL_BROADCAST = "com.google.android.gms.permission.INTERNAL_BROADCAST";
  
  private static final Uri zztz;
  
  private static final Uri zzua;
  
  static {
    Uri uri = Uri.parse("https://plus.google.com/");
    zztz = uri;
    zzua = uri.buildUpon().appendPath("circles").appendPath("find").build();
  }
  
  public static Intent createAndroidWearUpdateIntent() {
    Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
    intent.setPackage("com.google.android.wearable.app");
    return intent;
  }
  
  public static Intent createChooseGmsAccountIntent() {
    return AccountPicker.newChooseAccountIntent(null, null, new String[] { "com.google" }, true, null, null, null, null, true);
  }
  
  public static Intent createChooseGmsAccountWithConsentIntent(String paramString, Scope[] paramArrayOfScope, boolean paramBoolean) {
    Intent intent = new Intent("com.google.android.gms.signin.action.SIGN_IN");
    intent.putExtra("SIGN_IN_PACKAGE_NAME", paramString);
    intent.putExtra("SIGN_IN_SCOPE_ARRAY", (Parcelable[])paramArrayOfScope);
    intent.putExtra("SIGN_IN_SAVE_DEFAULT_ACCOUNT", paramBoolean);
    return intent;
  }
  
  public static Intent createDateSettingsIntent() {
    return new Intent("android.settings.DATE_SETTINGS");
  }
  
  public static Intent createFindPeopleIntent(Context paramContext) {
    return zza(paramContext, zzua);
  }
  
  public static Intent createPlayStoreGamesIntent(Context paramContext) {
    Intent intent = new Intent("android.intent.action.VIEW");
    intent.setData(Uri.parse("http://play.google.com/store/apps/category/GAME"));
    intent.addFlags(524288);
    intent.setPackage("com.android.vending");
    if (paramContext.getPackageManager().resolveActivity(intent, 65536) == null) {
      Intent intent1 = new Intent(intent.getAction(), intent.getData());
      intent1.setFlags(intent.getFlags());
      return intent1;
    } 
    return intent;
  }
  
  public static Intent createPlayStoreIntent(String paramString) {
    return createPlayStoreIntent(paramString, null);
  }
  
  public static Intent createPlayStoreIntent(String paramString1, @Nullable String paramString2) {
    Intent intent = new Intent("android.intent.action.VIEW");
    Uri.Builder builder = Uri.parse("market://details").buildUpon().appendQueryParameter("id", paramString1);
    if (!TextUtils.isEmpty(paramString2))
      builder.appendQueryParameter("pcampaignid", paramString2); 
    intent.setData(builder.build());
    intent.setPackage("com.android.vending");
    intent.addFlags(524288);
    return intent;
  }
  
  public static Intent createPlayStoreLightPurchaseFlowIntent(Context paramContext, @Nullable String paramString1, String paramString2) {
    Intent intent2 = new Intent("com.android.vending.billing.PURCHASE");
    intent2.addCategory("android.intent.category.DEFAULT");
    intent2.setPackage("com.android.vending");
    if (!TextUtils.isEmpty(paramString1))
      intent2.putExtra("authAccount", paramString1); 
    intent2.putExtra("backend", 3);
    intent2.putExtra("document_type", 1);
    intent2.putExtra("full_docid", paramString2);
    intent2.putExtra("backend_docid", paramString2);
    intent2.putExtra("offer_type", 1);
    Intent intent1 = intent2;
    if (!isIntentResolvable(paramContext.getPackageManager(), intent2)) {
      intent1 = new Intent("android.intent.action.VIEW");
      intent1.setData(Uri.parse(String.format(Locale.US, "https://play.google.com/store/apps/details?id=%1$s&rdid=%1$s&rdot=%2$d", new Object[] { paramString2, Integer.valueOf(1) })));
      intent1.setPackage("com.android.vending");
      intent1.putExtra("use_direct_purchase", true);
    } 
    return intent1;
  }
  
  public static Intent createSettingsIntent(String paramString) {
    Uri uri = Uri.fromParts("package", paramString, null);
    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    intent.setData(uri);
    return intent;
  }
  
  public static Intent createShareOnPlusIntent(Activity paramActivity, String paramString1, String paramString2) {
    Intent intent = ShareCompat.IntentBuilder.from(paramActivity).setSubject(paramString1).setText(paramString2).setType("text/plain").getIntent();
    intent.setPackage("com.google.android.apps.plus");
    return isIntentResolvable(paramActivity.getPackageManager(), intent) ? intent : createPlayStoreIntent("com.google.android.apps.plus");
  }
  
  public static Intent createShowProfileIntent(Context paramContext, String paramString) {
    return zza(paramContext, Uri.parse(String.format("https://plus.google.com/%s/about", new Object[] { paramString })));
  }
  
  public static Intent getFitnessAppDisconnectedIntent(String paramString1, String paramString2) {
    Intent intent = new Intent();
    intent.setPackage("com.google.android.gms");
    intent.setAction("com.google.android.gms.fitness.app_disconnected");
    intent.setType("vnd.google.android.fitness/app_disconnect");
    intent.putExtra("com.google.android.gms.fitness.disconnected_app", paramString1);
    intent.putExtra("com.google.android.gms.fitness.disconnected_account", paramString2);
    return intent;
  }
  
  public static Uri getPlayStoreUri(String paramString) {
    return Uri.parse("https://play.google.com/store/apps/details").buildUpon().appendQueryParameter("id", paramString).build();
  }
  
  public static boolean isIntentResolvable(PackageManager paramPackageManager, Intent paramIntent) {
    return (paramPackageManager.resolveActivity(paramIntent, 65536) != null);
  }
  
  public static void sendIcingContactChangedBroadcast(Context paramContext, boolean paramBoolean) {
    Intent intent = (new Intent("com.google.android.gms.icing.action.CONTACT_CHANGED")).setPackage("com.google.android.gms").putExtra("com.google.android.gms.icing.extra.isSignificant", paramBoolean);
    if (Log.isLoggable("GmsIntents", 2)) {
      String str = intent.getAction();
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 98);
      stringBuilder.append("Icing detected contact change, broadcasting it with intent action: ");
      stringBuilder.append(str);
      stringBuilder.append(" and isSignificant extra: ");
      stringBuilder.append(paramBoolean);
      Log.v("GmsIntents", stringBuilder.toString());
    } 
    paramContext.sendBroadcast(intent);
  }
  
  public static void sendSetGmsAccountIntent(Context paramContext, String paramString1, String paramString2) {
    Intent intent = new Intent("com.google.android.gms.common.SET_GMS_ACCOUNT");
    intent.putExtra("ACCOUNT_NAME", paramString1);
    intent.putExtra("PACKAGE_NAME", paramString2);
    intent.setPackage("com.google.android.gms");
    paramContext.sendBroadcast(intent, "com.google.android.gms.permission.INTERNAL_BROADCAST");
  }
  
  public static void sendUdcSettingsChangedBroadcast(Context paramContext, String paramString, int[] paramArrayOfint) {
    zza("com.google.android.gms", paramContext, paramString, paramArrayOfint);
    if (!GoogleSignatureVerifier.getInstance(paramContext).isPackageGoogleSigned("com.google.android.googlequicksearchbox")) {
      if (Log.isLoggable("GmsIntents", 5))
        Log.w("GmsIntents", "Google now certificate not valid. UDC settings broadcast will not be sent."); 
      return;
    } 
    zza("com.google.android.googlequicksearchbox", paramContext, paramString, paramArrayOfint);
  }
  
  private static Intent zza(Context paramContext, Uri paramUri) {
    Intent intent = new Intent("android.intent.action.VIEW");
    intent.setData(paramUri);
    intent.setPackage("com.google.android.apps.plus");
    return isIntentResolvable(paramContext.getPackageManager(), intent) ? intent : createPlayStoreIntent("com.google.android.apps.plus");
  }
  
  private static void zza(String paramString1, Context paramContext, String paramString2, int[] paramArrayOfint) {
    Intent intent = (new Intent("com.google.android.gms.udc.action.SETTING_CHANGED")).setPackage(paramString1).putExtra("com.google.android.gms.udc.extra.accountName", paramString2).putExtra("com.google.android.gms.udc.extra.settingIdList", paramArrayOfint);
    if (Log.isLoggable("GmsIntents", 3)) {
      String str = intent.getAction();
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 72 + String.valueOf(str).length());
      stringBuilder.append("UDC settings changed, sending broadcast to package ");
      stringBuilder.append(paramString1);
      stringBuilder.append(" with intent action: ");
      stringBuilder.append(str);
      Log.d("GmsIntents", stringBuilder.toString());
    } 
    paramContext.sendBroadcast(intent);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GmsIntents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */