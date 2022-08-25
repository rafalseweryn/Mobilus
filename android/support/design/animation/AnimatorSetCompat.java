package android.support.design.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.annotation.RestrictTo;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimatorSetCompat {
  public static void playTogether(AnimatorSet paramAnimatorSet, List<Animator> paramList) {
    int i = paramList.size();
    long l = 0L;
    for (byte b = 0; b < i; b++) {
      Animator animator = paramList.get(b);
      l = Math.max(l, animator.getStartDelay() + animator.getDuration());
    } 
    ValueAnimator valueAnimator = ValueAnimator.ofInt(new int[] { 0, 0 });
    valueAnimator.setDuration(l);
    paramList.add(0, valueAnimator);
    paramAnimatorSet.playTogether(paramList);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\animation\AnimatorSetCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */