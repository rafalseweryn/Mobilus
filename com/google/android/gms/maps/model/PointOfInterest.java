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

@Class(creator = "PointOfInterestCreator")
@Reserved({1})
public final class PointOfInterest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzj();
  
  @Field(id = 2)
  public final LatLng latLng;
  
  @Field(id = 4)
  public final String name;
  
  @Field(id = 3)
  public final String placeId;
  
  @Constructor
  public PointOfInterest(@Param(id = 2) LatLng paramLatLng, @Param(id = 3) String paramString1, @Param(id = 4) String paramString2) {
    this.latLng = paramLatLng;
    this.placeId = paramString1;
    this.name = paramString2;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.latLng, paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 3, this.placeId, false);
    SafeParcelWriter.writeString(paramParcel, 4, this.name, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\PointOfInterest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */