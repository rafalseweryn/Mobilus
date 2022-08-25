package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "TileCreator")
@Reserved({1})
public final class Tile extends AbstractSafeParcelable {
  public static final Parcelable.Creator<Tile> CREATOR = new zzr();
  
  @Field(id = 4)
  public final byte[] data;
  
  @Field(id = 3)
  public final int height;
  
  @Field(id = 2)
  public final int width;
  
  @Constructor
  public Tile(@Param(id = 2) int paramInt1, @Param(id = 3) int paramInt2, @Param(id = 4) byte[] paramArrayOfbyte) {
    this.width = paramInt1;
    this.height = paramInt2;
    this.data = paramArrayOfbyte;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 2, this.width);
    SafeParcelWriter.writeInt(paramParcel, 3, this.height);
    SafeParcelWriter.writeByteArray(paramParcel, 4, this.data, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\Tile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */