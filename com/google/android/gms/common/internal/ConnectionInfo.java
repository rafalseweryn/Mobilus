package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "ConnectionInfoCreator")
public class ConnectionInfo extends AbstractSafeParcelable {
  public static final Parcelable.Creator<ConnectionInfo> CREATOR = new ConnectionInfoCreator();
  
  @Field(id = 1)
  private Bundle zzsf;
  
  @Field(id = 2)
  private Feature[] zzsg;
  
  public ConnectionInfo() {}
  
  @Constructor
  ConnectionInfo(@Param(id = 1) Bundle paramBundle, @Param(id = 2) Feature[] paramArrayOfFeature) {
    this.zzsf = paramBundle;
    this.zzsg = paramArrayOfFeature;
  }
  
  public Feature[] getAvailableFeatures() {
    return this.zzsg;
  }
  
  public Bundle getResolutionBundle() {
    return this.zzsf;
  }
  
  public ConnectionInfo setAvailableFeatures(Feature[] paramArrayOfFeature) {
    this.zzsg = paramArrayOfFeature;
    return this;
  }
  
  public ConnectionInfo setResolutionBundle(Bundle paramBundle) {
    this.zzsf = paramBundle;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeBundle(paramParcel, 1, this.zzsf, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 2, (Parcelable[])this.zzsg, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ConnectionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */