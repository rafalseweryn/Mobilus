package pl.com.mobilelabs.mobilus.view.activity;

import android.content.Intent;
import android.os.Bundle;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.addingdevice.ProgrammingModeAddingDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.MenuEditDeviceFragment;

public class AddingDeviceActivity extends UltimateHeaderActivity implements DoSthWithDeviceInterface {
  protected long newDeviceID = -1L;
  
  protected int newDeviceType = -1;
  
  public long getNewDeviceID() {
    return this.newDeviceID;
  }
  
  public int getNewDeviceType() {
    return this.newDeviceType;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 7 && paramInt2 == -1)
      changeView(2131492931, (DoSthDeviceFragment)new MenuEditDeviceFragment()); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492952, (DoSthDeviceFragment)new ProgrammingModeAddingDeviceFragment());
    configureView(2131558400, 2131623980, 2131624049, 2131099699, 2131558462);
  }
  
  public void setNewDeviceID(long paramLong) {
    this.newDeviceID = paramLong;
  }
  
  public void setNewDeviceType(int paramInt) {
    this.newDeviceType = paramInt;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\AddingDeviceActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */