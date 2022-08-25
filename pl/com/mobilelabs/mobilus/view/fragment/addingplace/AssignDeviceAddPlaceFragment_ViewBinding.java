package pl.com.mobilelabs.mobilus.view.fragment.addingplace;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AssignDeviceAddPlaceFragment_ViewBinding implements Unbinder {
  private AssignDeviceAddPlaceFragment target;
  
  private View view2131296422;
  
  @UiThread
  public AssignDeviceAddPlaceFragment_ViewBinding(final AssignDeviceAddPlaceFragment target, View paramView) {
    this.target = target;
    target.placeName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296420, "field 'placeName'", TextView.class);
    target.placeIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296418, "field 'placeIcon'", ImageView.class);
    target.devicesListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296421, "field 'devicesListTable'", TableLayout.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296426, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296427, "field 'subjectTitle'", TextView.class);
    target.nextButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296423, "field 'nextButtonTitle'", TextView.class);
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
    AssignDeviceAddPlaceFragment assignDeviceAddPlaceFragment = this.target;
    if (assignDeviceAddPlaceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    assignDeviceAddPlaceFragment.placeName = null;
    assignDeviceAddPlaceFragment.placeIcon = null;
    assignDeviceAddPlaceFragment.devicesListTable = null;
    assignDeviceAddPlaceFragment.subjectIcon = null;
    assignDeviceAddPlaceFragment.subjectTitle = null;
    assignDeviceAddPlaceFragment.nextButtonTitle = null;
    this.view2131296422.setOnClickListener(null);
    this.view2131296422 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingplace\AssignDeviceAddPlaceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */