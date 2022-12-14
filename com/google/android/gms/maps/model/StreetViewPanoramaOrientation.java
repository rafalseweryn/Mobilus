package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "StreetViewPanoramaOrientationCreator")
@Reserved({1})
public class StreetViewPanoramaOrientation extends AbstractSafeParcelable {
  public static final Parcelable.Creator<StreetViewPanoramaOrientation> CREATOR = new zzp();
  
  @Field(id = 3)
  public final float bearing;
  
  @Field(id = 2)
  public final float tilt;
  
  @Constructor
  public StreetViewPanoramaOrientation(@Param(id = 2) float paramFloat1, @Param(id = 3) float paramFloat2) {
    boolean bool;
    if (-90.0F <= paramFloat1 && paramFloat1 <= 90.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder(62);
    stringBuilder.append("Tilt needs to be between -90 and 90 inclusive: ");
    stringBuilder.append(paramFloat1);
    Preconditions.checkArgument(bool, stringBuilder.toString());
    this.tilt = paramFloat1 + 0.0F;
    paramFloat1 = paramFloat2;
    if (paramFloat2 <= 0.0D)
      paramFloat1 = paramFloat2 % 360.0F + 360.0F; 
    this.bearing = paramFloat1 % 360.0F;
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public static Builder builder(@NonNull StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation) {
    return new Builder(paramStreetViewPanoramaOrientation);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof StreetViewPanoramaOrientation))
      return false; 
    paramObject = paramObject;
    return (Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((StreetViewPanoramaOrientation)paramObject).tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaOrientation)paramObject).bearing));
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { Float.valueOf(this.tilt), Float.valueOf(this.bearing) });
  }
  
  public String toString() {
    return Objects.toStringHelper(this).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeFloat(paramParcel, 2, this.tilt);
    SafeParcelWriter.writeFloat(paramParcel, 3, this.bearing);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  public static final class Builder {
    public float bearing;
    
    public float tilt;
    
    public Builder() {}
    
    public Builder(@NonNull StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation) {
      Preconditions.checkNotNull(param1StreetViewPanoramaOrientation, "StreetViewPanoramaOrientation");
      this.bearing = param1StreetViewPanoramaOrientation.bearing;
      this.tilt = param1StreetViewPanoramaOrientation.tilt;
    }
    
    public final Builder bearing(float param1Float) {
      this.bearing = param1Float;
      return this;
    }
    
    public final StreetViewPanoramaOrientation build() {
      return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
    }
    
    public final Builder tilt(float param1Float) {
      this.tilt = param1Float;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\StreetViewPanoramaOrientation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */