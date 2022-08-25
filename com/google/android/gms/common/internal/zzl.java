package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

final class zzl implements PendingResult.StatusListener {
  zzl(PendingResult paramPendingResult, TaskCompletionSource paramTaskCompletionSource, PendingResultUtil.ResultConverter paramResultConverter, PendingResultUtil.StatusConverter paramStatusConverter) {}
  
  public final void onComplete(Status paramStatus) {
    Result result;
    if (paramStatus.isSuccess()) {
      result = this.zzuo.await(0L, TimeUnit.MILLISECONDS);
      this.zzup.setResult(this.zzuq.convert(result));
      return;
    } 
    this.zzup.setException((Exception)this.zzur.convert((Status)result));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\zzl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */