package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public interface IMqttToken {
  IMqttActionListener getActionCallback();
  
  IMqttAsyncClient getClient();
  
  MqttException getException();
  
  int[] getGrantedQos();
  
  int getMessageId();
  
  MqttWireMessage getResponse();
  
  boolean getSessionPresent();
  
  String[] getTopics();
  
  Object getUserContext();
  
  boolean isComplete();
  
  void setActionCallback(IMqttActionListener paramIMqttActionListener);
  
  void setUserContext(Object paramObject);
  
  void waitForCompletion() throws MqttException;
  
  void waitForCompletion(long paramLong) throws MqttException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\IMqttToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */