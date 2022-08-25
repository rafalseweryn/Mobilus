package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.BindView;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class SensoZRollerView extends SensoAbstractRollerView {
  @BindView(2131296637)
  protected ImageView leanImage;
  
  @BindView(2131296638)
  protected LinearLayout leanLayout;
  
  @BindView(2131296639)
  protected SeekBar leanSlider;
  
  @BindView(2131296719)
  protected TextView leanValue;
  
  protected SensoZRollerView(Context paramContext) {
    super(paramContext);
  }
  
  public static SensoZRollerView build(Context paramContext) {
    SensoZRollerView sensoZRollerView = new SensoZRollerView(paramContext);
    sensoZRollerView.onFinishInflate();
    return sensoZRollerView;
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    if (paramBaseObject.getIcon() == 35 || paramBaseObject.getIcon() == 10)
      this.leanLayout.setVisibility(0); 
    this.leanSlider.setMax(100);
    this.leanSlider.setOnSeekBarChangeListener(this);
  }
  
  public void onFinishInflate() {
    super.onFinishInflate();
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar) {
    if (paramSeekBar == this.leanSlider) {
      ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
      if (managementServiceBinder != null)
        managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), new Action(this.leanSlider.getProgress(), true)); 
    } else {
      ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
      if (managementServiceBinder != null) {
        int i = this.rollerSlider.getMax();
        int j = this.rollerSlider.getProgress();
        managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), new Action(i - j, false));
      } 
    } 
  }
  
  public void setState(State paramState) {
    super.setState(paramState);
    boolean bool = true;
    if (paramState == null) {
      this.leanImage.setImageResource(IconsGenerator.getLeanErrorImageResource(true));
      this.leanSlider.setProgress(0);
      this.leanValue.setText("0%");
    } else if (paramState instanceof Reading) {
      int j;
      int i = ((Reading)paramState).getLean();
      if (i < 0) {
        j = 0;
      } else {
        j = i;
        if (i > 100)
          j = 100; 
      } 
      this.leanImage.setImageResource(IconsGenerator.getLeanImageResource(j));
      this.leanSlider.setProgress(j);
      this.leanValue.setText(String.format("%d%%", new Object[] { Integer.valueOf(j) }));
    } else {
      Error error = (Error)paramState;
      ImageView imageView = this.leanImage;
      if (error.getErrorType() != ErrorType.NO_CONNECTION)
        bool = false; 
      imageView.setImageResource(IconsGenerator.getLeanErrorImageResource(bool));
      this.leanSlider.setProgress(0);
      this.leanValue.setText("0%");
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SensoZRollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */