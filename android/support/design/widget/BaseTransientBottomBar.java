package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
  static final int ANIMATION_DURATION = 250;
  
  static final int ANIMATION_FADE_DURATION = 180;
  
  public static final int LENGTH_INDEFINITE = -2;
  
  public static final int LENGTH_LONG = 0;
  
  public static final int LENGTH_SHORT = -1;
  
  static final int MSG_DISMISS = 1;
  
  static final int MSG_SHOW = 0;
  
  private static final int[] SNACKBAR_STYLE_ATTR;
  
  private static final boolean USE_OFFSET_API;
  
  static final Handler handler;
  
  private final AccessibilityManager accessibilityManager;
  
  private Behavior behavior;
  
  private List<BaseCallback<B>> callbacks;
  
  private final android.support.design.snackbar.ContentViewCallback contentViewCallback;
  
  private final Context context;
  
  private int duration;
  
  final SnackbarManager.Callback managerCallback = new SnackbarManager.Callback() {
      public void dismiss(int param1Int) {
        BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(1, param1Int, 0, BaseTransientBottomBar.this));
      }
      
      public void show() {
        BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(0, BaseTransientBottomBar.this));
      }
    };
  
  private final ViewGroup targetParent;
  
  protected final SnackbarBaseLayout view;
  
  static {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 19) {
      bool = true;
    } else {
      bool = false;
    } 
    USE_OFFSET_API = bool;
    SNACKBAR_STYLE_ATTR = new int[] { R.attr.snackbarStyle };
    handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
          public boolean handleMessage(Message param1Message) {
            switch (param1Message.what) {
              default:
                return false;
              case 1:
                ((BaseTransientBottomBar)param1Message.obj).hideView(param1Message.arg1);
                return true;
              case 0:
                break;
            } 
            ((BaseTransientBottomBar)param1Message.obj).showView();
            return true;
          }
        });
  }
  
  protected BaseTransientBottomBar(@NonNull ViewGroup paramViewGroup, @NonNull View paramView, @NonNull android.support.design.snackbar.ContentViewCallback paramContentViewCallback) {
    if (paramViewGroup == null)
      throw new IllegalArgumentException("Transient bottom bar must have non-null parent"); 
    if (paramView == null)
      throw new IllegalArgumentException("Transient bottom bar must have non-null content"); 
    if (paramContentViewCallback == null)
      throw new IllegalArgumentException("Transient bottom bar must have non-null callback"); 
    this.targetParent = paramViewGroup;
    this.contentViewCallback = paramContentViewCallback;
    this.context = paramViewGroup.getContext();
    ThemeEnforcement.checkAppCompatTheme(this.context);
    this.view = (SnackbarBaseLayout)LayoutInflater.from(this.context).inflate(getSnackbarBaseLayoutResId(), this.targetParent, false);
    this.view.addView(paramView);
    ViewCompat.setAccessibilityLiveRegion((View)this.view, 1);
    ViewCompat.setImportantForAccessibility((View)this.view, 1);
    ViewCompat.setFitsSystemWindows((View)this.view, true);
    ViewCompat.setOnApplyWindowInsetsListener((View)this.view, new OnApplyWindowInsetsListener() {
          public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
            param1View.setPadding(param1View.getPaddingLeft(), param1View.getPaddingTop(), param1View.getPaddingRight(), param1WindowInsetsCompat.getSystemWindowInsetBottom());
            return param1WindowInsetsCompat;
          }
        });
    ViewCompat.setAccessibilityDelegate((View)this.view, new AccessibilityDelegateCompat() {
          public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
            param1AccessibilityNodeInfoCompat.addAction(1048576);
            param1AccessibilityNodeInfoCompat.setDismissable(true);
          }
          
          public boolean performAccessibilityAction(View param1View, int param1Int, Bundle param1Bundle) {
            if (param1Int == 1048576) {
              BaseTransientBottomBar.this.dismiss();
              return true;
            } 
            return super.performAccessibilityAction(param1View, param1Int, param1Bundle);
          }
        });
    this.accessibilityManager = (AccessibilityManager)this.context.getSystemService("accessibility");
  }
  
  private void animateViewOut(final int event) {
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.setIntValues(new int[] { 0, getTranslationYBottom() });
    valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    valueAnimator.setDuration(250L);
    valueAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            BaseTransientBottomBar.this.onViewHidden(event);
          }
          
          public void onAnimationStart(Animator param1Animator) {
            BaseTransientBottomBar.this.contentViewCallback.animateContentOut(0, 180);
          }
        });
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          private int previousAnimatedIntValue = 0;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            int i = ((Integer)param1ValueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.USE_OFFSET_API) {
              ViewCompat.offsetTopAndBottom((View)BaseTransientBottomBar.this.view, i - this.previousAnimatedIntValue);
            } else {
              BaseTransientBottomBar.this.view.setTranslationY(i);
            } 
            this.previousAnimatedIntValue = i;
          }
        });
    valueAnimator.start();
  }
  
  private int getTranslationYBottom() {
    int i = this.view.getHeight();
    ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
    int j = i;
    if (layoutParams instanceof ViewGroup.MarginLayoutParams)
      j = i + ((ViewGroup.MarginLayoutParams)layoutParams).bottomMargin; 
    return j;
  }
  
  @NonNull
  public B addCallback(@NonNull BaseCallback<B> paramBaseCallback) {
    if (paramBaseCallback == null)
      return (B)this; 
    if (this.callbacks == null)
      this.callbacks = new ArrayList<>(); 
    this.callbacks.add(paramBaseCallback);
    return (B)this;
  }
  
  void animateViewIn() {
    final int translationYBottom = getTranslationYBottom();
    if (USE_OFFSET_API) {
      ViewCompat.offsetTopAndBottom((View)this.view, i);
    } else {
      this.view.setTranslationY(i);
    } 
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.setIntValues(new int[] { i, 0 });
    valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    valueAnimator.setDuration(250L);
    valueAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            BaseTransientBottomBar.this.onViewShown();
          }
          
          public void onAnimationStart(Animator param1Animator) {
            BaseTransientBottomBar.this.contentViewCallback.animateContentIn(70, 180);
          }
        });
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          private int previousAnimatedIntValue = translationYBottom;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            int i = ((Integer)param1ValueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.USE_OFFSET_API) {
              ViewCompat.offsetTopAndBottom((View)BaseTransientBottomBar.this.view, i - this.previousAnimatedIntValue);
            } else {
              BaseTransientBottomBar.this.view.setTranslationY(i);
            } 
            this.previousAnimatedIntValue = i;
          }
        });
    valueAnimator.start();
  }
  
  public void dismiss() {
    dispatchDismiss(3);
  }
  
  protected void dispatchDismiss(int paramInt) {
    SnackbarManager.getInstance().dismiss(this.managerCallback, paramInt);
  }
  
  public Behavior getBehavior() {
    return this.behavior;
  }
  
  @NonNull
  public Context getContext() {
    return this.context;
  }
  
  public int getDuration() {
    return this.duration;
  }
  
  protected SwipeDismissBehavior<? extends View> getNewBehavior() {
    return new Behavior();
  }
  
  @LayoutRes
  protected int getSnackbarBaseLayoutResId() {
    int i;
    if (hasSnackbarStyleAttr()) {
      i = R.layout.mtrl_layout_snackbar;
    } else {
      i = R.layout.design_layout_snackbar;
    } 
    return i;
  }
  
  @NonNull
  public View getView() {
    return (View)this.view;
  }
  
  protected boolean hasSnackbarStyleAttr() {
    TypedArray typedArray = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
    boolean bool = false;
    int i = typedArray.getResourceId(0, -1);
    typedArray.recycle();
    if (i != -1)
      bool = true; 
    return bool;
  }
  
  final void hideView(int paramInt) {
    if (shouldAnimate() && this.view.getVisibility() == 0) {
      animateViewOut(paramInt);
    } else {
      onViewHidden(paramInt);
    } 
  }
  
  public boolean isShown() {
    return SnackbarManager.getInstance().isCurrent(this.managerCallback);
  }
  
  public boolean isShownOrQueued() {
    return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
  }
  
  void onViewHidden(int paramInt) {
    SnackbarManager.getInstance().onDismissed(this.managerCallback);
    if (this.callbacks != null)
      for (int i = this.callbacks.size() - 1; i >= 0; i--)
        ((BaseCallback<BaseTransientBottomBar>)this.callbacks.get(i)).onDismissed(this, paramInt);  
    ViewParent viewParent = this.view.getParent();
    if (viewParent instanceof ViewGroup)
      ((ViewGroup)viewParent).removeView((View)this.view); 
  }
  
  void onViewShown() {
    SnackbarManager.getInstance().onShown(this.managerCallback);
    if (this.callbacks != null)
      for (int i = this.callbacks.size() - 1; i >= 0; i--)
        ((BaseCallback<BaseTransientBottomBar>)this.callbacks.get(i)).onShown(this);  
  }
  
  @NonNull
  public B removeCallback(@NonNull BaseCallback<B> paramBaseCallback) {
    if (paramBaseCallback == null)
      return (B)this; 
    if (this.callbacks == null)
      return (B)this; 
    this.callbacks.remove(paramBaseCallback);
    return (B)this;
  }
  
  public B setBehavior(Behavior paramBehavior) {
    this.behavior = paramBehavior;
    return (B)this;
  }
  
  @NonNull
  public B setDuration(int paramInt) {
    this.duration = paramInt;
    return (B)this;
  }
  
  boolean shouldAnimate() {
    AccessibilityManager accessibilityManager = this.accessibilityManager;
    boolean bool = true;
    List list = accessibilityManager.getEnabledAccessibilityServiceList(1);
    if (list == null || !list.isEmpty())
      bool = false; 
    return bool;
  }
  
  public void show() {
    SnackbarManager.getInstance().show(getDuration(), this.managerCallback);
  }
  
  final void showView() {
    if (this.view.getParent() == null) {
      ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
      if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
        SwipeDismissBehavior<? extends View> swipeDismissBehavior;
        CoordinatorLayout.LayoutParams layoutParams1 = (CoordinatorLayout.LayoutParams)layoutParams;
        if (this.behavior == null) {
          swipeDismissBehavior = getNewBehavior();
        } else {
          swipeDismissBehavior = this.behavior;
        } 
        if (swipeDismissBehavior instanceof Behavior)
          ((Behavior)swipeDismissBehavior).setBaseTransientBottomBar(this); 
        swipeDismissBehavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
              public void onDismiss(View param1View) {
                param1View.setVisibility(8);
                BaseTransientBottomBar.this.dispatchDismiss(0);
              }
              
              public void onDragStateChanged(int param1Int) {
                switch (param1Int) {
                  default:
                    return;
                  case 1:
                  case 2:
                    SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.managerCallback);
                  case 0:
                    break;
                } 
                SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.managerCallback);
              }
            });
        layoutParams1.setBehavior(swipeDismissBehavior);
        layoutParams1.insetEdge = 80;
      } 
      this.targetParent.addView((View)this.view);
    } 
    this.view.setOnAttachStateChangeListener(new OnAttachStateChangeListener() {
          public void onViewAttachedToWindow(View param1View) {}
          
          public void onViewDetachedFromWindow(View param1View) {
            if (BaseTransientBottomBar.this.isShownOrQueued())
              BaseTransientBottomBar.handler.post(new Runnable() {
                    public void run() {
                      BaseTransientBottomBar.this.onViewHidden(3);
                    }
                  }); 
          }
        });
    if (ViewCompat.isLaidOut((View)this.view)) {
      if (shouldAnimate()) {
        animateViewIn();
      } else {
        onViewShown();
      } 
    } else {
      this.view.setOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
              BaseTransientBottomBar.this.view.setOnLayoutChangeListener((BaseTransientBottomBar.OnLayoutChangeListener)null);
              if (BaseTransientBottomBar.this.shouldAnimate()) {
                BaseTransientBottomBar.this.animateViewIn();
              } else {
                BaseTransientBottomBar.this.onViewShown();
              } 
            }
          });
    } 
  }
  
  public static abstract class BaseCallback<B> {
    public static final int DISMISS_EVENT_ACTION = 1;
    
    public static final int DISMISS_EVENT_CONSECUTIVE = 4;
    
    public static final int DISMISS_EVENT_MANUAL = 3;
    
    public static final int DISMISS_EVENT_SWIPE = 0;
    
    public static final int DISMISS_EVENT_TIMEOUT = 2;
    
    public void onDismissed(B param1B, int param1Int) {}
    
    public void onShown(B param1B) {}
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface DismissEvent {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DismissEvent {}
  
  public static class Behavior extends SwipeDismissBehavior<View> {
    private final BaseTransientBottomBar.BehaviorDelegate delegate = new BaseTransientBottomBar.BehaviorDelegate(this);
    
    private void setBaseTransientBottomBar(BaseTransientBottomBar<?> param1BaseTransientBottomBar) {
      this.delegate.setBaseTransientBottomBar(param1BaseTransientBottomBar);
    }
    
    public boolean canSwipeDismissView(View param1View) {
      return this.delegate.canSwipeDismissView(param1View);
    }
    
    public boolean onInterceptTouchEvent(CoordinatorLayout param1CoordinatorLayout, View param1View, MotionEvent param1MotionEvent) {
      this.delegate.onInterceptTouchEvent(param1CoordinatorLayout, param1View, param1MotionEvent);
      return super.onInterceptTouchEvent(param1CoordinatorLayout, param1View, param1MotionEvent);
    }
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static class BehaviorDelegate {
    private SnackbarManager.Callback managerCallback;
    
    public BehaviorDelegate(SwipeDismissBehavior<?> param1SwipeDismissBehavior) {
      param1SwipeDismissBehavior.setStartAlphaSwipeDistance(0.1F);
      param1SwipeDismissBehavior.setEndAlphaSwipeDistance(0.6F);
      param1SwipeDismissBehavior.setSwipeDirection(0);
    }
    
    public boolean canSwipeDismissView(View param1View) {
      return param1View instanceof BaseTransientBottomBar.SnackbarBaseLayout;
    }
    
    public void onInterceptTouchEvent(CoordinatorLayout param1CoordinatorLayout, View param1View, MotionEvent param1MotionEvent) {
      int i = param1MotionEvent.getActionMasked();
      if (i != 3)
        switch (i) {
          default:
            return;
          case 0:
            if (param1CoordinatorLayout.isPointInChildBounds(param1View, (int)param1MotionEvent.getX(), (int)param1MotionEvent.getY()))
              SnackbarManager.getInstance().pauseTimeout(this.managerCallback); 
          case 1:
            break;
        }  
      SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
    }
    
    public void setBaseTransientBottomBar(BaseTransientBottomBar<?> param1BaseTransientBottomBar) {
      this.managerCallback = param1BaseTransientBottomBar.managerCallback;
    }
  }
  
  @Deprecated
  public static interface ContentViewCallback extends android.support.design.snackbar.ContentViewCallback {}
  
  @Retention(RetentionPolicy.SOURCE)
  @IntRange(from = 1L)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Duration {}
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected static interface OnAttachStateChangeListener {
    void onViewAttachedToWindow(View param1View);
    
    void onViewDetachedFromWindow(View param1View);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected static interface OnLayoutChangeListener {
    void onLayoutChange(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected static class SnackbarBaseLayout extends FrameLayout {
    private final AccessibilityManager accessibilityManager;
    
    private BaseTransientBottomBar.OnAttachStateChangeListener onAttachStateChangeListener;
    
    private BaseTransientBottomBar.OnLayoutChangeListener onLayoutChangeListener;
    
    private final AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener;
    
    protected SnackbarBaseLayout(Context param1Context) {
      this(param1Context, (AttributeSet)null);
    }
    
    protected SnackbarBaseLayout(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.SnackbarLayout);
      if (typedArray.hasValue(R.styleable.SnackbarLayout_elevation))
        ViewCompat.setElevation((View)this, typedArray.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0)); 
      typedArray.recycle();
      this.accessibilityManager = (AccessibilityManager)param1Context.getSystemService("accessibility");
      this.touchExplorationStateChangeListener = new AccessibilityManagerCompat.TouchExplorationStateChangeListener() {
          public void onTouchExplorationStateChanged(boolean param2Boolean) {
            BaseTransientBottomBar.SnackbarBaseLayout.this.setClickableOrFocusableBasedOnAccessibility(param2Boolean);
          }
        };
      AccessibilityManagerCompat.addTouchExplorationStateChangeListener(this.accessibilityManager, this.touchExplorationStateChangeListener);
      setClickableOrFocusableBasedOnAccessibility(this.accessibilityManager.isTouchExplorationEnabled());
    }
    
    private void setClickableOrFocusableBasedOnAccessibility(boolean param1Boolean) {
      setClickable(param1Boolean ^ true);
      setFocusable(param1Boolean);
    }
    
    protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      if (this.onAttachStateChangeListener != null)
        this.onAttachStateChangeListener.onViewAttachedToWindow((View)this); 
      ViewCompat.requestApplyInsets((View)this);
    }
    
    protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.onAttachStateChangeListener != null)
        this.onAttachStateChangeListener.onViewDetachedFromWindow((View)this); 
      AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(this.accessibilityManager, this.touchExplorationStateChangeListener);
    }
    
    protected void onLayout(boolean param1Boolean, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      super.onLayout(param1Boolean, param1Int1, param1Int2, param1Int3, param1Int4);
      if (this.onLayoutChangeListener != null)
        this.onLayoutChangeListener.onLayoutChange((View)this, param1Int1, param1Int2, param1Int3, param1Int4); 
    }
    
    void setOnAttachStateChangeListener(BaseTransientBottomBar.OnAttachStateChangeListener param1OnAttachStateChangeListener) {
      this.onAttachStateChangeListener = param1OnAttachStateChangeListener;
    }
    
    void setOnLayoutChangeListener(BaseTransientBottomBar.OnLayoutChangeListener param1OnLayoutChangeListener) {
      this.onLayoutChangeListener = param1OnLayoutChangeListener;
    }
  }
  
  class null implements AccessibilityManagerCompat.TouchExplorationStateChangeListener {
    public void onTouchExplorationStateChanged(boolean param1Boolean) {
      this.this$0.setClickableOrFocusableBasedOnAccessibility(param1Boolean);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\BaseTransientBottomBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */