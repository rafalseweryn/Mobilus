package android.support.design.expandable;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewParent;

public final class ExpandableWidgetHelper {
  private boolean expanded = false;
  
  @IdRes
  private int expandedComponentIdHint = 0;
  
  private final View widget;
  
  public ExpandableWidgetHelper(ExpandableWidget paramExpandableWidget) {
    this.widget = (View)paramExpandableWidget;
  }
  
  private void dispatchExpandedStateChanged() {
    ViewParent viewParent = this.widget.getParent();
    if (viewParent instanceof CoordinatorLayout)
      ((CoordinatorLayout)viewParent).dispatchDependentViewsChanged(this.widget); 
  }
  
  @IdRes
  public int getExpandedComponentIdHint() {
    return this.expandedComponentIdHint;
  }
  
  public boolean isExpanded() {
    return this.expanded;
  }
  
  public void onRestoreInstanceState(Bundle paramBundle) {
    this.expanded = paramBundle.getBoolean("expanded", false);
    this.expandedComponentIdHint = paramBundle.getInt("expandedComponentIdHint", 0);
    if (this.expanded)
      dispatchExpandedStateChanged(); 
  }
  
  public Bundle onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putBoolean("expanded", this.expanded);
    bundle.putInt("expandedComponentIdHint", this.expandedComponentIdHint);
    return bundle;
  }
  
  public boolean setExpanded(boolean paramBoolean) {
    if (this.expanded != paramBoolean) {
      this.expanded = paramBoolean;
      dispatchExpandedStateChanged();
      return true;
    } 
    return false;
  }
  
  public void setExpandedComponentIdHint(@IdRes int paramInt) {
    this.expandedComponentIdHint = paramInt;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\expandable\ExpandableWidgetHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */