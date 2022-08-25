package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse;

@Class(creator = "ConverterWrapperCreator")
public class ConverterWrapper extends AbstractSafeParcelable {
  public static final Parcelable.Creator<ConverterWrapper> CREATOR = new ConverterWrapperCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(getter = "getStringToIntConverter", id = 2)
  private final StringToIntConverter zzwd;
  
  @Constructor
  ConverterWrapper(@Param(id = 1) int paramInt, @Param(id = 2) StringToIntConverter paramStringToIntConverter) {
    this.zzal = paramInt;
    this.zzwd = paramStringToIntConverter;
  }
  
  private ConverterWrapper(StringToIntConverter paramStringToIntConverter) {
    this.zzal = 1;
    this.zzwd = paramStringToIntConverter;
  }
  
  public static ConverterWrapper wrap(FastJsonResponse.FieldConverter<?, ?> paramFieldConverter) {
    if (paramFieldConverter instanceof StringToIntConverter)
      return new ConverterWrapper((StringToIntConverter)paramFieldConverter); 
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }
  
  public FastJsonResponse.FieldConverter<?, ?> unwrap() {
    if (this.zzwd != null)
      return this.zzwd; 
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.zzwd, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\converter\ConverterWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */