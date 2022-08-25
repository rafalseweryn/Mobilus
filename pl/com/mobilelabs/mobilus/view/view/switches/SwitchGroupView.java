package pl.com.mobilelabs.mobilus.view.view.switches;

import android.content.Context;
import android.widget.CompoundButton;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class SwitchGroupView extends SwitchView {
  private SwitchGroupView(Context paramContext) {
    super(paramContext);
  }
  
  public static SwitchGroupView build(Context paramContext) {
    SwitchGroupView switchGroupView = new SwitchGroupView(paramContext);
    switchGroupView.onFinishInflate();
    return switchGroupView;
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    this.switchStateView.showGroupIcon();
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean) {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      if (paramBoolean) {
        managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.ON);
      } else {
        managementServiceBinder.sendControlGroupMessage(this.presentedObject.getId(), Action.OFF);
      }  
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\switches\SwitchGroupView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */