package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

final class zzd implements DeferredLifecycleHelper.zza {
  zzd(DeferredLifecycleHelper paramDeferredLifecycleHelper, FrameLayout paramFrameLayout, LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup) {}
  
  public final int getState() {
    return 2;
  }
  
  public final void zza(LifecycleDelegate paramLifecycleDelegate) {
    this.zzabj.removeAllViews();
    this.zzabj.addView(DeferredLifecycleHelper.zzb(this.zzabg).onCreateView(this.zzabk, container, this.zzabi));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\zzd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */