package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends InputStream {
  private int counter;
  
  private InputStream in;
  
  public CountingInputStream(InputStream paramInputStream) {
    this.in = paramInputStream;
    this.counter = 0;
  }
  
  public int getCounter() {
    return this.counter;
  }
  
  public int read() throws IOException {
    int i = this.in.read();
    if (i != -1)
      this.counter++; 
    return i;
  }
  
  public void resetCounter() {
    this.counter = 0;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\CountingInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */