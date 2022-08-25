package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class BitmapTeleporterCreator implements Parcelable.Creator<BitmapTeleporter> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public BitmapTeleporter createFromParcel(Parcel paramParcel) {
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    ParcelFileDescriptor parcelFileDescriptor = null;
    int k = 0;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      switch (SafeParcelReader.getFieldId(m)) {
        default:
          SafeParcelReader.skipUnknownField(paramParcel, m);
          continue;
        case 3:
          k = SafeParcelReader.readInt(paramParcel, m);
          continue;
        case 2:
          parcelFileDescriptor = (ParcelFileDescriptor)SafeParcelReader.createParcelable(paramParcel, m, ParcelFileDescriptor.CREATOR);
          continue;
        case 1:
          break;
      } 
      j = SafeParcelReader.readInt(paramParcel, m);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    return new BitmapTeleporter(j, parcelFileDescriptor, k);
  }
  
  public BitmapTeleporter[] newArray(int paramInt) {
    return new BitmapTeleporter[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\BitmapTeleporterCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */