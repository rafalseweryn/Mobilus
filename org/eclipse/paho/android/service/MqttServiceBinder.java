package org.eclipse.paho.android.service;

import android.os.Binder;

class MqttServiceBinder extends Binder {
  private String activityToken;
  
  private MqttService mqttService;
  
  MqttServiceBinder(MqttService paramMqttService) {
    this.mqttService = paramMqttService;
  }
  
  public String getActivityToken() {
    return this.activityToken;
  }
  
  public MqttService getService() {
    return this.mqttService;
  }
  
  void setActivityToken(String paramString) {
    this.activityToken = paramString;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\MqttServiceBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */