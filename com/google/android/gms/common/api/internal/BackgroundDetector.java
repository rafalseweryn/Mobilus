package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
  private static final BackgroundDetector zzem = new BackgroundDetector();
  
  private final AtomicBoolean zzen = new AtomicBoolean();
  
  private final AtomicBoolean zzeo = new AtomicBoolean();
  
  @GuardedBy("sInstance")
  private final ArrayList<BackgroundStateChangeListener> zzep = new ArrayList<>();
  
  @GuardedBy("sInstance")
  private boolean zzeq = false;
  
  @KeepForSdk
  public static BackgroundDetector getInstance() {
    return zzem;
  }
  
  @KeepForSdk
  public static void initialize(Application paramApplication) {
    synchronized (zzem) {
      if (!zzem.zzeq) {
        paramApplication.registerActivityLifecycleCallbacks(zzem);
        paramApplication.registerComponentCallbacks((ComponentCallbacks)zzem);
        zzem.zzeq = true;
      } 
      return;
    } 
  }
  
  private final void onBackgroundStateChanged(boolean paramBoolean) {
    synchronized (zzem) {
      ArrayList<BackgroundStateChangeListener> arrayList = this.zzep;
      int i = arrayList.size();
      byte b = 0;
      while (b < i) {
        BackgroundStateChangeListener backgroundStateChangeListener = (BackgroundStateChangeListener)arrayList.get(b);
        b++;
        ((BackgroundStateChangeListener)backgroundStateChangeListener).onBackgroundStateChanged(paramBoolean);
      } 
      return;
    } 
  }
  
  @KeepForSdk
  public final void addListener(BackgroundStateChangeListener paramBackgroundStateChangeListener) {
    synchronized (zzem) {
      this.zzep.add(paramBackgroundStateChangeListener);
      return;
    } 
  }
  
  @KeepForSdk
  public final boolean isInBackground() {
    return this.zzen.get();
  }
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle) {
    boolean bool = this.zzen.compareAndSet(true, false);
    this.zzeo.set(true);
    if (bool)
      onBackgroundStateChanged(false); 
  }
  
  public final void onActivityDestroyed(Activity paramActivity) {}
  
  public final void onActivityPaused(Activity paramActivity) {}
  
  public final void onActivityResumed(Activity paramActivity) {
    boolean bool = this.zzen.compareAndSet(true, false);
    this.zzeo.set(true);
    if (bool)
      onBackgroundStateChanged(false); 
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public final void onActivityStarted(Activity paramActivity) {}
  
  public final void onActivityStopped(Activity paramActivity) {}
  
  public final void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public final void onLowMemory() {}
  
  public final void onTrimMemory(int paramInt) {
    if (paramInt == 20 && this.zzen.compareAndSet(false, true)) {
      this.zzeo.set(true);
      onBackgroundStateChanged(true);
    } 
  }
  
  @TargetApi(16)
  @KeepForSdk
  public final boolean readCurrentStateIfPossible(boolean paramBoolean) {
    if (!this.zzeo.get())
      if (PlatformVersion.isAtLeastJellyBean()) {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        if (!this.zzeo.getAndSet(true) && runningAppProcessInfo.importance > 100)
          this.zzen.set(true); 
      } else {
        return paramBoolean;
      }  
    return isInBackground();
  }
  
  @KeepForSdk
  public static interface BackgroundStateChangeListener {
    @KeepForSdk
    void onBackgroundStateChanged(boolean param1Boolean);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\BackgroundDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */