package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class CgrRollerView extends RollerView {
  @BindView(2131296715)
  LinearLayout buttonContainer;
  
  @BindView(2131296716)
  RelativeLayout rollerDownButton;
  
  @BindView(2131296717)
  View rollerDownButtonDivider;
  
  @BindView(2131296718)
  LinearLayout rollerLayout;
  
  @BindView(2131296723)
  View rollerStopButtonDivider;
  
  @BindView(2131296724)
  ImageView rollerStopButtonImage;
  
  @BindView(2131296725)
  RelativeLayout rollerUpButton;
  
  protected CgrRollerView(Context paramContext) {
    super(paramContext);
  }
  
  public static CgrRollerView build(Context paramContext) {
    CgrRollerView cgrRollerView = new CgrRollerView(paramContext);
    cgrRollerView.onFinishInflate();
    return cgrRollerView;
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    this.rollerLayout.setVisibility(8);
    this.rollerDownButton.setVisibility(4);
    this.rollerStopButtonImage.setImageResource(2131230901);
    this.rollerUpButton.setVisibility(4);
  }
  
  @OnClick({2131296722})
  public void onStopButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.DOWN); 
  }
  
  @OnClick({2131296725})
  public void onUpButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.UP); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CgrRollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */