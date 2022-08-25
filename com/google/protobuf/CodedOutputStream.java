package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CodedOutputStream extends ByteOutput {
  public static final int DEFAULT_BUFFER_SIZE = 4096;
  
  private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS;
  
  @Deprecated
  public static final int LITTLE_ENDIAN_32_SIZE = 4;
  
  private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());
  
  private boolean serializationDeterministic;
  
  CodedOutputStreamWriter wrapper;
  
  static {
    HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.hasUnsafeArrayOperations();
  }
  
  private CodedOutputStream() {}
  
  public static int computeBoolSize(int paramInt, boolean paramBoolean) {
    return computeTagSize(paramInt) + computeBoolSizeNoTag(paramBoolean);
  }
  
  public static int computeBoolSizeNoTag(boolean paramBoolean) {
    return 1;
  }
  
  public static int computeByteArraySize(int paramInt, byte[] paramArrayOfbyte) {
    return computeTagSize(paramInt) + computeByteArraySizeNoTag(paramArrayOfbyte);
  }
  
  public static int computeByteArraySizeNoTag(byte[] paramArrayOfbyte) {
    return computeLengthDelimitedFieldSize(paramArrayOfbyte.length);
  }
  
  public static int computeByteBufferSize(int paramInt, ByteBuffer paramByteBuffer) {
    return computeTagSize(paramInt) + computeByteBufferSizeNoTag(paramByteBuffer);
  }
  
  public static int computeByteBufferSizeNoTag(ByteBuffer paramByteBuffer) {
    return computeLengthDelimitedFieldSize(paramByteBuffer.capacity());
  }
  
  public static int computeBytesSize(int paramInt, ByteString paramByteString) {
    return computeTagSize(paramInt) + computeBytesSizeNoTag(paramByteString);
  }
  
  public static int computeBytesSizeNoTag(ByteString paramByteString) {
    return computeLengthDelimitedFieldSize(paramByteString.size());
  }
  
  public static int computeDoubleSize(int paramInt, double paramDouble) {
    return computeTagSize(paramInt) + computeDoubleSizeNoTag(paramDouble);
  }
  
  public static int computeDoubleSizeNoTag(double paramDouble) {
    return 8;
  }
  
  public static int computeEnumSize(int paramInt1, int paramInt2) {
    return computeTagSize(paramInt1) + computeEnumSizeNoTag(paramInt2);
  }
  
  public static int computeEnumSizeNoTag(int paramInt) {
    return computeInt32SizeNoTag(paramInt);
  }
  
  public static int computeFixed32Size(int paramInt1, int paramInt2) {
    return computeTagSize(paramInt1) + computeFixed32SizeNoTag(paramInt2);
  }
  
  public static int computeFixed32SizeNoTag(int paramInt) {
    return 4;
  }
  
  public static int computeFixed64Size(int paramInt, long paramLong) {
    return computeTagSize(paramInt) + computeFixed64SizeNoTag(paramLong);
  }
  
  public static int computeFixed64SizeNoTag(long paramLong) {
    return 8;
  }
  
  public static int computeFloatSize(int paramInt, float paramFloat) {
    return computeTagSize(paramInt) + computeFloatSizeNoTag(paramFloat);
  }
  
  public static int computeFloatSizeNoTag(float paramFloat) {
    return 4;
  }
  
  @Deprecated
  public static int computeGroupSize(int paramInt, MessageLite paramMessageLite) {
    return computeTagSize(paramInt) * 2 + computeGroupSizeNoTag(paramMessageLite);
  }
  
  @Deprecated
  static int computeGroupSize(int paramInt, MessageLite paramMessageLite, Schema paramSchema) {
    return computeTagSize(paramInt) * 2 + computeGroupSizeNoTag(paramMessageLite, paramSchema);
  }
  
  @Deprecated
  public static int computeGroupSizeNoTag(MessageLite paramMessageLite) {
    return paramMessageLite.getSerializedSize();
  }
  
  @Deprecated
  static int computeGroupSizeNoTag(MessageLite paramMessageLite, Schema paramSchema) {
    return ((AbstractMessageLite)paramMessageLite).getSerializedSize(paramSchema);
  }
  
  public static int computeInt32Size(int paramInt1, int paramInt2) {
    return computeTagSize(paramInt1) + computeInt32SizeNoTag(paramInt2);
  }
  
  public static int computeInt32SizeNoTag(int paramInt) {
    return (paramInt >= 0) ? computeUInt32SizeNoTag(paramInt) : 10;
  }
  
  public static int computeInt64Size(int paramInt, long paramLong) {
    return computeTagSize(paramInt) + computeInt64SizeNoTag(paramLong);
  }
  
  public static int computeInt64SizeNoTag(long paramLong) {
    return computeUInt64SizeNoTag(paramLong);
  }
  
  public static int computeLazyFieldMessageSetExtensionSize(int paramInt, LazyFieldLite paramLazyFieldLite) {
    return computeTagSize(1) * 2 + computeUInt32Size(2, paramInt) + computeLazyFieldSize(3, paramLazyFieldLite);
  }
  
  public static int computeLazyFieldSize(int paramInt, LazyFieldLite paramLazyFieldLite) {
    return computeTagSize(paramInt) + computeLazyFieldSizeNoTag(paramLazyFieldLite);
  }
  
  public static int computeLazyFieldSizeNoTag(LazyFieldLite paramLazyFieldLite) {
    return computeLengthDelimitedFieldSize(paramLazyFieldLite.getSerializedSize());
  }
  
  static int computeLengthDelimitedFieldSize(int paramInt) {
    return computeUInt32SizeNoTag(paramInt) + paramInt;
  }
  
  public static int computeMessageSetExtensionSize(int paramInt, MessageLite paramMessageLite) {
    return computeTagSize(1) * 2 + computeUInt32Size(2, paramInt) + computeMessageSize(3, paramMessageLite);
  }
  
  public static int computeMessageSize(int paramInt, MessageLite paramMessageLite) {
    return computeTagSize(paramInt) + computeMessageSizeNoTag(paramMessageLite);
  }
  
  static int computeMessageSize(int paramInt, MessageLite paramMessageLite, Schema paramSchema) {
    return computeTagSize(paramInt) + computeMessageSizeNoTag(paramMessageLite, paramSchema);
  }
  
  public static int computeMessageSizeNoTag(MessageLite paramMessageLite) {
    return computeLengthDelimitedFieldSize(paramMessageLite.getSerializedSize());
  }
  
  static int computeMessageSizeNoTag(MessageLite paramMessageLite, Schema paramSchema) {
    return computeLengthDelimitedFieldSize(((AbstractMessageLite)paramMessageLite).getSerializedSize(paramSchema));
  }
  
  static int computePreferredBufferSize(int paramInt) {
    return (paramInt > 4096) ? 4096 : paramInt;
  }
  
  public static int computeRawMessageSetExtensionSize(int paramInt, ByteString paramByteString) {
    return computeTagSize(1) * 2 + computeUInt32Size(2, paramInt) + computeBytesSize(3, paramByteString);
  }
  
  @Deprecated
  public static int computeRawVarint32Size(int paramInt) {
    return computeUInt32SizeNoTag(paramInt);
  }
  
  @Deprecated
  public static int computeRawVarint64Size(long paramLong) {
    return computeUInt64SizeNoTag(paramLong);
  }
  
  public static int computeSFixed32Size(int paramInt1, int paramInt2) {
    return computeTagSize(paramInt1) + computeSFixed32SizeNoTag(paramInt2);
  }
  
  public static int computeSFixed32SizeNoTag(int paramInt) {
    return 4;
  }
  
  public static int computeSFixed64Size(int paramInt, long paramLong) {
    return computeTagSize(paramInt) + computeSFixed64SizeNoTag(paramLong);
  }
  
  public static int computeSFixed64SizeNoTag(long paramLong) {
    return 8;
  }
  
  public static int computeSInt32Size(int paramInt1, int paramInt2) {
    return computeTagSize(paramInt1) + computeSInt32SizeNoTag(paramInt2);
  }
  
  public static int computeSInt32SizeNoTag(int paramInt) {
    return computeUInt32SizeNoTag(encodeZigZag32(paramInt));
  }
  
  public static int computeSInt64Size(int paramInt, long paramLong) {
    return computeTagSize(paramInt) + computeSInt64SizeNoTag(paramLong);
  }
  
  public static int computeSInt64SizeNoTag(long paramLong) {
    return computeUInt64SizeNoTag(encodeZigZag64(paramLong));
  }
  
  public static int computeStringSize(int paramInt, String paramString) {
    return computeTagSize(paramInt) + computeStringSizeNoTag(paramString);
  }
  
  public static int computeStringSizeNoTag(String paramString) {
    int i;
    try {
      i = Utf8.encodedLength(paramString);
    } catch (UnpairedSurrogateException unpairedSurrogateException) {
      i = (paramString.getBytes(Internal.UTF_8)).length;
    } 
    return computeLengthDelimitedFieldSize(i);
  }
  
  public static int computeTagSize(int paramInt) {
    return computeUInt32SizeNoTag(WireFormat.makeTag(paramInt, 0));
  }
  
  public static int computeUInt32Size(int paramInt1, int paramInt2) {
    return computeTagSize(paramInt1) + computeUInt32SizeNoTag(paramInt2);
  }
  
  public static int computeUInt32SizeNoTag(int paramInt) {
    return ((paramInt & 0xFFFFFF80) == 0) ? 1 : (((paramInt & 0xFFFFC000) == 0) ? 2 : (((0xFFE00000 & paramInt) == 0) ? 3 : (((paramInt & 0xF0000000) == 0) ? 4 : 5)));
  }
  
  public static int computeUInt64Size(int paramInt, long paramLong) {
    return computeTagSize(paramInt) + computeUInt64SizeNoTag(paramLong);
  }
  
  public static int computeUInt64SizeNoTag(long paramLong) {
    if ((0xFFFFFFFFFFFFFF80L & paramLong) == 0L)
      return 1; 
    if (paramLong < 0L)
      return 10; 
    if ((0xFFFFFFF800000000L & paramLong) != 0L) {
      i = 6;
      paramLong >>>= 28L;
    } else {
      i = 2;
    } 
    int j = i;
    long l = paramLong;
    if ((0xFFFFFFFFFFE00000L & paramLong) != 0L) {
      j = i + 2;
      l = paramLong >>> 14L;
    } 
    int i = j;
    if ((l & 0xFFFFFFFFFFFFC000L) != 0L)
      i = j + 1; 
    return i;
  }
  
  public static int encodeZigZag32(int paramInt) {
    return paramInt >> 31 ^ paramInt << 1;
  }
  
  public static long encodeZigZag64(long paramLong) {
    return paramLong >> 63L ^ paramLong << 1L;
  }
  
  static CodedOutputStream newInstance(ByteOutput paramByteOutput, int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentException("bufferSize must be positive"); 
    return new ByteOutputEncoder(paramByteOutput, paramInt);
  }
  
  public static CodedOutputStream newInstance(OutputStream paramOutputStream) {
    return newInstance(paramOutputStream, 4096);
  }
  
  public static CodedOutputStream newInstance(OutputStream paramOutputStream, int paramInt) {
    return new OutputStreamEncoder(paramOutputStream, paramInt);
  }
  
  public static CodedOutputStream newInstance(ByteBuffer paramByteBuffer) {
    if (paramByteBuffer.hasArray())
      return new HeapNioEncoder(paramByteBuffer); 
    if (paramByteBuffer.isDirect() && !paramByteBuffer.isReadOnly()) {
      CodedOutputStream codedOutputStream;
      if (UnsafeDirectNioEncoder.isSupported()) {
        codedOutputStream = newUnsafeInstance(paramByteBuffer);
      } else {
        codedOutputStream = newSafeInstance((ByteBuffer)codedOutputStream);
      } 
      return codedOutputStream;
    } 
    throw new IllegalArgumentException("ByteBuffer is read-only");
  }
  
  @Deprecated
  public static CodedOutputStream newInstance(ByteBuffer paramByteBuffer, int paramInt) {
    return newInstance(paramByteBuffer);
  }
  
  public static CodedOutputStream newInstance(byte[] paramArrayOfbyte) {
    return newInstance(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static CodedOutputStream newInstance(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return new ArrayEncoder(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  static CodedOutputStream newSafeInstance(ByteBuffer paramByteBuffer) {
    return new SafeDirectNioEncoder(paramByteBuffer);
  }
  
  static CodedOutputStream newUnsafeInstance(ByteBuffer paramByteBuffer) {
    return new UnsafeDirectNioEncoder(paramByteBuffer);
  }
  
  public final void checkNoSpaceLeft() {
    if (spaceLeft() != 0)
      throw new IllegalStateException("Did not write as much data as expected."); 
  }
  
  public abstract void flush() throws IOException;
  
  public abstract int getTotalBytesWritten();
  
  final void inefficientWriteStringNoTag(String paramString, Utf8.UnpairedSurrogateException paramUnpairedSurrogateException) throws IOException {
    logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", paramUnpairedSurrogateException);
    byte[] arrayOfByte = paramString.getBytes(Internal.UTF_8);
    try {
      writeUInt32NoTag(arrayOfByte.length);
      writeLazy(arrayOfByte, 0, arrayOfByte.length);
      return;
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw new OutOfSpaceException(indexOutOfBoundsException);
    } catch (OutOfSpaceException outOfSpaceException) {
      throw outOfSpaceException;
    } 
  }
  
  boolean isSerializationDeterministic() {
    return this.serializationDeterministic;
  }
  
  public abstract int spaceLeft();
  
  public void useDeterministicSerialization() {
    this.serializationDeterministic = true;
  }
  
  public abstract void write(byte paramByte) throws IOException;
  
  public abstract void write(ByteBuffer paramByteBuffer) throws IOException;
  
  public abstract void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeBool(int paramInt, boolean paramBoolean) throws IOException;
  
  public final void writeBoolNoTag(boolean paramBoolean) throws IOException {
    write((byte)paramBoolean);
  }
  
  public abstract void writeByteArray(int paramInt, byte[] paramArrayOfbyte) throws IOException;
  
  public abstract void writeByteArray(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) throws IOException;
  
  public final void writeByteArrayNoTag(byte[] paramArrayOfbyte) throws IOException {
    writeByteArrayNoTag(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  abstract void writeByteArrayNoTag(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeByteBuffer(int paramInt, ByteBuffer paramByteBuffer) throws IOException;
  
  public abstract void writeBytes(int paramInt, ByteString paramByteString) throws IOException;
  
  public abstract void writeBytesNoTag(ByteString paramByteString) throws IOException;
  
  public final void writeDouble(int paramInt, double paramDouble) throws IOException {
    writeFixed64(paramInt, Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void writeDoubleNoTag(double paramDouble) throws IOException {
    writeFixed64NoTag(Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void writeEnum(int paramInt1, int paramInt2) throws IOException {
    writeInt32(paramInt1, paramInt2);
  }
  
  public final void writeEnumNoTag(int paramInt) throws IOException {
    writeInt32NoTag(paramInt);
  }
  
  public abstract void writeFixed32(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeFixed32NoTag(int paramInt) throws IOException;
  
  public abstract void writeFixed64(int paramInt, long paramLong) throws IOException;
  
  public abstract void writeFixed64NoTag(long paramLong) throws IOException;
  
  public final void writeFloat(int paramInt, float paramFloat) throws IOException {
    writeFixed32(paramInt, Float.floatToRawIntBits(paramFloat));
  }
  
  public final void writeFloatNoTag(float paramFloat) throws IOException {
    writeFixed32NoTag(Float.floatToRawIntBits(paramFloat));
  }
  
  @Deprecated
  public final void writeGroup(int paramInt, MessageLite paramMessageLite) throws IOException {
    writeTag(paramInt, 3);
    writeGroupNoTag(paramMessageLite);
    writeTag(paramInt, 4);
  }
  
  @Deprecated
  final void writeGroup(int paramInt, MessageLite paramMessageLite, Schema paramSchema) throws IOException {
    writeTag(paramInt, 3);
    writeGroupNoTag(paramMessageLite, paramSchema);
    writeTag(paramInt, 4);
  }
  
  @Deprecated
  public final void writeGroupNoTag(MessageLite paramMessageLite) throws IOException {
    paramMessageLite.writeTo(this);
  }
  
  @Deprecated
  final void writeGroupNoTag(MessageLite paramMessageLite, Schema<MessageLite> paramSchema) throws IOException {
    paramSchema.writeTo(paramMessageLite, this.wrapper);
  }
  
  public abstract void writeInt32(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeInt32NoTag(int paramInt) throws IOException;
  
  public final void writeInt64(int paramInt, long paramLong) throws IOException {
    writeUInt64(paramInt, paramLong);
  }
  
  public final void writeInt64NoTag(long paramLong) throws IOException {
    writeUInt64NoTag(paramLong);
  }
  
  public abstract void writeLazy(ByteBuffer paramByteBuffer) throws IOException;
  
  public abstract void writeLazy(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeMessage(int paramInt, MessageLite paramMessageLite) throws IOException;
  
  abstract void writeMessage(int paramInt, MessageLite paramMessageLite, Schema paramSchema) throws IOException;
  
  public abstract void writeMessageNoTag(MessageLite paramMessageLite) throws IOException;
  
  abstract void writeMessageNoTag(MessageLite paramMessageLite, Schema paramSchema) throws IOException;
  
  public abstract void writeMessageSetExtension(int paramInt, MessageLite paramMessageLite) throws IOException;
  
  public final void writeRawByte(byte paramByte) throws IOException {
    write(paramByte);
  }
  
  public final void writeRawByte(int paramInt) throws IOException {
    write((byte)paramInt);
  }
  
  public final void writeRawBytes(ByteString paramByteString) throws IOException {
    paramByteString.writeTo(this);
  }
  
  public abstract void writeRawBytes(ByteBuffer paramByteBuffer) throws IOException;
  
  public final void writeRawBytes(byte[] paramArrayOfbyte) throws IOException {
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public final void writeRawBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    write(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  @Deprecated
  public final void writeRawLittleEndian32(int paramInt) throws IOException {
    writeFixed32NoTag(paramInt);
  }
  
  @Deprecated
  public final void writeRawLittleEndian64(long paramLong) throws IOException {
    writeFixed64NoTag(paramLong);
  }
  
  public abstract void writeRawMessageSetExtension(int paramInt, ByteString paramByteString) throws IOException;
  
  @Deprecated
  public final void writeRawVarint32(int paramInt) throws IOException {
    writeUInt32NoTag(paramInt);
  }
  
  @Deprecated
  public final void writeRawVarint64(long paramLong) throws IOException {
    writeUInt64NoTag(paramLong);
  }
  
  public final void writeSFixed32(int paramInt1, int paramInt2) throws IOException {
    writeFixed32(paramInt1, paramInt2);
  }
  
  public final void writeSFixed32NoTag(int paramInt) throws IOException {
    writeFixed32NoTag(paramInt);
  }
  
  public final void writeSFixed64(int paramInt, long paramLong) throws IOException {
    writeFixed64(paramInt, paramLong);
  }
  
  public final void writeSFixed64NoTag(long paramLong) throws IOException {
    writeFixed64NoTag(paramLong);
  }
  
  public final void writeSInt32(int paramInt1, int paramInt2) throws IOException {
    writeUInt32(paramInt1, encodeZigZag32(paramInt2));
  }
  
  public final void writeSInt32NoTag(int paramInt) throws IOException {
    writeUInt32NoTag(encodeZigZag32(paramInt));
  }
  
  public final void writeSInt64(int paramInt, long paramLong) throws IOException {
    writeUInt64(paramInt, encodeZigZag64(paramLong));
  }
  
  public final void writeSInt64NoTag(long paramLong) throws IOException {
    writeUInt64NoTag(encodeZigZag64(paramLong));
  }
  
  public abstract void writeString(int paramInt, String paramString) throws IOException;
  
  public abstract void writeStringNoTag(String paramString) throws IOException;
  
  public abstract void writeTag(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeUInt32(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeUInt32NoTag(int paramInt) throws IOException;
  
  public abstract void writeUInt64(int paramInt, long paramLong) throws IOException;
  
  public abstract void writeUInt64NoTag(long paramLong) throws IOException;
  
  private static abstract class AbstractBufferedEncoder extends CodedOutputStream {
    final byte[] buffer;
    
    final int limit;
    
    int position;
    
    int totalBytesWritten;
    
    AbstractBufferedEncoder(int param1Int) {
      if (param1Int < 0)
        throw new IllegalArgumentException("bufferSize must be >= 0"); 
      this.buffer = new byte[Math.max(param1Int, 20)];
      this.limit = this.buffer.length;
    }
    
    final void buffer(byte param1Byte) {
      byte[] arrayOfByte = this.buffer;
      int i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)param1Byte;
      this.totalBytesWritten++;
    }
    
    final void bufferFixed32NoTag(int param1Int) {
      byte[] arrayOfByte = this.buffer;
      int i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(param1Int & 0xFF);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(param1Int >> 8 & 0xFF);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(param1Int >> 16 & 0xFF);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(param1Int >> 24 & 0xFF);
      this.totalBytesWritten += 4;
    }
    
    final void bufferFixed64NoTag(long param1Long) {
      byte[] arrayOfByte = this.buffer;
      int i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(param1Long & 0xFFL);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 8L & 0xFFL);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 16L & 0xFFL);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(0xFFL & param1Long >> 24L);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 32L) & 0xFF);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 40L) & 0xFF);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 48L) & 0xFF);
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 56L) & 0xFF);
      this.totalBytesWritten += 8;
    }
    
    final void bufferInt32NoTag(int param1Int) {
      if (param1Int >= 0) {
        bufferUInt32NoTag(param1Int);
      } else {
        bufferUInt64NoTag(param1Int);
      } 
    }
    
    final void bufferTag(int param1Int1, int param1Int2) {
      bufferUInt32NoTag(WireFormat.makeTag(param1Int1, param1Int2));
    }
    
    final void bufferUInt32NoTag(int param1Int) {
      int i = param1Int;
      if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
        long l = this.position;
        while (true) {
          if ((param1Int & 0xFFFFFF80) == 0) {
            byte[] arrayOfByte1 = this.buffer;
            i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(arrayOfByte1, i, (byte)param1Int);
            param1Int = (int)(this.position - l);
            this.totalBytesWritten += param1Int;
            return;
          } 
          byte[] arrayOfByte = this.buffer;
          i = this.position;
          this.position = i + 1;
          UnsafeUtil.putByte(arrayOfByte, i, (byte)(param1Int & 0x7F | 0x80));
          param1Int >>>= 7;
        } 
      } 
      while (true) {
        if ((i & 0xFFFFFF80) == 0) {
          byte[] arrayOfByte1 = this.buffer;
          param1Int = this.position;
          this.position = param1Int + 1;
          arrayOfByte1[param1Int] = (byte)(byte)i;
          this.totalBytesWritten++;
          return;
        } 
        byte[] arrayOfByte = this.buffer;
        param1Int = this.position;
        this.position = param1Int + 1;
        arrayOfByte[param1Int] = (byte)(byte)(i & 0x7F | 0x80);
        this.totalBytesWritten++;
        i >>>= 7;
      } 
    }
    
    final void bufferUInt64NoTag(long param1Long) {
      long l = param1Long;
      if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
        l = this.position;
        while (true) {
          if ((param1Long & 0xFFFFFFFFFFFFFF80L) == 0L) {
            byte[] arrayOfByte1 = this.buffer;
            int j = this.position;
            this.position = j + 1;
            UnsafeUtil.putByte(arrayOfByte1, j, (byte)(int)param1Long);
            j = (int)(this.position - l);
            this.totalBytesWritten += j;
            return;
          } 
          byte[] arrayOfByte = this.buffer;
          int i = this.position;
          this.position = i + 1;
          UnsafeUtil.putByte(arrayOfByte, i, (byte)((int)param1Long & 0x7F | 0x80));
          param1Long >>>= 7L;
        } 
      } 
      while (true) {
        if ((l & 0xFFFFFFFFFFFFFF80L) == 0L) {
          byte[] arrayOfByte1 = this.buffer;
          int j = this.position;
          this.position = j + 1;
          arrayOfByte1[j] = (byte)(byte)(int)l;
          this.totalBytesWritten++;
          return;
        } 
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)l & 0x7F | 0x80);
        this.totalBytesWritten++;
        l >>>= 7L;
      } 
    }
    
    public final int getTotalBytesWritten() {
      return this.totalBytesWritten;
    }
    
    public final int spaceLeft() {
      throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }
  }
  
  private static class ArrayEncoder extends CodedOutputStream {
    private final byte[] buffer;
    
    private final int limit;
    
    private final int offset;
    
    private int position;
    
    ArrayEncoder(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      if (param1ArrayOfbyte == null)
        throw new NullPointerException("buffer"); 
      int i = param1ArrayOfbyte.length;
      int j = param1Int1 + param1Int2;
      if ((param1Int1 | param1Int2 | i - j) < 0)
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[] { Integer.valueOf(param1ArrayOfbyte.length), Integer.valueOf(param1Int1), Integer.valueOf(param1Int2) })); 
      this.buffer = param1ArrayOfbyte;
      this.offset = param1Int1;
      this.position = param1Int1;
      this.limit = j;
    }
    
    public void flush() {}
    
    public final int getTotalBytesWritten() {
      return this.position - this.offset;
    }
    
    public final int spaceLeft() {
      return this.limit - this.position;
    }
    
    public final void write(byte param1Byte) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)param1Byte;
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void write(ByteBuffer param1ByteBuffer) throws IOException {
      int i = param1ByteBuffer.remaining();
      try {
        param1ByteBuffer.get(this.buffer, this.position, i);
        this.position += i;
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i) }), indexOutOfBoundsException);
      } 
    }
    
    public final void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      try {
        System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, this.position, param1Int2);
        this.position += param1Int2;
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(param1Int2) }), indexOutOfBoundsException);
      } 
    }
    
    public final void writeBool(int param1Int, boolean param1Boolean) throws IOException {
      writeTag(param1Int, 0);
      write((byte)param1Boolean);
    }
    
    public final void writeByteArray(int param1Int, byte[] param1ArrayOfbyte) throws IOException {
      writeByteArray(param1Int, param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public final void writeByteArray(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3) throws IOException {
      writeTag(param1Int1, 2);
      writeByteArrayNoTag(param1ArrayOfbyte, param1Int2, param1Int3);
    }
    
    public final void writeByteArrayNoTag(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(param1Int2);
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void writeByteBuffer(int param1Int, ByteBuffer param1ByteBuffer) throws IOException {
      writeTag(param1Int, 2);
      writeUInt32NoTag(param1ByteBuffer.capacity());
      writeRawBytes(param1ByteBuffer);
    }
    
    public final void writeBytes(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(param1Int, 2);
      writeBytesNoTag(param1ByteString);
    }
    
    public final void writeBytesNoTag(ByteString param1ByteString) throws IOException {
      writeUInt32NoTag(param1ByteString.size());
      param1ByteString.writeTo(this);
    }
    
    public final void writeFixed32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 5);
      writeFixed32NoTag(param1Int2);
    }
    
    public final void writeFixed32NoTag(int param1Int) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)(param1Int & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)(param1Int >> 8 & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)(param1Int >> 16 & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)(param1Int >> 24 & 0xFF);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void writeFixed64(int param1Int, long param1Long) throws IOException {
      writeTag(param1Int, 1);
      writeFixed64NoTag(param1Long);
    }
    
    public final void writeFixed64NoTag(long param1Long) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)param1Long & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 8L) & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 16L) & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 24L) & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 32L) & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 40L) & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 48L) & 0xFF);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)(param1Long >> 56L) & 0xFF);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void writeInt32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 0);
      writeInt32NoTag(param1Int2);
    }
    
    public final void writeInt32NoTag(int param1Int) throws IOException {
      if (param1Int >= 0) {
        writeUInt32NoTag(param1Int);
      } else {
        writeUInt64NoTag(param1Int);
      } 
    }
    
    public final void writeLazy(ByteBuffer param1ByteBuffer) throws IOException {
      write(param1ByteBuffer);
    }
    
    public final void writeLazy(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void writeMessage(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite);
    }
    
    final void writeMessage(int param1Int, MessageLite param1MessageLite, Schema<MessageLite> param1Schema) throws IOException {
      writeTag(param1Int, 2);
      writeUInt32NoTag(((AbstractMessageLite)param1MessageLite).getSerializedSize(param1Schema));
      param1Schema.writeTo(param1MessageLite, this.wrapper);
    }
    
    public final void writeMessageNoTag(MessageLite param1MessageLite) throws IOException {
      writeUInt32NoTag(param1MessageLite.getSerializedSize());
      param1MessageLite.writeTo(this);
    }
    
    final void writeMessageNoTag(MessageLite param1MessageLite, Schema<MessageLite> param1Schema) throws IOException {
      writeUInt32NoTag(((AbstractMessageLite)param1MessageLite).getSerializedSize(param1Schema));
      param1Schema.writeTo(param1MessageLite, this.wrapper);
    }
    
    public final void writeMessageSetExtension(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeMessage(3, param1MessageLite);
      writeTag(1, 4);
    }
    
    public final void writeRawBytes(ByteBuffer param1ByteBuffer) throws IOException {
      if (param1ByteBuffer.hasArray()) {
        write(param1ByteBuffer.array(), param1ByteBuffer.arrayOffset(), param1ByteBuffer.capacity());
      } else {
        param1ByteBuffer = param1ByteBuffer.duplicate();
        param1ByteBuffer.clear();
        write(param1ByteBuffer);
      } 
    }
    
    public final void writeRawMessageSetExtension(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeBytes(3, param1ByteString);
      writeTag(1, 4);
    }
    
    public final void writeString(int param1Int, String param1String) throws IOException {
      writeTag(param1Int, 2);
      writeStringNoTag(param1String);
    }
    
    public final void writeStringNoTag(String param1String) throws IOException {
      int i = this.position;
      try {
        int j = computeUInt32SizeNoTag(param1String.length() * 3);
        int k = computeUInt32SizeNoTag(param1String.length());
        if (k == j) {
          this.position = i + k;
          j = Utf8.encode(param1String, this.buffer, this.position, spaceLeft());
          this.position = i;
          writeUInt32NoTag(j - i - k);
          this.position = j;
        } else {
          writeUInt32NoTag(Utf8.encodedLength(param1String));
          this.position = Utf8.encode(param1String, this.buffer, this.position, spaceLeft());
        } 
      } catch (UnpairedSurrogateException unpairedSurrogateException) {
        this.position = i;
        inefficientWriteStringNoTag(param1String, unpairedSurrogateException);
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(indexOutOfBoundsException);
      } 
    }
    
    public final void writeTag(int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(WireFormat.makeTag(param1Int1, param1Int2));
    }
    
    public final void writeUInt32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 0);
      writeUInt32NoTag(param1Int2);
    }
    
    public final void writeUInt32NoTag(int param1Int) throws IOException {
      int i = param1Int;
      if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
        i = param1Int;
        if (!Android.isOnAndroidDevice()) {
          i = param1Int;
          if (spaceLeft() >= 5) {
            if ((param1Int & 0xFFFFFF80) == 0) {
              byte[] arrayOfByte1 = this.buffer;
              i = this.position;
              this.position = i + 1;
              UnsafeUtil.putByte(arrayOfByte1, i, (byte)param1Int);
              return;
            } 
            byte[] arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(arrayOfByte, i, (byte)(param1Int | 0x80));
            param1Int >>>= 7;
            if ((param1Int & 0xFFFFFF80) == 0) {
              arrayOfByte = this.buffer;
              i = this.position;
              this.position = i + 1;
              UnsafeUtil.putByte(arrayOfByte, i, (byte)param1Int);
              return;
            } 
            arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(arrayOfByte, i, (byte)(param1Int | 0x80));
            param1Int >>>= 7;
            if ((param1Int & 0xFFFFFF80) == 0) {
              arrayOfByte = this.buffer;
              i = this.position;
              this.position = i + 1;
              UnsafeUtil.putByte(arrayOfByte, i, (byte)param1Int);
              return;
            } 
            arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(arrayOfByte, i, (byte)(param1Int | 0x80));
            param1Int >>>= 7;
            if ((param1Int & 0xFFFFFF80) == 0) {
              arrayOfByte = this.buffer;
              i = this.position;
              this.position = i + 1;
              UnsafeUtil.putByte(arrayOfByte, i, (byte)param1Int);
              return;
            } 
            arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(arrayOfByte, i, (byte)(param1Int | 0x80));
            arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(arrayOfByte, i, (byte)(param1Int >>> 7));
            return;
          } 
        } 
      } 
      while (true) {
        if ((i & 0xFFFFFF80) == 0)
          try {
            byte[] arrayOfByte1 = this.buffer;
            param1Int = this.position;
            this.position = param1Int + 1;
            arrayOfByte1[param1Int] = (byte)(byte)i;
            return;
          } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
          }  
        byte[] arrayOfByte = this.buffer;
        param1Int = this.position;
        this.position = param1Int + 1;
        arrayOfByte[param1Int] = (byte)(byte)(i & 0x7F | 0x80);
        i >>>= 7;
      } 
    }
    
    public final void writeUInt64(int param1Int, long param1Long) throws IOException {
      writeTag(param1Int, 0);
      writeUInt64NoTag(param1Long);
    }
    
    public final void writeUInt64NoTag(long param1Long) throws IOException {
      long l = param1Long;
      if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
        l = param1Long;
        if (spaceLeft() >= 10)
          while (true) {
            if ((param1Long & 0xFFFFFFFFFFFFFF80L) == 0L) {
              byte[] arrayOfByte1 = this.buffer;
              int j = this.position;
              this.position = j + 1;
              UnsafeUtil.putByte(arrayOfByte1, j, (byte)(int)param1Long);
              return;
            } 
            byte[] arrayOfByte = this.buffer;
            int i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(arrayOfByte, i, (byte)((int)param1Long & 0x7F | 0x80));
            param1Long >>>= 7L;
          }  
      } 
      while (true) {
        if ((l & 0xFFFFFFFFFFFFFF80L) == 0L)
          try {
            byte[] arrayOfByte1 = this.buffer;
            int j = this.position;
            this.position = j + 1;
            arrayOfByte1[j] = (byte)(byte)(int)l;
            return;
          } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
          }  
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)l & 0x7F | 0x80);
        l >>>= 7L;
      } 
    }
  }
  
  private static final class ByteOutputEncoder extends AbstractBufferedEncoder {
    private final ByteOutput out;
    
    ByteOutputEncoder(ByteOutput param1ByteOutput, int param1Int) {
      super(param1Int);
      if (param1ByteOutput == null)
        throw new NullPointerException("out"); 
      this.out = param1ByteOutput;
    }
    
    private void doFlush() throws IOException {
      this.out.write(this.buffer, 0, this.position);
      this.position = 0;
    }
    
    private void flushIfNotAvailable(int param1Int) throws IOException {
      if (this.limit - this.position < param1Int)
        doFlush(); 
    }
    
    public void flush() throws IOException {
      if (this.position > 0)
        doFlush(); 
    }
    
    public void write(byte param1Byte) throws IOException {
      if (this.position == this.limit)
        doFlush(); 
      buffer(param1Byte);
    }
    
    public void write(ByteBuffer param1ByteBuffer) throws IOException {
      flush();
      int i = param1ByteBuffer.remaining();
      this.out.write(param1ByteBuffer);
      this.totalBytesWritten += i;
    }
    
    public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      flush();
      this.out.write(param1ArrayOfbyte, param1Int1, param1Int2);
      this.totalBytesWritten += param1Int2;
    }
    
    public void writeBool(int param1Int, boolean param1Boolean) throws IOException {
      flushIfNotAvailable(11);
      bufferTag(param1Int, 0);
      buffer((byte)param1Boolean);
    }
    
    public void writeByteArray(int param1Int, byte[] param1ArrayOfbyte) throws IOException {
      writeByteArray(param1Int, param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public void writeByteArray(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3) throws IOException {
      writeTag(param1Int1, 2);
      writeByteArrayNoTag(param1ArrayOfbyte, param1Int2, param1Int3);
    }
    
    public void writeByteArrayNoTag(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(param1Int2);
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void writeByteBuffer(int param1Int, ByteBuffer param1ByteBuffer) throws IOException {
      writeTag(param1Int, 2);
      writeUInt32NoTag(param1ByteBuffer.capacity());
      writeRawBytes(param1ByteBuffer);
    }
    
    public void writeBytes(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(param1Int, 2);
      writeBytesNoTag(param1ByteString);
    }
    
    public void writeBytesNoTag(ByteString param1ByteString) throws IOException {
      writeUInt32NoTag(param1ByteString.size());
      param1ByteString.writeTo(this);
    }
    
    public void writeFixed32(int param1Int1, int param1Int2) throws IOException {
      flushIfNotAvailable(14);
      bufferTag(param1Int1, 5);
      bufferFixed32NoTag(param1Int2);
    }
    
    public void writeFixed32NoTag(int param1Int) throws IOException {
      flushIfNotAvailable(4);
      bufferFixed32NoTag(param1Int);
    }
    
    public void writeFixed64(int param1Int, long param1Long) throws IOException {
      flushIfNotAvailable(18);
      bufferTag(param1Int, 1);
      bufferFixed64NoTag(param1Long);
    }
    
    public void writeFixed64NoTag(long param1Long) throws IOException {
      flushIfNotAvailable(8);
      bufferFixed64NoTag(param1Long);
    }
    
    public void writeInt32(int param1Int1, int param1Int2) throws IOException {
      flushIfNotAvailable(20);
      bufferTag(param1Int1, 0);
      bufferInt32NoTag(param1Int2);
    }
    
    public void writeInt32NoTag(int param1Int) throws IOException {
      if (param1Int >= 0) {
        writeUInt32NoTag(param1Int);
      } else {
        writeUInt64NoTag(param1Int);
      } 
    }
    
    public void writeLazy(ByteBuffer param1ByteBuffer) throws IOException {
      flush();
      int i = param1ByteBuffer.remaining();
      this.out.writeLazy(param1ByteBuffer);
      this.totalBytesWritten += i;
    }
    
    public void writeLazy(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      flush();
      this.out.writeLazy(param1ArrayOfbyte, param1Int1, param1Int2);
      this.totalBytesWritten += param1Int2;
    }
    
    public void writeMessage(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite);
    }
    
    void writeMessage(int param1Int, MessageLite param1MessageLite, Schema param1Schema) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite, param1Schema);
    }
    
    public void writeMessageNoTag(MessageLite param1MessageLite) throws IOException {
      writeUInt32NoTag(param1MessageLite.getSerializedSize());
      param1MessageLite.writeTo(this);
    }
    
    void writeMessageNoTag(MessageLite param1MessageLite, Schema<MessageLite> param1Schema) throws IOException {
      writeUInt32NoTag(((AbstractMessageLite)param1MessageLite).getSerializedSize(param1Schema));
      param1Schema.writeTo(param1MessageLite, this.wrapper);
    }
    
    public void writeMessageSetExtension(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeMessage(3, param1MessageLite);
      writeTag(1, 4);
    }
    
    public void writeRawBytes(ByteBuffer param1ByteBuffer) throws IOException {
      if (param1ByteBuffer.hasArray()) {
        write(param1ByteBuffer.array(), param1ByteBuffer.arrayOffset(), param1ByteBuffer.capacity());
      } else {
        param1ByteBuffer = param1ByteBuffer.duplicate();
        param1ByteBuffer.clear();
        write(param1ByteBuffer);
      } 
    }
    
    public void writeRawMessageSetExtension(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeBytes(3, param1ByteString);
      writeTag(1, 4);
    }
    
    public void writeString(int param1Int, String param1String) throws IOException {
      writeTag(param1Int, 2);
      writeStringNoTag(param1String);
    }
    
    public void writeStringNoTag(String param1String) throws IOException {
      int i = param1String.length() * 3;
      int j = computeUInt32SizeNoTag(i);
      int k = j + i;
      if (k > this.limit) {
        byte[] arrayOfByte = new byte[i];
        i = Utf8.encode(param1String, arrayOfByte, 0, i);
        writeUInt32NoTag(i);
        writeLazy(arrayOfByte, 0, i);
        return;
      } 
      if (k > this.limit - this.position)
        doFlush(); 
      i = this.position;
      try {
        k = computeUInt32SizeNoTag(param1String.length());
        if (k == j) {
          this.position = i + k;
          j = Utf8.encode(param1String, this.buffer, this.position, this.limit - this.position);
          this.position = i;
          k = j - i - k;
          bufferUInt32NoTag(k);
          this.position = j;
          this.totalBytesWritten += k;
        } else {
          j = Utf8.encodedLength(param1String);
          bufferUInt32NoTag(j);
          this.position = Utf8.encode(param1String, this.buffer, this.position, j);
          this.totalBytesWritten += j;
        } 
      } catch (UnpairedSurrogateException unpairedSurrogateException) {
        this.totalBytesWritten -= this.position - i;
        this.position = i;
        inefficientWriteStringNoTag(param1String, unpairedSurrogateException);
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(indexOutOfBoundsException);
      } 
    }
    
    public void writeTag(int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(WireFormat.makeTag(param1Int1, param1Int2));
    }
    
    public void writeUInt32(int param1Int1, int param1Int2) throws IOException {
      flushIfNotAvailable(20);
      bufferTag(param1Int1, 0);
      bufferUInt32NoTag(param1Int2);
    }
    
    public void writeUInt32NoTag(int param1Int) throws IOException {
      flushIfNotAvailable(5);
      bufferUInt32NoTag(param1Int);
    }
    
    public void writeUInt64(int param1Int, long param1Long) throws IOException {
      flushIfNotAvailable(20);
      bufferTag(param1Int, 0);
      bufferUInt64NoTag(param1Long);
    }
    
    public void writeUInt64NoTag(long param1Long) throws IOException {
      flushIfNotAvailable(10);
      bufferUInt64NoTag(param1Long);
    }
  }
  
  private static final class HeapNioEncoder extends ArrayEncoder {
    private final ByteBuffer byteBuffer;
    
    private int initialPosition;
    
    HeapNioEncoder(ByteBuffer param1ByteBuffer) {
      super(param1ByteBuffer.array(), param1ByteBuffer.arrayOffset() + param1ByteBuffer.position(), param1ByteBuffer.remaining());
      this.byteBuffer = param1ByteBuffer;
      this.initialPosition = param1ByteBuffer.position();
    }
    
    public void flush() {
      this.byteBuffer.position(this.initialPosition + getTotalBytesWritten());
    }
  }
  
  public static class OutOfSpaceException extends IOException {
    private static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";
    
    private static final long serialVersionUID = -6947486886997889499L;
    
    OutOfSpaceException() {
      super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }
    
    OutOfSpaceException(String param1String) {
      super(stringBuilder.toString());
    }
    
    OutOfSpaceException(String param1String, Throwable param1Throwable) {
      super(stringBuilder.toString(), param1Throwable);
    }
    
    OutOfSpaceException(Throwable param1Throwable) {
      super("CodedOutputStream was writing to a flat byte array and ran out of space.", param1Throwable);
    }
  }
  
  private static final class OutputStreamEncoder extends AbstractBufferedEncoder {
    private final OutputStream out;
    
    OutputStreamEncoder(OutputStream param1OutputStream, int param1Int) {
      super(param1Int);
      if (param1OutputStream == null)
        throw new NullPointerException("out"); 
      this.out = param1OutputStream;
    }
    
    private void doFlush() throws IOException {
      this.out.write(this.buffer, 0, this.position);
      this.position = 0;
    }
    
    private void flushIfNotAvailable(int param1Int) throws IOException {
      if (this.limit - this.position < param1Int)
        doFlush(); 
    }
    
    public void flush() throws IOException {
      if (this.position > 0)
        doFlush(); 
    }
    
    public void write(byte param1Byte) throws IOException {
      if (this.position == this.limit)
        doFlush(); 
      buffer(param1Byte);
    }
    
    public void write(ByteBuffer param1ByteBuffer) throws IOException {
      int i = param1ByteBuffer.remaining();
      if (this.limit - this.position >= i) {
        param1ByteBuffer.get(this.buffer, this.position, i);
        this.position += i;
        this.totalBytesWritten += i;
      } else {
        int j = this.limit - this.position;
        param1ByteBuffer.get(this.buffer, this.position, j);
        i -= j;
        this.position = this.limit;
        this.totalBytesWritten += j;
        doFlush();
        while (i > this.limit) {
          param1ByteBuffer.get(this.buffer, 0, this.limit);
          this.out.write(this.buffer, 0, this.limit);
          i -= this.limit;
          this.totalBytesWritten += this.limit;
        } 
        param1ByteBuffer.get(this.buffer, 0, i);
        this.position = i;
        this.totalBytesWritten += i;
      } 
    }
    
    public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      if (this.limit - this.position >= param1Int2) {
        System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, this.position, param1Int2);
        this.position += param1Int2;
        this.totalBytesWritten += param1Int2;
      } else {
        int i = this.limit - this.position;
        System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, this.position, i);
        param1Int1 += i;
        param1Int2 -= i;
        this.position = this.limit;
        this.totalBytesWritten += i;
        doFlush();
        if (param1Int2 <= this.limit) {
          System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, 0, param1Int2);
          this.position = param1Int2;
        } else {
          this.out.write(param1ArrayOfbyte, param1Int1, param1Int2);
        } 
        this.totalBytesWritten += param1Int2;
      } 
    }
    
    public void writeBool(int param1Int, boolean param1Boolean) throws IOException {
      flushIfNotAvailable(11);
      bufferTag(param1Int, 0);
      buffer((byte)param1Boolean);
    }
    
    public void writeByteArray(int param1Int, byte[] param1ArrayOfbyte) throws IOException {
      writeByteArray(param1Int, param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public void writeByteArray(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3) throws IOException {
      writeTag(param1Int1, 2);
      writeByteArrayNoTag(param1ArrayOfbyte, param1Int2, param1Int3);
    }
    
    public void writeByteArrayNoTag(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(param1Int2);
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void writeByteBuffer(int param1Int, ByteBuffer param1ByteBuffer) throws IOException {
      writeTag(param1Int, 2);
      writeUInt32NoTag(param1ByteBuffer.capacity());
      writeRawBytes(param1ByteBuffer);
    }
    
    public void writeBytes(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(param1Int, 2);
      writeBytesNoTag(param1ByteString);
    }
    
    public void writeBytesNoTag(ByteString param1ByteString) throws IOException {
      writeUInt32NoTag(param1ByteString.size());
      param1ByteString.writeTo(this);
    }
    
    public void writeFixed32(int param1Int1, int param1Int2) throws IOException {
      flushIfNotAvailable(14);
      bufferTag(param1Int1, 5);
      bufferFixed32NoTag(param1Int2);
    }
    
    public void writeFixed32NoTag(int param1Int) throws IOException {
      flushIfNotAvailable(4);
      bufferFixed32NoTag(param1Int);
    }
    
    public void writeFixed64(int param1Int, long param1Long) throws IOException {
      flushIfNotAvailable(18);
      bufferTag(param1Int, 1);
      bufferFixed64NoTag(param1Long);
    }
    
    public void writeFixed64NoTag(long param1Long) throws IOException {
      flushIfNotAvailable(8);
      bufferFixed64NoTag(param1Long);
    }
    
    public void writeInt32(int param1Int1, int param1Int2) throws IOException {
      flushIfNotAvailable(20);
      bufferTag(param1Int1, 0);
      bufferInt32NoTag(param1Int2);
    }
    
    public void writeInt32NoTag(int param1Int) throws IOException {
      if (param1Int >= 0) {
        writeUInt32NoTag(param1Int);
      } else {
        writeUInt64NoTag(param1Int);
      } 
    }
    
    public void writeLazy(ByteBuffer param1ByteBuffer) throws IOException {
      write(param1ByteBuffer);
    }
    
    public void writeLazy(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void writeMessage(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite);
    }
    
    void writeMessage(int param1Int, MessageLite param1MessageLite, Schema param1Schema) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite, param1Schema);
    }
    
    public void writeMessageNoTag(MessageLite param1MessageLite) throws IOException {
      writeUInt32NoTag(param1MessageLite.getSerializedSize());
      param1MessageLite.writeTo(this);
    }
    
    void writeMessageNoTag(MessageLite param1MessageLite, Schema<MessageLite> param1Schema) throws IOException {
      writeUInt32NoTag(((AbstractMessageLite)param1MessageLite).getSerializedSize(param1Schema));
      param1Schema.writeTo(param1MessageLite, this.wrapper);
    }
    
    public void writeMessageSetExtension(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeMessage(3, param1MessageLite);
      writeTag(1, 4);
    }
    
    public void writeRawBytes(ByteBuffer param1ByteBuffer) throws IOException {
      if (param1ByteBuffer.hasArray()) {
        write(param1ByteBuffer.array(), param1ByteBuffer.arrayOffset(), param1ByteBuffer.capacity());
      } else {
        param1ByteBuffer = param1ByteBuffer.duplicate();
        param1ByteBuffer.clear();
        write(param1ByteBuffer);
      } 
    }
    
    public void writeRawMessageSetExtension(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeBytes(3, param1ByteString);
      writeTag(1, 4);
    }
    
    public void writeString(int param1Int, String param1String) throws IOException {
      writeTag(param1Int, 2);
      writeStringNoTag(param1String);
    }
    
    public void writeStringNoTag(String param1String) throws IOException {
      try {
        int i = param1String.length() * 3;
        int j = computeUInt32SizeNoTag(i);
        int k = j + i;
        if (k > this.limit) {
          byte[] arrayOfByte = new byte[i];
          j = Utf8.encode(param1String, arrayOfByte, 0, i);
          writeUInt32NoTag(j);
          writeLazy(arrayOfByte, 0, j);
          return;
        } 
        if (k > this.limit - this.position)
          doFlush(); 
        k = computeUInt32SizeNoTag(param1String.length());
        i = this.position;
        if (k == j) {
          try {
            this.position = i + k;
            int m = Utf8.encode(param1String, this.buffer, this.position, this.limit - this.position);
            this.position = i;
            j = m - i - k;
            bufferUInt32NoTag(j);
            this.position = m;
          } catch (UnpairedSurrogateException unpairedSurrogateException) {
          
          } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {}
        } else {
          j = Utf8.encodedLength(param1String);
          bufferUInt32NoTag(j);
          this.position = Utf8.encode(param1String, this.buffer, this.position, j);
        } 
        this.totalBytesWritten += j;
      } catch (UnpairedSurrogateException unpairedSurrogateException) {
        inefficientWriteStringNoTag(param1String, unpairedSurrogateException);
      } 
    }
    
    public void writeTag(int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(WireFormat.makeTag(param1Int1, param1Int2));
    }
    
    public void writeUInt32(int param1Int1, int param1Int2) throws IOException {
      flushIfNotAvailable(20);
      bufferTag(param1Int1, 0);
      bufferUInt32NoTag(param1Int2);
    }
    
    public void writeUInt32NoTag(int param1Int) throws IOException {
      flushIfNotAvailable(5);
      bufferUInt32NoTag(param1Int);
    }
    
    public void writeUInt64(int param1Int, long param1Long) throws IOException {
      flushIfNotAvailable(20);
      bufferTag(param1Int, 0);
      bufferUInt64NoTag(param1Long);
    }
    
    public void writeUInt64NoTag(long param1Long) throws IOException {
      flushIfNotAvailable(10);
      bufferUInt64NoTag(param1Long);
    }
  }
  
  private static final class SafeDirectNioEncoder extends CodedOutputStream {
    private final ByteBuffer buffer;
    
    private final int initialPosition;
    
    private final ByteBuffer originalBuffer;
    
    SafeDirectNioEncoder(ByteBuffer param1ByteBuffer) {
      this.originalBuffer = param1ByteBuffer;
      this.buffer = param1ByteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
      this.initialPosition = param1ByteBuffer.position();
    }
    
    private void encode(String param1String) throws IOException {
      try {
        Utf8.encodeUtf8(param1String, this.buffer);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(indexOutOfBoundsException);
      } 
    }
    
    public void flush() {
      this.originalBuffer.position(this.buffer.position());
    }
    
    public int getTotalBytesWritten() {
      return this.buffer.position() - this.initialPosition;
    }
    
    public int spaceLeft() {
      return this.buffer.remaining();
    }
    
    public void write(byte param1Byte) throws IOException {
      try {
        this.buffer.put(param1Byte);
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
      } 
    }
    
    public void write(ByteBuffer param1ByteBuffer) throws IOException {
      try {
        this.buffer.put(param1ByteBuffer);
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
      } 
    }
    
    public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      try {
        this.buffer.put(param1ArrayOfbyte, param1Int1, param1Int2);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(indexOutOfBoundsException);
      } catch (BufferOverflowException bufferOverflowException) {
        throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
      } 
    }
    
    public void writeBool(int param1Int, boolean param1Boolean) throws IOException {
      writeTag(param1Int, 0);
      write((byte)param1Boolean);
    }
    
    public void writeByteArray(int param1Int, byte[] param1ArrayOfbyte) throws IOException {
      writeByteArray(param1Int, param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public void writeByteArray(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3) throws IOException {
      writeTag(param1Int1, 2);
      writeByteArrayNoTag(param1ArrayOfbyte, param1Int2, param1Int3);
    }
    
    public void writeByteArrayNoTag(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(param1Int2);
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void writeByteBuffer(int param1Int, ByteBuffer param1ByteBuffer) throws IOException {
      writeTag(param1Int, 2);
      writeUInt32NoTag(param1ByteBuffer.capacity());
      writeRawBytes(param1ByteBuffer);
    }
    
    public void writeBytes(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(param1Int, 2);
      writeBytesNoTag(param1ByteString);
    }
    
    public void writeBytesNoTag(ByteString param1ByteString) throws IOException {
      writeUInt32NoTag(param1ByteString.size());
      param1ByteString.writeTo(this);
    }
    
    public void writeFixed32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 5);
      writeFixed32NoTag(param1Int2);
    }
    
    public void writeFixed32NoTag(int param1Int) throws IOException {
      try {
        this.buffer.putInt(param1Int);
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
      } 
    }
    
    public void writeFixed64(int param1Int, long param1Long) throws IOException {
      writeTag(param1Int, 1);
      writeFixed64NoTag(param1Long);
    }
    
    public void writeFixed64NoTag(long param1Long) throws IOException {
      try {
        this.buffer.putLong(param1Long);
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
      } 
    }
    
    public void writeInt32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 0);
      writeInt32NoTag(param1Int2);
    }
    
    public void writeInt32NoTag(int param1Int) throws IOException {
      if (param1Int >= 0) {
        writeUInt32NoTag(param1Int);
      } else {
        writeUInt64NoTag(param1Int);
      } 
    }
    
    public void writeLazy(ByteBuffer param1ByteBuffer) throws IOException {
      write(param1ByteBuffer);
    }
    
    public void writeLazy(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void writeMessage(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite);
    }
    
    void writeMessage(int param1Int, MessageLite param1MessageLite, Schema param1Schema) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite, param1Schema);
    }
    
    public void writeMessageNoTag(MessageLite param1MessageLite) throws IOException {
      writeUInt32NoTag(param1MessageLite.getSerializedSize());
      param1MessageLite.writeTo(this);
    }
    
    void writeMessageNoTag(MessageLite param1MessageLite, Schema<MessageLite> param1Schema) throws IOException {
      writeUInt32NoTag(((AbstractMessageLite)param1MessageLite).getSerializedSize(param1Schema));
      param1Schema.writeTo(param1MessageLite, this.wrapper);
    }
    
    public void writeMessageSetExtension(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeMessage(3, param1MessageLite);
      writeTag(1, 4);
    }
    
    public void writeRawBytes(ByteBuffer param1ByteBuffer) throws IOException {
      if (param1ByteBuffer.hasArray()) {
        write(param1ByteBuffer.array(), param1ByteBuffer.arrayOffset(), param1ByteBuffer.capacity());
      } else {
        param1ByteBuffer = param1ByteBuffer.duplicate();
        param1ByteBuffer.clear();
        write(param1ByteBuffer);
      } 
    }
    
    public void writeRawMessageSetExtension(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeBytes(3, param1ByteString);
      writeTag(1, 4);
    }
    
    public void writeString(int param1Int, String param1String) throws IOException {
      writeTag(param1Int, 2);
      writeStringNoTag(param1String);
    }
    
    public void writeStringNoTag(String param1String) throws IOException {
      int i = this.buffer.position();
      try {
        int j = computeUInt32SizeNoTag(param1String.length() * 3);
        int k = computeUInt32SizeNoTag(param1String.length());
        if (k == j) {
          k = this.buffer.position() + k;
          this.buffer.position(k);
          encode(param1String);
          j = this.buffer.position();
          this.buffer.position(i);
          writeUInt32NoTag(j - k);
          this.buffer.position(j);
        } else {
          writeUInt32NoTag(Utf8.encodedLength(param1String));
          encode(param1String);
        } 
      } catch (UnpairedSurrogateException unpairedSurrogateException) {
        this.buffer.position(i);
        inefficientWriteStringNoTag(param1String, unpairedSurrogateException);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new CodedOutputStream.OutOfSpaceException(illegalArgumentException);
      } 
    }
    
    public void writeTag(int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(WireFormat.makeTag(param1Int1, param1Int2));
    }
    
    public void writeUInt32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 0);
      writeUInt32NoTag(param1Int2);
    }
    
    public void writeUInt32NoTag(int param1Int) throws IOException {
      while (true) {
        if ((param1Int & 0xFFFFFF80) == 0)
          try {
            this.buffer.put((byte)param1Int);
            return;
          } catch (BufferOverflowException bufferOverflowException) {
            throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
          }  
        this.buffer.put((byte)(param1Int & 0x7F | 0x80));
        param1Int >>>= 7;
      } 
    }
    
    public void writeUInt64(int param1Int, long param1Long) throws IOException {
      writeTag(param1Int, 0);
      writeUInt64NoTag(param1Long);
    }
    
    public void writeUInt64NoTag(long param1Long) throws IOException {
      while (true) {
        if ((0xFFFFFFFFFFFFFF80L & param1Long) == 0L)
          try {
            this.buffer.put((byte)(int)param1Long);
            return;
          } catch (BufferOverflowException bufferOverflowException) {
            throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
          }  
        this.buffer.put((byte)((int)param1Long & 0x7F | 0x80));
        param1Long >>>= 7L;
      } 
    }
  }
  
  private static final class UnsafeDirectNioEncoder extends CodedOutputStream {
    private final long address;
    
    private final ByteBuffer buffer;
    
    private final long initialPosition;
    
    private final long limit;
    
    private final long oneVarintLimit;
    
    private final ByteBuffer originalBuffer;
    
    private long position;
    
    UnsafeDirectNioEncoder(ByteBuffer param1ByteBuffer) {
      this.originalBuffer = param1ByteBuffer;
      this.buffer = param1ByteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
      this.address = UnsafeUtil.addressOffset(param1ByteBuffer);
      this.initialPosition = this.address + param1ByteBuffer.position();
      this.limit = this.address + param1ByteBuffer.limit();
      this.oneVarintLimit = this.limit - 10L;
      this.position = this.initialPosition;
    }
    
    private int bufferPos(long param1Long) {
      return (int)(param1Long - this.address);
    }
    
    static boolean isSupported() {
      return UnsafeUtil.hasUnsafeByteBufferOperations();
    }
    
    private void repositionBuffer(long param1Long) {
      this.buffer.position(bufferPos(param1Long));
    }
    
    public void flush() {
      this.originalBuffer.position(bufferPos(this.position));
    }
    
    public int getTotalBytesWritten() {
      return (int)(this.position - this.initialPosition);
    }
    
    public int spaceLeft() {
      return (int)(this.limit - this.position);
    }
    
    public void write(byte param1Byte) throws IOException {
      if (this.position >= this.limit)
        throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(1) })); 
      long l = this.position;
      this.position = 1L + l;
      UnsafeUtil.putByte(l, param1Byte);
    }
    
    public void write(ByteBuffer param1ByteBuffer) throws IOException {
      try {
        int i = param1ByteBuffer.remaining();
        repositionBuffer(this.position);
        this.buffer.put(param1ByteBuffer);
        this.position += i;
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new CodedOutputStream.OutOfSpaceException(bufferOverflowException);
      } 
    }
    
    public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      if (param1ArrayOfbyte != null && param1Int1 >= 0 && param1Int2 >= 0 && param1ArrayOfbyte.length - param1Int2 >= param1Int1) {
        long l1 = this.limit;
        long l2 = param1Int2;
        if (l1 - l2 >= this.position) {
          UnsafeUtil.copyMemory(param1ArrayOfbyte, param1Int1, this.position, l2);
          this.position += l2;
          return;
        } 
      } 
      if (param1ArrayOfbyte == null)
        throw new NullPointerException("value"); 
      throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(param1Int2) }));
    }
    
    public void writeBool(int param1Int, boolean param1Boolean) throws IOException {
      writeTag(param1Int, 0);
      write((byte)param1Boolean);
    }
    
    public void writeByteArray(int param1Int, byte[] param1ArrayOfbyte) throws IOException {
      writeByteArray(param1Int, param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public void writeByteArray(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3) throws IOException {
      writeTag(param1Int1, 2);
      writeByteArrayNoTag(param1ArrayOfbyte, param1Int2, param1Int3);
    }
    
    public void writeByteArrayNoTag(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(param1Int2);
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void writeByteBuffer(int param1Int, ByteBuffer param1ByteBuffer) throws IOException {
      writeTag(param1Int, 2);
      writeUInt32NoTag(param1ByteBuffer.capacity());
      writeRawBytes(param1ByteBuffer);
    }
    
    public void writeBytes(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(param1Int, 2);
      writeBytesNoTag(param1ByteString);
    }
    
    public void writeBytesNoTag(ByteString param1ByteString) throws IOException {
      writeUInt32NoTag(param1ByteString.size());
      param1ByteString.writeTo(this);
    }
    
    public void writeFixed32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 5);
      writeFixed32NoTag(param1Int2);
    }
    
    public void writeFixed32NoTag(int param1Int) throws IOException {
      this.buffer.putInt(bufferPos(this.position), param1Int);
      this.position += 4L;
    }
    
    public void writeFixed64(int param1Int, long param1Long) throws IOException {
      writeTag(param1Int, 1);
      writeFixed64NoTag(param1Long);
    }
    
    public void writeFixed64NoTag(long param1Long) throws IOException {
      this.buffer.putLong(bufferPos(this.position), param1Long);
      this.position += 8L;
    }
    
    public void writeInt32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 0);
      writeInt32NoTag(param1Int2);
    }
    
    public void writeInt32NoTag(int param1Int) throws IOException {
      if (param1Int >= 0) {
        writeUInt32NoTag(param1Int);
      } else {
        writeUInt64NoTag(param1Int);
      } 
    }
    
    public void writeLazy(ByteBuffer param1ByteBuffer) throws IOException {
      write(param1ByteBuffer);
    }
    
    public void writeLazy(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void writeMessage(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite);
    }
    
    void writeMessage(int param1Int, MessageLite param1MessageLite, Schema param1Schema) throws IOException {
      writeTag(param1Int, 2);
      writeMessageNoTag(param1MessageLite, param1Schema);
    }
    
    public void writeMessageNoTag(MessageLite param1MessageLite) throws IOException {
      writeUInt32NoTag(param1MessageLite.getSerializedSize());
      param1MessageLite.writeTo(this);
    }
    
    void writeMessageNoTag(MessageLite param1MessageLite, Schema<MessageLite> param1Schema) throws IOException {
      writeUInt32NoTag(((AbstractMessageLite)param1MessageLite).getSerializedSize(param1Schema));
      param1Schema.writeTo(param1MessageLite, this.wrapper);
    }
    
    public void writeMessageSetExtension(int param1Int, MessageLite param1MessageLite) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeMessage(3, param1MessageLite);
      writeTag(1, 4);
    }
    
    public void writeRawBytes(ByteBuffer param1ByteBuffer) throws IOException {
      if (param1ByteBuffer.hasArray()) {
        write(param1ByteBuffer.array(), param1ByteBuffer.arrayOffset(), param1ByteBuffer.capacity());
      } else {
        param1ByteBuffer = param1ByteBuffer.duplicate();
        param1ByteBuffer.clear();
        write(param1ByteBuffer);
      } 
    }
    
    public void writeRawMessageSetExtension(int param1Int, ByteString param1ByteString) throws IOException {
      writeTag(1, 3);
      writeUInt32(2, param1Int);
      writeBytes(3, param1ByteString);
      writeTag(1, 4);
    }
    
    public void writeString(int param1Int, String param1String) throws IOException {
      writeTag(param1Int, 2);
      writeStringNoTag(param1String);
    }
    
    public void writeStringNoTag(String param1String) throws IOException {
      long l = this.position;
      try {
        int i = computeUInt32SizeNoTag(param1String.length() * 3);
        int j = computeUInt32SizeNoTag(param1String.length());
        if (j == i) {
          i = bufferPos(this.position) + j;
          this.buffer.position(i);
          Utf8.encodeUtf8(param1String, this.buffer);
          i = this.buffer.position() - i;
          writeUInt32NoTag(i);
          this.position += i;
        } else {
          i = Utf8.encodedLength(param1String);
          writeUInt32NoTag(i);
          repositionBuffer(this.position);
          Utf8.encodeUtf8(param1String, this.buffer);
          this.position += i;
        } 
      } catch (UnpairedSurrogateException unpairedSurrogateException) {
        this.position = l;
        repositionBuffer(this.position);
        inefficientWriteStringNoTag(param1String, unpairedSurrogateException);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new CodedOutputStream.OutOfSpaceException(illegalArgumentException);
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new CodedOutputStream.OutOfSpaceException(indexOutOfBoundsException);
      } 
    }
    
    public void writeTag(int param1Int1, int param1Int2) throws IOException {
      writeUInt32NoTag(WireFormat.makeTag(param1Int1, param1Int2));
    }
    
    public void writeUInt32(int param1Int1, int param1Int2) throws IOException {
      writeTag(param1Int1, 0);
      writeUInt32NoTag(param1Int2);
    }
    
    public void writeUInt32NoTag(int param1Int) throws IOException {
      int i = param1Int;
      if (this.position <= this.oneVarintLimit)
        while (true) {
          if ((param1Int & 0xFFFFFF80) == 0) {
            long l1 = this.position;
            this.position = 1L + l1;
            UnsafeUtil.putByte(l1, (byte)param1Int);
            return;
          } 
          long l = this.position;
          this.position = l + 1L;
          UnsafeUtil.putByte(l, (byte)(param1Int & 0x7F | 0x80));
          param1Int >>>= 7;
        }  
      while (this.position < this.limit) {
        if ((i & 0xFFFFFF80) == 0) {
          long l1 = this.position;
          this.position = 1L + l1;
          UnsafeUtil.putByte(l1, (byte)i);
          return;
        } 
        long l = this.position;
        this.position = l + 1L;
        UnsafeUtil.putByte(l, (byte)(i & 0x7F | 0x80));
        i >>>= 7;
      } 
      throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(1) }));
    }
    
    public void writeUInt64(int param1Int, long param1Long) throws IOException {
      writeTag(param1Int, 0);
      writeUInt64NoTag(param1Long);
    }
    
    public void writeUInt64NoTag(long param1Long) throws IOException {
      long l = param1Long;
      if (this.position <= this.oneVarintLimit)
        while (true) {
          if ((param1Long & 0xFFFFFFFFFFFFFF80L) == 0L) {
            l = this.position;
            this.position = 1L + l;
            UnsafeUtil.putByte(l, (byte)(int)param1Long);
            return;
          } 
          l = this.position;
          this.position = l + 1L;
          UnsafeUtil.putByte(l, (byte)((int)param1Long & 0x7F | 0x80));
          param1Long >>>= 7L;
        }  
      while (this.position < this.limit) {
        if ((l & 0xFFFFFFFFFFFFFF80L) == 0L) {
          param1Long = this.position;
          this.position = 1L + param1Long;
          UnsafeUtil.putByte(param1Long, (byte)(int)l);
          return;
        } 
        param1Long = this.position;
        this.position = param1Long + 1L;
        UnsafeUtil.putByte(param1Long, (byte)((int)l & 0x7F | 0x80));
        l >>>= 7L;
      } 
      throw new CodedOutputStream.OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(1) }));
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\CodedOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */