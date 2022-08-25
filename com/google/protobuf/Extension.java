package com.google.protobuf;

public abstract class Extension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {
  protected abstract Object fromReflectionType(Object paramObject);
  
  public abstract Descriptors.FieldDescriptor getDescriptor();
  
  protected abstract ExtensionType getExtensionType();
  
  public abstract Message getMessageDefaultInstance();
  
  public MessageType getMessageType() {
    return MessageType.PROTO2;
  }
  
  final boolean isLite() {
    return false;
  }
  
  protected abstract Object singularFromReflectionType(Object paramObject);
  
  protected abstract Object singularToReflectionType(Object paramObject);
  
  protected abstract Object toReflectionType(Object paramObject);
  
  protected enum ExtensionType {
    IMMUTABLE, MUTABLE, PROTO1;
    
    static {
    
    }
  }
  
  public enum MessageType {
    PROTO1, PROTO2;
    
    static {
    
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Extension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */