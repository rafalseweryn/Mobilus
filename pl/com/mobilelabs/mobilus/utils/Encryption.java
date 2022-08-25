package pl.com.mobilelabs.mobilus.utils;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
  public static final int INITIALIZATION_VECTOR_LENGTH = 16;
  
  public static final int KEY_LENGTH = 32;
  
  public static byte[] decodeUsingAes(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3) {
    try {
      IvParameterSpec ivParameterSpec = new IvParameterSpec();
      this(paramArrayOfbyte3);
      Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
      SecretKeySpec secretKeySpec = new SecretKeySpec();
      this(paramArrayOfbyte2, "AES");
      cipher.init(2, secretKeySpec, ivParameterSpec);
      return cipher.doFinal(paramArrayOfbyte1);
    } catch (GeneralSecurityException generalSecurityException) {
      return null;
    } catch (IllegalArgumentException illegalArgumentException) {
      return null;
    } 
  }
  
  public static byte[] encodeUsingAes(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3) {
    try {
      IvParameterSpec ivParameterSpec = new IvParameterSpec();
      this(paramArrayOfbyte3);
      Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
      SecretKeySpec secretKeySpec = new SecretKeySpec();
      this(paramArrayOfbyte2, "AES");
      cipher.init(1, secretKeySpec, ivParameterSpec);
      return cipher.doFinal(paramArrayOfbyte1);
    } catch (GeneralSecurityException generalSecurityException) {
      return null;
    } catch (IllegalArgumentException illegalArgumentException) {
      return null;
    } 
  }
  
  public static byte[] getSha256Hash(byte[] paramArrayOfbyte) {
    try {
      return MessageDigest.getInstance("SHA-256").digest(paramArrayOfbyte);
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilu\\utils\Encryption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */