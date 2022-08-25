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

public class AssignToPlaceEditDeviceFragment_ViewBinding implements Unbinder {
  private AssignToPlaceEditDeviceFragment target;
  
  private View view2131296422;
  
  @UiThread
  public AssignToPlaceEditDeviceFragment_ViewBinding(final AssignToPlaceEditDeviceFragment target, View paramView) {
    this.target = target;
    target.deviceName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296420, "field 'deviceName'", TextView.class);
    target.deviceIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296418, "field 'deviceIcon'", ImageView.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296426, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296427, "field 'subjectTitle'", TextView.class);
    target.placesListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296421, "field 'placesListTable'", TableLayout.class);
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
    AssignToPlaceEditDeviceFragment assignToPlaceEditDeviceFragment = this.target;
    if (assignToPlaceEditDeviceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    assignToPlaceEditDeviceFragment.deviceName = null;
    assignToPlaceEditDeviceFragment.deviceIcon = null;
    assignToPlaceEditDeviceFragment.subjectIcon = null;
    assignToPlaceEditDeviceFragment.subjectTitle = null;
    assignToPlaceEditDeviceFragment.placesListTable = null;
    this.view2131296422.setOnClickListener(null);
    this.view2131296422 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\AssignToPlaceEditDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */