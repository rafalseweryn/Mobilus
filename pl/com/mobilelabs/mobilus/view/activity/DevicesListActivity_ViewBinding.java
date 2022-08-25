package pl.com.mobilelabs.mobilus.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import pl.com.mobilelabs.mobilus.view.view.ConnectionStatusView;

public class DevicesListActivity_ViewBinding implements Unbinder {
  private DevicesListActivity target;
  
  private View view2131296468;
  
  @UiThread
  public DevicesListActivity_ViewBinding(DevicesListActivity paramDevicesListActivity) {
    this(paramDevicesListActivity, paramDevicesListActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public DevicesListActivity_ViewBinding(final DevicesListActivity target, View paramView) {
    this.target = target;
    target.connectionStatus = (ConnectionStatusView)Utils.findRequiredViewAsType(paramView, 2131296375, "field 'connectionStatus'", ConnectionStatusView.class);
    target.typeImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296826, "field 'typeImage'", ImageView.class);
    target.nameText = (TextView)Utils.findRequiredViewAsType(paramView, 2131296686, "field 'nameText'", TextView.class);
    target.demoText = (TextView)Utils.findRequiredViewAsType(paramView, 2131296376, "field 'demoText'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296468, "method 'back'");
    this.view2131296468 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.back();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    DevicesListActivity devicesListActivity = this.target;
    if (devicesListActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    devicesListActivity.connectionStatus = null;
    devicesListActivity.typeImage = null;
    devicesListActivity.nameText = null;
    devicesListActivity.demoText = null;
    this.view2131296468.setOnClickListener(null);
    this.view2131296468 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\DevicesListActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */