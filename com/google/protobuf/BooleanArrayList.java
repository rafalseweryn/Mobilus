package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class BooleanArrayList extends AbstractProtobufList<Boolean> implements Internal.BooleanList, RandomAccess, PrimitiveNonBoxingCollection {
  private static final BooleanArrayList EMPTY_LIST = new BooleanArrayList(new boolean[0], 0);
  
  private boolean[] array;
  
  private int size;
  
  static {
    EMPTY_LIST.makeImmutable();
  }
  
  BooleanArrayList() {
    this(new boolean[10], 0);
  }
  
  private BooleanArrayList(boolean[] paramArrayOfboolean, int paramInt) {
    this.array = paramArrayOfboolean;
    this.size = paramInt;
  }
  
  private void addBoolean(int paramInt, boolean paramBoolean) {
    ensureIsMutable();
    if (paramInt < 0 || paramInt > this.size)
      throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(paramInt)); 
    if (this.size < this.array.length) {
      System.arraycopy(this.array, paramInt, this.array, paramInt + 1, this.size - paramInt);
    } else {
      boolean[] arrayOfBoolean = new boolean[this.size * 3 / 2 + 1];
      System.arraycopy(this.array, 0, arrayOfBoolean, 0, paramInt);
      System.arraycopy(this.array, paramInt, arrayOfBoolean, paramInt + 1, this.size - paramInt);
      this.array = arrayOfBoolean;
    } 
    this.array[paramInt] = paramBoolean;
    this.size++;
    this.modCount++;
  }
  
  public static BooleanArrayList emptyList() {
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
  
  public void add(int paramInt, Boolean paramBoolean) {
    addBoolean(paramInt, paramBoolean.booleanValue());
  }
  
  public boolean addAll(Collection<? extends Boolean> paramCollection) {
    ensureIsMutable();
    Internal.checkNotNull(paramCollection);
    if (!(paramCollection instanceof BooleanArrayList))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    if (((BooleanArrayList)paramCollection).size == 0)
      return false; 
    if (Integer.MAX_VALUE - this.size < ((BooleanArrayList)paramCollection).size)
      throw new OutOfMemoryError(); 
    int i = this.size + ((BooleanArrayList)paramCollection).size;
    if (i > this.array.length)
      this.array = Arrays.copyOf(this.array, i); 
    System.arraycopy(((BooleanArrayList)paramCollection).array, 0, this.array, this.size, ((BooleanArrayList)paramCollection).size);
    this.size = i;
    this.modCount++;
    return true;
  }
  
  public void addBoolean(boolean paramBoolean) {
    addBoolean(this.size, paramBoolean);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof BooleanArrayList))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((BooleanArrayList)paramObject).size)
      return false; 
    paramObject = ((BooleanArrayList)paramObject).array;
    for (byte b = 0; b < this.size; b++) {
      if (this.array[b] != paramObject[b])
        return false; 
    } 
    return true;
  }
  
  public Boolean get(int paramInt) {
    return Boolean.valueOf(getBoolean(paramInt));
  }
  
  public boolean getBoolean(int paramInt) {
    ensureIndexInRange(paramInt);
    return this.array[paramInt];
  }
  
  public int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + Internal.hashBoolean(this.array[b]); 
    return i;
  }
  
  public Internal.BooleanList mutableCopyWithCapacity(int paramInt) {
    if (paramInt < this.size)
      throw new IllegalArgumentException(); 
    return new BooleanArrayList(Arrays.copyOf(this.array, paramInt), this.size);
  }
  
  public Boolean remove(int paramInt) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    boolean bool = this.array[paramInt];
    if (paramInt < this.size - 1)
      System.arraycopy(this.array, paramInt + 1, this.array, paramInt, this.size - paramInt - 1); 
    this.size--;
    this.modCount++;
    return Boolean.valueOf(bool);
  }
  
  public boolean remove(Object paramObject) {
    ensureIsMutable();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Boolean.valueOf(this.array[b]))) {
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
  
  public Boolean set(int paramInt, Boolean paramBoolean) {
    return Boolean.valueOf(setBoolean(paramInt, paramBoolean.booleanValue()));
  }
  
  public boolean setBoolean(int paramInt, boolean paramBoolean) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    boolean bool = this.array[paramInt];
    this.array[paramInt] = paramBoolean;
    return bool;
  }
  
  public int size() {
    return this.size;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\BooleanArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */