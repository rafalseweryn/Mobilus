package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttReceivedMessage extends MqttMessage {
  private int messageId;
  
  public int getMessageId() {
    return this.messageId;
  }
  
  public void setDuplicate(boolean paramBoolean) {
    super.setDuplicate(paramBoolean);
  }
  
  public void setMessageId(int paramInt) {
    this.messageId = paramInt;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttReceivedMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */