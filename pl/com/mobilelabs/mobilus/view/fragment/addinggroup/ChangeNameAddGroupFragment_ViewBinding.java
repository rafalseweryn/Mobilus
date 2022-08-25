package pl.com.mobilelabs.mobilus.view.fragment.addinggroup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class ChangeNameAddGroupFragment_ViewBinding implements Unbinder {
  private ChangeNameAddGroupFragment target;
  
  private View view2131296486;
  
  @UiThread
  public ChangeNameAddGroupFragment_ViewBinding(final ChangeNameAddGroupFragment target, View paramView) {
    this.target = target;
    target.groupName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296483, "field 'groupName'", TextView.class);
    target.groupIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296481, "field 'groupIcon'", ImageView.class);
    target.nameEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296485, "field 'nameEditText'", TextInputEditText.class);
    target.nextButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296487, "field 'nextButtonTitle'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296486, "method 'saveButtonClicked'");
    this.view2131296486 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    ChangeNameAddGroupFragment changeNameAddGroupFragment = this.target;
    if (changeNameAddGroupFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    changeNameAddGroupFragment.groupName = null;
    changeNameAddGroupFragment.groupIcon = null;
    changeNameAddGroupFragment.nameEditText = null;
    changeNameAddGroupFragment.nextButtonTitle = null;
    this.view2131296486.setOnClickListener(null);
    this.view2131296486 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addinggroup\ChangeNameAddGroupFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */