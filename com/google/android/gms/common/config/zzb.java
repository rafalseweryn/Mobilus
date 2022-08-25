package com.google.android.gms.common.config;

import android.content.Context;

final class zzb extends GservicesValue<Long> {
  zzb(String paramString, Long paramLong) {
    super(paramString, paramLong);
  }
  
  private static Long zza(Context paramContext, String paramString, Long paramLong) {
    String str = paramContext.getSharedPreferences("gservices-direboot-cache", 0).getString(paramString, null);
    if (str != null)
      try {
        long l = Long.parseLong(str);
        return Long.valueOf(l);
      } catch (NumberFormatException numberFormatException) {} 
    return paramLong;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\config\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */