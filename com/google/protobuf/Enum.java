package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Enum extends GeneratedMessageV3 implements EnumOrBuilder {
  private static final Enum DEFAULT_INSTANCE = new Enum();
  
  public static final int ENUMVALUE_FIELD_NUMBER = 2;
  
  public static final int NAME_FIELD_NUMBER = 1;
  
  public static final int OPTIONS_FIELD_NUMBER = 3;
  
  private static final Parser<Enum> PARSER = new AbstractParser<Enum>() {
      public Enum parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new Enum(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  public static final int SOURCE_CONTEXT_FIELD_NUMBER = 4;
  
  public static final int SYNTAX_FIELD_NUMBER = 5;
  
  private static final long serialVersionUID = 0L;
  
  private List<EnumValue> enumvalue_;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private volatile Object name_;
  
  private List<Option> options_;
  
  private SourceContext sourceContext_;
  
  private int syntax_;
  
  private Enum() {
    this.name_ = "";
    this.enumvalue_ = Collections.emptyList();
    this.options_ = Collections.emptyList();
    this.syntax_ = 0;
  }
  
  private Enum(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
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
            if (n != 18) {
              if (n != 26) {
                if (n != 34) {
                  if (n != 40) {
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
                  this.syntax_ = paramCodedInputStream.readEnum();
                  continue;
                } 
                SourceContext.Builder builder1 = null;
                j = i;
                k = i;
                m = i;
                if (this.sourceContext_ != null) {
                  j = i;
                  k = i;
                  m = i;
                  builder1 = this.sourceContext_.toBuilder();
                } 
                j = i;
                k = i;
                m = i;
                this.sourceContext_ = paramCodedInputStream.<SourceContext>readMessage(SourceContext.parser(), paramExtensionRegistryLite);
                if (builder1 != null) {
                  j = i;
                  k = i;
                  m = i;
                  builder1.mergeFrom(this.sourceContext_);
                  j = i;
                  k = i;
                  m = i;
                  this.sourceContext_ = builder1.buildPartial();
                } 
                continue;
              } 
              n = i;
              if ((i & 0x2) == 0) {
                j = i;
                k = i;
                m = i;
                ArrayList<Option> arrayList = new ArrayList();
                j = i;
                k = i;
                m = i;
                this();
                j = i;
                k = i;
                m = i;
                this.options_ = arrayList;
                n = i | 0x2;
              } 
              j = n;
              k = n;
              m = n;
              this.options_.add(paramCodedInputStream.readMessage(Option.parser(), paramExtensionRegistryLite));
              i = n;
              continue;
            } 
            n = i;
            if ((i & 0x1) == 0) {
              j = i;
              k = i;
              m = i;
              ArrayList<EnumValue> arrayList = new ArrayList();
              j = i;
              k = i;
              m = i;
              this();
              j = i;
              k = i;
              m = i;
              this.enumvalue_ = arrayList;
              n = i | 0x1;
            } 
            j = n;
            k = n;
            m = n;
            this.enumvalue_.add(paramCodedInputStream.readMessage(EnumValue.parser(), paramExtensionRegistryLite));
            i = n;
            continue;
          } 
          j = i;
          k = i;
          m = i;
          this.name_ = paramCodedInputStream.readStringRequireUtf8();
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
        this.enumvalue_ = Collections.unmodifiableList(this.enumvalue_); 
      if ((j & 0x2) != 0)
        this.options_ = Collections.unmodifiableList(this.options_); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
      throw paramCodedInputStream;
      bool = true;
    } 
    if ((i & 0x1) != 0)
      this.enumvalue_ = Collections.unmodifiableList(this.enumvalue_); 
    if ((i & 0x2) != 0)
      this.options_ = Collections.unmodifiableList(this.options_); 
    this.unknownFields = builder.build();
    makeExtensionsImmutable();
  }
  
  private Enum(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static Enum getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return TypeProto.internal_static_google_protobuf_Enum_descriptor;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Enum paramEnum) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramEnum);
  }
  
  public static Enum parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Enum>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static Enum parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Enum>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Enum parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static Enum parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static Enum parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<Enum>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static Enum parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Enum>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static Enum parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Enum>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static Enum parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Enum>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Enum parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static Enum parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static Enum parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static Enum parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<Enum> parser() {
    return PARSER;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Enum))
      return super.equals(paramObject); 
    paramObject = paramObject;
    return !getName().equals(paramObject.getName()) ? false : (!getEnumvalueList().equals(paramObject.getEnumvalueList()) ? false : (!getOptionsList().equals(paramObject.getOptionsList()) ? false : ((hasSourceContext() != paramObject.hasSourceContext()) ? false : ((hasSourceContext() && !getSourceContext().equals(paramObject.getSourceContext())) ? false : ((this.syntax_ != ((Enum)paramObject).syntax_) ? false : (!!this.unknownFields.equals(((Enum)paramObject).unknownFields)))))));
  }
  
  public Enum getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public EnumValue getEnumvalue(int paramInt) {
    return this.enumvalue_.get(paramInt);
  }
  
  public int getEnumvalueCount() {
    return this.enumvalue_.size();
  }
  
  public List<EnumValue> getEnumvalueList() {
    return this.enumvalue_;
  }
  
  public EnumValueOrBuilder getEnumvalueOrBuilder(int paramInt) {
    return this.enumvalue_.get(paramInt);
  }
  
  public List<? extends EnumValueOrBuilder> getEnumvalueOrBuilderList() {
    return (List)this.enumvalue_;
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
  
  public Option getOptions(int paramInt) {
    return this.options_.get(paramInt);
  }
  
  public int getOptionsCount() {
    return this.options_.size();
  }
  
  public List<Option> getOptionsList() {
    return this.options_;
  }
  
  public OptionOrBuilder getOptionsOrBuilder(int paramInt) {
    return this.options_.get(paramInt);
  }
  
  public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
    return (List)this.options_;
  }
  
  public Parser<Enum> getParserForType() {
    return PARSER;
  }
  
  public int getSerializedSize() {
    byte b3;
    int i = this.memoizedSize;
    if (i != -1)
      return i; 
    boolean bool = getNameBytes().isEmpty();
    byte b1 = 0;
    if (!bool) {
      i = GeneratedMessageV3.computeStringSize(1, this.name_) + 0;
    } else {
      i = 0;
    } 
    byte b2 = 0;
    while (true) {
      b3 = b1;
      j = i;
      if (b2 < this.enumvalue_.size()) {
        i += CodedOutputStream.computeMessageSize(2, this.enumvalue_.get(b2));
        b2++;
        continue;
      } 
      break;
    } 
    while (b3 < this.options_.size()) {
      j += CodedOutputStream.computeMessageSize(3, this.options_.get(b3));
      b3++;
    } 
    i = j;
    if (this.sourceContext_ != null)
      i = j + CodedOutputStream.computeMessageSize(4, getSourceContext()); 
    int j = i;
    if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber())
      j = i + CodedOutputStream.computeEnumSize(5, this.syntax_); 
    i = j + this.unknownFields.getSerializedSize();
    this.memoizedSize = i;
    return i;
  }
  
  public SourceContext getSourceContext() {
    SourceContext sourceContext;
    if (this.sourceContext_ == null) {
      sourceContext = SourceContext.getDefaultInstance();
    } else {
      sourceContext = this.sourceContext_;
    } 
    return sourceContext;
  }
  
  public SourceContextOrBuilder getSourceContextOrBuilder() {
    return getSourceContext();
  }
  
  public Syntax getSyntax() {
    Syntax syntax1 = Syntax.valueOf(this.syntax_);
    Syntax syntax2 = syntax1;
    if (syntax1 == null)
      syntax2 = Syntax.UNRECOGNIZED; 
    return syntax2;
  }
  
  public int getSyntaxValue() {
    return this.syntax_;
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  public boolean hasSourceContext() {
    boolean bool;
    if (this.sourceContext_ != null) {
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
    if (getEnumvalueCount() > 0)
      j = (i * 37 + 2) * 53 + getEnumvalueList().hashCode(); 
    i = j;
    if (getOptionsCount() > 0)
      i = (j * 37 + 3) * 53 + getOptionsList().hashCode(); 
    j = i;
    if (hasSourceContext())
      j = (i * 37 + 4) * 53 + getSourceContext().hashCode(); 
    j = ((j * 37 + 5) * 53 + this.syntax_) * 29 + this.unknownFields.hashCode();
    this.memoizedHashCode = j;
    return j;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return TypeProto.internal_static_google_protobuf_Enum_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Enum.class, (Class)Builder.class);
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
    return new Enum();
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
    byte b3;
    if (!getNameBytes().isEmpty())
      GeneratedMessageV3.writeString(paramCodedOutputStream, 1, this.name_); 
    byte b1 = 0;
    byte b2 = 0;
    while (true) {
      b3 = b1;
      if (b2 < this.enumvalue_.size()) {
        paramCodedOutputStream.writeMessage(2, this.enumvalue_.get(b2));
        b2++;
        continue;
      } 
      break;
    } 
    while (b3 < this.options_.size()) {
      paramCodedOutputStream.writeMessage(3, this.options_.get(b3));
      b3++;
    } 
    if (this.sourceContext_ != null)
      paramCodedOutputStream.writeMessage(4, getSourceContext()); 
    if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber())
      paramCodedOutputStream.writeEnum(5, this.syntax_); 
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnumOrBuilder {
    private int bitField0_;
    
    private RepeatedFieldBuilderV3<EnumValue, EnumValue.Builder, EnumValueOrBuilder> enumvalueBuilder_;
    
    private List<EnumValue> enumvalue_ = Collections.emptyList();
    
    private Object name_ = "";
    
    private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> optionsBuilder_;
    
    private List<Option> options_ = Collections.emptyList();
    
    private SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
    
    private SourceContext sourceContext_;
    
    private int syntax_ = 0;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureEnumvalueIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.enumvalue_ = new ArrayList<>(this.enumvalue_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    private void ensureOptionsIsMutable() {
      if ((this.bitField0_ & 0x2) == 0) {
        this.options_ = new ArrayList<>(this.options_);
        this.bitField0_ |= 0x2;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return TypeProto.internal_static_google_protobuf_Enum_descriptor;
    }
    
    private RepeatedFieldBuilderV3<EnumValue, EnumValue.Builder, EnumValueOrBuilder> getEnumvalueFieldBuilder() {
      if (this.enumvalueBuilder_ == null) {
        List<EnumValue> list = this.enumvalue_;
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        this.enumvalueBuilder_ = new RepeatedFieldBuilderV3<>(list, bool, getParentForChildren(), isClean());
        this.enumvalue_ = null;
      } 
      return this.enumvalueBuilder_;
    }
    
    private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> getOptionsFieldBuilder() {
      if (this.optionsBuilder_ == null) {
        boolean bool;
        List<Option> list = this.options_;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        this.optionsBuilder_ = new RepeatedFieldBuilderV3<>(list, bool, getParentForChildren(), isClean());
        this.options_ = null;
      } 
      return this.optionsBuilder_;
    }
    
    private SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> getSourceContextFieldBuilder() {
      if (this.sourceContextBuilder_ == null) {
        this.sourceContextBuilder_ = new SingleFieldBuilderV3<>(getSourceContext(), getParentForChildren(), isClean());
        this.sourceContext_ = null;
      } 
      return this.sourceContextBuilder_;
    }
    
    private void maybeForceBuilderInitialization() {
      if (GeneratedMessageV3.alwaysUseFieldBuilders) {
        getEnumvalueFieldBuilder();
        getOptionsFieldBuilder();
      } 
    }
    
    public Builder addAllEnumvalue(Iterable<? extends EnumValue> param1Iterable) {
      if (this.enumvalueBuilder_ == null) {
        ensureEnumvalueIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.enumvalue_);
        onChanged();
      } else {
        this.enumvalueBuilder_.addAllMessages(param1Iterable);
      } 
      return this;
    }
    
    public Builder addAllOptions(Iterable<? extends Option> param1Iterable) {
      if (this.optionsBuilder_ == null) {
        ensureOptionsIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.options_);
        onChanged();
      } else {
        this.optionsBuilder_.addAllMessages(param1Iterable);
      } 
      return this;
    }
    
    public Builder addEnumvalue(int param1Int, EnumValue.Builder param1Builder) {
      if (this.enumvalueBuilder_ == null) {
        ensureEnumvalueIsMutable();
        this.enumvalue_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.enumvalueBuilder_.addMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder addEnumvalue(int param1Int, EnumValue param1EnumValue) {
      if (this.enumvalueBuilder_ == null) {
        if (param1EnumValue == null)
          throw new NullPointerException(); 
        ensureEnumvalueIsMutable();
        this.enumvalue_.add(param1Int, param1EnumValue);
        onChanged();
      } else {
        this.enumvalueBuilder_.addMessage(param1Int, param1EnumValue);
      } 
      return this;
    }
    
    public Builder addEnumvalue(EnumValue.Builder param1Builder) {
      if (this.enumvalueBuilder_ == null) {
        ensureEnumvalueIsMutable();
        this.enumvalue_.add(param1Builder.build());
        onChanged();
      } else {
        this.enumvalueBuilder_.addMessage(param1Builder.build());
      } 
      return this;
    }
    
    public Builder addEnumvalue(EnumValue param1EnumValue) {
      if (this.enumvalueBuilder_ == null) {
        if (param1EnumValue == null)
          throw new NullPointerException(); 
        ensureEnumvalueIsMutable();
        this.enumvalue_.add(param1EnumValue);
        onChanged();
      } else {
        this.enumvalueBuilder_.addMessage(param1EnumValue);
      } 
      return this;
    }
    
    public EnumValue.Builder addEnumvalueBuilder() {
      return getEnumvalueFieldBuilder().addBuilder(EnumValue.getDefaultInstance());
    }
    
    public EnumValue.Builder addEnumvalueBuilder(int param1Int) {
      return getEnumvalueFieldBuilder().addBuilder(param1Int, EnumValue.getDefaultInstance());
    }
    
    public Builder addOptions(int param1Int, Option.Builder param1Builder) {
      if (this.optionsBuilder_ == null) {
        ensureOptionsIsMutable();
        this.options_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.optionsBuilder_.addMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder addOptions(int param1Int, Option param1Option) {
      if (this.optionsBuilder_ == null) {
        if (param1Option == null)
          throw new NullPointerException(); 
        ensureOptionsIsMutable();
        this.options_.add(param1Int, param1Option);
        onChanged();
      } else {
        this.optionsBuilder_.addMessage(param1Int, param1Option);
      } 
      return this;
    }
    
    public Builder addOptions(Option.Builder param1Builder) {
      if (this.optionsBuilder_ == null) {
        ensureOptionsIsMutable();
        this.options_.add(param1Builder.build());
        onChanged();
      } else {
        this.optionsBuilder_.addMessage(param1Builder.build());
      } 
      return this;
    }
    
    public Builder addOptions(Option param1Option) {
      if (this.optionsBuilder_ == null) {
        if (param1Option == null)
          throw new NullPointerException(); 
        ensureOptionsIsMutable();
        this.options_.add(param1Option);
        onChanged();
      } else {
        this.optionsBuilder_.addMessage(param1Option);
      } 
      return this;
    }
    
    public Option.Builder addOptionsBuilder() {
      return getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
    }
    
    public Option.Builder addOptionsBuilder(int param1Int) {
      return getOptionsFieldBuilder().addBuilder(param1Int, Option.getDefaultInstance());
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public Enum build() {
      Enum enum_ = buildPartial();
      if (!enum_.isInitialized())
        throw newUninitializedMessageException(enum_); 
      return enum_;
    }
    
    public Enum buildPartial() {
      Enum enum_ = new Enum(this);
      int i = this.bitField0_;
      Enum.access$302(enum_, this.name_);
      if (this.enumvalueBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0) {
          this.enumvalue_ = Collections.unmodifiableList(this.enumvalue_);
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        Enum.access$402(enum_, this.enumvalue_);
      } else {
        Enum.access$402(enum_, this.enumvalueBuilder_.build());
      } 
      if (this.optionsBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0) {
          this.options_ = Collections.unmodifiableList(this.options_);
          this.bitField0_ &= 0xFFFFFFFD;
        } 
        Enum.access$502(enum_, this.options_);
      } else {
        Enum.access$502(enum_, this.optionsBuilder_.build());
      } 
      if (this.sourceContextBuilder_ == null) {
        Enum.access$602(enum_, this.sourceContext_);
      } else {
        Enum.access$602(enum_, this.sourceContextBuilder_.build());
      } 
      Enum.access$702(enum_, this.syntax_);
      onBuilt();
      return enum_;
    }
    
    public Builder clear() {
      super.clear();
      this.name_ = "";
      if (this.enumvalueBuilder_ == null) {
        this.enumvalue_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
      } else {
        this.enumvalueBuilder_.clear();
      } 
      if (this.optionsBuilder_ == null) {
        this.options_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFD;
      } else {
        this.optionsBuilder_.clear();
      } 
      if (this.sourceContextBuilder_ == null) {
        this.sourceContext_ = null;
      } else {
        this.sourceContext_ = null;
        this.sourceContextBuilder_ = null;
      } 
      this.syntax_ = 0;
      return this;
    }
    
    public Builder clearEnumvalue() {
      if (this.enumvalueBuilder_ == null) {
        this.enumvalue_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
      } else {
        this.enumvalueBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearName() {
      this.name_ = Enum.getDefaultInstance().getName();
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearOptions() {
      if (this.optionsBuilder_ == null) {
        this.options_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
      } else {
        this.optionsBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearSourceContext() {
      if (this.sourceContextBuilder_ == null) {
        this.sourceContext_ = null;
        onChanged();
      } else {
        this.sourceContext_ = null;
        this.sourceContextBuilder_ = null;
      } 
      return this;
    }
    
    public Builder clearSyntax() {
      this.syntax_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public Enum getDefaultInstanceForType() {
      return Enum.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return TypeProto.internal_static_google_protobuf_Enum_descriptor;
    }
    
    public EnumValue getEnumvalue(int param1Int) {
      return (this.enumvalueBuilder_ == null) ? this.enumvalue_.get(param1Int) : this.enumvalueBuilder_.getMessage(param1Int);
    }
    
    public EnumValue.Builder getEnumvalueBuilder(int param1Int) {
      return getEnumvalueFieldBuilder().getBuilder(param1Int);
    }
    
    public List<EnumValue.Builder> getEnumvalueBuilderList() {
      return getEnumvalueFieldBuilder().getBuilderList();
    }
    
    public int getEnumvalueCount() {
      return (this.enumvalueBuilder_ == null) ? this.enumvalue_.size() : this.enumvalueBuilder_.getCount();
    }
    
    public List<EnumValue> getEnumvalueList() {
      return (this.enumvalueBuilder_ == null) ? Collections.unmodifiableList(this.enumvalue_) : this.enumvalueBuilder_.getMessageList();
    }
    
    public EnumValueOrBuilder getEnumvalueOrBuilder(int param1Int) {
      return (this.enumvalueBuilder_ == null) ? this.enumvalue_.get(param1Int) : this.enumvalueBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends EnumValueOrBuilder> getEnumvalueOrBuilderList() {
      return (this.enumvalueBuilder_ != null) ? this.enumvalueBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.enumvalue_);
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
    
    public Option getOptions(int param1Int) {
      return (this.optionsBuilder_ == null) ? this.options_.get(param1Int) : this.optionsBuilder_.getMessage(param1Int);
    }
    
    public Option.Builder getOptionsBuilder(int param1Int) {
      return getOptionsFieldBuilder().getBuilder(param1Int);
    }
    
    public List<Option.Builder> getOptionsBuilderList() {
      return getOptionsFieldBuilder().getBuilderList();
    }
    
    public int getOptionsCount() {
      return (this.optionsBuilder_ == null) ? this.options_.size() : this.optionsBuilder_.getCount();
    }
    
    public List<Option> getOptionsList() {
      return (this.optionsBuilder_ == null) ? Collections.unmodifiableList(this.options_) : this.optionsBuilder_.getMessageList();
    }
    
    public OptionOrBuilder getOptionsOrBuilder(int param1Int) {
      return (this.optionsBuilder_ == null) ? this.options_.get(param1Int) : this.optionsBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
      return (this.optionsBuilder_ != null) ? this.optionsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.options_);
    }
    
    public SourceContext getSourceContext() {
      if (this.sourceContextBuilder_ == null) {
        SourceContext sourceContext;
        if (this.sourceContext_ == null) {
          sourceContext = SourceContext.getDefaultInstance();
        } else {
          sourceContext = this.sourceContext_;
        } 
        return sourceContext;
      } 
      return this.sourceContextBuilder_.getMessage();
    }
    
    public SourceContext.Builder getSourceContextBuilder() {
      onChanged();
      return getSourceContextFieldBuilder().getBuilder();
    }
    
    public SourceContextOrBuilder getSourceContextOrBuilder() {
      SourceContext sourceContext;
      if (this.sourceContextBuilder_ != null)
        return this.sourceContextBuilder_.getMessageOrBuilder(); 
      if (this.sourceContext_ == null) {
        sourceContext = SourceContext.getDefaultInstance();
      } else {
        sourceContext = this.sourceContext_;
      } 
      return sourceContext;
    }
    
    public Syntax getSyntax() {
      Syntax syntax1 = Syntax.valueOf(this.syntax_);
      Syntax syntax2 = syntax1;
      if (syntax1 == null)
        syntax2 = Syntax.UNRECOGNIZED; 
      return syntax2;
    }
    
    public int getSyntaxValue() {
      return this.syntax_;
    }
    
    public boolean hasSourceContext() {
      return (this.sourceContextBuilder_ != null || this.sourceContext_ != null);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return TypeProto.internal_static_google_protobuf_Enum_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Enum.class, (Class)Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      Exception exception;
      CodedInputStream codedInputStream = null;
      try {
        Enum enum_ = Enum.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        Enum enum_ = (Enum)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        exception = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((Enum)param1CodedInputStream); 
      throw exception;
    }
    
    public Builder mergeFrom(Enum param1Enum) {
      if (param1Enum == Enum.getDefaultInstance())
        return this; 
      if (!param1Enum.getName().isEmpty()) {
        this.name_ = param1Enum.name_;
        onChanged();
      } 
      RepeatedFieldBuilderV3<EnumValue, EnumValue.Builder, EnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumvalueBuilder_;
      RepeatedFieldBuilderV3 repeatedFieldBuilderV31 = null;
      if (repeatedFieldBuilderV3 == null) {
        if (!param1Enum.enumvalue_.isEmpty()) {
          if (this.enumvalue_.isEmpty()) {
            this.enumvalue_ = param1Enum.enumvalue_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensureEnumvalueIsMutable();
            this.enumvalue_.addAll(param1Enum.enumvalue_);
          } 
          onChanged();
        } 
      } else if (!param1Enum.enumvalue_.isEmpty()) {
        if (this.enumvalueBuilder_.isEmpty()) {
          this.enumvalueBuilder_.dispose();
          this.enumvalueBuilder_ = null;
          this.enumvalue_ = param1Enum.enumvalue_;
          this.bitField0_ &= 0xFFFFFFFE;
          if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            repeatedFieldBuilderV3 = getEnumvalueFieldBuilder();
          } else {
            repeatedFieldBuilderV3 = null;
          } 
          this.enumvalueBuilder_ = repeatedFieldBuilderV3;
        } else {
          this.enumvalueBuilder_.addAllMessages(param1Enum.enumvalue_);
        } 
      } 
      if (this.optionsBuilder_ == null) {
        if (!param1Enum.options_.isEmpty()) {
          if (this.options_.isEmpty()) {
            this.options_ = param1Enum.options_;
            this.bitField0_ &= 0xFFFFFFFD;
          } else {
            ensureOptionsIsMutable();
            this.options_.addAll(param1Enum.options_);
          } 
          onChanged();
        } 
      } else if (!param1Enum.options_.isEmpty()) {
        if (this.optionsBuilder_.isEmpty()) {
          this.optionsBuilder_.dispose();
          this.optionsBuilder_ = null;
          this.options_ = param1Enum.options_;
          this.bitField0_ &= 0xFFFFFFFD;
          repeatedFieldBuilderV3 = repeatedFieldBuilderV31;
          if (GeneratedMessageV3.alwaysUseFieldBuilders)
            repeatedFieldBuilderV3 = (RepeatedFieldBuilderV3)getOptionsFieldBuilder(); 
          this.optionsBuilder_ = (RepeatedFieldBuilderV3)repeatedFieldBuilderV3;
        } else {
          this.optionsBuilder_.addAllMessages(param1Enum.options_);
        } 
      } 
      if (param1Enum.hasSourceContext())
        mergeSourceContext(param1Enum.getSourceContext()); 
      if (param1Enum.syntax_ != 0)
        setSyntaxValue(param1Enum.getSyntaxValue()); 
      mergeUnknownFields(param1Enum.unknownFields);
      onChanged();
      return this;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof Enum)
        return mergeFrom((Enum)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeSourceContext(SourceContext param1SourceContext) {
      if (this.sourceContextBuilder_ == null) {
        if (this.sourceContext_ != null) {
          this.sourceContext_ = SourceContext.newBuilder(this.sourceContext_).mergeFrom(param1SourceContext).buildPartial();
        } else {
          this.sourceContext_ = param1SourceContext;
        } 
        onChanged();
      } else {
        this.sourceContextBuilder_.mergeFrom(param1SourceContext);
      } 
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder removeEnumvalue(int param1Int) {
      if (this.enumvalueBuilder_ == null) {
        ensureEnumvalueIsMutable();
        this.enumvalue_.remove(param1Int);
        onChanged();
      } else {
        this.enumvalueBuilder_.remove(param1Int);
      } 
      return this;
    }
    
    public Builder removeOptions(int param1Int) {
      if (this.optionsBuilder_ == null) {
        ensureOptionsIsMutable();
        this.options_.remove(param1Int);
        onChanged();
      } else {
        this.optionsBuilder_.remove(param1Int);
      } 
      return this;
    }
    
    public Builder setEnumvalue(int param1Int, EnumValue.Builder param1Builder) {
      if (this.enumvalueBuilder_ == null) {
        ensureEnumvalueIsMutable();
        this.enumvalue_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.enumvalueBuilder_.setMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder setEnumvalue(int param1Int, EnumValue param1EnumValue) {
      if (this.enumvalueBuilder_ == null) {
        if (param1EnumValue == null)
          throw new NullPointerException(); 
        ensureEnumvalueIsMutable();
        this.enumvalue_.set(param1Int, param1EnumValue);
        onChanged();
      } else {
        this.enumvalueBuilder_.setMessage(param1Int, param1EnumValue);
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
    
    public Builder setOptions(int param1Int, Option.Builder param1Builder) {
      if (this.optionsBuilder_ == null) {
        ensureOptionsIsMutable();
        this.options_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.optionsBuilder_.setMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder setOptions(int param1Int, Option param1Option) {
      if (this.optionsBuilder_ == null) {
        if (param1Option == null)
          throw new NullPointerException(); 
        ensureOptionsIsMutable();
        this.options_.set(param1Int, param1Option);
        onChanged();
      } else {
        this.optionsBuilder_.setMessage(param1Int, param1Option);
      } 
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public Builder setSourceContext(SourceContext.Builder param1Builder) {
      if (this.sourceContextBuilder_ == null) {
        this.sourceContext_ = param1Builder.build();
        onChanged();
      } else {
        this.sourceContextBuilder_.setMessage(param1Builder.build());
      } 
      return this;
    }
    
    public Builder setSourceContext(SourceContext param1SourceContext) {
      if (this.sourceContextBuilder_ == null) {
        if (param1SourceContext == null)
          throw new NullPointerException(); 
        this.sourceContext_ = param1SourceContext;
        onChanged();
      } else {
        this.sourceContextBuilder_.setMessage(param1SourceContext);
      } 
      return this;
    }
    
    public Builder setSyntax(Syntax param1Syntax) {
      if (param1Syntax == null)
        throw new NullPointerException(); 
      this.syntax_ = param1Syntax.getNumber();
      onChanged();
      return this;
    }
    
    public Builder setSyntaxValue(int param1Int) {
      this.syntax_ = param1Int;
      onChanged();
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return super.setUnknownFields(param1UnknownFieldSet);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Enum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */