package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public abstract class MqttPersistableWireMessage extends MqttWireMessage implements MqttPersistable {
  public MqttPersistableWireMessage(byte paramByte) {
    super(paramByte);
  }
  
  public byte[] getHeaderBytes() throws MqttPersistenceException {
    try {
      return getHeader();
    } catch (MqttException mqttException) {
      throw new MqttPersistenceException(mqttException.getCause());
    } 
  }
  
  public int getHeaderLength() throws MqttPersistenceException {
    return (getHeaderBytes()).length;
  }
  
  public int getHeaderOffset() throws MqttPersistenceException {
    return 0;
  }
  
  public byte[] getPayloadBytes() throws MqttPersistenceException {
    try {
      return getPayload();
    } catch (MqttException mqttException) {
      throw new MqttPersistenceException(mqttException.getCause());
    } 
  }
  
  public int getPayloadLength() throws MqttPersistenceException {
    return 0;
  }
  
  public int getPayloadOffset() throws MqttPersistenceException {
    return 0;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttPersistableWireMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */