package pl.com.mobilelabs.mobilus.view.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.PrintStream;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.settings.SettingsActivity;
import pl.com.mobilelabs.mobilus.view.adapter.DashboardPagerAdapter;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;
import pl.com.mobilelabs.mobilus.view.view.ConnectionStatusView;

public class DashboardActivity extends BaseActivity implements IDevicesGroupsPlacesScenesListFragmentContext {
  private static final int WAIT_FOR_DATA_MS = 15000;
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.connectionStatusChanged")) {
          ConnectionStatus connectionStatus = (ConnectionStatus)param1Intent.getSerializableExtra("ManagementService.Intents.connectionStatusChangedConnectionState");
          DashboardActivity.this.connectionStatusLayout.bind(connectionStatus);
          if (connectionStatus == ConnectionStatus.DEMO_MODE) {
            DashboardActivity.this.shopImage.setVisibility(8);
            DashboardActivity.this.settingsImage.setVisibility(8);
            DashboardActivity.this.demoText.setVisibility(0);
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.sessionAction")) {
          String str = param1Intent.getStringExtra("ManagementService.Intents.sessionActionValue");
          if (DashboardActivity.this.managementBinder != null && str != null) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Pong: ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
            DashboardActivity.this.managementBinder.sendPing(str);
          } 
        } else {
          DashboardActivity.this.progressBar.setVisibility(8);
          if (DashboardActivity.this.closeActivityHandler != null) {
            DashboardActivity.this.closeActivityHandler.removeCallbacksAndMessages(null);
            DashboardActivity.access$002(DashboardActivity.this, (Handler)null);
          } 
        } 
      }
    };
  
  private Handler closeActivityHandler;
  
  @BindView(2131296371)
  protected ConnectionStatusView connectionStatusLayout;
  
  @BindView(2131296372)
  protected TextView demoText;
  
  private DashboardPagerAdapter pagerAdapter;
  
  @BindView(2131296535)
  protected ProgressBar progressBar;
  
  @BindView(2131296373)
  protected LinearLayout settingsImage;
  
  @BindView(2131296374)
  protected LinearLayout shopImage;
  
  @BindView(2131296797)
  protected TabLayout tabLayout;
  
  @BindView(2131296816)
  protected Toolbar toolBar;
  
  @BindView(2131296817)
  protected TextView toolbarTitleTextView;
  
  @BindView(2131296840)
  protected ViewPager viewPager;
  
  private void prepareTabs() {
    this.pagerAdapter = new DashboardPagerAdapter(getSupportFragmentManager());
    this.viewPager.setAdapter((PagerAdapter)this.pagerAdapter);
    this.tabLayout.setupWithViewPager(this.viewPager);
    this.tabLayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener)new TabLayout.OnTabSelectedListener() {
          public void onTabReselected(TabLayout.Tab param1Tab) {
            DashboardActivity.this.toolbarTitleTextView.setText(DashboardActivity.this.pagerAdapter.getTabTitleId(param1Tab.getPosition()));
          }
          
          public void onTabSelected(TabLayout.Tab param1Tab) {
            DashboardActivity.this.toolbarTitleTextView.setText(DashboardActivity.this.pagerAdapter.getTabTitleId(param1Tab.getPosition()));
          }
          
          public void onTabUnselected(TabLayout.Tab param1Tab) {}
        });
    for (byte b = 0; b < this.tabLayout.getTabCount(); b++)
      this.tabLayout.getTabAt(b).setCustomView(this.pagerAdapter.getTabView(b, (Context)this)); 
    this.tabLayout.getTabAt(0).select();
  }
  
  private void showLoginActivity() {
    showLoginActivity(false);
  }
  
  private void showLoginActivity(boolean paramBoolean) {
    if (this.managementBinder != null)
      if (paramBoolean) {
        this.managementBinder.logOutAndRemovePassword();
      } else {
        this.managementBinder.disconnect();
      }  
    startActivity(new Intent((Context)this, LoginActivity.class));
    finish();
  }
  
  private void showLoginActivityAndLogout() {
    showLoginActivity(true);
  }
  
  public ManagementService.ManagementServiceBinder getManagementServiceBinder() {
    return this.managementBinder;
  }
  
  protected void managementServiceConnected() {
    this.pagerAdapter.managementBinderBecameAvailable();
    if (!this.managementBinder.isWorkingInDemoMode() && !this.managementBinder.hasAllDataToConnect(ConnectionStatus.LOCAL_CONNECTION) && !this.managementBinder.hasAllDataToConnect(ConnectionStatus.REMOTE_CONNECTION)) {
      showLoginActivity();
    } else if (this.managementBinder.getScenes() != null && this.closeActivityHandler != null) {
      this.progressBar.setVisibility(8);
      this.closeActivityHandler.removeCallbacksAndMessages(null);
      this.closeActivityHandler = null;
    } 
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 861 && paramInt2 == -1) {
      showLoginActivityAndLogout();
    } else if (paramInt1 == 1337 && paramInt2 == -1 && paramIntent != null && paramIntent.getAction().equalsIgnoreCase("WifiChangeSettingsActivity.exitOrLogoutAction")) {
      showLoginActivityAndLogout();
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492893);
    ButterKnife.bind((Activity)this);
    setSupportActionBar(this.toolBar);
    prepareTabs();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("ManagementService.Intents.connectionStatusChanged");
    intentFilter.addAction("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.sessionAction");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    this.closeActivityHandler = new Handler();
    this.closeActivityHandler.postDelayed(new Runnable() {
          public void run() {
            DashboardActivity.this.showLoginActivity();
          }
        },  15000L);
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    if (this.closeActivityHandler != null)
      this.closeActivityHandler.removeCallbacksAndMessages(null); 
    super.onDestroy();
  }
  
  @OnClick({2131296371})
  protected void showGoToLoginScreen() {
    startActivityForResult(new Intent((Context)this, LoginInfoActivity.class), 861);
  }
  
  @OnClick({2131296373})
  protected void showSettings() {
    startActivityForResult(new Intent((Context)this, SettingsActivity.class), 1337);
  }
  
  @OnClick({2131296374})
  protected void showShop() {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://sklep.mobilus.pl")));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\DashboardActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */