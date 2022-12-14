package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class StructuralMessageInfo implements MessageInfo {
  private final int[] checkInitialized;
  
  private final MessageLite defaultInstance;
  
  private final FieldInfo[] fields;
  
  private final boolean messageSetWireFormat;
  
  private final ProtoSyntax syntax;
  
  StructuralMessageInfo(ProtoSyntax paramProtoSyntax, boolean paramBoolean, int[] paramArrayOfint, FieldInfo[] paramArrayOfFieldInfo, Object paramObject) {
    this.syntax = paramProtoSyntax;
    this.messageSetWireFormat = paramBoolean;
    this.checkInitialized = paramArrayOfint;
    this.fields = paramArrayOfFieldInfo;
    this.defaultInstance = (MessageLite)Internal.<Object>checkNotNull(paramObject, "defaultInstance");
  }
  
  public static Builder newBuilder() {
    return new Builder();
  }
  
  public static Builder newBuilder(int paramInt) {
    return new Builder(paramInt);
  }
  
  public int[] getCheckInitialized() {
    return this.checkInitialized;
  }
  
  public MessageLite getDefaultInstance() {
    return this.defaultInstance;
  }
  
  public FieldInfo[] getFields() {
    return this.fields;
  }
  
  public ProtoSyntax getSyntax() {
    return this.syntax;
  }
  
  public boolean isMessageSetWireFormat() {
    return this.messageSetWireFormat;
  }
  
  public static final class Builder {
    private int[] checkInitialized = null;
    
    private Object defaultInstance;
    
    private final List<FieldInfo> fields = new ArrayList<>();
    
    private boolean messageSetWireFormat;
    
    private ProtoSyntax syntax;
    
    private boolean wasBuilt;
    
    public Builder() {}
    
    public Builder(int param1Int) {}
    
    public StructuralMessageInfo build() {
      if (this.wasBuilt)
        throw new IllegalStateException("Builder can only build once"); 
      if (this.syntax == null)
        throw new IllegalStateException("Must specify a proto syntax"); 
      this.wasBuilt = true;
      Collections.sort(this.fields);
      return new StructuralMessageInfo(this.syntax, this.messageSetWireFormat, this.checkInitialized, this.fields.<FieldInfo>toArray(new FieldInfo[0]), this.defaultInstance);
    }
    
    public void withCheckInitialized(int[] param1ArrayOfint) {
      this.checkInitialized = param1ArrayOfint;
    }
    
    public void withDefaultInstance(Object param1Object) {
      this.defaultInstance = param1Object;
    }
    
    public void withField(FieldInfo param1FieldInfo) {
      if (this.wasBuilt)
        throw new IllegalStateException("Builder can only build once"); 
      this.fields.add(param1FieldInfo);
    }
    
    public void withMessageSetWireFormat(boolean param1Boolean) {
      this.messageSetWireFormat = param1Boolean;
    }
    
    public void withSyntax(ProtoSyntax param1ProtoSyntax) {
      this.syntax = Internal.<ProtoSyntax>checkNotNull(param1ProtoSyntax, "syntax");
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\StructuralMessageInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */