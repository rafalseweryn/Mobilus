package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.Preconditions;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.recyclerview.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2 {
  static {
    CLIP_TO_PADDING_ATTR = new int[] { 16842987 };
    if (Build.VERSION.SDK_INT == 18 || Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20) {
      bool = true;
    } else {
      bool = false;
    } 
    FORCE_INVALIDATE_DISPLAY_LIST = bool;
    if (Build.VERSION.SDK_INT >= 23) {
      bool = true;
    } else {
      bool = false;
    } 
    ALLOW_SIZE_IN_UNSPECIFIED_SPEC = bool;
    if (Build.VERSION.SDK_INT >= 16) {
      bool = true;
    } else {
      bool = false;
    } 
    POST_UPDATES_ON_ANIMATION = bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    } 
    ALLOW_THREAD_GAP_WORK = bool;
    if (Build.VERSION.SDK_INT <= 15) {
      bool = true;
    } else {
      bool = false;
    } 
    FORCE_ABS_FOCUS_SEARCH_DIRECTION = bool;
    if (Build.VERSION.SDK_INT <= 15) {
      bool = true;
    } else {
      bool = false;
    } 
    IGNORE_DETACHED_FOCUSED_CHILD = bool;
    LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class, AttributeSet.class, int.class, int.class };
    sQuinticInterpolator = new Interpolator() {
        public float getInterpolation(float param1Float) {
          param1Float--;
          return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
        }
      };
  }
  
  public RecyclerView(@NonNull Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public RecyclerView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RecyclerView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl;
    boolean bool2;
    this.mObserver = new RecyclerViewDataObserver();
    this.mRecycler = new Recycler();
    this.mViewInfoStore = new ViewInfoStore();
    this.mUpdateChildViewsRunnable = new Runnable() {
        public void run() {
          if (!RecyclerView.this.mFirstLayoutComplete || RecyclerView.this.isLayoutRequested())
            return; 
          if (!RecyclerView.this.mIsAttached) {
            RecyclerView.this.requestLayout();
            return;
          } 
          if (RecyclerView.this.mLayoutFrozen) {
            RecyclerView.this.mLayoutWasDefered = true;
            return;
          } 
          RecyclerView.this.consumePendingUpdateOperations();
        }
      };
    this.mTempRect = new Rect();
    this.mTempRect2 = new Rect();
    this.mTempRectF = new RectF();
    this.mItemDecorations = new ArrayList<>();
    this.mOnItemTouchListeners = new ArrayList<>();
    this.mInterceptRequestLayoutDepth = 0;
    this.mDataSetHasChangedAfterLayout = false;
    this.mDispatchItemsChangedEvent = false;
    this.mLayoutOrScrollCounter = 0;
    this.mDispatchScrollCounter = 0;
    this.mEdgeEffectFactory = new EdgeEffectFactory();
    this.mItemAnimator = new DefaultItemAnimator();
    this.mScrollState = 0;
    this.mScrollPointerId = -1;
    this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
    this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
    boolean bool1 = true;
    this.mPreserveFocusAfterLayout = true;
    this.mViewFlinger = new ViewFlinger();
    if (ALLOW_THREAD_GAP_WORK) {
      layoutPrefetchRegistryImpl = new GapWorker.LayoutPrefetchRegistryImpl();
    } else {
      layoutPrefetchRegistryImpl = null;
    } 
    this.mPrefetchRegistry = layoutPrefetchRegistryImpl;
    this.mState = new State();
    this.mItemsAddedOrRemoved = false;
    this.mItemsChanged = false;
    this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
    this.mPostedAnimatorRunner = false;
    this.mMinMaxLayoutPositions = new int[2];
    this.mScrollOffset = new int[2];
    this.mScrollConsumed = new int[2];
    this.mNestedOffsets = new int[2];
    this.mScrollStepConsumed = new int[2];
    this.mPendingAccessibilityImportanceChange = new ArrayList<>();
    this.mItemAnimatorRunner = new Runnable() {
        public void run() {
          if (RecyclerView.this.mItemAnimator != null)
            RecyclerView.this.mItemAnimator.runPendingAnimations(); 
          RecyclerView.this.mPostedAnimatorRunner = false;
        }
      };
    this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {
        public void processAppeared(RecyclerView.ViewHolder param1ViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2) {
          RecyclerView.this.animateAppearance(param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2);
        }
        
        public void processDisappeared(RecyclerView.ViewHolder param1ViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2) {
          RecyclerView.this.mRecycler.unscrapView(param1ViewHolder);
          RecyclerView.this.animateDisappearance(param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2);
        }
        
        public void processPersistent(RecyclerView.ViewHolder param1ViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2) {
          param1ViewHolder.setIsRecyclable(false);
          if (RecyclerView.this.mDataSetHasChangedAfterLayout) {
            if (RecyclerView.this.mItemAnimator.animateChange(param1ViewHolder, param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2))
              RecyclerView.this.postAnimationRunner(); 
          } else if (RecyclerView.this.mItemAnimator.animatePersistence(param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2)) {
            RecyclerView.this.postAnimationRunner();
          } 
        }
        
        public void unused(RecyclerView.ViewHolder param1ViewHolder) {
          RecyclerView.this.mLayout.removeAndRecycleView(param1ViewHolder.itemView, RecyclerView.this.mRecycler);
        }
      };
    if (paramAttributeSet != null) {
      TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, CLIP_TO_PADDING_ATTR, paramInt, 0);
      this.mClipToPadding = typedArray.getBoolean(0, true);
      typedArray.recycle();
    } else {
      this.mClipToPadding = true;
    } 
    setScrollContainer(true);
    setFocusableInTouchMode(true);
    ViewConfiguration viewConfiguration = ViewConfiguration.get(paramContext);
    this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, paramContext);
    this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, paramContext);
    this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    if (getOverScrollMode() == 2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    setWillNotDraw(bool2);
    this.mItemAnimator.setListener(this.mItemAnimatorListener);
    initAdapterManager();
    initChildrenHelper();
    initAutofill();
    if (ViewCompat.getImportantForAccessibility((View)this) == 0)
      ViewCompat.setImportantForAccessibility((View)this, 1); 
    this.mAccessibilityManager = (AccessibilityManager)getContext().getSystemService("accessibility");
    setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
    if (paramAttributeSet != null) {
      TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RecyclerView, paramInt, 0);
      String str = typedArray.getString(R.styleable.RecyclerView_layoutManager);
      if (typedArray.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1)
        setDescendantFocusability(262144); 
      this.mEnableFastScroller = typedArray.getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
      if (this.mEnableFastScroller)
        initFastScroller((StateListDrawable)typedArray.getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), typedArray.getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable)typedArray.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), typedArray.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable)); 
      typedArray.recycle();
      createLayoutManager(paramContext, str, paramAttributeSet, paramInt, 0);
      bool2 = bool1;
      if (Build.VERSION.SDK_INT >= 21) {
        TypedArray typedArray1 = paramContext.obtainStyledAttributes(paramAttributeSet, NESTED_SCROLLING_ATTRS, paramInt, 0);
        bool2 = typedArray1.getBoolean(0, true);
        typedArray1.recycle();
      } 
    } else {
      setDescendantFocusability(262144);
      bool2 = bool1;
    } 
    setNestedScrollingEnabled(bool2);
  }
  
  private void addAnimatingView(ViewHolder paramViewHolder) {
    boolean bool;
    View view = paramViewHolder.itemView;
    if (view.getParent() == this) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mRecycler.unscrapView(getChildViewHolder(view));
    if (paramViewHolder.isTmpDetached()) {
      this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
    } else if (!bool) {
      this.mChildHelper.addView(view, true);
    } else {
      this.mChildHelper.hide(view);
    } 
  }
  
  private void animateChange(@NonNull ViewHolder paramViewHolder1, @NonNull ViewHolder paramViewHolder2, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo2, boolean paramBoolean1, boolean paramBoolean2) {
    paramViewHolder1.setIsRecyclable(false);
    if (paramBoolean1)
      addAnimatingView(paramViewHolder1); 
    if (paramViewHolder1 != paramViewHolder2) {
      if (paramBoolean2)
        addAnimatingView(paramViewHolder2); 
      paramViewHolder1.mShadowedHolder = paramViewHolder2;
      addAnimatingView(paramViewHolder1);
      this.mRecycler.unscrapView(paramViewHolder1);
      paramViewHolder2.setIsRecyclable(false);
      paramViewHolder2.mShadowingHolder = paramViewHolder1;
    } 
    if (this.mItemAnimator.animateChange(paramViewHolder1, paramViewHolder2, paramItemHolderInfo1, paramItemHolderInfo2))
      postAnimationRunner(); 
  }
  
  private void cancelTouch() {
    resetTouch();
    setScrollState(0);
  }
  
  static void clearNestedRecyclerViewIfNotNested(@NonNull ViewHolder paramViewHolder) {
    if (paramViewHolder.mNestedRecyclerView != null) {
      View view = (View)paramViewHolder.mNestedRecyclerView.get();
      while (view != null) {
        if (view == paramViewHolder.itemView)
          return; 
        ViewParent viewParent = view.getParent();
        if (viewParent instanceof View) {
          View view1 = (View)viewParent;
          continue;
        } 
        viewParent = null;
      } 
      paramViewHolder.mNestedRecyclerView = null;
    } 
  }
  
  private void createLayoutManager(Context paramContext, String paramString, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    if (paramString != null) {
      paramString = paramString.trim();
      if (!paramString.isEmpty()) {
        String str = getFullClassName(paramContext, paramString);
        try {
          StringBuilder stringBuilder;
          ClassLoader classLoader;
          if (isInEditMode()) {
            classLoader = getClass().getClassLoader();
          } else {
            classLoader = paramContext.getClassLoader();
          } 
          Class<? extends LayoutManager> clazz = classLoader.loadClass(str).asSubclass(LayoutManager.class);
          NoSuchMethodException noSuchMethodException2 = null;
          try {
            Constructor<? extends LayoutManager> constructor = clazz.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
            Object[] arrayOfObject = { paramContext, paramAttributeSet, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) };
          } catch (NoSuchMethodException noSuchMethodException) {
            try {
              Constructor<? extends LayoutManager> constructor = clazz.getConstructor(new Class[0]);
              noSuchMethodException = noSuchMethodException2;
              constructor.setAccessible(true);
              setLayoutManager(constructor.newInstance((Object[])noSuchMethodException));
            } catch (NoSuchMethodException noSuchMethodException1) {
              noSuchMethodException1.initCause(noSuchMethodException);
              IllegalStateException illegalStateException = new IllegalStateException();
              stringBuilder = new StringBuilder();
              this();
              stringBuilder.append(paramAttributeSet.getPositionDescription());
              stringBuilder.append(": Error creating LayoutManager ");
              stringBuilder.append(str);
              this(stringBuilder.toString(), noSuchMethodException1);
              throw illegalStateException;
            } 
          } 
          noSuchMethodException1.setAccessible(true);
          setLayoutManager(noSuchMethodException1.newInstance((Object[])stringBuilder));
        } catch (ClassNotFoundException classNotFoundException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Unable to find LayoutManager ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), classNotFoundException);
        } catch (InvocationTargetException invocationTargetException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Could not instantiate the LayoutManager: ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), invocationTargetException);
        } catch (InstantiationException instantiationException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Could not instantiate the LayoutManager: ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), instantiationException);
        } catch (IllegalAccessException illegalAccessException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Cannot access non-public constructor ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), illegalAccessException);
        } catch (ClassCastException classCastException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Class is not a LayoutManager ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), classCastException);
        } 
      } 
    } 
  }
  
  private boolean didChildRangeChange(int paramInt1, int paramInt2) {
    findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
    int[] arrayOfInt = this.mMinMaxLayoutPositions;
    boolean bool = false;
    if (arrayOfInt[0] != paramInt1 || this.mMinMaxLayoutPositions[1] != paramInt2)
      bool = true; 
    return bool;
  }
  
  private void dispatchContentChangedIfNecessary() {
    int i = this.mEatenAccessibilityChangeFlags;
    this.mEatenAccessibilityChangeFlags = 0;
    if (i != 0 && isAccessibilityEnabled()) {
      AccessibilityEvent accessibilityEvent = AccessibilityEvent.obtain();
      accessibilityEvent.setEventType(2048);
      AccessibilityEventCompat.setContentChangeTypes(accessibilityEvent, i);
      sendAccessibilityEventUnchecked(accessibilityEvent);
    } 
  }
  
  private void dispatchLayoutStep1() {
    State state = this.mState;
    boolean bool = true;
    state.assertLayoutStep(1);
    fillRemainingScrollValues(this.mState);
    this.mState.mIsMeasuring = false;
    startInterceptRequestLayout();
    this.mViewInfoStore.clear();
    onEnterLayoutOrScroll();
    processAdapterUpdatesAndSetAnimationFlags();
    saveFocusInfo();
    state = this.mState;
    if (!this.mState.mRunSimpleAnimations || !this.mItemsChanged)
      bool = false; 
    state.mTrackOldChangeHolders = bool;
    this.mItemsChanged = false;
    this.mItemsAddedOrRemoved = false;
    this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
    this.mState.mItemCount = this.mAdapter.getItemCount();
    findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
    if (this.mState.mRunSimpleAnimations) {
      int i = this.mChildHelper.getChildCount();
      for (byte b = 0; b < i; b++) {
        ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
        if (!viewHolder.shouldIgnore() && (!viewHolder.isInvalid() || this.mAdapter.hasStableIds())) {
          ItemAnimator.ItemHolderInfo itemHolderInfo = this.mItemAnimator.recordPreLayoutInformation(this.mState, viewHolder, ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder), viewHolder.getUnmodifiedPayloads());
          this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
          if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore() && !viewHolder.isInvalid()) {
            long l = getChangedHolderKey(viewHolder);
            this.mViewInfoStore.addToOldChangeHolders(l, viewHolder);
          } 
        } 
      } 
    } 
    if (this.mState.mRunPredictiveAnimations) {
      saveOldPositions();
      bool = this.mState.mStructureChanged;
      this.mState.mStructureChanged = false;
      this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
      this.mState.mStructureChanged = bool;
      for (byte b = 0; b < this.mChildHelper.getChildCount(); b++) {
        ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
        if (!viewHolder.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(viewHolder)) {
          int j = ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder);
          bool = viewHolder.hasAnyOfTheFlags(8192);
          int i = j;
          if (!bool)
            i = j | 0x1000; 
          ItemAnimator.ItemHolderInfo itemHolderInfo = this.mItemAnimator.recordPreLayoutInformation(this.mState, viewHolder, i, viewHolder.getUnmodifiedPayloads());
          if (bool) {
            recordAnimationInfoIfBouncedHiddenView(viewHolder, itemHolderInfo);
          } else {
            this.mViewInfoStore.addToAppearedInPreLayoutHolders(viewHolder, itemHolderInfo);
          } 
        } 
      } 
      clearOldPositions();
    } else {
      clearOldPositions();
    } 
    onExitLayoutOrScroll();
    stopInterceptRequestLayout(false);
    this.mState.mLayoutStep = 2;
  }
  
  private void dispatchLayoutStep2() {
    boolean bool;
    startInterceptRequestLayout();
    onEnterLayoutOrScroll();
    this.mState.assertLayoutStep(6);
    this.mAdapterHelper.consumeUpdatesInOnePass();
    this.mState.mItemCount = this.mAdapter.getItemCount();
    this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
    this.mState.mInPreLayout = false;
    this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
    this.mState.mStructureChanged = false;
    this.mPendingSavedState = null;
    State state = this.mState;
    if (this.mState.mRunSimpleAnimations && this.mItemAnimator != null) {
      bool = true;
    } else {
      bool = false;
    } 
    state.mRunSimpleAnimations = bool;
    this.mState.mLayoutStep = 4;
    onExitLayoutOrScroll();
    stopInterceptRequestLayout(false);
  }
  
  private void dispatchLayoutStep3() {
    this.mState.assertLayoutStep(4);
    startInterceptRequestLayout();
    onEnterLayoutOrScroll();
    this.mState.mLayoutStep = 1;
    if (this.mState.mRunSimpleAnimations) {
      for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; i--) {
        ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
        if (!viewHolder.shouldIgnore()) {
          long l = getChangedHolderKey(viewHolder);
          ItemAnimator.ItemHolderInfo itemHolderInfo = this.mItemAnimator.recordPostLayoutInformation(this.mState, viewHolder);
          ViewHolder viewHolder1 = this.mViewInfoStore.getFromOldChangeHolders(l);
          if (viewHolder1 != null && !viewHolder1.shouldIgnore()) {
            boolean bool1 = this.mViewInfoStore.isDisappearing(viewHolder1);
            boolean bool2 = this.mViewInfoStore.isDisappearing(viewHolder);
            if (bool1 && viewHolder1 == viewHolder) {
              this.mViewInfoStore.addToPostLayout(viewHolder, itemHolderInfo);
            } else {
              ItemAnimator.ItemHolderInfo itemHolderInfo1 = this.mViewInfoStore.popFromPreLayout(viewHolder1);
              this.mViewInfoStore.addToPostLayout(viewHolder, itemHolderInfo);
              itemHolderInfo = this.mViewInfoStore.popFromPostLayout(viewHolder);
              if (itemHolderInfo1 == null) {
                handleMissingPreInfoForChangeError(l, viewHolder, viewHolder1);
              } else {
                animateChange(viewHolder1, viewHolder, itemHolderInfo1, itemHolderInfo, bool1, bool2);
              } 
            } 
          } else {
            this.mViewInfoStore.addToPostLayout(viewHolder, itemHolderInfo);
          } 
        } 
      } 
      this.mViewInfoStore.process(this.mViewInfoProcessCallback);
    } 
    this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
    this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
    this.mDataSetHasChangedAfterLayout = false;
    this.mDispatchItemsChangedEvent = false;
    this.mState.mRunSimpleAnimations = false;
    this.mState.mRunPredictiveAnimations = false;
    this.mLayout.mRequestedSimpleAnimations = false;
    if (this.mRecycler.mChangedScrap != null)
      this.mRecycler.mChangedScrap.clear(); 
    if (this.mLayout.mPrefetchMaxObservedInInitialPrefetch) {
      this.mLayout.mPrefetchMaxCountObserved = 0;
      this.mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
      this.mRecycler.updateViewCacheSize();
    } 
    this.mLayout.onLayoutCompleted(this.mState);
    onExitLayoutOrScroll();
    stopInterceptRequestLayout(false);
    this.mViewInfoStore.clear();
    if (didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1]))
      dispatchOnScrolled(0, 0); 
    recoverFocusFromState();
    resetFocusInfo();
  }
  
  private boolean dispatchOnItemTouch(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction();
    if (this.mActiveOnItemTouchListener != null)
      if (i == 0) {
        this.mActiveOnItemTouchListener = null;
      } else {
        this.mActiveOnItemTouchListener.onTouchEvent(this, paramMotionEvent);
        if (i == 3 || i == 1)
          this.mActiveOnItemTouchListener = null; 
        return true;
      }  
    if (i != 0) {
      int j = this.mOnItemTouchListeners.size();
      for (i = 0; i < j; i++) {
        OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(i);
        if (onItemTouchListener.onInterceptTouchEvent(this, paramMotionEvent)) {
          this.mActiveOnItemTouchListener = onItemTouchListener;
          return true;
        } 
      } 
    } 
    return false;
  }
  
  private boolean dispatchOnItemTouchIntercept(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction();
    if (i == 3 || i == 0)
      this.mActiveOnItemTouchListener = null; 
    int j = this.mOnItemTouchListeners.size();
    for (byte b = 0; b < j; b++) {
      OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(b);
      if (onItemTouchListener.onInterceptTouchEvent(this, paramMotionEvent) && i != 3) {
        this.mActiveOnItemTouchListener = onItemTouchListener;
        return true;
      } 
    } 
    return false;
  }
  
  private void findMinMaxChildLayoutPositions(int[] paramArrayOfint) {
    int i = this.mChildHelper.getChildCount();
    if (i == 0) {
      paramArrayOfint[0] = -1;
      paramArrayOfint[1] = -1;
      return;
    } 
    int j = Integer.MIN_VALUE;
    int k = Integer.MAX_VALUE;
    byte b = 0;
    while (b < i) {
      int m;
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
      if (viewHolder.shouldIgnore()) {
        m = j;
      } else {
        int n = viewHolder.getLayoutPosition();
        int i1 = k;
        if (n < k)
          i1 = n; 
        k = i1;
        m = j;
        if (n > j) {
          m = n;
          k = i1;
        } 
      } 
      b++;
      j = m;
    } 
    paramArrayOfint[0] = k;
    paramArrayOfint[1] = j;
  }
  
  @Nullable
  static RecyclerView findNestedRecyclerView(@NonNull View paramView) {
    if (!(paramView instanceof ViewGroup))
      return null; 
    if (paramView instanceof RecyclerView)
      return (RecyclerView)paramView; 
    ViewGroup viewGroup = (ViewGroup)paramView;
    int i = viewGroup.getChildCount();
    for (byte b = 0; b < i; b++) {
      RecyclerView recyclerView = findNestedRecyclerView(viewGroup.getChildAt(b));
      if (recyclerView != null)
        return recyclerView; 
    } 
    return null;
  }
  
  @Nullable
  private View findNextViewToFocus() {
    if (this.mState.mFocusedItemPosition != -1) {
      i = this.mState.mFocusedItemPosition;
    } else {
      i = 0;
    } 
    int j = this.mState.getItemCount();
    for (int k = i; k < j; k++) {
      ViewHolder viewHolder = findViewHolderForAdapterPosition(k);
      if (viewHolder == null)
        break; 
      if (viewHolder.itemView.hasFocusable())
        return viewHolder.itemView; 
    } 
    for (int i = Math.min(j, i) - 1; i >= 0; i--) {
      ViewHolder viewHolder = findViewHolderForAdapterPosition(i);
      if (viewHolder == null)
        return null; 
      if (viewHolder.itemView.hasFocusable())
        return viewHolder.itemView; 
    } 
    return null;
  }
  
  static ViewHolder getChildViewHolderInt(View paramView) {
    return (paramView == null) ? null : ((LayoutParams)paramView.getLayoutParams()).mViewHolder;
  }
  
  static void getDecoratedBoundsWithMarginsInt(View paramView, Rect paramRect) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect rect = layoutParams.mDecorInsets;
    paramRect.set(paramView.getLeft() - rect.left - layoutParams.leftMargin, paramView.getTop() - rect.top - layoutParams.topMargin, paramView.getRight() + rect.right + layoutParams.rightMargin, paramView.getBottom() + rect.bottom + layoutParams.bottomMargin);
  }
  
  private int getDeepestFocusedViewWithId(View paramView) {
    int i = paramView.getId();
    while (!paramView.isFocused() && paramView instanceof ViewGroup && paramView.hasFocus()) {
      View view = ((ViewGroup)paramView).getFocusedChild();
      paramView = view;
      if (view.getId() != -1) {
        i = view.getId();
        paramView = view;
      } 
    } 
    return i;
  }
  
  private String getFullClassName(Context paramContext, String paramString) {
    if (paramString.charAt(0) == '.') {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramContext.getPackageName());
      stringBuilder1.append(paramString);
      return stringBuilder1.toString();
    } 
    if (paramString.contains("."))
      return paramString; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(RecyclerView.class.getPackage().getName());
    stringBuilder.append('.');
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  private NestedScrollingChildHelper getScrollingChildHelper() {
    if (this.mScrollingChildHelper == null)
      this.mScrollingChildHelper = new NestedScrollingChildHelper((View)this); 
    return this.mScrollingChildHelper;
  }
  
  private void handleMissingPreInfoForChangeError(long paramLong, ViewHolder paramViewHolder1, ViewHolder paramViewHolder2) {
    StringBuilder stringBuilder1;
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
      if (viewHolder != paramViewHolder1 && getChangedHolderKey(viewHolder) == paramLong) {
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
          stringBuilder.append(viewHolder);
          stringBuilder.append(" \n View Holder 2:");
          stringBuilder.append(paramViewHolder1);
          stringBuilder.append(exceptionLabel());
          throw new IllegalStateException(stringBuilder.toString());
        } 
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
        stringBuilder1.append(viewHolder);
        stringBuilder1.append(" \n View Holder 2:");
        stringBuilder1.append(paramViewHolder1);
        stringBuilder1.append(exceptionLabel());
        throw new IllegalStateException(stringBuilder1.toString());
      } 
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ");
    stringBuilder2.append(stringBuilder1);
    stringBuilder2.append(" cannot be found but it is necessary for ");
    stringBuilder2.append(paramViewHolder1);
    stringBuilder2.append(exceptionLabel());
    Log.e("RecyclerView", stringBuilder2.toString());
  }
  
  private boolean hasUpdatedView() {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore() && viewHolder.isUpdated())
        return true; 
    } 
    return false;
  }
  
  @SuppressLint({"InlinedApi"})
  private void initAutofill() {
    if (ViewCompat.getImportantForAutofill((View)this) == 0)
      ViewCompat.setImportantForAutofill((View)this, 8); 
  }
  
  private void initChildrenHelper() {
    this.mChildHelper = new ChildHelper(new ChildHelper.Callback() {
          public void addView(View param1View, int param1Int) {
            RecyclerView.this.addView(param1View, param1Int);
            RecyclerView.this.dispatchChildAttached(param1View);
          }
          
          public void attachViewToParent(View param1View, int param1Int, ViewGroup.LayoutParams param1LayoutParams) {
            StringBuilder stringBuilder;
            RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
            if (viewHolder != null) {
              if (!viewHolder.isTmpDetached() && !viewHolder.shouldIgnore()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Called attach on a child which is not detached: ");
                stringBuilder.append(viewHolder);
                stringBuilder.append(RecyclerView.this.exceptionLabel());
                throw new IllegalArgumentException(stringBuilder.toString());
              } 
              viewHolder.clearTmpDetachFlag();
            } 
            RecyclerView.this.attachViewToParent((View)stringBuilder, param1Int, param1LayoutParams);
          }
          
          public void detachViewFromParent(int param1Int) {
            View view = getChildAt(param1Int);
            if (view != null) {
              RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
              if (viewHolder != null) {
                if (viewHolder.isTmpDetached() && !viewHolder.shouldIgnore()) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("called detach on an already detached child ");
                  stringBuilder.append(viewHolder);
                  stringBuilder.append(RecyclerView.this.exceptionLabel());
                  throw new IllegalArgumentException(stringBuilder.toString());
                } 
                viewHolder.addFlags(256);
              } 
            } 
            RecyclerView.this.detachViewFromParent(param1Int);
          }
          
          public View getChildAt(int param1Int) {
            return RecyclerView.this.getChildAt(param1Int);
          }
          
          public int getChildCount() {
            return RecyclerView.this.getChildCount();
          }
          
          public RecyclerView.ViewHolder getChildViewHolder(View param1View) {
            return RecyclerView.getChildViewHolderInt(param1View);
          }
          
          public int indexOfChild(View param1View) {
            return RecyclerView.this.indexOfChild(param1View);
          }
          
          public void onEnteredHiddenState(View param1View) {
            RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
            if (viewHolder != null)
              viewHolder.onEnteredHiddenState(RecyclerView.this); 
          }
          
          public void onLeftHiddenState(View param1View) {
            RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
            if (viewHolder != null)
              viewHolder.onLeftHiddenState(RecyclerView.this); 
          }
          
          public void removeAllViews() {
            int i = getChildCount();
            for (byte b = 0; b < i; b++) {
              View view = getChildAt(b);
              RecyclerView.this.dispatchChildDetached(view);
              view.clearAnimation();
            } 
            RecyclerView.this.removeAllViews();
          }
          
          public void removeViewAt(int param1Int) {
            View view = RecyclerView.this.getChildAt(param1Int);
            if (view != null) {
              RecyclerView.this.dispatchChildDetached(view);
              view.clearAnimation();
            } 
            RecyclerView.this.removeViewAt(param1Int);
          }
        });
  }
  
  private boolean isPreferredNextFocus(View paramView1, View paramView2, int paramInt) {
    byte b1;
    boolean bool1 = false;
    null = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    if (paramView2 == null || paramView2 == this)
      return false; 
    if (findContainingItemView(paramView2) == null)
      return false; 
    if (paramView1 == null)
      return true; 
    if (findContainingItemView(paramView1) == null)
      return true; 
    this.mTempRect.set(0, 0, paramView1.getWidth(), paramView1.getHeight());
    this.mTempRect2.set(0, 0, paramView2.getWidth(), paramView2.getHeight());
    offsetDescendantRectToMyCoords(paramView1, this.mTempRect);
    offsetDescendantRectToMyCoords(paramView2, this.mTempRect2);
    int i = this.mLayout.getLayoutDirection();
    byte b = -1;
    if (i == 1) {
      b1 = -1;
    } else {
      b1 = 1;
    } 
    if ((this.mTempRect.left < this.mTempRect2.left || this.mTempRect.right <= this.mTempRect2.left) && this.mTempRect.right < this.mTempRect2.right) {
      i = 1;
    } else if ((this.mTempRect.right > this.mTempRect2.right || this.mTempRect.left >= this.mTempRect2.right) && this.mTempRect.left > this.mTempRect2.left) {
      i = -1;
    } else {
      i = 0;
    } 
    if ((this.mTempRect.top < this.mTempRect2.top || this.mTempRect.bottom <= this.mTempRect2.top) && this.mTempRect.bottom < this.mTempRect2.bottom) {
      b = 1;
    } else if ((this.mTempRect.bottom <= this.mTempRect2.bottom && this.mTempRect.top < this.mTempRect2.bottom) || this.mTempRect.top <= this.mTempRect2.top) {
      b = 0;
    } 
    if (paramInt != 17) {
      if (paramInt != 33) {
        if (paramInt != 66) {
          if (paramInt != 130) {
            StringBuilder stringBuilder;
            switch (paramInt) {
              default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid direction: ");
                stringBuilder.append(paramInt);
                stringBuilder.append(exceptionLabel());
                throw new IllegalArgumentException(stringBuilder.toString());
              case 2:
                if (b <= 0) {
                  null = bool5;
                  if (b == 0) {
                    null = bool5;
                    if (i * b1 >= 0)
                      return true; 
                  } 
                  return null;
                } 
                return true;
              case 1:
                break;
            } 
            if (b >= 0) {
              null = bool1;
              if (b == 0) {
                null = bool1;
                if (i * b1 <= 0)
                  return true; 
              } 
              return null;
            } 
          } else {
            if (b > 0)
              null = true; 
            return null;
          } 
        } else {
          null = bool2;
          if (i > 0)
            null = true; 
          return null;
        } 
      } else {
        null = bool3;
        if (b < 0)
          null = true; 
        return null;
      } 
    } else {
      null = bool4;
      if (i < 0)
        null = true; 
      return null;
    } 
    return true;
  }
  
  private void onPointerUp(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == this.mScrollPointerId) {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      } 
      this.mScrollPointerId = paramMotionEvent.getPointerId(i);
      int j = (int)(paramMotionEvent.getX(i) + 0.5F);
      this.mLastTouchX = j;
      this.mInitialTouchX = j;
      i = (int)(paramMotionEvent.getY(i) + 0.5F);
      this.mLastTouchY = i;
      this.mInitialTouchY = i;
    } 
  }
  
  private boolean predictiveItemAnimationsEnabled() {
    boolean bool;
    if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void processAdapterUpdatesAndSetAnimationFlags() {
    boolean bool2;
    if (this.mDataSetHasChangedAfterLayout) {
      this.mAdapterHelper.reset();
      if (this.mDispatchItemsChangedEvent)
        this.mLayout.onItemsChanged(this); 
    } 
    if (predictiveItemAnimationsEnabled()) {
      this.mAdapterHelper.preProcess();
    } else {
      this.mAdapterHelper.consumeUpdatesInOnePass();
    } 
    boolean bool = this.mItemsAddedOrRemoved;
    boolean bool1 = true;
    if (bool || this.mItemsChanged) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    State state = this.mState;
    if (this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || bool2 || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds())) {
      bool = true;
    } else {
      bool = false;
    } 
    state.mRunSimpleAnimations = bool;
    state = this.mState;
    if (this.mState.mRunSimpleAnimations && bool2 && !this.mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled()) {
      bool = bool1;
    } else {
      bool = false;
    } 
    state.mRunPredictiveAnimations = bool;
  }
  
  private void pullGlows(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #5
    //   3: fload_2
    //   4: fconst_0
    //   5: fcmpg
    //   6: ifge -> 43
    //   9: aload_0
    //   10: invokevirtual ensureLeftGlow : ()V
    //   13: aload_0
    //   14: getfield mLeftGlow : Landroid/widget/EdgeEffect;
    //   17: fload_2
    //   18: fneg
    //   19: aload_0
    //   20: invokevirtual getWidth : ()I
    //   23: i2f
    //   24: fdiv
    //   25: fconst_1
    //   26: fload_3
    //   27: aload_0
    //   28: invokevirtual getHeight : ()I
    //   31: i2f
    //   32: fdiv
    //   33: fsub
    //   34: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   37: iconst_1
    //   38: istore #6
    //   40: goto -> 80
    //   43: fload_2
    //   44: fconst_0
    //   45: fcmpl
    //   46: ifle -> 77
    //   49: aload_0
    //   50: invokevirtual ensureRightGlow : ()V
    //   53: aload_0
    //   54: getfield mRightGlow : Landroid/widget/EdgeEffect;
    //   57: fload_2
    //   58: aload_0
    //   59: invokevirtual getWidth : ()I
    //   62: i2f
    //   63: fdiv
    //   64: fload_3
    //   65: aload_0
    //   66: invokevirtual getHeight : ()I
    //   69: i2f
    //   70: fdiv
    //   71: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   74: goto -> 37
    //   77: iconst_0
    //   78: istore #6
    //   80: fload #4
    //   82: fconst_0
    //   83: fcmpg
    //   84: ifge -> 121
    //   87: aload_0
    //   88: invokevirtual ensureTopGlow : ()V
    //   91: aload_0
    //   92: getfield mTopGlow : Landroid/widget/EdgeEffect;
    //   95: fload #4
    //   97: fneg
    //   98: aload_0
    //   99: invokevirtual getHeight : ()I
    //   102: i2f
    //   103: fdiv
    //   104: fload_1
    //   105: aload_0
    //   106: invokevirtual getWidth : ()I
    //   109: i2f
    //   110: fdiv
    //   111: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   114: iload #5
    //   116: istore #6
    //   118: goto -> 163
    //   121: fload #4
    //   123: fconst_0
    //   124: fcmpl
    //   125: ifle -> 163
    //   128: aload_0
    //   129: invokevirtual ensureBottomGlow : ()V
    //   132: aload_0
    //   133: getfield mBottomGlow : Landroid/widget/EdgeEffect;
    //   136: fload #4
    //   138: aload_0
    //   139: invokevirtual getHeight : ()I
    //   142: i2f
    //   143: fdiv
    //   144: fconst_1
    //   145: fload_1
    //   146: aload_0
    //   147: invokevirtual getWidth : ()I
    //   150: i2f
    //   151: fdiv
    //   152: fsub
    //   153: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   156: iload #5
    //   158: istore #6
    //   160: goto -> 163
    //   163: iload #6
    //   165: ifne -> 181
    //   168: fload_2
    //   169: fconst_0
    //   170: fcmpl
    //   171: ifne -> 181
    //   174: fload #4
    //   176: fconst_0
    //   177: fcmpl
    //   178: ifeq -> 185
    //   181: aload_0
    //   182: invokestatic postInvalidateOnAnimation : (Landroid/view/View;)V
    //   185: return
  }
  
  private void recoverFocusFromState() {
    View view1;
    if (!this.mPreserveFocusAfterLayout || this.mAdapter == null || !hasFocus() || getDescendantFocusability() == 393216 || (getDescendantFocusability() == 131072 && isFocused()))
      return; 
    if (!isFocused()) {
      view1 = getFocusedChild();
      if (IGNORE_DETACHED_FOCUSED_CHILD && (view1.getParent() == null || !view1.hasFocus())) {
        if (this.mChildHelper.getChildCount() == 0) {
          requestFocus();
          return;
        } 
      } else if (!this.mChildHelper.isHidden(view1)) {
        return;
      } 
    } 
    long l = this.mState.mFocusedItemId;
    View view2 = null;
    if (l != -1L && this.mAdapter.hasStableIds()) {
      view1 = (View)findViewHolderForItemId(this.mState.mFocusedItemId);
    } else {
      view1 = null;
    } 
    if (view1 == null || this.mChildHelper.isHidden(((ViewHolder)view1).itemView) || !((ViewHolder)view1).itemView.hasFocusable()) {
      view1 = view2;
      if (this.mChildHelper.getChildCount() > 0)
        view1 = findNextViewToFocus(); 
    } else {
      view1 = ((ViewHolder)view1).itemView;
    } 
    if (view1 != null) {
      if (this.mState.mFocusedSubChildId != -1L) {
        view2 = view1.findViewById(this.mState.mFocusedSubChildId);
        if (view2 != null && view2.isFocusable())
          view1 = view2; 
      } 
      view1.requestFocus();
    } 
  }
  
  private void releaseGlows() {
    if (this.mLeftGlow != null) {
      this.mLeftGlow.onRelease();
      bool1 = this.mLeftGlow.isFinished();
    } else {
      bool1 = false;
    } 
    boolean bool2 = bool1;
    if (this.mTopGlow != null) {
      this.mTopGlow.onRelease();
      bool2 = bool1 | this.mTopGlow.isFinished();
    } 
    boolean bool1 = bool2;
    if (this.mRightGlow != null) {
      this.mRightGlow.onRelease();
      bool1 = bool2 | this.mRightGlow.isFinished();
    } 
    bool2 = bool1;
    if (this.mBottomGlow != null) {
      this.mBottomGlow.onRelease();
      bool2 = bool1 | this.mBottomGlow.isFinished();
    } 
    if (bool2)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  private void requestChildOnScreen(@NonNull View paramView1, @Nullable View paramView2) {
    View view;
    boolean bool1;
    if (paramView2 != null) {
      view = paramView2;
    } else {
      view = paramView1;
    } 
    this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    if (layoutParams instanceof LayoutParams) {
      LayoutParams layoutParams1 = (LayoutParams)layoutParams;
      if (!layoutParams1.mInsetsDirty) {
        Rect rect1 = layoutParams1.mDecorInsets;
        Rect rect2 = this.mTempRect;
        rect2.left -= rect1.left;
        rect2 = this.mTempRect;
        rect2.right += rect1.right;
        rect2 = this.mTempRect;
        rect2.top -= rect1.top;
        rect2 = this.mTempRect;
        rect2.bottom += rect1.bottom;
      } 
    } 
    if (paramView2 != null) {
      offsetDescendantRectToMyCoords(paramView2, this.mTempRect);
      offsetRectIntoDescendantCoords(paramView1, this.mTempRect);
    } 
    LayoutManager layoutManager = this.mLayout;
    Rect rect = this.mTempRect;
    boolean bool = this.mFirstLayoutComplete;
    if (paramView2 == null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    layoutManager.requestChildRectangleOnScreen(this, paramView1, rect, bool ^ true, bool1);
  }
  
  private void resetFocusInfo() {
    this.mState.mFocusedItemId = -1L;
    this.mState.mFocusedItemPosition = -1;
    this.mState.mFocusedSubChildId = -1;
  }
  
  private void resetTouch() {
    if (this.mVelocityTracker != null)
      this.mVelocityTracker.clear(); 
    stopNestedScroll(0);
    releaseGlows();
  }
  
  private void saveFocusInfo() {
    ViewHolder viewHolder2;
    boolean bool = this.mPreserveFocusAfterLayout;
    ViewHolder viewHolder1 = null;
    if (bool && hasFocus() && this.mAdapter != null) {
      viewHolder2 = (ViewHolder)getFocusedChild();
    } else {
      viewHolder2 = null;
    } 
    if (viewHolder2 == null) {
      viewHolder2 = viewHolder1;
    } else {
      viewHolder2 = findContainingViewHolder((View)viewHolder2);
    } 
    if (viewHolder2 == null) {
      resetFocusInfo();
    } else {
      long l;
      int i;
      State state = this.mState;
      if (this.mAdapter.hasStableIds()) {
        l = viewHolder2.getItemId();
      } else {
        l = -1L;
      } 
      state.mFocusedItemId = l;
      state = this.mState;
      if (this.mDataSetHasChangedAfterLayout) {
        i = -1;
      } else if (viewHolder2.isRemoved()) {
        i = viewHolder2.mOldPosition;
      } else {
        i = viewHolder2.getAdapterPosition();
      } 
      state.mFocusedItemPosition = i;
      this.mState.mFocusedSubChildId = getDeepestFocusedViewWithId(viewHolder2.itemView);
    } 
  }
  
  private void setAdapterInternal(@Nullable Adapter paramAdapter, boolean paramBoolean1, boolean paramBoolean2) {
    if (this.mAdapter != null) {
      this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
      this.mAdapter.onDetachedFromRecyclerView(this);
    } 
    if (!paramBoolean1 || paramBoolean2)
      removeAndRecycleViews(); 
    this.mAdapterHelper.reset();
    Adapter adapter = this.mAdapter;
    this.mAdapter = paramAdapter;
    if (paramAdapter != null) {
      paramAdapter.registerAdapterDataObserver(this.mObserver);
      paramAdapter.onAttachedToRecyclerView(this);
    } 
    if (this.mLayout != null)
      this.mLayout.onAdapterChanged(adapter, this.mAdapter); 
    this.mRecycler.onAdapterChanged(adapter, this.mAdapter, paramBoolean1);
    this.mState.mStructureChanged = true;
  }
  
  private void stopScrollersInternal() {
    this.mViewFlinger.stop();
    if (this.mLayout != null)
      this.mLayout.stopSmoothScroller(); 
  }
  
  void absorbGlows(int paramInt1, int paramInt2) {
    if (paramInt1 < 0) {
      ensureLeftGlow();
      this.mLeftGlow.onAbsorb(-paramInt1);
    } else if (paramInt1 > 0) {
      ensureRightGlow();
      this.mRightGlow.onAbsorb(paramInt1);
    } 
    if (paramInt2 < 0) {
      ensureTopGlow();
      this.mTopGlow.onAbsorb(-paramInt2);
    } else if (paramInt2 > 0) {
      ensureBottomGlow();
      this.mBottomGlow.onAbsorb(paramInt2);
    } 
    if (paramInt1 != 0 || paramInt2 != 0)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2) {
    if (this.mLayout == null || !this.mLayout.onAddFocusables(this, paramArrayList, paramInt1, paramInt2))
      super.addFocusables(paramArrayList, paramInt1, paramInt2); 
  }
  
  public void addItemDecoration(@NonNull ItemDecoration paramItemDecoration) {
    addItemDecoration(paramItemDecoration, -1);
  }
  
  public void addItemDecoration(@NonNull ItemDecoration paramItemDecoration, int paramInt) {
    if (this.mLayout != null)
      this.mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout"); 
    if (this.mItemDecorations.isEmpty())
      setWillNotDraw(false); 
    if (paramInt < 0) {
      this.mItemDecorations.add(paramItemDecoration);
    } else {
      this.mItemDecorations.add(paramInt, paramItemDecoration);
    } 
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void addOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener paramOnChildAttachStateChangeListener) {
    if (this.mOnChildAttachStateListeners == null)
      this.mOnChildAttachStateListeners = new ArrayList<>(); 
    this.mOnChildAttachStateListeners.add(paramOnChildAttachStateChangeListener);
  }
  
  public void addOnItemTouchListener(@NonNull OnItemTouchListener paramOnItemTouchListener) {
    this.mOnItemTouchListeners.add(paramOnItemTouchListener);
  }
  
  public void addOnScrollListener(@NonNull OnScrollListener paramOnScrollListener) {
    if (this.mScrollListeners == null)
      this.mScrollListeners = new ArrayList<>(); 
    this.mScrollListeners.add(paramOnScrollListener);
  }
  
  void animateAppearance(@NonNull ViewHolder paramViewHolder, @Nullable ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo2) {
    paramViewHolder.setIsRecyclable(false);
    if (this.mItemAnimator.animateAppearance(paramViewHolder, paramItemHolderInfo1, paramItemHolderInfo2))
      postAnimationRunner(); 
  }
  
  void animateDisappearance(@NonNull ViewHolder paramViewHolder, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @Nullable ItemAnimator.ItemHolderInfo paramItemHolderInfo2) {
    addAnimatingView(paramViewHolder);
    paramViewHolder.setIsRecyclable(false);
    if (this.mItemAnimator.animateDisappearance(paramViewHolder, paramItemHolderInfo1, paramItemHolderInfo2))
      postAnimationRunner(); 
  }
  
  void assertInLayoutOrScroll(String paramString) {
    if (!isComputingLayout()) {
      StringBuilder stringBuilder1;
      if (paramString == null) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Cannot call this method unless RecyclerView is computing a layout or scrolling");
        stringBuilder1.append(exceptionLabel());
        throw new IllegalStateException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append((String)stringBuilder1);
      stringBuilder2.append(exceptionLabel());
      throw new IllegalStateException(stringBuilder2.toString());
    } 
  }
  
  void assertNotInLayoutOrScroll(String paramString) {
    if (isComputingLayout()) {
      StringBuilder stringBuilder;
      if (paramString == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot call this method while RecyclerView is computing a layout or scrolling");
        stringBuilder.append(exceptionLabel());
        throw new IllegalStateException(stringBuilder.toString());
      } 
      throw new IllegalStateException(stringBuilder);
    } 
    if (this.mDispatchScrollCounter > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(exceptionLabel());
      Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(stringBuilder.toString()));
    } 
  }
  
  boolean canReuseUpdatedViewHolder(ViewHolder paramViewHolder) {
    return (this.mItemAnimator == null || this.mItemAnimator.canReuseUpdatedViewHolder(paramViewHolder, paramViewHolder.getUnmodifiedPayloads()));
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (paramLayoutParams instanceof LayoutParams && this.mLayout.checkLayoutParams((LayoutParams)paramLayoutParams)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void clearOldPositions() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (!viewHolder.shouldIgnore())
        viewHolder.clearOldPosition(); 
    } 
    this.mRecycler.clearOldPositions();
  }
  
  public void clearOnChildAttachStateChangeListeners() {
    if (this.mOnChildAttachStateListeners != null)
      this.mOnChildAttachStateListeners.clear(); 
  }
  
  public void clearOnScrollListeners() {
    if (this.mScrollListeners != null)
      this.mScrollListeners.clear(); 
  }
  
  public int computeHorizontalScrollExtent() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (this.mLayout.canScrollHorizontally())
      i = this.mLayout.computeHorizontalScrollExtent(this.mState); 
    return i;
  }
  
  public int computeHorizontalScrollOffset() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (this.mLayout.canScrollHorizontally())
      i = this.mLayout.computeHorizontalScrollOffset(this.mState); 
    return i;
  }
  
  public int computeHorizontalScrollRange() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (this.mLayout.canScrollHorizontally())
      i = this.mLayout.computeHorizontalScrollRange(this.mState); 
    return i;
  }
  
  public int computeVerticalScrollExtent() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (this.mLayout.canScrollVertically())
      i = this.mLayout.computeVerticalScrollExtent(this.mState); 
    return i;
  }
  
  public int computeVerticalScrollOffset() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (this.mLayout.canScrollVertically())
      i = this.mLayout.computeVerticalScrollOffset(this.mState); 
    return i;
  }
  
  public int computeVerticalScrollRange() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (this.mLayout.canScrollVertically())
      i = this.mLayout.computeVerticalScrollRange(this.mState); 
    return i;
  }
  
  void considerReleasingGlowsOnScroll(int paramInt1, int paramInt2) {
    if (this.mLeftGlow != null && !this.mLeftGlow.isFinished() && paramInt1 > 0) {
      this.mLeftGlow.onRelease();
      bool1 = this.mLeftGlow.isFinished();
    } else {
      bool1 = false;
    } 
    boolean bool2 = bool1;
    if (this.mRightGlow != null) {
      bool2 = bool1;
      if (!this.mRightGlow.isFinished()) {
        bool2 = bool1;
        if (paramInt1 < 0) {
          this.mRightGlow.onRelease();
          bool2 = bool1 | this.mRightGlow.isFinished();
        } 
      } 
    } 
    boolean bool1 = bool2;
    if (this.mTopGlow != null) {
      bool1 = bool2;
      if (!this.mTopGlow.isFinished()) {
        bool1 = bool2;
        if (paramInt2 > 0) {
          this.mTopGlow.onRelease();
          bool1 = bool2 | this.mTopGlow.isFinished();
        } 
      } 
    } 
    bool2 = bool1;
    if (this.mBottomGlow != null) {
      bool2 = bool1;
      if (!this.mBottomGlow.isFinished()) {
        bool2 = bool1;
        if (paramInt2 < 0) {
          this.mBottomGlow.onRelease();
          bool2 = bool1 | this.mBottomGlow.isFinished();
        } 
      } 
    } 
    if (bool2)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  void consumePendingUpdateOperations() {
    if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
      TraceCompat.beginSection("RV FullInvalidate");
      dispatchLayout();
      TraceCompat.endSection();
      return;
    } 
    if (!this.mAdapterHelper.hasPendingUpdates())
      return; 
    if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
      TraceCompat.beginSection("RV PartialInvalidate");
      startInterceptRequestLayout();
      onEnterLayoutOrScroll();
      this.mAdapterHelper.preProcess();
      if (!this.mLayoutWasDefered)
        if (hasUpdatedView()) {
          dispatchLayout();
        } else {
          this.mAdapterHelper.consumePostponedUpdates();
        }  
      stopInterceptRequestLayout(true);
      onExitLayoutOrScroll();
      TraceCompat.endSection();
    } else if (this.mAdapterHelper.hasPendingUpdates()) {
      TraceCompat.beginSection("RV FullInvalidate");
      dispatchLayout();
      TraceCompat.endSection();
    } 
  }
  
  void defaultOnMeasure(int paramInt1, int paramInt2) {
    setMeasuredDimension(LayoutManager.chooseSize(paramInt1, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth((View)this)), LayoutManager.chooseSize(paramInt2, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight((View)this)));
  }
  
  void dispatchChildAttached(View paramView) {
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    onChildAttachedToWindow(paramView);
    if (this.mAdapter != null && viewHolder != null)
      this.mAdapter.onViewAttachedToWindow(viewHolder); 
    if (this.mOnChildAttachStateListeners != null)
      for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; i--)
        ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(i)).onChildViewAttachedToWindow(paramView);  
  }
  
  void dispatchChildDetached(View paramView) {
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    onChildDetachedFromWindow(paramView);
    if (this.mAdapter != null && viewHolder != null)
      this.mAdapter.onViewDetachedFromWindow(viewHolder); 
    if (this.mOnChildAttachStateListeners != null)
      for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; i--)
        ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(i)).onChildViewDetachedFromWindow(paramView);  
  }
  
  void dispatchLayout() {
    if (this.mAdapter == null) {
      Log.e("RecyclerView", "No adapter attached; skipping layout");
      return;
    } 
    if (this.mLayout == null) {
      Log.e("RecyclerView", "No layout manager attached; skipping layout");
      return;
    } 
    this.mState.mIsMeasuring = false;
    if (this.mState.mLayoutStep == 1) {
      dispatchLayoutStep1();
      this.mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    } else if (this.mAdapterHelper.hasUpdates() || this.mLayout.getWidth() != getWidth() || this.mLayout.getHeight() != getHeight()) {
      this.mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    } else {
      this.mLayout.setExactMeasureSpecsFrom(this);
    } 
    dispatchLayoutStep3();
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean) {
    return getScrollingChildHelper().dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2) {
    return getScrollingChildHelper().dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfint1, paramArrayOfint2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt3) {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfint1, paramArrayOfint2, paramInt3);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfint) {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfint);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfint, int paramInt5) {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfint, paramInt5);
  }
  
  void dispatchOnScrollStateChanged(int paramInt) {
    if (this.mLayout != null)
      this.mLayout.onScrollStateChanged(paramInt); 
    onScrollStateChanged(paramInt);
    if (this.mScrollListener != null)
      this.mScrollListener.onScrollStateChanged(this, paramInt); 
    if (this.mScrollListeners != null)
      for (int i = this.mScrollListeners.size() - 1; i >= 0; i--)
        ((OnScrollListener)this.mScrollListeners.get(i)).onScrollStateChanged(this, paramInt);  
  }
  
  void dispatchOnScrolled(int paramInt1, int paramInt2) {
    this.mDispatchScrollCounter++;
    int i = getScrollX();
    int j = getScrollY();
    onScrollChanged(i, j, i, j);
    onScrolled(paramInt1, paramInt2);
    if (this.mScrollListener != null)
      this.mScrollListener.onScrolled(this, paramInt1, paramInt2); 
    if (this.mScrollListeners != null)
      for (j = this.mScrollListeners.size() - 1; j >= 0; j--)
        ((OnScrollListener)this.mScrollListeners.get(j)).onScrolled(this, paramInt1, paramInt2);  
    this.mDispatchScrollCounter--;
  }
  
  void dispatchPendingImportantForAccessibilityChanges() {
    for (int i = this.mPendingAccessibilityImportanceChange.size() - 1; i >= 0; i--) {
      ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(i);
      if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore()) {
        int j = viewHolder.mPendingAccessibilityState;
        if (j != -1) {
          ViewCompat.setImportantForAccessibility(viewHolder.itemView, j);
          viewHolder.mPendingAccessibilityState = -1;
        } 
      } 
    } 
    this.mPendingAccessibilityImportanceChange.clear();
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray) {
    dispatchThawSelfOnly(paramSparseArray);
  }
  
  protected void dispatchSaveInstanceState(SparseArray<Parcelable> paramSparseArray) {
    dispatchFreezeSelfOnly(paramSparseArray);
  }
  
  public void draw(Canvas paramCanvas) {
    super.draw(paramCanvas);
    int i = this.mItemDecorations.size();
    boolean bool = false;
    int j;
    for (j = 0; j < i; j++)
      ((ItemDecoration)this.mItemDecorations.get(j)).onDrawOver(paramCanvas, this, this.mState); 
    if (this.mLeftGlow != null && !this.mLeftGlow.isFinished()) {
      int k = paramCanvas.save();
      if (this.mClipToPadding) {
        j = getPaddingBottom();
      } else {
        j = 0;
      } 
      paramCanvas.rotate(270.0F);
      paramCanvas.translate((-getHeight() + j), 0.0F);
      if (this.mLeftGlow != null && this.mLeftGlow.draw(paramCanvas)) {
        i = 1;
      } else {
        i = 0;
      } 
      paramCanvas.restoreToCount(k);
    } else {
      i = 0;
    } 
    j = i;
    if (this.mTopGlow != null) {
      j = i;
      if (!this.mTopGlow.isFinished()) {
        int k = paramCanvas.save();
        if (this.mClipToPadding)
          paramCanvas.translate(getPaddingLeft(), getPaddingTop()); 
        if (this.mTopGlow != null && this.mTopGlow.draw(paramCanvas)) {
          j = 1;
        } else {
          j = 0;
        } 
        j = i | j;
        paramCanvas.restoreToCount(k);
      } 
    } 
    i = j;
    if (this.mRightGlow != null) {
      i = j;
      if (!this.mRightGlow.isFinished()) {
        int k = paramCanvas.save();
        int m = getWidth();
        if (this.mClipToPadding) {
          i = getPaddingTop();
        } else {
          i = 0;
        } 
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-i, -m);
        if (this.mRightGlow != null && this.mRightGlow.draw(paramCanvas)) {
          i = 1;
        } else {
          i = 0;
        } 
        i = j | i;
        paramCanvas.restoreToCount(k);
      } 
    } 
    if (this.mBottomGlow != null && !this.mBottomGlow.isFinished()) {
      int k = paramCanvas.save();
      paramCanvas.rotate(180.0F);
      if (this.mClipToPadding) {
        paramCanvas.translate((-getWidth() + getPaddingRight()), (-getHeight() + getPaddingBottom()));
      } else {
        paramCanvas.translate(-getWidth(), -getHeight());
      } 
      j = bool;
      if (this.mBottomGlow != null) {
        j = bool;
        if (this.mBottomGlow.draw(paramCanvas))
          j = 1; 
      } 
      j |= i;
      paramCanvas.restoreToCount(k);
    } else {
      j = i;
    } 
    i = j;
    if (j == 0) {
      i = j;
      if (this.mItemAnimator != null) {
        i = j;
        if (this.mItemDecorations.size() > 0) {
          i = j;
          if (this.mItemAnimator.isRunning())
            i = 1; 
        } 
      } 
    } 
    if (i != 0)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  public boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  void ensureBottomGlow() {
    if (this.mBottomGlow != null)
      return; 
    this.mBottomGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
    if (this.mClipToPadding) {
      this.mBottomGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
    } else {
      this.mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
    } 
  }
  
  void ensureLeftGlow() {
    if (this.mLeftGlow != null)
      return; 
    this.mLeftGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
    if (this.mClipToPadding) {
      this.mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
    } else {
      this.mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
    } 
  }
  
  void ensureRightGlow() {
    if (this.mRightGlow != null)
      return; 
    this.mRightGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
    if (this.mClipToPadding) {
      this.mRightGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
    } else {
      this.mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
    } 
  }
  
  void ensureTopGlow() {
    if (this.mTopGlow != null)
      return; 
    this.mTopGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
    if (this.mClipToPadding) {
      this.mTopGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
    } else {
      this.mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
    } 
  }
  
  String exceptionLabel() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" ");
    stringBuilder.append(toString());
    stringBuilder.append(", adapter:");
    stringBuilder.append(this.mAdapter);
    stringBuilder.append(", layout:");
    stringBuilder.append(this.mLayout);
    stringBuilder.append(", context:");
    stringBuilder.append(getContext());
    return stringBuilder.toString();
  }
  
  final void fillRemainingScrollValues(State paramState) {
    if (getScrollState() == 2) {
      OverScroller overScroller = this.mViewFlinger.mScroller;
      paramState.mRemainingScrollHorizontal = overScroller.getFinalX() - overScroller.getCurrX();
      paramState.mRemainingScrollVertical = overScroller.getFinalY() - overScroller.getCurrY();
    } else {
      paramState.mRemainingScrollHorizontal = 0;
      paramState.mRemainingScrollVertical = 0;
    } 
  }
  
  @Nullable
  public View findChildViewUnder(float paramFloat1, float paramFloat2) {
    for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; i--) {
      View view = this.mChildHelper.getChildAt(i);
      float f1 = view.getTranslationX();
      float f2 = view.getTranslationY();
      if (paramFloat1 >= view.getLeft() + f1 && paramFloat1 <= view.getRight() + f1 && paramFloat2 >= view.getTop() + f2 && paramFloat2 <= view.getBottom() + f2)
        return view; 
    } 
    return null;
  }
  
  @Nullable
  public View findContainingItemView(@NonNull View paramView) {
    ViewParent viewParent;
    for (viewParent = paramView.getParent(); viewParent != null && viewParent != this && viewParent instanceof View; viewParent = paramView.getParent())
      paramView = (View)viewParent; 
    if (viewParent != this)
      paramView = null; 
    return paramView;
  }
  
  @Nullable
  public ViewHolder findContainingViewHolder(@NonNull View paramView) {
    ViewHolder viewHolder;
    paramView = findContainingItemView(paramView);
    if (paramView == null) {
      paramView = null;
    } else {
      viewHolder = getChildViewHolder(paramView);
    } 
    return viewHolder;
  }
  
  @Nullable
  public ViewHolder findViewHolderForAdapterPosition(int paramInt) {
    boolean bool = this.mDataSetHasChangedAfterLayout;
    ViewHolder viewHolder = null;
    if (bool)
      return null; 
    int i = this.mChildHelper.getUnfilteredChildCount();
    byte b = 0;
    while (b < i) {
      ViewHolder viewHolder1 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      ViewHolder viewHolder2 = viewHolder;
      if (viewHolder1 != null) {
        viewHolder2 = viewHolder;
        if (!viewHolder1.isRemoved()) {
          viewHolder2 = viewHolder;
          if (getAdapterPositionFor(viewHolder1) == paramInt)
            if (this.mChildHelper.isHidden(viewHolder1.itemView)) {
              viewHolder2 = viewHolder1;
            } else {
              return viewHolder1;
            }  
        } 
      } 
      b++;
      viewHolder = viewHolder2;
    } 
    return viewHolder;
  }
  
  public ViewHolder findViewHolderForItemId(long paramLong) {
    ViewHolder viewHolder;
    Adapter adapter1 = this.mAdapter;
    Adapter adapter2 = null;
    if (adapter1 == null || !this.mAdapter.hasStableIds())
      return null; 
    int i = this.mChildHelper.getUnfilteredChildCount();
    byte b = 0;
    while (b < i) {
      ViewHolder viewHolder1;
      ViewHolder viewHolder2 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      adapter1 = adapter2;
      if (viewHolder2 != null) {
        adapter1 = adapter2;
        if (!viewHolder2.isRemoved()) {
          adapter1 = adapter2;
          if (viewHolder2.getItemId() == paramLong)
            if (this.mChildHelper.isHidden(viewHolder2.itemView)) {
              viewHolder1 = viewHolder2;
            } else {
              return viewHolder2;
            }  
        } 
      } 
      b++;
      viewHolder = viewHolder1;
    } 
    return viewHolder;
  }
  
  @Nullable
  public ViewHolder findViewHolderForLayoutPosition(int paramInt) {
    return findViewHolderForPosition(paramInt, false);
  }
  
  @Deprecated
  @Nullable
  public ViewHolder findViewHolderForPosition(int paramInt) {
    return findViewHolderForPosition(paramInt, false);
  }
  
  @Nullable
  ViewHolder findViewHolderForPosition(int paramInt, boolean paramBoolean) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    Object object = null;
    byte b = 0;
    while (b < i) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      Object object1 = object;
      if (viewHolder != null) {
        object1 = object;
        if (!viewHolder.isRemoved()) {
          if (paramBoolean) {
            if (viewHolder.mPosition != paramInt) {
              object1 = object;
              continue;
            } 
          } else if (viewHolder.getLayoutPosition() != paramInt) {
            object1 = object;
            continue;
          } 
          if (this.mChildHelper.isHidden(viewHolder.itemView)) {
            object1 = viewHolder;
          } else {
            return viewHolder;
          } 
        } 
      } 
      continue;
      b++;
      object = SYNTHETIC_LOCAL_VARIABLE_7;
    } 
    return (ViewHolder)object;
  }
  
  public boolean fling(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   4: astore_3
    //   5: iconst_0
    //   6: istore #4
    //   8: aload_3
    //   9: ifnonnull -> 23
    //   12: ldc 'RecyclerView'
    //   14: ldc_w 'Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.'
    //   17: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   20: pop
    //   21: iconst_0
    //   22: ireturn
    //   23: aload_0
    //   24: getfield mLayoutFrozen : Z
    //   27: ifeq -> 32
    //   30: iconst_0
    //   31: ireturn
    //   32: aload_0
    //   33: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   36: invokevirtual canScrollHorizontally : ()Z
    //   39: istore #5
    //   41: aload_0
    //   42: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   45: invokevirtual canScrollVertically : ()Z
    //   48: istore #6
    //   50: iload #5
    //   52: ifeq -> 69
    //   55: iload_1
    //   56: istore #7
    //   58: iload_1
    //   59: invokestatic abs : (I)I
    //   62: aload_0
    //   63: getfield mMinFlingVelocity : I
    //   66: if_icmpge -> 72
    //   69: iconst_0
    //   70: istore #7
    //   72: iload #6
    //   74: ifeq -> 91
    //   77: iload_2
    //   78: istore #8
    //   80: iload_2
    //   81: invokestatic abs : (I)I
    //   84: aload_0
    //   85: getfield mMinFlingVelocity : I
    //   88: if_icmpge -> 94
    //   91: iconst_0
    //   92: istore #8
    //   94: iload #7
    //   96: ifne -> 106
    //   99: iload #8
    //   101: ifne -> 106
    //   104: iconst_0
    //   105: ireturn
    //   106: iload #7
    //   108: i2f
    //   109: fstore #9
    //   111: iload #8
    //   113: i2f
    //   114: fstore #10
    //   116: aload_0
    //   117: fload #9
    //   119: fload #10
    //   121: invokevirtual dispatchNestedPreFling : (FF)Z
    //   124: ifne -> 263
    //   127: iload #5
    //   129: ifne -> 146
    //   132: iload #6
    //   134: ifeq -> 140
    //   137: goto -> 146
    //   140: iconst_0
    //   141: istore #11
    //   143: goto -> 149
    //   146: iconst_1
    //   147: istore #11
    //   149: aload_0
    //   150: fload #9
    //   152: fload #10
    //   154: iload #11
    //   156: invokevirtual dispatchNestedFling : (FFZ)Z
    //   159: pop
    //   160: aload_0
    //   161: getfield mOnFlingListener : Landroid/support/v7/widget/RecyclerView$OnFlingListener;
    //   164: ifnull -> 183
    //   167: aload_0
    //   168: getfield mOnFlingListener : Landroid/support/v7/widget/RecyclerView$OnFlingListener;
    //   171: iload #7
    //   173: iload #8
    //   175: invokevirtual onFling : (II)Z
    //   178: ifeq -> 183
    //   181: iconst_1
    //   182: ireturn
    //   183: iload #11
    //   185: ifeq -> 263
    //   188: iload #4
    //   190: istore_1
    //   191: iload #5
    //   193: ifeq -> 198
    //   196: iconst_1
    //   197: istore_1
    //   198: iload_1
    //   199: istore_2
    //   200: iload #6
    //   202: ifeq -> 209
    //   205: iload_1
    //   206: iconst_2
    //   207: ior
    //   208: istore_2
    //   209: aload_0
    //   210: iload_2
    //   211: iconst_1
    //   212: invokevirtual startNestedScroll : (II)Z
    //   215: pop
    //   216: aload_0
    //   217: getfield mMaxFlingVelocity : I
    //   220: ineg
    //   221: iload #7
    //   223: aload_0
    //   224: getfield mMaxFlingVelocity : I
    //   227: invokestatic min : (II)I
    //   230: invokestatic max : (II)I
    //   233: istore_1
    //   234: aload_0
    //   235: getfield mMaxFlingVelocity : I
    //   238: ineg
    //   239: iload #8
    //   241: aload_0
    //   242: getfield mMaxFlingVelocity : I
    //   245: invokestatic min : (II)I
    //   248: invokestatic max : (II)I
    //   251: istore_2
    //   252: aload_0
    //   253: getfield mViewFlinger : Landroid/support/v7/widget/RecyclerView$ViewFlinger;
    //   256: iload_1
    //   257: iload_2
    //   258: invokevirtual fling : (II)V
    //   261: iconst_1
    //   262: ireturn
    //   263: iconst_0
    //   264: ireturn
  }
  
  public View focusSearch(View paramView, int paramInt) {
    View view1;
    int i;
    View view2 = this.mLayout.onInterceptFocusSearch(paramView, paramInt);
    if (view2 != null)
      return view2; 
    if (this.mAdapter != null && this.mLayout != null && !isComputingLayout() && !this.mLayoutFrozen) {
      i = 1;
    } else {
      i = 0;
    } 
    FocusFinder focusFinder = FocusFinder.getInstance();
    if (i && (paramInt == 2 || paramInt == 1)) {
      if (this.mLayout.canScrollVertically()) {
        byte b1;
        byte b2;
        if (paramInt == 2) {
          b1 = 130;
        } else {
          b1 = 33;
        } 
        if (focusFinder.findNextFocus(this, paramView, b1) == null) {
          b2 = 1;
        } else {
          b2 = 0;
        } 
        i = b2;
        if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
          paramInt = b1;
          i = b2;
        } 
      } else {
        i = 0;
      } 
      int k = i;
      int j = paramInt;
      if (!i) {
        k = i;
        j = paramInt;
        if (this.mLayout.canScrollHorizontally()) {
          boolean bool;
          if (this.mLayout.getLayoutDirection() == 1) {
            i = 1;
          } else {
            i = 0;
          } 
          if (paramInt == 2) {
            j = 1;
          } else {
            j = 0;
          } 
          if ((i ^ j) != 0) {
            i = 66;
          } else {
            i = 17;
          } 
          if (focusFinder.findNextFocus(this, paramView, i) == null) {
            bool = true;
          } else {
            bool = false;
          } 
          k = bool;
          j = paramInt;
          if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
            j = i;
            k = bool;
          } 
        } 
      } 
      if (k != 0) {
        consumePendingUpdateOperations();
        if (findContainingItemView(paramView) == null)
          return null; 
        startInterceptRequestLayout();
        this.mLayout.onFocusSearchFailed(paramView, j, this.mRecycler, this.mState);
        stopInterceptRequestLayout(false);
      } 
      view1 = focusFinder.findNextFocus(this, paramView, j);
      paramInt = j;
    } else {
      view1 = view1.findNextFocus(this, paramView, paramInt);
      if (view1 == null && i != 0) {
        consumePendingUpdateOperations();
        if (findContainingItemView(paramView) == null)
          return null; 
        startInterceptRequestLayout();
        view1 = this.mLayout.onFocusSearchFailed(paramView, paramInt, this.mRecycler, this.mState);
        stopInterceptRequestLayout(false);
      } 
    } 
    if (view1 != null && !view1.hasFocusable()) {
      if (getFocusedChild() == null)
        return super.focusSearch(paramView, paramInt); 
      requestChildOnScreen(view1, (View)null);
      return paramView;
    } 
    if (!isPreferredNextFocus(paramView, view1, paramInt))
      view1 = super.focusSearch(paramView, paramInt); 
    return view1;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    if (this.mLayout == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("RecyclerView has no LayoutManager");
      stringBuilder.append(exceptionLabel());
      throw new IllegalStateException(stringBuilder.toString());
    } 
    return (ViewGroup.LayoutParams)this.mLayout.generateDefaultLayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    StringBuilder stringBuilder;
    if (this.mLayout == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("RecyclerView has no LayoutManager");
      stringBuilder.append(exceptionLabel());
      throw new IllegalStateException(stringBuilder.toString());
    } 
    return (ViewGroup.LayoutParams)this.mLayout.generateLayoutParams(getContext(), (AttributeSet)stringBuilder);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    StringBuilder stringBuilder;
    if (this.mLayout == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("RecyclerView has no LayoutManager");
      stringBuilder.append(exceptionLabel());
      throw new IllegalStateException(stringBuilder.toString());
    } 
    return (ViewGroup.LayoutParams)this.mLayout.generateLayoutParams((ViewGroup.LayoutParams)stringBuilder);
  }
  
  @Nullable
  public Adapter getAdapter() {
    return this.mAdapter;
  }
  
  int getAdapterPositionFor(ViewHolder paramViewHolder) {
    return (paramViewHolder.hasAnyOfTheFlags(524) || !paramViewHolder.isBound()) ? -1 : this.mAdapterHelper.applyPendingUpdatesToPosition(paramViewHolder.mPosition);
  }
  
  public int getBaseline() {
    return (this.mLayout != null) ? this.mLayout.getBaseline() : super.getBaseline();
  }
  
  long getChangedHolderKey(ViewHolder paramViewHolder) {
    long l;
    if (this.mAdapter.hasStableIds()) {
      l = paramViewHolder.getItemId();
    } else {
      l = paramViewHolder.mPosition;
    } 
    return l;
  }
  
  public int getChildAdapterPosition(@NonNull View paramView) {
    byte b;
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    if (viewHolder != null) {
      b = viewHolder.getAdapterPosition();
    } else {
      b = -1;
    } 
    return b;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2) {
    return (this.mChildDrawingOrderCallback == null) ? super.getChildDrawingOrder(paramInt1, paramInt2) : this.mChildDrawingOrderCallback.onGetChildDrawingOrder(paramInt1, paramInt2);
  }
  
  public long getChildItemId(@NonNull View paramView) {
    Adapter adapter = this.mAdapter;
    long l = -1L;
    if (adapter == null || !this.mAdapter.hasStableIds())
      return -1L; 
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    if (viewHolder != null)
      l = viewHolder.getItemId(); 
    return l;
  }
  
  public int getChildLayoutPosition(@NonNull View paramView) {
    byte b;
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    if (viewHolder != null) {
      b = viewHolder.getLayoutPosition();
    } else {
      b = -1;
    } 
    return b;
  }
  
  @Deprecated
  public int getChildPosition(@NonNull View paramView) {
    return getChildAdapterPosition(paramView);
  }
  
  public ViewHolder getChildViewHolder(@NonNull View paramView) {
    ViewParent viewParent = paramView.getParent();
    if (viewParent != null && viewParent != this) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("View ");
      stringBuilder.append(paramView);
      stringBuilder.append(" is not a direct child of ");
      stringBuilder.append(this);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return getChildViewHolderInt(paramView);
  }
  
  public boolean getClipToPadding() {
    return this.mClipToPadding;
  }
  
  @Nullable
  public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
    return this.mAccessibilityDelegate;
  }
  
  public void getDecoratedBoundsWithMargins(@NonNull View paramView, @NonNull Rect paramRect) {
    getDecoratedBoundsWithMarginsInt(paramView, paramRect);
  }
  
  @NonNull
  public EdgeEffectFactory getEdgeEffectFactory() {
    return this.mEdgeEffectFactory;
  }
  
  @Nullable
  public ItemAnimator getItemAnimator() {
    return this.mItemAnimator;
  }
  
  Rect getItemDecorInsetsForChild(View paramView) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (!layoutParams.mInsetsDirty)
      return layoutParams.mDecorInsets; 
    if (this.mState.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid()))
      return layoutParams.mDecorInsets; 
    Rect rect = layoutParams.mDecorInsets;
    rect.set(0, 0, 0, 0);
    int i = this.mItemDecorations.size();
    for (byte b = 0; b < i; b++) {
      this.mTempRect.set(0, 0, 0, 0);
      ((ItemDecoration)this.mItemDecorations.get(b)).getItemOffsets(this.mTempRect, paramView, this, this.mState);
      rect.left += this.mTempRect.left;
      rect.top += this.mTempRect.top;
      rect.right += this.mTempRect.right;
      rect.bottom += this.mTempRect.bottom;
    } 
    layoutParams.mInsetsDirty = false;
    return rect;
  }
  
  @NonNull
  public ItemDecoration getItemDecorationAt(int paramInt) {
    int i = getItemDecorationCount();
    if (paramInt < 0 || paramInt >= i) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramInt);
      stringBuilder.append(" is an invalid index for size ");
      stringBuilder.append(i);
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    } 
    return this.mItemDecorations.get(paramInt);
  }
  
  public int getItemDecorationCount() {
    return this.mItemDecorations.size();
  }
  
  @Nullable
  public LayoutManager getLayoutManager() {
    return this.mLayout;
  }
  
  public int getMaxFlingVelocity() {
    return this.mMaxFlingVelocity;
  }
  
  public int getMinFlingVelocity() {
    return this.mMinFlingVelocity;
  }
  
  long getNanoTime() {
    return ALLOW_THREAD_GAP_WORK ? System.nanoTime() : 0L;
  }
  
  @Nullable
  public OnFlingListener getOnFlingListener() {
    return this.mOnFlingListener;
  }
  
  public boolean getPreserveFocusAfterLayout() {
    return this.mPreserveFocusAfterLayout;
  }
  
  @NonNull
  public RecycledViewPool getRecycledViewPool() {
    return this.mRecycler.getRecycledViewPool();
  }
  
  public int getScrollState() {
    return this.mScrollState;
  }
  
  public boolean hasFixedSize() {
    return this.mHasFixedSize;
  }
  
  public boolean hasNestedScrollingParent() {
    return getScrollingChildHelper().hasNestedScrollingParent();
  }
  
  public boolean hasNestedScrollingParent(int paramInt) {
    return getScrollingChildHelper().hasNestedScrollingParent(paramInt);
  }
  
  public boolean hasPendingAdapterUpdates() {
    return (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates());
  }
  
  void initAdapterManager() {
    this.mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() {
          void dispatchUpdate(AdapterHelper.UpdateOp param1UpdateOp) {
            int i = param1UpdateOp.cmd;
            if (i != 4) {
              if (i != 8) {
                switch (i) {
                  default:
                    return;
                  case 2:
                    RecyclerView.this.mLayout.onItemsRemoved(RecyclerView.this, param1UpdateOp.positionStart, param1UpdateOp.itemCount);
                  case 1:
                    break;
                } 
                RecyclerView.this.mLayout.onItemsAdded(RecyclerView.this, param1UpdateOp.positionStart, param1UpdateOp.itemCount);
              } 
              RecyclerView.this.mLayout.onItemsMoved(RecyclerView.this, param1UpdateOp.positionStart, param1UpdateOp.itemCount, 1);
            } 
            RecyclerView.this.mLayout.onItemsUpdated(RecyclerView.this, param1UpdateOp.positionStart, param1UpdateOp.itemCount, param1UpdateOp.payload);
          }
          
          public RecyclerView.ViewHolder findViewHolder(int param1Int) {
            RecyclerView.ViewHolder viewHolder = RecyclerView.this.findViewHolderForPosition(param1Int, true);
            return (viewHolder == null) ? null : (RecyclerView.this.mChildHelper.isHidden(viewHolder.itemView) ? null : viewHolder);
          }
          
          public void markViewHoldersUpdated(int param1Int1, int param1Int2, Object param1Object) {
            RecyclerView.this.viewRangeUpdate(param1Int1, param1Int2, param1Object);
            RecyclerView.this.mItemsChanged = true;
          }
          
          public void offsetPositionsForAdd(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForInsert(param1Int1, param1Int2);
            RecyclerView.this.mItemsAddedOrRemoved = true;
          }
          
          public void offsetPositionsForMove(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForMove(param1Int1, param1Int2);
            RecyclerView.this.mItemsAddedOrRemoved = true;
          }
          
          public void offsetPositionsForRemovingInvisible(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForRemove(param1Int1, param1Int2, true);
            RecyclerView.this.mItemsAddedOrRemoved = true;
            RecyclerView.State state = RecyclerView.this.mState;
            state.mDeletedInvisibleItemCountSincePreviousLayout += param1Int2;
          }
          
          public void offsetPositionsForRemovingLaidOutOrNewView(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForRemove(param1Int1, param1Int2, false);
            RecyclerView.this.mItemsAddedOrRemoved = true;
          }
          
          public void onDispatchFirstPass(AdapterHelper.UpdateOp param1UpdateOp) {
            dispatchUpdate(param1UpdateOp);
          }
          
          public void onDispatchSecondPass(AdapterHelper.UpdateOp param1UpdateOp) {
            dispatchUpdate(param1UpdateOp);
          }
        });
  }
  
  @VisibleForTesting
  void initFastScroller(StateListDrawable paramStateListDrawable1, Drawable paramDrawable1, StateListDrawable paramStateListDrawable2, Drawable paramDrawable2) {
    StringBuilder stringBuilder;
    if (paramStateListDrawable1 == null || paramDrawable1 == null || paramStateListDrawable2 == null || paramDrawable2 == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Trying to set fast scroller without both required drawables.");
      stringBuilder.append(exceptionLabel());
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    Resources resources = getContext().getResources();
    new FastScroller(this, (StateListDrawable)stringBuilder, paramDrawable1, paramStateListDrawable2, paramDrawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
  }
  
  void invalidateGlows() {
    this.mBottomGlow = null;
    this.mTopGlow = null;
    this.mRightGlow = null;
    this.mLeftGlow = null;
  }
  
  public void invalidateItemDecorations() {
    if (this.mItemDecorations.size() == 0)
      return; 
    if (this.mLayout != null)
      this.mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout"); 
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  boolean isAccessibilityEnabled() {
    boolean bool;
    if (this.mAccessibilityManager != null && this.mAccessibilityManager.isEnabled()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isAnimating() {
    boolean bool;
    if (this.mItemAnimator != null && this.mItemAnimator.isRunning()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isAttachedToWindow() {
    return this.mIsAttached;
  }
  
  public boolean isComputingLayout() {
    boolean bool;
    if (this.mLayoutOrScrollCounter > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isLayoutFrozen() {
    return this.mLayoutFrozen;
  }
  
  public boolean isNestedScrollingEnabled() {
    return getScrollingChildHelper().isNestedScrollingEnabled();
  }
  
  void jumpToPositionForSmoothScroller(int paramInt) {
    if (this.mLayout == null)
      return; 
    this.mLayout.scrollToPosition(paramInt);
    awakenScrollBars();
  }
  
  void markItemDecorInsetsDirty() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++)
      ((LayoutParams)this.mChildHelper.getUnfilteredChildAt(b).getLayoutParams()).mInsetsDirty = true; 
    this.mRecycler.markItemDecorInsetsDirty();
  }
  
  void markKnownViewsInvalid() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore())
        viewHolder.addFlags(6); 
    } 
    markItemDecorInsetsDirty();
    this.mRecycler.markKnownViewsInvalid();
  }
  
  public void offsetChildrenHorizontal(@Px int paramInt) {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++)
      this.mChildHelper.getChildAt(b).offsetLeftAndRight(paramInt); 
  }
  
  public void offsetChildrenVertical(@Px int paramInt) {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++)
      this.mChildHelper.getChildAt(b).offsetTopAndBottom(paramInt); 
  }
  
  void offsetPositionRecordsForInsert(int paramInt1, int paramInt2) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore() && viewHolder.mPosition >= paramInt1) {
        viewHolder.offsetPosition(paramInt2, false);
        this.mState.mStructureChanged = true;
      } 
    } 
    this.mRecycler.offsetPositionRecordsForInsert(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForMove(int paramInt1, int paramInt2) {
    int j;
    boolean bool;
    int k;
    int i = this.mChildHelper.getUnfilteredChildCount();
    if (paramInt1 < paramInt2) {
      j = paramInt2;
      bool = true;
      k = paramInt1;
    } else {
      j = paramInt1;
      k = paramInt2;
      bool = true;
    } 
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null && viewHolder.mPosition >= k && viewHolder.mPosition <= j) {
        if (viewHolder.mPosition == paramInt1) {
          viewHolder.offsetPosition(paramInt2 - paramInt1, false);
        } else {
          viewHolder.offsetPosition(bool, false);
        } 
        this.mState.mStructureChanged = true;
      } 
    } 
    this.mRecycler.offsetPositionRecordsForMove(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForRemove(int paramInt1, int paramInt2, boolean paramBoolean) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore())
        if (viewHolder.mPosition >= paramInt1 + paramInt2) {
          viewHolder.offsetPosition(-paramInt2, paramBoolean);
          this.mState.mStructureChanged = true;
        } else if (viewHolder.mPosition >= paramInt1) {
          viewHolder.flagRemovedAndOffsetPosition(paramInt1 - 1, -paramInt2, paramBoolean);
          this.mState.mStructureChanged = true;
        }  
    } 
    this.mRecycler.offsetPositionRecordsForRemove(paramInt1, paramInt2, paramBoolean);
    requestLayout();
  }
  
  protected void onAttachedToWindow() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial onAttachedToWindow : ()V
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield mLayoutOrScrollCounter : I
    //   9: iconst_1
    //   10: istore_1
    //   11: aload_0
    //   12: iconst_1
    //   13: putfield mIsAttached : Z
    //   16: aload_0
    //   17: getfield mFirstLayoutComplete : Z
    //   20: ifeq -> 33
    //   23: aload_0
    //   24: invokevirtual isLayoutRequested : ()Z
    //   27: ifne -> 33
    //   30: goto -> 35
    //   33: iconst_0
    //   34: istore_1
    //   35: aload_0
    //   36: iload_1
    //   37: putfield mFirstLayoutComplete : Z
    //   40: aload_0
    //   41: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   44: ifnull -> 55
    //   47: aload_0
    //   48: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   51: aload_0
    //   52: invokevirtual dispatchAttachedToWindow : (Landroid/support/v7/widget/RecyclerView;)V
    //   55: aload_0
    //   56: iconst_0
    //   57: putfield mPostedAnimatorRunner : Z
    //   60: getstatic android/support/v7/widget/RecyclerView.ALLOW_THREAD_GAP_WORK : Z
    //   63: ifeq -> 164
    //   66: aload_0
    //   67: getstatic android/support/v7/widget/GapWorker.sGapWorker : Ljava/lang/ThreadLocal;
    //   70: invokevirtual get : ()Ljava/lang/Object;
    //   73: checkcast android/support/v7/widget/GapWorker
    //   76: putfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   79: aload_0
    //   80: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   83: ifnonnull -> 156
    //   86: aload_0
    //   87: new android/support/v7/widget/GapWorker
    //   90: dup
    //   91: invokespecial <init> : ()V
    //   94: putfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   97: aload_0
    //   98: invokestatic getDisplay : (Landroid/view/View;)Landroid/view/Display;
    //   101: astore_2
    //   102: aload_0
    //   103: invokevirtual isInEditMode : ()Z
    //   106: ifne -> 129
    //   109: aload_2
    //   110: ifnull -> 129
    //   113: aload_2
    //   114: invokevirtual getRefreshRate : ()F
    //   117: fstore_3
    //   118: fload_3
    //   119: ldc_w 30.0
    //   122: fcmpl
    //   123: iflt -> 129
    //   126: goto -> 133
    //   129: ldc_w 60.0
    //   132: fstore_3
    //   133: aload_0
    //   134: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   137: ldc_w 1.0E9
    //   140: fload_3
    //   141: fdiv
    //   142: f2l
    //   143: putfield mFrameIntervalNs : J
    //   146: getstatic android/support/v7/widget/GapWorker.sGapWorker : Ljava/lang/ThreadLocal;
    //   149: aload_0
    //   150: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   153: invokevirtual set : (Ljava/lang/Object;)V
    //   156: aload_0
    //   157: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   160: aload_0
    //   161: invokevirtual add : (Landroid/support/v7/widget/RecyclerView;)V
    //   164: return
  }
  
  public void onChildAttachedToWindow(@NonNull View paramView) {}
  
  public void onChildDetachedFromWindow(@NonNull View paramView) {}
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if (this.mItemAnimator != null)
      this.mItemAnimator.endAnimations(); 
    stopScroll();
    this.mIsAttached = false;
    if (this.mLayout != null)
      this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler); 
    this.mPendingAccessibilityImportanceChange.clear();
    removeCallbacks(this.mItemAnimatorRunner);
    this.mViewInfoStore.onDetach();
    if (ALLOW_THREAD_GAP_WORK && this.mGapWorker != null) {
      this.mGapWorker.remove(this);
      this.mGapWorker = null;
    } 
  }
  
  public void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    int i = this.mItemDecorations.size();
    for (byte b = 0; b < i; b++)
      ((ItemDecoration)this.mItemDecorations.get(b)).onDraw(paramCanvas, this, this.mState); 
  }
  
  void onEnterLayoutOrScroll() {
    this.mLayoutOrScrollCounter++;
  }
  
  void onExitLayoutOrScroll() {
    onExitLayoutOrScroll(true);
  }
  
  void onExitLayoutOrScroll(boolean paramBoolean) {
    this.mLayoutOrScrollCounter--;
    if (this.mLayoutOrScrollCounter < 1) {
      this.mLayoutOrScrollCounter = 0;
      if (paramBoolean) {
        dispatchContentChangedIfNecessary();
        dispatchPendingImportantForAccessibilityChanges();
      } 
    } 
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   4: ifnonnull -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: aload_0
    //   10: getfield mLayoutFrozen : Z
    //   13: ifeq -> 18
    //   16: iconst_0
    //   17: ireturn
    //   18: aload_1
    //   19: invokevirtual getAction : ()I
    //   22: bipush #8
    //   24: if_icmpne -> 177
    //   27: aload_1
    //   28: invokevirtual getSource : ()I
    //   31: iconst_2
    //   32: iand
    //   33: ifeq -> 92
    //   36: aload_0
    //   37: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   40: invokevirtual canScrollVertically : ()Z
    //   43: ifeq -> 57
    //   46: aload_1
    //   47: bipush #9
    //   49: invokevirtual getAxisValue : (I)F
    //   52: fneg
    //   53: fstore_2
    //   54: goto -> 59
    //   57: fconst_0
    //   58: fstore_2
    //   59: fload_2
    //   60: fstore_3
    //   61: aload_0
    //   62: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   65: invokevirtual canScrollHorizontally : ()Z
    //   68: ifeq -> 81
    //   71: aload_1
    //   72: bipush #10
    //   74: invokevirtual getAxisValue : (I)F
    //   77: fstore_3
    //   78: goto -> 145
    //   81: fconst_0
    //   82: fstore #4
    //   84: fload_3
    //   85: fstore_2
    //   86: fload #4
    //   88: fstore_3
    //   89: goto -> 145
    //   92: aload_1
    //   93: invokevirtual getSource : ()I
    //   96: ldc_w 4194304
    //   99: iand
    //   100: ifeq -> 141
    //   103: aload_1
    //   104: bipush #26
    //   106: invokevirtual getAxisValue : (I)F
    //   109: fstore_3
    //   110: aload_0
    //   111: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   114: invokevirtual canScrollVertically : ()Z
    //   117: ifeq -> 126
    //   120: fload_3
    //   121: fneg
    //   122: fstore_3
    //   123: goto -> 81
    //   126: aload_0
    //   127: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   130: invokevirtual canScrollHorizontally : ()Z
    //   133: ifeq -> 141
    //   136: fconst_0
    //   137: fstore_2
    //   138: goto -> 145
    //   141: fconst_0
    //   142: fstore_2
    //   143: fload_2
    //   144: fstore_3
    //   145: fload_2
    //   146: fconst_0
    //   147: fcmpl
    //   148: ifne -> 157
    //   151: fload_3
    //   152: fconst_0
    //   153: fcmpl
    //   154: ifeq -> 177
    //   157: aload_0
    //   158: fload_3
    //   159: aload_0
    //   160: getfield mScaledHorizontalScrollFactor : F
    //   163: fmul
    //   164: f2i
    //   165: fload_2
    //   166: aload_0
    //   167: getfield mScaledVerticalScrollFactor : F
    //   170: fmul
    //   171: f2i
    //   172: aload_1
    //   173: invokevirtual scrollByInternal : (IILandroid/view/MotionEvent;)Z
    //   176: pop
    //   177: iconst_0
    //   178: ireturn
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    StringBuilder stringBuilder;
    int[] arrayOfInt;
    int k;
    boolean bool1 = this.mLayoutFrozen;
    boolean bool = false;
    if (bool1)
      return false; 
    if (dispatchOnItemTouchIntercept(paramMotionEvent)) {
      cancelTouch();
      return true;
    } 
    if (this.mLayout == null)
      return false; 
    bool1 = this.mLayout.canScrollHorizontally();
    boolean bool2 = this.mLayout.canScrollVertically();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain(); 
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int i = paramMotionEvent.getActionMasked();
    int j = paramMotionEvent.getActionIndex();
    switch (i) {
      case 6:
        onPointerUp(paramMotionEvent);
        break;
      case 5:
        this.mScrollPointerId = paramMotionEvent.getPointerId(j);
        i = (int)(paramMotionEvent.getX(j) + 0.5F);
        this.mLastTouchX = i;
        this.mInitialTouchX = i;
        j = (int)(paramMotionEvent.getY(j) + 0.5F);
        this.mLastTouchY = j;
        this.mInitialTouchY = j;
        break;
      case 3:
        cancelTouch();
        break;
      case 2:
        i = paramMotionEvent.findPointerIndex(this.mScrollPointerId);
        if (i < 0) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Error processing scroll; pointer index for id ");
          stringBuilder.append(this.mScrollPointerId);
          stringBuilder.append(" not found. Did any MotionEvents get skipped?");
          Log.e("RecyclerView", stringBuilder.toString());
          return false;
        } 
        j = (int)(stringBuilder.getX(i) + 0.5F);
        k = (int)(stringBuilder.getY(i) + 0.5F);
        if (this.mScrollState != 1) {
          i = this.mInitialTouchX;
          int m = this.mInitialTouchY;
          if (bool1 && Math.abs(j - i) > this.mTouchSlop) {
            this.mLastTouchX = j;
            j = 1;
          } else {
            j = 0;
          } 
          i = j;
          if (bool2) {
            i = j;
            if (Math.abs(k - m) > this.mTouchSlop) {
              this.mLastTouchY = k;
              i = 1;
            } 
          } 
          if (i != 0)
            setScrollState(1); 
        } 
        break;
      case 1:
        this.mVelocityTracker.clear();
        stopNestedScroll(0);
        break;
      case 0:
        if (this.mIgnoreMotionEventTillDown)
          this.mIgnoreMotionEventTillDown = false; 
        this.mScrollPointerId = stringBuilder.getPointerId(0);
        j = (int)(stringBuilder.getX() + 0.5F);
        this.mLastTouchX = j;
        this.mInitialTouchX = j;
        j = (int)(stringBuilder.getY() + 0.5F);
        this.mLastTouchY = j;
        this.mInitialTouchY = j;
        if (this.mScrollState == 2) {
          getParent().requestDisallowInterceptTouchEvent(true);
          setScrollState(1);
        } 
        arrayOfInt = this.mNestedOffsets;
        this.mNestedOffsets[1] = 0;
        arrayOfInt[0] = 0;
        if (bool1) {
          j = 1;
        } else {
          j = 0;
        } 
        i = j;
        if (bool2)
          i = j | 0x2; 
        startNestedScroll(i, 0);
        break;
    } 
    if (this.mScrollState == 1)
      bool = true; 
    return bool;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    TraceCompat.beginSection("RV OnLayout");
    dispatchLayout();
    TraceCompat.endSection();
    this.mFirstLayoutComplete = true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mLayout == null) {
      defaultOnMeasure(paramInt1, paramInt2);
      return;
    } 
    boolean bool = this.mLayout.isAutoMeasureEnabled();
    boolean bool1 = false;
    if (bool) {
      int i = View.MeasureSpec.getMode(paramInt1);
      int j = View.MeasureSpec.getMode(paramInt2);
      this.mLayout.onMeasure(this.mRecycler, this.mState, paramInt1, paramInt2);
      boolean bool2 = bool1;
      if (i == 1073741824) {
        bool2 = bool1;
        if (j == 1073741824)
          bool2 = true; 
      } 
      if (bool2 || this.mAdapter == null)
        return; 
      if (this.mState.mLayoutStep == 1)
        dispatchLayoutStep1(); 
      this.mLayout.setMeasureSpecs(paramInt1, paramInt2);
      this.mState.mIsMeasuring = true;
      dispatchLayoutStep2();
      this.mLayout.setMeasuredDimensionFromChildren(paramInt1, paramInt2);
      if (this.mLayout.shouldMeasureTwice()) {
        this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
        this.mState.mIsMeasuring = true;
        dispatchLayoutStep2();
        this.mLayout.setMeasuredDimensionFromChildren(paramInt1, paramInt2);
      } 
    } else {
      if (this.mHasFixedSize) {
        this.mLayout.onMeasure(this.mRecycler, this.mState, paramInt1, paramInt2);
        return;
      } 
      if (this.mAdapterUpdateDuringMeasure) {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        onExitLayoutOrScroll();
        if (this.mState.mRunPredictiveAnimations) {
          this.mState.mInPreLayout = true;
        } else {
          this.mAdapterHelper.consumeUpdatesInOnePass();
          this.mState.mInPreLayout = false;
        } 
        this.mAdapterUpdateDuringMeasure = false;
        stopInterceptRequestLayout(false);
      } else if (this.mState.mRunPredictiveAnimations) {
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        return;
      } 
      if (this.mAdapter != null) {
        this.mState.mItemCount = this.mAdapter.getItemCount();
      } else {
        this.mState.mItemCount = 0;
      } 
      startInterceptRequestLayout();
      this.mLayout.onMeasure(this.mRecycler, this.mState, paramInt1, paramInt2);
      stopInterceptRequestLayout(false);
      this.mState.mInPreLayout = false;
    } 
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect) {
    return isComputingLayout() ? false : super.onRequestFocusInDescendants(paramInt, paramRect);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    this.mPendingSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
    if (this.mLayout != null && this.mPendingSavedState.mLayoutState != null)
      this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState); 
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    if (this.mPendingSavedState != null) {
      savedState.copyFrom(this.mPendingSavedState);
    } else if (this.mLayout != null) {
      savedState.mLayoutState = this.mLayout.onSaveInstanceState();
    } else {
      savedState.mLayoutState = null;
    } 
    return (Parcelable)savedState;
  }
  
  public void onScrollStateChanged(int paramInt) {}
  
  public void onScrolled(@Px int paramInt1, @Px int paramInt2) {}
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3 || paramInt2 != paramInt4)
      invalidateGlows(); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLayoutFrozen : Z
    //   4: istore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: iload_2
    //   8: ifne -> 997
    //   11: aload_0
    //   12: getfield mIgnoreMotionEventTillDown : Z
    //   15: ifeq -> 21
    //   18: goto -> 997
    //   21: aload_0
    //   22: aload_1
    //   23: invokespecial dispatchOnItemTouch : (Landroid/view/MotionEvent;)Z
    //   26: ifeq -> 35
    //   29: aload_0
    //   30: invokespecial cancelTouch : ()V
    //   33: iconst_1
    //   34: ireturn
    //   35: aload_0
    //   36: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   39: ifnonnull -> 44
    //   42: iconst_0
    //   43: ireturn
    //   44: aload_0
    //   45: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   48: invokevirtual canScrollHorizontally : ()Z
    //   51: istore #4
    //   53: aload_0
    //   54: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   57: invokevirtual canScrollVertically : ()Z
    //   60: istore_2
    //   61: aload_0
    //   62: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   65: ifnonnull -> 75
    //   68: aload_0
    //   69: invokestatic obtain : ()Landroid/view/VelocityTracker;
    //   72: putfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   75: aload_1
    //   76: invokestatic obtain : (Landroid/view/MotionEvent;)Landroid/view/MotionEvent;
    //   79: astore #5
    //   81: aload_1
    //   82: invokevirtual getActionMasked : ()I
    //   85: istore #6
    //   87: aload_1
    //   88: invokevirtual getActionIndex : ()I
    //   91: istore #7
    //   93: iload #6
    //   95: ifne -> 116
    //   98: aload_0
    //   99: getfield mNestedOffsets : [I
    //   102: astore #8
    //   104: aload_0
    //   105: getfield mNestedOffsets : [I
    //   108: iconst_1
    //   109: iconst_0
    //   110: iastore
    //   111: aload #8
    //   113: iconst_0
    //   114: iconst_0
    //   115: iastore
    //   116: aload #5
    //   118: aload_0
    //   119: getfield mNestedOffsets : [I
    //   122: iconst_0
    //   123: iaload
    //   124: i2f
    //   125: aload_0
    //   126: getfield mNestedOffsets : [I
    //   129: iconst_1
    //   130: iaload
    //   131: i2f
    //   132: invokevirtual offsetLocation : (FF)V
    //   135: iload #6
    //   137: tableswitch default -> 180, 0 -> 882, 1 -> 767, 2 -> 273, 3 -> 263, 4 -> 180, 5 -> 197, 6 -> 186
    //   180: iload_3
    //   181: istore #7
    //   183: goto -> 976
    //   186: aload_0
    //   187: aload_1
    //   188: invokespecial onPointerUp : (Landroid/view/MotionEvent;)V
    //   191: iload_3
    //   192: istore #7
    //   194: goto -> 976
    //   197: aload_0
    //   198: aload_1
    //   199: iload #7
    //   201: invokevirtual getPointerId : (I)I
    //   204: putfield mScrollPointerId : I
    //   207: aload_1
    //   208: iload #7
    //   210: invokevirtual getX : (I)F
    //   213: ldc_w 0.5
    //   216: fadd
    //   217: f2i
    //   218: istore #6
    //   220: aload_0
    //   221: iload #6
    //   223: putfield mLastTouchX : I
    //   226: aload_0
    //   227: iload #6
    //   229: putfield mInitialTouchX : I
    //   232: aload_1
    //   233: iload #7
    //   235: invokevirtual getY : (I)F
    //   238: ldc_w 0.5
    //   241: fadd
    //   242: f2i
    //   243: istore #7
    //   245: aload_0
    //   246: iload #7
    //   248: putfield mLastTouchY : I
    //   251: aload_0
    //   252: iload #7
    //   254: putfield mInitialTouchY : I
    //   257: iload_3
    //   258: istore #7
    //   260: goto -> 976
    //   263: aload_0
    //   264: invokespecial cancelTouch : ()V
    //   267: iload_3
    //   268: istore #7
    //   270: goto -> 976
    //   273: aload_1
    //   274: aload_0
    //   275: getfield mScrollPointerId : I
    //   278: invokevirtual findPointerIndex : (I)I
    //   281: istore #7
    //   283: iload #7
    //   285: ifge -> 333
    //   288: new java/lang/StringBuilder
    //   291: dup
    //   292: invokespecial <init> : ()V
    //   295: astore_1
    //   296: aload_1
    //   297: ldc_w 'Error processing scroll; pointer index for id '
    //   300: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: pop
    //   304: aload_1
    //   305: aload_0
    //   306: getfield mScrollPointerId : I
    //   309: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   312: pop
    //   313: aload_1
    //   314: ldc_w ' not found. Did any MotionEvents get skipped?'
    //   317: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: pop
    //   321: ldc 'RecyclerView'
    //   323: aload_1
    //   324: invokevirtual toString : ()Ljava/lang/String;
    //   327: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   330: pop
    //   331: iconst_0
    //   332: ireturn
    //   333: aload_1
    //   334: iload #7
    //   336: invokevirtual getX : (I)F
    //   339: ldc_w 0.5
    //   342: fadd
    //   343: f2i
    //   344: istore #9
    //   346: aload_1
    //   347: iload #7
    //   349: invokevirtual getY : (I)F
    //   352: ldc_w 0.5
    //   355: fadd
    //   356: f2i
    //   357: istore #10
    //   359: aload_0
    //   360: getfield mLastTouchX : I
    //   363: iload #9
    //   365: isub
    //   366: istore #11
    //   368: aload_0
    //   369: getfield mLastTouchY : I
    //   372: iload #10
    //   374: isub
    //   375: istore #12
    //   377: iload #11
    //   379: istore #6
    //   381: iload #12
    //   383: istore #7
    //   385: aload_0
    //   386: iload #11
    //   388: iload #12
    //   390: aload_0
    //   391: getfield mScrollConsumed : [I
    //   394: aload_0
    //   395: getfield mScrollOffset : [I
    //   398: iconst_0
    //   399: invokevirtual dispatchNestedPreScroll : (II[I[II)Z
    //   402: ifeq -> 482
    //   405: iload #11
    //   407: aload_0
    //   408: getfield mScrollConsumed : [I
    //   411: iconst_0
    //   412: iaload
    //   413: isub
    //   414: istore #6
    //   416: iload #12
    //   418: aload_0
    //   419: getfield mScrollConsumed : [I
    //   422: iconst_1
    //   423: iaload
    //   424: isub
    //   425: istore #7
    //   427: aload #5
    //   429: aload_0
    //   430: getfield mScrollOffset : [I
    //   433: iconst_0
    //   434: iaload
    //   435: i2f
    //   436: aload_0
    //   437: getfield mScrollOffset : [I
    //   440: iconst_1
    //   441: iaload
    //   442: i2f
    //   443: invokevirtual offsetLocation : (FF)V
    //   446: aload_0
    //   447: getfield mNestedOffsets : [I
    //   450: astore_1
    //   451: aload_1
    //   452: iconst_0
    //   453: aload_1
    //   454: iconst_0
    //   455: iaload
    //   456: aload_0
    //   457: getfield mScrollOffset : [I
    //   460: iconst_0
    //   461: iaload
    //   462: iadd
    //   463: iastore
    //   464: aload_0
    //   465: getfield mNestedOffsets : [I
    //   468: astore_1
    //   469: aload_1
    //   470: iconst_1
    //   471: aload_1
    //   472: iconst_1
    //   473: iaload
    //   474: aload_0
    //   475: getfield mScrollOffset : [I
    //   478: iconst_1
    //   479: iaload
    //   480: iadd
    //   481: iastore
    //   482: iload #6
    //   484: istore #12
    //   486: iload #7
    //   488: istore #11
    //   490: aload_0
    //   491: getfield mScrollState : I
    //   494: iconst_1
    //   495: if_icmpeq -> 637
    //   498: iload #4
    //   500: ifeq -> 547
    //   503: iload #6
    //   505: invokestatic abs : (I)I
    //   508: aload_0
    //   509: getfield mTouchSlop : I
    //   512: if_icmple -> 547
    //   515: iload #6
    //   517: ifle -> 532
    //   520: iload #6
    //   522: aload_0
    //   523: getfield mTouchSlop : I
    //   526: isub
    //   527: istore #6
    //   529: goto -> 541
    //   532: iload #6
    //   534: aload_0
    //   535: getfield mTouchSlop : I
    //   538: iadd
    //   539: istore #6
    //   541: iconst_1
    //   542: istore #12
    //   544: goto -> 550
    //   547: iconst_0
    //   548: istore #12
    //   550: iload #12
    //   552: istore #13
    //   554: iload #7
    //   556: istore #14
    //   558: iload_2
    //   559: ifeq -> 611
    //   562: iload #12
    //   564: istore #13
    //   566: iload #7
    //   568: istore #14
    //   570: iload #7
    //   572: invokestatic abs : (I)I
    //   575: aload_0
    //   576: getfield mTouchSlop : I
    //   579: if_icmple -> 611
    //   582: iload #7
    //   584: ifle -> 599
    //   587: iload #7
    //   589: aload_0
    //   590: getfield mTouchSlop : I
    //   593: isub
    //   594: istore #14
    //   596: goto -> 608
    //   599: iload #7
    //   601: aload_0
    //   602: getfield mTouchSlop : I
    //   605: iadd
    //   606: istore #14
    //   608: iconst_1
    //   609: istore #13
    //   611: iload #6
    //   613: istore #12
    //   615: iload #14
    //   617: istore #11
    //   619: iload #13
    //   621: ifeq -> 637
    //   624: aload_0
    //   625: iconst_1
    //   626: invokevirtual setScrollState : (I)V
    //   629: iload #14
    //   631: istore #11
    //   633: iload #6
    //   635: istore #12
    //   637: iload_3
    //   638: istore #7
    //   640: aload_0
    //   641: getfield mScrollState : I
    //   644: iconst_1
    //   645: if_icmpne -> 976
    //   648: aload_0
    //   649: iload #9
    //   651: aload_0
    //   652: getfield mScrollOffset : [I
    //   655: iconst_0
    //   656: iaload
    //   657: isub
    //   658: putfield mLastTouchX : I
    //   661: aload_0
    //   662: iload #10
    //   664: aload_0
    //   665: getfield mScrollOffset : [I
    //   668: iconst_1
    //   669: iaload
    //   670: isub
    //   671: putfield mLastTouchY : I
    //   674: iload #4
    //   676: ifeq -> 686
    //   679: iload #12
    //   681: istore #7
    //   683: goto -> 689
    //   686: iconst_0
    //   687: istore #7
    //   689: iload_2
    //   690: ifeq -> 700
    //   693: iload #11
    //   695: istore #6
    //   697: goto -> 703
    //   700: iconst_0
    //   701: istore #6
    //   703: aload_0
    //   704: iload #7
    //   706: iload #6
    //   708: aload #5
    //   710: invokevirtual scrollByInternal : (IILandroid/view/MotionEvent;)Z
    //   713: ifeq -> 726
    //   716: aload_0
    //   717: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   720: iconst_1
    //   721: invokeinterface requestDisallowInterceptTouchEvent : (Z)V
    //   726: iload_3
    //   727: istore #7
    //   729: aload_0
    //   730: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   733: ifnull -> 976
    //   736: iload #12
    //   738: ifne -> 749
    //   741: iload_3
    //   742: istore #7
    //   744: iload #11
    //   746: ifeq -> 976
    //   749: aload_0
    //   750: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   753: aload_0
    //   754: iload #12
    //   756: iload #11
    //   758: invokevirtual postFromTraversal : (Landroid/support/v7/widget/RecyclerView;II)V
    //   761: iload_3
    //   762: istore #7
    //   764: goto -> 976
    //   767: aload_0
    //   768: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   771: aload #5
    //   773: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
    //   776: aload_0
    //   777: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   780: sipush #1000
    //   783: aload_0
    //   784: getfield mMaxFlingVelocity : I
    //   787: i2f
    //   788: invokevirtual computeCurrentVelocity : (IF)V
    //   791: iload #4
    //   793: ifeq -> 813
    //   796: aload_0
    //   797: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   800: aload_0
    //   801: getfield mScrollPointerId : I
    //   804: invokevirtual getXVelocity : (I)F
    //   807: fneg
    //   808: fstore #15
    //   810: goto -> 816
    //   813: fconst_0
    //   814: fstore #15
    //   816: iload_2
    //   817: ifeq -> 837
    //   820: aload_0
    //   821: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   824: aload_0
    //   825: getfield mScrollPointerId : I
    //   828: invokevirtual getYVelocity : (I)F
    //   831: fneg
    //   832: fstore #16
    //   834: goto -> 840
    //   837: fconst_0
    //   838: fstore #16
    //   840: fload #15
    //   842: fconst_0
    //   843: fcmpl
    //   844: ifne -> 854
    //   847: fload #16
    //   849: fconst_0
    //   850: fcmpl
    //   851: ifeq -> 867
    //   854: aload_0
    //   855: fload #15
    //   857: f2i
    //   858: fload #16
    //   860: f2i
    //   861: invokevirtual fling : (II)Z
    //   864: ifne -> 872
    //   867: aload_0
    //   868: iconst_0
    //   869: invokevirtual setScrollState : (I)V
    //   872: aload_0
    //   873: invokespecial resetTouch : ()V
    //   876: iconst_1
    //   877: istore #7
    //   879: goto -> 976
    //   882: aload_0
    //   883: aload_1
    //   884: iconst_0
    //   885: invokevirtual getPointerId : (I)I
    //   888: putfield mScrollPointerId : I
    //   891: aload_1
    //   892: invokevirtual getX : ()F
    //   895: ldc_w 0.5
    //   898: fadd
    //   899: f2i
    //   900: istore #7
    //   902: aload_0
    //   903: iload #7
    //   905: putfield mLastTouchX : I
    //   908: aload_0
    //   909: iload #7
    //   911: putfield mInitialTouchX : I
    //   914: aload_1
    //   915: invokevirtual getY : ()F
    //   918: ldc_w 0.5
    //   921: fadd
    //   922: f2i
    //   923: istore #7
    //   925: aload_0
    //   926: iload #7
    //   928: putfield mLastTouchY : I
    //   931: aload_0
    //   932: iload #7
    //   934: putfield mInitialTouchY : I
    //   937: iload #4
    //   939: ifeq -> 948
    //   942: iconst_1
    //   943: istore #7
    //   945: goto -> 951
    //   948: iconst_0
    //   949: istore #7
    //   951: iload #7
    //   953: istore #6
    //   955: iload_2
    //   956: ifeq -> 965
    //   959: iload #7
    //   961: iconst_2
    //   962: ior
    //   963: istore #6
    //   965: aload_0
    //   966: iload #6
    //   968: iconst_0
    //   969: invokevirtual startNestedScroll : (II)Z
    //   972: pop
    //   973: iload_3
    //   974: istore #7
    //   976: iload #7
    //   978: ifne -> 990
    //   981: aload_0
    //   982: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   985: aload #5
    //   987: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
    //   990: aload #5
    //   992: invokevirtual recycle : ()V
    //   995: iconst_1
    //   996: ireturn
    //   997: iconst_0
    //   998: ireturn
  }
  
  void postAnimationRunner() {
    if (!this.mPostedAnimatorRunner && this.mIsAttached) {
      ViewCompat.postOnAnimation((View)this, this.mItemAnimatorRunner);
      this.mPostedAnimatorRunner = true;
    } 
  }
  
  void processDataSetCompletelyChanged(boolean paramBoolean) {
    this.mDispatchItemsChangedEvent = paramBoolean | this.mDispatchItemsChangedEvent;
    this.mDataSetHasChangedAfterLayout = true;
    markKnownViewsInvalid();
  }
  
  void recordAnimationInfoIfBouncedHiddenView(ViewHolder paramViewHolder, ItemAnimator.ItemHolderInfo paramItemHolderInfo) {
    paramViewHolder.setFlags(0, 8192);
    if (this.mState.mTrackOldChangeHolders && paramViewHolder.isUpdated() && !paramViewHolder.isRemoved() && !paramViewHolder.shouldIgnore()) {
      long l = getChangedHolderKey(paramViewHolder);
      this.mViewInfoStore.addToOldChangeHolders(l, paramViewHolder);
    } 
    this.mViewInfoStore.addToPreLayout(paramViewHolder, paramItemHolderInfo);
  }
  
  void removeAndRecycleViews() {
    if (this.mItemAnimator != null)
      this.mItemAnimator.endAnimations(); 
    if (this.mLayout != null) {
      this.mLayout.removeAndRecycleAllViews(this.mRecycler);
      this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
    } 
    this.mRecycler.clear();
  }
  
  boolean removeAnimatingView(View paramView) {
    startInterceptRequestLayout();
    boolean bool = this.mChildHelper.removeViewIfHidden(paramView);
    if (bool) {
      ViewHolder viewHolder = getChildViewHolderInt(paramView);
      this.mRecycler.unscrapView(viewHolder);
      this.mRecycler.recycleViewHolderInternal(viewHolder);
    } 
    stopInterceptRequestLayout(bool ^ true);
    return bool;
  }
  
  protected void removeDetachedView(View paramView, boolean paramBoolean) {
    StringBuilder stringBuilder;
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    if (viewHolder != null)
      if (viewHolder.isTmpDetached()) {
        viewHolder.clearTmpDetachFlag();
      } else if (!viewHolder.shouldIgnore()) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
        stringBuilder.append(viewHolder);
        stringBuilder.append(exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      }  
    stringBuilder.clearAnimation();
    dispatchChildDetached((View)stringBuilder);
    super.removeDetachedView((View)stringBuilder, paramBoolean);
  }
  
  public void removeItemDecoration(@NonNull ItemDecoration paramItemDecoration) {
    if (this.mLayout != null)
      this.mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout"); 
    this.mItemDecorations.remove(paramItemDecoration);
    if (this.mItemDecorations.isEmpty()) {
      boolean bool;
      if (getOverScrollMode() == 2) {
        bool = true;
      } else {
        bool = false;
      } 
      setWillNotDraw(bool);
    } 
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void removeItemDecorationAt(int paramInt) {
    int i = getItemDecorationCount();
    if (paramInt < 0 || paramInt >= i) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramInt);
      stringBuilder.append(" is an invalid index for size ");
      stringBuilder.append(i);
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    } 
    removeItemDecoration(getItemDecorationAt(paramInt));
  }
  
  public void removeOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener paramOnChildAttachStateChangeListener) {
    if (this.mOnChildAttachStateListeners == null)
      return; 
    this.mOnChildAttachStateListeners.remove(paramOnChildAttachStateChangeListener);
  }
  
  public void removeOnItemTouchListener(@NonNull OnItemTouchListener paramOnItemTouchListener) {
    this.mOnItemTouchListeners.remove(paramOnItemTouchListener);
    if (this.mActiveOnItemTouchListener == paramOnItemTouchListener)
      this.mActiveOnItemTouchListener = null; 
  }
  
  public void removeOnScrollListener(@NonNull OnScrollListener paramOnScrollListener) {
    if (this.mScrollListeners != null)
      this.mScrollListeners.remove(paramOnScrollListener); 
  }
  
  void repositionShadowingViews() {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = this.mChildHelper.getChildAt(b);
      ViewHolder viewHolder = getChildViewHolder(view);
      if (viewHolder != null && viewHolder.mShadowingHolder != null) {
        View view1 = viewHolder.mShadowingHolder.itemView;
        int j = view.getLeft();
        int k = view.getTop();
        if (j != view1.getLeft() || k != view1.getTop())
          view1.layout(j, k, view1.getWidth() + j, view1.getHeight() + k); 
      } 
    } 
  }
  
  public void requestChildFocus(View paramView1, View paramView2) {
    if (!this.mLayout.onRequestChildFocus(this, this.mState, paramView1, paramView2) && paramView2 != null)
      requestChildOnScreen(paramView1, paramView2); 
    super.requestChildFocus(paramView1, paramView2);
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean) {
    return this.mLayout.requestChildRectangleOnScreen(this, paramView, paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean) {
    int i = this.mOnItemTouchListeners.size();
    for (byte b = 0; b < i; b++)
      ((OnItemTouchListener)this.mOnItemTouchListeners.get(b)).onRequestDisallowInterceptTouchEvent(paramBoolean); 
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout() {
    if (this.mInterceptRequestLayoutDepth == 0 && !this.mLayoutFrozen) {
      super.requestLayout();
    } else {
      this.mLayoutWasDefered = true;
    } 
  }
  
  void saveOldPositions() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (!viewHolder.shouldIgnore())
        viewHolder.saveOldPosition(); 
    } 
  }
  
  public void scrollBy(int paramInt1, int paramInt2) {
    if (this.mLayout == null) {
      Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    if (this.mLayoutFrozen)
      return; 
    boolean bool1 = this.mLayout.canScrollHorizontally();
    boolean bool2 = this.mLayout.canScrollVertically();
    if (bool1 || bool2) {
      if (!bool1)
        paramInt1 = 0; 
      if (!bool2)
        paramInt2 = 0; 
      scrollByInternal(paramInt1, paramInt2, (MotionEvent)null);
    } 
  }
  
  boolean scrollByInternal(int paramInt1, int paramInt2, MotionEvent paramMotionEvent) {
    int[] arrayOfInt;
    boolean bool2;
    boolean bool3;
    boolean bool4;
    boolean bool5;
    consumePendingUpdateOperations();
    Adapter adapter = this.mAdapter;
    boolean bool1 = true;
    if (adapter != null) {
      scrollStep(paramInt1, paramInt2, this.mScrollStepConsumed);
      bool2 = this.mScrollStepConsumed[0];
      bool3 = this.mScrollStepConsumed[1];
      bool4 = bool2;
      int i = bool3;
      bool2 = paramInt1 - bool2;
      bool5 = paramInt2 - bool3;
      bool3 = i;
    } else {
      bool5 = false;
      boolean bool = bool5;
      bool3 = bool;
      bool2 = bool3;
      bool4 = bool;
    } 
    if (!this.mItemDecorations.isEmpty())
      invalidate(); 
    if (dispatchNestedScroll(bool4, bool3, bool2, bool5, this.mScrollOffset, 0)) {
      this.mLastTouchX -= this.mScrollOffset[0];
      this.mLastTouchY -= this.mScrollOffset[1];
      if (paramMotionEvent != null)
        paramMotionEvent.offsetLocation(this.mScrollOffset[0], this.mScrollOffset[1]); 
      arrayOfInt = this.mNestedOffsets;
      arrayOfInt[0] = arrayOfInt[0] + this.mScrollOffset[0];
      arrayOfInt = this.mNestedOffsets;
      arrayOfInt[1] = arrayOfInt[1] + this.mScrollOffset[1];
    } else if (getOverScrollMode() != 2) {
      if (arrayOfInt != null && !MotionEventCompat.isFromSource((MotionEvent)arrayOfInt, 8194))
        pullGlows(arrayOfInt.getX(), bool2, arrayOfInt.getY(), bool5); 
      considerReleasingGlowsOnScroll(paramInt1, paramInt2);
    } 
    if (bool4 || bool3)
      dispatchOnScrolled(bool4, bool3); 
    if (!awakenScrollBars())
      invalidate(); 
    boolean bool6 = bool1;
    if (!bool4)
      if (bool3) {
        bool6 = bool1;
      } else {
        bool6 = false;
      }  
    return bool6;
  }
  
  void scrollStep(int paramInt1, int paramInt2, @Nullable int[] paramArrayOfint) {
    startInterceptRequestLayout();
    onEnterLayoutOrScroll();
    TraceCompat.beginSection("RV Scroll");
    fillRemainingScrollValues(this.mState);
    if (paramInt1 != 0) {
      paramInt1 = this.mLayout.scrollHorizontallyBy(paramInt1, this.mRecycler, this.mState);
    } else {
      paramInt1 = 0;
    } 
    if (paramInt2 != 0) {
      paramInt2 = this.mLayout.scrollVerticallyBy(paramInt2, this.mRecycler, this.mState);
    } else {
      paramInt2 = 0;
    } 
    TraceCompat.endSection();
    repositionShadowingViews();
    onExitLayoutOrScroll();
    stopInterceptRequestLayout(false);
    if (paramArrayOfint != null) {
      paramArrayOfint[0] = paramInt1;
      paramArrayOfint[1] = paramInt2;
    } 
  }
  
  public void scrollTo(int paramInt1, int paramInt2) {
    Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
  }
  
  public void scrollToPosition(int paramInt) {
    if (this.mLayoutFrozen)
      return; 
    stopScroll();
    if (this.mLayout == null) {
      Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    this.mLayout.scrollToPosition(paramInt);
    awakenScrollBars();
  }
  
  public void sendAccessibilityEventUnchecked(AccessibilityEvent paramAccessibilityEvent) {
    if (shouldDeferAccessibilityEvent(paramAccessibilityEvent))
      return; 
    super.sendAccessibilityEventUnchecked(paramAccessibilityEvent);
  }
  
  public void setAccessibilityDelegateCompat(@Nullable RecyclerViewAccessibilityDelegate paramRecyclerViewAccessibilityDelegate) {
    this.mAccessibilityDelegate = paramRecyclerViewAccessibilityDelegate;
    ViewCompat.setAccessibilityDelegate((View)this, this.mAccessibilityDelegate);
  }
  
  public void setAdapter(@Nullable Adapter paramAdapter) {
    setLayoutFrozen(false);
    setAdapterInternal(paramAdapter, false, true);
    processDataSetCompletelyChanged(false);
    requestLayout();
  }
  
  public void setChildDrawingOrderCallback(@Nullable ChildDrawingOrderCallback paramChildDrawingOrderCallback) {
    boolean bool;
    if (paramChildDrawingOrderCallback == this.mChildDrawingOrderCallback)
      return; 
    this.mChildDrawingOrderCallback = paramChildDrawingOrderCallback;
    if (this.mChildDrawingOrderCallback != null) {
      bool = true;
    } else {
      bool = false;
    } 
    setChildrenDrawingOrderEnabled(bool);
  }
  
  @VisibleForTesting
  boolean setChildImportantForAccessibilityInternal(ViewHolder paramViewHolder, int paramInt) {
    if (isComputingLayout()) {
      paramViewHolder.mPendingAccessibilityState = paramInt;
      this.mPendingAccessibilityImportanceChange.add(paramViewHolder);
      return false;
    } 
    ViewCompat.setImportantForAccessibility(paramViewHolder.itemView, paramInt);
    return true;
  }
  
  public void setClipToPadding(boolean paramBoolean) {
    if (paramBoolean != this.mClipToPadding)
      invalidateGlows(); 
    this.mClipToPadding = paramBoolean;
    super.setClipToPadding(paramBoolean);
    if (this.mFirstLayoutComplete)
      requestLayout(); 
  }
  
  public void setEdgeEffectFactory(@NonNull EdgeEffectFactory paramEdgeEffectFactory) {
    Preconditions.checkNotNull(paramEdgeEffectFactory);
    this.mEdgeEffectFactory = paramEdgeEffectFactory;
    invalidateGlows();
  }
  
  public void setHasFixedSize(boolean paramBoolean) {
    this.mHasFixedSize = paramBoolean;
  }
  
  public void setItemAnimator(@Nullable ItemAnimator paramItemAnimator) {
    if (this.mItemAnimator != null) {
      this.mItemAnimator.endAnimations();
      this.mItemAnimator.setListener(null);
    } 
    this.mItemAnimator = paramItemAnimator;
    if (this.mItemAnimator != null)
      this.mItemAnimator.setListener(this.mItemAnimatorListener); 
  }
  
  public void setItemViewCacheSize(int paramInt) {
    this.mRecycler.setViewCacheSize(paramInt);
  }
  
  public void setLayoutFrozen(boolean paramBoolean) {
    if (paramBoolean != this.mLayoutFrozen) {
      assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
      if (!paramBoolean) {
        this.mLayoutFrozen = false;
        if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null)
          requestLayout(); 
        this.mLayoutWasDefered = false;
      } else {
        long l = SystemClock.uptimeMillis();
        onTouchEvent(MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0));
        this.mLayoutFrozen = true;
        this.mIgnoreMotionEventTillDown = true;
        stopScroll();
      } 
    } 
  }
  
  public void setLayoutManager(@Nullable LayoutManager paramLayoutManager) {
    if (paramLayoutManager == this.mLayout)
      return; 
    stopScroll();
    if (this.mLayout != null) {
      if (this.mItemAnimator != null)
        this.mItemAnimator.endAnimations(); 
      this.mLayout.removeAndRecycleAllViews(this.mRecycler);
      this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
      this.mRecycler.clear();
      if (this.mIsAttached)
        this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler); 
      this.mLayout.setRecyclerView(null);
      this.mLayout = null;
    } else {
      this.mRecycler.clear();
    } 
    this.mChildHelper.removeAllViewsUnfiltered();
    this.mLayout = paramLayoutManager;
    if (paramLayoutManager != null) {
      if (paramLayoutManager.mRecyclerView != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LayoutManager ");
        stringBuilder.append(paramLayoutManager);
        stringBuilder.append(" is already attached to a RecyclerView:");
        stringBuilder.append(paramLayoutManager.mRecyclerView.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      this.mLayout.setRecyclerView(this);
      if (this.mIsAttached)
        this.mLayout.dispatchAttachedToWindow(this); 
    } 
    this.mRecycler.updateViewCacheSize();
    requestLayout();
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean) {
    getScrollingChildHelper().setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnFlingListener(@Nullable OnFlingListener paramOnFlingListener) {
    this.mOnFlingListener = paramOnFlingListener;
  }
  
  @Deprecated
  public void setOnScrollListener(@Nullable OnScrollListener paramOnScrollListener) {
    this.mScrollListener = paramOnScrollListener;
  }
  
  public void setPreserveFocusAfterLayout(boolean paramBoolean) {
    this.mPreserveFocusAfterLayout = paramBoolean;
  }
  
  public void setRecycledViewPool(@Nullable RecycledViewPool paramRecycledViewPool) {
    this.mRecycler.setRecycledViewPool(paramRecycledViewPool);
  }
  
  public void setRecyclerListener(@Nullable RecyclerListener paramRecyclerListener) {
    this.mRecyclerListener = paramRecyclerListener;
  }
  
  void setScrollState(int paramInt) {
    if (paramInt == this.mScrollState)
      return; 
    this.mScrollState = paramInt;
    if (paramInt != 2)
      stopScrollersInternal(); 
    dispatchOnScrollStateChanged(paramInt);
  }
  
  public void setScrollingTouchSlop(int paramInt) {
    StringBuilder stringBuilder;
    ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("setScrollingTouchSlop(): bad argument constant ");
        stringBuilder.append(paramInt);
        stringBuilder.append("; using default value");
        Log.w("RecyclerView", stringBuilder.toString());
        break;
      case 1:
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        return;
      case 0:
        break;
    } 
    this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
  }
  
  public void setViewCacheExtension(@Nullable ViewCacheExtension paramViewCacheExtension) {
    this.mRecycler.setViewCacheExtension(paramViewCacheExtension);
  }
  
  boolean shouldDeferAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    if (isComputingLayout()) {
      boolean bool1;
      if (paramAccessibilityEvent != null) {
        bool1 = AccessibilityEventCompat.getContentChangeTypes(paramAccessibilityEvent);
      } else {
        bool1 = false;
      } 
      boolean bool2 = bool1;
      if (!bool1)
        bool2 = false; 
      this.mEatenAccessibilityChangeFlags = bool2 | this.mEatenAccessibilityChangeFlags;
      return true;
    } 
    return false;
  }
  
  public void smoothScrollBy(@Px int paramInt1, @Px int paramInt2) {
    smoothScrollBy(paramInt1, paramInt2, (Interpolator)null);
  }
  
  public void smoothScrollBy(@Px int paramInt1, @Px int paramInt2, @Nullable Interpolator paramInterpolator) {
    if (this.mLayout == null) {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    if (this.mLayoutFrozen)
      return; 
    if (!this.mLayout.canScrollHorizontally())
      paramInt1 = 0; 
    if (!this.mLayout.canScrollVertically())
      paramInt2 = 0; 
    if (paramInt1 != 0 || paramInt2 != 0)
      this.mViewFlinger.smoothScrollBy(paramInt1, paramInt2, paramInterpolator); 
  }
  
  public void smoothScrollToPosition(int paramInt) {
    if (this.mLayoutFrozen)
      return; 
    if (this.mLayout == null) {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    this.mLayout.smoothScrollToPosition(this, this.mState, paramInt);
  }
  
  void startInterceptRequestLayout() {
    this.mInterceptRequestLayoutDepth++;
    if (this.mInterceptRequestLayoutDepth == 1 && !this.mLayoutFrozen)
      this.mLayoutWasDefered = false; 
  }
  
  public boolean startNestedScroll(int paramInt) {
    return getScrollingChildHelper().startNestedScroll(paramInt);
  }
  
  public boolean startNestedScroll(int paramInt1, int paramInt2) {
    return getScrollingChildHelper().startNestedScroll(paramInt1, paramInt2);
  }
  
  void stopInterceptRequestLayout(boolean paramBoolean) {
    if (this.mInterceptRequestLayoutDepth < 1)
      this.mInterceptRequestLayoutDepth = 1; 
    if (!paramBoolean && !this.mLayoutFrozen)
      this.mLayoutWasDefered = false; 
    if (this.mInterceptRequestLayoutDepth == 1) {
      if (paramBoolean && this.mLayoutWasDefered && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null)
        dispatchLayout(); 
      if (!this.mLayoutFrozen)
        this.mLayoutWasDefered = false; 
    } 
    this.mInterceptRequestLayoutDepth--;
  }
  
  public void stopNestedScroll() {
    getScrollingChildHelper().stopNestedScroll();
  }
  
  public void stopNestedScroll(int paramInt) {
    getScrollingChildHelper().stopNestedScroll(paramInt);
  }
  
  public void stopScroll() {
    setScrollState(0);
    stopScrollersInternal();
  }
  
  public void swapAdapter(@Nullable Adapter paramAdapter, boolean paramBoolean) {
    setLayoutFrozen(false);
    setAdapterInternal(paramAdapter, true, paramBoolean);
    processDataSetCompletelyChanged(true);
    requestLayout();
  }
  
  void viewRangeUpdate(int paramInt1, int paramInt2, Object paramObject) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      View view = this.mChildHelper.getUnfilteredChildAt(b);
      ViewHolder viewHolder = getChildViewHolderInt(view);
      if (viewHolder != null && !viewHolder.shouldIgnore() && viewHolder.mPosition >= paramInt1 && viewHolder.mPosition < paramInt1 + paramInt2) {
        viewHolder.addFlags(2);
        viewHolder.addChangePayload(paramObject);
        ((LayoutParams)view.getLayoutParams()).mInsetsDirty = true;
      } 
    } 
    this.mRecycler.viewRangeUpdate(paramInt1, paramInt2);
  }
  
  static {
    boolean bool;
  }
  
  static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
  
  static final boolean ALLOW_THREAD_GAP_WORK;
  
  private static final int[] CLIP_TO_PADDING_ATTR;
  
  static final boolean DEBUG = false;
  
  static final int DEFAULT_ORIENTATION = 1;
  
  static final boolean DISPATCH_TEMP_DETACH = false;
  
  private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
  
  static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
  
  static final long FOREVER_NS = 9223372036854775807L;
  
  public static final int HORIZONTAL = 0;
  
  private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
  
  private static final int INVALID_POINTER = -1;
  
  public static final int INVALID_TYPE = -1;
  
  private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
  
  static final int MAX_SCROLL_DURATION = 2000;
  
  private static final int[] NESTED_SCROLLING_ATTRS = new int[] { 16843830 };
  
  public static final long NO_ID = -1L;
  
  public static final int NO_POSITION = -1;
  
  static final boolean POST_UPDATES_ON_ANIMATION;
  
  public static final int SCROLL_STATE_DRAGGING = 1;
  
  public static final int SCROLL_STATE_IDLE = 0;
  
  public static final int SCROLL_STATE_SETTLING = 2;
  
  static final String TAG = "RecyclerView";
  
  public static final int TOUCH_SLOP_DEFAULT = 0;
  
  public static final int TOUCH_SLOP_PAGING = 1;
  
  static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
  
  static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
  
  private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
  
  static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
  
  private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
  
  private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
  
  static final String TRACE_PREFETCH_TAG = "RV Prefetch";
  
  static final String TRACE_SCROLL_TAG = "RV Scroll";
  
  static final boolean VERBOSE_TRACING = false;
  
  public static final int VERTICAL = 1;
  
  static final Interpolator sQuinticInterpolator;
  
  RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
  
  private final AccessibilityManager mAccessibilityManager;
  
  private OnItemTouchListener mActiveOnItemTouchListener;
  
  Adapter mAdapter;
  
  AdapterHelper mAdapterHelper;
  
  boolean mAdapterUpdateDuringMeasure;
  
  private EdgeEffect mBottomGlow;
  
  private ChildDrawingOrderCallback mChildDrawingOrderCallback;
  
  ChildHelper mChildHelper;
  
  boolean mClipToPadding;
  
  boolean mDataSetHasChangedAfterLayout;
  
  boolean mDispatchItemsChangedEvent;
  
  private int mDispatchScrollCounter;
  
  private int mEatenAccessibilityChangeFlags;
  
  @NonNull
  private EdgeEffectFactory mEdgeEffectFactory;
  
  boolean mEnableFastScroller;
  
  @VisibleForTesting
  boolean mFirstLayoutComplete;
  
  GapWorker mGapWorker;
  
  boolean mHasFixedSize;
  
  private boolean mIgnoreMotionEventTillDown;
  
  private int mInitialTouchX;
  
  private int mInitialTouchY;
  
  private int mInterceptRequestLayoutDepth;
  
  boolean mIsAttached;
  
  ItemAnimator mItemAnimator;
  
  private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
  
  private Runnable mItemAnimatorRunner;
  
  final ArrayList<ItemDecoration> mItemDecorations;
  
  boolean mItemsAddedOrRemoved;
  
  boolean mItemsChanged;
  
  private int mLastTouchX;
  
  private int mLastTouchY;
  
  @VisibleForTesting
  LayoutManager mLayout;
  
  boolean mLayoutFrozen;
  
  private int mLayoutOrScrollCounter;
  
  boolean mLayoutWasDefered;
  
  private EdgeEffect mLeftGlow;
  
  private final int mMaxFlingVelocity;
  
  private final int mMinFlingVelocity;
  
  private final int[] mMinMaxLayoutPositions;
  
  private final int[] mNestedOffsets;
  
  private final RecyclerViewDataObserver mObserver;
  
  private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
  
  private OnFlingListener mOnFlingListener;
  
  private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
  
  @VisibleForTesting
  final List<ViewHolder> mPendingAccessibilityImportanceChange;
  
  private SavedState mPendingSavedState;
  
  boolean mPostedAnimatorRunner;
  
  GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
  
  private boolean mPreserveFocusAfterLayout;
  
  final Recycler mRecycler;
  
  RecyclerListener mRecyclerListener;
  
  private EdgeEffect mRightGlow;
  
  private float mScaledHorizontalScrollFactor;
  
  private float mScaledVerticalScrollFactor;
  
  final int[] mScrollConsumed;
  
  private OnScrollListener mScrollListener;
  
  private List<OnScrollListener> mScrollListeners;
  
  private final int[] mScrollOffset;
  
  private int mScrollPointerId;
  
  private int mScrollState;
  
  final int[] mScrollStepConsumed;
  
  private NestedScrollingChildHelper mScrollingChildHelper;
  
  final State mState;
  
  final Rect mTempRect;
  
  private final Rect mTempRect2;
  
  final RectF mTempRectF;
  
  private EdgeEffect mTopGlow;
  
  private int mTouchSlop;
  
  final Runnable mUpdateChildViewsRunnable;
  
  private VelocityTracker mVelocityTracker;
  
  final ViewFlinger mViewFlinger;
  
  private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
  
  final ViewInfoStore mViewInfoStore;
  
  public static abstract class Adapter<VH extends ViewHolder> {
    private boolean mHasStableIds = false;
    
    private final RecyclerView.AdapterDataObservable mObservable = new RecyclerView.AdapterDataObservable();
    
    public final void bindViewHolder(@NonNull VH param1VH, int param1Int) {
      ((RecyclerView.ViewHolder)param1VH).mPosition = param1Int;
      if (hasStableIds())
        ((RecyclerView.ViewHolder)param1VH).mItemId = getItemId(param1Int); 
      param1VH.setFlags(1, 519);
      TraceCompat.beginSection("RV OnBindView");
      onBindViewHolder(param1VH, param1Int, param1VH.getUnmodifiedPayloads());
      param1VH.clearPayload();
      ViewGroup.LayoutParams layoutParams = ((RecyclerView.ViewHolder)param1VH).itemView.getLayoutParams();
      if (layoutParams instanceof RecyclerView.LayoutParams)
        ((RecyclerView.LayoutParams)layoutParams).mInsetsDirty = true; 
      TraceCompat.endSection();
    }
    
    @NonNull
    public final VH createViewHolder(@NonNull ViewGroup param1ViewGroup, int param1Int) {
      try {
        IllegalStateException illegalStateException;
        TraceCompat.beginSection("RV CreateView");
        param1ViewGroup = (ViewGroup)onCreateViewHolder(param1ViewGroup, param1Int);
        if (((RecyclerView.ViewHolder)param1ViewGroup).itemView.getParent() != null) {
          illegalStateException = new IllegalStateException();
          this("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
          throw illegalStateException;
        } 
        ((RecyclerView.ViewHolder)illegalStateException).mItemViewType = param1Int;
        return (VH)illegalStateException;
      } finally {
        TraceCompat.endSection();
      } 
    }
    
    public abstract int getItemCount();
    
    public long getItemId(int param1Int) {
      return -1L;
    }
    
    public int getItemViewType(int param1Int) {
      return 0;
    }
    
    public final boolean hasObservers() {
      return this.mObservable.hasObservers();
    }
    
    public final boolean hasStableIds() {
      return this.mHasStableIds;
    }
    
    public final void notifyDataSetChanged() {
      this.mObservable.notifyChanged();
    }
    
    public final void notifyItemChanged(int param1Int) {
      this.mObservable.notifyItemRangeChanged(param1Int, 1);
    }
    
    public final void notifyItemChanged(int param1Int, @Nullable Object param1Object) {
      this.mObservable.notifyItemRangeChanged(param1Int, 1, param1Object);
    }
    
    public final void notifyItemInserted(int param1Int) {
      this.mObservable.notifyItemRangeInserted(param1Int, 1);
    }
    
    public final void notifyItemMoved(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemMoved(param1Int1, param1Int2);
    }
    
    public final void notifyItemRangeChanged(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemRangeChanged(param1Int1, param1Int2);
    }
    
    public final void notifyItemRangeChanged(int param1Int1, int param1Int2, @Nullable Object param1Object) {
      this.mObservable.notifyItemRangeChanged(param1Int1, param1Int2, param1Object);
    }
    
    public final void notifyItemRangeInserted(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemRangeInserted(param1Int1, param1Int2);
    }
    
    public final void notifyItemRangeRemoved(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemRangeRemoved(param1Int1, param1Int2);
    }
    
    public final void notifyItemRemoved(int param1Int) {
      this.mObservable.notifyItemRangeRemoved(param1Int, 1);
    }
    
    public void onAttachedToRecyclerView(@NonNull RecyclerView param1RecyclerView) {}
    
    public abstract void onBindViewHolder(@NonNull VH param1VH, int param1Int);
    
    public void onBindViewHolder(@NonNull VH param1VH, int param1Int, @NonNull List<Object> param1List) {
      onBindViewHolder(param1VH, param1Int);
    }
    
    @NonNull
    public abstract VH onCreateViewHolder(@NonNull ViewGroup param1ViewGroup, int param1Int);
    
    public void onDetachedFromRecyclerView(@NonNull RecyclerView param1RecyclerView) {}
    
    public boolean onFailedToRecycleView(@NonNull VH param1VH) {
      return false;
    }
    
    public void onViewAttachedToWindow(@NonNull VH param1VH) {}
    
    public void onViewDetachedFromWindow(@NonNull VH param1VH) {}
    
    public void onViewRecycled(@NonNull VH param1VH) {}
    
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver param1AdapterDataObserver) {
      this.mObservable.registerObserver(param1AdapterDataObserver);
    }
    
    public void setHasStableIds(boolean param1Boolean) {
      if (hasObservers())
        throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers."); 
      this.mHasStableIds = param1Boolean;
    }
    
    public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver param1AdapterDataObserver) {
      this.mObservable.unregisterObserver(param1AdapterDataObserver);
    }
  }
  
  static class AdapterDataObservable extends Observable<AdapterDataObserver> {
    public boolean hasObservers() {
      return this.mObservers.isEmpty() ^ true;
    }
    
    public void notifyChanged() {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onChanged(); 
    }
    
    public void notifyItemMoved(int param1Int1, int param1Int2) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeMoved(param1Int1, param1Int2, 1); 
    }
    
    public void notifyItemRangeChanged(int param1Int1, int param1Int2) {
      notifyItemRangeChanged(param1Int1, param1Int2, (Object)null);
    }
    
    public void notifyItemRangeChanged(int param1Int1, int param1Int2, @Nullable Object param1Object) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeChanged(param1Int1, param1Int2, param1Object); 
    }
    
    public void notifyItemRangeInserted(int param1Int1, int param1Int2) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeInserted(param1Int1, param1Int2); 
    }
    
    public void notifyItemRangeRemoved(int param1Int1, int param1Int2) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeRemoved(param1Int1, param1Int2); 
    }
  }
  
  public static abstract class AdapterDataObserver {
    public void onChanged() {}
    
    public void onItemRangeChanged(int param1Int1, int param1Int2) {}
    
    public void onItemRangeChanged(int param1Int1, int param1Int2, @Nullable Object param1Object) {
      onItemRangeChanged(param1Int1, param1Int2);
    }
    
    public void onItemRangeInserted(int param1Int1, int param1Int2) {}
    
    public void onItemRangeMoved(int param1Int1, int param1Int2, int param1Int3) {}
    
    public void onItemRangeRemoved(int param1Int1, int param1Int2) {}
  }
  
  public static interface ChildDrawingOrderCallback {
    int onGetChildDrawingOrder(int param1Int1, int param1Int2);
  }
  
  public static class EdgeEffectFactory {
    public static final int DIRECTION_BOTTOM = 3;
    
    public static final int DIRECTION_LEFT = 0;
    
    public static final int DIRECTION_RIGHT = 2;
    
    public static final int DIRECTION_TOP = 1;
    
    @NonNull
    protected EdgeEffect createEdgeEffect(@NonNull RecyclerView param1RecyclerView, int param1Int) {
      return new EdgeEffect(param1RecyclerView.getContext());
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface EdgeDirection {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EdgeDirection {}
  
  public static abstract class ItemAnimator {
    public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    
    public static final int FLAG_CHANGED = 2;
    
    public static final int FLAG_INVALIDATED = 4;
    
    public static final int FLAG_MOVED = 2048;
    
    public static final int FLAG_REMOVED = 8;
    
    private long mAddDuration = 120L;
    
    private long mChangeDuration = 250L;
    
    private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<>();
    
    private ItemAnimatorListener mListener = null;
    
    private long mMoveDuration = 250L;
    
    private long mRemoveDuration = 120L;
    
    static int buildAdapterChangeFlagsForAnimations(RecyclerView.ViewHolder param1ViewHolder) {
      int i = param1ViewHolder.mFlags & 0xE;
      if (param1ViewHolder.isInvalid())
        return 4; 
      int j = i;
      if ((i & 0x4) == 0) {
        int k = param1ViewHolder.getOldPosition();
        int m = param1ViewHolder.getAdapterPosition();
        j = i;
        if (k != -1) {
          j = i;
          if (m != -1) {
            j = i;
            if (k != m)
              j = i | 0x800; 
          } 
        } 
      } 
      return j;
    }
    
    public abstract boolean animateAppearance(@NonNull RecyclerView.ViewHolder param1ViewHolder, @Nullable ItemHolderInfo param1ItemHolderInfo1, @NonNull ItemHolderInfo param1ItemHolderInfo2);
    
    public abstract boolean animateChange(@NonNull RecyclerView.ViewHolder param1ViewHolder1, @NonNull RecyclerView.ViewHolder param1ViewHolder2, @NonNull ItemHolderInfo param1ItemHolderInfo1, @NonNull ItemHolderInfo param1ItemHolderInfo2);
    
    public abstract boolean animateDisappearance(@NonNull RecyclerView.ViewHolder param1ViewHolder, @NonNull ItemHolderInfo param1ItemHolderInfo1, @Nullable ItemHolderInfo param1ItemHolderInfo2);
    
    public abstract boolean animatePersistence(@NonNull RecyclerView.ViewHolder param1ViewHolder, @NonNull ItemHolderInfo param1ItemHolderInfo1, @NonNull ItemHolderInfo param1ItemHolderInfo2);
    
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return true;
    }
    
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder param1ViewHolder, @NonNull List<Object> param1List) {
      return canReuseUpdatedViewHolder(param1ViewHolder);
    }
    
    public final void dispatchAnimationFinished(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      onAnimationFinished(param1ViewHolder);
      if (this.mListener != null)
        this.mListener.onAnimationFinished(param1ViewHolder); 
    }
    
    public final void dispatchAnimationStarted(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      onAnimationStarted(param1ViewHolder);
    }
    
    public final void dispatchAnimationsFinished() {
      int i = this.mFinishedListeners.size();
      for (byte b = 0; b < i; b++)
        ((ItemAnimatorFinishedListener)this.mFinishedListeners.get(b)).onAnimationsFinished(); 
      this.mFinishedListeners.clear();
    }
    
    public abstract void endAnimation(@NonNull RecyclerView.ViewHolder param1ViewHolder);
    
    public abstract void endAnimations();
    
    public long getAddDuration() {
      return this.mAddDuration;
    }
    
    public long getChangeDuration() {
      return this.mChangeDuration;
    }
    
    public long getMoveDuration() {
      return this.mMoveDuration;
    }
    
    public long getRemoveDuration() {
      return this.mRemoveDuration;
    }
    
    public abstract boolean isRunning();
    
    public final boolean isRunning(@Nullable ItemAnimatorFinishedListener param1ItemAnimatorFinishedListener) {
      boolean bool = isRunning();
      if (param1ItemAnimatorFinishedListener != null)
        if (!bool) {
          param1ItemAnimatorFinishedListener.onAnimationsFinished();
        } else {
          this.mFinishedListeners.add(param1ItemAnimatorFinishedListener);
        }  
      return bool;
    }
    
    @NonNull
    public ItemHolderInfo obtainHolderInfo() {
      return new ItemHolderInfo();
    }
    
    public void onAnimationFinished(@NonNull RecyclerView.ViewHolder param1ViewHolder) {}
    
    public void onAnimationStarted(@NonNull RecyclerView.ViewHolder param1ViewHolder) {}
    
    @NonNull
    public ItemHolderInfo recordPostLayoutInformation(@NonNull RecyclerView.State param1State, @NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return obtainHolderInfo().setFrom(param1ViewHolder);
    }
    
    @NonNull
    public ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State param1State, @NonNull RecyclerView.ViewHolder param1ViewHolder, int param1Int, @NonNull List<Object> param1List) {
      return obtainHolderInfo().setFrom(param1ViewHolder);
    }
    
    public abstract void runPendingAnimations();
    
    public void setAddDuration(long param1Long) {
      this.mAddDuration = param1Long;
    }
    
    public void setChangeDuration(long param1Long) {
      this.mChangeDuration = param1Long;
    }
    
    void setListener(ItemAnimatorListener param1ItemAnimatorListener) {
      this.mListener = param1ItemAnimatorListener;
    }
    
    public void setMoveDuration(long param1Long) {
      this.mMoveDuration = param1Long;
    }
    
    public void setRemoveDuration(long param1Long) {
      this.mRemoveDuration = param1Long;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface AdapterChanges {}
    
    public static interface ItemAnimatorFinishedListener {
      void onAnimationsFinished();
    }
    
    static interface ItemAnimatorListener {
      void onAnimationFinished(@NonNull RecyclerView.ViewHolder param2ViewHolder);
    }
    
    public static class ItemHolderInfo {
      public int bottom;
      
      public int changeFlags;
      
      public int left;
      
      public int right;
      
      public int top;
      
      @NonNull
      public ItemHolderInfo setFrom(@NonNull RecyclerView.ViewHolder param2ViewHolder) {
        return setFrom(param2ViewHolder, 0);
      }
      
      @NonNull
      public ItemHolderInfo setFrom(@NonNull RecyclerView.ViewHolder param2ViewHolder, int param2Int) {
        View view = param2ViewHolder.itemView;
        this.left = view.getLeft();
        this.top = view.getTop();
        this.right = view.getRight();
        this.bottom = view.getBottom();
        return this;
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AdapterChanges {}
  
  public static interface ItemAnimatorFinishedListener {
    void onAnimationsFinished();
  }
  
  static interface ItemAnimatorListener {
    void onAnimationFinished(@NonNull RecyclerView.ViewHolder param1ViewHolder);
  }
  
  public static class ItemHolderInfo {
    public int bottom;
    
    public int changeFlags;
    
    public int left;
    
    public int right;
    
    public int top;
    
    @NonNull
    public ItemHolderInfo setFrom(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return setFrom(param1ViewHolder, 0);
    }
    
    @NonNull
    public ItemHolderInfo setFrom(@NonNull RecyclerView.ViewHolder param1ViewHolder, int param1Int) {
      View view = param1ViewHolder.itemView;
      this.left = view.getLeft();
      this.top = view.getTop();
      this.right = view.getRight();
      this.bottom = view.getBottom();
      return this;
    }
  }
  
  private class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
    public void onAnimationFinished(RecyclerView.ViewHolder param1ViewHolder) {
      param1ViewHolder.setIsRecyclable(true);
      if (param1ViewHolder.mShadowedHolder != null && param1ViewHolder.mShadowingHolder == null)
        param1ViewHolder.mShadowedHolder = null; 
      param1ViewHolder.mShadowingHolder = null;
      if (!param1ViewHolder.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(param1ViewHolder.itemView) && param1ViewHolder.isTmpDetached())
        RecyclerView.this.removeDetachedView(param1ViewHolder.itemView, false); 
    }
  }
  
  public static abstract class ItemDecoration {
    @Deprecated
    public void getItemOffsets(@NonNull Rect param1Rect, int param1Int, @NonNull RecyclerView param1RecyclerView) {
      param1Rect.set(0, 0, 0, 0);
    }
    
    public void getItemOffsets(@NonNull Rect param1Rect, @NonNull View param1View, @NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.State param1State) {
      getItemOffsets(param1Rect, ((RecyclerView.LayoutParams)param1View.getLayoutParams()).getViewLayoutPosition(), param1RecyclerView);
    }
    
    @Deprecated
    public void onDraw(@NonNull Canvas param1Canvas, @NonNull RecyclerView param1RecyclerView) {}
    
    public void onDraw(@NonNull Canvas param1Canvas, @NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.State param1State) {
      onDraw(param1Canvas, param1RecyclerView);
    }
    
    @Deprecated
    public void onDrawOver(@NonNull Canvas param1Canvas, @NonNull RecyclerView param1RecyclerView) {}
    
    public void onDrawOver(@NonNull Canvas param1Canvas, @NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.State param1State) {
      onDrawOver(param1Canvas, param1RecyclerView);
    }
  }
  
  public static abstract class LayoutManager {
    boolean mAutoMeasure = false;
    
    ChildHelper mChildHelper;
    
    private int mHeight;
    
    private int mHeightMode;
    
    ViewBoundsCheck mHorizontalBoundCheck = new ViewBoundsCheck(this.mHorizontalBoundCheckCallback);
    
    private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback = new ViewBoundsCheck.Callback() {
        public View getChildAt(int param2Int) {
          return RecyclerView.LayoutManager.this.getChildAt(param2Int);
        }
        
        public int getChildCount() {
          return RecyclerView.LayoutManager.this.getChildCount();
        }
        
        public int getChildEnd(View param2View) {
          RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
          return RecyclerView.LayoutManager.this.getDecoratedRight(param2View) + layoutParams.rightMargin;
        }
        
        public int getChildStart(View param2View) {
          RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
          return RecyclerView.LayoutManager.this.getDecoratedLeft(param2View) - layoutParams.leftMargin;
        }
        
        public View getParent() {
          return (View)RecyclerView.LayoutManager.this.mRecyclerView;
        }
        
        public int getParentEnd() {
          return RecyclerView.LayoutManager.this.getWidth() - RecyclerView.LayoutManager.this.getPaddingRight();
        }
        
        public int getParentStart() {
          return RecyclerView.LayoutManager.this.getPaddingLeft();
        }
      };
    
    boolean mIsAttachedToWindow = false;
    
    private boolean mItemPrefetchEnabled = true;
    
    private boolean mMeasurementCacheEnabled = true;
    
    int mPrefetchMaxCountObserved;
    
    boolean mPrefetchMaxObservedInInitialPrefetch;
    
    RecyclerView mRecyclerView;
    
    boolean mRequestedSimpleAnimations = false;
    
    @Nullable
    RecyclerView.SmoothScroller mSmoothScroller;
    
    ViewBoundsCheck mVerticalBoundCheck = new ViewBoundsCheck(this.mVerticalBoundCheckCallback);
    
    private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback = new ViewBoundsCheck.Callback() {
        public View getChildAt(int param2Int) {
          return RecyclerView.LayoutManager.this.getChildAt(param2Int);
        }
        
        public int getChildCount() {
          return RecyclerView.LayoutManager.this.getChildCount();
        }
        
        public int getChildEnd(View param2View) {
          RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
          return RecyclerView.LayoutManager.this.getDecoratedBottom(param2View) + layoutParams.bottomMargin;
        }
        
        public int getChildStart(View param2View) {
          RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
          return RecyclerView.LayoutManager.this.getDecoratedTop(param2View) - layoutParams.topMargin;
        }
        
        public View getParent() {
          return (View)RecyclerView.LayoutManager.this.mRecyclerView;
        }
        
        public int getParentEnd() {
          return RecyclerView.LayoutManager.this.getHeight() - RecyclerView.LayoutManager.this.getPaddingBottom();
        }
        
        public int getParentStart() {
          return RecyclerView.LayoutManager.this.getPaddingTop();
        }
      };
    
    private int mWidth;
    
    private int mWidthMode;
    
    private void addViewInt(View param1View, int param1Int, boolean param1Boolean) {
      StringBuilder stringBuilder;
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (param1Boolean || viewHolder.isRemoved()) {
        this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewHolder);
      } else {
        this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewHolder);
      } 
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      if (viewHolder.wasReturnedFromScrap() || viewHolder.isScrap()) {
        if (viewHolder.isScrap()) {
          viewHolder.unScrap();
        } else {
          viewHolder.clearReturnedFromScrapFlag();
        } 
        this.mChildHelper.attachViewToParent(param1View, param1Int, param1View.getLayoutParams(), false);
      } else if (param1View.getParent() == this.mRecyclerView) {
        int i = this.mChildHelper.indexOfChild(param1View);
        int j = param1Int;
        if (param1Int == -1)
          j = this.mChildHelper.getChildCount(); 
        if (i == -1) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
          stringBuilder.append(this.mRecyclerView.indexOfChild(param1View));
          stringBuilder.append(this.mRecyclerView.exceptionLabel());
          throw new IllegalStateException(stringBuilder.toString());
        } 
        if (i != j)
          this.mRecyclerView.mLayout.moveView(i, j); 
      } else {
        this.mChildHelper.addView(param1View, param1Int, false);
        layoutParams.mInsetsDirty = true;
        if (this.mSmoothScroller != null && this.mSmoothScroller.isRunning())
          this.mSmoothScroller.onChildAttachedToWindow(param1View); 
      } 
      if (layoutParams.mPendingInvalidate) {
        ((RecyclerView.ViewHolder)stringBuilder).itemView.invalidate();
        layoutParams.mPendingInvalidate = false;
      } 
    }
    
    public static int chooseSize(int param1Int1, int param1Int2, int param1Int3) {
      int i = View.MeasureSpec.getMode(param1Int1);
      param1Int1 = View.MeasureSpec.getSize(param1Int1);
      return (i != Integer.MIN_VALUE) ? ((i != 1073741824) ? Math.max(param1Int2, param1Int3) : param1Int1) : Math.min(param1Int1, Math.max(param1Int2, param1Int3));
    }
    
    private void detachViewInternal(int param1Int, @NonNull View param1View) {
      this.mChildHelper.detachViewFromParent(param1Int);
    }
    
    public static int getChildMeasureSpec(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean) {
      // Byte code:
      //   0: iconst_0
      //   1: istore #5
      //   3: iconst_0
      //   4: iload_0
      //   5: iload_2
      //   6: isub
      //   7: invokestatic max : (II)I
      //   10: istore #6
      //   12: iload #4
      //   14: ifeq -> 67
      //   17: iload_3
      //   18: iflt -> 29
      //   21: iload_3
      //   22: istore_2
      //   23: ldc 1073741824
      //   25: istore_0
      //   26: goto -> 125
      //   29: iload_3
      //   30: iconst_m1
      //   31: if_icmpne -> 120
      //   34: iload_1
      //   35: ldc -2147483648
      //   37: if_icmpeq -> 57
      //   40: iload_1
      //   41: ifeq -> 50
      //   44: iload_1
      //   45: ldc 1073741824
      //   47: if_icmpeq -> 57
      //   50: iconst_0
      //   51: istore_1
      //   52: iload_1
      //   53: istore_0
      //   54: goto -> 60
      //   57: iload #6
      //   59: istore_0
      //   60: iload_0
      //   61: istore_2
      //   62: iload_1
      //   63: istore_0
      //   64: goto -> 125
      //   67: iload_3
      //   68: iflt -> 74
      //   71: goto -> 21
      //   74: iload_3
      //   75: iconst_m1
      //   76: if_icmpne -> 87
      //   79: iload_1
      //   80: istore_0
      //   81: iload #6
      //   83: istore_2
      //   84: goto -> 125
      //   87: iload_3
      //   88: bipush #-2
      //   90: if_icmpne -> 120
      //   93: iload_1
      //   94: ldc -2147483648
      //   96: if_icmpeq -> 111
      //   99: iload #6
      //   101: istore_2
      //   102: iload #5
      //   104: istore_0
      //   105: iload_1
      //   106: ldc 1073741824
      //   108: if_icmpne -> 125
      //   111: ldc -2147483648
      //   113: istore_0
      //   114: iload #6
      //   116: istore_2
      //   117: goto -> 125
      //   120: iconst_0
      //   121: istore_2
      //   122: iload #5
      //   124: istore_0
      //   125: iload_2
      //   126: iload_0
      //   127: invokestatic makeMeasureSpec : (II)I
      //   130: ireturn
    }
    
    @Deprecated
    public static int getChildMeasureSpec(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) {
      // Byte code:
      //   0: iconst_0
      //   1: istore #4
      //   3: iconst_0
      //   4: iload_0
      //   5: iload_1
      //   6: isub
      //   7: invokestatic max : (II)I
      //   10: istore_0
      //   11: iload_3
      //   12: ifeq -> 35
      //   15: iload_2
      //   16: iflt -> 27
      //   19: iload_2
      //   20: istore_0
      //   21: ldc 1073741824
      //   23: istore_1
      //   24: goto -> 59
      //   27: iconst_0
      //   28: istore_0
      //   29: iload #4
      //   31: istore_1
      //   32: goto -> 59
      //   35: iload_2
      //   36: iflt -> 42
      //   39: goto -> 19
      //   42: iload_2
      //   43: iconst_m1
      //   44: if_icmpne -> 50
      //   47: goto -> 21
      //   50: iload_2
      //   51: bipush #-2
      //   53: if_icmpne -> 27
      //   56: ldc -2147483648
      //   58: istore_1
      //   59: iload_0
      //   60: iload_1
      //   61: invokestatic makeMeasureSpec : (II)I
      //   64: ireturn
    }
    
    private int[] getChildRectangleOnScreenScrollAmount(RecyclerView param1RecyclerView, View param1View, Rect param1Rect, boolean param1Boolean) {
      int i = getPaddingLeft();
      int j = getPaddingTop();
      int k = getWidth();
      int m = getPaddingRight();
      int n = getHeight();
      int i1 = getPaddingBottom();
      int i2 = param1View.getLeft() + param1Rect.left - param1View.getScrollX();
      int i3 = param1View.getTop() + param1Rect.top - param1View.getScrollY();
      int i4 = param1Rect.width();
      int i5 = param1Rect.height();
      int i6 = i2 - i;
      i = Math.min(0, i6);
      int i7 = i3 - j;
      j = Math.min(0, i7);
      i4 = i4 + i2 - k - m;
      k = Math.max(0, i4);
      i1 = Math.max(0, i5 + i3 - n - i1);
      if (getLayoutDirection() == 1) {
        if (k != 0) {
          i = k;
        } else {
          i = Math.max(i, i4);
        } 
      } else if (i == 0) {
        i = Math.min(i6, k);
      } 
      if (j == 0)
        j = Math.min(i7, i1); 
      return new int[] { i, j };
    }
    
    public static Properties getProperties(@NonNull Context param1Context, @Nullable AttributeSet param1AttributeSet, int param1Int1, int param1Int2) {
      Properties properties = new Properties();
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.RecyclerView, param1Int1, param1Int2);
      properties.orientation = typedArray.getInt(R.styleable.RecyclerView_android_orientation, 1);
      properties.spanCount = typedArray.getInt(R.styleable.RecyclerView_spanCount, 1);
      properties.reverseLayout = typedArray.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
      properties.stackFromEnd = typedArray.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
      typedArray.recycle();
      return properties;
    }
    
    private boolean isFocusedChildVisibleAfterScrolling(RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {
      View view = param1RecyclerView.getFocusedChild();
      if (view == null)
        return false; 
      int i = getPaddingLeft();
      int j = getPaddingTop();
      int k = getWidth();
      int m = getPaddingRight();
      int n = getHeight();
      int i1 = getPaddingBottom();
      Rect rect = this.mRecyclerView.mTempRect;
      getDecoratedBoundsWithMargins(view, rect);
      return !(rect.left - param1Int1 >= k - m || rect.right - param1Int1 <= i || rect.top - param1Int2 >= n - i1 || rect.bottom - param1Int2 <= j);
    }
    
    private static boolean isMeasurementUpToDate(int param1Int1, int param1Int2, int param1Int3) {
      int i = View.MeasureSpec.getMode(param1Int2);
      param1Int2 = View.MeasureSpec.getSize(param1Int2);
      boolean bool1 = false;
      boolean bool2 = false;
      if (param1Int3 > 0 && param1Int1 != param1Int3)
        return false; 
      if (i != Integer.MIN_VALUE) {
        if (i != 0) {
          if (i != 1073741824)
            return false; 
          if (param1Int2 == param1Int1)
            bool2 = true; 
          return bool2;
        } 
        return true;
      } 
      bool2 = bool1;
      if (param1Int2 >= param1Int1)
        bool2 = true; 
      return bool2;
    }
    
    private void scrapOrRecycleView(RecyclerView.Recycler param1Recycler, int param1Int, View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.shouldIgnore())
        return; 
      if (viewHolder.isInvalid() && !viewHolder.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
        removeViewAt(param1Int);
        param1Recycler.recycleViewHolderInternal(viewHolder);
      } else {
        detachViewAt(param1Int);
        param1Recycler.scrapView(param1View);
        this.mRecyclerView.mViewInfoStore.onViewDetached(viewHolder);
      } 
    }
    
    public void addDisappearingView(View param1View) {
      addDisappearingView(param1View, -1);
    }
    
    public void addDisappearingView(View param1View, int param1Int) {
      addViewInt(param1View, param1Int, true);
    }
    
    public void addView(View param1View) {
      addView(param1View, -1);
    }
    
    public void addView(View param1View, int param1Int) {
      addViewInt(param1View, param1Int, false);
    }
    
    public void assertInLayoutOrScroll(String param1String) {
      if (this.mRecyclerView != null)
        this.mRecyclerView.assertInLayoutOrScroll(param1String); 
    }
    
    public void assertNotInLayoutOrScroll(String param1String) {
      if (this.mRecyclerView != null)
        this.mRecyclerView.assertNotInLayoutOrScroll(param1String); 
    }
    
    public void attachView(@NonNull View param1View) {
      attachView(param1View, -1);
    }
    
    public void attachView(@NonNull View param1View, int param1Int) {
      attachView(param1View, param1Int, (RecyclerView.LayoutParams)param1View.getLayoutParams());
    }
    
    public void attachView(@NonNull View param1View, int param1Int, RecyclerView.LayoutParams param1LayoutParams) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.isRemoved()) {
        this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewHolder);
      } else {
        this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewHolder);
      } 
      this.mChildHelper.attachViewToParent(param1View, param1Int, (ViewGroup.LayoutParams)param1LayoutParams, viewHolder.isRemoved());
    }
    
    public void calculateItemDecorationsForChild(@NonNull View param1View, @NonNull Rect param1Rect) {
      if (this.mRecyclerView == null) {
        param1Rect.set(0, 0, 0, 0);
        return;
      } 
      param1Rect.set(this.mRecyclerView.getItemDecorInsetsForChild(param1View));
    }
    
    public boolean canScrollHorizontally() {
      return false;
    }
    
    public boolean canScrollVertically() {
      return false;
    }
    
    public boolean checkLayoutParams(RecyclerView.LayoutParams param1LayoutParams) {
      boolean bool;
      if (param1LayoutParams != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void collectAdjacentPrefetchPositions(int param1Int1, int param1Int2, RecyclerView.State param1State, LayoutPrefetchRegistry param1LayoutPrefetchRegistry) {}
    
    public void collectInitialPrefetchPositions(int param1Int, LayoutPrefetchRegistry param1LayoutPrefetchRegistry) {}
    
    public int computeHorizontalScrollExtent(@NonNull RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeHorizontalScrollOffset(@NonNull RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeHorizontalScrollRange(@NonNull RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeVerticalScrollExtent(@NonNull RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeVerticalScrollOffset(@NonNull RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeVerticalScrollRange(@NonNull RecyclerView.State param1State) {
      return 0;
    }
    
    public void detachAndScrapAttachedViews(@NonNull RecyclerView.Recycler param1Recycler) {
      for (int i = getChildCount() - 1; i >= 0; i--)
        scrapOrRecycleView(param1Recycler, i, getChildAt(i)); 
    }
    
    public void detachAndScrapView(@NonNull View param1View, @NonNull RecyclerView.Recycler param1Recycler) {
      scrapOrRecycleView(param1Recycler, this.mChildHelper.indexOfChild(param1View), param1View);
    }
    
    public void detachAndScrapViewAt(int param1Int, @NonNull RecyclerView.Recycler param1Recycler) {
      scrapOrRecycleView(param1Recycler, param1Int, getChildAt(param1Int));
    }
    
    public void detachView(@NonNull View param1View) {
      int i = this.mChildHelper.indexOfChild(param1View);
      if (i >= 0)
        detachViewInternal(i, param1View); 
    }
    
    public void detachViewAt(int param1Int) {
      detachViewInternal(param1Int, getChildAt(param1Int));
    }
    
    void dispatchAttachedToWindow(RecyclerView param1RecyclerView) {
      this.mIsAttachedToWindow = true;
      onAttachedToWindow(param1RecyclerView);
    }
    
    void dispatchDetachedFromWindow(RecyclerView param1RecyclerView, RecyclerView.Recycler param1Recycler) {
      this.mIsAttachedToWindow = false;
      onDetachedFromWindow(param1RecyclerView, param1Recycler);
    }
    
    public void endAnimation(View param1View) {
      if (this.mRecyclerView.mItemAnimator != null)
        this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(param1View)); 
    }
    
    @Nullable
    public View findContainingItemView(@NonNull View param1View) {
      if (this.mRecyclerView == null)
        return null; 
      param1View = this.mRecyclerView.findContainingItemView(param1View);
      return (param1View == null) ? null : (this.mChildHelper.isHidden(param1View) ? null : param1View);
    }
    
    @Nullable
    public View findViewByPosition(int param1Int) {
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = getChildAt(b);
        RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
        if (viewHolder != null && viewHolder.getLayoutPosition() == param1Int && !viewHolder.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !viewHolder.isRemoved()))
          return view; 
      } 
      return null;
    }
    
    public abstract RecyclerView.LayoutParams generateDefaultLayoutParams();
    
    public RecyclerView.LayoutParams generateLayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      return new RecyclerView.LayoutParams(param1Context, param1AttributeSet);
    }
    
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      return (param1LayoutParams instanceof RecyclerView.LayoutParams) ? new RecyclerView.LayoutParams((RecyclerView.LayoutParams)param1LayoutParams) : ((param1LayoutParams instanceof ViewGroup.MarginLayoutParams) ? new RecyclerView.LayoutParams((ViewGroup.MarginLayoutParams)param1LayoutParams) : new RecyclerView.LayoutParams(param1LayoutParams));
    }
    
    public int getBaseline() {
      return -1;
    }
    
    public int getBottomDecorationHeight(@NonNull View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.bottom;
    }
    
    @Nullable
    public View getChildAt(int param1Int) {
      View view;
      if (this.mChildHelper != null) {
        view = this.mChildHelper.getChildAt(param1Int);
      } else {
        view = null;
      } 
      return view;
    }
    
    public int getChildCount() {
      boolean bool;
      if (this.mChildHelper != null) {
        bool = this.mChildHelper.getChildCount();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getClipToPadding() {
      boolean bool;
      if (this.mRecyclerView != null && this.mRecyclerView.mClipToPadding) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getColumnCountForAccessibility(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State) {
      RecyclerView recyclerView = this.mRecyclerView;
      int i = 1;
      if (recyclerView == null || this.mRecyclerView.mAdapter == null)
        return 1; 
      if (canScrollHorizontally())
        i = this.mRecyclerView.mAdapter.getItemCount(); 
      return i;
    }
    
    public int getDecoratedBottom(@NonNull View param1View) {
      return param1View.getBottom() + getBottomDecorationHeight(param1View);
    }
    
    public void getDecoratedBoundsWithMargins(@NonNull View param1View, @NonNull Rect param1Rect) {
      RecyclerView.getDecoratedBoundsWithMarginsInt(param1View, param1Rect);
    }
    
    public int getDecoratedLeft(@NonNull View param1View) {
      return param1View.getLeft() - getLeftDecorationWidth(param1View);
    }
    
    public int getDecoratedMeasuredHeight(@NonNull View param1View) {
      Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
      return param1View.getMeasuredHeight() + rect.top + rect.bottom;
    }
    
    public int getDecoratedMeasuredWidth(@NonNull View param1View) {
      Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
      return param1View.getMeasuredWidth() + rect.left + rect.right;
    }
    
    public int getDecoratedRight(@NonNull View param1View) {
      return param1View.getRight() + getRightDecorationWidth(param1View);
    }
    
    public int getDecoratedTop(@NonNull View param1View) {
      return param1View.getTop() - getTopDecorationHeight(param1View);
    }
    
    @Nullable
    public View getFocusedChild() {
      if (this.mRecyclerView == null)
        return null; 
      View view = this.mRecyclerView.getFocusedChild();
      return (view == null || this.mChildHelper.isHidden(view)) ? null : view;
    }
    
    @Px
    public int getHeight() {
      return this.mHeight;
    }
    
    public int getHeightMode() {
      return this.mHeightMode;
    }
    
    public int getItemCount() {
      RecyclerView.Adapter adapter;
      boolean bool;
      if (this.mRecyclerView != null) {
        adapter = this.mRecyclerView.getAdapter();
      } else {
        adapter = null;
      } 
      if (adapter != null) {
        bool = adapter.getItemCount();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getItemViewType(@NonNull View param1View) {
      return RecyclerView.getChildViewHolderInt(param1View).getItemViewType();
    }
    
    public int getLayoutDirection() {
      return ViewCompat.getLayoutDirection((View)this.mRecyclerView);
    }
    
    public int getLeftDecorationWidth(@NonNull View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.left;
    }
    
    @Px
    public int getMinimumHeight() {
      return ViewCompat.getMinimumHeight((View)this.mRecyclerView);
    }
    
    @Px
    public int getMinimumWidth() {
      return ViewCompat.getMinimumWidth((View)this.mRecyclerView);
    }
    
    @Px
    public int getPaddingBottom() {
      boolean bool;
      if (this.mRecyclerView != null) {
        bool = this.mRecyclerView.getPaddingBottom();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Px
    public int getPaddingEnd() {
      boolean bool;
      if (this.mRecyclerView != null) {
        bool = ViewCompat.getPaddingEnd((View)this.mRecyclerView);
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Px
    public int getPaddingLeft() {
      boolean bool;
      if (this.mRecyclerView != null) {
        bool = this.mRecyclerView.getPaddingLeft();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Px
    public int getPaddingRight() {
      boolean bool;
      if (this.mRecyclerView != null) {
        bool = this.mRecyclerView.getPaddingRight();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Px
    public int getPaddingStart() {
      boolean bool;
      if (this.mRecyclerView != null) {
        bool = ViewCompat.getPaddingStart((View)this.mRecyclerView);
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Px
    public int getPaddingTop() {
      boolean bool;
      if (this.mRecyclerView != null) {
        bool = this.mRecyclerView.getPaddingTop();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getPosition(@NonNull View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).getViewLayoutPosition();
    }
    
    public int getRightDecorationWidth(@NonNull View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.right;
    }
    
    public int getRowCountForAccessibility(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State) {
      RecyclerView recyclerView = this.mRecyclerView;
      int i = 1;
      if (recyclerView == null || this.mRecyclerView.mAdapter == null)
        return 1; 
      if (canScrollVertically())
        i = this.mRecyclerView.mAdapter.getItemCount(); 
      return i;
    }
    
    public int getSelectionModeForAccessibility(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State) {
      return 0;
    }
    
    public int getTopDecorationHeight(@NonNull View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.top;
    }
    
    public void getTransformedBoundingBox(@NonNull View param1View, boolean param1Boolean, @NonNull Rect param1Rect) {
      if (param1Boolean) {
        Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
        param1Rect.set(-rect.left, -rect.top, param1View.getWidth() + rect.right, param1View.getHeight() + rect.bottom);
      } else {
        param1Rect.set(0, 0, param1View.getWidth(), param1View.getHeight());
      } 
      if (this.mRecyclerView != null) {
        Matrix matrix = param1View.getMatrix();
        if (matrix != null && !matrix.isIdentity()) {
          RectF rectF = this.mRecyclerView.mTempRectF;
          rectF.set(param1Rect);
          matrix.mapRect(rectF);
          param1Rect.set((int)Math.floor(rectF.left), (int)Math.floor(rectF.top), (int)Math.ceil(rectF.right), (int)Math.ceil(rectF.bottom));
        } 
      } 
      param1Rect.offset(param1View.getLeft(), param1View.getTop());
    }
    
    @Px
    public int getWidth() {
      return this.mWidth;
    }
    
    public int getWidthMode() {
      return this.mWidthMode;
    }
    
    boolean hasFlexibleChildInBothOrientations() {
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        ViewGroup.LayoutParams layoutParams = getChildAt(b).getLayoutParams();
        if (layoutParams.width < 0 && layoutParams.height < 0)
          return true; 
      } 
      return false;
    }
    
    public boolean hasFocus() {
      boolean bool;
      if (this.mRecyclerView != null && this.mRecyclerView.hasFocus()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void ignoreView(@NonNull View param1View) {
      StringBuilder stringBuilder;
      if (param1View.getParent() != this.mRecyclerView || this.mRecyclerView.indexOfChild(param1View) == -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("View should be fully attached to be ignored");
        stringBuilder.append(this.mRecyclerView.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt((View)stringBuilder);
      viewHolder.addFlags(128);
      this.mRecyclerView.mViewInfoStore.removeViewHolder(viewHolder);
    }
    
    public boolean isAttachedToWindow() {
      return this.mIsAttachedToWindow;
    }
    
    public boolean isAutoMeasureEnabled() {
      return this.mAutoMeasure;
    }
    
    public boolean isFocused() {
      boolean bool;
      if (this.mRecyclerView != null && this.mRecyclerView.isFocused()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public final boolean isItemPrefetchEnabled() {
      return this.mItemPrefetchEnabled;
    }
    
    public boolean isLayoutHierarchical(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State) {
      return false;
    }
    
    public boolean isMeasurementCacheEnabled() {
      return this.mMeasurementCacheEnabled;
    }
    
    public boolean isSmoothScrolling() {
      boolean bool;
      if (this.mSmoothScroller != null && this.mSmoothScroller.isRunning()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isViewPartiallyVisible(@NonNull View param1View, boolean param1Boolean1, boolean param1Boolean2) {
      if (this.mHorizontalBoundCheck.isViewWithinBoundFlags(param1View, 24579) && this.mVerticalBoundCheck.isViewWithinBoundFlags(param1View, 24579)) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      return param1Boolean1 ? param1Boolean2 : (param1Boolean2 ^ true);
    }
    
    public void layoutDecorated(@NonNull View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
      param1View.layout(param1Int1 + rect.left, param1Int2 + rect.top, param1Int3 - rect.right, param1Int4 - rect.bottom);
    }
    
    public void layoutDecoratedWithMargins(@NonNull View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      Rect rect = layoutParams.mDecorInsets;
      param1View.layout(param1Int1 + rect.left + layoutParams.leftMargin, param1Int2 + rect.top + layoutParams.topMargin, param1Int3 - rect.right - layoutParams.rightMargin, param1Int4 - rect.bottom - layoutParams.bottomMargin);
    }
    
    public void measureChild(@NonNull View param1View, int param1Int1, int param1Int2) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      Rect rect = this.mRecyclerView.getItemDecorInsetsForChild(param1View);
      int i = rect.left;
      int j = rect.right;
      int k = rect.top;
      int m = rect.bottom;
      param1Int1 = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + param1Int1 + i + j, layoutParams.width, canScrollHorizontally());
      param1Int2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + param1Int2 + k + m, layoutParams.height, canScrollVertically());
      if (shouldMeasureChild(param1View, param1Int1, param1Int2, layoutParams))
        param1View.measure(param1Int1, param1Int2); 
    }
    
    public void measureChildWithMargins(@NonNull View param1View, int param1Int1, int param1Int2) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      Rect rect = this.mRecyclerView.getItemDecorInsetsForChild(param1View);
      int i = rect.left;
      int j = rect.right;
      int k = rect.top;
      int m = rect.bottom;
      param1Int1 = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + param1Int1 + i + j, layoutParams.width, canScrollHorizontally());
      param1Int2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + param1Int2 + k + m, layoutParams.height, canScrollVertically());
      if (shouldMeasureChild(param1View, param1Int1, param1Int2, layoutParams))
        param1View.measure(param1Int1, param1Int2); 
    }
    
    public void moveView(int param1Int1, int param1Int2) {
      StringBuilder stringBuilder;
      View view = getChildAt(param1Int1);
      if (view == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot move a child from non-existing index:");
        stringBuilder.append(param1Int1);
        stringBuilder.append(this.mRecyclerView.toString());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      detachViewAt(param1Int1);
      attachView((View)stringBuilder, param1Int2);
    }
    
    public void offsetChildrenHorizontal(@Px int param1Int) {
      if (this.mRecyclerView != null)
        this.mRecyclerView.offsetChildrenHorizontal(param1Int); 
    }
    
    public void offsetChildrenVertical(@Px int param1Int) {
      if (this.mRecyclerView != null)
        this.mRecyclerView.offsetChildrenVertical(param1Int); 
    }
    
    public void onAdapterChanged(@Nullable RecyclerView.Adapter param1Adapter1, @Nullable RecyclerView.Adapter param1Adapter2) {}
    
    public boolean onAddFocusables(@NonNull RecyclerView param1RecyclerView, @NonNull ArrayList<View> param1ArrayList, int param1Int1, int param1Int2) {
      return false;
    }
    
    @CallSuper
    public void onAttachedToWindow(RecyclerView param1RecyclerView) {}
    
    @Deprecated
    public void onDetachedFromWindow(RecyclerView param1RecyclerView) {}
    
    @CallSuper
    public void onDetachedFromWindow(RecyclerView param1RecyclerView, RecyclerView.Recycler param1Recycler) {
      onDetachedFromWindow(param1RecyclerView);
    }
    
    @Nullable
    public View onFocusSearchFailed(@NonNull View param1View, int param1Int, @NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State) {
      return null;
    }
    
    public void onInitializeAccessibilityEvent(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State, @NonNull AccessibilityEvent param1AccessibilityEvent) {
      if (this.mRecyclerView == null || param1AccessibilityEvent == null)
        return; 
      RecyclerView recyclerView = this.mRecyclerView;
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (!recyclerView.canScrollVertically(1)) {
        bool2 = bool1;
        if (!this.mRecyclerView.canScrollVertically(-1)) {
          bool2 = bool1;
          if (!this.mRecyclerView.canScrollHorizontally(-1))
            if (this.mRecyclerView.canScrollHorizontally(1)) {
              bool2 = bool1;
            } else {
              bool2 = false;
            }  
        } 
      } 
      param1AccessibilityEvent.setScrollable(bool2);
      if (this.mRecyclerView.mAdapter != null)
        param1AccessibilityEvent.setItemCount(this.mRecyclerView.mAdapter.getItemCount()); 
    }
    
    public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent param1AccessibilityEvent) {
      onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, param1AccessibilityEvent);
    }
    
    void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, param1AccessibilityNodeInfoCompat);
    }
    
    public void onInitializeAccessibilityNodeInfo(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State, @NonNull AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
        param1AccessibilityNodeInfoCompat.addAction(8192);
        param1AccessibilityNodeInfoCompat.setScrollable(true);
      } 
      if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
        param1AccessibilityNodeInfoCompat.addAction(4096);
        param1AccessibilityNodeInfoCompat.setScrollable(true);
      } 
      param1AccessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(param1Recycler, param1State), getColumnCountForAccessibility(param1Recycler, param1State), isLayoutHierarchical(param1Recycler, param1State), getSelectionModeForAccessibility(param1Recycler, param1State)));
    }
    
    public void onInitializeAccessibilityNodeInfoForItem(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State, @NonNull View param1View, @NonNull AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      boolean bool1;
      boolean bool = canScrollVertically();
      int i = 0;
      if (bool) {
        bool1 = getPosition(param1View);
      } else {
        bool1 = false;
      } 
      if (canScrollHorizontally())
        i = getPosition(param1View); 
      param1AccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(bool1, 1, i, 1, false, false));
    }
    
    void onInitializeAccessibilityNodeInfoForItem(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder != null && !viewHolder.isRemoved() && !this.mChildHelper.isHidden(viewHolder.itemView))
        onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, param1View, param1AccessibilityNodeInfoCompat); 
    }
    
    @Nullable
    public View onInterceptFocusSearch(@NonNull View param1View, int param1Int) {
      return null;
    }
    
    public void onItemsAdded(@NonNull RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
    
    public void onItemsChanged(@NonNull RecyclerView param1RecyclerView) {}
    
    public void onItemsMoved(@NonNull RecyclerView param1RecyclerView, int param1Int1, int param1Int2, int param1Int3) {}
    
    public void onItemsRemoved(@NonNull RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
    
    public void onItemsUpdated(@NonNull RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
    
    public void onItemsUpdated(@NonNull RecyclerView param1RecyclerView, int param1Int1, int param1Int2, @Nullable Object param1Object) {
      onItemsUpdated(param1RecyclerView, param1Int1, param1Int2);
    }
    
    public void onLayoutChildren(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }
    
    public void onLayoutCompleted(RecyclerView.State param1State) {}
    
    public void onMeasure(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State, int param1Int1, int param1Int2) {
      this.mRecyclerView.defaultOnMeasure(param1Int1, param1Int2);
    }
    
    public boolean onRequestChildFocus(@NonNull RecyclerView param1RecyclerView, @NonNull RecyclerView.State param1State, @NonNull View param1View1, @Nullable View param1View2) {
      return onRequestChildFocus(param1RecyclerView, param1View1, param1View2);
    }
    
    @Deprecated
    public boolean onRequestChildFocus(@NonNull RecyclerView param1RecyclerView, @NonNull View param1View1, @Nullable View param1View2) {
      return (isSmoothScrolling() || param1RecyclerView.isComputingLayout());
    }
    
    public void onRestoreInstanceState(Parcelable param1Parcelable) {}
    
    @Nullable
    public Parcelable onSaveInstanceState() {
      return null;
    }
    
    public void onScrollStateChanged(int param1Int) {}
    
    void onSmoothScrollerStopped(RecyclerView.SmoothScroller param1SmoothScroller) {
      if (this.mSmoothScroller == param1SmoothScroller)
        this.mSmoothScroller = null; 
    }
    
    boolean performAccessibilityAction(int param1Int, @Nullable Bundle param1Bundle) {
      return performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, param1Int, param1Bundle);
    }
    
    public boolean performAccessibilityAction(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State, int param1Int, @Nullable Bundle param1Bundle) {
      // Byte code:
      //   0: aload_0
      //   1: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   4: ifnonnull -> 9
      //   7: iconst_0
      //   8: ireturn
      //   9: iload_3
      //   10: sipush #4096
      //   13: if_icmpeq -> 97
      //   16: iload_3
      //   17: sipush #8192
      //   20: if_icmpeq -> 31
      //   23: iconst_0
      //   24: istore_3
      //   25: iload_3
      //   26: istore #5
      //   28: goto -> 167
      //   31: aload_0
      //   32: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   35: iconst_m1
      //   36: invokevirtual canScrollVertically : (I)Z
      //   39: ifeq -> 61
      //   42: aload_0
      //   43: invokevirtual getHeight : ()I
      //   46: aload_0
      //   47: invokevirtual getPaddingTop : ()I
      //   50: isub
      //   51: aload_0
      //   52: invokevirtual getPaddingBottom : ()I
      //   55: isub
      //   56: ineg
      //   57: istore_3
      //   58: goto -> 63
      //   61: iconst_0
      //   62: istore_3
      //   63: iload_3
      //   64: istore #6
      //   66: aload_0
      //   67: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   70: iconst_m1
      //   71: invokevirtual canScrollHorizontally : (I)Z
      //   74: ifeq -> 161
      //   77: aload_0
      //   78: invokevirtual getWidth : ()I
      //   81: aload_0
      //   82: invokevirtual getPaddingLeft : ()I
      //   85: isub
      //   86: aload_0
      //   87: invokevirtual getPaddingRight : ()I
      //   90: isub
      //   91: ineg
      //   92: istore #5
      //   94: goto -> 167
      //   97: aload_0
      //   98: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   101: iconst_1
      //   102: invokevirtual canScrollVertically : (I)Z
      //   105: ifeq -> 126
      //   108: aload_0
      //   109: invokevirtual getHeight : ()I
      //   112: aload_0
      //   113: invokevirtual getPaddingTop : ()I
      //   116: isub
      //   117: aload_0
      //   118: invokevirtual getPaddingBottom : ()I
      //   121: isub
      //   122: istore_3
      //   123: goto -> 128
      //   126: iconst_0
      //   127: istore_3
      //   128: iload_3
      //   129: istore #6
      //   131: aload_0
      //   132: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   135: iconst_1
      //   136: invokevirtual canScrollHorizontally : (I)Z
      //   139: ifeq -> 161
      //   142: aload_0
      //   143: invokevirtual getWidth : ()I
      //   146: aload_0
      //   147: invokevirtual getPaddingLeft : ()I
      //   150: isub
      //   151: aload_0
      //   152: invokevirtual getPaddingRight : ()I
      //   155: isub
      //   156: istore #5
      //   158: goto -> 167
      //   161: iconst_0
      //   162: istore #5
      //   164: iload #6
      //   166: istore_3
      //   167: iload_3
      //   168: ifne -> 178
      //   171: iload #5
      //   173: ifne -> 178
      //   176: iconst_0
      //   177: ireturn
      //   178: aload_0
      //   179: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   182: iload #5
      //   184: iload_3
      //   185: invokevirtual smoothScrollBy : (II)V
      //   188: iconst_1
      //   189: ireturn
    }
    
    public boolean performAccessibilityActionForItem(@NonNull RecyclerView.Recycler param1Recycler, @NonNull RecyclerView.State param1State, @NonNull View param1View, int param1Int, @Nullable Bundle param1Bundle) {
      return false;
    }
    
    boolean performAccessibilityActionForItem(@NonNull View param1View, int param1Int, @Nullable Bundle param1Bundle) {
      return performAccessibilityActionForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, param1View, param1Int, param1Bundle);
    }
    
    public void postOnAnimation(Runnable param1Runnable) {
      if (this.mRecyclerView != null)
        ViewCompat.postOnAnimation((View)this.mRecyclerView, param1Runnable); 
    }
    
    public void removeAllViews() {
      for (int i = getChildCount() - 1; i >= 0; i--)
        this.mChildHelper.removeViewAt(i); 
    }
    
    public void removeAndRecycleAllViews(@NonNull RecyclerView.Recycler param1Recycler) {
      for (int i = getChildCount() - 1; i >= 0; i--) {
        if (!RecyclerView.getChildViewHolderInt(getChildAt(i)).shouldIgnore())
          removeAndRecycleViewAt(i, param1Recycler); 
      } 
    }
    
    void removeAndRecycleScrapInt(RecyclerView.Recycler param1Recycler) {
      int i = param1Recycler.getScrapCount();
      for (int j = i - 1; j >= 0; j--) {
        View view = param1Recycler.getScrapViewAt(j);
        RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
        if (!viewHolder.shouldIgnore()) {
          viewHolder.setIsRecyclable(false);
          if (viewHolder.isTmpDetached())
            this.mRecyclerView.removeDetachedView(view, false); 
          if (this.mRecyclerView.mItemAnimator != null)
            this.mRecyclerView.mItemAnimator.endAnimation(viewHolder); 
          viewHolder.setIsRecyclable(true);
          param1Recycler.quickRecycleScrapView(view);
        } 
      } 
      param1Recycler.clearScrap();
      if (i > 0)
        this.mRecyclerView.invalidate(); 
    }
    
    public void removeAndRecycleView(@NonNull View param1View, @NonNull RecyclerView.Recycler param1Recycler) {
      removeView(param1View);
      param1Recycler.recycleView(param1View);
    }
    
    public void removeAndRecycleViewAt(int param1Int, @NonNull RecyclerView.Recycler param1Recycler) {
      View view = getChildAt(param1Int);
      removeViewAt(param1Int);
      param1Recycler.recycleView(view);
    }
    
    public boolean removeCallbacks(Runnable param1Runnable) {
      return (this.mRecyclerView != null) ? this.mRecyclerView.removeCallbacks(param1Runnable) : false;
    }
    
    public void removeDetachedView(@NonNull View param1View) {
      this.mRecyclerView.removeDetachedView(param1View, false);
    }
    
    public void removeView(View param1View) {
      this.mChildHelper.removeView(param1View);
    }
    
    public void removeViewAt(int param1Int) {
      if (getChildAt(param1Int) != null)
        this.mChildHelper.removeViewAt(param1Int); 
    }
    
    public boolean requestChildRectangleOnScreen(@NonNull RecyclerView param1RecyclerView, @NonNull View param1View, @NonNull Rect param1Rect, boolean param1Boolean) {
      return requestChildRectangleOnScreen(param1RecyclerView, param1View, param1Rect, param1Boolean, false);
    }
    
    public boolean requestChildRectangleOnScreen(@NonNull RecyclerView param1RecyclerView, @NonNull View param1View, @NonNull Rect param1Rect, boolean param1Boolean1, boolean param1Boolean2) {
      int[] arrayOfInt = getChildRectangleOnScreenScrollAmount(param1RecyclerView, param1View, param1Rect, param1Boolean1);
      int i = arrayOfInt[0];
      int j = arrayOfInt[1];
      if ((!param1Boolean2 || isFocusedChildVisibleAfterScrolling(param1RecyclerView, i, j)) && (i != 0 || j != 0)) {
        if (param1Boolean1) {
          param1RecyclerView.scrollBy(i, j);
        } else {
          param1RecyclerView.smoothScrollBy(i, j);
        } 
        return true;
      } 
      return false;
    }
    
    public void requestLayout() {
      if (this.mRecyclerView != null)
        this.mRecyclerView.requestLayout(); 
    }
    
    public void requestSimpleAnimationsInNextLayout() {
      this.mRequestedSimpleAnimations = true;
    }
    
    public int scrollHorizontallyBy(int param1Int, RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      return 0;
    }
    
    public void scrollToPosition(int param1Int) {}
    
    public int scrollVerticallyBy(int param1Int, RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      return 0;
    }
    
    @Deprecated
    public void setAutoMeasureEnabled(boolean param1Boolean) {
      this.mAutoMeasure = param1Boolean;
    }
    
    void setExactMeasureSpecsFrom(RecyclerView param1RecyclerView) {
      setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(param1RecyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(param1RecyclerView.getHeight(), 1073741824));
    }
    
    public final void setItemPrefetchEnabled(boolean param1Boolean) {
      if (param1Boolean != this.mItemPrefetchEnabled) {
        this.mItemPrefetchEnabled = param1Boolean;
        this.mPrefetchMaxCountObserved = 0;
        if (this.mRecyclerView != null)
          this.mRecyclerView.mRecycler.updateViewCacheSize(); 
      } 
    }
    
    void setMeasureSpecs(int param1Int1, int param1Int2) {
      this.mWidth = View.MeasureSpec.getSize(param1Int1);
      this.mWidthMode = View.MeasureSpec.getMode(param1Int1);
      if (this.mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
        this.mWidth = 0; 
      this.mHeight = View.MeasureSpec.getSize(param1Int2);
      this.mHeightMode = View.MeasureSpec.getMode(param1Int2);
      if (this.mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
        this.mHeight = 0; 
    }
    
    public void setMeasuredDimension(int param1Int1, int param1Int2) {
      this.mRecyclerView.setMeasuredDimension(param1Int1, param1Int2);
    }
    
    public void setMeasuredDimension(Rect param1Rect, int param1Int1, int param1Int2) {
      int i = param1Rect.width();
      int j = getPaddingLeft();
      int k = getPaddingRight();
      int m = param1Rect.height();
      int n = getPaddingTop();
      int i1 = getPaddingBottom();
      setMeasuredDimension(chooseSize(param1Int1, i + j + k, getMinimumWidth()), chooseSize(param1Int2, m + n + i1, getMinimumHeight()));
    }
    
    void setMeasuredDimensionFromChildren(int param1Int1, int param1Int2) {
      int i = getChildCount();
      if (i == 0) {
        this.mRecyclerView.defaultOnMeasure(param1Int1, param1Int2);
        return;
      } 
      byte b = 0;
      int j = Integer.MAX_VALUE;
      int k = Integer.MIN_VALUE;
      int m = k;
      int n = Integer.MAX_VALUE;
      while (b < i) {
        View view = getChildAt(b);
        Rect rect = this.mRecyclerView.mTempRect;
        getDecoratedBoundsWithMargins(view, rect);
        int i1 = j;
        if (rect.left < j)
          i1 = rect.left; 
        int i2 = k;
        if (rect.right > k)
          i2 = rect.right; 
        k = n;
        if (rect.top < n)
          k = rect.top; 
        int i3 = m;
        if (rect.bottom > m)
          i3 = rect.bottom; 
        b++;
        n = k;
        j = i1;
        k = i2;
        m = i3;
      } 
      this.mRecyclerView.mTempRect.set(j, n, k, m);
      setMeasuredDimension(this.mRecyclerView.mTempRect, param1Int1, param1Int2);
    }
    
    public void setMeasurementCacheEnabled(boolean param1Boolean) {
      this.mMeasurementCacheEnabled = param1Boolean;
    }
    
    void setRecyclerView(RecyclerView param1RecyclerView) {
      if (param1RecyclerView == null) {
        this.mRecyclerView = null;
        this.mChildHelper = null;
        this.mWidth = 0;
        this.mHeight = 0;
      } else {
        this.mRecyclerView = param1RecyclerView;
        this.mChildHelper = param1RecyclerView.mChildHelper;
        this.mWidth = param1RecyclerView.getWidth();
        this.mHeight = param1RecyclerView.getHeight();
      } 
      this.mWidthMode = 1073741824;
      this.mHeightMode = 1073741824;
    }
    
    boolean shouldMeasureChild(View param1View, int param1Int1, int param1Int2, RecyclerView.LayoutParams param1LayoutParams) {
      return (param1View.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(param1View.getWidth(), param1Int1, param1LayoutParams.width) || !isMeasurementUpToDate(param1View.getHeight(), param1Int2, param1LayoutParams.height));
    }
    
    boolean shouldMeasureTwice() {
      return false;
    }
    
    boolean shouldReMeasureChild(View param1View, int param1Int1, int param1Int2, RecyclerView.LayoutParams param1LayoutParams) {
      return (!this.mMeasurementCacheEnabled || !isMeasurementUpToDate(param1View.getMeasuredWidth(), param1Int1, param1LayoutParams.width) || !isMeasurementUpToDate(param1View.getMeasuredHeight(), param1Int2, param1LayoutParams.height));
    }
    
    public void smoothScrollToPosition(RecyclerView param1RecyclerView, RecyclerView.State param1State, int param1Int) {
      Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
    }
    
    public void startSmoothScroll(RecyclerView.SmoothScroller param1SmoothScroller) {
      if (this.mSmoothScroller != null && param1SmoothScroller != this.mSmoothScroller && this.mSmoothScroller.isRunning())
        this.mSmoothScroller.stop(); 
      this.mSmoothScroller = param1SmoothScroller;
      this.mSmoothScroller.start(this.mRecyclerView, this);
    }
    
    public void stopIgnoringView(@NonNull View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      viewHolder.stopIgnoring();
      viewHolder.resetInternal();
      viewHolder.addFlags(4);
    }
    
    void stopSmoothScroller() {
      if (this.mSmoothScroller != null)
        this.mSmoothScroller.stop(); 
    }
    
    public boolean supportsPredictiveItemAnimations() {
      return false;
    }
    
    public static interface LayoutPrefetchRegistry {
      void addPosition(int param2Int1, int param2Int2);
    }
    
    public static class Properties {
      public int orientation;
      
      public boolean reverseLayout;
      
      public int spanCount;
      
      public boolean stackFromEnd;
    }
  }
  
  class null implements ViewBoundsCheck.Callback {
    public View getChildAt(int param1Int) {
      return this.this$0.getChildAt(param1Int);
    }
    
    public int getChildCount() {
      return this.this$0.getChildCount();
    }
    
    public int getChildEnd(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedRight(param1View) + layoutParams.rightMargin;
    }
    
    public int getChildStart(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedLeft(param1View) - layoutParams.leftMargin;
    }
    
    public View getParent() {
      return (View)this.this$0.mRecyclerView;
    }
    
    public int getParentEnd() {
      return this.this$0.getWidth() - this.this$0.getPaddingRight();
    }
    
    public int getParentStart() {
      return this.this$0.getPaddingLeft();
    }
  }
  
  class null implements ViewBoundsCheck.Callback {
    public View getChildAt(int param1Int) {
      return this.this$0.getChildAt(param1Int);
    }
    
    public int getChildCount() {
      return this.this$0.getChildCount();
    }
    
    public int getChildEnd(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedBottom(param1View) + layoutParams.bottomMargin;
    }
    
    public int getChildStart(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedTop(param1View) - layoutParams.topMargin;
    }
    
    public View getParent() {
      return (View)this.this$0.mRecyclerView;
    }
    
    public int getParentEnd() {
      return this.this$0.getHeight() - this.this$0.getPaddingBottom();
    }
    
    public int getParentStart() {
      return this.this$0.getPaddingTop();
    }
  }
  
  public static interface LayoutPrefetchRegistry {
    void addPosition(int param1Int1, int param1Int2);
  }
  
  public static class Properties {
    public int orientation;
    
    public boolean reverseLayout;
    
    public int spanCount;
    
    public boolean stackFromEnd;
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    final Rect mDecorInsets = new Rect();
    
    boolean mInsetsDirty = true;
    
    boolean mPendingInvalidate = false;
    
    RecyclerView.ViewHolder mViewHolder;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super((ViewGroup.LayoutParams)param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public int getViewAdapterPosition() {
      return this.mViewHolder.getAdapterPosition();
    }
    
    public int getViewLayoutPosition() {
      return this.mViewHolder.getLayoutPosition();
    }
    
    @Deprecated
    public int getViewPosition() {
      return this.mViewHolder.getPosition();
    }
    
    public boolean isItemChanged() {
      return this.mViewHolder.isUpdated();
    }
    
    public boolean isItemRemoved() {
      return this.mViewHolder.isRemoved();
    }
    
    public boolean isViewInvalid() {
      return this.mViewHolder.isInvalid();
    }
    
    public boolean viewNeedsUpdate() {
      return this.mViewHolder.needsUpdate();
    }
  }
  
  public static interface OnChildAttachStateChangeListener {
    void onChildViewAttachedToWindow(@NonNull View param1View);
    
    void onChildViewDetachedFromWindow(@NonNull View param1View);
  }
  
  public static abstract class OnFlingListener {
    public abstract boolean onFling(int param1Int1, int param1Int2);
  }
  
  public static interface OnItemTouchListener {
    boolean onInterceptTouchEvent(@NonNull RecyclerView param1RecyclerView, @NonNull MotionEvent param1MotionEvent);
    
    void onRequestDisallowInterceptTouchEvent(boolean param1Boolean);
    
    void onTouchEvent(@NonNull RecyclerView param1RecyclerView, @NonNull MotionEvent param1MotionEvent);
  }
  
  public static abstract class OnScrollListener {
    public void onScrollStateChanged(@NonNull RecyclerView param1RecyclerView, int param1Int) {}
    
    public void onScrolled(@NonNull RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Orientation {}
  
  public static class RecycledViewPool {
    private static final int DEFAULT_MAX_SCRAP = 5;
    
    private int mAttachCount = 0;
    
    SparseArray<ScrapData> mScrap = new SparseArray();
    
    private ScrapData getScrapDataForType(int param1Int) {
      ScrapData scrapData1 = (ScrapData)this.mScrap.get(param1Int);
      ScrapData scrapData2 = scrapData1;
      if (scrapData1 == null) {
        scrapData2 = new ScrapData();
        this.mScrap.put(param1Int, scrapData2);
      } 
      return scrapData2;
    }
    
    void attach() {
      this.mAttachCount++;
    }
    
    public void clear() {
      for (byte b = 0; b < this.mScrap.size(); b++)
        ((ScrapData)this.mScrap.valueAt(b)).mScrapHeap.clear(); 
    }
    
    void detach() {
      this.mAttachCount--;
    }
    
    void factorInBindTime(int param1Int, long param1Long) {
      ScrapData scrapData = getScrapDataForType(param1Int);
      scrapData.mBindRunningAverageNs = runningAverage(scrapData.mBindRunningAverageNs, param1Long);
    }
    
    void factorInCreateTime(int param1Int, long param1Long) {
      ScrapData scrapData = getScrapDataForType(param1Int);
      scrapData.mCreateRunningAverageNs = runningAverage(scrapData.mCreateRunningAverageNs, param1Long);
    }
    
    @Nullable
    public RecyclerView.ViewHolder getRecycledView(int param1Int) {
      ScrapData scrapData = (ScrapData)this.mScrap.get(param1Int);
      if (scrapData != null && !scrapData.mScrapHeap.isEmpty()) {
        ArrayList<RecyclerView.ViewHolder> arrayList = scrapData.mScrapHeap;
        return arrayList.remove(arrayList.size() - 1);
      } 
      return null;
    }
    
    public int getRecycledViewCount(int param1Int) {
      return (getScrapDataForType(param1Int)).mScrapHeap.size();
    }
    
    void onAdapterChanged(RecyclerView.Adapter param1Adapter1, RecyclerView.Adapter param1Adapter2, boolean param1Boolean) {
      if (param1Adapter1 != null)
        detach(); 
      if (!param1Boolean && this.mAttachCount == 0)
        clear(); 
      if (param1Adapter2 != null)
        attach(); 
    }
    
    public void putRecycledView(RecyclerView.ViewHolder param1ViewHolder) {
      int i = param1ViewHolder.getItemViewType();
      ArrayList<RecyclerView.ViewHolder> arrayList = (getScrapDataForType(i)).mScrapHeap;
      if (((ScrapData)this.mScrap.get(i)).mMaxScrap <= arrayList.size())
        return; 
      param1ViewHolder.resetInternal();
      arrayList.add(param1ViewHolder);
    }
    
    long runningAverage(long param1Long1, long param1Long2) {
      return (param1Long1 == 0L) ? param1Long2 : (param1Long1 / 4L * 3L + param1Long2 / 4L);
    }
    
    public void setMaxRecycledViews(int param1Int1, int param1Int2) {
      ScrapData scrapData = getScrapDataForType(param1Int1);
      scrapData.mMaxScrap = param1Int2;
      ArrayList<RecyclerView.ViewHolder> arrayList = scrapData.mScrapHeap;
      while (arrayList.size() > param1Int2)
        arrayList.remove(arrayList.size() - 1); 
    }
    
    int size() {
      byte b = 0;
      int i;
      for (i = 0; b < this.mScrap.size(); i = j) {
        ArrayList<RecyclerView.ViewHolder> arrayList = ((ScrapData)this.mScrap.valueAt(b)).mScrapHeap;
        int j = i;
        if (arrayList != null)
          j = i + arrayList.size(); 
        b++;
      } 
      return i;
    }
    
    boolean willBindInTime(int param1Int, long param1Long1, long param1Long2) {
      long l = (getScrapDataForType(param1Int)).mBindRunningAverageNs;
      return (l == 0L || param1Long1 + l < param1Long2);
    }
    
    boolean willCreateInTime(int param1Int, long param1Long1, long param1Long2) {
      long l = (getScrapDataForType(param1Int)).mCreateRunningAverageNs;
      return (l == 0L || param1Long1 + l < param1Long2);
    }
    
    static class ScrapData {
      long mBindRunningAverageNs = 0L;
      
      long mCreateRunningAverageNs = 0L;
      
      int mMaxScrap = 5;
      
      final ArrayList<RecyclerView.ViewHolder> mScrapHeap = new ArrayList<>();
    }
  }
  
  static class ScrapData {
    long mBindRunningAverageNs = 0L;
    
    long mCreateRunningAverageNs = 0L;
    
    int mMaxScrap = 5;
    
    final ArrayList<RecyclerView.ViewHolder> mScrapHeap = new ArrayList<>();
  }
  
  public final class Recycler {
    static final int DEFAULT_CACHE_SIZE = 2;
    
    final ArrayList<RecyclerView.ViewHolder> mAttachedScrap = new ArrayList<>();
    
    final ArrayList<RecyclerView.ViewHolder> mCachedViews = new ArrayList<>();
    
    ArrayList<RecyclerView.ViewHolder> mChangedScrap = null;
    
    RecyclerView.RecycledViewPool mRecyclerPool;
    
    private int mRequestedCacheMax = 2;
    
    private final List<RecyclerView.ViewHolder> mUnmodifiableAttachedScrap = Collections.unmodifiableList(this.mAttachedScrap);
    
    private RecyclerView.ViewCacheExtension mViewCacheExtension;
    
    int mViewCacheMax = 2;
    
    private void attachAccessibilityDelegateOnBind(RecyclerView.ViewHolder param1ViewHolder) {
      if (RecyclerView.this.isAccessibilityEnabled()) {
        View view = param1ViewHolder.itemView;
        if (ViewCompat.getImportantForAccessibility(view) == 0)
          ViewCompat.setImportantForAccessibility(view, 1); 
        if (!ViewCompat.hasAccessibilityDelegate(view)) {
          param1ViewHolder.addFlags(16384);
          ViewCompat.setAccessibilityDelegate(view, RecyclerView.this.mAccessibilityDelegate.getItemDelegate());
        } 
      } 
    }
    
    private void invalidateDisplayListInt(RecyclerView.ViewHolder param1ViewHolder) {
      if (param1ViewHolder.itemView instanceof ViewGroup)
        invalidateDisplayListInt((ViewGroup)param1ViewHolder.itemView, false); 
    }
    
    private void invalidateDisplayListInt(ViewGroup param1ViewGroup, boolean param1Boolean) {
      int i;
      for (i = param1ViewGroup.getChildCount() - 1; i >= 0; i--) {
        View view = param1ViewGroup.getChildAt(i);
        if (view instanceof ViewGroup)
          invalidateDisplayListInt((ViewGroup)view, true); 
      } 
      if (!param1Boolean)
        return; 
      if (param1ViewGroup.getVisibility() == 4) {
        param1ViewGroup.setVisibility(0);
        param1ViewGroup.setVisibility(4);
      } else {
        i = param1ViewGroup.getVisibility();
        param1ViewGroup.setVisibility(4);
        param1ViewGroup.setVisibility(i);
      } 
    }
    
    private boolean tryBindViewHolderByDeadline(@NonNull RecyclerView.ViewHolder param1ViewHolder, int param1Int1, int param1Int2, long param1Long) {
      param1ViewHolder.mOwnerRecyclerView = RecyclerView.this;
      int i = param1ViewHolder.getItemViewType();
      long l = RecyclerView.this.getNanoTime();
      if (param1Long != Long.MAX_VALUE && !this.mRecyclerPool.willBindInTime(i, l, param1Long))
        return false; 
      RecyclerView.this.mAdapter.bindViewHolder(param1ViewHolder, param1Int1);
      param1Long = RecyclerView.this.getNanoTime();
      this.mRecyclerPool.factorInBindTime(param1ViewHolder.getItemViewType(), param1Long - l);
      attachAccessibilityDelegateOnBind(param1ViewHolder);
      if (RecyclerView.this.mState.isPreLayout())
        param1ViewHolder.mPreLayoutPosition = param1Int2; 
      return true;
    }
    
    void addViewHolderToRecycledViewPool(@NonNull RecyclerView.ViewHolder param1ViewHolder, boolean param1Boolean) {
      RecyclerView.clearNestedRecyclerViewIfNotNested(param1ViewHolder);
      if (param1ViewHolder.hasAnyOfTheFlags(16384)) {
        param1ViewHolder.setFlags(0, 16384);
        ViewCompat.setAccessibilityDelegate(param1ViewHolder.itemView, null);
      } 
      if (param1Boolean)
        dispatchViewRecycled(param1ViewHolder); 
      param1ViewHolder.mOwnerRecyclerView = null;
      getRecycledViewPool().putRecycledView(param1ViewHolder);
    }
    
    public void bindViewToPosition(@NonNull View param1View, int param1Int) {
      RecyclerView.LayoutParams layoutParams;
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder == null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter");
        stringBuilder.append(RecyclerView.this.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      int i = RecyclerView.this.mAdapterHelper.findPositionOffset(param1Int);
      if (i < 0 || i >= RecyclerView.this.mAdapter.getItemCount()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Inconsistency detected. Invalid item position ");
        stringBuilder.append(param1Int);
        stringBuilder.append("(offset:");
        stringBuilder.append(i);
        stringBuilder.append(").");
        stringBuilder.append("state:");
        stringBuilder.append(RecyclerView.this.mState.getItemCount());
        stringBuilder.append(RecyclerView.this.exceptionLabel());
        throw new IndexOutOfBoundsException(stringBuilder.toString());
      } 
      tryBindViewHolderByDeadline(viewHolder, i, param1Int, Long.MAX_VALUE);
      ViewGroup.LayoutParams layoutParams1 = viewHolder.itemView.getLayoutParams();
      if (layoutParams1 == null) {
        layoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
        viewHolder.itemView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      } else if (!RecyclerView.this.checkLayoutParams((ViewGroup.LayoutParams)layoutParams)) {
        layoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateLayoutParams((ViewGroup.LayoutParams)layoutParams);
        viewHolder.itemView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      } else {
        layoutParams = layoutParams;
      } 
      boolean bool = true;
      layoutParams.mInsetsDirty = true;
      layoutParams.mViewHolder = viewHolder;
      if (viewHolder.itemView.getParent() != null)
        bool = false; 
      layoutParams.mPendingInvalidate = bool;
    }
    
    public void clear() {
      this.mAttachedScrap.clear();
      recycleAndClearCachedViews();
    }
    
    void clearOldPositions() {
      int i = this.mCachedViews.size();
      boolean bool = false;
      byte b;
      for (b = 0; b < i; b++)
        ((RecyclerView.ViewHolder)this.mCachedViews.get(b)).clearOldPosition(); 
      i = this.mAttachedScrap.size();
      for (b = 0; b < i; b++)
        ((RecyclerView.ViewHolder)this.mAttachedScrap.get(b)).clearOldPosition(); 
      if (this.mChangedScrap != null) {
        i = this.mChangedScrap.size();
        for (b = bool; b < i; b++)
          ((RecyclerView.ViewHolder)this.mChangedScrap.get(b)).clearOldPosition(); 
      } 
    }
    
    void clearScrap() {
      this.mAttachedScrap.clear();
      if (this.mChangedScrap != null)
        this.mChangedScrap.clear(); 
    }
    
    public int convertPreLayoutPositionToPostLayout(int param1Int) {
      if (param1Int < 0 || param1Int >= RecyclerView.this.mState.getItemCount()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("invalid position ");
        stringBuilder.append(param1Int);
        stringBuilder.append(". State ");
        stringBuilder.append("item count is ");
        stringBuilder.append(RecyclerView.this.mState.getItemCount());
        stringBuilder.append(RecyclerView.this.exceptionLabel());
        throw new IndexOutOfBoundsException(stringBuilder.toString());
      } 
      return !RecyclerView.this.mState.isPreLayout() ? param1Int : RecyclerView.this.mAdapterHelper.findPositionOffset(param1Int);
    }
    
    void dispatchViewRecycled(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      if (RecyclerView.this.mRecyclerListener != null)
        RecyclerView.this.mRecyclerListener.onViewRecycled(param1ViewHolder); 
      if (RecyclerView.this.mAdapter != null)
        RecyclerView.this.mAdapter.onViewRecycled(param1ViewHolder); 
      if (RecyclerView.this.mState != null)
        RecyclerView.this.mViewInfoStore.removeViewHolder(param1ViewHolder); 
    }
    
    RecyclerView.ViewHolder getChangedScrapViewForPosition(int param1Int) {
      if (this.mChangedScrap != null) {
        int i = this.mChangedScrap.size();
        if (i != 0) {
          boolean bool = false;
          for (byte b = 0; b < i; b++) {
            RecyclerView.ViewHolder viewHolder = this.mChangedScrap.get(b);
            if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == param1Int) {
              viewHolder.addFlags(32);
              return viewHolder;
            } 
          } 
          if (RecyclerView.this.mAdapter.hasStableIds()) {
            param1Int = RecyclerView.this.mAdapterHelper.findPositionOffset(param1Int);
            if (param1Int > 0 && param1Int < RecyclerView.this.mAdapter.getItemCount()) {
              long l = RecyclerView.this.mAdapter.getItemId(param1Int);
              for (param1Int = bool; param1Int < i; param1Int++) {
                RecyclerView.ViewHolder viewHolder = this.mChangedScrap.get(param1Int);
                if (!viewHolder.wasReturnedFromScrap() && viewHolder.getItemId() == l) {
                  viewHolder.addFlags(32);
                  return viewHolder;
                } 
              } 
            } 
          } 
          return null;
        } 
      } 
      return null;
    }
    
    RecyclerView.RecycledViewPool getRecycledViewPool() {
      if (this.mRecyclerPool == null)
        this.mRecyclerPool = new RecyclerView.RecycledViewPool(); 
      return this.mRecyclerPool;
    }
    
    int getScrapCount() {
      return this.mAttachedScrap.size();
    }
    
    @NonNull
    public List<RecyclerView.ViewHolder> getScrapList() {
      return this.mUnmodifiableAttachedScrap;
    }
    
    RecyclerView.ViewHolder getScrapOrCachedViewForId(long param1Long, int param1Int, boolean param1Boolean) {
      int i;
      for (i = this.mAttachedScrap.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mAttachedScrap.get(i);
        if (viewHolder.getItemId() == param1Long && !viewHolder.wasReturnedFromScrap()) {
          if (param1Int == viewHolder.getItemViewType()) {
            viewHolder.addFlags(32);
            if (viewHolder.isRemoved() && !RecyclerView.this.mState.isPreLayout())
              viewHolder.setFlags(2, 14); 
            return viewHolder;
          } 
          if (!param1Boolean) {
            this.mAttachedScrap.remove(i);
            RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
            quickRecycleScrapView(viewHolder.itemView);
          } 
        } 
      } 
      for (i = this.mCachedViews.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(i);
        if (viewHolder.getItemId() == param1Long) {
          if (param1Int == viewHolder.getItemViewType()) {
            if (!param1Boolean)
              this.mCachedViews.remove(i); 
            return viewHolder;
          } 
          if (!param1Boolean) {
            recycleCachedViewAt(i);
            return null;
          } 
        } 
      } 
      return null;
    }
    
    RecyclerView.ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int param1Int, boolean param1Boolean) {
      int i = this.mAttachedScrap.size();
      boolean bool = false;
      byte b;
      for (b = 0; b < i; b++) {
        RecyclerView.ViewHolder viewHolder = this.mAttachedScrap.get(b);
        if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == param1Int && !viewHolder.isInvalid() && (RecyclerView.this.mState.mInPreLayout || !viewHolder.isRemoved())) {
          viewHolder.addFlags(32);
          return viewHolder;
        } 
      } 
      if (!param1Boolean) {
        View view = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(param1Int);
        if (view != null) {
          StringBuilder stringBuilder;
          RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
          RecyclerView.this.mChildHelper.unhide(view);
          param1Int = RecyclerView.this.mChildHelper.indexOfChild(view);
          if (param1Int == -1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("layout index should not be -1 after unhiding a view:");
            stringBuilder.append(viewHolder);
            stringBuilder.append(RecyclerView.this.exceptionLabel());
            throw new IllegalStateException(stringBuilder.toString());
          } 
          RecyclerView.this.mChildHelper.detachViewFromParent(param1Int);
          scrapView((View)stringBuilder);
          viewHolder.addFlags(8224);
          return viewHolder;
        } 
      } 
      i = this.mCachedViews.size();
      for (b = bool; b < i; b++) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
        if (!viewHolder.isInvalid() && viewHolder.getLayoutPosition() == param1Int) {
          if (!param1Boolean)
            this.mCachedViews.remove(b); 
          return viewHolder;
        } 
      } 
      return null;
    }
    
    View getScrapViewAt(int param1Int) {
      return ((RecyclerView.ViewHolder)this.mAttachedScrap.get(param1Int)).itemView;
    }
    
    @NonNull
    public View getViewForPosition(int param1Int) {
      return getViewForPosition(param1Int, false);
    }
    
    View getViewForPosition(int param1Int, boolean param1Boolean) {
      return (tryGetViewHolderForPositionByDeadline(param1Int, param1Boolean, Long.MAX_VALUE)).itemView;
    }
    
    void markItemDecorInsetsDirty() {
      int i = this.mCachedViews.size();
      for (byte b = 0; b < i; b++) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)((RecyclerView.ViewHolder)this.mCachedViews.get(b)).itemView.getLayoutParams();
        if (layoutParams != null)
          layoutParams.mInsetsDirty = true; 
      } 
    }
    
    void markKnownViewsInvalid() {
      int i = this.mCachedViews.size();
      for (byte b = 0; b < i; b++) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
        if (viewHolder != null) {
          viewHolder.addFlags(6);
          viewHolder.addChangePayload(null);
        } 
      } 
      if (RecyclerView.this.mAdapter == null || !RecyclerView.this.mAdapter.hasStableIds())
        recycleAndClearCachedViews(); 
    }
    
    void offsetPositionRecordsForInsert(int param1Int1, int param1Int2) {
      int i = this.mCachedViews.size();
      for (byte b = 0; b < i; b++) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
        if (viewHolder != null && viewHolder.mPosition >= param1Int1)
          viewHolder.offsetPosition(param1Int2, true); 
      } 
    }
    
    void offsetPositionRecordsForMove(int param1Int1, int param1Int2) {
      int i;
      boolean bool;
      int j;
      if (param1Int1 < param1Int2) {
        i = param1Int2;
        bool = true;
        j = param1Int1;
      } else {
        i = param1Int1;
        bool = true;
        j = param1Int2;
      } 
      int k = this.mCachedViews.size();
      for (byte b = 0; b < k; b++) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
        if (viewHolder != null && viewHolder.mPosition >= j && viewHolder.mPosition <= i)
          if (viewHolder.mPosition == param1Int1) {
            viewHolder.offsetPosition(param1Int2 - param1Int1, false);
          } else {
            viewHolder.offsetPosition(bool, false);
          }  
      } 
    }
    
    void offsetPositionRecordsForRemove(int param1Int1, int param1Int2, boolean param1Boolean) {
      for (int i = this.mCachedViews.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(i);
        if (viewHolder != null)
          if (viewHolder.mPosition >= param1Int1 + param1Int2) {
            viewHolder.offsetPosition(-param1Int2, param1Boolean);
          } else if (viewHolder.mPosition >= param1Int1) {
            viewHolder.addFlags(8);
            recycleCachedViewAt(i);
          }  
      } 
    }
    
    void onAdapterChanged(RecyclerView.Adapter param1Adapter1, RecyclerView.Adapter param1Adapter2, boolean param1Boolean) {
      clear();
      getRecycledViewPool().onAdapterChanged(param1Adapter1, param1Adapter2, param1Boolean);
    }
    
    void quickRecycleScrapView(View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      viewHolder.mScrapContainer = null;
      viewHolder.mInChangeScrap = false;
      viewHolder.clearReturnedFromScrapFlag();
      recycleViewHolderInternal(viewHolder);
    }
    
    void recycleAndClearCachedViews() {
      for (int i = this.mCachedViews.size() - 1; i >= 0; i--)
        recycleCachedViewAt(i); 
      this.mCachedViews.clear();
      if (RecyclerView.ALLOW_THREAD_GAP_WORK)
        RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions(); 
    }
    
    void recycleCachedViewAt(int param1Int) {
      addViewHolderToRecycledViewPool(this.mCachedViews.get(param1Int), true);
      this.mCachedViews.remove(param1Int);
    }
    
    public void recycleView(@NonNull View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.isTmpDetached())
        RecyclerView.this.removeDetachedView(param1View, false); 
      if (viewHolder.isScrap()) {
        viewHolder.unScrap();
      } else if (viewHolder.wasReturnedFromScrap()) {
        viewHolder.clearReturnedFromScrapFlag();
      } 
      recycleViewHolderInternal(viewHolder);
    }
    
    void recycleViewHolderInternal(RecyclerView.ViewHolder param1ViewHolder) {
      StringBuilder stringBuilder;
      int i;
      boolean bool4;
      boolean bool1 = param1ViewHolder.isScrap();
      boolean bool2 = false;
      boolean bool3 = false;
      if (bool1 || param1ViewHolder.itemView.getParent() != null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Scrapped or attached views may not be recycled. isScrap:");
        stringBuilder1.append(param1ViewHolder.isScrap());
        stringBuilder1.append(" isAttached:");
        if (param1ViewHolder.itemView.getParent() != null)
          bool2 = true; 
        stringBuilder1.append(bool2);
        stringBuilder1.append(RecyclerView.this.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      if (param1ViewHolder.isTmpDetached()) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
        stringBuilder1.append(param1ViewHolder);
        stringBuilder1.append(RecyclerView.this.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      if (param1ViewHolder.shouldIgnore()) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
        stringBuilder.append(RecyclerView.this.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      bool2 = stringBuilder.doesTransientStatePreventRecycling();
      if (RecyclerView.this.mAdapter != null && bool2 && RecyclerView.this.mAdapter.onFailedToRecycleView(stringBuilder)) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i || stringBuilder.isRecyclable()) {
        if (this.mViewCacheMax > 0 && !stringBuilder.hasAnyOfTheFlags(526)) {
          int j = this.mCachedViews.size();
          i = j;
          if (j >= this.mViewCacheMax) {
            i = j;
            if (j > 0) {
              recycleCachedViewAt(0);
              i = j - 1;
            } 
          } 
          j = i;
          if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
            j = i;
            if (i > 0) {
              j = i;
              if (!RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(((RecyclerView.ViewHolder)stringBuilder).mPosition)) {
                while (--i >= 0) {
                  j = ((RecyclerView.ViewHolder)this.mCachedViews.get(i)).mPosition;
                  if (!RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(j))
                    break; 
                  i--;
                } 
                j = i + 1;
              } 
            } 
          } 
          this.mCachedViews.add(j, stringBuilder);
          i = 1;
        } else {
          i = 0;
        } 
        bool4 = i;
        if (i == 0) {
          addViewHolderToRecycledViewPool((RecyclerView.ViewHolder)stringBuilder, true);
          bool3 = true;
          bool4 = i;
        } 
      } else {
        bool4 = false;
      } 
      RecyclerView.this.mViewInfoStore.removeViewHolder((RecyclerView.ViewHolder)stringBuilder);
      if (!bool4 && !bool3 && bool2)
        ((RecyclerView.ViewHolder)stringBuilder).mOwnerRecyclerView = null; 
    }
    
    void recycleViewInternal(View param1View) {
      recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(param1View));
    }
    
    void scrapView(View param1View) {
      StringBuilder stringBuilder;
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.hasAnyOfTheFlags(12) || !viewHolder.isUpdated() || RecyclerView.this.canReuseUpdatedViewHolder(viewHolder)) {
        if (viewHolder.isInvalid() && !viewHolder.isRemoved() && !RecyclerView.this.mAdapter.hasStableIds()) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
          stringBuilder.append(RecyclerView.this.exceptionLabel());
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        stringBuilder.setScrapContainer(this, false);
        this.mAttachedScrap.add(stringBuilder);
        return;
      } 
      if (this.mChangedScrap == null)
        this.mChangedScrap = new ArrayList<>(); 
      stringBuilder.setScrapContainer(this, true);
      this.mChangedScrap.add(stringBuilder);
    }
    
    void setRecycledViewPool(RecyclerView.RecycledViewPool param1RecycledViewPool) {
      if (this.mRecyclerPool != null)
        this.mRecyclerPool.detach(); 
      this.mRecyclerPool = param1RecycledViewPool;
      if (this.mRecyclerPool != null && RecyclerView.this.getAdapter() != null)
        this.mRecyclerPool.attach(); 
    }
    
    void setViewCacheExtension(RecyclerView.ViewCacheExtension param1ViewCacheExtension) {
      this.mViewCacheExtension = param1ViewCacheExtension;
    }
    
    public void setViewCacheSize(int param1Int) {
      this.mRequestedCacheMax = param1Int;
      updateViewCacheSize();
    }
    
    @Nullable
    RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int param1Int, boolean param1Boolean, long param1Long) {
      // Byte code:
      //   0: iload_1
      //   1: iflt -> 1073
      //   4: iload_1
      //   5: aload_0
      //   6: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   9: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   12: invokevirtual getItemCount : ()I
      //   15: if_icmplt -> 21
      //   18: goto -> 1073
      //   21: aload_0
      //   22: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   25: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   28: invokevirtual isPreLayout : ()Z
      //   31: istore #5
      //   33: iconst_1
      //   34: istore #6
      //   36: iload #5
      //   38: ifeq -> 67
      //   41: aload_0
      //   42: iload_1
      //   43: invokevirtual getChangedScrapViewForPosition : (I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   46: astore #7
      //   48: aload #7
      //   50: astore #8
      //   52: aload #7
      //   54: ifnull -> 70
      //   57: iconst_1
      //   58: istore #9
      //   60: aload #7
      //   62: astore #8
      //   64: goto -> 73
      //   67: aconst_null
      //   68: astore #8
      //   70: iconst_0
      //   71: istore #9
      //   73: aload #8
      //   75: astore #7
      //   77: iload #9
      //   79: istore #10
      //   81: aload #8
      //   83: ifnonnull -> 191
      //   86: aload_0
      //   87: iload_1
      //   88: iload_2
      //   89: invokevirtual getScrapOrHiddenOrCachedHolderForPosition : (IZ)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   92: astore #8
      //   94: aload #8
      //   96: astore #7
      //   98: iload #9
      //   100: istore #10
      //   102: aload #8
      //   104: ifnull -> 191
      //   107: aload_0
      //   108: aload #8
      //   110: invokevirtual validateViewHolderForOffsetPosition : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)Z
      //   113: ifne -> 184
      //   116: iload_2
      //   117: ifne -> 174
      //   120: aload #8
      //   122: iconst_4
      //   123: invokevirtual addFlags : (I)V
      //   126: aload #8
      //   128: invokevirtual isScrap : ()Z
      //   131: ifeq -> 155
      //   134: aload_0
      //   135: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   138: aload #8
      //   140: getfield itemView : Landroid/view/View;
      //   143: iconst_0
      //   144: invokevirtual removeDetachedView : (Landroid/view/View;Z)V
      //   147: aload #8
      //   149: invokevirtual unScrap : ()V
      //   152: goto -> 168
      //   155: aload #8
      //   157: invokevirtual wasReturnedFromScrap : ()Z
      //   160: ifeq -> 168
      //   163: aload #8
      //   165: invokevirtual clearReturnedFromScrapFlag : ()V
      //   168: aload_0
      //   169: aload #8
      //   171: invokevirtual recycleViewHolderInternal : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)V
      //   174: aconst_null
      //   175: astore #7
      //   177: iload #9
      //   179: istore #10
      //   181: goto -> 191
      //   184: iconst_1
      //   185: istore #10
      //   187: aload #8
      //   189: astore #7
      //   191: aload #7
      //   193: astore #11
      //   195: iload #10
      //   197: istore #12
      //   199: aload #7
      //   201: ifnonnull -> 763
      //   204: aload_0
      //   205: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   208: getfield mAdapterHelper : Landroid/support/v7/widget/AdapterHelper;
      //   211: iload_1
      //   212: invokevirtual findPositionOffset : (I)I
      //   215: istore #12
      //   217: iload #12
      //   219: iflt -> 661
      //   222: iload #12
      //   224: aload_0
      //   225: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   228: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   231: invokevirtual getItemCount : ()I
      //   234: if_icmplt -> 240
      //   237: goto -> 661
      //   240: aload_0
      //   241: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   244: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   247: iload #12
      //   249: invokevirtual getItemViewType : (I)I
      //   252: istore #13
      //   254: aload #7
      //   256: astore #8
      //   258: iload #10
      //   260: istore #9
      //   262: aload_0
      //   263: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   266: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   269: invokevirtual hasStableIds : ()Z
      //   272: ifeq -> 323
      //   275: aload_0
      //   276: aload_0
      //   277: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   280: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   283: iload #12
      //   285: invokevirtual getItemId : (I)J
      //   288: iload #13
      //   290: iload_2
      //   291: invokevirtual getScrapOrCachedViewForId : (JIZ)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   294: astore #7
      //   296: aload #7
      //   298: astore #8
      //   300: iload #10
      //   302: istore #9
      //   304: aload #7
      //   306: ifnull -> 323
      //   309: aload #7
      //   311: iload #12
      //   313: putfield mPosition : I
      //   316: iconst_1
      //   317: istore #9
      //   319: aload #7
      //   321: astore #8
      //   323: aload #8
      //   325: astore #7
      //   327: aload #8
      //   329: ifnonnull -> 481
      //   332: aload #8
      //   334: astore #7
      //   336: aload_0
      //   337: getfield mViewCacheExtension : Landroid/support/v7/widget/RecyclerView$ViewCacheExtension;
      //   340: ifnull -> 481
      //   343: aload_0
      //   344: getfield mViewCacheExtension : Landroid/support/v7/widget/RecyclerView$ViewCacheExtension;
      //   347: aload_0
      //   348: iload_1
      //   349: iload #13
      //   351: invokevirtual getViewForPositionAndType : (Landroid/support/v7/widget/RecyclerView$Recycler;II)Landroid/view/View;
      //   354: astore #11
      //   356: aload #8
      //   358: astore #7
      //   360: aload #11
      //   362: ifnull -> 481
      //   365: aload_0
      //   366: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   369: aload #11
      //   371: invokevirtual getChildViewHolder : (Landroid/view/View;)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   374: astore #8
      //   376: aload #8
      //   378: ifnonnull -> 425
      //   381: new java/lang/StringBuilder
      //   384: dup
      //   385: invokespecial <init> : ()V
      //   388: astore #7
      //   390: aload #7
      //   392: ldc_w 'getViewForPositionAndType returned a view which does not have a ViewHolder'
      //   395: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   398: pop
      //   399: aload #7
      //   401: aload_0
      //   402: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   405: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   411: pop
      //   412: new java/lang/IllegalArgumentException
      //   415: dup
      //   416: aload #7
      //   418: invokevirtual toString : ()Ljava/lang/String;
      //   421: invokespecial <init> : (Ljava/lang/String;)V
      //   424: athrow
      //   425: aload #8
      //   427: astore #7
      //   429: aload #8
      //   431: invokevirtual shouldIgnore : ()Z
      //   434: ifeq -> 481
      //   437: new java/lang/StringBuilder
      //   440: dup
      //   441: invokespecial <init> : ()V
      //   444: astore #7
      //   446: aload #7
      //   448: ldc_w 'getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.'
      //   451: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   454: pop
      //   455: aload #7
      //   457: aload_0
      //   458: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   461: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   467: pop
      //   468: new java/lang/IllegalArgumentException
      //   471: dup
      //   472: aload #7
      //   474: invokevirtual toString : ()Ljava/lang/String;
      //   477: invokespecial <init> : (Ljava/lang/String;)V
      //   480: athrow
      //   481: aload #7
      //   483: astore #8
      //   485: aload #7
      //   487: ifnonnull -> 535
      //   490: aload_0
      //   491: invokevirtual getRecycledViewPool : ()Landroid/support/v7/widget/RecyclerView$RecycledViewPool;
      //   494: iload #13
      //   496: invokevirtual getRecycledView : (I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   499: astore #7
      //   501: aload #7
      //   503: astore #8
      //   505: aload #7
      //   507: ifnull -> 535
      //   510: aload #7
      //   512: invokevirtual resetInternal : ()V
      //   515: aload #7
      //   517: astore #8
      //   519: getstatic android/support/v7/widget/RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST : Z
      //   522: ifeq -> 535
      //   525: aload_0
      //   526: aload #7
      //   528: invokespecial invalidateDisplayListInt : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)V
      //   531: aload #7
      //   533: astore #8
      //   535: aload #8
      //   537: astore #11
      //   539: iload #9
      //   541: istore #12
      //   543: aload #8
      //   545: ifnonnull -> 763
      //   548: aload_0
      //   549: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   552: invokevirtual getNanoTime : ()J
      //   555: lstore #14
      //   557: lload_3
      //   558: ldc2_w 9223372036854775807
      //   561: lcmp
      //   562: ifeq -> 582
      //   565: aload_0
      //   566: getfield mRecyclerPool : Landroid/support/v7/widget/RecyclerView$RecycledViewPool;
      //   569: iload #13
      //   571: lload #14
      //   573: lload_3
      //   574: invokevirtual willCreateInTime : (IJJ)Z
      //   577: ifne -> 582
      //   580: aconst_null
      //   581: areturn
      //   582: aload_0
      //   583: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   586: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   589: aload_0
      //   590: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   593: iload #13
      //   595: invokevirtual createViewHolder : (Landroid/view/ViewGroup;I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   598: astore #8
      //   600: getstatic android/support/v7/widget/RecyclerView.ALLOW_THREAD_GAP_WORK : Z
      //   603: ifeq -> 635
      //   606: aload #8
      //   608: getfield itemView : Landroid/view/View;
      //   611: invokestatic findNestedRecyclerView : (Landroid/view/View;)Landroid/support/v7/widget/RecyclerView;
      //   614: astore #7
      //   616: aload #7
      //   618: ifnull -> 635
      //   621: aload #8
      //   623: new java/lang/ref/WeakReference
      //   626: dup
      //   627: aload #7
      //   629: invokespecial <init> : (Ljava/lang/Object;)V
      //   632: putfield mNestedRecyclerView : Ljava/lang/ref/WeakReference;
      //   635: aload_0
      //   636: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   639: invokevirtual getNanoTime : ()J
      //   642: lstore #16
      //   644: aload_0
      //   645: getfield mRecyclerPool : Landroid/support/v7/widget/RecyclerView$RecycledViewPool;
      //   648: iload #13
      //   650: lload #16
      //   652: lload #14
      //   654: lsub
      //   655: invokevirtual factorInCreateTime : (IJ)V
      //   658: goto -> 771
      //   661: new java/lang/StringBuilder
      //   664: dup
      //   665: invokespecial <init> : ()V
      //   668: astore #7
      //   670: aload #7
      //   672: ldc_w 'Inconsistency detected. Invalid item position '
      //   675: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   678: pop
      //   679: aload #7
      //   681: iload_1
      //   682: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   685: pop
      //   686: aload #7
      //   688: ldc_w '(offset:'
      //   691: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   694: pop
      //   695: aload #7
      //   697: iload #12
      //   699: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   702: pop
      //   703: aload #7
      //   705: ldc_w ').'
      //   708: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   711: pop
      //   712: aload #7
      //   714: ldc_w 'state:'
      //   717: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   720: pop
      //   721: aload #7
      //   723: aload_0
      //   724: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   727: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   730: invokevirtual getItemCount : ()I
      //   733: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   736: pop
      //   737: aload #7
      //   739: aload_0
      //   740: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   743: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   746: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   749: pop
      //   750: new java/lang/IndexOutOfBoundsException
      //   753: dup
      //   754: aload #7
      //   756: invokevirtual toString : ()Ljava/lang/String;
      //   759: invokespecial <init> : (Ljava/lang/String;)V
      //   762: athrow
      //   763: aload #11
      //   765: astore #8
      //   767: iload #12
      //   769: istore #9
      //   771: iload #9
      //   773: ifeq -> 872
      //   776: aload_0
      //   777: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   780: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   783: invokevirtual isPreLayout : ()Z
      //   786: ifne -> 872
      //   789: aload #8
      //   791: sipush #8192
      //   794: invokevirtual hasAnyOfTheFlags : (I)Z
      //   797: ifeq -> 872
      //   800: aload #8
      //   802: iconst_0
      //   803: sipush #8192
      //   806: invokevirtual setFlags : (II)V
      //   809: aload_0
      //   810: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   813: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   816: getfield mRunSimpleAnimations : Z
      //   819: ifeq -> 872
      //   822: aload #8
      //   824: invokestatic buildAdapterChangeFlagsForAnimations : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)I
      //   827: istore #10
      //   829: aload_0
      //   830: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   833: getfield mItemAnimator : Landroid/support/v7/widget/RecyclerView$ItemAnimator;
      //   836: aload_0
      //   837: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   840: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   843: aload #8
      //   845: iload #10
      //   847: sipush #4096
      //   850: ior
      //   851: aload #8
      //   853: invokevirtual getUnmodifiedPayloads : ()Ljava/util/List;
      //   856: invokevirtual recordPreLayoutInformation : (Landroid/support/v7/widget/RecyclerView$State;Landroid/support/v7/widget/RecyclerView$ViewHolder;ILjava/util/List;)Landroid/support/v7/widget/RecyclerView$ItemAnimator$ItemHolderInfo;
      //   859: astore #7
      //   861: aload_0
      //   862: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   865: aload #8
      //   867: aload #7
      //   869: invokevirtual recordAnimationInfoIfBouncedHiddenView : (Landroid/support/v7/widget/RecyclerView$ViewHolder;Landroid/support/v7/widget/RecyclerView$ItemAnimator$ItemHolderInfo;)V
      //   872: aload_0
      //   873: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   876: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   879: invokevirtual isPreLayout : ()Z
      //   882: ifeq -> 902
      //   885: aload #8
      //   887: invokevirtual isBound : ()Z
      //   890: ifeq -> 902
      //   893: aload #8
      //   895: iload_1
      //   896: putfield mPreLayoutPosition : I
      //   899: goto -> 929
      //   902: aload #8
      //   904: invokevirtual isBound : ()Z
      //   907: ifeq -> 934
      //   910: aload #8
      //   912: invokevirtual needsUpdate : ()Z
      //   915: ifne -> 934
      //   918: aload #8
      //   920: invokevirtual isInvalid : ()Z
      //   923: ifeq -> 929
      //   926: goto -> 934
      //   929: iconst_0
      //   930: istore_2
      //   931: goto -> 954
      //   934: aload_0
      //   935: aload #8
      //   937: aload_0
      //   938: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   941: getfield mAdapterHelper : Landroid/support/v7/widget/AdapterHelper;
      //   944: iload_1
      //   945: invokevirtual findPositionOffset : (I)I
      //   948: iload_1
      //   949: lload_3
      //   950: invokespecial tryBindViewHolderByDeadline : (Landroid/support/v7/widget/RecyclerView$ViewHolder;IIJ)Z
      //   953: istore_2
      //   954: aload #8
      //   956: getfield itemView : Landroid/view/View;
      //   959: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
      //   962: astore #7
      //   964: aload #7
      //   966: ifnonnull -> 994
      //   969: aload_0
      //   970: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   973: invokevirtual generateDefaultLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
      //   976: checkcast android/support/v7/widget/RecyclerView$LayoutParams
      //   979: astore #7
      //   981: aload #8
      //   983: getfield itemView : Landroid/view/View;
      //   986: aload #7
      //   988: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
      //   991: goto -> 1040
      //   994: aload_0
      //   995: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   998: aload #7
      //   1000: invokevirtual checkLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)Z
      //   1003: ifne -> 1033
      //   1006: aload_0
      //   1007: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   1010: aload #7
      //   1012: invokevirtual generateLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;
      //   1015: checkcast android/support/v7/widget/RecyclerView$LayoutParams
      //   1018: astore #7
      //   1020: aload #8
      //   1022: getfield itemView : Landroid/view/View;
      //   1025: aload #7
      //   1027: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
      //   1030: goto -> 1040
      //   1033: aload #7
      //   1035: checkcast android/support/v7/widget/RecyclerView$LayoutParams
      //   1038: astore #7
      //   1040: aload #7
      //   1042: aload #8
      //   1044: putfield mViewHolder : Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   1047: iload #9
      //   1049: ifeq -> 1062
      //   1052: iload_2
      //   1053: ifeq -> 1062
      //   1056: iload #6
      //   1058: istore_2
      //   1059: goto -> 1064
      //   1062: iconst_0
      //   1063: istore_2
      //   1064: aload #7
      //   1066: iload_2
      //   1067: putfield mPendingInvalidate : Z
      //   1070: aload #8
      //   1072: areturn
      //   1073: new java/lang/StringBuilder
      //   1076: dup
      //   1077: invokespecial <init> : ()V
      //   1080: astore #7
      //   1082: aload #7
      //   1084: ldc_w 'Invalid item position '
      //   1087: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1090: pop
      //   1091: aload #7
      //   1093: iload_1
      //   1094: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   1097: pop
      //   1098: aload #7
      //   1100: ldc_w '('
      //   1103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1106: pop
      //   1107: aload #7
      //   1109: iload_1
      //   1110: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   1113: pop
      //   1114: aload #7
      //   1116: ldc_w '). Item count:'
      //   1119: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1122: pop
      //   1123: aload #7
      //   1125: aload_0
      //   1126: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   1129: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   1132: invokevirtual getItemCount : ()I
      //   1135: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   1138: pop
      //   1139: aload #7
      //   1141: aload_0
      //   1142: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   1145: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   1148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1151: pop
      //   1152: new java/lang/IndexOutOfBoundsException
      //   1155: dup
      //   1156: aload #7
      //   1158: invokevirtual toString : ()Ljava/lang/String;
      //   1161: invokespecial <init> : (Ljava/lang/String;)V
      //   1164: athrow
    }
    
    void unscrapView(RecyclerView.ViewHolder param1ViewHolder) {
      if (param1ViewHolder.mInChangeScrap) {
        this.mChangedScrap.remove(param1ViewHolder);
      } else {
        this.mAttachedScrap.remove(param1ViewHolder);
      } 
      param1ViewHolder.mScrapContainer = null;
      param1ViewHolder.mInChangeScrap = false;
      param1ViewHolder.clearReturnedFromScrapFlag();
    }
    
    void updateViewCacheSize() {
      if (RecyclerView.this.mLayout != null) {
        i = RecyclerView.this.mLayout.mPrefetchMaxCountObserved;
      } else {
        i = 0;
      } 
      this.mViewCacheMax = this.mRequestedCacheMax + i;
      for (int i = this.mCachedViews.size() - 1; i >= 0 && this.mCachedViews.size() > this.mViewCacheMax; i--)
        recycleCachedViewAt(i); 
    }
    
    boolean validateViewHolderForOffsetPosition(RecyclerView.ViewHolder param1ViewHolder) {
      if (param1ViewHolder.isRemoved())
        return RecyclerView.this.mState.isPreLayout(); 
      if (param1ViewHolder.mPosition < 0 || param1ViewHolder.mPosition >= RecyclerView.this.mAdapter.getItemCount()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Inconsistency detected. Invalid view holder adapter position");
        stringBuilder.append(param1ViewHolder);
        stringBuilder.append(RecyclerView.this.exceptionLabel());
        throw new IndexOutOfBoundsException(stringBuilder.toString());
      } 
      boolean bool = RecyclerView.this.mState.isPreLayout();
      boolean bool1 = false;
      if (!bool && RecyclerView.this.mAdapter.getItemViewType(param1ViewHolder.mPosition) != param1ViewHolder.getItemViewType())
        return false; 
      if (RecyclerView.this.mAdapter.hasStableIds()) {
        if (param1ViewHolder.getItemId() == RecyclerView.this.mAdapter.getItemId(param1ViewHolder.mPosition))
          bool1 = true; 
        return bool1;
      } 
      return true;
    }
    
    void viewRangeUpdate(int param1Int1, int param1Int2) {
      for (int i = this.mCachedViews.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(i);
        if (viewHolder != null) {
          int j = viewHolder.mPosition;
          if (j >= param1Int1 && j < param1Int2 + param1Int1) {
            viewHolder.addFlags(2);
            recycleCachedViewAt(i);
          } 
        } 
      } 
    }
  }
  
  public static interface RecyclerListener {
    void onViewRecycled(@NonNull RecyclerView.ViewHolder param1ViewHolder);
  }
  
  private class RecyclerViewDataObserver extends AdapterDataObserver {
    public void onChanged() {
      RecyclerView.this.assertNotInLayoutOrScroll((String)null);
      RecyclerView.this.mState.mStructureChanged = true;
      RecyclerView.this.processDataSetCompletelyChanged(true);
      if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates())
        RecyclerView.this.requestLayout(); 
    }
    
    public void onItemRangeChanged(int param1Int1, int param1Int2, Object param1Object) {
      RecyclerView.this.assertNotInLayoutOrScroll((String)null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(param1Int1, param1Int2, param1Object))
        triggerUpdateProcessor(); 
    }
    
    public void onItemRangeInserted(int param1Int1, int param1Int2) {
      RecyclerView.this.assertNotInLayoutOrScroll((String)null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(param1Int1, param1Int2))
        triggerUpdateProcessor(); 
    }
    
    public void onItemRangeMoved(int param1Int1, int param1Int2, int param1Int3) {
      RecyclerView.this.assertNotInLayoutOrScroll((String)null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(param1Int1, param1Int2, param1Int3))
        triggerUpdateProcessor(); 
    }
    
    public void onItemRangeRemoved(int param1Int1, int param1Int2) {
      RecyclerView.this.assertNotInLayoutOrScroll((String)null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(param1Int1, param1Int2))
        triggerUpdateProcessor(); 
    }
    
    void triggerUpdateProcessor() {
      if (RecyclerView.POST_UPDATES_ON_ANIMATION && RecyclerView.this.mHasFixedSize && RecyclerView.this.mIsAttached) {
        ViewCompat.postOnAnimation((View)RecyclerView.this, RecyclerView.this.mUpdateChildViewsRunnable);
      } else {
        RecyclerView.this.mAdapterUpdateDuringMeasure = true;
        RecyclerView.this.requestLayout();
      } 
    }
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public RecyclerView.SavedState createFromParcel(Parcel param2Parcel) {
          return new RecyclerView.SavedState(param2Parcel, null);
        }
        
        public RecyclerView.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new RecyclerView.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public RecyclerView.SavedState[] newArray(int param2Int) {
          return new RecyclerView.SavedState[param2Int];
        }
      };
    
    Parcelable mLayoutState;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      if (param1ClassLoader == null)
        param1ClassLoader = RecyclerView.LayoutManager.class.getClassLoader(); 
      this.mLayoutState = param1Parcel.readParcelable(param1ClassLoader);
    }
    
    SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    void copyFrom(SavedState param1SavedState) {
      this.mLayoutState = param1SavedState.mLayoutState;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.mLayoutState, 0);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public RecyclerView.SavedState createFromParcel(Parcel param1Parcel) {
      return new RecyclerView.SavedState(param1Parcel, null);
    }
    
    public RecyclerView.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new RecyclerView.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public RecyclerView.SavedState[] newArray(int param1Int) {
      return new RecyclerView.SavedState[param1Int];
    }
  }
  
  public static class SimpleOnItemTouchListener implements OnItemTouchListener {
    public boolean onInterceptTouchEvent(@NonNull RecyclerView param1RecyclerView, @NonNull MotionEvent param1MotionEvent) {
      return false;
    }
    
    public void onRequestDisallowInterceptTouchEvent(boolean param1Boolean) {}
    
    public void onTouchEvent(@NonNull RecyclerView param1RecyclerView, @NonNull MotionEvent param1MotionEvent) {}
  }
  
  public static abstract class SmoothScroller {
    private RecyclerView.LayoutManager mLayoutManager;
    
    private boolean mPendingInitialRun;
    
    private RecyclerView mRecyclerView;
    
    private final Action mRecyclingAction = new Action(0, 0);
    
    private boolean mRunning;
    
    private boolean mStarted;
    
    private int mTargetPosition = -1;
    
    private View mTargetView;
    
    @Nullable
    public PointF computeScrollVectorForPosition(int param1Int) {
      RecyclerView.LayoutManager layoutManager = getLayoutManager();
      if (layoutManager instanceof ScrollVectorProvider)
        return ((ScrollVectorProvider)layoutManager).computeScrollVectorForPosition(param1Int); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
      stringBuilder.append(ScrollVectorProvider.class.getCanonicalName());
      Log.w("RecyclerView", stringBuilder.toString());
      return null;
    }
    
    public View findViewByPosition(int param1Int) {
      return this.mRecyclerView.mLayout.findViewByPosition(param1Int);
    }
    
    public int getChildCount() {
      return this.mRecyclerView.mLayout.getChildCount();
    }
    
    public int getChildPosition(View param1View) {
      return this.mRecyclerView.getChildLayoutPosition(param1View);
    }
    
    @Nullable
    public RecyclerView.LayoutManager getLayoutManager() {
      return this.mLayoutManager;
    }
    
    public int getTargetPosition() {
      return this.mTargetPosition;
    }
    
    @Deprecated
    public void instantScrollToPosition(int param1Int) {
      this.mRecyclerView.scrollToPosition(param1Int);
    }
    
    public boolean isPendingInitialRun() {
      return this.mPendingInitialRun;
    }
    
    public boolean isRunning() {
      return this.mRunning;
    }
    
    protected void normalize(@NonNull PointF param1PointF) {
      float f = (float)Math.sqrt((param1PointF.x * param1PointF.x + param1PointF.y * param1PointF.y));
      param1PointF.x /= f;
      param1PointF.y /= f;
    }
    
    void onAnimation(int param1Int1, int param1Int2) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (!this.mRunning || this.mTargetPosition == -1 || recyclerView == null)
        stop(); 
      if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null) {
        PointF pointF = computeScrollVectorForPosition(this.mTargetPosition);
        if (pointF != null && (pointF.x != 0.0F || pointF.y != 0.0F))
          recyclerView.scrollStep((int)Math.signum(pointF.x), (int)Math.signum(pointF.y), (int[])null); 
      } 
      this.mPendingInitialRun = false;
      if (this.mTargetView != null)
        if (getChildPosition(this.mTargetView) == this.mTargetPosition) {
          onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
          this.mRecyclingAction.runIfNecessary(recyclerView);
          stop();
        } else {
          Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
          this.mTargetView = null;
        }  
      if (this.mRunning) {
        onSeekTargetStep(param1Int1, param1Int2, recyclerView.mState, this.mRecyclingAction);
        boolean bool = this.mRecyclingAction.hasJumpTarget();
        this.mRecyclingAction.runIfNecessary(recyclerView);
        if (bool)
          if (this.mRunning) {
            this.mPendingInitialRun = true;
            recyclerView.mViewFlinger.postOnAnimation();
          } else {
            stop();
          }  
      } 
    }
    
    protected void onChildAttachedToWindow(View param1View) {
      if (getChildPosition(param1View) == getTargetPosition())
        this.mTargetView = param1View; 
    }
    
    protected abstract void onSeekTargetStep(@Px int param1Int1, @Px int param1Int2, @NonNull RecyclerView.State param1State, @NonNull Action param1Action);
    
    protected abstract void onStart();
    
    protected abstract void onStop();
    
    protected abstract void onTargetFound(@NonNull View param1View, @NonNull RecyclerView.State param1State, @NonNull Action param1Action);
    
    public void setTargetPosition(int param1Int) {
      this.mTargetPosition = param1Int;
    }
    
    void start(RecyclerView param1RecyclerView, RecyclerView.LayoutManager param1LayoutManager) {
      if (this.mStarted) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("An instance of ");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" was started ");
        stringBuilder.append("more than once. Each instance of");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" ");
        stringBuilder.append("is intended to only be used once. You should create a new instance for ");
        stringBuilder.append("each use.");
        Log.w("RecyclerView", stringBuilder.toString());
      } 
      this.mRecyclerView = param1RecyclerView;
      this.mLayoutManager = param1LayoutManager;
      if (this.mTargetPosition == -1)
        throw new IllegalArgumentException("Invalid target position"); 
      this.mRecyclerView.mState.mTargetPosition = this.mTargetPosition;
      this.mRunning = true;
      this.mPendingInitialRun = true;
      this.mTargetView = findViewByPosition(getTargetPosition());
      onStart();
      this.mRecyclerView.mViewFlinger.postOnAnimation();
      this.mStarted = true;
    }
    
    protected final void stop() {
      if (!this.mRunning)
        return; 
      this.mRunning = false;
      onStop();
      this.mRecyclerView.mState.mTargetPosition = -1;
      this.mTargetView = null;
      this.mTargetPosition = -1;
      this.mPendingInitialRun = false;
      this.mLayoutManager.onSmoothScrollerStopped(this);
      this.mLayoutManager = null;
      this.mRecyclerView = null;
    }
    
    public static class Action {
      public static final int UNDEFINED_DURATION = -2147483648;
      
      private boolean mChanged = false;
      
      private int mConsecutiveUpdates = 0;
      
      private int mDuration;
      
      private int mDx;
      
      private int mDy;
      
      private Interpolator mInterpolator;
      
      private int mJumpToPosition = -1;
      
      public Action(@Px int param2Int1, @Px int param2Int2) {
        this(param2Int1, param2Int2, -2147483648, null);
      }
      
      public Action(@Px int param2Int1, @Px int param2Int2, int param2Int3) {
        this(param2Int1, param2Int2, param2Int3, null);
      }
      
      public Action(@Px int param2Int1, @Px int param2Int2, int param2Int3, @Nullable Interpolator param2Interpolator) {
        this.mDx = param2Int1;
        this.mDy = param2Int2;
        this.mDuration = param2Int3;
        this.mInterpolator = param2Interpolator;
      }
      
      private void validate() {
        if (this.mInterpolator != null && this.mDuration < 1)
          throw new IllegalStateException("If you provide an interpolator, you must set a positive duration"); 
        if (this.mDuration < 1)
          throw new IllegalStateException("Scroll duration must be a positive number"); 
      }
      
      public int getDuration() {
        return this.mDuration;
      }
      
      @Px
      public int getDx() {
        return this.mDx;
      }
      
      @Px
      public int getDy() {
        return this.mDy;
      }
      
      @Nullable
      public Interpolator getInterpolator() {
        return this.mInterpolator;
      }
      
      boolean hasJumpTarget() {
        boolean bool;
        if (this.mJumpToPosition >= 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public void jumpTo(int param2Int) {
        this.mJumpToPosition = param2Int;
      }
      
      void runIfNecessary(RecyclerView param2RecyclerView) {
        if (this.mJumpToPosition >= 0) {
          int i = this.mJumpToPosition;
          this.mJumpToPosition = -1;
          param2RecyclerView.jumpToPositionForSmoothScroller(i);
          this.mChanged = false;
          return;
        } 
        if (this.mChanged) {
          validate();
          if (this.mInterpolator == null) {
            if (this.mDuration == Integer.MIN_VALUE) {
              param2RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
            } else {
              param2RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration);
            } 
          } else {
            param2RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
          } 
          this.mConsecutiveUpdates++;
          if (this.mConsecutiveUpdates > 10)
            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary"); 
          this.mChanged = false;
        } else {
          this.mConsecutiveUpdates = 0;
        } 
      }
      
      public void setDuration(int param2Int) {
        this.mChanged = true;
        this.mDuration = param2Int;
      }
      
      public void setDx(@Px int param2Int) {
        this.mChanged = true;
        this.mDx = param2Int;
      }
      
      public void setDy(@Px int param2Int) {
        this.mChanged = true;
        this.mDy = param2Int;
      }
      
      public void setInterpolator(@Nullable Interpolator param2Interpolator) {
        this.mChanged = true;
        this.mInterpolator = param2Interpolator;
      }
      
      public void update(@Px int param2Int1, @Px int param2Int2, int param2Int3, @Nullable Interpolator param2Interpolator) {
        this.mDx = param2Int1;
        this.mDy = param2Int2;
        this.mDuration = param2Int3;
        this.mInterpolator = param2Interpolator;
        this.mChanged = true;
      }
    }
    
    public static interface ScrollVectorProvider {
      @Nullable
      PointF computeScrollVectorForPosition(int param2Int);
    }
  }
  
  public static class Action {
    public static final int UNDEFINED_DURATION = -2147483648;
    
    private boolean mChanged = false;
    
    private int mConsecutiveUpdates = 0;
    
    private int mDuration;
    
    private int mDx;
    
    private int mDy;
    
    private Interpolator mInterpolator;
    
    private int mJumpToPosition = -1;
    
    public Action(@Px int param1Int1, @Px int param1Int2) {
      this(param1Int1, param1Int2, -2147483648, null);
    }
    
    public Action(@Px int param1Int1, @Px int param1Int2, int param1Int3) {
      this(param1Int1, param1Int2, param1Int3, null);
    }
    
    public Action(@Px int param1Int1, @Px int param1Int2, int param1Int3, @Nullable Interpolator param1Interpolator) {
      this.mDx = param1Int1;
      this.mDy = param1Int2;
      this.mDuration = param1Int3;
      this.mInterpolator = param1Interpolator;
    }
    
    private void validate() {
      if (this.mInterpolator != null && this.mDuration < 1)
        throw new IllegalStateException("If you provide an interpolator, you must set a positive duration"); 
      if (this.mDuration < 1)
        throw new IllegalStateException("Scroll duration must be a positive number"); 
    }
    
    public int getDuration() {
      return this.mDuration;
    }
    
    @Px
    public int getDx() {
      return this.mDx;
    }
    
    @Px
    public int getDy() {
      return this.mDy;
    }
    
    @Nullable
    public Interpolator getInterpolator() {
      return this.mInterpolator;
    }
    
    boolean hasJumpTarget() {
      boolean bool;
      if (this.mJumpToPosition >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void jumpTo(int param1Int) {
      this.mJumpToPosition = param1Int;
    }
    
    void runIfNecessary(RecyclerView param1RecyclerView) {
      if (this.mJumpToPosition >= 0) {
        int i = this.mJumpToPosition;
        this.mJumpToPosition = -1;
        param1RecyclerView.jumpToPositionForSmoothScroller(i);
        this.mChanged = false;
        return;
      } 
      if (this.mChanged) {
        validate();
        if (this.mInterpolator == null) {
          if (this.mDuration == Integer.MIN_VALUE) {
            param1RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
          } else {
            param1RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration);
          } 
        } else {
          param1RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
        } 
        this.mConsecutiveUpdates++;
        if (this.mConsecutiveUpdates > 10)
          Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary"); 
        this.mChanged = false;
      } else {
        this.mConsecutiveUpdates = 0;
      } 
    }
    
    public void setDuration(int param1Int) {
      this.mChanged = true;
      this.mDuration = param1Int;
    }
    
    public void setDx(@Px int param1Int) {
      this.mChanged = true;
      this.mDx = param1Int;
    }
    
    public void setDy(@Px int param1Int) {
      this.mChanged = true;
      this.mDy = param1Int;
    }
    
    public void setInterpolator(@Nullable Interpolator param1Interpolator) {
      this.mChanged = true;
      this.mInterpolator = param1Interpolator;
    }
    
    public void update(@Px int param1Int1, @Px int param1Int2, int param1Int3, @Nullable Interpolator param1Interpolator) {
      this.mDx = param1Int1;
      this.mDy = param1Int2;
      this.mDuration = param1Int3;
      this.mInterpolator = param1Interpolator;
      this.mChanged = true;
    }
  }
  
  public static interface ScrollVectorProvider {
    @Nullable
    PointF computeScrollVectorForPosition(int param1Int);
  }
  
  public static class State {
    static final int STEP_ANIMATIONS = 4;
    
    static final int STEP_LAYOUT = 2;
    
    static final int STEP_START = 1;
    
    private SparseArray<Object> mData;
    
    int mDeletedInvisibleItemCountSincePreviousLayout = 0;
    
    long mFocusedItemId;
    
    int mFocusedItemPosition;
    
    int mFocusedSubChildId;
    
    boolean mInPreLayout = false;
    
    boolean mIsMeasuring = false;
    
    int mItemCount = 0;
    
    int mLayoutStep = 1;
    
    int mPreviousLayoutItemCount = 0;
    
    int mRemainingScrollHorizontal;
    
    int mRemainingScrollVertical;
    
    boolean mRunPredictiveAnimations = false;
    
    boolean mRunSimpleAnimations = false;
    
    boolean mStructureChanged = false;
    
    int mTargetPosition = -1;
    
    boolean mTrackOldChangeHolders = false;
    
    void assertLayoutStep(int param1Int) {
      if ((this.mLayoutStep & param1Int) == 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Layout state should be one of ");
        stringBuilder.append(Integer.toBinaryString(param1Int));
        stringBuilder.append(" but it is ");
        stringBuilder.append(Integer.toBinaryString(this.mLayoutStep));
        throw new IllegalStateException(stringBuilder.toString());
      } 
    }
    
    public boolean didStructureChange() {
      return this.mStructureChanged;
    }
    
    public <T> T get(int param1Int) {
      return (T)((this.mData == null) ? null : this.mData.get(param1Int));
    }
    
    public int getItemCount() {
      int i;
      if (this.mInPreLayout) {
        i = this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
      } else {
        i = this.mItemCount;
      } 
      return i;
    }
    
    public int getRemainingScrollHorizontal() {
      return this.mRemainingScrollHorizontal;
    }
    
    public int getRemainingScrollVertical() {
      return this.mRemainingScrollVertical;
    }
    
    public int getTargetScrollPosition() {
      return this.mTargetPosition;
    }
    
    public boolean hasTargetScrollPosition() {
      boolean bool;
      if (this.mTargetPosition != -1) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isMeasuring() {
      return this.mIsMeasuring;
    }
    
    public boolean isPreLayout() {
      return this.mInPreLayout;
    }
    
    void prepareForNestedPrefetch(RecyclerView.Adapter param1Adapter) {
      this.mLayoutStep = 1;
      this.mItemCount = param1Adapter.getItemCount();
      this.mInPreLayout = false;
      this.mTrackOldChangeHolders = false;
      this.mIsMeasuring = false;
    }
    
    public void put(int param1Int, Object param1Object) {
      if (this.mData == null)
        this.mData = new SparseArray(); 
      this.mData.put(param1Int, param1Object);
    }
    
    public void remove(int param1Int) {
      if (this.mData == null)
        return; 
      this.mData.remove(param1Int);
    }
    
    State reset() {
      this.mTargetPosition = -1;
      if (this.mData != null)
        this.mData.clear(); 
      this.mItemCount = 0;
      this.mStructureChanged = false;
      this.mIsMeasuring = false;
      return this;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("State{mTargetPosition=");
      stringBuilder.append(this.mTargetPosition);
      stringBuilder.append(", mData=");
      stringBuilder.append(this.mData);
      stringBuilder.append(", mItemCount=");
      stringBuilder.append(this.mItemCount);
      stringBuilder.append(", mIsMeasuring=");
      stringBuilder.append(this.mIsMeasuring);
      stringBuilder.append(", mPreviousLayoutItemCount=");
      stringBuilder.append(this.mPreviousLayoutItemCount);
      stringBuilder.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
      stringBuilder.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
      stringBuilder.append(", mStructureChanged=");
      stringBuilder.append(this.mStructureChanged);
      stringBuilder.append(", mInPreLayout=");
      stringBuilder.append(this.mInPreLayout);
      stringBuilder.append(", mRunSimpleAnimations=");
      stringBuilder.append(this.mRunSimpleAnimations);
      stringBuilder.append(", mRunPredictiveAnimations=");
      stringBuilder.append(this.mRunPredictiveAnimations);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public boolean willRunPredictiveAnimations() {
      return this.mRunPredictiveAnimations;
    }
    
    public boolean willRunSimpleAnimations() {
      return this.mRunSimpleAnimations;
    }
  }
  
  public static abstract class ViewCacheExtension {
    @Nullable
    public abstract View getViewForPositionAndType(@NonNull RecyclerView.Recycler param1Recycler, int param1Int1, int param1Int2);
  }
  
  class ViewFlinger implements Runnable {
    private boolean mEatRunOnAnimationRequest = false;
    
    Interpolator mInterpolator = RecyclerView.sQuinticInterpolator;
    
    private int mLastFlingX;
    
    private int mLastFlingY;
    
    private boolean mReSchedulePostAnimationCallback = false;
    
    OverScroller mScroller = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
    
    private int computeScrollDuration(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      boolean bool;
      int i = Math.abs(param1Int1);
      int j = Math.abs(param1Int2);
      if (i > j) {
        bool = true;
      } else {
        bool = false;
      } 
      param1Int3 = (int)Math.sqrt((param1Int3 * param1Int3 + param1Int4 * param1Int4));
      param1Int2 = (int)Math.sqrt((param1Int1 * param1Int1 + param1Int2 * param1Int2));
      if (bool) {
        param1Int1 = RecyclerView.this.getWidth();
      } else {
        param1Int1 = RecyclerView.this.getHeight();
      } 
      param1Int4 = param1Int1 / 2;
      float f1 = param1Int2;
      float f2 = param1Int1;
      float f3 = Math.min(1.0F, f1 * 1.0F / f2);
      f1 = param1Int4;
      f3 = distanceInfluenceForSnapDuration(f3);
      if (param1Int3 > 0) {
        param1Int1 = Math.round(Math.abs((f1 + f3 * f1) / param1Int3) * 1000.0F) * 4;
      } else {
        if (bool) {
          param1Int1 = i;
        } else {
          param1Int1 = j;
        } 
        param1Int1 = (int)((param1Int1 / f2 + 1.0F) * 300.0F);
      } 
      return Math.min(param1Int1, 2000);
    }
    
    private void disableRunOnAnimationRequests() {
      this.mReSchedulePostAnimationCallback = false;
      this.mEatRunOnAnimationRequest = true;
    }
    
    private float distanceInfluenceForSnapDuration(float param1Float) {
      return (float)Math.sin(((param1Float - 0.5F) * 0.47123894F));
    }
    
    private void enableRunOnAnimationRequests() {
      this.mEatRunOnAnimationRequest = false;
      if (this.mReSchedulePostAnimationCallback)
        postOnAnimation(); 
    }
    
    public void fling(int param1Int1, int param1Int2) {
      RecyclerView.this.setScrollState(2);
      this.mLastFlingY = 0;
      this.mLastFlingX = 0;
      this.mScroller.fling(0, 0, param1Int1, param1Int2, -2147483648, 2147483647, -2147483648, 2147483647);
      postOnAnimation();
    }
    
    void postOnAnimation() {
      if (this.mEatRunOnAnimationRequest) {
        this.mReSchedulePostAnimationCallback = true;
      } else {
        RecyclerView.this.removeCallbacks(this);
        ViewCompat.postOnAnimation((View)RecyclerView.this, this);
      } 
    }
    
    public void run() {
      // Byte code:
      //   0: aload_0
      //   1: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   4: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   7: ifnonnull -> 15
      //   10: aload_0
      //   11: invokevirtual stop : ()V
      //   14: return
      //   15: aload_0
      //   16: invokespecial disableRunOnAnimationRequests : ()V
      //   19: aload_0
      //   20: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   23: invokevirtual consumePendingUpdateOperations : ()V
      //   26: aload_0
      //   27: getfield mScroller : Landroid/widget/OverScroller;
      //   30: astore_1
      //   31: aload_0
      //   32: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   35: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   38: getfield mSmoothScroller : Landroid/support/v7/widget/RecyclerView$SmoothScroller;
      //   41: astore_2
      //   42: aload_1
      //   43: invokevirtual computeScrollOffset : ()Z
      //   46: ifeq -> 856
      //   49: aload_0
      //   50: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   53: getfield mScrollConsumed : [I
      //   56: astore_3
      //   57: aload_1
      //   58: invokevirtual getCurrX : ()I
      //   61: istore #4
      //   63: aload_1
      //   64: invokevirtual getCurrY : ()I
      //   67: istore #5
      //   69: iload #4
      //   71: aload_0
      //   72: getfield mLastFlingX : I
      //   75: isub
      //   76: istore #6
      //   78: iload #5
      //   80: aload_0
      //   81: getfield mLastFlingY : I
      //   84: isub
      //   85: istore #7
      //   87: aload_0
      //   88: iload #4
      //   90: putfield mLastFlingX : I
      //   93: aload_0
      //   94: iload #5
      //   96: putfield mLastFlingY : I
      //   99: iload #6
      //   101: istore #8
      //   103: iload #7
      //   105: istore #9
      //   107: aload_0
      //   108: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   111: iload #6
      //   113: iload #7
      //   115: aload_3
      //   116: aconst_null
      //   117: iconst_1
      //   118: invokevirtual dispatchNestedPreScroll : (II[I[II)Z
      //   121: ifeq -> 140
      //   124: iload #6
      //   126: aload_3
      //   127: iconst_0
      //   128: iaload
      //   129: isub
      //   130: istore #8
      //   132: iload #7
      //   134: aload_3
      //   135: iconst_1
      //   136: iaload
      //   137: isub
      //   138: istore #9
      //   140: aload_0
      //   141: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   144: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   147: ifnull -> 393
      //   150: aload_0
      //   151: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   154: iload #8
      //   156: iload #9
      //   158: aload_0
      //   159: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   162: getfield mScrollStepConsumed : [I
      //   165: invokevirtual scrollStep : (II[I)V
      //   168: aload_0
      //   169: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   172: getfield mScrollStepConsumed : [I
      //   175: iconst_0
      //   176: iaload
      //   177: istore #6
      //   179: aload_0
      //   180: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   183: getfield mScrollStepConsumed : [I
      //   186: iconst_1
      //   187: iaload
      //   188: istore #10
      //   190: iload #8
      //   192: iload #6
      //   194: isub
      //   195: istore #7
      //   197: iload #9
      //   199: iload #10
      //   201: isub
      //   202: istore #11
      //   204: iload #6
      //   206: istore #12
      //   208: iload #10
      //   210: istore #13
      //   212: iload #7
      //   214: istore #14
      //   216: iload #11
      //   218: istore #15
      //   220: aload_2
      //   221: ifnull -> 416
      //   224: iload #6
      //   226: istore #12
      //   228: iload #10
      //   230: istore #13
      //   232: iload #7
      //   234: istore #14
      //   236: iload #11
      //   238: istore #15
      //   240: aload_2
      //   241: invokevirtual isPendingInitialRun : ()Z
      //   244: ifne -> 416
      //   247: iload #6
      //   249: istore #12
      //   251: iload #10
      //   253: istore #13
      //   255: iload #7
      //   257: istore #14
      //   259: iload #11
      //   261: istore #15
      //   263: aload_2
      //   264: invokevirtual isRunning : ()Z
      //   267: ifeq -> 416
      //   270: aload_0
      //   271: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   274: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   277: invokevirtual getItemCount : ()I
      //   280: istore #12
      //   282: iload #12
      //   284: ifne -> 310
      //   287: aload_2
      //   288: invokevirtual stop : ()V
      //   291: iload #6
      //   293: istore #12
      //   295: iload #10
      //   297: istore #13
      //   299: iload #7
      //   301: istore #14
      //   303: iload #11
      //   305: istore #15
      //   307: goto -> 416
      //   310: aload_2
      //   311: invokevirtual getTargetPosition : ()I
      //   314: iload #12
      //   316: if_icmplt -> 360
      //   319: aload_2
      //   320: iload #12
      //   322: iconst_1
      //   323: isub
      //   324: invokevirtual setTargetPosition : (I)V
      //   327: aload_2
      //   328: iload #8
      //   330: iload #7
      //   332: isub
      //   333: iload #9
      //   335: iload #11
      //   337: isub
      //   338: invokevirtual onAnimation : (II)V
      //   341: iload #6
      //   343: istore #12
      //   345: iload #10
      //   347: istore #13
      //   349: iload #7
      //   351: istore #14
      //   353: iload #11
      //   355: istore #15
      //   357: goto -> 416
      //   360: aload_2
      //   361: iload #8
      //   363: iload #7
      //   365: isub
      //   366: iload #9
      //   368: iload #11
      //   370: isub
      //   371: invokevirtual onAnimation : (II)V
      //   374: iload #6
      //   376: istore #12
      //   378: iload #10
      //   380: istore #13
      //   382: iload #7
      //   384: istore #14
      //   386: iload #11
      //   388: istore #15
      //   390: goto -> 416
      //   393: iconst_0
      //   394: istore #12
      //   396: iload #12
      //   398: istore #7
      //   400: iload #7
      //   402: istore #6
      //   404: iload #6
      //   406: istore #15
      //   408: iload #6
      //   410: istore #14
      //   412: iload #7
      //   414: istore #13
      //   416: aload_0
      //   417: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   420: getfield mItemDecorations : Ljava/util/ArrayList;
      //   423: invokevirtual isEmpty : ()Z
      //   426: ifne -> 436
      //   429: aload_0
      //   430: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   433: invokevirtual invalidate : ()V
      //   436: aload_0
      //   437: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   440: invokevirtual getOverScrollMode : ()I
      //   443: iconst_2
      //   444: if_icmpeq -> 458
      //   447: aload_0
      //   448: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   451: iload #8
      //   453: iload #9
      //   455: invokevirtual considerReleasingGlowsOnScroll : (II)V
      //   458: aload_0
      //   459: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   462: iload #12
      //   464: iload #13
      //   466: iload #14
      //   468: iload #15
      //   470: aconst_null
      //   471: iconst_1
      //   472: invokevirtual dispatchNestedScroll : (IIII[II)Z
      //   475: ifne -> 625
      //   478: iload #14
      //   480: ifne -> 488
      //   483: iload #15
      //   485: ifeq -> 625
      //   488: aload_1
      //   489: invokevirtual getCurrVelocity : ()F
      //   492: f2i
      //   493: istore #6
      //   495: iload #14
      //   497: iload #4
      //   499: if_icmpeq -> 527
      //   502: iload #14
      //   504: ifge -> 515
      //   507: iload #6
      //   509: ineg
      //   510: istore #7
      //   512: goto -> 530
      //   515: iload #14
      //   517: ifle -> 527
      //   520: iload #6
      //   522: istore #7
      //   524: goto -> 530
      //   527: iconst_0
      //   528: istore #7
      //   530: iload #15
      //   532: iload #5
      //   534: if_icmpeq -> 558
      //   537: iload #15
      //   539: ifge -> 550
      //   542: iload #6
      //   544: ineg
      //   545: istore #6
      //   547: goto -> 561
      //   550: iload #15
      //   552: ifle -> 558
      //   555: goto -> 561
      //   558: iconst_0
      //   559: istore #6
      //   561: aload_0
      //   562: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   565: invokevirtual getOverScrollMode : ()I
      //   568: iconst_2
      //   569: if_icmpeq -> 583
      //   572: aload_0
      //   573: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   576: iload #7
      //   578: iload #6
      //   580: invokevirtual absorbGlows : (II)V
      //   583: iload #7
      //   585: ifne -> 602
      //   588: iload #14
      //   590: iload #4
      //   592: if_icmpeq -> 602
      //   595: aload_1
      //   596: invokevirtual getFinalX : ()I
      //   599: ifne -> 625
      //   602: iload #6
      //   604: ifne -> 621
      //   607: iload #15
      //   609: iload #5
      //   611: if_icmpeq -> 621
      //   614: aload_1
      //   615: invokevirtual getFinalY : ()I
      //   618: ifne -> 625
      //   621: aload_1
      //   622: invokevirtual abortAnimation : ()V
      //   625: iload #12
      //   627: ifne -> 635
      //   630: iload #13
      //   632: ifeq -> 646
      //   635: aload_0
      //   636: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   639: iload #12
      //   641: iload #13
      //   643: invokevirtual dispatchOnScrolled : (II)V
      //   646: aload_0
      //   647: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   650: invokestatic access$200 : (Landroid/support/v7/widget/RecyclerView;)Z
      //   653: ifne -> 663
      //   656: aload_0
      //   657: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   660: invokevirtual invalidate : ()V
      //   663: iload #9
      //   665: ifeq -> 694
      //   668: aload_0
      //   669: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   672: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   675: invokevirtual canScrollVertically : ()Z
      //   678: ifeq -> 694
      //   681: iload #13
      //   683: iload #9
      //   685: if_icmpne -> 694
      //   688: iconst_1
      //   689: istore #7
      //   691: goto -> 697
      //   694: iconst_0
      //   695: istore #7
      //   697: iload #8
      //   699: ifeq -> 728
      //   702: aload_0
      //   703: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   706: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   709: invokevirtual canScrollHorizontally : ()Z
      //   712: ifeq -> 728
      //   715: iload #12
      //   717: iload #8
      //   719: if_icmpne -> 728
      //   722: iconst_1
      //   723: istore #6
      //   725: goto -> 731
      //   728: iconst_0
      //   729: istore #6
      //   731: iload #8
      //   733: ifne -> 741
      //   736: iload #9
      //   738: ifeq -> 760
      //   741: iload #6
      //   743: ifne -> 760
      //   746: iload #7
      //   748: ifeq -> 754
      //   751: goto -> 760
      //   754: iconst_0
      //   755: istore #7
      //   757: goto -> 763
      //   760: iconst_1
      //   761: istore #7
      //   763: aload_1
      //   764: invokevirtual isFinished : ()Z
      //   767: ifne -> 824
      //   770: iload #7
      //   772: ifne -> 789
      //   775: aload_0
      //   776: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   779: iconst_1
      //   780: invokevirtual hasNestedScrollingParent : (I)Z
      //   783: ifne -> 789
      //   786: goto -> 824
      //   789: aload_0
      //   790: invokevirtual postOnAnimation : ()V
      //   793: aload_0
      //   794: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   797: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
      //   800: ifnull -> 856
      //   803: aload_0
      //   804: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   807: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
      //   810: aload_0
      //   811: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   814: iload #8
      //   816: iload #9
      //   818: invokevirtual postFromTraversal : (Landroid/support/v7/widget/RecyclerView;II)V
      //   821: goto -> 856
      //   824: aload_0
      //   825: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   828: iconst_0
      //   829: invokevirtual setScrollState : (I)V
      //   832: getstatic android/support/v7/widget/RecyclerView.ALLOW_THREAD_GAP_WORK : Z
      //   835: ifeq -> 848
      //   838: aload_0
      //   839: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   842: getfield mPrefetchRegistry : Landroid/support/v7/widget/GapWorker$LayoutPrefetchRegistryImpl;
      //   845: invokevirtual clearPrefetchPositions : ()V
      //   848: aload_0
      //   849: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   852: iconst_1
      //   853: invokevirtual stopNestedScroll : (I)V
      //   856: aload_2
      //   857: ifnull -> 884
      //   860: aload_2
      //   861: invokevirtual isPendingInitialRun : ()Z
      //   864: ifeq -> 873
      //   867: aload_2
      //   868: iconst_0
      //   869: iconst_0
      //   870: invokevirtual onAnimation : (II)V
      //   873: aload_0
      //   874: getfield mReSchedulePostAnimationCallback : Z
      //   877: ifne -> 884
      //   880: aload_2
      //   881: invokevirtual stop : ()V
      //   884: aload_0
      //   885: invokespecial enableRunOnAnimationRequests : ()V
      //   888: return
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2) {
      smoothScrollBy(param1Int1, param1Int2, 0, 0);
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, int param1Int3) {
      smoothScrollBy(param1Int1, param1Int2, param1Int3, RecyclerView.sQuinticInterpolator);
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      smoothScrollBy(param1Int1, param1Int2, computeScrollDuration(param1Int1, param1Int2, param1Int3, param1Int4));
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, int param1Int3, Interpolator param1Interpolator) {
      if (this.mInterpolator != param1Interpolator) {
        this.mInterpolator = param1Interpolator;
        this.mScroller = new OverScroller(RecyclerView.this.getContext(), param1Interpolator);
      } 
      RecyclerView.this.setScrollState(2);
      this.mLastFlingY = 0;
      this.mLastFlingX = 0;
      this.mScroller.startScroll(0, 0, param1Int1, param1Int2, param1Int3);
      if (Build.VERSION.SDK_INT < 23)
        this.mScroller.computeScrollOffset(); 
      postOnAnimation();
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, Interpolator param1Interpolator) {
      int i = computeScrollDuration(param1Int1, param1Int2, 0, 0);
      Interpolator interpolator = param1Interpolator;
      if (param1Interpolator == null)
        interpolator = RecyclerView.sQuinticInterpolator; 
      smoothScrollBy(param1Int1, param1Int2, i, interpolator);
    }
    
    public void stop() {
      RecyclerView.this.removeCallbacks(this);
      this.mScroller.abortAnimation();
    }
  }
  
  public static abstract class ViewHolder {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    
    static final int FLAG_BOUND = 1;
    
    static final int FLAG_IGNORE = 128;
    
    static final int FLAG_INVALID = 4;
    
    static final int FLAG_MOVED = 2048;
    
    static final int FLAG_NOT_RECYCLABLE = 16;
    
    static final int FLAG_REMOVED = 8;
    
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    
    static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
    
    static final int FLAG_TMP_DETACHED = 256;
    
    static final int FLAG_UPDATE = 2;
    
    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
    
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    
    @NonNull
    public final View itemView;
    
    int mFlags;
    
    boolean mInChangeScrap = false;
    
    private int mIsRecyclableCount = 0;
    
    long mItemId = -1L;
    
    int mItemViewType = -1;
    
    WeakReference<RecyclerView> mNestedRecyclerView;
    
    int mOldPosition = -1;
    
    RecyclerView mOwnerRecyclerView;
    
    List<Object> mPayloads = null;
    
    @VisibleForTesting
    int mPendingAccessibilityState = -1;
    
    int mPosition = -1;
    
    int mPreLayoutPosition = -1;
    
    RecyclerView.Recycler mScrapContainer = null;
    
    ViewHolder mShadowedHolder = null;
    
    ViewHolder mShadowingHolder = null;
    
    List<Object> mUnmodifiedPayloads = null;
    
    private int mWasImportantForAccessibilityBeforeHidden = 0;
    
    public ViewHolder(@NonNull View param1View) {
      if (param1View == null)
        throw new IllegalArgumentException("itemView may not be null"); 
      this.itemView = param1View;
    }
    
    private void createPayloadsIfNeeded() {
      if (this.mPayloads == null) {
        this.mPayloads = new ArrayList();
        this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
      } 
    }
    
    void addChangePayload(Object param1Object) {
      if (param1Object == null) {
        addFlags(1024);
      } else if ((0x400 & this.mFlags) == 0) {
        createPayloadsIfNeeded();
        this.mPayloads.add(param1Object);
      } 
    }
    
    void addFlags(int param1Int) {
      this.mFlags = param1Int | this.mFlags;
    }
    
    void clearOldPosition() {
      this.mOldPosition = -1;
      this.mPreLayoutPosition = -1;
    }
    
    void clearPayload() {
      if (this.mPayloads != null)
        this.mPayloads.clear(); 
      this.mFlags &= 0xFFFFFBFF;
    }
    
    void clearReturnedFromScrapFlag() {
      this.mFlags &= 0xFFFFFFDF;
    }
    
    void clearTmpDetachFlag() {
      this.mFlags &= 0xFFFFFEFF;
    }
    
    boolean doesTransientStatePreventRecycling() {
      boolean bool;
      if ((this.mFlags & 0x10) == 0 && ViewCompat.hasTransientState(this.itemView)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void flagRemovedAndOffsetPosition(int param1Int1, int param1Int2, boolean param1Boolean) {
      addFlags(8);
      offsetPosition(param1Int2, param1Boolean);
      this.mPosition = param1Int1;
    }
    
    public final int getAdapterPosition() {
      return (this.mOwnerRecyclerView == null) ? -1 : this.mOwnerRecyclerView.getAdapterPositionFor(this);
    }
    
    public final long getItemId() {
      return this.mItemId;
    }
    
    public final int getItemViewType() {
      return this.mItemViewType;
    }
    
    public final int getLayoutPosition() {
      int i;
      if (this.mPreLayoutPosition == -1) {
        i = this.mPosition;
      } else {
        i = this.mPreLayoutPosition;
      } 
      return i;
    }
    
    public final int getOldPosition() {
      return this.mOldPosition;
    }
    
    @Deprecated
    public final int getPosition() {
      int i;
      if (this.mPreLayoutPosition == -1) {
        i = this.mPosition;
      } else {
        i = this.mPreLayoutPosition;
      } 
      return i;
    }
    
    List<Object> getUnmodifiedPayloads() {
      return ((this.mFlags & 0x400) == 0) ? ((this.mPayloads == null || this.mPayloads.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads) : FULLUPDATE_PAYLOADS;
    }
    
    boolean hasAnyOfTheFlags(int param1Int) {
      boolean bool;
      if ((this.mFlags & param1Int) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isAdapterPositionUnknown() {
      return ((this.mFlags & 0x200) != 0 || isInvalid());
    }
    
    boolean isBound() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    boolean isInvalid() {
      boolean bool;
      if ((this.mFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public final boolean isRecyclable() {
      boolean bool;
      if ((this.mFlags & 0x10) == 0 && !ViewCompat.hasTransientState(this.itemView)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isRemoved() {
      boolean bool;
      if ((this.mFlags & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isScrap() {
      boolean bool;
      if (this.mScrapContainer != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isTmpDetached() {
      boolean bool;
      if ((this.mFlags & 0x100) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isUpdated() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean needsUpdate() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void offsetPosition(int param1Int, boolean param1Boolean) {
      if (this.mOldPosition == -1)
        this.mOldPosition = this.mPosition; 
      if (this.mPreLayoutPosition == -1)
        this.mPreLayoutPosition = this.mPosition; 
      if (param1Boolean)
        this.mPreLayoutPosition += param1Int; 
      this.mPosition += param1Int;
      if (this.itemView.getLayoutParams() != null)
        ((RecyclerView.LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true; 
    }
    
    void onEnteredHiddenState(RecyclerView param1RecyclerView) {
      if (this.mPendingAccessibilityState != -1) {
        this.mWasImportantForAccessibilityBeforeHidden = this.mPendingAccessibilityState;
      } else {
        this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
      } 
      param1RecyclerView.setChildImportantForAccessibilityInternal(this, 4);
    }
    
    void onLeftHiddenState(RecyclerView param1RecyclerView) {
      param1RecyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
      this.mWasImportantForAccessibilityBeforeHidden = 0;
    }
    
    void resetInternal() {
      this.mFlags = 0;
      this.mPosition = -1;
      this.mOldPosition = -1;
      this.mItemId = -1L;
      this.mPreLayoutPosition = -1;
      this.mIsRecyclableCount = 0;
      this.mShadowedHolder = null;
      this.mShadowingHolder = null;
      clearPayload();
      this.mWasImportantForAccessibilityBeforeHidden = 0;
      this.mPendingAccessibilityState = -1;
      RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }
    
    void saveOldPosition() {
      if (this.mOldPosition == -1)
        this.mOldPosition = this.mPosition; 
    }
    
    void setFlags(int param1Int1, int param1Int2) {
      this.mFlags = param1Int1 & param1Int2 | this.mFlags & (param1Int2 ^ 0xFFFFFFFF);
    }
    
    public final void setIsRecyclable(boolean param1Boolean) {
      int i;
      if (param1Boolean) {
        i = this.mIsRecyclableCount - 1;
      } else {
        i = this.mIsRecyclableCount + 1;
      } 
      this.mIsRecyclableCount = i;
      if (this.mIsRecyclableCount < 0) {
        this.mIsRecyclableCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ");
        stringBuilder.append(this);
        Log.e("View", stringBuilder.toString());
      } else if (!param1Boolean && this.mIsRecyclableCount == 1) {
        this.mFlags |= 0x10;
      } else if (param1Boolean && this.mIsRecyclableCount == 0) {
        this.mFlags &= 0xFFFFFFEF;
      } 
    }
    
    void setScrapContainer(RecyclerView.Recycler param1Recycler, boolean param1Boolean) {
      this.mScrapContainer = param1Recycler;
      this.mInChangeScrap = param1Boolean;
    }
    
    boolean shouldBeKeptAsChild() {
      boolean bool;
      if ((this.mFlags & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean shouldIgnore() {
      boolean bool;
      if ((this.mFlags & 0x80) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void stopIgnoring() {
      this.mFlags &= 0xFFFFFF7F;
    }
    
    public String toString() {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("ViewHolder{");
      stringBuilder1.append(Integer.toHexString(hashCode()));
      stringBuilder1.append(" position=");
      stringBuilder1.append(this.mPosition);
      stringBuilder1.append(" id=");
      stringBuilder1.append(this.mItemId);
      stringBuilder1.append(", oldPos=");
      stringBuilder1.append(this.mOldPosition);
      stringBuilder1.append(", pLpos:");
      stringBuilder1.append(this.mPreLayoutPosition);
      StringBuilder stringBuilder2 = new StringBuilder(stringBuilder1.toString());
      if (isScrap()) {
        String str;
        stringBuilder2.append(" scrap ");
        if (this.mInChangeScrap) {
          str = "[changeScrap]";
        } else {
          str = "[attachedScrap]";
        } 
        stringBuilder2.append(str);
      } 
      if (isInvalid())
        stringBuilder2.append(" invalid"); 
      if (!isBound())
        stringBuilder2.append(" unbound"); 
      if (needsUpdate())
        stringBuilder2.append(" update"); 
      if (isRemoved())
        stringBuilder2.append(" removed"); 
      if (shouldIgnore())
        stringBuilder2.append(" ignored"); 
      if (isTmpDetached())
        stringBuilder2.append(" tmpDetached"); 
      if (!isRecyclable()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(" not recyclable(");
        stringBuilder1.append(this.mIsRecyclableCount);
        stringBuilder1.append(")");
        stringBuilder2.append(stringBuilder1.toString());
      } 
      if (isAdapterPositionUnknown())
        stringBuilder2.append(" undefined adapter position"); 
      if (this.itemView.getParent() == null)
        stringBuilder2.append(" no parent"); 
      stringBuilder2.append("}");
      return stringBuilder2.toString();
    }
    
    void unScrap() {
      this.mScrapContainer.unscrapView(this);
    }
    
    boolean wasReturnedFromScrap() {
      boolean bool;
      if ((this.mFlags & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\RecyclerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */