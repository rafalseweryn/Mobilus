package com.google.android.gms.common.util;

import android.util.Base64;

public final class Base64Utils {
  public static byte[] decode(String paramString) {
    return (paramString == null) ? null : Base64.decode(paramString, 0);
  }
  
  public static byte[] decodeUrlSafe(String paramString) {
    return (paramString == null) ? null : Base64.decode(paramString, 10);
  }
  
  public static byte[] decodeUrlSafeNoPadding(String paramString) {
    return (paramString == null) ? null : Base64.decode(paramString, 11);
  }
  
  public static byte[] decodeUrlSafeNoPadding(byte[] paramArrayOfbyte) {
    return (paramArrayOfbyte == null) ? null : Base64.decode(paramArrayOfbyte, 11);
  }
  
  public static String encode(byte[] paramArrayOfbyte) {
    return (paramArrayOfbyte == null) ? null : Base64.encodeToString(paramArrayOfbyte, 0);
  }
  
  public static String encodeUrlSafe(byte[] paramArrayOfbyte) {
    return (paramArrayOfbyte == null) ? null : Base64.encodeToString(paramArrayOfbyte, 10);
  }
  
  public static String encodeUrlSafeNoPadding(byte[] paramArrayOfbyte) {
    return (paramArrayOfbyte == null) ? null : Base64.encodeToString(paramArrayOfbyte, 11);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\Base64Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */