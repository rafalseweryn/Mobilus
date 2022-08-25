package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class MqttOutputStream extends OutputStream {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private ClientState clientState = null;
  
  private BufferedOutputStream out;
  
  public MqttOutputStream(ClientState paramClientState, OutputStream paramOutputStream) {
    this.clientState = paramClientState;
    this.out = new BufferedOutputStream(paramOutputStream);
  }
  
  public void close() throws IOException {
    this.out.close();
  }
  
  public void flush() throws IOException {
    this.out.flush();
  }
  
  public void write(int paramInt) throws IOException {
    this.out.write(paramInt);
  }
  
  public void write(MqttWireMessage paramMqttWireMessage) throws IOException, MqttException {
    byte[] arrayOfByte1 = paramMqttWireMessage.getHeader();
    byte[] arrayOfByte2 = paramMqttWireMessage.getPayload();
    this.out.write(arrayOfByte1, 0, arrayOfByte1.length);
    this.clientState.notifySentBytes(arrayOfByte1.length);
    byte b = 0;
    while (true) {
      if (b >= arrayOfByte2.length) {
        log.fine(CLASS_NAME, "write", "500", new Object[] { paramMqttWireMessage });
        return;
      } 
      int i = Math.min(1024, arrayOfByte2.length - b);
      this.out.write(arrayOfByte2, b, i);
      b += 1024;
      this.clientState.notifySentBytes(i);
    } 
  }
  
  public void write(byte[] paramArrayOfbyte) throws IOException {
    this.out.write(paramArrayOfbyte);
    this.clientState.notifySentBytes(paramArrayOfbyte.length);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.out.write(paramArrayOfbyte, paramInt1, paramInt2);
    this.clientState.notifySentBytes(paramInt2);
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */