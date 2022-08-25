package pl.com.mobilelabs.mobilus.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import pl.com.mobilelabs.mobilus.view.view.DeviceView;

public class DeviceGroupPlaceSceneViewHolder extends RecyclerView.ViewHolder implements DeviceView.IFavouriteLongClickedListener {
  private IChangeViewOrderListener changeListener;
  
  private View view;
  
  public DeviceGroupPlaceSceneViewHolder(View paramView) {
    super(paramView);
    this.view = paramView;
  }
  
  public void favouriteLongClicked() {
    if (this.changeListener != null)
      this.changeListener.startChange(this); 
  }
  
  public View getView() {
    return this.view;
  }
  
  public void setChangeListener(IChangeViewOrderListener paramIChangeViewOrderListener) {
    this.changeListener = paramIChangeViewOrderListener;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\adapter\DeviceGroupPlaceSceneViewHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */