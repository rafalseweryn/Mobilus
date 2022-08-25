package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public class BooleanResult implements Result {
  private final Status mStatus;
  
  private final boolean zzck;
  
  @KeepForSdk
  public BooleanResult(Status paramStatus, boolean paramBoolean) {
    this.mStatus = (Status)Preconditions.checkNotNull(paramStatus, "Status must not be null");
    this.zzck = paramBoolean;
  }
  
  @KeepForSdk
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof BooleanResult))
      return false; 
    paramObject = paramObject;
    return (this.mStatus.equals(((BooleanResult)paramObject).mStatus) && this.zzck == ((BooleanResult)paramObject).zzck);
  }
  
  @KeepForSdk
  public Status getStatus() {
    return this.mStatus;
  }
  
  @KeepForSdk
  public boolean getValue() {
    return this.zzck;
  }
  
  @KeepForSdk
  public final int hashCode() {
    return (this.mStatus.hashCode() + 527) * 31 + this.zzck;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\BooleanResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */