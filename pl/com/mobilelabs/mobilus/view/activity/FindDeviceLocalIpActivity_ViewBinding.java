package pl.com.mobilelabs.mobilus.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class FindDeviceLocalIpActivity_ViewBinding implements Unbinder {
  private FindDeviceLocalIpActivity target;
  
  private View view2131296377;
  
  @UiThread
  public FindDeviceLocalIpActivity_ViewBinding(FindDeviceLocalIpActivity paramFindDeviceLocalIpActivity) {
    this(paramFindDeviceLocalIpActivity, paramFindDeviceLocalIpActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public FindDeviceLocalIpActivity_ViewBinding(final FindDeviceLocalIpActivity target, View paramView) {
    this.target = target;
    target.searchProgressBar = (ProgressBar)Utils.findRequiredViewAsType(paramView, 2131296378, "field 'searchProgressBar'", ProgressBar.class);
    target.searchTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296379, "field 'searchTextView'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296377, "field 'cancelCloseButton' and method 'closeActivity'");
    target.cancelCloseButton = (Button)Utils.castView(paramView, 2131296377, "field 'cancelCloseButton'", Button.class);
    this.view2131296377 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.closeActivity();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    FindDeviceLocalIpActivity findDeviceLocalIpActivity = this.target;
    if (findDeviceLocalIpActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    findDeviceLocalIpActivity.searchProgressBar = null;
    findDeviceLocalIpActivity.searchTextView = null;
    findDeviceLocalIpActivity.cancelCloseButton = null;
    this.view2131296377.setOnClickListener(null);
    this.view2131296377 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\FindDeviceLocalIpActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */