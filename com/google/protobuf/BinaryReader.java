package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

abstract class BinaryReader implements Reader {
  private static final int FIXED32_MULTIPLE_MASK = 3;
  
  private static final int FIXED64_MULTIPLE_MASK = 7;
  
  private BinaryReader() {}
  
  public static BinaryReader newInstance(ByteBuffer paramByteBuffer, boolean paramBoolean) {
    if (paramByteBuffer.hasArray())
      return new SafeHeapReader(paramByteBuffer, paramBoolean); 
    throw new IllegalArgumentException("Direct buffers not yet supported");
  }
  
  public abstract int getTotalBytesRead();
  
  public boolean shouldDiscardUnknownFields() {
    return false;
  }
  
  private static final class SafeHeapReader extends BinaryReader {
    private final byte[] buffer;
    
    private final boolean bufferIsImmutable;
    
    private int endGroupTag;
    
    private final int initialPos;
    
    private int limit;
    
    private int pos;
    
    private int tag;
    
    public SafeHeapReader(ByteBuffer param1ByteBuffer, boolean param1Boolean) {
      this.bufferIsImmutable = param1Boolean;
      this.buffer = param1ByteBuffer.array();
      int i = param1ByteBuffer.arrayOffset() + param1ByteBuffer.position();
      this.pos = i;
      this.initialPos = i;
      this.limit = param1ByteBuffer.arrayOffset() + param1ByteBuffer.limit();
    }
    
    private boolean isAtEnd() {
      boolean bool;
      if (this.pos == this.limit) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private byte readByte() throws IOException {
      if (this.pos == this.limit)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      byte[] arrayOfByte = this.buffer;
      int i = this.pos;
      this.pos = i + 1;
      return arrayOfByte[i];
    }
    
    private Object readField(WireFormat.FieldType param1FieldType, Class<?> param1Class, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      switch (param1FieldType) {
        default:
          throw new RuntimeException("unsupported field type.");
        case UINT64:
          return Long.valueOf(readUInt64());
        case UINT32:
          return Integer.valueOf(readUInt32());
        case STRING:
          return readStringRequireUtf8();
        case SINT64:
          return Long.valueOf(readSInt64());
        case SINT32:
          return Integer.valueOf(readSInt32());
        case SFIXED64:
          return Long.valueOf(readSFixed64());
        case SFIXED32:
          return Integer.valueOf(readSFixed32());
        case MESSAGE:
          return readMessage(param1Class, param1ExtensionRegistryLite);
        case INT64:
          return Long.valueOf(readInt64());
        case INT32:
          return Integer.valueOf(readInt32());
        case FLOAT:
          return Float.valueOf(readFloat());
        case FIXED64:
          return Long.valueOf(readFixed64());
        case FIXED32:
          return Integer.valueOf(readFixed32());
        case ENUM:
          return Integer.valueOf(readEnum());
        case DOUBLE:
          return Double.valueOf(readDouble());
        case BYTES:
          return readBytes();
        case BOOL:
          break;
      } 
      return Boolean.valueOf(readBool());
    }
    
    private <T> T readGroup(Schema<T> param1Schema, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = this.endGroupTag;
      this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
      try {
        T t = param1Schema.newInstance();
        param1Schema.mergeFrom(t, this, param1ExtensionRegistryLite);
        param1Schema.makeImmutable(t);
        if (this.tag != this.endGroupTag)
          throw InvalidProtocolBufferException.parseFailure(); 
        return t;
      } finally {
        this.endGroupTag = i;
      } 
    }
    
    private int readLittleEndian32() throws IOException {
      requireBytes(4);
      return readLittleEndian32_NoCheck();
    }
    
    private int readLittleEndian32_NoCheck() {
      int i = this.pos;
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 4;
      return arrayOfByte[i] & 0xFF | (arrayOfByte[i + 1] & 0xFF) << 8 | (arrayOfByte[i + 2] & 0xFF) << 16 | (arrayOfByte[i + 3] & 0xFF) << 24;
    }
    
    private long readLittleEndian64() throws IOException {
      requireBytes(8);
      return readLittleEndian64_NoCheck();
    }
    
    private long readLittleEndian64_NoCheck() {
      int i = this.pos;
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
    
    private <T> T readMessage(Schema<T> param1Schema, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      int i = readVarint32();
      requireBytes(i);
      int j = this.limit;
      i = this.pos + i;
      this.limit = i;
      try {
        T t = param1Schema.newInstance();
        param1Schema.mergeFrom(t, this, param1ExtensionRegistryLite);
        param1Schema.makeImmutable(t);
        if (this.pos != i)
          throw InvalidProtocolBufferException.parseFailure(); 
        return t;
      } finally {
        this.limit = j;
      } 
    }
    
    private int readVarint32() throws IOException {
      int i = this.pos;
      if (this.limit == this.pos)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      byte[] arrayOfByte = this.buffer;
      int j = i + 1;
      byte b = arrayOfByte[i];
      if (b >= 0) {
        this.pos = j;
        return b;
      } 
      if (this.limit - j < 9)
        return (int)readVarint64SlowPath(); 
      arrayOfByte = this.buffer;
      i = j + 1;
      int k = b ^ arrayOfByte[j] << 7;
      if (k < 0) {
        j = k ^ 0xFFFFFF80;
      } else {
        arrayOfByte = this.buffer;
        j = i + 1;
        k ^= arrayOfByte[i] << 14;
        if (k >= 0) {
          k ^= 0x3F80;
          i = j;
          j = k;
        } else {
          arrayOfByte = this.buffer;
          i = j + 1;
          j = k ^ arrayOfByte[j] << 21;
          if (j < 0) {
            j ^= 0xFFE03F80;
          } else {
            arrayOfByte = this.buffer;
            int m = i + 1;
            byte b1 = arrayOfByte[i];
            k = j ^ b1 << 28 ^ 0xFE03F80;
            j = k;
            i = m;
            if (b1 < 0) {
              arrayOfByte = this.buffer;
              int n = m + 1;
              j = k;
              i = n;
              if (arrayOfByte[m] < 0) {
                arrayOfByte = this.buffer;
                m = n + 1;
                j = k;
                i = m;
                if (arrayOfByte[n] < 0) {
                  arrayOfByte = this.buffer;
                  n = m + 1;
                  j = k;
                  i = n;
                  if (arrayOfByte[m] < 0) {
                    arrayOfByte = this.buffer;
                    m = n + 1;
                    j = k;
                    i = m;
                    if (arrayOfByte[n] < 0) {
                      arrayOfByte = this.buffer;
                      i = m + 1;
                      j = k;
                      if (arrayOfByte[m] < 0)
                        throw InvalidProtocolBufferException.malformedVarint(); 
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
    
    private long readVarint64SlowPath() throws IOException {
      long l = 0L;
      for (byte b = 0; b < 64; b += 7) {
        byte b1 = readByte();
        l |= (b1 & Byte.MAX_VALUE) << b;
        if ((b1 & 0x80) == 0)
          return l; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private void requireBytes(int param1Int) throws IOException {
      if (param1Int < 0 || param1Int > this.limit - this.pos)
        throw InvalidProtocolBufferException.truncatedMessage(); 
    }
    
    private void requirePosition(int param1Int) throws IOException {
      if (this.pos != param1Int)
        throw InvalidProtocolBufferException.truncatedMessage(); 
    }
    
    private void requireWireType(int param1Int) throws IOException {
      if (WireFormat.getTagWireType(this.tag) != param1Int)
        throw InvalidProtocolBufferException.invalidWireType(); 
    }
    
    private void skipBytes(int param1Int) throws IOException {
      requireBytes(param1Int);
      this.pos += param1Int;
    }
    
    private void skipGroup() throws IOException {
      int i = this.endGroupTag;
      this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
      do {
      
      } while (getFieldNumber() != Integer.MAX_VALUE && skipField());
      if (this.tag != this.endGroupTag)
        throw InvalidProtocolBufferException.parseFailure(); 
      this.endGroupTag = i;
    }
    
    private void skipVarint() throws IOException {
      if (this.limit - this.pos >= 10) {
        byte[] arrayOfByte = this.buffer;
        int i = this.pos;
        byte b = 0;
        while (b < 10) {
          int j = i + 1;
          if (arrayOfByte[i] >= 0) {
            this.pos = j;
            return;
          } 
          b++;
          i = j;
        } 
      } 
      skipVarintSlowPath();
    }
    
    private void skipVarintSlowPath() throws IOException {
      for (byte b = 0; b < 10; b++) {
        if (readByte() >= 0)
          return; 
      } 
      throw InvalidProtocolBufferException.malformedVarint();
    }
    
    private void verifyPackedFixed32Length(int param1Int) throws IOException {
      requireBytes(param1Int);
      if ((param1Int & 0x3) != 0)
        throw InvalidProtocolBufferException.parseFailure(); 
    }
    
    private void verifyPackedFixed64Length(int param1Int) throws IOException {
      requireBytes(param1Int);
      if ((param1Int & 0x7) != 0)
        throw InvalidProtocolBufferException.parseFailure(); 
    }
    
    public int getFieldNumber() throws IOException {
      if (isAtEnd())
        return Integer.MAX_VALUE; 
      this.tag = readVarint32();
      return (this.tag == this.endGroupTag) ? Integer.MAX_VALUE : WireFormat.getTagFieldNumber(this.tag);
    }
    
    public int getTag() {
      return this.tag;
    }
    
    public int getTotalBytesRead() {
      return this.pos - this.initialPos;
    }
    
    public boolean readBool() throws IOException {
      boolean bool = false;
      requireWireType(0);
      if (readVarint32() != 0)
        bool = true; 
      return bool;
    }
    
    public void readBoolList(List<Boolean> param1List) throws IOException {
      if (param1List instanceof BooleanArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i) {
            boolean bool;
            if (readVarint32() != 0) {
              bool = true;
            } else {
              bool = false;
            } 
            param1List.addBoolean(bool);
          } 
          requirePosition(i);
        } else {
          while (true) {
            param1List.addBoolean(readBool());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i) {
            boolean bool;
            if (readVarint32() != 0) {
              bool = true;
            } else {
              bool = false;
            } 
            param1List.add(Boolean.valueOf(bool));
          } 
          requirePosition(i);
          return;
        } 
        while (true) {
          param1List.add(Boolean.valueOf(readBool()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public ByteString readBytes() throws IOException {
      ByteString byteString;
      requireWireType(2);
      int i = readVarint32();
      if (i == 0)
        return ByteString.EMPTY; 
      requireBytes(i);
      if (this.bufferIsImmutable) {
        byteString = ByteString.wrap(this.buffer, this.pos, i);
      } else {
        byteString = ByteString.copyFrom(this.buffer, this.pos, i);
      } 
      this.pos += i;
      return byteString;
    }
    
    public void readBytesList(List<ByteString> param1List) throws IOException {
      if (WireFormat.getTagWireType(this.tag) != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      while (true) {
        param1List.add(readBytes());
        if (isAtEnd())
          return; 
        int i = this.pos;
        if (readVarint32() != this.tag) {
          this.pos = i;
          return;
        } 
      } 
    }
    
    public double readDouble() throws IOException {
      requireWireType(1);
      return Double.longBitsToDouble(readLittleEndian64());
    }
    
    public void readDoubleList(List<Double> param1List) throws IOException {
      int i;
      int j;
      if (param1List instanceof DoubleArrayList) {
        int k;
        int m;
        param1List = param1List;
        switch (WireFormat.getTagWireType(this.tag)) {
          default:
            throw InvalidProtocolBufferException.invalidWireType();
          case 2:
            k = readVarint32();
            verifyPackedFixed64Length(k);
            m = this.pos;
            while (this.pos < m + k)
              param1List.addDouble(Double.longBitsToDouble(readLittleEndian64_NoCheck())); 
            return;
          case 1:
            break;
        } 
        while (true) {
          param1List.addDouble(readDouble());
          if (isAtEnd())
            return; 
          k = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = k;
            return;
          } 
        } 
      } 
      switch (WireFormat.getTagWireType(this.tag)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 2:
          j = readVarint32();
          verifyPackedFixed64Length(j);
          i = this.pos;
          while (this.pos < i + j)
            param1List.add(Double.valueOf(Double.longBitsToDouble(readLittleEndian64_NoCheck()))); 
          return;
        case 1:
          break;
      } 
      while (true) {
        param1List.add(Double.valueOf(readDouble()));
        if (isAtEnd())
          return; 
        i = this.pos;
        if (readVarint32() != this.tag) {
          this.pos = i;
          return;
        } 
      } 
    }
    
    public int readEnum() throws IOException {
      requireWireType(0);
      return readVarint32();
    }
    
    public void readEnumList(List<Integer> param1List) throws IOException {
      if (param1List instanceof IntArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          int j = this.pos;
          while (this.pos < j + i)
            param1List.addInt(readVarint32()); 
        } else {
          while (true) {
            param1List.addInt(readEnum());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          int j = this.pos;
          while (this.pos < j + i)
            param1List.add(Integer.valueOf(readVarint32())); 
          return;
        } 
        while (true) {
          param1List.add(Integer.valueOf(readEnum()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public int readFixed32() throws IOException {
      requireWireType(5);
      return readLittleEndian32();
    }
    
    public void readFixed32List(List<Integer> param1List) throws IOException {
      if (param1List instanceof IntArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 2) {
          if (i != 5)
            throw InvalidProtocolBufferException.invalidWireType(); 
          while (true) {
            param1List.addInt(readFixed32());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
        int j = readVarint32();
        verifyPackedFixed32Length(j);
        i = this.pos;
        while (this.pos < i + j)
          param1List.addInt(readLittleEndian32_NoCheck()); 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 2) {
          if (i != 5)
            throw InvalidProtocolBufferException.invalidWireType(); 
          while (true) {
            param1List.add(Integer.valueOf(readFixed32()));
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
        i = readVarint32();
        verifyPackedFixed32Length(i);
        int j = this.pos;
        while (this.pos < j + i)
          param1List.add(Integer.valueOf(readLittleEndian32_NoCheck())); 
      } 
    }
    
    public long readFixed64() throws IOException {
      requireWireType(1);
      return readLittleEndian64();
    }
    
    public void readFixed64List(List<Long> param1List) throws IOException {
      int i;
      int j;
      if (param1List instanceof LongArrayList) {
        int k;
        int m;
        param1List = param1List;
        switch (WireFormat.getTagWireType(this.tag)) {
          default:
            throw InvalidProtocolBufferException.invalidWireType();
          case 2:
            k = readVarint32();
            verifyPackedFixed64Length(k);
            m = this.pos;
            while (this.pos < m + k)
              param1List.addLong(readLittleEndian64_NoCheck()); 
            return;
          case 1:
            break;
        } 
        while (true) {
          param1List.addLong(readFixed64());
          if (isAtEnd())
            return; 
          k = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = k;
            return;
          } 
        } 
      } 
      switch (WireFormat.getTagWireType(this.tag)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 2:
          i = readVarint32();
          verifyPackedFixed64Length(i);
          j = this.pos;
          while (this.pos < j + i)
            param1List.add(Long.valueOf(readLittleEndian64_NoCheck())); 
          return;
        case 1:
          break;
      } 
      while (true) {
        param1List.add(Long.valueOf(readFixed64()));
        if (isAtEnd())
          return; 
        i = this.pos;
        if (readVarint32() != this.tag) {
          this.pos = i;
          return;
        } 
      } 
    }
    
    public float readFloat() throws IOException {
      requireWireType(5);
      return Float.intBitsToFloat(readLittleEndian32());
    }
    
    public void readFloatList(List<Float> param1List) throws IOException {
      if (param1List instanceof FloatArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 2) {
          if (i != 5)
            throw InvalidProtocolBufferException.invalidWireType(); 
          while (true) {
            param1List.addFloat(readFloat());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
        int j = readVarint32();
        verifyPackedFixed32Length(j);
        i = this.pos;
        while (this.pos < i + j)
          param1List.addFloat(Float.intBitsToFloat(readLittleEndian32_NoCheck())); 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 2) {
          if (i != 5)
            throw InvalidProtocolBufferException.invalidWireType(); 
          while (true) {
            param1List.add(Float.valueOf(readFloat()));
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
        i = readVarint32();
        verifyPackedFixed32Length(i);
        int j = this.pos;
        while (this.pos < j + i)
          param1List.add(Float.valueOf(Float.intBitsToFloat(readLittleEndian32_NoCheck()))); 
      } 
    }
    
    public <T> T readGroup(Class<T> param1Class, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      requireWireType(3);
      return readGroup(Protobuf.getInstance().schemaFor(param1Class), param1ExtensionRegistryLite);
    }
    
    public <T> T readGroupBySchemaWithCheck(Schema<T> param1Schema, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      requireWireType(3);
      return readGroup(param1Schema, param1ExtensionRegistryLite);
    }
    
    public <T> void readGroupList(List<T> param1List, Schema<T> param1Schema, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (WireFormat.getTagWireType(this.tag) != 3)
        throw InvalidProtocolBufferException.invalidWireType(); 
      int i = this.tag;
      while (true) {
        param1List.add(readGroup(param1Schema, param1ExtensionRegistryLite));
        if (isAtEnd())
          return; 
        int j = this.pos;
        if (readVarint32() != i) {
          this.pos = j;
          return;
        } 
      } 
    }
    
    public <T> void readGroupList(List<T> param1List, Class<T> param1Class, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      readGroupList(param1List, Protobuf.getInstance().schemaFor(param1Class), param1ExtensionRegistryLite);
    }
    
    public int readInt32() throws IOException {
      requireWireType(0);
      return readVarint32();
    }
    
    public void readInt32List(List<Integer> param1List) throws IOException {
      if (param1List instanceof IntArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i)
            param1List.addInt(readVarint32()); 
          requirePosition(i);
        } else {
          while (true) {
            param1List.addInt(readInt32());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i)
            param1List.add(Integer.valueOf(readVarint32())); 
          requirePosition(i);
          return;
        } 
        while (true) {
          param1List.add(Integer.valueOf(readInt32()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public long readInt64() throws IOException {
      requireWireType(0);
      return readVarint64();
    }
    
    public void readInt64List(List<Long> param1List) throws IOException {
      if (param1List instanceof LongArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i)
            param1List.addLong(readVarint64()); 
          requirePosition(i);
        } else {
          while (true) {
            param1List.addLong(readInt64());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i)
            param1List.add(Long.valueOf(readVarint64())); 
          requirePosition(i);
          return;
        } 
        while (true) {
          param1List.add(Long.valueOf(readInt64()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public <K, V> void readMap(Map<K, V> param1Map, MapEntryLite.Metadata<K, V> param1Metadata, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      // Byte code:
      //   0: aload_0
      //   1: iconst_2
      //   2: invokespecial requireWireType : (I)V
      //   5: aload_0
      //   6: invokespecial readVarint32 : ()I
      //   9: istore #4
      //   11: aload_0
      //   12: iload #4
      //   14: invokespecial requireBytes : (I)V
      //   17: aload_0
      //   18: getfield limit : I
      //   21: istore #5
      //   23: aload_0
      //   24: aload_0
      //   25: getfield pos : I
      //   28: iload #4
      //   30: iadd
      //   31: putfield limit : I
      //   34: aload_2
      //   35: getfield defaultKey : Ljava/lang/Object;
      //   38: astore #6
      //   40: aload_2
      //   41: getfield defaultValue : Ljava/lang/Object;
      //   44: astore #7
      //   46: aload #7
      //   48: astore #8
      //   50: aload #6
      //   52: astore #9
      //   54: aload_0
      //   55: invokevirtual getFieldNumber : ()I
      //   58: istore #4
      //   60: iload #4
      //   62: ldc 2147483647
      //   64: if_icmpne -> 85
      //   67: aload_1
      //   68: aload #9
      //   70: aload #8
      //   72: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   77: pop
      //   78: aload_0
      //   79: iload #5
      //   81: putfield limit : I
      //   84: return
      //   85: iload #4
      //   87: tableswitch default -> 108, 1 -> 142, 2 -> 117
      //   108: aload_0
      //   109: invokevirtual skipField : ()Z
      //   112: istore #10
      //   114: goto -> 161
      //   117: aload_0
      //   118: aload_2
      //   119: getfield valueType : Lcom/google/protobuf/WireFormat$FieldType;
      //   122: aload_2
      //   123: getfield defaultValue : Ljava/lang/Object;
      //   126: invokevirtual getClass : ()Ljava/lang/Class;
      //   129: aload_3
      //   130: invokespecial readField : (Lcom/google/protobuf/WireFormat$FieldType;Ljava/lang/Class;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
      //   133: astore #7
      //   135: aload #9
      //   137: astore #6
      //   139: goto -> 46
      //   142: aload_0
      //   143: aload_2
      //   144: getfield keyType : Lcom/google/protobuf/WireFormat$FieldType;
      //   147: aconst_null
      //   148: aconst_null
      //   149: invokespecial readField : (Lcom/google/protobuf/WireFormat$FieldType;Ljava/lang/Class;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
      //   152: astore #6
      //   154: aload #8
      //   156: astore #7
      //   158: goto -> 46
      //   161: aload #9
      //   163: astore #6
      //   165: aload #8
      //   167: astore #7
      //   169: iload #10
      //   171: ifne -> 46
      //   174: new com/google/protobuf/InvalidProtocolBufferException
      //   177: astore #6
      //   179: aload #6
      //   181: ldc_w 'Unable to parse map entry.'
      //   184: invokespecial <init> : (Ljava/lang/String;)V
      //   187: aload #6
      //   189: athrow
      //   190: astore #6
      //   192: aload #9
      //   194: astore #6
      //   196: aload #8
      //   198: astore #7
      //   200: aload_0
      //   201: invokevirtual skipField : ()Z
      //   204: ifne -> 46
      //   207: new com/google/protobuf/InvalidProtocolBufferException
      //   210: astore_1
      //   211: aload_1
      //   212: ldc_w 'Unable to parse map entry.'
      //   215: invokespecial <init> : (Ljava/lang/String;)V
      //   218: aload_1
      //   219: athrow
      //   220: astore_1
      //   221: aload_0
      //   222: iload #5
      //   224: putfield limit : I
      //   227: aload_1
      //   228: athrow
      // Exception table:
      //   from	to	target	type
      //   34	46	220	finally
      //   54	60	220	finally
      //   67	78	220	finally
      //   108	114	190	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
      //   108	114	220	finally
      //   117	135	190	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
      //   117	135	220	finally
      //   142	154	190	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
      //   142	154	220	finally
      //   174	190	190	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
      //   174	190	220	finally
      //   200	220	220	finally
    }
    
    public <T> T readMessage(Class<T> param1Class, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      requireWireType(2);
      return readMessage(Protobuf.getInstance().schemaFor(param1Class), param1ExtensionRegistryLite);
    }
    
    public <T> T readMessageBySchemaWithCheck(Schema<T> param1Schema, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      requireWireType(2);
      return readMessage(param1Schema, param1ExtensionRegistryLite);
    }
    
    public <T> void readMessageList(List<T> param1List, Schema<T> param1Schema, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      if (WireFormat.getTagWireType(this.tag) != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      int i = this.tag;
      while (true) {
        param1List.add(readMessage(param1Schema, param1ExtensionRegistryLite));
        if (isAtEnd())
          return; 
        int j = this.pos;
        if (readVarint32() != i) {
          this.pos = j;
          return;
        } 
      } 
    }
    
    public <T> void readMessageList(List<T> param1List, Class<T> param1Class, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      readMessageList(param1List, Protobuf.getInstance().schemaFor(param1Class), param1ExtensionRegistryLite);
    }
    
    public int readSFixed32() throws IOException {
      requireWireType(5);
      return readLittleEndian32();
    }
    
    public void readSFixed32List(List<Integer> param1List) throws IOException {
      if (param1List instanceof IntArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 2) {
          if (i != 5)
            throw InvalidProtocolBufferException.invalidWireType(); 
          while (true) {
            param1List.addInt(readSFixed32());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
        i = readVarint32();
        verifyPackedFixed32Length(i);
        int j = this.pos;
        while (this.pos < j + i)
          param1List.addInt(readLittleEndian32_NoCheck()); 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 2) {
          if (i != 5)
            throw InvalidProtocolBufferException.invalidWireType(); 
          while (true) {
            param1List.add(Integer.valueOf(readSFixed32()));
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
        i = readVarint32();
        verifyPackedFixed32Length(i);
        int j = this.pos;
        while (this.pos < j + i)
          param1List.add(Integer.valueOf(readLittleEndian32_NoCheck())); 
      } 
    }
    
    public long readSFixed64() throws IOException {
      requireWireType(1);
      return readLittleEndian64();
    }
    
    public void readSFixed64List(List<Long> param1List) throws IOException {
      int i;
      int j;
      if (param1List instanceof LongArrayList) {
        int k;
        int m;
        param1List = param1List;
        switch (WireFormat.getTagWireType(this.tag)) {
          default:
            throw InvalidProtocolBufferException.invalidWireType();
          case 2:
            k = readVarint32();
            verifyPackedFixed64Length(k);
            m = this.pos;
            while (this.pos < m + k)
              param1List.addLong(readLittleEndian64_NoCheck()); 
            return;
          case 1:
            break;
        } 
        while (true) {
          param1List.addLong(readSFixed64());
          if (isAtEnd())
            return; 
          k = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = k;
            return;
          } 
        } 
      } 
      switch (WireFormat.getTagWireType(this.tag)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 2:
          j = readVarint32();
          verifyPackedFixed64Length(j);
          i = this.pos;
          while (this.pos < i + j)
            param1List.add(Long.valueOf(readLittleEndian64_NoCheck())); 
          return;
        case 1:
          break;
      } 
      while (true) {
        param1List.add(Long.valueOf(readSFixed64()));
        if (isAtEnd())
          return; 
        i = this.pos;
        if (readVarint32() != this.tag) {
          this.pos = i;
          return;
        } 
      } 
    }
    
    public int readSInt32() throws IOException {
      requireWireType(0);
      return CodedInputStream.decodeZigZag32(readVarint32());
    }
    
    public void readSInt32List(List<Integer> param1List) throws IOException {
      if (param1List instanceof IntArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          int j = this.pos;
          while (this.pos < j + i)
            param1List.addInt(CodedInputStream.decodeZigZag32(readVarint32())); 
        } else {
          while (true) {
            param1List.addInt(readSInt32());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          int j = this.pos;
          while (this.pos < j + i)
            param1List.add(Integer.valueOf(CodedInputStream.decodeZigZag32(readVarint32()))); 
          return;
        } 
        while (true) {
          param1List.add(Integer.valueOf(readSInt32()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public long readSInt64() throws IOException {
      requireWireType(0);
      return CodedInputStream.decodeZigZag64(readVarint64());
    }
    
    public void readSInt64List(List<Long> param1List) throws IOException {
      if (param1List instanceof LongArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          int j = this.pos;
          while (this.pos < j + i)
            param1List.addLong(CodedInputStream.decodeZigZag64(readVarint64())); 
        } else {
          while (true) {
            param1List.addLong(readSInt64());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          int j = readVarint32();
          i = this.pos;
          while (this.pos < i + j)
            param1List.add(Long.valueOf(CodedInputStream.decodeZigZag64(readVarint64()))); 
          return;
        } 
        while (true) {
          param1List.add(Long.valueOf(readSInt64()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public String readString() throws IOException {
      return readStringInternal(false);
    }
    
    public String readStringInternal(boolean param1Boolean) throws IOException {
      requireWireType(2);
      int i = readVarint32();
      if (i == 0)
        return ""; 
      requireBytes(i);
      if (param1Boolean && !Utf8.isValidUtf8(this.buffer, this.pos, this.pos + i))
        throw InvalidProtocolBufferException.invalidUtf8(); 
      String str = new String(this.buffer, this.pos, i, Internal.UTF_8);
      this.pos += i;
      return str;
    }
    
    public void readStringList(List<String> param1List) throws IOException {
      readStringListInternal(param1List, false);
    }
    
    public void readStringListInternal(List<String> param1List, boolean param1Boolean) throws IOException {
      if (WireFormat.getTagWireType(this.tag) != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      if (param1List instanceof LazyStringList && !param1Boolean) {
        param1List = param1List;
        while (true) {
          param1List.add(readBytes());
          if (isAtEnd())
            return; 
          int i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
      while (true) {
        param1List.add(readStringInternal(param1Boolean));
        if (isAtEnd())
          return; 
        int i = this.pos;
        if (readVarint32() != this.tag) {
          this.pos = i;
          return;
        } 
      } 
    }
    
    public void readStringListRequireUtf8(List<String> param1List) throws IOException {
      readStringListInternal(param1List, true);
    }
    
    public String readStringRequireUtf8() throws IOException {
      return readStringInternal(true);
    }
    
    public int readUInt32() throws IOException {
      requireWireType(0);
      return readVarint32();
    }
    
    public void readUInt32List(List<Integer> param1List) throws IOException {
      if (param1List instanceof IntArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          int j = readVarint32();
          i = this.pos;
          while (this.pos < i + j)
            param1List.addInt(readVarint32()); 
        } else {
          while (true) {
            param1List.addInt(readUInt32());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          int j = this.pos;
          while (this.pos < j + i)
            param1List.add(Integer.valueOf(readVarint32())); 
          return;
        } 
        while (true) {
          param1List.add(Integer.valueOf(readUInt32()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public long readUInt64() throws IOException {
      requireWireType(0);
      return readVarint64();
    }
    
    public void readUInt64List(List<Long> param1List) throws IOException {
      if (param1List instanceof LongArrayList) {
        param1List = param1List;
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i)
            param1List.addLong(readVarint64()); 
          requirePosition(i);
        } else {
          while (true) {
            param1List.addLong(readUInt64());
            if (isAtEnd())
              return; 
            i = this.pos;
            if (readVarint32() != this.tag) {
              this.pos = i;
              return;
            } 
          } 
        } 
      } else {
        int i = WireFormat.getTagWireType(this.tag);
        if (i != 0) {
          if (i != 2)
            throw InvalidProtocolBufferException.invalidWireType(); 
          i = readVarint32();
          i = this.pos + i;
          while (this.pos < i)
            param1List.add(Long.valueOf(readVarint64())); 
          requirePosition(i);
          return;
        } 
        while (true) {
          param1List.add(Long.valueOf(readUInt64()));
          if (isAtEnd())
            return; 
          i = this.pos;
          if (readVarint32() != this.tag) {
            this.pos = i;
            return;
          } 
        } 
      } 
    }
    
    public long readVarint64() throws IOException {
      long l;
      int i = this.pos;
      if (this.limit == i)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      byte[] arrayOfByte = this.buffer;
      int j = i + 1;
      byte b = arrayOfByte[i];
      if (b >= 0) {
        this.pos = j;
        return b;
      } 
      if (this.limit - j < 9)
        return readVarint64SlowPath(); 
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
                        throw InvalidProtocolBufferException.malformedVarint(); 
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
    
    public boolean skipField() throws IOException {
      if (isAtEnd() || this.tag == this.endGroupTag)
        return false; 
      int i = WireFormat.getTagWireType(this.tag);
      if (i != 5) {
        switch (i) {
          default:
            throw InvalidProtocolBufferException.invalidWireType();
          case 3:
            skipGroup();
            return true;
          case 2:
            skipBytes(readVarint32());
            return true;
          case 1:
            skipBytes(8);
            return true;
          case 0:
            break;
        } 
        skipVarint();
        return true;
      } 
      skipBytes(4);
      return true;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\BinaryReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */