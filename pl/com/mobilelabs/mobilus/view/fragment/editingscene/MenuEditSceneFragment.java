package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.DoSthWithSthInterface;
import pl.com.mobilelabs.mobilus.view.activity.EditingSceneChildActivity;
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;

public class MenuEditSceneFragment extends EditingSceneFragment {
  protected boolean runForYourLife = true;
  
  @BindView(2131296678)
  protected TextView sceneName;
  
  private void presentData() {
    Scene scene = extractScene();
    if (scene != null)
      showScene(scene); 
  }
  
  private void showScene(Scene paramScene) {
    this.sceneName.setText(paramScene.getName());
  }
  
  @OnClick({2131296674})
  protected void assignDeviceToSceneClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingSceneChildActivity.class);
    intent.putExtra("EditingSceneChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingSceneChildActivity.fragmentResource", 2131492900);
    startActivityForResult(intent, 10);
  }
  
  public void backSelected() {}
  
  @OnClick({2131296675})
  protected void changeNameClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingSceneChildActivity.class);
    intent.putExtra("EditingSceneChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingSceneChildActivity.fragmentResource", 2131492904);
    startActivityForResult(intent, 10);
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractScene() != null)
      presentData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractScene() != null)
      presentData(); 
  }
  
  public void managementServiceConnected() {
    presentData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    if (extractScene() == null && ultimateHeaderActivity != null && this.runForYourLife)
      ultimateHeaderActivity.finish(); 
    if (extractScene() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492934, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296676})
  protected void removeSceneClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingSceneChildActivity.class);
    intent.putExtra("EditingSceneChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingSceneChildActivity.fragmentResource", 2131492907);
    startActivityForResult(intent, 10);
  }
  
  @OnClick({2131296679})
  protected void timeAutomationClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingSceneChildActivity.class);
    intent.putExtra("EditingSceneChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    if (extractScene() != null && (extractScene().getAstralSchedules()).length > 0) {
      intent.putExtra("EditingSceneChildActivity.fragmentResource", 2131492902);
    } else {
      intent.putExtra("EditingSceneChildActivity.fragmentResource", 2131492929);
    } 
    startActivityForResult(intent, 10);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\MenuEditSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */