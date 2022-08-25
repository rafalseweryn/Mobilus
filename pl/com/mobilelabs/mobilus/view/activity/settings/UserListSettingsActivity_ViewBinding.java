package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class UserListSettingsActivity_ViewBinding implements Unbinder {
  private UserListSettingsActivity target;
  
  private View view2131296577;
  
  @UiThread
  public UserListSettingsActivity_ViewBinding(UserListSettingsActivity paramUserListSettingsActivity) {
    this(paramUserListSettingsActivity, paramUserListSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public UserListSettingsActivity_ViewBinding(final UserListSettingsActivity target, View paramView) {
    this.target = target;
    paramView = Utils.findRequiredView(paramView, 2131296577, "method 'fabClicked'");
    this.view2131296577 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.fabClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    if (this.target == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    this.view2131296577.setOnClickListener(null);
    this.view2131296577 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserListSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */