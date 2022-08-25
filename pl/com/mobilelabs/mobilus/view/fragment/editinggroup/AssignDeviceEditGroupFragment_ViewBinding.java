package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AssignDeviceEditGroupFragment_ViewBinding implements Unbinder {
  private AssignDeviceEditGroupFragment target;
  
  private View view2131296422;
  
  @UiThread
  public AssignDeviceEditGroupFragment_ViewBinding(final AssignDeviceEditGroupFragment target, View paramView) {
    this.target = target;
    target.groupName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296420, "field 'groupName'", TextView.class);
    target.groupIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296418, "field 'groupIcon'", ImageView.class);
    target.devicesListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296421, "field 'devicesListTable'", TableLayout.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296426, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296427, "field 'subjectTitle'", TextView.class);
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
    AssignDeviceEditGroupFragment assignDeviceEditGroupFragment = this.target;
    if (assignDeviceEditGroupFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    assignDeviceEditGroupFragment.groupName = null;
    assignDeviceEditGroupFragment.groupIcon = null;
    assignDeviceEditGroupFragment.devicesListTable = null;
    assignDeviceEditGroupFragment.subjectIcon = null;
    assignDeviceEditGroupFragment.subjectTitle = null;
    this.view2131296422.setOnClickListener(null);
    this.view2131296422 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\AssignDeviceEditGroupFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */