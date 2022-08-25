package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

final class RopeByteString extends ByteString {
  static final int[] minLengthByDepth = new int[] { 
      1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 
      89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 
      10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 
      1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 
      165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE };
  
  private static final long serialVersionUID = 1L;
  
  private final ByteString left;
  
  private final int leftLength;
  
  private final ByteString right;
  
  private final int totalLength;
  
  private final int treeDepth;
  
  private RopeByteString(ByteString paramByteString1, ByteString paramByteString2) {
    this.left = paramByteString1;
    this.right = paramByteString2;
    this.leftLength = paramByteString1.size();
    this.totalLength = this.leftLength + paramByteString2.size();
    this.treeDepth = Math.max(paramByteString1.getTreeDepth(), paramByteString2.getTreeDepth()) + 1;
  }
  
  static ByteString concatenate(ByteString paramByteString1, ByteString paramByteString2) {
    if (paramByteString2.size() == 0)
      return paramByteString1; 
    if (paramByteString1.size() == 0)
      return paramByteString2; 
    int i = paramByteString1.size() + paramByteString2.size();
    if (i < 128)
      return concatenateBytes(paramByteString1, paramByteString2); 
    if (paramByteString1 instanceof RopeByteString) {
      RopeByteString ropeByteString = (RopeByteString)paramByteString1;
      if (ropeByteString.right.size() + paramByteString2.size() < 128) {
        paramByteString1 = concatenateBytes(ropeByteString.right, paramByteString2);
        return new RopeByteString(ropeByteString.left, paramByteString1);
      } 
      if (ropeByteString.left.getTreeDepth() > ropeByteString.right.getTreeDepth() && ropeByteString.getTreeDepth() > paramByteString2.getTreeDepth()) {
        paramByteString1 = new RopeByteString(ropeByteString.right, paramByteString2);
        return new RopeByteString(ropeByteString.left, paramByteString1);
      } 
    } 
    int j = Math.max(paramByteString1.getTreeDepth(), paramByteString2.getTreeDepth());
    return (i >= minLengthByDepth[j + 1]) ? new RopeByteString(paramByteString1, paramByteString2) : (new Balancer()).balance(paramByteString1, paramByteString2);
  }
  
  private static ByteString concatenateBytes(ByteString paramByteString1, ByteString paramByteString2) {
    int i = paramByteString1.size();
    int j = paramByteString2.size();
    byte[] arrayOfByte = new byte[i + j];
    paramByteString1.copyTo(arrayOfByte, 0, 0, i);
    paramByteString2.copyTo(arrayOfByte, 0, i, j);
    return ByteString.wrap(arrayOfByte);
  }
  
  private boolean equalsFragments(ByteString paramByteString) {
    PieceIterator pieceIterator1 = new PieceIterator(this);
    ByteString.LeafByteString leafByteString = pieceIterator1.next();
    PieceIterator pieceIterator2 = new PieceIterator(paramByteString);
    paramByteString = pieceIterator2.next();
    int i = 0;
    int j = i;
    int k = j;
    while (true) {
      boolean bool;
      int m = leafByteString.size() - i;
      int n = paramByteString.size() - j;
      int i1 = Math.min(m, n);
      if (i == 0) {
        bool = leafByteString.equalsRange(paramByteString, j, i1);
      } else {
        bool = paramByteString.equalsRange(leafByteString, i, i1);
      } 
      if (!bool)
        return false; 
      k += i1;
      if (k >= this.totalLength) {
        if (k == this.totalLength)
          return true; 
        throw new IllegalStateException();
      } 
      if (i1 == m) {
        leafByteString = pieceIterator1.next();
        i = 0;
      } else {
        i += i1;
      } 
      if (i1 == n) {
        paramByteString = pieceIterator2.next();
        j = 0;
        continue;
      } 
      j += i1;
    } 
  }
  
  static RopeByteString newInstanceForTest(ByteString paramByteString1, ByteString paramByteString2) {
    return new RopeByteString(paramByteString1, paramByteString2);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream) throws IOException {
    throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
  }
  
  public ByteBuffer asReadOnlyByteBuffer() {
    return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
  }
  
  public List<ByteBuffer> asReadOnlyByteBufferList() {
    ArrayList<ByteBuffer> arrayList = new ArrayList();
    PieceIterator pieceIterator = new PieceIterator(this);
    while (pieceIterator.hasNext())
      arrayList.add(pieceIterator.next().asReadOnlyByteBuffer()); 
    return arrayList;
  }
  
  public byte byteAt(int paramInt) {
    checkIndex(paramInt, this.totalLength);
    return internalByteAt(paramInt);
  }
  
  public void copyTo(ByteBuffer paramByteBuffer) {
    this.left.copyTo(paramByteBuffer);
    this.right.copyTo(paramByteBuffer);
  }
  
  protected void copyToInternal(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 + paramInt3 <= this.leftLength) {
      this.left.copyToInternal(paramArrayOfbyte, paramInt1, paramInt2, paramInt3);
    } else if (paramInt1 >= this.leftLength) {
      this.right.copyToInternal(paramArrayOfbyte, paramInt1 - this.leftLength, paramInt2, paramInt3);
    } else {
      int i = this.leftLength - paramInt1;
      this.left.copyToInternal(paramArrayOfbyte, paramInt1, paramInt2, i);
      this.right.copyToInternal(paramArrayOfbyte, 0, paramInt2 + i, paramInt3 - i);
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof ByteString))
      return false; 
    paramObject = paramObject;
    if (this.totalLength != paramObject.size())
      return false; 
    if (this.totalLength == 0)
      return true; 
    int i = peekCachedHashCode();
    int j = paramObject.peekCachedHashCode();
    return (i != 0 && j != 0 && i != j) ? false : equalsFragments((ByteString)paramObject);
  }
  
  protected int getTreeDepth() {
    return this.treeDepth;
  }
  
  byte internalByteAt(int paramInt) {
    return (paramInt < this.leftLength) ? this.left.internalByteAt(paramInt) : this.right.internalByteAt(paramInt - this.leftLength);
  }
  
  protected boolean isBalanced() {
    boolean bool;
    if (this.totalLength >= minLengthByDepth[this.treeDepth]) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isValidUtf8() {
    ByteString byteString = this.left;
    int i = this.leftLength;
    boolean bool = false;
    i = byteString.partialIsValidUtf8(0, 0, i);
    if (this.right.partialIsValidUtf8(i, 0, this.right.size()) == 0)
      bool = true; 
    return bool;
  }
  
  public ByteString.ByteIterator iterator() {
    return new ByteString.AbstractByteIterator() {
        ByteString.ByteIterator current = nextPiece();
        
        final RopeByteString.PieceIterator pieces = new RopeByteString.PieceIterator(RopeByteString.this);
        
        private ByteString.ByteIterator nextPiece() {
          ByteString.ByteIterator byteIterator;
          if (this.pieces.hasNext()) {
            byteIterator = this.pieces.next().iterator();
          } else {
            byteIterator = null;
          } 
          return byteIterator;
        }
        
        public boolean hasNext() {
          boolean bool;
          if (this.current != null) {
            bool = true;
          } else {
            bool = false;
          } 
          return bool;
        }
        
        public byte nextByte() {
          if (this.current == null)
            throw new NoSuchElementException(); 
          byte b = this.current.nextByte();
          if (!this.current.hasNext())
            this.current = nextPiece(); 
          return b;
        }
      };
  }
  
  public CodedInputStream newCodedInput() {
    return CodedInputStream.newInstance(new RopeInputStream());
  }
  
  public InputStream newInput() {
    return new RopeInputStream();
  }
  
  protected int partialHash(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt2 + paramInt3 <= this.leftLength)
      return this.left.partialHash(paramInt1, paramInt2, paramInt3); 
    if (paramInt2 >= this.leftLength)
      return this.right.partialHash(paramInt1, paramInt2 - this.leftLength, paramInt3); 
    int i = this.leftLength - paramInt2;
    paramInt1 = this.left.partialHash(paramInt1, paramInt2, i);
    return this.right.partialHash(paramInt1, 0, paramInt3 - i);
  }
  
  protected int partialIsValidUtf8(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt2 + paramInt3 <= this.leftLength)
      return this.left.partialIsValidUtf8(paramInt1, paramInt2, paramInt3); 
    if (paramInt2 >= this.leftLength)
      return this.right.partialIsValidUtf8(paramInt1, paramInt2 - this.leftLength, paramInt3); 
    int i = this.leftLength - paramInt2;
    paramInt1 = this.left.partialIsValidUtf8(paramInt1, paramInt2, i);
    return this.right.partialIsValidUtf8(paramInt1, 0, paramInt3 - i);
  }
  
  public int size() {
    return this.totalLength;
  }
  
  public ByteString substring(int paramInt1, int paramInt2) {
    int i = checkRange(paramInt1, paramInt2, this.totalLength);
    return (i == 0) ? ByteString.EMPTY : ((i == this.totalLength) ? this : ((paramInt2 <= this.leftLength) ? this.left.substring(paramInt1, paramInt2) : ((paramInt1 >= this.leftLength) ? this.right.substring(paramInt1 - this.leftLength, paramInt2 - this.leftLength) : new RopeByteString(this.left.substring(paramInt1), this.right.substring(0, paramInt2 - this.leftLength)))));
  }
  
  protected String toStringInternal(Charset paramCharset) {
    return new String(toByteArray(), paramCharset);
  }
  
  Object writeReplace() {
    return ByteString.wrap(toByteArray());
  }
  
  void writeTo(ByteOutput paramByteOutput) throws IOException {
    this.left.writeTo(paramByteOutput);
    this.right.writeTo(paramByteOutput);
  }
  
  public void writeTo(OutputStream paramOutputStream) throws IOException {
    this.left.writeTo(paramOutputStream);
    this.right.writeTo(paramOutputStream);
  }
  
  void writeToInternal(OutputStream paramOutputStream, int paramInt1, int paramInt2) throws IOException {
    if (paramInt1 + paramInt2 <= this.leftLength) {
      this.left.writeToInternal(paramOutputStream, paramInt1, paramInt2);
    } else if (paramInt1 >= this.leftLength) {
      this.right.writeToInternal(paramOutputStream, paramInt1 - this.leftLength, paramInt2);
    } else {
      int i = this.leftLength - paramInt1;
      this.left.writeToInternal(paramOutputStream, paramInt1, i);
      this.right.writeToInternal(paramOutputStream, 0, paramInt2 - i);
    } 
  }
  
  void writeToReverse(ByteOutput paramByteOutput) throws IOException {
    this.right.writeToReverse(paramByteOutput);
    this.left.writeToReverse(paramByteOutput);
  }
  
  private static class Balancer {
    private final ArrayDeque<ByteString> prefixesStack = new ArrayDeque<>();
    
    private Balancer() {}
    
    private ByteString balance(ByteString param1ByteString1, ByteString param1ByteString2) {
      doBalance(param1ByteString1);
      doBalance(param1ByteString2);
      for (param1ByteString1 = this.prefixesStack.pop(); !this.prefixesStack.isEmpty(); param1ByteString1 = new RopeByteString(this.prefixesStack.pop(), param1ByteString1));
      return param1ByteString1;
    }
    
    private void doBalance(ByteString param1ByteString) {
      if (param1ByteString.isBalanced()) {
        insert(param1ByteString);
      } else {
        if (param1ByteString instanceof RopeByteString) {
          param1ByteString = param1ByteString;
          doBalance(((RopeByteString)param1ByteString).left);
          doBalance(((RopeByteString)param1ByteString).right);
          return;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Has a new type of ByteString been created? Found ");
        stringBuilder.append(param1ByteString.getClass());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    }
    
    private int getDepthBinForLength(int param1Int) {
      int i = Arrays.binarySearch(RopeByteString.minLengthByDepth, param1Int);
      param1Int = i;
      if (i < 0)
        param1Int = -(i + 1) - 1; 
      return param1Int;
    }
    
    private void insert(ByteString param1ByteString) {
      int i = getDepthBinForLength(param1ByteString.size());
      int j = RopeByteString.minLengthByDepth[i + 1];
      if (this.prefixesStack.isEmpty() || ((ByteString)this.prefixesStack.peek()).size() >= j) {
        this.prefixesStack.push(param1ByteString);
        return;
      } 
      i = RopeByteString.minLengthByDepth[i];
      ByteString byteString;
      for (byteString = this.prefixesStack.pop(); !this.prefixesStack.isEmpty() && ((ByteString)this.prefixesStack.peek()).size() < i; byteString = new RopeByteString(this.prefixesStack.pop(), byteString));
      param1ByteString = new RopeByteString(byteString, param1ByteString);
      while (!this.prefixesStack.isEmpty()) {
        i = getDepthBinForLength(param1ByteString.size());
        i = RopeByteString.minLengthByDepth[i + 1];
        if (((ByteString)this.prefixesStack.peek()).size() < i)
          param1ByteString = new RopeByteString(this.prefixesStack.pop(), param1ByteString); 
      } 
      this.prefixesStack.push(param1ByteString);
    }
  }
  
  private static final class PieceIterator implements Iterator<ByteString.LeafByteString> {
    private final ArrayDeque<RopeByteString> breadCrumbs;
    
    private ByteString.LeafByteString next;
    
    private PieceIterator(ByteString param1ByteString) {
      if (param1ByteString instanceof RopeByteString) {
        param1ByteString = param1ByteString;
        this.breadCrumbs = new ArrayDeque<>(param1ByteString.getTreeDepth());
        this.breadCrumbs.push(param1ByteString);
        this.next = getLeafByLeft(((RopeByteString)param1ByteString).left);
      } else {
        this.breadCrumbs = null;
        this.next = (ByteString.LeafByteString)param1ByteString;
      } 
    }
    
    private ByteString.LeafByteString getLeafByLeft(ByteString param1ByteString) {
      while (param1ByteString instanceof RopeByteString) {
        param1ByteString = param1ByteString;
        this.breadCrumbs.push(param1ByteString);
        param1ByteString = ((RopeByteString)param1ByteString).left;
      } 
      return (ByteString.LeafByteString)param1ByteString;
    }
    
    private ByteString.LeafByteString getNextNonEmptyLeaf() {
      while (this.breadCrumbs != null && !this.breadCrumbs.isEmpty()) {
        ByteString.LeafByteString leafByteString = getLeafByLeft((this.breadCrumbs.pop()).right);
        if (!leafByteString.isEmpty())
          return leafByteString; 
      } 
      return null;
    }
    
    public boolean hasNext() {
      boolean bool;
      if (this.next != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public ByteString.LeafByteString next() {
      if (this.next == null)
        throw new NoSuchElementException(); 
      ByteString.LeafByteString leafByteString = this.next;
      this.next = getNextNonEmptyLeaf();
      return leafByteString;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  private class RopeInputStream extends InputStream {
    private ByteString.LeafByteString currentPiece;
    
    private int currentPieceIndex;
    
    private int currentPieceOffsetInRope;
    
    private int currentPieceSize;
    
    private int mark;
    
    private RopeByteString.PieceIterator pieceIterator;
    
    public RopeInputStream() {
      initialize();
    }
    
    private void advanceIfCurrentPieceFullyRead() {
      if (this.currentPiece != null && this.currentPieceIndex == this.currentPieceSize) {
        this.currentPieceOffsetInRope += this.currentPieceSize;
        this.currentPieceIndex = 0;
        if (this.pieceIterator.hasNext()) {
          this.currentPiece = this.pieceIterator.next();
          this.currentPieceSize = this.currentPiece.size();
        } else {
          this.currentPiece = null;
          this.currentPieceSize = 0;
        } 
      } 
    }
    
    private void initialize() {
      this.pieceIterator = new RopeByteString.PieceIterator(RopeByteString.this);
      this.currentPiece = this.pieceIterator.next();
      this.currentPieceSize = this.currentPiece.size();
      this.currentPieceIndex = 0;
      this.currentPieceOffsetInRope = 0;
    }
    
    private int readSkipInternal(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      int i = param1Int1;
      param1Int1 = param1Int2;
      while (param1Int1 > 0) {
        advanceIfCurrentPieceFullyRead();
        if (this.currentPiece == null) {
          if (param1Int1 == param1Int2)
            return -1; 
          break;
        } 
        int j = Math.min(this.currentPieceSize - this.currentPieceIndex, param1Int1);
        int k = i;
        if (param1ArrayOfbyte != null) {
          this.currentPiece.copyTo(param1ArrayOfbyte, this.currentPieceIndex, i, j);
          k = i + j;
        } 
        this.currentPieceIndex += j;
        param1Int1 -= j;
        i = k;
      } 
      return param1Int2 - param1Int1;
    }
    
    public int available() throws IOException {
      int i = this.currentPieceOffsetInRope;
      int j = this.currentPieceIndex;
      return RopeByteString.this.size() - i + j;
    }
    
    public void mark(int param1Int) {
      this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
    }
    
    public boolean markSupported() {
      return true;
    }
    
    public int read() throws IOException {
      advanceIfCurrentPieceFullyRead();
      if (this.currentPiece == null)
        return -1; 
      ByteString.LeafByteString leafByteString = this.currentPiece;
      int i = this.currentPieceIndex;
      this.currentPieceIndex = i + 1;
      return leafByteString.byteAt(i) & 0xFF;
    }
    
    public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      if (param1ArrayOfbyte == null)
        throw new NullPointerException(); 
      if (param1Int1 < 0 || param1Int2 < 0 || param1Int2 > param1ArrayOfbyte.length - param1Int1)
        throw new IndexOutOfBoundsException(); 
      return readSkipInternal(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void reset() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: invokespecial initialize : ()V
      //   6: aload_0
      //   7: aconst_null
      //   8: iconst_0
      //   9: aload_0
      //   10: getfield mark : I
      //   13: invokespecial readSkipInternal : ([BII)I
      //   16: pop
      //   17: aload_0
      //   18: monitorexit
      //   19: return
      //   20: astore_1
      //   21: aload_0
      //   22: monitorexit
      //   23: aload_1
      //   24: athrow
      // Exception table:
      //   from	to	target	type
      //   2	17	20	finally
    }
    
    public long skip(long param1Long) {
      if (param1Long < 0L)
        throw new IndexOutOfBoundsException(); 
      long l = param1Long;
      if (param1Long > 2147483647L)
        l = 2147483647L; 
      return readSkipInternal(null, 0, (int)l);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\RopeByteString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */