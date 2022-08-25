package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.view.activity.DoSthWithDeviceInterface;
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceChildActivity;
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class MenuEditDeviceFragment extends EditingDeviceFragment {
  @BindView(2131296658)
  protected ImageView deviceIcon;
  
  @BindView(2131296659)
  protected TextView deviceName;
  
  protected boolean runForYourLife = true;
  
  private void presentData() {
    Device device = extractDevice();
    if (device != null)
      showDevice(device); 
  }
  
  private void showDevice(Device paramDevice) {
    int i;
    this.deviceName.setText(paramDevice.getName());
    State state = (State)this.managementServiceBinder.getDeviceCurrentState().get(Long.valueOf(paramDevice.getId()));
    boolean bool = true;
    if (state == null) {
      i = IconsGenerator.getErrorImageResource((BaseObject)paramDevice, true);
    } else if (state instanceof Error) {
      if (((Error)state).getErrorType() != ErrorType.NO_CONNECTION)
        bool = false; 
      i = IconsGenerator.getErrorImageResource((BaseObject)paramDevice, bool);
    } else {
      Reading reading = (Reading)state;
      if (reading.getAction() == Action.ON || reading.getAction() == Action.UP) {
        i = IconsGenerator.getTotallyOpenedImageResource((BaseObject)paramDevice);
      } else if (reading.getAction() == Action.OFF || reading.getAction() == Action.DOWN) {
        i = IconsGenerator.getTotallyClosedImageResource((BaseObject)paramDevice);
      } else {
        i = IconsGenerator.getPositionImageResource((BaseObject)paramDevice, reading.getPosition());
      } 
    } 
    this.deviceIcon.setImageResource(i);
  }
  
  @OnClick({2131296654})
  protected void addToGroupClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingDeviceChildActivity.class);
    intent.putExtra("EditingDeviceChildActivity.deviceId", ((DoSthWithDeviceInterface)ultimateHeaderActivity).getNewDeviceID());
    intent.putExtra("EditingDeviceChildActivity.fragmentResource", 2131492901);
    intent.putExtra("EditingDeviceChildActivity.groupFragment", true);
    startActivityForResult(intent, 7);
  }
  
  @OnClick({2131296655})
  protected void addToPlaceClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingDeviceChildActivity.class);
    intent.putExtra("EditingDeviceChildActivity.deviceId", ((DoSthWithDeviceInterface)ultimateHeaderActivity).getNewDeviceID());
    intent.putExtra("EditingDeviceChildActivity.fragmentResource", 2131492901);
    intent.putExtra("EditingDeviceChildActivity.groupFragment", false);
    startActivityForResult(intent, 7);
  }
  
  public void backSelected() {}
  
  @OnClick({2131296656})
  protected void changeNameClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingDeviceChildActivity.class);
    intent.putExtra("EditingDeviceChildActivity.deviceId", ((DoSthWithDeviceInterface)ultimateHeaderActivity).getNewDeviceID());
    intent.putExtra("EditingDeviceChildActivity.fragmentResource", 2131492904);
    startActivityForResult(intent, 7);
  }
  
  @OnClick({2131296657})
  protected void chooseIconClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingDeviceChildActivity.class);
    intent.putExtra("EditingDeviceChildActivity.deviceId", ((DoSthWithDeviceInterface)ultimateHeaderActivity).getNewDeviceID());
    intent.putExtra("EditingDeviceChildActivity.fragmentResource", 2131492905);
    startActivityForResult(intent, 7);
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractDevice() != null)
      presentData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractDevice() != null)
      presentData(); 
  }
  
  public void managementServiceConnected() {
    presentData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    if (extractDevice() == null && ultimateHeaderActivity != null)
      if (this.runForYourLife) {
        ultimateHeaderActivity.finish();
      } else {
        ultimateHeaderActivity.changeView(2131492962, new SuccessRemovingDeviceFragment());
        ultimateHeaderActivity.changeHeaderColor(2131099704);
        ultimateHeaderActivity.changeIconImage(2131558672);
        ultimateHeaderActivity.changeIconText(2131624158);
      }  
    if (extractDevice() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492931, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296660})
  protected void removeDeviceClicked() {
    this.runForYourLife = false;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingDeviceChildActivity.class);
    intent.putExtra("EditingDeviceChildActivity.deviceId", ((DoSthWithDeviceInterface)ultimateHeaderActivity).getNewDeviceID());
    intent.putExtra("EditingDeviceChildActivity.fragmentResource", 2131492953);
    startActivityForResult(intent, 7);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\MenuEditDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */