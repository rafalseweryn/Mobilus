package pl.com.mobilelabs.mobilus.view.view.switches;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.Utils;
import com.kyleduo.switchbutton.SwitchButton;
import pl.com.mobilelabs.mobilus.view.view.DeviceView_ViewBinding;

public class SwitchView_ViewBinding extends DeviceView_ViewBinding {
  private SwitchView target;
  
  @UiThread
  public SwitchView_ViewBinding(SwitchView paramSwitchView) {
    this(paramSwitchView, (View)paramSwitchView);
  }
  
  @UiThread
  public SwitchView_ViewBinding(SwitchView paramSwitchView, View paramView) {
    super(paramSwitchView, paramView);
    this.target = paramSwitchView;
    paramSwitchView.bulbSwitch = (SwitchButton)Utils.findRequiredViewAsType(paramView, 2131296472, "field 'bulbSwitch'", SwitchButton.class);
    paramSwitchView.switchStateView = (SwitchStateView)Utils.findRequiredViewAsType(paramView, 2131296557, "field 'switchStateView'", SwitchStateView.class);
  }
  
  public void unbind() {
    SwitchView switchView = this.target;
    if (switchView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    switchView.bulbSwitch = null;
    switchView.switchStateView = null;
    super.unbind();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\switches\SwitchView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */