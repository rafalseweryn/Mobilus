package com.google.android.gms.common;

import java.util.Arrays;

final class zzb extends GoogleCertificates.CertData {
  private final byte[] zzbd;
  
  zzb(byte[] paramArrayOfbyte) {
    super(Arrays.copyOfRange(paramArrayOfbyte, 0, 25));
    this.zzbd = paramArrayOfbyte;
  }
  
  final byte[] getBytes() {
    return this.zzbd;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */