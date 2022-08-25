package pl.com.mobilelabs.mobilus.view.fragment.addingdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class DeviceFoundedAddingDeviceFragment_ViewBinding implements Unbinder {
  private DeviceFoundedAddingDeviceFragment target;
  
  private View view2131296549;
  
  @UiThread
  public DeviceFoundedAddingDeviceFragment_ViewBinding(final DeviceFoundedAddingDeviceFragment target, View paramView) {
    this.target = target;
    target.typeTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296553, "field 'typeTextView'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296549, "method 'nextClicked'");
    this.view2131296549 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.nextClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    DeviceFoundedAddingDeviceFragment deviceFoundedAddingDeviceFragment = this.target;
    if (deviceFoundedAddingDeviceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    deviceFoundedAddingDeviceFragment.typeTextView = null;
    this.view2131296549.setOnClickListener(null);
    this.view2131296549 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingdevice\DeviceFoundedAddingDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */