package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttUnsubscribe extends MqttWireMessage {
  private int count;
  
  private String[] names;
  
  public MqttUnsubscribe(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)10);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    this.msgId = dataInputStream.readUnsignedShort();
    paramByte = 0;
    this.count = 0;
    this.names = new String[10];
    while (true) {
      if (paramByte != 0) {
        dataInputStream.close();
        return;
      } 
      try {
        this.names[this.count] = decodeUTF8(dataInputStream);
      } catch (Exception exception) {
        paramByte = 1;
      } 
    } 
  }
  
  public MqttUnsubscribe(String[] paramArrayOfString) {
    super((byte)10);
    this.names = paramArrayOfString;
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
  
  public byte[] getPayload() throws MqttException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    for (byte b = 0;; b++) {
      if (b >= this.names.length)
        return byteArrayOutputStream.toByteArray(); 
      encodeUTF8(dataOutputStream, this.names[b]);
    } 
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      DataOutputStream dataOutputStream = new DataOutputStream();
      this(byteArrayOutputStream);
      dataOutputStream.writeShort(this.msgId);
      dataOutputStream.flush();
      return byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      throw new MqttException(iOException);
    } 
  }
  
  public boolean isRetryable() {
    return true;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(super.toString());
    stringBuffer.append(" names:[");
    for (byte b = 0;; b++) {
      if (b >= this.count) {
        stringBuffer.append("]");
        return stringBuffer.toString();
      } 
      if (b > 0)
        stringBuffer.append(", "); 
      StringBuffer stringBuffer1 = new StringBuffer("\"");
      stringBuffer1.append(this.names[b]);
      stringBuffer1.append("\"");
      stringBuffer.append(stringBuffer1.toString());
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttUnsubscribe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */