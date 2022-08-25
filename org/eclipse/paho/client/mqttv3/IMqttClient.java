package org.eclipse.paho.client.mqttv3;

public interface IMqttClient {
  void close() throws MqttException;
  
  void connect() throws MqttSecurityException, MqttException;
  
  void connect(MqttConnectOptions paramMqttConnectOptions) throws MqttSecurityException, MqttException;
  
  IMqttToken connectWithResult(MqttConnectOptions paramMqttConnectOptions) throws MqttSecurityException, MqttException;
  
  void disconnect() throws MqttException;
  
  void disconnect(long paramLong) throws MqttException;
  
  void disconnectForcibly() throws MqttException;
  
  void disconnectForcibly(long paramLong) throws MqttException;
  
  void disconnectForcibly(long paramLong1, long paramLong2) throws MqttException;
  
  String getClientId();
  
  IMqttDeliveryToken[] getPendingDeliveryTokens();
  
  String getServerURI();
  
  MqttTopic getTopic(String paramString);
  
  boolean isConnected();
  
  void publish(String paramString, MqttMessage paramMqttMessage) throws MqttException, MqttPersistenceException;
  
  void publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) throws MqttException, MqttPersistenceException;
  
  void setCallback(MqttCallback paramMqttCallback);
  
  void subscribe(String paramString) throws MqttException, MqttSecurityException;
  
  void subscribe(String paramString, int paramInt) throws MqttException;
  
  void subscribe(String[] paramArrayOfString) throws MqttException;
  
  void subscribe(String[] paramArrayOfString, int[] paramArrayOfint) throws MqttException;
  
  void unsubscribe(String paramString) throws MqttException;
  
  void unsubscribe(String[] paramArrayOfString) throws MqttException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\IMqttClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */