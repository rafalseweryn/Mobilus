package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Api extends GeneratedMessageV3 implements ApiOrBuilder {
  private static final Api DEFAULT_INSTANCE = new Api();
  
  public static final int METHODS_FIELD_NUMBER = 2;
  
  public static final int MIXINS_FIELD_NUMBER = 6;
  
  public static final int NAME_FIELD_NUMBER = 1;
  
  public static final int OPTIONS_FIELD_NUMBER = 3;
  
  private static final Parser<Api> PARSER = new AbstractParser<Api>() {
      public Api parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
        return new Api(param1CodedInputStream, param1ExtensionRegistryLite);
      }
    };
  
  public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
  
  public static final int SYNTAX_FIELD_NUMBER = 7;
  
  public static final int VERSION_FIELD_NUMBER = 4;
  
  private static final long serialVersionUID = 0L;
  
  private byte memoizedIsInitialized = (byte)-1;
  
  private List<Method> methods_;
  
  private List<Mixin> mixins_;
  
  private volatile Object name_;
  
  private List<Option> options_;
  
  private SourceContext sourceContext_;
  
  private int syntax_;
  
  private volatile Object version_;
  
  private Api() {
    this.name_ = "";
    this.methods_ = Collections.emptyList();
    this.options_ = Collections.emptyList();
    this.version_ = "";
    this.mixins_ = Collections.emptyList();
    this.syntax_ = 0;
  }
  
  private Api(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
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
                  if (n != 42) {
                    if (n != 50) {
                      if (n != 56) {
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
                    n = i;
                    if ((i & 0x4) == 0) {
                      j = i;
                      k = i;
                      m = i;
                      ArrayList<Mixin> arrayList = new ArrayList();
                      j = i;
                      k = i;
                      m = i;
                      this();
                      j = i;
                      k = i;
                      m = i;
                      this.mixins_ = arrayList;
                      n = i | 0x4;
                    } 
                    j = n;
                    k = n;
                    m = n;
                    this.mixins_.add(paramCodedInputStream.readMessage(Mixin.parser(), paramExtensionRegistryLite));
                    i = n;
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
                j = i;
                k = i;
                m = i;
                this.version_ = paramCodedInputStream.readStringRequireUtf8();
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
              ArrayList<Method> arrayList = new ArrayList();
              j = i;
              k = i;
              m = i;
              this();
              j = i;
              k = i;
              m = i;
              this.methods_ = arrayList;
              n = i | 0x1;
            } 
            j = n;
            k = n;
            m = n;
            this.methods_.add(paramCodedInputStream.readMessage(Method.parser(), paramExtensionRegistryLite));
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
        this.methods_ = Collections.unmodifiableList(this.methods_); 
      if ((j & 0x2) != 0)
        this.options_ = Collections.unmodifiableList(this.options_); 
      if ((j & 0x4) != 0)
        this.mixins_ = Collections.unmodifiableList(this.mixins_); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
      throw paramCodedInputStream;
      bool = true;
    } 
    if ((i & 0x1) != 0)
      this.methods_ = Collections.unmodifiableList(this.methods_); 
    if ((i & 0x2) != 0)
      this.options_ = Collections.unmodifiableList(this.options_); 
    if ((i & 0x4) != 0)
      this.mixins_ = Collections.unmodifiableList(this.mixins_); 
    this.unknownFields = builder.build();
    makeExtensionsImmutable();
  }
  
  private Api(GeneratedMessageV3.Builder<?> paramBuilder) {
    super(paramBuilder);
  }
  
  public static Api getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return ApiProto.internal_static_google_protobuf_Api_descriptor;
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Api paramApi) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(paramApi);
  }
  
  public static Api parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Api>parseDelimitedWithIOException(PARSER, paramInputStream);
  }
  
  public static Api parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Api>parseDelimitedWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Api parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString);
  }
  
  public static Api parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteString, paramExtensionRegistryLite);
  }
  
  public static Api parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return GeneratedMessageV3.<Api>parseWithIOException(PARSER, paramCodedInputStream);
  }
  
  public static Api parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Api>parseWithIOException(PARSER, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static Api parseFrom(InputStream paramInputStream) throws IOException {
    return GeneratedMessageV3.<Api>parseWithIOException(PARSER, paramInputStream);
  }
  
  public static Api parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return GeneratedMessageV3.<Api>parseWithIOException(PARSER, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Api parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer);
  }
  
  public static Api parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static Api parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte);
  }
  
  public static Api parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<Api> parser() {
    return PARSER;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Api))
      return super.equals(paramObject); 
    paramObject = paramObject;
    return !getName().equals(paramObject.getName()) ? false : (!getMethodsList().equals(paramObject.getMethodsList()) ? false : (!getOptionsList().equals(paramObject.getOptionsList()) ? false : (!getVersion().equals(paramObject.getVersion()) ? false : ((hasSourceContext() != paramObject.hasSourceContext()) ? false : ((hasSourceContext() && !getSourceContext().equals(paramObject.getSourceContext())) ? false : (!getMixinsList().equals(paramObject.getMixinsList()) ? false : ((this.syntax_ != ((Api)paramObject).syntax_) ? false : (!!this.unknownFields.equals(((Api)paramObject).unknownFields)))))))));
  }
  
  public Api getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public Method getMethods(int paramInt) {
    return this.methods_.get(paramInt);
  }
  
  public int getMethodsCount() {
    return this.methods_.size();
  }
  
  public List<Method> getMethodsList() {
    return this.methods_;
  }
  
  public MethodOrBuilder getMethodsOrBuilder(int paramInt) {
    return this.methods_.get(paramInt);
  }
  
  public List<? extends MethodOrBuilder> getMethodsOrBuilderList() {
    return (List)this.methods_;
  }
  
  public Mixin getMixins(int paramInt) {
    return this.mixins_.get(paramInt);
  }
  
  public int getMixinsCount() {
    return this.mixins_.size();
  }
  
  public List<Mixin> getMixinsList() {
    return this.mixins_;
  }
  
  public MixinOrBuilder getMixinsOrBuilder(int paramInt) {
    return this.mixins_.get(paramInt);
  }
  
  public List<? extends MixinOrBuilder> getMixinsOrBuilderList() {
    return (List)this.mixins_;
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
  
  public Parser<Api> getParserForType() {
    return PARSER;
  }
  
  public int getSerializedSize() {
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
    int j;
    for (j = 0; j < this.methods_.size(); j++)
      i += CodedOutputStream.computeMessageSize(2, this.methods_.get(j)); 
    for (j = 0; j < this.options_.size(); j++)
      i += CodedOutputStream.computeMessageSize(3, this.options_.get(j)); 
    j = i;
    if (!getVersionBytes().isEmpty())
      j = i + GeneratedMessageV3.computeStringSize(4, this.version_); 
    byte b2 = b1;
    i = j;
    if (this.sourceContext_ != null) {
      i = j + CodedOutputStream.computeMessageSize(5, getSourceContext());
      b2 = b1;
    } 
    while (b2 < this.mixins_.size()) {
      i += CodedOutputStream.computeMessageSize(6, this.mixins_.get(b2));
      b2++;
    } 
    j = i;
    if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber())
      j = i + CodedOutputStream.computeEnumSize(7, this.syntax_); 
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
  
  public String getVersion() {
    Object object = this.version_;
    if (object instanceof String)
      return (String)object; 
    object = ((ByteString)object).toStringUtf8();
    this.version_ = object;
    return (String)object;
  }
  
  public ByteString getVersionBytes() {
    Object object = this.version_;
    if (object instanceof String) {
      object = ByteString.copyFromUtf8((String)object);
      this.version_ = object;
      return (ByteString)object;
    } 
    return (ByteString)object;
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
    if (getMethodsCount() > 0)
      j = (i * 37 + 2) * 53 + getMethodsList().hashCode(); 
    i = j;
    if (getOptionsCount() > 0)
      i = (j * 37 + 3) * 53 + getOptionsList().hashCode(); 
    i = (i * 37 + 4) * 53 + getVersion().hashCode();
    j = i;
    if (hasSourceContext())
      j = (i * 37 + 5) * 53 + getSourceContext().hashCode(); 
    i = j;
    if (getMixinsCount() > 0)
      i = (j * 37 + 6) * 53 + getMixinsList().hashCode(); 
    j = ((i * 37 + 7) * 53 + this.syntax_) * 29 + this.unknownFields.hashCode();
    this.memoizedHashCode = j;
    return j;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Api.class, (Class)Builder.class);
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
    return new Api();
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
    boolean bool = false;
    byte b;
    for (b = 0; b < this.methods_.size(); b++)
      paramCodedOutputStream.writeMessage(2, this.methods_.get(b)); 
    for (b = 0; b < this.options_.size(); b++)
      paramCodedOutputStream.writeMessage(3, this.options_.get(b)); 
    if (!getVersionBytes().isEmpty())
      GeneratedMessageV3.writeString(paramCodedOutputStream, 4, this.version_); 
    b = bool;
    if (this.sourceContext_ != null) {
      paramCodedOutputStream.writeMessage(5, getSourceContext());
      b = bool;
    } 
    while (b < this.mixins_.size()) {
      paramCodedOutputStream.writeMessage(6, this.mixins_.get(b));
      b++;
    } 
    if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber())
      paramCodedOutputStream.writeEnum(7, this.syntax_); 
    this.unknownFields.writeTo(paramCodedOutputStream);
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ApiOrBuilder {
    private int bitField0_;
    
    private RepeatedFieldBuilderV3<Method, Method.Builder, MethodOrBuilder> methodsBuilder_;
    
    private List<Method> methods_ = Collections.emptyList();
    
    private RepeatedFieldBuilderV3<Mixin, Mixin.Builder, MixinOrBuilder> mixinsBuilder_;
    
    private List<Mixin> mixins_ = Collections.emptyList();
    
    private Object name_ = "";
    
    private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> optionsBuilder_;
    
    private List<Option> options_ = Collections.emptyList();
    
    private SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
    
    private SourceContext sourceContext_;
    
    private int syntax_ = 0;
    
    private Object version_ = "";
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureMethodsIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.methods_ = new ArrayList<>(this.methods_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    private void ensureMixinsIsMutable() {
      if ((this.bitField0_ & 0x4) == 0) {
        this.mixins_ = new ArrayList<>(this.mixins_);
        this.bitField0_ |= 0x4;
      } 
    }
    
    private void ensureOptionsIsMutable() {
      if ((this.bitField0_ & 0x2) == 0) {
        this.options_ = new ArrayList<>(this.options_);
        this.bitField0_ |= 0x2;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return ApiProto.internal_static_google_protobuf_Api_descriptor;
    }
    
    private RepeatedFieldBuilderV3<Method, Method.Builder, MethodOrBuilder> getMethodsFieldBuilder() {
      if (this.methodsBuilder_ == null) {
        List<Method> list = this.methods_;
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        this.methodsBuilder_ = new RepeatedFieldBuilderV3<>(list, bool, getParentForChildren(), isClean());
        this.methods_ = null;
      } 
      return this.methodsBuilder_;
    }
    
    private RepeatedFieldBuilderV3<Mixin, Mixin.Builder, MixinOrBuilder> getMixinsFieldBuilder() {
      if (this.mixinsBuilder_ == null) {
        boolean bool;
        List<Mixin> list = this.mixins_;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        this.mixinsBuilder_ = new RepeatedFieldBuilderV3<>(list, bool, getParentForChildren(), isClean());
        this.mixins_ = null;
      } 
      return this.mixinsBuilder_;
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
        getMethodsFieldBuilder();
        getOptionsFieldBuilder();
        getMixinsFieldBuilder();
      } 
    }
    
    public Builder addAllMethods(Iterable<? extends Method> param1Iterable) {
      if (this.methodsBuilder_ == null) {
        ensureMethodsIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.methods_);
        onChanged();
      } else {
        this.methodsBuilder_.addAllMessages(param1Iterable);
      } 
      return this;
    }
    
    public Builder addAllMixins(Iterable<? extends Mixin> param1Iterable) {
      if (this.mixinsBuilder_ == null) {
        ensureMixinsIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.mixins_);
        onChanged();
      } else {
        this.mixinsBuilder_.addAllMessages(param1Iterable);
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
    
    public Builder addMethods(int param1Int, Method.Builder param1Builder) {
      if (this.methodsBuilder_ == null) {
        ensureMethodsIsMutable();
        this.methods_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.methodsBuilder_.addMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder addMethods(int param1Int, Method param1Method) {
      if (this.methodsBuilder_ == null) {
        if (param1Method == null)
          throw new NullPointerException(); 
        ensureMethodsIsMutable();
        this.methods_.add(param1Int, param1Method);
        onChanged();
      } else {
        this.methodsBuilder_.addMessage(param1Int, param1Method);
      } 
      return this;
    }
    
    public Builder addMethods(Method.Builder param1Builder) {
      if (this.methodsBuilder_ == null) {
        ensureMethodsIsMutable();
        this.methods_.add(param1Builder.build());
        onChanged();
      } else {
        this.methodsBuilder_.addMessage(param1Builder.build());
      } 
      return this;
    }
    
    public Builder addMethods(Method param1Method) {
      if (this.methodsBuilder_ == null) {
        if (param1Method == null)
          throw new NullPointerException(); 
        ensureMethodsIsMutable();
        this.methods_.add(param1Method);
        onChanged();
      } else {
        this.methodsBuilder_.addMessage(param1Method);
      } 
      return this;
    }
    
    public Method.Builder addMethodsBuilder() {
      return getMethodsFieldBuilder().addBuilder(Method.getDefaultInstance());
    }
    
    public Method.Builder addMethodsBuilder(int param1Int) {
      return getMethodsFieldBuilder().addBuilder(param1Int, Method.getDefaultInstance());
    }
    
    public Builder addMixins(int param1Int, Mixin.Builder param1Builder) {
      if (this.mixinsBuilder_ == null) {
        ensureMixinsIsMutable();
        this.mixins_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.mixinsBuilder_.addMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder addMixins(int param1Int, Mixin param1Mixin) {
      if (this.mixinsBuilder_ == null) {
        if (param1Mixin == null)
          throw new NullPointerException(); 
        ensureMixinsIsMutable();
        this.mixins_.add(param1Int, param1Mixin);
        onChanged();
      } else {
        this.mixinsBuilder_.addMessage(param1Int, param1Mixin);
      } 
      return this;
    }
    
    public Builder addMixins(Mixin.Builder param1Builder) {
      if (this.mixinsBuilder_ == null) {
        ensureMixinsIsMutable();
        this.mixins_.add(param1Builder.build());
        onChanged();
      } else {
        this.mixinsBuilder_.addMessage(param1Builder.build());
      } 
      return this;
    }
    
    public Builder addMixins(Mixin param1Mixin) {
      if (this.mixinsBuilder_ == null) {
        if (param1Mixin == null)
          throw new NullPointerException(); 
        ensureMixinsIsMutable();
        this.mixins_.add(param1Mixin);
        onChanged();
      } else {
        this.mixinsBuilder_.addMessage(param1Mixin);
      } 
      return this;
    }
    
    public Mixin.Builder addMixinsBuilder() {
      return getMixinsFieldBuilder().addBuilder(Mixin.getDefaultInstance());
    }
    
    public Mixin.Builder addMixinsBuilder(int param1Int) {
      return getMixinsFieldBuilder().addBuilder(param1Int, Mixin.getDefaultInstance());
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
    
    public Api build() {
      Api api = buildPartial();
      if (!api.isInitialized())
        throw newUninitializedMessageException(api); 
      return api;
    }
    
    public Api buildPartial() {
      Api api = new Api(this);
      int i = this.bitField0_;
      Api.access$302(api, this.name_);
      if (this.methodsBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0) {
          this.methods_ = Collections.unmodifiableList(this.methods_);
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        Api.access$402(api, this.methods_);
      } else {
        Api.access$402(api, this.methodsBuilder_.build());
      } 
      if (this.optionsBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0) {
          this.options_ = Collections.unmodifiableList(this.options_);
          this.bitField0_ &= 0xFFFFFFFD;
        } 
        Api.access$502(api, this.options_);
      } else {
        Api.access$502(api, this.optionsBuilder_.build());
      } 
      Api.access$602(api, this.version_);
      if (this.sourceContextBuilder_ == null) {
        Api.access$702(api, this.sourceContext_);
      } else {
        Api.access$702(api, this.sourceContextBuilder_.build());
      } 
      if (this.mixinsBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0) {
          this.mixins_ = Collections.unmodifiableList(this.mixins_);
          this.bitField0_ &= 0xFFFFFFFB;
        } 
        Api.access$802(api, this.mixins_);
      } else {
        Api.access$802(api, this.mixinsBuilder_.build());
      } 
      Api.access$902(api, this.syntax_);
      onBuilt();
      return api;
    }
    
    public Builder clear() {
      super.clear();
      this.name_ = "";
      if (this.methodsBuilder_ == null) {
        this.methods_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
      } else {
        this.methodsBuilder_.clear();
      } 
      if (this.optionsBuilder_ == null) {
        this.options_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFD;
      } else {
        this.optionsBuilder_.clear();
      } 
      this.version_ = "";
      if (this.sourceContextBuilder_ == null) {
        this.sourceContext_ = null;
      } else {
        this.sourceContext_ = null;
        this.sourceContextBuilder_ = null;
      } 
      if (this.mixinsBuilder_ == null) {
        this.mixins_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFB;
      } else {
        this.mixinsBuilder_.clear();
      } 
      this.syntax_ = 0;
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearMethods() {
      if (this.methodsBuilder_ == null) {
        this.methods_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
      } else {
        this.methodsBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearMixins() {
      if (this.mixinsBuilder_ == null) {
        this.mixins_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
      } else {
        this.mixinsBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearName() {
      this.name_ = Api.getDefaultInstance().getName();
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
    
    public Builder clearVersion() {
      this.version_ = Api.getDefaultInstance().getVersion();
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public Api getDefaultInstanceForType() {
      return Api.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return ApiProto.internal_static_google_protobuf_Api_descriptor;
    }
    
    public Method getMethods(int param1Int) {
      return (this.methodsBuilder_ == null) ? this.methods_.get(param1Int) : this.methodsBuilder_.getMessage(param1Int);
    }
    
    public Method.Builder getMethodsBuilder(int param1Int) {
      return getMethodsFieldBuilder().getBuilder(param1Int);
    }
    
    public List<Method.Builder> getMethodsBuilderList() {
      return getMethodsFieldBuilder().getBuilderList();
    }
    
    public int getMethodsCount() {
      return (this.methodsBuilder_ == null) ? this.methods_.size() : this.methodsBuilder_.getCount();
    }
    
    public List<Method> getMethodsList() {
      return (this.methodsBuilder_ == null) ? Collections.unmodifiableList(this.methods_) : this.methodsBuilder_.getMessageList();
    }
    
    public MethodOrBuilder getMethodsOrBuilder(int param1Int) {
      return (this.methodsBuilder_ == null) ? this.methods_.get(param1Int) : this.methodsBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends MethodOrBuilder> getMethodsOrBuilderList() {
      return (this.methodsBuilder_ != null) ? this.methodsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.methods_);
    }
    
    public Mixin getMixins(int param1Int) {
      return (this.mixinsBuilder_ == null) ? this.mixins_.get(param1Int) : this.mixinsBuilder_.getMessage(param1Int);
    }
    
    public Mixin.Builder getMixinsBuilder(int param1Int) {
      return getMixinsFieldBuilder().getBuilder(param1Int);
    }
    
    public List<Mixin.Builder> getMixinsBuilderList() {
      return getMixinsFieldBuilder().getBuilderList();
    }
    
    public int getMixinsCount() {
      return (this.mixinsBuilder_ == null) ? this.mixins_.size() : this.mixinsBuilder_.getCount();
    }
    
    public List<Mixin> getMixinsList() {
      return (this.mixinsBuilder_ == null) ? Collections.unmodifiableList(this.mixins_) : this.mixinsBuilder_.getMessageList();
    }
    
    public MixinOrBuilder getMixinsOrBuilder(int param1Int) {
      return (this.mixinsBuilder_ == null) ? this.mixins_.get(param1Int) : this.mixinsBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends MixinOrBuilder> getMixinsOrBuilderList() {
      return (this.mixinsBuilder_ != null) ? this.mixinsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.mixins_);
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
    
    public String getVersion() {
      Object object = this.version_;
      if (!(object instanceof String)) {
        object = ((ByteString)object).toStringUtf8();
        this.version_ = object;
        return (String)object;
      } 
      return (String)object;
    }
    
    public ByteString getVersionBytes() {
      Object object = this.version_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.version_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasSourceContext() {
      return (this.sourceContextBuilder_ != null || this.sourceContext_ != null);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Api.class, (Class)Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(Api param1Api) {
      if (param1Api == Api.getDefaultInstance())
        return this; 
      if (!param1Api.getName().isEmpty()) {
        this.name_ = param1Api.name_;
        onChanged();
      } 
      RepeatedFieldBuilderV3<Method, Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
      RepeatedFieldBuilderV3 repeatedFieldBuilderV31 = null;
      if (repeatedFieldBuilderV3 == null) {
        if (!param1Api.methods_.isEmpty()) {
          if (this.methods_.isEmpty()) {
            this.methods_ = param1Api.methods_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensureMethodsIsMutable();
            this.methods_.addAll(param1Api.methods_);
          } 
          onChanged();
        } 
      } else if (!param1Api.methods_.isEmpty()) {
        if (this.methodsBuilder_.isEmpty()) {
          this.methodsBuilder_.dispose();
          this.methodsBuilder_ = null;
          this.methods_ = param1Api.methods_;
          this.bitField0_ &= 0xFFFFFFFE;
          if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            repeatedFieldBuilderV3 = getMethodsFieldBuilder();
          } else {
            repeatedFieldBuilderV3 = null;
          } 
          this.methodsBuilder_ = repeatedFieldBuilderV3;
        } else {
          this.methodsBuilder_.addAllMessages(param1Api.methods_);
        } 
      } 
      if (this.optionsBuilder_ == null) {
        if (!param1Api.options_.isEmpty()) {
          if (this.options_.isEmpty()) {
            this.options_ = param1Api.options_;
            this.bitField0_ &= 0xFFFFFFFD;
          } else {
            ensureOptionsIsMutable();
            this.options_.addAll(param1Api.options_);
          } 
          onChanged();
        } 
      } else if (!param1Api.options_.isEmpty()) {
        if (this.optionsBuilder_.isEmpty()) {
          this.optionsBuilder_.dispose();
          this.optionsBuilder_ = null;
          this.options_ = param1Api.options_;
          this.bitField0_ &= 0xFFFFFFFD;
          if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            repeatedFieldBuilderV3 = (RepeatedFieldBuilderV3)getOptionsFieldBuilder();
          } else {
            repeatedFieldBuilderV3 = null;
          } 
          this.optionsBuilder_ = (RepeatedFieldBuilderV3)repeatedFieldBuilderV3;
        } else {
          this.optionsBuilder_.addAllMessages(param1Api.options_);
        } 
      } 
      if (!param1Api.getVersion().isEmpty()) {
        this.version_ = param1Api.version_;
        onChanged();
      } 
      if (param1Api.hasSourceContext())
        mergeSourceContext(param1Api.getSourceContext()); 
      if (this.mixinsBuilder_ == null) {
        if (!param1Api.mixins_.isEmpty()) {
          if (this.mixins_.isEmpty()) {
            this.mixins_ = param1Api.mixins_;
            this.bitField0_ &= 0xFFFFFFFB;
          } else {
            ensureMixinsIsMutable();
            this.mixins_.addAll(param1Api.mixins_);
          } 
          onChanged();
        } 
      } else if (!param1Api.mixins_.isEmpty()) {
        if (this.mixinsBuilder_.isEmpty()) {
          this.mixinsBuilder_.dispose();
          this.mixinsBuilder_ = null;
          this.mixins_ = param1Api.mixins_;
          this.bitField0_ &= 0xFFFFFFFB;
          repeatedFieldBuilderV3 = repeatedFieldBuilderV31;
          if (GeneratedMessageV3.alwaysUseFieldBuilders)
            repeatedFieldBuilderV3 = (RepeatedFieldBuilderV3)getMixinsFieldBuilder(); 
          this.mixinsBuilder_ = (RepeatedFieldBuilderV3)repeatedFieldBuilderV3;
        } else {
          this.mixinsBuilder_.addAllMessages(param1Api.mixins_);
        } 
      } 
      if (param1Api.syntax_ != 0)
        setSyntaxValue(param1Api.getSyntaxValue()); 
      mergeUnknownFields(param1Api.unknownFields);
      onChanged();
      return this;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        Api api = Api.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        Api api = (Api)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((Api)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof Api)
        return mergeFrom((Api)param1Message); 
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
    
    public Builder removeMethods(int param1Int) {
      if (this.methodsBuilder_ == null) {
        ensureMethodsIsMutable();
        this.methods_.remove(param1Int);
        onChanged();
      } else {
        this.methodsBuilder_.remove(param1Int);
      } 
      return this;
    }
    
    public Builder removeMixins(int param1Int) {
      if (this.mixinsBuilder_ == null) {
        ensureMixinsIsMutable();
        this.mixins_.remove(param1Int);
        onChanged();
      } else {
        this.mixinsBuilder_.remove(param1Int);
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
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setMethods(int param1Int, Method.Builder param1Builder) {
      if (this.methodsBuilder_ == null) {
        ensureMethodsIsMutable();
        this.methods_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.methodsBuilder_.setMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder setMethods(int param1Int, Method param1Method) {
      if (this.methodsBuilder_ == null) {
        if (param1Method == null)
          throw new NullPointerException(); 
        ensureMethodsIsMutable();
        this.methods_.set(param1Int, param1Method);
        onChanged();
      } else {
        this.methodsBuilder_.setMessage(param1Int, param1Method);
      } 
      return this;
    }
    
    public Builder setMixins(int param1Int, Mixin.Builder param1Builder) {
      if (this.mixinsBuilder_ == null) {
        ensureMixinsIsMutable();
        this.mixins_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.mixinsBuilder_.setMessage(param1Int, param1Builder.build());
      } 
      return this;
    }
    
    public Builder setMixins(int param1Int, Mixin param1Mixin) {
      if (this.mixinsBuilder_ == null) {
        if (param1Mixin == null)
          throw new NullPointerException(); 
        ensureMixinsIsMutable();
        this.mixins_.set(param1Int, param1Mixin);
        onChanged();
      } else {
        this.mixinsBuilder_.setMessage(param1Int, param1Mixin);
      } 
      return this;
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
    
    public Builder setVersion(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.version_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setVersionBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      AbstractMessageLite.checkByteStringIsUtf8(param1ByteString);
      this.version_ = param1ByteString;
      onChanged();
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Api.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */