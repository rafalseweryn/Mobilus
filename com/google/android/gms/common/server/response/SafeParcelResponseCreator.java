package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class SafeParcelResponseCreator implements Parcelable.Creator<SafeParcelResponse> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public SafeParcelResponse createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    Parcel parcel = null;
    int j = 0;
    FieldMappingDictionary fieldMappingDictionary = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 3:
          fieldMappingDictionary = (FieldMappingDictionary)SafeParcelReader.createParcelable(paramParcel, k, FieldMappingDictionary.CREATOR);
          continue;
        case 2:
          parcel = SafeParcelReader.createParcel(paramParcel, k);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new SafeParcelResponse(j, parcel, fieldMappingDictionary);
  }
  
  public SafeParcelResponse[] newArray(int paramInt) {
    return new SafeParcelResponse[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\SafeParcelResponseCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */