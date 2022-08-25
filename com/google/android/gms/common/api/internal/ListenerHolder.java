package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public final class ListenerHolder<L> {
  private final zza zzlh;
  
  private volatile L zzli;
  
  private final ListenerKey<L> zzlj;
  
  @KeepForSdk
  ListenerHolder(@NonNull Looper paramLooper, @NonNull L paramL, @NonNull String paramString) {
    this.zzlh = new zza(this, paramLooper);
    this.zzli = (L)Preconditions.checkNotNull(paramL, "Listener must not be null");
    this.zzlj = new ListenerKey<>(paramL, Preconditions.checkNotEmpty(paramString));
  }
  
  @KeepForSdk
  public final void clear() {
    this.zzli = null;
  }
  
  @NonNull
  @KeepForSdk
  public final ListenerKey<L> getListenerKey() {
    return this.zzlj;
  }
  
  @KeepForSdk
  public final boolean hasListener() {
    return (this.zzli != null);
  }
  
  @KeepForSdk
  public final void notifyListener(Notifier<? super L> paramNotifier) {
    Preconditions.checkNotNull(paramNotifier, "Notifier must not be null");
    Message message = this.zzlh.obtainMessage(1, paramNotifier);
    this.zzlh.sendMessage(message);
  }
  
  @KeepForSdk
  final void notifyListenerInternal(Notifier<? super L> paramNotifier) {
    L l = this.zzli;
    if (l == null) {
      paramNotifier.onNotifyListenerFailed();
      return;
    } 
    try {
      paramNotifier.notifyListener(l);
      return;
    } catch (RuntimeException runtimeException) {
      paramNotifier.onNotifyListenerFailed();
      throw runtimeException;
    } 
  }
  
  @KeepForSdk
  public static final class ListenerKey<L> {
    private final L zzli;
    
    private final String zzll;
    
    @KeepForSdk
    ListenerKey(L param1L, String param1String) {
      this.zzli = param1L;
      this.zzll = param1String;
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof ListenerKey))
        return false; 
      param1Object = param1Object;
      return (this.zzli == ((ListenerKey)param1Object).zzli && this.zzll.equals(((ListenerKey)param1Object).zzll));
    }
    
    public final int hashCode() {
      return System.identityHashCode(this.zzli) * 31 + this.zzll.hashCode();
    }
  }
  
  @KeepForSdk
  public static interface Notifier<L> {
    @KeepForSdk
    void notifyListener(L param1L);
    
    @KeepForSdk
    void onNotifyListenerFailed();
  }
  
  private final class zza extends Handler {
    public zza(ListenerHolder this$0, Looper param1Looper) {
      super(param1Looper);
    }
    
    public final void handleMessage(Message param1Message) {
      int i = param1Message.what;
      boolean bool = true;
      if (i != 1)
        bool = false; 
      Preconditions.checkArgument(bool);
      this.zzlk.notifyListenerInternal((ListenerHolder.Notifier)param1Message.obj);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\ListenerHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */