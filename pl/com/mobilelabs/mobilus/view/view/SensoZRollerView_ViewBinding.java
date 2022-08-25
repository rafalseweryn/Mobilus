package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.internal.Utils;

public class SensoZRollerView_ViewBinding extends SensoAbstractRollerView_ViewBinding {
  private SensoZRollerView target;
  
  @UiThread
  public SensoZRollerView_ViewBinding(SensoZRollerView paramSensoZRollerView) {
    this(paramSensoZRollerView, (View)paramSensoZRollerView);
  }
  
  @UiThread
  public SensoZRollerView_ViewBinding(SensoZRollerView paramSensoZRollerView, View paramView) {
    super(paramSensoZRollerView, paramView);
    this.target = paramSensoZRollerView;
    paramSensoZRollerView.leanLayout = (LinearLayout)Utils.findRequiredViewAsType(paramView, 2131296638, "field 'leanLayout'", LinearLayout.class);
    paramSensoZRollerView.leanSlider = (SeekBar)Utils.findRequiredViewAsType(paramView, 2131296639, "field 'leanSlider'", SeekBar.class);
    paramSensoZRollerView.leanImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296637, "field 'leanImage'", ImageView.class);
    paramSensoZRollerView.leanValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296719, "field 'leanValue'", TextView.class);
  }
  
  public void unbind() {
    SensoZRollerView sensoZRollerView = this.target;
    if (sensoZRollerView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    sensoZRollerView.leanLayout = null;
    sensoZRollerView.leanSlider = null;
    sensoZRollerView.leanImage = null;
    sensoZRollerView.leanValue = null;
    super.unbind();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SensoZRollerView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */