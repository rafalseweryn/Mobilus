package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class GeneratedMessage extends AbstractMessage implements Serializable {
  protected static boolean alwaysUseFieldBuilders = false;
  
  private static final long serialVersionUID = 1L;
  
  protected UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
  
  protected GeneratedMessage() {}
  
  protected GeneratedMessage(Builder<?> paramBuilder) {}
  
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
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(Class paramClass, Message paramMessage) {
    return new GeneratedExtension<>(null, paramClass, paramMessage, Extension.ExtensionType.IMMUTABLE);
  }
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(final Class singularType, Message paramMessage, final String descriptorOuterClass, final String extensionName) {
    return new GeneratedExtension<>(new CachedDescriptorRetriever() {
          protected Descriptors.FieldDescriptor loadDescriptor() {
            try {
              return ((Descriptors.FileDescriptor)singularType.getClassLoader().loadClass(descriptorOuterClass).getField("descriptor").get(null)).findExtensionByName(extensionName);
            } catch (Exception exception) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Cannot load descriptors: ");
              stringBuilder.append(descriptorOuterClass);
              stringBuilder.append(" is not a valid descriptor class name");
              throw new RuntimeException(stringBuilder.toString(), exception);
            } 
          }
        }singularType, paramMessage, Extension.ExtensionType.MUTABLE);
  }
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(final Message scope, final int descriptorIndex, Class paramClass, Message paramMessage2) {
    return new GeneratedExtension<>(new CachedDescriptorRetriever() {
          public Descriptors.FieldDescriptor loadDescriptor() {
            return scope.getDescriptorForType().getExtensions().get(descriptorIndex);
          }
        },  paramClass, paramMessage2, Extension.ExtensionType.IMMUTABLE);
  }
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(final Message scope, final String name, Class paramClass, Message paramMessage2) {
    return new GeneratedExtension<>(new CachedDescriptorRetriever() {
          protected Descriptors.FieldDescriptor loadDescriptor() {
            return scope.getDescriptorForType().findFieldByName(name);
          }
        },  paramClass, paramMessage2, Extension.ExtensionType.MUTABLE);
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
  
  public Parser<? extends GeneratedMessage> getParserForType() {
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
  
  protected Message.Builder newBuilderForType(final AbstractMessage.BuilderParent parent) {
    return newBuilderForType(new BuilderParent() {
          public void markDirty() {
            parent.markDirty();
          }
        });
  }
  
  protected abstract Message.Builder newBuilderForType(BuilderParent paramBuilderParent);
  
  protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt) throws IOException {
    return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
  }
  
  protected Object writeReplace() throws ObjectStreamException {
    return new GeneratedMessageLite.SerializedForm(this);
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    MessageReflection.writeMessageTo(this, getAllFieldsRaw(), paramCodedOutputStream, false);
  }
  
  public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends AbstractMessage.Builder<BuilderType> {
    private GeneratedMessage.BuilderParent builderParent;
    
    private boolean isClean;
    
    private BuilderParentImpl meAsParent;
    
    private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
    
    protected Builder() {
      this((GeneratedMessage.BuilderParent)null);
    }
    
    protected Builder(GeneratedMessage.BuilderParent param1BuilderParent) {
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
    
    protected GeneratedMessage.BuilderParent getParentForChildren() {
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
    
    protected abstract GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable();
    
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
      this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(param1UnknownFieldSet).build();
      onChanged();
      return (BuilderType)this;
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
    
    protected boolean parseUnknownField(CodedInputStream param1CodedInputStream, UnknownFieldSet.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite, int param1Int) throws IOException {
      return param1Builder.mergeFieldFrom(param1Int, param1CodedInputStream);
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
      this.unknownFields = param1UnknownFieldSet;
      onChanged();
      return (BuilderType)this;
    }
    
    private class BuilderParentImpl implements GeneratedMessage.BuilderParent {
      private BuilderParentImpl() {}
      
      public void markDirty() {
        GeneratedMessage.Builder.this.onChanged();
      }
    }
  }
  
  private class BuilderParentImpl implements BuilderParent {
    private BuilderParentImpl() {}
    
    public void markDirty() {
      GeneratedMessage.Builder.this.onChanged();
    }
  }
  
  protected static interface BuilderParent extends AbstractMessage.BuilderParent {}
  
  private static abstract class CachedDescriptorRetriever implements ExtensionDescriptorRetriever {
    private volatile Descriptors.FieldDescriptor descriptor;
    
    private CachedDescriptorRetriever() {}
    
    public Descriptors.FieldDescriptor getDescriptor() {
      // Byte code:
      //   0: aload_0
      //   1: getfield descriptor : Lcom/google/protobuf/Descriptors$FieldDescriptor;
      //   4: ifnonnull -> 34
      //   7: aload_0
      //   8: monitorenter
      //   9: aload_0
      //   10: getfield descriptor : Lcom/google/protobuf/Descriptors$FieldDescriptor;
      //   13: ifnonnull -> 24
      //   16: aload_0
      //   17: aload_0
      //   18: invokevirtual loadDescriptor : ()Lcom/google/protobuf/Descriptors$FieldDescriptor;
      //   21: putfield descriptor : Lcom/google/protobuf/Descriptors$FieldDescriptor;
      //   24: aload_0
      //   25: monitorexit
      //   26: goto -> 34
      //   29: astore_1
      //   30: aload_0
      //   31: monitorexit
      //   32: aload_1
      //   33: athrow
      //   34: aload_0
      //   35: getfield descriptor : Lcom/google/protobuf/Descriptors$FieldDescriptor;
      //   38: areturn
      // Exception table:
      //   from	to	target	type
      //   9	24	29	finally
      //   24	26	29	finally
      //   30	32	29	finally
    }
    
    protected abstract Descriptors.FieldDescriptor loadDescriptor();
  }
  
  public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
    private FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.emptySet();
    
    protected ExtendableBuilder() {}
    
    protected ExtendableBuilder(GeneratedMessage.BuilderParent param1BuilderParent) {
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
      Extension<MessageType, ?> extension = GeneratedMessage.checkNotLite((ExtensionLite)param1ExtensionLite);
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
      param1ExtensionLite = GeneratedMessage.checkNotLite((ExtensionLite)param1ExtensionLite);
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
    
    public BuilderType clone() {
      return super.clone();
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
      param1ExtensionLite = GeneratedMessage.checkNotLite(param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      Descriptors.FieldDescriptor fieldDescriptor = param1ExtensionLite.getDescriptor();
      Object object = this.extensions.getField(fieldDescriptor);
      return (Type)((object == null) ? (fieldDescriptor.isRepeated() ? Collections.emptyList() : ((fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) ? param1ExtensionLite.getMessageDefaultInstance() : param1ExtensionLite.fromReflectionType(fieldDescriptor.getDefaultValue()))) : param1ExtensionLite.fromReflectionType(object));
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessage.checkNotLite((ExtensionLite)param1ExtensionLite);
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
      param1ExtensionLite = (ExtensionLite)GeneratedMessage.checkNotLite((ExtensionLite)param1ExtensionLite);
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
      param1ExtensionLite = GeneratedMessage.checkNotLite(param1ExtensionLite);
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
    
    protected final void mergeExtensionFields(GeneratedMessage.ExtendableMessage param1ExtendableMessage) {
      ensureExtensionsIsMutable();
      this.extensions.mergeFrom(param1ExtendableMessage.extensions);
      onChanged();
    }
    
    protected boolean parseUnknownField(CodedInputStream param1CodedInputStream, UnknownFieldSet.Builder param1Builder, ExtensionRegistryLite param1ExtensionRegistryLite, int param1Int) throws IOException {
      return MessageReflection.mergeFieldFrom(param1CodedInputStream, param1Builder, param1ExtensionRegistryLite, getDescriptorForType(), new MessageReflection.BuilderAdapter(this), param1Int);
    }
    
    public final <Type> BuilderType setExtension(Extension<MessageType, List<Type>> param1Extension, int param1Int, Type param1Type) {
      return setExtension(param1Extension, param1Int, param1Type);
    }
    
    public final <Type> BuilderType setExtension(Extension<MessageType, Type> param1Extension, Type param1Type) {
      return setExtension(param1Extension, param1Type);
    }
    
    public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int, Type param1Type) {
      param1ExtensionLite = (ExtensionLite)GeneratedMessage.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType((Extension<MessageType, ?>)param1ExtensionLite);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor fieldDescriptor = param1ExtensionLite.getDescriptor();
      this.extensions.setRepeatedField(fieldDescriptor, param1Int, param1ExtensionLite.singularToReflectionType(param1Type));
      onChanged();
      return (BuilderType)this;
    }
    
    public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> param1ExtensionLite, Type param1Type) {
      Extension<MessageType, ?> extension = GeneratedMessage.checkNotLite(param1ExtensionLite);
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
  
  public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessage implements ExtendableMessageOrBuilder<MessageType> {
    private static final long serialVersionUID = 1L;
    
    private final FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.newFieldSet();
    
    protected ExtendableMessage() {}
    
    protected ExtendableMessage(GeneratedMessage.ExtendableBuilder<MessageType, ?> param1ExtendableBuilder) {
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
      Extension<MessageType, ?> extension = GeneratedMessage.checkNotLite(param1ExtensionLite);
      verifyExtensionContainingType(extension);
      Descriptors.FieldDescriptor fieldDescriptor = extension.getDescriptor();
      Object object = this.extensions.getField(fieldDescriptor);
      return (Type)((object == null) ? (fieldDescriptor.isRepeated() ? Collections.emptyList() : ((fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) ? extension.getMessageDefaultInstance() : extension.fromReflectionType(fieldDescriptor.getDefaultValue()))) : extension.fromReflectionType(object));
    }
    
    public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int) {
      Extension<MessageType, ?> extension = GeneratedMessage.checkNotLite((ExtensionLite)param1ExtensionLite);
      verifyExtensionContainingType(extension);
      Descriptors.FieldDescriptor fieldDescriptor = extension.getDescriptor();
      return (Type)extension.singularFromReflectionType(this.extensions.getRepeatedField(fieldDescriptor, param1Int));
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
      param1ExtensionLite = (ExtensionLite)GeneratedMessage.checkNotLite((ExtensionLite)param1ExtensionLite);
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
      param1ExtensionLite = GeneratedMessage.checkNotLite(param1ExtensionLite);
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
      return MessageReflection.mergeFieldFrom(param1CodedInputStream, param1Builder, param1ExtensionRegistryLite, getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), param1Int);
    }
    
    protected class ExtensionWriter {
      private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = GeneratedMessage.ExtendableMessage.this.extensions.iterator();
      
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
    private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = GeneratedMessage.ExtendableMessage.this.extensions.iterator();
    
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
    
    public FieldAccessorTable(Descriptors.Descriptor param1Descriptor, String[] param1ArrayOfString, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1) {
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
    
    private boolean isMapFieldEnabled(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return true;
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
    
    public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1) {
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
      //   23: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
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
      //   40: if_icmpge -> 402
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
      //   92: ifeq -> 246
      //   95: aload #7
      //   97: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   100: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.MESSAGE : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   103: if_acmpne -> 179
      //   106: aload #7
      //   108: invokevirtual isMapField : ()Z
      //   111: ifeq -> 151
      //   114: aload_0
      //   115: aload #7
      //   117: invokespecial isMapFieldEnabled : (Lcom/google/protobuf/Descriptors$FieldDescriptor;)Z
      //   120: ifeq -> 151
      //   123: aload_0
      //   124: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   127: iload #5
      //   129: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$MapFieldAccessor
      //   132: dup
      //   133: aload #7
      //   135: aload_0
      //   136: getfield camelCaseNames : [Ljava/lang/String;
      //   139: iload #5
      //   141: aaload
      //   142: aload_1
      //   143: aload_2
      //   144: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   147: aastore
      //   148: goto -> 396
      //   151: aload_0
      //   152: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   155: iload #5
      //   157: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$RepeatedMessageFieldAccessor
      //   160: dup
      //   161: aload #7
      //   163: aload_0
      //   164: getfield camelCaseNames : [Ljava/lang/String;
      //   167: iload #5
      //   169: aaload
      //   170: aload_1
      //   171: aload_2
      //   172: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   175: aastore
      //   176: goto -> 396
      //   179: aload #7
      //   181: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   184: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.ENUM : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   187: if_acmpne -> 218
      //   190: aload_0
      //   191: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   194: iload #5
      //   196: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$RepeatedEnumFieldAccessor
      //   199: dup
      //   200: aload #7
      //   202: aload_0
      //   203: getfield camelCaseNames : [Ljava/lang/String;
      //   206: iload #5
      //   208: aaload
      //   209: aload_1
      //   210: aload_2
      //   211: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   214: aastore
      //   215: goto -> 396
      //   218: aload_0
      //   219: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   222: iload #5
      //   224: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$RepeatedFieldAccessor
      //   227: dup
      //   228: aload #7
      //   230: aload_0
      //   231: getfield camelCaseNames : [Ljava/lang/String;
      //   234: iload #5
      //   236: aaload
      //   237: aload_1
      //   238: aload_2
      //   239: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   242: aastore
      //   243: goto -> 396
      //   246: aload #7
      //   248: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   251: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.MESSAGE : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   254: if_acmpne -> 287
      //   257: aload_0
      //   258: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   261: iload #5
      //   263: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$SingularMessageFieldAccessor
      //   266: dup
      //   267: aload #7
      //   269: aload_0
      //   270: getfield camelCaseNames : [Ljava/lang/String;
      //   273: iload #5
      //   275: aaload
      //   276: aload_1
      //   277: aload_2
      //   278: aload #6
      //   280: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   283: aastore
      //   284: goto -> 396
      //   287: aload #7
      //   289: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   292: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.ENUM : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   295: if_acmpne -> 328
      //   298: aload_0
      //   299: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   302: iload #5
      //   304: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$SingularEnumFieldAccessor
      //   307: dup
      //   308: aload #7
      //   310: aload_0
      //   311: getfield camelCaseNames : [Ljava/lang/String;
      //   314: iload #5
      //   316: aaload
      //   317: aload_1
      //   318: aload_2
      //   319: aload #6
      //   321: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   324: aastore
      //   325: goto -> 396
      //   328: aload #7
      //   330: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   333: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.STRING : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
      //   336: if_acmpne -> 369
      //   339: aload_0
      //   340: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   343: iload #5
      //   345: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$SingularStringFieldAccessor
      //   348: dup
      //   349: aload #7
      //   351: aload_0
      //   352: getfield camelCaseNames : [Ljava/lang/String;
      //   355: iload #5
      //   357: aaload
      //   358: aload_1
      //   359: aload_2
      //   360: aload #6
      //   362: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   365: aastore
      //   366: goto -> 396
      //   369: aload_0
      //   370: getfield fields : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$FieldAccessor;
      //   373: iload #5
      //   375: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$SingularFieldAccessor
      //   378: dup
      //   379: aload #7
      //   381: aload_0
      //   382: getfield camelCaseNames : [Ljava/lang/String;
      //   385: iload #5
      //   387: aaload
      //   388: aload_1
      //   389: aload_2
      //   390: aload #6
      //   392: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
      //   395: aastore
      //   396: iinc #5, 1
      //   399: goto -> 34
      //   402: aload_0
      //   403: getfield oneofs : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$OneofAccessor;
      //   406: arraylength
      //   407: istore #8
      //   409: iload #4
      //   411: istore #5
      //   413: iload #5
      //   415: iload #8
      //   417: if_icmpge -> 455
      //   420: aload_0
      //   421: getfield oneofs : [Lcom/google/protobuf/GeneratedMessage$FieldAccessorTable$OneofAccessor;
      //   424: iload #5
      //   426: new com/google/protobuf/GeneratedMessage$FieldAccessorTable$OneofAccessor
      //   429: dup
      //   430: aload_0
      //   431: getfield descriptor : Lcom/google/protobuf/Descriptors$Descriptor;
      //   434: aload_0
      //   435: getfield camelCaseNames : [Ljava/lang/String;
      //   438: iload #5
      //   440: iload_3
      //   441: iadd
      //   442: aaload
      //   443: aload_1
      //   444: aload_2
      //   445: invokespecial <init> : (Lcom/google/protobuf/Descriptors$Descriptor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V
      //   448: aastore
      //   449: iinc #5, 1
      //   452: goto -> 413
      //   455: aload_0
      //   456: iconst_1
      //   457: putfield initialized : Z
      //   460: aload_0
      //   461: aconst_null
      //   462: putfield camelCaseNames : [Ljava/lang/String;
      //   465: aload_0
      //   466: monitorexit
      //   467: aload_0
      //   468: areturn
      //   469: astore_1
      //   470: aload_0
      //   471: monitorexit
      //   472: aload_1
      //   473: athrow
      // Exception table:
      //   from	to	target	type
      //   11	20	469	finally
      //   22	28	469	finally
      //   43	62	469	finally
      //   62	87	469	finally
      //   87	148	469	finally
      //   151	176	469	finally
      //   179	215	469	finally
      //   218	243	469	finally
      //   246	284	469	finally
      //   287	325	469	finally
      //   328	366	469	finally
      //   369	396	469	finally
      //   402	409	469	finally
      //   420	449	469	finally
      //   455	467	469	finally
      //   470	472	469	finally
    }
    
    private static interface FieldAccessor {
      void addRepeated(GeneratedMessage.Builder param2Builder, Object param2Object);
      
      void clear(GeneratedMessage.Builder param2Builder);
      
      Object get(GeneratedMessage.Builder param2Builder);
      
      Object get(GeneratedMessage param2GeneratedMessage);
      
      Message.Builder getBuilder(GeneratedMessage.Builder param2Builder);
      
      Object getRaw(GeneratedMessage.Builder param2Builder);
      
      Object getRaw(GeneratedMessage param2GeneratedMessage);
      
      Object getRepeated(GeneratedMessage.Builder param2Builder, int param2Int);
      
      Object getRepeated(GeneratedMessage param2GeneratedMessage, int param2Int);
      
      Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param2Builder, int param2Int);
      
      int getRepeatedCount(GeneratedMessage.Builder param2Builder);
      
      int getRepeatedCount(GeneratedMessage param2GeneratedMessage);
      
      Object getRepeatedRaw(GeneratedMessage.Builder param2Builder, int param2Int);
      
      Object getRepeatedRaw(GeneratedMessage param2GeneratedMessage, int param2Int);
      
      boolean has(GeneratedMessage.Builder param2Builder);
      
      boolean has(GeneratedMessage param2GeneratedMessage);
      
      Message.Builder newBuilder();
      
      void set(GeneratedMessage.Builder param2Builder, Object param2Object);
      
      void setRepeated(GeneratedMessage.Builder param2Builder, int param2Int, Object param2Object);
    }
    
    private static class MapFieldAccessor implements FieldAccessor {
      private final Descriptors.FieldDescriptor field;
      
      private final Message mapEntryMessageDefaultInstance;
      
      MapFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1) {
        this.field = param2FieldDescriptor;
        this.mapEntryMessageDefaultInstance = getMapField((GeneratedMessage)GeneratedMessage.invokeOrDie(GeneratedMessage.getMethodOrDie(param2Class, "getDefaultInstance", new Class[0]), null, new Object[0])).getMapEntryMessageDefaultInstance();
      }
      
      private MapField<?, ?> getMapField(GeneratedMessage.Builder param2Builder) {
        return param2Builder.internalGetMapField(this.field.getNumber());
      }
      
      private MapField<?, ?> getMapField(GeneratedMessage param2GeneratedMessage) {
        return param2GeneratedMessage.internalGetMapField(this.field.getNumber());
      }
      
      private MapField<?, ?> getMutableMapField(GeneratedMessage.Builder param2Builder) {
        return param2Builder.internalGetMutableMapField(this.field.getNumber());
      }
      
      public void addRepeated(GeneratedMessage.Builder param2Builder, Object param2Object) {
        getMutableMapField(param2Builder).getMutableList().add((Message)param2Object);
      }
      
      public void clear(GeneratedMessage.Builder param2Builder) {
        getMutableMapField(param2Builder).getMutableList().clear();
      }
      
      public Object get(GeneratedMessage.Builder param2Builder) {
        ArrayList<Object> arrayList = new ArrayList();
        for (byte b = 0; b < getRepeatedCount(param2Builder); b++)
          arrayList.add(getRepeated(param2Builder, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Object get(GeneratedMessage param2GeneratedMessage) {
        ArrayList<Object> arrayList = new ArrayList();
        for (byte b = 0; b < getRepeatedCount(param2GeneratedMessage); b++)
          arrayList.add(getRepeated(param2GeneratedMessage, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Message.Builder getBuilder(GeneratedMessage.Builder param2Builder) {
        throw new UnsupportedOperationException("Nested builder not supported for map fields.");
      }
      
      public Object getRaw(GeneratedMessage.Builder param2Builder) {
        return get(param2Builder);
      }
      
      public Object getRaw(GeneratedMessage param2GeneratedMessage) {
        return get(param2GeneratedMessage);
      }
      
      public Object getRepeated(GeneratedMessage.Builder param2Builder, int param2Int) {
        return getMapField(param2Builder).getList().get(param2Int);
      }
      
      public Object getRepeated(GeneratedMessage param2GeneratedMessage, int param2Int) {
        return getMapField(param2GeneratedMessage).getList().get(param2Int);
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("Nested builder not supported for map fields.");
      }
      
      public int getRepeatedCount(GeneratedMessage.Builder param2Builder) {
        return getMapField(param2Builder).getList().size();
      }
      
      public int getRepeatedCount(GeneratedMessage param2GeneratedMessage) {
        return getMapField(param2GeneratedMessage).getList().size();
      }
      
      public Object getRepeatedRaw(GeneratedMessage.Builder param2Builder, int param2Int) {
        return getRepeated(param2Builder, param2Int);
      }
      
      public Object getRepeatedRaw(GeneratedMessage param2GeneratedMessage, int param2Int) {
        return getRepeated(param2GeneratedMessage, param2Int);
      }
      
      public boolean has(GeneratedMessage.Builder param2Builder) {
        throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
      }
      
      public boolean has(GeneratedMessage param2GeneratedMessage) {
        throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
      }
      
      public Message.Builder newBuilder() {
        return this.mapEntryMessageDefaultInstance.newBuilderForType();
      }
      
      public void set(GeneratedMessage.Builder param2Builder, Object param2Object) {
        clear(param2Builder);
        param2Object = ((List)param2Object).iterator();
        while (param2Object.hasNext())
          addRepeated(param2Builder, param2Object.next()); 
      }
      
      public void setRepeated(GeneratedMessage.Builder param2Builder, int param2Int, Object param2Object) {
        getMutableMapField(param2Builder).getMutableList().set(param2Int, (Message)param2Object);
      }
    }
    
    private static class OneofAccessor {
      private final Method caseMethod;
      
      private final Method caseMethodBuilder;
      
      private final Method clearMethod;
      
      private final Descriptors.Descriptor descriptor;
      
      OneofAccessor(Descriptors.Descriptor param2Descriptor, String param2String, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1) {
        this.descriptor = param2Descriptor;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Case");
        this.caseMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Case");
        this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("clear");
        stringBuilder.append(param2String);
        this.clearMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
      }
      
      public void clear(GeneratedMessage.Builder param2Builder) {
        GeneratedMessage.invokeOrDie(this.clearMethod, param2Builder, new Object[0]);
      }
      
      public Descriptors.FieldDescriptor get(GeneratedMessage.Builder param2Builder) {
        int i = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, param2Builder, new Object[0])).getNumber();
        return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
      }
      
      public Descriptors.FieldDescriptor get(GeneratedMessage param2GeneratedMessage) {
        int i = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, param2GeneratedMessage, new Object[0])).getNumber();
        return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
      }
      
      public boolean has(GeneratedMessage.Builder param2Builder) {
        return !(((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, param2Builder, new Object[0])).getNumber() == 0);
      }
      
      public boolean has(GeneratedMessage param2GeneratedMessage) {
        return !(((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, param2GeneratedMessage, new Object[0])).getNumber() == 0);
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
      
      RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1) {
        super(param2FieldDescriptor, param2String, param2Class, param2Class1);
        this.enumDescriptor = param2FieldDescriptor.getEnumType();
        this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
        this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
        this.supportUnknownEnumValue = param2FieldDescriptor.getFile().supportsUnknownEnumValue();
        if (this.supportUnknownEnumValue) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.getRepeatedValueMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[] { int.class });
          stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.getRepeatedValueMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
          stringBuilder = new StringBuilder();
          stringBuilder.append("set");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.setRepeatedValueMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class, int.class });
          stringBuilder = new StringBuilder();
          stringBuilder.append("add");
          stringBuilder.append(param2String);
          stringBuilder.append("Value");
          this.addRepeatedValueMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
        } 
      }
      
      public void addRepeated(GeneratedMessage.Builder param2Builder, Object param2Object) {
        if (this.supportUnknownEnumValue) {
          GeneratedMessage.invokeOrDie(this.addRepeatedValueMethod, param2Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param2Object).getNumber()) });
          return;
        } 
        super.addRepeated(param2Builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { param2Object }));
      }
      
      public Object get(GeneratedMessage.Builder param2Builder) {
        ArrayList<Object> arrayList = new ArrayList();
        int i = getRepeatedCount(param2Builder);
        for (byte b = 0; b < i; b++)
          arrayList.add(getRepeated(param2Builder, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Object get(GeneratedMessage param2GeneratedMessage) {
        ArrayList<Object> arrayList = new ArrayList();
        int i = getRepeatedCount(param2GeneratedMessage);
        for (byte b = 0; b < i; b++)
          arrayList.add(getRepeated(param2GeneratedMessage, b)); 
        return Collections.unmodifiableList(arrayList);
      }
      
      public Object getRepeated(GeneratedMessage.Builder param2Builder, int param2Int) {
        if (this.supportUnknownEnumValue) {
          param2Int = ((Integer)GeneratedMessage.invokeOrDie(this.getRepeatedValueMethodBuilder, param2Builder, new Object[] { Integer.valueOf(param2Int) })).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param2Int);
        } 
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param2Builder, param2Int), new Object[0]);
      }
      
      public Object getRepeated(GeneratedMessage param2GeneratedMessage, int param2Int) {
        if (this.supportUnknownEnumValue) {
          param2Int = ((Integer)GeneratedMessage.invokeOrDie(this.getRepeatedValueMethod, param2GeneratedMessage, new Object[] { Integer.valueOf(param2Int) })).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param2Int);
        } 
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param2GeneratedMessage, param2Int), new Object[0]);
      }
      
      public void setRepeated(GeneratedMessage.Builder param2Builder, int param2Int, Object param2Object) {
        if (this.supportUnknownEnumValue) {
          GeneratedMessage.invokeOrDie(this.setRepeatedValueMethod, param2Builder, new Object[] { Integer.valueOf(param2Int), Integer.valueOf(((Descriptors.EnumValueDescriptor)param2Object).getNumber()) });
          return;
        } 
        super.setRepeated(param2Builder, param2Int, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { param2Object }));
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
      
      RepeatedFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("List");
        this.getMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("List");
        this.getMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[] { int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
        this.type = this.getRepeatedMethod.getReturnType();
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param2String);
        this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class, this.type });
        stringBuilder = new StringBuilder();
        stringBuilder.append("add");
        stringBuilder.append(param2String);
        this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { this.type });
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Count");
        this.getCountMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Count");
        this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("clear");
        stringBuilder.append(param2String);
        this.clearMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
      }
      
      public void addRepeated(GeneratedMessage.Builder param2Builder, Object param2Object) {
        GeneratedMessage.invokeOrDie(this.addRepeatedMethod, param2Builder, new Object[] { param2Object });
      }
      
      public void clear(GeneratedMessage.Builder param2Builder) {
        GeneratedMessage.invokeOrDie(this.clearMethod, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessage.Builder param2Builder) {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessage param2GeneratedMessage) {
        return GeneratedMessage.invokeOrDie(this.getMethod, param2GeneratedMessage, new Object[0]);
      }
      
      public Message.Builder getBuilder(GeneratedMessage.Builder param2Builder) {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }
      
      public Object getRaw(GeneratedMessage.Builder param2Builder) {
        return get(param2Builder);
      }
      
      public Object getRaw(GeneratedMessage param2GeneratedMessage) {
        return get(param2GeneratedMessage);
      }
      
      public Object getRepeated(GeneratedMessage.Builder param2Builder, int param2Int) {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, param2Builder, new Object[] { Integer.valueOf(param2Int) });
      }
      
      public Object getRepeated(GeneratedMessage param2GeneratedMessage, int param2Int) {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, param2GeneratedMessage, new Object[] { Integer.valueOf(param2Int) });
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
      }
      
      public int getRepeatedCount(GeneratedMessage.Builder param2Builder) {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, param2Builder, new Object[0])).intValue();
      }
      
      public int getRepeatedCount(GeneratedMessage param2GeneratedMessage) {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, param2GeneratedMessage, new Object[0])).intValue();
      }
      
      public Object getRepeatedRaw(GeneratedMessage.Builder param2Builder, int param2Int) {
        return getRepeated(param2Builder, param2Int);
      }
      
      public Object getRepeatedRaw(GeneratedMessage param2GeneratedMessage, int param2Int) {
        return getRepeated(param2GeneratedMessage, param2Int);
      }
      
      public boolean has(GeneratedMessage.Builder param2Builder) {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }
      
      public boolean has(GeneratedMessage param2GeneratedMessage) {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }
      
      public Message.Builder newBuilder() {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
      
      public void set(GeneratedMessage.Builder param2Builder, Object param2Object) {
        clear(param2Builder);
        param2Object = ((List)param2Object).iterator();
        while (param2Object.hasNext())
          addRepeated(param2Builder, param2Object.next()); 
      }
      
      public void setRepeated(GeneratedMessage.Builder param2Builder, int param2Int, Object param2Object) {
        GeneratedMessage.invokeOrDie(this.setRepeatedMethod, param2Builder, new Object[] { Integer.valueOf(param2Int), param2Object });
      }
    }
    
    private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
      private final Method getBuilderMethodBuilder;
      
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
      
      RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1) {
        super(param2FieldDescriptor, param2String, param2Class, param2Class1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String);
        stringBuilder.append("Builder");
        this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
      }
      
      private Object coerceType(Object param2Object) {
        return this.type.isInstance(param2Object) ? param2Object : ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param2Object).build();
      }
      
      public void addRepeated(GeneratedMessage.Builder param2Builder, Object param2Object) {
        super.addRepeated(param2Builder, coerceType(param2Object));
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param2Builder, int param2Int) {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, param2Builder, new Object[] { Integer.valueOf(param2Int) });
      }
      
      public Message.Builder newBuilder() {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
      
      public void setRepeated(GeneratedMessage.Builder param2Builder, int param2Int, Object param2Object) {
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
      
      SingularEnumFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1, String param2String2) {
        super(param2FieldDescriptor, param2String1, param2Class, param2Class1, param2String2);
        this.enumDescriptor = param2FieldDescriptor.getEnumType();
        this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
        this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
        this.supportUnknownEnumValue = param2FieldDescriptor.getFile().supportsUnknownEnumValue();
        if (this.supportUnknownEnumValue) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String1);
          stringBuilder.append("Value");
          this.getValueMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
          stringBuilder = new StringBuilder();
          stringBuilder.append("get");
          stringBuilder.append(param2String1);
          stringBuilder.append("Value");
          this.getValueMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
          stringBuilder = new StringBuilder();
          stringBuilder.append("set");
          stringBuilder.append(param2String1);
          stringBuilder.append("Value");
          this.setValueMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { int.class });
        } 
      }
      
      public Object get(GeneratedMessage.Builder param2Builder) {
        if (this.supportUnknownEnumValue) {
          int i = ((Integer)GeneratedMessage.invokeOrDie(this.getValueMethodBuilder, param2Builder, new Object[0])).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
        } 
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(param2Builder), new Object[0]);
      }
      
      public Object get(GeneratedMessage param2GeneratedMessage) {
        if (this.supportUnknownEnumValue) {
          int i = ((Integer)GeneratedMessage.invokeOrDie(this.getValueMethod, param2GeneratedMessage, new Object[0])).intValue();
          return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
        } 
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(param2GeneratedMessage), new Object[0]);
      }
      
      public void set(GeneratedMessage.Builder param2Builder, Object param2Object) {
        if (this.supportUnknownEnumValue) {
          GeneratedMessage.invokeOrDie(this.setValueMethod, param2Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param2Object).getNumber()) });
          return;
        } 
        super.set(param2Builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { param2Object }));
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
      
      SingularFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1, String param2String2) {
        Method method;
        this.field = param2FieldDescriptor;
        if (param2FieldDescriptor.getContainingOneof() != null) {
          bool = true;
        } else {
          bool = false;
        } 
        this.isOneofField = bool;
        if (GeneratedMessage.FieldAccessorTable.supportFieldPresence(param2FieldDescriptor.getFile()) || (!this.isOneofField && param2FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)) {
          bool = true;
        } else {
          bool = false;
        } 
        this.hasHasMethod = bool;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get");
        stringBuilder1.append(param2String1);
        this.getMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder1.toString(), new Class[0]);
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get");
        stringBuilder1.append(param2String1);
        this.getMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        this.type = this.getMethod.getReturnType();
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("set");
        stringBuilder1.append(param2String1);
        this.setMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[] { this.type });
        boolean bool = this.hasHasMethod;
        StringBuilder stringBuilder2 = null;
        if (bool) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("has");
          stringBuilder1.append(param2String1);
          method = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder1.toString(), new Class[0]);
        } else {
          stringBuilder1 = null;
        } 
        this.hasMethod = (Method)stringBuilder1;
        if (this.hasHasMethod) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("has");
          stringBuilder1.append(param2String1);
          method = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        } else {
          stringBuilder1 = null;
        } 
        this.hasMethodBuilder = (Method)stringBuilder1;
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("clear");
        stringBuilder1.append(param2String1);
        this.clearMethod = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        if (this.isOneofField) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("get");
          stringBuilder1.append(param2String2);
          stringBuilder1.append("Case");
          method = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder1.toString(), new Class[0]);
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
          method = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder1.toString(), new Class[0]);
        } 
        this.caseMethodBuilder = method;
      }
      
      private int getOneofFieldNumber(GeneratedMessage.Builder param2Builder) {
        return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, param2Builder, new Object[0])).getNumber();
      }
      
      private int getOneofFieldNumber(GeneratedMessage param2GeneratedMessage) {
        return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, param2GeneratedMessage, new Object[0])).getNumber();
      }
      
      public void addRepeated(GeneratedMessage.Builder param2Builder, Object param2Object) {
        throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
      }
      
      public void clear(GeneratedMessage.Builder param2Builder) {
        GeneratedMessage.invokeOrDie(this.clearMethod, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessage.Builder param2Builder) {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Object get(GeneratedMessage param2GeneratedMessage) {
        return GeneratedMessage.invokeOrDie(this.getMethod, param2GeneratedMessage, new Object[0]);
      }
      
      public Message.Builder getBuilder(GeneratedMessage.Builder param2Builder) {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }
      
      public Object getRaw(GeneratedMessage.Builder param2Builder) {
        return get(param2Builder);
      }
      
      public Object getRaw(GeneratedMessage param2GeneratedMessage) {
        return get(param2GeneratedMessage);
      }
      
      public Object getRepeated(GeneratedMessage.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }
      
      public Object getRepeated(GeneratedMessage param2GeneratedMessage, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }
      
      public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
      }
      
      public int getRepeatedCount(GeneratedMessage.Builder param2Builder) {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }
      
      public int getRepeatedCount(GeneratedMessage param2GeneratedMessage) {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }
      
      public Object getRepeatedRaw(GeneratedMessage.Builder param2Builder, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
      }
      
      public Object getRepeatedRaw(GeneratedMessage param2GeneratedMessage, int param2Int) {
        throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
      }
      
      public boolean has(GeneratedMessage.Builder param2Builder) {
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
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, param2Builder, new Object[0])).booleanValue();
      }
      
      public boolean has(GeneratedMessage param2GeneratedMessage) {
        boolean bool = this.hasHasMethod;
        boolean bool1 = false;
        if (!bool) {
          if (this.isOneofField) {
            if (getOneofFieldNumber(param2GeneratedMessage) == this.field.getNumber())
              bool1 = true; 
            return bool1;
          } 
          return get(param2GeneratedMessage).equals(this.field.getDefaultValue()) ^ true;
        } 
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, param2GeneratedMessage, new Object[0])).booleanValue();
      }
      
      public Message.Builder newBuilder() {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
      
      public void set(GeneratedMessage.Builder param2Builder, Object param2Object) {
        GeneratedMessage.invokeOrDie(this.setMethod, param2Builder, new Object[] { param2Object });
      }
      
      public void setRepeated(GeneratedMessage.Builder param2Builder, int param2Int, Object param2Object) {
        throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
      }
    }
    
    private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
      private final Method getBuilderMethodBuilder;
      
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
      
      SingularMessageFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1, String param2String2) {
        super(param2FieldDescriptor, param2String1, param2Class, param2Class1, param2String2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String1);
        stringBuilder.append("Builder");
        this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
      }
      
      private Object coerceType(Object param2Object) {
        return this.type.isInstance(param2Object) ? param2Object : ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param2Object).buildPartial();
      }
      
      public Message.Builder getBuilder(GeneratedMessage.Builder param2Builder) {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Message.Builder newBuilder() {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
      
      public void set(GeneratedMessage.Builder param2Builder, Object param2Object) {
        super.set(param2Builder, coerceType(param2Object));
      }
    }
    
    private static final class SingularStringFieldAccessor extends SingularFieldAccessor {
      private final Method getBytesMethod;
      
      private final Method getBytesMethodBuilder;
      
      private final Method setBytesMethodBuilder;
      
      SingularStringFieldAccessor(Descriptors.FieldDescriptor param2FieldDescriptor, String param2String1, Class<? extends GeneratedMessage> param2Class, Class<? extends GeneratedMessage.Builder> param2Class1, String param2String2) {
        super(param2FieldDescriptor, param2String1, param2Class, param2Class1, param2String2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String1);
        stringBuilder.append("Bytes");
        this.getBytesMethod = GeneratedMessage.getMethodOrDie(param2Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param2String1);
        stringBuilder.append("Bytes");
        this.getBytesMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param2String1);
        stringBuilder.append("Bytes");
        this.setBytesMethodBuilder = GeneratedMessage.getMethodOrDie(param2Class1, stringBuilder.toString(), new Class[] { ByteString.class });
      }
      
      public Object getRaw(GeneratedMessage.Builder param2Builder) {
        return GeneratedMessage.invokeOrDie(this.getBytesMethodBuilder, param2Builder, new Object[0]);
      }
      
      public Object getRaw(GeneratedMessage param2GeneratedMessage) {
        return GeneratedMessage.invokeOrDie(this.getBytesMethod, param2GeneratedMessage, new Object[0]);
      }
      
      public void set(GeneratedMessage.Builder param2Builder, Object param2Object) {
        if (param2Object instanceof ByteString) {
          GeneratedMessage.invokeOrDie(this.setBytesMethodBuilder, param2Builder, new Object[] { param2Object });
        } else {
          super.set(param2Builder, param2Object);
        } 
      }
    }
  }
  
  private static interface FieldAccessor {
    void addRepeated(GeneratedMessage.Builder param1Builder, Object param1Object);
    
    void clear(GeneratedMessage.Builder param1Builder);
    
    Object get(GeneratedMessage.Builder param1Builder);
    
    Object get(GeneratedMessage param1GeneratedMessage);
    
    Message.Builder getBuilder(GeneratedMessage.Builder param1Builder);
    
    Object getRaw(GeneratedMessage.Builder param1Builder);
    
    Object getRaw(GeneratedMessage param1GeneratedMessage);
    
    Object getRepeated(GeneratedMessage.Builder param1Builder, int param1Int);
    
    Object getRepeated(GeneratedMessage param1GeneratedMessage, int param1Int);
    
    Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param1Builder, int param1Int);
    
    int getRepeatedCount(GeneratedMessage.Builder param1Builder);
    
    int getRepeatedCount(GeneratedMessage param1GeneratedMessage);
    
    Object getRepeatedRaw(GeneratedMessage.Builder param1Builder, int param1Int);
    
    Object getRepeatedRaw(GeneratedMessage param1GeneratedMessage, int param1Int);
    
    boolean has(GeneratedMessage.Builder param1Builder);
    
    boolean has(GeneratedMessage param1GeneratedMessage);
    
    Message.Builder newBuilder();
    
    void set(GeneratedMessage.Builder param1Builder, Object param1Object);
    
    void setRepeated(GeneratedMessage.Builder param1Builder, int param1Int, Object param1Object);
  }
  
  private static class MapFieldAccessor implements FieldAccessorTable.FieldAccessor {
    private final Descriptors.FieldDescriptor field;
    
    private final Message mapEntryMessageDefaultInstance;
    
    MapFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1) {
      this.field = param1FieldDescriptor;
      this.mapEntryMessageDefaultInstance = getMapField((GeneratedMessage)GeneratedMessage.invokeOrDie(GeneratedMessage.getMethodOrDie(param1Class, "getDefaultInstance", new Class[0]), null, new Object[0])).getMapEntryMessageDefaultInstance();
    }
    
    private MapField<?, ?> getMapField(GeneratedMessage.Builder param1Builder) {
      return param1Builder.internalGetMapField(this.field.getNumber());
    }
    
    private MapField<?, ?> getMapField(GeneratedMessage param1GeneratedMessage) {
      return param1GeneratedMessage.internalGetMapField(this.field.getNumber());
    }
    
    private MapField<?, ?> getMutableMapField(GeneratedMessage.Builder param1Builder) {
      return param1Builder.internalGetMutableMapField(this.field.getNumber());
    }
    
    public void addRepeated(GeneratedMessage.Builder param1Builder, Object param1Object) {
      getMutableMapField(param1Builder).getMutableList().add((Message)param1Object);
    }
    
    public void clear(GeneratedMessage.Builder param1Builder) {
      getMutableMapField(param1Builder).getMutableList().clear();
    }
    
    public Object get(GeneratedMessage.Builder param1Builder) {
      ArrayList<Object> arrayList = new ArrayList();
      for (byte b = 0; b < getRepeatedCount(param1Builder); b++)
        arrayList.add(getRepeated(param1Builder, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Object get(GeneratedMessage param1GeneratedMessage) {
      ArrayList<Object> arrayList = new ArrayList();
      for (byte b = 0; b < getRepeatedCount(param1GeneratedMessage); b++)
        arrayList.add(getRepeated(param1GeneratedMessage, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Message.Builder getBuilder(GeneratedMessage.Builder param1Builder) {
      throw new UnsupportedOperationException("Nested builder not supported for map fields.");
    }
    
    public Object getRaw(GeneratedMessage.Builder param1Builder) {
      return get(param1Builder);
    }
    
    public Object getRaw(GeneratedMessage param1GeneratedMessage) {
      return get(param1GeneratedMessage);
    }
    
    public Object getRepeated(GeneratedMessage.Builder param1Builder, int param1Int) {
      return getMapField(param1Builder).getList().get(param1Int);
    }
    
    public Object getRepeated(GeneratedMessage param1GeneratedMessage, int param1Int) {
      return getMapField(param1GeneratedMessage).getList().get(param1Int);
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("Nested builder not supported for map fields.");
    }
    
    public int getRepeatedCount(GeneratedMessage.Builder param1Builder) {
      return getMapField(param1Builder).getList().size();
    }
    
    public int getRepeatedCount(GeneratedMessage param1GeneratedMessage) {
      return getMapField(param1GeneratedMessage).getList().size();
    }
    
    public Object getRepeatedRaw(GeneratedMessage.Builder param1Builder, int param1Int) {
      return getRepeated(param1Builder, param1Int);
    }
    
    public Object getRepeatedRaw(GeneratedMessage param1GeneratedMessage, int param1Int) {
      return getRepeated(param1GeneratedMessage, param1Int);
    }
    
    public boolean has(GeneratedMessage.Builder param1Builder) {
      throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
    }
    
    public boolean has(GeneratedMessage param1GeneratedMessage) {
      throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
    }
    
    public Message.Builder newBuilder() {
      return this.mapEntryMessageDefaultInstance.newBuilderForType();
    }
    
    public void set(GeneratedMessage.Builder param1Builder, Object param1Object) {
      clear(param1Builder);
      param1Object = ((List)param1Object).iterator();
      while (param1Object.hasNext())
        addRepeated(param1Builder, param1Object.next()); 
    }
    
    public void setRepeated(GeneratedMessage.Builder param1Builder, int param1Int, Object param1Object) {
      getMutableMapField(param1Builder).getMutableList().set(param1Int, (Message)param1Object);
    }
  }
  
  private static class OneofAccessor {
    private final Method caseMethod;
    
    private final Method caseMethodBuilder;
    
    private final Method clearMethod;
    
    private final Descriptors.Descriptor descriptor;
    
    OneofAccessor(Descriptors.Descriptor param1Descriptor, String param1String, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1) {
      this.descriptor = param1Descriptor;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Case");
      this.caseMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Case");
      this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("clear");
      stringBuilder.append(param1String);
      this.clearMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
    }
    
    public void clear(GeneratedMessage.Builder param1Builder) {
      GeneratedMessage.invokeOrDie(this.clearMethod, param1Builder, new Object[0]);
    }
    
    public Descriptors.FieldDescriptor get(GeneratedMessage.Builder param1Builder) {
      int i = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, param1Builder, new Object[0])).getNumber();
      return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
    }
    
    public Descriptors.FieldDescriptor get(GeneratedMessage param1GeneratedMessage) {
      int i = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, param1GeneratedMessage, new Object[0])).getNumber();
      return (i > 0) ? this.descriptor.findFieldByNumber(i) : null;
    }
    
    public boolean has(GeneratedMessage.Builder param1Builder) {
      return !(((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, param1Builder, new Object[0])).getNumber() == 0);
    }
    
    public boolean has(GeneratedMessage param1GeneratedMessage) {
      return !(((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, param1GeneratedMessage, new Object[0])).getNumber() == 0);
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
    
    RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1) {
      super(param1FieldDescriptor, param1String, param1Class, param1Class1);
      this.enumDescriptor = param1FieldDescriptor.getEnumType();
      this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      this.supportUnknownEnumValue = param1FieldDescriptor.getFile().supportsUnknownEnumValue();
      if (this.supportUnknownEnumValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.getRepeatedValueMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[] { int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.getRepeatedValueMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.setRepeatedValueMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class, int.class });
        stringBuilder = new StringBuilder();
        stringBuilder.append("add");
        stringBuilder.append(param1String);
        stringBuilder.append("Value");
        this.addRepeatedValueMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
      } 
    }
    
    public void addRepeated(GeneratedMessage.Builder param1Builder, Object param1Object) {
      if (this.supportUnknownEnumValue) {
        GeneratedMessage.invokeOrDie(this.addRepeatedValueMethod, param1Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param1Object).getNumber()) });
        return;
      } 
      super.addRepeated(param1Builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { param1Object }));
    }
    
    public Object get(GeneratedMessage.Builder param1Builder) {
      ArrayList<Object> arrayList = new ArrayList();
      int i = getRepeatedCount(param1Builder);
      for (byte b = 0; b < i; b++)
        arrayList.add(getRepeated(param1Builder, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Object get(GeneratedMessage param1GeneratedMessage) {
      ArrayList<Object> arrayList = new ArrayList();
      int i = getRepeatedCount(param1GeneratedMessage);
      for (byte b = 0; b < i; b++)
        arrayList.add(getRepeated(param1GeneratedMessage, b)); 
      return Collections.unmodifiableList(arrayList);
    }
    
    public Object getRepeated(GeneratedMessage.Builder param1Builder, int param1Int) {
      if (this.supportUnknownEnumValue) {
        param1Int = ((Integer)GeneratedMessage.invokeOrDie(this.getRepeatedValueMethodBuilder, param1Builder, new Object[] { Integer.valueOf(param1Int) })).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param1Int);
      } 
      return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param1Builder, param1Int), new Object[0]);
    }
    
    public Object getRepeated(GeneratedMessage param1GeneratedMessage, int param1Int) {
      if (this.supportUnknownEnumValue) {
        param1Int = ((Integer)GeneratedMessage.invokeOrDie(this.getRepeatedValueMethod, param1GeneratedMessage, new Object[] { Integer.valueOf(param1Int) })).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(param1Int);
      } 
      return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(param1GeneratedMessage, param1Int), new Object[0]);
    }
    
    public void setRepeated(GeneratedMessage.Builder param1Builder, int param1Int, Object param1Object) {
      if (this.supportUnknownEnumValue) {
        GeneratedMessage.invokeOrDie(this.setRepeatedValueMethod, param1Builder, new Object[] { Integer.valueOf(param1Int), Integer.valueOf(((Descriptors.EnumValueDescriptor)param1Object).getNumber()) });
        return;
      } 
      super.setRepeated(param1Builder, param1Int, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { param1Object }));
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
    
    RepeatedFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("List");
      this.getMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("List");
      this.getMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[] { int.class });
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
      this.type = this.getRepeatedMethod.getReturnType();
      stringBuilder = new StringBuilder();
      stringBuilder.append("set");
      stringBuilder.append(param1String);
      this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class, this.type });
      stringBuilder = new StringBuilder();
      stringBuilder.append("add");
      stringBuilder.append(param1String);
      this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { this.type });
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Count");
      this.getCountMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Count");
      this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("clear");
      stringBuilder.append(param1String);
      this.clearMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
    }
    
    public void addRepeated(GeneratedMessage.Builder param1Builder, Object param1Object) {
      GeneratedMessage.invokeOrDie(this.addRepeatedMethod, param1Builder, new Object[] { param1Object });
    }
    
    public void clear(GeneratedMessage.Builder param1Builder) {
      GeneratedMessage.invokeOrDie(this.clearMethod, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessage.Builder param1Builder) {
      return GeneratedMessage.invokeOrDie(this.getMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessage param1GeneratedMessage) {
      return GeneratedMessage.invokeOrDie(this.getMethod, param1GeneratedMessage, new Object[0]);
    }
    
    public Message.Builder getBuilder(GeneratedMessage.Builder param1Builder) {
      throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
    }
    
    public Object getRaw(GeneratedMessage.Builder param1Builder) {
      return get(param1Builder);
    }
    
    public Object getRaw(GeneratedMessage param1GeneratedMessage) {
      return get(param1GeneratedMessage);
    }
    
    public Object getRepeated(GeneratedMessage.Builder param1Builder, int param1Int) {
      return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, param1Builder, new Object[] { Integer.valueOf(param1Int) });
    }
    
    public Object getRepeated(GeneratedMessage param1GeneratedMessage, int param1Int) {
      return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, param1GeneratedMessage, new Object[] { Integer.valueOf(param1Int) });
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
    }
    
    public int getRepeatedCount(GeneratedMessage.Builder param1Builder) {
      return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, param1Builder, new Object[0])).intValue();
    }
    
    public int getRepeatedCount(GeneratedMessage param1GeneratedMessage) {
      return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, param1GeneratedMessage, new Object[0])).intValue();
    }
    
    public Object getRepeatedRaw(GeneratedMessage.Builder param1Builder, int param1Int) {
      return getRepeated(param1Builder, param1Int);
    }
    
    public Object getRepeatedRaw(GeneratedMessage param1GeneratedMessage, int param1Int) {
      return getRepeated(param1GeneratedMessage, param1Int);
    }
    
    public boolean has(GeneratedMessage.Builder param1Builder) {
      throw new UnsupportedOperationException("hasField() called on a repeated field.");
    }
    
    public boolean has(GeneratedMessage param1GeneratedMessage) {
      throw new UnsupportedOperationException("hasField() called on a repeated field.");
    }
    
    public Message.Builder newBuilder() {
      throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
    }
    
    public void set(GeneratedMessage.Builder param1Builder, Object param1Object) {
      clear(param1Builder);
      param1Object = ((List)param1Object).iterator();
      while (param1Object.hasNext())
        addRepeated(param1Builder, param1Object.next()); 
    }
    
    public void setRepeated(GeneratedMessage.Builder param1Builder, int param1Int, Object param1Object) {
      GeneratedMessage.invokeOrDie(this.setRepeatedMethod, param1Builder, new Object[] { Integer.valueOf(param1Int), param1Object });
    }
  }
  
  private static final class RepeatedMessageFieldAccessor extends FieldAccessorTable.RepeatedFieldAccessor {
    private final Method getBuilderMethodBuilder;
    
    private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
    
    RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1) {
      super(param1FieldDescriptor, param1String, param1Class, param1Class1);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String);
      stringBuilder.append("Builder");
      this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
    }
    
    private Object coerceType(Object param1Object) {
      return this.type.isInstance(param1Object) ? param1Object : ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param1Object).build();
    }
    
    public void addRepeated(GeneratedMessage.Builder param1Builder, Object param1Object) {
      super.addRepeated(param1Builder, coerceType(param1Object));
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param1Builder, int param1Int) {
      return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, param1Builder, new Object[] { Integer.valueOf(param1Int) });
    }
    
    public Message.Builder newBuilder() {
      return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
    }
    
    public void setRepeated(GeneratedMessage.Builder param1Builder, int param1Int, Object param1Object) {
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
    
    SingularEnumFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1, String param1String2) {
      super(param1FieldDescriptor, param1String1, param1Class, param1Class1, param1String2);
      this.enumDescriptor = param1FieldDescriptor.getEnumType();
      this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      this.supportUnknownEnumValue = param1FieldDescriptor.getFile().supportsUnknownEnumValue();
      if (this.supportUnknownEnumValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String1);
        stringBuilder.append("Value");
        this.getValueMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(param1String1);
        stringBuilder.append("Value");
        this.getValueMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
        stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(param1String1);
        stringBuilder.append("Value");
        this.setValueMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { int.class });
      } 
    }
    
    public Object get(GeneratedMessage.Builder param1Builder) {
      if (this.supportUnknownEnumValue) {
        int i = ((Integer)GeneratedMessage.invokeOrDie(this.getValueMethodBuilder, param1Builder, new Object[0])).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
      } 
      return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(param1Builder), new Object[0]);
    }
    
    public Object get(GeneratedMessage param1GeneratedMessage) {
      if (this.supportUnknownEnumValue) {
        int i = ((Integer)GeneratedMessage.invokeOrDie(this.getValueMethod, param1GeneratedMessage, new Object[0])).intValue();
        return this.enumDescriptor.findValueByNumberCreatingIfUnknown(i);
      } 
      return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(param1GeneratedMessage), new Object[0]);
    }
    
    public void set(GeneratedMessage.Builder param1Builder, Object param1Object) {
      if (this.supportUnknownEnumValue) {
        GeneratedMessage.invokeOrDie(this.setValueMethod, param1Builder, new Object[] { Integer.valueOf(((Descriptors.EnumValueDescriptor)param1Object).getNumber()) });
        return;
      } 
      super.set(param1Builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { param1Object }));
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
    
    SingularFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1, String param1String2) {
      Method method;
      this.field = param1FieldDescriptor;
      if (param1FieldDescriptor.getContainingOneof() != null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.isOneofField = bool;
      if (GeneratedMessage.FieldAccessorTable.supportFieldPresence(param1FieldDescriptor.getFile()) || (!this.isOneofField && param1FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)) {
        bool = true;
      } else {
        bool = false;
      } 
      this.hasHasMethod = bool;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("get");
      stringBuilder1.append(param1String1);
      this.getMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder1.toString(), new Class[0]);
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("get");
      stringBuilder1.append(param1String1);
      this.getMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      this.type = this.getMethod.getReturnType();
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("set");
      stringBuilder1.append(param1String1);
      this.setMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[] { this.type });
      boolean bool = this.hasHasMethod;
      StringBuilder stringBuilder2 = null;
      if (bool) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("has");
        stringBuilder1.append(param1String1);
        method = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder1.toString(), new Class[0]);
      } else {
        stringBuilder1 = null;
      } 
      this.hasMethod = (Method)stringBuilder1;
      if (this.hasHasMethod) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("has");
        stringBuilder1.append(param1String1);
        method = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      } else {
        stringBuilder1 = null;
      } 
      this.hasMethodBuilder = (Method)stringBuilder1;
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("clear");
      stringBuilder1.append(param1String1);
      this.clearMethod = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      if (this.isOneofField) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get");
        stringBuilder1.append(param1String2);
        stringBuilder1.append("Case");
        method = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder1.toString(), new Class[0]);
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
        method = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder1.toString(), new Class[0]);
      } 
      this.caseMethodBuilder = method;
    }
    
    private int getOneofFieldNumber(GeneratedMessage.Builder param1Builder) {
      return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, param1Builder, new Object[0])).getNumber();
    }
    
    private int getOneofFieldNumber(GeneratedMessage param1GeneratedMessage) {
      return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, param1GeneratedMessage, new Object[0])).getNumber();
    }
    
    public void addRepeated(GeneratedMessage.Builder param1Builder, Object param1Object) {
      throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
    }
    
    public void clear(GeneratedMessage.Builder param1Builder) {
      GeneratedMessage.invokeOrDie(this.clearMethod, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessage.Builder param1Builder) {
      return GeneratedMessage.invokeOrDie(this.getMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Object get(GeneratedMessage param1GeneratedMessage) {
      return GeneratedMessage.invokeOrDie(this.getMethod, param1GeneratedMessage, new Object[0]);
    }
    
    public Message.Builder getBuilder(GeneratedMessage.Builder param1Builder) {
      throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
    }
    
    public Object getRaw(GeneratedMessage.Builder param1Builder) {
      return get(param1Builder);
    }
    
    public Object getRaw(GeneratedMessage param1GeneratedMessage) {
      return get(param1GeneratedMessage);
    }
    
    public Object getRepeated(GeneratedMessage.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
    }
    
    public Object getRepeated(GeneratedMessage param1GeneratedMessage, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
    }
    
    public Message.Builder getRepeatedBuilder(GeneratedMessage.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
    }
    
    public int getRepeatedCount(GeneratedMessage.Builder param1Builder) {
      throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
    }
    
    public int getRepeatedCount(GeneratedMessage param1GeneratedMessage) {
      throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
    }
    
    public Object getRepeatedRaw(GeneratedMessage.Builder param1Builder, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
    }
    
    public Object getRepeatedRaw(GeneratedMessage param1GeneratedMessage, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
    }
    
    public boolean has(GeneratedMessage.Builder param1Builder) {
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
      return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, param1Builder, new Object[0])).booleanValue();
    }
    
    public boolean has(GeneratedMessage param1GeneratedMessage) {
      boolean bool = this.hasHasMethod;
      boolean bool1 = false;
      if (!bool) {
        if (this.isOneofField) {
          if (getOneofFieldNumber(param1GeneratedMessage) == this.field.getNumber())
            bool1 = true; 
          return bool1;
        } 
        return get(param1GeneratedMessage).equals(this.field.getDefaultValue()) ^ true;
      } 
      return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, param1GeneratedMessage, new Object[0])).booleanValue();
    }
    
    public Message.Builder newBuilder() {
      throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
    }
    
    public void set(GeneratedMessage.Builder param1Builder, Object param1Object) {
      GeneratedMessage.invokeOrDie(this.setMethod, param1Builder, new Object[] { param1Object });
    }
    
    public void setRepeated(GeneratedMessage.Builder param1Builder, int param1Int, Object param1Object) {
      throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
    }
  }
  
  private static final class SingularMessageFieldAccessor extends FieldAccessorTable.SingularFieldAccessor {
    private final Method getBuilderMethodBuilder;
    
    private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
    
    SingularMessageFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1, String param1String2) {
      super(param1FieldDescriptor, param1String1, param1Class, param1Class1, param1String2);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String1);
      stringBuilder.append("Builder");
      this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
    }
    
    private Object coerceType(Object param1Object) {
      return this.type.isInstance(param1Object) ? param1Object : ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)param1Object).buildPartial();
    }
    
    public Message.Builder getBuilder(GeneratedMessage.Builder param1Builder) {
      return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Message.Builder newBuilder() {
      return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
    }
    
    public void set(GeneratedMessage.Builder param1Builder, Object param1Object) {
      super.set(param1Builder, coerceType(param1Object));
    }
  }
  
  private static final class SingularStringFieldAccessor extends FieldAccessorTable.SingularFieldAccessor {
    private final Method getBytesMethod;
    
    private final Method getBytesMethodBuilder;
    
    private final Method setBytesMethodBuilder;
    
    SingularStringFieldAccessor(Descriptors.FieldDescriptor param1FieldDescriptor, String param1String1, Class<? extends GeneratedMessage> param1Class, Class<? extends GeneratedMessage.Builder> param1Class1, String param1String2) {
      super(param1FieldDescriptor, param1String1, param1Class, param1Class1, param1String2);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String1);
      stringBuilder.append("Bytes");
      this.getBytesMethod = GeneratedMessage.getMethodOrDie(param1Class, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("get");
      stringBuilder.append(param1String1);
      stringBuilder.append("Bytes");
      this.getBytesMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[0]);
      stringBuilder = new StringBuilder();
      stringBuilder.append("set");
      stringBuilder.append(param1String1);
      stringBuilder.append("Bytes");
      this.setBytesMethodBuilder = GeneratedMessage.getMethodOrDie(param1Class1, stringBuilder.toString(), new Class[] { ByteString.class });
    }
    
    public Object getRaw(GeneratedMessage.Builder param1Builder) {
      return GeneratedMessage.invokeOrDie(this.getBytesMethodBuilder, param1Builder, new Object[0]);
    }
    
    public Object getRaw(GeneratedMessage param1GeneratedMessage) {
      return GeneratedMessage.invokeOrDie(this.getBytesMethod, param1GeneratedMessage, new Object[0]);
    }
    
    public void set(GeneratedMessage.Builder param1Builder, Object param1Object) {
      if (param1Object instanceof ByteString) {
        GeneratedMessage.invokeOrDie(this.setBytesMethodBuilder, param1Builder, new Object[] { param1Object });
      } else {
        super.set(param1Builder, param1Object);
      } 
    }
  }
  
  public static class GeneratedExtension<ContainingType extends Message, Type> extends Extension<ContainingType, Type> {
    private GeneratedMessage.ExtensionDescriptorRetriever descriptorRetriever;
    
    private final Method enumGetValueDescriptor;
    
    private final Method enumValueOf;
    
    private final Extension.ExtensionType extensionType;
    
    private final Message messageDefaultInstance;
    
    private final Class singularType;
    
    GeneratedExtension(GeneratedMessage.ExtensionDescriptorRetriever param1ExtensionDescriptorRetriever, Class<?> param1Class, Message param1Message, Extension.ExtensionType param1ExtensionType) {
      StringBuilder stringBuilder;
      if (Message.class.isAssignableFrom(param1Class) && !param1Class.isInstance(param1Message)) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Bad messageDefaultInstance for ");
        stringBuilder.append(param1Class.getName());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      this.descriptorRetriever = (GeneratedMessage.ExtensionDescriptorRetriever)stringBuilder;
      this.singularType = param1Class;
      this.messageDefaultInstance = param1Message;
      if (ProtocolMessageEnum.class.isAssignableFrom(param1Class)) {
        this.enumValueOf = GeneratedMessage.getMethodOrDie(param1Class, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
        this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(param1Class, "getValueDescriptor", new Class[0]);
      } else {
        this.enumValueOf = null;
        this.enumGetValueDescriptor = null;
      } 
      this.extensionType = param1ExtensionType;
    }
    
    protected Object fromReflectionType(Object param1Object) {
      Descriptors.FieldDescriptor fieldDescriptor = getDescriptor();
      if (fieldDescriptor.isRepeated()) {
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE || fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
          ArrayList<Object> arrayList = new ArrayList();
          param1Object = ((List)param1Object).iterator();
          while (param1Object.hasNext())
            arrayList.add(singularFromReflectionType(param1Object.next())); 
          return arrayList;
        } 
        return param1Object;
      } 
      return singularFromReflectionType(param1Object);
    }
    
    public Type getDefaultValue() {
      return (Type)(isRepeated() ? Collections.emptyList() : ((getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) ? this.messageDefaultInstance : singularFromReflectionType(getDescriptor().getDefaultValue())));
    }
    
    public Descriptors.FieldDescriptor getDescriptor() {
      if (this.descriptorRetriever == null)
        throw new IllegalStateException("getDescriptor() called before internalInit()"); 
      return this.descriptorRetriever.getDescriptor();
    }
    
    protected Extension.ExtensionType getExtensionType() {
      return this.extensionType;
    }
    
    public WireFormat.FieldType getLiteType() {
      return getDescriptor().getLiteType();
    }
    
    public Message getMessageDefaultInstance() {
      return this.messageDefaultInstance;
    }
    
    public int getNumber() {
      return getDescriptor().getNumber();
    }
    
    public void internalInit(final Descriptors.FieldDescriptor descriptor) {
      if (this.descriptorRetriever != null)
        throw new IllegalStateException("Already initialized."); 
      this.descriptorRetriever = new GeneratedMessage.ExtensionDescriptorRetriever() {
          public Descriptors.FieldDescriptor getDescriptor() {
            return descriptor;
          }
        };
    }
    
    public boolean isRepeated() {
      return getDescriptor().isRepeated();
    }
    
    protected Object singularFromReflectionType(Object param1Object) {
      Descriptors.FieldDescriptor fieldDescriptor = getDescriptor();
      switch (fieldDescriptor.getJavaType()) {
        default:
          return param1Object;
        case ENUM:
          return GeneratedMessage.invokeOrDie(this.enumValueOf, null, new Object[] { param1Object });
        case MESSAGE:
          break;
      } 
      return this.singularType.isInstance(param1Object) ? param1Object : this.messageDefaultInstance.newBuilderForType().mergeFrom((Message)param1Object).build();
    }
    
    protected Object singularToReflectionType(Object param1Object) {
      Descriptors.FieldDescriptor fieldDescriptor = getDescriptor();
      return (GeneratedMessage.null.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[fieldDescriptor.getJavaType().ordinal()] != 2) ? param1Object : GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, param1Object, new Object[0]);
    }
    
    protected Object toReflectionType(Object param1Object) {
      Descriptors.FieldDescriptor fieldDescriptor = getDescriptor();
      if (fieldDescriptor.isRepeated()) {
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
          ArrayList<Object> arrayList = new ArrayList();
          param1Object = ((List)param1Object).iterator();
          while (param1Object.hasNext())
            arrayList.add(singularToReflectionType(param1Object.next())); 
          return arrayList;
        } 
        return param1Object;
      } 
      return singularToReflectionType(param1Object);
    }
  }
  
  class null implements ExtensionDescriptorRetriever {
    public Descriptors.FieldDescriptor getDescriptor() {
      return descriptor;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\GeneratedMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */