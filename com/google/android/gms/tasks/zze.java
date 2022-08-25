package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zze<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult> {
  private final Executor zzafk;
  
  private final Continuation<TResult, Task<TContinuationResult>> zzafl;
  
  private final zzu<TContinuationResult> zzafm;
  
  public zze(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation, @NonNull zzu<TContinuationResult> paramzzu) {
    this.zzafk = paramExecutor;
    this.zzafl = paramContinuation;
    this.zzafm = paramzzu;
  }
  
  public final void cancel() {
    throw new UnsupportedOperationException();
  }
  
  public final void onCanceled() {
    this.zzafm.zzdp();
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    this.zzafk.execute(new zzf(this, paramTask));
  }
  
  public final void onFailure(@NonNull Exception paramException) {
    this.zzafm.setException(paramException);
  }
  
  public final void onSuccess(TContinuationResult paramTContinuationResult) {
    this.zzafm.setResult(paramTContinuationResult);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */