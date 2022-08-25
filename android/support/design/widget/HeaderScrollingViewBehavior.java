package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.math.MathUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
  private int overlayTop;
  
  final Rect tempRect1 = new Rect();
  
  final Rect tempRect2 = new Rect();
  
  private int verticalLayoutGap = 0;
  
  public HeaderScrollingViewBehavior() {}
  
  public HeaderScrollingViewBehavior(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  private static int resolveGravity(int paramInt) {
    int i = paramInt;
    if (paramInt == 0)
      i = 8388659; 
    return i;
  }
  
  abstract View findFirstDependency(List<View> paramList);
  
  final int getOverlapPixelsForOffset(View paramView) {
    int i = this.overlayTop;
    int j = 0;
    if (i != 0)
      j = MathUtils.clamp((int)(getOverlapRatioForOffset(paramView) * this.overlayTop), 0, this.overlayTop); 
    return j;
  }
  
  float getOverlapRatioForOffset(View paramView) {
    return 1.0F;
  }
  
  public final int getOverlayTop() {
    return this.overlayTop;
  }
  
  int getScrollRange(View paramView) {
    return paramView.getMeasuredHeight();
  }
  
  final int getVerticalLayoutGap() {
    return this.verticalLayoutGap;
  }
  
  protected void layoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt) {
    Rect rect;
    View view = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
    if (view != null) {
      CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)paramView.getLayoutParams();
      Rect rect1 = this.tempRect1;
      rect1.set(paramCoordinatorLayout.getPaddingLeft() + layoutParams.leftMargin, view.getBottom() + layoutParams.topMargin, paramCoordinatorLayout.getWidth() - paramCoordinatorLayout.getPaddingRight() - layoutParams.rightMargin, paramCoordinatorLayout.getHeight() + view.getBottom() - paramCoordinatorLayout.getPaddingBottom() - layoutParams.bottomMargin);
      WindowInsetsCompat windowInsetsCompat = paramCoordinatorLayout.getLastWindowInsets();
      if (windowInsetsCompat != null && ViewCompat.getFitsSystemWindows((View)paramCoordinatorLayout) && !ViewCompat.getFitsSystemWindows(paramView)) {
        rect1.left += windowInsetsCompat.getSystemWindowInsetLeft();
        rect1.right -= windowInsetsCompat.getSystemWindowInsetRight();
      } 
      rect = this.tempRect2;
      GravityCompat.apply(resolveGravity(layoutParams.gravity), paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), rect1, rect, paramInt);
      paramInt = getOverlapPixelsForOffset(view);
      paramView.layout(rect.left, rect.top - paramInt, rect.right, rect.bottom - paramInt);
      this.verticalLayoutGap = rect.top - view.getBottom();
    } else {
      super.layoutChild((CoordinatorLayout)rect, paramView, paramInt);
      this.verticalLayoutGap = 0;
    } 
  }
  
  public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = (paramView.getLayoutParams()).height;
    if (i == -1 || i == -2) {
      View view = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
      if (view != null) {
        if (ViewCompat.getFitsSystemWindows(view) && !ViewCompat.getFitsSystemWindows(paramView)) {
          View view1 = paramView;
          ViewCompat.setFitsSystemWindows(view1, true);
          if (ViewCompat.getFitsSystemWindows(view1)) {
            view1.requestLayout();
            return true;
          } 
        } 
        int j = View.MeasureSpec.getSize(paramInt3);
        paramInt3 = j;
        if (j == 0)
          paramInt3 = paramCoordinatorLayout.getHeight(); 
        int k = view.getMeasuredHeight();
        int m = getScrollRange(view);
        if (i == -1) {
          j = 1073741824;
        } else {
          j = Integer.MIN_VALUE;
        } 
        paramCoordinatorLayout.onMeasureChild(paramView, paramInt1, paramInt2, View.MeasureSpec.makeMeasureSpec(paramInt3 - k + m, j), paramInt4);
        return true;
      } 
    } 
    return false;
  }
  
  public final void setOverlayTop(int paramInt) {
    this.overlayTop = paramInt;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\HeaderScrollingViewBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */