package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;

public abstract class Visibility extends Transition {
  public static final int MODE_IN = 1;
  
  public static final int MODE_OUT = 2;
  
  private static final String PROPNAME_PARENT = "android:visibility:parent";
  
  private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
  
  static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
  
  private static final String[] sTransitionProperties = new String[] { "android:visibility:visibility", "android:visibility:parent" };
  
  private int mMode = 3;
  
  public Visibility() {}
  
  public Visibility(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, Styleable.VISIBILITY_TRANSITION);
    int i = TypedArrayUtils.getNamedInt(typedArray, (XmlPullParser)paramAttributeSet, "transitionVisibilityMode", 0, 0);
    typedArray.recycle();
    if (i != 0)
      setMode(i); 
  }
  
  private void captureValues(TransitionValues paramTransitionValues) {
    int i = paramTransitionValues.view.getVisibility();
    paramTransitionValues.values.put("android:visibility:visibility", Integer.valueOf(i));
    paramTransitionValues.values.put("android:visibility:parent", paramTransitionValues.view.getParent());
    int[] arrayOfInt = new int[2];
    paramTransitionValues.view.getLocationOnScreen(arrayOfInt);
    paramTransitionValues.values.put("android:visibility:screenLocation", arrayOfInt);
  }
  
  private VisibilityInfo getVisibilityChangeInfo(TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    VisibilityInfo visibilityInfo = new VisibilityInfo();
    visibilityInfo.mVisibilityChange = false;
    visibilityInfo.mFadeIn = false;
    if (paramTransitionValues1 != null && paramTransitionValues1.values.containsKey("android:visibility:visibility")) {
      visibilityInfo.mStartVisibility = ((Integer)paramTransitionValues1.values.get("android:visibility:visibility")).intValue();
      visibilityInfo.mStartParent = (ViewGroup)paramTransitionValues1.values.get("android:visibility:parent");
    } else {
      visibilityInfo.mStartVisibility = -1;
      visibilityInfo.mStartParent = null;
    } 
    if (paramTransitionValues2 != null && paramTransitionValues2.values.containsKey("android:visibility:visibility")) {
      visibilityInfo.mEndVisibility = ((Integer)paramTransitionValues2.values.get("android:visibility:visibility")).intValue();
      visibilityInfo.mEndParent = (ViewGroup)paramTransitionValues2.values.get("android:visibility:parent");
    } else {
      visibilityInfo.mEndVisibility = -1;
      visibilityInfo.mEndParent = null;
    } 
    if (paramTransitionValues1 != null && paramTransitionValues2 != null) {
      if (visibilityInfo.mStartVisibility == visibilityInfo.mEndVisibility && visibilityInfo.mStartParent == visibilityInfo.mEndParent)
        return visibilityInfo; 
      if (visibilityInfo.mStartVisibility != visibilityInfo.mEndVisibility) {
        if (visibilityInfo.mStartVisibility == 0) {
          visibilityInfo.mFadeIn = false;
          visibilityInfo.mVisibilityChange = true;
        } else if (visibilityInfo.mEndVisibility == 0) {
          visibilityInfo.mFadeIn = true;
          visibilityInfo.mVisibilityChange = true;
        } 
      } else if (visibilityInfo.mEndParent == null) {
        visibilityInfo.mFadeIn = false;
        visibilityInfo.mVisibilityChange = true;
      } else if (visibilityInfo.mStartParent == null) {
        visibilityInfo.mFadeIn = true;
        visibilityInfo.mVisibilityChange = true;
      } 
    } else if (paramTransitionValues1 == null && visibilityInfo.mEndVisibility == 0) {
      visibilityInfo.mFadeIn = true;
      visibilityInfo.mVisibilityChange = true;
    } else if (paramTransitionValues2 == null && visibilityInfo.mStartVisibility == 0) {
      visibilityInfo.mFadeIn = false;
      visibilityInfo.mVisibilityChange = true;
    } 
    return visibilityInfo;
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues) {
    captureValues(paramTransitionValues);
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues) {
    captureValues(paramTransitionValues);
  }
  
  @Nullable
  public Animator createAnimator(@NonNull ViewGroup paramViewGroup, @Nullable TransitionValues paramTransitionValues1, @Nullable TransitionValues paramTransitionValues2) {
    VisibilityInfo visibilityInfo = getVisibilityChangeInfo(paramTransitionValues1, paramTransitionValues2);
    return (visibilityInfo.mVisibilityChange && (visibilityInfo.mStartParent != null || visibilityInfo.mEndParent != null)) ? (visibilityInfo.mFadeIn ? onAppear(paramViewGroup, paramTransitionValues1, visibilityInfo.mStartVisibility, paramTransitionValues2, visibilityInfo.mEndVisibility) : onDisappear(paramViewGroup, paramTransitionValues1, visibilityInfo.mStartVisibility, paramTransitionValues2, visibilityInfo.mEndVisibility)) : null;
  }
  
  public int getMode() {
    return this.mMode;
  }
  
  @Nullable
  public String[] getTransitionProperties() {
    return sTransitionProperties;
  }
  
  public boolean isTransitionRequired(TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    boolean bool = false;
    if (paramTransitionValues1 == null && paramTransitionValues2 == null)
      return false; 
    if (paramTransitionValues1 != null && paramTransitionValues2 != null && paramTransitionValues2.values.containsKey("android:visibility:visibility") != paramTransitionValues1.values.containsKey("android:visibility:visibility"))
      return false; 
    VisibilityInfo visibilityInfo = getVisibilityChangeInfo(paramTransitionValues1, paramTransitionValues2);
    null = bool;
    if (visibilityInfo.mVisibilityChange) {
      if (visibilityInfo.mStartVisibility != 0) {
        null = bool;
        return (visibilityInfo.mEndVisibility == 0) ? true : null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  public boolean isVisible(TransitionValues paramTransitionValues) {
    boolean bool1 = false;
    if (paramTransitionValues == null)
      return false; 
    int i = ((Integer)paramTransitionValues.values.get("android:visibility:visibility")).intValue();
    View view = (View)paramTransitionValues.values.get("android:visibility:parent");
    boolean bool2 = bool1;
    if (i == 0) {
      bool2 = bool1;
      if (view != null)
        bool2 = true; 
    } 
    return bool2;
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2) {
    if ((this.mMode & 0x1) != 1 || paramTransitionValues2 == null)
      return null; 
    if (paramTransitionValues1 == null) {
      View view = (View)paramTransitionValues2.view.getParent();
      if ((getVisibilityChangeInfo(getMatchedTransitionValues(view, false), getTransitionValues(view, false))).mVisibilityChange)
        return null; 
    } 
    return onAppear(paramViewGroup, paramTransitionValues2.view, paramTransitionValues1, paramTransitionValues2);
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, View paramView, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    return null;
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mMode : I
    //   4: iconst_2
    //   5: iand
    //   6: iconst_2
    //   7: if_icmpeq -> 12
    //   10: aconst_null
    //   11: areturn
    //   12: aload_2
    //   13: ifnull -> 25
    //   16: aload_2
    //   17: getfield view : Landroid/view/View;
    //   20: astore #6
    //   22: goto -> 28
    //   25: aconst_null
    //   26: astore #6
    //   28: aload #4
    //   30: ifnull -> 43
    //   33: aload #4
    //   35: getfield view : Landroid/view/View;
    //   38: astore #7
    //   40: goto -> 46
    //   43: aconst_null
    //   44: astore #7
    //   46: aload #7
    //   48: ifnull -> 121
    //   51: aload #7
    //   53: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   56: ifnonnull -> 62
    //   59: goto -> 121
    //   62: iload #5
    //   64: iconst_4
    //   65: if_icmpne -> 71
    //   68: goto -> 78
    //   71: aload #6
    //   73: aload #7
    //   75: if_acmpne -> 92
    //   78: aconst_null
    //   79: astore #8
    //   81: aload #7
    //   83: astore #6
    //   85: aload #8
    //   87: astore #7
    //   89: goto -> 264
    //   92: aload_0
    //   93: getfield mCanRemoveViews : Z
    //   96: ifeq -> 102
    //   99: goto -> 145
    //   102: aload_1
    //   103: aload #6
    //   105: aload #6
    //   107: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   110: checkcast android/view/View
    //   113: invokestatic copyViewImage : (Landroid/view/ViewGroup;Landroid/view/View;Landroid/view/View;)Landroid/view/View;
    //   116: astore #7
    //   118: goto -> 126
    //   121: aload #7
    //   123: ifnull -> 132
    //   126: aconst_null
    //   127: astore #6
    //   129: goto -> 264
    //   132: aload #6
    //   134: ifnull -> 257
    //   137: aload #6
    //   139: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   142: ifnonnull -> 152
    //   145: aload #6
    //   147: astore #7
    //   149: goto -> 126
    //   152: aload #6
    //   154: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   157: instanceof android/view/View
    //   160: ifeq -> 257
    //   163: aload #6
    //   165: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   168: checkcast android/view/View
    //   171: astore #7
    //   173: aload_0
    //   174: aload_0
    //   175: aload #7
    //   177: iconst_1
    //   178: invokevirtual getTransitionValues : (Landroid/view/View;Z)Landroid/support/transition/TransitionValues;
    //   181: aload_0
    //   182: aload #7
    //   184: iconst_1
    //   185: invokevirtual getMatchedTransitionValues : (Landroid/view/View;Z)Landroid/support/transition/TransitionValues;
    //   188: invokespecial getVisibilityChangeInfo : (Landroid/support/transition/TransitionValues;Landroid/support/transition/TransitionValues;)Landroid/support/transition/Visibility$VisibilityInfo;
    //   191: getfield mVisibilityChange : Z
    //   194: ifne -> 210
    //   197: aload_1
    //   198: aload #6
    //   200: aload #7
    //   202: invokestatic copyViewImage : (Landroid/view/ViewGroup;Landroid/view/View;Landroid/view/View;)Landroid/view/View;
    //   205: astore #7
    //   207: goto -> 126
    //   210: aload #7
    //   212: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   215: ifnonnull -> 251
    //   218: aload #7
    //   220: invokevirtual getId : ()I
    //   223: istore_3
    //   224: iload_3
    //   225: iconst_m1
    //   226: if_icmpeq -> 251
    //   229: aload_1
    //   230: iload_3
    //   231: invokevirtual findViewById : (I)Landroid/view/View;
    //   234: ifnull -> 251
    //   237: aload_0
    //   238: getfield mCanRemoveViews : Z
    //   241: ifeq -> 251
    //   244: aload #6
    //   246: astore #7
    //   248: goto -> 126
    //   251: aconst_null
    //   252: astore #7
    //   254: goto -> 126
    //   257: aconst_null
    //   258: astore #7
    //   260: aload #7
    //   262: astore #6
    //   264: aload #7
    //   266: ifnull -> 406
    //   269: aload_2
    //   270: ifnull -> 406
    //   273: aload_2
    //   274: getfield values : Ljava/util/Map;
    //   277: ldc 'android:visibility:screenLocation'
    //   279: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   284: checkcast [I
    //   287: astore #6
    //   289: aload #6
    //   291: iconst_0
    //   292: iaload
    //   293: istore_3
    //   294: aload #6
    //   296: iconst_1
    //   297: iaload
    //   298: istore #5
    //   300: iconst_2
    //   301: newarray int
    //   303: astore #6
    //   305: aload_1
    //   306: aload #6
    //   308: invokevirtual getLocationOnScreen : ([I)V
    //   311: aload #7
    //   313: iload_3
    //   314: aload #6
    //   316: iconst_0
    //   317: iaload
    //   318: isub
    //   319: aload #7
    //   321: invokevirtual getLeft : ()I
    //   324: isub
    //   325: invokevirtual offsetLeftAndRight : (I)V
    //   328: aload #7
    //   330: iload #5
    //   332: aload #6
    //   334: iconst_1
    //   335: iaload
    //   336: isub
    //   337: aload #7
    //   339: invokevirtual getTop : ()I
    //   342: isub
    //   343: invokevirtual offsetTopAndBottom : (I)V
    //   346: aload_1
    //   347: invokestatic getOverlay : (Landroid/view/ViewGroup;)Landroid/support/transition/ViewGroupOverlayImpl;
    //   350: astore #6
    //   352: aload #6
    //   354: aload #7
    //   356: invokeinterface add : (Landroid/view/View;)V
    //   361: aload_0
    //   362: aload_1
    //   363: aload #7
    //   365: aload_2
    //   366: aload #4
    //   368: invokevirtual onDisappear : (Landroid/view/ViewGroup;Landroid/view/View;Landroid/support/transition/TransitionValues;Landroid/support/transition/TransitionValues;)Landroid/animation/Animator;
    //   371: astore_1
    //   372: aload_1
    //   373: ifnonnull -> 388
    //   376: aload #6
    //   378: aload #7
    //   380: invokeinterface remove : (Landroid/view/View;)V
    //   385: goto -> 404
    //   388: aload_1
    //   389: new android/support/transition/Visibility$1
    //   392: dup
    //   393: aload_0
    //   394: aload #6
    //   396: aload #7
    //   398: invokespecial <init> : (Landroid/support/transition/Visibility;Landroid/support/transition/ViewGroupOverlayImpl;Landroid/view/View;)V
    //   401: invokevirtual addListener : (Landroid/animation/Animator$AnimatorListener;)V
    //   404: aload_1
    //   405: areturn
    //   406: aload #6
    //   408: ifnull -> 478
    //   411: aload #6
    //   413: invokevirtual getVisibility : ()I
    //   416: istore_3
    //   417: aload #6
    //   419: iconst_0
    //   420: invokestatic setTransitionVisibility : (Landroid/view/View;I)V
    //   423: aload_0
    //   424: aload_1
    //   425: aload #6
    //   427: aload_2
    //   428: aload #4
    //   430: invokevirtual onDisappear : (Landroid/view/ViewGroup;Landroid/view/View;Landroid/support/transition/TransitionValues;Landroid/support/transition/TransitionValues;)Landroid/animation/Animator;
    //   433: astore_1
    //   434: aload_1
    //   435: ifnull -> 470
    //   438: new android/support/transition/Visibility$DisappearListener
    //   441: dup
    //   442: aload #6
    //   444: iload #5
    //   446: iconst_1
    //   447: invokespecial <init> : (Landroid/view/View;IZ)V
    //   450: astore_2
    //   451: aload_1
    //   452: aload_2
    //   453: invokevirtual addListener : (Landroid/animation/Animator$AnimatorListener;)V
    //   456: aload_1
    //   457: aload_2
    //   458: invokestatic addPauseListener : (Landroid/animation/Animator;Landroid/animation/AnimatorListenerAdapter;)V
    //   461: aload_0
    //   462: aload_2
    //   463: invokevirtual addListener : (Landroid/support/transition/Transition$TransitionListener;)Landroid/support/transition/Transition;
    //   466: pop
    //   467: goto -> 476
    //   470: aload #6
    //   472: iload_3
    //   473: invokestatic setTransitionVisibility : (Landroid/view/View;I)V
    //   476: aload_1
    //   477: areturn
    //   478: aconst_null
    //   479: areturn
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, View paramView, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    return null;
  }
  
  public void setMode(int paramInt) {
    if ((paramInt & 0xFFFFFFFC) != 0)
      throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed"); 
    this.mMode = paramInt;
  }
  
  private static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener, AnimatorUtils.AnimatorPauseListenerCompat {
    boolean mCanceled = false;
    
    private final int mFinalVisibility;
    
    private boolean mLayoutSuppressed;
    
    private final ViewGroup mParent;
    
    private final boolean mSuppressLayout;
    
    private final View mView;
    
    DisappearListener(View param1View, int param1Int, boolean param1Boolean) {
      this.mView = param1View;
      this.mFinalVisibility = param1Int;
      this.mParent = (ViewGroup)param1View.getParent();
      this.mSuppressLayout = param1Boolean;
      suppressLayout(true);
    }
    
    private void hideViewWhenNotCanceled() {
      if (!this.mCanceled) {
        ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
        if (this.mParent != null)
          this.mParent.invalidate(); 
      } 
      suppressLayout(false);
    }
    
    private void suppressLayout(boolean param1Boolean) {
      if (this.mSuppressLayout && this.mLayoutSuppressed != param1Boolean && this.mParent != null) {
        this.mLayoutSuppressed = param1Boolean;
        ViewGroupUtils.suppressLayout(this.mParent, param1Boolean);
      } 
    }
    
    public void onAnimationCancel(Animator param1Animator) {
      this.mCanceled = true;
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      hideViewWhenNotCanceled();
    }
    
    public void onAnimationPause(Animator param1Animator) {
      if (!this.mCanceled)
        ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility); 
    }
    
    public void onAnimationRepeat(Animator param1Animator) {}
    
    public void onAnimationResume(Animator param1Animator) {
      if (!this.mCanceled)
        ViewUtils.setTransitionVisibility(this.mView, 0); 
    }
    
    public void onAnimationStart(Animator param1Animator) {}
    
    public void onTransitionCancel(@NonNull Transition param1Transition) {}
    
    public void onTransitionEnd(@NonNull Transition param1Transition) {
      hideViewWhenNotCanceled();
      param1Transition.removeListener(this);
    }
    
    public void onTransitionPause(@NonNull Transition param1Transition) {
      suppressLayout(false);
    }
    
    public void onTransitionResume(@NonNull Transition param1Transition) {
      suppressLayout(true);
    }
    
    public void onTransitionStart(@NonNull Transition param1Transition) {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Mode {}
  
  private static class VisibilityInfo {
    ViewGroup mEndParent;
    
    int mEndVisibility;
    
    boolean mFadeIn;
    
    ViewGroup mStartParent;
    
    int mStartVisibility;
    
    boolean mVisibilityChange;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\Visibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */