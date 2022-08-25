package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class FavaDiagnosticsEntityCreator implements Parcelable.Creator<FavaDiagnosticsEntity> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public FavaDiagnosticsEntity createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    String str = null;
    int k = 0;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(m)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, m);
          continue;
        case 3:
          k = SafeParcelReader.readInt(paramParcel, m);
          continue;
        case 2:
          str = SafeParcelReader.createString(paramParcel, m);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, m);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new FavaDiagnosticsEntity(j, str, k);
  }
  
  public FavaDiagnosticsEntity[] newArray(int paramInt) {
    return new FavaDiagnosticsEntity[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\FavaDiagnosticsEntityCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */