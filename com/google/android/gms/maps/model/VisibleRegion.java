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

@Class(creator = "VisibleRegionCreator")
@Reserved({1})
public final class VisibleRegion extends AbstractSafeParcelable {
  public static final Parcelable.Creator<VisibleRegion> CREATOR = new zzv();
  
  @Field(id = 4)
  public final LatLng farLeft;
  
  @Field(id = 5)
  public final LatLng farRight;
  
  @Field(id = 6)
  public final LatLngBounds latLngBounds;
  
  @Field(id = 2)
  public final LatLng nearLeft;
  
  @Field(id = 3)
  public final LatLng nearRight;
  
  @Constructor
  public VisibleRegion(@Param(id = 2) LatLng paramLatLng1, @Param(id = 3) LatLng paramLatLng2, @Param(id = 4) LatLng paramLatLng3, @Param(id = 5) LatLng paramLatLng4, @Param(id = 6) LatLngBounds paramLatLngBounds) {
    this.nearLeft = paramLatLng1;
    this.nearRight = paramLatLng2;
    this.farLeft = paramLatLng3;
    this.farRight = paramLatLng4;
    this.latLngBounds = paramLatLngBounds;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof VisibleRegion))
      return false; 
    paramObject = paramObject;
    return (this.nearLeft.equals(((VisibleRegion)paramObject).nearLeft) && this.nearRight.equals(((VisibleRegion)paramObject).nearRight) && this.farLeft.equals(((VisibleRegion)paramObject).farLeft) && this.farRight.equals(((VisibleRegion)paramObject).farRight) && this.latLngBounds.equals(((VisibleRegion)paramObject).latLngBounds));
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds });
  }
  
  public final String toString() {
    return Objects.toStringHelper(this).add("nearLeft", this.nearLeft).add("nearRight", this.nearRight).add("farLeft", this.farLeft).add("farRight", this.farRight).add("latLngBounds", this.latLngBounds).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.nearLeft, paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)this.nearRight, paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 4, (Parcelable)this.farLeft, paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 5, (Parcelable)this.farRight, paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 6, (Parcelable)this.latLngBounds, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\VisibleRegion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */