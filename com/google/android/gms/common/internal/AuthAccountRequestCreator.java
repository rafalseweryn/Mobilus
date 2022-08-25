package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class AuthAccountRequestCreator implements Parcelable.Creator<AuthAccountRequest> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public AuthAccountRequest createFromParcel(Parcel paramParcel) {
    IBinder iBinder;
    Scope[] arrayOfScope;
    Integer integer1;
    Integer integer2;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    Account account1 = null;
    Account account2 = account1;
    Account account3 = account2;
    Account account4 = account3;
    Account account5 = account4;
    int j;
    for (j = 0; paramParcel.dataPosition() < i; j = SafeParcelReader.readInt(paramParcel, k)) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 6:
          account5 = (Account)SafeParcelReader.createParcelable(paramParcel, k, Account.CREATOR);
          continue;
        case 5:
          integer2 = SafeParcelReader.readIntegerObject(paramParcel, k);
          continue;
        case 4:
          integer1 = SafeParcelReader.readIntegerObject(paramParcel, k);
          continue;
        case 3:
          arrayOfScope = (Scope[])SafeParcelReader.createTypedArray(paramParcel, k, Scope.CREATOR);
          continue;
        case 2:
          iBinder = SafeParcelReader.readIBinder(paramParcel, k);
          continue;
        case 1:
          break;
      } 
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new AuthAccountRequest(j, iBinder, arrayOfScope, integer1, integer2, account5);
  }
  
  public AuthAccountRequest[] newArray(int paramInt) {
    return new AuthAccountRequest[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\AuthAccountRequestCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */