package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;

public abstract class DeviceStateView extends RelativeLayout {
  private boolean alreadyInflated = false;
  
  @BindView(2131296556)
  protected ImageView deviceStateImageView;
  
  @BindView(2131296558)
  protected ProgressBar deviceWorkingProgressBar;
  
  public DeviceStateView(Context paramContext) {
    super(paramContext);
  }
  
  public DeviceStateView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public void bind(int paramInt, State paramState) {
    this.deviceStateImageView.setImageResource(paramInt);
    if (paramState != null)
      if (paramState instanceof pl.com.mobilelabs.mobilus.model.Error) {
        this.deviceWorkingProgressBar.setVisibility(4);
      } else {
        setWorking(((Reading)paramState).isWorking());
      }  
  }
  
  public void onFinishInflate() {
    if (!this.alreadyInflated) {
      this.alreadyInflated = true;
      inflate(getContext(), 2131492973, (ViewGroup)this);
      ButterKnife.bind((View)this);
    } 
    super.onFinishInflate();
  }
  
  public void setWorking(boolean paramBoolean) {
    if (paramBoolean) {
      this.deviceWorkingProgressBar.setVisibility(0);
    } else {
      this.deviceWorkingProgressBar.setVisibility(4);
    } 
  }
  
  public abstract void showGroupIcon();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\DeviceStateView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */