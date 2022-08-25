package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPubComp extends MqttAck {
  public MqttPubComp(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)7);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    this.msgId = dataInputStream.readUnsignedShort();
    dataInputStream.close();
  }
  
  public MqttPubComp(int paramInt) {
    super((byte)7);
    this.msgId = paramInt;
  }
  
  public MqttPubComp(MqttPublish paramMqttPublish) {
    super((byte)7);
    this.msgId = paramMqttPublish.getMessageId();
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    return encodeMessageId();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttPubComp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */