package com.google.protobuf;

public interface ValueOrBuilder extends MessageOrBuilder {
  boolean getBoolValue();
  
  Value.KindCase getKindCase();
  
  ListValue getListValue();
  
  ListValueOrBuilder getListValueOrBuilder();
  
  NullValue getNullValue();
  
  int getNullValueValue();
  
  double getNumberValue();
  
  String getStringValue();
  
  ByteString getStringValueBytes();
  
  Struct getStructValue();
  
  StructOrBuilder getStructValueOrBuilder();
  
  boolean hasListValue();
  
  boolean hasStructValue();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ValueOrBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */