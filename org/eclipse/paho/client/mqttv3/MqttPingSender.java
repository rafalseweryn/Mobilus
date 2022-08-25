package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public interface MqttPingSender {
  void init(ClientComms paramClientComms);
  
  void schedule(long paramLong);
  
  void start();
  
  void stop();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttPingSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */