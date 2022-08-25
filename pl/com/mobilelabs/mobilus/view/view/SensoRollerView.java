package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;

public class SensoRollerView extends SensoAbstractRollerView {
  private SensoRollerView(Context paramContext) {
    super(paramContext);
  }
  
  public static SensoRollerView build(Context paramContext) {
    SensoRollerView sensoRollerView = new SensoRollerView(paramContext);
    sensoRollerView.onFinishInflate();
    return sensoRollerView;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SensoRollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */