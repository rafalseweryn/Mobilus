package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "AuthAccountResultCreator")
public class AuthAccountResult extends AbstractSafeParcelable implements Result {
  public static final Parcelable.Creator<AuthAccountResult> CREATOR = new AuthAccountResultCreator();
  
  @Field(getter = "getConnectionResultCode", id = 2)
  private int zzadn;
  
  @Field(getter = "getRawAuthResolutionIntent", id = 3)
  private Intent zzado;
  
  @VersionField(id = 1)
  private final int zzal;
  
  public AuthAccountResult() {
    this(0, null);
  }
  
  @Constructor
  AuthAccountResult(@Param(id = 1) int paramInt1, @Param(id = 2) int paramInt2, @Param(id = 3) Intent paramIntent) {
    this.zzal = paramInt1;
    this.zzadn = paramInt2;
    this.zzado = paramIntent;
  }
  
  public AuthAccountResult(int paramInt, Intent paramIntent) {
    this(2, paramInt, paramIntent);
  }
  
  public int getConnectionResultCode() {
    return this.zzadn;
  }
  
  public Intent getRawAuthResolutionIntent() {
    return this.zzado;
  }
  
  public Status getStatus() {
    return (this.zzadn == 0) ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeInt(paramParcel, 2, getConnectionResultCode());
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)getRawAuthResolutionIntent(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\AuthAccountResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */