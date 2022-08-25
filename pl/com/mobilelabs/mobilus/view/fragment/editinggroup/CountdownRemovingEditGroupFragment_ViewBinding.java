package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class CountdownRemovingEditGroupFragment_ViewBinding implements Unbinder {
  private CountdownRemovingEditGroupFragment target;
  
  private View view2131296528;
  
  @UiThread
  public CountdownRemovingEditGroupFragment_ViewBinding(final CountdownRemovingEditGroupFragment target, View paramView) {
    this.target = target;
    target.groupName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296527, "field 'groupName'", TextView.class);
    target.groupIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296525, "field 'groupIcon'", ImageView.class);
    View view = Utils.findRequiredView(paramView, 2131296528, "field 'nextButton' and method 'nextClicked'");
    target.nextButton = (RelativeLayout)Utils.castView(view, 2131296528, "field 'nextButton'", RelativeLayout.class);
    this.view2131296528 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.nextClicked();
          }
        });
    target.nextButtonTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296529, "field 'nextButtonTitle'", TextView.class);
    target.progressBar = (ContentLoadingProgressBar)Utils.findRequiredViewAsType(paramView, 2131296501, "field 'progressBar'", ContentLoadingProgressBar.class);
    target.secondsLeft = (TextView)Utils.findRequiredViewAsType(paramView, 2131296753, "field 'secondsLeft'", TextView.class);
    target.message = (TextView)Utils.findRequiredViewAsType(paramView, 2131296526, "field 'message'", TextView.class);
  }
  
  @CallSuper
  public void unbind() {
    CountdownRemovingEditGroupFragment countdownRemovingEditGroupFragment = this.target;
    if (countdownRemovingEditGroupFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    countdownRemovingEditGroupFragment.groupName = null;
    countdownRemovingEditGroupFragment.groupIcon = null;
    countdownRemovingEditGroupFragment.nextButton = null;
    countdownRemovingEditGroupFragment.nextButtonTitle = null;
    countdownRemovingEditGroupFragment.progressBar = null;
    countdownRemovingEditGroupFragment.secondsLeft = null;
    countdownRemovingEditGroupFragment.message = null;
    this.view2131296528.setOnClickListener(null);
    this.view2131296528 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\CountdownRemovingEditGroupFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */