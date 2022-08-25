package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "CapCreator")
@Reserved({1})
public class Cap extends AbstractSafeParcelable {
  public static final Parcelable.Creator<Cap> CREATOR = new zzb();
  
  private static final String TAG = "Cap";
  
  @Nullable
  @Field(getter = "getWrappedBitmapDescriptorImplBinder", id = 3, type = "android.os.IBinder")
  private final BitmapDescriptor bitmapDescriptor;
  
  @Field(getter = "getType", id = 2)
  private final int type;
  
  @Nullable
  @Field(getter = "getBitmapRefWidth", id = 4)
  private final Float zzcm;
  
  protected Cap(int paramInt) {
    this(paramInt, (BitmapDescriptor)null, (Float)null);
  }
  
  @Constructor
  Cap(@Param(id = 2) int paramInt, @Nullable @Param(id = 3) IBinder paramIBinder, @Nullable @Param(id = 4) Float paramFloat) {
    this(paramInt, bitmapDescriptor, paramFloat);
  }
  
  private Cap(int paramInt, @Nullable BitmapDescriptor paramBitmapDescriptor, @Nullable Float paramFloat) {
    boolean bool1;
    boolean bool2;
    if (paramFloat != null && paramFloat.floatValue() > 0.0F) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramInt != 3 || (paramBitmapDescriptor != null && bool1)) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[] { Integer.valueOf(paramInt), paramBitmapDescriptor, paramFloat }));
    this.type = paramInt;
    this.bitmapDescriptor = paramBitmapDescriptor;
    this.zzcm = paramFloat;
  }
  
  protected Cap(@NonNull BitmapDescriptor paramBitmapDescriptor, float paramFloat) {
    this(3, paramBitmapDescriptor, Float.valueOf(paramFloat));
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof Cap))
      return false; 
    paramObject = paramObject;
    return (this.type == ((Cap)paramObject).type && Objects.equal(this.bitmapDescriptor, ((Cap)paramObject).bitmapDescriptor) && Objects.equal(this.zzcm, ((Cap)paramObject).zzcm));
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcm });
  }
  
  public String toString() {
    int i = this.type;
    StringBuilder stringBuilder = new StringBuilder(23);
    stringBuilder.append("[Cap: type=");
    stringBuilder.append(i);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder;
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 2, this.type);
    if (this.bitmapDescriptor == null) {
      iBinder = null;
    } else {
      iBinder = this.bitmapDescriptor.zza().asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 3, iBinder, false);
    SafeParcelWriter.writeFloatObject(paramParcel, 4, this.zzcm, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  final Cap zzg() {
    String str;
    int i;
    StringBuilder stringBuilder;
    switch (this.type) {
      default:
        str = TAG;
        i = this.type;
        stringBuilder = new StringBuilder(29);
        stringBuilder.append("Unknown Cap type: ");
        stringBuilder.append(i);
        Log.w(str, stringBuilder.toString());
        return this;
      case 3:
        return new CustomCap(this.bitmapDescriptor, this.zzcm.floatValue());
      case 2:
        return new RoundCap();
      case 1:
        return new SquareCap();
      case 0:
        break;
    } 
    return new ButtCap();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\Cap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */