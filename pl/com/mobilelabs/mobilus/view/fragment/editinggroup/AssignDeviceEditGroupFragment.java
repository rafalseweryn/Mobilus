package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

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
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.DeviceType;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.view.activity.EditingGroupChildActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class AssignDeviceEditGroupFragment extends EditingGroupFragment {
  protected ArrayList<AppCompatCheckBox> deviceCheckBoxes = new ArrayList<>();
  
  @BindView(2131296421)
  protected TableLayout devicesListTable;
  
  @BindView(2131296418)
  protected ImageView groupIcon;
  
  @BindView(2131296420)
  protected TextView groupName;
  
  @BindView(2131296426)
  protected ImageView subjectIcon;
  
  @BindView(2131296427)
  protected TextView subjectTitle;
  
  protected ArrayList<TableRow> tableRowArrayList = new ArrayList<>();
  
  private boolean gateDeviceGateGroup(DeviceType paramDeviceType1, DeviceType paramDeviceType2) {
    boolean bool;
    if (paramDeviceType1 == DeviceType.CGR && paramDeviceType2 == DeviceType.CGR) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isGroupCompatible(DeviceType paramDeviceType1, DeviceType paramDeviceType2) {
    return (gateDeviceGateGroup(paramDeviceType1, paramDeviceType2) || switchDeviceSwitchGroup(paramDeviceType1, paramDeviceType2) || notGateNotSwitch(paramDeviceType1, paramDeviceType2));
  }
  
  private boolean notGateNotSwitch(DeviceType paramDeviceType1, DeviceType paramDeviceType2) {
    boolean bool;
    if (paramDeviceType1 != DeviceType.ON_OFF && paramDeviceType1 != DeviceType.ON_OFF_POTENTIAL_FREE && paramDeviceType1 != DeviceType.CGR && paramDeviceType2 != DeviceType.ON_OFF && paramDeviceType2 != DeviceType.ON_OFF_POTENTIAL_FREE && paramDeviceType2 != DeviceType.CGR) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void populateTableLayout(final Group group) {
    this.devicesListTable.removeAllViews();
    this.tableRowArrayList.clear();
    ArrayList<Device> arrayList = this.managementServiceBinder.getDevices();
    this.deviceCheckBoxes.clear();
    byte b1 = 0;
    for (byte b2 = b1; b1 < arrayList.size(); b2++) {
      byte b;
      Device device = arrayList.get(b1);
      TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
      AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
      appCompatCheckBox.setText(device.getName());
      appCompatCheckBox.setChecked(device.belongsToGroup(group.getId()));
      appCompatCheckBox.setTag(device);
      tableRow.setTag(device);
      appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
              AssignDeviceEditGroupFragment.this.refreshDeviceList(group);
            }
          });
      if (group.getType() == null || isGroupCompatible(device.getType(), group.getType())) {
        b = 1;
      } else {
        b = 0;
      } 
      if (b) {
        b = 0;
      } else {
        b = 8;
      } 
      tableRow.setVisibility(b);
      this.deviceCheckBoxes.add(appCompatCheckBox);
      this.tableRowArrayList.add(tableRow);
      this.devicesListTable.addView((View)tableRow, b2);
      b1++;
    } 
  }
  
  private void refreshData() {
    Group group = extractGroup();
    if (group != null) {
      showGroup(group);
      populateTableLayout(group);
    } 
  }
  
  private void refreshDeviceList(Group paramGroup) {
    ArrayList<Device> arrayList = new ArrayList();
    byte b;
    for (b = 0; b < this.deviceCheckBoxes.size(); b++) {
      if (((AppCompatCheckBox)this.deviceCheckBoxes.get(b)).isChecked())
        arrayList.add((Device)((AppCompatCheckBox)this.deviceCheckBoxes.get(b)).getTag()); 
    } 
    DeviceType deviceType = paramGroup.calculateGroupType(arrayList);
    for (b = 0; b < this.tableRowArrayList.size(); b++) {
      byte b1;
      TableRow tableRow = this.tableRowArrayList.get(b);
      Device device = (Device)tableRow.getTag();
      if (deviceType == null || isGroupCompatible(device.getType(), deviceType)) {
        b1 = 1;
      } else {
        b1 = 0;
      } 
      if (b1) {
        b1 = 0;
      } else {
        b1 = 8;
      } 
      tableRow.setVisibility(b1);
    } 
  }
  
  private void showGroup(Group paramGroup) {
    int i;
    this.groupName.setText(paramGroup.getName());
    State state = (State)this.managementServiceBinder.getGroupCurrentState().get(Long.valueOf(paramGroup.getId()));
    boolean bool = true;
    if (state == null) {
      i = IconsGenerator.getErrorImageResource((BaseObject)paramGroup, true);
    } else if (state instanceof Error) {
      if (((Error)state).getErrorType() != ErrorType.NO_CONNECTION)
        bool = false; 
      i = IconsGenerator.getErrorImageResource((BaseObject)paramGroup, bool);
    } else {
      Reading reading = (Reading)state;
      if (reading.getAction() == Action.ON || reading.getAction() == Action.UP) {
        i = IconsGenerator.getTotallyOpenedImageResource((BaseObject)paramGroup);
      } else if (reading.getAction() == Action.OFF || reading.getAction() == Action.DOWN) {
        i = IconsGenerator.getTotallyClosedImageResource((BaseObject)paramGroup);
      } else {
        i = IconsGenerator.getPositionImageResource((BaseObject)paramGroup, reading.getPosition());
      } 
    } 
    this.groupIcon.setImageResource(i);
  }
  
  private boolean switchDeviceSwitchGroup(DeviceType paramDeviceType1, DeviceType paramDeviceType2) {
    return ((paramDeviceType1 == DeviceType.ON_OFF || paramDeviceType1 == DeviceType.ON_OFF_POTENTIAL_FREE) && (paramDeviceType2 == DeviceType.ON_OFF || paramDeviceType2 == DeviceType.ON_OFF_POTENTIAL_FREE));
  }
  
  public void backSelected() {}
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDeviceGroupResponseReceived");
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractGroup() != null)
      refreshData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractGroup() != null)
      refreshData(); 
  }
  
  public void managementServiceConnected() {
    refreshData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractGroup() != null)
      refreshData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492901, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.subjectIcon.setImageResource(2131558517);
      this.subjectTitle.setText(2131623994);
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296422})
  protected void saveButtonClicked() {
    Group group = extractGroup();
    if (group != null) {
      ArrayList<Long> arrayList = new ArrayList();
      for (byte b = 0; b < this.deviceCheckBoxes.size(); b++) {
        if (((AppCompatCheckBox)this.deviceCheckBoxes.get(b)).isChecked())
          arrayList.add(Long.valueOf(((Device)((AppCompatCheckBox)this.deviceCheckBoxes.get(b)).getTag()).getId())); 
      } 
      if (arrayList.size() > 0) {
        this.managementServiceBinder.editDeviceGroupDevices(group.getId(), arrayList);
      } else {
        Toast.makeText((Context)getActivity(), 2131624006, 0).show();
      } 
    } 
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {
    super.somethingElseReceived(paramContext, paramIntent);
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDeviceGroupResponseReceived")) {
      boolean bool = false;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDeviceGroupResponseReceivedOperationStatus", 0)) {
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
        EditingGroupChildActivity editingGroupChildActivity = (EditingGroupChildActivity)getActivity();
        if (editingGroupChildActivity != null) {
          editingGroupChildActivity.setResult(-1);
          editingGroupChildActivity.finish();
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\AssignDeviceEditGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */