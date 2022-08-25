package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberedThreadFactory implements ThreadFactory {
  private final int priority;
  
  private final ThreadFactory zzaau = Executors.defaultThreadFactory();
  
  private final String zzaav;
  
  private final AtomicInteger zzaaw = new AtomicInteger();
  
  public NumberedThreadFactory(String paramString) {
    this(paramString, 0);
  }
  
  public NumberedThreadFactory(String paramString, int paramInt) {
    this.zzaav = (String)Preconditions.checkNotNull(paramString, "Name must not be null");
    this.priority = paramInt;
  }
  
  public Thread newThread(Runnable paramRunnable) {
    Thread thread = this.zzaau.newThread(new zza(paramRunnable, this.priority));
    String str = this.zzaav;
    int i = this.zzaaw.getAndIncrement();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 13);
    stringBuilder.append(str);
    stringBuilder.append("[");
    stringBuilder.append(i);
    stringBuilder.append("]");
    thread.setName(stringBuilder.toString());
    return thread;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\concurrent\NumberedThreadFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */