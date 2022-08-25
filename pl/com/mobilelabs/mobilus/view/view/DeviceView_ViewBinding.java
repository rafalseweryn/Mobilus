package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class DeviceView_ViewBinding implements Unbinder {
  private DeviceView target;
  
  private View view2131296545;
  
  private View view2131296546;
  
  private View view2131296557;
  
  @UiThread
  public DeviceView_ViewBinding(DeviceView paramDeviceView) {
    this(paramDeviceView, (View)paramDeviceView);
  }
  
  @UiThread
  public DeviceView_ViewBinding(final DeviceView target, View paramView) {
    this.target = target;
    target.deviceIdTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296554, "field 'deviceIdTextView'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296546, "field 'deviceFavouriteCheckBox' and method 'favouriteCheckboxClicked'");
    target.deviceFavouriteCheckBox = (CheckBox)Utils.castView(view, 2131296546, "field 'deviceFavouriteCheckBox'", CheckBox.class);
    this.view2131296546 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.favouriteCheckboxClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296557, "field 'deviceStateLayout' and method 'deviceIconClicked'");
    target.deviceStateLayout = (DeviceStateView)Utils.castView(view, 2131296557, "field 'deviceStateLayout'", DeviceStateView.class);
    this.view2131296557 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.deviceIconClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296545, "field 'editButton' and method 'editButtonClicked'");
    target.editButton = (ImageButton)Utils.castView(paramView, 2131296545, "field 'editButton'", ImageButton.class);
    this.view2131296545 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.editButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    DeviceView deviceView = this.target;
    if (deviceView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    deviceView.deviceIdTextView = null;
    deviceView.deviceFavouriteCheckBox = null;
    deviceView.deviceStateLayout = null;
    deviceView.editButton = null;
    this.view2131296546.setOnClickListener(null);
    this.view2131296546 = null;
    this.view2131296557.setOnClickListener(null);
    this.view2131296557 = null;
    this.view2131296545.setOnClickListener(null);
    this.view2131296545 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\DeviceView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */