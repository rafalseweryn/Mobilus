package android.support.design.chip;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimatorRes;
import android.support.annotation.BoolRes;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.R;
import android.support.design.animation.MotionSpec;
import android.support.design.internal.ViewUtils;
import android.support.design.resources.TextAppearance;
import android.support.design.ripple.RippleUtils;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate {
  private static final int CLOSE_ICON_VIRTUAL_ID = 0;
  
  private static final Rect EMPTY_BOUNDS = new Rect();
  
  private static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
  
  private static final int[] SELECTED_STATE = new int[] { 16842913 };
  
  private static final String TAG = "Chip";
  
  @Nullable
  private ChipDrawable chipDrawable;
  
  private boolean closeIconFocused;
  
  private boolean closeIconHovered;
  
  private boolean closeIconPressed;
  
  private boolean deferredCheckedValue;
  
  private int focusedVirtualView = Integer.MIN_VALUE;
  
  private final ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback() {
      public void onFontRetrievalFailed(int param1Int) {}
      
      public void onFontRetrieved(@NonNull Typeface param1Typeface) {
        Chip.this.setText(Chip.this.getText());
        Chip.this.requestLayout();
        Chip.this.invalidate();
      }
    };
  
  @Nullable
  private CompoundButton.OnCheckedChangeListener onCheckedChangeListenerInternal;
  
  @Nullable
  private View.OnClickListener onCloseIconClickListener;
  
  private final Rect rect = new Rect();
  
  private final RectF rectF = new RectF();
  
  @Nullable
  private RippleDrawable ripple;
  
  private final ChipTouchHelper touchHelper;
  
  public Chip(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public Chip(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.chipStyle);
  }
  
  public Chip(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    validateAttributes(paramAttributeSet);
    ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(paramContext, paramAttributeSet, paramInt, R.style.Widget_MaterialComponents_Chip_Action);
    setChipDrawable(chipDrawable);
    this.touchHelper = new ChipTouchHelper(this);
    ViewCompat.setAccessibilityDelegate((View)this, (AccessibilityDelegateCompat)this.touchHelper);
    initOutlineProvider();
    setChecked(this.deferredCheckedValue);
    chipDrawable.setShouldDrawText(false);
    setText(chipDrawable.getText());
    setEllipsize(chipDrawable.getEllipsize());
    setIncludeFontPadding(false);
    if (getTextAppearance() != null)
      updateTextPaintDrawState(getTextAppearance()); 
    setSingleLine();
    setGravity(8388627);
    updatePaddingInternal();
  }
  
  private void applyChipDrawable(@NonNull ChipDrawable paramChipDrawable) {
    paramChipDrawable.setDelegate(this);
  }
  
  private float calculateTextOffsetFromStart(@NonNull ChipDrawable paramChipDrawable) {
    float f = getChipStartPadding() + paramChipDrawable.calculateChipIconWidth() + getTextStartPadding();
    return (ViewCompat.getLayoutDirection((View)this) == 0) ? f : -f;
  }
  
  private int[] createCloseIconDrawableState() {
    boolean bool = isEnabled();
    boolean bool1 = false;
    if (bool) {
      i = 1;
    } else {
      i = 0;
    } 
    int j = i;
    if (this.closeIconFocused)
      j = i + 1; 
    int i = j;
    if (this.closeIconHovered)
      i = j + 1; 
    j = i;
    if (this.closeIconPressed)
      j = i + 1; 
    i = j;
    if (isChecked())
      i = j + 1; 
    int[] arrayOfInt = new int[i];
    i = bool1;
    if (isEnabled()) {
      arrayOfInt[0] = 16842910;
      i = 1;
    } 
    j = i;
    if (this.closeIconFocused) {
      arrayOfInt[i] = 16842908;
      j = i + 1;
    } 
    i = j;
    if (this.closeIconHovered) {
      arrayOfInt[j] = 16843623;
      i = j + 1;
    } 
    j = i;
    if (this.closeIconPressed) {
      arrayOfInt[i] = 16842919;
      j = i + 1;
    } 
    if (isChecked())
      arrayOfInt[j] = 16842913; 
    return arrayOfInt;
  }
  
  private void ensureFocus() {
    if (this.focusedVirtualView == Integer.MIN_VALUE)
      setFocusedVirtualView(-1); 
  }
  
  private RectF getCloseIconTouchBounds() {
    this.rectF.setEmpty();
    if (hasCloseIcon())
      this.chipDrawable.getCloseIconTouchBounds(this.rectF); 
    return this.rectF;
  }
  
  private Rect getCloseIconTouchBoundsInt() {
    RectF rectF = getCloseIconTouchBounds();
    this.rect.set((int)rectF.left, (int)rectF.top, (int)rectF.right, (int)rectF.bottom);
    return this.rect;
  }
  
  @Nullable
  private TextAppearance getTextAppearance() {
    TextAppearance textAppearance;
    if (this.chipDrawable != null) {
      textAppearance = this.chipDrawable.getTextAppearance();
    } else {
      textAppearance = null;
    } 
    return textAppearance;
  }
  
  @SuppressLint({"PrivateApi"})
  private boolean handleAccessibilityExit(MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 10)
      try {
        Field field = ExploreByTouchHelper.class.getDeclaredField("mHoveredVirtualViewId");
        field.setAccessible(true);
        if (((Integer)field.get(this.touchHelper)).intValue() != Integer.MIN_VALUE) {
          Method method = ExploreByTouchHelper.class.getDeclaredMethod("updateHoveredVirtualView", new Class[] { int.class });
          method.setAccessible(true);
          method.invoke(this.touchHelper, new Object[] { Integer.valueOf(-2147483648) });
          return true;
        } 
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.e("Chip", "Unable to send Accessibility Exit event", noSuchMethodException);
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("Chip", "Unable to send Accessibility Exit event", illegalAccessException);
      } catch (InvocationTargetException invocationTargetException) {
        Log.e("Chip", "Unable to send Accessibility Exit event", invocationTargetException);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("Chip", "Unable to send Accessibility Exit event", noSuchFieldException);
      }  
    return false;
  }
  
  private boolean hasCloseIcon() {
    boolean bool;
    if (this.chipDrawable != null && this.chipDrawable.getCloseIcon() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void initOutlineProvider() {
    if (Build.VERSION.SDK_INT >= 21)
      setOutlineProvider(new ViewOutlineProvider() {
            @TargetApi(21)
            public void getOutline(View param1View, Outline param1Outline) {
              if (Chip.this.chipDrawable != null) {
                Chip.this.chipDrawable.getOutline(param1Outline);
              } else {
                param1Outline.setAlpha(0.0F);
              } 
            }
          }); 
  }
  
  private boolean moveFocus(boolean paramBoolean) {
    ensureFocus();
    boolean bool = true;
    if (paramBoolean) {
      if (this.focusedVirtualView == -1) {
        setFocusedVirtualView(0);
        return bool;
      } 
    } else if (this.focusedVirtualView == 0) {
      setFocusedVirtualView(-1);
      return bool;
    } 
    return false;
  }
  
  private void setCloseIconFocused(boolean paramBoolean) {
    if (this.closeIconFocused != paramBoolean) {
      this.closeIconFocused = paramBoolean;
      refreshDrawableState();
    } 
  }
  
  private void setCloseIconHovered(boolean paramBoolean) {
    if (this.closeIconHovered != paramBoolean) {
      this.closeIconHovered = paramBoolean;
      refreshDrawableState();
    } 
  }
  
  private void setCloseIconPressed(boolean paramBoolean) {
    if (this.closeIconPressed != paramBoolean) {
      this.closeIconPressed = paramBoolean;
      refreshDrawableState();
    } 
  }
  
  private void setFocusedVirtualView(int paramInt) {
    if (this.focusedVirtualView != paramInt) {
      if (this.focusedVirtualView == 0)
        setCloseIconFocused(false); 
      this.focusedVirtualView = paramInt;
      if (paramInt == 0)
        setCloseIconFocused(true); 
    } 
  }
  
  private void unapplyChipDrawable(@Nullable ChipDrawable paramChipDrawable) {
    if (paramChipDrawable != null)
      paramChipDrawable.setDelegate((ChipDrawable.Delegate)null); 
  }
  
  private void updatePaddingInternal() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getText : ()Ljava/lang/CharSequence;
    //   4: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   7: ifne -> 210
    //   10: aload_0
    //   11: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   14: ifnonnull -> 20
    //   17: goto -> 210
    //   20: aload_0
    //   21: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   24: invokevirtual getChipStartPadding : ()F
    //   27: aload_0
    //   28: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   31: invokevirtual getChipEndPadding : ()F
    //   34: fadd
    //   35: aload_0
    //   36: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   39: invokevirtual getTextStartPadding : ()F
    //   42: fadd
    //   43: aload_0
    //   44: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   47: invokevirtual getTextEndPadding : ()F
    //   50: fadd
    //   51: fstore_1
    //   52: aload_0
    //   53: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   56: invokevirtual isChipIconVisible : ()Z
    //   59: ifeq -> 72
    //   62: aload_0
    //   63: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   66: invokevirtual getChipIcon : ()Landroid/graphics/drawable/Drawable;
    //   69: ifnonnull -> 105
    //   72: fload_1
    //   73: fstore_2
    //   74: aload_0
    //   75: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   78: invokevirtual getCheckedIcon : ()Landroid/graphics/drawable/Drawable;
    //   81: ifnull -> 131
    //   84: fload_1
    //   85: fstore_2
    //   86: aload_0
    //   87: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   90: invokevirtual isCheckedIconVisible : ()Z
    //   93: ifeq -> 131
    //   96: fload_1
    //   97: fstore_2
    //   98: aload_0
    //   99: invokevirtual isChecked : ()Z
    //   102: ifeq -> 131
    //   105: fload_1
    //   106: aload_0
    //   107: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   110: invokevirtual getIconStartPadding : ()F
    //   113: aload_0
    //   114: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   117: invokevirtual getIconEndPadding : ()F
    //   120: fadd
    //   121: aload_0
    //   122: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   125: invokevirtual getChipIconSize : ()F
    //   128: fadd
    //   129: fadd
    //   130: fstore_2
    //   131: fload_2
    //   132: fstore_1
    //   133: aload_0
    //   134: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   137: invokevirtual isCloseIconVisible : ()Z
    //   140: ifeq -> 181
    //   143: fload_2
    //   144: fstore_1
    //   145: aload_0
    //   146: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   149: invokevirtual getCloseIcon : ()Landroid/graphics/drawable/Drawable;
    //   152: ifnull -> 181
    //   155: fload_2
    //   156: aload_0
    //   157: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   160: invokevirtual getCloseIconStartPadding : ()F
    //   163: aload_0
    //   164: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   167: invokevirtual getCloseIconEndPadding : ()F
    //   170: fadd
    //   171: aload_0
    //   172: getfield chipDrawable : Landroid/support/design/chip/ChipDrawable;
    //   175: invokevirtual getCloseIconSize : ()F
    //   178: fadd
    //   179: fadd
    //   180: fstore_1
    //   181: aload_0
    //   182: invokestatic getPaddingEnd : (Landroid/view/View;)I
    //   185: i2f
    //   186: fload_1
    //   187: fcmpl
    //   188: ifeq -> 209
    //   191: aload_0
    //   192: aload_0
    //   193: invokestatic getPaddingStart : (Landroid/view/View;)I
    //   196: aload_0
    //   197: invokevirtual getPaddingTop : ()I
    //   200: fload_1
    //   201: f2i
    //   202: aload_0
    //   203: invokevirtual getPaddingBottom : ()I
    //   206: invokestatic setPaddingRelative : (Landroid/view/View;IIII)V
    //   209: return
    //   210: return
  }
  
  private void updateTextPaintDrawState(TextAppearance paramTextAppearance) {
    TextPaint textPaint = getPaint();
    textPaint.drawableState = this.chipDrawable.getState();
    paramTextAppearance.updateDrawState(getContext(), textPaint, this.fontCallback);
  }
  
  private void validateAttributes(@Nullable AttributeSet paramAttributeSet) {
    if (paramAttributeSet == null)
      return; 
    if (paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null)
      throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable."); 
    if (paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null)
      throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon."); 
    if (paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null)
      throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon."); 
    if (paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null)
      throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon."); 
    if (paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null)
      throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon."); 
    if (!paramAttributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || paramAttributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || paramAttributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || paramAttributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1)
      throw new UnsupportedOperationException("Chip does not support multi-line text"); 
    if (paramAttributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627)
      Log.w("Chip", "Chip text must be vertically center and start aligned"); 
  }
  
  protected boolean dispatchHoverEvent(MotionEvent paramMotionEvent) {
    return (handleAccessibilityExit(paramMotionEvent) || this.touchHelper.dispatchHoverEvent(paramMotionEvent) || super.dispatchHoverEvent(paramMotionEvent));
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    return (this.touchHelper.dispatchKeyEvent(paramKeyEvent) || super.dispatchKeyEvent(paramKeyEvent));
  }
  
  protected void drawableStateChanged() {
    boolean bool;
    super.drawableStateChanged();
    if (this.chipDrawable != null && this.chipDrawable.isCloseIconStateful()) {
      bool = this.chipDrawable.setCloseIconState(createCloseIconDrawableState());
    } else {
      bool = false;
    } 
    if (bool)
      invalidate(); 
  }
  
  @Nullable
  public Drawable getCheckedIcon() {
    Drawable drawable;
    if (this.chipDrawable != null) {
      drawable = this.chipDrawable.getCheckedIcon();
    } else {
      drawable = null;
    } 
    return drawable;
  }
  
  @Nullable
  public ColorStateList getChipBackgroundColor() {
    ColorStateList colorStateList;
    if (this.chipDrawable != null) {
      colorStateList = this.chipDrawable.getChipBackgroundColor();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  public float getChipCornerRadius() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getChipCornerRadius();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public Drawable getChipDrawable() {
    return this.chipDrawable;
  }
  
  public float getChipEndPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getChipEndPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  @Nullable
  public Drawable getChipIcon() {
    Drawable drawable;
    if (this.chipDrawable != null) {
      drawable = this.chipDrawable.getChipIcon();
    } else {
      drawable = null;
    } 
    return drawable;
  }
  
  public float getChipIconSize() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getChipIconSize();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  @Nullable
  public ColorStateList getChipIconTint() {
    ColorStateList colorStateList;
    if (this.chipDrawable != null) {
      colorStateList = this.chipDrawable.getChipIconTint();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  public float getChipMinHeight() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getChipMinHeight();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getChipStartPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getChipStartPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  @Nullable
  public ColorStateList getChipStrokeColor() {
    ColorStateList colorStateList;
    if (this.chipDrawable != null) {
      colorStateList = this.chipDrawable.getChipStrokeColor();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  public float getChipStrokeWidth() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getChipStrokeWidth();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  @Deprecated
  public CharSequence getChipText() {
    return getText();
  }
  
  @Nullable
  public Drawable getCloseIcon() {
    Drawable drawable;
    if (this.chipDrawable != null) {
      drawable = this.chipDrawable.getCloseIcon();
    } else {
      drawable = null;
    } 
    return drawable;
  }
  
  @Nullable
  public CharSequence getCloseIconContentDescription() {
    CharSequence charSequence;
    if (this.chipDrawable != null) {
      charSequence = this.chipDrawable.getCloseIconContentDescription();
    } else {
      charSequence = null;
    } 
    return charSequence;
  }
  
  public float getCloseIconEndPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getCloseIconEndPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getCloseIconSize() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getCloseIconSize();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getCloseIconStartPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getCloseIconStartPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  @Nullable
  public ColorStateList getCloseIconTint() {
    ColorStateList colorStateList;
    if (this.chipDrawable != null) {
      colorStateList = this.chipDrawable.getCloseIconTint();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  public TextUtils.TruncateAt getEllipsize() {
    TextUtils.TruncateAt truncateAt;
    if (this.chipDrawable != null) {
      truncateAt = this.chipDrawable.getEllipsize();
    } else {
      truncateAt = null;
    } 
    return truncateAt;
  }
  
  public void getFocusedRect(Rect paramRect) {
    if (this.focusedVirtualView == 0) {
      paramRect.set(getCloseIconTouchBoundsInt());
    } else {
      super.getFocusedRect(paramRect);
    } 
  }
  
  @Nullable
  public MotionSpec getHideMotionSpec() {
    MotionSpec motionSpec;
    if (this.chipDrawable != null) {
      motionSpec = this.chipDrawable.getHideMotionSpec();
    } else {
      motionSpec = null;
    } 
    return motionSpec;
  }
  
  public float getIconEndPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getIconEndPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getIconStartPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getIconStartPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  @Nullable
  public ColorStateList getRippleColor() {
    ColorStateList colorStateList;
    if (this.chipDrawable != null) {
      colorStateList = this.chipDrawable.getRippleColor();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  @Nullable
  public MotionSpec getShowMotionSpec() {
    MotionSpec motionSpec;
    if (this.chipDrawable != null) {
      motionSpec = this.chipDrawable.getShowMotionSpec();
    } else {
      motionSpec = null;
    } 
    return motionSpec;
  }
  
  public CharSequence getText() {
    CharSequence charSequence;
    if (this.chipDrawable != null) {
      charSequence = this.chipDrawable.getText();
    } else {
      charSequence = "";
    } 
    return charSequence;
  }
  
  public float getTextEndPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getTextEndPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getTextStartPadding() {
    float f;
    if (this.chipDrawable != null) {
      f = this.chipDrawable.getTextStartPadding();
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public boolean isCheckable() {
    boolean bool;
    if (this.chipDrawable != null && this.chipDrawable.isCheckable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isCheckedIconEnabled() {
    return isCheckedIconVisible();
  }
  
  public boolean isCheckedIconVisible() {
    boolean bool;
    if (this.chipDrawable != null && this.chipDrawable.isCheckedIconVisible()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isChipIconEnabled() {
    return isChipIconVisible();
  }
  
  public boolean isChipIconVisible() {
    boolean bool;
    if (this.chipDrawable != null && this.chipDrawable.isChipIconVisible()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isCloseIconEnabled() {
    return isCloseIconVisible();
  }
  
  public boolean isCloseIconVisible() {
    boolean bool;
    if (this.chipDrawable != null && this.chipDrawable.isCloseIconVisible()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onChipDrawableSizeChange() {
    updatePaddingInternal();
    requestLayout();
    if (Build.VERSION.SDK_INT >= 21)
      invalidateOutline(); 
  }
  
  protected int[] onCreateDrawableState(int paramInt) {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (isChecked())
      mergeDrawableStates(arrayOfInt, SELECTED_STATE); 
    return arrayOfInt;
  }
  
  protected void onDraw(Canvas paramCanvas) {
    if (TextUtils.isEmpty(getText()) || this.chipDrawable == null || this.chipDrawable.shouldDrawText()) {
      super.onDraw(paramCanvas);
      return;
    } 
    int i = paramCanvas.save();
    paramCanvas.translate(calculateTextOffsetFromStart(this.chipDrawable), 0.0F);
    super.onDraw(paramCanvas);
    paramCanvas.restoreToCount(i);
  }
  
  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect) {
    if (paramBoolean) {
      setFocusedVirtualView(-1);
    } else {
      setFocusedVirtualView(-2147483648);
    } 
    invalidate();
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    this.touchHelper.onFocusChanged(paramBoolean, paramInt, paramRect);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionMasked();
    if (i != 7) {
      if (i == 10)
        setCloseIconHovered(false); 
    } else {
      setCloseIconHovered(getCloseIconTouchBounds().contains(paramMotionEvent.getX(), paramMotionEvent.getY()));
    } 
    return super.onHoverEvent(paramMotionEvent);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool2;
    int i = paramKeyEvent.getKeyCode();
    boolean bool1 = false;
    if (i != 61) {
      if (i != 66) {
        switch (i) {
          default:
            bool2 = bool1;
            break;
          case 22:
            bool2 = bool1;
            if (paramKeyEvent.hasNoModifiers())
              bool2 = moveFocus(ViewUtils.isLayoutRtl((View)this) ^ true); 
            break;
          case 21:
            bool2 = bool1;
            if (paramKeyEvent.hasNoModifiers())
              bool2 = moveFocus(ViewUtils.isLayoutRtl((View)this)); 
            break;
          case 23:
            switch (this.focusedVirtualView) {
              default:
                bool2 = bool1;
                break;
              case 0:
                performCloseIconClick();
                return true;
              case -1:
                break;
            } 
            performClick();
            return true;
        } 
      } else {
      
      } 
    } else {
      if (paramKeyEvent.hasNoModifiers()) {
        i = 2;
      } else if (paramKeyEvent.hasModifiers(1)) {
        i = 1;
      } else {
        i = 0;
      } 
      bool2 = bool1;
      if (i != 0) {
        View view;
        ViewParent viewParent = getParent();
        Chip chip = this;
        while (true) {
          view = chip.focusSearch(i);
          if (view != null && view != this) {
            View view1 = view;
            if (view.getParent() != viewParent)
              break; 
            continue;
          } 
          break;
        } 
        bool2 = bool1;
        if (view != null) {
          view.requestFocus();
          return true;
        } 
      } 
    } 
    if (bool2) {
      invalidate();
      return true;
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  @TargetApi(24)
  public PointerIcon onResolvePointerIcon(MotionEvent paramMotionEvent, int paramInt) {
    return (getCloseIconTouchBounds().contains(paramMotionEvent.getX(), paramMotionEvent.getY()) && isEnabled()) ? PointerIcon.getSystemIcon(getContext(), 1002) : null;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getActionMasked : ()I
    //   4: istore_2
    //   5: aload_0
    //   6: invokespecial getCloseIconTouchBounds : ()Landroid/graphics/RectF;
    //   9: aload_1
    //   10: invokevirtual getX : ()F
    //   13: aload_1
    //   14: invokevirtual getY : ()F
    //   17: invokevirtual contains : (FF)Z
    //   20: istore_3
    //   21: iconst_0
    //   22: istore #4
    //   24: iload_2
    //   25: tableswitch default -> 56, 0 -> 105, 1 -> 78, 2 -> 59, 3 -> 95
    //   56: goto -> 119
    //   59: aload_0
    //   60: getfield closeIconPressed : Z
    //   63: ifeq -> 119
    //   66: iload_3
    //   67: ifne -> 114
    //   70: aload_0
    //   71: iconst_0
    //   72: invokespecial setCloseIconPressed : (Z)V
    //   75: goto -> 114
    //   78: aload_0
    //   79: getfield closeIconPressed : Z
    //   82: ifeq -> 95
    //   85: aload_0
    //   86: invokevirtual performCloseIconClick : ()Z
    //   89: pop
    //   90: iconst_1
    //   91: istore_2
    //   92: goto -> 97
    //   95: iconst_0
    //   96: istore_2
    //   97: aload_0
    //   98: iconst_0
    //   99: invokespecial setCloseIconPressed : (Z)V
    //   102: goto -> 121
    //   105: iload_3
    //   106: ifeq -> 119
    //   109: aload_0
    //   110: iconst_1
    //   111: invokespecial setCloseIconPressed : (Z)V
    //   114: iconst_1
    //   115: istore_2
    //   116: goto -> 121
    //   119: iconst_0
    //   120: istore_2
    //   121: iload_2
    //   122: ifne -> 133
    //   125: aload_0
    //   126: aload_1
    //   127: invokespecial onTouchEvent : (Landroid/view/MotionEvent;)Z
    //   130: ifeq -> 136
    //   133: iconst_1
    //   134: istore #4
    //   136: iload #4
    //   138: ireturn
  }
  
  @CallSuper
  public boolean performCloseIconClick() {
    boolean bool;
    playSoundEffect(0);
    if (this.onCloseIconClickListener != null) {
      this.onCloseIconClickListener.onClick((View)this);
      bool = true;
    } else {
      bool = false;
    } 
    this.touchHelper.sendEventForVirtualView(0, 1);
    return bool;
  }
  
  public void setBackground(Drawable paramDrawable) {
    if (paramDrawable != this.chipDrawable && paramDrawable != this.ripple)
      throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable."); 
    super.setBackground(paramDrawable);
  }
  
  public void setBackgroundColor(int paramInt) {
    throw new UnsupportedOperationException("Do not set the background color; Chip manages its own background drawable.");
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    if (paramDrawable != this.chipDrawable && paramDrawable != this.ripple)
      throw new UnsupportedOperationException("Do not set the background drawable; Chip manages its own background drawable."); 
    super.setBackgroundDrawable(paramDrawable);
  }
  
  public void setBackgroundResource(int paramInt) {
    throw new UnsupportedOperationException("Do not set the background resource; Chip manages its own background drawable.");
  }
  
  public void setBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    throw new UnsupportedOperationException("Do not set the background tint list; Chip manages its own background drawable.");
  }
  
  public void setBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    throw new UnsupportedOperationException("Do not set the background tint mode; Chip manages its own background drawable.");
  }
  
  public void setCheckable(boolean paramBoolean) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCheckable(paramBoolean); 
  }
  
  public void setCheckableResource(@BoolRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCheckableResource(paramInt); 
  }
  
  public void setChecked(boolean paramBoolean) {
    if (this.chipDrawable == null) {
      this.deferredCheckedValue = paramBoolean;
    } else if (this.chipDrawable.isCheckable()) {
      boolean bool = isChecked();
      super.setChecked(paramBoolean);
      if (bool != paramBoolean && this.onCheckedChangeListenerInternal != null)
        this.onCheckedChangeListenerInternal.onCheckedChanged((CompoundButton)this, paramBoolean); 
    } 
  }
  
  public void setCheckedIcon(@Nullable Drawable paramDrawable) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCheckedIcon(paramDrawable); 
  }
  
  @Deprecated
  public void setCheckedIconEnabled(boolean paramBoolean) {
    setCheckedIconVisible(paramBoolean);
  }
  
  @Deprecated
  public void setCheckedIconEnabledResource(@BoolRes int paramInt) {
    setCheckedIconVisible(paramInt);
  }
  
  public void setCheckedIconResource(@DrawableRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCheckedIconResource(paramInt); 
  }
  
  public void setCheckedIconVisible(@BoolRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCheckedIconVisible(paramInt); 
  }
  
  public void setCheckedIconVisible(boolean paramBoolean) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCheckedIconVisible(paramBoolean); 
  }
  
  public void setChipBackgroundColor(@Nullable ColorStateList paramColorStateList) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipBackgroundColor(paramColorStateList); 
  }
  
  public void setChipBackgroundColorResource(@ColorRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipBackgroundColorResource(paramInt); 
  }
  
  public void setChipCornerRadius(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipCornerRadius(paramFloat); 
  }
  
  public void setChipCornerRadiusResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipCornerRadiusResource(paramInt); 
  }
  
  public void setChipDrawable(@NonNull ChipDrawable paramChipDrawable) {
    if (this.chipDrawable != paramChipDrawable) {
      unapplyChipDrawable(this.chipDrawable);
      this.chipDrawable = paramChipDrawable;
      applyChipDrawable(this.chipDrawable);
      if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
        this.ripple = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(this.chipDrawable.getRippleColor()), this.chipDrawable, null);
        this.chipDrawable.setUseCompatRipple(false);
        ViewCompat.setBackground((View)this, (Drawable)this.ripple);
      } else {
        this.chipDrawable.setUseCompatRipple(true);
        ViewCompat.setBackground((View)this, this.chipDrawable);
      } 
    } 
  }
  
  public void setChipEndPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipEndPadding(paramFloat); 
  }
  
  public void setChipEndPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipEndPaddingResource(paramInt); 
  }
  
  public void setChipIcon(@Nullable Drawable paramDrawable) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIcon(paramDrawable); 
  }
  
  @Deprecated
  public void setChipIconEnabled(boolean paramBoolean) {
    setChipIconVisible(paramBoolean);
  }
  
  @Deprecated
  public void setChipIconEnabledResource(@BoolRes int paramInt) {
    setChipIconVisible(paramInt);
  }
  
  public void setChipIconResource(@DrawableRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIconResource(paramInt); 
  }
  
  public void setChipIconSize(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIconSize(paramFloat); 
  }
  
  public void setChipIconSizeResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIconSizeResource(paramInt); 
  }
  
  public void setChipIconTint(@Nullable ColorStateList paramColorStateList) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIconTint(paramColorStateList); 
  }
  
  public void setChipIconTintResource(@ColorRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIconTintResource(paramInt); 
  }
  
  public void setChipIconVisible(@BoolRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIconVisible(paramInt); 
  }
  
  public void setChipIconVisible(boolean paramBoolean) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipIconVisible(paramBoolean); 
  }
  
  public void setChipMinHeight(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipMinHeight(paramFloat); 
  }
  
  public void setChipMinHeightResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipMinHeightResource(paramInt); 
  }
  
  public void setChipStartPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipStartPadding(paramFloat); 
  }
  
  public void setChipStartPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipStartPaddingResource(paramInt); 
  }
  
  public void setChipStrokeColor(@Nullable ColorStateList paramColorStateList) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipStrokeColor(paramColorStateList); 
  }
  
  public void setChipStrokeColorResource(@ColorRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipStrokeColorResource(paramInt); 
  }
  
  public void setChipStrokeWidth(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipStrokeWidth(paramFloat); 
  }
  
  public void setChipStrokeWidthResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setChipStrokeWidthResource(paramInt); 
  }
  
  @Deprecated
  public void setChipText(@Nullable CharSequence paramCharSequence) {
    setText(paramCharSequence);
  }
  
  @Deprecated
  public void setChipTextResource(@StringRes int paramInt) {
    setText(getResources().getString(paramInt));
  }
  
  public void setCloseIcon(@Nullable Drawable paramDrawable) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIcon(paramDrawable); 
  }
  
  public void setCloseIconContentDescription(@Nullable CharSequence paramCharSequence) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconContentDescription(paramCharSequence); 
  }
  
  @Deprecated
  public void setCloseIconEnabled(boolean paramBoolean) {
    setCloseIconVisible(paramBoolean);
  }
  
  @Deprecated
  public void setCloseIconEnabledResource(@BoolRes int paramInt) {
    setCloseIconVisible(paramInt);
  }
  
  public void setCloseIconEndPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconEndPadding(paramFloat); 
  }
  
  public void setCloseIconEndPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconEndPaddingResource(paramInt); 
  }
  
  public void setCloseIconResource(@DrawableRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconResource(paramInt); 
  }
  
  public void setCloseIconSize(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconSize(paramFloat); 
  }
  
  public void setCloseIconSizeResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconSizeResource(paramInt); 
  }
  
  public void setCloseIconStartPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconStartPadding(paramFloat); 
  }
  
  public void setCloseIconStartPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconStartPaddingResource(paramInt); 
  }
  
  public void setCloseIconTint(@Nullable ColorStateList paramColorStateList) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconTint(paramColorStateList); 
  }
  
  public void setCloseIconTintResource(@ColorRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconTintResource(paramInt); 
  }
  
  public void setCloseIconVisible(@BoolRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconVisible(paramInt); 
  }
  
  public void setCloseIconVisible(boolean paramBoolean) {
    if (this.chipDrawable != null)
      this.chipDrawable.setCloseIconVisible(paramBoolean); 
  }
  
  public void setCompoundDrawables(@Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    if (paramDrawable1 != null)
      throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon."); 
    if (paramDrawable3 != null)
      throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon."); 
    super.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public void setCompoundDrawablesRelative(@Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    if (paramDrawable1 != null)
      throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon."); 
    if (paramDrawable3 != null)
      throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon."); 
    super.setCompoundDrawablesRelative(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public void setCompoundDrawablesRelativeWithIntrinsicBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 != 0)
      throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon."); 
    if (paramInt3 != 0)
      throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon."); 
    super.setCompoundDrawablesRelativeWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setCompoundDrawablesRelativeWithIntrinsicBounds(@Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    if (paramDrawable1 != null)
      throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon."); 
    if (paramDrawable3 != null)
      throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon."); 
    super.setCompoundDrawablesRelativeWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public void setCompoundDrawablesWithIntrinsicBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 != 0)
      throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon."); 
    if (paramInt3 != 0)
      throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon."); 
    super.setCompoundDrawablesWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    if (paramDrawable1 != null)
      throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon."); 
    if (paramDrawable3 != null)
      throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon."); 
    super.setCompoundDrawablesWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public void setEllipsize(TextUtils.TruncateAt paramTruncateAt) {
    if (this.chipDrawable == null)
      return; 
    if (paramTruncateAt == TextUtils.TruncateAt.MARQUEE)
      throw new UnsupportedOperationException("Text within a chip are not allowed to scroll."); 
    super.setEllipsize(paramTruncateAt);
    if (this.chipDrawable != null)
      this.chipDrawable.setEllipsize(paramTruncateAt); 
  }
  
  public void setGravity(int paramInt) {
    if (paramInt != 8388627) {
      Log.w("Chip", "Chip text must be vertically center and start aligned");
    } else {
      super.setGravity(paramInt);
    } 
  }
  
  public void setHideMotionSpec(@Nullable MotionSpec paramMotionSpec) {
    if (this.chipDrawable != null)
      this.chipDrawable.setHideMotionSpec(paramMotionSpec); 
  }
  
  public void setHideMotionSpecResource(@AnimatorRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setHideMotionSpecResource(paramInt); 
  }
  
  public void setIconEndPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setIconEndPadding(paramFloat); 
  }
  
  public void setIconEndPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setIconEndPaddingResource(paramInt); 
  }
  
  public void setIconStartPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setIconStartPadding(paramFloat); 
  }
  
  public void setIconStartPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setIconStartPaddingResource(paramInt); 
  }
  
  public void setLines(int paramInt) {
    if (paramInt > 1)
      throw new UnsupportedOperationException("Chip does not support multi-line text"); 
    super.setLines(paramInt);
  }
  
  public void setMaxLines(int paramInt) {
    if (paramInt > 1)
      throw new UnsupportedOperationException("Chip does not support multi-line text"); 
    super.setMaxLines(paramInt);
  }
  
  public void setMaxWidth(@Px int paramInt) {
    super.setMaxWidth(paramInt);
    if (this.chipDrawable != null)
      this.chipDrawable.setMaxWidth(paramInt); 
  }
  
  public void setMinLines(int paramInt) {
    if (paramInt > 1)
      throw new UnsupportedOperationException("Chip does not support multi-line text"); 
    super.setMinLines(paramInt);
  }
  
  void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener) {
    this.onCheckedChangeListenerInternal = paramOnCheckedChangeListener;
  }
  
  public void setOnCloseIconClickListener(View.OnClickListener paramOnClickListener) {
    this.onCloseIconClickListener = paramOnClickListener;
  }
  
  public void setRippleColor(@Nullable ColorStateList paramColorStateList) {
    if (this.chipDrawable != null)
      this.chipDrawable.setRippleColor(paramColorStateList); 
  }
  
  public void setRippleColorResource(@ColorRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setRippleColorResource(paramInt); 
  }
  
  public void setShowMotionSpec(@Nullable MotionSpec paramMotionSpec) {
    if (this.chipDrawable != null)
      this.chipDrawable.setShowMotionSpec(paramMotionSpec); 
  }
  
  public void setShowMotionSpecResource(@AnimatorRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setShowMotionSpecResource(paramInt); 
  }
  
  public void setSingleLine(boolean paramBoolean) {
    if (!paramBoolean)
      throw new UnsupportedOperationException("Chip does not support multi-line text"); 
    super.setSingleLine(paramBoolean);
  }
  
  public void setText(CharSequence paramCharSequence, TextView.BufferType paramBufferType) {
    if (this.chipDrawable == null)
      return; 
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = ""; 
    paramCharSequence = BidiFormatter.getInstance().unicodeWrap(charSequence);
    if (this.chipDrawable.shouldDrawText())
      paramCharSequence = null; 
    super.setText(paramCharSequence, paramBufferType);
    if (this.chipDrawable != null)
      this.chipDrawable.setText(charSequence); 
  }
  
  public void setTextAppearance(int paramInt) {
    super.setTextAppearance(paramInt);
    if (this.chipDrawable != null)
      this.chipDrawable.setTextAppearanceResource(paramInt); 
    if (getTextAppearance() != null) {
      getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
      updateTextPaintDrawState(getTextAppearance());
    } 
  }
  
  public void setTextAppearance(Context paramContext, int paramInt) {
    super.setTextAppearance(paramContext, paramInt);
    if (this.chipDrawable != null)
      this.chipDrawable.setTextAppearanceResource(paramInt); 
    if (getTextAppearance() != null) {
      getTextAppearance().updateMeasureState(paramContext, getPaint(), this.fontCallback);
      updateTextPaintDrawState(getTextAppearance());
    } 
  }
  
  public void setTextAppearance(@Nullable TextAppearance paramTextAppearance) {
    if (this.chipDrawable != null)
      this.chipDrawable.setTextAppearance(paramTextAppearance); 
    if (getTextAppearance() != null) {
      getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
      updateTextPaintDrawState(paramTextAppearance);
    } 
  }
  
  public void setTextAppearanceResource(@StyleRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setTextAppearanceResource(paramInt); 
    setTextAppearance(getContext(), paramInt);
  }
  
  public void setTextEndPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setTextEndPadding(paramFloat); 
  }
  
  public void setTextEndPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setTextEndPaddingResource(paramInt); 
  }
  
  public void setTextStartPadding(float paramFloat) {
    if (this.chipDrawable != null)
      this.chipDrawable.setTextStartPadding(paramFloat); 
  }
  
  public void setTextStartPaddingResource(@DimenRes int paramInt) {
    if (this.chipDrawable != null)
      this.chipDrawable.setTextStartPaddingResource(paramInt); 
  }
  
  private class ChipTouchHelper extends ExploreByTouchHelper {
    ChipTouchHelper(Chip param1Chip1) {
      super((View)param1Chip1);
    }
    
    protected int getVirtualViewAt(float param1Float1, float param1Float2) {
      byte b;
      if (Chip.this.hasCloseIcon() && Chip.this.getCloseIconTouchBounds().contains(param1Float1, param1Float2)) {
        b = 0;
      } else {
        b = -1;
      } 
      return b;
    }
    
    protected void getVisibleVirtualViews(List<Integer> param1List) {
      if (Chip.this.hasCloseIcon())
        param1List.add(Integer.valueOf(0)); 
    }
    
    protected boolean onPerformActionForVirtualView(int param1Int1, int param1Int2, Bundle param1Bundle) {
      return (param1Int2 == 16 && param1Int1 == 0) ? Chip.this.performCloseIconClick() : false;
    }
    
    protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      boolean bool;
      if (Chip.this.chipDrawable != null && Chip.this.chipDrawable.isCheckable()) {
        bool = true;
      } else {
        bool = false;
      } 
      param1AccessibilityNodeInfoCompat.setCheckable(bool);
      param1AccessibilityNodeInfoCompat.setClassName(Chip.class.getName());
      CharSequence charSequence = Chip.this.getText();
      if (Build.VERSION.SDK_INT >= 23) {
        param1AccessibilityNodeInfoCompat.setText(charSequence);
      } else {
        param1AccessibilityNodeInfoCompat.setContentDescription(charSequence);
      } 
    }
    
    protected void onPopulateNodeForVirtualView(int param1Int, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      if (Chip.this.hasCloseIcon()) {
        CharSequence charSequence = Chip.this.getCloseIconContentDescription();
        if (charSequence != null) {
          param1AccessibilityNodeInfoCompat.setContentDescription(charSequence);
        } else {
          charSequence = Chip.this.getText();
          Context context = Chip.this.getContext();
          param1Int = R.string.mtrl_chip_close_icon_content_description;
          if (TextUtils.isEmpty(charSequence))
            charSequence = ""; 
          param1AccessibilityNodeInfoCompat.setContentDescription(context.getString(param1Int, new Object[] { charSequence }).trim());
        } 
        param1AccessibilityNodeInfoCompat.setBoundsInParent(Chip.this.getCloseIconTouchBoundsInt());
        param1AccessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        param1AccessibilityNodeInfoCompat.setEnabled(Chip.this.isEnabled());
      } else {
        param1AccessibilityNodeInfoCompat.setContentDescription("");
        param1AccessibilityNodeInfoCompat.setBoundsInParent(Chip.EMPTY_BOUNDS);
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\chip\Chip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */