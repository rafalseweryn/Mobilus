package pl.com.mobilelabs.mobilus.services.communication.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.protobuf.GeneratedMessageV3;
import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.communication.MessageStatus;
import pl.com.mobilelabs.mobilus.model.communication.MessageType;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.model.communication.MobilusProtocol;
import pl.com.mobilelabs.mobilus.model.communication.ReceivedData;
import pl.com.mobilelabs.mobilus.services.communication.CommunicationBinder;
import pl.com.mobilelabs.mobilus.services.communication.ICommunicationListener;

public class DemoService extends Service {
  private IBinder binder;
  
  private Configuration configuration;
  
  private ConnectionStatus connectionStatus;
  
  private ScheduledExecutorService executor;
  
  private ICommunicationListener listener;
  
  private String localIpAddress;
  
  private String moduleSerialNumber;
  
  private Random random;
  
  private void sendMessageToListener(MessageType paramMessageType, GeneratedMessageV3 paramGeneratedMessageV3, boolean paramBoolean) {
    long l;
    if (paramMessageType == null || paramGeneratedMessageV3 == null)
      return; 
    if (paramBoolean) {
      l = (this.random.nextInt(800) + 200);
    } else {
      l = (this.random.nextInt(200) + 100);
    } 
    MessageStatus messageStatus = MessageStatus.OK;
    ReceivedData receivedData = new ReceivedData(paramMessageType, new byte[] { 17, 34, 51, 68, 85, 102 }, messageStatus, paramGeneratedMessageV3);
    this.executor.schedule(new ExecutorTask(this.listener, receivedData, null), l, TimeUnit.MILLISECONDS);
  }
  
  public IBinder onBind(Intent paramIntent) {
    return this.binder;
  }
  
  public void onCreate() {
    super.onCreate();
    this.random = new Random();
    this.configuration = new Configuration();
    this.configuration.setContext(getApplicationContext());
    this.binder = (IBinder)new DemoServiceBinder();
    this.executor = Executors.newSingleThreadScheduledExecutor();
  }
  
  public void onDestroy() {
    this.executor.shutdownNow();
    this.binder = null;
    super.onDestroy();
  }
  
  public boolean onUnbind(Intent paramIntent) {
    return false;
  }
  
  public class DemoServiceBinder extends CommunicationBinder {
    public void configureService(ICommunicationListener param1ICommunicationListener, String param1String) {
      DemoService.access$002(DemoService.this, param1ICommunicationListener);
      if (param1String != null && !param1String.isEmpty()) {
        DemoService.access$202(DemoService.this, param1String);
      } else {
        DemoService.access$202(DemoService.this, "MB171309");
      } 
    }
    
    public void configureService(ICommunicationListener param1ICommunicationListener, String param1String1, String param1String2) {
      DemoService.access$002(DemoService.this, param1ICommunicationListener);
      if (param1String1 != null && !param1String1.isEmpty()) {
        DemoService.access$102(DemoService.this, param1String1);
      } else {
        DemoService.access$102(DemoService.this, "192.168.1.7");
      } 
      if (param1String2 != null && !param1String2.isEmpty()) {
        DemoService.access$202(DemoService.this, param1String2);
      } else {
        DemoService.access$202(DemoService.this, "MB171309");
      } 
    }
    
    public boolean connect(boolean param1Boolean) {
      DemoService.access$302(DemoService.this, ConnectionStatus.DEMO_MODE);
      DemoService.this.executor.schedule(new DemoService.ExecutorTask(DemoService.this.listener, null, DemoService.this.connectionStatus), 700L, TimeUnit.MILLISECONDS);
      return true;
    }
    
    public void disconnect() {
      DemoService.access$302(DemoService.this, ConnectionStatus.NO_CONNECTION);
      DemoService.this.executor.schedule(new DemoService.ExecutorTask(DemoService.this.listener, null, ConnectionStatus.NO_CONNECTION), 500L, TimeUnit.MILLISECONDS);
    }
    
    public ConnectionStatus isConnected() {
      return DemoService.this.connectionStatus;
    }
    
    public boolean isWorking() {
      boolean bool;
      if (DemoService.this.connectionStatus != ConnectionStatus.NO_CONNECTION) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean resendLastMessage() {
      return true;
    }
    
    public boolean sendMessage(MessageType param1MessageType, GeneratedMessageV3 param1GeneratedMessageV3) {
      MobilusProtocol.CurrentStateResponse currentStateResponse;
      MobilusProtocol.CallEvents.Builder builder1;
      MobilusProtocol.ScenesResponse scenesResponse;
      MobilusProtocol.DeviceGroupsResponse deviceGroupsResponse;
      MobilusProtocol.DevicePlacesResponse devicePlacesResponse;
      MobilusProtocol.DevicesListResponse devicesListResponse;
      MobilusProtocol.CallEvents.Builder builder2;
      MobilusProtocol.CallEvents callEvents;
      switch (param1MessageType) {
        default:
          return true;
        case CURRENT_STATE_REQUEST:
          currentStateResponse = MobilusProtocol.CurrentStateResponse.newBuilder().addAllEvents(DemoService.this.configuration.getCurrentState()).build();
          DemoService.this.sendMessageToListener(MessageType.CURRENT_STATE_RESPONSE, (GeneratedMessageV3)currentStateResponse, false);
        case CALL_EVENTS:
          callEvents = (MobilusProtocol.CallEvents)param1GeneratedMessageV3;
          builder2 = MobilusProtocol.CallEvents.newBuilder();
          builder1 = MobilusProtocol.CallEvents.newBuilder();
          for (MobilusModel.Event event : callEvents.getEventsList()) {
            if (!event.getValue().equalsIgnoreCase("STOP")) {
              String str = DemoService.this.configuration.setState(event.getDeviceId(), event.getValue());
              builder2.addEvents(MobilusModel.Event.newBuilder().setDeviceId(event.getDeviceId()).setEventNumber(7).setValue(str).build());
              builder1.addEvents(MobilusModel.Event.newBuilder().setDeviceId(event.getDeviceId()).setEventNumber(8).setValue(str).build());
            } 
          } 
          DemoService.this.sendMessageToListener(MessageType.CALL_EVENTS, (GeneratedMessageV3)builder2.build(), false);
          DemoService.this.sendMessageToListener(MessageType.CALL_EVENTS, (GeneratedMessageV3)builder1.build(), true);
        case SCENES_REQUEST:
          scenesResponse = MobilusProtocol.ScenesResponse.newBuilder().addAllScenes(DemoService.this.configuration.getScenes()).build();
          DemoService.this.sendMessageToListener(MessageType.SCENES_RESPONSE, (GeneratedMessageV3)scenesResponse, false);
        case DEVICE_GROUPS_REQUEST:
          deviceGroupsResponse = MobilusProtocol.DeviceGroupsResponse.newBuilder().addAllDeviceGroups(DemoService.this.configuration.getGroups()).build();
          DemoService.this.sendMessageToListener(MessageType.DEVICE_GROUPS_RESPONSE, (GeneratedMessageV3)deviceGroupsResponse, false);
        case DEVICE_PLACES_REQUEST:
          devicePlacesResponse = MobilusProtocol.DevicePlacesResponse.newBuilder().addAllDevicePlaces(DemoService.this.configuration.getPlaces()).build();
          DemoService.this.sendMessageToListener(MessageType.DEVICE_PLACES_RESPONSE, (GeneratedMessageV3)devicePlacesResponse, false);
        case DEVICES_LIST_REQUEST:
          devicesListResponse = MobilusProtocol.DevicesListResponse.newBuilder().addAllDevices(DemoService.this.configuration.getDevices()).build();
          DemoService.this.sendMessageToListener(MessageType.DEVICES_LIST_RESPONSE, (GeneratedMessageV3)devicesListResponse, false);
        case LOGIN_REQUEST:
          break;
      } 
      MobilusProtocol.LoginResponse loginResponse = MobilusProtocol.LoginResponse.newBuilder().setLoginStatus(0).setAdmin(false).setSerialNumber(DemoService.this.moduleSerialNumber).setUserId(123456L).build();
      DemoService.this.sendMessageToListener(MessageType.LOGIN_RESPONSE, (GeneratedMessageV3)loginResponse, false);
    }
    
    public void setEncryptionKeys(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {}
  }
  
  private static class ExecutorTask implements Runnable {
    private ConnectionStatus connectionStatus;
    
    private ReceivedData data;
    
    private WeakReference<ICommunicationListener> listener;
    
    public ExecutorTask(ICommunicationListener param1ICommunicationListener, ReceivedData param1ReceivedData, ConnectionStatus param1ConnectionStatus) {
      this.listener = new WeakReference<>(param1ICommunicationListener);
      this.data = param1ReceivedData;
      this.connectionStatus = param1ConnectionStatus;
    }
    
    public void run() {
      ICommunicationListener iCommunicationListener = this.listener.get();
      if (iCommunicationListener != null) {
        if (this.connectionStatus != null)
          iCommunicationListener.connectionStateChanged(this.connectionStatus); 
        if (this.data != null)
          iCommunicationListener.newMessageReceived(this.data); 
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\demoservice\DemoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */