package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;

public class ConnectionStatusView extends RelativeLayout {
  private boolean alreadyInflated = false;
  
  @BindView(2131296832)
  ImageView cloudOffImage;
  
  @BindView(2131296833)
  ImageView cloudOnImage;
  
  @BindView(2131296834)
  ImageView demoImage;
  
  @BindView(2131296835)
  ImageView wifiOffImage;
  
  @BindView(2131296836)
  ImageView wifiOnImage;
  
  public ConnectionStatusView(Context paramContext) {
    super(paramContext);
  }
  
  public ConnectionStatusView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public void bind(ConnectionStatus paramConnectionStatus) {
    boolean bool;
    if (this.cloudOnImage.getVisibility() == 0 || this.cloudOffImage.getVisibility() == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.cloudOnImage.setVisibility(4);
    this.cloudOffImage.setVisibility(4);
    this.wifiOnImage.setVisibility(4);
    this.wifiOffImage.setVisibility(4);
    this.demoImage.setVisibility(4);
    switch (paramConnectionStatus) {
      default:
        return;
      case DEMO_MODE:
        this.demoImage.setVisibility(0);
      case REMOTE_CONNECTION:
        this.cloudOnImage.setVisibility(0);
      case LOCAL_CONNECTION:
        this.wifiOnImage.setVisibility(0);
      case NO_CONNECTION:
        break;
    } 
    if (bool)
      this.cloudOffImage.setVisibility(0); 
    this.wifiOffImage.setVisibility(0);
  }
  
  public void onFinishInflate() {
    if (!this.alreadyInflated) {
      this.alreadyInflated = true;
      inflate(getContext(), 2131492971, (ViewGroup)this);
      ButterKnife.bind((View)this);
    } 
    super.onFinishInflate();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\ConnectionStatusView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */