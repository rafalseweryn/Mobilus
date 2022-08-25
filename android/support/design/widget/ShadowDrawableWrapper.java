package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;

public class ShadowDrawableWrapper extends DrawableWrapper {
  static final double COS_45 = Math.cos(Math.toRadians(45.0D));
  
  static final float SHADOW_BOTTOM_SCALE = 1.0F;
  
  static final float SHADOW_HORIZ_SCALE = 0.5F;
  
  static final float SHADOW_MULTIPLIER = 1.5F;
  
  static final float SHADOW_TOP_SCALE = 0.25F;
  
  private boolean addPaddingForCorners = true;
  
  final RectF contentBounds;
  
  float cornerRadius;
  
  final Paint cornerShadowPaint;
  
  Path cornerShadowPath;
  
  private boolean dirty = true;
  
  final Paint edgeShadowPaint;
  
  float maxShadowSize;
  
  private boolean printedShadowClipWarning = false;
  
  float rawMaxShadowSize;
  
  float rawShadowSize;
  
  private float rotation;
  
  private final int shadowEndColor;
  
  private final int shadowMiddleColor;
  
  float shadowSize;
  
  private final int shadowStartColor;
  
  public ShadowDrawableWrapper(Context paramContext, Drawable paramDrawable, float paramFloat1, float paramFloat2, float paramFloat3) {
    super(paramDrawable);
    this.shadowStartColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_start_color);
    this.shadowMiddleColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_mid_color);
    this.shadowEndColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_end_color);
    this.cornerShadowPaint = new Paint(5);
    this.cornerShadowPaint.setStyle(Paint.Style.FILL);
    this.cornerRadius = Math.round(paramFloat1);
    this.contentBounds = new RectF();
    this.edgeShadowPaint = new Paint(this.cornerShadowPaint);
    this.edgeShadowPaint.setAntiAlias(false);
    setShadowSize(paramFloat2, paramFloat3);
  }
  
  private void buildComponents(Rect paramRect) {
    float f = this.rawMaxShadowSize * 1.5F;
    this.contentBounds.set(paramRect.left + this.rawMaxShadowSize, paramRect.top + f, paramRect.right - this.rawMaxShadowSize, paramRect.bottom - f);
    getWrappedDrawable().setBounds((int)this.contentBounds.left, (int)this.contentBounds.top, (int)this.contentBounds.right, (int)this.contentBounds.bottom);
    buildShadowCorners();
  }
  
  private void buildShadowCorners() {
    RectF rectF1 = new RectF(-this.cornerRadius, -this.cornerRadius, this.cornerRadius, this.cornerRadius);
    RectF rectF2 = new RectF(rectF1);
    rectF2.inset(-this.shadowSize, -this.shadowSize);
    if (this.cornerShadowPath == null) {
      this.cornerShadowPath = new Path();
    } else {
      this.cornerShadowPath.reset();
    } 
    this.cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
    this.cornerShadowPath.moveTo(-this.cornerRadius, 0.0F);
    this.cornerShadowPath.rLineTo(-this.shadowSize, 0.0F);
    this.cornerShadowPath.arcTo(rectF2, 180.0F, 90.0F, false);
    this.cornerShadowPath.arcTo(rectF1, 270.0F, -90.0F, false);
    this.cornerShadowPath.close();
    float f1 = -rectF2.top;
    if (f1 > 0.0F) {
      float f4 = this.cornerRadius / f1;
      float f5 = (1.0F - f4) / 2.0F;
      Paint paint1 = this.cornerShadowPaint;
      int m = this.shadowStartColor;
      int n = this.shadowMiddleColor;
      int i1 = this.shadowEndColor;
      Shader.TileMode tileMode1 = Shader.TileMode.CLAMP;
      paint1.setShader((Shader)new RadialGradient(0.0F, 0.0F, f1, new int[] { 0, m, n, i1 }, new float[] { 0.0F, f4, f5 + f4, 1.0F }, tileMode1));
    } 
    Paint paint = this.edgeShadowPaint;
    float f3 = rectF1.top;
    float f2 = rectF2.top;
    int i = this.shadowStartColor;
    int j = this.shadowMiddleColor;
    int k = this.shadowEndColor;
    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
    paint.setShader((Shader)new LinearGradient(0.0F, f3, 0.0F, f2, new int[] { i, j, k }, new float[] { 0.0F, 0.5F, 1.0F }, tileMode));
    this.edgeShadowPaint.setAntiAlias(false);
  }
  
  public static float calculateHorizontalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean) {
    return paramBoolean ? (float)(paramFloat1 + (1.0D - COS_45) * paramFloat2) : paramFloat1;
  }
  
  public static float calculateVerticalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean) {
    return paramBoolean ? (float)((paramFloat1 * 1.5F) + (1.0D - COS_45) * paramFloat2) : (paramFloat1 * 1.5F);
  }
  
  private void drawShadow(Canvas paramCanvas) {
    boolean bool;
    int i = paramCanvas.save();
    paramCanvas.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
    float f1 = -this.cornerRadius - this.shadowSize;
    float f2 = this.cornerRadius;
    float f3 = this.contentBounds.width();
    float f4 = f2 * 2.0F;
    if (f3 - f4 > 0.0F) {
      j = 1;
    } else {
      j = 0;
    } 
    if (this.contentBounds.height() - f4 > 0.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    float f5 = this.rawShadowSize;
    float f6 = this.rawShadowSize;
    float f7 = this.rawShadowSize;
    float f8 = this.rawShadowSize;
    float f9 = this.rawShadowSize;
    f3 = this.rawShadowSize;
    f8 = f2 / (f7 - f8 * 0.5F + f2);
    f5 = f2 / (f5 - f6 * 0.25F + f2);
    f3 = f2 / (f9 - f3 * 1.0F + f2);
    int k = paramCanvas.save();
    paramCanvas.translate(this.contentBounds.left + f2, this.contentBounds.top + f2);
    paramCanvas.scale(f8, f5);
    paramCanvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
    if (j) {
      paramCanvas.scale(1.0F / f8, 1.0F);
      paramCanvas.drawRect(0.0F, f1, this.contentBounds.width() - f4, -this.cornerRadius, this.edgeShadowPaint);
    } 
    paramCanvas.restoreToCount(k);
    k = paramCanvas.save();
    paramCanvas.translate(this.contentBounds.right - f2, this.contentBounds.bottom - f2);
    paramCanvas.scale(f8, f3);
    paramCanvas.rotate(180.0F);
    paramCanvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
    if (j) {
      paramCanvas.scale(1.0F / f8, 1.0F);
      paramCanvas.drawRect(0.0F, f1, this.contentBounds.width() - f4, -this.cornerRadius + this.shadowSize, this.edgeShadowPaint);
    } 
    paramCanvas.restoreToCount(k);
    int j = paramCanvas.save();
    paramCanvas.translate(this.contentBounds.left + f2, this.contentBounds.bottom - f2);
    paramCanvas.scale(f8, f3);
    paramCanvas.rotate(270.0F);
    paramCanvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
    if (bool) {
      paramCanvas.scale(1.0F / f3, 1.0F);
      paramCanvas.drawRect(0.0F, f1, this.contentBounds.height() - f4, -this.cornerRadius, this.edgeShadowPaint);
    } 
    paramCanvas.restoreToCount(j);
    j = paramCanvas.save();
    paramCanvas.translate(this.contentBounds.right - f2, this.contentBounds.top + f2);
    paramCanvas.scale(f8, f5);
    paramCanvas.rotate(90.0F);
    paramCanvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
    if (bool) {
      paramCanvas.scale(1.0F / f5, 1.0F);
      paramCanvas.drawRect(0.0F, f1, this.contentBounds.height() - f4, -this.cornerRadius, this.edgeShadowPaint);
    } 
    paramCanvas.restoreToCount(j);
    paramCanvas.restoreToCount(i);
  }
  
  private static int toEven(float paramFloat) {
    int i = Math.round(paramFloat);
    int j = i;
    if (i % 2 == 1)
      j = i - 1; 
    return j;
  }
  
  public void draw(Canvas paramCanvas) {
    if (this.dirty) {
      buildComponents(getBounds());
      this.dirty = false;
    } 
    drawShadow(paramCanvas);
    super.draw(paramCanvas);
  }
  
  public float getCornerRadius() {
    return this.cornerRadius;
  }
  
  public float getMaxShadowSize() {
    return this.rawMaxShadowSize;
  }
  
  public float getMinHeight() {
    return Math.max(this.rawMaxShadowSize, this.cornerRadius + this.rawMaxShadowSize * 1.5F / 2.0F) * 2.0F + this.rawMaxShadowSize * 1.5F * 2.0F;
  }
  
  public float getMinWidth() {
    return Math.max(this.rawMaxShadowSize, this.cornerRadius + this.rawMaxShadowSize / 2.0F) * 2.0F + this.rawMaxShadowSize * 2.0F;
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public boolean getPadding(Rect paramRect) {
    int i = (int)Math.ceil(calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
    int j = (int)Math.ceil(calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
    paramRect.set(j, i, j, i);
    return true;
  }
  
  public float getShadowSize() {
    return this.rawShadowSize;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    this.dirty = true;
  }
  
  public void setAddPaddingForCorners(boolean paramBoolean) {
    this.addPaddingForCorners = paramBoolean;
    invalidateSelf();
  }
  
  public void setAlpha(int paramInt) {
    super.setAlpha(paramInt);
    this.cornerShadowPaint.setAlpha(paramInt);
    this.edgeShadowPaint.setAlpha(paramInt);
  }
  
  public void setCornerRadius(float paramFloat) {
    paramFloat = Math.round(paramFloat);
    if (this.cornerRadius == paramFloat)
      return; 
    this.cornerRadius = paramFloat;
    this.dirty = true;
    invalidateSelf();
  }
  
  public void setMaxShadowSize(float paramFloat) {
    setShadowSize(this.rawShadowSize, paramFloat);
  }
  
  public final void setRotation(float paramFloat) {
    if (this.rotation != paramFloat) {
      this.rotation = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setShadowSize(float paramFloat) {
    setShadowSize(paramFloat, this.rawMaxShadowSize);
  }
  
  public void setShadowSize(float paramFloat1, float paramFloat2) {
    if (paramFloat1 < 0.0F || paramFloat2 < 0.0F)
      throw new IllegalArgumentException("invalid shadow size"); 
    float f = toEven(paramFloat1);
    paramFloat2 = toEven(paramFloat2);
    paramFloat1 = f;
    if (f > paramFloat2) {
      if (!this.printedShadowClipWarning)
        this.printedShadowClipWarning = true; 
      paramFloat1 = paramFloat2;
    } 
    if (this.rawShadowSize == paramFloat1 && this.rawMaxShadowSize == paramFloat2)
      return; 
    this.rawShadowSize = paramFloat1;
    this.rawMaxShadowSize = paramFloat2;
    this.shadowSize = Math.round(paramFloat1 * 1.5F);
    this.maxShadowSize = paramFloat2;
    this.dirty = true;
    invalidateSelf();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\ShadowDrawableWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */