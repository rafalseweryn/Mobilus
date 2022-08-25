package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class FieldMappingDictionaryEntryCreator implements Parcelable.Creator<FieldMappingDictionary.Entry> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public FieldMappingDictionary.Entry createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    String str = null;
    int j = 0;
    ArrayList<FieldMappingDictionary.FieldMapPair> arrayList = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 3:
          arrayList = SafeParcelReader.createTypedList(paramParcel, k, FieldMappingDictionary.FieldMapPair.CREATOR);
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
    return new FieldMappingDictionary.Entry(j, str, arrayList);
  }
  
  public FieldMappingDictionary.Entry[] newArray(int paramInt) {
    return new FieldMappingDictionary.Entry[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FieldMappingDictionaryEntryCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */