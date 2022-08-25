package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzc<T> extends zzb {
  protected final TaskCompletionSource<T> zzdu;
  
  public zzc(int paramInt, TaskCompletionSource<T> paramTaskCompletionSource) {
    super(paramInt);
    this.zzdu = paramTaskCompletionSource;
  }
  
  public void zza(@NonNull Status paramStatus) {
    this.zzdu.trySetException((Exception)new ApiException(paramStatus));
  }
  
  public final void zza(GoogleApiManager.zza<?> paramzza) throws DeadObjectException {
    try {
      zzb(paramzza);
      return;
    } catch (DeadObjectException deadObjectException) {
      super.zza(zzb.zzb((RemoteException)deadObjectException));
      throw deadObjectException;
    } catch (RemoteException remoteException) {
      super.zza(zzb.zzb(remoteException));
      return;
    } catch (RuntimeException runtimeException) {
      super.zza(runtimeException);
      return;
    } 
  }
  
  public void zza(@NonNull zzaa paramzzaa, boolean paramBoolean) {}
  
  public void zza(@NonNull RuntimeException paramRuntimeException) {
    this.zzdu.trySetException(paramRuntimeException);
  }
  
  protected abstract void zzb(GoogleApiManager.zza<?> paramzza) throws RemoteException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */