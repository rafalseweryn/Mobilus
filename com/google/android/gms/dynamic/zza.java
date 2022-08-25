package com.google.android.gms.dynamic;

import android.os.Bundle;
import java.util.Iterator;

final class zza implements OnDelegateCreatedListener<T> {
  zza(DeferredLifecycleHelper paramDeferredLifecycleHelper) {}
  
  public final void onDelegateCreated(T paramT) {
    DeferredLifecycleHelper.zza(this.zzabg, (LifecycleDelegate)paramT);
    Iterator<DeferredLifecycleHelper.zza> iterator = DeferredLifecycleHelper.zza(this.zzabg).iterator();
    while (iterator.hasNext())
      ((DeferredLifecycleHelper.zza)iterator.next()).zza(DeferredLifecycleHelper.zzb(this.zzabg)); 
    DeferredLifecycleHelper.zza(this.zzabg).clear();
    DeferredLifecycleHelper.zza(this.zzabg, (Bundle)null);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */