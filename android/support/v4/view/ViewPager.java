package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
  private static final int CLOSE_ENOUGH = 2;
  
  private static final Comparator<ItemInfo> COMPARATOR;
  
  private static final boolean DEBUG = false;
  
  private static final int DEFAULT_GUTTER_SIZE = 16;
  
  private static final int DEFAULT_OFFSCREEN_PAGES = 1;
  
  private static final int DRAW_ORDER_DEFAULT = 0;
  
  private static final int DRAW_ORDER_FORWARD = 1;
  
  private static final int DRAW_ORDER_REVERSE = 2;
  
  private static final int INVALID_POINTER = -1;
  
  static final int[] LAYOUT_ATTRS = new int[] { 16842931 };
  
  private static final int MAX_SETTLE_DURATION = 600;
  
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  
  private static final int MIN_FLING_VELOCITY = 400;
  
  public static final int SCROLL_STATE_DRAGGING = 1;
  
  public static final int SCROLL_STATE_IDLE = 0;
  
  public static final int SCROLL_STATE_SETTLING = 2;
  
  private static final String TAG = "ViewPager";
  
  private static final boolean USE_CACHE = false;
  
  private static final Interpolator sInterpolator;
  
  private static final ViewPositionComparator sPositionComparator;
  
  private int mActivePointerId = -1;
  
  PagerAdapter mAdapter;
  
  private List<OnAdapterChangeListener> mAdapterChangeListeners;
  
  private int mBottomPageBounds;
  
  private boolean mCalledSuper;
  
  private int mChildHeightMeasureSpec;
  
  private int mChildWidthMeasureSpec;
  
  private int mCloseEnough;
  
  int mCurItem;
  
  private int mDecorChildCount;
  
  private int mDefaultGutterSize;
  
  private int mDrawingOrder;
  
  private ArrayList<View> mDrawingOrderedChildren;
  
  private final Runnable mEndScrollRunnable = new Runnable() {
      public void run() {
        ViewPager.this.setScrollState(0);
        ViewPager.this.populate();
      }
    };
  
  private int mExpectedAdapterCount;
  
  private long mFakeDragBeginTime;
  
  private boolean mFakeDragging;
  
  private boolean mFirstLayout = true;
  
  private float mFirstOffset = -3.4028235E38F;
  
  private int mFlingDistance;
  
  private int mGutterSize;
  
  private boolean mInLayout;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  private OnPageChangeListener mInternalPageChangeListener;
  
  private boolean mIsBeingDragged;
  
  private boolean mIsScrollStarted;
  
  private boolean mIsUnableToDrag;
  
  private final ArrayList<ItemInfo> mItems = new ArrayList<>();
  
  private float mLastMotionX;
  
  private float mLastMotionY;
  
  private float mLastOffset = Float.MAX_VALUE;
  
  private EdgeEffect mLeftEdge;
  
  private Drawable mMarginDrawable;
  
  private int mMaximumVelocity;
  
  private int mMinimumVelocity;
  
  private boolean mNeedCalculatePageOffsets = false;
  
  private PagerObserver mObserver;
  
  private int mOffscreenPageLimit = 1;
  
  private OnPageChangeListener mOnPageChangeListener;
  
  private List<OnPageChangeListener> mOnPageChangeListeners;
  
  private int mPageMargin;
  
  private PageTransformer mPageTransformer;
  
  private int mPageTransformerLayerType;
  
  private boolean mPopulatePending;
  
  private Parcelable mRestoredAdapterState = null;
  
  private ClassLoader mRestoredClassLoader = null;
  
  private int mRestoredCurItem = -1;
  
  private EdgeEffect mRightEdge;
  
  private int mScrollState = 0;
  
  private Scroller mScroller;
  
  private boolean mScrollingCacheEnabled;
  
  private final ItemInfo mTempItem = new ItemInfo();
  
  private final Rect mTempRect = new Rect();
  
  private int mTopPageBounds;
  
  private int mTouchSlop;
  
  private VelocityTracker mVelocityTracker;
  
  static {
    COMPARATOR = new Comparator<ItemInfo>() {
        public int compare(ViewPager.ItemInfo param1ItemInfo1, ViewPager.ItemInfo param1ItemInfo2) {
          return param1ItemInfo1.position - param1ItemInfo2.position;
        }
      };
    sInterpolator = new Interpolator() {
        public float getInterpolation(float param1Float) {
          param1Float--;
          return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
        }
      };
    sPositionComparator = new ViewPositionComparator();
  }
  
  public ViewPager(@NonNull Context paramContext) {
    super(paramContext);
    initViewPager();
  }
  
  public ViewPager(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initViewPager();
  }
  
  private void calculatePageOffsets(ItemInfo paramItemInfo1, int paramInt, ItemInfo paramItemInfo2) {
    float f1;
    int i = this.mAdapter.getCount();
    int j = getClientWidth();
    if (j > 0) {
      f1 = this.mPageMargin / j;
    } else {
      f1 = 0.0F;
    } 
    if (paramItemInfo2 != null) {
      j = paramItemInfo2.position;
      if (j < paramItemInfo1.position) {
        f2 = paramItemInfo2.offset + paramItemInfo2.widthFactor + f1;
        j++;
        byte b = 0;
        while (j <= paramItemInfo1.position && b < this.mItems.size()) {
          int n;
          float f;
          paramItemInfo2 = this.mItems.get(b);
          while (true) {
            n = j;
            f = f2;
            if (j > paramItemInfo2.position) {
              n = j;
              f = f2;
              if (b < this.mItems.size() - 1) {
                paramItemInfo2 = this.mItems.get(++b);
                continue;
              } 
            } 
            break;
          } 
          while (n < paramItemInfo2.position) {
            f += this.mAdapter.getPageWidth(n) + f1;
            n++;
          } 
          paramItemInfo2.offset = f;
          f2 = f + paramItemInfo2.widthFactor + f1;
          j = n + 1;
        } 
      } else if (j > paramItemInfo1.position) {
        int n = this.mItems.size() - 1;
        f2 = paramItemInfo2.offset;
        while (--j >= paramItemInfo1.position && n >= 0) {
          int i1;
          float f;
          paramItemInfo2 = this.mItems.get(n);
          while (true) {
            i1 = j;
            f = f2;
            if (j < paramItemInfo2.position) {
              i1 = j;
              f = f2;
              if (n > 0) {
                paramItemInfo2 = this.mItems.get(--n);
                continue;
              } 
            } 
            break;
          } 
          while (i1 > paramItemInfo2.position) {
            f -= this.mAdapter.getPageWidth(i1) + f1;
            i1--;
          } 
          f2 = f - paramItemInfo2.widthFactor + f1;
          paramItemInfo2.offset = f2;
          j = i1 - 1;
        } 
      } 
    } 
    int m = this.mItems.size();
    float f3 = paramItemInfo1.offset;
    j = paramItemInfo1.position - 1;
    if (paramItemInfo1.position == 0) {
      f2 = paramItemInfo1.offset;
    } else {
      f2 = -3.4028235E38F;
    } 
    this.mFirstOffset = f2;
    int k = paramItemInfo1.position;
    if (k == --i) {
      f2 = paramItemInfo1.offset + paramItemInfo1.widthFactor - 1.0F;
    } else {
      f2 = Float.MAX_VALUE;
    } 
    this.mLastOffset = f2;
    k = paramInt - 1;
    float f2 = f3;
    while (k >= 0) {
      paramItemInfo2 = this.mItems.get(k);
      while (j > paramItemInfo2.position) {
        f2 -= this.mAdapter.getPageWidth(j) + f1;
        j--;
      } 
      f2 -= paramItemInfo2.widthFactor + f1;
      paramItemInfo2.offset = f2;
      if (paramItemInfo2.position == 0)
        this.mFirstOffset = f2; 
      k--;
      j--;
    } 
    f2 = paramItemInfo1.offset + paramItemInfo1.widthFactor + f1;
    k = paramItemInfo1.position + 1;
    j = paramInt + 1;
    for (paramInt = k; j < m; paramInt++) {
      paramItemInfo1 = this.mItems.get(j);
      while (paramInt < paramItemInfo1.position) {
        f2 += this.mAdapter.getPageWidth(paramInt) + f1;
        paramInt++;
      } 
      if (paramItemInfo1.position == i)
        this.mLastOffset = paramItemInfo1.widthFactor + f2 - 1.0F; 
      paramItemInfo1.offset = f2;
      f2 += paramItemInfo1.widthFactor + f1;
      j++;
    } 
    this.mNeedCalculatePageOffsets = false;
  }
  
  private void completeScroll(boolean paramBoolean) {
    if (this.mScrollState == 2) {
      b1 = 1;
    } else {
      b1 = 0;
    } 
    if (b1) {
      setScrollingCacheEnabled(false);
      if ((this.mScroller.isFinished() ^ true) != 0) {
        this.mScroller.abortAnimation();
        int i = getScrollX();
        int j = getScrollY();
        int k = this.mScroller.getCurrX();
        int m = this.mScroller.getCurrY();
        if (i != k || j != m) {
          scrollTo(k, m);
          if (k != i)
            pageScrolled(k); 
        } 
      } 
    } 
    this.mPopulatePending = false;
    byte b2 = 0;
    byte b3 = b1;
    for (byte b1 = b2; b1 < this.mItems.size(); b1++) {
      ItemInfo itemInfo = this.mItems.get(b1);
      if (itemInfo.scrolling) {
        itemInfo.scrolling = false;
        b3 = 1;
      } 
    } 
    if (b3 != 0)
      if (paramBoolean) {
        ViewCompat.postOnAnimation((View)this, this.mEndScrollRunnable);
      } else {
        this.mEndScrollRunnable.run();
      }  
  }
  
  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3) {
    if (Math.abs(paramInt3) > this.mFlingDistance && Math.abs(paramInt2) > this.mMinimumVelocity) {
      if (paramInt2 <= 0)
        paramInt1++; 
    } else {
      float f;
      if (paramInt1 >= this.mCurItem) {
        f = 0.4F;
      } else {
        f = 0.6F;
      } 
      paramInt1 += (int)(paramFloat + f);
    } 
    paramInt2 = paramInt1;
    if (this.mItems.size() > 0) {
      ItemInfo itemInfo1 = this.mItems.get(0);
      ItemInfo itemInfo2 = this.mItems.get(this.mItems.size() - 1);
      paramInt2 = Math.max(itemInfo1.position, Math.min(paramInt1, itemInfo2.position));
    } 
    return paramInt2;
  }
  
  private void dispatchOnPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    if (this.mOnPageChangeListener != null)
      this.mOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2); 
    if (this.mOnPageChangeListeners != null) {
      byte b = 0;
      int i = this.mOnPageChangeListeners.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2); 
        b++;
      } 
    } 
    if (this.mInternalPageChangeListener != null)
      this.mInternalPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2); 
  }
  
  private void dispatchOnPageSelected(int paramInt) {
    if (this.mOnPageChangeListener != null)
      this.mOnPageChangeListener.onPageSelected(paramInt); 
    if (this.mOnPageChangeListeners != null) {
      byte b = 0;
      int i = this.mOnPageChangeListeners.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageSelected(paramInt); 
        b++;
      } 
    } 
    if (this.mInternalPageChangeListener != null)
      this.mInternalPageChangeListener.onPageSelected(paramInt); 
  }
  
  private void dispatchOnScrollStateChanged(int paramInt) {
    if (this.mOnPageChangeListener != null)
      this.mOnPageChangeListener.onPageScrollStateChanged(paramInt); 
    if (this.mOnPageChangeListeners != null) {
      byte b = 0;
      int i = this.mOnPageChangeListeners.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageScrollStateChanged(paramInt); 
        b++;
      } 
    } 
    if (this.mInternalPageChangeListener != null)
      this.mInternalPageChangeListener.onPageScrollStateChanged(paramInt); 
  }
  
  private void enableLayers(boolean paramBoolean) {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      boolean bool;
      if (paramBoolean) {
        bool = this.mPageTransformerLayerType;
      } else {
        bool = false;
      } 
      getChildAt(b).setLayerType(bool, null);
    } 
  }
  
  private void endDrag() {
    this.mIsBeingDragged = false;
    this.mIsUnableToDrag = false;
    if (this.mVelocityTracker != null) {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    } 
  }
  
  private Rect getChildRectInPagerCoordinates(Rect paramRect, View paramView) {
    Rect rect = paramRect;
    if (paramRect == null)
      rect = new Rect(); 
    if (paramView == null) {
      rect.set(0, 0, 0, 0);
      return rect;
    } 
    rect.left = paramView.getLeft();
    rect.right = paramView.getRight();
    rect.top = paramView.getTop();
    rect.bottom = paramView.getBottom();
    ViewParent viewParent = paramView.getParent();
    while (viewParent instanceof ViewGroup && viewParent != this) {
      ViewGroup viewGroup = (ViewGroup)viewParent;
      rect.left += viewGroup.getLeft();
      rect.right += viewGroup.getRight();
      rect.top += viewGroup.getTop();
      rect.bottom += viewGroup.getBottom();
      ViewParent viewParent1 = viewGroup.getParent();
    } 
    return rect;
  }
  
  private int getClientWidth() {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private ItemInfo infoForCurrentScrollPosition() {
    float f1;
    float f2;
    int i = getClientWidth();
    if (i > 0) {
      f1 = getScrollX() / i;
    } else {
      f1 = 0.0F;
    } 
    if (i > 0) {
      f2 = this.mPageMargin / i;
    } else {
      f2 = 0.0F;
    } 
    float f3 = 0.0F;
    float f4 = f3;
    i = 0;
    int j = -1;
    ItemInfo itemInfo = null;
    boolean bool = true;
    while (i < this.mItems.size()) {
      ItemInfo itemInfo1 = this.mItems.get(i);
      int k = i;
      ItemInfo itemInfo2 = itemInfo1;
      if (!bool) {
        int m = itemInfo1.position;
        j++;
        k = i;
        itemInfo2 = itemInfo1;
        if (m != j) {
          itemInfo2 = this.mTempItem;
          itemInfo2.offset = f3 + f4 + f2;
          itemInfo2.position = j;
          itemInfo2.widthFactor = this.mAdapter.getPageWidth(itemInfo2.position);
          k = i - 1;
        } 
      } 
      f3 = itemInfo2.offset;
      f4 = itemInfo2.widthFactor;
      if (bool || f1 >= f3) {
        if (f1 < f4 + f3 + f2 || k == this.mItems.size() - 1)
          return itemInfo2; 
        j = itemInfo2.position;
        f4 = itemInfo2.widthFactor;
        i = k + 1;
        bool = false;
        itemInfo = itemInfo2;
        continue;
      } 
      return itemInfo;
    } 
    return itemInfo;
  }
  
  private static boolean isDecorView(@NonNull View paramView) {
    boolean bool;
    if (paramView.getClass().getAnnotation(DecorView.class) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isGutterDrag(float paramFloat1, float paramFloat2) {
    boolean bool;
    if ((paramFloat1 < this.mGutterSize && paramFloat2 > 0.0F) || (paramFloat1 > (getWidth() - this.mGutterSize) && paramFloat2 < 0.0F)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == this.mActivePointerId) {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      } 
      this.mLastMotionX = paramMotionEvent.getX(i);
      this.mActivePointerId = paramMotionEvent.getPointerId(i);
      if (this.mVelocityTracker != null)
        this.mVelocityTracker.clear(); 
    } 
  }
  
  private boolean pageScrolled(int paramInt) {
    if (this.mItems.size() == 0) {
      if (this.mFirstLayout)
        return false; 
      this.mCalledSuper = false;
      onPageScrolled(0, 0.0F, 0);
      if (!this.mCalledSuper)
        throw new IllegalStateException("onPageScrolled did not call superclass implementation"); 
      return false;
    } 
    ItemInfo itemInfo = infoForCurrentScrollPosition();
    int i = getClientWidth();
    int j = this.mPageMargin;
    float f1 = this.mPageMargin;
    float f2 = i;
    f1 /= f2;
    int k = itemInfo.position;
    f2 = (paramInt / f2 - itemInfo.offset) / (itemInfo.widthFactor + f1);
    paramInt = (int)((j + i) * f2);
    this.mCalledSuper = false;
    onPageScrolled(k, f2, paramInt);
    if (!this.mCalledSuper)
      throw new IllegalStateException("onPageScrolled did not call superclass implementation"); 
    return true;
  }
  
  private boolean performDrag(float paramFloat) {
    boolean bool4;
    float f1 = this.mLastMotionX;
    this.mLastMotionX = paramFloat;
    float f2 = getScrollX() + f1 - paramFloat;
    float f3 = getClientWidth();
    paramFloat = this.mFirstOffset * f3;
    f1 = this.mLastOffset * f3;
    ArrayList<ItemInfo> arrayList = this.mItems;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    ItemInfo itemInfo1 = arrayList.get(0);
    ItemInfo itemInfo2 = this.mItems.get(this.mItems.size() - 1);
    if (itemInfo1.position != 0) {
      paramFloat = itemInfo1.offset * f3;
      i = 0;
    } else {
      i = 1;
    } 
    if (itemInfo2.position != this.mAdapter.getCount() - 1) {
      f1 = itemInfo2.offset * f3;
      bool4 = false;
    } else {
      bool4 = true;
    } 
    if (f2 < paramFloat) {
      if (i) {
        this.mLeftEdge.onPull(Math.abs(paramFloat - f2) / f3);
        bool3 = true;
      } 
    } else {
      bool3 = bool2;
      paramFloat = f2;
      if (f2 > f1) {
        bool3 = bool1;
        if (bool4) {
          this.mRightEdge.onPull(Math.abs(f2 - f1) / f3);
          bool3 = true;
        } 
        paramFloat = f1;
      } 
    } 
    f1 = this.mLastMotionX;
    int i = (int)paramFloat;
    this.mLastMotionX = f1 + paramFloat - i;
    scrollTo(i, getScrollY());
    pageScrolled(i);
    return bool3;
  }
  
  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt2 > 0 && !this.mItems.isEmpty()) {
      if (!this.mScroller.isFinished()) {
        this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
      } else {
        int i = getPaddingLeft();
        int j = getPaddingRight();
        int k = getPaddingLeft();
        int m = getPaddingRight();
        scrollTo((int)(getScrollX() / (paramInt2 - k - m + paramInt4) * (paramInt1 - i - j + paramInt3)), getScrollY());
      } 
    } else {
      float f;
      ItemInfo itemInfo = infoForPosition(this.mCurItem);
      if (itemInfo != null) {
        f = Math.min(itemInfo.offset, this.mLastOffset);
      } else {
        f = 0.0F;
      } 
      paramInt1 = (int)(f * (paramInt1 - getPaddingLeft() - getPaddingRight()));
      if (paramInt1 != getScrollX()) {
        completeScroll(false);
        scrollTo(paramInt1, getScrollY());
      } 
    } 
  }
  
  private void removeNonDecorViews() {
    for (int i = 0; i < getChildCount(); i = j + 1) {
      int j = i;
      if (!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor) {
        removeViewAt(i);
        j = i - 1;
      } 
    } 
  }
  
  private void requestParentDisallowInterceptTouchEvent(boolean paramBoolean) {
    ViewParent viewParent = getParent();
    if (viewParent != null)
      viewParent.requestDisallowInterceptTouchEvent(paramBoolean); 
  }
  
  private boolean resetTouch() {
    this.mActivePointerId = -1;
    endDrag();
    this.mLeftEdge.onRelease();
    this.mRightEdge.onRelease();
    return (this.mLeftEdge.isFinished() || this.mRightEdge.isFinished());
  }
  
  private void scrollToItem(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2) {
    boolean bool;
    ItemInfo itemInfo = infoForPosition(paramInt1);
    if (itemInfo != null) {
      bool = (int)(getClientWidth() * Math.max(this.mFirstOffset, Math.min(itemInfo.offset, this.mLastOffset)));
    } else {
      bool = false;
    } 
    if (paramBoolean1) {
      smoothScrollTo(bool, 0, paramInt2);
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1); 
    } else {
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1); 
      completeScroll(false);
      scrollTo(bool, 0);
      pageScrolled(bool);
    } 
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean) {
    if (this.mScrollingCacheEnabled != paramBoolean)
      this.mScrollingCacheEnabled = paramBoolean; 
  }
  
  private void sortChildDrawingOrder() {
    if (this.mDrawingOrder != 0) {
      if (this.mDrawingOrderedChildren == null) {
        this.mDrawingOrderedChildren = new ArrayList<>();
      } else {
        this.mDrawingOrderedChildren.clear();
      } 
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = getChildAt(b);
        this.mDrawingOrderedChildren.add(view);
      } 
      Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
    } 
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2) {
    int i = paramArrayList.size();
    int j = getDescendantFocusability();
    if (j != 393216)
      for (byte b = 0; b < getChildCount(); b++) {
        View view = getChildAt(b);
        if (view.getVisibility() == 0) {
          ItemInfo itemInfo = infoForChild(view);
          if (itemInfo != null && itemInfo.position == this.mCurItem)
            view.addFocusables(paramArrayList, paramInt1, paramInt2); 
        } 
      }  
    if (j != 262144 || i == paramArrayList.size()) {
      if (!isFocusable())
        return; 
      if ((paramInt2 & 0x1) == 1 && isInTouchMode() && !isFocusableInTouchMode())
        return; 
      if (paramArrayList != null)
        paramArrayList.add(this); 
    } 
  }
  
  ItemInfo addNewItem(int paramInt1, int paramInt2) {
    ItemInfo itemInfo = new ItemInfo();
    itemInfo.position = paramInt1;
    itemInfo.object = this.mAdapter.instantiateItem(this, paramInt1);
    itemInfo.widthFactor = this.mAdapter.getPageWidth(paramInt1);
    if (paramInt2 < 0 || paramInt2 >= this.mItems.size()) {
      this.mItems.add(itemInfo);
      return itemInfo;
    } 
    this.mItems.add(paramInt2, itemInfo);
    return itemInfo;
  }
  
  public void addOnAdapterChangeListener(@NonNull OnAdapterChangeListener paramOnAdapterChangeListener) {
    if (this.mAdapterChangeListeners == null)
      this.mAdapterChangeListeners = new ArrayList<>(); 
    this.mAdapterChangeListeners.add(paramOnAdapterChangeListener);
  }
  
  public void addOnPageChangeListener(@NonNull OnPageChangeListener paramOnPageChangeListener) {
    if (this.mOnPageChangeListeners == null)
      this.mOnPageChangeListeners = new ArrayList<>(); 
    this.mOnPageChangeListeners.add(paramOnPageChangeListener);
  }
  
  public void addTouchables(ArrayList<View> paramArrayList) {
    for (byte b = 0; b < getChildCount(); b++) {
      View view = getChildAt(b);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem)
          view.addTouchables(paramArrayList); 
      } 
    } 
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    ViewGroup.LayoutParams layoutParams = paramLayoutParams;
    if (!checkLayoutParams(paramLayoutParams))
      layoutParams = generateLayoutParams(paramLayoutParams); 
    paramLayoutParams = layoutParams;
    ((LayoutParams)paramLayoutParams).isDecor |= isDecorView(paramView);
    if (this.mInLayout) {
      if (paramLayoutParams != null && ((LayoutParams)paramLayoutParams).isDecor)
        throw new IllegalStateException("Cannot add pager decor view during layout"); 
      ((LayoutParams)paramLayoutParams).needsMeasure = true;
      addViewInLayout(paramView, paramInt, layoutParams);
    } else {
      super.addView(paramView, paramInt, layoutParams);
    } 
  }
  
  public boolean arrowScroll(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual findFocus : ()Landroid/view/View;
    //   4: astore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: aconst_null
    //   8: astore #4
    //   10: aload_2
    //   11: aload_0
    //   12: if_acmpne -> 22
    //   15: aload #4
    //   17: astore #5
    //   19: goto -> 193
    //   22: aload_2
    //   23: ifnull -> 190
    //   26: aload_2
    //   27: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   30: astore #5
    //   32: aload #5
    //   34: instanceof android/view/ViewGroup
    //   37: ifeq -> 64
    //   40: aload #5
    //   42: aload_0
    //   43: if_acmpne -> 52
    //   46: iconst_1
    //   47: istore #6
    //   49: goto -> 67
    //   52: aload #5
    //   54: invokeinterface getParent : ()Landroid/view/ViewParent;
    //   59: astore #5
    //   61: goto -> 32
    //   64: iconst_0
    //   65: istore #6
    //   67: iload #6
    //   69: ifne -> 190
    //   72: new java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial <init> : ()V
    //   79: astore #7
    //   81: aload #7
    //   83: aload_2
    //   84: invokevirtual getClass : ()Ljava/lang/Class;
    //   87: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload_2
    //   95: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   98: astore #5
    //   100: aload #5
    //   102: instanceof android/view/ViewGroup
    //   105: ifeq -> 143
    //   108: aload #7
    //   110: ldc_w ' => '
    //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload #7
    //   119: aload #5
    //   121: invokevirtual getClass : ()Ljava/lang/Class;
    //   124: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload #5
    //   133: invokeinterface getParent : ()Landroid/view/ViewParent;
    //   138: astore #5
    //   140: goto -> 100
    //   143: new java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial <init> : ()V
    //   150: astore #5
    //   152: aload #5
    //   154: ldc_w 'arrowScroll tried to find focus based on non-child current focused view '
    //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload #5
    //   163: aload #7
    //   165: invokevirtual toString : ()Ljava/lang/String;
    //   168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: ldc 'ViewPager'
    //   174: aload #5
    //   176: invokevirtual toString : ()Ljava/lang/String;
    //   179: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   182: pop
    //   183: aload #4
    //   185: astore #5
    //   187: goto -> 193
    //   190: aload_2
    //   191: astore #5
    //   193: invokestatic getInstance : ()Landroid/view/FocusFinder;
    //   196: aload_0
    //   197: aload #5
    //   199: iload_1
    //   200: invokevirtual findNextFocus : (Landroid/view/ViewGroup;Landroid/view/View;I)Landroid/view/View;
    //   203: astore #4
    //   205: aload #4
    //   207: ifnull -> 347
    //   210: aload #4
    //   212: aload #5
    //   214: if_acmpeq -> 347
    //   217: iload_1
    //   218: bipush #17
    //   220: if_icmpne -> 282
    //   223: aload_0
    //   224: aload_0
    //   225: getfield mTempRect : Landroid/graphics/Rect;
    //   228: aload #4
    //   230: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   233: getfield left : I
    //   236: istore #8
    //   238: aload_0
    //   239: aload_0
    //   240: getfield mTempRect : Landroid/graphics/Rect;
    //   243: aload #5
    //   245: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   248: getfield left : I
    //   251: istore #6
    //   253: aload #5
    //   255: ifnull -> 273
    //   258: iload #8
    //   260: iload #6
    //   262: if_icmplt -> 273
    //   265: aload_0
    //   266: invokevirtual pageLeft : ()Z
    //   269: istore_3
    //   270: goto -> 279
    //   273: aload #4
    //   275: invokevirtual requestFocus : ()Z
    //   278: istore_3
    //   279: goto -> 385
    //   282: iload_1
    //   283: bipush #66
    //   285: if_icmpne -> 385
    //   288: aload_0
    //   289: aload_0
    //   290: getfield mTempRect : Landroid/graphics/Rect;
    //   293: aload #4
    //   295: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   298: getfield left : I
    //   301: istore #6
    //   303: aload_0
    //   304: aload_0
    //   305: getfield mTempRect : Landroid/graphics/Rect;
    //   308: aload #5
    //   310: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   313: getfield left : I
    //   316: istore #8
    //   318: aload #5
    //   320: ifnull -> 338
    //   323: iload #6
    //   325: iload #8
    //   327: if_icmpgt -> 338
    //   330: aload_0
    //   331: invokevirtual pageRight : ()Z
    //   334: istore_3
    //   335: goto -> 279
    //   338: aload #4
    //   340: invokevirtual requestFocus : ()Z
    //   343: istore_3
    //   344: goto -> 279
    //   347: iload_1
    //   348: bipush #17
    //   350: if_icmpeq -> 380
    //   353: iload_1
    //   354: iconst_1
    //   355: if_icmpne -> 361
    //   358: goto -> 380
    //   361: iload_1
    //   362: bipush #66
    //   364: if_icmpeq -> 372
    //   367: iload_1
    //   368: iconst_2
    //   369: if_icmpne -> 385
    //   372: aload_0
    //   373: invokevirtual pageRight : ()Z
    //   376: istore_3
    //   377: goto -> 385
    //   380: aload_0
    //   381: invokevirtual pageLeft : ()Z
    //   384: istore_3
    //   385: iload_3
    //   386: ifeq -> 397
    //   389: aload_0
    //   390: iload_1
    //   391: invokestatic getContantForFocusDirection : (I)I
    //   394: invokevirtual playSoundEffect : (I)V
    //   397: iload_3
    //   398: ireturn
  }
  
  public boolean beginFakeDrag() {
    if (this.mIsBeingDragged)
      return false; 
    this.mFakeDragging = true;
    setScrollState(1);
    this.mLastMotionX = 0.0F;
    this.mInitialMotionX = 0.0F;
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    } else {
      this.mVelocityTracker.clear();
    } 
    long l = SystemClock.uptimeMillis();
    MotionEvent motionEvent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
    this.mVelocityTracker.addMovement(motionEvent);
    motionEvent.recycle();
    this.mFakeDragBeginTime = l;
    return true;
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3) {
    boolean bool = paramView instanceof ViewGroup;
    boolean bool1 = true;
    if (bool) {
      ViewGroup viewGroup = (ViewGroup)paramView;
      int i = paramView.getScrollX();
      int j = paramView.getScrollY();
      for (int k = viewGroup.getChildCount() - 1; k >= 0; k--) {
        View view = viewGroup.getChildAt(k);
        int m = paramInt2 + i;
        if (m >= view.getLeft() && m < view.getRight()) {
          int n = paramInt3 + j;
          if (n >= view.getTop() && n < view.getBottom() && canScroll(view, true, paramInt1, m - view.getLeft(), n - view.getTop()))
            return true; 
        } 
      } 
    } 
    if (paramBoolean && paramView.canScrollHorizontally(-paramInt1)) {
      paramBoolean = bool1;
    } else {
      paramBoolean = false;
    } 
    return paramBoolean;
  }
  
  public boolean canScrollHorizontally(int paramInt) {
    PagerAdapter pagerAdapter = this.mAdapter;
    boolean bool1 = false;
    boolean bool2 = false;
    if (pagerAdapter == null)
      return false; 
    int i = getClientWidth();
    int j = getScrollX();
    if (paramInt < 0) {
      if (j > (int)(i * this.mFirstOffset))
        bool2 = true; 
      return bool2;
    } 
    if (paramInt > 0) {
      bool2 = bool1;
      if (j < (int)(i * this.mLastOffset))
        bool2 = true; 
      return bool2;
    } 
    return false;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (paramLayoutParams instanceof LayoutParams && super.checkLayoutParams(paramLayoutParams)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearOnPageChangeListeners() {
    if (this.mOnPageChangeListeners != null)
      this.mOnPageChangeListeners.clear(); 
  }
  
  public void computeScroll() {
    this.mIsScrollStarted = true;
    if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
      int i = getScrollX();
      int j = getScrollY();
      int k = this.mScroller.getCurrX();
      int m = this.mScroller.getCurrY();
      if (i != k || j != m) {
        scrollTo(k, m);
        if (!pageScrolled(k)) {
          this.mScroller.abortAnimation();
          scrollTo(0, m);
        } 
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
      return;
    } 
    completeScroll(true);
  }
  
  void dataSetChanged() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   4: invokevirtual getCount : ()I
    //   7: istore_1
    //   8: aload_0
    //   9: iload_1
    //   10: putfield mExpectedAdapterCount : I
    //   13: aload_0
    //   14: getfield mItems : Ljava/util/ArrayList;
    //   17: invokevirtual size : ()I
    //   20: aload_0
    //   21: getfield mOffscreenPageLimit : I
    //   24: iconst_2
    //   25: imul
    //   26: iconst_1
    //   27: iadd
    //   28: if_icmpge -> 47
    //   31: aload_0
    //   32: getfield mItems : Ljava/util/ArrayList;
    //   35: invokevirtual size : ()I
    //   38: iload_1
    //   39: if_icmpge -> 47
    //   42: iconst_1
    //   43: istore_2
    //   44: goto -> 49
    //   47: iconst_0
    //   48: istore_2
    //   49: aload_0
    //   50: getfield mCurItem : I
    //   53: istore_3
    //   54: iload_2
    //   55: istore #4
    //   57: iload_3
    //   58: istore_2
    //   59: iconst_0
    //   60: istore #5
    //   62: iload #5
    //   64: istore_3
    //   65: iload #5
    //   67: aload_0
    //   68: getfield mItems : Ljava/util/ArrayList;
    //   71: invokevirtual size : ()I
    //   74: if_icmpge -> 300
    //   77: aload_0
    //   78: getfield mItems : Ljava/util/ArrayList;
    //   81: iload #5
    //   83: invokevirtual get : (I)Ljava/lang/Object;
    //   86: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   89: astore #6
    //   91: aload_0
    //   92: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   95: aload #6
    //   97: getfield object : Ljava/lang/Object;
    //   100: invokevirtual getItemPosition : (Ljava/lang/Object;)I
    //   103: istore #7
    //   105: iload #7
    //   107: iconst_m1
    //   108: if_icmpne -> 124
    //   111: iload #5
    //   113: istore #8
    //   115: iload_3
    //   116: istore #9
    //   118: iload_2
    //   119: istore #10
    //   121: goto -> 285
    //   124: iload #7
    //   126: bipush #-2
    //   128: if_icmpne -> 240
    //   131: aload_0
    //   132: getfield mItems : Ljava/util/ArrayList;
    //   135: iload #5
    //   137: invokevirtual remove : (I)Ljava/lang/Object;
    //   140: pop
    //   141: iload #5
    //   143: iconst_1
    //   144: isub
    //   145: istore #9
    //   147: iload_3
    //   148: istore #8
    //   150: iload_3
    //   151: ifne -> 165
    //   154: aload_0
    //   155: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   158: aload_0
    //   159: invokevirtual startUpdate : (Landroid/view/ViewGroup;)V
    //   162: iconst_1
    //   163: istore #8
    //   165: aload_0
    //   166: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   169: aload_0
    //   170: aload #6
    //   172: getfield position : I
    //   175: aload #6
    //   177: getfield object : Ljava/lang/Object;
    //   180: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   183: iload #9
    //   185: istore #5
    //   187: iload #8
    //   189: istore_3
    //   190: aload_0
    //   191: getfield mCurItem : I
    //   194: aload #6
    //   196: getfield position : I
    //   199: if_icmpne -> 224
    //   202: iconst_0
    //   203: aload_0
    //   204: getfield mCurItem : I
    //   207: iload_1
    //   208: iconst_1
    //   209: isub
    //   210: invokestatic min : (II)I
    //   213: invokestatic max : (II)I
    //   216: istore_2
    //   217: iload #8
    //   219: istore_3
    //   220: iload #9
    //   222: istore #5
    //   224: iconst_1
    //   225: istore #4
    //   227: iload #5
    //   229: istore #8
    //   231: iload_3
    //   232: istore #9
    //   234: iload_2
    //   235: istore #10
    //   237: goto -> 285
    //   240: iload #5
    //   242: istore #8
    //   244: iload_3
    //   245: istore #9
    //   247: iload_2
    //   248: istore #10
    //   250: aload #6
    //   252: getfield position : I
    //   255: iload #7
    //   257: if_icmpeq -> 285
    //   260: aload #6
    //   262: getfield position : I
    //   265: aload_0
    //   266: getfield mCurItem : I
    //   269: if_icmpne -> 275
    //   272: iload #7
    //   274: istore_2
    //   275: aload #6
    //   277: iload #7
    //   279: putfield position : I
    //   282: goto -> 224
    //   285: iload #8
    //   287: iconst_1
    //   288: iadd
    //   289: istore #5
    //   291: iload #9
    //   293: istore_3
    //   294: iload #10
    //   296: istore_2
    //   297: goto -> 65
    //   300: iload_3
    //   301: ifeq -> 312
    //   304: aload_0
    //   305: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   308: aload_0
    //   309: invokevirtual finishUpdate : (Landroid/view/ViewGroup;)V
    //   312: aload_0
    //   313: getfield mItems : Ljava/util/ArrayList;
    //   316: getstatic android/support/v4/view/ViewPager.COMPARATOR : Ljava/util/Comparator;
    //   319: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
    //   322: iload #4
    //   324: ifeq -> 385
    //   327: aload_0
    //   328: invokevirtual getChildCount : ()I
    //   331: istore #5
    //   333: iconst_0
    //   334: istore_3
    //   335: iload_3
    //   336: iload #5
    //   338: if_icmpge -> 374
    //   341: aload_0
    //   342: iload_3
    //   343: invokevirtual getChildAt : (I)Landroid/view/View;
    //   346: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   349: checkcast android/support/v4/view/ViewPager$LayoutParams
    //   352: astore #6
    //   354: aload #6
    //   356: getfield isDecor : Z
    //   359: ifne -> 368
    //   362: aload #6
    //   364: fconst_0
    //   365: putfield widthFactor : F
    //   368: iinc #3, 1
    //   371: goto -> 335
    //   374: aload_0
    //   375: iload_2
    //   376: iconst_0
    //   377: iconst_1
    //   378: invokevirtual setCurrentItemInternal : (IZZ)V
    //   381: aload_0
    //   382: invokevirtual requestLayout : ()V
    //   385: return
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    return (super.dispatchKeyEvent(paramKeyEvent) || executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    if (paramAccessibilityEvent.getEventType() == 4096)
      return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent); 
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem && view.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))
          return true; 
      } 
    } 
    return false;
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat) {
    return (float)Math.sin(((paramFloat - 0.5F) * 0.47123894F));
  }
  
  public void draw(Canvas paramCanvas) {
    boolean bool;
    super.draw(paramCanvas);
    int i = getOverScrollMode();
    int j = 0;
    int k = 0;
    if (i == 0 || (i == 1 && this.mAdapter != null && this.mAdapter.getCount() > 1)) {
      if (!this.mLeftEdge.isFinished()) {
        j = paramCanvas.save();
        k = getHeight() - getPaddingTop() - getPaddingBottom();
        i = getWidth();
        paramCanvas.rotate(270.0F);
        paramCanvas.translate((-k + getPaddingTop()), this.mFirstOffset * i);
        this.mLeftEdge.setSize(k, i);
        k = false | this.mLeftEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(j);
      } 
      j = k;
      if (!this.mRightEdge.isFinished()) {
        i = paramCanvas.save();
        int m = getWidth();
        j = getHeight();
        int n = getPaddingTop();
        int i1 = getPaddingBottom();
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-getPaddingTop(), -(this.mLastOffset + 1.0F) * m);
        this.mRightEdge.setSize(j - n - i1, m);
        bool = k | this.mRightEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(i);
      } 
    } else {
      this.mLeftEdge.finish();
      this.mRightEdge.finish();
    } 
    if (bool)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    Drawable drawable = this.mMarginDrawable;
    if (drawable != null && drawable.isStateful())
      drawable.setState(getDrawableState()); 
  }
  
  public void endFakeDrag() {
    if (!this.mFakeDragging)
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first."); 
    if (this.mAdapter != null) {
      VelocityTracker velocityTracker = this.mVelocityTracker;
      velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
      int i = (int)velocityTracker.getXVelocity(this.mActivePointerId);
      this.mPopulatePending = true;
      int j = getClientWidth();
      int k = getScrollX();
      ItemInfo itemInfo = infoForCurrentScrollPosition();
      setCurrentItemInternal(determineTargetPage(itemInfo.position, (k / j - itemInfo.offset) / itemInfo.widthFactor, i, (int)(this.mLastMotionX - this.mInitialMotionX)), true, true, i);
    } 
    endDrag();
    this.mFakeDragging = false;
  }
  
  public boolean executeKeyEvent(@NonNull KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getAction() == 0) {
      int i = paramKeyEvent.getKeyCode();
      if (i != 61) {
        boolean bool1;
        switch (i) {
          case 22:
            if (paramKeyEvent.hasModifiers(2)) {
              bool1 = pageRight();
            } else {
              bool1 = arrowScroll(66);
            } 
            return bool1;
          case 21:
            if (paramKeyEvent.hasModifiers(2)) {
              bool1 = pageLeft();
            } else {
              bool1 = arrowScroll(17);
            } 
            return bool1;
        } 
      } else {
        boolean bool1;
        if (paramKeyEvent.hasNoModifiers()) {
          bool1 = arrowScroll(2);
        } else {
          if (paramKeyEvent.hasModifiers(1))
            return arrowScroll(1); 
          bool1 = false;
        } 
        return bool1;
      } 
    } 
    boolean bool = false;
  }
  
  public void fakeDragBy(float paramFloat) {
    if (!this.mFakeDragging)
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first."); 
    if (this.mAdapter == null)
      return; 
    this.mLastMotionX += paramFloat;
    float f1 = getScrollX() - paramFloat;
    float f2 = getClientWidth();
    paramFloat = this.mFirstOffset * f2;
    float f3 = this.mLastOffset * f2;
    ItemInfo itemInfo1 = this.mItems.get(0);
    ItemInfo itemInfo2 = this.mItems.get(this.mItems.size() - 1);
    if (itemInfo1.position != 0)
      paramFloat = itemInfo1.offset * f2; 
    if (itemInfo2.position != this.mAdapter.getCount() - 1)
      f3 = itemInfo2.offset * f2; 
    if (f1 >= paramFloat) {
      paramFloat = f1;
      if (f1 > f3)
        paramFloat = f3; 
    } 
    f3 = this.mLastMotionX;
    int i = (int)paramFloat;
    this.mLastMotionX = f3 + paramFloat - i;
    scrollTo(i, getScrollY());
    pageScrolled(i);
    long l = SystemClock.uptimeMillis();
    MotionEvent motionEvent = MotionEvent.obtain(this.mFakeDragBeginTime, l, 2, this.mLastMotionX, 0.0F, 0);
    this.mVelocityTracker.addMovement(motionEvent);
    motionEvent.recycle();
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return generateDefaultLayoutParams();
  }
  
  @Nullable
  public PagerAdapter getAdapter() {
    return this.mAdapter;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2) {
    int i = paramInt2;
    if (this.mDrawingOrder == 2)
      i = paramInt1 - 1 - paramInt2; 
    return ((LayoutParams)((View)this.mDrawingOrderedChildren.get(i)).getLayoutParams()).childIndex;
  }
  
  public int getCurrentItem() {
    return this.mCurItem;
  }
  
  public int getOffscreenPageLimit() {
    return this.mOffscreenPageLimit;
  }
  
  public int getPageMargin() {
    return this.mPageMargin;
  }
  
  ItemInfo infoForAnyChild(View paramView) {
    while (true) {
      ViewParent viewParent = paramView.getParent();
      if (viewParent != this) {
        if (viewParent != null) {
          if (!(viewParent instanceof View))
            return null; 
          paramView = (View)viewParent;
          continue;
        } 
        continue;
      } 
      return infoForChild(paramView);
    } 
  }
  
  ItemInfo infoForChild(View paramView) {
    for (byte b = 0; b < this.mItems.size(); b++) {
      ItemInfo itemInfo = this.mItems.get(b);
      if (this.mAdapter.isViewFromObject(paramView, itemInfo.object))
        return itemInfo; 
    } 
    return null;
  }
  
  ItemInfo infoForPosition(int paramInt) {
    for (byte b = 0; b < this.mItems.size(); b++) {
      ItemInfo itemInfo = this.mItems.get(b);
      if (itemInfo.position == paramInt)
        return itemInfo; 
    } 
    return null;
  }
  
  void initViewPager() {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context context = getContext();
    this.mScroller = new Scroller(context, sInterpolator);
    ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
    float f = (context.getResources().getDisplayMetrics()).density;
    this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
    this.mMinimumVelocity = (int)(400.0F * f);
    this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    this.mLeftEdge = new EdgeEffect(context);
    this.mRightEdge = new EdgeEffect(context);
    this.mFlingDistance = (int)(25.0F * f);
    this.mCloseEnough = (int)(2.0F * f);
    this.mDefaultGutterSize = (int)(f * 16.0F);
    ViewCompat.setAccessibilityDelegate((View)this, new MyAccessibilityDelegate());
    if (ViewCompat.getImportantForAccessibility((View)this) == 0)
      ViewCompat.setImportantForAccessibility((View)this, 1); 
    ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
          private final Rect mTempRect = new Rect();
          
          public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
            param1WindowInsetsCompat = ViewCompat.onApplyWindowInsets(param1View, param1WindowInsetsCompat);
            if (param1WindowInsetsCompat.isConsumed())
              return param1WindowInsetsCompat; 
            Rect rect = this.mTempRect;
            rect.left = param1WindowInsetsCompat.getSystemWindowInsetLeft();
            rect.top = param1WindowInsetsCompat.getSystemWindowInsetTop();
            rect.right = param1WindowInsetsCompat.getSystemWindowInsetRight();
            rect.bottom = param1WindowInsetsCompat.getSystemWindowInsetBottom();
            byte b = 0;
            int i = ViewPager.this.getChildCount();
            while (b < i) {
              WindowInsetsCompat windowInsetsCompat = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(b), param1WindowInsetsCompat);
              rect.left = Math.min(windowInsetsCompat.getSystemWindowInsetLeft(), rect.left);
              rect.top = Math.min(windowInsetsCompat.getSystemWindowInsetTop(), rect.top);
              rect.right = Math.min(windowInsetsCompat.getSystemWindowInsetRight(), rect.right);
              rect.bottom = Math.min(windowInsetsCompat.getSystemWindowInsetBottom(), rect.bottom);
              b++;
            } 
            return param1WindowInsetsCompat.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
          }
        });
  }
  
  public boolean isFakeDragging() {
    return this.mFakeDragging;
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow() {
    removeCallbacks(this.mEndScrollRunnable);
    if (this.mScroller != null && !this.mScroller.isFinished())
      this.mScroller.abortAnimation(); 
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
      int i = getScrollX();
      int j = getWidth();
      float f1 = this.mPageMargin;
      float f2 = j;
      float f3 = f1 / f2;
      ArrayList<ItemInfo> arrayList = this.mItems;
      byte b = 0;
      ItemInfo itemInfo = arrayList.get(0);
      f1 = itemInfo.offset;
      int k = this.mItems.size();
      int m = itemInfo.position;
      int n = ((ItemInfo)this.mItems.get(k - 1)).position;
      while (m < n) {
        ItemInfo itemInfo1;
        float f;
        while (m > itemInfo.position && b < k) {
          ArrayList<ItemInfo> arrayList1 = this.mItems;
          itemInfo1 = arrayList1.get(++b);
        } 
        if (m == itemInfo1.position) {
          f = itemInfo1.offset;
          float f4 = itemInfo1.widthFactor;
          f1 = itemInfo1.offset;
          float f5 = itemInfo1.widthFactor;
          f = (f + f4) * f2;
          f1 = f1 + f5 + f3;
        } else {
          float f4 = this.mAdapter.getPageWidth(m);
          f = (f1 + f4) * f2;
          f1 += f4 + f3;
        } 
        if (this.mPageMargin + f > i) {
          this.mMarginDrawable.setBounds(Math.round(f), this.mTopPageBounds, Math.round(this.mPageMargin + f), this.mBottomPageBounds);
          this.mMarginDrawable.draw(paramCanvas);
        } 
        if (f > (i + j))
          break; 
        m++;
      } 
    } 
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction() & 0xFF;
    if (i == 3 || i == 1) {
      resetTouch();
      return false;
    } 
    if (i != 0) {
      if (this.mIsBeingDragged)
        return true; 
      if (this.mIsUnableToDrag)
        return false; 
    } 
    if (i != 0) {
      if (i != 2) {
        if (i == 6)
          onSecondaryPointerUp(paramMotionEvent); 
      } else {
        i = this.mActivePointerId;
        if (i != -1) {
          i = paramMotionEvent.findPointerIndex(i);
          float f1 = paramMotionEvent.getX(i);
          float f2 = f1 - this.mLastMotionX;
          float f3 = Math.abs(f2);
          float f4 = paramMotionEvent.getY(i);
          float f5 = Math.abs(f4 - this.mInitialMotionY);
          i = f2 cmp 0.0F;
          if (i != 0 && !isGutterDrag(this.mLastMotionX, f2) && canScroll((View)this, false, (int)f2, (int)f1, (int)f4)) {
            this.mLastMotionX = f1;
            this.mLastMotionY = f4;
            this.mIsUnableToDrag = true;
            return false;
          } 
          if (f3 > this.mTouchSlop && f3 * 0.5F > f5) {
            this.mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
            if (i > 0) {
              f2 = this.mInitialMotionX + this.mTouchSlop;
            } else {
              f2 = this.mInitialMotionX - this.mTouchSlop;
            } 
            this.mLastMotionX = f2;
            this.mLastMotionY = f4;
            setScrollingCacheEnabled(true);
          } else if (f5 > this.mTouchSlop) {
            this.mIsUnableToDrag = true;
          } 
          if (this.mIsBeingDragged && performDrag(f1))
            ViewCompat.postInvalidateOnAnimation((View)this); 
        } 
      } 
    } else {
      float f = paramMotionEvent.getX();
      this.mInitialMotionX = f;
      this.mLastMotionX = f;
      f = paramMotionEvent.getY();
      this.mInitialMotionY = f;
      this.mLastMotionY = f;
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      this.mIsUnableToDrag = false;
      this.mIsScrollStarted = true;
      this.mScroller.computeScrollOffset();
      if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
        this.mScroller.abortAnimation();
        this.mPopulatePending = false;
        populate();
        this.mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        setScrollState(1);
      } else {
        completeScroll(false);
        this.mIsBeingDragged = false;
      } 
    } 
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain(); 
    this.mVelocityTracker.addMovement(paramMotionEvent);
    return this.mIsBeingDragged;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getChildCount();
    int j = paramInt3 - paramInt1;
    int k = paramInt4 - paramInt2;
    paramInt2 = getPaddingLeft();
    paramInt1 = getPaddingTop();
    int m = getPaddingRight();
    paramInt4 = getPaddingBottom();
    int n = getScrollX();
    int i1 = 0;
    byte b = 0;
    while (b < i) {
      View view = getChildAt(b);
      int i2 = paramInt2;
      int i3 = m;
      int i4 = paramInt1;
      int i5 = paramInt4;
      paramInt3 = i1;
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        i2 = paramInt2;
        i3 = m;
        i4 = paramInt1;
        i5 = paramInt4;
        paramInt3 = i1;
        if (layoutParams.isDecor) {
          paramInt3 = layoutParams.gravity & 0x7;
          i5 = layoutParams.gravity & 0x70;
          if (paramInt3 != 1) {
            if (paramInt3 != 3) {
              if (paramInt3 != 5) {
                paramInt3 = paramInt2;
                i2 = paramInt2;
              } else {
                paramInt3 = j - m - view.getMeasuredWidth();
                m += view.getMeasuredWidth();
                i2 = paramInt2;
              } 
            } else {
              i2 = view.getMeasuredWidth();
              paramInt3 = paramInt2;
              i2 += paramInt2;
            } 
          } else {
            paramInt3 = Math.max((j - view.getMeasuredWidth()) / 2, paramInt2);
            i2 = paramInt2;
          } 
          if (i5 != 16) {
            if (i5 != 48) {
              if (i5 != 80) {
                paramInt2 = paramInt1;
              } else {
                paramInt2 = k - paramInt4 - view.getMeasuredHeight();
                paramInt4 += view.getMeasuredHeight();
              } 
            } else {
              i5 = view.getMeasuredHeight();
              paramInt2 = paramInt1;
              paramInt1 = i5 + paramInt1;
            } 
          } else {
            paramInt2 = Math.max((k - view.getMeasuredHeight()) / 2, paramInt1);
          } 
          paramInt3 += n;
          view.layout(paramInt3, paramInt2, view.getMeasuredWidth() + paramInt3, paramInt2 + view.getMeasuredHeight());
          paramInt3 = i1 + 1;
          i5 = paramInt4;
          i4 = paramInt1;
          i3 = m;
        } 
      } 
      b++;
      paramInt2 = i2;
      m = i3;
      paramInt1 = i4;
      paramInt4 = i5;
      i1 = paramInt3;
    } 
    for (paramInt3 = 0; paramInt3 < i; paramInt3++) {
      View view = getChildAt(paramInt3);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (!layoutParams.isDecor) {
          ItemInfo itemInfo = infoForChild(view);
          if (itemInfo != null) {
            float f = (j - paramInt2 - m);
            int i2 = (int)(itemInfo.offset * f) + paramInt2;
            if (layoutParams.needsMeasure) {
              layoutParams.needsMeasure = false;
              view.measure(View.MeasureSpec.makeMeasureSpec((int)(f * layoutParams.widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec(k - paramInt1 - paramInt4, 1073741824));
            } 
            view.layout(i2, paramInt1, view.getMeasuredWidth() + i2, view.getMeasuredHeight() + paramInt1);
          } 
        } 
      } 
    } 
    this.mTopPageBounds = paramInt1;
    this.mBottomPageBounds = k - paramInt4;
    this.mDecorChildCount = i1;
    if (this.mFirstLayout)
      scrollToItem(this.mCurItem, false, 0, false); 
    this.mFirstLayout = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    setMeasuredDimension(getDefaultSize(0, paramInt1), getDefaultSize(0, paramInt2));
    int i = getMeasuredWidth();
    this.mGutterSize = Math.min(i / 10, this.mDefaultGutterSize);
    int j = getPaddingLeft();
    paramInt1 = getPaddingRight();
    int k = getMeasuredHeight();
    int m = getPaddingTop();
    paramInt2 = getPaddingBottom();
    int n = getChildCount();
    paramInt2 = k - m - paramInt2;
    paramInt1 = i - j - paramInt1;
    m = 0;
    while (true) {
      int i1 = 1;
      int i2 = 1073741824;
      if (m < n) {
        View view = getChildAt(m);
        i = paramInt1;
        j = paramInt2;
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          i = paramInt1;
          j = paramInt2;
          if (layoutParams != null) {
            i = paramInt1;
            j = paramInt2;
            if (layoutParams.isDecor) {
              boolean bool;
              int i3;
              i = layoutParams.gravity & 0x7;
              j = layoutParams.gravity & 0x70;
              if (j == 48 || j == 80) {
                bool = true;
              } else {
                bool = false;
              } 
              k = i1;
              if (i != 3)
                if (i == 5) {
                  k = i1;
                } else {
                  k = 0;
                }  
              j = Integer.MIN_VALUE;
              if (bool) {
                i = Integer.MIN_VALUE;
                j = 1073741824;
              } else if (k != 0) {
                i = 1073741824;
              } else {
                i = Integer.MIN_VALUE;
              } 
              if (layoutParams.width != -2) {
                if (layoutParams.width != -1) {
                  j = layoutParams.width;
                } else {
                  j = paramInt1;
                } 
                i3 = 1073741824;
                i1 = j;
              } else {
                i1 = paramInt1;
                i3 = j;
              } 
              if (layoutParams.height != -2) {
                if (layoutParams.height != -1) {
                  i = layoutParams.height;
                } else {
                  i = paramInt2;
                } 
              } else {
                j = paramInt2;
                i2 = i;
                i = j;
              } 
              view.measure(View.MeasureSpec.makeMeasureSpec(i1, i3), View.MeasureSpec.makeMeasureSpec(i, i2));
              if (bool) {
                j = paramInt2 - view.getMeasuredHeight();
                i = paramInt1;
              } else {
                i = paramInt1;
                j = paramInt2;
                if (k != 0) {
                  i = paramInt1 - view.getMeasuredWidth();
                  j = paramInt2;
                } 
              } 
            } 
          } 
        } 
        m++;
        paramInt1 = i;
        paramInt2 = j;
        continue;
      } 
      this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
      this.mInLayout = true;
      populate();
      paramInt2 = 0;
      this.mInLayout = false;
      i = getChildCount();
      while (paramInt2 < i) {
        View view = getChildAt(paramInt2);
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          if (layoutParams == null || !layoutParams.isDecor)
            view.measure(View.MeasureSpec.makeMeasureSpec((int)(paramInt1 * layoutParams.widthFactor), 1073741824), this.mChildHeightMeasureSpec); 
        } 
        paramInt2++;
      } 
      return;
    } 
  }
  
  @CallSuper
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    int i = this.mDecorChildCount;
    boolean bool = false;
    if (i > 0) {
      int j = getScrollX();
      i = getPaddingLeft();
      int k = getPaddingRight();
      int m = getWidth();
      int n = getChildCount();
      for (byte b = 0; b < n; b++) {
        View view = getChildAt(b);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.isDecor) {
          int i1 = layoutParams.gravity & 0x7;
          if (i1 != 1) {
            if (i1 != 3) {
              if (i1 != 5) {
                int i2 = i;
                i1 = i;
                i = i2;
              } else {
                i1 = m - k - view.getMeasuredWidth();
                k += view.getMeasuredWidth();
              } 
            } else {
              int i2 = view.getWidth() + i;
              i1 = i;
              i = i2;
            } 
          } else {
            i1 = Math.max((m - view.getMeasuredWidth()) / 2, i);
          } 
          i1 = i1 + j - view.getLeft();
          if (i1 != 0)
            view.offsetLeftAndRight(i1); 
        } 
      } 
    } 
    dispatchOnPageScrolled(paramInt1, paramFloat, paramInt2);
    if (this.mPageTransformer != null) {
      i = getScrollX();
      paramInt2 = getChildCount();
      for (paramInt1 = bool; paramInt1 < paramInt2; paramInt1++) {
        View view = getChildAt(paramInt1);
        if (!((LayoutParams)view.getLayoutParams()).isDecor) {
          paramFloat = (view.getLeft() - i) / getClientWidth();
          this.mPageTransformer.transformPage(view, paramFloat);
        } 
      } 
    } 
    this.mCalledSuper = true;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect) {
    byte b;
    int i = getChildCount();
    int j = -1;
    if ((paramInt & 0x2) != 0) {
      j = i;
      i = 0;
      b = 1;
    } else {
      i--;
      b = -1;
    } 
    while (i != j) {
      View view = getChildAt(i);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem && view.requestFocus(paramInt, paramRect))
          return true; 
      } 
      i += b;
    } 
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    paramParcelable = paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (this.mAdapter != null) {
      this.mAdapter.restoreState(((SavedState)paramParcelable).adapterState, ((SavedState)paramParcelable).loader);
      setCurrentItemInternal(((SavedState)paramParcelable).position, false, true);
    } else {
      this.mRestoredCurItem = ((SavedState)paramParcelable).position;
      this.mRestoredAdapterState = ((SavedState)paramParcelable).adapterState;
      this.mRestoredClassLoader = ((SavedState)paramParcelable).loader;
    } 
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    savedState.position = this.mCurItem;
    if (this.mAdapter != null)
      savedState.adapterState = this.mAdapter.saveState(); 
    return savedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
      recomputeScrollPosition(paramInt1, paramInt3, this.mPageMargin, this.mPageMargin); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    float f;
    if (this.mFakeDragging)
      return true; 
    int i = paramMotionEvent.getAction();
    boolean bool = false;
    if (i == 0 && paramMotionEvent.getEdgeFlags() != 0)
      return false; 
    if (this.mAdapter == null || this.mAdapter.getCount() == 0)
      return false; 
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain(); 
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (paramMotionEvent.getAction() & 0xFF) {
      case 6:
        onSecondaryPointerUp(paramMotionEvent);
        this.mLastMotionX = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId));
        break;
      case 5:
        i = paramMotionEvent.getActionIndex();
        this.mLastMotionX = paramMotionEvent.getX(i);
        this.mActivePointerId = paramMotionEvent.getPointerId(i);
        break;
      case 3:
        if (this.mIsBeingDragged) {
          scrollToItem(this.mCurItem, true, 0, false);
          bool = resetTouch();
        } 
        break;
      case 2:
        if (!this.mIsBeingDragged) {
          i = paramMotionEvent.findPointerIndex(this.mActivePointerId);
          if (i == -1) {
            bool = resetTouch();
            break;
          } 
          float f1 = paramMotionEvent.getX(i);
          float f2 = Math.abs(f1 - this.mLastMotionX);
          float f3 = paramMotionEvent.getY(i);
          float f4 = Math.abs(f3 - this.mLastMotionY);
          if (f2 > this.mTouchSlop && f2 > f4) {
            this.mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            if (f1 - this.mInitialMotionX > 0.0F) {
              f1 = this.mInitialMotionX + this.mTouchSlop;
            } else {
              f1 = this.mInitialMotionX - this.mTouchSlop;
            } 
            this.mLastMotionX = f1;
            this.mLastMotionY = f3;
            setScrollState(1);
            setScrollingCacheEnabled(true);
            ViewParent viewParent = getParent();
            if (viewParent != null)
              viewParent.requestDisallowInterceptTouchEvent(true); 
          } 
        } 
        if (this.mIsBeingDragged)
          int j = false | performDrag(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId))); 
        break;
      case 1:
        if (this.mIsBeingDragged) {
          VelocityTracker velocityTracker = this.mVelocityTracker;
          velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
          i = (int)velocityTracker.getXVelocity(this.mActivePointerId);
          this.mPopulatePending = true;
          int j = getClientWidth();
          int k = getScrollX();
          ItemInfo itemInfo = infoForCurrentScrollPosition();
          float f2 = this.mPageMargin;
          float f1 = j;
          f2 /= f1;
          setCurrentItemInternal(determineTargetPage(itemInfo.position, (k / f1 - itemInfo.offset) / (itemInfo.widthFactor + f2), i, (int)(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, i);
          bool = resetTouch();
        } 
        break;
      case 0:
        this.mScroller.abortAnimation();
        this.mPopulatePending = false;
        populate();
        f = paramMotionEvent.getX();
        this.mInitialMotionX = f;
        this.mLastMotionX = f;
        f = paramMotionEvent.getY();
        this.mInitialMotionY = f;
        this.mLastMotionY = f;
        this.mActivePointerId = paramMotionEvent.getPointerId(0);
        break;
    } 
    if (bool)
      ViewCompat.postInvalidateOnAnimation((View)this); 
    return true;
  }
  
  boolean pageLeft() {
    if (this.mCurItem > 0) {
      setCurrentItem(this.mCurItem - 1, true);
      return true;
    } 
    return false;
  }
  
  boolean pageRight() {
    if (this.mAdapter != null && this.mCurItem < this.mAdapter.getCount() - 1) {
      setCurrentItem(this.mCurItem + 1, true);
      return true;
    } 
    return false;
  }
  
  void populate() {
    populate(this.mCurItem);
  }
  
  void populate(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mCurItem : I
    //   4: iload_1
    //   5: if_icmpeq -> 25
    //   8: aload_0
    //   9: aload_0
    //   10: getfield mCurItem : I
    //   13: invokevirtual infoForPosition : (I)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   16: astore_2
    //   17: aload_0
    //   18: iload_1
    //   19: putfield mCurItem : I
    //   22: goto -> 27
    //   25: aconst_null
    //   26: astore_2
    //   27: aload_0
    //   28: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   31: ifnonnull -> 39
    //   34: aload_0
    //   35: invokespecial sortChildDrawingOrder : ()V
    //   38: return
    //   39: aload_0
    //   40: getfield mPopulatePending : Z
    //   43: ifeq -> 51
    //   46: aload_0
    //   47: invokespecial sortChildDrawingOrder : ()V
    //   50: return
    //   51: aload_0
    //   52: invokevirtual getWindowToken : ()Landroid/os/IBinder;
    //   55: ifnonnull -> 59
    //   58: return
    //   59: aload_0
    //   60: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   63: aload_0
    //   64: invokevirtual startUpdate : (Landroid/view/ViewGroup;)V
    //   67: aload_0
    //   68: getfield mOffscreenPageLimit : I
    //   71: istore_1
    //   72: iconst_0
    //   73: aload_0
    //   74: getfield mCurItem : I
    //   77: iload_1
    //   78: isub
    //   79: invokestatic max : (II)I
    //   82: istore_3
    //   83: aload_0
    //   84: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   87: invokevirtual getCount : ()I
    //   90: istore #4
    //   92: iload #4
    //   94: iconst_1
    //   95: isub
    //   96: aload_0
    //   97: getfield mCurItem : I
    //   100: iload_1
    //   101: iadd
    //   102: invokestatic min : (II)I
    //   105: istore #5
    //   107: iload #4
    //   109: aload_0
    //   110: getfield mExpectedAdapterCount : I
    //   113: if_icmpeq -> 247
    //   116: aload_0
    //   117: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   120: aload_0
    //   121: invokevirtual getId : ()I
    //   124: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   127: astore #6
    //   129: goto -> 143
    //   132: astore #6
    //   134: aload_0
    //   135: invokevirtual getId : ()I
    //   138: invokestatic toHexString : (I)Ljava/lang/String;
    //   141: astore #6
    //   143: new java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial <init> : ()V
    //   150: astore_2
    //   151: aload_2
    //   152: ldc_w 'The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: '
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload_2
    //   160: aload_0
    //   161: getfield mExpectedAdapterCount : I
    //   164: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload_2
    //   169: ldc_w ', found: '
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload_2
    //   177: iload #4
    //   179: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   182: pop
    //   183: aload_2
    //   184: ldc_w ' Pager id: '
    //   187: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: pop
    //   191: aload_2
    //   192: aload #6
    //   194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: aload_2
    //   199: ldc_w ' Pager class: '
    //   202: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: pop
    //   206: aload_2
    //   207: aload_0
    //   208: invokevirtual getClass : ()Ljava/lang/Class;
    //   211: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   214: pop
    //   215: aload_2
    //   216: ldc_w ' Problematic adapter: '
    //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: aload_2
    //   224: aload_0
    //   225: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   228: invokevirtual getClass : ()Ljava/lang/Class;
    //   231: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: new java/lang/IllegalStateException
    //   238: dup
    //   239: aload_2
    //   240: invokevirtual toString : ()Ljava/lang/String;
    //   243: invokespecial <init> : (Ljava/lang/String;)V
    //   246: athrow
    //   247: iconst_0
    //   248: istore_1
    //   249: iload_1
    //   250: aload_0
    //   251: getfield mItems : Ljava/util/ArrayList;
    //   254: invokevirtual size : ()I
    //   257: if_icmpge -> 306
    //   260: aload_0
    //   261: getfield mItems : Ljava/util/ArrayList;
    //   264: iload_1
    //   265: invokevirtual get : (I)Ljava/lang/Object;
    //   268: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   271: astore #6
    //   273: aload #6
    //   275: getfield position : I
    //   278: aload_0
    //   279: getfield mCurItem : I
    //   282: if_icmplt -> 300
    //   285: aload #6
    //   287: getfield position : I
    //   290: aload_0
    //   291: getfield mCurItem : I
    //   294: if_icmpne -> 306
    //   297: goto -> 309
    //   300: iinc #1, 1
    //   303: goto -> 249
    //   306: aconst_null
    //   307: astore #6
    //   309: aload #6
    //   311: astore #7
    //   313: aload #6
    //   315: ifnonnull -> 338
    //   318: aload #6
    //   320: astore #7
    //   322: iload #4
    //   324: ifle -> 338
    //   327: aload_0
    //   328: aload_0
    //   329: getfield mCurItem : I
    //   332: iload_1
    //   333: invokevirtual addNewItem : (II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   336: astore #7
    //   338: aload #7
    //   340: ifnull -> 1145
    //   343: iload_1
    //   344: iconst_1
    //   345: isub
    //   346: istore #8
    //   348: iload #8
    //   350: iflt -> 370
    //   353: aload_0
    //   354: getfield mItems : Ljava/util/ArrayList;
    //   357: iload #8
    //   359: invokevirtual get : (I)Ljava/lang/Object;
    //   362: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   365: astore #6
    //   367: goto -> 373
    //   370: aconst_null
    //   371: astore #6
    //   373: aload_0
    //   374: invokespecial getClientWidth : ()I
    //   377: istore #9
    //   379: iload #9
    //   381: ifgt -> 390
    //   384: fconst_0
    //   385: fstore #10
    //   387: goto -> 409
    //   390: fconst_2
    //   391: aload #7
    //   393: getfield widthFactor : F
    //   396: fsub
    //   397: aload_0
    //   398: invokevirtual getPaddingLeft : ()I
    //   401: i2f
    //   402: iload #9
    //   404: i2f
    //   405: fdiv
    //   406: fadd
    //   407: fstore #10
    //   409: aload_0
    //   410: getfield mCurItem : I
    //   413: iconst_1
    //   414: isub
    //   415: istore #11
    //   417: fconst_0
    //   418: fstore #12
    //   420: iload_1
    //   421: istore #13
    //   423: iload #11
    //   425: iflt -> 734
    //   428: fload #12
    //   430: fload #10
    //   432: fcmpl
    //   433: iflt -> 569
    //   436: iload #11
    //   438: iload_3
    //   439: if_icmpge -> 569
    //   442: aload #6
    //   444: ifnonnull -> 450
    //   447: goto -> 734
    //   450: fload #12
    //   452: fstore #14
    //   454: iload #8
    //   456: istore_1
    //   457: aload #6
    //   459: astore #15
    //   461: iload #13
    //   463: istore #16
    //   465: iload #11
    //   467: aload #6
    //   469: getfield position : I
    //   472: if_icmpne -> 713
    //   475: fload #12
    //   477: fstore #14
    //   479: iload #8
    //   481: istore_1
    //   482: aload #6
    //   484: astore #15
    //   486: iload #13
    //   488: istore #16
    //   490: aload #6
    //   492: getfield scrolling : Z
    //   495: ifne -> 713
    //   498: aload_0
    //   499: getfield mItems : Ljava/util/ArrayList;
    //   502: iload #8
    //   504: invokevirtual remove : (I)Ljava/lang/Object;
    //   507: pop
    //   508: aload_0
    //   509: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   512: aload_0
    //   513: iload #11
    //   515: aload #6
    //   517: getfield object : Ljava/lang/Object;
    //   520: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   523: iinc #8, -1
    //   526: iinc #13, -1
    //   529: fload #12
    //   531: fstore #14
    //   533: iload #8
    //   535: istore_1
    //   536: iload #13
    //   538: istore #16
    //   540: iload #8
    //   542: iflt -> 698
    //   545: aload_0
    //   546: getfield mItems : Ljava/util/ArrayList;
    //   549: iload #8
    //   551: invokevirtual get : (I)Ljava/lang/Object;
    //   554: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   557: astore #6
    //   559: fload #12
    //   561: fstore #14
    //   563: iload #8
    //   565: istore_1
    //   566: goto -> 705
    //   569: aload #6
    //   571: ifnull -> 637
    //   574: iload #11
    //   576: aload #6
    //   578: getfield position : I
    //   581: if_icmpne -> 637
    //   584: fload #12
    //   586: aload #6
    //   588: getfield widthFactor : F
    //   591: fadd
    //   592: fstore #12
    //   594: iinc #8, -1
    //   597: fload #12
    //   599: fstore #14
    //   601: iload #8
    //   603: istore_1
    //   604: iload #13
    //   606: istore #16
    //   608: iload #8
    //   610: iflt -> 698
    //   613: aload_0
    //   614: getfield mItems : Ljava/util/ArrayList;
    //   617: iload #8
    //   619: invokevirtual get : (I)Ljava/lang/Object;
    //   622: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   625: astore #6
    //   627: fload #12
    //   629: fstore #14
    //   631: iload #8
    //   633: istore_1
    //   634: goto -> 705
    //   637: fload #12
    //   639: aload_0
    //   640: iload #11
    //   642: iload #8
    //   644: iconst_1
    //   645: iadd
    //   646: invokevirtual addNewItem : (II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   649: getfield widthFactor : F
    //   652: fadd
    //   653: fstore #12
    //   655: iinc #13, 1
    //   658: fload #12
    //   660: fstore #14
    //   662: iload #8
    //   664: istore_1
    //   665: iload #13
    //   667: istore #16
    //   669: iload #8
    //   671: iflt -> 698
    //   674: aload_0
    //   675: getfield mItems : Ljava/util/ArrayList;
    //   678: iload #8
    //   680: invokevirtual get : (I)Ljava/lang/Object;
    //   683: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   686: astore #6
    //   688: fload #12
    //   690: fstore #14
    //   692: iload #8
    //   694: istore_1
    //   695: goto -> 705
    //   698: aconst_null
    //   699: astore #6
    //   701: iload #16
    //   703: istore #13
    //   705: iload #13
    //   707: istore #16
    //   709: aload #6
    //   711: astore #15
    //   713: iinc #11, -1
    //   716: fload #14
    //   718: fstore #12
    //   720: iload_1
    //   721: istore #8
    //   723: aload #15
    //   725: astore #6
    //   727: iload #16
    //   729: istore #13
    //   731: goto -> 423
    //   734: aload #7
    //   736: getfield widthFactor : F
    //   739: fstore #12
    //   741: iload #13
    //   743: iconst_1
    //   744: iadd
    //   745: istore #8
    //   747: fload #12
    //   749: fconst_2
    //   750: fcmpg
    //   751: ifge -> 1119
    //   754: iload #8
    //   756: aload_0
    //   757: getfield mItems : Ljava/util/ArrayList;
    //   760: invokevirtual size : ()I
    //   763: if_icmpge -> 783
    //   766: aload_0
    //   767: getfield mItems : Ljava/util/ArrayList;
    //   770: iload #8
    //   772: invokevirtual get : (I)Ljava/lang/Object;
    //   775: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   778: astore #6
    //   780: goto -> 786
    //   783: aconst_null
    //   784: astore #6
    //   786: iload #9
    //   788: ifgt -> 797
    //   791: fconst_0
    //   792: fstore #10
    //   794: goto -> 810
    //   797: aload_0
    //   798: invokevirtual getPaddingRight : ()I
    //   801: i2f
    //   802: iload #9
    //   804: i2f
    //   805: fdiv
    //   806: fconst_2
    //   807: fadd
    //   808: fstore #10
    //   810: aload_0
    //   811: getfield mCurItem : I
    //   814: istore_1
    //   815: aload #6
    //   817: astore #15
    //   819: iload_1
    //   820: iconst_1
    //   821: iadd
    //   822: istore #16
    //   824: iload #16
    //   826: iload #4
    //   828: if_icmpge -> 1119
    //   831: fload #12
    //   833: fload #10
    //   835: fcmpl
    //   836: iflt -> 968
    //   839: iload #16
    //   841: iload #5
    //   843: if_icmple -> 968
    //   846: aload #15
    //   848: ifnonnull -> 854
    //   851: goto -> 1119
    //   854: fload #12
    //   856: fstore #14
    //   858: iload #8
    //   860: istore_1
    //   861: aload #15
    //   863: astore #6
    //   865: iload #16
    //   867: aload #15
    //   869: getfield position : I
    //   872: if_icmpne -> 1102
    //   875: fload #12
    //   877: fstore #14
    //   879: iload #8
    //   881: istore_1
    //   882: aload #15
    //   884: astore #6
    //   886: aload #15
    //   888: getfield scrolling : Z
    //   891: ifne -> 1102
    //   894: aload_0
    //   895: getfield mItems : Ljava/util/ArrayList;
    //   898: iload #8
    //   900: invokevirtual remove : (I)Ljava/lang/Object;
    //   903: pop
    //   904: aload_0
    //   905: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   908: aload_0
    //   909: iload #16
    //   911: aload #15
    //   913: getfield object : Ljava/lang/Object;
    //   916: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   919: fload #12
    //   921: fstore #14
    //   923: iload #8
    //   925: istore_1
    //   926: iload #8
    //   928: aload_0
    //   929: getfield mItems : Ljava/util/ArrayList;
    //   932: invokevirtual size : ()I
    //   935: if_icmpge -> 962
    //   938: aload_0
    //   939: getfield mItems : Ljava/util/ArrayList;
    //   942: iload #8
    //   944: invokevirtual get : (I)Ljava/lang/Object;
    //   947: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   950: astore #6
    //   952: fload #12
    //   954: fstore #14
    //   956: iload #8
    //   958: istore_1
    //   959: goto -> 1102
    //   962: aconst_null
    //   963: astore #6
    //   965: goto -> 1102
    //   968: aload #15
    //   970: ifnull -> 1039
    //   973: iload #16
    //   975: aload #15
    //   977: getfield position : I
    //   980: if_icmpne -> 1039
    //   983: fload #12
    //   985: aload #15
    //   987: getfield widthFactor : F
    //   990: fadd
    //   991: fstore #12
    //   993: iinc #8, 1
    //   996: fload #12
    //   998: fstore #14
    //   1000: iload #8
    //   1002: istore_1
    //   1003: iload #8
    //   1005: aload_0
    //   1006: getfield mItems : Ljava/util/ArrayList;
    //   1009: invokevirtual size : ()I
    //   1012: if_icmpge -> 962
    //   1015: aload_0
    //   1016: getfield mItems : Ljava/util/ArrayList;
    //   1019: iload #8
    //   1021: invokevirtual get : (I)Ljava/lang/Object;
    //   1024: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   1027: astore #6
    //   1029: fload #12
    //   1031: fstore #14
    //   1033: iload #8
    //   1035: istore_1
    //   1036: goto -> 1102
    //   1039: aload_0
    //   1040: iload #16
    //   1042: iload #8
    //   1044: invokevirtual addNewItem : (II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1047: astore #6
    //   1049: iinc #8, 1
    //   1052: fload #12
    //   1054: aload #6
    //   1056: getfield widthFactor : F
    //   1059: fadd
    //   1060: fstore #12
    //   1062: fload #12
    //   1064: fstore #14
    //   1066: iload #8
    //   1068: istore_1
    //   1069: iload #8
    //   1071: aload_0
    //   1072: getfield mItems : Ljava/util/ArrayList;
    //   1075: invokevirtual size : ()I
    //   1078: if_icmpge -> 962
    //   1081: aload_0
    //   1082: getfield mItems : Ljava/util/ArrayList;
    //   1085: iload #8
    //   1087: invokevirtual get : (I)Ljava/lang/Object;
    //   1090: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   1093: astore #6
    //   1095: iload #8
    //   1097: istore_1
    //   1098: fload #12
    //   1100: fstore #14
    //   1102: fload #14
    //   1104: fstore #12
    //   1106: iload_1
    //   1107: istore #8
    //   1109: aload #6
    //   1111: astore #15
    //   1113: iload #16
    //   1115: istore_1
    //   1116: goto -> 819
    //   1119: aload_0
    //   1120: aload #7
    //   1122: iload #13
    //   1124: aload_2
    //   1125: invokespecial calculatePageOffsets : (Landroid/support/v4/view/ViewPager$ItemInfo;ILandroid/support/v4/view/ViewPager$ItemInfo;)V
    //   1128: aload_0
    //   1129: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   1132: aload_0
    //   1133: aload_0
    //   1134: getfield mCurItem : I
    //   1137: aload #7
    //   1139: getfield object : Ljava/lang/Object;
    //   1142: invokevirtual setPrimaryItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   1145: aload_0
    //   1146: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   1149: aload_0
    //   1150: invokevirtual finishUpdate : (Landroid/view/ViewGroup;)V
    //   1153: aload_0
    //   1154: invokevirtual getChildCount : ()I
    //   1157: istore #13
    //   1159: iconst_0
    //   1160: istore_1
    //   1161: iload_1
    //   1162: iload #13
    //   1164: if_icmpge -> 1240
    //   1167: aload_0
    //   1168: iload_1
    //   1169: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1172: astore_2
    //   1173: aload_2
    //   1174: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1177: checkcast android/support/v4/view/ViewPager$LayoutParams
    //   1180: astore #6
    //   1182: aload #6
    //   1184: iload_1
    //   1185: putfield childIndex : I
    //   1188: aload #6
    //   1190: getfield isDecor : Z
    //   1193: ifne -> 1234
    //   1196: aload #6
    //   1198: getfield widthFactor : F
    //   1201: fconst_0
    //   1202: fcmpl
    //   1203: ifne -> 1234
    //   1206: aload_0
    //   1207: aload_2
    //   1208: invokevirtual infoForChild : (Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1211: astore_2
    //   1212: aload_2
    //   1213: ifnull -> 1234
    //   1216: aload #6
    //   1218: aload_2
    //   1219: getfield widthFactor : F
    //   1222: putfield widthFactor : F
    //   1225: aload #6
    //   1227: aload_2
    //   1228: getfield position : I
    //   1231: putfield position : I
    //   1234: iinc #1, 1
    //   1237: goto -> 1161
    //   1240: aload_0
    //   1241: invokespecial sortChildDrawingOrder : ()V
    //   1244: aload_0
    //   1245: invokevirtual hasFocus : ()Z
    //   1248: ifeq -> 1350
    //   1251: aload_0
    //   1252: invokevirtual findFocus : ()Landroid/view/View;
    //   1255: astore #6
    //   1257: aload #6
    //   1259: ifnull -> 1273
    //   1262: aload_0
    //   1263: aload #6
    //   1265: invokevirtual infoForAnyChild : (Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1268: astore #6
    //   1270: goto -> 1276
    //   1273: aconst_null
    //   1274: astore #6
    //   1276: aload #6
    //   1278: ifnull -> 1293
    //   1281: aload #6
    //   1283: getfield position : I
    //   1286: aload_0
    //   1287: getfield mCurItem : I
    //   1290: if_icmpeq -> 1350
    //   1293: iconst_0
    //   1294: istore_1
    //   1295: iload_1
    //   1296: aload_0
    //   1297: invokevirtual getChildCount : ()I
    //   1300: if_icmpge -> 1350
    //   1303: aload_0
    //   1304: iload_1
    //   1305: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1308: astore #6
    //   1310: aload_0
    //   1311: aload #6
    //   1313: invokevirtual infoForChild : (Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1316: astore_2
    //   1317: aload_2
    //   1318: ifnull -> 1344
    //   1321: aload_2
    //   1322: getfield position : I
    //   1325: aload_0
    //   1326: getfield mCurItem : I
    //   1329: if_icmpne -> 1344
    //   1332: aload #6
    //   1334: iconst_2
    //   1335: invokevirtual requestFocus : (I)Z
    //   1338: ifeq -> 1344
    //   1341: goto -> 1350
    //   1344: iinc #1, 1
    //   1347: goto -> 1295
    //   1350: return
    // Exception table:
    //   from	to	target	type
    //   116	129	132	android/content/res/Resources$NotFoundException
  }
  
  public void removeOnAdapterChangeListener(@NonNull OnAdapterChangeListener paramOnAdapterChangeListener) {
    if (this.mAdapterChangeListeners != null)
      this.mAdapterChangeListeners.remove(paramOnAdapterChangeListener); 
  }
  
  public void removeOnPageChangeListener(@NonNull OnPageChangeListener paramOnPageChangeListener) {
    if (this.mOnPageChangeListeners != null)
      this.mOnPageChangeListeners.remove(paramOnPageChangeListener); 
  }
  
  public void removeView(View paramView) {
    if (this.mInLayout) {
      removeViewInLayout(paramView);
    } else {
      super.removeView(paramView);
    } 
  }
  
  public void setAdapter(@Nullable PagerAdapter paramPagerAdapter) {
    PagerAdapter pagerAdapter = this.mAdapter;
    byte b = 0;
    if (pagerAdapter != null) {
      this.mAdapter.setViewPagerObserver(null);
      this.mAdapter.startUpdate(this);
      for (byte b1 = 0; b1 < this.mItems.size(); b1++) {
        ItemInfo itemInfo = this.mItems.get(b1);
        this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
      } 
      this.mAdapter.finishUpdate(this);
      this.mItems.clear();
      removeNonDecorViews();
      this.mCurItem = 0;
      scrollTo(0, 0);
    } 
    pagerAdapter = this.mAdapter;
    this.mAdapter = paramPagerAdapter;
    this.mExpectedAdapterCount = 0;
    if (this.mAdapter != null) {
      if (this.mObserver == null)
        this.mObserver = new PagerObserver(); 
      this.mAdapter.setViewPagerObserver(this.mObserver);
      this.mPopulatePending = false;
      boolean bool = this.mFirstLayout;
      this.mFirstLayout = true;
      this.mExpectedAdapterCount = this.mAdapter.getCount();
      if (this.mRestoredCurItem >= 0) {
        this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
        setCurrentItemInternal(this.mRestoredCurItem, false, true);
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
      } else if (!bool) {
        populate();
      } else {
        requestLayout();
      } 
    } 
    if (this.mAdapterChangeListeners != null && !this.mAdapterChangeListeners.isEmpty()) {
      int i = this.mAdapterChangeListeners.size();
      for (byte b1 = b; b1 < i; b1++)
        ((OnAdapterChangeListener)this.mAdapterChangeListeners.get(b1)).onAdapterChanged(this, pagerAdapter, paramPagerAdapter); 
    } 
  }
  
  public void setCurrentItem(int paramInt) {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, this.mFirstLayout ^ true, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean) {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    int i;
    if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    if (!paramBoolean2 && this.mCurItem == paramInt1 && this.mItems.size() != 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    paramBoolean2 = true;
    if (paramInt1 < 0) {
      i = 0;
    } else {
      i = paramInt1;
      if (paramInt1 >= this.mAdapter.getCount())
        i = this.mAdapter.getCount() - 1; 
    } 
    paramInt1 = this.mOffscreenPageLimit;
    if (i > this.mCurItem + paramInt1 || i < this.mCurItem - paramInt1)
      for (paramInt1 = 0; paramInt1 < this.mItems.size(); paramInt1++)
        ((ItemInfo)this.mItems.get(paramInt1)).scrolling = true;  
    if (this.mCurItem == i)
      paramBoolean2 = false; 
    if (this.mFirstLayout) {
      this.mCurItem = i;
      if (paramBoolean2)
        dispatchOnPageSelected(i); 
      requestLayout();
    } else {
      populate(i);
      scrollToItem(i, paramBoolean1, paramInt2, paramBoolean2);
    } 
  }
  
  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    OnPageChangeListener onPageChangeListener = this.mInternalPageChangeListener;
    this.mInternalPageChangeListener = paramOnPageChangeListener;
    return onPageChangeListener;
  }
  
  public void setOffscreenPageLimit(int paramInt) {
    int i = paramInt;
    if (paramInt < 1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested offscreen page limit ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" too small; defaulting to ");
      stringBuilder.append(1);
      Log.w("ViewPager", stringBuilder.toString());
      i = 1;
    } 
    if (i != this.mOffscreenPageLimit) {
      this.mOffscreenPageLimit = i;
      populate();
    } 
  }
  
  @Deprecated
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    this.mOnPageChangeListener = paramOnPageChangeListener;
  }
  
  public void setPageMargin(int paramInt) {
    int i = this.mPageMargin;
    this.mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }
  
  public void setPageMarginDrawable(@DrawableRes int paramInt) {
    setPageMarginDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setPageMarginDrawable(@Nullable Drawable paramDrawable) {
    boolean bool;
    this.mMarginDrawable = paramDrawable;
    if (paramDrawable != null)
      refreshDrawableState(); 
    if (paramDrawable == null) {
      bool = true;
    } else {
      bool = false;
    } 
    setWillNotDraw(bool);
    invalidate();
  }
  
  public void setPageTransformer(boolean paramBoolean, @Nullable PageTransformer paramPageTransformer) {
    setPageTransformer(paramBoolean, paramPageTransformer, 2);
  }
  
  public void setPageTransformer(boolean paramBoolean, @Nullable PageTransformer paramPageTransformer, int paramInt) {
    boolean bool1;
    boolean bool2;
    boolean bool3;
    byte b = 1;
    if (paramPageTransformer != null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (this.mPageTransformer != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (bool1 != bool2) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    this.mPageTransformer = paramPageTransformer;
    setChildrenDrawingOrderEnabled(bool1);
    if (bool1) {
      if (paramBoolean)
        b = 2; 
      this.mDrawingOrder = b;
      this.mPageTransformerLayerType = paramInt;
    } else {
      this.mDrawingOrder = 0;
    } 
    if (bool3)
      populate(); 
  }
  
  void setScrollState(int paramInt) {
    if (this.mScrollState == paramInt)
      return; 
    this.mScrollState = paramInt;
    if (this.mPageTransformer != null) {
      boolean bool;
      if (paramInt != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      enableLayers(bool);
    } 
    dispatchOnScrollStateChanged(paramInt);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2) {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3) {
    int i;
    if (getChildCount() == 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    if (this.mScroller != null && !this.mScroller.isFinished()) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i) {
      if (this.mIsScrollStarted) {
        i = this.mScroller.getCurrX();
      } else {
        i = this.mScroller.getStartX();
      } 
      this.mScroller.abortAnimation();
      setScrollingCacheEnabled(false);
    } else {
      i = getScrollX();
    } 
    int j = getScrollY();
    int k = paramInt1 - i;
    paramInt2 -= j;
    if (k == 0 && paramInt2 == 0) {
      completeScroll(false);
      populate();
      setScrollState(0);
      return;
    } 
    setScrollingCacheEnabled(true);
    setScrollState(2);
    paramInt1 = getClientWidth();
    int m = paramInt1 / 2;
    float f1 = Math.abs(k);
    float f2 = paramInt1;
    float f3 = Math.min(1.0F, f1 * 1.0F / f2);
    f1 = m;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt1 = Math.abs(paramInt3);
    if (paramInt1 > 0) {
      paramInt1 = Math.round(Math.abs((f1 + f3 * f1) / paramInt1) * 1000.0F) * 4;
    } else {
      f1 = this.mAdapter.getPageWidth(this.mCurItem);
      paramInt1 = (int)((Math.abs(k) / (f2 * f1 + this.mPageMargin) + 1.0F) * 100.0F);
    } 
    paramInt1 = Math.min(paramInt1, 600);
    this.mIsScrollStarted = false;
    this.mScroller.startScroll(i, j, k, paramInt2, paramInt1);
    ViewCompat.postInvalidateOnAnimation((View)this);
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable) {
    return (super.verifyDrawable(paramDrawable) || paramDrawable == this.mMarginDrawable);
  }
  
  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE})
  public static @interface DecorView {}
  
  static class ItemInfo {
    Object object;
    
    float offset;
    
    int position;
    
    boolean scrolling;
    
    float widthFactor;
  }
  
  public static class LayoutParams extends ViewGroup.LayoutParams {
    int childIndex;
    
    public int gravity;
    
    public boolean isDecor;
    
    boolean needsMeasure;
    
    int position;
    
    float widthFactor = 0.0F;
    
    public LayoutParams() {
      super(-1, -1);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, ViewPager.LAYOUT_ATTRS);
      this.gravity = typedArray.getInteger(0, 48);
      typedArray.recycle();
    }
  }
  
  class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
    private boolean canScroll() {
      PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
      boolean bool = true;
      if (pagerAdapter == null || ViewPager.this.mAdapter.getCount() <= 1)
        bool = false; 
      return bool;
    }
    
    public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(ViewPager.class.getName());
      param1AccessibilityEvent.setScrollable(canScroll());
      if (param1AccessibilityEvent.getEventType() == 4096 && ViewPager.this.mAdapter != null) {
        param1AccessibilityEvent.setItemCount(ViewPager.this.mAdapter.getCount());
        param1AccessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
        param1AccessibilityEvent.setToIndex(ViewPager.this.mCurItem);
      } 
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      param1AccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
      param1AccessibilityNodeInfoCompat.setScrollable(canScroll());
      if (ViewPager.this.canScrollHorizontally(1))
        param1AccessibilityNodeInfoCompat.addAction(4096); 
      if (ViewPager.this.canScrollHorizontally(-1))
        param1AccessibilityNodeInfoCompat.addAction(8192); 
    }
    
    public boolean performAccessibilityAction(View param1View, int param1Int, Bundle param1Bundle) {
      if (super.performAccessibilityAction(param1View, param1Int, param1Bundle))
        return true; 
      if (param1Int != 4096) {
        if (param1Int != 8192)
          return false; 
        if (ViewPager.this.canScrollHorizontally(-1)) {
          ViewPager.this.setCurrentItem(ViewPager.this.mCurItem - 1);
          return true;
        } 
        return false;
      } 
      if (ViewPager.this.canScrollHorizontally(1)) {
        ViewPager.this.setCurrentItem(ViewPager.this.mCurItem + 1);
        return true;
      } 
      return false;
    }
  }
  
  public static interface OnAdapterChangeListener {
    void onAdapterChanged(@NonNull ViewPager param1ViewPager, @Nullable PagerAdapter param1PagerAdapter1, @Nullable PagerAdapter param1PagerAdapter2);
  }
  
  public static interface OnPageChangeListener {
    void onPageScrollStateChanged(int param1Int);
    
    void onPageScrolled(int param1Int1, float param1Float, @Px int param1Int2);
    
    void onPageSelected(int param1Int);
  }
  
  public static interface PageTransformer {
    void transformPage(@NonNull View param1View, float param1Float);
  }
  
  private class PagerObserver extends DataSetObserver {
    public void onChanged() {
      ViewPager.this.dataSetChanged();
    }
    
    public void onInvalidated() {
      ViewPager.this.dataSetChanged();
    }
  }
  
  public static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public ViewPager.SavedState createFromParcel(Parcel param2Parcel) {
          return new ViewPager.SavedState(param2Parcel, null);
        }
        
        public ViewPager.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new ViewPager.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public ViewPager.SavedState[] newArray(int param2Int) {
          return new ViewPager.SavedState[param2Int];
        }
      };
    
    Parcelable adapterState;
    
    ClassLoader loader;
    
    int position;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      ClassLoader classLoader = param1ClassLoader;
      if (param1ClassLoader == null)
        classLoader = getClass().getClassLoader(); 
      this.position = param1Parcel.readInt();
      this.adapterState = param1Parcel.readParcelable(classLoader);
      this.loader = classLoader;
    }
    
    public SavedState(@NonNull Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FragmentPager.SavedState{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" position=");
      stringBuilder.append(this.position);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.position);
      param1Parcel.writeParcelable(this.adapterState, param1Int);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public ViewPager.SavedState createFromParcel(Parcel param1Parcel) {
      return new ViewPager.SavedState(param1Parcel, null);
    }
    
    public ViewPager.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new ViewPager.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public ViewPager.SavedState[] newArray(int param1Int) {
      return new ViewPager.SavedState[param1Int];
    }
  }
  
  public static class SimpleOnPageChangeListener implements OnPageChangeListener {
    public void onPageScrollStateChanged(int param1Int) {}
    
    public void onPageScrolled(int param1Int1, float param1Float, int param1Int2) {}
    
    public void onPageSelected(int param1Int) {}
  }
  
  static class ViewPositionComparator implements Comparator<View> {
    public int compare(View param1View1, View param1View2) {
      ViewPager.LayoutParams layoutParams1 = (ViewPager.LayoutParams)param1View1.getLayoutParams();
      ViewPager.LayoutParams layoutParams2 = (ViewPager.LayoutParams)param1View2.getLayoutParams();
      if (layoutParams1.isDecor != layoutParams2.isDecor) {
        byte b;
        if (layoutParams1.isDecor) {
          b = 1;
        } else {
          b = -1;
        } 
        return b;
      } 
      return layoutParams1.position - layoutParams2.position;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\view\ViewPager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */