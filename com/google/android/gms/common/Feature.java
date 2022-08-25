package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "FeatureCreator")
public class Feature extends AbstractSafeParcelable {
  public static final Parcelable.Creator<Feature> CREATOR = new FeatureCreator();
  
  @Field(getter = "getName", id = 1)
  private final String name;
  
  @Deprecated
  @Field(getter = "getOldVersion", id = 2)
  private final int zzaq;
  
  @Field(defaultValue = "-1", getter = "getVersion", id = 3)
  private final long zzar;
  
  @Constructor
  public Feature(@Param(id = 1) String paramString, @Param(id = 2) int paramInt, @Param(id = 3) long paramLong) {
    this.name = paramString;
    this.zzaq = paramInt;
    this.zzar = paramLong;
  }
  
  public Feature(String paramString, long paramLong) {
    this.name = paramString;
    this.zzar = paramLong;
    this.zzaq = -1;
  }
  
  public boolean equals(@Nullable Object paramObject) {
    if (paramObject instanceof Feature) {
      paramObject = paramObject;
      if (((getName() != null && getName().equals(paramObject.getName())) || (getName() == null && paramObject.getName() == null)) && getVersion() == paramObject.getVersion())
        return true; 
    } 
    return false;
  }
  
  public String getName() {
    return this.name;
  }
  
  public long getVersion() {
    return (this.zzar == -1L) ? this.zzaq : this.zzar;
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { getName(), Long.valueOf(getVersion()) });
  }
  
  public String toString() {
    return Objects.toStringHelper(this).add("name", getName()).add("version", Long.valueOf(getVersion())).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 1, getName(), false);
    SafeParcelWriter.writeInt(paramParcel, 2, this.zzaq);
    SafeParcelWriter.writeLong(paramParcel, 3, getVersion());
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\Feature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */