package android.support.design.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.design.R;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class FlowLayout extends ViewGroup {
  private int itemSpacing;
  
  private int lineSpacing;
  
  private boolean singleLine = false;
  
  public FlowLayout(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public FlowLayout(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public FlowLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    loadFromAttributes(paramContext, paramAttributeSet);
  }
  
  @TargetApi(21)
  public FlowLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    loadFromAttributes(paramContext, paramAttributeSet);
  }
  
  private static int getMeasuredDimension(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt2 != Integer.MIN_VALUE) ? ((paramInt2 != 1073741824) ? paramInt3 : paramInt1) : Math.min(paramInt3, paramInt1);
  }
  
  private void loadFromAttributes(Context paramContext, AttributeSet paramAttributeSet) {
    TypedArray typedArray = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.FlowLayout, 0, 0);
    this.lineSpacing = typedArray.getDimensionPixelSize(R.styleable.FlowLayout_lineSpacing, 0);
    this.itemSpacing = typedArray.getDimensionPixelSize(R.styleable.FlowLayout_itemSpacing, 0);
    typedArray.recycle();
  }
  
  protected int getItemSpacing() {
    return this.itemSpacing;
  }
  
  protected int getLineSpacing() {
    return this.lineSpacing;
  }
  
  protected boolean isSingleLine() {
    return this.singleLine;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (getChildCount() == 0)
      return; 
    paramInt2 = ViewCompat.getLayoutDirection((View)this);
    boolean bool = true;
    if (paramInt2 != 1)
      bool = false; 
    if (bool) {
      paramInt2 = getPaddingRight();
    } else {
      paramInt2 = getPaddingLeft();
    } 
    if (bool) {
      paramInt4 = getPaddingLeft();
    } else {
      paramInt4 = getPaddingRight();
    } 
    int i = getPaddingTop();
    int j = paramInt3 - paramInt1 - paramInt4;
    paramInt3 = paramInt2;
    byte b = 0;
    paramInt1 = i;
    while (b < getChildCount()) {
      View view = getChildAt(b);
      if (view.getVisibility() != 8) {
        byte b1;
        byte b2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
          ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)layoutParams;
          b1 = MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
          b2 = MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
        } else {
          b2 = 0;
          b1 = b2;
        } 
        int k = view.getMeasuredWidth();
        int m = paramInt3;
        paramInt4 = paramInt1;
        if (!this.singleLine) {
          m = paramInt3;
          paramInt4 = paramInt1;
          if (paramInt3 + b1 + k > j) {
            paramInt4 = i + this.lineSpacing;
            m = paramInt2;
          } 
        } 
        paramInt3 = m + b1;
        paramInt1 = view.getMeasuredWidth() + paramInt3;
        i = view.getMeasuredHeight() + paramInt4;
        if (bool) {
          view.layout(j - paramInt1, paramInt4, j - m - b1, i);
        } else {
          view.layout(paramInt3, paramInt4, paramInt1, i);
        } 
        paramInt3 = m + b1 + b2 + view.getMeasuredWidth() + this.itemSpacing;
        paramInt1 = paramInt4;
      } 
      b++;
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int n;
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt1);
    int k = View.MeasureSpec.getSize(paramInt2);
    int m = View.MeasureSpec.getMode(paramInt2);
    if (j == Integer.MIN_VALUE || j == 1073741824) {
      n = i;
    } else {
      n = Integer.MAX_VALUE;
    } 
    int i1 = getPaddingLeft();
    int i2 = getPaddingTop();
    int i3 = getPaddingRight();
    int i4 = i2;
    byte b = 0;
    int i5 = 0;
    int i6 = i2;
    i2 = i5;
    while (b < getChildCount()) {
      View view = getChildAt(b);
      if (view.getVisibility() == 8) {
        i5 = i4;
      } else {
        byte b1;
        byte b2;
        measureChild(view, paramInt1, paramInt2);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
          ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)layoutParams;
          b1 = marginLayoutParams.leftMargin + 0;
          b2 = marginLayoutParams.rightMargin + 0;
        } else {
          b1 = 0;
          b2 = b1;
        } 
        int i7 = view.getMeasuredWidth();
        int i8 = i1;
        i5 = i4;
        int i9 = i8;
        if (i1 + b1 + i7 > n - i3) {
          i5 = i4;
          i9 = i8;
          if (!isSingleLine()) {
            i9 = getPaddingLeft();
            i5 = this.lineSpacing + i6;
          } 
        } 
        i1 = i9 + b1 + view.getMeasuredWidth();
        i6 = view.getMeasuredHeight();
        i4 = i2;
        if (i1 > i2)
          i4 = i1; 
        i1 = view.getMeasuredWidth();
        i2 = this.itemSpacing;
        i6 += i5;
        i1 = i9 + b1 + b2 + i1 + i2;
        i2 = i4;
      } 
      b++;
      i4 = i5;
    } 
    setMeasuredDimension(getMeasuredDimension(i, j, i2), getMeasuredDimension(k, m, i6));
  }
  
  protected void setItemSpacing(int paramInt) {
    this.itemSpacing = paramInt;
  }
  
  protected void setLineSpacing(int paramInt) {
    this.lineSpacing = paramInt;
  }
  
  public void setSingleLine(boolean paramBoolean) {
    this.singleLine = paramBoolean;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\internal\FlowLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */