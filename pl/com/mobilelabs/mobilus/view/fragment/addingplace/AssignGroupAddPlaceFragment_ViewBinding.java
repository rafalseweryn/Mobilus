package pl.com.mobilelabs.mobilus.view.fragment.addingplace;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AssignGroupAddPlaceFragment_ViewBinding implements Unbinder {
  private AssignGroupAddPlaceFragment target;
  
  private View view2131296422;
  
  @UiThread
  public AssignGroupAddPlaceFragment_ViewBinding(final AssignGroupAddPlaceFragment target, View paramView) {
    this.target = target;
    target.placeName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296420, "field 'placeName'", TextView.class);
    target.placeIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296418, "field 'placeIcon'", ImageView.class);
    target.groupsListTable = (TableLayout)Utils.findRequiredViewAsType(paramView, 2131296421, "field 'groupsListTable'", TableLayout.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296426, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296427, "field 'subjectTitle'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296422, "method 'saveButtonClicked'");
    this.view2131296422 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    AssignGroupAddPlaceFragment assignGroupAddPlaceFragment = this.target;
    if (assignGroupAddPlaceFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    assignGroupAddPlaceFragment.placeName = null;
    assignGroupAddPlaceFragment.placeIcon = null;
    assignGroupAddPlaceFragment.groupsListTable = null;
    assignGroupAddPlaceFragment.subjectIcon = null;
    assignGroupAddPlaceFragment.subjectTitle = null;
    this.view2131296422.setOnClickListener(null);
    this.view2131296422 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingplace\AssignGroupAddPlaceFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */