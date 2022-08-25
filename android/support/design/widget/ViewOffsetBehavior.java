package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
  private int tempLeftRightOffset = 0;
  
  private int tempTopBottomOffset = 0;
  
  private ViewOffsetHelper viewOffsetHelper;
  
  public ViewOffsetBehavior() {}
  
  public ViewOffsetBehavior(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public int getLeftAndRightOffset() {
    boolean bool;
    if (this.viewOffsetHelper != null) {
      bool = this.viewOffsetHelper.getLeftAndRightOffset();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getTopAndBottomOffset() {
    boolean bool;
    if (this.viewOffsetHelper != null) {
      bool = this.viewOffsetHelper.getTopAndBottomOffset();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected void layoutChild(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt) {
    paramCoordinatorLayout.onLayoutChild((View)paramV, paramInt);
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt) {
    layoutChild(paramCoordinatorLayout, paramV, paramInt);
    if (this.viewOffsetHelper == null)
      this.viewOffsetHelper = new ViewOffsetHelper((View)paramV); 
    this.viewOffsetHelper.onViewLayout();
    if (this.tempTopBottomOffset != 0) {
      this.viewOffsetHelper.setTopAndBottomOffset(this.tempTopBottomOffset);
      this.tempTopBottomOffset = 0;
    } 
    if (this.tempLeftRightOffset != 0) {
      this.viewOffsetHelper.setLeftAndRightOffset(this.tempLeftRightOffset);
      this.tempLeftRightOffset = 0;
    } 
    return true;
  }
  
  public boolean setLeftAndRightOffset(int paramInt) {
    if (this.viewOffsetHelper != null)
      return this.viewOffsetHelper.setLeftAndRightOffset(paramInt); 
    this.tempLeftRightOffset = paramInt;
    return false;
  }
  
  public boolean setTopAndBottomOffset(int paramInt) {
    if (this.viewOffsetHelper != null)
      return this.viewOffsetHelper.setTopAndBottomOffset(paramInt); 
    this.tempTopBottomOffset = paramInt;
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\ViewOffsetBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */