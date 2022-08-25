package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class FieldMask extends GeneratedMessageV3 implements FieldMaskOrBuilder {
  private static final FieldMask DEFAULT_INSTANCE = new FieldMask();
  
  private static final Parser<FieldMask> PARSER = new AbstractParser<FieldMask>() {
      public FieldMask parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new FieldMask(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  public static final int PATHS_FIELD_NUMBER = 1;
  
  private static final long serialVersionUID = 0L;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private LazyStringList paths_;
  
  private FieldMask() {
    this.paths_ = LazyStringArrayList.EMPTY;
  }
  
  private FieldMask(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
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
          j = i;
          k = i;
          m = i;
          String str = paramCodedInputStream.readStringRequireUtf8();
          n = i;
          if ((i & 0x1) == 0) {
            j = i;
            k = i;
            m = i;
            LazyStringArrayList lazyStringArrayList = new LazyStringArrayList();
            j = i;
            k = i;
            m = i;
            this();
            j = i;
            k = i;
            m = i;
            this.paths_ = lazyStringArrayList;
            n = i | 0x1;
          } 
          j = n;
          k = n;
          m = n;
          this.paths_.add(str);
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
        this.paths_ = this.paths_.getUnmodifiableView(); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
      throw paramCodedInputStream;
      bool = true;
    } 
    if ((i & 0x1) != 0)
      this.paths_ = this.paths_.getUnmodifiableView(); 
    this.unknownFields = builder.build();
    makeExtensionsImmutable();
  }
  
  private FieldMask(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static FieldMask getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(FieldMask paramFieldMask) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramFieldMask);
  }
  
  public static FieldMask parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<FieldMask>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static FieldMask parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<FieldMask>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static FieldMask parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static FieldMask parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static FieldMask parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<FieldMask>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static FieldMask parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<FieldMask>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static FieldMask parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<FieldMask>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static FieldMask parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<FieldMask>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static FieldMask parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static FieldMask parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static FieldMask parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static FieldMask parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<FieldMask> parser() {
    return PARSER;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof FieldMask))
      return super.equals(paramObject); 
    paramObject = paramObject;
    return !getPathsList().equals(paramObject.getPathsList()) ? false : (!!this.unknownFields.equals(((FieldMask)paramObject).unknownFields));
  }
  
  public FieldMask getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public Parser<FieldMask> getParserForType() {
    return PARSER;
  }
  
  public String getPaths(int paramInt) {
    return this.paths_.get(paramInt);
  }
  
  public ByteString getPathsBytes(int paramInt) {
    return this.paths_.getByteString(paramInt);
  }
  
  public int getPathsCount() {
    return this.paths_.size();
  }
  
  public ProtocolStringList getPathsList() {
    return this.paths_;
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    byte b = 0;
    i = b;
    while (b < this.paths_.size()) {
      i += computeStringSizeNoTag(this.paths_.getRaw(b));
      b++;
    } 
    i = i + 0 + getPathsList().size() * 1 + this.unknownFields.getSerializedSize();
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
    if (getPathsCount() > 0)
      j = (i * 37 + 1) * 53 + getPathsList().hashCode(); 
    j = j * 29 + this.unknownFields.hashCode();
    this.memoizedHashCode = j;
    return j;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return FieldMaskProto.internal_static_google_protobuf_FieldMask_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)FieldMask.class, (Class)Builder.class);
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
    return new FieldMask();
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
    for (byte b = 0; b < this.paths_.size(); b++)
      GeneratedMessageV3.writeString(paramCodedOutputStream, 1, this.paths_.getRaw(b)); 
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FieldMaskOrBuilder {
    private int bitField0_;
    
    private LazyStringList paths_ = LazyStringArrayList.EMPTY;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensurePathsIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.paths_ = new LazyStringArrayList(this.paths_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      boolean bool = GeneratedMessageV3.alwaysUseFieldBuilders;
    }
    
    public Builder addAllPaths(Iterable<String> param1Iterable) {
      ensurePathsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, this.paths_);
      onChanged();
      return this;
    }
    
    public Builder addPaths(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      ensurePathsIsMutable();
      this.paths_.add(param1String);
      onChanged();
      return this;
    }
    
    public Builder addPathsBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      AbstractMessageLite.checkByteStringIsUtf8(param1ByteString);
      ensurePathsIsMutable();
      this.paths_.add(param1ByteString);
      onChanged();
      return this;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public FieldMask build() {
      FieldMask fieldMask = buildPartial();
      if (!fieldMask.isInitialized())
        throw newUninitializedMessageException(fieldMask); 
      return fieldMask;
    }
    
    public FieldMask buildPartial() {
      FieldMask fieldMask = new FieldMask(this);
      int i = this.bitField0_;
      if ((this.bitField0_ & 0x1) != 0) {
        this.paths_ = this.paths_.getUnmodifiableView();
        this.bitField0_ &= 0xFFFFFFFE;
      } 
      FieldMask.access$302(fieldMask, this.paths_);
      onBuilt();
      return fieldMask;
    }
    
    public Builder clear() {
      super.clear();
      this.paths_ = LazyStringArrayList.EMPTY;
      this.bitField0_ &= 0xFFFFFFFE;
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearPaths() {
      this.paths_ = LazyStringArrayList.EMPTY;
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public FieldMask getDefaultInstanceForType() {
      return FieldMask.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
    }
    
    public String getPaths(int param1Int) {
      return this.paths_.get(param1Int);
    }
    
    public ByteString getPathsBytes(int param1Int) {
      return this.paths_.getByteString(param1Int);
    }
    
    public int getPathsCount() {
      return this.paths_.size();
    }
    
    public ProtocolStringList getPathsList() {
      return this.paths_.getUnmodifiableView();
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return FieldMaskProto.internal_static_google_protobuf_FieldMask_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)FieldMask.class, (Class)Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        FieldMask fieldMask = FieldMask.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        FieldMask fieldMask = (FieldMask)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((FieldMask)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(FieldMask param1FieldMask) {
      if (param1FieldMask == FieldMask.getDefaultInstance())
        return this; 
      if (!param1FieldMask.paths_.isEmpty()) {
        if (this.paths_.isEmpty()) {
          this.paths_ = param1FieldMask.paths_;
          this.bitField0_ &= 0xFFFFFFFE;
        } else {
          ensurePathsIsMutable();
          this.paths_.addAll(param1FieldMask.paths_);
        } 
        onChanged();
      } 
      mergeUnknownFields(param1FieldMask.unknownFields);
      onChanged();
      return this;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof FieldMask)
        return mergeFrom((FieldMask)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setPaths(int param1Int, String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      ensurePathsIsMutable();
      this.paths_.set(param1Int, param1String);
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.setUnknownFields(param1UnknownFieldSet);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\FieldMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */