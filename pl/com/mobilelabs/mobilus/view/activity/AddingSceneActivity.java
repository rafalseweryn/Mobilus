package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.addingscene.ChangeNameAddSceneFragment;

public class AddingSceneActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  protected Scene newScene;
  
  protected void backSelected() {
    this.mFragment.backSelected();
  }
  
  public Scene getNewScene() {
    return this.newScene;
  }
  
  public long getNewSomethingID() {
    return 0L;
  }
  
  public int getType() {
    return 3;
  }
  
  protected void managementServiceConnected() {
    super.managementServiceConnected();
    MobilusModel.Scene.Builder builder = MobilusModel.Scene.newBuilder();
    builder.setId(-1L);
    builder.setName("");
    builder.setIcon(1);
    builder.setEnabled(false);
    this.newScene = Scene.createFrom(builder.build(), getManagementServiceBinder().getLocation());
    this.mFragment.managementServiceConnected();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492904, (DoSthDeviceFragment)new ChangeNameAddSceneFragment());
    configureView(2131558403, 2131623985, 2131624052, 2131099699, 2131558462);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\AddingSceneActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */