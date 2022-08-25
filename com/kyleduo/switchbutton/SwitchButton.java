package com.kyleduo.switchbutton;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;

public class SwitchButton extends CompoundButton {
  private static int[] CHECKED_PRESSED_STATE = new int[] { 16842912, 16842910, 16842919 };
  
  public static final int DEFAULT_ANIMATION_DURATION = 250;
  
  public static final float DEFAULT_BACK_MEASURE_RATIO = 1.8F;
  
  public static final int DEFAULT_TEXT_MARGIN_DP = 2;
  
  public static final int DEFAULT_THUMB_MARGIN_DP = 2;
  
  public static final int DEFAULT_THUMB_SIZE_DP = 20;
  
  public static final int DEFAULT_TINT_COLOR = 3309506;
  
  private static int[] UNCHECKED_PRESSED_STATE = new int[] { -16842912, 16842910, 16842919 };
  
  private long mAnimationDuration;
  
  private boolean mAutoAdjustTextPosition = true;
  
  private ColorStateList mBackColor;
  
  private Drawable mBackDrawable;
  
  private float mBackMeasureRatio;
  
  private float mBackRadius;
  
  private RectF mBackRectF;
  
  private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
  
  private int mClickTimeout;
  
  private int mCurrBackColor;
  
  private int mCurrThumbColor;
  
  private Drawable mCurrentBackDrawable;
  
  private boolean mDrawDebugRect = false;
  
  private boolean mFadeBack;
  
  private boolean mIsBackUseDrawable;
  
  private boolean mIsThumbUseDrawable;
  
  private float mLastX;
  
  private int mNextBackColor;
  
  private Drawable mNextBackDrawable;
  
  private Layout mOffLayout;
  
  private int mOffTextColor;
  
  private Layout mOnLayout;
  
  private int mOnTextColor;
  
  private Paint mPaint;
  
  private RectF mPresentThumbRectF;
  
  private float mProcess;
  
  private ObjectAnimator mProcessAnimator;
  
  private Paint mRectPaint;
  
  private boolean mRestoring = false;
  
  private RectF mSafeRectF;
  
  private float mStartX;
  
  private float mStartY;
  
  private float mTextHeight;
  
  private float mTextMarginH;
  
  private CharSequence mTextOff;
  
  private RectF mTextOffRectF;
  
  private CharSequence mTextOn;
  
  private RectF mTextOnRectF;
  
  private TextPaint mTextPaint;
  
  private float mTextWidth;
  
  private ColorStateList mThumbColor;
  
  private Drawable mThumbDrawable;
  
  private RectF mThumbMargin;
  
  private float mThumbRadius;
  
  private RectF mThumbRectF;
  
  private PointF mThumbSizeF;
  
  private int mTintColor;
  
  private int mTouchSlop;
  
  public SwitchButton(Context paramContext) {
    super(paramContext);
    init((AttributeSet)null);
  }
  
  public SwitchButton(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  public SwitchButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }
  
  private void catchView() {
    ViewParent viewParent = getParent();
    if (viewParent != null)
      viewParent.requestDisallowInterceptTouchEvent(true); 
  }
  
  private int ceil(double paramDouble) {
    return (int)Math.ceil(paramDouble);
  }
  
  private boolean getStatusBasedOnPos() {
    boolean bool;
    if (getProcess() > 0.5F) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void init(AttributeSet paramAttributeSet) {
    TypedArray typedArray1;
    TypedArray typedArray2;
    Drawable drawable1;
    ColorStateList colorStateList;
    float f4;
    float f5;
    float f6;
    float f7;
    float f8;
    float f9;
    float f10;
    Drawable drawable2;
    char c;
    boolean bool1;
    boolean bool2;
    CharSequence charSequence1;
    CharSequence charSequence2;
    this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    this.mClickTimeout = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
    this.mPaint = new Paint(1);
    this.mRectPaint = new Paint(1);
    this.mRectPaint.setStyle(Paint.Style.STROKE);
    this.mRectPaint.setStrokeWidth((getResources().getDisplayMetrics()).density);
    this.mTextPaint = getPaint();
    this.mThumbRectF = new RectF();
    this.mBackRectF = new RectF();
    this.mSafeRectF = new RectF();
    this.mThumbSizeF = new PointF();
    this.mThumbMargin = new RectF();
    this.mTextOnRectF = new RectF();
    this.mTextOffRectF = new RectF();
    this.mProcessAnimator = ObjectAnimator.ofFloat(this, "process", new float[] { 0.0F, 0.0F }).setDuration(250L);
    this.mProcessAnimator.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
    this.mPresentThumbRectF = new RectF();
    float f1 = (getResources().getDisplayMetrics()).density;
    float f2 = f1 * 2.0F;
    float f3 = f1 * 20.0F;
    f1 = f3 / 2.0F;
    if (paramAttributeSet == null) {
      typedArray2 = null;
    } else {
      typedArray2 = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.SwitchButton);
    } 
    if (typedArray2 != null) {
      drawable1 = typedArray2.getDrawable(R.styleable.SwitchButton_kswThumbDrawable);
      colorStateList = typedArray2.getColorStateList(R.styleable.SwitchButton_kswThumbColor);
      f1 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbMargin, f2);
      f4 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbMarginLeft, f1);
      f5 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbMarginRight, f1);
      f6 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbMarginTop, f1);
      f7 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbMarginBottom, f1);
      f8 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbWidth, f3);
      f3 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbHeight, f3);
      f9 = typedArray2.getDimension(R.styleable.SwitchButton_kswThumbRadius, Math.min(f8, f3) / 2.0F);
      f10 = typedArray2.getDimension(R.styleable.SwitchButton_kswBackRadius, f9 + f2);
      drawable2 = typedArray2.getDrawable(R.styleable.SwitchButton_kswBackDrawable);
      ColorStateList colorStateList2 = typedArray2.getColorStateList(R.styleable.SwitchButton_kswBackColor);
      f1 = typedArray2.getFloat(R.styleable.SwitchButton_kswBackMeasureRatio, 1.8F);
      c = typedArray2.getInteger(R.styleable.SwitchButton_kswAnimationDuration, 250);
      bool1 = typedArray2.getBoolean(R.styleable.SwitchButton_kswFadeBack, true);
      bool2 = typedArray2.getColor(R.styleable.SwitchButton_kswTintColor, 0);
      charSequence1 = typedArray2.getString(R.styleable.SwitchButton_kswTextOn);
      charSequence2 = typedArray2.getString(R.styleable.SwitchButton_kswTextOff);
      f2 = Math.max(f2, f10 / 2.0F);
      f2 = typedArray2.getDimension(R.styleable.SwitchButton_kswTextMarginH, f2);
      bool3 = typedArray2.getBoolean(R.styleable.SwitchButton_kswAutoAdjustTextPosition, true);
      typedArray2.recycle();
      ColorStateList colorStateList1 = colorStateList2;
    } else {
      c = 'ú';
      f4 = 1.8F;
      f8 = f3;
      f10 = f1;
      charSequence1 = null;
      charSequence2 = null;
      bool3 = true;
      bool2 = false;
      drawable1 = null;
      f7 = 0.0F;
      f6 = 0.0F;
      f5 = 0.0F;
      typedArray2 = null;
      drawable2 = null;
      colorStateList = null;
      float f = 0.0F;
      bool1 = true;
      f9 = f1;
      f1 = f4;
      f4 = f;
    } 
    if (paramAttributeSet == null) {
      paramAttributeSet = null;
    } else {
      typedArray1 = getContext().obtainStyledAttributes(paramAttributeSet, new int[] { 16842970, 16842981 });
    } 
    if (typedArray1 != null) {
      boolean bool4 = typedArray1.getBoolean(0, true);
      boolean bool5 = typedArray1.getBoolean(1, bool4);
      setFocusable(bool4);
      setClickable(bool5);
      typedArray1.recycle();
    } 
    this.mTextOn = charSequence1;
    this.mTextOff = charSequence2;
    this.mTextMarginH = f2;
    this.mAutoAdjustTextPosition = bool3;
    this.mThumbDrawable = drawable1;
    this.mThumbColor = colorStateList;
    if (this.mThumbDrawable != null) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    this.mIsThumbUseDrawable = bool3;
    this.mTintColor = bool2;
    if (this.mTintColor == 0) {
      TypedValue typedValue = new TypedValue();
      if (getContext().getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true)) {
        this.mTintColor = typedValue.data;
      } else {
        this.mTintColor = 3309506;
      } 
    } 
    boolean bool3 = true;
    if (!this.mIsThumbUseDrawable && this.mThumbColor == null) {
      this.mThumbColor = ColorUtils.generateThumbColorWithTintColor(this.mTintColor);
      this.mCurrThumbColor = this.mThumbColor.getDefaultColor();
    } 
    float f11 = f3;
    f2 = f8;
    if (this.mIsThumbUseDrawable) {
      f2 = Math.max(f8, this.mThumbDrawable.getMinimumWidth());
      f11 = Math.max(f3, this.mThumbDrawable.getMinimumHeight());
    } 
    this.mThumbSizeF.set(f2, f11);
    this.mBackDrawable = drawable2;
    this.mBackColor = (ColorStateList)typedArray2;
    if (this.mBackDrawable == null)
      bool3 = false; 
    this.mIsBackUseDrawable = bool3;
    if (!this.mIsBackUseDrawable && this.mBackColor == null) {
      this.mBackColor = ColorUtils.generateBackColorWithTintColor(this.mTintColor);
      this.mCurrBackColor = this.mBackColor.getDefaultColor();
      this.mNextBackColor = this.mBackColor.getColorForState(CHECKED_PRESSED_STATE, this.mCurrBackColor);
    } 
    this.mThumbMargin.set(f4, f6, f5, f7);
    f3 = f1;
    if (this.mThumbMargin.width() >= 0.0F)
      f3 = Math.max(f1, 1.0F); 
    this.mBackMeasureRatio = f3;
    this.mThumbRadius = f9;
    this.mBackRadius = f10;
    this.mAnimationDuration = c;
    this.mFadeBack = bool1;
    this.mProcessAnimator.setDuration(this.mAnimationDuration);
    if (isChecked())
      setProcess(1.0F); 
  }
  
  private Layout makeLayout(CharSequence paramCharSequence) {
    return (Layout)new StaticLayout(paramCharSequence, this.mTextPaint, (int)Math.ceil(Layout.getDesiredWidth(paramCharSequence, this.mTextPaint)), Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, false);
  }
  
  private int measureHeight(int paramInt) {
    float f1;
    float f2;
    int i = View.MeasureSpec.getMode(paramInt);
    int j = View.MeasureSpec.getSize(paramInt);
    paramInt = ceil(Math.max(this.mThumbSizeF.y, this.mThumbSizeF.y + this.mThumbMargin.top + this.mThumbMargin.right));
    if (this.mOnLayout != null) {
      f1 = this.mOnLayout.getHeight();
    } else {
      f1 = 0.0F;
    } 
    if (this.mOffLayout != null) {
      f2 = this.mOffLayout.getHeight();
    } else {
      f2 = 0.0F;
    } 
    if (f1 != 0.0F || f2 != 0.0F) {
      this.mTextHeight = Math.max(f1, f2);
      paramInt = ceil(Math.max(paramInt, this.mTextHeight));
    } else {
      this.mTextHeight = 0.0F;
    } 
    paramInt = Math.max(paramInt, getSuggestedMinimumHeight());
    int k = Math.max(paramInt, getPaddingTop() + paramInt + getPaddingBottom());
    if (i == 1073741824) {
      paramInt = Math.max(k, j);
    } else {
      paramInt = k;
      if (i == Integer.MIN_VALUE)
        paramInt = Math.min(k, j); 
    } 
    return paramInt;
  }
  
  private int measureWidth(int paramInt) {
    float f1;
    float f2;
    int i = View.MeasureSpec.getSize(paramInt);
    int j = View.MeasureSpec.getMode(paramInt);
    int k = ceil((this.mThumbSizeF.x * this.mBackMeasureRatio));
    paramInt = k;
    if (this.mIsBackUseDrawable)
      paramInt = Math.max(k, this.mBackDrawable.getMinimumWidth()); 
    if (this.mOnLayout != null) {
      f1 = this.mOnLayout.getWidth();
    } else {
      f1 = 0.0F;
    } 
    if (this.mOffLayout != null) {
      f2 = this.mOffLayout.getWidth();
    } else {
      f2 = 0.0F;
    } 
    if (f1 != 0.0F || f2 != 0.0F) {
      this.mTextWidth = Math.max(f1, f2) + this.mTextMarginH * 2.0F;
      f2 = paramInt;
      f1 = f2 - this.mThumbSizeF.x;
      if (f1 < this.mTextWidth)
        paramInt = (int)(f2 + this.mTextWidth - f1); 
    } else {
      this.mTextWidth = 0.0F;
    } 
    paramInt = Math.max(paramInt, ceil((paramInt + this.mThumbMargin.left + this.mThumbMargin.right)));
    k = Math.max(Math.max(paramInt, getPaddingLeft() + paramInt + getPaddingRight()), getSuggestedMinimumWidth());
    if (j == 1073741824) {
      paramInt = Math.max(k, i);
    } else {
      paramInt = k;
      if (j == Integer.MIN_VALUE)
        paramInt = Math.min(k, i); 
    } 
    return paramInt;
  }
  
  private void setDrawableState(Drawable paramDrawable) {
    if (paramDrawable != null) {
      paramDrawable.setState(getDrawableState());
      invalidate();
    } 
  }
  
  private void setup() {
    float f1 = getPaddingTop();
    float f2 = this.mThumbMargin.top;
    float f3 = 0.0F;
    f2 = f1 + Math.max(0.0F, f2);
    float f4 = getPaddingLeft() + Math.max(0.0F, this.mThumbMargin.left);
    f1 = f2;
    if (this.mOnLayout != null) {
      f1 = f2;
      if (this.mOffLayout != null) {
        f1 = f2;
        if (this.mThumbMargin.top + this.mThumbMargin.bottom > 0.0F)
          f1 = f2 + ((getMeasuredHeight() - getPaddingBottom() - getPaddingTop()) - this.mThumbSizeF.y - this.mThumbMargin.top - this.mThumbMargin.bottom) / 2.0F; 
      } 
    } 
    if (this.mIsThumbUseDrawable) {
      this.mThumbSizeF.x = Math.max(this.mThumbSizeF.x, this.mThumbDrawable.getMinimumWidth());
      this.mThumbSizeF.y = Math.max(this.mThumbSizeF.y, this.mThumbDrawable.getMinimumHeight());
    } 
    this.mThumbRectF.set(f4, f1, this.mThumbSizeF.x + f4, this.mThumbSizeF.y + f1);
    f4 = this.mThumbRectF.left - this.mThumbMargin.left;
    f1 = Math.min(0.0F, (Math.max(this.mThumbSizeF.x * this.mBackMeasureRatio, this.mThumbSizeF.x + this.mTextWidth) - this.mThumbRectF.width() - this.mTextWidth) / 2.0F);
    f2 = Math.min(0.0F, (this.mThumbRectF.height() + this.mThumbMargin.top + this.mThumbMargin.bottom - this.mTextHeight) / 2.0F);
    this.mBackRectF.set(f4 + f1, this.mThumbRectF.top - this.mThumbMargin.top + f2, f4 + this.mThumbMargin.left + Math.max(this.mThumbSizeF.x * this.mBackMeasureRatio, this.mThumbSizeF.x + this.mTextWidth) + this.mThumbMargin.right - f1, this.mThumbRectF.bottom + this.mThumbMargin.bottom - f2);
    this.mSafeRectF.set(this.mThumbRectF.left, 0.0F, this.mBackRectF.right - this.mThumbMargin.right - this.mThumbRectF.width(), 0.0F);
    this.mBackRadius = Math.min(Math.min(this.mBackRectF.width(), this.mBackRectF.height()) / 2.0F, this.mBackRadius);
    if (this.mBackDrawable != null)
      this.mBackDrawable.setBounds((int)this.mBackRectF.left, (int)this.mBackRectF.top, ceil(this.mBackRectF.right), ceil(this.mBackRectF.bottom)); 
    if (this.mOnLayout != null) {
      f4 = this.mBackRectF.left;
      f2 = (this.mBackRectF.width() - this.mThumbRectF.width() - this.mThumbMargin.right - this.mOnLayout.getWidth()) / 2.0F;
      if (this.mThumbMargin.left < 0.0F) {
        f1 = this.mThumbMargin.left * -0.5F;
      } else {
        f1 = 0.0F;
      } 
      f2 = f4 + f2 + f1;
      f1 = f2;
      if (!this.mIsBackUseDrawable) {
        f1 = f2;
        if (this.mAutoAdjustTextPosition)
          f1 = f2 + this.mBackRadius / 4.0F; 
      } 
      f2 = this.mBackRectF.top + (this.mBackRectF.height() - this.mOnLayout.getHeight()) / 2.0F;
      this.mTextOnRectF.set(f1, f2, this.mOnLayout.getWidth() + f1, this.mOnLayout.getHeight() + f2);
    } 
    if (this.mOffLayout != null) {
      f4 = this.mBackRectF.right;
      f2 = (this.mBackRectF.width() - this.mThumbRectF.width() - this.mThumbMargin.left - this.mOffLayout.getWidth()) / 2.0F;
      float f = this.mOffLayout.getWidth();
      f1 = f3;
      if (this.mThumbMargin.right < 0.0F)
        f1 = this.mThumbMargin.right * 0.5F; 
      f3 = f4 - f2 - f + f1;
      f1 = f3;
      if (!this.mIsBackUseDrawable) {
        f1 = f3;
        if (this.mAutoAdjustTextPosition)
          f1 = f3 - this.mBackRadius / 4.0F; 
      } 
      f3 = this.mBackRectF.top + (this.mBackRectF.height() - this.mOffLayout.getHeight()) / 2.0F;
      this.mTextOffRectF.set(f1, f3, this.mOffLayout.getWidth() + f1, this.mOffLayout.getHeight() + f3);
    } 
  }
  
  protected void animateToState(boolean paramBoolean) {
    if (this.mProcessAnimator == null)
      return; 
    if (this.mProcessAnimator.isRunning())
      this.mProcessAnimator.cancel(); 
    this.mProcessAnimator.setDuration(this.mAnimationDuration);
    if (paramBoolean) {
      this.mProcessAnimator.setFloatValues(new float[] { this.mProcess, 1.0F });
    } else {
      this.mProcessAnimator.setFloatValues(new float[] { this.mProcess, 0.0F });
    } 
    this.mProcessAnimator.start();
  }
  
  protected void drawableStateChanged() {
    int[] arrayOfInt;
    super.drawableStateChanged();
    if (!this.mIsThumbUseDrawable && this.mThumbColor != null) {
      this.mCurrThumbColor = this.mThumbColor.getColorForState(getDrawableState(), this.mCurrThumbColor);
    } else {
      setDrawableState(this.mThumbDrawable);
    } 
    if (isChecked()) {
      arrayOfInt = UNCHECKED_PRESSED_STATE;
    } else {
      arrayOfInt = CHECKED_PRESSED_STATE;
    } 
    ColorStateList colorStateList = getTextColors();
    if (colorStateList != null) {
      int i = colorStateList.getDefaultColor();
      this.mOnTextColor = colorStateList.getColorForState(CHECKED_PRESSED_STATE, i);
      this.mOffTextColor = colorStateList.getColorForState(UNCHECKED_PRESSED_STATE, i);
    } 
    if (!this.mIsBackUseDrawable && this.mBackColor != null) {
      this.mCurrBackColor = this.mBackColor.getColorForState(getDrawableState(), this.mCurrBackColor);
      this.mNextBackColor = this.mBackColor.getColorForState(arrayOfInt, this.mCurrBackColor);
    } else {
      if (this.mBackDrawable instanceof android.graphics.drawable.StateListDrawable && this.mFadeBack) {
        this.mBackDrawable.setState(arrayOfInt);
        this.mNextBackDrawable = this.mBackDrawable.getCurrent().mutate();
      } else {
        this.mNextBackDrawable = null;
      } 
      setDrawableState(this.mBackDrawable);
      if (this.mBackDrawable != null)
        this.mCurrentBackDrawable = this.mBackDrawable.getCurrent().mutate(); 
    } 
  }
  
  public long getAnimationDuration() {
    return this.mAnimationDuration;
  }
  
  public ColorStateList getBackColor() {
    return this.mBackColor;
  }
  
  public Drawable getBackDrawable() {
    return this.mBackDrawable;
  }
  
  public float getBackMeasureRatio() {
    return this.mBackMeasureRatio;
  }
  
  public float getBackRadius() {
    return this.mBackRadius;
  }
  
  public PointF getBackSizeF() {
    return new PointF(this.mBackRectF.width(), this.mBackRectF.height());
  }
  
  public final float getProcess() {
    return this.mProcess;
  }
  
  public ColorStateList getThumbColor() {
    return this.mThumbColor;
  }
  
  public Drawable getThumbDrawable() {
    return this.mThumbDrawable;
  }
  
  public float getThumbHeight() {
    return this.mThumbSizeF.y;
  }
  
  public RectF getThumbMargin() {
    return this.mThumbMargin;
  }
  
  public float getThumbRadius() {
    return this.mThumbRadius;
  }
  
  public PointF getThumbSizeF() {
    return this.mThumbSizeF;
  }
  
  public float getThumbWidth() {
    return this.mThumbSizeF.x;
  }
  
  public int getTintColor() {
    return this.mTintColor;
  }
  
  public boolean isDrawDebugRect() {
    return this.mDrawDebugRect;
  }
  
  public boolean isFadeBack() {
    return this.mFadeBack;
  }
  
  protected void onDraw(Canvas paramCanvas) {
    Layout layout;
    RectF rectF;
    super.onDraw(paramCanvas);
    if (this.mIsBackUseDrawable) {
      if (this.mFadeBack && this.mCurrentBackDrawable != null && this.mNextBackDrawable != null) {
        Drawable drawable1;
        Drawable drawable2;
        if (isChecked()) {
          drawable1 = this.mCurrentBackDrawable;
        } else {
          drawable1 = this.mNextBackDrawable;
        } 
        if (isChecked()) {
          drawable2 = this.mNextBackDrawable;
        } else {
          drawable2 = this.mCurrentBackDrawable;
        } 
        int i = (int)(getProcess() * 255.0F);
        drawable1.setAlpha(i);
        drawable1.draw(paramCanvas);
        drawable2.setAlpha(255 - i);
        drawable2.draw(paramCanvas);
      } else {
        this.mBackDrawable.setAlpha(255);
        this.mBackDrawable.draw(paramCanvas);
      } 
    } else if (this.mFadeBack) {
      int j;
      if (isChecked()) {
        i = this.mCurrBackColor;
      } else {
        i = this.mNextBackColor;
      } 
      if (isChecked()) {
        j = this.mNextBackColor;
      } else {
        j = this.mCurrBackColor;
      } 
      int k = (int)(getProcess() * 255.0F);
      int m = Color.alpha(i) * k / 255;
      this.mPaint.setARGB(m, Color.red(i), Color.green(i), Color.blue(i));
      paramCanvas.drawRoundRect(this.mBackRectF, this.mBackRadius, this.mBackRadius, this.mPaint);
      int i = Color.alpha(j) * (255 - k) / 255;
      this.mPaint.setARGB(i, Color.red(j), Color.green(j), Color.blue(j));
      paramCanvas.drawRoundRect(this.mBackRectF, this.mBackRadius, this.mBackRadius, this.mPaint);
      this.mPaint.setAlpha(255);
    } else {
      this.mPaint.setColor(this.mCurrBackColor);
      paramCanvas.drawRoundRect(this.mBackRectF, this.mBackRadius, this.mBackRadius, this.mPaint);
    } 
    if (getProcess() > 0.5D) {
      layout = this.mOnLayout;
    } else {
      layout = this.mOffLayout;
    } 
    if (getProcess() > 0.5D) {
      rectF = this.mTextOnRectF;
    } else {
      rectF = this.mTextOffRectF;
    } 
    if (layout != null && rectF != null) {
      int i;
      float f;
      if (getProcess() >= 0.75D) {
        f = getProcess() * 4.0F - 3.0F;
      } else if (getProcess() < 0.25D) {
        f = 1.0F - getProcess() * 4.0F;
      } else {
        f = 0.0F;
      } 
      int j = (int)(255.0F * f);
      if (getProcess() > 0.5D) {
        i = this.mOnTextColor;
      } else {
        i = this.mOffTextColor;
      } 
      j = Color.alpha(i) * j / 255;
      layout.getPaint().setARGB(j, Color.red(i), Color.green(i), Color.blue(i));
      paramCanvas.save();
      paramCanvas.translate(rectF.left, rectF.top);
      layout.draw(paramCanvas);
      paramCanvas.restore();
    } 
    this.mPresentThumbRectF.set(this.mThumbRectF);
    this.mPresentThumbRectF.offset(this.mProcess * this.mSafeRectF.width(), 0.0F);
    if (this.mIsThumbUseDrawable) {
      this.mThumbDrawable.setBounds((int)this.mPresentThumbRectF.left, (int)this.mPresentThumbRectF.top, ceil(this.mPresentThumbRectF.right), ceil(this.mPresentThumbRectF.bottom));
      this.mThumbDrawable.draw(paramCanvas);
    } else {
      this.mPaint.setColor(this.mCurrThumbColor);
      paramCanvas.drawRoundRect(this.mPresentThumbRectF, this.mThumbRadius, this.mThumbRadius, this.mPaint);
    } 
    if (this.mDrawDebugRect) {
      RectF rectF1;
      this.mRectPaint.setColor(Color.parseColor("#AA0000"));
      paramCanvas.drawRect(this.mBackRectF, this.mRectPaint);
      this.mRectPaint.setColor(Color.parseColor("#0000FF"));
      paramCanvas.drawRect(this.mPresentThumbRectF, this.mRectPaint);
      this.mRectPaint.setColor(Color.parseColor("#00CC00"));
      if (getProcess() > 0.5D) {
        rectF1 = this.mTextOnRectF;
      } else {
        rectF1 = this.mTextOffRectF;
      } 
      paramCanvas.drawRect(rectF1, this.mRectPaint);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mOnLayout == null && this.mTextOn != null)
      this.mOnLayout = makeLayout(this.mTextOn); 
    if (this.mOffLayout == null && this.mTextOff != null)
      this.mOffLayout = makeLayout(this.mTextOff); 
    setMeasuredDimension(measureWidth(paramInt1), measureHeight(paramInt2));
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    SavedState savedState = (SavedState)paramParcelable;
    setText(savedState.onText, savedState.offText);
    this.mRestoring = true;
    super.onRestoreInstanceState(savedState.getSuperState());
    this.mRestoring = false;
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    savedState.onText = this.mTextOn;
    savedState.offText = this.mTextOff;
    return (Parcelable)savedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3 || paramInt2 != paramInt4)
      setup(); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    boolean bool;
    float f5;
    if (!isEnabled() || !isClickable() || !isFocusable())
      return false; 
    int i = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getX();
    float f2 = this.mStartX;
    float f3 = paramMotionEvent.getY();
    float f4 = this.mStartY;
    switch (i) {
      default:
        return true;
      case 2:
        f4 = paramMotionEvent.getX();
        setProcess(getProcess() + (f4 - this.mLastX) / this.mSafeRectF.width());
        this.mLastX = f4;
      case 1:
      case 3:
        setPressed(false);
        bool = getStatusBasedOnPos();
        f5 = (float)(paramMotionEvent.getEventTime() - paramMotionEvent.getDownTime());
        if (f1 - f2 < this.mTouchSlop && f3 - f4 < this.mTouchSlop && f5 < this.mClickTimeout) {
          performClick();
        } else if (bool != isChecked()) {
          playSoundEffect(0);
          setChecked(bool);
        } else {
          animateToState(bool);
        } 
      case 0:
        break;
    } 
    catchView();
    this.mStartX = paramMotionEvent.getX();
    this.mStartY = paramMotionEvent.getY();
    this.mLastX = this.mStartX;
    setPressed(true);
  }
  
  public boolean performClick() {
    return super.performClick();
  }
  
  public void setAnimationDuration(long paramLong) {
    this.mAnimationDuration = paramLong;
  }
  
  public void setBackColor(ColorStateList paramColorStateList) {
    this.mBackColor = paramColorStateList;
    if (this.mBackColor != null)
      setBackDrawable((Drawable)null); 
    invalidate();
  }
  
  public void setBackColorRes(int paramInt) {
    setBackColor(ContextCompat.getColorStateList(getContext(), paramInt));
  }
  
  public void setBackDrawable(Drawable paramDrawable) {
    boolean bool;
    this.mBackDrawable = paramDrawable;
    if (this.mBackDrawable != null) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mIsBackUseDrawable = bool;
    setup();
    refreshDrawableState();
    requestLayout();
    invalidate();
  }
  
  public void setBackDrawableRes(int paramInt) {
    setBackDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setBackMeasureRatio(float paramFloat) {
    this.mBackMeasureRatio = paramFloat;
    requestLayout();
  }
  
  public void setBackRadius(float paramFloat) {
    this.mBackRadius = paramFloat;
    if (!this.mIsBackUseDrawable)
      invalidate(); 
  }
  
  public void setChecked(boolean paramBoolean) {
    if (isChecked() != paramBoolean)
      animateToState(paramBoolean); 
    if (this.mRestoring) {
      setCheckedImmediatelyNoEvent(paramBoolean);
    } else {
      super.setChecked(paramBoolean);
    } 
  }
  
  public void setCheckedImmediately(boolean paramBoolean) {
    float f;
    super.setChecked(paramBoolean);
    if (this.mProcessAnimator != null && this.mProcessAnimator.isRunning())
      this.mProcessAnimator.cancel(); 
    if (paramBoolean) {
      f = 1.0F;
    } else {
      f = 0.0F;
    } 
    setProcess(f);
    invalidate();
  }
  
  public void setCheckedImmediatelyNoEvent(boolean paramBoolean) {
    if (this.mChildOnCheckedChangeListener == null) {
      setCheckedImmediately(paramBoolean);
    } else {
      super.setOnCheckedChangeListener(null);
      setCheckedImmediately(paramBoolean);
      super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    } 
  }
  
  public void setCheckedNoEvent(boolean paramBoolean) {
    if (this.mChildOnCheckedChangeListener == null) {
      setChecked(paramBoolean);
    } else {
      super.setOnCheckedChangeListener(null);
      setChecked(paramBoolean);
      super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    } 
  }
  
  public void setDrawDebugRect(boolean paramBoolean) {
    this.mDrawDebugRect = paramBoolean;
    invalidate();
  }
  
  public void setFadeBack(boolean paramBoolean) {
    this.mFadeBack = paramBoolean;
  }
  
  public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener) {
    super.setOnCheckedChangeListener(paramOnCheckedChangeListener);
    this.mChildOnCheckedChangeListener = paramOnCheckedChangeListener;
  }
  
  public final void setProcess(float paramFloat) {
    float f;
    if (paramFloat > 1.0F) {
      f = 1.0F;
    } else {
      f = paramFloat;
      if (paramFloat < 0.0F)
        f = 0.0F; 
    } 
    this.mProcess = f;
    invalidate();
  }
  
  public void setText(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    this.mTextOn = paramCharSequence1;
    this.mTextOff = paramCharSequence2;
    this.mOnLayout = null;
    this.mOffLayout = null;
    requestLayout();
    invalidate();
  }
  
  public void setThumbColor(ColorStateList paramColorStateList) {
    this.mThumbColor = paramColorStateList;
    if (this.mThumbColor != null)
      setThumbDrawable((Drawable)null); 
  }
  
  public void setThumbColorRes(int paramInt) {
    setThumbColor(ContextCompat.getColorStateList(getContext(), paramInt));
  }
  
  public void setThumbDrawable(Drawable paramDrawable) {
    boolean bool;
    this.mThumbDrawable = paramDrawable;
    if (this.mThumbDrawable != null) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mIsThumbUseDrawable = bool;
    setup();
    refreshDrawableState();
    requestLayout();
    invalidate();
  }
  
  public void setThumbDrawableRes(int paramInt) {
    setThumbDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setThumbMargin(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.mThumbMargin.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    requestLayout();
  }
  
  public void setThumbMargin(RectF paramRectF) {
    if (paramRectF == null) {
      setThumbMargin(0.0F, 0.0F, 0.0F, 0.0F);
    } else {
      setThumbMargin(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom);
    } 
  }
  
  public void setThumbRadius(float paramFloat) {
    this.mThumbRadius = paramFloat;
    if (!this.mIsThumbUseDrawable)
      invalidate(); 
  }
  
  public void setThumbSize(float paramFloat1, float paramFloat2) {
    this.mThumbSizeF.set(paramFloat1, paramFloat2);
    setup();
    requestLayout();
  }
  
  public void setThumbSize(PointF paramPointF) {
    if (paramPointF == null) {
      float f = (getResources().getDisplayMetrics()).density * 20.0F;
      setThumbSize(f, f);
    } else {
      setThumbSize(paramPointF.x, paramPointF.y);
    } 
  }
  
  public void setTintColor(int paramInt) {
    this.mTintColor = paramInt;
    this.mThumbColor = ColorUtils.generateThumbColorWithTintColor(this.mTintColor);
    this.mBackColor = ColorUtils.generateBackColorWithTintColor(this.mTintColor);
    this.mIsBackUseDrawable = false;
    this.mIsThumbUseDrawable = false;
    refreshDrawableState();
    invalidate();
  }
  
  public void toggleImmediately() {
    setCheckedImmediately(isChecked() ^ true);
  }
  
  public void toggleImmediatelyNoEvent() {
    if (this.mChildOnCheckedChangeListener == null) {
      toggleImmediately();
    } else {
      super.setOnCheckedChangeListener(null);
      toggleImmediately();
      super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    } 
  }
  
  public void toggleNoEvent() {
    if (this.mChildOnCheckedChangeListener == null) {
      toggle();
    } else {
      super.setOnCheckedChangeListener(null);
      toggle();
      super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    } 
  }
  
  static class SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
        public SwitchButton.SavedState createFromParcel(Parcel param2Parcel) {
          return new SwitchButton.SavedState(param2Parcel);
        }
        
        public SwitchButton.SavedState[] newArray(int param2Int) {
          return new SwitchButton.SavedState[param2Int];
        }
      };
    
    CharSequence offText;
    
    CharSequence onText;
    
    private SavedState(Parcel param1Parcel) {
      super(param1Parcel);
      this.onText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel);
      this.offText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel);
    }
    
    SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      TextUtils.writeToParcel(this.onText, param1Parcel, param1Int);
      TextUtils.writeToParcel(this.offText, param1Parcel, param1Int);
    }
  }
  
  static final class null implements Parcelable.Creator<SavedState> {
    public SwitchButton.SavedState createFromParcel(Parcel param1Parcel) {
      return new SwitchButton.SavedState(param1Parcel);
    }
    
    public SwitchButton.SavedState[] newArray(int param1Int) {
      return new SwitchButton.SavedState[param1Int];
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\kyleduo\switchbutton\SwitchButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */