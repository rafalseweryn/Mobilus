package pl.com.mobilelabs.mobilus.view.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SceneView_ViewBinding implements Unbinder {
  private SceneView target;
  
  private View view2131296563;
  
  private View view2131296569;
  
  private View view2131296732;
  
  @UiThread
  public SceneView_ViewBinding(SceneView paramSceneView) {
    this(paramSceneView, (View)paramSceneView);
  }
  
  @UiThread
  public SceneView_ViewBinding(final SceneView target, View paramView) {
    this.target = target;
    target.nameTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296838, "field 'nameTextView'", TextView.class);
    target.sceneNextRunTableRow = (TableRow)Utils.findRequiredViewAsType(paramView, 2131296733, "field 'sceneNextRunTableRow'", TableRow.class);
    View view = Utils.findRequiredView(paramView, 2131296569, "field 'enabledSwitch' and method 'enabledSwitchClicked'");
    target.enabledSwitch = (SwitchCompat)Utils.castView(view, 2131296569, "field 'enabledSwitch'", SwitchCompat.class);
    this.view2131296569 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.enabledSwitchClicked();
          }
        });
    target.scenePlayButtonTableRow = (TableRow)Utils.findRequiredViewAsType(paramView, 2131296735, "field 'scenePlayButtonTableRow'", TableRow.class);
    target.scenePlayGreyDotsImageView = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296736, "field 'scenePlayGreyDotsImageView'", ImageView.class);
    target.scenePlayBlueDotsImageView = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296734, "field 'scenePlayBlueDotsImageView'", ImageView.class);
    view = Utils.findRequiredView(paramView, 2131296563, "field 'editButton' and method 'editButtonClicked'");
    target.editButton = (ImageView)Utils.castView(view, 2131296563, "field 'editButton'", ImageView.class);
    this.view2131296563 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.editButtonClicked();
          }
        });
    target.iconBlue = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296624, "field 'iconBlue'", ImageView.class);
    target.iconGray = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296625, "field 'iconGray'", ImageView.class);
    target.nextRunTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296837, "field 'nextRunTextView'", TextView.class);
    target.separator1 = (LinearLayout)Utils.findRequiredViewAsType(paramView, 2131296756, "field 'separator1'", LinearLayout.class);
    target.separator2 = (LinearLayout)Utils.findRequiredViewAsType(paramView, 2131296757, "field 'separator2'", LinearLayout.class);
    paramView = Utils.findRequiredView(paramView, 2131296732, "method 'onSceneButtonClicked'");
    this.view2131296732 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.onSceneButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    SceneView sceneView = this.target;
    if (sceneView == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    sceneView.nameTextView = null;
    sceneView.sceneNextRunTableRow = null;
    sceneView.enabledSwitch = null;
    sceneView.scenePlayButtonTableRow = null;
    sceneView.scenePlayGreyDotsImageView = null;
    sceneView.scenePlayBlueDotsImageView = null;
    sceneView.editButton = null;
    sceneView.iconBlue = null;
    sceneView.iconGray = null;
    sceneView.nextRunTextView = null;
    sceneView.separator1 = null;
    sceneView.separator2 = null;
    this.view2131296569.setOnClickListener(null);
    this.view2131296569 = null;
    this.view2131296563.setOnClickListener(null);
    this.view2131296563 = null;
    this.view2131296732.setOnClickListener(null);
    this.view2131296732 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SceneView_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */