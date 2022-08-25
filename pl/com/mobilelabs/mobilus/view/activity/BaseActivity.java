package pl.com.mobilelabs.mobilus.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;

public abstract class BaseActivity extends AppCompatActivity implements ServiceConnection {
  protected ManagementService.ManagementServiceBinder managementBinder;
  
  protected void managementServiceConnected() {}
  
  protected void managementServiceDisconnected() {}
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    bindService(new Intent((Context)this, ManagementService.class), this, 1);
  }
  
  protected void onDestroy() {
    unbindService(this);
    super.onDestroy();
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    this.managementBinder = (ManagementService.ManagementServiceBinder)paramIBinder;
    managementServiceConnected();
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {
    this.managementBinder = null;
    managementServiceDisconnected();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\BaseActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */