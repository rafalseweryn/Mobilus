package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class CgrRollerView_ViewBinding extends DeviceView_ViewBinding {
  private CgrRollerView target;
  
  private View view2131296722;
  
  private View view2131296725;
  
  @UiThread
  public CgrRollerView_ViewBinding(CgrRollerView paramCgrRollerView) {
    this(paramCgrRollerView, (View)paramCgrRollerView);
  }
  
  @UiThread
  public CgrRollerView_ViewBinding(final CgrRollerView target, View paramView) {
    super(target, paramView);
    this.target = target;
    target.rollerLayout = (LinearLayout)Utils.findRequiredViewAsType(paramView, 2131296718, "field 'rollerLayout'", LinearLayout.class);
    target.buttonContainer = (LinearLayout)Utils.findRequiredViewAsType(paramView, 2131296715, "field 'buttonContainer'", LinearLayout.class);
    target.rollerDownButton = (RelativeLayout)Utils.findRequiredViewAsType(paramView, 2131296716, "field 'rollerDownButton'", RelativeLayout.class);
    target.rollerDownButtonDivider = Utils.findRequiredView(paramView, 2131296717, "field 'rollerDownButtonDivider'");
    View view = Utils.findRequiredView(paramView, 2131296725, "field 'rollerUpButton' and method 'onUpButtonClicked'");
    target.rollerUpButton = (RelativeLayout)Utils.castView(view, 2131296725, "field 'rollerUpButton'", RelativeLayout.class);
    this.view2131296725 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onUpButtonClicked();
          }
        });
    target.rollerStopButtonDivider = Utils.findRequiredView(paramView, 2131296723, "field 'rollerStopButtonDivider'");
    target.rollerStopButtonImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296724, "field 'rollerStopButtonImage'", ImageView.class);
    paramView = Utils.findRequiredView(paramView, 2131296722, "method 'onStopButtonClicked'");
    this.view2131296722 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onStopButtonClicked();
          }
        });
  }
  
  public void unbind() {
    CgrRollerView cgrRollerView = this.target;
    if (cgrRollerView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    cgrRollerView.rollerLayout = null;
    cgrRollerView.buttonContainer = null;
    cgrRollerView.rollerDownButton = null;
    cgrRollerView.rollerDownButtonDivider = null;
    cgrRollerView.rollerUpButton = null;
    cgrRollerView.rollerStopButtonDivider = null;
    cgrRollerView.rollerStopButtonImage = null;
    this.view2131296725.setOnClickListener(null);
    this.view2131296725 = null;
    this.view2131296722.setOnClickListener(null);
    this.view2131296722 = null;
    super.unbind();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CgrRollerView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */