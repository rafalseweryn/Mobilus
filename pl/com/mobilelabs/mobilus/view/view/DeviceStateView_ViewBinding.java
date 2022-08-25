package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class DeviceStateView_ViewBinding implements Unbinder {
  private DeviceStateView target;
  
  @UiThread
  public DeviceStateView_ViewBinding(DeviceStateView paramDeviceStateView) {
    this(paramDeviceStateView, (View)paramDeviceStateView);
  }
  
  @UiThread
  public DeviceStateView_ViewBinding(DeviceStateView paramDeviceStateView, View paramView) {
    this.target = paramDeviceStateView;
    paramDeviceStateView.deviceWorkingProgressBar = (ProgressBar)Utils.findRequiredViewAsType(paramView, 2131296558, "field 'deviceWorkingProgressBar'", ProgressBar.class);
    paramDeviceStateView.deviceStateImageView = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296556, "field 'deviceStateImageView'", ImageView.class);
  }
  
  @CallSuper
  public void unbind() {
    DeviceStateView deviceStateView = this.target;
    if (deviceStateView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    deviceStateView.deviceWorkingProgressBar = null;
    deviceStateView.deviceStateImageView = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\DeviceStateView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */