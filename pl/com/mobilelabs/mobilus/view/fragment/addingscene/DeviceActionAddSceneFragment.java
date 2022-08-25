package pl.com.mobilelabs.mobilus.view.fragment.addingscene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.AddingSceneActivity;

public class DeviceActionAddSceneFragment extends AddingSceneFragment implements AdapterView.OnItemSelectedListener {
  @BindView(2131296406)
  protected TableLayout deviceActionsListTable;
  
  @BindView(2131296408)
  protected TextView nextButtonTitle;
  
  @BindView(2131296411)
  protected ImageView sceneIcon;
  
  @BindView(2131296413)
  protected TextView sceneName;
  
  @BindView(2131296415)
  protected ImageView subjectIcon;
  
  @BindView(2131296416)
  protected TextView subjectTitle;
  
  protected ArrayList<TableRow> tableRowArrayList = new ArrayList<>();
  
  private Map<String, String> valueTranslations = new HashMap<>();
  
  private void populateTableLayout(Scene paramScene) {
    AddingSceneActivity addingSceneActivity;
    this.deviceActionsListTable.removeAllViews();
    this.tableRowArrayList.clear();
    ArrayList<MobilusModel.SceneEvent> arrayList = paramScene.exportSceneEvents();
    if (arrayList.size() == 0) {
      addingSceneActivity = (AddingSceneActivity)getActivity();
      if (addingSceneActivity != null)
        addingSceneActivity.changeView(2131492901, new AssignDevicesAddSceneFragment()); 
    } else {
      byte b1 = 0;
      for (byte b2 = b1; b1 < arrayList.size(); b2++) {
        MobilusModel.SceneEvent sceneEvent = arrayList.get(b1);
        TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492923, null);
        TextView textView = (TextView)tableRow.findViewById(2131296555);
        AppCompatSpinner appCompatSpinner = (AppCompatSpinner)tableRow.findViewById(2131296352);
        Device device = extractDevice(sceneEvent.getDeviceId());
        if (device != null) {
          ArrayList arrayList1 = addingSceneActivity.valuesForType(getContext(), device.getType());
          ArrayList arrayList2 = new ArrayList();
          for (byte b = 0; b < arrayList1.size(); b++)
            arrayList2.add(this.valueTranslations.get(arrayList1.get(b))); 
          textView.setText(device.getName());
          ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), 2131492924, arrayList2);
          arrayAdapter.setDropDownViewResource(17367049);
          appCompatSpinner.setAdapter((SpinnerAdapter)arrayAdapter);
          appCompatSpinner.setSelection(arrayList1.indexOf(sceneEvent.getValue()));
          appCompatSpinner.setOnItemSelectedListener(this);
          appCompatSpinner.setTag(sceneEvent);
        } 
        this.tableRowArrayList.add(tableRow);
        this.deviceActionsListTable.addView((View)tableRow, b2);
        b1++;
      } 
    } 
  }
  
  private void populateValueTranslations() {
    this.valueTranslations.clear();
    String[] arrayOfString1 = getResources().getStringArray(2130903044);
    String[] arrayOfString2 = getResources().getStringArray(2130903041);
    String[] arrayOfString3 = getResources().getStringArray(2130903042);
    String[] arrayOfString4 = getResources().getStringArray(2130903043);
    this.valueTranslations.put(arrayOfString1[0], getString(2131624215));
    this.valueTranslations.put(arrayOfString1[1], getString(2131624214));
    this.valueTranslations.put(arrayOfString2[0], getString(2131624217));
    this.valueTranslations.put(arrayOfString2[1], getString(2131624213));
    byte b;
    for (b = 0; b < arrayOfString3.length; b++)
      this.valueTranslations.put(arrayOfString3[b], arrayOfString3[b]); 
    for (b = 0; b < arrayOfString4.length; b++) {
      this.valueTranslations.put(arrayOfString4[b], String.format("%s %s: %d%%", new Object[] { getString(2131624213), getString(2131624216), Integer.valueOf(b * 10) }));
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
    AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
    if (addingSceneActivity != null) {
      addingSceneActivity.changeBackButtonImage(2131558462);
      addingSceneActivity.changeView(2131492904, new ChangeNameAddSceneFragment());
    } 
  }
  
  @OnClick({2131296403})
  protected void chooseDeviceClicked() {
    AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
    if (addingSceneActivity != null)
      addingSceneActivity.changeView(2131492901, new AssignDevicesAddSceneFragment()); 
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
      view1 = paramLayoutInflater.inflate(2131492900, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.nextButtonTitle.setText(2131624133);
      populateValueTranslations();
      this.managementServiceBinder = ((AddingSceneActivity)getActivity()).getManagementServiceBinder();
      refreshData();
    } 
    return view1;
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    Scene scene = extractScene();
    if (scene != null) {
      MobilusModel.SceneEvent sceneEvent = (MobilusModel.SceneEvent)paramAdapterView.getTag();
      if (sceneEvent != null) {
        Device device = extractDevice(sceneEvent.getDeviceId());
        if (device != null)
          scene.changeEventValue(getContext(), sceneEvent.getDeviceId(), device.getType(), paramInt); 
      } 
    } 
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
  
  @OnClick({2131296407})
  protected void saveButtonClicked() {
    Scene scene = extractScene();
    if (scene != null && scene.exportSceneEvents().size() > 0) {
      scene.saveTempEvents();
      AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
      if (addingSceneActivity != null)
        addingSceneActivity.changeView(2131492929, new HourlyScheduleAddSceneFragment()); 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingscene\DeviceActionAddSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */