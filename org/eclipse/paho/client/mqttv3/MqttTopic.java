package org.eclipse.paho.client.mqttv3;

import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.util.Strings;

public class MqttTopic {
  private static final int MAX_TOPIC_LEN = 65535;
  
  private static final int MIN_TOPIC_LEN = 1;
  
  public static final String MULTI_LEVEL_WILDCARD = "#";
  
  public static final String MULTI_LEVEL_WILDCARD_PATTERN = "/#";
  
  private static final char NUL = '\000';
  
  public static final String SINGLE_LEVEL_WILDCARD = "+";
  
  public static final String TOPIC_LEVEL_SEPARATOR = "/";
  
  public static final String TOPIC_WILDCARDS = "#+";
  
  private ClientComms comms;
  
  private String name;
  
  public MqttTopic(String paramString, ClientComms paramClientComms) {
    this.comms = paramClientComms;
    this.name = paramString;
  }
  
  private MqttPublish createPublish(MqttMessage paramMqttMessage) {
    return new MqttPublish(getName(), paramMqttMessage);
  }
  
  public static void validate(String paramString, boolean paramBoolean) {
    try {
      int i = (paramString.getBytes("UTF-8")).length;
      if (i < 1 || i > 65535)
        throw new IllegalArgumentException(String.format("Invalid topic length, should be in range[%d, %d]!", new Object[] { new Integer(1), new Integer(65535) })); 
      if (paramBoolean) {
        if (Strings.equalsAny(paramString, (CharSequence[])new String[] { "#", "+" }))
          return; 
        if (Strings.countMatches(paramString, "#") > 1 || (paramString.contains("#") && !paramString.endsWith("/#"))) {
          StringBuffer stringBuffer = new StringBuffer("Invalid usage of multi-level wildcard in topic string: ");
          stringBuffer.append(paramString);
          throw new IllegalArgumentException(stringBuffer.toString());
        } 
        validateSingleLevelWildcard(paramString);
        return;
      } 
      if (Strings.containsAny(paramString, "#+"))
        throw new IllegalArgumentException("The topic name MUST NOT contain any wildcard characters (#+)"); 
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new IllegalStateException(unsupportedEncodingException);
    } 
  }
  
  private static void validateSingleLevelWildcard(String paramString) {
    char c1 = "+".charAt(0);
    char c2 = "/".charAt(0);
    char[] arrayOfChar = paramString.toCharArray();
    int i = arrayOfChar.length;
    int j;
    for (j = 0;; j = m) {
      char c;
      if (j >= i)
        return; 
      int k = j - 1;
      if (k >= 0) {
        k = arrayOfChar[k];
      } else {
        k = 0;
      } 
      int m = j + 1;
      if (m < i) {
        c = arrayOfChar[m];
      } else {
        c = Character.MIN_VALUE;
      } 
      if (arrayOfChar[j] == c1 && ((k != c2 && k != 0) || (c != c2 && c != '\000')))
        throw new IllegalArgumentException(String.format("Invalid usage of single-level wildcard in topic string '%s'!", new Object[] { paramString })); 
    } 
  }
  
  public String getName() {
    return this.name;
  }
  
  public MqttDeliveryToken publish(MqttMessage paramMqttMessage) throws MqttException, MqttPersistenceException {
    MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(this.comms.getClient().getClientId());
    mqttDeliveryToken.setMessage(paramMqttMessage);
    this.comms.sendNoWait((MqttWireMessage)createPublish(paramMqttMessage), mqttDeliveryToken);
    mqttDeliveryToken.internalTok.waitUntilSent();
    return mqttDeliveryToken;
  }
  
  public MqttDeliveryToken publish(byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) throws MqttException, MqttPersistenceException {
    MqttMessage mqttMessage = new MqttMessage(paramArrayOfbyte);
    mqttMessage.setQos(paramInt);
    mqttMessage.setRetained(paramBoolean);
    return publish(mqttMessage);
  }
  
  public String toString() {
    return getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */