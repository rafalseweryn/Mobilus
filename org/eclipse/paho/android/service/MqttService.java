package org.eclipse.paho.android.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class MqttService extends Service implements MqttTraceHandler {
  static final String TAG = "MqttService";
  
  private volatile boolean backgroundDataEnabled = true;
  
  private BackgroundDataPreferenceReceiver backgroundDataPreferenceMonitor;
  
  private Map<String, MqttConnection> connections = new ConcurrentHashMap<>();
  
  MessageStore messageStore;
  
  private MqttServiceBinder mqttServiceBinder;
  
  private NetworkConnectionIntentReceiver networkConnectionMonitor;
  
  private String traceCallbackId;
  
  private boolean traceEnabled = false;
  
  private MqttConnection getConnection(String paramString) {
    MqttConnection mqttConnection = this.connections.get(paramString);
    if (mqttConnection == null)
      throw new IllegalArgumentException("Invalid ClientHandle"); 
    return mqttConnection;
  }
  
  private void registerBroadcastReceivers() {
    if (this.networkConnectionMonitor == null) {
      this.networkConnectionMonitor = new NetworkConnectionIntentReceiver();
      registerReceiver(this.networkConnectionMonitor, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    } 
    if (Build.VERSION.SDK_INT < 14) {
      this.backgroundDataEnabled = ((ConnectivityManager)getSystemService("connectivity")).getBackgroundDataSetting();
      if (this.backgroundDataPreferenceMonitor == null) {
        this.backgroundDataPreferenceMonitor = new BackgroundDataPreferenceReceiver();
        registerReceiver(this.backgroundDataPreferenceMonitor, new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED"));
      } 
    } 
  }
  
  private void traceCallback(String paramString1, String paramString2, String paramString3) {
    if (this.traceCallbackId != null && this.traceEnabled) {
      Bundle bundle = new Bundle();
      bundle.putString("MqttService.callbackAction", "trace");
      bundle.putString("MqttService.traceSeverity", paramString1);
      bundle.putString("MqttService.traceTag", paramString2);
      bundle.putString("MqttService.errorMessage", paramString3);
      callbackToActivity(this.traceCallbackId, Status.ERROR, bundle);
    } 
  }
  
  private void unregisterBroadcastReceivers() {
    if (this.networkConnectionMonitor != null) {
      unregisterReceiver(this.networkConnectionMonitor);
      this.networkConnectionMonitor = null;
    } 
    if (Build.VERSION.SDK_INT < 14 && this.backgroundDataPreferenceMonitor != null)
      unregisterReceiver(this.backgroundDataPreferenceMonitor); 
  }
  
  public Status acknowledgeMessageArrival(String paramString1, String paramString2) {
    return this.messageStore.discardArrived(paramString1, paramString2) ? Status.OK : Status.ERROR;
  }
  
  void callbackToActivity(String paramString, Status paramStatus, Bundle paramBundle) {
    Intent intent = new Intent("MqttService.callbackToActivity.v0");
    if (paramString != null)
      intent.putExtra("MqttService.clientHandle", paramString); 
    intent.putExtra("MqttService.callbackStatus", paramStatus);
    if (paramBundle != null)
      intent.putExtras(paramBundle); 
    LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
  }
  
  public void close(String paramString) {
    getConnection(paramString).close();
  }
  
  public void connect(String paramString1, MqttConnectOptions paramMqttConnectOptions, String paramString2, String paramString3) throws MqttSecurityException, MqttException {
    getConnection(paramString1).connect(paramMqttConnectOptions, paramString2, paramString3);
  }
  
  public void disconnect(String paramString1, long paramLong, String paramString2, String paramString3) {
    getConnection(paramString1).disconnect(paramLong, paramString2, paramString3);
    this.connections.remove(paramString1);
    stopSelf();
  }
  
  public void disconnect(String paramString1, String paramString2, String paramString3) {
    getConnection(paramString1).disconnect(paramString2, paramString3);
    this.connections.remove(paramString1);
    stopSelf();
  }
  
  public String getClient(String paramString1, String paramString2, String paramString3, MqttClientPersistence paramMqttClientPersistence) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    stringBuilder.append(":");
    stringBuilder.append(paramString2);
    stringBuilder.append(":");
    stringBuilder.append(paramString3);
    paramString3 = stringBuilder.toString();
    if (!this.connections.containsKey(paramString3)) {
      MqttConnection mqttConnection = new MqttConnection(this, paramString1, paramString2, paramMqttClientPersistence, paramString3);
      this.connections.put(paramString3, mqttConnection);
    } 
    return paramString3;
  }
  
  public IMqttDeliveryToken[] getPendingDeliveryTokens(String paramString) {
    return getConnection(paramString).getPendingDeliveryTokens();
  }
  
  public boolean isConnected(String paramString) {
    return getConnection(paramString).isConnected();
  }
  
  public boolean isOnline() {
    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService("connectivity");
    return (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected() && this.backgroundDataEnabled);
  }
  
  public boolean isTraceEnabled() {
    return this.traceEnabled;
  }
  
  public void notifyClientsOffline() {
    Iterator<MqttConnection> iterator = this.connections.values().iterator();
    while (iterator.hasNext())
      ((MqttConnection)iterator.next()).offline(); 
  }
  
  public IBinder onBind(Intent paramIntent) {
    String str = paramIntent.getStringExtra("MqttService.activityToken");
    this.mqttServiceBinder.setActivityToken(str);
    return (IBinder)this.mqttServiceBinder;
  }
  
  public void onCreate() {
    super.onCreate();
    this.mqttServiceBinder = new MqttServiceBinder(this);
    this.messageStore = new DatabaseMessageStore(this, (Context)this);
  }
  
  public void onDestroy() {
    Iterator<MqttConnection> iterator = this.connections.values().iterator();
    while (iterator.hasNext())
      ((MqttConnection)iterator.next()).disconnect(null, null); 
    if (this.mqttServiceBinder != null)
      this.mqttServiceBinder = null; 
    unregisterBroadcastReceivers();
    if (this.messageStore != null)
      this.messageStore.close(); 
    super.onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    registerBroadcastReceivers();
    return 1;
  }
  
  public IMqttDeliveryToken publish(String paramString1, String paramString2, MqttMessage paramMqttMessage, String paramString3, String paramString4) throws MqttPersistenceException, MqttException {
    return getConnection(paramString1).publish(paramString2, paramMqttMessage, paramString3, paramString4);
  }
  
  public IMqttDeliveryToken publish(String paramString1, String paramString2, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean, String paramString3, String paramString4) throws MqttPersistenceException, MqttException {
    return getConnection(paramString1).publish(paramString2, paramArrayOfbyte, paramInt, paramBoolean, paramString3, paramString4);
  }
  
  void reconnect() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Reconnect to server, client size=");
    stringBuilder.append(this.connections.size());
    traceDebug("MqttService", stringBuilder.toString());
    for (MqttConnection mqttConnection : this.connections.values()) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(mqttConnection.getClientId());
      stringBuilder1.append('/');
      stringBuilder1.append(mqttConnection.getServerURI());
      traceDebug("Reconnect Client:", stringBuilder1.toString());
      if (isOnline())
        mqttConnection.reconnect(); 
    } 
  }
  
  public void setTraceCallbackId(String paramString) {
    this.traceCallbackId = paramString;
  }
  
  public void setTraceEnabled(boolean paramBoolean) {
    this.traceEnabled = paramBoolean;
  }
  
  public void subscribe(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4) {
    getConnection(paramString1).subscribe(paramString2, paramInt, paramString3, paramString4);
  }
  
  public void subscribe(String paramString1, String[] paramArrayOfString, int[] paramArrayOfint, String paramString2, String paramString3) {
    getConnection(paramString1).subscribe(paramArrayOfString, paramArrayOfint, paramString2, paramString3);
  }
  
  public void traceDebug(String paramString1, String paramString2) {
    traceCallback("debug", paramString1, paramString2);
  }
  
  public void traceError(String paramString1, String paramString2) {
    traceCallback("error", paramString1, paramString2);
  }
  
  public void traceException(String paramString1, String paramString2, Exception paramException) {
    if (this.traceCallbackId != null) {
      Bundle bundle = new Bundle();
      bundle.putString("MqttService.callbackAction", "trace");
      bundle.putString("MqttService.traceSeverity", "exception");
      bundle.putString("MqttService.errorMessage", paramString2);
      bundle.putSerializable("MqttService.exception", paramException);
      bundle.putString("MqttService.traceTag", paramString1);
      callbackToActivity(this.traceCallbackId, Status.ERROR, bundle);
    } 
  }
  
  public void unsubscribe(String paramString1, String paramString2, String paramString3, String paramString4) {
    getConnection(paramString1).unsubscribe(paramString2, paramString3, paramString4);
  }
  
  public void unsubscribe(String paramString1, String[] paramArrayOfString, String paramString2, String paramString3) {
    getConnection(paramString1).unsubscribe(paramArrayOfString, paramString2, paramString3);
  }
  
  private class BackgroundDataPreferenceReceiver extends BroadcastReceiver {
    private BackgroundDataPreferenceReceiver() {}
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      ConnectivityManager connectivityManager = (ConnectivityManager)MqttService.this.getSystemService("connectivity");
      MqttService.this.traceDebug("MqttService", "Reconnect since BroadcastReceiver.");
      if (connectivityManager.getBackgroundDataSetting()) {
        if (!MqttService.this.backgroundDataEnabled) {
          MqttService.access$202(MqttService.this, true);
          MqttService.this.reconnect();
        } 
      } else {
        MqttService.access$202(MqttService.this, false);
        MqttService.this.notifyClientsOffline();
      } 
    }
  }
  
  private class NetworkConnectionIntentReceiver extends BroadcastReceiver {
    private NetworkConnectionIntentReceiver() {}
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      MqttService.this.traceDebug("MqttService", "Internal network status receive.");
      PowerManager.WakeLock wakeLock = ((PowerManager)MqttService.this.getSystemService("power")).newWakeLock(1, "MQTT");
      wakeLock.acquire();
      MqttService.this.traceDebug("MqttService", "Reconnect for Network recovery.");
      if (MqttService.this.isOnline()) {
        MqttService.this.traceDebug("MqttService", "Online,reconnect.");
        MqttService.this.reconnect();
      } else {
        MqttService.this.notifyClientsOffline();
      } 
      wakeLock.release();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\MqttService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */