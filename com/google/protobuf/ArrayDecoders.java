package com.google.protobuf;

import java.io.IOException;

final class ArrayDecoders {
  static int decodeBoolList(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    boolean bool;
    paramProtobufList = paramProtobufList;
    paramInt2 = decodeVarint64(paramArrayOfbyte, paramInt2, paramRegisters);
    if (paramRegisters.long1 != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    paramProtobufList.addBoolean(bool);
    while (paramInt2 < paramInt3) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint64(paramArrayOfbyte, i, paramRegisters);
      if (paramRegisters.long1 != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      paramProtobufList.addBoolean(bool);
    } 
    return paramInt2;
  }
  
  static int decodeBytes(byte[] paramArrayOfbyte, int paramInt, Registers paramRegisters) throws InvalidProtocolBufferException {
    int i = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    paramInt = paramRegisters.int1;
    if (paramInt < 0)
      throw InvalidProtocolBufferException.negativeSize(); 
    if (paramInt > paramArrayOfbyte.length - i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    if (paramInt == 0) {
      paramRegisters.object1 = ByteString.EMPTY;
      return i;
    } 
    paramRegisters.object1 = ByteString.copyFrom(paramArrayOfbyte, i, paramInt);
    return i + paramInt;
  }
  
  static int decodeBytesList(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws InvalidProtocolBufferException {
    paramInt2 = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
    int i = paramRegisters.int1;
    if (i < 0)
      throw InvalidProtocolBufferException.negativeSize(); 
    if (i > paramArrayOfbyte.length - paramInt2)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    if (i == 0) {
      paramProtobufList.add(ByteString.EMPTY);
    } else {
      paramProtobufList.add(ByteString.copyFrom(paramArrayOfbyte, paramInt2, i));
      paramInt2 += i;
    } 
    while (paramInt2 < paramInt3) {
      i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint32(paramArrayOfbyte, i, paramRegisters);
      i = paramRegisters.int1;
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      if (i > paramArrayOfbyte.length - paramInt2)
        throw InvalidProtocolBufferException.truncatedMessage(); 
      if (i == 0) {
        paramProtobufList.add(ByteString.EMPTY);
        continue;
      } 
      paramProtobufList.add(ByteString.copyFrom(paramArrayOfbyte, paramInt2, i));
      paramInt2 += i;
    } 
    return paramInt2;
  }
  
  static double decodeDouble(byte[] paramArrayOfbyte, int paramInt) {
    return Double.longBitsToDouble(decodeFixed64(paramArrayOfbyte, paramInt));
  }
  
  static int decodeDoubleList(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramProtobufList.addDouble(decodeDouble(paramArrayOfbyte, paramInt2));
    for (paramInt2 += 8; paramInt2 < paramInt3; paramInt2 = i + 8) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramProtobufList.addDouble(decodeDouble(paramArrayOfbyte, i));
    } 
    return paramInt2;
  }
  
  static int decodeExtension(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, GeneratedMessageLite.ExtendableMessage<?, ?> paramExtendableMessage, GeneratedMessageLite.GeneratedExtension<?, ?> paramGeneratedExtension, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> paramUnknownFieldSchema, Registers paramRegisters) throws IOException {
    UnknownFieldSetLite unknownFieldSetLite1;
    DoubleArrayList doubleArrayList;
    UnknownFieldSetLite unknownFieldSetLite2;
    FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet = paramExtendableMessage.extensions;
    int i = paramInt1 >>> 3;
    boolean bool = paramGeneratedExtension.descriptor.isRepeated();
    IntArrayList intArrayList = null;
    if (bool && paramGeneratedExtension.descriptor.isPacked()) {
      StringBuilder stringBuilder;
      LongArrayList longArrayList3;
      IntArrayList intArrayList3;
      BooleanArrayList booleanArrayList;
      IntArrayList intArrayList2;
      LongArrayList longArrayList2;
      IntArrayList intArrayList1;
      LongArrayList longArrayList1;
      FloatArrayList floatArrayList;
      switch (paramGeneratedExtension.getLiteType()) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Type cannot be packed: ");
          stringBuilder.append(paramGeneratedExtension.descriptor.getLiteType());
          throw new IllegalStateException(stringBuilder.toString());
        case ENUM:
          intArrayList = new IntArrayList();
          paramInt1 = decodePackedVarint32List((byte[])stringBuilder, paramInt2, intArrayList, paramRegisters);
          unknownFieldSetLite2 = paramExtendableMessage.unknownFields;
          unknownFieldSetLite1 = unknownFieldSetLite2;
          if (unknownFieldSetLite2 == UnknownFieldSetLite.getDefaultInstance())
            unknownFieldSetLite1 = null; 
          unknownFieldSetLite1 = SchemaUtil.<UnknownFieldSetLite, UnknownFieldSetLite>filterUnknownEnumList(i, intArrayList, paramGeneratedExtension.descriptor.getEnumType(), unknownFieldSetLite1, paramUnknownFieldSchema);
          if (unknownFieldSetLite1 != null)
            paramExtendableMessage.unknownFields = unknownFieldSetLite1; 
          fieldSet.setField(paramGeneratedExtension.descriptor, intArrayList);
          return paramInt1;
        case SINT64:
          longArrayList3 = new LongArrayList();
          paramInt1 = decodePackedSInt64List((byte[])unknownFieldSetLite1, paramInt2, longArrayList3, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, longArrayList3);
          return paramInt1;
        case SINT32:
          intArrayList3 = new IntArrayList();
          paramInt1 = decodePackedSInt32List((byte[])unknownFieldSetLite1, paramInt2, intArrayList3, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, intArrayList3);
          return paramInt1;
        case BOOL:
          booleanArrayList = new BooleanArrayList();
          paramInt1 = decodePackedBoolList((byte[])unknownFieldSetLite1, paramInt2, booleanArrayList, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, booleanArrayList);
          return paramInt1;
        case FIXED32:
        case SFIXED32:
          intArrayList2 = new IntArrayList();
          paramInt1 = decodePackedFixed32List((byte[])unknownFieldSetLite1, paramInt2, intArrayList2, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, intArrayList2);
          return paramInt1;
        case FIXED64:
        case SFIXED64:
          longArrayList2 = new LongArrayList();
          paramInt1 = decodePackedFixed64List((byte[])unknownFieldSetLite1, paramInt2, longArrayList2, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, longArrayList2);
          return paramInt1;
        case INT32:
        case UINT32:
          intArrayList1 = new IntArrayList();
          paramInt1 = decodePackedVarint32List((byte[])unknownFieldSetLite1, paramInt2, intArrayList1, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, intArrayList1);
          return paramInt1;
        case INT64:
        case UINT64:
          longArrayList1 = new LongArrayList();
          paramInt1 = decodePackedVarint64List((byte[])unknownFieldSetLite1, paramInt2, longArrayList1, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, longArrayList1);
          return paramInt1;
        case FLOAT:
          floatArrayList = new FloatArrayList();
          paramInt1 = decodePackedFloatList((byte[])unknownFieldSetLite1, paramInt2, floatArrayList, (Registers)unknownFieldSetLite2);
          fieldSet.setField(paramGeneratedExtension.descriptor, floatArrayList);
          return paramInt1;
        case DOUBLE:
          break;
      } 
      doubleArrayList = new DoubleArrayList();
      paramInt1 = decodePackedDoubleList((byte[])unknownFieldSetLite1, paramInt2, doubleArrayList, (Registers)unknownFieldSetLite2);
      fieldSet.setField(paramGeneratedExtension.descriptor, doubleArrayList);
    } else {
      UnknownFieldSetLite unknownFieldSetLite;
      if (paramGeneratedExtension.getLiteType() == WireFormat.FieldType.ENUM) {
        paramInt2 = decodeVarint32((byte[])unknownFieldSetLite1, paramInt2, (Registers)unknownFieldSetLite2);
        if (paramGeneratedExtension.descriptor.getEnumType().findValueByNumber(((Registers)unknownFieldSetLite2).int1) == null) {
          unknownFieldSetLite = ((GeneratedMessageLite)doubleArrayList).unknownFields;
          unknownFieldSetLite1 = unknownFieldSetLite;
          if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
            unknownFieldSetLite1 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite)doubleArrayList).unknownFields = unknownFieldSetLite1;
          } 
          SchemaUtil.storeUnknownEnum(i, ((Registers)unknownFieldSetLite2).int1, unknownFieldSetLite1, paramUnknownFieldSchema);
          return paramInt2;
        } 
        Integer integer = Integer.valueOf(((Registers)unknownFieldSetLite2).int1);
      } else {
        IntArrayList intArrayList1;
        Object object;
        switch (unknownFieldSetLite.getLiteType()) {
          default:
            intArrayList1 = intArrayList;
            paramInt1 = paramInt2;
          case MESSAGE:
            paramInt2 = decodeMessageField(Protobuf.getInstance().schemaFor(unknownFieldSetLite.getMessageDefaultInstance().getClass()), (byte[])intArrayList1, paramInt2, paramInt3, (Registers)unknownFieldSetLite2);
            object = ((Registers)unknownFieldSetLite2).object1;
            paramInt1 = paramInt2;
          case GROUP:
            paramInt2 = decodeGroupField(Protobuf.getInstance().schemaFor(unknownFieldSetLite.getMessageDefaultInstance().getClass()), (byte[])object, paramInt2, paramInt3, i << 3 | 0x4, (Registers)unknownFieldSetLite2);
            object = ((Registers)unknownFieldSetLite2).object1;
            paramInt1 = paramInt2;
          case STRING:
            paramInt2 = decodeString((byte[])object, paramInt2, (Registers)unknownFieldSetLite2);
            object = ((Registers)unknownFieldSetLite2).object1;
            paramInt1 = paramInt2;
          case BYTES:
            paramInt2 = decodeBytes((byte[])object, paramInt2, (Registers)unknownFieldSetLite2);
            object = ((Registers)unknownFieldSetLite2).object1;
            paramInt1 = paramInt2;
          case ENUM:
            throw new IllegalStateException("Shouldn't reach here.");
          case SINT64:
            paramInt2 = decodeVarint64((byte[])object, paramInt2, (Registers)unknownFieldSetLite2);
            object = Long.valueOf(CodedInputStream.decodeZigZag64(((Registers)unknownFieldSetLite2).long1));
            paramInt1 = paramInt2;
          case SINT32:
            paramInt2 = decodeVarint32((byte[])object, paramInt2, (Registers)unknownFieldSetLite2);
            object = Integer.valueOf(CodedInputStream.decodeZigZag32(((Registers)unknownFieldSetLite2).int1));
            paramInt1 = paramInt2;
          case BOOL:
            paramInt2 = decodeVarint64((byte[])object, paramInt2, (Registers)unknownFieldSetLite2);
            if (((Registers)unknownFieldSetLite2).long1 != 0L) {
              bool = true;
            } else {
              bool = false;
            } 
            object = Boolean.valueOf(bool);
            paramInt1 = paramInt2;
          case FIXED32:
          case SFIXED32:
            object = Integer.valueOf(decodeFixed32((byte[])object, paramInt2));
            paramInt2 += 4;
            paramInt1 = paramInt2;
          case FIXED64:
          case SFIXED64:
            object = Long.valueOf(decodeFixed64((byte[])object, paramInt2));
            paramInt2 += 8;
            paramInt1 = paramInt2;
          case INT32:
          case UINT32:
            paramInt2 = decodeVarint32((byte[])object, paramInt2, (Registers)unknownFieldSetLite2);
            object = Integer.valueOf(((Registers)unknownFieldSetLite2).int1);
            paramInt1 = paramInt2;
          case INT64:
          case UINT64:
            paramInt2 = decodeVarint64((byte[])object, paramInt2, (Registers)unknownFieldSetLite2);
            object = Long.valueOf(((Registers)unknownFieldSetLite2).long1);
            paramInt1 = paramInt2;
          case FLOAT:
            object = Float.valueOf(decodeFloat((byte[])object, paramInt2));
            paramInt2 += 4;
            paramInt1 = paramInt2;
          case DOUBLE:
            object = Double.valueOf(decodeDouble((byte[])object, paramInt2));
            paramInt2 += 8;
            paramInt1 = paramInt2;
        } 
        if (unknownFieldSetLite.isRepeated()) {
          fieldSet.addRepeatedField(((GeneratedMessageLite.GeneratedExtension)unknownFieldSetLite).descriptor, object);
        } else {
          Object object1;
          Object object2;
          switch (unknownFieldSetLite.getLiteType()) {
            default:
              object1 = object;
              break;
            case GROUP:
            case MESSAGE:
              object2 = fieldSet.getField(((GeneratedMessageLite.GeneratedExtension)unknownFieldSetLite).descriptor);
              object1 = object;
              if (object2 != null)
                object1 = Internal.mergeMessage(object2, object); 
              break;
          } 
          fieldSet.setField(((GeneratedMessageLite.GeneratedExtension)unknownFieldSetLite).descriptor, object1);
        } 
        return paramInt1;
      } 
      paramInt1 = paramInt2;
    } 
    return paramInt1;
  }
  
  static int decodeExtensionOrUnknownField(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Object paramObject, MessageLite paramMessageLite, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> paramUnknownFieldSchema, Registers paramRegisters) throws IOException {
    GeneratedMessageLite.GeneratedExtension<MessageLite, ?> generatedExtension = paramRegisters.extensionRegistry.findLiteExtensionByNumber(paramMessageLite, paramInt1 >>> 3);
    if (generatedExtension == null)
      return decodeUnknownField(paramInt1, paramArrayOfbyte, paramInt2, paramInt3, MessageSchema.getMutableUnknownFields(paramObject), paramRegisters); 
    paramObject = paramObject;
    paramObject.ensureExtensionsAreMutable();
    return decodeExtension(paramInt1, paramArrayOfbyte, paramInt2, paramInt3, (GeneratedMessageLite.ExtendableMessage<?, ?>)paramObject, generatedExtension, paramUnknownFieldSchema, paramRegisters);
  }
  
  static int decodeFixed32(byte[] paramArrayOfbyte, int paramInt) {
    byte b1 = paramArrayOfbyte[paramInt];
    byte b2 = paramArrayOfbyte[paramInt + 1];
    byte b3 = paramArrayOfbyte[paramInt + 2];
    return (paramArrayOfbyte[paramInt + 3] & 0xFF) << 24 | b1 & 0xFF | (b2 & 0xFF) << 8 | (b3 & 0xFF) << 16;
  }
  
  static int decodeFixed32List(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramProtobufList.addInt(decodeFixed32(paramArrayOfbyte, paramInt2));
    for (paramInt2 += 4; paramInt2 < paramInt3; paramInt2 = i + 4) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramProtobufList.addInt(decodeFixed32(paramArrayOfbyte, i));
    } 
    return paramInt2;
  }
  
  static long decodeFixed64(byte[] paramArrayOfbyte, int paramInt) {
    long l1 = paramArrayOfbyte[paramInt];
    long l2 = paramArrayOfbyte[paramInt + 1];
    long l3 = paramArrayOfbyte[paramInt + 2];
    long l4 = paramArrayOfbyte[paramInt + 3];
    long l5 = paramArrayOfbyte[paramInt + 4];
    long l6 = paramArrayOfbyte[paramInt + 5];
    long l7 = paramArrayOfbyte[paramInt + 6];
    return (paramArrayOfbyte[paramInt + 7] & 0xFFL) << 56L | l1 & 0xFFL | (l2 & 0xFFL) << 8L | (l3 & 0xFFL) << 16L | (l4 & 0xFFL) << 24L | (l5 & 0xFFL) << 32L | (l6 & 0xFFL) << 40L | (l7 & 0xFFL) << 48L;
  }
  
  static int decodeFixed64List(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramProtobufList.addLong(decodeFixed64(paramArrayOfbyte, paramInt2));
    for (paramInt2 += 8; paramInt2 < paramInt3; paramInt2 = i + 8) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramProtobufList.addLong(decodeFixed64(paramArrayOfbyte, i));
    } 
    return paramInt2;
  }
  
  static float decodeFloat(byte[] paramArrayOfbyte, int paramInt) {
    return Float.intBitsToFloat(decodeFixed32(paramArrayOfbyte, paramInt));
  }
  
  static int decodeFloatList(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramProtobufList.addFloat(decodeFloat(paramArrayOfbyte, paramInt2));
    for (paramInt2 += 4; paramInt2 < paramInt3; paramInt2 = i + 4) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramProtobufList.addFloat(decodeFloat(paramArrayOfbyte, i));
    } 
    return paramInt2;
  }
  
  static int decodeGroupField(Schema paramSchema, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, Registers paramRegisters) throws IOException {
    MessageSchema<Schema> messageSchema = (MessageSchema)paramSchema;
    paramSchema = messageSchema.newInstance();
    paramInt1 = messageSchema.parseProto2Message(paramSchema, paramArrayOfbyte, paramInt1, paramInt2, paramInt3, paramRegisters);
    messageSchema.makeImmutable(paramSchema);
    paramRegisters.object1 = paramSchema;
    return paramInt1;
  }
  
  static int decodeGroupList(Schema paramSchema, int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    int i = paramInt1 & 0xFFFFFFF8 | 0x4;
    paramInt2 = decodeGroupField(paramSchema, paramArrayOfbyte, paramInt2, paramInt3, i, paramRegisters);
    paramProtobufList.add(paramRegisters.object1);
    while (paramInt2 < paramInt3) {
      int j = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeGroupField(paramSchema, paramArrayOfbyte, j, paramInt3, i, paramRegisters);
      paramProtobufList.add(paramRegisters.object1);
    } 
    return paramInt2;
  }
  
  static int decodeMessageField(Schema<Object> paramSchema, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, Registers paramRegisters) throws IOException {
    int i = paramInt1 + 1;
    byte b = paramArrayOfbyte[paramInt1];
    int j = i;
    paramInt1 = b;
    if (b < 0) {
      j = decodeVarint32(b, paramArrayOfbyte, i, paramRegisters);
      paramInt1 = paramRegisters.int1;
    } 
    if (paramInt1 < 0 || paramInt1 > paramInt2 - j)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    Object object = paramSchema.newInstance();
    paramInt1 += j;
    paramSchema.mergeFrom(object, paramArrayOfbyte, j, paramInt1, paramRegisters);
    paramSchema.makeImmutable(object);
    paramRegisters.object1 = object;
    return paramInt1;
  }
  
  static int decodeMessageList(Schema<?> paramSchema, int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramInt2 = decodeMessageField(paramSchema, paramArrayOfbyte, paramInt2, paramInt3, paramRegisters);
    paramProtobufList.add(paramRegisters.object1);
    while (paramInt2 < paramInt3) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeMessageField(paramSchema, paramArrayOfbyte, i, paramInt3, paramRegisters);
      paramProtobufList.add(paramRegisters.object1);
    } 
    return paramInt2;
  }
  
  static int decodePackedBoolList(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      boolean bool;
      paramInt = decodeVarint64(paramArrayOfbyte, paramInt, paramRegisters);
      if (paramRegisters.long1 != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      paramProtobufList.addBoolean(bool);
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedDoubleList(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramProtobufList.addDouble(decodeDouble(paramArrayOfbyte, paramInt));
      paramInt += 8;
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedFixed32List(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramProtobufList.addInt(decodeFixed32(paramArrayOfbyte, paramInt));
      paramInt += 4;
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedFixed64List(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramProtobufList.addLong(decodeFixed64(paramArrayOfbyte, paramInt));
      paramInt += 8;
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedFloatList(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramProtobufList.addFloat(decodeFloat(paramArrayOfbyte, paramInt));
      paramInt += 4;
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedSInt32List(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
      paramProtobufList.addInt(CodedInputStream.decodeZigZag32(paramRegisters.int1));
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedSInt64List(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramInt = decodeVarint64(paramArrayOfbyte, paramInt, paramRegisters);
      paramProtobufList.addLong(CodedInputStream.decodeZigZag64(paramRegisters.long1));
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedVarint32List(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
      paramProtobufList.addInt(paramRegisters.int1);
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodePackedVarint64List(byte[] paramArrayOfbyte, int paramInt, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws IOException {
    paramProtobufList = paramProtobufList;
    paramInt = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    int i = paramRegisters.int1 + paramInt;
    while (paramInt < i) {
      paramInt = decodeVarint64(paramArrayOfbyte, paramInt, paramRegisters);
      paramProtobufList.addLong(paramRegisters.long1);
    } 
    if (paramInt != i)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    return paramInt;
  }
  
  static int decodeSInt32List(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramInt2 = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
    paramProtobufList.addInt(CodedInputStream.decodeZigZag32(paramRegisters.int1));
    while (paramInt2 < paramInt3) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint32(paramArrayOfbyte, i, paramRegisters);
      paramProtobufList.addInt(CodedInputStream.decodeZigZag32(paramRegisters.int1));
    } 
    return paramInt2;
  }
  
  static int decodeSInt64List(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramInt2 = decodeVarint64(paramArrayOfbyte, paramInt2, paramRegisters);
    paramProtobufList.addLong(CodedInputStream.decodeZigZag64(paramRegisters.long1));
    while (paramInt2 < paramInt3) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint64(paramArrayOfbyte, i, paramRegisters);
      paramProtobufList.addLong(CodedInputStream.decodeZigZag64(paramRegisters.long1));
    } 
    return paramInt2;
  }
  
  static int decodeString(byte[] paramArrayOfbyte, int paramInt, Registers paramRegisters) throws InvalidProtocolBufferException {
    int i = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    paramInt = paramRegisters.int1;
    if (paramInt < 0)
      throw InvalidProtocolBufferException.negativeSize(); 
    if (paramInt == 0) {
      paramRegisters.object1 = "";
      return i;
    } 
    paramRegisters.object1 = new String(paramArrayOfbyte, i, paramInt, Internal.UTF_8);
    return i + paramInt;
  }
  
  static int decodeStringList(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws InvalidProtocolBufferException {
    paramInt2 = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
    int i = paramRegisters.int1;
    if (i < 0)
      throw InvalidProtocolBufferException.negativeSize(); 
    if (i == 0) {
      paramProtobufList.add("");
    } else {
      paramProtobufList.add(new String(paramArrayOfbyte, paramInt2, i, Internal.UTF_8));
      paramInt2 += i;
    } 
    while (paramInt2 < paramInt3) {
      i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint32(paramArrayOfbyte, i, paramRegisters);
      i = paramRegisters.int1;
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      if (i == 0) {
        paramProtobufList.add("");
        continue;
      } 
      paramProtobufList.add(new String(paramArrayOfbyte, paramInt2, i, Internal.UTF_8));
      paramInt2 += i;
    } 
    return paramInt2;
  }
  
  static int decodeStringListRequireUtf8(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) throws InvalidProtocolBufferException {
    paramInt2 = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
    int i = paramRegisters.int1;
    if (i < 0)
      throw InvalidProtocolBufferException.negativeSize(); 
    if (i == 0) {
      paramProtobufList.add("");
    } else {
      int j = paramInt2 + i;
      if (!Utf8.isValidUtf8(paramArrayOfbyte, paramInt2, j))
        throw InvalidProtocolBufferException.invalidUtf8(); 
      paramProtobufList.add(new String(paramArrayOfbyte, paramInt2, i, Internal.UTF_8));
      paramInt2 = j;
    } 
    while (paramInt2 < paramInt3) {
      int j = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint32(paramArrayOfbyte, j, paramRegisters);
      i = paramRegisters.int1;
      if (i < 0)
        throw InvalidProtocolBufferException.negativeSize(); 
      if (i == 0) {
        paramProtobufList.add("");
        continue;
      } 
      j = paramInt2 + i;
      if (!Utf8.isValidUtf8(paramArrayOfbyte, paramInt2, j))
        throw InvalidProtocolBufferException.invalidUtf8(); 
      paramProtobufList.add(new String(paramArrayOfbyte, paramInt2, i, Internal.UTF_8));
      paramInt2 = j;
    } 
    return paramInt2;
  }
  
  static int decodeStringRequireUtf8(byte[] paramArrayOfbyte, int paramInt, Registers paramRegisters) throws InvalidProtocolBufferException {
    int i = decodeVarint32(paramArrayOfbyte, paramInt, paramRegisters);
    paramInt = paramRegisters.int1;
    if (paramInt < 0)
      throw InvalidProtocolBufferException.negativeSize(); 
    if (paramInt == 0) {
      paramRegisters.object1 = "";
      return i;
    } 
    paramRegisters.object1 = Utf8.decodeUtf8(paramArrayOfbyte, i, paramInt);
    return i + paramInt;
  }
  
  static int decodeUnknownField(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, UnknownFieldSetLite paramUnknownFieldSetLite, Registers paramRegisters) throws InvalidProtocolBufferException {
    if (WireFormat.getTagFieldNumber(paramInt1) == 0)
      throw InvalidProtocolBufferException.invalidTag(); 
    int i = WireFormat.getTagWireType(paramInt1);
    if (i != 5) {
      UnknownFieldSetLite unknownFieldSetLite;
      int j;
      int k;
      switch (i) {
        default:
          throw InvalidProtocolBufferException.invalidTag();
        case 3:
          unknownFieldSetLite = UnknownFieldSetLite.newInstance();
          j = paramInt1 & 0xFFFFFFF8 | 0x4;
          i = 0;
          while (true) {
            k = paramInt2;
            if (paramInt2 < paramInt3) {
              k = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
              i = paramRegisters.int1;
              if (i == j)
                break; 
              paramInt2 = decodeUnknownField(i, paramArrayOfbyte, k, paramInt3, unknownFieldSetLite, paramRegisters);
              continue;
            } 
            break;
          } 
          if (k > paramInt3 || i != j)
            throw InvalidProtocolBufferException.parseFailure(); 
          paramUnknownFieldSetLite.storeField(paramInt1, unknownFieldSetLite);
          return k;
        case 2:
          paramInt2 = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
          paramInt3 = paramRegisters.int1;
          if (paramInt3 < 0)
            throw InvalidProtocolBufferException.negativeSize(); 
          if (paramInt3 > paramArrayOfbyte.length - paramInt2)
            throw InvalidProtocolBufferException.truncatedMessage(); 
          if (paramInt3 == 0) {
            paramUnknownFieldSetLite.storeField(paramInt1, ByteString.EMPTY);
          } else {
            paramUnknownFieldSetLite.storeField(paramInt1, ByteString.copyFrom(paramArrayOfbyte, paramInt2, paramInt3));
          } 
          return paramInt2 + paramInt3;
        case 1:
          paramUnknownFieldSetLite.storeField(paramInt1, Long.valueOf(decodeFixed64(paramArrayOfbyte, paramInt2)));
          return paramInt2 + 8;
        case 0:
          break;
      } 
      paramInt2 = decodeVarint64(paramArrayOfbyte, paramInt2, paramRegisters);
      paramUnknownFieldSetLite.storeField(paramInt1, Long.valueOf(paramRegisters.long1));
      return paramInt2;
    } 
    paramUnknownFieldSetLite.storeField(paramInt1, Integer.valueOf(decodeFixed32(paramArrayOfbyte, paramInt2)));
    return paramInt2 + 4;
  }
  
  static int decodeVarint32(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, Registers paramRegisters) {
    int i = paramInt1 & 0x7F;
    paramInt1 = paramInt2 + 1;
    paramInt2 = paramArrayOfbyte[paramInt2];
    if (paramInt2 >= 0) {
      paramRegisters.int1 = i | paramInt2 << 7;
      return paramInt1;
    } 
    paramInt2 = i | (paramInt2 & 0x7F) << 7;
    i = paramInt1 + 1;
    paramInt1 = paramArrayOfbyte[paramInt1];
    if (paramInt1 >= 0) {
      paramRegisters.int1 = paramInt2 | paramInt1 << 14;
      return i;
    } 
    paramInt1 = paramInt2 | (paramInt1 & 0x7F) << 14;
    paramInt2 = i + 1;
    i = paramArrayOfbyte[i];
    if (i >= 0) {
      paramRegisters.int1 = paramInt1 | i << 21;
      return paramInt2;
    } 
    i = paramInt1 | (i & 0x7F) << 21;
    paramInt1 = paramInt2 + 1;
    byte b = paramArrayOfbyte[paramInt2];
    if (b >= 0) {
      paramRegisters.int1 = i | b << 28;
      return paramInt1;
    } 
    while (true) {
      paramInt2 = paramInt1 + 1;
      if (paramArrayOfbyte[paramInt1] < 0) {
        paramInt1 = paramInt2;
        continue;
      } 
      paramRegisters.int1 = i | (b & Byte.MAX_VALUE) << 28;
      return paramInt2;
    } 
  }
  
  static int decodeVarint32(byte[] paramArrayOfbyte, int paramInt, Registers paramRegisters) {
    int i = paramInt + 1;
    paramInt = paramArrayOfbyte[paramInt];
    if (paramInt >= 0) {
      paramRegisters.int1 = paramInt;
      return i;
    } 
    return decodeVarint32(paramInt, paramArrayOfbyte, i, paramRegisters);
  }
  
  static int decodeVarint32List(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramInt2 = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
    paramProtobufList.addInt(paramRegisters.int1);
    while (paramInt2 < paramInt3) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint32(paramArrayOfbyte, i, paramRegisters);
      paramProtobufList.addInt(paramRegisters.int1);
    } 
    return paramInt2;
  }
  
  static int decodeVarint64(long paramLong, byte[] paramArrayOfbyte, int paramInt, Registers paramRegisters) {
    int i = paramInt + 1;
    paramInt = paramArrayOfbyte[paramInt];
    paramLong = paramLong & 0x7FL | (paramInt & 0x7F) << 7L;
    byte b = 7;
    while (paramInt < 0) {
      paramInt = paramArrayOfbyte[i];
      b += 7;
      paramLong |= (paramInt & 0x7F) << b;
      i++;
    } 
    paramRegisters.long1 = paramLong;
    return i;
  }
  
  static int decodeVarint64(byte[] paramArrayOfbyte, int paramInt, Registers paramRegisters) {
    int i = paramInt + 1;
    long l = paramArrayOfbyte[paramInt];
    if (l >= 0L) {
      paramRegisters.long1 = l;
      return i;
    } 
    return decodeVarint64(l, paramArrayOfbyte, i, paramRegisters);
  }
  
  static int decodeVarint64List(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Internal.ProtobufList<?> paramProtobufList, Registers paramRegisters) {
    paramProtobufList = paramProtobufList;
    paramInt2 = decodeVarint64(paramArrayOfbyte, paramInt2, paramRegisters);
    paramProtobufList.addLong(paramRegisters.long1);
    while (paramInt2 < paramInt3) {
      int i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
      if (paramInt1 != paramRegisters.int1)
        break; 
      paramInt2 = decodeVarint64(paramArrayOfbyte, i, paramRegisters);
      paramProtobufList.addLong(paramRegisters.long1);
    } 
    return paramInt2;
  }
  
  static int skipField(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, Registers paramRegisters) throws InvalidProtocolBufferException {
    if (WireFormat.getTagFieldNumber(paramInt1) == 0)
      throw InvalidProtocolBufferException.invalidTag(); 
    int i = WireFormat.getTagWireType(paramInt1);
    if (i != 5) {
      int j;
      switch (i) {
        default:
          throw InvalidProtocolBufferException.invalidTag();
        case 3:
          j = paramInt1 & 0xFFFFFFF8 | 0x4;
          paramInt1 = 0;
          while (true) {
            i = paramInt2;
            if (paramInt2 < paramInt3) {
              i = decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters);
              paramInt1 = paramRegisters.int1;
              if (paramInt1 == j)
                break; 
              paramInt2 = skipField(paramInt1, paramArrayOfbyte, i, paramInt3, paramRegisters);
              continue;
            } 
            break;
          } 
          if (i > paramInt3 || paramInt1 != j)
            throw InvalidProtocolBufferException.parseFailure(); 
          return i;
        case 2:
          return decodeVarint32(paramArrayOfbyte, paramInt2, paramRegisters) + paramRegisters.int1;
        case 1:
          return paramInt2 + 8;
        case 0:
          break;
      } 
      return decodeVarint64(paramArrayOfbyte, paramInt2, paramRegisters);
    } 
    return paramInt2 + 4;
  }
  
  static final class Registers {
    public final ExtensionRegistryLite extensionRegistry;
    
    public int int1;
    
    public long long1;
    
    public Object object1;
    
    Registers() {
      this.extensionRegistry = ExtensionRegistryLite.getEmptyRegistry();
    }
    
    Registers(ExtensionRegistryLite param1ExtensionRegistryLite) {
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      this.extensionRegistry = param1ExtensionRegistryLite;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ArrayDecoders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */