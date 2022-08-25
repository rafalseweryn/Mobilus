package com.google.android.gms.maps.model;

public final class Gap extends PatternItem {
  public final float length;
  
  public Gap(float paramFloat) {
    super(2, Float.valueOf(Math.max(paramFloat, 0.0F)));
    this.length = Math.max(paramFloat, 0.0F);
  }
  
  public final String toString() {
    float f = this.length;
    StringBuilder stringBuilder = new StringBuilder(29);
    stringBuilder.append("[Gap: length=");
    stringBuilder.append(f);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\Gap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */