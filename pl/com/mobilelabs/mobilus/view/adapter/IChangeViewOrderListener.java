package pl.com.mobilelabs.mobilus.view.adapter;

import android.support.v7.widget.RecyclerView;

public interface IChangeViewOrderListener {
  void clearDevicesOrder();
  
  boolean devicesOrderChanged(int paramInt1, int paramInt2, String paramString);
  
  void startChange(RecyclerView.ViewHolder paramViewHolder);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\adapter\IChangeViewOrderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */