package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class FailureRemovingDeviceFragment_ViewBinding implements Unbinder {
  private FailureRemovingDeviceFragment target;
  
  private View view2131296584;
  
  @UiThread
  public FailureRemovingDeviceFragment_ViewBinding(final FailureRemovingDeviceFragment target, View paramView) {
    this.target = target;
    paramView = Utils.findRequiredView(paramView, 2131296584, "method 'nextClicked'");
    this.view2131296584 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.nextClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    if (this.target == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    this.view2131296584.setOnClickListener(null);
    this.view2131296584 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\FailureRemovingDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */