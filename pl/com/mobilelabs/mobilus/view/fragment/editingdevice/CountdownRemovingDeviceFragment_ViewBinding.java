package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class CountdownRemovingDeviceFragment_ViewBinding implements Unbinder {
  private CountdownRemovingDeviceFragment target;
  
  private View view2131296517;
  
  @UiThread
  public CountdownRemovingDeviceFragment_ViewBinding(final CountdownRemovingDeviceFragment target, View paramView) {
    this.target = target;
    View view = Utils.findRequiredView(paramView, 2131296517, "field 'nextButton' and method 'nextClicked'");
    target.nextButton = (RelativeLayout)Utils.castView(view, 2131296517, "field 'nextButton'", RelativeLayout.class);
    this.view2131296517 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.nextClicked();
          }
        });
    target.nextButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296518, "field 'nextButtonTitle'", TextView.class);
    target.progressBar = (ContentLoadingProgressBar)Utils.findRequiredViewAsType(paramView, 2131296501, "field 'progressBar'", ContentLoadingProgressBar.class);
    target.secondsLeft = (TextView)Utils.findRequiredViewAsType(paramView, 2131296753, "field 'secondsLeft'", TextView.class);
  }
  
  @CallSuper
  public void unbind() {
    CountdownRemovingDeviceFragment countdownRemovingDeviceFragment = this.target;
    if (countdownRemovingDeviceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    countdownRemovingDeviceFragment.nextButton = null;
    countdownRemovingDeviceFragment.nextButtonTitle = null;
    countdownRemovingDeviceFragment.progressBar = null;
    countdownRemovingDeviceFragment.secondsLeft = null;
    this.view2131296517.setOnClickListener(null);
    this.view2131296517 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\CountdownRemovingDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */