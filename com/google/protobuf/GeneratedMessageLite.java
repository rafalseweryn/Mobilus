package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends GeneratedMessageLite.Builder<MessageType, BuilderType>> extends AbstractMessageLite<MessageType, BuilderType> {
  private static Map<Object, GeneratedMessageLite<?, ?>> defaultInstanceMap = new ConcurrentHashMap<>();
  
  protected int memoizedSerializedSize = -1;
  
  protected UnknownFieldSetLite unknownFields = UnknownFieldSetLite.getDefaultInstance();
  
  private static <MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>, T> GeneratedExtension<MessageType, T> checkIsLite(ExtensionLite<MessageType, T> paramExtensionLite) {
    if (!paramExtensionLite.isLite())
      throw new IllegalArgumentException("Expected a lite extension."); 
    return (GeneratedExtension<MessageType, T>)paramExtensionLite;
  }
  
  private static <T extends GeneratedMessageLite<T, ?>> T checkMessageInitialized(T paramT) throws InvalidProtocolBufferException {
    if (paramT != null && !paramT.isInitialized())
      throw paramT.newUninitializedMessageException().asInvalidProtocolBufferException().setUnfinishedMessage(paramT); 
    return paramT;
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
  
  protected static <E> Internal.ProtobufList<E> emptyProtobufList() {
    return ProtobufArrayList.emptyList();
  }
  
  private final void ensureUnknownFieldsInitialized() {
    if (this.unknownFields == UnknownFieldSetLite.getDefaultInstance())
      this.unknownFields = UnknownFieldSetLite.newInstance(); 
  }
  
  static <T extends GeneratedMessageLite<?, ?>> T getDefaultInstance(Class<T> paramClass) {
    GeneratedMessageLite<?, ?> generatedMessageLite1 = defaultInstanceMap.get(paramClass);
    GeneratedMessageLite<?, ?> generatedMessageLite2 = generatedMessageLite1;
    if (generatedMessageLite1 == null)
      try {
        Class.forName(paramClass.getName(), true, paramClass.getClassLoader());
        generatedMessageLite2 = defaultInstanceMap.get(paramClass);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new IllegalStateException("Class initialization cannot fail.", classNotFoundException);
      }  
    generatedMessageLite1 = generatedMessageLite2;
    if (generatedMessageLite2 == null) {
      generatedMessageLite1 = (GeneratedMessageLite<?, ?>)((GeneratedMessageLite)UnsafeUtil.<GeneratedMessageLite>allocateInstance((Class<GeneratedMessageLite>)classNotFoundException)).getDefaultInstanceForType();
      if (generatedMessageLite1 == null)
        throw new IllegalStateException(); 
      defaultInstanceMap.put(classNotFoundException, generatedMessageLite1);
    } 
    return (T)generatedMessageLite1;
  }
  
  static Method getMethodOrDie(Class paramClass, String paramString, Class... paramVarArgs) {
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
  
  static Object invokeOrDie(Method paramMethod, Object paramObject, Object... paramVarArgs) {
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
  
  protected static final <T extends GeneratedMessageLite<T, ?>> boolean isInitialized(T paramT, boolean paramBoolean) {
    byte b = ((Byte)paramT.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
    if (b == 1)
      return true; 
    if (b == 0)
      return false; 
    boolean bool = Protobuf.getInstance().<T>schemaFor(paramT).isInitialized(paramT);
    if (paramBoolean) {
      Object object;
      MethodToInvoke methodToInvoke = MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED;
      if (bool) {
        T t = paramT;
      } else {
        object = null;
      } 
      paramT.dynamicMethod(methodToInvoke, object);
    } 
    return bool;
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
  
  protected static <E> Internal.ProtobufList<E> mutableCopy(Internal.ProtobufList<E> paramProtobufList) {
    int i = paramProtobufList.size();
    if (i == 0) {
      i = 10;
    } else {
      i *= 2;
    } 
    return paramProtobufList.mutableCopyWithCapacity(i);
  }
  
  protected static Object newMessageInfo(MessageLite paramMessageLite, String paramString, Object[] paramArrayOfObject) {
    return new RawMessageInfo(paramMessageLite, paramString, paramArrayOfObject);
  }
  
  public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType paramContainingType, MessageLite paramMessageLite, Internal.EnumLiteMap<?> paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType, boolean paramBoolean, Class paramClass) {
    return new GeneratedExtension<>(paramContainingType, (Type)Collections.emptyList(), paramMessageLite, new ExtensionDescriptor(paramEnumLiteMap, paramInt, paramFieldType, true, paramBoolean), paramClass);
  }
  
  public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType paramContainingType, Type paramType, MessageLite paramMessageLite, Internal.EnumLiteMap<?> paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType, Class paramClass) {
    return new GeneratedExtension<>(paramContainingType, paramType, paramMessageLite, new ExtensionDescriptor(paramEnumLiteMap, paramInt, paramFieldType, false, false), paramClass);
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T paramT, InputStream paramInputStream) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialDelimitedFrom(paramT, paramInputStream, ExtensionRegistryLite.getEmptyRegistry()));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T paramT, InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialDelimitedFrom(paramT, paramInputStream, paramExtensionRegistryLite));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, ByteString paramByteString) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parseFrom(paramT, paramByteString, ExtensionRegistryLite.getEmptyRegistry()));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialFrom(paramT, paramByteString, paramExtensionRegistryLite));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, CodedInputStream paramCodedInputStream) throws InvalidProtocolBufferException {
    return parseFrom(paramT, paramCodedInputStream, ExtensionRegistryLite.getEmptyRegistry());
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialFrom(paramT, paramCodedInputStream, paramExtensionRegistryLite));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, InputStream paramInputStream) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialFrom(paramT, CodedInputStream.newInstance(paramInputStream), ExtensionRegistryLite.getEmptyRegistry()));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialFrom(paramT, CodedInputStream.newInstance(paramInputStream), paramExtensionRegistryLite));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return parseFrom(paramT, paramByteBuffer, ExtensionRegistryLite.getEmptyRegistry());
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parseFrom(paramT, CodedInputStream.newInstance(paramByteBuffer), paramExtensionRegistryLite));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialFrom(paramT, paramArrayOfbyte, 0, paramArrayOfbyte.length, ExtensionRegistryLite.getEmptyRegistry()));
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T paramT, byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialFrom(paramT, paramArrayOfbyte, 0, paramArrayOfbyte.length, paramExtensionRegistryLite));
  }
  
  private static <T extends GeneratedMessageLite<T, ?>> T parsePartialDelimitedFrom(T paramT, InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    try {
      int i = paramInputStream.read();
      if (i == -1)
        return null; 
      i = CodedInputStream.readRawVarint32(i, paramInputStream);
      CodedInputStream codedInputStream = CodedInputStream.newInstance(new AbstractMessageLite.Builder.LimitedInputStream(paramInputStream, i));
      paramT = parsePartialFrom(paramT, codedInputStream, paramExtensionRegistryLite);
      try {
        codedInputStream.checkLastTagWas(0);
        return paramT;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw invalidProtocolBufferException.setUnfinishedMessage(paramT);
      } 
    } catch (IOException iOException) {
      throw new InvalidProtocolBufferException(iOException.getMessage());
    } 
  }
  
  private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T paramT, ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    try {
      CodedInputStream codedInputStream = paramByteString.newCodedInput();
      paramT = parsePartialFrom(paramT, codedInputStream, paramExtensionRegistryLite);
      try {
        codedInputStream.checkLastTagWas(0);
        return paramT;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw invalidProtocolBufferException.setUnfinishedMessage(paramT);
      } 
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      throw invalidProtocolBufferException;
    } 
  }
  
  protected static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T paramT, CodedInputStream paramCodedInputStream) throws InvalidProtocolBufferException {
    return parsePartialFrom(paramT, paramCodedInputStream, ExtensionRegistryLite.getEmptyRegistry());
  }
  
  static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T paramT, CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite)paramT.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
    try {
      Schema<GeneratedMessageLite> schema = Protobuf.getInstance().schemaFor(generatedMessageLite);
      schema.mergeFrom(generatedMessageLite, CodedInputStreamReader.forCodedInput(paramCodedInputStream), paramExtensionRegistryLite);
      schema.makeImmutable(generatedMessageLite);
      return (T)generatedMessageLite;
    } catch (IOException iOException) {
      if (iOException.getCause() instanceof InvalidProtocolBufferException)
        throw (InvalidProtocolBufferException)iOException.getCause(); 
      throw (new InvalidProtocolBufferException(iOException.getMessage())).setUnfinishedMessage(generatedMessageLite);
    } catch (RuntimeException runtimeException) {
      if (runtimeException.getCause() instanceof InvalidProtocolBufferException)
        throw (InvalidProtocolBufferException)runtimeException.getCause(); 
      throw runtimeException;
    } 
  }
  
  static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite)paramT.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
    try {
      Schema<GeneratedMessageLite> schema = Protobuf.getInstance().schemaFor(generatedMessageLite);
      ArrayDecoders.Registers registers = new ArrayDecoders.Registers();
      this(paramExtensionRegistryLite);
      schema.mergeFrom(generatedMessageLite, paramArrayOfbyte, paramInt1, paramInt1 + paramInt2, registers);
      schema.makeImmutable(generatedMessageLite);
      if (generatedMessageLite.memoizedHashCode != 0) {
        RuntimeException runtimeException = new RuntimeException();
        this();
        throw runtimeException;
      } 
      return (T)generatedMessageLite;
    } catch (IOException iOException) {
      if (iOException.getCause() instanceof InvalidProtocolBufferException)
        throw (InvalidProtocolBufferException)iOException.getCause(); 
      throw (new InvalidProtocolBufferException(iOException.getMessage())).setUnfinishedMessage(generatedMessageLite);
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw InvalidProtocolBufferException.truncatedMessage().setUnfinishedMessage(generatedMessageLite);
    } 
  }
  
  private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T paramT, byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return checkMessageInitialized(parsePartialFrom(paramT, paramArrayOfbyte, 0, paramArrayOfbyte.length, paramExtensionRegistryLite));
  }
  
  protected static <T extends GeneratedMessageLite<?, ?>> void registerDefaultInstance(Class<T> paramClass, T paramT) {
    defaultInstanceMap.put(paramClass, (GeneratedMessageLite<?, ?>)paramT);
  }
  
  Object buildMessageInfo() throws Exception {
    return dynamicMethod(MethodToInvoke.BUILD_MESSAGE_INFO);
  }
  
  protected final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder() {
    return (BuilderType)dynamicMethod(MethodToInvoke.NEW_BUILDER);
  }
  
  protected final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder(MessageType paramMessageType) {
    return createBuilder().mergeFrom(paramMessageType);
  }
  
  protected Object dynamicMethod(MethodToInvoke paramMethodToInvoke) {
    return dynamicMethod(paramMethodToInvoke, (Object)null, (Object)null);
  }
  
  protected Object dynamicMethod(MethodToInvoke paramMethodToInvoke, Object paramObject) {
    return dynamicMethod(paramMethodToInvoke, paramObject, (Object)null);
  }
  
  protected abstract Object dynamicMethod(MethodToInvoke paramMethodToInvoke, Object paramObject1, Object paramObject2);
  
  public boolean equals(Object paramObject) {
    return (this == paramObject) ? true : (!getDefaultInstanceForType().getClass().isInstance(paramObject) ? false : Protobuf.getInstance().<GeneratedMessageLite>schemaFor(this).equals(this, (GeneratedMessageLite)paramObject));
  }
  
  public final MessageType getDefaultInstanceForType() {
    return (MessageType)dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
  }
  
  int getMemoizedSerializedSize() {
    return this.memoizedSerializedSize;
  }
  
  public final Parser<MessageType> getParserForType() {
    return (Parser<MessageType>)dynamicMethod(MethodToInvoke.GET_PARSER);
  }
  
  public int getSerializedSize() {
    if (this.memoizedSerializedSize == -1)
      this.memoizedSerializedSize = Protobuf.getInstance().<GeneratedMessageLite>schemaFor(this).getSerializedSize(this); 
    return this.memoizedSerializedSize;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    this.memoizedHashCode = Protobuf.getInstance().<GeneratedMessageLite>schemaFor(this).hashCode(this);
    return this.memoizedHashCode;
  }
  
  public final boolean isInitialized() {
    return isInitialized(this, Boolean.TRUE.booleanValue());
  }
  
  protected void makeImmutable() {
    Protobuf.getInstance().<GeneratedMessageLite>schemaFor(this).makeImmutable(this);
  }
  
  protected void mergeLengthDelimitedField(int paramInt, ByteString paramByteString) {
    ensureUnknownFieldsInitialized();
    this.unknownFields.mergeLengthDelimitedField(paramInt, paramByteString);
  }
  
  protected final void mergeUnknownFields(UnknownFieldSetLite paramUnknownFieldSetLite) {
    this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, paramUnknownFieldSetLite);
  }
  
  protected void mergeVarintField(int paramInt1, int paramInt2) {
    ensureUnknownFieldsInitialized();
    this.unknownFields.mergeVarintField(paramInt1, paramInt2);
  }
  
  public final BuilderType newBuilderForType() {
    return (BuilderType)dynamicMethod(MethodToInvoke.NEW_BUILDER);
  }
  
  protected boolean parseUnknownField(int paramInt, CodedInputStream paramCodedInputStream) throws IOException {
    if (WireFormat.getTagWireType(paramInt) == 4)
      return false; 
    ensureUnknownFieldsInitialized();
    return this.unknownFields.mergeFieldFrom(paramInt, paramCodedInputStream);
  }
  
  void setMemoizedSerializedSize(int paramInt) {
    this.memoizedSerializedSize = paramInt;
  }
  
  public final BuilderType toBuilder() {
    Builder builder = (Builder)dynamicMethod(MethodToInvoke.NEW_BUILDER);
    builder.mergeFrom(this);
    return (BuilderType)builder;
  }
  
  public String toString() {
    return MessageLiteToString.toString(this, super.toString());
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    Protobuf.getInstance().<GeneratedMessageLite>schemaFor(this).writeTo(this, CodedOutputStreamWriter.forCodedOutput(paramCodedOutputStream));
  }
  
  public static abstract class Builder<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite.Builder<MessageType, BuilderType> {
    private final MessageType defaultInstance;
    
    protected MessageType instance;
    
    protected boolean isBuilt;
    
    protected Builder(MessageType param1MessageType) {
      this.defaultInstance = param1MessageType;
      this.instance = (MessageType)param1MessageType.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
      this.isBuilt = false;
    }
    
    private void mergeFromInstance(MessageType param1MessageType1, MessageType param1MessageType2) {
      Protobuf.getInstance().<MessageType>schemaFor(param1MessageType1).mergeFrom(param1MessageType1, param1MessageType2);
    }
    
    public final MessageType build() {
      MessageType messageType = buildPartial();
      if (!messageType.isInitialized())
        throw newUninitializedMessageException(messageType); 
      return messageType;
    }
    
    public MessageType buildPartial() {
      if (this.isBuilt)
        return this.instance; 
      this.instance.makeImmutable();
      this.isBuilt = true;
      return this.instance;
    }
    
    public final BuilderType clear() {
      this.instance = (MessageType)this.instance.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
      return (BuilderType)this;
    }
    
    public BuilderType clone() {
      Object object = getDefaultInstanceForType().newBuilderForType();
      object.mergeFrom(buildPartial());
      return (BuilderType)object;
    }
    
    protected void copyOnWrite() {
      if (this.isBuilt) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite)this.instance.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
        mergeFromInstance((MessageType)generatedMessageLite, this.instance);
        this.instance = (MessageType)generatedMessageLite;
        this.isBuilt = false;
      } 
    }
    
    public MessageType getDefaultInstanceForType() {
      return this.defaultInstance;
    }
    
    protected BuilderType internalMergeFrom(MessageType param1MessageType) {
      return mergeFrom(param1MessageType);
    }
    
    public final boolean isInitialized() {
      return GeneratedMessageLite.isInitialized(this.instance, false);
    }
    
    public BuilderType mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      copyOnWrite();
      try {
        Protobuf.getInstance().<MessageType>schemaFor(this.instance).mergeFrom(this.instance, CodedInputStreamReader.forCodedInput(param1CodedInputStream), param1ExtensionRegistryLite);
        return (BuilderType)this;
      } catch (RuntimeException runtimeException) {
        if (runtimeException.getCause() instanceof IOException)
          throw (IOException)runtimeException.getCause(); 
        throw runtimeException;
      } 
    }
    
    public BuilderType mergeFrom(MessageType param1MessageType) {
      copyOnWrite();
      mergeFromInstance(this.instance, param1MessageType);
      return (BuilderType)this;
    }
    
    public BuilderType mergeFrom(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      return mergeFrom(param1ArrayOfbyte, param1Int1, param1Int2, ExtensionRegistryLite.getEmptyRegistry());
    }
    
    public BuilderType mergeFrom(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      copyOnWrite();
      try {
        Schema<MessageType> schema = Protobuf.getInstance().schemaFor(this.instance);
        MessageType messageType = this.instance;
        ArrayDecoders.Registers registers = new ArrayDecoders.Registers();
        this(param1ExtensionRegistryLite);
        schema.mergeFrom(messageType, param1ArrayOfbyte, param1Int1, param1Int1 + param1Int2, registers);
        return (BuilderType)this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw invalidProtocolBufferException;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw InvalidProtocolBufferException.truncatedMessage();
      } catch (IOException iOException) {
        throw new RuntimeException("Reading from byte array should not throw IOException.", iOException);
      } 
    }
  }
  
  protected static class DefaultInstanceBasedParser<T extends GeneratedMessageLite<T, ?>> extends AbstractParser<T> {
    private final T defaultInstance;
    
    public DefaultInstanceBasedParser(T param1T) {
      this.defaultInstance = param1T;
    }
    
    public T parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public T parsePartialFrom(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, param1ArrayOfbyte, param1Int1, param1Int2, param1ExtensionRegistryLite);
    }
  }
  
  public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
    protected ExtendableBuilder(MessageType param1MessageType) {
      super(param1MessageType);
    }
    
    private FieldSet<GeneratedMessageLite.ExtensionDescriptor> ensureExtensionsAreMutable() {
      FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet1 = ((GeneratedMessageLite.ExtendableMessage)this.instance).extensions;
      FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet2 = fieldSet1;
      if (fieldSet1.isImmutable()) {
        fieldSet2 = fieldSet1.clone();
        ((GeneratedMessageLite.ExtendableMessage)this.instance).extensions = fieldSet2;
      } 
      return fieldSet2;
    }
    
    private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension<MessageType, ?> param1GeneratedExtension) {
      if (param1GeneratedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings."); 
    }
    
    public final <Type> BuilderType addExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, Type param1Type) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageLite.checkIsLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((GeneratedMessageLite.GeneratedExtension<MessageType, ?>)param1ExtensionLite);
      copyOnWrite();
      ensureExtensionsAreMutable().addRepeatedField(((GeneratedMessageLite.GeneratedExtension)param1ExtensionLite).descriptor, param1ExtensionLite.singularToFieldSetType(param1Type));
      return (BuilderType)this;
    }
    
    public final MessageType buildPartial() {
      if (this.isBuilt)
        return this.instance; 
      ((GeneratedMessageLite.ExtendableMessage)this.instance).extensions.makeImmutable();
      return super.buildPartial();
    }
    
    public final <Type> BuilderType clearExtension(ExtensionLite<MessageType, ?> param1ExtensionLite) {
      param1ExtensionLite = GeneratedMessageLite.checkIsLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((GeneratedMessageLite.GeneratedExtension<MessageType, ?>)param1ExtensionLite);
      copyOnWrite();
      ensureExtensionsAreMutable().clearField(((GeneratedMessageLite.GeneratedExtension)param1ExtensionLite).descriptor);
      return (BuilderType)this;
    }
    
    protected void copyOnWrite() {
      if (!this.isBuilt)
        return; 
      super.copyOnWrite();
      ((GeneratedMessageLite.ExtendableMessage)this.instance).extensions = ((GeneratedMessageLite.ExtendableMessage)this.instance).extensions.clone();
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      return (Type)((GeneratedMessageLite.ExtendableMessage)this.instance).getExtension(param1ExtensionLite);
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int) {
      return (Type)((GeneratedMessageLite.ExtendableMessage)this.instance).getExtension(param1ExtensionLite, param1Int);
    }
    
    public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite) {
      return ((GeneratedMessageLite.ExtendableMessage)this.instance).getExtensionCount(param1ExtensionLite);
    }
    
    public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      return ((GeneratedMessageLite.ExtendableMessage)this.instance).hasExtension(param1ExtensionLite);
    }
    
    void internalSetExtensionSet(FieldSet<GeneratedMessageLite.ExtensionDescriptor> param1FieldSet) {
      copyOnWrite();
      ((GeneratedMessageLite.ExtendableMessage)this.instance).extensions = param1FieldSet;
    }
    
    public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int, Type param1Type) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageLite.checkIsLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((GeneratedMessageLite.GeneratedExtension<MessageType, ?>)param1ExtensionLite);
      copyOnWrite();
      ensureExtensionsAreMutable().setRepeatedField(((GeneratedMessageLite.GeneratedExtension)param1ExtensionLite).descriptor, param1Int, param1ExtensionLite.singularToFieldSetType(param1Type));
      return (BuilderType)this;
    }
    
    public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> param1ExtensionLite, Type param1Type) {
      param1ExtensionLite = GeneratedMessageLite.checkIsLite(param1ExtensionLite);
      verifyExtensionContainingType((GeneratedMessageLite.GeneratedExtension<MessageType, ?>)param1ExtensionLite);
      copyOnWrite();
      ensureExtensionsAreMutable().setField(((GeneratedMessageLite.GeneratedExtension)param1ExtensionLite).descriptor, param1ExtensionLite.toFieldSetType(param1Type));
      return (BuilderType)this;
    }
  }
  
  public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends GeneratedMessageLite<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
    protected FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions = FieldSet.emptySet();
    
    private void eagerlyMergeMessageSetExtension(CodedInputStream param1CodedInputStream, GeneratedMessageLite.GeneratedExtension<?, ?> param1GeneratedExtension, ExtensionRegistryLite param1ExtensionRegistryLite, int param1Int) throws IOException {
      parseExtension(param1CodedInputStream, param1ExtensionRegistryLite, param1GeneratedExtension, WireFormat.makeTag(param1Int, 2), param1Int);
    }
    
    private void mergeMessageSetExtensionFromBytes(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite, GeneratedMessageLite.GeneratedExtension<?, ?> param1GeneratedExtension) throws IOException {
      MessageLite.Builder builder;
      MessageLite messageLite2 = (MessageLite)this.extensions.getField(param1GeneratedExtension.descriptor);
      if (messageLite2 != null) {
        MessageLite.Builder builder1 = messageLite2.toBuilder();
      } else {
        messageLite2 = null;
      } 
      MessageLite messageLite3 = messageLite2;
      if (messageLite2 == null)
        builder = param1GeneratedExtension.getMessageDefaultInstance().newBuilderForType(); 
      builder.mergeFrom(param1ByteString, param1ExtensionRegistryLite);
      MessageLite messageLite1 = builder.build();
      ensureExtensionsAreMutable().setField(param1GeneratedExtension.descriptor, param1GeneratedExtension.singularToFieldSetType(messageLite1));
    }
    
    private <MessageType extends MessageLite> void mergeMessageSetExtensionFromCodedStream(MessageType param1MessageType, CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ByteString byteString;
      int i = 0;
      GeneratedMessageLite.GeneratedExtension<MessageType, ?> generatedExtension1 = null;
      GeneratedMessageLite.GeneratedExtension<MessageType, ?> generatedExtension2 = generatedExtension1;
      while (true) {
        int j = param1CodedInputStream.readTag();
        if (j == 0)
          break; 
        if (j == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
          j = param1CodedInputStream.readUInt32();
          i = j;
          if (j != 0) {
            generatedExtension1 = param1ExtensionRegistryLite.findLiteExtensionByNumber(param1MessageType, j);
            i = j;
          } 
          continue;
        } 
        if (j == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
          if (i != 0 && generatedExtension1 != null) {
            eagerlyMergeMessageSetExtension(param1CodedInputStream, generatedExtension1, param1ExtensionRegistryLite, i);
            generatedExtension2 = null;
            continue;
          } 
          byteString = param1CodedInputStream.readBytes();
          continue;
        } 
        if (!param1CodedInputStream.skipField(j))
          break; 
      } 
      param1CodedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
      if (byteString != null && i != 0)
        if (generatedExtension1 != null) {
          mergeMessageSetExtensionFromBytes(byteString, param1ExtensionRegistryLite, generatedExtension1);
        } else if (byteString != null) {
          mergeLengthDelimitedField(i, byteString);
        }  
    }
    
    private boolean parseExtension(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, GeneratedMessageLite.GeneratedExtension<?, ?> param1GeneratedExtension, int param1Int1, int param1Int2) throws IOException {
      // Byte code:
      //   0: iload #4
      //   2: invokestatic getTagWireType : (I)I
      //   5: istore #6
      //   7: aload_3
      //   8: ifnonnull -> 20
      //   11: iconst_0
      //   12: istore #7
      //   14: iconst_1
      //   15: istore #6
      //   17: goto -> 91
      //   20: iload #6
      //   22: aload_3
      //   23: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   26: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
      //   29: iconst_0
      //   30: invokestatic getWireFormatForFieldType : (Lcom/google/protobuf/WireFormat$FieldType;Z)I
      //   33: if_icmpne -> 46
      //   36: iconst_0
      //   37: istore #6
      //   39: iload #6
      //   41: istore #7
      //   43: goto -> 91
      //   46: aload_3
      //   47: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   50: getfield isRepeated : Z
      //   53: ifeq -> 11
      //   56: aload_3
      //   57: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   60: getfield type : Lcom/google/protobuf/WireFormat$FieldType;
      //   63: invokevirtual isPackable : ()Z
      //   66: ifeq -> 11
      //   69: iload #6
      //   71: aload_3
      //   72: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   75: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
      //   78: iconst_1
      //   79: invokestatic getWireFormatForFieldType : (Lcom/google/protobuf/WireFormat$FieldType;Z)I
      //   82: if_icmpne -> 11
      //   85: iconst_0
      //   86: istore #6
      //   88: iconst_1
      //   89: istore #7
      //   91: iload #6
      //   93: ifeq -> 104
      //   96: aload_0
      //   97: iload #4
      //   99: aload_1
      //   100: invokevirtual parseUnknownField : (ILcom/google/protobuf/CodedInputStream;)Z
      //   103: ireturn
      //   104: aload_0
      //   105: invokevirtual ensureExtensionsAreMutable : ()Lcom/google/protobuf/FieldSet;
      //   108: pop
      //   109: iload #7
      //   111: ifeq -> 234
      //   114: aload_1
      //   115: aload_1
      //   116: invokevirtual readRawVarint32 : ()I
      //   119: invokevirtual pushLimit : (I)I
      //   122: istore #4
      //   124: aload_3
      //   125: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   128: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
      //   131: getstatic com/google/protobuf/WireFormat$FieldType.ENUM : Lcom/google/protobuf/WireFormat$FieldType;
      //   134: if_acmpne -> 190
      //   137: aload_1
      //   138: invokevirtual getBytesUntilLimit : ()I
      //   141: ifle -> 225
      //   144: aload_1
      //   145: invokevirtual readEnum : ()I
      //   148: istore #5
      //   150: aload_3
      //   151: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   154: invokevirtual getEnumType : ()Lcom/google/protobuf/Internal$EnumLiteMap;
      //   157: iload #5
      //   159: invokeinterface findValueByNumber : (I)Lcom/google/protobuf/Internal$EnumLite;
      //   164: astore_2
      //   165: aload_2
      //   166: ifnonnull -> 171
      //   169: iconst_1
      //   170: ireturn
      //   171: aload_0
      //   172: getfield extensions : Lcom/google/protobuf/FieldSet;
      //   175: aload_3
      //   176: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   179: aload_3
      //   180: aload_2
      //   181: invokevirtual singularToFieldSetType : (Ljava/lang/Object;)Ljava/lang/Object;
      //   184: invokevirtual addRepeatedField : (Lcom/google/protobuf/FieldSet$FieldDescriptorLite;Ljava/lang/Object;)V
      //   187: goto -> 137
      //   190: aload_1
      //   191: invokevirtual getBytesUntilLimit : ()I
      //   194: ifle -> 225
      //   197: aload_1
      //   198: aload_3
      //   199: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   202: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
      //   205: iconst_0
      //   206: invokestatic readPrimitiveField : (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/WireFormat$FieldType;Z)Ljava/lang/Object;
      //   209: astore_2
      //   210: aload_0
      //   211: getfield extensions : Lcom/google/protobuf/FieldSet;
      //   214: aload_3
      //   215: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   218: aload_2
      //   219: invokevirtual addRepeatedField : (Lcom/google/protobuf/FieldSet$FieldDescriptorLite;Ljava/lang/Object;)V
      //   222: goto -> 190
      //   225: aload_1
      //   226: iload #4
      //   228: invokevirtual popLimit : (I)V
      //   231: goto -> 483
      //   234: getstatic com/google/protobuf/GeneratedMessageLite$1.$SwitchMap$com$google$protobuf$WireFormat$JavaType : [I
      //   237: aload_3
      //   238: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   241: invokevirtual getLiteJavaType : ()Lcom/google/protobuf/WireFormat$JavaType;
      //   244: invokevirtual ordinal : ()I
      //   247: iaload
      //   248: tableswitch default -> 272, 1 -> 325, 2 -> 288
      //   272: aload_1
      //   273: aload_3
      //   274: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   277: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
      //   280: iconst_0
      //   281: invokestatic readPrimitiveField : (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/WireFormat$FieldType;Z)Ljava/lang/Object;
      //   284: astore_1
      //   285: goto -> 438
      //   288: aload_1
      //   289: invokevirtual readEnum : ()I
      //   292: istore #4
      //   294: aload_3
      //   295: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   298: invokevirtual getEnumType : ()Lcom/google/protobuf/Internal$EnumLiteMap;
      //   301: iload #4
      //   303: invokeinterface findValueByNumber : (I)Lcom/google/protobuf/Internal$EnumLite;
      //   308: astore_2
      //   309: aload_2
      //   310: astore_1
      //   311: aload_2
      //   312: ifnonnull -> 438
      //   315: aload_0
      //   316: iload #5
      //   318: iload #4
      //   320: invokevirtual mergeVarintField : (II)V
      //   323: iconst_1
      //   324: ireturn
      //   325: aconst_null
      //   326: astore #8
      //   328: aload #8
      //   330: astore #9
      //   332: aload_3
      //   333: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   336: invokevirtual isRepeated : ()Z
      //   339: ifne -> 376
      //   342: aload_0
      //   343: getfield extensions : Lcom/google/protobuf/FieldSet;
      //   346: aload_3
      //   347: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   350: invokevirtual getField : (Lcom/google/protobuf/FieldSet$FieldDescriptorLite;)Ljava/lang/Object;
      //   353: checkcast com/google/protobuf/MessageLite
      //   356: astore #10
      //   358: aload #8
      //   360: astore #9
      //   362: aload #10
      //   364: ifnull -> 376
      //   367: aload #10
      //   369: invokeinterface toBuilder : ()Lcom/google/protobuf/MessageLite$Builder;
      //   374: astore #9
      //   376: aload #9
      //   378: astore #8
      //   380: aload #9
      //   382: ifnonnull -> 396
      //   385: aload_3
      //   386: invokevirtual getMessageDefaultInstance : ()Lcom/google/protobuf/MessageLite;
      //   389: invokeinterface newBuilderForType : ()Lcom/google/protobuf/MessageLite$Builder;
      //   394: astore #8
      //   396: aload_3
      //   397: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   400: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
      //   403: getstatic com/google/protobuf/WireFormat$FieldType.GROUP : Lcom/google/protobuf/WireFormat$FieldType;
      //   406: if_acmpne -> 423
      //   409: aload_1
      //   410: aload_3
      //   411: invokevirtual getNumber : ()I
      //   414: aload #8
      //   416: aload_2
      //   417: invokevirtual readGroup : (ILcom/google/protobuf/MessageLite$Builder;Lcom/google/protobuf/ExtensionRegistryLite;)V
      //   420: goto -> 430
      //   423: aload_1
      //   424: aload #8
      //   426: aload_2
      //   427: invokevirtual readMessage : (Lcom/google/protobuf/MessageLite$Builder;Lcom/google/protobuf/ExtensionRegistryLite;)V
      //   430: aload #8
      //   432: invokeinterface build : ()Lcom/google/protobuf/MessageLite;
      //   437: astore_1
      //   438: aload_3
      //   439: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   442: invokevirtual isRepeated : ()Z
      //   445: ifeq -> 467
      //   448: aload_0
      //   449: getfield extensions : Lcom/google/protobuf/FieldSet;
      //   452: aload_3
      //   453: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   456: aload_3
      //   457: aload_1
      //   458: invokevirtual singularToFieldSetType : (Ljava/lang/Object;)Ljava/lang/Object;
      //   461: invokevirtual addRepeatedField : (Lcom/google/protobuf/FieldSet$FieldDescriptorLite;Ljava/lang/Object;)V
      //   464: goto -> 483
      //   467: aload_0
      //   468: getfield extensions : Lcom/google/protobuf/FieldSet;
      //   471: aload_3
      //   472: getfield descriptor : Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;
      //   475: aload_3
      //   476: aload_1
      //   477: invokevirtual singularToFieldSetType : (Ljava/lang/Object;)Ljava/lang/Object;
      //   480: invokevirtual setField : (Lcom/google/protobuf/FieldSet$FieldDescriptorLite;Ljava/lang/Object;)V
      //   483: iconst_1
      //   484: ireturn
    }
    
    private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension<MessageType, ?> param1GeneratedExtension) {
      if (param1GeneratedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings."); 
    }
    
    FieldSet<GeneratedMessageLite.ExtensionDescriptor> ensureExtensionsAreMutable() {
      if (this.extensions.isImmutable())
        this.extensions = this.extensions.clone(); 
      return this.extensions;
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
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      GeneratedMessageLite.GeneratedExtension<MessageType, ?> generatedExtension = GeneratedMessageLite.checkIsLite(param1ExtensionLite);
      verifyExtensionContainingType(generatedExtension);
      Object object = this.extensions.getField(generatedExtension.descriptor);
      return (Type)((object == null) ? (Object)generatedExtension.defaultValue : generatedExtension.fromFieldSetType(object));
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageLite.checkIsLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((GeneratedMessageLite.GeneratedExtension<MessageType, ?>)param1ExtensionLite);
      return (Type)param1ExtensionLite.singularFromFieldSetType(this.extensions.getRepeatedField(((GeneratedMessageLite.GeneratedExtension)param1ExtensionLite).descriptor, param1Int));
    }
    
    public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessageLite.checkIsLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((GeneratedMessageLite.GeneratedExtension<MessageType, ?>)param1ExtensionLite);
      return this.extensions.getRepeatedFieldCount(((GeneratedMessageLite.GeneratedExtension)param1ExtensionLite).descriptor);
    }
    
    public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite) {
      param1ExtensionLite = GeneratedMessageLite.checkIsLite(param1ExtensionLite);
      verifyExtensionContainingType((GeneratedMessageLite.GeneratedExtension<MessageType, ?>)param1ExtensionLite);
      return this.extensions.hasField(((GeneratedMessageLite.GeneratedExtension)param1ExtensionLite).descriptor);
    }
    
    protected final void mergeExtensionFields(MessageType param1MessageType) {
      if (this.extensions.isImmutable())
        this.extensions = this.extensions.clone(); 
      this.extensions.mergeFrom(((ExtendableMessage)param1MessageType).extensions);
    }
    
    protected ExtensionWriter newExtensionWriter() {
      return new ExtensionWriter(false);
    }
    
    protected ExtensionWriter newMessageSetExtensionWriter() {
      return new ExtensionWriter(true);
    }
    
    protected <MessageType extends MessageLite> boolean parseUnknownField(MessageType param1MessageType, CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, int param1Int) throws IOException {
      int i = WireFormat.getTagFieldNumber(param1Int);
      return parseExtension(param1CodedInputStream, param1ExtensionRegistryLite, param1ExtensionRegistryLite.findLiteExtensionByNumber(param1MessageType, i), param1Int, i);
    }
    
    protected <MessageType extends MessageLite> boolean parseUnknownFieldAsMessageSet(MessageType param1MessageType, CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, int param1Int) throws IOException {
      if (param1Int == WireFormat.MESSAGE_SET_ITEM_TAG) {
        mergeMessageSetExtensionFromCodedStream(param1MessageType, param1CodedInputStream, param1ExtensionRegistryLite);
        return true;
      } 
      return (WireFormat.getTagWireType(param1Int) == 2) ? parseUnknownField(param1MessageType, param1CodedInputStream, param1ExtensionRegistryLite, param1Int) : param1CodedInputStream.skipField(param1Int);
    }
    
    protected class ExtensionWriter {
      private final Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> iter = GeneratedMessageLite.ExtendableMessage.this.extensions.iterator();
      
      private final boolean messageSetWireFormat;
      
      private Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next;
      
      private ExtensionWriter(boolean param2Boolean) {
        if (this.iter.hasNext())
          this.next = this.iter.next(); 
        this.messageSetWireFormat = param2Boolean;
      }
      
      public void writeUntil(int param2Int, CodedOutputStream param2CodedOutputStream) throws IOException {
        while (this.next != null && ((GeneratedMessageLite.ExtensionDescriptor)this.next.getKey()).getNumber() < param2Int) {
          GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = this.next.getKey();
          if (this.messageSetWireFormat && extensionDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extensionDescriptor.isRepeated()) {
            param2CodedOutputStream.writeMessageSetExtension(extensionDescriptor.getNumber(), (MessageLite)this.next.getValue());
          } else {
            FieldSet.writeField(extensionDescriptor, this.next.getValue(), param2CodedOutputStream);
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
    private final Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> iter = GeneratedMessageLite.ExtendableMessage.this.extensions.iterator();
    
    private final boolean messageSetWireFormat;
    
    private Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next;
    
    private ExtensionWriter(boolean param1Boolean) {
      if (this.iter.hasNext())
        this.next = this.iter.next(); 
      this.messageSetWireFormat = param1Boolean;
    }
    
    public void writeUntil(int param1Int, CodedOutputStream param1CodedOutputStream) throws IOException {
      while (this.next != null && ((GeneratedMessageLite.ExtensionDescriptor)this.next.getKey()).getNumber() < param1Int) {
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = this.next.getKey();
        if (this.messageSetWireFormat && extensionDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extensionDescriptor.isRepeated()) {
          param1CodedOutputStream.writeMessageSetExtension(extensionDescriptor.getNumber(), (MessageLite)this.next.getValue());
        } else {
          FieldSet.writeField(extensionDescriptor, this.next.getValue(), param1CodedOutputStream);
        } 
        if (this.iter.hasNext()) {
          this.next = this.iter.next();
          continue;
        } 
        this.next = null;
      } 
    }
  }
  
  public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends MessageLiteOrBuilder {
    <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
    
    <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int);
    
    <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite);
    
    <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
  }
  
  static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
    final Internal.EnumLiteMap<?> enumTypeMap;
    
    final boolean isPacked;
    
    final boolean isRepeated;
    
    final int number;
    
    final WireFormat.FieldType type;
    
    ExtensionDescriptor(Internal.EnumLiteMap<?> param1EnumLiteMap, int param1Int, WireFormat.FieldType param1FieldType, boolean param1Boolean1, boolean param1Boolean2) {
      this.enumTypeMap = param1EnumLiteMap;
      this.number = param1Int;
      this.type = param1FieldType;
      this.isRepeated = param1Boolean1;
      this.isPacked = param1Boolean2;
    }
    
    public int compareTo(ExtensionDescriptor param1ExtensionDescriptor) {
      return this.number - param1ExtensionDescriptor.number;
    }
    
    public Internal.EnumLiteMap<?> getEnumType() {
      return this.enumTypeMap;
    }
    
    public WireFormat.JavaType getLiteJavaType() {
      return this.type.getJavaType();
    }
    
    public WireFormat.FieldType getLiteType() {
      return this.type;
    }
    
    public int getNumber() {
      return this.number;
    }
    
    public MessageLite.Builder internalMergeFrom(MessageLite.Builder param1Builder, MessageLite param1MessageLite) {
      return ((GeneratedMessageLite.Builder<GeneratedMessageLite, MessageLite.Builder>)param1Builder).mergeFrom((GeneratedMessageLite)param1MessageLite);
    }
    
    public boolean isPacked() {
      return this.isPacked;
    }
    
    public boolean isRepeated() {
      return this.isRepeated;
    }
  }
  
  public static class GeneratedExtension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {
    final ContainingType containingTypeDefaultInstance;
    
    final Type defaultValue;
    
    final GeneratedMessageLite.ExtensionDescriptor descriptor;
    
    final MessageLite messageDefaultInstance;
    
    GeneratedExtension(ContainingType param1ContainingType, Type param1Type, MessageLite param1MessageLite, GeneratedMessageLite.ExtensionDescriptor param1ExtensionDescriptor, Class param1Class) {
      if (param1ContainingType == null)
        throw new IllegalArgumentException("Null containingTypeDefaultInstance"); 
      if (param1ExtensionDescriptor.getLiteType() == WireFormat.FieldType.MESSAGE && param1MessageLite == null)
        throw new IllegalArgumentException("Null messageDefaultInstance"); 
      this.containingTypeDefaultInstance = param1ContainingType;
      this.defaultValue = param1Type;
      this.messageDefaultInstance = param1MessageLite;
      this.descriptor = param1ExtensionDescriptor;
    }
    
    Object fromFieldSetType(Object param1Object) {
      if (this.descriptor.isRepeated()) {
        if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
          ArrayList<Object> arrayList = new ArrayList();
          param1Object = ((List)param1Object).iterator();
          while (param1Object.hasNext())
            arrayList.add(singularFromFieldSetType(param1Object.next())); 
          return arrayList;
        } 
        return param1Object;
      } 
      return singularFromFieldSetType(param1Object);
    }
    
    public ContainingType getContainingTypeDefaultInstance() {
      return this.containingTypeDefaultInstance;
    }
    
    public Type getDefaultValue() {
      return this.defaultValue;
    }
    
    public WireFormat.FieldType getLiteType() {
      return this.descriptor.getLiteType();
    }
    
    public MessageLite getMessageDefaultInstance() {
      return this.messageDefaultInstance;
    }
    
    public int getNumber() {
      return this.descriptor.getNumber();
    }
    
    public boolean isRepeated() {
      return this.descriptor.isRepeated;
    }
    
    Object singularFromFieldSetType(Object param1Object) {
      return (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) ? this.descriptor.enumTypeMap.findValueByNumber(((Integer)param1Object).intValue()) : param1Object;
    }
    
    Object singularToFieldSetType(Object param1Object) {
      return (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) ? Integer.valueOf(((Internal.EnumLite)param1Object).getNumber()) : param1Object;
    }
    
    Object toFieldSetType(Object param1Object) {
      if (this.descriptor.isRepeated()) {
        if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
          ArrayList<Object> arrayList = new ArrayList();
          param1Object = ((List)param1Object).iterator();
          while (param1Object.hasNext())
            arrayList.add(singularToFieldSetType(param1Object.next())); 
          return arrayList;
        } 
        return param1Object;
      } 
      return singularToFieldSetType(param1Object);
    }
  }
  
  public enum MethodToInvoke {
    BUILD_MESSAGE_INFO, GET_DEFAULT_INSTANCE, GET_MEMOIZED_IS_INITIALIZED, GET_PARSER, NEW_BUILDER, NEW_MUTABLE_INSTANCE, SET_MEMOIZED_IS_INITIALIZED;
    
    static {
      NEW_BUILDER = new MethodToInvoke("NEW_BUILDER", 4);
      GET_DEFAULT_INSTANCE = new MethodToInvoke("GET_DEFAULT_INSTANCE", 5);
      GET_PARSER = new MethodToInvoke("GET_PARSER", 6);
      $VALUES = new MethodToInvoke[] { GET_MEMOIZED_IS_INITIALIZED, SET_MEMOIZED_IS_INITIALIZED, BUILD_MESSAGE_INFO, NEW_MUTABLE_INSTANCE, NEW_BUILDER, GET_DEFAULT_INSTANCE, GET_PARSER };
    }
  }
  
  protected static final class SerializedForm implements Serializable {
    private static final long serialVersionUID = 0L;
    
    private final byte[] asBytes;
    
    private final Class<?> messageClass;
    
    private final String messageClassName;
    
    SerializedForm(MessageLite param1MessageLite) {
      this.messageClass = param1MessageLite.getClass();
      this.messageClassName = this.messageClass.getName();
      this.asBytes = param1MessageLite.toByteArray();
    }
    
    public static SerializedForm of(MessageLite param1MessageLite) {
      return new SerializedForm(param1MessageLite);
    }
    
    @Deprecated
    private Object readResolveFallback() throws ObjectStreamException {
      try {
        Field field = resolveMessageClass().getDeclaredField("defaultInstance");
        field.setAccessible(true);
        return ((MessageLite)field.get((Object)null)).newBuilderForType().mergeFrom(this.asBytes).buildPartial();
      } catch (ClassNotFoundException classNotFoundException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to find proto buffer class: ");
        stringBuilder.append(this.messageClassName);
        throw new RuntimeException(stringBuilder.toString(), classNotFoundException);
      } catch (NoSuchFieldException noSuchFieldException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to find defaultInstance in ");
        stringBuilder.append(this.messageClassName);
        throw new RuntimeException(stringBuilder.toString(), noSuchFieldException);
      } catch (SecurityException securityException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to call defaultInstance in ");
        stringBuilder.append(this.messageClassName);
        throw new RuntimeException(stringBuilder.toString(), securityException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new RuntimeException("Unable to call parsePartialFrom", illegalAccessException);
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw new RuntimeException("Unable to understand proto buffer", invalidProtocolBufferException);
      } 
    }
    
    private Class<?> resolveMessageClass() throws ClassNotFoundException {
      Class<?> clazz;
      if (this.messageClass != null) {
        clazz = this.messageClass;
      } else {
        clazz = Class.forName(this.messageClassName);
      } 
      return clazz;
    }
    
    protected Object readResolve() throws ObjectStreamException {
      try {
        Field field = resolveMessageClass().getDeclaredField("DEFAULT_INSTANCE");
        field.setAccessible(true);
        return ((MessageLite)field.get((Object)null)).newBuilderForType().mergeFrom(this.asBytes).buildPartial();
      } catch (ClassNotFoundException classNotFoundException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to find proto buffer class: ");
        stringBuilder.append(this.messageClassName);
        throw new RuntimeException(stringBuilder.toString(), classNotFoundException);
      } catch (NoSuchFieldException noSuchFieldException) {
        return readResolveFallback();
      } catch (SecurityException securityException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to call DEFAULT_INSTANCE in ");
        stringBuilder.append(this.messageClassName);
        throw new RuntimeException(stringBuilder.toString(), securityException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new RuntimeException("Unable to call parsePartialFrom", illegalAccessException);
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw new RuntimeException("Unable to understand proto buffer", invalidProtocolBufferException);
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\GeneratedMessageLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */