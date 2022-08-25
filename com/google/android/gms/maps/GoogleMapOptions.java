package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

@Class(creator = "GoogleMapOptionsCreator")
@Reserved({1})
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzaa();
  
  @Field(getter = "getMapType", id = 4)
  private int mapType = -1;
  
  @Field(defaultValue = "-1", getter = "getZOrderOnTopForParcel", id = 2, type = "byte")
  private Boolean zzaj;
  
  @Field(defaultValue = "-1", getter = "getUseViewLifecycleInFragmentForParcel", id = 3, type = "byte")
  private Boolean zzak;
  
  @Field(getter = "getCamera", id = 5)
  private CameraPosition zzal;
  
  @Field(defaultValue = "-1", getter = "getZoomControlsEnabledForParcel", id = 6, type = "byte")
  private Boolean zzam;
  
  @Field(defaultValue = "-1", getter = "getCompassEnabledForParcel", id = 7, type = "byte")
  private Boolean zzan;
  
  @Field(defaultValue = "-1", getter = "getScrollGesturesEnabledForParcel", id = 8, type = "byte")
  private Boolean zzao;
  
  @Field(defaultValue = "-1", getter = "getZoomGesturesEnabledForParcel", id = 9, type = "byte")
  private Boolean zzap;
  
  @Field(defaultValue = "-1", getter = "getTiltGesturesEnabledForParcel", id = 10, type = "byte")
  private Boolean zzaq;
  
  @Field(defaultValue = "-1", getter = "getRotateGesturesEnabledForParcel", id = 11, type = "byte")
  private Boolean zzar;
  
  @Field(defaultValue = "-1", getter = "getLiteModeForParcel", id = 12, type = "byte")
  private Boolean zzas;
  
  @Field(defaultValue = "-1", getter = "getMapToolbarEnabledForParcel", id = 14, type = "byte")
  private Boolean zzat;
  
  @Field(defaultValue = "-1", getter = "getAmbientEnabledForParcel", id = 15, type = "byte")
  private Boolean zzau;
  
  @Field(getter = "getMinZoomPreference", id = 16)
  private Float zzav = null;
  
  @Field(getter = "getMaxZoomPreference", id = 17)
  private Float zzaw = null;
  
  @Field(getter = "getLatLngBoundsForCameraTarget", id = 18)
  private LatLngBounds zzax = null;
  
  public GoogleMapOptions() {}
  
  @Constructor
  GoogleMapOptions(@Param(id = 2) byte paramByte1, @Param(id = 3) byte paramByte2, @Param(id = 4) int paramInt, @Param(id = 5) CameraPosition paramCameraPosition, @Param(id = 6) byte paramByte3, @Param(id = 7) byte paramByte4, @Param(id = 8) byte paramByte5, @Param(id = 9) byte paramByte6, @Param(id = 10) byte paramByte7, @Param(id = 11) byte paramByte8, @Param(id = 12) byte paramByte9, @Param(id = 14) byte paramByte10, @Param(id = 15) byte paramByte11, @Param(id = 16) Float paramFloat1, @Param(id = 17) Float paramFloat2, @Param(id = 18) LatLngBounds paramLatLngBounds) {
    this.zzaj = zza.zza(paramByte1);
    this.zzak = zza.zza(paramByte2);
    this.mapType = paramInt;
    this.zzal = paramCameraPosition;
    this.zzam = zza.zza(paramByte3);
    this.zzan = zza.zza(paramByte4);
    this.zzao = zza.zza(paramByte5);
    this.zzap = zza.zza(paramByte6);
    this.zzaq = zza.zza(paramByte7);
    this.zzar = zza.zza(paramByte8);
    this.zzas = zza.zza(paramByte9);
    this.zzat = zza.zza(paramByte10);
    this.zzau = zza.zza(paramByte11);
    this.zzav = paramFloat1;
    this.zzaw = paramFloat2;
    this.zzax = paramLatLngBounds;
  }
  
  public static GoogleMapOptions createFromAttributes(Context paramContext, AttributeSet paramAttributeSet) {
    if (paramContext == null || paramAttributeSet == null)
      return null; 
    TypedArray typedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    GoogleMapOptions googleMapOptions = new GoogleMapOptions();
    if (typedArray.hasValue(R.styleable.MapAttrs_mapType))
      googleMapOptions.mapType(typedArray.getInt(R.styleable.MapAttrs_mapType, -1)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_zOrderOnTop))
      googleMapOptions.zOrderOnTop(typedArray.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_useViewLifecycle))
      googleMapOptions.useViewLifecycleInFragment(typedArray.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_uiCompass))
      googleMapOptions.compassEnabled(typedArray.getBoolean(R.styleable.MapAttrs_uiCompass, true)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_uiRotateGestures))
      googleMapOptions.rotateGesturesEnabled(typedArray.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_uiScrollGestures))
      googleMapOptions.scrollGesturesEnabled(typedArray.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_uiTiltGestures))
      googleMapOptions.tiltGesturesEnabled(typedArray.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_uiZoomGestures))
      googleMapOptions.zoomGesturesEnabled(typedArray.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_uiZoomControls))
      googleMapOptions.zoomControlsEnabled(typedArray.getBoolean(R.styleable.MapAttrs_uiZoomControls, true)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_liteMode))
      googleMapOptions.liteMode(typedArray.getBoolean(R.styleable.MapAttrs_liteMode, false)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_uiMapToolbar))
      googleMapOptions.mapToolbarEnabled(typedArray.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_ambientEnabled))
      googleMapOptions.ambientEnabled(typedArray.getBoolean(R.styleable.MapAttrs_ambientEnabled, false)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference))
      googleMapOptions.minZoomPreference(typedArray.getFloat(R.styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference))
      googleMapOptions.maxZoomPreference(typedArray.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY)); 
    googleMapOptions.latLngBoundsForCameraTarget(zza(paramContext, paramAttributeSet));
    googleMapOptions.camera(zzb(paramContext, paramAttributeSet));
    typedArray.recycle();
    return googleMapOptions;
  }
  
  public static LatLngBounds zza(Context paramContext, AttributeSet paramAttributeSet) {
    LatLngBounds latLngBounds;
    TypedArray typedArray1 = null;
    TypedArray typedArray2 = typedArray1;
    if (paramContext != null) {
      Float float_1;
      Float float_2;
      if (paramAttributeSet == null)
        return null; 
      typedArray2 = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
      if (typedArray2.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude)) {
        Float float_ = Float.valueOf(typedArray2.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0F));
      } else {
        paramContext = null;
      } 
      if (typedArray2.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude)) {
        Float float_ = Float.valueOf(typedArray2.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0F));
      } else {
        paramAttributeSet = null;
      } 
      if (typedArray2.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude)) {
        float_1 = Float.valueOf(typedArray2.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0F));
      } else {
        float_1 = null;
      } 
      if (typedArray2.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude)) {
        float_2 = Float.valueOf(typedArray2.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0F));
      } else {
        float_2 = null;
      } 
      typedArray2.recycle();
      typedArray2 = typedArray1;
      if (paramContext != null) {
        typedArray2 = typedArray1;
        if (paramAttributeSet != null) {
          typedArray2 = typedArray1;
          if (float_1 != null) {
            if (float_2 == null)
              return null; 
            latLngBounds = new LatLngBounds(new LatLng(paramContext.floatValue(), paramAttributeSet.floatValue()), new LatLng(float_1.floatValue(), float_2.floatValue()));
          } 
        } 
      } 
    } 
    return latLngBounds;
  }
  
  public static CameraPosition zzb(Context paramContext, AttributeSet paramAttributeSet) {
    float f1;
    float f2;
    if (paramContext == null || paramAttributeSet == null)
      return null; 
    TypedArray typedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    if (typedArray.hasValue(R.styleable.MapAttrs_cameraTargetLat)) {
      f1 = typedArray.getFloat(R.styleable.MapAttrs_cameraTargetLat, 0.0F);
    } else {
      f1 = 0.0F;
    } 
    if (typedArray.hasValue(R.styleable.MapAttrs_cameraTargetLng)) {
      f2 = typedArray.getFloat(R.styleable.MapAttrs_cameraTargetLng, 0.0F);
    } else {
      f2 = 0.0F;
    } 
    LatLng latLng = new LatLng(f1, f2);
    CameraPosition.Builder builder = CameraPosition.builder();
    builder.target(latLng);
    if (typedArray.hasValue(R.styleable.MapAttrs_cameraZoom))
      builder.zoom(typedArray.getFloat(R.styleable.MapAttrs_cameraZoom, 0.0F)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_cameraBearing))
      builder.bearing(typedArray.getFloat(R.styleable.MapAttrs_cameraBearing, 0.0F)); 
    if (typedArray.hasValue(R.styleable.MapAttrs_cameraTilt))
      builder.tilt(typedArray.getFloat(R.styleable.MapAttrs_cameraTilt, 0.0F)); 
    typedArray.recycle();
    return builder.build();
  }
  
  public final GoogleMapOptions ambientEnabled(boolean paramBoolean) {
    this.zzau = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions camera(CameraPosition paramCameraPosition) {
    this.zzal = paramCameraPosition;
    return this;
  }
  
  public final GoogleMapOptions compassEnabled(boolean paramBoolean) {
    this.zzan = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final Boolean getAmbientEnabled() {
    return this.zzau;
  }
  
  public final CameraPosition getCamera() {
    return this.zzal;
  }
  
  public final Boolean getCompassEnabled() {
    return this.zzan;
  }
  
  public final LatLngBounds getLatLngBoundsForCameraTarget() {
    return this.zzax;
  }
  
  public final Boolean getLiteMode() {
    return this.zzas;
  }
  
  public final Boolean getMapToolbarEnabled() {
    return this.zzat;
  }
  
  public final int getMapType() {
    return this.mapType;
  }
  
  public final Float getMaxZoomPreference() {
    return this.zzaw;
  }
  
  public final Float getMinZoomPreference() {
    return this.zzav;
  }
  
  public final Boolean getRotateGesturesEnabled() {
    return this.zzar;
  }
  
  public final Boolean getScrollGesturesEnabled() {
    return this.zzao;
  }
  
  public final Boolean getTiltGesturesEnabled() {
    return this.zzaq;
  }
  
  public final Boolean getUseViewLifecycleInFragment() {
    return this.zzak;
  }
  
  public final Boolean getZOrderOnTop() {
    return this.zzaj;
  }
  
  public final Boolean getZoomControlsEnabled() {
    return this.zzam;
  }
  
  public final Boolean getZoomGesturesEnabled() {
    return this.zzap;
  }
  
  public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds) {
    this.zzax = paramLatLngBounds;
    return this;
  }
  
  public final GoogleMapOptions liteMode(boolean paramBoolean) {
    this.zzas = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions mapToolbarEnabled(boolean paramBoolean) {
    this.zzat = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions mapType(int paramInt) {
    this.mapType = paramInt;
    return this;
  }
  
  public final GoogleMapOptions maxZoomPreference(float paramFloat) {
    this.zzaw = Float.valueOf(paramFloat);
    return this;
  }
  
  public final GoogleMapOptions minZoomPreference(float paramFloat) {
    this.zzav = Float.valueOf(paramFloat);
    return this;
  }
  
  public final GoogleMapOptions rotateGesturesEnabled(boolean paramBoolean) {
    this.zzar = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions scrollGesturesEnabled(boolean paramBoolean) {
    this.zzao = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions tiltGesturesEnabled(boolean paramBoolean) {
    this.zzaq = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final String toString() {
    return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.mapType)).add("LiteMode", this.zzas).add("Camera", this.zzal).add("CompassEnabled", this.zzan).add("ZoomControlsEnabled", this.zzam).add("ScrollGesturesEnabled", this.zzao).add("ZoomGesturesEnabled", this.zzap).add("TiltGesturesEnabled", this.zzaq).add("RotateGesturesEnabled", this.zzar).add("MapToolbarEnabled", this.zzat).add("AmbientEnabled", this.zzau).add("MinZoomPreference", this.zzav).add("MaxZoomPreference", this.zzaw).add("LatLngBoundsForCameraTarget", this.zzax).add("ZOrderOnTop", this.zzaj).add("UseViewLifecycleInFragment", this.zzak).toString();
  }
  
  public final GoogleMapOptions useViewLifecycleInFragment(boolean paramBoolean) {
    this.zzak = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeByte(paramParcel, 2, zza.zza(this.zzaj));
    SafeParcelWriter.writeByte(paramParcel, 3, zza.zza(this.zzak));
    SafeParcelWriter.writeInt(paramParcel, 4, getMapType());
    SafeParcelWriter.writeParcelable(paramParcel, 5, (Parcelable)getCamera(), paramInt, false);
    SafeParcelWriter.writeByte(paramParcel, 6, zza.zza(this.zzam));
    SafeParcelWriter.writeByte(paramParcel, 7, zza.zza(this.zzan));
    SafeParcelWriter.writeByte(paramParcel, 8, zza.zza(this.zzao));
    SafeParcelWriter.writeByte(paramParcel, 9, zza.zza(this.zzap));
    SafeParcelWriter.writeByte(paramParcel, 10, zza.zza(this.zzaq));
    SafeParcelWriter.writeByte(paramParcel, 11, zza.zza(this.zzar));
    SafeParcelWriter.writeByte(paramParcel, 12, zza.zza(this.zzas));
    SafeParcelWriter.writeByte(paramParcel, 14, zza.zza(this.zzat));
    SafeParcelWriter.writeByte(paramParcel, 15, zza.zza(this.zzau));
    SafeParcelWriter.writeFloatObject(paramParcel, 16, getMinZoomPreference(), false);
    SafeParcelWriter.writeFloatObject(paramParcel, 17, getMaxZoomPreference(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 18, (Parcelable)getLatLngBoundsForCameraTarget(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final GoogleMapOptions zOrderOnTop(boolean paramBoolean) {
    this.zzaj = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions zoomControlsEnabled(boolean paramBoolean) {
    this.zzam = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions zoomGesturesEnabled(boolean paramBoolean) {
    this.zzap = Boolean.valueOf(paramBoolean);
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\GoogleMapOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */