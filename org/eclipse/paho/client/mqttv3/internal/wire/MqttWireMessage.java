package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;

public abstract class MqttWireMessage {
  public static final byte MESSAGE_TYPE_CONNACK = 2;
  
  public static final byte MESSAGE_TYPE_CONNECT = 1;
  
  public static final byte MESSAGE_TYPE_DISCONNECT = 14;
  
  public static final byte MESSAGE_TYPE_PINGREQ = 12;
  
  public static final byte MESSAGE_TYPE_PINGRESP = 13;
  
  public static final byte MESSAGE_TYPE_PUBACK = 4;
  
  public static final byte MESSAGE_TYPE_PUBCOMP = 7;
  
  public static final byte MESSAGE_TYPE_PUBLISH = 3;
  
  public static final byte MESSAGE_TYPE_PUBREC = 5;
  
  public static final byte MESSAGE_TYPE_PUBREL = 6;
  
  public static final byte MESSAGE_TYPE_SUBACK = 9;
  
  public static final byte MESSAGE_TYPE_SUBSCRIBE = 8;
  
  public static final byte MESSAGE_TYPE_UNSUBACK = 11;
  
  public static final byte MESSAGE_TYPE_UNSUBSCRIBE = 10;
  
  private static final String[] PACKET_NAMES = new String[] { 
      "reserved", "CONNECT", "CONNACK", "PUBLISH", "PUBACK", "PUBREC", "PUBREL", "PUBCOMP", "SUBSCRIBE", "SUBACK", 
      "UNSUBSCRIBE", "UNSUBACK", "PINGREQ", "PINGRESP", "DISCONNECT" };
  
  protected static final String STRING_ENCODING = "UTF-8";
  
  protected boolean duplicate = false;
  
  protected int msgId;
  
  private byte type;
  
  public MqttWireMessage(byte paramByte) {
    this.type = (byte)paramByte;
    this.msgId = 0;
  }
  
  private static MqttWireMessage createWireMessage(InputStream paramInputStream) throws MqttException {
    try {
      MqttConnect mqttConnect;
      CountingInputStream countingInputStream = new CountingInputStream();
      this(paramInputStream);
      DataInputStream dataInputStream = new DataInputStream();
      this(countingInputStream);
      int i = dataInputStream.readUnsignedByte();
      byte b1 = (byte)(i >> 4);
      byte b2 = (byte)(i & 0xF);
      long l = readMBI(dataInputStream).getValue();
      l = countingInputStream.getCounter() + l - countingInputStream.getCounter();
      byte[] arrayOfByte = new byte[0];
      if (l > 0L) {
        arrayOfByte = new byte[(int)l];
        dataInputStream.readFully(arrayOfByte, 0, arrayOfByte.length);
      } 
      if (b1 == 1) {
        mqttConnect = new MqttConnect(b2, arrayOfByte);
      } else {
        MqttPublish mqttPublish;
        if (b1 == 3) {
          mqttPublish = new MqttPublish(b2, (byte[])mqttConnect);
        } else {
          MqttPubAck mqttPubAck;
          if (b1 == 4) {
            mqttPubAck = new MqttPubAck(b2, (byte[])mqttPublish);
          } else {
            MqttPubComp mqttPubComp;
            if (b1 == 7) {
              mqttPubComp = new MqttPubComp(b2, (byte[])mqttPubAck);
            } else {
              MqttConnack mqttConnack;
              if (b1 == 2) {
                mqttConnack = new MqttConnack(b2, (byte[])mqttPubComp);
              } else {
                MqttPingReq mqttPingReq;
                if (b1 == 12) {
                  mqttPingReq = new MqttPingReq(b2, (byte[])mqttConnack);
                } else {
                  MqttPingResp mqttPingResp;
                  if (b1 == 13) {
                    mqttPingResp = new MqttPingResp(b2, (byte[])mqttPingReq);
                  } else {
                    MqttSubscribe mqttSubscribe;
                    if (b1 == 8) {
                      mqttSubscribe = new MqttSubscribe(b2, (byte[])mqttPingResp);
                    } else {
                      MqttSuback mqttSuback;
                      if (b1 == 9) {
                        mqttSuback = new MqttSuback(b2, (byte[])mqttSubscribe);
                      } else {
                        MqttUnsubscribe mqttUnsubscribe;
                        if (b1 == 10) {
                          mqttUnsubscribe = new MqttUnsubscribe(b2, (byte[])mqttSuback);
                        } else {
                          MqttUnsubAck mqttUnsubAck;
                          if (b1 == 11) {
                            mqttUnsubAck = new MqttUnsubAck(b2, (byte[])mqttUnsubscribe);
                          } else {
                            MqttPubRel mqttPubRel;
                            if (b1 == 6) {
                              mqttPubRel = new MqttPubRel(b2, (byte[])mqttUnsubAck);
                            } else {
                              MqttPubRec mqttPubRec;
                              if (b1 == 5) {
                                mqttPubRec = new MqttPubRec(b2, (byte[])mqttPubRel);
                              } else {
                                if (b1 == 14)
                                  return new MqttDisconnect(b2, (byte[])mqttPubRec); 
                                throw ExceptionHelper.createMqttException(6);
                              } 
                            } 
                          } 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
      return mqttConnect;
    } catch (IOException iOException) {
      throw new MqttException(iOException);
    } 
  }
  
  public static MqttWireMessage createWireMessage(MqttPersistable paramMqttPersistable) throws MqttException {
    byte[] arrayOfByte1 = paramMqttPersistable.getPayloadBytes();
    byte[] arrayOfByte2 = arrayOfByte1;
    if (arrayOfByte1 == null)
      arrayOfByte2 = new byte[0]; 
    return createWireMessage(new MultiByteArrayInputStream(paramMqttPersistable.getHeaderBytes(), paramMqttPersistable.getHeaderOffset(), paramMqttPersistable.getHeaderLength(), arrayOfByte2, paramMqttPersistable.getPayloadOffset(), paramMqttPersistable.getPayloadLength()));
  }
  
  public static MqttWireMessage createWireMessage(byte[] paramArrayOfbyte) throws MqttException {
    return createWireMessage(new ByteArrayInputStream(paramArrayOfbyte));
  }
  
  protected static byte[] encodeMBI(long paramLong) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    int i = 0;
    while (true) {
      byte b = (byte)(int)(paramLong % 128L);
      paramLong /= 128L;
      int j = paramLong cmp 0L;
      int k = b;
      if (j > 0)
        k = (byte)(b | 0x80); 
      byteArrayOutputStream.write(k);
      k = i + 1;
      if (j > 0) {
        i = k;
        if (k >= 4)
          break; 
        continue;
      } 
      break;
    } 
    return byteArrayOutputStream.toByteArray();
  }
  
  protected static MultiByteInteger readMBI(DataInputStream paramDataInputStream) throws IOException {
    long l = 0L;
    int i = 0;
    int j = 1;
    while (true) {
      byte b = paramDataInputStream.readByte();
      int k = i + 1;
      long l1 = l + ((b & Byte.MAX_VALUE) * j);
      j *= 128;
      l = l1;
      i = k;
      if ((b & 0x80) == 0)
        return new MultiByteInteger(l1, k); 
    } 
  }
  
  protected String decodeUTF8(DataInputStream paramDataInputStream) throws MqttException {
    try {
      byte[] arrayOfByte = new byte[paramDataInputStream.readUnsignedShort()];
      paramDataInputStream.readFully(arrayOfByte);
      return new String(arrayOfByte, "UTF-8");
    } catch (IOException iOException) {
      throw new MqttException(iOException);
    } 
  }
  
  protected byte[] encodeMessageId() throws MqttException {
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
  
  protected void encodeUTF8(DataOutputStream paramDataOutputStream, String paramString) throws MqttException {
    try {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      byte b1 = (byte)(arrayOfByte.length >>> 8 & 0xFF);
      byte b2 = (byte)(arrayOfByte.length >>> 0 & 0xFF);
      paramDataOutputStream.write(b1);
      paramDataOutputStream.write(b2);
      paramDataOutputStream.write(arrayOfByte);
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new MqttException(unsupportedEncodingException);
    } catch (IOException iOException) {
      throw new MqttException(iOException);
    } 
  }
  
  public byte[] getHeader() throws MqttException {
    try {
      byte b1 = getType();
      byte b2 = getMessageInfo();
      null = getVariableHeader();
      int i = null.length;
      int j = (getPayload()).length;
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      DataOutputStream dataOutputStream = new DataOutputStream();
      this(byteArrayOutputStream);
      dataOutputStream.writeByte((b1 & 0xF) << 4 ^ b2 & 0xF);
      dataOutputStream.write(encodeMBI((i + j)));
      dataOutputStream.write(null);
      dataOutputStream.flush();
      return byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      throw new MqttException(iOException);
    } 
  }
  
  public String getKey() {
    return (new Integer(getMessageId())).toString();
  }
  
  public int getMessageId() {
    return this.msgId;
  }
  
  protected abstract byte getMessageInfo();
  
  public byte[] getPayload() throws MqttException {
    return new byte[0];
  }
  
  public byte getType() {
    return this.type;
  }
  
  protected abstract byte[] getVariableHeader() throws MqttException;
  
  public boolean isMessageIdRequired() {
    return true;
  }
  
  public boolean isRetryable() {
    return false;
  }
  
  public void setDuplicate(boolean paramBoolean) {
    this.duplicate = paramBoolean;
  }
  
  public void setMessageId(int paramInt) {
    this.msgId = paramInt;
  }
  
  public String toString() {
    return PACKET_NAMES[this.type];
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttWireMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */