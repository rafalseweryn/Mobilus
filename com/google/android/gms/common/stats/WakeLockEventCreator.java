package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class WakeLockEventCreator implements Parcelable.Creator<WakeLockEvent> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public WakeLockEvent createFromParcel(Parcel paramParcel) {
    ArrayList<String> arrayList;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    long l1 = 0L;
    long l2 = l1;
    long l3 = l2;
    int j = 0;
    int k = j;
    int m = k;
    int n = m;
    String str1 = null;
    String str2 = str1;
    String str3 = str2;
    String str4 = str3;
    String str5 = str4;
    String str6 = str5;
    float f = 0.0F;
    while (paramParcel.dataPosition() < i) {
      int i1 = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(i1)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, i1);
          continue;
        case 17:
          str6 = SafeParcelReader.createString(paramParcel, i1);
          continue;
        case 16:
          l3 = SafeParcelReader.readLong(paramParcel, i1);
          continue;
        case 15:
          f = SafeParcelReader.readFloat(paramParcel, i1);
          continue;
        case 14:
          n = SafeParcelReader.readInt(paramParcel, i1);
          continue;
        case 13:
          str5 = SafeParcelReader.createString(paramParcel, i1);
          continue;
        case 12:
          str3 = SafeParcelReader.createString(paramParcel, i1);
          continue;
        case 11:
          k = SafeParcelReader.readInt(paramParcel, i1);
          continue;
        case 10:
          str4 = SafeParcelReader.createString(paramParcel, i1);
          continue;
        case 8:
          l2 = SafeParcelReader.readLong(paramParcel, i1);
          continue;
        case 6:
          arrayList = SafeParcelReader.createStringList(paramParcel, i1);
          continue;
        case 5:
          m = SafeParcelReader.readInt(paramParcel, i1);
          continue;
        case 4:
          str1 = SafeParcelReader.createString(paramParcel, i1);
          continue;
        case 2:
          l1 = SafeParcelReader.readLong(paramParcel, i1);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, i1);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new WakeLockEvent(j, l1, k, str1, m, arrayList, str3, l2, n, str4, str5, f, l3, str6);
  }
  
  public WakeLockEvent[] newArray(int paramInt) {
    return new WakeLockEvent[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\WakeLockEventCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */