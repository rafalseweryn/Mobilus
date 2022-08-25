package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;

public class GmsServiceEndpoint {
  @NonNull
  private final String mPackageName;
  
  private final int zztq;
  
  @NonNull
  private final String zzue;
  
  private final boolean zzuf;
  
  public GmsServiceEndpoint(@NonNull String paramString1, @NonNull String paramString2, boolean paramBoolean, int paramInt) {
    this.mPackageName = paramString1;
    this.zzue = paramString2;
    this.zzuf = paramBoolean;
    this.zztq = paramInt;
  }
  
  final int getBindFlags() {
    return this.zztq;
  }
  
  @NonNull
  final String getPackageName() {
    return this.mPackageName;
  }
  
  @NonNull
  final String zzcw() {
    return this.zzue;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GmsServiceEndpoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */