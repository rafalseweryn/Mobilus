package com.google.protobuf;

public class SingleFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> implements GeneratedMessage.BuilderParent {
  private BType builder;
  
  private boolean isClean;
  
  private MType message;
  
  private GeneratedMessage.BuilderParent parent;
  
  public SingleFieldBuilder(MType paramMType, GeneratedMessage.BuilderParent paramBuilderParent, boolean paramBoolean) {
    this.message = (MType)Internal.<GeneratedMessage>checkNotNull((GeneratedMessage)paramMType);
    this.parent = paramBuilderParent;
    this.isClean = paramBoolean;
  }
  
  private void onChanged() {
    if (this.builder != null)
      this.message = null; 
    if (this.isClean && this.parent != null) {
      this.parent.markDirty();
      this.isClean = false;
    } 
  }
  
  public MType build() {
    this.isClean = true;
    return getMessage();
  }
  
  public SingleFieldBuilder<MType, BType, IType> clear() {
    Message message;
    if (this.message != null) {
      message = this.message.getDefaultInstanceForType();
    } else {
      message = this.builder.getDefaultInstanceForType();
    } 
    this.message = (MType)message;
    if (this.builder != null) {
      this.builder.dispose();
      this.builder = null;
    } 
    onChanged();
    return this;
  }
  
  public void dispose() {
    this.parent = null;
  }
  
  public BType getBuilder() {
    if (this.builder == null) {
      this.builder = (BType)this.message.newBuilderForType(this);
      this.builder.mergeFrom((Message)this.message);
      this.builder.markClean();
    } 
    return this.builder;
  }
  
  public MType getMessage() {
    if (this.message == null)
      this.message = (MType)this.builder.buildPartial(); 
    return this.message;
  }
  
  public IType getMessageOrBuilder() {
    return (IType)((this.builder != null) ? (Object)this.builder : (Object)this.message);
  }
  
  public void markDirty() {
    onChanged();
  }
  
  public SingleFieldBuilder<MType, BType, IType> mergeFrom(MType paramMType) {
    if (this.builder == null && this.message == this.message.getDefaultInstanceForType()) {
      this.message = paramMType;
    } else {
      getBuilder().mergeFrom((Message)paramMType);
    } 
    onChanged();
    return this;
  }
  
  public SingleFieldBuilder<MType, BType, IType> setMessage(MType paramMType) {
    this.message = (MType)Internal.<GeneratedMessage>checkNotNull((GeneratedMessage)paramMType);
    if (this.builder != null) {
      this.builder.dispose();
      this.builder = null;
    } 
    onChanged();
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\SingleFieldBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */