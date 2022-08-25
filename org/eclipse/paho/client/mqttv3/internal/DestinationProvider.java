package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttTopic;

public interface DestinationProvider {
  MqttTopic getTopic(String paramString);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\DestinationProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */