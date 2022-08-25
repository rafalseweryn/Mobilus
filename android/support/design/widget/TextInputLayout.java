package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.TintTypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TextInputLayout extends LinearLayout {
  public static final int BOX_BACKGROUND_FILLED = 1;
  
  public static final int BOX_BACKGROUND_NONE = 0;
  
  public static final int BOX_BACKGROUND_OUTLINE = 2;
  
  private static final int INVALID_MAX_LENGTH = -1;
  
  private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
  
  private static final String LOG_TAG = "TextInputLayout";
  
  private ValueAnimator animator;
  
  private GradientDrawable boxBackground;
  
  @ColorInt
  private int boxBackgroundColor;
  
  private int boxBackgroundMode;
  
  private final int boxBottomOffsetPx;
  
  private final int boxCollapsedPaddingTopPx;
  
  private float boxCornerRadiusBottomEnd;
  
  private float boxCornerRadiusBottomStart;
  
  private float boxCornerRadiusTopEnd;
  
  private float boxCornerRadiusTopStart;
  
  private final int boxLabelCutoutPaddingPx;
  
  @ColorInt
  private int boxStrokeColor;
  
  private final int boxStrokeWidthDefaultPx;
  
  private final int boxStrokeWidthFocusedPx;
  
  private int boxStrokeWidthPx;
  
  final CollapsingTextHelper collapsingTextHelper = new CollapsingTextHelper((View)this);
  
  boolean counterEnabled;
  
  private int counterMaxLength;
  
  private final int counterOverflowTextAppearance;
  
  private boolean counterOverflowed;
  
  private final int counterTextAppearance;
  
  private TextView counterView;
  
  private ColorStateList defaultHintTextColor;
  
  @ColorInt
  private final int defaultStrokeColor;
  
  @ColorInt
  private final int disabledColor;
  
  EditText editText;
  
  private Drawable editTextOriginalDrawable;
  
  @ColorInt
  private int focusedStrokeColor;
  
  private ColorStateList focusedTextColor;
  
  private boolean hasPasswordToggleTintList;
  
  private boolean hasPasswordToggleTintMode;
  
  private boolean hasReconstructedEditTextBackground;
  
  private CharSequence hint;
  
  private boolean hintAnimationEnabled;
  
  private boolean hintEnabled;
  
  private boolean hintExpanded;
  
  @ColorInt
  private final int hoveredStrokeColor;
  
  private boolean inDrawableStateChanged;
  
  private final IndicatorViewController indicatorViewController = new IndicatorViewController(this);
  
  private final FrameLayout inputFrame;
  
  private boolean isProvidingHint;
  
  private Drawable originalEditTextEndDrawable;
  
  private CharSequence originalHint;
  
  private CharSequence passwordToggleContentDesc;
  
  private Drawable passwordToggleDrawable;
  
  private Drawable passwordToggleDummyDrawable;
  
  private boolean passwordToggleEnabled;
  
  private ColorStateList passwordToggleTintList;
  
  private PorterDuff.Mode passwordToggleTintMode;
  
  private CheckableImageButton passwordToggleView;
  
  private boolean passwordToggledVisible;
  
  private boolean restoringSavedState;
  
  private final Rect tmpRect = new Rect();
  
  private final RectF tmpRectF = new RectF();
  
  private Typeface typeface;
  
  public TextInputLayout(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.textInputStyle);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    setOrientation(1);
    setWillNotDraw(false);
    setAddStatesFromChildren(true);
    this.inputFrame = new FrameLayout(paramContext);
    this.inputFrame.setAddStatesFromChildren(true);
    addView((View)this.inputFrame);
    this.collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
    this.collapsingTextHelper.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
    this.collapsingTextHelper.setCollapsedTextGravity(8388659);
    TintTypedArray tintTypedArray = ThemeEnforcement.obtainTintedStyledAttributes(paramContext, paramAttributeSet, R.styleable.TextInputLayout, paramInt, R.style.Widget_Design_TextInputLayout, new int[0]);
    this.hintEnabled = tintTypedArray.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
    setHint(tintTypedArray.getText(R.styleable.TextInputLayout_android_hint));
    this.hintAnimationEnabled = tintTypedArray.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
    this.boxBottomOffsetPx = paramContext.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_bottom_offset);
    this.boxLabelCutoutPaddingPx = paramContext.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_label_cutout_padding);
    this.boxCollapsedPaddingTopPx = tintTypedArray.getDimensionPixelOffset(R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
    this.boxCornerRadiusTopStart = tintTypedArray.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopStart, 0.0F);
    this.boxCornerRadiusTopEnd = tintTypedArray.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopEnd, 0.0F);
    this.boxCornerRadiusBottomEnd = tintTypedArray.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, 0.0F);
    this.boxCornerRadiusBottomStart = tintTypedArray.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomStart, 0.0F);
    this.boxBackgroundColor = tintTypedArray.getColor(R.styleable.TextInputLayout_boxBackgroundColor, 0);
    this.focusedStrokeColor = tintTypedArray.getColor(R.styleable.TextInputLayout_boxStrokeColor, 0);
    this.boxStrokeWidthDefaultPx = paramContext.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_default);
    this.boxStrokeWidthFocusedPx = paramContext.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_focused);
    this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
    setBoxBackgroundMode(tintTypedArray.getInt(R.styleable.TextInputLayout_boxBackgroundMode, 0));
    if (tintTypedArray.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
      ColorStateList colorStateList = tintTypedArray.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
      this.focusedTextColor = colorStateList;
      this.defaultHintTextColor = colorStateList;
    } 
    this.defaultStrokeColor = ContextCompat.getColor(paramContext, R.color.mtrl_textinput_default_box_stroke_color);
    this.disabledColor = ContextCompat.getColor(paramContext, R.color.mtrl_textinput_disabled_color);
    this.hoveredStrokeColor = ContextCompat.getColor(paramContext, R.color.mtrl_textinput_hovered_box_stroke_color);
    if (tintTypedArray.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1)
      setHintTextAppearance(tintTypedArray.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0)); 
    int i = tintTypedArray.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
    boolean bool1 = tintTypedArray.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
    paramInt = tintTypedArray.getResourceId(R.styleable.TextInputLayout_helperTextTextAppearance, 0);
    boolean bool2 = tintTypedArray.getBoolean(R.styleable.TextInputLayout_helperTextEnabled, false);
    CharSequence charSequence = tintTypedArray.getText(R.styleable.TextInputLayout_helperText);
    boolean bool3 = tintTypedArray.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
    setCounterMaxLength(tintTypedArray.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
    this.counterTextAppearance = tintTypedArray.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
    this.counterOverflowTextAppearance = tintTypedArray.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
    this.passwordToggleEnabled = tintTypedArray.getBoolean(R.styleable.TextInputLayout_passwordToggleEnabled, false);
    this.passwordToggleDrawable = tintTypedArray.getDrawable(R.styleable.TextInputLayout_passwordToggleDrawable);
    this.passwordToggleContentDesc = tintTypedArray.getText(R.styleable.TextInputLayout_passwordToggleContentDescription);
    if (tintTypedArray.hasValue(R.styleable.TextInputLayout_passwordToggleTint)) {
      this.hasPasswordToggleTintList = true;
      this.passwordToggleTintList = tintTypedArray.getColorStateList(R.styleable.TextInputLayout_passwordToggleTint);
    } 
    if (tintTypedArray.hasValue(R.styleable.TextInputLayout_passwordToggleTintMode)) {
      this.hasPasswordToggleTintMode = true;
      this.passwordToggleTintMode = ViewUtils.parseTintMode(tintTypedArray.getInt(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
    } 
    tintTypedArray.recycle();
    setHelperTextEnabled(bool2);
    setHelperText(charSequence);
    setHelperTextTextAppearance(paramInt);
    setErrorEnabled(bool1);
    setErrorTextAppearance(i);
    setCounterEnabled(bool3);
    applyPasswordToggleTint();
    ViewCompat.setImportantForAccessibility((View)this, 2);
  }
  
  private void applyBoxAttributes() {
    if (this.boxBackground == null)
      return; 
    setBoxAttributes();
    if (this.editText != null && this.boxBackgroundMode == 2) {
      if (this.editText.getBackground() != null)
        this.editTextOriginalDrawable = this.editText.getBackground(); 
      ViewCompat.setBackground((View)this.editText, null);
    } 
    if (this.editText != null && this.boxBackgroundMode == 1 && this.editTextOriginalDrawable != null)
      ViewCompat.setBackground((View)this.editText, this.editTextOriginalDrawable); 
    if (this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0)
      this.boxBackground.setStroke(this.boxStrokeWidthPx, this.boxStrokeColor); 
    this.boxBackground.setCornerRadii(getCornerRadiiAsArray());
    this.boxBackground.setColor(this.boxBackgroundColor);
    invalidate();
  }
  
  private void applyCutoutPadding(RectF paramRectF) {
    paramRectF.left -= this.boxLabelCutoutPaddingPx;
    paramRectF.top -= this.boxLabelCutoutPaddingPx;
    paramRectF.right += this.boxLabelCutoutPaddingPx;
    paramRectF.bottom += this.boxLabelCutoutPaddingPx;
  }
  
  private void applyPasswordToggleTint() {
    if (this.passwordToggleDrawable != null && (this.hasPasswordToggleTintList || this.hasPasswordToggleTintMode)) {
      this.passwordToggleDrawable = DrawableCompat.wrap(this.passwordToggleDrawable).mutate();
      if (this.hasPasswordToggleTintList)
        DrawableCompat.setTintList(this.passwordToggleDrawable, this.passwordToggleTintList); 
      if (this.hasPasswordToggleTintMode)
        DrawableCompat.setTintMode(this.passwordToggleDrawable, this.passwordToggleTintMode); 
      if (this.passwordToggleView != null && this.passwordToggleView.getDrawable() != this.passwordToggleDrawable)
        this.passwordToggleView.setImageDrawable(this.passwordToggleDrawable); 
    } 
  }
  
  private void assignBoxBackgroundByMode() {
    if (this.boxBackgroundMode == 0) {
      this.boxBackground = null;
    } else if (this.boxBackgroundMode == 2 && this.hintEnabled && !(this.boxBackground instanceof CutoutDrawable)) {
      this.boxBackground = new CutoutDrawable();
    } else if (!(this.boxBackground instanceof GradientDrawable)) {
      this.boxBackground = new GradientDrawable();
    } 
  }
  
  private int calculateBoxBackgroundTop() {
    if (this.editText == null)
      return 0; 
    switch (this.boxBackgroundMode) {
      default:
        return 0;
      case 2:
        return this.editText.getTop() + calculateLabelMarginTop();
      case 1:
        break;
    } 
    return this.editText.getTop();
  }
  
  private int calculateCollapsedTextTopBounds() {
    switch (this.boxBackgroundMode) {
      default:
        return getPaddingTop();
      case 2:
        return (getBoxBackground().getBounds()).top - calculateLabelMarginTop();
      case 1:
        break;
    } 
    return (getBoxBackground().getBounds()).top + this.boxCollapsedPaddingTopPx;
  }
  
  private int calculateLabelMarginTop() {
    if (!this.hintEnabled)
      return 0; 
    switch (this.boxBackgroundMode) {
      default:
        return 0;
      case 2:
        return (int)(this.collapsingTextHelper.getCollapsedTextHeight() / 2.0F);
      case 0:
      case 1:
        break;
    } 
    return (int)this.collapsingTextHelper.getCollapsedTextHeight();
  }
  
  private void closeCutout() {
    if (cutoutEnabled())
      ((CutoutDrawable)this.boxBackground).removeCutout(); 
  }
  
  private void collapseHint(boolean paramBoolean) {
    if (this.animator != null && this.animator.isRunning())
      this.animator.cancel(); 
    if (paramBoolean && this.hintAnimationEnabled) {
      animateToExpansionFraction(1.0F);
    } else {
      this.collapsingTextHelper.setExpansionFraction(1.0F);
    } 
    this.hintExpanded = false;
    if (cutoutEnabled())
      openCutout(); 
  }
  
  private boolean cutoutEnabled() {
    boolean bool;
    if (this.hintEnabled && !TextUtils.isEmpty(this.hint) && this.boxBackground instanceof CutoutDrawable) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void ensureBackgroundDrawableStateWorkaround() {
    int i = Build.VERSION.SDK_INT;
    if (i != 21 && i != 22)
      return; 
    Drawable drawable = this.editText.getBackground();
    if (drawable == null)
      return; 
    if (!this.hasReconstructedEditTextBackground) {
      Drawable drawable1 = drawable.getConstantState().newDrawable();
      if (drawable instanceof DrawableContainer)
        this.hasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)drawable, drawable1.getConstantState()); 
      if (!this.hasReconstructedEditTextBackground) {
        ViewCompat.setBackground((View)this.editText, drawable1);
        this.hasReconstructedEditTextBackground = true;
        onApplyBoxBackgroundMode();
      } 
    } 
  }
  
  private void expandHint(boolean paramBoolean) {
    if (this.animator != null && this.animator.isRunning())
      this.animator.cancel(); 
    if (paramBoolean && this.hintAnimationEnabled) {
      animateToExpansionFraction(0.0F);
    } else {
      this.collapsingTextHelper.setExpansionFraction(0.0F);
    } 
    if (cutoutEnabled() && ((CutoutDrawable)this.boxBackground).hasCutout())
      closeCutout(); 
    this.hintExpanded = true;
  }
  
  @NonNull
  private Drawable getBoxBackground() {
    if (this.boxBackgroundMode == 1 || this.boxBackgroundMode == 2)
      return (Drawable)this.boxBackground; 
    throw new IllegalStateException();
  }
  
  private float[] getCornerRadiiAsArray() {
    return !ViewUtils.isLayoutRtl((View)this) ? new float[] { this.boxCornerRadiusTopStart, this.boxCornerRadiusTopStart, this.boxCornerRadiusTopEnd, this.boxCornerRadiusTopEnd, this.boxCornerRadiusBottomEnd, this.boxCornerRadiusBottomEnd, this.boxCornerRadiusBottomStart, this.boxCornerRadiusBottomStart } : new float[] { this.boxCornerRadiusTopEnd, this.boxCornerRadiusTopEnd, this.boxCornerRadiusTopStart, this.boxCornerRadiusTopStart, this.boxCornerRadiusBottomStart, this.boxCornerRadiusBottomStart, this.boxCornerRadiusBottomEnd, this.boxCornerRadiusBottomEnd };
  }
  
  private boolean hasPasswordTransformation() {
    boolean bool;
    if (this.editText != null && this.editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void onApplyBoxBackgroundMode() {
    assignBoxBackgroundByMode();
    if (this.boxBackgroundMode != 0)
      updateInputLayoutMargins(); 
    updateTextInputBoxBounds();
  }
  
  private void openCutout() {
    if (!cutoutEnabled())
      return; 
    RectF rectF = this.tmpRectF;
    this.collapsingTextHelper.getCollapsedTextActualBounds(rectF);
    applyCutoutPadding(rectF);
    ((CutoutDrawable)this.boxBackground).setCutout(rectF);
  }
  
  private static void recursiveSetEnabled(ViewGroup paramViewGroup, boolean paramBoolean) {
    int i = paramViewGroup.getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = paramViewGroup.getChildAt(b);
      view.setEnabled(paramBoolean);
      if (view instanceof ViewGroup)
        recursiveSetEnabled((ViewGroup)view, paramBoolean); 
    } 
  }
  
  private void setBoxAttributes() {
    switch (this.boxBackgroundMode) {
      default:
        return;
      case 2:
        if (this.focusedStrokeColor == 0)
          this.focusedStrokeColor = this.focusedTextColor.getColorForState(getDrawableState(), this.focusedTextColor.getDefaultColor()); 
      case 1:
        break;
    } 
    this.boxStrokeWidthPx = 0;
  }
  
  private void setEditText(EditText paramEditText) {
    if (this.editText != null)
      throw new IllegalArgumentException("We already have an EditText, can only have one"); 
    if (!(paramEditText instanceof TextInputEditText))
      Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead."); 
    this.editText = paramEditText;
    onApplyBoxBackgroundMode();
    setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
    if (!hasPasswordTransformation())
      this.collapsingTextHelper.setTypefaces(this.editText.getTypeface()); 
    this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
    int i = this.editText.getGravity();
    this.collapsingTextHelper.setCollapsedTextGravity(i & 0xFFFFFF8F | 0x30);
    this.collapsingTextHelper.setExpandedTextGravity(i);
    this.editText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {
            TextInputLayout.this.updateLabelState(TextInputLayout.this.restoringSavedState ^ true);
            if (TextInputLayout.this.counterEnabled)
              TextInputLayout.this.updateCounter(param1Editable.length()); 
          }
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
        });
    if (this.defaultHintTextColor == null)
      this.defaultHintTextColor = this.editText.getHintTextColors(); 
    if (this.hintEnabled) {
      if (TextUtils.isEmpty(this.hint)) {
        this.originalHint = this.editText.getHint();
        setHint(this.originalHint);
        this.editText.setHint(null);
      } 
      this.isProvidingHint = true;
    } 
    if (this.counterView != null)
      updateCounter(this.editText.getText().length()); 
    this.indicatorViewController.adjustIndicatorPadding();
    updatePasswordToggleView();
    updateLabelState(false, true);
  }
  
  private void setHintInternal(CharSequence paramCharSequence) {
    if (!TextUtils.equals(paramCharSequence, this.hint)) {
      this.hint = paramCharSequence;
      this.collapsingTextHelper.setText(paramCharSequence);
      if (!this.hintExpanded)
        openCutout(); 
    } 
  }
  
  private boolean shouldShowPasswordIcon() {
    boolean bool;
    if (this.passwordToggleEnabled && (hasPasswordTransformation() || this.passwordToggledVisible)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void updateEditTextBackgroundBounds() {
    if (this.editText == null)
      return; 
    Drawable drawable1 = this.editText.getBackground();
    if (drawable1 == null)
      return; 
    Drawable drawable2 = drawable1;
    if (DrawableUtils.canSafelyMutateDrawable(drawable1))
      drawable2 = drawable1.mutate(); 
    Rect rect = new Rect();
    DescendantOffsetUtils.getDescendantRect((ViewGroup)this, (View)this.editText, rect);
    rect = drawable2.getBounds();
    if (rect.left != rect.right) {
      Rect rect1 = new Rect();
      drawable2.getPadding(rect1);
      int i = rect.left;
      int j = rect1.left;
      int k = rect.right;
      int m = rect1.right;
      drawable2.setBounds(i - j, rect.top, k + m * 2, this.editText.getBottom());
    } 
  }
  
  private void updateInputLayoutMargins() {
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.inputFrame.getLayoutParams();
    int i = calculateLabelMarginTop();
    if (i != layoutParams.topMargin) {
      layoutParams.topMargin = i;
      this.inputFrame.requestLayout();
    } 
  }
  
  private void updateLabelState(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool3;
    boolean bool1 = isEnabled();
    EditText editText = this.editText;
    boolean bool2 = false;
    if (editText != null && !TextUtils.isEmpty((CharSequence)this.editText.getText())) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    boolean bool4 = bool2;
    if (this.editText != null) {
      bool4 = bool2;
      if (this.editText.hasFocus())
        bool4 = true; 
    } 
    boolean bool5 = this.indicatorViewController.errorShouldBeShown();
    if (this.defaultHintTextColor != null) {
      this.collapsingTextHelper.setCollapsedTextColor(this.defaultHintTextColor);
      this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
    } 
    if (!bool1) {
      this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(this.disabledColor));
      this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(this.disabledColor));
    } else if (bool5) {
      this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
    } else if (this.counterOverflowed && this.counterView != null) {
      this.collapsingTextHelper.setCollapsedTextColor(this.counterView.getTextColors());
    } else if (bool4 && this.focusedTextColor != null) {
      this.collapsingTextHelper.setCollapsedTextColor(this.focusedTextColor);
    } 
    if (bool3 || (isEnabled() && (bool4 || bool5))) {
      if (paramBoolean2 || this.hintExpanded)
        collapseHint(paramBoolean1); 
      return;
    } 
    if (paramBoolean2 || !this.hintExpanded)
      expandHint(paramBoolean1); 
  }
  
  private void updatePasswordToggleView() {
    if (this.editText == null)
      return; 
    if (shouldShowPasswordIcon()) {
      if (this.passwordToggleView == null) {
        this.passwordToggleView = (CheckableImageButton)LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_password_icon, (ViewGroup)this.inputFrame, false);
        this.passwordToggleView.setImageDrawable(this.passwordToggleDrawable);
        this.passwordToggleView.setContentDescription(this.passwordToggleContentDesc);
        this.inputFrame.addView((View)this.passwordToggleView);
        this.passwordToggleView.setOnClickListener(new View.OnClickListener() {
              public void onClick(View param1View) {
                TextInputLayout.this.passwordVisibilityToggleRequested(false);
              }
            });
      } 
      if (this.editText != null && ViewCompat.getMinimumHeight((View)this.editText) <= 0)
        this.editText.setMinimumHeight(ViewCompat.getMinimumHeight((View)this.passwordToggleView)); 
      this.passwordToggleView.setVisibility(0);
      this.passwordToggleView.setChecked(this.passwordToggledVisible);
      if (this.passwordToggleDummyDrawable == null)
        this.passwordToggleDummyDrawable = (Drawable)new ColorDrawable(); 
      this.passwordToggleDummyDrawable.setBounds(0, 0, this.passwordToggleView.getMeasuredWidth(), 1);
      Drawable[] arrayOfDrawable = TextViewCompat.getCompoundDrawablesRelative((TextView)this.editText);
      if (arrayOfDrawable[2] != this.passwordToggleDummyDrawable)
        this.originalEditTextEndDrawable = arrayOfDrawable[2]; 
      TextViewCompat.setCompoundDrawablesRelative((TextView)this.editText, arrayOfDrawable[0], arrayOfDrawable[1], this.passwordToggleDummyDrawable, arrayOfDrawable[3]);
      this.passwordToggleView.setPadding(this.editText.getPaddingLeft(), this.editText.getPaddingTop(), this.editText.getPaddingRight(), this.editText.getPaddingBottom());
    } else {
      if (this.passwordToggleView != null && this.passwordToggleView.getVisibility() == 0)
        this.passwordToggleView.setVisibility(8); 
      if (this.passwordToggleDummyDrawable != null) {
        Drawable[] arrayOfDrawable = TextViewCompat.getCompoundDrawablesRelative((TextView)this.editText);
        if (arrayOfDrawable[2] == this.passwordToggleDummyDrawable) {
          TextViewCompat.setCompoundDrawablesRelative((TextView)this.editText, arrayOfDrawable[0], arrayOfDrawable[1], this.originalEditTextEndDrawable, arrayOfDrawable[3]);
          this.passwordToggleDummyDrawable = null;
        } 
      } 
    } 
  }
  
  private void updateTextInputBoxBounds() {
    if (this.boxBackgroundMode == 0 || this.boxBackground == null || this.editText == null || getRight() == 0)
      return; 
    int i = this.editText.getLeft();
    int j = calculateBoxBackgroundTop();
    int k = this.editText.getRight();
    int m = this.editText.getBottom() + this.boxBottomOffsetPx;
    int n = i;
    int i1 = j;
    int i2 = k;
    int i3 = m;
    if (this.boxBackgroundMode == 2) {
      n = i + this.boxStrokeWidthFocusedPx / 2;
      i1 = j - this.boxStrokeWidthFocusedPx / 2;
      i2 = k - this.boxStrokeWidthFocusedPx / 2;
      i3 = m + this.boxStrokeWidthFocusedPx / 2;
    } 
    this.boxBackground.setBounds(n, i1, i2, i3);
    applyBoxAttributes();
    updateEditTextBackgroundBounds();
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    if (paramView instanceof EditText) {
      FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(paramLayoutParams);
      layoutParams.gravity = layoutParams.gravity & 0xFFFFFF8F | 0x10;
      this.inputFrame.addView(paramView, (ViewGroup.LayoutParams)layoutParams);
      this.inputFrame.setLayoutParams(paramLayoutParams);
      updateInputLayoutMargins();
      setEditText((EditText)paramView);
    } else {
      super.addView(paramView, paramInt, paramLayoutParams);
    } 
  }
  
  @VisibleForTesting
  void animateToExpansionFraction(float paramFloat) {
    if (this.collapsingTextHelper.getExpansionFraction() == paramFloat)
      return; 
    if (this.animator == null) {
      this.animator = new ValueAnimator();
      this.animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
      this.animator.setDuration(167L);
      this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
              TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float)param1ValueAnimator.getAnimatedValue()).floatValue());
            }
          });
    } 
    this.animator.setFloatValues(new float[] { this.collapsingTextHelper.getExpansionFraction(), paramFloat });
    this.animator.start();
  }
  
  @VisibleForTesting
  boolean cutoutIsOpen() {
    boolean bool;
    if (cutoutEnabled() && ((CutoutDrawable)this.boxBackground).hasCutout()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void dispatchProvideAutofillStructure(ViewStructure paramViewStructure, int paramInt) {
    if (this.originalHint == null || this.editText == null) {
      super.dispatchProvideAutofillStructure(paramViewStructure, paramInt);
      return;
    } 
    boolean bool = this.isProvidingHint;
    this.isProvidingHint = false;
    CharSequence charSequence = this.editText.getHint();
    this.editText.setHint(this.originalHint);
    try {
      super.dispatchProvideAutofillStructure(paramViewStructure, paramInt);
      return;
    } finally {
      this.editText.setHint(charSequence);
      this.isProvidingHint = bool;
    } 
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray) {
    this.restoringSavedState = true;
    super.dispatchRestoreInstanceState(paramSparseArray);
    this.restoringSavedState = false;
  }
  
  public void draw(Canvas paramCanvas) {
    if (this.boxBackground != null)
      this.boxBackground.draw(paramCanvas); 
    super.draw(paramCanvas);
    if (this.hintEnabled)
      this.collapsingTextHelper.draw(paramCanvas); 
  }
  
  protected void drawableStateChanged() {
    boolean bool2;
    if (this.inDrawableStateChanged)
      return; 
    boolean bool1 = true;
    this.inDrawableStateChanged = true;
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    if (!ViewCompat.isLaidOut((View)this) || !isEnabled())
      bool1 = false; 
    updateLabelState(bool1);
    updateEditTextBackground();
    updateTextInputBoxBounds();
    updateTextInputBoxState();
    if (this.collapsingTextHelper != null) {
      bool2 = this.collapsingTextHelper.setState(arrayOfInt) | false;
    } else {
      bool2 = false;
    } 
    if (bool2)
      invalidate(); 
    this.inDrawableStateChanged = false;
  }
  
  public int getBoxBackgroundColor() {
    return this.boxBackgroundColor;
  }
  
  public float getBoxCornerRadiusBottomEnd() {
    return this.boxCornerRadiusBottomEnd;
  }
  
  public float getBoxCornerRadiusBottomStart() {
    return this.boxCornerRadiusBottomStart;
  }
  
  public float getBoxCornerRadiusTopEnd() {
    return this.boxCornerRadiusTopEnd;
  }
  
  public float getBoxCornerRadiusTopStart() {
    return this.boxCornerRadiusTopStart;
  }
  
  public int getBoxStrokeColor() {
    return this.focusedStrokeColor;
  }
  
  public int getCounterMaxLength() {
    return this.counterMaxLength;
  }
  
  @Nullable
  CharSequence getCounterOverflowDescription() {
    return (this.counterEnabled && this.counterOverflowed && this.counterView != null) ? this.counterView.getContentDescription() : null;
  }
  
  @Nullable
  public ColorStateList getDefaultHintTextColor() {
    return this.defaultHintTextColor;
  }
  
  @Nullable
  public EditText getEditText() {
    return this.editText;
  }
  
  @Nullable
  public CharSequence getError() {
    CharSequence charSequence;
    if (this.indicatorViewController.isErrorEnabled()) {
      charSequence = this.indicatorViewController.getErrorText();
    } else {
      charSequence = null;
    } 
    return charSequence;
  }
  
  @ColorInt
  public int getErrorCurrentTextColors() {
    return this.indicatorViewController.getErrorViewCurrentTextColor();
  }
  
  @VisibleForTesting
  final int getErrorTextCurrentColor() {
    return this.indicatorViewController.getErrorViewCurrentTextColor();
  }
  
  @Nullable
  public CharSequence getHelperText() {
    CharSequence charSequence;
    if (this.indicatorViewController.isHelperTextEnabled()) {
      charSequence = this.indicatorViewController.getHelperText();
    } else {
      charSequence = null;
    } 
    return charSequence;
  }
  
  @ColorInt
  public int getHelperTextCurrentTextColor() {
    return this.indicatorViewController.getHelperTextViewCurrentTextColor();
  }
  
  @Nullable
  public CharSequence getHint() {
    CharSequence charSequence;
    if (this.hintEnabled) {
      charSequence = this.hint;
    } else {
      charSequence = null;
    } 
    return charSequence;
  }
  
  @VisibleForTesting
  final float getHintCollapsedTextHeight() {
    return this.collapsingTextHelper.getCollapsedTextHeight();
  }
  
  @VisibleForTesting
  final int getHintCurrentCollapsedTextColor() {
    return this.collapsingTextHelper.getCurrentCollapsedTextColor();
  }
  
  @Nullable
  public CharSequence getPasswordVisibilityToggleContentDescription() {
    return this.passwordToggleContentDesc;
  }
  
  @Nullable
  public Drawable getPasswordVisibilityToggleDrawable() {
    return this.passwordToggleDrawable;
  }
  
  @Nullable
  public Typeface getTypeface() {
    return this.typeface;
  }
  
  public boolean isCounterEnabled() {
    return this.counterEnabled;
  }
  
  public boolean isErrorEnabled() {
    return this.indicatorViewController.isErrorEnabled();
  }
  
  @VisibleForTesting
  final boolean isHelperTextDisplayed() {
    return this.indicatorViewController.helperTextIsDisplayed();
  }
  
  public boolean isHelperTextEnabled() {
    return this.indicatorViewController.isHelperTextEnabled();
  }
  
  public boolean isHintAnimationEnabled() {
    return this.hintAnimationEnabled;
  }
  
  public boolean isHintEnabled() {
    return this.hintEnabled;
  }
  
  @VisibleForTesting
  final boolean isHintExpanded() {
    return this.hintExpanded;
  }
  
  public boolean isPasswordVisibilityToggleEnabled() {
    return this.passwordToggleEnabled;
  }
  
  boolean isProvidingHint() {
    return this.isProvidingHint;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.boxBackground != null)
      updateTextInputBoxBounds(); 
    if (this.hintEnabled && this.editText != null) {
      Rect rect = this.tmpRect;
      DescendantOffsetUtils.getDescendantRect((ViewGroup)this, (View)this.editText, rect);
      paramInt3 = rect.left + this.editText.getCompoundPaddingLeft();
      paramInt1 = rect.right - this.editText.getCompoundPaddingRight();
      int i = calculateCollapsedTextTopBounds();
      this.collapsingTextHelper.setExpandedBounds(paramInt3, rect.top + this.editText.getCompoundPaddingTop(), paramInt1, rect.bottom - this.editText.getCompoundPaddingBottom());
      this.collapsingTextHelper.setCollapsedBounds(paramInt3, i, paramInt1, paramInt4 - paramInt2 - getPaddingBottom());
      this.collapsingTextHelper.recalculate();
      if (cutoutEnabled() && !this.hintExpanded)
        openCutout(); 
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    updatePasswordToggleView();
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    setError(savedState.error);
    if (savedState.isPasswordToggledVisible)
      passwordVisibilityToggleRequested(true); 
    requestLayout();
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    if (this.indicatorViewController.errorShouldBeShown())
      savedState.error = getError(); 
    savedState.isPasswordToggledVisible = this.passwordToggledVisible;
    return (Parcelable)savedState;
  }
  
  public void passwordVisibilityToggleRequested(boolean paramBoolean) {
    if (this.passwordToggleEnabled) {
      int i = this.editText.getSelectionEnd();
      if (hasPasswordTransformation()) {
        this.editText.setTransformationMethod(null);
        this.passwordToggledVisible = true;
      } else {
        this.editText.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
        this.passwordToggledVisible = false;
      } 
      this.passwordToggleView.setChecked(this.passwordToggledVisible);
      if (paramBoolean)
        this.passwordToggleView.jumpDrawablesToCurrentState(); 
      this.editText.setSelection(i);
    } 
  }
  
  public void setBoxBackgroundColor(@ColorInt int paramInt) {
    if (this.boxBackgroundColor != paramInt) {
      this.boxBackgroundColor = paramInt;
      applyBoxAttributes();
    } 
  }
  
  public void setBoxBackgroundColorResource(@ColorRes int paramInt) {
    setBoxBackgroundColor(ContextCompat.getColor(getContext(), paramInt));
  }
  
  public void setBoxBackgroundMode(int paramInt) {
    if (paramInt == this.boxBackgroundMode)
      return; 
    this.boxBackgroundMode = paramInt;
    onApplyBoxBackgroundMode();
  }
  
  public void setBoxCornerRadii(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    if (this.boxCornerRadiusTopStart != paramFloat1 || this.boxCornerRadiusTopEnd != paramFloat2 || this.boxCornerRadiusBottomEnd != paramFloat4 || this.boxCornerRadiusBottomStart != paramFloat3) {
      this.boxCornerRadiusTopStart = paramFloat1;
      this.boxCornerRadiusTopEnd = paramFloat2;
      this.boxCornerRadiusBottomEnd = paramFloat4;
      this.boxCornerRadiusBottomStart = paramFloat3;
      applyBoxAttributes();
    } 
  }
  
  public void setBoxCornerRadiiResources(@DimenRes int paramInt1, @DimenRes int paramInt2, @DimenRes int paramInt3, @DimenRes int paramInt4) {
    setBoxCornerRadii(getContext().getResources().getDimension(paramInt1), getContext().getResources().getDimension(paramInt2), getContext().getResources().getDimension(paramInt3), getContext().getResources().getDimension(paramInt4));
  }
  
  public void setBoxStrokeColor(@ColorInt int paramInt) {
    if (this.focusedStrokeColor != paramInt) {
      this.focusedStrokeColor = paramInt;
      updateTextInputBoxState();
    } 
  }
  
  public void setCounterEnabled(boolean paramBoolean) {
    if (this.counterEnabled != paramBoolean) {
      if (paramBoolean) {
        this.counterView = (TextView)new AppCompatTextView(getContext());
        this.counterView.setId(R.id.textinput_counter);
        if (this.typeface != null)
          this.counterView.setTypeface(this.typeface); 
        this.counterView.setMaxLines(1);
        setTextAppearanceCompatWithErrorFallback(this.counterView, this.counterTextAppearance);
        this.indicatorViewController.addIndicator(this.counterView, 2);
        if (this.editText == null) {
          updateCounter(0);
        } else {
          updateCounter(this.editText.getText().length());
        } 
      } else {
        this.indicatorViewController.removeIndicator(this.counterView, 2);
        this.counterView = null;
      } 
      this.counterEnabled = paramBoolean;
    } 
  }
  
  public void setCounterMaxLength(int paramInt) {
    if (this.counterMaxLength != paramInt) {
      if (paramInt > 0) {
        this.counterMaxLength = paramInt;
      } else {
        this.counterMaxLength = -1;
      } 
      if (this.counterEnabled) {
        if (this.editText == null) {
          paramInt = 0;
        } else {
          paramInt = this.editText.getText().length();
        } 
        updateCounter(paramInt);
      } 
    } 
  }
  
  public void setDefaultHintTextColor(@Nullable ColorStateList paramColorStateList) {
    this.defaultHintTextColor = paramColorStateList;
    this.focusedTextColor = paramColorStateList;
    if (this.editText != null)
      updateLabelState(false); 
  }
  
  public void setEnabled(boolean paramBoolean) {
    recursiveSetEnabled((ViewGroup)this, paramBoolean);
    super.setEnabled(paramBoolean);
  }
  
  public void setError(@Nullable CharSequence paramCharSequence) {
    if (!this.indicatorViewController.isErrorEnabled()) {
      if (TextUtils.isEmpty(paramCharSequence))
        return; 
      setErrorEnabled(true);
    } 
    if (!TextUtils.isEmpty(paramCharSequence)) {
      this.indicatorViewController.showError(paramCharSequence);
    } else {
      this.indicatorViewController.hideError();
    } 
  }
  
  public void setErrorEnabled(boolean paramBoolean) {
    this.indicatorViewController.setErrorEnabled(paramBoolean);
  }
  
  public void setErrorTextAppearance(@StyleRes int paramInt) {
    this.indicatorViewController.setErrorTextAppearance(paramInt);
  }
  
  public void setErrorTextColor(@Nullable ColorStateList paramColorStateList) {
    this.indicatorViewController.setErrorViewTextColor(paramColorStateList);
  }
  
  public void setHelperText(@Nullable CharSequence paramCharSequence) {
    if (TextUtils.isEmpty(paramCharSequence)) {
      if (isHelperTextEnabled())
        setHelperTextEnabled(false); 
    } else {
      if (!isHelperTextEnabled())
        setHelperTextEnabled(true); 
      this.indicatorViewController.showHelper(paramCharSequence);
    } 
  }
  
  public void setHelperTextColor(@Nullable ColorStateList paramColorStateList) {
    this.indicatorViewController.setHelperTextViewTextColor(paramColorStateList);
  }
  
  public void setHelperTextEnabled(boolean paramBoolean) {
    this.indicatorViewController.setHelperTextEnabled(paramBoolean);
  }
  
  public void setHelperTextTextAppearance(@StyleRes int paramInt) {
    this.indicatorViewController.setHelperTextAppearance(paramInt);
  }
  
  public void setHint(@Nullable CharSequence paramCharSequence) {
    if (this.hintEnabled) {
      setHintInternal(paramCharSequence);
      sendAccessibilityEvent(2048);
    } 
  }
  
  public void setHintAnimationEnabled(boolean paramBoolean) {
    this.hintAnimationEnabled = paramBoolean;
  }
  
  public void setHintEnabled(boolean paramBoolean) {
    if (paramBoolean != this.hintEnabled) {
      this.hintEnabled = paramBoolean;
      if (!this.hintEnabled) {
        this.isProvidingHint = false;
        if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint()))
          this.editText.setHint(this.hint); 
        setHintInternal((CharSequence)null);
      } else {
        CharSequence charSequence = this.editText.getHint();
        if (!TextUtils.isEmpty(charSequence)) {
          if (TextUtils.isEmpty(this.hint))
            setHint(charSequence); 
          this.editText.setHint(null);
        } 
        this.isProvidingHint = true;
      } 
      if (this.editText != null)
        updateInputLayoutMargins(); 
    } 
  }
  
  public void setHintTextAppearance(@StyleRes int paramInt) {
    this.collapsingTextHelper.setCollapsedTextAppearance(paramInt);
    this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
    if (this.editText != null) {
      updateLabelState(false);
      updateInputLayoutMargins();
    } 
  }
  
  public void setPasswordVisibilityToggleContentDescription(@StringRes int paramInt) {
    CharSequence charSequence;
    if (paramInt != 0) {
      charSequence = getResources().getText(paramInt);
    } else {
      charSequence = null;
    } 
    setPasswordVisibilityToggleContentDescription(charSequence);
  }
  
  public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence paramCharSequence) {
    this.passwordToggleContentDesc = paramCharSequence;
    if (this.passwordToggleView != null)
      this.passwordToggleView.setContentDescription(paramCharSequence); 
  }
  
  public void setPasswordVisibilityToggleDrawable(@DrawableRes int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = AppCompatResources.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    setPasswordVisibilityToggleDrawable(drawable);
  }
  
  public void setPasswordVisibilityToggleDrawable(@Nullable Drawable paramDrawable) {
    this.passwordToggleDrawable = paramDrawable;
    if (this.passwordToggleView != null)
      this.passwordToggleView.setImageDrawable(paramDrawable); 
  }
  
  public void setPasswordVisibilityToggleEnabled(boolean paramBoolean) {
    if (this.passwordToggleEnabled != paramBoolean) {
      this.passwordToggleEnabled = paramBoolean;
      if (!paramBoolean && this.passwordToggledVisible && this.editText != null)
        this.editText.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance()); 
      this.passwordToggledVisible = false;
      updatePasswordToggleView();
    } 
  }
  
  public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList paramColorStateList) {
    this.passwordToggleTintList = paramColorStateList;
    this.hasPasswordToggleTintList = true;
    applyPasswordToggleTint();
  }
  
  public void setPasswordVisibilityToggleTintMode(@Nullable PorterDuff.Mode paramMode) {
    this.passwordToggleTintMode = paramMode;
    this.hasPasswordToggleTintMode = true;
    applyPasswordToggleTint();
  }
  
  void setTextAppearanceCompatWithErrorFallback(TextView paramTextView, @StyleRes int paramInt) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: iload_2
    //   4: invokestatic setTextAppearance : (Landroid/widget/TextView;I)V
    //   7: getstatic android/os/Build$VERSION.SDK_INT : I
    //   10: bipush #23
    //   12: if_icmplt -> 35
    //   15: aload_1
    //   16: invokevirtual getTextColors : ()Landroid/content/res/ColorStateList;
    //   19: invokevirtual getDefaultColor : ()I
    //   22: istore_2
    //   23: iload_2
    //   24: ldc_w -65281
    //   27: if_icmpne -> 35
    //   30: iload_3
    //   31: istore_2
    //   32: goto -> 37
    //   35: iconst_0
    //   36: istore_2
    //   37: iload_2
    //   38: ifeq -> 62
    //   41: aload_1
    //   42: getstatic android/support/design/R$style.TextAppearance_AppCompat_Caption : I
    //   45: invokestatic setTextAppearance : (Landroid/widget/TextView;I)V
    //   48: aload_1
    //   49: aload_0
    //   50: invokevirtual getContext : ()Landroid/content/Context;
    //   53: getstatic android/support/design/R$color.design_error : I
    //   56: invokestatic getColor : (Landroid/content/Context;I)I
    //   59: invokevirtual setTextColor : (I)V
    //   62: return
    //   63: astore #4
    //   65: iload_3
    //   66: istore_2
    //   67: goto -> 37
    // Exception table:
    //   from	to	target	type
    //   2	23	63	java/lang/Exception
  }
  
  public void setTextInputAccessibilityDelegate(AccessibilityDelegate paramAccessibilityDelegate) {
    if (this.editText != null)
      ViewCompat.setAccessibilityDelegate((View)this.editText, paramAccessibilityDelegate); 
  }
  
  public void setTypeface(@Nullable Typeface paramTypeface) {
    if (paramTypeface != this.typeface) {
      this.typeface = paramTypeface;
      this.collapsingTextHelper.setTypefaces(paramTypeface);
      this.indicatorViewController.setTypefaces(paramTypeface);
      if (this.counterView != null)
        this.counterView.setTypeface(paramTypeface); 
    } 
  }
  
  void updateCounter(int paramInt) {
    boolean bool = this.counterOverflowed;
    if (this.counterMaxLength == -1) {
      this.counterView.setText(String.valueOf(paramInt));
      this.counterView.setContentDescription(null);
      this.counterOverflowed = false;
    } else {
      boolean bool1;
      if (ViewCompat.getAccessibilityLiveRegion((View)this.counterView) == 1)
        ViewCompat.setAccessibilityLiveRegion((View)this.counterView, 0); 
      if (paramInt > this.counterMaxLength) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      this.counterOverflowed = bool1;
      if (bool != this.counterOverflowed) {
        int i;
        TextView textView = this.counterView;
        if (this.counterOverflowed) {
          i = this.counterOverflowTextAppearance;
        } else {
          i = this.counterTextAppearance;
        } 
        setTextAppearanceCompatWithErrorFallback(textView, i);
        if (this.counterOverflowed)
          ViewCompat.setAccessibilityLiveRegion((View)this.counterView, 1); 
      } 
      this.counterView.setText(getContext().getString(R.string.character_counter_pattern, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(this.counterMaxLength) }));
      this.counterView.setContentDescription(getContext().getString(R.string.character_counter_content_description, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(this.counterMaxLength) }));
    } 
    if (this.editText != null && bool != this.counterOverflowed) {
      updateLabelState(false);
      updateTextInputBoxState();
      updateEditTextBackground();
    } 
  }
  
  void updateEditTextBackground() {
    if (this.editText == null)
      return; 
    Drawable drawable1 = this.editText.getBackground();
    if (drawable1 == null)
      return; 
    ensureBackgroundDrawableStateWorkaround();
    Drawable drawable2 = drawable1;
    if (DrawableUtils.canSafelyMutateDrawable(drawable1))
      drawable2 = drawable1.mutate(); 
    if (this.indicatorViewController.errorShouldBeShown()) {
      drawable2.setColorFilter((ColorFilter)AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), PorterDuff.Mode.SRC_IN));
    } else if (this.counterOverflowed && this.counterView != null) {
      drawable2.setColorFilter((ColorFilter)AppCompatDrawableManager.getPorterDuffColorFilter(this.counterView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
    } else {
      DrawableCompat.clearColorFilter(drawable2);
      this.editText.refreshDrawableState();
    } 
  }
  
  void updateLabelState(boolean paramBoolean) {
    updateLabelState(paramBoolean, false);
  }
  
  void updateTextInputBoxState() {
    boolean bool2;
    if (this.boxBackground == null || this.boxBackgroundMode == 0)
      return; 
    EditText editText = this.editText;
    boolean bool1 = false;
    if (editText != null && this.editText.hasFocus()) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool3 = bool1;
    if (this.editText != null) {
      bool3 = bool1;
      if (this.editText.isHovered())
        bool3 = true; 
    } 
    if (this.boxBackgroundMode == 2) {
      if (!isEnabled()) {
        this.boxStrokeColor = this.disabledColor;
      } else if (this.indicatorViewController.errorShouldBeShown()) {
        this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
      } else if (this.counterOverflowed && this.counterView != null) {
        this.boxStrokeColor = this.counterView.getCurrentTextColor();
      } else if (bool2) {
        this.boxStrokeColor = this.focusedStrokeColor;
      } else if (bool3) {
        this.boxStrokeColor = this.hoveredStrokeColor;
      } else {
        this.boxStrokeColor = this.defaultStrokeColor;
      } 
      if ((bool3 || bool2) && isEnabled()) {
        this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
      } else {
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
      } 
      applyBoxAttributes();
    } 
  }
  
  public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
    private final TextInputLayout layout;
    
    public AccessibilityDelegate(TextInputLayout param1TextInputLayout) {
      this.layout = param1TextInputLayout;
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      boolean bool2;
      super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      EditText editText = this.layout.getEditText();
      if (editText != null) {
        Editable editable = editText.getText();
      } else {
        editText = null;
      } 
      CharSequence charSequence1 = this.layout.getHint();
      CharSequence charSequence2 = this.layout.getError();
      CharSequence charSequence3 = this.layout.getCounterOverflowDescription();
      int i = TextUtils.isEmpty((CharSequence)editText) ^ true;
      int j = TextUtils.isEmpty(charSequence1) ^ true;
      int k = TextUtils.isEmpty(charSequence2) ^ true;
      boolean bool1 = false;
      if (k != 0 || !TextUtils.isEmpty(charSequence3)) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (i != 0) {
        param1AccessibilityNodeInfoCompat.setText((CharSequence)editText);
      } else if (j != 0) {
        param1AccessibilityNodeInfoCompat.setText(charSequence1);
      } 
      if (j != 0) {
        param1AccessibilityNodeInfoCompat.setHintText(charSequence1);
        boolean bool = bool1;
        if (i == 0) {
          bool = bool1;
          if (j != 0)
            bool = true; 
        } 
        param1AccessibilityNodeInfoCompat.setShowingHintText(bool);
      } 
      if (bool2) {
        CharSequence charSequence = charSequence3;
        if (k != 0)
          charSequence = charSequence2; 
        param1AccessibilityNodeInfoCompat.setError(charSequence);
        param1AccessibilityNodeInfoCompat.setContentInvalid(true);
      } 
    }
    
    public void onPopulateAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      CharSequence charSequence;
      super.onPopulateAccessibilityEvent(param1View, param1AccessibilityEvent);
      EditText editText1 = this.layout.getEditText();
      if (editText1 != null) {
        Editable editable = editText1.getText();
      } else {
        editText1 = null;
      } 
      EditText editText2 = editText1;
      if (TextUtils.isEmpty((CharSequence)editText1))
        charSequence = this.layout.getHint(); 
      if (!TextUtils.isEmpty(charSequence))
        param1AccessibilityEvent.getText().add(charSequence); 
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BoxBackgroundMode {}
  
  static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public TextInputLayout.SavedState createFromParcel(Parcel param2Parcel) {
          return new TextInputLayout.SavedState(param2Parcel, null);
        }
        
        public TextInputLayout.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new TextInputLayout.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public TextInputLayout.SavedState[] newArray(int param2Int) {
          return new TextInputLayout.SavedState[param2Int];
        }
      };
    
    CharSequence error;
    
    boolean isPasswordToggledVisible;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      this.error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel);
      int i = param1Parcel.readInt();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      this.isPasswordToggledVisible = bool;
    }
    
    SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("TextInputLayout.SavedState{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" error=");
      stringBuilder.append(this.error);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      TextUtils.writeToParcel(this.error, param1Parcel, param1Int);
      param1Parcel.writeInt(this.isPasswordToggledVisible);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public TextInputLayout.SavedState createFromParcel(Parcel param1Parcel) {
      return new TextInputLayout.SavedState(param1Parcel, null);
    }
    
    public TextInputLayout.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new TextInputLayout.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public TextInputLayout.SavedState[] newArray(int param1Int) {
      return new TextInputLayout.SavedState[param1Int];
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\TextInputLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */