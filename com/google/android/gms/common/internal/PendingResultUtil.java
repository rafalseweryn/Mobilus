package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class PendingResultUtil {
  private static final StatusConverter zzun = new zzk();
  
  public static <R extends com.google.android.gms.common.api.Result, T extends Response<R>> Task<T> toResponseTask(PendingResult<R> paramPendingResult, T paramT) {
    return toTask(paramPendingResult, new zzm((Response)paramT));
  }
  
  public static <R extends com.google.android.gms.common.api.Result, T> Task<T> toTask(PendingResult<R> paramPendingResult, ResultConverter<R, T> paramResultConverter) {
    return toTask(paramPendingResult, paramResultConverter, zzun);
  }
  
  public static <R extends com.google.android.gms.common.api.Result, T> Task<T> toTask(PendingResult<R> paramPendingResult, ResultConverter<R, T> paramResultConverter, StatusConverter paramStatusConverter) {
    TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
    paramPendingResult.addStatusListener(new zzl(paramPendingResult, taskCompletionSource, paramResultConverter, paramStatusConverter));
    return taskCompletionSource.getTask();
  }
  
  public static <R extends com.google.android.gms.common.api.Result> Task<Void> toVoidTask(PendingResult<R> paramPendingResult) {
    return toTask(paramPendingResult, new zzn());
  }
  
  public static interface ResultConverter<R extends com.google.android.gms.common.api.Result, T> {
    T convert(R param1R);
  }
  
  public static interface StatusConverter {
    ApiException convert(Status param1Status);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\PendingResultUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */