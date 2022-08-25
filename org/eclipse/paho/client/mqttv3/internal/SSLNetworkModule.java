package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class SSLNetworkModule extends TCPNetworkModule {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private String[] enabledCiphers;
  
  private int handshakeTimeoutSecs;
  
  public SSLNetworkModule(SSLSocketFactory paramSSLSocketFactory, String paramString1, int paramInt, String paramString2) {
    super(paramSSLSocketFactory, paramString1, paramInt, paramString2);
    log.setResourceName(paramString2);
  }
  
  public String[] getEnabledCiphers() {
    return this.enabledCiphers;
  }
  
  public void setEnabledCiphers(String[] paramArrayOfString) {
    this.enabledCiphers = paramArrayOfString;
    if (this.socket != null && paramArrayOfString != null) {
      if (log.isLoggable(5)) {
        String str = "";
        for (byte b = 0;; b++) {
          if (b >= paramArrayOfString.length) {
            log.fine(CLASS_NAME, "setEnabledCiphers", "260", new Object[] { str });
            break;
          } 
          String str2 = str;
          if (b > 0) {
            StringBuffer stringBuffer1 = new StringBuffer(String.valueOf(str));
            stringBuffer1.append(",");
            str2 = stringBuffer1.toString();
          } 
          StringBuffer stringBuffer = new StringBuffer(String.valueOf(str2));
          stringBuffer.append(paramArrayOfString[b]);
          String str1 = stringBuffer.toString();
        } 
      } 
      ((SSLSocket)this.socket).setEnabledCipherSuites(paramArrayOfString);
    } 
  }
  
  public void setSSLhandshakeTimeout(int paramInt) {
    setConnectTimeout(paramInt);
    this.handshakeTimeoutSecs = paramInt;
  }
  
  public void start() throws IOException, MqttException {
    super.start();
    setEnabledCiphers(this.enabledCiphers);
    int i = this.socket.getSoTimeout();
    if (i == 0)
      this.socket.setSoTimeout(this.handshakeTimeoutSecs * 1000); 
    ((SSLSocket)this.socket).startHandshake();
    this.socket.setSoTimeout(i);
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\SSLNetworkModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */