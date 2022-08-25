package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup {
  private int baseline = -1;
  
  public BaselineLayout(Context paramContext) {
    super(paramContext, null, 0);
  }
  
  public BaselineLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet, 0);
  }
  
  public BaselineLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public int getBaseline() {
    return this.baseline;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getChildCount();
    int j = getPaddingLeft();
    int k = getPaddingRight();
    int m = getPaddingTop();
    for (paramInt2 = 0; paramInt2 < i; paramInt2++) {
      View view = getChildAt(paramInt2);
      if (view.getVisibility() != 8) {
        int n = view.getMeasuredWidth();
        int i1 = view.getMeasuredHeight();
        int i2 = (paramInt3 - paramInt1 - k - j - n) / 2 + j;
        if (this.baseline != -1 && view.getBaseline() != -1) {
          paramInt4 = this.baseline + m - view.getBaseline();
        } else {
          paramInt4 = m;
        } 
        view.layout(i2, paramInt4, n + i2, i1 + paramInt4);
      } 
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = getChildCount();
    byte b = 0;
    int j = -1;
    int k = j;
    int m = 0;
    int n = m;
    int i1 = n;
    int i2 = n;
    n = m;
    while (b < i) {
      View view = getChildAt(b);
      if (view.getVisibility() != 8) {
        measureChild(view, paramInt1, paramInt2);
        int i3 = view.getBaseline();
        int i4 = j;
        m = k;
        if (i3 != -1) {
          i4 = Math.max(j, i3);
          m = Math.max(k, view.getMeasuredHeight() - i3);
        } 
        i2 = Math.max(i2, view.getMeasuredWidth());
        n = Math.max(n, view.getMeasuredHeight());
        i1 = View.combineMeasuredStates(i1, view.getMeasuredState());
        k = m;
        j = i4;
      } 
      b++;
    } 
    m = n;
    if (j != -1) {
      m = Math.max(n, Math.max(k, getPaddingBottom()) + j);
      this.baseline = j;
    } 
    n = Math.max(m, getSuggestedMinimumHeight());
    setMeasuredDimension(View.resolveSizeAndState(Math.max(i2, getSuggestedMinimumWidth()), paramInt1, i1), View.resolveSizeAndState(n, paramInt2, i1 << 16));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\internal\BaselineLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */