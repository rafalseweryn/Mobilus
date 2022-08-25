package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class RemoteAccessSettingsActivity_ViewBinding implements Unbinder {
  private RemoteAccessSettingsActivity target;
  
  private View view2131296265;
  
  private View view2131296266;
  
  private View view2131296267;
  
  @UiThread
  public RemoteAccessSettingsActivity_ViewBinding(RemoteAccessSettingsActivity paramRemoteAccessSettingsActivity) {
    this(paramRemoteAccessSettingsActivity, paramRemoteAccessSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public RemoteAccessSettingsActivity_ViewBinding(final RemoteAccessSettingsActivity target, View paramView) {
    this.target = target;
    View view = Utils.findRequiredView(paramView, 2131296267, "field 'stateSwitch' and method 'remoteAccessStateSwitchClicked'");
    target.stateSwitch = (SwitchCompat)Utils.castView(view, 2131296267, "field 'stateSwitch'", SwitchCompat.class);
    this.view2131296267 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.remoteAccessStateSwitchClicked();
          }
        });
    target.connectionStateTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296264, "field 'connectionStateTextView'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296265, "field 'consentCheckBox' and method 'dataProcessingCheckboxClicked'");
    target.consentCheckBox = (AppCompatCheckBox)Utils.castView(view, 2131296265, "field 'consentCheckBox'", AppCompatCheckBox.class);
    this.view2131296265 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.dataProcessingCheckboxClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296266, "field 'policyButton' and method 'policyButtonClicked'");
    target.policyButton = (LinearLayout)Utils.castView(paramView, 2131296266, "field 'policyButton'", LinearLayout.class);
    this.view2131296266 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.policyButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    RemoteAccessSettingsActivity remoteAccessSettingsActivity = this.target;
    if (remoteAccessSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    remoteAccessSettingsActivity.stateSwitch = null;
    remoteAccessSettingsActivity.connectionStateTextView = null;
    remoteAccessSettingsActivity.consentCheckBox = null;
    remoteAccessSettingsActivity.policyButton = null;
    this.view2131296267.setOnClickListener(null);
    this.view2131296267 = null;
    this.view2131296265.setOnClickListener(null);
    this.view2131296265 = null;
    this.view2131296266.setOnClickListener(null);
    this.view2131296266 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\RemoteAccessSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */