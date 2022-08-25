package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class DataHolderCreator implements Parcelable.Creator<DataHolder> {
  public static final int CONTENT_DESCRIPTION = 0;
  
  public DataHolder createFromParcel(Parcel paramParcel) {
    String[] arrayOfString;
    CursorWindow[] arrayOfCursorWindow;
    int i = SafeParcelReader.validateObjectHeader(paramParcel);
    int j = 0;
    int k = j;
    Bundle bundle1 = null;
    Bundle bundle2 = bundle1;
    Bundle bundle3 = bundle2;
    while (paramParcel.dataPosition() < i) {
      int m = SafeParcelReader.readHeader(paramParcel);
      int n = SafeParcelReader.getFieldId(m);
      if (n != 1000) {
        switch (n) {
          default:
            SafeParcelReader.skipUnknownField(paramParcel, m);
            continue;
          case 4:
            bundle3 = SafeParcelReader.createBundle(paramParcel, m);
            continue;
          case 3:
            k = SafeParcelReader.readInt(paramParcel, m);
            continue;
          case 2:
            arrayOfCursorWindow = (CursorWindow[])SafeParcelReader.createTypedArray(paramParcel, m, CursorWindow.CREATOR);
            continue;
          case 1:
            break;
        } 
        arrayOfString = SafeParcelReader.createStringArray(paramParcel, m);
        continue;
      } 
      j = SafeParcelReader.readInt(paramParcel, m);
    } 
    SafeParcelReader.ensureAtEnd(paramParcel, i);
    DataHolder dataHolder = new DataHolder(j, arrayOfString, arrayOfCursorWindow, k, bundle3);
    dataHolder.validateContents();
    return dataHolder;
  }
  
  public DataHolder[] newArray(int paramInt) {
    return new DataHolder[paramInt];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\DataHolderCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */