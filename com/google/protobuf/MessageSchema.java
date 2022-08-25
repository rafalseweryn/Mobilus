package com.google.protobuf;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class MessageSchema<T> implements Schema<T> {
  private static final int[] EMPTY_INT_ARRAY = new int[0];
  
  private static final int ENFORCE_UTF8_MASK = 536870912;
  
  private static final int FIELD_TYPE_MASK = 267386880;
  
  private static final int INTS_PER_FIELD = 3;
  
  private static final int OFFSET_BITS = 20;
  
  private static final int OFFSET_MASK = 1048575;
  
  static final int ONEOF_TYPE_OFFSET = 51;
  
  private static final int REQUIRED_MASK = 268435456;
  
  private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
  
  private final int[] buffer;
  
  private final int checkInitializedCount;
  
  private final MessageLite defaultInstance;
  
  private final ExtensionSchema<?> extensionSchema;
  
  private final boolean hasExtensions;
  
  private final int[] intArray;
  
  private final ListFieldSchema listFieldSchema;
  
  private final boolean lite;
  
  private final MapFieldSchema mapFieldSchema;
  
  private final int maxFieldNumber;
  
  private final int minFieldNumber;
  
  private final NewInstanceSchema newInstanceSchema;
  
  private final Object[] objects;
  
  private final boolean proto3;
  
  private final int repeatedFieldOffsetStart;
  
  private final UnknownFieldSchema<?, ?> unknownFieldSchema;
  
  private final boolean useCachedSizeField;
  
  private MessageSchema(int[] paramArrayOfint1, Object[] paramArrayOfObject, int paramInt1, int paramInt2, MessageLite paramMessageLite, boolean paramBoolean1, boolean paramBoolean2, int[] paramArrayOfint2, int paramInt3, int paramInt4, NewInstanceSchema paramNewInstanceSchema, ListFieldSchema paramListFieldSchema, UnknownFieldSchema<?, ?> paramUnknownFieldSchema, ExtensionSchema<?> paramExtensionSchema, MapFieldSchema paramMapFieldSchema) {
    this.buffer = paramArrayOfint1;
    this.objects = paramArrayOfObject;
    this.minFieldNumber = paramInt1;
    this.maxFieldNumber = paramInt2;
    this.lite = paramMessageLite instanceof GeneratedMessageLite;
    this.proto3 = paramBoolean1;
    if (paramExtensionSchema != null && paramExtensionSchema.hasExtensions(paramMessageLite)) {
      paramBoolean1 = true;
    } else {
      paramBoolean1 = false;
    } 
    this.hasExtensions = paramBoolean1;
    this.useCachedSizeField = paramBoolean2;
    this.intArray = paramArrayOfint2;
    this.checkInitializedCount = paramInt3;
    this.repeatedFieldOffsetStart = paramInt4;
    this.newInstanceSchema = paramNewInstanceSchema;
    this.listFieldSchema = paramListFieldSchema;
    this.unknownFieldSchema = paramUnknownFieldSchema;
    this.extensionSchema = paramExtensionSchema;
    this.defaultInstance = paramMessageLite;
    this.mapFieldSchema = paramMapFieldSchema;
  }
  
  private boolean arePresentForEquals(T paramT1, T paramT2, int paramInt) {
    boolean bool;
    if (isFieldPresent(paramT1, paramInt) == isFieldPresent(paramT2, paramInt)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static <T> boolean booleanAt(T paramT, long paramLong) {
    return UnsafeUtil.getBoolean(paramT, paramLong);
  }
  
  private <K, V> int decodeMapEntry(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, MapEntryLite.Metadata<K, V> paramMetadata, Map<K, V> paramMap, ArrayDecoders.Registers paramRegisters) throws IOException {
    paramInt1 = ArrayDecoders.decodeVarint32(paramArrayOfbyte, paramInt1, paramRegisters);
    int i = paramRegisters.int1;
    if (i < 0 || i > paramInt2 - paramInt1)
      throw InvalidProtocolBufferException.truncatedMessage(); 
    int j = paramInt1 + i;
    K k = paramMetadata.defaultKey;
    V v = paramMetadata.defaultValue;
    while (paramInt1 < j) {
      i = paramInt1 + 1;
      paramInt1 = paramArrayOfbyte[paramInt1];
      if (paramInt1 < 0) {
        i = ArrayDecoders.decodeVarint32(paramInt1, paramArrayOfbyte, i, paramRegisters);
        paramInt1 = paramRegisters.int1;
      } 
      int m = paramInt1 & 0x7;
      switch (paramInt1 >>> 3) {
        case 2:
          if (m == paramMetadata.valueType.getWireType()) {
            paramInt1 = decodeMapEntryValue(paramArrayOfbyte, i, paramInt2, paramMetadata.valueType, paramMetadata.defaultValue.getClass(), paramRegisters);
            v = (V)paramRegisters.object1;
            continue;
          } 
          break;
        case 1:
          if (m == paramMetadata.keyType.getWireType()) {
            paramInt1 = decodeMapEntryValue(paramArrayOfbyte, i, paramInt2, paramMetadata.keyType, null, paramRegisters);
            k = (K)paramRegisters.object1;
            continue;
          } 
          break;
      } 
      paramInt1 = ArrayDecoders.skipField(paramInt1, paramArrayOfbyte, i, paramInt2, paramRegisters);
    } 
    if (paramInt1 != j)
      throw InvalidProtocolBufferException.parseFailure(); 
    paramMap.put(k, v);
    return j;
  }
  
  private int decodeMapEntryValue(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, WireFormat.FieldType paramFieldType, Class<?> paramClass, ArrayDecoders.Registers paramRegisters) throws IOException {
    boolean bool;
    switch (paramFieldType) {
      default:
        throw new RuntimeException("unsupported field type.");
      case STRING:
        return ArrayDecoders.decodeStringRequireUtf8(paramArrayOfbyte, paramInt1, paramRegisters);
      case SINT64:
        paramInt1 = ArrayDecoders.decodeVarint64(paramArrayOfbyte, paramInt1, paramRegisters);
        paramRegisters.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(paramRegisters.long1));
        return paramInt1;
      case SINT32:
        paramInt1 = ArrayDecoders.decodeVarint32(paramArrayOfbyte, paramInt1, paramRegisters);
        paramRegisters.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(paramRegisters.int1));
        return paramInt1;
      case MESSAGE:
        return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(paramClass), paramArrayOfbyte, paramInt1, paramInt2, paramRegisters);
      case INT64:
      case UINT64:
        paramInt1 = ArrayDecoders.decodeVarint64(paramArrayOfbyte, paramInt1, paramRegisters);
        paramRegisters.object1 = Long.valueOf(paramRegisters.long1);
        return paramInt1;
      case ENUM:
      case INT32:
      case UINT32:
        paramInt1 = ArrayDecoders.decodeVarint32(paramArrayOfbyte, paramInt1, paramRegisters);
        paramRegisters.object1 = Integer.valueOf(paramRegisters.int1);
        return paramInt1;
      case FLOAT:
        paramRegisters.object1 = Float.valueOf(ArrayDecoders.decodeFloat(paramArrayOfbyte, paramInt1));
        paramInt1 += 4;
        return paramInt1;
      case FIXED64:
      case SFIXED64:
        paramRegisters.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(paramArrayOfbyte, paramInt1));
        paramInt1 += 8;
        return paramInt1;
      case FIXED32:
      case SFIXED32:
        paramRegisters.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(paramArrayOfbyte, paramInt1));
        paramInt1 += 4;
        return paramInt1;
      case DOUBLE:
        paramRegisters.object1 = Double.valueOf(ArrayDecoders.decodeDouble(paramArrayOfbyte, paramInt1));
        paramInt1 += 8;
        return paramInt1;
      case BYTES:
        return ArrayDecoders.decodeBytes(paramArrayOfbyte, paramInt1, paramRegisters);
      case BOOL:
        break;
    } 
    paramInt1 = ArrayDecoders.decodeVarint64(paramArrayOfbyte, paramInt1, paramRegisters);
    if (paramRegisters.long1 != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    paramRegisters.object1 = Boolean.valueOf(bool);
    return paramInt1;
  }
  
  private static <T> double doubleAt(T paramT, long paramLong) {
    return UnsafeUtil.getDouble(paramT, paramLong);
  }
  
  private boolean equals(T paramT1, T paramT2, int paramInt) {
    int i = typeAndOffsetAt(paramInt);
    long l = offset(i);
    i = type(i);
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool6 = false;
    boolean bool7 = false;
    boolean bool8 = false;
    boolean bool9 = false;
    boolean bool10 = false;
    boolean bool11 = false;
    boolean bool12 = false;
    boolean bool13 = false;
    boolean bool14 = false;
    boolean bool15 = false;
    boolean bool16 = false;
    boolean bool17 = false;
    boolean bool18 = false;
    boolean bool19 = false;
    switch (i) {
      default:
        return true;
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      case 66:
      case 67:
      case 68:
        bool20 = bool19;
        if (isOneofCaseEqual(paramT1, paramT2, paramInt)) {
          bool20 = bool19;
          if (SchemaUtil.safeEquals(UnsafeUtil.getObject(paramT1, l), UnsafeUtil.getObject(paramT2, l)))
            bool20 = true; 
        } 
        return bool20;
      case 50:
        return SchemaUtil.safeEquals(UnsafeUtil.getObject(paramT1, l), UnsafeUtil.getObject(paramT2, l));
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
        return SchemaUtil.safeEquals(UnsafeUtil.getObject(paramT1, l), UnsafeUtil.getObject(paramT2, l));
      case 17:
        bool20 = bool1;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool1;
          if (SchemaUtil.safeEquals(UnsafeUtil.getObject(paramT1, l), UnsafeUtil.getObject(paramT2, l)))
            bool20 = true; 
        } 
        return bool20;
      case 16:
        bool20 = bool2;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool2;
          if (UnsafeUtil.getLong(paramT1, l) == UnsafeUtil.getLong(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 15:
        bool20 = bool3;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool3;
          if (UnsafeUtil.getInt(paramT1, l) == UnsafeUtil.getInt(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 14:
        bool20 = bool4;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool4;
          if (UnsafeUtil.getLong(paramT1, l) == UnsafeUtil.getLong(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 13:
        bool20 = bool5;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool5;
          if (UnsafeUtil.getInt(paramT1, l) == UnsafeUtil.getInt(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 12:
        bool20 = bool6;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool6;
          if (UnsafeUtil.getInt(paramT1, l) == UnsafeUtil.getInt(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 11:
        bool20 = bool7;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool7;
          if (UnsafeUtil.getInt(paramT1, l) == UnsafeUtil.getInt(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 10:
        bool20 = bool8;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool8;
          if (SchemaUtil.safeEquals(UnsafeUtil.getObject(paramT1, l), UnsafeUtil.getObject(paramT2, l)))
            bool20 = true; 
        } 
        return bool20;
      case 9:
        bool20 = bool9;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool9;
          if (SchemaUtil.safeEquals(UnsafeUtil.getObject(paramT1, l), UnsafeUtil.getObject(paramT2, l)))
            bool20 = true; 
        } 
        return bool20;
      case 8:
        bool20 = bool10;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool10;
          if (SchemaUtil.safeEquals(UnsafeUtil.getObject(paramT1, l), UnsafeUtil.getObject(paramT2, l)))
            bool20 = true; 
        } 
        return bool20;
      case 7:
        bool20 = bool11;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool11;
          if (UnsafeUtil.getBoolean(paramT1, l) == UnsafeUtil.getBoolean(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 6:
        bool20 = bool12;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool12;
          if (UnsafeUtil.getInt(paramT1, l) == UnsafeUtil.getInt(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 5:
        bool20 = bool13;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool13;
          if (UnsafeUtil.getLong(paramT1, l) == UnsafeUtil.getLong(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 4:
        bool20 = bool14;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool14;
          if (UnsafeUtil.getInt(paramT1, l) == UnsafeUtil.getInt(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 3:
        bool20 = bool15;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool15;
          if (UnsafeUtil.getLong(paramT1, l) == UnsafeUtil.getLong(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 2:
        bool20 = bool16;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool16;
          if (UnsafeUtil.getLong(paramT1, l) == UnsafeUtil.getLong(paramT2, l))
            bool20 = true; 
        } 
        return bool20;
      case 1:
        bool20 = bool17;
        if (arePresentForEquals(paramT1, paramT2, paramInt)) {
          bool20 = bool17;
          if (Float.floatToIntBits(UnsafeUtil.getFloat(paramT1, l)) == Float.floatToIntBits(UnsafeUtil.getFloat(paramT2, l)))
            bool20 = true; 
        } 
        return bool20;
      case 0:
        break;
    } 
    boolean bool20 = bool18;
    if (arePresentForEquals(paramT1, paramT2, paramInt)) {
      bool20 = bool18;
      if (Double.doubleToLongBits(UnsafeUtil.getDouble(paramT1, l)) == Double.doubleToLongBits(UnsafeUtil.getDouble(paramT2, l)))
        bool20 = true; 
    } 
    return bool20;
  }
  
  private final <UT, UB> UB filterMapUnknownEnumValues(Object paramObject, int paramInt, UB paramUB, UnknownFieldSchema<UT, UB> paramUnknownFieldSchema) {
    int i = numberAt(paramInt);
    paramObject = UnsafeUtil.getObject(paramObject, offset(typeAndOffsetAt(paramInt)));
    if (paramObject == null)
      return paramUB; 
    Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(paramInt);
    return (enumVerifier == null) ? paramUB : filterUnknownEnumMap(paramInt, i, this.mapFieldSchema.forMutableMapData(paramObject), enumVerifier, paramUB, paramUnknownFieldSchema);
  }
  
  private final <K, V, UT, UB> UB filterUnknownEnumMap(int paramInt1, int paramInt2, Map<K, V> paramMap, Internal.EnumVerifier paramEnumVerifier, UB paramUB, UnknownFieldSchema<UT, UB> paramUnknownFieldSchema) {
    UB uB;
    MapEntryLite.Metadata<?, ?> metadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(paramInt1));
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      if (!paramEnumVerifier.isInRange(((Integer)entry.getValue()).intValue())) {
        UB uB1 = paramUB;
        if (paramUB == null)
          uB1 = paramUnknownFieldSchema.newBuilder(); 
        ByteString.CodedBuilder codedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadata, entry.getKey(), entry.getValue()));
        CodedOutputStream codedOutputStream = codedBuilder.getCodedOutput();
        try {
          MapEntryLite.writeTo(codedOutputStream, metadata, entry.getKey(), entry.getValue());
          paramUnknownFieldSchema.addLengthDelimited(uB1, paramInt2, codedBuilder.build());
          iterator.remove();
          uB = uB1;
        } catch (IOException iOException) {
          throw new RuntimeException(iOException);
        } 
      } 
    } 
    return uB;
  }
  
  private static <T> float floatAt(T paramT, long paramLong) {
    return UnsafeUtil.getFloat(paramT, paramLong);
  }
  
  private Internal.EnumVerifier getEnumFieldVerifier(int paramInt) {
    return (Internal.EnumVerifier)this.objects[paramInt / 3 * 2 + 1];
  }
  
  private Object getMapFieldDefaultEntry(int paramInt) {
    return this.objects[paramInt / 3 * 2];
  }
  
  private Schema getMessageFieldSchema(int paramInt) {
    paramInt = paramInt / 3 * 2;
    Schema<?> schema = (Schema)this.objects[paramInt];
    if (schema != null)
      return schema; 
    schema = Protobuf.getInstance().schemaFor((Class)this.objects[paramInt + 1]);
    this.objects[paramInt] = schema;
    return schema;
  }
  
  static UnknownFieldSetLite getMutableUnknownFields(Object paramObject) {
    GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite)paramObject;
    UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
    paramObject = unknownFieldSetLite;
    if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
      paramObject = UnknownFieldSetLite.newInstance();
      generatedMessageLite.unknownFields = (UnknownFieldSetLite)paramObject;
    } 
    return (UnknownFieldSetLite)paramObject;
  }
  
  private int getSerializedSizeProto2(T paramT) {
    Unsafe unsafe = UNSAFE;
    int i = -1;
    int j = 0;
    int k = 0;
    int m;
    for (m = 0; j < this.buffer.length; m = i4) {
      int i3;
      int i4;
      int i5;
      int n = typeAndOffsetAt(j);
      int i1 = numberAt(j);
      int i2 = type(n);
      if (i2 <= 17) {
        int i6 = this.buffer[j + 2];
        i4 = 0xFFFFF & i6;
        if (i4 != i) {
          m = unsafe.getInt(paramT, i4);
          i = i4;
        } 
        int i7 = 1 << i6 >>> 20;
        i4 = m;
      } else {
        if (this.useCachedSizeField && i2 >= FieldType.DOUBLE_LIST_PACKED.id() && i2 <= FieldType.SINT64_LIST_PACKED.id()) {
          i4 = this.buffer[j + 2] & 0xFFFFF;
        } else {
          i4 = 0;
        } 
        boolean bool = false;
        int i6 = i4;
        i4 = m;
      } 
      long l = offset(n);
      switch (i2) {
        default:
          m = k;
          break;
        case 68:
          i3 = j;
          m = k;
          if (isOneofPresent(paramT, i1, i3))
            m = k + CodedOutputStream.computeGroupSize(i1, (MessageLite)unsafe.getObject(paramT, l), getMessageFieldSchema(i3)); 
          break;
        case 67:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeSInt64Size(i1, oneofLongAt(paramT, l)); 
          break;
        case 66:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeSInt32Size(i1, oneofIntAt(paramT, l)); 
          break;
        case 65:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeSFixed64Size(i1, 0L); 
          break;
        case 64:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeSFixed32Size(i1, 0); 
          break;
        case 63:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeEnumSize(i1, oneofIntAt(paramT, l)); 
          break;
        case 62:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeUInt32Size(i1, oneofIntAt(paramT, l)); 
          break;
        case 61:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeBytesSize(i1, (ByteString)unsafe.getObject(paramT, l)); 
          break;
        case 60:
          i3 = j;
          m = k;
          if (isOneofPresent(paramT, i1, i3))
            m = k + SchemaUtil.computeSizeMessage(i1, unsafe.getObject(paramT, l), getMessageFieldSchema(i3)); 
          break;
        case 59:
          m = k;
          if (isOneofPresent(paramT, i1, j)) {
            Object object = unsafe.getObject(paramT, l);
            if (object instanceof ByteString) {
              m = k + CodedOutputStream.computeBytesSize(i1, (ByteString)object);
              break;
            } 
            m = k + CodedOutputStream.computeStringSize(i1, (String)object);
          } 
          break;
        case 58:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeBoolSize(i1, true); 
          break;
        case 57:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeFixed32Size(i1, 0); 
          break;
        case 56:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeFixed64Size(i1, 0L); 
          break;
        case 55:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeInt32Size(i1, oneofIntAt(paramT, l)); 
          break;
        case 54:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeUInt64Size(i1, oneofLongAt(paramT, l)); 
          break;
        case 53:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeInt64Size(i1, oneofLongAt(paramT, l)); 
          break;
        case 52:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeFloatSize(i1, 0.0F); 
          break;
        case 51:
          m = k;
          if (isOneofPresent(paramT, i1, j))
            m = k + CodedOutputStream.computeDoubleSize(i1, 0.0D); 
          break;
        case 50:
          m = k + this.mapFieldSchema.getSerializedSize(i1, unsafe.getObject(paramT, l), getMapFieldDefaultEntry(j));
          break;
        case 49:
          m = k + SchemaUtil.computeSizeGroupList(i1, (List<MessageLite>)unsafe.getObject(paramT, l), getMessageFieldSchema(j));
          break;
        case 48:
          i5 = SchemaUtil.computeSizeSInt64ListNoTag((List<Long>)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 47:
          i5 = SchemaUtil.computeSizeSInt32ListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 46:
          i5 = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 45:
          i5 = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 44:
          i5 = SchemaUtil.computeSizeEnumListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 43:
          i5 = SchemaUtil.computeSizeUInt32ListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 42:
          i5 = SchemaUtil.computeSizeBoolListNoTag((List)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 41:
          i5 = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 40:
          i5 = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 39:
          i5 = SchemaUtil.computeSizeInt32ListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 38:
          i5 = SchemaUtil.computeSizeUInt64ListNoTag((List<Long>)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 37:
          i5 = SchemaUtil.computeSizeInt64ListNoTag((List<Long>)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 36:
          i5 = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 35:
          i5 = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(paramT, l));
          m = k;
          if (i5 > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, i3, i5); 
            m = k + CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i5) + i5;
          } 
          break;
        case 34:
          m = k + SchemaUtil.computeSizeSInt64List(i1, (List<Long>)unsafe.getObject(paramT, l), false);
          break;
        case 33:
          m = k + SchemaUtil.computeSizeSInt32List(i1, (List<Integer>)unsafe.getObject(paramT, l), false);
          break;
        case 32:
          m = k + SchemaUtil.computeSizeFixed64List(i1, (List)unsafe.getObject(paramT, l), false);
          break;
        case 31:
          m = k + SchemaUtil.computeSizeFixed32List(i1, (List)unsafe.getObject(paramT, l), false);
          break;
        case 30:
          m = k + SchemaUtil.computeSizeEnumList(i1, (List<Integer>)unsafe.getObject(paramT, l), false);
          break;
        case 29:
          m = k + SchemaUtil.computeSizeUInt32List(i1, (List<Integer>)unsafe.getObject(paramT, l), false);
          break;
        case 28:
          m = k + SchemaUtil.computeSizeByteStringList(i1, (List<ByteString>)unsafe.getObject(paramT, l));
          break;
        case 27:
          m = k + SchemaUtil.computeSizeMessageList(i1, (List)unsafe.getObject(paramT, l), getMessageFieldSchema(j));
          break;
        case 26:
          m = k + SchemaUtil.computeSizeStringList(i1, (List)unsafe.getObject(paramT, l));
          break;
        case 25:
          m = k + SchemaUtil.computeSizeBoolList(i1, (List)unsafe.getObject(paramT, l), false);
          break;
        case 24:
          m = k + SchemaUtil.computeSizeFixed32List(i1, (List)unsafe.getObject(paramT, l), false);
          break;
        case 23:
          m = k + SchemaUtil.computeSizeFixed64List(i1, (List)unsafe.getObject(paramT, l), false);
          break;
        case 22:
          m = k + SchemaUtil.computeSizeInt32List(i1, (List<Integer>)unsafe.getObject(paramT, l), false);
          break;
        case 21:
          m = k + SchemaUtil.computeSizeUInt64List(i1, (List<Long>)unsafe.getObject(paramT, l), false);
          break;
        case 20:
          m = k + SchemaUtil.computeSizeInt64List(i1, (List<Long>)unsafe.getObject(paramT, l), false);
          break;
        case 19:
          m = k + SchemaUtil.computeSizeFixed32List(i1, (List)unsafe.getObject(paramT, l), false);
          break;
        case 18:
          m = k + SchemaUtil.computeSizeFixed64List(i1, (List)unsafe.getObject(paramT, l), false);
          break;
        case 17:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeGroupSize(i1, (MessageLite)unsafe.getObject(paramT, l), getMessageFieldSchema(j)); 
          break;
        case 16:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeSInt64Size(i1, unsafe.getLong(paramT, l)); 
          break;
        case 15:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeSInt32Size(i1, unsafe.getInt(paramT, l)); 
          break;
        case 14:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeSFixed64Size(i1, 0L); 
          break;
        case 13:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeSFixed32Size(i1, 0); 
          break;
        case 12:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeEnumSize(i1, unsafe.getInt(paramT, l)); 
          break;
        case 11:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeUInt32Size(i1, unsafe.getInt(paramT, l)); 
          break;
        case 10:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeBytesSize(i1, (ByteString)unsafe.getObject(paramT, l)); 
          break;
        case 9:
          m = k;
          if ((i4 & i5) != 0)
            m = k + SchemaUtil.computeSizeMessage(i1, unsafe.getObject(paramT, l), getMessageFieldSchema(j)); 
          break;
        case 8:
          m = k;
          if ((i4 & i5) != 0) {
            Object object = unsafe.getObject(paramT, l);
            if (object instanceof ByteString) {
              m = k + CodedOutputStream.computeBytesSize(i1, (ByteString)object);
              break;
            } 
            m = k + CodedOutputStream.computeStringSize(i1, (String)object);
          } 
          break;
        case 7:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeBoolSize(i1, true); 
          break;
        case 6:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeFixed32Size(i1, 0); 
          break;
        case 5:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeFixed64Size(i1, 0L); 
          break;
        case 4:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeInt32Size(i1, unsafe.getInt(paramT, l)); 
          break;
        case 3:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeUInt64Size(i1, unsafe.getLong(paramT, l)); 
          break;
        case 2:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeInt64Size(i1, unsafe.getLong(paramT, l)); 
          break;
        case 1:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeFloatSize(i1, 0.0F); 
          break;
        case 0:
          m = k;
          if ((i4 & i5) != 0)
            m = k + CodedOutputStream.computeDoubleSize(i1, 0.0D); 
          break;
      } 
      j += 3;
      k = m;
    } 
    j = k + getUnknownFieldsSerializedSize(this.unknownFieldSchema, paramT);
    m = j;
    if (this.hasExtensions)
      m = j + this.extensionSchema.getExtensions(paramT).getSerializedSize(); 
    return m;
  }
  
  private int getSerializedSizeProto3(T paramT) {
    Unsafe unsafe = UNSAFE;
    byte b = 0;
    int i;
    for (i = b; b < this.buffer.length; i = k) {
      int n;
      int j = typeAndOffsetAt(b);
      int k = type(j);
      int m = numberAt(b);
      long l = offset(j);
      if (k >= FieldType.DOUBLE_LIST_PACKED.id() && k <= FieldType.SINT64_LIST_PACKED.id()) {
        j = this.buffer[b + 2] & 0xFFFFF;
      } else {
        j = 0;
      } 
      switch (k) {
        default:
          k = i;
          break;
        case 68:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeGroupSize(m, (MessageLite)UnsafeUtil.getObject(paramT, l), getMessageFieldSchema(b)); 
          break;
        case 67:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeSInt64Size(m, oneofLongAt(paramT, l)); 
          break;
        case 66:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeSInt32Size(m, oneofIntAt(paramT, l)); 
          break;
        case 65:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeSFixed64Size(m, 0L); 
          break;
        case 64:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeSFixed32Size(m, 0); 
          break;
        case 63:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeEnumSize(m, oneofIntAt(paramT, l)); 
          break;
        case 62:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeUInt32Size(m, oneofIntAt(paramT, l)); 
          break;
        case 61:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeBytesSize(m, (ByteString)UnsafeUtil.getObject(paramT, l)); 
          break;
        case 60:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + SchemaUtil.computeSizeMessage(m, UnsafeUtil.getObject(paramT, l), getMessageFieldSchema(b)); 
          break;
        case 59:
          k = i;
          if (isOneofPresent(paramT, m, b)) {
            Object object = UnsafeUtil.getObject(paramT, l);
            if (object instanceof ByteString) {
              k = i + CodedOutputStream.computeBytesSize(m, (ByteString)object);
              break;
            } 
            k = i + CodedOutputStream.computeStringSize(m, (String)object);
          } 
          break;
        case 58:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeBoolSize(m, true); 
          break;
        case 57:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeFixed32Size(m, 0); 
          break;
        case 56:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeFixed64Size(m, 0L); 
          break;
        case 55:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeInt32Size(m, oneofIntAt(paramT, l)); 
          break;
        case 54:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeUInt64Size(m, oneofLongAt(paramT, l)); 
          break;
        case 53:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeInt64Size(m, oneofLongAt(paramT, l)); 
          break;
        case 52:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeFloatSize(m, 0.0F); 
          break;
        case 51:
          k = i;
          if (isOneofPresent(paramT, m, b))
            k = i + CodedOutputStream.computeDoubleSize(m, 0.0D); 
          break;
        case 50:
          k = i + this.mapFieldSchema.getSerializedSize(m, UnsafeUtil.getObject(paramT, l), getMapFieldDefaultEntry(b));
          break;
        case 49:
          k = i + SchemaUtil.computeSizeGroupList(m, (List)listAt(paramT, l), getMessageFieldSchema(b));
          break;
        case 48:
          n = SchemaUtil.computeSizeSInt64ListNoTag((List<Long>)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 47:
          n = SchemaUtil.computeSizeSInt32ListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 46:
          n = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 45:
          n = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 44:
          n = SchemaUtil.computeSizeEnumListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 43:
          n = SchemaUtil.computeSizeUInt32ListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 42:
          n = SchemaUtil.computeSizeBoolListNoTag((List)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 41:
          n = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 40:
          n = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 39:
          n = SchemaUtil.computeSizeInt32ListNoTag((List<Integer>)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 38:
          n = SchemaUtil.computeSizeUInt64ListNoTag((List<Long>)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 37:
          n = SchemaUtil.computeSizeInt64ListNoTag((List<Long>)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 36:
          n = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 35:
          n = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(paramT, l));
          k = i;
          if (n > 0) {
            if (this.useCachedSizeField)
              unsafe.putInt(paramT, j, n); 
            k = i + CodedOutputStream.computeTagSize(m) + CodedOutputStream.computeUInt32SizeNoTag(n) + n;
          } 
          break;
        case 34:
          k = i + SchemaUtil.computeSizeSInt64List(m, (List)listAt(paramT, l), false);
          break;
        case 33:
          k = i + SchemaUtil.computeSizeSInt32List(m, (List)listAt(paramT, l), false);
          break;
        case 32:
          k = i + SchemaUtil.computeSizeFixed64List(m, listAt(paramT, l), false);
          break;
        case 31:
          k = i + SchemaUtil.computeSizeFixed32List(m, listAt(paramT, l), false);
          break;
        case 30:
          k = i + SchemaUtil.computeSizeEnumList(m, (List)listAt(paramT, l), false);
          break;
        case 29:
          k = i + SchemaUtil.computeSizeUInt32List(m, (List)listAt(paramT, l), false);
          break;
        case 28:
          k = i + SchemaUtil.computeSizeByteStringList(m, (List)listAt(paramT, l));
          break;
        case 27:
          k = i + SchemaUtil.computeSizeMessageList(m, listAt(paramT, l), getMessageFieldSchema(b));
          break;
        case 26:
          k = i + SchemaUtil.computeSizeStringList(m, listAt(paramT, l));
          break;
        case 25:
          k = i + SchemaUtil.computeSizeBoolList(m, listAt(paramT, l), false);
          break;
        case 24:
          k = i + SchemaUtil.computeSizeFixed32List(m, listAt(paramT, l), false);
          break;
        case 23:
          k = i + SchemaUtil.computeSizeFixed64List(m, listAt(paramT, l), false);
          break;
        case 22:
          k = i + SchemaUtil.computeSizeInt32List(m, (List)listAt(paramT, l), false);
          break;
        case 21:
          k = i + SchemaUtil.computeSizeUInt64List(m, (List)listAt(paramT, l), false);
          break;
        case 20:
          k = i + SchemaUtil.computeSizeInt64List(m, (List)listAt(paramT, l), false);
          break;
        case 19:
          k = i + SchemaUtil.computeSizeFixed32List(m, listAt(paramT, l), false);
          break;
        case 18:
          k = i + SchemaUtil.computeSizeFixed64List(m, listAt(paramT, l), false);
          break;
        case 17:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeGroupSize(m, (MessageLite)UnsafeUtil.getObject(paramT, l), getMessageFieldSchema(b)); 
          break;
        case 16:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeSInt64Size(m, UnsafeUtil.getLong(paramT, l)); 
          break;
        case 15:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeSInt32Size(m, UnsafeUtil.getInt(paramT, l)); 
          break;
        case 14:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeSFixed64Size(m, 0L); 
          break;
        case 13:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeSFixed32Size(m, 0); 
          break;
        case 12:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeEnumSize(m, UnsafeUtil.getInt(paramT, l)); 
          break;
        case 11:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeUInt32Size(m, UnsafeUtil.getInt(paramT, l)); 
          break;
        case 10:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeBytesSize(m, (ByteString)UnsafeUtil.getObject(paramT, l)); 
          break;
        case 9:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + SchemaUtil.computeSizeMessage(m, UnsafeUtil.getObject(paramT, l), getMessageFieldSchema(b)); 
          break;
        case 8:
          k = i;
          if (isFieldPresent(paramT, b)) {
            Object object = UnsafeUtil.getObject(paramT, l);
            if (object instanceof ByteString) {
              k = i + CodedOutputStream.computeBytesSize(m, (ByteString)object);
              break;
            } 
            k = i + CodedOutputStream.computeStringSize(m, (String)object);
          } 
          break;
        case 7:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeBoolSize(m, true); 
          break;
        case 6:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeFixed32Size(m, 0); 
          break;
        case 5:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeFixed64Size(m, 0L); 
          break;
        case 4:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeInt32Size(m, UnsafeUtil.getInt(paramT, l)); 
          break;
        case 3:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeUInt64Size(m, UnsafeUtil.getLong(paramT, l)); 
          break;
        case 2:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeInt64Size(m, UnsafeUtil.getLong(paramT, l)); 
          break;
        case 1:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeFloatSize(m, 0.0F); 
          break;
        case 0:
          k = i;
          if (isFieldPresent(paramT, b))
            k = i + CodedOutputStream.computeDoubleSize(m, 0.0D); 
          break;
      } 
      b += 3;
    } 
    return i + getUnknownFieldsSerializedSize(this.unknownFieldSchema, paramT);
  }
  
  private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> paramUnknownFieldSchema, T paramT) {
    return paramUnknownFieldSchema.getSerializedSize(paramUnknownFieldSchema.getFromMessage(paramT));
  }
  
  private static <T> int intAt(T paramT, long paramLong) {
    return UnsafeUtil.getInt(paramT, paramLong);
  }
  
  private static boolean isEnforceUtf8(int paramInt) {
    boolean bool;
    if ((paramInt & 0x20000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isFieldPresent(T paramT, int paramInt) {
    boolean bool = this.proto3;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool6 = false;
    boolean bool7 = false;
    boolean bool8 = false;
    boolean bool9 = false;
    boolean bool10 = false;
    boolean bool11 = false;
    boolean bool12 = false;
    boolean bool13 = false;
    boolean bool14 = false;
    boolean bool15 = false;
    boolean bool16 = false;
    if (bool) {
      paramInt = typeAndOffsetAt(paramInt);
      long l = offset(paramInt);
      switch (type(paramInt)) {
        default:
          throw new IllegalArgumentException();
        case 17:
          if (UnsafeUtil.getObject(paramT, l) != null)
            bool16 = true; 
          return bool16;
        case 16:
          bool16 = bool1;
          if (UnsafeUtil.getLong(paramT, l) != 0L)
            bool16 = true; 
          return bool16;
        case 15:
          bool16 = bool2;
          if (UnsafeUtil.getInt(paramT, l) != 0)
            bool16 = true; 
          return bool16;
        case 14:
          bool16 = bool3;
          if (UnsafeUtil.getLong(paramT, l) != 0L)
            bool16 = true; 
          return bool16;
        case 13:
          bool16 = bool4;
          if (UnsafeUtil.getInt(paramT, l) != 0)
            bool16 = true; 
          return bool16;
        case 12:
          bool16 = bool5;
          if (UnsafeUtil.getInt(paramT, l) != 0)
            bool16 = true; 
          return bool16;
        case 11:
          bool16 = bool6;
          if (UnsafeUtil.getInt(paramT, l) != 0)
            bool16 = true; 
          return bool16;
        case 10:
          return ByteString.EMPTY.equals(UnsafeUtil.getObject(paramT, l)) ^ true;
        case 9:
          bool16 = bool7;
          if (UnsafeUtil.getObject(paramT, l) != null)
            bool16 = true; 
          return bool16;
        case 8:
          paramT = (T)UnsafeUtil.getObject(paramT, l);
          if (paramT instanceof String)
            return ((String)paramT).isEmpty() ^ true; 
          if (paramT instanceof ByteString)
            return ByteString.EMPTY.equals(paramT) ^ true; 
          throw new IllegalArgumentException();
        case 7:
          return UnsafeUtil.getBoolean(paramT, l);
        case 6:
          bool16 = bool8;
          if (UnsafeUtil.getInt(paramT, l) != 0)
            bool16 = true; 
          return bool16;
        case 5:
          bool16 = bool9;
          if (UnsafeUtil.getLong(paramT, l) != 0L)
            bool16 = true; 
          return bool16;
        case 4:
          bool16 = bool10;
          if (UnsafeUtil.getInt(paramT, l) != 0)
            bool16 = true; 
          return bool16;
        case 3:
          bool16 = bool11;
          if (UnsafeUtil.getLong(paramT, l) != 0L)
            bool16 = true; 
          return bool16;
        case 2:
          bool16 = bool12;
          if (UnsafeUtil.getLong(paramT, l) != 0L)
            bool16 = true; 
          return bool16;
        case 1:
          bool16 = bool13;
          if (UnsafeUtil.getFloat(paramT, l) != 0.0F)
            bool16 = true; 
          return bool16;
        case 0:
          break;
      } 
      bool16 = bool14;
      if (UnsafeUtil.getDouble(paramT, l) != 0.0D)
        bool16 = true; 
      return bool16;
    } 
    paramInt = presenceMaskAndOffsetAt(paramInt);
    bool16 = bool15;
    if ((UnsafeUtil.getInt(paramT, (paramInt & 0xFFFFF)) & 1 << paramInt >>> 20) != 0)
      bool16 = true; 
    return bool16;
  }
  
  private boolean isFieldPresent(T paramT, int paramInt1, int paramInt2, int paramInt3) {
    boolean bool;
    if (this.proto3)
      return isFieldPresent(paramT, paramInt1); 
    if ((paramInt2 & paramInt3) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static boolean isInitialized(Object paramObject, int paramInt, Schema<Object> paramSchema) {
    return paramSchema.isInitialized(UnsafeUtil.getObject(paramObject, offset(paramInt)));
  }
  
  private <N> boolean isListInitialized(Object paramObject, int paramInt1, int paramInt2) {
    List list = (List)UnsafeUtil.getObject(paramObject, offset(paramInt1));
    if (list.isEmpty())
      return true; 
    paramObject = getMessageFieldSchema(paramInt2);
    for (paramInt1 = 0; paramInt1 < list.size(); paramInt1++) {
      if (!paramObject.isInitialized(list.get(paramInt1)))
        return false; 
    } 
    return true;
  }
  
  private boolean isMapInitialized(T paramT, int paramInt1, int paramInt2) {
    Map<?, ?> map = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(paramT, offset(paramInt1)));
    if (map.isEmpty())
      return true; 
    Object<?> object = (Object<?>)getMapFieldDefaultEntry(paramInt2);
    if ((this.mapFieldSchema.forMapMetadata(object)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE)
      return true; 
    object = null;
    for (Object object2 : map.values()) {
      Object<?> object1 = object;
      if (object == null)
        object1 = Protobuf.getInstance().schemaFor(object2.getClass()); 
      object = object1;
      if (!object1.isInitialized(object2))
        return false; 
    } 
    return true;
  }
  
  private boolean isOneofCaseEqual(T paramT1, T paramT2, int paramInt) {
    boolean bool;
    long l = (presenceMaskAndOffsetAt(paramInt) & 0xFFFFF);
    if (UnsafeUtil.getInt(paramT1, l) == UnsafeUtil.getInt(paramT2, l)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isOneofPresent(T paramT, int paramInt1, int paramInt2) {
    boolean bool;
    if (UnsafeUtil.getInt(paramT, (presenceMaskAndOffsetAt(paramInt2) & 0xFFFFF)) == paramInt1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static boolean isRequired(int paramInt) {
    boolean bool;
    if ((paramInt & 0x10000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static List<?> listAt(Object paramObject, long paramLong) {
    return (List)UnsafeUtil.getObject(paramObject, paramLong);
  }
  
  private static <T> long longAt(T paramT, long paramLong) {
    return UnsafeUtil.getLong(paramT, paramLong);
  }
  
  private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> paramUnknownFieldSchema, ExtensionSchema<ET> paramExtensionSchema, T paramT, Reader paramReader, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #6
    //   3: aload #6
    //   5: astore #7
    //   7: aload #6
    //   9: astore #8
    //   11: aload #4
    //   13: invokeinterface getFieldNumber : ()I
    //   18: istore #9
    //   20: aload #6
    //   22: astore #8
    //   24: aload_0
    //   25: iload #9
    //   27: invokespecial positionForFieldNumber : (I)I
    //   30: istore #10
    //   32: iload #10
    //   34: ifge -> 319
    //   37: iload #9
    //   39: ldc_w 2147483647
    //   42: if_icmpne -> 96
    //   45: aload_0
    //   46: getfield checkInitializedCount : I
    //   49: istore #10
    //   51: iload #10
    //   53: aload_0
    //   54: getfield repeatedFieldOffsetStart : I
    //   57: if_icmpge -> 83
    //   60: aload_0
    //   61: aload_3
    //   62: aload_0
    //   63: getfield intArray : [I
    //   66: iload #10
    //   68: iaload
    //   69: aload #6
    //   71: aload_1
    //   72: invokespecial filterMapUnknownEnumValues : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   75: astore #6
    //   77: iinc #10, 1
    //   80: goto -> 51
    //   83: aload #6
    //   85: ifnull -> 95
    //   88: aload_1
    //   89: aload_3
    //   90: aload #6
    //   92: invokevirtual setBuilderToMessage : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   95: return
    //   96: aload #6
    //   98: astore #8
    //   100: aload_0
    //   101: getfield hasExtensions : Z
    //   104: ifne -> 113
    //   107: aconst_null
    //   108: astore #11
    //   110: goto -> 131
    //   113: aload #6
    //   115: astore #8
    //   117: aload_2
    //   118: aload #5
    //   120: aload_0
    //   121: getfield defaultInstance : Lcom/google/protobuf/MessageLite;
    //   124: iload #9
    //   126: invokevirtual findExtensionByNumber : (Lcom/google/protobuf/ExtensionRegistryLite;Lcom/google/protobuf/MessageLite;I)Ljava/lang/Object;
    //   129: astore #11
    //   131: aload #11
    //   133: ifnull -> 184
    //   136: aload #7
    //   138: astore #12
    //   140: aload #7
    //   142: ifnonnull -> 156
    //   145: aload #6
    //   147: astore #8
    //   149: aload_2
    //   150: aload_3
    //   151: invokevirtual getMutableExtensions : (Ljava/lang/Object;)Lcom/google/protobuf/FieldSet;
    //   154: astore #12
    //   156: aload #6
    //   158: astore #8
    //   160: aload_2
    //   161: aload #4
    //   163: aload #11
    //   165: aload #5
    //   167: aload #12
    //   169: aload #6
    //   171: aload_1
    //   172: invokevirtual parseExtension : (Lcom/google/protobuf/Reader;Ljava/lang/Object;Lcom/google/protobuf/ExtensionRegistryLite;Lcom/google/protobuf/FieldSet;Ljava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   175: astore #6
    //   177: aload #12
    //   179: astore #7
    //   181: goto -> 7
    //   184: aload #6
    //   186: astore #8
    //   188: aload_1
    //   189: aload #4
    //   191: invokevirtual shouldDiscardUnknownFields : (Lcom/google/protobuf/Reader;)Z
    //   194: ifeq -> 218
    //   197: aload #6
    //   199: astore #11
    //   201: aload #6
    //   203: astore #8
    //   205: aload #4
    //   207: invokeinterface skipField : ()Z
    //   212: ifeq -> 268
    //   215: goto -> 7
    //   218: aload #6
    //   220: astore #12
    //   222: aload #6
    //   224: ifnonnull -> 238
    //   227: aload #6
    //   229: astore #8
    //   231: aload_1
    //   232: aload_3
    //   233: invokevirtual getBuilderFromMessage : (Ljava/lang/Object;)Ljava/lang/Object;
    //   236: astore #12
    //   238: aload #12
    //   240: astore #8
    //   242: aload_1
    //   243: aload #12
    //   245: aload #4
    //   247: invokevirtual mergeOneFieldFrom : (Ljava/lang/Object;Lcom/google/protobuf/Reader;)Z
    //   250: istore #13
    //   252: aload #12
    //   254: astore #11
    //   256: iload #13
    //   258: ifeq -> 268
    //   261: aload #12
    //   263: astore #6
    //   265: goto -> 7
    //   268: aload_0
    //   269: getfield checkInitializedCount : I
    //   272: istore #10
    //   274: iload #10
    //   276: aload_0
    //   277: getfield repeatedFieldOffsetStart : I
    //   280: if_icmpge -> 306
    //   283: aload_0
    //   284: aload_3
    //   285: aload_0
    //   286: getfield intArray : [I
    //   289: iload #10
    //   291: iaload
    //   292: aload #11
    //   294: aload_1
    //   295: invokespecial filterMapUnknownEnumValues : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   298: astore #11
    //   300: iinc #10, 1
    //   303: goto -> 274
    //   306: aload #11
    //   308: ifnull -> 318
    //   311: aload_1
    //   312: aload_3
    //   313: aload #11
    //   315: invokevirtual setBuilderToMessage : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   318: return
    //   319: aload #6
    //   321: astore #8
    //   323: aload_0
    //   324: iload #10
    //   326: invokespecial typeAndOffsetAt : (I)I
    //   329: istore #14
    //   331: aload #6
    //   333: astore #11
    //   335: aload #6
    //   337: astore #8
    //   339: iload #14
    //   341: invokestatic type : (I)I
    //   344: tableswitch default -> 636, 0 -> 3732, 1 -> 3690, 2 -> 3648, 3 -> 3606, 4 -> 3564, 5 -> 3522, 6 -> 3480, 7 -> 3438, 8 -> 3403, 9 -> 3276, 10 -> 3234, 11 -> 3192, 12 -> 3067, 13 -> 3025, 14 -> 2983, 15 -> 2941, 16 -> 2899, 17 -> 2772, 18 -> 2741, 19 -> 2710, 20 -> 2679, 21 -> 2648, 22 -> 2617, 23 -> 2586, 24 -> 2555, 25 -> 2524, 26 -> 2504, 27 -> 2476, 28 -> 2445, 29 -> 2414, 30 -> 2341, 31 -> 2310, 32 -> 2279, 33 -> 2248, 34 -> 2217, 35 -> 2186, 36 -> 2155, 37 -> 2124, 38 -> 2093, 39 -> 2062, 40 -> 2031, 41 -> 2000, 42 -> 1969, 43 -> 1938, 44 -> 1865, 45 -> 1834, 46 -> 1803, 47 -> 1772, 48 -> 1741, 49 -> 1710, 50 -> 1682, 51 -> 1635, 52 -> 1588, 53 -> 1541, 54 -> 1494, 55 -> 1447, 56 -> 1400, 57 -> 1353, 58 -> 1306, 59 -> 1269, 60 -> 1123, 61 -> 1079, 62 -> 1032, 63 -> 902, 64 -> 855, 65 -> 808, 66 -> 761, 67 -> 714, 68 -> 662
    //   636: aload #6
    //   638: astore #12
    //   640: aload #6
    //   642: ifnonnull -> 3778
    //   645: aload #6
    //   647: astore #11
    //   649: aload #6
    //   651: astore #8
    //   653: aload_1
    //   654: invokevirtual newBuilder : ()Ljava/lang/Object;
    //   657: astore #12
    //   659: goto -> 3778
    //   662: aload #6
    //   664: astore #11
    //   666: aload #6
    //   668: astore #8
    //   670: aload_3
    //   671: iload #14
    //   673: invokestatic offset : (I)J
    //   676: aload #4
    //   678: aload_0
    //   679: iload #10
    //   681: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   684: aload #5
    //   686: invokeinterface readGroupBySchemaWithCheck : (Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   691: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   694: aload #6
    //   696: astore #11
    //   698: aload #6
    //   700: astore #8
    //   702: aload_0
    //   703: aload_3
    //   704: iload #9
    //   706: iload #10
    //   708: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   711: goto -> 3771
    //   714: aload #6
    //   716: astore #11
    //   718: aload #6
    //   720: astore #8
    //   722: aload_3
    //   723: iload #14
    //   725: invokestatic offset : (I)J
    //   728: aload #4
    //   730: invokeinterface readSInt64 : ()J
    //   735: invokestatic valueOf : (J)Ljava/lang/Long;
    //   738: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   741: aload #6
    //   743: astore #11
    //   745: aload #6
    //   747: astore #8
    //   749: aload_0
    //   750: aload_3
    //   751: iload #9
    //   753: iload #10
    //   755: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   758: goto -> 3771
    //   761: aload #6
    //   763: astore #11
    //   765: aload #6
    //   767: astore #8
    //   769: aload_3
    //   770: iload #14
    //   772: invokestatic offset : (I)J
    //   775: aload #4
    //   777: invokeinterface readSInt32 : ()I
    //   782: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   785: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   788: aload #6
    //   790: astore #11
    //   792: aload #6
    //   794: astore #8
    //   796: aload_0
    //   797: aload_3
    //   798: iload #9
    //   800: iload #10
    //   802: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   805: goto -> 3771
    //   808: aload #6
    //   810: astore #11
    //   812: aload #6
    //   814: astore #8
    //   816: aload_3
    //   817: iload #14
    //   819: invokestatic offset : (I)J
    //   822: aload #4
    //   824: invokeinterface readSFixed64 : ()J
    //   829: invokestatic valueOf : (J)Ljava/lang/Long;
    //   832: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   835: aload #6
    //   837: astore #11
    //   839: aload #6
    //   841: astore #8
    //   843: aload_0
    //   844: aload_3
    //   845: iload #9
    //   847: iload #10
    //   849: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   852: goto -> 3771
    //   855: aload #6
    //   857: astore #11
    //   859: aload #6
    //   861: astore #8
    //   863: aload_3
    //   864: iload #14
    //   866: invokestatic offset : (I)J
    //   869: aload #4
    //   871: invokeinterface readSFixed32 : ()I
    //   876: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   879: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   882: aload #6
    //   884: astore #11
    //   886: aload #6
    //   888: astore #8
    //   890: aload_0
    //   891: aload_3
    //   892: iload #9
    //   894: iload #10
    //   896: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   899: goto -> 3771
    //   902: aload #6
    //   904: astore #11
    //   906: aload #6
    //   908: astore #8
    //   910: aload #4
    //   912: invokeinterface readEnum : ()I
    //   917: istore #15
    //   919: aload #6
    //   921: astore #11
    //   923: aload #6
    //   925: astore #8
    //   927: aload_0
    //   928: iload #10
    //   930: invokespecial getEnumFieldVerifier : (I)Lcom/google/protobuf/Internal$EnumVerifier;
    //   933: astore #12
    //   935: aload #12
    //   937: ifnull -> 990
    //   940: aload #6
    //   942: astore #11
    //   944: aload #6
    //   946: astore #8
    //   948: aload #12
    //   950: iload #15
    //   952: invokeinterface isInRange : (I)Z
    //   957: ifeq -> 963
    //   960: goto -> 990
    //   963: aload #6
    //   965: astore #11
    //   967: aload #6
    //   969: astore #8
    //   971: iload #9
    //   973: iload #15
    //   975: aload #6
    //   977: aload_1
    //   978: invokestatic storeUnknownEnum : (IILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   981: astore #6
    //   983: aload #7
    //   985: astore #12
    //   987: goto -> 177
    //   990: aload #6
    //   992: astore #11
    //   994: aload #6
    //   996: astore #8
    //   998: aload_3
    //   999: iload #14
    //   1001: invokestatic offset : (I)J
    //   1004: iload #15
    //   1006: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1009: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1012: aload #6
    //   1014: astore #11
    //   1016: aload #6
    //   1018: astore #8
    //   1020: aload_0
    //   1021: aload_3
    //   1022: iload #9
    //   1024: iload #10
    //   1026: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1029: goto -> 3771
    //   1032: aload #6
    //   1034: astore #11
    //   1036: aload #6
    //   1038: astore #8
    //   1040: aload_3
    //   1041: iload #14
    //   1043: invokestatic offset : (I)J
    //   1046: aload #4
    //   1048: invokeinterface readUInt32 : ()I
    //   1053: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1056: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1059: aload #6
    //   1061: astore #11
    //   1063: aload #6
    //   1065: astore #8
    //   1067: aload_0
    //   1068: aload_3
    //   1069: iload #9
    //   1071: iload #10
    //   1073: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1076: goto -> 3771
    //   1079: aload #6
    //   1081: astore #11
    //   1083: aload #6
    //   1085: astore #8
    //   1087: aload_3
    //   1088: iload #14
    //   1090: invokestatic offset : (I)J
    //   1093: aload #4
    //   1095: invokeinterface readBytes : ()Lcom/google/protobuf/ByteString;
    //   1100: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1103: aload #6
    //   1105: astore #11
    //   1107: aload #6
    //   1109: astore #8
    //   1111: aload_0
    //   1112: aload_3
    //   1113: iload #9
    //   1115: iload #10
    //   1117: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1120: goto -> 3771
    //   1123: aload #6
    //   1125: astore #11
    //   1127: aload #6
    //   1129: astore #8
    //   1131: aload_0
    //   1132: aload_3
    //   1133: iload #9
    //   1135: iload #10
    //   1137: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   1140: ifeq -> 1202
    //   1143: aload #6
    //   1145: astore #11
    //   1147: aload #6
    //   1149: astore #8
    //   1151: aload_3
    //   1152: iload #14
    //   1154: invokestatic offset : (I)J
    //   1157: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1160: aload #4
    //   1162: aload_0
    //   1163: iload #10
    //   1165: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1168: aload #5
    //   1170: invokeinterface readMessageBySchemaWithCheck : (Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   1175: invokestatic mergeMessage : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1178: astore #12
    //   1180: aload #6
    //   1182: astore #11
    //   1184: aload #6
    //   1186: astore #8
    //   1188: aload_3
    //   1189: iload #14
    //   1191: invokestatic offset : (I)J
    //   1194: aload #12
    //   1196: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1199: goto -> 1249
    //   1202: aload #6
    //   1204: astore #11
    //   1206: aload #6
    //   1208: astore #8
    //   1210: aload_3
    //   1211: iload #14
    //   1213: invokestatic offset : (I)J
    //   1216: aload #4
    //   1218: aload_0
    //   1219: iload #10
    //   1221: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1224: aload #5
    //   1226: invokeinterface readMessageBySchemaWithCheck : (Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   1231: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1234: aload #6
    //   1236: astore #11
    //   1238: aload #6
    //   1240: astore #8
    //   1242: aload_0
    //   1243: aload_3
    //   1244: iload #10
    //   1246: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   1249: aload #6
    //   1251: astore #11
    //   1253: aload #6
    //   1255: astore #8
    //   1257: aload_0
    //   1258: aload_3
    //   1259: iload #9
    //   1261: iload #10
    //   1263: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1266: goto -> 3771
    //   1269: aload #6
    //   1271: astore #11
    //   1273: aload #6
    //   1275: astore #8
    //   1277: aload_0
    //   1278: aload_3
    //   1279: iload #14
    //   1281: aload #4
    //   1283: invokespecial readString : (Ljava/lang/Object;ILcom/google/protobuf/Reader;)V
    //   1286: aload #6
    //   1288: astore #11
    //   1290: aload #6
    //   1292: astore #8
    //   1294: aload_0
    //   1295: aload_3
    //   1296: iload #9
    //   1298: iload #10
    //   1300: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1303: goto -> 3771
    //   1306: aload #6
    //   1308: astore #11
    //   1310: aload #6
    //   1312: astore #8
    //   1314: aload_3
    //   1315: iload #14
    //   1317: invokestatic offset : (I)J
    //   1320: aload #4
    //   1322: invokeinterface readBool : ()Z
    //   1327: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1330: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1333: aload #6
    //   1335: astore #11
    //   1337: aload #6
    //   1339: astore #8
    //   1341: aload_0
    //   1342: aload_3
    //   1343: iload #9
    //   1345: iload #10
    //   1347: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1350: goto -> 3771
    //   1353: aload #6
    //   1355: astore #11
    //   1357: aload #6
    //   1359: astore #8
    //   1361: aload_3
    //   1362: iload #14
    //   1364: invokestatic offset : (I)J
    //   1367: aload #4
    //   1369: invokeinterface readFixed32 : ()I
    //   1374: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1377: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1380: aload #6
    //   1382: astore #11
    //   1384: aload #6
    //   1386: astore #8
    //   1388: aload_0
    //   1389: aload_3
    //   1390: iload #9
    //   1392: iload #10
    //   1394: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1397: goto -> 3771
    //   1400: aload #6
    //   1402: astore #11
    //   1404: aload #6
    //   1406: astore #8
    //   1408: aload_3
    //   1409: iload #14
    //   1411: invokestatic offset : (I)J
    //   1414: aload #4
    //   1416: invokeinterface readFixed64 : ()J
    //   1421: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1424: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1427: aload #6
    //   1429: astore #11
    //   1431: aload #6
    //   1433: astore #8
    //   1435: aload_0
    //   1436: aload_3
    //   1437: iload #9
    //   1439: iload #10
    //   1441: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1444: goto -> 3771
    //   1447: aload #6
    //   1449: astore #11
    //   1451: aload #6
    //   1453: astore #8
    //   1455: aload_3
    //   1456: iload #14
    //   1458: invokestatic offset : (I)J
    //   1461: aload #4
    //   1463: invokeinterface readInt32 : ()I
    //   1468: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1471: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1474: aload #6
    //   1476: astore #11
    //   1478: aload #6
    //   1480: astore #8
    //   1482: aload_0
    //   1483: aload_3
    //   1484: iload #9
    //   1486: iload #10
    //   1488: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1491: goto -> 3771
    //   1494: aload #6
    //   1496: astore #11
    //   1498: aload #6
    //   1500: astore #8
    //   1502: aload_3
    //   1503: iload #14
    //   1505: invokestatic offset : (I)J
    //   1508: aload #4
    //   1510: invokeinterface readUInt64 : ()J
    //   1515: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1518: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1521: aload #6
    //   1523: astore #11
    //   1525: aload #6
    //   1527: astore #8
    //   1529: aload_0
    //   1530: aload_3
    //   1531: iload #9
    //   1533: iload #10
    //   1535: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1538: goto -> 3771
    //   1541: aload #6
    //   1543: astore #11
    //   1545: aload #6
    //   1547: astore #8
    //   1549: aload_3
    //   1550: iload #14
    //   1552: invokestatic offset : (I)J
    //   1555: aload #4
    //   1557: invokeinterface readInt64 : ()J
    //   1562: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1565: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1568: aload #6
    //   1570: astore #11
    //   1572: aload #6
    //   1574: astore #8
    //   1576: aload_0
    //   1577: aload_3
    //   1578: iload #9
    //   1580: iload #10
    //   1582: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1585: goto -> 3771
    //   1588: aload #6
    //   1590: astore #11
    //   1592: aload #6
    //   1594: astore #8
    //   1596: aload_3
    //   1597: iload #14
    //   1599: invokestatic offset : (I)J
    //   1602: aload #4
    //   1604: invokeinterface readFloat : ()F
    //   1609: invokestatic valueOf : (F)Ljava/lang/Float;
    //   1612: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1615: aload #6
    //   1617: astore #11
    //   1619: aload #6
    //   1621: astore #8
    //   1623: aload_0
    //   1624: aload_3
    //   1625: iload #9
    //   1627: iload #10
    //   1629: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1632: goto -> 3771
    //   1635: aload #6
    //   1637: astore #11
    //   1639: aload #6
    //   1641: astore #8
    //   1643: aload_3
    //   1644: iload #14
    //   1646: invokestatic offset : (I)J
    //   1649: aload #4
    //   1651: invokeinterface readDouble : ()D
    //   1656: invokestatic valueOf : (D)Ljava/lang/Double;
    //   1659: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1662: aload #6
    //   1664: astore #11
    //   1666: aload #6
    //   1668: astore #8
    //   1670: aload_0
    //   1671: aload_3
    //   1672: iload #9
    //   1674: iload #10
    //   1676: invokespecial setOneofPresent : (Ljava/lang/Object;II)V
    //   1679: goto -> 3771
    //   1682: aload #6
    //   1684: astore #11
    //   1686: aload #6
    //   1688: astore #8
    //   1690: aload_0
    //   1691: aload_3
    //   1692: iload #10
    //   1694: aload_0
    //   1695: iload #10
    //   1697: invokespecial getMapFieldDefaultEntry : (I)Ljava/lang/Object;
    //   1700: aload #5
    //   1702: aload #4
    //   1704: invokespecial mergeMap : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/ExtensionRegistryLite;Lcom/google/protobuf/Reader;)V
    //   1707: goto -> 3771
    //   1710: aload #6
    //   1712: astore #11
    //   1714: aload #6
    //   1716: astore #8
    //   1718: aload_0
    //   1719: aload_3
    //   1720: iload #14
    //   1722: invokestatic offset : (I)J
    //   1725: aload #4
    //   1727: aload_0
    //   1728: iload #10
    //   1730: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1733: aload #5
    //   1735: invokespecial readGroupList : (Ljava/lang/Object;JLcom/google/protobuf/Reader;Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)V
    //   1738: goto -> 3771
    //   1741: aload #6
    //   1743: astore #11
    //   1745: aload #6
    //   1747: astore #8
    //   1749: aload #4
    //   1751: aload_0
    //   1752: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   1755: aload_3
    //   1756: iload #14
    //   1758: invokestatic offset : (I)J
    //   1761: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   1764: invokeinterface readSInt64List : (Ljava/util/List;)V
    //   1769: goto -> 3771
    //   1772: aload #6
    //   1774: astore #11
    //   1776: aload #6
    //   1778: astore #8
    //   1780: aload #4
    //   1782: aload_0
    //   1783: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   1786: aload_3
    //   1787: iload #14
    //   1789: invokestatic offset : (I)J
    //   1792: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   1795: invokeinterface readSInt32List : (Ljava/util/List;)V
    //   1800: goto -> 3771
    //   1803: aload #6
    //   1805: astore #11
    //   1807: aload #6
    //   1809: astore #8
    //   1811: aload #4
    //   1813: aload_0
    //   1814: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   1817: aload_3
    //   1818: iload #14
    //   1820: invokestatic offset : (I)J
    //   1823: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   1826: invokeinterface readSFixed64List : (Ljava/util/List;)V
    //   1831: goto -> 3771
    //   1834: aload #6
    //   1836: astore #11
    //   1838: aload #6
    //   1840: astore #8
    //   1842: aload #4
    //   1844: aload_0
    //   1845: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   1848: aload_3
    //   1849: iload #14
    //   1851: invokestatic offset : (I)J
    //   1854: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   1857: invokeinterface readSFixed32List : (Ljava/util/List;)V
    //   1862: goto -> 3771
    //   1865: aload #6
    //   1867: astore #11
    //   1869: aload #6
    //   1871: astore #8
    //   1873: aload_0
    //   1874: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   1877: aload_3
    //   1878: iload #14
    //   1880: invokestatic offset : (I)J
    //   1883: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   1886: astore #12
    //   1888: aload #6
    //   1890: astore #11
    //   1892: aload #6
    //   1894: astore #8
    //   1896: aload #4
    //   1898: aload #12
    //   1900: invokeinterface readEnumList : (Ljava/util/List;)V
    //   1905: aload #6
    //   1907: astore #11
    //   1909: aload #6
    //   1911: astore #8
    //   1913: iload #9
    //   1915: aload #12
    //   1917: aload_0
    //   1918: iload #10
    //   1920: invokespecial getEnumFieldVerifier : (I)Lcom/google/protobuf/Internal$EnumVerifier;
    //   1923: aload #6
    //   1925: aload_1
    //   1926: invokestatic filterUnknownEnumList : (ILjava/util/List;Lcom/google/protobuf/Internal$EnumVerifier;Ljava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   1929: astore #6
    //   1931: aload #7
    //   1933: astore #12
    //   1935: goto -> 177
    //   1938: aload #6
    //   1940: astore #11
    //   1942: aload #6
    //   1944: astore #8
    //   1946: aload #4
    //   1948: aload_0
    //   1949: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   1952: aload_3
    //   1953: iload #14
    //   1955: invokestatic offset : (I)J
    //   1958: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   1961: invokeinterface readUInt32List : (Ljava/util/List;)V
    //   1966: goto -> 3771
    //   1969: aload #6
    //   1971: astore #11
    //   1973: aload #6
    //   1975: astore #8
    //   1977: aload #4
    //   1979: aload_0
    //   1980: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   1983: aload_3
    //   1984: iload #14
    //   1986: invokestatic offset : (I)J
    //   1989: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   1992: invokeinterface readBoolList : (Ljava/util/List;)V
    //   1997: goto -> 3771
    //   2000: aload #6
    //   2002: astore #11
    //   2004: aload #6
    //   2006: astore #8
    //   2008: aload #4
    //   2010: aload_0
    //   2011: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2014: aload_3
    //   2015: iload #14
    //   2017: invokestatic offset : (I)J
    //   2020: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2023: invokeinterface readFixed32List : (Ljava/util/List;)V
    //   2028: goto -> 3771
    //   2031: aload #6
    //   2033: astore #11
    //   2035: aload #6
    //   2037: astore #8
    //   2039: aload #4
    //   2041: aload_0
    //   2042: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2045: aload_3
    //   2046: iload #14
    //   2048: invokestatic offset : (I)J
    //   2051: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2054: invokeinterface readFixed64List : (Ljava/util/List;)V
    //   2059: goto -> 3771
    //   2062: aload #6
    //   2064: astore #11
    //   2066: aload #6
    //   2068: astore #8
    //   2070: aload #4
    //   2072: aload_0
    //   2073: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2076: aload_3
    //   2077: iload #14
    //   2079: invokestatic offset : (I)J
    //   2082: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2085: invokeinterface readInt32List : (Ljava/util/List;)V
    //   2090: goto -> 3771
    //   2093: aload #6
    //   2095: astore #11
    //   2097: aload #6
    //   2099: astore #8
    //   2101: aload #4
    //   2103: aload_0
    //   2104: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2107: aload_3
    //   2108: iload #14
    //   2110: invokestatic offset : (I)J
    //   2113: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2116: invokeinterface readUInt64List : (Ljava/util/List;)V
    //   2121: goto -> 3771
    //   2124: aload #6
    //   2126: astore #11
    //   2128: aload #6
    //   2130: astore #8
    //   2132: aload #4
    //   2134: aload_0
    //   2135: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2138: aload_3
    //   2139: iload #14
    //   2141: invokestatic offset : (I)J
    //   2144: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2147: invokeinterface readInt64List : (Ljava/util/List;)V
    //   2152: goto -> 3771
    //   2155: aload #6
    //   2157: astore #11
    //   2159: aload #6
    //   2161: astore #8
    //   2163: aload #4
    //   2165: aload_0
    //   2166: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2169: aload_3
    //   2170: iload #14
    //   2172: invokestatic offset : (I)J
    //   2175: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2178: invokeinterface readFloatList : (Ljava/util/List;)V
    //   2183: goto -> 3771
    //   2186: aload #6
    //   2188: astore #11
    //   2190: aload #6
    //   2192: astore #8
    //   2194: aload #4
    //   2196: aload_0
    //   2197: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2200: aload_3
    //   2201: iload #14
    //   2203: invokestatic offset : (I)J
    //   2206: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2209: invokeinterface readDoubleList : (Ljava/util/List;)V
    //   2214: goto -> 3771
    //   2217: aload #6
    //   2219: astore #11
    //   2221: aload #6
    //   2223: astore #8
    //   2225: aload #4
    //   2227: aload_0
    //   2228: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2231: aload_3
    //   2232: iload #14
    //   2234: invokestatic offset : (I)J
    //   2237: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2240: invokeinterface readSInt64List : (Ljava/util/List;)V
    //   2245: goto -> 3771
    //   2248: aload #6
    //   2250: astore #11
    //   2252: aload #6
    //   2254: astore #8
    //   2256: aload #4
    //   2258: aload_0
    //   2259: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2262: aload_3
    //   2263: iload #14
    //   2265: invokestatic offset : (I)J
    //   2268: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2271: invokeinterface readSInt32List : (Ljava/util/List;)V
    //   2276: goto -> 3771
    //   2279: aload #6
    //   2281: astore #11
    //   2283: aload #6
    //   2285: astore #8
    //   2287: aload #4
    //   2289: aload_0
    //   2290: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2293: aload_3
    //   2294: iload #14
    //   2296: invokestatic offset : (I)J
    //   2299: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2302: invokeinterface readSFixed64List : (Ljava/util/List;)V
    //   2307: goto -> 3771
    //   2310: aload #6
    //   2312: astore #11
    //   2314: aload #6
    //   2316: astore #8
    //   2318: aload #4
    //   2320: aload_0
    //   2321: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2324: aload_3
    //   2325: iload #14
    //   2327: invokestatic offset : (I)J
    //   2330: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2333: invokeinterface readSFixed32List : (Ljava/util/List;)V
    //   2338: goto -> 3771
    //   2341: aload #6
    //   2343: astore #11
    //   2345: aload #6
    //   2347: astore #8
    //   2349: aload_0
    //   2350: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2353: aload_3
    //   2354: iload #14
    //   2356: invokestatic offset : (I)J
    //   2359: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2362: astore #12
    //   2364: aload #6
    //   2366: astore #11
    //   2368: aload #6
    //   2370: astore #8
    //   2372: aload #4
    //   2374: aload #12
    //   2376: invokeinterface readEnumList : (Ljava/util/List;)V
    //   2381: aload #6
    //   2383: astore #11
    //   2385: aload #6
    //   2387: astore #8
    //   2389: iload #9
    //   2391: aload #12
    //   2393: aload_0
    //   2394: iload #10
    //   2396: invokespecial getEnumFieldVerifier : (I)Lcom/google/protobuf/Internal$EnumVerifier;
    //   2399: aload #6
    //   2401: aload_1
    //   2402: invokestatic filterUnknownEnumList : (ILjava/util/List;Lcom/google/protobuf/Internal$EnumVerifier;Ljava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   2405: astore #6
    //   2407: aload #7
    //   2409: astore #12
    //   2411: goto -> 177
    //   2414: aload #6
    //   2416: astore #11
    //   2418: aload #6
    //   2420: astore #8
    //   2422: aload #4
    //   2424: aload_0
    //   2425: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2428: aload_3
    //   2429: iload #14
    //   2431: invokestatic offset : (I)J
    //   2434: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2437: invokeinterface readUInt32List : (Ljava/util/List;)V
    //   2442: goto -> 3771
    //   2445: aload #6
    //   2447: astore #11
    //   2449: aload #6
    //   2451: astore #8
    //   2453: aload #4
    //   2455: aload_0
    //   2456: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2459: aload_3
    //   2460: iload #14
    //   2462: invokestatic offset : (I)J
    //   2465: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2468: invokeinterface readBytesList : (Ljava/util/List;)V
    //   2473: goto -> 3771
    //   2476: aload #6
    //   2478: astore #11
    //   2480: aload #6
    //   2482: astore #8
    //   2484: aload_0
    //   2485: aload_3
    //   2486: iload #14
    //   2488: aload #4
    //   2490: aload_0
    //   2491: iload #10
    //   2493: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   2496: aload #5
    //   2498: invokespecial readMessageList : (Ljava/lang/Object;ILcom/google/protobuf/Reader;Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)V
    //   2501: goto -> 3771
    //   2504: aload #6
    //   2506: astore #11
    //   2508: aload #6
    //   2510: astore #8
    //   2512: aload_0
    //   2513: aload_3
    //   2514: iload #14
    //   2516: aload #4
    //   2518: invokespecial readStringList : (Ljava/lang/Object;ILcom/google/protobuf/Reader;)V
    //   2521: goto -> 3771
    //   2524: aload #6
    //   2526: astore #11
    //   2528: aload #6
    //   2530: astore #8
    //   2532: aload #4
    //   2534: aload_0
    //   2535: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2538: aload_3
    //   2539: iload #14
    //   2541: invokestatic offset : (I)J
    //   2544: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2547: invokeinterface readBoolList : (Ljava/util/List;)V
    //   2552: goto -> 3771
    //   2555: aload #6
    //   2557: astore #11
    //   2559: aload #6
    //   2561: astore #8
    //   2563: aload #4
    //   2565: aload_0
    //   2566: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2569: aload_3
    //   2570: iload #14
    //   2572: invokestatic offset : (I)J
    //   2575: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2578: invokeinterface readFixed32List : (Ljava/util/List;)V
    //   2583: goto -> 3771
    //   2586: aload #6
    //   2588: astore #11
    //   2590: aload #6
    //   2592: astore #8
    //   2594: aload #4
    //   2596: aload_0
    //   2597: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2600: aload_3
    //   2601: iload #14
    //   2603: invokestatic offset : (I)J
    //   2606: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2609: invokeinterface readFixed64List : (Ljava/util/List;)V
    //   2614: goto -> 3771
    //   2617: aload #6
    //   2619: astore #11
    //   2621: aload #6
    //   2623: astore #8
    //   2625: aload #4
    //   2627: aload_0
    //   2628: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2631: aload_3
    //   2632: iload #14
    //   2634: invokestatic offset : (I)J
    //   2637: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2640: invokeinterface readInt32List : (Ljava/util/List;)V
    //   2645: goto -> 3771
    //   2648: aload #6
    //   2650: astore #11
    //   2652: aload #6
    //   2654: astore #8
    //   2656: aload #4
    //   2658: aload_0
    //   2659: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2662: aload_3
    //   2663: iload #14
    //   2665: invokestatic offset : (I)J
    //   2668: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2671: invokeinterface readUInt64List : (Ljava/util/List;)V
    //   2676: goto -> 3771
    //   2679: aload #6
    //   2681: astore #11
    //   2683: aload #6
    //   2685: astore #8
    //   2687: aload #4
    //   2689: aload_0
    //   2690: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2693: aload_3
    //   2694: iload #14
    //   2696: invokestatic offset : (I)J
    //   2699: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2702: invokeinterface readInt64List : (Ljava/util/List;)V
    //   2707: goto -> 3771
    //   2710: aload #6
    //   2712: astore #11
    //   2714: aload #6
    //   2716: astore #8
    //   2718: aload #4
    //   2720: aload_0
    //   2721: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2724: aload_3
    //   2725: iload #14
    //   2727: invokestatic offset : (I)J
    //   2730: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2733: invokeinterface readFloatList : (Ljava/util/List;)V
    //   2738: goto -> 3771
    //   2741: aload #6
    //   2743: astore #11
    //   2745: aload #6
    //   2747: astore #8
    //   2749: aload #4
    //   2751: aload_0
    //   2752: getfield listFieldSchema : Lcom/google/protobuf/ListFieldSchema;
    //   2755: aload_3
    //   2756: iload #14
    //   2758: invokestatic offset : (I)J
    //   2761: invokevirtual mutableListAt : (Ljava/lang/Object;J)Ljava/util/List;
    //   2764: invokeinterface readDoubleList : (Ljava/util/List;)V
    //   2769: goto -> 3771
    //   2772: aload #6
    //   2774: astore #11
    //   2776: aload #6
    //   2778: astore #8
    //   2780: aload_0
    //   2781: aload_3
    //   2782: iload #10
    //   2784: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2787: ifeq -> 2849
    //   2790: aload #6
    //   2792: astore #11
    //   2794: aload #6
    //   2796: astore #8
    //   2798: aload_3
    //   2799: iload #14
    //   2801: invokestatic offset : (I)J
    //   2804: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2807: aload #4
    //   2809: aload_0
    //   2810: iload #10
    //   2812: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   2815: aload #5
    //   2817: invokeinterface readGroupBySchemaWithCheck : (Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   2822: invokestatic mergeMessage : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2825: astore #12
    //   2827: aload #6
    //   2829: astore #11
    //   2831: aload #6
    //   2833: astore #8
    //   2835: aload_3
    //   2836: iload #14
    //   2838: invokestatic offset : (I)J
    //   2841: aload #12
    //   2843: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   2846: goto -> 3771
    //   2849: aload #6
    //   2851: astore #11
    //   2853: aload #6
    //   2855: astore #8
    //   2857: aload_3
    //   2858: iload #14
    //   2860: invokestatic offset : (I)J
    //   2863: aload #4
    //   2865: aload_0
    //   2866: iload #10
    //   2868: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   2871: aload #5
    //   2873: invokeinterface readGroupBySchemaWithCheck : (Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   2878: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   2881: aload #6
    //   2883: astore #11
    //   2885: aload #6
    //   2887: astore #8
    //   2889: aload_0
    //   2890: aload_3
    //   2891: iload #10
    //   2893: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   2896: goto -> 3771
    //   2899: aload #6
    //   2901: astore #11
    //   2903: aload #6
    //   2905: astore #8
    //   2907: aload_3
    //   2908: iload #14
    //   2910: invokestatic offset : (I)J
    //   2913: aload #4
    //   2915: invokeinterface readSInt64 : ()J
    //   2920: invokestatic putLong : (Ljava/lang/Object;JJ)V
    //   2923: aload #6
    //   2925: astore #11
    //   2927: aload #6
    //   2929: astore #8
    //   2931: aload_0
    //   2932: aload_3
    //   2933: iload #10
    //   2935: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   2938: goto -> 3771
    //   2941: aload #6
    //   2943: astore #11
    //   2945: aload #6
    //   2947: astore #8
    //   2949: aload_3
    //   2950: iload #14
    //   2952: invokestatic offset : (I)J
    //   2955: aload #4
    //   2957: invokeinterface readSInt32 : ()I
    //   2962: invokestatic putInt : (Ljava/lang/Object;JI)V
    //   2965: aload #6
    //   2967: astore #11
    //   2969: aload #6
    //   2971: astore #8
    //   2973: aload_0
    //   2974: aload_3
    //   2975: iload #10
    //   2977: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   2980: goto -> 3771
    //   2983: aload #6
    //   2985: astore #11
    //   2987: aload #6
    //   2989: astore #8
    //   2991: aload_3
    //   2992: iload #14
    //   2994: invokestatic offset : (I)J
    //   2997: aload #4
    //   2999: invokeinterface readSFixed64 : ()J
    //   3004: invokestatic putLong : (Ljava/lang/Object;JJ)V
    //   3007: aload #6
    //   3009: astore #11
    //   3011: aload #6
    //   3013: astore #8
    //   3015: aload_0
    //   3016: aload_3
    //   3017: iload #10
    //   3019: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3022: goto -> 3771
    //   3025: aload #6
    //   3027: astore #11
    //   3029: aload #6
    //   3031: astore #8
    //   3033: aload_3
    //   3034: iload #14
    //   3036: invokestatic offset : (I)J
    //   3039: aload #4
    //   3041: invokeinterface readSFixed32 : ()I
    //   3046: invokestatic putInt : (Ljava/lang/Object;JI)V
    //   3049: aload #6
    //   3051: astore #11
    //   3053: aload #6
    //   3055: astore #8
    //   3057: aload_0
    //   3058: aload_3
    //   3059: iload #10
    //   3061: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3064: goto -> 3771
    //   3067: aload #6
    //   3069: astore #11
    //   3071: aload #6
    //   3073: astore #8
    //   3075: aload #4
    //   3077: invokeinterface readEnum : ()I
    //   3082: istore #15
    //   3084: aload #6
    //   3086: astore #11
    //   3088: aload #6
    //   3090: astore #8
    //   3092: aload_0
    //   3093: iload #10
    //   3095: invokespecial getEnumFieldVerifier : (I)Lcom/google/protobuf/Internal$EnumVerifier;
    //   3098: astore #12
    //   3100: aload #12
    //   3102: ifnull -> 3155
    //   3105: aload #6
    //   3107: astore #11
    //   3109: aload #6
    //   3111: astore #8
    //   3113: aload #12
    //   3115: iload #15
    //   3117: invokeinterface isInRange : (I)Z
    //   3122: ifeq -> 3128
    //   3125: goto -> 3155
    //   3128: aload #6
    //   3130: astore #11
    //   3132: aload #6
    //   3134: astore #8
    //   3136: iload #9
    //   3138: iload #15
    //   3140: aload #6
    //   3142: aload_1
    //   3143: invokestatic storeUnknownEnum : (IILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   3146: astore #6
    //   3148: aload #7
    //   3150: astore #12
    //   3152: goto -> 177
    //   3155: aload #6
    //   3157: astore #11
    //   3159: aload #6
    //   3161: astore #8
    //   3163: aload_3
    //   3164: iload #14
    //   3166: invokestatic offset : (I)J
    //   3169: iload #15
    //   3171: invokestatic putInt : (Ljava/lang/Object;JI)V
    //   3174: aload #6
    //   3176: astore #11
    //   3178: aload #6
    //   3180: astore #8
    //   3182: aload_0
    //   3183: aload_3
    //   3184: iload #10
    //   3186: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3189: goto -> 3771
    //   3192: aload #6
    //   3194: astore #11
    //   3196: aload #6
    //   3198: astore #8
    //   3200: aload_3
    //   3201: iload #14
    //   3203: invokestatic offset : (I)J
    //   3206: aload #4
    //   3208: invokeinterface readUInt32 : ()I
    //   3213: invokestatic putInt : (Ljava/lang/Object;JI)V
    //   3216: aload #6
    //   3218: astore #11
    //   3220: aload #6
    //   3222: astore #8
    //   3224: aload_0
    //   3225: aload_3
    //   3226: iload #10
    //   3228: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3231: goto -> 3771
    //   3234: aload #6
    //   3236: astore #11
    //   3238: aload #6
    //   3240: astore #8
    //   3242: aload_3
    //   3243: iload #14
    //   3245: invokestatic offset : (I)J
    //   3248: aload #4
    //   3250: invokeinterface readBytes : ()Lcom/google/protobuf/ByteString;
    //   3255: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   3258: aload #6
    //   3260: astore #11
    //   3262: aload #6
    //   3264: astore #8
    //   3266: aload_0
    //   3267: aload_3
    //   3268: iload #10
    //   3270: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3273: goto -> 3771
    //   3276: aload #6
    //   3278: astore #11
    //   3280: aload #6
    //   3282: astore #8
    //   3284: aload_0
    //   3285: aload_3
    //   3286: iload #10
    //   3288: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   3291: ifeq -> 3353
    //   3294: aload #6
    //   3296: astore #11
    //   3298: aload #6
    //   3300: astore #8
    //   3302: aload_3
    //   3303: iload #14
    //   3305: invokestatic offset : (I)J
    //   3308: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   3311: aload #4
    //   3313: aload_0
    //   3314: iload #10
    //   3316: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   3319: aload #5
    //   3321: invokeinterface readMessageBySchemaWithCheck : (Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   3326: invokestatic mergeMessage : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3329: astore #12
    //   3331: aload #6
    //   3333: astore #11
    //   3335: aload #6
    //   3337: astore #8
    //   3339: aload_3
    //   3340: iload #14
    //   3342: invokestatic offset : (I)J
    //   3345: aload #12
    //   3347: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   3350: goto -> 3771
    //   3353: aload #6
    //   3355: astore #11
    //   3357: aload #6
    //   3359: astore #8
    //   3361: aload_3
    //   3362: iload #14
    //   3364: invokestatic offset : (I)J
    //   3367: aload #4
    //   3369: aload_0
    //   3370: iload #10
    //   3372: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   3375: aload #5
    //   3377: invokeinterface readMessageBySchemaWithCheck : (Lcom/google/protobuf/Schema;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   3382: invokestatic putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   3385: aload #6
    //   3387: astore #11
    //   3389: aload #6
    //   3391: astore #8
    //   3393: aload_0
    //   3394: aload_3
    //   3395: iload #10
    //   3397: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3400: goto -> 3771
    //   3403: aload #6
    //   3405: astore #11
    //   3407: aload #6
    //   3409: astore #8
    //   3411: aload_0
    //   3412: aload_3
    //   3413: iload #14
    //   3415: aload #4
    //   3417: invokespecial readString : (Ljava/lang/Object;ILcom/google/protobuf/Reader;)V
    //   3420: aload #6
    //   3422: astore #11
    //   3424: aload #6
    //   3426: astore #8
    //   3428: aload_0
    //   3429: aload_3
    //   3430: iload #10
    //   3432: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3435: goto -> 3771
    //   3438: aload #6
    //   3440: astore #11
    //   3442: aload #6
    //   3444: astore #8
    //   3446: aload_3
    //   3447: iload #14
    //   3449: invokestatic offset : (I)J
    //   3452: aload #4
    //   3454: invokeinterface readBool : ()Z
    //   3459: invokestatic putBoolean : (Ljava/lang/Object;JZ)V
    //   3462: aload #6
    //   3464: astore #11
    //   3466: aload #6
    //   3468: astore #8
    //   3470: aload_0
    //   3471: aload_3
    //   3472: iload #10
    //   3474: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3477: goto -> 3771
    //   3480: aload #6
    //   3482: astore #11
    //   3484: aload #6
    //   3486: astore #8
    //   3488: aload_3
    //   3489: iload #14
    //   3491: invokestatic offset : (I)J
    //   3494: aload #4
    //   3496: invokeinterface readFixed32 : ()I
    //   3501: invokestatic putInt : (Ljava/lang/Object;JI)V
    //   3504: aload #6
    //   3506: astore #11
    //   3508: aload #6
    //   3510: astore #8
    //   3512: aload_0
    //   3513: aload_3
    //   3514: iload #10
    //   3516: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3519: goto -> 3771
    //   3522: aload #6
    //   3524: astore #11
    //   3526: aload #6
    //   3528: astore #8
    //   3530: aload_3
    //   3531: iload #14
    //   3533: invokestatic offset : (I)J
    //   3536: aload #4
    //   3538: invokeinterface readFixed64 : ()J
    //   3543: invokestatic putLong : (Ljava/lang/Object;JJ)V
    //   3546: aload #6
    //   3548: astore #11
    //   3550: aload #6
    //   3552: astore #8
    //   3554: aload_0
    //   3555: aload_3
    //   3556: iload #10
    //   3558: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3561: goto -> 3771
    //   3564: aload #6
    //   3566: astore #11
    //   3568: aload #6
    //   3570: astore #8
    //   3572: aload_3
    //   3573: iload #14
    //   3575: invokestatic offset : (I)J
    //   3578: aload #4
    //   3580: invokeinterface readInt32 : ()I
    //   3585: invokestatic putInt : (Ljava/lang/Object;JI)V
    //   3588: aload #6
    //   3590: astore #11
    //   3592: aload #6
    //   3594: astore #8
    //   3596: aload_0
    //   3597: aload_3
    //   3598: iload #10
    //   3600: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3603: goto -> 3771
    //   3606: aload #6
    //   3608: astore #11
    //   3610: aload #6
    //   3612: astore #8
    //   3614: aload_3
    //   3615: iload #14
    //   3617: invokestatic offset : (I)J
    //   3620: aload #4
    //   3622: invokeinterface readUInt64 : ()J
    //   3627: invokestatic putLong : (Ljava/lang/Object;JJ)V
    //   3630: aload #6
    //   3632: astore #11
    //   3634: aload #6
    //   3636: astore #8
    //   3638: aload_0
    //   3639: aload_3
    //   3640: iload #10
    //   3642: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3645: goto -> 3771
    //   3648: aload #6
    //   3650: astore #11
    //   3652: aload #6
    //   3654: astore #8
    //   3656: aload_3
    //   3657: iload #14
    //   3659: invokestatic offset : (I)J
    //   3662: aload #4
    //   3664: invokeinterface readInt64 : ()J
    //   3669: invokestatic putLong : (Ljava/lang/Object;JJ)V
    //   3672: aload #6
    //   3674: astore #11
    //   3676: aload #6
    //   3678: astore #8
    //   3680: aload_0
    //   3681: aload_3
    //   3682: iload #10
    //   3684: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3687: goto -> 3771
    //   3690: aload #6
    //   3692: astore #11
    //   3694: aload #6
    //   3696: astore #8
    //   3698: aload_3
    //   3699: iload #14
    //   3701: invokestatic offset : (I)J
    //   3704: aload #4
    //   3706: invokeinterface readFloat : ()F
    //   3711: invokestatic putFloat : (Ljava/lang/Object;JF)V
    //   3714: aload #6
    //   3716: astore #11
    //   3718: aload #6
    //   3720: astore #8
    //   3722: aload_0
    //   3723: aload_3
    //   3724: iload #10
    //   3726: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3729: goto -> 3771
    //   3732: aload #6
    //   3734: astore #11
    //   3736: aload #6
    //   3738: astore #8
    //   3740: aload_3
    //   3741: iload #14
    //   3743: invokestatic offset : (I)J
    //   3746: aload #4
    //   3748: invokeinterface readDouble : ()D
    //   3753: invokestatic putDouble : (Ljava/lang/Object;JD)V
    //   3756: aload #6
    //   3758: astore #11
    //   3760: aload #6
    //   3762: astore #8
    //   3764: aload_0
    //   3765: aload_3
    //   3766: iload #10
    //   3768: invokespecial setFieldPresent : (Ljava/lang/Object;I)V
    //   3771: aload #7
    //   3773: astore #12
    //   3775: goto -> 177
    //   3778: aload #12
    //   3780: astore #11
    //   3782: aload #12
    //   3784: astore #8
    //   3786: aload_1
    //   3787: aload #12
    //   3789: aload #4
    //   3791: invokevirtual mergeOneFieldFrom : (Ljava/lang/Object;Lcom/google/protobuf/Reader;)Z
    //   3794: istore #13
    //   3796: aload #12
    //   3798: astore #6
    //   3800: iload #13
    //   3802: ifne -> 3771
    //   3805: aload_0
    //   3806: getfield checkInitializedCount : I
    //   3809: istore #10
    //   3811: iload #10
    //   3813: aload_0
    //   3814: getfield repeatedFieldOffsetStart : I
    //   3817: if_icmpge -> 3843
    //   3820: aload_0
    //   3821: aload_3
    //   3822: aload_0
    //   3823: getfield intArray : [I
    //   3826: iload #10
    //   3828: iaload
    //   3829: aload #12
    //   3831: aload_1
    //   3832: invokespecial filterMapUnknownEnumValues : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   3835: astore #12
    //   3837: iinc #10, 1
    //   3840: goto -> 3811
    //   3843: aload #12
    //   3845: ifnull -> 3855
    //   3848: aload_1
    //   3849: aload_3
    //   3850: aload #12
    //   3852: invokevirtual setBuilderToMessage : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   3855: return
    //   3856: astore #6
    //   3858: aload #11
    //   3860: astore #8
    //   3862: aload_1
    //   3863: aload #4
    //   3865: invokevirtual shouldDiscardUnknownFields : (Lcom/google/protobuf/Reader;)Z
    //   3868: ifeq -> 3944
    //   3871: aload #11
    //   3873: astore #8
    //   3875: aload #4
    //   3877: invokeinterface skipField : ()Z
    //   3882: istore #13
    //   3884: aload #11
    //   3886: astore #6
    //   3888: iload #13
    //   3890: ifne -> 7
    //   3893: aload_0
    //   3894: getfield checkInitializedCount : I
    //   3897: istore #10
    //   3899: iload #10
    //   3901: aload_0
    //   3902: getfield repeatedFieldOffsetStart : I
    //   3905: if_icmpge -> 3931
    //   3908: aload_0
    //   3909: aload_3
    //   3910: aload_0
    //   3911: getfield intArray : [I
    //   3914: iload #10
    //   3916: iaload
    //   3917: aload #11
    //   3919: aload_1
    //   3920: invokespecial filterMapUnknownEnumValues : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   3923: astore #11
    //   3925: iinc #10, 1
    //   3928: goto -> 3899
    //   3931: aload #11
    //   3933: ifnull -> 3943
    //   3936: aload_1
    //   3937: aload_3
    //   3938: aload #11
    //   3940: invokevirtual setBuilderToMessage : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   3943: return
    //   3944: aload #11
    //   3946: astore #12
    //   3948: aload #11
    //   3950: ifnonnull -> 3964
    //   3953: aload #11
    //   3955: astore #8
    //   3957: aload_1
    //   3958: aload_3
    //   3959: invokevirtual getBuilderFromMessage : (Ljava/lang/Object;)Ljava/lang/Object;
    //   3962: astore #12
    //   3964: aload #12
    //   3966: astore #8
    //   3968: aload_1
    //   3969: aload #12
    //   3971: aload #4
    //   3973: invokevirtual mergeOneFieldFrom : (Ljava/lang/Object;Lcom/google/protobuf/Reader;)Z
    //   3976: istore #13
    //   3978: aload #12
    //   3980: astore #6
    //   3982: iload #13
    //   3984: ifne -> 7
    //   3987: aload_0
    //   3988: getfield checkInitializedCount : I
    //   3991: istore #10
    //   3993: iload #10
    //   3995: aload_0
    //   3996: getfield repeatedFieldOffsetStart : I
    //   3999: if_icmpge -> 4025
    //   4002: aload_0
    //   4003: aload_3
    //   4004: aload_0
    //   4005: getfield intArray : [I
    //   4008: iload #10
    //   4010: iaload
    //   4011: aload #12
    //   4013: aload_1
    //   4014: invokespecial filterMapUnknownEnumValues : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   4017: astore #12
    //   4019: iinc #10, 1
    //   4022: goto -> 3993
    //   4025: aload #12
    //   4027: ifnull -> 4037
    //   4030: aload_1
    //   4031: aload_3
    //   4032: aload #12
    //   4034: invokevirtual setBuilderToMessage : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   4037: return
    //   4038: astore_2
    //   4039: aload_0
    //   4040: getfield checkInitializedCount : I
    //   4043: istore #10
    //   4045: iload #10
    //   4047: aload_0
    //   4048: getfield repeatedFieldOffsetStart : I
    //   4051: if_icmpge -> 4077
    //   4054: aload_0
    //   4055: aload_3
    //   4056: aload_0
    //   4057: getfield intArray : [I
    //   4060: iload #10
    //   4062: iaload
    //   4063: aload #8
    //   4065: aload_1
    //   4066: invokespecial filterMapUnknownEnumValues : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   4069: astore #8
    //   4071: iinc #10, 1
    //   4074: goto -> 4045
    //   4077: aload #8
    //   4079: ifnull -> 4089
    //   4082: aload_1
    //   4083: aload_3
    //   4084: aload #8
    //   4086: invokevirtual setBuilderToMessage : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   4089: aload_2
    //   4090: athrow
    // Exception table:
    //   from	to	target	type
    //   11	20	4038	finally
    //   24	32	4038	finally
    //   100	107	4038	finally
    //   117	131	4038	finally
    //   149	156	4038	finally
    //   160	177	4038	finally
    //   188	197	4038	finally
    //   205	215	4038	finally
    //   231	238	4038	finally
    //   242	252	4038	finally
    //   323	331	4038	finally
    //   339	636	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   339	636	4038	finally
    //   653	659	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   653	659	4038	finally
    //   670	694	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   670	694	4038	finally
    //   702	711	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   702	711	4038	finally
    //   722	741	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   722	741	4038	finally
    //   749	758	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   749	758	4038	finally
    //   769	788	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   769	788	4038	finally
    //   796	805	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   796	805	4038	finally
    //   816	835	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   816	835	4038	finally
    //   843	852	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   843	852	4038	finally
    //   863	882	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   863	882	4038	finally
    //   890	899	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   890	899	4038	finally
    //   910	919	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   910	919	4038	finally
    //   927	935	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   927	935	4038	finally
    //   948	960	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   948	960	4038	finally
    //   971	983	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   971	983	4038	finally
    //   998	1012	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   998	1012	4038	finally
    //   1020	1029	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1020	1029	4038	finally
    //   1040	1059	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1040	1059	4038	finally
    //   1067	1076	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1067	1076	4038	finally
    //   1087	1103	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1087	1103	4038	finally
    //   1111	1120	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1111	1120	4038	finally
    //   1131	1143	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1131	1143	4038	finally
    //   1151	1180	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1151	1180	4038	finally
    //   1188	1199	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1188	1199	4038	finally
    //   1210	1234	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1210	1234	4038	finally
    //   1242	1249	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1242	1249	4038	finally
    //   1257	1266	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1257	1266	4038	finally
    //   1277	1286	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1277	1286	4038	finally
    //   1294	1303	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1294	1303	4038	finally
    //   1314	1333	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1314	1333	4038	finally
    //   1341	1350	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1341	1350	4038	finally
    //   1361	1380	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1361	1380	4038	finally
    //   1388	1397	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1388	1397	4038	finally
    //   1408	1427	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1408	1427	4038	finally
    //   1435	1444	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1435	1444	4038	finally
    //   1455	1474	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1455	1474	4038	finally
    //   1482	1491	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1482	1491	4038	finally
    //   1502	1521	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1502	1521	4038	finally
    //   1529	1538	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1529	1538	4038	finally
    //   1549	1568	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1549	1568	4038	finally
    //   1576	1585	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1576	1585	4038	finally
    //   1596	1615	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1596	1615	4038	finally
    //   1623	1632	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1623	1632	4038	finally
    //   1643	1662	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1643	1662	4038	finally
    //   1670	1679	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1670	1679	4038	finally
    //   1690	1707	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1690	1707	4038	finally
    //   1718	1738	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1718	1738	4038	finally
    //   1749	1769	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1749	1769	4038	finally
    //   1780	1800	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1780	1800	4038	finally
    //   1811	1831	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1811	1831	4038	finally
    //   1842	1862	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1842	1862	4038	finally
    //   1873	1888	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1873	1888	4038	finally
    //   1896	1905	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1896	1905	4038	finally
    //   1913	1931	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1913	1931	4038	finally
    //   1946	1966	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1946	1966	4038	finally
    //   1977	1997	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   1977	1997	4038	finally
    //   2008	2028	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2008	2028	4038	finally
    //   2039	2059	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2039	2059	4038	finally
    //   2070	2090	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2070	2090	4038	finally
    //   2101	2121	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2101	2121	4038	finally
    //   2132	2152	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2132	2152	4038	finally
    //   2163	2183	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2163	2183	4038	finally
    //   2194	2214	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2194	2214	4038	finally
    //   2225	2245	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2225	2245	4038	finally
    //   2256	2276	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2256	2276	4038	finally
    //   2287	2307	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2287	2307	4038	finally
    //   2318	2338	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2318	2338	4038	finally
    //   2349	2364	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2349	2364	4038	finally
    //   2372	2381	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2372	2381	4038	finally
    //   2389	2407	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2389	2407	4038	finally
    //   2422	2442	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2422	2442	4038	finally
    //   2453	2473	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2453	2473	4038	finally
    //   2484	2501	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2484	2501	4038	finally
    //   2512	2521	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2512	2521	4038	finally
    //   2532	2552	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2532	2552	4038	finally
    //   2563	2583	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2563	2583	4038	finally
    //   2594	2614	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2594	2614	4038	finally
    //   2625	2645	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2625	2645	4038	finally
    //   2656	2676	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2656	2676	4038	finally
    //   2687	2707	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2687	2707	4038	finally
    //   2718	2738	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2718	2738	4038	finally
    //   2749	2769	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2749	2769	4038	finally
    //   2780	2790	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2780	2790	4038	finally
    //   2798	2827	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2798	2827	4038	finally
    //   2835	2846	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2835	2846	4038	finally
    //   2857	2881	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2857	2881	4038	finally
    //   2889	2896	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2889	2896	4038	finally
    //   2907	2923	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2907	2923	4038	finally
    //   2931	2938	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2931	2938	4038	finally
    //   2949	2965	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2949	2965	4038	finally
    //   2973	2980	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2973	2980	4038	finally
    //   2991	3007	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   2991	3007	4038	finally
    //   3015	3022	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3015	3022	4038	finally
    //   3033	3049	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3033	3049	4038	finally
    //   3057	3064	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3057	3064	4038	finally
    //   3075	3084	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3075	3084	4038	finally
    //   3092	3100	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3092	3100	4038	finally
    //   3113	3125	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3113	3125	4038	finally
    //   3136	3148	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3136	3148	4038	finally
    //   3163	3174	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3163	3174	4038	finally
    //   3182	3189	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3182	3189	4038	finally
    //   3200	3216	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3200	3216	4038	finally
    //   3224	3231	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3224	3231	4038	finally
    //   3242	3258	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3242	3258	4038	finally
    //   3266	3273	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3266	3273	4038	finally
    //   3284	3294	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3284	3294	4038	finally
    //   3302	3331	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3302	3331	4038	finally
    //   3339	3350	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3339	3350	4038	finally
    //   3361	3385	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3361	3385	4038	finally
    //   3393	3400	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3393	3400	4038	finally
    //   3411	3420	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3411	3420	4038	finally
    //   3428	3435	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3428	3435	4038	finally
    //   3446	3462	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3446	3462	4038	finally
    //   3470	3477	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3470	3477	4038	finally
    //   3488	3504	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3488	3504	4038	finally
    //   3512	3519	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3512	3519	4038	finally
    //   3530	3546	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3530	3546	4038	finally
    //   3554	3561	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3554	3561	4038	finally
    //   3572	3588	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3572	3588	4038	finally
    //   3596	3603	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3596	3603	4038	finally
    //   3614	3630	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3614	3630	4038	finally
    //   3638	3645	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3638	3645	4038	finally
    //   3656	3672	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3656	3672	4038	finally
    //   3680	3687	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3680	3687	4038	finally
    //   3698	3714	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3698	3714	4038	finally
    //   3722	3729	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3722	3729	4038	finally
    //   3740	3756	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3740	3756	4038	finally
    //   3764	3771	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3764	3771	4038	finally
    //   3786	3796	3856	com/google/protobuf/InvalidProtocolBufferException$InvalidWireTypeException
    //   3786	3796	4038	finally
    //   3862	3871	4038	finally
    //   3875	3884	4038	finally
    //   3957	3964	4038	finally
    //   3968	3978	4038	finally
  }
  
  private final <K, V> void mergeMap(Object paramObject1, int paramInt, Object paramObject2, ExtensionRegistryLite paramExtensionRegistryLite, Reader paramReader) throws IOException {
    Object object2;
    long l = offset(typeAndOffsetAt(paramInt));
    Object object1 = UnsafeUtil.getObject(paramObject1, l);
    if (object1 == null) {
      object2 = this.mapFieldSchema.newMapField(paramObject2);
      UnsafeUtil.putObject(paramObject1, l, object2);
    } else {
      object2 = object1;
      if (this.mapFieldSchema.isImmutable(object1)) {
        object2 = this.mapFieldSchema.newMapField(paramObject2);
        this.mapFieldSchema.mergeFrom(object2, object1);
        UnsafeUtil.putObject(paramObject1, l, object2);
      } 
    } 
    paramReader.readMap(this.mapFieldSchema.forMutableMapData(object2), this.mapFieldSchema.forMapMetadata(paramObject2), paramExtensionRegistryLite);
  }
  
  private void mergeMessage(T paramT1, T paramT2, int paramInt) {
    long l = offset(typeAndOffsetAt(paramInt));
    if (!isFieldPresent(paramT2, paramInt))
      return; 
    Object object = UnsafeUtil.getObject(paramT1, l);
    paramT2 = (T)UnsafeUtil.getObject(paramT2, l);
    if (object != null && paramT2 != null) {
      UnsafeUtil.putObject(paramT1, l, Internal.mergeMessage(object, paramT2));
      setFieldPresent(paramT1, paramInt);
    } else if (paramT2 != null) {
      UnsafeUtil.putObject(paramT1, l, paramT2);
      setFieldPresent(paramT1, paramInt);
    } 
  }
  
  private void mergeOneofMessage(T paramT1, T paramT2, int paramInt) {
    int i = typeAndOffsetAt(paramInt);
    int j = numberAt(paramInt);
    long l = offset(i);
    if (!isOneofPresent(paramT2, j, paramInt))
      return; 
    Object object = UnsafeUtil.getObject(paramT1, l);
    paramT2 = (T)UnsafeUtil.getObject(paramT2, l);
    if (object != null && paramT2 != null) {
      UnsafeUtil.putObject(paramT1, l, Internal.mergeMessage(object, paramT2));
      setOneofPresent(paramT1, j, paramInt);
    } else if (paramT2 != null) {
      UnsafeUtil.putObject(paramT1, l, paramT2);
      setOneofPresent(paramT1, j, paramInt);
    } 
  }
  
  private void mergeSingleField(T paramT1, T paramT2, int paramInt) {
    int i = typeAndOffsetAt(paramInt);
    long l = offset(i);
    int j = numberAt(paramInt);
    switch (type(i)) {
      default:
        return;
      case 68:
        mergeOneofMessage(paramT1, paramT2, paramInt);
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      case 66:
      case 67:
        if (isOneofPresent(paramT2, j, paramInt)) {
          UnsafeUtil.putObject(paramT1, l, UnsafeUtil.getObject(paramT2, l));
          setOneofPresent(paramT1, j, paramInt);
        } 
      case 60:
        mergeOneofMessage(paramT1, paramT2, paramInt);
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
        if (isOneofPresent(paramT2, j, paramInt)) {
          UnsafeUtil.putObject(paramT1, l, UnsafeUtil.getObject(paramT2, l));
          setOneofPresent(paramT1, j, paramInt);
        } 
      case 50:
        SchemaUtil.mergeMap(this.mapFieldSchema, paramT1, paramT2, l);
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
        this.listFieldSchema.mergeListsAt(paramT1, paramT2, l);
      case 17:
        mergeMessage(paramT1, paramT2, paramInt);
      case 16:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putLong(paramT1, l, UnsafeUtil.getLong(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 15:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putInt(paramT1, l, UnsafeUtil.getInt(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 14:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putLong(paramT1, l, UnsafeUtil.getLong(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 13:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putInt(paramT1, l, UnsafeUtil.getInt(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 12:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putInt(paramT1, l, UnsafeUtil.getInt(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 11:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putInt(paramT1, l, UnsafeUtil.getInt(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 10:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putObject(paramT1, l, UnsafeUtil.getObject(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 9:
        mergeMessage(paramT1, paramT2, paramInt);
      case 8:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putObject(paramT1, l, UnsafeUtil.getObject(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 7:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putBoolean(paramT1, l, UnsafeUtil.getBoolean(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 6:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putInt(paramT1, l, UnsafeUtil.getInt(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 5:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putLong(paramT1, l, UnsafeUtil.getLong(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 4:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putInt(paramT1, l, UnsafeUtil.getInt(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 3:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putLong(paramT1, l, UnsafeUtil.getLong(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 2:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putLong(paramT1, l, UnsafeUtil.getLong(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 1:
        if (isFieldPresent(paramT2, paramInt)) {
          UnsafeUtil.putFloat(paramT1, l, UnsafeUtil.getFloat(paramT2, l));
          setFieldPresent(paramT1, paramInt);
        } 
      case 0:
        break;
    } 
    if (isFieldPresent(paramT2, paramInt)) {
      UnsafeUtil.putDouble(paramT1, l, UnsafeUtil.getDouble(paramT2, l));
      setFieldPresent(paramT1, paramInt);
    } 
  }
  
  static <T> MessageSchema<T> newSchema(Class<T> paramClass, MessageInfo paramMessageInfo, NewInstanceSchema paramNewInstanceSchema, ListFieldSchema paramListFieldSchema, UnknownFieldSchema<?, ?> paramUnknownFieldSchema, ExtensionSchema<?> paramExtensionSchema, MapFieldSchema paramMapFieldSchema) {
    return (paramMessageInfo instanceof RawMessageInfo) ? newSchemaForRawMessageInfo((RawMessageInfo)paramMessageInfo, paramNewInstanceSchema, paramListFieldSchema, paramUnknownFieldSchema, paramExtensionSchema, paramMapFieldSchema) : newSchemaForMessageInfo((StructuralMessageInfo)paramMessageInfo, paramNewInstanceSchema, paramListFieldSchema, paramUnknownFieldSchema, paramExtensionSchema, paramMapFieldSchema);
  }
  
  static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo paramStructuralMessageInfo, NewInstanceSchema paramNewInstanceSchema, ListFieldSchema paramListFieldSchema, UnknownFieldSchema<?, ?> paramUnknownFieldSchema, ExtensionSchema<?> paramExtensionSchema, MapFieldSchema paramMapFieldSchema) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getSyntax : ()Lcom/google/protobuf/ProtoSyntax;
    //   4: getstatic com/google/protobuf/ProtoSyntax.PROTO3 : Lcom/google/protobuf/ProtoSyntax;
    //   7: if_acmpne -> 16
    //   10: iconst_1
    //   11: istore #6
    //   13: goto -> 19
    //   16: iconst_0
    //   17: istore #6
    //   19: aload_0
    //   20: invokevirtual getFields : ()[Lcom/google/protobuf/FieldInfo;
    //   23: astore #7
    //   25: aload #7
    //   27: arraylength
    //   28: ifne -> 41
    //   31: iconst_0
    //   32: istore #8
    //   34: iload #8
    //   36: istore #9
    //   38: goto -> 63
    //   41: aload #7
    //   43: iconst_0
    //   44: aaload
    //   45: invokevirtual getFieldNumber : ()I
    //   48: istore #8
    //   50: aload #7
    //   52: aload #7
    //   54: arraylength
    //   55: iconst_1
    //   56: isub
    //   57: aaload
    //   58: invokevirtual getFieldNumber : ()I
    //   61: istore #9
    //   63: aload #7
    //   65: arraylength
    //   66: istore #10
    //   68: iload #10
    //   70: iconst_3
    //   71: imul
    //   72: newarray int
    //   74: astore #11
    //   76: iload #10
    //   78: iconst_2
    //   79: imul
    //   80: anewarray java/lang/Object
    //   83: astore #12
    //   85: aload #7
    //   87: arraylength
    //   88: istore #13
    //   90: iconst_0
    //   91: istore #14
    //   93: iload #14
    //   95: istore #10
    //   97: iload #10
    //   99: istore #15
    //   101: iload #10
    //   103: istore #16
    //   105: iload #14
    //   107: istore #10
    //   109: iload #10
    //   111: iload #13
    //   113: if_icmpge -> 213
    //   116: aload #7
    //   118: iload #10
    //   120: aaload
    //   121: astore #17
    //   123: aload #17
    //   125: invokevirtual getType : ()Lcom/google/protobuf/FieldType;
    //   128: getstatic com/google/protobuf/FieldType.MAP : Lcom/google/protobuf/FieldType;
    //   131: if_acmpne -> 147
    //   134: iload #16
    //   136: iconst_1
    //   137: iadd
    //   138: istore #18
    //   140: iload #15
    //   142: istore #14
    //   144: goto -> 199
    //   147: iload #16
    //   149: istore #18
    //   151: iload #15
    //   153: istore #14
    //   155: aload #17
    //   157: invokevirtual getType : ()Lcom/google/protobuf/FieldType;
    //   160: invokevirtual id : ()I
    //   163: bipush #18
    //   165: if_icmplt -> 199
    //   168: iload #16
    //   170: istore #18
    //   172: iload #15
    //   174: istore #14
    //   176: aload #17
    //   178: invokevirtual getType : ()Lcom/google/protobuf/FieldType;
    //   181: invokevirtual id : ()I
    //   184: bipush #49
    //   186: if_icmpgt -> 199
    //   189: iload #15
    //   191: iconst_1
    //   192: iadd
    //   193: istore #14
    //   195: iload #16
    //   197: istore #18
    //   199: iinc #10, 1
    //   202: iload #18
    //   204: istore #16
    //   206: iload #14
    //   208: istore #15
    //   210: goto -> 109
    //   213: aconst_null
    //   214: astore #17
    //   216: iload #16
    //   218: ifle -> 230
    //   221: iload #16
    //   223: newarray int
    //   225: astore #19
    //   227: goto -> 233
    //   230: aconst_null
    //   231: astore #19
    //   233: iload #15
    //   235: ifle -> 244
    //   238: iload #15
    //   240: newarray int
    //   242: astore #17
    //   244: aload_0
    //   245: invokevirtual getCheckInitialized : ()[I
    //   248: astore #20
    //   250: aload #20
    //   252: astore #21
    //   254: aload #20
    //   256: ifnonnull -> 264
    //   259: getstatic com/google/protobuf/MessageSchema.EMPTY_INT_ARRAY : [I
    //   262: astore #21
    //   264: iconst_0
    //   265: istore #18
    //   267: iload #18
    //   269: istore #15
    //   271: iload #15
    //   273: istore #16
    //   275: iload #16
    //   277: istore #10
    //   279: iload #10
    //   281: istore #14
    //   283: iload #16
    //   285: istore #22
    //   287: iload #15
    //   289: istore #16
    //   291: iload #18
    //   293: aload #7
    //   295: arraylength
    //   296: if_icmpge -> 460
    //   299: aload #7
    //   301: iload #18
    //   303: aaload
    //   304: astore #20
    //   306: aload #20
    //   308: invokevirtual getFieldNumber : ()I
    //   311: istore #15
    //   313: aload #20
    //   315: aload #11
    //   317: iload #16
    //   319: iload #6
    //   321: aload #12
    //   323: invokestatic storeFieldData : (Lcom/google/protobuf/FieldInfo;[IIZ[Ljava/lang/Object;)V
    //   326: iload #22
    //   328: istore #13
    //   330: iload #22
    //   332: aload #21
    //   334: arraylength
    //   335: if_icmpge -> 365
    //   338: iload #22
    //   340: istore #13
    //   342: aload #21
    //   344: iload #22
    //   346: iaload
    //   347: iload #15
    //   349: if_icmpne -> 365
    //   352: aload #21
    //   354: iload #22
    //   356: iload #16
    //   358: iastore
    //   359: iload #22
    //   361: iconst_1
    //   362: iadd
    //   363: istore #13
    //   365: aload #20
    //   367: invokevirtual getType : ()Lcom/google/protobuf/FieldType;
    //   370: getstatic com/google/protobuf/FieldType.MAP : Lcom/google/protobuf/FieldType;
    //   373: if_acmpne -> 396
    //   376: aload #19
    //   378: iload #10
    //   380: iload #16
    //   382: iastore
    //   383: iload #10
    //   385: iconst_1
    //   386: iadd
    //   387: istore #15
    //   389: iload #15
    //   391: istore #10
    //   393: goto -> 447
    //   396: iload #10
    //   398: istore #15
    //   400: aload #20
    //   402: invokevirtual getType : ()Lcom/google/protobuf/FieldType;
    //   405: invokevirtual id : ()I
    //   408: bipush #18
    //   410: if_icmplt -> 389
    //   413: iload #10
    //   415: istore #15
    //   417: aload #20
    //   419: invokevirtual getType : ()Lcom/google/protobuf/FieldType;
    //   422: invokevirtual id : ()I
    //   425: bipush #49
    //   427: if_icmpgt -> 389
    //   430: aload #17
    //   432: iload #14
    //   434: aload #20
    //   436: invokevirtual getField : ()Ljava/lang/reflect/Field;
    //   439: invokestatic objectFieldOffset : (Ljava/lang/reflect/Field;)J
    //   442: l2i
    //   443: iastore
    //   444: iinc #14, 1
    //   447: iinc #18, 1
    //   450: iinc #16, 3
    //   453: iload #13
    //   455: istore #22
    //   457: goto -> 291
    //   460: aload #19
    //   462: astore #20
    //   464: aload #19
    //   466: ifnonnull -> 474
    //   469: getstatic com/google/protobuf/MessageSchema.EMPTY_INT_ARRAY : [I
    //   472: astore #20
    //   474: aload #17
    //   476: astore #19
    //   478: aload #17
    //   480: ifnonnull -> 488
    //   483: getstatic com/google/protobuf/MessageSchema.EMPTY_INT_ARRAY : [I
    //   486: astore #19
    //   488: aload #21
    //   490: arraylength
    //   491: aload #20
    //   493: arraylength
    //   494: iadd
    //   495: aload #19
    //   497: arraylength
    //   498: iadd
    //   499: newarray int
    //   501: astore #17
    //   503: aload #21
    //   505: iconst_0
    //   506: aload #17
    //   508: iconst_0
    //   509: aload #21
    //   511: arraylength
    //   512: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   515: aload #20
    //   517: iconst_0
    //   518: aload #17
    //   520: aload #21
    //   522: arraylength
    //   523: aload #20
    //   525: arraylength
    //   526: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   529: aload #19
    //   531: iconst_0
    //   532: aload #17
    //   534: aload #21
    //   536: arraylength
    //   537: aload #20
    //   539: arraylength
    //   540: iadd
    //   541: aload #19
    //   543: arraylength
    //   544: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   547: new com/google/protobuf/MessageSchema
    //   550: dup
    //   551: aload #11
    //   553: aload #12
    //   555: iload #8
    //   557: iload #9
    //   559: aload_0
    //   560: invokevirtual getDefaultInstance : ()Lcom/google/protobuf/MessageLite;
    //   563: iload #6
    //   565: iconst_1
    //   566: aload #17
    //   568: aload #21
    //   570: arraylength
    //   571: aload #21
    //   573: arraylength
    //   574: aload #20
    //   576: arraylength
    //   577: iadd
    //   578: aload_1
    //   579: aload_2
    //   580: aload_3
    //   581: aload #4
    //   583: aload #5
    //   585: invokespecial <init> : ([I[Ljava/lang/Object;IILcom/google/protobuf/MessageLite;ZZ[IIILcom/google/protobuf/NewInstanceSchema;Lcom/google/protobuf/ListFieldSchema;Lcom/google/protobuf/UnknownFieldSchema;Lcom/google/protobuf/ExtensionSchema;Lcom/google/protobuf/MapFieldSchema;)V
    //   588: areturn
  }
  
  static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo paramRawMessageInfo, NewInstanceSchema paramNewInstanceSchema, ListFieldSchema paramListFieldSchema, UnknownFieldSchema<?, ?> paramUnknownFieldSchema, ExtensionSchema<?> paramExtensionSchema, MapFieldSchema paramMapFieldSchema) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getSyntax : ()Lcom/google/protobuf/ProtoSyntax;
    //   4: getstatic com/google/protobuf/ProtoSyntax.PROTO3 : Lcom/google/protobuf/ProtoSyntax;
    //   7: if_acmpne -> 16
    //   10: iconst_1
    //   11: istore #6
    //   13: goto -> 19
    //   16: iconst_0
    //   17: istore #6
    //   19: aload_0
    //   20: invokevirtual getStringInfo : ()Ljava/lang/String;
    //   23: astore #7
    //   25: aload #7
    //   27: invokevirtual length : ()I
    //   30: istore #8
    //   32: aload #7
    //   34: iconst_0
    //   35: invokevirtual charAt : (I)C
    //   38: istore #9
    //   40: iload #9
    //   42: ldc_w 55296
    //   45: if_icmplt -> 123
    //   48: iload #9
    //   50: sipush #8191
    //   53: iand
    //   54: istore #10
    //   56: iconst_1
    //   57: istore #11
    //   59: bipush #13
    //   61: istore #9
    //   63: iload #11
    //   65: iconst_1
    //   66: iadd
    //   67: istore #12
    //   69: aload #7
    //   71: iload #11
    //   73: invokevirtual charAt : (I)C
    //   76: istore #11
    //   78: iload #11
    //   80: ldc_w 55296
    //   83: if_icmplt -> 110
    //   86: iload #10
    //   88: iload #11
    //   90: sipush #8191
    //   93: iand
    //   94: iload #9
    //   96: ishl
    //   97: ior
    //   98: istore #10
    //   100: iinc #9, 13
    //   103: iload #12
    //   105: istore #11
    //   107: goto -> 63
    //   110: iload #11
    //   112: iload #9
    //   114: ishl
    //   115: iload #10
    //   117: ior
    //   118: istore #9
    //   120: goto -> 126
    //   123: iconst_1
    //   124: istore #12
    //   126: iload #12
    //   128: iconst_1
    //   129: iadd
    //   130: istore #11
    //   132: aload #7
    //   134: iload #12
    //   136: invokevirtual charAt : (I)C
    //   139: istore #12
    //   141: iload #12
    //   143: ldc_w 55296
    //   146: if_icmplt -> 221
    //   149: iload #12
    //   151: sipush #8191
    //   154: iand
    //   155: istore #10
    //   157: bipush #13
    //   159: istore #12
    //   161: iload #11
    //   163: iconst_1
    //   164: iadd
    //   165: istore #13
    //   167: aload #7
    //   169: iload #11
    //   171: invokevirtual charAt : (I)C
    //   174: istore #11
    //   176: iload #11
    //   178: ldc_w 55296
    //   181: if_icmplt -> 208
    //   184: iload #10
    //   186: iload #11
    //   188: sipush #8191
    //   191: iand
    //   192: iload #12
    //   194: ishl
    //   195: ior
    //   196: istore #10
    //   198: iinc #12, 13
    //   201: iload #13
    //   203: istore #11
    //   205: goto -> 161
    //   208: iload #10
    //   210: iload #11
    //   212: iload #12
    //   214: ishl
    //   215: ior
    //   216: istore #12
    //   218: goto -> 225
    //   221: iload #11
    //   223: istore #13
    //   225: iload #12
    //   227: ifne -> 277
    //   230: getstatic com/google/protobuf/MessageSchema.EMPTY_INT_ARRAY : [I
    //   233: astore #14
    //   235: iconst_0
    //   236: istore #15
    //   238: iload #15
    //   240: istore #11
    //   242: iload #11
    //   244: istore #16
    //   246: iload #16
    //   248: istore #17
    //   250: iload #17
    //   252: istore #12
    //   254: iload #12
    //   256: istore #10
    //   258: iload #10
    //   260: istore #18
    //   262: iload #16
    //   264: istore #19
    //   266: iload #17
    //   268: istore #20
    //   270: iload #10
    //   272: istore #16
    //   274: goto -> 1165
    //   277: iload #13
    //   279: iconst_1
    //   280: iadd
    //   281: istore #12
    //   283: aload #7
    //   285: iload #13
    //   287: invokevirtual charAt : (I)C
    //   290: istore #10
    //   292: iload #10
    //   294: ldc_w 55296
    //   297: if_icmplt -> 380
    //   300: iload #10
    //   302: sipush #8191
    //   305: iand
    //   306: istore #11
    //   308: bipush #13
    //   310: istore #10
    //   312: iload #12
    //   314: istore #17
    //   316: iload #17
    //   318: iconst_1
    //   319: iadd
    //   320: istore #12
    //   322: aload #7
    //   324: iload #17
    //   326: invokevirtual charAt : (I)C
    //   329: istore #17
    //   331: iload #17
    //   333: ldc_w 55296
    //   336: if_icmplt -> 363
    //   339: iload #11
    //   341: iload #17
    //   343: sipush #8191
    //   346: iand
    //   347: iload #10
    //   349: ishl
    //   350: ior
    //   351: istore #11
    //   353: iinc #10, 13
    //   356: iload #12
    //   358: istore #17
    //   360: goto -> 316
    //   363: iload #17
    //   365: iload #10
    //   367: ishl
    //   368: iload #11
    //   370: ior
    //   371: istore #10
    //   373: iload #12
    //   375: istore #11
    //   377: goto -> 384
    //   380: iload #12
    //   382: istore #11
    //   384: iload #11
    //   386: iconst_1
    //   387: iadd
    //   388: istore #12
    //   390: aload #7
    //   392: iload #11
    //   394: invokevirtual charAt : (I)C
    //   397: istore #16
    //   399: iload #16
    //   401: ldc_w 55296
    //   404: if_icmplt -> 483
    //   407: iload #16
    //   409: sipush #8191
    //   412: iand
    //   413: istore #17
    //   415: bipush #13
    //   417: istore #11
    //   419: iload #12
    //   421: istore #18
    //   423: iload #18
    //   425: iconst_1
    //   426: iadd
    //   427: istore #12
    //   429: aload #7
    //   431: iload #18
    //   433: invokevirtual charAt : (I)C
    //   436: istore #18
    //   438: iload #18
    //   440: ldc_w 55296
    //   443: if_icmplt -> 470
    //   446: iload #17
    //   448: iload #18
    //   450: sipush #8191
    //   453: iand
    //   454: iload #11
    //   456: ishl
    //   457: ior
    //   458: istore #17
    //   460: iinc #11, 13
    //   463: iload #12
    //   465: istore #18
    //   467: goto -> 423
    //   470: iload #17
    //   472: iload #18
    //   474: iload #11
    //   476: ishl
    //   477: ior
    //   478: istore #16
    //   480: goto -> 483
    //   483: iload #12
    //   485: iconst_1
    //   486: iadd
    //   487: istore #11
    //   489: aload #7
    //   491: iload #12
    //   493: invokevirtual charAt : (I)C
    //   496: istore #12
    //   498: iload #12
    //   500: ldc_w 55296
    //   503: if_icmplt -> 582
    //   506: iload #12
    //   508: sipush #8191
    //   511: iand
    //   512: istore #17
    //   514: bipush #13
    //   516: istore #12
    //   518: iload #11
    //   520: istore #18
    //   522: iload #18
    //   524: iconst_1
    //   525: iadd
    //   526: istore #11
    //   528: aload #7
    //   530: iload #18
    //   532: invokevirtual charAt : (I)C
    //   535: istore #18
    //   537: iload #18
    //   539: ldc_w 55296
    //   542: if_icmplt -> 569
    //   545: iload #17
    //   547: iload #18
    //   549: sipush #8191
    //   552: iand
    //   553: iload #12
    //   555: ishl
    //   556: ior
    //   557: istore #17
    //   559: iinc #12, 13
    //   562: iload #11
    //   564: istore #18
    //   566: goto -> 522
    //   569: iload #18
    //   571: iload #12
    //   573: ishl
    //   574: iload #17
    //   576: ior
    //   577: istore #12
    //   579: goto -> 582
    //   582: iload #11
    //   584: iconst_1
    //   585: iadd
    //   586: istore #17
    //   588: aload #7
    //   590: iload #11
    //   592: invokevirtual charAt : (I)C
    //   595: istore #11
    //   597: iload #11
    //   599: ldc_w 55296
    //   602: if_icmplt -> 681
    //   605: iload #11
    //   607: sipush #8191
    //   610: iand
    //   611: istore #18
    //   613: bipush #13
    //   615: istore #11
    //   617: iload #17
    //   619: istore #15
    //   621: iload #15
    //   623: iconst_1
    //   624: iadd
    //   625: istore #17
    //   627: aload #7
    //   629: iload #15
    //   631: invokevirtual charAt : (I)C
    //   634: istore #15
    //   636: iload #15
    //   638: ldc_w 55296
    //   641: if_icmplt -> 668
    //   644: iload #18
    //   646: iload #15
    //   648: sipush #8191
    //   651: iand
    //   652: iload #11
    //   654: ishl
    //   655: ior
    //   656: istore #18
    //   658: iinc #11, 13
    //   661: iload #17
    //   663: istore #15
    //   665: goto -> 621
    //   668: iload #15
    //   670: iload #11
    //   672: ishl
    //   673: iload #18
    //   675: ior
    //   676: istore #11
    //   678: goto -> 681
    //   681: iload #17
    //   683: iconst_1
    //   684: iadd
    //   685: istore #18
    //   687: aload #7
    //   689: iload #17
    //   691: invokevirtual charAt : (I)C
    //   694: istore #17
    //   696: iload #17
    //   698: ldc_w 55296
    //   701: if_icmplt -> 780
    //   704: iload #17
    //   706: sipush #8191
    //   709: iand
    //   710: istore #15
    //   712: bipush #13
    //   714: istore #17
    //   716: iload #18
    //   718: istore #13
    //   720: iload #13
    //   722: iconst_1
    //   723: iadd
    //   724: istore #18
    //   726: aload #7
    //   728: iload #13
    //   730: invokevirtual charAt : (I)C
    //   733: istore #13
    //   735: iload #13
    //   737: ldc_w 55296
    //   740: if_icmplt -> 767
    //   743: iload #15
    //   745: iload #13
    //   747: sipush #8191
    //   750: iand
    //   751: iload #17
    //   753: ishl
    //   754: ior
    //   755: istore #15
    //   757: iinc #17, 13
    //   760: iload #18
    //   762: istore #13
    //   764: goto -> 720
    //   767: iload #13
    //   769: iload #17
    //   771: ishl
    //   772: iload #15
    //   774: ior
    //   775: istore #17
    //   777: goto -> 780
    //   780: iload #18
    //   782: iconst_1
    //   783: iadd
    //   784: istore #15
    //   786: aload #7
    //   788: iload #18
    //   790: invokevirtual charAt : (I)C
    //   793: istore #21
    //   795: iload #15
    //   797: istore #13
    //   799: iload #21
    //   801: istore #18
    //   803: iload #21
    //   805: ldc_w 55296
    //   808: if_icmplt -> 888
    //   811: iload #21
    //   813: sipush #8191
    //   816: iand
    //   817: istore #21
    //   819: bipush #13
    //   821: istore #13
    //   823: iload #15
    //   825: iconst_1
    //   826: iadd
    //   827: istore #18
    //   829: aload #7
    //   831: iload #15
    //   833: invokevirtual charAt : (I)C
    //   836: istore #15
    //   838: iload #15
    //   840: ldc_w 55296
    //   843: if_icmplt -> 870
    //   846: iload #21
    //   848: iload #15
    //   850: sipush #8191
    //   853: iand
    //   854: iload #13
    //   856: ishl
    //   857: ior
    //   858: istore #21
    //   860: iinc #13, 13
    //   863: iload #18
    //   865: istore #15
    //   867: goto -> 823
    //   870: iload #15
    //   872: iload #13
    //   874: ishl
    //   875: iload #21
    //   877: ior
    //   878: istore #15
    //   880: iload #18
    //   882: istore #13
    //   884: iload #15
    //   886: istore #18
    //   888: iload #13
    //   890: iconst_1
    //   891: iadd
    //   892: istore #15
    //   894: aload #7
    //   896: iload #13
    //   898: invokevirtual charAt : (I)C
    //   901: istore #20
    //   903: iload #20
    //   905: istore #21
    //   907: iload #15
    //   909: istore #13
    //   911: iload #20
    //   913: ldc_w 55296
    //   916: if_icmplt -> 996
    //   919: iload #20
    //   921: sipush #8191
    //   924: iand
    //   925: istore #21
    //   927: bipush #13
    //   929: istore #13
    //   931: iload #15
    //   933: istore #20
    //   935: iload #20
    //   937: iconst_1
    //   938: iadd
    //   939: istore #15
    //   941: aload #7
    //   943: iload #20
    //   945: invokevirtual charAt : (I)C
    //   948: istore #20
    //   950: iload #20
    //   952: ldc_w 55296
    //   955: if_icmplt -> 982
    //   958: iload #21
    //   960: iload #20
    //   962: sipush #8191
    //   965: iand
    //   966: iload #13
    //   968: ishl
    //   969: ior
    //   970: istore #21
    //   972: iinc #13, 13
    //   975: iload #15
    //   977: istore #20
    //   979: goto -> 935
    //   982: iload #21
    //   984: iload #20
    //   986: iload #13
    //   988: ishl
    //   989: ior
    //   990: istore #21
    //   992: iload #15
    //   994: istore #13
    //   996: iload #13
    //   998: iconst_1
    //   999: iadd
    //   1000: istore #20
    //   1002: aload #7
    //   1004: iload #13
    //   1006: invokevirtual charAt : (I)C
    //   1009: istore #19
    //   1011: iload #19
    //   1013: istore #15
    //   1015: iload #20
    //   1017: istore #13
    //   1019: iload #19
    //   1021: ldc_w 55296
    //   1024: if_icmplt -> 1112
    //   1027: bipush #13
    //   1029: istore #13
    //   1031: iload #19
    //   1033: sipush #8191
    //   1036: iand
    //   1037: istore #15
    //   1039: iload #20
    //   1041: istore #19
    //   1043: iload #15
    //   1045: istore #20
    //   1047: iload #19
    //   1049: iconst_1
    //   1050: iadd
    //   1051: istore #15
    //   1053: aload #7
    //   1055: iload #19
    //   1057: invokevirtual charAt : (I)C
    //   1060: istore #19
    //   1062: iload #19
    //   1064: ldc_w 55296
    //   1067: if_icmplt -> 1094
    //   1070: iload #20
    //   1072: iload #19
    //   1074: sipush #8191
    //   1077: iand
    //   1078: iload #13
    //   1080: ishl
    //   1081: ior
    //   1082: istore #20
    //   1084: iinc #13, 13
    //   1087: iload #15
    //   1089: istore #19
    //   1091: goto -> 1047
    //   1094: iload #20
    //   1096: iload #19
    //   1098: iload #13
    //   1100: ishl
    //   1101: ior
    //   1102: istore #20
    //   1104: iload #15
    //   1106: istore #13
    //   1108: iload #20
    //   1110: istore #15
    //   1112: iload #15
    //   1114: iload #18
    //   1116: iadd
    //   1117: iload #21
    //   1119: iadd
    //   1120: newarray int
    //   1122: astore #14
    //   1124: iload #10
    //   1126: iconst_2
    //   1127: imul
    //   1128: iload #16
    //   1130: iadd
    //   1131: istore #22
    //   1133: iload #12
    //   1135: istore #21
    //   1137: iload #11
    //   1139: istore #16
    //   1141: iload #15
    //   1143: istore #12
    //   1145: iload #18
    //   1147: istore #20
    //   1149: iload #17
    //   1151: istore #19
    //   1153: iload #22
    //   1155: istore #11
    //   1157: iload #10
    //   1159: istore #15
    //   1161: iload #21
    //   1163: istore #18
    //   1165: getstatic com/google/protobuf/MessageSchema.UNSAFE : Lsun/misc/Unsafe;
    //   1168: astore #23
    //   1170: aload_0
    //   1171: invokevirtual getObjects : ()[Ljava/lang/Object;
    //   1174: astore #24
    //   1176: aload_0
    //   1177: invokevirtual getDefaultInstance : ()Lcom/google/protobuf/MessageLite;
    //   1180: invokevirtual getClass : ()Ljava/lang/Class;
    //   1183: astore #25
    //   1185: iload #19
    //   1187: iconst_3
    //   1188: imul
    //   1189: newarray int
    //   1191: astore #26
    //   1193: iload #19
    //   1195: iconst_2
    //   1196: imul
    //   1197: anewarray java/lang/Object
    //   1200: astore #27
    //   1202: iload #20
    //   1204: iload #12
    //   1206: iadd
    //   1207: istore #19
    //   1209: iload #11
    //   1211: istore #17
    //   1213: iload #19
    //   1215: istore #10
    //   1217: iload #12
    //   1219: istore #11
    //   1221: iconst_0
    //   1222: istore #20
    //   1224: iconst_0
    //   1225: istore #28
    //   1227: iload #12
    //   1229: istore #21
    //   1231: iload #20
    //   1233: istore #12
    //   1235: iload #15
    //   1237: istore #20
    //   1239: iload #18
    //   1241: istore #29
    //   1243: iload #9
    //   1245: istore #22
    //   1247: iload #13
    //   1249: iload #8
    //   1251: if_icmpge -> 2499
    //   1254: iload #13
    //   1256: iconst_1
    //   1257: iadd
    //   1258: istore #9
    //   1260: aload #7
    //   1262: iload #13
    //   1264: invokevirtual charAt : (I)C
    //   1267: istore #30
    //   1269: iload #30
    //   1271: ldc_w 55296
    //   1274: if_icmplt -> 1357
    //   1277: bipush #13
    //   1279: istore #18
    //   1281: iload #30
    //   1283: sipush #8191
    //   1286: iand
    //   1287: istore #15
    //   1289: iload #9
    //   1291: istore #13
    //   1293: iload #18
    //   1295: istore #9
    //   1297: iload #13
    //   1299: iconst_1
    //   1300: iadd
    //   1301: istore #18
    //   1303: aload #7
    //   1305: iload #13
    //   1307: invokevirtual charAt : (I)C
    //   1310: istore #13
    //   1312: iload #13
    //   1314: ldc_w 55296
    //   1317: if_icmplt -> 1344
    //   1320: iload #15
    //   1322: iload #13
    //   1324: sipush #8191
    //   1327: iand
    //   1328: iload #9
    //   1330: ishl
    //   1331: ior
    //   1332: istore #15
    //   1334: iinc #9, 13
    //   1337: iload #18
    //   1339: istore #13
    //   1341: goto -> 1297
    //   1344: iload #15
    //   1346: iload #13
    //   1348: iload #9
    //   1350: ishl
    //   1351: ior
    //   1352: istore #30
    //   1354: goto -> 1361
    //   1357: iload #9
    //   1359: istore #18
    //   1361: iload #18
    //   1363: iconst_1
    //   1364: iadd
    //   1365: istore #9
    //   1367: aload #7
    //   1369: iload #18
    //   1371: invokevirtual charAt : (I)C
    //   1374: istore #31
    //   1376: iload #31
    //   1378: ldc_w 55296
    //   1381: if_icmplt -> 1464
    //   1384: bipush #13
    //   1386: istore #18
    //   1388: iload #31
    //   1390: sipush #8191
    //   1393: iand
    //   1394: istore #15
    //   1396: iload #9
    //   1398: istore #13
    //   1400: iload #13
    //   1402: iconst_1
    //   1403: iadd
    //   1404: istore #9
    //   1406: aload #7
    //   1408: iload #13
    //   1410: invokevirtual charAt : (I)C
    //   1413: istore #13
    //   1415: iload #13
    //   1417: ldc_w 55296
    //   1420: if_icmplt -> 1447
    //   1423: iload #15
    //   1425: iload #13
    //   1427: sipush #8191
    //   1430: iand
    //   1431: iload #18
    //   1433: ishl
    //   1434: ior
    //   1435: istore #15
    //   1437: iinc #18, 13
    //   1440: iload #9
    //   1442: istore #13
    //   1444: goto -> 1400
    //   1447: iload #15
    //   1449: iload #13
    //   1451: iload #18
    //   1453: ishl
    //   1454: ior
    //   1455: istore #31
    //   1457: iload #9
    //   1459: istore #18
    //   1461: goto -> 1468
    //   1464: iload #9
    //   1466: istore #18
    //   1468: iload #31
    //   1470: sipush #255
    //   1473: iand
    //   1474: istore #32
    //   1476: iload #12
    //   1478: istore #9
    //   1480: iload #31
    //   1482: sipush #1024
    //   1485: iand
    //   1486: ifeq -> 1502
    //   1489: aload #14
    //   1491: iload #12
    //   1493: iload #28
    //   1495: iastore
    //   1496: iload #12
    //   1498: iconst_1
    //   1499: iadd
    //   1500: istore #9
    //   1502: iload #32
    //   1504: bipush #51
    //   1506: if_icmplt -> 1866
    //   1509: iload #18
    //   1511: iconst_1
    //   1512: iadd
    //   1513: istore #15
    //   1515: aload #7
    //   1517: iload #18
    //   1519: invokevirtual charAt : (I)C
    //   1522: istore #12
    //   1524: iload #12
    //   1526: ldc_w 55296
    //   1529: if_icmplt -> 1612
    //   1532: iload #12
    //   1534: sipush #8191
    //   1537: iand
    //   1538: istore #18
    //   1540: bipush #13
    //   1542: istore #12
    //   1544: iload #15
    //   1546: istore #13
    //   1548: iload #18
    //   1550: istore #15
    //   1552: iload #13
    //   1554: iconst_1
    //   1555: iadd
    //   1556: istore #18
    //   1558: aload #7
    //   1560: iload #13
    //   1562: invokevirtual charAt : (I)C
    //   1565: istore #13
    //   1567: iload #13
    //   1569: ldc_w 55296
    //   1572: if_icmplt -> 1599
    //   1575: iload #15
    //   1577: iload #13
    //   1579: sipush #8191
    //   1582: iand
    //   1583: iload #12
    //   1585: ishl
    //   1586: ior
    //   1587: istore #15
    //   1589: iinc #12, 13
    //   1592: iload #18
    //   1594: istore #13
    //   1596: goto -> 1552
    //   1599: iload #15
    //   1601: iload #13
    //   1603: iload #12
    //   1605: ishl
    //   1606: ior
    //   1607: istore #15
    //   1609: goto -> 1620
    //   1612: iload #15
    //   1614: istore #18
    //   1616: iload #12
    //   1618: istore #15
    //   1620: iload #32
    //   1622: bipush #51
    //   1624: isub
    //   1625: istore #12
    //   1627: iload #12
    //   1629: bipush #9
    //   1631: if_icmpeq -> 1695
    //   1634: iload #12
    //   1636: bipush #17
    //   1638: if_icmpne -> 1644
    //   1641: goto -> 1695
    //   1644: iload #12
    //   1646: bipush #12
    //   1648: if_icmpne -> 1688
    //   1651: iload #22
    //   1653: iconst_1
    //   1654: iand
    //   1655: iconst_1
    //   1656: if_icmpne -> 1688
    //   1659: iload #28
    //   1661: iconst_3
    //   1662: idiv
    //   1663: istore #13
    //   1665: iload #17
    //   1667: iconst_1
    //   1668: iadd
    //   1669: istore #12
    //   1671: aload #27
    //   1673: iload #13
    //   1675: iconst_2
    //   1676: imul
    //   1677: iconst_1
    //   1678: iadd
    //   1679: aload #24
    //   1681: iload #17
    //   1683: aaload
    //   1684: aastore
    //   1685: goto -> 1721
    //   1688: iload #17
    //   1690: istore #12
    //   1692: goto -> 1721
    //   1695: iload #28
    //   1697: iconst_3
    //   1698: idiv
    //   1699: istore #13
    //   1701: iload #17
    //   1703: iconst_1
    //   1704: iadd
    //   1705: istore #12
    //   1707: aload #27
    //   1709: iload #13
    //   1711: iconst_2
    //   1712: imul
    //   1713: iconst_1
    //   1714: iadd
    //   1715: aload #24
    //   1717: iload #17
    //   1719: aaload
    //   1720: aastore
    //   1721: iload #15
    //   1723: iconst_2
    //   1724: imul
    //   1725: istore #17
    //   1727: aload #24
    //   1729: iload #17
    //   1731: aaload
    //   1732: astore #33
    //   1734: aload #33
    //   1736: instanceof java/lang/reflect/Field
    //   1739: ifeq -> 1752
    //   1742: aload #33
    //   1744: checkcast java/lang/reflect/Field
    //   1747: astore #33
    //   1749: goto -> 1774
    //   1752: aload #25
    //   1754: aload #33
    //   1756: checkcast java/lang/String
    //   1759: invokestatic reflectField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1762: astore #33
    //   1764: aload #24
    //   1766: iload #17
    //   1768: aload #33
    //   1770: aastore
    //   1771: goto -> 1749
    //   1774: aload #23
    //   1776: aload #33
    //   1778: invokevirtual objectFieldOffset : (Ljava/lang/reflect/Field;)J
    //   1781: l2i
    //   1782: istore #13
    //   1784: iinc #17, 1
    //   1787: aload #24
    //   1789: iload #17
    //   1791: aaload
    //   1792: astore #33
    //   1794: aload #33
    //   1796: instanceof java/lang/reflect/Field
    //   1799: ifeq -> 1812
    //   1802: aload #33
    //   1804: checkcast java/lang/reflect/Field
    //   1807: astore #33
    //   1809: goto -> 1834
    //   1812: aload #25
    //   1814: aload #33
    //   1816: checkcast java/lang/String
    //   1819: invokestatic reflectField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1822: astore #33
    //   1824: aload #24
    //   1826: iload #17
    //   1828: aload #33
    //   1830: aastore
    //   1831: goto -> 1809
    //   1834: aload #23
    //   1836: aload #33
    //   1838: invokevirtual objectFieldOffset : (Ljava/lang/reflect/Field;)J
    //   1841: l2i
    //   1842: istore #15
    //   1844: iload #18
    //   1846: istore #17
    //   1848: iload #12
    //   1850: istore #34
    //   1852: iconst_0
    //   1853: istore #18
    //   1855: iload #17
    //   1857: istore #12
    //   1859: iload #34
    //   1861: istore #17
    //   1863: goto -> 2393
    //   1866: iload #17
    //   1868: iconst_1
    //   1869: iadd
    //   1870: istore #12
    //   1872: aload #25
    //   1874: aload #24
    //   1876: iload #17
    //   1878: aaload
    //   1879: checkcast java/lang/String
    //   1882: invokestatic reflectField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1885: astore #33
    //   1887: iload #32
    //   1889: bipush #9
    //   1891: if_icmpeq -> 2100
    //   1894: iload #32
    //   1896: bipush #17
    //   1898: if_icmpne -> 1904
    //   1901: goto -> 2100
    //   1904: iload #32
    //   1906: bipush #27
    //   1908: if_icmpeq -> 2067
    //   1911: iload #32
    //   1913: bipush #49
    //   1915: if_icmpne -> 1921
    //   1918: goto -> 2067
    //   1921: iload #32
    //   1923: bipush #12
    //   1925: if_icmpeq -> 2026
    //   1928: iload #32
    //   1930: bipush #30
    //   1932: if_icmpeq -> 2026
    //   1935: iload #32
    //   1937: bipush #44
    //   1939: if_icmpne -> 1945
    //   1942: goto -> 2026
    //   1945: iload #32
    //   1947: bipush #50
    //   1949: if_icmpne -> 2023
    //   1952: aload #14
    //   1954: iload #11
    //   1956: iload #28
    //   1958: iastore
    //   1959: iload #28
    //   1961: iconst_3
    //   1962: idiv
    //   1963: iconst_2
    //   1964: imul
    //   1965: istore #15
    //   1967: iload #12
    //   1969: iconst_1
    //   1970: iadd
    //   1971: istore #17
    //   1973: aload #27
    //   1975: iload #15
    //   1977: aload #24
    //   1979: iload #12
    //   1981: aaload
    //   1982: aastore
    //   1983: iload #31
    //   1985: sipush #2048
    //   1988: iand
    //   1989: ifeq -> 2013
    //   1992: iload #17
    //   1994: iconst_1
    //   1995: iadd
    //   1996: istore #12
    //   1998: aload #27
    //   2000: iload #15
    //   2002: iconst_1
    //   2003: iadd
    //   2004: aload #24
    //   2006: iload #17
    //   2008: aaload
    //   2009: aastore
    //   2010: goto -> 2017
    //   2013: iload #17
    //   2015: istore #12
    //   2017: iinc #11, 1
    //   2020: goto -> 2116
    //   2023: goto -> 2116
    //   2026: iload #22
    //   2028: iconst_1
    //   2029: iand
    //   2030: iconst_1
    //   2031: if_icmpne -> 2116
    //   2034: iload #28
    //   2036: iconst_3
    //   2037: idiv
    //   2038: istore #15
    //   2040: iload #12
    //   2042: iconst_1
    //   2043: iadd
    //   2044: istore #17
    //   2046: aload #27
    //   2048: iload #15
    //   2050: iconst_2
    //   2051: imul
    //   2052: iconst_1
    //   2053: iadd
    //   2054: aload #24
    //   2056: iload #12
    //   2058: aaload
    //   2059: aastore
    //   2060: iload #17
    //   2062: istore #12
    //   2064: goto -> 2097
    //   2067: iload #28
    //   2069: iconst_3
    //   2070: idiv
    //   2071: istore #15
    //   2073: iload #12
    //   2075: iconst_1
    //   2076: iadd
    //   2077: istore #17
    //   2079: aload #27
    //   2081: iload #15
    //   2083: iconst_2
    //   2084: imul
    //   2085: iconst_1
    //   2086: iadd
    //   2087: aload #24
    //   2089: iload #12
    //   2091: aaload
    //   2092: aastore
    //   2093: iload #17
    //   2095: istore #12
    //   2097: goto -> 2116
    //   2100: aload #27
    //   2102: iload #28
    //   2104: iconst_3
    //   2105: idiv
    //   2106: iconst_2
    //   2107: imul
    //   2108: iconst_1
    //   2109: iadd
    //   2110: aload #33
    //   2112: invokevirtual getType : ()Ljava/lang/Class;
    //   2115: aastore
    //   2116: aload #23
    //   2118: aload #33
    //   2120: invokevirtual objectFieldOffset : (Ljava/lang/reflect/Field;)J
    //   2123: l2i
    //   2124: istore #34
    //   2126: iload #22
    //   2128: iconst_1
    //   2129: iand
    //   2130: iconst_1
    //   2131: if_icmpne -> 2328
    //   2134: iload #32
    //   2136: bipush #17
    //   2138: if_icmpgt -> 2328
    //   2141: iload #18
    //   2143: iconst_1
    //   2144: iadd
    //   2145: istore #17
    //   2147: aload #7
    //   2149: iload #18
    //   2151: invokevirtual charAt : (I)C
    //   2154: istore #15
    //   2156: iload #15
    //   2158: istore #18
    //   2160: iload #17
    //   2162: istore #13
    //   2164: iload #15
    //   2166: ldc_w 55296
    //   2169: if_icmplt -> 2249
    //   2172: iload #15
    //   2174: sipush #8191
    //   2177: iand
    //   2178: istore #15
    //   2180: bipush #13
    //   2182: istore #18
    //   2184: iload #17
    //   2186: istore #13
    //   2188: iload #13
    //   2190: iconst_1
    //   2191: iadd
    //   2192: istore #17
    //   2194: aload #7
    //   2196: iload #13
    //   2198: invokevirtual charAt : (I)C
    //   2201: istore #13
    //   2203: iload #13
    //   2205: ldc_w 55296
    //   2208: if_icmplt -> 2235
    //   2211: iload #15
    //   2213: iload #13
    //   2215: sipush #8191
    //   2218: iand
    //   2219: iload #18
    //   2221: ishl
    //   2222: ior
    //   2223: istore #15
    //   2225: iinc #18, 13
    //   2228: iload #17
    //   2230: istore #13
    //   2232: goto -> 2188
    //   2235: iload #15
    //   2237: iload #13
    //   2239: iload #18
    //   2241: ishl
    //   2242: ior
    //   2243: istore #18
    //   2245: iload #17
    //   2247: istore #13
    //   2249: iload #20
    //   2251: iconst_2
    //   2252: imul
    //   2253: iload #18
    //   2255: bipush #32
    //   2257: idiv
    //   2258: iadd
    //   2259: istore #17
    //   2261: aload #24
    //   2263: iload #17
    //   2265: aaload
    //   2266: astore #33
    //   2268: aload #33
    //   2270: instanceof java/lang/reflect/Field
    //   2273: ifeq -> 2286
    //   2276: aload #33
    //   2278: checkcast java/lang/reflect/Field
    //   2281: astore #33
    //   2283: goto -> 2308
    //   2286: aload #25
    //   2288: aload #33
    //   2290: checkcast java/lang/String
    //   2293: invokestatic reflectField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   2296: astore #33
    //   2298: aload #24
    //   2300: iload #17
    //   2302: aload #33
    //   2304: aastore
    //   2305: goto -> 2283
    //   2308: aload #23
    //   2310: aload #33
    //   2312: invokevirtual objectFieldOffset : (Ljava/lang/reflect/Field;)J
    //   2315: l2i
    //   2316: istore #15
    //   2318: iload #18
    //   2320: bipush #32
    //   2322: irem
    //   2323: istore #18
    //   2325: goto -> 2342
    //   2328: iconst_0
    //   2329: istore #17
    //   2331: iconst_0
    //   2332: istore #15
    //   2334: iload #18
    //   2336: istore #13
    //   2338: iload #17
    //   2340: istore #18
    //   2342: iload #10
    //   2344: istore #17
    //   2346: iload #32
    //   2348: bipush #18
    //   2350: if_icmplt -> 2377
    //   2353: iload #10
    //   2355: istore #17
    //   2357: iload #32
    //   2359: bipush #49
    //   2361: if_icmpgt -> 2377
    //   2364: aload #14
    //   2366: iload #10
    //   2368: iload #34
    //   2370: iastore
    //   2371: iload #10
    //   2373: iconst_1
    //   2374: iadd
    //   2375: istore #17
    //   2377: iload #17
    //   2379: istore #10
    //   2381: iload #12
    //   2383: istore #17
    //   2385: iload #13
    //   2387: istore #12
    //   2389: iload #34
    //   2391: istore #13
    //   2393: iload #28
    //   2395: iconst_1
    //   2396: iadd
    //   2397: istore #34
    //   2399: aload #26
    //   2401: iload #28
    //   2403: iload #30
    //   2405: iastore
    //   2406: iload #34
    //   2408: iconst_1
    //   2409: iadd
    //   2410: istore #35
    //   2412: iload #31
    //   2414: sipush #512
    //   2417: iand
    //   2418: ifeq -> 2428
    //   2421: ldc 536870912
    //   2423: istore #28
    //   2425: goto -> 2431
    //   2428: iconst_0
    //   2429: istore #28
    //   2431: iload #31
    //   2433: sipush #256
    //   2436: iand
    //   2437: ifeq -> 2447
    //   2440: ldc 268435456
    //   2442: istore #30
    //   2444: goto -> 2450
    //   2447: iconst_0
    //   2448: istore #30
    //   2450: aload #26
    //   2452: iload #34
    //   2454: iload #30
    //   2456: iload #28
    //   2458: ior
    //   2459: iload #32
    //   2461: bipush #20
    //   2463: ishl
    //   2464: ior
    //   2465: iload #13
    //   2467: ior
    //   2468: iastore
    //   2469: iload #35
    //   2471: iconst_1
    //   2472: iadd
    //   2473: istore #28
    //   2475: aload #26
    //   2477: iload #35
    //   2479: iload #18
    //   2481: bipush #20
    //   2483: ishl
    //   2484: iload #15
    //   2486: ior
    //   2487: iastore
    //   2488: iload #12
    //   2490: istore #13
    //   2492: iload #9
    //   2494: istore #12
    //   2496: goto -> 1247
    //   2499: new com/google/protobuf/MessageSchema
    //   2502: dup
    //   2503: aload #26
    //   2505: aload #27
    //   2507: iload #29
    //   2509: iload #16
    //   2511: aload_0
    //   2512: invokevirtual getDefaultInstance : ()Lcom/google/protobuf/MessageLite;
    //   2515: iload #6
    //   2517: iconst_0
    //   2518: aload #14
    //   2520: iload #21
    //   2522: iload #19
    //   2524: aload_1
    //   2525: aload_2
    //   2526: aload_3
    //   2527: aload #4
    //   2529: aload #5
    //   2531: invokespecial <init> : ([I[Ljava/lang/Object;IILcom/google/protobuf/MessageLite;ZZ[IIILcom/google/protobuf/NewInstanceSchema;Lcom/google/protobuf/ListFieldSchema;Lcom/google/protobuf/UnknownFieldSchema;Lcom/google/protobuf/ExtensionSchema;Lcom/google/protobuf/MapFieldSchema;)V
    //   2534: areturn
  }
  
  private int numberAt(int paramInt) {
    return this.buffer[paramInt];
  }
  
  private static long offset(int paramInt) {
    return (paramInt & 0xFFFFF);
  }
  
  private static <T> boolean oneofBooleanAt(T paramT, long paramLong) {
    return ((Boolean)UnsafeUtil.getObject(paramT, paramLong)).booleanValue();
  }
  
  private static <T> double oneofDoubleAt(T paramT, long paramLong) {
    return ((Double)UnsafeUtil.getObject(paramT, paramLong)).doubleValue();
  }
  
  private static <T> float oneofFloatAt(T paramT, long paramLong) {
    return ((Float)UnsafeUtil.getObject(paramT, paramLong)).floatValue();
  }
  
  private static <T> int oneofIntAt(T paramT, long paramLong) {
    return ((Integer)UnsafeUtil.getObject(paramT, paramLong)).intValue();
  }
  
  private static <T> long oneofLongAt(T paramT, long paramLong) {
    return ((Long)UnsafeUtil.getObject(paramT, paramLong)).longValue();
  }
  
  private <K, V> int parseMapField(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, long paramLong, ArrayDecoders.Registers paramRegisters) throws IOException {
    Unsafe unsafe = UNSAFE;
    Object object1 = getMapFieldDefaultEntry(paramInt3);
    Object object2 = unsafe.getObject(paramT, paramLong);
    Object object3 = object2;
    if (this.mapFieldSchema.isImmutable(object2)) {
      object3 = this.mapFieldSchema.newMapField(object1);
      this.mapFieldSchema.mergeFrom(object3, object2);
      unsafe.putObject(paramT, paramLong, object3);
    } 
    return decodeMapEntry(paramArrayOfbyte, paramInt1, paramInt2, this.mapFieldSchema.forMapMetadata(object1), this.mapFieldSchema.forMutableMapData(object3), paramRegisters);
  }
  
  private int parseOneofField(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong, int paramInt8, ArrayDecoders.Registers paramRegisters) throws IOException {
    Object object;
    Unsafe unsafe = UNSAFE;
    long l = (this.buffer[paramInt8 + 2] & 0xFFFFF);
    switch (paramInt7) {
      default:
        return paramInt1;
      case 68:
        if (paramInt5 == 3) {
          paramInt1 = ArrayDecoders.decodeGroupField(getMessageFieldSchema(paramInt8), paramArrayOfbyte, paramInt1, paramInt2, paramInt3 & 0xFFFFFFF8 | 0x4, paramRegisters);
          if (unsafe.getInt(paramT, l) == paramInt4) {
            object = unsafe.getObject(paramT, paramLong);
          } else {
            paramArrayOfbyte = null;
          } 
          if (paramArrayOfbyte == null) {
            unsafe.putObject(paramT, paramLong, paramRegisters.object1);
          } else {
            unsafe.putObject(paramT, paramLong, Internal.mergeMessage(paramArrayOfbyte, paramRegisters.object1));
          } 
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 67:
        if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint64(paramArrayOfbyte, paramInt1, paramRegisters);
          unsafe.putObject(paramT, paramLong, Long.valueOf(CodedInputStream.decodeZigZag64(paramRegisters.long1)));
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 66:
        if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint32(paramArrayOfbyte, paramInt1, paramRegisters);
          unsafe.putObject(paramT, paramLong, Integer.valueOf(CodedInputStream.decodeZigZag32(paramRegisters.int1)));
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 63:
        if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint32(paramArrayOfbyte, paramInt1, paramRegisters);
          paramInt2 = paramRegisters.int1;
          object = getEnumFieldVerifier(paramInt8);
          if (object == null || object.isInRange(paramInt2)) {
            unsafe.putObject(paramT, paramLong, Integer.valueOf(paramInt2));
            unsafe.putInt(paramT, l, paramInt4);
          } 
          getMutableUnknownFields(paramT).storeField(paramInt3, Long.valueOf(paramInt2));
        } 
      case 61:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodeBytes((byte[])object, paramInt1, paramRegisters);
          unsafe.putObject(paramT, paramLong, paramRegisters.object1);
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 60:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodeMessageField(getMessageFieldSchema(paramInt8), (byte[])object, paramInt1, paramInt2, paramRegisters);
          if (unsafe.getInt(paramT, l) == paramInt4) {
            object = unsafe.getObject(paramT, paramLong);
          } else {
            object = null;
          } 
          if (object == null) {
            unsafe.putObject(paramT, paramLong, paramRegisters.object1);
          } else {
            unsafe.putObject(paramT, paramLong, Internal.mergeMessage(object, paramRegisters.object1));
          } 
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 59:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodeVarint32((byte[])object, paramInt1, paramRegisters);
          paramInt2 = paramRegisters.int1;
          if (paramInt2 == 0) {
            unsafe.putObject(paramT, paramLong, "");
          } else {
            if ((paramInt6 & 0x20000000) != 0 && !Utf8.isValidUtf8((byte[])object, paramInt1, paramInt1 + paramInt2))
              throw InvalidProtocolBufferException.invalidUtf8(); 
            unsafe.putObject(paramT, paramLong, new String((byte[])object, paramInt1, paramInt2, Internal.UTF_8));
            paramInt1 += paramInt2;
          } 
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 58:
        if (paramInt5 == 0) {
          boolean bool;
          paramInt1 = ArrayDecoders.decodeVarint64((byte[])object, paramInt1, paramRegisters);
          if (paramRegisters.long1 != 0L) {
            bool = true;
          } else {
            bool = false;
          } 
          unsafe.putObject(paramT, paramLong, Boolean.valueOf(bool));
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 57:
      case 64:
        if (paramInt5 == 5) {
          unsafe.putObject(paramT, paramLong, Integer.valueOf(ArrayDecoders.decodeFixed32((byte[])object, paramInt1)));
          paramInt1 += 4;
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 56:
      case 65:
        if (paramInt5 == 1) {
          unsafe.putObject(paramT, paramLong, Long.valueOf(ArrayDecoders.decodeFixed64((byte[])object, paramInt1)));
          paramInt1 += 8;
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 55:
      case 62:
        if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint32((byte[])object, paramInt1, paramRegisters);
          unsafe.putObject(paramT, paramLong, Integer.valueOf(paramRegisters.int1));
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 53:
      case 54:
        if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint64((byte[])object, paramInt1, paramRegisters);
          unsafe.putObject(paramT, paramLong, Long.valueOf(paramRegisters.long1));
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 52:
        if (paramInt5 == 5) {
          unsafe.putObject(paramT, paramLong, Float.valueOf(ArrayDecoders.decodeFloat((byte[])object, paramInt1)));
          paramInt1 += 4;
          unsafe.putInt(paramT, l, paramInt4);
        } 
      case 51:
        break;
    } 
    if (paramInt5 == 1) {
      unsafe.putObject(paramT, paramLong, Double.valueOf(ArrayDecoders.decodeDouble((byte[])object, paramInt1)));
      paramInt1 += 8;
      unsafe.putInt(paramT, l, paramInt4);
    } 
  }
  
  private int parseProto3Message(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, ArrayDecoders.Registers paramRegisters) throws IOException {
    // Byte code:
    //   0: getstatic com/google/protobuf/MessageSchema.UNSAFE : Lsun/misc/Unsafe;
    //   3: astore #6
    //   5: iconst_m1
    //   6: istore #7
    //   8: iload_3
    //   9: istore #8
    //   11: iconst_m1
    //   12: istore_3
    //   13: iconst_0
    //   14: istore #9
    //   16: aload_0
    //   17: astore #10
    //   19: aload_1
    //   20: astore #11
    //   22: iload #4
    //   24: istore #12
    //   26: aload_2
    //   27: astore #13
    //   29: aload #5
    //   31: astore #14
    //   33: iload #8
    //   35: iload #12
    //   37: if_icmpge -> 1132
    //   40: iload #8
    //   42: iconst_1
    //   43: iadd
    //   44: istore #15
    //   46: aload #13
    //   48: iload #8
    //   50: baload
    //   51: istore #16
    //   53: iload #16
    //   55: ifge -> 81
    //   58: iload #16
    //   60: aload #13
    //   62: iload #15
    //   64: aload #14
    //   66: invokestatic decodeVarint32 : (I[BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   69: istore #8
    //   71: aload #14
    //   73: getfield int1 : I
    //   76: istore #16
    //   78: goto -> 85
    //   81: iload #15
    //   83: istore #8
    //   85: iload #16
    //   87: iconst_3
    //   88: iushr
    //   89: istore #15
    //   91: iload #16
    //   93: bipush #7
    //   95: iand
    //   96: istore #17
    //   98: iload #15
    //   100: iload_3
    //   101: if_icmple -> 119
    //   104: aload #10
    //   106: iload #15
    //   108: iload #9
    //   110: iconst_3
    //   111: idiv
    //   112: invokespecial positionForFieldNumber : (II)I
    //   115: istore_3
    //   116: goto -> 130
    //   119: aload #10
    //   121: iload #15
    //   123: invokespecial positionForFieldNumber : (I)I
    //   126: istore_3
    //   127: goto -> 116
    //   130: iload_3
    //   131: iload #7
    //   133: if_icmpne -> 141
    //   136: iconst_0
    //   137: istore_3
    //   138: goto -> 1105
    //   141: aload #10
    //   143: getfield buffer : [I
    //   146: iload_3
    //   147: iconst_1
    //   148: iadd
    //   149: iaload
    //   150: istore #7
    //   152: iload #7
    //   154: invokestatic type : (I)I
    //   157: istore #9
    //   159: iload #7
    //   161: invokestatic offset : (I)J
    //   164: lstore #18
    //   166: iload #9
    //   168: bipush #17
    //   170: if_icmpgt -> 793
    //   173: iconst_1
    //   174: istore #20
    //   176: iload #9
    //   178: tableswitch default -> 260, 0 -> 743, 1 -> 714, 2 -> 681, 3 -> 681, 4 -> 648, 5 -> 616, 6 -> 582, 7 -> 538, 8 -> 482, 9 -> 402, 10 -> 368, 11 -> 648, 12 -> 335, 13 -> 582, 14 -> 616, 15 -> 299, 16 -> 263
    //   260: goto -> 1050
    //   263: iload #17
    //   265: ifne -> 260
    //   268: aload #13
    //   270: iload #8
    //   272: aload #14
    //   274: invokestatic decodeVarint64 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   277: istore #7
    //   279: aload #6
    //   281: aload #11
    //   283: lload #18
    //   285: aload #14
    //   287: getfield long1 : J
    //   290: invokestatic decodeZigZag64 : (J)J
    //   293: invokevirtual putLong : (Ljava/lang/Object;JJ)V
    //   296: goto -> 711
    //   299: iload #17
    //   301: ifne -> 790
    //   304: aload #13
    //   306: iload #8
    //   308: aload #14
    //   310: invokestatic decodeVarint32 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   313: istore #7
    //   315: aload #6
    //   317: aload #11
    //   319: lload #18
    //   321: aload #14
    //   323: getfield int1 : I
    //   326: invokestatic decodeZigZag32 : (I)I
    //   329: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   332: goto -> 770
    //   335: iload #17
    //   337: ifne -> 790
    //   340: aload #13
    //   342: iload #8
    //   344: aload #14
    //   346: invokestatic decodeVarint32 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   349: istore #7
    //   351: aload #6
    //   353: aload #11
    //   355: lload #18
    //   357: aload #14
    //   359: getfield int1 : I
    //   362: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   365: goto -> 770
    //   368: iload #17
    //   370: iconst_2
    //   371: if_icmpne -> 260
    //   374: aload #13
    //   376: iload #8
    //   378: aload #14
    //   380: invokestatic decodeBytes : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   383: istore #7
    //   385: aload #6
    //   387: aload #11
    //   389: lload #18
    //   391: aload #14
    //   393: getfield object1 : Ljava/lang/Object;
    //   396: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   399: goto -> 610
    //   402: iload #17
    //   404: iconst_2
    //   405: if_icmpne -> 260
    //   408: aload #10
    //   410: iload_3
    //   411: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   414: aload #13
    //   416: iload #8
    //   418: iload #12
    //   420: aload #14
    //   422: invokestatic decodeMessageField : (Lcom/google/protobuf/Schema;[BIILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   425: istore #7
    //   427: aload #6
    //   429: aload #11
    //   431: lload #18
    //   433: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   436: astore #21
    //   438: aload #21
    //   440: ifnonnull -> 460
    //   443: aload #6
    //   445: aload #11
    //   447: lload #18
    //   449: aload #14
    //   451: getfield object1 : Ljava/lang/Object;
    //   454: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   457: goto -> 610
    //   460: aload #6
    //   462: aload #11
    //   464: lload #18
    //   466: aload #21
    //   468: aload #14
    //   470: getfield object1 : Ljava/lang/Object;
    //   473: invokestatic mergeMessage : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   476: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   479: goto -> 610
    //   482: iload #17
    //   484: iconst_2
    //   485: if_icmpne -> 260
    //   488: ldc 536870912
    //   490: iload #7
    //   492: iand
    //   493: ifne -> 510
    //   496: aload #13
    //   498: iload #8
    //   500: aload #14
    //   502: invokestatic decodeString : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   505: istore #7
    //   507: goto -> 521
    //   510: aload #13
    //   512: iload #8
    //   514: aload #14
    //   516: invokestatic decodeStringRequireUtf8 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   519: istore #7
    //   521: aload #6
    //   523: aload #11
    //   525: lload #18
    //   527: aload #14
    //   529: getfield object1 : Ljava/lang/Object;
    //   532: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   535: goto -> 610
    //   538: iload #17
    //   540: ifne -> 260
    //   543: aload #13
    //   545: iload #8
    //   547: aload #14
    //   549: invokestatic decodeVarint64 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   552: istore #7
    //   554: aload #14
    //   556: getfield long1 : J
    //   559: lconst_0
    //   560: lcmp
    //   561: ifeq -> 567
    //   564: goto -> 570
    //   567: iconst_0
    //   568: istore #20
    //   570: aload #11
    //   572: lload #18
    //   574: iload #20
    //   576: invokestatic putBoolean : (Ljava/lang/Object;JZ)V
    //   579: goto -> 610
    //   582: iload #17
    //   584: iconst_5
    //   585: if_icmpne -> 260
    //   588: aload #6
    //   590: aload #11
    //   592: lload #18
    //   594: aload #13
    //   596: iload #8
    //   598: invokestatic decodeFixed32 : ([BI)I
    //   601: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   604: iload #8
    //   606: iconst_4
    //   607: iadd
    //   608: istore #7
    //   610: iload_3
    //   611: istore #9
    //   613: goto -> 773
    //   616: iload #17
    //   618: iconst_1
    //   619: if_icmpne -> 260
    //   622: aload #6
    //   624: aload #11
    //   626: lload #18
    //   628: aload #13
    //   630: iload #8
    //   632: invokestatic decodeFixed64 : ([BI)J
    //   635: invokevirtual putLong : (Ljava/lang/Object;JJ)V
    //   638: iload #8
    //   640: bipush #8
    //   642: iadd
    //   643: istore #7
    //   645: goto -> 770
    //   648: iload #17
    //   650: ifne -> 790
    //   653: aload #13
    //   655: iload #8
    //   657: aload #14
    //   659: invokestatic decodeVarint32 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   662: istore #7
    //   664: aload #6
    //   666: aload #11
    //   668: lload #18
    //   670: aload #14
    //   672: getfield int1 : I
    //   675: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   678: goto -> 770
    //   681: iload #17
    //   683: ifne -> 790
    //   686: aload #13
    //   688: iload #8
    //   690: aload #14
    //   692: invokestatic decodeVarint64 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   695: istore #7
    //   697: aload #6
    //   699: aload #11
    //   701: lload #18
    //   703: aload #14
    //   705: getfield long1 : J
    //   708: invokevirtual putLong : (Ljava/lang/Object;JJ)V
    //   711: goto -> 770
    //   714: iload #17
    //   716: iconst_5
    //   717: if_icmpne -> 790
    //   720: aload #11
    //   722: lload #18
    //   724: aload #13
    //   726: iload #8
    //   728: invokestatic decodeFloat : ([BI)F
    //   731: invokestatic putFloat : (Ljava/lang/Object;JF)V
    //   734: iload #8
    //   736: iconst_4
    //   737: iadd
    //   738: istore #7
    //   740: goto -> 770
    //   743: iload #17
    //   745: iconst_1
    //   746: if_icmpne -> 790
    //   749: aload #11
    //   751: lload #18
    //   753: aload #13
    //   755: iload #8
    //   757: invokestatic decodeDouble : ([BI)D
    //   760: invokestatic putDouble : (Ljava/lang/Object;JD)V
    //   763: iload #8
    //   765: bipush #8
    //   767: iadd
    //   768: istore #7
    //   770: iload_3
    //   771: istore #9
    //   773: iload #15
    //   775: istore_3
    //   776: iconst_m1
    //   777: istore #15
    //   779: iload #7
    //   781: istore #8
    //   783: iload #15
    //   785: istore #7
    //   787: goto -> 16
    //   790: goto -> 260
    //   793: iload #9
    //   795: bipush #27
    //   797: if_icmpne -> 912
    //   800: iload #17
    //   802: iconst_2
    //   803: if_icmpne -> 260
    //   806: aload #6
    //   808: aload #11
    //   810: lload #18
    //   812: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   815: checkcast com/google/protobuf/Internal$ProtobufList
    //   818: astore #22
    //   820: aload #22
    //   822: astore #21
    //   824: aload #22
    //   826: invokeinterface isModifiable : ()Z
    //   831: ifne -> 883
    //   834: aload #22
    //   836: invokeinterface size : ()I
    //   841: istore #7
    //   843: iload #7
    //   845: ifne -> 855
    //   848: bipush #10
    //   850: istore #7
    //   852: goto -> 861
    //   855: iload #7
    //   857: iconst_2
    //   858: imul
    //   859: istore #7
    //   861: aload #22
    //   863: iload #7
    //   865: invokeinterface mutableCopyWithCapacity : (I)Lcom/google/protobuf/Internal$ProtobufList;
    //   870: astore #21
    //   872: aload #6
    //   874: aload #11
    //   876: lload #18
    //   878: aload #21
    //   880: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   883: aload #10
    //   885: iload_3
    //   886: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   889: iload #16
    //   891: aload #13
    //   893: iload #8
    //   895: iload #12
    //   897: aload #21
    //   899: aload #14
    //   901: invokestatic decodeMessageList : (Lcom/google/protobuf/Schema;I[BIILcom/google/protobuf/Internal$ProtobufList;Lcom/google/protobuf/ArrayDecoders$Registers;)I
    //   904: istore #7
    //   906: iload_3
    //   907: istore #9
    //   909: goto -> 773
    //   912: iload #9
    //   914: bipush #49
    //   916: if_icmpgt -> 999
    //   919: aload #10
    //   921: aload #11
    //   923: aload #13
    //   925: iload #8
    //   927: iload #12
    //   929: iload #16
    //   931: iload #15
    //   933: iload #17
    //   935: iload_3
    //   936: iload #7
    //   938: i2l
    //   939: iload #9
    //   941: lload #18
    //   943: aload #5
    //   945: invokespecial parseRepeatedField : (Ljava/lang/Object;[BIIIIIIJIJLcom/google/protobuf/ArrayDecoders$Registers;)I
    //   948: istore #9
    //   950: iload #9
    //   952: istore #7
    //   954: iload #9
    //   956: iload #8
    //   958: if_icmpeq -> 985
    //   961: iload_3
    //   962: istore #7
    //   964: iload #15
    //   966: istore_3
    //   967: iconst_m1
    //   968: istore #15
    //   970: iload #9
    //   972: istore #8
    //   974: iload #7
    //   976: istore #9
    //   978: iload #15
    //   980: istore #7
    //   982: goto -> 16
    //   985: iconst_m1
    //   986: istore #9
    //   988: iload #7
    //   990: istore #8
    //   992: iload #9
    //   994: istore #7
    //   996: goto -> 1105
    //   999: iconst_m1
    //   1000: istore #12
    //   1002: iload #9
    //   1004: bipush #50
    //   1006: if_icmpne -> 1056
    //   1009: iload #17
    //   1011: iconst_2
    //   1012: if_icmpne -> 1050
    //   1015: aload_0
    //   1016: aload_1
    //   1017: aload_2
    //   1018: iload #8
    //   1020: iload #4
    //   1022: iload_3
    //   1023: lload #18
    //   1025: aload #5
    //   1027: invokespecial parseMapField : (Ljava/lang/Object;[BIIIJLcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1030: istore #9
    //   1032: iload #9
    //   1034: istore #7
    //   1036: iload #9
    //   1038: iload #8
    //   1040: if_icmpeq -> 985
    //   1043: iload #9
    //   1045: istore #8
    //   1047: goto -> 1098
    //   1050: iconst_m1
    //   1051: istore #7
    //   1053: goto -> 1105
    //   1056: aload_0
    //   1057: aload_1
    //   1058: aload_2
    //   1059: iload #8
    //   1061: iload #4
    //   1063: iload #16
    //   1065: iload #15
    //   1067: iload #17
    //   1069: iload #7
    //   1071: iload #9
    //   1073: lload #18
    //   1075: iload_3
    //   1076: aload #5
    //   1078: invokespecial parseOneofField : (Ljava/lang/Object;[BIIIIIIIJILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1081: istore #9
    //   1083: iload #9
    //   1085: istore #7
    //   1087: iload #9
    //   1089: iload #8
    //   1091: if_icmpeq -> 985
    //   1094: iload #9
    //   1096: istore #8
    //   1098: iload #12
    //   1100: istore #7
    //   1102: goto -> 1123
    //   1105: iload #16
    //   1107: aload_2
    //   1108: iload #8
    //   1110: iload #4
    //   1112: aload_1
    //   1113: invokestatic getMutableUnknownFields : (Ljava/lang/Object;)Lcom/google/protobuf/UnknownFieldSetLite;
    //   1116: aload #5
    //   1118: invokestatic decodeUnknownField : (I[BIILcom/google/protobuf/UnknownFieldSetLite;Lcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1121: istore #8
    //   1123: iload_3
    //   1124: istore #9
    //   1126: iload #15
    //   1128: istore_3
    //   1129: goto -> 16
    //   1132: iload #8
    //   1134: iload #12
    //   1136: if_icmpeq -> 1143
    //   1139: invokestatic parseFailure : ()Lcom/google/protobuf/InvalidProtocolBufferException;
    //   1142: athrow
    //   1143: iload #8
    //   1145: ireturn
  }
  
  private int parseRepeatedField(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, int paramInt7, long paramLong2, ArrayDecoders.Registers paramRegisters) throws IOException {
    UnknownFieldSetLite unknownFieldSetLite1;
    UnknownFieldSetLite unknownFieldSetLite2;
    GeneratedMessageLite generatedMessageLite;
    Internal.ProtobufList<?> protobufList1 = (Internal.ProtobufList)UNSAFE.getObject(paramT, paramLong2);
    Internal.ProtobufList<?> protobufList2 = protobufList1;
    if (!protobufList1.isModifiable()) {
      int i = protobufList1.size();
      if (i == 0) {
        i = 10;
      } else {
        i *= 2;
      } 
      protobufList2 = protobufList1.mutableCopyWithCapacity(i);
      UNSAFE.putObject(paramT, paramLong2, protobufList2);
    } 
    switch (paramInt7) {
      default:
        return paramInt1;
      case 49:
        if (paramInt5 == 3)
          paramInt1 = ArrayDecoders.decodeGroupList(getMessageFieldSchema(paramInt6), paramInt3, paramArrayOfbyte, paramInt1, paramInt2, protobufList2, paramRegisters); 
      case 34:
      case 48:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedSInt64List(paramArrayOfbyte, paramInt1, protobufList2, paramRegisters);
        } else if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeSInt64List(paramInt3, paramArrayOfbyte, paramInt1, paramInt2, protobufList2, paramRegisters);
        } 
      case 33:
      case 47:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedSInt32List(paramArrayOfbyte, paramInt1, protobufList2, paramRegisters);
        } else if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeSInt32List(paramInt3, paramArrayOfbyte, paramInt1, paramInt2, protobufList2, paramRegisters);
        } 
      case 30:
      case 44:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedVarint32List(paramArrayOfbyte, paramInt1, protobufList2, paramRegisters);
        } else if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint32List(paramInt3, paramArrayOfbyte, paramInt1, paramInt2, protobufList2, paramRegisters);
        } else {
        
        } 
        generatedMessageLite = (GeneratedMessageLite)paramT;
        unknownFieldSetLite2 = generatedMessageLite.unknownFields;
        unknownFieldSetLite1 = unknownFieldSetLite2;
        if (unknownFieldSetLite2 == UnknownFieldSetLite.getDefaultInstance())
          unknownFieldSetLite1 = null; 
        unknownFieldSetLite1 = (UnknownFieldSetLite)SchemaUtil.filterUnknownEnumList(paramInt4, (List)protobufList2, getEnumFieldVerifier(paramInt6), unknownFieldSetLite1, this.unknownFieldSchema);
        if (unknownFieldSetLite1 != null)
          generatedMessageLite.unknownFields = unknownFieldSetLite1; 
      case 28:
        if (paramInt5 == 2)
          paramInt1 = ArrayDecoders.decodeBytesList(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite); 
      case 27:
        if (paramInt5 == 2)
          paramInt1 = ArrayDecoders.decodeMessageList(getMessageFieldSchema(paramInt6), paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite); 
      case 26:
        if (paramInt5 == 2)
          if ((paramLong1 & 0x20000000L) == 0L) {
            paramInt1 = ArrayDecoders.decodeStringList(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
          } else {
            paramInt1 = ArrayDecoders.decodeStringListRequireUtf8(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
          }  
      case 25:
      case 42:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedBoolList((byte[])unknownFieldSetLite2, paramInt1, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } else if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeBoolList(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } 
      case 24:
      case 31:
      case 41:
      case 45:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedFixed32List((byte[])unknownFieldSetLite2, paramInt1, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } else if (paramInt5 == 5) {
          paramInt1 = ArrayDecoders.decodeFixed32List(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } 
      case 23:
      case 32:
      case 40:
      case 46:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedFixed64List((byte[])unknownFieldSetLite2, paramInt1, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } else if (paramInt5 == 1) {
          paramInt1 = ArrayDecoders.decodeFixed64List(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } 
      case 22:
      case 29:
      case 39:
      case 43:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedVarint32List((byte[])unknownFieldSetLite2, paramInt1, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } else if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint32List(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } 
      case 20:
      case 21:
      case 37:
      case 38:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedVarint64List((byte[])unknownFieldSetLite2, paramInt1, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } else if (paramInt5 == 0) {
          paramInt1 = ArrayDecoders.decodeVarint64List(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } 
      case 19:
      case 36:
        if (paramInt5 == 2) {
          paramInt1 = ArrayDecoders.decodePackedFloatList((byte[])unknownFieldSetLite2, paramInt1, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } else if (paramInt5 == 5) {
          paramInt1 = ArrayDecoders.decodeFloatList(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite);
        } 
      case 18:
      case 35:
        break;
    } 
    if (paramInt5 == 2)
      paramInt1 = ArrayDecoders.decodePackedDoubleList((byte[])unknownFieldSetLite2, paramInt1, protobufList2, (ArrayDecoders.Registers)generatedMessageLite); 
    if (paramInt5 == 1)
      paramInt1 = ArrayDecoders.decodeDoubleList(paramInt3, (byte[])unknownFieldSetLite2, paramInt1, paramInt2, protobufList2, (ArrayDecoders.Registers)generatedMessageLite); 
  }
  
  private int positionForFieldNumber(int paramInt) {
    return (paramInt >= this.minFieldNumber && paramInt <= this.maxFieldNumber) ? slowPositionForFieldNumber(paramInt, 0) : -1;
  }
  
  private int positionForFieldNumber(int paramInt1, int paramInt2) {
    return (paramInt1 >= this.minFieldNumber && paramInt1 <= this.maxFieldNumber) ? slowPositionForFieldNumber(paramInt1, paramInt2) : -1;
  }
  
  private int presenceMaskAndOffsetAt(int paramInt) {
    return this.buffer[paramInt + 2];
  }
  
  private <E> void readGroupList(Object paramObject, long paramLong, Reader paramReader, Schema<E> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    paramReader.readGroupList(this.listFieldSchema.mutableListAt(paramObject, paramLong), paramSchema, paramExtensionRegistryLite);
  }
  
  private <E> void readMessageList(Object paramObject, int paramInt, Reader paramReader, Schema<E> paramSchema, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    long l = offset(paramInt);
    paramReader.readMessageList(this.listFieldSchema.mutableListAt(paramObject, l), paramSchema, paramExtensionRegistryLite);
  }
  
  private void readString(Object paramObject, int paramInt, Reader paramReader) throws IOException {
    if (isEnforceUtf8(paramInt)) {
      UnsafeUtil.putObject(paramObject, offset(paramInt), paramReader.readStringRequireUtf8());
    } else if (this.lite) {
      UnsafeUtil.putObject(paramObject, offset(paramInt), paramReader.readString());
    } else {
      UnsafeUtil.putObject(paramObject, offset(paramInt), paramReader.readBytes());
    } 
  }
  
  private void readStringList(Object paramObject, int paramInt, Reader paramReader) throws IOException {
    if (isEnforceUtf8(paramInt)) {
      paramReader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(paramObject, offset(paramInt)));
    } else {
      paramReader.readStringList(this.listFieldSchema.mutableListAt(paramObject, offset(paramInt)));
    } 
  }
  
  private static Field reflectField(Class<?> paramClass, String paramString) {
    try {
      return paramClass.getDeclaredField(paramString);
    } catch (NoSuchFieldException noSuchFieldException) {
      for (Field field : paramClass.getDeclaredFields()) {
        if (paramString.equals(field.getName()))
          return field; 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Field ");
      stringBuilder.append(paramString);
      stringBuilder.append(" for ");
      stringBuilder.append(paramClass.getName());
      stringBuilder.append(" not found. Known fields are ");
      stringBuilder.append(Arrays.toString((Object[])noSuchFieldException));
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  private void setFieldPresent(T paramT, int paramInt) {
    if (this.proto3)
      return; 
    paramInt = presenceMaskAndOffsetAt(paramInt);
    long l = (paramInt & 0xFFFFF);
    UnsafeUtil.putInt(paramT, l, UnsafeUtil.getInt(paramT, l) | 1 << paramInt >>> 20);
  }
  
  private void setOneofPresent(T paramT, int paramInt1, int paramInt2) {
    UnsafeUtil.putInt(paramT, (presenceMaskAndOffsetAt(paramInt2) & 0xFFFFF), paramInt1);
  }
  
  private int slowPositionForFieldNumber(int paramInt1, int paramInt2) {
    int i = this.buffer.length / 3 - 1;
    while (paramInt2 <= i) {
      int j = i + paramInt2 >>> 1;
      int k = j * 3;
      int m = numberAt(k);
      if (paramInt1 == m)
        return k; 
      if (paramInt1 < m) {
        i = j - 1;
        continue;
      } 
      paramInt2 = j + 1;
    } 
    return -1;
  }
  
  private static void storeFieldData(FieldInfo paramFieldInfo, int[] paramArrayOfint, int paramInt, boolean paramBoolean, Object[] paramArrayOfObject) {
    OneofInfo oneofInfo = paramFieldInfo.getOneof();
    int i = 0;
    if (oneofInfo != null) {
      int j = paramFieldInfo.getType().id();
      int k = (int)UnsafeUtil.objectFieldOffset(oneofInfo.getValueField());
      int m = (int)UnsafeUtil.objectFieldOffset(oneofInfo.getCaseField());
      int n = j + 51;
    } else {
      byte b1;
      int k;
      byte b2;
      FieldType fieldType = paramFieldInfo.getType();
      int j = (int)UnsafeUtil.objectFieldOffset(paramFieldInfo.getField());
      int m = fieldType.id();
      if (!paramBoolean && !fieldType.isList() && !fieldType.isMap()) {
        k = (int)UnsafeUtil.objectFieldOffset(paramFieldInfo.getPresenceField());
        b1 = Integer.numberOfTrailingZeros(paramFieldInfo.getPresenceMask());
      } else if (paramFieldInfo.getCachedSizeField() == null) {
        k = 0;
        b1 = k;
      } else {
        k = (int)UnsafeUtil.objectFieldOffset(paramFieldInfo.getCachedSizeField());
        b1 = 0;
      } 
      paramArrayOfint[paramInt] = paramFieldInfo.getFieldNumber();
      if (paramFieldInfo.isEnforceUtf8()) {
        b2 = 536870912;
      } else {
        b2 = 0;
      } 
      if (paramFieldInfo.isRequired())
        i = 268435456; 
      paramArrayOfint[paramInt + 1] = i | b2 | m << 20 | j;
      paramArrayOfint[paramInt + 2] = k | b1 << 20;
      Class<?> clazz = paramFieldInfo.getMessageFieldClass();
      if (paramFieldInfo.getMapDefaultEntry() != null) {
        paramInt = paramInt / 3 * 2;
        paramArrayOfObject[paramInt] = paramFieldInfo.getMapDefaultEntry();
        if (clazz != null) {
          paramArrayOfObject[paramInt + 1] = clazz;
        } else if (paramFieldInfo.getEnumVerifier() != null) {
          paramArrayOfObject[paramInt + 1] = paramFieldInfo.getEnumVerifier();
        } 
      } else if (clazz != null) {
        paramArrayOfObject[paramInt / 3 * 2 + 1] = clazz;
      } else if (paramFieldInfo.getEnumVerifier() != null) {
        paramArrayOfObject[paramInt / 3 * 2 + 1] = paramFieldInfo.getEnumVerifier();
      } 
      return;
    } 
    boolean bool = false;
  }
  
  private static int type(int paramInt) {
    return (paramInt & 0xFF00000) >>> 20;
  }
  
  private int typeAndOffsetAt(int paramInt) {
    return this.buffer[paramInt + 1];
  }
  
  private void writeFieldsInAscendingOrderProto2(T paramT, Writer paramWriter) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: getfield hasExtensions : Z
    //   4: ifeq -> 43
    //   7: aload_0
    //   8: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   11: aload_1
    //   12: invokevirtual getExtensions : (Ljava/lang/Object;)Lcom/google/protobuf/FieldSet;
    //   15: astore_3
    //   16: aload_3
    //   17: invokevirtual isEmpty : ()Z
    //   20: ifne -> 43
    //   23: aload_3
    //   24: invokevirtual iterator : ()Ljava/util/Iterator;
    //   27: astore #4
    //   29: aload #4
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: checkcast java/util/Map$Entry
    //   39: astore_3
    //   40: goto -> 48
    //   43: aconst_null
    //   44: astore #4
    //   46: aconst_null
    //   47: astore_3
    //   48: iconst_m1
    //   49: istore #5
    //   51: aload_0
    //   52: getfield buffer : [I
    //   55: arraylength
    //   56: istore #6
    //   58: getstatic com/google/protobuf/MessageSchema.UNSAFE : Lsun/misc/Unsafe;
    //   61: astore #7
    //   63: iconst_0
    //   64: istore #8
    //   66: iconst_0
    //   67: istore #9
    //   69: aload_3
    //   70: astore #10
    //   72: iload #8
    //   74: iload #6
    //   76: if_icmpge -> 2435
    //   79: aload_0
    //   80: iload #8
    //   82: invokespecial typeAndOffsetAt : (I)I
    //   85: istore #11
    //   87: aload_0
    //   88: iload #8
    //   90: invokespecial numberAt : (I)I
    //   93: istore #12
    //   95: iload #11
    //   97: invokestatic type : (I)I
    //   100: istore #13
    //   102: aload_0
    //   103: getfield proto3 : Z
    //   106: ifne -> 175
    //   109: iload #13
    //   111: bipush #17
    //   113: if_icmpgt -> 175
    //   116: aload_0
    //   117: getfield buffer : [I
    //   120: iload #8
    //   122: iconst_2
    //   123: iadd
    //   124: iaload
    //   125: istore #14
    //   127: iload #14
    //   129: ldc 1048575
    //   131: iand
    //   132: istore #15
    //   134: iload #15
    //   136: iload #5
    //   138: if_icmpeq -> 159
    //   141: aload #7
    //   143: aload_1
    //   144: iload #15
    //   146: i2l
    //   147: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   150: istore #9
    //   152: iload #15
    //   154: istore #5
    //   156: goto -> 159
    //   159: iconst_1
    //   160: iload #14
    //   162: bipush #20
    //   164: iushr
    //   165: ishl
    //   166: istore #14
    //   168: iload #5
    //   170: istore #15
    //   172: goto -> 182
    //   175: iconst_0
    //   176: istore #14
    //   178: iload #5
    //   180: istore #15
    //   182: iload #8
    //   184: istore #5
    //   186: aload_3
    //   187: ifnull -> 241
    //   190: aload_0
    //   191: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   194: aload_3
    //   195: invokevirtual extensionNumber : (Ljava/util/Map$Entry;)I
    //   198: iload #12
    //   200: if_icmpgt -> 241
    //   203: aload_0
    //   204: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   207: aload_2
    //   208: aload_3
    //   209: invokevirtual serializeExtension : (Lcom/google/protobuf/Writer;Ljava/util/Map$Entry;)V
    //   212: aload #4
    //   214: invokeinterface hasNext : ()Z
    //   219: ifeq -> 236
    //   222: aload #4
    //   224: invokeinterface next : ()Ljava/lang/Object;
    //   229: checkcast java/util/Map$Entry
    //   232: astore_3
    //   233: goto -> 182
    //   236: aconst_null
    //   237: astore_3
    //   238: goto -> 182
    //   241: iload #11
    //   243: invokestatic offset : (I)J
    //   246: lstore #16
    //   248: iload #13
    //   250: tableswitch default -> 540, 0 -> 2400, 1 -> 2375, 2 -> 2348, 3 -> 2321, 4 -> 2294, 5 -> 2267, 6 -> 2240, 7 -> 2215, 8 -> 2189, 9 -> 2156, 10 -> 2126, 11 -> 2099, 12 -> 2072, 13 -> 2045, 14 -> 2018, 15 -> 1991, 16 -> 1964, 17 -> 1931, 18 -> 1906, 19 -> 1881, 20 -> 1856, 21 -> 1831, 22 -> 1806, 23 -> 1781, 24 -> 1756, 25 -> 1731, 26 -> 1707, 27 -> 1673, 28 -> 1649, 29 -> 1624, 30 -> 1599, 31 -> 1574, 32 -> 1549, 33 -> 1524, 34 -> 1499, 35 -> 1474, 36 -> 1449, 37 -> 1424, 38 -> 1399, 39 -> 1374, 40 -> 1349, 41 -> 1324, 42 -> 1299, 43 -> 1274, 44 -> 1249, 45 -> 1224, 46 -> 1199, 47 -> 1174, 48 -> 1149, 49 -> 1115, 50 -> 1095, 51 -> 1066, 52 -> 1037, 53 -> 1008, 54 -> 979, 55 -> 950, 56 -> 921, 57 -> 892, 58 -> 863, 59 -> 833, 60 -> 792, 61 -> 758, 62 -> 729, 63 -> 700, 64 -> 671, 65 -> 642, 66 -> 613, 67 -> 584, 68 -> 543
    //   540: goto -> 2422
    //   543: iload #5
    //   545: istore #8
    //   547: aload_0
    //   548: aload_1
    //   549: iload #12
    //   551: iload #8
    //   553: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   556: ifeq -> 540
    //   559: aload_2
    //   560: iload #12
    //   562: aload #7
    //   564: aload_1
    //   565: lload #16
    //   567: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   570: aload_0
    //   571: iload #8
    //   573: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   576: invokeinterface writeGroup : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   581: goto -> 540
    //   584: aload_0
    //   585: aload_1
    //   586: iload #12
    //   588: iload #5
    //   590: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   593: ifeq -> 540
    //   596: aload_2
    //   597: iload #12
    //   599: aload_1
    //   600: lload #16
    //   602: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   605: invokeinterface writeSInt64 : (IJ)V
    //   610: goto -> 540
    //   613: aload_0
    //   614: aload_1
    //   615: iload #12
    //   617: iload #5
    //   619: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   622: ifeq -> 540
    //   625: aload_2
    //   626: iload #12
    //   628: aload_1
    //   629: lload #16
    //   631: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   634: invokeinterface writeSInt32 : (II)V
    //   639: goto -> 540
    //   642: aload_0
    //   643: aload_1
    //   644: iload #12
    //   646: iload #5
    //   648: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   651: ifeq -> 540
    //   654: aload_2
    //   655: iload #12
    //   657: aload_1
    //   658: lload #16
    //   660: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   663: invokeinterface writeSFixed64 : (IJ)V
    //   668: goto -> 540
    //   671: aload_0
    //   672: aload_1
    //   673: iload #12
    //   675: iload #5
    //   677: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   680: ifeq -> 540
    //   683: aload_2
    //   684: iload #12
    //   686: aload_1
    //   687: lload #16
    //   689: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   692: invokeinterface writeSFixed32 : (II)V
    //   697: goto -> 540
    //   700: aload_0
    //   701: aload_1
    //   702: iload #12
    //   704: iload #5
    //   706: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   709: ifeq -> 540
    //   712: aload_2
    //   713: iload #12
    //   715: aload_1
    //   716: lload #16
    //   718: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   721: invokeinterface writeEnum : (II)V
    //   726: goto -> 540
    //   729: aload_0
    //   730: aload_1
    //   731: iload #12
    //   733: iload #5
    //   735: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   738: ifeq -> 540
    //   741: aload_2
    //   742: iload #12
    //   744: aload_1
    //   745: lload #16
    //   747: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   750: invokeinterface writeUInt32 : (II)V
    //   755: goto -> 540
    //   758: aload_0
    //   759: aload_1
    //   760: iload #12
    //   762: iload #5
    //   764: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   767: ifeq -> 540
    //   770: aload_2
    //   771: iload #12
    //   773: aload #7
    //   775: aload_1
    //   776: lload #16
    //   778: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   781: checkcast com/google/protobuf/ByteString
    //   784: invokeinterface writeBytes : (ILcom/google/protobuf/ByteString;)V
    //   789: goto -> 540
    //   792: iload #5
    //   794: istore #8
    //   796: aload_0
    //   797: aload_1
    //   798: iload #12
    //   800: iload #8
    //   802: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   805: ifeq -> 540
    //   808: aload_2
    //   809: iload #12
    //   811: aload #7
    //   813: aload_1
    //   814: lload #16
    //   816: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   819: aload_0
    //   820: iload #8
    //   822: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   825: invokeinterface writeMessage : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   830: goto -> 540
    //   833: aload_0
    //   834: aload_1
    //   835: iload #12
    //   837: iload #5
    //   839: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   842: ifeq -> 540
    //   845: aload_0
    //   846: iload #12
    //   848: aload #7
    //   850: aload_1
    //   851: lload #16
    //   853: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   856: aload_2
    //   857: invokespecial writeString : (ILjava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   860: goto -> 540
    //   863: aload_0
    //   864: aload_1
    //   865: iload #12
    //   867: iload #5
    //   869: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   872: ifeq -> 540
    //   875: aload_2
    //   876: iload #12
    //   878: aload_1
    //   879: lload #16
    //   881: invokestatic oneofBooleanAt : (Ljava/lang/Object;J)Z
    //   884: invokeinterface writeBool : (IZ)V
    //   889: goto -> 540
    //   892: aload_0
    //   893: aload_1
    //   894: iload #12
    //   896: iload #5
    //   898: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   901: ifeq -> 540
    //   904: aload_2
    //   905: iload #12
    //   907: aload_1
    //   908: lload #16
    //   910: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   913: invokeinterface writeFixed32 : (II)V
    //   918: goto -> 540
    //   921: aload_0
    //   922: aload_1
    //   923: iload #12
    //   925: iload #5
    //   927: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   930: ifeq -> 540
    //   933: aload_2
    //   934: iload #12
    //   936: aload_1
    //   937: lload #16
    //   939: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   942: invokeinterface writeFixed64 : (IJ)V
    //   947: goto -> 540
    //   950: aload_0
    //   951: aload_1
    //   952: iload #12
    //   954: iload #5
    //   956: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   959: ifeq -> 540
    //   962: aload_2
    //   963: iload #12
    //   965: aload_1
    //   966: lload #16
    //   968: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   971: invokeinterface writeInt32 : (II)V
    //   976: goto -> 540
    //   979: aload_0
    //   980: aload_1
    //   981: iload #12
    //   983: iload #5
    //   985: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   988: ifeq -> 540
    //   991: aload_2
    //   992: iload #12
    //   994: aload_1
    //   995: lload #16
    //   997: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   1000: invokeinterface writeUInt64 : (IJ)V
    //   1005: goto -> 540
    //   1008: aload_0
    //   1009: aload_1
    //   1010: iload #12
    //   1012: iload #5
    //   1014: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   1017: ifeq -> 540
    //   1020: aload_2
    //   1021: iload #12
    //   1023: aload_1
    //   1024: lload #16
    //   1026: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   1029: invokeinterface writeInt64 : (IJ)V
    //   1034: goto -> 540
    //   1037: aload_0
    //   1038: aload_1
    //   1039: iload #12
    //   1041: iload #5
    //   1043: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   1046: ifeq -> 540
    //   1049: aload_2
    //   1050: iload #12
    //   1052: aload_1
    //   1053: lload #16
    //   1055: invokestatic oneofFloatAt : (Ljava/lang/Object;J)F
    //   1058: invokeinterface writeFloat : (IF)V
    //   1063: goto -> 540
    //   1066: aload_0
    //   1067: aload_1
    //   1068: iload #12
    //   1070: iload #5
    //   1072: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   1075: ifeq -> 540
    //   1078: aload_2
    //   1079: iload #12
    //   1081: aload_1
    //   1082: lload #16
    //   1084: invokestatic oneofDoubleAt : (Ljava/lang/Object;J)D
    //   1087: invokeinterface writeDouble : (ID)V
    //   1092: goto -> 540
    //   1095: aload_0
    //   1096: aload_2
    //   1097: iload #12
    //   1099: aload #7
    //   1101: aload_1
    //   1102: lload #16
    //   1104: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1107: iload #5
    //   1109: invokespecial writeMapHelper : (Lcom/google/protobuf/Writer;ILjava/lang/Object;I)V
    //   1112: goto -> 540
    //   1115: iload #5
    //   1117: istore #8
    //   1119: aload_0
    //   1120: iload #8
    //   1122: invokespecial numberAt : (I)I
    //   1125: aload #7
    //   1127: aload_1
    //   1128: lload #16
    //   1130: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1133: checkcast java/util/List
    //   1136: aload_2
    //   1137: aload_0
    //   1138: iload #8
    //   1140: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1143: invokestatic writeGroupList : (ILjava/util/List;Lcom/google/protobuf/Writer;Lcom/google/protobuf/Schema;)V
    //   1146: goto -> 540
    //   1149: aload_0
    //   1150: iload #5
    //   1152: invokespecial numberAt : (I)I
    //   1155: aload #7
    //   1157: aload_1
    //   1158: lload #16
    //   1160: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1163: checkcast java/util/List
    //   1166: aload_2
    //   1167: iconst_1
    //   1168: invokestatic writeSInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1171: goto -> 540
    //   1174: aload_0
    //   1175: iload #5
    //   1177: invokespecial numberAt : (I)I
    //   1180: aload #7
    //   1182: aload_1
    //   1183: lload #16
    //   1185: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1188: checkcast java/util/List
    //   1191: aload_2
    //   1192: iconst_1
    //   1193: invokestatic writeSInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1196: goto -> 540
    //   1199: aload_0
    //   1200: iload #5
    //   1202: invokespecial numberAt : (I)I
    //   1205: aload #7
    //   1207: aload_1
    //   1208: lload #16
    //   1210: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1213: checkcast java/util/List
    //   1216: aload_2
    //   1217: iconst_1
    //   1218: invokestatic writeSFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1221: goto -> 540
    //   1224: aload_0
    //   1225: iload #5
    //   1227: invokespecial numberAt : (I)I
    //   1230: aload #7
    //   1232: aload_1
    //   1233: lload #16
    //   1235: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1238: checkcast java/util/List
    //   1241: aload_2
    //   1242: iconst_1
    //   1243: invokestatic writeSFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1246: goto -> 540
    //   1249: aload_0
    //   1250: iload #5
    //   1252: invokespecial numberAt : (I)I
    //   1255: aload #7
    //   1257: aload_1
    //   1258: lload #16
    //   1260: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1263: checkcast java/util/List
    //   1266: aload_2
    //   1267: iconst_1
    //   1268: invokestatic writeEnumList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1271: goto -> 540
    //   1274: aload_0
    //   1275: iload #5
    //   1277: invokespecial numberAt : (I)I
    //   1280: aload #7
    //   1282: aload_1
    //   1283: lload #16
    //   1285: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1288: checkcast java/util/List
    //   1291: aload_2
    //   1292: iconst_1
    //   1293: invokestatic writeUInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1296: goto -> 540
    //   1299: aload_0
    //   1300: iload #5
    //   1302: invokespecial numberAt : (I)I
    //   1305: aload #7
    //   1307: aload_1
    //   1308: lload #16
    //   1310: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1313: checkcast java/util/List
    //   1316: aload_2
    //   1317: iconst_1
    //   1318: invokestatic writeBoolList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1321: goto -> 540
    //   1324: aload_0
    //   1325: iload #5
    //   1327: invokespecial numberAt : (I)I
    //   1330: aload #7
    //   1332: aload_1
    //   1333: lload #16
    //   1335: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1338: checkcast java/util/List
    //   1341: aload_2
    //   1342: iconst_1
    //   1343: invokestatic writeFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1346: goto -> 540
    //   1349: aload_0
    //   1350: iload #5
    //   1352: invokespecial numberAt : (I)I
    //   1355: aload #7
    //   1357: aload_1
    //   1358: lload #16
    //   1360: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1363: checkcast java/util/List
    //   1366: aload_2
    //   1367: iconst_1
    //   1368: invokestatic writeFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1371: goto -> 540
    //   1374: aload_0
    //   1375: iload #5
    //   1377: invokespecial numberAt : (I)I
    //   1380: aload #7
    //   1382: aload_1
    //   1383: lload #16
    //   1385: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1388: checkcast java/util/List
    //   1391: aload_2
    //   1392: iconst_1
    //   1393: invokestatic writeInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1396: goto -> 540
    //   1399: aload_0
    //   1400: iload #5
    //   1402: invokespecial numberAt : (I)I
    //   1405: aload #7
    //   1407: aload_1
    //   1408: lload #16
    //   1410: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1413: checkcast java/util/List
    //   1416: aload_2
    //   1417: iconst_1
    //   1418: invokestatic writeUInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1421: goto -> 540
    //   1424: aload_0
    //   1425: iload #5
    //   1427: invokespecial numberAt : (I)I
    //   1430: aload #7
    //   1432: aload_1
    //   1433: lload #16
    //   1435: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1438: checkcast java/util/List
    //   1441: aload_2
    //   1442: iconst_1
    //   1443: invokestatic writeInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1446: goto -> 540
    //   1449: aload_0
    //   1450: iload #5
    //   1452: invokespecial numberAt : (I)I
    //   1455: aload #7
    //   1457: aload_1
    //   1458: lload #16
    //   1460: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1463: checkcast java/util/List
    //   1466: aload_2
    //   1467: iconst_1
    //   1468: invokestatic writeFloatList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1471: goto -> 540
    //   1474: aload_0
    //   1475: iload #5
    //   1477: invokespecial numberAt : (I)I
    //   1480: aload #7
    //   1482: aload_1
    //   1483: lload #16
    //   1485: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1488: checkcast java/util/List
    //   1491: aload_2
    //   1492: iconst_1
    //   1493: invokestatic writeDoubleList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1496: goto -> 540
    //   1499: aload_0
    //   1500: iload #5
    //   1502: invokespecial numberAt : (I)I
    //   1505: aload #7
    //   1507: aload_1
    //   1508: lload #16
    //   1510: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1513: checkcast java/util/List
    //   1516: aload_2
    //   1517: iconst_0
    //   1518: invokestatic writeSInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1521: goto -> 1646
    //   1524: aload_0
    //   1525: iload #5
    //   1527: invokespecial numberAt : (I)I
    //   1530: aload #7
    //   1532: aload_1
    //   1533: lload #16
    //   1535: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1538: checkcast java/util/List
    //   1541: aload_2
    //   1542: iconst_0
    //   1543: invokestatic writeSInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1546: goto -> 1646
    //   1549: aload_0
    //   1550: iload #5
    //   1552: invokespecial numberAt : (I)I
    //   1555: aload #7
    //   1557: aload_1
    //   1558: lload #16
    //   1560: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1563: checkcast java/util/List
    //   1566: aload_2
    //   1567: iconst_0
    //   1568: invokestatic writeSFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1571: goto -> 1646
    //   1574: aload_0
    //   1575: iload #5
    //   1577: invokespecial numberAt : (I)I
    //   1580: aload #7
    //   1582: aload_1
    //   1583: lload #16
    //   1585: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1588: checkcast java/util/List
    //   1591: aload_2
    //   1592: iconst_0
    //   1593: invokestatic writeSFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1596: goto -> 1646
    //   1599: aload_0
    //   1600: iload #5
    //   1602: invokespecial numberAt : (I)I
    //   1605: aload #7
    //   1607: aload_1
    //   1608: lload #16
    //   1610: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1613: checkcast java/util/List
    //   1616: aload_2
    //   1617: iconst_0
    //   1618: invokestatic writeEnumList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1621: goto -> 1646
    //   1624: aload_0
    //   1625: iload #5
    //   1627: invokespecial numberAt : (I)I
    //   1630: aload #7
    //   1632: aload_1
    //   1633: lload #16
    //   1635: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1638: checkcast java/util/List
    //   1641: aload_2
    //   1642: iconst_0
    //   1643: invokestatic writeUInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1646: goto -> 2422
    //   1649: aload_0
    //   1650: iload #5
    //   1652: invokespecial numberAt : (I)I
    //   1655: aload #7
    //   1657: aload_1
    //   1658: lload #16
    //   1660: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1663: checkcast java/util/List
    //   1666: aload_2
    //   1667: invokestatic writeBytesList : (ILjava/util/List;Lcom/google/protobuf/Writer;)V
    //   1670: goto -> 540
    //   1673: iload #5
    //   1675: istore #8
    //   1677: aload_0
    //   1678: iload #8
    //   1680: invokespecial numberAt : (I)I
    //   1683: aload #7
    //   1685: aload_1
    //   1686: lload #16
    //   1688: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1691: checkcast java/util/List
    //   1694: aload_2
    //   1695: aload_0
    //   1696: iload #8
    //   1698: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1701: invokestatic writeMessageList : (ILjava/util/List;Lcom/google/protobuf/Writer;Lcom/google/protobuf/Schema;)V
    //   1704: goto -> 540
    //   1707: aload_0
    //   1708: iload #5
    //   1710: invokespecial numberAt : (I)I
    //   1713: aload #7
    //   1715: aload_1
    //   1716: lload #16
    //   1718: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1721: checkcast java/util/List
    //   1724: aload_2
    //   1725: invokestatic writeStringList : (ILjava/util/List;Lcom/google/protobuf/Writer;)V
    //   1728: goto -> 540
    //   1731: aload_0
    //   1732: iload #5
    //   1734: invokespecial numberAt : (I)I
    //   1737: aload #7
    //   1739: aload_1
    //   1740: lload #16
    //   1742: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1745: checkcast java/util/List
    //   1748: aload_2
    //   1749: iconst_0
    //   1750: invokestatic writeBoolList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1753: goto -> 2422
    //   1756: aload_0
    //   1757: iload #5
    //   1759: invokespecial numberAt : (I)I
    //   1762: aload #7
    //   1764: aload_1
    //   1765: lload #16
    //   1767: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1770: checkcast java/util/List
    //   1773: aload_2
    //   1774: iconst_0
    //   1775: invokestatic writeFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1778: goto -> 2422
    //   1781: aload_0
    //   1782: iload #5
    //   1784: invokespecial numberAt : (I)I
    //   1787: aload #7
    //   1789: aload_1
    //   1790: lload #16
    //   1792: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1795: checkcast java/util/List
    //   1798: aload_2
    //   1799: iconst_0
    //   1800: invokestatic writeFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1803: goto -> 2422
    //   1806: aload_0
    //   1807: iload #5
    //   1809: invokespecial numberAt : (I)I
    //   1812: aload #7
    //   1814: aload_1
    //   1815: lload #16
    //   1817: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1820: checkcast java/util/List
    //   1823: aload_2
    //   1824: iconst_0
    //   1825: invokestatic writeInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1828: goto -> 2422
    //   1831: aload_0
    //   1832: iload #5
    //   1834: invokespecial numberAt : (I)I
    //   1837: aload #7
    //   1839: aload_1
    //   1840: lload #16
    //   1842: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1845: checkcast java/util/List
    //   1848: aload_2
    //   1849: iconst_0
    //   1850: invokestatic writeUInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1853: goto -> 2422
    //   1856: aload_0
    //   1857: iload #5
    //   1859: invokespecial numberAt : (I)I
    //   1862: aload #7
    //   1864: aload_1
    //   1865: lload #16
    //   1867: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1870: checkcast java/util/List
    //   1873: aload_2
    //   1874: iconst_0
    //   1875: invokestatic writeInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1878: goto -> 2422
    //   1881: aload_0
    //   1882: iload #5
    //   1884: invokespecial numberAt : (I)I
    //   1887: aload #7
    //   1889: aload_1
    //   1890: lload #16
    //   1892: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1895: checkcast java/util/List
    //   1898: aload_2
    //   1899: iconst_0
    //   1900: invokestatic writeFloatList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1903: goto -> 2422
    //   1906: aload_0
    //   1907: iload #5
    //   1909: invokespecial numberAt : (I)I
    //   1912: aload #7
    //   1914: aload_1
    //   1915: lload #16
    //   1917: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1920: checkcast java/util/List
    //   1923: aload_2
    //   1924: iconst_0
    //   1925: invokestatic writeDoubleList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1928: goto -> 2422
    //   1931: iload #14
    //   1933: iload #9
    //   1935: iand
    //   1936: ifeq -> 2422
    //   1939: aload_2
    //   1940: iload #12
    //   1942: aload #7
    //   1944: aload_1
    //   1945: lload #16
    //   1947: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1950: aload_0
    //   1951: iload #5
    //   1953: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1956: invokeinterface writeGroup : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   1961: goto -> 2422
    //   1964: iload #14
    //   1966: iload #9
    //   1968: iand
    //   1969: ifeq -> 2422
    //   1972: aload_2
    //   1973: iload #12
    //   1975: aload #7
    //   1977: aload_1
    //   1978: lload #16
    //   1980: invokevirtual getLong : (Ljava/lang/Object;J)J
    //   1983: invokeinterface writeSInt64 : (IJ)V
    //   1988: goto -> 2422
    //   1991: iload #14
    //   1993: iload #9
    //   1995: iand
    //   1996: ifeq -> 2422
    //   1999: aload_2
    //   2000: iload #12
    //   2002: aload #7
    //   2004: aload_1
    //   2005: lload #16
    //   2007: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   2010: invokeinterface writeSInt32 : (II)V
    //   2015: goto -> 2422
    //   2018: iload #14
    //   2020: iload #9
    //   2022: iand
    //   2023: ifeq -> 2422
    //   2026: aload_2
    //   2027: iload #12
    //   2029: aload #7
    //   2031: aload_1
    //   2032: lload #16
    //   2034: invokevirtual getLong : (Ljava/lang/Object;J)J
    //   2037: invokeinterface writeSFixed64 : (IJ)V
    //   2042: goto -> 2422
    //   2045: iload #14
    //   2047: iload #9
    //   2049: iand
    //   2050: ifeq -> 2422
    //   2053: aload_2
    //   2054: iload #12
    //   2056: aload #7
    //   2058: aload_1
    //   2059: lload #16
    //   2061: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   2064: invokeinterface writeSFixed32 : (II)V
    //   2069: goto -> 2422
    //   2072: iload #14
    //   2074: iload #9
    //   2076: iand
    //   2077: ifeq -> 2422
    //   2080: aload_2
    //   2081: iload #12
    //   2083: aload #7
    //   2085: aload_1
    //   2086: lload #16
    //   2088: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   2091: invokeinterface writeEnum : (II)V
    //   2096: goto -> 2422
    //   2099: iload #14
    //   2101: iload #9
    //   2103: iand
    //   2104: ifeq -> 2422
    //   2107: aload_2
    //   2108: iload #12
    //   2110: aload #7
    //   2112: aload_1
    //   2113: lload #16
    //   2115: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   2118: invokeinterface writeUInt32 : (II)V
    //   2123: goto -> 2422
    //   2126: iload #14
    //   2128: iload #9
    //   2130: iand
    //   2131: ifeq -> 2422
    //   2134: aload_2
    //   2135: iload #12
    //   2137: aload #7
    //   2139: aload_1
    //   2140: lload #16
    //   2142: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2145: checkcast com/google/protobuf/ByteString
    //   2148: invokeinterface writeBytes : (ILcom/google/protobuf/ByteString;)V
    //   2153: goto -> 2422
    //   2156: iload #14
    //   2158: iload #9
    //   2160: iand
    //   2161: ifeq -> 2422
    //   2164: aload_2
    //   2165: iload #12
    //   2167: aload #7
    //   2169: aload_1
    //   2170: lload #16
    //   2172: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2175: aload_0
    //   2176: iload #5
    //   2178: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   2181: invokeinterface writeMessage : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   2186: goto -> 2422
    //   2189: iload #14
    //   2191: iload #9
    //   2193: iand
    //   2194: ifeq -> 2422
    //   2197: aload_0
    //   2198: iload #12
    //   2200: aload #7
    //   2202: aload_1
    //   2203: lload #16
    //   2205: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2208: aload_2
    //   2209: invokespecial writeString : (ILjava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   2212: goto -> 2422
    //   2215: iload #14
    //   2217: iload #9
    //   2219: iand
    //   2220: ifeq -> 2422
    //   2223: aload_2
    //   2224: iload #12
    //   2226: aload_1
    //   2227: lload #16
    //   2229: invokestatic booleanAt : (Ljava/lang/Object;J)Z
    //   2232: invokeinterface writeBool : (IZ)V
    //   2237: goto -> 2422
    //   2240: iload #14
    //   2242: iload #9
    //   2244: iand
    //   2245: ifeq -> 2422
    //   2248: aload_2
    //   2249: iload #12
    //   2251: aload #7
    //   2253: aload_1
    //   2254: lload #16
    //   2256: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   2259: invokeinterface writeFixed32 : (II)V
    //   2264: goto -> 2422
    //   2267: iload #14
    //   2269: iload #9
    //   2271: iand
    //   2272: ifeq -> 2422
    //   2275: aload_2
    //   2276: iload #12
    //   2278: aload #7
    //   2280: aload_1
    //   2281: lload #16
    //   2283: invokevirtual getLong : (Ljava/lang/Object;J)J
    //   2286: invokeinterface writeFixed64 : (IJ)V
    //   2291: goto -> 2422
    //   2294: iload #14
    //   2296: iload #9
    //   2298: iand
    //   2299: ifeq -> 2422
    //   2302: aload_2
    //   2303: iload #12
    //   2305: aload #7
    //   2307: aload_1
    //   2308: lload #16
    //   2310: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   2313: invokeinterface writeInt32 : (II)V
    //   2318: goto -> 2422
    //   2321: iload #14
    //   2323: iload #9
    //   2325: iand
    //   2326: ifeq -> 2422
    //   2329: aload_2
    //   2330: iload #12
    //   2332: aload #7
    //   2334: aload_1
    //   2335: lload #16
    //   2337: invokevirtual getLong : (Ljava/lang/Object;J)J
    //   2340: invokeinterface writeUInt64 : (IJ)V
    //   2345: goto -> 2422
    //   2348: iload #14
    //   2350: iload #9
    //   2352: iand
    //   2353: ifeq -> 2422
    //   2356: aload_2
    //   2357: iload #12
    //   2359: aload #7
    //   2361: aload_1
    //   2362: lload #16
    //   2364: invokevirtual getLong : (Ljava/lang/Object;J)J
    //   2367: invokeinterface writeInt64 : (IJ)V
    //   2372: goto -> 2422
    //   2375: iload #14
    //   2377: iload #9
    //   2379: iand
    //   2380: ifeq -> 2422
    //   2383: aload_2
    //   2384: iload #12
    //   2386: aload_1
    //   2387: lload #16
    //   2389: invokestatic floatAt : (Ljava/lang/Object;J)F
    //   2392: invokeinterface writeFloat : (IF)V
    //   2397: goto -> 2422
    //   2400: iload #14
    //   2402: iload #9
    //   2404: iand
    //   2405: ifeq -> 2422
    //   2408: aload_2
    //   2409: iload #12
    //   2411: aload_1
    //   2412: lload #16
    //   2414: invokestatic doubleAt : (Ljava/lang/Object;J)D
    //   2417: invokeinterface writeDouble : (ID)V
    //   2422: iload #5
    //   2424: iconst_3
    //   2425: iadd
    //   2426: istore #8
    //   2428: iload #15
    //   2430: istore #5
    //   2432: goto -> 69
    //   2435: aload #10
    //   2437: ifnull -> 2481
    //   2440: aload_0
    //   2441: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   2444: aload_2
    //   2445: aload #10
    //   2447: invokevirtual serializeExtension : (Lcom/google/protobuf/Writer;Ljava/util/Map$Entry;)V
    //   2450: aload #4
    //   2452: invokeinterface hasNext : ()Z
    //   2457: ifeq -> 2475
    //   2460: aload #4
    //   2462: invokeinterface next : ()Ljava/lang/Object;
    //   2467: checkcast java/util/Map$Entry
    //   2470: astore #10
    //   2472: goto -> 2435
    //   2475: aconst_null
    //   2476: astore #10
    //   2478: goto -> 2435
    //   2481: aload_0
    //   2482: aload_0
    //   2483: getfield unknownFieldSchema : Lcom/google/protobuf/UnknownFieldSchema;
    //   2486: aload_1
    //   2487: aload_2
    //   2488: invokespecial writeUnknownInMessageTo : (Lcom/google/protobuf/UnknownFieldSchema;Ljava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   2491: return
  }
  
  private void writeFieldsInAscendingOrderProto3(T paramT, Writer paramWriter) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: getfield hasExtensions : Z
    //   4: ifeq -> 43
    //   7: aload_0
    //   8: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   11: aload_1
    //   12: invokevirtual getExtensions : (Ljava/lang/Object;)Lcom/google/protobuf/FieldSet;
    //   15: astore_3
    //   16: aload_3
    //   17: invokevirtual isEmpty : ()Z
    //   20: ifne -> 43
    //   23: aload_3
    //   24: invokevirtual iterator : ()Ljava/util/Iterator;
    //   27: astore #4
    //   29: aload #4
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: checkcast java/util/Map$Entry
    //   39: astore_3
    //   40: goto -> 49
    //   43: aconst_null
    //   44: astore #4
    //   46: aload #4
    //   48: astore_3
    //   49: aload_0
    //   50: getfield buffer : [I
    //   53: arraylength
    //   54: istore #5
    //   56: iconst_0
    //   57: istore #6
    //   59: aload_3
    //   60: astore #7
    //   62: aload #7
    //   64: astore_3
    //   65: iload #6
    //   67: iload #5
    //   69: if_icmpge -> 2455
    //   72: aload_0
    //   73: iload #6
    //   75: invokespecial typeAndOffsetAt : (I)I
    //   78: istore #8
    //   80: aload_0
    //   81: iload #6
    //   83: invokespecial numberAt : (I)I
    //   86: istore #9
    //   88: aload #7
    //   90: ifnull -> 148
    //   93: aload_0
    //   94: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   97: aload #7
    //   99: invokevirtual extensionNumber : (Ljava/util/Map$Entry;)I
    //   102: iload #9
    //   104: if_icmpgt -> 148
    //   107: aload_0
    //   108: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   111: aload_2
    //   112: aload #7
    //   114: invokevirtual serializeExtension : (Lcom/google/protobuf/Writer;Ljava/util/Map$Entry;)V
    //   117: aload #4
    //   119: invokeinterface hasNext : ()Z
    //   124: ifeq -> 142
    //   127: aload #4
    //   129: invokeinterface next : ()Ljava/lang/Object;
    //   134: checkcast java/util/Map$Entry
    //   137: astore #7
    //   139: goto -> 88
    //   142: aconst_null
    //   143: astore #7
    //   145: goto -> 88
    //   148: iload #8
    //   150: invokestatic type : (I)I
    //   153: tableswitch default -> 444, 0 -> 2422, 1 -> 2392, 2 -> 2362, 3 -> 2332, 4 -> 2302, 5 -> 2272, 6 -> 2242, 7 -> 2212, 8 -> 2183, 9 -> 2147, 10 -> 2114, 11 -> 2084, 12 -> 2054, 13 -> 2024, 14 -> 1994, 15 -> 1964, 16 -> 1934, 17 -> 1898, 18 -> 1872, 19 -> 1846, 20 -> 1820, 21 -> 1794, 22 -> 1768, 23 -> 1742, 24 -> 1716, 25 -> 1690, 26 -> 1665, 27 -> 1634, 28 -> 1609, 29 -> 1583, 30 -> 1557, 31 -> 1531, 32 -> 1505, 33 -> 1479, 34 -> 1453, 35 -> 1427, 36 -> 1401, 37 -> 1375, 38 -> 1349, 39 -> 1323, 40 -> 1297, 41 -> 1271, 42 -> 1245, 43 -> 1219, 44 -> 1193, 45 -> 1167, 46 -> 1141, 47 -> 1115, 48 -> 1089, 49 -> 1058, 50 -> 1037, 51 -> 1005, 52 -> 973, 53 -> 941, 54 -> 909, 55 -> 877, 56 -> 845, 57 -> 813, 58 -> 781, 59 -> 750, 60 -> 712, 61 -> 677, 62 -> 645, 63 -> 613, 64 -> 581, 65 -> 549, 66 -> 517, 67 -> 485, 68 -> 447
    //   444: goto -> 2449
    //   447: aload_0
    //   448: aload_1
    //   449: iload #9
    //   451: iload #6
    //   453: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   456: ifeq -> 2449
    //   459: aload_2
    //   460: iload #9
    //   462: aload_1
    //   463: iload #8
    //   465: invokestatic offset : (I)J
    //   468: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   471: aload_0
    //   472: iload #6
    //   474: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   477: invokeinterface writeGroup : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   482: goto -> 2449
    //   485: aload_0
    //   486: aload_1
    //   487: iload #9
    //   489: iload #6
    //   491: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   494: ifeq -> 2449
    //   497: aload_2
    //   498: iload #9
    //   500: aload_1
    //   501: iload #8
    //   503: invokestatic offset : (I)J
    //   506: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   509: invokeinterface writeSInt64 : (IJ)V
    //   514: goto -> 2449
    //   517: aload_0
    //   518: aload_1
    //   519: iload #9
    //   521: iload #6
    //   523: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   526: ifeq -> 2449
    //   529: aload_2
    //   530: iload #9
    //   532: aload_1
    //   533: iload #8
    //   535: invokestatic offset : (I)J
    //   538: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   541: invokeinterface writeSInt32 : (II)V
    //   546: goto -> 2449
    //   549: aload_0
    //   550: aload_1
    //   551: iload #9
    //   553: iload #6
    //   555: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   558: ifeq -> 2449
    //   561: aload_2
    //   562: iload #9
    //   564: aload_1
    //   565: iload #8
    //   567: invokestatic offset : (I)J
    //   570: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   573: invokeinterface writeSFixed64 : (IJ)V
    //   578: goto -> 2449
    //   581: aload_0
    //   582: aload_1
    //   583: iload #9
    //   585: iload #6
    //   587: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   590: ifeq -> 2449
    //   593: aload_2
    //   594: iload #9
    //   596: aload_1
    //   597: iload #8
    //   599: invokestatic offset : (I)J
    //   602: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   605: invokeinterface writeSFixed32 : (II)V
    //   610: goto -> 2449
    //   613: aload_0
    //   614: aload_1
    //   615: iload #9
    //   617: iload #6
    //   619: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   622: ifeq -> 2449
    //   625: aload_2
    //   626: iload #9
    //   628: aload_1
    //   629: iload #8
    //   631: invokestatic offset : (I)J
    //   634: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   637: invokeinterface writeEnum : (II)V
    //   642: goto -> 2449
    //   645: aload_0
    //   646: aload_1
    //   647: iload #9
    //   649: iload #6
    //   651: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   654: ifeq -> 2449
    //   657: aload_2
    //   658: iload #9
    //   660: aload_1
    //   661: iload #8
    //   663: invokestatic offset : (I)J
    //   666: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   669: invokeinterface writeUInt32 : (II)V
    //   674: goto -> 2449
    //   677: aload_0
    //   678: aload_1
    //   679: iload #9
    //   681: iload #6
    //   683: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   686: ifeq -> 2449
    //   689: aload_2
    //   690: iload #9
    //   692: aload_1
    //   693: iload #8
    //   695: invokestatic offset : (I)J
    //   698: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   701: checkcast com/google/protobuf/ByteString
    //   704: invokeinterface writeBytes : (ILcom/google/protobuf/ByteString;)V
    //   709: goto -> 2449
    //   712: aload_0
    //   713: aload_1
    //   714: iload #9
    //   716: iload #6
    //   718: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   721: ifeq -> 2449
    //   724: aload_2
    //   725: iload #9
    //   727: aload_1
    //   728: iload #8
    //   730: invokestatic offset : (I)J
    //   733: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   736: aload_0
    //   737: iload #6
    //   739: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   742: invokeinterface writeMessage : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   747: goto -> 2449
    //   750: aload_0
    //   751: aload_1
    //   752: iload #9
    //   754: iload #6
    //   756: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   759: ifeq -> 2449
    //   762: aload_0
    //   763: iload #9
    //   765: aload_1
    //   766: iload #8
    //   768: invokestatic offset : (I)J
    //   771: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   774: aload_2
    //   775: invokespecial writeString : (ILjava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   778: goto -> 2449
    //   781: aload_0
    //   782: aload_1
    //   783: iload #9
    //   785: iload #6
    //   787: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   790: ifeq -> 2449
    //   793: aload_2
    //   794: iload #9
    //   796: aload_1
    //   797: iload #8
    //   799: invokestatic offset : (I)J
    //   802: invokestatic oneofBooleanAt : (Ljava/lang/Object;J)Z
    //   805: invokeinterface writeBool : (IZ)V
    //   810: goto -> 2449
    //   813: aload_0
    //   814: aload_1
    //   815: iload #9
    //   817: iload #6
    //   819: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   822: ifeq -> 2449
    //   825: aload_2
    //   826: iload #9
    //   828: aload_1
    //   829: iload #8
    //   831: invokestatic offset : (I)J
    //   834: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   837: invokeinterface writeFixed32 : (II)V
    //   842: goto -> 2449
    //   845: aload_0
    //   846: aload_1
    //   847: iload #9
    //   849: iload #6
    //   851: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   854: ifeq -> 2449
    //   857: aload_2
    //   858: iload #9
    //   860: aload_1
    //   861: iload #8
    //   863: invokestatic offset : (I)J
    //   866: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   869: invokeinterface writeFixed64 : (IJ)V
    //   874: goto -> 2449
    //   877: aload_0
    //   878: aload_1
    //   879: iload #9
    //   881: iload #6
    //   883: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   886: ifeq -> 2449
    //   889: aload_2
    //   890: iload #9
    //   892: aload_1
    //   893: iload #8
    //   895: invokestatic offset : (I)J
    //   898: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   901: invokeinterface writeInt32 : (II)V
    //   906: goto -> 2449
    //   909: aload_0
    //   910: aload_1
    //   911: iload #9
    //   913: iload #6
    //   915: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   918: ifeq -> 2449
    //   921: aload_2
    //   922: iload #9
    //   924: aload_1
    //   925: iload #8
    //   927: invokestatic offset : (I)J
    //   930: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   933: invokeinterface writeUInt64 : (IJ)V
    //   938: goto -> 2449
    //   941: aload_0
    //   942: aload_1
    //   943: iload #9
    //   945: iload #6
    //   947: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   950: ifeq -> 2449
    //   953: aload_2
    //   954: iload #9
    //   956: aload_1
    //   957: iload #8
    //   959: invokestatic offset : (I)J
    //   962: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   965: invokeinterface writeInt64 : (IJ)V
    //   970: goto -> 2449
    //   973: aload_0
    //   974: aload_1
    //   975: iload #9
    //   977: iload #6
    //   979: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   982: ifeq -> 2449
    //   985: aload_2
    //   986: iload #9
    //   988: aload_1
    //   989: iload #8
    //   991: invokestatic offset : (I)J
    //   994: invokestatic oneofFloatAt : (Ljava/lang/Object;J)F
    //   997: invokeinterface writeFloat : (IF)V
    //   1002: goto -> 2449
    //   1005: aload_0
    //   1006: aload_1
    //   1007: iload #9
    //   1009: iload #6
    //   1011: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   1014: ifeq -> 2449
    //   1017: aload_2
    //   1018: iload #9
    //   1020: aload_1
    //   1021: iload #8
    //   1023: invokestatic offset : (I)J
    //   1026: invokestatic oneofDoubleAt : (Ljava/lang/Object;J)D
    //   1029: invokeinterface writeDouble : (ID)V
    //   1034: goto -> 2449
    //   1037: aload_0
    //   1038: aload_2
    //   1039: iload #9
    //   1041: aload_1
    //   1042: iload #8
    //   1044: invokestatic offset : (I)J
    //   1047: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1050: iload #6
    //   1052: invokespecial writeMapHelper : (Lcom/google/protobuf/Writer;ILjava/lang/Object;I)V
    //   1055: goto -> 2449
    //   1058: aload_0
    //   1059: iload #6
    //   1061: invokespecial numberAt : (I)I
    //   1064: aload_1
    //   1065: iload #8
    //   1067: invokestatic offset : (I)J
    //   1070: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1073: checkcast java/util/List
    //   1076: aload_2
    //   1077: aload_0
    //   1078: iload #6
    //   1080: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1083: invokestatic writeGroupList : (ILjava/util/List;Lcom/google/protobuf/Writer;Lcom/google/protobuf/Schema;)V
    //   1086: goto -> 2449
    //   1089: aload_0
    //   1090: iload #6
    //   1092: invokespecial numberAt : (I)I
    //   1095: aload_1
    //   1096: iload #8
    //   1098: invokestatic offset : (I)J
    //   1101: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1104: checkcast java/util/List
    //   1107: aload_2
    //   1108: iconst_1
    //   1109: invokestatic writeSInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1112: goto -> 2449
    //   1115: aload_0
    //   1116: iload #6
    //   1118: invokespecial numberAt : (I)I
    //   1121: aload_1
    //   1122: iload #8
    //   1124: invokestatic offset : (I)J
    //   1127: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1130: checkcast java/util/List
    //   1133: aload_2
    //   1134: iconst_1
    //   1135: invokestatic writeSInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1138: goto -> 2449
    //   1141: aload_0
    //   1142: iload #6
    //   1144: invokespecial numberAt : (I)I
    //   1147: aload_1
    //   1148: iload #8
    //   1150: invokestatic offset : (I)J
    //   1153: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1156: checkcast java/util/List
    //   1159: aload_2
    //   1160: iconst_1
    //   1161: invokestatic writeSFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1164: goto -> 2449
    //   1167: aload_0
    //   1168: iload #6
    //   1170: invokespecial numberAt : (I)I
    //   1173: aload_1
    //   1174: iload #8
    //   1176: invokestatic offset : (I)J
    //   1179: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1182: checkcast java/util/List
    //   1185: aload_2
    //   1186: iconst_1
    //   1187: invokestatic writeSFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1190: goto -> 2449
    //   1193: aload_0
    //   1194: iload #6
    //   1196: invokespecial numberAt : (I)I
    //   1199: aload_1
    //   1200: iload #8
    //   1202: invokestatic offset : (I)J
    //   1205: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1208: checkcast java/util/List
    //   1211: aload_2
    //   1212: iconst_1
    //   1213: invokestatic writeEnumList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1216: goto -> 2449
    //   1219: aload_0
    //   1220: iload #6
    //   1222: invokespecial numberAt : (I)I
    //   1225: aload_1
    //   1226: iload #8
    //   1228: invokestatic offset : (I)J
    //   1231: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1234: checkcast java/util/List
    //   1237: aload_2
    //   1238: iconst_1
    //   1239: invokestatic writeUInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1242: goto -> 2449
    //   1245: aload_0
    //   1246: iload #6
    //   1248: invokespecial numberAt : (I)I
    //   1251: aload_1
    //   1252: iload #8
    //   1254: invokestatic offset : (I)J
    //   1257: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1260: checkcast java/util/List
    //   1263: aload_2
    //   1264: iconst_1
    //   1265: invokestatic writeBoolList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1268: goto -> 2449
    //   1271: aload_0
    //   1272: iload #6
    //   1274: invokespecial numberAt : (I)I
    //   1277: aload_1
    //   1278: iload #8
    //   1280: invokestatic offset : (I)J
    //   1283: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1286: checkcast java/util/List
    //   1289: aload_2
    //   1290: iconst_1
    //   1291: invokestatic writeFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1294: goto -> 2449
    //   1297: aload_0
    //   1298: iload #6
    //   1300: invokespecial numberAt : (I)I
    //   1303: aload_1
    //   1304: iload #8
    //   1306: invokestatic offset : (I)J
    //   1309: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1312: checkcast java/util/List
    //   1315: aload_2
    //   1316: iconst_1
    //   1317: invokestatic writeFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1320: goto -> 2449
    //   1323: aload_0
    //   1324: iload #6
    //   1326: invokespecial numberAt : (I)I
    //   1329: aload_1
    //   1330: iload #8
    //   1332: invokestatic offset : (I)J
    //   1335: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1338: checkcast java/util/List
    //   1341: aload_2
    //   1342: iconst_1
    //   1343: invokestatic writeInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1346: goto -> 2449
    //   1349: aload_0
    //   1350: iload #6
    //   1352: invokespecial numberAt : (I)I
    //   1355: aload_1
    //   1356: iload #8
    //   1358: invokestatic offset : (I)J
    //   1361: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1364: checkcast java/util/List
    //   1367: aload_2
    //   1368: iconst_1
    //   1369: invokestatic writeUInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1372: goto -> 2449
    //   1375: aload_0
    //   1376: iload #6
    //   1378: invokespecial numberAt : (I)I
    //   1381: aload_1
    //   1382: iload #8
    //   1384: invokestatic offset : (I)J
    //   1387: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1390: checkcast java/util/List
    //   1393: aload_2
    //   1394: iconst_1
    //   1395: invokestatic writeInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1398: goto -> 2449
    //   1401: aload_0
    //   1402: iload #6
    //   1404: invokespecial numberAt : (I)I
    //   1407: aload_1
    //   1408: iload #8
    //   1410: invokestatic offset : (I)J
    //   1413: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1416: checkcast java/util/List
    //   1419: aload_2
    //   1420: iconst_1
    //   1421: invokestatic writeFloatList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1424: goto -> 2449
    //   1427: aload_0
    //   1428: iload #6
    //   1430: invokespecial numberAt : (I)I
    //   1433: aload_1
    //   1434: iload #8
    //   1436: invokestatic offset : (I)J
    //   1439: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1442: checkcast java/util/List
    //   1445: aload_2
    //   1446: iconst_1
    //   1447: invokestatic writeDoubleList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1450: goto -> 2449
    //   1453: aload_0
    //   1454: iload #6
    //   1456: invokespecial numberAt : (I)I
    //   1459: aload_1
    //   1460: iload #8
    //   1462: invokestatic offset : (I)J
    //   1465: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1468: checkcast java/util/List
    //   1471: aload_2
    //   1472: iconst_0
    //   1473: invokestatic writeSInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1476: goto -> 2449
    //   1479: aload_0
    //   1480: iload #6
    //   1482: invokespecial numberAt : (I)I
    //   1485: aload_1
    //   1486: iload #8
    //   1488: invokestatic offset : (I)J
    //   1491: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1494: checkcast java/util/List
    //   1497: aload_2
    //   1498: iconst_0
    //   1499: invokestatic writeSInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1502: goto -> 2449
    //   1505: aload_0
    //   1506: iload #6
    //   1508: invokespecial numberAt : (I)I
    //   1511: aload_1
    //   1512: iload #8
    //   1514: invokestatic offset : (I)J
    //   1517: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1520: checkcast java/util/List
    //   1523: aload_2
    //   1524: iconst_0
    //   1525: invokestatic writeSFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1528: goto -> 2449
    //   1531: aload_0
    //   1532: iload #6
    //   1534: invokespecial numberAt : (I)I
    //   1537: aload_1
    //   1538: iload #8
    //   1540: invokestatic offset : (I)J
    //   1543: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1546: checkcast java/util/List
    //   1549: aload_2
    //   1550: iconst_0
    //   1551: invokestatic writeSFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1554: goto -> 2449
    //   1557: aload_0
    //   1558: iload #6
    //   1560: invokespecial numberAt : (I)I
    //   1563: aload_1
    //   1564: iload #8
    //   1566: invokestatic offset : (I)J
    //   1569: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1572: checkcast java/util/List
    //   1575: aload_2
    //   1576: iconst_0
    //   1577: invokestatic writeEnumList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1580: goto -> 2449
    //   1583: aload_0
    //   1584: iload #6
    //   1586: invokespecial numberAt : (I)I
    //   1589: aload_1
    //   1590: iload #8
    //   1592: invokestatic offset : (I)J
    //   1595: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1598: checkcast java/util/List
    //   1601: aload_2
    //   1602: iconst_0
    //   1603: invokestatic writeUInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1606: goto -> 2449
    //   1609: aload_0
    //   1610: iload #6
    //   1612: invokespecial numberAt : (I)I
    //   1615: aload_1
    //   1616: iload #8
    //   1618: invokestatic offset : (I)J
    //   1621: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1624: checkcast java/util/List
    //   1627: aload_2
    //   1628: invokestatic writeBytesList : (ILjava/util/List;Lcom/google/protobuf/Writer;)V
    //   1631: goto -> 2449
    //   1634: aload_0
    //   1635: iload #6
    //   1637: invokespecial numberAt : (I)I
    //   1640: aload_1
    //   1641: iload #8
    //   1643: invokestatic offset : (I)J
    //   1646: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1649: checkcast java/util/List
    //   1652: aload_2
    //   1653: aload_0
    //   1654: iload #6
    //   1656: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1659: invokestatic writeMessageList : (ILjava/util/List;Lcom/google/protobuf/Writer;Lcom/google/protobuf/Schema;)V
    //   1662: goto -> 2449
    //   1665: aload_0
    //   1666: iload #6
    //   1668: invokespecial numberAt : (I)I
    //   1671: aload_1
    //   1672: iload #8
    //   1674: invokestatic offset : (I)J
    //   1677: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1680: checkcast java/util/List
    //   1683: aload_2
    //   1684: invokestatic writeStringList : (ILjava/util/List;Lcom/google/protobuf/Writer;)V
    //   1687: goto -> 2449
    //   1690: aload_0
    //   1691: iload #6
    //   1693: invokespecial numberAt : (I)I
    //   1696: aload_1
    //   1697: iload #8
    //   1699: invokestatic offset : (I)J
    //   1702: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1705: checkcast java/util/List
    //   1708: aload_2
    //   1709: iconst_0
    //   1710: invokestatic writeBoolList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1713: goto -> 2449
    //   1716: aload_0
    //   1717: iload #6
    //   1719: invokespecial numberAt : (I)I
    //   1722: aload_1
    //   1723: iload #8
    //   1725: invokestatic offset : (I)J
    //   1728: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1731: checkcast java/util/List
    //   1734: aload_2
    //   1735: iconst_0
    //   1736: invokestatic writeFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1739: goto -> 2449
    //   1742: aload_0
    //   1743: iload #6
    //   1745: invokespecial numberAt : (I)I
    //   1748: aload_1
    //   1749: iload #8
    //   1751: invokestatic offset : (I)J
    //   1754: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1757: checkcast java/util/List
    //   1760: aload_2
    //   1761: iconst_0
    //   1762: invokestatic writeFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1765: goto -> 2449
    //   1768: aload_0
    //   1769: iload #6
    //   1771: invokespecial numberAt : (I)I
    //   1774: aload_1
    //   1775: iload #8
    //   1777: invokestatic offset : (I)J
    //   1780: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1783: checkcast java/util/List
    //   1786: aload_2
    //   1787: iconst_0
    //   1788: invokestatic writeInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1791: goto -> 2449
    //   1794: aload_0
    //   1795: iload #6
    //   1797: invokespecial numberAt : (I)I
    //   1800: aload_1
    //   1801: iload #8
    //   1803: invokestatic offset : (I)J
    //   1806: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1809: checkcast java/util/List
    //   1812: aload_2
    //   1813: iconst_0
    //   1814: invokestatic writeUInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1817: goto -> 2449
    //   1820: aload_0
    //   1821: iload #6
    //   1823: invokespecial numberAt : (I)I
    //   1826: aload_1
    //   1827: iload #8
    //   1829: invokestatic offset : (I)J
    //   1832: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1835: checkcast java/util/List
    //   1838: aload_2
    //   1839: iconst_0
    //   1840: invokestatic writeInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1843: goto -> 2449
    //   1846: aload_0
    //   1847: iload #6
    //   1849: invokespecial numberAt : (I)I
    //   1852: aload_1
    //   1853: iload #8
    //   1855: invokestatic offset : (I)J
    //   1858: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1861: checkcast java/util/List
    //   1864: aload_2
    //   1865: iconst_0
    //   1866: invokestatic writeFloatList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1869: goto -> 2449
    //   1872: aload_0
    //   1873: iload #6
    //   1875: invokespecial numberAt : (I)I
    //   1878: aload_1
    //   1879: iload #8
    //   1881: invokestatic offset : (I)J
    //   1884: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1887: checkcast java/util/List
    //   1890: aload_2
    //   1891: iconst_0
    //   1892: invokestatic writeDoubleList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1895: goto -> 2449
    //   1898: aload_0
    //   1899: aload_1
    //   1900: iload #6
    //   1902: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   1905: ifeq -> 2449
    //   1908: aload_2
    //   1909: iload #9
    //   1911: aload_1
    //   1912: iload #8
    //   1914: invokestatic offset : (I)J
    //   1917: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1920: aload_0
    //   1921: iload #6
    //   1923: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1926: invokeinterface writeGroup : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   1931: goto -> 2449
    //   1934: aload_0
    //   1935: aload_1
    //   1936: iload #6
    //   1938: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   1941: ifeq -> 2449
    //   1944: aload_2
    //   1945: iload #9
    //   1947: aload_1
    //   1948: iload #8
    //   1950: invokestatic offset : (I)J
    //   1953: invokestatic longAt : (Ljava/lang/Object;J)J
    //   1956: invokeinterface writeSInt64 : (IJ)V
    //   1961: goto -> 2449
    //   1964: aload_0
    //   1965: aload_1
    //   1966: iload #6
    //   1968: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   1971: ifeq -> 2449
    //   1974: aload_2
    //   1975: iload #9
    //   1977: aload_1
    //   1978: iload #8
    //   1980: invokestatic offset : (I)J
    //   1983: invokestatic intAt : (Ljava/lang/Object;J)I
    //   1986: invokeinterface writeSInt32 : (II)V
    //   1991: goto -> 2449
    //   1994: aload_0
    //   1995: aload_1
    //   1996: iload #6
    //   1998: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2001: ifeq -> 2449
    //   2004: aload_2
    //   2005: iload #9
    //   2007: aload_1
    //   2008: iload #8
    //   2010: invokestatic offset : (I)J
    //   2013: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2016: invokeinterface writeSFixed64 : (IJ)V
    //   2021: goto -> 2449
    //   2024: aload_0
    //   2025: aload_1
    //   2026: iload #6
    //   2028: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2031: ifeq -> 2449
    //   2034: aload_2
    //   2035: iload #9
    //   2037: aload_1
    //   2038: iload #8
    //   2040: invokestatic offset : (I)J
    //   2043: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2046: invokeinterface writeSFixed32 : (II)V
    //   2051: goto -> 2449
    //   2054: aload_0
    //   2055: aload_1
    //   2056: iload #6
    //   2058: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2061: ifeq -> 2449
    //   2064: aload_2
    //   2065: iload #9
    //   2067: aload_1
    //   2068: iload #8
    //   2070: invokestatic offset : (I)J
    //   2073: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2076: invokeinterface writeEnum : (II)V
    //   2081: goto -> 2449
    //   2084: aload_0
    //   2085: aload_1
    //   2086: iload #6
    //   2088: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2091: ifeq -> 2449
    //   2094: aload_2
    //   2095: iload #9
    //   2097: aload_1
    //   2098: iload #8
    //   2100: invokestatic offset : (I)J
    //   2103: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2106: invokeinterface writeUInt32 : (II)V
    //   2111: goto -> 2449
    //   2114: aload_0
    //   2115: aload_1
    //   2116: iload #6
    //   2118: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2121: ifeq -> 2449
    //   2124: aload_2
    //   2125: iload #9
    //   2127: aload_1
    //   2128: iload #8
    //   2130: invokestatic offset : (I)J
    //   2133: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2136: checkcast com/google/protobuf/ByteString
    //   2139: invokeinterface writeBytes : (ILcom/google/protobuf/ByteString;)V
    //   2144: goto -> 2449
    //   2147: aload_0
    //   2148: aload_1
    //   2149: iload #6
    //   2151: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2154: ifeq -> 2449
    //   2157: aload_2
    //   2158: iload #9
    //   2160: aload_1
    //   2161: iload #8
    //   2163: invokestatic offset : (I)J
    //   2166: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2169: aload_0
    //   2170: iload #6
    //   2172: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   2175: invokeinterface writeMessage : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   2180: goto -> 2449
    //   2183: aload_0
    //   2184: aload_1
    //   2185: iload #6
    //   2187: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2190: ifeq -> 2449
    //   2193: aload_0
    //   2194: iload #9
    //   2196: aload_1
    //   2197: iload #8
    //   2199: invokestatic offset : (I)J
    //   2202: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2205: aload_2
    //   2206: invokespecial writeString : (ILjava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   2209: goto -> 2449
    //   2212: aload_0
    //   2213: aload_1
    //   2214: iload #6
    //   2216: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2219: ifeq -> 2449
    //   2222: aload_2
    //   2223: iload #9
    //   2225: aload_1
    //   2226: iload #8
    //   2228: invokestatic offset : (I)J
    //   2231: invokestatic booleanAt : (Ljava/lang/Object;J)Z
    //   2234: invokeinterface writeBool : (IZ)V
    //   2239: goto -> 2449
    //   2242: aload_0
    //   2243: aload_1
    //   2244: iload #6
    //   2246: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2249: ifeq -> 2449
    //   2252: aload_2
    //   2253: iload #9
    //   2255: aload_1
    //   2256: iload #8
    //   2258: invokestatic offset : (I)J
    //   2261: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2264: invokeinterface writeFixed32 : (II)V
    //   2269: goto -> 2449
    //   2272: aload_0
    //   2273: aload_1
    //   2274: iload #6
    //   2276: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2279: ifeq -> 2449
    //   2282: aload_2
    //   2283: iload #9
    //   2285: aload_1
    //   2286: iload #8
    //   2288: invokestatic offset : (I)J
    //   2291: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2294: invokeinterface writeFixed64 : (IJ)V
    //   2299: goto -> 2449
    //   2302: aload_0
    //   2303: aload_1
    //   2304: iload #6
    //   2306: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2309: ifeq -> 2449
    //   2312: aload_2
    //   2313: iload #9
    //   2315: aload_1
    //   2316: iload #8
    //   2318: invokestatic offset : (I)J
    //   2321: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2324: invokeinterface writeInt32 : (II)V
    //   2329: goto -> 2449
    //   2332: aload_0
    //   2333: aload_1
    //   2334: iload #6
    //   2336: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2339: ifeq -> 2449
    //   2342: aload_2
    //   2343: iload #9
    //   2345: aload_1
    //   2346: iload #8
    //   2348: invokestatic offset : (I)J
    //   2351: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2354: invokeinterface writeUInt64 : (IJ)V
    //   2359: goto -> 2449
    //   2362: aload_0
    //   2363: aload_1
    //   2364: iload #6
    //   2366: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2369: ifeq -> 2449
    //   2372: aload_2
    //   2373: iload #9
    //   2375: aload_1
    //   2376: iload #8
    //   2378: invokestatic offset : (I)J
    //   2381: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2384: invokeinterface writeInt64 : (IJ)V
    //   2389: goto -> 2449
    //   2392: aload_0
    //   2393: aload_1
    //   2394: iload #6
    //   2396: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2399: ifeq -> 2449
    //   2402: aload_2
    //   2403: iload #9
    //   2405: aload_1
    //   2406: iload #8
    //   2408: invokestatic offset : (I)J
    //   2411: invokestatic floatAt : (Ljava/lang/Object;J)F
    //   2414: invokeinterface writeFloat : (IF)V
    //   2419: goto -> 2449
    //   2422: aload_0
    //   2423: aload_1
    //   2424: iload #6
    //   2426: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2429: ifeq -> 2449
    //   2432: aload_2
    //   2433: iload #9
    //   2435: aload_1
    //   2436: iload #8
    //   2438: invokestatic offset : (I)J
    //   2441: invokestatic doubleAt : (Ljava/lang/Object;J)D
    //   2444: invokeinterface writeDouble : (ID)V
    //   2449: iinc #6, 3
    //   2452: goto -> 62
    //   2455: aload_3
    //   2456: ifnull -> 2497
    //   2459: aload_0
    //   2460: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   2463: aload_2
    //   2464: aload_3
    //   2465: invokevirtual serializeExtension : (Lcom/google/protobuf/Writer;Ljava/util/Map$Entry;)V
    //   2468: aload #4
    //   2470: invokeinterface hasNext : ()Z
    //   2475: ifeq -> 2492
    //   2478: aload #4
    //   2480: invokeinterface next : ()Ljava/lang/Object;
    //   2485: checkcast java/util/Map$Entry
    //   2488: astore_3
    //   2489: goto -> 2455
    //   2492: aconst_null
    //   2493: astore_3
    //   2494: goto -> 2455
    //   2497: aload_0
    //   2498: aload_0
    //   2499: getfield unknownFieldSchema : Lcom/google/protobuf/UnknownFieldSchema;
    //   2502: aload_1
    //   2503: aload_2
    //   2504: invokespecial writeUnknownInMessageTo : (Lcom/google/protobuf/UnknownFieldSchema;Ljava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   2507: return
  }
  
  private void writeFieldsInDescendingOrder(T paramT, Writer paramWriter) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield unknownFieldSchema : Lcom/google/protobuf/UnknownFieldSchema;
    //   5: aload_1
    //   6: aload_2
    //   7: invokespecial writeUnknownInMessageTo : (Lcom/google/protobuf/UnknownFieldSchema;Ljava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   10: aload_0
    //   11: getfield hasExtensions : Z
    //   14: ifeq -> 53
    //   17: aload_0
    //   18: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   21: aload_1
    //   22: invokevirtual getExtensions : (Ljava/lang/Object;)Lcom/google/protobuf/FieldSet;
    //   25: astore_3
    //   26: aload_3
    //   27: invokevirtual isEmpty : ()Z
    //   30: ifne -> 53
    //   33: aload_3
    //   34: invokevirtual descendingIterator : ()Ljava/util/Iterator;
    //   37: astore #4
    //   39: aload #4
    //   41: invokeinterface next : ()Ljava/lang/Object;
    //   46: checkcast java/util/Map$Entry
    //   49: astore_3
    //   50: goto -> 59
    //   53: aconst_null
    //   54: astore #4
    //   56: aload #4
    //   58: astore_3
    //   59: aload_0
    //   60: getfield buffer : [I
    //   63: arraylength
    //   64: iconst_3
    //   65: isub
    //   66: istore #5
    //   68: aload_3
    //   69: astore #6
    //   71: iload #5
    //   73: iflt -> 2455
    //   76: aload_0
    //   77: iload #5
    //   79: invokespecial typeAndOffsetAt : (I)I
    //   82: istore #7
    //   84: aload_0
    //   85: iload #5
    //   87: invokespecial numberAt : (I)I
    //   90: istore #8
    //   92: aload_3
    //   93: ifnull -> 147
    //   96: aload_0
    //   97: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   100: aload_3
    //   101: invokevirtual extensionNumber : (Ljava/util/Map$Entry;)I
    //   104: iload #8
    //   106: if_icmple -> 147
    //   109: aload_0
    //   110: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   113: aload_2
    //   114: aload_3
    //   115: invokevirtual serializeExtension : (Lcom/google/protobuf/Writer;Ljava/util/Map$Entry;)V
    //   118: aload #4
    //   120: invokeinterface hasNext : ()Z
    //   125: ifeq -> 142
    //   128: aload #4
    //   130: invokeinterface next : ()Ljava/lang/Object;
    //   135: checkcast java/util/Map$Entry
    //   138: astore_3
    //   139: goto -> 92
    //   142: aconst_null
    //   143: astore_3
    //   144: goto -> 92
    //   147: iload #7
    //   149: invokestatic type : (I)I
    //   152: tableswitch default -> 444, 0 -> 2422, 1 -> 2392, 2 -> 2362, 3 -> 2332, 4 -> 2302, 5 -> 2272, 6 -> 2242, 7 -> 2212, 8 -> 2183, 9 -> 2147, 10 -> 2114, 11 -> 2084, 12 -> 2054, 13 -> 2024, 14 -> 1994, 15 -> 1964, 16 -> 1934, 17 -> 1898, 18 -> 1872, 19 -> 1846, 20 -> 1820, 21 -> 1794, 22 -> 1768, 23 -> 1742, 24 -> 1716, 25 -> 1690, 26 -> 1665, 27 -> 1634, 28 -> 1609, 29 -> 1583, 30 -> 1557, 31 -> 1531, 32 -> 1505, 33 -> 1479, 34 -> 1453, 35 -> 1427, 36 -> 1401, 37 -> 1375, 38 -> 1349, 39 -> 1323, 40 -> 1297, 41 -> 1271, 42 -> 1245, 43 -> 1219, 44 -> 1193, 45 -> 1167, 46 -> 1141, 47 -> 1115, 48 -> 1089, 49 -> 1058, 50 -> 1037, 51 -> 1005, 52 -> 973, 53 -> 941, 54 -> 909, 55 -> 877, 56 -> 845, 57 -> 813, 58 -> 781, 59 -> 750, 60 -> 712, 61 -> 677, 62 -> 645, 63 -> 613, 64 -> 581, 65 -> 549, 66 -> 517, 67 -> 485, 68 -> 447
    //   444: goto -> 2449
    //   447: aload_0
    //   448: aload_1
    //   449: iload #8
    //   451: iload #5
    //   453: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   456: ifeq -> 2449
    //   459: aload_2
    //   460: iload #8
    //   462: aload_1
    //   463: iload #7
    //   465: invokestatic offset : (I)J
    //   468: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   471: aload_0
    //   472: iload #5
    //   474: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   477: invokeinterface writeGroup : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   482: goto -> 2449
    //   485: aload_0
    //   486: aload_1
    //   487: iload #8
    //   489: iload #5
    //   491: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   494: ifeq -> 2449
    //   497: aload_2
    //   498: iload #8
    //   500: aload_1
    //   501: iload #7
    //   503: invokestatic offset : (I)J
    //   506: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   509: invokeinterface writeSInt64 : (IJ)V
    //   514: goto -> 2449
    //   517: aload_0
    //   518: aload_1
    //   519: iload #8
    //   521: iload #5
    //   523: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   526: ifeq -> 2449
    //   529: aload_2
    //   530: iload #8
    //   532: aload_1
    //   533: iload #7
    //   535: invokestatic offset : (I)J
    //   538: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   541: invokeinterface writeSInt32 : (II)V
    //   546: goto -> 2449
    //   549: aload_0
    //   550: aload_1
    //   551: iload #8
    //   553: iload #5
    //   555: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   558: ifeq -> 2449
    //   561: aload_2
    //   562: iload #8
    //   564: aload_1
    //   565: iload #7
    //   567: invokestatic offset : (I)J
    //   570: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   573: invokeinterface writeSFixed64 : (IJ)V
    //   578: goto -> 2449
    //   581: aload_0
    //   582: aload_1
    //   583: iload #8
    //   585: iload #5
    //   587: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   590: ifeq -> 2449
    //   593: aload_2
    //   594: iload #8
    //   596: aload_1
    //   597: iload #7
    //   599: invokestatic offset : (I)J
    //   602: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   605: invokeinterface writeSFixed32 : (II)V
    //   610: goto -> 2449
    //   613: aload_0
    //   614: aload_1
    //   615: iload #8
    //   617: iload #5
    //   619: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   622: ifeq -> 2449
    //   625: aload_2
    //   626: iload #8
    //   628: aload_1
    //   629: iload #7
    //   631: invokestatic offset : (I)J
    //   634: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   637: invokeinterface writeEnum : (II)V
    //   642: goto -> 2449
    //   645: aload_0
    //   646: aload_1
    //   647: iload #8
    //   649: iload #5
    //   651: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   654: ifeq -> 2449
    //   657: aload_2
    //   658: iload #8
    //   660: aload_1
    //   661: iload #7
    //   663: invokestatic offset : (I)J
    //   666: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   669: invokeinterface writeUInt32 : (II)V
    //   674: goto -> 2449
    //   677: aload_0
    //   678: aload_1
    //   679: iload #8
    //   681: iload #5
    //   683: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   686: ifeq -> 2449
    //   689: aload_2
    //   690: iload #8
    //   692: aload_1
    //   693: iload #7
    //   695: invokestatic offset : (I)J
    //   698: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   701: checkcast com/google/protobuf/ByteString
    //   704: invokeinterface writeBytes : (ILcom/google/protobuf/ByteString;)V
    //   709: goto -> 2449
    //   712: aload_0
    //   713: aload_1
    //   714: iload #8
    //   716: iload #5
    //   718: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   721: ifeq -> 2449
    //   724: aload_2
    //   725: iload #8
    //   727: aload_1
    //   728: iload #7
    //   730: invokestatic offset : (I)J
    //   733: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   736: aload_0
    //   737: iload #5
    //   739: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   742: invokeinterface writeMessage : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   747: goto -> 2449
    //   750: aload_0
    //   751: aload_1
    //   752: iload #8
    //   754: iload #5
    //   756: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   759: ifeq -> 2449
    //   762: aload_0
    //   763: iload #8
    //   765: aload_1
    //   766: iload #7
    //   768: invokestatic offset : (I)J
    //   771: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   774: aload_2
    //   775: invokespecial writeString : (ILjava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   778: goto -> 2449
    //   781: aload_0
    //   782: aload_1
    //   783: iload #8
    //   785: iload #5
    //   787: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   790: ifeq -> 2449
    //   793: aload_2
    //   794: iload #8
    //   796: aload_1
    //   797: iload #7
    //   799: invokestatic offset : (I)J
    //   802: invokestatic oneofBooleanAt : (Ljava/lang/Object;J)Z
    //   805: invokeinterface writeBool : (IZ)V
    //   810: goto -> 2449
    //   813: aload_0
    //   814: aload_1
    //   815: iload #8
    //   817: iload #5
    //   819: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   822: ifeq -> 2449
    //   825: aload_2
    //   826: iload #8
    //   828: aload_1
    //   829: iload #7
    //   831: invokestatic offset : (I)J
    //   834: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   837: invokeinterface writeFixed32 : (II)V
    //   842: goto -> 2449
    //   845: aload_0
    //   846: aload_1
    //   847: iload #8
    //   849: iload #5
    //   851: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   854: ifeq -> 2449
    //   857: aload_2
    //   858: iload #8
    //   860: aload_1
    //   861: iload #7
    //   863: invokestatic offset : (I)J
    //   866: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   869: invokeinterface writeFixed64 : (IJ)V
    //   874: goto -> 2449
    //   877: aload_0
    //   878: aload_1
    //   879: iload #8
    //   881: iload #5
    //   883: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   886: ifeq -> 2449
    //   889: aload_2
    //   890: iload #8
    //   892: aload_1
    //   893: iload #7
    //   895: invokestatic offset : (I)J
    //   898: invokestatic oneofIntAt : (Ljava/lang/Object;J)I
    //   901: invokeinterface writeInt32 : (II)V
    //   906: goto -> 2449
    //   909: aload_0
    //   910: aload_1
    //   911: iload #8
    //   913: iload #5
    //   915: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   918: ifeq -> 2449
    //   921: aload_2
    //   922: iload #8
    //   924: aload_1
    //   925: iload #7
    //   927: invokestatic offset : (I)J
    //   930: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   933: invokeinterface writeUInt64 : (IJ)V
    //   938: goto -> 2449
    //   941: aload_0
    //   942: aload_1
    //   943: iload #8
    //   945: iload #5
    //   947: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   950: ifeq -> 2449
    //   953: aload_2
    //   954: iload #8
    //   956: aload_1
    //   957: iload #7
    //   959: invokestatic offset : (I)J
    //   962: invokestatic oneofLongAt : (Ljava/lang/Object;J)J
    //   965: invokeinterface writeInt64 : (IJ)V
    //   970: goto -> 2449
    //   973: aload_0
    //   974: aload_1
    //   975: iload #8
    //   977: iload #5
    //   979: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   982: ifeq -> 2449
    //   985: aload_2
    //   986: iload #8
    //   988: aload_1
    //   989: iload #7
    //   991: invokestatic offset : (I)J
    //   994: invokestatic oneofFloatAt : (Ljava/lang/Object;J)F
    //   997: invokeinterface writeFloat : (IF)V
    //   1002: goto -> 2449
    //   1005: aload_0
    //   1006: aload_1
    //   1007: iload #8
    //   1009: iload #5
    //   1011: invokespecial isOneofPresent : (Ljava/lang/Object;II)Z
    //   1014: ifeq -> 2449
    //   1017: aload_2
    //   1018: iload #8
    //   1020: aload_1
    //   1021: iload #7
    //   1023: invokestatic offset : (I)J
    //   1026: invokestatic oneofDoubleAt : (Ljava/lang/Object;J)D
    //   1029: invokeinterface writeDouble : (ID)V
    //   1034: goto -> 2449
    //   1037: aload_0
    //   1038: aload_2
    //   1039: iload #8
    //   1041: aload_1
    //   1042: iload #7
    //   1044: invokestatic offset : (I)J
    //   1047: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1050: iload #5
    //   1052: invokespecial writeMapHelper : (Lcom/google/protobuf/Writer;ILjava/lang/Object;I)V
    //   1055: goto -> 2449
    //   1058: aload_0
    //   1059: iload #5
    //   1061: invokespecial numberAt : (I)I
    //   1064: aload_1
    //   1065: iload #7
    //   1067: invokestatic offset : (I)J
    //   1070: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1073: checkcast java/util/List
    //   1076: aload_2
    //   1077: aload_0
    //   1078: iload #5
    //   1080: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1083: invokestatic writeGroupList : (ILjava/util/List;Lcom/google/protobuf/Writer;Lcom/google/protobuf/Schema;)V
    //   1086: goto -> 2449
    //   1089: aload_0
    //   1090: iload #5
    //   1092: invokespecial numberAt : (I)I
    //   1095: aload_1
    //   1096: iload #7
    //   1098: invokestatic offset : (I)J
    //   1101: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1104: checkcast java/util/List
    //   1107: aload_2
    //   1108: iconst_1
    //   1109: invokestatic writeSInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1112: goto -> 2449
    //   1115: aload_0
    //   1116: iload #5
    //   1118: invokespecial numberAt : (I)I
    //   1121: aload_1
    //   1122: iload #7
    //   1124: invokestatic offset : (I)J
    //   1127: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1130: checkcast java/util/List
    //   1133: aload_2
    //   1134: iconst_1
    //   1135: invokestatic writeSInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1138: goto -> 2449
    //   1141: aload_0
    //   1142: iload #5
    //   1144: invokespecial numberAt : (I)I
    //   1147: aload_1
    //   1148: iload #7
    //   1150: invokestatic offset : (I)J
    //   1153: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1156: checkcast java/util/List
    //   1159: aload_2
    //   1160: iconst_1
    //   1161: invokestatic writeSFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1164: goto -> 2449
    //   1167: aload_0
    //   1168: iload #5
    //   1170: invokespecial numberAt : (I)I
    //   1173: aload_1
    //   1174: iload #7
    //   1176: invokestatic offset : (I)J
    //   1179: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1182: checkcast java/util/List
    //   1185: aload_2
    //   1186: iconst_1
    //   1187: invokestatic writeSFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1190: goto -> 2449
    //   1193: aload_0
    //   1194: iload #5
    //   1196: invokespecial numberAt : (I)I
    //   1199: aload_1
    //   1200: iload #7
    //   1202: invokestatic offset : (I)J
    //   1205: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1208: checkcast java/util/List
    //   1211: aload_2
    //   1212: iconst_1
    //   1213: invokestatic writeEnumList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1216: goto -> 2449
    //   1219: aload_0
    //   1220: iload #5
    //   1222: invokespecial numberAt : (I)I
    //   1225: aload_1
    //   1226: iload #7
    //   1228: invokestatic offset : (I)J
    //   1231: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1234: checkcast java/util/List
    //   1237: aload_2
    //   1238: iconst_1
    //   1239: invokestatic writeUInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1242: goto -> 2449
    //   1245: aload_0
    //   1246: iload #5
    //   1248: invokespecial numberAt : (I)I
    //   1251: aload_1
    //   1252: iload #7
    //   1254: invokestatic offset : (I)J
    //   1257: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1260: checkcast java/util/List
    //   1263: aload_2
    //   1264: iconst_1
    //   1265: invokestatic writeBoolList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1268: goto -> 2449
    //   1271: aload_0
    //   1272: iload #5
    //   1274: invokespecial numberAt : (I)I
    //   1277: aload_1
    //   1278: iload #7
    //   1280: invokestatic offset : (I)J
    //   1283: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1286: checkcast java/util/List
    //   1289: aload_2
    //   1290: iconst_1
    //   1291: invokestatic writeFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1294: goto -> 2449
    //   1297: aload_0
    //   1298: iload #5
    //   1300: invokespecial numberAt : (I)I
    //   1303: aload_1
    //   1304: iload #7
    //   1306: invokestatic offset : (I)J
    //   1309: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1312: checkcast java/util/List
    //   1315: aload_2
    //   1316: iconst_1
    //   1317: invokestatic writeFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1320: goto -> 2449
    //   1323: aload_0
    //   1324: iload #5
    //   1326: invokespecial numberAt : (I)I
    //   1329: aload_1
    //   1330: iload #7
    //   1332: invokestatic offset : (I)J
    //   1335: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1338: checkcast java/util/List
    //   1341: aload_2
    //   1342: iconst_1
    //   1343: invokestatic writeInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1346: goto -> 2449
    //   1349: aload_0
    //   1350: iload #5
    //   1352: invokespecial numberAt : (I)I
    //   1355: aload_1
    //   1356: iload #7
    //   1358: invokestatic offset : (I)J
    //   1361: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1364: checkcast java/util/List
    //   1367: aload_2
    //   1368: iconst_1
    //   1369: invokestatic writeUInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1372: goto -> 2449
    //   1375: aload_0
    //   1376: iload #5
    //   1378: invokespecial numberAt : (I)I
    //   1381: aload_1
    //   1382: iload #7
    //   1384: invokestatic offset : (I)J
    //   1387: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1390: checkcast java/util/List
    //   1393: aload_2
    //   1394: iconst_1
    //   1395: invokestatic writeInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1398: goto -> 2449
    //   1401: aload_0
    //   1402: iload #5
    //   1404: invokespecial numberAt : (I)I
    //   1407: aload_1
    //   1408: iload #7
    //   1410: invokestatic offset : (I)J
    //   1413: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1416: checkcast java/util/List
    //   1419: aload_2
    //   1420: iconst_1
    //   1421: invokestatic writeFloatList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1424: goto -> 2449
    //   1427: aload_0
    //   1428: iload #5
    //   1430: invokespecial numberAt : (I)I
    //   1433: aload_1
    //   1434: iload #7
    //   1436: invokestatic offset : (I)J
    //   1439: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1442: checkcast java/util/List
    //   1445: aload_2
    //   1446: iconst_1
    //   1447: invokestatic writeDoubleList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1450: goto -> 2449
    //   1453: aload_0
    //   1454: iload #5
    //   1456: invokespecial numberAt : (I)I
    //   1459: aload_1
    //   1460: iload #7
    //   1462: invokestatic offset : (I)J
    //   1465: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1468: checkcast java/util/List
    //   1471: aload_2
    //   1472: iconst_0
    //   1473: invokestatic writeSInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1476: goto -> 2449
    //   1479: aload_0
    //   1480: iload #5
    //   1482: invokespecial numberAt : (I)I
    //   1485: aload_1
    //   1486: iload #7
    //   1488: invokestatic offset : (I)J
    //   1491: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1494: checkcast java/util/List
    //   1497: aload_2
    //   1498: iconst_0
    //   1499: invokestatic writeSInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1502: goto -> 2449
    //   1505: aload_0
    //   1506: iload #5
    //   1508: invokespecial numberAt : (I)I
    //   1511: aload_1
    //   1512: iload #7
    //   1514: invokestatic offset : (I)J
    //   1517: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1520: checkcast java/util/List
    //   1523: aload_2
    //   1524: iconst_0
    //   1525: invokestatic writeSFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1528: goto -> 2449
    //   1531: aload_0
    //   1532: iload #5
    //   1534: invokespecial numberAt : (I)I
    //   1537: aload_1
    //   1538: iload #7
    //   1540: invokestatic offset : (I)J
    //   1543: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1546: checkcast java/util/List
    //   1549: aload_2
    //   1550: iconst_0
    //   1551: invokestatic writeSFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1554: goto -> 2449
    //   1557: aload_0
    //   1558: iload #5
    //   1560: invokespecial numberAt : (I)I
    //   1563: aload_1
    //   1564: iload #7
    //   1566: invokestatic offset : (I)J
    //   1569: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1572: checkcast java/util/List
    //   1575: aload_2
    //   1576: iconst_0
    //   1577: invokestatic writeEnumList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1580: goto -> 2449
    //   1583: aload_0
    //   1584: iload #5
    //   1586: invokespecial numberAt : (I)I
    //   1589: aload_1
    //   1590: iload #7
    //   1592: invokestatic offset : (I)J
    //   1595: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1598: checkcast java/util/List
    //   1601: aload_2
    //   1602: iconst_0
    //   1603: invokestatic writeUInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1606: goto -> 2449
    //   1609: aload_0
    //   1610: iload #5
    //   1612: invokespecial numberAt : (I)I
    //   1615: aload_1
    //   1616: iload #7
    //   1618: invokestatic offset : (I)J
    //   1621: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1624: checkcast java/util/List
    //   1627: aload_2
    //   1628: invokestatic writeBytesList : (ILjava/util/List;Lcom/google/protobuf/Writer;)V
    //   1631: goto -> 2449
    //   1634: aload_0
    //   1635: iload #5
    //   1637: invokespecial numberAt : (I)I
    //   1640: aload_1
    //   1641: iload #7
    //   1643: invokestatic offset : (I)J
    //   1646: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1649: checkcast java/util/List
    //   1652: aload_2
    //   1653: aload_0
    //   1654: iload #5
    //   1656: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1659: invokestatic writeMessageList : (ILjava/util/List;Lcom/google/protobuf/Writer;Lcom/google/protobuf/Schema;)V
    //   1662: goto -> 2449
    //   1665: aload_0
    //   1666: iload #5
    //   1668: invokespecial numberAt : (I)I
    //   1671: aload_1
    //   1672: iload #7
    //   1674: invokestatic offset : (I)J
    //   1677: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1680: checkcast java/util/List
    //   1683: aload_2
    //   1684: invokestatic writeStringList : (ILjava/util/List;Lcom/google/protobuf/Writer;)V
    //   1687: goto -> 2449
    //   1690: aload_0
    //   1691: iload #5
    //   1693: invokespecial numberAt : (I)I
    //   1696: aload_1
    //   1697: iload #7
    //   1699: invokestatic offset : (I)J
    //   1702: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1705: checkcast java/util/List
    //   1708: aload_2
    //   1709: iconst_0
    //   1710: invokestatic writeBoolList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1713: goto -> 2449
    //   1716: aload_0
    //   1717: iload #5
    //   1719: invokespecial numberAt : (I)I
    //   1722: aload_1
    //   1723: iload #7
    //   1725: invokestatic offset : (I)J
    //   1728: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1731: checkcast java/util/List
    //   1734: aload_2
    //   1735: iconst_0
    //   1736: invokestatic writeFixed32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1739: goto -> 2449
    //   1742: aload_0
    //   1743: iload #5
    //   1745: invokespecial numberAt : (I)I
    //   1748: aload_1
    //   1749: iload #7
    //   1751: invokestatic offset : (I)J
    //   1754: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1757: checkcast java/util/List
    //   1760: aload_2
    //   1761: iconst_0
    //   1762: invokestatic writeFixed64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1765: goto -> 2449
    //   1768: aload_0
    //   1769: iload #5
    //   1771: invokespecial numberAt : (I)I
    //   1774: aload_1
    //   1775: iload #7
    //   1777: invokestatic offset : (I)J
    //   1780: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1783: checkcast java/util/List
    //   1786: aload_2
    //   1787: iconst_0
    //   1788: invokestatic writeInt32List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1791: goto -> 2449
    //   1794: aload_0
    //   1795: iload #5
    //   1797: invokespecial numberAt : (I)I
    //   1800: aload_1
    //   1801: iload #7
    //   1803: invokestatic offset : (I)J
    //   1806: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1809: checkcast java/util/List
    //   1812: aload_2
    //   1813: iconst_0
    //   1814: invokestatic writeUInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1817: goto -> 2449
    //   1820: aload_0
    //   1821: iload #5
    //   1823: invokespecial numberAt : (I)I
    //   1826: aload_1
    //   1827: iload #7
    //   1829: invokestatic offset : (I)J
    //   1832: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1835: checkcast java/util/List
    //   1838: aload_2
    //   1839: iconst_0
    //   1840: invokestatic writeInt64List : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1843: goto -> 2449
    //   1846: aload_0
    //   1847: iload #5
    //   1849: invokespecial numberAt : (I)I
    //   1852: aload_1
    //   1853: iload #7
    //   1855: invokestatic offset : (I)J
    //   1858: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1861: checkcast java/util/List
    //   1864: aload_2
    //   1865: iconst_0
    //   1866: invokestatic writeFloatList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1869: goto -> 2449
    //   1872: aload_0
    //   1873: iload #5
    //   1875: invokespecial numberAt : (I)I
    //   1878: aload_1
    //   1879: iload #7
    //   1881: invokestatic offset : (I)J
    //   1884: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1887: checkcast java/util/List
    //   1890: aload_2
    //   1891: iconst_0
    //   1892: invokestatic writeDoubleList : (ILjava/util/List;Lcom/google/protobuf/Writer;Z)V
    //   1895: goto -> 2449
    //   1898: aload_0
    //   1899: aload_1
    //   1900: iload #5
    //   1902: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   1905: ifeq -> 2449
    //   1908: aload_2
    //   1909: iload #8
    //   1911: aload_1
    //   1912: iload #7
    //   1914: invokestatic offset : (I)J
    //   1917: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1920: aload_0
    //   1921: iload #5
    //   1923: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1926: invokeinterface writeGroup : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   1931: goto -> 2449
    //   1934: aload_0
    //   1935: aload_1
    //   1936: iload #5
    //   1938: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   1941: ifeq -> 2449
    //   1944: aload_2
    //   1945: iload #8
    //   1947: aload_1
    //   1948: iload #7
    //   1950: invokestatic offset : (I)J
    //   1953: invokestatic longAt : (Ljava/lang/Object;J)J
    //   1956: invokeinterface writeSInt64 : (IJ)V
    //   1961: goto -> 2449
    //   1964: aload_0
    //   1965: aload_1
    //   1966: iload #5
    //   1968: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   1971: ifeq -> 2449
    //   1974: aload_2
    //   1975: iload #8
    //   1977: aload_1
    //   1978: iload #7
    //   1980: invokestatic offset : (I)J
    //   1983: invokestatic intAt : (Ljava/lang/Object;J)I
    //   1986: invokeinterface writeSInt32 : (II)V
    //   1991: goto -> 2449
    //   1994: aload_0
    //   1995: aload_1
    //   1996: iload #5
    //   1998: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2001: ifeq -> 2449
    //   2004: aload_2
    //   2005: iload #8
    //   2007: aload_1
    //   2008: iload #7
    //   2010: invokestatic offset : (I)J
    //   2013: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2016: invokeinterface writeSFixed64 : (IJ)V
    //   2021: goto -> 2449
    //   2024: aload_0
    //   2025: aload_1
    //   2026: iload #5
    //   2028: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2031: ifeq -> 2449
    //   2034: aload_2
    //   2035: iload #8
    //   2037: aload_1
    //   2038: iload #7
    //   2040: invokestatic offset : (I)J
    //   2043: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2046: invokeinterface writeSFixed32 : (II)V
    //   2051: goto -> 2449
    //   2054: aload_0
    //   2055: aload_1
    //   2056: iload #5
    //   2058: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2061: ifeq -> 2449
    //   2064: aload_2
    //   2065: iload #8
    //   2067: aload_1
    //   2068: iload #7
    //   2070: invokestatic offset : (I)J
    //   2073: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2076: invokeinterface writeEnum : (II)V
    //   2081: goto -> 2449
    //   2084: aload_0
    //   2085: aload_1
    //   2086: iload #5
    //   2088: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2091: ifeq -> 2449
    //   2094: aload_2
    //   2095: iload #8
    //   2097: aload_1
    //   2098: iload #7
    //   2100: invokestatic offset : (I)J
    //   2103: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2106: invokeinterface writeUInt32 : (II)V
    //   2111: goto -> 2449
    //   2114: aload_0
    //   2115: aload_1
    //   2116: iload #5
    //   2118: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2121: ifeq -> 2449
    //   2124: aload_2
    //   2125: iload #8
    //   2127: aload_1
    //   2128: iload #7
    //   2130: invokestatic offset : (I)J
    //   2133: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2136: checkcast com/google/protobuf/ByteString
    //   2139: invokeinterface writeBytes : (ILcom/google/protobuf/ByteString;)V
    //   2144: goto -> 2449
    //   2147: aload_0
    //   2148: aload_1
    //   2149: iload #5
    //   2151: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2154: ifeq -> 2449
    //   2157: aload_2
    //   2158: iload #8
    //   2160: aload_1
    //   2161: iload #7
    //   2163: invokestatic offset : (I)J
    //   2166: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2169: aload_0
    //   2170: iload #5
    //   2172: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   2175: invokeinterface writeMessage : (ILjava/lang/Object;Lcom/google/protobuf/Schema;)V
    //   2180: goto -> 2449
    //   2183: aload_0
    //   2184: aload_1
    //   2185: iload #5
    //   2187: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2190: ifeq -> 2449
    //   2193: aload_0
    //   2194: iload #8
    //   2196: aload_1
    //   2197: iload #7
    //   2199: invokestatic offset : (I)J
    //   2202: invokestatic getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   2205: aload_2
    //   2206: invokespecial writeString : (ILjava/lang/Object;Lcom/google/protobuf/Writer;)V
    //   2209: goto -> 2449
    //   2212: aload_0
    //   2213: aload_1
    //   2214: iload #5
    //   2216: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2219: ifeq -> 2449
    //   2222: aload_2
    //   2223: iload #8
    //   2225: aload_1
    //   2226: iload #7
    //   2228: invokestatic offset : (I)J
    //   2231: invokestatic booleanAt : (Ljava/lang/Object;J)Z
    //   2234: invokeinterface writeBool : (IZ)V
    //   2239: goto -> 2449
    //   2242: aload_0
    //   2243: aload_1
    //   2244: iload #5
    //   2246: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2249: ifeq -> 2449
    //   2252: aload_2
    //   2253: iload #8
    //   2255: aload_1
    //   2256: iload #7
    //   2258: invokestatic offset : (I)J
    //   2261: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2264: invokeinterface writeFixed32 : (II)V
    //   2269: goto -> 2449
    //   2272: aload_0
    //   2273: aload_1
    //   2274: iload #5
    //   2276: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2279: ifeq -> 2449
    //   2282: aload_2
    //   2283: iload #8
    //   2285: aload_1
    //   2286: iload #7
    //   2288: invokestatic offset : (I)J
    //   2291: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2294: invokeinterface writeFixed64 : (IJ)V
    //   2299: goto -> 2449
    //   2302: aload_0
    //   2303: aload_1
    //   2304: iload #5
    //   2306: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2309: ifeq -> 2449
    //   2312: aload_2
    //   2313: iload #8
    //   2315: aload_1
    //   2316: iload #7
    //   2318: invokestatic offset : (I)J
    //   2321: invokestatic intAt : (Ljava/lang/Object;J)I
    //   2324: invokeinterface writeInt32 : (II)V
    //   2329: goto -> 2449
    //   2332: aload_0
    //   2333: aload_1
    //   2334: iload #5
    //   2336: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2339: ifeq -> 2449
    //   2342: aload_2
    //   2343: iload #8
    //   2345: aload_1
    //   2346: iload #7
    //   2348: invokestatic offset : (I)J
    //   2351: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2354: invokeinterface writeUInt64 : (IJ)V
    //   2359: goto -> 2449
    //   2362: aload_0
    //   2363: aload_1
    //   2364: iload #5
    //   2366: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2369: ifeq -> 2449
    //   2372: aload_2
    //   2373: iload #8
    //   2375: aload_1
    //   2376: iload #7
    //   2378: invokestatic offset : (I)J
    //   2381: invokestatic longAt : (Ljava/lang/Object;J)J
    //   2384: invokeinterface writeInt64 : (IJ)V
    //   2389: goto -> 2449
    //   2392: aload_0
    //   2393: aload_1
    //   2394: iload #5
    //   2396: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2399: ifeq -> 2449
    //   2402: aload_2
    //   2403: iload #8
    //   2405: aload_1
    //   2406: iload #7
    //   2408: invokestatic offset : (I)J
    //   2411: invokestatic floatAt : (Ljava/lang/Object;J)F
    //   2414: invokeinterface writeFloat : (IF)V
    //   2419: goto -> 2449
    //   2422: aload_0
    //   2423: aload_1
    //   2424: iload #5
    //   2426: invokespecial isFieldPresent : (Ljava/lang/Object;I)Z
    //   2429: ifeq -> 2449
    //   2432: aload_2
    //   2433: iload #8
    //   2435: aload_1
    //   2436: iload #7
    //   2438: invokestatic offset : (I)J
    //   2441: invokestatic doubleAt : (Ljava/lang/Object;J)D
    //   2444: invokeinterface writeDouble : (ID)V
    //   2449: iinc #5, -3
    //   2452: goto -> 68
    //   2455: aload #6
    //   2457: ifnull -> 2501
    //   2460: aload_0
    //   2461: getfield extensionSchema : Lcom/google/protobuf/ExtensionSchema;
    //   2464: aload_2
    //   2465: aload #6
    //   2467: invokevirtual serializeExtension : (Lcom/google/protobuf/Writer;Ljava/util/Map$Entry;)V
    //   2470: aload #4
    //   2472: invokeinterface hasNext : ()Z
    //   2477: ifeq -> 2495
    //   2480: aload #4
    //   2482: invokeinterface next : ()Ljava/lang/Object;
    //   2487: checkcast java/util/Map$Entry
    //   2490: astore #6
    //   2492: goto -> 2455
    //   2495: aconst_null
    //   2496: astore #6
    //   2498: goto -> 2455
    //   2501: return
  }
  
  private <K, V> void writeMapHelper(Writer paramWriter, int paramInt1, Object paramObject, int paramInt2) throws IOException {
    if (paramObject != null)
      paramWriter.writeMap(paramInt1, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(paramInt2)), this.mapFieldSchema.forMapData(paramObject)); 
  }
  
  private void writeString(int paramInt, Object paramObject, Writer paramWriter) throws IOException {
    if (paramObject instanceof String) {
      paramWriter.writeString(paramInt, (String)paramObject);
    } else {
      paramWriter.writeBytes(paramInt, (ByteString)paramObject);
    } 
  }
  
  private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> paramUnknownFieldSchema, T paramT, Writer paramWriter) throws IOException {
    paramUnknownFieldSchema.writeTo(paramUnknownFieldSchema.getFromMessage(paramT), paramWriter);
  }
  
  public boolean equals(T paramT1, T paramT2) {
    int i = this.buffer.length;
    for (byte b = 0; b < i; b += 3) {
      if (!equals(paramT1, paramT2, b))
        return false; 
    } 
    return !this.unknownFieldSchema.getFromMessage(paramT1).equals(this.unknownFieldSchema.getFromMessage(paramT2)) ? false : (this.hasExtensions ? this.extensionSchema.getExtensions(paramT1).equals(this.extensionSchema.getExtensions(paramT2)) : true);
  }
  
  int getSchemaSize() {
    return this.buffer.length * 3;
  }
  
  public int getSerializedSize(T paramT) {
    int i;
    if (this.proto3) {
      i = getSerializedSizeProto3(paramT);
    } else {
      i = getSerializedSizeProto2(paramT);
    } 
    return i;
  }
  
  public int hashCode(T paramT) {
    int i = this.buffer.length;
    int j = 0;
    int k;
    for (k = 0; j < i; k = n) {
      Object object;
      int n = typeAndOffsetAt(j);
      int i1 = numberAt(j);
      long l = offset(n);
      int i2 = type(n);
      n = 37;
      switch (i2) {
        default:
          n = k;
          break;
        case 68:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + UnsafeUtil.getObject(paramT, l).hashCode(); 
          break;
        case 67:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Internal.hashLong(oneofLongAt(paramT, l)); 
          break;
        case 66:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + oneofIntAt(paramT, l); 
          break;
        case 65:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Internal.hashLong(oneofLongAt(paramT, l)); 
          break;
        case 64:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + oneofIntAt(paramT, l); 
          break;
        case 63:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + oneofIntAt(paramT, l); 
          break;
        case 62:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + oneofIntAt(paramT, l); 
          break;
        case 61:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + UnsafeUtil.getObject(paramT, l).hashCode(); 
          break;
        case 60:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + UnsafeUtil.getObject(paramT, l).hashCode(); 
          break;
        case 59:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + ((String)UnsafeUtil.getObject(paramT, l)).hashCode(); 
          break;
        case 58:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Internal.hashBoolean(oneofBooleanAt(paramT, l)); 
          break;
        case 57:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + oneofIntAt(paramT, l); 
          break;
        case 56:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Internal.hashLong(oneofLongAt(paramT, l)); 
          break;
        case 55:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + oneofIntAt(paramT, l); 
          break;
        case 54:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Internal.hashLong(oneofLongAt(paramT, l)); 
          break;
        case 53:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Internal.hashLong(oneofLongAt(paramT, l)); 
          break;
        case 52:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Float.floatToIntBits(oneofFloatAt(paramT, l)); 
          break;
        case 51:
          n = k;
          if (isOneofPresent(paramT, i1, j))
            n = k * 53 + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(paramT, l))); 
          break;
        case 50:
          n = k * 53 + UnsafeUtil.getObject(paramT, l).hashCode();
          break;
        case 18:
        case 19:
        case 20:
        case 21:
        case 22:
        case 23:
        case 24:
        case 25:
        case 26:
        case 27:
        case 28:
        case 29:
        case 30:
        case 31:
        case 32:
        case 33:
        case 34:
        case 35:
        case 36:
        case 37:
        case 38:
        case 39:
        case 40:
        case 41:
        case 42:
        case 43:
        case 44:
        case 45:
        case 46:
        case 47:
        case 48:
        case 49:
          n = k * 53 + UnsafeUtil.getObject(paramT, l).hashCode();
          break;
        case 17:
          object = UnsafeUtil.getObject(paramT, l);
          if (object != null)
            n = object.hashCode(); 
          n = k * 53 + n;
          break;
        case 16:
          n = k * 53 + Internal.hashLong(UnsafeUtil.getLong(paramT, l));
          break;
        case 15:
          n = k * 53 + UnsafeUtil.getInt(paramT, l);
          break;
        case 14:
          n = k * 53 + Internal.hashLong(UnsafeUtil.getLong(paramT, l));
          break;
        case 13:
          n = k * 53 + UnsafeUtil.getInt(paramT, l);
          break;
        case 12:
          n = k * 53 + UnsafeUtil.getInt(paramT, l);
          break;
        case 11:
          n = k * 53 + UnsafeUtil.getInt(paramT, l);
          break;
        case 10:
          n = k * 53 + UnsafeUtil.getObject(paramT, l).hashCode();
          break;
        case 9:
          object = UnsafeUtil.getObject(paramT, l);
          if (object != null)
            n = object.hashCode(); 
          n = k * 53 + n;
          break;
        case 8:
          n = k * 53 + ((String)UnsafeUtil.getObject(paramT, l)).hashCode();
          break;
        case 7:
          n = k * 53 + Internal.hashBoolean(UnsafeUtil.getBoolean(paramT, l));
          break;
        case 6:
          n = k * 53 + UnsafeUtil.getInt(paramT, l);
          break;
        case 5:
          n = k * 53 + Internal.hashLong(UnsafeUtil.getLong(paramT, l));
          break;
        case 4:
          n = k * 53 + UnsafeUtil.getInt(paramT, l);
          break;
        case 3:
          n = k * 53 + Internal.hashLong(UnsafeUtil.getLong(paramT, l));
          break;
        case 2:
          n = k * 53 + Internal.hashLong(UnsafeUtil.getLong(paramT, l));
          break;
        case 1:
          n = k * 53 + Float.floatToIntBits(UnsafeUtil.getFloat(paramT, l));
          break;
        case 0:
          n = k * 53 + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(paramT, l)));
          break;
      } 
      j += 3;
    } 
    j = k * 53 + this.unknownFieldSchema.getFromMessage(paramT).hashCode();
    int m = j;
    if (this.hasExtensions)
      m = j * 53 + this.extensionSchema.getExtensions(paramT).hashCode(); 
    return m;
  }
  
  public final boolean isInitialized(T paramT) {
    int i = 0;
    int j = -1;
    int k = i;
    while (k < this.checkInitializedCount) {
      int i2;
      boolean bool;
      int m = this.intArray[k];
      int n = numberAt(m);
      int i1 = typeAndOffsetAt(m);
      if (!this.proto3) {
        i2 = this.buffer[m + 2];
        int i3 = 0xFFFFF & i2;
        int i4 = 1 << i2 >>> 20;
        i2 = j;
        bool = i4;
        if (i3 != j) {
          i = UNSAFE.getInt(paramT, i3);
          i2 = i3;
          bool = i4;
        } 
      } else {
        bool = false;
        i2 = j;
      } 
      if (isRequired(i1) && !isFieldPresent(paramT, m, i, bool))
        return false; 
      j = type(i1);
      if (j != 9 && j != 17) {
        if (j != 27) {
          if (j != 60 && j != 68) {
            switch (j) {
              case 50:
                if (!isMapInitialized(paramT, i1, m))
                  return false; 
                break;
              case 49:
                if (!isListInitialized(paramT, i1, m))
                  return false; 
                break;
            } 
          } else if (isOneofPresent(paramT, n, m) && !isInitialized(paramT, i1, getMessageFieldSchema(m))) {
            return false;
          } 
        } else {
        
        } 
      } else if (isFieldPresent(paramT, m, i, bool) && !isInitialized(paramT, i1, getMessageFieldSchema(m))) {
        return false;
      } 
      k++;
      j = i2;
    } 
    return !(this.hasExtensions && !this.extensionSchema.getExtensions(paramT).isInitialized());
  }
  
  public void makeImmutable(T paramT) {
    int i;
    for (i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
      long l = offset(typeAndOffsetAt(this.intArray[i]));
      Object object = UnsafeUtil.getObject(paramT, l);
      if (object != null)
        UnsafeUtil.putObject(paramT, l, this.mapFieldSchema.toImmutable(object)); 
    } 
    int j = this.intArray.length;
    for (i = this.repeatedFieldOffsetStart; i < j; i++)
      this.listFieldSchema.makeImmutableListAt(paramT, this.intArray[i]); 
    this.unknownFieldSchema.makeImmutable(paramT);
    if (this.hasExtensions)
      this.extensionSchema.makeImmutable(paramT); 
  }
  
  public void mergeFrom(T paramT, Reader paramReader, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    if (paramExtensionRegistryLite == null)
      throw new NullPointerException(); 
    mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, paramT, paramReader, paramExtensionRegistryLite);
  }
  
  public void mergeFrom(T paramT1, T paramT2) {
    if (paramT2 == null)
      throw new NullPointerException(); 
    for (byte b = 0; b < this.buffer.length; b += 3)
      mergeSingleField(paramT1, paramT2, b); 
    if (!this.proto3) {
      SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, paramT1, paramT2);
      if (this.hasExtensions)
        SchemaUtil.mergeExtensions(this.extensionSchema, paramT1, paramT2); 
    } 
  }
  
  public void mergeFrom(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, ArrayDecoders.Registers paramRegisters) throws IOException {
    if (this.proto3) {
      parseProto3Message(paramT, paramArrayOfbyte, paramInt1, paramInt2, paramRegisters);
    } else {
      parseProto2Message(paramT, paramArrayOfbyte, paramInt1, paramInt2, 0, paramRegisters);
    } 
  }
  
  public T newInstance() {
    return (T)this.newInstanceSchema.newInstance(this.defaultInstance);
  }
  
  int parseProto2Message(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, ArrayDecoders.Registers paramRegisters) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: astore #7
    //   3: aload_2
    //   4: astore #8
    //   6: iload #4
    //   8: istore #9
    //   10: iload #5
    //   12: istore #10
    //   14: aload #6
    //   16: astore #11
    //   18: getstatic com/google/protobuf/MessageSchema.UNSAFE : Lsun/misc/Unsafe;
    //   21: astore #12
    //   23: iconst_0
    //   24: istore #13
    //   26: iload #13
    //   28: istore #14
    //   30: iload #14
    //   32: istore #15
    //   34: iconst_m1
    //   35: istore #16
    //   37: iconst_m1
    //   38: istore #17
    //   40: aload_1
    //   41: astore #18
    //   43: iload_3
    //   44: iload #9
    //   46: if_icmpge -> 1960
    //   49: iload_3
    //   50: iconst_1
    //   51: iadd
    //   52: istore #14
    //   54: aload #8
    //   56: iload_3
    //   57: baload
    //   58: istore_3
    //   59: iload_3
    //   60: ifge -> 84
    //   63: iload_3
    //   64: aload #8
    //   66: iload #14
    //   68: aload #11
    //   70: invokestatic decodeVarint32 : (I[BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   73: istore #14
    //   75: aload #11
    //   77: getfield int1 : I
    //   80: istore_3
    //   81: goto -> 84
    //   84: iload_3
    //   85: iconst_3
    //   86: iushr
    //   87: istore #19
    //   89: iload_3
    //   90: bipush #7
    //   92: iand
    //   93: istore #20
    //   95: iload #19
    //   97: iload #16
    //   99: if_icmple -> 118
    //   102: aload #7
    //   104: iload #19
    //   106: iload #13
    //   108: iconst_3
    //   109: idiv
    //   110: invokespecial positionForFieldNumber : (II)I
    //   113: istore #16
    //   115: goto -> 130
    //   118: aload #7
    //   120: iload #19
    //   122: invokespecial positionForFieldNumber : (I)I
    //   125: istore #16
    //   127: goto -> 115
    //   130: iload #16
    //   132: iconst_m1
    //   133: if_icmpne -> 156
    //   136: iload_3
    //   137: istore #16
    //   139: iload #15
    //   141: istore_3
    //   142: iconst_0
    //   143: istore #9
    //   145: iload #14
    //   147: istore #15
    //   149: iload #10
    //   151: istore #14
    //   153: goto -> 1833
    //   156: aload #7
    //   158: getfield buffer : [I
    //   161: iload #16
    //   163: iconst_1
    //   164: iadd
    //   165: iaload
    //   166: istore #21
    //   168: iload #21
    //   170: invokestatic type : (I)I
    //   173: istore #22
    //   175: iload #21
    //   177: invokestatic offset : (I)J
    //   180: lstore #23
    //   182: iload #22
    //   184: bipush #17
    //   186: if_icmpgt -> 1389
    //   189: aload #7
    //   191: getfield buffer : [I
    //   194: iload #16
    //   196: iconst_2
    //   197: iadd
    //   198: iaload
    //   199: istore #10
    //   201: iconst_1
    //   202: istore #25
    //   204: iconst_1
    //   205: iload #10
    //   207: bipush #20
    //   209: iushr
    //   210: ishl
    //   211: istore #26
    //   213: iload #10
    //   215: ldc 1048575
    //   217: iand
    //   218: istore #27
    //   220: iload #15
    //   222: istore #13
    //   224: iload #17
    //   226: istore #10
    //   228: iload #27
    //   230: iload #17
    //   232: if_icmpeq -> 269
    //   235: iload #17
    //   237: iconst_m1
    //   238: if_icmpeq -> 253
    //   241: aload #12
    //   243: aload #18
    //   245: iload #17
    //   247: i2l
    //   248: iload #15
    //   250: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   253: aload #12
    //   255: aload #18
    //   257: iload #27
    //   259: i2l
    //   260: invokevirtual getInt : (Ljava/lang/Object;J)I
    //   263: istore #13
    //   265: iload #27
    //   267: istore #10
    //   269: iload #22
    //   271: tableswitch default -> 356, 0 -> 1275, 1 -> 1225, 2 -> 1151, 3 -> 1151, 4 -> 1097, 5 -> 1045, 6 -> 970, 7 -> 920, 8 -> 850, 9 -> 753, 10 -> 662, 11 -> 1097, 12 -> 569, 13 -> 970, 14 -> 1045, 15 -> 527, 16 -> 461, 17 -> 366
    //   356: iload #14
    //   358: istore #15
    //   360: iload_3
    //   361: istore #14
    //   363: goto -> 1375
    //   366: iload #20
    //   368: iconst_3
    //   369: if_icmpne -> 458
    //   372: aload #7
    //   374: iload #16
    //   376: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   379: aload_2
    //   380: iload #14
    //   382: iload #9
    //   384: iload #19
    //   386: iconst_3
    //   387: ishl
    //   388: iconst_4
    //   389: ior
    //   390: aload #11
    //   392: invokestatic decodeGroupField : (Lcom/google/protobuf/Schema;[BIIILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   395: istore #15
    //   397: iload #13
    //   399: iload #26
    //   401: iand
    //   402: ifne -> 422
    //   405: aload #12
    //   407: aload #18
    //   409: lload #23
    //   411: aload #11
    //   413: getfield object1 : Ljava/lang/Object;
    //   416: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   419: goto -> 448
    //   422: aload #12
    //   424: aload #18
    //   426: lload #23
    //   428: aload #12
    //   430: aload #18
    //   432: lload #23
    //   434: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   437: aload #11
    //   439: getfield object1 : Ljava/lang/Object;
    //   442: invokestatic mergeMessage : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   445: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   448: iload #13
    //   450: iload #26
    //   452: ior
    //   453: istore #14
    //   455: goto -> 699
    //   458: goto -> 517
    //   461: iload #20
    //   463: ifne -> 517
    //   466: aload_2
    //   467: iload #14
    //   469: aload #11
    //   471: invokestatic decodeVarint64 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   474: istore #15
    //   476: aload #12
    //   478: aload #18
    //   480: lload #23
    //   482: aload #11
    //   484: getfield long1 : J
    //   487: invokestatic decodeZigZag64 : (J)J
    //   490: invokevirtual putLong : (Ljava/lang/Object;JJ)V
    //   493: iload #13
    //   495: iload #26
    //   497: ior
    //   498: istore #17
    //   500: iload #16
    //   502: istore #13
    //   504: iload_3
    //   505: istore #14
    //   507: iload #19
    //   509: istore #16
    //   511: iload #15
    //   513: istore_3
    //   514: goto -> 717
    //   517: iload #14
    //   519: istore #15
    //   521: iload_3
    //   522: istore #14
    //   524: goto -> 750
    //   527: iload #20
    //   529: ifne -> 743
    //   532: aload_2
    //   533: iload #14
    //   535: aload #11
    //   537: invokestatic decodeVarint32 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   540: istore #15
    //   542: aload #12
    //   544: aload #18
    //   546: lload #23
    //   548: aload #11
    //   550: getfield int1 : I
    //   553: invokestatic decodeZigZag32 : (I)I
    //   556: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   559: iload #13
    //   561: iload #26
    //   563: ior
    //   564: istore #14
    //   566: goto -> 699
    //   569: iload #20
    //   571: ifne -> 743
    //   574: aload_2
    //   575: iload #14
    //   577: aload #11
    //   579: invokestatic decodeVarint32 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   582: istore #15
    //   584: aload #11
    //   586: getfield int1 : I
    //   589: istore #14
    //   591: aload #7
    //   593: iload #16
    //   595: invokespecial getEnumFieldVerifier : (I)Lcom/google/protobuf/Internal$EnumVerifier;
    //   598: astore #8
    //   600: aload #8
    //   602: ifnull -> 641
    //   605: aload #8
    //   607: iload #14
    //   609: invokeinterface isInRange : (I)Z
    //   614: ifeq -> 620
    //   617: goto -> 641
    //   620: aload_1
    //   621: invokestatic getMutableUnknownFields : (Ljava/lang/Object;)Lcom/google/protobuf/UnknownFieldSetLite;
    //   624: iload_3
    //   625: iload #14
    //   627: i2l
    //   628: invokestatic valueOf : (J)Ljava/lang/Long;
    //   631: invokevirtual storeField : (ILjava/lang/Object;)V
    //   634: iload #13
    //   636: istore #14
    //   638: goto -> 699
    //   641: aload #12
    //   643: aload #18
    //   645: lload #23
    //   647: iload #14
    //   649: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   652: iload #13
    //   654: iload #26
    //   656: ior
    //   657: istore #14
    //   659: goto -> 699
    //   662: iload #20
    //   664: iconst_2
    //   665: if_icmpne -> 743
    //   668: aload_2
    //   669: iload #14
    //   671: aload #11
    //   673: invokestatic decodeBytes : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   676: istore #15
    //   678: aload #12
    //   680: aload #18
    //   682: lload #23
    //   684: aload #11
    //   686: getfield object1 : Ljava/lang/Object;
    //   689: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   692: iload #13
    //   694: iload #26
    //   696: ior
    //   697: istore #14
    //   699: iload #14
    //   701: istore #17
    //   703: iload_3
    //   704: istore #14
    //   706: iload #16
    //   708: istore #13
    //   710: iload #19
    //   712: istore #16
    //   714: iload #15
    //   716: istore_3
    //   717: aload_2
    //   718: astore #8
    //   720: iload #10
    //   722: istore #19
    //   724: iload #5
    //   726: istore #10
    //   728: iload #4
    //   730: istore #9
    //   732: iload #17
    //   734: istore #15
    //   736: iload #19
    //   738: istore #17
    //   740: goto -> 40
    //   743: iload #14
    //   745: istore #15
    //   747: iload_3
    //   748: istore #14
    //   750: goto -> 1375
    //   753: iload #20
    //   755: iconst_2
    //   756: if_icmpne -> 847
    //   759: aload #7
    //   761: iload #16
    //   763: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   766: astore #8
    //   768: iload #4
    //   770: istore #9
    //   772: aload #8
    //   774: aload_2
    //   775: iload #14
    //   777: iload #9
    //   779: aload #11
    //   781: invokestatic decodeMessageField : (Lcom/google/protobuf/Schema;[BIILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   784: istore #15
    //   786: iload #13
    //   788: iload #26
    //   790: iand
    //   791: ifne -> 811
    //   794: aload #12
    //   796: aload #18
    //   798: lload #23
    //   800: aload #11
    //   802: getfield object1 : Ljava/lang/Object;
    //   805: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   808: goto -> 837
    //   811: aload #12
    //   813: aload #18
    //   815: lload #23
    //   817: aload #12
    //   819: aload #18
    //   821: lload #23
    //   823: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   826: aload #11
    //   828: getfield object1 : Ljava/lang/Object;
    //   831: invokestatic mergeMessage : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   834: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   837: iload #13
    //   839: iload #26
    //   841: ior
    //   842: istore #14
    //   844: goto -> 1005
    //   847: goto -> 1087
    //   850: iload #14
    //   852: istore #15
    //   854: aload_2
    //   855: astore #8
    //   857: iload #20
    //   859: iconst_2
    //   860: if_icmpne -> 1087
    //   863: ldc 536870912
    //   865: iload #21
    //   867: iand
    //   868: ifne -> 885
    //   871: aload #8
    //   873: iload #15
    //   875: aload #11
    //   877: invokestatic decodeString : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   880: istore #15
    //   882: goto -> 896
    //   885: aload #8
    //   887: iload #15
    //   889: aload #11
    //   891: invokestatic decodeStringRequireUtf8 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   894: istore #15
    //   896: aload #12
    //   898: aload #18
    //   900: lload #23
    //   902: aload #11
    //   904: getfield object1 : Ljava/lang/Object;
    //   907: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   910: iload #13
    //   912: iload #26
    //   914: ior
    //   915: istore #14
    //   917: goto -> 1005
    //   920: iload #20
    //   922: ifne -> 1087
    //   925: aload_2
    //   926: iload #14
    //   928: aload #11
    //   930: invokestatic decodeVarint64 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   933: istore #15
    //   935: aload #11
    //   937: getfield long1 : J
    //   940: lconst_0
    //   941: lcmp
    //   942: ifeq -> 948
    //   945: goto -> 951
    //   948: iconst_0
    //   949: istore #25
    //   951: aload #18
    //   953: lload #23
    //   955: iload #25
    //   957: invokestatic putBoolean : (Ljava/lang/Object;JZ)V
    //   960: iload #13
    //   962: iload #26
    //   964: ior
    //   965: istore #14
    //   967: goto -> 1005
    //   970: iload #14
    //   972: istore #15
    //   974: iload #20
    //   976: iconst_5
    //   977: if_icmpne -> 1087
    //   980: aload #12
    //   982: aload #18
    //   984: lload #23
    //   986: aload_2
    //   987: iload #15
    //   989: invokestatic decodeFixed32 : ([BI)I
    //   992: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   995: iinc #15, 4
    //   998: iload #13
    //   1000: iload #26
    //   1002: ior
    //   1003: istore #14
    //   1005: aload_2
    //   1006: astore #8
    //   1008: iload_3
    //   1009: istore #17
    //   1011: iload #16
    //   1013: istore #27
    //   1015: iload #19
    //   1017: istore #16
    //   1019: iload #10
    //   1021: istore #13
    //   1023: iload #15
    //   1025: istore_3
    //   1026: iload #27
    //   1028: istore #15
    //   1030: iload #17
    //   1032: istore #10
    //   1034: iload #14
    //   1036: istore #19
    //   1038: iload #13
    //   1040: istore #17
    //   1042: goto -> 1352
    //   1045: iload #14
    //   1047: istore #15
    //   1049: iload #20
    //   1051: iconst_1
    //   1052: if_icmpne -> 1087
    //   1055: aload #12
    //   1057: aload #18
    //   1059: lload #23
    //   1061: aload_2
    //   1062: iload #15
    //   1064: invokestatic decodeFixed64 : ([BI)J
    //   1067: invokevirtual putLong : (Ljava/lang/Object;JJ)V
    //   1070: iload #15
    //   1072: bipush #8
    //   1074: iadd
    //   1075: istore #14
    //   1077: iload #13
    //   1079: iload #26
    //   1081: ior
    //   1082: istore #15
    //   1084: goto -> 1323
    //   1087: iload #14
    //   1089: istore #15
    //   1091: iload_3
    //   1092: istore #14
    //   1094: goto -> 1375
    //   1097: iload_3
    //   1098: istore #17
    //   1100: iload #14
    //   1102: istore #27
    //   1104: iload #27
    //   1106: istore #15
    //   1108: iload #17
    //   1110: istore #14
    //   1112: iload #20
    //   1114: ifne -> 1375
    //   1117: aload_2
    //   1118: iload #27
    //   1120: aload #11
    //   1122: invokestatic decodeVarint32 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1125: istore #14
    //   1127: aload #12
    //   1129: aload #18
    //   1131: lload #23
    //   1133: aload #11
    //   1135: getfield int1 : I
    //   1138: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   1141: iload #13
    //   1143: iload #26
    //   1145: ior
    //   1146: istore #15
    //   1148: goto -> 1323
    //   1151: iload #10
    //   1153: istore #17
    //   1155: iload_3
    //   1156: istore #22
    //   1158: iload #14
    //   1160: istore #27
    //   1162: aload_2
    //   1163: astore #8
    //   1165: iload #27
    //   1167: istore #15
    //   1169: iload #22
    //   1171: istore #14
    //   1173: iload #20
    //   1175: ifne -> 1375
    //   1178: aload #8
    //   1180: iload #27
    //   1182: aload #11
    //   1184: invokestatic decodeVarint64 : ([BILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1187: istore #14
    //   1189: aload #12
    //   1191: aload #18
    //   1193: lload #23
    //   1195: aload #11
    //   1197: getfield long1 : J
    //   1200: invokevirtual putLong : (Ljava/lang/Object;JJ)V
    //   1203: iload #13
    //   1205: iload #26
    //   1207: ior
    //   1208: istore #10
    //   1210: iload #16
    //   1212: istore #15
    //   1214: iload #19
    //   1216: istore #16
    //   1218: iload #10
    //   1220: istore #19
    //   1222: goto -> 1346
    //   1225: iload_3
    //   1226: istore #17
    //   1228: iload #14
    //   1230: istore #27
    //   1232: iload #27
    //   1234: istore #15
    //   1236: iload #17
    //   1238: istore #14
    //   1240: iload #20
    //   1242: iconst_5
    //   1243: if_icmpne -> 1375
    //   1246: aload #18
    //   1248: lload #23
    //   1250: aload_2
    //   1251: iload #27
    //   1253: invokestatic decodeFloat : ([BI)F
    //   1256: invokestatic putFloat : (Ljava/lang/Object;JF)V
    //   1259: iload #27
    //   1261: iconst_4
    //   1262: iadd
    //   1263: istore #14
    //   1265: iload #13
    //   1267: iload #26
    //   1269: ior
    //   1270: istore #15
    //   1272: goto -> 1323
    //   1275: iload_3
    //   1276: istore #17
    //   1278: iload #14
    //   1280: istore #27
    //   1282: iload #27
    //   1284: istore #15
    //   1286: iload #17
    //   1288: istore #14
    //   1290: iload #20
    //   1292: iconst_1
    //   1293: if_icmpne -> 1375
    //   1296: aload #18
    //   1298: lload #23
    //   1300: aload_2
    //   1301: iload #27
    //   1303: invokestatic decodeDouble : ([BI)D
    //   1306: invokestatic putDouble : (Ljava/lang/Object;JD)V
    //   1309: iload #27
    //   1311: bipush #8
    //   1313: iadd
    //   1314: istore #14
    //   1316: iload #13
    //   1318: iload #26
    //   1320: ior
    //   1321: istore #15
    //   1323: iload #10
    //   1325: istore #17
    //   1327: aload_2
    //   1328: astore #8
    //   1330: iload #19
    //   1332: istore #10
    //   1334: iload #15
    //   1336: istore #19
    //   1338: iload #16
    //   1340: istore #15
    //   1342: iload #10
    //   1344: istore #16
    //   1346: iload_3
    //   1347: istore #10
    //   1349: iload #14
    //   1351: istore_3
    //   1352: iload #5
    //   1354: istore #27
    //   1356: iload #15
    //   1358: istore #13
    //   1360: iload #10
    //   1362: istore #14
    //   1364: iload #19
    //   1366: istore #15
    //   1368: iload #27
    //   1370: istore #10
    //   1372: goto -> 40
    //   1375: iload #10
    //   1377: istore #17
    //   1379: iload #13
    //   1381: istore_3
    //   1382: iload #16
    //   1384: istore #9
    //   1386: goto -> 1729
    //   1389: iload #19
    //   1391: istore #13
    //   1393: iload_3
    //   1394: istore #10
    //   1396: iload #22
    //   1398: bipush #27
    //   1400: if_icmpne -> 1536
    //   1403: iload #20
    //   1405: iconst_2
    //   1406: if_icmpne -> 1533
    //   1409: aload #12
    //   1411: aload #18
    //   1413: lload #23
    //   1415: invokevirtual getObject : (Ljava/lang/Object;J)Ljava/lang/Object;
    //   1418: checkcast com/google/protobuf/Internal$ProtobufList
    //   1421: astore #28
    //   1423: aload #28
    //   1425: astore #29
    //   1427: aload #28
    //   1429: invokeinterface isModifiable : ()Z
    //   1434: ifne -> 1486
    //   1437: aload #28
    //   1439: invokeinterface size : ()I
    //   1444: istore #19
    //   1446: iload #19
    //   1448: ifne -> 1458
    //   1451: bipush #10
    //   1453: istore #19
    //   1455: goto -> 1464
    //   1458: iload #19
    //   1460: iconst_2
    //   1461: imul
    //   1462: istore #19
    //   1464: aload #28
    //   1466: iload #19
    //   1468: invokeinterface mutableCopyWithCapacity : (I)Lcom/google/protobuf/Internal$ProtobufList;
    //   1473: astore #29
    //   1475: aload #12
    //   1477: aload #18
    //   1479: lload #23
    //   1481: aload #29
    //   1483: invokevirtual putObject : (Ljava/lang/Object;JLjava/lang/Object;)V
    //   1486: aload #7
    //   1488: iload #16
    //   1490: invokespecial getMessageFieldSchema : (I)Lcom/google/protobuf/Schema;
    //   1493: iload #10
    //   1495: aload #8
    //   1497: iload #14
    //   1499: iload #9
    //   1501: aload #29
    //   1503: aload #11
    //   1505: invokestatic decodeMessageList : (Lcom/google/protobuf/Schema;I[BIILcom/google/protobuf/Internal$ProtobufList;Lcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1508: istore #14
    //   1510: iload #16
    //   1512: istore #19
    //   1514: iload #13
    //   1516: istore #16
    //   1518: iload #15
    //   1520: istore #10
    //   1522: iload #19
    //   1524: istore #15
    //   1526: iload #10
    //   1528: istore #19
    //   1530: goto -> 1346
    //   1533: goto -> 1714
    //   1536: iload #15
    //   1538: istore_3
    //   1539: iload #22
    //   1541: bipush #49
    //   1543: if_icmpgt -> 1654
    //   1546: iload #21
    //   1548: i2l
    //   1549: lstore #30
    //   1551: iload #16
    //   1553: istore #27
    //   1555: aload #7
    //   1557: aload #18
    //   1559: aload #8
    //   1561: iload #14
    //   1563: iload #9
    //   1565: iload #10
    //   1567: iload #13
    //   1569: iload #20
    //   1571: iload #16
    //   1573: lload #30
    //   1575: iload #22
    //   1577: lload #23
    //   1579: aload #6
    //   1581: invokespecial parseRepeatedField : (Ljava/lang/Object;[BIIIIIIJIJLcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1584: istore #16
    //   1586: iload #16
    //   1588: iload #14
    //   1590: if_icmpeq -> 1632
    //   1593: aload_2
    //   1594: astore #8
    //   1596: iload #4
    //   1598: istore #9
    //   1600: aload #6
    //   1602: astore #11
    //   1604: iload_3
    //   1605: istore #15
    //   1607: iload #10
    //   1609: istore #14
    //   1611: iload #5
    //   1613: istore #10
    //   1615: aload_0
    //   1616: astore #7
    //   1618: iload #16
    //   1620: istore_3
    //   1621: iload #13
    //   1623: istore #16
    //   1625: iload #27
    //   1627: istore #13
    //   1629: goto -> 40
    //   1632: iload #16
    //   1634: istore #15
    //   1636: iload #10
    //   1638: istore #16
    //   1640: iload #5
    //   1642: istore #14
    //   1644: aload_0
    //   1645: astore #7
    //   1647: iload #27
    //   1649: istore #9
    //   1651: goto -> 1833
    //   1654: iload #14
    //   1656: istore #27
    //   1658: iload #16
    //   1660: istore #9
    //   1662: iload #22
    //   1664: bipush #50
    //   1666: if_icmpne -> 1740
    //   1669: iload #20
    //   1671: iconst_2
    //   1672: if_icmpne -> 1711
    //   1675: aload_0
    //   1676: aload_1
    //   1677: aload_2
    //   1678: iload #27
    //   1680: iload #4
    //   1682: iload #9
    //   1684: lload #23
    //   1686: aload #6
    //   1688: invokespecial parseMapField : (Ljava/lang/Object;[BIIIJLcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1691: istore #16
    //   1693: iload #16
    //   1695: istore #14
    //   1697: iload #16
    //   1699: iload #27
    //   1701: if_icmpeq -> 1819
    //   1704: iload #16
    //   1706: istore #14
    //   1708: goto -> 1783
    //   1711: aload_0
    //   1712: astore #7
    //   1714: iload #15
    //   1716: istore_3
    //   1717: iload #14
    //   1719: istore #15
    //   1721: iload #10
    //   1723: istore #14
    //   1725: iload #16
    //   1727: istore #9
    //   1729: iload #14
    //   1731: istore #16
    //   1733: iload #5
    //   1735: istore #14
    //   1737: goto -> 1833
    //   1740: aload_0
    //   1741: aload_1
    //   1742: aload_2
    //   1743: iload #27
    //   1745: iload #4
    //   1747: iload #10
    //   1749: iload #13
    //   1751: iload #20
    //   1753: iload #21
    //   1755: iload #22
    //   1757: lload #23
    //   1759: iload #9
    //   1761: aload #6
    //   1763: invokespecial parseOneofField : (Ljava/lang/Object;[BIIIIIIIJILcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1766: istore #16
    //   1768: iload #16
    //   1770: istore #14
    //   1772: iload #16
    //   1774: iload #27
    //   1776: if_icmpeq -> 1819
    //   1779: iload #16
    //   1781: istore #14
    //   1783: aload #6
    //   1785: astore #11
    //   1787: aload_0
    //   1788: astore #7
    //   1790: iload #13
    //   1792: istore #16
    //   1794: iload_3
    //   1795: istore #19
    //   1797: iload #10
    //   1799: istore #15
    //   1801: iload #5
    //   1803: istore #10
    //   1805: iload #14
    //   1807: istore_3
    //   1808: iload #9
    //   1810: istore #13
    //   1812: iload #15
    //   1814: istore #14
    //   1816: goto -> 1946
    //   1819: aload_0
    //   1820: astore #7
    //   1822: iload #14
    //   1824: istore #15
    //   1826: iload #10
    //   1828: istore #14
    //   1830: goto -> 1729
    //   1833: iload #16
    //   1835: iload #14
    //   1837: if_icmpne -> 1851
    //   1840: iload #14
    //   1842: ifeq -> 1851
    //   1845: iload_3
    //   1846: istore #5
    //   1848: goto -> 1975
    //   1851: aload #7
    //   1853: getfield hasExtensions : Z
    //   1856: ifeq -> 1902
    //   1859: aload #6
    //   1861: astore #8
    //   1863: aload #8
    //   1865: getfield extensionRegistry : Lcom/google/protobuf/ExtensionRegistryLite;
    //   1868: invokestatic getEmptyRegistry : ()Lcom/google/protobuf/ExtensionRegistryLite;
    //   1871: if_acmpeq -> 1902
    //   1874: iload #16
    //   1876: aload_2
    //   1877: iload #15
    //   1879: iload #4
    //   1881: aload_1
    //   1882: aload #7
    //   1884: getfield defaultInstance : Lcom/google/protobuf/MessageLite;
    //   1887: aload #7
    //   1889: getfield unknownFieldSchema : Lcom/google/protobuf/UnknownFieldSchema;
    //   1892: aload #8
    //   1894: invokestatic decodeExtensionOrUnknownField : (I[BIILjava/lang/Object;Lcom/google/protobuf/MessageLite;Lcom/google/protobuf/UnknownFieldSchema;Lcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1897: istore #15
    //   1899: goto -> 1920
    //   1902: iload #16
    //   1904: aload_2
    //   1905: iload #15
    //   1907: iload #4
    //   1909: aload_1
    //   1910: invokestatic getMutableUnknownFields : (Ljava/lang/Object;)Lcom/google/protobuf/UnknownFieldSetLite;
    //   1913: aload #6
    //   1915: invokestatic decodeUnknownField : (I[BIILcom/google/protobuf/UnknownFieldSetLite;Lcom/google/protobuf/ArrayDecoders$Registers;)I
    //   1918: istore #15
    //   1920: iload #14
    //   1922: istore #10
    //   1924: iload #16
    //   1926: istore #14
    //   1928: aload #6
    //   1930: astore #11
    //   1932: iload #19
    //   1934: istore #16
    //   1936: iload_3
    //   1937: istore #19
    //   1939: iload #9
    //   1941: istore #13
    //   1943: iload #15
    //   1945: istore_3
    //   1946: iload #4
    //   1948: istore #9
    //   1950: aload_2
    //   1951: astore #8
    //   1953: iload #19
    //   1955: istore #15
    //   1957: goto -> 40
    //   1960: iload #15
    //   1962: istore #5
    //   1964: iload #14
    //   1966: istore #16
    //   1968: iload #10
    //   1970: istore #14
    //   1972: iload_3
    //   1973: istore #15
    //   1975: iload #17
    //   1977: iconst_m1
    //   1978: if_icmpeq -> 1995
    //   1981: aload #12
    //   1983: aload_1
    //   1984: iload #17
    //   1986: i2l
    //   1987: iload #5
    //   1989: invokevirtual putInt : (Ljava/lang/Object;JI)V
    //   1992: goto -> 1995
    //   1995: aload_1
    //   1996: astore_2
    //   1997: aconst_null
    //   1998: astore_1
    //   1999: aload #7
    //   2001: getfield checkInitializedCount : I
    //   2004: istore_3
    //   2005: iload_3
    //   2006: aload #7
    //   2008: getfield repeatedFieldOffsetStart : I
    //   2011: if_icmpge -> 2043
    //   2014: aload #7
    //   2016: aload_2
    //   2017: aload #7
    //   2019: getfield intArray : [I
    //   2022: iload_3
    //   2023: iaload
    //   2024: aload_1
    //   2025: aload #7
    //   2027: getfield unknownFieldSchema : Lcom/google/protobuf/UnknownFieldSchema;
    //   2030: invokespecial filterMapUnknownEnumValues : (Ljava/lang/Object;ILjava/lang/Object;Lcom/google/protobuf/UnknownFieldSchema;)Ljava/lang/Object;
    //   2033: checkcast com/google/protobuf/UnknownFieldSetLite
    //   2036: astore_1
    //   2037: iinc #3, 1
    //   2040: goto -> 2005
    //   2043: aload_1
    //   2044: ifnull -> 2057
    //   2047: aload #7
    //   2049: getfield unknownFieldSchema : Lcom/google/protobuf/UnknownFieldSchema;
    //   2052: aload_2
    //   2053: aload_1
    //   2054: invokevirtual setBuilderToMessage : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   2057: iload #14
    //   2059: ifne -> 2073
    //   2062: iload #15
    //   2064: iload #4
    //   2066: if_icmpeq -> 2090
    //   2069: invokestatic parseFailure : ()Lcom/google/protobuf/InvalidProtocolBufferException;
    //   2072: athrow
    //   2073: iload #15
    //   2075: iload #4
    //   2077: if_icmpgt -> 2093
    //   2080: iload #16
    //   2082: iload #14
    //   2084: if_icmpeq -> 2090
    //   2087: goto -> 2093
    //   2090: iload #15
    //   2092: ireturn
    //   2093: invokestatic parseFailure : ()Lcom/google/protobuf/InvalidProtocolBufferException;
    //   2096: athrow
  }
  
  public void writeTo(T paramT, Writer paramWriter) throws IOException {
    if (paramWriter.fieldOrder() == Writer.FieldOrder.DESCENDING) {
      writeFieldsInDescendingOrder(paramT, paramWriter);
    } else if (this.proto3) {
      writeFieldsInAscendingOrderProto3(paramT, paramWriter);
    } else {
      writeFieldsInAscendingOrderProto2(paramT, paramWriter);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\MessageSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */