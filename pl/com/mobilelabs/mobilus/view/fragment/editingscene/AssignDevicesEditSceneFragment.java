package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

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
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.EditingSceneChildActivity;

public class AssignDevicesEditSceneFragment extends EditingSceneFragment {
  protected ArrayList<AppCompatCheckBox> devicesCheckBoxes = new ArrayList<>();
  
  @BindView(2131296421)
  protected TableLayout devicesListTable;
  
  @BindView(2131296423)
  protected TextView nextButtonTitle;
  
  private boolean runForYourLife = false;
  
  @BindView(2131296418)
  protected ImageView sceneIcon;
  
  @BindView(2131296420)
  protected TextView sceneName;
  
  @BindView(2131296426)
  protected ImageView subjectIcon;
  
  @BindView(2131296427)
  protected TextView subjectTitle;
  
  private void populateTableLayout(Scene paramScene) {
    this.devicesListTable.removeAllViews();
    ArrayList<Device> arrayList = this.managementServiceBinder.getDevices();
    this.devicesCheckBoxes.clear();
    byte b = 0;
    int i;
    for (i = 0; b < arrayList.size(); i = j) {
      Device device = arrayList.get(b);
      int j = i;
      if (paramScene.isActionCapable(device.getType())) {
        TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
        appCompatCheckBox.setText(device.getName());
        appCompatCheckBox.setChecked(paramScene.hasDevice(device.getId()));
        appCompatCheckBox.setTag(device);
        this.devicesCheckBoxes.add(appCompatCheckBox);
        this.devicesListTable.addView((View)tableRow, i);
        j = i + 1;
      } 
      b++;
    } 
  }
  
  private void refreshData() {
    Scene scene = extractScene();
    if (scene != null) {
      showScene(scene);
      populateTableLayout(scene);
    } 
  }
  
  private void showScene(Scene paramScene) {
    this.sceneName.setText(paramScene.getName());
    this.sceneIcon.setImageResource(2131558733);
  }
  
  public void backSelected() {
    EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)getActivity();
    if (editingSceneChildActivity != null)
      if (this.runForYourLife) {
        editingSceneChildActivity.finish();
      } else {
        editingSceneChildActivity.changeView(2131492900, new DeviceActionEditSceneFragment());
      }  
  }
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractScene() != null)
      refreshData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractScene() != null)
      refreshData(); 
  }
  
  public void managementServiceConnected() {
    refreshData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractScene() != null)
      refreshData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492901, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.subjectIcon.setImageResource(2131558517);
      this.subjectTitle.setText(2131624020);
      this.nextButtonTitle.setText(2131624133);
      Scene scene = extractScene();
      if (scene != null && scene.exportSceneEvents().size() == 0)
        this.runForYourLife = true; 
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296422})
  protected void saveButtonClicked() {
    Scene scene = extractScene();
    ArrayList<Device> arrayList = new ArrayList();
    for (byte b = 0; b < this.devicesCheckBoxes.size(); b++) {
      AppCompatCheckBox appCompatCheckBox = this.devicesCheckBoxes.get(b);
      if (appCompatCheckBox.isChecked()) {
        Device device = (Device)appCompatCheckBox.getTag();
        if (device != null)
          arrayList.add(device); 
      } 
    } 
    if (scene != null && arrayList.size() > 0) {
      scene.updateTempEvents(getContext(), arrayList);
      EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)getActivity();
      if (editingSceneChildActivity != null)
        editingSceneChildActivity.changeView(2131492900, new DeviceActionEditSceneFragment()); 
    } else {
      Toast.makeText((Context)getActivity(), 2131624006, 0).show();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\AssignDevicesEditSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */