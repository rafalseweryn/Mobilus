package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.annotation.RestrictTo;
import android.util.StateSet;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class StateListAnimator {
  private final Animator.AnimatorListener animationListener = (Animator.AnimatorListener)new AnimatorListenerAdapter() {
      public void onAnimationEnd(Animator param1Animator) {
        if (StateListAnimator.this.runningAnimator == param1Animator)
          StateListAnimator.this.runningAnimator = null; 
      }
    };
  
  private Tuple lastMatch = null;
  
  ValueAnimator runningAnimator = null;
  
  private final ArrayList<Tuple> tuples = new ArrayList<>();
  
  private void cancel() {
    if (this.runningAnimator != null) {
      this.runningAnimator.cancel();
      this.runningAnimator = null;
    } 
  }
  
  private void start(Tuple paramTuple) {
    this.runningAnimator = paramTuple.animator;
    this.runningAnimator.start();
  }
  
  public void addState(int[] paramArrayOfint, ValueAnimator paramValueAnimator) {
    Tuple tuple = new Tuple(paramArrayOfint, paramValueAnimator);
    paramValueAnimator.addListener(this.animationListener);
    this.tuples.add(tuple);
  }
  
  public void jumpToCurrentState() {
    if (this.runningAnimator != null) {
      this.runningAnimator.end();
      this.runningAnimator = null;
    } 
  }
  
  public void setState(int[] paramArrayOfint) {
    int i = this.tuples.size();
    byte b = 0;
    while (true) {
      if (b < i) {
        Tuple tuple = this.tuples.get(b);
        if (StateSet.stateSetMatches(tuple.specs, paramArrayOfint)) {
          Tuple tuple1 = tuple;
          break;
        } 
        b++;
        continue;
      } 
      paramArrayOfint = null;
      break;
    } 
    if (paramArrayOfint == this.lastMatch)
      return; 
    if (this.lastMatch != null)
      cancel(); 
    this.lastMatch = (Tuple)paramArrayOfint;
    if (paramArrayOfint != null)
      start((Tuple)paramArrayOfint); 
  }
  
  static class Tuple {
    final ValueAnimator animator;
    
    final int[] specs;
    
    Tuple(int[] param1ArrayOfint, ValueAnimator param1ValueAnimator) {
      this.specs = param1ArrayOfint;
      this.animator = param1ValueAnimator;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\StateListAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */