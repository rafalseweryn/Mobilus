package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "StreetViewSourceCreator")
@Reserved({1})
public final class StreetViewSource extends AbstractSafeParcelable {
  public static final Parcelable.Creator<StreetViewSource> CREATOR = new zzq();
  
  public static final StreetViewSource DEFAULT = new StreetViewSource(0);
  
  public static final StreetViewSource OUTDOOR = new StreetViewSource(1);
  
  private static final String TAG = "StreetViewSource";
  
  @Field(getter = "getType", id = 2)
  private final int type;
  
  @Constructor
  public StreetViewSource(@Param(id = 2) int paramInt) {
    this.type = paramInt;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof StreetViewSource))
      return false; 
    paramObject = paramObject;
    return (this.type == ((StreetViewSource)paramObject).type);
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.type) });
  }
  
  public final String toString() {
    switch (this.type) {
      default:
        str = String.format("UNKNOWN(%s)", new Object[] { Integer.valueOf(this.type) });
        return String.format("StreetViewSource:%s", new Object[] { str });
      case 1:
        str = "OUTDOOR";
        return String.format("StreetViewSource:%s", new Object[] { str });
      case 0:
        break;
    } 
    String str = "DEFAULT";
    return String.format("StreetViewSource:%s", new Object[] { str });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 2, this.type);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\StreetViewSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */