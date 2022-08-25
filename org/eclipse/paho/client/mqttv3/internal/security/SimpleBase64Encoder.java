package org.eclipse.paho.client.mqttv3.internal.security;

public class SimpleBase64Encoder {
  private static final char[] PWDCHARS_ARRAY = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
  
  private static final String PWDCHARS_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  
  public static byte[] decode(String paramString) {
    byte[] arrayOfByte1 = paramString.getBytes();
    int i = arrayOfByte1.length;
    byte[] arrayOfByte2 = new byte[i * 3 / 4];
    int j = 0;
    byte b = 0;
    label22: while (true) {
      byte b1 = 2;
      if (i < 4) {
        if (i == 3) {
          long l1 = from64(arrayOfByte1, j, 3);
          for (byte b2 = 1; !b2; b2--) {
            arrayOfByte2[b + b2] = (byte)(byte)(int)(l1 & 0xFFL);
            l1 >>= 8L;
          } 
        } 
        if (i == 2)
          arrayOfByte2[b] = (byte)(byte)(int)(from64(arrayOfByte1, j, 2) & 0xFFL); 
        return arrayOfByte2;
      } 
      long l = from64(arrayOfByte1, j, 4);
      i -= 4;
      int k = j + 4;
      for (j = b1;; j--) {
        if (j < 0) {
          b += 3;
          j = k;
          continue label22;
        } 
        arrayOfByte2[b + j] = (byte)(byte)(int)(l & 0xFFL);
        l >>= 8L;
      } 
      break;
    } 
  }
  
  public static String encode(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    StringBuffer stringBuffer = new StringBuffer((i + 2) / 3 * 4);
    byte b = 0;
    while (true) {
      if (i < 3) {
        if (i == 2)
          stringBuffer.append(to64(((paramArrayOfbyte[b] & 0xFF) << 8 | paramArrayOfbyte[b + 1] & 0xFF), 3)); 
        if (i == 1)
          stringBuffer.append(to64((paramArrayOfbyte[b] & 0xFF), 2)); 
        return stringBuffer.toString();
      } 
      stringBuffer.append(to64(((paramArrayOfbyte[b] & 0xFF) << 16 | (paramArrayOfbyte[b + 1] & 0xFF) << 8 | paramArrayOfbyte[b + 2] & 0xFF), 4));
      b += 3;
      i -= 3;
    } 
  }
  
  private static final long from64(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte b = 0;
    long l = 0L;
    int i = paramInt2;
    paramInt2 = b;
    while (true) {
      if (i <= 0)
        return l; 
      i--;
      b = paramArrayOfbyte[paramInt1];
      if (b == 47) {
        l1 = 1L;
      } else {
        l1 = 0L;
      } 
      long l2 = l1;
      if (b >= 48) {
        l2 = l1;
        if (b <= 57)
          l2 = (b + 2 - 48); 
      } 
      long l1 = l2;
      if (b >= 65) {
        l1 = l2;
        if (b <= 90)
          l1 = (b + 12 - 65); 
      } 
      l2 = l1;
      if (b >= 97) {
        l2 = l1;
        if (b <= 122)
          l2 = (b + 38 - 97); 
      } 
      l += l2 << paramInt2;
      paramInt2 += 6;
      paramInt1++;
    } 
  }
  
  private static final String to64(long paramLong, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(paramInt);
    while (true) {
      if (paramInt <= 0)
        return stringBuffer.toString(); 
      paramInt--;
      stringBuffer.append(PWDCHARS_ARRAY[(int)(0x3FL & paramLong)]);
      paramLong >>= 6L;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\security\SimpleBase64Encoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */