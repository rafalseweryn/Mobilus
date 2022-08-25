package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

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
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.EditingSceneChildActivity;

public class ChangeNameEditSceneFragment extends EditingSceneFragment {
  @BindView(2131296485)
  protected TextInputEditText nameEditText;
  
  @BindView(2131296487)
  protected TextView saveButtonTitle;
  
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
    this.sceneName.setText(paramScene.getName());
    this.nameEditText.setText(paramScene.getName());
    this.sceneIcon.setImageResource(2131558733);
  }
  
  public void backSelected() {
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
      presentData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractScene() != null)
      presentData(); 
  }
  
  public void managementServiceConnected() {
    presentData();
    this.saveButtonTitle.setEnabled(false);
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
      this.nameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
              ChangeNameEditSceneFragment.this.saveButtonTitle.setEnabled(ChangeNameEditSceneFragment.this.nameEditText.getText().toString().isEmpty() ^ true);
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
    if (scene != null && this.saveButtonTitle.isEnabled())
      this.managementServiceBinder.editSceneName(scene.getId(), this.nameEditText.getText().toString()); 
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
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\ChangeNameEditSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */