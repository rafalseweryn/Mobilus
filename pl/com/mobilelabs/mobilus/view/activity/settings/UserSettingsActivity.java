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
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class UserSettingsActivity extends BackButtonHeaderActivity {
  public static final String MANAGE_USERS = "UserSettingsActivity.manageUsers";
  
  public static final String USERNAME = "UserSettingsActivity.username";
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !UserSettingsActivity.this.managementBinder.isWorkingInLocalMode()) {
          UserSettingsActivity.this.changeButtonTextView.setText(2131624014);
          return;
        } 
        UserSettingsActivity.this.changeButtonTextView.setText(2131624119);
      }
    };
  
  @BindView(2131296328)
  protected LinearLayout changeButton;
  
  @BindView(2131296329)
  protected TextView changeButtonTextView;
  
  @BindView(2131296330)
  protected TextView usernameTextView;
  
  @OnClick({2131296328})
  protected void changeButtonClicked() {
    if (this.changeButtonTextView.getText().toString().equalsIgnoreCase(getString(2131624014))) {
      Intent intent = new Intent((Context)this, UserPasswordSettingsActivity.class);
      String str = this.managementBinder.getUserLogin();
      if (str != null)
        intent.putExtra("UserPasswordSettingsActivity.username", str); 
      startActivity(intent);
    } else if (this.changeButtonTextView.getText().toString().equalsIgnoreCase(getString(2131624119))) {
      startActivity(new Intent((Context)this, UserListSettingsActivity.class));
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492970);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("UserSettingsActivity.username"))
      this.usernameTextView.setText(getIntent().getStringExtra("UserSettingsActivity.username")); 
    if (getIntent().hasExtra("UserSettingsActivity.manageUsers"))
      if (getIntent().getBooleanExtra("UserSettingsActivity.manageUsers", false)) {
        this.changeButtonTextView.setText(2131624119);
      } else {
        this.changeButtonTextView.setText(2131624014);
      }  
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */