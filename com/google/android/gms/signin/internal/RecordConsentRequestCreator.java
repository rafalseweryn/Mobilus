package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class RecordConsentRequestCreator implements Parcelable.Creator<RecordConsentRequest> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public RecordConsentRequest createFromParcel(Parcel paramParcel) {
    Scope[] arrayOfScope;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    Account account = null;
    int j = 0;
    String str1 = null;
    String str2 = str1;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 4:
          str2 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 3:
          arrayOfScope = (Scope[])SafeParcelReader.createTypedArray(paramParcel, k, Scope.CREATOR);
          continue;
        case 2:
          account = (Account)SafeParcelReader.createParcelable(paramParcel, k, Account.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new RecordConsentRequest(j, account, arrayOfScope, str2);
  }
  
  public RecordConsentRequest[] newArray(int paramInt) {
    return new RecordConsentRequest[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\RecordConsentRequestCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */