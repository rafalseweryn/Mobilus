package pl.com.mobilelabs.mobilus.view.view.switches;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.kyleduo.switchbutton.SwitchButton;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;
import pl.com.mobilelabs.mobilus.view.view.DeviceView;

public class SwitchView extends DeviceView implements CompoundButton.OnCheckedChangeListener {
  private static final String TAG = "SwitchView";
  
  private boolean alreadyInflated = false;
  
  @BindView(2131296472)
  SwitchButton bulbSwitch;
  
  @BindView(2131296557)
  SwitchStateView switchStateView;
  
  protected SwitchView(Context paramContext) {
    super(paramContext);
  }
  
  public static SwitchView build(Context paramContext) {
    SwitchView switchView = new SwitchView(paramContext);
    switchView.onFinishInflate();
    return switchView;
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean) {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      if (paramBoolean) {
        managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.ON);
      } else {
        managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.OFF);
      }  
  }
  
  public void onFinishInflate() {
    if (!this.alreadyInflated) {
      this.alreadyInflated = true;
      inflate(getContext(), 2131492977, (ViewGroup)this);
      ButterKnife.bind((View)this);
      this.bulbSwitch.setOnCheckedChangeListener(this);
    } 
    super.onFinishInflate();
  }
  
  public void setState(State paramState) {
    super.setState(paramState);
    if (paramState instanceof Reading)
      if (((Reading)paramState).getAction() == Action.ON) {
        this.bulbSwitch.setCheckedImmediatelyNoEvent(true);
      } else {
        this.bulbSwitch.setCheckedImmediatelyNoEvent(false);
      }  
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\switches\SwitchView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */