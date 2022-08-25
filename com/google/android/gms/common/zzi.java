package com.google.android.gms.common;

import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;

final class zzi extends zzg {
  private final String packageName;
  
  private final GoogleCertificates.CertData zzbn;
  
  private final boolean zzbo;
  
  private final boolean zzbp;
  
  private zzi(String paramString, GoogleCertificates.CertData paramCertData, boolean paramBoolean1, boolean paramBoolean2) {
    super(false, null, null);
    this.packageName = paramString;
    this.zzbn = paramCertData;
    this.zzbo = paramBoolean1;
    this.zzbp = paramBoolean2;
  }
  
  final String getErrorMessage() {
    String str1;
    if (this.zzbp) {
      str1 = "debug cert rejected";
    } else {
      str1 = "not whitelisted";
    } 
    String str2 = this.packageName;
    GoogleCertificates.CertData certData = this.zzbn;
    String str3 = Hex.bytesToStringLowercase(AndroidUtilsLight.getMessageDigest("SHA-1").digest(certData.getBytes()));
    boolean bool = this.zzbo;
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 44 + String.valueOf(str2).length() + String.valueOf(str3).length());
    stringBuilder.append(str1);
    stringBuilder.append(": pkg=");
    stringBuilder.append(str2);
    stringBuilder.append(", sha1=");
    stringBuilder.append(str3);
    stringBuilder.append(", atk=");
    stringBuilder.append(bool);
    stringBuilder.append(", ver=12451009.false");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\zzi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */