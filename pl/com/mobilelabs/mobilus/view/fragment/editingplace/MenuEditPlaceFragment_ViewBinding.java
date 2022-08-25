package pl.com.mobilelabs.mobilus.view.fragment.editingplace;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MenuEditPlaceFragment_ViewBinding implements Unbinder {
  private MenuEditPlaceFragment target;
  
  private View view2131296668;
  
  private View view2131296669;
  
  private View view2131296670;
  
  private View view2131296673;
  
  @UiThread
  public MenuEditPlaceFragment_ViewBinding(final MenuEditPlaceFragment target, View paramView) {
    this.target = target;
    target.placeName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296672, "field 'placeName'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296670, "method 'changeNameClicked'");
    this.view2131296670 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.changeNameClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296668, "method 'assignDeviceToPlaceClicked'");
    this.view2131296668 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.assignDeviceToPlaceClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296669, "method 'assignGroupToPlaceClicked'");
    this.view2131296669 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.assignGroupToPlaceClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296673, "method 'removePlaceClicked'");
    this.view2131296673 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.removePlaceClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    MenuEditPlaceFragment menuEditPlaceFragment = this.target;
    if (menuEditPlaceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    menuEditPlaceFragment.placeName = null;
    this.view2131296670.setOnClickListener(null);
    this.view2131296670 = null;
    this.view2131296668.setOnClickListener(null);
    this.view2131296668 = null;
    this.view2131296669.setOnClickListener(null);
    this.view2131296669 = null;
    this.view2131296673.setOnClickListener(null);
    this.view2131296673 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingplace\MenuEditPlaceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */