package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SpacetimeSettingsActivity_ViewBinding implements Unbinder {
  private SpacetimeSettingsActivity target;
  
  private View view2131296284;
  
  @UiThread
  public SpacetimeSettingsActivity_ViewBinding(SpacetimeSettingsActivity paramSpacetimeSettingsActivity) {
    this(paramSpacetimeSettingsActivity, paramSpacetimeSettingsActivity.getWindow().getDecorView());
  }
  
  @UiThread
  public SpacetimeSettingsActivity_ViewBinding(final SpacetimeSettingsActivity target, View paramView) {
    this.target = target;
    target.scrollView = (ScrollView)Utils.findRequiredViewAsType(paramView, 2131296289, "field 'scrollView'", ScrollView.class);
    target.dateTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296285, "field 'dateTextView'", TextView.class);
    target.timeTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296290, "field 'timeTextView'", TextView.class);
    target.latitudeTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296286, "field 'latitudeTextView'", TextView.class);
    target.longitudeTextView = (TextView)Utils.findRequiredViewAsType(paramView, 2131296287, "field 'longitudeTextView'", TextView.class);
    paramView = Utils.findRequiredView(paramView, 2131296284, "method 'spacetimeChangeClicked'");
    this.view2131296284 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          public void doClick(View param1View) {
            target.spacetimeChangeClicked();
          }
        });
  }
  
  @CallSuper
  public void unbind() {
    SpacetimeSettingsActivity spacetimeSettingsActivity = this.target;
    if (spacetimeSettingsActivity == null)
      throw new IllegalStateException("Bindings already cleared."); 
    this.target = null;
    spacetimeSettingsActivity.scrollView = null;
    spacetimeSettingsActivity.dateTextView = null;
    spacetimeSettingsActivity.timeTextView = null;
    spacetimeSettingsActivity.latitudeTextView = null;
    spacetimeSettingsActivity.longitudeTextView = null;
    this.view2131296284.setOnClickListener(null);
    this.view2131296284 = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SpacetimeSettingsActivity_ViewBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */