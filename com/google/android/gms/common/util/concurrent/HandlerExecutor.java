package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public class HandlerExecutor implements Executor {
  private final Handler handler;
  
  public HandlerExecutor(Handler paramHandler) {
    this(paramHandler.getLooper());
  }
  
  public HandlerExecutor(Looper paramLooper) {
    this.handler = new Handler(paramLooper);
  }
  
  public void execute(@NonNull Runnable paramRunnable) {
    this.handler.post(paramRunnable);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\concurrent\HandlerExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */