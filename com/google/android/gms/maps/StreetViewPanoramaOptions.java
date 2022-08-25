package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

@Class(creator = "StreetViewPanoramaOptionsCreator")
@Reserved({1})
public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzai();
  
  @Field(getter = "getPanoramaId", id = 3)
  private String panoId;
  
  @Field(getter = "getPosition", id = 4)
  private LatLng position;
  
  @Field(getter = "getUseViewLifecycleInFragmentForParcel", id = 10, type = "byte")
  private Boolean zzak;
  
  @Field(getter = "getZoomGesturesEnabledForParcel", id = 7, type = "byte")
  private Boolean zzap = Boolean.valueOf(true);
  
  @Field(getter = "getStreetViewPanoramaCamera", id = 2)
  private StreetViewPanoramaCamera zzbw;
  
  @Field(getter = "getRadius", id = 5)
  private Integer zzbx;
  
  @Field(getter = "getUserNavigationEnabledForParcel", id = 6, type = "byte")
  private Boolean zzby = Boolean.valueOf(true);
  
  @Field(getter = "getPanningGesturesEnabledForParcel", id = 8, type = "byte")
  private Boolean zzbz = Boolean.valueOf(true);
  
  @Field(getter = "getStreetNamesEnabledForParcel", id = 9, type = "byte")
  private Boolean zzca = Boolean.valueOf(true);
  
  @Field(getter = "getSource", id = 11)
  private StreetViewSource zzcb = StreetViewSource.DEFAULT;
  
  public StreetViewPanoramaOptions() {}
  
  @Constructor
  StreetViewPanoramaOptions(@Param(id = 2) StreetViewPanoramaCamera paramStreetViewPanoramaCamera, @Param(id = 3) String paramString, @Param(id = 4) LatLng paramLatLng, @Param(id = 5) Integer paramInteger, @Param(id = 6) byte paramByte1, @Param(id = 7) byte paramByte2, @Param(id = 8) byte paramByte3, @Param(id = 9) byte paramByte4, @Param(id = 10) byte paramByte5, @Param(id = 11) StreetViewSource paramStreetViewSource) {
    this.zzbw = paramStreetViewPanoramaCamera;
    this.position = paramLatLng;
    this.zzbx = paramInteger;
    this.panoId = paramString;
    this.zzby = zza.zza(paramByte1);
    this.zzap = zza.zza(paramByte2);
    this.zzbz = zza.zza(paramByte3);
    this.zzca = zza.zza(paramByte4);
    this.zzak = zza.zza(paramByte5);
    this.zzcb = paramStreetViewSource;
  }
  
  public final Boolean getPanningGesturesEnabled() {
    return this.zzbz;
  }
  
  public final String getPanoramaId() {
    return this.panoId;
  }
  
  public final LatLng getPosition() {
    return this.position;
  }
  
  public final Integer getRadius() {
    return this.zzbx;
  }
  
  public final StreetViewSource getSource() {
    return this.zzcb;
  }
  
  public final Boolean getStreetNamesEnabled() {
    return this.zzca;
  }
  
  public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
    return this.zzbw;
  }
  
  public final Boolean getUseViewLifecycleInFragment() {
    return this.zzak;
  }
  
  public final Boolean getUserNavigationEnabled() {
    return this.zzby;
  }
  
  public final Boolean getZoomGesturesEnabled() {
    return this.zzap;
  }
  
  public final StreetViewPanoramaOptions panningGesturesEnabled(boolean paramBoolean) {
    this.zzbz = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera paramStreetViewPanoramaCamera) {
    this.zzbw = paramStreetViewPanoramaCamera;
    return this;
  }
  
  public final StreetViewPanoramaOptions panoramaId(String paramString) {
    this.panoId = paramString;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng) {
    this.position = paramLatLng;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng, StreetViewSource paramStreetViewSource) {
    this.position = paramLatLng;
    this.zzcb = paramStreetViewSource;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger) {
    this.position = paramLatLng;
    this.zzbx = paramInteger;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger, StreetViewSource paramStreetViewSource) {
    this.position = paramLatLng;
    this.zzbx = paramInteger;
    this.zzcb = paramStreetViewSource;
    return this;
  }
  
  public final StreetViewPanoramaOptions streetNamesEnabled(boolean paramBoolean) {
    this.zzca = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final String toString() {
    return Objects.toStringHelper(this).add("PanoramaId", this.panoId).add("Position", this.position).add("Radius", this.zzbx).add("Source", this.zzcb).add("StreetViewPanoramaCamera", this.zzbw).add("UserNavigationEnabled", this.zzby).add("ZoomGesturesEnabled", this.zzap).add("PanningGesturesEnabled", this.zzbz).add("StreetNamesEnabled", this.zzca).add("UseViewLifecycleInFragment", this.zzak).toString();
  }
  
  public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean paramBoolean) {
    this.zzak = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final StreetViewPanoramaOptions userNavigationEnabled(boolean paramBoolean) {
    this.zzby = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getStreetViewPanoramaCamera(), paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 3, getPanoramaId(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 4, (Parcelable)getPosition(), paramInt, false);
    SafeParcelWriter.writeIntegerObject(paramParcel, 5, getRadius(), false);
    SafeParcelWriter.writeByte(paramParcel, 6, zza.zza(this.zzby));
    SafeParcelWriter.writeByte(paramParcel, 7, zza.zza(this.zzap));
    SafeParcelWriter.writeByte(paramParcel, 8, zza.zza(this.zzbz));
    SafeParcelWriter.writeByte(paramParcel, 9, zza.zza(this.zzca));
    SafeParcelWriter.writeByte(paramParcel, 10, zza.zza(this.zzak));
    SafeParcelWriter.writeParcelable(paramParcel, 11, (Parcelable)getSource(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean paramBoolean) {
    this.zzap = Boolean.valueOf(paramBoolean);
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\StreetViewPanoramaOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */