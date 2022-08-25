package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "ClientIdentityCreator")
@Reserved({1000})
public class ClientIdentity extends AbstractSafeParcelable {
  public static final Parcelable.Creator<ClientIdentity> CREATOR = new ClientIdentityCreator();
  
  @Nullable
  @Field(defaultValueUnchecked = "null", id = 2)
  public final String packageName;
  
  @Field(defaultValueUnchecked = "0", id = 1)
  public final int uid;
  
  @Constructor
  public ClientIdentity(@Param(id = 1) int paramInt, @Nullable @Param(id = 2) String paramString) {
    this.uid = paramInt;
    this.packageName = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (paramObject != null) {
      if (!(paramObject instanceof ClientIdentity))
        return false; 
      paramObject = paramObject;
      if (((ClientIdentity)paramObject).uid == this.uid && Objects.equal(((ClientIdentity)paramObject).packageName, this.packageName))
        return true; 
    } 
    return false;
  }
  
  public int hashCode() {
    return this.uid;
  }
  
  public String toString() {
    int i = this.uid;
    String str = this.packageName;
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 12);
    stringBuilder.append(i);
    stringBuilder.append(":");
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.uid);
    SafeParcelWriter.writeString(paramParcel, 2, this.packageName, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ClientIdentity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */