package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzaa {
  private final Map<BasePendingResult<?>, Boolean> zzgw = Collections.synchronizedMap(new WeakHashMap<>());
  
  private final Map<TaskCompletionSource<?>, Boolean> zzgx = Collections.synchronizedMap(new WeakHashMap<>());
  
  private final void zza(boolean paramBoolean, Status paramStatus) {
    Map<BasePendingResult<?>, Boolean> map;
    synchronized (this.zzgw) {
      Map<TaskCompletionSource<?>, Boolean> map1;
      HashMap<Object, Object> hashMap = new HashMap<>();
      this((Map)this.zzgw);
      synchronized (this.zzgx) {
        map = new HashMap<>();
        super((Map)this.zzgx);
        for (Map.Entry<Object, Object> entry1 : hashMap.entrySet()) {
          if (paramBoolean || ((Boolean)entry1.getValue()).booleanValue())
            ((BasePendingResult)entry1.getKey()).zzb(paramStatus); 
        } 
        for (Map.Entry<BasePendingResult<?>, Boolean> entry : map.entrySet()) {
          if (paramBoolean || ((Boolean)entry.getValue()).booleanValue())
            ((TaskCompletionSource)entry.getKey()).trySetException((Exception)new ApiException(paramStatus)); 
        } 
        return;
      } 
    } 
  }
  
  final void zza(BasePendingResult<? extends Result> paramBasePendingResult, boolean paramBoolean) {
    this.zzgw.put(paramBasePendingResult, Boolean.valueOf(paramBoolean));
    paramBasePendingResult.addStatusListener(new zzab(this, paramBasePendingResult));
  }
  
  final <TResult> void zza(TaskCompletionSource<TResult> paramTaskCompletionSource, boolean paramBoolean) {
    this.zzgx.put(paramTaskCompletionSource, Boolean.valueOf(paramBoolean));
    paramTaskCompletionSource.getTask().addOnCompleteListener(new zzac(this, paramTaskCompletionSource));
  }
  
  final boolean zzaj() {
    return (!this.zzgw.isEmpty() || !this.zzgx.isEmpty());
  }
  
  public final void zzak() {
    zza(false, GoogleApiManager.zzjj);
  }
  
  public final void zzal() {
    zza(true, zzck.zzmm);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzaa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */