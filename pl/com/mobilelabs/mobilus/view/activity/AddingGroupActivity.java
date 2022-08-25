package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.addinggroup.ChangeNameAddGroupFragment;

public class AddingGroupActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  protected Group newGroup;
  
  protected void backSelected() {
    this.mFragment.backSelected();
  }
  
  public Group getNewGroup() {
    return this.newGroup;
  }
  
  public long getNewSomethingID() {
    return 0L;
  }
  
  public int getType() {
    return 1;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    MobilusModel.DeviceGroup.Builder builder = MobilusModel.DeviceGroup.newBuilder();
    builder.setId(-1L);
    builder.setName("");
    builder.setIcon(1);
    this.newGroup = Group.createFrom(builder.build());
    initializeActivity(2131492904, (DoSthDeviceFragment)new ChangeNameAddGroupFragment());
    configureView(2131558401, 2131623983, 2131624050, 2131099699, 2131558462);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\AddingGroupActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */