package org.eclipse.paho.client.mqttv3;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class TimerPingSender implements MqttPingSender {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private ClientComms comms;
  
  private Timer timer;
  
  public void init(ClientComms paramClientComms) {
    if (paramClientComms == null)
      throw new IllegalArgumentException("ClientComms cannot be null."); 
    this.comms = paramClientComms;
  }
  
  public void schedule(long paramLong) {
    this.timer.schedule(new PingTask(null), paramLong);
  }
  
  public void start() {
    String str = this.comms.getClient().getClientId();
    log.fine(CLASS_NAME, "start", "659", new Object[] { str });
    StringBuffer stringBuffer = new StringBuffer("MQTT Ping: ");
    stringBuffer.append(str);
    this.timer = new Timer(stringBuffer.toString());
    this.timer.schedule(new PingTask(null), this.comms.getKeepAlive());
  }
  
  public void stop() {
    log.fine(CLASS_NAME, "stop", "661", null);
    if (this.timer != null)
      this.timer.cancel(); 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.TimerPingSender");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
  
  private class PingTask extends TimerTask {
    private static final String methodName = "PingTask.run";
    
    private PingTask() {}
    
    public void run() {
      TimerPingSender.log.fine(TimerPingSender.CLASS_NAME, "PingTask.run", "660", new Object[] { new Long(System.currentTimeMillis()) });
      TimerPingSender.this.comms.checkForActivity();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\TimerPingSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */