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

public class DeviceActionEditSceneFragment_ViewBinding implements Unbinder {
  private DeviceActionEditSceneFragment target;
  
  private View view2131296403;
  
  private View view2131296407;
  
  @UiThread
  public DeviceActionEditSceneFragment_ViewBinding(final DeviceActionEditSceneFragment target, View paramView) {
    this.target = target;
    target.sceneName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296413, "field 'sceneName'", TextView.class);
    target.sceneIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296411, "field 'sceneIcon'", ImageView.class);
    target.deviceActionsListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296406, "field 'deviceActionsListTable'", TableLayout.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296415, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296416, "field 'subjectTitle'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296407, "method 'saveButtonClicked'");
    this.view2131296407 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296403, "method 'chooseDeviceClicked'");
    this.view2131296403 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.chooseDeviceClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    DeviceActionEditSceneFragment deviceActionEditSceneFragment = this.target;
    if (deviceActionEditSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    deviceActionEditSceneFragment.sceneName = null;
    deviceActionEditSceneFragment.sceneIcon = null;
    deviceActionEditSceneFragment.deviceActionsListTable = null;
    deviceActionEditSceneFragment.subjectIcon = null;
    deviceActionEditSceneFragment.subjectTitle = null;
    this.view2131296407.setOnClickListener(null);
    this.view2131296407 = null;
    this.view2131296403.setOnClickListener(null);
    this.view2131296403 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\DeviceActionEditSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */