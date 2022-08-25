package pl.com.mobilelabs.mobilus.utils;

public class Conversions {
  public static byte[] getBytesForValue(long paramLong, int paramInt) {
    if (paramInt < 0)
      return null; 
    byte[] arrayOfByte = new byte[paramInt];
    while (--paramInt >= 0) {
      arrayOfByte[paramInt] = (byte)(byte)(int)(0xFFL & paramLong);
      paramLong >>>= 8L;
      paramInt--;
    } 
    return arrayOfByte;
  }
  
  public static byte[] getBytesFromHexString(String paramString) {
    int i = 0;
    if (paramString == null || paramString.isEmpty())
      return new byte[0]; 
    int j = (paramString.toCharArray()).length;
    byte[] arrayOfByte = new byte[(int)Math.ceil((j / 2))];
    while (i < j) {
      int m;
      int k = i + 2;
      if (k <= j) {
        m = k;
      } else {
        m = i + 1;
      } 
      String str = paramString.substring(i, m);
      try {
        m = Integer.parseInt(str, 16);
        arrayOfByte[i / 2] = (byte)(byte)m;
        i = k;
      } catch (NumberFormatException numberFormatException) {
        return null;
      } 
    } 
    return arrayOfByte;
  }
  
  public static String getHexStringForBytes(byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder = new StringBuilder();
    int i = paramArrayOfbyte.length;
    for (byte b = 0; b < i; b++) {
      stringBuilder.append(String.format("%02x", new Object[] { Byte.valueOf(paramArrayOfbyte[b]) }));
    } 
    return stringBuilder.toString().toUpperCase();
  }
  
  public static long getLongFromArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    long l = 0L;
    if (paramArrayOfbyte != null && paramInt1 < paramArrayOfbyte.length) {
      paramInt2 += paramInt1;
      if (paramInt2 <= paramArrayOfbyte.length) {
        while (paramInt1 < paramInt2) {
          long l1 = l | (paramArrayOfbyte[paramInt1] & 0xFF);
          l = l1;
          if (paramInt1 != paramInt2 - 1)
            l = l1 << 8L; 
          paramInt1++;
        } 
        return l;
      } 
    } 
    return 0L;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilu\\utils\Conversions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */