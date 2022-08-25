package pl.com.mobilelabs.mobilus.services.communication.cloudservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

class CloudClient extends Thread implements MqttCallback {
  private MqttConnectOptions connectionOptions;
  
  private boolean interrupted;
  
  private ICloudClientListener listener;
  
  private IMqttClient mqttClient;
  
  private String publishTopic;
  
  private SocketFactory socketsFactory;
  
  private ConnectionState state = ConnectionState.DISCONNECTED;
  
  private String[] subscribeTopics;
  
  @SuppressLint({"DefaultLocale"})
  public CloudClient(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String[] paramArrayOfString, String paramString5, ICloudClientListener paramICloudClientListener) {
    String str = String.format("ssl://%s:%d", new Object[] { paramString1, Integer.valueOf(8883) });
    try {
      MqttClient mqttClient = new MqttClient();
      MemoryPersistence memoryPersistence = new MemoryPersistence();
      this();
      this(str, paramString2, (MqttClientPersistence)memoryPersistence);
      this.mqttClient = (IMqttClient)mqttClient;
      this.mqttClient.setCallback(this);
      this.socketsFactory = SSLFactory.getFactory(paramContext);
      this.connectionOptions = getConnectionOptions(paramString3, paramString4, paramContext.getAssets());
    } catch (MqttException mqttException) {}
    this.subscribeTopics = paramArrayOfString;
    this.publishTopic = paramString5;
    this.listener = paramICloudClientListener;
  }
  
  private ConnectionState connectClient(IMqttClient paramIMqttClient) {
    if (this.connectionOptions == null)
      return ConnectionState.ERROR_SSL; 
    try {
      paramIMqttClient.connect(this.connectionOptions);
      try {
        paramIMqttClient.subscribe(this.subscribeTopics);
        return ConnectionState.CONNECTED;
      } catch (MqttException mqttException) {
        return ConnectionState.ERROR_CANNOT_SUBSCRIBE;
      } 
    } catch (MqttException mqttException) {
      return ConnectionState.ERROR_CANNOT_CONNECT;
    } 
  }
  
  private MqttConnectOptions getConnectionOptions(String paramString1, String paramString2, AssetManager paramAssetManager) {
    if (this.socketsFactory == null)
      return null; 
    MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
    mqttConnectOptions.setCleanSession(true);
    mqttConnectOptions.setConnectionTimeout(10);
    mqttConnectOptions.setKeepAliveInterval(30);
    if (paramString1 != null && !paramString1.isEmpty())
      mqttConnectOptions.setUserName(paramString1); 
    if (paramString2 != null && !paramString1.isEmpty())
      mqttConnectOptions.setPassword(paramString2.toCharArray()); 
    mqttConnectOptions.setSocketFactory(this.socketsFactory);
    return mqttConnectOptions;
  }
  
  public void connectionLost(Throwable paramThrowable) {
    this.state = ConnectionState.DISCONNECTED;
    this.listener.connectionStateChanged(false);
  }
  
  public void deliveryComplete(IMqttDeliveryToken paramIMqttDeliveryToken) {}
  
  public void interrupt() {
    this.interrupted = true;
    super.interrupt();
  }
  
  public boolean isConnected() {
    boolean bool;
    if (this.state == ConnectionState.CONNECTED) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void messageArrived(String paramString, MqttMessage paramMqttMessage) throws Exception {
    this.listener.newBytesReceived(paramMqttMessage.getPayload());
  }
  
  public void run() {
    this.interrupted = false;
    this.state = ConnectionState.DISCONNECTED;
    while (true) {
      if (!this.interrupted) {
        if (this.state == ConnectionState.CONNECTED) {
          if (this.mqttClient.isConnected() != true) {
            this.state = ConnectionState.DISCONNECTED;
            this.listener.connectionStateChanged(false);
          } 
        } else {
          this.state = connectClient(this.mqttClient);
          if (this.state == ConnectionState.CONNECTED)
            this.listener.connectionStateChanged(true); 
        } 
        try {
          TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException interruptedException) {}
        continue;
      } 
      this.state = ConnectionState.DISCONNECTED;
      try {
        this.mqttClient.disconnect();
      } catch (MqttException mqttException) {}
      try {
        this.mqttClient.close();
      } catch (MqttException mqttException) {}
    } 
  }
  
  public boolean send(byte[] paramArrayOfbyte) {
    if (this.state != ConnectionState.CONNECTED)
      return false; 
    try {
      this.mqttClient.publish(this.publishTopic, paramArrayOfbyte, CloudQoS.EXACTLY_ONCE.getValue(), false);
      return true;
    } catch (MqttException mqttException) {
      return false;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\cloudservice\CloudClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */