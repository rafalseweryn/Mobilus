package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzu<TResult> extends Task<TResult> {
  private final Object mLock = new Object();
  
  private final zzr<TResult> zzage = new zzr<>();
  
  @GuardedBy("mLock")
  private boolean zzagf;
  
  @GuardedBy("mLock")
  private TResult zzagg;
  
  @GuardedBy("mLock")
  private Exception zzagh;
  
  private volatile boolean zzfi;
  
  @GuardedBy("mLock")
  private final void zzdq() {
    Preconditions.checkState(this.zzagf, "Task is not yet complete");
  }
  
  @GuardedBy("mLock")
  private final void zzdr() {
    Preconditions.checkState(this.zzagf ^ true, "Task is already complete");
  }
  
  @GuardedBy("mLock")
  private final void zzds() {
    if (this.zzfi)
      throw new CancellationException("Task is already canceled."); 
  }
  
  private final void zzdt() {
    synchronized (this.mLock) {
      if (!this.zzagf)
        return; 
      this.zzage.zza(this);
      return;
    } 
  }
  
  @NonNull
  public final Task<TResult> addOnCanceledListener(@NonNull Activity paramActivity, @NonNull OnCanceledListener paramOnCanceledListener) {
    zzg<TResult> zzg = new zzg(TaskExecutors.MAIN_THREAD, paramOnCanceledListener);
    this.zzage.zza(zzg);
    zza.zze(paramActivity).zzb(zzg);
    zzdt();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnCanceledListener(@NonNull OnCanceledListener paramOnCanceledListener) {
    return super.addOnCanceledListener(TaskExecutors.MAIN_THREAD, paramOnCanceledListener);
  }
  
  @NonNull
  public final Task<TResult> addOnCanceledListener(@NonNull Executor paramExecutor, @NonNull OnCanceledListener paramOnCanceledListener) {
    this.zzage.zza(new zzg<>(paramExecutor, paramOnCanceledListener));
    zzdt();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull Activity paramActivity, @NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    zzi<TResult> zzi = new zzi<>(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
    this.zzage.zza(zzi);
    zza.zze(paramActivity).zzb(zzi);
    zzdt();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    return super.addOnCompleteListener(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    this.zzage.zza(new zzi<>(paramExecutor, paramOnCompleteListener));
    zzdt();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull Activity paramActivity, @NonNull OnFailureListener paramOnFailureListener) {
    zzk<TResult> zzk = new zzk(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
    this.zzage.zza(zzk);
    zza.zze(paramActivity).zzb(zzk);
    zzdt();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull OnFailureListener paramOnFailureListener) {
    return super.addOnFailureListener(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener) {
    this.zzage.zza(new zzk<>(paramExecutor, paramOnFailureListener));
    zzdt();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull Activity paramActivity, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    zzm<TResult> zzm = new zzm<>(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
    this.zzage.zza(zzm);
    zza.zze(paramActivity).zzb(zzm);
    zzdt();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    return super.addOnSuccessListener(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    this.zzage.zza(new zzm<>(paramExecutor, paramOnSuccessListener));
    zzdt();
    return this;
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> paramContinuation) {
    return super.continueWith(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation) {
    zzu<TContinuationResult> zzu1 = new zzu();
    this.zzage.zza(new zzc<>(paramExecutor, paramContinuation, zzu1));
    zzdt();
    return zzu1;
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation) {
    return super.continueWithTask(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation) {
    zzu<TContinuationResult> zzu1 = new zzu();
    this.zzage.zza(new zze<>(paramExecutor, paramContinuation, zzu1));
    zzdt();
    return zzu1;
  }
  
  @Nullable
  public final Exception getException() {
    synchronized (this.mLock) {
      return this.zzagh;
    } 
  }
  
  public final TResult getResult() {
    synchronized (this.mLock) {
      zzdq();
      zzds();
      if (this.zzagh != null) {
        RuntimeExecutionException runtimeExecutionException = new RuntimeExecutionException();
        this(this.zzagh);
        throw runtimeExecutionException;
      } 
      return this.zzagg;
    } 
  }
  
  public final <X extends Throwable> TResult getResult(@NonNull Class<X> paramClass) throws X {
    synchronized (this.mLock) {
      zzdq();
      zzds();
      if (paramClass.isInstance(this.zzagh))
        throw (X)paramClass.cast(this.zzagh); 
      if (this.zzagh != null) {
        RuntimeExecutionException runtimeExecutionException = new RuntimeExecutionException();
        this(this.zzagh);
        throw (X)runtimeExecutionException;
      } 
      return this.zzagg;
    } 
  }
  
  public final boolean isCanceled() {
    return this.zzfi;
  }
  
  public final boolean isComplete() {
    synchronized (this.mLock) {
      return this.zzagf;
    } 
  }
  
  public final boolean isSuccessful() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzagf && !this.zzfi && this.zzagh == null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull SuccessContinuation<TResult, TContinuationResult> paramSuccessContinuation) {
    return super.onSuccessTask(TaskExecutors.MAIN_THREAD, paramSuccessContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor paramExecutor, SuccessContinuation<TResult, TContinuationResult> paramSuccessContinuation) {
    zzu<TContinuationResult> zzu1 = new zzu();
    this.zzage.zza(new zzo<>(paramExecutor, paramSuccessContinuation, zzu1));
    zzdt();
    return zzu1;
  }
  
  public final void setException(@NonNull Exception paramException) {
    Preconditions.checkNotNull(paramException, "Exception must not be null");
    synchronized (this.mLock) {
      zzdr();
      this.zzagf = true;
      this.zzagh = paramException;
      this.zzage.zza(this);
      return;
    } 
  }
  
  public final void setResult(TResult paramTResult) {
    synchronized (this.mLock) {
      zzdr();
      this.zzagf = true;
      this.zzagg = paramTResult;
      this.zzage.zza(this);
      return;
    } 
  }
  
  public final boolean trySetException(@NonNull Exception paramException) {
    Preconditions.checkNotNull(paramException, "Exception must not be null");
    synchronized (this.mLock) {
      if (this.zzagf)
        return false; 
      this.zzagf = true;
      this.zzagh = paramException;
      this.zzage.zza(this);
      return true;
    } 
  }
  
  public final boolean trySetResult(TResult paramTResult) {
    synchronized (this.mLock) {
      if (this.zzagf)
        return false; 
      this.zzagf = true;
      this.zzagg = paramTResult;
      this.zzage.zza(this);
      return true;
    } 
  }
  
  public final boolean zzdp() {
    synchronized (this.mLock) {
      if (this.zzagf)
        return false; 
      this.zzagf = true;
      this.zzfi = true;
      this.zzage.zza(this);
      return true;
    } 
  }
  
  private static class zza extends LifecycleCallback {
    private final List<WeakReference<zzq<?>>> zzagi = new ArrayList<>();
    
    private zza(LifecycleFragment param1LifecycleFragment) {
      super(param1LifecycleFragment);
      this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
    }
    
    public static zza zze(Activity param1Activity) {
      LifecycleFragment lifecycleFragment = getFragment(param1Activity);
      zza zza2 = (zza)lifecycleFragment.getCallbackOrNull("TaskOnStopCallback", zza.class);
      zza zza1 = zza2;
      if (zza2 == null)
        zza1 = new zza(lifecycleFragment); 
      return zza1;
    }
    
    @MainThread
    public void onStop() {
      synchronized (this.zzagi) {
        Iterator<WeakReference<zzq<?>>> iterator = this.zzagi.iterator();
        while (iterator.hasNext()) {
          zzq zzq = ((WeakReference<zzq>)iterator.next()).get();
          if (zzq != null)
            zzq.cancel(); 
        } 
        this.zzagi.clear();
        return;
      } 
    }
    
    public final <T> void zzb(zzq<T> param1zzq) {
      synchronized (this.zzagi) {
        List<WeakReference<zzq<?>>> list = this.zzagi;
        WeakReference<zzq<?>> weakReference = new WeakReference();
        this((T)param1zzq);
        list.add(weakReference);
        return;
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */