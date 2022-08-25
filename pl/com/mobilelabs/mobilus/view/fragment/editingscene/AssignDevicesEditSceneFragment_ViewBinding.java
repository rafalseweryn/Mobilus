package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AssignDevicesEditSceneFragment_ViewBinding implements Unbinder {
  private AssignDevicesEditSceneFragment target;
  
  private View view2131296422;
  
  @UiThread
  public AssignDevicesEditSceneFragment_ViewBinding(final AssignDevicesEditSceneFragment target, View paramView) {
    this.target = target;
    target.sceneName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296420, "field 'sceneName'", TextView.class);
    target.sceneIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296418, "field 'sceneIcon'", ImageView.class);
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
    AssignDevicesEditSceneFragment assignDevicesEditSceneFragment = this.target;
    if (assignDevicesEditSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    assignDevicesEditSceneFragment.sceneName = null;
    assignDevicesEditSceneFragment.sceneIcon = null;
    assignDevicesEditSceneFragment.devicesListTable = null;
    assignDevicesEditSceneFragment.subjectIcon = null;
    assignDevicesEditSceneFragment.subjectTitle = null;
    assignDevicesEditSceneFragment.nextButtonTitle = null;
    this.view2131296422.setOnClickListener(null);
    this.view2131296422 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\AssignDevicesEditSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */