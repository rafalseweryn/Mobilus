package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class AppCompatTextHelper {
  private static final int MONOSPACE = 3;
  
  private static final int SANS = 1;
  
  private static final int SERIF = 2;
  
  private boolean mAsyncFontPending;
  
  @NonNull
  private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
  
  private TintInfo mDrawableBottomTint;
  
  private TintInfo mDrawableEndTint;
  
  private TintInfo mDrawableLeftTint;
  
  private TintInfo mDrawableRightTint;
  
  private TintInfo mDrawableStartTint;
  
  private TintInfo mDrawableTopTint;
  
  private Typeface mFontTypeface;
  
  private int mStyle = 0;
  
  private final TextView mView;
  
  AppCompatTextHelper(TextView paramTextView) {
    this.mView = paramTextView;
    this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(this.mView);
  }
  
  private void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo) {
    if (paramDrawable != null && paramTintInfo != null)
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, this.mView.getDrawableState()); 
  }
  
  private static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt) {
    ColorStateList colorStateList = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (colorStateList != null) {
      TintInfo tintInfo = new TintInfo();
      tintInfo.mHasTintList = true;
      tintInfo.mTintList = colorStateList;
      return tintInfo;
    } 
    return null;
  }
  
  private void setTextSizeInternal(int paramInt, float paramFloat) {
    this.mAutoSizeTextHelper.setTextSizeInternal(paramInt, paramFloat);
  }
  
  private void updateTypefaceAndStyle(Context paramContext, TintTypedArray paramTintTypedArray) {
    this.mStyle = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
    boolean bool = paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily);
    boolean bool1 = true;
    if (bool || paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
      int i;
      this.mFontTypeface = null;
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
        i = R.styleable.TextAppearance_fontFamily;
      } else {
        i = R.styleable.TextAppearance_android_fontFamily;
      } 
      if (!paramContext.isRestricted()) {
        ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback() {
            public void onFontRetrievalFailed(int param1Int) {}
            
            public void onFontRetrieved(@NonNull Typeface param1Typeface) {
              AppCompatTextHelper.this.onAsyncTypefaceReceived(textViewWeak, param1Typeface);
            }
          };
        try {
          this.mFontTypeface = paramTintTypedArray.getFont(i, this.mStyle, fontCallback);
          if (this.mFontTypeface != null)
            bool1 = false; 
          this.mAsyncFontPending = bool1;
        } catch (UnsupportedOperationException|android.content.res.Resources.NotFoundException unsupportedOperationException) {}
      } 
      if (this.mFontTypeface == null) {
        String str = paramTintTypedArray.getString(i);
        if (str != null)
          this.mFontTypeface = Typeface.create(str, this.mStyle); 
      } 
      return;
    } 
    if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_typeface)) {
      this.mAsyncFontPending = false;
      switch (paramTintTypedArray.getInt(R.styleable.TextAppearance_android_typeface, 1)) {
        default:
          return;
        case 3:
          this.mFontTypeface = Typeface.MONOSPACE;
        case 2:
          this.mFontTypeface = Typeface.SERIF;
        case 1:
          break;
      } 
      this.mFontTypeface = Typeface.SANS_SERIF;
    } 
  }
  
  void applyCompoundDrawablesTints() {
    if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
      Drawable[] arrayOfDrawable = this.mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], this.mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], this.mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], this.mDrawableRightTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], this.mDrawableBottomTint);
    } 
    if (Build.VERSION.SDK_INT >= 17 && (this.mDrawableStartTint != null || this.mDrawableEndTint != null)) {
      Drawable[] arrayOfDrawable = this.mView.getCompoundDrawablesRelative();
      applyCompoundDrawableTint(arrayOfDrawable[0], this.mDrawableStartTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], this.mDrawableEndTint);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void autoSizeText() {
    this.mAutoSizeTextHelper.autoSizeText();
  }
  
  int getAutoSizeMaxTextSize() {
    return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
  }
  
  int getAutoSizeMinTextSize() {
    return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
  }
  
  int getAutoSizeStepGranularity() {
    return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
  }
  
  int[] getAutoSizeTextAvailableSizes() {
    return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
  }
  
  int getAutoSizeTextType() {
    return this.mAutoSizeTextHelper.getAutoSizeTextType();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  boolean isAutoSizeEnabled() {
    return this.mAutoSizeTextHelper.isAutoSizeEnabled();
  }
  
  @SuppressLint({"NewApi"})
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt) {
    ColorStateList colorStateList1;
    boolean bool2;
    boolean bool3;
    ColorStateList colorStateList4;
    Context context = this.mView.getContext();
    AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
    TintTypedArray tintTypedArray2 = TintTypedArray.obtainStyledAttributes(context, paramAttributeSet, R.styleable.AppCompatTextHelper, paramInt, 0);
    int i = tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft))
      this.mDrawableLeftTint = createTintInfo(context, appCompatDrawableManager, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0)); 
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop))
      this.mDrawableTopTint = createTintInfo(context, appCompatDrawableManager, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0)); 
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight))
      this.mDrawableRightTint = createTintInfo(context, appCompatDrawableManager, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0)); 
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom))
      this.mDrawableBottomTint = createTintInfo(context, appCompatDrawableManager, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0)); 
    if (Build.VERSION.SDK_INT >= 17) {
      if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableStart))
        this.mDrawableStartTint = createTintInfo(context, appCompatDrawableManager, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableStart, 0)); 
      if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableEnd))
        this.mDrawableEndTint = createTintInfo(context, appCompatDrawableManager, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableEnd, 0)); 
    } 
    tintTypedArray2.recycle();
    boolean bool1 = this.mView.getTransformationMethod() instanceof android.text.method.PasswordTransformationMethod;
    tintTypedArray2 = null;
    TintTypedArray tintTypedArray3 = null;
    ColorStateList colorStateList3 = null;
    if (i != -1) {
      tintTypedArray3 = TintTypedArray.obtainStyledAttributes(context, i, R.styleable.TextAppearance);
      if (!bool1 && tintTypedArray3.hasValue(R.styleable.TextAppearance_textAllCaps)) {
        bool2 = tintTypedArray3.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        bool3 = true;
      } else {
        bool3 = false;
        bool2 = bool3;
      } 
      updateTypefaceAndStyle(context, tintTypedArray3);
      if (Build.VERSION.SDK_INT < 23) {
        if (tintTypedArray3.hasValue(R.styleable.TextAppearance_android_textColor)) {
          ColorStateList colorStateList = tintTypedArray3.getColorStateList(R.styleable.TextAppearance_android_textColor);
        } else {
          tintTypedArray2 = null;
        } 
        if (tintTypedArray3.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
          colorStateList1 = tintTypedArray3.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
        } else {
          appCompatDrawableManager = null;
        } 
        if (tintTypedArray3.hasValue(R.styleable.TextAppearance_android_textColorLink))
          colorStateList3 = tintTypedArray3.getColorStateList(R.styleable.TextAppearance_android_textColorLink); 
      } else {
        colorStateList3 = null;
        colorStateList1 = colorStateList3;
      } 
      tintTypedArray3.recycle();
    } else {
      bool3 = false;
      bool2 = bool3;
      colorStateList3 = null;
      colorStateList1 = colorStateList3;
      tintTypedArray2 = tintTypedArray3;
    } 
    TintTypedArray tintTypedArray4 = TintTypedArray.obtainStyledAttributes(context, paramAttributeSet, R.styleable.TextAppearance, paramInt, 0);
    boolean bool4 = bool3;
    boolean bool5 = bool2;
    if (!bool1) {
      bool4 = bool3;
      bool5 = bool2;
      if (tintTypedArray4.hasValue(R.styleable.TextAppearance_textAllCaps)) {
        bool5 = tintTypedArray4.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        bool4 = true;
      } 
    } 
    TintTypedArray tintTypedArray5 = tintTypedArray2;
    ColorStateList colorStateList5 = colorStateList3;
    ColorStateList colorStateList2 = colorStateList1;
    if (Build.VERSION.SDK_INT < 23) {
      ColorStateList colorStateList;
      if (tintTypedArray4.hasValue(R.styleable.TextAppearance_android_textColor))
        colorStateList = tintTypedArray4.getColorStateList(R.styleable.TextAppearance_android_textColor); 
      if (tintTypedArray4.hasValue(R.styleable.TextAppearance_android_textColorHint))
        colorStateList1 = tintTypedArray4.getColorStateList(R.styleable.TextAppearance_android_textColorHint); 
      colorStateList4 = colorStateList;
      colorStateList5 = colorStateList3;
      colorStateList2 = colorStateList1;
      if (tintTypedArray4.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
        colorStateList5 = tintTypedArray4.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
        colorStateList2 = colorStateList1;
        colorStateList4 = colorStateList;
      } 
    } 
    if (Build.VERSION.SDK_INT >= 28 && tintTypedArray4.hasValue(R.styleable.TextAppearance_android_textSize) && tintTypedArray4.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)
      this.mView.setTextSize(0, 0.0F); 
    updateTypefaceAndStyle(context, tintTypedArray4);
    tintTypedArray4.recycle();
    if (colorStateList4 != null)
      this.mView.setTextColor(colorStateList4); 
    if (colorStateList2 != null)
      this.mView.setHintTextColor(colorStateList2); 
    if (colorStateList5 != null)
      this.mView.setLinkTextColor(colorStateList5); 
    if (!bool1 && bool4)
      setAllCaps(bool5); 
    if (this.mFontTypeface != null)
      this.mView.setTypeface(this.mFontTypeface, this.mStyle); 
    this.mAutoSizeTextHelper.loadFromAttributes(paramAttributeSet, paramInt);
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && this.mAutoSizeTextHelper.getAutoSizeTextType() != 0) {
      int[] arrayOfInt = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
      if (arrayOfInt.length > 0)
        if (this.mView.getAutoSizeStepGranularity() != -1.0F) {
          this.mView.setAutoSizeTextTypeUniformWithConfiguration(this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
        } else {
          this.mView.setAutoSizeTextTypeUniformWithPresetSizes(arrayOfInt, 0);
        }  
    } 
    TintTypedArray tintTypedArray1 = TintTypedArray.obtainStyledAttributes(context, paramAttributeSet, R.styleable.AppCompatTextView);
    i = tintTypedArray1.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
    int j = tintTypedArray1.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
    paramInt = tintTypedArray1.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, -1);
    tintTypedArray1.recycle();
    if (i != -1)
      TextViewCompat.setFirstBaselineToTopHeight(this.mView, i); 
    if (j != -1)
      TextViewCompat.setLastBaselineToBottomHeight(this.mView, j); 
    if (paramInt != -1)
      TextViewCompat.setLineHeight(this.mView, paramInt); 
  }
  
  void onAsyncTypefaceReceived(WeakReference<TextView> paramWeakReference, Typeface paramTypeface) {
    if (this.mAsyncFontPending) {
      this.mFontTypeface = paramTypeface;
      TextView textView = paramWeakReference.get();
      if (textView != null)
        textView.setTypeface(paramTypeface, this.mStyle); 
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
      autoSizeText(); 
  }
  
  void onSetTextAppearance(Context paramContext, int paramInt) {
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    if (tintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps))
      setAllCaps(tintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false)); 
    if (Build.VERSION.SDK_INT < 23 && tintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
      ColorStateList colorStateList = tintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
      if (colorStateList != null)
        this.mView.setTextColor(colorStateList); 
    } 
    if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize) && tintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)
      this.mView.setTextSize(0, 0.0F); 
    updateTypefaceAndStyle(paramContext, tintTypedArray);
    tintTypedArray.recycle();
    if (this.mFontTypeface != null)
      this.mView.setTypeface(this.mFontTypeface, this.mStyle); 
  }
  
  void setAllCaps(boolean paramBoolean) {
    this.mView.setAllCaps(paramBoolean);
  }
  
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
  }
  
  void setAutoSizeTextTypeWithDefaults(int paramInt) {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(paramInt);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setTextSize(int paramInt, float paramFloat) {
    if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && !isAutoSizeEnabled())
      setTextSizeInternal(paramInt, paramFloat); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\AppCompatTextHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */