package com.google.android.gms.signin.internal;

import android.accounts.Account;
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

@Class(creator = "RecordConsentRequestCreator")
public class RecordConsentRequest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<RecordConsentRequest> CREATOR = new RecordConsentRequestCreator();
  
  @Field(getter = "getScopesToConsent", id = 3)
  private final Scope[] zzadr;
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(getter = "getAccount", id = 2)
  private final Account zzs;
  
  @Field(getter = "getServerClientId", id = 4)
  private final String zzw;
  
  @Constructor
  RecordConsentRequest(@Param(id = 1) int paramInt, @Param(id = 2) Account paramAccount, @Param(id = 3) Scope[] paramArrayOfScope, @Param(id = 4) String paramString) {
    this.zzal = paramInt;
    this.zzs = paramAccount;
    this.zzadr = paramArrayOfScope;
    this.zzw = paramString;
  }
  
  public RecordConsentRequest(Account paramAccount, Scope[] paramArrayOfScope, String paramString) {
    this(1, paramAccount, paramArrayOfScope, paramString);
  }
  
  public Account getAccount() {
    return this.zzs;
  }
  
  public Scope[] getScopesToConsent() {
    return this.zzadr;
  }
  
  public String getServerClientId() {
    return this.zzw;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getAccount(), paramInt, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 3, (Parcelable[])getScopesToConsent(), paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 4, getServerClientId(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\RecordConsentRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */