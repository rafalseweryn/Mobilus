package android.support.design.shape;

import android.support.design.internal.Experimental;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

@Experimental("The shapes API is currently experimental and subject to change")
public class InterpolateOnScrollPositionChangeHelper {
  private final int[] containerLocation = new int[2];
  
  private ScrollView containingScrollView;
  
  private MaterialShapeDrawable materialShapeDrawable;
  
  private final ViewTreeObserver.OnScrollChangedListener scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
      public void onScrollChanged() {
        InterpolateOnScrollPositionChangeHelper.this.updateInterpolationForScreenPosition();
      }
    };
  
  private final int[] scrollLocation = new int[2];
  
  private View shapedView;
  
  public InterpolateOnScrollPositionChangeHelper(View paramView, MaterialShapeDrawable paramMaterialShapeDrawable, ScrollView paramScrollView) {
    this.shapedView = paramView;
    this.materialShapeDrawable = paramMaterialShapeDrawable;
    this.containingScrollView = paramScrollView;
  }
  
  public void setContainingScrollView(ScrollView paramScrollView) {
    this.containingScrollView = paramScrollView;
  }
  
  public void setMaterialShapeDrawable(MaterialShapeDrawable paramMaterialShapeDrawable) {
    this.materialShapeDrawable = paramMaterialShapeDrawable;
  }
  
  public void startListeningForScrollChanges(ViewTreeObserver paramViewTreeObserver) {
    paramViewTreeObserver.addOnScrollChangedListener(this.scrollChangedListener);
  }
  
  public void stopListeningForScrollChanges(ViewTreeObserver paramViewTreeObserver) {
    paramViewTreeObserver.removeOnScrollChangedListener(this.scrollChangedListener);
  }
  
  public void updateInterpolationForScreenPosition() {
    if (this.containingScrollView == null)
      return; 
    if (this.containingScrollView.getChildCount() == 0)
      throw new IllegalStateException("Scroll bar must contain a child to calculate interpolation."); 
    this.containingScrollView.getLocationInWindow(this.scrollLocation);
    this.containingScrollView.getChildAt(0).getLocationInWindow(this.containerLocation);
    int i = this.shapedView.getTop() - this.scrollLocation[1] + this.containerLocation[1];
    int j = this.shapedView.getHeight();
    int k = this.containingScrollView.getHeight();
    if (i < 0) {
      this.materialShapeDrawable.setInterpolation(Math.max(0.0F, Math.min(1.0F, i / j + 1.0F)));
      this.shapedView.invalidate();
    } else {
      i += j;
      if (i > k) {
        this.materialShapeDrawable.setInterpolation(Math.max(0.0F, Math.min(1.0F, 1.0F - (i - k) / j)));
        this.shapedView.invalidate();
      } else if (this.materialShapeDrawable.getInterpolation() != 1.0F) {
        this.materialShapeDrawable.setInterpolation(1.0F);
        this.shapedView.invalidate();
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\shape\InterpolateOnScrollPositionChangeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */