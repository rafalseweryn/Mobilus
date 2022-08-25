package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class AuthAccountResultCreator implements Parcelable.Creator<AuthAccountResult> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public AuthAccountResult createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    Intent intent = null;
    int k = 0;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(m)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, m);
          continue;
        case 3:
          intent = (Intent)SafeParcelReader.createParcelable(paramParcel, m, Intent.CREATOR);
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
    return new AuthAccountResult(j, k, intent);
  }
  
  public AuthAccountResult[] newArray(int paramInt) {
    return new AuthAccountResult[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\AuthAccountResultCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */