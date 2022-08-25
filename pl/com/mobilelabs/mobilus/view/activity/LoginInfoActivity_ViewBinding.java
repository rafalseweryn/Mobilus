package pl.com.mobilelabs.mobilus.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class LoginInfoActivity_ViewBinding implements Unbinder {
  private LoginInfoActivity target;
  
  private View view2131296392;
  
  private View view2131296395;
  
  @UiThread
  public LoginInfoActivity_ViewBinding(LoginInfoActivity paramLoginInfoActivity) {
    this(paramLoginInfoActivity, paramLoginInfoActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public LoginInfoActivity_ViewBinding(final LoginInfoActivity target, View paramView) {
    this.target = target;
    target.mLoginView = (EditText)Utils.findRequiredViewAsType(paramView, 2131296382, "field 'mLoginView'", EditText.class);
    target.mIpAddressView = (EditText)Utils.findRequiredViewAsType(paramView, 2131296380, "field 'mIpAddressView'", EditText.class);
    target.ipAddressLayout = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296381, "field 'ipAddressLayout'", TextInputLayout.class);
    target.mConnectionTypeImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296393, "field 'mConnectionTypeImage'", ImageView.class);
    target.mConnectionTypeText = (TextView)Utils.findRequiredViewAsType(paramView, 2131296394, "field 'mConnectionTypeText'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296392, "method 'close'");
    this.view2131296392 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.close();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296395, "method 'logout'");
    this.view2131296395 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.logout();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    LoginInfoActivity loginInfoActivity = this.target;
    if (loginInfoActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    loginInfoActivity.mLoginView = null;
    loginInfoActivity.mIpAddressView = null;
    loginInfoActivity.ipAddressLayout = null;
    loginInfoActivity.mConnectionTypeImage = null;
    loginInfoActivity.mConnectionTypeText = null;
    this.view2131296392.setOnClickListener(null);
    this.view2131296392 = null;
    this.view2131296395.setOnClickListener(null);
    this.view2131296395 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\LoginInfoActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */