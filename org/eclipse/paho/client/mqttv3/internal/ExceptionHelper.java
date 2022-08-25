package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class ExceptionHelper {
  public static MqttException createMqttException(int paramInt) {
    return (MqttException)((paramInt == 4 || paramInt == 5) ? new MqttSecurityException(paramInt) : new MqttException(paramInt));
  }
  
  public static MqttException createMqttException(Throwable paramThrowable) {
    return (MqttException)(paramThrowable.getClass().getName().equals("java.security.GeneralSecurityException") ? new MqttSecurityException(paramThrowable) : new MqttException(paramThrowable));
  }
  
  public static boolean isClassAvailable(String paramString) {
    boolean bool;
    try {
      Class.forName(paramString);
      bool = true;
    } catch (ClassNotFoundException classNotFoundException) {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\ExceptionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */