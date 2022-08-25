package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MenuEditGroupFragment_ViewBinding implements Unbinder {
  private MenuEditGroupFragment target;
  
  private View view2131296661;
  
  private View view2131296662;
  
  private View view2131296663;
  
  private View view2131296664;
  
  private View view2131296667;
  
  @UiThread
  public MenuEditGroupFragment_ViewBinding(final MenuEditGroupFragment target, View paramView) {
    this.target = target;
    target.groupName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296666, "field 'groupName'", TextView.class);
    target.groupIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296665, "field 'groupIcon'", ImageView.class);
    View view = Utils.findRequiredView(paramView, 2131296663, "method 'changeNameClicked'");
    this.view2131296663 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.changeNameClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296664, "method 'chooseIconClicked'");
    this.view2131296664 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.chooseIconClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296661, "method 'assignDeviceToGroupClicked'");
    this.view2131296661 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.assignDeviceToGroupClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296662, "method 'assignGroupToPlaceClicked'");
    this.view2131296662 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.assignGroupToPlaceClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296667, "method 'removeGroupClicked'");
    this.view2131296667 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.removeGroupClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    MenuEditGroupFragment menuEditGroupFragment = this.target;
    if (menuEditGroupFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    menuEditGroupFragment.groupName = null;
    menuEditGroupFragment.groupIcon = null;
    this.view2131296663.setOnClickListener(null);
    this.view2131296663 = null;
    this.view2131296664.setOnClickListener(null);
    this.view2131296664 = null;
    this.view2131296661.setOnClickListener(null);
    this.view2131296661 = null;
    this.view2131296662.setOnClickListener(null);
    this.view2131296662 = null;
    this.view2131296667.setOnClickListener(null);
    this.view2131296667 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\MenuEditGroupFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */