package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
  public static final int HORIZONTAL = 0;
  
  private static final int INDEX_BOTTOM = 2;
  
  private static final int INDEX_CENTER_VERTICAL = 0;
  
  private static final int INDEX_FILL = 3;
  
  private static final int INDEX_TOP = 1;
  
  public static final int SHOW_DIVIDER_BEGINNING = 1;
  
  public static final int SHOW_DIVIDER_END = 4;
  
  public static final int SHOW_DIVIDER_MIDDLE = 2;
  
  public static final int SHOW_DIVIDER_NONE = 0;
  
  public static final int VERTICAL = 1;
  
  private static final int VERTICAL_GRAVITY_COUNT = 4;
  
  private boolean mBaselineAligned = true;
  
  private int mBaselineAlignedChildIndex = -1;
  
  private int mBaselineChildTop = 0;
  
  private Drawable mDivider;
  
  private int mDividerHeight;
  
  private int mDividerPadding;
  
  private int mDividerWidth;
  
  private int mGravity = 8388659;
  
  private int[] mMaxAscent;
  
  private int[] mMaxDescent;
  
  private int mOrientation;
  
  private int mShowDividers;
  
  private int mTotalLength;
  
  private boolean mUseLargestChild;
  
  private float mWeightSum;
  
  public LinearLayoutCompat(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.LinearLayoutCompat, paramInt, 0);
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
    if (paramInt >= 0)
      setOrientation(paramInt); 
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
    if (paramInt >= 0)
      setGravity(paramInt); 
    boolean bool = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
    if (!bool)
      setBaselineAligned(bool); 
    this.mWeightSum = tintTypedArray.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
    this.mBaselineAlignedChildIndex = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
    this.mUseLargestChild = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
    setDividerDrawable(tintTypedArray.getDrawable(R.styleable.LinearLayoutCompat_divider));
    this.mShowDividers = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
    this.mDividerPadding = tintTypedArray.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
    tintTypedArray.recycle();
  }
  
  private void forceUniformHeight(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
    for (byte b = 0; b < paramInt1; b++) {
      View view = getVirtualChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.height == -1) {
          int j = layoutParams.width;
          layoutParams.width = view.getMeasuredWidth();
          measureChildWithMargins(view, paramInt2, 0, i, 0);
          layoutParams.width = j;
        } 
      } 
    } 
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    for (byte b = 0; b < paramInt1; b++) {
      View view = getVirtualChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.width == -1) {
          int j = layoutParams.height;
          layoutParams.height = view.getMeasuredHeight();
          measureChildWithMargins(view, i, 0, paramInt2, 0);
          layoutParams.height = j;
        } 
      } 
    } 
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramView.layout(paramInt1, paramInt2, paramInt3 + paramInt1, paramInt4 + paramInt2);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void drawDividersHorizontal(Canvas paramCanvas) {
    int i = getVirtualChildCount();
    boolean bool = ViewUtils.isLayoutRtl((View)this);
    int j;
    for (j = 0; j < i; j++) {
      View view = getVirtualChildAt(j);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(j)) {
        int k;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          k = view.getRight() + layoutParams.rightMargin;
        } else {
          k = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
        } 
        drawVerticalDivider(paramCanvas, k);
      } 
    } 
    if (hasDividerBeforeChildAt(i)) {
      View view = getVirtualChildAt(i - 1);
      if (view == null) {
        if (bool) {
          j = getPaddingLeft();
        } else {
          j = getWidth() - getPaddingRight() - this.mDividerWidth;
        } 
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          j = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
        } else {
          j = view.getRight() + layoutParams.rightMargin;
        } 
      } 
      drawVerticalDivider(paramCanvas, j);
    } 
  }
  
  void drawDividersVertical(Canvas paramCanvas) {
    int i = getVirtualChildCount();
    int j;
    for (j = 0; j < i; j++) {
      View view = getVirtualChildAt(j);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(j)) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        drawHorizontalDivider(paramCanvas, view.getTop() - layoutParams.topMargin - this.mDividerHeight);
      } 
    } 
    if (hasDividerBeforeChildAt(i)) {
      View view = getVirtualChildAt(i - 1);
      if (view == null) {
        j = getHeight() - getPaddingBottom() - this.mDividerHeight;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        j = view.getBottom() + layoutParams.bottomMargin;
      } 
      drawHorizontalDivider(paramCanvas, j);
    } 
  }
  
  void drawHorizontalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, paramInt, getWidth() - getPaddingRight() - this.mDividerPadding, this.mDividerHeight + paramInt);
    this.mDivider.draw(paramCanvas);
  }
  
  void drawVerticalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(paramInt, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + paramInt, getHeight() - getPaddingBottom() - this.mDividerPadding);
    this.mDivider.draw(paramCanvas);
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    return (this.mOrientation == 0) ? new LayoutParams(-2, -2) : ((this.mOrientation == 1) ? new LayoutParams(-1, -2) : null);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getBaseline() {
    if (this.mBaselineAlignedChildIndex < 0)
      return super.getBaseline(); 
    if (getChildCount() <= this.mBaselineAlignedChildIndex)
      throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds."); 
    View view = getChildAt(this.mBaselineAlignedChildIndex);
    int i = view.getBaseline();
    if (i == -1) {
      if (this.mBaselineAlignedChildIndex == 0)
        return -1; 
      throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
    } 
    int j = this.mBaselineChildTop;
    int k = j;
    if (this.mOrientation == 1) {
      int m = this.mGravity & 0x70;
      k = j;
      if (m != 48)
        if (m != 16) {
          if (m != 80) {
            k = j;
          } else {
            k = getBottom() - getTop() - getPaddingBottom() - this.mTotalLength;
          } 
        } else {
          k = j + (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - this.mTotalLength) / 2;
        }  
    } 
    return k + ((LayoutParams)view.getLayoutParams()).topMargin + i;
  }
  
  public int getBaselineAlignedChildIndex() {
    return this.mBaselineAlignedChildIndex;
  }
  
  int getChildrenSkipCount(View paramView, int paramInt) {
    return 0;
  }
  
  public Drawable getDividerDrawable() {
    return this.mDivider;
  }
  
  public int getDividerPadding() {
    return this.mDividerPadding;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getDividerWidth() {
    return this.mDividerWidth;
  }
  
  public int getGravity() {
    return this.mGravity;
  }
  
  int getLocationOffset(View paramView) {
    return 0;
  }
  
  int getNextLocationOffset(View paramView) {
    return 0;
  }
  
  public int getOrientation() {
    return this.mOrientation;
  }
  
  public int getShowDividers() {
    return this.mShowDividers;
  }
  
  View getVirtualChildAt(int paramInt) {
    return getChildAt(paramInt);
  }
  
  int getVirtualChildCount() {
    return getChildCount();
  }
  
  public float getWeightSum() {
    return this.mWeightSum;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  protected boolean hasDividerBeforeChildAt(int paramInt) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    if (paramInt == 0) {
      if ((this.mShowDividers & 0x1) != 0)
        bool3 = true; 
      return bool3;
    } 
    if (paramInt == getChildCount()) {
      bool3 = bool1;
      if ((this.mShowDividers & 0x4) != 0)
        bool3 = true; 
      return bool3;
    } 
    if ((this.mShowDividers & 0x2) != 0) {
      paramInt--;
      while (true) {
        bool3 = bool2;
        if (paramInt >= 0) {
          if (getChildAt(paramInt).getVisibility() != 8) {
            bool3 = true;
            break;
          } 
          paramInt--;
          continue;
        } 
        break;
      } 
      return bool3;
    } 
    return false;
  }
  
  public boolean isBaselineAligned() {
    return this.mBaselineAligned;
  }
  
  public boolean isMeasureWithLargestChildEnabled() {
    return this.mUseLargestChild;
  }
  
  void layoutHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    byte b1;
    byte b2;
    boolean bool1 = ViewUtils.isLayoutRtl((View)this);
    int i = getPaddingTop();
    int j = paramInt4 - paramInt2;
    int k = getPaddingBottom();
    int m = getPaddingBottom();
    int n = getVirtualChildCount();
    paramInt4 = this.mGravity;
    paramInt2 = this.mGravity & 0x70;
    boolean bool2 = this.mBaselineAligned;
    int[] arrayOfInt1 = this.mMaxAscent;
    int[] arrayOfInt2 = this.mMaxDescent;
    paramInt4 = GravityCompat.getAbsoluteGravity(paramInt4 & 0x800007, ViewCompat.getLayoutDirection((View)this));
    boolean bool = true;
    if (paramInt4 != 1) {
      if (paramInt4 != 5) {
        paramInt1 = getPaddingLeft();
      } else {
        paramInt1 = getPaddingLeft() + paramInt3 - paramInt1 - this.mTotalLength;
      } 
    } else {
      paramInt4 = getPaddingLeft();
      paramInt1 = (paramInt3 - paramInt1 - this.mTotalLength) / 2 + paramInt4;
    } 
    if (bool1) {
      b1 = n - 1;
      b2 = -1;
    } else {
      b1 = 0;
      b2 = 1;
    } 
    paramInt4 = 0;
    paramInt3 = i;
    while (paramInt4 < n) {
      int i1 = b1 + b2 * paramInt4;
      View view = getVirtualChildAt(i1);
      if (view == null) {
        paramInt1 += measureNullChild(i1);
      } else if (view.getVisibility() != 8) {
        int i2 = view.getMeasuredWidth();
        int i3 = view.getMeasuredHeight();
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool2 && layoutParams.height != -1) {
          i4 = view.getBaseline();
        } else {
          i4 = -1;
        } 
        int i5 = layoutParams.gravity;
        int i6 = i5;
        if (i5 < 0)
          i6 = paramInt2; 
        i6 &= 0x70;
        if (i6 != 16) {
          if (i6 != 48) {
            if (i6 != 80) {
              i6 = paramInt3;
            } else {
              i5 = j - k - i3 - layoutParams.bottomMargin;
              i6 = i5;
              if (i4 != -1) {
                i6 = view.getMeasuredHeight();
                i6 = i5 - arrayOfInt2[2] - i6 - i4;
              } 
            } 
          } else {
            i6 = layoutParams.topMargin + paramInt3;
            if (i4 != -1)
              i6 += arrayOfInt1[1] - i4; 
          } 
        } else {
          i6 = (j - i - m - i3) / 2 + paramInt3 + layoutParams.topMargin - layoutParams.bottomMargin;
        } 
        bool = true;
        int i4 = paramInt1;
        if (hasDividerBeforeChildAt(i1))
          i4 = paramInt1 + this.mDividerWidth; 
        paramInt1 = layoutParams.leftMargin + i4;
        setChildFrame(view, paramInt1 + getLocationOffset(view), i6, i2, i3);
        i4 = layoutParams.rightMargin;
        i6 = getNextLocationOffset(view);
        paramInt4 += getChildrenSkipCount(view, i1);
        paramInt1 += i2 + i4 + i6;
      } else {
        bool = true;
      } 
      paramInt4++;
    } 
  }
  
  void layoutVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getPaddingLeft();
    int j = paramInt3 - paramInt1;
    int k = getPaddingRight();
    int m = getPaddingRight();
    int n = getVirtualChildCount();
    paramInt1 = this.mGravity & 0x70;
    int i1 = this.mGravity;
    if (paramInt1 != 16) {
      if (paramInt1 != 80) {
        paramInt1 = getPaddingTop();
      } else {
        paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - this.mTotalLength;
      } 
    } else {
      paramInt1 = getPaddingTop();
      paramInt1 = (paramInt4 - paramInt2 - this.mTotalLength) / 2 + paramInt1;
    } 
    for (paramInt2 = 0; paramInt2 < n; paramInt2++) {
      View view = getVirtualChildAt(paramInt2);
      if (view == null) {
        paramInt3 = paramInt1 + measureNullChild(paramInt2);
      } else {
        paramInt3 = paramInt1;
        if (view.getVisibility() != 8) {
          int i2 = view.getMeasuredWidth();
          int i3 = view.getMeasuredHeight();
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          paramInt4 = layoutParams.gravity;
          paramInt3 = paramInt4;
          if (paramInt4 < 0)
            paramInt3 = i1 & 0x800007; 
          paramInt3 = GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection((View)this)) & 0x7;
          if (paramInt3 != 1) {
            if (paramInt3 != 5) {
              paramInt3 = layoutParams.leftMargin + i;
            } else {
              paramInt3 = j - k - i2 - layoutParams.rightMargin;
            } 
          } else {
            paramInt3 = (j - i - m - i2) / 2 + i + layoutParams.leftMargin - layoutParams.rightMargin;
          } 
          paramInt4 = paramInt1;
          if (hasDividerBeforeChildAt(paramInt2))
            paramInt4 = paramInt1 + this.mDividerHeight; 
          paramInt1 = paramInt4 + layoutParams.topMargin;
          setChildFrame(view, paramInt3, paramInt1 + getLocationOffset(view), i2, i3);
          paramInt3 = layoutParams.bottomMargin;
          paramInt4 = getNextLocationOffset(view);
          paramInt2 += getChildrenSkipCount(view, paramInt2);
          paramInt1 += i3 + paramInt3 + paramInt4;
          continue;
        } 
      } 
      paramInt1 = paramInt3;
      continue;
    } 
  }
  
  void measureChildBeforeLayout(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    measureChildWithMargins(paramView, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  void measureHorizontal(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield mTotalLength : I
    //   5: aload_0
    //   6: invokevirtual getVirtualChildCount : ()I
    //   9: istore_3
    //   10: iload_1
    //   11: invokestatic getMode : (I)I
    //   14: istore #4
    //   16: iload_2
    //   17: invokestatic getMode : (I)I
    //   20: istore #5
    //   22: aload_0
    //   23: getfield mMaxAscent : [I
    //   26: ifnull -> 36
    //   29: aload_0
    //   30: getfield mMaxDescent : [I
    //   33: ifnonnull -> 50
    //   36: aload_0
    //   37: iconst_4
    //   38: newarray int
    //   40: putfield mMaxAscent : [I
    //   43: aload_0
    //   44: iconst_4
    //   45: newarray int
    //   47: putfield mMaxDescent : [I
    //   50: aload_0
    //   51: getfield mMaxAscent : [I
    //   54: astore #6
    //   56: aload_0
    //   57: getfield mMaxDescent : [I
    //   60: astore #7
    //   62: aload #6
    //   64: iconst_3
    //   65: iconst_m1
    //   66: iastore
    //   67: aload #6
    //   69: iconst_2
    //   70: iconst_m1
    //   71: iastore
    //   72: aload #6
    //   74: iconst_1
    //   75: iconst_m1
    //   76: iastore
    //   77: aload #6
    //   79: iconst_0
    //   80: iconst_m1
    //   81: iastore
    //   82: aload #7
    //   84: iconst_3
    //   85: iconst_m1
    //   86: iastore
    //   87: aload #7
    //   89: iconst_2
    //   90: iconst_m1
    //   91: iastore
    //   92: aload #7
    //   94: iconst_1
    //   95: iconst_m1
    //   96: iastore
    //   97: aload #7
    //   99: iconst_0
    //   100: iconst_m1
    //   101: iastore
    //   102: aload_0
    //   103: getfield mBaselineAligned : Z
    //   106: istore #8
    //   108: aload_0
    //   109: getfield mUseLargestChild : Z
    //   112: istore #9
    //   114: ldc 1073741824
    //   116: istore #10
    //   118: iload #4
    //   120: ldc 1073741824
    //   122: if_icmpne -> 131
    //   125: iconst_1
    //   126: istore #11
    //   128: goto -> 134
    //   131: iconst_0
    //   132: istore #11
    //   134: iconst_0
    //   135: istore #12
    //   137: iload #12
    //   139: istore #13
    //   141: iload #13
    //   143: istore #14
    //   145: iload #14
    //   147: istore #15
    //   149: iload #15
    //   151: istore #16
    //   153: iload #16
    //   155: istore #17
    //   157: iload #17
    //   159: istore #18
    //   161: iload #18
    //   163: istore #19
    //   165: iconst_1
    //   166: istore #20
    //   168: fconst_0
    //   169: fstore #21
    //   171: iload #12
    //   173: iload_3
    //   174: if_icmpge -> 894
    //   177: aload_0
    //   178: iload #12
    //   180: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   183: astore #22
    //   185: aload #22
    //   187: ifnonnull -> 220
    //   190: aload_0
    //   191: aload_0
    //   192: getfield mTotalLength : I
    //   195: aload_0
    //   196: iload #12
    //   198: invokevirtual measureNullChild : (I)I
    //   201: iadd
    //   202: putfield mTotalLength : I
    //   205: iload #10
    //   207: istore #23
    //   209: iload #12
    //   211: istore #10
    //   213: iload #23
    //   215: istore #12
    //   217: goto -> 877
    //   220: aload #22
    //   222: invokevirtual getVisibility : ()I
    //   225: bipush #8
    //   227: if_icmpne -> 246
    //   230: iload #12
    //   232: aload_0
    //   233: aload #22
    //   235: iload #12
    //   237: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   240: iadd
    //   241: istore #12
    //   243: goto -> 205
    //   246: aload_0
    //   247: iload #12
    //   249: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   252: ifeq -> 268
    //   255: aload_0
    //   256: aload_0
    //   257: getfield mTotalLength : I
    //   260: aload_0
    //   261: getfield mDividerWidth : I
    //   264: iadd
    //   265: putfield mTotalLength : I
    //   268: aload #22
    //   270: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   273: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   276: astore #24
    //   278: fload #21
    //   280: aload #24
    //   282: getfield weight : F
    //   285: fadd
    //   286: fstore #21
    //   288: iload #4
    //   290: iload #10
    //   292: if_icmpne -> 404
    //   295: aload #24
    //   297: getfield width : I
    //   300: ifne -> 404
    //   303: aload #24
    //   305: getfield weight : F
    //   308: fconst_0
    //   309: fcmpl
    //   310: ifle -> 404
    //   313: iload #11
    //   315: ifeq -> 341
    //   318: aload_0
    //   319: aload_0
    //   320: getfield mTotalLength : I
    //   323: aload #24
    //   325: getfield leftMargin : I
    //   328: aload #24
    //   330: getfield rightMargin : I
    //   333: iadd
    //   334: iadd
    //   335: putfield mTotalLength : I
    //   338: goto -> 370
    //   341: aload_0
    //   342: getfield mTotalLength : I
    //   345: istore #10
    //   347: aload_0
    //   348: iload #10
    //   350: aload #24
    //   352: getfield leftMargin : I
    //   355: iload #10
    //   357: iadd
    //   358: aload #24
    //   360: getfield rightMargin : I
    //   363: iadd
    //   364: invokestatic max : (II)I
    //   367: putfield mTotalLength : I
    //   370: iload #8
    //   372: ifeq -> 398
    //   375: iconst_0
    //   376: iconst_0
    //   377: invokestatic makeMeasureSpec : (II)I
    //   380: istore #10
    //   382: aload #22
    //   384: iload #10
    //   386: iload #10
    //   388: invokevirtual measure : (II)V
    //   391: iload #13
    //   393: istore #10
    //   395: goto -> 593
    //   398: iconst_1
    //   399: istore #15
    //   401: goto -> 597
    //   404: aload #24
    //   406: getfield width : I
    //   409: ifne -> 435
    //   412: aload #24
    //   414: getfield weight : F
    //   417: fconst_0
    //   418: fcmpl
    //   419: ifle -> 435
    //   422: aload #24
    //   424: bipush #-2
    //   426: putfield width : I
    //   429: iconst_0
    //   430: istore #10
    //   432: goto -> 440
    //   435: ldc_w -2147483648
    //   438: istore #10
    //   440: fload #21
    //   442: fconst_0
    //   443: fcmpl
    //   444: ifne -> 456
    //   447: aload_0
    //   448: getfield mTotalLength : I
    //   451: istore #23
    //   453: goto -> 459
    //   456: iconst_0
    //   457: istore #23
    //   459: aload #22
    //   461: astore #25
    //   463: aload_0
    //   464: aload #22
    //   466: iload #12
    //   468: iload_1
    //   469: iload #23
    //   471: iload_2
    //   472: iconst_0
    //   473: invokevirtual measureChildBeforeLayout : (Landroid/view/View;IIIII)V
    //   476: iload #10
    //   478: ldc_w -2147483648
    //   481: if_icmpeq -> 491
    //   484: aload #24
    //   486: iload #10
    //   488: putfield width : I
    //   491: aload #25
    //   493: invokevirtual getMeasuredWidth : ()I
    //   496: istore #23
    //   498: iload #11
    //   500: ifeq -> 536
    //   503: aload_0
    //   504: aload_0
    //   505: getfield mTotalLength : I
    //   508: aload #24
    //   510: getfield leftMargin : I
    //   513: iload #23
    //   515: iadd
    //   516: aload #24
    //   518: getfield rightMargin : I
    //   521: iadd
    //   522: aload_0
    //   523: aload #25
    //   525: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   528: iadd
    //   529: iadd
    //   530: putfield mTotalLength : I
    //   533: goto -> 575
    //   536: aload_0
    //   537: getfield mTotalLength : I
    //   540: istore #10
    //   542: aload_0
    //   543: iload #10
    //   545: iload #10
    //   547: iload #23
    //   549: iadd
    //   550: aload #24
    //   552: getfield leftMargin : I
    //   555: iadd
    //   556: aload #24
    //   558: getfield rightMargin : I
    //   561: iadd
    //   562: aload_0
    //   563: aload #25
    //   565: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   568: iadd
    //   569: invokestatic max : (II)I
    //   572: putfield mTotalLength : I
    //   575: iload #13
    //   577: istore #10
    //   579: iload #9
    //   581: ifeq -> 593
    //   584: iload #23
    //   586: iload #13
    //   588: invokestatic max : (II)I
    //   591: istore #10
    //   593: iload #10
    //   595: istore #13
    //   597: iload #12
    //   599: istore #26
    //   601: ldc 1073741824
    //   603: istore #27
    //   605: iload #5
    //   607: ldc 1073741824
    //   609: if_icmpeq -> 631
    //   612: aload #24
    //   614: getfield height : I
    //   617: iconst_m1
    //   618: if_icmpne -> 631
    //   621: iconst_1
    //   622: istore #12
    //   624: iload #12
    //   626: istore #19
    //   628: goto -> 634
    //   631: iconst_0
    //   632: istore #12
    //   634: aload #24
    //   636: getfield topMargin : I
    //   639: aload #24
    //   641: getfield bottomMargin : I
    //   644: iadd
    //   645: istore #10
    //   647: aload #22
    //   649: invokevirtual getMeasuredHeight : ()I
    //   652: iload #10
    //   654: iadd
    //   655: istore #23
    //   657: iload #18
    //   659: aload #22
    //   661: invokevirtual getMeasuredState : ()I
    //   664: invokestatic combineMeasuredStates : (II)I
    //   667: istore #28
    //   669: iload #8
    //   671: ifeq -> 758
    //   674: aload #22
    //   676: invokevirtual getBaseline : ()I
    //   679: istore #29
    //   681: iload #29
    //   683: iconst_m1
    //   684: if_icmpeq -> 758
    //   687: aload #24
    //   689: getfield gravity : I
    //   692: ifge -> 704
    //   695: aload_0
    //   696: getfield mGravity : I
    //   699: istore #18
    //   701: goto -> 711
    //   704: aload #24
    //   706: getfield gravity : I
    //   709: istore #18
    //   711: iload #18
    //   713: bipush #112
    //   715: iand
    //   716: iconst_4
    //   717: ishr
    //   718: bipush #-2
    //   720: iand
    //   721: iconst_1
    //   722: ishr
    //   723: istore #18
    //   725: aload #6
    //   727: iload #18
    //   729: aload #6
    //   731: iload #18
    //   733: iaload
    //   734: iload #29
    //   736: invokestatic max : (II)I
    //   739: iastore
    //   740: aload #7
    //   742: iload #18
    //   744: aload #7
    //   746: iload #18
    //   748: iaload
    //   749: iload #23
    //   751: iload #29
    //   753: isub
    //   754: invokestatic max : (II)I
    //   757: iastore
    //   758: iload #14
    //   760: iload #23
    //   762: invokestatic max : (II)I
    //   765: istore #14
    //   767: iload #20
    //   769: ifeq -> 787
    //   772: aload #24
    //   774: getfield height : I
    //   777: iconst_m1
    //   778: if_icmpne -> 787
    //   781: iconst_1
    //   782: istore #20
    //   784: goto -> 790
    //   787: iconst_0
    //   788: istore #20
    //   790: aload #24
    //   792: getfield weight : F
    //   795: fconst_0
    //   796: fcmpl
    //   797: ifle -> 827
    //   800: iload #12
    //   802: ifeq -> 808
    //   805: goto -> 815
    //   808: iload #23
    //   810: istore #10
    //   812: goto -> 805
    //   815: iload #17
    //   817: iload #10
    //   819: invokestatic max : (II)I
    //   822: istore #12
    //   824: goto -> 852
    //   827: iload #12
    //   829: ifeq -> 836
    //   832: iload #10
    //   834: istore #23
    //   836: iload #16
    //   838: iload #23
    //   840: invokestatic max : (II)I
    //   843: istore #16
    //   845: iload #17
    //   847: istore #12
    //   849: goto -> 824
    //   852: aload_0
    //   853: aload #22
    //   855: iload #26
    //   857: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   860: iload #26
    //   862: iadd
    //   863: istore #10
    //   865: iload #28
    //   867: istore #18
    //   869: iload #12
    //   871: istore #17
    //   873: iload #27
    //   875: istore #12
    //   877: iload #12
    //   879: istore #23
    //   881: iload #10
    //   883: iconst_1
    //   884: iadd
    //   885: istore #12
    //   887: iload #23
    //   889: istore #10
    //   891: goto -> 171
    //   894: iload #14
    //   896: istore #12
    //   898: aload_0
    //   899: getfield mTotalLength : I
    //   902: ifle -> 926
    //   905: aload_0
    //   906: iload_3
    //   907: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   910: ifeq -> 926
    //   913: aload_0
    //   914: aload_0
    //   915: getfield mTotalLength : I
    //   918: aload_0
    //   919: getfield mDividerWidth : I
    //   922: iadd
    //   923: putfield mTotalLength : I
    //   926: aload #6
    //   928: iconst_1
    //   929: iaload
    //   930: iconst_m1
    //   931: if_icmpne -> 962
    //   934: aload #6
    //   936: iconst_0
    //   937: iaload
    //   938: iconst_m1
    //   939: if_icmpne -> 962
    //   942: aload #6
    //   944: iconst_2
    //   945: iaload
    //   946: iconst_m1
    //   947: if_icmpne -> 962
    //   950: iload #12
    //   952: istore #14
    //   954: aload #6
    //   956: iconst_3
    //   957: iaload
    //   958: iconst_m1
    //   959: if_icmpeq -> 1020
    //   962: iload #12
    //   964: aload #6
    //   966: iconst_3
    //   967: iaload
    //   968: aload #6
    //   970: iconst_0
    //   971: iaload
    //   972: aload #6
    //   974: iconst_1
    //   975: iaload
    //   976: aload #6
    //   978: iconst_2
    //   979: iaload
    //   980: invokestatic max : (II)I
    //   983: invokestatic max : (II)I
    //   986: invokestatic max : (II)I
    //   989: aload #7
    //   991: iconst_3
    //   992: iaload
    //   993: aload #7
    //   995: iconst_0
    //   996: iaload
    //   997: aload #7
    //   999: iconst_1
    //   1000: iaload
    //   1001: aload #7
    //   1003: iconst_2
    //   1004: iaload
    //   1005: invokestatic max : (II)I
    //   1008: invokestatic max : (II)I
    //   1011: invokestatic max : (II)I
    //   1014: iadd
    //   1015: invokestatic max : (II)I
    //   1018: istore #14
    //   1020: iload #9
    //   1022: ifeq -> 1202
    //   1025: iload #4
    //   1027: ldc_w -2147483648
    //   1030: if_icmpeq -> 1038
    //   1033: iload #4
    //   1035: ifne -> 1202
    //   1038: aload_0
    //   1039: iconst_0
    //   1040: putfield mTotalLength : I
    //   1043: iconst_0
    //   1044: istore #12
    //   1046: iload #12
    //   1048: iload_3
    //   1049: if_icmpge -> 1202
    //   1052: aload_0
    //   1053: iload #12
    //   1055: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1058: astore #22
    //   1060: aload #22
    //   1062: ifnonnull -> 1083
    //   1065: aload_0
    //   1066: aload_0
    //   1067: getfield mTotalLength : I
    //   1070: aload_0
    //   1071: iload #12
    //   1073: invokevirtual measureNullChild : (I)I
    //   1076: iadd
    //   1077: putfield mTotalLength : I
    //   1080: goto -> 1106
    //   1083: aload #22
    //   1085: invokevirtual getVisibility : ()I
    //   1088: bipush #8
    //   1090: if_icmpne -> 1109
    //   1093: iload #12
    //   1095: aload_0
    //   1096: aload #22
    //   1098: iload #12
    //   1100: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   1103: iadd
    //   1104: istore #12
    //   1106: goto -> 1196
    //   1109: aload #22
    //   1111: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1114: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   1117: astore #25
    //   1119: iload #11
    //   1121: ifeq -> 1157
    //   1124: aload_0
    //   1125: aload_0
    //   1126: getfield mTotalLength : I
    //   1129: aload #25
    //   1131: getfield leftMargin : I
    //   1134: iload #13
    //   1136: iadd
    //   1137: aload #25
    //   1139: getfield rightMargin : I
    //   1142: iadd
    //   1143: aload_0
    //   1144: aload #22
    //   1146: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1149: iadd
    //   1150: iadd
    //   1151: putfield mTotalLength : I
    //   1154: goto -> 1106
    //   1157: aload_0
    //   1158: getfield mTotalLength : I
    //   1161: istore #10
    //   1163: aload_0
    //   1164: iload #10
    //   1166: iload #10
    //   1168: iload #13
    //   1170: iadd
    //   1171: aload #25
    //   1173: getfield leftMargin : I
    //   1176: iadd
    //   1177: aload #25
    //   1179: getfield rightMargin : I
    //   1182: iadd
    //   1183: aload_0
    //   1184: aload #22
    //   1186: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1189: iadd
    //   1190: invokestatic max : (II)I
    //   1193: putfield mTotalLength : I
    //   1196: iinc #12, 1
    //   1199: goto -> 1046
    //   1202: aload_0
    //   1203: aload_0
    //   1204: getfield mTotalLength : I
    //   1207: aload_0
    //   1208: invokevirtual getPaddingLeft : ()I
    //   1211: aload_0
    //   1212: invokevirtual getPaddingRight : ()I
    //   1215: iadd
    //   1216: iadd
    //   1217: putfield mTotalLength : I
    //   1220: aload_0
    //   1221: getfield mTotalLength : I
    //   1224: aload_0
    //   1225: invokevirtual getSuggestedMinimumWidth : ()I
    //   1228: invokestatic max : (II)I
    //   1231: iload_1
    //   1232: iconst_0
    //   1233: invokestatic resolveSizeAndState : (III)I
    //   1236: istore #27
    //   1238: ldc_w 16777215
    //   1241: iload #27
    //   1243: iand
    //   1244: aload_0
    //   1245: getfield mTotalLength : I
    //   1248: isub
    //   1249: istore #10
    //   1251: iload #15
    //   1253: ifne -> 1382
    //   1256: iload #10
    //   1258: ifeq -> 1271
    //   1261: fload #21
    //   1263: fconst_0
    //   1264: fcmpl
    //   1265: ifle -> 1271
    //   1268: goto -> 1382
    //   1271: iload #16
    //   1273: iload #17
    //   1275: invokestatic max : (II)I
    //   1278: istore #12
    //   1280: iload #9
    //   1282: ifeq -> 1371
    //   1285: iload #4
    //   1287: ldc 1073741824
    //   1289: if_icmpeq -> 1371
    //   1292: iconst_0
    //   1293: istore #16
    //   1295: iload #16
    //   1297: iload_3
    //   1298: if_icmpge -> 1371
    //   1301: aload_0
    //   1302: iload #16
    //   1304: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1307: astore #7
    //   1309: aload #7
    //   1311: ifnull -> 1365
    //   1314: aload #7
    //   1316: invokevirtual getVisibility : ()I
    //   1319: bipush #8
    //   1321: if_icmpne -> 1327
    //   1324: goto -> 1365
    //   1327: aload #7
    //   1329: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1332: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   1335: getfield weight : F
    //   1338: fconst_0
    //   1339: fcmpl
    //   1340: ifle -> 1365
    //   1343: aload #7
    //   1345: iload #13
    //   1347: ldc 1073741824
    //   1349: invokestatic makeMeasureSpec : (II)I
    //   1352: aload #7
    //   1354: invokevirtual getMeasuredHeight : ()I
    //   1357: ldc 1073741824
    //   1359: invokestatic makeMeasureSpec : (II)I
    //   1362: invokevirtual measure : (II)V
    //   1365: iinc #16, 1
    //   1368: goto -> 1295
    //   1371: iload #12
    //   1373: istore #16
    //   1375: iload #20
    //   1377: istore #13
    //   1379: goto -> 2111
    //   1382: aload_0
    //   1383: getfield mWeightSum : F
    //   1386: fconst_0
    //   1387: fcmpl
    //   1388: ifle -> 1397
    //   1391: aload_0
    //   1392: getfield mWeightSum : F
    //   1395: fstore #21
    //   1397: aload #6
    //   1399: iconst_3
    //   1400: iconst_m1
    //   1401: iastore
    //   1402: aload #6
    //   1404: iconst_2
    //   1405: iconst_m1
    //   1406: iastore
    //   1407: aload #6
    //   1409: iconst_1
    //   1410: iconst_m1
    //   1411: iastore
    //   1412: aload #6
    //   1414: iconst_0
    //   1415: iconst_m1
    //   1416: iastore
    //   1417: aload #7
    //   1419: iconst_3
    //   1420: iconst_m1
    //   1421: iastore
    //   1422: aload #7
    //   1424: iconst_2
    //   1425: iconst_m1
    //   1426: iastore
    //   1427: aload #7
    //   1429: iconst_1
    //   1430: iconst_m1
    //   1431: iastore
    //   1432: aload #7
    //   1434: iconst_0
    //   1435: iconst_m1
    //   1436: iastore
    //   1437: aload_0
    //   1438: iconst_0
    //   1439: putfield mTotalLength : I
    //   1442: iconst_m1
    //   1443: istore #17
    //   1445: iconst_0
    //   1446: istore #14
    //   1448: iload #20
    //   1450: istore #13
    //   1452: iload #16
    //   1454: istore #12
    //   1456: iload #18
    //   1458: istore #20
    //   1460: iload #10
    //   1462: istore #16
    //   1464: iload #14
    //   1466: istore #18
    //   1468: iload #18
    //   1470: iload_3
    //   1471: if_icmpge -> 1987
    //   1474: aload_0
    //   1475: iload #18
    //   1477: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1480: astore #25
    //   1482: aload #25
    //   1484: ifnull -> 1981
    //   1487: aload #25
    //   1489: invokevirtual getVisibility : ()I
    //   1492: bipush #8
    //   1494: if_icmpne -> 1500
    //   1497: goto -> 1981
    //   1500: aload #25
    //   1502: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1505: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   1508: astore #22
    //   1510: aload #22
    //   1512: getfield weight : F
    //   1515: fstore #30
    //   1517: fload #30
    //   1519: fconst_0
    //   1520: fcmpl
    //   1521: ifle -> 1687
    //   1524: iload #16
    //   1526: i2f
    //   1527: fload #30
    //   1529: fmul
    //   1530: fload #21
    //   1532: fdiv
    //   1533: f2i
    //   1534: istore #15
    //   1536: iload_2
    //   1537: aload_0
    //   1538: invokevirtual getPaddingTop : ()I
    //   1541: aload_0
    //   1542: invokevirtual getPaddingBottom : ()I
    //   1545: iadd
    //   1546: aload #22
    //   1548: getfield topMargin : I
    //   1551: iadd
    //   1552: aload #22
    //   1554: getfield bottomMargin : I
    //   1557: iadd
    //   1558: aload #22
    //   1560: getfield height : I
    //   1563: invokestatic getChildMeasureSpec : (III)I
    //   1566: istore #23
    //   1568: aload #22
    //   1570: getfield width : I
    //   1573: ifne -> 1618
    //   1576: iload #4
    //   1578: ldc 1073741824
    //   1580: if_icmpeq -> 1586
    //   1583: goto -> 1618
    //   1586: iload #15
    //   1588: ifle -> 1598
    //   1591: iload #15
    //   1593: istore #14
    //   1595: goto -> 1601
    //   1598: iconst_0
    //   1599: istore #14
    //   1601: aload #25
    //   1603: iload #14
    //   1605: ldc 1073741824
    //   1607: invokestatic makeMeasureSpec : (II)I
    //   1610: iload #23
    //   1612: invokevirtual measure : (II)V
    //   1615: goto -> 1654
    //   1618: aload #25
    //   1620: invokevirtual getMeasuredWidth : ()I
    //   1623: iload #15
    //   1625: iadd
    //   1626: istore #10
    //   1628: iload #10
    //   1630: istore #14
    //   1632: iload #10
    //   1634: ifge -> 1640
    //   1637: iconst_0
    //   1638: istore #14
    //   1640: aload #25
    //   1642: iload #14
    //   1644: ldc 1073741824
    //   1646: invokestatic makeMeasureSpec : (II)I
    //   1649: iload #23
    //   1651: invokevirtual measure : (II)V
    //   1654: iload #20
    //   1656: aload #25
    //   1658: invokevirtual getMeasuredState : ()I
    //   1661: ldc_w -16777216
    //   1664: iand
    //   1665: invokestatic combineMeasuredStates : (II)I
    //   1668: istore #20
    //   1670: fload #21
    //   1672: fload #30
    //   1674: fsub
    //   1675: fstore #21
    //   1677: iload #16
    //   1679: iload #15
    //   1681: isub
    //   1682: istore #16
    //   1684: goto -> 1687
    //   1687: iload #11
    //   1689: ifeq -> 1728
    //   1692: aload_0
    //   1693: aload_0
    //   1694: getfield mTotalLength : I
    //   1697: aload #25
    //   1699: invokevirtual getMeasuredWidth : ()I
    //   1702: aload #22
    //   1704: getfield leftMargin : I
    //   1707: iadd
    //   1708: aload #22
    //   1710: getfield rightMargin : I
    //   1713: iadd
    //   1714: aload_0
    //   1715: aload #25
    //   1717: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1720: iadd
    //   1721: iadd
    //   1722: putfield mTotalLength : I
    //   1725: goto -> 1773
    //   1728: aload_0
    //   1729: getfield mTotalLength : I
    //   1732: istore #14
    //   1734: aload_0
    //   1735: iload #14
    //   1737: aload #25
    //   1739: invokevirtual getMeasuredWidth : ()I
    //   1742: iload #14
    //   1744: iadd
    //   1745: aload #22
    //   1747: getfield leftMargin : I
    //   1750: iadd
    //   1751: aload #22
    //   1753: getfield rightMargin : I
    //   1756: iadd
    //   1757: aload_0
    //   1758: aload #25
    //   1760: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1763: iadd
    //   1764: invokestatic max : (II)I
    //   1767: putfield mTotalLength : I
    //   1770: goto -> 1725
    //   1773: iload #5
    //   1775: ldc 1073741824
    //   1777: if_icmpeq -> 1795
    //   1780: aload #22
    //   1782: getfield height : I
    //   1785: iconst_m1
    //   1786: if_icmpne -> 1795
    //   1789: iconst_1
    //   1790: istore #14
    //   1792: goto -> 1798
    //   1795: iconst_0
    //   1796: istore #14
    //   1798: aload #22
    //   1800: getfield topMargin : I
    //   1803: aload #22
    //   1805: getfield bottomMargin : I
    //   1808: iadd
    //   1809: istore #23
    //   1811: aload #25
    //   1813: invokevirtual getMeasuredHeight : ()I
    //   1816: iload #23
    //   1818: iadd
    //   1819: istore #10
    //   1821: iload #17
    //   1823: iload #10
    //   1825: invokestatic max : (II)I
    //   1828: istore #15
    //   1830: iload #14
    //   1832: ifeq -> 1842
    //   1835: iload #23
    //   1837: istore #17
    //   1839: goto -> 1846
    //   1842: iload #10
    //   1844: istore #17
    //   1846: iload #12
    //   1848: iload #17
    //   1850: invokestatic max : (II)I
    //   1853: istore #17
    //   1855: iload #13
    //   1857: ifeq -> 1875
    //   1860: aload #22
    //   1862: getfield height : I
    //   1865: iconst_m1
    //   1866: if_icmpne -> 1875
    //   1869: iconst_1
    //   1870: istore #13
    //   1872: goto -> 1878
    //   1875: iconst_0
    //   1876: istore #13
    //   1878: iload #8
    //   1880: ifeq -> 1970
    //   1883: aload #25
    //   1885: invokevirtual getBaseline : ()I
    //   1888: istore #14
    //   1890: iload #14
    //   1892: iconst_m1
    //   1893: if_icmpeq -> 1970
    //   1896: aload #22
    //   1898: getfield gravity : I
    //   1901: ifge -> 1913
    //   1904: aload_0
    //   1905: getfield mGravity : I
    //   1908: istore #12
    //   1910: goto -> 1920
    //   1913: aload #22
    //   1915: getfield gravity : I
    //   1918: istore #12
    //   1920: iload #12
    //   1922: bipush #112
    //   1924: iand
    //   1925: iconst_4
    //   1926: ishr
    //   1927: bipush #-2
    //   1929: iand
    //   1930: iconst_1
    //   1931: ishr
    //   1932: istore #12
    //   1934: aload #6
    //   1936: iload #12
    //   1938: aload #6
    //   1940: iload #12
    //   1942: iaload
    //   1943: iload #14
    //   1945: invokestatic max : (II)I
    //   1948: iastore
    //   1949: aload #7
    //   1951: iload #12
    //   1953: aload #7
    //   1955: iload #12
    //   1957: iaload
    //   1958: iload #10
    //   1960: iload #14
    //   1962: isub
    //   1963: invokestatic max : (II)I
    //   1966: iastore
    //   1967: goto -> 1970
    //   1970: iload #17
    //   1972: istore #12
    //   1974: iload #15
    //   1976: istore #17
    //   1978: goto -> 1981
    //   1981: iinc #18, 1
    //   1984: goto -> 1468
    //   1987: aload_0
    //   1988: aload_0
    //   1989: getfield mTotalLength : I
    //   1992: aload_0
    //   1993: invokevirtual getPaddingLeft : ()I
    //   1996: aload_0
    //   1997: invokevirtual getPaddingRight : ()I
    //   2000: iadd
    //   2001: iadd
    //   2002: putfield mTotalLength : I
    //   2005: aload #6
    //   2007: iconst_1
    //   2008: iaload
    //   2009: iconst_m1
    //   2010: if_icmpne -> 2041
    //   2013: aload #6
    //   2015: iconst_0
    //   2016: iaload
    //   2017: iconst_m1
    //   2018: if_icmpne -> 2041
    //   2021: aload #6
    //   2023: iconst_2
    //   2024: iaload
    //   2025: iconst_m1
    //   2026: if_icmpne -> 2041
    //   2029: iload #17
    //   2031: istore #16
    //   2033: aload #6
    //   2035: iconst_3
    //   2036: iaload
    //   2037: iconst_m1
    //   2038: if_icmpeq -> 2099
    //   2041: iload #17
    //   2043: aload #6
    //   2045: iconst_3
    //   2046: iaload
    //   2047: aload #6
    //   2049: iconst_0
    //   2050: iaload
    //   2051: aload #6
    //   2053: iconst_1
    //   2054: iaload
    //   2055: aload #6
    //   2057: iconst_2
    //   2058: iaload
    //   2059: invokestatic max : (II)I
    //   2062: invokestatic max : (II)I
    //   2065: invokestatic max : (II)I
    //   2068: aload #7
    //   2070: iconst_3
    //   2071: iaload
    //   2072: aload #7
    //   2074: iconst_0
    //   2075: iaload
    //   2076: aload #7
    //   2078: iconst_1
    //   2079: iaload
    //   2080: aload #7
    //   2082: iconst_2
    //   2083: iaload
    //   2084: invokestatic max : (II)I
    //   2087: invokestatic max : (II)I
    //   2090: invokestatic max : (II)I
    //   2093: iadd
    //   2094: invokestatic max : (II)I
    //   2097: istore #16
    //   2099: iload #20
    //   2101: istore #18
    //   2103: iload #16
    //   2105: istore #14
    //   2107: iload #12
    //   2109: istore #16
    //   2111: iload #13
    //   2113: ifne -> 2126
    //   2116: iload #5
    //   2118: ldc 1073741824
    //   2120: if_icmpeq -> 2126
    //   2123: goto -> 2130
    //   2126: iload #14
    //   2128: istore #16
    //   2130: aload_0
    //   2131: iload #27
    //   2133: ldc_w -16777216
    //   2136: iload #18
    //   2138: iand
    //   2139: ior
    //   2140: iload #16
    //   2142: aload_0
    //   2143: invokevirtual getPaddingTop : ()I
    //   2146: aload_0
    //   2147: invokevirtual getPaddingBottom : ()I
    //   2150: iadd
    //   2151: iadd
    //   2152: aload_0
    //   2153: invokevirtual getSuggestedMinimumHeight : ()I
    //   2156: invokestatic max : (II)I
    //   2159: iload_2
    //   2160: iload #18
    //   2162: bipush #16
    //   2164: ishl
    //   2165: invokestatic resolveSizeAndState : (III)I
    //   2168: invokevirtual setMeasuredDimension : (II)V
    //   2171: iload #19
    //   2173: ifeq -> 2182
    //   2176: aload_0
    //   2177: iload_3
    //   2178: iload_1
    //   2179: invokespecial forceUniformHeight : (II)V
    //   2182: return
  }
  
  int measureNullChild(int paramInt) {
    return 0;
  }
  
  void measureVertical(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield mTotalLength : I
    //   5: aload_0
    //   6: invokevirtual getVirtualChildCount : ()I
    //   9: istore_3
    //   10: iload_1
    //   11: invokestatic getMode : (I)I
    //   14: istore #4
    //   16: iload_2
    //   17: invokestatic getMode : (I)I
    //   20: istore #5
    //   22: aload_0
    //   23: getfield mBaselineAlignedChildIndex : I
    //   26: istore #6
    //   28: aload_0
    //   29: getfield mUseLargestChild : Z
    //   32: istore #7
    //   34: iconst_0
    //   35: istore #8
    //   37: iload #8
    //   39: istore #9
    //   41: iload #9
    //   43: istore #10
    //   45: iload #10
    //   47: istore #11
    //   49: iload #11
    //   51: istore #12
    //   53: iload #12
    //   55: istore #13
    //   57: iload #13
    //   59: istore #14
    //   61: iload #14
    //   63: istore #15
    //   65: fconst_0
    //   66: fstore #16
    //   68: iconst_1
    //   69: istore #17
    //   71: iload #13
    //   73: iload_3
    //   74: if_icmpge -> 650
    //   77: aload_0
    //   78: iload #13
    //   80: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   83: astore #18
    //   85: aload #18
    //   87: ifnonnull -> 108
    //   90: aload_0
    //   91: aload_0
    //   92: getfield mTotalLength : I
    //   95: aload_0
    //   96: iload #13
    //   98: invokevirtual measureNullChild : (I)I
    //   101: iadd
    //   102: putfield mTotalLength : I
    //   105: goto -> 644
    //   108: aload #18
    //   110: invokevirtual getVisibility : ()I
    //   113: bipush #8
    //   115: if_icmpne -> 134
    //   118: iload #13
    //   120: aload_0
    //   121: aload #18
    //   123: iload #13
    //   125: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   128: iadd
    //   129: istore #13
    //   131: goto -> 644
    //   134: aload_0
    //   135: iload #13
    //   137: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   140: ifeq -> 156
    //   143: aload_0
    //   144: aload_0
    //   145: getfield mTotalLength : I
    //   148: aload_0
    //   149: getfield mDividerHeight : I
    //   152: iadd
    //   153: putfield mTotalLength : I
    //   156: aload #18
    //   158: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   161: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   164: astore #19
    //   166: fload #16
    //   168: aload #19
    //   170: getfield weight : F
    //   173: fadd
    //   174: fstore #16
    //   176: iload #5
    //   178: ldc 1073741824
    //   180: if_icmpne -> 236
    //   183: aload #19
    //   185: getfield height : I
    //   188: ifne -> 236
    //   191: aload #19
    //   193: getfield weight : F
    //   196: fconst_0
    //   197: fcmpl
    //   198: ifle -> 236
    //   201: aload_0
    //   202: getfield mTotalLength : I
    //   205: istore #14
    //   207: aload_0
    //   208: iload #14
    //   210: aload #19
    //   212: getfield topMargin : I
    //   215: iload #14
    //   217: iadd
    //   218: aload #19
    //   220: getfield bottomMargin : I
    //   223: iadd
    //   224: invokestatic max : (II)I
    //   227: putfield mTotalLength : I
    //   230: iconst_1
    //   231: istore #14
    //   233: goto -> 386
    //   236: aload #19
    //   238: getfield height : I
    //   241: ifne -> 267
    //   244: aload #19
    //   246: getfield weight : F
    //   249: fconst_0
    //   250: fcmpl
    //   251: ifle -> 267
    //   254: aload #19
    //   256: bipush #-2
    //   258: putfield height : I
    //   261: iconst_0
    //   262: istore #20
    //   264: goto -> 272
    //   267: ldc_w -2147483648
    //   270: istore #20
    //   272: fload #16
    //   274: fconst_0
    //   275: fcmpl
    //   276: ifne -> 288
    //   279: aload_0
    //   280: getfield mTotalLength : I
    //   283: istore #21
    //   285: goto -> 291
    //   288: iconst_0
    //   289: istore #21
    //   291: aload #18
    //   293: astore #22
    //   295: aload_0
    //   296: aload #18
    //   298: iload #13
    //   300: iload_1
    //   301: iconst_0
    //   302: iload_2
    //   303: iload #21
    //   305: invokevirtual measureChildBeforeLayout : (Landroid/view/View;IIIII)V
    //   308: iload #20
    //   310: ldc_w -2147483648
    //   313: if_icmpeq -> 323
    //   316: aload #19
    //   318: iload #20
    //   320: putfield height : I
    //   323: aload #22
    //   325: invokevirtual getMeasuredHeight : ()I
    //   328: istore #21
    //   330: aload_0
    //   331: getfield mTotalLength : I
    //   334: istore #20
    //   336: aload_0
    //   337: iload #20
    //   339: iload #20
    //   341: iload #21
    //   343: iadd
    //   344: aload #19
    //   346: getfield topMargin : I
    //   349: iadd
    //   350: aload #19
    //   352: getfield bottomMargin : I
    //   355: iadd
    //   356: aload_0
    //   357: aload #22
    //   359: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   362: iadd
    //   363: invokestatic max : (II)I
    //   366: putfield mTotalLength : I
    //   369: iload #7
    //   371: ifeq -> 386
    //   374: iload #21
    //   376: iload #10
    //   378: invokestatic max : (II)I
    //   381: istore #10
    //   383: goto -> 386
    //   386: iload #11
    //   388: istore #20
    //   390: iload #13
    //   392: istore #23
    //   394: iload #6
    //   396: iflt -> 416
    //   399: iload #6
    //   401: iload #23
    //   403: iconst_1
    //   404: iadd
    //   405: if_icmpne -> 416
    //   408: aload_0
    //   409: aload_0
    //   410: getfield mTotalLength : I
    //   413: putfield mBaselineChildTop : I
    //   416: iload #23
    //   418: iload #6
    //   420: if_icmpge -> 444
    //   423: aload #19
    //   425: getfield weight : F
    //   428: fconst_0
    //   429: fcmpl
    //   430: ifle -> 444
    //   433: new java/lang/RuntimeException
    //   436: dup
    //   437: ldc_w 'A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.'
    //   440: invokespecial <init> : (Ljava/lang/String;)V
    //   443: athrow
    //   444: iload #4
    //   446: ldc 1073741824
    //   448: if_icmpeq -> 470
    //   451: aload #19
    //   453: getfield width : I
    //   456: iconst_m1
    //   457: if_icmpne -> 470
    //   460: iconst_1
    //   461: istore #13
    //   463: iload #13
    //   465: istore #15
    //   467: goto -> 473
    //   470: iconst_0
    //   471: istore #13
    //   473: aload #19
    //   475: getfield leftMargin : I
    //   478: aload #19
    //   480: getfield rightMargin : I
    //   483: iadd
    //   484: istore #21
    //   486: aload #18
    //   488: invokevirtual getMeasuredWidth : ()I
    //   491: iload #21
    //   493: iadd
    //   494: istore #24
    //   496: iload #9
    //   498: iload #24
    //   500: invokestatic max : (II)I
    //   503: istore #9
    //   505: iload #8
    //   507: aload #18
    //   509: invokevirtual getMeasuredState : ()I
    //   512: invokestatic combineMeasuredStates : (II)I
    //   515: istore #8
    //   517: iload #17
    //   519: ifeq -> 537
    //   522: aload #19
    //   524: getfield width : I
    //   527: iconst_m1
    //   528: if_icmpne -> 537
    //   531: iconst_1
    //   532: istore #11
    //   534: goto -> 540
    //   537: iconst_0
    //   538: istore #11
    //   540: aload #19
    //   542: getfield weight : F
    //   545: fconst_0
    //   546: fcmpl
    //   547: ifle -> 585
    //   550: iload #13
    //   552: ifeq -> 558
    //   555: goto -> 565
    //   558: iload #24
    //   560: istore #21
    //   562: goto -> 555
    //   565: iload #20
    //   567: iload #21
    //   569: invokestatic max : (II)I
    //   572: istore #13
    //   574: iload #12
    //   576: istore #17
    //   578: iload #13
    //   580: istore #12
    //   582: goto -> 607
    //   585: iload #13
    //   587: ifeq -> 594
    //   590: iload #21
    //   592: istore #24
    //   594: iload #12
    //   596: iload #24
    //   598: invokestatic max : (II)I
    //   601: istore #17
    //   603: iload #20
    //   605: istore #12
    //   607: aload_0
    //   608: aload #18
    //   610: iload #23
    //   612: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   615: istore #20
    //   617: iload #11
    //   619: istore #13
    //   621: iload #12
    //   623: istore #11
    //   625: iload #20
    //   627: iload #23
    //   629: iadd
    //   630: istore #20
    //   632: iload #17
    //   634: istore #12
    //   636: iload #13
    //   638: istore #17
    //   640: iload #20
    //   642: istore #13
    //   644: iinc #13, 1
    //   647: goto -> 71
    //   650: iload #8
    //   652: istore #20
    //   654: iload #9
    //   656: istore #8
    //   658: aload_0
    //   659: getfield mTotalLength : I
    //   662: ifle -> 689
    //   665: aload_0
    //   666: iload_3
    //   667: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   670: ifeq -> 689
    //   673: aload_0
    //   674: aload_0
    //   675: getfield mTotalLength : I
    //   678: aload_0
    //   679: getfield mDividerHeight : I
    //   682: iadd
    //   683: putfield mTotalLength : I
    //   686: goto -> 689
    //   689: iload_3
    //   690: istore #9
    //   692: iload #7
    //   694: ifeq -> 853
    //   697: iload #5
    //   699: istore #13
    //   701: iload #13
    //   703: ldc_w -2147483648
    //   706: if_icmpeq -> 726
    //   709: iload #8
    //   711: istore_3
    //   712: iload #13
    //   714: ifne -> 720
    //   717: goto -> 726
    //   720: iload_3
    //   721: istore #8
    //   723: goto -> 853
    //   726: aload_0
    //   727: iconst_0
    //   728: putfield mTotalLength : I
    //   731: iconst_0
    //   732: istore #13
    //   734: iload #8
    //   736: istore_3
    //   737: iload #13
    //   739: iload #9
    //   741: if_icmpge -> 720
    //   744: aload_0
    //   745: iload #13
    //   747: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   750: astore #18
    //   752: aload #18
    //   754: ifnonnull -> 775
    //   757: aload_0
    //   758: aload_0
    //   759: getfield mTotalLength : I
    //   762: aload_0
    //   763: iload #13
    //   765: invokevirtual measureNullChild : (I)I
    //   768: iadd
    //   769: putfield mTotalLength : I
    //   772: goto -> 798
    //   775: aload #18
    //   777: invokevirtual getVisibility : ()I
    //   780: bipush #8
    //   782: if_icmpne -> 801
    //   785: iload #13
    //   787: aload_0
    //   788: aload #18
    //   790: iload #13
    //   792: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   795: iadd
    //   796: istore #13
    //   798: goto -> 847
    //   801: aload #18
    //   803: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   806: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   809: astore #22
    //   811: aload_0
    //   812: getfield mTotalLength : I
    //   815: istore_3
    //   816: aload_0
    //   817: iload_3
    //   818: iload_3
    //   819: iload #10
    //   821: iadd
    //   822: aload #22
    //   824: getfield topMargin : I
    //   827: iadd
    //   828: aload #22
    //   830: getfield bottomMargin : I
    //   833: iadd
    //   834: aload_0
    //   835: aload #18
    //   837: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   840: iadd
    //   841: invokestatic max : (II)I
    //   844: putfield mTotalLength : I
    //   847: iinc #13, 1
    //   850: goto -> 734
    //   853: iload #5
    //   855: istore_3
    //   856: aload_0
    //   857: aload_0
    //   858: getfield mTotalLength : I
    //   861: aload_0
    //   862: invokevirtual getPaddingTop : ()I
    //   865: aload_0
    //   866: invokevirtual getPaddingBottom : ()I
    //   869: iadd
    //   870: iadd
    //   871: putfield mTotalLength : I
    //   874: aload_0
    //   875: getfield mTotalLength : I
    //   878: aload_0
    //   879: invokevirtual getSuggestedMinimumHeight : ()I
    //   882: invokestatic max : (II)I
    //   885: iload_2
    //   886: iconst_0
    //   887: invokestatic resolveSizeAndState : (III)I
    //   890: istore #21
    //   892: ldc_w 16777215
    //   895: iload #21
    //   897: iand
    //   898: aload_0
    //   899: getfield mTotalLength : I
    //   902: isub
    //   903: istore #5
    //   905: iload #14
    //   907: ifne -> 1036
    //   910: iload #5
    //   912: ifeq -> 925
    //   915: fload #16
    //   917: fconst_0
    //   918: fcmpl
    //   919: ifle -> 925
    //   922: goto -> 1036
    //   925: iload #12
    //   927: iload #11
    //   929: invokestatic max : (II)I
    //   932: istore #5
    //   934: iload #7
    //   936: ifeq -> 1025
    //   939: iload_3
    //   940: ldc 1073741824
    //   942: if_icmpeq -> 1025
    //   945: iconst_0
    //   946: istore #11
    //   948: iload #11
    //   950: iload #9
    //   952: if_icmpge -> 1025
    //   955: aload_0
    //   956: iload #11
    //   958: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   961: astore #18
    //   963: aload #18
    //   965: ifnull -> 1019
    //   968: aload #18
    //   970: invokevirtual getVisibility : ()I
    //   973: bipush #8
    //   975: if_icmpne -> 981
    //   978: goto -> 1019
    //   981: aload #18
    //   983: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   986: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   989: getfield weight : F
    //   992: fconst_0
    //   993: fcmpl
    //   994: ifle -> 1019
    //   997: aload #18
    //   999: aload #18
    //   1001: invokevirtual getMeasuredWidth : ()I
    //   1004: ldc 1073741824
    //   1006: invokestatic makeMeasureSpec : (II)I
    //   1009: iload #10
    //   1011: ldc 1073741824
    //   1013: invokestatic makeMeasureSpec : (II)I
    //   1016: invokevirtual measure : (II)V
    //   1019: iinc #11, 1
    //   1022: goto -> 948
    //   1025: iload #8
    //   1027: istore #12
    //   1029: iload #5
    //   1031: istore #11
    //   1033: goto -> 1512
    //   1036: aload_0
    //   1037: getfield mWeightSum : F
    //   1040: fconst_0
    //   1041: fcmpl
    //   1042: ifle -> 1051
    //   1045: aload_0
    //   1046: getfield mWeightSum : F
    //   1049: fstore #16
    //   1051: aload_0
    //   1052: iconst_0
    //   1053: putfield mTotalLength : I
    //   1056: iconst_0
    //   1057: istore #13
    //   1059: iload #5
    //   1061: istore #10
    //   1063: iload #12
    //   1065: istore #5
    //   1067: iload #8
    //   1069: istore #12
    //   1071: iload #20
    //   1073: istore #11
    //   1075: iload #13
    //   1077: istore #8
    //   1079: iload #8
    //   1081: iload #9
    //   1083: if_icmpge -> 1486
    //   1086: aload_0
    //   1087: iload #8
    //   1089: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1092: astore #22
    //   1094: aload #22
    //   1096: invokevirtual getVisibility : ()I
    //   1099: bipush #8
    //   1101: if_icmpne -> 1107
    //   1104: goto -> 1480
    //   1107: aload #22
    //   1109: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1112: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   1115: astore #18
    //   1117: aload #18
    //   1119: getfield weight : F
    //   1122: fstore #25
    //   1124: fload #25
    //   1126: fconst_0
    //   1127: fcmpl
    //   1128: ifle -> 1317
    //   1131: iload #10
    //   1133: i2f
    //   1134: fload #25
    //   1136: fmul
    //   1137: fload #16
    //   1139: fdiv
    //   1140: f2i
    //   1141: istore #14
    //   1143: aload_0
    //   1144: invokevirtual getPaddingLeft : ()I
    //   1147: istore #20
    //   1149: aload_0
    //   1150: invokevirtual getPaddingRight : ()I
    //   1153: istore #24
    //   1155: iload #10
    //   1157: iload #14
    //   1159: isub
    //   1160: istore #13
    //   1162: aload #18
    //   1164: getfield leftMargin : I
    //   1167: istore #6
    //   1169: aload #18
    //   1171: getfield rightMargin : I
    //   1174: istore #10
    //   1176: aload #18
    //   1178: getfield width : I
    //   1181: istore #23
    //   1183: fload #16
    //   1185: fload #25
    //   1187: fsub
    //   1188: fstore #16
    //   1190: iload_1
    //   1191: iload #20
    //   1193: iload #24
    //   1195: iadd
    //   1196: iload #6
    //   1198: iadd
    //   1199: iload #10
    //   1201: iadd
    //   1202: iload #23
    //   1204: invokestatic getChildMeasureSpec : (III)I
    //   1207: istore #20
    //   1209: aload #18
    //   1211: getfield height : I
    //   1214: ifne -> 1258
    //   1217: iload_3
    //   1218: ldc 1073741824
    //   1220: if_icmpeq -> 1226
    //   1223: goto -> 1258
    //   1226: iload #14
    //   1228: ifle -> 1238
    //   1231: iload #14
    //   1233: istore #10
    //   1235: goto -> 1241
    //   1238: iconst_0
    //   1239: istore #10
    //   1241: aload #22
    //   1243: iload #20
    //   1245: iload #10
    //   1247: ldc 1073741824
    //   1249: invokestatic makeMeasureSpec : (II)I
    //   1252: invokevirtual measure : (II)V
    //   1255: goto -> 1294
    //   1258: aload #22
    //   1260: invokevirtual getMeasuredHeight : ()I
    //   1263: iload #14
    //   1265: iadd
    //   1266: istore #14
    //   1268: iload #14
    //   1270: istore #10
    //   1272: iload #14
    //   1274: ifge -> 1280
    //   1277: iconst_0
    //   1278: istore #10
    //   1280: aload #22
    //   1282: iload #20
    //   1284: iload #10
    //   1286: ldc 1073741824
    //   1288: invokestatic makeMeasureSpec : (II)I
    //   1291: invokevirtual measure : (II)V
    //   1294: iload #11
    //   1296: aload #22
    //   1298: invokevirtual getMeasuredState : ()I
    //   1301: sipush #-256
    //   1304: iand
    //   1305: invokestatic combineMeasuredStates : (II)I
    //   1308: istore #11
    //   1310: iload #13
    //   1312: istore #10
    //   1314: goto -> 1317
    //   1317: aload #18
    //   1319: getfield leftMargin : I
    //   1322: aload #18
    //   1324: getfield rightMargin : I
    //   1327: iadd
    //   1328: istore #14
    //   1330: aload #22
    //   1332: invokevirtual getMeasuredWidth : ()I
    //   1335: iload #14
    //   1337: iadd
    //   1338: istore #20
    //   1340: iload #12
    //   1342: iload #20
    //   1344: invokestatic max : (II)I
    //   1347: istore #13
    //   1349: iload #4
    //   1351: ldc 1073741824
    //   1353: if_icmpeq -> 1371
    //   1356: aload #18
    //   1358: getfield width : I
    //   1361: iconst_m1
    //   1362: if_icmpne -> 1371
    //   1365: iconst_1
    //   1366: istore #12
    //   1368: goto -> 1374
    //   1371: iconst_0
    //   1372: istore #12
    //   1374: iload #12
    //   1376: ifeq -> 1386
    //   1379: iload #14
    //   1381: istore #12
    //   1383: goto -> 1390
    //   1386: iload #20
    //   1388: istore #12
    //   1390: iload #5
    //   1392: iload #12
    //   1394: invokestatic max : (II)I
    //   1397: istore #14
    //   1399: iload #17
    //   1401: ifeq -> 1419
    //   1404: aload #18
    //   1406: getfield width : I
    //   1409: iconst_m1
    //   1410: if_icmpne -> 1419
    //   1413: iconst_1
    //   1414: istore #5
    //   1416: goto -> 1422
    //   1419: iconst_0
    //   1420: istore #5
    //   1422: aload_0
    //   1423: getfield mTotalLength : I
    //   1426: istore #17
    //   1428: aload_0
    //   1429: iload #17
    //   1431: aload #22
    //   1433: invokevirtual getMeasuredHeight : ()I
    //   1436: iload #17
    //   1438: iadd
    //   1439: aload #18
    //   1441: getfield topMargin : I
    //   1444: iadd
    //   1445: aload #18
    //   1447: getfield bottomMargin : I
    //   1450: iadd
    //   1451: aload_0
    //   1452: aload #22
    //   1454: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1457: iadd
    //   1458: invokestatic max : (II)I
    //   1461: putfield mTotalLength : I
    //   1464: iload #13
    //   1466: istore #12
    //   1468: iload #14
    //   1470: istore #13
    //   1472: iload #5
    //   1474: istore #17
    //   1476: iload #13
    //   1478: istore #5
    //   1480: iinc #8, 1
    //   1483: goto -> 1079
    //   1486: aload_0
    //   1487: aload_0
    //   1488: getfield mTotalLength : I
    //   1491: aload_0
    //   1492: invokevirtual getPaddingTop : ()I
    //   1495: aload_0
    //   1496: invokevirtual getPaddingBottom : ()I
    //   1499: iadd
    //   1500: iadd
    //   1501: putfield mTotalLength : I
    //   1504: iload #11
    //   1506: istore #20
    //   1508: iload #5
    //   1510: istore #11
    //   1512: iload #17
    //   1514: ifne -> 1527
    //   1517: iload #4
    //   1519: ldc 1073741824
    //   1521: if_icmpeq -> 1527
    //   1524: goto -> 1531
    //   1527: iload #12
    //   1529: istore #11
    //   1531: aload_0
    //   1532: iload #11
    //   1534: aload_0
    //   1535: invokevirtual getPaddingLeft : ()I
    //   1538: aload_0
    //   1539: invokevirtual getPaddingRight : ()I
    //   1542: iadd
    //   1543: iadd
    //   1544: aload_0
    //   1545: invokevirtual getSuggestedMinimumWidth : ()I
    //   1548: invokestatic max : (II)I
    //   1551: iload_1
    //   1552: iload #20
    //   1554: invokestatic resolveSizeAndState : (III)I
    //   1557: iload #21
    //   1559: invokevirtual setMeasuredDimension : (II)V
    //   1562: iload #15
    //   1564: ifeq -> 1574
    //   1567: aload_0
    //   1568: iload #9
    //   1570: iload_2
    //   1571: invokespecial forceUniformWidth : (II)V
    //   1574: return
  }
  
  protected void onDraw(Canvas paramCanvas) {
    if (this.mDivider == null)
      return; 
    if (this.mOrientation == 1) {
      drawDividersVertical(paramCanvas);
    } else {
      drawDividersHorizontal(paramCanvas);
    } 
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mOrientation == 1) {
      layoutVertical(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      layoutHorizontal(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mOrientation == 1) {
      measureVertical(paramInt1, paramInt2);
    } else {
      measureHorizontal(paramInt1, paramInt2);
    } 
  }
  
  public void setBaselineAligned(boolean paramBoolean) {
    this.mBaselineAligned = paramBoolean;
  }
  
  public void setBaselineAlignedChildIndex(int paramInt) {
    if (paramInt < 0 || paramInt >= getChildCount()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("base aligned child index out of range (0, ");
      stringBuilder.append(getChildCount());
      stringBuilder.append(")");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    this.mBaselineAlignedChildIndex = paramInt;
  }
  
  public void setDividerDrawable(Drawable paramDrawable) {
    if (paramDrawable == this.mDivider)
      return; 
    this.mDivider = paramDrawable;
    boolean bool = false;
    if (paramDrawable != null) {
      this.mDividerWidth = paramDrawable.getIntrinsicWidth();
      this.mDividerHeight = paramDrawable.getIntrinsicHeight();
    } else {
      this.mDividerWidth = 0;
      this.mDividerHeight = 0;
    } 
    if (paramDrawable == null)
      bool = true; 
    setWillNotDraw(bool);
    requestLayout();
  }
  
  public void setDividerPadding(int paramInt) {
    this.mDividerPadding = paramInt;
  }
  
  public void setGravity(int paramInt) {
    if (this.mGravity != paramInt) {
      int i = paramInt;
      if ((0x800007 & paramInt) == 0)
        i = paramInt | 0x800003; 
      paramInt = i;
      if ((i & 0x70) == 0)
        paramInt = i | 0x30; 
      this.mGravity = paramInt;
      requestLayout();
    } 
  }
  
  public void setHorizontalGravity(int paramInt) {
    paramInt &= 0x800007;
    if ((0x800007 & this.mGravity) != paramInt) {
      this.mGravity = paramInt | this.mGravity & 0xFF7FFFF8;
      requestLayout();
    } 
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean) {
    this.mUseLargestChild = paramBoolean;
  }
  
  public void setOrientation(int paramInt) {
    if (this.mOrientation != paramInt) {
      this.mOrientation = paramInt;
      requestLayout();
    } 
  }
  
  public void setShowDividers(int paramInt) {
    if (paramInt != this.mShowDividers)
      requestLayout(); 
    this.mShowDividers = paramInt;
  }
  
  public void setVerticalGravity(int paramInt) {
    paramInt &= 0x70;
    if ((this.mGravity & 0x70) != paramInt) {
      this.mGravity = paramInt | this.mGravity & 0xFFFFFF8F;
      requestLayout();
    } 
  }
  
  public void setWeightSum(float paramFloat) {
    this.mWeightSum = Math.max(0.0F, paramFloat);
  }
  
  public boolean shouldDelayChildPressedState() {
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DividerMode {}
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int gravity = -1;
    
    public float weight;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.weight = 0.0F;
    }
    
    public LayoutParams(int param1Int1, int param1Int2, float param1Float) {
      super(param1Int1, param1Int2);
      this.weight = param1Float;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.LinearLayoutCompat_Layout);
      this.weight = typedArray.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
      this.gravity = typedArray.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
      typedArray.recycle();
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.weight = param1LayoutParams.weight;
      this.gravity = param1LayoutParams.gravity;
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface OrientationMode {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\LinearLayoutCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */