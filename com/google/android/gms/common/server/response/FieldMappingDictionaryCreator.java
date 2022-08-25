package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class FieldMappingDictionaryCreator implements Parcelable.Creator<FieldMappingDictionary> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public FieldMappingDictionary createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    ArrayList<FieldMappingDictionary.Entry> arrayList = null;
    int j = 0;
    String str = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 3:
          str = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 2:
          arrayList = SafeParcelReader.createTypedList(paramParcel, k, FieldMappingDictionary.Entry.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new FieldMappingDictionary(j, arrayList, str);
  }
  
  public FieldMappingDictionary[] newArray(int paramInt) {
    return new FieldMappingDictionary[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FieldMappingDictionaryCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */