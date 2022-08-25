package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttToken;

public class ConnectActionListener implements IMqttActionListener {
  private MqttAsyncClient client;
  
  private ClientComms comms;
  
  private MqttConnectOptions options;
  
  private int originalMqttVersion;
  
  private MqttClientPersistence persistence;
  
  private IMqttActionListener userCallback;
  
  private Object userContext;
  
  private MqttToken userToken;
  
  public ConnectActionListener(MqttAsyncClient paramMqttAsyncClient, MqttClientPersistence paramMqttClientPersistence, ClientComms paramClientComms, MqttConnectOptions paramMqttConnectOptions, MqttToken paramMqttToken, Object paramObject, IMqttActionListener paramIMqttActionListener) {
    this.persistence = paramMqttClientPersistence;
    this.client = paramMqttAsyncClient;
    this.comms = paramClientComms;
    this.options = paramMqttConnectOptions;
    this.userToken = paramMqttToken;
    this.userContext = paramObject;
    this.userCallback = paramIMqttActionListener;
    this.originalMqttVersion = paramMqttConnectOptions.getMqttVersion();
  }
  
  public void connect() throws MqttPersistenceException {
    MqttToken mqttToken = new MqttToken(this.client.getClientId());
    mqttToken.setActionCallback(this);
    mqttToken.setUserContext(this);
    this.persistence.open(this.client.getClientId(), this.client.getServerURI());
    if (this.options.isCleanSession())
      this.persistence.clear(); 
    if (this.options.getMqttVersion() == 0)
      this.options.setMqttVersion(4); 
    try {
      this.comms.connect(this.options, mqttToken);
    } catch (MqttException mqttException) {
      onFailure((IMqttToken)mqttToken, (Throwable)mqttException);
    } 
  }
  
  public void onFailure(IMqttToken paramIMqttToken, Throwable paramThrowable) {
    MqttException mqttException;
    int i = (this.comms.getNetworkModules()).length;
    int j = this.comms.getNetworkModuleIndex() + 1;
    if (j < i || (this.originalMqttVersion == 0 && this.options.getMqttVersion() == 4)) {
      if (this.originalMqttVersion == 0) {
        if (this.options.getMqttVersion() == 4) {
          this.options.setMqttVersion(3);
        } else {
          this.options.setMqttVersion(4);
          this.comms.setNetworkModuleIndex(j);
        } 
      } else {
        this.comms.setNetworkModuleIndex(j);
      } 
      try {
        connect();
      } catch (MqttPersistenceException mqttPersistenceException) {
        onFailure(paramIMqttToken, (Throwable)mqttPersistenceException);
      } 
      return;
    } 
    if (this.originalMqttVersion == 0)
      this.options.setMqttVersion(0); 
    if (mqttPersistenceException instanceof MqttException) {
      mqttException = (MqttException)mqttPersistenceException;
    } else {
      mqttException = new MqttException((Throwable)mqttPersistenceException);
    } 
    this.userToken.internalTok.markComplete(null, mqttException);
    this.userToken.internalTok.notifyComplete();
    if (this.userCallback != null) {
      this.userToken.setUserContext(this.userContext);
      this.userCallback.onFailure((IMqttToken)this.userToken, (Throwable)mqttPersistenceException);
    } 
  }
  
  public void onSuccess(IMqttToken paramIMqttToken) {
    if (this.originalMqttVersion == 0)
      this.options.setMqttVersion(0); 
    this.userToken.internalTok.markComplete(paramIMqttToken.getResponse(), null);
    this.userToken.internalTok.notifyComplete();
    if (this.userCallback != null) {
      this.userToken.setUserContext(this.userContext);
      this.userCallback.onSuccess((IMqttToken)this.userToken);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\ConnectActionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */