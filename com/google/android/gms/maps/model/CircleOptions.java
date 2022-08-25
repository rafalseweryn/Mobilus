package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@Class(creator = "CircleOptionsCreator")
@Reserved({1})
public final class CircleOptions extends AbstractSafeParcelable {
  public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
  
  @Field(getter = "getFillColor", id = 6)
  private int fillColor = 0;
  
  @Field(getter = "getStrokeColor", id = 5)
  private int strokeColor = -16777216;
  
  @Field(getter = "getCenter", id = 2)
  private LatLng zzco = null;
  
  @Field(getter = "getRadius", id = 3)
  private double zzcp = 0.0D;
  
  @Field(getter = "getStrokeWidth", id = 4)
  private float zzcq = 10.0F;
  
  @Field(getter = "getZIndex", id = 7)
  private float zzcr = 0.0F;
  
  @Field(getter = "isVisible", id = 8)
  private boolean zzcs = true;
  
  @Field(getter = "isClickable", id = 9)
  private boolean zzct = false;
  
  @Nullable
  @Field(getter = "getStrokePattern", id = 10)
  private List<PatternItem> zzcu = null;
  
  public CircleOptions() {}
  
  @Constructor
  CircleOptions(@Param(id = 2) LatLng paramLatLng, @Param(id = 3) double paramDouble, @Param(id = 4) float paramFloat1, @Param(id = 5) int paramInt1, @Param(id = 6) int paramInt2, @Param(id = 7) float paramFloat2, @Param(id = 8) boolean paramBoolean1, @Param(id = 9) boolean paramBoolean2, @Nullable @Param(id = 10) List<PatternItem> paramList) {
    this.zzco = paramLatLng;
    this.zzcp = paramDouble;
    this.zzcq = paramFloat1;
    this.strokeColor = paramInt1;
    this.fillColor = paramInt2;
    this.zzcr = paramFloat2;
    this.zzcs = paramBoolean1;
    this.zzct = paramBoolean2;
    this.zzcu = paramList;
  }
  
  public final CircleOptions center(LatLng paramLatLng) {
    this.zzco = paramLatLng;
    return this;
  }
  
  public final CircleOptions clickable(boolean paramBoolean) {
    this.zzct = paramBoolean;
    return this;
  }
  
  public final CircleOptions fillColor(int paramInt) {
    this.fillColor = paramInt;
    return this;
  }
  
  public final LatLng getCenter() {
    return this.zzco;
  }
  
  public final int getFillColor() {
    return this.fillColor;
  }
  
  public final double getRadius() {
    return this.zzcp;
  }
  
  public final int getStrokeColor() {
    return this.strokeColor;
  }
  
  @Nullable
  public final List<PatternItem> getStrokePattern() {
    return this.zzcu;
  }
  
  public final float getStrokeWidth() {
    return this.zzcq;
  }
  
  public final float getZIndex() {
    return this.zzcr;
  }
  
  public final boolean isClickable() {
    return this.zzct;
  }
  
  public final boolean isVisible() {
    return this.zzcs;
  }
  
  public final CircleOptions radius(double paramDouble) {
    this.zzcp = paramDouble;
    return this;
  }
  
  public final CircleOptions strokeColor(int paramInt) {
    this.strokeColor = paramInt;
    return this;
  }
  
  public final CircleOptions strokePattern(@Nullable List<PatternItem> paramList) {
    this.zzcu = paramList;
    return this;
  }
  
  public final CircleOptions strokeWidth(float paramFloat) {
    this.zzcq = paramFloat;
    return this;
  }
  
  public final CircleOptions visible(boolean paramBoolean) {
    this.zzcs = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getCenter(), paramInt, false);
    SafeParcelWriter.writeDouble(paramParcel, 3, getRadius());
    SafeParcelWriter.writeFloat(paramParcel, 4, getStrokeWidth());
    SafeParcelWriter.writeInt(paramParcel, 5, getStrokeColor());
    SafeParcelWriter.writeInt(paramParcel, 6, getFillColor());
    SafeParcelWriter.writeFloat(paramParcel, 7, getZIndex());
    SafeParcelWriter.writeBoolean(paramParcel, 8, isVisible());
    SafeParcelWriter.writeBoolean(paramParcel, 9, isClickable());
    SafeParcelWriter.writeTypedList(paramParcel, 10, getStrokePattern(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final CircleOptions zIndex(float paramFloat) {
    this.zzcr = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\CircleOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */