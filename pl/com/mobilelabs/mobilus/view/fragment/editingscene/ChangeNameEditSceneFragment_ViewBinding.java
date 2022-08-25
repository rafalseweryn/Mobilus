package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class ChangeNameEditSceneFragment_ViewBinding implements Unbinder {
  private ChangeNameEditSceneFragment target;
  
  private View view2131296486;
  
  @UiThread
  public ChangeNameEditSceneFragment_ViewBinding(final ChangeNameEditSceneFragment target, View paramView) {
    this.target = target;
    target.sceneName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296483, "field 'sceneName'", TextView.class);
    target.sceneIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296481, "field 'sceneIcon'", ImageView.class);
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
    ChangeNameEditSceneFragment changeNameEditSceneFragment = this.target;
    if (changeNameEditSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    changeNameEditSceneFragment.sceneName = null;
    changeNameEditSceneFragment.sceneIcon = null;
    changeNameEditSceneFragment.nameEditText = null;
    changeNameEditSceneFragment.saveButtonTitle = null;
    this.view2131296486.setOnClickListener(null);
    this.view2131296486 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\ChangeNameEditSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */