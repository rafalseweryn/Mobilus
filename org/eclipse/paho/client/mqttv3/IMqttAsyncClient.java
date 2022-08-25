package org.eclipse.paho.client.mqttv3;

public interface IMqttAsyncClient {
  void close() throws MqttException;
  
  IMqttToken connect() throws MqttException, MqttSecurityException;
  
  IMqttToken connect(Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttSecurityException;
  
  IMqttToken connect(MqttConnectOptions paramMqttConnectOptions) throws MqttException, MqttSecurityException;
  
  IMqttToken connect(MqttConnectOptions paramMqttConnectOptions, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttSecurityException;
  
  IMqttToken disconnect() throws MqttException;
  
  IMqttToken disconnect(long paramLong) throws MqttException;
  
  IMqttToken disconnect(long paramLong, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException;
  
  IMqttToken disconnect(Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException;
  
  void disconnectForcibly() throws MqttException;
  
  void disconnectForcibly(long paramLong) throws MqttException;
  
  void disconnectForcibly(long paramLong1, long paramLong2) throws MqttException;
  
  String getClientId();
  
  IMqttDeliveryToken[] getPendingDeliveryTokens();
  
  String getServerURI();
  
  boolean isConnected();
  
  IMqttDeliveryToken publish(String paramString, MqttMessage paramMqttMessage) throws MqttException, MqttPersistenceException;
  
  IMqttDeliveryToken publish(String paramString, MqttMessage paramMqttMessage, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttPersistenceException;
  
  IMqttDeliveryToken publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) throws MqttException, MqttPersistenceException;
  
  IMqttDeliveryToken publish(String paramString, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException, MqttPersistenceException;
  
  void setCallback(MqttCallback paramMqttCallback);
  
  IMqttToken subscribe(String paramString, int paramInt) throws MqttException;
  
  IMqttToken subscribe(String paramString, int paramInt, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException;
  
  IMqttToken subscribe(String[] paramArrayOfString, int[] paramArrayOfint) throws MqttException;
  
  IMqttToken subscribe(String[] paramArrayOfString, int[] paramArrayOfint, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException;
  
  IMqttToken unsubscribe(String paramString) throws MqttException;
  
  IMqttToken unsubscribe(String paramString, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException;
  
  IMqttToken unsubscribe(String[] paramArrayOfString) throws MqttException;
  
  IMqttToken unsubscribe(String[] paramArrayOfString, Object paramObject, IMqttActionListener paramIMqttActionListener) throws MqttException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\IMqttAsyncClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */