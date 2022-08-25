package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingplace.AssignDeviceEditPlaceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingplace.AssignGroupEditPlaceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingplace.ChangeNameEditPlaceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingplace.CountdownRemovingEditPlaceFragment;

public class EditingPlaceChildActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  public static final String DEVICE_FRAGMENT = "EditingPlaceChildActivity.deviceFragment";
  
  public static final String FRAGMENT_RESOURCE = "EditingPlaceChildActivity.fragmentResource";
  
  public static final String PLACE_ID = "EditingPlaceChildActivity.placeId";
  
  public static final int REQUEST_CODE = 9;
  
  protected long newSomethingID = -1L;
  
  public long getNewSomethingID() {
    return this.newSomethingID;
  }
  
  public int getType() {
    return 2;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    boolean bool1 = false;
    setResult(0);
    if (getIntent().hasExtra("EditingPlaceChildActivity.placeId"))
      this.newSomethingID = getIntent().getLongExtra("EditingPlaceChildActivity.placeId", -1L); 
    boolean bool2 = getIntent().hasExtra("EditingPlaceChildActivity.fragmentResource");
    int i = -1;
    if (bool2)
      i = getIntent().getIntExtra("EditingPlaceChildActivity.fragmentResource", -1); 
    if (getIntent().hasExtra("EditingPlaceChildActivity.deviceFragment"))
      bool1 = getIntent().getBooleanExtra("EditingPlaceChildActivity.deviceFragment", false); 
    if (i != 2131492901) {
      if (i != 2131492904) {
        if (i == 2131492907) {
          initializeActivity(2131492907, (DoSthDeviceFragment)new CountdownRemovingEditPlaceFragment());
          configureView(2131558674, 2131624163, 2131624051, 2131099704, 2131558462);
        } 
      } else {
        initializeActivity(2131492904, (DoSthDeviceFragment)new ChangeNameEditPlaceFragment());
        configureView(2131558523, 2131624066, 2131624051, 2131099698, 2131558473);
      } 
    } else if (bool1) {
      initializeActivity(2131492901, (DoSthDeviceFragment)new AssignDeviceEditPlaceFragment());
      configureView(2131558523, 2131624066, 2131624051, 2131099698, 2131558473);
    } else {
      initializeActivity(2131492901, (DoSthDeviceFragment)new AssignGroupEditPlaceFragment());
      configureView(2131558523, 2131624066, 2131624051, 2131099698, 2131558473);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\EditingPlaceChildActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */