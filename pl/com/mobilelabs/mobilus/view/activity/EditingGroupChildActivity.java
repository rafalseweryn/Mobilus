package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editinggroup.AssignDeviceEditGroupFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editinggroup.AssignToPlaceEditGroupFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editinggroup.ChangeNameEditGroupFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editinggroup.ChooseIconEditGroupFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editinggroup.CountdownRemovingEditGroupFragment;

public class EditingGroupChildActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  public static final String DEVICE_FRAGMENT = "EditingGroupChildActivity.deviceFragment";
  
  public static final String FRAGMENT_RESOURCE = "EditingGroupChildActivity.fragmentResource";
  
  public static final String GROUP_ID = "EditingGroupChildActivity.groupId";
  
  public static final int REQUEST_CODE = 8;
  
  protected long newSomethingID = -1L;
  
  public long getNewSomethingID() {
    return this.newSomethingID;
  }
  
  public int getType() {
    return 1;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    boolean bool1 = false;
    setResult(0);
    if (getIntent().hasExtra("EditingGroupChildActivity.groupId"))
      this.newSomethingID = getIntent().getLongExtra("EditingGroupChildActivity.groupId", -1L); 
    boolean bool2 = getIntent().hasExtra("EditingGroupChildActivity.fragmentResource");
    int i = -1;
    if (bool2)
      i = getIntent().getIntExtra("EditingGroupChildActivity.fragmentResource", -1); 
    if (getIntent().hasExtra("EditingGroupChildActivity.deviceFragment"))
      bool1 = getIntent().getBooleanExtra("EditingGroupChildActivity.deviceFragment", false); 
    switch (i) {
      default:
        return;
      case 2131492907:
        initializeActivity(2131492907, (DoSthDeviceFragment)new CountdownRemovingEditGroupFragment());
        configureView(2131558673, 2131624161, 2131624050, 2131099704, 2131558462);
      case 2131492905:
        initializeActivity(2131492905, (DoSthDeviceFragment)new ChooseIconEditGroupFragment());
        configureView(2131558522, 2131624065, 2131624050, 2131099698, 2131558473);
      case 2131492904:
        initializeActivity(2131492904, (DoSthDeviceFragment)new ChangeNameEditGroupFragment());
        configureView(2131558522, 2131624065, 2131624050, 2131099698, 2131558473);
      case 2131492901:
        break;
    } 
    if (bool1) {
      initializeActivity(2131492901, (DoSthDeviceFragment)new AssignDeviceEditGroupFragment());
      configureView(2131558522, 2131624065, 2131624050, 2131099698, 2131558473);
    } 
    initializeActivity(2131492901, (DoSthDeviceFragment)new AssignToPlaceEditGroupFragment());
    configureView(2131558522, 2131624065, 2131624050, 2131099698, 2131558473);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\EditingGroupChildActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */