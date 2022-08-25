package pl.com.mobilelabs.mobilus.view.activity;

import android.content.Intent;
import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingplace.MenuEditPlaceFragment;

public class EditingPlaceActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  public static final String SOMETHING_ID = "EditingPlaceActivity.somethingId";
  
  protected long newSomethingID = -1L;
  
  protected int type = 2;
  
  public long getNewSomethingID() {
    return this.newSomethingID;
  }
  
  public int getType() {
    return this.type;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 9 && paramInt2 == -1)
      changeView(2131492933, (DoSthDeviceFragment)new MenuEditPlaceFragment()); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setResult(0);
    if (getIntent().hasExtra("EditingPlaceActivity.somethingId"))
      this.newSomethingID = getIntent().getLongExtra("EditingPlaceActivity.somethingId", -1L); 
    initializeActivity(2131492933, (DoSthDeviceFragment)new MenuEditPlaceFragment());
    configureView(2131558523, 2131624066, 2131624051, 2131099698, 2131558473);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\EditingPlaceActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */