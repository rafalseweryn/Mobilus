package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListValue extends GeneratedMessageV3 implements ListValueOrBuilder {
  private static final ListValue DEFAULT_INSTANCE = new ListValue();
  
  private static final Parser<ListValue> PARSER = new AbstractParser<ListValue>() {
      public ListValue parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new ListValue(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  public static final int VALUES_FIELD_NUMBER = 1;
  
  private static final long serialVersionUID = 0L;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private List<Value> values_;
  
  private ListValue() {
    this.values_ = Collections.emptyList();
  }
  
  private ListValue(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    this();
    if (paramExtensionRegistryLite == null)
      throw new NullPointerException(); 
    UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
    boolean bool = false;
    int i = 0;
    while (!bool) {
      int j = i;
      int k = i;
      int m = i;
      try {
        int n = paramCodedInputStream.readTag();
        if (n != 0) {
          if (n != 10) {
            j = i;
            k = i;
            m = i;
            if (!parseUnknownField(paramCodedInputStream, builder, paramExtensionRegistryLite, n))
              continue; 
            continue;
          } 
          n = i;
          if ((i & 0x1) == 0) {
            j = i;
            k = i;
            m = i;
            ArrayList<Value> arrayList = new ArrayList();
            j = i;
            k = i;
            m = i;
            this();
            j = i;
            k = i;
            m = i;
            this.values_ = arrayList;
            n = i | 0x1;
          } 
          j = n;
          k = n;
          m = n;
          this.values_.add(paramCodedInputStream.readMessage(Value.parser(), paramExtensionRegistryLite));
          i = n;
          continue;
        } 
        continue;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        j = m;
        throw invalidProtocolBufferException.setUnfinishedMessage(this);
      } catch (IOException iOException) {
        j = k;
        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
        j = k;
        this(iOException);
        j = k;
        throw invalidProtocolBufferException.setUnfinishedMessage(this);
      } finally {}
      if ((j & 0x1) != 0)
        this.values_ = Collections.unmodifiableList(this.values_); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
      throw paramCodedInputStream;
      bool = true;
    } 
    if ((i & 0x1) != 0)
      this.values_ = Collections.unmodifiableList(this.values_); 
    this.unknownFields = builder.build();
    makeExtensionsImmutable();
  }
  
  private ListValue(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static ListValue getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return StructProto.internal_static_google_protobuf_ListValue_descriptor;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(ListValue paramListValue) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramListValue);
  }
  
  public static ListValue parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<ListValue>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static ListValue parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<ListValue>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static ListValue parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static ListValue parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static ListValue parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static ListValue parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static ListValue parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static ListValue parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static ListValue parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static ListValue parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static ListValue parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static ListValue parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<ListValue> parser() {
    return PARSER;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof ListValue))
      return super.equals(paramObject); 
    paramObject = paramObject;
    return !getValuesList().equals(paramObject.getValuesList()) ? false : (!!this.unknownFields.equals(((ListValue)paramObject).unknownFields));
  }
  
  public ListValue getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public Parser<ListValue> getParserForType() {
    return PARSER;
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    byte b = 0;
    i = 0;
    while (b < this.values_.size()) {
      i += CodedOutputStream.computeMessageSize(1, this.values_.get(b));
      b++;
    } 
    i += this.unknownFields.getSerializedSize();
    this.memoizedSize = i;
    return i;
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  public Value getValues(int paramInt) {
    return this.values_.get(paramInt);
  }
  
  public int getValuesCount() {
    return this.values_.size();
  }
  
  public List<Value> getValuesList() {
    return this.values_;
  }
  
  public ValueOrBuilder getValuesOrBuilder(int paramInt) {
    return this.values_.get(paramInt);
  }
  
  public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
    return (List)this.values_;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int i = 779 + getDescriptor().hashCode();
    int j = i;
    if (getValuesCount() > 0)
      j = (i * 37 + 1) * 53 + getValuesList().hashCode(); 
    j = j * 29 + this.unknownFields.hashCode();
    this.memoizedHashCode = j;
    return j;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ListValue.class, (Class)Builder.class);
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
    return new ListValue();
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
    for (byte b = 0; b < this.values_.size(); b++)
      paramCodedOutputStream.writeMessage(1, this.values_.get(b)); 
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListValueOrBuilder {
    private int bitField0_;
    
    private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> valuesBuilder_;
    
    private List<Value> values_ = Collections.emptyList();
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureValuesIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.values_ = new ArrayList<>(this.values_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return StructProto.internal_static_google_protobuf_ListValue_descriptor;
    }
    
    private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> getValuesFieldBuilder() {
      if (this.valuesBuilder_ == null) {
        List<Value> list = this.values_;
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        this.valuesBuilder_ = new RepeatedFieldBuilderV3<>(list, bool, getParentForChildren(), isClean());
        this.values_ = null;
      } 
      return this.valuesBuilder_;
    }
    
    private void maybeForceBuilderInitialization() {
      if (GeneratedMessageV3.alwaysUseFieldBuilders)
        getValuesFieldBuilder(); 
    }
    
    public Builder addAllValues(Iterable<? extends Value> param1Iterable) {
      if (this.valuesBuilder_ == null) {
        ensureValuesIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.values_);
        onChanged();
      } else {
        this.valuesBuilder_.addAllMessages(param1Iterable);
      } 
      return this;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public Builder addValues(int param1Int, Value.Builder param1Builder) {
      if (this.valuesBuilder_ == null) {
        ensureValuesIsMutable();
        this.values_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.valuesBuilder_.addMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder addValues(int param1Int, Value param1Value) {
      if (this.valuesBuilder_ == null) {
        if (param1Value == null)
          throw new NullPointerException(); 
        ensureValuesIsMutable();
        this.values_.add(param1Int, param1Value);
        onChanged();
      } else {
        this.valuesBuilder_.addMessage(param1Int, param1Value);
      } 
      return this;
    }
    
    public Builder addValues(Value.Builder param1Builder) {
      if (this.valuesBuilder_ == null) {
        ensureValuesIsMutable();
        this.values_.add(param1Builder.build());
        onChanged();
      } else {
        this.valuesBuilder_.addMessage(param1Builder.build());
      } 
      return this;
    }
    
    public Builder addValues(Value param1Value) {
      if (this.valuesBuilder_ == null) {
        if (param1Value == null)
          throw new NullPointerException(); 
        ensureValuesIsMutable();
        this.values_.add(param1Value);
        onChanged();
      } else {
        this.valuesBuilder_.addMessage(param1Value);
      } 
      return this;
    }
    
    public Value.Builder addValuesBuilder() {
      return getValuesFieldBuilder().addBuilder(Value.getDefaultInstance());
    }
    
    public Value.Builder addValuesBuilder(int param1Int) {
      return getValuesFieldBuilder().addBuilder(param1Int, Value.getDefaultInstance());
    }
    
    public ListValue build() {
      ListValue listValue = buildPartial();
      if (!listValue.isInitialized())
        throw newUninitializedMessageException(listValue); 
      return listValue;
    }
    
    public ListValue buildPartial() {
      ListValue listValue = new ListValue(this);
      int i = this.bitField0_;
      if (this.valuesBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0) {
          this.values_ = Collections.unmodifiableList(this.values_);
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        ListValue.access$302(listValue, this.values_);
      } else {
        ListValue.access$302(listValue, this.valuesBuilder_.build());
      } 
      onBuilt();
      return listValue;
    }
    
    public Builder clear() {
      super.clear();
      if (this.valuesBuilder_ == null) {
        this.values_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
      } else {
        this.valuesBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearValues() {
      if (this.valuesBuilder_ == null) {
        this.values_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
      } else {
        this.valuesBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public ListValue getDefaultInstanceForType() {
      return ListValue.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return StructProto.internal_static_google_protobuf_ListValue_descriptor;
    }
    
    public Value getValues(int param1Int) {
      return (this.valuesBuilder_ == null) ? this.values_.get(param1Int) : this.valuesBuilder_.getMessage(param1Int);
    }
    
    public Value.Builder getValuesBuilder(int param1Int) {
      return getValuesFieldBuilder().getBuilder(param1Int);
    }
    
    public List<Value.Builder> getValuesBuilderList() {
      return getValuesFieldBuilder().getBuilderList();
    }
    
    public int getValuesCount() {
      return (this.valuesBuilder_ == null) ? this.values_.size() : this.valuesBuilder_.getCount();
    }
    
    public List<Value> getValuesList() {
      return (this.valuesBuilder_ == null) ? Collections.unmodifiableList(this.values_) : this.valuesBuilder_.getMessageList();
    }
    
    public ValueOrBuilder getValuesOrBuilder(int param1Int) {
      return (this.valuesBuilder_ == null) ? this.values_.get(param1Int) : this.valuesBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
      return (this.valuesBuilder_ != null) ? this.valuesBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.values_);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ListValue.class, (Class)Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      CodedInputStream codedInputStream = null;
      try {
        ListValue listValue = ListValue.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        ListValue listValue = (ListValue)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1ExtensionRegistryLite = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((ListValue)param1CodedInputStream); 
      throw param1ExtensionRegistryLite;
    }
    
    public Builder mergeFrom(ListValue param1ListValue) {
      if (param1ListValue == ListValue.getDefaultInstance())
        return this; 
      if (this.valuesBuilder_ == null) {
        if (!param1ListValue.values_.isEmpty()) {
          if (this.values_.isEmpty()) {
            this.values_ = param1ListValue.values_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensureValuesIsMutable();
            this.values_.addAll(param1ListValue.values_);
          } 
          onChanged();
        } 
      } else if (!param1ListValue.values_.isEmpty()) {
        if (this.valuesBuilder_.isEmpty()) {
          this.valuesBuilder_.dispose();
          RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = null;
          this.valuesBuilder_ = null;
          this.values_ = param1ListValue.values_;
          this.bitField0_ &= 0xFFFFFFFE;
          if (GeneratedMessageV3.alwaysUseFieldBuilders)
            repeatedFieldBuilderV3 = getValuesFieldBuilder(); 
          this.valuesBuilder_ = repeatedFieldBuilderV3;
        } else {
          this.valuesBuilder_.addAllMessages(param1ListValue.values_);
        } 
      } 
      mergeUnknownFields(param1ListValue.unknownFields);
      onChanged();
      return this;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof ListValue)
        return mergeFrom((ListValue)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder removeValues(int param1Int) {
      if (this.valuesBuilder_ == null) {
        ensureValuesIsMutable();
        this.values_.remove(param1Int);
        onChanged();
      } else {
        this.valuesBuilder_.remove(param1Int);
      } 
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
    
    public Builder setValues(int param1Int, Value.Builder param1Builder) {
      if (this.valuesBuilder_ == null) {
        ensureValuesIsMutable();
        this.values_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.valuesBuilder_.setMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder setValues(int param1Int, Value param1Value) {
      if (this.valuesBuilder_ == null) {
        if (param1Value == null)
          throw new NullPointerException(); 
        ensureValuesIsMutable();
        this.values_.set(param1Int, param1Value);
        onChanged();
      } else {
        this.valuesBuilder_.setMessage(param1Int, param1Value);
      } 
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ListValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */