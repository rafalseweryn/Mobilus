package com.google.android.gms.common;

public final class GooglePlayServicesNotAvailableException extends Exception {
  public final int errorCode;
  
  public GooglePlayServicesNotAvailableException(int paramInt) {
    this.errorCode = paramInt;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GooglePlayServicesNotAvailableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */