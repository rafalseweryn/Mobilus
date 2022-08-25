package android.support.design.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RestrictTo;
import android.support.design.R;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.design.resources.MaterialResources;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MaterialButton extends AppCompatButton {
  public static final int ICON_GRAVITY_START = 1;
  
  public static final int ICON_GRAVITY_TEXT_START = 2;
  
  private static final String LOG_TAG = "MaterialButton";
  
  private Drawable icon;
  
  private int iconGravity;
  
  @Px
  private int iconLeft;
  
  @Px
  private int iconPadding;
  
  @Px
  private int iconSize;
  
  private ColorStateList iconTint;
  
  private PorterDuff.Mode iconTintMode;
  
  @Nullable
  private final MaterialButtonHelper materialButtonHelper;
  
  public MaterialButton(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public MaterialButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.materialButtonStyle);
  }
  
  public MaterialButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray typedArray = ThemeEnforcement.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.MaterialButton, paramInt, R.style.Widget_MaterialComponents_Button, new int[0]);
    this.iconPadding = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_iconPadding, 0);
    this.iconTintMode = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
    this.iconTint = MaterialResources.getColorStateList(getContext(), typedArray, R.styleable.MaterialButton_iconTint);
    this.icon = MaterialResources.getDrawable(getContext(), typedArray, R.styleable.MaterialButton_icon);
    this.iconGravity = typedArray.getInteger(R.styleable.MaterialButton_iconGravity, 1);
    this.iconSize = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_iconSize, 0);
    this.materialButtonHelper = new MaterialButtonHelper(this);
    this.materialButtonHelper.loadFromAttributes(typedArray);
    typedArray.recycle();
    setCompoundDrawablePadding(this.iconPadding);
    updateIcon();
  }
  
  private boolean isLayoutRTL() {
    int i = ViewCompat.getLayoutDirection((View)this);
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  private boolean isUsingOriginalBackground() {
    boolean bool;
    if (this.materialButtonHelper != null && !this.materialButtonHelper.isBackgroundOverwritten()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void updateIcon() {
    if (this.icon != null) {
      int i;
      int j;
      this.icon = this.icon.mutate();
      DrawableCompat.setTintList(this.icon, this.iconTint);
      if (this.iconTintMode != null)
        DrawableCompat.setTintMode(this.icon, this.iconTintMode); 
      if (this.iconSize != 0) {
        i = this.iconSize;
      } else {
        i = this.icon.getIntrinsicWidth();
      } 
      if (this.iconSize != 0) {
        j = this.iconSize;
      } else {
        j = this.icon.getIntrinsicHeight();
      } 
      this.icon.setBounds(this.iconLeft, 0, this.iconLeft + i, j);
    } 
    TextViewCompat.setCompoundDrawablesRelative((TextView)this, this.icon, null, null, null);
  }
  
  @Nullable
  public ColorStateList getBackgroundTintList() {
    return getSupportBackgroundTintList();
  }
  
  @Nullable
  public PorterDuff.Mode getBackgroundTintMode() {
    return getSupportBackgroundTintMode();
  }
  
  @Px
  public int getCornerRadius() {
    boolean bool;
    if (isUsingOriginalBackground()) {
      bool = this.materialButtonHelper.getCornerRadius();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Drawable getIcon() {
    return this.icon;
  }
  
  public int getIconGravity() {
    return this.iconGravity;
  }
  
  @Px
  public int getIconPadding() {
    return this.iconPadding;
  }
  
  @Px
  public int getIconSize() {
    return this.iconSize;
  }
  
  public ColorStateList getIconTint() {
    return this.iconTint;
  }
  
  public PorterDuff.Mode getIconTintMode() {
    return this.iconTintMode;
  }
  
  public ColorStateList getRippleColor() {
    ColorStateList colorStateList;
    if (isUsingOriginalBackground()) {
      colorStateList = this.materialButtonHelper.getRippleColor();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  public ColorStateList getStrokeColor() {
    ColorStateList colorStateList;
    if (isUsingOriginalBackground()) {
      colorStateList = this.materialButtonHelper.getStrokeColor();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  @Px
  public int getStrokeWidth() {
    boolean bool;
    if (isUsingOriginalBackground()) {
      bool = this.materialButtonHelper.getStrokeWidth();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public ColorStateList getSupportBackgroundTintList() {
    return isUsingOriginalBackground() ? this.materialButtonHelper.getSupportBackgroundTintList() : super.getSupportBackgroundTintList();
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public PorterDuff.Mode getSupportBackgroundTintMode() {
    return isUsingOriginalBackground() ? this.materialButtonHelper.getSupportBackgroundTintMode() : super.getSupportBackgroundTintMode();
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (Build.VERSION.SDK_INT < 21 && isUsingOriginalBackground())
      this.materialButtonHelper.drawStroke(paramCanvas); 
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (Build.VERSION.SDK_INT == 21 && this.materialButtonHelper != null)
      this.materialButtonHelper.updateMaskBounds(paramInt4 - paramInt2, paramInt3 - paramInt1); 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    if (this.icon == null || this.iconGravity != 2)
      return; 
    paramInt2 = (int)getPaint().measureText(getText().toString());
    if (this.iconSize == 0) {
      paramInt1 = this.icon.getIntrinsicWidth();
    } else {
      paramInt1 = this.iconSize;
    } 
    paramInt2 = (getMeasuredWidth() - paramInt2 - ViewCompat.getPaddingEnd((View)this) - paramInt1 - this.iconPadding - ViewCompat.getPaddingStart((View)this)) / 2;
    paramInt1 = paramInt2;
    if (isLayoutRTL())
      paramInt1 = -paramInt2; 
    if (this.iconLeft != paramInt1) {
      this.iconLeft = paramInt1;
      updateIcon();
    } 
  }
  
  public void setBackground(Drawable paramDrawable) {
    setBackgroundDrawable(paramDrawable);
  }
  
  public void setBackgroundColor(@ColorInt int paramInt) {
    if (isUsingOriginalBackground()) {
      this.materialButtonHelper.setBackgroundColor(paramInt);
    } else {
      super.setBackgroundColor(paramInt);
    } 
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    if (isUsingOriginalBackground()) {
      if (paramDrawable != getBackground()) {
        Log.i("MaterialButton", "Setting a custom background is not supported.");
        this.materialButtonHelper.setBackgroundOverwritten();
        super.setBackgroundDrawable(paramDrawable);
      } else {
        getBackground().setState(paramDrawable.getState());
      } 
    } else {
      super.setBackgroundDrawable(paramDrawable);
    } 
  }
  
  public void setBackgroundResource(@DrawableRes int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = AppCompatResources.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    setBackgroundDrawable(drawable);
  }
  
  public void setBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    setSupportBackgroundTintList(paramColorStateList);
  }
  
  public void setBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    setSupportBackgroundTintMode(paramMode);
  }
  
  public void setCornerRadius(@Px int paramInt) {
    if (isUsingOriginalBackground())
      this.materialButtonHelper.setCornerRadius(paramInt); 
  }
  
  public void setCornerRadiusResource(@DimenRes int paramInt) {
    if (isUsingOriginalBackground())
      setCornerRadius(getResources().getDimensionPixelSize(paramInt)); 
  }
  
  public void setIcon(Drawable paramDrawable) {
    if (this.icon != paramDrawable) {
      this.icon = paramDrawable;
      updateIcon();
    } 
  }
  
  public void setIconGravity(int paramInt) {
    this.iconGravity = paramInt;
  }
  
  public void setIconPadding(@Px int paramInt) {
    if (this.iconPadding != paramInt) {
      this.iconPadding = paramInt;
      setCompoundDrawablePadding(paramInt);
    } 
  }
  
  public void setIconResource(@DrawableRes int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = AppCompatResources.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    setIcon(drawable);
  }
  
  public void setIconSize(@Px int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentException("iconSize cannot be less than 0"); 
    if (this.iconSize != paramInt) {
      this.iconSize = paramInt;
      updateIcon();
    } 
  }
  
  public void setIconTint(@Nullable ColorStateList paramColorStateList) {
    if (this.iconTint != paramColorStateList) {
      this.iconTint = paramColorStateList;
      updateIcon();
    } 
  }
  
  public void setIconTintMode(PorterDuff.Mode paramMode) {
    if (this.iconTintMode != paramMode) {
      this.iconTintMode = paramMode;
      updateIcon();
    } 
  }
  
  public void setIconTintResource(@ColorRes int paramInt) {
    setIconTint(AppCompatResources.getColorStateList(getContext(), paramInt));
  }
  
  void setInternalBackground(Drawable paramDrawable) {
    super.setBackgroundDrawable(paramDrawable);
  }
  
  public void setRippleColor(@Nullable ColorStateList paramColorStateList) {
    if (isUsingOriginalBackground())
      this.materialButtonHelper.setRippleColor(paramColorStateList); 
  }
  
  public void setRippleColorResource(@ColorRes int paramInt) {
    if (isUsingOriginalBackground())
      setRippleColor(AppCompatResources.getColorStateList(getContext(), paramInt)); 
  }
  
  public void setStrokeColor(@Nullable ColorStateList paramColorStateList) {
    if (isUsingOriginalBackground())
      this.materialButtonHelper.setStrokeColor(paramColorStateList); 
  }
  
  public void setStrokeColorResource(@ColorRes int paramInt) {
    if (isUsingOriginalBackground())
      setStrokeColor(AppCompatResources.getColorStateList(getContext(), paramInt)); 
  }
  
  public void setStrokeWidth(@Px int paramInt) {
    if (isUsingOriginalBackground())
      this.materialButtonHelper.setStrokeWidth(paramInt); 
  }
  
  public void setStrokeWidthResource(@DimenRes int paramInt) {
    if (isUsingOriginalBackground())
      setStrokeWidth(getResources().getDimensionPixelSize(paramInt)); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    if (isUsingOriginalBackground()) {
      this.materialButtonHelper.setSupportBackgroundTintList(paramColorStateList);
    } else if (this.materialButtonHelper != null) {
      super.setSupportBackgroundTintList(paramColorStateList);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    if (isUsingOriginalBackground()) {
      this.materialButtonHelper.setSupportBackgroundTintMode(paramMode);
    } else if (this.materialButtonHelper != null) {
      super.setSupportBackgroundTintMode(paramMode);
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface IconGravity {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\button\MaterialButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */