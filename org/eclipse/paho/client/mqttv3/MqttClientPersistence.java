package org.eclipse.paho.client.mqttv3;

import java.util.Enumeration;

public interface MqttClientPersistence {
  void clear() throws MqttPersistenceException;
  
  void close() throws MqttPersistenceException;
  
  boolean containsKey(String paramString) throws MqttPersistenceException;
  
  MqttPersistable get(String paramString) throws MqttPersistenceException;
  
  Enumeration keys() throws MqttPersistenceException;
  
  void open(String paramString1, String paramString2) throws MqttPersistenceException;
  
  void put(String paramString, MqttPersistable paramMqttPersistable) throws MqttPersistenceException;
  
  void remove(String paramString) throws MqttPersistenceException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttClientPersistence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */