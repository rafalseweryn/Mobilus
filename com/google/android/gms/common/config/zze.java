package com.google.android.gms.common.config;

import android.content.Context;

final class zze extends GservicesValue<Float> {
  zze(String paramString, Float paramFloat) {
    super(paramString, paramFloat);
  }
  
  private static Float zza(Context paramContext, String paramString, Float paramFloat) {
    String str = paramContext.getSharedPreferences("gservices-direboot-cache", 0).getString(paramString, null);
    if (str != null)
      try {
        float f = Float.parseFloat(str);
        return Float.valueOf(f);
      } catch (NumberFormatException numberFormatException) {} 
    return paramFloat;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\config\zze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */