package org.eclipse.paho.client.mqttv3.internal;

import java.util.Properties;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class ClientComms {
  public static String BUILD_LEVEL = "L${build.level}";
  
  private static final String CLASS_NAME;
  
  private static final byte CLOSED = 4;
  
  private static final byte CONNECTED = 0;
  
  private static final byte CONNECTING = 1;
  
  private static final byte DISCONNECTED = 3;
  
  private static final byte DISCONNECTING = 2;
  
  public static String VERSION = "${project.version}";
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private CommsCallback callback;
  
  private IMqttAsyncClient client;
  
  private ClientState clientState;
  
  private boolean closePending = false;
  
  private Object conLock = new Object();
  
  private MqttConnectOptions conOptions;
  
  private byte conState = (byte)3;
  
  private int networkModuleIndex;
  
  private NetworkModule[] networkModules;
  
  private MqttClientPersistence persistence;
  
  private MqttPingSender pingSender;
  
  private CommsReceiver receiver;
  
  private CommsSender sender;
  
  private boolean stoppingComms = false;
  
  private CommsTokenStore tokenStore;
  
  public ClientComms(IMqttAsyncClient paramIMqttAsyncClient, MqttClientPersistence paramMqttClientPersistence, MqttPingSender paramMqttPingSender) throws MqttException {
    this.conState = (byte)3;
    this.client = paramIMqttAsyncClient;
    this.persistence = paramMqttClientPersistence;
    this.pingSender = paramMqttPingSender;
    this.pingSender.init(this);
    this.tokenStore = new CommsTokenStore(getClient().getClientId());
    this.callback = new CommsCallback(this);
    this.clientState = new ClientState(paramMqttClientPersistence, this.tokenStore, this.callback, this, paramMqttPingSender);
    this.callback.setClientState(this.clientState);
    log.setResourceName(getClient().getClientId());
  }
  
  private MqttToken handleOldTokens(MqttToken paramMqttToken, MqttException paramMqttException) {
    // Byte code:
    //   0: getstatic org/eclipse/paho/client/mqttv3/internal/ClientComms.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   3: getstatic org/eclipse/paho/client/mqttv3/internal/ClientComms.CLASS_NAME : Ljava/lang/String;
    //   6: ldc 'handleOldTokens'
    //   8: ldc '222'
    //   10: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   15: aconst_null
    //   16: astore_3
    //   17: aconst_null
    //   18: astore #4
    //   20: aload_1
    //   21: ifnull -> 62
    //   24: aload_3
    //   25: astore #5
    //   27: aload_0
    //   28: getfield tokenStore : Lorg/eclipse/paho/client/mqttv3/internal/CommsTokenStore;
    //   31: aload_1
    //   32: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   35: invokevirtual getKey : ()Ljava/lang/String;
    //   38: invokevirtual getToken : (Ljava/lang/String;)Lorg/eclipse/paho/client/mqttv3/MqttToken;
    //   41: ifnonnull -> 62
    //   44: aload_3
    //   45: astore #5
    //   47: aload_0
    //   48: getfield tokenStore : Lorg/eclipse/paho/client/mqttv3/internal/CommsTokenStore;
    //   51: aload_1
    //   52: aload_1
    //   53: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   56: invokevirtual getKey : ()Ljava/lang/String;
    //   59: invokevirtual saveToken : (Lorg/eclipse/paho/client/mqttv3/MqttToken;Ljava/lang/String;)V
    //   62: aload_3
    //   63: astore #5
    //   65: aload_0
    //   66: getfield clientState : Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
    //   69: aload_2
    //   70: invokevirtual resolveOldTokens : (Lorg/eclipse/paho/client/mqttv3/MqttException;)Ljava/util/Vector;
    //   73: invokevirtual elements : ()Ljava/util/Enumeration;
    //   76: astore_3
    //   77: aload #4
    //   79: astore_1
    //   80: aload_1
    //   81: astore #5
    //   83: aload_3
    //   84: invokeinterface hasMoreElements : ()Z
    //   89: ifne -> 95
    //   92: goto -> 166
    //   95: aload_1
    //   96: astore #5
    //   98: aload_3
    //   99: invokeinterface nextElement : ()Ljava/lang/Object;
    //   104: checkcast org/eclipse/paho/client/mqttv3/MqttToken
    //   107: astore_2
    //   108: aload_1
    //   109: astore #5
    //   111: aload_2
    //   112: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   115: invokevirtual getKey : ()Ljava/lang/String;
    //   118: ldc 'Disc'
    //   120: invokevirtual equals : (Ljava/lang/Object;)Z
    //   123: ifne -> 161
    //   126: aload_1
    //   127: astore #5
    //   129: aload_2
    //   130: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   133: invokevirtual getKey : ()Ljava/lang/String;
    //   136: ldc 'Con'
    //   138: invokevirtual equals : (Ljava/lang/Object;)Z
    //   141: ifeq -> 147
    //   144: goto -> 161
    //   147: aload_1
    //   148: astore #5
    //   150: aload_0
    //   151: getfield callback : Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback;
    //   154: aload_2
    //   155: invokevirtual asyncOperationComplete : (Lorg/eclipse/paho/client/mqttv3/MqttToken;)V
    //   158: goto -> 80
    //   161: aload_2
    //   162: astore_1
    //   163: goto -> 80
    //   166: aload_1
    //   167: areturn
    //   168: astore_1
    //   169: aload #5
    //   171: astore_1
    //   172: goto -> 166
    // Exception table:
    //   from	to	target	type
    //   27	44	168	java/lang/Exception
    //   47	62	168	java/lang/Exception
    //   65	77	168	java/lang/Exception
    //   83	92	168	java/lang/Exception
    //   98	108	168	java/lang/Exception
    //   111	126	168	java/lang/Exception
    //   129	144	168	java/lang/Exception
    //   150	158	168	java/lang/Exception
  }
  
  private void handleRunException(Exception paramException) {
    MqttException mqttException;
    log.fine(CLASS_NAME, "handleRunException", "804", null, paramException);
    if (!(paramException instanceof MqttException)) {
      mqttException = new MqttException(32109, paramException);
    } else {
      mqttException = mqttException;
    } 
    shutdownConnection(null, mqttException);
  }
  
  public MqttToken checkForActivity() {
    try {
      MqttToken mqttToken = this.clientState.checkForActivity();
    } catch (MqttException mqttException) {
      handleRunException((Exception)mqttException);
      mqttException = null;
    } catch (Exception exception) {
      handleRunException(exception);
    } 
    return (MqttToken)exception;
  }
  
  public void close() throws MqttException {
    synchronized (this.conLock) {
      if (!isClosed()) {
        if (!isDisconnected()) {
          log.fine(CLASS_NAME, "close", "224");
          if (isConnecting()) {
            MqttException mqttException = new MqttException();
            this(32110);
            throw mqttException;
          } 
          if (isConnected())
            throw ExceptionHelper.createMqttException(32100); 
          if (isDisconnecting()) {
            this.closePending = true;
            return;
          } 
        } 
        this.conState = (byte)4;
        this.clientState.close();
        this.clientState = null;
        this.callback = null;
        this.persistence = null;
        this.sender = null;
        this.pingSender = null;
        this.receiver = null;
        this.networkModules = null;
        this.conOptions = null;
        this.tokenStore = null;
      } 
      return;
    } 
  }
  
  public void connect(MqttConnectOptions paramMqttConnectOptions, MqttToken paramMqttToken) throws MqttException {
    synchronized (this.conLock) {
      if (isDisconnected() && !this.closePending) {
        log.fine(CLASS_NAME, "connect", "214");
        this.conState = (byte)1;
        this.conOptions = paramMqttConnectOptions;
        MqttConnect mqttConnect = new MqttConnect();
        this(this.client.getClientId(), paramMqttConnectOptions.getMqttVersion(), paramMqttConnectOptions.isCleanSession(), paramMqttConnectOptions.getKeepAliveInterval(), paramMqttConnectOptions.getUserName(), paramMqttConnectOptions.getPassword(), paramMqttConnectOptions.getWillMessage(), paramMqttConnectOptions.getWillDestination());
        this.clientState.setKeepAliveSecs(paramMqttConnectOptions.getKeepAliveInterval());
        this.clientState.setCleanSession(paramMqttConnectOptions.isCleanSession());
        this.tokenStore.open();
        ConnectBG connectBG = new ConnectBG();
        this(this, this, paramMqttToken, mqttConnect);
        connectBG.start();
        return;
      } 
      Logger logger = log;
      String str = CLASS_NAME;
      Byte byte_ = new Byte();
      this(this.conState);
      logger.fine(str, "connect", "207", new Object[] { byte_ });
      if (isClosed() || this.closePending) {
        MqttException mqttException = new MqttException();
        this(32111);
        throw mqttException;
      } 
      if (isConnecting()) {
        MqttException mqttException = new MqttException();
        this(32110);
        throw mqttException;
      } 
      if (isDisconnecting()) {
        MqttException mqttException = new MqttException();
        this(32102);
        throw mqttException;
      } 
      throw ExceptionHelper.createMqttException(32100);
    } 
  }
  
  public void connectComplete(MqttConnack paramMqttConnack, MqttException paramMqttException) throws MqttException {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getReturnCode : ()I
    //   4: istore_3
    //   5: aload_0
    //   6: getfield conLock : Ljava/lang/Object;
    //   9: astore_1
    //   10: aload_1
    //   11: monitorenter
    //   12: iload_3
    //   13: ifne -> 46
    //   16: getstatic org/eclipse/paho/client/mqttv3/internal/ClientComms.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   19: getstatic org/eclipse/paho/client/mqttv3/internal/ClientComms.CLASS_NAME : Ljava/lang/String;
    //   22: ldc_w 'connectComplete'
    //   25: ldc_w '215'
    //   28: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   33: aload_0
    //   34: iconst_0
    //   35: i2b
    //   36: putfield conState : B
    //   39: aload_1
    //   40: monitorexit
    //   41: return
    //   42: astore_2
    //   43: goto -> 82
    //   46: aload_1
    //   47: monitorexit
    //   48: getstatic org/eclipse/paho/client/mqttv3/internal/ClientComms.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   51: getstatic org/eclipse/paho/client/mqttv3/internal/ClientComms.CLASS_NAME : Ljava/lang/String;
    //   54: ldc_w 'connectComplete'
    //   57: ldc_w '204'
    //   60: iconst_1
    //   61: anewarray java/lang/Object
    //   64: dup
    //   65: iconst_0
    //   66: new java/lang/Integer
    //   69: dup
    //   70: iload_3
    //   71: invokespecial <init> : (I)V
    //   74: aastore
    //   75: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   80: aload_2
    //   81: athrow
    //   82: aload_1
    //   83: monitorexit
    //   84: aload_2
    //   85: athrow
    // Exception table:
    //   from	to	target	type
    //   16	41	42	finally
    //   46	48	42	finally
    //   82	84	42	finally
  }
  
  protected void deliveryComplete(MqttPublish paramMqttPublish) throws MqttPersistenceException {
    this.clientState.deliveryComplete(paramMqttPublish);
  }
  
  public void disconnect(MqttDisconnect paramMqttDisconnect, long paramLong, MqttToken paramMqttToken) throws MqttException {
    synchronized (this.conLock) {
      if (isClosed()) {
        log.fine(CLASS_NAME, "disconnect", "223");
        throw ExceptionHelper.createMqttException(32111);
      } 
      if (isDisconnected()) {
        log.fine(CLASS_NAME, "disconnect", "211");
        throw ExceptionHelper.createMqttException(32101);
      } 
      if (isDisconnecting()) {
        log.fine(CLASS_NAME, "disconnect", "219");
        throw ExceptionHelper.createMqttException(32102);
      } 
      if (Thread.currentThread() == this.callback.getThread()) {
        log.fine(CLASS_NAME, "disconnect", "210");
        throw ExceptionHelper.createMqttException(32107);
      } 
      log.fine(CLASS_NAME, "disconnect", "218");
      this.conState = (byte)2;
      DisconnectBG disconnectBG = new DisconnectBG();
      this(this, paramMqttDisconnect, paramLong, paramMqttToken);
      disconnectBG.start();
      return;
    } 
  }
  
  public void disconnectForcibly(long paramLong1, long paramLong2) throws MqttException {
    this.clientState.quiesce(paramLong1);
    MqttToken mqttToken = new MqttToken(this.client.getClientId());
    try {
      MqttDisconnect mqttDisconnect = new MqttDisconnect();
      this();
      internalSend((MqttWireMessage)mqttDisconnect, mqttToken);
      mqttToken.waitForCompletion(paramLong2);
    } catch (Exception exception) {
    
    } finally {
      mqttToken.internalTok.markComplete(null, null);
      shutdownConnection(mqttToken, null);
    } 
  }
  
  public IMqttAsyncClient getClient() {
    return this.client;
  }
  
  public ClientState getClientState() {
    return this.clientState;
  }
  
  public MqttConnectOptions getConOptions() {
    return this.conOptions;
  }
  
  public Properties getDebug() {
    Properties properties = new Properties();
    properties.put("conState", new Integer(this.conState));
    properties.put("serverURI", getClient().getServerURI());
    properties.put("callback", this.callback);
    properties.put("stoppingComms", new Boolean(this.stoppingComms));
    return properties;
  }
  
  public long getKeepAlive() {
    return this.clientState.getKeepAlive();
  }
  
  public int getNetworkModuleIndex() {
    return this.networkModuleIndex;
  }
  
  public NetworkModule[] getNetworkModules() {
    return this.networkModules;
  }
  
  public MqttDeliveryToken[] getPendingDeliveryTokens() {
    return this.tokenStore.getOutstandingDelTokens();
  }
  
  CommsReceiver getReceiver() {
    return this.receiver;
  }
  
  protected MqttTopic getTopic(String paramString) {
    return new MqttTopic(paramString, this);
  }
  
  void internalSend(MqttWireMessage paramMqttWireMessage, MqttToken paramMqttToken) throws MqttException {
    log.fine(CLASS_NAME, "internalSend", "200", new Object[] { paramMqttWireMessage.getKey(), paramMqttWireMessage, paramMqttToken });
    if (paramMqttToken.getClient() == null) {
      paramMqttToken.internalTok.setClient(getClient());
      try {
        this.clientState.send(paramMqttWireMessage, paramMqttToken);
        return;
      } catch (MqttException mqttException) {
        if (paramMqttWireMessage instanceof MqttPublish)
          this.clientState.undo((MqttPublish)paramMqttWireMessage); 
        throw mqttException;
      } 
    } 
    log.fine(CLASS_NAME, "internalSend", "213", new Object[] { paramMqttWireMessage.getKey(), paramMqttWireMessage, mqttException });
    throw new MqttException(32201);
  }
  
  public boolean isClosed() {
    synchronized (this.conLock) {
      boolean bool;
      if (this.conState == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public boolean isConnected() {
    synchronized (this.conLock) {
      boolean bool;
      if (this.conState == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public boolean isConnecting() {
    synchronized (this.conLock) {
      byte b = this.conState;
      boolean bool = true;
      if (b != 1)
        bool = false; 
      return bool;
    } 
  }
  
  public boolean isDisconnected() {
    synchronized (this.conLock) {
      boolean bool;
      if (this.conState == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public boolean isDisconnecting() {
    synchronized (this.conLock) {
      boolean bool;
      if (this.conState == 2) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public void sendNoWait(MqttWireMessage paramMqttWireMessage, MqttToken paramMqttToken) throws MqttException {
    if (isConnected() || (!isConnected() && paramMqttWireMessage instanceof MqttConnect) || (isDisconnecting() && paramMqttWireMessage instanceof MqttDisconnect)) {
      internalSend(paramMqttWireMessage, paramMqttToken);
      return;
    } 
    log.fine(CLASS_NAME, "sendNoWait", "208");
    throw ExceptionHelper.createMqttException(32104);
  }
  
  public void setCallback(MqttCallback paramMqttCallback) {
    this.callback.setCallback(paramMqttCallback);
  }
  
  public void setNetworkModuleIndex(int paramInt) {
    this.networkModuleIndex = paramInt;
  }
  
  public void setNetworkModules(NetworkModule[] paramArrayOfNetworkModule) {
    this.networkModules = paramArrayOfNetworkModule;
  }
  
  public void shutdownConnection(MqttToken paramMqttToken, MqttException paramMqttException) {
    synchronized (this.conLock) {
      boolean bool2;
      if (this.stoppingComms || this.closePending)
        return; 
      boolean bool1 = true;
      this.stoppingComms = true;
      log.fine(CLASS_NAME, "shutdownConnection", "216");
      if (!isConnected() && !isDisconnecting()) {
        bool2 = false;
      } else {
        bool2 = true;
      } 
      this.conState = (byte)2;
      if (paramMqttToken != null && !paramMqttToken.isComplete())
        paramMqttToken.internalTok.setException(paramMqttException); 
      if (this.callback != null)
        this.callback.stop(); 
      try {
        if (this.networkModules != null) {
          null = this.networkModules[this.networkModuleIndex];
          if (null != null)
            null.stop(); 
        } 
      } catch (Exception exception) {}
      if (this.receiver != null)
        this.receiver.stop(); 
      this.tokenStore.quiesce(new MqttException(32102));
      paramMqttToken = handleOldTokens(paramMqttToken, paramMqttException);
      try {
        this.clientState.disconnected(paramMqttException);
      } catch (Exception exception) {}
      if (this.sender != null)
        this.sender.stop(); 
      if (this.pingSender != null)
        this.pingSender.stop(); 
      try {
        if (this.persistence != null)
          this.persistence.close(); 
      } catch (Exception exception) {}
      synchronized (this.conLock) {
        boolean bool;
        log.fine(CLASS_NAME, "shutdownConnection", "217");
        this.conState = (byte)3;
        this.stoppingComms = false;
        if (paramMqttToken != null) {
          bool = true;
        } else {
          bool = false;
        } 
        if (this.callback == null)
          bool1 = false; 
        if ((bool & bool1) != 0)
          this.callback.asyncOperationComplete(paramMqttToken); 
        if (bool2 && this.callback != null)
          this.callback.connectionLost(paramMqttException); 
        synchronized (this.conLock) {
          boolean bool3 = this.closePending;
          if (bool3)
            try {
              close();
            } catch (Exception exception) {} 
          return;
        } 
      } 
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
  
  private class ConnectBG implements Runnable {
    Thread cBg = null;
    
    ClientComms clientComms = null;
    
    MqttConnect conPacket;
    
    MqttToken conToken;
    
    ConnectBG(ClientComms param1ClientComms1, MqttToken param1MqttToken, MqttConnect param1MqttConnect) {
      this.clientComms = param1ClientComms1;
      this.conToken = param1MqttToken;
      this.conPacket = param1MqttConnect;
      StringBuffer stringBuffer = new StringBuffer("MQTT Con: ");
      stringBuffer.append(ClientComms.this.getClient().getClientId());
      this.cBg = new Thread(this, stringBuffer.toString());
    }
    
    public void run() {
      ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "220");
      MqttException mqttException = null;
      try {
        MqttDeliveryToken[] arrayOfMqttDeliveryToken = ClientComms.this.tokenStore.getOutstandingDelTokens();
        for (byte b = 0;; b++) {
          CommsCallback commsCallback;
          if (b >= arrayOfMqttDeliveryToken.length) {
            ClientComms.this.tokenStore.saveToken(this.conToken, (MqttWireMessage)this.conPacket);
            NetworkModule networkModule = ClientComms.this.networkModules[ClientComms.this.networkModuleIndex];
            networkModule.start();
            ClientComms clientComms2 = ClientComms.this;
            CommsReceiver commsReceiver = new CommsReceiver();
            this(ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getInputStream());
            clientComms2.receiver = commsReceiver;
            commsReceiver = ClientComms.this.receiver;
            StringBuffer stringBuffer2 = new StringBuffer();
            this("MQTT Rec: ");
            stringBuffer2.append(ClientComms.this.getClient().getClientId());
            commsReceiver.start(stringBuffer2.toString());
            ClientComms clientComms1 = ClientComms.this;
            CommsSender commsSender2 = new CommsSender();
            this(ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getOutputStream());
            clientComms1.sender = commsSender2;
            CommsSender commsSender1 = ClientComms.this.sender;
            StringBuffer stringBuffer1 = new StringBuffer();
            this("MQTT Snd: ");
            stringBuffer1.append(ClientComms.this.getClient().getClientId());
            commsSender1.start(stringBuffer1.toString());
            commsCallback = ClientComms.this.callback;
            stringBuffer1 = new StringBuffer();
            this("MQTT Call: ");
            stringBuffer1.append(ClientComms.this.getClient().getClientId());
            commsCallback.start(stringBuffer1.toString());
            ClientComms.this.internalSend((MqttWireMessage)this.conPacket, this.conToken);
            break;
          } 
          ((MqttDeliveryToken)commsCallback[b]).internalTok.setException(null);
        } 
      } catch (MqttException mqttException1) {
        ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "212", null, (Throwable)mqttException1);
      } catch (Exception exception) {
        ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "209", null, exception);
        mqttException = ExceptionHelper.createMqttException(exception);
      } 
      if (mqttException != null)
        ClientComms.this.shutdownConnection(this.conToken, mqttException); 
    }
    
    void start() {
      this.cBg.start();
    }
  }
  
  private class DisconnectBG implements Runnable {
    Thread dBg = null;
    
    MqttDisconnect disconnect;
    
    long quiesceTimeout;
    
    MqttToken token;
    
    DisconnectBG(MqttDisconnect param1MqttDisconnect, long param1Long, MqttToken param1MqttToken) {
      this.disconnect = param1MqttDisconnect;
      this.quiesceTimeout = param1Long;
      this.token = param1MqttToken;
    }
    
    public void run() {
      ClientComms.log.fine(ClientComms.CLASS_NAME, "disconnectBG:run", "221");
      ClientComms.this.clientState.quiesce(this.quiesceTimeout);
      try {
        ClientComms.this.internalSend((MqttWireMessage)this.disconnect, this.token);
        this.token.internalTok.waitUntilSent();
      } catch (MqttException mqttException) {
      
      } finally {
        this.token.internalTok.markComplete(null, null);
        ClientComms.this.shutdownConnection(this.token, null);
      } 
    }
    
    void start() {
      StringBuffer stringBuffer = new StringBuffer("MQTT Disc: ");
      stringBuffer.append(ClientComms.this.getClient().getClientId());
      this.dBg = new Thread(this, stringBuffer.toString());
      this.dBg.start();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\ClientComms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */