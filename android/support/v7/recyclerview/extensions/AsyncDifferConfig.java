package android.support.v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v7.util.DiffUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class AsyncDifferConfig<T> {
  @NonNull
  private final Executor mBackgroundThreadExecutor;
  
  @NonNull
  private final DiffUtil.ItemCallback<T> mDiffCallback;
  
  @NonNull
  private final Executor mMainThreadExecutor;
  
  AsyncDifferConfig(@NonNull Executor paramExecutor1, @NonNull Executor paramExecutor2, @NonNull DiffUtil.ItemCallback<T> paramItemCallback) {
    this.mMainThreadExecutor = paramExecutor1;
    this.mBackgroundThreadExecutor = paramExecutor2;
    this.mDiffCallback = paramItemCallback;
  }
  
  @NonNull
  public Executor getBackgroundThreadExecutor() {
    return this.mBackgroundThreadExecutor;
  }
  
  @NonNull
  public DiffUtil.ItemCallback<T> getDiffCallback() {
    return this.mDiffCallback;
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public Executor getMainThreadExecutor() {
    return this.mMainThreadExecutor;
  }
  
  public static final class Builder<T> {
    private static Executor sDiffExecutor;
    
    private static final Object sExecutorLock = new Object();
    
    private Executor mBackgroundThreadExecutor;
    
    private final DiffUtil.ItemCallback<T> mDiffCallback;
    
    private Executor mMainThreadExecutor;
    
    public Builder(@NonNull DiffUtil.ItemCallback<T> param1ItemCallback) {
      this.mDiffCallback = param1ItemCallback;
    }
    
    @NonNull
    public AsyncDifferConfig<T> build() {
      if (this.mBackgroundThreadExecutor == null)
        synchronized (sExecutorLock) {
          if (sDiffExecutor == null)
            sDiffExecutor = Executors.newFixedThreadPool(2); 
          this.mBackgroundThreadExecutor = sDiffExecutor;
        }  
      return new AsyncDifferConfig<>(this.mMainThreadExecutor, this.mBackgroundThreadExecutor, this.mDiffCallback);
    }
    
    @NonNull
    public Builder<T> setBackgroundThreadExecutor(Executor param1Executor) {
      this.mBackgroundThreadExecutor = param1Executor;
      return this;
    }
    
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Builder<T> setMainThreadExecutor(Executor param1Executor) {
      this.mMainThreadExecutor = param1Executor;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\recyclerview\extensions\AsyncDifferConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */