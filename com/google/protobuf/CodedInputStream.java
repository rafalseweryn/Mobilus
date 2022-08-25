package com.google.protobuf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class CodedInputStream {
  private static final int DEFAULT_BUFFER_SIZE = 4096;
  
  private static final int DEFAULT_RECURSION_LIMIT = 100;
  
  private static final int DEFAULT_SIZE_LIMIT = 2147483647;
  
  int recursionDepth;
  
  int recursionLimit = 100;
  
  private boolean shouldDiscardUnknownFields = false;
  
  int sizeLimit = Integer.MAX_VALUE;
  
  CodedInputStreamReader wrapper;
  
  private CodedInputStream() {}
  
  public static int decodeZigZag32(int paramInt) {
    return -(paramInt & 0x1) ^ paramInt >>> 1;
  }
  
  public static long decodeZigZag64(long paramLong) {
    return -(paramLong & 0x1L) ^ paramLong >>> 1L;
  }
  
  public static CodedInputStream newInstance(InputStream paramInputStream) {
    return newInstance(paramInputStream, 4096);
  }
  
  public static CodedInputStream newInstance(InputStream paramInputStream, int paramInt) {
    if (paramInt <= 0)
      throw new IllegalArgumentException("bufferSize must be > 0"); 
    return (paramInputStream == null) ? newInstance(Internal.EMPTY_BYTE_ARRAY) : new StreamDecoder(paramInputStream, paramInt);
  }
  
  public static CodedInputStream newInstance(Iterable<ByteBuffer> paramIterable) {
    return !UnsafeDirectNioDecoder.isSupported() ? newInstance(new IterableByteBufferInputStream(paramIterable)) : newInstance(paramIterable, false);
  }
  
  static CodedInputStream newInstance(Iterable<ByteBuffer> paramIterable, boolean paramBoolean) {
    Iterator<ByteBuffer> iterator = paramIterable.iterator();
    int i = 0;
    int j;
    for (j = 0; iterator.hasNext(); j |= 0x4) {
      ByteBuffer byteBuffer = iterator.next();
      i += byteBuffer.remaining();
      if (byteBuffer.hasArray()) {
        j |= 0x1;
        continue;
      } 
      if (byteBuffer.isDirect()) {
        j |= 0x2;
        continue;
      } 
    } 
    return (j == 2) ? new IterableDirectByteBufferDecoder(paramIterable, i, paramBoolean) : newInstance(new IterableByteBufferInputStream(paramIterable));
  }
  
  public static CodedInputStream newInstance(ByteBuffer paramByteBuffer) {
    return newInstance(paramByteBuffer, false);
  }
  
  static CodedInputStream newInstance(ByteBuffer paramByteBuffer, boolean paramBoolean) {
    if (paramByteBuffer.hasArray())
      return newInstance(paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining(), paramBoolean); 
    if (paramByteBuffer.isDirect() && UnsafeDirectNioDecoder.isSupported())
      return new UnsafeDirectNioDecoder(paramByteBuffer, paramBoolean); 
    byte[] arrayOfByte = new byte[paramByteBuffer.remaining()];
    paramByteBuffer.duplicate().get(arrayOfByte);
    return newInstance(arrayOfByte, 0, arrayOfByte.length, true);
  }
  
  public static CodedInputStream newInstance(byte[] paramArrayOfbyte) {
    return newInstance(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static CodedInputStream newInstance(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return newInstance(paramArrayOfbyte, paramInt1, paramInt2, false);
  }
  
  static CodedInputStream newInstance(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    ArrayDecoder arrayDecoder = new ArrayDecoder(paramArrayOfbyte, paramInt1, paramInt2, paramBoolean);
    try {
      arrayDecoder.pushLimit(paramInt2);
      return arrayDecoder;
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw new IllegalArgumentException(invalidProtocolBufferException);
    } 
  }
  
  public static int readRawVarint32(int paramInt, InputStream paramInputStream) throws IOException {
    int j;
    if ((paramInt & 0x80) == 0)
      return paramInt; 
    int i = paramInt & 0x7F;
    paramInt = 7;
    while (true) {
      j = paramInt;
      if (paramInt < 32) {
        j = paramInputStream.read();
        if (j == -1)
          throw InvalidProtocolBufferException.truncatedMessage(); 
        i |= (j & 0x7F) << paramInt;
        if ((j & 0x80) == 0)
          return i; 
        paramInt += 7;
        continue;
      } 
      break;
    } 
    while (j < 64) {
      paramInt = paramInputStream.read();
      if (paramInt == -1)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      if ((paramInt & 0x80) == 0)
        return i; 
      j += 7;
    } 
    throw InvalidProtocolBufferException.malformedVarint();
  }
  
  static int readRawVarint32(InputStream paramInputStream) throws IOException {
    int i = paramInputStream.read();
    if (i == -1)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return readRawVarint32(i, paramInputStream);
  }
  
  public abstract void checkLastTagWas(int paramInt) throws InvalidProtocolBufferException;
  
  final void discardUnknownFields() {
    this.shouldDiscardUnknownFields = true;
  }
  
  public abstract void enableAliasing(boolean paramBoolean);
  
  public abstract int getBytesUntilLimit();
  
  public abstract int getLastTag();
  
  public abstract int getTotalBytesRead();
  
  public abstract boolean isAtEnd() throws IOException;
  
  public abstract void popLimit(int paramInt);
  
  public abstract int pushLimit(int paramInt) throws InvalidProtocolBufferException;
  
  public abstract boolean readBool() throws IOException;
  
  public abstract byte[] readByteArray() throws IOException;
  
  public abstract ByteBuffer readByteBuffer() throws IOException;
  
  public abstract ByteString readBytes() throws IOException;
  
  public abstract double readDouble() throws IOException;
  
  public abstract int readEnum() throws IOException;
  
  public abstract int readFixed32() throws IOException;
  
  public abstract long readFixed64() throws IOException;
  
  public abstract float readFloat() throws IOException;
  
  public abstract <T extends MessageLite> T readGroup(int paramInt, Parser<T> paramParser, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException;
  
  public abstract void readGroup(int paramInt, MessageLite.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException;
  
  public abstract int readInt32() throws IOException;
  
  public abstract long readInt64() throws IOException;
  
  public abstract <T extends MessageLite> T readMessage(Parser<T> paramParser, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException;
  
  public abstract void readMessage(MessageLite.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException;
  
  public abstract byte readRawByte() throws IOException;
  
  public abstract byte[] readRawBytes(int paramInt) throws IOException;
  
  public abstract int readRawLittleEndian32() throws IOException;
  
  public abstract long readRawLittleEndian64() throws IOException;
  
  public abstract int readRawVarint32() throws IOException;
  
  public abstract long readRawVarint64() throws IOException;
  
  abstract long readRawVarint64SlowPath() throws IOException;
  
  public abstract int readSFixed32() throws IOException;
  
  public abstract long readSFixed64() throws IOException;
  
  public abstract int readSInt32() throws IOException;
  
  public abstract long readSInt64() throws IOException;
  
  public abstract String readString() throws IOException;
  
  public abstract String readStringRequireUtf8() throws IOException;
  
  public abstract int readTag() throws IOException;
  
  public abstract int readUInt32() throws IOException;
  
  public abstract long readUInt64() throws IOException;
  
  @Deprecated
  public abstract void readUnknownGroup(int paramInt, MessageLite.Builder paramBuilder) throws IOException;
  
  public abstract void resetSizeCounter();
  
  public final int setRecursionLimit(int paramInt) {
    if (paramInt < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Recursion limit cannot be negative: ");
      stringBuilder.append(paramInt);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    int i = this.recursionLimit;
    this.recursionLimit = paramInt;
    return i;
  }
  
  public final int setSizeLimit(int paramInt) {
    if (paramInt < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Size limit cannot be negative: ");
      stringBuilder.append(paramInt);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    int i = this.sizeLimit;
    this.sizeLimit = paramInt;
    return i;
  }
  
  final boolean shouldDiscardUnknownFields() {
    return this.shouldDiscardUnknownFields;
  }
  
  public abstract boolean skipField(int paramInt) throws IOException;
  
  @Deprecated
  public abstract boolean skipField(int paramInt, CodedOutputStream paramCodedOutputStream) throws IOException;
  
  public abstract void skipMessage() throws IOException;
  
  public abstract void skipMessage(CodedOutputStream paramCodedOutputStream) throws IOException;
  
  public abstract void skipRawBytes(int paramInt) throws IOException;
  
  final void unsetDiscardUnknownFields() {
    this.shouldDiscardUnknownFields = false;
  }
  
  private static final class ArrayDecoder extends CodedInputStream {
    private final byte[] buffer;
    
    private int bufferSizeAfterLimit;
    
    private int currentLimit = Integer.MAX_VALUE;
    
    private boolean enableAliasing;
    
    private final boolean immutable;
    
    private int lastTag;
    
    private int limit;
    
    private int pos;
    
    private int startPos;
    
    private ArrayDecoder(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2, boolean param1Boolean) {
      this.buffer = param1ArrayOfbyte;
      this.limit = param1Int2 + param1Int1;
      this.pos = param1Int1;
      this.startPos = this.pos;
      this.immutable = param1Boolean;
    }
    
    private void recomputeBufferSizeAfterLimit() {
      this.limit += this.bufferSizeAfterLimit;
      int i = this.limit - this.startPos;
      if (i > this.currentLimit) {
        this.bufferSizeAfterLimit = i - this.currentLimit;
        this.limit -= this.bufferSizeAfterLimit;
      } else {
        this.bufferSizeAfterLimit = 0;
      } 
    }
    
    private void skipRawVarint() throws IOException {
      if (this.limit - this.pos >= 10) {
        skipRawVarintFastPath();
      } else {
        skipRawVarintSlowPath();
      } 
    }
    
    private void skipRawVarintFastPath() throws IOException {
      for (byte b = 0; b < 10; b++) {
        byte[] arrayOfByte = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        if (arrayOfByte[i] >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private void skipRawVarintSlowPath() throws IOException {
      for (byte b = 0; b < 10; b++) {
        if (readRawByte() >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    public void checkLastTagWas(int param1Int) throws InvalidProtocolBufferException {
      if (this.lastTag != param1Int)
        throw InvalidProtocolBufferException.invalidEndTag(); 
    }
    
    public void enableAliasing(boolean param1Boolean) {
      this.enableAliasing = param1Boolean;
    }
    
    public int getBytesUntilLimit() {
      return (this.currentLimit == Integer.MAX_VALUE) ? -1 : (this.currentLimit - getTotalBytesRead());
    }
    
    public int getLastTag() {
      return this.lastTag;
    }
    
    public int getTotalBytesRead() {
      return this.pos - this.startPos;
    }
    
    public boolean isAtEnd() throws IOException {
      boolean bool;
      if (this.pos == this.limit) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void popLimit(int param1Int) {
      this.currentLimit = param1Int;
      recomputeBufferSizeAfterLimit();
    }
    
    public int pushLimit(int param1Int) throws InvalidProtocolBufferException {
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      int i = param1Int + getTotalBytesRead();
      param1Int = this.currentLimit;
      if (i > param1Int)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      this.currentLimit = i;
      recomputeBufferSizeAfterLimit();
      return param1Int;
    }
    
    public boolean readBool() throws IOException {
      boolean bool;
      if (readRawVarint64() != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public byte[] readByteArray() throws IOException {
      return readRawBytes(readRawVarint32());
    }
    
    public ByteBuffer readByteBuffer() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= this.limit - this.pos) {
        ByteBuffer byteBuffer;
        if (!this.immutable && this.enableAliasing) {
          byteBuffer = ByteBuffer.wrap(this.buffer, this.pos, i).slice();
        } else {
          byteBuffer = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.pos, this.pos + i));
        } 
        this.pos += i;
        return byteBuffer;
      } 
      if (i == 0)
        return Internal.EMPTY_BYTE_BUFFER; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public ByteString readBytes() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= this.limit - this.pos) {
        ByteString byteString;
        if (this.immutable && this.enableAliasing) {
          byteString = ByteString.wrap(this.buffer, this.pos, i);
        } else {
          byteString = ByteString.copyFrom(this.buffer, this.pos, i);
        } 
        this.pos += i;
        return byteString;
      } 
      return (i == 0) ? ByteString.EMPTY : ByteString.wrap(readRawBytes(i));
    }
    
    public double readDouble() throws IOException {
      return Double.longBitsToDouble(readRawLittleEndian64());
    }
    
    public int readEnum() throws IOException {
      return readRawVarint32();
    }
    
    public int readFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public float readFloat() throws IOException {
      return Float.intBitsToFloat(readRawLittleEndian32());
    }
    
    public <T extends MessageLite> T readGroup(int param1Int, Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
      return (T)messageLite;
    }
    
    public void readGroup(int param1Int, MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
    }
    
    public int readInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readInt64() throws IOException {
      return readRawVarint64();
    }
    
    public <T extends MessageLite> T readMessage(Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
      return (T)messageLite;
    }
    
    public void readMessage(MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
    }
    
    public byte readRawByte() throws IOException {
      if (this.pos == this.limit)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      byte[] arrayOfByte = this.buffer;
      int i = this.pos;
      this.pos = i + 1;
      return arrayOfByte[i];
    }
    
    public byte[] readRawBytes(int param1Int) throws IOException {
      if (param1Int > 0 && param1Int <= this.limit - this.pos) {
        int i = this.pos;
        this.pos += param1Int;
        return Arrays.copyOfRange(this.buffer, i, this.pos);
      } 
      if (param1Int <= 0) {
        if (param1Int == 0)
          return Internal.EMPTY_BYTE_ARRAY; 
        throw InvalidProtocolBufferException.negativeSize();
      } 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public int readRawLittleEndian32() throws IOException {
      int i = this.pos;
      if (this.limit - i < 4)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 4;
      return arrayOfByte[i] & 0xFF | (arrayOfByte[i + 1] & 0xFF) << 8 | (arrayOfByte[i + 2] & 0xFF) << 16 | (arrayOfByte[i + 3] & 0xFF) << 24;
    }
    
    public long readRawLittleEndian64() throws IOException {
      int i = this.pos;
      if (this.limit - i < 8)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 8;
      long l1 = arrayOfByte[i];
      long l2 = arrayOfByte[i + 1];
      long l3 = arrayOfByte[i + 2];
      long l4 = arrayOfByte[i + 3];
      long l5 = arrayOfByte[i + 4];
      long l6 = arrayOfByte[i + 5];
      long l7 = arrayOfByte[i + 6];
      return (arrayOfByte[i + 7] & 0xFFL) << 56L | l1 & 0xFFL | (l2 & 0xFFL) << 8L | (l3 & 0xFFL) << 16L | (l4 & 0xFFL) << 24L | (l5 & 0xFFL) << 32L | (l6 & 0xFFL) << 40L | (l7 & 0xFFL) << 48L;
    }
    
    public int readRawVarint32() throws IOException {
      int i = this.pos;
      if (this.limit != i) {
        byte[] arrayOfByte = this.buffer;
        int j = i + 1;
        byte b = arrayOfByte[i];
        if (b >= 0) {
          this.pos = j;
          return b;
        } 
        if (this.limit - j >= 9) {
          i = j + 1;
          int k = b ^ arrayOfByte[j] << 7;
          if (k < 0) {
            j = k ^ 0xFFFFFF80;
          } else {
            j = i + 1;
            k ^= arrayOfByte[i] << 14;
            if (k >= 0) {
              k ^= 0x3F80;
              i = j;
              j = k;
            } else {
              i = j + 1;
              j = k ^ arrayOfByte[j] << 21;
              if (j < 0) {
                j ^= 0xFFE03F80;
              } else {
                int m = i + 1;
                byte b1 = arrayOfByte[i];
                k = j ^ b1 << 28 ^ 0xFE03F80;
                j = k;
                i = m;
                if (b1 < 0) {
                  int n = m + 1;
                  j = k;
                  i = n;
                  if (arrayOfByte[m] < 0) {
                    int i1 = n + 1;
                    j = k;
                    i = i1;
                    if (arrayOfByte[n] < 0) {
                      m = i1 + 1;
                      j = k;
                      i = m;
                      if (arrayOfByte[i1] < 0) {
                        n = m + 1;
                        j = k;
                        i = n;
                        if (arrayOfByte[m] < 0) {
                          i = n + 1;
                          j = k;
                          if (arrayOfByte[n] < 0)
                            return (int)readRawVarint64SlowPath(); 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.pos = i;
          return j;
        } 
      } 
      return (int)readRawVarint64SlowPath();
    }
    
    public long readRawVarint64() throws IOException {
      int i = this.pos;
      if (this.limit != i) {
        byte[] arrayOfByte = this.buffer;
        int j = i + 1;
        byte b = arrayOfByte[i];
        if (b >= 0) {
          this.pos = j;
          return b;
        } 
        if (this.limit - j >= 9) {
          long l;
          i = j + 1;
          int k = b ^ arrayOfByte[j] << 7;
          if (k < 0) {
            l = (k ^ 0xFFFFFF80);
          } else {
            j = i + 1;
            k ^= arrayOfByte[i] << 14;
            if (k >= 0) {
              l = (k ^ 0x3F80);
              i = j;
            } else {
              i = j + 1;
              j = k ^ arrayOfByte[j] << 21;
              if (j < 0) {
                l = (j ^ 0xFFE03F80);
              } else {
                l = j;
                j = i + 1;
                l = arrayOfByte[i] << 28L ^ l;
                if (l >= 0L) {
                  l ^= 0xFE03F80L;
                  i = j;
                } else {
                  i = j + 1;
                  l ^= arrayOfByte[j] << 35L;
                  if (l < 0L) {
                    l = 0xFFFFFFF80FE03F80L ^ l;
                  } else {
                    j = i + 1;
                    l ^= arrayOfByte[i] << 42L;
                    if (l >= 0L) {
                      l ^= 0x3F80FE03F80L;
                      i = j;
                    } else {
                      i = j + 1;
                      l ^= arrayOfByte[j] << 49L;
                      if (l < 0L) {
                        l = 0xFFFE03F80FE03F80L ^ l;
                      } else {
                        j = i + 1;
                        l = l ^ arrayOfByte[i] << 56L ^ 0xFE03F80FE03F80L;
                        if (l < 0L) {
                          i = j + 1;
                          if (arrayOfByte[j] < 0L)
                            return readRawVarint64SlowPath(); 
                        } else {
                          i = j;
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.pos = i;
          return l;
        } 
      } 
      return readRawVarint64SlowPath();
    }
    
    long readRawVarint64SlowPath() throws IOException {
      long l = 0L;
      for (byte b = 0; b < 64; b += 7) {
        byte b1 = readRawByte();
        l |= (b1 & Byte.MAX_VALUE) << b;
        if ((b1 & 0x80) == 0)
          return l; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    public int readSFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readSFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public int readSInt32() throws IOException {
      return decodeZigZag32(readRawVarint32());
    }
    
    public long readSInt64() throws IOException {
      return decodeZigZag64(readRawVarint64());
    }
    
    public String readString() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= this.limit - this.pos) {
        String str = new String(this.buffer, this.pos, i, Internal.UTF_8);
        this.pos += i;
        return str;
      } 
      if (i == 0)
        return ""; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public String readStringRequireUtf8() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= this.limit - this.pos) {
        String str = Utf8.decodeUtf8(this.buffer, this.pos, i);
        this.pos += i;
        return str;
      } 
      if (i == 0)
        return ""; 
      if (i <= 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public int readTag() throws IOException {
      if (isAtEnd()) {
        this.lastTag = 0;
        return 0;
      } 
      this.lastTag = readRawVarint32();
      if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
        throw InvalidProtocolBufferException.invalidTag(); 
      return this.lastTag;
    }
    
    public int readUInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readUInt64() throws IOException {
      return readRawVarint64();
    }
    
    @Deprecated
    public void readUnknownGroup(int param1Int, MessageLite.Builder param1Builder) throws IOException {
      readGroup(param1Int, param1Builder, ExtensionRegistryLite.getEmptyRegistry());
    }
    
    public void resetSizeCounter() {
      this.startPos = this.pos;
    }
    
    public boolean skipField(int param1Int) throws IOException {
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          skipRawBytes(4);
          return true;
        case 4:
          return false;
        case 3:
          skipMessage();
          checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4));
          return true;
        case 2:
          skipRawBytes(readRawVarint32());
          return true;
        case 1:
          skipRawBytes(8);
          return true;
        case 0:
          break;
      } 
      skipRawVarint();
      return true;
    }
    
    public boolean skipField(int param1Int, CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      ByteString byteString;
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          i = readRawLittleEndian32();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed32NoTag(i);
          return true;
        case 4:
          return false;
        case 3:
          param1CodedOutputStream.writeRawVarint32(param1Int);
          skipMessage(param1CodedOutputStream);
          param1Int = WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4);
          checkLastTagWas(param1Int);
          param1CodedOutputStream.writeRawVarint32(param1Int);
          return true;
        case 2:
          byteString = readBytes();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeBytesNoTag(byteString);
          return true;
        case 1:
          l = readRawLittleEndian64();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed64NoTag(l);
          return true;
        case 0:
          break;
      } 
      long l = readInt64();
      param1CodedOutputStream.writeRawVarint32(param1Int);
      param1CodedOutputStream.writeUInt64NoTag(l);
      return true;
    }
    
    public void skipMessage() throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i));
    }
    
    public void skipMessage(CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i, param1CodedOutputStream));
    }
    
    public void skipRawBytes(int param1Int) throws IOException {
      if (param1Int >= 0 && param1Int <= this.limit - this.pos) {
        this.pos += param1Int;
        return;
      } 
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
  }
  
  private static final class IterableDirectByteBufferDecoder extends CodedInputStream {
    private int bufferSizeAfterCurrentLimit;
    
    private long currentAddress;
    
    private ByteBuffer currentByteBuffer;
    
    private long currentByteBufferLimit;
    
    private long currentByteBufferPos;
    
    private long currentByteBufferStartPos;
    
    private int currentLimit = Integer.MAX_VALUE;
    
    private boolean enableAliasing;
    
    private boolean immutable;
    
    private Iterable<ByteBuffer> input;
    
    private Iterator<ByteBuffer> iterator;
    
    private int lastTag;
    
    private int startOffset;
    
    private int totalBufferSize;
    
    private int totalBytesRead;
    
    private IterableDirectByteBufferDecoder(Iterable<ByteBuffer> param1Iterable, int param1Int, boolean param1Boolean) {
      this.totalBufferSize = param1Int;
      this.input = param1Iterable;
      this.iterator = this.input.iterator();
      this.immutable = param1Boolean;
      this.totalBytesRead = 0;
      this.startOffset = 0;
      if (param1Int == 0) {
        this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
        this.currentByteBufferPos = 0L;
        this.currentByteBufferStartPos = 0L;
        this.currentByteBufferLimit = 0L;
        this.currentAddress = 0L;
      } else {
        tryGetNextByteBuffer();
      } 
    }
    
    private long currentRemaining() {
      return this.currentByteBufferLimit - this.currentByteBufferPos;
    }
    
    private void getNextByteBuffer() throws InvalidProtocolBufferException {
      if (!this.iterator.hasNext())
        throw InvalidProtocolBufferException.truncatedMessage(); 
      tryGetNextByteBuffer();
    }
    
    private void readRawBytesTo(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      if (param1Int2 >= 0 && param1Int2 <= remaining()) {
        int i = param1Int2;
        while (i > 0) {
          if (currentRemaining() == 0L)
            getNextByteBuffer(); 
          int j = Math.min(i, (int)currentRemaining());
          long l1 = this.currentByteBufferPos;
          long l2 = (param1Int2 - i + param1Int1);
          long l3 = j;
          UnsafeUtil.copyMemory(l1, param1ArrayOfbyte, l2, l3);
          i -= j;
          this.currentByteBufferPos += l3;
        } 
        return;
      } 
      if (param1Int2 <= 0) {
        if (param1Int2 == 0)
          return; 
        throw InvalidProtocolBufferException.negativeSize();
      } 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    private void recomputeBufferSizeAfterLimit() {
      this.totalBufferSize += this.bufferSizeAfterCurrentLimit;
      int i = this.totalBufferSize - this.startOffset;
      if (i > this.currentLimit) {
        this.bufferSizeAfterCurrentLimit = i - this.currentLimit;
        this.totalBufferSize -= this.bufferSizeAfterCurrentLimit;
      } else {
        this.bufferSizeAfterCurrentLimit = 0;
      } 
    }
    
    private int remaining() {
      return (int)((this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos + this.currentByteBufferStartPos);
    }
    
    private void skipRawVarint() throws IOException {
      for (byte b = 0; b < 10; b++) {
        if (readRawByte() >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private ByteBuffer slice(int param1Int1, int param1Int2) throws IOException {
      Exception exception;
      int i = this.currentByteBuffer.position();
      int j = this.currentByteBuffer.limit();
      try {
        this.currentByteBuffer.position(param1Int1);
        this.currentByteBuffer.limit(param1Int2);
        ByteBuffer byteBuffer = this.currentByteBuffer.slice();
        this.currentByteBuffer.position(i);
        this.currentByteBuffer.limit(j);
        return byteBuffer;
      } catch (IllegalArgumentException null) {
        throw InvalidProtocolBufferException.truncatedMessage();
      } finally {}
      this.currentByteBuffer.position(i);
      this.currentByteBuffer.limit(j);
      throw exception;
    }
    
    private void tryGetNextByteBuffer() {
      this.currentByteBuffer = this.iterator.next();
      this.totalBytesRead += (int)(this.currentByteBufferPos - this.currentByteBufferStartPos);
      this.currentByteBufferPos = this.currentByteBuffer.position();
      this.currentByteBufferStartPos = this.currentByteBufferPos;
      this.currentByteBufferLimit = this.currentByteBuffer.limit();
      this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
      this.currentByteBufferPos += this.currentAddress;
      this.currentByteBufferStartPos += this.currentAddress;
      this.currentByteBufferLimit += this.currentAddress;
    }
    
    public void checkLastTagWas(int param1Int) throws InvalidProtocolBufferException {
      if (this.lastTag != param1Int)
        throw InvalidProtocolBufferException.invalidEndTag(); 
    }
    
    public void enableAliasing(boolean param1Boolean) {
      this.enableAliasing = param1Boolean;
    }
    
    public int getBytesUntilLimit() {
      return (this.currentLimit == Integer.MAX_VALUE) ? -1 : (this.currentLimit - getTotalBytesRead());
    }
    
    public int getLastTag() {
      return this.lastTag;
    }
    
    public int getTotalBytesRead() {
      return (int)((this.totalBytesRead - this.startOffset) + this.currentByteBufferPos - this.currentByteBufferStartPos);
    }
    
    public boolean isAtEnd() throws IOException {
      boolean bool;
      if (this.totalBytesRead + this.currentByteBufferPos - this.currentByteBufferStartPos == this.totalBufferSize) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void popLimit(int param1Int) {
      this.currentLimit = param1Int;
      recomputeBufferSizeAfterLimit();
    }
    
    public int pushLimit(int param1Int) throws InvalidProtocolBufferException {
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      int i = param1Int + getTotalBytesRead();
      param1Int = this.currentLimit;
      if (i > param1Int)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      this.currentLimit = i;
      recomputeBufferSizeAfterLimit();
      return param1Int;
    }
    
    public boolean readBool() throws IOException {
      boolean bool;
      if (readRawVarint64() != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public byte[] readByteArray() throws IOException {
      return readRawBytes(readRawVarint32());
    }
    
    public ByteBuffer readByteBuffer() throws IOException {
      int i = readRawVarint32();
      if (i > 0) {
        long l = i;
        if (l <= currentRemaining()) {
          if (!this.immutable && this.enableAliasing) {
            this.currentByteBufferPos += l;
            return slice((int)(this.currentByteBufferPos - this.currentAddress - l), (int)(this.currentByteBufferPos - this.currentAddress));
          } 
          byte[] arrayOfByte = new byte[i];
          UnsafeUtil.copyMemory(this.currentByteBufferPos, arrayOfByte, 0L, l);
          this.currentByteBufferPos += l;
          return ByteBuffer.wrap(arrayOfByte);
        } 
      } 
      if (i > 0 && i <= remaining()) {
        byte[] arrayOfByte = new byte[i];
        readRawBytesTo(arrayOfByte, 0, i);
        return ByteBuffer.wrap(arrayOfByte);
      } 
      if (i == 0)
        return Internal.EMPTY_BYTE_BUFFER; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public ByteString readBytes() throws IOException {
      int i = readRawVarint32();
      if (i > 0) {
        long l = i;
        if (l <= this.currentByteBufferLimit - this.currentByteBufferPos) {
          if (this.immutable && this.enableAliasing) {
            int j = (int)(this.currentByteBufferPos - this.currentAddress);
            ByteString byteString = ByteString.wrap(slice(j, i + j));
            this.currentByteBufferPos += l;
            return byteString;
          } 
          byte[] arrayOfByte = new byte[i];
          UnsafeUtil.copyMemory(this.currentByteBufferPos, arrayOfByte, 0L, l);
          this.currentByteBufferPos += l;
          return ByteString.wrap(arrayOfByte);
        } 
      } 
      if (i > 0 && i <= remaining()) {
        byte[] arrayOfByte = new byte[i];
        readRawBytesTo(arrayOfByte, 0, i);
        return ByteString.wrap(arrayOfByte);
      } 
      if (i == 0)
        return ByteString.EMPTY; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public double readDouble() throws IOException {
      return Double.longBitsToDouble(readRawLittleEndian64());
    }
    
    public int readEnum() throws IOException {
      return readRawVarint32();
    }
    
    public int readFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public float readFloat() throws IOException {
      return Float.intBitsToFloat(readRawLittleEndian32());
    }
    
    public <T extends MessageLite> T readGroup(int param1Int, Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
      return (T)messageLite;
    }
    
    public void readGroup(int param1Int, MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
    }
    
    public int readInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readInt64() throws IOException {
      return readRawVarint64();
    }
    
    public <T extends MessageLite> T readMessage(Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
      return (T)messageLite;
    }
    
    public void readMessage(MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
    }
    
    public byte readRawByte() throws IOException {
      if (currentRemaining() == 0L)
        getNextByteBuffer(); 
      long l = this.currentByteBufferPos;
      this.currentByteBufferPos = 1L + l;
      return UnsafeUtil.getByte(l);
    }
    
    public byte[] readRawBytes(int param1Int) throws IOException {
      if (param1Int >= 0) {
        long l = param1Int;
        if (l <= currentRemaining()) {
          byte[] arrayOfByte = new byte[param1Int];
          UnsafeUtil.copyMemory(this.currentByteBufferPos, arrayOfByte, 0L, l);
          this.currentByteBufferPos += l;
          return arrayOfByte;
        } 
      } 
      if (param1Int >= 0 && param1Int <= remaining()) {
        byte[] arrayOfByte = new byte[param1Int];
        readRawBytesTo(arrayOfByte, 0, param1Int);
        return arrayOfByte;
      } 
      if (param1Int <= 0) {
        if (param1Int == 0)
          return Internal.EMPTY_BYTE_ARRAY; 
        throw InvalidProtocolBufferException.negativeSize();
      } 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public int readRawLittleEndian32() throws IOException {
      if (currentRemaining() >= 4L) {
        long l = this.currentByteBufferPos;
        this.currentByteBufferPos += 4L;
        return UnsafeUtil.getByte(l) & 0xFF | (UnsafeUtil.getByte(1L + l) & 0xFF) << 8 | (UnsafeUtil.getByte(2L + l) & 0xFF) << 16 | (UnsafeUtil.getByte(l + 3L) & 0xFF) << 24;
      } 
      byte b1 = readRawByte();
      byte b2 = readRawByte();
      byte b3 = readRawByte();
      return (readRawByte() & 0xFF) << 24 | b1 & 0xFF | (b2 & 0xFF) << 8 | (b3 & 0xFF) << 16;
    }
    
    public long readRawLittleEndian64() throws IOException {
      if (currentRemaining() >= 8L) {
        long l = this.currentByteBufferPos;
        this.currentByteBufferPos += 8L;
        return UnsafeUtil.getByte(l) & 0xFFL | (UnsafeUtil.getByte(1L + l) & 0xFFL) << 8L | (UnsafeUtil.getByte(2L + l) & 0xFFL) << 16L | (UnsafeUtil.getByte(3L + l) & 0xFFL) << 24L | (UnsafeUtil.getByte(4L + l) & 0xFFL) << 32L | (UnsafeUtil.getByte(5L + l) & 0xFFL) << 40L | (UnsafeUtil.getByte(6L + l) & 0xFFL) << 48L | (UnsafeUtil.getByte(l + 7L) & 0xFFL) << 56L;
      } 
      long l2 = readRawByte();
      long l3 = readRawByte();
      long l4 = readRawByte();
      long l5 = readRawByte();
      long l6 = readRawByte();
      long l1 = readRawByte();
      long l7 = readRawByte();
      return (readRawByte() & 0xFFL) << 56L | l2 & 0xFFL | (l3 & 0xFFL) << 8L | (l4 & 0xFFL) << 16L | (l5 & 0xFFL) << 24L | (l6 & 0xFFL) << 32L | (l1 & 0xFFL) << 40L | (l7 & 0xFFL) << 48L;
    }
    
    public int readRawVarint32() throws IOException {
      long l = this.currentByteBufferPos;
      if (this.currentByteBufferLimit != this.currentByteBufferPos) {
        long l1 = l + 1L;
        byte b = UnsafeUtil.getByte(l);
        if (b >= 0) {
          this.currentByteBufferPos++;
          return b;
        } 
        if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10L) {
          l = l1 + 1L;
          int i = b ^ UnsafeUtil.getByte(l1) << 7;
          if (i < 0) {
            i ^= 0xFFFFFF80;
            l1 = l;
          } else {
            l1 = l + 1L;
            i ^= UnsafeUtil.getByte(l) << 14;
            if (i >= 0) {
              i ^= 0x3F80;
            } else {
              long l2 = l1 + 1L;
              i ^= UnsafeUtil.getByte(l1) << 21;
              if (i < 0) {
                i ^= 0xFFE03F80;
                l1 = l2;
              } else {
                l = l2 + 1L;
                byte b1 = UnsafeUtil.getByte(l2);
                int j = i ^ b1 << 28 ^ 0xFE03F80;
                i = j;
                l1 = l;
                if (b1 < 0) {
                  l2 = l + 1L;
                  i = j;
                  l1 = l2;
                  if (UnsafeUtil.getByte(l) < 0) {
                    l = l2 + 1L;
                    i = j;
                    l1 = l;
                    if (UnsafeUtil.getByte(l2) < 0) {
                      l2 = l + 1L;
                      i = j;
                      l1 = l2;
                      if (UnsafeUtil.getByte(l) < 0) {
                        l = l2 + 1L;
                        i = j;
                        l1 = l;
                        if (UnsafeUtil.getByte(l2) < 0) {
                          l1 = l + 1L;
                          i = j;
                          if (UnsafeUtil.getByte(l) < 0)
                            return (int)readRawVarint64SlowPath(); 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.currentByteBufferPos = l1;
          return i;
        } 
      } 
      return (int)readRawVarint64SlowPath();
    }
    
    public long readRawVarint64() throws IOException {
      long l = this.currentByteBufferPos;
      if (this.currentByteBufferLimit != this.currentByteBufferPos) {
        long l1 = l + 1L;
        byte b = UnsafeUtil.getByte(l);
        if (b >= 0) {
          this.currentByteBufferPos++;
          return b;
        } 
        if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10L) {
          l = l1 + 1L;
          int i = b ^ UnsafeUtil.getByte(l1) << 7;
          if (i < 0) {
            long l2 = (i ^ 0xFFFFFF80);
            l1 = l;
            l = l2;
          } else {
            l1 = l + 1L;
            i ^= UnsafeUtil.getByte(l) << 14;
            if (i >= 0) {
              l = (i ^ 0x3F80);
            } else {
              long l2 = l1 + 1L;
              i ^= UnsafeUtil.getByte(l1) << 21;
              if (i < 0) {
                l = (i ^ 0xFFE03F80);
                l1 = l2;
              } else {
                l = i;
                l1 = l2 + 1L;
                l2 = l ^ UnsafeUtil.getByte(l2) << 28L;
                if (l2 >= 0L) {
                  l = l2 ^ 0xFE03F80L;
                } else {
                  l = l1 + 1L;
                  l1 = l2 ^ UnsafeUtil.getByte(l1) << 35L;
                  if (l1 < 0L) {
                    l2 = l1 ^ 0xFFFFFFF80FE03F80L;
                    l1 = l;
                    l = l2;
                  } else {
                    l2 = l + 1L;
                    l = l1 ^ UnsafeUtil.getByte(l) << 42L;
                    if (l >= 0L) {
                      l ^= 0x3F80FE03F80L;
                      l1 = l2;
                    } else {
                      l1 = l2 + 1L;
                      l ^= UnsafeUtil.getByte(l2) << 49L;
                      if (l < 0L) {
                        l ^= 0xFFFE03F80FE03F80L;
                      } else {
                        l2 = l1 + 1L;
                        long l3 = l ^ UnsafeUtil.getByte(l1) << 56L ^ 0xFE03F80FE03F80L;
                        l = l3;
                        l1 = l2;
                        if (l3 < 0L) {
                          if (UnsafeUtil.getByte(l2) < 0L)
                            return readRawVarint64SlowPath(); 
                          l1 = 1L + l2;
                          l = l3;
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.currentByteBufferPos = l1;
          return l;
        } 
      } 
      return readRawVarint64SlowPath();
    }
    
    long readRawVarint64SlowPath() throws IOException {
      long l = 0L;
      for (byte b = 0; b < 64; b += 7) {
        byte b1 = readRawByte();
        l |= (b1 & Byte.MAX_VALUE) << b;
        if ((b1 & 0x80) == 0)
          return l; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    public int readSFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readSFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public int readSInt32() throws IOException {
      return decodeZigZag32(readRawVarint32());
    }
    
    public long readSInt64() throws IOException {
      return decodeZigZag64(readRawVarint64());
    }
    
    public String readString() throws IOException {
      int i = readRawVarint32();
      if (i > 0) {
        long l = i;
        if (l <= this.currentByteBufferLimit - this.currentByteBufferPos) {
          byte[] arrayOfByte = new byte[i];
          UnsafeUtil.copyMemory(this.currentByteBufferPos, arrayOfByte, 0L, l);
          String str = new String(arrayOfByte, Internal.UTF_8);
          this.currentByteBufferPos += l;
          return str;
        } 
      } 
      if (i > 0 && i <= remaining()) {
        byte[] arrayOfByte = new byte[i];
        readRawBytesTo(arrayOfByte, 0, i);
        return new String(arrayOfByte, Internal.UTF_8);
      } 
      if (i == 0)
        return ""; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public String readStringRequireUtf8() throws IOException {
      int i = readRawVarint32();
      if (i > 0) {
        long l = i;
        if (l <= this.currentByteBufferLimit - this.currentByteBufferPos) {
          int j = (int)(this.currentByteBufferPos - this.currentByteBufferStartPos);
          String str = Utf8.decodeUtf8(this.currentByteBuffer, j, i);
          this.currentByteBufferPos += l;
          return str;
        } 
      } 
      if (i >= 0 && i <= remaining()) {
        byte[] arrayOfByte = new byte[i];
        readRawBytesTo(arrayOfByte, 0, i);
        return Utf8.decodeUtf8(arrayOfByte, 0, i);
      } 
      if (i == 0)
        return ""; 
      if (i <= 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public int readTag() throws IOException {
      if (isAtEnd()) {
        this.lastTag = 0;
        return 0;
      } 
      this.lastTag = readRawVarint32();
      if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
        throw InvalidProtocolBufferException.invalidTag(); 
      return this.lastTag;
    }
    
    public int readUInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readUInt64() throws IOException {
      return readRawVarint64();
    }
    
    @Deprecated
    public void readUnknownGroup(int param1Int, MessageLite.Builder param1Builder) throws IOException {
      readGroup(param1Int, param1Builder, ExtensionRegistryLite.getEmptyRegistry());
    }
    
    public void resetSizeCounter() {
      this.startOffset = (int)(this.totalBytesRead + this.currentByteBufferPos - this.currentByteBufferStartPos);
    }
    
    public boolean skipField(int param1Int) throws IOException {
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          skipRawBytes(4);
          return true;
        case 4:
          return false;
        case 3:
          skipMessage();
          checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4));
          return true;
        case 2:
          skipRawBytes(readRawVarint32());
          return true;
        case 1:
          skipRawBytes(8);
          return true;
        case 0:
          break;
      } 
      skipRawVarint();
      return true;
    }
    
    public boolean skipField(int param1Int, CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      ByteString byteString;
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          i = readRawLittleEndian32();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed32NoTag(i);
          return true;
        case 4:
          return false;
        case 3:
          param1CodedOutputStream.writeRawVarint32(param1Int);
          skipMessage(param1CodedOutputStream);
          param1Int = WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4);
          checkLastTagWas(param1Int);
          param1CodedOutputStream.writeRawVarint32(param1Int);
          return true;
        case 2:
          byteString = readBytes();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeBytesNoTag(byteString);
          return true;
        case 1:
          l = readRawLittleEndian64();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed64NoTag(l);
          return true;
        case 0:
          break;
      } 
      long l = readInt64();
      param1CodedOutputStream.writeRawVarint32(param1Int);
      param1CodedOutputStream.writeUInt64NoTag(l);
      return true;
    }
    
    public void skipMessage() throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i));
    }
    
    public void skipMessage(CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i, param1CodedOutputStream));
    }
    
    public void skipRawBytes(int param1Int) throws IOException {
      if (param1Int >= 0 && param1Int <= (this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos + this.currentByteBufferStartPos) {
        while (param1Int > 0) {
          if (currentRemaining() == 0L)
            getNextByteBuffer(); 
          int i = Math.min(param1Int, (int)currentRemaining());
          param1Int -= i;
          this.currentByteBufferPos += i;
        } 
        return;
      } 
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
  }
  
  private static final class StreamDecoder extends CodedInputStream {
    private final byte[] buffer;
    
    private int bufferSize;
    
    private int bufferSizeAfterLimit;
    
    private int currentLimit = Integer.MAX_VALUE;
    
    private final InputStream input;
    
    private int lastTag;
    
    private int pos;
    
    private RefillCallback refillCallback = null;
    
    private int totalBytesRetired;
    
    private StreamDecoder(InputStream param1InputStream, int param1Int) {
      Internal.checkNotNull(param1InputStream, "input");
      this.input = param1InputStream;
      this.buffer = new byte[param1Int];
      this.bufferSize = 0;
      this.pos = 0;
      this.totalBytesRetired = 0;
    }
    
    private ByteString readBytesSlowPath(int param1Int) throws IOException {
      byte[] arrayOfByte = readRawBytesSlowPathOneChunk(param1Int);
      if (arrayOfByte != null)
        return ByteString.copyFrom(arrayOfByte); 
      int i = this.pos;
      int j = this.bufferSize - this.pos;
      this.totalBytesRetired += this.bufferSize;
      this.pos = 0;
      this.bufferSize = 0;
      List<byte[]> list = readRawBytesSlowPathRemainingChunks(param1Int - j);
      arrayOfByte = new byte[param1Int];
      System.arraycopy(this.buffer, i, arrayOfByte, 0, j);
      Iterator<byte> iterator = list.iterator();
      for (param1Int = j; iterator.hasNext(); param1Int += arrayOfByte1.length) {
        byte[] arrayOfByte1 = (byte[])iterator.next();
        System.arraycopy(arrayOfByte1, 0, arrayOfByte, param1Int, arrayOfByte1.length);
      } 
      return ByteString.wrap(arrayOfByte);
    }
    
    private byte[] readRawBytesSlowPath(int param1Int, boolean param1Boolean) throws IOException {
      byte[] arrayOfByte1 = readRawBytesSlowPathOneChunk(param1Int);
      if (arrayOfByte1 != null) {
        byte[] arrayOfByte = arrayOfByte1;
        if (param1Boolean)
          arrayOfByte = (byte[])arrayOfByte1.clone(); 
        return arrayOfByte;
      } 
      int i = this.pos;
      int j = this.bufferSize - this.pos;
      this.totalBytesRetired += this.bufferSize;
      this.pos = 0;
      this.bufferSize = 0;
      List<byte[]> list = readRawBytesSlowPathRemainingChunks(param1Int - j);
      byte[] arrayOfByte2 = new byte[param1Int];
      System.arraycopy(this.buffer, i, arrayOfByte2, 0, j);
      Iterator<byte> iterator = list.iterator();
      for (param1Int = j; iterator.hasNext(); param1Int += arrayOfByte.length) {
        byte[] arrayOfByte = (byte[])iterator.next();
        System.arraycopy(arrayOfByte, 0, arrayOfByte2, param1Int, arrayOfByte.length);
      } 
      return arrayOfByte2;
    }
    
    private byte[] readRawBytesSlowPathOneChunk(int param1Int) throws IOException {
      if (param1Int == 0)
        return Internal.EMPTY_BYTE_ARRAY; 
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      int i = this.totalBytesRetired + this.pos + param1Int;
      if (i - this.sizeLimit > 0)
        throw InvalidProtocolBufferException.sizeLimitExceeded(); 
      if (i > this.currentLimit) {
        skipRawBytes(this.currentLimit - this.totalBytesRetired - this.pos);
        throw InvalidProtocolBufferException.truncatedMessage();
      } 
      i = this.bufferSize - this.pos;
      int j = param1Int - i;
      if (j < 4096 || j <= this.input.available()) {
        byte[] arrayOfByte = new byte[param1Int];
        System.arraycopy(this.buffer, this.pos, arrayOfByte, 0, i);
        this.totalBytesRetired += this.bufferSize;
        this.pos = 0;
        this.bufferSize = 0;
        while (i < arrayOfByte.length) {
          j = this.input.read(arrayOfByte, i, param1Int - i);
          if (j == -1)
            throw InvalidProtocolBufferException.truncatedMessage(); 
          this.totalBytesRetired += j;
          i += j;
        } 
        return arrayOfByte;
      } 
      return null;
    }
    
    private List<byte[]> readRawBytesSlowPathRemainingChunks(int param1Int) throws IOException {
      ArrayList<byte[]> arrayList = new ArrayList();
      while (param1Int > 0) {
        byte[] arrayOfByte = new byte[Math.min(param1Int, 4096)];
        int i;
        for (i = 0; i < arrayOfByte.length; i += j) {
          int j = this.input.read(arrayOfByte, i, arrayOfByte.length - i);
          if (j == -1)
            throw InvalidProtocolBufferException.truncatedMessage(); 
          this.totalBytesRetired += j;
        } 
        param1Int -= arrayOfByte.length;
        arrayList.add(arrayOfByte);
      } 
      return (List<byte[]>)arrayList;
    }
    
    private void recomputeBufferSizeAfterLimit() {
      this.bufferSize += this.bufferSizeAfterLimit;
      int i = this.totalBytesRetired + this.bufferSize;
      if (i > this.currentLimit) {
        this.bufferSizeAfterLimit = i - this.currentLimit;
        this.bufferSize -= this.bufferSizeAfterLimit;
      } else {
        this.bufferSizeAfterLimit = 0;
      } 
    }
    
    private void refillBuffer(int param1Int) throws IOException {
      if (!tryRefillBuffer(param1Int)) {
        if (param1Int > this.sizeLimit - this.totalBytesRetired - this.pos)
          throw InvalidProtocolBufferException.sizeLimitExceeded(); 
        throw InvalidProtocolBufferException.truncatedMessage();
      } 
    }
    
    private void skipRawBytesSlowPath(int param1Int) throws IOException {
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      if (this.totalBytesRetired + this.pos + param1Int > this.currentLimit) {
        skipRawBytes(this.currentLimit - this.totalBytesRetired - this.pos);
        throw InvalidProtocolBufferException.truncatedMessage();
      } 
      RefillCallback refillCallback = this.refillCallback;
      int i = 0;
      if (refillCallback == null) {
        this.totalBytesRetired += this.pos;
        int j = this.bufferSize;
        i = this.pos;
        this.bufferSize = 0;
        this.pos = 0;
        i = j - i;
        while (i < param1Int) {
          try {
            InputStream inputStream = this.input;
            long l1 = (param1Int - i);
            long l2 = inputStream.skip(l1);
            j = l2 cmp 0L;
            if (j < 0 || l2 > l1) {
              IllegalStateException illegalStateException = new IllegalStateException();
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append(this.input.getClass());
              stringBuilder.append("#skip returned invalid result: ");
              stringBuilder.append(l2);
              stringBuilder.append("\nThe InputStream implementation is buggy.");
              this(stringBuilder.toString());
              throw illegalStateException;
            } 
          } finally {
            this.totalBytesRetired += i;
            recomputeBufferSizeAfterLimit();
          } 
        } 
        this.totalBytesRetired += i;
        recomputeBufferSizeAfterLimit();
      } 
      if (i < param1Int) {
        i = this.bufferSize - this.pos;
        this.pos = this.bufferSize;
        refillBuffer(1);
        while (true) {
          int j = param1Int - i;
          if (j > this.bufferSize) {
            i += this.bufferSize;
            this.pos = this.bufferSize;
            refillBuffer(1);
            continue;
          } 
          this.pos = j;
          break;
        } 
      } 
    }
    
    private void skipRawVarint() throws IOException {
      if (this.bufferSize - this.pos >= 10) {
        skipRawVarintFastPath();
      } else {
        skipRawVarintSlowPath();
      } 
    }
    
    private void skipRawVarintFastPath() throws IOException {
      for (byte b = 0; b < 10; b++) {
        byte[] arrayOfByte = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        if (arrayOfByte[i] >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private void skipRawVarintSlowPath() throws IOException {
      for (byte b = 0; b < 10; b++) {
        if (readRawByte() >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private boolean tryRefillBuffer(int param1Int) throws IOException {
      if (this.pos + param1Int <= this.bufferSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("refillBuffer() called when ");
        stringBuilder.append(param1Int);
        stringBuilder.append(" bytes were already available in buffer");
        throw new IllegalStateException(stringBuilder.toString());
      } 
      if (param1Int > this.sizeLimit - this.totalBytesRetired - this.pos)
        return false; 
      if (this.totalBytesRetired + this.pos + param1Int > this.currentLimit)
        return false; 
      if (this.refillCallback != null)
        this.refillCallback.onRefill(); 
      int i = this.pos;
      if (i > 0) {
        if (this.bufferSize > i)
          System.arraycopy(this.buffer, i, this.buffer, 0, this.bufferSize - i); 
        this.totalBytesRetired += i;
        this.bufferSize -= i;
        this.pos = 0;
      } 
      i = this.input.read(this.buffer, this.bufferSize, Math.min(this.buffer.length - this.bufferSize, this.sizeLimit - this.totalBytesRetired - this.bufferSize));
      if (i == 0 || i < -1 || i > this.buffer.length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.input.getClass());
        stringBuilder.append("#read(byte[]) returned invalid result: ");
        stringBuilder.append(i);
        stringBuilder.append("\nThe InputStream implementation is buggy.");
        throw new IllegalStateException(stringBuilder.toString());
      } 
      if (i > 0) {
        boolean bool;
        this.bufferSize += i;
        recomputeBufferSizeAfterLimit();
        if (this.bufferSize >= param1Int) {
          bool = true;
        } else {
          bool = tryRefillBuffer(param1Int);
        } 
        return bool;
      } 
      return false;
    }
    
    public void checkLastTagWas(int param1Int) throws InvalidProtocolBufferException {
      if (this.lastTag != param1Int)
        throw InvalidProtocolBufferException.invalidEndTag(); 
    }
    
    public void enableAliasing(boolean param1Boolean) {}
    
    public int getBytesUntilLimit() {
      if (this.currentLimit == Integer.MAX_VALUE)
        return -1; 
      int i = this.totalBytesRetired;
      int j = this.pos;
      return this.currentLimit - i + j;
    }
    
    public int getLastTag() {
      return this.lastTag;
    }
    
    public int getTotalBytesRead() {
      return this.totalBytesRetired + this.pos;
    }
    
    public boolean isAtEnd() throws IOException {
      int i = this.pos;
      int j = this.bufferSize;
      boolean bool = true;
      if (i != j || tryRefillBuffer(1))
        bool = false; 
      return bool;
    }
    
    public void popLimit(int param1Int) {
      this.currentLimit = param1Int;
      recomputeBufferSizeAfterLimit();
    }
    
    public int pushLimit(int param1Int) throws InvalidProtocolBufferException {
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      param1Int += this.totalBytesRetired + this.pos;
      int i = this.currentLimit;
      if (param1Int > i)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      this.currentLimit = param1Int;
      recomputeBufferSizeAfterLimit();
      return i;
    }
    
    public boolean readBool() throws IOException {
      boolean bool;
      if (readRawVarint64() != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public byte[] readByteArray() throws IOException {
      int i = readRawVarint32();
      if (i <= this.bufferSize - this.pos && i > 0) {
        byte[] arrayOfByte = Arrays.copyOfRange(this.buffer, this.pos, this.pos + i);
        this.pos += i;
        return arrayOfByte;
      } 
      return readRawBytesSlowPath(i, false);
    }
    
    public ByteBuffer readByteBuffer() throws IOException {
      int i = readRawVarint32();
      if (i <= this.bufferSize - this.pos && i > 0) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.pos, this.pos + i));
        this.pos += i;
        return byteBuffer;
      } 
      return (i == 0) ? Internal.EMPTY_BYTE_BUFFER : ByteBuffer.wrap(readRawBytesSlowPath(i, true));
    }
    
    public ByteString readBytes() throws IOException {
      int i = readRawVarint32();
      if (i <= this.bufferSize - this.pos && i > 0) {
        ByteString byteString = ByteString.copyFrom(this.buffer, this.pos, i);
        this.pos += i;
        return byteString;
      } 
      return (i == 0) ? ByteString.EMPTY : readBytesSlowPath(i);
    }
    
    public double readDouble() throws IOException {
      return Double.longBitsToDouble(readRawLittleEndian64());
    }
    
    public int readEnum() throws IOException {
      return readRawVarint32();
    }
    
    public int readFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public float readFloat() throws IOException {
      return Float.intBitsToFloat(readRawLittleEndian32());
    }
    
    public <T extends MessageLite> T readGroup(int param1Int, Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
      return (T)messageLite;
    }
    
    public void readGroup(int param1Int, MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
    }
    
    public int readInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readInt64() throws IOException {
      return readRawVarint64();
    }
    
    public <T extends MessageLite> T readMessage(Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
      return (T)messageLite;
    }
    
    public void readMessage(MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
    }
    
    public byte readRawByte() throws IOException {
      if (this.pos == this.bufferSize)
        refillBuffer(1); 
      byte[] arrayOfByte = this.buffer;
      int i = this.pos;
      this.pos = i + 1;
      return arrayOfByte[i];
    }
    
    public byte[] readRawBytes(int param1Int) throws IOException {
      int i = this.pos;
      if (param1Int <= this.bufferSize - i && param1Int > 0) {
        param1Int += i;
        this.pos = param1Int;
        return Arrays.copyOfRange(this.buffer, i, param1Int);
      } 
      return readRawBytesSlowPath(param1Int, false);
    }
    
    public int readRawLittleEndian32() throws IOException {
      int i = this.pos;
      int j = i;
      if (this.bufferSize - i < 4) {
        refillBuffer(4);
        j = this.pos;
      } 
      byte[] arrayOfByte = this.buffer;
      this.pos = j + 4;
      return arrayOfByte[j] & 0xFF | (arrayOfByte[j + 1] & 0xFF) << 8 | (arrayOfByte[j + 2] & 0xFF) << 16 | (arrayOfByte[j + 3] & 0xFF) << 24;
    }
    
    public long readRawLittleEndian64() throws IOException {
      int i = this.pos;
      int j = i;
      if (this.bufferSize - i < 8) {
        refillBuffer(8);
        j = this.pos;
      } 
      byte[] arrayOfByte = this.buffer;
      this.pos = j + 8;
      long l1 = arrayOfByte[j];
      long l2 = arrayOfByte[j + 1];
      long l3 = arrayOfByte[j + 2];
      long l4 = arrayOfByte[j + 3];
      long l5 = arrayOfByte[j + 4];
      long l6 = arrayOfByte[j + 5];
      long l7 = arrayOfByte[j + 6];
      return (arrayOfByte[j + 7] & 0xFFL) << 56L | l1 & 0xFFL | (l2 & 0xFFL) << 8L | (l3 & 0xFFL) << 16L | (l4 & 0xFFL) << 24L | (l5 & 0xFFL) << 32L | (l6 & 0xFFL) << 40L | (l7 & 0xFFL) << 48L;
    }
    
    public int readRawVarint32() throws IOException {
      int i = this.pos;
      if (this.bufferSize != i) {
        byte[] arrayOfByte = this.buffer;
        int j = i + 1;
        byte b = arrayOfByte[i];
        if (b >= 0) {
          this.pos = j;
          return b;
        } 
        if (this.bufferSize - j >= 9) {
          i = j + 1;
          int k = b ^ arrayOfByte[j] << 7;
          if (k < 0) {
            j = k ^ 0xFFFFFF80;
          } else {
            j = i + 1;
            k ^= arrayOfByte[i] << 14;
            if (k >= 0) {
              k ^= 0x3F80;
              i = j;
              j = k;
            } else {
              i = j + 1;
              j = k ^ arrayOfByte[j] << 21;
              if (j < 0) {
                j ^= 0xFFE03F80;
              } else {
                int m = i + 1;
                byte b1 = arrayOfByte[i];
                k = j ^ b1 << 28 ^ 0xFE03F80;
                j = k;
                i = m;
                if (b1 < 0) {
                  int n = m + 1;
                  j = k;
                  i = n;
                  if (arrayOfByte[m] < 0) {
                    int i1 = n + 1;
                    j = k;
                    i = i1;
                    if (arrayOfByte[n] < 0) {
                      m = i1 + 1;
                      j = k;
                      i = m;
                      if (arrayOfByte[i1] < 0) {
                        n = m + 1;
                        j = k;
                        i = n;
                        if (arrayOfByte[m] < 0) {
                          i = n + 1;
                          j = k;
                          if (arrayOfByte[n] < 0)
                            return (int)readRawVarint64SlowPath(); 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.pos = i;
          return j;
        } 
      } 
      return (int)readRawVarint64SlowPath();
    }
    
    public long readRawVarint64() throws IOException {
      int i = this.pos;
      if (this.bufferSize != i) {
        byte[] arrayOfByte = this.buffer;
        int j = i + 1;
        byte b = arrayOfByte[i];
        if (b >= 0) {
          this.pos = j;
          return b;
        } 
        if (this.bufferSize - j >= 9) {
          long l;
          i = j + 1;
          int k = b ^ arrayOfByte[j] << 7;
          if (k < 0) {
            l = (k ^ 0xFFFFFF80);
          } else {
            j = i + 1;
            k ^= arrayOfByte[i] << 14;
            if (k >= 0) {
              l = (k ^ 0x3F80);
              i = j;
            } else {
              i = j + 1;
              j = k ^ arrayOfByte[j] << 21;
              if (j < 0) {
                l = (j ^ 0xFFE03F80);
              } else {
                l = j;
                j = i + 1;
                l = arrayOfByte[i] << 28L ^ l;
                if (l >= 0L) {
                  l ^= 0xFE03F80L;
                  i = j;
                } else {
                  i = j + 1;
                  l ^= arrayOfByte[j] << 35L;
                  if (l < 0L) {
                    l = 0xFFFFFFF80FE03F80L ^ l;
                  } else {
                    j = i + 1;
                    l ^= arrayOfByte[i] << 42L;
                    if (l >= 0L) {
                      l ^= 0x3F80FE03F80L;
                      i = j;
                    } else {
                      i = j + 1;
                      l ^= arrayOfByte[j] << 49L;
                      if (l < 0L) {
                        l = 0xFFFE03F80FE03F80L ^ l;
                      } else {
                        j = i + 1;
                        l = l ^ arrayOfByte[i] << 56L ^ 0xFE03F80FE03F80L;
                        if (l < 0L) {
                          i = j + 1;
                          if (arrayOfByte[j] < 0L)
                            return readRawVarint64SlowPath(); 
                        } else {
                          i = j;
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.pos = i;
          return l;
        } 
      } 
      return readRawVarint64SlowPath();
    }
    
    long readRawVarint64SlowPath() throws IOException {
      long l = 0L;
      for (byte b = 0; b < 64; b += 7) {
        byte b1 = readRawByte();
        l |= (b1 & Byte.MAX_VALUE) << b;
        if ((b1 & 0x80) == 0)
          return l; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    public int readSFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readSFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public int readSInt32() throws IOException {
      return decodeZigZag32(readRawVarint32());
    }
    
    public long readSInt64() throws IOException {
      return decodeZigZag64(readRawVarint64());
    }
    
    public String readString() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= this.bufferSize - this.pos) {
        String str = new String(this.buffer, this.pos, i, Internal.UTF_8);
        this.pos += i;
        return str;
      } 
      if (i == 0)
        return ""; 
      if (i <= this.bufferSize) {
        refillBuffer(i);
        String str = new String(this.buffer, this.pos, i, Internal.UTF_8);
        this.pos += i;
        return str;
      } 
      return new String(readRawBytesSlowPath(i, false), Internal.UTF_8);
    }
    
    public String readStringRequireUtf8() throws IOException {
      byte[] arrayOfByte;
      int i = readRawVarint32();
      int j = this.pos;
      int k = this.bufferSize;
      boolean bool = false;
      if (i <= k - j && i > 0) {
        arrayOfByte = this.buffer;
        this.pos = j + i;
      } else {
        if (i == 0)
          return ""; 
        if (i <= this.bufferSize) {
          refillBuffer(i);
          arrayOfByte = this.buffer;
          this.pos = i + 0;
          j = bool;
        } else {
          arrayOfByte = readRawBytesSlowPath(i, false);
          j = bool;
        } 
      } 
      return Utf8.decodeUtf8(arrayOfByte, j, i);
    }
    
    public int readTag() throws IOException {
      if (isAtEnd()) {
        this.lastTag = 0;
        return 0;
      } 
      this.lastTag = readRawVarint32();
      if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
        throw InvalidProtocolBufferException.invalidTag(); 
      return this.lastTag;
    }
    
    public int readUInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readUInt64() throws IOException {
      return readRawVarint64();
    }
    
    @Deprecated
    public void readUnknownGroup(int param1Int, MessageLite.Builder param1Builder) throws IOException {
      readGroup(param1Int, param1Builder, ExtensionRegistryLite.getEmptyRegistry());
    }
    
    public void resetSizeCounter() {
      this.totalBytesRetired = -this.pos;
    }
    
    public boolean skipField(int param1Int) throws IOException {
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          skipRawBytes(4);
          return true;
        case 4:
          return false;
        case 3:
          skipMessage();
          checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4));
          return true;
        case 2:
          skipRawBytes(readRawVarint32());
          return true;
        case 1:
          skipRawBytes(8);
          return true;
        case 0:
          break;
      } 
      skipRawVarint();
      return true;
    }
    
    public boolean skipField(int param1Int, CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      ByteString byteString;
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          i = readRawLittleEndian32();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed32NoTag(i);
          return true;
        case 4:
          return false;
        case 3:
          param1CodedOutputStream.writeRawVarint32(param1Int);
          skipMessage(param1CodedOutputStream);
          param1Int = WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4);
          checkLastTagWas(param1Int);
          param1CodedOutputStream.writeRawVarint32(param1Int);
          return true;
        case 2:
          byteString = readBytes();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeBytesNoTag(byteString);
          return true;
        case 1:
          l = readRawLittleEndian64();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed64NoTag(l);
          return true;
        case 0:
          break;
      } 
      long l = readInt64();
      param1CodedOutputStream.writeRawVarint32(param1Int);
      param1CodedOutputStream.writeUInt64NoTag(l);
      return true;
    }
    
    public void skipMessage() throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i));
    }
    
    public void skipMessage(CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i, param1CodedOutputStream));
    }
    
    public void skipRawBytes(int param1Int) throws IOException {
      if (param1Int <= this.bufferSize - this.pos && param1Int >= 0) {
        this.pos += param1Int;
      } else {
        skipRawBytesSlowPath(param1Int);
      } 
    }
    
    private static interface RefillCallback {
      void onRefill();
    }
    
    private class SkippedDataSink implements RefillCallback {
      private ByteArrayOutputStream byteArrayStream;
      
      private int lastPos = CodedInputStream.StreamDecoder.this.pos;
      
      ByteBuffer getSkippedData() {
        if (this.byteArrayStream == null)
          return ByteBuffer.wrap(CodedInputStream.StreamDecoder.this.buffer, this.lastPos, CodedInputStream.StreamDecoder.this.pos - this.lastPos); 
        this.byteArrayStream.write(CodedInputStream.StreamDecoder.this.buffer, this.lastPos, CodedInputStream.StreamDecoder.this.pos);
        return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
      }
      
      public void onRefill() {
        if (this.byteArrayStream == null)
          this.byteArrayStream = new ByteArrayOutputStream(); 
        this.byteArrayStream.write(CodedInputStream.StreamDecoder.this.buffer, this.lastPos, CodedInputStream.StreamDecoder.this.pos - this.lastPos);
        this.lastPos = 0;
      }
    }
  }
  
  private static interface RefillCallback {
    void onRefill();
  }
  
  private class SkippedDataSink implements StreamDecoder.RefillCallback {
    private ByteArrayOutputStream byteArrayStream;
    
    private int lastPos = this.this$0.pos;
    
    ByteBuffer getSkippedData() {
      if (this.byteArrayStream == null)
        return ByteBuffer.wrap(this.this$0.buffer, this.lastPos, this.this$0.pos - this.lastPos); 
      this.byteArrayStream.write(this.this$0.buffer, this.lastPos, this.this$0.pos);
      return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
    }
    
    public void onRefill() {
      if (this.byteArrayStream == null)
        this.byteArrayStream = new ByteArrayOutputStream(); 
      this.byteArrayStream.write(this.this$0.buffer, this.lastPos, this.this$0.pos - this.lastPos);
      this.lastPos = 0;
    }
  }
  
  private static final class UnsafeDirectNioDecoder extends CodedInputStream {
    private final long address;
    
    private final ByteBuffer buffer;
    
    private int bufferSizeAfterLimit;
    
    private int currentLimit = Integer.MAX_VALUE;
    
    private boolean enableAliasing;
    
    private final boolean immutable;
    
    private int lastTag;
    
    private long limit;
    
    private long pos;
    
    private long startPos;
    
    private UnsafeDirectNioDecoder(ByteBuffer param1ByteBuffer, boolean param1Boolean) {
      this.buffer = param1ByteBuffer;
      this.address = UnsafeUtil.addressOffset(param1ByteBuffer);
      this.limit = this.address + param1ByteBuffer.limit();
      this.pos = this.address + param1ByteBuffer.position();
      this.startPos = this.pos;
      this.immutable = param1Boolean;
    }
    
    private int bufferPos(long param1Long) {
      return (int)(param1Long - this.address);
    }
    
    static boolean isSupported() {
      return UnsafeUtil.hasUnsafeByteBufferOperations();
    }
    
    private void recomputeBufferSizeAfterLimit() {
      this.limit += this.bufferSizeAfterLimit;
      int i = (int)(this.limit - this.startPos);
      if (i > this.currentLimit) {
        this.bufferSizeAfterLimit = i - this.currentLimit;
        this.limit -= this.bufferSizeAfterLimit;
      } else {
        this.bufferSizeAfterLimit = 0;
      } 
    }
    
    private int remaining() {
      return (int)(this.limit - this.pos);
    }
    
    private void skipRawVarint() throws IOException {
      if (remaining() >= 10) {
        skipRawVarintFastPath();
      } else {
        skipRawVarintSlowPath();
      } 
    }
    
    private void skipRawVarintFastPath() throws IOException {
      for (byte b = 0; b < 10; b++) {
        long l = this.pos;
        this.pos = 1L + l;
        if (UnsafeUtil.getByte(l) >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private void skipRawVarintSlowPath() throws IOException {
      for (byte b = 0; b < 10; b++) {
        if (readRawByte() >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private ByteBuffer slice(long param1Long1, long param1Long2) throws IOException {
      Exception exception;
      int i = this.buffer.position();
      int j = this.buffer.limit();
      try {
        this.buffer.position(bufferPos(param1Long1));
        this.buffer.limit(bufferPos(param1Long2));
        ByteBuffer byteBuffer = this.buffer.slice();
        this.buffer.position(i);
        this.buffer.limit(j);
        return byteBuffer;
      } catch (IllegalArgumentException null) {
        throw InvalidProtocolBufferException.truncatedMessage();
      } finally {}
      this.buffer.position(i);
      this.buffer.limit(j);
      throw exception;
    }
    
    public void checkLastTagWas(int param1Int) throws InvalidProtocolBufferException {
      if (this.lastTag != param1Int)
        throw InvalidProtocolBufferException.invalidEndTag(); 
    }
    
    public void enableAliasing(boolean param1Boolean) {
      this.enableAliasing = param1Boolean;
    }
    
    public int getBytesUntilLimit() {
      return (this.currentLimit == Integer.MAX_VALUE) ? -1 : (this.currentLimit - getTotalBytesRead());
    }
    
    public int getLastTag() {
      return this.lastTag;
    }
    
    public int getTotalBytesRead() {
      return (int)(this.pos - this.startPos);
    }
    
    public boolean isAtEnd() throws IOException {
      boolean bool;
      if (this.pos == this.limit) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void popLimit(int param1Int) {
      this.currentLimit = param1Int;
      recomputeBufferSizeAfterLimit();
    }
    
    public int pushLimit(int param1Int) throws InvalidProtocolBufferException {
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      int i = param1Int + getTotalBytesRead();
      param1Int = this.currentLimit;
      if (i > param1Int)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      this.currentLimit = i;
      recomputeBufferSizeAfterLimit();
      return param1Int;
    }
    
    public boolean readBool() throws IOException {
      boolean bool;
      if (readRawVarint64() != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public byte[] readByteArray() throws IOException {
      return readRawBytes(readRawVarint32());
    }
    
    public ByteBuffer readByteBuffer() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= remaining()) {
        if (!this.immutable && this.enableAliasing) {
          long l3 = this.pos;
          long l4 = this.pos;
          long l5 = i;
          ByteBuffer byteBuffer = slice(l3, l4 + l5);
          this.pos += l5;
          return byteBuffer;
        } 
        byte[] arrayOfByte = new byte[i];
        long l2 = this.pos;
        long l1 = i;
        UnsafeUtil.copyMemory(l2, arrayOfByte, 0L, l1);
        this.pos += l1;
        return ByteBuffer.wrap(arrayOfByte);
      } 
      if (i == 0)
        return Internal.EMPTY_BYTE_BUFFER; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public ByteString readBytes() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= remaining()) {
        if (this.immutable && this.enableAliasing) {
          long l3 = this.pos;
          long l4 = this.pos;
          long l5 = i;
          ByteBuffer byteBuffer = slice(l3, l4 + l5);
          this.pos += l5;
          return ByteString.wrap(byteBuffer);
        } 
        byte[] arrayOfByte = new byte[i];
        long l1 = this.pos;
        long l2 = i;
        UnsafeUtil.copyMemory(l1, arrayOfByte, 0L, l2);
        this.pos += l2;
        return ByteString.wrap(arrayOfByte);
      } 
      if (i == 0)
        return ByteString.EMPTY; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public double readDouble() throws IOException {
      return Double.longBitsToDouble(readRawLittleEndian64());
    }
    
    public int readEnum() throws IOException {
      return readRawVarint32();
    }
    
    public int readFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public float readFloat() throws IOException {
      return Float.intBitsToFloat(readRawLittleEndian32());
    }
    
    public <T extends MessageLite> T readGroup(int param1Int, Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
      return (T)messageLite;
    }
    
    public void readGroup(int param1Int, MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(WireFormat.makeTag(param1Int, 4));
      this.recursionDepth--;
    }
    
    public int readInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readInt64() throws IOException {
      return readRawVarint64();
    }
    
    public <T extends MessageLite> T readMessage(Parser<T> param1Parser, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      MessageLite messageLite = (MessageLite)param1Parser.parsePartialFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
      return (T)messageLite;
    }
    
    public void readMessage(MessageLite.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readRawVarint32();
      if (this.recursionDepth >= this.recursionLimit)
        throw InvalidProtocolBufferException.recursionLimitExceeded(); 
      i = pushLimit(i);
      this.recursionDepth++;
      param1Builder.mergeFrom(this, param1ExtensionRegistryLite);
      checkLastTagWas(0);
      this.recursionDepth--;
      popLimit(i);
    }
    
    public byte readRawByte() throws IOException {
      if (this.pos == this.limit)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      long l = this.pos;
      this.pos = 1L + l;
      return UnsafeUtil.getByte(l);
    }
    
    public byte[] readRawBytes(int param1Int) throws IOException {
      if (param1Int >= 0 && param1Int <= remaining()) {
        byte[] arrayOfByte = new byte[param1Int];
        long l1 = this.pos;
        long l2 = this.pos;
        long l3 = param1Int;
        slice(l1, l2 + l3).get(arrayOfByte);
        this.pos += l3;
        return arrayOfByte;
      } 
      if (param1Int <= 0) {
        if (param1Int == 0)
          return Internal.EMPTY_BYTE_ARRAY; 
        throw InvalidProtocolBufferException.negativeSize();
      } 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public int readRawLittleEndian32() throws IOException {
      long l = this.pos;
      if (this.limit - l < 4L)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      this.pos = 4L + l;
      return UnsafeUtil.getByte(l) & 0xFF | (UnsafeUtil.getByte(1L + l) & 0xFF) << 8 | (UnsafeUtil.getByte(2L + l) & 0xFF) << 16 | (UnsafeUtil.getByte(l + 3L) & 0xFF) << 24;
    }
    
    public long readRawLittleEndian64() throws IOException {
      long l1 = this.pos;
      if (this.limit - l1 < 8L)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      this.pos = 8L + l1;
      long l2 = UnsafeUtil.getByte(l1);
      long l3 = UnsafeUtil.getByte(1L + l1);
      long l4 = UnsafeUtil.getByte(2L + l1);
      long l5 = UnsafeUtil.getByte(3L + l1);
      long l6 = UnsafeUtil.getByte(4L + l1);
      long l7 = UnsafeUtil.getByte(5L + l1);
      long l8 = UnsafeUtil.getByte(6L + l1);
      return (UnsafeUtil.getByte(l1 + 7L) & 0xFFL) << 56L | l2 & 0xFFL | (l3 & 0xFFL) << 8L | (l4 & 0xFFL) << 16L | (l5 & 0xFFL) << 24L | (l6 & 0xFFL) << 32L | (l7 & 0xFFL) << 40L | (l8 & 0xFFL) << 48L;
    }
    
    public int readRawVarint32() throws IOException {
      long l = this.pos;
      if (this.limit != l) {
        long l1 = l + 1L;
        byte b = UnsafeUtil.getByte(l);
        if (b >= 0) {
          this.pos = l1;
          return b;
        } 
        if (this.limit - l1 >= 9L) {
          l = l1 + 1L;
          int i = b ^ UnsafeUtil.getByte(l1) << 7;
          if (i < 0) {
            i ^= 0xFFFFFF80;
            l1 = l;
          } else {
            l1 = l + 1L;
            i ^= UnsafeUtil.getByte(l) << 14;
            if (i >= 0) {
              i ^= 0x3F80;
            } else {
              long l2 = l1 + 1L;
              i ^= UnsafeUtil.getByte(l1) << 21;
              if (i < 0) {
                i ^= 0xFFE03F80;
                l1 = l2;
              } else {
                l = l2 + 1L;
                byte b1 = UnsafeUtil.getByte(l2);
                int j = i ^ b1 << 28 ^ 0xFE03F80;
                i = j;
                l1 = l;
                if (b1 < 0) {
                  l2 = l + 1L;
                  i = j;
                  l1 = l2;
                  if (UnsafeUtil.getByte(l) < 0) {
                    l = l2 + 1L;
                    i = j;
                    l1 = l;
                    if (UnsafeUtil.getByte(l2) < 0) {
                      l2 = l + 1L;
                      i = j;
                      l1 = l2;
                      if (UnsafeUtil.getByte(l) < 0) {
                        l = l2 + 1L;
                        i = j;
                        l1 = l;
                        if (UnsafeUtil.getByte(l2) < 0) {
                          l1 = l + 1L;
                          i = j;
                          if (UnsafeUtil.getByte(l) < 0)
                            return (int)readRawVarint64SlowPath(); 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.pos = l1;
          return i;
        } 
      } 
      return (int)readRawVarint64SlowPath();
    }
    
    public long readRawVarint64() throws IOException {
      long l = this.pos;
      if (this.limit != l) {
        long l1 = l + 1L;
        byte b = UnsafeUtil.getByte(l);
        if (b >= 0) {
          this.pos = l1;
          return b;
        } 
        if (this.limit - l1 >= 9L) {
          l = l1 + 1L;
          int i = b ^ UnsafeUtil.getByte(l1) << 7;
          if (i < 0) {
            long l2 = (i ^ 0xFFFFFF80);
            l1 = l;
            l = l2;
          } else {
            l1 = l + 1L;
            i ^= UnsafeUtil.getByte(l) << 14;
            if (i >= 0) {
              l = (i ^ 0x3F80);
            } else {
              long l2 = l1 + 1L;
              i ^= UnsafeUtil.getByte(l1) << 21;
              if (i < 0) {
                l = (i ^ 0xFFE03F80);
                l1 = l2;
              } else {
                l = i;
                l1 = l2 + 1L;
                l2 = l ^ UnsafeUtil.getByte(l2) << 28L;
                if (l2 >= 0L) {
                  l = l2 ^ 0xFE03F80L;
                } else {
                  l = l1 + 1L;
                  l1 = l2 ^ UnsafeUtil.getByte(l1) << 35L;
                  if (l1 < 0L) {
                    l2 = l1 ^ 0xFFFFFFF80FE03F80L;
                    l1 = l;
                    l = l2;
                  } else {
                    l2 = l + 1L;
                    l = l1 ^ UnsafeUtil.getByte(l) << 42L;
                    if (l >= 0L) {
                      l ^= 0x3F80FE03F80L;
                      l1 = l2;
                    } else {
                      l1 = l2 + 1L;
                      l ^= UnsafeUtil.getByte(l2) << 49L;
                      if (l < 0L) {
                        l ^= 0xFFFE03F80FE03F80L;
                      } else {
                        l2 = l1 + 1L;
                        long l3 = l ^ UnsafeUtil.getByte(l1) << 56L ^ 0xFE03F80FE03F80L;
                        l = l3;
                        l1 = l2;
                        if (l3 < 0L) {
                          if (UnsafeUtil.getByte(l2) < 0L)
                            return readRawVarint64SlowPath(); 
                          l1 = 1L + l2;
                          l = l3;
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          this.pos = l1;
          return l;
        } 
      } 
      return readRawVarint64SlowPath();
    }
    
    long readRawVarint64SlowPath() throws IOException {
      long l = 0L;
      for (byte b = 0; b < 64; b += 7) {
        byte b1 = readRawByte();
        l |= (b1 & Byte.MAX_VALUE) << b;
        if ((b1 & 0x80) == 0)
          return l; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    public int readSFixed32() throws IOException {
      return readRawLittleEndian32();
    }
    
    public long readSFixed64() throws IOException {
      return readRawLittleEndian64();
    }
    
    public int readSInt32() throws IOException {
      return decodeZigZag32(readRawVarint32());
    }
    
    public long readSInt64() throws IOException {
      return decodeZigZag64(readRawVarint64());
    }
    
    public String readString() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= remaining()) {
        byte[] arrayOfByte = new byte[i];
        long l1 = this.pos;
        long l2 = i;
        UnsafeUtil.copyMemory(l1, arrayOfByte, 0L, l2);
        String str = new String(arrayOfByte, Internal.UTF_8);
        this.pos += l2;
        return str;
      } 
      if (i == 0)
        return ""; 
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public String readStringRequireUtf8() throws IOException {
      int i = readRawVarint32();
      if (i > 0 && i <= remaining()) {
        int j = bufferPos(this.pos);
        String str = Utf8.decodeUtf8(this.buffer, j, i);
        this.pos += i;
        return str;
      } 
      if (i == 0)
        return ""; 
      if (i <= 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    
    public int readTag() throws IOException {
      if (isAtEnd()) {
        this.lastTag = 0;
        return 0;
      } 
      this.lastTag = readRawVarint32();
      if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
        throw InvalidProtocolBufferException.invalidTag(); 
      return this.lastTag;
    }
    
    public int readUInt32() throws IOException {
      return readRawVarint32();
    }
    
    public long readUInt64() throws IOException {
      return readRawVarint64();
    }
    
    @Deprecated
    public void readUnknownGroup(int param1Int, MessageLite.Builder param1Builder) throws IOException {
      readGroup(param1Int, param1Builder, ExtensionRegistryLite.getEmptyRegistry());
    }
    
    public void resetSizeCounter() {
      this.startPos = this.pos;
    }
    
    public boolean skipField(int param1Int) throws IOException {
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          skipRawBytes(4);
          return true;
        case 4:
          return false;
        case 3:
          skipMessage();
          checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4));
          return true;
        case 2:
          skipRawBytes(readRawVarint32());
          return true;
        case 1:
          skipRawBytes(8);
          return true;
        case 0:
          break;
      } 
      skipRawVarint();
      return true;
    }
    
    public boolean skipField(int param1Int, CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      ByteString byteString;
      switch (WireFormat.getTagWireType(param1Int)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 5:
          i = readRawLittleEndian32();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed32NoTag(i);
          return true;
        case 4:
          return false;
        case 3:
          param1CodedOutputStream.writeRawVarint32(param1Int);
          skipMessage(param1CodedOutputStream);
          param1Int = WireFormat.makeTag(WireFormat.getTagFieldNumber(param1Int), 4);
          checkLastTagWas(param1Int);
          param1CodedOutputStream.writeRawVarint32(param1Int);
          return true;
        case 2:
          byteString = readBytes();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeBytesNoTag(byteString);
          return true;
        case 1:
          l = readRawLittleEndian64();
          param1CodedOutputStream.writeRawVarint32(param1Int);
          param1CodedOutputStream.writeFixed64NoTag(l);
          return true;
        case 0:
          break;
      } 
      long l = readInt64();
      param1CodedOutputStream.writeRawVarint32(param1Int);
      param1CodedOutputStream.writeUInt64NoTag(l);
      return true;
    }
    
    public void skipMessage() throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i));
    }
    
    public void skipMessage(CodedOutputStream param1CodedOutputStream) throws IOException {
      int i;
      do {
        i = readTag();
      } while (i != 0 && skipField(i, param1CodedOutputStream));
    }
    
    public void skipRawBytes(int param1Int) throws IOException {
      if (param1Int >= 0 && param1Int <= remaining()) {
        this.pos += param1Int;
        return;
      } 
      if (param1Int < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      throw InvalidProtocolBufferException.truncatedMessage();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\CodedInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */