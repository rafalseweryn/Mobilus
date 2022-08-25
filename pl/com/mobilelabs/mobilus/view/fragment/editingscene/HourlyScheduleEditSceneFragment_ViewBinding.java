package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class HourlyScheduleEditSceneFragment_ViewBinding implements Unbinder {
  private HourlyScheduleEditSceneFragment target;
  
  private View view2131296600;
  
  private View view2131296607;
  
  private View view2131296619;
  
  @UiThread
  public HourlyScheduleEditSceneFragment_ViewBinding(final HourlyScheduleEditSceneFragment target, View paramView) {
    this.target = target;
    target.sceneName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296613, "field 'sceneName'", TextView.class);
    target.sceneIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296611, "field 'sceneIcon'", ImageView.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296615, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296616, "field 'subjectTitle'", TextView.class);
    target.sundayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296617, "field 'sundayButton'", AppCompatCheckBox.class);
    target.mondayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296605, "field 'mondayButton'", AppCompatCheckBox.class);
    target.tuesdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296620, "field 'tuesdayButton'", AppCompatCheckBox.class);
    target.wednesdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296621, "field 'wednesdayButton'", AppCompatCheckBox.class);
    target.thursdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296618, "field 'thursdayButton'", AppCompatCheckBox.class);
    target.fridayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296601, "field 'fridayButton'", AppCompatCheckBox.class);
    target.saturdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296606, "field 'saturdayButton'", AppCompatCheckBox.class);
    target.hourTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296602, "field 'hourTextView'", TextView.class);
    target.minuteTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296604, "field 'minuteTextView'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296607, "method 'saveButtonClicked'");
    this.view2131296607 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296600, "method 'astralButtonClicked'");
    this.view2131296600 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.astralButtonClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296619, "method 'timeClicked'");
    this.view2131296619 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.timeClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    HourlyScheduleEditSceneFragment hourlyScheduleEditSceneFragment = this.target;
    if (hourlyScheduleEditSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    hourlyScheduleEditSceneFragment.sceneName = null;
    hourlyScheduleEditSceneFragment.sceneIcon = null;
    hourlyScheduleEditSceneFragment.subjectIcon = null;
    hourlyScheduleEditSceneFragment.subjectTitle = null;
    hourlyScheduleEditSceneFragment.sundayButton = null;
    hourlyScheduleEditSceneFragment.mondayButton = null;
    hourlyScheduleEditSceneFragment.tuesdayButton = null;
    hourlyScheduleEditSceneFragment.wednesdayButton = null;
    hourlyScheduleEditSceneFragment.thursdayButton = null;
    hourlyScheduleEditSceneFragment.fridayButton = null;
    hourlyScheduleEditSceneFragment.saturdayButton = null;
    hourlyScheduleEditSceneFragment.hourTextView = null;
    hourlyScheduleEditSceneFragment.minuteTextView = null;
    this.view2131296607.setOnClickListener(null);
    this.view2131296607 = null;
    this.view2131296600.setOnClickListener(null);
    this.view2131296600 = null;
    this.view2131296619.setOnClickListener(null);
    this.view2131296619 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\HourlyScheduleEditSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */