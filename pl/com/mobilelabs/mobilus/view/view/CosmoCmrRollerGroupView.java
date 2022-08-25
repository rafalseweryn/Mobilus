package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class CosmoCmrRollerGroupView extends CosmoCmrAbstractRollerView {
  public CosmoCmrRollerGroupView(Context paramContext) {
    super(paramContext);
  }
  
  public static CosmoCmrRollerGroupView build(Context paramContext) {
    CosmoCmrRollerGroupView cosmoCmrRollerGroupView = new CosmoCmrRollerGroupView(paramContext);
    cosmoCmrRollerGroupView.onFinishInflate();
    return cosmoCmrRollerGroupView;
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    this.deviceStateLayout.showGroupIcon();
  }
  
  public void deviceIconClicked() {}
  
  public void onDownButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.DOWN); 
  }
  
  public void onStopButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.STOP); 
  }
  
  public void onUpButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.UP); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CosmoCmrRollerGroupView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */