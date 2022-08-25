package org.eclipse.paho.client.mqttv3.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleCatalog extends MessageCatalog {
  private ResourceBundle bundle = ResourceBundle.getBundle("org.eclipse.paho.client.mqttv3.internal.nls.messages");
  
  protected String getLocalizedMessage(int paramInt) {
    try {
      return this.bundle.getString(Integer.toString(paramInt));
    } catch (MissingResourceException missingResourceException) {
      return "MqttException";
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\ResourceBundleCatalog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */