package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class SignInButtonConfigCreator implements Parcelable.Creator<SignInButtonConfig> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public SignInButtonConfig createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    int k = 0;
    Scope[] arrayOfScope = null;
    int m = k;
    while (paramParcel.dataPosition() < i) {
      int n = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(n)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, n);
          continue;
        case 4:
          arrayOfScope = (Scope[])SafeParcelReader.createTypedArray(paramParcel, n, Scope.CREATOR);
          continue;
        case 3:
          k = SafeParcelReader.readInt(paramParcel, n);
          continue;
        case 2:
          m = SafeParcelReader.readInt(paramParcel, n);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, n);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new SignInButtonConfig(j, m, k, arrayOfScope);
  }
  
  public SignInButtonConfig[] newArray(int paramInt) {
    return new SignInButtonConfig[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\SignInButtonConfigCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */