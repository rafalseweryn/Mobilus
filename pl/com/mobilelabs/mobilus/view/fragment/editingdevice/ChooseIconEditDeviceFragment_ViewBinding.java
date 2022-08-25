package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class ChooseIconEditDeviceFragment_ViewBinding implements Unbinder {
  private ChooseIconEditDeviceFragment target;
  
  private View view2131296496;
  
  @UiThread
  public ChooseIconEditDeviceFragment_ViewBinding(final ChooseIconEditDeviceFragment target, View paramView) {
    this.target = target;
    target.deviceName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296495, "field 'deviceName'", TextView.class);
    target.deviceIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296493, "field 'deviceIcon'", ImageView.class);
    target.recyclerView = (RecyclerView)Utils.findRequiredViewAsType(paramView, 2131296711, "field 'recyclerView'", RecyclerView.class);
    target.saveButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296497, "field 'saveButtonTitle'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296496, "method 'saveButtonClicked'");
    this.view2131296496 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    ChooseIconEditDeviceFragment chooseIconEditDeviceFragment = this.target;
    if (chooseIconEditDeviceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    chooseIconEditDeviceFragment.deviceName = null;
    chooseIconEditDeviceFragment.deviceIcon = null;
    chooseIconEditDeviceFragment.recyclerView = null;
    chooseIconEditDeviceFragment.saveButtonTitle = null;
    this.view2131296496.setOnClickListener(null);
    this.view2131296496 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\ChooseIconEditDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */