package pl.com.mobilelabs.mobilus.services.communication.cloudservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Pair;
import com.google.protobuf.GeneratedMessageV3;
import java.util.ArrayList;
import java.util.Random;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.communication.MessageStatus;
import pl.com.mobilelabs.mobilus.model.communication.MessageType;
import pl.com.mobilelabs.mobilus.model.communication.MobilusProtocol;
import pl.com.mobilelabs.mobilus.model.communication.ReceivedData;
import pl.com.mobilelabs.mobilus.services.communication.CommunicationBinder;
import pl.com.mobilelabs.mobilus.services.communication.ICommunicationListener;
import pl.com.mobilelabs.mobilus.utils.Conversions;
import pl.com.mobilelabs.mobilus.utils.NetworkUtils;

public class CloudService extends Service implements ICloudClientListener {
  private IBinder binder;
  
  private CloudClient client;
  
  private byte[] clientId;
  
  private ArrayList<Pair<EventType, Object>> eventsList;
  
  private boolean isRemoteConnection;
  
  private GeneratedMessageV3 lastSentMessage;
  
  private MessageType lastSentMessageType;
  
  private ICommunicationListener listener;
  
  private String localIpAddress;
  
  private String moduleSerialNumber;
  
  private byte[] privateKey;
  
  private Thread processEventsThread;
  
  private byte[] publicKey;
  
  private void disconnect() {
    if (this.client != null) {
      this.client.interrupt();
      try {
        this.client.join(1000L);
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      } 
      this.client = null;
      this.processEventsThread.interrupt();
      try {
        this.processEventsThread.join(1000L);
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      } 
      this.processEventsThread = null;
      this.eventsList.clear();
      this.isRemoteConnection = false;
    } 
  }
  
  private byte[] getClientId() {
    byte[] arrayOfByte;
    String str = NetworkUtils.getWifiMacAddress((Context)this);
    if (str == null || str.isEmpty() || str.equalsIgnoreCase("02:00:00:00:00:00")) {
      arrayOfByte = new byte[6];
      (new Random()).nextBytes(arrayOfByte);
      return arrayOfByte;
    } 
    return Conversions.getBytesFromHexString(arrayOfByte.replace(":", ""));
  }
  
  private String getPublishTopic(boolean paramBoolean, String paramString) {
    if (paramBoolean) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString.replace("-", ""));
      stringBuilder.append("/module");
      return stringBuilder.toString();
    } 
    return "module";
  }
  
  private String getRandomString() {
    StringBuilder stringBuilder = new StringBuilder();
    Random random = new Random();
    while (stringBuilder.length() < 17)
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt((int)(random.nextFloat() * "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".length()))); 
    return stringBuilder.toString();
  }
  
  private String[] getSubscribeTopics(boolean paramBoolean, byte[] paramArrayOfbyte, String paramString) {
    String str = Conversions.getHexStringForBytes(paramArrayOfbyte);
    if (paramBoolean) {
      paramString = paramString.replace("-", "");
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("/clients");
      String str1 = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(paramString);
      stringBuilder2.append("/");
      stringBuilder2.append(str);
      return new String[] { str1, stringBuilder2.toString() };
    } 
    return new String[] { "clients", str };
  }
  
  public void connectionStateChanged(boolean paramBoolean) {
    synchronized (this.eventsList) {
      ArrayList<Pair<EventType, Object>> arrayList = this.eventsList;
      Pair<EventType, Object> pair = new Pair();
      this(EventType.CONNECTION_STATUS_CHANGED, Boolean.valueOf(paramBoolean));
      arrayList.add(pair);
      this.eventsList.notifyAll();
      return;
    } 
  }
  
  public void newBytesReceived(byte[] paramArrayOfbyte) {
    synchronized (this.eventsList) {
      ArrayList<Pair<EventType, Object>> arrayList = this.eventsList;
      Pair<EventType, Object> pair = new Pair();
      this(EventType.NEW_BYTES_RECEIVED, paramArrayOfbyte);
      arrayList.add(pair);
      this.eventsList.notifyAll();
      return;
    } 
  }
  
  public IBinder onBind(Intent paramIntent) {
    return this.binder;
  }
  
  public void onCreate() {
    super.onCreate();
    this.eventsList = new ArrayList<>();
    this.binder = (IBinder)new CloudServiceBinder();
  }
  
  public void onDestroy() {
    disconnect();
    this.eventsList = null;
    this.binder = null;
    super.onDestroy();
  }
  
  public boolean onUnbind(Intent paramIntent) {
    if (this.processEventsThread != null)
      this.processEventsThread.interrupt(); 
    if (this.client != null) {
      this.client.interrupt();
      this.client = null;
    } 
    return false;
  }
  
  public class CloudServiceBinder extends CommunicationBinder {
    public void configureService(ICommunicationListener param1ICommunicationListener, String param1String) {
      CloudService.access$002(CloudService.this, param1ICommunicationListener);
      CloudService.access$202(CloudService.this, CloudService.this.getClientId());
      CloudService.access$402(CloudService.this, param1String);
    }
    
    public void configureService(ICommunicationListener param1ICommunicationListener, String param1String1, String param1String2) {
      CloudService.access$002(CloudService.this, param1ICommunicationListener);
      CloudService.access$102(CloudService.this, param1String1);
      CloudService.access$202(CloudService.this, CloudService.this.getClientId());
      CloudService.access$402(CloudService.this, param1String2);
    }
    
    public boolean connect(boolean param1Boolean) {
      if (CloudService.this.listener == null || (CloudService.this.localIpAddress == null && !param1Boolean) || CloudService.this.clientId == null)
        return false; 
      if (param1Boolean == true && CloudService.this.moduleSerialNumber == null)
        return false; 
      if (CloudService.this.client != null)
        return false; 
      CloudService.access$802(CloudService.this, param1Boolean);
      CloudService.access$902(CloudService.this, new Thread(new Runnable() {
              public void run() {
                label46: while (!Thread.currentThread().isInterrupted()) {
                  if (CloudService.this.eventsList == null)
                    return; 
                  ArrayList arrayList = CloudService.this.eventsList;
                  /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/ArrayList}, name=null} */
                  boolean bool = false;
                  while (true) {
                    if (!bool)
                      try {
                        int i = CloudService.this.eventsList.size();
                        if (i <= 0) {
                          try {
                            CloudService.this.eventsList.wait();
                          } catch (InterruptedException interruptedException) {
                            bool = true;
                          } 
                          continue;
                        } 
                      } finally {
                        Exception exception;
                      }  
                    if (CloudService.this.eventsList.size() <= 0) {
                      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/ArrayList}, name=null} */
                      return;
                    } 
                    Pair pair = CloudService.this.eventsList.remove(0);
                    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/ArrayList}, name=null} */
                    if (pair.first == CloudService.EventType.CONNECTION_STATUS_CHANGED) {
                      ConnectionStatus connectionStatus;
                      if (((Boolean)pair.second).booleanValue()) {
                        if (CloudService.this.isRemoteConnection) {
                          connectionStatus = ConnectionStatus.REMOTE_CONNECTION;
                        } else {
                          connectionStatus = ConnectionStatus.LOCAL_CONNECTION;
                        } 
                      } else {
                        connectionStatus = ConnectionStatus.NO_CONNECTION;
                      } 
                      CloudService.this.listener.connectionStateChanged(connectionStatus);
                      continue label46;
                    } 
                    ReceivedData receivedData = Communication.decodeMessage((byte[])pair.second, CloudService.this.privateKey, CloudService.this.publicKey);
                    if (receivedData != null) {
                      if (receivedData.getMessageType() == MessageType.LOGIN_RESPONSE && receivedData.getMessageStatus() == MessageStatus.OK && receivedData.getMessage() != null) {
                        MobilusProtocol.LoginResponse loginResponse = (MobilusProtocol.LoginResponse)receivedData.getMessage();
                        if (loginResponse.getLoginStatus() == 0) {
                          CloudService.access$502(CloudService.this, loginResponse.getPrivateKey().toByteArray());
                          CloudService.access$602(CloudService.this, loginResponse.getPublicKey().toByteArray());
                        } 
                      } 
                      CloudService.this.listener.newMessageReceived(receivedData);
                      continue label46;
                    } 
                    continue label46;
                  } 
                } 
              }
            }));
      CloudService.this.processEventsThread.start();
      String str = Conversions.getHexStringForBytes(CloudService.this.clientId);
      if (param1Boolean) {
        CloudService.access$702(CloudService.this, new CloudClient((Context)CloudService.this, "mobilus.ovh", str, "android", "android", CloudService.this.getSubscribeTopics(param1Boolean, CloudService.this.clientId, CloudService.this.moduleSerialNumber), CloudService.this.getPublishTopic(param1Boolean, CloudService.this.moduleSerialNumber), CloudService.this));
      } else {
        CloudService.access$702(CloudService.this, new CloudClient((Context)CloudService.this, CloudService.this.localIpAddress, str, "android", "android", CloudService.this.getSubscribeTopics(param1Boolean, CloudService.this.clientId, CloudService.this.moduleSerialNumber), CloudService.this.getPublishTopic(param1Boolean, CloudService.this.moduleSerialNumber), CloudService.this));
      } 
      CloudService.this.client.start();
      return true;
    }
    
    public void disconnect() {
      CloudService.this.disconnect();
    }
    
    public ConnectionStatus isConnected() {
      return (CloudService.this.client == null) ? ConnectionStatus.NO_CONNECTION : (CloudService.this.client.isConnected() ? (CloudService.this.isRemoteConnection ? ConnectionStatus.REMOTE_CONNECTION : ConnectionStatus.LOCAL_CONNECTION) : ConnectionStatus.NO_CONNECTION);
    }
    
    public boolean isWorking() {
      boolean bool;
      if (CloudService.this.client != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean resendLastMessage() {
      return (CloudService.this.lastSentMessageType == null || CloudService.this.lastSentMessage == null) ? true : sendMessage(CloudService.this.lastSentMessageType, CloudService.this.lastSentMessage);
    }
    
    public boolean sendMessage(MessageType param1MessageType, GeneratedMessageV3 param1GeneratedMessageV3) {
      byte[] arrayOfByte;
      if (CloudService.this.client == null || !CloudService.this.client.isConnected())
        return false; 
      if (param1MessageType == MessageType.LOGIN_REQUEST) {
        arrayOfByte = Communication.encodeAndAddHeader(param1MessageType, param1GeneratedMessageV3.toByteArray(), null, CloudService.this.clientId);
        CloudService.access$502(CloudService.this, ((MobilusProtocol.LoginRequest)param1GeneratedMessageV3).getPassword().toByteArray());
      } else {
        arrayOfByte = Communication.encodeAndAddHeader(param1MessageType, param1GeneratedMessageV3.toByteArray(), CloudService.this.privateKey, CloudService.this.clientId);
      } 
      if (arrayOfByte != null) {
        boolean bool = CloudService.this.client.send(arrayOfByte);
        if (bool == true && param1MessageType != MessageType.LOGIN_REQUEST) {
          CloudService.access$1402(CloudService.this, param1MessageType);
          CloudService.access$1502(CloudService.this, param1GeneratedMessageV3);
        } 
        return bool;
      } 
      return false;
    }
    
    public void setEncryptionKeys(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
      if (param1ArrayOfbyte1 == null || param1ArrayOfbyte1.length == 32)
        CloudService.access$502(CloudService.this, param1ArrayOfbyte1); 
      if (param1ArrayOfbyte2 == null || param1ArrayOfbyte2.length == 32)
        CloudService.access$602(CloudService.this, param1ArrayOfbyte2); 
    }
  }
  
  class null implements Runnable {
    public void run() {
      label46: while (!Thread.currentThread().isInterrupted()) {
        if (CloudService.this.eventsList == null)
          return; 
        ArrayList arrayList = CloudService.this.eventsList;
        /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/ArrayList}, name=null} */
        boolean bool = false;
        while (true) {
          if (!bool)
            try {
              int i = CloudService.this.eventsList.size();
              if (i <= 0) {
                try {
                  CloudService.this.eventsList.wait();
                } catch (InterruptedException interruptedException) {
                  bool = true;
                } 
                continue;
              } 
            } finally {
              Exception exception;
            }  
          if (CloudService.this.eventsList.size() <= 0) {
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/ArrayList}, name=null} */
            return;
          } 
          Pair pair = CloudService.this.eventsList.remove(0);
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/ArrayList}, name=null} */
          if (pair.first == CloudService.EventType.CONNECTION_STATUS_CHANGED) {
            ConnectionStatus connectionStatus;
            if (((Boolean)pair.second).booleanValue()) {
              if (CloudService.this.isRemoteConnection) {
                connectionStatus = ConnectionStatus.REMOTE_CONNECTION;
              } else {
                connectionStatus = ConnectionStatus.LOCAL_CONNECTION;
              } 
            } else {
              connectionStatus = ConnectionStatus.NO_CONNECTION;
            } 
            CloudService.this.listener.connectionStateChanged(connectionStatus);
            continue label46;
          } 
          ReceivedData receivedData = Communication.decodeMessage((byte[])pair.second, CloudService.this.privateKey, CloudService.this.publicKey);
          if (receivedData != null) {
            if (receivedData.getMessageType() == MessageType.LOGIN_RESPONSE && receivedData.getMessageStatus() == MessageStatus.OK && receivedData.getMessage() != null) {
              MobilusProtocol.LoginResponse loginResponse = (MobilusProtocol.LoginResponse)receivedData.getMessage();
              if (loginResponse.getLoginStatus() == 0) {
                CloudService.access$502(CloudService.this, loginResponse.getPrivateKey().toByteArray());
                CloudService.access$602(CloudService.this, loginResponse.getPublicKey().toByteArray());
              } 
            } 
            CloudService.this.listener.newMessageReceived(receivedData);
            continue label46;
          } 
          continue label46;
        } 
      } 
    }
  }
  
  private enum EventType {
    CONNECTION_STATUS_CHANGED, NEW_BYTES_RECEIVED;
    
    static {
    
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\cloudservice\CloudService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */