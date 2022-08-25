package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class StringToIntConverterCreator implements Parcelable.Creator<StringToIntConverter> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public StringToIntConverter createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    ArrayList<StringToIntConverter.Entry> arrayList = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 2:
          arrayList = SafeParcelReader.createTypedList(paramParcel, k, StringToIntConverter.Entry.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new StringToIntConverter(j, arrayList);
  }
  
  public StringToIntConverter[] newArray(int paramInt) {
    return new StringToIntConverter[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\converter\StringToIntConverterCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */