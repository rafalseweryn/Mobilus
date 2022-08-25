package com.google.protobuf;

class GeneratedMessageInfoFactory implements MessageInfoFactory {
  private static final GeneratedMessageInfoFactory instance = new GeneratedMessageInfoFactory();
  
  public static GeneratedMessageInfoFactory getInstance() {
    return instance;
  }
  
  public boolean isSupported(Class<?> paramClass) {
    return GeneratedMessageLite.class.isAssignableFrom(paramClass);
  }
  
  public MessageInfo messageInfoFor(Class<?> paramClass) {
    if (!GeneratedMessageLite.class.isAssignableFrom(paramClass)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unsupported message type: ");
      stringBuilder.append(paramClass.getName());
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    try {
      return (MessageInfo)GeneratedMessageLite.<GeneratedMessageLite<?, ?>>getDefaultInstance((Class)paramClass.asSubclass((Class)GeneratedMessageLite.class)).buildMessageInfo();
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to get message info for ");
      stringBuilder.append(paramClass.getName());
      throw new RuntimeException(stringBuilder.toString(), exception);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\GeneratedMessageInfoFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */