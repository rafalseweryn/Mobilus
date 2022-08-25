package com.google.protobuf;

import java.io.IOException;

public class LazyFieldLite {
  private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();
  
  private ByteString delayedBytes;
  
  private ExtensionRegistryLite extensionRegistry;
  
  private volatile ByteString memoizedBytes;
  
  protected volatile MessageLite value;
  
  public LazyFieldLite() {}
  
  public LazyFieldLite(ExtensionRegistryLite paramExtensionRegistryLite, ByteString paramByteString) {
    checkArguments(paramExtensionRegistryLite, paramByteString);
    this.extensionRegistry = paramExtensionRegistryLite;
    this.delayedBytes = paramByteString;
  }
  
  private static void checkArguments(ExtensionRegistryLite paramExtensionRegistryLite, ByteString paramByteString) {
    if (paramExtensionRegistryLite == null)
      throw new NullPointerException("found null ExtensionRegistry"); 
    if (paramByteString == null)
      throw new NullPointerException("found null ByteString"); 
  }
  
  public static LazyFieldLite fromValue(MessageLite paramMessageLite) {
    LazyFieldLite lazyFieldLite = new LazyFieldLite();
    lazyFieldLite.setValue(paramMessageLite);
    return lazyFieldLite;
  }
  
  private static MessageLite mergeValueAndBytes(MessageLite paramMessageLite, ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) {
    try {
      return paramMessageLite.toBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite).build();
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      return paramMessageLite;
    } 
  }
  
  public void clear() {
    this.delayedBytes = null;
    this.value = null;
    this.memoizedBytes = null;
  }
  
  public boolean containsDefaultInstance() {
    return (this.memoizedBytes == ByteString.EMPTY || (this.value == null && (this.delayedBytes == null || this.delayedBytes == ByteString.EMPTY)));
  }
  
  protected void ensureInitialized(MessageLite paramMessageLite) {
    // Byte code:
    //   0: aload_0
    //   1: getfield value : Lcom/google/protobuf/MessageLite;
    //   4: ifnull -> 8
    //   7: return
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield value : Lcom/google/protobuf/MessageLite;
    //   14: ifnull -> 20
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: getfield delayedBytes : Lcom/google/protobuf/ByteString;
    //   24: ifnull -> 64
    //   27: aload_0
    //   28: aload_1
    //   29: invokeinterface getParserForType : ()Lcom/google/protobuf/Parser;
    //   34: aload_0
    //   35: getfield delayedBytes : Lcom/google/protobuf/ByteString;
    //   38: aload_0
    //   39: getfield extensionRegistry : Lcom/google/protobuf/ExtensionRegistryLite;
    //   42: invokeinterface parseFrom : (Lcom/google/protobuf/ByteString;Lcom/google/protobuf/ExtensionRegistryLite;)Ljava/lang/Object;
    //   47: checkcast com/google/protobuf/MessageLite
    //   50: putfield value : Lcom/google/protobuf/MessageLite;
    //   53: aload_0
    //   54: aload_0
    //   55: getfield delayedBytes : Lcom/google/protobuf/ByteString;
    //   58: putfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   61: goto -> 92
    //   64: aload_0
    //   65: aload_1
    //   66: putfield value : Lcom/google/protobuf/MessageLite;
    //   69: aload_0
    //   70: getstatic com/google/protobuf/ByteString.EMPTY : Lcom/google/protobuf/ByteString;
    //   73: putfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   76: goto -> 92
    //   79: astore_2
    //   80: aload_0
    //   81: aload_1
    //   82: putfield value : Lcom/google/protobuf/MessageLite;
    //   85: aload_0
    //   86: getstatic com/google/protobuf/ByteString.EMPTY : Lcom/google/protobuf/ByteString;
    //   89: putfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   92: aload_0
    //   93: monitorexit
    //   94: return
    //   95: astore_1
    //   96: aload_0
    //   97: monitorexit
    //   98: aload_1
    //   99: athrow
    // Exception table:
    //   from	to	target	type
    //   10	19	95	finally
    //   20	61	79	com/google/protobuf/InvalidProtocolBufferException
    //   20	61	95	finally
    //   64	76	79	com/google/protobuf/InvalidProtocolBufferException
    //   64	76	95	finally
    //   80	92	95	finally
    //   92	94	95	finally
    //   96	98	95	finally
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LazyFieldLite))
      return false; 
    LazyFieldLite lazyFieldLite = (LazyFieldLite)paramObject;
    MessageLite messageLite = this.value;
    paramObject = lazyFieldLite.value;
    return (messageLite == null && paramObject == null) ? toByteString().equals(lazyFieldLite.toByteString()) : ((messageLite != null && paramObject != null) ? messageLite.equals(paramObject) : ((messageLite != null) ? messageLite.equals(lazyFieldLite.getValue(messageLite.getDefaultInstanceForType())) : getValue(paramObject.getDefaultInstanceForType()).equals(paramObject)));
  }
  
  public int getSerializedSize() {
    return (this.memoizedBytes != null) ? this.memoizedBytes.size() : ((this.delayedBytes != null) ? this.delayedBytes.size() : ((this.value != null) ? this.value.getSerializedSize() : 0));
  }
  
  public MessageLite getValue(MessageLite paramMessageLite) {
    ensureInitialized(paramMessageLite);
    return this.value;
  }
  
  public int hashCode() {
    return 1;
  }
  
  public void merge(LazyFieldLite paramLazyFieldLite) {
    if (paramLazyFieldLite.containsDefaultInstance())
      return; 
    if (containsDefaultInstance()) {
      set(paramLazyFieldLite);
      return;
    } 
    if (this.extensionRegistry == null)
      this.extensionRegistry = paramLazyFieldLite.extensionRegistry; 
    if (this.delayedBytes != null && paramLazyFieldLite.delayedBytes != null) {
      this.delayedBytes = this.delayedBytes.concat(paramLazyFieldLite.delayedBytes);
      return;
    } 
    if (this.value == null && paramLazyFieldLite.value != null) {
      setValue(mergeValueAndBytes(paramLazyFieldLite.value, this.delayedBytes, this.extensionRegistry));
      return;
    } 
    if (this.value != null && paramLazyFieldLite.value == null) {
      setValue(mergeValueAndBytes(this.value, paramLazyFieldLite.delayedBytes, paramLazyFieldLite.extensionRegistry));
      return;
    } 
    setValue(this.value.toBuilder().mergeFrom(paramLazyFieldLite.value).build());
  }
  
  public void mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    if (containsDefaultInstance()) {
      setByteString(paramCodedInputStream.readBytes(), paramExtensionRegistryLite);
      return;
    } 
    if (this.extensionRegistry == null)
      this.extensionRegistry = paramExtensionRegistryLite; 
    if (this.delayedBytes != null) {
      setByteString(this.delayedBytes.concat(paramCodedInputStream.readBytes()), this.extensionRegistry);
      return;
    } 
    try {
      setValue(this.value.toBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).build());
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {}
  }
  
  public void set(LazyFieldLite paramLazyFieldLite) {
    this.delayedBytes = paramLazyFieldLite.delayedBytes;
    this.value = paramLazyFieldLite.value;
    this.memoizedBytes = paramLazyFieldLite.memoizedBytes;
    if (paramLazyFieldLite.extensionRegistry != null)
      this.extensionRegistry = paramLazyFieldLite.extensionRegistry; 
  }
  
  public void setByteString(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) {
    checkArguments(paramExtensionRegistryLite, paramByteString);
    this.delayedBytes = paramByteString;
    this.extensionRegistry = paramExtensionRegistryLite;
    this.value = null;
    this.memoizedBytes = null;
  }
  
  public MessageLite setValue(MessageLite paramMessageLite) {
    MessageLite messageLite = this.value;
    this.delayedBytes = null;
    this.memoizedBytes = null;
    this.value = paramMessageLite;
    return messageLite;
  }
  
  public ByteString toByteString() {
    // Byte code:
    //   0: aload_0
    //   1: getfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   4: ifnull -> 12
    //   7: aload_0
    //   8: getfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   11: areturn
    //   12: aload_0
    //   13: getfield delayedBytes : Lcom/google/protobuf/ByteString;
    //   16: ifnull -> 24
    //   19: aload_0
    //   20: getfield delayedBytes : Lcom/google/protobuf/ByteString;
    //   23: areturn
    //   24: aload_0
    //   25: monitorenter
    //   26: aload_0
    //   27: getfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   30: ifnull -> 42
    //   33: aload_0
    //   34: getfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: aload_0
    //   43: getfield value : Lcom/google/protobuf/MessageLite;
    //   46: ifnonnull -> 59
    //   49: aload_0
    //   50: getstatic com/google/protobuf/ByteString.EMPTY : Lcom/google/protobuf/ByteString;
    //   53: putfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   56: goto -> 72
    //   59: aload_0
    //   60: aload_0
    //   61: getfield value : Lcom/google/protobuf/MessageLite;
    //   64: invokeinterface toByteString : ()Lcom/google/protobuf/ByteString;
    //   69: putfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   72: aload_0
    //   73: getfield memoizedBytes : Lcom/google/protobuf/ByteString;
    //   76: astore_1
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_1
    //   80: areturn
    //   81: astore_1
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_1
    //   85: athrow
    // Exception table:
    //   from	to	target	type
    //   26	40	81	finally
    //   42	56	81	finally
    //   59	72	81	finally
    //   72	79	81	finally
    //   82	84	81	finally
  }
  
  void writeTo(Writer paramWriter, int paramInt) throws IOException {
    if (this.memoizedBytes != null) {
      paramWriter.writeBytes(paramInt, this.memoizedBytes);
    } else if (this.delayedBytes != null) {
      paramWriter.writeBytes(paramInt, this.delayedBytes);
    } else if (this.value != null) {
      paramWriter.writeMessage(paramInt, this.value);
    } else {
      paramWriter.writeBytes(paramInt, ByteString.EMPTY);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\LazyFieldLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */