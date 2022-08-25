package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class SignInRequestCreator implements Parcelable.Creator<SignInRequest> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public SignInRequest createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    ResolveAccountRequest resolveAccountRequest = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 2:
          resolveAccountRequest = (ResolveAccountRequest)SafeParcelReader.createParcelable(paramParcel, k, ResolveAccountRequest.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new SignInRequest(j, resolveAccountRequest);
  }
  
  public SignInRequest[] newArray(int paramInt) {
    return new SignInRequest[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\SignInRequestCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */