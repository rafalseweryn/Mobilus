package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
  private final ListenerHolder<L> zzls;
  
  @KeepForSdk
  protected RegisterListenerMethod(ListenerHolder<L> paramListenerHolder) {
    this.zzls = paramListenerHolder;
  }
  
  @KeepForSdk
  public void clearListener() {
    this.zzls.clear();
  }
  
  @KeepForSdk
  public ListenerHolder.ListenerKey<L> getListenerKey() {
    return this.zzls.getListenerKey();
  }
  
  @KeepForSdk
  protected abstract void registerListener(A paramA, TaskCompletionSource<Void> paramTaskCompletionSource) throws RemoteException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\RegisterListenerMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */