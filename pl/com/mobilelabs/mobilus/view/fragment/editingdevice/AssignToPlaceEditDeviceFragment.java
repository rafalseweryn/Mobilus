package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceChildActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class AssignToPlaceEditDeviceFragment extends EditingDeviceFragment {
  @BindView(2131296418)
  protected ImageView deviceIcon;
  
  @BindView(2131296420)
  protected TextView deviceName;
  
  protected ArrayList<AppCompatCheckBox> placeCheckBoxes = new ArrayList<>();
  
  @BindView(2131296421)
  protected TableLayout placesListTable;
  
  @BindView(2131296426)
  protected ImageView subjectIcon;
  
  @BindView(2131296427)
  protected TextView subjectTitle;
  
  private void populateTableLayout(Device paramDevice) {
    this.placesListTable.removeAllViews();
    ArrayList<Place> arrayList = this.managementServiceBinder.getPlaces();
    this.placeCheckBoxes.clear();
    for (byte b = 0; b < arrayList.size(); b++) {
      Place place = arrayList.get(b);
      TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
      AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
      appCompatCheckBox.setText(place.getName());
      appCompatCheckBox.setChecked(paramDevice.belongsToPlace(place.getId()));
      appCompatCheckBox.setTag(place);
      this.placeCheckBoxes.add(appCompatCheckBox);
      this.placesListTable.addView((View)tableRow, b);
    } 
  }
  
  private void refreshData() {
    Device device = extractDevice();
    if (device != null) {
      showDevice(device);
      populateTableLayout(device);
    } 
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
  
  public void backSelected() {}
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDeviceResponseReceived");
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractDevice() != null)
      refreshData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractDevice() != null)
      refreshData(); 
  }
  
  public void managementServiceConnected() {
    refreshData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractDevice() != null)
      refreshData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492901, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.subjectIcon.setImageResource(2131558656);
      this.subjectTitle.setText(2131623987);
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296422})
  protected void saveButtonClicked() {
    Device device = extractDevice();
    if (device != null) {
      ArrayList<Long> arrayList = new ArrayList();
      for (byte b = 0; b < this.placeCheckBoxes.size(); b++) {
        if (((AppCompatCheckBox)this.placeCheckBoxes.get(b)).isChecked())
          arrayList.add(Long.valueOf(((Place)((AppCompatCheckBox)this.placeCheckBoxes.get(b)).getTag()).getId())); 
      } 
      this.managementServiceBinder.editDevicePlaces(device.getId(), arrayList);
    } 
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\AssignToPlaceEditDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */