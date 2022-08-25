package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.v4.math.MathUtils;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
  private static final float HIDE_FRICTION = 0.1F;
  
  private static final float HIDE_THRESHOLD = 0.5F;
  
  public static final int PEEK_HEIGHT_AUTO = -1;
  
  public static final int STATE_COLLAPSED = 4;
  
  public static final int STATE_DRAGGING = 1;
  
  public static final int STATE_EXPANDED = 3;
  
  public static final int STATE_HALF_EXPANDED = 6;
  
  public static final int STATE_HIDDEN = 5;
  
  public static final int STATE_SETTLING = 2;
  
  int activePointerId;
  
  private BottomSheetCallback callback;
  
  int collapsedOffset;
  
  private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() {
      public int clampViewPositionHorizontal(@NonNull View param1View, int param1Int1, int param1Int2) {
        return param1View.getLeft();
      }
      
      public int clampViewPositionVertical(@NonNull View param1View, int param1Int1, int param1Int2) {
        int i = BottomSheetBehavior.this.getExpandedOffset();
        if (BottomSheetBehavior.this.hideable) {
          param1Int2 = BottomSheetBehavior.this.parentHeight;
        } else {
          param1Int2 = BottomSheetBehavior.this.collapsedOffset;
        } 
        return MathUtils.clamp(param1Int1, i, param1Int2);
      }
      
      public int getViewVerticalDragRange(@NonNull View param1View) {
        return BottomSheetBehavior.this.hideable ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset;
      }
      
      public void onViewDragStateChanged(int param1Int) {
        if (param1Int == 1)
          BottomSheetBehavior.this.setStateInternal(1); 
      }
      
      public void onViewPositionChanged(@NonNull View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
        BottomSheetBehavior.this.dispatchOnSlide(param1Int2);
      }
      
      public void onViewReleased(@NonNull View param1View, float param1Float1, float param1Float2) {
        // Byte code:
        //   0: iconst_0
        //   1: istore #4
        //   3: iconst_4
        //   4: istore #5
        //   6: fload_3
        //   7: fconst_0
        //   8: fcmpg
        //   9: ifge -> 70
        //   12: aload_0
        //   13: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   16: invokestatic access$000 : (Landroid/support/design/widget/BottomSheetBehavior;)Z
        //   19: ifeq -> 37
        //   22: aload_0
        //   23: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   26: getfield fitToContentsOffset : I
        //   29: istore #4
        //   31: iconst_3
        //   32: istore #5
        //   34: goto -> 333
        //   37: aload_1
        //   38: invokevirtual getTop : ()I
        //   41: aload_0
        //   42: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   45: getfield halfExpandedOffset : I
        //   48: if_icmple -> 67
        //   51: aload_0
        //   52: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   55: getfield halfExpandedOffset : I
        //   58: istore #4
        //   60: bipush #6
        //   62: istore #5
        //   64: goto -> 333
        //   67: goto -> 31
        //   70: aload_0
        //   71: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   74: getfield hideable : Z
        //   77: ifeq -> 133
        //   80: aload_0
        //   81: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   84: aload_1
        //   85: fload_3
        //   86: invokevirtual shouldHide : (Landroid/view/View;F)Z
        //   89: ifeq -> 133
        //   92: aload_1
        //   93: invokevirtual getTop : ()I
        //   96: aload_0
        //   97: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   100: getfield collapsedOffset : I
        //   103: if_icmpgt -> 118
        //   106: fload_2
        //   107: invokestatic abs : (F)F
        //   110: fload_3
        //   111: invokestatic abs : (F)F
        //   114: fcmpg
        //   115: ifge -> 133
        //   118: aload_0
        //   119: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   122: getfield parentHeight : I
        //   125: istore #4
        //   127: iconst_5
        //   128: istore #5
        //   130: goto -> 333
        //   133: fload_3
        //   134: fconst_0
        //   135: fcmpl
        //   136: ifeq -> 166
        //   139: fload_2
        //   140: invokestatic abs : (F)F
        //   143: fload_3
        //   144: invokestatic abs : (F)F
        //   147: fcmpl
        //   148: ifle -> 154
        //   151: goto -> 166
        //   154: aload_0
        //   155: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   158: getfield collapsedOffset : I
        //   161: istore #4
        //   163: goto -> 333
        //   166: aload_1
        //   167: invokevirtual getTop : ()I
        //   170: istore #6
        //   172: aload_0
        //   173: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   176: invokestatic access$000 : (Landroid/support/design/widget/BottomSheetBehavior;)Z
        //   179: ifeq -> 235
        //   182: iload #6
        //   184: aload_0
        //   185: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   188: getfield fitToContentsOffset : I
        //   191: isub
        //   192: invokestatic abs : (I)I
        //   195: iload #6
        //   197: aload_0
        //   198: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   201: getfield collapsedOffset : I
        //   204: isub
        //   205: invokestatic abs : (I)I
        //   208: if_icmpge -> 223
        //   211: aload_0
        //   212: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   215: getfield fitToContentsOffset : I
        //   218: istore #4
        //   220: goto -> 67
        //   223: aload_0
        //   224: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   227: getfield collapsedOffset : I
        //   230: istore #4
        //   232: goto -> 333
        //   235: iload #6
        //   237: aload_0
        //   238: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   241: getfield halfExpandedOffset : I
        //   244: if_icmpge -> 280
        //   247: iload #6
        //   249: iload #6
        //   251: aload_0
        //   252: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   255: getfield collapsedOffset : I
        //   258: isub
        //   259: invokestatic abs : (I)I
        //   262: if_icmpge -> 268
        //   265: goto -> 67
        //   268: aload_0
        //   269: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   272: getfield halfExpandedOffset : I
        //   275: istore #4
        //   277: goto -> 60
        //   280: iload #6
        //   282: aload_0
        //   283: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   286: getfield halfExpandedOffset : I
        //   289: isub
        //   290: invokestatic abs : (I)I
        //   293: iload #6
        //   295: aload_0
        //   296: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   299: getfield collapsedOffset : I
        //   302: isub
        //   303: invokestatic abs : (I)I
        //   306: if_icmpge -> 321
        //   309: aload_0
        //   310: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   313: getfield halfExpandedOffset : I
        //   316: istore #4
        //   318: goto -> 60
        //   321: aload_0
        //   322: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   325: getfield collapsedOffset : I
        //   328: istore #4
        //   330: goto -> 232
        //   333: aload_0
        //   334: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   337: getfield viewDragHelper : Landroid/support/v4/widget/ViewDragHelper;
        //   340: aload_1
        //   341: invokevirtual getLeft : ()I
        //   344: iload #4
        //   346: invokevirtual settleCapturedViewAt : (II)Z
        //   349: ifeq -> 381
        //   352: aload_0
        //   353: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   356: iconst_2
        //   357: invokevirtual setStateInternal : (I)V
        //   360: aload_1
        //   361: new android/support/design/widget/BottomSheetBehavior$SettleRunnable
        //   364: dup
        //   365: aload_0
        //   366: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   369: aload_1
        //   370: iload #5
        //   372: invokespecial <init> : (Landroid/support/design/widget/BottomSheetBehavior;Landroid/view/View;I)V
        //   375: invokestatic postOnAnimation : (Landroid/view/View;Ljava/lang/Runnable;)V
        //   378: goto -> 390
        //   381: aload_0
        //   382: getfield this$0 : Landroid/support/design/widget/BottomSheetBehavior;
        //   385: iload #5
        //   387: invokevirtual setStateInternal : (I)V
        //   390: return
      }
      
      public boolean tryCaptureView(@NonNull View param1View, int param1Int) {
        int i = BottomSheetBehavior.this.state;
        boolean bool = true;
        if (i == 1)
          return false; 
        if (BottomSheetBehavior.this.touchingScrollingChild)
          return false; 
        if (BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == param1Int) {
          View view = BottomSheetBehavior.this.nestedScrollingChildRef.get();
          if (view != null && view.canScrollVertically(-1))
            return false; 
        } 
        if (BottomSheetBehavior.this.viewRef == null || BottomSheetBehavior.this.viewRef.get() != param1View)
          bool = false; 
        return bool;
      }
    };
  
  private boolean fitToContents = true;
  
  int fitToContentsOffset;
  
  int halfExpandedOffset;
  
  boolean hideable;
  
  private boolean ignoreEvents;
  
  private Map<View, Integer> importantForAccessibilityMap;
  
  private int initialY;
  
  private int lastNestedScrollDy;
  
  private int lastPeekHeight;
  
  private float maximumVelocity;
  
  private boolean nestedScrolled;
  
  WeakReference<View> nestedScrollingChildRef;
  
  int parentHeight;
  
  private int peekHeight;
  
  private boolean peekHeightAuto;
  
  private int peekHeightMin;
  
  private boolean skipCollapsed;
  
  int state = 4;
  
  boolean touchingScrollingChild;
  
  private VelocityTracker velocityTracker;
  
  ViewDragHelper viewDragHelper;
  
  WeakReference<V> viewRef;
  
  public BottomSheetBehavior() {}
  
  public BottomSheetBehavior(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.BottomSheetBehavior_Layout);
    TypedValue typedValue = typedArray.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
    if (typedValue != null && typedValue.data == -1) {
      setPeekHeight(typedValue.data);
    } else {
      setPeekHeight(typedArray.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
    } 
    setHideable(typedArray.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
    setFitToContents(typedArray.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
    setSkipCollapsed(typedArray.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
    typedArray.recycle();
    this.maximumVelocity = ViewConfiguration.get(paramContext).getScaledMaximumFlingVelocity();
  }
  
  private void calculateCollapsedOffset() {
    if (this.fitToContents) {
      this.collapsedOffset = Math.max(this.parentHeight - this.lastPeekHeight, this.fitToContentsOffset);
    } else {
      this.collapsedOffset = this.parentHeight - this.lastPeekHeight;
    } 
  }
  
  public static <V extends View> BottomSheetBehavior<V> from(V paramV) {
    ViewGroup.LayoutParams layoutParams = paramV.getLayoutParams();
    if (!(layoutParams instanceof CoordinatorLayout.LayoutParams))
      throw new IllegalArgumentException("The view is not a child of CoordinatorLayout"); 
    CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)layoutParams).getBehavior();
    if (!(behavior instanceof BottomSheetBehavior))
      throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior"); 
    return (BottomSheetBehavior<V>)behavior;
  }
  
  private int getExpandedOffset() {
    boolean bool;
    if (this.fitToContents) {
      bool = this.fitToContentsOffset;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private float getYVelocity() {
    if (this.velocityTracker == null)
      return 0.0F; 
    this.velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
    return this.velocityTracker.getYVelocity(this.activePointerId);
  }
  
  private void reset() {
    this.activePointerId = -1;
    if (this.velocityTracker != null) {
      this.velocityTracker.recycle();
      this.velocityTracker = null;
    } 
  }
  
  private void updateImportantForAccessibility(boolean paramBoolean) {
    if (this.viewRef == null)
      return; 
    ViewParent viewParent = ((View)this.viewRef.get()).getParent();
    if (!(viewParent instanceof CoordinatorLayout))
      return; 
    CoordinatorLayout coordinatorLayout = (CoordinatorLayout)viewParent;
    int i = coordinatorLayout.getChildCount();
    if (Build.VERSION.SDK_INT >= 16 && paramBoolean)
      if (this.importantForAccessibilityMap == null) {
        this.importantForAccessibilityMap = new HashMap<>(i);
      } else {
        return;
      }  
    for (byte b = 0; b < i; b++) {
      View view = coordinatorLayout.getChildAt(b);
      if (view != this.viewRef.get())
        if (!paramBoolean) {
          if (this.importantForAccessibilityMap != null && this.importantForAccessibilityMap.containsKey(view))
            ViewCompat.setImportantForAccessibility(view, ((Integer)this.importantForAccessibilityMap.get(view)).intValue()); 
        } else {
          if (Build.VERSION.SDK_INT >= 16)
            this.importantForAccessibilityMap.put(view, Integer.valueOf(view.getImportantForAccessibility())); 
          ViewCompat.setImportantForAccessibility(view, 4);
        }  
    } 
    if (!paramBoolean)
      this.importantForAccessibilityMap = null; 
  }
  
  void dispatchOnSlide(int paramInt) {
    View view = (View)this.viewRef.get();
    if (view != null && this.callback != null)
      if (paramInt > this.collapsedOffset) {
        this.callback.onSlide(view, (this.collapsedOffset - paramInt) / (this.parentHeight - this.collapsedOffset));
      } else {
        this.callback.onSlide(view, (this.collapsedOffset - paramInt) / (this.collapsedOffset - getExpandedOffset()));
      }  
  }
  
  @VisibleForTesting
  View findScrollingChild(View paramView) {
    if (ViewCompat.isNestedScrollingEnabled(paramView))
      return paramView; 
    if (paramView instanceof ViewGroup) {
      ViewGroup viewGroup = (ViewGroup)paramView;
      byte b = 0;
      int i = viewGroup.getChildCount();
      while (b < i) {
        paramView = findScrollingChild(viewGroup.getChildAt(b));
        if (paramView != null)
          return paramView; 
        b++;
      } 
    } 
    return null;
  }
  
  public final int getPeekHeight() {
    int i;
    if (this.peekHeightAuto) {
      i = -1;
    } else {
      i = this.peekHeight;
    } 
    return i;
  }
  
  @VisibleForTesting
  int getPeekHeightMin() {
    return this.peekHeightMin;
  }
  
  public boolean getSkipCollapsed() {
    return this.skipCollapsed;
  }
  
  public final int getState() {
    return this.state;
  }
  
  public boolean isFitToContents() {
    return this.fitToContents;
  }
  
  public boolean isHideable() {
    return this.hideable;
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent) {
    boolean bool = paramV.isShown();
    boolean bool1 = false;
    if (!bool) {
      this.ignoreEvents = true;
      return false;
    } 
    int i = paramMotionEvent.getActionMasked();
    if (i == 0)
      reset(); 
    if (this.velocityTracker == null)
      this.velocityTracker = VelocityTracker.obtain(); 
    this.velocityTracker.addMovement(paramMotionEvent);
    V v = null;
    if (i != 3) {
      View view1;
      int j;
      View view2;
      switch (i) {
        case 0:
          j = (int)paramMotionEvent.getX();
          this.initialY = (int)paramMotionEvent.getY();
          if (this.nestedScrollingChildRef != null) {
            view2 = this.nestedScrollingChildRef.get();
          } else {
            view2 = null;
          } 
          if (view2 != null && paramCoordinatorLayout.isPointInChildBounds(view2, j, this.initialY)) {
            this.activePointerId = paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex());
            this.touchingScrollingChild = true;
          } 
          if (this.activePointerId == -1 && !paramCoordinatorLayout.isPointInChildBounds((View)paramV, j, this.initialY)) {
            bool = true;
          } else {
            bool = false;
          } 
          this.ignoreEvents = bool;
          break;
        case 1:
          this.touchingScrollingChild = false;
          this.activePointerId = -1;
          if (this.ignoreEvents) {
            this.ignoreEvents = false;
            return false;
          } 
          break;
      } 
      if (!this.ignoreEvents && this.viewDragHelper != null && this.viewDragHelper.shouldInterceptTouchEvent(paramMotionEvent))
        return true; 
      paramV = v;
      if (this.nestedScrollingChildRef != null)
        view1 = this.nestedScrollingChildRef.get(); 
      bool = bool1;
      if (i == 2) {
        bool = bool1;
        if (view1 != null) {
          bool = bool1;
          if (!this.ignoreEvents) {
            bool = bool1;
            if (this.state != 1) {
              bool = bool1;
              if (!paramCoordinatorLayout.isPointInChildBounds(view1, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) {
                bool = bool1;
                if (this.viewDragHelper != null) {
                  bool = bool1;
                  if (Math.abs(this.initialY - paramMotionEvent.getY()) > this.viewDragHelper.getTouchSlop())
                    bool = true; 
                } 
              } 
            } 
          } 
        } 
      } 
      return bool;
    } 
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt) {
    if (ViewCompat.getFitsSystemWindows((View)paramCoordinatorLayout) && !ViewCompat.getFitsSystemWindows((View)paramV))
      paramV.setFitsSystemWindows(true); 
    int i = paramV.getTop();
    paramCoordinatorLayout.onLayoutChild((View)paramV, paramInt);
    this.parentHeight = paramCoordinatorLayout.getHeight();
    if (this.peekHeightAuto) {
      if (this.peekHeightMin == 0)
        this.peekHeightMin = paramCoordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min); 
      this.lastPeekHeight = Math.max(this.peekHeightMin, this.parentHeight - paramCoordinatorLayout.getWidth() * 9 / 16);
    } else {
      this.lastPeekHeight = this.peekHeight;
    } 
    this.fitToContentsOffset = Math.max(0, this.parentHeight - paramV.getHeight());
    this.halfExpandedOffset = this.parentHeight / 2;
    calculateCollapsedOffset();
    if (this.state == 3) {
      ViewCompat.offsetTopAndBottom((View)paramV, getExpandedOffset());
    } else if (this.state == 6) {
      ViewCompat.offsetTopAndBottom((View)paramV, this.halfExpandedOffset);
    } else if (this.hideable && this.state == 5) {
      ViewCompat.offsetTopAndBottom((View)paramV, this.parentHeight);
    } else if (this.state == 4) {
      ViewCompat.offsetTopAndBottom((View)paramV, this.collapsedOffset);
    } else if (this.state == 1 || this.state == 2) {
      ViewCompat.offsetTopAndBottom((View)paramV, i - paramV.getTop());
    } 
    if (this.viewDragHelper == null)
      this.viewDragHelper = ViewDragHelper.create(paramCoordinatorLayout, this.dragCallback); 
    this.viewRef = new WeakReference<>(paramV);
    this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild((View)paramV));
    return true;
  }
  
  public boolean onNestedPreFling(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, float paramFloat1, float paramFloat2) {
    boolean bool;
    if (paramView == this.nestedScrollingChildRef.get() && (this.state != 3 || super.onNestedPreFling(paramCoordinatorLayout, paramV, paramView, paramFloat1, paramFloat2))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onNestedPreScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, int paramInt1, int paramInt2, @NonNull int[] paramArrayOfint, int paramInt3) {
    if (paramInt3 == 1)
      return; 
    if (paramView != (View)this.nestedScrollingChildRef.get())
      return; 
    paramInt3 = paramV.getTop();
    paramInt1 = paramInt3 - paramInt2;
    if (paramInt2 > 0) {
      if (paramInt1 < getExpandedOffset()) {
        paramArrayOfint[1] = paramInt3 - getExpandedOffset();
        ViewCompat.offsetTopAndBottom((View)paramV, -paramArrayOfint[1]);
        setStateInternal(3);
      } else {
        paramArrayOfint[1] = paramInt2;
        ViewCompat.offsetTopAndBottom((View)paramV, -paramInt2);
        setStateInternal(1);
      } 
    } else if (paramInt2 < 0 && !paramView.canScrollVertically(-1)) {
      if (paramInt1 <= this.collapsedOffset || this.hideable) {
        paramArrayOfint[1] = paramInt2;
        ViewCompat.offsetTopAndBottom((View)paramV, -paramInt2);
        setStateInternal(1);
      } else {
        paramArrayOfint[1] = paramInt3 - this.collapsedOffset;
        ViewCompat.offsetTopAndBottom((View)paramV, -paramArrayOfint[1]);
        setStateInternal(4);
      } 
    } 
    dispatchOnSlide(paramV.getTop());
    this.lastNestedScrollDy = paramInt2;
    this.nestedScrolled = true;
  }
  
  public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV, Parcelable paramParcelable) {
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramCoordinatorLayout, paramV, savedState.getSuperState());
    if (savedState.state == 1 || savedState.state == 2) {
      this.state = 4;
      return;
    } 
    this.state = savedState.state;
  }
  
  public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV) {
    return (Parcelable)new SavedState(super.onSaveInstanceState(paramCoordinatorLayout, paramV), this.state);
  }
  
  public boolean onStartNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2) {
    boolean bool = false;
    this.lastNestedScrollDy = 0;
    this.nestedScrolled = false;
    if ((paramInt1 & 0x2) != 0)
      bool = true; 
    return bool;
  }
  
  public void onStopNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, int paramInt) {
    int i = paramV.getTop();
    paramInt = getExpandedOffset();
    byte b = 3;
    if (i == paramInt) {
      setStateInternal(3);
      return;
    } 
    if (paramView != this.nestedScrollingChildRef.get() || !this.nestedScrolled)
      return; 
    if (this.lastNestedScrollDy > 0) {
      paramInt = getExpandedOffset();
    } else if (this.hideable && shouldHide((View)paramV, getYVelocity())) {
      paramInt = this.parentHeight;
      b = 5;
    } else if (this.lastNestedScrollDy == 0) {
      paramInt = paramV.getTop();
      if (this.fitToContents) {
        if (Math.abs(paramInt - this.fitToContentsOffset) < Math.abs(paramInt - this.collapsedOffset)) {
          paramInt = this.fitToContentsOffset;
        } else {
          paramInt = this.collapsedOffset;
          b = 4;
        } 
      } else if (paramInt < this.halfExpandedOffset) {
        if (paramInt < Math.abs(paramInt - this.collapsedOffset)) {
          paramInt = 0;
        } else {
          paramInt = this.halfExpandedOffset;
          b = 6;
        } 
      } else {
        if (Math.abs(paramInt - this.halfExpandedOffset) < Math.abs(paramInt - this.collapsedOffset)) {
          paramInt = this.halfExpandedOffset;
        } else {
          paramInt = this.collapsedOffset;
          b = 4;
        } 
        b = 6;
      } 
    } else {
      paramInt = this.collapsedOffset;
      b = 4;
    } 
    if (this.viewDragHelper.smoothSlideViewTo((View)paramV, paramV.getLeft(), paramInt)) {
      setStateInternal(2);
      ViewCompat.postOnAnimation((View)paramV, new SettleRunnable((View)paramV, b));
    } else {
      setStateInternal(b);
    } 
    this.nestedScrolled = false;
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent) {
    if (!paramV.isShown())
      return false; 
    int i = paramMotionEvent.getActionMasked();
    if (this.state == 1 && i == 0)
      return true; 
    if (this.viewDragHelper != null)
      this.viewDragHelper.processTouchEvent(paramMotionEvent); 
    if (i == 0)
      reset(); 
    if (this.velocityTracker == null)
      this.velocityTracker = VelocityTracker.obtain(); 
    this.velocityTracker.addMovement(paramMotionEvent);
    if (i == 2 && !this.ignoreEvents && Math.abs(this.initialY - paramMotionEvent.getY()) > this.viewDragHelper.getTouchSlop())
      this.viewDragHelper.captureChildView((View)paramV, paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex())); 
    return this.ignoreEvents ^ true;
  }
  
  public void setBottomSheetCallback(BottomSheetCallback paramBottomSheetCallback) {
    this.callback = paramBottomSheetCallback;
  }
  
  public void setFitToContents(boolean paramBoolean) {
    int i;
    if (this.fitToContents == paramBoolean)
      return; 
    this.fitToContents = paramBoolean;
    if (this.viewRef != null)
      calculateCollapsedOffset(); 
    if (this.fitToContents && this.state == 6) {
      i = 3;
    } else {
      i = this.state;
    } 
    setStateInternal(i);
  }
  
  public void setHideable(boolean paramBoolean) {
    this.hideable = paramBoolean;
  }
  
  public final void setPeekHeight(int paramInt) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: iload_1
    //   3: iconst_m1
    //   4: if_icmpne -> 24
    //   7: aload_0
    //   8: getfield peekHeightAuto : Z
    //   11: ifne -> 42
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield peekHeightAuto : Z
    //   19: iload_2
    //   20: istore_1
    //   21: goto -> 73
    //   24: aload_0
    //   25: getfield peekHeightAuto : Z
    //   28: ifne -> 47
    //   31: aload_0
    //   32: getfield peekHeight : I
    //   35: iload_1
    //   36: if_icmpeq -> 42
    //   39: goto -> 47
    //   42: iconst_0
    //   43: istore_1
    //   44: goto -> 73
    //   47: aload_0
    //   48: iconst_0
    //   49: putfield peekHeightAuto : Z
    //   52: aload_0
    //   53: iconst_0
    //   54: iload_1
    //   55: invokestatic max : (II)I
    //   58: putfield peekHeight : I
    //   61: aload_0
    //   62: aload_0
    //   63: getfield parentHeight : I
    //   66: iload_1
    //   67: isub
    //   68: putfield collapsedOffset : I
    //   71: iload_2
    //   72: istore_1
    //   73: iload_1
    //   74: ifeq -> 111
    //   77: aload_0
    //   78: getfield state : I
    //   81: iconst_4
    //   82: if_icmpne -> 111
    //   85: aload_0
    //   86: getfield viewRef : Ljava/lang/ref/WeakReference;
    //   89: ifnull -> 111
    //   92: aload_0
    //   93: getfield viewRef : Ljava/lang/ref/WeakReference;
    //   96: invokevirtual get : ()Ljava/lang/Object;
    //   99: checkcast android/view/View
    //   102: astore_3
    //   103: aload_3
    //   104: ifnull -> 111
    //   107: aload_3
    //   108: invokevirtual requestLayout : ()V
    //   111: return
  }
  
  public void setSkipCollapsed(boolean paramBoolean) {
    this.skipCollapsed = paramBoolean;
  }
  
  public final void setState(final int finalState) {
    if (finalState == this.state)
      return; 
    if (this.viewRef == null) {
      if (finalState == 4 || finalState == 3 || finalState == 6 || (this.hideable && finalState == 5))
        this.state = finalState; 
      return;
    } 
    final View child = (View)this.viewRef.get();
    if (view == null)
      return; 
    ViewParent viewParent = view.getParent();
    if (viewParent != null && viewParent.isLayoutRequested() && ViewCompat.isAttachedToWindow(view)) {
      view.post(new Runnable() {
            public void run() {
              BottomSheetBehavior.this.startSettlingAnimation(child, finalState);
            }
          });
    } else {
      startSettlingAnimation(view, finalState);
    } 
  }
  
  void setStateInternal(int paramInt) {
    if (this.state == paramInt)
      return; 
    this.state = paramInt;
    if (paramInt == 6 || paramInt == 3) {
      updateImportantForAccessibility(true);
    } else if (paramInt == 5 || paramInt == 4) {
      updateImportantForAccessibility(false);
    } 
    View view = (View)this.viewRef.get();
    if (view != null && this.callback != null)
      this.callback.onStateChanged(view, paramInt); 
  }
  
  boolean shouldHide(View paramView, float paramFloat) {
    boolean bool = this.skipCollapsed;
    boolean bool1 = true;
    if (bool)
      return true; 
    if (paramView.getTop() < this.collapsedOffset)
      return false; 
    if (Math.abs(paramView.getTop() + paramFloat * 0.1F - this.collapsedOffset) / this.peekHeight <= 0.5F)
      bool1 = false; 
    return bool1;
  }
  
  void startSettlingAnimation(View paramView, int paramInt) {
    StringBuilder stringBuilder;
    int i;
    if (paramInt == 4) {
      i = this.collapsedOffset;
    } else if (paramInt == 6) {
      i = this.halfExpandedOffset;
      if (this.fitToContents && i <= this.fitToContentsOffset) {
        i = this.fitToContentsOffset;
        paramInt = 3;
      } 
    } else if (paramInt == 3) {
      i = getExpandedOffset();
    } else if (this.hideable && paramInt == 5) {
      i = this.parentHeight;
    } else {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Illegal state argument: ");
      stringBuilder.append(paramInt);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    if (this.viewDragHelper.smoothSlideViewTo((View)stringBuilder, stringBuilder.getLeft(), i)) {
      setStateInternal(2);
      ViewCompat.postOnAnimation((View)stringBuilder, new SettleRunnable((View)stringBuilder, paramInt));
    } else {
      setStateInternal(paramInt);
    } 
  }
  
  public static abstract class BottomSheetCallback {
    public abstract void onSlide(@NonNull View param1View, float param1Float);
    
    public abstract void onStateChanged(@NonNull View param1View, int param1Int);
  }
  
  protected static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public BottomSheetBehavior.SavedState createFromParcel(Parcel param2Parcel) {
          return new BottomSheetBehavior.SavedState(param2Parcel, null);
        }
        
        public BottomSheetBehavior.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new BottomSheetBehavior.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public BottomSheetBehavior.SavedState[] newArray(int param2Int) {
          return new BottomSheetBehavior.SavedState[param2Int];
        }
      };
    
    final int state;
    
    public SavedState(Parcel param1Parcel) {
      this(param1Parcel, (ClassLoader)null);
    }
    
    public SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      this.state = param1Parcel.readInt();
    }
    
    public SavedState(Parcelable param1Parcelable, int param1Int) {
      super(param1Parcelable);
      this.state = param1Int;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.state);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public BottomSheetBehavior.SavedState createFromParcel(Parcel param1Parcel) {
      return new BottomSheetBehavior.SavedState(param1Parcel, null);
    }
    
    public BottomSheetBehavior.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new BottomSheetBehavior.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public BottomSheetBehavior.SavedState[] newArray(int param1Int) {
      return new BottomSheetBehavior.SavedState[param1Int];
    }
  }
  
  private class SettleRunnable implements Runnable {
    private final int targetState;
    
    private final View view;
    
    SettleRunnable(View param1View, int param1Int) {
      this.view = param1View;
      this.targetState = param1Int;
    }
    
    public void run() {
      if (BottomSheetBehavior.this.viewDragHelper != null && BottomSheetBehavior.this.viewDragHelper.continueSettling(true)) {
        ViewCompat.postOnAnimation(this.view, this);
      } else {
        BottomSheetBehavior.this.setStateInternal(this.targetState);
      } 
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface State {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\BottomSheetBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */