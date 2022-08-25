package pl.com.mobilelabs.mobilus.view.fragment.editingplace;

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
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.activity.EditingPlaceChildActivity;

public class AssignDeviceEditPlaceFragment extends EditingPlaceFragment {
  protected ArrayList<AppCompatCheckBox> devicesCheckBoxes = new ArrayList<>();
  
  @BindView(2131296421)
  protected TableLayout devicesListTable;
  
  @BindView(2131296418)
  protected ImageView placeIcon;
  
  @BindView(2131296420)
  protected TextView placeName;
  
  @BindView(2131296426)
  protected ImageView subjectIcon;
  
  @BindView(2131296427)
  protected TextView subjectTitle;
  
  private void populateTableLayout(Place paramPlace) {
    this.devicesListTable.removeAllViews();
    ArrayList<Device> arrayList = this.managementServiceBinder.getDevices();
    this.devicesCheckBoxes.clear();
    byte b1 = 0;
    for (byte b2 = 0; b1 < arrayList.size(); b2++) {
      Device device = arrayList.get(b1);
      TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
      AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
      appCompatCheckBox.setText(device.getName());
      appCompatCheckBox.setChecked(device.belongsToPlace(paramPlace.getId()));
      appCompatCheckBox.setTag(device);
      this.devicesCheckBoxes.add(appCompatCheckBox);
      this.devicesListTable.addView((View)tableRow, b2);
      b1++;
    } 
  }
  
  private void refreshData() {
    Place place = extractPlace();
    if (place != null) {
      showPlace(place);
      populateTableLayout(place);
    } 
  }
  
  private void showPlace(Place paramPlace) {
    this.placeName.setText(paramPlace.getName());
    this.placeIcon.setImageResource(2131558655);
  }
  
  public void backSelected() {}
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDevicePlaceResponseReceived");
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractPlace() != null)
      refreshData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractPlace() != null)
      refreshData(); 
  }
  
  public void managementServiceConnected() {
    refreshData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractPlace() != null)
      refreshData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492901, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.subjectIcon.setImageResource(2131558517);
      this.subjectTitle.setText(2131623995);
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296422})
  protected void saveButtonClicked() {
    Place place = extractPlace();
    if (place != null) {
      ArrayList<Long> arrayList = new ArrayList();
      for (byte b = 0; b < this.devicesCheckBoxes.size(); b++) {
        if (((AppCompatCheckBox)this.devicesCheckBoxes.get(b)).isChecked())
          arrayList.add(Long.valueOf(((Device)((AppCompatCheckBox)this.devicesCheckBoxes.get(b)).getTag()).getId())); 
      } 
      if (arrayList.size() != 0 || (place.getGroups()).length != 0) {
        this.managementServiceBinder.editDevicePlaceDevices(place.getId(), arrayList);
        return;
      } 
      Toast.makeText((Context)getActivity(), 2131624006, 0).show();
    } 
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {
    super.somethingElseReceived(paramContext, paramIntent);
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDevicePlaceResponseReceived")) {
      boolean bool = false;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDevicePlaceResponseReceivedOperationStatus", 0)) {
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
        EditingPlaceChildActivity editingPlaceChildActivity = (EditingPlaceChildActivity)getActivity();
        if (editingPlaceChildActivity != null) {
          editingPlaceChildActivity.setResult(-1);
          editingPlaceChildActivity.finish();
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingplace\AssignDeviceEditPlaceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */