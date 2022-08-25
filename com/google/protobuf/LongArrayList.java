package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class LongArrayList extends AbstractProtobufList<Long> implements Internal.LongList, RandomAccess, PrimitiveNonBoxingCollection {
  private static final LongArrayList EMPTY_LIST = new LongArrayList(new long[0], 0);
  
  private long[] array;
  
  private int size;
  
  static {
    EMPTY_LIST.makeImmutable();
  }
  
  LongArrayList() {
    this(new long[10], 0);
  }
  
  private LongArrayList(long[] paramArrayOflong, int paramInt) {
    this.array = paramArrayOflong;
    this.size = paramInt;
  }
  
  private void addLong(int paramInt, long paramLong) {
    ensureIsMutable();
    if (paramInt < 0 || paramInt > this.size)
      throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(paramInt)); 
    if (this.size < this.array.length) {
      System.arraycopy(this.array, paramInt, this.array, paramInt + 1, this.size - paramInt);
    } else {
      long[] arrayOfLong = new long[this.size * 3 / 2 + 1];
      System.arraycopy(this.array, 0, arrayOfLong, 0, paramInt);
      System.arraycopy(this.array, paramInt, arrayOfLong, paramInt + 1, this.size - paramInt);
      this.array = arrayOfLong;
    } 
    this.array[paramInt] = paramLong;
    this.size++;
    this.modCount++;
  }
  
  public static LongArrayList emptyList() {
    return EMPTY_LIST;
  }
  
  private void ensureIndexInRange(int paramInt) {
    if (paramInt < 0 || paramInt >= this.size)
      throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(paramInt)); 
  }
  
  private String makeOutOfBoundsExceptionMessage(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Index:");
    stringBuilder.append(paramInt);
    stringBuilder.append(", Size:");
    stringBuilder.append(this.size);
    return stringBuilder.toString();
  }
  
  public void add(int paramInt, Long paramLong) {
    addLong(paramInt, paramLong.longValue());
  }
  
  public boolean addAll(Collection<? extends Long> paramCollection) {
    ensureIsMutable();
    Internal.checkNotNull(paramCollection);
    if (!(paramCollection instanceof LongArrayList))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    if (((LongArrayList)paramCollection).size == 0)
      return false; 
    if (Integer.MAX_VALUE - this.size < ((LongArrayList)paramCollection).size)
      throw new OutOfMemoryError(); 
    int i = this.size + ((LongArrayList)paramCollection).size;
    if (i > this.array.length)
      this.array = Arrays.copyOf(this.array, i); 
    System.arraycopy(((LongArrayList)paramCollection).array, 0, this.array, this.size, ((LongArrayList)paramCollection).size);
    this.size = i;
    this.modCount++;
    return true;
  }
  
  public void addLong(long paramLong) {
    addLong(this.size, paramLong);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LongArrayList))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((LongArrayList)paramObject).size)
      return false; 
    paramObject = ((LongArrayList)paramObject).array;
    for (byte b = 0; b < this.size; b++) {
      if (this.array[b] != paramObject[b])
        return false; 
    } 
    return true;
  }
  
  public Long get(int paramInt) {
    return Long.valueOf(getLong(paramInt));
  }
  
  public long getLong(int paramInt) {
    ensureIndexInRange(paramInt);
    return this.array[paramInt];
  }
  
  public int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + Internal.hashLong(this.array[b]); 
    return i;
  }
  
  public Internal.LongList mutableCopyWithCapacity(int paramInt) {
    if (paramInt < this.size)
      throw new IllegalArgumentException(); 
    return new LongArrayList(Arrays.copyOf(this.array, paramInt), this.size);
  }
  
  public Long remove(int paramInt) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    long l = this.array[paramInt];
    if (paramInt < this.size - 1)
      System.arraycopy(this.array, paramInt + 1, this.array, paramInt, this.size - paramInt - 1); 
    this.size--;
    this.modCount++;
    return Long.valueOf(l);
  }
  
  public boolean remove(Object paramObject) {
    ensureIsMutable();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Long.valueOf(this.array[b]))) {
        System.arraycopy(this.array, b + 1, this.array, b, this.size - b - 1);
        this.size--;
        this.modCount++;
        return true;
      } 
    } 
    return false;
  }
  
  protected void removeRange(int paramInt1, int paramInt2) {
    ensureIsMutable();
    if (paramInt2 < paramInt1)
      throw new IndexOutOfBoundsException("toIndex < fromIndex"); 
    System.arraycopy(this.array, paramInt2, this.array, paramInt1, this.size - paramInt2);
    this.size -= paramInt2 - paramInt1;
    this.modCount++;
  }
  
  public Long set(int paramInt, Long paramLong) {
    return Long.valueOf(setLong(paramInt, paramLong.longValue()));
  }
  
  public long setLong(int paramInt, long paramLong) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    long l = this.array[paramInt];
    this.array[paramInt] = paramLong;
    return l;
  }
  
  public int size() {
    return this.size;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\LongArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */