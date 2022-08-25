package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors {
  public static final Executor MAIN_THREAD = new zza();
  
  static final Executor zzagd = new zzt();
  
  private static final class zza implements Executor {
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    
    public final void execute(@NonNull Runnable param1Runnable) {
      this.mHandler.post(param1Runnable);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\TaskExecutors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */