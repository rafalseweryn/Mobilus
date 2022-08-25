package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ResolveAccountRequestCreator implements Parcelable.Creator<ResolveAccountRequest> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ResolveAccountRequest createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    Account account = null;
    int j = 0;
    GoogleSignInAccount googleSignInAccount = null;
    int k = 0;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(m)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, m);
          continue;
        case 4:
          googleSignInAccount = (GoogleSignInAccount)SafeParcelReader.createParcelable(paramParcel, m, GoogleSignInAccount.CREATOR);
          continue;
        case 3:
          k = SafeParcelReader.readInt(paramParcel, m);
          continue;
        case 2:
          account = (Account)SafeParcelReader.createParcelable(paramParcel, m, Account.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, m);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new ResolveAccountRequest(j, account, k, googleSignInAccount);
  }
  
  public ResolveAccountRequest[] newArray(int paramInt) {
    return new ResolveAccountRequest[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ResolveAccountRequestCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */