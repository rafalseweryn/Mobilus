package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzo<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult> {
  private final Executor zzafk;
  
  private final zzu<TContinuationResult> zzafm;
  
  private final SuccessContinuation<TResult, TContinuationResult> zzafy;
  
  public zzo(@NonNull Executor paramExecutor, @NonNull SuccessContinuation<TResult, TContinuationResult> paramSuccessContinuation, @NonNull zzu<TContinuationResult> paramzzu) {
    this.zzafk = paramExecutor;
    this.zzafy = paramSuccessContinuation;
    this.zzafm = paramzzu;
  }
  
  public final void cancel() {
    throw new UnsupportedOperationException();
  }
  
  public final void onCanceled() {
    this.zzafm.zzdp();
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    this.zzafk.execute(new zzp(this, paramTask));
  }
  
  public final void onFailure(@NonNull Exception paramException) {
    this.zzafm.setException(paramException);
  }
  
  public final void onSuccess(TContinuationResult paramTContinuationResult) {
    this.zzafm.setResult(paramTContinuationResult);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */