package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractMessage extends AbstractMessageLite implements Message {
  protected int memoizedSize = -1;
  
  private static boolean compareBytes(Object paramObject1, Object paramObject2) {
    return (paramObject1 instanceof byte[] && paramObject2 instanceof byte[]) ? Arrays.equals((byte[])paramObject1, (byte[])paramObject2) : toByteString(paramObject1).equals(toByteString(paramObject2));
  }
  
  static boolean compareFields(Map<Descriptors.FieldDescriptor, Object> paramMap1, Map<Descriptors.FieldDescriptor, Object> paramMap2) {
    if (paramMap1.size() != paramMap2.size())
      return false; 
    for (Descriptors.FieldDescriptor fieldDescriptor : paramMap1.keySet()) {
      if (!paramMap2.containsKey(fieldDescriptor))
        return false; 
      Object object1 = paramMap1.get(fieldDescriptor);
      Object object2 = paramMap2.get(fieldDescriptor);
      if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.BYTES) {
        if (fieldDescriptor.isRepeated()) {
          object1 = object1;
          object2 = object2;
          if (object1.size() != object2.size())
            return false; 
          for (byte b = 0; b < object1.size(); b++) {
            if (!compareBytes(object1.get(b), object2.get(b)))
              return false; 
          } 
          continue;
        } 
        if (!compareBytes(object1, object2))
          return false; 
        continue;
      } 
      if (fieldDescriptor.isMapField()) {
        if (!compareMapField(object1, object2))
          return false; 
        continue;
      } 
      if (!object1.equals(object2))
        return false; 
    } 
    return true;
  }
  
  private static boolean compareMapField(Object paramObject1, Object paramObject2) {
    return MapFieldLite.equals(convertMapEntryListToMap((List)paramObject1), convertMapEntryListToMap((List)paramObject2));
  }
  
  private static Map convertMapEntryListToMap(List paramList) {
    if (paramList.isEmpty())
      return Collections.emptyMap(); 
    HashMap<Object, Object> hashMap = new HashMap<>();
    Iterator<Message> iterator = paramList.iterator();
    Message message = iterator.next();
    Descriptors.Descriptor descriptor = message.getDescriptorForType();
    Descriptors.FieldDescriptor fieldDescriptor1 = descriptor.findFieldByName("key");
    Descriptors.FieldDescriptor fieldDescriptor2 = descriptor.findFieldByName("value");
    Object object2 = message.getField(fieldDescriptor2);
    Object object1 = object2;
    if (object2 instanceof Descriptors.EnumValueDescriptor)
      object1 = Integer.valueOf(((Descriptors.EnumValueDescriptor)object2).getNumber()); 
    hashMap.put(message.getField(fieldDescriptor1), object1);
    while (iterator.hasNext()) {
      message = iterator.next();
      object2 = message.getField(fieldDescriptor2);
      object1 = object2;
      if (object2 instanceof Descriptors.EnumValueDescriptor)
        object1 = Integer.valueOf(((Descriptors.EnumValueDescriptor)object2).getNumber()); 
      hashMap.put(message.getField(fieldDescriptor1), object1);
    } 
    return hashMap;
  }
  
  @Deprecated
  protected static int hashBoolean(boolean paramBoolean) {
    char c;
    if (paramBoolean) {
      c = 'ӏ';
    } else {
      c = 'ӕ';
    } 
    return c;
  }
  
  @Deprecated
  protected static int hashEnum(Internal.EnumLite paramEnumLite) {
    return paramEnumLite.getNumber();
  }
  
  @Deprecated
  protected static int hashEnumList(List<? extends Internal.EnumLite> paramList) {
    Iterator<? extends Internal.EnumLite> iterator = paramList.iterator();
    int i;
    for (i = 1; iterator.hasNext(); i = i * 31 + hashEnum(iterator.next()));
    return i;
  }
  
  protected static int hashFields(int paramInt, Map<Descriptors.FieldDescriptor, Object> paramMap) {
    for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : paramMap.entrySet()) {
      Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor)entry.getKey();
      entry = (Map.Entry<Descriptors.FieldDescriptor, Object>)entry.getValue();
      paramInt = paramInt * 37 + fieldDescriptor.getNumber();
      if (fieldDescriptor.isMapField()) {
        paramInt = paramInt * 53 + hashMapField(entry);
        continue;
      } 
      if (fieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
        paramInt = paramInt * 53 + entry.hashCode();
        continue;
      } 
      if (fieldDescriptor.isRepeated()) {
        paramInt = paramInt * 53 + Internal.hashEnumList((List<? extends Internal.EnumLite>)entry);
        continue;
      } 
      paramInt = paramInt * 53 + Internal.hashEnum((Internal.EnumLite)entry);
    } 
    return paramInt;
  }
  
  @Deprecated
  protected static int hashLong(long paramLong) {
    return (int)(paramLong ^ paramLong >>> 32L);
  }
  
  private static int hashMapField(Object paramObject) {
    return MapFieldLite.calculateHashCodeForMap(convertMapEntryListToMap((List)paramObject));
  }
  
  private static ByteString toByteString(Object paramObject) {
    return (paramObject instanceof byte[]) ? ByteString.copyFrom((byte[])paramObject) : (ByteString)paramObject;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Message))
      return false; 
    paramObject = paramObject;
    if (getDescriptorForType() != paramObject.getDescriptorForType())
      return false; 
    if (!compareFields(getAllFields(), paramObject.getAllFields()) || !getUnknownFields().equals(paramObject.getUnknownFields()))
      bool = false; 
    return bool;
  }
  
  public List<String> findInitializationErrors() {
    return MessageReflection.findMissingFields(this);
  }
  
  public String getInitializationErrorString() {
    return MessageReflection.delimitWithCommas(findInitializationErrors());
  }
  
  int getMemoizedSerializedSize() {
    return this.memoizedSize;
  }
  
  public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor paramOneofDescriptor) {
    throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    this.memoizedSize = MessageReflection.getSerializedSize(this, getAllFields());
    return this.memoizedSize;
  }
  
  public boolean hasOneof(Descriptors.OneofDescriptor paramOneofDescriptor) {
    throw new UnsupportedOperationException("hasOneof() is not implemented.");
  }
  
  public int hashCode() {
    int i = this.memoizedHashCode;
    int j = i;
    if (i == 0) {
      j = hashFields(779 + getDescriptorForType().hashCode(), getAllFields()) * 29 + getUnknownFields().hashCode();
      this.memoizedHashCode = j;
    } 
    return j;
  }
  
  public boolean isInitialized() {
    return MessageReflection.isInitialized(this);
  }
  
  protected Message.Builder newBuilderForType(BuilderParent paramBuilderParent) {
    throw new UnsupportedOperationException("Nested builder is not supported for this type.");
  }
  
  UninitializedMessageException newUninitializedMessageException() {
    return Builder.newUninitializedMessageException(this);
  }
  
  void setMemoizedSerializedSize(int paramInt) {
    this.memoizedSize = paramInt;
  }
  
  public final String toString() {
    return TextFormat.printer().printToString(this);
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    MessageReflection.writeMessageTo(this, getAllFields(), paramCodedOutputStream, false);
  }
  
  public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends AbstractMessageLite.Builder implements Message.Builder {
    protected static UninitializedMessageException newUninitializedMessageException(Message param1Message) {
      return new UninitializedMessageException(MessageReflection.findMissingFields(param1Message));
    }
    
    public BuilderType clear() {
      Iterator<Map.Entry> iterator = getAllFields().entrySet().iterator();
      while (iterator.hasNext())
        clearField((Descriptors.FieldDescriptor)((Map.Entry)iterator.next()).getKey()); 
      return (BuilderType)this;
    }
    
    public BuilderType clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      throw new UnsupportedOperationException("clearOneof() is not implemented.");
    }
    
    public BuilderType clone() {
      throw new UnsupportedOperationException("clone() should be implemented in subclasses.");
    }
    
    void dispose() {
      throw new IllegalStateException("Should be overridden by subclasses.");
    }
    
    public List<String> findInitializationErrors() {
      return MessageReflection.findMissingFields(this);
    }
    
    public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor param1FieldDescriptor) {
      throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
    }
    
    public String getInitializationErrorString() {
      return MessageReflection.delimitWithCommas(findInitializationErrors());
    }
    
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor param1OneofDescriptor) {
      throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }
    
    public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int) {
      throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on an unsupported message type.");
    }
    
    public boolean hasOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }
    
    protected BuilderType internalMergeFrom(AbstractMessageLite param1AbstractMessageLite) {
      return mergeFrom((Message)param1AbstractMessageLite);
    }
    
    void markClean() {
      throw new IllegalStateException("Should be overridden by subclasses.");
    }
    
    public boolean mergeDelimitedFrom(InputStream param1InputStream) throws IOException {
      return super.mergeDelimitedFrom(param1InputStream);
    }
    
    public boolean mergeDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return super.mergeDelimitedFrom(param1InputStream, param1ExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (BuilderType)super.mergeFrom(param1ByteString);
    }
    
    public BuilderType mergeFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (BuilderType)super.mergeFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return mergeFrom(param1CodedInputStream, ExtensionRegistry.getEmptyRegistry());
    }
    
    public BuilderType mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      UnknownFieldSet.Builder builder;
      int i;
      MessageReflection.BuilderAdapter builderAdapter;
      if (param1CodedInputStream.shouldDiscardUnknownFields()) {
        builder = null;
      } else {
        builder = UnknownFieldSet.newBuilder(getUnknownFields());
      } 
      do {
        i = param1CodedInputStream.readTag();
        if (i == 0)
          break; 
        builderAdapter = new MessageReflection.BuilderAdapter(this);
      } while (MessageReflection.mergeFieldFrom(param1CodedInputStream, builder, param1ExtensionRegistryLite, getDescriptorForType(), builderAdapter, i));
      if (builder != null)
        setUnknownFields(builder.build()); 
      return (BuilderType)this;
    }
    
    public BuilderType mergeFrom(Message param1Message) {
      return mergeFrom(param1Message, param1Message.getAllFields());
    }
    
    BuilderType mergeFrom(Message param1Message, Map<Descriptors.FieldDescriptor, Object> param1Map) {
      if (param1Message.getDescriptorForType() != getDescriptorForType())
        throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type."); 
      for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : param1Map.entrySet()) {
        Iterator iterator;
        Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor)entry.getKey();
        if (fieldDescriptor.isRepeated()) {
          iterator = ((List)entry.getValue()).iterator();
          while (iterator.hasNext())
            addRepeatedField(fieldDescriptor, iterator.next()); 
          continue;
        } 
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
          Message message = (Message)getField(fieldDescriptor);
          if (message == message.getDefaultInstanceForType()) {
            setField(fieldDescriptor, iterator.getValue());
            continue;
          } 
          setField(fieldDescriptor, message.newBuilderForType().mergeFrom(message).mergeFrom((Message)iterator.getValue()).build());
          continue;
        } 
        setField(fieldDescriptor, iterator.getValue());
      } 
      mergeUnknownFields(param1Message.getUnknownFields());
      return (BuilderType)this;
    }
    
    public BuilderType mergeFrom(InputStream param1InputStream) throws IOException {
      return (BuilderType)super.mergeFrom(param1InputStream);
    }
    
    public BuilderType mergeFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (BuilderType)super.mergeFrom(param1InputStream, param1ExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (BuilderType)super.mergeFrom(param1ArrayOfbyte);
    }
    
    public BuilderType mergeFrom(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      return (BuilderType)super.mergeFrom(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public BuilderType mergeFrom(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (BuilderType)super.mergeFrom(param1ArrayOfbyte, param1Int1, param1Int2, param1ExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (BuilderType)super.mergeFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public BuilderType mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(param1UnknownFieldSet).build());
      return (BuilderType)this;
    }
    
    public String toString() {
      return TextFormat.printer().printToString(this);
    }
  }
  
  protected static interface BuilderParent {
    void markDirty();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\AbstractMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */