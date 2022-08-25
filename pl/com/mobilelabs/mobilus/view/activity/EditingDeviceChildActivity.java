package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.AssignToGroupEditDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.AssignToPlaceEditDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.ChangeNameEditDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.ChooseIconEditDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.ProgrammingModeRemovingDeviceFragment;

public class EditingDeviceChildActivity extends UltimateHeaderActivity implements DoSthWithDeviceInterface {
  public static final String DEVICE_ID = "EditingDeviceChildActivity.deviceId";
  
  public static final String FRAGMENT_RESOURCE = "EditingDeviceChildActivity.fragmentResource";
  
  public static final String GROUP_FRAGMENT = "EditingDeviceChildActivity.groupFragment";
  
  public static final int REQUEST_CODE = 7;
  
  protected long newDeviceID = -1L;
  
  public long getNewDeviceID() {
    return this.newDeviceID;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    boolean bool1 = false;
    setResult(0);
    if (getIntent().hasExtra("EditingDeviceChildActivity.deviceId"))
      this.newDeviceID = getIntent().getLongExtra("EditingDeviceChildActivity.deviceId", -1L); 
    boolean bool2 = getIntent().hasExtra("EditingDeviceChildActivity.fragmentResource");
    int i = -1;
    if (bool2)
      i = getIntent().getIntExtra("EditingDeviceChildActivity.fragmentResource", -1); 
    if (getIntent().hasExtra("EditingDeviceChildActivity.groupFragment"))
      bool1 = getIntent().getBooleanExtra("EditingDeviceChildActivity.groupFragment", false); 
    switch (i) {
      default:
        return;
      case 2131492953:
        initializeActivity(2131492953, (DoSthDeviceFragment)new ProgrammingModeRemovingDeviceFragment());
        configureView(2131558672, 2131624158, 2131624049, 2131099704, 2131558462);
      case 2131492905:
        initializeActivity(2131492905, (DoSthDeviceFragment)new ChooseIconEditDeviceFragment());
        configureView(2131558521, 2131624063, 2131624049, 2131099698, 2131558473);
      case 2131492904:
        initializeActivity(2131492904, (DoSthDeviceFragment)new ChangeNameEditDeviceFragment());
        configureView(2131558521, 2131624063, 2131624049, 2131099698, 2131558473);
      case 2131492901:
        break;
    } 
    if (bool1) {
      initializeActivity(2131492901, (DoSthDeviceFragment)new AssignToGroupEditDeviceFragment());
      configureView(2131558521, 2131624063, 2131624049, 2131099698, 2131558473);
    } 
    initializeActivity(2131492901, (DoSthDeviceFragment)new AssignToPlaceEditDeviceFragment());
    configureView(2131558521, 2131624063, 2131624049, 2131099698, 2131558473);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\EditingDeviceChildActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */