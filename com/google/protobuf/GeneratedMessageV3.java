package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class GeneratedMessageV3 extends AbstractMessage implements Serializable {
  protected static boolean alwaysUseFieldBuilders = false;
  
  private static final long serialVersionUID = 1L;
  
  protected UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
  
  protected GeneratedMessageV3() {}
  
  protected GeneratedMessageV3(Builder<?> paramBuilder) {}
  
  protected static boolean canUseUnsafe() {
    boolean bool;
    if (UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static <MessageType extends ExtendableMessage<MessageType>, T> Extension<MessageType, T> checkNotLite(ExtensionLite<MessageType, T> paramExtensionLite) {
    if (paramExtensionLite.isLite())
      throw new IllegalArgumentException("Expected non-lite extension."); 
    return (Extension<MessageType, T>)paramExtensionLite;
  }
  
  protected static int computeStringSize(int paramInt, Object paramObject) {
    return (paramObject instanceof String) ? CodedOutputStream.computeStringSize(paramInt, (String)paramObject) : CodedOutputStream.computeBytesSize(paramInt, (ByteString)paramObject);
  }
  
  protected static int computeStringSizeNoTag(Object paramObject) {
    return (paramObject instanceof String) ? CodedOutputStream.computeStringSizeNoTag((String)paramObject) : CodedOutputStream.computeBytesSizeNoTag((ByteString)paramObject);
  }
  
  protected static Internal.BooleanList emptyBooleanList() {
    return BooleanArrayList.emptyList();
  }
  
  protected static Internal.DoubleList emptyDoubleList() {
    return DoubleArrayList.emptyList();
  }
  
  protected static Internal.FloatList emptyFloatList() {
    return FloatArrayList.emptyList();
  }
  
  protected static Internal.IntList emptyIntList() {
    return IntArrayList.emptyList();
  }
  
  protected static Internal.LongList emptyLongList() {
    return LongArrayList.emptyList();
  }
  
  static void enableAlwaysUseFieldBuildersForTesting() {
    alwaysUseFieldBuilders = true;
  }
  
  private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable(boolean paramBoolean) {
    TreeMap<Object, Object> treeMap = new TreeMap<>();
    List<Descriptors.FieldDescriptor> list = (internalGetFieldAccessorTable()).descriptor.getFields();
    int i;
    for (i = 0; i < list.size(); i = SYNTHETIC_LOCAL_VARIABLE_7 + 1) {
      Descriptors.FieldDescriptor fieldDescriptor2;
      Descriptors.FieldDescriptor fieldDescriptor1 = list.get(i);
      Descriptors.OneofDescriptor oneofDescriptor = fieldDescriptor1.getContainingOneof();
      if (oneofDescriptor != null) {
        int j = i + oneofDescriptor.getFieldCount() - 1;
        if (!hasOneof(oneofDescriptor))
          continue; 
        fieldDescriptor2 = getOneofFieldDescriptor(oneofDescriptor);
      } else {
        if (fieldDescriptor1.isRepeated()) {
          List list1 = (List)getField(fieldDescriptor1);
          int k = i;
          if (!list1.isEmpty()) {
            treeMap.put(fieldDescriptor1, list1);
            k = i;
          } 
          continue;
        } 
        int j = i;
        fieldDescriptor2 = fieldDescriptor1;
        if (!hasField(fieldDescriptor1)) {
          j = i;
          continue;
        } 
      } 
      if (paramBoolean && fieldDescriptor2.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
        treeMap.put(fieldDescriptor2, getFieldRaw(fieldDescriptor2));
      } else {
        treeMap.put(fieldDescriptor2, getField(fieldDescriptor2));
      } 
      continue;
    } 
    return (Map)treeMap;
  }
  
  private static Method getMethodOrDie(Class paramClass, String paramString, Class... paramVarArgs) {
    try {
      return paramClass.getMethod(paramString, paramVarArgs);
    } catch (NoSuchMethodException noSuchMethodException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Generated message class \"");
      stringBuilder.append(paramClass.getName());
      stringBuilder.append("\" missing method \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\".");
      throw new RuntimeException(stringBuilder.toString(), noSuchMethodException);
    } 
  }
  
  private static Object invokeOrDie(Method paramMethod, Object paramObject, Object... paramVarArgs) {
    try {
      return paramMethod.invoke(paramObject, paramVarArgs);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      if (throwable instanceof Error)
        throw (Error)throwable; 
      throw new RuntimeException("Unexpected exception thrown by generated accessor method.", throwable);
    } 
  }
  
  private static <V> void maybeSerializeBooleanEntryTo(CodedOutputStream paramCodedOutputStream, Map<Boolean, V> paramMap, MapEntry<Boolean, V> paramMapEntry, int paramInt, boolean paramBoolean) throws IOException {
    if (paramMap.containsKey(Boolean.valueOf(paramBoolean)))
      paramCodedOutputStream.writeMessage(paramInt, paramMapEntry.newBuilderForType().setKey(Boolean.valueOf(paramBoolean)).setValue(paramMap.get(Boolean.valueOf(paramBoolean))).build()); 
  }
  
  protected static Internal.BooleanList mutableCopy(Internal.BooleanList paramBooleanList) {
    int i = paramBooleanList.size();
    if (i == 0) {
      i = 10;
    } else {
      i *= 2;
    } 
    return paramBooleanList.mutableCopyWithCapacity(i);
  }
  
  protected static Internal.DoubleList mutableCopy(Internal.DoubleList paramDoubleList) {
    int i = paramDoubleList.size();
    if (i == 0) {
      i = 10;
    } else {
      i *= 2;
    } 
    return paramDoubleList.mutableCopyWithCapacity(i);
  }
  
  protected static Internal.FloatList mutableCopy(Internal.FloatList paramFloatList) {
    int i = paramFloatList.size();
    if (i == 0) {
      i = 10;
    } else {
      i *= 2;
    } 
    return paramFloatList.mutableCopyWithCapacity(i);
  }
  
  protected static Internal.IntList mutableCopy(Internal.IntList paramIntList) {
    int i = paramIntList.size();
    if (i == 0) {
      i = 10;
    } else {
      i *= 2;
    } 
    return paramIntList.mutableCopyWithCapacity(i);
  }
  
  protected static Internal.LongList mutableCopy(Internal.LongList paramLongList) {
    int i = paramLongList.size();
    if (i == 0) {
      i = 10;
    } else {
      i *= 2;
    } 
    return paramLongList.mutableCopyWithCapacity(i);
  }
  
  protected static Internal.BooleanList newBooleanList() {
    return new BooleanArrayList();
  }
  
  protected static Internal.DoubleList newDoubleList() {
    return new DoubleArrayList();
  }
  
  protected static Internal.FloatList newFloatList() {
    return new FloatArrayList();
  }
  
  protected static Internal.IntList newIntList() {
    return new IntArrayList();
  }
  
  protected static Internal.LongList newLongList() {
    return new LongArrayList();
  }
  
  protected static <M extends Message> M parseDelimitedWithIOException(Parser<M> paramParser, InputStream paramInputStream) throws IOException {
    try {
      return paramParser.parseDelimitedFrom(paramInputStream);
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException.unwrapIOException();
    } 
  }
  
  protected static <M extends Message> M parseDelimitedWithIOException(Parser<M> paramParser, InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    try {
      return paramParser.parseDelimitedFrom(paramInputStream, paramExtensionRegistryLite);
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException.unwrapIOException();
    } 
  }
  
  protected static <M extends Message> M parseWithIOException(Parser<M> paramParser, CodedInputStream paramCodedInputStream) throws IOException {
    try {
      return paramParser.parseFrom(paramCodedInputStream);
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException.unwrapIOException();
    } 
  }
  
  protected static <M extends Message> M parseWithIOException(Parser<M> paramParser, CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    try {
      return paramParser.parseFrom(paramCodedInputStream, paramExtensionRegistryLite);
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException.unwrapIOException();
    } 
  }
  
  protected static <M extends Message> M parseWithIOException(Parser<M> paramParser, InputStream paramInputStream) throws IOException {
    try {
      return paramParser.parseFrom(paramInputStream);
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException.unwrapIOException();
    } 
  }
  
  protected static <M extends Message> M parseWithIOException(Parser<M> paramParser, InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    try {
      return paramParser.parseFrom(paramInputStream, paramExtensionRegistryLite);
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException.unwrapIOException();
    } 
  }
  
  protected static <V> void serializeBooleanMapTo(CodedOutputStream paramCodedOutputStream, MapField<Boolean, V> paramMapField, MapEntry<Boolean, V> paramMapEntry, int paramInt) throws IOException {
    Map<Boolean, V> map = paramMapField.getMap();
    if (!paramCodedOutputStream.isSerializationDeterministic()) {
      serializeMapTo(paramCodedOutputStream, map, paramMapEntry, paramInt);
      return;
    } 
    maybeSerializeBooleanEntryTo(paramCodedOutputStream, map, paramMapEntry, paramInt, false);
    maybeSerializeBooleanEntryTo(paramCodedOutputStream, map, paramMapEntry, paramInt, true);
  }
  
  protected static <V> void serializeIntegerMapTo(CodedOutputStream paramCodedOutputStream, MapField<Integer, V> paramMapField, MapEntry<Integer, V> paramMapEntry, int paramInt) throws IOException {
    Map<Integer, V> map = paramMapField.getMap();
    if (!paramCodedOutputStream.isSerializationDeterministic()) {
      serializeMapTo(paramCodedOutputStream, map, paramMapEntry, paramInt);
      return;
    } 
    int[] arrayOfInt = new int[map.size()];
    Iterator iterator = map.keySet().iterator();
    int i = 0;
    int j;
    for (j = 0; iterator.hasNext(); j++)
      arrayOfInt[j] = ((Integer)iterator.next()).intValue(); 
    Arrays.sort(arrayOfInt);
    int k = arrayOfInt.length;
    for (j = i; j < k; j++) {
      i = arrayOfInt[j];
      paramCodedOutputStream.writeMessage(paramInt, paramMapEntry.newBuilderForType().setKey(Integer.valueOf(i)).setValue(map.get(Integer.valueOf(i))).build());
    } 
  }
  
  protected static <V> void serializeLongMapTo(CodedOutputStream paramCodedOutputStream, MapField<Long, V> paramMapField, MapEntry<Long, V> paramMapEntry, int paramInt) throws IOException {
    Map<Long, V> map = paramMapField.getMap();
    if (!paramCodedOutputStream.isSerializationDeterministic()) {
      serializeMapTo(paramCodedOutputStream, map, paramMapEntry, paramInt);
      return;
    } 
    long[] arrayOfLong = new long[map.size()];
    Iterator iterator = map.keySet().iterator();
    boolean bool = false;
    byte b;
    for (b = 0; iterator.hasNext(); b++)
      arrayOfLong[b] = ((Long)iterator.next()).longValue(); 
    Arrays.sort(arrayOfLong);
    int i = arrayOfLong.length;
    for (b = bool; b < i; b++) {
      long l = arrayOfLong[b];
      paramCodedOutputStream.writeMessage(paramInt, paramMapEntry.newBuilderForType().setKey(Long.valueOf(l)).setValue(map.get(Long.valueOf(l))).build());
    } 
  }
  
  private static <K, V> void serializeMapTo(CodedOutputStream paramCodedOutputStream, Map<K, V> paramMap, MapEntry<K, V> paramMapEntry, int paramInt) throws IOException {
    for (Map.Entry<K, V> entry : paramMap.entrySet())
      paramCodedOutputStream.writeMessage(paramInt, paramMapEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build()); 
  }
  
  protected static <V> void serializeStringMapTo(CodedOutputStream paramCodedOutputStream, MapField<String, V> paramMapField, MapEntry<String, V> paramMapEntry, int paramInt) throws IOException {
    Map<String, V> map = paramMapField.getMap();
    if (!paramCodedOutputStream.isSerializationDeterministic()) {
      serializeMapTo(paramCodedOutputStream, map, paramMapEntry, paramInt);
      return;
    } 
    String[] arrayOfString1 = new String[map.size()];
    String[] arrayOfString2 = (String[])map.keySet().toArray((Object[])arrayOfString1);
    Arrays.sort((Object[])arrayOfString2);
    int i = arrayOfString2.length;
    for (byte b = 0; b < i; b++) {
      String str = arrayOfString2[b];
      paramCodedOutputStream.writeMessage(paramInt, paramMapEntry.newBuilderForType().setKey(str).setValue(map.get(str)).build());
    } 
  }
  
  protected static void writeString(CodedOutputStream paramCodedOutputStream, int paramInt, Object paramObject) throws IOException {
    if (paramObject instanceof String) {
      paramCodedOutputStream.writeString(paramInt, (String)paramObject);
    } else {
      paramCodedOutputStream.writeBytes(paramInt, (ByteString)paramObject);
    } 
  }
  
  protected static void writeStringNoTag(CodedOutputStream paramCodedOutputStream, Object paramObject) throws IOException {
    if (paramObject instanceof String) {
      paramCodedOutputStream.writeStringNoTag((String)paramObject);
    } else {
      paramCodedOutputStream.writeBytesNoTag((ByteString)paramObject);
    } 
  }
  
  public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
    return Collections.unmodifiableMap(getAllFieldsMutable(false));
  }
  
  Map<Descriptors.FieldDescriptor, Object> getAllFieldsRaw() {
    return Collections.unmodifiableMap(getAllFieldsMutable(true));
  }
  
  public Descriptors.Descriptor getDescriptorForType() {
    return (internalGetFieldAccessorTable()).descriptor;
  }
  
  public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor) {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).get(this);
  }
  
  Object getFieldRaw(Descriptors.FieldDescriptor paramFieldDescriptor) {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRaw(this);
  }
  
  public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor paramOneofDescriptor) {
    return internalGetFieldAccessorTable().getOneof(paramOneofDescriptor).get(this);
  }
  
  public Parser<? extends GeneratedMessageV3> getParserForType() {
    throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
  }
  
  public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt) {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeated(this, paramInt);
  }
  
  public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor) {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeatedCount(this);
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    this.memoizedSize = MessageReflection.getSerializedSize(this, getAllFieldsRaw());
    return this.memoizedSize;
  }
  
  public UnknownFieldSet getUnknownFields() {
    throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
  }
  
  public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor) {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).has(this);
  }
  
  public boolean hasOneof(Descriptors.OneofDescriptor paramOneofDescriptor) {
    return internalGetFieldAccessorTable().getOneof(paramOneofDescriptor).has(this);
  }
  
  protected abstract FieldAccessorTable internalGetFieldAccessorTable();
  
  protected MapField internalGetMapField(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No map fields found in ");
    stringBuilder.append(getClass().getName());
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public boolean isInitialized() {
    for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
      if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor))
        return false; 
      if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        Iterator<Message> iterator;
        if (fieldDescriptor.isRepeated()) {
          iterator = ((List)getField(fieldDescriptor)).iterator();
          while (iterator.hasNext()) {
            if (!((Message)iterator.next()).isInitialized())
              return false; 
          } 
          continue;
        } 
        if (hasField((Descriptors.FieldDescriptor)iterator) && !((Message)getField((Descriptors.FieldDescriptor)iterator)).isInitialized())
          return false; 
      } 
    } 
    return true;
  }
  
  protected void makeExtensionsImmutable() {}
  
  protected void mergeFromAndMakeImmutableInternal(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    Schema<GeneratedMessageV3> schema = Protobuf.getInstance().schemaFor(this);
    try {
      schema.mergeFrom(this, CodedInputStreamReader.forCodedInput(paramCodedInputStream), paramExtensionRegistryLite);
      schema.makeImmutable(this);
      return;
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException.setUnfinishedMessage(this);
    } catch (IOException iOException) {
      throw (new InvalidProtocolBufferException(iOException)).setUnfinishedMessage(this);
    } 
  }
  
  protected Message.Builder newBuilderForType(final AbstractMessage.BuilderParent parent) {
    return newBuilderForType(new BuilderParent() {
          public void markDirty() {
            parent.markDirty();
          }
        });
  }
  
  protected abstract Message.Builder newBuilderForType(BuilderParent paramBuilderParent);
  
  protected Object newInstance(UnusedPrivateParameter paramUnusedPrivateParameter) {
    throw new UnsupportedOperationException("This method must be overridden by the subclass.");
  }
  
  protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt) throws IOException {
    return paramCodedInputStream.shouldDiscardUnknownFields() ? paramCodedInputStream.skipField(paramInt) : paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
  }
  
  protected boolean parseUnknownFieldProto3(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt) throws IOException {
    return parseUnknownField(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, paramInt);
  }
  
  protected Object writeReplace() throws ObjectStreamException {
    return new GeneratedMessageLite.SerializedForm(this);
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    MessageReflection.writeMessageTo(this, getAllFieldsRaw(), paramCodedOutputStream, false);
  }
  
  public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends AbstractMessage.Builder<BuilderType> {
    private GeneratedMessageV3.BuilderParent builderParent;
    
    private boolean isClean;
    
    private BuilderParentImpl meAsParent;
    
    private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
    
    protected Builder() {
      this((GeneratedMessageV3.BuilderParent)null);
    }
    
    protected Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      this.builderParent = param1BuilderParent;
    }
    
    private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
      TreeMap<Object, Object> treeMap = new TreeMap<>();
      List<Descriptors.FieldDescriptor> list = (internalGetFieldAccessorTable()).descriptor.getFields();
      for (int i = 0; i < list.size(); i = SYNTHETIC_LOCAL_VARIABLE_6 + 1) {
        Descriptors.FieldDescriptor fieldDescriptor2;
        Descriptors.FieldDescriptor fieldDescriptor1 = list.get(i);
        Descriptors.OneofDescriptor oneofDescriptor = fieldDescriptor1.getContainingOneof();
        if (oneofDescriptor != null) {
          int j = i + oneofDescriptor.getFieldCount() - 1;
          if (!hasOneof(oneofDescriptor))
            continue; 
          fieldDescriptor2 = getOneofFieldDescriptor(oneofDescriptor);
        } else {
          if (fieldDescriptor1.isRepeated()) {
            List list1 = (List)getField(fieldDescriptor1);
            int k = i;
            if (!list1.isEmpty()) {
              treeMap.put(fieldDescriptor1, list1);
              k = i;
            } 
            continue;
          } 
          int j = i;
          fieldDescriptor2 = fieldDescriptor1;
          if (!hasField(fieldDescriptor1)) {
            j = i;
            continue;
          } 
        } 
        treeMap.put(fieldDescriptor2, getField(fieldDescriptor2));
        continue;
      } 
      return (Map)treeMap;
    }
    
    private BuilderType setUnknownFieldsInternal(UnknownFieldSet param1UnknownFieldSet) {
      this.unknownFields = param1UnknownFieldSet;
      onChanged();
      return (BuilderType)this;
    }
    
    public BuilderType addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      internalGetFieldAccessorTable().getField(param1FieldDescriptor).addRepeated(this, param1Object);
      return (BuilderType)this;
    }
    
    public BuilderType clear() {
      this.unknownFields = UnknownFieldSet.getDefaultInstance();
      onChanged();
      return (BuilderType)this;
    }
    
    public BuilderType clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      internalGetFieldAccessorTable().getField(param1FieldDescriptor).clear(this);
      return (BuilderType)this;
    }
    
    public BuilderType clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      internalGetFieldAccessorTable().getOneof(param1OneofDescriptor).clear(this);
      return (BuilderType)this;
    }
    
    public BuilderType clone() {
      Builder builder = (Builder)getDefaultInstanceForType().newBuilderForType();
      builder.mergeFrom(buildPartial());
      return (BuilderType)builder;
    }
    
    void dispose() {
      this.builderParent = null;
    }
    
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
      return Collections.unmodifiableMap(getAllFieldsMutable());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return (internalGetFieldAccessorTable()).descriptor;
    }
    
    public Object getField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      Object object = internalGetFieldAccessorTable().getField(param1FieldDescriptor).get(this);
      return param1FieldDescriptor.isRepeated() ? Collections.unmodifiableList((List)object) : object;
    }
    
    public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return internalGetFieldAccessorTable().getField(param1FieldDescriptor).getBuilder(this);
    }
    
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return internalGetFieldAccessorTable().getOneof(param1OneofDescriptor).get(this);
    }
    
    protected GeneratedMessageV3.BuilderParent getParentForChildren() {
      if (this.meAsParent == null)
        this.meAsParent = new BuilderParentImpl(); 
      return this.meAsParent;
    }
    
    public Object getRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int) {
      return internalGetFieldAccessorTable().getField(param1FieldDescriptor).getRepeated(this, param1Int);
    }
    
    public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int) {
      return internalGetFieldAccessorTable().getField(param1FieldDescriptor).getRepeatedBuilder(this, param1Int);
    }
    
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return internalGetFieldAccessorTable().getField(param1FieldDescriptor).getRepeatedCount(this);
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return internalGetFieldAccessorTable().getField(param1FieldDescriptor).has(this);
    }
    
    public boolean hasOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return internalGetFieldAccessorTable().getOneof(param1OneofDescriptor).has(this);
    }
    
    protected abstract GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable();
    
    protected MapField internalGetMapField(int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No map fields found in ");
      stringBuilder.append(getClass().getName());
      throw new RuntimeException(stringBuilder.toString());
    }
    
    protected MapField internalGetMutableMapField(int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No map fields found in ");
      stringBuilder.append(getClass().getName());
      throw new RuntimeException(stringBuilder.toString());
    }
    
    protected boolean isClean() {
      return this.isClean;
    }
    
    public boolean isInitialized() {
      for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
        if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor))
          return false; 
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
          Iterator<Message> iterator;
          if (fieldDescriptor.isRepeated()) {
            iterator = ((List)getField(fieldDescriptor)).iterator();
            while (iterator.hasNext()) {
              if (!((Message)iterator.next()).isInitialized())
                return false; 
            } 
            continue;
          } 
          if (hasField((Descriptors.FieldDescriptor)iterator) && !((Message)getField((Descriptors.FieldDescriptor)iterator)).isInitialized())
            return false; 
        } 
      } 
      return true;
    }
    
    protected void markClean() {
      this.isClean = true;
    }
    
    public BuilderType mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return setUnknownFields(UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(param1UnknownFieldSet).build());
    }
    
    public Message.Builder newBuilderForField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return internalGetFieldAccessorTable().getField(param1FieldDescriptor).newBuilder();
    }
    
    protected void onBuilt() {
      if (this.builderParent != null)
        markClean(); 
    }
    
    protected final void onChanged() {
      if (this.isClean && this.builderParent != null) {
        this.builderParent.markDirty();
        this.isClean = false;
      } 
    }
    
    public BuilderType setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      internalGetFieldAccessorTable().getField(param1FieldDescriptor).set(this, param1Object);
      return (BuilderType)this;
    }
    
    public BuilderType setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      internalGetFieldAccessorTable().getField(param1FieldDescriptor).setRepeated(this, param1Int, param1Object);
      return (BuilderType)this;
    }
    
    public BuilderType setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return setUnknownFieldsInternal(param1UnknownFieldSet);
    }
    
    protected BuilderType setUnknownFieldsProto3(UnknownFieldSet param1UnknownFieldSet) {
      return setUnknownFieldsInternal(param1UnknownFieldSet);
    }
    
    private class BuilderParentImpl implements GeneratedMessageV3.BuilderParent {
      private BuilderParentImpl() {}
      
      public void markDirty() {
        GeneratedMessageV3.Builder.this.onChanged();
      }
    }
  }
  
  private class BuilderParentImpl implements BuilderParent {
    private BuilderParentImpl() {}
    
    public void markDirty() {
      GeneratedMessageV3.Builder.this.onChanged();
    }
  }
  
  protected static interface BuilderParent extends AbstractMessage.BuilderParent {}
  
  public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
    private FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.emptySet();
    
    protected ExtendableBuilder() {}
    
    protected ExtendableBuilder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
    }
    
    private FieldSet<Descriptors.FieldDescriptor> buildExtensions() {
      this.extensions.makeImmutable();
      return this.extensions;
    }
    
    private void ensureExtensionsIsMutable() {
      if (this.extensions.isImmutable())
        this.extensions = this.extensions.clone(); 
    }
    
    private void verifyContainingType(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
    }
    
    private void verifyExtensionContainingType(Extension<MessageType, ?> param1Extension) {
      if (param1Extension.getDescriptor().getContainingType() != getDescriptorForType()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Extension is for type \"");
        stringBuilder.append(param1Extension.getDescriptor().getContainingType().getFullName());
        stringBuilder.append("\" which does not match message type \"");
        stringBuilder.append(getDescriptorForType().getFullName());
        stringBuilder.append("\".");
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    }
    
    public final <Type> BuilderType addExtension(Extension<MessageType, List<Type>> param1Extension, Type param1Type) {
      return addExtension(param1Extension, param1Type);
    }
    
    public final <Type> BuilderType addExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, Type param1Type) {
      Extension<MessageType, ?> extension = GeneratedMessageV3.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType(extension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor fieldDescriptor = extension.getDescriptor();
      this.extensions.addRepeatedField(fieldDescriptor, extension.singularToReflectionType(param1Type));
      onChanged();
      return (BuilderType)this;
    }
    
    public <Type> BuilderType addExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, Type param1Type) {
      return addExtension(param1GeneratedExtension, param1Type);
    }
    
    public BuilderType addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.addRepeatedField(param1FieldDescriptor, param1Object);
        onChanged();
        return (BuilderType)this;
      } 
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public BuilderType clear() {
      this.extensions = FieldSet.emptySet();
      return super.clear();
    }
    
    public final <Type> BuilderType clearExtension(Extension<MessageType, ?> param1Extension) {
      return clearExtension(param1Extension);
    }
    
    public final <Type> BuilderType clearExtension(ExtensionLite<MessageType, ?> param1ExtensionLite) {
      param1ExtensionLite = GeneratedMessageV3.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      ensureExtensionsIsMutable();
      this.extensions.clearField(param1ExtensionLite.getDescriptor());
      onChanged();
      return (BuilderType)this;
    }
    
    public <Type> BuilderType clearExtension(GeneratedMessage.GeneratedExtension<MessageType, ?> param1GeneratedExtension) {
      return clearExtension(param1GeneratedExtension);
    }
    
    public BuilderType clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.clearField(param1FieldDescriptor);
        onChanged();
        return (BuilderType)this;
      } 
      return super.clearField(param1FieldDescriptor);
    }
    
    protected boolean extensionsAreInitialized() {
      return this.extensions.isInitialized();
    }
    
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
      Map<? extends Descriptors.FieldDescriptor, ?> map = getAllFieldsMutable();
      map.putAll(this.extensions.getAllFields());
      return Collections.unmodifiableMap(map);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, Type> param1Extension) {
      return getExtension(param1Extension);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, List<Type>> param1Extension, int param1Int) {
      return getExtension(param1Extension, param1Int);
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      Extension<MessageType, ?> extension = GeneratedMessageV3.checkNotLite(param1ExtensionLite);
      verifyExtensionContainingType(extension);
      Descriptors.FieldDescriptor fieldDescriptor = extension.getDescriptor();
      Object object = this.extensions.getField(fieldDescriptor);
      return (Type)((object == null) ? (fieldDescriptor.isRepeated() ? Collections.emptyList() : ((fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) ? extension.getMessageDefaultInstance() : extension.fromReflectionType(fieldDescriptor.getDefaultValue()))) : extension.fromReflectionType(object));
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageV3.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      Descriptors.FieldDescriptor fieldDescriptor = param1ExtensionLite.getDescriptor();
      return (Type)param1ExtensionLite.singularFromReflectionType(this.extensions.getRepeatedField(fieldDescriptor, param1Int));
    }
    
    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension) {
      return getExtension(param1GeneratedExtension);
    }
    
    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, int param1Int) {
      return getExtension(param1GeneratedExtension, param1Int);
    }
    
    public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> param1Extension) {
      return getExtensionCount(param1Extension);
    }
    
    public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageV3.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      Descriptors.FieldDescriptor fieldDescriptor = param1ExtensionLite.getDescriptor();
      return this.extensions.getRepeatedFieldCount(fieldDescriptor);
    }
    
    public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension) {
      return getExtensionCount(param1GeneratedExtension);
    }
    
    public Object getField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        Object object = this.extensions.getField(param1FieldDescriptor);
        return (object == null) ? ((param1FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) ? DynamicMessage.getDefaultInstance(param1FieldDescriptor.getMessageType()) : param1FieldDescriptor.getDefaultValue()) : object;
      } 
      return super.getField(param1FieldDescriptor);
    }
    
    public Object getRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        return this.extensions.getRepeatedField(param1FieldDescriptor, param1Int);
      } 
      return super.getRepeatedField(param1FieldDescriptor, param1Int);
    }
    
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        return this.extensions.getRepeatedFieldCount(param1FieldDescriptor);
      } 
      return super.getRepeatedFieldCount(param1FieldDescriptor);
    }
    
    public final <Type> boolean hasExtension(Extension<MessageType, Type> param1Extension) {
      return hasExtension(param1Extension);
    }
    
    public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      param1ExtensionLite = GeneratedMessageV3.checkNotLite(param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      return this.extensions.hasField(param1ExtensionLite.getDescriptor());
    }
    
    public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension) {
      return hasExtension(param1GeneratedExtension);
    }
    
    public boolean hasField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        return this.extensions.hasField(param1FieldDescriptor);
      } 
      return super.hasField(param1FieldDescriptor);
    }
    
    void internalSetExtensionSet(FieldSet<Descriptors.FieldDescriptor> param1FieldSet) {
      this.extensions = param1FieldSet;
    }
    
    public boolean isInitialized() {
      boolean bool;
      if (super.isInitialized() && extensionsAreInitialized()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected final void mergeExtensionFields(GeneratedMessageV3.ExtendableMessage param1ExtendableMessage) {
      ensureExtensionsIsMutable();
      this.extensions.mergeFrom(param1ExtendableMessage.extensions);
      onChanged();
    }
    
    public Message.Builder newBuilderForField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return param1FieldDescriptor.isExtension() ? DynamicMessage.newBuilder(param1FieldDescriptor.getMessageType()) : super.newBuilderForField(param1FieldDescriptor);
    }
    
    public final <Type> BuilderType setExtension(Extension<MessageType, List<Type>> param1Extension, int param1Int, Type param1Type) {
      return setExtension(param1Extension, param1Int, param1Type);
    }
    
    public final <Type> BuilderType setExtension(Extension<MessageType, Type> param1Extension, Type param1Type) {
      return setExtension(param1Extension, param1Type);
    }
    
    public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int, Type param1Type) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageV3.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor fieldDescriptor = param1ExtensionLite.getDescriptor();
      this.extensions.setRepeatedField(fieldDescriptor, param1Int, param1ExtensionLite.singularToReflectionType(param1Type));
      onChanged();
      return (BuilderType)this;
    }
    
    public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> param1ExtensionLite, Type param1Type) {
      Extension<MessageType, ?> extension = GeneratedMessageV3.checkNotLite(param1ExtensionLite);
      verifyExtensionContainingType(extension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor fieldDescriptor = extension.getDescriptor();
      this.extensions.setField(fieldDescriptor, extension.toReflectionType(param1Type));
      onChanged();
      return (BuilderType)this;
    }
    
    public <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, int param1Int, Type param1Type) {
      return setExtension(param1GeneratedExtension, param1Int, param1Type);
    }
    
    public <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension, Type param1Type) {
      return setExtension(param1GeneratedExtension, param1Type);
    }
    
    public BuilderType setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setField(param1FieldDescriptor, param1Object);
        onChanged();
        return (BuilderType)this;
      } 
      return super.setField(param1FieldDescriptor, param1Object);
    }
    
    public BuilderType setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
        onChanged();
        return (BuilderType)this;
      } 
      return super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
  }
  
  public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessageV3 implements ExtendableMessageOrBuilder<MessageType> {
    private static final long serialVersionUID = 1L;
    
    private final FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.newFieldSet();
    
    protected ExtendableMessage() {}
    
    protected ExtendableMessage(GeneratedMessageV3.ExtendableBuilder<MessageType, ?> param1ExtendableBuilder) {
      super(param1ExtendableBuilder);
    }
    
    private void verifyContainingType(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
    }
    
    private void verifyExtensionContainingType(Extension<MessageType, ?> param1Extension) {
      if (param1Extension.getDescriptor().getContainingType() != getDescriptorForType()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Extension is for type \"");
        stringBuilder.append(param1Extension.getDescriptor().getContainingType().getFullName());
        stringBuilder.append("\" which does not match message type \"");
        stringBuilder.append(getDescriptorForType().getFullName());
        stringBuilder.append("\".");
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    }
    
    protected boolean extensionsAreInitialized() {
      return this.extensions.isInitialized();
    }
    
    protected int extensionsSerializedSize() {
      return this.extensions.getSerializedSize();
    }
    
    protected int extensionsSerializedSizeAsMessageSet() {
      return this.extensions.getMessageSetSerializedSize();
    }
    
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
      Map<Descriptors.FieldDescriptor, Object> map = getAllFieldsMutable(false);
      map.putAll(getExtensionFields());
      return Collections.unmodifiableMap(map);
    }
    
    public Map<Descriptors.FieldDescriptor, Object> getAllFieldsRaw() {
      Map<Descriptors.FieldDescriptor, Object> map = getAllFieldsMutable(false);
      map.putAll(getExtensionFields());
      return Collections.unmodifiableMap(map);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, Type> param1Extension) {
      return getExtension(param1Extension);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, List<Type>> param1Extension, int param1Int) {
      return getExtension(param1Extension, param1Int);
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      Extension<MessageType, ?> extension = GeneratedMessageV3.checkNotLite(param1ExtensionLite);
      verifyExtensionContainingType(extension);
      Descriptors.FieldDescriptor fieldDescriptor = extension.getDescriptor();
      Object object = this.extensions.getField(fieldDescriptor);
      return (Type)((object == null) ? (fieldDescriptor.isRepeated() ? Collections.emptyList() : ((fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) ? extension.getMessageDefaultInstance() : extension.fromReflectionType(fieldDescriptor.getDefaultValue()))) : extension.fromReflectionType(object));
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageV3.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      Descriptors.FieldDescriptor fieldDescriptor = param1ExtensionLite.getDescriptor();
      return (Type)param1ExtensionLite.singularFromReflectionType(this.extensions.getRepeatedField(fieldDescriptor, param1Int));
    }
    
    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension) {
      return getExtension(param1GeneratedExtension);
    }
    
    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, int param1Int) {
      return getExtension(param1GeneratedExtension, param1Int);
    }
    
    public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> param1Extension) {
      return getExtensionCount(param1Extension);
    }
    
    public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageV3.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      Descriptors.FieldDescriptor fieldDescriptor = param1ExtensionLite.getDescriptor();
      return this.extensions.getRepeatedFieldCount(fieldDescriptor);
    }
    
    public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension) {
      return getExtensionCount(param1GeneratedExtension);
    }
    
    protected Map<Descriptors.FieldDescriptor, Object> getExtensionFields() {
      return this.extensions.getAllFields();
    }
    
    public Object getField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        Object object = this.extensions.getField(param1FieldDescriptor);
        return (object == null) ? (param1FieldDescriptor.isRepeated() ? Collections.emptyList() : ((param1FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) ? DynamicMessage.getDefaultInstance(param1FieldDescriptor.getMessageType()) : param1FieldDescriptor.getDefaultValue())) : object;
      } 
      return super.getField(param1FieldDescriptor);
    }
    
    public Object getRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        return this.extensions.getRepeatedField(param1FieldDescriptor, param1Int);
      } 
      return super.getRepeatedField(param1FieldDescriptor, param1Int);
    }
    
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        return this.extensions.getRepeatedFieldCount(param1FieldDescriptor);
      } 
      return super.getRepeatedFieldCount(param1FieldDescriptor);
    }
    
    public final <Type> boolean hasExtension(Extension<MessageType, Type> param1Extension) {
      return hasExtension(param1Extension);
    }
    
    public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      param1ExtensionLite = GeneratedMessageV3.checkNotLite(param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      return this.extensions.hasField(param1ExtensionLite.getDescriptor());
    }
    
    public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension) {
      return hasExtension(param1GeneratedExtension);
    }
    
    public boolean hasField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.isExtension()) {
        verifyContainingType(param1FieldDescriptor);
        return this.extensions.hasField(param1FieldDescriptor);
      } 
      return super.hasField(param1FieldDescriptor);
    }
    
    public boolean isInitialized() {
      boolean bool;
      if (super.isInitialized() && extensionsAreInitialized()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected void makeExtensionsImmutable() {
      this.extensions.makeImmutable();
    }
    
    protected ExtensionWriter newExtensionWriter() {
      return new ExtensionWriter(false);
    }
    
    protected ExtensionWriter newMessageSetExtensionWriter() {
      return new ExtensionWriter(true);
    }
    
    protected boolean parseUnknownField(CodedInputStream param1CodedInputStream, UnknownFieldSet.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite, int param1Int) throws IOException {
      if (param1CodedInputStream.shouldDiscardUnknownFields())
        param1Builder = null; 
      return MessageReflection.mergeFieldFrom(param1CodedInputStream, param1Builder, param1ExtensionRegistryLite, getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), param1Int);
    }
    
    protected boolean parseUnknownFieldProto3(CodedInputStream param1CodedInputStream, UnknownFieldSet.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite, int param1Int) throws IOException {
      return parseUnknownField(param1CodedInputStream, param1Builder, param1ExtensionRegistryLite, param1Int);
    }
    
    protected class ExtensionWriter {
      private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = GeneratedMessageV3.ExtendableMessage.this.extensions.iterator();
      
      private final boolean messageSetWireFormat;
      
      private Map.Entry<Descriptors.FieldDescriptor, Object> next;
      
      private ExtensionWriter(boolean param2Boolean) {
        if (this.iter.hasNext())
          this.next = this.iter.next(); 
        this.messageSetWireFormat = param2Boolean;
      }
      
      public void writeUntil(int param2Int, CodedOutputStream param2CodedOutputStream) throws IOException {
        while (this.next != null && ((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < param2Int) {
          Descriptors.FieldDescriptor fieldDescriptor = this.next.getKey();
          if (this.messageSetWireFormat && fieldDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !fieldDescriptor.isRepeated()) {
            if (this.next instanceof LazyField.LazyEntry) {
              param2CodedOutputStream.writeRawMessageSetExtension(fieldDescriptor.getNumber(), ((LazyField.LazyEntry)this.next).getField().toByteString());
            } else {
              param2CodedOutputStream.writeMessageSetExtension(fieldDescriptor.getNumber(), (Message)this.next.getValue());
            } 
          } else {
            FieldSet.writeField(fieldDescriptor, this.next.getValue(), param2CodedOutputStream);
          } 
          if (this.iter.hasNext()) {
            this.next = this.iter.next();
            continue;
          } 
          this.next = null;
        } 
      }
    }
  }
  
  protected class ExtensionWriter {
    private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = GeneratedMessageV3.ExtendableMessage.this.extensions.iterator();
    
    private final boolean messageSetWireFormat;
    
    private Map.Entry<Descriptors.FieldDescriptor, Object> next;
    
    private ExtensionWriter(boolean param1Boolean) {
      if (this.iter.hasNext())
        this.next = this.iter.next(); 
      this.messageSetWireFormat = param1Boolean;
    }
    
    public void writeUntil(int param1Int, CodedOutputStream param1CodedOutputStream) throws IOException {
      while (this.next != null && ((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < param1Int) {
        Descriptors.FieldDescriptor fieldDescriptor = this.next.getKey();
        if (this.messageSetWireFormat && fieldDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !fieldDescriptor.isRepeated()) {
          if (this.next instanceof LazyField.LazyEntry) {
            param1CodedOutputStream.writeRawMessageSetExtension(fieldDescriptor.getNumber(), ((LazyField.LazyEntry)this.next).getField().toByteString());
          } else {
            param1CodedOutputStream.writeMessageSetExtension(fieldDescriptor.getNumber(), (Message)this.next.getValue());
          } 
        } else {
          FieldSet.writeField(fieldDescriptor, this.next.getValue(), param1CodedOutputStream);
        } 
        if (this.iter.hasNext()) {
          this.next = this.iter.next();
          continue;
        } 
        this.next = null;
      } 
    }
  }
  
  public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageOrBuilder {
    Message getDefaultInstanceForType();
    
    <Type> Type getExtension(Extension<MessageType, Type> param1Extension);
    
    <Type> Type getExtension(Extension<MessageType, List<Type>> param1Extension, int param1Int);
    
    <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
    
    <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int);
    
    <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
    
    <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, int param1Int);
    
    <Type> int getExtensionCount(Extension<MessageType, List<Type>> param1Extension);
    
    <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite);
    
    <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension);
    
    <Type> boolean hasExtension(Extension<MessageType, Type> param1Extension);
    
    <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
    
    <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
  }
  
  static interface ExtensionDescriptorRetriever {
    Descriptors.FieldDescriptor getDescriptor();
  }
  
  public static final class FieldAccessorTable {
    private String[] camelCaseNames;
    
    private final Descriptors.Descriptor descriptor;
    
    private final FieldAccessor[] fields;
    
    private volatile boolean initialized;
    
    private final OneofAccessor[] oneofs;
    
    public FieldAccessorTable(Descriptors.Descriptor param1Descriptor, String[] param1ArrayOfString) {
      this.descriptor = param1Descriptor;
      this.camelCaseNames = param1ArrayOfString;
      this.fields = new FieldAccessor[param1Descriptor.getFields().size()];
      this.oneofs = new OneofAccessor[param1Descriptor.getOneofs().size()];
      this.initialized = false;
    }
    
    public FieldAccessorTable(Descriptors.Descriptor param1Descriptor, String[] param1ArrayOfString, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1) {
      this(param1Descriptor, param1ArrayOfString);
      ensureFieldAccessorsInitialized(param1Class, param1Class1);
    }
    
    private FieldAccessor getField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.getContainingType() != this.descriptor)
        throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
      if (param1FieldDescriptor.isExtension())
        throw new IllegalArgumentException("This type does not have extensions."); 
      return this.fields[param1FieldDescriptor.getIndex()];
    }
    
    private OneofAccessor getOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      if (param1OneofDescriptor.getContainingType() != this.descriptor)
        throw new IllegalArgumentException("OneofDescriptor does not match message type."); 
      return this.oneofs[param1OneofDescriptor.getIndex()];
    }
    
    private static boolean supportFieldPresence(Descriptors.FileDescriptor param1FileDescriptor) {
      boolean bool;
      if (param1FileDescriptor.getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1) {
      // Byte code:
      //   0: aload_0
      //   1: getfield initialized : Z
      //   4: ifeq -> 9
      //   7: aload_0
      //   8: areturn
      //   9: aload_0
      //   10: monitorenter
      //   11: aload_0
      //   12: getfield initialized : Z
      //   15: ifeq -> 22
      //   18: aload_0
      //   19: monitorexit
      //   20: aload_0
      //   21: areturn
      //   22: aload_0
      //   23: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   26: arraylength
      //   27: istore_3
      //   28: iconst_0
      //   29: istore #4
      //   31: iconst_0
      //   32: istore #5
      //   34: aconst_null
      //   35: astore #6
      //   37: iload #5
      //   39: iload_3
      //   40: if_icmpge -> 393
      //   43: aload_0
      //   44: getfield descriptor : Lcom/google/protobuf/Descriptors$Descriptor;
      //   47: invokevirtual getFields : ()Ljava/util/List;
      //   50: iload #5
      //   52: invokeinterface get : (I)Ljava/lang/Object;
      //   57: checkcast com/google/protobuf/Descriptors$FieldDescriptor
      //   60: astore #7
      //   62: aload #7
      //   64: invokevirtual getContainingOneof : ()Lcom/google/protobuf/Descriptors$OneofDescriptor;
      //   67: ifnull -> 87
      //   70: aload_0
      //   71: getfield camelCaseNames : [Ljava/lang/String;
      //   74: aload #7
      //   76: invokevirtual getContainingOneof : ()Lcom/google/protobuf/Descriptors$OneofDescriptor;
      //   79: invokevirtual getIndex : ()I
      //   82: iload_3
      //   83: iadd
      //   84: aaload
      //   85: astore #6
      //   87: aload #7
      //   89: invokevirtual isRepeated : ()Z
      //   92: ifeq -> 237
      //   95: aload #7
      //   97: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   100: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.MESSAGE : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   103: if_acmpne -> 170
      //   106: aload #7
      //   108: invokevirtual isMapField : ()Z
      //   111: ifeq -> 142
      //   114: aload_0
      //   115: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   118: iload #5
      //   120: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$MapFieldAccessor
      //   123: dup
      //   124: aload #7
      //   126: aload_0
      //   127: getfield camelCaseNames : [Ljava/lang/String;
      //   130: iload #5
      //   132: aaload
      //   133: aload_1
      //   134: aload_2
      //   135: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   138: aastore
      //   139: goto -> 387
      //   142: aload_0
      //   143: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   146: iload #5
      //   148: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$RepeatedMessageFieldAccessor
      //   151: dup
      //   152: aload #7
      //   154: aload_0
      //   155: getfield camelCaseNames : [Ljava/lang/String;
      //   158: iload #5
      //   160: aaload
      //   161: aload_1
      //   162: aload_2
      //   163: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   166: aastore
      //   167: goto -> 387
      //   170: aload #7
      //   172: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   175: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.ENUM : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   178: if_acmpne -> 209
      //   181: aload_0
      //   182: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   185: iload #5
      //   187: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$RepeatedEnumFieldAccessor
      //   190: dup
      //   191: aload #7
      //   193: aload_0
      //   194: getfield camelCaseNames : [Ljava/lang/String;
      //   197: iload #5
      //   199: aaload
      //   200: aload_1
      //   201: aload_2
      //   202: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   205: aastore
      //   206: goto -> 387
      //   209: aload_0
      //   210: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   213: iload #5
      //   215: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$RepeatedFieldAccessor
      //   218: dup
      //   219: aload #7
      //   221: aload_0
      //   222: getfield camelCaseNames : [Ljava/lang/String;
      //   225: iload #5
      //   227: aaload
      //   228: aload_1
      //   229: aload_2
      //   230: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   233: aastore
      //   234: goto -> 387
      //   237: aload #7
      //   239: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   242: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.MESSAGE : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   245: if_acmpne -> 278
      //   248: aload_0
      //   249: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   252: iload #5
      //   254: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$SingularMessageFieldAccessor
      //   257: dup
      //   258: aload #7
      //   260: aload_0
      //   261: getfield camelCaseNames : [Ljava/lang/String;
      //   264: iload #5
      //   266: aaload
      //   267: aload_1
      //   268: aload_2
      //   269: aload #6
      //   271: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   274: aastore
      //   275: goto -> 387
      //   278: aload #7
      //   280: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   283: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.ENUM : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   286: if_acmpne -> 319
      //   289: aload_0
      //   290: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   293: iload #5
      //   295: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$SingularEnumFieldAccessor
      //   298: dup
      //   299: aload #7
      //   301: aload_0
      //   302: getfield camelCaseNames : [Ljava/lang/String;
      //   305: iload #5
      //   307: aaload
      //   308: aload_1
      //   309: aload_2
      //   310: aload #6
      //   312: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   315: aastore
      //   316: goto -> 387
      //   319: aload #7
      //   321: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   324: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.STRING : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   327: if_acmpne -> 360
      //   330: aload_0
      //   331: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   334: iload #5
      //   336: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$SingularStringFieldAccessor
      //   339: dup
      //   340: aload #7
      //   342: aload_0
      //   343: getfield camelCaseNames : [Ljava/lang/String;
      //   346: iload #5
      //   348: aaload
      //   349: aload_1
      //   350: aload_2
      //   351: aload #6
      //   353: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   356: aastore
      //   357: goto -> 387
      //   360: aload_0
      //   361: getfield fields : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$FieldAccessor;
      //   364: iload #5
      //   366: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$SingularFieldAccessor
      //   369: dup
      //   370: aload #7
      //   372: aload_0
      //   373: getfield camelCaseNames : [Ljava/lang/String;
      //   376: iload #5
      //   378: aaload
      //   379: aload_1
      //   380: aload_2
      //   381: aload #6
      //   383: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   386: aastore
      //   387: iinc #5, 1
      //   390: goto -> 34
      //   393: aload_0
      //   394: getfield oneofs : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$OneofAccessor;
      //   397: arraylength
      //   398: istore #8
      //   400: iload #4
      //   402: istore #5
      //   404: iload #5
      //   406: iload #8
      //   408: if_icmpge -> 446
      //   411: aload_0
      //   412: getfield oneofs : [Lcom/google/protobuf/GeneratedMessageV3$FieldAccessorTable$OneofAccessor;
      //   415: iload #5
      //   417: new com/google/protobuf/GeneratedMessageV3$FieldAccessorTable$OneofAccessor
      //   420: dup
      //   421: aload_0
      //   422: getfield descriptor : Lcom/google/protobuf/Descriptors$Descriptor;
      //   425: aload_0
      //   426: getfield camelCaseNames : [Ljava/lang/String;
      //   429: iload #5
      //   431: iload_3
      //   432: iadd
      //   433: aaload
      //   434: aload_1
      //   435: aload_2
      //   436: invokespecial <init> : (Lcom/google/protobuf/Descriptors$Descriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   439: aastore
      //   440: iinc #5, 1
      //   443: goto -> 404
      //   446: aload_0
      //   447: iconst_1
      //   448: putfield initialized : Z
      //   451: aload_0
      //   452: aconst_null
      //   453: putfield camelCaseNames : [Ljava/lang/String;
      //   456: aload_0
      //   457: monitorexit
      //   458: aload_0
      //   459: areturn
      //   460: astore_1
      //   461: aload_0
      //   462: monitorexit
      //   463: aload_1
      //   464: athrow
      // Exception table:
      //   from	to	target	type
      //   11	20	460	finally
      //   22	28	460	finally
      //   43	62	460	finally
      //   62	87	460	finally
      //   87	139	460	finally
      //   142	167	460	finally
      //   170	206	460	finally
      //   209	234	460	finally
      //   237	275	460	finally
      //   278	316	460	finally
      //   319	357	460	finally
      //   360	387	460	finally
      //   393	400	460	finally
      //   411	440	460	finally
      //   446	458	460	finally
      //   461	463	460	finally
    }
    
    private static interface FieldAccessor {
      void addRepeated(GeneratedMessageV3.Builder param2Builder, Object param2Object);
      
      void clear(GeneratedMessageV3.Builder param2Builder);
      
      Object get(GeneratedMessageV3.Builder param2Builder);
      
      Object get(GeneratedMessageV3 param2GeneratedMessageV3);
      
      Message.Builder getBuilder(GeneratedMessageV3.Builder param2Builder);
      
      Object getRaw(GeneratedMessageV3.Builder param2Builder);
      
      Object getRaw(GeneratedMessageV3 param2GeneratedMessageV3);
      
      Object getRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int);
      
      Object getRepeated(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int);
      
      Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param2Builder, int param2Int);
      
      int getRepeatedCount(GeneratedMessageV3.Builder param2Builder);
      
      int getRepeatedCount(GeneratedMessageV3 param2GeneratedMessageV3);
      
      Object getRepeatedRaw(GeneratedMessageV3.Builder param2Builder, int param2Int);
      
      Object getRepeatedRaw(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int);
      
      boolean has(GeneratedMessageV3.Builder param2Builder);
      
      boolean has(GeneratedMessageV3 param2GeneratedMessageV3);
      
      Message.Builder newBuilder();
      
      void set(GeneratedMessageV3.Builder param2Builder, Object param2Object);
      
      void setRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int, Object param2Object);
    }
    
    private static class MapFieldAccessor implements FieldAccessor {
      private final Descriptors.FieldDescriptor field;
      
      private final Message mapEntryMessageDefaultInstance;
      
      MapFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1) {
        this.field = param2FieldDescriptor;
        this.mapEntryMessageDefaultInstance = getMapField((GeneratedMessageV3)GeneratedMessageV3.invokeOrDie(GeneratedMessageV3.getMethodOrDie(param2Class, "getDefaultInstance", new Class[0]), null, new Object[0])).getMapEntryMessageDefaultInstance();
      }
      
      private Message coerceType(Message param2Message) {
        return (param2Message == null) ? null : (this.mapEntryMessageDefaultInstance.getClass().isInstance(param2Message) ? param2Message : this.mapEntryMessageDefaultInstance.toBuilder().mergeFrom(param2Message).build());
      }
      
      private MapField<?, ?> getMapField(GeneratedMessageV3.Builder param2Builder) {
        return param2Builder.internalGetMapField(this.field.getNumber());
      }
      
      private MapField<?, ?> getMapField(GeneratedMessageV3 param2GeneratedMessageV3) {
        return param2GeneratedMessageV3.internalGetMapField(this.field.getNumber());
      }
      
      private MapField<?, ?> getMutableMapField(GeneratedMessageV3.Builder param2Builder) {
        return param2Builder.internalGetMutableMapField(this.field.getNumber());
      }
      
      public void addRepeated(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        getMutableMapField(param2Builder).getMutableList().add(coerceType((Message)param2Object));
      }
      
      public void clear(GeneratedMessageV3.Builder param2Builder) {
        getMutableMapField(param2Builder).getMutableList().clear();
      }
      
      public Object get(GeneratedMessageV3.Builder param2Builder) {
        ArrayList<Object> arrayList = new ArrayList();
        for (byte b = 0; b < getRepeatedCount(param2Builder); b++)
          arrayList.add(getRepeated(param2Builder, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Object get(GeneratedMessageV3 param2GeneratedMessageV3) {
        ArrayList<Object> arrayList = new ArrayList();
        for (byte b = 0; b < getRepeatedCount(param2GeneratedMessageV3); b++)
          arrayList.add(getRepeated(param2GeneratedMessageV3, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Message.Builder getBuilder(GeneratedMessageV3.Builder param2Builder) {
        throw new UnsupportedOperationException("Nested builder not supported for map fields.");
      }
      
      public Object getRaw(GeneratedMessageV3.Builder param2Builder) {
        return get(param2Builder);
      }
      
      public Object getRaw(GeneratedMessageV3 param2GeneratedMessageV3) {
        return get(param2GeneratedMessageV3);
      }
      
      public Object getRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        return getMapField(param2Builder).getList().get(param2Int);
      }
      
      public Object getRepeated(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int) {
        return getMapField(param2GeneratedMessageV3).getList().get(param2Int);
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("Nested builder not supported for map fields.");
      }
      
      public int getRepeatedCount(GeneratedMessageV3.Builder param2Builder) {
        return getMapField(param2Builder).getList().size();
      }
      
      public int getRepeatedCount(GeneratedMessageV3 param2GeneratedMessageV3) {
        return getMapField(param2GeneratedMessageV3).getList().size();
      }
      
      public Object getRepeatedRaw(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        return getRepeated(param2Builder, param2Int);
      }
      
      public Object getRepeatedRaw(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int) {
        return getRepeated(param2GeneratedMessageV3, param2Int);
      }
      
      public boolean has(GeneratedMessageV3.Builder param2Builder) {
        throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
      }
      
      public boolean has(GeneratedMessageV3 param2GeneratedMessageV3) {
        throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
      }
      
      public Message.Builder newBuilder() {
        return this.mapEntryMessageDefaultInstance.newBuilderForType();
      }
      
      public void set(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        clear(param2Builder);
        param2Object = ((List)param2Object).iterator();
        while (param2Object.hasNext())
          addRepeated(param2Builder, param2Object.next()); 
      }
      
      public void setRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int, Object param2Object) {
        getMutableMapField(param2Builder).getMutableList().set(param2Int, coerceType((Message)param2Object));
      }
    }
    
    private static class OneofAccessor {
      private final Method caseMethod;
      
      private final Method caseMethodBuilder;
      
      private final Method clearMethod;
      
      private final Descriptors.Descriptor descriptor;
      
      OneofAccessor(Descriptors.Descriptor param2Descriptor, String param2String, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1) {
        this.descriptor = param2Descriptor;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Case");
        this.caseMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Case");
        this.caseMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("clear");
        stringBuilder.append(param2String);
        this.clearMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
      }
      
      public void clear(GeneratedMessageV3.Builder param2Builder) {
        GeneratedMessageV3.invokeOrDie(this.clearMethod, param2Builder, new Object[0]);
      }
      
      public Descriptors.FieldDescriptor get(GeneratedMessageV3.Builder param2Builder) {
        int i = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, param2Builder, new Object[0])).getNumber();
        return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
      }
      
      public Descriptors.FieldDescriptor get(GeneratedMessageV3 param2GeneratedMessageV3) {
        int i = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, param2GeneratedMessageV3, new Object[0])).getNumber();
        return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
      }
      
      public boolean has(GeneratedMessageV3.Builder param2Builder) {
        return !(((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, param2Builder, new Object[0])).getNumber() == 0);
      }
      
      public boolean has(GeneratedMessageV3 param2GeneratedMessageV3) {
        return !(((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, param2GeneratedMessageV3, new Object[0])).getNumber() == 0);
      }
    }
    
    private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
      private Method addRepeatedValueMethod;
      
      private Descriptors.EnumDescriptor enumDescriptor;
      
      private Method getRepeatedValueMethod;
      
      private Method getRepeatedValueMethodBuilder;
      
      private final Method getValueDescriptorMethod;
      
      private Method setRepeatedValueMethod;
      
      private boolean supportUnknownEnumValue;
      
      private final Method valueOfMethod;
      
      RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1) {
        super(param2FieldDescriptor, param2String, param2Class, param2Class1);
        this.enumDescriptor = param2FieldDescriptor.getEnumType();
        this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
        this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
        this.supportUnknownEnumValue = param2FieldDescriptor.getFile().supportsUnknownEnumValue();
        if (this.supportUnknownEnumValue) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.getRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[] { int.class });
          stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.getRepeatedValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
          stringBuilder = new StringBuilder();
          stringBuilder.append("set");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.setRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class, int.class });
          stringBuilder = new StringBuilder();
          stringBuilder.append("add");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.addRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
        } 
      }
      
      public void addRepeated(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        if (this.supportUnknownEnumValue) {
          GeneratedMessageV3.invokeOrDie(this.addRepeatedValueMethod, param2Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param2Object).getNumber()) });
          return;
        } 
        super.addRepeated(param2Builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { param2Object }));
      }
      
      public Object get(GeneratedMessageV3.Builder param2Builder) {
        ArrayList<Object> arrayList = new ArrayList();
        int i = getRepeatedCount(param2Builder);
        for (byte b = 0; b < i; b++)
          arrayList.add(getRepeated(param2Builder, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Object get(GeneratedMessageV3 param2GeneratedMessageV3) {
        ArrayList<Object> arrayList = new ArrayList();
        int i = getRepeatedCount(param2GeneratedMessageV3);
        for (byte b = 0; b < i; b++)
          arrayList.add(getRepeated(param2GeneratedMessageV3, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Object getRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        if (this.supportUnknownEnumValue) {
          param2Int = ((Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethodBuilder, param2Builder, new Object[] { Integer.valueOf(param2Int) })).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param2Int);
        } 
        return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param2Builder, param2Int), new Object[0]);
      }
      
      public Object getRepeated(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int) {
        if (this.supportUnknownEnumValue) {
          param2Int = ((Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethod, param2GeneratedMessageV3, new Object[] { Integer.valueOf(param2Int) })).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param2Int);
        } 
        return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param2GeneratedMessageV3, param2Int), new Object[0]);
      }
      
      public void setRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int, Object param2Object) {
        if (this.supportUnknownEnumValue) {
          GeneratedMessageV3.invokeOrDie(this.setRepeatedValueMethod, param2Builder, new Object[] { Integer.valueOf(param2Int), Integer.valueOf(((Descriptors.EnumValueDescriptor)param2Object).getNumber()) });
          return;
        } 
        super.setRepeated(param2Builder, param2Int, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { param2Object }));
      }
    }
    
    private static class RepeatedFieldAccessor implements FieldAccessor {
      protected final Method addRepeatedMethod;
      
      protected final Method clearMethod;
      
      protected final Method getCountMethod;
      
      protected final Method getCountMethodBuilder;
      
      protected final Method getMethod;
      
      protected final Method getMethodBuilder;
      
      protected final Method getRepeatedMethod;
      
      protected final Method getRepeatedMethodBuilder;
      
      protected final Method setRepeatedMethod;
      
      protected final Class type;
      
      RepeatedFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("List");
        this.getMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("List");
        this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        this.getRepeatedMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[] { int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        this.getRepeatedMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
        this.type = this.getRepeatedMethod.getReturnType();
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param2String);
        this.setRepeatedMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class, this.type });
        stringBuilder = new StringBuilder();
        stringBuilder.append("add");
        stringBuilder.append(param2String);
        this.addRepeatedMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { this.type });
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Count");
        this.getCountMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Count");
        this.getCountMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("clear");
        stringBuilder.append(param2String);
        this.clearMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
      }
      
      public void addRepeated(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        GeneratedMessageV3.invokeOrDie(this.addRepeatedMethod, param2Builder, new Object[] { param2Object });
      }
      
      public void clear(GeneratedMessageV3.Builder param2Builder) {
        GeneratedMessageV3.invokeOrDie(this.clearMethod, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessageV3.Builder param2Builder) {
        return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessageV3 param2GeneratedMessageV3) {
        return GeneratedMessageV3.invokeOrDie(this.getMethod, param2GeneratedMessageV3, new Object[0]);
      }
      
      public Message.Builder getBuilder(GeneratedMessageV3.Builder param2Builder) {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }
      
      public Object getRaw(GeneratedMessageV3.Builder param2Builder) {
        return get(param2Builder);
      }
      
      public Object getRaw(GeneratedMessageV3 param2GeneratedMessageV3) {
        return get(param2GeneratedMessageV3);
      }
      
      public Object getRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethodBuilder, param2Builder, new Object[] { Integer.valueOf(param2Int) });
      }
      
      public Object getRepeated(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int) {
        return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethod, param2GeneratedMessageV3, new Object[] { Integer.valueOf(param2Int) });
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
      }
      
      public int getRepeatedCount(GeneratedMessageV3.Builder param2Builder) {
        return ((Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethodBuilder, param2Builder, new Object[0])).intValue();
      }
      
      public int getRepeatedCount(GeneratedMessageV3 param2GeneratedMessageV3) {
        return ((Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethod, param2GeneratedMessageV3, new Object[0])).intValue();
      }
      
      public Object getRepeatedRaw(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        return getRepeated(param2Builder, param2Int);
      }
      
      public Object getRepeatedRaw(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int) {
        return getRepeated(param2GeneratedMessageV3, param2Int);
      }
      
      public boolean has(GeneratedMessageV3.Builder param2Builder) {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }
      
      public boolean has(GeneratedMessageV3 param2GeneratedMessageV3) {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }
      
      public Message.Builder newBuilder() {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
      
      public void set(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        clear(param2Builder);
        param2Object = ((List)param2Object).iterator();
        while (param2Object.hasNext())
          addRepeated(param2Builder, param2Object.next()); 
      }
      
      public void setRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int, Object param2Object) {
        GeneratedMessageV3.invokeOrDie(this.setRepeatedMethod, param2Builder, new Object[] { Integer.valueOf(param2Int), param2Object });
      }
    }
    
    private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
      private final Method getBuilderMethodBuilder;
      
      private final Method newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
      
      RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1) {
        super(param2FieldDescriptor, param2String, param2Class, param2Class1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Builder");
        this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
      }
      
      private Object coerceType(Object param2Object) {
        return this.type.isInstance(param2Object) ? param2Object : ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param2Object).build();
      }
      
      public void addRepeated(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        super.addRepeated(param2Builder, coerceType(param2Object));
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, param2Builder, new Object[] { Integer.valueOf(param2Int) });
      }
      
      public Message.Builder newBuilder() {
        return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
      
      public void setRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int, Object param2Object) {
        super.setRepeated(param2Builder, param2Int, coerceType(param2Object));
      }
    }
    
    private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
      private Descriptors.EnumDescriptor enumDescriptor;
      
      private Method getValueDescriptorMethod;
      
      private Method getValueMethod;
      
      private Method getValueMethodBuilder;
      
      private Method setValueMethod;
      
      private boolean supportUnknownEnumValue;
      
      private Method valueOfMethod;
      
      SingularEnumFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1, String param2String2) {
        super(param2FieldDescriptor, param2String1, param2Class, param2Class1, param2String2);
        this.enumDescriptor = param2FieldDescriptor.getEnumType();
        this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
        this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
        this.supportUnknownEnumValue = param2FieldDescriptor.getFile().supportsUnknownEnumValue();
        if (this.supportUnknownEnumValue) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String1);
          stringBuilder.append("Value");
          this.getValueMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
          stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String1);
          stringBuilder.append("Value");
          this.getValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
          stringBuilder = new StringBuilder();
          stringBuilder.append("set");
          stringBuilder.append(param2String1);
          stringBuilder.append("Value");
          this.setValueMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
        } 
      }
      
      public Object get(GeneratedMessageV3.Builder param2Builder) {
        if (this.supportUnknownEnumValue) {
          int i = ((Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethodBuilder, param2Builder, new Object[0])).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
        } 
        return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(param2Builder), new Object[0]);
      }
      
      public Object get(GeneratedMessageV3 param2GeneratedMessageV3) {
        if (this.supportUnknownEnumValue) {
          int i = ((Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethod, param2GeneratedMessageV3, new Object[0])).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
        } 
        return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(param2GeneratedMessageV3), new Object[0]);
      }
      
      public void set(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        if (this.supportUnknownEnumValue) {
          GeneratedMessageV3.invokeOrDie(this.setValueMethod, param2Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param2Object).getNumber()) });
          return;
        } 
        super.set(param2Builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { param2Object }));
      }
    }
    
    private static class SingularFieldAccessor implements FieldAccessor {
      protected final Method caseMethod;
      
      protected final Method caseMethodBuilder;
      
      protected final Method clearMethod;
      
      protected final Descriptors.FieldDescriptor field;
      
      protected final Method getMethod;
      
      protected final Method getMethodBuilder;
      
      protected final boolean hasHasMethod;
      
      protected final Method hasMethod;
      
      protected final Method hasMethodBuilder;
      
      protected final boolean isOneofField;
      
      protected final Method setMethod;
      
      protected final Class<?> type;
      
      SingularFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1, String param2String2) {
        Method method;
        this.field = param2FieldDescriptor;
        if (param2FieldDescriptor.getContainingOneof() != null) {
          bool = true;
        } else {
          bool = false;
        } 
        this.isOneofField = bool;
        if (GeneratedMessageV3.FieldAccessorTable.supportFieldPresence(param2FieldDescriptor.getFile()) || (!this.isOneofField && param2FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)) {
          bool = true;
        } else {
          bool = false;
        } 
        this.hasHasMethod = bool;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get");
        stringBuilder1.append(param2String1);
        this.getMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder1.toString(), new Class[0]);
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get");
        stringBuilder1.append(param2String1);
        this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        this.type = this.getMethod.getReturnType();
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("set");
        stringBuilder1.append(param2String1);
        this.setMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[] { this.type });
        boolean bool = this.hasHasMethod;
        StringBuilder stringBuilder2 = null;
        if (bool) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("has");
          stringBuilder1.append(param2String1);
          method = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder1.toString(), new Class[0]);
        } else {
          stringBuilder1 = null;
        } 
        this.hasMethod = (Method)stringBuilder1;
        if (this.hasHasMethod) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("has");
          stringBuilder1.append(param2String1);
          method = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        } else {
          stringBuilder1 = null;
        } 
        this.hasMethodBuilder = (Method)stringBuilder1;
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("clear");
        stringBuilder1.append(param2String1);
        this.clearMethod = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        if (this.isOneofField) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("get");
          stringBuilder1.append(param2String2);
          stringBuilder1.append("Case");
          method = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder1.toString(), new Class[0]);
        } else {
          stringBuilder1 = null;
        } 
        this.caseMethod = (Method)stringBuilder1;
        stringBuilder1 = stringBuilder2;
        if (this.isOneofField) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("get");
          stringBuilder1.append(param2String2);
          stringBuilder1.append("Case");
          method = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        } 
        this.caseMethodBuilder = method;
      }
      
      private int getOneofFieldNumber(GeneratedMessageV3.Builder param2Builder) {
        return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, param2Builder, new Object[0])).getNumber();
      }
      
      private int getOneofFieldNumber(GeneratedMessageV3 param2GeneratedMessageV3) {
        return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, param2GeneratedMessageV3, new Object[0])).getNumber();
      }
      
      public void addRepeated(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
      }
      
      public void clear(GeneratedMessageV3.Builder param2Builder) {
        GeneratedMessageV3.invokeOrDie(this.clearMethod, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessageV3.Builder param2Builder) {
        return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessageV3 param2GeneratedMessageV3) {
        return GeneratedMessageV3.invokeOrDie(this.getMethod, param2GeneratedMessageV3, new Object[0]);
      }
      
      public Message.Builder getBuilder(GeneratedMessageV3.Builder param2Builder) {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }
      
      public Object getRaw(GeneratedMessageV3.Builder param2Builder) {
        return get(param2Builder);
      }
      
      public Object getRaw(GeneratedMessageV3 param2GeneratedMessageV3) {
        return get(param2GeneratedMessageV3);
      }
      
      public Object getRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }
      
      public Object getRepeated(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
      }
      
      public int getRepeatedCount(GeneratedMessageV3.Builder param2Builder) {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }
      
      public int getRepeatedCount(GeneratedMessageV3 param2GeneratedMessageV3) {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }
      
      public Object getRepeatedRaw(GeneratedMessageV3.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
      }
      
      public Object getRepeatedRaw(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
      }
      
      public boolean has(GeneratedMessageV3.Builder param2Builder) {
        boolean bool = this.hasHasMethod;
        boolean bool1 = false;
        if (!bool) {
          if (this.isOneofField) {
            if (getOneofFieldNumber(param2Builder) == this.field.getNumber())
              bool1 = true; 
            return bool1;
          } 
          return get(param2Builder).equals(this.field.getDefaultValue()) ^ true;
        } 
        return ((Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethodBuilder, param2Builder, new Object[0])).booleanValue();
      }
      
      public boolean has(GeneratedMessageV3 param2GeneratedMessageV3) {
        boolean bool = this.hasHasMethod;
        boolean bool1 = false;
        if (!bool) {
          if (this.isOneofField) {
            if (getOneofFieldNumber(param2GeneratedMessageV3) == this.field.getNumber())
              bool1 = true; 
            return bool1;
          } 
          return get(param2GeneratedMessageV3).equals(this.field.getDefaultValue()) ^ true;
        } 
        return ((Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethod, param2GeneratedMessageV3, new Object[0])).booleanValue();
      }
      
      public Message.Builder newBuilder() {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
      
      public void set(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        GeneratedMessageV3.invokeOrDie(this.setMethod, param2Builder, new Object[] { param2Object });
      }
      
      public void setRepeated(GeneratedMessageV3.Builder param2Builder, int param2Int, Object param2Object) {
        throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
      }
    }
    
    private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
      private final Method getBuilderMethodBuilder;
      
      private final Method newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
      
      SingularMessageFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1, String param2String2) {
        super(param2FieldDescriptor, param2String1, param2Class, param2Class1, param2String2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String1);
        stringBuilder.append("Builder");
        this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
      }
      
      private Object coerceType(Object param2Object) {
        return this.type.isInstance(param2Object) ? param2Object : ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param2Object).buildPartial();
      }
      
      public Message.Builder getBuilder(GeneratedMessageV3.Builder param2Builder) {
        return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Message.Builder newBuilder() {
        return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
      
      public void set(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        super.set(param2Builder, coerceType(param2Object));
      }
    }
    
    private static final class SingularStringFieldAccessor extends SingularFieldAccessor {
      private final Method getBytesMethod;
      
      private final Method getBytesMethodBuilder;
      
      private final Method setBytesMethodBuilder;
      
      SingularStringFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessageV3> param2Class, Class<? extends GeneratedMessageV3.Builder> param2Class1, String param2String2) {
        super(param2FieldDescriptor, param2String1, param2Class, param2Class1, param2String2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String1);
        stringBuilder.append("Bytes");
        this.getBytesMethod = GeneratedMessageV3.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String1);
        stringBuilder.append("Bytes");
        this.getBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param2String1);
        stringBuilder.append("Bytes");
        this.setBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { ByteString.class });
      }
      
      public Object getRaw(GeneratedMessageV3.Builder param2Builder) {
        return GeneratedMessageV3.invokeOrDie(this.getBytesMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Object getRaw(GeneratedMessageV3 param2GeneratedMessageV3) {
        return GeneratedMessageV3.invokeOrDie(this.getBytesMethod, param2GeneratedMessageV3, new Object[0]);
      }
      
      public void set(GeneratedMessageV3.Builder param2Builder, Object param2Object) {
        if (param2Object instanceof ByteString) {
          GeneratedMessageV3.invokeOrDie(this.setBytesMethodBuilder, param2Builder, new Object[] { param2Object });
        } else {
          super.set(param2Builder, param2Object);
        } 
      }
    }
  }
  
  private static interface FieldAccessor {
    void addRepeated(GeneratedMessageV3.Builder param1Builder, Object param1Object);
    
    void clear(GeneratedMessageV3.Builder param1Builder);
    
    Object get(GeneratedMessageV3.Builder param1Builder);
    
    Object get(GeneratedMessageV3 param1GeneratedMessageV3);
    
    Message.Builder getBuilder(GeneratedMessageV3.Builder param1Builder);
    
    Object getRaw(GeneratedMessageV3.Builder param1Builder);
    
    Object getRaw(GeneratedMessageV3 param1GeneratedMessageV3);
    
    Object getRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int);
    
    Object getRepeated(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int);
    
    Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param1Builder, int param1Int);
    
    int getRepeatedCount(GeneratedMessageV3.Builder param1Builder);
    
    int getRepeatedCount(GeneratedMessageV3 param1GeneratedMessageV3);
    
    Object getRepeatedRaw(GeneratedMessageV3.Builder param1Builder, int param1Int);
    
    Object getRepeatedRaw(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int);
    
    boolean has(GeneratedMessageV3.Builder param1Builder);
    
    boolean has(GeneratedMessageV3 param1GeneratedMessageV3);
    
    Message.Builder newBuilder();
    
    void set(GeneratedMessageV3.Builder param1Builder, Object param1Object);
    
    void setRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int, Object param1Object);
  }
  
  private static class MapFieldAccessor implements FieldAccessorTable.FieldAccessor {
    private final Descriptors.FieldDescriptor field;
    
    private final Message mapEntryMessageDefaultInstance;
    
    MapFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1) {
      this.field = param1FieldDescriptor;
      this.mapEntryMessageDefaultInstance = getMapField((GeneratedMessageV3)GeneratedMessageV3.invokeOrDie(GeneratedMessageV3.getMethodOrDie(param1Class, "getDefaultInstance", new Class[0]), null, new Object[0])).getMapEntryMessageDefaultInstance();
    }
    
    private Message coerceType(Message param1Message) {
      return (param1Message == null) ? null : (this.mapEntryMessageDefaultInstance.getClass().isInstance(param1Message) ? param1Message : this.mapEntryMessageDefaultInstance.toBuilder().mergeFrom(param1Message).build());
    }
    
    private MapField<?, ?> getMapField(GeneratedMessageV3.Builder param1Builder) {
      return param1Builder.internalGetMapField(this.field.getNumber());
    }
    
    private MapField<?, ?> getMapField(GeneratedMessageV3 param1GeneratedMessageV3) {
      return param1GeneratedMessageV3.internalGetMapField(this.field.getNumber());
    }
    
    private MapField<?, ?> getMutableMapField(GeneratedMessageV3.Builder param1Builder) {
      return param1Builder.internalGetMutableMapField(this.field.getNumber());
    }
    
    public void addRepeated(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      getMutableMapField(param1Builder).getMutableList().add(coerceType((Message)param1Object));
    }
    
    public void clear(GeneratedMessageV3.Builder param1Builder) {
      getMutableMapField(param1Builder).getMutableList().clear();
    }
    
    public Object get(GeneratedMessageV3.Builder param1Builder) {
      ArrayList<Object> arrayList = new ArrayList();
      for (byte b = 0; b < getRepeatedCount(param1Builder); b++)
        arrayList.add(getRepeated(param1Builder, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Object get(GeneratedMessageV3 param1GeneratedMessageV3) {
      ArrayList<Object> arrayList = new ArrayList();
      for (byte b = 0; b < getRepeatedCount(param1GeneratedMessageV3); b++)
        arrayList.add(getRepeated(param1GeneratedMessageV3, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Message.Builder getBuilder(GeneratedMessageV3.Builder param1Builder) {
      throw new UnsupportedOperationException("Nested builder not supported for map fields.");
    }
    
    public Object getRaw(GeneratedMessageV3.Builder param1Builder) {
      return get(param1Builder);
    }
    
    public Object getRaw(GeneratedMessageV3 param1GeneratedMessageV3) {
      return get(param1GeneratedMessageV3);
    }
    
    public Object getRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      return getMapField(param1Builder).getList().get(param1Int);
    }
    
    public Object getRepeated(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int) {
      return getMapField(param1GeneratedMessageV3).getList().get(param1Int);
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("Nested builder not supported for map fields.");
    }
    
    public int getRepeatedCount(GeneratedMessageV3.Builder param1Builder) {
      return getMapField(param1Builder).getList().size();
    }
    
    public int getRepeatedCount(GeneratedMessageV3 param1GeneratedMessageV3) {
      return getMapField(param1GeneratedMessageV3).getList().size();
    }
    
    public Object getRepeatedRaw(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      return getRepeated(param1Builder, param1Int);
    }
    
    public Object getRepeatedRaw(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int) {
      return getRepeated(param1GeneratedMessageV3, param1Int);
    }
    
    public boolean has(GeneratedMessageV3.Builder param1Builder) {
      throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
    }
    
    public boolean has(GeneratedMessageV3 param1GeneratedMessageV3) {
      throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
    }
    
    public Message.Builder newBuilder() {
      return this.mapEntryMessageDefaultInstance.newBuilderForType();
    }
    
    public void set(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      clear(param1Builder);
      param1Object = ((List)param1Object).iterator();
      while (param1Object.hasNext())
        addRepeated(param1Builder, param1Object.next()); 
    }
    
    public void setRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int, Object param1Object) {
      getMutableMapField(param1Builder).getMutableList().set(param1Int, coerceType((Message)param1Object));
    }
  }
  
  private static class OneofAccessor {
    private final Method caseMethod;
    
    private final Method caseMethodBuilder;
    
    private final Method clearMethod;
    
    private final Descriptors.Descriptor descriptor;
    
    OneofAccessor(Descriptors.Descriptor param1Descriptor, String param1String, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1) {
      this.descriptor = param1Descriptor;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Case");
      this.caseMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Case");
      this.caseMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("clear");
      stringBuilder.append(param1String);
      this.clearMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
    }
    
    public void clear(GeneratedMessageV3.Builder param1Builder) {
      GeneratedMessageV3.invokeOrDie(this.clearMethod, param1Builder, new Object[0]);
    }
    
    public Descriptors.FieldDescriptor get(GeneratedMessageV3.Builder param1Builder) {
      int i = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, param1Builder, new Object[0])).getNumber();
      return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
    }
    
    public Descriptors.FieldDescriptor get(GeneratedMessageV3 param1GeneratedMessageV3) {
      int i = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, param1GeneratedMessageV3, new Object[0])).getNumber();
      return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
    }
    
    public boolean has(GeneratedMessageV3.Builder param1Builder) {
      return !(((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, param1Builder, new Object[0])).getNumber() == 0);
    }
    
    public boolean has(GeneratedMessageV3 param1GeneratedMessageV3) {
      return !(((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, param1GeneratedMessageV3, new Object[0])).getNumber() == 0);
    }
  }
  
  private static final class RepeatedEnumFieldAccessor extends FieldAccessorTable.RepeatedFieldAccessor {
    private Method addRepeatedValueMethod;
    
    private Descriptors.EnumDescriptor enumDescriptor;
    
    private Method getRepeatedValueMethod;
    
    private Method getRepeatedValueMethodBuilder;
    
    private final Method getValueDescriptorMethod;
    
    private Method setRepeatedValueMethod;
    
    private boolean supportUnknownEnumValue;
    
    private final Method valueOfMethod;
    
    RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1) {
      super(param1FieldDescriptor, param1String, param1Class, param1Class1);
      this.enumDescriptor = param1FieldDescriptor.getEnumType();
      this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      this.supportUnknownEnumValue = param1FieldDescriptor.getFile().supportsUnknownEnumValue();
      if (this.supportUnknownEnumValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.getRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[] { int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.getRepeatedValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.setRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class, int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("add");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.addRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
      } 
    }
    
    public void addRepeated(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      if (this.supportUnknownEnumValue) {
        GeneratedMessageV3.invokeOrDie(this.addRepeatedValueMethod, param1Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param1Object).getNumber()) });
        return;
      } 
      super.addRepeated(param1Builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { param1Object }));
    }
    
    public Object get(GeneratedMessageV3.Builder param1Builder) {
      ArrayList<Object> arrayList = new ArrayList();
      int i = getRepeatedCount(param1Builder);
      for (byte b = 0; b < i; b++)
        arrayList.add(getRepeated(param1Builder, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Object get(GeneratedMessageV3 param1GeneratedMessageV3) {
      ArrayList<Object> arrayList = new ArrayList();
      int i = getRepeatedCount(param1GeneratedMessageV3);
      for (byte b = 0; b < i; b++)
        arrayList.add(getRepeated(param1GeneratedMessageV3, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Object getRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      if (this.supportUnknownEnumValue) {
        param1Int = ((Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethodBuilder, param1Builder, new Object[] { Integer.valueOf(param1Int) })).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param1Int);
      } 
      return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param1Builder, param1Int), new Object[0]);
    }
    
    public Object getRepeated(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int) {
      if (this.supportUnknownEnumValue) {
        param1Int = ((Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethod, param1GeneratedMessageV3, new Object[] { Integer.valueOf(param1Int) })).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param1Int);
      } 
      return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param1GeneratedMessageV3, param1Int), new Object[0]);
    }
    
    public void setRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int, Object param1Object) {
      if (this.supportUnknownEnumValue) {
        GeneratedMessageV3.invokeOrDie(this.setRepeatedValueMethod, param1Builder, new Object[] { Integer.valueOf(param1Int), Integer.valueOf(((Descriptors.EnumValueDescriptor)param1Object).getNumber()) });
        return;
      } 
      super.setRepeated(param1Builder, param1Int, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { param1Object }));
    }
  }
  
  private static class RepeatedFieldAccessor implements FieldAccessorTable.FieldAccessor {
    protected final Method addRepeatedMethod;
    
    protected final Method clearMethod;
    
    protected final Method getCountMethod;
    
    protected final Method getCountMethodBuilder;
    
    protected final Method getMethod;
    
    protected final Method getMethodBuilder;
    
    protected final Method getRepeatedMethod;
    
    protected final Method getRepeatedMethodBuilder;
    
    protected final Method setRepeatedMethod;
    
    protected final Class type;
    
    RepeatedFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("List");
      this.getMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("List");
      this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      this.getRepeatedMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[] { int.class });
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      this.getRepeatedMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
      this.type = this.getRepeatedMethod.getReturnType();
      stringBuilder = new StringBuilder();
      stringBuilder.append("set");
      stringBuilder.append(param1String);
      this.setRepeatedMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class, this.type });
      stringBuilder = new StringBuilder();
      stringBuilder.append("add");
      stringBuilder.append(param1String);
      this.addRepeatedMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { this.type });
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Count");
      this.getCountMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Count");
      this.getCountMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("clear");
      stringBuilder.append(param1String);
      this.clearMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
    }
    
    public void addRepeated(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      GeneratedMessageV3.invokeOrDie(this.addRepeatedMethod, param1Builder, new Object[] { param1Object });
    }
    
    public void clear(GeneratedMessageV3.Builder param1Builder) {
      GeneratedMessageV3.invokeOrDie(this.clearMethod, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessageV3.Builder param1Builder) {
      return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessageV3 param1GeneratedMessageV3) {
      return GeneratedMessageV3.invokeOrDie(this.getMethod, param1GeneratedMessageV3, new Object[0]);
    }
    
    public Message.Builder getBuilder(GeneratedMessageV3.Builder param1Builder) {
      throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
    }
    
    public Object getRaw(GeneratedMessageV3.Builder param1Builder) {
      return get(param1Builder);
    }
    
    public Object getRaw(GeneratedMessageV3 param1GeneratedMessageV3) {
      return get(param1GeneratedMessageV3);
    }
    
    public Object getRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethodBuilder, param1Builder, new Object[] { Integer.valueOf(param1Int) });
    }
    
    public Object getRepeated(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int) {
      return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethod, param1GeneratedMessageV3, new Object[] { Integer.valueOf(param1Int) });
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
    }
    
    public int getRepeatedCount(GeneratedMessageV3.Builder param1Builder) {
      return ((Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethodBuilder, param1Builder, new Object[0])).intValue();
    }
    
    public int getRepeatedCount(GeneratedMessageV3 param1GeneratedMessageV3) {
      return ((Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethod, param1GeneratedMessageV3, new Object[0])).intValue();
    }
    
    public Object getRepeatedRaw(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      return getRepeated(param1Builder, param1Int);
    }
    
    public Object getRepeatedRaw(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int) {
      return getRepeated(param1GeneratedMessageV3, param1Int);
    }
    
    public boolean has(GeneratedMessageV3.Builder param1Builder) {
      throw new UnsupportedOperationException("hasField() called on a repeated field.");
    }
    
    public boolean has(GeneratedMessageV3 param1GeneratedMessageV3) {
      throw new UnsupportedOperationException("hasField() called on a repeated field.");
    }
    
    public Message.Builder newBuilder() {
      throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
    }
    
    public void set(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      clear(param1Builder);
      param1Object = ((List)param1Object).iterator();
      while (param1Object.hasNext())
        addRepeated(param1Builder, param1Object.next()); 
    }
    
    public void setRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int, Object param1Object) {
      GeneratedMessageV3.invokeOrDie(this.setRepeatedMethod, param1Builder, new Object[] { Integer.valueOf(param1Int), param1Object });
    }
  }
  
  private static final class RepeatedMessageFieldAccessor extends FieldAccessorTable.RepeatedFieldAccessor {
    private final Method getBuilderMethodBuilder;
    
    private final Method newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
    
    RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1) {
      super(param1FieldDescriptor, param1String, param1Class, param1Class1);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Builder");
      this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
    }
    
    private Object coerceType(Object param1Object) {
      return this.type.isInstance(param1Object) ? param1Object : ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param1Object).build();
    }
    
    public void addRepeated(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      super.addRepeated(param1Builder, coerceType(param1Object));
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, param1Builder, new Object[] { Integer.valueOf(param1Int) });
    }
    
    public Message.Builder newBuilder() {
      return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
    }
    
    public void setRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int, Object param1Object) {
      super.setRepeated(param1Builder, param1Int, coerceType(param1Object));
    }
  }
  
  private static final class SingularEnumFieldAccessor extends FieldAccessorTable.SingularFieldAccessor {
    private Descriptors.EnumDescriptor enumDescriptor;
    
    private Method getValueDescriptorMethod;
    
    private Method getValueMethod;
    
    private Method getValueMethodBuilder;
    
    private Method setValueMethod;
    
    private boolean supportUnknownEnumValue;
    
    private Method valueOfMethod;
    
    SingularEnumFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1, String param1String2) {
      super(param1FieldDescriptor, param1String1, param1Class, param1Class1, param1String2);
      this.enumDescriptor = param1FieldDescriptor.getEnumType();
      this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      this.supportUnknownEnumValue = param1FieldDescriptor.getFile().supportsUnknownEnumValue();
      if (this.supportUnknownEnumValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String1);
        stringBuilder.append("Value");
        this.getValueMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String1);
        stringBuilder.append("Value");
        this.getValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param1String1);
        stringBuilder.append("Value");
        this.setValueMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
      } 
    }
    
    public Object get(GeneratedMessageV3.Builder param1Builder) {
      if (this.supportUnknownEnumValue) {
        int i = ((Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethodBuilder, param1Builder, new Object[0])).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
      } 
      return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(param1Builder), new Object[0]);
    }
    
    public Object get(GeneratedMessageV3 param1GeneratedMessageV3) {
      if (this.supportUnknownEnumValue) {
        int i = ((Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethod, param1GeneratedMessageV3, new Object[0])).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
      } 
      return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(param1GeneratedMessageV3), new Object[0]);
    }
    
    public void set(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      if (this.supportUnknownEnumValue) {
        GeneratedMessageV3.invokeOrDie(this.setValueMethod, param1Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param1Object).getNumber()) });
        return;
      } 
      super.set(param1Builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { param1Object }));
    }
  }
  
  private static class SingularFieldAccessor implements FieldAccessorTable.FieldAccessor {
    protected final Method caseMethod;
    
    protected final Method caseMethodBuilder;
    
    protected final Method clearMethod;
    
    protected final Descriptors.FieldDescriptor field;
    
    protected final Method getMethod;
    
    protected final Method getMethodBuilder;
    
    protected final boolean hasHasMethod;
    
    protected final Method hasMethod;
    
    protected final Method hasMethodBuilder;
    
    protected final boolean isOneofField;
    
    protected final Method setMethod;
    
    protected final Class<?> type;
    
    SingularFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1, String param1String2) {
      Method method;
      this.field = param1FieldDescriptor;
      if (param1FieldDescriptor.getContainingOneof() != null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.isOneofField = bool;
      if (GeneratedMessageV3.FieldAccessorTable.supportFieldPresence(param1FieldDescriptor.getFile()) || (!this.isOneofField && param1FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)) {
        bool = true;
      } else {
        bool = false;
      } 
      this.hasHasMethod = bool;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("get");
      stringBuilder1.append(param1String1);
      this.getMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder1.toString(), new Class[0]);
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("get");
      stringBuilder1.append(param1String1);
      this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      this.type = this.getMethod.getReturnType();
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("set");
      stringBuilder1.append(param1String1);
      this.setMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[] { this.type });
      boolean bool = this.hasHasMethod;
      StringBuilder stringBuilder2 = null;
      if (bool) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("has");
        stringBuilder1.append(param1String1);
        method = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder1.toString(), new Class[0]);
      } else {
        stringBuilder1 = null;
      } 
      this.hasMethod = (Method)stringBuilder1;
      if (this.hasHasMethod) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("has");
        stringBuilder1.append(param1String1);
        method = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      } else {
        stringBuilder1 = null;
      } 
      this.hasMethodBuilder = (Method)stringBuilder1;
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("clear");
      stringBuilder1.append(param1String1);
      this.clearMethod = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      if (this.isOneofField) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get");
        stringBuilder1.append(param1String2);
        stringBuilder1.append("Case");
        method = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder1.toString(), new Class[0]);
      } else {
        stringBuilder1 = null;
      } 
      this.caseMethod = (Method)stringBuilder1;
      stringBuilder1 = stringBuilder2;
      if (this.isOneofField) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get");
        stringBuilder1.append(param1String2);
        stringBuilder1.append("Case");
        method = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      } 
      this.caseMethodBuilder = method;
    }
    
    private int getOneofFieldNumber(GeneratedMessageV3.Builder param1Builder) {
      return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, param1Builder, new Object[0])).getNumber();
    }
    
    private int getOneofFieldNumber(GeneratedMessageV3 param1GeneratedMessageV3) {
      return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, param1GeneratedMessageV3, new Object[0])).getNumber();
    }
    
    public void addRepeated(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
    }
    
    public void clear(GeneratedMessageV3.Builder param1Builder) {
      GeneratedMessageV3.invokeOrDie(this.clearMethod, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessageV3.Builder param1Builder) {
      return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessageV3 param1GeneratedMessageV3) {
      return GeneratedMessageV3.invokeOrDie(this.getMethod, param1GeneratedMessageV3, new Object[0]);
    }
    
    public Message.Builder getBuilder(GeneratedMessageV3.Builder param1Builder) {
      throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
    }
    
    public Object getRaw(GeneratedMessageV3.Builder param1Builder) {
      return get(param1Builder);
    }
    
    public Object getRaw(GeneratedMessageV3 param1GeneratedMessageV3) {
      return get(param1GeneratedMessageV3);
    }
    
    public Object getRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
    }
    
    public Object getRepeated(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
    }
    
    public int getRepeatedCount(GeneratedMessageV3.Builder param1Builder) {
      throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
    }
    
    public int getRepeatedCount(GeneratedMessageV3 param1GeneratedMessageV3) {
      throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
    }
    
    public Object getRepeatedRaw(GeneratedMessageV3.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
    }
    
    public Object getRepeatedRaw(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
    }
    
    public boolean has(GeneratedMessageV3.Builder param1Builder) {
      boolean bool = this.hasHasMethod;
      boolean bool1 = false;
      if (!bool) {
        if (this.isOneofField) {
          if (getOneofFieldNumber(param1Builder) == this.field.getNumber())
            bool1 = true; 
          return bool1;
        } 
        return get(param1Builder).equals(this.field.getDefaultValue()) ^ true;
      } 
      return ((Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethodBuilder, param1Builder, new Object[0])).booleanValue();
    }
    
    public boolean has(GeneratedMessageV3 param1GeneratedMessageV3) {
      boolean bool = this.hasHasMethod;
      boolean bool1 = false;
      if (!bool) {
        if (this.isOneofField) {
          if (getOneofFieldNumber(param1GeneratedMessageV3) == this.field.getNumber())
            bool1 = true; 
          return bool1;
        } 
        return get(param1GeneratedMessageV3).equals(this.field.getDefaultValue()) ^ true;
      } 
      return ((Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethod, param1GeneratedMessageV3, new Object[0])).booleanValue();
    }
    
    public Message.Builder newBuilder() {
      throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
    }
    
    public void set(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      GeneratedMessageV3.invokeOrDie(this.setMethod, param1Builder, new Object[] { param1Object });
    }
    
    public void setRepeated(GeneratedMessageV3.Builder param1Builder, int param1Int, Object param1Object) {
      throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
    }
  }
  
  private static final class SingularMessageFieldAccessor extends FieldAccessorTable.SingularFieldAccessor {
    private final Method getBuilderMethodBuilder;
    
    private final Method newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
    
    SingularMessageFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1, String param1String2) {
      super(param1FieldDescriptor, param1String1, param1Class, param1Class1, param1String2);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String1);
      stringBuilder.append("Builder");
      this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
    }
    
    private Object coerceType(Object param1Object) {
      return this.type.isInstance(param1Object) ? param1Object : ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param1Object).buildPartial();
    }
    
    public Message.Builder getBuilder(GeneratedMessageV3.Builder param1Builder) {
      return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Message.Builder newBuilder() {
      return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
    }
    
    public void set(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      super.set(param1Builder, coerceType(param1Object));
    }
  }
  
  private static final class SingularStringFieldAccessor extends FieldAccessorTable.SingularFieldAccessor {
    private final Method getBytesMethod;
    
    private final Method getBytesMethodBuilder;
    
    private final Method setBytesMethodBuilder;
    
    SingularStringFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessageV3> param1Class, Class<? extends GeneratedMessageV3.Builder> param1Class1, String param1String2) {
      super(param1FieldDescriptor, param1String1, param1Class, param1Class1, param1String2);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String1);
      stringBuilder.append("Bytes");
      this.getBytesMethod = GeneratedMessageV3.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String1);
      stringBuilder.append("Bytes");
      this.getBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("set");
      stringBuilder.append(param1String1);
      stringBuilder.append("Bytes");
      this.setBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { ByteString.class });
    }
    
    public Object getRaw(GeneratedMessageV3.Builder param1Builder) {
      return GeneratedMessageV3.invokeOrDie(this.getBytesMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Object getRaw(GeneratedMessageV3 param1GeneratedMessageV3) {
      return GeneratedMessageV3.invokeOrDie(this.getBytesMethod, param1GeneratedMessageV3, new Object[0]);
    }
    
    public void set(GeneratedMessageV3.Builder param1Builder, Object param1Object) {
      if (param1Object instanceof ByteString) {
        GeneratedMessageV3.invokeOrDie(this.setBytesMethodBuilder, param1Builder, new Object[] { param1Object });
      } else {
        super.set(param1Builder, param1Object);
      } 
    }
  }
  
  protected static final class UnusedPrivateParameter {
    static final UnusedPrivateParameter INSTANCE = new UnusedPrivateParameter();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\GeneratedMessageV3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */