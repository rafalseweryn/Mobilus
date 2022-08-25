package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class FieldMapPairCreator implements Parcelable.Creator<FieldMappingDictionary.FieldMapPair> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public FieldMappingDictionary.FieldMapPair createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    String str = null;
    int j = 0;
    FastJsonResponse.Field<?, ?> field = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 3:
          field = (FastJsonResponse.Field)SafeParcelReader.createParcelable(paramParcel, k, FastJsonResponse.Field.CREATOR);
          continue;
        case 2:
          str = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new FieldMappingDictionary.FieldMapPair(j, str, field);
  }
  
  public FieldMappingDictionary.FieldMapPair[] newArray(int paramInt) {
    return new FieldMappingDictionary.FieldMapPair[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FieldMapPairCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */