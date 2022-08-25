package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class FeatureCreator implements Parcelable.Creator<Feature> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public Feature createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    String str = null;
    int j = 0;
    long l = -1L;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 3:
          l = SafeParcelReader.readLong(paramParcel, k);
          continue;
        case 2:
          j = SafeParcelReader.readInt(paramParcel, k);
          continue;
        case 1:
          break;
      } 
      str = SafeParcelReader.createString(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new Feature(str, j, l);
  }
  
  public Feature[] newArray(int paramInt) {
    return new Feature[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\FeatureCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */