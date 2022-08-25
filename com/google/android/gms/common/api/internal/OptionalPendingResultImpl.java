package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.TransformedResult;
import java.util.concurrent.TimeUnit;

@KeepForSdk
public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {
  private final BasePendingResult<R> zzlo;
  
  public OptionalPendingResultImpl(PendingResult<R> paramPendingResult) {
    this.zzlo = (BasePendingResult<R>)paramPendingResult;
  }
  
  public final void addStatusListener(PendingResult.StatusListener paramStatusListener) {
    this.zzlo.addStatusListener(paramStatusListener);
  }
  
  public final R await() {
    return (R)this.zzlo.await();
  }
  
  public final R await(long paramLong, TimeUnit paramTimeUnit) {
    return (R)this.zzlo.await(paramLong, paramTimeUnit);
  }
  
  public final void cancel() {
    this.zzlo.cancel();
  }
  
  public final R get() {
    if (super.isDone())
      return (R)super.await(0L, TimeUnit.MILLISECONDS); 
    throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
  }
  
  public final boolean isCanceled() {
    return this.zzlo.isCanceled();
  }
  
  public final boolean isDone() {
    return this.zzlo.isReady();
  }
  
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback) {
    this.zzlo.setResultCallback(paramResultCallback);
  }
  
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback, long paramLong, TimeUnit paramTimeUnit) {
    this.zzlo.setResultCallback(paramResultCallback, paramLong, paramTimeUnit);
  }
  
  @NonNull
  public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform) {
    return this.zzlo.then(paramResultTransform);
  }
  
  public final Integer zzo() {
    return this.zzlo.zzo();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\OptionalPendingResultImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */