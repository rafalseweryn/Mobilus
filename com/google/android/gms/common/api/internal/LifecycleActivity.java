package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public class LifecycleActivity {
  private final Object zzkz;
  
  public LifecycleActivity(Activity paramActivity) {
    Preconditions.checkNotNull(paramActivity, "Activity must not be null");
    this.zzkz = paramActivity;
  }
  
  public final boolean zzbv() {
    return this.zzkz instanceof FragmentActivity;
  }
  
  public final boolean zzbw() {
    return this.zzkz instanceof Activity;
  }
  
  public final Activity zzbx() {
    return (Activity)this.zzkz;
  }
  
  public final FragmentActivity zzby() {
    return (FragmentActivity)this.zzkz;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\LifecycleActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */