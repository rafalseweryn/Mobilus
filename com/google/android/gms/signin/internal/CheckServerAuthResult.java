package com.google.android.gms.signin.internal;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Class(creator = "CheckServerAuthResultCreator")
public class CheckServerAuthResult extends AbstractSafeParcelable {
  public static final Parcelable.Creator<CheckServerAuthResult> CREATOR = new CheckServerAuthResultCreator();
  
  @Field(id = 2)
  private final boolean zzadp;
  
  @Field(id = 3)
  private final List<Scope> zzadq;
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Constructor
  CheckServerAuthResult(@Param(id = 1) int paramInt, @Param(id = 2) boolean paramBoolean, @Param(id = 3) List<Scope> paramList) {
    this.zzal = paramInt;
    this.zzadp = paramBoolean;
    this.zzadq = paramList;
  }
  
  public CheckServerAuthResult(boolean paramBoolean, Set<Scope> paramSet) {
    this(1, paramBoolean, (List)list);
  }
  
  public Set<Scope> getAdditionalScopes() {
    return new HashSet<>(this.zzadq);
  }
  
  public boolean isNewAuthCodeRequired() {
    return this.zzadp;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeBoolean(paramParcel, 2, this.zzadp);
    SafeParcelWriter.writeTypedList(paramParcel, 3, this.zzadq, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\internal\CheckServerAuthResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */