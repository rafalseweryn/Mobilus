package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "SignInResponseCreator")
public class SignInResponse extends AbstractSafeParcelable {
  public static final Parcelable.Creator<SignInResponse> CREATOR = new SignInResponseCreator();
  
  @Field(getter = "getResolveAccountResponse", id = 3)
  private final ResolveAccountResponse zzadu;
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(getter = "getConnectionResult", id = 2)
  private final ConnectionResult zzeu;
  
  public SignInResponse(int paramInt) {
    this(new ConnectionResult(paramInt, null), null);
  }
  
  @Constructor
  SignInResponse(@Param(id = 1) int paramInt, @Param(id = 2) ConnectionResult paramConnectionResult, @Param(id = 3) ResolveAccountResponse paramResolveAccountResponse) {
    this.zzal = paramInt;
    this.zzeu = paramConnectionResult;
    this.zzadu = paramResolveAccountResponse;
  }
  
  public SignInResponse(ConnectionResult paramConnectionResult, ResolveAccountResponse paramResolveAccountResponse) {
    this(1, paramConnectionResult, paramResolveAccountResponse);
  }
  
  public ConnectionResult getConnectionResult() {
    return this.zzeu;
  }
  
  public ResolveAccountResponse getResolveAccountResponse() {
    return this.zzadu;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getConnectionResult(), paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)getResolveAccountResponse(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\SignInResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */