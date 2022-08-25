package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class MultiByteArrayInputStream extends InputStream {
  private byte[] bytesA;
  
  private byte[] bytesB;
  
  private int lengthA;
  
  private int lengthB;
  
  private int offsetA;
  
  private int offsetB;
  
  private int pos = 0;
  
  public MultiByteArrayInputStream(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3, int paramInt4) {
    this.bytesA = paramArrayOfbyte1;
    this.bytesB = paramArrayOfbyte2;
    this.offsetA = paramInt1;
    this.offsetB = paramInt3;
    this.lengthA = paramInt2;
    this.lengthB = paramInt4;
  }
  
  public int read() throws IOException {
    byte b;
    if (this.pos < this.lengthA) {
      b = this.bytesA[this.offsetA + this.pos];
    } else if (this.pos < this.lengthA + this.lengthB) {
      b = this.bytesB[this.offsetB + this.pos - this.lengthA];
    } else {
      return -1;
    } 
    int i = b;
    if (b < 0)
      i = b + 256; 
    this.pos++;
    return i;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MultiByteArrayInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */