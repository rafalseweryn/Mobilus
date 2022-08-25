package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class ChangeNameEditDeviceFragment_ViewBinding implements Unbinder {
  private ChangeNameEditDeviceFragment target;
  
  private View view2131296486;
  
  @UiThread
  public ChangeNameEditDeviceFragment_ViewBinding(final ChangeNameEditDeviceFragment target, View paramView) {
    this.target = target;
    target.deviceName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296483, "field 'deviceName'", TextView.class);
    target.deviceIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296481, "field 'deviceIcon'", ImageView.class);
    target.nameEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296485, "field 'nameEditText'", TextInputEditText.class);
    target.saveButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296487, "field 'saveButtonTitle'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296486, "method 'saveButtonClicked'");
    this.view2131296486 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    ChangeNameEditDeviceFragment changeNameEditDeviceFragment = this.target;
    if (changeNameEditDeviceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    changeNameEditDeviceFragment.deviceName = null;
    changeNameEditDeviceFragment.deviceIcon = null;
    changeNameEditDeviceFragment.nameEditText = null;
    changeNameEditDeviceFragment.saveButtonTitle = null;
    this.view2131296486.setOnClickListener(null);
    this.view2131296486 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\ChangeNameEditDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */