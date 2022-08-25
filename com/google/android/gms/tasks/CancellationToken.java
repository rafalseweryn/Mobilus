package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public abstract class CancellationToken {
  public abstract boolean isCancellationRequested();
  
  public abstract CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener paramOnTokenCanceledListener);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\CancellationToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */