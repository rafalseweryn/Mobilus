package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TableRow;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.Date;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.RemoteAccessState;
import pl.com.mobilelabs.mobilus.model.WifiState;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class SettingsActivity extends BackButtonHeaderActivity {
  public static final int REQUEST_CODE = 1337;
  
  private static String TAG = "SettingsActivity";
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
          if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !SettingsActivity.this.managementBinder.isWorkingInLocalMode()) {
            ((TableRow)SettingsActivity.this.findViewById(2131296759)).setVisibility(8);
            ((TableRow)SettingsActivity.this.findViewById(2131296760)).setVisibility(8);
            return;
          } 
          ((TableRow)SettingsActivity.this.findViewById(2131296759)).setVisibility(0);
          ((TableRow)SettingsActivity.this.findViewById(2131296760)).setVisibility(0);
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceConfigurationReceived")) {
          SettingsActivity.access$102(SettingsActivity.this, param1Intent.getDoubleExtra("ManagementService.Intents.deviceConfigurationReceivedLatitude", 0.0D));
          SettingsActivity.access$202(SettingsActivity.this, param1Intent.getDoubleExtra("ManagementService.Intents.deviceConfigurationReceivedLongitude", 0.0D));
          SettingsActivity.access$302(SettingsActivity.this, param1Intent.getBooleanExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccess", false));
          SettingsActivity.access$402(SettingsActivity.this, param1Intent.getLongExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentTime", 0L));
          SettingsActivity.access$502(SettingsActivity.this, (new Date()).getTime() / 1000L);
          SettingsActivity.access$602(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedEthernetIp"));
          SettingsActivity.access$702(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedWifiIp"));
          SettingsActivity.access$802(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedEthernetMac"));
          SettingsActivity.access$902(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedWifiMac"));
          SettingsActivity.access$1002(SettingsActivity.this, param1Intent.getLongExtra("ManagementService.Intents.deviceConfigurationReceivedSunriseTime", 0L));
          SettingsActivity.access$1102(SettingsActivity.this, param1Intent.getLongExtra("ManagementService.Intents.deviceConfigurationReceivedSunsetTime", 0L));
          SettingsActivity.access$1202(SettingsActivity.this, (RemoteAccessState)param1Intent.getSerializableExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccessState"));
          SettingsActivity.access$1302(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentFirmwareVersion"));
          SettingsActivity.access$1402(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedNewestFirmwareVersion"));
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedEmailAddress"))
            SettingsActivity.access$1502(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedEmailAddress")); 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedMarketingMaterials"))
            SettingsActivity.access$1602(SettingsActivity.this, param1Intent.getBooleanExtra("ManagementService.Intents.deviceConfigurationReceivedMarketingMaterials", false)); 
          SettingsActivity.this.managementBinder.startReadingNetworkSettings();
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.networkConfigurationReceived")) {
          SettingsActivity.access$1802(SettingsActivity.this, param1Intent.getBooleanExtra("ManagementService.Intents.networkConfigurationReceivedEthernetState", false));
          SettingsActivity.access$1902(SettingsActivity.this, (WifiState)param1Intent.getSerializableExtra("ManagementService.Intents.networkConfigurationReceivedWifiState"));
          SettingsActivity.access$2002(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.networkConfigurationReceivedNetName"));
          SettingsActivity.access$2102(SettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.networkConfigurationReceivedNetPassword"));
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.connectionStatusChanged")) {
          if (param1Intent.hasExtra("ManagementService.Intents.connectionStatusChangedConnectionState") && (ConnectionStatus)param1Intent.getSerializableExtra("ManagementService.Intents.connectionStatusChangedConnectionState") == ConnectionStatus.NO_CONNECTION)
            SettingsActivity.this.finish(); 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.remoteConnectionChanged")) {
          SettingsActivity.this.managementBinder.startReadingDeviceSettings();
        } 
      }
    };
  
  private String currentFirmwareVersion;
  
  private long currentTime;
  
  private long currentTimeReceiveTime;
  
  private String emailAddress;
  
  private String ethernetIp;
  
  private String ethernetMac;
  
  private boolean ethernetState;
  
  private String latestFirmwareVersion;
  
  private double latitude;
  
  private double longitude;
  
  private boolean marketingMaterials;
  
  private boolean remoteAccess;
  
  private RemoteAccessState remoteAccessState;
  
  private long sunriseTime;
  
  private long sunsetTime;
  
  private String wifiIp;
  
  private String wifiMac;
  
  private String wifiNetName;
  
  private String wifiPasswordName;
  
  private WifiState wifiState;
  
  @OnClick({2131296758})
  protected void ethernetSelected() {
    boolean bool2;
    boolean bool3;
    Intent intent = new Intent((Context)this, EthernetSettingsActivity.class);
    intent.putExtra("EthernetSettingsActivity.state", this.ethernetState);
    if (this.ethernetIp != null)
      intent.putExtra("EthernetSettingsActivity.ipAddress", this.ethernetIp); 
    if (this.managementBinder.getCentralLocalIpAddress() != null)
      intent.putExtra("EthernetSettingsActivity.currentIpAddress", this.managementBinder.getCentralLocalIpAddress().getHostAddress()); 
    String str = this.ethernetIp;
    boolean bool1 = true;
    if (str != null && this.managementBinder.getCentralLocalIpAddress() != null && this.ethernetIp.compareTo(this.managementBinder.getCentralLocalIpAddress().getHostAddress()) == 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (this.ethernetMac != null)
      intent.putExtra("EthernetSettingsActivity.macAddress", this.ethernetMac); 
    if (this.managementBinder.isAdminAndLocalMode() && !bool2) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    intent.putExtra("EthernetSettingsActivity.switchActive", bool3);
    if (this.wifiIp != null) {
      bool3 = bool1;
    } else {
      bool3 = false;
    } 
    intent.putExtra("EthernetSettingsActivity.BOLD_IP", bool3);
    startActivity(intent);
  }
  
  protected void managementServiceConnected() {
    super.managementServiceConnected();
    this.managementBinder.startReadingDeviceSettings();
    TableRow tableRow1 = (TableRow)findViewById(2131296759);
    TableRow tableRow2 = (TableRow)findViewById(2131296760);
    if (this.managementBinder.isAdminAndLocalMode()) {
      tableRow1.setVisibility(0);
      tableRow2.setVisibility(0);
    } else {
      tableRow1.setVisibility(8);
      tableRow2.setVisibility(8);
    } 
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 58008 && paramInt2 == -1 && paramIntent != null && paramIntent.getAction().equalsIgnoreCase("WifiChangeSettingsActivity.exitOrLogoutAction")) {
      setResult(-1, paramIntent);
      finish();
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setResult(0);
    initializeActivity(2131492958);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.networkConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.connectionStatusChanged");
    intentFilter.addAction("ManagementService.Intents.remoteConnectionChanged");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  @OnClick({2131296759})
  protected void remoteAccessSelected() {
    Intent intent = new Intent((Context)this, RemoteAccessSettingsActivity.class);
    intent.putExtra("RemoteAccessSettingsActivity.state", this.remoteAccess);
    if (this.remoteAccessState != null)
      intent.putExtra("RemoteAccessSettingsActivity.connectionState", this.remoteAccessState.getValue()); 
    if (this.emailAddress != null)
      intent.putExtra("RemoteAccessSettingsActivity.emailAddress", this.emailAddress); 
    intent.putExtra("RemoteAccessSettingsActivity.marketingMaterials", this.marketingMaterials);
    startActivity(intent);
  }
  
  @OnClick({2131296761})
  protected void systemSelected() {
    Intent intent = new Intent((Context)this, SystemSettingsActivity.class);
    String str = this.managementBinder.getCentralSerialNumber();
    if (str != null)
      intent.putExtra("SystemSettingsActivity.serialNumber", str); 
    if (this.currentFirmwareVersion != null)
      intent.putExtra("SystemSettingsActivity.currentVersionNumber", this.currentFirmwareVersion); 
    if (this.latestFirmwareVersion != null)
      intent.putExtra("SystemSettingsActivity.latestVersionNumber", this.latestFirmwareVersion); 
    str = this.managementBinder.getLastFirmwareUpdateProcessState();
    if (str != null)
      intent.putExtra("SystemSettingsActivity.lastFirmwareUpdateProcessState", str); 
    intent.putExtra("SystemSettingsActivity.showButton", this.managementBinder.isAdminAndLocalMode());
    startActivity(intent);
  }
  
  @OnClick({2131296762})
  protected void timeLocalizationSelected() {
    Intent intent = new Intent((Context)this, SpacetimeSettingsActivity.class);
    intent.putExtra("SpacetimeSettingsActivity.currentTime", this.currentTime);
    intent.putExtra("SpacetimeSettingsActivity.currentTimeReceiveTime", this.currentTimeReceiveTime);
    intent.putExtra("SpacetimeSettingsActivity.latitude", this.latitude);
    intent.putExtra("SpacetimeSettingsActivity.longitude", this.longitude);
    String str = this.managementBinder.getCentralSerialNumber();
    if (str != null)
      intent.putExtra("SpacetimeSettingsActivity.serialNumber", str); 
    intent.putExtra("SpacetimeSettingsActivity.showButton", this.managementBinder.isAdminAndLocalMode());
    startActivity(intent);
  }
  
  @OnClick({2131296763})
  protected void userSelected() {
    Intent intent = new Intent((Context)this, UserSettingsActivity.class);
    String str = this.managementBinder.getUserLogin();
    if (str != null)
      intent.putExtra("UserSettingsActivity.username", str); 
    intent.putExtra("UserSettingsActivity.manageUsers", this.managementBinder.isAdminAndLocalMode());
    startActivity(intent);
  }
  
  @OnClick({2131296764})
  protected void wifiSelected() {
    boolean bool;
    Intent intent = new Intent((Context)this, WifiSettingsActivity.class);
    if (this.wifiState != null)
      intent.putExtra("WifiSettingsActivity.mode", this.wifiState.getValue()); 
    if (this.wifiIp != null)
      intent.putExtra("WifiSettingsActivity.ipAddress", this.wifiIp); 
    if (this.managementBinder.getCentralSerialNumber() != null)
      intent.putExtra("WifiSettingsActivity.currentIpAddress", this.managementBinder.getCentralLocalIpAddress().getHostAddress()); 
    if (this.wifiMac != null)
      intent.putExtra("WifiSettingsActivity.macAddress", this.wifiMac); 
    if (this.wifiNetName != null)
      intent.putExtra("WifiSettingsActivity.netName", this.wifiNetName); 
    if (this.wifiPasswordName != null)
      intent.putExtra("WifiSettingsActivity.netPassword", this.wifiPasswordName); 
    intent.putExtra("WifiSettingsActivity.showButton", this.managementBinder.isAdminAndLocalMode());
    if (this.ethernetIp != null) {
      bool = true;
    } else {
      bool = false;
    } 
    intent.putExtra("WifiSettingsActivity.BOLD_IP", bool);
    startActivityForResult(intent, 58008);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */