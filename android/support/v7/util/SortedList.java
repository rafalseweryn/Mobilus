package android.support.v7.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList<T> {
  private static final int CAPACITY_GROWTH = 10;
  
  private static final int DELETION = 2;
  
  private static final int INSERTION = 1;
  
  public static final int INVALID_POSITION = -1;
  
  private static final int LOOKUP = 4;
  
  private static final int MIN_CAPACITY = 10;
  
  private BatchedCallback mBatchedCallback;
  
  private Callback mCallback;
  
  T[] mData;
  
  private int mNewDataStart;
  
  private T[] mOldData;
  
  private int mOldDataSize;
  
  private int mOldDataStart;
  
  private int mSize;
  
  private final Class<T> mTClass;
  
  public SortedList(@NonNull Class<T> paramClass, @NonNull Callback<T> paramCallback) {
    this(paramClass, paramCallback, 10);
  }
  
  public SortedList(@NonNull Class<T> paramClass, @NonNull Callback<T> paramCallback, int paramInt) {
    this.mTClass = paramClass;
    this.mData = (T[])Array.newInstance(paramClass, paramInt);
    this.mCallback = paramCallback;
    this.mSize = 0;
  }
  
  private int add(T paramT, boolean paramBoolean) {
    int j;
    int i = findIndexOf(paramT, this.mData, 0, this.mSize, 1);
    if (i == -1) {
      j = 0;
    } else {
      j = i;
      if (i < this.mSize) {
        T t = this.mData[i];
        j = i;
        if (this.mCallback.areItemsTheSame(t, paramT)) {
          if (this.mCallback.areContentsTheSame(t, paramT)) {
            this.mData[i] = paramT;
            return i;
          } 
          this.mData[i] = paramT;
          this.mCallback.onChanged(i, 1, this.mCallback.getChangePayload(t, paramT));
          return i;
        } 
      } 
    } 
    addToData(j, paramT);
    if (paramBoolean)
      this.mCallback.onInserted(j, 1); 
    return j;
  }
  
  private void addAllInternal(T[] paramArrayOfT) {
    if (paramArrayOfT.length < 1)
      return; 
    int i = sortAndDedup(paramArrayOfT);
    if (this.mSize == 0) {
      this.mData = paramArrayOfT;
      this.mSize = i;
      this.mCallback.onInserted(0, i);
    } else {
      merge(paramArrayOfT, i);
    } 
  }
  
  private void addToData(int paramInt, T paramT) {
    StringBuilder stringBuilder;
    if (paramInt > this.mSize) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("cannot add item to ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" because size is ");
      stringBuilder.append(this.mSize);
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    } 
    if (this.mSize == this.mData.length) {
      Object[] arrayOfObject = (Object[])Array.newInstance(this.mTClass, this.mData.length + 10);
      System.arraycopy(this.mData, 0, arrayOfObject, 0, paramInt);
      arrayOfObject[paramInt] = stringBuilder;
      System.arraycopy(this.mData, paramInt, arrayOfObject, paramInt + 1, this.mSize - paramInt);
      this.mData = (T[])arrayOfObject;
    } else {
      System.arraycopy(this.mData, paramInt, this.mData, paramInt + 1, this.mSize - paramInt);
      this.mData[paramInt] = (T)stringBuilder;
    } 
    this.mSize++;
  }
  
  private T[] copyArray(T[] paramArrayOfT) {
    Object[] arrayOfObject = (Object[])Array.newInstance(this.mTClass, paramArrayOfT.length);
    System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, paramArrayOfT.length);
    return (T[])arrayOfObject;
  }
  
  private int findIndexOf(T paramT, T[] paramArrayOfT, int paramInt1, int paramInt2, int paramInt3) {
    while (paramInt1 < paramInt2) {
      int i = (paramInt1 + paramInt2) / 2;
      T t = paramArrayOfT[i];
      int j = this.mCallback.compare(t, paramT);
      if (j < 0) {
        paramInt1 = i + 1;
        continue;
      } 
      if (j == 0) {
        if (this.mCallback.areItemsTheSame(t, paramT))
          return i; 
        paramInt2 = linearEqualitySearch(paramT, i, paramInt1, paramInt2);
        if (paramInt3 == 1) {
          paramInt1 = paramInt2;
          if (paramInt2 == -1)
            paramInt1 = i; 
          return paramInt1;
        } 
        return paramInt2;
      } 
      paramInt2 = i;
    } 
    if (paramInt3 != 1)
      paramInt1 = -1; 
    return paramInt1;
  }
  
  private int findSameItem(T paramT, T[] paramArrayOfT, int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      if (this.mCallback.areItemsTheSame(paramArrayOfT[paramInt1], paramT))
        return paramInt1; 
      paramInt1++;
    } 
    return -1;
  }
  
  private int linearEqualitySearch(T paramT, int paramInt1, int paramInt2, int paramInt3) {
    int j;
    int i = paramInt1 - 1;
    while (true) {
      j = paramInt1;
      if (i >= paramInt2) {
        T t = this.mData[i];
        if (this.mCallback.compare(t, paramT) != 0) {
          j = paramInt1;
          break;
        } 
        if (this.mCallback.areItemsTheSame(t, paramT))
          return i; 
        i--;
        continue;
      } 
      break;
    } 
    while (true) {
      paramInt1 = j + 1;
      if (paramInt1 < paramInt3) {
        T t = this.mData[paramInt1];
        if (this.mCallback.compare(t, paramT) != 0)
          break; 
        j = paramInt1;
        if (this.mCallback.areItemsTheSame(t, paramT))
          return paramInt1; 
        continue;
      } 
      break;
    } 
    return -1;
  }
  
  private void merge(T[] paramArrayOfT, int paramInt) {
    boolean bool1;
    boolean bool = this.mCallback instanceof BatchedCallback;
    int i = 0;
    if (!bool) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (bool1)
      beginBatchedUpdates(); 
    this.mOldData = this.mData;
    this.mOldDataStart = 0;
    this.mOldDataSize = this.mSize;
    int j = this.mSize;
    this.mData = (T[])Array.newInstance(this.mTClass, j + paramInt + 10);
    this.mNewDataStart = 0;
    while (true) {
      if (this.mOldDataStart < this.mOldDataSize || i < paramInt)
        if (this.mOldDataStart == this.mOldDataSize) {
          paramInt -= i;
          System.arraycopy(paramArrayOfT, i, this.mData, this.mNewDataStart, paramInt);
          this.mNewDataStart += paramInt;
          this.mSize += paramInt;
          this.mCallback.onInserted(this.mNewDataStart - paramInt, paramInt);
        } else if (i == paramInt) {
          paramInt = this.mOldDataSize - this.mOldDataStart;
          System.arraycopy(this.mOldData, this.mOldDataStart, this.mData, this.mNewDataStart, paramInt);
          this.mNewDataStart += paramInt;
        } else {
          T[] arrayOfT1;
          T t1 = this.mOldData[this.mOldDataStart];
          T t2 = paramArrayOfT[i];
          j = this.mCallback.compare(t1, t2);
          if (j > 0) {
            arrayOfT1 = this.mData;
            j = this.mNewDataStart;
            this.mNewDataStart = j + 1;
            arrayOfT1[j] = t2;
            this.mSize++;
            i++;
            this.mCallback.onInserted(this.mNewDataStart - 1, 1);
            continue;
          } 
          if (j == 0 && this.mCallback.areItemsTheSame(arrayOfT1, (T[])t2)) {
            T[] arrayOfT = this.mData;
            j = this.mNewDataStart;
            this.mNewDataStart = j + 1;
            arrayOfT[j] = t2;
            j = i + 1;
            this.mOldDataStart++;
            i = j;
            if (!this.mCallback.areContentsTheSame(arrayOfT1, (T[])t2)) {
              this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(arrayOfT1, (T[])t2));
              i = j;
            } 
            continue;
          } 
          T[] arrayOfT2 = this.mData;
          j = this.mNewDataStart;
          this.mNewDataStart = j + 1;
          arrayOfT2[j] = (T)arrayOfT1;
          this.mOldDataStart++;
          continue;
        }  
      this.mOldData = null;
      if (bool1)
        endBatchedUpdates(); 
      return;
    } 
  }
  
  private boolean remove(T paramT, boolean paramBoolean) {
    int i = findIndexOf(paramT, this.mData, 0, this.mSize, 2);
    if (i == -1)
      return false; 
    removeItemAtIndex(i, paramBoolean);
    return true;
  }
  
  private void removeItemAtIndex(int paramInt, boolean paramBoolean) {
    System.arraycopy(this.mData, paramInt + 1, this.mData, paramInt, this.mSize - paramInt - 1);
    this.mSize--;
    this.mData[this.mSize] = null;
    if (paramBoolean)
      this.mCallback.onRemoved(paramInt, 1); 
  }
  
  private void replaceAllInsert(T paramT) {
    this.mData[this.mNewDataStart] = paramT;
    this.mNewDataStart++;
    this.mSize++;
    this.mCallback.onInserted(this.mNewDataStart - 1, 1);
  }
  
  private void replaceAllInternal(@NonNull T[] paramArrayOfT) {
    boolean bool;
    if (!(this.mCallback instanceof BatchedCallback)) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      beginBatchedUpdates(); 
    this.mOldDataStart = 0;
    this.mOldDataSize = this.mSize;
    this.mOldData = this.mData;
    this.mNewDataStart = 0;
    int i = sortAndDedup(paramArrayOfT);
    this.mData = (T[])Array.newInstance(this.mTClass, i);
    while (true) {
      if (this.mNewDataStart < i || this.mOldDataStart < this.mOldDataSize)
        if (this.mOldDataStart >= this.mOldDataSize) {
          int j = this.mNewDataStart;
          i -= this.mNewDataStart;
          System.arraycopy(paramArrayOfT, j, this.mData, j, i);
          this.mNewDataStart += i;
          this.mSize += i;
          this.mCallback.onInserted(j, i);
        } else if (this.mNewDataStart >= i) {
          int j = this.mOldDataSize - this.mOldDataStart;
          this.mSize -= j;
          this.mCallback.onRemoved(this.mNewDataStart, j);
        } else {
          T t1 = this.mOldData[this.mOldDataStart];
          T t2 = paramArrayOfT[this.mNewDataStart];
          int j = this.mCallback.compare(t1, t2);
          if (j < 0) {
            replaceAllRemove();
            continue;
          } 
          if (j > 0) {
            replaceAllInsert(t2);
            continue;
          } 
          if (!this.mCallback.areItemsTheSame(t1, t2)) {
            replaceAllRemove();
            replaceAllInsert(t2);
            continue;
          } 
          this.mData[this.mNewDataStart] = t2;
          this.mOldDataStart++;
          this.mNewDataStart++;
          if (!this.mCallback.areContentsTheSame(t1, t2))
            this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(t1, t2)); 
          continue;
        }  
      this.mOldData = null;
      if (bool)
        endBatchedUpdates(); 
      return;
    } 
  }
  
  private void replaceAllRemove() {
    this.mSize--;
    this.mOldDataStart++;
    this.mCallback.onRemoved(this.mNewDataStart, 1);
  }
  
  private int sortAndDedup(@NonNull T[] paramArrayOfT) {
    int i = paramArrayOfT.length;
    int j = 0;
    if (i == 0)
      return 0; 
    Arrays.sort(paramArrayOfT, this.mCallback);
    byte b = 1;
    i = 1;
    while (b < paramArrayOfT.length) {
      T t = paramArrayOfT[b];
      if (this.mCallback.compare(paramArrayOfT[j], t) == 0) {
        int k = findSameItem(t, paramArrayOfT, j, i);
        if (k != -1) {
          paramArrayOfT[k] = t;
        } else {
          if (i != b)
            paramArrayOfT[i] = t; 
          i++;
        } 
      } else {
        if (i != b)
          paramArrayOfT[i] = t; 
        int k = i + 1;
        j = i;
        i = k;
      } 
      b++;
    } 
    return i;
  }
  
  private void throwIfInMutationOperation() {
    if (this.mOldData != null)
      throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll."); 
  }
  
  public int add(T paramT) {
    throwIfInMutationOperation();
    return add(paramT, true);
  }
  
  public void addAll(@NonNull Collection<T> paramCollection) {
    addAll(paramCollection.toArray((T[])Array.newInstance(this.mTClass, paramCollection.size())), true);
  }
  
  public void addAll(@NonNull T... paramVarArgs) {
    addAll(paramVarArgs, false);
  }
  
  public void addAll(@NonNull T[] paramArrayOfT, boolean paramBoolean) {
    throwIfInMutationOperation();
    if (paramArrayOfT.length == 0)
      return; 
    if (paramBoolean) {
      addAllInternal(paramArrayOfT);
    } else {
      addAllInternal(copyArray(paramArrayOfT));
    } 
  }
  
  public void beginBatchedUpdates() {
    throwIfInMutationOperation();
    if (this.mCallback instanceof BatchedCallback)
      return; 
    if (this.mBatchedCallback == null)
      this.mBatchedCallback = new BatchedCallback(this.mCallback); 
    this.mCallback = this.mBatchedCallback;
  }
  
  public void clear() {
    throwIfInMutationOperation();
    if (this.mSize == 0)
      return; 
    int i = this.mSize;
    Arrays.fill((Object[])this.mData, 0, i, (Object)null);
    this.mSize = 0;
    this.mCallback.onRemoved(0, i);
  }
  
  public void endBatchedUpdates() {
    throwIfInMutationOperation();
    if (this.mCallback instanceof BatchedCallback)
      ((BatchedCallback)this.mCallback).dispatchLastEvent(); 
    if (this.mCallback == this.mBatchedCallback)
      this.mCallback = this.mBatchedCallback.mWrappedCallback; 
  }
  
  public T get(int paramInt) throws IndexOutOfBoundsException {
    if (paramInt >= this.mSize || paramInt < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Asked to get item at ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" but size is ");
      stringBuilder.append(this.mSize);
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    } 
    return (this.mOldData != null && paramInt >= this.mNewDataStart) ? this.mOldData[paramInt - this.mNewDataStart + this.mOldDataStart] : this.mData[paramInt];
  }
  
  public int indexOf(T paramT) {
    if (this.mOldData != null) {
      int i = findIndexOf(paramT, this.mData, 0, this.mNewDataStart, 4);
      if (i != -1)
        return i; 
      i = findIndexOf(paramT, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
      return (i != -1) ? (i - this.mOldDataStart + this.mNewDataStart) : -1;
    } 
    return findIndexOf(paramT, this.mData, 0, this.mSize, 4);
  }
  
  public void recalculatePositionOfItemAt(int paramInt) {
    throwIfInMutationOperation();
    T t = get(paramInt);
    removeItemAtIndex(paramInt, false);
    int i = add(t, false);
    if (paramInt != i)
      this.mCallback.onMoved(paramInt, i); 
  }
  
  public boolean remove(T paramT) {
    throwIfInMutationOperation();
    return remove(paramT, true);
  }
  
  public T removeItemAt(int paramInt) {
    throwIfInMutationOperation();
    T t = get(paramInt);
    removeItemAtIndex(paramInt, true);
    return t;
  }
  
  public void replaceAll(@NonNull Collection<T> paramCollection) {
    replaceAll(paramCollection.toArray((T[])Array.newInstance(this.mTClass, paramCollection.size())), true);
  }
  
  public void replaceAll(@NonNull T... paramVarArgs) {
    replaceAll(paramVarArgs, false);
  }
  
  public void replaceAll(@NonNull T[] paramArrayOfT, boolean paramBoolean) {
    throwIfInMutationOperation();
    if (paramBoolean) {
      replaceAllInternal(paramArrayOfT);
    } else {
      replaceAllInternal(copyArray(paramArrayOfT));
    } 
  }
  
  public int size() {
    return this.mSize;
  }
  
  public void updateItemAt(int paramInt, T paramT) {
    throwIfInMutationOperation();
    T t = get(paramInt);
    if (t == paramT || !this.mCallback.areContentsTheSame(t, paramT)) {
      i = 1;
    } else {
      i = 0;
    } 
    if (t != paramT && this.mCallback.compare(t, paramT) == 0) {
      this.mData[paramInt] = paramT;
      if (i)
        this.mCallback.onChanged(paramInt, 1, this.mCallback.getChangePayload(t, paramT)); 
      return;
    } 
    if (i)
      this.mCallback.onChanged(paramInt, 1, this.mCallback.getChangePayload(t, paramT)); 
    removeItemAtIndex(paramInt, false);
    int i = add(paramT, false);
    if (paramInt != i)
      this.mCallback.onMoved(paramInt, i); 
  }
  
  public static class BatchedCallback<T2> extends Callback<T2> {
    private final BatchingListUpdateCallback mBatchingListUpdateCallback;
    
    final SortedList.Callback<T2> mWrappedCallback;
    
    public BatchedCallback(SortedList.Callback<T2> param1Callback) {
      this.mWrappedCallback = param1Callback;
      this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(this.mWrappedCallback);
    }
    
    public boolean areContentsTheSame(T2 param1T21, T2 param1T22) {
      return this.mWrappedCallback.areContentsTheSame(param1T21, param1T22);
    }
    
    public boolean areItemsTheSame(T2 param1T21, T2 param1T22) {
      return this.mWrappedCallback.areItemsTheSame(param1T21, param1T22);
    }
    
    public int compare(T2 param1T21, T2 param1T22) {
      return this.mWrappedCallback.compare(param1T21, param1T22);
    }
    
    public void dispatchLastEvent() {
      this.mBatchingListUpdateCallback.dispatchLastEvent();
    }
    
    @Nullable
    public Object getChangePayload(T2 param1T21, T2 param1T22) {
      return this.mWrappedCallback.getChangePayload(param1T21, param1T22);
    }
    
    public void onChanged(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onChanged(param1Int1, param1Int2, null);
    }
    
    public void onChanged(int param1Int1, int param1Int2, Object param1Object) {
      this.mBatchingListUpdateCallback.onChanged(param1Int1, param1Int2, param1Object);
    }
    
    public void onInserted(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onInserted(param1Int1, param1Int2);
    }
    
    public void onMoved(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onMoved(param1Int1, param1Int2);
    }
    
    public void onRemoved(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onRemoved(param1Int1, param1Int2);
    }
  }
  
  public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
    public abstract boolean areContentsTheSame(T2 param1T21, T2 param1T22);
    
    public abstract boolean areItemsTheSame(T2 param1T21, T2 param1T22);
    
    public abstract int compare(T2 param1T21, T2 param1T22);
    
    @Nullable
    public Object getChangePayload(T2 param1T21, T2 param1T22) {
      return null;
    }
    
    public abstract void onChanged(int param1Int1, int param1Int2);
    
    public void onChanged(int param1Int1, int param1Int2, Object param1Object) {
      onChanged(param1Int1, param1Int2);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v\\util\SortedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */