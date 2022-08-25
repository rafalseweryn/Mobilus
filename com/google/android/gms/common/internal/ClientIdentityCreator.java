package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ClientIdentityCreator implements Parcelable.Creator<ClientIdentity> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ClientIdentity createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    String str = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
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
    return new ClientIdentity(j, str);
  }
  
  public ClientIdentity[] newArray(int paramInt) {
    return new ClientIdentity[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ClientIdentityCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */