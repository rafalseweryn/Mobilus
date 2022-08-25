package android.support.design.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.design.R;
import android.support.design.internal.ThemeEnforcement;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class MaterialCardView extends CardView {
  private final MaterialCardViewHelper cardViewHelper;
  
  public MaterialCardView(Context paramContext) {
    this(paramContext, null);
  }
  
  public MaterialCardView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.materialCardViewStyle);
  }
  
  public MaterialCardView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray typedArray = ThemeEnforcement.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.MaterialCardView, paramInt, R.style.Widget_MaterialComponents_CardView, new int[0]);
    this.cardViewHelper = new MaterialCardViewHelper(this);
    this.cardViewHelper.loadFromAttributes(typedArray);
    typedArray.recycle();
  }
  
  @ColorInt
  public int getStrokeColor() {
    return this.cardViewHelper.getStrokeColor();
  }
  
  @Dimension
  public int getStrokeWidth() {
    return this.cardViewHelper.getStrokeWidth();
  }
  
  public void setRadius(float paramFloat) {
    super.setRadius(paramFloat);
    this.cardViewHelper.updateForeground();
  }
  
  public void setStrokeColor(@ColorInt int paramInt) {
    this.cardViewHelper.setStrokeColor(paramInt);
  }
  
  public void setStrokeWidth(@Dimension int paramInt) {
    this.cardViewHelper.setStrokeWidth(paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\card\MaterialCardView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */