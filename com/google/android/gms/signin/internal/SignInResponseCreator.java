package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class SignInResponseCreator implements Parcelable.Creator<SignInResponse> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public SignInResponse createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    ConnectionResult connectionResult = null;
    int j = 0;
    ResolveAccountResponse resolveAccountResponse = null;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 3:
          resolveAccountResponse = (ResolveAccountResponse)SafeParcelReader.createParcelable(paramParcel, k, ResolveAccountResponse.CREATOR);
          continue;
        case 2:
          connectionResult = (ConnectionResult)SafeParcelReader.createParcelable(paramParcel, k, ConnectionResult.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new SignInResponse(j, connectionResult, resolveAccountResponse);
  }
  
  public SignInResponse[] newArray(int paramInt) {
    return new SignInResponse[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\SignInResponseCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */