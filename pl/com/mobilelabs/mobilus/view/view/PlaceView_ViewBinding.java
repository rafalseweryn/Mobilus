package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class PlaceView_ViewBinding implements Unbinder {
  private PlaceView target;
  
  private View view2131296563;
  
  @UiThread
  public PlaceView_ViewBinding(PlaceView paramPlaceView) {
    this(paramPlaceView, (View)paramPlaceView);
  }
  
  @UiThread
  public PlaceView_ViewBinding(final PlaceView target, View paramView) {
    this.target = target;
    target.nameTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296838, "field 'nameTextView'", TextView.class);
    target.editLayout = (RelativeLayout)Utils.findRequiredViewAsType(paramView, 2131296564, "field 'editLayout'", RelativeLayout.class);
    paramView = Utils.findRequiredView(paramView, 2131296563, "method 'editButtonClicked'");
    this.view2131296563 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.editButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    PlaceView placeView = this.target;
    if (placeView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    placeView.nameTextView = null;
    placeView.editLayout = null;
    this.view2131296563.setOnClickListener(null);
    this.view2131296563 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\PlaceView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */