package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@Class(creator = "AuthAccountRequestCreator")
public class AuthAccountRequest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<AuthAccountRequest> CREATOR = new AuthAccountRequestCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Deprecated
  @Field(id = 2)
  private final IBinder zzqv;
  
  @Field(id = 3)
  private final Scope[] zzqw;
  
  @Field(id = 4)
  private Integer zzqx;
  
  @Field(id = 5)
  private Integer zzqy;
  
  @Field(id = 6, type = "android.accounts.Account")
  private Account zzs;
  
  @Constructor
  AuthAccountRequest(@Param(id = 1) int paramInt, @Param(id = 2) IBinder paramIBinder, @Param(id = 3) Scope[] paramArrayOfScope, @Param(id = 4) Integer paramInteger1, @Param(id = 5) Integer paramInteger2, @Param(id = 6) Account paramAccount) {
    this.zzal = paramInt;
    this.zzqv = paramIBinder;
    this.zzqw = paramArrayOfScope;
    this.zzqx = paramInteger1;
    this.zzqy = paramInteger2;
    this.zzs = paramAccount;
  }
  
  public AuthAccountRequest(Account paramAccount, Set<Scope> paramSet) {
    this(3, null, paramSet.<Scope>toArray(new Scope[paramSet.size()]), null, null, Preconditions.<Account>checkNotNull(paramAccount));
  }
  
  @Deprecated
  public AuthAccountRequest(IAccountAccessor paramIAccountAccessor, Set<Scope> paramSet) {
    this(3, paramIAccountAccessor.asBinder(), paramSet.<Scope>toArray(new Scope[paramSet.size()]), null, null, null);
  }
  
  public Account getAccount() {
    return (this.zzs != null) ? this.zzs : ((this.zzqv != null) ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(this.zzqv)) : null);
  }
  
  @Nullable
  public Integer getOauthPolicy() {
    return this.zzqx;
  }
  
  @Nullable
  public Integer getPolicyAction() {
    return this.zzqy;
  }
  
  public Set<Scope> getScopes() {
    return new HashSet<>(Arrays.asList(this.zzqw));
  }
  
  public AuthAccountRequest setOauthPolicy(@Nullable Integer paramInteger) {
    this.zzqx = paramInteger;
    return this;
  }
  
  public AuthAccountRequest setPolicyAction(@Nullable Integer paramInteger) {
    this.zzqy = paramInteger;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeIBinder(paramParcel, 2, this.zzqv, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 3, (Parcelable[])this.zzqw, paramInt, false);
    SafeParcelWriter.writeIntegerObject(paramParcel, 4, this.zzqx, false);
    SafeParcelWriter.writeIntegerObject(paramParcel, 5, this.zzqy, false);
    SafeParcelWriter.writeParcelable(paramParcel, 6, (Parcelable)this.zzs, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\AuthAccountRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */