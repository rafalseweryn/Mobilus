package com.google.android.gms.common.config;

import android.content.Context;

final class zzd extends GservicesValue<Double> {
  zzd(String paramString, Double paramDouble) {
    super(paramString, paramDouble);
  }
  
  private static Double zza(Context paramContext, String paramString, Double paramDouble) {
    String str = paramContext.getSharedPreferences("gservices-direboot-cache", 0).getString(paramString, null);
    if (str != null)
      try {
        double d = Double.parseDouble(str);
        return Double.valueOf(d);
      } catch (NumberFormatException numberFormatException) {} 
    return paramDouble;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\config\zzd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */