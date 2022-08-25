package com.google.protobuf;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

final class SchemaUtil {
  private static final int DEFAULT_LOOK_UP_START_NUMBER = 40;
  
  private static final Class<?> GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
  
  private static final UnknownFieldSchema<?, ?> PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
  
  private static final UnknownFieldSchema<?, ?> PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
  
  private static final UnknownFieldSchema<?, ?> UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();
  
  static int computeSizeBoolList(int paramInt, List<?> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(i)) : (i * CodedOutputStream.computeBoolSize(paramInt, true)));
  }
  
  static int computeSizeBoolListNoTag(List<?> paramList) {
    return paramList.size();
  }
  
  static int computeSizeByteStringList(int paramInt, List<ByteString> paramList) {
    int i = paramList.size();
    int j = 0;
    if (i == 0)
      return 0; 
    i *= CodedOutputStream.computeTagSize(paramInt);
    paramInt = j;
    j = i;
    while (paramInt < paramList.size()) {
      j += CodedOutputStream.computeBytesSizeNoTag(paramList.get(paramInt));
      paramInt++;
    } 
    return j;
  }
  
  static int computeSizeEnumList(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    if (i == 0)
      return 0; 
    int j = computeSizeEnumListNoTag(paramList);
    return paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(j)) : (j + i * CodedOutputStream.computeTagSize(paramInt));
  }
  
  static int computeSizeEnumListNoTag(List<Integer> paramList) {
    int i = paramList.size();
    int j = 0;
    int k = 0;
    if (i == 0)
      return 0; 
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int m = 0;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeEnumSizeNoTag(paramList.getInt(k));
          k++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      k = j;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeEnumSizeNoTag(((Integer)paramList.get(k)).intValue());
          k++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static int computeSizeFixed32List(int paramInt, List<?> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(i * 4)) : (i * CodedOutputStream.computeFixed32Size(paramInt, 0)));
  }
  
  static int computeSizeFixed32ListNoTag(List<?> paramList) {
    return paramList.size() * 4;
  }
  
  static int computeSizeFixed64List(int paramInt, List<?> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(i * 8)) : (i * CodedOutputStream.computeFixed64Size(paramInt, 0L)));
  }
  
  static int computeSizeFixed64ListNoTag(List<?> paramList) {
    return paramList.size() * 8;
  }
  
  static int computeSizeGroupList(int paramInt, List<MessageLite> paramList) {
    int i = paramList.size();
    byte b = 0;
    if (i == 0)
      return 0; 
    int j = 0;
    while (b < i) {
      j += CodedOutputStream.computeGroupSize(paramInt, paramList.get(b));
      b++;
    } 
    return j;
  }
  
  static int computeSizeGroupList(int paramInt, List<MessageLite> paramList, Schema paramSchema) {
    int i = paramList.size();
    byte b = 0;
    if (i == 0)
      return 0; 
    int j = 0;
    while (b < i) {
      j += CodedOutputStream.computeGroupSize(paramInt, paramList.get(b), paramSchema);
      b++;
    } 
    return j;
  }
  
  static int computeSizeInt32List(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    if (i == 0)
      return 0; 
    int j = computeSizeInt32ListNoTag(paramList);
    return paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(j)) : (j + i * CodedOutputStream.computeTagSize(paramInt));
  }
  
  static int computeSizeInt32ListNoTag(List<Integer> paramList) {
    int i = paramList.size();
    int j = 0;
    int k = 0;
    if (i == 0)
      return 0; 
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int m = 0;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeInt32SizeNoTag(paramList.getInt(k));
          k++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      k = j;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeInt32SizeNoTag(((Integer)paramList.get(k)).intValue());
          k++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static int computeSizeInt64List(int paramInt, List<Long> paramList, boolean paramBoolean) {
    if (paramList.size() == 0)
      return 0; 
    int i = computeSizeInt64ListNoTag(paramList);
    return paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(i)) : (i + paramList.size() * CodedOutputStream.computeTagSize(paramInt));
  }
  
  static int computeSizeInt64ListNoTag(List<Long> paramList) {
    int i = paramList.size();
    int j = 0;
    int k = 0;
    if (i == 0)
      return 0; 
    if (paramList instanceof LongArrayList) {
      paramList = paramList;
      int m = 0;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeInt64SizeNoTag(paramList.getLong(k));
          k++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      k = j;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeInt64SizeNoTag(((Long)paramList.get(k)).longValue());
          k++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static int computeSizeMessage(int paramInt, Object paramObject, Schema paramSchema) {
    return (paramObject instanceof LazyFieldLite) ? CodedOutputStream.computeLazyFieldSize(paramInt, (LazyFieldLite)paramObject) : CodedOutputStream.computeMessageSize(paramInt, (MessageLite)paramObject, paramSchema);
  }
  
  static int computeSizeMessageList(int paramInt, List<?> paramList) {
    int i = paramList.size();
    byte b = 0;
    if (i == 0)
      return 0; 
    paramInt = CodedOutputStream.computeTagSize(paramInt) * i;
    while (b < i) {
      Object object = paramList.get(b);
      if (object instanceof LazyFieldLite) {
        paramInt += CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite)object);
      } else {
        paramInt += CodedOutputStream.computeMessageSizeNoTag((MessageLite)object);
      } 
      b++;
    } 
    return paramInt;
  }
  
  static int computeSizeMessageList(int paramInt, List<?> paramList, Schema paramSchema) {
    int i = paramList.size();
    byte b = 0;
    if (i == 0)
      return 0; 
    paramInt = CodedOutputStream.computeTagSize(paramInt) * i;
    while (b < i) {
      Object object = paramList.get(b);
      if (object instanceof LazyFieldLite) {
        paramInt += CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite)object);
      } else {
        paramInt += CodedOutputStream.computeMessageSizeNoTag((MessageLite)object, paramSchema);
      } 
      b++;
    } 
    return paramInt;
  }
  
  static int computeSizeSInt32List(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    if (i == 0)
      return 0; 
    int j = computeSizeSInt32ListNoTag(paramList);
    return paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(j)) : (j + i * CodedOutputStream.computeTagSize(paramInt));
  }
  
  static int computeSizeSInt32ListNoTag(List<Integer> paramList) {
    int i = paramList.size();
    int j = 0;
    int k = 0;
    if (i == 0)
      return 0; 
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int m = 0;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeSInt32SizeNoTag(paramList.getInt(k));
          k++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      k = j;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeSInt32SizeNoTag(((Integer)paramList.get(k)).intValue());
          k++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static int computeSizeSInt64List(int paramInt, List<Long> paramList, boolean paramBoolean) {
    int i = paramList.size();
    if (i == 0)
      return 0; 
    int j = computeSizeSInt64ListNoTag(paramList);
    return paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(j)) : (j + i * CodedOutputStream.computeTagSize(paramInt));
  }
  
  static int computeSizeSInt64ListNoTag(List<Long> paramList) {
    int i = paramList.size();
    int j = 0;
    int k = 0;
    if (i == 0)
      return 0; 
    if (paramList instanceof LongArrayList) {
      paramList = paramList;
      int m = 0;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeSInt64SizeNoTag(paramList.getLong(k));
          k++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      k = j;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeSInt64SizeNoTag(((Long)paramList.get(k)).longValue());
          k++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static int computeSizeStringList(int paramInt, List<?> paramList) {
    int i = paramList.size();
    byte b1 = 0;
    byte b2 = 0;
    if (i == 0)
      return 0; 
    int j = CodedOutputStream.computeTagSize(paramInt) * i;
    paramInt = j;
    if (paramList instanceof LazyStringList) {
      paramList = paramList;
      paramInt = j;
      b1 = b2;
      while (true) {
        j = paramInt;
        if (b1 < i) {
          Object object = paramList.getRaw(b1);
          if (object instanceof ByteString) {
            paramInt += CodedOutputStream.computeBytesSizeNoTag((ByteString)object);
          } else {
            paramInt += CodedOutputStream.computeStringSizeNoTag((String)object);
          } 
          b1++;
          continue;
        } 
        break;
      } 
    } else {
      while (true) {
        j = paramInt;
        if (b1 < i) {
          Object object = paramList.get(b1);
          if (object instanceof ByteString) {
            paramInt += CodedOutputStream.computeBytesSizeNoTag((ByteString)object);
          } else {
            paramInt += CodedOutputStream.computeStringSizeNoTag((String)object);
          } 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static int computeSizeUInt32List(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    if (i == 0)
      return 0; 
    int j = computeSizeUInt32ListNoTag(paramList);
    return paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(j)) : (j + i * CodedOutputStream.computeTagSize(paramInt));
  }
  
  static int computeSizeUInt32ListNoTag(List<Integer> paramList) {
    int i = paramList.size();
    int j = 0;
    int k = 0;
    if (i == 0)
      return 0; 
    if (paramList instanceof IntArrayList) {
      paramList = paramList;
      int m = 0;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeUInt32SizeNoTag(paramList.getInt(k));
          k++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      k = j;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeUInt32SizeNoTag(((Integer)paramList.get(k)).intValue());
          k++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static int computeSizeUInt64List(int paramInt, List<Long> paramList, boolean paramBoolean) {
    int i = paramList.size();
    if (i == 0)
      return 0; 
    int j = computeSizeUInt64ListNoTag(paramList);
    return paramBoolean ? (CodedOutputStream.computeTagSize(paramInt) + CodedOutputStream.computeLengthDelimitedFieldSize(j)) : (j + i * CodedOutputStream.computeTagSize(paramInt));
  }
  
  static int computeSizeUInt64ListNoTag(List<Long> paramList) {
    int i = paramList.size();
    int j = 0;
    int k = 0;
    if (i == 0)
      return 0; 
    if (paramList instanceof LongArrayList) {
      paramList = paramList;
      int m = 0;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeUInt64SizeNoTag(paramList.getLong(k));
          k++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      k = j;
      while (true) {
        j = m;
        if (k < i) {
          m += CodedOutputStream.computeUInt64SizeNoTag(((Long)paramList.get(k)).longValue());
          k++;
          continue;
        } 
        break;
      } 
    } 
    return j;
  }
  
  static <UT, UB> UB filterUnknownEnumList(int paramInt, List<Integer> paramList, Internal.EnumLiteMap<?> paramEnumLiteMap, UB paramUB, UnknownFieldSchema<UT, UB> paramUnknownFieldSchema) {
    UB uB;
    if (paramEnumLiteMap == null)
      return paramUB; 
    if (paramList instanceof java.util.RandomAccess) {
      int i = paramList.size();
      byte b1 = 0;
      byte b2 = 0;
      while (b1 < i) {
        int j = ((Integer)paramList.get(b1)).intValue();
        if (paramEnumLiteMap.findValueByNumber(j) != null) {
          if (b1 != b2)
            paramList.set(b2, Integer.valueOf(j)); 
          b2++;
        } else {
          paramUB = storeUnknownEnum(paramInt, j, paramUB, paramUnknownFieldSchema);
        } 
        b1++;
      } 
      uB = paramUB;
      if (b2 != i) {
        paramList.subList(b2, i).clear();
        uB = paramUB;
      } 
    } else {
      Iterator<Integer> iterator = paramList.iterator();
      while (true) {
        uB = paramUB;
        if (iterator.hasNext()) {
          int i = ((Integer)iterator.next()).intValue();
          if (paramEnumLiteMap.findValueByNumber(i) == null) {
            paramUB = storeUnknownEnum(paramInt, i, paramUB, paramUnknownFieldSchema);
            iterator.remove();
          } 
          continue;
        } 
        break;
      } 
    } 
    return uB;
  }
  
  static <UT, UB> UB filterUnknownEnumList(int paramInt, List<Integer> paramList, Internal.EnumVerifier paramEnumVerifier, UB paramUB, UnknownFieldSchema<UT, UB> paramUnknownFieldSchema) {
    UB uB;
    if (paramEnumVerifier == null)
      return paramUB; 
    if (paramList instanceof java.util.RandomAccess) {
      int i = paramList.size();
      byte b1 = 0;
      byte b2 = 0;
      while (b1 < i) {
        int j = ((Integer)paramList.get(b1)).intValue();
        if (paramEnumVerifier.isInRange(j)) {
          if (b1 != b2)
            paramList.set(b2, Integer.valueOf(j)); 
          b2++;
        } else {
          paramUB = storeUnknownEnum(paramInt, j, paramUB, paramUnknownFieldSchema);
        } 
        b1++;
      } 
      uB = paramUB;
      if (b2 != i) {
        paramList.subList(b2, i).clear();
        uB = paramUB;
      } 
    } else {
      Iterator<Integer> iterator = paramList.iterator();
      while (true) {
        uB = paramUB;
        if (iterator.hasNext()) {
          int i = ((Integer)iterator.next()).intValue();
          if (!paramEnumVerifier.isInRange(i)) {
            paramUB = storeUnknownEnum(paramInt, i, paramUB, paramUnknownFieldSchema);
            iterator.remove();
          } 
          continue;
        } 
        break;
      } 
    } 
    return uB;
  }
  
  private static Class<?> getGeneratedMessageClass() {
    try {
      return Class.forName("com.google.protobuf.GeneratedMessageV3");
    } catch (Throwable throwable) {
      return null;
    } 
  }
  
  static Object getMapDefaultEntry(Class<?> paramClass, String paramString) {
    try {
      StringBuilder stringBuilder1;
      StringBuilder stringBuilder2 = new StringBuilder();
      this();
      stringBuilder2.append(paramClass.getName());
      stringBuilder2.append("$");
      stringBuilder2.append(toCamelCase(paramString, true));
      stringBuilder2.append("DefaultEntryHolder");
      Field[] arrayOfField = Class.forName(stringBuilder2.toString()).getDeclaredFields();
      if (arrayOfField.length != 1) {
        IllegalStateException illegalStateException = new IllegalStateException();
        stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Unable to look up map field default entry holder class for ");
        stringBuilder1.append(paramString);
        stringBuilder1.append(" in ");
        stringBuilder1.append(paramClass.getName());
        this(stringBuilder1.toString());
        throw illegalStateException;
      } 
      return UnsafeUtil.getStaticObject((Field)stringBuilder1[0]);
    } catch (Throwable throwable) {
      throw new RuntimeException(throwable);
    } 
  }
  
  private static UnknownFieldSchema<?, ?> getUnknownFieldSetSchema(boolean paramBoolean) {
    try {
      Class<?> clazz = getUnknownFieldSetSchemaClass();
      return (clazz == null) ? null : clazz.getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(paramBoolean) });
    } catch (Throwable throwable) {
      return null;
    } 
  }
  
  private static Class<?> getUnknownFieldSetSchemaClass() {
    try {
      return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
    } catch (Throwable throwable) {
      return null;
    } 
  }
  
  static <T, FT extends FieldSet.FieldDescriptorLite<FT>> void mergeExtensions(ExtensionSchema<FT> paramExtensionSchema, T paramT1, T paramT2) {
    FieldSet<FT> fieldSet = paramExtensionSchema.getExtensions(paramT2);
    if (!fieldSet.isEmpty())
      paramExtensionSchema.getMutableExtensions(paramT1).mergeFrom(fieldSet); 
  }
  
  static <T> void mergeMap(MapFieldSchema paramMapFieldSchema, T paramT1, T paramT2, long paramLong) {
    UnsafeUtil.putObject(paramT1, paramLong, paramMapFieldSchema.mergeFrom(UnsafeUtil.getObject(paramT1, paramLong), UnsafeUtil.getObject(paramT2, paramLong)));
  }
  
  static <T, UT, UB> void mergeUnknownFields(UnknownFieldSchema<UT, UB> paramUnknownFieldSchema, T paramT1, T paramT2) {
    paramUnknownFieldSchema.setToMessage(paramT1, paramUnknownFieldSchema.merge(paramUnknownFieldSchema.getFromMessage(paramT1), paramUnknownFieldSchema.getFromMessage(paramT2)));
  }
  
  public static UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema() {
    return PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
  }
  
  public static UnknownFieldSchema<?, ?> proto3UnknownFieldSetSchema() {
    return PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
  }
  
  public static void requireGeneratedMessage(Class<?> paramClass) {
    if (!GeneratedMessageLite.class.isAssignableFrom(paramClass) && GENERATED_MESSAGE_CLASS != null && !GENERATED_MESSAGE_CLASS.isAssignableFrom(paramClass))
      throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite"); 
  }
  
  static boolean safeEquals(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2 || (paramObject1 != null && paramObject1.equals(paramObject2)));
  }
  
  public static boolean shouldUseTableSwitch(int paramInt1, int paramInt2, int paramInt3) {
    boolean bool = true;
    if (paramInt2 < 40)
      return true; 
    long l1 = paramInt2;
    long l2 = paramInt1;
    long l3 = paramInt3;
    if (l1 - l2 + 1L + 9L > 2L * l3 + 3L + (l3 + 3L) * 3L)
      bool = false; 
    return bool;
  }
  
  public static boolean shouldUseTableSwitch(FieldInfo[] paramArrayOfFieldInfo) {
    return (paramArrayOfFieldInfo.length == 0) ? false : shouldUseTableSwitch(paramArrayOfFieldInfo[0].getFieldNumber(), paramArrayOfFieldInfo[paramArrayOfFieldInfo.length - 1].getFieldNumber(), paramArrayOfFieldInfo.length);
  }
  
  static <UT, UB> UB storeUnknownEnum(int paramInt1, int paramInt2, UB paramUB, UnknownFieldSchema<UT, UB> paramUnknownFieldSchema) {
    UB uB = paramUB;
    if (paramUB == null)
      uB = paramUnknownFieldSchema.newBuilder(); 
    paramUnknownFieldSchema.addVarint(uB, paramInt1, paramInt2);
    return uB;
  }
  
  static String toCamelCase(String paramString, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0;; b++) {
      if (b < paramString.length()) {
        char c = paramString.charAt(b);
        if ('a' <= c && c <= 'z') {
          if (paramBoolean) {
            stringBuilder.append((char)(c - 32));
          } else {
            stringBuilder.append(c);
          } 
        } else if ('A' <= c && c <= 'Z') {
          if (b == 0 && !paramBoolean) {
            stringBuilder.append((char)(c + 32));
          } else {
            stringBuilder.append(c);
          } 
        } else {
          if ('0' <= c && c <= '9')
            stringBuilder.append(c); 
          paramBoolean = true;
          b++;
        } 
        paramBoolean = false;
      } else {
        break;
      } 
    } 
    return stringBuilder.toString();
  }
  
  public static UnknownFieldSchema<?, ?> unknownFieldSetLiteSchema() {
    return UNKNOWN_FIELD_SET_LITE_SCHEMA;
  }
  
  public static void writeBool(int paramInt, boolean paramBoolean, Writer paramWriter) throws IOException {
    if (paramBoolean)
      paramWriter.writeBool(paramInt, true); 
  }
  
  public static void writeBoolList(int paramInt, List<Boolean> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeBoolList(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeBytes(int paramInt, ByteString paramByteString, Writer paramWriter) throws IOException {
    if (paramByteString != null && !paramByteString.isEmpty())
      paramWriter.writeBytes(paramInt, paramByteString); 
  }
  
  public static void writeBytesList(int paramInt, List<ByteString> paramList, Writer paramWriter) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeBytesList(paramInt, paramList); 
  }
  
  public static void writeDouble(int paramInt, double paramDouble, Writer paramWriter) throws IOException {
    if (Double.compare(paramDouble, 0.0D) != 0)
      paramWriter.writeDouble(paramInt, paramDouble); 
  }
  
  public static void writeDoubleList(int paramInt, List<Double> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeDoubleList(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeEnum(int paramInt1, int paramInt2, Writer paramWriter) throws IOException {
    if (paramInt2 != 0)
      paramWriter.writeEnum(paramInt1, paramInt2); 
  }
  
  public static void writeEnumList(int paramInt, List<Integer> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeEnumList(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeFixed32(int paramInt1, int paramInt2, Writer paramWriter) throws IOException {
    if (paramInt2 != 0)
      paramWriter.writeFixed32(paramInt1, paramInt2); 
  }
  
  public static void writeFixed32List(int paramInt, List<Integer> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeFixed32List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeFixed64(int paramInt, long paramLong, Writer paramWriter) throws IOException {
    if (paramLong != 0L)
      paramWriter.writeFixed64(paramInt, paramLong); 
  }
  
  public static void writeFixed64List(int paramInt, List<Long> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeFixed64List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeFloat(int paramInt, float paramFloat, Writer paramWriter) throws IOException {
    if (Float.compare(paramFloat, 0.0F) != 0)
      paramWriter.writeFloat(paramInt, paramFloat); 
  }
  
  public static void writeFloatList(int paramInt, List<Float> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeFloatList(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeGroupList(int paramInt, List<?> paramList, Writer paramWriter) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeGroupList(paramInt, paramList); 
  }
  
  public static void writeGroupList(int paramInt, List<?> paramList, Writer paramWriter, Schema paramSchema) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeGroupList(paramInt, paramList, paramSchema); 
  }
  
  public static void writeInt32(int paramInt1, int paramInt2, Writer paramWriter) throws IOException {
    if (paramInt2 != 0)
      paramWriter.writeInt32(paramInt1, paramInt2); 
  }
  
  public static void writeInt32List(int paramInt, List<Integer> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeInt32List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeInt64(int paramInt, long paramLong, Writer paramWriter) throws IOException {
    if (paramLong != 0L)
      paramWriter.writeInt64(paramInt, paramLong); 
  }
  
  public static void writeInt64List(int paramInt, List<Long> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeInt64List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeLazyFieldList(int paramInt, List<?> paramList, Writer paramWriter) throws IOException {
    if (paramList != null && !paramList.isEmpty()) {
      Iterator<?> iterator = paramList.iterator();
      while (iterator.hasNext())
        ((LazyFieldLite)iterator.next()).writeTo(paramWriter, paramInt); 
    } 
  }
  
  public static void writeMessage(int paramInt, Object paramObject, Writer paramWriter) throws IOException {
    if (paramObject != null)
      paramWriter.writeMessage(paramInt, paramObject); 
  }
  
  public static void writeMessageList(int paramInt, List<?> paramList, Writer paramWriter) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeMessageList(paramInt, paramList); 
  }
  
  public static void writeMessageList(int paramInt, List<?> paramList, Writer paramWriter, Schema paramSchema) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeMessageList(paramInt, paramList, paramSchema); 
  }
  
  public static void writeSFixed32(int paramInt1, int paramInt2, Writer paramWriter) throws IOException {
    if (paramInt2 != 0)
      paramWriter.writeSFixed32(paramInt1, paramInt2); 
  }
  
  public static void writeSFixed32List(int paramInt, List<Integer> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeSFixed32List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeSFixed64(int paramInt, long paramLong, Writer paramWriter) throws IOException {
    if (paramLong != 0L)
      paramWriter.writeSFixed64(paramInt, paramLong); 
  }
  
  public static void writeSFixed64List(int paramInt, List<Long> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeSFixed64List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeSInt32(int paramInt1, int paramInt2, Writer paramWriter) throws IOException {
    if (paramInt2 != 0)
      paramWriter.writeSInt32(paramInt1, paramInt2); 
  }
  
  public static void writeSInt32List(int paramInt, List<Integer> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeSInt32List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeSInt64(int paramInt, long paramLong, Writer paramWriter) throws IOException {
    if (paramLong != 0L)
      paramWriter.writeSInt64(paramInt, paramLong); 
  }
  
  public static void writeSInt64List(int paramInt, List<Long> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeSInt64List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeString(int paramInt, Object paramObject, Writer paramWriter) throws IOException {
    if (paramObject instanceof String) {
      writeStringInternal(paramInt, (String)paramObject, paramWriter);
    } else {
      writeBytes(paramInt, (ByteString)paramObject, paramWriter);
    } 
  }
  
  private static void writeStringInternal(int paramInt, String paramString, Writer paramWriter) throws IOException {
    if (paramString != null && !paramString.isEmpty())
      paramWriter.writeString(paramInt, paramString); 
  }
  
  public static void writeStringList(int paramInt, List<String> paramList, Writer paramWriter) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeStringList(paramInt, paramList); 
  }
  
  public static void writeUInt32(int paramInt1, int paramInt2, Writer paramWriter) throws IOException {
    if (paramInt2 != 0)
      paramWriter.writeUInt32(paramInt1, paramInt2); 
  }
  
  public static void writeUInt32List(int paramInt, List<Integer> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeUInt32List(paramInt, paramList, paramBoolean); 
  }
  
  public static void writeUInt64(int paramInt, long paramLong, Writer paramWriter) throws IOException {
    if (paramLong != 0L)
      paramWriter.writeUInt64(paramInt, paramLong); 
  }
  
  public static void writeUInt64List(int paramInt, List<Long> paramList, Writer paramWriter, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramWriter.writeUInt64List(paramInt, paramList, paramBoolean); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\SchemaUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */