package org.eclipse.paho.client.mqttv3;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttConnectOptions {
  public static final boolean CLEAN_SESSION_DEFAULT = true;
  
  public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
  
  public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
  
  public static final int MQTT_VERSION_3_1 = 3;
  
  public static final int MQTT_VERSION_3_1_1 = 4;
  
  public static final int MQTT_VERSION_DEFAULT = 0;
  
  protected static final int URI_TYPE_LOCAL = 2;
  
  protected static final int URI_TYPE_SSL = 1;
  
  protected static final int URI_TYPE_TCP = 0;
  
  private int MqttVersion = 0;
  
  private boolean cleanSession = true;
  
  private int connectionTimeout = 30;
  
  private int keepAliveInterval = 60;
  
  private char[] password;
  
  private String[] serverURIs = null;
  
  private SocketFactory socketFactory;
  
  private Properties sslClientProps = null;
  
  private String userName;
  
  private String willDestination = null;
  
  private MqttMessage willMessage = null;
  
  protected static int validateURI(String paramString) {
    try {
      URI uRI = new URI();
      this(paramString);
      if (!uRI.getPath().equals("")) {
        illegalArgumentException = new IllegalArgumentException();
        this(paramString);
        throw illegalArgumentException;
      } 
      if (illegalArgumentException.getScheme().equals("tcp"))
        return 0; 
      if (illegalArgumentException.getScheme().equals("ssl"))
        return 1; 
      if (illegalArgumentException.getScheme().equals("local"))
        return 2; 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this(paramString);
      throw illegalArgumentException;
    } catch (URISyntaxException uRISyntaxException) {
      throw new IllegalArgumentException(paramString);
    } 
  }
  
  private void validateWill(String paramString, Object paramObject) {
    if (paramString == null || paramObject == null)
      throw new IllegalArgumentException(); 
    MqttTopic.validate(paramString, false);
  }
  
  public int getConnectionTimeout() {
    return this.connectionTimeout;
  }
  
  public Properties getDebug() {
    String str;
    Properties properties = new Properties();
    properties.put("MqttVersion", new Integer(getMqttVersion()));
    properties.put("CleanSession", Boolean.valueOf(isCleanSession()));
    properties.put("ConTimeout", new Integer(getConnectionTimeout()));
    properties.put("KeepAliveInterval", new Integer(getKeepAliveInterval()));
    if (getUserName() == null) {
      str = "null";
    } else {
      str = getUserName();
    } 
    properties.put("UserName", str);
    if (getWillDestination() == null) {
      str = "null";
    } else {
      str = getWillDestination();
    } 
    properties.put("WillDestination", str);
    if (getSocketFactory() == null) {
      properties.put("SocketFactory", "null");
    } else {
      properties.put("SocketFactory", getSocketFactory());
    } 
    if (getSSLProperties() == null) {
      properties.put("SSLProperties", "null");
    } else {
      properties.put("SSLProperties", getSSLProperties());
    } 
    return properties;
  }
  
  public int getKeepAliveInterval() {
    return this.keepAliveInterval;
  }
  
  public int getMqttVersion() {
    return this.MqttVersion;
  }
  
  public char[] getPassword() {
    return this.password;
  }
  
  public Properties getSSLProperties() {
    return this.sslClientProps;
  }
  
  public String[] getServerURIs() {
    return this.serverURIs;
  }
  
  public SocketFactory getSocketFactory() {
    return this.socketFactory;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public String getWillDestination() {
    return this.willDestination;
  }
  
  public MqttMessage getWillMessage() {
    return this.willMessage;
  }
  
  public boolean isCleanSession() {
    return this.cleanSession;
  }
  
  public void setCleanSession(boolean paramBoolean) {
    this.cleanSession = paramBoolean;
  }
  
  public void setConnectionTimeout(int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentException(); 
    this.connectionTimeout = paramInt;
  }
  
  public void setKeepAliveInterval(int paramInt) throws IllegalArgumentException {
    if (paramInt < 0)
      throw new IllegalArgumentException(); 
    this.keepAliveInterval = paramInt;
  }
  
  public void setMqttVersion(int paramInt) throws IllegalArgumentException {
    if (paramInt != 0 && paramInt != 3 && paramInt != 4)
      throw new IllegalArgumentException(); 
    this.MqttVersion = paramInt;
  }
  
  public void setPassword(char[] paramArrayOfchar) {
    this.password = paramArrayOfchar;
  }
  
  public void setSSLProperties(Properties paramProperties) {
    this.sslClientProps = paramProperties;
  }
  
  public void setServerURIs(String[] paramArrayOfString) {
    for (byte b = 0;; b++) {
      if (b >= paramArrayOfString.length) {
        this.serverURIs = paramArrayOfString;
        return;
      } 
      validateURI(paramArrayOfString[b]);
    } 
  }
  
  public void setSocketFactory(SocketFactory paramSocketFactory) {
    this.socketFactory = paramSocketFactory;
  }
  
  public void setUserName(String paramString) {
    if (paramString != null && paramString.trim().equals(""))
      throw new IllegalArgumentException(); 
    this.userName = paramString;
  }
  
  protected void setWill(String paramString, MqttMessage paramMqttMessage, int paramInt, boolean paramBoolean) {
    this.willDestination = paramString;
    this.willMessage = paramMqttMessage;
    this.willMessage.setQos(paramInt);
    this.willMessage.setRetained(paramBoolean);
    this.willMessage.setMutable(false);
  }
  
  public void setWill(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) {
    validateWill(paramString, paramArrayOfbyte);
    setWill(paramString, new MqttMessage(paramArrayOfbyte), paramInt, paramBoolean);
  }
  
  public void setWill(MqttTopic paramMqttTopic, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) {
    String str = paramMqttTopic.getName();
    validateWill(str, paramArrayOfbyte);
    setWill(str, new MqttMessage(paramArrayOfbyte), paramInt, paramBoolean);
  }
  
  public String toString() {
    return Debug.dumpProperties(getDebug(), "Connection options");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttConnectOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */