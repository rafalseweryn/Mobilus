package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.widget.SeekBar;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class SensoZRollerGroupView extends SensoZRollerView {
  protected SensoZRollerGroupView(Context paramContext) {
    super(paramContext);
  }
  
  public static SensoZRollerGroupView build(Context paramContext) {
    SensoZRollerGroupView sensoZRollerGroupView = new SensoZRollerGroupView(paramContext);
    sensoZRollerGroupView.onFinishInflate();
    return sensoZRollerGroupView;
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
    if (paramSeekBar == this.leanSlider) {
      ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
      if (managementServiceBinder != null)
        managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), new Action(this.leanSlider.getProgress(), true)); 
    } else {
      ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
      if (managementServiceBinder != null) {
        int i = this.rollerSlider.getMax();
        int j = this.rollerSlider.getProgress();
        managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), new Action(i - j, false));
      } 
    } 
  }
  
  public void onUpButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.UP); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SensoZRollerGroupView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */