package org.eclipse.paho.android.service;

import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

class MqttConnection implements MqttCallback {
  private static final String NOT_CONNECTED = "not connected";
  
  private static final String TAG = "MqttConnection";
  
  private boolean cleanSession = true;
  
  private String clientHandle;
  
  private String clientId;
  
  private MqttConnectOptions connectOptions;
  
  private volatile boolean disconnected = true;
  
  private volatile boolean isConnecting = false;
  
  private MqttAsyncClient myClient = null;
  
  private MqttClientPersistence persistence = null;
  
  private String reconnectActivityToken = null;
  
  private Map<IMqttDeliveryToken, String> savedActivityTokens = new HashMap<>();
  
  private Map<IMqttDeliveryToken, String> savedInvocationContexts = new HashMap<>();
  
  private Map<IMqttDeliveryToken, MqttMessage> savedSentMessages = new HashMap<>();
  
  private Map<IMqttDeliveryToken, String> savedTopics = new HashMap<>();
  
  private String serverURI;
  
  private MqttService service = null;
  
  private String wakeLockTag = null;
  
  private PowerManager.WakeLock wakelock = null;
  
  MqttConnection(MqttService paramMqttService, String paramString1, String paramString2, MqttClientPersistence paramMqttClientPersistence, String paramString3) {
    this.serverURI = paramString1.toString();
    this.service = paramMqttService;
    this.clientId = paramString2;
    this.persistence = paramMqttClientPersistence;
    this.clientHandle = paramString3;
    StringBuffer stringBuffer = new StringBuffer(getClass().getCanonicalName());
    stringBuffer.append(" ");
    stringBuffer.append(paramString2);
    stringBuffer.append(" ");
    stringBuffer.append("on host ");
    stringBuffer.append(paramString1);
    this.wakeLockTag = stringBuffer.toString();
  }
  
  private void acquireWakeLock() {
    if (this.wakelock == null)
      this.wakelock = ((PowerManager)this.service.getSystemService("power")).newWakeLock(1, this.wakeLockTag); 
    this.wakelock.acquire();
  }
  
  private void deliverBacklog() {
    Iterator<MessageStore.StoredMessage> iterator = this.service.messageStore.getAllArrivedMessages(this.clientHandle);
    while (iterator.hasNext()) {
      MessageStore.StoredMessage storedMessage = iterator.next();
      Bundle bundle = messageToBundle(storedMessage.getMessageId(), storedMessage.getTopic(), storedMessage.getMessage());
      bundle.putString("MqttService.callbackAction", "messageArrived");
      this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
    } 
  }
  
  private void doAfterConnectFail(Bundle paramBundle) {
    acquireWakeLock();
    this.disconnected = true;
    setConnectingState(false);
    this.service.callbackToActivity(this.clientHandle, Status.ERROR, paramBundle);
    releaseWakeLock();
  }
  
  private void doAfterConnectSuccess(Bundle paramBundle) {
    acquireWakeLock();
    this.service.callbackToActivity(this.clientHandle, Status.OK, paramBundle);
    deliverBacklog();
    setConnectingState(false);
    this.disconnected = false;
    releaseWakeLock();
  }
  
  private void handleException(Bundle paramBundle, Exception paramException) {
    paramBundle.putString("MqttService.errorMessage", paramException.getLocalizedMessage());
    paramBundle.putSerializable("MqttService.exception", paramException);
    this.service.callbackToActivity(this.clientHandle, Status.ERROR, paramBundle);
  }
  
  private Bundle messageToBundle(String paramString1, String paramString2, MqttMessage paramMqttMessage) {
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.messageId", paramString1);
    bundle.putString("MqttService.destinationName", paramString2);
    bundle.putParcelable("MqttService.PARCEL", new ParcelableMqttMessage(paramMqttMessage));
    return bundle;
  }
  
  private void releaseWakeLock() {
    if (this.wakelock != null && this.wakelock.isHeld())
      this.wakelock.release(); 
  }
  
  private void storeSendDetails(String paramString1, MqttMessage paramMqttMessage, IMqttDeliveryToken paramIMqttDeliveryToken, String paramString2, String paramString3) {
    this.savedTopics.put(paramIMqttDeliveryToken, paramString1);
    this.savedSentMessages.put(paramIMqttDeliveryToken, paramMqttMessage);
    this.savedActivityTokens.put(paramIMqttDeliveryToken, paramString3);
    this.savedInvocationContexts.put(paramIMqttDeliveryToken, paramString2);
  }
  
  void close() {
    this.service.traceDebug("MqttConnection", "close()");
    try {
      if (this.myClient != null)
        this.myClient.close(); 
    } catch (MqttException mqttException) {
      handleException(new Bundle(), (Exception)mqttException);
    } 
  }
  
  public void connect(MqttConnectOptions paramMqttConnectOptions, String paramString1, String paramString2) {
    this.connectOptions = paramMqttConnectOptions;
    this.reconnectActivityToken = paramString2;
    if (paramMqttConnectOptions != null)
      this.cleanSession = paramMqttConnectOptions.isCleanSession(); 
    if (this.connectOptions.isCleanSession())
      this.service.messageStore.clearArrivedMessages(this.clientHandle); 
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Connecting {");
    stringBuilder.append(this.serverURI);
    stringBuilder.append("} as {");
    stringBuilder.append(this.clientId);
    stringBuilder.append("}");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.activityToken", paramString2);
    bundle.putString("MqttService.invocationContext", paramString1);
    bundle.putString("MqttService.callbackAction", "connect");
    try {
      MqttService mqttService1;
      if (this.persistence == null) {
        MqttPersistenceException mqttPersistenceException;
        File file2 = this.service.getExternalFilesDir("MqttConnection");
        File file1 = file2;
        if (file2 == null) {
          file2 = this.service.getDir("MqttConnection", 0);
          file1 = file2;
          if (file2 == null) {
            bundle.putString("MqttService.errorMessage", "Error! No external and internal storage available");
            mqttPersistenceException = new MqttPersistenceException();
            this();
            bundle.putSerializable("MqttService.exception", (Serializable)mqttPersistenceException);
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
            return;
          } 
        } 
        MqttDefaultFilePersistence mqttDefaultFilePersistence = new MqttDefaultFilePersistence();
        this(mqttPersistenceException.getAbsolutePath());
        this.persistence = (MqttClientPersistence)mqttDefaultFilePersistence;
      } 
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener() {
          public void onFailure(IMqttToken param1IMqttToken, Throwable param1Throwable) {
            resultBundle.putString("MqttService.errorMessage", param1Throwable.getLocalizedMessage());
            resultBundle.putSerializable("MqttService.exception", param1Throwable);
            MqttService mqttService = MqttConnection.this.service;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("connect fail, call connect to reconnect.reason:");
            stringBuilder.append(param1Throwable.getMessage());
            mqttService.traceError("MqttConnection", stringBuilder.toString());
            MqttConnection.this.doAfterConnectFail(resultBundle);
          }
          
          public void onSuccess(IMqttToken param1IMqttToken) {
            MqttConnection.this.doAfterConnectSuccess(resultBundle);
            MqttConnection.this.service.traceDebug("MqttConnection", "connect success!");
          }
        };
      super(this, bundle, bundle);
      if (this.myClient != null) {
        if (this.isConnecting) {
          this.service.traceDebug("MqttConnection", "myClient != null and the client is connecting. Connect return directly.");
          mqttService1 = this.service;
          stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Connect return:isConnecting:");
          stringBuilder.append(this.isConnecting);
          stringBuilder.append(".disconnected:");
          stringBuilder.append(this.disconnected);
          mqttService1.traceDebug("MqttConnection", stringBuilder.toString());
          return;
        } 
        if (!this.disconnected) {
          this.service.traceDebug("MqttConnection", "myClient != null and the client is connected and notify!");
          doAfterConnectSuccess(bundle);
        } else {
          this.service.traceDebug("MqttConnection", "myClient != null and the client is not connected");
          this.service.traceDebug("MqttConnection", "Do Real connect!");
          setConnectingState(true);
          this.myClient.connect(this.connectOptions, mqttService1, mqttConnectionListener);
        } 
      } else {
        MqttAsyncClient mqttAsyncClient = new MqttAsyncClient();
        String str1 = this.serverURI;
        String str2 = this.clientId;
        MqttClientPersistence mqttClientPersistence = this.persistence;
        AlarmPingSender alarmPingSender = new AlarmPingSender();
        this(this.service);
        this(str1, str2, mqttClientPersistence, alarmPingSender);
        this.myClient = mqttAsyncClient;
        this.myClient.setCallback(this);
        this.service.traceDebug("MqttConnection", "Do Real connect!");
        setConnectingState(true);
        this.myClient.connect(this.connectOptions, mqttService1, mqttConnectionListener);
      } 
    } catch (Exception exception) {
      handleException(bundle, exception);
    } 
  }
  
  public void connectionLost(Throwable paramThrowable) {
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connectionLost(");
    stringBuilder.append(paramThrowable.getMessage());
    stringBuilder.append(")");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    this.disconnected = true;
    try {
      MqttAsyncClient mqttAsyncClient = this.myClient;
      IMqttActionListener iMqttActionListener = new IMqttActionListener() {
          public void onFailure(IMqttToken param1IMqttToken, Throwable param1Throwable) {}
          
          public void onSuccess(IMqttToken param1IMqttToken) {}
        };
      super(this);
      mqttAsyncClient.disconnect(null, iMqttActionListener);
    } catch (Exception exception) {}
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.callbackAction", "onConnectionLost");
    if (paramThrowable != null) {
      bundle.putString("MqttService.errorMessage", paramThrowable.getMessage());
      if (paramThrowable instanceof MqttException)
        bundle.putSerializable("MqttService.exception", paramThrowable); 
      bundle.putString("MqttService.exceptionStack", Log.getStackTraceString(paramThrowable));
    } 
    this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
    releaseWakeLock();
  }
  
  public void deliveryComplete(IMqttDeliveryToken paramIMqttDeliveryToken) {
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("deliveryComplete(");
    stringBuilder.append(paramIMqttDeliveryToken);
    stringBuilder.append(")");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    MqttMessage mqttMessage = this.savedSentMessages.remove(paramIMqttDeliveryToken);
    if (mqttMessage != null) {
      String str3 = this.savedTopics.remove(paramIMqttDeliveryToken);
      String str2 = this.savedActivityTokens.remove(paramIMqttDeliveryToken);
      String str1 = this.savedInvocationContexts.remove(paramIMqttDeliveryToken);
      Bundle bundle = messageToBundle(null, str3, mqttMessage);
      if (str2 != null) {
        bundle.putString("MqttService.callbackAction", "send");
        bundle.putString("MqttService.activityToken", str2);
        bundle.putString("MqttService.invocationContext", str1);
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
      } 
      bundle.putString("MqttService.callbackAction", "messageDelivered");
      this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
    } 
  }
  
  void disconnect(long paramLong, String paramString1, String paramString2) {
    this.service.traceDebug("MqttConnection", "disconnect()");
    this.disconnected = true;
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.activityToken", paramString2);
    bundle.putString("MqttService.invocationContext", paramString1);
    bundle.putString("MqttService.callbackAction", "disconnect");
    if (this.myClient != null && this.myClient.isConnected()) {
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        this.myClient.disconnect(paramLong, paramString1, mqttConnectionListener);
      } catch (Exception exception) {
        handleException(bundle, exception);
      } 
    } else {
      bundle.putString("MqttService.errorMessage", "not connected");
      this.service.traceError("disconnect", "not connected");
      this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    } 
    if (this.connectOptions.isCleanSession())
      this.service.messageStore.clearArrivedMessages(this.clientHandle); 
    releaseWakeLock();
  }
  
  void disconnect(String paramString1, String paramString2) {
    this.service.traceDebug("MqttConnection", "disconnect()");
    this.disconnected = true;
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.activityToken", paramString2);
    bundle.putString("MqttService.invocationContext", paramString1);
    bundle.putString("MqttService.callbackAction", "disconnect");
    if (this.myClient != null && this.myClient.isConnected()) {
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        this.myClient.disconnect(paramString1, mqttConnectionListener);
      } catch (Exception exception) {
        handleException(bundle, exception);
      } 
    } else {
      bundle.putString("MqttService.errorMessage", "not connected");
      this.service.traceError("disconnect", "not connected");
      this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    } 
    if (this.connectOptions.isCleanSession())
      this.service.messageStore.clearArrivedMessages(this.clientHandle); 
    releaseWakeLock();
  }
  
  public String getClientHandle() {
    return this.clientHandle;
  }
  
  public String getClientId() {
    return this.clientId;
  }
  
  public MqttConnectOptions getConnectOptions() {
    return this.connectOptions;
  }
  
  public IMqttDeliveryToken[] getPendingDeliveryTokens() {
    return this.myClient.getPendingDeliveryTokens();
  }
  
  public String getServerURI() {
    return this.serverURI;
  }
  
  public boolean isConnected() {
    return (this.myClient != null) ? this.myClient.isConnected() : false;
  }
  
  public void messageArrived(String paramString, MqttMessage paramMqttMessage) throws Exception {
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("messageArrived(");
    stringBuilder.append(paramString);
    stringBuilder.append(",{");
    stringBuilder.append(paramMqttMessage.toString());
    stringBuilder.append("})");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    String str = this.service.messageStore.storeArrived(this.clientHandle, paramString, paramMqttMessage);
    Bundle bundle = messageToBundle(str, paramString, paramMqttMessage);
    bundle.putString("MqttService.callbackAction", "messageArrived");
    bundle.putString("MqttService.messageId", str);
    this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
  }
  
  void offline() {
    if (!this.disconnected && !this.cleanSession)
      connectionLost(new Exception("Android offline")); 
  }
  
  public IMqttDeliveryToken publish(String paramString1, MqttMessage paramMqttMessage, String paramString2, String paramString3) {
    IMqttDeliveryToken iMqttDeliveryToken;
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.callbackAction", "send");
    bundle.putString("MqttService.activityToken", paramString3);
    bundle.putString("MqttService.invocationContext", paramString2);
    MqttAsyncClient mqttAsyncClient = this.myClient;
    MqttConnectionListener mqttConnectionListener = null;
    String str = null;
    if (mqttAsyncClient != null && this.myClient.isConnected()) {
      mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        iMqttDeliveryToken = this.myClient.publish(paramString1, paramMqttMessage, paramString2, mqttConnectionListener);
        try {
          storeSendDetails(paramString1, paramMqttMessage, iMqttDeliveryToken, paramString2, paramString3);
          IMqttDeliveryToken iMqttDeliveryToken1 = iMqttDeliveryToken;
        } catch (Exception null) {
          IMqttDeliveryToken iMqttDeliveryToken1 = iMqttDeliveryToken;
        } 
      } catch (Exception exception) {
        paramString1 = str;
      } 
      handleException(bundle, exception);
    } 
    bundle.putString("MqttService.errorMessage", "not connected");
    this.service.traceError("send", "not connected");
    this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    return iMqttDeliveryToken;
  }
  
  public IMqttDeliveryToken publish(String paramString1, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean, String paramString2, String paramString3) {
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.callbackAction", "send");
    bundle.putString("MqttService.activityToken", paramString3);
    bundle.putString("MqttService.invocationContext", paramString2);
    MqttAsyncClient mqttAsyncClient = this.myClient;
    MqttMessage mqttMessage = null;
    String str = null;
    if (mqttAsyncClient != null && this.myClient.isConnected()) {
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        mqttMessage = new MqttMessage();
        this(paramArrayOfbyte);
        mqttMessage.setQos(paramInt);
        mqttMessage.setRetained(paramBoolean);
        IMqttDeliveryToken iMqttDeliveryToken = this.myClient.publish(paramString1, paramArrayOfbyte, paramInt, paramBoolean, paramString2, mqttConnectionListener);
        try {
          storeSendDetails(paramString1, mqttMessage, iMqttDeliveryToken, paramString2, paramString3);
          IMqttDeliveryToken iMqttDeliveryToken1 = iMqttDeliveryToken;
        } catch (Exception exception1) {
          IMqttDeliveryToken iMqttDeliveryToken1 = iMqttDeliveryToken;
          exception = exception1;
        } 
      } catch (Exception exception) {
        paramString1 = str;
      } 
      handleException(bundle, exception);
    } 
    bundle.putString("MqttService.errorMessage", "not connected");
    this.service.traceError("send", "not connected");
    this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    return (IMqttDeliveryToken)mqttMessage;
  }
  
  void reconnect() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield isConnecting : Z
    //   6: ifeq -> 24
    //   9: aload_0
    //   10: getfield service : Lorg/eclipse/paho/android/service/MqttService;
    //   13: ldc 'MqttConnection'
    //   15: ldc_w 'The client is connecting. Reconnect return directly.'
    //   18: invokevirtual traceDebug : (Ljava/lang/String;Ljava/lang/String;)V
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: aload_0
    //   25: getfield service : Lorg/eclipse/paho/android/service/MqttService;
    //   28: invokevirtual isOnline : ()Z
    //   31: ifne -> 49
    //   34: aload_0
    //   35: getfield service : Lorg/eclipse/paho/android/service/MqttService;
    //   38: ldc 'MqttConnection'
    //   40: ldc_w 'The network is not reachable. Will not do reconnect'
    //   43: invokevirtual traceDebug : (Ljava/lang/String;Ljava/lang/String;)V
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: aload_0
    //   50: getfield disconnected : Z
    //   53: ifeq -> 198
    //   56: aload_0
    //   57: getfield cleanSession : Z
    //   60: ifne -> 198
    //   63: aload_0
    //   64: getfield service : Lorg/eclipse/paho/android/service/MqttService;
    //   67: ldc 'MqttConnection'
    //   69: ldc_w 'Do Real Reconnect!'
    //   72: invokevirtual traceDebug : (Ljava/lang/String;Ljava/lang/String;)V
    //   75: new android/os/Bundle
    //   78: astore_1
    //   79: aload_1
    //   80: invokespecial <init> : ()V
    //   83: aload_1
    //   84: ldc_w 'MqttService.activityToken'
    //   87: aload_0
    //   88: getfield reconnectActivityToken : Ljava/lang/String;
    //   91: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload_1
    //   95: ldc_w 'MqttService.invocationContext'
    //   98: aconst_null
    //   99: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   102: aload_1
    //   103: ldc 'MqttService.callbackAction'
    //   105: ldc_w 'connect'
    //   108: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   111: new org/eclipse/paho/android/service/MqttConnection$3
    //   114: astore_2
    //   115: aload_2
    //   116: aload_0
    //   117: aload_1
    //   118: aload_1
    //   119: invokespecial <init> : (Lorg/eclipse/paho/android/service/MqttConnection;Landroid/os/Bundle;Landroid/os/Bundle;)V
    //   122: aload_0
    //   123: getfield myClient : Lorg/eclipse/paho/client/mqttv3/MqttAsyncClient;
    //   126: aload_0
    //   127: getfield connectOptions : Lorg/eclipse/paho/client/mqttv3/MqttConnectOptions;
    //   130: aconst_null
    //   131: aload_2
    //   132: invokevirtual connect : (Lorg/eclipse/paho/client/mqttv3/MqttConnectOptions;Ljava/lang/Object;Lorg/eclipse/paho/client/mqttv3/IMqttActionListener;)Lorg/eclipse/paho/client/mqttv3/IMqttToken;
    //   135: pop
    //   136: aload_0
    //   137: iconst_1
    //   138: invokevirtual setConnectingState : (Z)V
    //   141: goto -> 198
    //   144: astore_3
    //   145: aload_0
    //   146: getfield service : Lorg/eclipse/paho/android/service/MqttService;
    //   149: astore #4
    //   151: new java/lang/StringBuilder
    //   154: astore_2
    //   155: aload_2
    //   156: invokespecial <init> : ()V
    //   159: aload_2
    //   160: ldc_w 'Cannot reconnect to remote server.'
    //   163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload_2
    //   168: aload_3
    //   169: invokevirtual getMessage : ()Ljava/lang/String;
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload #4
    //   178: ldc 'MqttConnection'
    //   180: aload_2
    //   181: invokevirtual toString : ()Ljava/lang/String;
    //   184: invokevirtual traceError : (Ljava/lang/String;Ljava/lang/String;)V
    //   187: aload_0
    //   188: iconst_0
    //   189: invokevirtual setConnectingState : (Z)V
    //   192: aload_0
    //   193: aload_1
    //   194: aload_3
    //   195: invokespecial handleException : (Landroid/os/Bundle;Ljava/lang/Exception;)V
    //   198: aload_0
    //   199: monitorexit
    //   200: return
    //   201: astore_1
    //   202: aload_0
    //   203: monitorexit
    //   204: aload_1
    //   205: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	201	finally
    //   24	46	201	finally
    //   49	111	201	finally
    //   111	141	144	org/eclipse/paho/client/mqttv3/MqttException
    //   111	141	201	finally
    //   145	198	201	finally
  }
  
  public void setClientHandle(String paramString) {
    this.clientHandle = paramString;
  }
  
  public void setClientId(String paramString) {
    this.clientId = paramString;
  }
  
  public void setConnectOptions(MqttConnectOptions paramMqttConnectOptions) {
    this.connectOptions = paramMqttConnectOptions;
  }
  
  void setConnectingState(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield isConnecting : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void setServerURI(String paramString) {
    this.serverURI = paramString;
  }
  
  public void subscribe(String paramString1, int paramInt, String paramString2, String paramString3) {
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("subscribe({");
    stringBuilder.append(paramString1);
    stringBuilder.append("},");
    stringBuilder.append(paramInt);
    stringBuilder.append(",{");
    stringBuilder.append(paramString2);
    stringBuilder.append("}, {");
    stringBuilder.append(paramString3);
    stringBuilder.append("}");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.callbackAction", "subscribe");
    bundle.putString("MqttService.activityToken", paramString3);
    bundle.putString("MqttService.invocationContext", paramString2);
    if (this.myClient != null && this.myClient.isConnected()) {
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        this.myClient.subscribe(paramString1, paramInt, paramString2, mqttConnectionListener);
      } catch (Exception exception) {
        handleException(bundle, exception);
      } 
    } else {
      bundle.putString("MqttService.errorMessage", "not connected");
      this.service.traceError("subscribe", "not connected");
      this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    } 
  }
  
  public void subscribe(String[] paramArrayOfString, int[] paramArrayOfint, String paramString1, String paramString2) {
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("subscribe({");
    stringBuilder.append(paramArrayOfString);
    stringBuilder.append("},");
    stringBuilder.append(paramArrayOfint);
    stringBuilder.append(",{");
    stringBuilder.append(paramString1);
    stringBuilder.append("}, {");
    stringBuilder.append(paramString2);
    stringBuilder.append("}");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.callbackAction", "subscribe");
    bundle.putString("MqttService.activityToken", paramString2);
    bundle.putString("MqttService.invocationContext", paramString1);
    if (this.myClient != null && this.myClient.isConnected()) {
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        this.myClient.subscribe(paramArrayOfString, paramArrayOfint, paramString1, mqttConnectionListener);
      } catch (Exception exception) {
        handleException(bundle, exception);
      } 
    } else {
      bundle.putString("MqttService.errorMessage", "not connected");
      this.service.traceError("subscribe", "not connected");
      this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    } 
  }
  
  void unsubscribe(String paramString1, String paramString2, String paramString3) {
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unsubscribe({");
    stringBuilder.append(paramString1);
    stringBuilder.append("},{");
    stringBuilder.append(paramString2);
    stringBuilder.append("}, {");
    stringBuilder.append(paramString3);
    stringBuilder.append("})");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.callbackAction", "unsubscribe");
    bundle.putString("MqttService.activityToken", paramString3);
    bundle.putString("MqttService.invocationContext", paramString2);
    if (this.myClient != null && this.myClient.isConnected()) {
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        this.myClient.unsubscribe(paramString1, paramString2, mqttConnectionListener);
      } catch (Exception exception) {
        handleException(bundle, exception);
      } 
    } else {
      bundle.putString("MqttService.errorMessage", "not connected");
      this.service.traceError("subscribe", "not connected");
      this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    } 
  }
  
  void unsubscribe(String[] paramArrayOfString, String paramString1, String paramString2) {
    MqttService mqttService = this.service;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unsubscribe({");
    stringBuilder.append(paramArrayOfString);
    stringBuilder.append("},{");
    stringBuilder.append(paramString1);
    stringBuilder.append("}, {");
    stringBuilder.append(paramString2);
    stringBuilder.append("})");
    mqttService.traceDebug("MqttConnection", stringBuilder.toString());
    Bundle bundle = new Bundle();
    bundle.putString("MqttService.callbackAction", "unsubscribe");
    bundle.putString("MqttService.activityToken", paramString2);
    bundle.putString("MqttService.invocationContext", paramString1);
    if (this.myClient != null && this.myClient.isConnected()) {
      MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
      try {
        this.myClient.unsubscribe(paramArrayOfString, paramString1, mqttConnectionListener);
      } catch (Exception exception) {
        handleException(bundle, exception);
      } 
    } else {
      bundle.putString("MqttService.errorMessage", "not connected");
      this.service.traceError("subscribe", "not connected");
      this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    } 
  }
  
  private class MqttConnectionListener implements IMqttActionListener {
    private final Bundle resultBundle;
    
    private MqttConnectionListener(Bundle param1Bundle) {
      this.resultBundle = param1Bundle;
    }
    
    public void onFailure(IMqttToken param1IMqttToken, Throwable param1Throwable) {
      this.resultBundle.putString("MqttService.errorMessage", param1Throwable.getLocalizedMessage());
      this.resultBundle.putSerializable("MqttService.exception", param1Throwable);
      MqttConnection.this.service.callbackToActivity(MqttConnection.this.clientHandle, Status.ERROR, this.resultBundle);
    }
    
    public void onSuccess(IMqttToken param1IMqttToken) {
      MqttConnection.this.service.callbackToActivity(MqttConnection.this.clientHandle, Status.OK, this.resultBundle);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\MqttConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */