package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface SuccessContinuation<TResult, TContinuationResult> {
  @NonNull
  Task<TContinuationResult> then(@Nullable TResult paramTResult) throws Exception;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\SuccessContinuation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */