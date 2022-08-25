package com.google.android.gms.common;

import android.support.annotation.NonNull;
import android.util.Log;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class zzg {
  private static final zzg zzbk = new zzg(true, null, null);
  
  private final Throwable cause;
  
  final boolean zzbl;
  
  private final String zzbm;
  
  zzg(boolean paramBoolean, @Nullable String paramString, @Nullable Throwable paramThrowable) {
    this.zzbl = paramBoolean;
    this.zzbm = paramString;
    this.cause = paramThrowable;
  }
  
  static zzg zza(String paramString, GoogleCertificates.CertData paramCertData, boolean paramBoolean1, boolean paramBoolean2) {
    return new zzi(paramString, paramCertData, paramBoolean1, paramBoolean2, null);
  }
  
  static zzg zza(@NonNull String paramString, @NonNull Throwable paramThrowable) {
    return new zzg(false, paramString, paramThrowable);
  }
  
  static zzg zze(@NonNull String paramString) {
    return new zzg(false, paramString, null);
  }
  
  static zzg zzg() {
    return zzbk;
  }
  
  @Nullable
  String getErrorMessage() {
    return this.zzbm;
  }
  
  final void zzh() throws SecurityException {
    if (!this.zzbl) {
      String str1 = String.valueOf("GoogleCertificatesRslt: ");
      String str2 = String.valueOf(getErrorMessage());
      if (str2.length() != 0) {
        str2 = str1.concat(str2);
      } else {
        str2 = new String(str1);
      } 
      if (this.cause != null)
        throw new SecurityException(str2, this.cause); 
      throw new SecurityException(str2);
    } 
  }
  
  final void zzi() {
    if (!this.zzbl) {
      if (this.cause != null) {
        Log.d("GoogleCertificatesRslt", getErrorMessage(), this.cause);
        return;
      } 
      Log.d("GoogleCertificatesRslt", getErrorMessage());
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\zzg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */