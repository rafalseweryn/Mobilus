package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;

public class CzrMzrRollerView extends SensoZRollerView {
  protected CzrMzrRollerView(Context paramContext) {
    super(paramContext);
  }
  
  public static CzrMzrRollerView build(Context paramContext) {
    CzrMzrRollerView czrMzrRollerView = new CzrMzrRollerView(paramContext);
    czrMzrRollerView.onFinishInflate();
    return czrMzrRollerView;
  }
  
  public void bind(BaseObject paramBaseObject) {
    super.bind(paramBaseObject);
    this.rollerLayout.setVisibility(4);
    this.rollerSlider.setOnClickListener(null);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CzrMzrRollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */