package android.arch.lifecycle;

import android.arch.core.internal.SafeIterableMap;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;

public class MediatorLiveData<T> extends MutableLiveData<T> {
  private SafeIterableMap<LiveData<?>, Source<?>> mSources = new SafeIterableMap();
  
  @MainThread
  public <S> void addSource(@NonNull LiveData<S> paramLiveData, @NonNull Observer<S> paramObserver) {
    Source<S> source1 = new Source<>(paramLiveData, paramObserver);
    Source source = (Source)this.mSources.putIfAbsent(paramLiveData, source1);
    if (source != null && source.mObserver != paramObserver)
      throw new IllegalArgumentException("This source was already added with the different observer"); 
    if (source != null)
      return; 
    if (hasActiveObservers())
      source1.plug(); 
  }
  
  @CallSuper
  protected void onActive() {
    Iterator<Map.Entry> iterator = this.mSources.iterator();
    while (iterator.hasNext())
      ((Source)((Map.Entry)iterator.next()).getValue()).plug(); 
  }
  
  @CallSuper
  protected void onInactive() {
    Iterator<Map.Entry> iterator = this.mSources.iterator();
    while (iterator.hasNext())
      ((Source)((Map.Entry)iterator.next()).getValue()).unplug(); 
  }
  
  @MainThread
  public <S> void removeSource(@NonNull LiveData<S> paramLiveData) {
    Source source = (Source)this.mSources.remove(paramLiveData);
    if (source != null)
      source.unplug(); 
  }
  
  private static class Source<V> implements Observer<V> {
    final LiveData<V> mLiveData;
    
    final Observer<V> mObserver;
    
    int mVersion = -1;
    
    Source(LiveData<V> param1LiveData, Observer<V> param1Observer) {
      this.mLiveData = param1LiveData;
      this.mObserver = param1Observer;
    }
    
    public void onChanged(@Nullable V param1V) {
      if (this.mVersion != this.mLiveData.getVersion()) {
        this.mVersion = this.mLiveData.getVersion();
        this.mObserver.onChanged(param1V);
      } 
    }
    
    void plug() {
      this.mLiveData.observeForever(this);
    }
    
    void unplug() {
      this.mLiveData.removeObserver(this);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\arch\lifecycle\MediatorLiveData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */