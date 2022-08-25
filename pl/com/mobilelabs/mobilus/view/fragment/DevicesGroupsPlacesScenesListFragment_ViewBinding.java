package pl.com.mobilelabs.mobilus.view.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class DevicesGroupsPlacesScenesListFragment_ViewBinding implements Unbinder {
  private DevicesGroupsPlacesScenesListFragment target;
  
  private View view2131296577;
  
  @UiThread
  public DevicesGroupsPlacesScenesListFragment_ViewBinding(final DevicesGroupsPlacesScenesListFragment target, View paramView) {
    this.target = target;
    target.mRecyclerView = (RecyclerView)Utils.findRequiredViewAsType(paramView, 2131296812, "field 'mRecyclerView'", RecyclerView.class);
    paramView = Utils.findRequiredView(paramView, 2131296577, "field 'floatingActionButton' and method 'floatingActionButtonClicked'");
    target.floatingActionButton = (FloatingActionButton)Utils.castView(paramView, 2131296577, "field 'floatingActionButton'", FloatingActionButton.class);
    this.view2131296577 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.floatingActionButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    DevicesGroupsPlacesScenesListFragment devicesGroupsPlacesScenesListFragment = this.target;
    if (devicesGroupsPlacesScenesListFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    devicesGroupsPlacesScenesListFragment.mRecyclerView = null;
    devicesGroupsPlacesScenesListFragment.floatingActionButton = null;
    this.view2131296577.setOnClickListener(null);
    this.view2131296577 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\DevicesGroupsPlacesScenesListFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */