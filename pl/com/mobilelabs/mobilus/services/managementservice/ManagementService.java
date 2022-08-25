package pl.com.mobilelabs.mobilus.services.managementservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.RemoteAccessState;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.WifiState;
import pl.com.mobilelabs.mobilus.model.communication.EventType;
import pl.com.mobilelabs.mobilus.model.communication.MessageStatus;
import pl.com.mobilelabs.mobilus.model.communication.MessageType;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.model.communication.MobilusProtocol;
import pl.com.mobilelabs.mobilus.model.communication.Platform;
import pl.com.mobilelabs.mobilus.model.communication.ReceivedData;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.services.communication.CommunicationBinder;
import pl.com.mobilelabs.mobilus.services.communication.ICommunicationListener;
import pl.com.mobilelabs.mobilus.services.communication.cloudservice.CloudService;
import pl.com.mobilelabs.mobilus.services.communication.demoservice.DemoService;
import pl.com.mobilelabs.mobilus.services.settingsservice.SettingsService;
import pl.com.mobilelabs.mobilus.utils.Encryption;

public class ManagementService extends Service implements ICommunicationListener, ExtendTimeout.IListener {
  private static final String DEMO_SERIAL = "MB0DE170";
  
  private static final int MAX_TIMEOUT_COUNT = 3;
  
  private static final int OPERATION_TIMEOUT_S = 5;
  
  private static String TAG = "ManagementService";
  
  private IBinder binder = (IBinder)new ManagementServiceBinder();
  
  private CommunicationBinder communicationBinder;
  
  private ConnectionStatus desiredConnectionStatus;
  
  private ArrayList<Device> devices;
  
  private HashMap<Long, State> devicesStates;
  
  private ArrayList<Group> groups;
  
  private HashMap<Long, State> groupsStates;
  
  private String lastFirmwareUpdateProcessState;
  
  private MobilusModel.User lastModifiedUser;
  
  private Location location;
  
  private Handler mainThreadHandler;
  
  private ArrayList<Place> places;
  
  private ArrayList<Scene> scenes;
  
  private ServiceConnection serviceConnection = new ServiceConnection() {
      public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
        ManagementService.access$002(ManagementService.this, (CommunicationBinder)param1IBinder);
        if (ManagementService.this.settingsService.hasDataToConnect(ManagementService.this.desiredConnectionStatus)) {
          CommunicationBinder communicationBinder = ManagementService.this.communicationBinder;
          ManagementService managementService = ManagementService.this;
          if (ManagementService.this.settingsService.getCentralIpAddress() != null) {
            String str = ManagementService.this.settingsService.getCentralIpAddress().getHostAddress();
          } else {
            param1ComponentName = null;
          } 
          communicationBinder.configureService(managementService, (String)param1ComponentName, ManagementService.this.settingsService.getCentralSerialNumber());
          if (ManagementService.this.communicationBinder.connect(ManagementService.this.settingsService.shouldUseRemoteConnection()))
            ManagementService.this.setServiceState(ManagementServiceState.CONNECTING); 
        } 
      }
      
      public void onServiceDisconnected(ComponentName param1ComponentName) {
        ManagementService.access$002(ManagementService.this, (CommunicationBinder)null);
        Intent intent = new Intent("ManagementService.Intents.connectionStatusChanged");
        intent.putExtra("ManagementService.Intents.connectionStatusChangedConnectionState", (Serializable)ConnectionStatus.NO_CONNECTION);
        ManagementService.this.mainThreadHandler.post(new ManagementService.IntentRunnable(intent));
      }
    };
  
  private ManagementServiceState serviceState;
  
  private SettingsService settingsService;
  
  private ExtendTimeout timeout;
  
  private int timeoutCounter;
  
  private long userId;
  
  private boolean areDevicesListIdentical(String paramString) {
    String str = returnIdsOrder();
    boolean bool = paramString.equalsIgnoreCase(str);
    if (!bool)
      System.out.println(String.format("devices order differ: cells = \"%s\" devices = \"%s\"", new Object[] { paramString, str })); 
    return bool;
  }
  
  private String availaibleDeviceName(int paramInt) {
    String str;
    ArrayList<String> arrayList1 = new ArrayList(Arrays.asList((Object[])getResources().getStringArray(2130903040)));
    if (paramInt >= 0 && paramInt < arrayList1.size()) {
      str = arrayList1.get(paramInt);
    } else {
      str = str.get(0);
    } 
    ArrayList<String> arrayList2 = new ArrayList();
    if (this.devices != null && this.devices.size() > 0) {
      for (paramInt = 0; paramInt < this.devices.size(); paramInt++) {
        if (((Device)this.devices.get(paramInt)).getName().startsWith(str))
          arrayList2.add(((Device)this.devices.get(paramInt)).getName()); 
      } 
      str = String.format("%s%d", new Object[] { str, Integer.valueOf(arrayList2.size()) });
    } else {
      str = String.format("%s%d", new Object[] { str, Integer.valueOf(0) });
    } 
    return str;
  }
  
  private static String bytesToHex(byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder = new StringBuilder();
    int i = paramArrayOfbyte.length;
    for (byte b = 0; b < i; b++) {
      stringBuilder.append(String.format("%02x", new Object[] { Byte.valueOf(paramArrayOfbyte[b]) }));
    } 
    return stringBuilder.toString();
  }
  
  private boolean changeDevicesOrder(int paramInt1, int paramInt2, String paramString) {
    boolean bool;
    int i = (int)(this.devices.size() * 1.618D);
    int j = 0;
    int k = j;
    int m = paramInt2;
    int n = paramInt1;
    while (true) {
      String str;
      System.out.println(String.format("counter = %d", new Object[] { Integer.valueOf(j) }));
      paramInt1 = m;
      if (k) {
        System.out.println("result == true");
        String str1 = returnIdsOrder();
        String[] arrayOfString1 = str1.split(":");
        String[] arrayOfString2 = paramString.split(":");
        int[] arrayOfInt = new int[arrayOfString1.length];
        for (paramInt1 = 0; paramInt1 < arrayOfInt.length; paramInt1++) {
          str = arrayOfString1[paramInt1];
          for (paramInt2 = 0; paramInt2 < arrayOfString2.length; paramInt2++) {
            if (str.equalsIgnoreCase(arrayOfString2[paramInt2])) {
              arrayOfInt[paramInt1] = paramInt2 - paramInt1;
              break;
            } 
          } 
        } 
        System.out.println(str1);
        System.out.println(paramString);
        System.out.println(Arrays.toString(arrayOfInt));
        int i2 = 0;
        paramInt1 = i2;
        n = -1;
        paramInt2 = n;
        int i3 = paramInt1;
        m = paramInt1;
        paramInt1 = i3;
        while (paramInt1 < arrayOfInt.length) {
          i3 = i2;
          if (arrayOfInt[paramInt1] < i2) {
            i3 = arrayOfInt[paramInt1];
            n = paramInt1;
          } 
          int i4 = m;
          if (arrayOfInt[paramInt1] > m) {
            i4 = arrayOfInt[paramInt1];
            paramInt2 = paramInt1;
          } 
          paramInt1++;
          i2 = i3;
          m = i4;
        } 
        if (Math.abs(i2) > m) {
          paramInt1 = i2 + n;
        } else {
          paramInt1 = paramInt2 + m;
          n = paramInt2;
        } 
        System.out.println(String.format("index1 = %d, index2 = %d", new Object[] { Integer.valueOf(n), Integer.valueOf(paramInt1) }));
        moveDevice(n, paramInt1);
      } 
      paramInt2 = k;
      if (!j)
        bool = moveDevice(n, paramInt1); 
      PrintStream printStream = System.out;
      if (bool) {
        str = "result == true";
      } else {
        str = "result == false";
      } 
      printStream.println(str);
      int i1 = j + 1;
      if (!areDevicesListIdentical(paramString)) {
        m = paramInt1;
        j = i1;
        boolean bool1 = bool;
        if (i1 > i)
          break; 
        continue;
      } 
      break;
    } 
    if (!areDevicesListIdentical(paramString))
      sendNewConfigurationNotification(); 
    return bool;
  }
  
  private void clearDevicesOrder() {
    String str;
    long l;
    if (!isWorkingInDemoMode()) {
      str = this.settingsService.getCentralSerialNumber();
      l = this.userId;
    } else {
      str = "MB0DE170";
      l = 1L;
    } 
    this.settingsService.setDevicesOrder(str, l, new LinkedList());
    Collections.sort(this.devices);
    Intent intent = new Intent("ManagementService.Intents.newConfigurationReceived");
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
    this.mainThreadHandler.post(new IntentRunnable(intent));
  }
  
  private void copyDeviceFavourite(Device paramDevice, MobilusModel.Device.Builder paramBuilder) {
    paramBuilder.setFavourite(paramDevice.isFavourite());
  }
  
  private void copyDeviceGroupDevices(Group paramGroup, MobilusModel.DeviceGroup.Builder paramBuilder) {
    Device[] arrayOfDevice = paramGroup.getDevices();
    for (byte b = 0; b < arrayOfDevice.length; b++)
      paramBuilder.addAssignedDevicesIds(arrayOfDevice[b].getId()); 
  }
  
  private void copyDeviceGroupFavourite(Group paramGroup, MobilusModel.DeviceGroup.Builder paramBuilder) {
    paramBuilder.setFavourite(paramGroup.isFavourite());
  }
  
  private void copyDeviceGroupIcon(Group paramGroup, MobilusModel.DeviceGroup.Builder paramBuilder) {
    paramBuilder.setIcon(paramGroup.getIcon());
  }
  
  private void copyDeviceGroupId(Group paramGroup, MobilusModel.DeviceGroup.Builder paramBuilder) {
    paramBuilder.setId(paramGroup.getId());
  }
  
  private void copyDeviceGroupName(Group paramGroup, MobilusModel.DeviceGroup.Builder paramBuilder) {
    paramBuilder.setName(paramGroup.getName());
  }
  
  private void copyDeviceGroupPlaces(Group paramGroup, MobilusModel.DeviceGroup.Builder paramBuilder) {
    Place[] arrayOfPlace = paramGroup.getPlaces();
    for (byte b = 0; b < arrayOfPlace.length; b++)
      paramBuilder.addAssignedPlaceIds(arrayOfPlace[b].getId()); 
  }
  
  private void copyDeviceGroups(Device paramDevice, MobilusModel.Device.Builder paramBuilder) {
    Group[] arrayOfGroup = paramDevice.getGroups();
    for (byte b = 0; b < arrayOfGroup.length; b++)
      paramBuilder.addAssignedGroupIds(arrayOfGroup[b].getId()); 
  }
  
  private void copyDeviceIcon(Device paramDevice, MobilusModel.Device.Builder paramBuilder) {
    paramBuilder.setIcon(paramDevice.getIcon());
  }
  
  private void copyDeviceId(Device paramDevice, MobilusModel.Device.Builder paramBuilder) {
    paramBuilder.setId(paramDevice.getId());
  }
  
  private void copyDeviceName(Device paramDevice, MobilusModel.Device.Builder paramBuilder) {
    paramBuilder.setName(paramDevice.getName());
  }
  
  private void copyDevicePlaceDevices(Place paramPlace, MobilusModel.DevicePlace.Builder paramBuilder) {
    Device[] arrayOfDevice = paramPlace.getDevices();
    for (byte b = 0; b < arrayOfDevice.length; b++)
      paramBuilder.addAssignedDevicesIds(arrayOfDevice[b].getId()); 
  }
  
  private void copyDevicePlaceGroups(Place paramPlace, MobilusModel.DevicePlace.Builder paramBuilder) {
    Group[] arrayOfGroup = paramPlace.getGroups();
    for (byte b = 0; b < arrayOfGroup.length; b++)
      paramBuilder.addAssignedGroupIds(arrayOfGroup[b].getId()); 
  }
  
  private void copyDevicePlaceIcon(Place paramPlace, MobilusModel.DevicePlace.Builder paramBuilder) {
    paramBuilder.setIcon(paramPlace.getIcon());
  }
  
  private void copyDevicePlaceId(Place paramPlace, MobilusModel.DevicePlace.Builder paramBuilder) {
    paramBuilder.setId(paramPlace.getId());
  }
  
  private void copyDevicePlaceName(Place paramPlace, MobilusModel.DevicePlace.Builder paramBuilder) {
    paramBuilder.setName(paramPlace.getName());
  }
  
  private void copyDevicePlaces(Device paramDevice, MobilusModel.Device.Builder paramBuilder) {
    Place[] arrayOfPlace = paramDevice.getPlaces();
    for (byte b = 0; b < arrayOfPlace.length; b++)
      paramBuilder.addAssignedGroupIds(arrayOfPlace[b].getId()); 
  }
  
  private void copyDeviceType(Device paramDevice, MobilusModel.Device.Builder paramBuilder) {
    paramBuilder.setType(paramDevice.getType().getValue());
  }
  
  private void copySceneEnabled(Scene paramScene, MobilusModel.Scene.Builder paramBuilder) {
    paramBuilder.setEnabled(paramScene.isEnabled());
  }
  
  private void copySceneIcon(Scene paramScene, MobilusModel.Scene.Builder paramBuilder) {
    paramBuilder.setIcon(paramScene.getIcon());
  }
  
  private void copySceneId(Scene paramScene, MobilusModel.Scene.Builder paramBuilder) {
    paramBuilder.setId(paramScene.getId());
  }
  
  private void copySceneName(Scene paramScene, MobilusModel.Scene.Builder paramBuilder) {
    paramBuilder.setName(paramScene.getName());
  }
  
  private void copySceneSceneAstralSchedule(Scene paramScene, MobilusModel.Scene.Builder paramBuilder) {
    MobilusModel.SceneAstralSchedule[] arrayOfSceneAstralSchedule = paramScene.getAstralSchedules();
    for (byte b = 0; b < arrayOfSceneAstralSchedule.length; b++)
      paramBuilder.addSceneAstralSchedules(arrayOfSceneAstralSchedule[b]); 
  }
  
  private void copySceneSceneEvents(Scene paramScene, MobilusModel.Scene.Builder paramBuilder) {
    MobilusModel.SceneEvent[] arrayOfSceneEvent = paramScene.getEvents();
    for (byte b = 0; b < arrayOfSceneEvent.length; b++)
      paramBuilder.addSceneEvents(arrayOfSceneEvent[b]); 
  }
  
  private void copySceneSceneWeekSchedule(Scene paramScene, MobilusModel.Scene.Builder paramBuilder) {
    MobilusModel.SceneWeekSchedule[] arrayOfSceneWeekSchedule = paramScene.getWeekSchedules();
    for (byte b = 0; b < arrayOfSceneWeekSchedule.length; b++)
      paramBuilder.addSceneWeekSchedules(arrayOfSceneWeekSchedule[b]); 
  }
  
  private int defaultIcon(int paramInt) {
    switch (paramInt) {
      default:
        return 1;
      case 7:
      case 8:
      case 9:
        return 35;
      case 5:
      case 6:
        return 16;
      case 4:
        return 4;
      case 1:
      case 2:
      case 3:
        break;
    } 
    return 1;
  }
  
  private void disconnect() {
    if (this.communicationBinder != null) {
      this.communicationBinder.disconnect();
      setServiceState(ManagementServiceState.DISCONNECTED_IDLE);
    } 
    this.devices = null;
    this.devicesStates = null;
    this.places = null;
    this.groups = null;
    this.scenes = null;
    this.userId = -1L;
    this.location = null;
  }
  
  private Device findDevice(long paramLong) {
    Device device = null;
    for (byte b = 0; b < this.devices.size(); b++) {
      device = this.devices.get(b);
      if (device.getId() == paramLong)
        break; 
    } 
    return device;
  }
  
  private Group findGroup(long paramLong) {
    Group group = null;
    for (byte b = 0; b < this.groups.size(); b++) {
      group = this.groups.get(b);
      if (group.getId() == paramLong)
        break; 
    } 
    return group;
  }
  
  private Place findPlace(long paramLong) {
    Place place = null;
    for (byte b = 0; b < this.places.size(); b++) {
      place = this.places.get(b);
      if (place.getId() == paramLong)
        break; 
    } 
    return place;
  }
  
  private Scene findScene(long paramLong) {
    Scene scene = null;
    for (byte b = 0; b < this.scenes.size(); b++) {
      scene = this.scenes.get(b);
      if (scene.getId() == paramLong)
        break; 
    } 
    return scene;
  }
  
  private String getLastFirmwareUpdateProcessState() {
    return this.lastFirmwareUpdateProcessState;
  }
  
  private State getStateForGroup(Group paramGroup) {
    Action action;
    Device[] arrayOfDevice = paramGroup.getDevices();
    int i = arrayOfDevice.length;
    Device device1 = null;
    Device device2 = device1;
    byte b = 0;
    int j = 0;
    int k = -1;
    int m = 0;
    boolean bool = false;
    int n;
    for (n = -1;; n = i2) {
      int i1;
      int i2;
      Action action1;
      if (b < i) {
        Action action2;
        Device device3 = arrayOfDevice[b];
        State state = this.devicesStates.get(Long.valueOf(device3.getId()));
        device3 = device1;
        int i3 = j;
        int i4 = k;
        i1 = m;
        boolean bool1 = bool;
        i2 = n;
        Device device4 = device2;
        if (state != null) {
          Error error;
          if (state instanceof Error) {
            error = (Error)state;
            if (device1 == null) {
              ErrorType errorType = error.getErrorType();
              i3 = j;
              i4 = k;
              i1 = m;
              bool1 = bool;
              i2 = n;
              device4 = device2;
            } else {
              device3 = device1;
              i3 = j;
              i4 = k;
              i1 = m;
              bool1 = bool;
              i2 = n;
              device4 = device2;
              if (device1 == ErrorType.NO_CONNECTION) {
                device3 = device1;
                i3 = j;
                i4 = k;
                i1 = m;
                bool1 = bool;
                i2 = n;
                device4 = device2;
                if (error.getErrorType() != ErrorType.NO_CONNECTION) {
                  ErrorType errorType = error.getErrorType();
                  i3 = j;
                  i4 = k;
                  i1 = m;
                  bool1 = bool;
                  i2 = n;
                  device4 = device2;
                } 
              } 
            } 
          } else {
            Action action3;
            Reading reading = (Reading)error;
            bool1 = bool;
            if (!bool) {
              bool1 = bool;
              if (reading.isWorking() == true)
                bool1 = true; 
            } 
            if (device2 == null) {
              action3 = reading.getAction();
              if (reading.getPosition() != -1) {
                k = reading.getPosition();
                j = 1;
              } 
              if (reading.getLean() != -1) {
                i2 = reading.getLean();
                i1 = 1;
                device3 = device1;
                i3 = j;
                i4 = k;
                Action action4 = action3;
              } else {
                device4 = device1;
                i1 = m;
                bool = bool1;
                i2 = n;
                b++;
                device1 = device4;
                m = i1;
                n = i2;
              } 
            } else {
              action2 = action3;
              if (action3 == Action.OFF) {
                action2 = action3;
                if (reading.getAction() == Action.ON)
                  action2 = Action.ON; 
              } 
              i4 = j;
              i3 = k;
              if (reading.getPosition() != -1) {
                i3 = k + reading.getPosition();
                i4 = j + 1;
              } 
              device4 = device1;
              j = i4;
              k = i3;
              i1 = m;
              bool = bool1;
              i2 = n;
              action3 = action2;
              if (reading.getLean() != -1) {
                i2 = n + reading.getLean();
                i1 = m + 1;
                device4 = device1;
                j = i4;
                k = i3;
                bool = bool1;
                action3 = action2;
              } 
              b++;
              device1 = device4;
              m = i1;
              n = i2;
            } 
          } 
        } 
        device2 = device4;
        bool = bool1;
        k = i4;
        j = i3;
        action1 = action2;
      } else {
        break;
      } 
      b++;
      action = action1;
      m = i1;
    } 
    if (action != null)
      return (State)new Error(paramGroup.getId(), bool, (ErrorType)action); 
    if (j > 0) {
      j = k / j;
    } else {
      j = 0;
    } 
    if (m > 0) {
      k = n / m;
    } else {
      k = 0;
    } 
    return (State)new Reading(paramGroup.getId(), bool, (Action)device2, j, k);
  }
  
  private long getUserId() {
    return this.userId;
  }
  
  private boolean isAdminAndLocalMode() {
    boolean bool;
    if (this.settingsService.isAdmin() && !this.settingsService.shouldUseRemoteConnection()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isPasswordValid(byte[] paramArrayOfbyte) {
    return ByteString.copyFrom(this.settingsService.getUserPasswordHash()).equals(ByteString.copyFrom(paramArrayOfbyte));
  }
  
  private boolean isWorkingInDemoMode() {
    boolean bool;
    if (this.desiredConnectionStatus == ConnectionStatus.DEMO_MODE) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isWorkingInLocalMode() {
    return this.settingsService.shouldUseRemoteConnection() ^ true;
  }
  
  private void logOutAndRemoveAllData() {
    this.settingsService.clearAllSettings();
    disconnect();
  }
  
  private void logOutAndRemovePassword() {
    this.settingsService.clearPasswordHash();
    disconnect();
  }
  
  private boolean moveDevice(int paramInt1, int paramInt2) {
    boolean bool;
    if (paramInt1 < paramInt2) {
      boolean bool1 = true;
      while (true) {
        bool = bool1;
        if (paramInt1 < paramInt2) {
          if (bool1 && swapTwoDevices(paramInt1, paramInt1 + 1)) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          paramInt1++;
          continue;
        } 
        break;
      } 
    } else {
      boolean bool1 = true;
      while (true) {
        bool = bool1;
        if (paramInt1 > paramInt2) {
          if (bool1 && swapTwoDevices(paramInt1, paramInt1 - 1)) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          paramInt1--;
          continue;
        } 
        break;
      } 
    } 
    return bool;
  }
  
  private void moveDevices() {
    String str;
    long l;
    if (!isWorkingInDemoMode()) {
      str = this.settingsService.getCentralSerialNumber();
      l = this.userId;
    } else {
      str = "MB0DE170";
      l = 1L;
    } 
    this.settingsService.processDeviceOrder(str, l);
    LinkedList<SettingsService.DeviceOrder> linkedList = this.settingsService.getDevicesOrder();
    LinkedList<Integer> linkedList1 = new LinkedList();
    boolean bool1 = false;
    for (byte b = 0; b < linkedList.size(); b++) {
      int j;
      SettingsService.DeviceOrder deviceOrder = linkedList.get(b);
      int i = 0;
      while (true) {
        if (i < this.devices.size()) {
          if (((Device)this.devices.get(i)).getId() == deviceOrder.getDeviceId()) {
            int k = deviceOrder.getOffset() + i;
            int m = i;
            i = k;
            break;
          } 
          i++;
          continue;
        } 
        j = -1;
        i = j;
        break;
      } 
      if (j != -1) {
        Device device = this.devices.remove(j);
        j = i;
        if (i < 0) {
          i = deviceOrder.getOffset() - i;
          System.out.println(String.format("to < 0; actualOffset = %d", new Object[] { Integer.valueOf(i) }));
          this.settingsService.setActualOffset(b, i);
          ((SettingsService.DeviceOrder)linkedList.get(b)).setActualOffset(i);
          j = 0;
        } 
        if (j <= this.devices.size()) {
          this.devices.add(j, device);
        } else {
          this.devices.add(device);
          if (j - this.devices.size() > 0) {
            i = deviceOrder.getOffset() - j - this.devices.size();
            System.out.println(String.format("to > size; actualOffset = %d", new Object[] { Integer.valueOf(i) }));
            this.settingsService.setActualOffset(b, i);
            ((SettingsService.DeviceOrder)linkedList.get(b)).setActualOffset(i);
          } 
        } 
      } else {
        linkedList1.add(Integer.valueOf(b));
      } 
    } 
    boolean bool2 = bool1;
    if (linkedList1.size() > 0)
      bool2 = true; 
    while (linkedList1.size() > 0)
      linkedList.remove(((Integer)linkedList1.removeLast()).intValue()); 
    if (bool2)
      this.settingsService.setDevicesOrder(str, l, linkedList); 
  }
  
  private String returnIdsOrder() {
    StringBuilder stringBuilder = new StringBuilder();
    Iterator<Device> iterator = this.devices.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      stringBuilder.append(((Device)iterator.next()).getId());
      int j = i + 1;
      i = j;
      if (j < this.devices.size()) {
        stringBuilder.append(":");
        i = j;
      } 
    } 
    return stringBuilder.toString();
  }
  
  private boolean sendDeviceSettingsRequest(boolean paramBoolean, double paramDouble1, double paramDouble2, long paramLong) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.DeviceSettingsRequest.Builder builder = MobilusProtocol.DeviceSettingsRequest.newBuilder();
      if (paramBoolean) {
        builder.setAction(2);
        if (!Double.isNaN(paramDouble1))
          builder.setLatitude(paramDouble1); 
        if (!Double.isNaN(paramDouble2))
          builder.setLongitude(paramDouble2); 
        if (paramLong > 0L)
          builder.setNewTime(paramLong); 
      } else {
        builder.setAction(1);
      } 
      return this.communicationBinder.sendMessage(MessageType.DEVICE_SETTINGS_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendDeviceSettingsRequest(boolean paramBoolean1, String paramString, boolean paramBoolean2) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.DeviceSettingsRequest.Builder builder = MobilusProtocol.DeviceSettingsRequest.newBuilder();
      builder.setAction(2);
      builder.setRemoteAccessState(paramBoolean1);
      if (paramString != null) {
        builder.setEmailAddress(paramString);
        builder.setMarketingMaterials(paramBoolean2);
      } 
      return this.communicationBinder.sendMessage(MessageType.DEVICE_SETTINGS_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendDeviceSettingsRequestCheckUpdates() {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.DeviceSettingsRequest.Builder builder = MobilusProtocol.DeviceSettingsRequest.newBuilder();
      builder.setAction(3);
      return this.communicationBinder.sendMessage(MessageType.DEVICE_SETTINGS_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendFirmwareUpdateRequest() {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.FirmwareUpdateRequest.Builder builder = MobilusProtocol.FirmwareUpdateRequest.newBuilder();
      return this.communicationBinder.sendMessage(MessageType.FIRMWARE_UPDATE_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendNetworkSettingsRequest(WifiState paramWifiState, String paramString1, String paramString2) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.NetworkSettingsRequest.Builder builder = MobilusProtocol.NetworkSettingsRequest.newBuilder();
      if (paramWifiState != null) {
        builder.setAction(2);
        builder.setWifiMode(paramWifiState.getValue());
        if (paramString1 != null)
          builder.setWifiNetName(paramString1); 
        if (paramString2 != null)
          builder.setWifiNetPassword(paramString2); 
      } else {
        builder.setAction(1);
      } 
      return this.communicationBinder.sendMessage(MessageType.NETWORK_SETTINGS_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendNetworkSettingsRequest(boolean paramBoolean) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.NetworkSettingsRequest.Builder builder = MobilusProtocol.NetworkSettingsRequest.newBuilder();
      builder.setAction(2);
      builder.setEthernetState(paramBoolean);
      return this.communicationBinder.sendMessage(MessageType.NETWORK_SETTINGS_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private void sendNewConfigurationNotification() {
    System.out.println("Sending notification");
    Intent intent = new Intent("ManagementService.Intents.newConfigurationReceived");
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
    intent.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
    this.mainThreadHandler.post(new IntentRunnable(intent));
  }
  
  private boolean sendPing(String paramString) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.CallEvents.Builder builder = MobilusProtocol.CallEvents.newBuilder();
      MobilusModel.Event.Builder builder1 = MobilusModel.Event.newBuilder();
      builder1.setEventNumber(EventType.ACTION_ON_SESSION.getValue());
      builder1.setValue(paramString);
      builder.addEvents(builder1.build());
      return this.communicationBinder.sendMessage(MessageType.CALL_EVENTS, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendProgramNewDeviceRequest() {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.ProgramNewDeviceRequest.Builder builder = MobilusProtocol.ProgramNewDeviceRequest.newBuilder();
      return this.communicationBinder.sendMessage(MessageType.PROGRAM_NEW_DEVICE_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendUpdateDeviceGroupRequest(int paramInt, MobilusModel.DeviceGroup paramDeviceGroup) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.UpdateDeviceGroupRequest.Builder builder = MobilusProtocol.UpdateDeviceGroupRequest.newBuilder();
      builder.setOperationType(paramInt);
      builder.setDeviceGroup(paramDeviceGroup);
      return this.communicationBinder.sendMessage(MessageType.UPDATE_DEVICE_GROUP_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendUpdateDevicePlaceRequest(int paramInt, MobilusModel.DevicePlace paramDevicePlace) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.UpdateDevicePlaceRequest.Builder builder = MobilusProtocol.UpdateDevicePlaceRequest.newBuilder();
      builder.setOperationType(paramInt);
      builder.setDevicePlace(paramDevicePlace);
      return this.communicationBinder.sendMessage(MessageType.UPDATE_DEVICE_PLACE_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendUpdateDeviceRequest(int paramInt, MobilusModel.Device paramDevice) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.UpdateDeviceRequest.Builder builder = MobilusProtocol.UpdateDeviceRequest.newBuilder();
      builder.setOperationType(paramInt);
      builder.setDevice(paramDevice);
      return this.communicationBinder.sendMessage(MessageType.UPDATE_DEVICE_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendUpdateSceneRequest(int paramInt, MobilusModel.Scene paramScene) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.UpdateSceneRequest.Builder builder = MobilusProtocol.UpdateSceneRequest.newBuilder();
      builder.setOperationType(paramInt);
      builder.setScene(paramScene);
      return this.communicationBinder.sendMessage(MessageType.UPDATE_SCENE_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendUpdateUserRequest(int paramInt, MobilusModel.User paramUser, byte[] paramArrayOfbyte) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.UpdateUserRequest.Builder builder = MobilusProtocol.UpdateUserRequest.newBuilder();
      builder.setOperationType(paramInt);
      builder.setUser(paramUser);
      builder.setPassword(ByteString.copyFrom(paramArrayOfbyte));
      boolean bool = this.communicationBinder.sendMessage(MessageType.UPDATE_USER_REQUEST, (GeneratedMessageV3)builder.build());
      if (bool)
        this.lastModifiedUser = paramUser; 
      return bool;
    } 
    return false;
  }
  
  private boolean sendUserListRequest() {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.UserListRequest.Builder builder = MobilusProtocol.UserListRequest.newBuilder();
      return this.communicationBinder.sendMessage(MessageType.USER_LIST_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private boolean sendWifiNetworkListRequest() {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusProtocol.WifiNetworkListRequest.Builder builder = MobilusProtocol.WifiNetworkListRequest.newBuilder();
      return this.communicationBinder.sendMessage(MessageType.WIFI_NETWORK_LIST_REQUEST, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  private void setConnectionOptions(String paramString, byte[] paramArrayOfbyte) {
    this.settingsService.setUserLogin(paramString);
    this.settingsService.setUserPasswordHash(paramArrayOfbyte);
    if (this.communicationBinder != null) {
      this.communicationBinder.disconnect();
      this.communicationBinder.configureService(this, this.settingsService.getCentralSerialNumber());
    } 
  }
  
  private void setConnectionOptions(InetAddress paramInetAddress, String paramString, byte[] paramArrayOfbyte) {
    this.settingsService.setShouldUseRemoteConnection(false);
    this.settingsService.setCentralIpAddress(paramInetAddress);
    this.settingsService.setUserLogin(paramString);
    this.settingsService.setUserPasswordHash(paramArrayOfbyte);
    if (this.communicationBinder != null) {
      this.communicationBinder.disconnect();
      this.communicationBinder.configureService(this, paramInetAddress.getHostAddress(), this.settingsService.getCentralSerialNumber());
    } 
  }
  
  private void setConnectionType(ConnectionStatus paramConnectionStatus) {
    boolean bool;
    this.desiredConnectionStatus = paramConnectionStatus;
    if (paramConnectionStatus == ConnectionStatus.REMOTE_CONNECTION) {
      bool = true;
    } else {
      bool = false;
    } 
    this.settingsService.setShouldUseRemoteConnection(bool);
    if (paramConnectionStatus != ConnectionStatus.DEMO_MODE && !this.settingsService.hasDataToConnect(paramConnectionStatus))
      return; 
    if (this.communicationBinder != null)
      if (paramConnectionStatus == ConnectionStatus.DEMO_MODE && this.communicationBinder instanceof CloudService.CloudServiceBinder) {
        this.communicationBinder.disconnect();
        unbindService(this.serviceConnection);
        this.communicationBinder = null;
        bindService(new Intent((Context)this, DemoService.class), this.serviceConnection, 1);
      } else if (paramConnectionStatus != ConnectionStatus.DEMO_MODE && this.communicationBinder instanceof DemoService.DemoServiceBinder) {
        this.communicationBinder.disconnect();
        unbindService(this.serviceConnection);
        this.communicationBinder = null;
        bindService(new Intent((Context)this, CloudService.class), this.serviceConnection, 1);
      } else if ((bool && this.communicationBinder.isConnected() != ConnectionStatus.REMOTE_CONNECTION) || (!bool && this.communicationBinder.isConnected() != ConnectionStatus.LOCAL_CONNECTION)) {
        this.communicationBinder.disconnect();
        Intent intent = new Intent("ManagementService.Intents.connectionStatusChanged");
        intent.putExtra("ManagementService.Intents.connectionStatusChangedConnectionState", (Serializable)ConnectionStatus.NO_CONNECTION);
        this.mainThreadHandler.post(new IntentRunnable(intent));
        this.communicationBinder.connect(bool);
      }  
  }
  
  private void setServiceState(ManagementServiceState paramManagementServiceState) {
    if (paramManagementServiceState != this.serviceState) {
      if (paramManagementServiceState.isConnected() != this.serviceState.isConnected()) {
        ConnectionStatus connectionStatus;
        Intent intent = new Intent("ManagementService.Intents.connectionStatusChanged");
        if (!paramManagementServiceState.isConnected()) {
          connectionStatus = ConnectionStatus.NO_CONNECTION;
        } else if (this.settingsService.shouldUseRemoteConnection()) {
          connectionStatus = ConnectionStatus.REMOTE_CONNECTION;
        } else {
          connectionStatus = ConnectionStatus.LOCAL_CONNECTION;
        } 
        intent.putExtra("ManagementService.Intents.connectionStatusChangedConnectionState", (Serializable)connectionStatus);
        this.mainThreadHandler.post(new IntentRunnable(intent));
      } 
      if (paramManagementServiceState.isLogged() != this.serviceState.isLogged()) {
        Intent intent = new Intent("ManagementService.Intents.userLogStatusChanged");
        intent.putExtra("ManagementService.Intents.userLogStatusChangedIsUserLogged", paramManagementServiceState.isLogged());
        this.mainThreadHandler.post(new IntentRunnable(intent));
      } 
      this.serviceState = paramManagementServiceState;
    } 
  }
  
  private boolean swapTwoDevices(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iload_1
    //   1: istore_3
    //   2: aload_0
    //   3: invokespecial isWorkingInDemoMode : ()Z
    //   6: ifne -> 27
    //   9: aload_0
    //   10: getfield settingsService : Lpl/com/mobilelabs/mobilus/services/settingsservice/SettingsService;
    //   13: invokevirtual getCentralSerialNumber : ()Ljava/lang/String;
    //   16: astore #4
    //   18: aload_0
    //   19: getfield userId : J
    //   22: lstore #5
    //   24: goto -> 37
    //   27: ldc 'MB0DE170'
    //   29: astore #4
    //   31: lconst_1
    //   32: lstore #5
    //   34: goto -> 24
    //   37: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   40: ldc_w 'changeDevicesOrder(%d, %d)'
    //   43: iconst_2
    //   44: anewarray java/lang/Object
    //   47: dup
    //   48: iconst_0
    //   49: iload_1
    //   50: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   53: aastore
    //   54: dup
    //   55: iconst_1
    //   56: iload_2
    //   57: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   60: aastore
    //   61: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   64: invokevirtual println : (Ljava/lang/String;)V
    //   67: aload_0
    //   68: getfield devices : Ljava/util/ArrayList;
    //   71: iload_3
    //   72: invokevirtual get : (I)Ljava/lang/Object;
    //   75: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   78: invokevirtual getId : ()J
    //   81: lstore #7
    //   83: aload_0
    //   84: getfield devices : Ljava/util/ArrayList;
    //   87: iload_2
    //   88: invokevirtual get : (I)Ljava/lang/Object;
    //   91: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   94: invokevirtual getId : ()J
    //   97: lstore #9
    //   99: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   102: ldc_w 'deviceId1 = %d, deviceId2 = %d'
    //   105: iconst_2
    //   106: anewarray java/lang/Object
    //   109: dup
    //   110: iconst_0
    //   111: lload #7
    //   113: invokestatic valueOf : (J)Ljava/lang/Long;
    //   116: aastore
    //   117: dup
    //   118: iconst_1
    //   119: lload #9
    //   121: invokestatic valueOf : (J)Ljava/lang/Long;
    //   124: aastore
    //   125: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   128: invokevirtual println : (Ljava/lang/String;)V
    //   131: aload_0
    //   132: getfield settingsService : Lpl/com/mobilelabs/mobilus/services/settingsservice/SettingsService;
    //   135: invokevirtual getDevicesOrder : ()Ljava/util/LinkedList;
    //   138: astore #11
    //   140: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   143: aload #11
    //   145: invokevirtual toString : ()Ljava/lang/String;
    //   148: invokevirtual println : (Ljava/lang/String;)V
    //   151: new java/util/LinkedList
    //   154: dup
    //   155: invokespecial <init> : ()V
    //   158: astore #12
    //   160: iconst_0
    //   161: istore_1
    //   162: iconst_m1
    //   163: istore #13
    //   165: iconst_m1
    //   166: istore #14
    //   168: iload_1
    //   169: aload #11
    //   171: invokevirtual size : ()I
    //   174: if_icmpge -> 222
    //   177: aload #11
    //   179: iload_1
    //   180: invokevirtual get : (I)Ljava/lang/Object;
    //   183: checkcast pl/com/mobilelabs/mobilus/services/settingsservice/SettingsService$DeviceOrder
    //   186: astore #15
    //   188: aload #15
    //   190: invokevirtual getDeviceId : ()J
    //   193: lload #7
    //   195: lcmp
    //   196: ifne -> 202
    //   199: iload_1
    //   200: istore #13
    //   202: aload #15
    //   204: invokevirtual getDeviceId : ()J
    //   207: lload #9
    //   209: lcmp
    //   210: ifne -> 216
    //   213: iload_1
    //   214: istore #14
    //   216: iinc #1, 1
    //   219: goto -> 168
    //   222: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   225: ldc_w 'order1 = %d, order2 = %d'
    //   228: iconst_2
    //   229: anewarray java/lang/Object
    //   232: dup
    //   233: iconst_0
    //   234: iload #13
    //   236: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   239: aastore
    //   240: dup
    //   241: iconst_1
    //   242: iload #14
    //   244: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   247: aastore
    //   248: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   251: invokevirtual println : (Ljava/lang/String;)V
    //   254: iload #13
    //   256: iconst_m1
    //   257: if_icmpeq -> 291
    //   260: iload #14
    //   262: iconst_m1
    //   263: if_icmpeq -> 291
    //   266: iload #13
    //   268: iload #14
    //   270: if_icmple -> 282
    //   273: iconst_0
    //   274: istore #16
    //   276: iconst_1
    //   277: istore #17
    //   279: goto -> 306
    //   282: iconst_1
    //   283: istore #16
    //   285: iconst_0
    //   286: istore #17
    //   288: goto -> 306
    //   291: iload #13
    //   293: iconst_m1
    //   294: if_icmpne -> 273
    //   297: iload #14
    //   299: iconst_m1
    //   300: if_icmpeq -> 273
    //   303: goto -> 282
    //   306: iload #16
    //   308: ifeq -> 315
    //   311: iload #14
    //   313: istore #13
    //   315: iload #13
    //   317: iconst_m1
    //   318: if_icmpeq -> 594
    //   321: aload #11
    //   323: invokevirtual size : ()I
    //   326: iconst_1
    //   327: isub
    //   328: istore_1
    //   329: iload_1
    //   330: iload #13
    //   332: if_icmple -> 410
    //   335: iconst_0
    //   336: istore #14
    //   338: iload #14
    //   340: aload_0
    //   341: getfield devices : Ljava/util/ArrayList;
    //   344: invokevirtual size : ()I
    //   347: if_icmpge -> 404
    //   350: aload_0
    //   351: getfield devices : Ljava/util/ArrayList;
    //   354: iload #14
    //   356: invokevirtual get : (I)Ljava/lang/Object;
    //   359: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   362: invokevirtual getId : ()J
    //   365: aload #11
    //   367: iload_1
    //   368: invokevirtual get : (I)Ljava/lang/Object;
    //   371: checkcast pl/com/mobilelabs/mobilus/services/settingsservice/SettingsService$DeviceOrder
    //   374: invokevirtual getDeviceId : ()J
    //   377: lcmp
    //   378: ifne -> 398
    //   381: aload #11
    //   383: iload_1
    //   384: invokevirtual get : (I)Ljava/lang/Object;
    //   387: checkcast pl/com/mobilelabs/mobilus/services/settingsservice/SettingsService$DeviceOrder
    //   390: iload #14
    //   392: invokevirtual setPreviousIndex : (I)V
    //   395: goto -> 404
    //   398: iinc #14, 1
    //   401: goto -> 338
    //   404: iinc #1, -1
    //   407: goto -> 329
    //   410: aload #4
    //   412: astore #15
    //   414: lload #5
    //   416: lstore #18
    //   418: aload #15
    //   420: astore #4
    //   422: aload #11
    //   424: invokevirtual size : ()I
    //   427: iload #13
    //   429: if_icmple -> 598
    //   432: aload #11
    //   434: invokevirtual removeLast : ()Ljava/lang/Object;
    //   437: checkcast pl/com/mobilelabs/mobilus/services/settingsservice/SettingsService$DeviceOrder
    //   440: astore #20
    //   442: iconst_0
    //   443: istore_1
    //   444: iload_1
    //   445: aload_0
    //   446: getfield devices : Ljava/util/ArrayList;
    //   449: invokevirtual size : ()I
    //   452: if_icmpge -> 502
    //   455: aload_0
    //   456: getfield devices : Ljava/util/ArrayList;
    //   459: iload_1
    //   460: invokevirtual get : (I)Ljava/lang/Object;
    //   463: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   466: invokevirtual getId : ()J
    //   469: aload #20
    //   471: invokevirtual getDeviceId : ()J
    //   474: lcmp
    //   475: ifne -> 496
    //   478: iload_1
    //   479: aload #20
    //   481: invokevirtual getActualOffset : ()I
    //   484: isub
    //   485: istore #21
    //   487: iload_1
    //   488: istore #14
    //   490: iload #21
    //   492: istore_1
    //   493: goto -> 507
    //   496: iinc #1, 1
    //   499: goto -> 444
    //   502: iconst_m1
    //   503: istore #14
    //   505: iconst_m1
    //   506: istore_1
    //   507: iload #14
    //   509: iconst_m1
    //   510: if_icmpeq -> 573
    //   513: aload_0
    //   514: getfield devices : Ljava/util/ArrayList;
    //   517: iload #14
    //   519: invokevirtual remove : (I)Ljava/lang/Object;
    //   522: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   525: astore #4
    //   527: iload_1
    //   528: istore #14
    //   530: iload_1
    //   531: ifge -> 537
    //   534: iconst_0
    //   535: istore #14
    //   537: iload #14
    //   539: aload_0
    //   540: getfield devices : Ljava/util/ArrayList;
    //   543: invokevirtual size : ()I
    //   546: if_icmpgt -> 563
    //   549: aload_0
    //   550: getfield devices : Ljava/util/ArrayList;
    //   553: iload #14
    //   555: aload #4
    //   557: invokevirtual add : (ILjava/lang/Object;)V
    //   560: goto -> 573
    //   563: aload_0
    //   564: getfield devices : Ljava/util/ArrayList;
    //   567: aload #4
    //   569: invokevirtual add : (Ljava/lang/Object;)Z
    //   572: pop
    //   573: aload #11
    //   575: invokevirtual size : ()I
    //   578: iload #13
    //   580: if_icmple -> 414
    //   583: aload #12
    //   585: aload #20
    //   587: invokevirtual add : (Ljava/lang/Object;)Z
    //   590: pop
    //   591: goto -> 414
    //   594: lload #5
    //   596: lstore #18
    //   598: aload #12
    //   600: invokevirtual size : ()I
    //   603: ifle -> 812
    //   606: aload #12
    //   608: invokevirtual removeLast : ()Ljava/lang/Object;
    //   611: checkcast pl/com/mobilelabs/mobilus/services/settingsservice/SettingsService$DeviceOrder
    //   614: astore #15
    //   616: iconst_0
    //   617: istore_1
    //   618: iload_1
    //   619: aload_0
    //   620: getfield devices : Ljava/util/ArrayList;
    //   623: invokevirtual size : ()I
    //   626: if_icmpge -> 713
    //   629: aload_0
    //   630: getfield devices : Ljava/util/ArrayList;
    //   633: iload_1
    //   634: invokevirtual get : (I)Ljava/lang/Object;
    //   637: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   640: invokevirtual getId : ()J
    //   643: aload #15
    //   645: invokevirtual getDeviceId : ()J
    //   648: lcmp
    //   649: ifne -> 707
    //   652: aload #15
    //   654: invokevirtual getPreviousIndex : ()I
    //   657: istore #14
    //   659: iload #14
    //   661: iconst_m1
    //   662: if_icmpeq -> 689
    //   665: aload #15
    //   667: iload #14
    //   669: iload_1
    //   670: isub
    //   671: invokevirtual setOffset : (I)V
    //   674: aload #15
    //   676: iconst_m1
    //   677: invokevirtual setPreviousIndex : (I)V
    //   680: iload_1
    //   681: istore #13
    //   683: iload #14
    //   685: istore_1
    //   686: goto -> 719
    //   689: aload #15
    //   691: invokevirtual getActualOffset : ()I
    //   694: iload_1
    //   695: iadd
    //   696: istore #14
    //   698: iload_1
    //   699: istore #13
    //   701: iload #14
    //   703: istore_1
    //   704: goto -> 719
    //   707: iinc #1, 1
    //   710: goto -> 618
    //   713: iconst_m1
    //   714: istore #13
    //   716: iload #13
    //   718: istore_1
    //   719: iload_1
    //   720: istore #14
    //   722: iload #13
    //   724: iconst_m1
    //   725: if_icmpeq -> 788
    //   728: aload_0
    //   729: getfield devices : Ljava/util/ArrayList;
    //   732: iload #13
    //   734: invokevirtual remove : (I)Ljava/lang/Object;
    //   737: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   740: astore #20
    //   742: iload_1
    //   743: istore #14
    //   745: iload_1
    //   746: ifge -> 752
    //   749: iconst_0
    //   750: istore #14
    //   752: iload #14
    //   754: aload_0
    //   755: getfield devices : Ljava/util/ArrayList;
    //   758: invokevirtual size : ()I
    //   761: if_icmpgt -> 778
    //   764: aload_0
    //   765: getfield devices : Ljava/util/ArrayList;
    //   768: iload #14
    //   770: aload #20
    //   772: invokevirtual add : (ILjava/lang/Object;)V
    //   775: goto -> 788
    //   778: aload_0
    //   779: getfield devices : Ljava/util/ArrayList;
    //   782: aload #20
    //   784: invokevirtual add : (Ljava/lang/Object;)Z
    //   787: pop
    //   788: iload #14
    //   790: iload #13
    //   792: if_icmpeq -> 598
    //   795: iload #13
    //   797: iconst_m1
    //   798: if_icmpeq -> 598
    //   801: aload #11
    //   803: aload #15
    //   805: invokevirtual add : (Ljava/lang/Object;)Z
    //   808: pop
    //   809: goto -> 598
    //   812: iconst_0
    //   813: istore_1
    //   814: iload_1
    //   815: aload_0
    //   816: getfield devices : Ljava/util/ArrayList;
    //   819: invokevirtual size : ()I
    //   822: if_icmpge -> 890
    //   825: iload #17
    //   827: ifeq -> 853
    //   830: iload_1
    //   831: istore #14
    //   833: aload_0
    //   834: getfield devices : Ljava/util/ArrayList;
    //   837: iload_1
    //   838: invokevirtual get : (I)Ljava/lang/Object;
    //   841: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   844: invokevirtual getId : ()J
    //   847: lload #7
    //   849: lcmp
    //   850: ifeq -> 893
    //   853: iload #16
    //   855: ifeq -> 884
    //   858: aload_0
    //   859: getfield devices : Ljava/util/ArrayList;
    //   862: iload_1
    //   863: invokevirtual get : (I)Ljava/lang/Object;
    //   866: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   869: invokevirtual getId : ()J
    //   872: lload #9
    //   874: lcmp
    //   875: ifne -> 884
    //   878: iload_1
    //   879: istore #14
    //   881: goto -> 893
    //   884: iinc #1, 1
    //   887: goto -> 814
    //   890: iconst_m1
    //   891: istore #14
    //   893: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   896: astore #15
    //   898: iconst_1
    //   899: istore #22
    //   901: aload #15
    //   903: ldc_w 'from = %d'
    //   906: iconst_1
    //   907: anewarray java/lang/Object
    //   910: dup
    //   911: iconst_0
    //   912: iload #14
    //   914: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   917: aastore
    //   918: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   921: invokevirtual println : (Ljava/lang/String;)V
    //   924: iload #14
    //   926: iconst_m1
    //   927: if_icmpeq -> 1165
    //   930: iload #16
    //   932: ifeq -> 1050
    //   935: aload_0
    //   936: getfield devices : Ljava/util/ArrayList;
    //   939: iload #14
    //   941: invokevirtual remove : (I)Ljava/lang/Object;
    //   944: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   947: astore #15
    //   949: iload_3
    //   950: istore_1
    //   951: iload_3
    //   952: ifge -> 957
    //   955: iconst_0
    //   956: istore_1
    //   957: iload_1
    //   958: aload_0
    //   959: getfield devices : Ljava/util/ArrayList;
    //   962: invokevirtual size : ()I
    //   965: if_icmpgt -> 981
    //   968: aload_0
    //   969: getfield devices : Ljava/util/ArrayList;
    //   972: iload_1
    //   973: aload #15
    //   975: invokevirtual add : (ILjava/lang/Object;)V
    //   978: goto -> 991
    //   981: aload_0
    //   982: getfield devices : Ljava/util/ArrayList;
    //   985: aload #15
    //   987: invokevirtual add : (Ljava/lang/Object;)Z
    //   990: pop
    //   991: iload_1
    //   992: iload #14
    //   994: isub
    //   995: istore_1
    //   996: iload_1
    //   997: ifeq -> 1021
    //   1000: aload_0
    //   1001: getfield settingsService : Lpl/com/mobilelabs/mobilus/services/settingsservice/SettingsService;
    //   1004: aload #4
    //   1006: lload #18
    //   1008: aload #11
    //   1010: lload #9
    //   1012: iload_1
    //   1013: invokevirtual addDeviceOrder : (Ljava/lang/String;JLjava/util/LinkedList;JI)Ljava/util/LinkedList;
    //   1016: astore #4
    //   1018: goto -> 1036
    //   1021: aload_0
    //   1022: getfield settingsService : Lpl/com/mobilelabs/mobilus/services/settingsservice/SettingsService;
    //   1025: aload #4
    //   1027: lload #18
    //   1029: aload #11
    //   1031: invokevirtual setDevicesOrder : (Ljava/lang/String;JLjava/util/LinkedList;)Ljava/util/LinkedList;
    //   1034: astore #4
    //   1036: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   1039: aload #4
    //   1041: invokevirtual toString : ()Ljava/lang/String;
    //   1044: invokevirtual println : (Ljava/lang/String;)V
    //   1047: goto -> 1194
    //   1050: aload_0
    //   1051: getfield devices : Ljava/util/ArrayList;
    //   1054: iload #14
    //   1056: invokevirtual remove : (I)Ljava/lang/Object;
    //   1059: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   1062: astore #15
    //   1064: iload_2
    //   1065: istore_1
    //   1066: iload_2
    //   1067: ifge -> 1072
    //   1070: iconst_0
    //   1071: istore_1
    //   1072: iload_1
    //   1073: aload_0
    //   1074: getfield devices : Ljava/util/ArrayList;
    //   1077: invokevirtual size : ()I
    //   1080: if_icmpgt -> 1096
    //   1083: aload_0
    //   1084: getfield devices : Ljava/util/ArrayList;
    //   1087: iload_1
    //   1088: aload #15
    //   1090: invokevirtual add : (ILjava/lang/Object;)V
    //   1093: goto -> 1106
    //   1096: aload_0
    //   1097: getfield devices : Ljava/util/ArrayList;
    //   1100: aload #15
    //   1102: invokevirtual add : (Ljava/lang/Object;)Z
    //   1105: pop
    //   1106: iload_1
    //   1107: iload #14
    //   1109: isub
    //   1110: istore_1
    //   1111: iload_1
    //   1112: ifeq -> 1136
    //   1115: aload_0
    //   1116: getfield settingsService : Lpl/com/mobilelabs/mobilus/services/settingsservice/SettingsService;
    //   1119: aload #4
    //   1121: lload #18
    //   1123: aload #11
    //   1125: lload #7
    //   1127: iload_1
    //   1128: invokevirtual addDeviceOrder : (Ljava/lang/String;JLjava/util/LinkedList;JI)Ljava/util/LinkedList;
    //   1131: astore #4
    //   1133: goto -> 1151
    //   1136: aload_0
    //   1137: getfield settingsService : Lpl/com/mobilelabs/mobilus/services/settingsservice/SettingsService;
    //   1140: aload #4
    //   1142: lload #18
    //   1144: aload #11
    //   1146: invokevirtual setDevicesOrder : (Ljava/lang/String;JLjava/util/LinkedList;)Ljava/util/LinkedList;
    //   1149: astore #4
    //   1151: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   1154: aload #4
    //   1156: invokevirtual toString : ()Ljava/lang/String;
    //   1159: invokevirtual println : (Ljava/lang/String;)V
    //   1162: goto -> 1194
    //   1165: aload_0
    //   1166: getfield settingsService : Lpl/com/mobilelabs/mobilus/services/settingsservice/SettingsService;
    //   1169: aload #4
    //   1171: lload #18
    //   1173: aload #11
    //   1175: invokevirtual setDevicesOrder : (Ljava/lang/String;JLjava/util/LinkedList;)Ljava/util/LinkedList;
    //   1178: astore #4
    //   1180: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   1183: aload #4
    //   1185: invokevirtual toString : ()Ljava/lang/String;
    //   1188: invokevirtual println : (Ljava/lang/String;)V
    //   1191: iconst_0
    //   1192: istore #22
    //   1194: iload #22
    //   1196: ireturn
  }
  
  public void connectionStateChanged(ConnectionStatus paramConnectionStatus) {
    boolean bool;
    if (!this.serviceState.isConnected() && paramConnectionStatus == ConnectionStatus.DEMO_MODE) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramConnectionStatus == ConnectionStatus.NO_CONNECTION) {
      setServiceState(ManagementServiceState.CONNECTING);
    } else {
      if (this.devices == null) {
        MobilusProtocol.LoginRequest.Builder builder = MobilusProtocol.LoginRequest.newBuilder();
        if (this.settingsService.getUserLogin() != null)
          builder.setLogin(this.settingsService.getUserLogin()); 
        if (this.settingsService.getUserPasswordHash() != null)
          builder.setPassword(ByteString.copyFrom(this.settingsService.getUserPasswordHash())); 
        if (this.communicationBinder.sendMessage(MessageType.LOGIN_REQUEST, (GeneratedMessageV3)builder.build())) {
          setServiceState(ManagementServiceState.LOGIN_REQUEST_SENT);
        } else {
          setServiceState(ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE);
        } 
      } else if (this.devicesStates == null) {
        MobilusProtocol.CurrentStateRequest currentStateRequest = MobilusProtocol.CurrentStateRequest.newBuilder().build();
        if (this.communicationBinder.sendMessage(MessageType.CURRENT_STATE_REQUEST, (GeneratedMessageV3)currentStateRequest)) {
          setServiceState(ManagementServiceState.CURRENT_STATE_REQUEST_SENT);
        } else {
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
        } 
      } else if (this.places == null) {
        MobilusProtocol.DevicePlacesRequest devicePlacesRequest = MobilusProtocol.DevicePlacesRequest.newBuilder().build();
        if (this.communicationBinder.sendMessage(MessageType.DEVICE_PLACES_REQUEST, (GeneratedMessageV3)devicePlacesRequest)) {
          setServiceState(ManagementServiceState.DEVICE_PLACES_REQUEST_SENT);
        } else {
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
        } 
      } else if (this.groups == null) {
        MobilusProtocol.DeviceGroupsRequest deviceGroupsRequest = MobilusProtocol.DeviceGroupsRequest.newBuilder().build();
        if (this.communicationBinder.sendMessage(MessageType.DEVICE_GROUPS_REQUEST, (GeneratedMessageV3)deviceGroupsRequest)) {
          setServiceState(ManagementServiceState.DEVICE_GROUPS_REQUEST_SENT);
        } else {
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
        } 
      } else if (this.scenes == null) {
        MobilusProtocol.ScenesRequest scenesRequest = MobilusProtocol.ScenesRequest.newBuilder().build();
        if (this.communicationBinder.sendMessage(MessageType.SCENES_REQUEST, (GeneratedMessageV3)scenesRequest)) {
          setServiceState(ManagementServiceState.SCENES_REQUEST_SENT);
        } else {
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
        } 
      } else {
        setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
      } 
      if (this.serviceState != ManagementServiceState.CONNECTED_LOGGED_IDLE) {
        if (this.timeout == null) {
          this.timeout = new ExtendTimeout(5, this);
        } else {
          this.timeout.extend();
        } 
      } else if (this.timeout != null) {
        this.timeout.interrupt();
        this.timeout = null;
      } 
      this.timeoutCounter = 0;
    } 
    if (bool) {
      Intent intent = new Intent("ManagementService.Intents.connectionStatusChanged");
      intent.putExtra("ManagementService.Intents.connectionStatusChangedConnectionState", (Serializable)ConnectionStatus.DEMO_MODE);
      this.mainThreadHandler.post(new IntentRunnable(intent));
    } 
  }
  
  public void errorOccurred() {}
  
  public void newMessageReceived(ReceivedData paramReceivedData) {
    MobilusProtocol.DevicesListRequest devicesListRequest;
    if (paramReceivedData.getMessageStatus() == MessageStatus.OK) {
      Intent intent4;
      MobilusProtocol.UpdateSceneResponse updateSceneResponse;
      MobilusProtocol.UpdateDeviceResponse updateDeviceResponse;
      MobilusProtocol.ProgramNewDeviceResponse programNewDeviceResponse;
      MobilusProtocol.FirmwareUpdateResponse firmwareUpdateResponse;
      Intent intent3;
      MobilusProtocol.NetworkSettingsResponse networkSettingsResponse;
      MobilusProtocol.DevicesListRequest devicesListRequest1;
      Intent intent2;
      MobilusProtocol.CallEvents callEvents;
      MobilusProtocol.ScenesResponse scenesResponse;
      Intent intent1;
      MobilusProtocol.DevicePlacesResponse devicePlacesResponse;
      MobilusProtocol.ScenesRequest scenesRequest;
      MobilusProtocol.CurrentStateResponse currentStateResponse;
      MobilusProtocol.DevicePlacesRequest devicePlacesRequest;
      MobilusProtocol.DeviceGroupsResponse deviceGroupsResponse;
      MobilusProtocol.CurrentStateRequest currentStateRequest;
      MobilusProtocol.DevicesListResponse devicesListResponse;
      MobilusProtocol.DeviceGroupsRequest deviceGroupsRequest;
      MobilusProtocol.LoginResponse loginResponse;
      MobilusProtocol.UpdateDeviceGroupResponse updateDeviceGroupResponse;
      MobilusProtocol.UpdateDevicePlaceResponse updateDevicePlaceResponse;
      Intent intent5;
      MobilusProtocol.UserListResponse userListResponse;
      MobilusProtocol.WifiNetworkListResponse wifiNetworkListResponse;
      MobilusProtocol.DeviceSettingsResponse deviceSettingsResponse;
      MobilusProtocol.UpdateUserResponse updateUserResponse;
      ArrayList arrayList;
      switch (paramReceivedData.getMessageType()) {
        case null:
          updateDeviceGroupResponse = (MobilusProtocol.UpdateDeviceGroupResponse)paramReceivedData.getMessage();
          intent4 = new Intent("ManagementService.Intents.updateDeviceGroupResponseReceived");
          if (updateDeviceGroupResponse != null && updateDeviceGroupResponse.hasOperationStatus())
            intent4.putExtra("ManagementService.Intents.updateDeviceGroupResponseReceivedOperationStatus", updateDeviceGroupResponse.getOperationStatus()); 
          this.mainThreadHandler.post(new IntentRunnable(intent4));
          break;
        case null:
          updateDevicePlaceResponse = (MobilusProtocol.UpdateDevicePlaceResponse)intent4.getMessage();
          intent4 = new Intent("ManagementService.Intents.updateDevicePlaceResponseReceived");
          if (updateDevicePlaceResponse != null && updateDevicePlaceResponse.hasOperationStatus())
            intent4.putExtra("ManagementService.Intents.updateDevicePlaceResponseReceivedOperationStatus", updateDevicePlaceResponse.getOperationStatus()); 
          this.mainThreadHandler.post(new IntentRunnable(intent4));
          break;
        case null:
          updateSceneResponse = (MobilusProtocol.UpdateSceneResponse)intent4.getMessage();
          intent5 = new Intent("ManagementService.Intents.updateSceneResponseReceived");
          if (updateSceneResponse != null && updateSceneResponse.hasOperationStatus())
            intent5.putExtra("ManagementService.Intents.updateSceneResponseReceivedOperationStatus", updateSceneResponse.getOperationStatus()); 
          this.mainThreadHandler.post(new IntentRunnable(intent5));
          break;
        case null:
          updateDeviceResponse = (MobilusProtocol.UpdateDeviceResponse)updateSceneResponse.getMessage();
          intent5 = new Intent("ManagementService.Intents.updateDeviceResponseReceived");
          if (updateDeviceResponse != null && updateDeviceResponse.hasOperationStatus())
            intent5.putExtra("ManagementService.Intents.updateDeviceResponseReceivedOperationStatus", updateDeviceResponse.getOperationStatus()); 
          this.mainThreadHandler.post(new IntentRunnable(intent5));
          break;
        case null:
          programNewDeviceResponse = (MobilusProtocol.ProgramNewDeviceResponse)updateDeviceResponse.getMessage();
          intent5 = new Intent("ManagementService.Intents.programNewDeviceResponseReceived");
          if (programNewDeviceResponse != null) {
            if (programNewDeviceResponse.hasId())
              intent5.putExtra("ManagementService.Intents.programNewDeviceResponseReceivedDeviceId", programNewDeviceResponse.getId()); 
            if (programNewDeviceResponse.hasType())
              intent5.putExtra("ManagementService.Intents.programNewDeviceResponseReceivedDeviceType", programNewDeviceResponse.getType()); 
          } 
          this.mainThreadHandler.post(new IntentRunnable(intent5));
          break;
        case null:
          firmwareUpdateResponse = (MobilusProtocol.FirmwareUpdateResponse)programNewDeviceResponse.getMessage();
          intent5 = new Intent("ManagementService.Intents.firmwareUpdateResponseReceived");
          if (firmwareUpdateResponse != null && firmwareUpdateResponse.hasOperationStatus())
            intent5.putExtra("ManagementService.Intents.firmwareUpdateResponseReceivedOperationStatus", firmwareUpdateResponse.getOperationStatus()); 
          this.mainThreadHandler.post(new IntentRunnable(intent5));
          break;
        case null:
          userListResponse = (MobilusProtocol.UserListResponse)firmwareUpdateResponse.getMessage();
          intent3 = new Intent("ManagementService.Intents.usersListReceived");
          arrayList = new ArrayList();
          if (userListResponse != null && userListResponse.getUsersCount() > 0)
            arrayList.addAll(userListResponse.getUsersList()); 
          intent3.putExtra("ManagementService.Intents.usersListReceivedUsersList", arrayList);
          this.mainThreadHandler.post(new IntentRunnable(intent3));
          break;
        case null:
          wifiNetworkListResponse = (MobilusProtocol.WifiNetworkListResponse)intent3.getMessage();
          intent3 = new Intent("ManagementService.Intents.wifiNetworksListReceived");
          arrayList = new ArrayList();
          if (wifiNetworkListResponse != null && wifiNetworkListResponse.getWifiNetworksCount() > 0)
            arrayList.addAll(wifiNetworkListResponse.getWifiNetworksList()); 
          intent3.putExtra("ManagementService.Intents.wifiNetworksListReceivedNetworksList", arrayList);
          this.mainThreadHandler.post(new IntentRunnable(intent3));
          break;
        case null:
          networkSettingsResponse = (MobilusProtocol.NetworkSettingsResponse)intent3.getMessage();
          if (networkSettingsResponse.hasOperationStatus()) {
            Intent intent = new Intent("ManagementService.Intents.networkConfigurationReceived");
            intent.putExtra("ManagementService.Intents.networkConfigurationReceivedOperationStatus", networkSettingsResponse.getOperationStatus());
            if (networkSettingsResponse.getOperationStatus() == 0) {
              if (networkSettingsResponse.hasEthernetState())
                intent.putExtra("ManagementService.Intents.networkConfigurationReceivedEthernetState", networkSettingsResponse.getEthernetState()); 
              if (networkSettingsResponse.hasWifiMode()) {
                WifiState wifiState = WifiState.fromValue(networkSettingsResponse.getWifiMode());
                if (wifiState != null) {
                  intent.putExtra("ManagementService.Intents.networkConfigurationReceivedWifiState", (Serializable)wifiState);
                  if (networkSettingsResponse.hasWifiNetName())
                    intent.putExtra("ManagementService.Intents.networkConfigurationReceivedNetName", networkSettingsResponse.getWifiNetName()); 
                  if (networkSettingsResponse.hasWifiNetPassword())
                    intent.putExtra("ManagementService.Intents.networkConfigurationReceivedNetPassword", networkSettingsResponse.getWifiNetPassword()); 
                } 
              } 
            } 
            this.mainThreadHandler.post(new IntentRunnable(intent));
          } 
          break;
        case null:
          deviceSettingsResponse = (MobilusProtocol.DeviceSettingsResponse)networkSettingsResponse.getMessage();
          if (deviceSettingsResponse.hasOperationStatus()) {
            Intent intent = new Intent("ManagementService.Intents.deviceConfigurationReceived");
            intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedOperationStatus", deviceSettingsResponse.getOperationStatus());
            if (deviceSettingsResponse.getOperationStatus() == 0) {
              if (deviceSettingsResponse.hasLatitude())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedLatitude", deviceSettingsResponse.getLatitude()); 
              if (deviceSettingsResponse.hasLongitude())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedLongitude", deviceSettingsResponse.getLongitude()); 
              if (deviceSettingsResponse.hasLatitude() && deviceSettingsResponse.hasLongitude()) {
                if (this.location == null)
                  this.location = new Location(""); 
                this.location.setLatitude(deviceSettingsResponse.getLatitude());
                this.location.setLongitude(deviceSettingsResponse.getLongitude());
              } 
              if (deviceSettingsResponse.hasRemoteAccessState())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccess", deviceSettingsResponse.getRemoteAccessState()); 
              if (deviceSettingsResponse.hasCurrentTime())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentTime", deviceSettingsResponse.getCurrentTime()); 
              if (deviceSettingsResponse.hasEthernetIp())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedEthernetIp", deviceSettingsResponse.getEthernetIp()); 
              if (deviceSettingsResponse.hasWifiIp())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedWifiIp", deviceSettingsResponse.getWifiIp()); 
              if (deviceSettingsResponse.hasEthernetMac())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedEthernetMac", deviceSettingsResponse.getEthernetMac()); 
              if (deviceSettingsResponse.hasWifiMac())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedWifiMac", deviceSettingsResponse.getWifiMac()); 
              if (deviceSettingsResponse.hasSunriseTime())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedSunriseTime", deviceSettingsResponse.getSunriseTime()); 
              if (deviceSettingsResponse.hasSunsetTime())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedSunsetTime", deviceSettingsResponse.getSunsetTime()); 
              if (deviceSettingsResponse.hasRemoteAccessConnection()) {
                RemoteAccessState remoteAccessState = RemoteAccessState.fromString(deviceSettingsResponse.getRemoteAccessConnection());
                if (remoteAccessState != null)
                  intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccessState", (Serializable)remoteAccessState); 
              } 
              if (deviceSettingsResponse.hasCurrentFirmwareVersion())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentFirmwareVersion", deviceSettingsResponse.getCurrentFirmwareVersion()); 
              if (deviceSettingsResponse.hasLatestFirmwareVersion())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedNewestFirmwareVersion", deviceSettingsResponse.getLatestFirmwareVersion()); 
              if (deviceSettingsResponse.hasCurrentFirmwareVersion() && deviceSettingsResponse.hasLatestFirmwareVersion() && deviceSettingsResponse.getCurrentFirmwareVersion().equalsIgnoreCase(deviceSettingsResponse.getLatestFirmwareVersion()))
                this.lastFirmwareUpdateProcessState = null; 
              if (deviceSettingsResponse.hasEmailAddress())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedEmailAddress", deviceSettingsResponse.getEmailAddress()); 
              if (deviceSettingsResponse.hasMarketingMaterials())
                intent.putExtra("ManagementService.Intents.deviceConfigurationReceivedMarketingMaterials", deviceSettingsResponse.getMarketingMaterials()); 
            } 
            this.mainThreadHandler.post(new IntentRunnable(intent));
            if (this.serviceState == ManagementServiceState.DEVICE_SETTINGS_REQUEST_SENT) {
              devicesListRequest1 = MobilusProtocol.DevicesListRequest.newBuilder().build();
              if (this.communicationBinder.sendMessage(MessageType.DEVICES_LIST_REQUEST, (GeneratedMessageV3)devicesListRequest1)) {
                setServiceState(ManagementServiceState.DEVICES_LIST_REQUEST_SENT);
                break;
              } 
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
            } 
          } 
          break;
        case null:
          updateUserResponse = (MobilusProtocol.UpdateUserResponse)devicesListRequest1.getMessage();
          if (updateUserResponse.hasOperationStatus() && updateUserResponse.getOperationStatus() == 0 && this.lastModifiedUser != null && this.lastModifiedUser.hasId()) {
            if (this.userId >= 0L && this.lastModifiedUser.getId() == this.userId) {
              if (this.lastModifiedUser.hasLogin())
                this.settingsService.setUserLogin(this.lastModifiedUser.getLogin()); 
              if (this.lastModifiedUser.hasPassword())
                this.settingsService.setUserPasswordHash(this.lastModifiedUser.getPassword().toByteArray()); 
            } 
            this.lastModifiedUser = null;
          } 
          intent2 = new Intent("ManagementService.Intents.updateUserResponseReceived");
          if (updateUserResponse.hasOperationStatus())
            intent2.putExtra("ManagementService.Intents.updateUserResponseReceivedOperationStatus", updateUserResponse.getOperationStatus()); 
          this.mainThreadHandler.post(new IntentRunnable(intent2));
          break;
        case null:
          callEvents = (MobilusProtocol.CallEvents)intent2.getMessage();
          if (callEvents != null && callEvents.getEventsCount() > 0)
            for (MobilusModel.Event event : callEvents.getEventsList()) {
              if (event.hasEventNumber()) {
                State state;
                MobilusProtocol.DevicesListRequest devicesListRequest2;
                EventType eventType = EventType.fromValue(event.getEventNumber());
                switch (eventType) {
                  default:
                    continue;
                  case null:
                    if (event.hasValue()) {
                      this.lastFirmwareUpdateProcessState = event.getValue();
                      Intent intent = new Intent("ManagementService.Intents.firmwareUpdateProcessStateChanged");
                      intent.putExtra("ManagementService.Intents.firmwareUpdateProcessStateChangedValue", event.getValue());
                      this.mainThreadHandler.post(new IntentRunnable(intent));
                    } 
                    continue;
                  case null:
                    if (event.hasValue()) {
                      Intent intent = new Intent("ManagementService.Intents.remoteConnectionChanged");
                      intent.putExtra("ManagementService.Intents.remoteConnectionChangedValue", event.getValue());
                      this.mainThreadHandler.post(new IntentRunnable(intent));
                    } 
                    continue;
                  case null:
                    if (event.hasValue()) {
                      PrintStream printStream = System.out;
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append("Ping: ");
                      stringBuilder.append(event.getValue());
                      printStream.println(stringBuilder.toString());
                      Intent intent = new Intent("ManagementService.Intents.sessionAction");
                      intent.putExtra("ManagementService.Intents.sessionActionValue", event.getValue());
                      this.mainThreadHandler.post(new IntentRunnable(intent));
                    } 
                    continue;
                  case null:
                  case null:
                    if (event.hasDeviceId() && event.hasValue()) {
                      state = State.fromEvent(event);
                      if (state != null && this.devicesStates != null) {
                        this.devicesStates.put(Long.valueOf(state.getId()), state);
                        HashMap<Object, Object> hashMap = new HashMap<>();
                        Iterator<Device> iterator = this.devices.iterator();
                        while (iterator.hasNext()) {
                          Device device = iterator.next();
                          if (device.getId() == state.getId()) {
                            for (Group group : device.getGroups()) {
                              State state1 = getStateForGroup(group);
                              this.groupsStates.put(Long.valueOf(group.getId()), state1);
                              hashMap.put(Long.valueOf(group.getId()), state1);
                            } 
                            break;
                          } 
                        } 
                        Intent intent = new Intent("ManagementService.Intents.deviceActionFinished");
                        intent.putExtra("ManagementService.Intents.deviceActionFinishedResultDevice", (Serializable)state);
                        if (hashMap.size() > 0)
                          intent.putExtra("ManagementService.Intents.deviceActionFinishedResultGroups", hashMap); 
                        this.mainThreadHandler.post(new IntentRunnable(intent));
                      } 
                    } 
                    continue;
                  case SCENES_REQUEST_SENT:
                    if (state.hasDeviceId() && state.hasValue()) {
                      State state1 = State.fromEvent((MobilusModel.Event)state);
                      if (state1 != null && this.devicesStates != null) {
                        this.devicesStates.put(Long.valueOf(state1.getId()), state1);
                        HashMap<Object, Object> hashMap = new HashMap<>();
                        for (Device device : this.devices) {
                          if (device.getId() == state1.getId()) {
                            for (Group group : device.getGroups()) {
                              State state2 = getStateForGroup(group);
                              this.groupsStates.put(Long.valueOf(group.getId()), state2);
                              hashMap.put(Long.valueOf(group.getId()), state2);
                            } 
                            break;
                          } 
                        } 
                        Intent intent = new Intent("ManagementService.Intents.deviceActionStarted");
                        intent.putExtra("ManagementService.Intents.deviceActionStartedDeviceId", (Serializable)state1);
                        if (hashMap.size() > 0)
                          intent.putExtra("ManagementService.Intents.deviceActionStartedGroupsStates", hashMap); 
                        this.mainThreadHandler.post(new IntentRunnable(intent));
                      } 
                    } 
                    continue;
                  case DEVICE_GROUPS_REQUEST_SENT:
                    devicesListRequest2 = MobilusProtocol.DevicesListRequest.newBuilder().build();
                    if (this.communicationBinder.sendMessage(MessageType.DEVICES_LIST_REQUEST, (GeneratedMessageV3)devicesListRequest2)) {
                      setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_DEVICES_LIST_REQUEST_SENT);
                      continue;
                    } 
                    setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
                    continue;
                  case DEVICE_PLACES_REQUEST_SENT:
                    devicesListRequest2 = MobilusProtocol.DevicesListRequest.newBuilder().build();
                    if (this.communicationBinder.sendMessage(MessageType.DEVICES_LIST_REQUEST, (GeneratedMessageV3)devicesListRequest2)) {
                      setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_DEVICES_LIST_REQUEST_SENT);
                      continue;
                    } 
                    setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
                    continue;
                  case CURRENT_STATE_REQUEST_SENT:
                    devicesListRequest2 = MobilusProtocol.DevicesListRequest.newBuilder().build();
                    if (this.communicationBinder.sendMessage(MessageType.DEVICES_LIST_REQUEST, (GeneratedMessageV3)devicesListRequest2)) {
                      setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_DEVICES_LIST_REQUEST_SENT);
                      continue;
                    } 
                    setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
                    continue;
                  case DEVICES_LIST_REQUEST_SENT:
                    devicesListRequest2 = MobilusProtocol.DevicesListRequest.newBuilder().build();
                    if (this.communicationBinder.sendMessage(MessageType.DEVICES_LIST_REQUEST, (GeneratedMessageV3)devicesListRequest2)) {
                      setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_DEVICES_LIST_REQUEST_SENT);
                      continue;
                    } 
                    setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
                    continue;
                  case LOGIN_REQUEST_SENT:
                    break;
                } 
                MobilusProtocol.LoginRequest loginRequest = MobilusProtocol.LoginRequest.newBuilder().setLogin(this.settingsService.getUserLogin()).setPassword(ByteString.copyFrom(this.settingsService.getUserPasswordHash())).build();
                if (this.communicationBinder.sendMessage(MessageType.LOGIN_REQUEST, (GeneratedMessageV3)loginRequest)) {
                  setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_LOGIN_REQUEST_SENT);
                  continue;
                } 
                setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
              } 
            }  
          break;
        case null:
          scenesResponse = (MobilusProtocol.ScenesResponse)callEvents.getMessage();
          this.scenes = new ArrayList<>();
          if (scenesResponse != null && scenesResponse.getScenesList() != null) {
            Iterator<MobilusModel.Scene> iterator = scenesResponse.getScenesList().iterator();
            while (iterator.hasNext()) {
              Scene scene = Scene.createFrom(iterator.next(), this.location);
              if (scene != null)
                this.scenes.add(scene); 
            } 
            Collections.sort(this.scenes);
          } 
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          intent1 = new Intent("ManagementService.Intents.newConfigurationReceived");
          intent1.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
          intent1.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
          intent1.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
          intent1.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
          intent1.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
          intent1.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
          intent1.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
          this.mainThreadHandler.post(new IntentRunnable(intent1));
          break;
        case null:
          devicePlacesResponse = (MobilusProtocol.DevicePlacesResponse)intent1.getMessage();
          this.places = new ArrayList<>();
          if (devicePlacesResponse != null && devicePlacesResponse.getDevicePlacesList() != null) {
            for (MobilusModel.DevicePlace devicePlace : devicePlacesResponse.getDevicePlacesList()) {
              Place place = Place.createFrom(devicePlace);
              if (place != null) {
                if (this.groups != null && this.groups.size() > 0 && devicePlace.getAssignedGroupIdsCount() > 0)
                  for (Long long_ : devicePlace.getAssignedGroupIdsList()) {
                    for (Group group : this.groups) {
                      if (long_.longValue() == group.getId()) {
                        place.addGroup(group);
                        group.addPlace(place);
                      } 
                    } 
                  }  
                if (this.devices != null && this.devices.size() > 0 && devicePlace.getAssignedDevicesIdsCount() > 0)
                  for (Long long_ : devicePlace.getAssignedDevicesIdsList()) {
                    for (Device device : this.devices) {
                      if (long_.longValue() == device.getId()) {
                        place.addDevice(device);
                        device.addPlace(place);
                      } 
                    } 
                  }  
                this.places.add(place);
              } 
            } 
            Collections.sort(this.places);
          } 
          if (this.serviceState == ManagementServiceState.CONFIGURATION_CHANGED_DEVICE_PLACES_REQUEST_SENT) {
            MobilusProtocol.ScenesRequest scenesRequest1 = MobilusProtocol.ScenesRequest.newBuilder().build();
            if (this.communicationBinder.sendMessage(MessageType.SCENES_REQUEST, (GeneratedMessageV3)scenesRequest1)) {
              setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_SCENES_REQUEST_SENT);
            } else {
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
            } 
            Intent intent = new Intent("ManagementService.Intents.newConfigurationReceived");
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
            this.mainThreadHandler.post(new IntentRunnable(intent));
            break;
          } 
          scenesRequest = MobilusProtocol.ScenesRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.SCENES_REQUEST, (GeneratedMessageV3)scenesRequest)) {
            setServiceState(ManagementServiceState.SCENES_REQUEST_SENT);
            break;
          } 
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          break;
        case null:
          currentStateResponse = (MobilusProtocol.CurrentStateResponse)scenesRequest.getMessage();
          this.devicesStates = new HashMap<>();
          this.groupsStates = new HashMap<>();
          if (currentStateResponse != null && currentStateResponse.getEventsList() != null) {
            for (MobilusModel.Event event : currentStateResponse.getEventsList()) {
              State state = State.fromEvent(event);
              if (state != null)
                this.devicesStates.put(Long.valueOf(event.getDeviceId()), state); 
            } 
            for (Group group : this.groups) {
              State state = getStateForGroup(group);
              if (state != null)
                this.groupsStates.put(Long.valueOf(group.getId()), state); 
            } 
          } 
          if (this.serviceState == ManagementServiceState.CONFIGURATION_CHANGED_CURRENT_STATE_REQUEST_SENT) {
            MobilusProtocol.DevicePlacesRequest devicePlacesRequest1 = MobilusProtocol.DevicePlacesRequest.newBuilder().build();
            if (this.communicationBinder.sendMessage(MessageType.DEVICE_PLACES_REQUEST, (GeneratedMessageV3)devicePlacesRequest1)) {
              setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_DEVICE_PLACES_REQUEST_SENT);
            } else {
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
            } 
            Intent intent = new Intent("ManagementService.Intents.newConfigurationReceived");
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
            this.mainThreadHandler.post(new IntentRunnable(intent));
            break;
          } 
          devicePlacesRequest = MobilusProtocol.DevicePlacesRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.DEVICE_PLACES_REQUEST, (GeneratedMessageV3)devicePlacesRequest)) {
            setServiceState(ManagementServiceState.DEVICE_PLACES_REQUEST_SENT);
            break;
          } 
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          break;
        case SCENES_REQUEST_SENT:
          deviceGroupsResponse = (MobilusProtocol.DeviceGroupsResponse)devicePlacesRequest.getMessage();
          this.groups = new ArrayList<>();
          if (deviceGroupsResponse != null && deviceGroupsResponse.getDeviceGroupsList() != null) {
            for (MobilusModel.DeviceGroup deviceGroup : deviceGroupsResponse.getDeviceGroupsList()) {
              Group group = Group.createFrom(deviceGroup);
              if (group != null) {
                if (this.places != null && this.places.size() > 0 && deviceGroup.getAssignedPlaceIdsCount() > 0)
                  for (Long long_ : deviceGroup.getAssignedPlaceIdsList()) {
                    for (Place place : this.places) {
                      if (long_.longValue() == place.getId()) {
                        group.addPlace(place);
                        place.addGroup(group);
                      } 
                    } 
                  }  
                if (this.devices != null && this.devices.size() > 0 && deviceGroup.getAssignedDevicesIdsCount() > 0)
                  for (Long long_ : deviceGroup.getAssignedDevicesIdsList()) {
                    for (Device device : this.devices) {
                      if (long_.longValue() == device.getId()) {
                        group.addDevice(device);
                        device.addGroup(group);
                      } 
                    } 
                  }  
                this.groups.add(group);
              } 
            } 
            Collections.sort(this.groups);
          } 
          if (this.serviceState == ManagementServiceState.CONFIGURATION_CHANGED_DEVICE_GROUPS_REQUEST_SENT) {
            MobilusProtocol.CurrentStateRequest currentStateRequest1 = MobilusProtocol.CurrentStateRequest.newBuilder().build();
            if (this.communicationBinder.sendMessage(MessageType.CURRENT_STATE_REQUEST, (GeneratedMessageV3)currentStateRequest1)) {
              setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_CURRENT_STATE_REQUEST_SENT);
            } else {
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
            } 
            Intent intent = new Intent("ManagementService.Intents.newConfigurationReceived");
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
            this.mainThreadHandler.post(new IntentRunnable(intent));
            break;
          } 
          currentStateRequest = MobilusProtocol.CurrentStateRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.CURRENT_STATE_REQUEST, (GeneratedMessageV3)currentStateRequest)) {
            setServiceState(ManagementServiceState.CURRENT_STATE_REQUEST_SENT);
            break;
          } 
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          break;
        case DEVICE_GROUPS_REQUEST_SENT:
          devicesListResponse = (MobilusProtocol.DevicesListResponse)currentStateRequest.getMessage();
          this.devices = new ArrayList<>();
          if (devicesListResponse != null && devicesListResponse.getDevicesList() != null) {
            for (MobilusModel.Device device1 : devicesListResponse.getDevicesList()) {
              Device device = Device.createFrom(device1);
              if (device != null) {
                if (this.groups != null && this.groups.size() > 0 && device1.getAssignedGroupIdsCount() > 0)
                  for (Long long_ : device1.getAssignedGroupIdsList()) {
                    for (Group group : this.groups) {
                      if (long_.longValue() == group.getId()) {
                        device.addGroup(group);
                        group.addDevice(device);
                      } 
                    } 
                  }  
                if (this.places != null && this.places.size() > 0 && device1.getAssignedPlaceIdsCount() > 0)
                  for (Long long_ : device1.getAssignedPlaceIdsList()) {
                    for (Place place : this.places) {
                      if (long_.longValue() == place.getId()) {
                        device.addPlace(place);
                        place.addDevice(device);
                      } 
                    } 
                  }  
                this.devices.add(device);
              } 
            } 
            Collections.sort(this.devices);
            moveDevices();
          } 
          if (this.serviceState == ManagementServiceState.CONFIGURATION_CHANGED_DEVICES_LIST_REQUEST_SENT) {
            MobilusProtocol.DeviceGroupsRequest deviceGroupsRequest1 = MobilusProtocol.DeviceGroupsRequest.newBuilder().build();
            if (this.communicationBinder.sendMessage(MessageType.DEVICE_GROUPS_REQUEST, (GeneratedMessageV3)deviceGroupsRequest1)) {
              setServiceState(ManagementServiceState.CONFIGURATION_CHANGED_DEVICE_GROUPS_REQUEST_SENT);
            } else {
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
            } 
            Intent intent = new Intent("ManagementService.Intents.newConfigurationReceived");
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
            intent.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
            this.mainThreadHandler.post(new IntentRunnable(intent));
            break;
          } 
          deviceGroupsRequest = MobilusProtocol.DeviceGroupsRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.DEVICE_GROUPS_REQUEST, (GeneratedMessageV3)deviceGroupsRequest)) {
            setServiceState(ManagementServiceState.DEVICE_GROUPS_REQUEST_SENT);
            break;
          } 
          setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          break;
        case CURRENT_STATE_REQUEST_SENT:
          loginResponse = (MobilusProtocol.LoginResponse)deviceGroupsRequest.getMessage();
          if (loginResponse.hasLoginStatus() && loginResponse.getLoginStatus() == 0) {
            if (loginResponse.hasSerialNumber())
              this.settingsService.setCentralSerialNumber(loginResponse.getSerialNumber()); 
            if (loginResponse.hasAdmin()) {
              if (loginResponse.getAdmin() != this.settingsService.isAdmin()) {
                setServiceState(ManagementServiceState.LOGIN_REQUEST_SENT);
              } else if (!this.settingsService.isAdmin()) {
                setServiceState(ManagementServiceState.LOGIN_REQUEST_SENT);
              } 
              if (loginResponse.getAdmin()) {
                this.settingsService.setAdmin(true);
              } else {
                this.settingsService.setAdmin(false);
              } 
            } 
            if (loginResponse.hasUserId()) {
              this.userId = loginResponse.getUserId();
            } else {
              this.userId = -1L;
            } 
            if (this.serviceState == ManagementServiceState.CONFIGURATION_CHANGED_LOGIN_REQUEST_SENT) {
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
              Intent intent = new Intent("ManagementService.Intents.newConfigurationReceived");
              intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevices", this.devices);
              intent.putExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState", this.devicesStates);
              intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState", this.groupsStates);
              intent.putExtra("ManagementService.Intents.newConfigurationReceivedPlaces", this.places);
              intent.putExtra("ManagementService.Intents.newConfigurationReceivedGroups", this.groups);
              intent.putExtra("ManagementService.Intents.newConfigurationReceivedScenes", this.scenes);
              intent.putExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", this.settingsService.isAdmin());
              this.mainThreadHandler.post(new IntentRunnable(intent));
              break;
            } 
            if (this.serviceState == ManagementServiceState.SESSION_EXPIRED_LOGIN_REQUEST_SENT) {
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
              this.communicationBinder.resendLastMessage();
              break;
            } 
            if (this.desiredConnectionStatus != ConnectionStatus.DEMO_MODE) {
              MobilusProtocol.DeviceSettingsRequest.Builder builder = MobilusProtocol.DeviceSettingsRequest.newBuilder();
              builder.setAction(1);
              MobilusProtocol.DeviceSettingsRequest deviceSettingsRequest = builder.build();
              if (this.communicationBinder.sendMessage(MessageType.DEVICE_SETTINGS_REQUEST, (GeneratedMessageV3)deviceSettingsRequest)) {
                setServiceState(ManagementServiceState.DEVICE_SETTINGS_REQUEST_SENT);
                break;
              } 
              setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
              break;
            } 
            this.location = new Location("");
            this.location.setLatitude(52.21875D);
            this.location.setLongitude(19.131361D);
            devicesListRequest = MobilusProtocol.DevicesListRequest.newBuilder().build();
            if (this.communicationBinder.sendMessage(MessageType.DEVICES_LIST_REQUEST, (GeneratedMessageV3)devicesListRequest)) {
              setServiceState(ManagementServiceState.DEVICES_LIST_REQUEST_SENT);
              break;
            } 
            setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
            break;
          } 
          this.communicationBinder.disconnect();
          setServiceState(ManagementServiceState.DISCONNECTED_IDLE);
          break;
      } 
      if (this.serviceState == ManagementServiceState.CONNECTED_LOGGED_IDLE || this.serviceState == ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE) {
        if (this.timeout != null) {
          this.timeout.interrupt();
          this.timeout = null;
        } 
        this.timeoutCounter = 0;
        return;
      } 
      if (this.timeout != null) {
        this.timeoutCounter = 0;
        this.timeout.extend();
      } else {
        this.timeout = new ExtendTimeout(5, this);
      } 
    } else {
      MobilusProtocol.LoginRequest loginRequest;
      if (devicesListRequest.getMessageStatus() == MessageStatus.SESSION_NOT_VALID) {
        setServiceState(ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE);
        loginRequest = MobilusProtocol.LoginRequest.newBuilder().setLogin(this.settingsService.getUserLogin()).setPassword(ByteString.copyFrom(this.settingsService.getUserPasswordHash())).build();
        if (this.communicationBinder.sendMessage(MessageType.LOGIN_REQUEST, (GeneratedMessageV3)loginRequest)) {
          setServiceState(ManagementServiceState.SESSION_EXPIRED_LOGIN_REQUEST_SENT);
          this.timeoutCounter = 0;
          if (this.timeout != null) {
            this.timeout.extend();
          } else {
            this.timeout = new ExtendTimeout(5, this);
          } 
        } else {
          setServiceState(ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE);
          if (this.timeout != null) {
            this.timeout.interrupt();
            this.timeout = null;
            this.timeoutCounter = 0;
          } 
        } 
      } else if (loginRequest.getMessageStatus() == MessageStatus.ERROR) {
        setServiceState(ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE);
        this.devices = null;
        this.devicesStates = null;
        this.places = null;
        this.groups = null;
        this.scenes = null;
        loginRequest = MobilusProtocol.LoginRequest.newBuilder().setLogin(this.settingsService.getUserLogin()).setPassword(ByteString.copyFrom(this.settingsService.getUserPasswordHash())).build();
        if (this.communicationBinder.sendMessage(MessageType.LOGIN_REQUEST, (GeneratedMessageV3)loginRequest)) {
          setServiceState(ManagementServiceState.LOGIN_REQUEST_SENT);
          this.timeoutCounter = 0;
          if (this.timeout != null) {
            this.timeout.extend();
          } else {
            this.timeout = new ExtendTimeout(5, this);
          } 
        } else {
          setServiceState(ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE);
          if (this.timeout != null) {
            this.timeout.interrupt();
            this.timeout = null;
            this.timeoutCounter = 0;
          } 
        } 
      } 
    } 
  }
  
  @Nullable
  public IBinder onBind(Intent paramIntent) {
    return this.binder;
  }
  
  public void onCreate() {
    super.onCreate();
    this.mainThreadHandler = new Handler(getMainLooper());
    this.settingsService = new SettingsService((Context)this);
    this.serviceState = ManagementServiceState.DISCONNECTED_IDLE;
    this.userId = -1L;
    this.location = null;
    bindService(new Intent((Context)this, CloudService.class), this.serviceConnection, 1);
  }
  
  public void onDestroy() {
    this.mainThreadHandler.removeCallbacksAndMessages(null);
    this.communicationBinder.disconnect();
    unbindService(this.serviceConnection);
    this.communicationBinder = null;
    super.onDestroy();
  }
  
  public boolean runSceneActions(long paramLong) {
    boolean bool = this.serviceState.isConnected();
    byte b = 0;
    if (bool && this.serviceState.isLogged()) {
      Scene scene1 = null;
      Scene scene2 = scene1;
      if (this.scenes != null) {
        scene2 = scene1;
        if (this.scenes.size() > 0) {
          Iterator<Scene> iterator = this.scenes.iterator();
          while (true) {
            scene2 = scene1;
            if (iterator.hasNext()) {
              scene2 = iterator.next();
              if (scene2.getId() == paramLong)
                break; 
              continue;
            } 
            break;
          } 
        } 
      } 
      if (scene2 == null)
        return false; 
      if ((scene2.getEvents()).length <= 0)
        return true; 
      MobilusProtocol.CallEvents.Builder builder = MobilusProtocol.CallEvents.newBuilder();
      MobilusModel.SceneEvent[] arrayOfSceneEvent = scene2.getEvents();
      int i = arrayOfSceneEvent.length;
      while (b < i) {
        MobilusModel.SceneEvent sceneEvent = arrayOfSceneEvent[b];
        builder.addEvents(MobilusModel.Event.newBuilder().setDeviceId(sceneEvent.getDeviceId()).setEventNumber(EventType.CONTROL_DEVICE.getValue()).setValue(sceneEvent.getValue()).setPlatform(Platform.ANDROID.getValue()).build());
        b++;
      } 
      return this.communicationBinder.sendMessage(MessageType.CALL_EVENTS, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  public boolean sendControlDeviceMessage(long paramLong, Action paramAction) {
    if (this.serviceState.isConnected() && this.serviceState.isLogged()) {
      MobilusModel.Event event = MobilusModel.Event.newBuilder().setDeviceId(paramLong).setEventNumber(EventType.CONTROL_DEVICE.getValue()).setValue(paramAction.toString()).setPlatform(Platform.ANDROID.getValue()).build();
      MobilusProtocol.CallEvents callEvents = MobilusProtocol.CallEvents.newBuilder().addEvents(event).build();
      return this.communicationBinder.sendMessage(MessageType.CALL_EVENTS, (GeneratedMessageV3)callEvents);
    } 
    return false;
  }
  
  public boolean sendControlGroupMessage(long paramLong, Action paramAction) {
    boolean bool = this.serviceState.isConnected();
    byte b = 0;
    if (bool && this.serviceState.isLogged()) {
      Group group1 = null;
      Group group2 = group1;
      if (this.groups != null) {
        group2 = group1;
        if (this.groups.size() > 0) {
          Iterator<Group> iterator = this.groups.iterator();
          while (true) {
            group2 = group1;
            if (iterator.hasNext()) {
              group2 = iterator.next();
              if (group2.getId() == paramLong)
                break; 
              continue;
            } 
            break;
          } 
        } 
      } 
      if (group2 == null)
        return false; 
      if ((group2.getDevices()).length <= 0)
        return true; 
      MobilusProtocol.CallEvents.Builder builder = MobilusProtocol.CallEvents.newBuilder();
      Device[] arrayOfDevice = group2.getDevices();
      int i = arrayOfDevice.length;
      while (b < i) {
        Device device = arrayOfDevice[b];
        builder.addEvents(MobilusModel.Event.newBuilder().setDeviceId(device.getId()).setEventNumber(EventType.CONTROL_DEVICE.getValue()).setValue(paramAction.toString()).setPlatform(Platform.ANDROID.getValue()).build());
        b++;
      } 
      return this.communicationBinder.sendMessage(MessageType.CALL_EVENTS, (GeneratedMessageV3)builder.build());
    } 
    return false;
  }
  
  public void timeoutOccurred() {
    this.timeoutCounter++;
    if (this.timeoutCounter > 3) {
      this.timeout = null;
      this.timeoutCounter = 0;
      if (this.serviceState == ManagementServiceState.CONNECTING) {
        setServiceState(ManagementServiceState.DISCONNECTED_IDLE);
      } else if (this.serviceState == ManagementServiceState.LOGIN_REQUEST_SENT) {
        this.communicationBinder.disconnect();
        setServiceState(ManagementServiceState.DISCONNECTED_IDLE);
      } else if (this.serviceState != ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE) {
        setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
      } 
    } else {
      MobilusProtocol.ScenesRequest scenesRequest;
      MobilusProtocol.DeviceGroupsRequest deviceGroupsRequest;
      MobilusProtocol.DevicePlacesRequest devicePlacesRequest;
      MobilusProtocol.CurrentStateRequest currentStateRequest;
      MobilusProtocol.DevicesListRequest devicesListRequest;
      switch (this.serviceState) {
        default:
          this.timeout = null;
          this.timeoutCounter = 0;
          return;
        case SCENES_REQUEST_SENT:
          scenesRequest = MobilusProtocol.ScenesRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.SCENES_REQUEST, (GeneratedMessageV3)scenesRequest)) {
            this.timeout = new ExtendTimeout(5, this);
          } else {
            setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          } 
          return;
        case DEVICE_GROUPS_REQUEST_SENT:
          deviceGroupsRequest = MobilusProtocol.DeviceGroupsRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.DEVICE_GROUPS_REQUEST, (GeneratedMessageV3)deviceGroupsRequest)) {
            this.timeout = new ExtendTimeout(5, this);
          } else {
            setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          } 
          return;
        case DEVICE_PLACES_REQUEST_SENT:
          devicePlacesRequest = MobilusProtocol.DevicePlacesRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.DEVICE_PLACES_REQUEST, (GeneratedMessageV3)devicePlacesRequest)) {
            this.timeout = new ExtendTimeout(5, this);
          } else {
            setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          } 
          return;
        case CURRENT_STATE_REQUEST_SENT:
          currentStateRequest = MobilusProtocol.CurrentStateRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.CURRENT_STATE_REQUEST, (GeneratedMessageV3)currentStateRequest)) {
            this.timeout = new ExtendTimeout(5, this);
          } else {
            setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          } 
          return;
        case DEVICES_LIST_REQUEST_SENT:
          devicesListRequest = MobilusProtocol.DevicesListRequest.newBuilder().build();
          if (this.communicationBinder.sendMessage(MessageType.DEVICES_LIST_REQUEST, (GeneratedMessageV3)devicesListRequest)) {
            this.timeout = new ExtendTimeout(5, this);
          } else {
            setServiceState(ManagementServiceState.CONNECTED_LOGGED_IDLE);
          } 
          return;
        case LOGIN_REQUEST_SENT:
          break;
      } 
      MobilusProtocol.LoginRequest.Builder builder = MobilusProtocol.LoginRequest.newBuilder();
      if (this.settingsService.getUserLogin() != null)
        builder.setLogin(this.settingsService.getUserLogin()); 
      if (this.settingsService.getUserPasswordHash() != null)
        builder.setPassword(ByteString.copyFrom(this.settingsService.getUserPasswordHash())); 
      if (this.communicationBinder.sendMessage(MessageType.LOGIN_REQUEST, (GeneratedMessageV3)builder.build())) {
        this.timeout = new ExtendTimeout(5, this);
      } else {
        setServiceState(ManagementServiceState.CONNECTED_NOT_LOGGED_IDLE);
      } 
    } 
  }
  
  private class IntentRunnable implements Runnable {
    private Intent intentToSend;
    
    public IntentRunnable(Intent param1Intent) {
      this.intentToSend = param1Intent;
    }
    
    public void run() {
      LocalBroadcastManager.getInstance((Context)ManagementService.this).sendBroadcast(this.intentToSend);
    }
  }
  
  public static class Intents {
    public static final String CONNECTION_STATUS_CHANGED = "ManagementService.Intents.connectionStatusChanged";
    
    public static final String CONNECTION_STATUS_CHANGED_CONNECTION_STATE = "ManagementService.Intents.connectionStatusChangedConnectionState";
    
    public static final String DEVICE_ACTION_FINISHED = "ManagementService.Intents.deviceActionFinished";
    
    public static final String DEVICE_ACTION_FINISHED_RESULT_DEVICE = "ManagementService.Intents.deviceActionFinishedResultDevice";
    
    public static final String DEVICE_ACTION_FINISHED_RESULT_GROUPS = "ManagementService.Intents.deviceActionFinishedResultGroups";
    
    public static final String DEVICE_ACTION_STARTED = "ManagementService.Intents.deviceActionStarted";
    
    public static final String DEVICE_ACTION_STARTED_DEVICE_STATE = "ManagementService.Intents.deviceActionStartedDeviceId";
    
    public static final String DEVICE_ACTION_STARTED_GROUPS_STATES = "ManagementService.Intents.deviceActionStartedGroupsStates";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED = "ManagementService.Intents.deviceConfigurationReceived";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_CURRENT_FIRMWARE_VERSION = "ManagementService.Intents.deviceConfigurationReceivedCurrentFirmwareVersion";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_CURRENT_TIME = "ManagementService.Intents.deviceConfigurationReceivedCurrentTime";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_EMAIL_ADDRESS = "ManagementService.Intents.deviceConfigurationReceivedEmailAddress";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_ETHERNET_IP = "ManagementService.Intents.deviceConfigurationReceivedEthernetIp";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_ETHERNET_MAC = "ManagementService.Intents.deviceConfigurationReceivedEthernetMac";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_LATITUDE = "ManagementService.Intents.deviceConfigurationReceivedLatitude";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_LONGITUDE = "ManagementService.Intents.deviceConfigurationReceivedLongitude";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_MARKETING_MATERIALS = "ManagementService.Intents.deviceConfigurationReceivedMarketingMaterials";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_NEWEST_FIRMWARE_VERSION = "ManagementService.Intents.deviceConfigurationReceivedNewestFirmwareVersion";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.deviceConfigurationReceivedOperationStatus";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_REMOTE_ACCESS = "ManagementService.Intents.deviceConfigurationReceivedRemoteAccess";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_REMOTE_ACCESS_STATE = "ManagementService.Intents.deviceConfigurationReceivedRemoteAccessState";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_SUNRISE_TIME = "ManagementService.Intents.deviceConfigurationReceivedSunriseTime";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_SUNSET_TIME = "ManagementService.Intents.deviceConfigurationReceivedSunsetTime";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_WIFI_IP = "ManagementService.Intents.deviceConfigurationReceivedWifiIp";
    
    public static final String DEVICE_CONFIGURATION_RECEIVED_WIFI_MAC = "ManagementService.Intents.deviceConfigurationReceivedWifiMac";
    
    public static final String FIRMWARE_UPDATE_PROCESS_STATE_CHANGED = "ManagementService.Intents.firmwareUpdateProcessStateChanged";
    
    public static final String FIRMWARE_UPDATE_PROCESS_STATE_CHANGED_VALUE = "ManagementService.Intents.firmwareUpdateProcessStateChangedValue";
    
    public static final String FIRMWARE_UPDATE_RESPONSE_RECEIVED = "ManagementService.Intents.firmwareUpdateResponseReceived";
    
    public static final String FIRMWARE_UPDATE_RESPONSE_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.firmwareUpdateResponseReceivedOperationStatus";
    
    public static final String NETWORK_CONFIGURATION_RECEIVED = "ManagementService.Intents.networkConfigurationReceived";
    
    public static final String NETWORK_CONFIGURATION_RECEIVED_ETHERNET_STATE = "ManagementService.Intents.networkConfigurationReceivedEthernetState";
    
    public static final String NETWORK_CONFIGURATION_RECEIVED_NET_NAME = "ManagementService.Intents.networkConfigurationReceivedNetName";
    
    public static final String NETWORK_CONFIGURATION_RECEIVED_NET_PASSWORD = "ManagementService.Intents.networkConfigurationReceivedNetPassword";
    
    public static final String NETWORK_CONFIGURATION_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.networkConfigurationReceivedOperationStatus";
    
    public static final String NETWORK_CONFIGURATION_RECEIVED_WIFI_STATE = "ManagementService.Intents.networkConfigurationReceivedWifiState";
    
    public static final String NEW_CONFIGURATION_RECEIVED = "ManagementService.Intents.newConfigurationReceived";
    
    public static final String NEW_CONFIGURATION_RECEIVED_DEVICES = "ManagementService.Intents.newConfigurationReceivedDevices";
    
    public static final String NEW_CONFIGURATION_RECEIVED_DEVICES_CURRENT_STATE = "ManagementService.Intents.newConfigurationReceivedDevicesCurrentState";
    
    public static final String NEW_CONFIGURATION_RECEIVED_GROUPS = "ManagementService.Intents.newConfigurationReceivedGroups";
    
    public static final String NEW_CONFIGURATION_RECEIVED_GROUPS_CURRENT_STATE = "ManagementService.Intents.newConfigurationReceivedGroupsCurrentState";
    
    public static final String NEW_CONFIGURATION_RECEIVED_IS_ADMIN = "ManagementService.Intents.newConfigurationReceivedIsAdmin";
    
    public static final String NEW_CONFIGURATION_RECEIVED_PLACES = "ManagementService.Intents.newConfigurationReceivedPlaces";
    
    public static final String NEW_CONFIGURATION_RECEIVED_SCENES = "ManagementService.Intents.newConfigurationReceivedScenes";
    
    public static final String PROGRAM_NEW_DEVICE_RESPONSE_RECEIVED = "ManagementService.Intents.programNewDeviceResponseReceived";
    
    public static final String PROGRAM_NEW_DEVICE_RESPONSE_RECEIVED_DEVICE_ID = "ManagementService.Intents.programNewDeviceResponseReceivedDeviceId";
    
    public static final String PROGRAM_NEW_DEVICE_RESPONSE_RECEIVED_DEVICE_TYPE = "ManagementService.Intents.programNewDeviceResponseReceivedDeviceType";
    
    public static final String REMOTE_CONNECTION_CHANGED = "ManagementService.Intents.remoteConnectionChanged";
    
    public static final String REMOTE_CONNECTION_CHANGED_VALUE = "ManagementService.Intents.remoteConnectionChangedValue";
    
    public static final String SESSION_ACTION = "ManagementService.Intents.sessionAction";
    
    public static final String SESSION_ACTION_VALUE = "ManagementService.Intents.sessionActionValue";
    
    public static final String UPDATE_DEVICE_GROUP_RESPONSE_RECEIVED = "ManagementService.Intents.updateDeviceGroupResponseReceived";
    
    public static final String UPDATE_DEVICE_GROUP_RESPONSE_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.updateDeviceGroupResponseReceivedOperationStatus";
    
    public static final String UPDATE_DEVICE_PLACE_RESPONSE_RECEIVED = "ManagementService.Intents.updateDevicePlaceResponseReceived";
    
    public static final String UPDATE_DEVICE_PLACE_RESPONSE_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.updateDevicePlaceResponseReceivedOperationStatus";
    
    public static final String UPDATE_DEVICE_RESPONSE_RECEIVED = "ManagementService.Intents.updateDeviceResponseReceived";
    
    public static final String UPDATE_DEVICE_RESPONSE_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.updateDeviceResponseReceivedOperationStatus";
    
    public static final String UPDATE_SCENE_RESPONSE_RECEIVED = "ManagementService.Intents.updateSceneResponseReceived";
    
    public static final String UPDATE_SCENE_RESPONSE_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.updateSceneResponseReceivedOperationStatus";
    
    public static final String UPDATE_USER_RESPONSE_RECEIVED = "ManagementService.Intents.updateUserResponseReceived";
    
    public static final String UPDATE_USER_RESPONSE_RECEIVED_OPERATION_STATUS = "ManagementService.Intents.updateUserResponseReceivedOperationStatus";
    
    public static final String USERS_LIST_RECEIVED = "ManagementService.Intents.usersListReceived";
    
    public static final String USERS_LIST_RECEIVED_USERS_LIST = "ManagementService.Intents.usersListReceivedUsersList";
    
    public static final String USER_LOG_STATUS_CHANGED = "ManagementService.Intents.userLogStatusChanged";
    
    public static final String USER_LOG_STATUS_CHANGED_IS_USER_LOGGED = "ManagementService.Intents.userLogStatusChangedIsUserLogged";
    
    public static final String WIFI_NETWORKS_LIST_RECEIVED = "ManagementService.Intents.wifiNetworksListReceived";
    
    public static final String WIFI_NETWORKS_LIST_RECEIVED_NETWORKS_LIST = "ManagementService.Intents.wifiNetworksListReceivedNetworksList";
  }
  
  public class ManagementServiceBinder extends Binder {
    public boolean addDeviceGroup(Group param1Group) {
      MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
      ManagementService.this.copyDeviceGroupName(param1Group, builder);
      ManagementService.this.copyDeviceGroupIcon(param1Group, builder);
      ManagementService.this.copyDeviceGroupFavourite(param1Group, builder);
      ManagementService.this.copyDeviceGroupDevices(param1Group, builder);
      ManagementService.this.copyDeviceGroupPlaces(param1Group, builder);
      return ManagementService.this.sendUpdateDeviceGroupRequest(0, builder.build());
    }
    
    public boolean addDevicePlace(Place param1Place) {
      MobilusModel.DevicePlace.Builder builder = MobilusModel.DevicePlace.newBuilder();
      ManagementService.this.copyDevicePlaceName(param1Place, builder);
      ManagementService.this.copyDevicePlaceIcon(param1Place, builder);
      ManagementService.this.copyDevicePlaceDevices(param1Place, builder);
      ManagementService.this.copyDevicePlaceGroups(param1Place, builder);
      return ManagementService.this.sendUpdateDevicePlaceRequest(0, builder.build());
    }
    
    public boolean addNewDevice(long param1Long, int param1Int) {
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      builder.setId(param1Long);
      builder.setType(param1Int);
      builder.setName(ManagementService.this.availaibleDeviceName(param1Int));
      builder.setIcon(ManagementService.this.defaultIcon(param1Int));
      return ManagementService.this.sendUpdateDeviceRequest(0, builder.build());
    }
    
    public boolean addScene(Scene param1Scene) {
      MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
      ManagementService.this.copySceneName(param1Scene, builder);
      ManagementService.this.copySceneIcon(param1Scene, builder);
      ManagementService.this.copySceneEnabled(param1Scene, builder);
      ManagementService.this.copySceneSceneEvents(param1Scene, builder);
      ManagementService.this.copySceneSceneAstralSchedule(param1Scene, builder);
      ManagementService.this.copySceneSceneWeekSchedule(param1Scene, builder);
      return ManagementService.this.sendUpdateSceneRequest(0, builder.build());
    }
    
    public boolean changeDevicesOrder(int param1Int1, int param1Int2, String param1String) {
      return ManagementService.this.changeDevicesOrder(param1Int1, param1Int2, param1String);
    }
    
    public void clearDevicesOrder() {
      ManagementService.this.clearDevicesOrder();
    }
    
    public void disconnect() {
      ManagementService.this.disconnect();
    }
    
    public boolean editDeviceFavourite(long param1Long, boolean param1Boolean) {
      Device device = ManagementService.this.findDevice(param1Long);
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      ManagementService.this.copyDeviceId(device, builder);
      ManagementService.this.copyDeviceName(device, builder);
      ManagementService.this.copyDeviceType(device, builder);
      ManagementService.this.copyDeviceIcon(device, builder);
      builder.setFavourite(param1Boolean);
      ManagementService.this.copyDeviceGroups(device, builder);
      ManagementService.this.copyDevicePlaces(device, builder);
      return ManagementService.this.sendUpdateDeviceRequest(1, builder.build());
    }
    
    public boolean editDeviceGroupDevices(long param1Long, ArrayList<Long> param1ArrayList) {
      Group group = ManagementService.this.findGroup(param1Long);
      MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
      ManagementService.this.copyDeviceGroupId(group, builder);
      ManagementService.this.copyDeviceGroupName(group, builder);
      ManagementService.this.copyDeviceGroupIcon(group, builder);
      ManagementService.this.copyDeviceGroupFavourite(group, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addAssignedDevicesIds(((Long)param1ArrayList.get(b)).longValue()); 
      ManagementService.this.copyDeviceGroupPlaces(group, builder);
      return ManagementService.this.sendUpdateDeviceGroupRequest(1, builder.build());
    }
    
    public boolean editDeviceGroupFavourite(long param1Long, boolean param1Boolean) {
      Group group = ManagementService.this.findGroup(param1Long);
      MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
      ManagementService.this.copyDeviceGroupId(group, builder);
      ManagementService.this.copyDeviceGroupName(group, builder);
      ManagementService.this.copyDeviceGroupIcon(group, builder);
      builder.setFavourite(param1Boolean);
      ManagementService.this.copyDeviceGroupDevices(group, builder);
      ManagementService.this.copyDeviceGroupPlaces(group, builder);
      return ManagementService.this.sendUpdateDeviceGroupRequest(1, builder.build());
    }
    
    public boolean editDeviceGroupIcon(long param1Long, int param1Int) {
      Group group = ManagementService.this.findGroup(param1Long);
      MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
      ManagementService.this.copyDeviceGroupId(group, builder);
      ManagementService.this.copyDeviceGroupName(group, builder);
      builder.setIcon(param1Int);
      ManagementService.this.copyDeviceGroupFavourite(group, builder);
      ManagementService.this.copyDeviceGroupDevices(group, builder);
      ManagementService.this.copyDeviceGroupPlaces(group, builder);
      return ManagementService.this.sendUpdateDeviceGroupRequest(1, builder.build());
    }
    
    public boolean editDeviceGroupName(long param1Long, String param1String) {
      Group group = ManagementService.this.findGroup(param1Long);
      MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
      ManagementService.this.copyDeviceGroupId(group, builder);
      builder.setName(param1String);
      ManagementService.this.copyDeviceGroupIcon(group, builder);
      ManagementService.this.copyDeviceGroupFavourite(group, builder);
      ManagementService.this.copyDeviceGroupDevices(group, builder);
      ManagementService.this.copyDeviceGroupPlaces(group, builder);
      return ManagementService.this.sendUpdateDeviceGroupRequest(1, builder.build());
    }
    
    public boolean editDeviceGroupPlaces(long param1Long, ArrayList<Long> param1ArrayList) {
      Group group = ManagementService.this.findGroup(param1Long);
      MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
      ManagementService.this.copyDeviceGroupId(group, builder);
      ManagementService.this.copyDeviceGroupName(group, builder);
      ManagementService.this.copyDeviceGroupIcon(group, builder);
      ManagementService.this.copyDeviceGroupFavourite(group, builder);
      ManagementService.this.copyDeviceGroupDevices(group, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addAssignedPlaceIds(((Long)param1ArrayList.get(b)).longValue()); 
      return ManagementService.this.sendUpdateDeviceGroupRequest(1, builder.build());
    }
    
    public boolean editDeviceGroups(long param1Long, ArrayList<Long> param1ArrayList) {
      Device device = ManagementService.this.findDevice(param1Long);
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      ManagementService.this.copyDeviceId(device, builder);
      ManagementService.this.copyDeviceName(device, builder);
      ManagementService.this.copyDeviceType(device, builder);
      ManagementService.this.copyDeviceIcon(device, builder);
      ManagementService.this.copyDeviceFavourite(device, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addAssignedGroupIds(((Long)param1ArrayList.get(b)).longValue()); 
      ManagementService.this.copyDevicePlaces(device, builder);
      return ManagementService.this.sendUpdateDeviceRequest(1, builder.build());
    }
    
    public boolean editDeviceIcon(long param1Long, int param1Int) {
      Device device = ManagementService.this.findDevice(param1Long);
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      ManagementService.this.copyDeviceId(device, builder);
      ManagementService.this.copyDeviceName(device, builder);
      ManagementService.this.copyDeviceType(device, builder);
      builder.setIcon(param1Int);
      ManagementService.this.copyDeviceFavourite(device, builder);
      ManagementService.this.copyDeviceGroups(device, builder);
      ManagementService.this.copyDevicePlaces(device, builder);
      return ManagementService.this.sendUpdateDeviceRequest(1, builder.build());
    }
    
    public boolean editDeviceName(long param1Long, String param1String) {
      Device device = ManagementService.this.findDevice(param1Long);
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      ManagementService.this.copyDeviceId(device, builder);
      builder.setName(param1String);
      ManagementService.this.copyDeviceType(device, builder);
      ManagementService.this.copyDeviceIcon(device, builder);
      ManagementService.this.copyDeviceFavourite(device, builder);
      ManagementService.this.copyDeviceGroups(device, builder);
      ManagementService.this.copyDevicePlaces(device, builder);
      return ManagementService.this.sendUpdateDeviceRequest(1, builder.build());
    }
    
    public boolean editDevicePlaceDevices(long param1Long, ArrayList<Long> param1ArrayList) {
      Place place = ManagementService.this.findPlace(param1Long);
      MobilusModel.DevicePlace.Builder builder = MobilusModel.DevicePlace.newBuilder();
      ManagementService.this.copyDevicePlaceId(place, builder);
      ManagementService.this.copyDevicePlaceName(place, builder);
      ManagementService.this.copyDevicePlaceIcon(place, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addAssignedDevicesIds(((Long)param1ArrayList.get(b)).longValue()); 
      ManagementService.this.copyDevicePlaceGroups(place, builder);
      return ManagementService.this.sendUpdateDevicePlaceRequest(1, builder.build());
    }
    
    public boolean editDevicePlaceGroups(long param1Long, ArrayList<Long> param1ArrayList) {
      Place place = ManagementService.this.findPlace(param1Long);
      MobilusModel.DevicePlace.Builder builder = MobilusModel.DevicePlace.newBuilder();
      ManagementService.this.copyDevicePlaceId(place, builder);
      ManagementService.this.copyDevicePlaceName(place, builder);
      ManagementService.this.copyDevicePlaceIcon(place, builder);
      ManagementService.this.copyDevicePlaceDevices(place, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addAssignedGroupIds(((Long)param1ArrayList.get(b)).longValue()); 
      return ManagementService.this.sendUpdateDevicePlaceRequest(1, builder.build());
    }
    
    public boolean editDevicePlaceName(long param1Long, String param1String) {
      Place place = ManagementService.this.findPlace(param1Long);
      MobilusModel.DevicePlace.Builder builder = MobilusModel.DevicePlace.newBuilder();
      ManagementService.this.copyDevicePlaceId(place, builder);
      builder.setName(param1String);
      ManagementService.this.copyDevicePlaceIcon(place, builder);
      ManagementService.this.copyDevicePlaceDevices(place, builder);
      ManagementService.this.copyDevicePlaceGroups(place, builder);
      return ManagementService.this.sendUpdateDevicePlaceRequest(1, builder.build());
    }
    
    public boolean editDevicePlaces(long param1Long, ArrayList<Long> param1ArrayList) {
      Device device = ManagementService.this.findDevice(param1Long);
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      ManagementService.this.copyDeviceId(device, builder);
      ManagementService.this.copyDeviceName(device, builder);
      ManagementService.this.copyDeviceType(device, builder);
      ManagementService.this.copyDeviceIcon(device, builder);
      ManagementService.this.copyDeviceFavourite(device, builder);
      ManagementService.this.copyDeviceGroups(device, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addAssignedPlaceIds(((Long)param1ArrayList.get(b)).longValue()); 
      return ManagementService.this.sendUpdateDeviceRequest(1, builder.build());
    }
    
    public boolean editSceneEnabled(long param1Long, boolean param1Boolean) {
      Scene scene = ManagementService.this.findScene(param1Long);
      MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
      ManagementService.this.copySceneId(scene, builder);
      ManagementService.this.copySceneName(scene, builder);
      ManagementService.this.copySceneIcon(scene, builder);
      builder.setEnabled(param1Boolean);
      ManagementService.this.copySceneSceneEvents(scene, builder);
      ManagementService.this.copySceneSceneAstralSchedule(scene, builder);
      ManagementService.this.copySceneSceneWeekSchedule(scene, builder);
      return ManagementService.this.sendUpdateSceneRequest(1, builder.build());
    }
    
    public boolean editSceneName(long param1Long, String param1String) {
      Scene scene = ManagementService.this.findScene(param1Long);
      MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
      ManagementService.this.copySceneId(scene, builder);
      builder.setName(param1String);
      ManagementService.this.copySceneIcon(scene, builder);
      ManagementService.this.copySceneEnabled(scene, builder);
      ManagementService.this.copySceneSceneEvents(scene, builder);
      ManagementService.this.copySceneSceneAstralSchedule(scene, builder);
      ManagementService.this.copySceneSceneWeekSchedule(scene, builder);
      return ManagementService.this.sendUpdateSceneRequest(1, builder.build());
    }
    
    public boolean editSceneSceneAstralSchedules(long param1Long, ArrayList<MobilusModel.SceneAstralSchedule> param1ArrayList) {
      Scene scene = ManagementService.this.findScene(param1Long);
      MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
      ManagementService.this.copySceneId(scene, builder);
      ManagementService.this.copySceneName(scene, builder);
      ManagementService.this.copySceneIcon(scene, builder);
      ManagementService.this.copySceneEnabled(scene, builder);
      ManagementService.this.copySceneSceneEvents(scene, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addSceneAstralSchedules(param1ArrayList.get(b)); 
      return ManagementService.this.sendUpdateSceneRequest(1, builder.build());
    }
    
    public boolean editSceneSceneEvents(long param1Long, ArrayList<MobilusModel.SceneEvent> param1ArrayList) {
      Scene scene = ManagementService.this.findScene(param1Long);
      MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
      ManagementService.this.copySceneId(scene, builder);
      ManagementService.this.copySceneName(scene, builder);
      ManagementService.this.copySceneIcon(scene, builder);
      ManagementService.this.copySceneEnabled(scene, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addSceneEvents(param1ArrayList.get(b)); 
      ManagementService.this.copySceneSceneAstralSchedule(scene, builder);
      ManagementService.this.copySceneSceneWeekSchedule(scene, builder);
      return ManagementService.this.sendUpdateSceneRequest(1, builder.build());
    }
    
    public boolean editSceneSceneWeekSchedules(long param1Long, ArrayList<MobilusModel.SceneWeekSchedule> param1ArrayList) {
      Scene scene = ManagementService.this.findScene(param1Long);
      MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
      ManagementService.this.copySceneId(scene, builder);
      ManagementService.this.copySceneName(scene, builder);
      ManagementService.this.copySceneIcon(scene, builder);
      ManagementService.this.copySceneEnabled(scene, builder);
      ManagementService.this.copySceneSceneEvents(scene, builder);
      for (byte b = 0; b < param1ArrayList.size(); b++)
        builder.addSceneWeekSchedules(param1ArrayList.get(b)); 
      return ManagementService.this.sendUpdateSceneRequest(1, builder.build());
    }
    
    public InetAddress getCentralLocalIpAddress() {
      return ManagementService.this.settingsService.getCentralIpAddress();
    }
    
    public String getCentralSerialNumber() {
      return ManagementService.this.settingsService.getCentralSerialNumber();
    }
    
    public ConnectionStatus getConnectionStatus() {
      return (ManagementService.this.communicationBinder == null) ? ConnectionStatus.NO_CONNECTION : ManagementService.this.communicationBinder.isConnected();
    }
    
    public ConnectionStatus getConnectionType() {
      return ManagementService.this.settingsService.shouldUseRemoteConnection() ? ConnectionStatus.REMOTE_CONNECTION : ConnectionStatus.LOCAL_CONNECTION;
    }
    
    @Nullable
    public HashMap<Long, State> getDeviceCurrentState() {
      return ManagementService.this.devicesStates;
    }
    
    @Nullable
    public ArrayList<Device> getDevices() {
      return ManagementService.this.devices;
    }
    
    @Nullable
    public HashMap<Long, State> getGroupCurrentState() {
      return ManagementService.this.groupsStates;
    }
    
    @Nullable
    public ArrayList<Group> getGroups() {
      return ManagementService.this.groups;
    }
    
    public String getLastFirmwareUpdateProcessState() {
      return ManagementService.this.getLastFirmwareUpdateProcessState();
    }
    
    public Location getLocation() {
      return ManagementService.this.location;
    }
    
    @Nullable
    public ArrayList<Place> getPlaces() {
      return ManagementService.this.places;
    }
    
    @Nullable
    public ArrayList<Scene> getScenes() {
      return ManagementService.this.scenes;
    }
    
    public long getUserId() {
      return ManagementService.this.getUserId();
    }
    
    public String getUserLogin() {
      return ManagementService.this.settingsService.getUserLogin();
    }
    
    public boolean hasAllDataToConnect(ConnectionStatus param1ConnectionStatus) {
      return ManagementService.this.settingsService.hasDataToConnect(param1ConnectionStatus);
    }
    
    public boolean hasCentralSerialNumber() {
      return ManagementService.this.settingsService.hasCentralSerialNumber();
    }
    
    public boolean isAdminAndLocalMode() {
      return ManagementService.this.isAdminAndLocalMode();
    }
    
    public boolean isPasswordValid(String param1String) {
      byte[] arrayOfByte = Encryption.getSha256Hash(param1String.getBytes());
      return ManagementService.this.isPasswordValid(arrayOfByte);
    }
    
    public boolean isUserAdmin() {
      return ManagementService.this.settingsService.isAdmin();
    }
    
    public boolean isUserLogged() {
      return ManagementService.this.serviceState.isLogged();
    }
    
    public boolean isWorkingInDemoMode() {
      return ManagementService.this.isWorkingInDemoMode();
    }
    
    public boolean isWorkingInLocalMode() {
      return ManagementService.this.isWorkingInLocalMode();
    }
    
    public void logOutAndRemoveAllData() {
      ManagementService.this.logOutAndRemoveAllData();
    }
    
    public void logOutAndRemovePassword() {
      ManagementService.this.logOutAndRemovePassword();
    }
    
    public boolean removeDeviceGroup(long param1Long) {
      Group group = ManagementService.this.findGroup(param1Long);
      MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
      ManagementService.this.copyDeviceGroupId(group, builder);
      return ManagementService.this.sendUpdateDeviceGroupRequest(2, builder.build());
    }
    
    public boolean removeDeviceHard(long param1Long) {
      Device device = ManagementService.this.findDevice(param1Long);
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      ManagementService.this.copyDeviceId(device, builder);
      ManagementService.this.copyDeviceName(device, builder);
      return ManagementService.this.sendUpdateDeviceRequest(3, builder.build());
    }
    
    public boolean removeDevicePlace(long param1Long) {
      Place place = ManagementService.this.findPlace(param1Long);
      MobilusModel.DevicePlace.Builder builder = MobilusModel.DevicePlace.newBuilder();
      ManagementService.this.copyDevicePlaceId(place, builder);
      return ManagementService.this.sendUpdateDevicePlaceRequest(2, builder.build());
    }
    
    public boolean removeDeviceSoft(long param1Long) {
      Device device = ManagementService.this.findDevice(param1Long);
      MobilusModel.Device.Builder builder = MobilusModel.Device.newBuilder();
      ManagementService.this.copyDeviceId(device, builder);
      ManagementService.this.copyDeviceName(device, builder);
      return ManagementService.this.sendUpdateDeviceRequest(2, builder.build());
    }
    
    public boolean removeScene(long param1Long) {
      Scene scene = ManagementService.this.findScene(param1Long);
      MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
      ManagementService.this.copySceneId(scene, builder);
      return ManagementService.this.sendUpdateSceneRequest(2, builder.build());
    }
    
    public boolean runSceneActions(long param1Long) {
      return ManagementService.this.runSceneActions(param1Long);
    }
    
    public boolean sendAddUserRequest(MobilusModel.User param1User, String param1String) {
      if (isPasswordValid(param1String)) {
        byte[] arrayOfByte = Encryption.getSha256Hash(param1String.getBytes());
        return ManagementService.this.sendUpdateUserRequest(0, param1User, arrayOfByte);
      } 
      return false;
    }
    
    public boolean sendChangePasswordRequest(String param1String1, String param1String2) {
      boolean bool = isPasswordValid(param1String1);
      byte b = 0;
      if (bool) {
        MobilusModel.User.Builder builder = MobilusModel.User.newBuilder();
        builder.setId(getUserId());
        byte[] arrayOfByte = Encryption.getSha256Hash(param1String1.getBytes());
        builder.setPassword(ByteString.copyFrom(Encryption.getSha256Hash(param1String2.getBytes())));
        if (!isUserAdmin())
          while (b < ManagementService.this.devices.size()) {
            builder.addAssignedDevicesIds(((Device)ManagementService.this.devices.get(b)).getId());
            b++;
          }  
        return ManagementService.this.sendUpdateUserRequest(1, builder.build(), arrayOfByte);
      } 
      return false;
    }
    
    public boolean sendControlDeviceMessage(long param1Long, Action param1Action) {
      return ManagementService.this.sendControlDeviceMessage(param1Long, param1Action);
    }
    
    public boolean sendControlGroupMessage(long param1Long, Action param1Action) {
      return ManagementService.this.sendControlGroupMessage(param1Long, param1Action);
    }
    
    public boolean sendEditUserRequest(MobilusModel.User param1User, String param1String) {
      if (isPasswordValid(param1String) && param1User.hasId() && (param1User.getId() != ManagementService.this.userId || !param1User.hasAdmin() || param1User.getAdmin())) {
        byte[] arrayOfByte = Encryption.getSha256Hash(param1String.getBytes());
        return ManagementService.this.sendUpdateUserRequest(1, param1User, arrayOfByte);
      } 
      return false;
    }
    
    public boolean sendPing(String param1String) {
      return ManagementService.this.sendPing(param1String);
    }
    
    public boolean sendRemoveUserRequest(MobilusModel.User param1User, String param1String) {
      if (isPasswordValid(param1String) && param1User.hasId() && param1User.getId() != ManagementService.this.userId) {
        byte[] arrayOfByte = Encryption.getSha256Hash(param1String.getBytes());
        return ManagementService.this.sendUpdateUserRequest(2, param1User, arrayOfByte);
      } 
      return false;
    }
    
    public void setConnectionOptions(String param1String1, String param1String2) {
      byte[] arrayOfByte = Encryption.getSha256Hash(param1String2.getBytes());
      ManagementService.this.setConnectionOptions(param1String1, arrayOfByte);
    }
    
    public void setConnectionOptions(InetAddress param1InetAddress, String param1String1, String param1String2) {
      byte[] arrayOfByte = Encryption.getSha256Hash(param1String2.getBytes());
      ManagementService.this.setConnectionOptions(param1InetAddress, param1String1, arrayOfByte);
    }
    
    public void setConnectionType(ConnectionStatus param1ConnectionStatus) {
      ManagementService.this.setConnectionType(param1ConnectionStatus);
    }
    
    public boolean startChangingEthernetState(boolean param1Boolean) {
      return ManagementService.this.sendNetworkSettingsRequest(param1Boolean);
    }
    
    public boolean startChangingRemoteAccessState(boolean param1Boolean) {
      return ManagementService.this.sendDeviceSettingsRequest(param1Boolean, (String)null, false);
    }
    
    public boolean startChangingRemoteAccessState(boolean param1Boolean1, String param1String, boolean param1Boolean2) {
      return ManagementService.this.sendDeviceSettingsRequest(param1Boolean1, param1String, param1Boolean2);
    }
    
    public boolean startFirmwareUpdateProcess() {
      return ManagementService.this.sendFirmwareUpdateRequest();
    }
    
    public boolean startProgrammingNewDevice() {
      return ManagementService.this.sendProgramNewDeviceRequest();
    }
    
    public boolean startReadingDeviceSettings() {
      return ManagementService.this.sendDeviceSettingsRequest(false, Double.NaN, Double.NaN, -1L);
    }
    
    public boolean startReadingDeviceSettingsCheckUpdates() {
      return ManagementService.this.sendDeviceSettingsRequestCheckUpdates();
    }
    
    public boolean startReadingNetworkSettings() {
      return ManagementService.this.sendNetworkSettingsRequest((WifiState)null, (String)null, (String)null);
    }
    
    public boolean startReadingUsersList() {
      return ManagementService.this.sendUserListRequest();
    }
    
    public boolean startReadingWifiNetworkList() {
      return ManagementService.this.sendWifiNetworkListRequest();
    }
    
    public boolean startRevokeDataProcessingConsent() {
      return ManagementService.this.sendDeviceSettingsRequest(false, "", false);
    }
    
    public boolean startWritingDeviceSettings(double param1Double1, double param1Double2, long param1Long) {
      return ManagementService.this.sendDeviceSettingsRequest(true, param1Double1, param1Double2, param1Long);
    }
    
    public boolean startWritingNetworkSettings(@NonNull WifiState param1WifiState, @Nullable String param1String1, @Nullable String param1String2) {
      return ManagementService.this.sendNetworkSettingsRequest(param1WifiState, param1String1, param1String2);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\managementservice\ManagementService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */