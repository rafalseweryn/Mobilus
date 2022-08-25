package android.support.design.widget;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.design.animation.MotionSpec;
import android.support.design.expandable.ExpandableTransformationWidget;
import android.support.design.expandable.ExpandableWidget;
import android.support.design.expandable.ExpandableWidgetHelper;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.design.resources.MaterialResources;
import android.support.design.stateful.ExtendableSavedState;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TintableImageSourceView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatImageHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@DefaultBehavior(FloatingActionButton.Behavior.class)
public class FloatingActionButton extends VisibilityAwareImageButton implements TintableBackgroundView, TintableImageSourceView, ExpandableTransformationWidget {
  private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
  
  private static final String EXPANDABLE_WIDGET_HELPER_KEY = "expandableWidgetHelper";
  
  private static final String LOG_TAG = "FloatingActionButton";
  
  public static final int NO_CUSTOM_SIZE = 0;
  
  public static final int SIZE_AUTO = -1;
  
  public static final int SIZE_MINI = 1;
  
  public static final int SIZE_NORMAL = 0;
  
  private ColorStateList backgroundTint;
  
  private PorterDuff.Mode backgroundTintMode;
  
  private int borderWidth;
  
  boolean compatPadding;
  
  private int customSize;
  
  private final ExpandableWidgetHelper expandableWidgetHelper;
  
  private final AppCompatImageHelper imageHelper;
  
  @Nullable
  private PorterDuff.Mode imageMode;
  
  private int imagePadding;
  
  @Nullable
  private ColorStateList imageTint;
  
  private FloatingActionButtonImpl impl;
  
  private int maxImageSize;
  
  private ColorStateList rippleColor;
  
  final Rect shadowPadding = new Rect();
  
  private int size;
  
  private final Rect touchArea = new Rect();
  
  public FloatingActionButton(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.floatingActionButtonStyle);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray typedArray = ThemeEnforcement.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.FloatingActionButton, paramInt, R.style.Widget_Design_FloatingActionButton, new int[0]);
    this.backgroundTint = MaterialResources.getColorStateList(paramContext, typedArray, R.styleable.FloatingActionButton_backgroundTint);
    this.backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
    this.rippleColor = MaterialResources.getColorStateList(paramContext, typedArray, R.styleable.FloatingActionButton_rippleColor);
    this.size = typedArray.getInt(R.styleable.FloatingActionButton_fabSize, -1);
    this.customSize = typedArray.getDimensionPixelSize(R.styleable.FloatingActionButton_fabCustomSize, 0);
    this.borderWidth = typedArray.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
    float f1 = typedArray.getDimension(R.styleable.FloatingActionButton_elevation, 0.0F);
    float f2 = typedArray.getDimension(R.styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0F);
    float f3 = typedArray.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0F);
    this.compatPadding = typedArray.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
    this.maxImageSize = typedArray.getDimensionPixelSize(R.styleable.FloatingActionButton_maxImageSize, 0);
    MotionSpec motionSpec2 = MotionSpec.createFromAttribute(paramContext, typedArray, R.styleable.FloatingActionButton_showMotionSpec);
    MotionSpec motionSpec1 = MotionSpec.createFromAttribute(paramContext, typedArray, R.styleable.FloatingActionButton_hideMotionSpec);
    typedArray.recycle();
    this.imageHelper = new AppCompatImageHelper((ImageView)this);
    this.imageHelper.loadFromAttributes(paramAttributeSet, paramInt);
    this.expandableWidgetHelper = new ExpandableWidgetHelper((ExpandableWidget)this);
    getImpl().setBackgroundDrawable(this.backgroundTint, this.backgroundTintMode, this.rippleColor, this.borderWidth);
    getImpl().setElevation(f1);
    getImpl().setHoveredFocusedTranslationZ(f2);
    getImpl().setPressedTranslationZ(f3);
    getImpl().setMaxImageSize(this.maxImageSize);
    getImpl().setShowMotionSpec(motionSpec2);
    getImpl().setHideMotionSpec(motionSpec1);
    setScaleType(ImageView.ScaleType.MATRIX);
  }
  
  private FloatingActionButtonImpl createImpl() {
    return (Build.VERSION.SDK_INT >= 21) ? new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl()) : new FloatingActionButtonImpl(this, new ShadowDelegateImpl());
  }
  
  private FloatingActionButtonImpl getImpl() {
    if (this.impl == null)
      this.impl = createImpl(); 
    return this.impl;
  }
  
  private int getSizeDimension(int paramInt) {
    if (this.customSize != 0)
      return this.customSize; 
    Resources resources = getResources();
    if (paramInt != -1)
      return (paramInt != 1) ? resources.getDimensionPixelSize(R.dimen.design_fab_size_normal) : resources.getDimensionPixelSize(R.dimen.design_fab_size_mini); 
    if (Math.max((resources.getConfiguration()).screenWidthDp, (resources.getConfiguration()).screenHeightDp) < 470) {
      paramInt = getSizeDimension(1);
    } else {
      paramInt = getSizeDimension(0);
    } 
    return paramInt;
  }
  
  private void offsetRectWithShadow(@NonNull Rect paramRect) {
    paramRect.left += this.shadowPadding.left;
    paramRect.top += this.shadowPadding.top;
    paramRect.right -= this.shadowPadding.right;
    paramRect.bottom -= this.shadowPadding.bottom;
  }
  
  private void onApplySupportImageTint() {
    Drawable drawable = getDrawable();
    if (drawable == null)
      return; 
    if (this.imageTint == null) {
      DrawableCompat.clearColorFilter(drawable);
      return;
    } 
    int i = this.imageTint.getColorForState(getDrawableState(), 0);
    PorterDuff.Mode mode1 = this.imageMode;
    PorterDuff.Mode mode2 = mode1;
    if (mode1 == null)
      mode2 = PorterDuff.Mode.SRC_IN; 
    drawable.mutate().setColorFilter((ColorFilter)AppCompatDrawableManager.getPorterDuffColorFilter(i, mode2));
  }
  
  private static int resolveAdjustedSize(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (i != Integer.MIN_VALUE) {
      if (i != 0) {
        if (i != 1073741824)
          throw new IllegalArgumentException(); 
        paramInt1 = paramInt2;
      } 
    } else {
      paramInt1 = Math.min(paramInt1, paramInt2);
    } 
    return paramInt1;
  }
  
  @Nullable
  private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(@Nullable final OnVisibilityChangedListener listener) {
    return (listener == null) ? null : new FloatingActionButtonImpl.InternalVisibilityChangedListener() {
        public void onHidden() {
          listener.onHidden(FloatingActionButton.this);
        }
        
        public void onShown() {
          listener.onShown(FloatingActionButton.this);
        }
      };
  }
  
  public void addOnHideAnimationListener(@NonNull Animator.AnimatorListener paramAnimatorListener) {
    getImpl().addOnHideAnimationListener(paramAnimatorListener);
  }
  
  public void addOnShowAnimationListener(@NonNull Animator.AnimatorListener paramAnimatorListener) {
    getImpl().addOnShowAnimationListener(paramAnimatorListener);
  }
  
  public void clearCustomSize() {
    setCustomSize(0);
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    getImpl().onDrawableStateChanged(getDrawableState());
  }
  
  @Nullable
  public ColorStateList getBackgroundTintList() {
    return this.backgroundTint;
  }
  
  @Nullable
  public PorterDuff.Mode getBackgroundTintMode() {
    return this.backgroundTintMode;
  }
  
  public float getCompatElevation() {
    return getImpl().getElevation();
  }
  
  public float getCompatHoveredFocusedTranslationZ() {
    return getImpl().getHoveredFocusedTranslationZ();
  }
  
  public float getCompatPressedTranslationZ() {
    return getImpl().getPressedTranslationZ();
  }
  
  @NonNull
  public Drawable getContentBackground() {
    return getImpl().getContentBackground();
  }
  
  @Deprecated
  public boolean getContentRect(@NonNull Rect paramRect) {
    if (ViewCompat.isLaidOut((View)this)) {
      paramRect.set(0, 0, getWidth(), getHeight());
      offsetRectWithShadow(paramRect);
      return true;
    } 
    return false;
  }
  
  @Px
  public int getCustomSize() {
    return this.customSize;
  }
  
  public int getExpandedComponentIdHint() {
    return this.expandableWidgetHelper.getExpandedComponentIdHint();
  }
  
  public MotionSpec getHideMotionSpec() {
    return getImpl().getHideMotionSpec();
  }
  
  public void getMeasuredContentRect(@NonNull Rect paramRect) {
    paramRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    offsetRectWithShadow(paramRect);
  }
  
  @Deprecated
  @ColorInt
  public int getRippleColor() {
    boolean bool;
    if (this.rippleColor != null) {
      bool = this.rippleColor.getDefaultColor();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Nullable
  public ColorStateList getRippleColorStateList() {
    return this.rippleColor;
  }
  
  public MotionSpec getShowMotionSpec() {
    return getImpl().getShowMotionSpec();
  }
  
  public int getSize() {
    return this.size;
  }
  
  int getSizeDimension() {
    return getSizeDimension(this.size);
  }
  
  @Nullable
  public ColorStateList getSupportBackgroundTintList() {
    return getBackgroundTintList();
  }
  
  @Nullable
  public PorterDuff.Mode getSupportBackgroundTintMode() {
    return getBackgroundTintMode();
  }
  
  @Nullable
  public ColorStateList getSupportImageTintList() {
    return this.imageTint;
  }
  
  @Nullable
  public PorterDuff.Mode getSupportImageTintMode() {
    return this.imageMode;
  }
  
  public boolean getUseCompatPadding() {
    return this.compatPadding;
  }
  
  public void hide() {
    hide((OnVisibilityChangedListener)null);
  }
  
  public void hide(@Nullable OnVisibilityChangedListener paramOnVisibilityChangedListener) {
    hide(paramOnVisibilityChangedListener, true);
  }
  
  void hide(@Nullable OnVisibilityChangedListener paramOnVisibilityChangedListener, boolean paramBoolean) {
    getImpl().hide(wrapOnVisibilityChangedListener(paramOnVisibilityChangedListener), paramBoolean);
  }
  
  public boolean isExpanded() {
    return this.expandableWidgetHelper.isExpanded();
  }
  
  public boolean isOrWillBeHidden() {
    return getImpl().isOrWillBeHidden();
  }
  
  public boolean isOrWillBeShown() {
    return getImpl().isOrWillBeShown();
  }
  
  public void jumpDrawablesToCurrentState() {
    super.jumpDrawablesToCurrentState();
    getImpl().jumpDrawableToCurrentState();
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    getImpl().onAttachedToWindow();
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    getImpl().onDetachedFromWindow();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = getSizeDimension();
    this.imagePadding = (i - this.maxImageSize) / 2;
    getImpl().updatePadding();
    paramInt1 = Math.min(resolveAdjustedSize(i, paramInt1), resolveAdjustedSize(i, paramInt2));
    setMeasuredDimension(this.shadowPadding.left + paramInt1 + this.shadowPadding.right, paramInt1 + this.shadowPadding.top + this.shadowPadding.bottom);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof ExtendableSavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    ExtendableSavedState extendableSavedState = (ExtendableSavedState)paramParcelable;
    super.onRestoreInstanceState(extendableSavedState.getSuperState());
    this.expandableWidgetHelper.onRestoreInstanceState((Bundle)extendableSavedState.extendableStates.get("expandableWidgetHelper"));
  }
  
  protected Parcelable onSaveInstanceState() {
    ExtendableSavedState extendableSavedState = new ExtendableSavedState(super.onSaveInstanceState());
    extendableSavedState.extendableStates.put("expandableWidgetHelper", this.expandableWidgetHelper.onSaveInstanceState());
    return (Parcelable)extendableSavedState;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return (paramMotionEvent.getAction() == 0 && getContentRect(this.touchArea) && !this.touchArea.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) ? false : super.onTouchEvent(paramMotionEvent);
  }
  
  public void removeOnHideAnimationListener(@NonNull Animator.AnimatorListener paramAnimatorListener) {
    getImpl().removeOnHideAnimationListener(paramAnimatorListener);
  }
  
  public void removeOnShowAnimationListener(@NonNull Animator.AnimatorListener paramAnimatorListener) {
    getImpl().removeOnShowAnimationListener(paramAnimatorListener);
  }
  
  public void setBackgroundColor(int paramInt) {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundResource(int paramInt) {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    if (this.backgroundTint != paramColorStateList) {
      this.backgroundTint = paramColorStateList;
      getImpl().setBackgroundTintList(paramColorStateList);
    } 
  }
  
  public void setBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    if (this.backgroundTintMode != paramMode) {
      this.backgroundTintMode = paramMode;
      getImpl().setBackgroundTintMode(paramMode);
    } 
  }
  
  public void setCompatElevation(float paramFloat) {
    getImpl().setElevation(paramFloat);
  }
  
  public void setCompatElevationResource(@DimenRes int paramInt) {
    setCompatElevation(getResources().getDimension(paramInt));
  }
  
  public void setCompatHoveredFocusedTranslationZ(float paramFloat) {
    getImpl().setHoveredFocusedTranslationZ(paramFloat);
  }
  
  public void setCompatHoveredFocusedTranslationZResource(@DimenRes int paramInt) {
    setCompatHoveredFocusedTranslationZ(getResources().getDimension(paramInt));
  }
  
  public void setCompatPressedTranslationZ(float paramFloat) {
    getImpl().setPressedTranslationZ(paramFloat);
  }
  
  public void setCompatPressedTranslationZResource(@DimenRes int paramInt) {
    setCompatPressedTranslationZ(getResources().getDimension(paramInt));
  }
  
  public void setCustomSize(@Px int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentException("Custom size must be non-negative"); 
    this.customSize = paramInt;
  }
  
  public boolean setExpanded(boolean paramBoolean) {
    return this.expandableWidgetHelper.setExpanded(paramBoolean);
  }
  
  public void setExpandedComponentIdHint(@IdRes int paramInt) {
    this.expandableWidgetHelper.setExpandedComponentIdHint(paramInt);
  }
  
  public void setHideMotionSpec(MotionSpec paramMotionSpec) {
    getImpl().setHideMotionSpec(paramMotionSpec);
  }
  
  public void setHideMotionSpecResource(@AnimatorRes int paramInt) {
    setHideMotionSpec(MotionSpec.createFromResource(getContext(), paramInt));
  }
  
  public void setImageDrawable(@Nullable Drawable paramDrawable) {
    super.setImageDrawable(paramDrawable);
    getImpl().updateImageMatrixScale();
  }
  
  public void setImageResource(@DrawableRes int paramInt) {
    this.imageHelper.setImageResource(paramInt);
  }
  
  public void setRippleColor(@ColorInt int paramInt) {
    setRippleColor(ColorStateList.valueOf(paramInt));
  }
  
  public void setRippleColor(@Nullable ColorStateList paramColorStateList) {
    if (this.rippleColor != paramColorStateList) {
      this.rippleColor = paramColorStateList;
      getImpl().setRippleColor(this.rippleColor);
    } 
  }
  
  public void setShowMotionSpec(MotionSpec paramMotionSpec) {
    getImpl().setShowMotionSpec(paramMotionSpec);
  }
  
  public void setShowMotionSpecResource(@AnimatorRes int paramInt) {
    setShowMotionSpec(MotionSpec.createFromResource(getContext(), paramInt));
  }
  
  public void setSize(int paramInt) {
    this.customSize = 0;
    if (paramInt != this.size) {
      this.size = paramInt;
      requestLayout();
    } 
  }
  
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    setBackgroundTintList(paramColorStateList);
  }
  
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    setBackgroundTintMode(paramMode);
  }
  
  public void setSupportImageTintList(@Nullable ColorStateList paramColorStateList) {
    if (this.imageTint != paramColorStateList) {
      this.imageTint = paramColorStateList;
      onApplySupportImageTint();
    } 
  }
  
  public void setSupportImageTintMode(@Nullable PorterDuff.Mode paramMode) {
    if (this.imageMode != paramMode) {
      this.imageMode = paramMode;
      onApplySupportImageTint();
    } 
  }
  
  public void setUseCompatPadding(boolean paramBoolean) {
    if (this.compatPadding != paramBoolean) {
      this.compatPadding = paramBoolean;
      getImpl().onCompatShadowChanged();
    } 
  }
  
  public void show() {
    show((OnVisibilityChangedListener)null);
  }
  
  public void show(@Nullable OnVisibilityChangedListener paramOnVisibilityChangedListener) {
    show(paramOnVisibilityChangedListener, true);
  }
  
  void show(OnVisibilityChangedListener paramOnVisibilityChangedListener, boolean paramBoolean) {
    getImpl().show(wrapOnVisibilityChangedListener(paramOnVisibilityChangedListener), paramBoolean);
  }
  
  protected static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
    private static final boolean AUTO_HIDE_DEFAULT = true;
    
    private boolean autoHideEnabled;
    
    private FloatingActionButton.OnVisibilityChangedListener internalAutoHideListener;
    
    private Rect tmpRect;
    
    public BaseBehavior() {
      this.autoHideEnabled = true;
    }
    
    public BaseBehavior(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.FloatingActionButton_Behavior_Layout);
      this.autoHideEnabled = typedArray.getBoolean(R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
      typedArray.recycle();
    }
    
    private static boolean isBottomSheet(@NonNull View param1View) {
      ViewGroup.LayoutParams layoutParams = param1View.getLayoutParams();
      return (layoutParams instanceof CoordinatorLayout.LayoutParams) ? (((CoordinatorLayout.LayoutParams)layoutParams).getBehavior() instanceof BottomSheetBehavior) : false;
    }
    
    private void offsetIfNeeded(CoordinatorLayout param1CoordinatorLayout, FloatingActionButton param1FloatingActionButton) {
      Rect rect = param1FloatingActionButton.shadowPadding;
      if (rect != null && rect.centerX() > 0 && rect.centerY() > 0) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)param1FloatingActionButton.getLayoutParams();
        int i = param1FloatingActionButton.getRight();
        int j = param1CoordinatorLayout.getWidth();
        int k = layoutParams.rightMargin;
        int m = 0;
        if (i >= j - k) {
          k = rect.right;
        } else if (param1FloatingActionButton.getLeft() <= layoutParams.leftMargin) {
          k = -rect.left;
        } else {
          k = 0;
        } 
        if (param1FloatingActionButton.getBottom() >= param1CoordinatorLayout.getHeight() - layoutParams.bottomMargin) {
          m = rect.bottom;
        } else if (param1FloatingActionButton.getTop() <= layoutParams.topMargin) {
          m = -rect.top;
        } 
        if (m != 0)
          ViewCompat.offsetTopAndBottom((View)param1FloatingActionButton, m); 
        if (k != 0)
          ViewCompat.offsetLeftAndRight((View)param1FloatingActionButton, k); 
      } 
    }
    
    private boolean shouldUpdateVisibility(View param1View, FloatingActionButton param1FloatingActionButton) {
      CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)param1FloatingActionButton.getLayoutParams();
      return !this.autoHideEnabled ? false : ((layoutParams.getAnchorId() != param1View.getId()) ? false : (!(param1FloatingActionButton.getUserSetVisibility() != 0)));
    }
    
    private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout param1CoordinatorLayout, AppBarLayout param1AppBarLayout, FloatingActionButton param1FloatingActionButton) {
      if (!shouldUpdateVisibility((View)param1AppBarLayout, param1FloatingActionButton))
        return false; 
      if (this.tmpRect == null)
        this.tmpRect = new Rect(); 
      Rect rect = this.tmpRect;
      DescendantOffsetUtils.getDescendantRect(param1CoordinatorLayout, (View)param1AppBarLayout, rect);
      if (rect.bottom <= param1AppBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
        param1FloatingActionButton.hide(this.internalAutoHideListener, false);
      } else {
        param1FloatingActionButton.show(this.internalAutoHideListener, false);
      } 
      return true;
    }
    
    private boolean updateFabVisibilityForBottomSheet(View param1View, FloatingActionButton param1FloatingActionButton) {
      if (!shouldUpdateVisibility(param1View, param1FloatingActionButton))
        return false; 
      CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)param1FloatingActionButton.getLayoutParams();
      if (param1View.getTop() < param1FloatingActionButton.getHeight() / 2 + layoutParams.topMargin) {
        param1FloatingActionButton.hide(this.internalAutoHideListener, false);
      } else {
        param1FloatingActionButton.show(this.internalAutoHideListener, false);
      } 
      return true;
    }
    
    public boolean getInsetDodgeRect(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull FloatingActionButton param1FloatingActionButton, @NonNull Rect param1Rect) {
      Rect rect = param1FloatingActionButton.shadowPadding;
      param1Rect.set(param1FloatingActionButton.getLeft() + rect.left, param1FloatingActionButton.getTop() + rect.top, param1FloatingActionButton.getRight() - rect.right, param1FloatingActionButton.getBottom() - rect.bottom);
      return true;
    }
    
    public boolean isAutoHideEnabled() {
      return this.autoHideEnabled;
    }
    
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams param1LayoutParams) {
      if (param1LayoutParams.dodgeInsetEdges == 0)
        param1LayoutParams.dodgeInsetEdges = 80; 
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout param1CoordinatorLayout, FloatingActionButton param1FloatingActionButton, View param1View) {
      if (param1View instanceof AppBarLayout) {
        updateFabVisibilityForAppBarLayout(param1CoordinatorLayout, (AppBarLayout)param1View, param1FloatingActionButton);
      } else if (isBottomSheet(param1View)) {
        updateFabVisibilityForBottomSheet(param1View, param1FloatingActionButton);
      } 
      return false;
    }
    
    public boolean onLayoutChild(CoordinatorLayout param1CoordinatorLayout, FloatingActionButton param1FloatingActionButton, int param1Int) {
      List<View> list = param1CoordinatorLayout.getDependencies((View)param1FloatingActionButton);
      int i = list.size();
      for (byte b = 0; b < i; b++) {
        View view = list.get(b);
        if ((view instanceof AppBarLayout) ? updateFabVisibilityForAppBarLayout(param1CoordinatorLayout, (AppBarLayout)view, param1FloatingActionButton) : (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, param1FloatingActionButton)))
          break; 
      } 
      param1CoordinatorLayout.onLayoutChild((View)param1FloatingActionButton, param1Int);
      offsetIfNeeded(param1CoordinatorLayout, param1FloatingActionButton);
      return true;
    }
    
    public void setAutoHideEnabled(boolean param1Boolean) {
      this.autoHideEnabled = param1Boolean;
    }
    
    @VisibleForTesting
    public void setInternalAutoHideListener(FloatingActionButton.OnVisibilityChangedListener param1OnVisibilityChangedListener) {
      this.internalAutoHideListener = param1OnVisibilityChangedListener;
    }
  }
  
  public static class Behavior extends BaseBehavior<FloatingActionButton> {
    public Behavior() {}
    
    public Behavior(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
  }
  
  public static abstract class OnVisibilityChangedListener {
    public void onHidden(FloatingActionButton param1FloatingActionButton) {}
    
    public void onShown(FloatingActionButton param1FloatingActionButton) {}
  }
  
  private class ShadowDelegateImpl implements ShadowViewDelegate {
    public float getRadius() {
      return FloatingActionButton.this.getSizeDimension() / 2.0F;
    }
    
    public boolean isCompatPaddingEnabled() {
      return FloatingActionButton.this.compatPadding;
    }
    
    public void setBackgroundDrawable(Drawable param1Drawable) {
      FloatingActionButton.this.setBackgroundDrawable(param1Drawable);
    }
    
    public void setShadowPadding(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      FloatingActionButton.this.shadowPadding.set(param1Int1, param1Int2, param1Int3, param1Int4);
      FloatingActionButton.this.setPadding(param1Int1 + FloatingActionButton.this.imagePadding, param1Int2 + FloatingActionButton.this.imagePadding, param1Int3 + FloatingActionButton.this.imagePadding, param1Int4 + FloatingActionButton.this.imagePadding);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Size {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\FloatingActionButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */