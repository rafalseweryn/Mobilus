package com.google.android.gms.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HttpUtils {
  private static final Pattern zzaab = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
  
  private static final Pattern zzaac = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
  
  private static final Pattern zzaad = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
  
  private static String decode(String paramString1, String paramString2) {
    if (paramString2 == null)
      paramString2 = "ISO-8859-1"; 
    try {
      return URLDecoder.decode(paramString1, paramString2);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new IllegalArgumentException(unsupportedEncodingException);
    } 
  }
  
  public static boolean isIPv4Address(String paramString) {
    return zzaab.matcher(paramString).matches();
  }
  
  public static boolean isIPv6Address(String paramString) {
    return (isIPv6StdAddress(paramString) || isIPv6HexCompressedAddress(paramString));
  }
  
  public static boolean isIPv6HexCompressedAddress(String paramString) {
    return zzaad.matcher(paramString).matches();
  }
  
  public static boolean isIPv6StdAddress(String paramString) {
    return zzaac.matcher(paramString).matches();
  }
  
  public static Map<String, String> parse(URI paramURI, String paramString) {
    String str1;
    Map<?, ?> map2 = Collections.emptyMap();
    String str2 = paramURI.getRawQuery();
    Map<?, ?> map1 = map2;
    if (str2 != null) {
      map1 = map2;
      if (str2.length() > 0) {
        map2 = new HashMap<>();
        Scanner scanner = new Scanner(str2);
        scanner.useDelimiter("&");
        while (true) {
          map1 = map2;
          if (scanner.hasNext()) {
            String[] arrayOfString = scanner.next().split("=");
            if (arrayOfString.length == 0 || arrayOfString.length > 2)
              throw new IllegalArgumentException("bad parameter"); 
            String str = decode(arrayOfString[0], paramString);
            map1 = null;
            if (arrayOfString.length == 2)
              str1 = decode(arrayOfString[1], paramString); 
            map2.put(str, str1);
            continue;
          } 
          break;
        } 
      } 
    } 
    return (Map<String, String>)str1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\HttpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */