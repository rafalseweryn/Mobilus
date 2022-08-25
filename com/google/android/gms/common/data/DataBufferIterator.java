package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataBufferIterator<T> implements Iterator<T> {
  protected final DataBuffer<T> mDataBuffer;
  
  protected int mPosition;
  
  public DataBufferIterator(DataBuffer<T> paramDataBuffer) {
    this.mDataBuffer = (DataBuffer<T>)Preconditions.checkNotNull(paramDataBuffer);
    this.mPosition = -1;
  }
  
  public boolean hasNext() {
    return (this.mPosition < this.mDataBuffer.getCount() - 1);
  }
  
  public T next() {
    if (!hasNext()) {
      int j = this.mPosition;
      StringBuilder stringBuilder = new StringBuilder(46);
      stringBuilder.append("Cannot advance the iterator beyond ");
      stringBuilder.append(j);
      throw new NoSuchElementException(stringBuilder.toString());
    } 
    DataBuffer<T> dataBuffer = this.mDataBuffer;
    int i = this.mPosition + 1;
    this.mPosition = i;
    return dataBuffer.get(i);
  }
  
  public void remove() {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\DataBufferIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */