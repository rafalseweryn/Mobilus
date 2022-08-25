package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class UserPasswordSettingsActivity_ViewBinding implements Unbinder {
  private UserPasswordSettingsActivity target;
  
  private View view2131296318;
  
  private View view2131296319;
  
  @UiThread
  public UserPasswordSettingsActivity_ViewBinding(UserPasswordSettingsActivity paramUserPasswordSettingsActivity) {
    this(paramUserPasswordSettingsActivity, paramUserPasswordSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public UserPasswordSettingsActivity_ViewBinding(final UserPasswordSettingsActivity target, View paramView) {
    this.target = target;
    target.usernameTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296326, "field 'usernameTextView'", TextView.class);
    target.oldPasswordEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296323, "field 'oldPasswordEditText'", TextInputEditText.class);
    target.oldPasswordTextInput = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296322, "field 'oldPasswordTextInput'", TextInputLayout.class);
    target.newPasswordEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296321, "field 'newPasswordEditText'", TextInputEditText.class);
    target.newPasswordTextInput = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296320, "field 'newPasswordTextInput'", TextInputLayout.class);
    target.repeatPasswordEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296325, "field 'repeatPasswordEditText'", TextInputEditText.class);
    target.repeatPasswordTextInput = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296324, "field 'repeatPasswordTextInput'", TextInputLayout.class);
    View view = Utils.findRequiredView(paramView, 2131296318, "field 'applyButton' and method 'applyButtonClicked'");
    target.applyButton = (LinearLayout)Utils.castView(view, 2131296318, "field 'applyButton'", LinearLayout.class);
    this.view2131296318 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.applyButtonClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296319, "method 'cancelButtonClicked'");
    this.view2131296319 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.cancelButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    UserPasswordSettingsActivity userPasswordSettingsActivity = this.target;
    if (userPasswordSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    userPasswordSettingsActivity.usernameTextView = null;
    userPasswordSettingsActivity.oldPasswordEditText = null;
    userPasswordSettingsActivity.oldPasswordTextInput = null;
    userPasswordSettingsActivity.newPasswordEditText = null;
    userPasswordSettingsActivity.newPasswordTextInput = null;
    userPasswordSettingsActivity.repeatPasswordEditText = null;
    userPasswordSettingsActivity.repeatPasswordTextInput = null;
    userPasswordSettingsActivity.applyButton = null;
    this.view2131296318.setOnClickListener(null);
    this.view2131296318 = null;
    this.view2131296319.setOnClickListener(null);
    this.view2131296319 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserPasswordSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */