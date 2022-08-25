package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class CgrRollerGroupView extends CgrRollerView {
  private CgrRollerGroupView(Context paramContext) {
    super(paramContext);
  }
  
  public static CgrRollerGroupView build(Context paramContext) {
    CgrRollerGroupView cgrRollerGroupView = new CgrRollerGroupView(paramContext);
    cgrRollerGroupView.onFinishInflate();
    return cgrRollerGroupView;
  }
  
  public void deviceIconClicked() {}
  
  @OnClick({2131296716})
  public void onStopButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.DOWN); 
  }
  
  @OnClick({2131296725})
  public void onUpButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.UP); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CgrRollerGroupView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */