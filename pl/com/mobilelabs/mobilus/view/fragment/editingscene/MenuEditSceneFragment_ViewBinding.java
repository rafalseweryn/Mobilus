package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MenuEditSceneFragment_ViewBinding implements Unbinder {
  private MenuEditSceneFragment target;
  
  private View view2131296674;
  
  private View view2131296675;
  
  private View view2131296676;
  
  private View view2131296679;
  
  @UiThread
  public MenuEditSceneFragment_ViewBinding(final MenuEditSceneFragment target, View paramView) {
    this.target = target;
    target.sceneName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296678, "field 'sceneName'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296675, "method 'changeNameClicked'");
    this.view2131296675 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.changeNameClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296674, "method 'assignDeviceToSceneClicked'");
    this.view2131296674 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.assignDeviceToSceneClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296679, "method 'timeAutomationClicked'");
    this.view2131296679 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.timeAutomationClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296676, "method 'removeSceneClicked'");
    this.view2131296676 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.removeSceneClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    MenuEditSceneFragment menuEditSceneFragment = this.target;
    if (menuEditSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    menuEditSceneFragment.sceneName = null;
    this.view2131296675.setOnClickListener(null);
    this.view2131296675 = null;
    this.view2131296674.setOnClickListener(null);
    this.view2131296674 = null;
    this.view2131296679.setOnClickListener(null);
    this.view2131296679 = null;
    this.view2131296676.setOnClickListener(null);
    this.view2131296676 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\MenuEditSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */