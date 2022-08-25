package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new FavaDiagnosticsEntityCreator();
  
  public static final String EXTRA_NAMESPACE = "namespace";
  
  public static final String EXTRA_TYPE_NUM = "typeNum";
  
  @Field(id = 2)
  public final String namespace;
  
  @Field(id = 3)
  public final int typeNum;
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Constructor
  public FavaDiagnosticsEntity(@Param(id = 1) int paramInt1, @Param(id = 2) String paramString, @Param(id = 3) int paramInt2) {
    this.zzal = paramInt1;
    this.namespace = paramString;
    this.typeNum = paramInt2;
  }
  
  public FavaDiagnosticsEntity(String paramString, int paramInt) {
    this.zzal = 1;
    this.namespace = paramString;
    this.typeNum = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeString(paramParcel, 2, this.namespace, false);
    SafeParcelWriter.writeInt(paramParcel, 3, this.typeNum);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\FavaDiagnosticsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */