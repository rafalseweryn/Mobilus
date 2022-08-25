package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class CheckServerAuthResultCreator implements Parcelable.Creator<CheckServerAuthResult> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public CheckServerAuthResult createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    ArrayList<Scope> arrayList = null;
    boolean bool = false;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 3:
          arrayList = SafeParcelReader.createTypedList(paramParcel, k, Scope.CREATOR);
          continue;
        case 2:
          bool = SafeParcelReader.readBoolean(paramParcel, k);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new CheckServerAuthResult(j, bool, arrayList);
  }
  
  public CheckServerAuthResult[] newArray(int paramInt) {
    return new CheckServerAuthResult[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\CheckServerAuthResultCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */