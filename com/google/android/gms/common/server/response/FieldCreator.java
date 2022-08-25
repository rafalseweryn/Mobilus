package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.converter.ConverterWrapper;

public class FieldCreator implements Parcelable.Creator<FastJsonResponse.Field> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public FastJsonResponse.Field createFromParcel(Parcel paramParcel) {
    String str1;
    String str2;
    boolean bool1;
    boolean bool2;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    ConverterWrapper converterWrapper1 = null;
    ConverterWrapper converterWrapper2 = converterWrapper1;
    ConverterWrapper converterWrapper3 = converterWrapper2;
    int j = 0;
    int k = j;
    int m = k;
    int n = m;
    int i1 = n;
    int i2 = i1;
    while (paramParcel.dataPosition() < i) {
      int i3 = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(i3)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, i3);
          continue;
        case 9:
          converterWrapper3 = (ConverterWrapper)SafeParcelReader.createParcelable(paramParcel, i3, ConverterWrapper.CREATOR);
          continue;
        case 8:
          str2 = SafeParcelReader.createString(paramParcel, i3);
          continue;
        case 7:
          i2 = SafeParcelReader.readInt(paramParcel, i3);
          continue;
        case 6:
          str1 = SafeParcelReader.createString(paramParcel, i3);
          continue;
        case 5:
          bool2 = SafeParcelReader.readBoolean(paramParcel, i3);
          continue;
        case 4:
          n = SafeParcelReader.readInt(paramParcel, i3);
          continue;
        case 3:
          bool1 = SafeParcelReader.readBoolean(paramParcel, i3);
          continue;
        case 2:
          k = SafeParcelReader.readInt(paramParcel, i3);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, i3);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new FastJsonResponse.Field<>(j, k, bool1, n, bool2, str1, i2, str2, converterWrapper3);
  }
  
  public FastJsonResponse.Field[] newArray(int paramInt) {
    return new FastJsonResponse.Field[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FieldCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */