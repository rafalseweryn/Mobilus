package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SpacetimeChangeSettingsActivity_ViewBinding implements Unbinder {
  private SpacetimeChangeSettingsActivity target;
  
  private View view2131296276;
  
  private View view2131296277;
  
  private View view2131296278;
  
  private View view2131296283;
  
  @UiThread
  public SpacetimeChangeSettingsActivity_ViewBinding(SpacetimeChangeSettingsActivity paramSpacetimeChangeSettingsActivity) {
    this(paramSpacetimeChangeSettingsActivity, paramSpacetimeChangeSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public SpacetimeChangeSettingsActivity_ViewBinding(final SpacetimeChangeSettingsActivity target, View paramView) {
    this.target = target;
    target.scrollView = (ScrollView)Utils.findRequiredViewAsType(paramView, 2131296282, "field 'scrollView'", ScrollView.class);
    View view = Utils.findRequiredView(paramView, 2131296278, "field 'dateTextView' and method 'showDatePickerDialogButtonClicked'");
    target.dateTextView = (TextView)Utils.castView(view, 2131296278, "field 'dateTextView'", TextView.class);
    this.view2131296278 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.showDatePickerDialogButtonClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296283, "field 'timeTextView' and method 'showTimePickerDialogButtonClicked'");
    target.timeTextView = (TextView)Utils.castView(view, 2131296283, "field 'timeTextView'", TextView.class);
    this.view2131296283 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.showTimePickerDialogButtonClicked();
          }
        });
    target.latitudeTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296279, "field 'latitudeTextView'", TextView.class);
    target.longitudeTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296280, "field 'longitudeTextView'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296276, "method 'spacetimeApplyClicked'");
    this.view2131296276 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.spacetimeApplyClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296277, "method 'spacetimeCancelClicked'");
    this.view2131296277 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.spacetimeCancelClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity = this.target;
    if (spacetimeChangeSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    spacetimeChangeSettingsActivity.scrollView = null;
    spacetimeChangeSettingsActivity.dateTextView = null;
    spacetimeChangeSettingsActivity.timeTextView = null;
    spacetimeChangeSettingsActivity.latitudeTextView = null;
    spacetimeChangeSettingsActivity.longitudeTextView = null;
    this.view2131296278.setOnClickListener(null);
    this.view2131296278 = null;
    this.view2131296283.setOnClickListener(null);
    this.view2131296283 = null;
    this.view2131296276.setOnClickListener(null);
    this.view2131296276 = null;
    this.view2131296277.setOnClickListener(null);
    this.view2131296277 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SpacetimeChangeSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */