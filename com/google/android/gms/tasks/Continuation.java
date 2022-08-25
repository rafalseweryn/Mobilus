package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public interface Continuation<TResult, TContinuationResult> {
  TContinuationResult then(@NonNull Task<TResult> paramTask) throws Exception;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\Continuation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */