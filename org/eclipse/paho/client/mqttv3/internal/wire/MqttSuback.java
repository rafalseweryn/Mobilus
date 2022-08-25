package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSuback extends MqttAck {
  private int[] grantedQos;
  
  public MqttSuback(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)9);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    this.msgId = dataInputStream.readUnsignedShort();
    this.grantedQos = new int[paramArrayOfbyte.length - 2];
    int i = dataInputStream.read();
    paramByte = 0;
    while (true) {
      if (i == -1) {
        dataInputStream.close();
        return;
      } 
      this.grantedQos[paramByte] = i;
      paramByte++;
      i = dataInputStream.read();
    } 
  }
  
  public int[] getGrantedQos() {
    return this.grantedQos;
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    return new byte[0];
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(super.toString());
    stringBuffer.append(" granted Qos");
    for (byte b = 0;; b++) {
      if (b >= this.grantedQos.length)
        return stringBuffer.toString(); 
      stringBuffer.append(" ");
      stringBuffer.append(this.grantedQos[b]);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttSuback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */