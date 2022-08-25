package org.eclipse.paho.client.mqttv3;

import java.util.Hashtable;
import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.LocalNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttAsyncClient implements IMqttAsyncClient {
  private static final String CLASS_NAME;
  
  private static final String CLIENT_ID_PREFIX = "paho";
  
  private static final long DISCONNECT_TIMEOUT = 10000L;
  
  private static final char MAX_HIGH_SURROGATE = '?';
  
  private static final char MIN_HIGH_SURROGATE = '?';
  
  private static final long QUIESCE_TIMEOUT = 30000L;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private String clientId;
  
  protected ClientComms comms;
  
  private MqttClientPersistence persistence;
  
  private String serverURI;
  
  private Hashtable topics;
  
  public MqttAsyncClient(String paramString1, String paramString2) throws MqttException {
    this(paramString1, paramString2, (MqttClientPersistence)new MqttDefaultFilePersistence());
  }
  
  public MqttAsyncClient(String paramString1, String paramString2, MqttClientPersistence paramMqttClientPersistence) throws MqttException {
    this(paramString1, paramString2, paramMqttClientPersistence, new TimerPingSender());
  }
  
  public MqttAsyncClient(String paramString1, String paramString2, MqttClientPersistence paramMqttClientPersistence, MqttPingSender paramMqttPingSender) throws MqttException {
    log.setResourceName(paramString2);
    if (paramString2 == null)
      throw new IllegalArgumentException("Null clientId"); 
    int i = 0;
    int j = i;
    while (true) {
      if (i >= paramString2.length() - 1) {
        if (j > 65535)
          throw new IllegalArgumentException("ClientId longer than 65535 characters"); 
        MqttConnectOptions.validateURI(paramString1);
        this.serverURI = paramString1;
        this.clientId = paramString2;
        this.persistence = paramMqttClientPersistence;
        if (this.persistence == null)
          this.persistence = (MqttClientPersistence)new MemoryPersistence(); 
        log.fine(CLASS_NAME, "MqttAsyncClient", "101", new Object[] { paramString2, paramString1, paramMqttClientPersistence });
        this.persistence.open(paramString2, paramString1);
        this.comms = new ClientComms(this, this.persistence, paramMqttPingSender);
        this.persistence.close();
        this.topics = new Hashtable<>();
        return;
      } 
      int k = i;
      if (Character_isHighSurrogate(paramString2.charAt(i)))
        k = i + 1; 
      j++;
      i = k + 1;
    } 
  }
  
  protected static boolean Character_isHighSurrogate(char paramChar) {
    return (paramChar >= '?' && paramChar <= '?');
  }
  
  private NetworkModule createNetworkModule(String paramString, MqttConnectOptions paramMqttConnectOptions) throws MqttException, MqttSecurityException {
    SSLNetworkModule sSLNetworkModule1;
    SSLNetworkModule sSLNetworkModule2;
    SSLNetworkModule sSLNetworkModule3;
    log.fine(CLASS_NAME, "createNetworkModule", "115", new Object[] { paramString });
    SocketFactory socketFactory = paramMqttConnectOptions.getSocketFactory();
    int i = MqttConnectOptions.validateURI(paramString);
    String str2 = null;
    switch (i) {
      default:
        return (NetworkModule)str2;
      case 2:
        return (NetworkModule)new LocalNetworkModule(paramString.substring(8));
      case 1:
        paramString = paramString.substring(6);
        str2 = getHostName(paramString);
        i = getPort(paramString, 8883);
        if (socketFactory == null) {
          SSLSocketFactoryFactory sSLSocketFactoryFactory = new SSLSocketFactoryFactory();
          Properties properties = paramMqttConnectOptions.getSSLProperties();
          if (properties != null)
            sSLSocketFactoryFactory.initialize(properties, null); 
          socketFactory = sSLSocketFactoryFactory.createSocketFactory(null);
        } else {
          if (!(socketFactory instanceof SSLSocketFactory))
            throw ExceptionHelper.createMqttException(32105); 
          paramString = null;
        } 
        sSLNetworkModule2 = new SSLNetworkModule((SSLSocketFactory)socketFactory, str2, i, this.clientId);
        sSLNetworkModule3 = sSLNetworkModule2;
        sSLNetworkModule3.setSSLhandshakeTimeout(paramMqttConnectOptions.getConnectionTimeout());
        if (paramString != null) {
          String[] arrayOfString = paramString.getEnabledCipherSuites(null);
          if (arrayOfString != null)
            sSLNetworkModule3.setEnabledCiphers(arrayOfString); 
        } 
        return (NetworkModule)sSLNetworkModule2;
      case 0:
        break;
    } 
    paramString = paramString.substring(6);
    String str1 = getHostName(paramString);
    i = getPort(paramString, 1883);
    if (sSLNetworkModule2 == null) {
      SocketFactory socketFactory1 = SocketFactory.getDefault();
    } else {
      sSLNetworkModule1 = sSLNetworkModule2;
      if (sSLNetworkModule2 instanceof SSLSocketFactory)
        throw ExceptionHelper.createMqttException(32105); 
    } 
    TCPNetworkModule tCPNetworkModule = new TCPNetworkModule((SocketFactory)sSLNetworkModule1, str1, i, this.clientId);
    tCPNetworkModule.setConnectTimeout(paramMqttConnectOptions.getConnectionTimeout());
    return (NetworkModule)tCPNetworkModule;
  }
  
  public static String generateClientId() {
    StringBuffer stringBuffer = new StringBuffer("paho");
    stringBuffer.append(System.nanoTime());
    return stringBuffer.toString();
  }
  
  private String getHostName(String paramString) {
    int i = paramString.lastIndexOf('/');
    int j = paramString.lastIndexOf(':');
    int k = j;
    if (j == -1)
      k = paramString.length(); 
    return paramString.substring(i + 1, k);
  }
  
  private int getPort(String paramString, int paramInt) {
    int i = paramString.lastIndexOf(':');
    if (i != -1)
      paramInt = Integer.parseInt(paramString.substring(i + 1)); 
    return paramInt;
  }
  
  public IMqttToken checkPing(Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    log.fine(CLASS_NAME, "ping", "117");
    paramObject = this.comms.checkForActivity();
    log.fine(CLASS_NAME, "ping", "118");
    return (IMqttToken)paramObject;
  }
  
  public void close() throws MqttException {
    log.fine(CLASS_NAME, "close", "113");
    this.comms.close();
    log.fine(CLASS_NAME, "close", "114");
  }
  
  public IMqttToken connect() throws MqttException, MqttSecurityException {
    return connect(null, null);
  }
  
  public IMqttToken connect(Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttSecurityException {
    return connect(new MqttConnectOptions(), paramObject, paramIMqttActionListener);
  }
  
  public IMqttToken connect(MqttConnectOptions paramMqttConnectOptions) throws MqttException, MqttSecurityException {
    return connect(paramMqttConnectOptions, null, null);
  }
  
  public IMqttToken connect(MqttConnectOptions paramMqttConnectOptions, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttSecurityException {
    String str3;
    String str4;
    if (this.comms.isConnected())
      throw ExceptionHelper.createMqttException(32100); 
    if (this.comms.isConnecting())
      throw new MqttException(32110); 
    if (this.comms.isDisconnecting())
      throw new MqttException(32102); 
    if (this.comms.isClosed())
      throw new MqttException(32111); 
    Logger logger = log;
    String str1 = CLASS_NAME;
    boolean bool = paramMqttConnectOptions.isCleanSession();
    Integer integer1 = new Integer(paramMqttConnectOptions.getConnectionTimeout());
    Integer integer2 = new Integer(paramMqttConnectOptions.getKeepAliveInterval());
    String str2 = paramMqttConnectOptions.getUserName();
    if (paramMqttConnectOptions.getPassword() == null) {
      str3 = "[null]";
    } else {
      str3 = "[notnull]";
    } 
    if (paramMqttConnectOptions.getWillMessage() == null) {
      str4 = "[null]";
    } else {
      str4 = "[notnull]";
    } 
    logger.fine(str1, "connect", "103", new Object[] { Boolean.valueOf(bool), integer1, integer2, str2, str3, str4, paramObject, paramIMqttActionListener });
    this.comms.setNetworkModules(createNetworkModules(this.serverURI, paramMqttConnectOptions));
    MqttToken mqttToken = new MqttToken(getClientId());
    ConnectActionListener connectActionListener = new ConnectActionListener(this, this.persistence, this.comms, paramMqttConnectOptions, mqttToken, paramObject, paramIMqttActionListener);
    mqttToken.setActionCallback((IMqttActionListener)connectActionListener);
    mqttToken.setUserContext(this);
    this.comms.setNetworkModuleIndex(0);
    connectActionListener.connect();
    return mqttToken;
  }
  
  protected NetworkModule[] createNetworkModules(String paramString, MqttConnectOptions paramMqttConnectOptions) throws MqttException, MqttSecurityException {
    String[] arrayOfString1;
    Logger logger = log;
    String str = CLASS_NAME;
    byte b = 0;
    logger.fine(str, "createNetworkModules", "116", new Object[] { paramString });
    String[] arrayOfString2 = paramMqttConnectOptions.getServerURIs();
    if (arrayOfString2 == null) {
      arrayOfString1 = new String[1];
      arrayOfString1[0] = paramString;
    } else {
      arrayOfString1 = arrayOfString2;
      if (arrayOfString2.length == 0) {
        arrayOfString1 = new String[1];
        arrayOfString1[0] = paramString;
      } 
    } 
    NetworkModule[] arrayOfNetworkModule = new NetworkModule[arrayOfString1.length];
    while (true) {
      if (b >= arrayOfString1.length) {
        log.fine(CLASS_NAME, "createNetworkModules", "108");
        return arrayOfNetworkModule;
      } 
      arrayOfNetworkModule[b] = createNetworkModule(arrayOfString1[b], paramMqttConnectOptions);
      b++;
    } 
  }
  
  public IMqttToken disconnect() throws MqttException {
    return disconnect(null, null);
  }
  
  public IMqttToken disconnect(long paramLong) throws MqttException {
    return disconnect(paramLong, null, null);
  }
  
  public IMqttToken disconnect(long paramLong, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    log.fine(CLASS_NAME, "disconnect", "104", new Object[] { new Long(paramLong), paramObject, paramIMqttActionListener });
    MqttToken mqttToken = new MqttToken(getClientId());
    mqttToken.setActionCallback(paramIMqttActionListener);
    mqttToken.setUserContext(paramObject);
    paramObject = new MqttDisconnect();
    try {
      this.comms.disconnect((MqttDisconnect)paramObject, paramLong, mqttToken);
      log.fine(CLASS_NAME, "disconnect", "108");
      return mqttToken;
    } catch (MqttException mqttException) {
      log.fine(CLASS_NAME, "disconnect", "105", null, mqttException);
      throw mqttException;
    } 
  }
  
  public IMqttToken disconnect(Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    return disconnect(30000L, paramObject, paramIMqttActionListener);
  }
  
  public void disconnectForcibly() throws MqttException {
    disconnectForcibly(30000L, 10000L);
  }
  
  public void disconnectForcibly(long paramLong) throws MqttException {
    disconnectForcibly(30000L, paramLong);
  }
  
  public void disconnectForcibly(long paramLong1, long paramLong2) throws MqttException {
    this.comms.disconnectForcibly(paramLong1, paramLong2);
  }
  
  public String getClientId() {
    return this.clientId;
  }
  
  public Debug getDebug() {
    return new Debug(this.clientId, this.comms);
  }
  
  public IMqttDeliveryToken[] getPendingDeliveryTokens() {
    return (IMqttDeliveryToken[])this.comms.getPendingDeliveryTokens();
  }
  
  public String getServerURI() {
    return this.serverURI;
  }
  
  protected MqttTopic getTopic(String paramString) {
    MqttTopic.validate(paramString, false);
    MqttTopic mqttTopic1 = (MqttTopic)this.topics.get(paramString);
    MqttTopic mqttTopic2 = mqttTopic1;
    if (mqttTopic1 == null) {
      mqttTopic2 = new MqttTopic(paramString, this.comms);
      this.topics.put(paramString, mqttTopic2);
    } 
    return mqttTopic2;
  }
  
  public boolean isConnected() {
    return this.comms.isConnected();
  }
  
  public IMqttDeliveryToken publish(String paramString, MqttMessage paramMqttMessage) throws MqttException, MqttPersistenceException {
    return publish(paramString, paramMqttMessage, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttDeliveryToken publish(String paramString, MqttMessage paramMqttMessage, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttPersistenceException {
    log.fine(CLASS_NAME, "publish", "111", new Object[] { paramString, paramObject, paramIMqttActionListener });
    MqttTopic.validate(paramString, false);
    MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(getClientId());
    mqttDeliveryToken.setActionCallback(paramIMqttActionListener);
    mqttDeliveryToken.setUserContext(paramObject);
    mqttDeliveryToken.setMessage(paramMqttMessage);
    mqttDeliveryToken.internalTok.setTopics(new String[] { paramString });
    MqttPublish mqttPublish = new MqttPublish(paramString, paramMqttMessage);
    this.comms.sendNoWait((MqttWireMessage)mqttPublish, mqttDeliveryToken);
    log.fine(CLASS_NAME, "publish", "112");
    return mqttDeliveryToken;
  }
  
  public IMqttDeliveryToken publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) throws MqttException, MqttPersistenceException {
    return publish(paramString, paramArrayOfbyte, paramInt, paramBoolean, null, null);
  }
  
  public IMqttDeliveryToken publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttPersistenceException {
    MqttMessage mqttMessage = new MqttMessage(paramArrayOfbyte);
    mqttMessage.setQos(paramInt);
    mqttMessage.setRetained(paramBoolean);
    return publish(paramString, mqttMessage, paramObject, paramIMqttActionListener);
  }
  
  public void setCallback(MqttCallback paramMqttCallback) {
    this.comms.setCallback(paramMqttCallback);
  }
  
  public IMqttToken subscribe(String paramString, int paramInt) throws MqttException {
    return subscribe(new String[] { paramString }, new int[] { paramInt }, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken subscribe(String paramString, int paramInt, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    return subscribe(new String[] { paramString }, new int[] { paramInt }, paramObject, paramIMqttActionListener);
  }
  
  public IMqttToken subscribe(String[] paramArrayOfString, int[] paramArrayOfint) throws MqttException {
    return subscribe(paramArrayOfString, paramArrayOfint, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken subscribe(String[] paramArrayOfString, int[] paramArrayOfint, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    if (paramArrayOfString.length != paramArrayOfint.length)
      throw new IllegalArgumentException(); 
    String str = "";
    for (byte b = 0;; b++) {
      MqttSubscribe mqttSubscribe;
      MqttToken mqttToken1;
      String str2;
      if (b >= paramArrayOfString.length) {
        log.fine(CLASS_NAME, "subscribe", "106", new Object[] { str, paramObject, paramIMqttActionListener });
        mqttToken1 = new MqttToken(getClientId());
        mqttToken1.setActionCallback(paramIMqttActionListener);
        mqttToken1.setUserContext(paramObject);
        mqttToken1.internalTok.setTopics(paramArrayOfString);
        mqttSubscribe = new MqttSubscribe(paramArrayOfString, paramArrayOfint);
        this.comms.sendNoWait((MqttWireMessage)mqttSubscribe, mqttToken1);
        log.fine(CLASS_NAME, "subscribe", "109");
        return mqttToken1;
      } 
      MqttToken mqttToken2 = mqttToken1;
      if (b > 0) {
        StringBuffer stringBuffer1 = new StringBuffer(String.valueOf(mqttToken1));
        stringBuffer1.append(", ");
        str2 = stringBuffer1.toString();
      } 
      StringBuffer stringBuffer = new StringBuffer(String.valueOf(str2));
      stringBuffer.append("topic=");
      stringBuffer.append((String)mqttSubscribe[b]);
      stringBuffer.append(" qos=");
      stringBuffer.append(paramArrayOfint[b]);
      String str1 = stringBuffer.toString();
      MqttTopic.validate((String)mqttSubscribe[b], true);
    } 
  }
  
  public IMqttToken unsubscribe(String paramString) throws MqttException {
    return unsubscribe(new String[] { paramString }, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken unsubscribe(String paramString, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    return unsubscribe(new String[] { paramString }, paramObject, paramIMqttActionListener);
  }
  
  public IMqttToken unsubscribe(String[] paramArrayOfString) throws MqttException {
    return unsubscribe(paramArrayOfString, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken unsubscribe(String[] paramArrayOfString, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    String str = "";
    for (byte b = 0;; b++) {
      MqttUnsubscribe mqttUnsubscribe;
      MqttToken mqttToken1;
      String str2;
      if (b >= paramArrayOfString.length) {
        log.fine(CLASS_NAME, "unsubscribe", "107", new Object[] { str, paramObject, paramIMqttActionListener });
        mqttToken1 = new MqttToken(getClientId());
        mqttToken1.setActionCallback(paramIMqttActionListener);
        mqttToken1.setUserContext(paramObject);
        mqttToken1.internalTok.setTopics(paramArrayOfString);
        mqttUnsubscribe = new MqttUnsubscribe(paramArrayOfString);
        this.comms.sendNoWait((MqttWireMessage)mqttUnsubscribe, mqttToken1);
        log.fine(CLASS_NAME, "unsubscribe", "110");
        return mqttToken1;
      } 
      MqttToken mqttToken2 = mqttToken1;
      if (b > 0) {
        StringBuffer stringBuffer1 = new StringBuffer(String.valueOf(mqttToken1));
        stringBuffer1.append(", ");
        str2 = stringBuffer1.toString();
      } 
      StringBuffer stringBuffer = new StringBuffer(String.valueOf(str2));
      stringBuffer.append((String)mqttUnsubscribe[b]);
      String str1 = stringBuffer.toString();
      MqttTopic.validate((String)mqttUnsubscribe[b], true);
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.MqttAsyncClient");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttAsyncClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */