package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MenuEditDeviceFragment_ViewBinding implements Unbinder {
  private MenuEditDeviceFragment target;
  
  private View view2131296654;
  
  private View view2131296655;
  
  private View view2131296656;
  
  private View view2131296657;
  
  private View view2131296660;
  
  @UiThread
  public MenuEditDeviceFragment_ViewBinding(final MenuEditDeviceFragment target, View paramView) {
    this.target = target;
    target.deviceName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296659, "field 'deviceName'", TextView.class);
    target.deviceIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296658, "field 'deviceIcon'", ImageView.class);
    View view = Utils.findRequiredView(paramView, 2131296656, "method 'changeNameClicked'");
    this.view2131296656 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.changeNameClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296657, "method 'chooseIconClicked'");
    this.view2131296657 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.chooseIconClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296654, "method 'addToGroupClicked'");
    this.view2131296654 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.addToGroupClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296655, "method 'addToPlaceClicked'");
    this.view2131296655 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.addToPlaceClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296660, "method 'removeDeviceClicked'");
    this.view2131296660 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.removeDeviceClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    MenuEditDeviceFragment menuEditDeviceFragment = this.target;
    if (menuEditDeviceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    menuEditDeviceFragment.deviceName = null;
    menuEditDeviceFragment.deviceIcon = null;
    this.view2131296656.setOnClickListener(null);
    this.view2131296656 = null;
    this.view2131296657.setOnClickListener(null);
    this.view2131296657 = null;
    this.view2131296654.setOnClickListener(null);
    this.view2131296654 = null;
    this.view2131296655.setOnClickListener(null);
    this.view2131296655 = null;
    this.view2131296660.setOnClickListener(null);
    this.view2131296660 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\MenuEditDeviceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */