package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class Struct extends GeneratedMessageV3 implements StructOrBuilder {
  private static final Struct DEFAULT_INSTANCE = new Struct();
  
  public static final int FIELDS_FIELD_NUMBER = 1;
  
  private static final Parser<Struct> PARSER = new AbstractParser<Struct>() {
      public Struct parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new Struct(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  private static final long serialVersionUID = 0L;
  
  private MapField<String, Value> fields_;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private Struct() {}
  
  private Struct(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    this();
    if (paramExtensionRegistryLite == null)
      throw new NullPointerException(); 
    UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
    boolean bool = false;
    int i = 0;
    while (!bool) {
      try {
        int j = paramCodedInputStream.readTag();
        if (j != 0) {
          if (j != 10) {
            if (!parseUnknownField(paramCodedInputStream, builder, paramExtensionRegistryLite, j))
              continue; 
            continue;
          } 
          j = i;
          if ((i & 0x1) == 0) {
            this.fields_ = MapField.newMapField(FieldsDefaultEntryHolder.defaultEntry);
            j = i | 0x1;
          } 
          MapEntry mapEntry = paramCodedInputStream.<MapEntry>readMessage((Parser)FieldsDefaultEntryHolder.defaultEntry.getParserForType(), paramExtensionRegistryLite);
          this.fields_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
          i = j;
          continue;
        } 
        continue;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw invalidProtocolBufferException.setUnfinishedMessage(this);
      } catch (IOException iOException) {
        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
        this(iOException);
        throw invalidProtocolBufferException.setUnfinishedMessage(this);
      } finally {}
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
      throw paramCodedInputStream;
      bool = true;
    } 
    this.unknownFields = builder.build();
    makeExtensionsImmutable();
  }
  
  private Struct(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static Struct getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return StructProto.internal_static_google_protobuf_Struct_descriptor;
  }
  
  private MapField<String, Value> internalGetFields() {
    return (this.fields_ == null) ? MapField.emptyMapField(FieldsDefaultEntryHolder.defaultEntry) : this.fields_;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Struct paramStruct) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramStruct);
  }
  
  public static Struct parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Struct>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static Struct parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Struct>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Struct parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static Struct parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static Struct parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<Struct>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static Struct parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Struct>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static Struct parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Struct>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static Struct parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Struct>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Struct parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static Struct parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static Struct parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static Struct parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<Struct> parser() {
    return PARSER;
  }
  
  public boolean containsFields(String paramString) {
    if (paramString == null)
      throw new NullPointerException(); 
    return internalGetFields().getMap().containsKey(paramString);
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Struct))
      return super.equals(paramObject); 
    paramObject = paramObject;
    return !internalGetFields().equals(paramObject.internalGetFields()) ? false : (!!this.unknownFields.equals(((Struct)paramObject).unknownFields));
  }
  
  public Struct getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  @Deprecated
  public Map<String, Value> getFields() {
    return getFieldsMap();
  }
  
  public int getFieldsCount() {
    return internalGetFields().getMap().size();
  }
  
  public Map<String, Value> getFieldsMap() {
    return internalGetFields().getMap();
  }
  
  public Value getFieldsOrDefault(String paramString, Value paramValue) {
    if (paramString == null)
      throw new NullPointerException(); 
    Map<String, Value> map = internalGetFields().getMap();
    if (map.containsKey(paramString))
      paramValue = map.get(paramString); 
    return paramValue;
  }
  
  public Value getFieldsOrThrow(String paramString) {
    if (paramString == null)
      throw new NullPointerException(); 
    Map<String, Value> map = internalGetFields().getMap();
    if (!map.containsKey(paramString))
      throw new IllegalArgumentException(); 
    return map.get(paramString);
  }
  
  public Parser<Struct> getParserForType() {
    return PARSER;
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    i = 0;
    for (Map.Entry entry : internalGetFields().getMap().entrySet())
      i += CodedOutputStream.computeMessageSize(1, FieldsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build()); 
    i += this.unknownFields.getSerializedSize();
    this.memoizedSize = i;
    return i;
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int i = 779 + getDescriptor().hashCode();
    int j = i;
    if (!internalGetFields().getMap().isEmpty())
      j = (i * 37 + 1) * 53 + internalGetFields().hashCode(); 
    j = j * 29 + this.unknownFields.hashCode();
    this.memoizedHashCode = j;
    return j;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Struct.class, (Class)Builder.class);
  }
  
  protected MapField internalGetMapField(int paramInt) {
    if (paramInt != 1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid map field number: ");
      stringBuilder.append(paramInt);
      throw new RuntimeException(stringBuilder.toString());
    } 
    return internalGetFields();
  }
  
  public final boolean isInitialized() {
    byte b = this.memoizedIsInitialized;
    if (b == 1)
      return true; 
    if (b == 0)
      return false; 
    this.memoizedIsInitialized = (byte)1;
    return true;
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent paramBuilderParent) {
    return new Builder(paramBuilderParent);
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter paramUnusedPrivateParameter) {
    return new Struct();
  }
  
  public Builder toBuilder() {
    Builder builder;
    if (this == DEFAULT_INSTANCE) {
      builder = new Builder();
    } else {
      builder = (new Builder()).mergeFrom(this);
    } 
    return builder;
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    GeneratedMessageV3.serializeStringMapTo(paramCodedOutputStream, internalGetFields(), FieldsDefaultEntryHolder.defaultEntry, 1);
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StructOrBuilder {
    private int bitField0_;
    
    private MapField<String, Value> fields_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return StructProto.internal_static_google_protobuf_Struct_descriptor;
    }
    
    private MapField<String, Value> internalGetFields() {
      return (this.fields_ == null) ? MapField.emptyMapField(Struct.FieldsDefaultEntryHolder.defaultEntry) : this.fields_;
    }
    
    private MapField<String, Value> internalGetMutableFields() {
      onChanged();
      if (this.fields_ == null)
        this.fields_ = MapField.newMapField(Struct.FieldsDefaultEntryHolder.defaultEntry); 
      if (!this.fields_.isMutable())
        this.fields_ = this.fields_.copy(); 
      return this.fields_;
    }
    
    private void maybeForceBuilderInitialization() {
      boolean bool = GeneratedMessageV3.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public Struct build() {
      Struct struct = buildPartial();
      if (!struct.isInitialized())
        throw newUninitializedMessageException(struct); 
      return struct;
    }
    
    public Struct buildPartial() {
      Struct struct = new Struct(this);
      int i = this.bitField0_;
      Struct.access$302(struct, internalGetFields());
      struct.fields_.makeImmutable();
      onBuilt();
      return struct;
    }
    
    public Builder clear() {
      super.clear();
      internalGetMutableFields().clear();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearFields() {
      internalGetMutableFields().getMutableMap().clear();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public boolean containsFields(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      return internalGetFields().getMap().containsKey(param1String);
    }
    
    public Struct getDefaultInstanceForType() {
      return Struct.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return StructProto.internal_static_google_protobuf_Struct_descriptor;
    }
    
    @Deprecated
    public Map<String, Value> getFields() {
      return getFieldsMap();
    }
    
    public int getFieldsCount() {
      return internalGetFields().getMap().size();
    }
    
    public Map<String, Value> getFieldsMap() {
      return internalGetFields().getMap();
    }
    
    public Value getFieldsOrDefault(String param1String, Value param1Value) {
      if (param1String == null)
        throw new NullPointerException(); 
      Map<String, Value> map = internalGetFields().getMap();
      if (map.containsKey(param1String))
        param1Value = map.get(param1String); 
      return param1Value;
    }
    
    public Value getFieldsOrThrow(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      Map<String, Value> map = internalGetFields().getMap();
      if (!map.containsKey(param1String))
        throw new IllegalArgumentException(); 
      return map.get(param1String);
    }
    
    @Deprecated
    public Map<String, Value> getMutableFields() {
      return internalGetMutableFields().getMutableMap();
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Struct.class, (Class)Builder.class);
    }
    
    protected MapField internalGetMapField(int param1Int) {
      if (param1Int != 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid map field number: ");
        stringBuilder.append(param1Int);
        throw new RuntimeException(stringBuilder.toString());
      } 
      return internalGetFields();
    }
    
    protected MapField internalGetMutableMapField(int param1Int) {
      if (param1Int != 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid map field number: ");
        stringBuilder.append(param1Int);
        throw new RuntimeException(stringBuilder.toString());
      } 
      return internalGetMutableFields();
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      Exception exception;
      CodedInputStream codedInputStream = null;
      try {
        Struct struct = Struct.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        Struct struct = (Struct)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        exception = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((Struct)param1CodedInputStream); 
      throw exception;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof Struct)
        return mergeFrom((Struct)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(Struct param1Struct) {
      if (param1Struct == Struct.getDefaultInstance())
        return this; 
      internalGetMutableFields().mergeFrom(param1Struct.internalGetFields());
      mergeUnknownFields(param1Struct.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder putAllFields(Map<String, Value> param1Map) {
      internalGetMutableFields().getMutableMap().putAll(param1Map);
      return this;
    }
    
    public Builder putFields(String param1String, Value param1Value) {
      if (param1String == null)
        throw new NullPointerException(); 
      if (param1Value == null)
        throw new NullPointerException(); 
      internalGetMutableFields().getMutableMap().put(param1String, param1Value);
      return this;
    }
    
    public Builder removeFields(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      internalGetMutableFields().getMutableMap().remove(param1String);
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  private static final class FieldsDefaultEntryHolder {
    static final MapEntry<String, Value> defaultEntry = MapEntry.newDefaultInstance(StructProto.internal_static_google_protobuf_Struct_FieldsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Struct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */