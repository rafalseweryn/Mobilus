package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttUnsubAck extends MqttAck {
  public MqttUnsubAck(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)11);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    this.msgId = dataInputStream.readUnsignedShort();
    dataInputStream.close();
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    return new byte[0];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttUnsubAck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */