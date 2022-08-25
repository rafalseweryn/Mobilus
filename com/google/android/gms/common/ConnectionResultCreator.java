package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ConnectionResultCreator implements Parcelable.Creator<ConnectionResult> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ConnectionResult createFromParcel(Parcel paramParcel) {
    PendingIntent pendingIntent;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = str1;
    int k = 0;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(m)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, m);
          continue;
        case 4:
          str2 = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 3:
          pendingIntent = (PendingIntent)SafeParcelReader.createParcelable(paramParcel, m, PendingIntent.CREATOR);
          continue;
        case 2:
          k = SafeParcelReader.readInt(paramParcel, m);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, m);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new ConnectionResult(j, k, pendingIntent, str2);
  }
  
  public ConnectionResult[] newArray(int paramInt) {
    return new ConnectionResult[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\ConnectionResultCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */