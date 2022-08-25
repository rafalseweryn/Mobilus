package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class WifiSettingsActivity_ViewBinding implements Unbinder {
  private WifiSettingsActivity target;
  
  private View view2131296345;
  
  @UiThread
  public WifiSettingsActivity_ViewBinding(WifiSettingsActivity paramWifiSettingsActivity) {
    this(paramWifiSettingsActivity, paramWifiSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public WifiSettingsActivity_ViewBinding(final WifiSettingsActivity target, View paramView) {
    this.target = target;
    target.modeTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296348, "field 'modeTextView'", TextView.class);
    target.ipTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296346, "field 'ipTextView'", TextView.class);
    target.macTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296347, "field 'macTextView'", TextView.class);
    target.netNameTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296349, "field 'netNameTextView'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296345, "method 'wifiConfigChangeClicked'");
    this.view2131296345 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.wifiConfigChangeClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    WifiSettingsActivity wifiSettingsActivity = this.target;
    if (wifiSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    wifiSettingsActivity.modeTextView = null;
    wifiSettingsActivity.ipTextView = null;
    wifiSettingsActivity.macTextView = null;
    wifiSettingsActivity.netNameTextView = null;
    this.view2131296345.setOnClickListener(null);
    this.view2131296345 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\WifiSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */