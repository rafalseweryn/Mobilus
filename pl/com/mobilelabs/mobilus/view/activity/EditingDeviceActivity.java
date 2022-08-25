package pl.com.mobilelabs.mobilus.view.activity;

import android.content.Intent;
import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.MenuEditDeviceFragment;

public class EditingDeviceActivity extends UltimateHeaderActivity implements DoSthWithDeviceInterface {
  public static final String DEVICE_ID = "EditingDeviceActivity.deviceId";
  
  protected long newDeviceID = -1L;
  
  public long getNewDeviceID() {
    return this.newDeviceID;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 7 && paramInt2 == -1)
      changeView(2131492931, (DoSthDeviceFragment)new MenuEditDeviceFragment()); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setResult(0);
    if (getIntent().hasExtra("EditingDeviceActivity.deviceId"))
      this.newDeviceID = getIntent().getLongExtra("EditingDeviceActivity.deviceId", -1L); 
    initializeActivity(2131492931, (DoSthDeviceFragment)new MenuEditDeviceFragment());
    configureView(2131558521, 2131624063, 2131624049, 2131099698, 2131558473);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\EditingDeviceActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */