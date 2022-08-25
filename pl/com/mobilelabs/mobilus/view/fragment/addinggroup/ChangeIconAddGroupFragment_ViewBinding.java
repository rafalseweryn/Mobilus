package pl.com.mobilelabs.mobilus.view.fragment.addinggroup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class ChangeIconAddGroupFragment_ViewBinding implements Unbinder {
  private ChangeIconAddGroupFragment target;
  
  private View view2131296496;
  
  @UiThread
  public ChangeIconAddGroupFragment_ViewBinding(final ChangeIconAddGroupFragment target, View paramView) {
    this.target = target;
    target.groupName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296495, "field 'groupName'", TextView.class);
    target.groupIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296493, "field 'groupIcon'", ImageView.class);
    target.nextButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296497, "field 'nextButtonTitle'", TextView.class);
    target.recyclerView = (RecyclerView)Utils.findRequiredViewAsType(paramView, 2131296711, "field 'recyclerView'", RecyclerView.class);
    paramView = Utils.findRequiredView(paramView, 2131296496, "method 'saveButtonClicked'");
    this.view2131296496 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    ChangeIconAddGroupFragment changeIconAddGroupFragment = this.target;
    if (changeIconAddGroupFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    changeIconAddGroupFragment.groupName = null;
    changeIconAddGroupFragment.groupIcon = null;
    changeIconAddGroupFragment.nextButtonTitle = null;
    changeIconAddGroupFragment.recyclerView = null;
    this.view2131296496.setOnClickListener(null);
    this.view2131296496 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addinggroup\ChangeIconAddGroupFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */