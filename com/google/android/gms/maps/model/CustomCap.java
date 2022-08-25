package com.google.android.gms.maps.model;

import android.support.annotation.NonNull;

public final class CustomCap extends Cap {
  public final BitmapDescriptor bitmapDescriptor;
  
  public final float refWidth;
  
  public CustomCap(@NonNull BitmapDescriptor paramBitmapDescriptor) {
    this(paramBitmapDescriptor, 10.0F);
  }
  
  public CustomCap(@NonNull BitmapDescriptor paramBitmapDescriptor, float paramFloat) {
    super(bitmapDescriptor, paramFloat);
    this.bitmapDescriptor = paramBitmapDescriptor;
    this.refWidth = paramFloat;
  }
  
  public final String toString() {
    String str = String.valueOf(this.bitmapDescriptor);
    float f = this.refWidth;
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 55);
    stringBuilder.append("[CustomCap: bitmapDescriptor=");
    stringBuilder.append(str);
    stringBuilder.append(" refWidth=");
    stringBuilder.append(f);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\CustomCap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */