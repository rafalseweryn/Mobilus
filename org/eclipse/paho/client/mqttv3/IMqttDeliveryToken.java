package org.eclipse.paho.client.mqttv3;

public interface IMqttDeliveryToken extends IMqttToken {
  MqttMessage getMessage() throws MqttException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\IMqttDeliveryToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */