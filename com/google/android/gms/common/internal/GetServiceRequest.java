package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Collection;

@Class(creator = "GetServiceRequestCreator")
@Reserved({9})
public class GetServiceRequest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<GetServiceRequest> CREATOR = new GetServiceRequestCreator();
  
  @VersionField(id = 1)
  private final int version = 4;
  
  @Field(id = 2)
  private final int zzst;
  
  @Field(id = 3)
  private int zzsu;
  
  @Field(id = 4)
  private String zzsv;
  
  @Field(id = 5)
  private IBinder zzsw;
  
  @Field(id = 6)
  private Scope[] zzsx;
  
  @Field(id = 7)
  private Bundle zzsy;
  
  @Field(id = 8)
  private Account zzsz;
  
  @Field(id = 10)
  private Feature[] zzta;
  
  @Field(id = 11)
  private Feature[] zztb;
  
  @Field(id = 12)
  private boolean zztc;
  
  public GetServiceRequest(int paramInt) {
    this.zzsu = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    this.zzst = paramInt;
    this.zztc = true;
  }
  
  @Constructor
  GetServiceRequest(@Param(id = 1) int paramInt1, @Param(id = 2) int paramInt2, @Param(id = 3) int paramInt3, @Param(id = 4) String paramString, @Param(id = 5) IBinder paramIBinder, @Param(id = 6) Scope[] paramArrayOfScope, @Param(id = 7) Bundle paramBundle, @Param(id = 8) Account paramAccount, @Param(id = 10) Feature[] paramArrayOfFeature1, @Param(id = 11) Feature[] paramArrayOfFeature2, @Param(id = 12) boolean paramBoolean) {
    this.zzst = paramInt2;
    this.zzsu = paramInt3;
    if ("com.google.android.gms".equals(paramString)) {
      this.zzsv = "com.google.android.gms";
    } else {
      this.zzsv = paramString;
    } 
    if (paramInt1 < 2) {
      this.zzsz = zzb(paramIBinder);
    } else {
      this.zzsw = paramIBinder;
      this.zzsz = paramAccount;
    } 
    this.zzsx = paramArrayOfScope;
    this.zzsy = paramBundle;
    this.zzta = paramArrayOfFeature1;
    this.zztb = paramArrayOfFeature2;
    this.zztc = paramBoolean;
  }
  
  public static Parcelable.Creator<GetServiceRequest> getCreator() {
    return CREATOR;
  }
  
  private static Account zzb(IBinder paramIBinder) {
    return (paramIBinder != null) ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(paramIBinder)) : null;
  }
  
  public Account getAuthenticatedAccount() {
    return zzb(this.zzsw);
  }
  
  public String getCallingPackage() {
    return this.zzsv;
  }
  
  public Feature[] getClientApiFeatures() {
    return this.zztb;
  }
  
  public int getClientLibraryVersion() {
    return this.zzsu;
  }
  
  public Account getClientRequestedAccount() {
    return this.zzsz;
  }
  
  public Feature[] getClientRequiredFeatures() {
    return this.zzta;
  }
  
  public Bundle getExtraArgs() {
    return this.zzsy;
  }
  
  public Scope[] getScopes() {
    return this.zzsx;
  }
  
  public int getServiceId() {
    return this.zzst;
  }
  
  public boolean isRequestingConnectionInfo() {
    return this.zztc;
  }
  
  public GetServiceRequest setAuthenticatedAccount(IAccountAccessor paramIAccountAccessor) {
    if (paramIAccountAccessor != null)
      this.zzsw = paramIAccountAccessor.asBinder(); 
    return this;
  }
  
  public GetServiceRequest setCallingPackage(String paramString) {
    this.zzsv = paramString;
    return this;
  }
  
  public GetServiceRequest setClientApiFeatures(Feature[] paramArrayOfFeature) {
    this.zztb = paramArrayOfFeature;
    return this;
  }
  
  public GetServiceRequest setClientLibraryVersion(int paramInt) {
    this.zzsu = paramInt;
    return this;
  }
  
  public GetServiceRequest setClientRequestedAccount(Account paramAccount) {
    this.zzsz = paramAccount;
    return this;
  }
  
  public GetServiceRequest setClientRequiredFeatures(Feature[] paramArrayOfFeature) {
    this.zzta = paramArrayOfFeature;
    return this;
  }
  
  public GetServiceRequest setExtraArgs(Bundle paramBundle) {
    this.zzsy = paramBundle;
    return this;
  }
  
  public GetServiceRequest setRequestingConnectionInfo(boolean paramBoolean) {
    this.zztc = paramBoolean;
    return this;
  }
  
  public GetServiceRequest setScopes(Collection<Scope> paramCollection) {
    this.zzsx = paramCollection.<Scope>toArray(new Scope[paramCollection.size()]);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.version);
    SafeParcelWriter.writeInt(paramParcel, 2, this.zzst);
    SafeParcelWriter.writeInt(paramParcel, 3, this.zzsu);
    SafeParcelWriter.writeString(paramParcel, 4, this.zzsv, false);
    SafeParcelWriter.writeIBinder(paramParcel, 5, this.zzsw, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 6, (Parcelable[])this.zzsx, paramInt, false);
    SafeParcelWriter.writeBundle(paramParcel, 7, this.zzsy, false);
    SafeParcelWriter.writeParcelable(paramParcel, 8, (Parcelable)this.zzsz, paramInt, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 10, (Parcelable[])this.zzta, paramInt, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 11, (Parcelable[])this.zztb, paramInt, false);
    SafeParcelWriter.writeBoolean(paramParcel, 12, this.zztc);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GetServiceRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */