package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class MqttDeliveryTokenAndroid extends MqttTokenAndroid implements IMqttDeliveryToken {
  private MqttMessage message;
  
  MqttDeliveryTokenAndroid(MqttAndroidClient paramMqttAndroidClient, Object paramObject, IMqttActionListener paramIMqttActionListener, MqttMessage paramMqttMessage) {
    super(paramMqttAndroidClient, paramObject, paramIMqttActionListener);
    this.message = paramMqttMessage;
  }
  
  public MqttMessage getMessage() throws MqttException {
    return this.message;
  }
  
  void notifyDelivery(MqttMessage paramMqttMessage) {
    this.message = paramMqttMessage;
    notifyComplete();
  }
  
  void setMessage(MqttMessage paramMqttMessage) {
    this.message = paramMqttMessage;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\MqttDeliveryTokenAndroid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */