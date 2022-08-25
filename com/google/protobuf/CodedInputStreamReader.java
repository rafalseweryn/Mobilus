package com.google.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

final class CodedInputStreamReader implements Reader {
  private static final int FIXED32_MULTIPLE_MASK = 3;
  
  private static final int FIXED64_MULTIPLE_MASK = 7;
  
  private static final int NEXT_TAG_UNSET = 0;
  
  private int endGroupTag;
  
  private final CodedInputStream input;
  
  private int nextTag = 0;
  
  private int tag;
  
  private CodedInputStreamReader(CodedInputStream paramCodedInputStream) {
    this.input = Internal.<CodedInputStream>checkNotNull(paramCodedInputStream, "input");
    this.input.wrapper = this;
  }
  
  public static CodedInputStreamReader forCodedInput(CodedInputStream paramCodedInputStream) {
    return (paramCodedInputStream.wrapper != null) ? paramCodedInputStream.wrapper : new CodedInputStreamReader(paramCodedInputStream);
  }
  
  private Object readField(WireFormat.FieldType paramFieldType, Class<?> paramClass, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    switch (paramFieldType) {
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
        return readMessage(paramClass, paramExtensionRegistryLite);
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
  
  private <T> T readGroup(Schema<T> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    int i = this.endGroupTag;
    this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
    try {
      T t = paramSchema.newInstance();
      paramSchema.mergeFrom(t, this, paramExtensionRegistryLite);
      paramSchema.makeImmutable(t);
      if (this.tag != this.endGroupTag)
        throw InvalidProtocolBufferException.parseFailure(); 
      return t;
    } finally {
      this.endGroupTag = i;
    } 
  }
  
  private <T> T readMessage(Schema<T> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    int i = this.input.readUInt32();
    if (this.input.recursionDepth >= this.input.recursionLimit)
      throw InvalidProtocolBufferException.recursionLimitExceeded(); 
    i = this.input.pushLimit(i);
    T t = paramSchema.newInstance();
    CodedInputStream codedInputStream2 = this.input;
    codedInputStream2.recursionDepth++;
    paramSchema.mergeFrom(t, this, paramExtensionRegistryLite);
    paramSchema.makeImmutable(t);
    this.input.checkLastTagWas(0);
    CodedInputStream codedInputStream1 = this.input;
    codedInputStream1.recursionDepth--;
    this.input.popLimit(i);
    return t;
  }
  
  private void requirePosition(int paramInt) throws IOException {
    if (this.input.getTotalBytesRead() != paramInt)
      throw InvalidProtocolBufferException.truncatedMessage(); 
  }
  
  private void requireWireType(int paramInt) throws IOException {
    if (WireFormat.getTagWireType(this.tag) != paramInt)
      throw InvalidProtocolBufferException.invalidWireType(); 
  }
  
  private void verifyPackedFixed32Length(int paramInt) throws IOException {
    if ((paramInt & 0x3) != 0)
      throw InvalidProtocolBufferException.parseFailure(); 
  }
  
  private void verifyPackedFixed64Length(int paramInt) throws IOException {
    if ((paramInt & 0x7) != 0)
      throw InvalidProtocolBufferException.parseFailure(); 
  }
  
  public int getFieldNumber() throws IOException {
    if (this.nextTag != 0) {
      this.tag = this.nextTag;
      this.nextTag = 0;
    } else {
      this.tag = this.input.readTag();
    } 
    return (this.tag == 0 || this.tag == this.endGroupTag) ? Integer.MAX_VALUE : WireFormat.getTagFieldNumber(this.tag);
  }
  
  public int getTag() {
    return this.tag;
  }
  
  public boolean readBool() throws IOException {
    requireWireType(0);
    return this.input.readBool();
  }
  
  public void readBoolList(List<Boolean> paramList) throws IOException {
    if (paramList instanceof BooleanArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addBoolean(this.input.readBool());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addBoolean(this.input.readBool());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Boolean.valueOf(this.input.readBool()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Boolean.valueOf(this.input.readBool()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public ByteString readBytes() throws IOException {
    requireWireType(2);
    return this.input.readBytes();
  }
  
  public void readBytesList(List<ByteString> paramList) throws IOException {
    if (WireFormat.getTagWireType(this.tag) != 2)
      throw InvalidProtocolBufferException.invalidWireType(); 
    while (true) {
      paramList.add(readBytes());
      if (this.input.isAtEnd())
        return; 
      int i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public double readDouble() throws IOException {
    requireWireType(1);
    return this.input.readDouble();
  }
  
  public void readDoubleList(List<Double> paramList) throws IOException {
    int i;
    int j;
    if (paramList instanceof DoubleArrayList) {
      int k;
      int m;
      paramList = paramList;
      switch (WireFormat.getTagWireType(this.tag)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 2:
          k = this.input.readUInt32();
          verifyPackedFixed64Length(k);
          m = this.input.getTotalBytesRead();
          do {
            paramList.addDouble(this.input.readDouble());
          } while (this.input.getTotalBytesRead() < m + k);
          return;
        case 1:
          break;
      } 
      while (true) {
        paramList.addDouble(this.input.readDouble());
        if (this.input.isAtEnd())
          return; 
        m = this.input.readTag();
        if (m != this.tag) {
          this.nextTag = m;
          return;
        } 
      } 
    } 
    switch (WireFormat.getTagWireType(this.tag)) {
      default:
        throw InvalidProtocolBufferException.invalidWireType();
      case 2:
        j = this.input.readUInt32();
        verifyPackedFixed64Length(j);
        i = this.input.getTotalBytesRead();
        do {
          paramList.add(Double.valueOf(this.input.readDouble()));
        } while (this.input.getTotalBytesRead() < i + j);
        return;
      case 1:
        break;
    } 
    while (true) {
      paramList.add(Double.valueOf(this.input.readDouble()));
      if (this.input.isAtEnd())
        return; 
      j = this.input.readTag();
      if (j != this.tag) {
        this.nextTag = j;
        return;
      } 
    } 
  }
  
  public int readEnum() throws IOException {
    requireWireType(0);
    return this.input.readEnum();
  }
  
  public void readEnumList(List<Integer> paramList) throws IOException {
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addInt(this.input.readEnum());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addInt(this.input.readEnum());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Integer.valueOf(this.input.readEnum()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.input.readEnum()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public int readFixed32() throws IOException {
    requireWireType(5);
    return this.input.readFixed32();
  }
  
  public void readFixed32List(List<Integer> paramList) throws IOException {
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int i = WireFormat.getTagWireType(this.tag);
      if (i != 2) {
        if (i != 5)
          throw InvalidProtocolBufferException.invalidWireType(); 
        while (true) {
          paramList.addInt(this.input.readFixed32());
          if (this.input.isAtEnd())
            return; 
          i = this.input.readTag();
          if (i != this.tag) {
            this.nextTag = i;
            return;
          } 
        } 
      } 
      int j = this.input.readUInt32();
      verifyPackedFixed32Length(j);
      i = this.input.getTotalBytesRead();
      do {
        paramList.addInt(this.input.readFixed32());
      } while (this.input.getTotalBytesRead() < i + j);
    } else {
      int i = WireFormat.getTagWireType(this.tag);
      if (i != 2) {
        if (i != 5)
          throw InvalidProtocolBufferException.invalidWireType(); 
        while (true) {
          paramList.add(Integer.valueOf(this.input.readFixed32()));
          if (this.input.isAtEnd())
            return; 
          i = this.input.readTag();
          if (i != this.tag) {
            this.nextTag = i;
            return;
          } 
        } 
      } 
      int j = this.input.readUInt32();
      verifyPackedFixed32Length(j);
      i = this.input.getTotalBytesRead();
      do {
        paramList.add(Integer.valueOf(this.input.readFixed32()));
      } while (this.input.getTotalBytesRead() < i + j);
    } 
  }
  
  public long readFixed64() throws IOException {
    requireWireType(1);
    return this.input.readFixed64();
  }
  
  public void readFixed64List(List<Long> paramList) throws IOException {
    int i;
    int j;
    if (paramList instanceof LongArrayList) {
      int k;
      int m;
      paramList = paramList;
      switch (WireFormat.getTagWireType(this.tag)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 2:
          k = this.input.readUInt32();
          verifyPackedFixed64Length(k);
          m = this.input.getTotalBytesRead();
          do {
            paramList.addLong(this.input.readFixed64());
          } while (this.input.getTotalBytesRead() < m + k);
          return;
        case 1:
          break;
      } 
      while (true) {
        paramList.addLong(this.input.readFixed64());
        if (this.input.isAtEnd())
          return; 
        k = this.input.readTag();
        if (k != this.tag) {
          this.nextTag = k;
          return;
        } 
      } 
    } 
    switch (WireFormat.getTagWireType(this.tag)) {
      default:
        throw InvalidProtocolBufferException.invalidWireType();
      case 2:
        i = this.input.readUInt32();
        verifyPackedFixed64Length(i);
        j = this.input.getTotalBytesRead();
        do {
          paramList.add(Long.valueOf(this.input.readFixed64()));
        } while (this.input.getTotalBytesRead() < j + i);
        return;
      case 1:
        break;
    } 
    while (true) {
      paramList.add(Long.valueOf(this.input.readFixed64()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public float readFloat() throws IOException {
    requireWireType(5);
    return this.input.readFloat();
  }
  
  public void readFloatList(List<Float> paramList) throws IOException {
    if (paramList instanceof FloatArrayList) {
      paramList = paramList;
      int i = WireFormat.getTagWireType(this.tag);
      if (i != 2) {
        if (i != 5)
          throw InvalidProtocolBufferException.invalidWireType(); 
        while (true) {
          paramList.addFloat(this.input.readFloat());
          if (this.input.isAtEnd())
            return; 
          i = this.input.readTag();
          if (i != this.tag) {
            this.nextTag = i;
            return;
          } 
        } 
      } 
      i = this.input.readUInt32();
      verifyPackedFixed32Length(i);
      int j = this.input.getTotalBytesRead();
      do {
        paramList.addFloat(this.input.readFloat());
      } while (this.input.getTotalBytesRead() < j + i);
    } else {
      int i = WireFormat.getTagWireType(this.tag);
      if (i != 2) {
        if (i != 5)
          throw InvalidProtocolBufferException.invalidWireType(); 
        while (true) {
          paramList.add(Float.valueOf(this.input.readFloat()));
          if (this.input.isAtEnd())
            return; 
          i = this.input.readTag();
          if (i != this.tag) {
            this.nextTag = i;
            return;
          } 
        } 
      } 
      int j = this.input.readUInt32();
      verifyPackedFixed32Length(j);
      i = this.input.getTotalBytesRead();
      do {
        paramList.add(Float.valueOf(this.input.readFloat()));
      } while (this.input.getTotalBytesRead() < i + j);
    } 
  }
  
  public <T> T readGroup(Class<T> paramClass, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    requireWireType(3);
    return readGroup(Protobuf.getInstance().schemaFor(paramClass), paramExtensionRegistryLite);
  }
  
  public <T> T readGroupBySchemaWithCheck(Schema<T> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    requireWireType(3);
    return readGroup(paramSchema, paramExtensionRegistryLite);
  }
  
  public <T> void readGroupList(List<T> paramList, Schema<T> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    if (WireFormat.getTagWireType(this.tag) != 3)
      throw InvalidProtocolBufferException.invalidWireType(); 
    int i = this.tag;
    while (true) {
      paramList.add(readGroup(paramSchema, paramExtensionRegistryLite));
      if (this.input.isAtEnd() || this.nextTag != 0)
        break; 
      int j = this.input.readTag();
      if (j != i) {
        this.nextTag = j;
        return;
      } 
    } 
  }
  
  public <T> void readGroupList(List<T> paramList, Class<T> paramClass, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    readGroupList(paramList, Protobuf.getInstance().schemaFor(paramClass), paramExtensionRegistryLite);
  }
  
  public int readInt32() throws IOException {
    requireWireType(0);
    return this.input.readInt32();
  }
  
  public void readInt32List(List<Integer> paramList) throws IOException {
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addInt(this.input.readInt32());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addInt(this.input.readInt32());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Integer.valueOf(this.input.readInt32()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.input.readInt32()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public long readInt64() throws IOException {
    requireWireType(0);
    return this.input.readInt64();
  }
  
  public void readInt64List(List<Long> paramList) throws IOException {
    if (paramList instanceof LongArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addLong(this.input.readInt64());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addLong(this.input.readInt64());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Long.valueOf(this.input.readInt64()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Long.valueOf(this.input.readInt64()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public <K, V> void readMap(Map<K, V> paramMap, MapEntryLite.Metadata<K, V> paramMetadata, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: iconst_2
    //   2: invokespecial requireWireType : (I)V
    //   5: aload_0
    //   6: getfield input : Lcom/google/protobuf/CodedInputStream;
    //   9: invokevirtual readUInt32 : ()I
    //   12: istore #4
    //   14: aload_0
    //   15: getfield input : Lcom/google/protobuf/CodedInputStream;
    //   18: iload #4
    //   20: invokevirtual pushLimit : (I)I
    //   23: istore #5
    //   25: aload_2
    //   26: getfield defaultKey : Ljava/lang/Object;
    //   29: astore #6
    //   31: aload_2
    //   32: getfield defaultValue : Ljava/lang/Object;
    //   35: astore #7
    //   37: aload #7
    //   39: astore #8
    //   41: aload #6
    //   43: astore #9
    //   45: aload_0
    //   46: invokevirtual getFieldNumber : ()I
    //   49: istore #4
    //   51: iload #4
    //   53: ldc 2147483647
    //   55: if_icmpeq -> 212
    //   58: aload_0
    //   59: getfield input : Lcom/google/protobuf/CodedInputStream;
    //   62: invokevirtual isAtEnd : ()Z
    //   65: istore #10
    //   67: iload #10
    //   69: ifeq -> 75
    //   72: goto -> 212
    //   75: iload #4
    //   77: tableswitch default -> 100, 1 -> 134, 2 -> 109
    //   100: aload_0
    //   101: invokevirtual skipField : ()Z
    //   104: istore #10
    //   106: goto -> 153
    //   109: aload_0
    //   110: aload_2
    //   111: getfield valueType : Lcom/google/protobuf/WireFormat$FieldType;
    //   114: aload_2
    //   115: getfield defaultValue : Ljava/lang/Object;
    //   118: invokevirtual getClass : ()Ljava/lang/Class;
    //   121: aload_3
    //   122: invokespecial readField : (Lcom/google/protobuf/WireFormat$FieldType;Ljava/lang/Class;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   125: astore #7
    //   127: aload #9
    //   129: astore #6
    //   131: goto -> 37
    //   134: aload_0
    //   135: aload_2
    //   136: getfield keyType : Lcom/google/protobuf/WireFormat$FieldType;
    //   139: aconst_null
    //   140: aconst_null
    //   141: invokespecial readField : (Lcom/google/protobuf/WireFormat$FieldType;Ljava/lang/Class;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   144: astore #6
    //   146: aload #8
    //   148: astore #7
    //   150: goto -> 37
    //   153: aload #9
    //   155: astore #6
    //   157: aload #8
    //   159: astore #7
    //   161: iload #10
    //   163: ifne -> 37
    //   166: new com/google/protobuf/InvalidProtocolBufferException
    //   169: astore #6
    //   171: aload #6
    //   173: ldc_w 'Unable to parse map entry.'
    //   176: invokespecial <init> : (Ljava/lang/String;)V
    //   179: aload #6
    //   181: athrow
    //   182: astore #6
    //   184: aload #9
    //   186: astore #6
    //   188: aload #8
    //   190: astore #7
    //   192: aload_0
    //   193: invokevirtual skipField : ()Z
    //   196: ifne -> 37
    //   199: new com/google/protobuf/InvalidProtocolBufferException
    //   202: astore_1
    //   203: aload_1
    //   204: ldc_w 'Unable to parse map entry.'
    //   207: invokespecial <init> : (Ljava/lang/String;)V
    //   210: aload_1
    //   211: athrow
    //   212: aload_1
    //   213: aload #9
    //   215: aload #8
    //   217: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   222: pop
    //   223: aload_0
    //   224: getfield input : Lcom/google/protobuf/CodedInputStream;
    //   227: iload #5
    //   229: invokevirtual popLimit : (I)V
    //   232: return
    //   233: astore_1
    //   234: aload_0
    //   235: getfield input : Lcom/google/protobuf/CodedInputStream;
    //   238: iload #5
    //   240: invokevirtual popLimit : (I)V
    //   243: aload_1
    //   244: athrow
    // Exception table:
    //   from	to	target	type
    //   45	51	233	finally
    //   58	67	233	finally
    //   100	106	182	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   100	106	233	finally
    //   109	127	182	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   109	127	233	finally
    //   134	146	182	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   134	146	233	finally
    //   166	182	182	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   166	182	233	finally
    //   192	212	233	finally
    //   212	223	233	finally
  }
  
  public <T> T readMessage(Class<T> paramClass, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    requireWireType(2);
    return readMessage(Protobuf.getInstance().schemaFor(paramClass), paramExtensionRegistryLite);
  }
  
  public <T> T readMessageBySchemaWithCheck(Schema<T> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    requireWireType(2);
    return readMessage(paramSchema, paramExtensionRegistryLite);
  }
  
  public <T> void readMessageList(List<T> paramList, Schema<T> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    if (WireFormat.getTagWireType(this.tag) != 2)
      throw InvalidProtocolBufferException.invalidWireType(); 
    int i = this.tag;
    while (true) {
      paramList.add(readMessage(paramSchema, paramExtensionRegistryLite));
      if (this.input.isAtEnd() || this.nextTag != 0)
        break; 
      int j = this.input.readTag();
      if (j != i) {
        this.nextTag = j;
        return;
      } 
    } 
  }
  
  public <T> void readMessageList(List<T> paramList, Class<T> paramClass, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    readMessageList(paramList, Protobuf.getInstance().schemaFor(paramClass), paramExtensionRegistryLite);
  }
  
  public int readSFixed32() throws IOException {
    requireWireType(5);
    return this.input.readSFixed32();
  }
  
  public void readSFixed32List(List<Integer> paramList) throws IOException {
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int i = WireFormat.getTagWireType(this.tag);
      if (i != 2) {
        if (i != 5)
          throw InvalidProtocolBufferException.invalidWireType(); 
        while (true) {
          paramList.addInt(this.input.readSFixed32());
          if (this.input.isAtEnd())
            return; 
          i = this.input.readTag();
          if (i != this.tag) {
            this.nextTag = i;
            return;
          } 
        } 
      } 
      int j = this.input.readUInt32();
      verifyPackedFixed32Length(j);
      i = this.input.getTotalBytesRead();
      do {
        paramList.addInt(this.input.readSFixed32());
      } while (this.input.getTotalBytesRead() < i + j);
    } else {
      int i = WireFormat.getTagWireType(this.tag);
      if (i != 2) {
        if (i != 5)
          throw InvalidProtocolBufferException.invalidWireType(); 
        while (true) {
          paramList.add(Integer.valueOf(this.input.readSFixed32()));
          if (this.input.isAtEnd())
            return; 
          i = this.input.readTag();
          if (i != this.tag) {
            this.nextTag = i;
            return;
          } 
        } 
      } 
      i = this.input.readUInt32();
      verifyPackedFixed32Length(i);
      int j = this.input.getTotalBytesRead();
      do {
        paramList.add(Integer.valueOf(this.input.readSFixed32()));
      } while (this.input.getTotalBytesRead() < j + i);
    } 
  }
  
  public long readSFixed64() throws IOException {
    requireWireType(1);
    return this.input.readSFixed64();
  }
  
  public void readSFixed64List(List<Long> paramList) throws IOException {
    int i;
    int j;
    if (paramList instanceof LongArrayList) {
      int k;
      int m;
      paramList = paramList;
      switch (WireFormat.getTagWireType(this.tag)) {
        default:
          throw InvalidProtocolBufferException.invalidWireType();
        case 2:
          k = this.input.readUInt32();
          verifyPackedFixed64Length(k);
          m = this.input.getTotalBytesRead();
          do {
            paramList.addLong(this.input.readSFixed64());
          } while (this.input.getTotalBytesRead() < m + k);
          return;
        case 1:
          break;
      } 
      while (true) {
        paramList.addLong(this.input.readSFixed64());
        if (this.input.isAtEnd())
          return; 
        k = this.input.readTag();
        if (k != this.tag) {
          this.nextTag = k;
          return;
        } 
      } 
    } 
    switch (WireFormat.getTagWireType(this.tag)) {
      default:
        throw InvalidProtocolBufferException.invalidWireType();
      case 2:
        j = this.input.readUInt32();
        verifyPackedFixed64Length(j);
        i = this.input.getTotalBytesRead();
        do {
          paramList.add(Long.valueOf(this.input.readSFixed64()));
        } while (this.input.getTotalBytesRead() < i + j);
        return;
      case 1:
        break;
    } 
    while (true) {
      paramList.add(Long.valueOf(this.input.readSFixed64()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public int readSInt32() throws IOException {
    requireWireType(0);
    return this.input.readSInt32();
  }
  
  public void readSInt32List(List<Integer> paramList) throws IOException {
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addInt(this.input.readSInt32());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addInt(this.input.readSInt32());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Integer.valueOf(this.input.readSInt32()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.input.readSInt32()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public long readSInt64() throws IOException {
    requireWireType(0);
    return this.input.readSInt64();
  }
  
  public void readSInt64List(List<Long> paramList) throws IOException {
    if (paramList instanceof LongArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addLong(this.input.readSInt64());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addLong(this.input.readSInt64());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Long.valueOf(this.input.readSInt64()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Long.valueOf(this.input.readSInt64()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public String readString() throws IOException {
    requireWireType(2);
    return this.input.readString();
  }
  
  public void readStringList(List<String> paramList) throws IOException {
    readStringListInternal(paramList, false);
  }
  
  public void readStringListInternal(List<String> paramList, boolean paramBoolean) throws IOException {
    if (WireFormat.getTagWireType(this.tag) != 2)
      throw InvalidProtocolBufferException.invalidWireType(); 
    if (paramList instanceof LazyStringList && !paramBoolean) {
      paramList = paramList;
      while (true) {
        paramList.add(readBytes());
        if (this.input.isAtEnd())
          return; 
        int i = this.input.readTag();
        if (i != this.tag) {
          this.nextTag = i;
          return;
        } 
      } 
    } 
    while (true) {
      String str;
      if (paramBoolean) {
        str = readStringRequireUtf8();
      } else {
        str = readString();
      } 
      paramList.add(str);
      if (this.input.isAtEnd())
        return; 
      int i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public void readStringListRequireUtf8(List<String> paramList) throws IOException {
    readStringListInternal(paramList, true);
  }
  
  public String readStringRequireUtf8() throws IOException {
    requireWireType(2);
    return this.input.readStringRequireUtf8();
  }
  
  public int readUInt32() throws IOException {
    requireWireType(0);
    return this.input.readUInt32();
  }
  
  public void readUInt32List(List<Integer> paramList) throws IOException {
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addInt(this.input.readUInt32());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addInt(this.input.readUInt32());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Integer.valueOf(this.input.readUInt32()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.input.readUInt32()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public long readUInt64() throws IOException {
    requireWireType(0);
    return this.input.readUInt64();
  }
  
  public void readUInt64List(List<Long> paramList) throws IOException {
    if (paramList instanceof LongArrayList) {
      paramList = paramList;
      int j = WireFormat.getTagWireType(this.tag);
      if (j != 0) {
        if (j != 2)
          throw InvalidProtocolBufferException.invalidWireType(); 
        j = this.input.readUInt32();
        j = this.input.getTotalBytesRead() + j;
        while (true) {
          paramList.addLong(this.input.readUInt64());
          if (this.input.getTotalBytesRead() >= j) {
            requirePosition(j);
            return;
          } 
        } 
      } 
      while (true) {
        paramList.addLong(this.input.readUInt64());
        if (this.input.isAtEnd())
          return; 
        j = this.input.readTag();
        if (j != this.tag) {
          this.nextTag = j;
          return;
        } 
      } 
    } 
    int i = WireFormat.getTagWireType(this.tag);
    if (i != 0) {
      if (i != 2)
        throw InvalidProtocolBufferException.invalidWireType(); 
      i = this.input.readUInt32();
      i = this.input.getTotalBytesRead() + i;
      while (true) {
        paramList.add(Long.valueOf(this.input.readUInt64()));
        if (this.input.getTotalBytesRead() >= i) {
          requirePosition(i);
          return;
        } 
      } 
    } 
    while (true) {
      paramList.add(Long.valueOf(this.input.readUInt64()));
      if (this.input.isAtEnd())
        return; 
      i = this.input.readTag();
      if (i != this.tag) {
        this.nextTag = i;
        return;
      } 
    } 
  }
  
  public boolean shouldDiscardUnknownFields() {
    return this.input.shouldDiscardUnknownFields();
  }
  
  public boolean skipField() throws IOException {
    return (this.input.isAtEnd() || this.tag == this.endGroupTag) ? false : this.input.skipField(this.tag);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\CodedInputStreamReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */