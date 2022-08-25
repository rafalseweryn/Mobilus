package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public abstract class SensoAbstractRollerView extends RollerView implements SeekBar.OnSeekBarChangeListener {
  @BindView(2131296720)
  protected TextView positionText;
  
  @BindView(2131296718)
  protected LinearLayout rollerLayout;
  
  @BindView(2131296721)
  SeekBar rollerSlider;
  
  protected SensoAbstractRollerView(Context paramContext) {
    super(paramContext);
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    this.rollerSlider.setMax(100);
    this.rollerSlider.setOnSeekBarChangeListener(this);
  }
  
  @OnClick({2131296716})
  public void onDownButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.DOWN); 
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean) {}
  
  public void onStartTrackingTouch(SeekBar paramSeekBar) {}
  
  @OnClick({2131296722})
  public void onStopButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.STOP); 
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar) {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null) {
      int i = this.rollerSlider.getMax();
      int j = this.rollerSlider.getProgress();
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), new Action(i - j, false));
    } 
  }
  
  @OnClick({2131296725})
  public void onUpButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.UP); 
  }
  
  public void setState(State paramState) {
    super.setState(paramState);
    if (paramState instanceof Reading) {
      int j;
      int i = ((Reading)paramState).getPosition();
      if (i < 0) {
        j = 0;
      } else {
        j = i;
        if (i > 100)
          j = 100; 
      } 
      this.rollerSlider.setProgress(this.rollerSlider.getMax() - j);
      this.positionText.setText(String.format("%d%%", new Object[] { Integer.valueOf(j) }));
    } else {
      this.rollerSlider.setProgress(0);
      this.positionText.setText("0%");
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SensoAbstractRollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */