package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataBufferSafeParcelable<T extends SafeParcelable> extends AbstractDataBuffer<T> {
  public static final String DATA_FIELD = "data";
  
  private static final String[] zznk = new String[] { "data" };
  
  private final Parcelable.Creator<T> zznl;
  
  public DataBufferSafeParcelable(DataHolder paramDataHolder, Parcelable.Creator<T> paramCreator) {
    super(paramDataHolder);
    this.zznl = paramCreator;
  }
  
  public static <T extends SafeParcelable> void addValue(DataHolder.Builder paramBuilder, T paramT) {
    Parcel parcel = Parcel.obtain();
    paramT.writeToParcel(parcel, 0);
    ContentValues contentValues = new ContentValues();
    contentValues.put("data", parcel.marshall());
    paramBuilder.withRow(contentValues);
    parcel.recycle();
  }
  
  public static DataHolder.Builder buildDataHolder() {
    return DataHolder.builder(zznk);
  }
  
  public T get(int paramInt) {
    byte[] arrayOfByte = this.mDataHolder.getByteArray("data", paramInt, this.mDataHolder.getWindowIndex(paramInt));
    Parcel parcel = Parcel.obtain();
    parcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
    parcel.setDataPosition(0);
    SafeParcelable safeParcelable = (SafeParcelable)this.zznl.createFromParcel(parcel);
    parcel.recycle();
    return (T)safeParcelable;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\DataBufferSafeParcelable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */