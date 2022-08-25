package pl.com.mobilelabs.mobilus.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;
  
  private View view2131296383;
  
  private View view2131296386;
  
  private View view2131296388;
  
  private View view2131296389;
  
  @UiThread
  public LoginActivity_ViewBinding(LoginActivity paramLoginActivity) {
    this(paramLoginActivity, paramLoginActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View paramView) {
    this.target = target;
    target.mLoginView = (EditText)Utils.findRequiredViewAsType(paramView, 2131296390, "field 'mLoginView'", EditText.class);
    target.mPasswordView = (EditText)Utils.findRequiredViewAsType(paramView, 2131296385, "field 'mPasswordView'", EditText.class);
    target.mIpAddressView = (EditText)Utils.findRequiredViewAsType(paramView, 2131296384, "field 'mIpAddressView'", EditText.class);
    target.mLoginFormView = Utils.findRequiredView(paramView, 2131296648, "field 'mLoginFormView'");
    target.mProgressView = Utils.findRequiredView(paramView, 2131296649, "field 'mProgressView'");
    View view = Utils.findRequiredView(paramView, 2131296388, "field 'mLocalAccessButton' and method 'loginLocally'");
    target.mLocalAccessButton = (LinearLayout)Utils.castView(view, 2131296388, "field 'mLocalAccessButton'", LinearLayout.class);
    this.view2131296388 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.loginLocally();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296389, "field 'mRemoteAccessButton' and method 'loginRemotely'");
    target.mRemoteAccessButton = (LinearLayout)Utils.castView(view, 2131296389, "field 'mRemoteAccessButton'", LinearLayout.class);
    this.view2131296389 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.loginRemotely();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296386, "method 'startSearchDeviceLocalIp'");
    this.view2131296386 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.startSearchDeviceLocalIp();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296383, "method 'demoMode'");
    this.view2131296383 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.demoMode();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    LoginActivity loginActivity = this.target;
    if (loginActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    loginActivity.mLoginView = null;
    loginActivity.mPasswordView = null;
    loginActivity.mIpAddressView = null;
    loginActivity.mLoginFormView = null;
    loginActivity.mProgressView = null;
    loginActivity.mLocalAccessButton = null;
    loginActivity.mRemoteAccessButton = null;
    this.view2131296388.setOnClickListener(null);
    this.view2131296388 = null;
    this.view2131296389.setOnClickListener(null);
    this.view2131296389 = null;
    this.view2131296386.setOnClickListener(null);
    this.view2131296386 = null;
    this.view2131296383.setOnClickListener(null);
    this.view2131296383 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\LoginActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */