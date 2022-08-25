package pl.com.mobilelabs.mobilus.view.fragment.addingplace;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.activity.AddingPlaceActivity;

public class AssignDeviceAddPlaceFragment extends AddingPlaceFragment {
  protected ArrayList<AppCompatCheckBox> devicesCheckBoxes = new ArrayList<>();
  
  @BindView(2131296421)
  protected TableLayout devicesListTable;
  
  @BindView(2131296423)
  protected TextView nextButtonTitle;
  
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
      appCompatCheckBox.setChecked(paramPlace.deviceBelongs(device.getId()));
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
  
  public void backSelected() {
    AddingPlaceActivity addingPlaceActivity = (AddingPlaceActivity)getActivity();
    if (addingPlaceActivity != null)
      addingPlaceActivity.changeView(2131492904, new ChangeNameAddPlaceFragment()); 
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
      this.nextButtonTitle.setText(2131624133);
      this.managementServiceBinder = ((AddingPlaceActivity)getActivity()).getManagementServiceBinder();
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296422})
  protected void saveButtonClicked() {
    Place place = extractPlace();
    AddingPlaceActivity addingPlaceActivity = (AddingPlaceActivity)getActivity();
    if (place != null && addingPlaceActivity != null) {
      ArrayList<Device> arrayList = new ArrayList();
      for (byte b = 0; b < this.devicesCheckBoxes.size(); b++) {
        if (((AppCompatCheckBox)this.devicesCheckBoxes.get(b)).isChecked())
          arrayList.add((Device)((AppCompatCheckBox)this.devicesCheckBoxes.get(b)).getTag()); 
      } 
      place.assignDevices(arrayList);
      addingPlaceActivity.changeView(2131492901, new AssignGroupAddPlaceFragment());
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingplace\AssignDeviceAddPlaceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */