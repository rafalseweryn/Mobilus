package org.eclipse.paho.client.mqttv3;

public interface IMqttActionListener {
  void onFailure(IMqttToken paramIMqttToken, Throwable paramThrowable);
  
  void onSuccess(IMqttToken paramIMqttToken);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\IMqttActionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */