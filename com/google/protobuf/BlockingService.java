package com.google.protobuf;

public interface BlockingService {
  Message callBlockingMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage) throws ServiceException;
  
  Descriptors.ServiceDescriptor getDescriptorForType();
  
  Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
  
  Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\BlockingService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */