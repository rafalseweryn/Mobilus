package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.EditingSceneChildActivity;

public class HourlyScheduleEditSceneFragment extends EditingSceneFragment {
  protected ArrayList<AppCompatCheckBox> daysCheckBoxes = new ArrayList<>();
  
  @BindView(2131296601)
  protected AppCompatCheckBox fridayButton;
  
  @BindView(2131296602)
  protected TextView hourTextView;
  
  @BindView(2131296604)
  protected TextView minuteTextView;
  
  @BindView(2131296605)
  protected AppCompatCheckBox mondayButton;
  
  @BindView(2131296606)
  protected AppCompatCheckBox saturdayButton;
  
  @BindView(2131296611)
  protected ImageView sceneIcon;
  
  @BindView(2131296613)
  protected TextView sceneName;
  
  @BindView(2131296615)
  protected ImageView subjectIcon;
  
  @BindView(2131296616)
  protected TextView subjectTitle;
  
  @BindView(2131296617)
  protected AppCompatCheckBox sundayButton;
  
  @BindView(2131296618)
  protected AppCompatCheckBox thursdayButton;
  
  @BindView(2131296620)
  protected AppCompatCheckBox tuesdayButton;
  
  @BindView(2131296621)
  protected AppCompatCheckBox wednesdayButton;
  
  private void populateDaysArrayList() {
    this.daysCheckBoxes.clear();
    this.daysCheckBoxes.add(this.sundayButton);
    this.daysCheckBoxes.add(this.mondayButton);
    this.daysCheckBoxes.add(this.tuesdayButton);
    this.daysCheckBoxes.add(this.wednesdayButton);
    this.daysCheckBoxes.add(this.thursdayButton);
    this.daysCheckBoxes.add(this.fridayButton);
    this.daysCheckBoxes.add(this.saturdayButton);
  }
  
  private void refreshData() {
    Scene scene = extractScene();
    if (scene != null) {
      showScene(scene);
      selectDays(scene);
      showTime(scene);
    } 
  }
  
  private void selectDays(final Scene scene) {
    if (this.daysCheckBoxes.size() == 7)
      for (byte b = 0; b < this.daysCheckBoxes.size(); b++) {
        final AppCompatCheckBox checkBox = this.daysCheckBoxes.get(b);
        appCompatCheckBox.setChecked(scene.getDay(b));
        appCompatCheckBox.setTag(Integer.valueOf(b));
        appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
              public void onClick(View param1View) {
                scene.selectDay(((Integer)param1View.getTag()).intValue(), checkBox.isChecked());
              }
            });
      }  
  }
  
  private void showScene(Scene paramScene) {
    this.sceneName.setText(paramScene.getName());
    this.sceneIcon.setImageResource(2131558733);
  }
  
  private void showTime(Scene paramScene) {
    this.hourTextView.setText(String.format("%02d", new Object[] { Integer.valueOf(paramScene.getHour()) }));
    this.minuteTextView.setText(String.format("%02d", new Object[] { Integer.valueOf(paramScene.getMinute()) }));
  }
  
  @OnClick({2131296600})
  protected void astralButtonClicked() {
    EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)getActivity();
    if (editingSceneChildActivity != null)
      editingSceneChildActivity.changeView(2131492902, new AstralScheduleEditSceneFragment()); 
  }
  
  public void backSelected() {
    Scene scene = extractScene();
    if (scene != null)
      scene.forgetSchedule(); 
    EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)getActivity();
    if (editingSceneChildActivity != null)
      editingSceneChildActivity.finish(); 
  }
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateSceneResponseReceived");
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
      view1 = paramLayoutInflater.inflate(2131492929, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      populateDaysArrayList();
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296607})
  protected void saveButtonClicked() {
    Scene scene = extractScene();
    if (scene != null) {
      ArrayList arrayList = scene.extractWeekSchedules();
      if (arrayList.size() > 0) {
        this.managementServiceBinder.editSceneSceneWeekSchedules(scene.getId(), arrayList);
      } else {
        Toast.makeText((Context)getActivity(), 2131624006, 0).show();
      } 
    } 
  }
  
  public void setHourMinute(int paramInt1, int paramInt2) {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setHourMinute(paramInt1, paramInt2);
      refreshData();
    } 
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {
    super.somethingElseReceived(paramContext, paramIntent);
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateSceneResponseReceived")) {
      boolean bool = false;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateSceneResponseReceivedOperationStatus", 0)) {
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
        EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)getActivity();
        if (editingSceneChildActivity != null) {
          editingSceneChildActivity.setResult(-1);
          editingSceneChildActivity.finish();
        } 
      } 
    } 
  }
  
  @OnClick({2131296619})
  protected void timeClicked() {
    Scene scene = extractScene();
    if (scene != null) {
      int i = scene.getHour();
      int j = scene.getMinute();
      (new TimePickerDialog((Context)getActivity(), 2131690000, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker param1TimePicker, int param1Int1, int param1Int2) {
              HourlyScheduleEditSceneFragment.this.setHourMinute(param1Int1, param1Int2);
            }
          }i, j, true)).show();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\HourlyScheduleEditSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */