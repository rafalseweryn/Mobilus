package org.eclipse.paho.client.mqttv3;

public class MqttMessage {
  private boolean dup = false;
  
  private boolean mutable = true;
  
  private byte[] payload;
  
  private int qos = 1;
  
  private boolean retained = false;
  
  public MqttMessage() {
    setPayload(new byte[0]);
  }
  
  public MqttMessage(byte[] paramArrayOfbyte) {
    setPayload(paramArrayOfbyte);
  }
  
  public static void validateQos(int paramInt) {
    if (paramInt < 0 || paramInt > 2)
      throw new IllegalArgumentException(); 
  }
  
  protected void checkMutable() throws IllegalStateException {
    if (!this.mutable)
      throw new IllegalStateException(); 
  }
  
  public void clearPayload() {
    checkMutable();
    this.payload = new byte[0];
  }
  
  public byte[] getPayload() {
    return this.payload;
  }
  
  public int getQos() {
    return this.qos;
  }
  
  public boolean isDuplicate() {
    return this.dup;
  }
  
  public boolean isRetained() {
    return this.retained;
  }
  
  protected void setDuplicate(boolean paramBoolean) {
    this.dup = paramBoolean;
  }
  
  protected void setMutable(boolean paramBoolean) {
    this.mutable = paramBoolean;
  }
  
  public void setPayload(byte[] paramArrayOfbyte) {
    checkMutable();
    if (paramArrayOfbyte == null)
      throw new NullPointerException(); 
    this.payload = paramArrayOfbyte;
  }
  
  public void setQos(int paramInt) {
    checkMutable();
    validateQos(paramInt);
    this.qos = paramInt;
  }
  
  public void setRetained(boolean paramBoolean) {
    checkMutable();
    this.retained = paramBoolean;
  }
  
  public String toString() {
    return new String(this.payload);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */