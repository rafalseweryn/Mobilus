package org.eclipse.paho.android.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.SparseArray;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class MqttAndroidClient extends BroadcastReceiver implements IMqttAsyncClient {
  private static final int BIND_SERVICE_FLAG = 0;
  
  private static final String SERVICE_NAME = "org.eclipse.paho.android.service.MqttService";
  
  private static ExecutorService pool = Executors.newCachedThreadPool();
  
  private volatile boolean bindedService = false;
  
  private MqttCallback callback;
  
  private String clientHandle;
  
  private String clientId;
  
  private MqttConnectOptions connectOptions;
  
  private IMqttToken connectToken;
  
  private Ack messageAck;
  
  private MqttService mqttService;
  
  Context myContext;
  
  private MqttClientPersistence persistence = null;
  
  private volatile boolean registerReceiver = false;
  
  private String serverURI;
  
  private MyServiceConnection serviceConnection = new MyServiceConnection();
  
  private SparseArray<IMqttToken> tokenMap = new SparseArray();
  
  private int tokenNumber = 0;
  
  private MqttTraceHandler traceCallback;
  
  private boolean traceEnabled = false;
  
  public MqttAndroidClient(Context paramContext, String paramString1, String paramString2) {
    this(paramContext, paramString1, paramString2, null, Ack.AUTO_ACK);
  }
  
  public MqttAndroidClient(Context paramContext, String paramString1, String paramString2, Ack paramAck) {
    this(paramContext, paramString1, paramString2, null, paramAck);
  }
  
  public MqttAndroidClient(Context paramContext, String paramString1, String paramString2, MqttClientPersistence paramMqttClientPersistence) {
    this(paramContext, paramString1, paramString2, paramMqttClientPersistence, Ack.AUTO_ACK);
  }
  
  public MqttAndroidClient(Context paramContext, String paramString1, String paramString2, MqttClientPersistence paramMqttClientPersistence, Ack paramAck) {
    this.myContext = paramContext;
    this.serverURI = paramString1;
    this.clientId = paramString2;
    this.persistence = paramMqttClientPersistence;
    this.messageAck = paramAck;
  }
  
  private void connectAction(Bundle paramBundle) {
    IMqttToken iMqttToken = this.connectToken;
    removeMqttToken(paramBundle);
    simpleAction(iMqttToken, paramBundle);
  }
  
  private void connectionLostAction(Bundle paramBundle) {
    if (this.callback != null) {
      Exception exception = (Exception)paramBundle.getSerializable("MqttService.exception");
      this.callback.connectionLost(exception);
    } 
  }
  
  private void disconnected(Bundle paramBundle) {
    this.clientHandle = null;
    IMqttToken iMqttToken = removeMqttToken(paramBundle);
    if (iMqttToken != null)
      ((MqttTokenAndroid)iMqttToken).notifyComplete(); 
    if (this.callback != null)
      this.callback.connectionLost(null); 
  }
  
  private void doConnect() {
    if (this.clientHandle == null)
      this.clientHandle = this.mqttService.getClient(this.serverURI, this.clientId, (this.myContext.getApplicationInfo()).packageName, this.persistence); 
    this.mqttService.setTraceEnabled(this.traceEnabled);
    this.mqttService.setTraceCallbackId(this.clientHandle);
    String str = storeToken(this.connectToken);
    try {
      this.mqttService.connect(this.clientHandle, this.connectOptions, null, str);
    } catch (MqttException mqttException) {
      IMqttActionListener iMqttActionListener = this.connectToken.getActionCallback();
      if (iMqttActionListener != null)
        iMqttActionListener.onFailure(this.connectToken, (Throwable)mqttException); 
    } 
  }
  
  private IMqttToken getMqttToken(Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 'MqttService.activityToken'
    //   5: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_1
    //   9: aload_0
    //   10: getfield tokenMap : Landroid/util/SparseArray;
    //   13: aload_1
    //   14: invokestatic parseInt : (Ljava/lang/String;)I
    //   17: invokevirtual get : (I)Ljava/lang/Object;
    //   20: checkcast org/eclipse/paho/client/mqttv3/IMqttToken
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: areturn
    //   28: astore_1
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   2	24	28	finally
  }
  
  private void messageArrivedAction(Bundle paramBundle) {
    if (this.callback != null) {
      String str1 = paramBundle.getString("MqttService.messageId");
      String str2 = paramBundle.getString("MqttService.destinationName");
      ParcelableMqttMessage parcelableMqttMessage = (ParcelableMqttMessage)paramBundle.getParcelable("MqttService.PARCEL");
      try {
        if (this.messageAck == Ack.AUTO_ACK) {
          this.callback.messageArrived(str2, parcelableMqttMessage);
          this.mqttService.acknowledgeMessageArrival(this.clientHandle, str1);
        } else {
          parcelableMqttMessage.messageId = str1;
          this.callback.messageArrived(str2, parcelableMqttMessage);
        } 
      } catch (Exception exception) {}
    } 
  }
  
  private void messageDeliveredAction(Bundle paramBundle) {
    IMqttToken iMqttToken = removeMqttToken(paramBundle);
    if (iMqttToken != null && this.callback != null && (Status)paramBundle.getSerializable("MqttService.callbackStatus") == Status.OK)
      this.callback.deliveryComplete((IMqttDeliveryToken)iMqttToken); 
  }
  
  private void registerReceiver(BroadcastReceiver paramBroadcastReceiver) {
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("MqttService.callbackToActivity.v0");
    LocalBroadcastManager.getInstance(this.myContext).registerReceiver(paramBroadcastReceiver, intentFilter);
    this.registerReceiver = true;
  }
  
  private IMqttToken removeMqttToken(Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 'MqttService.activityToken'
    //   5: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_1
    //   9: aload_1
    //   10: ifnull -> 42
    //   13: aload_1
    //   14: invokestatic parseInt : (Ljava/lang/String;)I
    //   17: istore_2
    //   18: aload_0
    //   19: getfield tokenMap : Landroid/util/SparseArray;
    //   22: iload_2
    //   23: invokevirtual get : (I)Ljava/lang/Object;
    //   26: checkcast org/eclipse/paho/client/mqttv3/IMqttToken
    //   29: astore_1
    //   30: aload_0
    //   31: getfield tokenMap : Landroid/util/SparseArray;
    //   34: iload_2
    //   35: invokevirtual delete : (I)V
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: aload_0
    //   43: monitorexit
    //   44: aconst_null
    //   45: areturn
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	46	finally
    //   13	38	46	finally
  }
  
  private void sendAction(Bundle paramBundle) {
    simpleAction(getMqttToken(paramBundle), paramBundle);
  }
  
  private void simpleAction(IMqttToken paramIMqttToken, Bundle paramBundle) {
    if (paramIMqttToken != null) {
      if ((Status)paramBundle.getSerializable("MqttService.callbackStatus") == Status.OK) {
        ((MqttTokenAndroid)paramIMqttToken).notifyComplete();
      } else {
        Exception exception = (Exception)paramBundle.getSerializable("MqttService.exception");
        ((MqttTokenAndroid)paramIMqttToken).notifyFailure(exception);
      } 
    } else {
      this.mqttService.traceError("MqttService", "simpleAction : token is null");
    } 
  }
  
  private String storeToken(IMqttToken paramIMqttToken) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield tokenMap : Landroid/util/SparseArray;
    //   6: aload_0
    //   7: getfield tokenNumber : I
    //   10: aload_1
    //   11: invokevirtual put : (ILjava/lang/Object;)V
    //   14: aload_0
    //   15: getfield tokenNumber : I
    //   18: istore_2
    //   19: aload_0
    //   20: iload_2
    //   21: iconst_1
    //   22: iadd
    //   23: putfield tokenNumber : I
    //   26: iload_2
    //   27: invokestatic toString : (I)Ljava/lang/String;
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: areturn
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	35	finally
  }
  
  private void subscribeAction(Bundle paramBundle) {
    simpleAction(removeMqttToken(paramBundle), paramBundle);
  }
  
  private void traceAction(Bundle paramBundle) {
    if (this.traceCallback != null) {
      String str1 = paramBundle.getString("MqttService.traceSeverity");
      String str2 = paramBundle.getString("MqttService.errorMessage");
      String str3 = paramBundle.getString("MqttService.traceTag");
      if ("debug".equals(str1)) {
        this.traceCallback.traceDebug(str3, str2);
      } else if ("error".equals(str1)) {
        this.traceCallback.traceError(str3, str2);
      } else {
        Exception exception = (Exception)paramBundle.getSerializable("MqttService.exception");
        this.traceCallback.traceException(str3, str2, exception);
      } 
    } 
  }
  
  private void unSubscribeAction(Bundle paramBundle) {
    simpleAction(removeMqttToken(paramBundle), paramBundle);
  }
  
  public boolean acknowledgeMessage(String paramString) {
    Ack ack1 = this.messageAck;
    Ack ack2 = Ack.MANUAL_ACK;
    boolean bool = false;
    if (ack1 == ack2) {
      if (this.mqttService.acknowledgeMessageArrival(this.clientHandle, paramString) == Status.OK)
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  public void close() {
    if (this.clientHandle == null)
      this.clientHandle = this.mqttService.getClient(this.serverURI, this.clientId, (this.myContext.getApplicationInfo()).packageName, this.persistence); 
    this.mqttService.close(this.clientHandle);
  }
  
  public IMqttToken connect() throws MqttException {
    return connect(null, null);
  }
  
  public IMqttToken connect(Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    return connect(new MqttConnectOptions(), paramObject, paramIMqttActionListener);
  }
  
  public IMqttToken connect(MqttConnectOptions paramMqttConnectOptions) throws MqttException {
    return connect(paramMqttConnectOptions, null, null);
  }
  
  public IMqttToken connect(MqttConnectOptions paramMqttConnectOptions, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    paramObject = new MqttTokenAndroid(this, paramObject, paramIMqttActionListener);
    this.connectOptions = paramMqttConnectOptions;
    this.connectToken = (IMqttToken)paramObject;
    if (this.mqttService == null) {
      Intent intent = new Intent();
      intent.setClassName(this.myContext, "org.eclipse.paho.android.service.MqttService");
      if (this.myContext.startService(intent) == null) {
        paramIMqttActionListener = paramObject.getActionCallback();
        if (paramIMqttActionListener != null)
          paramIMqttActionListener.onFailure((IMqttToken)paramObject, new RuntimeException("cannot start service org.eclipse.paho.android.service.MqttService")); 
      } 
      this.myContext.startService(intent);
      this.myContext.bindService(intent, this.serviceConnection, 1);
      registerReceiver(this);
    } else {
      pool.execute(new Runnable() {
            public void run() {
              MqttAndroidClient.this.doConnect();
              MqttAndroidClient.this.registerReceiver(MqttAndroidClient.this);
            }
          });
    } 
    return (IMqttToken)paramObject;
  }
  
  public IMqttToken disconnect() throws MqttException {
    MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, null, (IMqttActionListener)null);
    String str = storeToken(mqttTokenAndroid);
    this.mqttService.disconnect(this.clientHandle, null, str);
    return mqttTokenAndroid;
  }
  
  public IMqttToken disconnect(long paramLong) throws MqttException {
    MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, null, (IMqttActionListener)null);
    String str = storeToken(mqttTokenAndroid);
    this.mqttService.disconnect(this.clientHandle, paramLong, null, str);
    return mqttTokenAndroid;
  }
  
  public IMqttToken disconnect(long paramLong, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, paramObject, paramIMqttActionListener);
    paramObject = storeToken(mqttTokenAndroid);
    this.mqttService.disconnect(this.clientHandle, paramLong, null, (String)paramObject);
    return mqttTokenAndroid;
  }
  
  public IMqttToken disconnect(Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, paramObject, paramIMqttActionListener);
    paramObject = storeToken(mqttTokenAndroid);
    this.mqttService.disconnect(this.clientHandle, null, (String)paramObject);
    return mqttTokenAndroid;
  }
  
  public void disconnectForcibly() throws MqttException {
    throw new UnsupportedOperationException();
  }
  
  public void disconnectForcibly(long paramLong) throws MqttException {
    throw new UnsupportedOperationException();
  }
  
  public void disconnectForcibly(long paramLong1, long paramLong2) throws MqttException {
    throw new UnsupportedOperationException();
  }
  
  public String getClientId() {
    return this.clientId;
  }
  
  public IMqttDeliveryToken[] getPendingDeliveryTokens() {
    return this.mqttService.getPendingDeliveryTokens(this.clientHandle);
  }
  
  public SSLSocketFactory getSSLSocketFactory(InputStream paramInputStream, String paramString) throws MqttSecurityException {
    try {
      KeyStore keyStore = KeyStore.getInstance("BKS");
      keyStore.load(paramInputStream, paramString.toCharArray());
      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
      trustManagerFactory.init(keyStore);
      TrustManager[] arrayOfTrustManager = trustManagerFactory.getTrustManagers();
      SSLContext sSLContext = SSLContext.getInstance("SSL");
      sSLContext.init(null, arrayOfTrustManager, null);
      return sSLContext.getSocketFactory();
    } catch (KeyStoreException keyStoreException) {
      throw new MqttSecurityException(keyStoreException);
    } catch (CertificateException certificateException) {
      throw new MqttSecurityException(certificateException);
    } catch (FileNotFoundException fileNotFoundException) {
      throw new MqttSecurityException(fileNotFoundException);
    } catch (IOException iOException) {
      throw new MqttSecurityException(iOException);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw new MqttSecurityException(noSuchAlgorithmException);
    } catch (KeyManagementException keyManagementException) {
      throw new MqttSecurityException(keyManagementException);
    } 
  }
  
  public String getServerURI() {
    return this.serverURI;
  }
  
  public boolean isConnected() {
    return (this.mqttService != null) ? this.mqttService.isConnected(this.clientHandle) : false;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    Bundle bundle = paramIntent.getExtras();
    String str = bundle.getString("MqttService.clientHandle");
    if (str == null || !str.equals(this.clientHandle))
      return; 
    str = bundle.getString("MqttService.callbackAction");
    if ("connect".equals(str)) {
      connectAction(bundle);
    } else if ("messageArrived".equals(str)) {
      messageArrivedAction(bundle);
    } else if ("subscribe".equals(str)) {
      subscribeAction(bundle);
    } else if ("unsubscribe".equals(str)) {
      unSubscribeAction(bundle);
    } else if ("send".equals(str)) {
      sendAction(bundle);
    } else if ("messageDelivered".equals(str)) {
      messageDeliveredAction(bundle);
    } else if ("onConnectionLost".equals(str)) {
      connectionLostAction(bundle);
    } else if ("disconnect".equals(str)) {
      disconnected(bundle);
    } else if ("trace".equals(str)) {
      traceAction(bundle);
    } else {
      this.mqttService.traceError("MqttService", "Callback action doesn't exist.");
    } 
  }
  
  public IMqttDeliveryToken publish(String paramString, MqttMessage paramMqttMessage) throws MqttException, MqttPersistenceException {
    return publish(paramString, paramMqttMessage, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttDeliveryToken publish(String paramString, MqttMessage paramMqttMessage, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttPersistenceException {
    MqttDeliveryTokenAndroid mqttDeliveryTokenAndroid = new MqttDeliveryTokenAndroid(this, paramObject, paramIMqttActionListener, paramMqttMessage);
    paramObject = storeToken(mqttDeliveryTokenAndroid);
    mqttDeliveryTokenAndroid.setDelegate((IMqttToken)this.mqttService.publish(this.clientHandle, paramString, paramMqttMessage, null, (String)paramObject));
    return mqttDeliveryTokenAndroid;
  }
  
  public IMqttDeliveryToken publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) throws MqttException, MqttPersistenceException {
    return publish(paramString, paramArrayOfbyte, paramInt, paramBoolean, null, null);
  }
  
  public IMqttDeliveryToken publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttPersistenceException {
    MqttMessage mqttMessage = new MqttMessage(paramArrayOfbyte);
    mqttMessage.setQos(paramInt);
    mqttMessage.setRetained(paramBoolean);
    paramObject = new MqttDeliveryTokenAndroid(this, paramObject, paramIMqttActionListener, mqttMessage);
    String str = storeToken((IMqttToken)paramObject);
    paramObject.setDelegate((IMqttToken)this.mqttService.publish(this.clientHandle, paramString, paramArrayOfbyte, paramInt, paramBoolean, null, str));
    return (IMqttDeliveryToken)paramObject;
  }
  
  public void registerResources(Context paramContext) {
    if (paramContext != null) {
      this.myContext = paramContext;
      if (!this.registerReceiver)
        registerReceiver(this); 
    } 
  }
  
  public void setCallback(MqttCallback paramMqttCallback) {
    this.callback = paramMqttCallback;
  }
  
  public void setTraceCallback(MqttTraceHandler paramMqttTraceHandler) {
    this.traceCallback = paramMqttTraceHandler;
  }
  
  public void setTraceEnabled(boolean paramBoolean) {
    this.traceEnabled = paramBoolean;
    if (this.mqttService != null)
      this.mqttService.setTraceEnabled(paramBoolean); 
  }
  
  public IMqttToken subscribe(String paramString, int paramInt) throws MqttException, MqttSecurityException {
    return subscribe(paramString, paramInt, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken subscribe(String paramString, int paramInt, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    paramObject = new MqttTokenAndroid(this, paramObject, paramIMqttActionListener, new String[] { paramString });
    String str = storeToken((IMqttToken)paramObject);
    this.mqttService.subscribe(this.clientHandle, paramString, paramInt, (String)null, str);
    return (IMqttToken)paramObject;
  }
  
  public IMqttToken subscribe(String[] paramArrayOfString, int[] paramArrayOfint) throws MqttException, MqttSecurityException {
    return subscribe(paramArrayOfString, paramArrayOfint, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken subscribe(String[] paramArrayOfString, int[] paramArrayOfint, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    paramObject = new MqttTokenAndroid(this, paramObject, paramIMqttActionListener, paramArrayOfString);
    String str = storeToken((IMqttToken)paramObject);
    this.mqttService.subscribe(this.clientHandle, paramArrayOfString, paramArrayOfint, (String)null, str);
    return (IMqttToken)paramObject;
  }
  
  public void unregisterResources() {
    // Byte code:
    //   0: aload_0
    //   1: getfield myContext : Landroid/content/Context;
    //   4: ifnull -> 65
    //   7: aload_0
    //   8: getfield registerReceiver : Z
    //   11: ifeq -> 65
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield myContext : Landroid/content/Context;
    //   20: invokestatic getInstance : (Landroid/content/Context;)Landroid/support/v4/content/LocalBroadcastManager;
    //   23: aload_0
    //   24: invokevirtual unregisterReceiver : (Landroid/content/BroadcastReceiver;)V
    //   27: aload_0
    //   28: iconst_0
    //   29: putfield registerReceiver : Z
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_0
    //   35: getfield bindedService : Z
    //   38: ifeq -> 65
    //   41: aload_0
    //   42: getfield myContext : Landroid/content/Context;
    //   45: aload_0
    //   46: getfield serviceConnection : Lorg/eclipse/paho/android/service/MqttAndroidClient$MyServiceConnection;
    //   49: invokevirtual unbindService : (Landroid/content/ServiceConnection;)V
    //   52: aload_0
    //   53: iconst_0
    //   54: putfield bindedService : Z
    //   57: goto -> 65
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    //   65: return
    //   66: astore_1
    //   67: goto -> 65
    // Exception table:
    //   from	to	target	type
    //   16	34	60	finally
    //   41	57	66	java/lang/IllegalArgumentException
    //   61	63	60	finally
  }
  
  public IMqttToken unsubscribe(String paramString) throws MqttException {
    return unsubscribe(paramString, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken unsubscribe(String paramString, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    paramObject = new MqttTokenAndroid(this, paramObject, paramIMqttActionListener);
    String str = storeToken((IMqttToken)paramObject);
    this.mqttService.unsubscribe(this.clientHandle, paramString, (String)null, str);
    return (IMqttToken)paramObject;
  }
  
  public IMqttToken unsubscribe(String[] paramArrayOfString) throws MqttException {
    return unsubscribe(paramArrayOfString, (Object)null, (IMqttActionListener)null);
  }
  
  public IMqttToken unsubscribe(String[] paramArrayOfString, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException {
    paramObject = new MqttTokenAndroid(this, paramObject, paramIMqttActionListener);
    String str = storeToken((IMqttToken)paramObject);
    this.mqttService.unsubscribe(this.clientHandle, paramArrayOfString, (String)null, str);
    return (IMqttToken)paramObject;
  }
  
  public enum Ack {
    AUTO_ACK, MANUAL_ACK;
    
    static {
    
    }
  }
  
  private final class MyServiceConnection implements ServiceConnection {
    private MyServiceConnection() {}
    
    public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
      MqttAndroidClient.access$002(MqttAndroidClient.this, ((MqttServiceBinder)param1IBinder).getService());
      MqttAndroidClient.access$102(MqttAndroidClient.this, true);
      MqttAndroidClient.this.doConnect();
    }
    
    public void onServiceDisconnected(ComponentName param1ComponentName) {
      MqttAndroidClient.access$002(MqttAndroidClient.this, null);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\MqttAndroidClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */