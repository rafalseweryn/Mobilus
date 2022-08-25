package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsTokenStore {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private MqttException closedResponse = null;
  
  private String logContext;
  
  private Hashtable tokens;
  
  public CommsTokenStore(String paramString) {
    log.setResourceName(paramString);
    this.tokens = new Hashtable<>();
    this.logContext = paramString;
    log.fine(CLASS_NAME, "<Init>", "308");
  }
  
  public void clear() {
    log.fine(CLASS_NAME, "clear", "305", new Object[] { new Integer(this.tokens.size()) });
    synchronized (this.tokens) {
      this.tokens.clear();
      return;
    } 
  }
  
  public int count() {
    synchronized (this.tokens) {
      return this.tokens.size();
    } 
  }
  
  public MqttDeliveryToken[] getOutstandingDelTokens() {
    synchronized (this.tokens) {
      log.fine(CLASS_NAME, "getOutstandingDelTokens", "311");
      Vector vector = new Vector();
      this();
      Enumeration<MqttToken> enumeration = this.tokens.elements();
      while (true) {
        MqttDeliveryToken[] arrayOfMqttDeliveryToken;
        if (!enumeration.hasMoreElements()) {
          arrayOfMqttDeliveryToken = (MqttDeliveryToken[])vector.toArray((Object[])new MqttDeliveryToken[vector.size()]);
          return arrayOfMqttDeliveryToken;
        } 
        MqttToken mqttToken = enumeration.nextElement();
        if (mqttToken != null && mqttToken instanceof MqttDeliveryToken && !mqttToken.internalTok.isNotified())
          arrayOfMqttDeliveryToken.addElement(mqttToken); 
      } 
    } 
  }
  
  public Vector getOutstandingTokens() {
    synchronized (this.tokens) {
      log.fine(CLASS_NAME, "getOutstandingTokens", "312");
      Vector<MqttToken> vector = new Vector();
      this();
      Enumeration<MqttToken> enumeration = this.tokens.elements();
      while (true) {
        if (!enumeration.hasMoreElements())
          return vector; 
        MqttToken mqttToken = enumeration.nextElement();
        if (mqttToken != null)
          vector.addElement(mqttToken); 
      } 
    } 
  }
  
  public MqttToken getToken(String paramString) {
    return (MqttToken)this.tokens.get(paramString);
  }
  
  public MqttToken getToken(MqttWireMessage paramMqttWireMessage) {
    String str = paramMqttWireMessage.getKey();
    return (MqttToken)this.tokens.get(str);
  }
  
  public void open() {
    synchronized (this.tokens) {
      log.fine(CLASS_NAME, "open", "310");
      this.closedResponse = null;
      return;
    } 
  }
  
  protected void quiesce(MqttException paramMqttException) {
    synchronized (this.tokens) {
      log.fine(CLASS_NAME, "quiesce", "309", new Object[] { paramMqttException });
      this.closedResponse = paramMqttException;
      return;
    } 
  }
  
  public MqttToken removeToken(String paramString) {
    log.fine(CLASS_NAME, "removeToken", "306", new Object[] { paramString });
    return (paramString != null) ? (MqttToken)this.tokens.remove(paramString) : null;
  }
  
  public MqttToken removeToken(MqttWireMessage paramMqttWireMessage) {
    return (paramMqttWireMessage != null) ? removeToken(paramMqttWireMessage.getKey()) : null;
  }
  
  protected MqttDeliveryToken restoreToken(MqttPublish paramMqttPublish) {
    synchronized (this.tokens) {
      MqttDeliveryToken mqttDeliveryToken;
      Integer integer = new Integer();
      this(paramMqttPublish.getMessageId());
      String str = integer.toString();
      if (this.tokens.containsKey(str)) {
        MqttDeliveryToken mqttDeliveryToken1 = (MqttDeliveryToken)this.tokens.get(str);
        log.fine(CLASS_NAME, "restoreToken", "302", new Object[] { str, paramMqttPublish, mqttDeliveryToken1 });
        mqttDeliveryToken = mqttDeliveryToken1;
      } else {
        MqttDeliveryToken mqttDeliveryToken1 = new MqttDeliveryToken();
        this(this.logContext);
        mqttDeliveryToken1.internalTok.setKey(str);
        this.tokens.put(str, mqttDeliveryToken1);
        log.fine(CLASS_NAME, "restoreToken", "303", new Object[] { str, mqttDeliveryToken, mqttDeliveryToken1 });
        mqttDeliveryToken = mqttDeliveryToken1;
      } 
      return mqttDeliveryToken;
    } 
  }
  
  protected void saveToken(MqttToken paramMqttToken, String paramString) {
    synchronized (this.tokens) {
      log.fine(CLASS_NAME, "saveToken", "307", new Object[] { paramString, paramMqttToken.toString() });
      paramMqttToken.internalTok.setKey(paramString);
      this.tokens.put(paramString, paramMqttToken);
      return;
    } 
  }
  
  protected void saveToken(MqttToken paramMqttToken, MqttWireMessage paramMqttWireMessage) throws MqttException {
    synchronized (this.tokens) {
      if (this.closedResponse == null) {
        String str = paramMqttWireMessage.getKey();
        log.fine(CLASS_NAME, "saveToken", "300", new Object[] { str, paramMqttWireMessage });
        saveToken(paramMqttToken, str);
        return;
      } 
      throw this.closedResponse;
    } 
  }
  
  public String toString() {
    null = System.getProperty("line.separator", "\n");
    StringBuffer stringBuffer = new StringBuffer();
    synchronized (this.tokens) {
      Enumeration<MqttToken> enumeration = this.tokens.elements();
      while (true) {
        if (!enumeration.hasMoreElements()) {
          null = stringBuffer.toString();
          return null;
        } 
        MqttToken mqttToken = enumeration.nextElement();
        StringBuffer stringBuffer1 = new StringBuffer();
        this("{");
        stringBuffer1.append(mqttToken.internalTok);
        stringBuffer1.append("}");
        stringBuffer1.append(null);
        stringBuffer.append(stringBuffer1.toString());
      } 
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsTokenStore");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\CommsTokenStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */