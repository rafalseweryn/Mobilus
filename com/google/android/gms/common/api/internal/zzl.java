package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

final class zzl {
  private final int zzet;
  
  private final ConnectionResult zzeu;
  
  zzl(ConnectionResult paramConnectionResult, int paramInt) {
    Preconditions.checkNotNull(paramConnectionResult);
    this.zzeu = paramConnectionResult;
    this.zzet = paramInt;
  }
  
  final ConnectionResult getConnectionResult() {
    return this.zzeu;
  }
  
  final int zzu() {
    return this.zzet;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */