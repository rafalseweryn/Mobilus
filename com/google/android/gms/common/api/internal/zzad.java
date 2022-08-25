package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

public class zzad extends zzk {
  private GoogleApiManager zzcq;
  
  private final ArraySet<zzh<?>> zzhb = new ArraySet();
  
  private zzad(LifecycleFragment paramLifecycleFragment) {
    super(paramLifecycleFragment);
    this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
  }
  
  public static void zza(Activity paramActivity, GoogleApiManager paramGoogleApiManager, zzh<?> paramzzh) {
    LifecycleFragment lifecycleFragment = getFragment(paramActivity);
    zzad zzad2 = lifecycleFragment.<zzad>getCallbackOrNull("ConnectionlessLifecycleHelper", zzad.class);
    zzad zzad1 = zzad2;
    if (zzad2 == null)
      zzad1 = new zzad(lifecycleFragment); 
    zzad1.zzcq = paramGoogleApiManager;
    Preconditions.checkNotNull(paramzzh, "ApiKey cannot be null");
    zzad1.zzhb.add(paramzzh);
    paramGoogleApiManager.zza(zzad1);
  }
  
  private final void zzan() {
    if (!this.zzhb.isEmpty())
      this.zzcq.zza(this); 
  }
  
  public final void onResume() {
    super.onResume();
    zzan();
  }
  
  public final void onStart() {
    super.onStart();
    zzan();
  }
  
  public void onStop() {
    super.onStop();
    this.zzcq.zzb(this);
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    this.zzcq.zza(paramConnectionResult, paramInt);
  }
  
  final ArraySet<zzh<?>> zzam() {
    return this.zzhb;
  }
  
  protected final void zzr() {
    this.zzcq.zzr();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */