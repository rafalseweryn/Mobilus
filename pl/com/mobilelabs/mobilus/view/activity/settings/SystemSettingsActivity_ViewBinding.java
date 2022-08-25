package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SystemSettingsActivity_ViewBinding implements Unbinder {
  private SystemSettingsActivity target;
  
  private View view2131296295;
  
  @UiThread
  public SystemSettingsActivity_ViewBinding(SystemSettingsActivity paramSystemSettingsActivity) {
    this(paramSystemSettingsActivity, paramSystemSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public SystemSettingsActivity_ViewBinding(final SystemSettingsActivity target, View paramView) {
    this.target = target;
    target.serialNumberTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296294, "field 'serialNumberTextView'", TextView.class);
    target.currentFirmwareVersionTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296291, "field 'currentFirmwareVersionTextView'", TextView.class);
    target.latestFirmwareVersionTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296292, "field 'latestFirmwareVersionTextView'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296295, "field 'updatesButton' and method 'changeButtonClicked'");
    target.updatesButton = (LinearLayout)Utils.castView(view, 2131296295, "field 'updatesButton'", LinearLayout.class);
    this.view2131296295 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.changeButtonClicked();
          }
        });
    target.updatesButtonTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296296, "field 'updatesButtonTextView'", TextView.class);
    target.progressBar = (ContentLoadingProgressBar)Utils.findRequiredViewAsType(paramView, 2131296293, "field 'progressBar'", ContentLoadingProgressBar.class);
  }
  
  @CallSuper
  public void unbind() {
    SystemSettingsActivity systemSettingsActivity = this.target;
    if (systemSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    systemSettingsActivity.serialNumberTextView = null;
    systemSettingsActivity.currentFirmwareVersionTextView = null;
    systemSettingsActivity.latestFirmwareVersionTextView = null;
    systemSettingsActivity.updatesButton = null;
    systemSettingsActivity.updatesButtonTextView = null;
    systemSettingsActivity.progressBar = null;
    this.view2131296295.setOnClickListener(null);
    this.view2131296295 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SystemSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */