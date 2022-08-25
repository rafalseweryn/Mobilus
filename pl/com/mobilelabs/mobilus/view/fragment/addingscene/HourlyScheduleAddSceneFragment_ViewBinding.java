package pl.com.mobilelabs.mobilus.view.fragment.addingscene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class HourlyScheduleAddSceneFragment_ViewBinding implements Unbinder {
  private HourlyScheduleAddSceneFragment target;
  
  private View view2131296600;
  
  private View view2131296607;
  
  private View view2131296619;
  
  @UiThread
  public HourlyScheduleAddSceneFragment_ViewBinding(final HourlyScheduleAddSceneFragment target, View paramView) {
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
    HourlyScheduleAddSceneFragment hourlyScheduleAddSceneFragment = this.target;
    if (hourlyScheduleAddSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    hourlyScheduleAddSceneFragment.sceneName = null;
    hourlyScheduleAddSceneFragment.sceneIcon = null;
    hourlyScheduleAddSceneFragment.subjectIcon = null;
    hourlyScheduleAddSceneFragment.subjectTitle = null;
    hourlyScheduleAddSceneFragment.sundayButton = null;
    hourlyScheduleAddSceneFragment.mondayButton = null;
    hourlyScheduleAddSceneFragment.tuesdayButton = null;
    hourlyScheduleAddSceneFragment.wednesdayButton = null;
    hourlyScheduleAddSceneFragment.thursdayButton = null;
    hourlyScheduleAddSceneFragment.fridayButton = null;
    hourlyScheduleAddSceneFragment.saturdayButton = null;
    hourlyScheduleAddSceneFragment.hourTextView = null;
    hourlyScheduleAddSceneFragment.minuteTextView = null;
    this.view2131296607.setOnClickListener(null);
    this.view2131296607 = null;
    this.view2131296600.setOnClickListener(null);
    this.view2131296600 = null;
    this.view2131296619.setOnClickListener(null);
    this.view2131296619 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingscene\HourlyScheduleAddSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */