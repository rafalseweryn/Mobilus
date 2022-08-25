package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class UserDetailsSettingsActivity_ViewBinding implements Unbinder {
  private UserDetailsSettingsActivity target;
  
  private View view2131296299;
  
  private View view2131296300;
  
  private View view2131296301;
  
  @UiThread
  public UserDetailsSettingsActivity_ViewBinding(UserDetailsSettingsActivity paramUserDetailsSettingsActivity) {
    this(paramUserDetailsSettingsActivity, paramUserDetailsSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public UserDetailsSettingsActivity_ViewBinding(final UserDetailsSettingsActivity target, View paramView) {
    this.target = target;
    target.titleView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296310, "field 'titleView'", TextView.class);
    target.usernameEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296312, "field 'usernameEditText'", TextInputEditText.class);
    target.usernameTextInput = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296311, "field 'usernameTextInput'", TextInputLayout.class);
    target.adminPasswordEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296298, "field 'adminPasswordEditText'", TextInputEditText.class);
    target.adminPasswordTextInput = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296297, "field 'adminPasswordTextInput'", TextInputLayout.class);
    target.newPasswordEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296306, "field 'newPasswordEditText'", TextInputEditText.class);
    target.newPasswordTextInput = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296305, "field 'newPasswordTextInput'", TextInputLayout.class);
    target.repeatPasswordEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296309, "field 'repeatPasswordEditText'", TextInputEditText.class);
    target.repeatPasswordTextInput = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296308, "field 'repeatPasswordTextInput'", TextInputLayout.class);
    View view = Utils.findRequiredView(paramView, 2131296299, "field 'adminSwitch' and method 'onAdminRightsCheckedChanged'");
    target.adminSwitch = (SwitchCompat)Utils.castView(view, 2131296299, "field 'adminSwitch'", SwitchCompat.class);
    this.view2131296299 = view;
    ((CompoundButton)view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            target.onAdminRightsCheckedChanged();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296300, "field 'applyButton' and method 'applyButtonClicked'");
    target.applyButton = (LinearLayout)Utils.castView(view, 2131296300, "field 'applyButton'", LinearLayout.class);
    this.view2131296300 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.applyButtonClicked();
          }
        });
    target.devicesGroupsPlacesLayout = (LinearLayout)Utils.findRequiredViewAsType(paramView, 2131296302, "field 'devicesGroupsPlacesLayout'", LinearLayout.class);
    target.devicesListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296303, "field 'devicesListTable'", TableLayout.class);
    target.groupsListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296304, "field 'groupsListTable'", TableLayout.class);
    target.placesListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296307, "field 'placesListTable'", TableLayout.class);
    paramView = Utils.findRequiredView(paramView, 2131296301, "method 'cancelButtonClicked'");
    this.view2131296301 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.cancelButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    UserDetailsSettingsActivity userDetailsSettingsActivity = this.target;
    if (userDetailsSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    userDetailsSettingsActivity.titleView = null;
    userDetailsSettingsActivity.usernameEditText = null;
    userDetailsSettingsActivity.usernameTextInput = null;
    userDetailsSettingsActivity.adminPasswordEditText = null;
    userDetailsSettingsActivity.adminPasswordTextInput = null;
    userDetailsSettingsActivity.newPasswordEditText = null;
    userDetailsSettingsActivity.newPasswordTextInput = null;
    userDetailsSettingsActivity.repeatPasswordEditText = null;
    userDetailsSettingsActivity.repeatPasswordTextInput = null;
    userDetailsSettingsActivity.adminSwitch = null;
    userDetailsSettingsActivity.applyButton = null;
    userDetailsSettingsActivity.devicesGroupsPlacesLayout = null;
    userDetailsSettingsActivity.devicesListTable = null;
    userDetailsSettingsActivity.groupsListTable = null;
    userDetailsSettingsActivity.placesListTable = null;
    ((CompoundButton)this.view2131296299).setOnCheckedChangeListener(null);
    this.view2131296299 = null;
    this.view2131296300.setOnClickListener(null);
    this.view2131296300 = null;
    this.view2131296301.setOnClickListener(null);
    this.view2131296301 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserDetailsSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */