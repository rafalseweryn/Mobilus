package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.RemoteAccessState;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class RemoteAccessSettingsActivity extends BackButtonHeaderActivity {
  public static final String CONNECTION_STATE = "RemoteAccessSettingsActivity.connectionState";
  
  public static final String EMAIL_ADDRESS = "RemoteAccessSettingsActivity.emailAddress";
  
  public static final String MARKETING_MATERIALS = "RemoteAccessSettingsActivity.marketingMaterials";
  
  public static final String STATE = "RemoteAccessSettingsActivity.state";
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
          if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !RemoteAccessSettingsActivity.this.managementBinder.isWorkingInLocalMode())
            RemoteAccessSettingsActivity.this.finish(); 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceConfigurationReceived")) {
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccess"))
            RemoteAccessSettingsActivity.access$102(RemoteAccessSettingsActivity.this, param1Intent.getBooleanExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccess", false)); 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccessState")) {
            RemoteAccessState remoteAccessState = (RemoteAccessState)param1Intent.getSerializableExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccessState");
            RemoteAccessSettingsActivity.access$202(RemoteAccessSettingsActivity.this, remoteAccessState.getValue());
          } 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedEmailAddress"))
            RemoteAccessSettingsActivity.access$302(RemoteAccessSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.deviceConfigurationReceivedEmailAddress")); 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedMarketingMaterials"))
            RemoteAccessSettingsActivity.access$402(RemoteAccessSettingsActivity.this, param1Intent.getBooleanExtra("ManagementService.Intents.deviceConfigurationReceivedMarketingMaterials", false)); 
          if (RemoteAccessSettingsActivity.this.sentNewValues && param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedOperationStatus")) {
            RemoteAccessSettingsActivity.access$502(RemoteAccessSettingsActivity.this, false);
            switch (param1Intent.getIntExtra("ManagementService.Intents.deviceConfigurationReceivedOperationStatus", 0)) {
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
            if (!param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedRemoteAccess"))
              RemoteAccessSettingsActivity.this.managementBinder.startReadingDeviceSettings(); 
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.remoteConnectionChanged")) {
          if (param1Intent.hasExtra("ManagementService.Intents.remoteConnectionChangedValue"))
            RemoteAccessSettingsActivity.access$202(RemoteAccessSettingsActivity.this, param1Intent.getStringExtra("ManagementService.Intents.remoteConnectionChangedValue")); 
          RemoteAccessSettingsActivity.this.managementBinder.startReadingDeviceSettings();
        } 
        RemoteAccessSettingsActivity.this.presentData();
      }
    };
  
  @BindView(2131296264)
  protected TextView connectionStateTextView;
  
  @BindView(2131296265)
  protected AppCompatCheckBox consentCheckBox;
  
  private String emailAddress;
  
  private boolean marketingMaterials;
  
  @BindView(2131296266)
  protected LinearLayout policyButton;
  
  private boolean remoteAccess;
  
  private String remoteAccessState;
  
  private boolean sentNewValues;
  
  @BindView(2131296267)
  protected SwitchCompat stateSwitch;
  
  private void presentData() {
    this.stateSwitch.setChecked(this.remoteAccess);
    if (this.remoteAccessState != null) {
      if (this.remoteAccessState.equalsIgnoreCase("OFF")) {
        this.connectionStateTextView.setText(2131624060);
      } else if (this.remoteAccessState.equalsIgnoreCase("ON_DISCONNECTED")) {
        this.connectionStateTextView.setText(2131624072);
      } else if (this.remoteAccessState.equalsIgnoreCase("ON_CONNECTED")) {
        this.connectionStateTextView.setText(2131624071);
      } else {
        this.connectionStateTextView.setText(this.remoteAccessState);
      } 
    } else {
      this.connectionStateTextView.setText("");
    } 
    if (this.emailAddress != null) {
      this.consentCheckBox.setChecked(this.emailAddress.isEmpty() ^ true);
    } else {
      this.consentCheckBox.setChecked(false);
    } 
  }
  
  private void showConsentAlertDialog(Context paramContext) {
    AlertDialog.Builder builder = new AlertDialog.Builder(paramContext);
    View view = getLayoutInflater().inflate(2131492898, null);
    LinearLayout linearLayout1 = (LinearLayout)view.findViewById(2131296475);
    final LinearLayout saveButton = (LinearLayout)view.findViewById(2131296728);
    final AppCompatCheckBox dataProcessingCheckBox = (AppCompatCheckBox)view.findViewById(2131296537);
    final AppCompatCheckBox marketingMaterialsCheckBox = (AppCompatCheckBox)view.findViewById(2131296651);
    linearLayout2.setEnabled(false);
    final TextInputEditText emailAddressField = (TextInputEditText)view.findViewById(2131296567);
    textInputEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            boolean bool;
            LinearLayout linearLayout = saveButton;
            if (!emailAddressField.getText().toString().isEmpty() && dataProcessingCheckBox.isChecked()) {
              bool = true;
            } else {
              bool = false;
            } 
            linearLayout.setEnabled(bool);
          }
        });
    appCompatCheckBox1.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            boolean bool;
            LinearLayout linearLayout = saveButton;
            if (!emailAddressField.getText().toString().isEmpty() && dataProcessingCheckBox.isChecked()) {
              bool = true;
            } else {
              bool = false;
            } 
            linearLayout.setEnabled(bool);
          }
        });
    builder.setView(view);
    final AlertDialog dialog = builder.create();
    linearLayout1.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            if (RemoteAccessSettingsActivity.this.stateSwitch.isChecked())
              RemoteAccessSettingsActivity.this.stateSwitch.setChecked(false); 
            if (RemoteAccessSettingsActivity.this.consentCheckBox.isChecked())
              RemoteAccessSettingsActivity.this.consentCheckBox.setChecked(false); 
            dialog.dismiss();
          }
        });
    linearLayout2.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            RemoteAccessSettingsActivity.access$502(RemoteAccessSettingsActivity.this, RemoteAccessSettingsActivity.this.managementBinder.startChangingRemoteAccessState(RemoteAccessSettingsActivity.this.stateSwitch.isChecked(), emailAddressField.getText().toString(), marketingMaterialsCheckBox.isChecked()));
            RemoteAccessSettingsActivity.access$302(RemoteAccessSettingsActivity.this, emailAddressField.getText().toString());
            dialog.dismiss();
          }
        });
    alertDialog.show();
  }
  
  @OnClick({2131296265})
  protected void dataProcessingCheckboxClicked() {
    if (this.consentCheckBox.isChecked()) {
      showConsentAlertDialog((Context)this);
    } else {
      this.managementBinder.startRevokeDataProcessingConsent();
      this.emailAddress = "";
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492954);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    this.sentNewValues = false;
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.remoteConnectionChanged");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("RemoteAccessSettingsActivity.state")) {
      this.remoteAccess = getIntent().getBooleanExtra("RemoteAccessSettingsActivity.state", false);
    } else {
      this.remoteAccess = false;
    } 
    if (getIntent().hasExtra("RemoteAccessSettingsActivity.connectionState")) {
      this.remoteAccessState = getIntent().getStringExtra("RemoteAccessSettingsActivity.connectionState");
    } else {
      this.remoteAccessState = null;
    } 
    if (getIntent().hasExtra("RemoteAccessSettingsActivity.emailAddress")) {
      this.emailAddress = getIntent().getStringExtra("RemoteAccessSettingsActivity.emailAddress");
    } else {
      this.emailAddress = null;
    } 
    if (getIntent().hasExtra("RemoteAccessSettingsActivity.marketingMaterials")) {
      this.marketingMaterials = getIntent().getBooleanExtra("RemoteAccessSettingsActivity.marketingMaterials", false);
    } else {
      this.marketingMaterials = false;
    } 
    presentData();
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  @OnClick({2131296266})
  protected void policyButtonClicked() {
    try {
      Intent intent = new Intent();
      this("android.intent.action.VIEW", Uri.parse(getString(2131624153)));
      startActivity(intent);
    } catch (ActivityNotFoundException activityNotFoundException) {
      Toast.makeText((Context)this, 2131624136, 1).show();
      activityNotFoundException.printStackTrace();
    } 
  }
  
  @OnClick({2131296267})
  protected void remoteAccessStateSwitchClicked() {
    this.remoteAccess = this.stateSwitch.isChecked();
    if (this.emailAddress != null && !this.emailAddress.isEmpty()) {
      this.sentNewValues = this.managementBinder.startChangingRemoteAccessState(this.stateSwitch.isChecked());
    } else {
      showConsentAlertDialog((Context)this);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\RemoteAccessSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */