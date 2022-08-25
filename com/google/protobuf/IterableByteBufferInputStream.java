package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;

class IterableByteBufferInputStream extends InputStream {
  private long currentAddress;
  
  private byte[] currentArray;
  
  private int currentArrayOffset;
  
  private ByteBuffer currentByteBuffer;
  
  private int currentByteBufferPos;
  
  private int currentIndex;
  
  private int dataSize;
  
  private boolean hasArray;
  
  private Iterator<ByteBuffer> iterator;
  
  IterableByteBufferInputStream(Iterable<ByteBuffer> paramIterable) {
    this.iterator = paramIterable.iterator();
    this.dataSize = 0;
    for (ByteBuffer byteBuffer : paramIterable)
      this.dataSize++; 
    this.currentIndex = -1;
    if (!getNextByteBuffer()) {
      this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
      this.currentIndex = 0;
      this.currentByteBufferPos = 0;
      this.currentAddress = 0L;
    } 
  }
  
  private boolean getNextByteBuffer() {
    this.currentIndex++;
    if (!this.iterator.hasNext())
      return false; 
    this.currentByteBuffer = this.iterator.next();
    this.currentByteBufferPos = this.currentByteBuffer.position();
    if (this.currentByteBuffer.hasArray()) {
      this.hasArray = true;
      this.currentArray = this.currentByteBuffer.array();
      this.currentArrayOffset = this.currentByteBuffer.arrayOffset();
    } else {
      this.hasArray = false;
      this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
      this.currentArray = null;
    } 
    return true;
  }
  
  private void updateCurrentByteBufferPos(int paramInt) {
    this.currentByteBufferPos += paramInt;
    if (this.currentByteBufferPos == this.currentByteBuffer.limit())
      getNextByteBuffer(); 
  }
  
  public int read() throws IOException {
    if (this.currentIndex == this.dataSize)
      return -1; 
    if (this.hasArray) {
      byte b1 = this.currentArray[this.currentByteBufferPos + this.currentArrayOffset];
      updateCurrentByteBufferPos(1);
      return b1 & 0xFF;
    } 
    byte b = UnsafeUtil.getByte(this.currentByteBufferPos + this.currentAddress);
    updateCurrentByteBufferPos(1);
    return b & 0xFF;
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (this.currentIndex == this.dataSize)
      return -1; 
    int i = this.currentByteBuffer.limit() - this.currentByteBufferPos;
    int j = paramInt2;
    if (paramInt2 > i)
      j = i; 
    if (this.hasArray) {
      System.arraycopy(this.currentArray, this.currentByteBufferPos + this.currentArrayOffset, paramArrayOfbyte, paramInt1, j);
      updateCurrentByteBufferPos(j);
    } else {
      paramInt2 = this.currentByteBuffer.position();
      this.currentByteBuffer.position(this.currentByteBufferPos);
      this.currentByteBuffer.get(paramArrayOfbyte, paramInt1, j);
      this.currentByteBuffer.position(paramInt2);
      updateCurrentByteBufferPos(j);
    } 
    return j;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\IterableByteBufferInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */