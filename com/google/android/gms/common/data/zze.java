package com.google.android.gms.common.data;

final class zze implements TextFilterable.StringFilter {
  public final boolean matches(String paramString1, String paramString2) {
    if (!paramString1.startsWith(paramString2)) {
      paramString2 = String.valueOf(paramString2);
      if (paramString2.length() != 0) {
        paramString2 = " ".concat(paramString2);
      } else {
        paramString2 = new String(" ");
      } 
      if (!paramString1.contains(paramString2))
        return false; 
    } 
    return true;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\zze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */