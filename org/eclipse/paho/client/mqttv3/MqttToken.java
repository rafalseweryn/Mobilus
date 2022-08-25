package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.Token;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public class MqttToken implements IMqttToken {
  public Token internalTok = null;
  
  public MqttToken() {}
  
  public MqttToken(String paramString) {
    this.internalTok = new Token(paramString);
  }
  
  public IMqttActionListener getActionCallback() {
    return this.internalTok.getActionCallback();
  }
  
  public IMqttAsyncClient getClient() {
    return this.internalTok.getClient();
  }
  
  public MqttException getException() {
    return this.internalTok.getException();
  }
  
  public int[] getGrantedQos() {
    return this.internalTok.getGrantedQos();
  }
  
  public int getMessageId() {
    return this.internalTok.getMessageID();
  }
  
  public MqttWireMessage getResponse() {
    return this.internalTok.getResponse();
  }
  
  public boolean getSessionPresent() {
    return this.internalTok.getSessionPresent();
  }
  
  public String[] getTopics() {
    return this.internalTok.getTopics();
  }
  
  public Object getUserContext() {
    return this.internalTok.getUserContext();
  }
  
  public boolean isComplete() {
    return this.internalTok.isComplete();
  }
  
  public void setActionCallback(IMqttActionListener paramIMqttActionListener) {
    this.internalTok.setActionCallback(paramIMqttActionListener);
  }
  
  public void setUserContext(Object paramObject) {
    this.internalTok.setUserContext(paramObject);
  }
  
  public void waitForCompletion() throws MqttException {
    this.internalTok.waitForCompletion(-1L);
  }
  
  public void waitForCompletion(long paramLong) throws MqttException {
    this.internalTok.waitForCompletion(paramLong);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */