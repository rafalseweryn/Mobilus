package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublish extends MqttPersistableWireMessage {
  private byte[] encodedPayload = null;
  
  private MqttMessage message;
  
  private String topicName;
  
  public MqttPublish(byte paramByte, byte[] paramArrayOfbyte) throws MqttException, IOException {
    super((byte)3);
    this.message = new MqttReceivedMessage();
    this.message.setQos(0x3 & paramByte >> 1);
    if ((paramByte & 0x1) == 1)
      this.message.setRetained(true); 
    if ((paramByte & 0x8) == 8)
      ((MqttReceivedMessage)this.message).setDuplicate(true); 
    CountingInputStream countingInputStream = new CountingInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    DataInputStream dataInputStream = new DataInputStream(countingInputStream);
    this.topicName = decodeUTF8(dataInputStream);
    if (this.message.getQos() > 0)
      this.msgId = dataInputStream.readUnsignedShort(); 
    paramArrayOfbyte = new byte[paramArrayOfbyte.length - countingInputStream.getCounter()];
    dataInputStream.readFully(paramArrayOfbyte);
    dataInputStream.close();
    this.message.setPayload(paramArrayOfbyte);
  }
  
  public MqttPublish(String paramString, MqttMessage paramMqttMessage) {
    super((byte)3);
    this.topicName = paramString;
    this.message = paramMqttMessage;
  }
  
  protected static byte[] encodePayload(MqttMessage paramMqttMessage) {
    return paramMqttMessage.getPayload();
  }
  
  public MqttMessage getMessage() {
    return this.message;
  }
  
  protected byte getMessageInfo() {
    byte b = (byte)(this.message.getQos() << 1);
    byte b1 = b;
    if (this.message.isRetained())
      b1 = (byte)(b | 0x1); 
    if (!this.message.isDuplicate()) {
      byte b2 = b1;
      if (this.duplicate) {
        b1 = (byte)(b1 | 0x8);
        return b1;
      } 
      return b2;
    } 
    b1 = (byte)(b1 | 0x8);
    return b1;
  }
  
  public byte[] getPayload() throws MqttException {
    if (this.encodedPayload == null)
      this.encodedPayload = encodePayload(this.message); 
    return this.encodedPayload;
  }
  
  public int getPayloadLength() {
    boolean bool;
    try {
      bool = (getPayload()).length;
    } catch (MqttException mqttException) {
      bool = false;
    } 
    return bool;
  }
  
  public String getTopicName() {
    return this.topicName;
  }
  
  protected byte[] getVariableHeader() throws MqttException {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      DataOutputStream dataOutputStream = new DataOutputStream();
      this(byteArrayOutputStream);
      encodeUTF8(dataOutputStream, this.topicName);
      if (this.message.getQos() > 0)
        dataOutputStream.writeShort(this.msgId); 
      dataOutputStream.flush();
      return byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      throw new MqttException(iOException);
    } 
  }
  
  public boolean isMessageIdRequired() {
    return true;
  }
  
  public void setMessageId(int paramInt) {
    super.setMessageId(paramInt);
    if (this.message instanceof MqttReceivedMessage)
      ((MqttReceivedMessage)this.message).setMessageId(paramInt); 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    byte[] arrayOfByte = this.message.getPayload();
    int i = Math.min(arrayOfByte.length, 20);
    for (byte b = 0;; b++) {
      if (b >= i) {
        String str;
        try {
          str = new String();
          this(arrayOfByte, 0, i, "UTF-8");
        } catch (Exception exception) {
          str = "?";
        } 
        StringBuffer stringBuffer1 = new StringBuffer();
        stringBuffer1.append(super.toString());
        stringBuffer1.append(" qos:");
        stringBuffer1.append(this.message.getQos());
        if (this.message.getQos() > 0) {
          stringBuffer1.append(" msgId:");
          stringBuffer1.append(this.msgId);
        } 
        stringBuffer1.append(" retained:");
        stringBuffer1.append(this.message.isRetained());
        stringBuffer1.append(" dup:");
        stringBuffer1.append(this.duplicate);
        stringBuffer1.append(" topic:\"");
        stringBuffer1.append(this.topicName);
        stringBuffer1.append("\"");
        stringBuffer1.append(" payload:[hex:");
        stringBuffer1.append(stringBuffer);
        stringBuffer1.append(" utf8:\"");
        stringBuffer1.append(str);
        stringBuffer1.append("\"");
        stringBuffer1.append(" length:");
        stringBuffer1.append(arrayOfByte.length);
        stringBuffer1.append("]");
        return stringBuffer1.toString();
      } 
      String str2 = Integer.toHexString(arrayOfByte[b]);
      String str1 = str2;
      if (str2.length() == 1) {
        StringBuffer stringBuffer1 = new StringBuffer("0");
        stringBuffer1.append(str2);
        str1 = stringBuffer1.toString();
      } 
      stringBuffer.append(str1);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttPublish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */