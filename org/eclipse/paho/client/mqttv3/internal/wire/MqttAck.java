package org.eclipse.paho.client.mqttv3.internal.wire;

public abstract class MqttAck extends MqttWireMessage {
  public MqttAck(byte paramByte) {
    super(paramByte);
  }
  
  protected byte getMessageInfo() {
    return 0;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
    stringBuffer.append(" msgId ");
    stringBuffer.append(this.msgId);
    return stringBuffer.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\wire\MqttAck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */