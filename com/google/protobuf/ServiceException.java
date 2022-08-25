package com.google.protobuf;

public class ServiceException extends Exception {
  private static final long serialVersionUID = -1219262335729891920L;
  
  public ServiceException(String paramString) {
    super(paramString);
  }
  
  public ServiceException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
  
  public ServiceException(Throwable paramThrowable) {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ServiceException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */