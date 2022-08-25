package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public final class SortedDataBuffer<T> implements DataBuffer<T> {
  private final DataBuffer<T> zzok;
  
  private final Integer[] zzol;
  
  public SortedDataBuffer(DataBuffer<T> paramDataBuffer, Comparator<T> paramComparator) {
    this.zzok = paramDataBuffer;
    this.zzol = new Integer[paramDataBuffer.getCount()];
    for (byte b = 0; b < this.zzol.length; b++)
      this.zzol[b] = Integer.valueOf(b); 
    Arrays.sort(this.zzol, new zzb(this, paramComparator));
  }
  
  public final void close() {
    this.zzok.release();
  }
  
  public final T get(int paramInt) {
    return this.zzok.get(this.zzol[paramInt].intValue());
  }
  
  public final int getCount() {
    return this.zzol.length;
  }
  
  public final Bundle getMetadata() {
    return this.zzok.getMetadata();
  }
  
  public final boolean isClosed() {
    return this.zzok.isClosed();
  }
  
  public final Iterator<T> iterator() {
    return new DataBufferIterator<>(this);
  }
  
  public final void release() {
    this.zzok.release();
  }
  
  public final Iterator<T> singleRefIterator() {
    return iterator();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\SortedDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */