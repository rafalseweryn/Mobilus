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
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceChildActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class AssignToGroupEditDeviceFragment extends EditingDeviceFragment {
  @BindView(2131296418)
  protected ImageView deviceIcon;
  
  @BindView(2131296420)
  protected TextView deviceName;
  
  protected ArrayList<AppCompatCheckBox> groupCheckBoxes = new ArrayList<>();
  
  @BindView(2131296421)
  protected TableLayout groupsListTable;
  
  private boolean gateDeviceGateGroup(Device paramDevice, Group paramGroup) {
    boolean bool;
    if (paramDevice.getType() == DeviceType.CGR && paramGroup.getType() == DeviceType.CGR) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isGroupCompatible(Device paramDevice, Group paramGroup) {
    return (gateDeviceGateGroup(paramDevice, paramGroup) || switchDeviceSwitchGroup(paramDevice, paramGroup) || notGateNotSwitch(paramDevice, paramGroup));
  }
  
  private boolean notGateNotSwitch(Device paramDevice, Group paramGroup) {
    boolean bool;
    if (paramDevice.getType() != DeviceType.ON_OFF && paramDevice.getType() != DeviceType.ON_OFF_POTENTIAL_FREE && paramDevice.getType() != DeviceType.CGR && paramGroup.getType() != DeviceType.ON_OFF && paramGroup.getType() != DeviceType.ON_OFF_POTENTIAL_FREE && paramGroup.getType() != DeviceType.CGR) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void populateTableLayout(Device paramDevice) {
    // Byte code:
    //   0: aload_0
    //   1: getfield groupsListTable : Landroid/widget/TableLayout;
    //   4: invokevirtual removeAllViews : ()V
    //   7: aload_0
    //   8: getfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   11: invokevirtual getGroups : ()Ljava/util/ArrayList;
    //   14: astore_2
    //   15: aload_0
    //   16: getfield groupCheckBoxes : Ljava/util/ArrayList;
    //   19: invokevirtual clear : ()V
    //   22: iconst_0
    //   23: istore_3
    //   24: iconst_0
    //   25: istore #4
    //   27: iload_3
    //   28: aload_2
    //   29: invokevirtual size : ()I
    //   32: if_icmpge -> 162
    //   35: aload_2
    //   36: iload_3
    //   37: invokevirtual get : (I)Ljava/lang/Object;
    //   40: checkcast pl/com/mobilelabs/mobilus/model/objects/Group
    //   43: astore #5
    //   45: aload #5
    //   47: invokevirtual getType : ()Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   50: ifnull -> 67
    //   53: iload #4
    //   55: istore #6
    //   57: aload_0
    //   58: aload_1
    //   59: aload #5
    //   61: invokespecial isGroupCompatible : (Lpl/com/mobilelabs/mobilus/model/objects/Device;Lpl/com/mobilelabs/mobilus/model/objects/Group;)Z
    //   64: ifeq -> 152
    //   67: aload_0
    //   68: invokevirtual getLayoutInflater : ()Landroid/view/LayoutInflater;
    //   71: ldc 2131492965
    //   73: aconst_null
    //   74: invokevirtual inflate : (ILandroid/view/ViewGroup;)Landroid/view/View;
    //   77: checkcast android/widget/TableRow
    //   80: astore #7
    //   82: aload #7
    //   84: ldc 2131296490
    //   86: invokevirtual findViewById : (I)Landroid/view/View;
    //   89: checkcast android/support/v7/widget/AppCompatCheckBox
    //   92: astore #8
    //   94: aload #8
    //   96: aload #5
    //   98: invokevirtual getName : ()Ljava/lang/String;
    //   101: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   104: aload #8
    //   106: aload_1
    //   107: aload #5
    //   109: invokevirtual getId : ()J
    //   112: invokevirtual belongsToGroup : (J)Z
    //   115: invokevirtual setChecked : (Z)V
    //   118: aload #8
    //   120: aload #5
    //   122: invokevirtual setTag : (Ljava/lang/Object;)V
    //   125: aload_0
    //   126: getfield groupCheckBoxes : Ljava/util/ArrayList;
    //   129: aload #8
    //   131: invokevirtual add : (Ljava/lang/Object;)Z
    //   134: pop
    //   135: aload_0
    //   136: getfield groupsListTable : Landroid/widget/TableLayout;
    //   139: aload #7
    //   141: iload #4
    //   143: invokevirtual addView : (Landroid/view/View;I)V
    //   146: iload #4
    //   148: iconst_1
    //   149: iadd
    //   150: istore #6
    //   152: iinc #3, 1
    //   155: iload #6
    //   157: istore #4
    //   159: goto -> 27
    //   162: return
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
  
  private boolean switchDeviceSwitchGroup(Device paramDevice, Group paramGroup) {
    return ((paramDevice.getType() == DeviceType.ON_OFF || paramDevice.getType() == DeviceType.ON_OFF_POTENTIAL_FREE) && (paramGroup.getType() == DeviceType.ON_OFF || paramGroup.getType() == DeviceType.ON_OFF_POTENTIAL_FREE));
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
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296422})
  protected void saveButtonClicked() {
    Device device = extractDevice();
    if (device != null) {
      ArrayList<Long> arrayList = new ArrayList();
      for (byte b = 0; b < this.groupCheckBoxes.size(); b++) {
        if (((AppCompatCheckBox)this.groupCheckBoxes.get(b)).isChecked())
          arrayList.add(Long.valueOf(((Group)((AppCompatCheckBox)this.groupCheckBoxes.get(b)).getTag()).getId())); 
      } 
      this.managementServiceBinder.editDeviceGroups(device.getId(), arrayList);
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\AssignToGroupEditDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */