package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzg extends zzc<Boolean> {
  private final ListenerHolder.ListenerKey<?> zzea;
  
  public zzg(ListenerHolder.ListenerKey<?> paramListenerKey, TaskCompletionSource<Boolean> paramTaskCompletionSource) {
    super(4, paramTaskCompletionSource);
    this.zzea = paramListenerKey;
  }
  
  public final void zzb(GoogleApiManager.zza<?> paramzza) throws RemoteException {
    zzbv zzbv = paramzza.zzbn().remove(this.zzea);
    if (zzbv != null) {
      zzbv.zzlu.unregisterListener(paramzza.zzae(), this.zzdu);
      zzbv.zzlt.clearListener();
      return;
    } 
    this.zzdu.trySetResult(Boolean.valueOf(false));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */