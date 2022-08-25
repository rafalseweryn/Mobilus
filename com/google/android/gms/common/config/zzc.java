package com.google.android.gms.common.config;

import android.content.Context;

final class zzc extends GservicesValue<Integer> {
  zzc(String paramString, Integer paramInteger) {
    super(paramString, paramInteger);
  }
  
  private static Integer zza(Context paramContext, String paramString, Integer paramInteger) {
    String str = paramContext.getSharedPreferences("gservices-direboot-cache", 0).getString(paramString, null);
    if (str != null)
      try {
        int i = Integer.parseInt(str);
        return Integer.valueOf(i);
      } catch (NumberFormatException numberFormatException) {} 
    return paramInteger;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\config\zzc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */