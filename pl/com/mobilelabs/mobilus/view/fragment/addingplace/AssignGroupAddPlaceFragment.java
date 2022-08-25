package pl.com.mobilelabs.mobilus.view.fragment.addingplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.activity.AddingPlaceActivity;

public class AssignGroupAddPlaceFragment extends AddingPlaceFragment {
  protected ArrayList<AppCompatCheckBox> groupsCheckBoxes = new ArrayList<>();
  
  @BindView(2131296421)
  protected TableLayout groupsListTable;
  
  @BindView(2131296418)
  protected ImageView placeIcon;
  
  @BindView(2131296420)
  protected TextView placeName;
  
  @BindView(2131296426)
  protected ImageView subjectIcon;
  
  @BindView(2131296427)
  protected TextView subjectTitle;
  
  private void populateTableLayout(final Place place) {
    this.groupsListTable.removeAllViews();
    ArrayList<Group> arrayList = this.managementServiceBinder.getGroups();
    this.groupsCheckBoxes.clear();
    byte b1 = 0;
    for (byte b2 = 0; b1 < arrayList.size(); b2++) {
      final Group group = arrayList.get(b1);
      TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
      AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
      appCompatCheckBox.setText(group.getName());
      appCompatCheckBox.setChecked(place.groupBelongs(group.getId()));
      appCompatCheckBox.setTag(group);
      appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
              place.assignGroup(group, param1Boolean);
            }
          });
      this.groupsCheckBoxes.add(appCompatCheckBox);
      this.groupsListTable.addView((View)tableRow, b2);
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
      addingPlaceActivity.changeView(2131492901, new AssignDeviceAddPlaceFragment()); 
  }
  
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
      this.subjectIcon.setImageResource(2131558566);
      this.subjectTitle.setText(2131623997);
      this.managementServiceBinder = ((AddingPlaceActivity)getActivity()).getManagementServiceBinder();
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296422})
  protected void saveButtonClicked() {
    Place place = extractPlace();
    if (place != null && this.managementServiceBinder != null) {
      if ((place.getDevices()).length != 0 || (place.getGroups()).length != 0) {
        this.managementServiceBinder.addDevicePlace(place);
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
        AddingPlaceActivity addingPlaceActivity = (AddingPlaceActivity)getActivity();
        if (addingPlaceActivity != null)
          addingPlaceActivity.finish(); 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingplace\AssignGroupAddPlaceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */