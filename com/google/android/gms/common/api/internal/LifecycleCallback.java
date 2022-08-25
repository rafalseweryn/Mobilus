package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@KeepForSdk
public class LifecycleCallback {
  @KeepForSdk
  protected final LifecycleFragment mLifecycleFragment;
  
  @KeepForSdk
  protected LifecycleCallback(LifecycleFragment paramLifecycleFragment) {
    this.mLifecycleFragment = paramLifecycleFragment;
  }
  
  @Keep
  private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity paramLifecycleActivity) {
    throw new IllegalStateException("Method not available in SDK.");
  }
  
  @KeepForSdk
  public static LifecycleFragment getFragment(Activity paramActivity) {
    return getFragment(new LifecycleActivity(paramActivity));
  }
  
  @KeepForSdk
  protected static LifecycleFragment getFragment(LifecycleActivity paramLifecycleActivity) {
    if (paramLifecycleActivity.zzbv())
      return zzcc.zza(paramLifecycleActivity.zzby()); 
    if (paramLifecycleActivity.zzbw())
      return zzbr.zzc(paramLifecycleActivity.zzbx()); 
    throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
  }
  
  @MainThread
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final Activity getActivity() {
    return this.mLifecycleFragment.getLifecycleActivity();
  }
  
  @MainThread
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  @MainThread
  public void onCreate(Bundle paramBundle) {}
  
  @MainThread
  public void onDestroy() {}
  
  @MainThread
  public void onResume() {}
  
  @MainThread
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  @MainThread
  public void onStart() {}
  
  @MainThread
  @KeepForSdk
  public void onStop() {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\LifecycleCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */