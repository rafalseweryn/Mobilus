package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Class(creator = "PolylineOptionsCreator")
@Reserved({1})
public final class PolylineOptions extends AbstractSafeParcelable {
  public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();
  
  @Field(getter = "getColor", id = 4)
  private int color = -16777216;
  
  @Field(getter = "getWidth", id = 3)
  private float width = 10.0F;
  
  @Field(getter = "getZIndex", id = 5)
  private float zzcr = 0.0F;
  
  @Field(getter = "isVisible", id = 6)
  private boolean zzcs = true;
  
  @Field(getter = "isClickable", id = 8)
  private boolean zzct = false;
  
  @Field(getter = "getPoints", id = 2)
  private final List<LatLng> zzdw = new ArrayList<>();
  
  @Field(getter = "isGeodesic", id = 7)
  private boolean zzdy = false;
  
  @NonNull
  @Field(getter = "getStartCap", id = 9)
  private Cap zzeb = new ButtCap();
  
  @NonNull
  @Field(getter = "getEndCap", id = 10)
  private Cap zzec = new ButtCap();
  
  @Field(getter = "getJointType", id = 11)
  private int zzed = 0;
  
  @Nullable
  @Field(getter = "getPattern", id = 12)
  private List<PatternItem> zzee = null;
  
  public PolylineOptions() {}
  
  @Constructor
  PolylineOptions(@Param(id = 2) List<LatLng> paramList, @Param(id = 3) float paramFloat1, @Param(id = 4) int paramInt1, @Param(id = 5) float paramFloat2, @Param(id = 6) boolean paramBoolean1, @Param(id = 7) boolean paramBoolean2, @Param(id = 8) boolean paramBoolean3, @Nullable @Param(id = 9) Cap paramCap1, @Nullable @Param(id = 10) Cap paramCap2, @Param(id = 11) int paramInt2, @Nullable @Param(id = 12) List<PatternItem> paramList1) {
    this.width = paramFloat1;
    this.color = paramInt1;
    this.zzcr = paramFloat2;
    this.zzcs = paramBoolean1;
    this.zzdy = paramBoolean2;
    this.zzct = paramBoolean3;
    if (paramCap1 != null)
      this.zzeb = paramCap1; 
    if (paramCap2 != null)
      this.zzec = paramCap2; 
    this.zzed = paramInt2;
    this.zzee = paramList1;
  }
  
  public final PolylineOptions add(LatLng paramLatLng) {
    this.zzdw.add(paramLatLng);
    return this;
  }
  
  public final PolylineOptions add(LatLng... paramVarArgs) {
    this.zzdw.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public final PolylineOptions addAll(Iterable<LatLng> paramIterable) {
    for (LatLng latLng : paramIterable)
      this.zzdw.add(latLng); 
    return this;
  }
  
  public final PolylineOptions clickable(boolean paramBoolean) {
    this.zzct = paramBoolean;
    return this;
  }
  
  public final PolylineOptions color(int paramInt) {
    this.color = paramInt;
    return this;
  }
  
  public final PolylineOptions endCap(@NonNull Cap paramCap) {
    this.zzec = (Cap)Preconditions.checkNotNull(paramCap, "endCap must not be null");
    return this;
  }
  
  public final PolylineOptions geodesic(boolean paramBoolean) {
    this.zzdy = paramBoolean;
    return this;
  }
  
  public final int getColor() {
    return this.color;
  }
  
  @NonNull
  public final Cap getEndCap() {
    return this.zzec;
  }
  
  public final int getJointType() {
    return this.zzed;
  }
  
  @Nullable
  public final List<PatternItem> getPattern() {
    return this.zzee;
  }
  
  public final List<LatLng> getPoints() {
    return this.zzdw;
  }
  
  @NonNull
  public final Cap getStartCap() {
    return this.zzeb;
  }
  
  public final float getWidth() {
    return this.width;
  }
  
  public final float getZIndex() {
    return this.zzcr;
  }
  
  public final boolean isClickable() {
    return this.zzct;
  }
  
  public final boolean isGeodesic() {
    return this.zzdy;
  }
  
  public final boolean isVisible() {
    return this.zzcs;
  }
  
  public final PolylineOptions jointType(int paramInt) {
    this.zzed = paramInt;
    return this;
  }
  
  public final PolylineOptions pattern(@Nullable List<PatternItem> paramList) {
    this.zzee = paramList;
    return this;
  }
  
  public final PolylineOptions startCap(@NonNull Cap paramCap) {
    this.zzeb = (Cap)Preconditions.checkNotNull(paramCap, "startCap must not be null");
    return this;
  }
  
  public final PolylineOptions visible(boolean paramBoolean) {
    this.zzcs = paramBoolean;
    return this;
  }
  
  public final PolylineOptions width(float paramFloat) {
    this.width = paramFloat;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeTypedList(paramParcel, 2, getPoints(), false);
    SafeParcelWriter.writeFloat(paramParcel, 3, getWidth());
    SafeParcelWriter.writeInt(paramParcel, 4, getColor());
    SafeParcelWriter.writeFloat(paramParcel, 5, getZIndex());
    SafeParcelWriter.writeBoolean(paramParcel, 6, isVisible());
    SafeParcelWriter.writeBoolean(paramParcel, 7, isGeodesic());
    SafeParcelWriter.writeBoolean(paramParcel, 8, isClickable());
    SafeParcelWriter.writeParcelable(paramParcel, 9, (Parcelable)getStartCap(), paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 10, (Parcelable)getEndCap(), paramInt, false);
    SafeParcelWriter.writeInt(paramParcel, 11, getJointType());
    SafeParcelWriter.writeTypedList(paramParcel, 12, getPattern(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final PolylineOptions zIndex(float paramFloat) {
    this.zzcr = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\PolylineOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */