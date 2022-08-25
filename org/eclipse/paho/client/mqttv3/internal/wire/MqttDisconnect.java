package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttDisconnect extends MqttWireMessage {
  public static final String KEY = "Disc";
  
  public MqttDisconnect() {
    super((byte)14);
  }
  
  public MqttDisconnect(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)14);
  }
  
  public String getKey() {
    return "Disc";
  }
  
  protected byte getMessageInfo() {
    return 0;
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    return new byte[0];
  }
  
  public boolean isMessageIdRequired() {
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttDisconnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */