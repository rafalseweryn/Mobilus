package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;

public class LocalNetworkModule implements NetworkModule {
  private String brokerName;
  
  private Object localAdapter;
  
  private Class localListener;
  
  public LocalNetworkModule(String paramString) {
    this.brokerName = paramString;
  }
  
  public InputStream getInputStream() throws IOException {
    try {
      InputStream inputStream = (InputStream)this.localListener.getMethod("getClientInputStream", new Class[0]).invoke(this.localAdapter, new Object[0]);
    } catch (Exception exception) {
      exception = null;
    } 
    return (InputStream)exception;
  }
  
  public OutputStream getOutputStream() throws IOException {
    try {
      OutputStream outputStream = (OutputStream)this.localListener.getMethod("getClientOutputStream", new Class[0]).invoke(this.localAdapter, new Object[0]);
    } catch (Exception exception) {
      exception = null;
    } 
    return (OutputStream)exception;
  }
  
  public void start() throws IOException, MqttException {
    if (!ExceptionHelper.isClassAvailable("com.ibm.mqttdirect.modules.local.bindings.localListener"))
      throw ExceptionHelper.createMqttException(32103); 
    try {
      NoClassDefFoundError noClassDefFoundError;
      this.localListener = Class.forName("com.ibm.mqttdirect.modules.local.bindings.localListener");
      Class clazz = this.localListener;
      Class<?> clazz1 = class$0;
      Class<?> clazz2 = clazz1;
      if (clazz1 == null)
        try {
          clazz2 = Class.forName("java.lang.String");
          class$0 = clazz2;
        } catch (ClassNotFoundException classNotFoundException) {
          noClassDefFoundError = new NoClassDefFoundError();
          this(classNotFoundException.getMessage());
          throw noClassDefFoundError;
        }  
      this.localAdapter = clazz.getMethod("connect", new Class[] { (Class)noClassDefFoundError }).invoke(null, new Object[] { this.brokerName });
    } catch (Exception exception) {}
    if (this.localAdapter == null)
      throw ExceptionHelper.createMqttException(32103); 
  }
  
  public void stop() throws IOException {
    if (this.localAdapter != null)
      try {
        this.localListener.getMethod("close", new Class[0]).invoke(this.localAdapter, new Object[0]);
      } catch (Exception exception) {} 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\LocalNetworkModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */