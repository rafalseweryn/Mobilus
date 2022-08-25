package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeScroll extends Transition {
  private static final String[] PROPERTIES = new String[] { "android:changeScroll:x", "android:changeScroll:y" };
  
  private static final String PROPNAME_SCROLL_X = "android:changeScroll:x";
  
  private static final String PROPNAME_SCROLL_Y = "android:changeScroll:y";
  
  public ChangeScroll() {}
  
  public ChangeScroll(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  private void captureValues(TransitionValues paramTransitionValues) {
    paramTransitionValues.values.put("android:changeScroll:x", Integer.valueOf(paramTransitionValues.view.getScrollX()));
    paramTransitionValues.values.put("android:changeScroll:y", Integer.valueOf(paramTransitionValues.view.getScrollY()));
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues) {
    captureValues(paramTransitionValues);
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues) {
    captureValues(paramTransitionValues);
  }
  
  @Nullable
  public Animator createAnimator(@NonNull ViewGroup paramViewGroup, @Nullable TransitionValues paramTransitionValues1, @Nullable TransitionValues paramTransitionValues2) {
    ObjectAnimator objectAnimator;
    TransitionValues transitionValues = null;
    if (paramTransitionValues1 == null || paramTransitionValues2 == null)
      return null; 
    View view = paramTransitionValues2.view;
    int i = ((Integer)paramTransitionValues1.values.get("android:changeScroll:x")).intValue();
    int j = ((Integer)paramTransitionValues2.values.get("android:changeScroll:x")).intValue();
    int k = ((Integer)paramTransitionValues1.values.get("android:changeScroll:y")).intValue();
    int m = ((Integer)paramTransitionValues2.values.get("android:changeScroll:y")).intValue();
    if (i != j) {
      view.setScrollX(i);
      ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(view, "scrollX", new int[] { i, j });
    } else {
      paramViewGroup = null;
    } 
    paramTransitionValues1 = transitionValues;
    if (k != m) {
      view.setScrollY(k);
      objectAnimator = ObjectAnimator.ofInt(view, "scrollY", new int[] { k, m });
    } 
    return TransitionUtils.mergeAnimators((Animator)paramViewGroup, (Animator)objectAnimator);
  }
  
  @Nullable
  public String[] getTransitionProperties() {
    return PROPERTIES;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\ChangeScroll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */