package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class EthernetSettingsActivity extends BackButtonHeaderActivity {
  public static final String BOLD_IP = "EthernetSettingsActivity.BOLD_IP";
  
  public static final String CURRENT_IP_ADDRESS = "EthernetSettingsActivity.currentIpAddress";
  
  public static final String IP_ADDRESS = "EthernetSettingsActivity.ipAddress";
  
  public static final String MAC_ADDRESS = "EthernetSettingsActivity.macAddress";
  
  public static final String STATE = "EthernetSettingsActivity.state";
  
  public static final String SWITCH_ACTIVE = "EthernetSettingsActivity.switchActive";
  
  private boolean boldIp;
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
          boolean bool1;
          boolean bool = EthernetSettingsActivity.this.getIntent().hasExtra("EthernetSettingsActivity.ipAddress");
          String str = null;
          if (bool) {
            String str1 = EthernetSettingsActivity.this.getIntent().getStringExtra("EthernetSettingsActivity.ipAddress");
          } else {
            param1Context = null;
          } 
          if (EthernetSettingsActivity.this.getIntent().hasExtra("EthernetSettingsActivity.currentIpAddress"))
            str = EthernetSettingsActivity.this.getIntent().getStringExtra("EthernetSettingsActivity.currentIpAddress"); 
          if (param1Context != null && str != null && param1Context.compareTo(str) == 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !EthernetSettingsActivity.this.managementBinder.isWorkingInLocalMode() || bool1) {
            EthernetSettingsActivity.this.stateSwitch.setEnabled(false);
          } else {
            EthernetSettingsActivity.this.stateSwitch.setEnabled(true);
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceConfigurationReceived")) {
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedWifiIp")) {
            EthernetSettingsActivity.access$102(EthernetSettingsActivity.this, true);
          } else {
            EthernetSettingsActivity.access$102(EthernetSettingsActivity.this, false);
          } 
          EthernetSettingsActivity.access$202(EthernetSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedEthernetIp"));
          EthernetSettingsActivity.access$302(EthernetSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedEthernetMac"));
          EthernetSettingsActivity.this.managementBinder.startReadingNetworkSettings();
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.networkConfigurationReceived")) {
          int i = param1Intent.getIntExtra("ManagementService.Intents.networkConfigurationReceivedOperationStatus", 0);
          if (param1Intent.hasExtra("ManagementService.Intents.networkConfigurationReceivedEthernetState"))
            EthernetSettingsActivity.access$502(EthernetSettingsActivity.this, param1Intent.getBooleanExtra("ManagementService.Intents.networkConfigurationReceivedEthernetState", false)); 
          if (EthernetSettingsActivity.this.sentNewValues && param1Intent.hasExtra("ManagementService.Intents.networkConfigurationReceivedOperationStatus")) {
            EthernetSettingsActivity.access$602(EthernetSettingsActivity.this, false);
            switch (i) {
              case 2:
                Toast.makeText(param1Context, 2131624139, 0).show();
                break;
              case 1:
                Toast.makeText(param1Context, 2131624103, 0).show();
                break;
              case 0:
                Toast.makeText(param1Context, 2131624141, 0).show();
                break;
            } 
            if (!param1Intent.hasExtra("ManagementService.Intents.networkConfigurationReceivedEthernetState"))
              EthernetSettingsActivity.this.managementBinder.startReadingDeviceSettings(); 
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.remoteConnectionChanged")) {
          EthernetSettingsActivity.this.managementBinder.startReadingDeviceSettings();
        } 
        EthernetSettingsActivity.this.presentData();
      }
    };
  
  private String centralIpAddress;
  
  private String ethernetIp;
  
  private String ethernetMac;
  
  private boolean ethernetState;
  
  @BindView(2131296258)
  protected TextView ipTextView;
  
  @BindView(2131296259)
  protected TextView macTextView;
  
  private boolean sentNewValues;
  
  @BindView(2131296260)
  protected SwitchCompat stateSwitch;
  
  private boolean switchActive;
  
  private void presentData() {
    this.stateSwitch.setChecked(this.ethernetState);
    if (this.ethernetIp != null) {
      this.ipTextView.setText(this.ethernetIp);
      if (this.boldIp && this.centralIpAddress != null) {
        this.ipTextView.setTypeface(this.ipTextView.getTypeface(), this.ethernetIp.equals(this.centralIpAddress));
      } else {
        this.ipTextView.setTypeface(this.ipTextView.getTypeface(), 0);
      } 
    } else {
      this.ipTextView.setText("");
    } 
    if (this.ethernetMac != null) {
      this.macTextView.setText(this.ethernetMac);
    } else {
      this.macTextView.setText("");
    } 
    this.stateSwitch.setEnabled(this.switchActive);
  }
  
  @OnClick({2131296260})
  protected void ethernetStateSwitchClicked() {
    this.ethernetState = this.stateSwitch.isChecked();
    this.sentNewValues = this.managementBinder.startChangingEthernetState(this.stateSwitch.isChecked());
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492926);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    this.sentNewValues = false;
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.networkConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.remoteConnectionChanged");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("EthernetSettingsActivity.state")) {
      this.ethernetState = getIntent().getBooleanExtra("EthernetSettingsActivity.state", false);
    } else {
      this.ethernetState = false;
    } 
    if (getIntent().hasExtra("EthernetSettingsActivity.ipAddress")) {
      this.ethernetIp = getIntent().getStringExtra("EthernetSettingsActivity.ipAddress");
    } else {
      this.ethernetIp = null;
    } 
    if (getIntent().hasExtra("EthernetSettingsActivity.currentIpAddress")) {
      this.centralIpAddress = getIntent().getStringExtra("EthernetSettingsActivity.currentIpAddress");
    } else {
      this.centralIpAddress = null;
    } 
    if (getIntent().hasExtra("EthernetSettingsActivity.macAddress")) {
      this.ethernetMac = getIntent().getStringExtra("EthernetSettingsActivity.macAddress");
    } else {
      this.ethernetMac = null;
    } 
    if (getIntent().hasExtra("EthernetSettingsActivity.switchActive")) {
      this.switchActive = getIntent().getBooleanExtra("EthernetSettingsActivity.switchActive", false);
    } else {
      this.switchActive = false;
    } 
    if (getIntent().hasExtra("EthernetSettingsActivity.BOLD_IP")) {
      this.boldIp = getIntent().getBooleanExtra("EthernetSettingsActivity.BOLD_IP", false);
    } else {
      this.boldIp = false;
    } 
    presentData();
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\EthernetSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */