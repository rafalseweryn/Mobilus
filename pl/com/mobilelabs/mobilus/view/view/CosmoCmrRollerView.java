package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;

public class CosmoCmrRollerView extends CosmoCmrAbstractRollerView {
  private CosmoCmrRollerView(Context paramContext) {
    super(paramContext);
  }
  
  public static CosmoCmrRollerView build(Context paramContext) {
    CosmoCmrRollerView cosmoCmrRollerView = new CosmoCmrRollerView(paramContext);
    cosmoCmrRollerView.onFinishInflate();
    return cosmoCmrRollerView;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\CosmoCmrRollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */