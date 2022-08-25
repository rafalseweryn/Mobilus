package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.net.Uri;

public final class zzg extends zze.zza {
  private static final Uri CONTENT_URI = Uri.parse("content://com.google.settings/partner");
  
  public static int getInt(ContentResolver paramContentResolver, String paramString, int paramInt) {
    String str = getString(paramContentResolver, paramString);
    byte b = -1;
    paramInt = b;
    if (str != null)
      try {
        paramInt = Integer.parseInt(str);
      } catch (NumberFormatException numberFormatException) {
        paramInt = b;
      }  
    return paramInt;
  }
  
  private static String getString(ContentResolver paramContentResolver, String paramString) {
    return zza(paramContentResolver, CONTENT_URI, paramString);
  }
  
  public static String zza(ContentResolver paramContentResolver, String paramString1, String paramString2) {
    paramString1 = getString(paramContentResolver, paramString1);
    String str = paramString1;
    if (paramString1 == null)
      str = paramString2; 
    return str;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zzg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */