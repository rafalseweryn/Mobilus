package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class SystemSettingsActivity extends BackButtonHeaderActivity {
  public static final String CURRENT_VERSION_NUMBER = "SystemSettingsActivity.currentVersionNumber";
  
  public static final String LAST_FIRMWARE_UPDATE_PROCESS_STATE = "SystemSettingsActivity.lastFirmwareUpdateProcessState";
  
  public static final String LATEST_VERSION_NUMBER = "SystemSettingsActivity.latestVersionNumber";
  
  public static final String SERIAL_NUMBER = "SystemSettingsActivity.serialNumber";
  
  public static final String SHOW_BUTTON = "SystemSettingsActivity.showButton";
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.networkConfigurationReceived")) {
          if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !SystemSettingsActivity.this.managementBinder.isWorkingInLocalMode()) {
            SystemSettingsActivity.access$102(SystemSettingsActivity.this, false);
          } else {
            SystemSettingsActivity.access$102(SystemSettingsActivity.this, true);
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceConfigurationReceived")) {
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentFirmwareVersion")) {
            SystemSettingsActivity.access$202(SystemSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentFirmwareVersion"));
          } else {
            SystemSettingsActivity.access$202(SystemSettingsActivity.this, (String)null);
          } 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedNewestFirmwareVersion")) {
            SystemSettingsActivity.access$302(SystemSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedNewestFirmwareVersion"));
          } else {
            SystemSettingsActivity.access$302(SystemSettingsActivity.this, (String)null);
          } 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedOperationStatus")) {
            int i = param1Intent.getIntExtra("ManagementService.Intents.deviceConfigurationReceivedOperationStatus", 0);
            if (SystemSettingsActivity.this.sentNewValues) {
              SystemSettingsActivity.access$402(SystemSettingsActivity.this, false);
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
            } 
            if (i == 0 && !param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentTime"))
              SystemSettingsActivity.this.managementBinder.startReadingDeviceSettings(); 
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.firmwareUpdateResponseReceived")) {
          if (SystemSettingsActivity.this.sentNewValues && param1Intent.hasExtra("ManagementService.Intents.firmwareUpdateResponseReceivedOperationStatus")) {
            SystemSettingsActivity.access$402(SystemSettingsActivity.this, false);
            switch (param1Intent.getIntExtra("ManagementService.Intents.firmwareUpdateResponseReceivedOperationStatus", 0)) {
              case 2:
                Toast.makeText(param1Context, 2131624139, 0).show();
                break;
              case 1:
                Toast.makeText(param1Context, 2131624204, 0).show();
                break;
              case 0:
                Toast.makeText(param1Context, 2131624141, 0).show();
                break;
            } 
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.firmwareUpdateProcessStateChanged") && param1Intent.hasExtra("ManagementService.Intents.firmwareUpdateProcessStateChangedValue")) {
          String str = param1Intent.getStringExtra("ManagementService.Intents.firmwareUpdateProcessStateChangedValue");
          if (str != null) {
            SystemSettingsActivity.access$602(SystemSettingsActivity.this, str);
            if (str.equalsIgnoreCase("START")) {
              Toast.makeText(param1Context, 2131624090, 0).show();
            } else if (str.equalsIgnoreCase("FINISH")) {
              Toast.makeText(param1Context, 2131624089, 0).show();
              SystemSettingsActivity.this.managementBinder.startReadingDeviceSettings();
            } else if (str.equalsIgnoreCase("ERROR")) {
              Toast.makeText(param1Context, 2131624088, 0).show();
              SystemSettingsActivity.this.managementBinder.startReadingDeviceSettingsCheckUpdates();
            } else {
              Toast.makeText(param1Context, 2131624091, 0).show();
              SystemSettingsActivity.this.managementBinder.startReadingDeviceSettingsCheckUpdates();
            } 
          } 
        } 
        SystemSettingsActivity.this.presentData();
      }
    };
  
  private String currentFirmwareVersion;
  
  @BindView(2131296291)
  protected TextView currentFirmwareVersionTextView;
  
  private String latestFirmwareUpdateProcessState;
  
  private String latestFirmwareVersion;
  
  @BindView(2131296292)
  protected TextView latestFirmwareVersionTextView;
  
  @BindView(2131296293)
  protected ContentLoadingProgressBar progressBar;
  
  private boolean sentNewValues;
  
  private String serialNumber;
  
  @BindView(2131296294)
  protected TextView serialNumberTextView;
  
  private boolean showButton;
  
  @BindView(2131296295)
  protected LinearLayout updatesButton;
  
  @BindView(2131296296)
  protected TextView updatesButtonTextView;
  
  @OnClick({2131296295})
  protected void changeButtonClicked() {
    if (this.updatesButtonTextView.getText().toString().equalsIgnoreCase(getString(2131624018))) {
      this.sentNewValues = this.managementBinder.startReadingDeviceSettingsCheckUpdates();
    } else if (this.latestFirmwareUpdateProcessState == null || this.latestFirmwareUpdateProcessState.equalsIgnoreCase("START")) {
      this.sentNewValues = this.managementBinder.startFirmwareUpdateProcess();
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492964);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.firmwareUpdateResponseReceived");
    intentFilter.addAction("ManagementService.Intents.firmwareUpdateProcessStateChanged");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("SystemSettingsActivity.serialNumber")) {
      this.serialNumber = getIntent().getStringExtra("SystemSettingsActivity.serialNumber");
    } else {
      this.serialNumber = null;
    } 
    if (getIntent().hasExtra("SystemSettingsActivity.currentVersionNumber")) {
      this.currentFirmwareVersion = getIntent().getStringExtra("SystemSettingsActivity.currentVersionNumber");
    } else {
      this.currentFirmwareVersion = null;
    } 
    if (getIntent().hasExtra("SystemSettingsActivity.latestVersionNumber")) {
      this.latestFirmwareVersion = getIntent().getStringExtra("SystemSettingsActivity.latestVersionNumber");
    } else {
      this.latestFirmwareVersion = null;
    } 
    if (getIntent().hasExtra("SystemSettingsActivity.showButton")) {
      this.showButton = getIntent().getBooleanExtra("SystemSettingsActivity.showButton", false);
    } else {
      this.showButton = false;
    } 
    if (getIntent().hasExtra("SystemSettingsActivity.lastFirmwareUpdateProcessState")) {
      this.latestFirmwareUpdateProcessState = getIntent().getStringExtra("SystemSettingsActivity.lastFirmwareUpdateProcessState");
    } else {
      this.latestFirmwareUpdateProcessState = null;
    } 
    presentData();
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  void presentData() {
    if (this.serialNumber != null) {
      this.serialNumberTextView.setText(this.serialNumber);
    } else {
      this.serialNumberTextView.setText("");
    } 
    if (this.currentFirmwareVersion != null) {
      this.currentFirmwareVersionTextView.setText(this.currentFirmwareVersion);
    } else {
      this.currentFirmwareVersionTextView.setText("");
    } 
    if (this.latestFirmwareVersion != null) {
      this.latestFirmwareVersionTextView.setText(this.latestFirmwareVersion);
    } else {
      this.latestFirmwareVersionTextView.setText("");
    } 
    if (this.currentFirmwareVersion != null && this.latestFirmwareVersion != null) {
      if (this.currentFirmwareVersion.equals(this.latestFirmwareVersion)) {
        this.updatesButtonTextView.setText(2131624018);
        this.latestFirmwareUpdateProcessState = null;
      } else {
        this.updatesButtonTextView.setText(2131624205);
      } 
    } else {
      this.showButton = false;
    } 
    if (this.latestFirmwareUpdateProcessState != null && this.latestFirmwareUpdateProcessState.equalsIgnoreCase("START")) {
      this.progressBar.setVisibility(0);
    } else {
      this.progressBar.setVisibility(8);
    } 
    if (this.showButton) {
      this.updatesButton.setVisibility(0);
    } else {
      this.updatesButton.setVisibility(8);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SystemSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */