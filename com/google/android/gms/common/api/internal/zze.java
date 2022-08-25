package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zze extends zzc<Void> {
  private final RegisterListenerMethod<Api.AnyClient, ?> zzdw;
  
  private final UnregisterListenerMethod<Api.AnyClient, ?> zzdx;
  
  public zze(zzbv paramzzbv, TaskCompletionSource<Void> paramTaskCompletionSource) {
    super(3, paramTaskCompletionSource);
    this.zzdw = paramzzbv.zzlt;
    this.zzdx = paramzzbv.zzlu;
  }
  
  public final void zzb(GoogleApiManager.zza<?> paramzza) throws RemoteException {
    this.zzdw.registerListener(paramzza.zzae(), this.zzdu);
    if (this.zzdw.getListenerKey() != null)
      paramzza.zzbn().put(this.zzdw.getListenerKey(), new zzbv(this.zzdw, this.zzdx)); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */