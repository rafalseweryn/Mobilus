package pl.com.mobilelabs.mobilus.view.fragment.addingscene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.AddingSceneActivity;

public class ChangeNameAddSceneFragment extends AddingSceneFragment {
  @BindView(2131296485)
  protected TextInputEditText nameEditText;
  
  @BindView(2131296487)
  protected TextView nextButtonTitle;
  
  @BindView(2131296481)
  protected ImageView sceneIcon;
  
  @BindView(2131296483)
  protected TextView sceneName;
  
  private void presentData() {
    Scene scene = extractScene();
    if (scene != null)
      showScene(scene); 
  }
  
  private void showScene(Scene paramScene) {
    if (paramScene.getName() != null && !paramScene.getName().isEmpty())
      this.sceneName.setText(paramScene.getName()); 
    this.nameEditText.setText(paramScene.getName());
    this.sceneIcon.setImageResource(2131558733);
  }
  
  public void backSelected() {
    AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
    if (addingSceneActivity != null)
      addingSceneActivity.finish(); 
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
    this.nextButtonTitle.setEnabled(false);
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractScene() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492904, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.sceneName.setText(2131624131);
      this.nextButtonTitle.setText(2131624133);
      this.managementServiceBinder = ((AddingSceneActivity)getActivity()).getManagementServiceBinder();
      this.nameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
              ChangeNameAddSceneFragment.this.nextButtonTitle.setEnabled(ChangeNameAddSceneFragment.this.nameEditText.getText().toString().isEmpty() ^ true);
            }
            
            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
            
            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          });
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296486})
  protected void saveButtonClicked() {
    Scene scene = extractScene();
    AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
    if (scene != null && this.nextButtonTitle.isEnabled() && addingSceneActivity != null) {
      scene.setName(this.nameEditText.getText().toString());
      addingSceneActivity.changeBackButtonImage(2131558473);
      addingSceneActivity.changeView(2131492900, new DeviceActionAddSceneFragment());
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingscene\ChangeNameAddSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */