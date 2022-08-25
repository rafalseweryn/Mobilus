package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class GoogleSignInAccountCreator implements Parcelable.Creator<GoogleSignInAccount> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public GoogleSignInAccount createFromParcel(Parcel paramParcel) {
    Uri uri;
    ArrayList<Scope> arrayList;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    String str1 = null;
    String str2 = str1;
    String str3 = str2;
    String str4 = str3;
    String str5 = str4;
    String str6 = str5;
    String str7 = str6;
    String str8 = str7;
    String str9 = str8;
    String str10 = str9;
    int j = 0;
    long l = 0L;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 12:
          str10 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 11:
          str9 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 10:
          arrayList = SafeParcelReader.createTypedList(paramParcel, k, Scope.CREATOR);
          continue;
        case 9:
          str7 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 8:
          l = SafeParcelReader.readLong(paramParcel, k);
          continue;
        case 7:
          str6 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 6:
          uri = (Uri)SafeParcelReader.createParcelable(paramParcel, k, Uri.CREATOR);
          continue;
        case 5:
          str4 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 4:
          str3 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 3:
          str2 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 2:
          str1 = SafeParcelReader.createString(paramParcel, k);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new GoogleSignInAccount(j, str1, str2, str3, str4, uri, str6, l, str7, arrayList, str9, str10);
  }
  
  public GoogleSignInAccount[] newArray(int paramInt) {
    return new GoogleSignInAccount[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInAccountCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */