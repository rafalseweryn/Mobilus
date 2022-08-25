package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class ConnectionStatusView_ViewBinding implements Unbinder {
  private ConnectionStatusView target;
  
  @UiThread
  public ConnectionStatusView_ViewBinding(ConnectionStatusView paramConnectionStatusView) {
    this(paramConnectionStatusView, (View)paramConnectionStatusView);
  }
  
  @UiThread
  public ConnectionStatusView_ViewBinding(ConnectionStatusView paramConnectionStatusView, View paramView) {
    this.target = paramConnectionStatusView;
    paramConnectionStatusView.cloudOnImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296833, "field 'cloudOnImage'", ImageView.class);
    paramConnectionStatusView.cloudOffImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296832, "field 'cloudOffImage'", ImageView.class);
    paramConnectionStatusView.wifiOnImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296836, "field 'wifiOnImage'", ImageView.class);
    paramConnectionStatusView.wifiOffImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296835, "field 'wifiOffImage'", ImageView.class);
    paramConnectionStatusView.demoImage = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296834, "field 'demoImage'", ImageView.class);
  }
  
  @CallSuper
  public void unbind() {
    ConnectionStatusView connectionStatusView = this.target;
    if (connectionStatusView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    connectionStatusView.cloudOnImage = null;
    connectionStatusView.cloudOffImage = null;
    connectionStatusView.wifiOnImage = null;
    connectionStatusView.wifiOffImage = null;
    connectionStatusView.demoImage = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\ConnectionStatusView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */