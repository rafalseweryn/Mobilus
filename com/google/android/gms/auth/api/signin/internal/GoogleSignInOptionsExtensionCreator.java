package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class GoogleSignInOptionsExtensionCreator implements Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public GoogleSignInOptionsExtensionParcelable createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    Bundle bundle = null;
    int k = 0;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(m)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, m);
          continue;
        case 3:
          bundle = SafeParcelReader.createBundle(paramParcel, m);
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
    return new GoogleSignInOptionsExtensionParcelable(j, k, bundle);
  }
  
  public GoogleSignInOptionsExtensionParcelable[] newArray(int paramInt) {
    return new GoogleSignInOptionsExtensionParcelable[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\internal\GoogleSignInOptionsExtensionCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */