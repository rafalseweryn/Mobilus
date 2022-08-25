package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class FloatArrayList extends AbstractProtobufList<Float> implements Internal.FloatList, RandomAccess, PrimitiveNonBoxingCollection {
  private static final FloatArrayList EMPTY_LIST = new FloatArrayList(new float[0], 0);
  
  private float[] array;
  
  private int size;
  
  static {
    EMPTY_LIST.makeImmutable();
  }
  
  FloatArrayList() {
    this(new float[10], 0);
  }
  
  private FloatArrayList(float[] paramArrayOffloat, int paramInt) {
    this.array = paramArrayOffloat;
    this.size = paramInt;
  }
  
  private void addFloat(int paramInt, float paramFloat) {
    ensureIsMutable();
    if (paramInt < 0 || paramInt > this.size)
      throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(paramInt)); 
    if (this.size < this.array.length) {
      System.arraycopy(this.array, paramInt, this.array, paramInt + 1, this.size - paramInt);
    } else {
      float[] arrayOfFloat = new float[this.size * 3 / 2 + 1];
      System.arraycopy(this.array, 0, arrayOfFloat, 0, paramInt);
      System.arraycopy(this.array, paramInt, arrayOfFloat, paramInt + 1, this.size - paramInt);
      this.array = arrayOfFloat;
    } 
    this.array[paramInt] = paramFloat;
    this.size++;
    this.modCount++;
  }
  
  public static FloatArrayList emptyList() {
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
  
  public void add(int paramInt, Float paramFloat) {
    addFloat(paramInt, paramFloat.floatValue());
  }
  
  public boolean addAll(Collection<? extends Float> paramCollection) {
    ensureIsMutable();
    Internal.checkNotNull(paramCollection);
    if (!(paramCollection instanceof FloatArrayList))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    if (((FloatArrayList)paramCollection).size == 0)
      return false; 
    if (Integer.MAX_VALUE - this.size < ((FloatArrayList)paramCollection).size)
      throw new OutOfMemoryError(); 
    int i = this.size + ((FloatArrayList)paramCollection).size;
    if (i > this.array.length)
      this.array = Arrays.copyOf(this.array, i); 
    System.arraycopy(((FloatArrayList)paramCollection).array, 0, this.array, this.size, ((FloatArrayList)paramCollection).size);
    this.size = i;
    this.modCount++;
    return true;
  }
  
  public void addFloat(float paramFloat) {
    addFloat(this.size, paramFloat);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof FloatArrayList))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((FloatArrayList)paramObject).size)
      return false; 
    paramObject = ((FloatArrayList)paramObject).array;
    for (byte b = 0; b < this.size; b++) {
      if (Float.floatToIntBits(this.array[b]) != Float.floatToIntBits(paramObject[b]))
        return false; 
    } 
    return true;
  }
  
  public Float get(int paramInt) {
    return Float.valueOf(getFloat(paramInt));
  }
  
  public float getFloat(int paramInt) {
    ensureIndexInRange(paramInt);
    return this.array[paramInt];
  }
  
  public int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + Float.floatToIntBits(this.array[b]); 
    return i;
  }
  
  public Internal.FloatList mutableCopyWithCapacity(int paramInt) {
    if (paramInt < this.size)
      throw new IllegalArgumentException(); 
    return new FloatArrayList(Arrays.copyOf(this.array, paramInt), this.size);
  }
  
  public Float remove(int paramInt) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    float f = this.array[paramInt];
    if (paramInt < this.size - 1)
      System.arraycopy(this.array, paramInt + 1, this.array, paramInt, this.size - paramInt - 1); 
    this.size--;
    this.modCount++;
    return Float.valueOf(f);
  }
  
  public boolean remove(Object paramObject) {
    ensureIsMutable();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Float.valueOf(this.array[b]))) {
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
  
  public Float set(int paramInt, Float paramFloat) {
    return Float.valueOf(setFloat(paramInt, paramFloat.floatValue()));
  }
  
  public float setFloat(int paramInt, float paramFloat) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    float f = this.array[paramInt];
    this.array[paramInt] = paramFloat;
    return f;
  }
  
  public int size() {
    return this.size;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\FloatArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */