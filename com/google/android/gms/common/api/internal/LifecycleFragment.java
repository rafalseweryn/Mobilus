package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface LifecycleFragment {
  @KeepForSdk
  void addCallback(String paramString, @NonNull LifecycleCallback paramLifecycleCallback);
  
  @KeepForSdk
  <T extends LifecycleCallback> T getCallbackOrNull(String paramString, Class<T> paramClass);
  
  @KeepForSdk
  Activity getLifecycleActivity();
  
  @KeepForSdk
  boolean isCreated();
  
  @KeepForSdk
  boolean isStarted();
  
  @KeepForSdk
  void startActivityForResult(Intent paramIntent, int paramInt);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\LifecycleFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */