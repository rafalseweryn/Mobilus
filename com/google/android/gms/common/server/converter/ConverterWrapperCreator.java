package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ConverterWrapperCreator implements Parcelable.Creator<ConverterWrapper> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ConverterWrapper createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    StringToIntConverter stringToIntConverter = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 2:
          stringToIntConverter = (StringToIntConverter)SafeParcelReader.createParcelable(paramParcel, k, StringToIntConverter.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new ConverterWrapper(j, stringToIntConverter);
  }
  
  public ConverterWrapper[] newArray(int paramInt) {
    return new ConverterWrapper[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\converter\ConverterWrapperCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */