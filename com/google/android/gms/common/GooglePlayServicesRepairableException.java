package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException {
  private final int zzbq;
  
  public GooglePlayServicesRepairableException(int paramInt, String paramString, Intent paramIntent) {
    super(paramString, paramIntent);
    this.zzbq = paramInt;
  }
  
  public int getConnectionStatusCode() {
    return this.zzbq;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GooglePlayServicesRepairableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */