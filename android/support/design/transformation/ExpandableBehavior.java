package android.support.design.transformation;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.expandable.ExpandableWidget;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.List;

public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {
  private static final int STATE_COLLAPSED = 2;
  
  private static final int STATE_EXPANDED = 1;
  
  private static final int STATE_UNINITIALIZED = 0;
  
  private int currentState = 0;
  
  public ExpandableBehavior() {}
  
  public ExpandableBehavior(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  private boolean didStateChange(boolean paramBoolean) {
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramBoolean) {
      if (this.currentState != 0) {
        paramBoolean = bool2;
        return (this.currentState == 2) ? true : paramBoolean;
      } 
    } else {
      paramBoolean = bool1;
      if (this.currentState == 1)
        paramBoolean = true; 
      return paramBoolean;
    } 
    return true;
  }
  
  public static <T extends ExpandableBehavior> T from(View paramView, Class<T> paramClass) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (!(layoutParams instanceof CoordinatorLayout.LayoutParams))
      throw new IllegalArgumentException("The view is not a child of CoordinatorLayout"); 
    CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)layoutParams).getBehavior();
    if (!(behavior instanceof ExpandableBehavior))
      throw new IllegalArgumentException("The view is not associated with ExpandableBehavior"); 
    return paramClass.cast(behavior);
  }
  
  @Nullable
  protected ExpandableWidget findExpandableWidget(CoordinatorLayout paramCoordinatorLayout, View paramView) {
    List<View> list = paramCoordinatorLayout.getDependencies(paramView);
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      View view = list.get(b);
      if (layoutDependsOn(paramCoordinatorLayout, paramView, view))
        return (ExpandableWidget)view; 
    } 
    return null;
  }
  
  public abstract boolean layoutDependsOn(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2);
  
  @CallSuper
  public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2) {
    ExpandableWidget expandableWidget = (ExpandableWidget)paramView2;
    if (didStateChange(expandableWidget.isExpanded())) {
      byte b;
      if (expandableWidget.isExpanded()) {
        b = 1;
      } else {
        b = 2;
      } 
      this.currentState = b;
      return onExpandedStateChange((View)expandableWidget, paramView1, expandableWidget.isExpanded(), true);
    } 
    return false;
  }
  
  protected abstract boolean onExpandedStateChange(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2);
  
  @CallSuper
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, final View child, final int expectedState) {
    if (!ViewCompat.isLaidOut(child)) {
      final ExpandableWidget dep = findExpandableWidget(paramCoordinatorLayout, child);
      if (expandableWidget != null && didStateChange(expandableWidget.isExpanded())) {
        if (expandableWidget.isExpanded()) {
          expectedState = 1;
        } else {
          expectedState = 2;
        } 
        this.currentState = expectedState;
        expectedState = this.currentState;
        child.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
              public boolean onPreDraw() {
                child.getViewTreeObserver().removeOnPreDrawListener(this);
                if (ExpandableBehavior.this.currentState == expectedState)
                  ExpandableBehavior.this.onExpandedStateChange((View)dep, child, dep.isExpanded(), false); 
                return false;
              }
            });
      } 
    } 
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\transformation\ExpandableBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */