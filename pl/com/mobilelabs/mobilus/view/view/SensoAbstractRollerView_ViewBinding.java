package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SensoAbstractRollerView_ViewBinding extends DeviceView_ViewBinding {
  private SensoAbstractRollerView target;
  
  private View view2131296716;
  
  private View view2131296722;
  
  private View view2131296725;
  
  @UiThread
  public SensoAbstractRollerView_ViewBinding(SensoAbstractRollerView paramSensoAbstractRollerView) {
    this(paramSensoAbstractRollerView, (View)paramSensoAbstractRollerView);
  }
  
  @UiThread
  public SensoAbstractRollerView_ViewBinding(final SensoAbstractRollerView target, View paramView) {
    super(target, paramView);
    this.target = target;
    target.rollerLayout = (LinearLayout)Utils.findRequiredViewAsType(paramView, 2131296718, "field 'rollerLayout'", LinearLayout.class);
    target.rollerSlider = (SeekBar)Utils.findRequiredViewAsType(paramView, 2131296721, "field 'rollerSlider'", SeekBar.class);
    target.positionText = (TextView)Utils.findRequiredViewAsType(paramView, 2131296720, "field 'positionText'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296725, "method 'onUpButtonClicked'");
    this.view2131296725 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onUpButtonClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296716, "method 'onDownButtonClicked'");
    this.view2131296716 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onDownButtonClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296722, "method 'onStopButtonClicked'");
    this.view2131296722 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onStopButtonClicked();
          }
        });
  }
  
  public void unbind() {
    SensoAbstractRollerView sensoAbstractRollerView = this.target;
    if (sensoAbstractRollerView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    sensoAbstractRollerView.rollerLayout = null;
    sensoAbstractRollerView.rollerSlider = null;
    sensoAbstractRollerView.positionText = null;
    this.view2131296725.setOnClickListener(null);
    this.view2131296725 = null;
    this.view2131296716.setOnClickListener(null);
    this.view2131296716 = null;
    this.view2131296722.setOnClickListener(null);
    this.view2131296722 = null;
    super.unbind();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SensoAbstractRollerView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */