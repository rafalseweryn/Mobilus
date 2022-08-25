package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.zzbx;

public abstract class ResultTransform<R extends Result, S extends Result> {
  @NonNull
  public final PendingResult<S> createFailedResult(@NonNull Status paramStatus) {
    return (PendingResult<S>)new zzbx(paramStatus);
  }
  
  @NonNull
  public Status onFailure(@NonNull Status paramStatus) {
    return paramStatus;
  }
  
  @Nullable
  @WorkerThread
  public abstract PendingResult<S> onSuccess(@NonNull R paramR);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\ResultTransform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */