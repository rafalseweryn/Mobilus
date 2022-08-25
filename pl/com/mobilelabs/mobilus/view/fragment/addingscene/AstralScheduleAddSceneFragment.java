package pl.com.mobilelabs.mobilus.view.fragment.addingscene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.AddingSceneActivity;

public class AstralScheduleAddSceneFragment extends AddingSceneFragment {
  protected ArrayList<AppCompatCheckBox> daysCheckBoxes = new ArrayList<>();
  
  @BindView(2131296429)
  protected AppCompatCheckBox fridayButton;
  
  @BindView(2131296431)
  protected AppCompatCheckBox mondayButton;
  
  @BindView(2131296432)
  protected AppCompatCheckBox saturdayButton;
  
  @BindView(2131296437)
  protected ImageView sceneIcon;
  
  @BindView(2131296439)
  protected TextView sceneName;
  
  @BindView(2131296441)
  protected ImageView subjectIcon;
  
  @BindView(2131296442)
  protected TextView subjectTitle;
  
  @BindView(2131296443)
  protected AppCompatCheckBox sundayButton;
  
  @BindView(2131296444)
  protected RelativeLayout sunriseChecked;
  
  @BindView(2131296445)
  protected AppCompatCheckBox sunriseMinus15;
  
  @BindView(2131296446)
  protected AppCompatCheckBox sunriseMinus30;
  
  @BindView(2131296447)
  protected AppCompatCheckBox sunrisePlus15;
  
  @BindView(2131296448)
  protected AppCompatCheckBox sunrisePlus30;
  
  @BindView(2131296449)
  protected RelativeLayout sunriseUnchecked;
  
  @BindView(2131296450)
  protected RelativeLayout sunsetChecked;
  
  @BindView(2131296451)
  protected AppCompatCheckBox sunsetMinus15;
  
  @BindView(2131296452)
  protected AppCompatCheckBox sunsetMinus30;
  
  @BindView(2131296453)
  protected AppCompatCheckBox sunsetPlus15;
  
  @BindView(2131296454)
  protected AppCompatCheckBox sunsetPlus30;
  
  @BindView(2131296455)
  protected RelativeLayout sunsetUnchecked;
  
  @BindView(2131296456)
  protected AppCompatCheckBox thursdayButton;
  
  @BindView(2131296457)
  protected AppCompatCheckBox tuesdayButton;
  
  @BindView(2131296458)
  protected AppCompatCheckBox wednesdayButton;
  
  private void clearAstralSelection() {
    this.sunriseMinus30.setChecked(false);
    this.sunriseMinus15.setChecked(false);
    this.sunriseChecked.setVisibility(8);
    this.sunriseUnchecked.setVisibility(0);
    this.sunrisePlus15.setChecked(false);
    this.sunrisePlus30.setChecked(false);
    this.sunsetMinus30.setChecked(false);
    this.sunsetMinus15.setChecked(false);
    this.sunsetChecked.setVisibility(8);
    this.sunsetUnchecked.setVisibility(0);
    this.sunsetPlus15.setChecked(false);
    this.sunsetPlus30.setChecked(false);
  }
  
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
      selectAstral(scene);
    } 
  }
  
  private void selectAstral(Scene paramScene) {
    clearAstralSelection();
    int i = paramScene.getAstral();
    int j = paramScene.getInterval();
    if (i == 1) {
      if (j == 0) {
        this.sunriseChecked.setVisibility(0);
        this.sunriseUnchecked.setVisibility(8);
      } else if (j > 0) {
        if (j <= 15) {
          this.sunrisePlus15.setChecked(true);
        } else {
          this.sunrisePlus30.setChecked(true);
        } 
      } else if (j >= -15) {
        this.sunriseMinus15.setChecked(true);
      } else {
        this.sunriseMinus30.setChecked(true);
      } 
    } else if (i == 2) {
      if (j == 0) {
        this.sunsetChecked.setVisibility(0);
        this.sunsetUnchecked.setVisibility(8);
      } else if (j > 0) {
        if (j <= 15) {
          this.sunsetPlus15.setChecked(true);
        } else {
          this.sunsetPlus30.setChecked(true);
        } 
      } else if (j >= -15) {
        this.sunsetMinus15.setChecked(true);
      } else {
        this.sunsetMinus30.setChecked(true);
      } 
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
  
  public void backSelected() {
    AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
    if (addingSceneActivity != null)
      addingSceneActivity.changeView(2131492900, new DeviceActionAddSceneFragment()); 
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
  
  @OnClick({2131296430})
  protected void hourlyButtonClicked() {
    AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
    if (addingSceneActivity != null)
      addingSceneActivity.changeView(2131492929, new HourlyScheduleAddSceneFragment()); 
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
      view1 = paramLayoutInflater.inflate(2131492902, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      populateDaysArrayList();
      this.managementServiceBinder = ((AddingSceneActivity)getActivity()).getManagementServiceBinder();
      refreshData();
    } 
    return view1;
  }
  
  @OnClick({2131296433})
  protected void saveButtonClicked() {
    Scene scene = extractScene();
    if (scene != null)
      if (scene.extractAstralSchedules().size() > 0) {
        scene.generateAstralSchedules();
        this.managementServiceBinder.addScene(scene);
      } else {
        Toast.makeText((Context)getActivity(), 2131624006, 0).show();
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
        AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
        if (addingSceneActivity != null) {
          addingSceneActivity.setResult(-1);
          addingSceneActivity.finish();
        } 
      } 
    } 
  }
  
  @OnClick({2131296445})
  protected void sunriseMinus15Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunriseWithOffset(-15);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296446})
  protected void sunriseMinus30Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunriseWithOffset(-30);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296447})
  protected void sunrisePlus15Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunriseWithOffset(15);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296448})
  protected void sunrisePlus30Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunriseWithOffset(30);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296449})
  protected void sunriseUncheckedClicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunriseWithOffset(0);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296451})
  protected void sunsetMinus15Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunsetWithOffset(-15);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296452})
  protected void sunsetMinus30Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunsetWithOffset(-30);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296453})
  protected void sunsetPlus15Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunsetWithOffset(15);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296454})
  protected void sunsetPlus30Clicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunsetWithOffset(30);
      selectAstral(scene);
    } 
  }
  
  @OnClick({2131296455})
  protected void sunsetUncheckedClicked() {
    Scene scene = extractScene();
    if (scene != null) {
      scene.setSunsetWithOffset(0);
      selectAstral(scene);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingscene\AstralScheduleAddSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */