package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.dynamic.IObjectWrapper;

@Class(creator = "GroundOverlayOptionsCreator")
@Reserved({1})
public final class GroundOverlayOptions extends AbstractSafeParcelable {
  public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
  
  public static final float NO_DIMENSION = -1.0F;
  
  @Field(getter = "getBearing", id = 7)
  private float bearing;
  
  @Field(getter = "getHeight", id = 5)
  private float height;
  
  @Field(getter = "getWidth", id = 4)
  private float width;
  
  @Field(getter = "getZIndex", id = 8)
  private float zzcr;
  
  @Field(getter = "isVisible", id = 9)
  private boolean zzcs = true;
  
  @Field(getter = "isClickable", id = 13)
  private boolean zzct = false;
  
  @NonNull
  @Field(getter = "getWrappedImageDescriptorImplBinder", id = 2, type = "android.os.IBinder")
  private BitmapDescriptor zzcw;
  
  @Field(getter = "getLocation", id = 3)
  private LatLng zzcx;
  
  @Field(getter = "getBounds", id = 6)
  private LatLngBounds zzcy;
  
  @Field(getter = "getTransparency", id = 10)
  private float zzcz = 0.0F;
  
  @Field(getter = "getAnchorU", id = 11)
  private float zzda = 0.5F;
  
  @Field(getter = "getAnchorV", id = 12)
  private float zzdb = 0.5F;
  
  public GroundOverlayOptions() {}
  
  @Constructor
  GroundOverlayOptions(@Param(id = 2) IBinder paramIBinder, @Param(id = 3) LatLng paramLatLng, @Param(id = 4) float paramFloat1, @Param(id = 5) float paramFloat2, @Param(id = 6) LatLngBounds paramLatLngBounds, @Param(id = 7) float paramFloat3, @Param(id = 8) float paramFloat4, @Param(id = 9) boolean paramBoolean1, @Param(id = 10) float paramFloat5, @Param(id = 11) float paramFloat6, @Param(id = 12) float paramFloat7, @Param(id = 13) boolean paramBoolean2) {
    this.zzcw = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(paramIBinder));
    this.zzcx = paramLatLng;
    this.width = paramFloat1;
    this.height = paramFloat2;
    this.zzcy = paramLatLngBounds;
    this.bearing = paramFloat3;
    this.zzcr = paramFloat4;
    this.zzcs = paramBoolean1;
    this.zzcz = paramFloat5;
    this.zzda = paramFloat6;
    this.zzdb = paramFloat7;
    this.zzct = paramBoolean2;
  }
  
  private final GroundOverlayOptions zza(LatLng paramLatLng, float paramFloat1, float paramFloat2) {
    this.zzcx = paramLatLng;
    this.width = paramFloat1;
    this.height = paramFloat2;
    return this;
  }
  
  public final GroundOverlayOptions anchor(float paramFloat1, float paramFloat2) {
    this.zzda = paramFloat1;
    this.zzdb = paramFloat2;
    return this;
  }
  
  public final GroundOverlayOptions bearing(float paramFloat) {
    this.bearing = (paramFloat % 360.0F + 360.0F) % 360.0F;
    return this;
  }
  
  public final GroundOverlayOptions clickable(boolean paramBoolean) {
    this.zzct = paramBoolean;
    return this;
  }
  
  public final float getAnchorU() {
    return this.zzda;
  }
  
  public final float getAnchorV() {
    return this.zzdb;
  }
  
  public final float getBearing() {
    return this.bearing;
  }
  
  public final LatLngBounds getBounds() {
    return this.zzcy;
  }
  
  public final float getHeight() {
    return this.height;
  }
  
  public final BitmapDescriptor getImage() {
    return this.zzcw;
  }
  
  public final LatLng getLocation() {
    return this.zzcx;
  }
  
  public final float getTransparency() {
    return this.zzcz;
  }
  
  public final float getWidth() {
    return this.width;
  }
  
  public final float getZIndex() {
    return this.zzcr;
  }
  
  public final GroundOverlayOptions image(@NonNull BitmapDescriptor paramBitmapDescriptor) {
    Preconditions.checkNotNull(paramBitmapDescriptor, "imageDescriptor must not be null");
    this.zzcw = paramBitmapDescriptor;
    return this;
  }
  
  public final boolean isClickable() {
    return this.zzct;
  }
  
  public final boolean isVisible() {
    return this.zzcs;
  }
  
  public final GroundOverlayOptions position(LatLng paramLatLng, float paramFloat) {
    LatLngBounds latLngBounds = this.zzcy;
    boolean bool1 = false;
    if (latLngBounds == null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkState(bool2, "Position has already been set using positionFromBounds");
    if (paramLatLng != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "Location must be specified");
    boolean bool2 = bool1;
    if (paramFloat >= 0.0F)
      bool2 = true; 
    Preconditions.checkArgument(bool2, "Width must be non-negative");
    return zza(paramLatLng, paramFloat, -1.0F);
  }
  
  public final GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2) {
    LatLngBounds latLngBounds = this.zzcy;
    boolean bool1 = false;
    if (latLngBounds == null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkState(bool2, "Position has already been set using positionFromBounds");
    if (paramLatLng != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "Location must be specified");
    if (paramFloat1 >= 0.0F) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "Width must be non-negative");
    boolean bool2 = bool1;
    if (paramFloat2 >= 0.0F)
      bool2 = true; 
    Preconditions.checkArgument(bool2, "Height must be non-negative");
    return zza(paramLatLng, paramFloat1, paramFloat2);
  }
  
  public final GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds) {
    boolean bool;
    if (this.zzcx == null) {
      bool = true;
    } else {
      bool = false;
    } 
    String str = String.valueOf(this.zzcx);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 46);
    stringBuilder.append("Position has already been set using position: ");
    stringBuilder.append(str);
    Preconditions.checkState(bool, stringBuilder.toString());
    this.zzcy = paramLatLngBounds;
    return this;
  }
  
  public final GroundOverlayOptions transparency(float paramFloat) {
    boolean bool;
    if (paramFloat >= 0.0F && paramFloat <= 1.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Transparency must be in the range [0..1]");
    this.zzcz = paramFloat;
    return this;
  }
  
  public final GroundOverlayOptions visible(boolean paramBoolean) {
    this.zzcs = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeIBinder(paramParcel, 2, this.zzcw.zza().asBinder(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)getLocation(), paramInt, false);
    SafeParcelWriter.writeFloat(paramParcel, 4, getWidth());
    SafeParcelWriter.writeFloat(paramParcel, 5, getHeight());
    SafeParcelWriter.writeParcelable(paramParcel, 6, (Parcelable)getBounds(), paramInt, false);
    SafeParcelWriter.writeFloat(paramParcel, 7, getBearing());
    SafeParcelWriter.writeFloat(paramParcel, 8, getZIndex());
    SafeParcelWriter.writeBoolean(paramParcel, 9, isVisible());
    SafeParcelWriter.writeFloat(paramParcel, 10, getTransparency());
    SafeParcelWriter.writeFloat(paramParcel, 11, getAnchorU());
    SafeParcelWriter.writeFloat(paramParcel, 12, getAnchorV());
    SafeParcelWriter.writeBoolean(paramParcel, 13, isClickable());
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final GroundOverlayOptions zIndex(float paramFloat) {
    this.zzcr = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\GroundOverlayOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */