package pl.com.mobilelabs.mobilus.view.fragment.addingscene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class DeviceActionAddSceneFragment_ViewBinding implements Unbinder {
  private DeviceActionAddSceneFragment target;
  
  private View view2131296403;
  
  private View view2131296407;
  
  @UiThread
  public DeviceActionAddSceneFragment_ViewBinding(final DeviceActionAddSceneFragment target, View paramView) {
    this.target = target;
    target.sceneName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296413, "field 'sceneName'", TextView.class);
    target.sceneIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296411, "field 'sceneIcon'", ImageView.class);
    target.deviceActionsListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296406, "field 'deviceActionsListTable'", TableLayout.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296415, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296416, "field 'subjectTitle'", TextView.class);
    target.nextButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296408, "field 'nextButtonTitle'", TextView.class);
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
    DeviceActionAddSceneFragment deviceActionAddSceneFragment = this.target;
    if (deviceActionAddSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    deviceActionAddSceneFragment.sceneName = null;
    deviceActionAddSceneFragment.sceneIcon = null;
    deviceActionAddSceneFragment.deviceActionsListTable = null;
    deviceActionAddSceneFragment.subjectIcon = null;
    deviceActionAddSceneFragment.subjectTitle = null;
    deviceActionAddSceneFragment.nextButtonTitle = null;
    this.view2131296407.setOnClickListener(null);
    this.view2131296407 = null;
    this.view2131296403.setOnClickListener(null);
    this.view2131296403 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingscene\DeviceActionAddSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */