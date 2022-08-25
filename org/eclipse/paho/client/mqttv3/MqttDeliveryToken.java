package org.eclipse.paho.client.mqttv3;

public class MqttDeliveryToken extends MqttToken implements IMqttDeliveryToken {
  public MqttDeliveryToken() {}
  
  public MqttDeliveryToken(String paramString) {
    super(paramString);
  }
  
  public MqttMessage getMessage() throws MqttException {
    return this.internalTok.getMessage();
  }
  
  protected void setMessage(MqttMessage paramMqttMessage) {
    this.internalTok.setMessage(paramMqttMessage);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttDeliveryToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */