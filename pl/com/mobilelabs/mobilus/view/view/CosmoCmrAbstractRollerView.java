package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public abstract class CosmoCmrAbstractRollerView extends RollerView {
  @BindView(2131296718)
  protected LinearLayout rollerLayout;
  
  protected CosmoCmrAbstractRollerView(Context paramContext) {
    super(paramContext);
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    this.rollerLayout.setVisibility(8);
  }
  
  @OnClick({2131296716})
  public void onDownButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.DOWN); 
  }
  
  @OnClick({2131296722})
  public void onStopButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.STOP); 
  }
  
  @OnClick({2131296725})
  public void onUpButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.UP); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CosmoCmrAbstractRollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */