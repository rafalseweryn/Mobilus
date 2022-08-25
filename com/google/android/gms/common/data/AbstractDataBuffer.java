package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
  protected final DataHolder mDataHolder;
  
  protected AbstractDataBuffer(DataHolder paramDataHolder) {
    this.mDataHolder = paramDataHolder;
  }
  
  @Deprecated
  public final void close() {
    release();
  }
  
  public abstract T get(int paramInt);
  
  public int getCount() {
    return (this.mDataHolder == null) ? 0 : this.mDataHolder.getCount();
  }
  
  public Bundle getMetadata() {
    return this.mDataHolder.getMetadata();
  }
  
  @Deprecated
  public boolean isClosed() {
    return (this.mDataHolder == null || this.mDataHolder.isClosed());
  }
  
  public Iterator<T> iterator() {
    return new DataBufferIterator<>(this);
  }
  
  public void release() {
    if (this.mDataHolder != null)
      this.mDataHolder.close(); 
  }
  
  public Iterator<T> singleRefIterator() {
    return new SingleRefDataBufferIterator<>(this);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\AbstractDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */