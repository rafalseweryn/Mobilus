package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AssignToGroupEditDeviceFragment_ViewBinding implements Unbinder {
  private AssignToGroupEditDeviceFragment target;
  
  private View view2131296422;
  
  @UiThread
  public AssignToGroupEditDeviceFragment_ViewBinding(final AssignToGroupEditDeviceFragment target, View paramView) {
    this.target = target;
    target.deviceName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296420, "field 'deviceName'", TextView.class);
    target.deviceIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296418, "field 'deviceIcon'", ImageView.class);
    target.groupsListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296421, "field 'groupsListTable'", TableLayout.class);
    paramView = Utils.findRequiredView(paramView, 2131296422, "method 'saveButtonClicked'");
    this.view2131296422 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    AssignToGroupEditDeviceFragment assignToGroupEditDeviceFragment = this.target;
    if (assignToGroupEditDeviceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    assignToGroupEditDeviceFragment.deviceName = null;
    assignToGroupEditDeviceFragment.deviceIcon = null;
    assignToGroupEditDeviceFragment.groupsListTable = null;
    this.view2131296422.setOnClickListener(null);
    this.view2131296422 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\AssignToGroupEditDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */