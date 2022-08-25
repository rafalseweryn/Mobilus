package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public abstract class RollerView extends DeviceView {
  private static final String TAG = "RollerLayout";
  
  protected boolean alreadyInflated = false;
  
  protected RollerView(Context paramContext) {
    super(paramContext);
  }
  
  public void onFinishInflate() {
    if (!this.alreadyInflated) {
      this.alreadyInflated = true;
      inflate(getContext(), 2131492975, (ViewGroup)this);
      ButterKnife.bind((View)this);
    } 
    super.onFinishInflate();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\RollerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */