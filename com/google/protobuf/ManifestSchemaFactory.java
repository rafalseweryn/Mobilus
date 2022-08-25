package com.google.protobuf;

final class ManifestSchemaFactory implements SchemaFactory {
  private static final MessageInfoFactory EMPTY_FACTORY = new MessageInfoFactory() {
      public boolean isSupported(Class<?> param1Class) {
        return false;
      }
      
      public MessageInfo messageInfoFor(Class<?> param1Class) {
        throw new IllegalStateException("This should never be called.");
      }
    };
  
  private final MessageInfoFactory messageInfoFactory;
  
  public ManifestSchemaFactory() {
    this(getDefaultMessageInfoFactory());
  }
  
  private ManifestSchemaFactory(MessageInfoFactory paramMessageInfoFactory) {
    this.messageInfoFactory = Internal.<MessageInfoFactory>checkNotNull(paramMessageInfoFactory, "messageInfoFactory");
  }
  
  private static MessageInfoFactory getDefaultMessageInfoFactory() {
    return new CompositeMessageInfoFactory(new MessageInfoFactory[] { GeneratedMessageInfoFactory.getInstance(), getDescriptorMessageInfoFactory() });
  }
  
  private static MessageInfoFactory getDescriptorMessageInfoFactory() {
    try {
      return (MessageInfoFactory)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
    } catch (Exception exception) {
      return EMPTY_FACTORY;
    } 
  }
  
  private static boolean isProto2(MessageInfo paramMessageInfo) {
    boolean bool;
    if (paramMessageInfo.getSyntax() == ProtoSyntax.PROTO2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static <T> Schema<T> newSchema(Class<T> paramClass, MessageInfo paramMessageInfo) {
    MessageSchema<T> messageSchema;
    if (GeneratedMessageLite.class.isAssignableFrom(paramClass)) {
      if (isProto2(paramMessageInfo)) {
        messageSchema = MessageSchema.newSchema(paramClass, paramMessageInfo, NewInstanceSchemas.lite(), ListFieldSchema.lite(), SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.lite(), MapFieldSchemas.lite());
      } else {
        messageSchema = MessageSchema.newSchema((Class<T>)messageSchema, paramMessageInfo, NewInstanceSchemas.lite(), ListFieldSchema.lite(), SchemaUtil.unknownFieldSetLiteSchema(), null, MapFieldSchemas.lite());
      } 
      return messageSchema;
    } 
    if (isProto2(paramMessageInfo)) {
      messageSchema = MessageSchema.newSchema((Class<T>)messageSchema, paramMessageInfo, NewInstanceSchemas.full(), ListFieldSchema.full(), SchemaUtil.proto2UnknownFieldSetSchema(), ExtensionSchemas.full(), MapFieldSchemas.full());
    } else {
      messageSchema = MessageSchema.newSchema((Class<T>)messageSchema, paramMessageInfo, NewInstanceSchemas.full(), ListFieldSchema.full(), SchemaUtil.proto3UnknownFieldSetSchema(), null, MapFieldSchemas.full());
    } 
    return messageSchema;
  }
  
  public <T> Schema<T> createSchema(Class<T> paramClass) {
    SchemaUtil.requireGeneratedMessage(paramClass);
    MessageInfo messageInfo = this.messageInfoFactory.messageInfoFor(paramClass);
    return messageInfo.isMessageSetWireFormat() ? (GeneratedMessageLite.class.isAssignableFrom(paramClass) ? MessageSetSchema.newSchema(SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.lite(), messageInfo.getDefaultInstance()) : MessageSetSchema.newSchema(SchemaUtil.proto2UnknownFieldSetSchema(), ExtensionSchemas.full(), messageInfo.getDefaultInstance())) : newSchema(paramClass, messageInfo);
  }
  
  private static class CompositeMessageInfoFactory implements MessageInfoFactory {
    private MessageInfoFactory[] factories;
    
    CompositeMessageInfoFactory(MessageInfoFactory... param1VarArgs) {
      this.factories = param1VarArgs;
    }
    
    public boolean isSupported(Class<?> param1Class) {
      MessageInfoFactory[] arrayOfMessageInfoFactory = this.factories;
      int i = arrayOfMessageInfoFactory.length;
      for (byte b = 0; b < i; b++) {
        if (arrayOfMessageInfoFactory[b].isSupported(param1Class))
          return true; 
      } 
      return false;
    }
    
    public MessageInfo messageInfoFor(Class<?> param1Class) {
      for (MessageInfoFactory messageInfoFactory : this.factories) {
        if (messageInfoFactory.isSupported(param1Class))
          return messageInfoFactory.messageInfoFor(param1Class); 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No factory is available for message type: ");
      stringBuilder.append(param1Class.getName());
      throw new UnsupportedOperationException(stringBuilder.toString());
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ManifestSchemaFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */