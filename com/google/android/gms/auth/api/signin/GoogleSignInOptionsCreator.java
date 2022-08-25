package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class GoogleSignInOptionsCreator implements Parcelable.Creator<GoogleSignInOptions> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public GoogleSignInOptions createFromParcel(Parcel paramParcel) {
    int j;
    Account account;
    String str1;
    String str2;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    boolean bool1 = false;
    boolean bool2 = bool1;
    boolean bool3 = bool2;
    boolean bool4 = bool3;
    ArrayList<Scope> arrayList1 = null;
    ArrayList<Scope> arrayList2 = arrayList1;
    ArrayList<Scope> arrayList3 = arrayList2;
    ArrayList<Scope> arrayList4 = arrayList3;
    ArrayList<Scope> arrayList5 = arrayList4;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 9:
          arrayList5 = SafeParcelReader.createTypedList(paramParcel, k, GoogleSignInOptionsExtensionParcelable.CREATOR);
          continue;
        case 8:
          str2 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 7:
          str1 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 6:
          bool4 = SafeParcelReader.readBoolean(paramParcel, k);
          continue;
        case 5:
          bool3 = SafeParcelReader.readBoolean(paramParcel, k);
          continue;
        case 4:
          bool2 = SafeParcelReader.readBoolean(paramParcel, k);
          continue;
        case 3:
          account = (Account)SafeParcelReader.createParcelable(paramParcel, k, Account.CREATOR);
          continue;
        case 2:
          arrayList1 = SafeParcelReader.createTypedList(paramParcel, k, Scope.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new GoogleSignInOptions(j, arrayList1, account, bool2, bool3, bool4, str1, str2, (ArrayList)arrayList5);
  }
  
  public GoogleSignInOptions[] newArray(int paramInt) {
    return new GoogleSignInOptions[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInOptionsCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */