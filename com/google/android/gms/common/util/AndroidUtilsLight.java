package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AndroidUtilsLight {
  public static final String DIGEST_ALGORITHM_SHA1 = "SHA1";
  
  public static final String DIGEST_ALGORITHM_SHA512 = "SHA-512";
  
  public static MessageDigest getMessageDigest(String paramString) {
    byte b = 0;
    while (true) {
      if (b < 2) {
        try {
          MessageDigest messageDigest = MessageDigest.getInstance(paramString);
          if (messageDigest != null)
            return messageDigest; 
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {}
        b++;
        continue;
      } 
      return null;
    } 
  }
  
  public static byte[] getPackageCertificateHashBytes(Context paramContext, String paramString) throws PackageManager.NameNotFoundException {
    return getPackageCertificateHashBytes(paramContext, paramString, "SHA1");
  }
  
  public static byte[] getPackageCertificateHashBytes(Context paramContext, String paramString1, String paramString2) throws PackageManager.NameNotFoundException {
    PackageInfo packageInfo = Wrappers.packageManager(paramContext).getPackageInfo(paramString1, 64);
    if (packageInfo.signatures != null && packageInfo.signatures.length == 1) {
      MessageDigest messageDigest = getMessageDigest(paramString2);
      if (messageDigest != null)
        return messageDigest.digest(packageInfo.signatures[0].toByteArray()); 
    } 
    return null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\AndroidUtilsLight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */