package org.eclipse.paho.client.mqttv3;

public interface MqttCallback {
  void connectionLost(Throwable paramThrowable);
  
  void deliveryComplete(IMqttDeliveryToken paramIMqttDeliveryToken);
  
  void messageArrived(String paramString, MqttMessage paramMqttMessage) throws Exception;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */