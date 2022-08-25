package org.eclipse.paho.android.service;

import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.MqttMessage;

interface MessageStore {
  void clearArrivedMessages(String paramString);
  
  void close();
  
  boolean discardArrived(String paramString1, String paramString2);
  
  Iterator<StoredMessage> getAllArrivedMessages(String paramString);
  
  String storeArrived(String paramString1, String paramString2, MqttMessage paramMqttMessage);
  
  public static interface StoredMessage {
    String getClientHandle();
    
    MqttMessage getMessage();
    
    String getMessageId();
    
    String getTopic();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\MessageStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */