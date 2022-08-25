package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Deprecated
@Class(creator = "ValidateAccountRequestCreator")
public class ValidateAccountRequest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<ValidateAccountRequest> CREATOR = new ValidateAccountRequestCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Constructor
  ValidateAccountRequest(@Param(id = 1) int paramInt) {
    this.zzal = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ValidateAccountRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */