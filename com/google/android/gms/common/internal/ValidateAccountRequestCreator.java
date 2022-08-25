package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ValidateAccountRequestCreator implements Parcelable.Creator<ValidateAccountRequest> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ValidateAccountRequest createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j;
    for (j = 0; paramParcel.dataPosition() < i; j = SafeParcelReader.readInt(paramParcel, k)) {
      int k = SafeParcelReader.readHeader(paramParcel);
      if (SafeParcelReader.getFieldId(k) != 1) {
        SafeParcelReader.skipUnknownField(paramParcel, k);
        continue;
      } 
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new ValidateAccountRequest(j);
  }
  
  public ValidateAccountRequest[] newArray(int paramInt) {
    return new ValidateAccountRequest[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ValidateAccountRequestCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */