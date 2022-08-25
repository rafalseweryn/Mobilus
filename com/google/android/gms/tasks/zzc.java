package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc<TResult, TContinuationResult> implements zzq<TResult> {
  private final Executor zzafk;
  
  private final Continuation<TResult, TContinuationResult> zzafl;
  
  private final zzu<TContinuationResult> zzafm;
  
  public zzc(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation, @NonNull zzu<TContinuationResult> paramzzu) {
    this.zzafk = paramExecutor;
    this.zzafl = paramContinuation;
    this.zzafm = paramzzu;
  }
  
  public final void cancel() {
    throw new UnsupportedOperationException();
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    this.zzafk.execute(new zzd(this, paramTask));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */