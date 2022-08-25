package com.google.android.gms.common.util;

public class Hex {
  private static final char[] zzaaa;
  
  private static final char[] zzzz = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F' };
  
  static {
    zzaaa = new char[] { 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f' };
  }
  
  public static String bytesToColonDelimitedStringLowercase(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length == 0)
      return new String(); 
    char[] arrayOfChar = new char[paramArrayOfbyte.length * 3 - 1];
    int i = 0;
    int j;
    for (j = 0; i < paramArrayOfbyte.length - 1; j++) {
      int k = paramArrayOfbyte[i] & 0xFF;
      int m = j + 1;
      arrayOfChar[j] = (char)zzaaa[k >>> 4];
      j = m + 1;
      arrayOfChar[m] = (char)zzaaa[k & 0xF];
      arrayOfChar[j] = (char)':';
      i++;
    } 
    i = paramArrayOfbyte[paramArrayOfbyte.length - 1] & 0xFF;
    arrayOfChar[j] = (char)zzaaa[i >>> 4];
    arrayOfChar[j + 1] = (char)zzaaa[i & 0xF];
    return new String(arrayOfChar);
  }
  
  public static String bytesToColonDelimitedStringUppercase(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length == 0)
      return new String(); 
    char[] arrayOfChar = new char[paramArrayOfbyte.length * 3 - 1];
    int i = 0;
    int j;
    for (j = 0; i < paramArrayOfbyte.length - 1; j++) {
      int k = paramArrayOfbyte[i] & 0xFF;
      int m = j + 1;
      arrayOfChar[j] = (char)zzzz[k >>> 4];
      j = m + 1;
      arrayOfChar[m] = (char)zzzz[k & 0xF];
      arrayOfChar[j] = (char)':';
      i++;
    } 
    i = paramArrayOfbyte[paramArrayOfbyte.length - 1] & 0xFF;
    arrayOfChar[j] = (char)zzzz[i >>> 4];
    arrayOfChar[j + 1] = (char)zzzz[i & 0xF];
    return new String(arrayOfChar);
  }
  
  public static String bytesToStringLowercase(byte[] paramArrayOfbyte) {
    char[] arrayOfChar = new char[paramArrayOfbyte.length << 1];
    byte b = 0;
    int i = 0;
    while (b < paramArrayOfbyte.length) {
      int j = paramArrayOfbyte[b] & 0xFF;
      int k = i + 1;
      arrayOfChar[i] = (char)zzaaa[j >>> 4];
      i = k + 1;
      arrayOfChar[k] = (char)zzaaa[j & 0xF];
      b++;
    } 
    return new String(arrayOfChar);
  }
  
  public static String bytesToStringUppercase(byte[] paramArrayOfbyte) {
    return bytesToStringUppercase(paramArrayOfbyte, false);
  }
  
  public static String bytesToStringUppercase(byte[] paramArrayOfbyte, boolean paramBoolean) {
    int i = paramArrayOfbyte.length;
    StringBuilder stringBuilder = new StringBuilder(i << 1);
    for (byte b = 0; b < i && (!paramBoolean || b != i - 1 || (paramArrayOfbyte[b] & 0xFF) != 0); b++) {
      stringBuilder.append(zzzz[(paramArrayOfbyte[b] & 0xF0) >>> 4]);
      stringBuilder.append(zzzz[paramArrayOfbyte[b] & 0xF]);
    } 
    return stringBuilder.toString();
  }
  
  public static byte[] colonDelimitedStringToBytes(String paramString) {
    return stringToBytes(paramString.replace(":", ""));
  }
  
  public static byte[] stringToBytes(String paramString) throws IllegalArgumentException {
    int i = paramString.length();
    if (i % 2 != 0)
      throw new IllegalArgumentException("Hex string has odd number of characters"); 
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; j < i; j = m) {
      int k = j / 2;
      int m = j + 2;
      arrayOfByte[k] = (byte)(byte)Integer.parseInt(paramString.substring(j, m), 16);
    } 
    return arrayOfByte;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\Hex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */