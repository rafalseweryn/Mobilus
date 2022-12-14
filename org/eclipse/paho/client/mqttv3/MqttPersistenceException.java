package org.eclipse.paho.client.mqttv3;

public class MqttPersistenceException extends MqttException {
  public static final short REASON_CODE_PERSISTENCE_IN_USE = 32200;
  
  private static final long serialVersionUID = 300L;
  
  public MqttPersistenceException() {
    super(0);
  }
  
  public MqttPersistenceException(int paramInt) {
    super(paramInt);
  }
  
  public MqttPersistenceException(int paramInt, Throwable paramThrowable) {
    super(paramInt, paramThrowable);
  }
  
  public MqttPersistenceException(Throwable paramThrowable) {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\MqttPersistenceException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */