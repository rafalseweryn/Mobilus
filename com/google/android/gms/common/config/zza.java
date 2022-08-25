package com.google.android.gms.common.config;

import android.content.Context;

final class zza extends GservicesValue<Boolean> {
  zza(String paramString, Boolean paramBoolean) {
    super(paramString, paramBoolean);
  }
  
  private static Boolean zza(Context paramContext, String paramString, Boolean paramBoolean) {
    String str = paramContext.getSharedPreferences("gservices-direboot-cache", 0).getString(paramString, null);
    if (str != null)
      try {
        boolean bool = Boolean.parseBoolean(str);
        return Boolean.valueOf(bool);
      } catch (NumberFormatException numberFormatException) {} 
    return paramBoolean;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\config\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */