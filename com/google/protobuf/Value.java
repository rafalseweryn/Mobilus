package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Value extends GeneratedMessageV3 implements ValueOrBuilder {
  public static final int BOOL_VALUE_FIELD_NUMBER = 4;
  
  private static final Value DEFAULT_INSTANCE = new Value();
  
  public static final int LIST_VALUE_FIELD_NUMBER = 6;
  
  public static final int NULL_VALUE_FIELD_NUMBER = 1;
  
  public static final int NUMBER_VALUE_FIELD_NUMBER = 2;
  
  private static final Parser<Value> PARSER = new AbstractParser<Value>() {
      public Value parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new Value(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  public static final int STRING_VALUE_FIELD_NUMBER = 3;
  
  public static final int STRUCT_VALUE_FIELD_NUMBER = 5;
  
  private static final long serialVersionUID = 0L;
  
  private int kindCase_ = 0;
  
  private Object kind_;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private Value() {}
  
  private Value(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    this();
    if (paramExtensionRegistryLite == null)
      throw new NullPointerException(); 
    UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
    for (boolean bool = false; !bool; bool = true) {
      try {
        int i = paramCodedInputStream.readTag();
        if (i != 0) {
          if (i != 8) {
            if (i != 17) {
              if (i != 26) {
                if (i != 32) {
                  Struct.Builder builder2;
                  ListValue.Builder builder1 = null;
                  ListValue.Builder builder3 = null;
                  if (i != 42) {
                    if (i != 50) {
                      if (!parseUnknownField(paramCodedInputStream, builder, paramExtensionRegistryLite, i))
                        continue; 
                      continue;
                    } 
                    if (this.kindCase_ == 6)
                      builder3 = ((ListValue)this.kind_).toBuilder(); 
                    this.kind_ = paramCodedInputStream.readMessage(ListValue.parser(), paramExtensionRegistryLite);
                    if (builder3 != null) {
                      builder3.mergeFrom((ListValue)this.kind_);
                      this.kind_ = builder3.buildPartial();
                    } 
                    this.kindCase_ = 6;
                    continue;
                  } 
                  builder3 = builder1;
                  if (this.kindCase_ == 5)
                    builder2 = ((Struct)this.kind_).toBuilder(); 
                  this.kind_ = paramCodedInputStream.readMessage(Struct.parser(), paramExtensionRegistryLite);
                  if (builder2 != null) {
                    builder2.mergeFrom((Struct)this.kind_);
                    this.kind_ = builder2.buildPartial();
                  } 
                  this.kindCase_ = 5;
                  continue;
                } 
                this.kindCase_ = 4;
                this.kind_ = Boolean.valueOf(paramCodedInputStream.readBool());
                continue;
              } 
              String str = paramCodedInputStream.readStringRequireUtf8();
              this.kindCase_ = 3;
              this.kind_ = str;
              continue;
            } 
            this.kindCase_ = 2;
            this.kind_ = Double.valueOf(paramCodedInputStream.readDouble());
            continue;
          } 
          i = paramCodedInputStream.readEnum();
          this.kindCase_ = 1;
          this.kind_ = Integer.valueOf(i);
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
  
  private Value(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static Value getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return StructProto.internal_static_google_protobuf_Value_descriptor;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Value paramValue) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramValue);
  }
  
  public static Value parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Value>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static Value parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Value>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Value parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static Value parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static Value parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<Value>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static Value parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Value>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static Value parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Value>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static Value parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Value>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Value parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static Value parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static Value parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static Value parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<Value> parser() {
    return PARSER;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Value))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (!getKindCase().equals(paramObject.getKindCase()))
      return false; 
    switch (this.kindCase_) {
      case 6:
        if (!getListValue().equals(paramObject.getListValue()))
          return false; 
        break;
      case 5:
        if (!getStructValue().equals(paramObject.getStructValue()))
          return false; 
        break;
      case 4:
        if (getBoolValue() != paramObject.getBoolValue())
          return false; 
        break;
      case 3:
        if (!getStringValue().equals(paramObject.getStringValue()))
          return false; 
        break;
      case 2:
        if (Double.doubleToLongBits(getNumberValue()) != Double.doubleToLongBits(paramObject.getNumberValue()))
          return false; 
        break;
      case 1:
        if (getNullValueValue() != paramObject.getNullValueValue())
          return false; 
        break;
    } 
    return !!this.unknownFields.equals(((Value)paramObject).unknownFields);
  }
  
  public boolean getBoolValue() {
    return (this.kindCase_ == 4) ? ((Boolean)this.kind_).booleanValue() : false;
  }
  
  public Value getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public KindCase getKindCase() {
    return KindCase.forNumber(this.kindCase_);
  }
  
  public ListValue getListValue() {
    return (this.kindCase_ == 6) ? (ListValue)this.kind_ : ListValue.getDefaultInstance();
  }
  
  public ListValueOrBuilder getListValueOrBuilder() {
    return (this.kindCase_ == 6) ? (ListValue)this.kind_ : ListValue.getDefaultInstance();
  }
  
  public NullValue getNullValue() {
    if (this.kindCase_ == 1) {
      NullValue nullValue1 = NullValue.valueOf(((Integer)this.kind_).intValue());
      NullValue nullValue2 = nullValue1;
      if (nullValue1 == null)
        nullValue2 = NullValue.UNRECOGNIZED; 
      return nullValue2;
    } 
    return NullValue.NULL_VALUE;
  }
  
  public int getNullValueValue() {
    return (this.kindCase_ == 1) ? ((Integer)this.kind_).intValue() : 0;
  }
  
  public double getNumberValue() {
    return (this.kindCase_ == 2) ? ((Double)this.kind_).doubleValue() : 0.0D;
  }
  
  public Parser<Value> getParserForType() {
    return PARSER;
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    i = 0;
    if (this.kindCase_ == 1)
      i = 0 + CodedOutputStream.computeEnumSize(1, ((Integer)this.kind_).intValue()); 
    int j = i;
    if (this.kindCase_ == 2)
      j = i + CodedOutputStream.computeDoubleSize(2, ((Double)this.kind_).doubleValue()); 
    i = j;
    if (this.kindCase_ == 3)
      i = j + GeneratedMessageV3.computeStringSize(3, this.kind_); 
    j = i;
    if (this.kindCase_ == 4)
      j = i + CodedOutputStream.computeBoolSize(4, ((Boolean)this.kind_).booleanValue()); 
    i = j;
    if (this.kindCase_ == 5)
      i = j + CodedOutputStream.computeMessageSize(5, (Struct)this.kind_); 
    j = i;
    if (this.kindCase_ == 6)
      j = i + CodedOutputStream.computeMessageSize(6, (ListValue)this.kind_); 
    i = j + this.unknownFields.getSerializedSize();
    this.memoizedSize = i;
    return i;
  }
  
  public String getStringValue() {
    Object object = "";
    if (this.kindCase_ == 3)
      object = this.kind_; 
    if (object instanceof String)
      return (String)object; 
    object = ((ByteString)object).toStringUtf8();
    if (this.kindCase_ == 3)
      this.kind_ = object; 
    return (String)object;
  }
  
  public ByteString getStringValueBytes() {
    Object object = "";
    if (this.kindCase_ == 3)
      object = this.kind_; 
    if (object instanceof String) {
      object = ByteString.copyFromUtf8((String)object);
      if (this.kindCase_ == 3)
        this.kind_ = object; 
      return (ByteString)object;
    } 
    return (ByteString)object;
  }
  
  public Struct getStructValue() {
    return (this.kindCase_ == 5) ? (Struct)this.kind_ : Struct.getDefaultInstance();
  }
  
  public StructOrBuilder getStructValueOrBuilder() {
    return (this.kindCase_ == 5) ? (Struct)this.kind_ : Struct.getDefaultInstance();
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  public boolean hasListValue() {
    boolean bool;
    if (this.kindCase_ == 6) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasStructValue() {
    boolean bool;
    if (this.kindCase_ == 5) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int i = 779 + getDescriptor().hashCode();
    switch (this.kindCase_) {
      default:
        i = i * 29 + this.unknownFields.hashCode();
        this.memoizedHashCode = i;
        return i;
      case 6:
        i = (i * 37 + 6) * 53 + getListValue().hashCode();
      case 5:
        i = (i * 37 + 5) * 53 + getStructValue().hashCode();
      case 4:
        i = (i * 37 + 4) * 53 + Internal.hashBoolean(getBoolValue());
      case 3:
        i = (i * 37 + 3) * 53 + getStringValue().hashCode();
      case 2:
        i = (i * 37 + 2) * 53 + Internal.hashLong(Double.doubleToLongBits(getNumberValue()));
      case 1:
        break;
    } 
    i = (i * 37 + 1) * 53 + getNullValueValue();
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Value.class, (Class)Builder.class);
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
    return new Value();
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
    if (this.kindCase_ == 1)
      paramCodedOutputStream.writeEnum(1, ((Integer)this.kind_).intValue()); 
    if (this.kindCase_ == 2)
      paramCodedOutputStream.writeDouble(2, ((Double)this.kind_).doubleValue()); 
    if (this.kindCase_ == 3)
      GeneratedMessageV3.writeString(paramCodedOutputStream, 3, this.kind_); 
    if (this.kindCase_ == 4)
      paramCodedOutputStream.writeBool(4, ((Boolean)this.kind_).booleanValue()); 
    if (this.kindCase_ == 5)
      paramCodedOutputStream.writeMessage(5, (Struct)this.kind_); 
    if (this.kindCase_ == 6)
      paramCodedOutputStream.writeMessage(6, (ListValue)this.kind_); 
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValueOrBuilder {
    private int kindCase_ = 0;
    
    private Object kind_;
    
    private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> listValueBuilder_;
    
    private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> structValueBuilder_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return StructProto.internal_static_google_protobuf_Value_descriptor;
    }
    
    private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> getListValueFieldBuilder() {
      if (this.listValueBuilder_ == null) {
        if (this.kindCase_ != 6)
          this.kind_ = ListValue.getDefaultInstance(); 
        this.listValueBuilder_ = new SingleFieldBuilderV3<>((ListValue)this.kind_, getParentForChildren(), isClean());
        this.kind_ = null;
      } 
      this.kindCase_ = 6;
      onChanged();
      return this.listValueBuilder_;
    }
    
    private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getStructValueFieldBuilder() {
      if (this.structValueBuilder_ == null) {
        if (this.kindCase_ != 5)
          this.kind_ = Struct.getDefaultInstance(); 
        this.structValueBuilder_ = new SingleFieldBuilderV3<>((Struct)this.kind_, getParentForChildren(), isClean());
        this.kind_ = null;
      } 
      this.kindCase_ = 5;
      onChanged();
      return this.structValueBuilder_;
    }
    
    private void maybeForceBuilderInitialization() {
      boolean bool = GeneratedMessageV3.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public Value build() {
      Value value = buildPartial();
      if (!value.isInitialized())
        throw newUninitializedMessageException(value); 
      return value;
    }
    
    public Value buildPartial() {
      Value value = new Value(this);
      if (this.kindCase_ == 1)
        Value.access$302(value, this.kind_); 
      if (this.kindCase_ == 2)
        Value.access$302(value, this.kind_); 
      if (this.kindCase_ == 3)
        Value.access$302(value, this.kind_); 
      if (this.kindCase_ == 4)
        Value.access$302(value, this.kind_); 
      if (this.kindCase_ == 5)
        if (this.structValueBuilder_ == null) {
          Value.access$302(value, this.kind_);
        } else {
          Value.access$302(value, this.structValueBuilder_.build());
        }  
      if (this.kindCase_ == 6)
        if (this.listValueBuilder_ == null) {
          Value.access$302(value, this.kind_);
        } else {
          Value.access$302(value, this.listValueBuilder_.build());
        }  
      Value.access$402(value, this.kindCase_);
      onBuilt();
      return value;
    }
    
    public Builder clear() {
      super.clear();
      this.kindCase_ = 0;
      this.kind_ = null;
      return this;
    }
    
    public Builder clearBoolValue() {
      if (this.kindCase_ == 4) {
        this.kindCase_ = 0;
        this.kind_ = null;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearKind() {
      this.kindCase_ = 0;
      this.kind_ = null;
      onChanged();
      return this;
    }
    
    public Builder clearListValue() {
      if (this.listValueBuilder_ == null) {
        if (this.kindCase_ == 6) {
          this.kindCase_ = 0;
          this.kind_ = null;
          onChanged();
        } 
      } else {
        if (this.kindCase_ == 6) {
          this.kindCase_ = 0;
          this.kind_ = null;
        } 
        this.listValueBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearNullValue() {
      if (this.kindCase_ == 1) {
        this.kindCase_ = 0;
        this.kind_ = null;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearNumberValue() {
      if (this.kindCase_ == 2) {
        this.kindCase_ = 0;
        this.kind_ = null;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearStringValue() {
      if (this.kindCase_ == 3) {
        this.kindCase_ = 0;
        this.kind_ = null;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearStructValue() {
      if (this.structValueBuilder_ == null) {
        if (this.kindCase_ == 5) {
          this.kindCase_ = 0;
          this.kind_ = null;
          onChanged();
        } 
      } else {
        if (this.kindCase_ == 5) {
          this.kindCase_ = 0;
          this.kind_ = null;
        } 
        this.structValueBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public boolean getBoolValue() {
      return (this.kindCase_ == 4) ? ((Boolean)this.kind_).booleanValue() : false;
    }
    
    public Value getDefaultInstanceForType() {
      return Value.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return StructProto.internal_static_google_protobuf_Value_descriptor;
    }
    
    public Value.KindCase getKindCase() {
      return Value.KindCase.forNumber(this.kindCase_);
    }
    
    public ListValue getListValue() {
      return (this.listValueBuilder_ == null) ? ((this.kindCase_ == 6) ? (ListValue)this.kind_ : ListValue.getDefaultInstance()) : ((this.kindCase_ == 6) ? this.listValueBuilder_.getMessage() : ListValue.getDefaultInstance());
    }
    
    public ListValue.Builder getListValueBuilder() {
      return getListValueFieldBuilder().getBuilder();
    }
    
    public ListValueOrBuilder getListValueOrBuilder() {
      return (this.kindCase_ == 6 && this.listValueBuilder_ != null) ? this.listValueBuilder_.getMessageOrBuilder() : ((this.kindCase_ == 6) ? (ListValue)this.kind_ : ListValue.getDefaultInstance());
    }
    
    public NullValue getNullValue() {
      if (this.kindCase_ == 1) {
        NullValue nullValue1 = NullValue.valueOf(((Integer)this.kind_).intValue());
        NullValue nullValue2 = nullValue1;
        if (nullValue1 == null)
          nullValue2 = NullValue.UNRECOGNIZED; 
        return nullValue2;
      } 
      return NullValue.NULL_VALUE;
    }
    
    public int getNullValueValue() {
      return (this.kindCase_ == 1) ? ((Integer)this.kind_).intValue() : 0;
    }
    
    public double getNumberValue() {
      return (this.kindCase_ == 2) ? ((Double)this.kind_).doubleValue() : 0.0D;
    }
    
    public String getStringValue() {
      Object object = "";
      if (this.kindCase_ == 3)
        object = this.kind_; 
      if (!(object instanceof String)) {
        object = ((ByteString)object).toStringUtf8();
        if (this.kindCase_ == 3)
          this.kind_ = object; 
        return (String)object;
      } 
      return (String)object;
    }
    
    public ByteString getStringValueBytes() {
      Object object = "";
      if (this.kindCase_ == 3)
        object = this.kind_; 
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        if (this.kindCase_ == 3)
          this.kind_ = object; 
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public Struct getStructValue() {
      return (this.structValueBuilder_ == null) ? ((this.kindCase_ == 5) ? (Struct)this.kind_ : Struct.getDefaultInstance()) : ((this.kindCase_ == 5) ? this.structValueBuilder_.getMessage() : Struct.getDefaultInstance());
    }
    
    public Struct.Builder getStructValueBuilder() {
      return getStructValueFieldBuilder().getBuilder();
    }
    
    public StructOrBuilder getStructValueOrBuilder() {
      return (this.kindCase_ == 5 && this.structValueBuilder_ != null) ? this.structValueBuilder_.getMessageOrBuilder() : ((this.kindCase_ == 5) ? (Struct)this.kind_ : Struct.getDefaultInstance());
    }
    
    public boolean hasListValue() {
      boolean bool;
      if (this.kindCase_ == 6) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasStructValue() {
      boolean bool;
      if (this.kindCase_ == 5) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Value.class, (Class)Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      CodedInputStream codedInputStream = null;
      try {
        Value value = Value.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        Value value = (Value)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1ExtensionRegistryLite = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((Value)param1CodedInputStream); 
      throw param1ExtensionRegistryLite;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof Value)
        return mergeFrom((Value)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(Value param1Value) {
      if (param1Value == Value.getDefaultInstance())
        return this; 
      switch (param1Value.getKindCase()) {
        default:
          mergeUnknownFields(param1Value.unknownFields);
          onChanged();
          return this;
        case LIST_VALUE:
          mergeListValue(param1Value.getListValue());
        case STRUCT_VALUE:
          mergeStructValue(param1Value.getStructValue());
        case BOOL_VALUE:
          setBoolValue(param1Value.getBoolValue());
        case STRING_VALUE:
          this.kindCase_ = 3;
          this.kind_ = param1Value.kind_;
          onChanged();
        case NUMBER_VALUE:
          setNumberValue(param1Value.getNumberValue());
        case NULL_VALUE:
          break;
      } 
      setNullValueValue(param1Value.getNullValueValue());
    }
    
    public Builder mergeListValue(ListValue param1ListValue) {
      if (this.listValueBuilder_ == null) {
        if (this.kindCase_ == 6 && this.kind_ != ListValue.getDefaultInstance()) {
          this.kind_ = ListValue.newBuilder((ListValue)this.kind_).mergeFrom(param1ListValue).buildPartial();
        } else {
          this.kind_ = param1ListValue;
        } 
        onChanged();
      } else {
        if (this.kindCase_ == 6)
          this.listValueBuilder_.mergeFrom(param1ListValue); 
        this.listValueBuilder_.setMessage(param1ListValue);
      } 
      this.kindCase_ = 6;
      return this;
    }
    
    public Builder mergeStructValue(Struct param1Struct) {
      if (this.structValueBuilder_ == null) {
        if (this.kindCase_ == 5 && this.kind_ != Struct.getDefaultInstance()) {
          this.kind_ = Struct.newBuilder((Struct)this.kind_).mergeFrom(param1Struct).buildPartial();
        } else {
          this.kind_ = param1Struct;
        } 
        onChanged();
      } else {
        if (this.kindCase_ == 5)
          this.structValueBuilder_.mergeFrom(param1Struct); 
        this.structValueBuilder_.setMessage(param1Struct);
      } 
      this.kindCase_ = 5;
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setBoolValue(boolean param1Boolean) {
      this.kindCase_ = 4;
      this.kind_ = Boolean.valueOf(param1Boolean);
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setListValue(ListValue.Builder param1Builder) {
      if (this.listValueBuilder_ == null) {
        this.kind_ = param1Builder.build();
        onChanged();
      } else {
        this.listValueBuilder_.setMessage(param1Builder.build());
      } 
      this.kindCase_ = 6;
      return this;
    }
    
    public Builder setListValue(ListValue param1ListValue) {
      if (this.listValueBuilder_ == null) {
        if (param1ListValue == null)
          throw new NullPointerException(); 
        this.kind_ = param1ListValue;
        onChanged();
      } else {
        this.listValueBuilder_.setMessage(param1ListValue);
      } 
      this.kindCase_ = 6;
      return this;
    }
    
    public Builder setNullValue(NullValue param1NullValue) {
      if (param1NullValue == null)
        throw new NullPointerException(); 
      this.kindCase_ = 1;
      this.kind_ = Integer.valueOf(param1NullValue.getNumber());
      onChanged();
      return this;
    }
    
    public Builder setNullValueValue(int param1Int) {
      this.kindCase_ = 1;
      this.kind_ = Integer.valueOf(param1Int);
      onChanged();
      return this;
    }
    
    public Builder setNumberValue(double param1Double) {
      this.kindCase_ = 2;
      this.kind_ = Double.valueOf(param1Double);
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public Builder setStringValue(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.kindCase_ = 3;
      this.kind_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setStringValueBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      AbstractMessageLite.checkByteStringIsUtf8(param1ByteString);
      this.kindCase_ = 3;
      this.kind_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setStructValue(Struct.Builder param1Builder) {
      if (this.structValueBuilder_ == null) {
        this.kind_ = param1Builder.build();
        onChanged();
      } else {
        this.structValueBuilder_.setMessage(param1Builder.build());
      } 
      this.kindCase_ = 5;
      return this;
    }
    
    public Builder setStructValue(Struct param1Struct) {
      if (this.structValueBuilder_ == null) {
        if (param1Struct == null)
          throw new NullPointerException(); 
        this.kind_ = param1Struct;
        onChanged();
      } else {
        this.structValueBuilder_.setMessage(param1Struct);
      } 
      this.kindCase_ = 5;
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public enum KindCase implements Internal.EnumLite {
    BOOL_VALUE,
    KIND_NOT_SET,
    LIST_VALUE,
    NULL_VALUE(1),
    NUMBER_VALUE(2),
    STRING_VALUE(3),
    STRUCT_VALUE(3);
    
    private final int value;
    
    static {
      LIST_VALUE = new KindCase("LIST_VALUE", 5, 6);
      KIND_NOT_SET = new KindCase("KIND_NOT_SET", 6, 0);
      $VALUES = new KindCase[] { NULL_VALUE, NUMBER_VALUE, STRING_VALUE, BOOL_VALUE, STRUCT_VALUE, LIST_VALUE, KIND_NOT_SET };
    }
    
    KindCase(int param1Int1) {
      this.value = param1Int1;
    }
    
    public static KindCase forNumber(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 6:
          return LIST_VALUE;
        case 5:
          return STRUCT_VALUE;
        case 4:
          return BOOL_VALUE;
        case 3:
          return STRING_VALUE;
        case 2:
          return NUMBER_VALUE;
        case 1:
          return NULL_VALUE;
        case 0:
          break;
      } 
      return KIND_NOT_SET;
    }
    
    public int getNumber() {
      return this.value;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Value.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */