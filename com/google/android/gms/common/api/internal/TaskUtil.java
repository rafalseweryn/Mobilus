package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class TaskUtil {
  @KeepForSdk
  public static void setResultOrApiException(Status paramStatus, TaskCompletionSource<Void> paramTaskCompletionSource) {
    setResultOrApiException(paramStatus, null, paramTaskCompletionSource);
  }
  
  @KeepForSdk
  public static <TResult> void setResultOrApiException(Status paramStatus, TResult paramTResult, TaskCompletionSource<TResult> paramTaskCompletionSource) {
    if (paramStatus.isSuccess()) {
      paramTaskCompletionSource.setResult(paramTResult);
      return;
    } 
    paramTaskCompletionSource.setException((Exception)new ApiException(paramStatus));
  }
  
  @Deprecated
  @KeepForSdk
  public static Task<Void> toVoidTaskThatFailsOnFalse(Task<Boolean> paramTask) {
    return paramTask.continueWith(new zzcg());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\TaskUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */