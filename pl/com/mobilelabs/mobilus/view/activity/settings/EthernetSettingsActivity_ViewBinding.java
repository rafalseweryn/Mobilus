package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class EthernetSettingsActivity_ViewBinding implements Unbinder {
  private EthernetSettingsActivity target;
  
  private View view2131296260;
  
  @UiThread
  public EthernetSettingsActivity_ViewBinding(EthernetSettingsActivity paramEthernetSettingsActivity) {
    this(paramEthernetSettingsActivity, paramEthernetSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public EthernetSettingsActivity_ViewBinding(final EthernetSettingsActivity target, View paramView) {
    this.target = target;
    View view = Utils.findRequiredView(paramView, 2131296260, "field 'stateSwitch' and method 'ethernetStateSwitchClicked'");
    target.stateSwitch = (SwitchCompat)Utils.castView(view, 2131296260, "field 'stateSwitch'", SwitchCompat.class);
    this.view2131296260 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.ethernetStateSwitchClicked();
          }
        });
    target.ipTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296258, "field 'ipTextView'", TextView.class);
    target.macTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296259, "field 'macTextView'", TextView.class);
  }
  
  @CallSuper
  public void unbind() {
    EthernetSettingsActivity ethernetSettingsActivity = this.target;
    if (ethernetSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    ethernetSettingsActivity.stateSwitch = null;
    ethernetSettingsActivity.ipTextView = null;
    ethernetSettingsActivity.macTextView = null;
    this.view2131296260.setOnClickListener(null);
    this.view2131296260 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\EthernetSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */