package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
  private T zzoj;
  
  public SingleRefDataBufferIterator(DataBuffer<T> paramDataBuffer) {
    super(paramDataBuffer);
  }
  
  public T next() {
    if (!hasNext()) {
      int i = this.mPosition;
      StringBuilder stringBuilder = new StringBuilder(46);
      stringBuilder.append("Cannot advance the iterator beyond ");
      stringBuilder.append(i);
      throw new NoSuchElementException(stringBuilder.toString());
    } 
    this.mPosition++;
    if (this.mPosition == 0) {
      this.zzoj = this.mDataBuffer.get(0);
      if (!(this.zzoj instanceof DataBufferRef)) {
        String str = String.valueOf(this.zzoj.getClass());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 44);
        stringBuilder.append("DataBuffer reference of type ");
        stringBuilder.append(str);
        stringBuilder.append(" is not movable");
        throw new IllegalStateException(stringBuilder.toString());
      } 
    } else {
      ((DataBufferRef)this.zzoj).setDataRow(this.mPosition);
    } 
    return this.zzoj;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\SingleRefDataBufferIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */