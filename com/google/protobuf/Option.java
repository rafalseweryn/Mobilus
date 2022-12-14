package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Option extends GeneratedMessageV3 implements OptionOrBuilder {
  private static final Option DEFAULT_INSTANCE = new Option();
  
  public static final int NAME_FIELD_NUMBER = 1;
  
  private static final Parser<Option> PARSER = new AbstractParser<Option>() {
      public Option parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new Option(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  public static final int VALUE_FIELD_NUMBER = 2;
  
  private static final long serialVersionUID = 0L;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private volatile Object name_;
  
  private Any value_;
  
  private Option() {
    this.name_ = "";
  }
  
  private Option(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    this();
    if (paramExtensionRegistryLite == null)
      throw new NullPointerException(); 
    UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
    for (boolean bool = false; !bool; bool = true) {
      try {
        int i = paramCodedInputStream.readTag();
        if (i != 0) {
          if (i != 10) {
            if (i != 18) {
              if (!parseUnknownField(paramCodedInputStream, builder, paramExtensionRegistryLite, i))
                continue; 
              continue;
            } 
            Any.Builder builder1 = null;
            if (this.value_ != null)
              builder1 = this.value_.toBuilder(); 
            this.value_ = paramCodedInputStream.<Any>readMessage(Any.parser(), paramExtensionRegistryLite);
            if (builder1 != null) {
              builder1.mergeFrom(this.value_);
              this.value_ = builder1.buildPartial();
            } 
            continue;
          } 
          this.name_ = paramCodedInputStream.readStringRequireUtf8();
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
    } 
    this.unknownFields = builder.build();
    makeExtensionsImmutable();
  }
  
  private Option(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static Option getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return TypeProto.internal_static_google_protobuf_Option_descriptor;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Option paramOption) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramOption);
  }
  
  public static Option parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Option>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static Option parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Option>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Option parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static Option parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static Option parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<Option>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static Option parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Option>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static Option parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Option>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static Option parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Option>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Option parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static Option parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static Option parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static Option parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<Option> parser() {
    return PARSER;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Option))
      return super.equals(paramObject); 
    paramObject = paramObject;
    return !getName().equals(paramObject.getName()) ? false : ((hasValue() != paramObject.hasValue()) ? false : ((hasValue() && !getValue().equals(paramObject.getValue())) ? false : (!!this.unknownFields.equals(((Option)paramObject).unknownFields))));
  }
  
  public Option getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public String getName() {
    Object object = this.name_;
    if (object instanceof String)
      return (String)object; 
    object = ((ByteString)object).toStringUtf8();
    this.name_ = object;
    return (String)object;
  }
  
  public ByteString getNameBytes() {
    Object object = this.name_;
    if (object instanceof String) {
      object = ByteString.copyFromUtf8((String)object);
      this.name_ = object;
      return (ByteString)object;
    } 
    return (ByteString)object;
  }
  
  public Parser<Option> getParserForType() {
    return PARSER;
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    i = 0;
    if (!getNameBytes().isEmpty())
      i = 0 + GeneratedMessageV3.computeStringSize(1, this.name_); 
    int j = i;
    if (this.value_ != null)
      j = i + CodedOutputStream.computeMessageSize(2, getValue()); 
    i = j + this.unknownFields.getSerializedSize();
    this.memoizedSize = i;
    return i;
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  public Any getValue() {
    Any any;
    if (this.value_ == null) {
      any = Any.getDefaultInstance();
    } else {
      any = this.value_;
    } 
    return any;
  }
  
  public AnyOrBuilder getValueOrBuilder() {
    return getValue();
  }
  
  public boolean hasValue() {
    boolean bool;
    if (this.value_ != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int i = ((779 + getDescriptor().hashCode()) * 37 + 1) * 53 + getName().hashCode();
    int j = i;
    if (hasValue())
      j = (i * 37 + 2) * 53 + getValue().hashCode(); 
    j = j * 29 + this.unknownFields.hashCode();
    this.memoizedHashCode = j;
    return j;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Option.class, (Class)Builder.class);
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
    return new Option();
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
    if (!getNameBytes().isEmpty())
      GeneratedMessageV3.writeString(paramCodedOutputStream, 1, this.name_); 
    if (this.value_ != null)
      paramCodedOutputStream.writeMessage(2, getValue()); 
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OptionOrBuilder {
    private Object name_ = "";
    
    private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> valueBuilder_;
    
    private Any value_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return TypeProto.internal_static_google_protobuf_Option_descriptor;
    }
    
    private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
      if (this.valueBuilder_ == null) {
        this.valueBuilder_ = new SingleFieldBuilderV3<>(getValue(), getParentForChildren(), isClean());
        this.value_ = null;
      } 
      return this.valueBuilder_;
    }
    
    private void maybeForceBuilderInitialization() {
      boolean bool = GeneratedMessageV3.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public Option build() {
      Option option = buildPartial();
      if (!option.isInitialized())
        throw newUninitializedMessageException(option); 
      return option;
    }
    
    public Option buildPartial() {
      Option option = new Option(this);
      Option.access$302(option, this.name_);
      if (this.valueBuilder_ == null) {
        Option.access$402(option, this.value_);
      } else {
        Option.access$402(option, this.valueBuilder_.build());
      } 
      onBuilt();
      return option;
    }
    
    public Builder clear() {
      super.clear();
      this.name_ = "";
      if (this.valueBuilder_ == null) {
        this.value_ = null;
      } else {
        this.value_ = null;
        this.valueBuilder_ = null;
      } 
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearName() {
      this.name_ = Option.getDefaultInstance().getName();
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearValue() {
      if (this.valueBuilder_ == null) {
        this.value_ = null;
        onChanged();
      } else {
        this.value_ = null;
        this.valueBuilder_ = null;
      } 
      return this;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public Option getDefaultInstanceForType() {
      return Option.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return TypeProto.internal_static_google_protobuf_Option_descriptor;
    }
    
    public String getName() {
      Object object = this.name_;
      if (!(object instanceof String)) {
        object = ((ByteString)object).toStringUtf8();
        this.name_ = object;
        return (String)object;
      } 
      return (String)object;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public Any getValue() {
      if (this.valueBuilder_ == null) {
        Any any;
        if (this.value_ == null) {
          any = Any.getDefaultInstance();
        } else {
          any = this.value_;
        } 
        return any;
      } 
      return this.valueBuilder_.getMessage();
    }
    
    public Any.Builder getValueBuilder() {
      onChanged();
      return getValueFieldBuilder().getBuilder();
    }
    
    public AnyOrBuilder getValueOrBuilder() {
      Any any;
      if (this.valueBuilder_ != null)
        return this.valueBuilder_.getMessageOrBuilder(); 
      if (this.value_ == null) {
        any = Any.getDefaultInstance();
      } else {
        any = this.value_;
      } 
      return any;
    }
    
    public boolean hasValue() {
      return (this.valueBuilder_ != null || this.value_ != null);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Option.class, (Class)Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      CodedInputStream codedInputStream = null;
      try {
        Option option = Option.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        Option option = (Option)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1ExtensionRegistryLite = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((Option)param1CodedInputStream); 
      throw param1ExtensionRegistryLite;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof Option)
        return mergeFrom((Option)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(Option param1Option) {
      if (param1Option == Option.getDefaultInstance())
        return this; 
      if (!param1Option.getName().isEmpty()) {
        this.name_ = param1Option.name_;
        onChanged();
      } 
      if (param1Option.hasValue())
        mergeValue(param1Option.getValue()); 
      mergeUnknownFields(param1Option.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder mergeValue(Any param1Any) {
      if (this.valueBuilder_ == null) {
        if (this.value_ != null) {
          this.value_ = Any.newBuilder(this.value_).mergeFrom(param1Any).buildPartial();
        } else {
          this.value_ = param1Any;
        } 
        onChanged();
      } else {
        this.valueBuilder_.mergeFrom(param1Any);
      } 
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setName(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.name_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      AbstractMessageLite.checkByteStringIsUtf8(param1ByteString);
      this.name_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.setUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setValue(Any.Builder param1Builder) {
      if (this.valueBuilder_ == null) {
        this.value_ = param1Builder.build();
        onChanged();
      } else {
        this.valueBuilder_.setMessage(param1Builder.build());
      } 
      return this;
    }
    
    public Builder setValue(Any param1Any) {
      if (this.valueBuilder_ == null) {
        if (param1Any == null)
          throw new NullPointerException(); 
        this.value_ = param1Any;
        onChanged();
      } else {
        this.valueBuilder_.setMessage(param1Any);
      } 
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Option.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */