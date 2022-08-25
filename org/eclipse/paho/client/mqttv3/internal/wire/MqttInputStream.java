package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class MqttInputStream extends InputStream {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private ClientState clientState = null;
  
  private DataInputStream in;
  
  public MqttInputStream(ClientState paramClientState, InputStream paramInputStream) {
    this.clientState = paramClientState;
    this.in = new DataInputStream(paramInputStream);
  }
  
  private void readFully(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (paramInt2 < 0)
      throw new IndexOutOfBoundsException(); 
    int i;
    for (i = 0;; i += j) {
      if (i >= paramInt2)
        return; 
      int j = this.in.read(paramArrayOfbyte, paramInt1 + i, paramInt2 - i);
      this.clientState.notifyReceivedBytes(j);
      if (j < 0)
        throw new EOFException(); 
    } 
  }
  
  public int available() throws IOException {
    return this.in.available();
  }
  
  public void close() throws IOException {
    this.in.close();
  }
  
  public int read() throws IOException {
    return this.in.read();
  }
  
  public MqttWireMessage readMqttWireMessage() throws IOException, MqttException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte b1 = this.in.readByte();
    this.clientState.notifyReceivedBytes(1);
    byte b2 = (byte)(b1 >>> 4 & 0xF);
    if (b2 < 1 || b2 > 14)
      throw ExceptionHelper.createMqttException(32108); 
    long l = MqttWireMessage.readMBI(this.in).getValue();
    byteArrayOutputStream.write(b1);
    byteArrayOutputStream.write(MqttWireMessage.encodeMBI(l));
    byte[] arrayOfByte2 = new byte[(int)(byteArrayOutputStream.size() + l)];
    readFully(arrayOfByte2, byteArrayOutputStream.size(), arrayOfByte2.length - byteArrayOutputStream.size());
    byte[] arrayOfByte1 = byteArrayOutputStream.toByteArray();
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    MqttWireMessage mqttWireMessage = MqttWireMessage.createWireMessage(arrayOfByte2);
    log.fine(CLASS_NAME, "readMqttWireMessage", "501", new Object[] { mqttWireMessage });
    return mqttWireMessage;
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */