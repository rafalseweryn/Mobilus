package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttPersistable;

public class MqttPersistentData implements MqttPersistable {
  private int hLength = 0;
  
  private int hOffset = 0;
  
  private byte[] header = null;
  
  private String key = null;
  
  private int pLength = 0;
  
  private int pOffset = 0;
  
  private byte[] payload = null;
  
  public MqttPersistentData(String paramString, byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3, int paramInt4) {
    this.key = paramString;
    this.header = paramArrayOfbyte1;
    this.hOffset = paramInt1;
    this.hLength = paramInt2;
    this.payload = paramArrayOfbyte2;
    this.pOffset = paramInt3;
    this.pLength = paramInt4;
  }
  
  public byte[] getHeaderBytes() {
    return this.header;
  }
  
  public int getHeaderLength() {
    return this.hLength;
  }
  
  public int getHeaderOffset() {
    return this.hOffset;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public byte[] getPayloadBytes() {
    return this.payload;
  }
  
  public int getPayloadLength() {
    return (this.payload == null) ? 0 : this.pLength;
  }
  
  public int getPayloadOffset() {
    return this.pOffset;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\MqttPersistentData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */