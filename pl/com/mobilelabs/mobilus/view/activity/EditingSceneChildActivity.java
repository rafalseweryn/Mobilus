package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingscene.AssignDevicesEditSceneFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingscene.AstralScheduleEditSceneFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingscene.ChangeNameEditSceneFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingscene.CountdownRemovingEditSceneFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingscene.DeviceActionEditSceneFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingscene.HourlyScheduleEditSceneFragment;

public class EditingSceneChildActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  public static final String DEVICE_FRAGMENT = "EditingSceneChildActivity.deviceFragment";
  
  public static final String FRAGMENT_RESOURCE = "EditingSceneChildActivity.fragmentResource";
  
  public static final int REQUEST_CODE = 10;
  
  public static final String SCENE_ID = "EditingSceneChildActivity.placeId";
  
  protected long newSomethingID = -1L;
  
  protected void backSelected() {
    this.mFragment.backSelected();
  }
  
  public long getNewSomethingID() {
    return this.newSomethingID;
  }
  
  public int getType() {
    return 3;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setResult(0);
    if (getIntent().hasExtra("EditingSceneChildActivity.placeId"))
      this.newSomethingID = getIntent().getLongExtra("EditingSceneChildActivity.placeId", -1L); 
    boolean bool = getIntent().hasExtra("EditingSceneChildActivity.fragmentResource");
    int i = -1;
    if (bool)
      i = getIntent().getIntExtra("EditingSceneChildActivity.fragmentResource", -1); 
    if (getIntent().hasExtra("EditingSceneChildActivity.deviceFragment"))
      getIntent().getBooleanExtra("EditingSceneChildActivity.deviceFragment", false); 
    if (i != 2131492904) {
      if (i != 2131492907) {
        if (i != 2131492929) {
          switch (i) {
            default:
              return;
            case 2131492902:
              initializeActivity(2131492902, (DoSthDeviceFragment)new AstralScheduleEditSceneFragment());
              configureView(2131558524, 2131624067, 2131624052, 2131099698, 2131558473);
            case 2131492901:
              initializeActivity(2131492901, (DoSthDeviceFragment)new AssignDevicesEditSceneFragment());
              configureView(2131558524, 2131624067, 2131624052, 2131099698, 2131558473);
            case 2131492900:
              break;
          } 
          initializeActivity(2131492900, (DoSthDeviceFragment)new DeviceActionEditSceneFragment());
          configureView(2131558524, 2131624067, 2131624052, 2131099698, 2131558473);
        } 
        initializeActivity(2131492929, (DoSthDeviceFragment)new HourlyScheduleEditSceneFragment());
        configureView(2131558524, 2131624067, 2131624052, 2131099698, 2131558473);
      } 
      initializeActivity(2131492907, (DoSthDeviceFragment)new CountdownRemovingEditSceneFragment());
      configureView(2131558675, 2131624165, 2131624052, 2131099704, 2131558462);
    } 
    initializeActivity(2131492904, (DoSthDeviceFragment)new ChangeNameEditSceneFragment());
    configureView(2131558524, 2131624067, 2131624052, 2131099698, 2131558473);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\EditingSceneChildActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */