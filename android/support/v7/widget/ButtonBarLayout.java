package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ButtonBarLayout extends LinearLayout {
  private static final int PEEK_BUTTON_DP = 16;
  
  private boolean mAllowStacking;
  
  private int mLastWidthSize = -1;
  
  private int mMinimumHeight = 0;
  
  public ButtonBarLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ButtonBarLayout);
    this.mAllowStacking = typedArray.getBoolean(R.styleable.ButtonBarLayout_allowStacking, true);
    typedArray.recycle();
  }
  
  private int getNextVisibleChildIndex(int paramInt) {
    int i = getChildCount();
    while (paramInt < i) {
      if (getChildAt(paramInt).getVisibility() == 0)
        return paramInt; 
      paramInt++;
    } 
    return -1;
  }
  
  private boolean isStacked() {
    int i = getOrientation();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  private void setStacked(boolean paramBoolean) {
    byte b;
    setOrientation(paramBoolean);
    if (paramBoolean) {
      b = 5;
    } else {
      b = 80;
    } 
    setGravity(b);
    View view = findViewById(R.id.spacer);
    if (view != null) {
      byte b1;
      if (paramBoolean) {
        b1 = 8;
      } else {
        b1 = 4;
      } 
      view.setVisibility(b1);
    } 
    for (int i = getChildCount() - 2; i >= 0; i--)
      bringChildToFront(getChildAt(i)); 
  }
  
  public int getMinimumHeight() {
    return Math.max(this.mMinimumHeight, super.getMinimumHeight());
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.getSize(paramInt1);
    boolean bool = this.mAllowStacking;
    boolean bool1 = false;
    if (bool) {
      if (i > this.mLastWidthSize && isStacked())
        setStacked(false); 
      this.mLastWidthSize = i;
    } 
    if (!isStacked() && View.MeasureSpec.getMode(paramInt1) == 1073741824) {
      j = View.MeasureSpec.makeMeasureSpec(i, -2147483648);
      i = 1;
    } else {
      j = paramInt1;
      i = 0;
    } 
    super.onMeasure(j, paramInt2);
    int j = i;
    if (this.mAllowStacking) {
      j = i;
      if (!isStacked()) {
        boolean bool2;
        if ((getMeasuredWidthAndState() & 0xFF000000) == 16777216) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        j = i;
        if (bool2) {
          setStacked(true);
          j = 1;
        } 
      } 
    } 
    if (j != 0)
      super.onMeasure(paramInt1, paramInt2); 
    i = getNextVisibleChildIndex(0);
    paramInt1 = bool1;
    if (i >= 0) {
      View view = getChildAt(i);
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)view.getLayoutParams();
      paramInt2 = getPaddingTop() + view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
      if (isStacked()) {
        i = getNextVisibleChildIndex(i + 1);
        paramInt1 = paramInt2;
        if (i >= 0)
          paramInt1 = paramInt2 + getChildAt(i).getPaddingTop() + (int)((getResources().getDisplayMetrics()).density * 16.0F); 
      } else {
        paramInt1 = paramInt2 + getPaddingBottom();
      } 
    } 
    if (ViewCompat.getMinimumHeight((View)this) != paramInt1)
      setMinimumHeight(paramInt1); 
  }
  
  public void setAllowStacking(boolean paramBoolean) {
    if (this.mAllowStacking != paramBoolean) {
      this.mAllowStacking = paramBoolean;
      if (!this.mAllowStacking && getOrientation() == 1)
        setStacked(false); 
      requestLayout();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\ButtonBarLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */