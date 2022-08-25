package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.ArgbEvaluatorCompat;
import android.support.design.animation.ChildrenAlphaProperty;
import android.support.design.animation.DrawableAlphaProperty;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.MotionTiming;
import android.support.design.animation.Positioning;
import android.support.design.circularreveal.CircularRevealCompat;
import android.support.design.circularreveal.CircularRevealHelper;
import android.support.design.circularreveal.CircularRevealWidget;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.MathUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
  private final int[] tmpArray = new int[2];
  
  private final Rect tmpRect = new Rect();
  
  private final RectF tmpRectF1 = new RectF();
  
  private final RectF tmpRectF2 = new RectF();
  
  public FabTransformationBehavior() {}
  
  public FabTransformationBehavior(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  @Nullable
  private ViewGroup calculateChildContentContainer(View paramView) {
    View view = paramView.findViewById(R.id.mtrl_child_content_container);
    return (view != null) ? toViewGroupOrNull(view) : ((paramView instanceof TransformationChildLayout || paramView instanceof TransformationChildCard) ? toViewGroupOrNull(((ViewGroup)paramView).getChildAt(0)) : toViewGroupOrNull(paramView));
  }
  
  private void calculateChildVisibleBoundsAtEndOfExpansion(View paramView, FabTransformationSpec paramFabTransformationSpec, MotionTiming paramMotionTiming1, MotionTiming paramMotionTiming2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, RectF paramRectF) {
    paramFloat1 = calculateValueOfAnimationAtEndOfExpansion(paramFabTransformationSpec, paramMotionTiming1, paramFloat1, paramFloat3);
    paramFloat2 = calculateValueOfAnimationAtEndOfExpansion(paramFabTransformationSpec, paramMotionTiming2, paramFloat2, paramFloat4);
    Rect rect = this.tmpRect;
    paramView.getWindowVisibleDisplayFrame(rect);
    RectF rectF1 = this.tmpRectF1;
    rectF1.set(rect);
    RectF rectF2 = this.tmpRectF2;
    calculateWindowBounds(paramView, rectF2);
    rectF2.offset(paramFloat1, paramFloat2);
    rectF2.intersect(rectF1);
    paramRectF.set(rectF2);
  }
  
  private float calculateRevealCenterX(View paramView1, View paramView2, Positioning paramPositioning) {
    RectF rectF1 = this.tmpRectF1;
    RectF rectF2 = this.tmpRectF2;
    calculateWindowBounds(paramView1, rectF1);
    calculateWindowBounds(paramView2, rectF2);
    rectF2.offset(-calculateTranslationX(paramView1, paramView2, paramPositioning), 0.0F);
    return rectF1.centerX() - rectF2.left;
  }
  
  private float calculateRevealCenterY(View paramView1, View paramView2, Positioning paramPositioning) {
    RectF rectF1 = this.tmpRectF1;
    RectF rectF2 = this.tmpRectF2;
    calculateWindowBounds(paramView1, rectF1);
    calculateWindowBounds(paramView2, rectF2);
    rectF2.offset(0.0F, -calculateTranslationY(paramView1, paramView2, paramPositioning));
    return rectF1.centerY() - rectF2.top;
  }
  
  private float calculateTranslationX(View paramView1, View paramView2, Positioning paramPositioning) {
    float f;
    RectF rectF1 = this.tmpRectF1;
    RectF rectF2 = this.tmpRectF2;
    calculateWindowBounds(paramView1, rectF1);
    calculateWindowBounds(paramView2, rectF2);
    int i = paramPositioning.gravity & 0x7;
    if (i != 1) {
      if (i != 3) {
        if (i != 5) {
          f = 0.0F;
        } else {
          f = rectF2.right - rectF1.right;
        } 
      } else {
        f = rectF2.left - rectF1.left;
      } 
    } else {
      f = rectF2.centerX() - rectF1.centerX();
    } 
    return f + paramPositioning.xAdjustment;
  }
  
  private float calculateTranslationY(View paramView1, View paramView2, Positioning paramPositioning) {
    float f;
    RectF rectF1 = this.tmpRectF1;
    RectF rectF2 = this.tmpRectF2;
    calculateWindowBounds(paramView1, rectF1);
    calculateWindowBounds(paramView2, rectF2);
    int i = paramPositioning.gravity & 0x70;
    if (i != 16) {
      if (i != 48) {
        if (i != 80) {
          f = 0.0F;
        } else {
          f = rectF2.bottom - rectF1.bottom;
        } 
      } else {
        f = rectF2.top - rectF1.top;
      } 
    } else {
      f = rectF2.centerY() - rectF1.centerY();
    } 
    return f + paramPositioning.yAdjustment;
  }
  
  private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec paramFabTransformationSpec, MotionTiming paramMotionTiming, float paramFloat1, float paramFloat2) {
    long l1 = paramMotionTiming.getDelay();
    long l2 = paramMotionTiming.getDuration();
    MotionTiming motionTiming = paramFabTransformationSpec.timings.getTiming("expansion");
    float f = (float)(motionTiming.getDelay() + motionTiming.getDuration() + 17L - l1) / (float)l2;
    return AnimationUtils.lerp(paramFloat1, paramFloat2, paramMotionTiming.getInterpolator().getInterpolation(f));
  }
  
  private void calculateWindowBounds(View paramView, RectF paramRectF) {
    paramRectF.set(0.0F, 0.0F, paramView.getWidth(), paramView.getHeight());
    int[] arrayOfInt = this.tmpArray;
    paramView.getLocationInWindow(arrayOfInt);
    paramRectF.offsetTo(arrayOfInt[0], arrayOfInt[1]);
    paramRectF.offset((int)-paramView.getTranslationX(), (int)-paramView.getTranslationY());
  }
  
  private void createChildrenFadeAnimation(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, FabTransformationSpec paramFabTransformationSpec, List<Animator> paramList, List<Animator.AnimatorListener> paramList1) {
    ObjectAnimator objectAnimator;
    if (!(paramView2 instanceof ViewGroup))
      return; 
    if (paramView2 instanceof CircularRevealWidget && CircularRevealHelper.STRATEGY == 0)
      return; 
    ViewGroup viewGroup = calculateChildContentContainer(paramView2);
    if (viewGroup == null)
      return; 
    if (paramBoolean1) {
      if (!paramBoolean2)
        ChildrenAlphaProperty.CHILDREN_ALPHA.set(viewGroup, Float.valueOf(0.0F)); 
      objectAnimator = ObjectAnimator.ofFloat(viewGroup, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[] { 1.0F });
    } else {
      objectAnimator = ObjectAnimator.ofFloat(objectAnimator, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[] { 0.0F });
    } 
    paramFabTransformationSpec.timings.getTiming("contentFade").apply((Animator)objectAnimator);
    paramList.add(objectAnimator);
  }
  
  private void createColorAnimation(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, FabTransformationSpec paramFabTransformationSpec, List<Animator> paramList, List<Animator.AnimatorListener> paramList1) {
    ObjectAnimator objectAnimator;
    if (!(paramView2 instanceof CircularRevealWidget))
      return; 
    CircularRevealWidget circularRevealWidget = (CircularRevealWidget)paramView2;
    int i = getBackgroundTint(paramView1);
    if (paramBoolean1) {
      if (!paramBoolean2)
        circularRevealWidget.setCircularRevealScrimColor(i); 
      objectAnimator = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[] { 0xFFFFFF & i });
    } else {
      objectAnimator = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[] { i });
    } 
    objectAnimator.setEvaluator((TypeEvaluator)ArgbEvaluatorCompat.getInstance());
    paramFabTransformationSpec.timings.getTiming("color").apply((Animator)objectAnimator);
    paramList.add(objectAnimator);
  }
  
  @TargetApi(21)
  private void createElevationAnimation(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, FabTransformationSpec paramFabTransformationSpec, List<Animator> paramList, List<Animator.AnimatorListener> paramList1) {
    ObjectAnimator objectAnimator;
    float f = ViewCompat.getElevation(paramView2) - ViewCompat.getElevation(paramView1);
    if (paramBoolean1) {
      if (!paramBoolean2)
        paramView2.setTranslationZ(-f); 
      objectAnimator = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_Z, new float[] { 0.0F });
    } else {
      objectAnimator = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_Z, new float[] { -f });
    } 
    paramFabTransformationSpec.timings.getTiming("elevation").apply((Animator)objectAnimator);
    paramList.add(objectAnimator);
  }
  
  private void createExpansionAnimation(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, FabTransformationSpec paramFabTransformationSpec, float paramFloat1, float paramFloat2, List<Animator> paramList, List<Animator.AnimatorListener> paramList1) {
    Animator animator;
    if (!(paramView2 instanceof CircularRevealWidget))
      return; 
    final CircularRevealWidget circularRevealChild = (CircularRevealWidget)paramView2;
    float f1 = calculateRevealCenterX(paramView1, paramView2, paramFabTransformationSpec.positioning);
    float f2 = calculateRevealCenterY(paramView1, paramView2, paramFabTransformationSpec.positioning);
    ((FloatingActionButton)paramView1).getContentRect(this.tmpRect);
    float f3 = this.tmpRect.width() / 2.0F;
    MotionTiming motionTiming = paramFabTransformationSpec.timings.getTiming("expansion");
    if (paramBoolean1) {
      if (!paramBoolean2)
        circularRevealWidget.setRevealInfo(new CircularRevealWidget.RevealInfo(f1, f2, f3)); 
      if (paramBoolean2)
        f3 = (circularRevealWidget.getRevealInfo()).radius; 
      animator = CircularRevealCompat.createCircularReveal(circularRevealWidget, f1, f2, MathUtils.distanceToFurthestCorner(f1, f2, 0.0F, 0.0F, paramFloat1, paramFloat2));
      animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator param1Animator) {
              CircularRevealWidget.RevealInfo revealInfo = circularRevealChild.getRevealInfo();
              revealInfo.radius = Float.MAX_VALUE;
              circularRevealChild.setRevealInfo(revealInfo);
            }
          });
      createPreFillRadialExpansion(paramView2, motionTiming.getDelay(), (int)f1, (int)f2, f3, paramList);
    } else {
      MotionTiming motionTiming1 = motionTiming;
      paramFloat1 = (circularRevealWidget.getRevealInfo()).radius;
      animator = CircularRevealCompat.createCircularReveal(circularRevealWidget, f1, f2, f3);
      long l = motionTiming1.getDelay();
      int i = (int)f1;
      int j = (int)f2;
      createPreFillRadialExpansion(paramView2, l, i, j, paramFloat1, paramList);
      createPostFillRadialExpansion(paramView2, motionTiming1.getDelay(), motionTiming1.getDuration(), paramFabTransformationSpec.timings.getTotalDuration(), i, j, f3, paramList);
    } 
    motionTiming.apply(animator);
    paramList.add(animator);
    paramList1.add(CircularRevealCompat.createCircularRevealListener(circularRevealWidget));
  }
  
  private void createIconFadeAnimation(View paramView1, final View child, boolean paramBoolean1, boolean paramBoolean2, FabTransformationSpec paramFabTransformationSpec, List<Animator> paramList, List<Animator.AnimatorListener> paramList1) {
    ObjectAnimator objectAnimator;
    if (!(child instanceof CircularRevealWidget) || !(paramView1 instanceof ImageView))
      return; 
    final CircularRevealWidget circularRevealChild = (CircularRevealWidget)child;
    final Drawable icon = ((ImageView)paramView1).getDrawable();
    if (drawable == null)
      return; 
    drawable.mutate();
    if (paramBoolean1) {
      if (!paramBoolean2)
        drawable.setAlpha(255); 
      objectAnimator = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[] { 0 });
    } else {
      objectAnimator = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[] { 255 });
    } 
    objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            child.invalidate();
          }
        });
    paramFabTransformationSpec.timings.getTiming("iconFade").apply((Animator)objectAnimator);
    paramList.add(objectAnimator);
    paramList1.add(new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            circularRevealChild.setCircularRevealOverlayDrawable(null);
          }
          
          public void onAnimationStart(Animator param1Animator) {
            circularRevealChild.setCircularRevealOverlayDrawable(icon);
          }
        });
  }
  
  private void createPostFillRadialExpansion(View paramView, long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, float paramFloat, List<Animator> paramList) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramLong1 += paramLong2;
      if (paramLong1 < paramLong3) {
        Animator animator = ViewAnimationUtils.createCircularReveal(paramView, paramInt1, paramInt2, paramFloat, paramFloat);
        animator.setStartDelay(paramLong1);
        animator.setDuration(paramLong3 - paramLong1);
        paramList.add(animator);
      } 
    } 
  }
  
  private void createPreFillRadialExpansion(View paramView, long paramLong, int paramInt1, int paramInt2, float paramFloat, List<Animator> paramList) {
    if (Build.VERSION.SDK_INT >= 21 && paramLong > 0L) {
      Animator animator = ViewAnimationUtils.createCircularReveal(paramView, paramInt1, paramInt2, paramFloat, paramFloat);
      animator.setStartDelay(0L);
      animator.setDuration(paramLong);
      paramList.add(animator);
    } 
  }
  
  private void createTranslationAnimation(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, FabTransformationSpec paramFabTransformationSpec, List<Animator> paramList, List<Animator.AnimatorListener> paramList1, RectF paramRectF) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: aload #5
    //   5: getfield positioning : Landroid/support/design/animation/Positioning;
    //   8: invokespecial calculateTranslationX : (Landroid/view/View;Landroid/view/View;Landroid/support/design/animation/Positioning;)F
    //   11: fstore #9
    //   13: aload_0
    //   14: aload_1
    //   15: aload_2
    //   16: aload #5
    //   18: getfield positioning : Landroid/support/design/animation/Positioning;
    //   21: invokespecial calculateTranslationY : (Landroid/view/View;Landroid/view/View;Landroid/support/design/animation/Positioning;)F
    //   24: fstore #10
    //   26: fload #9
    //   28: fconst_0
    //   29: fcmpl
    //   30: ifeq -> 123
    //   33: fload #10
    //   35: fconst_0
    //   36: fcmpl
    //   37: istore #11
    //   39: iload #11
    //   41: ifne -> 47
    //   44: goto -> 123
    //   47: iload_3
    //   48: ifeq -> 58
    //   51: fload #10
    //   53: fconst_0
    //   54: fcmpg
    //   55: iflt -> 67
    //   58: iload_3
    //   59: ifne -> 95
    //   62: iload #11
    //   64: ifle -> 95
    //   67: aload #5
    //   69: getfield timings : Landroid/support/design/animation/MotionSpec;
    //   72: ldc_w 'translationXCurveUpwards'
    //   75: invokevirtual getTiming : (Ljava/lang/String;)Landroid/support/design/animation/MotionTiming;
    //   78: astore_1
    //   79: aload #5
    //   81: getfield timings : Landroid/support/design/animation/MotionSpec;
    //   84: ldc_w 'translationYCurveUpwards'
    //   87: invokevirtual getTiming : (Ljava/lang/String;)Landroid/support/design/animation/MotionTiming;
    //   90: astore #7
    //   92: goto -> 148
    //   95: aload #5
    //   97: getfield timings : Landroid/support/design/animation/MotionSpec;
    //   100: ldc_w 'translationXCurveDownwards'
    //   103: invokevirtual getTiming : (Ljava/lang/String;)Landroid/support/design/animation/MotionTiming;
    //   106: astore_1
    //   107: aload #5
    //   109: getfield timings : Landroid/support/design/animation/MotionSpec;
    //   112: ldc_w 'translationYCurveDownwards'
    //   115: invokevirtual getTiming : (Ljava/lang/String;)Landroid/support/design/animation/MotionTiming;
    //   118: astore #7
    //   120: goto -> 148
    //   123: aload #5
    //   125: getfield timings : Landroid/support/design/animation/MotionSpec;
    //   128: ldc_w 'translationXLinear'
    //   131: invokevirtual getTiming : (Ljava/lang/String;)Landroid/support/design/animation/MotionTiming;
    //   134: astore_1
    //   135: aload #5
    //   137: getfield timings : Landroid/support/design/animation/MotionSpec;
    //   140: ldc_w 'translationYLinear'
    //   143: invokevirtual getTiming : (Ljava/lang/String;)Landroid/support/design/animation/MotionTiming;
    //   146: astore #7
    //   148: iload_3
    //   149: ifeq -> 233
    //   152: iload #4
    //   154: ifne -> 171
    //   157: aload_2
    //   158: fload #9
    //   160: fneg
    //   161: invokevirtual setTranslationX : (F)V
    //   164: aload_2
    //   165: fload #10
    //   167: fneg
    //   168: invokevirtual setTranslationY : (F)V
    //   171: aload_2
    //   172: getstatic android/view/View.TRANSLATION_X : Landroid/util/Property;
    //   175: iconst_1
    //   176: newarray float
    //   178: dup
    //   179: iconst_0
    //   180: fconst_0
    //   181: fastore
    //   182: invokestatic ofFloat : (Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;
    //   185: astore #12
    //   187: aload_2
    //   188: getstatic android/view/View.TRANSLATION_Y : Landroid/util/Property;
    //   191: iconst_1
    //   192: newarray float
    //   194: dup
    //   195: iconst_0
    //   196: fconst_0
    //   197: fastore
    //   198: invokestatic ofFloat : (Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;
    //   201: astore #13
    //   203: aload_0
    //   204: aload_2
    //   205: aload #5
    //   207: aload_1
    //   208: aload #7
    //   210: fload #9
    //   212: fneg
    //   213: fload #10
    //   215: fneg
    //   216: fconst_0
    //   217: fconst_0
    //   218: aload #8
    //   220: invokespecial calculateChildVisibleBoundsAtEndOfExpansion : (Landroid/view/View;Landroid/support/design/transformation/FabTransformationBehavior$FabTransformationSpec;Landroid/support/design/animation/MotionTiming;Landroid/support/design/animation/MotionTiming;FFFFLandroid/graphics/RectF;)V
    //   223: aload #12
    //   225: astore_2
    //   226: aload #13
    //   228: astore #5
    //   230: goto -> 272
    //   233: aload_2
    //   234: getstatic android/view/View.TRANSLATION_X : Landroid/util/Property;
    //   237: iconst_1
    //   238: newarray float
    //   240: dup
    //   241: iconst_0
    //   242: fload #9
    //   244: fneg
    //   245: fastore
    //   246: invokestatic ofFloat : (Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;
    //   249: astore #8
    //   251: aload_2
    //   252: getstatic android/view/View.TRANSLATION_Y : Landroid/util/Property;
    //   255: iconst_1
    //   256: newarray float
    //   258: dup
    //   259: iconst_0
    //   260: fload #10
    //   262: fneg
    //   263: fastore
    //   264: invokestatic ofFloat : (Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;
    //   267: astore #5
    //   269: aload #8
    //   271: astore_2
    //   272: aload_1
    //   273: aload_2
    //   274: invokevirtual apply : (Landroid/animation/Animator;)V
    //   277: aload #7
    //   279: aload #5
    //   281: invokevirtual apply : (Landroid/animation/Animator;)V
    //   284: aload #6
    //   286: aload_2
    //   287: invokeinterface add : (Ljava/lang/Object;)Z
    //   292: pop
    //   293: aload #6
    //   295: aload #5
    //   297: invokeinterface add : (Ljava/lang/Object;)Z
    //   302: pop
    //   303: return
  }
  
  private int getBackgroundTint(View paramView) {
    ColorStateList colorStateList = ViewCompat.getBackgroundTintList(paramView);
    return (colorStateList != null) ? colorStateList.getColorForState(paramView.getDrawableState(), colorStateList.getDefaultColor()) : 0;
  }
  
  @Nullable
  private ViewGroup toViewGroupOrNull(View paramView) {
    return (paramView instanceof ViewGroup) ? (ViewGroup)paramView : null;
  }
  
  @CallSuper
  public boolean layoutDependsOn(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2) {
    if (paramView1.getVisibility() == 8)
      throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead."); 
    boolean bool = paramView2 instanceof FloatingActionButton;
    boolean bool1 = false;
    if (bool) {
      int i = ((FloatingActionButton)paramView2).getExpandedComponentIdHint();
      if (i == 0 || i == paramView1.getId())
        bool1 = true; 
      return bool1;
    } 
    return false;
  }
  
  @CallSuper
  public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams paramLayoutParams) {
    if (paramLayoutParams.dodgeInsetEdges == 0)
      paramLayoutParams.dodgeInsetEdges = 80; 
  }
  
  @NonNull
  protected AnimatorSet onCreateExpandedStateChangeAnimation(final View dependency, final View child, final boolean expanded, boolean paramBoolean2) {
    FabTransformationSpec fabTransformationSpec = onCreateMotionSpec(child.getContext(), expanded);
    ArrayList<Animator> arrayList = new ArrayList();
    ArrayList<Animator.AnimatorListener> arrayList1 = new ArrayList();
    if (Build.VERSION.SDK_INT >= 21)
      createElevationAnimation(dependency, child, expanded, paramBoolean2, fabTransformationSpec, arrayList, arrayList1); 
    RectF rectF = this.tmpRectF1;
    createTranslationAnimation(dependency, child, expanded, paramBoolean2, fabTransformationSpec, arrayList, arrayList1, rectF);
    float f1 = rectF.width();
    float f2 = rectF.height();
    createIconFadeAnimation(dependency, child, expanded, paramBoolean2, fabTransformationSpec, arrayList, arrayList1);
    createExpansionAnimation(dependency, child, expanded, paramBoolean2, fabTransformationSpec, f1, f2, arrayList, arrayList1);
    createColorAnimation(dependency, child, expanded, paramBoolean2, fabTransformationSpec, arrayList, arrayList1);
    createChildrenFadeAnimation(dependency, child, expanded, paramBoolean2, fabTransformationSpec, arrayList, arrayList1);
    AnimatorSet animatorSet = new AnimatorSet();
    AnimatorSetCompat.playTogether(animatorSet, arrayList);
    animatorSet.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            if (!expanded) {
              child.setVisibility(4);
              dependency.setAlpha(1.0F);
              dependency.setVisibility(0);
            } 
          }
          
          public void onAnimationStart(Animator param1Animator) {
            if (expanded) {
              child.setVisibility(0);
              dependency.setAlpha(0.0F);
              dependency.setVisibility(4);
            } 
          }
        });
    byte b = 0;
    int i = arrayList1.size();
    while (b < i) {
      animatorSet.addListener(arrayList1.get(b));
      b++;
    } 
    return animatorSet;
  }
  
  protected abstract FabTransformationSpec onCreateMotionSpec(Context paramContext, boolean paramBoolean);
  
  protected static class FabTransformationSpec {
    public Positioning positioning;
    
    public MotionSpec timings;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\transformation\FabTransformationBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */