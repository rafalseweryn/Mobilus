package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceChildActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class ChangeNameEditDeviceFragment extends EditingDeviceFragment {
  @BindView(2131296481)
  protected ImageView deviceIcon;
  
  @BindView(2131296483)
  protected TextView deviceName;
  
  @BindView(2131296485)
  protected TextInputEditText nameEditText;
  
  @BindView(2131296487)
  protected TextView saveButtonTitle;
  
  private void presentData() {
    Device device = extractDevice();
    if (device != null)
      showDevice(device); 
  }
  
  private void showDevice(Device paramDevice) {
    int i;
    this.deviceName.setText(paramDevice.getName());
    this.nameEditText.setText(paramDevice.getName());
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
  
  public void backSelected() {}
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDeviceResponseReceived");
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
    this.saveButtonTitle.setEnabled(false);
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractDevice() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492904, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.nameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
              ChangeNameEditDeviceFragment.this.saveButtonTitle.setEnabled(ChangeNameEditDeviceFragment.this.nameEditText.getText().toString().isEmpty() ^ true);
            }
            
            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
            
            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          });
      presentData();
      this.saveButtonTitle.setEnabled(false);
    } 
    return view1;
  }
  
  @OnClick({2131296486})
  protected void saveButtonClicked() {
    Device device = extractDevice();
    if (device != null && this.saveButtonTitle.isEnabled())
      this.managementServiceBinder.editDeviceName(device.getId(), this.nameEditText.getText().toString()); 
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {
    super.somethingElseReceived(paramContext, paramIntent);
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDeviceResponseReceived")) {
      boolean bool = false;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDeviceResponseReceivedOperationStatus", 0)) {
        case 2:
          Toast.makeText(paramContext, 2131624139, 0).show();
          break;
        case 1:
          Toast.makeText(paramContext, 2131624103, 0).show();
          break;
        case 0:
          Toast.makeText(paramContext, 2131624141, 0).show();
          bool = true;
          break;
      } 
      if (bool) {
        EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
        if (editingDeviceChildActivity != null) {
          editingDeviceChildActivity.setResult(-1);
          editingDeviceChildActivity.finish();
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\ChangeNameEditDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */