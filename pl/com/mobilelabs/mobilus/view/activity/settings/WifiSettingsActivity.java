package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.WifiState;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class WifiSettingsActivity extends BackButtonHeaderActivity {
  public static final String BOLD_IP = "WifiSettingsActivity.BOLD_IP";
  
  public static final String CURRENT_IP_ADDRESS = "WifiSettingsActivity.currentIpAddress";
  
  public static final String IP_ADDRESS = "WifiSettingsActivity.ipAddress";
  
  public static final String MAC_ADDRESS = "WifiSettingsActivity.macAddress";
  
  public static final String MODE = "WifiSettingsActivity.mode";
  
  public static final String NET_NAME = "WifiSettingsActivity.netName";
  
  public static final String NET_PASSWORD = "WifiSettingsActivity.netPassword";
  
  public static final int REQUEST_CODE = 58008;
  
  public static final String SHOW_BUTTON = "WifiSettingsActivity.showButton";
  
  private static final String TAG = "WifiSetActivity";
  
  private boolean boldIp;
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
          if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !WifiSettingsActivity.this.managementBinder.isWorkingInLocalMode()) {
            WifiSettingsActivity.access$102(WifiSettingsActivity.this, false);
          } else {
            WifiSettingsActivity.access$102(WifiSettingsActivity.this, true);
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceConfigurationReceived")) {
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedEthernetIp")) {
            WifiSettingsActivity.access$202(WifiSettingsActivity.this, true);
          } else {
            WifiSettingsActivity.access$202(WifiSettingsActivity.this, false);
          } 
          WifiSettingsActivity.access$302(WifiSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedWifiIp"));
          WifiSettingsActivity.access$402(WifiSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedWifiMac"));
          WifiSettingsActivity.this.managementBinder.startReadingNetworkSettings();
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.networkConfigurationReceived")) {
          if (param1Intent.hasExtra("ManagementService.Intents.networkConfigurationReceivedWifiState")) {
            WifiSettingsActivity.access$602(WifiSettingsActivity.this, ((WifiState)param1Intent.getSerializableExtra("ManagementService.Intents.networkConfigurationReceivedWifiState")).getValue());
          } else {
            WifiSettingsActivity.access$602(WifiSettingsActivity.this, 0);
          } 
          if (param1Intent.hasExtra("ManagementService.Intents.networkConfigurationReceivedNetName")) {
            WifiSettingsActivity.access$702(WifiSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.networkConfigurationReceivedNetName"));
          } else {
            WifiSettingsActivity.access$702(WifiSettingsActivity.this, (String)null);
          } 
          if (param1Intent.hasExtra("ManagementService.Intents.networkConfigurationReceivedNetPassword")) {
            WifiSettingsActivity.access$802(WifiSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.networkConfigurationReceivedNetPassword"));
          } else {
            WifiSettingsActivity.access$802(WifiSettingsActivity.this, (String)null);
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.connectionStatusChanged")) {
          if (param1Intent.hasExtra("ManagementService.Intents.connectionStatusChangedConnectionState") && (ConnectionStatus)param1Intent.getSerializableExtra("ManagementService.Intents.connectionStatusChangedConnectionState") == ConnectionStatus.NO_CONNECTION)
            WifiSettingsActivity.this.finish(); 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.remoteConnectionChanged")) {
          WifiSettingsActivity.this.managementBinder.startReadingDeviceSettings();
        } 
        WifiSettingsActivity.this.presentData();
      }
    };
  
  private String centralIpAddress;
  
  @BindView(2131296346)
  protected TextView ipTextView;
  
  @BindView(2131296347)
  protected TextView macTextView;
  
  @BindView(2131296348)
  protected TextView modeTextView;
  
  @BindView(2131296349)
  protected TextView netNameTextView;
  
  private boolean showButton;
  
  private String wifiIp;
  
  private String wifiMac;
  
  private String wifiNetName;
  
  private String wifiPasswordName;
  
  private int wifiState;
  
  private void presentData() {
    String str = "";
    switch (this.wifiState) {
      case 2:
        str = getString(2131624220);
        break;
      case 1:
        str = getString(2131624225);
        break;
      case 0:
        str = getString(2131624224);
        break;
    } 
    this.modeTextView.setText(str);
    if (this.wifiIp != null) {
      this.ipTextView.setText(this.wifiIp);
      if (this.boldIp && this.centralIpAddress != null) {
        this.ipTextView.setTypeface(this.ipTextView.getTypeface(), this.wifiIp.equals(this.centralIpAddress));
      } else {
        this.ipTextView.setTypeface(this.ipTextView.getTypeface(), 0);
      } 
    } 
    if (this.wifiMac != null)
      this.macTextView.setText(this.wifiMac); 
    if (this.wifiNetName != null)
      this.netNameTextView.setText(this.wifiNetName); 
    LinearLayout linearLayout = (LinearLayout)findViewById(2131296345);
    if (this.showButton) {
      linearLayout.setVisibility(0);
    } else {
      linearLayout.setVisibility(8);
    } 
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 8008 && paramInt2 == -1 && paramIntent != null && paramIntent.getAction().equalsIgnoreCase("WifiChangeSettingsActivity.exitOrLogoutAction")) {
      setResult(-1, paramIntent);
      finish();
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492983);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.networkConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.connectionStatusChanged");
    intentFilter.addAction("ManagementService.Intents.remoteConnectionChanged");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("WifiSettingsActivity.mode")) {
      this.wifiState = getIntent().getIntExtra("WifiSettingsActivity.mode", 0);
    } else {
      this.wifiState = -1;
    } 
    if (getIntent().hasExtra("WifiSettingsActivity.ipAddress")) {
      this.wifiIp = getIntent().getStringExtra("WifiSettingsActivity.ipAddress");
    } else {
      this.wifiIp = null;
    } 
    if (getIntent().hasExtra("WifiSettingsActivity.currentIpAddress")) {
      this.centralIpAddress = getIntent().getStringExtra("WifiSettingsActivity.currentIpAddress");
    } else {
      this.centralIpAddress = null;
    } 
    if (getIntent().hasExtra("WifiSettingsActivity.macAddress")) {
      this.wifiMac = getIntent().getStringExtra("WifiSettingsActivity.macAddress");
    } else {
      this.wifiMac = null;
    } 
    if (getIntent().hasExtra("WifiSettingsActivity.netName")) {
      this.wifiNetName = getIntent().getStringExtra("WifiSettingsActivity.netName");
    } else {
      this.wifiNetName = null;
    } 
    if (getIntent().hasExtra("WifiSettingsActivity.netPassword")) {
      this.wifiPasswordName = getIntent().getStringExtra("WifiSettingsActivity.netPassword");
    } else {
      this.wifiPasswordName = null;
    } 
    if (getIntent().hasExtra("WifiSettingsActivity.showButton")) {
      this.showButton = getIntent().getBooleanExtra("WifiSettingsActivity.showButton", false);
    } else {
      this.showButton = false;
    } 
    if (getIntent().hasExtra("WifiSettingsActivity.BOLD_IP")) {
      this.boldIp = getIntent().getBooleanExtra("WifiSettingsActivity.BOLD_IP", false);
    } else {
      this.boldIp = false;
    } 
    presentData();
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  @OnClick({2131296345})
  protected void wifiConfigChangeClicked() {
    Intent intent = new Intent((Context)this, WifiChangeSettingsActivity.class);
    intent.putExtra("WifiChangeSettingsActivity.mode", this.wifiState);
    if (this.wifiIp != null)
      intent.putExtra("WifiChangeSettingsActivity.ipAddress", this.wifiIp); 
    if (this.centralIpAddress != null)
      intent.putExtra("WifiChangeSettingsActivity.currentIpAddress", this.centralIpAddress); 
    if (this.wifiMac != null)
      intent.putExtra("WifiChangeSettingsActivity.macAddress", this.wifiMac); 
    if (this.wifiNetName != null)
      intent.putExtra("WifiChangeSettingsActivity.netName", this.wifiNetName); 
    if (this.wifiPasswordName != null)
      intent.putExtra("WifiChangeSettingsActivity.netPassword", this.wifiPasswordName); 
    intent.putExtra("WifiChangeSettingsActivity.showButton", true);
    intent.putExtra("WifiChangeSettingsActivity.BOLD_IP", this.boldIp);
    startActivityForResult(intent, 8008);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\WifiSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */