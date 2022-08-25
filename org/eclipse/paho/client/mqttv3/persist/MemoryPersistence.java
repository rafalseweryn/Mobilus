package org.eclipse.paho.client.mqttv3.persist;

import java.util.Enumeration;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MemoryPersistence implements MqttClientPersistence {
  private Hashtable data;
  
  public void clear() throws MqttPersistenceException {
    this.data.clear();
  }
  
  public void close() throws MqttPersistenceException {
    this.data.clear();
  }
  
  public boolean containsKey(String paramString) throws MqttPersistenceException {
    return this.data.containsKey(paramString);
  }
  
  public MqttPersistable get(String paramString) throws MqttPersistenceException {
    return (MqttPersistable)this.data.get(paramString);
  }
  
  public Enumeration keys() throws MqttPersistenceException {
    return this.data.keys();
  }
  
  public void open(String paramString1, String paramString2) throws MqttPersistenceException {
    this.data = new Hashtable<>();
  }
  
  public void put(String paramString, MqttPersistable paramMqttPersistable) throws MqttPersistenceException {
    this.data.put(paramString, paramMqttPersistable);
  }
  
  public void remove(String paramString) throws MqttPersistenceException {
    this.data.remove(paramString);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\persist\MemoryPersistence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */