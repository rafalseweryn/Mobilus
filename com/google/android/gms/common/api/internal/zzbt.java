package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzbt extends zzk {
  private TaskCompletionSource<Void> zzln = new TaskCompletionSource();
  
  private zzbt(LifecycleFragment paramLifecycleFragment) {
    super(paramLifecycleFragment);
    this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
  }
  
  public static zzbt zzd(Activity paramActivity) {
    LifecycleFragment lifecycleFragment = getFragment(paramActivity);
    zzbt zzbt1 = lifecycleFragment.<zzbt>getCallbackOrNull("GmsAvailabilityHelper", zzbt.class);
    if (zzbt1 != null) {
      if (zzbt1.zzln.getTask().isComplete())
        zzbt1.zzln = new TaskCompletionSource(); 
      return zzbt1;
    } 
    return new zzbt(lifecycleFragment);
  }
  
  public final Task<Void> getTask() {
    return this.zzln.getTask();
  }
  
  public final void onDestroy() {
    super.onDestroy();
    this.zzln.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    this.zzln.setException((Exception)ApiExceptionUtil.fromConnectionResult(paramConnectionResult));
  }
  
  protected final void zzr() {
    int i = this.zzdg.isGooglePlayServicesAvailable((Context)this.mLifecycleFragment.getLifecycleActivity());
    if (i == 0) {
      this.zzln.setResult(null);
      return;
    } 
    if (!this.zzln.getTask().isComplete())
      zzb(new ConnectionResult(i, null), 0); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */