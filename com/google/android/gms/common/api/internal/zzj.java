package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import java.util.Set;

public final class zzj {
  private final ArrayMap<zzh<?>, ConnectionResult> zzcc = new ArrayMap();
  
  private final ArrayMap<zzh<?>, String> zzei = new ArrayMap();
  
  private final TaskCompletionSource<Map<zzh<?>, String>> zzej = new TaskCompletionSource();
  
  private int zzek;
  
  private boolean zzel = false;
  
  public zzj(Iterable<? extends GoogleApi<?>> paramIterable) {
    for (GoogleApi<?> googleApi : paramIterable)
      this.zzcc.put(googleApi.zzm(), null); 
    this.zzek = this.zzcc.keySet().size();
  }
  
  public final Task<Map<zzh<?>, String>> getTask() {
    return this.zzej.getTask();
  }
  
  public final void zza(zzh<?> paramzzh, ConnectionResult paramConnectionResult, @Nullable String paramString) {
    this.zzcc.put(paramzzh, paramConnectionResult);
    this.zzei.put(paramzzh, paramString);
    this.zzek--;
    if (!paramConnectionResult.isSuccess())
      this.zzel = true; 
    if (this.zzek == 0) {
      if (this.zzel) {
        AvailabilityException availabilityException = new AvailabilityException(this.zzcc);
        this.zzej.setException((Exception)availabilityException);
        return;
      } 
      this.zzej.setResult(this.zzei);
    } 
  }
  
  public final Set<zzh<?>> zzs() {
    return this.zzcc.keySet();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */