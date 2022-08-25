package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.widget.SeekBar;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class CzrMzrRollerGroupView extends CzrMzrRollerView {
  protected CzrMzrRollerGroupView(Context paramContext) {
    super(paramContext);
  }
  
  public static CzrMzrRollerGroupView build(Context paramContext) {
    CzrMzrRollerGroupView czrMzrRollerGroupView = new CzrMzrRollerGroupView(paramContext);
    czrMzrRollerGroupView.onFinishInflate();
    return czrMzrRollerGroupView;
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    this.leanLayout.setVisibility(0);
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
  
  public void onStopTrackingTouch(SeekBar paramSeekBar) {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), new Action(this.leanSlider.getProgress(), true)); 
  }
  
  public void onUpButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.UP); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CzrMzrRollerGroupView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */