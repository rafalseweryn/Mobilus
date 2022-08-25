package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class ChangeNameEditGroupFragment_ViewBinding implements Unbinder {
  private ChangeNameEditGroupFragment target;
  
  private View view2131296486;
  
  @UiThread
  public ChangeNameEditGroupFragment_ViewBinding(final ChangeNameEditGroupFragment target, View paramView) {
    this.target = target;
    target.groupName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296483, "field 'groupName'", TextView.class);
    target.groupIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296481, "field 'groupIcon'", ImageView.class);
    target.nameEditText = (TextInputEditText)Utils.findRequiredViewAsType(paramView, 2131296485, "field 'nameEditText'", TextInputEditText.class);
    target.saveButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296487, "field 'saveButtonTitle'", TextView.class);
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
    ChangeNameEditGroupFragment changeNameEditGroupFragment = this.target;
    if (changeNameEditGroupFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    changeNameEditGroupFragment.groupName = null;
    changeNameEditGroupFragment.groupIcon = null;
    changeNameEditGroupFragment.nameEditText = null;
    changeNameEditGroupFragment.saveButtonTitle = null;
    this.view2131296486.setOnClickListener(null);
    this.view2131296486 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\ChangeNameEditGroupFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */