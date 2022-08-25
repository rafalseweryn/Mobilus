package android.support.v7.recyclerview.extensions;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class AsyncListDiffer<T> {
  private static final Executor sMainThreadExecutor = new MainThreadExecutor();
  
  final AsyncDifferConfig<T> mConfig;
  
  @Nullable
  private List<T> mList;
  
  final Executor mMainThreadExecutor;
  
  int mMaxScheduledGeneration;
  
  @NonNull
  private List<T> mReadOnlyList = Collections.emptyList();
  
  private final ListUpdateCallback mUpdateCallback;
  
  public AsyncListDiffer(@NonNull ListUpdateCallback paramListUpdateCallback, @NonNull AsyncDifferConfig<T> paramAsyncDifferConfig) {
    this.mUpdateCallback = paramListUpdateCallback;
    this.mConfig = paramAsyncDifferConfig;
    if (paramAsyncDifferConfig.getMainThreadExecutor() != null) {
      this.mMainThreadExecutor = paramAsyncDifferConfig.getMainThreadExecutor();
    } else {
      this.mMainThreadExecutor = sMainThreadExecutor;
    } 
  }
  
  public AsyncListDiffer(@NonNull RecyclerView.Adapter paramAdapter, @NonNull DiffUtil.ItemCallback<T> paramItemCallback) {
    this((ListUpdateCallback)new AdapterListUpdateCallback(paramAdapter), (new AsyncDifferConfig.Builder<>(paramItemCallback)).build());
  }
  
  @NonNull
  public List<T> getCurrentList() {
    return this.mReadOnlyList;
  }
  
  void latchList(@NonNull List<T> paramList, @NonNull DiffUtil.DiffResult paramDiffResult) {
    this.mList = paramList;
    this.mReadOnlyList = Collections.unmodifiableList(paramList);
    paramDiffResult.dispatchUpdatesTo(this.mUpdateCallback);
  }
  
  public void submitList(@Nullable final List<T> newList) {
    final int runGeneration = this.mMaxScheduledGeneration + 1;
    this.mMaxScheduledGeneration = i;
    if (newList == this.mList)
      return; 
    if (newList == null) {
      i = this.mList.size();
      this.mList = null;
      this.mReadOnlyList = Collections.emptyList();
      this.mUpdateCallback.onRemoved(0, i);
      return;
    } 
    if (this.mList == null) {
      this.mList = newList;
      this.mReadOnlyList = Collections.unmodifiableList(newList);
      this.mUpdateCallback.onInserted(0, newList.size());
      return;
    } 
    final List<T> oldList = this.mList;
    this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() {
          public void run() {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                  public boolean areContentsTheSame(int param2Int1, int param2Int2) {
                    Object object1 = oldList.get(param2Int1);
                    Object object2 = newList.get(param2Int2);
                    if (object1 != null && object2 != null)
                      return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(object1, object2); 
                    if (object1 == null && object2 == null)
                      return true; 
                    throw new AssertionError();
                  }
                  
                  public boolean areItemsTheSame(int param2Int1, int param2Int2) {
                    boolean bool;
                    Object object1 = oldList.get(param2Int1);
                    Object object2 = newList.get(param2Int2);
                    if (object1 != null && object2 != null)
                      return AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(object1, object2); 
                    if (object1 == null && object2 == null) {
                      bool = true;
                    } else {
                      bool = false;
                    } 
                    return bool;
                  }
                  
                  @Nullable
                  public Object getChangePayload(int param2Int1, int param2Int2) {
                    Object object1 = oldList.get(param2Int1);
                    Object object2 = newList.get(param2Int2);
                    if (object1 != null && object2 != null)
                      return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(object1, object2); 
                    throw new AssertionError();
                  }
                  
                  public int getNewListSize() {
                    return newList.size();
                  }
                  
                  public int getOldListSize() {
                    return oldList.size();
                  }
                });
            AsyncListDiffer.this.mMainThreadExecutor.execute(new Runnable() {
                  public void run() {
                    if (AsyncListDiffer.this.mMaxScheduledGeneration == runGeneration)
                      AsyncListDiffer.this.latchList(newList, result); 
                  }
                });
          }
        });
  }
  
  private static class MainThreadExecutor implements Executor {
    final Handler mHandler = new Handler(Looper.getMainLooper());
    
    public void execute(@NonNull Runnable param1Runnable) {
      this.mHandler.post(param1Runnable);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\recyclerview\extensions\AsyncListDiffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */