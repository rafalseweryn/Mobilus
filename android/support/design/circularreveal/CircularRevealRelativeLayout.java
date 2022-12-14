package android.support.design.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CircularRevealRelativeLayout extends RelativeLayout implements CircularRevealWidget {
  private final CircularRevealHelper helper = new CircularRevealHelper(this);
  
  public CircularRevealRelativeLayout(Context paramContext) {
    this(paramContext, null);
  }
  
  public CircularRevealRelativeLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public void actualDraw(Canvas paramCanvas) {
    super.draw(paramCanvas);
  }
  
  public boolean actualIsOpaque() {
    return super.isOpaque();
  }
  
  public void buildCircularRevealCache() {
    this.helper.buildCircularRevealCache();
  }
  
  public void destroyCircularRevealCache() {
    this.helper.destroyCircularRevealCache();
  }
  
  public void draw(Canvas paramCanvas) {
    if (this.helper != null) {
      this.helper.draw(paramCanvas);
    } else {
      super.draw(paramCanvas);
    } 
  }
  
  @Nullable
  public Drawable getCircularRevealOverlayDrawable() {
    return this.helper.getCircularRevealOverlayDrawable();
  }
  
  public int getCircularRevealScrimColor() {
    return this.helper.getCircularRevealScrimColor();
  }
  
  @Nullable
  public CircularRevealWidget.RevealInfo getRevealInfo() {
    return this.helper.getRevealInfo();
  }
  
  public boolean isOpaque() {
    return (this.helper != null) ? this.helper.isOpaque() : super.isOpaque();
  }
  
  public void setCircularRevealOverlayDrawable(@Nullable Drawable paramDrawable) {
    this.helper.setCircularRevealOverlayDrawable(paramDrawable);
  }
  
  public void setCircularRevealScrimColor(@ColorInt int paramInt) {
    this.helper.setCircularRevealScrimColor(paramInt);
  }
  
  public void setRevealInfo(@Nullable CircularRevealWidget.RevealInfo paramRevealInfo) {
    this.helper.setRevealInfo(paramRevealInfo);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\circularreveal\CircularRevealRelativeLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */