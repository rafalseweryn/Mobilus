package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class TCPNetworkModule implements NetworkModule {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private int conTimeout;
  
  private SocketFactory factory;
  
  private String host;
  
  private int port;
  
  protected Socket socket;
  
  public TCPNetworkModule(SocketFactory paramSocketFactory, String paramString1, int paramInt, String paramString2) {
    log.setResourceName(paramString2);
    this.factory = paramSocketFactory;
    this.host = paramString1;
    this.port = paramInt;
  }
  
  public InputStream getInputStream() throws IOException {
    return this.socket.getInputStream();
  }
  
  public OutputStream getOutputStream() throws IOException {
    return this.socket.getOutputStream();
  }
  
  public void setConnectTimeout(int paramInt) {
    this.conTimeout = paramInt;
  }
  
  public void start() throws IOException, MqttException {
    try {
      Logger logger = log;
      String str1 = CLASS_NAME;
      String str2 = this.host;
      Integer integer = new Integer();
      this(this.port);
      Long long_ = new Long();
      this((this.conTimeout * 1000));
      logger.fine(str1, "start", "252", new Object[] { str2, integer, long_ });
      InetSocketAddress inetSocketAddress = new InetSocketAddress();
      this(this.host, this.port);
      this.socket = this.factory.createSocket();
      this.socket.connect(inetSocketAddress, this.conTimeout * 1000);
      return;
    } catch (ConnectException connectException) {
      log.fine(CLASS_NAME, "start", "250", null, connectException);
      throw new MqttException(32103, connectException);
    } 
  }
  
  public void stop() throws IOException {
    if (this.socket != null)
      this.socket.close(); 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\TCPNetworkModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */