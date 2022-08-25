package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult> {
  private final zzu<TResult> zzafh = new zzu<>();
  
  public TaskCompletionSource() {}
  
  public TaskCompletionSource(@NonNull CancellationToken paramCancellationToken) {
    paramCancellationToken.onCanceledRequested(new zzs(this));
  }
  
  @NonNull
  public Task<TResult> getTask() {
    return this.zzafh;
  }
  
  public void setException(@NonNull Exception paramException) {
    this.zzafh.setException(paramException);
  }
  
  public void setResult(TResult paramTResult) {
    this.zzafh.setResult(paramTResult);
  }
  
  public boolean trySetException(@NonNull Exception paramException) {
    return this.zzafh.trySetException(paramException);
  }
  
  public boolean trySetResult(TResult paramTResult) {
    return this.zzafh.trySetResult(paramTResult);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\TaskCompletionSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */