package org.eclipse.paho.client.mqttv3.internal.wire;

public class MultiByteInteger {
  private int length;
  
  private long value;
  
  public MultiByteInteger(long paramLong) {
    this(paramLong, -1);
  }
  
  public MultiByteInteger(long paramLong, int paramInt) {
    this.value = paramLong;
    this.length = paramInt;
  }
  
  public int getEncodedLength() {
    return this.length;
  }
  
  public long getValue() {
    return this.value;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MultiByteInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */