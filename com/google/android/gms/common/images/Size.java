package com.google.android.gms.common.images;

public final class Size {
  private final int zzps;
  
  private final int zzpt;
  
  public Size(int paramInt1, int paramInt2) {
    this.zzps = paramInt1;
    this.zzpt = paramInt2;
  }
  
  public static Size parseSize(String paramString) throws NumberFormatException {
    if (paramString == null)
      throw new IllegalArgumentException("string must not be null"); 
    int i = paramString.indexOf('*');
    int j = i;
    if (i < 0)
      j = paramString.indexOf('x'); 
    if (j < 0)
      throw zzi(paramString); 
    try {
      return new Size(Integer.parseInt(paramString.substring(0, j)), Integer.parseInt(paramString.substring(j + 1)));
    } catch (NumberFormatException numberFormatException) {
      throw zzi(paramString);
    } 
  }
  
  private static NumberFormatException zzi(String paramString) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 16);
    stringBuilder.append("Invalid Size: \"");
    stringBuilder.append(paramString);
    stringBuilder.append("\"");
    throw new NumberFormatException(stringBuilder.toString());
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof Size) {
      paramObject = paramObject;
      if (this.zzps == ((Size)paramObject).zzps && this.zzpt == ((Size)paramObject).zzpt)
        return true; 
    } 
    return false;
  }
  
  public final int getHeight() {
    return this.zzpt;
  }
  
  public final int getWidth() {
    return this.zzps;
  }
  
  public final int hashCode() {
    int i = this.zzpt;
    int j = this.zzps;
    return (this.zzps >>> 16 | j << 16) ^ i;
  }
  
  public final String toString() {
    int i = this.zzps;
    int j = this.zzpt;
    StringBuilder stringBuilder = new StringBuilder(23);
    stringBuilder.append(i);
    stringBuilder.append("x");
    stringBuilder.append(j);
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\Size.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */