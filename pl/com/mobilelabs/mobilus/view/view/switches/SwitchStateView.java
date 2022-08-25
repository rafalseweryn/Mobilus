package pl.com.mobilelabs.mobilus.view.view.switches;

import android.content.Context;
import android.util.AttributeSet;
import pl.com.mobilelabs.mobilus.view.view.DeviceStateView;

public class SwitchStateView extends DeviceStateView {
  public SwitchStateView(Context paramContext) {
    super(paramContext);
  }
  
  public SwitchStateView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public void showGroupIcon() {
    this.deviceStateImageView.setImageResource(2131558874);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\switches\SwitchStateView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */