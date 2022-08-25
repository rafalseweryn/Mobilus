package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

final class zza extends CancellationToken {
  private final zzu<Void> zzafh = new zzu<>();
  
  public final void cancel() {
    this.zzafh.trySetResult(null);
  }
  
  public final boolean isCancellationRequested() {
    return this.zzafh.isComplete();
  }
  
  public final CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener paramOnTokenCanceledListener) {
    this.zzafh.addOnSuccessListener(new zzb(this, paramOnTokenCanceledListener));
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */