package com.google.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class FieldSet<FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> {
  private static final FieldSet DEFAULT_INSTANCE = new FieldSet(true);
  
  private final SmallSortedMap<FieldDescriptorType, Object> fields = SmallSortedMap.newFieldMap(16);
  
  private boolean hasLazyField = false;
  
  private boolean isImmutable;
  
  private FieldSet() {}
  
  private FieldSet(boolean paramBoolean) {
    makeImmutable();
  }
  
  private void cloneFieldEntry(Map<FieldDescriptorType, Object> paramMap, Map.Entry<FieldDescriptorType, Object> paramEntry) {
    FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    paramEntry = (Map.Entry<FieldDescriptorType, Object>)paramEntry.getValue();
    if (paramEntry instanceof LazyField) {
      paramMap.put((FieldDescriptorType)fieldDescriptorLite, ((LazyField)paramEntry).getValue());
    } else {
      paramMap.put((FieldDescriptorType)fieldDescriptorLite, paramEntry);
    } 
  }
  
  private Object cloneIfMutable(Object paramObject) {
    if (paramObject instanceof byte[]) {
      byte[] arrayOfByte = (byte[])paramObject;
      paramObject = new byte[arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, paramObject, 0, arrayOfByte.length);
      return paramObject;
    } 
    return paramObject;
  }
  
  static int computeElementSize(WireFormat.FieldType paramFieldType, int paramInt, Object paramObject) {
    int i = CodedOutputStream.computeTagSize(paramInt);
    paramInt = i;
    if (paramFieldType == WireFormat.FieldType.GROUP)
      paramInt = i * 2; 
    return paramInt + computeElementSizeNoTag(paramFieldType, paramObject);
  }
  
  static int computeElementSizeNoTag(WireFormat.FieldType paramFieldType, Object paramObject) {
    switch (paramFieldType) {
      default:
        throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
      case ENUM:
        return (paramObject instanceof Internal.EnumLite) ? CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite)paramObject).getNumber()) : CodedOutputStream.computeEnumSizeNoTag(((Integer)paramObject).intValue());
      case SINT64:
        return CodedOutputStream.computeSInt64SizeNoTag(((Long)paramObject).longValue());
      case SINT32:
        return CodedOutputStream.computeSInt32SizeNoTag(((Integer)paramObject).intValue());
      case SFIXED64:
        return CodedOutputStream.computeSFixed64SizeNoTag(((Long)paramObject).longValue());
      case SFIXED32:
        return CodedOutputStream.computeSFixed32SizeNoTag(((Integer)paramObject).intValue());
      case UINT32:
        return CodedOutputStream.computeUInt32SizeNoTag(((Integer)paramObject).intValue());
      case BYTES:
        return (paramObject instanceof ByteString) ? CodedOutputStream.computeBytesSizeNoTag((ByteString)paramObject) : CodedOutputStream.computeByteArraySizeNoTag((byte[])paramObject);
      case STRING:
        return (paramObject instanceof ByteString) ? CodedOutputStream.computeBytesSizeNoTag((ByteString)paramObject) : CodedOutputStream.computeStringSizeNoTag((String)paramObject);
      case MESSAGE:
        return (paramObject instanceof LazyField) ? CodedOutputStream.computeLazyFieldSizeNoTag((LazyField)paramObject) : CodedOutputStream.computeMessageSizeNoTag((MessageLite)paramObject);
      case GROUP:
        return CodedOutputStream.computeGroupSizeNoTag((MessageLite)paramObject);
      case BOOL:
        return CodedOutputStream.computeBoolSizeNoTag(((Boolean)paramObject).booleanValue());
      case FIXED32:
        return CodedOutputStream.computeFixed32SizeNoTag(((Integer)paramObject).intValue());
      case FIXED64:
        return CodedOutputStream.computeFixed64SizeNoTag(((Long)paramObject).longValue());
      case INT32:
        return CodedOutputStream.computeInt32SizeNoTag(((Integer)paramObject).intValue());
      case UINT64:
        return CodedOutputStream.computeUInt64SizeNoTag(((Long)paramObject).longValue());
      case INT64:
        return CodedOutputStream.computeInt64SizeNoTag(((Long)paramObject).longValue());
      case FLOAT:
        return CodedOutputStream.computeFloatSizeNoTag(((Float)paramObject).floatValue());
      case DOUBLE:
        break;
    } 
    return CodedOutputStream.computeDoubleSizeNoTag(((Double)paramObject).doubleValue());
  }
  
  public static int computeFieldSize(FieldDescriptorLite<?> paramFieldDescriptorLite, Object paramObject) {
    WireFormat.FieldType fieldType = paramFieldDescriptorLite.getLiteType();
    int i = paramFieldDescriptorLite.getNumber();
    if (paramFieldDescriptorLite.isRepeated()) {
      boolean bool = paramFieldDescriptorLite.isPacked();
      boolean bool1 = false;
      int j = 0;
      if (bool) {
        Iterator iterator1 = ((List)paramObject).iterator();
        while (iterator1.hasNext())
          j += computeElementSizeNoTag(fieldType, iterator1.next()); 
        return CodedOutputStream.computeTagSize(i) + j + CodedOutputStream.computeRawVarint32Size(j);
      } 
      Iterator iterator = ((List)paramObject).iterator();
      for (j = bool1; iterator.hasNext(); j += computeElementSize(fieldType, i, iterator.next()));
      return j;
    } 
    return computeElementSize(fieldType, i, paramObject);
  }
  
  public static <T extends FieldDescriptorLite<T>> FieldSet<T> emptySet() {
    return DEFAULT_INSTANCE;
  }
  
  private int getMessageSetSerializedSize(Map.Entry<FieldDescriptorType, Object> paramEntry) {
    FieldDescriptorLite<?> fieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    Object object = paramEntry.getValue();
    return (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !fieldDescriptorLite.isRepeated() && !fieldDescriptorLite.isPacked()) ? ((object instanceof LazyField) ? CodedOutputStream.computeLazyFieldMessageSetExtensionSize(((FieldDescriptorLite)paramEntry.getKey()).getNumber(), (LazyField)object) : CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite)paramEntry.getKey()).getNumber(), (MessageLite)object)) : computeFieldSize(fieldDescriptorLite, object);
  }
  
  static int getWireFormatForFieldType(WireFormat.FieldType paramFieldType, boolean paramBoolean) {
    return paramBoolean ? 2 : paramFieldType.getWireType();
  }
  
  private boolean isInitialized(Map.Entry<FieldDescriptorType, Object> paramEntry) {
    FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
      Iterator<MessageLite> iterator;
      if (fieldDescriptorLite.isRepeated()) {
        iterator = ((List)paramEntry.getValue()).iterator();
        while (iterator.hasNext()) {
          if (!((MessageLite)iterator.next()).isInitialized())
            return false; 
        } 
      } else {
        iterator = iterator.getValue();
        if (iterator instanceof MessageLite) {
          if (!((MessageLite)iterator).isInitialized())
            return false; 
        } else {
          if (iterator instanceof LazyField)
            return true; 
          throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } 
      } 
    } 
    return true;
  }
  
  private void mergeFromField(Map.Entry<FieldDescriptorType, Object> paramEntry) {
    FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    Object object2 = paramEntry.getValue();
    Object object1 = object2;
    if (object2 instanceof LazyField)
      object1 = ((LazyField)object2).getValue(); 
    if (fieldDescriptorLite.isRepeated()) {
      object = getField((FieldDescriptorType)fieldDescriptorLite);
      object2 = object;
      if (object == null)
        object2 = new ArrayList(); 
      for (Object object : object1)
        ((List<Object>)object2).add(cloneIfMutable(object)); 
      this.fields.put((FieldDescriptorType)fieldDescriptorLite, object2);
    } else if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
      object2 = getField((FieldDescriptorType)fieldDescriptorLite);
      if (object2 == null) {
        this.fields.put((FieldDescriptorType)fieldDescriptorLite, cloneIfMutable(object1));
      } else {
        object1 = fieldDescriptorLite.internalMergeFrom(((MessageLite)object2).toBuilder(), (MessageLite)object1).build();
        this.fields.put((FieldDescriptorType)fieldDescriptorLite, object1);
      } 
    } else {
      this.fields.put((FieldDescriptorType)fieldDescriptorLite, cloneIfMutable(object1));
    } 
  }
  
  public static <T extends FieldDescriptorLite<T>> FieldSet<T> newFieldSet() {
    return new FieldSet<>();
  }
  
  public static Object readPrimitiveField(CodedInputStream paramCodedInputStream, WireFormat.FieldType paramFieldType, boolean paramBoolean) throws IOException {
    return paramBoolean ? WireFormat.readPrimitiveField(paramCodedInputStream, paramFieldType, WireFormat.Utf8Validation.STRICT) : WireFormat.readPrimitiveField(paramCodedInputStream, paramFieldType, WireFormat.Utf8Validation.LOOSE);
  }
  
  private static void verifyType(WireFormat.FieldType paramFieldType, Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: getstatic com/google/protobuf/FieldSet$1.$SwitchMap$com$google$protobuf$WireFormat$JavaType : [I
    //   8: aload_0
    //   9: invokevirtual getJavaType : ()Lcom/google/protobuf/WireFormat$JavaType;
    //   12: invokevirtual ordinal : ()I
    //   15: iaload
    //   16: istore_2
    //   17: iconst_0
    //   18: istore_3
    //   19: iload_2
    //   20: tableswitch default -> 72, 1 -> 168, 2 -> 160, 3 -> 152, 4 -> 144, 5 -> 136, 6 -> 128, 7 -> 111, 8 -> 94, 9 -> 75
    //   72: goto -> 173
    //   75: aload_1
    //   76: instanceof com/google/protobuf/MessageLite
    //   79: ifne -> 89
    //   82: aload_1
    //   83: instanceof com/google/protobuf/LazyField
    //   86: ifeq -> 173
    //   89: iconst_1
    //   90: istore_3
    //   91: goto -> 173
    //   94: aload_1
    //   95: instanceof java/lang/Integer
    //   98: ifne -> 89
    //   101: aload_1
    //   102: instanceof com/google/protobuf/Internal$EnumLite
    //   105: ifeq -> 173
    //   108: goto -> 89
    //   111: aload_1
    //   112: instanceof com/google/protobuf/ByteString
    //   115: ifne -> 89
    //   118: aload_1
    //   119: instanceof [B
    //   122: ifeq -> 173
    //   125: goto -> 89
    //   128: aload_1
    //   129: instanceof java/lang/String
    //   132: istore_3
    //   133: goto -> 173
    //   136: aload_1
    //   137: instanceof java/lang/Boolean
    //   140: istore_3
    //   141: goto -> 173
    //   144: aload_1
    //   145: instanceof java/lang/Double
    //   148: istore_3
    //   149: goto -> 173
    //   152: aload_1
    //   153: instanceof java/lang/Float
    //   156: istore_3
    //   157: goto -> 173
    //   160: aload_1
    //   161: instanceof java/lang/Long
    //   164: istore_3
    //   165: goto -> 173
    //   168: aload_1
    //   169: instanceof java/lang/Integer
    //   172: istore_3
    //   173: iload_3
    //   174: ifne -> 188
    //   177: new java/lang/IllegalArgumentException
    //   180: dup
    //   181: ldc_w 'Wrong object type used with protocol message reflection.'
    //   184: invokespecial <init> : (Ljava/lang/String;)V
    //   187: athrow
    //   188: return
  }
  
  static void writeElement(CodedOutputStream paramCodedOutputStream, WireFormat.FieldType paramFieldType, int paramInt, Object paramObject) throws IOException {
    if (paramFieldType == WireFormat.FieldType.GROUP) {
      paramCodedOutputStream.writeGroup(paramInt, (MessageLite)paramObject);
    } else {
      paramCodedOutputStream.writeTag(paramInt, getWireFormatForFieldType(paramFieldType, false));
      writeElementNoTag(paramCodedOutputStream, paramFieldType, paramObject);
    } 
  }
  
  static void writeElementNoTag(CodedOutputStream paramCodedOutputStream, WireFormat.FieldType paramFieldType, Object paramObject) throws IOException {
    switch (paramFieldType) {
      default:
        return;
      case ENUM:
        if (paramObject instanceof Internal.EnumLite) {
          paramCodedOutputStream.writeEnumNoTag(((Internal.EnumLite)paramObject).getNumber());
        } else {
          paramCodedOutputStream.writeEnumNoTag(((Integer)paramObject).intValue());
        } 
      case SINT64:
        paramCodedOutputStream.writeSInt64NoTag(((Long)paramObject).longValue());
      case SINT32:
        paramCodedOutputStream.writeSInt32NoTag(((Integer)paramObject).intValue());
      case SFIXED64:
        paramCodedOutputStream.writeSFixed64NoTag(((Long)paramObject).longValue());
      case SFIXED32:
        paramCodedOutputStream.writeSFixed32NoTag(((Integer)paramObject).intValue());
      case UINT32:
        paramCodedOutputStream.writeUInt32NoTag(((Integer)paramObject).intValue());
      case BYTES:
        if (paramObject instanceof ByteString) {
          paramCodedOutputStream.writeBytesNoTag((ByteString)paramObject);
        } else {
          paramCodedOutputStream.writeByteArrayNoTag((byte[])paramObject);
        } 
      case STRING:
        if (paramObject instanceof ByteString) {
          paramCodedOutputStream.writeBytesNoTag((ByteString)paramObject);
        } else {
          paramCodedOutputStream.writeStringNoTag((String)paramObject);
        } 
      case MESSAGE:
        paramCodedOutputStream.writeMessageNoTag((MessageLite)paramObject);
      case GROUP:
        paramCodedOutputStream.writeGroupNoTag((MessageLite)paramObject);
      case BOOL:
        paramCodedOutputStream.writeBoolNoTag(((Boolean)paramObject).booleanValue());
      case FIXED32:
        paramCodedOutputStream.writeFixed32NoTag(((Integer)paramObject).intValue());
      case FIXED64:
        paramCodedOutputStream.writeFixed64NoTag(((Long)paramObject).longValue());
      case INT32:
        paramCodedOutputStream.writeInt32NoTag(((Integer)paramObject).intValue());
      case UINT64:
        paramCodedOutputStream.writeUInt64NoTag(((Long)paramObject).longValue());
      case INT64:
        paramCodedOutputStream.writeInt64NoTag(((Long)paramObject).longValue());
      case FLOAT:
        paramCodedOutputStream.writeFloatNoTag(((Float)paramObject).floatValue());
      case DOUBLE:
        break;
    } 
    paramCodedOutputStream.writeDoubleNoTag(((Double)paramObject).doubleValue());
  }
  
  public static void writeField(FieldDescriptorLite<?> paramFieldDescriptorLite, Object paramObject, CodedOutputStream paramCodedOutputStream) throws IOException {
    WireFormat.FieldType fieldType = paramFieldDescriptorLite.getLiteType();
    int i = paramFieldDescriptorLite.getNumber();
    if (paramFieldDescriptorLite.isRepeated()) {
      paramObject = paramObject;
      if (paramFieldDescriptorLite.isPacked()) {
        paramCodedOutputStream.writeTag(i, 2);
        i = 0;
        Iterator iterator = paramObject.iterator();
        while (iterator.hasNext())
          i += computeElementSizeNoTag(fieldType, iterator.next()); 
        paramCodedOutputStream.writeRawVarint32(i);
        iterator = paramObject.iterator();
        while (iterator.hasNext())
          writeElementNoTag(paramCodedOutputStream, fieldType, iterator.next()); 
      } else {
        Iterator iterator = paramObject.iterator();
        while (iterator.hasNext())
          writeElement(paramCodedOutputStream, fieldType, i, iterator.next()); 
      } 
    } else if (paramObject instanceof LazyField) {
      writeElement(paramCodedOutputStream, fieldType, i, ((LazyField)paramObject).getValue());
    } else {
      writeElement(paramCodedOutputStream, fieldType, i, paramObject);
    } 
  }
  
  private void writeMessageSetTo(Map.Entry<FieldDescriptorType, Object> paramEntry, CodedOutputStream paramCodedOutputStream) throws IOException {
    Object object = (FieldDescriptorLite)paramEntry.getKey();
    if (object.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !object.isRepeated() && !object.isPacked()) {
      Object object1 = paramEntry.getValue();
      object = object1;
      if (object1 instanceof LazyField)
        object = ((LazyField)object1).getValue(); 
      paramCodedOutputStream.writeMessageSetExtension(((FieldDescriptorLite)paramEntry.getKey()).getNumber(), (MessageLite)object);
    } else {
      writeField((FieldDescriptorLite<?>)object, paramEntry.getValue(), paramCodedOutputStream);
    } 
  }
  
  public void addRepeatedField(FieldDescriptorType paramFieldDescriptorType, Object paramObject) {
    List<Object> list;
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields."); 
    verifyType(paramFieldDescriptorType.getLiteType(), paramObject);
    Object object = getField(paramFieldDescriptorType);
    if (object == null) {
      object = new ArrayList();
      this.fields.put(paramFieldDescriptorType, object);
      paramFieldDescriptorType = (FieldDescriptorType)object;
    } else {
      list = (List)object;
    } 
    list.add(paramObject);
  }
  
  public void clear() {
    this.fields.clear();
    this.hasLazyField = false;
  }
  
  public void clearField(FieldDescriptorType paramFieldDescriptorType) {
    this.fields.remove(paramFieldDescriptorType);
    if (this.fields.isEmpty())
      this.hasLazyField = false; 
  }
  
  public FieldSet<FieldDescriptorType> clone() {
    FieldSet<FieldDescriptorLite> fieldSet = newFieldSet();
    for (byte b = 0; b < this.fields.getNumArrayEntries(); b++) {
      Map.Entry<FieldDescriptorType, Object> entry = this.fields.getArrayEntryAt(b);
      fieldSet.setField((FieldDescriptorLite)entry.getKey(), entry.getValue());
    } 
    for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries())
      fieldSet.setField((FieldDescriptorLite)entry.getKey(), entry.getValue()); 
    fieldSet.hasLazyField = this.hasLazyField;
    return (FieldSet)fieldSet;
  }
  
  Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
    return this.hasLazyField ? new LazyField.LazyIterator<>(this.fields.descendingEntrySet().iterator()) : this.fields.descendingEntrySet().iterator();
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof FieldSet))
      return false; 
    paramObject = paramObject;
    return this.fields.equals(((FieldSet)paramObject).fields);
  }
  
  public Map<FieldDescriptorType, Object> getAllFields() {
    Map<FieldDescriptorType, Object> map;
    if (this.hasLazyField) {
      SmallSortedMap<FieldDescriptorLite, Object> smallSortedMap = SmallSortedMap.newFieldMap(16);
      for (byte b = 0; b < this.fields.getNumArrayEntries(); b++)
        cloneFieldEntry((Map)smallSortedMap, this.fields.getArrayEntryAt(b)); 
      Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.fields.getOverflowEntries().iterator();
      while (iterator.hasNext())
        cloneFieldEntry((Map)smallSortedMap, iterator.next()); 
      if (this.fields.isImmutable())
        smallSortedMap.makeImmutable(); 
      return (Map)smallSortedMap;
    } 
    if (this.fields.isImmutable()) {
      map = this.fields;
    } else {
      map = Collections.unmodifiableMap(this.fields);
    } 
    return map;
  }
  
  public Object getField(FieldDescriptorType paramFieldDescriptorType) {
    paramFieldDescriptorType = (FieldDescriptorType)this.fields.get(paramFieldDescriptorType);
    return (paramFieldDescriptorType instanceof LazyField) ? ((LazyField)paramFieldDescriptorType).getValue() : (Object)paramFieldDescriptorType;
  }
  
  public int getMessageSetSerializedSize() {
    byte b = 0;
    int i = 0;
    while (b < this.fields.getNumArrayEntries()) {
      i += getMessageSetSerializedSize(this.fields.getArrayEntryAt(b));
      b++;
    } 
    Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.fields.getOverflowEntries().iterator();
    while (iterator.hasNext())
      i += getMessageSetSerializedSize(iterator.next()); 
    return i;
  }
  
  public Object getRepeatedField(FieldDescriptorType paramFieldDescriptorType, int paramInt) {
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields."); 
    paramFieldDescriptorType = (FieldDescriptorType)getField(paramFieldDescriptorType);
    if (paramFieldDescriptorType == null)
      throw new IndexOutOfBoundsException(); 
    return ((List)paramFieldDescriptorType).get(paramInt);
  }
  
  public int getRepeatedFieldCount(FieldDescriptorType paramFieldDescriptorType) {
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields."); 
    paramFieldDescriptorType = (FieldDescriptorType)getField(paramFieldDescriptorType);
    return (paramFieldDescriptorType == null) ? 0 : ((List)paramFieldDescriptorType).size();
  }
  
  public int getSerializedSize() {
    byte b = 0;
    int i = 0;
    while (b < this.fields.getNumArrayEntries()) {
      Map.Entry<FieldDescriptorType, Object> entry = this.fields.getArrayEntryAt(b);
      i += computeFieldSize((FieldDescriptorLite)entry.getKey(), entry.getValue());
      b++;
    } 
    for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries())
      i += computeFieldSize((FieldDescriptorLite)entry.getKey(), entry.getValue()); 
    return i;
  }
  
  public boolean hasField(FieldDescriptorType paramFieldDescriptorType) {
    boolean bool;
    if (paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("hasField() can only be called on non-repeated fields."); 
    if (this.fields.get(paramFieldDescriptorType) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.fields.hashCode();
  }
  
  boolean isEmpty() {
    return this.fields.isEmpty();
  }
  
  public boolean isImmutable() {
    return this.isImmutable;
  }
  
  public boolean isInitialized() {
    for (byte b = 0; b < this.fields.getNumArrayEntries(); b++) {
      if (!isInitialized(this.fields.getArrayEntryAt(b)))
        return false; 
    } 
    Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.fields.getOverflowEntries().iterator();
    while (iterator.hasNext()) {
      if (!isInitialized(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  public Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
    return this.hasLazyField ? new LazyField.LazyIterator<>(this.fields.entrySet().iterator()) : this.fields.entrySet().iterator();
  }
  
  public void makeImmutable() {
    if (this.isImmutable)
      return; 
    this.fields.makeImmutable();
    this.isImmutable = true;
  }
  
  public void mergeFrom(FieldSet<FieldDescriptorType> paramFieldSet) {
    for (byte b = 0; b < paramFieldSet.fields.getNumArrayEntries(); b++)
      mergeFromField(paramFieldSet.fields.getArrayEntryAt(b)); 
    Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = paramFieldSet.fields.getOverflowEntries().iterator();
    while (iterator.hasNext())
      mergeFromField(iterator.next()); 
  }
  
  public void setField(FieldDescriptorType paramFieldDescriptorType, Object paramObject) {
    if (paramFieldDescriptorType.isRepeated()) {
      if (!(paramObject instanceof List))
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection."); 
      ArrayList arrayList = new ArrayList();
      arrayList.addAll((List)paramObject);
      paramObject = arrayList.iterator();
      while (paramObject.hasNext()) {
        Object object = paramObject.next();
        verifyType(paramFieldDescriptorType.getLiteType(), object);
      } 
      paramObject = arrayList;
    } else {
      verifyType(paramFieldDescriptorType.getLiteType(), paramObject);
    } 
    if (paramObject instanceof LazyField)
      this.hasLazyField = true; 
    this.fields.put(paramFieldDescriptorType, paramObject);
  }
  
  public void setRepeatedField(FieldDescriptorType paramFieldDescriptorType, int paramInt, Object paramObject) {
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields."); 
    Object object = getField(paramFieldDescriptorType);
    if (object == null)
      throw new IndexOutOfBoundsException(); 
    verifyType(paramFieldDescriptorType.getLiteType(), paramObject);
    ((List<Object>)object).set(paramInt, paramObject);
  }
  
  public void writeMessageSetTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    for (byte b = 0; b < this.fields.getNumArrayEntries(); b++)
      writeMessageSetTo(this.fields.getArrayEntryAt(b), paramCodedOutputStream); 
    Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.fields.getOverflowEntries().iterator();
    while (iterator.hasNext())
      writeMessageSetTo(iterator.next(), paramCodedOutputStream); 
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    for (byte b = 0; b < this.fields.getNumArrayEntries(); b++) {
      Map.Entry<FieldDescriptorType, Object> entry = this.fields.getArrayEntryAt(b);
      writeField((FieldDescriptorLite)entry.getKey(), entry.getValue(), paramCodedOutputStream);
    } 
    for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries())
      writeField((FieldDescriptorLite)entry.getKey(), entry.getValue(), paramCodedOutputStream); 
  }
  
  public static interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
    Internal.EnumLiteMap<?> getEnumType();
    
    WireFormat.JavaType getLiteJavaType();
    
    WireFormat.FieldType getLiteType();
    
    int getNumber();
    
    MessageLite.Builder internalMergeFrom(MessageLite.Builder param1Builder, MessageLite param1MessageLite);
    
    boolean isPacked();
    
    boolean isRepeated();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\FieldSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */