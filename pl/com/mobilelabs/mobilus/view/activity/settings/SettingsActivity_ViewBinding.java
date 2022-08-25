package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SettingsActivity_ViewBinding implements Unbinder {
  private SettingsActivity target;
  
  private View view2131296758;
  
  private View view2131296759;
  
  private View view2131296761;
  
  private View view2131296762;
  
  private View view2131296763;
  
  private View view2131296764;
  
  @UiThread
  public SettingsActivity_ViewBinding(SettingsActivity paramSettingsActivity) {
    this(paramSettingsActivity, paramSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public SettingsActivity_ViewBinding(final SettingsActivity target, View paramView) {
    this.target = target;
    View view = Utils.findRequiredView(paramView, 2131296764, "method 'wifiSelected'");
    this.view2131296764 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.wifiSelected();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296758, "method 'ethernetSelected'");
    this.view2131296758 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.ethernetSelected();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296759, "method 'remoteAccessSelected'");
    this.view2131296759 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.remoteAccessSelected();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296763, "method 'userSelected'");
    this.view2131296763 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.userSelected();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296762, "method 'timeLocalizationSelected'");
    this.view2131296762 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.timeLocalizationSelected();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296761, "method 'systemSelected'");
    this.view2131296761 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.systemSelected();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    if (this.target == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    this.view2131296764.setOnClickListener(null);
    this.view2131296764 = null;
    this.view2131296758.setOnClickListener(null);
    this.view2131296758 = null;
    this.view2131296759.setOnClickListener(null);
    this.view2131296759 = null;
    this.view2131296763.setOnClickListener(null);
    this.view2131296763 = null;
    this.view2131296762.setOnClickListener(null);
    this.view2131296762 = null;
    this.view2131296761.setOnClickListener(null);
    this.view2131296761 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */