package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface NetworkModule {
  InputStream getInputStream() throws IOException;
  
  OutputStream getOutputStream() throws IOException;
  
  void start() throws IOException, MqttException;
  
  void stop() throws IOException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\NetworkModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */