package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

public final class UnsupportedApiCallException extends UnsupportedOperationException {
  private final Feature zzdr;
  
  public UnsupportedApiCallException(Feature paramFeature) {
    this.zzdr = paramFeature;
  }
  
  public final String getMessage() {
    String str = String.valueOf(this.zzdr);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 8);
    stringBuilder.append("Missing ");
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\UnsupportedApiCallException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */