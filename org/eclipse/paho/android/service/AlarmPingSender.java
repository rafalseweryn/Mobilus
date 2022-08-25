package org.eclipse.paho.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

class AlarmPingSender implements MqttPingSender {
  static final String TAG = "AlarmPingSender";
  
  private BroadcastReceiver alarmReceiver;
  
  private ClientComms comms;
  
  private volatile boolean hasStarted = false;
  
  private PendingIntent pendingIntent;
  
  private MqttService service;
  
  private AlarmPingSender that;
  
  public AlarmPingSender(MqttService paramMqttService) {
    if (paramMqttService == null)
      throw new IllegalArgumentException("Neither service nor client can be null."); 
    this.service = paramMqttService;
    this.that = this;
  }
  
  public void init(ClientComms paramClientComms) {
    this.comms = paramClientComms;
    this.alarmReceiver = new AlarmReceiver();
  }
  
  public void schedule(long paramLong) {
    paramLong = System.currentTimeMillis() + paramLong;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Schedule next alarm at ");
    stringBuilder.append(paramLong);
    Log.d("AlarmPingSender", stringBuilder.toString());
    ((AlarmManager)this.service.getSystemService("alarm")).set(0, paramLong, this.pendingIntent);
  }
  
  public void start() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("MqttService.pingSender.");
    stringBuilder1.append(this.comms.getClient().getClientId());
    String str = stringBuilder1.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Register alarmreceiver to MqttService");
    stringBuilder2.append(str);
    Log.d("AlarmPingSender", stringBuilder2.toString());
    this.service.registerReceiver(this.alarmReceiver, new IntentFilter(str));
    this.pendingIntent = PendingIntent.getBroadcast((Context)this.service, 0, new Intent(str), 134217728);
    schedule(this.comms.getKeepAlive());
    this.hasStarted = true;
  }
  
  public void stop() {
    ((AlarmManager)this.service.getSystemService("alarm")).cancel(this.pendingIntent);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unregister alarmreceiver to MqttService");
    stringBuilder.append(this.comms.getClient().getClientId());
    Log.d("AlarmPingSender", stringBuilder.toString());
    if (this.hasStarted) {
      this.hasStarted = false;
      try {
        this.service.unregisterReceiver(this.alarmReceiver);
      } catch (IllegalArgumentException illegalArgumentException) {}
    } 
  }
  
  class AlarmReceiver extends BroadcastReceiver {
    private String wakeLockTag;
    
    private PowerManager.WakeLock wakelock;
    
    AlarmReceiver() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("MqttService.client.");
      stringBuilder.append(AlarmPingSender.this.that.comms.getClient().getClientId());
      this.wakeLockTag = stringBuilder.toString();
    }
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      int i = param1Intent.getIntExtra("android.intent.extra.ALARM_COUNT", -1);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Ping ");
      stringBuilder.append(i);
      stringBuilder.append(" times.");
      Log.d("AlarmPingSender", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("Check time :");
      stringBuilder.append(System.currentTimeMillis());
      Log.d("AlarmPingSender", stringBuilder.toString());
      MqttToken mqttToken = AlarmPingSender.this.comms.checkForActivity();
      if (mqttToken == null)
        return; 
      if (this.wakelock == null)
        this.wakelock = ((PowerManager)AlarmPingSender.this.service.getSystemService("power")).newWakeLock(1, this.wakeLockTag); 
      this.wakelock.acquire();
      mqttToken.setActionCallback(new IMqttActionListener() {
            public void onFailure(IMqttToken param2IMqttToken, Throwable param2Throwable) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Failure. Release lock(");
              stringBuilder.append(AlarmPingSender.AlarmReceiver.this.wakeLockTag);
              stringBuilder.append("):");
              stringBuilder.append(System.currentTimeMillis());
              Log.d("AlarmPingSender", stringBuilder.toString());
              if (AlarmPingSender.AlarmReceiver.this.wakelock != null && AlarmPingSender.AlarmReceiver.this.wakelock.isHeld())
                AlarmPingSender.AlarmReceiver.this.wakelock.release(); 
            }
            
            public void onSuccess(IMqttToken param2IMqttToken) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Success. Release lock(");
              stringBuilder.append(AlarmPingSender.AlarmReceiver.this.wakeLockTag);
              stringBuilder.append("):");
              stringBuilder.append(System.currentTimeMillis());
              Log.d("AlarmPingSender", stringBuilder.toString());
              if (AlarmPingSender.AlarmReceiver.this.wakelock != null && AlarmPingSender.AlarmReceiver.this.wakelock.isHeld())
                AlarmPingSender.AlarmReceiver.this.wakelock.release(); 
            }
          });
    }
  }
  
  class null implements IMqttActionListener {
    public void onFailure(IMqttToken param1IMqttToken, Throwable param1Throwable) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failure. Release lock(");
      stringBuilder.append(this.this$1.wakeLockTag);
      stringBuilder.append("):");
      stringBuilder.append(System.currentTimeMillis());
      Log.d("AlarmPingSender", stringBuilder.toString());
      if (this.this$1.wakelock != null && this.this$1.wakelock.isHeld())
        this.this$1.wakelock.release(); 
    }
    
    public void onSuccess(IMqttToken param1IMqttToken) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Success. Release lock(");
      stringBuilder.append(this.this$1.wakeLockTag);
      stringBuilder.append("):");
      stringBuilder.append(System.currentTimeMillis());
      Log.d("AlarmPingSender", stringBuilder.toString());
      if (this.this$1.wakelock != null && this.this$1.wakelock.isHeld())
        this.this$1.wakelock.release(); 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\AlarmPingSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */