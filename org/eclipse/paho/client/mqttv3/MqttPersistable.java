package org.eclipse.paho.client.mqttv3;

public interface MqttPersistable {
  byte[] getHeaderBytes() throws MqttPersistenceException;
  
  int getHeaderLength() throws MqttPersistenceException;
  
  int getHeaderOffset() throws MqttPersistenceException;
  
  byte[] getPayloadBytes() throws MqttPersistenceException;
  
  int getPayloadLength() throws MqttPersistenceException;
  
  int getPayloadOffset() throws MqttPersistenceException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttPersistable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */