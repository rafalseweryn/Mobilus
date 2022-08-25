package com.google.protobuf;

public class SingleFieldBuilderV3<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> implements AbstractMessage.BuilderParent {
  private BType builder;
  
  private boolean isClean;
  
  private MType message;
  
  private AbstractMessage.BuilderParent parent;
  
  public SingleFieldBuilderV3(MType paramMType, AbstractMessage.BuilderParent paramBuilderParent, boolean paramBoolean) {
    this.message = (MType)Internal.<AbstractMessage>checkNotNull((AbstractMessage)paramMType);
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
  
  public SingleFieldBuilderV3<MType, BType, IType> clear() {
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
  
  public SingleFieldBuilderV3<MType, BType, IType> mergeFrom(MType paramMType) {
    if (this.builder == null && this.message == this.message.getDefaultInstanceForType()) {
      this.message = paramMType;
    } else {
      getBuilder().mergeFrom((Message)paramMType);
    } 
    onChanged();
    return this;
  }
  
  public SingleFieldBuilderV3<MType, BType, IType> setMessage(MType paramMType) {
    this.message = (MType)Internal.<AbstractMessage>checkNotNull((AbstractMessage)paramMType);
    if (this.builder != null) {
      this.builder.dispose();
      this.builder = null;
    } 
    onChanged();
    return this;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\SingleFieldBuilderV3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */