package pl.com.mobilelabs.mobilus.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import pl.com.mobilelabs.mobilus.view.view.ConnectionStatusView;

public class DashboardActivity_ViewBinding implements Unbinder {
  private DashboardActivity target;
  
  private View view2131296371;
  
  private View view2131296373;
  
  private View view2131296374;
  
  @UiThread
  public DashboardActivity_ViewBinding(DashboardActivity paramDashboardActivity) {
    this(paramDashboardActivity, paramDashboardActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public DashboardActivity_ViewBinding(final DashboardActivity target, View paramView) {
    this.target = target;
    target.viewPager = (ViewPager)Utils.findRequiredViewAsType(paramView, 2131296840, "field 'viewPager'", ViewPager.class);
    target.tabLayout = (TabLayout)Utils.findRequiredViewAsType(paramView, 2131296797, "field 'tabLayout'", TabLayout.class);
    target.toolBar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296816, "field 'toolBar'", Toolbar.class);
    target.toolbarTitleTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296817, "field 'toolbarTitleTextView'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296371, "field 'connectionStatusLayout' and method 'showGoToLoginScreen'");
    target.connectionStatusLayout = (ConnectionStatusView)Utils.castView(view, 2131296371, "field 'connectionStatusLayout'", ConnectionStatusView.class);
    this.view2131296371 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.showGoToLoginScreen();
          }
        });
    target.progressBar = (ProgressBar)Utils.findRequiredViewAsType(paramView, 2131296535, "field 'progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(paramView, 2131296374, "field 'shopImage' and method 'showShop'");
    target.shopImage = (LinearLayout)Utils.castView(view, 2131296374, "field 'shopImage'", LinearLayout.class);
    this.view2131296374 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.showShop();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296373, "field 'settingsImage' and method 'showSettings'");
    target.settingsImage = (LinearLayout)Utils.castView(view, 2131296373, "field 'settingsImage'", LinearLayout.class);
    this.view2131296373 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.showSettings();
          }
        });
    target.demoText = (TextView)Utils.findRequiredViewAsType(paramView, 2131296372, "field 'demoText'", TextView.class);
  }
  
  @CallSuper
  public void unbind() {
    DashboardActivity dashboardActivity = this.target;
    if (dashboardActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    dashboardActivity.viewPager = null;
    dashboardActivity.tabLayout = null;
    dashboardActivity.toolBar = null;
    dashboardActivity.toolbarTitleTextView = null;
    dashboardActivity.connectionStatusLayout = null;
    dashboardActivity.progressBar = null;
    dashboardActivity.shopImage = null;
    dashboardActivity.settingsImage = null;
    dashboardActivity.demoText = null;
    this.view2131296371.setOnClickListener(null);
    this.view2131296371 = null;
    this.view2131296374.setOnClickListener(null);
    this.view2131296374 = null;
    this.view2131296373.setOnClickListener(null);
    this.view2131296373 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\DashboardActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */