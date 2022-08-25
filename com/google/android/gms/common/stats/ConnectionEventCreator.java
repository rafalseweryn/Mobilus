package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ConnectionEventCreator implements Parcelable.Creator<ConnectionEvent> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ConnectionEvent createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    int k = j;
    long l1 = 0L;
    long l2 = l1;
    long l3 = l2;
    String str1 = null;
    String str2 = str1;
    String str3 = str2;
    String str4 = str3;
    String str5 = str4;
    String str6 = str5;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(m)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, m);
          continue;
        case 13:
          str6 = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 12:
          k = SafeParcelReader.readInt(paramParcel, m);
          continue;
        case 11:
          l3 = SafeParcelReader.readLong(paramParcel, m);
          continue;
        case 10:
          l2 = SafeParcelReader.readLong(paramParcel, m);
          continue;
        case 8:
          str5 = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 7:
          str4 = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 6:
          str3 = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 5:
          str2 = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 4:
          str1 = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 2:
          l1 = SafeParcelReader.readLong(paramParcel, m);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, m);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new ConnectionEvent(j, l1, k, str1, str2, str3, str4, str5, str6, l2, l3);
  }
  
  public ConnectionEvent[] newArray(int paramInt) {
    return new ConnectionEvent[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\ConnectionEventCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */