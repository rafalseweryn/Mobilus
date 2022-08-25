package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class GoogleCertificatesQueryCreator implements Parcelable.Creator<GoogleCertificatesQuery> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public GoogleCertificatesQuery createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    String str = null;
    boolean bool = false;
    IBinder iBinder = null;
    while (paramParcel.dataPosition() < i) {
      int j = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(j)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, j);
          continue;
        case 3:
          bool = SafeParcelReader.readBoolean(paramParcel, j);
          continue;
        case 2:
          iBinder = SafeParcelReader.readIBinder(paramParcel, j);
          continue;
        case 1:
          break;
      } 
      str = SafeParcelReader.createString(paramParcel, j);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new GoogleCertificatesQuery(str, iBinder, bool);
  }
  
  public GoogleCertificatesQuery[] newArray(int paramInt) {
    return new GoogleCertificatesQuery[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GoogleCertificatesQueryCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */