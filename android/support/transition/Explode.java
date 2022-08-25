package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class Explode extends Visibility {
  private static final String PROPNAME_SCREEN_BOUNDS = "android:explode:screenBounds";
  
  private static final TimeInterpolator sAccelerate;
  
  private static final TimeInterpolator sDecelerate = (TimeInterpolator)new DecelerateInterpolator();
  
  private int[] mTempLoc = new int[2];
  
  static {
    sAccelerate = (TimeInterpolator)new AccelerateInterpolator();
  }
  
  public Explode() {
    setPropagation(new CircularPropagation());
  }
  
  public Explode(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    setPropagation(new CircularPropagation());
  }
  
  private static float calculateDistance(float paramFloat1, float paramFloat2) {
    return (float)Math.sqrt((paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2));
  }
  
  private static float calculateMaxDistance(View paramView, int paramInt1, int paramInt2) {
    paramInt1 = Math.max(paramInt1, paramView.getWidth() - paramInt1);
    paramInt2 = Math.max(paramInt2, paramView.getHeight() - paramInt2);
    return calculateDistance(paramInt1, paramInt2);
  }
  
  private void calculateOut(View paramView, Rect paramRect, int[] paramArrayOfint) {
    int k;
    int m;
    paramView.getLocationOnScreen(this.mTempLoc);
    int i = this.mTempLoc[0];
    int j = this.mTempLoc[1];
    Rect rect = getEpicenter();
    if (rect == null) {
      k = paramView.getWidth() / 2 + i + Math.round(paramView.getTranslationX());
      m = paramView.getHeight() / 2 + j + Math.round(paramView.getTranslationY());
    } else {
      k = rect.centerX();
      m = rect.centerY();
    } 
    int n = paramRect.centerX();
    int i1 = paramRect.centerY();
    float f1 = (n - k);
    float f2 = (i1 - m);
    if (f1 == 0.0F && f2 == 0.0F) {
      f1 = (float)(Math.random() * 2.0D) - 1.0F;
      f2 = (float)(Math.random() * 2.0D) - 1.0F;
    } 
    float f3 = calculateDistance(f1, f2);
    f1 /= f3;
    f2 /= f3;
    f3 = calculateMaxDistance(paramView, k - i, m - j);
    paramArrayOfint[0] = Math.round(f1 * f3);
    paramArrayOfint[1] = Math.round(f3 * f2);
  }
  
  private void captureValues(TransitionValues paramTransitionValues) {
    View view = paramTransitionValues.view;
    view.getLocationOnScreen(this.mTempLoc);
    int i = this.mTempLoc[0];
    int j = this.mTempLoc[1];
    int k = view.getWidth();
    int m = view.getHeight();
    paramTransitionValues.values.put("android:explode:screenBounds", new Rect(i, j, k + i, m + j));
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues) {
    super.captureEndValues(paramTransitionValues);
    captureValues(paramTransitionValues);
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues) {
    super.captureStartValues(paramTransitionValues);
    captureValues(paramTransitionValues);
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, View paramView, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    if (paramTransitionValues2 == null)
      return null; 
    Rect rect = (Rect)paramTransitionValues2.values.get("android:explode:screenBounds");
    float f1 = paramView.getTranslationX();
    float f2 = paramView.getTranslationY();
    calculateOut((View)paramViewGroup, rect, this.mTempLoc);
    float f3 = this.mTempLoc[0];
    float f4 = this.mTempLoc[1];
    return TranslationAnimationCreator.createAnimation(paramView, paramTransitionValues2, rect.left, rect.top, f1 + f3, f2 + f4, f1, f2, sDecelerate);
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, View paramView, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    float f3;
    float f4;
    if (paramTransitionValues1 == null)
      return null; 
    Rect rect = (Rect)paramTransitionValues1.values.get("android:explode:screenBounds");
    int i = rect.left;
    int j = rect.top;
    float f1 = paramView.getTranslationX();
    float f2 = paramView.getTranslationY();
    int[] arrayOfInt = (int[])paramTransitionValues1.view.getTag(R.id.transition_position);
    if (arrayOfInt != null) {
      f3 = (arrayOfInt[0] - rect.left) + f1;
      f4 = (arrayOfInt[1] - rect.top) + f2;
      rect.offsetTo(arrayOfInt[0], arrayOfInt[1]);
    } else {
      f3 = f1;
      f4 = f2;
    } 
    calculateOut((View)paramViewGroup, rect, this.mTempLoc);
    return TranslationAnimationCreator.createAnimation(paramView, paramTransitionValues1, i, j, f1, f2, f3 + this.mTempLoc[0], f4 + this.mTempLoc[1], sAccelerate);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\Explode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */