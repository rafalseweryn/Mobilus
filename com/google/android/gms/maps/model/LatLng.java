package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "LatLngCreator")
@Reserved({1})
public final class LatLng extends AbstractSafeParcelable implements ReflectedParcelable {
  @KeepForSdk
  public static final Parcelable.Creator<LatLng> CREATOR = new zzf();
  
  @Field(id = 2)
  public final double latitude;
  
  @Field(id = 3)
  public final double longitude;
  
  @Constructor
  public LatLng(@Param(id = 2) double paramDouble1, @Param(id = 3) double paramDouble2) {
    if (-180.0D > paramDouble2 || paramDouble2 >= 180.0D)
      paramDouble2 = ((paramDouble2 - 180.0D) % 360.0D + 360.0D) % 360.0D - 180.0D; 
    this.longitude = paramDouble2;
    this.latitude = Math.max(-90.0D, Math.min(90.0D, paramDouble1));
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LatLng))
      return false; 
    paramObject = paramObject;
    return (Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(((LatLng)paramObject).latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(((LatLng)paramObject).longitude));
  }
  
  public final int hashCode() {
    long l = Double.doubleToLongBits(this.latitude);
    int i = (int)(l ^ l >>> 32L);
    l = Double.doubleToLongBits(this.longitude);
    return (i + 31) * 31 + (int)(l >>> 32L ^ l);
  }
  
  public final String toString() {
    double d1 = this.latitude;
    double d2 = this.longitude;
    StringBuilder stringBuilder = new StringBuilder(60);
    stringBuilder.append("lat/lng: (");
    stringBuilder.append(d1);
    stringBuilder.append(",");
    stringBuilder.append(d2);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeDouble(paramParcel, 2, this.latitude);
    SafeParcelWriter.writeDouble(paramParcel, 3, this.longitude);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\LatLng.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */