package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AstralScheduleEditSceneFragment_ViewBinding implements Unbinder {
  private AstralScheduleEditSceneFragment target;
  
  private View view2131296430;
  
  private View view2131296433;
  
  private View view2131296445;
  
  private View view2131296446;
  
  private View view2131296447;
  
  private View view2131296448;
  
  private View view2131296449;
  
  private View view2131296451;
  
  private View view2131296452;
  
  private View view2131296453;
  
  private View view2131296454;
  
  private View view2131296455;
  
  @UiThread
  public AstralScheduleEditSceneFragment_ViewBinding(final AstralScheduleEditSceneFragment target, View paramView) {
    this.target = target;
    target.sceneName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296439, "field 'sceneName'", TextView.class);
    target.sceneIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296437, "field 'sceneIcon'", ImageView.class);
    target.subjectIcon = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296441, "field 'subjectIcon'", ImageView.class);
    target.subjectTitle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296442, "field 'subjectTitle'", TextView.class);
    target.sundayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296443, "field 'sundayButton'", AppCompatCheckBox.class);
    target.mondayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296431, "field 'mondayButton'", AppCompatCheckBox.class);
    target.tuesdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296457, "field 'tuesdayButton'", AppCompatCheckBox.class);
    target.wednesdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296458, "field 'wednesdayButton'", AppCompatCheckBox.class);
    target.thursdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296456, "field 'thursdayButton'", AppCompatCheckBox.class);
    target.fridayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296429, "field 'fridayButton'", AppCompatCheckBox.class);
    target.saturdayButton = (AppCompatCheckBox)Utils.findRequiredViewAsType(paramView, 2131296432, "field 'saturdayButton'", AppCompatCheckBox.class);
    View view = Utils.findRequiredView(paramView, 2131296446, "field 'sunriseMinus30' and method 'sunriseMinus30Clicked'");
    target.sunriseMinus30 = (AppCompatCheckBox)Utils.castView(view, 2131296446, "field 'sunriseMinus30'", AppCompatCheckBox.class);
    this.view2131296446 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunriseMinus30Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296445, "field 'sunriseMinus15' and method 'sunriseMinus15Clicked'");
    target.sunriseMinus15 = (AppCompatCheckBox)Utils.castView(view, 2131296445, "field 'sunriseMinus15'", AppCompatCheckBox.class);
    this.view2131296445 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunriseMinus15Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296447, "field 'sunrisePlus15' and method 'sunrisePlus15Clicked'");
    target.sunrisePlus15 = (AppCompatCheckBox)Utils.castView(view, 2131296447, "field 'sunrisePlus15'", AppCompatCheckBox.class);
    this.view2131296447 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunrisePlus15Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296448, "field 'sunrisePlus30' and method 'sunrisePlus30Clicked'");
    target.sunrisePlus30 = (AppCompatCheckBox)Utils.castView(view, 2131296448, "field 'sunrisePlus30'", AppCompatCheckBox.class);
    this.view2131296448 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunrisePlus30Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296452, "field 'sunsetMinus30' and method 'sunsetMinus30Clicked'");
    target.sunsetMinus30 = (AppCompatCheckBox)Utils.castView(view, 2131296452, "field 'sunsetMinus30'", AppCompatCheckBox.class);
    this.view2131296452 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunsetMinus30Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296451, "field 'sunsetMinus15' and method 'sunsetMinus15Clicked'");
    target.sunsetMinus15 = (AppCompatCheckBox)Utils.castView(view, 2131296451, "field 'sunsetMinus15'", AppCompatCheckBox.class);
    this.view2131296451 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunsetMinus15Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296453, "field 'sunsetPlus15' and method 'sunsetPlus15Clicked'");
    target.sunsetPlus15 = (AppCompatCheckBox)Utils.castView(view, 2131296453, "field 'sunsetPlus15'", AppCompatCheckBox.class);
    this.view2131296453 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunsetPlus15Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296454, "field 'sunsetPlus30' and method 'sunsetPlus30Clicked'");
    target.sunsetPlus30 = (AppCompatCheckBox)Utils.castView(view, 2131296454, "field 'sunsetPlus30'", AppCompatCheckBox.class);
    this.view2131296454 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunsetPlus30Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296449, "field 'sunriseUnchecked' and method 'sunriseUncheckedClicked'");
    target.sunriseUnchecked = (RelativeLayout)Utils.castView(view, 2131296449, "field 'sunriseUnchecked'", RelativeLayout.class);
    this.view2131296449 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunriseUncheckedClicked();
          }
        });
    target.sunriseChecked = (RelativeLayout)Utils.findRequiredViewAsType(paramView, 2131296444, "field 'sunriseChecked'", RelativeLayout.class);
    view = Utils.findRequiredView(paramView, 2131296455, "field 'sunsetUnchecked' and method 'sunsetUncheckedClicked'");
    target.sunsetUnchecked = (RelativeLayout)Utils.castView(view, 2131296455, "field 'sunsetUnchecked'", RelativeLayout.class);
    this.view2131296455 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.sunsetUncheckedClicked();
          }
        });
    target.sunsetChecked = (RelativeLayout)Utils.findRequiredViewAsType(paramView, 2131296450, "field 'sunsetChecked'", RelativeLayout.class);
    view = Utils.findRequiredView(paramView, 2131296433, "method 'saveButtonClicked'");
    this.view2131296433 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.saveButtonClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296430, "method 'hourlyButtonClicked'");
    this.view2131296430 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.hourlyButtonClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    AstralScheduleEditSceneFragment astralScheduleEditSceneFragment = this.target;
    if (astralScheduleEditSceneFragment == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    astralScheduleEditSceneFragment.sceneName = null;
    astralScheduleEditSceneFragment.sceneIcon = null;
    astralScheduleEditSceneFragment.subjectIcon = null;
    astralScheduleEditSceneFragment.subjectTitle = null;
    astralScheduleEditSceneFragment.sundayButton = null;
    astralScheduleEditSceneFragment.mondayButton = null;
    astralScheduleEditSceneFragment.tuesdayButton = null;
    astralScheduleEditSceneFragment.wednesdayButton = null;
    astralScheduleEditSceneFragment.thursdayButton = null;
    astralScheduleEditSceneFragment.fridayButton = null;
    astralScheduleEditSceneFragment.saturdayButton = null;
    astralScheduleEditSceneFragment.sunriseMinus30 = null;
    astralScheduleEditSceneFragment.sunriseMinus15 = null;
    astralScheduleEditSceneFragment.sunrisePlus15 = null;
    astralScheduleEditSceneFragment.sunrisePlus30 = null;
    astralScheduleEditSceneFragment.sunsetMinus30 = null;
    astralScheduleEditSceneFragment.sunsetMinus15 = null;
    astralScheduleEditSceneFragment.sunsetPlus15 = null;
    astralScheduleEditSceneFragment.sunsetPlus30 = null;
    astralScheduleEditSceneFragment.sunriseUnchecked = null;
    astralScheduleEditSceneFragment.sunriseChecked = null;
    astralScheduleEditSceneFragment.sunsetUnchecked = null;
    astralScheduleEditSceneFragment.sunsetChecked = null;
    this.view2131296446.setOnClickListener(null);
    this.view2131296446 = null;
    this.view2131296445.setOnClickListener(null);
    this.view2131296445 = null;
    this.view2131296447.setOnClickListener(null);
    this.view2131296447 = null;
    this.view2131296448.setOnClickListener(null);
    this.view2131296448 = null;
    this.view2131296452.setOnClickListener(null);
    this.view2131296452 = null;
    this.view2131296451.setOnClickListener(null);
    this.view2131296451 = null;
    this.view2131296453.setOnClickListener(null);
    this.view2131296453 = null;
    this.view2131296454.setOnClickListener(null);
    this.view2131296454 = null;
    this.view2131296449.setOnClickListener(null);
    this.view2131296449 = null;
    this.view2131296455.setOnClickListener(null);
    this.view2131296455 = null;
    this.view2131296433.setOnClickListener(null);
    this.view2131296433 = null;
    this.view2131296430.setOnClickListener(null);
    this.view2131296430 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\AstralScheduleEditSceneFragment_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */