package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPubRel extends MqttPersistableWireMessage {
  public MqttPubRel(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)6);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    this.msgId = dataInputStream.readUnsignedShort();
    dataInputStream.close();
  }
  
  public MqttPubRel(MqttPubRec paramMqttPubRec) {
    super((byte)6);
    setMessageId(paramMqttPubRec.getMessageId());
  }
  
  protected byte getMessageInfo() {
    boolean bool;
    if (this.duplicate) {
      bool = true;
    } else {
      bool = false;
    } 
    return (byte)(bool | 0x2);
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    return encodeMessageId();
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
    stringBuffer.append(" msgId ");
    stringBuffer.append(this.msgId);
    return stringBuffer.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttPubRel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */