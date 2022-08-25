package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.Space;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

final class IndicatorViewController {
  private static final int CAPTION_OPACITY_FADE_ANIMATION_DURATION = 167;
  
  private static final int CAPTION_STATE_ERROR = 1;
  
  private static final int CAPTION_STATE_HELPER_TEXT = 2;
  
  private static final int CAPTION_STATE_NONE = 0;
  
  private static final int CAPTION_TRANSLATE_Y_ANIMATION_DURATION = 217;
  
  static final int COUNTER_INDEX = 2;
  
  static final int ERROR_INDEX = 0;
  
  static final int HELPER_INDEX = 1;
  
  @Nullable
  private Animator captionAnimator;
  
  private FrameLayout captionArea;
  
  private int captionDisplayed;
  
  private int captionToShow;
  
  private final float captionTranslationYPx;
  
  private int captionViewsAdded;
  
  private final Context context;
  
  private boolean errorEnabled;
  
  private CharSequence errorText;
  
  private int errorTextAppearance;
  
  private TextView errorView;
  
  private CharSequence helperText;
  
  private boolean helperTextEnabled;
  
  private int helperTextTextAppearance;
  
  private TextView helperTextView;
  
  private LinearLayout indicatorArea;
  
  private int indicatorsAdded;
  
  private final TextInputLayout textInputView;
  
  private Typeface typeface;
  
  public IndicatorViewController(TextInputLayout paramTextInputLayout) {
    this.context = paramTextInputLayout.getContext();
    this.textInputView = paramTextInputLayout;
    this.captionTranslationYPx = this.context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
  }
  
  private boolean canAdjustIndicatorPadding() {
    boolean bool;
    if (this.indicatorArea != null && this.textInputView.getEditText() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void createCaptionAnimators(List<Animator> paramList, boolean paramBoolean, TextView paramTextView, int paramInt1, int paramInt2, int paramInt3) {
    if (paramTextView == null || !paramBoolean)
      return; 
    if (paramInt1 == paramInt3 || paramInt1 == paramInt2) {
      if (paramInt3 == paramInt1) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      } 
      paramList.add(createCaptionOpacityAnimator(paramTextView, paramBoolean));
      if (paramInt3 == paramInt1)
        paramList.add(createCaptionTranslationYAnimator(paramTextView)); 
    } 
  }
  
  private ObjectAnimator createCaptionOpacityAnimator(TextView paramTextView, boolean paramBoolean) {
    float f;
    if (paramBoolean) {
      f = 1.0F;
    } else {
      f = 0.0F;
    } 
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(paramTextView, View.ALPHA, new float[] { f });
    objectAnimator.setDuration(167L);
    objectAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
    return objectAnimator;
  }
  
  private ObjectAnimator createCaptionTranslationYAnimator(TextView paramTextView) {
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(paramTextView, View.TRANSLATION_Y, new float[] { -this.captionTranslationYPx, 0.0F });
    objectAnimator.setDuration(217L);
    objectAnimator.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    return objectAnimator;
  }
  
  @Nullable
  private TextView getCaptionViewFromDisplayState(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 2:
        return this.helperTextView;
      case 1:
        break;
    } 
    return this.errorView;
  }
  
  private boolean isCaptionStateError(int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || this.errorView == null || TextUtils.isEmpty(this.errorText))
      bool = false; 
    return bool;
  }
  
  private boolean isCaptionStateHelperText(int paramInt) {
    boolean bool;
    if (paramInt == 2 && this.helperTextView != null && !TextUtils.isEmpty(this.helperText)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void setCaptionViewVisibilities(int paramInt1, int paramInt2) {
    if (paramInt1 == paramInt2)
      return; 
    if (paramInt2 != 0) {
      TextView textView = getCaptionViewFromDisplayState(paramInt2);
      if (textView != null) {
        textView.setVisibility(0);
        textView.setAlpha(1.0F);
      } 
    } 
    if (paramInt1 != 0) {
      TextView textView = getCaptionViewFromDisplayState(paramInt1);
      if (textView != null) {
        textView.setVisibility(4);
        if (paramInt1 == 1)
          textView.setText(null); 
      } 
    } 
    this.captionDisplayed = paramInt2;
  }
  
  private void setTextViewTypeface(@Nullable TextView paramTextView, Typeface paramTypeface) {
    if (paramTextView != null)
      paramTextView.setTypeface(paramTypeface); 
  }
  
  private void setViewGroupGoneIfEmpty(ViewGroup paramViewGroup, int paramInt) {
    if (paramInt == 0)
      paramViewGroup.setVisibility(8); 
  }
  
  private boolean shouldAnimateCaptionView(TextView paramTextView, @Nullable CharSequence paramCharSequence) {
    boolean bool;
    if (ViewCompat.isLaidOut((View)this.textInputView) && this.textInputView.isEnabled() && (this.captionToShow != this.captionDisplayed || paramTextView == null || !TextUtils.equals(paramTextView.getText(), paramCharSequence))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void updateCaptionViewsVisibility(final int captionToHide, final int captionToShow, boolean paramBoolean) {
    if (paramBoolean) {
      AnimatorSet animatorSet = new AnimatorSet();
      this.captionAnimator = (Animator)animatorSet;
      ArrayList<Animator> arrayList = new ArrayList();
      createCaptionAnimators(arrayList, this.helperTextEnabled, this.helperTextView, 2, captionToHide, captionToShow);
      createCaptionAnimators(arrayList, this.errorEnabled, this.errorView, 1, captionToHide, captionToShow);
      AnimatorSetCompat.playTogether(animatorSet, arrayList);
      animatorSet.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator param1Animator) {
              IndicatorViewController.access$002(IndicatorViewController.this, captionToShow);
              IndicatorViewController.access$102(IndicatorViewController.this, null);
              if (captionViewToHide != null) {
                captionViewToHide.setVisibility(4);
                if (captionToHide == 1 && IndicatorViewController.this.errorView != null)
                  IndicatorViewController.this.errorView.setText(null); 
              } 
            }
            
            public void onAnimationStart(Animator param1Animator) {
              if (captionViewToShow != null)
                captionViewToShow.setVisibility(0); 
            }
          });
      animatorSet.start();
    } else {
      setCaptionViewVisibilities(captionToHide, captionToShow);
    } 
    this.textInputView.updateEditTextBackground();
    this.textInputView.updateLabelState(paramBoolean);
    this.textInputView.updateTextInputBoxState();
  }
  
  void addIndicator(TextView paramTextView, int paramInt) {
    if (this.indicatorArea == null && this.captionArea == null) {
      this.indicatorArea = new LinearLayout(this.context);
      this.indicatorArea.setOrientation(0);
      this.textInputView.addView((View)this.indicatorArea, -1, -2);
      this.captionArea = new FrameLayout(this.context);
      this.indicatorArea.addView((View)this.captionArea, -1, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
      Space space = new Space(this.context);
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0, 1.0F);
      this.indicatorArea.addView((View)space, (ViewGroup.LayoutParams)layoutParams);
      if (this.textInputView.getEditText() != null)
        adjustIndicatorPadding(); 
    } 
    if (isCaptionView(paramInt)) {
      this.captionArea.setVisibility(0);
      this.captionArea.addView((View)paramTextView);
      this.captionViewsAdded++;
    } else {
      this.indicatorArea.addView((View)paramTextView, paramInt);
    } 
    this.indicatorArea.setVisibility(0);
    this.indicatorsAdded++;
  }
  
  void adjustIndicatorPadding() {
    if (canAdjustIndicatorPadding())
      ViewCompat.setPaddingRelative((View)this.indicatorArea, ViewCompat.getPaddingStart((View)this.textInputView.getEditText()), 0, ViewCompat.getPaddingEnd((View)this.textInputView.getEditText()), 0); 
  }
  
  void cancelCaptionAnimator() {
    if (this.captionAnimator != null)
      this.captionAnimator.cancel(); 
  }
  
  boolean errorIsDisplayed() {
    return isCaptionStateError(this.captionDisplayed);
  }
  
  boolean errorShouldBeShown() {
    return isCaptionStateError(this.captionToShow);
  }
  
  CharSequence getErrorText() {
    return this.errorText;
  }
  
  @ColorInt
  int getErrorViewCurrentTextColor() {
    byte b;
    if (this.errorView != null) {
      b = this.errorView.getCurrentTextColor();
    } else {
      b = -1;
    } 
    return b;
  }
  
  @Nullable
  ColorStateList getErrorViewTextColors() {
    ColorStateList colorStateList;
    if (this.errorView != null) {
      colorStateList = this.errorView.getTextColors();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  CharSequence getHelperText() {
    return this.helperText;
  }
  
  @Nullable
  ColorStateList getHelperTextViewColors() {
    ColorStateList colorStateList;
    if (this.helperTextView != null) {
      colorStateList = this.helperTextView.getTextColors();
    } else {
      colorStateList = null;
    } 
    return colorStateList;
  }
  
  @ColorInt
  int getHelperTextViewCurrentTextColor() {
    byte b;
    if (this.helperTextView != null) {
      b = this.helperTextView.getCurrentTextColor();
    } else {
      b = -1;
    } 
    return b;
  }
  
  boolean helperTextIsDisplayed() {
    return isCaptionStateHelperText(this.captionDisplayed);
  }
  
  boolean helperTextShouldBeShown() {
    return isCaptionStateHelperText(this.captionToShow);
  }
  
  void hideError() {
    this.errorText = null;
    cancelCaptionAnimator();
    if (this.captionDisplayed == 1)
      if (this.helperTextEnabled && !TextUtils.isEmpty(this.helperText)) {
        this.captionToShow = 2;
      } else {
        this.captionToShow = 0;
      }  
    updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, null));
  }
  
  void hideHelperText() {
    cancelCaptionAnimator();
    if (this.captionDisplayed == 2)
      this.captionToShow = 0; 
    updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, null));
  }
  
  boolean isCaptionView(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 0)
      if (paramInt == 1) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    return bool2;
  }
  
  boolean isErrorEnabled() {
    return this.errorEnabled;
  }
  
  boolean isHelperTextEnabled() {
    return this.helperTextEnabled;
  }
  
  void removeIndicator(TextView paramTextView, int paramInt) {
    if (this.indicatorArea == null)
      return; 
    if (isCaptionView(paramInt) && this.captionArea != null) {
      this.captionViewsAdded--;
      setViewGroupGoneIfEmpty((ViewGroup)this.captionArea, this.captionViewsAdded);
      this.captionArea.removeView((View)paramTextView);
    } else {
      this.indicatorArea.removeView((View)paramTextView);
    } 
    this.indicatorsAdded--;
    setViewGroupGoneIfEmpty((ViewGroup)this.indicatorArea, this.indicatorsAdded);
  }
  
  void setErrorEnabled(boolean paramBoolean) {
    if (this.errorEnabled == paramBoolean)
      return; 
    cancelCaptionAnimator();
    if (paramBoolean) {
      this.errorView = (TextView)new AppCompatTextView(this.context);
      this.errorView.setId(R.id.textinput_error);
      if (this.typeface != null)
        this.errorView.setTypeface(this.typeface); 
      setErrorTextAppearance(this.errorTextAppearance);
      this.errorView.setVisibility(4);
      ViewCompat.setAccessibilityLiveRegion((View)this.errorView, 1);
      addIndicator(this.errorView, 0);
    } else {
      hideError();
      removeIndicator(this.errorView, 0);
      this.errorView = null;
      this.textInputView.updateEditTextBackground();
      this.textInputView.updateTextInputBoxState();
    } 
    this.errorEnabled = paramBoolean;
  }
  
  void setErrorTextAppearance(@StyleRes int paramInt) {
    this.errorTextAppearance = paramInt;
    if (this.errorView != null)
      this.textInputView.setTextAppearanceCompatWithErrorFallback(this.errorView, paramInt); 
  }
  
  void setErrorViewTextColor(@Nullable ColorStateList paramColorStateList) {
    if (this.errorView != null)
      this.errorView.setTextColor(paramColorStateList); 
  }
  
  void setHelperTextAppearance(@StyleRes int paramInt) {
    this.helperTextTextAppearance = paramInt;
    if (this.helperTextView != null)
      TextViewCompat.setTextAppearance(this.helperTextView, paramInt); 
  }
  
  void setHelperTextEnabled(boolean paramBoolean) {
    if (this.helperTextEnabled == paramBoolean)
      return; 
    cancelCaptionAnimator();
    if (paramBoolean) {
      this.helperTextView = (TextView)new AppCompatTextView(this.context);
      this.helperTextView.setId(R.id.textinput_helper_text);
      if (this.typeface != null)
        this.helperTextView.setTypeface(this.typeface); 
      this.helperTextView.setVisibility(4);
      ViewCompat.setAccessibilityLiveRegion((View)this.helperTextView, 1);
      setHelperTextAppearance(this.helperTextTextAppearance);
      addIndicator(this.helperTextView, 1);
    } else {
      hideHelperText();
      removeIndicator(this.helperTextView, 1);
      this.helperTextView = null;
      this.textInputView.updateEditTextBackground();
      this.textInputView.updateTextInputBoxState();
    } 
    this.helperTextEnabled = paramBoolean;
  }
  
  void setHelperTextViewTextColor(@Nullable ColorStateList paramColorStateList) {
    if (this.helperTextView != null)
      this.helperTextView.setTextColor(paramColorStateList); 
  }
  
  void setTypefaces(Typeface paramTypeface) {
    if (paramTypeface != this.typeface) {
      this.typeface = paramTypeface;
      setTextViewTypeface(this.errorView, paramTypeface);
      setTextViewTypeface(this.helperTextView, paramTypeface);
    } 
  }
  
  void showError(CharSequence paramCharSequence) {
    cancelCaptionAnimator();
    this.errorText = paramCharSequence;
    this.errorView.setText(paramCharSequence);
    if (this.captionDisplayed != 1)
      this.captionToShow = 1; 
    updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, paramCharSequence));
  }
  
  void showHelper(CharSequence paramCharSequence) {
    cancelCaptionAnimator();
    this.helperText = paramCharSequence;
    this.helperTextView.setText(paramCharSequence);
    if (this.captionDisplayed != 2)
      this.captionToShow = 2; 
    updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, paramCharSequence));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\IndicatorViewController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */