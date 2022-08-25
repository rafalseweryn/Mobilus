package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzf<ResultT> extends zzb {
  private final TaskCompletionSource<ResultT> zzdu;
  
  private final TaskApiCall<Api.AnyClient, ResultT> zzdy;
  
  private final StatusExceptionMapper zzdz;
  
  public zzf(int paramInt, TaskApiCall<Api.AnyClient, ResultT> paramTaskApiCall, TaskCompletionSource<ResultT> paramTaskCompletionSource, StatusExceptionMapper paramStatusExceptionMapper) {
    super(paramInt);
    this.zzdu = paramTaskCompletionSource;
    this.zzdy = paramTaskApiCall;
    this.zzdz = paramStatusExceptionMapper;
  }
  
  @Nullable
  public final Feature[] getRequiredFeatures() {
    return this.zzdy.zzca();
  }
  
  public final boolean shouldAutoResolveMissingFeatures() {
    return this.zzdy.shouldAutoResolveMissingFeatures();
  }
  
  public final void zza(@NonNull Status paramStatus) {
    this.zzdu.trySetException(this.zzdz.getException(paramStatus));
  }
  
  public final void zza(GoogleApiManager.zza<?> paramzza) throws DeadObjectException {
    try {
      this.zzdy.doExecute(paramzza.zzae(), this.zzdu);
      return;
    } catch (DeadObjectException deadObjectException) {
      throw deadObjectException;
    } catch (RemoteException remoteException) {
      super.zza(zzb.zzb(remoteException));
      return;
    } catch (RuntimeException runtimeException) {
      super.zza(runtimeException);
      return;
    } 
  }
  
  public final void zza(@NonNull zzaa paramzzaa, boolean paramBoolean) {
    paramzzaa.zza(this.zzdu, paramBoolean);
  }
  
  public final void zza(@NonNull RuntimeException paramRuntimeException) {
    this.zzdu.trySetException(paramRuntimeException);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */