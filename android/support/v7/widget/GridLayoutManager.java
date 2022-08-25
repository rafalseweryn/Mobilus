package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
  private static final boolean DEBUG = false;
  
  public static final int DEFAULT_SPAN_COUNT = -1;
  
  private static final String TAG = "GridLayoutManager";
  
  int[] mCachedBorders;
  
  final Rect mDecorInsets = new Rect();
  
  boolean mPendingSpanCountChange = false;
  
  final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
  
  final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
  
  View[] mSet;
  
  int mSpanCount = -1;
  
  SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();
  
  public GridLayoutManager(Context paramContext, int paramInt) {
    super(paramContext);
    setSpanCount(paramInt);
  }
  
  public GridLayoutManager(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean) {
    super(paramContext, paramInt2, paramBoolean);
    setSpanCount(paramInt1);
  }
  
  public GridLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setSpanCount((getProperties(paramContext, paramAttributeSet, paramInt1, paramInt2)).spanCount);
  }
  
  private void assignSpans(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, boolean paramBoolean) {
    int i = -1;
    int j = 0;
    if (paramBoolean) {
      paramInt2 = 1;
      i = paramInt1;
      paramInt1 = 0;
    } else {
      paramInt1--;
      paramInt2 = -1;
    } 
    while (paramInt1 != i) {
      View view = this.mSet[paramInt1];
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      layoutParams.mSpanSize = getSpanSize(paramRecycler, paramState, getPosition(view));
      layoutParams.mSpanIndex = j;
      j += layoutParams.mSpanSize;
      paramInt1 += paramInt2;
    } 
  }
  
  private void cachePreLayoutSpanMapping() {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      LayoutParams layoutParams = (LayoutParams)getChildAt(b).getLayoutParams();
      int j = layoutParams.getViewLayoutPosition();
      this.mPreLayoutSpanSizeCache.put(j, layoutParams.getSpanSize());
      this.mPreLayoutSpanIndexCache.put(j, layoutParams.getSpanIndex());
    } 
  }
  
  private void calculateItemBorders(int paramInt) {
    this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, paramInt);
  }
  
  static int[] calculateItemBorders(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_0
    //   3: ifnull -> 27
    //   6: aload_0
    //   7: arraylength
    //   8: iload_1
    //   9: iconst_1
    //   10: iadd
    //   11: if_icmpne -> 27
    //   14: aload_0
    //   15: astore #4
    //   17: aload_0
    //   18: aload_0
    //   19: arraylength
    //   20: iconst_1
    //   21: isub
    //   22: iaload
    //   23: iload_2
    //   24: if_icmpeq -> 34
    //   27: iload_1
    //   28: iconst_1
    //   29: iadd
    //   30: newarray int
    //   32: astore #4
    //   34: iconst_0
    //   35: istore #5
    //   37: aload #4
    //   39: iconst_0
    //   40: iconst_0
    //   41: iastore
    //   42: iload_2
    //   43: iload_1
    //   44: idiv
    //   45: istore #6
    //   47: iload_2
    //   48: iload_1
    //   49: irem
    //   50: istore #7
    //   52: iconst_0
    //   53: istore #8
    //   55: iload #5
    //   57: istore_2
    //   58: iload_3
    //   59: iload_1
    //   60: if_icmpgt -> 116
    //   63: iload_2
    //   64: iload #7
    //   66: iadd
    //   67: istore_2
    //   68: iload_2
    //   69: ifle -> 93
    //   72: iload_1
    //   73: iload_2
    //   74: isub
    //   75: iload #7
    //   77: if_icmpge -> 93
    //   80: iload #6
    //   82: iconst_1
    //   83: iadd
    //   84: istore #5
    //   86: iload_2
    //   87: iload_1
    //   88: isub
    //   89: istore_2
    //   90: goto -> 97
    //   93: iload #6
    //   95: istore #5
    //   97: iload #8
    //   99: iload #5
    //   101: iadd
    //   102: istore #8
    //   104: aload #4
    //   106: iload_3
    //   107: iload #8
    //   109: iastore
    //   110: iinc #3, 1
    //   113: goto -> 58
    //   116: aload #4
    //   118: areturn
  }
  
  private void clearPreLayoutSpanMappingCache() {
    this.mPreLayoutSpanSizeCache.clear();
    this.mPreLayoutSpanIndexCache.clear();
  }
  
  private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.AnchorInfo paramAnchorInfo, int paramInt) {
    if (paramInt == 1) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    int i = getSpanIndex(paramRecycler, paramState, paramAnchorInfo.mPosition);
    if (paramInt != 0) {
      while (i > 0 && paramAnchorInfo.mPosition > 0) {
        paramAnchorInfo.mPosition--;
        i = getSpanIndex(paramRecycler, paramState, paramAnchorInfo.mPosition);
      } 
    } else {
      int j = paramState.getItemCount();
      paramInt = paramAnchorInfo.mPosition;
      while (paramInt < j - 1) {
        int k = paramInt + 1;
        int m = getSpanIndex(paramRecycler, paramState, k);
        if (m > i) {
          paramInt = k;
          i = m;
        } 
      } 
      paramAnchorInfo.mPosition = paramInt;
    } 
  }
  
  private void ensureViewSet() {
    if (this.mSet == null || this.mSet.length != this.mSpanCount)
      this.mSet = new View[this.mSpanCount]; 
  }
  
  private int getSpanGroupIndex(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt) {
    if (!paramState.isPreLayout())
      return this.mSpanSizeLookup.getSpanGroupIndex(paramInt, this.mSpanCount); 
    int i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot find span size for pre layout position. ");
      stringBuilder.append(paramInt);
      Log.w("GridLayoutManager", stringBuilder.toString());
      return 0;
    } 
    return this.mSpanSizeLookup.getSpanGroupIndex(i, this.mSpanCount);
  }
  
  private int getSpanIndex(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt) {
    if (!paramState.isPreLayout())
      return this.mSpanSizeLookup.getCachedSpanIndex(paramInt, this.mSpanCount); 
    int i = this.mPreLayoutSpanIndexCache.get(paramInt, -1);
    if (i != -1)
      return i; 
    i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
      stringBuilder.append(paramInt);
      Log.w("GridLayoutManager", stringBuilder.toString());
      return 0;
    } 
    return this.mSpanSizeLookup.getCachedSpanIndex(i, this.mSpanCount);
  }
  
  private int getSpanSize(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt) {
    if (!paramState.isPreLayout())
      return this.mSpanSizeLookup.getSpanSize(paramInt); 
    int i = this.mPreLayoutSpanSizeCache.get(paramInt, -1);
    if (i != -1)
      return i; 
    i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
      stringBuilder.append(paramInt);
      Log.w("GridLayoutManager", stringBuilder.toString());
      return 1;
    } 
    return this.mSpanSizeLookup.getSpanSize(i);
  }
  
  private void guessMeasurement(float paramFloat, int paramInt) {
    calculateItemBorders(Math.max(Math.round(paramFloat * this.mSpanCount), paramInt));
  }
  
  private void measureChild(View paramView, int paramInt, boolean paramBoolean) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect rect = layoutParams.mDecorInsets;
    int i = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
    int j = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
    int k = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
    if (this.mOrientation == 1) {
      j = getChildMeasureSpec(k, paramInt, j, layoutParams.width, false);
      paramInt = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), i, layoutParams.height, true);
    } else {
      paramInt = getChildMeasureSpec(k, paramInt, i, layoutParams.height, false);
      j = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), j, layoutParams.width, true);
    } 
    measureChildWithDecorationsAndMargin(paramView, j, paramInt, paramBoolean);
  }
  
  private void measureChildWithDecorationsAndMargin(View paramView, int paramInt1, int paramInt2, boolean paramBoolean) {
    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    if (paramBoolean) {
      paramBoolean = shouldReMeasureChild(paramView, paramInt1, paramInt2, layoutParams);
    } else {
      paramBoolean = shouldMeasureChild(paramView, paramInt1, paramInt2, layoutParams);
    } 
    if (paramBoolean)
      paramView.measure(paramInt1, paramInt2); 
  }
  
  private void updateMeasurements() {
    int i;
    if (getOrientation() == 1) {
      i = getWidth() - getPaddingRight() - getPaddingLeft();
    } else {
      i = getHeight() - getPaddingBottom() - getPaddingTop();
    } 
    calculateItemBorders(i);
  }
  
  public boolean checkLayoutParams(RecyclerView.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void collectPrefetchPositionsForLayoutState(RecyclerView.State paramState, LinearLayoutManager.LayoutState paramLayoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry) {
    int i = this.mSpanCount;
    for (byte b = 0; b < this.mSpanCount && paramLayoutState.hasMore(paramState) && i > 0; b++) {
      int j = paramLayoutState.mCurrentPosition;
      paramLayoutPrefetchRegistry.addPosition(j, Math.max(0, paramLayoutState.mScrollingOffset));
      i -= this.mSpanSizeLookup.getSpanSize(j);
      paramLayoutState.mCurrentPosition += paramLayoutState.mItemDirection;
    } 
  }
  
  View findReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, int paramInt3) {
    byte b;
    ensureLayoutState();
    int i = this.mOrientationHelper.getStartAfterPadding();
    int j = this.mOrientationHelper.getEndAfterPadding();
    if (paramInt2 > paramInt1) {
      b = 1;
    } else {
      b = -1;
    } 
    View view1 = null;
    View view2;
    for (view2 = null; paramInt1 != paramInt2; view2 = view5) {
      View view3 = getChildAt(paramInt1);
      int k = getPosition(view3);
      View view4 = view1;
      View view5 = view2;
      if (k >= 0) {
        view4 = view1;
        view5 = view2;
        if (k < paramInt3)
          if (getSpanIndex(paramRecycler, paramState, k) != 0) {
            view4 = view1;
            view5 = view2;
          } else if (((RecyclerView.LayoutParams)view3.getLayoutParams()).isItemRemoved()) {
            view4 = view1;
            view5 = view2;
            if (view2 == null) {
              view5 = view3;
              view4 = view1;
            } 
          } else if (this.mOrientationHelper.getDecoratedStart(view3) >= j || this.mOrientationHelper.getDecoratedEnd(view3) < i) {
            view4 = view1;
            view5 = view2;
            if (view1 == null) {
              view4 = view3;
              view5 = view2;
            } 
          } else {
            return view3;
          }  
      } 
      paramInt1 += b;
      view1 = view4;
    } 
    if (view1 == null)
      view1 = view2; 
    return view1;
  }
  
  public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return (this.mOrientation == 0) ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
  }
  
  public RecyclerView.LayoutParams generateLayoutParams(Context paramContext, AttributeSet paramAttributeSet) {
    return new LayoutParams(paramContext, paramAttributeSet);
  }
  
  public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (paramLayoutParams instanceof ViewGroup.MarginLayoutParams) ? new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams) : new LayoutParams(paramLayoutParams);
  }
  
  public int getColumnCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    return (this.mOrientation == 1) ? this.mSpanCount : ((paramState.getItemCount() < 1) ? 0 : (getSpanGroupIndex(paramRecycler, paramState, paramState.getItemCount() - 1) + 1));
  }
  
  public int getRowCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    return (this.mOrientation == 0) ? this.mSpanCount : ((paramState.getItemCount() < 1) ? 0 : (getSpanGroupIndex(paramRecycler, paramState, paramState.getItemCount() - 1) + 1));
  }
  
  int getSpaceForSpanRange(int paramInt1, int paramInt2) {
    return (this.mOrientation == 1 && isLayoutRTL()) ? (this.mCachedBorders[this.mSpanCount - paramInt1] - this.mCachedBorders[this.mSpanCount - paramInt1 - paramInt2]) : (this.mCachedBorders[paramInt2 + paramInt1] - this.mCachedBorders[paramInt1]);
  }
  
  public int getSpanCount() {
    return this.mSpanCount;
  }
  
  public SpanSizeLookup getSpanSizeLookup() {
    return this.mSpanSizeLookup;
  }
  
  void layoutChunk(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.LayoutState paramLayoutState, LinearLayoutManager.LayoutChunkResult paramLayoutChunkResult) {
    StringBuilder stringBuilder;
    int j;
    int k;
    boolean bool;
    int i = this.mOrientationHelper.getModeInOther();
    if (i != 1073741824) {
      j = 1;
    } else {
      j = 0;
    } 
    if (getChildCount() > 0) {
      k = this.mCachedBorders[this.mSpanCount];
    } else {
      k = 0;
    } 
    if (j)
      updateMeasurements(); 
    if (paramLayoutState.mItemDirection == 1) {
      bool = true;
    } else {
      bool = false;
    } 
    int m = this.mSpanCount;
    if (!bool)
      m = getSpanIndex(paramRecycler, paramState, paramLayoutState.mCurrentPosition) + getSpanSize(paramRecycler, paramState, paramLayoutState.mCurrentPosition); 
    int n = 0;
    byte b;
    for (b = 0; b < this.mSpanCount && paramLayoutState.hasMore(paramState) && m > 0; b++) {
      int i3 = paramLayoutState.mCurrentPosition;
      int i4 = getSpanSize(paramRecycler, paramState, i3);
      if (i4 > this.mSpanCount) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Item at position ");
        stringBuilder.append(i3);
        stringBuilder.append(" requires ");
        stringBuilder.append(i4);
        stringBuilder.append(" spans but GridLayoutManager has only ");
        stringBuilder.append(this.mSpanCount);
        stringBuilder.append(" spans.");
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      m -= i4;
      if (m < 0)
        break; 
      View view = paramLayoutState.next((RecyclerView.Recycler)stringBuilder);
      if (view == null)
        break; 
      n += i4;
      this.mSet[b] = view;
    } 
    if (b == 0) {
      paramLayoutChunkResult.mFinished = true;
      return;
    } 
    float f = 0.0F;
    assignSpans((RecyclerView.Recycler)stringBuilder, paramState, b, n, bool);
    int i2 = 0;
    m = 0;
    while (i2 < b) {
      View view = this.mSet[i2];
      if (paramLayoutState.mScrapList == null) {
        if (bool) {
          addView(view);
        } else {
          addView(view, 0);
        } 
      } else if (bool) {
        addDisappearingView(view);
      } else {
        addDisappearingView(view, 0);
      } 
      calculateItemDecorationsForChild(view, this.mDecorInsets);
      measureChild(view, i, false);
      int i3 = this.mOrientationHelper.getDecoratedMeasurement(view);
      n = m;
      if (i3 > m)
        n = i3; 
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      float f1 = this.mOrientationHelper.getDecoratedMeasurementInOther(view) * 1.0F / layoutParams.mSpanSize;
      float f2 = f;
      if (f1 > f)
        f2 = f1; 
      i2++;
      m = n;
      f = f2;
    } 
    n = m;
    if (j) {
      guessMeasurement(f, k);
      j = 0;
      m = 0;
      while (true) {
        n = m;
        if (j < b) {
          View view = this.mSet[j];
          measureChild(view, 1073741824, true);
          k = this.mOrientationHelper.getDecoratedMeasurement(view);
          n = m;
          if (k > m)
            n = k; 
          j++;
          m = n;
          continue;
        } 
        break;
      } 
    } 
    for (m = 0; m < b; m++) {
      View view = this.mSet[m];
      if (this.mOrientationHelper.getDecoratedMeasurement(view) != n) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        Rect rect = layoutParams.mDecorInsets;
        k = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        j = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
        i2 = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        if (this.mOrientation == 1) {
          j = getChildMeasureSpec(i2, 1073741824, j, layoutParams.width, false);
          k = View.MeasureSpec.makeMeasureSpec(n - k, 1073741824);
        } else {
          j = View.MeasureSpec.makeMeasureSpec(n - j, 1073741824);
          k = getChildMeasureSpec(i2, 1073741824, k, layoutParams.height, false);
        } 
        measureChildWithDecorationsAndMargin(view, j, k, true);
      } 
    } 
    int i1 = 0;
    paramLayoutChunkResult.mConsumed = n;
    if (this.mOrientation == 1) {
      if (paramLayoutState.mLayoutDirection == -1) {
        j = paramLayoutState.mOffset;
        m = j;
        n = j - n;
      } else {
        j = paramLayoutState.mOffset;
        m = j;
        j = n + j;
        n = m;
        m = j;
      } 
      j = 0;
      k = j;
      i2 = i1;
    } else if (paramLayoutState.mLayoutDirection == -1) {
      j = paramLayoutState.mOffset;
      i2 = 0;
      m = i2;
      k = j;
      j -= n;
      n = i2;
      i2 = i1;
    } else {
      j = paramLayoutState.mOffset;
      k = n + j;
      n = 0;
      m = n;
      i2 = i1;
    } 
    while (i2 < b) {
      View view = this.mSet[i2];
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      if (this.mOrientation == 1) {
        if (isLayoutRTL()) {
          k = getPaddingLeft() + this.mCachedBorders[this.mSpanCount - layoutParams.mSpanIndex];
          i1 = this.mOrientationHelper.getDecoratedMeasurementInOther(view);
          j = k;
          k -= i1;
        } else {
          j = getPaddingLeft() + this.mCachedBorders[layoutParams.mSpanIndex];
          k = this.mOrientationHelper.getDecoratedMeasurementInOther(view) + j;
          i1 = j;
          j = k;
          k = i1;
        } 
      } else {
        n = getPaddingTop() + this.mCachedBorders[layoutParams.mSpanIndex];
        m = this.mOrientationHelper.getDecoratedMeasurementInOther(view) + n;
        i1 = j;
        j = k;
        k = i1;
      } 
      layoutDecoratedWithMargins(view, k, n, j, m);
      if (layoutParams.isItemRemoved() || layoutParams.isItemChanged())
        paramLayoutChunkResult.mIgnoreConsumed = true; 
      paramLayoutChunkResult.mFocusable |= view.hasFocusable();
      i1 = i2 + 1;
      i2 = j;
      j = k;
      k = i2;
      i2 = i1;
    } 
    Arrays.fill((Object[])this.mSet, (Object)null);
  }
  
  void onAnchorReady(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.AnchorInfo paramAnchorInfo, int paramInt) {
    super.onAnchorReady(paramRecycler, paramState, paramAnchorInfo, paramInt);
    updateMeasurements();
    if (paramState.getItemCount() > 0 && !paramState.isPreLayout())
      ensureAnchorIsInCorrectSpan(paramRecycler, paramState, paramAnchorInfo, paramInt); 
    ensureViewSet();
  }
  
  public View onFocusSearchFailed(View paramView, int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual findContainingItemView : (Landroid/view/View;)Landroid/view/View;
    //   5: astore #5
    //   7: aconst_null
    //   8: astore #6
    //   10: aload #5
    //   12: ifnonnull -> 17
    //   15: aconst_null
    //   16: areturn
    //   17: aload #5
    //   19: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   22: checkcast android/support/v7/widget/GridLayoutManager$LayoutParams
    //   25: astore #7
    //   27: aload #7
    //   29: getfield mSpanIndex : I
    //   32: istore #8
    //   34: aload #7
    //   36: getfield mSpanIndex : I
    //   39: aload #7
    //   41: getfield mSpanSize : I
    //   44: iadd
    //   45: istore #9
    //   47: aload_0
    //   48: aload_1
    //   49: iload_2
    //   50: aload_3
    //   51: aload #4
    //   53: invokespecial onFocusSearchFailed : (Landroid/view/View;ILandroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;)Landroid/view/View;
    //   56: ifnonnull -> 61
    //   59: aconst_null
    //   60: areturn
    //   61: aload_0
    //   62: iload_2
    //   63: invokevirtual convertFocusDirectionToLayoutDirection : (I)I
    //   66: iconst_1
    //   67: if_icmpne -> 76
    //   70: iconst_1
    //   71: istore #10
    //   73: goto -> 79
    //   76: iconst_0
    //   77: istore #10
    //   79: iload #10
    //   81: aload_0
    //   82: getfield mShouldReverseLayout : Z
    //   85: if_icmpeq -> 93
    //   88: iconst_1
    //   89: istore_2
    //   90: goto -> 95
    //   93: iconst_0
    //   94: istore_2
    //   95: iload_2
    //   96: ifeq -> 117
    //   99: aload_0
    //   100: invokevirtual getChildCount : ()I
    //   103: iconst_1
    //   104: isub
    //   105: istore #11
    //   107: iconst_m1
    //   108: istore #12
    //   110: iload #12
    //   112: istore #13
    //   114: goto -> 129
    //   117: aload_0
    //   118: invokevirtual getChildCount : ()I
    //   121: istore #12
    //   123: iconst_1
    //   124: istore #13
    //   126: iconst_0
    //   127: istore #11
    //   129: aload_0
    //   130: getfield mOrientation : I
    //   133: iconst_1
    //   134: if_icmpne -> 150
    //   137: aload_0
    //   138: invokevirtual isLayoutRTL : ()Z
    //   141: ifeq -> 150
    //   144: iconst_1
    //   145: istore #14
    //   147: goto -> 153
    //   150: iconst_0
    //   151: istore #14
    //   153: aload_0
    //   154: aload_3
    //   155: aload #4
    //   157: iload #11
    //   159: invokespecial getSpanGroupIndex : (Landroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;I)I
    //   162: istore #15
    //   164: iconst_m1
    //   165: istore #16
    //   167: iload #16
    //   169: istore #17
    //   171: iconst_0
    //   172: istore #18
    //   174: iconst_0
    //   175: istore_2
    //   176: aconst_null
    //   177: astore_1
    //   178: iload #12
    //   180: istore #19
    //   182: iload #16
    //   184: istore #12
    //   186: iload #11
    //   188: iload #19
    //   190: if_icmpeq -> 561
    //   193: aload_0
    //   194: aload_3
    //   195: aload #4
    //   197: iload #11
    //   199: invokespecial getSpanGroupIndex : (Landroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;I)I
    //   202: istore #16
    //   204: aload_0
    //   205: iload #11
    //   207: invokevirtual getChildAt : (I)Landroid/view/View;
    //   210: astore #7
    //   212: aload #7
    //   214: aload #5
    //   216: if_acmpne -> 222
    //   219: goto -> 561
    //   222: aload #7
    //   224: invokevirtual hasFocusable : ()Z
    //   227: ifeq -> 248
    //   230: iload #16
    //   232: iload #15
    //   234: if_icmpeq -> 248
    //   237: aload #6
    //   239: ifnull -> 245
    //   242: goto -> 561
    //   245: goto -> 551
    //   248: aload #7
    //   250: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   253: checkcast android/support/v7/widget/GridLayoutManager$LayoutParams
    //   256: astore #20
    //   258: aload #20
    //   260: getfield mSpanIndex : I
    //   263: istore #21
    //   265: aload #20
    //   267: getfield mSpanIndex : I
    //   270: aload #20
    //   272: getfield mSpanSize : I
    //   275: iadd
    //   276: istore #22
    //   278: aload #7
    //   280: invokevirtual hasFocusable : ()Z
    //   283: ifeq -> 303
    //   286: iload #21
    //   288: iload #8
    //   290: if_icmpne -> 303
    //   293: iload #22
    //   295: iload #9
    //   297: if_icmpne -> 303
    //   300: aload #7
    //   302: areturn
    //   303: aload #7
    //   305: invokevirtual hasFocusable : ()Z
    //   308: ifeq -> 316
    //   311: aload #6
    //   313: ifnull -> 328
    //   316: aload #7
    //   318: invokevirtual hasFocusable : ()Z
    //   321: ifne -> 334
    //   324: aload_1
    //   325: ifnonnull -> 334
    //   328: iconst_1
    //   329: istore #16
    //   331: goto -> 472
    //   334: iload #21
    //   336: iload #8
    //   338: invokestatic max : (II)I
    //   341: istore #16
    //   343: iload #22
    //   345: iload #9
    //   347: invokestatic min : (II)I
    //   350: iload #16
    //   352: isub
    //   353: istore #23
    //   355: aload #7
    //   357: invokevirtual hasFocusable : ()Z
    //   360: ifeq -> 406
    //   363: iload #23
    //   365: iload #18
    //   367: if_icmple -> 373
    //   370: goto -> 328
    //   373: iload #23
    //   375: iload #18
    //   377: if_icmpne -> 469
    //   380: iload #21
    //   382: iload #12
    //   384: if_icmple -> 393
    //   387: iconst_1
    //   388: istore #16
    //   390: goto -> 396
    //   393: iconst_0
    //   394: istore #16
    //   396: iload #14
    //   398: iload #16
    //   400: if_icmpne -> 469
    //   403: goto -> 328
    //   406: aload #6
    //   408: ifnonnull -> 469
    //   411: iconst_0
    //   412: istore #24
    //   414: aload_0
    //   415: aload #7
    //   417: iconst_0
    //   418: iconst_1
    //   419: invokevirtual isViewPartiallyVisible : (Landroid/view/View;ZZ)Z
    //   422: ifeq -> 469
    //   425: iload_2
    //   426: istore #16
    //   428: iload #23
    //   430: iload #16
    //   432: if_icmple -> 438
    //   435: goto -> 328
    //   438: iload #23
    //   440: iload #16
    //   442: if_icmpne -> 469
    //   445: iload #24
    //   447: istore #16
    //   449: iload #21
    //   451: iload #17
    //   453: if_icmple -> 459
    //   456: iconst_1
    //   457: istore #16
    //   459: iload #14
    //   461: iload #16
    //   463: if_icmpne -> 469
    //   466: goto -> 328
    //   469: iconst_0
    //   470: istore #16
    //   472: iload #16
    //   474: ifeq -> 551
    //   477: aload #7
    //   479: invokevirtual hasFocusable : ()Z
    //   482: ifeq -> 516
    //   485: aload #20
    //   487: getfield mSpanIndex : I
    //   490: istore #12
    //   492: iload #22
    //   494: iload #9
    //   496: invokestatic min : (II)I
    //   499: iload #21
    //   501: iload #8
    //   503: invokestatic max : (II)I
    //   506: isub
    //   507: istore #18
    //   509: aload #7
    //   511: astore #6
    //   513: goto -> 551
    //   516: aload #20
    //   518: getfield mSpanIndex : I
    //   521: istore #17
    //   523: iload #22
    //   525: iload #9
    //   527: invokestatic min : (II)I
    //   530: istore_2
    //   531: iload #21
    //   533: iload #8
    //   535: invokestatic max : (II)I
    //   538: istore #16
    //   540: aload #7
    //   542: astore_1
    //   543: iload_2
    //   544: iload #16
    //   546: isub
    //   547: istore_2
    //   548: goto -> 551
    //   551: iload #11
    //   553: iload #13
    //   555: iadd
    //   556: istore #11
    //   558: goto -> 186
    //   561: aload #6
    //   563: ifnull -> 569
    //   566: aload #6
    //   568: astore_1
    //   569: aload_1
    //   570: areturn
  }
  
  public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {
    ViewGroup.LayoutParams layoutParams1 = paramView.getLayoutParams();
    if (!(layoutParams1 instanceof LayoutParams)) {
      onInitializeAccessibilityNodeInfoForItem(paramView, paramAccessibilityNodeInfoCompat);
      return;
    } 
    LayoutParams layoutParams = (LayoutParams)layoutParams1;
    int i = getSpanGroupIndex(paramRecycler, paramState, layoutParams.getViewLayoutPosition());
    if (this.mOrientation == 0) {
      boolean bool;
      int j = layoutParams.getSpanIndex();
      int k = layoutParams.getSpanSize();
      if (this.mSpanCount > 1 && layoutParams.getSpanSize() == this.mSpanCount) {
        bool = true;
      } else {
        bool = false;
      } 
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(j, k, i, 1, bool, false));
    } else {
      boolean bool;
      int k = layoutParams.getSpanIndex();
      int j = layoutParams.getSpanSize();
      if (this.mSpanCount > 1 && layoutParams.getSpanSize() == this.mSpanCount) {
        bool = true;
      } else {
        bool = false;
      } 
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, k, j, bool, false));
    } 
  }
  
  public void onItemsAdded(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsChanged(RecyclerView paramRecyclerView) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsMoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsRemoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, Object paramObject) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    if (paramState.isPreLayout())
      cachePreLayoutSpanMapping(); 
    super.onLayoutChildren(paramRecycler, paramState);
    clearPreLayoutSpanMappingCache();
  }
  
  public void onLayoutCompleted(RecyclerView.State paramState) {
    super.onLayoutCompleted(paramState);
    this.mPendingSpanCountChange = false;
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    updateMeasurements();
    ensureViewSet();
    return super.scrollHorizontallyBy(paramInt, paramRecycler, paramState);
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    updateMeasurements();
    ensureViewSet();
    return super.scrollVerticallyBy(paramInt, paramRecycler, paramState);
  }
  
  public void setMeasuredDimension(Rect paramRect, int paramInt1, int paramInt2) {
    if (this.mCachedBorders == null)
      super.setMeasuredDimension(paramRect, paramInt1, paramInt2); 
    int i = getPaddingLeft() + getPaddingRight();
    int j = getPaddingTop() + getPaddingBottom();
    if (this.mOrientation == 1) {
      paramInt2 = chooseSize(paramInt2, paramRect.height() + j, getMinimumHeight());
      paramInt1 = chooseSize(paramInt1, this.mCachedBorders[this.mCachedBorders.length - 1] + i, getMinimumWidth());
    } else {
      paramInt1 = chooseSize(paramInt1, paramRect.width() + i, getMinimumWidth());
      paramInt2 = chooseSize(paramInt2, this.mCachedBorders[this.mCachedBorders.length - 1] + j, getMinimumHeight());
    } 
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  public void setSpanCount(int paramInt) {
    if (paramInt == this.mSpanCount)
      return; 
    this.mPendingSpanCountChange = true;
    if (paramInt < 1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Span count should be at least 1. Provided ");
      stringBuilder.append(paramInt);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    this.mSpanCount = paramInt;
    this.mSpanSizeLookup.invalidateSpanIndexCache();
    requestLayout();
  }
  
  public void setSpanSizeLookup(SpanSizeLookup paramSpanSizeLookup) {
    this.mSpanSizeLookup = paramSpanSizeLookup;
  }
  
  public void setStackFromEnd(boolean paramBoolean) {
    if (paramBoolean)
      throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout"); 
    super.setStackFromEnd(false);
  }
  
  public boolean supportsPredictiveItemAnimations() {
    boolean bool;
    if (this.mPendingSavedState == null && !this.mPendingSpanCountChange) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
    public int getSpanIndex(int param1Int1, int param1Int2) {
      return param1Int1 % param1Int2;
    }
    
    public int getSpanSize(int param1Int) {
      return 1;
    }
  }
  
  public static class LayoutParams extends RecyclerView.LayoutParams {
    public static final int INVALID_SPAN_ID = -1;
    
    int mSpanIndex = -1;
    
    int mSpanSize = 0;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(RecyclerView.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public int getSpanIndex() {
      return this.mSpanIndex;
    }
    
    public int getSpanSize() {
      return this.mSpanSize;
    }
  }
  
  public static abstract class SpanSizeLookup {
    private boolean mCacheSpanIndices = false;
    
    final SparseIntArray mSpanIndexCache = new SparseIntArray();
    
    int findReferenceIndexFromCache(int param1Int) {
      int i = this.mSpanIndexCache.size() - 1;
      int j = 0;
      while (j <= i) {
        int k = j + i >>> 1;
        if (this.mSpanIndexCache.keyAt(k) < param1Int) {
          j = k + 1;
          continue;
        } 
        i = k - 1;
      } 
      param1Int = j - 1;
      return (param1Int >= 0 && param1Int < this.mSpanIndexCache.size()) ? this.mSpanIndexCache.keyAt(param1Int) : -1;
    }
    
    int getCachedSpanIndex(int param1Int1, int param1Int2) {
      if (!this.mCacheSpanIndices)
        return getSpanIndex(param1Int1, param1Int2); 
      int i = this.mSpanIndexCache.get(param1Int1, -1);
      if (i != -1)
        return i; 
      param1Int2 = getSpanIndex(param1Int1, param1Int2);
      this.mSpanIndexCache.put(param1Int1, param1Int2);
      return param1Int2;
    }
    
    public int getSpanGroupIndex(int param1Int1, int param1Int2) {
      int i = getSpanSize(param1Int1);
      byte b = 0;
      int j = b;
      int k;
      for (k = j; b < param1Int1; k = i1) {
        int i1;
        int m = getSpanSize(b);
        int n = j + m;
        if (n == param1Int2) {
          i1 = k + 1;
          j = 0;
        } else {
          j = n;
          i1 = k;
          if (n > param1Int2) {
            i1 = k + 1;
            j = m;
          } 
        } 
        b++;
      } 
      param1Int1 = k;
      if (j + i > param1Int2)
        param1Int1 = k + 1; 
      return param1Int1;
    }
    
    public int getSpanIndex(int param1Int1, int param1Int2) {
      // Byte code:
      //   0: aload_0
      //   1: iload_1
      //   2: invokevirtual getSpanSize : (I)I
      //   5: istore_3
      //   6: iload_3
      //   7: iload_2
      //   8: if_icmpne -> 13
      //   11: iconst_0
      //   12: ireturn
      //   13: aload_0
      //   14: getfield mCacheSpanIndices : Z
      //   17: ifeq -> 66
      //   20: aload_0
      //   21: getfield mSpanIndexCache : Landroid/util/SparseIntArray;
      //   24: invokevirtual size : ()I
      //   27: ifle -> 66
      //   30: aload_0
      //   31: iload_1
      //   32: invokevirtual findReferenceIndexFromCache : (I)I
      //   35: istore #4
      //   37: iload #4
      //   39: iflt -> 66
      //   42: aload_0
      //   43: getfield mSpanIndexCache : Landroid/util/SparseIntArray;
      //   46: iload #4
      //   48: invokevirtual get : (I)I
      //   51: aload_0
      //   52: iload #4
      //   54: invokevirtual getSpanSize : (I)I
      //   57: iadd
      //   58: istore #5
      //   60: iinc #4, 1
      //   63: goto -> 73
      //   66: iconst_0
      //   67: istore #4
      //   69: iload #4
      //   71: istore #5
      //   73: iload #4
      //   75: iload_1
      //   76: if_icmpge -> 126
      //   79: aload_0
      //   80: iload #4
      //   82: invokevirtual getSpanSize : (I)I
      //   85: istore #6
      //   87: iload #5
      //   89: iload #6
      //   91: iadd
      //   92: istore #7
      //   94: iload #7
      //   96: iload_2
      //   97: if_icmpne -> 106
      //   100: iconst_0
      //   101: istore #5
      //   103: goto -> 120
      //   106: iload #7
      //   108: istore #5
      //   110: iload #7
      //   112: iload_2
      //   113: if_icmple -> 120
      //   116: iload #6
      //   118: istore #5
      //   120: iinc #4, 1
      //   123: goto -> 73
      //   126: iload_3
      //   127: iload #5
      //   129: iadd
      //   130: iload_2
      //   131: if_icmpgt -> 137
      //   134: iload #5
      //   136: ireturn
      //   137: iconst_0
      //   138: ireturn
    }
    
    public abstract int getSpanSize(int param1Int);
    
    public void invalidateSpanIndexCache() {
      this.mSpanIndexCache.clear();
    }
    
    public boolean isSpanIndexCacheEnabled() {
      return this.mCacheSpanIndices;
    }
    
    public void setSpanIndexCacheEnabled(boolean param1Boolean) {
      this.mCacheSpanIndices = param1Boolean;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\GridLayoutManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */