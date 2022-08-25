package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@DefaultBehavior(AppBarLayout.Behavior.class)
public class AppBarLayout extends LinearLayout {
  private static final int INVALID_SCROLL_RANGE = -1;
  
  static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
  
  static final int PENDING_ACTION_COLLAPSED = 2;
  
  static final int PENDING_ACTION_EXPANDED = 1;
  
  static final int PENDING_ACTION_FORCE = 8;
  
  static final int PENDING_ACTION_NONE = 0;
  
  private int downPreScrollRange = -1;
  
  private int downScrollRange = -1;
  
  private boolean haveChildWithInterpolator;
  
  private WindowInsetsCompat lastInsets;
  
  private boolean liftOnScroll;
  
  private boolean liftable;
  
  private boolean liftableOverride;
  
  private boolean lifted;
  
  private List<BaseOnOffsetChangedListener> listeners;
  
  private int pendingAction = 0;
  
  private int[] tmpStatesArray;
  
  private int totalScrollRange = -1;
  
  public AppBarLayout(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public AppBarLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    setOrientation(1);
    if (Build.VERSION.SDK_INT >= 21) {
      ViewUtilsLollipop.setBoundsViewOutlineProvider((View)this);
      ViewUtilsLollipop.setStateListAnimatorFromAttrs((View)this, paramAttributeSet, 0, R.style.Widget_Design_AppBarLayout);
    } 
    TypedArray typedArray = ThemeEnforcement.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout, new int[0]);
    ViewCompat.setBackground((View)this, typedArray.getDrawable(R.styleable.AppBarLayout_android_background));
    if (typedArray.hasValue(R.styleable.AppBarLayout_expanded))
      setExpanded(typedArray.getBoolean(R.styleable.AppBarLayout_expanded, false), false, false); 
    if (Build.VERSION.SDK_INT >= 21 && typedArray.hasValue(R.styleable.AppBarLayout_elevation))
      ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator((View)this, typedArray.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0)); 
    if (Build.VERSION.SDK_INT >= 26) {
      if (typedArray.hasValue(R.styleable.AppBarLayout_android_keyboardNavigationCluster))
        setKeyboardNavigationCluster(typedArray.getBoolean(R.styleable.AppBarLayout_android_keyboardNavigationCluster, false)); 
      if (typedArray.hasValue(R.styleable.AppBarLayout_android_touchscreenBlocksFocus))
        setTouchscreenBlocksFocus(typedArray.getBoolean(R.styleable.AppBarLayout_android_touchscreenBlocksFocus, false)); 
    } 
    this.liftOnScroll = typedArray.getBoolean(R.styleable.AppBarLayout_liftOnScroll, false);
    typedArray.recycle();
    ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
          public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
            return AppBarLayout.this.onWindowInsetChanged(param1WindowInsetsCompat);
          }
        });
  }
  
  private boolean hasCollapsibleChild() {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      if (((LayoutParams)getChildAt(b).getLayoutParams()).isCollapsible())
        return true; 
    } 
    return false;
  }
  
  private void invalidateScrollRanges() {
    this.totalScrollRange = -1;
    this.downPreScrollRange = -1;
    this.downScrollRange = -1;
  }
  
  private void setExpanded(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    byte b1;
    byte b3;
    if (paramBoolean1) {
      b1 = 1;
    } else {
      b1 = 2;
    } 
    byte b2 = 0;
    if (paramBoolean2) {
      b3 = 4;
    } else {
      b3 = 0;
    } 
    if (paramBoolean3)
      b2 = 8; 
    this.pendingAction = b1 | b3 | b2;
    requestLayout();
  }
  
  private boolean setLiftableState(boolean paramBoolean) {
    if (this.liftable != paramBoolean) {
      this.liftable = paramBoolean;
      refreshDrawableState();
      return true;
    } 
    return false;
  }
  
  public void addOnOffsetChangedListener(BaseOnOffsetChangedListener paramBaseOnOffsetChangedListener) {
    if (this.listeners == null)
      this.listeners = new ArrayList<>(); 
    if (paramBaseOnOffsetChangedListener != null && !this.listeners.contains(paramBaseOnOffsetChangedListener))
      this.listeners.add(paramBaseOnOffsetChangedListener); 
  }
  
  public void addOnOffsetChangedListener(OnOffsetChangedListener paramOnOffsetChangedListener) {
    addOnOffsetChangedListener(paramOnOffsetChangedListener);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void dispatchOffsetUpdates(int paramInt) {
    if (this.listeners != null) {
      byte b = 0;
      int i = this.listeners.size();
      while (b < i) {
        BaseOnOffsetChangedListener<AppBarLayout> baseOnOffsetChangedListener = this.listeners.get(b);
        if (baseOnOffsetChangedListener != null)
          baseOnOffsetChangedListener.onOffsetChanged(this, paramInt); 
        b++;
      } 
    } 
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(-1, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (Build.VERSION.SDK_INT >= 19 && paramLayoutParams instanceof LinearLayout.LayoutParams) ? new LayoutParams((LinearLayout.LayoutParams)paramLayoutParams) : ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams) ? new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams) : new LayoutParams(paramLayoutParams));
  }
  
  int getDownNestedPreScrollRange() {
    if (this.downPreScrollRange != -1)
      return this.downPreScrollRange; 
    int i = getChildCount() - 1;
    int j;
    for (j = 0; i >= 0; j = m) {
      View view = getChildAt(i);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      int m = view.getMeasuredHeight();
      int n = layoutParams.scrollFlags;
      if ((n & 0x5) == 5) {
        j += layoutParams.topMargin + layoutParams.bottomMargin;
        if ((n & 0x8) != 0) {
          m = j + ViewCompat.getMinimumHeight(view);
        } else if ((n & 0x2) != 0) {
          m = j + m - ViewCompat.getMinimumHeight(view);
        } else {
          m = j + m - getTopInset();
        } 
      } else {
        m = j;
        if (j > 0)
          break; 
      } 
      i--;
    } 
    int k = Math.max(0, j);
    this.downPreScrollRange = k;
    return k;
  }
  
  int getDownNestedScrollRange() {
    int k;
    if (this.downScrollRange != -1)
      return this.downScrollRange; 
    int i = getChildCount();
    byte b = 0;
    int j = b;
    while (true) {
      k = j;
      if (b < i) {
        View view = getChildAt(b);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        int m = view.getMeasuredHeight();
        int n = layoutParams.topMargin;
        int i1 = layoutParams.bottomMargin;
        int i2 = layoutParams.scrollFlags;
        k = j;
        if ((i2 & 0x1) != 0) {
          j += m + n + i1;
          if ((i2 & 0x2) != 0) {
            k = j - ViewCompat.getMinimumHeight(view) + getTopInset();
            break;
          } 
          b++;
          continue;
        } 
      } 
      break;
    } 
    j = Math.max(0, k);
    this.downScrollRange = j;
    return j;
  }
  
  public final int getMinimumHeightForVisibleOverlappingContent() {
    int i = getTopInset();
    int j = ViewCompat.getMinimumHeight((View)this);
    if (j != 0)
      return j * 2 + i; 
    j = getChildCount();
    if (j >= 1) {
      j = ViewCompat.getMinimumHeight(getChildAt(j - 1));
    } else {
      j = 0;
    } 
    return (j != 0) ? (j * 2 + i) : (getHeight() / 3);
  }
  
  int getPendingAction() {
    return this.pendingAction;
  }
  
  @Deprecated
  public float getTargetElevation() {
    return 0.0F;
  }
  
  @VisibleForTesting
  final int getTopInset() {
    boolean bool;
    if (this.lastInsets != null) {
      bool = this.lastInsets.getSystemWindowInsetTop();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int getTotalScrollRange() {
    int k;
    if (this.totalScrollRange != -1)
      return this.totalScrollRange; 
    int i = getChildCount();
    byte b = 0;
    int j = b;
    while (true) {
      k = j;
      if (b < i) {
        View view = getChildAt(b);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        int m = view.getMeasuredHeight();
        int n = layoutParams.scrollFlags;
        k = j;
        if ((n & 0x1) != 0) {
          j += m + layoutParams.topMargin + layoutParams.bottomMargin;
          if ((n & 0x2) != 0) {
            k = j - ViewCompat.getMinimumHeight(view);
            break;
          } 
          b++;
          continue;
        } 
      } 
      break;
    } 
    j = Math.max(0, k - getTopInset());
    this.totalScrollRange = j;
    return j;
  }
  
  int getUpNestedPreScrollRange() {
    return getTotalScrollRange();
  }
  
  boolean hasChildWithInterpolator() {
    return this.haveChildWithInterpolator;
  }
  
  boolean hasScrollableChildren() {
    boolean bool;
    if (getTotalScrollRange() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isLiftOnScroll() {
    return this.liftOnScroll;
  }
  
  protected int[] onCreateDrawableState(int paramInt) {
    if (this.tmpStatesArray == null)
      this.tmpStatesArray = new int[4]; 
    int[] arrayOfInt1 = this.tmpStatesArray;
    int[] arrayOfInt2 = super.onCreateDrawableState(paramInt + arrayOfInt1.length);
    if (this.liftable) {
      paramInt = R.attr.state_liftable;
    } else {
      paramInt = -R.attr.state_liftable;
    } 
    arrayOfInt1[0] = paramInt;
    if (this.liftable && this.lifted) {
      paramInt = R.attr.state_lifted;
    } else {
      paramInt = -R.attr.state_lifted;
    } 
    arrayOfInt1[1] = paramInt;
    if (this.liftable) {
      paramInt = R.attr.state_collapsible;
    } else {
      paramInt = -R.attr.state_collapsible;
    } 
    arrayOfInt1[2] = paramInt;
    if (this.liftable && this.lifted) {
      paramInt = R.attr.state_collapsed;
    } else {
      paramInt = -R.attr.state_collapsed;
    } 
    arrayOfInt1[3] = paramInt;
    return mergeDrawableStates(arrayOfInt2, arrayOfInt1);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    invalidateScrollRanges();
    paramBoolean = false;
    this.haveChildWithInterpolator = false;
    paramInt2 = getChildCount();
    for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++) {
      if (((LayoutParams)getChildAt(paramInt1).getLayoutParams()).getScrollInterpolator() != null) {
        this.haveChildWithInterpolator = true;
        break;
      } 
    } 
    if (!this.liftableOverride) {
      if (this.liftOnScroll || hasCollapsibleChild())
        paramBoolean = true; 
      setLiftableState(paramBoolean);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    invalidateScrollRanges();
  }
  
  WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat paramWindowInsetsCompat) {
    WindowInsetsCompat windowInsetsCompat;
    if (ViewCompat.getFitsSystemWindows((View)this)) {
      windowInsetsCompat = paramWindowInsetsCompat;
    } else {
      windowInsetsCompat = null;
    } 
    if (!ObjectsCompat.equals(this.lastInsets, windowInsetsCompat)) {
      this.lastInsets = windowInsetsCompat;
      invalidateScrollRanges();
    } 
    return paramWindowInsetsCompat;
  }
  
  public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener paramBaseOnOffsetChangedListener) {
    if (this.listeners != null && paramBaseOnOffsetChangedListener != null)
      this.listeners.remove(paramBaseOnOffsetChangedListener); 
  }
  
  public void removeOnOffsetChangedListener(OnOffsetChangedListener paramOnOffsetChangedListener) {
    removeOnOffsetChangedListener(paramOnOffsetChangedListener);
  }
  
  void resetPendingAction() {
    this.pendingAction = 0;
  }
  
  public void setExpanded(boolean paramBoolean) {
    setExpanded(paramBoolean, ViewCompat.isLaidOut((View)this));
  }
  
  public void setExpanded(boolean paramBoolean1, boolean paramBoolean2) {
    setExpanded(paramBoolean1, paramBoolean2, true);
  }
  
  public void setLiftOnScroll(boolean paramBoolean) {
    this.liftOnScroll = paramBoolean;
  }
  
  public boolean setLiftable(boolean paramBoolean) {
    this.liftableOverride = true;
    return setLiftableState(paramBoolean);
  }
  
  public boolean setLifted(boolean paramBoolean) {
    return setLiftedState(paramBoolean);
  }
  
  boolean setLiftedState(boolean paramBoolean) {
    if (this.lifted != paramBoolean) {
      this.lifted = paramBoolean;
      refreshDrawableState();
      return true;
    } 
    return false;
  }
  
  public void setOrientation(int paramInt) {
    if (paramInt != 1)
      throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation"); 
    super.setOrientation(paramInt);
  }
  
  @Deprecated
  public void setTargetElevation(float paramFloat) {
    if (Build.VERSION.SDK_INT >= 21)
      ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator((View)this, paramFloat); 
  }
  
  protected static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
    private static final int INVALID_POSITION = -1;
    
    private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
    
    private WeakReference<View> lastNestedScrollingChildRef;
    
    private int lastStartedType;
    
    private ValueAnimator offsetAnimator;
    
    private int offsetDelta;
    
    private int offsetToChildIndexOnLayout = -1;
    
    private boolean offsetToChildIndexOnLayoutIsMinHeight;
    
    private float offsetToChildIndexOnLayoutPerc;
    
    private BaseDragCallback onDragCallback;
    
    public BaseBehavior() {}
    
    public BaseBehavior(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    private void animateOffsetTo(CoordinatorLayout param1CoordinatorLayout, T param1T, int param1Int, float param1Float) {
      int i = Math.abs(getTopBottomOffsetForScrollingSibling() - param1Int);
      param1Float = Math.abs(param1Float);
      if (param1Float > 0.0F) {
        i = Math.round(i / param1Float * 1000.0F) * 3;
      } else {
        i = (int)((i / param1T.getHeight() + 1.0F) * 150.0F);
      } 
      animateOffsetWithDuration(param1CoordinatorLayout, param1T, param1Int, i);
    }
    
    private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, final T child, int param1Int1, int param1Int2) {
      int i = getTopBottomOffsetForScrollingSibling();
      if (i == param1Int1) {
        if (this.offsetAnimator != null && this.offsetAnimator.isRunning())
          this.offsetAnimator.cancel(); 
        return;
      } 
      if (this.offsetAnimator == null) {
        this.offsetAnimator = new ValueAnimator();
        this.offsetAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
              public void onAnimationUpdate(ValueAnimator param2ValueAnimator) {
                AppBarLayout.BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, child, ((Integer)param2ValueAnimator.getAnimatedValue()).intValue());
              }
            });
      } else {
        this.offsetAnimator.cancel();
      } 
      this.offsetAnimator.setDuration(Math.min(param1Int2, 600));
      this.offsetAnimator.setIntValues(new int[] { i, param1Int1 });
      this.offsetAnimator.start();
    }
    
    private boolean canScrollChildren(CoordinatorLayout param1CoordinatorLayout, T param1T, View param1View) {
      boolean bool;
      if (param1T.hasScrollableChildren() && param1CoordinatorLayout.getHeight() - param1View.getHeight() <= param1T.getHeight()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static boolean checkFlag(int param1Int1, int param1Int2) {
      boolean bool;
      if ((param1Int1 & param1Int2) == param1Int2) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Nullable
    private View findFirstScrollingChild(CoordinatorLayout param1CoordinatorLayout) {
      int i = param1CoordinatorLayout.getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = param1CoordinatorLayout.getChildAt(b);
        if (view instanceof android.support.v4.view.NestedScrollingChild)
          return view; 
      } 
      return null;
    }
    
    private static View getAppBarChildOnOffset(AppBarLayout param1AppBarLayout, int param1Int) {
      int i = Math.abs(param1Int);
      int j = param1AppBarLayout.getChildCount();
      for (param1Int = 0; param1Int < j; param1Int++) {
        View view = param1AppBarLayout.getChildAt(param1Int);
        if (i >= view.getTop() && i <= view.getBottom())
          return view; 
      } 
      return null;
    }
    
    private int getChildIndexOnOffset(T param1T, int param1Int) {
      int i = param1T.getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = param1T.getChildAt(b);
        int j = view.getTop();
        int k = view.getBottom();
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)view.getLayoutParams();
        int m = j;
        int n = k;
        if (checkFlag(layoutParams.getScrollFlags(), 32)) {
          m = j - layoutParams.topMargin;
          n = k + layoutParams.bottomMargin;
        } 
        k = -param1Int;
        if (m <= k && n >= k)
          return b; 
      } 
      return -1;
    }
    
    private int interpolateOffset(T param1T, int param1Int) {
      int i = Math.abs(param1Int);
      int j = param1T.getChildCount();
      int k = 0;
      for (int m = 0; m < j; m++) {
        View view = param1T.getChildAt(m);
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)view.getLayoutParams();
        Interpolator interpolator = layoutParams.getScrollInterpolator();
        if (i >= view.getTop() && i <= view.getBottom()) {
          if (interpolator != null) {
            j = layoutParams.getScrollFlags();
            m = k;
            if ((j & 0x1) != 0) {
              k = 0 + view.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
              m = k;
              if ((j & 0x2) != 0)
                m = k - ViewCompat.getMinimumHeight(view); 
            } 
            k = m;
            if (ViewCompat.getFitsSystemWindows(view))
              k = m - param1T.getTopInset(); 
            if (k > 0) {
              m = view.getTop();
              float f = k;
              m = Math.round(f * interpolator.getInterpolation((i - m) / f));
              return Integer.signum(param1Int) * (view.getTop() + m);
            } 
          } 
          break;
        } 
      } 
      return param1Int;
    }
    
    private boolean shouldJumpElevationState(CoordinatorLayout param1CoordinatorLayout, T param1T) {
      List<View> list = param1CoordinatorLayout.getDependents((View)param1T);
      int i = list.size();
      boolean bool = false;
      for (byte b = 0; b < i; b++) {
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)((View)list.get(b)).getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.ScrollingViewBehavior) {
          if (((AppBarLayout.ScrollingViewBehavior)behavior).getOverlayTop() != 0)
            bool = true; 
          return bool;
        } 
      } 
      return false;
    }
    
    private void snapToChildIfNeeded(CoordinatorLayout param1CoordinatorLayout, T param1T) {
      int i = getTopBottomOffsetForScrollingSibling();
      int j = getChildIndexOnOffset(param1T, i);
      if (j >= 0) {
        View view = param1T.getChildAt(j);
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)view.getLayoutParams();
        int k = layoutParams.getScrollFlags();
        if ((k & 0x11) == 17) {
          int m = -view.getTop();
          int n = -view.getBottom();
          int i1 = n;
          if (j == param1T.getChildCount() - 1)
            i1 = n + param1T.getTopInset(); 
          if (checkFlag(k, 2)) {
            n = i1 + ViewCompat.getMinimumHeight(view);
            j = m;
          } else {
            j = m;
            n = i1;
            if (checkFlag(k, 5)) {
              n = ViewCompat.getMinimumHeight(view) + i1;
              if (i < n) {
                j = n;
                n = i1;
              } else {
                j = m;
              } 
            } 
          } 
          m = j;
          i1 = n;
          if (checkFlag(k, 32)) {
            m = j + layoutParams.topMargin;
            i1 = n - layoutParams.bottomMargin;
          } 
          n = m;
          if (i < (i1 + m) / 2)
            n = i1; 
          animateOffsetTo(param1CoordinatorLayout, param1T, MathUtils.clamp(n, -param1T.getTotalScrollRange(), 0), 0.0F);
        } 
      } 
    }
    
    private void stopNestedScrollIfNeeded(int param1Int1, T param1T, View param1View, int param1Int2) {
      if (param1Int2 == 1) {
        param1Int2 = getTopBottomOffsetForScrollingSibling();
        if ((param1Int1 < 0 && param1Int2 == 0) || (param1Int1 > 0 && param1Int2 == -param1T.getDownNestedScrollRange()))
          ViewCompat.stopNestedScroll(param1View, 1); 
      } 
    }
    
    private void updateAppBarLayoutDrawableState(CoordinatorLayout param1CoordinatorLayout, T param1T, int param1Int1, int param1Int2, boolean param1Boolean) {
      // Byte code:
      //   0: aload_2
      //   1: iload_3
      //   2: invokestatic getAppBarChildOnOffset : (Landroid/support/design/widget/AppBarLayout;I)Landroid/view/View;
      //   5: astore #6
      //   7: aload #6
      //   9: ifnull -> 190
      //   12: aload #6
      //   14: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
      //   17: checkcast android/support/design/widget/AppBarLayout$LayoutParams
      //   20: invokevirtual getScrollFlags : ()I
      //   23: istore #7
      //   25: iload #7
      //   27: iconst_1
      //   28: iand
      //   29: ifeq -> 104
      //   32: aload #6
      //   34: invokestatic getMinimumHeight : (Landroid/view/View;)I
      //   37: istore #8
      //   39: iload #4
      //   41: ifle -> 76
      //   44: iload #7
      //   46: bipush #12
      //   48: iand
      //   49: ifeq -> 76
      //   52: iload_3
      //   53: ineg
      //   54: aload #6
      //   56: invokevirtual getBottom : ()I
      //   59: iload #8
      //   61: isub
      //   62: aload_2
      //   63: invokevirtual getTopInset : ()I
      //   66: isub
      //   67: if_icmplt -> 104
      //   70: iconst_1
      //   71: istore #9
      //   73: goto -> 107
      //   76: iload #7
      //   78: iconst_2
      //   79: iand
      //   80: ifeq -> 104
      //   83: iload_3
      //   84: ineg
      //   85: aload #6
      //   87: invokevirtual getBottom : ()I
      //   90: iload #8
      //   92: isub
      //   93: aload_2
      //   94: invokevirtual getTopInset : ()I
      //   97: isub
      //   98: if_icmplt -> 104
      //   101: goto -> 70
      //   104: iconst_0
      //   105: istore #9
      //   107: iload #9
      //   109: istore #10
      //   111: aload_2
      //   112: invokevirtual isLiftOnScroll : ()Z
      //   115: ifeq -> 151
      //   118: aload_0
      //   119: aload_1
      //   120: invokespecial findFirstScrollingChild : (Landroid/support/design/widget/CoordinatorLayout;)Landroid/view/View;
      //   123: astore #6
      //   125: iload #9
      //   127: istore #10
      //   129: aload #6
      //   131: ifnull -> 151
      //   134: aload #6
      //   136: invokevirtual getScrollY : ()I
      //   139: ifle -> 148
      //   142: iconst_1
      //   143: istore #10
      //   145: goto -> 151
      //   148: iconst_0
      //   149: istore #10
      //   151: aload_2
      //   152: iload #10
      //   154: invokevirtual setLiftedState : (Z)Z
      //   157: istore #9
      //   159: getstatic android/os/Build$VERSION.SDK_INT : I
      //   162: bipush #11
      //   164: if_icmplt -> 190
      //   167: iload #5
      //   169: ifne -> 186
      //   172: iload #9
      //   174: ifeq -> 190
      //   177: aload_0
      //   178: aload_1
      //   179: aload_2
      //   180: invokespecial shouldJumpElevationState : (Landroid/support/design/widget/CoordinatorLayout;Landroid/support/design/widget/AppBarLayout;)Z
      //   183: ifeq -> 190
      //   186: aload_2
      //   187: invokevirtual jumpDrawablesToCurrentState : ()V
      //   190: return
    }
    
    boolean canDragView(T param1T) {
      if (this.onDragCallback != null)
        return this.onDragCallback.canDrag(param1T); 
      WeakReference<View> weakReference = this.lastNestedScrollingChildRef;
      boolean bool = true;
      if (weakReference != null) {
        View view = this.lastNestedScrollingChildRef.get();
        if (view == null || !view.isShown() || view.canScrollVertically(-1))
          bool = false; 
        return bool;
      } 
      return true;
    }
    
    int getMaxDragOffset(T param1T) {
      return -param1T.getDownNestedScrollRange();
    }
    
    int getScrollRangeForDragFling(T param1T) {
      return param1T.getTotalScrollRange();
    }
    
    int getTopBottomOffsetForScrollingSibling() {
      return getTopAndBottomOffset() + this.offsetDelta;
    }
    
    @VisibleForTesting
    boolean isOffsetAnimatorRunning() {
      boolean bool;
      if (this.offsetAnimator != null && this.offsetAnimator.isRunning()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void onFlingFinished(CoordinatorLayout param1CoordinatorLayout, T param1T) {
      snapToChildIfNeeded(param1CoordinatorLayout, param1T);
    }
    
    public boolean onLayoutChild(CoordinatorLayout param1CoordinatorLayout, T param1T, int param1Int) {
      boolean bool = super.onLayoutChild(param1CoordinatorLayout, param1T, param1Int);
      int i = param1T.getPendingAction();
      if (this.offsetToChildIndexOnLayout >= 0 && (i & 0x8) == 0) {
        View view = param1T.getChildAt(this.offsetToChildIndexOnLayout);
        param1Int = -view.getBottom();
        if (this.offsetToChildIndexOnLayoutIsMinHeight) {
          param1Int += ViewCompat.getMinimumHeight(view) + param1T.getTopInset();
        } else {
          param1Int += Math.round(view.getHeight() * this.offsetToChildIndexOnLayoutPerc);
        } 
        setHeaderTopBottomOffset(param1CoordinatorLayout, param1T, param1Int);
      } else if (i != 0) {
        if ((i & 0x4) != 0) {
          param1Int = 1;
        } else {
          param1Int = 0;
        } 
        if ((i & 0x2) != 0) {
          i = -param1T.getUpNestedPreScrollRange();
          if (param1Int != 0) {
            animateOffsetTo(param1CoordinatorLayout, param1T, i, 0.0F);
          } else {
            setHeaderTopBottomOffset(param1CoordinatorLayout, param1T, i);
          } 
        } else if ((i & 0x1) != 0) {
          if (param1Int != 0) {
            animateOffsetTo(param1CoordinatorLayout, param1T, 0, 0.0F);
          } else {
            setHeaderTopBottomOffset(param1CoordinatorLayout, param1T, 0);
          } 
        } 
      } 
      param1T.resetPendingAction();
      this.offsetToChildIndexOnLayout = -1;
      setTopAndBottomOffset(MathUtils.clamp(getTopAndBottomOffset(), -param1T.getTotalScrollRange(), 0));
      updateAppBarLayoutDrawableState(param1CoordinatorLayout, param1T, getTopAndBottomOffset(), 0, true);
      param1T.dispatchOffsetUpdates(getTopAndBottomOffset());
      return bool;
    }
    
    public boolean onMeasureChild(CoordinatorLayout param1CoordinatorLayout, T param1T, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      if (((CoordinatorLayout.LayoutParams)param1T.getLayoutParams()).height == -2) {
        param1CoordinatorLayout.onMeasureChild((View)param1T, param1Int1, param1Int2, View.MeasureSpec.makeMeasureSpec(0, 0), param1Int4);
        return true;
      } 
      return super.onMeasureChild(param1CoordinatorLayout, param1T, param1Int1, param1Int2, param1Int3, param1Int4);
    }
    
    public void onNestedPreScroll(CoordinatorLayout param1CoordinatorLayout, T param1T, View param1View, int param1Int1, int param1Int2, int[] param1ArrayOfint, int param1Int3) {
      if (param1Int2 != 0) {
        int i;
        if (param1Int2 < 0) {
          i = -param1T.getTotalScrollRange();
          param1Int1 = param1T.getDownNestedPreScrollRange() + i;
        } else {
          i = -param1T.getUpNestedPreScrollRange();
          param1Int1 = 0;
        } 
        if (i != param1Int1) {
          param1ArrayOfint[1] = scroll(param1CoordinatorLayout, param1T, param1Int2, i, param1Int1);
          stopNestedScrollIfNeeded(param1Int2, param1T, param1View, param1Int3);
        } 
      } 
    }
    
    public void onNestedScroll(CoordinatorLayout param1CoordinatorLayout, T param1T, View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
      if (param1Int4 < 0) {
        scroll(param1CoordinatorLayout, param1T, param1Int4, -param1T.getDownNestedScrollRange(), 0);
        stopNestedScrollIfNeeded(param1Int4, param1T, param1View, param1Int5);
      } 
      if (param1T.isLiftOnScroll()) {
        boolean bool;
        if (param1View.getScrollY() > 0) {
          bool = true;
        } else {
          bool = false;
        } 
        param1T.setLiftedState(bool);
      } 
    }
    
    public void onRestoreInstanceState(CoordinatorLayout param1CoordinatorLayout, T param1T, Parcelable param1Parcelable) {
      SavedState savedState;
      if (param1Parcelable instanceof SavedState) {
        savedState = (SavedState)param1Parcelable;
        super.onRestoreInstanceState(param1CoordinatorLayout, param1T, savedState.getSuperState());
        this.offsetToChildIndexOnLayout = savedState.firstVisibleChildIndex;
        this.offsetToChildIndexOnLayoutPerc = savedState.firstVisibleChildPercentageShown;
        this.offsetToChildIndexOnLayoutIsMinHeight = savedState.firstVisibleChildAtMinimumHeight;
      } else {
        super.onRestoreInstanceState(param1CoordinatorLayout, param1T, (Parcelable)savedState);
        this.offsetToChildIndexOnLayout = -1;
      } 
    }
    
    public Parcelable onSaveInstanceState(CoordinatorLayout param1CoordinatorLayout, T param1T) {
      SavedState savedState;
      Parcelable parcelable = super.onSaveInstanceState(param1CoordinatorLayout, param1T);
      int i = getTopAndBottomOffset();
      int j = param1T.getChildCount();
      boolean bool = false;
      for (byte b = 0; b < j; b++) {
        View view = param1T.getChildAt(b);
        int k = view.getBottom() + i;
        if (view.getTop() + i <= 0 && k >= 0) {
          savedState = new SavedState(parcelable);
          savedState.firstVisibleChildIndex = b;
          if (k == ViewCompat.getMinimumHeight(view) + param1T.getTopInset())
            bool = true; 
          savedState.firstVisibleChildAtMinimumHeight = bool;
          savedState.firstVisibleChildPercentageShown = k / view.getHeight();
          return (Parcelable)savedState;
        } 
      } 
      return (Parcelable)savedState;
    }
    
    public boolean onStartNestedScroll(CoordinatorLayout param1CoordinatorLayout, T param1T, View param1View1, View param1View2, int param1Int1, int param1Int2) {
      boolean bool;
      if ((param1Int1 & 0x2) != 0 && (param1T.isLiftOnScroll() || canScrollChildren(param1CoordinatorLayout, param1T, param1View1))) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool && this.offsetAnimator != null)
        this.offsetAnimator.cancel(); 
      this.lastNestedScrollingChildRef = null;
      this.lastStartedType = param1Int2;
      return bool;
    }
    
    public void onStopNestedScroll(CoordinatorLayout param1CoordinatorLayout, T param1T, View param1View, int param1Int) {
      if (this.lastStartedType == 0 || param1Int == 1)
        snapToChildIfNeeded(param1CoordinatorLayout, param1T); 
      this.lastNestedScrollingChildRef = new WeakReference<>(param1View);
    }
    
    public void setDragCallback(@Nullable BaseDragCallback param1BaseDragCallback) {
      this.onDragCallback = param1BaseDragCallback;
    }
    
    int setHeaderTopBottomOffset(CoordinatorLayout param1CoordinatorLayout, T param1T, int param1Int1, int param1Int2, int param1Int3) {
      int i = getTopBottomOffsetForScrollingSibling();
      boolean bool = false;
      if (param1Int2 != 0 && i >= param1Int2 && i <= param1Int3) {
        param1Int2 = MathUtils.clamp(param1Int1, param1Int2, param1Int3);
        param1Int1 = bool;
        if (i != param1Int2) {
          if (param1T.hasChildWithInterpolator()) {
            param1Int1 = interpolateOffset(param1T, param1Int2);
          } else {
            param1Int1 = param1Int2;
          } 
          boolean bool1 = setTopAndBottomOffset(param1Int1);
          param1Int3 = i - param1Int2;
          this.offsetDelta = param1Int2 - param1Int1;
          if (!bool1 && param1T.hasChildWithInterpolator())
            param1CoordinatorLayout.dispatchDependentViewsChanged((View)param1T); 
          param1T.dispatchOffsetUpdates(getTopAndBottomOffset());
          if (param1Int2 < i) {
            param1Int1 = -1;
          } else {
            param1Int1 = 1;
          } 
          updateAppBarLayoutDrawableState(param1CoordinatorLayout, param1T, param1Int2, param1Int1, false);
          param1Int1 = param1Int3;
        } 
      } else {
        this.offsetDelta = 0;
        param1Int1 = bool;
      } 
      return param1Int1;
    }
    
    public static abstract class BaseDragCallback<T extends AppBarLayout> {
      public abstract boolean canDrag(@NonNull T param2T);
    }
    
    protected static class SavedState extends AbsSavedState {
      public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
          public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param3Parcel) {
            return new AppBarLayout.BaseBehavior.SavedState(param3Parcel, null);
          }
          
          public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param3Parcel, ClassLoader param3ClassLoader) {
            return new AppBarLayout.BaseBehavior.SavedState(param3Parcel, param3ClassLoader);
          }
          
          public AppBarLayout.BaseBehavior.SavedState[] newArray(int param3Int) {
            return new AppBarLayout.BaseBehavior.SavedState[param3Int];
          }
        };
      
      boolean firstVisibleChildAtMinimumHeight;
      
      int firstVisibleChildIndex;
      
      float firstVisibleChildPercentageShown;
      
      public SavedState(Parcel param2Parcel, ClassLoader param2ClassLoader) {
        super(param2Parcel, param2ClassLoader);
        boolean bool;
        this.firstVisibleChildIndex = param2Parcel.readInt();
        this.firstVisibleChildPercentageShown = param2Parcel.readFloat();
        if (param2Parcel.readByte() != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        this.firstVisibleChildAtMinimumHeight = bool;
      }
      
      public SavedState(Parcelable param2Parcelable) {
        super(param2Parcelable);
      }
      
      public void writeToParcel(Parcel param2Parcel, int param2Int) {
        super.writeToParcel(param2Parcel, param2Int);
        param2Parcel.writeInt(this.firstVisibleChildIndex);
        param2Parcel.writeFloat(this.firstVisibleChildPercentageShown);
        param2Parcel.writeByte((byte)this.firstVisibleChildAtMinimumHeight);
      }
    }
    
    static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
      public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param2Parcel) {
        return new AppBarLayout.BaseBehavior.SavedState(param2Parcel, null);
      }
      
      public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
        return new AppBarLayout.BaseBehavior.SavedState(param2Parcel, param2ClassLoader);
      }
      
      public AppBarLayout.BaseBehavior.SavedState[] newArray(int param2Int) {
        return new AppBarLayout.BaseBehavior.SavedState[param2Int];
      }
    }
  }
  
  class null implements ValueAnimator.AnimatorUpdateListener {
    public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
      this.this$0.setHeaderTopBottomOffset(coordinatorLayout, child, ((Integer)param1ValueAnimator.getAnimatedValue()).intValue());
    }
  }
  
  public static abstract class BaseDragCallback<T extends AppBarLayout> {
    public abstract boolean canDrag(@NonNull T param1T);
  }
  
  protected static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param3Parcel) {
          return new AppBarLayout.BaseBehavior.SavedState(param3Parcel, null);
        }
        
        public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param3Parcel, ClassLoader param3ClassLoader) {
          return new AppBarLayout.BaseBehavior.SavedState(param3Parcel, param3ClassLoader);
        }
        
        public AppBarLayout.BaseBehavior.SavedState[] newArray(int param3Int) {
          return new AppBarLayout.BaseBehavior.SavedState[param3Int];
        }
      };
    
    boolean firstVisibleChildAtMinimumHeight;
    
    int firstVisibleChildIndex;
    
    float firstVisibleChildPercentageShown;
    
    public SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      boolean bool;
      this.firstVisibleChildIndex = param1Parcel.readInt();
      this.firstVisibleChildPercentageShown = param1Parcel.readFloat();
      if (param1Parcel.readByte() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.firstVisibleChildAtMinimumHeight = bool;
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.firstVisibleChildIndex);
      param1Parcel.writeFloat(this.firstVisibleChildPercentageShown);
      param1Parcel.writeByte((byte)this.firstVisibleChildAtMinimumHeight);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<BaseBehavior.SavedState> {
    public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param1Parcel) {
      return new AppBarLayout.BaseBehavior.SavedState(param1Parcel, null);
    }
    
    public AppBarLayout.BaseBehavior.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new AppBarLayout.BaseBehavior.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public AppBarLayout.BaseBehavior.SavedState[] newArray(int param1Int) {
      return new AppBarLayout.BaseBehavior.SavedState[param1Int];
    }
  }
  
  public static interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
    void onOffsetChanged(T param1T, int param1Int);
  }
  
  public static class Behavior extends BaseBehavior<AppBarLayout> {
    public Behavior() {}
    
    public Behavior(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public static abstract class DragCallback extends AppBarLayout.BaseBehavior.BaseDragCallback<AppBarLayout> {}
  }
  
  public static abstract class DragCallback extends BaseBehavior.BaseDragCallback<AppBarLayout> {}
  
  public static class LayoutParams extends LinearLayout.LayoutParams {
    static final int COLLAPSIBLE_FLAGS = 10;
    
    static final int FLAG_QUICK_RETURN = 5;
    
    static final int FLAG_SNAP = 17;
    
    public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
    
    public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
    
    public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
    
    public static final int SCROLL_FLAG_SCROLL = 1;
    
    public static final int SCROLL_FLAG_SNAP = 16;
    
    public static final int SCROLL_FLAG_SNAP_MARGINS = 32;
    
    int scrollFlags = 1;
    
    Interpolator scrollInterpolator;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(int param1Int1, int param1Int2, float param1Float) {
      super(param1Int1, param1Int2, param1Float);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.AppBarLayout_Layout);
      this.scrollFlags = typedArray.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
      if (typedArray.hasValue(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator))
        this.scrollInterpolator = AnimationUtils.loadInterpolator(param1Context, typedArray.getResourceId(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0)); 
      typedArray.recycle();
    }
    
    @RequiresApi(19)
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.scrollFlags = param1LayoutParams.scrollFlags;
      this.scrollInterpolator = param1LayoutParams.scrollInterpolator;
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    @RequiresApi(19)
    public LayoutParams(LinearLayout.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public int getScrollFlags() {
      return this.scrollFlags;
    }
    
    public Interpolator getScrollInterpolator() {
      return this.scrollInterpolator;
    }
    
    boolean isCollapsible() {
      int i = this.scrollFlags;
      boolean bool = true;
      if ((i & 0x1) != 1 || (this.scrollFlags & 0xA) == 0)
        bool = false; 
      return bool;
    }
    
    public void setScrollFlags(int param1Int) {
      this.scrollFlags = param1Int;
    }
    
    public void setScrollInterpolator(Interpolator param1Interpolator) {
      this.scrollInterpolator = param1Interpolator;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface ScrollFlags {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface ScrollFlags {}
  
  public static interface OnOffsetChangedListener extends BaseOnOffsetChangedListener<AppBarLayout> {
    void onOffsetChanged(AppBarLayout param1AppBarLayout, int param1Int);
  }
  
  public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
    public ScrollingViewBehavior() {}
    
    public ScrollingViewBehavior(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.ScrollingViewBehavior_Layout);
      setOverlayTop(typedArray.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
      typedArray.recycle();
    }
    
    private static int getAppBarLayoutOffset(AppBarLayout param1AppBarLayout) {
      CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)param1AppBarLayout.getLayoutParams()).getBehavior();
      return (behavior instanceof AppBarLayout.BaseBehavior) ? ((AppBarLayout.BaseBehavior)behavior).getTopBottomOffsetForScrollingSibling() : 0;
    }
    
    private void offsetChildAsNeeded(View param1View1, View param1View2) {
      CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)param1View2.getLayoutParams()).getBehavior();
      if (behavior instanceof AppBarLayout.BaseBehavior) {
        behavior = behavior;
        ViewCompat.offsetTopAndBottom(param1View1, param1View2.getBottom() - param1View1.getTop() + ((AppBarLayout.BaseBehavior)behavior).offsetDelta + getVerticalLayoutGap() - getOverlapPixelsForOffset(param1View2));
      } 
    }
    
    private void updateLiftedStateIfNeeded(View param1View1, View param1View2) {
      if (param1View2 instanceof AppBarLayout) {
        AppBarLayout appBarLayout = (AppBarLayout)param1View2;
        if (appBarLayout.isLiftOnScroll()) {
          boolean bool;
          if (param1View1.getScrollY() > 0) {
            bool = true;
          } else {
            bool = false;
          } 
          appBarLayout.setLiftedState(bool);
        } 
      } 
    }
    
    AppBarLayout findFirstDependency(List<View> param1List) {
      int i = param1List.size();
      for (byte b = 0; b < i; b++) {
        View view = param1List.get(b);
        if (view instanceof AppBarLayout)
          return (AppBarLayout)view; 
      } 
      return null;
    }
    
    float getOverlapRatioForOffset(View param1View) {
      if (param1View instanceof AppBarLayout) {
        AppBarLayout appBarLayout = (AppBarLayout)param1View;
        int i = appBarLayout.getTotalScrollRange();
        int j = appBarLayout.getDownNestedPreScrollRange();
        int k = getAppBarLayoutOffset(appBarLayout);
        if (j != 0 && i + k <= j)
          return 0.0F; 
        i -= j;
        if (i != 0)
          return k / i + 1.0F; 
      } 
      return 0.0F;
    }
    
    int getScrollRange(View param1View) {
      return (param1View instanceof AppBarLayout) ? ((AppBarLayout)param1View).getTotalScrollRange() : super.getScrollRange(param1View);
    }
    
    public boolean layoutDependsOn(CoordinatorLayout param1CoordinatorLayout, View param1View1, View param1View2) {
      return param1View2 instanceof AppBarLayout;
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout param1CoordinatorLayout, View param1View1, View param1View2) {
      offsetChildAsNeeded(param1View1, param1View2);
      updateLiftedStateIfNeeded(param1View1, param1View2);
      return false;
    }
    
    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout param1CoordinatorLayout, View param1View, Rect param1Rect, boolean param1Boolean) {
      AppBarLayout appBarLayout = findFirstDependency(param1CoordinatorLayout.getDependencies(param1View));
      if (appBarLayout != null) {
        param1Rect.offset(param1View.getLeft(), param1View.getTop());
        Rect rect = this.tempRect1;
        rect.set(0, 0, param1CoordinatorLayout.getWidth(), param1CoordinatorLayout.getHeight());
        if (!rect.contains(param1Rect)) {
          appBarLayout.setExpanded(false, param1Boolean ^ true);
          return true;
        } 
      } 
      return false;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\AppBarLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */