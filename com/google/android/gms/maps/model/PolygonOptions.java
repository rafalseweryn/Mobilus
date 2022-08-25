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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Class(creator = "PolygonOptionsCreator")
@Reserved({1})
public final class PolygonOptions extends AbstractSafeParcelable {
  public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzk();
  
  @Field(getter = "getFillColor", id = 6)
  private int fillColor = 0;
  
  @Field(getter = "getStrokeColor", id = 5)
  private int strokeColor = -16777216;
  
  @Field(getter = "getStrokeWidth", id = 4)
  private float zzcq = 10.0F;
  
  @Field(getter = "getZIndex", id = 7)
  private float zzcr = 0.0F;
  
  @Field(getter = "isVisible", id = 8)
  private boolean zzcs = true;
  
  @Field(getter = "isClickable", id = 10)
  private boolean zzct = false;
  
  @Nullable
  @Field(getter = "getStrokePattern", id = 12)
  private List<PatternItem> zzcu = null;
  
  @Field(getter = "getPoints", id = 2)
  private final List<LatLng> zzdw = new ArrayList<>();
  
  @Field(getter = "getHolesForParcel", id = 3, type = "java.util.List")
  private final List<List<LatLng>> zzdx = new ArrayList<>();
  
  @Field(getter = "isGeodesic", id = 9)
  private boolean zzdy = false;
  
  @Field(getter = "getStrokeJointType", id = 11)
  private int zzdz = 0;
  
  public PolygonOptions() {}
  
  @Constructor
  PolygonOptions(@Param(id = 2) List<LatLng> paramList, @Param(id = 3) List<List<LatLng>> paramList1, @Param(id = 4) float paramFloat1, @Param(id = 5) int paramInt1, @Param(id = 6) int paramInt2, @Param(id = 7) float paramFloat2, @Param(id = 8) boolean paramBoolean1, @Param(id = 9) boolean paramBoolean2, @Param(id = 10) boolean paramBoolean3, @Param(id = 11) int paramInt3, @Nullable @Param(id = 12) List<PatternItem> paramList2) {
    this.zzcq = paramFloat1;
    this.strokeColor = paramInt1;
    this.fillColor = paramInt2;
    this.zzcr = paramFloat2;
    this.zzcs = paramBoolean1;
    this.zzdy = paramBoolean2;
    this.zzct = paramBoolean3;
    this.zzdz = paramInt3;
    this.zzcu = paramList2;
  }
  
  public final PolygonOptions add(LatLng paramLatLng) {
    this.zzdw.add(paramLatLng);
    return this;
  }
  
  public final PolygonOptions add(LatLng... paramVarArgs) {
    this.zzdw.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public final PolygonOptions addAll(Iterable<LatLng> paramIterable) {
    for (LatLng latLng : paramIterable)
      this.zzdw.add(latLng); 
    return this;
  }
  
  public final PolygonOptions addHole(Iterable<LatLng> paramIterable) {
    ArrayList<LatLng> arrayList = new ArrayList();
    Iterator<LatLng> iterator = paramIterable.iterator();
    while (iterator.hasNext())
      arrayList.add(iterator.next()); 
    this.zzdx.add(arrayList);
    return this;
  }
  
  public final PolygonOptions clickable(boolean paramBoolean) {
    this.zzct = paramBoolean;
    return this;
  }
  
  public final PolygonOptions fillColor(int paramInt) {
    this.fillColor = paramInt;
    return this;
  }
  
  public final PolygonOptions geodesic(boolean paramBoolean) {
    this.zzdy = paramBoolean;
    return this;
  }
  
  public final int getFillColor() {
    return this.fillColor;
  }
  
  public final List<List<LatLng>> getHoles() {
    return this.zzdx;
  }
  
  public final List<LatLng> getPoints() {
    return this.zzdw;
  }
  
  public final int getStrokeColor() {
    return this.strokeColor;
  }
  
  public final int getStrokeJointType() {
    return this.zzdz;
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
  
  public final boolean isGeodesic() {
    return this.zzdy;
  }
  
  public final boolean isVisible() {
    return this.zzcs;
  }
  
  public final PolygonOptions strokeColor(int paramInt) {
    this.strokeColor = paramInt;
    return this;
  }
  
  public final PolygonOptions strokeJointType(int paramInt) {
    this.zzdz = paramInt;
    return this;
  }
  
  public final PolygonOptions strokePattern(@Nullable List<PatternItem> paramList) {
    this.zzcu = paramList;
    return this;
  }
  
  public final PolygonOptions strokeWidth(float paramFloat) {
    this.zzcq = paramFloat;
    return this;
  }
  
  public final PolygonOptions visible(boolean paramBoolean) {
    this.zzcs = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeTypedList(paramParcel, 2, getPoints(), false);
    SafeParcelWriter.writeList(paramParcel, 3, this.zzdx, false);
    SafeParcelWriter.writeFloat(paramParcel, 4, getStrokeWidth());
    SafeParcelWriter.writeInt(paramParcel, 5, getStrokeColor());
    SafeParcelWriter.writeInt(paramParcel, 6, getFillColor());
    SafeParcelWriter.writeFloat(paramParcel, 7, getZIndex());
    SafeParcelWriter.writeBoolean(paramParcel, 8, isVisible());
    SafeParcelWriter.writeBoolean(paramParcel, 9, isGeodesic());
    SafeParcelWriter.writeBoolean(paramParcel, 10, isClickable());
    SafeParcelWriter.writeInt(paramParcel, 11, getStrokeJointType());
    SafeParcelWriter.writeTypedList(paramParcel, 12, getStrokePattern(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  public final PolygonOptions zIndex(float paramFloat) {
    this.zzcr = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\PolygonOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */