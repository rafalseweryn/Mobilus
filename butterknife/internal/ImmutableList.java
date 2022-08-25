package butterknife.internal;

import java.util.AbstractList;
import java.util.RandomAccess;

final class ImmutableList<T> extends AbstractList<T> implements RandomAccess {
  private final T[] views;
  
  ImmutableList(T[] paramArrayOfT) {
    this.views = paramArrayOfT;
  }
  
  public boolean contains(Object paramObject) {
    T[] arrayOfT = this.views;
    int i = arrayOfT.length;
    for (byte b = 0; b < i; b++) {
      if (arrayOfT[b] == paramObject)
        return true; 
    } 
    return false;
  }
  
  public T get(int paramInt) {
    return this.views[paramInt];
  }
  
  public int size() {
    return this.views.length;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\internal\ImmutableList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */