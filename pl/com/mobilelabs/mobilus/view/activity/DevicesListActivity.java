package pl.com.mobilelabs.mobilus.view.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.Serializable;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.GroupsPlacesDetailsListFragment;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;
import pl.com.mobilelabs.mobilus.view.view.ConnectionStatusView;

public class DevicesListActivity extends BaseActivity implements IDevicesGroupsPlacesScenesListFragmentContext {
  private static final String OBJECT_TO_PRESENT = "pl.com.mobilelabs.mobilus.view.activity.DevicesListActivity.objectToPresent";
  
  @BindView(2131296375)
  protected ConnectionStatusView connectionStatus;
  
  @BindView(2131296376)
  protected TextView demoText;
  
  protected GroupsPlacesDetailsListFragment groupsDevicesFragment;
  
  @BindView(2131296686)
  protected TextView nameText;
  
  private BaseObject presentedObject;
  
  private BroadcastReceiver receiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.hasExtra("ManagementService.Intents.connectionStatusChangedConnectionState")) {
          ConnectionStatus connectionStatus = (ConnectionStatus)param1Intent.getSerializableExtra("ManagementService.Intents.connectionStatusChangedConnectionState");
          DevicesListActivity.this.connectionStatus.bind(connectionStatus);
          if (connectionStatus == ConnectionStatus.DEMO_MODE)
            DevicesListActivity.this.demoText.setVisibility(0); 
        } 
      }
    };
  
  @BindView(2131296826)
  protected ImageView typeImage;
  
  public static Intent createStartIntent(Context paramContext, BaseObject paramBaseObject) {
    Intent intent = new Intent(paramContext, DevicesListActivity.class);
    intent.putExtra("pl.com.mobilelabs.mobilus.view.activity.DevicesListActivity.objectToPresent", (Serializable)paramBaseObject);
    return intent;
  }
  
  @OnClick({2131296468})
  protected void back() {
    finish();
  }
  
  public ManagementService.ManagementServiceBinder getManagementServiceBinder() {
    return this.managementBinder;
  }
  
  protected void managementServiceConnected() {
    ConnectionStatus connectionStatus = this.managementBinder.getConnectionStatus();
    this.connectionStatus.bind(connectionStatus);
    if (connectionStatus == ConnectionStatus.DEMO_MODE)
      this.demoText.setVisibility(0); 
    this.groupsDevicesFragment.managementBinderBecameAvailable();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492897);
    ButterKnife.bind((Activity)this);
    if (getIntent().hasExtra("pl.com.mobilelabs.mobilus.view.activity.DevicesListActivity.objectToPresent")) {
      this.presentedObject = (BaseObject)getIntent().getSerializableExtra("pl.com.mobilelabs.mobilus.view.activity.DevicesListActivity.objectToPresent");
      this.nameText.setText(this.presentedObject.getName());
      if (this.presentedObject instanceof pl.com.mobilelabs.mobilus.model.objects.Group) {
        this.typeImage.setImageResource(2131558566);
      } else {
        this.typeImage.setImageResource(2131558656);
      } 
      this.groupsDevicesFragment = new GroupsPlacesDetailsListFragment();
      this.groupsDevicesFragment.setPresentedObject(this.presentedObject);
      getSupportFragmentManager().beginTransaction().replace(2131296595, (Fragment)this.groupsDevicesFragment).commit();
    } 
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.connectionStatusChanged");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.receiver, intentFilter);
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.receiver);
    super.onDestroy();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\DevicesListActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */