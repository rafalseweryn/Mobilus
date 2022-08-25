package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ResolveAccountResponseCreator implements Parcelable.Creator<ResolveAccountResponse> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ResolveAccountResponse createFromParcel(Parcel paramParcel) {
    IBinder iBinder;
    int j;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    ConnectionResult connectionResult1 = null;
    ConnectionResult connectionResult2 = connectionResult1;
    boolean bool1 = false;
    boolean bool2 = bool1;
    boolean bool3 = bool2;
    while (paramParcel.dataPosition() < i) {
      int k = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(k)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, k);
          continue;
        case 5:
          bool3 = SafeParcelReader.readBoolean(paramParcel, k);
          continue;
        case 4:
          bool2 = SafeParcelReader.readBoolean(paramParcel, k);
          continue;
        case 3:
          connectionResult2 = (ConnectionResult)SafeParcelReader.createParcelable(paramParcel, k, ConnectionResult.CREATOR);
          continue;
        case 2:
          iBinder = SafeParcelReader.readIBinder(paramParcel, k);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, k);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new ResolveAccountResponse(j, iBinder, connectionResult2, bool2, bool3);
  }
  
  public ResolveAccountResponse[] newArray(int paramInt) {
    return new ResolveAccountResponse[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ResolveAccountResponseCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */