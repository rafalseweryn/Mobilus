package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

@Class(creator = "TileOverlayOptionsCreator")
@Reserved({1})
public final class TileOverlayOptions extends AbstractSafeParcelable {
  public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();
  
  @Field(getter = "getZIndex", id = 4)
  private float zzcr;
  
  @Field(getter = "isVisible", id = 3)
  private boolean zzcs;
  
  @Field(getter = "getTransparency", id = 6)
  private float zzcz;
  
  @Field(getter = "getTileProviderDelegate", id = 2, type = "android.os.IBinder")
  private zzaf zzeh;
  
  private TileProvider zzei;
  
  @Field(defaultValue = "true", getter = "getFadeIn", id = 5)
  private boolean zzej;
  
  public TileOverlayOptions() {
    this.zzcs = true;
    this.zzej = true;
    this.zzcz = 0.0F;
  }
  
  @Constructor
  TileOverlayOptions(@Param(id = 2) IBinder paramIBinder, @Param(id = 3) boolean paramBoolean1, @Param(id = 4) float paramFloat1, @Param(id = 5) boolean paramBoolean2, @Param(id = 6) float paramFloat2) {
    zzs zzs;
    this.zzcs = true;
    this.zzej = true;
    this.zzcz = 0.0F;
    this.zzeh = zzag.zzk(paramIBinder);
    if (this.zzeh == null) {
      paramIBinder = null;
    } else {
      zzs = new zzs(this);
    } 
    this.zzei = zzs;
    this.zzcs = paramBoolean1;
    this.zzcr = paramFloat1;
    this.zzej = paramBoolean2;
    this.zzcz = paramFloat2;
  }
  
  public final TileOverlayOptions fadeIn(boolean paramBoolean) {
    this.zzej = paramBoolean;
    return this;
  }
  
  public final boolean getFadeIn() {
    return this.zzej;
  }
  
  public final TileProvider getTileProvider() {
    return this.zzei;
  }
  
  public final float getTransparency() {
    return this.zzcz;
  }
  
  public final float getZIndex() {
    return this.zzcr;
  }
  
  public final boolean isVisible() {
    return this.zzcs;
  }
  
  public final TileOverlayOptions tileProvider(TileProvider paramTileProvider) {
    zzt zzt;
    this.zzei = paramTileProvider;
    if (this.zzei == null) {
      paramTileProvider = null;
    } else {
      zzt = new zzt(this, paramTileProvider);
    } 
    this.zzeh = (zzaf)zzt;
    return this;
  }
  
  public final TileOverlayOptions transparency(float paramFloat) {
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
  
  public final TileOverlayOptions visible(boolean paramBoolean) {
    this.zzcs = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeIBinder(paramParcel, 2, this.zzeh.asBinder(), false);
    SafeParcelWriter.writeBoolean(paramParcel, 3, isVisible());
    SafeParcelWriter.writeFloat(paramParcel, 4, getZIndex());
    SafeParcelWriter.writeBoolean(paramParcel, 5, getFadeIn());
    SafeParcelWriter.writeFloat(paramParcel, 6, getTransparency());
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  public final TileOverlayOptions zIndex(float paramFloat) {
    this.zzcr = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\TileOverlayOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */