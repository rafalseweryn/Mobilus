package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.NonNull;
import java.util.ArrayList;

class AnimatorUtils {
  static void addPauseListener(@NonNull Animator paramAnimator, @NonNull AnimatorListenerAdapter paramAnimatorListenerAdapter) {
    if (Build.VERSION.SDK_INT >= 19)
      paramAnimator.addPauseListener((Animator.AnimatorPauseListener)paramAnimatorListenerAdapter); 
  }
  
  static void pause(@NonNull Animator paramAnimator) {
    if (Build.VERSION.SDK_INT >= 19) {
      paramAnimator.pause();
    } else {
      ArrayList<Animator.AnimatorListener> arrayList = paramAnimator.getListeners();
      if (arrayList != null) {
        byte b = 0;
        int i = arrayList.size();
        while (b < i) {
          Animator.AnimatorListener animatorListener = arrayList.get(b);
          if (animatorListener instanceof AnimatorPauseListenerCompat)
            ((AnimatorPauseListenerCompat)animatorListener).onAnimationPause(paramAnimator); 
          b++;
        } 
      } 
    } 
  }
  
  static void resume(@NonNull Animator paramAnimator) {
    if (Build.VERSION.SDK_INT >= 19) {
      paramAnimator.resume();
    } else {
      ArrayList<Animator.AnimatorListener> arrayList = paramAnimator.getListeners();
      if (arrayList != null) {
        byte b = 0;
        int i = arrayList.size();
        while (b < i) {
          Animator.AnimatorListener animatorListener = arrayList.get(b);
          if (animatorListener instanceof AnimatorPauseListenerCompat)
            ((AnimatorPauseListenerCompat)animatorListener).onAnimationResume(paramAnimator); 
          b++;
        } 
      } 
    } 
  }
  
  static interface AnimatorPauseListenerCompat {
    void onAnimationPause(Animator param1Animator);
    
    void onAnimationResume(Animator param1Animator);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\AnimatorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */