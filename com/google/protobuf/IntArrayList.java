package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class IntArrayList extends AbstractProtobufList<Integer> implements Internal.IntList, RandomAccess, PrimitiveNonBoxingCollection {
  private static final IntArrayList EMPTY_LIST = new IntArrayList(new int[0], 0);
  
  private int[] array;
  
  private int size;
  
  static {
    EMPTY_LIST.makeImmutable();
  }
  
  IntArrayList() {
    this(new int[10], 0);
  }
  
  private IntArrayList(int[] paramArrayOfint, int paramInt) {
    this.array = paramArrayOfint;
    this.size = paramInt;
  }
  
  private void addInt(int paramInt1, int paramInt2) {
    ensureIsMutable();
    if (paramInt1 < 0 || paramInt1 > this.size)
      throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(paramInt1)); 
    if (this.size < this.array.length) {
      System.arraycopy(this.array, paramInt1, this.array, paramInt1 + 1, this.size - paramInt1);
    } else {
      int[] arrayOfInt = new int[this.size * 3 / 2 + 1];
      System.arraycopy(this.array, 0, arrayOfInt, 0, paramInt1);
      System.arraycopy(this.array, paramInt1, arrayOfInt, paramInt1 + 1, this.size - paramInt1);
      this.array = arrayOfInt;
    } 
    this.array[paramInt1] = paramInt2;
    this.size++;
    this.modCount++;
  }
  
  public static IntArrayList emptyList() {
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
  
  public void add(int paramInt, Integer paramInteger) {
    addInt(paramInt, paramInteger.intValue());
  }
  
  public boolean addAll(Collection<? extends Integer> paramCollection) {
    ensureIsMutable();
    Internal.checkNotNull(paramCollection);
    if (!(paramCollection instanceof IntArrayList))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    if (((IntArrayList)paramCollection).size == 0)
      return false; 
    if (Integer.MAX_VALUE - this.size < ((IntArrayList)paramCollection).size)
      throw new OutOfMemoryError(); 
    int i = this.size + ((IntArrayList)paramCollection).size;
    if (i > this.array.length)
      this.array = Arrays.copyOf(this.array, i); 
    System.arraycopy(((IntArrayList)paramCollection).array, 0, this.array, this.size, ((IntArrayList)paramCollection).size);
    this.size = i;
    this.modCount++;
    return true;
  }
  
  public void addInt(int paramInt) {
    addInt(this.size, paramInt);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof IntArrayList))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((IntArrayList)paramObject).size)
      return false; 
    paramObject = ((IntArrayList)paramObject).array;
    for (byte b = 0; b < this.size; b++) {
      if (this.array[b] != paramObject[b])
        return false; 
    } 
    return true;
  }
  
  public Integer get(int paramInt) {
    return Integer.valueOf(getInt(paramInt));
  }
  
  public int getInt(int paramInt) {
    ensureIndexInRange(paramInt);
    return this.array[paramInt];
  }
  
  public int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + this.array[b]; 
    return i;
  }
  
  public Internal.IntList mutableCopyWithCapacity(int paramInt) {
    if (paramInt < this.size)
      throw new IllegalArgumentException(); 
    return new IntArrayList(Arrays.copyOf(this.array, paramInt), this.size);
  }
  
  public Integer remove(int paramInt) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    int i = this.array[paramInt];
    if (paramInt < this.size - 1)
      System.arraycopy(this.array, paramInt + 1, this.array, paramInt, this.size - paramInt - 1); 
    this.size--;
    this.modCount++;
    return Integer.valueOf(i);
  }
  
  public boolean remove(Object paramObject) {
    ensureIsMutable();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Integer.valueOf(this.array[b]))) {
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
  
  public Integer set(int paramInt, Integer paramInteger) {
    return Integer.valueOf(setInt(paramInt, paramInteger.intValue()));
  }
  
  public int setInt(int paramInt1, int paramInt2) {
    ensureIsMutable();
    ensureIndexInRange(paramInt1);
    int i = this.array[paramInt1];
    this.array[paramInt1] = paramInt2;
    return i;
  }
  
  public int size() {
    return this.size;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\IntArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */