package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class UserSettingsActivity_ViewBinding implements Unbinder {
  private UserSettingsActivity target;
  
  private View view2131296328;
  
  @UiThread
  public UserSettingsActivity_ViewBinding(UserSettingsActivity paramUserSettingsActivity) {
    this(paramUserSettingsActivity, paramUserSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public UserSettingsActivity_ViewBinding(final UserSettingsActivity target, View paramView) {
    this.target = target;
    target.usernameTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296330, "field 'usernameTextView'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296328, "field 'changeButton' and method 'changeButtonClicked'");
    target.changeButton = (LinearLayout)Utils.castView(view, 2131296328, "field 'changeButton'", LinearLayout.class);
    this.view2131296328 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.changeButtonClicked();
          }
        });
    target.changeButtonTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296329, "field 'changeButtonTextView'", TextView.class);
  }
  
  @CallSuper
  public void unbind() {
    UserSettingsActivity userSettingsActivity = this.target;
    if (userSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    userSettingsActivity.usernameTextView = null;
    userSettingsActivity.changeButton = null;
    userSettingsActivity.changeButtonTextView = null;
    this.view2131296328.setOnClickListener(null);
    this.view2131296328 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */