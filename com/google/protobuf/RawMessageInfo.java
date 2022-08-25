package com.google.protobuf;

final class RawMessageInfo implements MessageInfo {
  private final MessageLite defaultInstance;
  
  private final int flags;
  
  private final String info;
  
  private final Object[] objects;
  
  RawMessageInfo(MessageLite paramMessageLite, String paramString, Object[] paramArrayOfObject) {
    this.defaultInstance = paramMessageLite;
    this.info = paramString;
    this.objects = paramArrayOfObject;
    char c = paramString.charAt(0);
    if (c < '?') {
      this.flags = c;
    } else {
      int i = c & 0x1FFF;
      byte b = 13;
      c = '\001';
      while (true) {
        char c1 = paramString.charAt(c);
        if (c1 >= '?') {
          i |= (c1 & 0x1FFF) << b;
          b += 13;
          c++;
          continue;
        } 
        this.flags = i | c1 << b;
        return;
      } 
    } 
  }
  
  public MessageLite getDefaultInstance() {
    return this.defaultInstance;
  }
  
  Object[] getObjects() {
    return this.objects;
  }
  
  String getStringInfo() {
    return this.info;
  }
  
  public ProtoSyntax getSyntax() {
    ProtoSyntax protoSyntax;
    if ((this.flags & 0x1) == 1) {
      protoSyntax = ProtoSyntax.PROTO2;
    } else {
      protoSyntax = ProtoSyntax.PROTO3;
    } 
    return protoSyntax;
  }
  
  public boolean isMessageSetWireFormat() {
    boolean bool;
    if ((this.flags & 0x2) == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\RawMessageInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */