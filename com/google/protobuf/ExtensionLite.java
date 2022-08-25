package com.google.protobuf;

public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
  public abstract Type getDefaultValue();
  
  public abstract WireFormat.FieldType getLiteType();
  
  public abstract MessageLite getMessageDefaultInstance();
  
  public abstract int getNumber();
  
  boolean isLite() {
    return true;
  }
  
  public abstract boolean isRepeated();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ExtensionLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */