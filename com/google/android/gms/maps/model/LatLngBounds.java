package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.annotation.KeepForSdk;
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
import com.google.android.gms.maps.GoogleMapOptions;

@Class(creator = "LatLngBoundsCreator")
@Reserved({1})
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
  @KeepForSdk
  public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();
  
  @Field(id = 3)
  public final LatLng northeast;
  
  @Field(id = 2)
  public final LatLng southwest;
  
  @Constructor
  public LatLngBounds(@Param(id = 2) LatLng paramLatLng1, @Param(id = 3) LatLng paramLatLng2) {
    boolean bool;
    Preconditions.checkNotNull(paramLatLng1, "null southwest");
    Preconditions.checkNotNull(paramLatLng2, "null northeast");
    if (paramLatLng2.latitude >= paramLatLng1.latitude) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(paramLatLng1.latitude), Double.valueOf(paramLatLng2.latitude) });
    this.southwest = paramLatLng1;
    this.northeast = paramLatLng2;
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public static LatLngBounds createFromAttributes(Context paramContext, AttributeSet paramAttributeSet) {
    return GoogleMapOptions.zza(paramContext, paramAttributeSet);
  }
  
  private static double zza(double paramDouble1, double paramDouble2) {
    return (paramDouble1 - paramDouble2 + 360.0D) % 360.0D;
  }
  
  private final boolean zza(double paramDouble) {
    return (this.southwest.longitude <= this.northeast.longitude) ? ((this.southwest.longitude <= paramDouble && paramDouble <= this.northeast.longitude)) : ((this.southwest.longitude > paramDouble) ? ((paramDouble <= this.northeast.longitude)) : true);
  }
  
  private static double zzb(double paramDouble1, double paramDouble2) {
    return (paramDouble2 - paramDouble1 + 360.0D) % 360.0D;
  }
  
  public final boolean contains(LatLng paramLatLng) {
    boolean bool;
    double d = paramLatLng.latitude;
    if (this.southwest.latitude <= d && d <= this.northeast.latitude) {
      bool = true;
    } else {
      bool = false;
    } 
    return (bool && zza(paramLatLng.longitude));
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LatLngBounds))
      return false; 
    paramObject = paramObject;
    return (this.southwest.equals(((LatLngBounds)paramObject).southwest) && this.northeast.equals(((LatLngBounds)paramObject).northeast));
  }
  
  public final LatLng getCenter() {
    double d1 = (this.southwest.latitude + this.northeast.latitude) / 2.0D;
    double d2 = this.northeast.longitude;
    double d3 = this.southwest.longitude;
    if (d3 > d2)
      d2 += 360.0D; 
    d2 = (d2 + d3) / 2.0D;
    return new LatLng(d1, d2);
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { this.southwest, this.northeast });
  }
  
  public final LatLngBounds including(LatLng paramLatLng) {
    double d1 = Math.min(this.southwest.latitude, paramLatLng.latitude);
    double d2 = Math.max(this.northeast.latitude, paramLatLng.latitude);
    double d3 = this.northeast.longitude;
    double d4 = this.southwest.longitude;
    double d5 = paramLatLng.longitude;
    double d6 = d3;
    double d7 = d4;
    if (!zza(d5))
      if (zza(d4, d5) < zzb(d3, d5)) {
        d7 = d5;
        d6 = d3;
      } else {
        d6 = d5;
        d7 = d4;
      }  
    return new LatLngBounds(new LatLng(d1, d7), new LatLng(d2, d6));
  }
  
  public final String toString() {
    return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.southwest, paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)this.northeast, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public static final class Builder {
    private double zzdg = Double.POSITIVE_INFINITY;
    
    private double zzdh = Double.NEGATIVE_INFINITY;
    
    private double zzdi = Double.NaN;
    
    private double zzdj = Double.NaN;
    
    public final LatLngBounds build() {
      Preconditions.checkState(Double.isNaN(this.zzdi) ^ true, "no included points");
      return new LatLngBounds(new LatLng(this.zzdg, this.zzdi), new LatLng(this.zzdh, this.zzdj));
    }
    
    public final Builder include(LatLng param1LatLng) {
      // Byte code:
      //   0: aload_0
      //   1: aload_0
      //   2: getfield zzdg : D
      //   5: aload_1
      //   6: getfield latitude : D
      //   9: invokestatic min : (DD)D
      //   12: putfield zzdg : D
      //   15: aload_0
      //   16: aload_0
      //   17: getfield zzdh : D
      //   20: aload_1
      //   21: getfield latitude : D
      //   24: invokestatic max : (DD)D
      //   27: putfield zzdh : D
      //   30: aload_1
      //   31: getfield longitude : D
      //   34: dstore_2
      //   35: aload_0
      //   36: getfield zzdi : D
      //   39: invokestatic isNaN : (D)Z
      //   42: ifeq -> 53
      //   45: aload_0
      //   46: dload_2
      //   47: putfield zzdi : D
      //   50: goto -> 162
      //   53: aload_0
      //   54: getfield zzdi : D
      //   57: dstore #4
      //   59: aload_0
      //   60: getfield zzdj : D
      //   63: dstore #6
      //   65: iconst_0
      //   66: istore #8
      //   68: dload #4
      //   70: dload #6
      //   72: dcmpg
      //   73: ifgt -> 105
      //   76: iload #8
      //   78: istore #9
      //   80: aload_0
      //   81: getfield zzdi : D
      //   84: dload_2
      //   85: dcmpg
      //   86: ifgt -> 130
      //   89: iload #8
      //   91: istore #9
      //   93: dload_2
      //   94: aload_0
      //   95: getfield zzdj : D
      //   98: dcmpg
      //   99: ifgt -> 130
      //   102: goto -> 127
      //   105: aload_0
      //   106: getfield zzdi : D
      //   109: dload_2
      //   110: dcmpg
      //   111: ifle -> 127
      //   114: iload #8
      //   116: istore #9
      //   118: dload_2
      //   119: aload_0
      //   120: getfield zzdj : D
      //   123: dcmpg
      //   124: ifgt -> 130
      //   127: iconst_1
      //   128: istore #9
      //   130: iload #9
      //   132: ifne -> 167
      //   135: aload_0
      //   136: getfield zzdi : D
      //   139: dload_2
      //   140: invokestatic zzc : (DD)D
      //   143: aload_0
      //   144: getfield zzdj : D
      //   147: dload_2
      //   148: invokestatic zzd : (DD)D
      //   151: dcmpg
      //   152: ifge -> 162
      //   155: aload_0
      //   156: dload_2
      //   157: putfield zzdi : D
      //   160: aload_0
      //   161: areturn
      //   162: aload_0
      //   163: dload_2
      //   164: putfield zzdj : D
      //   167: aload_0
      //   168: areturn
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\LatLngBounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */