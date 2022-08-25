package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzbr extends Fragment implements LifecycleFragment {
  private static WeakHashMap<Activity, WeakReference<zzbr>> zzla = new WeakHashMap<>();
  
  private Map<String, LifecycleCallback> zzlb = (Map<String, LifecycleCallback>)new ArrayMap();
  
  private int zzlc = 0;
  
  private Bundle zzld;
  
  public static zzbr zzc(Activity paramActivity) {
    WeakReference<zzbr> weakReference = zzla.get(paramActivity);
    if (weakReference != null) {
      zzbr zzbr1 = weakReference.get();
      if (zzbr1 != null)
        return zzbr1; 
    } 
    try {
      zzbr zzbr2 = (zzbr)paramActivity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
      if (zzbr2 != null) {
        zzbr zzbr3 = zzbr2;
        if (zzbr2.isRemoving()) {
          zzbr3 = new zzbr();
          paramActivity.getFragmentManager().beginTransaction().add(zzbr3, "LifecycleFragmentImpl").commitAllowingStateLoss();
          zzla.put(paramActivity, new WeakReference<>(zzbr3));
          return zzbr3;
        } 
        zzla.put(paramActivity, new WeakReference<>(zzbr3));
        return zzbr3;
      } 
      zzbr zzbr1 = new zzbr();
      paramActivity.getFragmentManager().beginTransaction().add(zzbr1, "LifecycleFragmentImpl").commitAllowingStateLoss();
      zzla.put(paramActivity, new WeakReference<>(zzbr1));
      return zzbr1;
    } catch (ClassCastException classCastException) {
      throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", classCastException);
    } 
  }
  
  public final void addCallback(String paramString, @NonNull LifecycleCallback paramLifecycleCallback) {
    if (!this.zzlb.containsKey(paramString)) {
      this.zzlb.put(paramString, paramLifecycleCallback);
      if (this.zzlc > 0)
        (new Handler(Looper.getMainLooper())).post(new zzbs(this, paramLifecycleCallback, paramString)); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 59);
    stringBuilder.append("LifecycleCallback with tag ");
    stringBuilder.append(paramString);
    stringBuilder.append(" already added to this fragment.");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    Iterator<LifecycleCallback> iterator = this.zzlb.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
  }
  
  public final <T extends LifecycleCallback> T getCallbackOrNull(String paramString, Class<T> paramClass) {
    return paramClass.cast(this.zzlb.get(paramString));
  }
  
  public final Activity getLifecycleActivity() {
    return getActivity();
  }
  
  public final boolean isCreated() {
    return (this.zzlc > 0);
  }
  
  public final boolean isStarted() {
    return (this.zzlc >= 2);
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Iterator<LifecycleCallback> iterator = this.zzlb.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onActivityResult(paramInt1, paramInt2, paramIntent); 
  }
  
  public final void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.zzlc = 1;
    this.zzld = paramBundle;
    for (Map.Entry<String, LifecycleCallback> entry : this.zzlb.entrySet()) {
      LifecycleCallback lifecycleCallback = (LifecycleCallback)entry.getValue();
      if (paramBundle != null) {
        Bundle bundle = paramBundle.getBundle((String)entry.getKey());
      } else {
        entry = null;
      } 
      lifecycleCallback.onCreate((Bundle)entry);
    } 
  }
  
  public final void onDestroy() {
    super.onDestroy();
    this.zzlc = 5;
    Iterator<LifecycleCallback> iterator = this.zzlb.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onDestroy(); 
  }
  
  public final void onResume() {
    super.onResume();
    this.zzlc = 3;
    Iterator<LifecycleCallback> iterator = this.zzlb.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onResume(); 
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    if (paramBundle == null)
      return; 
    for (Map.Entry<String, LifecycleCallback> entry : this.zzlb.entrySet()) {
      Bundle bundle = new Bundle();
      ((LifecycleCallback)entry.getValue()).onSaveInstanceState(bundle);
      paramBundle.putBundle((String)entry.getKey(), bundle);
    } 
  }
  
  public final void onStart() {
    super.onStart();
    this.zzlc = 2;
    Iterator<LifecycleCallback> iterator = this.zzlb.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onStart(); 
  }
  
  public final void onStop() {
    super.onStop();
    this.zzlc = 4;
    Iterator<LifecycleCallback> iterator = this.zzlb.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onStop(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */