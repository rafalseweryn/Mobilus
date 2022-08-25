package pl.com.mobilelabs.mobilus.services.settingsservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Base64;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;

public class SettingsService {
  private static final String ADMIN_KEY = "admin";
  
  private static final String CENTRAL_IP_ADDRESS_KEY = "centralIpAddressKey";
  
  private static final String DEVICES_ORDER_LIST = "devicesOrderList";
  
  private static final String REMOTE_CONNECTION_KEY = "remoteConnectionKey";
  
  private static final String SERIAL_NUMBER_KEY = "serialNumber";
  
  private static final String SHARED_PREFERENCES_NAME = "pl.com.mobilelabs.mobiluscommunication.services.settingsservice.sharedPreferencesName";
  
  private static final String USER_LOGIN_KEY = "userLogin";
  
  private static final String USER_PASSWORD_HASH_KEY = "userPasswordHash";
  
  private boolean admin;
  
  private ArrayList<String> allDevicesOrder;
  
  private InetAddress centralIpAddress;
  
  private String centralSerialNumber;
  
  private LinkedList<DeviceOrder> devicesOrder;
  
  private ArrayList<String> otherDevicesOrder;
  
  private SharedPreferences preferences;
  
  private boolean remoteConnection;
  
  private String userLogin;
  
  private byte[] userPasswordHash;
  
  public SettingsService(Context paramContext) {
    byte b = 0;
    this.preferences = paramContext.getSharedPreferences("pl.com.mobilelabs.mobiluscommunication.services.settingsservice.sharedPreferencesName", 0);
    this.userLogin = this.preferences.getString("userLogin", null);
    this.admin = this.preferences.getBoolean("admin", false);
    String str = this.preferences.getString("userPasswordHash", null);
    if (str != null)
      try {
        this.userPasswordHash = Base64.decode(str, 0);
      } catch (IllegalArgumentException illegalArgumentException) {
        this.userPasswordHash = null;
      }  
    str = this.preferences.getString("centralIpAddressKey", null);
    if (str != null)
      try {
        this.centralIpAddress = Inet4Address.getByName(str);
      } catch (UnknownHostException unknownHostException) {
        this.centralIpAddress = null;
      }  
    this.centralSerialNumber = this.preferences.getString("serialNumber", null);
    this.remoteConnection = this.preferences.getBoolean("remoteConnectionKey", false);
    this.devicesOrder = new LinkedList<>();
    this.otherDevicesOrder = new ArrayList<>();
    this.allDevicesOrder = new ArrayList<>();
    str = this.preferences.getString("devicesOrderList", null);
    if (str != null) {
      System.out.println(str);
      String[] arrayOfString = str.split(",");
      while (b < arrayOfString.length) {
        this.allDevicesOrder.add(arrayOfString[b]);
        b++;
      } 
    } 
  }
  
  private void saveDevicesOrder(@NonNull String paramString, long paramLong) {
    StringBuilder stringBuilder1 = new StringBuilder();
    Iterator<String> iterator = this.otherDevicesOrder.iterator();
    int i = 0;
    int j = 0;
    while (iterator.hasNext()) {
      stringBuilder1.append(iterator.next());
      int k = j + 1;
      j = k;
      if (k < this.otherDevicesOrder.size()) {
        stringBuilder1.append(",");
        j = k;
      } 
    } 
    String str3 = stringBuilder1.toString();
    stringBuilder1 = new StringBuilder();
    j = i;
    while (j < this.devicesOrder.size()) {
      stringBuilder1.append(paramString);
      stringBuilder1.append(":");
      stringBuilder1.append(paramLong);
      stringBuilder1.append(":");
      stringBuilder1.append(j);
      stringBuilder1.append(":");
      stringBuilder1.append(((DeviceOrder)this.devicesOrder.get(j)).getDeviceId());
      stringBuilder1.append(":");
      stringBuilder1.append(((DeviceOrder)this.devicesOrder.get(j)).getOffset());
      i = j + 1;
      j = i;
      if (i < this.devicesOrder.size()) {
        stringBuilder1.append(",");
        j = i;
      } 
    } 
    String str2 = stringBuilder1.toString();
    paramString = str3;
    if (!str3.isEmpty()) {
      paramString = str3;
      if (!str2.isEmpty()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        stringBuilder.append(",");
        str1 = stringBuilder.toString();
      } 
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str1);
    stringBuilder2.append(str2);
    String str1 = stringBuilder2.toString();
    System.out.println(str1);
    SharedPreferences.Editor editor = this.preferences.edit();
    editor.putString("devicesOrderList", str1);
    editor.apply();
  }
  
  public LinkedList<DeviceOrder> addDeviceOrder(@NonNull String paramString, long paramLong1, @NonNull LinkedList<DeviceOrder> paramLinkedList, long paramLong2, int paramInt) {
    if (this.devicesOrder == null)
      this.devicesOrder = new LinkedList<>(); 
    this.devicesOrder.clear();
    this.devicesOrder.addAll(paramLinkedList);
    this.devicesOrder.add(new DeviceOrder(paramLong2, paramInt));
    saveDevicesOrder(paramString, paramLong1);
    return new LinkedList<>(this.devicesOrder);
  }
  
  public void clearAllSettings() {
    SharedPreferences.Editor editor = this.preferences.edit();
    editor.remove("userLogin");
    editor.remove("userPasswordHash");
    editor.remove("admin");
    editor.remove("centralIpAddressKey");
    editor.remove("serialNumber");
    editor.remove("remoteConnectionKey");
    editor.commit();
    this.userLogin = null;
    this.userPasswordHash = null;
    this.admin = false;
    this.centralIpAddress = null;
    this.centralSerialNumber = null;
    this.remoteConnection = false;
  }
  
  public void clearPasswordHash() {
    SharedPreferences.Editor editor = this.preferences.edit();
    editor.remove("userPasswordHash");
    this.userPasswordHash = null;
    editor.apply();
  }
  
  public InetAddress getCentralIpAddress() {
    return this.centralIpAddress;
  }
  
  public String getCentralSerialNumber() {
    return this.centralSerialNumber;
  }
  
  public LinkedList<DeviceOrder> getDevicesOrder() {
    return (this.devicesOrder != null) ? new LinkedList<>(this.devicesOrder) : null;
  }
  
  public String getUserLogin() {
    return this.userLogin;
  }
  
  public byte[] getUserPasswordHash() {
    return this.userPasswordHash;
  }
  
  public boolean hasCentralSerialNumber() {
    boolean bool;
    if (this.centralSerialNumber != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasDataToConnect(ConnectionStatus paramConnectionStatus) {
    boolean bool;
    if (paramConnectionStatus == ConnectionStatus.DEMO_MODE)
      return true; 
    if (paramConnectionStatus == ConnectionStatus.REMOTE_CONNECTION) {
      bool = true;
    } else {
      bool = false;
    } 
    return (bool && this.centralSerialNumber == null) ? false : (!(this.userLogin == null || this.userPasswordHash == null || this.centralIpAddress == null));
  }
  
  public boolean isAdmin() {
    return this.admin;
  }
  
  public void processDeviceOrder(@NonNull String paramString, long paramLong) {
    this.allDevicesOrder.clear();
    String str = this.preferences.getString("devicesOrderList", null);
    byte b = 0;
    if (str != null) {
      String[] arrayOfString = str.split(",");
      for (byte b1 = 0; b1 < arrayOfString.length; b1++)
        this.allDevicesOrder.add(arrayOfString[b1]); 
    } 
    str = paramString.replace("-", "").toUpperCase();
    if (this.allDevicesOrder != null && this.allDevicesOrder.size() > 0) {
      HashMap<Object, Object> hashMap = new HashMap<>();
      if (this.otherDevicesOrder == null) {
        this.otherDevicesOrder = new ArrayList<>();
      } else {
        this.otherDevicesOrder.clear();
      } 
      if (this.devicesOrder == null) {
        this.devicesOrder = new LinkedList<>();
      } else {
        this.devicesOrder.clear();
      } 
      for (String str1 : this.allDevicesOrder) {
        String[] arrayOfString = str1.split(":");
        if (arrayOfString.length == 5)
          try {
            String str2 = arrayOfString[0].replace("-", "").toUpperCase();
            long l1 = Long.parseLong(arrayOfString[1]);
            int j = Integer.parseInt(arrayOfString[2]);
            long l2 = Long.parseLong(arrayOfString[3]);
            int i = Integer.parseInt(arrayOfString[4]);
            if (str2.equalsIgnoreCase(str) && l1 == paramLong) {
              if (i != 0) {
                DeviceOrder deviceOrder = new DeviceOrder();
                this(this, l2, i);
                hashMap.put(Integer.valueOf(j), deviceOrder);
              } 
              continue;
            } 
            this.otherDevicesOrder.add(str1);
          } catch (Exception exception) {
            exception.printStackTrace();
          }  
      } 
      this.allDevicesOrder.clear();
      Integer[] arrayOfInteger = (Integer[])hashMap.keySet().toArray((Object[])new Integer[hashMap.size()]);
      Arrays.sort((Object[])arrayOfInteger);
      for (byte b1 = b; b1 < hashMap.size(); b1++)
        this.devicesOrder.add((DeviceOrder)hashMap.get(arrayOfInteger[b1])); 
    } 
    saveDevicesOrder(str, paramLong);
  }
  
  public void setActualOffset(int paramInt1, int paramInt2) {
    ((DeviceOrder)this.devicesOrder.get(paramInt1)).setActualOffset(paramInt2);
    System.out.println(String.format("setActualOffset(%d, %d)\ndevicesOrder = %s", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), this.devicesOrder.toString() }));
  }
  
  public void setAdmin(boolean paramBoolean) {
    if (paramBoolean != this.admin) {
      SharedPreferences.Editor editor = this.preferences.edit();
      editor.putBoolean("admin", paramBoolean);
      editor.apply();
      this.admin = paramBoolean;
    } 
  }
  
  public void setCentralIpAddress(InetAddress paramInetAddress) {
    SharedPreferences.Editor editor = this.preferences.edit();
    if (paramInetAddress == null) {
      editor.remove("centralIpAddressKey");
    } else {
      editor.putString("centralIpAddressKey", paramInetAddress.getHostAddress());
    } 
    editor.apply();
    this.centralIpAddress = paramInetAddress;
  }
  
  public void setCentralSerialNumber(String paramString) {
    SharedPreferences.Editor editor = this.preferences.edit();
    if (paramString == null) {
      editor.remove("serialNumber");
    } else {
      editor.putString("serialNumber", paramString);
    } 
    editor.apply();
    this.centralSerialNumber = paramString;
  }
  
  public LinkedList<DeviceOrder> setDevicesOrder(@NonNull String paramString, long paramLong, @NonNull LinkedList<DeviceOrder> paramLinkedList) {
    if (this.devicesOrder == null)
      this.devicesOrder = new LinkedList<>(); 
    this.devicesOrder.clear();
    this.devicesOrder.addAll(paramLinkedList);
    saveDevicesOrder(paramString, paramLong);
    return new LinkedList<>(this.devicesOrder);
  }
  
  public void setShouldUseRemoteConnection(boolean paramBoolean) {
    SharedPreferences.Editor editor = this.preferences.edit();
    editor.putBoolean("remoteConnectionKey", paramBoolean);
    editor.apply();
    this.remoteConnection = paramBoolean;
  }
  
  public void setUserLogin(String paramString) {
    SharedPreferences.Editor editor = this.preferences.edit();
    if (paramString == null) {
      editor.remove("userLogin");
    } else {
      editor.putString("userLogin", paramString);
    } 
    editor.apply();
    this.userLogin = paramString;
  }
  
  public void setUserPasswordHash(byte[] paramArrayOfbyte) {
    SharedPreferences.Editor editor = this.preferences.edit();
    if (paramArrayOfbyte == null) {
      editor.remove("userPasswordHash");
    } else {
      editor.putString("userPasswordHash", Base64.encodeToString(paramArrayOfbyte, 0));
    } 
    editor.apply();
    this.userPasswordHash = paramArrayOfbyte;
  }
  
  public boolean shouldUseRemoteConnection() {
    return this.remoteConnection;
  }
  
  public class DeviceOrder {
    private int actualOffset;
    
    private boolean actualOffsetUsed;
    
    private long deviceId;
    
    private int offset;
    
    private int previousIndex;
    
    public DeviceOrder(long param1Long, int param1Int) {
      this.deviceId = param1Long;
      this.offset = param1Int;
      this.actualOffset = 0;
      this.actualOffsetUsed = false;
      this.previousIndex = -1;
    }
    
    public DeviceOrder(long param1Long, int param1Int1, int param1Int2) {
      this.deviceId = param1Long;
      this.offset = param1Int1;
      this.actualOffset = param1Int2;
      this.actualOffsetUsed = true;
      this.previousIndex = -1;
    }
    
    public int getActualOffset() {
      return this.actualOffsetUsed ? this.actualOffset : this.offset;
    }
    
    public long getDeviceId() {
      return this.deviceId;
    }
    
    public int getOffset() {
      return this.offset;
    }
    
    public int getPreviousIndex() {
      return this.previousIndex;
    }
    
    public void setActualOffset(int param1Int) {
      this.actualOffset = param1Int;
      this.actualOffsetUsed = true;
    }
    
    public void setOffset(int param1Int) {
      this.offset = param1Int;
      this.actualOffsetUsed = false;
    }
    
    public void setPreviousIndex(int param1Int) {
      this.previousIndex = param1Int;
    }
    
    public String toString() {
      return (!this.actualOffsetUsed && this.previousIndex == -1) ? String.format("{deviceId = %d, offset = %d}", new Object[] { Long.valueOf(this.deviceId), Integer.valueOf(this.offset) }) : ((this.actualOffsetUsed && this.previousIndex == -1) ? String.format("{deviceId = %d, offset = %d, actualOffset = %d}", new Object[] { Long.valueOf(this.deviceId), Integer.valueOf(this.offset), Integer.valueOf(this.actualOffset) }) : ((!this.actualOffsetUsed && this.previousIndex != -1) ? String.format("{deviceId = %d, offset = %d, previousIndex = %d}", new Object[] { Long.valueOf(this.deviceId), Integer.valueOf(this.offset), Integer.valueOf(this.previousIndex) }) : String.format("{deviceId = %d, offset = %d, actualOffset = %d, previousIndex = %d}", new Object[] { Long.valueOf(this.deviceId), Integer.valueOf(this.offset), Integer.valueOf(this.actualOffset), Integer.valueOf(this.previousIndex) })));
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\settingsservice\SettingsService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */