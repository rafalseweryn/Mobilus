package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscribe extends MqttWireMessage {
  private int count;
  
  private String[] names;
  
  private int[] qos;
  
  public MqttSubscribe(byte paramByte, byte[] paramArrayOfbyte) throws IOException {
    super((byte)8);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    this.msgId = dataInputStream.readUnsignedShort();
    paramByte = 0;
    this.count = 0;
    this.names = new String[10];
    this.qos = new int[10];
    while (true) {
      if (paramByte != 0) {
        dataInputStream.close();
        return;
      } 
      try {
        this.names[this.count] = decodeUTF8(dataInputStream);
        int[] arrayOfInt = this.qos;
        int i = this.count;
        this.count = i + 1;
        arrayOfInt[i] = dataInputStream.readByte();
      } catch (Exception exception) {
        paramByte = 1;
      } 
    } 
  }
  
  public MqttSubscribe(String[] paramArrayOfString, int[] paramArrayOfint) {
    super((byte)8);
    this.names = paramArrayOfString;
    this.qos = paramArrayOfint;
    if (paramArrayOfString.length != paramArrayOfint.length)
      throw new IllegalArgumentException(); 
    for (byte b = 0;; b++) {
      if (b >= paramArrayOfint.length)
        return; 
      MqttMessage.validateQos(paramArrayOfint[b]);
    } 
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
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      DataOutputStream dataOutputStream = new DataOutputStream();
      this(byteArrayOutputStream);
      for (byte b = 0;; b++) {
        if (b >= this.names.length)
          return byteArrayOutputStream.toByteArray(); 
        encodeUTF8(dataOutputStream, this.names[b]);
        dataOutputStream.writeByte(this.qos[b]);
      } 
    } catch (IOException iOException) {
      throw new MqttException(iOException);
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
    boolean bool = false;
    for (byte b = 0;; b++) {
      if (b >= this.count) {
        stringBuffer.append("] qos:[");
        for (b = bool;; b++) {
          if (b >= this.count) {
            stringBuffer.append("]");
            return stringBuffer.toString();
          } 
          if (b > 0)
            stringBuffer.append(", "); 
          stringBuffer.append(this.qos[b]);
        } 
        break;
      } 
      if (b > 0)
        stringBuffer.append(", "); 
      stringBuffer.append("\"");
      stringBuffer.append(this.names[b]);
      stringBuffer.append("\"");
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttSubscribe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */