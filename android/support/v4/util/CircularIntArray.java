package android.support.v4.util;

public final class CircularIntArray {
  private int mCapacityBitmask;
  
  private int[] mElements;
  
  private int mHead;
  
  private int mTail;
  
  public CircularIntArray() {
    this(8);
  }
  
  public CircularIntArray(int paramInt) {
    if (paramInt < 1)
      throw new IllegalArgumentException("capacity must be >= 1"); 
    if (paramInt > 1073741824)
      throw new IllegalArgumentException("capacity must be <= 2^30"); 
    int i = paramInt;
    if (Integer.bitCount(paramInt) != 1)
      i = Integer.highestOneBit(paramInt - 1) << 1; 
    this.mCapacityBitmask = i - 1;
    this.mElements = new int[i];
  }
  
  private void doubleCapacity() {
    int i = this.mElements.length;
    int j = i - this.mHead;
    int k = i << 1;
    if (k < 0)
      throw new RuntimeException("Max array capacity exceeded"); 
    int[] arrayOfInt = new int[k];
    System.arraycopy(this.mElements, this.mHead, arrayOfInt, 0, j);
    System.arraycopy(this.mElements, 0, arrayOfInt, j, this.mHead);
    this.mElements = arrayOfInt;
    this.mHead = 0;
    this.mTail = i;
    this.mCapacityBitmask = k - 1;
  }
  
  public void addFirst(int paramInt) {
    this.mHead = this.mHead - 1 & this.mCapacityBitmask;
    this.mElements[this.mHead] = paramInt;
    if (this.mHead == this.mTail)
      doubleCapacity(); 
  }
  
  public void addLast(int paramInt) {
    this.mElements[this.mTail] = paramInt;
    this.mTail = this.mTail + 1 & this.mCapacityBitmask;
    if (this.mTail == this.mHead)
      doubleCapacity(); 
  }
  
  public void clear() {
    this.mTail = this.mHead;
  }
  
  public int get(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new ArrayIndexOutOfBoundsException(); 
    int[] arrayOfInt = this.mElements;
    int i = this.mHead;
    return arrayOfInt[this.mCapacityBitmask & i + paramInt];
  }
  
  public int getFirst() {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException(); 
    return this.mElements[this.mHead];
  }
  
  public int getLast() {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException(); 
    int[] arrayOfInt = this.mElements;
    int i = this.mTail;
    return arrayOfInt[this.mCapacityBitmask & i - 1];
  }
  
  public boolean isEmpty() {
    boolean bool;
    if (this.mHead == this.mTail) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int popFirst() {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException(); 
    int i = this.mElements[this.mHead];
    this.mHead = this.mHead + 1 & this.mCapacityBitmask;
    return i;
  }
  
  public int popLast() {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException(); 
    int i = this.mTail - 1 & this.mCapacityBitmask;
    int j = this.mElements[i];
    this.mTail = i;
    return j;
  }
  
  public void removeFromEnd(int paramInt) {
    if (paramInt <= 0)
      return; 
    if (paramInt > size())
      throw new ArrayIndexOutOfBoundsException(); 
    int i = this.mTail;
    this.mTail = this.mCapacityBitmask & i - paramInt;
  }
  
  public void removeFromStart(int paramInt) {
    if (paramInt <= 0)
      return; 
    if (paramInt > size())
      throw new ArrayIndexOutOfBoundsException(); 
    int i = this.mHead;
    this.mHead = this.mCapacityBitmask & i + paramInt;
  }
  
  public int size() {
    int i = this.mTail;
    int j = this.mHead;
    return this.mCapacityBitmask & i - j;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v\\util\CircularIntArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */