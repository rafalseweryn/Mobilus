package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "SignInRequestCreator")
public class SignInRequest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<SignInRequest> CREATOR = new SignInRequestCreator();
  
  @Field(getter = "getResolveAccountRequest", id = 2)
  private final ResolveAccountRequest zzadt;
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Constructor
  SignInRequest(@Param(id = 1) int paramInt, @Param(id = 2) ResolveAccountRequest paramResolveAccountRequest) {
    this.zzal = paramInt;
    this.zzadt = paramResolveAccountRequest;
  }
  
  public SignInRequest(ResolveAccountRequest paramResolveAccountRequest) {
    this(1, paramResolveAccountRequest);
  }
  
  public ResolveAccountRequest getResolveAccountRequest() {
    return this.zzadt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getResolveAccountRequest(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\SignInRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */