package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

@KeepForSdk
public class ListenerHolders {
  private final Set<ListenerHolder<?>> zzlm = Collections.newSetFromMap(new WeakHashMap<>());
  
  @KeepForSdk
  public static <L> ListenerHolder<L> createListenerHolder(@NonNull L paramL, @NonNull Looper paramLooper, @NonNull String paramString) {
    Preconditions.checkNotNull(paramL, "Listener must not be null");
    Preconditions.checkNotNull(paramLooper, "Looper must not be null");
    Preconditions.checkNotNull(paramString, "Listener type must not be null");
    return new ListenerHolder<>(paramLooper, paramL, paramString);
  }
  
  @KeepForSdk
  public static <L> ListenerHolder.ListenerKey<L> createListenerKey(@NonNull L paramL, @NonNull String paramString) {
    Preconditions.checkNotNull(paramL, "Listener must not be null");
    Preconditions.checkNotNull(paramString, "Listener type must not be null");
    Preconditions.checkNotEmpty(paramString, "Listener type must not be empty");
    return new ListenerHolder.ListenerKey<>(paramL, paramString);
  }
  
  public final void release() {
    Iterator<ListenerHolder<?>> iterator = this.zzlm.iterator();
    while (iterator.hasNext())
      ((ListenerHolder)iterator.next()).clear(); 
    this.zzlm.clear();
  }
  
  public final <L> ListenerHolder<L> zza(@NonNull L paramL, @NonNull Looper paramLooper, @NonNull String paramString) {
    ListenerHolder<L> listenerHolder = createListenerHolder(paramL, paramLooper, paramString);
    this.zzlm.add(listenerHolder);
    return listenerHolder;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\ListenerHolders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */