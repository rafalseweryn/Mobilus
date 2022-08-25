package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DoubleValue extends GeneratedMessageV3 implements DoubleValueOrBuilder {
  private static final DoubleValue DEFAULT_INSTANCE = new DoubleValue();
  
  private static final Parser<DoubleValue> PARSER = new AbstractParser<DoubleValue>() {
      public DoubleValue parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new DoubleValue(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  public static final int VALUE_FIELD_NUMBER = 1;
  
  private static final long serialVersionUID = 0L;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private double value_;
  
  private DoubleValue() {}
  
  private DoubleValue(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    this();
    if (paramExtensionRegistryLite == null)
      throw new NullPointerException(); 
    UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
    for (boolean bool = false; !bool; bool = true) {
      try {
        int i = paramCodedInputStream.readTag();
        if (i != 0) {
          if (i != 9) {
            if (!parseUnknownField(paramCodedInputStream, builder, paramExtensionRegistryLite, i))
              continue; 
            continue;
          } 
          this.value_ = paramCodedInputStream.readDouble();
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
  
  private DoubleValue(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static DoubleValue getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return WrappersProto.internal_static_google_protobuf_DoubleValue_descriptor;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(DoubleValue paramDoubleValue) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramDoubleValue);
  }
  
  public static DoubleValue of(double paramDouble) {
    return newBuilder().setValue(paramDouble).build();
  }
  
  public static DoubleValue parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<DoubleValue>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static DoubleValue parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<DoubleValue>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static DoubleValue parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static DoubleValue parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static DoubleValue parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<DoubleValue>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static DoubleValue parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<DoubleValue>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static DoubleValue parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<DoubleValue>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static DoubleValue parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<DoubleValue>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static DoubleValue parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static DoubleValue parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static DoubleValue parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static DoubleValue parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<DoubleValue> parser() {
    return PARSER;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof DoubleValue))
      return super.equals(paramObject); 
    paramObject = paramObject;
    return (Double.doubleToLongBits(getValue()) != Double.doubleToLongBits(paramObject.getValue())) ? false : (!!this.unknownFields.equals(((DoubleValue)paramObject).unknownFields));
  }
  
  public DoubleValue getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public Parser<DoubleValue> getParserForType() {
    return PARSER;
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    i = 0;
    if (this.value_ != 0.0D)
      i = 0 + CodedOutputStream.computeDoubleSize(1, this.value_); 
    i += this.unknownFields.getSerializedSize();
    this.memoizedSize = i;
    return i;
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  public double getValue() {
    return this.value_;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int i = (((779 + getDescriptor().hashCode()) * 37 + 1) * 53 + Internal.hashLong(Double.doubleToLongBits(getValue()))) * 29 + this.unknownFields.hashCode();
    this.memoizedHashCode = i;
    return i;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return WrappersProto.internal_static_google_protobuf_DoubleValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)DoubleValue.class, (Class)Builder.class);
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
    return new DoubleValue();
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
    if (this.value_ != 0.0D)
      paramCodedOutputStream.writeDouble(1, this.value_); 
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DoubleValueOrBuilder {
    private double value_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WrappersProto.internal_static_google_protobuf_DoubleValue_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      boolean bool = GeneratedMessageV3.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public DoubleValue build() {
      DoubleValue doubleValue = buildPartial();
      if (!doubleValue.isInitialized())
        throw newUninitializedMessageException(doubleValue); 
      return doubleValue;
    }
    
    public DoubleValue buildPartial() {
      DoubleValue doubleValue = new DoubleValue(this);
      DoubleValue.access$302(doubleValue, this.value_);
      onBuilt();
      return doubleValue;
    }
    
    public Builder clear() {
      super.clear();
      this.value_ = 0.0D;
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearValue() {
      this.value_ = 0.0D;
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public DoubleValue getDefaultInstanceForType() {
      return DoubleValue.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return WrappersProto.internal_static_google_protobuf_DoubleValue_descriptor;
    }
    
    public double getValue() {
      return this.value_;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WrappersProto.internal_static_google_protobuf_DoubleValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)DoubleValue.class, (Class)Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      InvalidProtocolBufferException invalidProtocolBufferException1;
      ExtensionRegistryLite extensionRegistryLite;
      InvalidProtocolBufferException invalidProtocolBufferException2 = null;
      try {
        return this;
      } catch (InvalidProtocolBufferException null) {
        DoubleValue doubleValue;
      } finally {
        param1ExtensionRegistryLite = null;
        invalidProtocolBufferException1 = invalidProtocolBufferException2;
      } 
      if (invalidProtocolBufferException1 != null)
        mergeFrom((DoubleValue)invalidProtocolBufferException1); 
      throw extensionRegistryLite;
    }
    
    public Builder mergeFrom(DoubleValue param1DoubleValue) {
      if (param1DoubleValue == DoubleValue.getDefaultInstance())
        return this; 
      if (param1DoubleValue.getValue() != 0.0D)
        setValue(param1DoubleValue.getValue()); 
      mergeUnknownFields(param1DoubleValue.unknownFields);
      onChanged();
      return this;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof DoubleValue)
        return mergeFrom((DoubleValue)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.mergeUnknownFields(param1UnknownFieldSet);
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
    
    public Builder setValue(double param1Double) {
      this.value_ = param1Double;
      onChanged();
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\DoubleValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */