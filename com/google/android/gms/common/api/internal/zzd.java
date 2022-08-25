package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzd<A extends BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> extends zzb {
  private final A zzdv;
  
  public zzd(int paramInt, A paramA) {
    super(paramInt);
    this.zzdv = paramA;
  }
  
  public final void zza(@NonNull Status paramStatus) {
    this.zzdv.setFailedResult(paramStatus);
  }
  
  public final void zza(GoogleApiManager.zza<?> paramzza) throws DeadObjectException {
    try {
      this.zzdv.run(paramzza.zzae());
      return;
    } catch (RuntimeException runtimeException) {
      super.zza(runtimeException);
      return;
    } 
  }
  
  public final void zza(@NonNull zzaa paramzzaa, boolean paramBoolean) {
    paramzzaa.zza((BasePendingResult<? extends Result>)this.zzdv, paramBoolean);
  }
  
  public final void zza(@NonNull RuntimeException paramRuntimeException) {
    String str1 = paramRuntimeException.getClass().getSimpleName();
    String str2 = paramRuntimeException.getLocalizedMessage();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 2 + String.valueOf(str2).length());
    stringBuilder.append(str1);
    stringBuilder.append(": ");
    stringBuilder.append(str2);
    Status status = new Status(10, stringBuilder.toString());
    this.zzdv.setFailedResult(status);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */