package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ConnectionInfoCreator implements Parcelable.Creator<ConnectionInfo> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public ConnectionInfo createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    Bundle bundle = null;
    Feature[] arrayOfFeature = null;
    while (paramParcel.dataPosition() < i) {
      int j = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(j)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, j);
          continue;
        case 2:
          arrayOfFeature = (Feature[])SafeParcelReader.createTypedArray(paramParcel, j, Feature.CREATOR);
          continue;
        case 1:
          break;
      } 
      bundle = SafeParcelReader.createBundle(paramParcel, j);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new ConnectionInfo(bundle, arrayOfFeature);
  }
  
  public ConnectionInfo[] newArray(int paramInt) {
    return new ConnectionInfo[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ConnectionInfoCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */