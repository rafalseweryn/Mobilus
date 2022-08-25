package com.google.android.gms.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GmsVersionParser {
  public static final int UNKNOWN = -1;
  
  private static Pattern zzzy;
  
  public static int parseBuildMajorVersion(int paramInt) {
    return (paramInt == -1) ? -1 : (paramInt / 100000);
  }
  
  public static long parseBuildNumber(String paramString) {
    if (paramString == null)
      return -1L; 
    Matcher matcher = zzdc().matcher(paramString);
    if (matcher.find()) {
      String str = matcher.group(2);
      try {
        return Long.parseLong(str);
      } catch (NumberFormatException numberFormatException) {}
    } 
    return -1L;
  }
  
  public static int parseBuildType(String paramString) {
    long l = parseVariantCode(paramString);
    return (l == -1L) ? -1 : (int)(l / 10000L);
  }
  
  public static int parseBuildVersion(int paramInt) {
    return (paramInt == -1) ? -1 : (paramInt / 1000);
  }
  
  public static int parseScreenDensity(String paramString) {
    long l = parseVariantCode(paramString);
    return (l == -1L) ? -1 : (int)(l % 100L);
  }
  
  public static int parseTargetArchitecture(String paramString) {
    long l = parseVariantCode(paramString);
    return (l == -1L) ? -1 : (int)(l / 100L % 100L);
  }
  
  public static long parseVariantCode(String paramString) {
    if (paramString == null)
      return -1L; 
    Matcher matcher = zzdc().matcher(paramString);
    if (matcher.find()) {
      String str = matcher.group(1);
      try {
        return Long.parseLong(str);
      } catch (NumberFormatException numberFormatException) {}
    } 
    return -1L;
  }
  
  private static Pattern zzdc() {
    if (zzzy == null)
      zzzy = Pattern.compile("\\((?:eng-)?(\\d+)-(.+?)[-)$]"); 
    return zzzy;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\GmsVersionParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */