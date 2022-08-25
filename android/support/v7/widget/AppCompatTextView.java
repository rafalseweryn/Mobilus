package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RestrictTo;
import android.support.v4.text.PrecomputedTextCompat;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.util.concurrent.Future;

public class AppCompatTextView extends TextView implements TintableBackgroundView, AutoSizeableTextView {
  private final AppCompatBackgroundHelper mBackgroundTintHelper = new AppCompatBackgroundHelper((View)this);
  
  @Nullable
  private Future<PrecomputedTextCompat> mPrecomputedTextFuture;
  
  private final AppCompatTextHelper mTextHelper;
  
  public AppCompatTextView(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 16842884);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    this.mBackgroundTintHelper.loadFromAttributes(paramAttributeSet, paramInt);
    this.mTextHelper = new AppCompatTextHelper(this);
    this.mTextHelper.loadFromAttributes(paramAttributeSet, paramInt);
    this.mTextHelper.applyCompoundDrawablesTints();
  }
  
  private void consumeTextFutureAndSetBlocking() {
    if (this.mPrecomputedTextFuture != null)
      try {
        Future<PrecomputedTextCompat> future = this.mPrecomputedTextFuture;
        this.mPrecomputedTextFuture = null;
        TextViewCompat.setPrecomputedText(this, future.get());
      } catch (InterruptedException|java.util.concurrent.ExecutionException interruptedException) {} 
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.applySupportBackgroundTint(); 
    if (this.mTextHelper != null)
      this.mTextHelper.applyCompoundDrawablesTints(); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getAutoSizeMaxTextSize() {
    return PLATFORM_SUPPORTS_AUTOSIZE ? super.getAutoSizeMaxTextSize() : ((this.mTextHelper != null) ? this.mTextHelper.getAutoSizeMaxTextSize() : -1);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getAutoSizeMinTextSize() {
    return PLATFORM_SUPPORTS_AUTOSIZE ? super.getAutoSizeMinTextSize() : ((this.mTextHelper != null) ? this.mTextHelper.getAutoSizeMinTextSize() : -1);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getAutoSizeStepGranularity() {
    return PLATFORM_SUPPORTS_AUTOSIZE ? super.getAutoSizeStepGranularity() : ((this.mTextHelper != null) ? this.mTextHelper.getAutoSizeStepGranularity() : -1);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int[] getAutoSizeTextAvailableSizes() {
    return PLATFORM_SUPPORTS_AUTOSIZE ? super.getAutoSizeTextAvailableSizes() : ((this.mTextHelper != null) ? this.mTextHelper.getAutoSizeTextAvailableSizes() : new int[0]);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getAutoSizeTextType() {
    if (PLATFORM_SUPPORTS_AUTOSIZE) {
      int i = super.getAutoSizeTextType();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      return bool;
    } 
    return (this.mTextHelper != null) ? this.mTextHelper.getAutoSizeTextType() : 0;
  }
  
  public int getFirstBaselineToTopHeight() {
    return TextViewCompat.getFirstBaselineToTopHeight(this);
  }
  
  public int getLastBaselineToBottomHeight() {
    return TextViewCompat.getLastBaselineToBottomHeight(this);
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public ColorStateList getSupportBackgroundTintList() {
    ColorStateList colorStateList;
    if (this.mBackgroundTintHelper != null) {
      colorStateList = this.mBackgroundTintHelper.getSupportBackgroundTintList();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public PorterDuff.Mode getSupportBackgroundTintMode() {
    PorterDuff.Mode mode;
    if (this.mBackgroundTintHelper != null) {
      mode = this.mBackgroundTintHelper.getSupportBackgroundTintMode();
    } else {
      mode = null;
    } 
    return mode;
  }
  
  public CharSequence getText() {
    consumeTextFutureAndSetBlocking();
    return super.getText();
  }
  
  @NonNull
  public PrecomputedTextCompat.Params getTextMetricsParamsCompat() {
    return TextViewCompat.getTextMetricsParams(this);
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo) {
    return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(paramEditorInfo), paramEditorInfo, (View)this);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mTextHelper != null)
      this.mTextHelper.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    consumeTextFutureAndSetBlocking();
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
    if (this.mTextHelper != null && !PLATFORM_SUPPORTS_AUTOSIZE && this.mTextHelper.isAutoSizeEnabled())
      this.mTextHelper.autoSizeText(); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    if (PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
    } else if (this.mTextHelper != null) {
      this.mTextHelper.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    if (PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
    } else if (this.mTextHelper != null) {
      this.mTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setAutoSizeTextTypeWithDefaults(int paramInt) {
    if (PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setAutoSizeTextTypeWithDefaults(paramInt);
    } else if (this.mTextHelper != null) {
      this.mTextHelper.setAutoSizeTextTypeWithDefaults(paramInt);
    } 
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    super.setBackgroundDrawable(paramDrawable);
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.onSetBackgroundDrawable(paramDrawable); 
  }
  
  public void setBackgroundResource(@DrawableRes int paramInt) {
    super.setBackgroundResource(paramInt);
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.onSetBackgroundResource(paramInt); 
  }
  
  public void setCustomSelectionActionModeCallback(ActionMode.Callback paramCallback) {
    super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, paramCallback));
  }
  
  public void setFirstBaselineToTopHeight(@IntRange(from = 0L) @Px int paramInt) {
    if (Build.VERSION.SDK_INT >= 28) {
      super.setFirstBaselineToTopHeight(paramInt);
    } else {
      TextViewCompat.setFirstBaselineToTopHeight(this, paramInt);
    } 
  }
  
  public void setLastBaselineToBottomHeight(@IntRange(from = 0L) @Px int paramInt) {
    if (Build.VERSION.SDK_INT >= 28) {
      super.setLastBaselineToBottomHeight(paramInt);
    } else {
      TextViewCompat.setLastBaselineToBottomHeight(this, paramInt);
    } 
  }
  
  public void setLineHeight(@IntRange(from = 0L) @Px int paramInt) {
    TextViewCompat.setLineHeight(this, paramInt);
  }
  
  public void setPrecomputedText(@NonNull PrecomputedTextCompat paramPrecomputedTextCompat) {
    TextViewCompat.setPrecomputedText(this, paramPrecomputedTextCompat);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.setSupportBackgroundTintList(paramColorStateList); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.setSupportBackgroundTintMode(paramMode); 
  }
  
  public void setTextAppearance(Context paramContext, int paramInt) {
    super.setTextAppearance(paramContext, paramInt);
    if (this.mTextHelper != null)
      this.mTextHelper.onSetTextAppearance(paramContext, paramInt); 
  }
  
  public void setTextFuture(@NonNull Future<PrecomputedTextCompat> paramFuture) {
    this.mPrecomputedTextFuture = paramFuture;
    requestLayout();
  }
  
  public void setTextMetricsParamsCompat(@NonNull PrecomputedTextCompat.Params paramParams) {
    TextViewCompat.setTextMetricsParams(this, paramParams);
  }
  
  public void setTextSize(int paramInt, float paramFloat) {
    if (PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setTextSize(paramInt, paramFloat);
    } else if (this.mTextHelper != null) {
      this.mTextHelper.setTextSize(paramInt, paramFloat);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\AppCompatTextView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */