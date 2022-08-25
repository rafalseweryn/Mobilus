package com.google.android.gms.common.internal;

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

@Class(creator = "SignInButtonConfigCreator")
public class SignInButtonConfig extends AbstractSafeParcelable {
  public static final Parcelable.Creator<SignInButtonConfig> CREATOR = new SignInButtonConfigCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Deprecated
  @Field(getter = "getScopes", id = 4)
  private final Scope[] zzqw;
  
  @Field(getter = "getButtonSize", id = 2)
  private final int zzux;
  
  @Field(getter = "getColorScheme", id = 3)
  private final int zzuy;
  
  @Constructor
  SignInButtonConfig(@Param(id = 1) int paramInt1, @Param(id = 2) int paramInt2, @Param(id = 3) int paramInt3, @Param(id = 4) Scope[] paramArrayOfScope) {
    this.zzal = paramInt1;
    this.zzux = paramInt2;
    this.zzuy = paramInt3;
    this.zzqw = paramArrayOfScope;
  }
  
  public SignInButtonConfig(int paramInt1, int paramInt2, Scope[] paramArrayOfScope) {
    this(1, paramInt1, paramInt2, null);
  }
  
  public int getButtonSize() {
    return this.zzux;
  }
  
  public int getColorScheme() {
    return this.zzuy;
  }
  
  @Deprecated
  public Scope[] getScopes() {
    return this.zzqw;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeInt(paramParcel, 2, getButtonSize());
    SafeParcelWriter.writeInt(paramParcel, 3, getColorScheme());
    SafeParcelWriter.writeTypedArray(paramParcel, 4, (Parcelable[])getScopes(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\SignInButtonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */