package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class GetServiceRequestCreator implements Parcelable.Creator<GetServiceRequest> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public GetServiceRequest createFromParcel(Parcel paramParcel) {
    int j;
    int k;
    int m;
    String str;
    IBinder iBinder;
    Scope[] arrayOfScope;
    Bundle bundle;
    Account account;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    boolean bool1 = false;
    boolean bool2 = bool1;
    boolean bool3 = bool2;
    boolean bool4 = bool3;
    Feature[] arrayOfFeature1 = null;
    Feature[] arrayOfFeature2 = arrayOfFeature1;
    Feature[] arrayOfFeature3 = arrayOfFeature2;
    Feature[] arrayOfFeature4 = arrayOfFeature3;
    Feature[] arrayOfFeature5 = arrayOfFeature4;
    Feature[] arrayOfFeature6 = arrayOfFeature5;
    Feature[] arrayOfFeature7 = arrayOfFeature6;
    while (paramParcel.dataPosition() < i) {
      int n = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(n)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, n);
          continue;
        case 12:
          bool4 = SafeParcelReader.readBoolean(paramParcel, n);
          continue;
        case 11:
          arrayOfFeature7 = (Feature[])SafeParcelReader.createTypedArray(paramParcel, n, Feature.CREATOR);
          continue;
        case 10:
          arrayOfFeature6 = (Feature[])SafeParcelReader.createTypedArray(paramParcel, n, Feature.CREATOR);
          continue;
        case 8:
          account = (Account)SafeParcelReader.createParcelable(paramParcel, n, Account.CREATOR);
          continue;
        case 7:
          bundle = SafeParcelReader.createBundle(paramParcel, n);
          continue;
        case 6:
          arrayOfScope = (Scope[])SafeParcelReader.createTypedArray(paramParcel, n, Scope.CREATOR);
          continue;
        case 5:
          iBinder = SafeParcelReader.readIBinder(paramParcel, n);
          continue;
        case 4:
          str = SafeParcelReader.createString(paramParcel, n);
          continue;
        case 3:
          m = SafeParcelReader.readInt(paramParcel, n);
          continue;
        case 2:
          k = SafeParcelReader.readInt(paramParcel, n);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, n);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new GetServiceRequest(j, k, m, str, iBinder, arrayOfScope, bundle, account, arrayOfFeature6, arrayOfFeature7, bool4);
  }
  
  public GetServiceRequest[] newArray(int paramInt) {
    return new GetServiceRequest[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GetServiceRequestCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */