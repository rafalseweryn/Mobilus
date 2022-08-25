package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPubRec extends MqttAck {
  public MqttPubRec(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)5);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    this.msgId = dataInputStream.readUnsignedShort();
    dataInputStream.close();
  }
  
  public MqttPubRec(MqttPublish paramMqttPublish) {
    super((byte)5);
    this.msgId = paramMqttPublish.getMessageId();
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    return encodeMessageId();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttPubRec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */