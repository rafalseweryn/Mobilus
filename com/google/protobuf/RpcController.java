package com.google.protobuf;

public interface RpcController {
  String errorText();
  
  boolean failed();
  
  boolean isCanceled();
  
  void notifyOnCancel(RpcCallback<Object> paramRpcCallback);
  
  void reset();
  
  void setFailed(String paramString);
  
  void startCancel();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\RpcController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */