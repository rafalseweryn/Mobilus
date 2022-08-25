package com.google.protobuf;

interface MessageInfo {
  MessageLite getDefaultInstance();
  
  ProtoSyntax getSyntax();
  
  boolean isMessageSetWireFormat();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\MessageInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */