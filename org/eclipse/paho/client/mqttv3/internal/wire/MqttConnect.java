package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttConnect extends MqttWireMessage {
  public static final String KEY = "Con";
  
  private int MqttVersion;
  
  private boolean cleanSession;
  
  private String clientId;
  
  private int keepAliveInterval;
  
  private char[] password;
  
  private String userName;
  
  private String willDestination;
  
  private MqttMessage willMessage;
  
  public MqttConnect(byte paramByte, byte[] paramArrayOfbyte) throws IOException, MqttException {
    super((byte)1);
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    decodeUTF8(dataInputStream);
    dataInputStream.readByte();
    dataInputStream.readByte();
    this.keepAliveInterval = dataInputStream.readUnsignedShort();
    this.clientId = decodeUTF8(dataInputStream);
    dataInputStream.close();
  }
  
  public MqttConnect(String paramString1, int paramInt1, boolean paramBoolean, int paramInt2, String paramString2, char[] paramArrayOfchar, MqttMessage paramMqttMessage, String paramString3) {
    super((byte)1);
    this.clientId = paramString1;
    this.cleanSession = paramBoolean;
    this.keepAliveInterval = paramInt2;
    this.userName = paramString2;
    this.password = paramArrayOfchar;
    this.willMessage = paramMqttMessage;
    this.willDestination = paramString3;
    this.MqttVersion = paramInt1;
  }
  
  public String getKey() {
    return "Con";
  }
  
  protected byte getMessageInfo() {
    return 0;
  }
  
  public byte[] getPayload() throws MqttException {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      DataOutputStream dataOutputStream = new DataOutputStream();
      this(byteArrayOutputStream);
      encodeUTF8(dataOutputStream, this.clientId);
      if (this.willMessage != null) {
        encodeUTF8(dataOutputStream, this.willDestination);
        dataOutputStream.writeShort((this.willMessage.getPayload()).length);
        dataOutputStream.write(this.willMessage.getPayload());
      } 
      if (this.userName != null) {
        encodeUTF8(dataOutputStream, this.userName);
        if (this.password != null) {
          String str = new String();
          this(this.password);
          encodeUTF8(dataOutputStream, str);
        } 
      } 
      dataOutputStream.flush();
      return byteArrayOutputStream.toByteArray();
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
      if (this.MqttVersion == 3) {
        encodeUTF8(dataOutputStream, "MQIsdp");
      } else if (this.MqttVersion == 4) {
        encodeUTF8(dataOutputStream, "MQTT");
      } 
      dataOutputStream.write(this.MqttVersion);
      byte b1 = 0;
      if (this.cleanSession)
        b1 = (byte)2; 
      byte b2 = b1;
      if (this.willMessage != null) {
        b1 = (byte)((byte)(b1 | 0x4) | this.willMessage.getQos() << 3);
        b2 = b1;
        if (this.willMessage.isRetained())
          b2 = (byte)(b1 | 0x20); 
      } 
      b1 = b2;
      if (this.userName != null) {
        b2 = (byte)(b2 | 0x80);
        b1 = b2;
        if (this.password != null)
          b1 = (byte)(b2 | 0x40); 
      } 
      dataOutputStream.write(b1);
      dataOutputStream.writeShort(this.keepAliveInterval);
      dataOutputStream.flush();
      return byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      throw new MqttException(iOException);
    } 
  }
  
  public boolean isCleanSession() {
    return this.cleanSession;
  }
  
  public boolean isMessageIdRequired() {
    return false;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
    stringBuffer.append(" clientId ");
    stringBuffer.append(this.clientId);
    stringBuffer.append(" keepAliveInterval ");
    stringBuffer.append(this.keepAliveInterval);
    return stringBuffer.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttConnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */