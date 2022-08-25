package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.dynamic.IObjectWrapper;

@Class(creator = "MarkerOptionsCreator")
@Reserved({1})
public final class MarkerOptions extends AbstractSafeParcelable {
  public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzh();
  
  @Field(defaultValue = "1.0f", getter = "getAlpha", id = 14)
  private float alpha = 1.0F;
  
  @Field(getter = "getPosition", id = 2)
  private LatLng position;
  
  @Field(getter = "getZIndex", id = 15)
  private float zzcr;
  
  @Field(getter = "isVisible", id = 9)
  private boolean zzcs = true;
  
  @Field(getter = "getAnchorU", id = 6)
  private float zzda = 0.5F;
  
  @Field(getter = "getAnchorV", id = 7)
  private float zzdb = 1.0F;
  
  @Field(getter = "getTitle", id = 3)
  private String zzdm;
  
  @Field(getter = "getSnippet", id = 4)
  private String zzdn;
  
  @Field(getter = "getWrappedIconDescriptorImplBinder", id = 5, type = "android.os.IBinder")
  private BitmapDescriptor zzdo;
  
  @Field(getter = "isDraggable", id = 8)
  private boolean zzdp;
  
  @Field(getter = "isFlat", id = 10)
  private boolean zzdq = false;
  
  @Field(getter = "getRotation", id = 11)
  private float zzdr = 0.0F;
  
  @Field(defaultValue = "0.5f", getter = "getInfoWindowAnchorU", id = 12)
  private float zzds = 0.5F;
  
  @Field(getter = "getInfoWindowAnchorV", id = 13)
  private float zzdt = 0.0F;
  
  public MarkerOptions() {}
  
  @Constructor
  MarkerOptions(@Param(id = 2) LatLng paramLatLng, @Param(id = 3) String paramString1, @Param(id = 4) String paramString2, @Param(id = 5) IBinder paramIBinder, @Param(id = 6) float paramFloat1, @Param(id = 7) float paramFloat2, @Param(id = 8) boolean paramBoolean1, @Param(id = 9) boolean paramBoolean2, @Param(id = 10) boolean paramBoolean3, @Param(id = 11) float paramFloat3, @Param(id = 12) float paramFloat4, @Param(id = 13) float paramFloat5, @Param(id = 14) float paramFloat6, @Param(id = 15) float paramFloat7) {
    this.position = paramLatLng;
    this.zzdm = paramString1;
    this.zzdn = paramString2;
    if (paramIBinder == null) {
      this.zzdo = null;
    } else {
      this.zzdo = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(paramIBinder));
    } 
    this.zzda = paramFloat1;
    this.zzdb = paramFloat2;
    this.zzdp = paramBoolean1;
    this.zzcs = paramBoolean2;
    this.zzdq = paramBoolean3;
    this.zzdr = paramFloat3;
    this.zzds = paramFloat4;
    this.zzdt = paramFloat5;
    this.alpha = paramFloat6;
    this.zzcr = paramFloat7;
  }
  
  public final MarkerOptions alpha(float paramFloat) {
    this.alpha = paramFloat;
    return this;
  }
  
  public final MarkerOptions anchor(float paramFloat1, float paramFloat2) {
    this.zzda = paramFloat1;
    this.zzdb = paramFloat2;
    return this;
  }
  
  public final MarkerOptions draggable(boolean paramBoolean) {
    this.zzdp = paramBoolean;
    return this;
  }
  
  public final MarkerOptions flat(boolean paramBoolean) {
    this.zzdq = paramBoolean;
    return this;
  }
  
  public final float getAlpha() {
    return this.alpha;
  }
  
  public final float getAnchorU() {
    return this.zzda;
  }
  
  public final float getAnchorV() {
    return this.zzdb;
  }
  
  public final BitmapDescriptor getIcon() {
    return this.zzdo;
  }
  
  public final float getInfoWindowAnchorU() {
    return this.zzds;
  }
  
  public final float getInfoWindowAnchorV() {
    return this.zzdt;
  }
  
  public final LatLng getPosition() {
    return this.position;
  }
  
  public final float getRotation() {
    return this.zzdr;
  }
  
  public final String getSnippet() {
    return this.zzdn;
  }
  
  public final String getTitle() {
    return this.zzdm;
  }
  
  public final float getZIndex() {
    return this.zzcr;
  }
  
  public final MarkerOptions icon(@Nullable BitmapDescriptor paramBitmapDescriptor) {
    this.zzdo = paramBitmapDescriptor;
    return this;
  }
  
  public final MarkerOptions infoWindowAnchor(float paramFloat1, float paramFloat2) {
    this.zzds = paramFloat1;
    this.zzdt = paramFloat2;
    return this;
  }
  
  public final boolean isDraggable() {
    return this.zzdp;
  }
  
  public final boolean isFlat() {
    return this.zzdq;
  }
  
  public final boolean isVisible() {
    return this.zzcs;
  }
  
  public final MarkerOptions position(@NonNull LatLng paramLatLng) {
    if (paramLatLng == null)
      throw new IllegalArgumentException("latlng cannot be null - a position is required."); 
    this.position = paramLatLng;
    return this;
  }
  
  public final MarkerOptions rotation(float paramFloat) {
    this.zzdr = paramFloat;
    return this;
  }
  
  public final MarkerOptions snippet(@Nullable String paramString) {
    this.zzdn = paramString;
    return this;
  }
  
  public final MarkerOptions title(@Nullable String paramString) {
    this.zzdm = paramString;
    return this;
  }
  
  public final MarkerOptions visible(boolean paramBoolean) {
    this.zzcs = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder;
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getPosition(), paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 3, getTitle(), false);
    SafeParcelWriter.writeString(paramParcel, 4, getSnippet(), false);
    if (this.zzdo == null) {
      iBinder = null;
    } else {
      iBinder = this.zzdo.zza().asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 5, iBinder, false);
    SafeParcelWriter.writeFloat(paramParcel, 6, getAnchorU());
    SafeParcelWriter.writeFloat(paramParcel, 7, getAnchorV());
    SafeParcelWriter.writeBoolean(paramParcel, 8, isDraggable());
    SafeParcelWriter.writeBoolean(paramParcel, 9, isVisible());
    SafeParcelWriter.writeBoolean(paramParcel, 10, isFlat());
    SafeParcelWriter.writeFloat(paramParcel, 11, getRotation());
    SafeParcelWriter.writeFloat(paramParcel, 12, getInfoWindowAnchorU());
    SafeParcelWriter.writeFloat(paramParcel, 13, getInfoWindowAnchorV());
    SafeParcelWriter.writeFloat(paramParcel, 14, getAlpha());
    SafeParcelWriter.writeFloat(paramParcel, 15, getZIndex());
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final MarkerOptions zIndex(float paramFloat) {
    this.zzcr = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\MarkerOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */