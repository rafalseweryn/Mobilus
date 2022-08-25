package pl.com.mobilelabs.mobilus.view.activity;

import android.content.Intent;
import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingscene.MenuEditSceneFragment;

public class EditingSceneActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  public static final String SOMETHING_ID = "EditingSceneActivity.somethingId";
  
  protected long newSomethingID = -1L;
  
  protected int type = 3;
  
  public long getNewSomethingID() {
    return this.newSomethingID;
  }
  
  public int getType() {
    return this.type;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 10 && paramInt2 == -1)
      changeView(2131492934, (DoSthDeviceFragment)new MenuEditSceneFragment()); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setResult(0);
    if (getIntent().hasExtra("EditingSceneActivity.somethingId"))
      this.newSomethingID = getIntent().getLongExtra("EditingSceneActivity.somethingId", -1L); 
    initializeActivity(2131492934, (DoSthDeviceFragment)new MenuEditSceneFragment());
    configureView(2131558524, 2131624067, 2131624052, 2131099698, 2131558473);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\EditingSceneActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */