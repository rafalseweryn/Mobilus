package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public final class Tasks {
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask) throws ExecutionException, InterruptedException {
    Preconditions.checkNotMainThread();
    Preconditions.checkNotNull(paramTask, "Task must not be null");
    if (paramTask.isComplete())
      return zzb(paramTask); 
    zza zza = new zza(null);
    zza(paramTask, zza);
    zza.await();
    return zzb(paramTask);
  }
  
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask, long paramLong, @NonNull TimeUnit paramTimeUnit) throws ExecutionException, InterruptedException, TimeoutException {
    Preconditions.checkNotMainThread();
    Preconditions.checkNotNull(paramTask, "Task must not be null");
    Preconditions.checkNotNull(paramTimeUnit, "TimeUnit must not be null");
    if (paramTask.isComplete())
      return zzb(paramTask); 
    zza zza = new zza(null);
    zza(paramTask, zza);
    if (!zza.await(paramLong, paramTimeUnit))
      throw new TimeoutException("Timed out waiting for Task"); 
    return zzb(paramTask);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Callable<TResult> paramCallable) {
    return call(TaskExecutors.MAIN_THREAD, paramCallable);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Executor paramExecutor, @NonNull Callable<TResult> paramCallable) {
    Preconditions.checkNotNull(paramExecutor, "Executor must not be null");
    Preconditions.checkNotNull(paramCallable, "Callback must not be null");
    zzu<TResult> zzu = new zzu();
    paramExecutor.execute(new zzv(zzu, paramCallable));
    return zzu;
  }
  
  public static <TResult> Task<TResult> forCanceled() {
    zzu<TResult> zzu = new zzu();
    zzu.zzdp();
    return zzu;
  }
  
  public static <TResult> Task<TResult> forException(@NonNull Exception paramException) {
    zzu<TResult> zzu = new zzu();
    zzu.setException(paramException);
    return zzu;
  }
  
  public static <TResult> Task<TResult> forResult(TResult paramTResult) {
    zzu<TResult> zzu = new zzu();
    zzu.setResult(paramTResult);
    return zzu;
  }
  
  public static Task<Void> whenAll(Collection<? extends Task<?>> paramCollection) {
    if (paramCollection.isEmpty())
      return forResult(null); 
    Iterator<? extends Task<?>> iterator2 = paramCollection.iterator();
    while (iterator2.hasNext()) {
      if ((Task)iterator2.next() == null)
        throw new NullPointerException("null tasks are not accepted"); 
    } 
    zzu<Void> zzu = new zzu();
    zzc zzc = new zzc(paramCollection.size(), zzu);
    Iterator<? extends Task<?>> iterator1 = paramCollection.iterator();
    while (iterator1.hasNext())
      zza(iterator1.next(), zzc); 
    return zzu;
  }
  
  public static Task<Void> whenAll(Task<?>... paramVarArgs) {
    return (paramVarArgs.length == 0) ? forResult(null) : whenAll(Arrays.asList(paramVarArgs));
  }
  
  public static Task<List<Task<?>>> whenAllComplete(Collection<? extends Task<?>> paramCollection) {
    return whenAll(paramCollection).continueWithTask(new zzx(paramCollection));
  }
  
  public static Task<List<Task<?>>> whenAllComplete(Task<?>... paramVarArgs) {
    return whenAllComplete(Arrays.asList(paramVarArgs));
  }
  
  public static <TResult> Task<List<TResult>> whenAllSuccess(Collection<? extends Task<?>> paramCollection) {
    return whenAll(paramCollection).continueWith(new zzw(paramCollection));
  }
  
  public static <TResult> Task<List<TResult>> whenAllSuccess(Task<?>... paramVarArgs) {
    return whenAllSuccess(Arrays.asList(paramVarArgs));
  }
  
  private static void zza(Task<?> paramTask, zzb paramzzb) {
    paramTask.addOnSuccessListener(TaskExecutors.zzagd, paramzzb);
    paramTask.addOnFailureListener(TaskExecutors.zzagd, paramzzb);
    paramTask.addOnCanceledListener(TaskExecutors.zzagd, paramzzb);
  }
  
  private static <TResult> TResult zzb(Task<TResult> paramTask) throws ExecutionException {
    if (paramTask.isSuccessful())
      return paramTask.getResult(); 
    if (paramTask.isCanceled())
      throw new CancellationException("Task is already canceled"); 
    throw new ExecutionException(paramTask.getException());
  }
  
  private static final class zza implements zzb {
    private final CountDownLatch zzfd = new CountDownLatch(1);
    
    private zza() {}
    
    public final void await() throws InterruptedException {
      this.zzfd.await();
    }
    
    public final boolean await(long param1Long, TimeUnit param1TimeUnit) throws InterruptedException {
      return this.zzfd.await(param1Long, param1TimeUnit);
    }
    
    public final void onCanceled() {
      this.zzfd.countDown();
    }
    
    public final void onFailure(@NonNull Exception param1Exception) {
      this.zzfd.countDown();
    }
    
    public final void onSuccess(Object param1Object) {
      this.zzfd.countDown();
    }
  }
  
  static interface zzb extends OnCanceledListener, OnFailureListener, OnSuccessListener<Object> {}
  
  private static final class zzc implements zzb {
    private final Object mLock = new Object();
    
    private final zzu<Void> zzafh;
    
    @GuardedBy("mLock")
    private Exception zzagh;
    
    private final int zzagl;
    
    @GuardedBy("mLock")
    private int zzagm;
    
    @GuardedBy("mLock")
    private int zzagn;
    
    @GuardedBy("mLock")
    private int zzago;
    
    @GuardedBy("mLock")
    private boolean zzagp;
    
    public zzc(int param1Int, zzu<Void> param1zzu) {
      this.zzagl = param1Int;
      this.zzafh = param1zzu;
    }
    
    @GuardedBy("mLock")
    private final void zzdu() {
      if (this.zzagm + this.zzagn + this.zzago == this.zzagl) {
        if (this.zzagh != null) {
          zzu<Void> zzu1 = this.zzafh;
          int i = this.zzagn;
          int j = this.zzagl;
          StringBuilder stringBuilder = new StringBuilder(54);
          stringBuilder.append(i);
          stringBuilder.append(" out of ");
          stringBuilder.append(j);
          stringBuilder.append(" underlying tasks failed");
          zzu1.setException(new ExecutionException(stringBuilder.toString(), this.zzagh));
          return;
        } 
        if (this.zzagp) {
          this.zzafh.zzdp();
          return;
        } 
        this.zzafh.setResult(null);
      } 
    }
    
    public final void onCanceled() {
      synchronized (this.mLock) {
        this.zzago++;
        this.zzagp = true;
        zzdu();
        return;
      } 
    }
    
    public final void onFailure(@NonNull Exception param1Exception) {
      synchronized (this.mLock) {
        this.zzagn++;
        this.zzagh = param1Exception;
        zzdu();
        return;
      } 
    }
    
    public final void onSuccess(Object param1Object) {
      synchronized (this.mLock) {
        this.zzagm++;
        zzdu();
        return;
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\Tasks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */