package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ResolveAccountRequestCreator")
public class ResolveAccountRequest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<ResolveAccountRequest> CREATOR = new ResolveAccountRequestCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(getter = "getAccount", id = 2)
  private final Account zzs;
  
  @Field(getter = "getSessionId", id = 3)
  private final int zzut;
  
  @Field(getter = "getSignInAccountHint", id = 4)
  private final GoogleSignInAccount zzuu;
  
  @Constructor
  ResolveAccountRequest(@Param(id = 1) int paramInt1, @Param(id = 2) Account paramAccount, @Param(id = 3) int paramInt2, @Param(id = 4) GoogleSignInAccount paramGoogleSignInAccount) {
    this.zzal = paramInt1;
    this.zzs = paramAccount;
    this.zzut = paramInt2;
    this.zzuu = paramGoogleSignInAccount;
  }
  
  public ResolveAccountRequest(Account paramAccount, int paramInt, GoogleSignInAccount paramGoogleSignInAccount) {
    this(2, paramAccount, paramInt, paramGoogleSignInAccount);
  }
  
  public Account getAccount() {
    return this.zzs;
  }
  
  public int getSessionId() {
    return this.zzut;
  }
  
  @Nullable
  public GoogleSignInAccount getSignInAccountHint() {
    return this.zzuu;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getAccount(), paramInt, false);
    SafeParcelWriter.writeInt(paramParcel, 3, getSessionId());
    SafeParcelWriter.writeParcelable(paramParcel, 4, (Parcelable)getSignInAccountHint(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ResolveAccountRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */