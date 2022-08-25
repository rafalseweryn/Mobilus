package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class CgrRollerGroupView_ViewBinding extends CgrRollerView_ViewBinding {
  private CgrRollerGroupView target;
  
  private View view2131296716;
  
  private View view2131296725;
  
  @UiThread
  public CgrRollerGroupView_ViewBinding(CgrRollerGroupView paramCgrRollerGroupView) {
    this(paramCgrRollerGroupView, (View)paramCgrRollerGroupView);
  }
  
  @UiThread
  public CgrRollerGroupView_ViewBinding(final CgrRollerGroupView target, View paramView) {
    super(target, paramView);
    this.target = target;
    View view = Utils.findRequiredView(paramView, 2131296725, "method 'onUpButtonClicked'");
    this.view2131296725 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onUpButtonClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296716, "method 'onStopButtonClicked'");
    this.view2131296716 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onStopButtonClicked();
          }
        });
  }
  
  public void unbind() {
    if (this.target == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    this.view2131296725.setOnClickListener(null);
    this.view2131296725 = null;
    this.view2131296716.setOnClickListener(null);
    this.view2131296716 = null;
    super.unbind();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CgrRollerGroupView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */