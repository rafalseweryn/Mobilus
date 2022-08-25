package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class WifiChangeSettingsActivity_ViewBinding implements Unbinder {
  private WifiChangeSettingsActivity target;
  
  private View view2131296331;
  
  private View view2131296332;
  
  private View view2131296344;
  
  @UiThread
  public WifiChangeSettingsActivity_ViewBinding(WifiChangeSettingsActivity paramWifiChangeSettingsActivity) {
    this(paramWifiChangeSettingsActivity, paramWifiChangeSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public WifiChangeSettingsActivity_ViewBinding(final WifiChangeSettingsActivity target, View paramView) {
    this.target = target;
    target.modeSpinner = (AppCompatSpinner)Utils.findRequiredViewAsType(paramView, 2131296335, "field 'modeSpinner'", AppCompatSpinner.class);
    target.ipTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296333, "field 'ipTextView'", TextView.class);
    target.macTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296334, "field 'macTextView'", TextView.class);
    target.netLayout = (RelativeLayout)Utils.findRequiredViewAsType(paramView, 2131296336, "field 'netLayout'", RelativeLayout.class);
    target.netNameSpinner = (AppCompatSpinner)Utils.findRequiredViewAsType(paramView, 2131296339, "field 'netNameSpinner'", AppCompatSpinner.class);
    target.netNameTextInputLayout = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296340, "field 'netNameTextInputLayout'", TextInputLayout.class);
    target.netNameTextInputEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296341, "field 'netNameTextInputEditText'", TextInputEditText.class);
    target.netPasswordTextInputLayout = (TextInputLayout)Utils.findRequiredViewAsType(paramView, 2131296342, "field 'netPasswordTextInputLayout'", TextInputLayout.class);
    target.netPasswordTextInputEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296343, "field 'netPasswordTextInputEditText'", TextInputEditText.class);
    View view = Utils.findRequiredView(paramView, 2131296344, "field 'refreshButton' and method 'wifiNetworksRefreshButtonClicked'");
    target.refreshButton = (AppCompatImageButton)Utils.castView(view, 2131296344, "field 'refreshButton'", AppCompatImageButton.class);
    this.view2131296344 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.wifiNetworksRefreshButtonClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296331, "method 'wifiConfigChangeClicked'");
    this.view2131296331 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.wifiConfigChangeClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296332, "method 'wifiConfigCancelClicked'");
    this.view2131296332 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.wifiConfigCancelClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    WifiChangeSettingsActivity wifiChangeSettingsActivity = this.target;
    if (wifiChangeSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    wifiChangeSettingsActivity.modeSpinner = null;
    wifiChangeSettingsActivity.ipTextView = null;
    wifiChangeSettingsActivity.macTextView = null;
    wifiChangeSettingsActivity.netLayout = null;
    wifiChangeSettingsActivity.netNameSpinner = null;
    wifiChangeSettingsActivity.netNameTextInputLayout = null;
    wifiChangeSettingsActivity.netNameTextInputEditText = null;
    wifiChangeSettingsActivity.netPasswordTextInputLayout = null;
    wifiChangeSettingsActivity.netPasswordTextInputEditText = null;
    wifiChangeSettingsActivity.refreshButton = null;
    this.view2131296344.setOnClickListener(null);
    this.view2131296344 = null;
    this.view2131296331.setOnClickListener(null);
    this.view2131296331 = null;
    this.view2131296332.setOnClickListener(null);
    this.view2131296332 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\WifiChangeSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */