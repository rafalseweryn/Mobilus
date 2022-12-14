package android.support.v7.widget.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
  static final int ACTION_MODE_DRAG_MASK = 16711680;
  
  private static final int ACTION_MODE_IDLE_MASK = 255;
  
  static final int ACTION_MODE_SWIPE_MASK = 65280;
  
  public static final int ACTION_STATE_DRAG = 2;
  
  public static final int ACTION_STATE_IDLE = 0;
  
  public static final int ACTION_STATE_SWIPE = 1;
  
  private static final int ACTIVE_POINTER_ID_NONE = -1;
  
  public static final int ANIMATION_TYPE_DRAG = 8;
  
  public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
  
  public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
  
  private static final boolean DEBUG = false;
  
  static final int DIRECTION_FLAG_COUNT = 8;
  
  public static final int DOWN = 2;
  
  public static final int END = 32;
  
  public static final int LEFT = 4;
  
  private static final int PIXELS_PER_SECOND = 1000;
  
  public static final int RIGHT = 8;
  
  public static final int START = 16;
  
  private static final String TAG = "ItemTouchHelper";
  
  public static final int UP = 1;
  
  private int mActionState = 0;
  
  int mActivePointerId = -1;
  
  @NonNull
  Callback mCallback;
  
  private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;
  
  private List<Integer> mDistances;
  
  private long mDragScrollStartTimeInMs;
  
  float mDx;
  
  float mDy;
  
  GestureDetectorCompat mGestureDetector;
  
  float mInitialTouchX;
  
  float mInitialTouchY;
  
  private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
  
  private float mMaxSwipeVelocity;
  
  private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() {
      public boolean onInterceptTouchEvent(@NonNull RecyclerView param1RecyclerView, @NonNull MotionEvent param1MotionEvent) {
        ItemTouchHelper.this.mGestureDetector.onTouchEvent(param1MotionEvent);
        int i = param1MotionEvent.getActionMasked();
        boolean bool = true;
        if (i == 0) {
          ItemTouchHelper.this.mActivePointerId = param1MotionEvent.getPointerId(0);
          ItemTouchHelper.this.mInitialTouchX = param1MotionEvent.getX();
          ItemTouchHelper.this.mInitialTouchY = param1MotionEvent.getY();
          ItemTouchHelper.this.obtainVelocityTracker();
          if (ItemTouchHelper.this.mSelected == null) {
            ItemTouchHelper.RecoverAnimation recoverAnimation = ItemTouchHelper.this.findAnimation(param1MotionEvent);
            if (recoverAnimation != null) {
              ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
              itemTouchHelper.mInitialTouchX -= recoverAnimation.mX;
              itemTouchHelper = ItemTouchHelper.this;
              itemTouchHelper.mInitialTouchY -= recoverAnimation.mY;
              ItemTouchHelper.this.endRecoverAnimation(recoverAnimation.mViewHolder, true);
              if (ItemTouchHelper.this.mPendingCleanup.remove(recoverAnimation.mViewHolder.itemView))
                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, recoverAnimation.mViewHolder); 
              ItemTouchHelper.this.select(recoverAnimation.mViewHolder, recoverAnimation.mActionState);
              ItemTouchHelper.this.updateDxDy(param1MotionEvent, ItemTouchHelper.this.mSelectedFlags, 0);
            } 
          } 
        } else if (i == 3 || i == 1) {
          ItemTouchHelper.this.mActivePointerId = -1;
          ItemTouchHelper.this.select(null, 0);
        } else if (ItemTouchHelper.this.mActivePointerId != -1) {
          int j = param1MotionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
          if (j >= 0)
            ItemTouchHelper.this.checkSelectForSwipe(i, param1MotionEvent, j); 
        } 
        if (ItemTouchHelper.this.mVelocityTracker != null)
          ItemTouchHelper.this.mVelocityTracker.addMovement(param1MotionEvent); 
        if (ItemTouchHelper.this.mSelected == null)
          bool = false; 
        return bool;
      }
      
      public void onRequestDisallowInterceptTouchEvent(boolean param1Boolean) {
        if (!param1Boolean)
          return; 
        ItemTouchHelper.this.select(null, 0);
      }
      
      public void onTouchEvent(@NonNull RecyclerView param1RecyclerView, @NonNull MotionEvent param1MotionEvent) {
        ItemTouchHelper.this.mGestureDetector.onTouchEvent(param1MotionEvent);
        if (ItemTouchHelper.this.mVelocityTracker != null)
          ItemTouchHelper.this.mVelocityTracker.addMovement(param1MotionEvent); 
        if (ItemTouchHelper.this.mActivePointerId == -1)
          return; 
        int i = param1MotionEvent.getActionMasked();
        int j = param1MotionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
        if (j >= 0)
          ItemTouchHelper.this.checkSelectForSwipe(i, param1MotionEvent, j); 
        RecyclerView.ViewHolder viewHolder = ItemTouchHelper.this.mSelected;
        if (viewHolder == null)
          return; 
        boolean bool = false;
        if (i != 6) {
          switch (i) {
            default:
              return;
            case 3:
              if (ItemTouchHelper.this.mVelocityTracker != null)
                ItemTouchHelper.this.mVelocityTracker.clear(); 
              break;
            case 2:
              if (j >= 0) {
                ItemTouchHelper.this.updateDxDy(param1MotionEvent, ItemTouchHelper.this.mSelectedFlags, j);
                ItemTouchHelper.this.moveIfNecessary(viewHolder);
                ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                ItemTouchHelper.this.mScrollRunnable.run();
                ItemTouchHelper.this.mRecyclerView.invalidate();
              } 
            case 1:
              break;
          } 
          ItemTouchHelper.this.select(null, 0);
          ItemTouchHelper.this.mActivePointerId = -1;
        } 
        j = param1MotionEvent.getActionIndex();
        if (param1MotionEvent.getPointerId(j) == ItemTouchHelper.this.mActivePointerId) {
          if (j == 0)
            bool = true; 
          ItemTouchHelper.this.mActivePointerId = param1MotionEvent.getPointerId(bool);
          ItemTouchHelper.this.updateDxDy(param1MotionEvent, ItemTouchHelper.this.mSelectedFlags, j);
        } 
      }
    };
  
  View mOverdrawChild = null;
  
  int mOverdrawChildPosition = -1;
  
  final List<View> mPendingCleanup = new ArrayList<>();
  
  List<RecoverAnimation> mRecoverAnimations = new ArrayList<>();
  
  RecyclerView mRecyclerView;
  
  final Runnable mScrollRunnable = new Runnable() {
      public void run() {
        if (ItemTouchHelper.this.mSelected != null && ItemTouchHelper.this.scrollIfNecessary()) {
          if (ItemTouchHelper.this.mSelected != null)
            ItemTouchHelper.this.moveIfNecessary(ItemTouchHelper.this.mSelected); 
          ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
          ViewCompat.postOnAnimation((View)ItemTouchHelper.this.mRecyclerView, this);
        } 
      }
    };
  
  RecyclerView.ViewHolder mSelected = null;
  
  int mSelectedFlags;
  
  private float mSelectedStartX;
  
  private float mSelectedStartY;
  
  private int mSlop;
  
  private List<RecyclerView.ViewHolder> mSwapTargets;
  
  private float mSwipeEscapeVelocity;
  
  private final float[] mTmpPosition = new float[2];
  
  private Rect mTmpRect;
  
  VelocityTracker mVelocityTracker;
  
  public ItemTouchHelper(@NonNull Callback paramCallback) {
    this.mCallback = paramCallback;
  }
  
  private void addChildDrawingOrderCallback() {
    if (Build.VERSION.SDK_INT >= 21)
      return; 
    if (this.mChildDrawingOrderCallback == null)
      this.mChildDrawingOrderCallback = new RecyclerView.ChildDrawingOrderCallback() {
          public int onGetChildDrawingOrder(int param1Int1, int param1Int2) {
            if (ItemTouchHelper.this.mOverdrawChild == null)
              return param1Int2; 
            int i = ItemTouchHelper.this.mOverdrawChildPosition;
            int j = i;
            if (i == -1) {
              j = ItemTouchHelper.this.mRecyclerView.indexOfChild(ItemTouchHelper.this.mOverdrawChild);
              ItemTouchHelper.this.mOverdrawChildPosition = j;
            } 
            if (param1Int2 == param1Int1 - 1)
              return j; 
            if (param1Int2 >= j)
              param1Int2++; 
            return param1Int2;
          }
        }; 
    this.mRecyclerView.setChildDrawingOrderCallback(this.mChildDrawingOrderCallback);
  }
  
  private int checkHorizontalSwipe(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    if ((paramInt & 0xC) != 0) {
      byte b2;
      float f1 = this.mDx;
      byte b1 = 4;
      if (f1 > 0.0F) {
        b2 = 8;
      } else {
        b2 = 4;
      } 
      if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
        float f = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
        f1 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
        if (f > 0.0F)
          b1 = 8; 
        f = Math.abs(f);
        if ((b1 & paramInt) != 0 && b2 == b1 && f >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && f > Math.abs(f1))
          return b1; 
      } 
      f1 = this.mRecyclerView.getWidth();
      float f2 = this.mCallback.getSwipeThreshold(paramViewHolder);
      if ((paramInt & b2) != 0 && Math.abs(this.mDx) > f1 * f2)
        return b2; 
    } 
    return 0;
  }
  
  private int checkVerticalSwipe(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    if ((paramInt & 0x3) != 0) {
      byte b2;
      float f1 = this.mDy;
      byte b1 = 1;
      if (f1 > 0.0F) {
        b2 = 2;
      } else {
        b2 = 1;
      } 
      if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
        f1 = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
        float f = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
        if (f > 0.0F)
          b1 = 2; 
        f = Math.abs(f);
        if ((b1 & paramInt) != 0 && b1 == b2 && f >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && f > Math.abs(f1))
          return b1; 
      } 
      f1 = this.mRecyclerView.getHeight();
      float f2 = this.mCallback.getSwipeThreshold(paramViewHolder);
      if ((paramInt & b2) != 0 && Math.abs(this.mDy) > f1 * f2)
        return b2; 
    } 
    return 0;
  }
  
  private void destroyCallbacks() {
    this.mRecyclerView.removeItemDecoration(this);
    this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
    this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(0);
      this.mCallback.clearView(this.mRecyclerView, recoverAnimation.mViewHolder);
    } 
    this.mRecoverAnimations.clear();
    this.mOverdrawChild = null;
    this.mOverdrawChildPosition = -1;
    releaseVelocityTracker();
    stopGestureDetection();
  }
  
  private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder paramViewHolder) {
    RecyclerView.ViewHolder viewHolder = paramViewHolder;
    if (this.mSwapTargets == null) {
      this.mSwapTargets = new ArrayList<>();
      this.mDistances = new ArrayList<>();
    } else {
      this.mSwapTargets.clear();
      this.mDistances.clear();
    } 
    int i = this.mCallback.getBoundingBoxMargin();
    int j = Math.round(this.mSelectedStartX + this.mDx) - i;
    int k = Math.round(this.mSelectedStartY + this.mDy) - i;
    int m = viewHolder.itemView.getWidth();
    i *= 2;
    int n = m + j + i;
    int i1 = viewHolder.itemView.getHeight() + k + i;
    int i2 = (j + n) / 2;
    int i3 = (k + i1) / 2;
    RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
    int i4 = layoutManager.getChildCount();
    for (m = 0; m < i4; m++) {
      View view = layoutManager.getChildAt(m);
      if (view != paramViewHolder.itemView && view.getBottom() >= k && view.getTop() <= i1 && view.getRight() >= j && view.getLeft() <= n) {
        RecyclerView.ViewHolder viewHolder1 = this.mRecyclerView.getChildViewHolder(view);
        if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, viewHolder1)) {
          int i5 = Math.abs(i2 - (view.getLeft() + view.getRight()) / 2);
          i = Math.abs(i3 - (view.getTop() + view.getBottom()) / 2);
          int i6 = i5 * i5 + i * i;
          int i7 = this.mSwapTargets.size();
          i = 0;
          i5 = 0;
          while (i < i7 && i6 > ((Integer)this.mDistances.get(i)).intValue()) {
            i5++;
            i++;
          } 
          this.mSwapTargets.add(i5, viewHolder1);
          this.mDistances.add(i5, Integer.valueOf(i6));
        } 
      } 
    } 
    return this.mSwapTargets;
  }
  
  private RecyclerView.ViewHolder findSwipedView(MotionEvent paramMotionEvent) {
    RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
    if (this.mActivePointerId == -1)
      return null; 
    int i = paramMotionEvent.findPointerIndex(this.mActivePointerId);
    float f1 = paramMotionEvent.getX(i);
    float f2 = this.mInitialTouchX;
    float f3 = paramMotionEvent.getY(i);
    float f4 = this.mInitialTouchY;
    f2 = Math.abs(f1 - f2);
    f3 = Math.abs(f3 - f4);
    if (f2 < this.mSlop && f3 < this.mSlop)
      return null; 
    if (f2 > f3 && layoutManager.canScrollHorizontally())
      return null; 
    if (f3 > f2 && layoutManager.canScrollVertically())
      return null; 
    View view = findChildView(paramMotionEvent);
    return (view == null) ? null : this.mRecyclerView.getChildViewHolder(view);
  }
  
  private void getSelectedDxDy(float[] paramArrayOffloat) {
    if ((this.mSelectedFlags & 0xC) != 0) {
      paramArrayOffloat[0] = this.mSelectedStartX + this.mDx - this.mSelected.itemView.getLeft();
    } else {
      paramArrayOffloat[0] = this.mSelected.itemView.getTranslationX();
    } 
    if ((this.mSelectedFlags & 0x3) != 0) {
      paramArrayOffloat[1] = this.mSelectedStartY + this.mDy - this.mSelected.itemView.getTop();
    } else {
      paramArrayOffloat[1] = this.mSelected.itemView.getTranslationY();
    } 
  }
  
  private static boolean hitTest(View paramView, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    boolean bool;
    if (paramFloat1 >= paramFloat3 && paramFloat1 <= paramFloat3 + paramView.getWidth() && paramFloat2 >= paramFloat4 && paramFloat2 <= paramFloat4 + paramView.getHeight()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void releaseVelocityTracker() {
    if (this.mVelocityTracker != null) {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    } 
  }
  
  private void setupCallbacks() {
    this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
    this.mRecyclerView.addItemDecoration(this);
    this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
    this.mRecyclerView.addOnChildAttachStateChangeListener(this);
    startGestureDetection();
  }
  
  private void startGestureDetection() {
    this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
    this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), (GestureDetector.OnGestureListener)this.mItemTouchHelperGestureListener);
  }
  
  private void stopGestureDetection() {
    if (this.mItemTouchHelperGestureListener != null) {
      this.mItemTouchHelperGestureListener.doNotReactToLongPress();
      this.mItemTouchHelperGestureListener = null;
    } 
    if (this.mGestureDetector != null)
      this.mGestureDetector = null; 
  }
  
  private int swipeIfNecessary(RecyclerView.ViewHolder paramViewHolder) {
    if (this.mActionState == 2)
      return 0; 
    int i = this.mCallback.getMovementFlags(this.mRecyclerView, paramViewHolder);
    int j = (this.mCallback.convertToAbsoluteDirection(i, ViewCompat.getLayoutDirection((View)this.mRecyclerView)) & 0xFF00) >> 8;
    if (j == 0)
      return 0; 
    i = (i & 0xFF00) >> 8;
    if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
      int k = checkHorizontalSwipe(paramViewHolder, j);
      if (k > 0)
        return ((i & k) == 0) ? Callback.convertToRelativeDirection(k, ViewCompat.getLayoutDirection((View)this.mRecyclerView)) : k; 
      i = checkVerticalSwipe(paramViewHolder, j);
      if (i > 0)
        return i; 
    } else {
      int k = checkVerticalSwipe(paramViewHolder, j);
      if (k > 0)
        return k; 
      j = checkHorizontalSwipe(paramViewHolder, j);
      if (j > 0)
        return ((i & j) == 0) ? Callback.convertToRelativeDirection(j, ViewCompat.getLayoutDirection((View)this.mRecyclerView)) : j; 
    } 
    return 0;
  }
  
  public void attachToRecyclerView(@Nullable RecyclerView paramRecyclerView) {
    if (this.mRecyclerView == paramRecyclerView)
      return; 
    if (this.mRecyclerView != null)
      destroyCallbacks(); 
    this.mRecyclerView = paramRecyclerView;
    if (paramRecyclerView != null) {
      Resources resources = paramRecyclerView.getResources();
      this.mSwipeEscapeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
      this.mMaxSwipeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
      setupCallbacks();
    } 
  }
  
  void checkSelectForSwipe(int paramInt1, MotionEvent paramMotionEvent, int paramInt2) {
    if (this.mSelected != null || paramInt1 != 2 || this.mActionState == 2 || !this.mCallback.isItemViewSwipeEnabled())
      return; 
    if (this.mRecyclerView.getScrollState() == 1)
      return; 
    RecyclerView.ViewHolder viewHolder = findSwipedView(paramMotionEvent);
    if (viewHolder == null)
      return; 
    paramInt1 = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, viewHolder) & 0xFF00) >> 8;
    if (paramInt1 == 0)
      return; 
    float f1 = paramMotionEvent.getX(paramInt2);
    float f2 = paramMotionEvent.getY(paramInt2);
    f1 -= this.mInitialTouchX;
    f2 -= this.mInitialTouchY;
    float f3 = Math.abs(f1);
    float f4 = Math.abs(f2);
    if (f3 < this.mSlop && f4 < this.mSlop)
      return; 
    if (f3 > f4) {
      if (f1 < 0.0F && (paramInt1 & 0x4) == 0)
        return; 
      if (f1 > 0.0F && (paramInt1 & 0x8) == 0)
        return; 
    } else {
      if (f2 < 0.0F && (paramInt1 & 0x1) == 0)
        return; 
      if (f2 > 0.0F && (paramInt1 & 0x2) == 0)
        return; 
    } 
    this.mDy = 0.0F;
    this.mDx = 0.0F;
    this.mActivePointerId = paramMotionEvent.getPointerId(0);
    select(viewHolder, 1);
  }
  
  void endRecoverAnimation(RecyclerView.ViewHolder paramViewHolder, boolean paramBoolean) {
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
      if (recoverAnimation.mViewHolder == paramViewHolder) {
        recoverAnimation.mOverridden |= paramBoolean;
        if (!recoverAnimation.mEnded)
          recoverAnimation.cancel(); 
        this.mRecoverAnimations.remove(i);
        return;
      } 
    } 
  }
  
  RecoverAnimation findAnimation(MotionEvent paramMotionEvent) {
    if (this.mRecoverAnimations.isEmpty())
      return null; 
    View view = findChildView(paramMotionEvent);
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
      if (recoverAnimation.mViewHolder.itemView == view)
        return recoverAnimation; 
    } 
    return null;
  }
  
  View findChildView(MotionEvent paramMotionEvent) {
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    if (this.mSelected != null) {
      View view = this.mSelected.itemView;
      if (hitTest(view, f1, f2, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy))
        return view; 
    } 
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
      View view = recoverAnimation.mViewHolder.itemView;
      if (hitTest(view, f1, f2, recoverAnimation.mX, recoverAnimation.mY))
        return view; 
    } 
    return this.mRecyclerView.findChildViewUnder(f1, f2);
  }
  
  public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    paramRect.setEmpty();
  }
  
  boolean hasRunningRecoverAnim() {
    int i = this.mRecoverAnimations.size();
    for (byte b = 0; b < i; b++) {
      if (!((RecoverAnimation)this.mRecoverAnimations.get(b)).mEnded)
        return true; 
    } 
    return false;
  }
  
  void moveIfNecessary(RecyclerView.ViewHolder paramViewHolder) {
    if (this.mRecyclerView.isLayoutRequested())
      return; 
    if (this.mActionState != 2)
      return; 
    float f = this.mCallback.getMoveThreshold(paramViewHolder);
    int i = (int)(this.mSelectedStartX + this.mDx);
    int j = (int)(this.mSelectedStartY + this.mDy);
    if (Math.abs(j - paramViewHolder.itemView.getTop()) < paramViewHolder.itemView.getHeight() * f && Math.abs(i - paramViewHolder.itemView.getLeft()) < paramViewHolder.itemView.getWidth() * f)
      return; 
    List<RecyclerView.ViewHolder> list = findSwapTargets(paramViewHolder);
    if (list.size() == 0)
      return; 
    RecyclerView.ViewHolder viewHolder = this.mCallback.chooseDropTarget(paramViewHolder, list, i, j);
    if (viewHolder == null) {
      this.mSwapTargets.clear();
      this.mDistances.clear();
      return;
    } 
    int k = viewHolder.getAdapterPosition();
    int m = paramViewHolder.getAdapterPosition();
    if (this.mCallback.onMove(this.mRecyclerView, paramViewHolder, viewHolder))
      this.mCallback.onMoved(this.mRecyclerView, paramViewHolder, m, viewHolder, k, i, j); 
  }
  
  void obtainVelocityTracker() {
    if (this.mVelocityTracker != null)
      this.mVelocityTracker.recycle(); 
    this.mVelocityTracker = VelocityTracker.obtain();
  }
  
  public void onChildViewAttachedToWindow(@NonNull View paramView) {}
  
  public void onChildViewDetachedFromWindow(@NonNull View paramView) {
    removeChildDrawingOrderCallbackIfNecessary(paramView);
    RecyclerView.ViewHolder viewHolder = this.mRecyclerView.getChildViewHolder(paramView);
    if (viewHolder == null)
      return; 
    if (this.mSelected != null && viewHolder == this.mSelected) {
      select(null, 0);
    } else {
      endRecoverAnimation(viewHolder, false);
      if (this.mPendingCleanup.remove(viewHolder.itemView))
        this.mCallback.clearView(this.mRecyclerView, viewHolder); 
    } 
  }
  
  public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    float f1;
    float f2;
    this.mOverdrawChildPosition = -1;
    if (this.mSelected != null) {
      getSelectedDxDy(this.mTmpPosition);
      f1 = this.mTmpPosition[0];
      f2 = this.mTmpPosition[1];
    } else {
      f1 = 0.0F;
      f2 = f1;
    } 
    this.mCallback.onDraw(paramCanvas, paramRecyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f1, f2);
  }
  
  public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    float f1;
    float f2;
    if (this.mSelected != null) {
      getSelectedDxDy(this.mTmpPosition);
      f1 = this.mTmpPosition[0];
      f2 = this.mTmpPosition[1];
    } else {
      f1 = 0.0F;
      f2 = f1;
    } 
    this.mCallback.onDrawOver(paramCanvas, paramRecyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f1, f2);
  }
  
  void postDispatchSwipe(final RecoverAnimation anim, final int swipeDir) {
    this.mRecyclerView.post(new Runnable() {
          public void run() {
            if (ItemTouchHelper.this.mRecyclerView != null && ItemTouchHelper.this.mRecyclerView.isAttachedToWindow() && !anim.mOverridden && anim.mViewHolder.getAdapterPosition() != -1) {
              RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.mRecyclerView.getItemAnimator();
              if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !ItemTouchHelper.this.hasRunningRecoverAnim()) {
                ItemTouchHelper.this.mCallback.onSwiped(anim.mViewHolder, swipeDir);
              } else {
                ItemTouchHelper.this.mRecyclerView.post(this);
              } 
            } 
          }
        });
  }
  
  void removeChildDrawingOrderCallbackIfNecessary(View paramView) {
    if (paramView == this.mOverdrawChild) {
      this.mOverdrawChild = null;
      if (this.mChildDrawingOrderCallback != null)
        this.mRecyclerView.setChildDrawingOrderCallback(null); 
    } 
  }
  
  boolean scrollIfNecessary() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   4: ifnonnull -> 16
    //   7: aload_0
    //   8: ldc2_w -9223372036854775808
    //   11: putfield mDragScrollStartTimeInMs : J
    //   14: iconst_0
    //   15: ireturn
    //   16: invokestatic currentTimeMillis : ()J
    //   19: lstore_1
    //   20: aload_0
    //   21: getfield mDragScrollStartTimeInMs : J
    //   24: ldc2_w -9223372036854775808
    //   27: lcmp
    //   28: ifne -> 36
    //   31: lconst_0
    //   32: lstore_3
    //   33: goto -> 43
    //   36: lload_1
    //   37: aload_0
    //   38: getfield mDragScrollStartTimeInMs : J
    //   41: lsub
    //   42: lstore_3
    //   43: aload_0
    //   44: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   47: invokevirtual getLayoutManager : ()Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   50: astore #5
    //   52: aload_0
    //   53: getfield mTmpRect : Landroid/graphics/Rect;
    //   56: ifnonnull -> 70
    //   59: aload_0
    //   60: new android/graphics/Rect
    //   63: dup
    //   64: invokespecial <init> : ()V
    //   67: putfield mTmpRect : Landroid/graphics/Rect;
    //   70: aload #5
    //   72: aload_0
    //   73: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   76: getfield itemView : Landroid/view/View;
    //   79: aload_0
    //   80: getfield mTmpRect : Landroid/graphics/Rect;
    //   83: invokevirtual calculateItemDecorationsForChild : (Landroid/view/View;Landroid/graphics/Rect;)V
    //   86: aload #5
    //   88: invokevirtual canScrollHorizontally : ()Z
    //   91: ifeq -> 199
    //   94: aload_0
    //   95: getfield mSelectedStartX : F
    //   98: aload_0
    //   99: getfield mDx : F
    //   102: fadd
    //   103: f2i
    //   104: istore #6
    //   106: iload #6
    //   108: aload_0
    //   109: getfield mTmpRect : Landroid/graphics/Rect;
    //   112: getfield left : I
    //   115: isub
    //   116: aload_0
    //   117: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   120: invokevirtual getPaddingLeft : ()I
    //   123: isub
    //   124: istore #7
    //   126: aload_0
    //   127: getfield mDx : F
    //   130: fconst_0
    //   131: fcmpg
    //   132: ifge -> 143
    //   135: iload #7
    //   137: ifge -> 143
    //   140: goto -> 202
    //   143: aload_0
    //   144: getfield mDx : F
    //   147: fconst_0
    //   148: fcmpl
    //   149: ifle -> 199
    //   152: iload #6
    //   154: aload_0
    //   155: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   158: getfield itemView : Landroid/view/View;
    //   161: invokevirtual getWidth : ()I
    //   164: iadd
    //   165: aload_0
    //   166: getfield mTmpRect : Landroid/graphics/Rect;
    //   169: getfield right : I
    //   172: iadd
    //   173: aload_0
    //   174: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   177: invokevirtual getWidth : ()I
    //   180: aload_0
    //   181: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   184: invokevirtual getPaddingRight : ()I
    //   187: isub
    //   188: isub
    //   189: istore #7
    //   191: iload #7
    //   193: ifle -> 199
    //   196: goto -> 202
    //   199: iconst_0
    //   200: istore #7
    //   202: aload #5
    //   204: invokevirtual canScrollVertically : ()Z
    //   207: ifeq -> 315
    //   210: aload_0
    //   211: getfield mSelectedStartY : F
    //   214: aload_0
    //   215: getfield mDy : F
    //   218: fadd
    //   219: f2i
    //   220: istore #8
    //   222: iload #8
    //   224: aload_0
    //   225: getfield mTmpRect : Landroid/graphics/Rect;
    //   228: getfield top : I
    //   231: isub
    //   232: aload_0
    //   233: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   236: invokevirtual getPaddingTop : ()I
    //   239: isub
    //   240: istore #6
    //   242: aload_0
    //   243: getfield mDy : F
    //   246: fconst_0
    //   247: fcmpg
    //   248: ifge -> 259
    //   251: iload #6
    //   253: ifge -> 259
    //   256: goto -> 318
    //   259: aload_0
    //   260: getfield mDy : F
    //   263: fconst_0
    //   264: fcmpl
    //   265: ifle -> 315
    //   268: iload #8
    //   270: aload_0
    //   271: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   274: getfield itemView : Landroid/view/View;
    //   277: invokevirtual getHeight : ()I
    //   280: iadd
    //   281: aload_0
    //   282: getfield mTmpRect : Landroid/graphics/Rect;
    //   285: getfield bottom : I
    //   288: iadd
    //   289: aload_0
    //   290: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   293: invokevirtual getHeight : ()I
    //   296: aload_0
    //   297: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   300: invokevirtual getPaddingBottom : ()I
    //   303: isub
    //   304: isub
    //   305: istore #6
    //   307: iload #6
    //   309: ifle -> 315
    //   312: goto -> 318
    //   315: iconst_0
    //   316: istore #6
    //   318: iload #7
    //   320: istore #8
    //   322: iload #7
    //   324: ifeq -> 360
    //   327: aload_0
    //   328: getfield mCallback : Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;
    //   331: aload_0
    //   332: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   335: aload_0
    //   336: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   339: getfield itemView : Landroid/view/View;
    //   342: invokevirtual getWidth : ()I
    //   345: iload #7
    //   347: aload_0
    //   348: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   351: invokevirtual getWidth : ()I
    //   354: lload_3
    //   355: invokevirtual interpolateOutOfBoundsScroll : (Landroid/support/v7/widget/RecyclerView;IIIJ)I
    //   358: istore #8
    //   360: iload #6
    //   362: ifeq -> 401
    //   365: aload_0
    //   366: getfield mCallback : Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;
    //   369: aload_0
    //   370: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   373: aload_0
    //   374: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   377: getfield itemView : Landroid/view/View;
    //   380: invokevirtual getHeight : ()I
    //   383: iload #6
    //   385: aload_0
    //   386: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   389: invokevirtual getHeight : ()I
    //   392: lload_3
    //   393: invokevirtual interpolateOutOfBoundsScroll : (Landroid/support/v7/widget/RecyclerView;IIIJ)I
    //   396: istore #6
    //   398: goto -> 401
    //   401: iload #8
    //   403: ifne -> 423
    //   406: iload #6
    //   408: ifeq -> 414
    //   411: goto -> 423
    //   414: aload_0
    //   415: ldc2_w -9223372036854775808
    //   418: putfield mDragScrollStartTimeInMs : J
    //   421: iconst_0
    //   422: ireturn
    //   423: aload_0
    //   424: getfield mDragScrollStartTimeInMs : J
    //   427: ldc2_w -9223372036854775808
    //   430: lcmp
    //   431: ifne -> 439
    //   434: aload_0
    //   435: lload_1
    //   436: putfield mDragScrollStartTimeInMs : J
    //   439: aload_0
    //   440: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   443: iload #8
    //   445: iload #6
    //   447: invokevirtual scrollBy : (II)V
    //   450: iconst_1
    //   451: ireturn
  }
  
  void select(@Nullable RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    boolean bool1;
    if (paramViewHolder == this.mSelected && paramInt == this.mActionState)
      return; 
    this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
    int i = this.mActionState;
    endRecoverAnimation(paramViewHolder, true);
    this.mActionState = paramInt;
    if (paramInt == 2) {
      if (paramViewHolder == null)
        throw new IllegalArgumentException("Must pass a ViewHolder when dragging"); 
      this.mOverdrawChild = paramViewHolder.itemView;
      addChildDrawingOrderCallback();
    } 
    if (this.mSelected != null) {
      RecoverAnimation recoverAnimation;
      final RecyclerView.ViewHolder prevSelected = this.mSelected;
      if (viewHolder.itemView.getParent() != null) {
        final int swipeDir;
        float f1;
        float f2;
        if (i == 2) {
          j = 0;
        } else {
          j = swipeIfNecessary(viewHolder);
        } 
        releaseVelocityTracker();
        if (j != 4 && j != 8 && j != 16 && j != 32) {
          float f;
          switch (j) {
            default:
              f1 = 0.0F;
              f2 = f1;
              break;
            case 1:
            case 2:
              f = Math.signum(this.mDy);
              f2 = this.mRecyclerView.getHeight();
              f1 = 0.0F;
              f2 = f * f2;
              break;
          } 
        } else {
          f1 = Math.signum(this.mDx);
          float f = this.mRecyclerView.getWidth();
          f2 = 0.0F;
          f1 *= f;
        } 
        if (i == 2) {
          bool1 = true;
        } else if (j > 0) {
          bool1 = true;
        } else {
          bool1 = true;
        } 
        getSelectedDxDy(this.mTmpPosition);
        float f4 = this.mTmpPosition[0];
        float f3 = this.mTmpPosition[1];
        recoverAnimation = new RecoverAnimation(viewHolder, bool1, i, f4, f3, f1, f2) {
            public void onAnimationEnd(Animator param1Animator) {
              super.onAnimationEnd(param1Animator);
              if (this.mOverridden)
                return; 
              if (swipeDir <= 0) {
                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, prevSelected);
              } else {
                ItemTouchHelper.this.mPendingCleanup.add(prevSelected.itemView);
                this.mIsPendingCleanup = true;
                if (swipeDir > 0)
                  ItemTouchHelper.this.postDispatchSwipe(this, swipeDir); 
              } 
              if (ItemTouchHelper.this.mOverdrawChild == prevSelected.itemView)
                ItemTouchHelper.this.removeChildDrawingOrderCallbackIfNecessary(prevSelected.itemView); 
            }
          };
        recoverAnimation.setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, bool1, f1 - f4, f2 - f3));
        this.mRecoverAnimations.add(recoverAnimation);
        recoverAnimation.start();
        bool1 = true;
      } else {
        removeChildDrawingOrderCallbackIfNecessary(((RecyclerView.ViewHolder)recoverAnimation).itemView);
        this.mCallback.clearView(this.mRecyclerView, (RecyclerView.ViewHolder)recoverAnimation);
        bool1 = false;
      } 
      this.mSelected = null;
    } else {
      bool1 = false;
    } 
    if (paramViewHolder != null) {
      this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, paramViewHolder) & (1 << paramInt * 8 + 8) - 1) >> this.mActionState * 8;
      this.mSelectedStartX = paramViewHolder.itemView.getLeft();
      this.mSelectedStartY = paramViewHolder.itemView.getTop();
      this.mSelected = paramViewHolder;
      if (paramInt == 2)
        this.mSelected.itemView.performHapticFeedback(0); 
    } 
    boolean bool2 = false;
    ViewParent viewParent = this.mRecyclerView.getParent();
    if (viewParent != null) {
      if (this.mSelected != null)
        bool2 = true; 
      viewParent.requestDisallowInterceptTouchEvent(bool2);
    } 
    if (!bool1)
      this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout(); 
    this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
    this.mRecyclerView.invalidate();
  }
  
  public void startDrag(@NonNull RecyclerView.ViewHolder paramViewHolder) {
    if (!this.mCallback.hasDragFlag(this.mRecyclerView, paramViewHolder)) {
      Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
      return;
    } 
    if (paramViewHolder.itemView.getParent() != this.mRecyclerView) {
      Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
      return;
    } 
    obtainVelocityTracker();
    this.mDy = 0.0F;
    this.mDx = 0.0F;
    select(paramViewHolder, 2);
  }
  
  public void startSwipe(@NonNull RecyclerView.ViewHolder paramViewHolder) {
    if (!this.mCallback.hasSwipeFlag(this.mRecyclerView, paramViewHolder)) {
      Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
      return;
    } 
    if (paramViewHolder.itemView.getParent() != this.mRecyclerView) {
      Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
      return;
    } 
    obtainVelocityTracker();
    this.mDy = 0.0F;
    this.mDx = 0.0F;
    select(paramViewHolder, 1);
  }
  
  void updateDxDy(MotionEvent paramMotionEvent, int paramInt1, int paramInt2) {
    float f1 = paramMotionEvent.getX(paramInt2);
    float f2 = paramMotionEvent.getY(paramInt2);
    this.mDx = f1 - this.mInitialTouchX;
    this.mDy = f2 - this.mInitialTouchY;
    if ((paramInt1 & 0x4) == 0)
      this.mDx = Math.max(0.0F, this.mDx); 
    if ((paramInt1 & 0x8) == 0)
      this.mDx = Math.min(0.0F, this.mDx); 
    if ((paramInt1 & 0x1) == 0)
      this.mDy = Math.max(0.0F, this.mDy); 
    if ((paramInt1 & 0x2) == 0)
      this.mDy = Math.min(0.0F, this.mDy); 
  }
  
  public static abstract class Callback {
    private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
    
    public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
    
    public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
    
    private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
    
    static final int RELATIVE_DIR_FLAGS = 3158064;
    
    private static final Interpolator sDragScrollInterpolator = new Interpolator() {
        public float getInterpolation(float param2Float) {
          return param2Float * param2Float * param2Float * param2Float * param2Float;
        }
      };
    
    private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() {
        public float getInterpolation(float param2Float) {
          param2Float--;
          return param2Float * param2Float * param2Float * param2Float * param2Float + 1.0F;
        }
      };
    
    private int mCachedMaxScrollSpeed = -1;
    
    public static int convertToRelativeDirection(int param1Int1, int param1Int2) {
      int i = param1Int1 & 0xC0C0C;
      if (i == 0)
        return param1Int1; 
      param1Int1 &= i ^ 0xFFFFFFFF;
      if (param1Int2 == 0)
        return param1Int1 | i << 2; 
      param1Int2 = i << 1;
      return param1Int1 | 0xFFF3F3F3 & param1Int2 | (param1Int2 & 0xC0C0C) << 2;
    }
    
    @NonNull
    public static ItemTouchUIUtil getDefaultUIUtil() {
      return ItemTouchUIUtilImpl.INSTANCE;
    }
    
    private int getMaxDragScroll(RecyclerView param1RecyclerView) {
      if (this.mCachedMaxScrollSpeed == -1)
        this.mCachedMaxScrollSpeed = param1RecyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame); 
      return this.mCachedMaxScrollSpeed;
    }
    
    public static int makeFlag(int param1Int1, int param1Int2) {
      return param1Int2 << param1Int1 * 8;
    }
    
    public static int makeMovementFlags(int param1Int1, int param1Int2) {
      int i = makeFlag(0, param1Int2 | param1Int1);
      param1Int2 = makeFlag(1, param1Int2);
      return makeFlag(2, param1Int1) | param1Int2 | i;
    }
    
    public boolean canDropOver(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder1, @NonNull RecyclerView.ViewHolder param1ViewHolder2) {
      return true;
    }
    
    public RecyclerView.ViewHolder chooseDropTarget(@NonNull RecyclerView.ViewHolder param1ViewHolder, @NonNull List<RecyclerView.ViewHolder> param1List, int param1Int1, int param1Int2) {
      int i = param1ViewHolder.itemView.getWidth();
      int j = param1ViewHolder.itemView.getHeight();
      int k = param1Int1 - param1ViewHolder.itemView.getLeft();
      int m = param1Int2 - param1ViewHolder.itemView.getTop();
      int n = param1List.size();
      RecyclerView.ViewHolder viewHolder = null;
      int i1 = -1;
      for (byte b = 0; b < n; b++) {
        RecyclerView.ViewHolder viewHolder1 = param1List.get(b);
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        int i2 = i1;
        if (k > 0) {
          int i3 = viewHolder1.itemView.getRight() - i + param1Int1;
          viewHolder2 = viewHolder;
          i2 = i1;
          if (i3 < 0) {
            viewHolder2 = viewHolder;
            i2 = i1;
            if (viewHolder1.itemView.getRight() > param1ViewHolder.itemView.getRight()) {
              i3 = Math.abs(i3);
              viewHolder2 = viewHolder;
              i2 = i1;
              if (i3 > i1) {
                viewHolder2 = viewHolder1;
                i2 = i3;
              } 
            } 
          } 
        } 
        viewHolder = viewHolder2;
        i1 = i2;
        if (k < 0) {
          int i3 = viewHolder1.itemView.getLeft() - param1Int1;
          viewHolder = viewHolder2;
          i1 = i2;
          if (i3 > 0) {
            viewHolder = viewHolder2;
            i1 = i2;
            if (viewHolder1.itemView.getLeft() < param1ViewHolder.itemView.getLeft()) {
              i3 = Math.abs(i3);
              viewHolder = viewHolder2;
              i1 = i2;
              if (i3 > i2) {
                viewHolder = viewHolder1;
                i1 = i3;
              } 
            } 
          } 
        } 
        viewHolder2 = viewHolder;
        i2 = i1;
        if (m < 0) {
          int i3 = viewHolder1.itemView.getTop() - param1Int2;
          viewHolder2 = viewHolder;
          i2 = i1;
          if (i3 > 0) {
            viewHolder2 = viewHolder;
            i2 = i1;
            if (viewHolder1.itemView.getTop() < param1ViewHolder.itemView.getTop()) {
              i3 = Math.abs(i3);
              viewHolder2 = viewHolder;
              i2 = i1;
              if (i3 > i1) {
                viewHolder2 = viewHolder1;
                i2 = i3;
              } 
            } 
          } 
        } 
        viewHolder = viewHolder2;
        i1 = i2;
        if (m > 0) {
          int i3 = viewHolder1.itemView.getBottom() - j + param1Int2;
          viewHolder = viewHolder2;
          i1 = i2;
          if (i3 < 0) {
            viewHolder = viewHolder2;
            i1 = i2;
            if (viewHolder1.itemView.getBottom() > param1ViewHolder.itemView.getBottom()) {
              i3 = Math.abs(i3);
              viewHolder = viewHolder2;
              i1 = i2;
              if (i3 > i2) {
                i1 = i3;
                viewHolder = viewHolder1;
              } 
            } 
          } 
        } 
      } 
      return viewHolder;
    }
    
    public void clearView(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder) {
      ItemTouchUIUtilImpl.INSTANCE.clearView(param1ViewHolder.itemView);
    }
    
    public int convertToAbsoluteDirection(int param1Int1, int param1Int2) {
      int i = param1Int1 & 0x303030;
      if (i == 0)
        return param1Int1; 
      param1Int1 &= i ^ 0xFFFFFFFF;
      if (param1Int2 == 0)
        return i >> 2 | param1Int1; 
      param1Int2 = i >> 1;
      return (0x303030 & param1Int2) >> 2 | param1Int1 | 0xFFCFCFCF & param1Int2;
    }
    
    final int getAbsoluteMovementFlags(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      return convertToAbsoluteDirection(getMovementFlags(param1RecyclerView, param1ViewHolder), ViewCompat.getLayoutDirection((View)param1RecyclerView));
    }
    
    public long getAnimationDuration(@NonNull RecyclerView param1RecyclerView, int param1Int, float param1Float1, float param1Float2) {
      long l;
      RecyclerView.ItemAnimator itemAnimator = param1RecyclerView.getItemAnimator();
      if (itemAnimator == null) {
        if (param1Int == 8) {
          l = 200L;
        } else {
          l = 250L;
        } 
        return l;
      } 
      if (param1Int == 8) {
        l = itemAnimator.getMoveDuration();
      } else {
        l = itemAnimator.getRemoveDuration();
      } 
      return l;
    }
    
    public int getBoundingBoxMargin() {
      return 0;
    }
    
    public float getMoveThreshold(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return 0.5F;
    }
    
    public abstract int getMovementFlags(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder);
    
    public float getSwipeEscapeVelocity(float param1Float) {
      return param1Float;
    }
    
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return 0.5F;
    }
    
    public float getSwipeVelocityThreshold(float param1Float) {
      return param1Float;
    }
    
    boolean hasDragFlag(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      boolean bool;
      if ((getAbsoluteMovementFlags(param1RecyclerView, param1ViewHolder) & 0xFF0000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean hasSwipeFlag(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      boolean bool;
      if ((getAbsoluteMovementFlags(param1RecyclerView, param1ViewHolder) & 0xFF00) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int interpolateOutOfBoundsScroll(@NonNull RecyclerView param1RecyclerView, int param1Int1, int param1Int2, int param1Int3, long param1Long) {
      param1Int3 = getMaxDragScroll(param1RecyclerView);
      int i = Math.abs(param1Int2);
      int j = (int)Math.signum(param1Int2);
      float f1 = i;
      float f2 = 1.0F;
      f1 = Math.min(1.0F, f1 * 1.0F / param1Int1);
      param1Int1 = (int)((j * param1Int3) * sDragViewScrollCapInterpolator.getInterpolation(f1));
      if (param1Long <= 2000L)
        f2 = (float)param1Long / 2000.0F; 
      param1Int1 = (int)(param1Int1 * sDragScrollInterpolator.getInterpolation(f2));
      if (param1Int1 == 0) {
        if (param1Int2 > 0) {
          param1Int1 = 1;
        } else {
          param1Int1 = -1;
        } 
        return param1Int1;
      } 
      return param1Int1;
    }
    
    public boolean isItemViewSwipeEnabled() {
      return true;
    }
    
    public boolean isLongPressDragEnabled() {
      return true;
    }
    
    public void onChildDraw(@NonNull Canvas param1Canvas, @NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder, float param1Float1, float param1Float2, int param1Int, boolean param1Boolean) {
      ItemTouchUIUtilImpl.INSTANCE.onDraw(param1Canvas, param1RecyclerView, param1ViewHolder.itemView, param1Float1, param1Float2, param1Int, param1Boolean);
    }
    
    public void onChildDrawOver(@NonNull Canvas param1Canvas, @NonNull RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder, float param1Float1, float param1Float2, int param1Int, boolean param1Boolean) {
      ItemTouchUIUtilImpl.INSTANCE.onDrawOver(param1Canvas, param1RecyclerView, param1ViewHolder.itemView, param1Float1, param1Float2, param1Int, param1Boolean);
    }
    
    void onDraw(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder, List<ItemTouchHelper.RecoverAnimation> param1List, int param1Int, float param1Float1, float param1Float2) {
      int i = param1List.size();
      int j;
      for (j = 0; j < i; j++) {
        ItemTouchHelper.RecoverAnimation recoverAnimation = param1List.get(j);
        recoverAnimation.update();
        int k = param1Canvas.save();
        onChildDraw(param1Canvas, param1RecyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
        param1Canvas.restoreToCount(k);
      } 
      if (param1ViewHolder != null) {
        j = param1Canvas.save();
        onChildDraw(param1Canvas, param1RecyclerView, param1ViewHolder, param1Float1, param1Float2, param1Int, true);
        param1Canvas.restoreToCount(j);
      } 
    }
    
    void onDrawOver(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder, List<ItemTouchHelper.RecoverAnimation> param1List, int param1Int, float param1Float1, float param1Float2) {
      int i = param1List.size();
      boolean bool = false;
      int j;
      for (j = 0; j < i; j++) {
        ItemTouchHelper.RecoverAnimation recoverAnimation = param1List.get(j);
        int k = param1Canvas.save();
        onChildDrawOver(param1Canvas, param1RecyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
        param1Canvas.restoreToCount(k);
      } 
      if (param1ViewHolder != null) {
        j = param1Canvas.save();
        onChildDrawOver(param1Canvas, param1RecyclerView, param1ViewHolder, param1Float1, param1Float2, param1Int, true);
        param1Canvas.restoreToCount(j);
      } 
      param1Int = i - 1;
      j = bool;
      while (param1Int >= 0) {
        ItemTouchHelper.RecoverAnimation recoverAnimation = param1List.get(param1Int);
        if (recoverAnimation.mEnded && !recoverAnimation.mIsPendingCleanup) {
          param1List.remove(param1Int);
        } else if (!recoverAnimation.mEnded) {
          j = 1;
        } 
        param1Int--;
      } 
      if (j != 0)
        param1RecyclerView.invalidate(); 
    }
    
    public abstract boolean onMove(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder1, @NonNull RecyclerView.ViewHolder param1ViewHolder2);
    
    public void onMoved(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder1, int param1Int1, @NonNull RecyclerView.ViewHolder param1ViewHolder2, int param1Int2, int param1Int3, int param1Int4) {
      RecyclerView.LayoutManager layoutManager = param1RecyclerView.getLayoutManager();
      if (layoutManager instanceof ItemTouchHelper.ViewDropHandler) {
        ((ItemTouchHelper.ViewDropHandler)layoutManager).prepareForDrop(param1ViewHolder1.itemView, param1ViewHolder2.itemView, param1Int3, param1Int4);
        return;
      } 
      if (layoutManager.canScrollHorizontally()) {
        if (layoutManager.getDecoratedLeft(param1ViewHolder2.itemView) <= param1RecyclerView.getPaddingLeft())
          param1RecyclerView.scrollToPosition(param1Int2); 
        if (layoutManager.getDecoratedRight(param1ViewHolder2.itemView) >= param1RecyclerView.getWidth() - param1RecyclerView.getPaddingRight())
          param1RecyclerView.scrollToPosition(param1Int2); 
      } 
      if (layoutManager.canScrollVertically()) {
        if (layoutManager.getDecoratedTop(param1ViewHolder2.itemView) <= param1RecyclerView.getPaddingTop())
          param1RecyclerView.scrollToPosition(param1Int2); 
        if (layoutManager.getDecoratedBottom(param1ViewHolder2.itemView) >= param1RecyclerView.getHeight() - param1RecyclerView.getPaddingBottom())
          param1RecyclerView.scrollToPosition(param1Int2); 
      } 
    }
    
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder param1ViewHolder, int param1Int) {
      if (param1ViewHolder != null)
        ItemTouchUIUtilImpl.INSTANCE.onSelected(param1ViewHolder.itemView); 
    }
    
    public abstract void onSwiped(@NonNull RecyclerView.ViewHolder param1ViewHolder, int param1Int);
  }
  
  static final class null implements Interpolator {
    public float getInterpolation(float param1Float) {
      return param1Float * param1Float * param1Float * param1Float * param1Float;
    }
  }
  
  static final class null implements Interpolator {
    public float getInterpolation(float param1Float) {
      param1Float--;
      return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
    }
  }
  
  private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
    private boolean mShouldReactToLongPress = true;
    
    void doNotReactToLongPress() {
      this.mShouldReactToLongPress = false;
    }
    
    public boolean onDown(MotionEvent param1MotionEvent) {
      return true;
    }
    
    public void onLongPress(MotionEvent param1MotionEvent) {
      if (!this.mShouldReactToLongPress)
        return; 
      View view = ItemTouchHelper.this.findChildView(param1MotionEvent);
      if (view != null) {
        RecyclerView.ViewHolder viewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(view);
        if (viewHolder != null) {
          if (!ItemTouchHelper.this.mCallback.hasDragFlag(ItemTouchHelper.this.mRecyclerView, viewHolder))
            return; 
          if (param1MotionEvent.getPointerId(0) == ItemTouchHelper.this.mActivePointerId) {
            int i = param1MotionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
            float f1 = param1MotionEvent.getX(i);
            float f2 = param1MotionEvent.getY(i);
            ItemTouchHelper.this.mInitialTouchX = f1;
            ItemTouchHelper.this.mInitialTouchY = f2;
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            ItemTouchHelper.this.mDy = 0.0F;
            itemTouchHelper.mDx = 0.0F;
            if (ItemTouchHelper.this.mCallback.isLongPressDragEnabled())
              ItemTouchHelper.this.select(viewHolder, 2); 
          } 
        } 
      } 
    }
  }
  
  private static class RecoverAnimation implements Animator.AnimatorListener {
    final int mActionState;
    
    final int mAnimationType;
    
    boolean mEnded = false;
    
    private float mFraction;
    
    boolean mIsPendingCleanup;
    
    boolean mOverridden = false;
    
    final float mStartDx;
    
    final float mStartDy;
    
    final float mTargetX;
    
    final float mTargetY;
    
    private final ValueAnimator mValueAnimator;
    
    final RecyclerView.ViewHolder mViewHolder;
    
    float mX;
    
    float mY;
    
    RecoverAnimation(RecyclerView.ViewHolder param1ViewHolder, int param1Int1, int param1Int2, float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
      this.mActionState = param1Int2;
      this.mAnimationType = param1Int1;
      this.mViewHolder = param1ViewHolder;
      this.mStartDx = param1Float1;
      this.mStartDy = param1Float2;
      this.mTargetX = param1Float3;
      this.mTargetY = param1Float4;
      this.mValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator param2ValueAnimator) {
              ItemTouchHelper.RecoverAnimation.this.setFraction(param2ValueAnimator.getAnimatedFraction());
            }
          });
      this.mValueAnimator.setTarget(param1ViewHolder.itemView);
      this.mValueAnimator.addListener(this);
      setFraction(0.0F);
    }
    
    public void cancel() {
      this.mValueAnimator.cancel();
    }
    
    public void onAnimationCancel(Animator param1Animator) {
      setFraction(1.0F);
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      if (!this.mEnded)
        this.mViewHolder.setIsRecyclable(true); 
      this.mEnded = true;
    }
    
    public void onAnimationRepeat(Animator param1Animator) {}
    
    public void onAnimationStart(Animator param1Animator) {}
    
    public void setDuration(long param1Long) {
      this.mValueAnimator.setDuration(param1Long);
    }
    
    public void setFraction(float param1Float) {
      this.mFraction = param1Float;
    }
    
    public void start() {
      this.mViewHolder.setIsRecyclable(false);
      this.mValueAnimator.start();
    }
    
    public void update() {
      if (this.mStartDx == this.mTargetX) {
        this.mX = this.mViewHolder.itemView.getTranslationX();
      } else {
        this.mX = this.mStartDx + this.mFraction * (this.mTargetX - this.mStartDx);
      } 
      if (this.mStartDy == this.mTargetY) {
        this.mY = this.mViewHolder.itemView.getTranslationY();
      } else {
        this.mY = this.mStartDy + this.mFraction * (this.mTargetY - this.mStartDy);
      } 
    }
  }
  
  class null implements ValueAnimator.AnimatorUpdateListener {
    public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
      this.this$0.setFraction(param1ValueAnimator.getAnimatedFraction());
    }
  }
  
  public static abstract class SimpleCallback extends Callback {
    private int mDefaultDragDirs;
    
    private int mDefaultSwipeDirs;
    
    public SimpleCallback(int param1Int1, int param1Int2) {
      this.mDefaultSwipeDirs = param1Int2;
      this.mDefaultDragDirs = param1Int1;
    }
    
    public int getDragDirs(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return this.mDefaultDragDirs;
    }
    
    public int getMovementFlags(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return makeMovementFlags(getDragDirs(param1RecyclerView, param1ViewHolder), getSwipeDirs(param1RecyclerView, param1ViewHolder));
    }
    
    public int getSwipeDirs(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return this.mDefaultSwipeDirs;
    }
    
    public void setDefaultDragDirs(int param1Int) {
      this.mDefaultDragDirs = param1Int;
    }
    
    public void setDefaultSwipeDirs(int param1Int) {
      this.mDefaultSwipeDirs = param1Int;
    }
  }
  
  public static interface ViewDropHandler {
    void prepareForDrop(@NonNull View param1View1, @NonNull View param1View2, int param1Int1, int param1Int2);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\helper\ItemTouchHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */