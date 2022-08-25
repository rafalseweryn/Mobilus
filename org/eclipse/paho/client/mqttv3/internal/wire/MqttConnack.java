package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttConnack extends MqttAck {
  public static final String KEY = "Con";
  
  private int returnCode;
  
  private boolean sessionPresent;
  
  public MqttConnack(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)2);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    int i = dataInputStream.readUnsignedByte();
    boolean bool = true;
    if ((i & 0x1) != 1)
      bool = false; 
    this.sessionPresent = bool;
    this.returnCode = dataInputStream.readUnsignedByte();
    dataInputStream.close();
  }
  
  public String getKey() {
    return "Con";
  }
  
  public int getReturnCode() {
    return this.returnCode;
  }
  
  public boolean getSessionPresent() {
    return this.sessionPresent;
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    return new byte[0];
  }
  
  public boolean isMessageIdRequired() {
    return false;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
    stringBuffer.append(" session present:");
    stringBuffer.append(this.sessionPresent);
    stringBuffer.append(" return code: ");
    stringBuffer.append(this.returnCode);
    return stringBuffer.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttConnack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */