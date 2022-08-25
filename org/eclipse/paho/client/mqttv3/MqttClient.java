package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttClient implements IMqttClient {
  protected MqttAsyncClient aClient = null;
  
  protected long timeToWait = -1L;
  
  public MqttClient(String paramString1, String paramString2) throws MqttException {
    this(paramString1, paramString2, (MqttClientPersistence)new MqttDefaultFilePersistence());
  }
  
  public MqttClient(String paramString1, String paramString2, MqttClientPersistence paramMqttClientPersistence) throws MqttException {
    this.aClient = new MqttAsyncClient(paramString1, paramString2, paramMqttClientPersistence);
  }
  
  public static String generateClientId() {
    return MqttAsyncClient.generateClientId();
  }
  
  public void close() throws MqttException {
    this.aClient.close();
  }
  
  public void connect() throws MqttSecurityException, MqttException {
    connect(new MqttConnectOptions());
  }
  
  public void connect(MqttConnectOptions paramMqttConnectOptions) throws MqttSecurityException, MqttException {
    this.aClient.connect(paramMqttConnectOptions, null, null).waitForCompletion(getTimeToWait());
  }
  
  public IMqttToken connectWithResult(MqttConnectOptions paramMqttConnectOptions) throws MqttSecurityException, MqttException {
    IMqttToken iMqttToken = this.aClient.connect(paramMqttConnectOptions, null, null);
    iMqttToken.waitForCompletion(getTimeToWait());
    return iMqttToken;
  }
  
  public void disconnect() throws MqttException {
    this.aClient.disconnect().waitForCompletion();
  }
  
  public void disconnect(long paramLong) throws MqttException {
    this.aClient.disconnect(paramLong, null, null).waitForCompletion();
  }
  
  public void disconnectForcibly() throws MqttException {
    this.aClient.disconnectForcibly();
  }
  
  public void disconnectForcibly(long paramLong) throws MqttException {
    this.aClient.disconnectForcibly(paramLong);
  }
  
  public void disconnectForcibly(long paramLong1, long paramLong2) throws MqttException {
    this.aClient.disconnectForcibly(paramLong1, paramLong2);
  }
  
  public String getClientId() {
    return this.aClient.getClientId();
  }
  
  public Debug getDebug() {
    return this.aClient.getDebug();
  }
  
  public IMqttDeliveryToken[] getPendingDeliveryTokens() {
    return this.aClient.getPendingDeliveryTokens();
  }
  
  public String getServerURI() {
    return this.aClient.getServerURI();
  }
  
  public long getTimeToWait() {
    return this.timeToWait;
  }
  
  public MqttTopic getTopic(String paramString) {
    return this.aClient.getTopic(paramString);
  }
  
  public boolean isConnected() {
    return this.aClient.isConnected();
  }
  
  public void publish(String paramString, MqttMessage paramMqttMessage) throws MqttException, MqttPersistenceException {
    this.aClient.publish(paramString, paramMqttMessage, (Object)null, (IMqttActionListener)null).waitForCompletion(getTimeToWait());
  }
  
  public void publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) throws MqttException, MqttPersistenceException {
    MqttMessage mqttMessage = new MqttMessage(paramArrayOfbyte);
    mqttMessage.setQos(paramInt);
    mqttMessage.setRetained(paramBoolean);
    publish(paramString, mqttMessage);
  }
  
  public void setCallback(MqttCallback paramMqttCallback) {
    this.aClient.setCallback(paramMqttCallback);
  }
  
  public void setTimeToWait(long paramLong) throws IllegalArgumentException {
    if (paramLong < -1L)
      throw new IllegalArgumentException(); 
    this.timeToWait = paramLong;
  }
  
  public void subscribe(String paramString) throws MqttException {
    subscribe(new String[] { paramString }, new int[] { 1 });
  }
  
  public void subscribe(String paramString, int paramInt) throws MqttException {
    subscribe(new String[] { paramString }, new int[] { paramInt });
  }
  
  public void subscribe(String[] paramArrayOfString) throws MqttException {
    int[] arrayOfInt = new int[paramArrayOfString.length];
    for (byte b = 0;; b++) {
      if (b >= arrayOfInt.length) {
        subscribe(paramArrayOfString, arrayOfInt);
        return;
      } 
      arrayOfInt[b] = 1;
    } 
  }
  
  public void subscribe(String[] paramArrayOfString, int[] paramArrayOfint) throws MqttException {
    IMqttToken iMqttToken = this.aClient.subscribe(paramArrayOfString, paramArrayOfint, (Object)null, (IMqttActionListener)null);
    iMqttToken.waitForCompletion(getTimeToWait());
    int[] arrayOfInt = iMqttToken.getGrantedQos();
    for (byte b = 0;; b++) {
      if (b >= arrayOfInt.length) {
        if (arrayOfInt.length == 1 && paramArrayOfint[0] == 128)
          throw new MqttException(128); 
        return;
      } 
      paramArrayOfint[b] = arrayOfInt[b];
    } 
  }
  
  public void unsubscribe(String paramString) throws MqttException {
    unsubscribe(new String[] { paramString });
  }
  
  public void unsubscribe(String[] paramArrayOfString) throws MqttException {
    this.aClient.unsubscribe(paramArrayOfString, (Object)null, (IMqttActionListener)null).waitForCompletion(getTimeToWait());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */