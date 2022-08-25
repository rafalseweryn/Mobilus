package com.google.android.gms.common.util;

@VisibleForTesting
public class NumberUtils {
  public static int compare(int paramInt1, int paramInt2) {
    return (paramInt1 < paramInt2) ? -1 : ((paramInt1 == paramInt2) ? 0 : 1);
  }
  
  public static int compare(long paramLong1, long paramLong2) {
    int i = paramLong1 cmp paramLong2;
    return (i < 0) ? -1 : ((i == 0) ? 0 : 1);
  }
  
  public static boolean isNumeric(String paramString) {
    try {
      Long.parseLong(paramString);
      return true;
    } catch (NumberFormatException numberFormatException) {
      return false;
    } 
  }
  
  public static long parseHexLong(String paramString) {
    if (paramString.length() > 16) {
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 37);
      stringBuilder.append("Invalid input: ");
      stringBuilder.append(paramString);
      stringBuilder.append(" exceeds 16 characters");
      throw new NumberFormatException(stringBuilder.toString());
    } 
    if (paramString.length() == 16) {
      long l = Long.parseLong(paramString.substring(8), 16);
      return Long.parseLong(paramString.substring(0, 8), 16) << 32L | l;
    } 
    return Long.parseLong(paramString, 16);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\NumberUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */