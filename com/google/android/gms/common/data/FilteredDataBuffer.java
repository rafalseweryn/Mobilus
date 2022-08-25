package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.internal.Asserts;
import java.util.Iterator;

public abstract class FilteredDataBuffer<T> implements DataBuffer<T> {
  protected final DataBuffer<T> mDataBuffer;
  
  public FilteredDataBuffer(DataBuffer<T> paramDataBuffer) {
    boolean bool;
    Asserts.checkNotNull(paramDataBuffer);
    if (!(paramDataBuffer instanceof FilteredDataBuffer)) {
      bool = true;
    } else {
      bool = false;
    } 
    Asserts.checkState(bool, "Not possible to have nested FilteredDataBuffers.");
    this.mDataBuffer = paramDataBuffer;
  }
  
  public void close() {
    release();
  }
  
  protected abstract int computeRealPosition(int paramInt);
  
  public T get(int paramInt) {
    return this.mDataBuffer.get(computeRealPosition(paramInt));
  }
  
  public Bundle getMetadata() {
    return this.mDataBuffer.getMetadata();
  }
  
  @Deprecated
  public boolean isClosed() {
    return this.mDataBuffer.isClosed();
  }
  
  public Iterator<T> iterator() {
    return new DataBufferIterator<>(this);
  }
  
  public void release() {
    this.mDataBuffer.release();
  }
  
  public Iterator<T> singleRefIterator() {
    return iterator();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\FilteredDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */