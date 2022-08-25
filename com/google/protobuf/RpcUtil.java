package com.google.protobuf;

public final class RpcUtil {
  private static <Type extends Message> Type copyAsType(Type paramType, Message paramMessage) {
    return (Type)paramType.newBuilderForType().mergeFrom(paramMessage).build();
  }
  
  public static <Type extends Message> RpcCallback<Message> generalizeCallback(final RpcCallback<Type> originalCallback, final Class<Type> originalClass, final Type defaultInstance) {
    return new RpcCallback<Message>() {
        public void run(Message param1Message) {
          try {
            Message message = originalClass.cast(param1Message);
            param1Message = message;
          } catch (ClassCastException classCastException) {
            param1Message = (Message)RpcUtil.copyAsType((Type)defaultInstance, param1Message);
          } 
          originalCallback.run(param1Message);
        }
      };
  }
  
  public static <ParameterType> RpcCallback<ParameterType> newOneTimeCallback(final RpcCallback<ParameterType> originalCallback) {
    return new RpcCallback<ParameterType>() {
        private boolean alreadyCalled = false;
        
        public void run(ParameterType param1ParameterType) {
          // Byte code:
          //   0: aload_0
          //   1: monitorenter
          //   2: aload_0
          //   3: getfield alreadyCalled : Z
          //   6: ifeq -> 19
          //   9: new com/google/protobuf/RpcUtil$AlreadyCalledException
          //   12: astore_1
          //   13: aload_1
          //   14: invokespecial <init> : ()V
          //   17: aload_1
          //   18: athrow
          //   19: aload_0
          //   20: iconst_1
          //   21: putfield alreadyCalled : Z
          //   24: aload_0
          //   25: monitorexit
          //   26: aload_0
          //   27: getfield val$originalCallback : Lcom/google/protobuf/RpcCallback;
          //   30: aload_1
          //   31: invokeinterface run : (Ljava/lang/Object;)V
          //   36: return
          //   37: astore_1
          //   38: aload_0
          //   39: monitorexit
          //   40: aload_1
          //   41: athrow
          // Exception table:
          //   from	to	target	type
          //   2	19	37	finally
          //   19	26	37	finally
          //   38	40	37	finally
        }
      };
  }
  
  public static <Type extends Message> RpcCallback<Type> specializeCallback(RpcCallback<Message> paramRpcCallback) {
    return (RpcCallback)paramRpcCallback;
  }
  
  public static final class AlreadyCalledException extends RuntimeException {
    private static final long serialVersionUID = 5469741279507848266L;
    
    public AlreadyCalledException() {
      super("This RpcCallback was already called and cannot be called multiple times.");
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\RpcUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */