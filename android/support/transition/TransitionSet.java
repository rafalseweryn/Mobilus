package android.support.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

public class TransitionSet extends Transition {
  private static final int FLAG_CHANGE_EPICENTER = 8;
  
  private static final int FLAG_CHANGE_INTERPOLATOR = 1;
  
  private static final int FLAG_CHANGE_PATH_MOTION = 4;
  
  private static final int FLAG_CHANGE_PROPAGATION = 2;
  
  public static final int ORDERING_SEQUENTIAL = 1;
  
  public static final int ORDERING_TOGETHER = 0;
  
  private int mChangeFlags = 0;
  
  int mCurrentListeners;
  
  private boolean mPlayTogether = true;
  
  boolean mStarted = false;
  
  private ArrayList<Transition> mTransitions = new ArrayList<>();
  
  public TransitionSet() {}
  
  public TransitionSet(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, Styleable.TRANSITION_SET);
    setOrdering(TypedArrayUtils.getNamedInt(typedArray, (XmlPullParser)paramAttributeSet, "transitionOrdering", 0, 0));
    typedArray.recycle();
  }
  
  private void setupStartEndListeners() {
    TransitionSetListener transitionSetListener = new TransitionSetListener(this);
    Iterator<Transition> iterator = this.mTransitions.iterator();
    while (iterator.hasNext())
      ((Transition)iterator.next()).addListener(transitionSetListener); 
    this.mCurrentListeners = this.mTransitions.size();
  }
  
  @NonNull
  public TransitionSet addListener(@NonNull Transition.TransitionListener paramTransitionListener) {
    return (TransitionSet)super.addListener(paramTransitionListener);
  }
  
  @NonNull
  public TransitionSet addTarget(@IdRes int paramInt) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).addTarget(paramInt); 
    return (TransitionSet)super.addTarget(paramInt);
  }
  
  @NonNull
  public TransitionSet addTarget(@NonNull View paramView) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).addTarget(paramView); 
    return (TransitionSet)super.addTarget(paramView);
  }
  
  @NonNull
  public TransitionSet addTarget(@NonNull Class paramClass) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).addTarget(paramClass); 
    return (TransitionSet)super.addTarget(paramClass);
  }
  
  @NonNull
  public TransitionSet addTarget(@NonNull String paramString) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).addTarget(paramString); 
    return (TransitionSet)super.addTarget(paramString);
  }
  
  @NonNull
  public TransitionSet addTransition(@NonNull Transition paramTransition) {
    this.mTransitions.add(paramTransition);
    paramTransition.mParent = this;
    if (this.mDuration >= 0L)
      paramTransition.setDuration(this.mDuration); 
    if ((this.mChangeFlags & 0x1) != 0)
      paramTransition.setInterpolator(getInterpolator()); 
    if ((this.mChangeFlags & 0x2) != 0)
      paramTransition.setPropagation(getPropagation()); 
    if ((this.mChangeFlags & 0x4) != 0)
      paramTransition.setPathMotion(getPathMotion()); 
    if ((this.mChangeFlags & 0x8) != 0)
      paramTransition.setEpicenterCallback(getEpicenterCallback()); 
    return this;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected void cancel() {
    super.cancel();
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).cancel(); 
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues) {
    if (isValidTarget(paramTransitionValues.view))
      for (Transition transition : this.mTransitions) {
        if (transition.isValidTarget(paramTransitionValues.view)) {
          transition.captureEndValues(paramTransitionValues);
          paramTransitionValues.mTargetedTransitions.add(transition);
        } 
      }  
  }
  
  void capturePropagationValues(TransitionValues paramTransitionValues) {
    super.capturePropagationValues(paramTransitionValues);
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).capturePropagationValues(paramTransitionValues); 
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues) {
    if (isValidTarget(paramTransitionValues.view))
      for (Transition transition : this.mTransitions) {
        if (transition.isValidTarget(paramTransitionValues.view)) {
          transition.captureStartValues(paramTransitionValues);
          paramTransitionValues.mTargetedTransitions.add(transition);
        } 
      }  
  }
  
  public Transition clone() {
    TransitionSet transitionSet = (TransitionSet)super.clone();
    transitionSet.mTransitions = new ArrayList<>();
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      transitionSet.addTransition(((Transition)this.mTransitions.get(b)).clone()); 
    return transitionSet;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected void createAnimators(ViewGroup paramViewGroup, TransitionValuesMaps paramTransitionValuesMaps1, TransitionValuesMaps paramTransitionValuesMaps2, ArrayList<TransitionValues> paramArrayList1, ArrayList<TransitionValues> paramArrayList2) {
    long l = getStartDelay();
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++) {
      Transition transition = this.mTransitions.get(b);
      if (l > 0L && (this.mPlayTogether || b == 0)) {
        long l1 = transition.getStartDelay();
        if (l1 > 0L) {
          transition.setStartDelay(l1 + l);
        } else {
          transition.setStartDelay(l);
        } 
      } 
      transition.createAnimators(paramViewGroup, paramTransitionValuesMaps1, paramTransitionValuesMaps2, paramArrayList1, paramArrayList2);
    } 
  }
  
  @NonNull
  public Transition excludeTarget(int paramInt, boolean paramBoolean) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).excludeTarget(paramInt, paramBoolean); 
    return super.excludeTarget(paramInt, paramBoolean);
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull View paramView, boolean paramBoolean) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).excludeTarget(paramView, paramBoolean); 
    return super.excludeTarget(paramView, paramBoolean);
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull Class paramClass, boolean paramBoolean) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).excludeTarget(paramClass, paramBoolean); 
    return super.excludeTarget(paramClass, paramBoolean);
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull String paramString, boolean paramBoolean) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).excludeTarget(paramString, paramBoolean); 
    return super.excludeTarget(paramString, paramBoolean);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void forceToEnd(ViewGroup paramViewGroup) {
    super.forceToEnd(paramViewGroup);
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).forceToEnd(paramViewGroup); 
  }
  
  public int getOrdering() {
    return this.mPlayTogether ^ true;
  }
  
  public Transition getTransitionAt(int paramInt) {
    return (paramInt < 0 || paramInt >= this.mTransitions.size()) ? null : this.mTransitions.get(paramInt);
  }
  
  public int getTransitionCount() {
    return this.mTransitions.size();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void pause(View paramView) {
    super.pause(paramView);
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).pause(paramView); 
  }
  
  @NonNull
  public TransitionSet removeListener(@NonNull Transition.TransitionListener paramTransitionListener) {
    return (TransitionSet)super.removeListener(paramTransitionListener);
  }
  
  @NonNull
  public TransitionSet removeTarget(@IdRes int paramInt) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).removeTarget(paramInt); 
    return (TransitionSet)super.removeTarget(paramInt);
  }
  
  @NonNull
  public TransitionSet removeTarget(@NonNull View paramView) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).removeTarget(paramView); 
    return (TransitionSet)super.removeTarget(paramView);
  }
  
  @NonNull
  public TransitionSet removeTarget(@NonNull Class paramClass) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).removeTarget(paramClass); 
    return (TransitionSet)super.removeTarget(paramClass);
  }
  
  @NonNull
  public TransitionSet removeTarget(@NonNull String paramString) {
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).removeTarget(paramString); 
    return (TransitionSet)super.removeTarget(paramString);
  }
  
  @NonNull
  public TransitionSet removeTransition(@NonNull Transition paramTransition) {
    this.mTransitions.remove(paramTransition);
    paramTransition.mParent = null;
    return this;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void resume(View paramView) {
    super.resume(paramView);
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).resume(paramView); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected void runAnimators() {
    if (this.mTransitions.isEmpty()) {
      start();
      end();
      return;
    } 
    setupStartEndListeners();
    if (!this.mPlayTogether) {
      for (byte b = 1; b < this.mTransitions.size(); b++) {
        ((Transition)this.mTransitions.get(b - 1)).addListener(new TransitionListenerAdapter() {
              public void onTransitionEnd(@NonNull Transition param1Transition) {
                nextTransition.runAnimators();
                param1Transition.removeListener(this);
              }
            });
      } 
      Transition transition = this.mTransitions.get(0);
      if (transition != null)
        transition.runAnimators(); 
    } else {
      Iterator<Transition> iterator = this.mTransitions.iterator();
      while (iterator.hasNext())
        ((Transition)iterator.next()).runAnimators(); 
    } 
  }
  
  void setCanRemoveViews(boolean paramBoolean) {
    super.setCanRemoveViews(paramBoolean);
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).setCanRemoveViews(paramBoolean); 
  }
  
  @NonNull
  public TransitionSet setDuration(long paramLong) {
    super.setDuration(paramLong);
    if (this.mDuration >= 0L) {
      int i = this.mTransitions.size();
      for (byte b = 0; b < i; b++)
        ((Transition)this.mTransitions.get(b)).setDuration(paramLong); 
    } 
    return this;
  }
  
  public void setEpicenterCallback(Transition.EpicenterCallback paramEpicenterCallback) {
    super.setEpicenterCallback(paramEpicenterCallback);
    this.mChangeFlags |= 0x8;
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).setEpicenterCallback(paramEpicenterCallback); 
  }
  
  @NonNull
  public TransitionSet setInterpolator(@Nullable TimeInterpolator paramTimeInterpolator) {
    this.mChangeFlags |= 0x1;
    if (this.mTransitions != null) {
      int i = this.mTransitions.size();
      for (byte b = 0; b < i; b++)
        ((Transition)this.mTransitions.get(b)).setInterpolator(paramTimeInterpolator); 
    } 
    return (TransitionSet)super.setInterpolator(paramTimeInterpolator);
  }
  
  @NonNull
  public TransitionSet setOrdering(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid parameter for TransitionSet ordering: ");
        stringBuilder.append(paramInt);
        throw new AndroidRuntimeException(stringBuilder.toString());
      case 1:
        this.mPlayTogether = false;
        return this;
      case 0:
        break;
    } 
    this.mPlayTogether = true;
    return this;
  }
  
  public void setPathMotion(PathMotion paramPathMotion) {
    super.setPathMotion(paramPathMotion);
    this.mChangeFlags |= 0x4;
    for (byte b = 0; b < this.mTransitions.size(); b++)
      ((Transition)this.mTransitions.get(b)).setPathMotion(paramPathMotion); 
  }
  
  public void setPropagation(TransitionPropagation paramTransitionPropagation) {
    super.setPropagation(paramTransitionPropagation);
    this.mChangeFlags |= 0x2;
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).setPropagation(paramTransitionPropagation); 
  }
  
  TransitionSet setSceneRoot(ViewGroup paramViewGroup) {
    super.setSceneRoot(paramViewGroup);
    int i = this.mTransitions.size();
    for (byte b = 0; b < i; b++)
      ((Transition)this.mTransitions.get(b)).setSceneRoot(paramViewGroup); 
    return this;
  }
  
  @NonNull
  public TransitionSet setStartDelay(long paramLong) {
    return (TransitionSet)super.setStartDelay(paramLong);
  }
  
  String toString(String paramString) {
    String str = super.toString(paramString);
    for (byte b = 0; b < this.mTransitions.size(); b++) {
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str);
      stringBuilder2.append("\n");
      Transition transition = this.mTransitions.get(b);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("  ");
      stringBuilder2.append(transition.toString(stringBuilder1.toString()));
      str = stringBuilder2.toString();
    } 
    return str;
  }
  
  static class TransitionSetListener extends TransitionListenerAdapter {
    TransitionSet mTransitionSet;
    
    TransitionSetListener(TransitionSet param1TransitionSet) {
      this.mTransitionSet = param1TransitionSet;
    }
    
    public void onTransitionEnd(@NonNull Transition param1Transition) {
      TransitionSet transitionSet = this.mTransitionSet;
      transitionSet.mCurrentListeners--;
      if (this.mTransitionSet.mCurrentListeners == 0) {
        this.mTransitionSet.mStarted = false;
        this.mTransitionSet.end();
      } 
      param1Transition.removeListener(this);
    }
    
    public void onTransitionStart(@NonNull Transition param1Transition) {
      if (!this.mTransitionSet.mStarted) {
        this.mTransitionSet.start();
        this.mTransitionSet.mStarted = true;
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\TransitionSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */