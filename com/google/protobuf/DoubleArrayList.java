package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class DoubleArrayList extends AbstractProtobufList<Double> implements Internal.DoubleList, RandomAccess, PrimitiveNonBoxingCollection {
  private static final DoubleArrayList EMPTY_LIST = new DoubleArrayList(new double[0], 0);
  
  private double[] array;
  
  private int size;
  
  static {
    EMPTY_LIST.makeImmutable();
  }
  
  DoubleArrayList() {
    this(new double[10], 0);
  }
  
  private DoubleArrayList(double[] paramArrayOfdouble, int paramInt) {
    this.array = paramArrayOfdouble;
    this.size = paramInt;
  }
  
  private void addDouble(int paramInt, double paramDouble) {
    ensureIsMutable();
    if (paramInt < 0 || paramInt > this.size)
      throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(paramInt)); 
    if (this.size < this.array.length) {
      System.arraycopy(this.array, paramInt, this.array, paramInt + 1, this.size - paramInt);
    } else {
      double[] arrayOfDouble = new double[this.size * 3 / 2 + 1];
      System.arraycopy(this.array, 0, arrayOfDouble, 0, paramInt);
      System.arraycopy(this.array, paramInt, arrayOfDouble, paramInt + 1, this.size - paramInt);
      this.array = arrayOfDouble;
    } 
    this.array[paramInt] = paramDouble;
    this.size++;
    this.modCount++;
  }
  
  public static DoubleArrayList emptyList() {
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
  
  public void add(int paramInt, Double paramDouble) {
    addDouble(paramInt, paramDouble.doubleValue());
  }
  
  public boolean addAll(Collection<? extends Double> paramCollection) {
    ensureIsMutable();
    Internal.checkNotNull(paramCollection);
    if (!(paramCollection instanceof DoubleArrayList))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    if (((DoubleArrayList)paramCollection).size == 0)
      return false; 
    if (Integer.MAX_VALUE - this.size < ((DoubleArrayList)paramCollection).size)
      throw new OutOfMemoryError(); 
    int i = this.size + ((DoubleArrayList)paramCollection).size;
    if (i > this.array.length)
      this.array = Arrays.copyOf(this.array, i); 
    System.arraycopy(((DoubleArrayList)paramCollection).array, 0, this.array, this.size, ((DoubleArrayList)paramCollection).size);
    this.size = i;
    this.modCount++;
    return true;
  }
  
  public void addDouble(double paramDouble) {
    addDouble(this.size, paramDouble);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof DoubleArrayList))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((DoubleArrayList)paramObject).size)
      return false; 
    paramObject = ((DoubleArrayList)paramObject).array;
    for (byte b = 0; b < this.size; b++) {
      if (Double.doubleToLongBits(this.array[b]) != Double.doubleToLongBits(paramObject[b]))
        return false; 
    } 
    return true;
  }
  
  public Double get(int paramInt) {
    return Double.valueOf(getDouble(paramInt));
  }
  
  public double getDouble(int paramInt) {
    ensureIndexInRange(paramInt);
    return this.array[paramInt];
  }
  
  public int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + Internal.hashLong(Double.doubleToLongBits(this.array[b])); 
    return i;
  }
  
  public Internal.DoubleList mutableCopyWithCapacity(int paramInt) {
    if (paramInt < this.size)
      throw new IllegalArgumentException(); 
    return new DoubleArrayList(Arrays.copyOf(this.array, paramInt), this.size);
  }
  
  public Double remove(int paramInt) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    double d = this.array[paramInt];
    if (paramInt < this.size - 1)
      System.arraycopy(this.array, paramInt + 1, this.array, paramInt, this.size - paramInt - 1); 
    this.size--;
    this.modCount++;
    return Double.valueOf(d);
  }
  
  public boolean remove(Object paramObject) {
    ensureIsMutable();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Double.valueOf(this.array[b]))) {
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
  
  public Double set(int paramInt, Double paramDouble) {
    return Double.valueOf(setDouble(paramInt, paramDouble.doubleValue()));
  }
  
  public double setDouble(int paramInt, double paramDouble) {
    ensureIsMutable();
    ensureIndexInRange(paramInt);
    double d = this.array[paramInt];
    this.array[paramInt] = paramDouble;
    return d;
  }
  
  public int size() {
    return this.size;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\DoubleArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */