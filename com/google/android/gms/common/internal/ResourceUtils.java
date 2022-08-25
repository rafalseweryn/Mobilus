package com.google.android.gms.common.internal;

import android.net.Uri;

public final class ResourceUtils {
  private static final Uri zzuw = (new Uri.Builder()).scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
  
  public static Uri getDrawableUri(String paramString) {
    Preconditions.checkNotNull(paramString, "Resource name must not be null.");
    return zzuw.buildUpon().appendPath(paramString).build();
  }
  
  @Deprecated
  public static interface SignInResources {
    public static final String BUTTON_DARK_TEXT_DEFAULT = "common_google_signin_btn_text_dark_normal";
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ResourceUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */