package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "StreetViewPanoramaCameraCreator")
@Reserved({1})
public class StreetViewPanoramaCamera extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<StreetViewPanoramaCamera> CREATOR = new zzm();
  
  @Field(id = 4)
  public final float bearing;
  
  @Field(id = 3)
  public final float tilt;
  
  @Field(id = 2)
  public final float zoom;
  
  @NonNull
  private final StreetViewPanoramaOrientation zzef;
  
  @Constructor
  public StreetViewPanoramaCamera(@Param(id = 2) float paramFloat1, @Param(id = 3) float paramFloat2, @Param(id = 4) float paramFloat3) {
    boolean bool;
    if (-90.0F <= paramFloat2 && paramFloat2 <= 90.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder(62);
    stringBuilder.append("Tilt needs to be between -90 and 90 inclusive: ");
    stringBuilder.append(paramFloat2);
    Preconditions.checkArgument(bool, stringBuilder.toString());
    float f = paramFloat1;
    if (paramFloat1 <= 0.0D)
      f = 0.0F; 
    this.zoom = f;
    this.tilt = 0.0F + paramFloat2;
    if (paramFloat3 <= 0.0D) {
      paramFloat1 = paramFloat3 % 360.0F + 360.0F;
    } else {
      paramFloat1 = paramFloat3;
    } 
    this.bearing = paramFloat1 % 360.0F;
    this.zzef = (new StreetViewPanoramaOrientation.Builder()).tilt(paramFloat2).bearing(paramFloat3).build();
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public static Builder builder(@NonNull StreetViewPanoramaCamera paramStreetViewPanoramaCamera) {
    return new Builder(paramStreetViewPanoramaCamera);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof StreetViewPanoramaCamera))
      return false; 
    paramObject = paramObject;
    return (Float.floatToIntBits(this.zoom) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).bearing));
  }
  
  @NonNull
  public StreetViewPanoramaOrientation getOrientation() {
    return this.zzef;
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing) });
  }
  
  public String toString() {
    return Objects.toStringHelper(this).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeFloat(paramParcel, 2, this.zoom);
    SafeParcelWriter.writeFloat(paramParcel, 3, this.tilt);
    SafeParcelWriter.writeFloat(paramParcel, 4, this.bearing);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  public static final class Builder {
    public float bearing;
    
    public float tilt;
    
    public float zoom;
    
    public Builder() {}
    
    public Builder(@NonNull StreetViewPanoramaCamera param1StreetViewPanoramaCamera) {
      Preconditions.checkNotNull(param1StreetViewPanoramaCamera, "StreetViewPanoramaCamera");
      this.zoom = param1StreetViewPanoramaCamera.zoom;
      this.bearing = param1StreetViewPanoramaCamera.bearing;
      this.tilt = param1StreetViewPanoramaCamera.tilt;
    }
    
    public final Builder bearing(float param1Float) {
      this.bearing = param1Float;
      return this;
    }
    
    public final StreetViewPanoramaCamera build() {
      return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
    }
    
    public final Builder orientation(StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation) {
      Preconditions.checkNotNull(param1StreetViewPanoramaOrientation, "StreetViewPanoramaOrientation");
      this.tilt = param1StreetViewPanoramaOrientation.tilt;
      this.bearing = param1StreetViewPanoramaOrientation.bearing;
      return this;
    }
    
    public final Builder tilt(float param1Float) {
      this.tilt = param1Float;
      return this;
    }
    
    public final Builder zoom(float param1Float) {
      this.zoom = param1Float;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\StreetViewPanoramaCamera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */