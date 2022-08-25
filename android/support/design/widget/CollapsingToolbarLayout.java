package android.support.design.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

public class CollapsingToolbarLayout extends FrameLayout {
  private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
  
  final CollapsingTextHelper collapsingTextHelper = new CollapsingTextHelper((View)this);
  
  private boolean collapsingTitleEnabled;
  
  private Drawable contentScrim;
  
  int currentOffset;
  
  private boolean drawCollapsingTitle;
  
  private View dummyView;
  
  private int expandedMarginBottom;
  
  private int expandedMarginEnd;
  
  private int expandedMarginStart;
  
  private int expandedMarginTop;
  
  WindowInsetsCompat lastInsets;
  
  private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;
  
  private boolean refreshToolbar = true;
  
  private int scrimAlpha;
  
  private long scrimAnimationDuration;
  
  private ValueAnimator scrimAnimator;
  
  private int scrimVisibleHeightTrigger = -1;
  
  private boolean scrimsAreShown;
  
  Drawable statusBarScrim;
  
  private final Rect tmpRect = new Rect();
  
  private Toolbar toolbar;
  
  private View toolbarDirectChild;
  
  private int toolbarId;
  
  public CollapsingToolbarLayout(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public CollapsingToolbarLayout(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CollapsingToolbarLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
    TypedArray typedArray = ThemeEnforcement.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.CollapsingToolbarLayout, paramInt, R.style.Widget_Design_CollapsingToolbar, new int[0]);
    this.collapsingTextHelper.setExpandedTextGravity(typedArray.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
    this.collapsingTextHelper.setCollapsedTextGravity(typedArray.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
    paramInt = typedArray.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
    this.expandedMarginBottom = paramInt;
    this.expandedMarginEnd = paramInt;
    this.expandedMarginTop = paramInt;
    this.expandedMarginStart = paramInt;
    if (typedArray.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart))
      this.expandedMarginStart = typedArray.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0); 
    if (typedArray.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd))
      this.expandedMarginEnd = typedArray.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0); 
    if (typedArray.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop))
      this.expandedMarginTop = typedArray.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0); 
    if (typedArray.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom))
      this.expandedMarginBottom = typedArray.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0); 
    this.collapsingTitleEnabled = typedArray.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, true);
    setTitle(typedArray.getText(R.styleable.CollapsingToolbarLayout_title));
    this.collapsingTextHelper.setExpandedTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
    this.collapsingTextHelper.setCollapsedTextAppearance(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
    if (typedArray.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance))
      this.collapsingTextHelper.setExpandedTextAppearance(typedArray.getResourceId(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0)); 
    if (typedArray.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance))
      this.collapsingTextHelper.setCollapsedTextAppearance(typedArray.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0)); 
    this.scrimVisibleHeightTrigger = typedArray.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
    this.scrimAnimationDuration = typedArray.getInt(R.styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
    setContentScrim(typedArray.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
    setStatusBarScrim(typedArray.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
    this.toolbarId = typedArray.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
    typedArray.recycle();
    setWillNotDraw(false);
    ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
          public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
            return CollapsingToolbarLayout.this.onWindowInsetChanged(param1WindowInsetsCompat);
          }
        });
  }
  
  private void animateScrim(int paramInt) {
    ensureToolbar();
    if (this.scrimAnimator == null) {
      TimeInterpolator timeInterpolator;
      this.scrimAnimator = new ValueAnimator();
      this.scrimAnimator.setDuration(this.scrimAnimationDuration);
      ValueAnimator valueAnimator = this.scrimAnimator;
      if (paramInt > this.scrimAlpha) {
        timeInterpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
      } else {
        timeInterpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
      } 
      valueAnimator.setInterpolator(timeInterpolator);
      this.scrimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
              CollapsingToolbarLayout.this.setScrimAlpha(((Integer)param1ValueAnimator.getAnimatedValue()).intValue());
            }
          });
    } else if (this.scrimAnimator.isRunning()) {
      this.scrimAnimator.cancel();
    } 
    this.scrimAnimator.setIntValues(new int[] { this.scrimAlpha, paramInt });
    this.scrimAnimator.start();
  }
  
  private void ensureToolbar() {
    if (!this.refreshToolbar)
      return; 
    View view = null;
    this.toolbar = null;
    this.toolbarDirectChild = null;
    if (this.toolbarId != -1) {
      this.toolbar = (Toolbar)findViewById(this.toolbarId);
      if (this.toolbar != null)
        this.toolbarDirectChild = findDirectChild((View)this.toolbar); 
    } 
    if (this.toolbar == null) {
      Toolbar toolbar;
      int i = getChildCount();
      byte b = 0;
      while (true) {
        View view1 = view;
        if (b < i) {
          view1 = getChildAt(b);
          if (view1 instanceof Toolbar) {
            toolbar = (Toolbar)view1;
            break;
          } 
          b++;
          continue;
        } 
        break;
      } 
      this.toolbar = toolbar;
    } 
    updateDummyView();
    this.refreshToolbar = false;
  }
  
  private View findDirectChild(View paramView) {
    ViewParent viewParent2 = paramView.getParent();
    View view = paramView;
    for (ViewParent viewParent1 = viewParent2; viewParent1 != this && viewParent1 != null; viewParent1 = viewParent1.getParent()) {
      if (viewParent1 instanceof View)
        view = (View)viewParent1; 
    } 
    return view;
  }
  
  private static int getHeightWithMargins(@NonNull View paramView) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
      ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)layoutParams;
      return paramView.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    } 
    return paramView.getHeight();
  }
  
  static ViewOffsetHelper getViewOffsetHelper(View paramView) {
    ViewOffsetHelper viewOffsetHelper1 = (ViewOffsetHelper)paramView.getTag(R.id.view_offset_helper);
    ViewOffsetHelper viewOffsetHelper2 = viewOffsetHelper1;
    if (viewOffsetHelper1 == null) {
      viewOffsetHelper2 = new ViewOffsetHelper(paramView);
      paramView.setTag(R.id.view_offset_helper, viewOffsetHelper2);
    } 
    return viewOffsetHelper2;
  }
  
  private boolean isToolbarChild(View paramView) {
    View view = this.toolbarDirectChild;
    boolean bool = false;
    if ((this.toolbarDirectChild == this) ? (paramView == this.toolbar) : (paramView == this.toolbarDirectChild))
      bool = true; 
    return bool;
  }
  
  private void updateContentDescriptionFromTitle() {
    setContentDescription(getTitle());
  }
  
  private void updateDummyView() {
    if (!this.collapsingTitleEnabled && this.dummyView != null) {
      ViewParent viewParent = this.dummyView.getParent();
      if (viewParent instanceof ViewGroup)
        ((ViewGroup)viewParent).removeView(this.dummyView); 
    } 
    if (this.collapsingTitleEnabled && this.toolbar != null) {
      if (this.dummyView == null)
        this.dummyView = new View(getContext()); 
      if (this.dummyView.getParent() == null)
        this.toolbar.addView(this.dummyView, -1, -1); 
    } 
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  public void draw(Canvas paramCanvas) {
    super.draw(paramCanvas);
    ensureToolbar();
    if (this.toolbar == null && this.contentScrim != null && this.scrimAlpha > 0) {
      this.contentScrim.mutate().setAlpha(this.scrimAlpha);
      this.contentScrim.draw(paramCanvas);
    } 
    if (this.collapsingTitleEnabled && this.drawCollapsingTitle)
      this.collapsingTextHelper.draw(paramCanvas); 
    if (this.statusBarScrim != null && this.scrimAlpha > 0) {
      byte b;
      if (this.lastInsets != null) {
        b = this.lastInsets.getSystemWindowInsetTop();
      } else {
        b = 0;
      } 
      if (b) {
        this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), b - this.currentOffset);
        this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
        this.statusBarScrim.draw(paramCanvas);
      } 
    } 
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    boolean bool2;
    Drawable drawable = this.contentScrim;
    boolean bool1 = true;
    if (drawable != null && this.scrimAlpha > 0 && isToolbarChild(paramView)) {
      this.contentScrim.mutate().setAlpha(this.scrimAlpha);
      this.contentScrim.draw(paramCanvas);
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool3 = bool1;
    if (!super.drawChild(paramCanvas, paramView, paramLong))
      if (bool2) {
        bool3 = bool1;
      } else {
        bool3 = false;
      }  
    return bool3;
  }
  
  protected void drawableStateChanged() {
    boolean bool1;
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    Drawable drawable = this.statusBarScrim;
    int i = 0;
    int j = i;
    if (drawable != null) {
      j = i;
      if (drawable.isStateful())
        j = false | drawable.setState(arrayOfInt); 
    } 
    drawable = this.contentScrim;
    i = j;
    if (drawable != null) {
      i = j;
      if (drawable.isStateful())
        bool1 = j | drawable.setState(arrayOfInt); 
    } 
    boolean bool2 = bool1;
    if (this.collapsingTextHelper != null)
      bool2 = bool1 | this.collapsingTextHelper.setState(arrayOfInt); 
    if (bool2)
      invalidate(); 
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(-1, -1);
  }
  
  public FrameLayout.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getCollapsedTitleGravity() {
    return this.collapsingTextHelper.getCollapsedTextGravity();
  }
  
  @NonNull
  public Typeface getCollapsedTitleTypeface() {
    return this.collapsingTextHelper.getCollapsedTypeface();
  }
  
  @Nullable
  public Drawable getContentScrim() {
    return this.contentScrim;
  }
  
  public int getExpandedTitleGravity() {
    return this.collapsingTextHelper.getExpandedTextGravity();
  }
  
  public int getExpandedTitleMarginBottom() {
    return this.expandedMarginBottom;
  }
  
  public int getExpandedTitleMarginEnd() {
    return this.expandedMarginEnd;
  }
  
  public int getExpandedTitleMarginStart() {
    return this.expandedMarginStart;
  }
  
  public int getExpandedTitleMarginTop() {
    return this.expandedMarginTop;
  }
  
  @NonNull
  public Typeface getExpandedTitleTypeface() {
    return this.collapsingTextHelper.getExpandedTypeface();
  }
  
  final int getMaxOffsetForPinChild(View paramView) {
    ViewOffsetHelper viewOffsetHelper = getViewOffsetHelper(paramView);
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    return getHeight() - viewOffsetHelper.getLayoutTop() - paramView.getHeight() - layoutParams.bottomMargin;
  }
  
  int getScrimAlpha() {
    return this.scrimAlpha;
  }
  
  public long getScrimAnimationDuration() {
    return this.scrimAnimationDuration;
  }
  
  public int getScrimVisibleHeightTrigger() {
    byte b;
    if (this.scrimVisibleHeightTrigger >= 0)
      return this.scrimVisibleHeightTrigger; 
    if (this.lastInsets != null) {
      b = this.lastInsets.getSystemWindowInsetTop();
    } else {
      b = 0;
    } 
    int i = ViewCompat.getMinimumHeight((View)this);
    return (i > 0) ? Math.min(i * 2 + b, getHeight()) : (getHeight() / 3);
  }
  
  @Nullable
  public Drawable getStatusBarScrim() {
    return this.statusBarScrim;
  }
  
  @Nullable
  public CharSequence getTitle() {
    CharSequence charSequence;
    if (this.collapsingTitleEnabled) {
      charSequence = this.collapsingTextHelper.getText();
    } else {
      charSequence = null;
    } 
    return charSequence;
  }
  
  public boolean isTitleEnabled() {
    return this.collapsingTitleEnabled;
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    ViewParent viewParent = getParent();
    if (viewParent instanceof AppBarLayout) {
      ViewCompat.setFitsSystemWindows((View)this, ViewCompat.getFitsSystemWindows((View)viewParent));
      if (this.onOffsetChangedListener == null)
        this.onOffsetChangedListener = new OffsetUpdateListener(); 
      ((AppBarLayout)viewParent).addOnOffsetChangedListener(this.onOffsetChangedListener);
      ViewCompat.requestApplyInsets((View)this);
    } 
  }
  
  protected void onDetachedFromWindow() {
    ViewParent viewParent = getParent();
    if (this.onOffsetChangedListener != null && viewParent instanceof AppBarLayout)
      ((AppBarLayout)viewParent).removeOnOffsetChangedListener(this.onOffsetChangedListener); 
    super.onDetachedFromWindow();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    WindowInsetsCompat windowInsetsCompat = this.lastInsets;
    boolean bool = false;
    if (windowInsetsCompat != null) {
      int i = this.lastInsets.getSystemWindowInsetTop();
      int j = getChildCount();
      for (byte b = 0; b < j; b++) {
        View view = getChildAt(b);
        if (!ViewCompat.getFitsSystemWindows(view) && view.getTop() < i)
          ViewCompat.offsetTopAndBottom(view, i); 
      } 
    } 
    if (this.collapsingTitleEnabled && this.dummyView != null) {
      paramBoolean = ViewCompat.isAttachedToWindow(this.dummyView);
      int i = 1;
      if (paramBoolean && this.dummyView.getVisibility() == 0) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      } 
      this.drawCollapsingTitle = paramBoolean;
      if (this.drawCollapsingTitle) {
        Toolbar toolbar;
        int j;
        if (ViewCompat.getLayoutDirection((View)this) != 1)
          i = 0; 
        if (this.toolbarDirectChild != null) {
          View view = this.toolbarDirectChild;
        } else {
          toolbar = this.toolbar;
        } 
        int m = getMaxOffsetForPinChild((View)toolbar);
        DescendantOffsetUtils.getDescendantRect((ViewGroup)this, this.dummyView, this.tmpRect);
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        int n = this.tmpRect.left;
        if (i) {
          j = this.toolbar.getTitleMarginEnd();
        } else {
          j = this.toolbar.getTitleMarginStart();
        } 
        int i1 = this.tmpRect.top;
        int i2 = this.toolbar.getTitleMarginTop();
        int i3 = this.tmpRect.right;
        if (i) {
          k = this.toolbar.getTitleMarginStart();
        } else {
          k = this.toolbar.getTitleMarginEnd();
        } 
        collapsingTextHelper.setCollapsedBounds(n + j, i1 + m + i2, i3 + k, this.tmpRect.bottom + m - this.toolbar.getTitleMarginBottom());
        collapsingTextHelper = this.collapsingTextHelper;
        if (i) {
          j = this.expandedMarginEnd;
        } else {
          j = this.expandedMarginStart;
        } 
        int k = this.tmpRect.top;
        n = this.expandedMarginTop;
        if (i) {
          i = this.expandedMarginStart;
        } else {
          i = this.expandedMarginEnd;
        } 
        collapsingTextHelper.setExpandedBounds(j, k + n, paramInt3 - paramInt1 - i, paramInt4 - paramInt2 - this.expandedMarginBottom);
        this.collapsingTextHelper.recalculate();
      } 
    } 
    paramInt2 = getChildCount();
    for (paramInt1 = bool; paramInt1 < paramInt2; paramInt1++)
      getViewOffsetHelper(getChildAt(paramInt1)).onViewLayout(); 
    if (this.toolbar != null) {
      if (this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.getText()))
        setTitle(this.toolbar.getTitle()); 
      if (this.toolbarDirectChild == null || this.toolbarDirectChild == this) {
        setMinimumHeight(getHeightWithMargins((View)this.toolbar));
      } else {
        setMinimumHeight(getHeightWithMargins(this.toolbarDirectChild));
      } 
    } 
    updateScrimVisibility();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    ensureToolbar();
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getMode(paramInt2);
    if (this.lastInsets != null) {
      paramInt2 = this.lastInsets.getSystemWindowInsetTop();
    } else {
      paramInt2 = 0;
    } 
    if (i == 0 && paramInt2 > 0)
      super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + paramInt2, 1073741824)); 
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.contentScrim != null)
      this.contentScrim.setBounds(0, 0, paramInt1, paramInt2); 
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
      requestLayout();
    } 
    return paramWindowInsetsCompat.consumeSystemWindowInsets();
  }
  
  public void setCollapsedTitleGravity(int paramInt) {
    this.collapsingTextHelper.setCollapsedTextGravity(paramInt);
  }
  
  public void setCollapsedTitleTextAppearance(@StyleRes int paramInt) {
    this.collapsingTextHelper.setCollapsedTextAppearance(paramInt);
  }
  
  public void setCollapsedTitleTextColor(@ColorInt int paramInt) {
    setCollapsedTitleTextColor(ColorStateList.valueOf(paramInt));
  }
  
  public void setCollapsedTitleTextColor(@NonNull ColorStateList paramColorStateList) {
    this.collapsingTextHelper.setCollapsedTextColor(paramColorStateList);
  }
  
  public void setCollapsedTitleTypeface(@Nullable Typeface paramTypeface) {
    this.collapsingTextHelper.setCollapsedTypeface(paramTypeface);
  }
  
  public void setContentScrim(@Nullable Drawable paramDrawable) {
    if (this.contentScrim != paramDrawable) {
      Drawable drawable1 = this.contentScrim;
      Drawable drawable2 = null;
      if (drawable1 != null)
        this.contentScrim.setCallback(null); 
      if (paramDrawable != null)
        drawable2 = paramDrawable.mutate(); 
      this.contentScrim = drawable2;
      if (this.contentScrim != null) {
        this.contentScrim.setBounds(0, 0, getWidth(), getHeight());
        this.contentScrim.setCallback((Drawable.Callback)this);
        this.contentScrim.setAlpha(this.scrimAlpha);
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
    } 
  }
  
  public void setContentScrimColor(@ColorInt int paramInt) {
    setContentScrim((Drawable)new ColorDrawable(paramInt));
  }
  
  public void setContentScrimResource(@DrawableRes int paramInt) {
    setContentScrim(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setExpandedTitleColor(@ColorInt int paramInt) {
    setExpandedTitleTextColor(ColorStateList.valueOf(paramInt));
  }
  
  public void setExpandedTitleGravity(int paramInt) {
    this.collapsingTextHelper.setExpandedTextGravity(paramInt);
  }
  
  public void setExpandedTitleMargin(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.expandedMarginStart = paramInt1;
    this.expandedMarginTop = paramInt2;
    this.expandedMarginEnd = paramInt3;
    this.expandedMarginBottom = paramInt4;
    requestLayout();
  }
  
  public void setExpandedTitleMarginBottom(int paramInt) {
    this.expandedMarginBottom = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleMarginEnd(int paramInt) {
    this.expandedMarginEnd = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleMarginStart(int paramInt) {
    this.expandedMarginStart = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleMarginTop(int paramInt) {
    this.expandedMarginTop = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleTextAppearance(@StyleRes int paramInt) {
    this.collapsingTextHelper.setExpandedTextAppearance(paramInt);
  }
  
  public void setExpandedTitleTextColor(@NonNull ColorStateList paramColorStateList) {
    this.collapsingTextHelper.setExpandedTextColor(paramColorStateList);
  }
  
  public void setExpandedTitleTypeface(@Nullable Typeface paramTypeface) {
    this.collapsingTextHelper.setExpandedTypeface(paramTypeface);
  }
  
  void setScrimAlpha(int paramInt) {
    if (paramInt != this.scrimAlpha) {
      if (this.contentScrim != null && this.toolbar != null)
        ViewCompat.postInvalidateOnAnimation((View)this.toolbar); 
      this.scrimAlpha = paramInt;
      ViewCompat.postInvalidateOnAnimation((View)this);
    } 
  }
  
  public void setScrimAnimationDuration(@IntRange(from = 0L) long paramLong) {
    this.scrimAnimationDuration = paramLong;
  }
  
  public void setScrimVisibleHeightTrigger(@IntRange(from = 0L) int paramInt) {
    if (this.scrimVisibleHeightTrigger != paramInt) {
      this.scrimVisibleHeightTrigger = paramInt;
      updateScrimVisibility();
    } 
  }
  
  public void setScrimsShown(boolean paramBoolean) {
    boolean bool;
    if (ViewCompat.isLaidOut((View)this) && !isInEditMode()) {
      bool = true;
    } else {
      bool = false;
    } 
    setScrimsShown(paramBoolean, bool);
  }
  
  public void setScrimsShown(boolean paramBoolean1, boolean paramBoolean2) {
    if (this.scrimsAreShown != paramBoolean1) {
      boolean bool = false;
      char c = Character.MIN_VALUE;
      if (paramBoolean2) {
        if (paramBoolean1)
          c = 'ÿ'; 
        animateScrim(c);
      } else {
        c = bool;
        if (paramBoolean1)
          c = 'ÿ'; 
        setScrimAlpha(c);
      } 
      this.scrimsAreShown = paramBoolean1;
    } 
  }
  
  public void setStatusBarScrim(@Nullable Drawable paramDrawable) {
    if (this.statusBarScrim != paramDrawable) {
      Drawable drawable1 = this.statusBarScrim;
      Drawable drawable2 = null;
      if (drawable1 != null)
        this.statusBarScrim.setCallback(null); 
      if (paramDrawable != null)
        drawable2 = paramDrawable.mutate(); 
      this.statusBarScrim = drawable2;
      if (this.statusBarScrim != null) {
        boolean bool;
        if (this.statusBarScrim.isStateful())
          this.statusBarScrim.setState(getDrawableState()); 
        DrawableCompat.setLayoutDirection(this.statusBarScrim, ViewCompat.getLayoutDirection((View)this));
        paramDrawable = this.statusBarScrim;
        if (getVisibility() == 0) {
          bool = true;
        } else {
          bool = false;
        } 
        paramDrawable.setVisible(bool, false);
        this.statusBarScrim.setCallback((Drawable.Callback)this);
        this.statusBarScrim.setAlpha(this.scrimAlpha);
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
    } 
  }
  
  public void setStatusBarScrimColor(@ColorInt int paramInt) {
    setStatusBarScrim((Drawable)new ColorDrawable(paramInt));
  }
  
  public void setStatusBarScrimResource(@DrawableRes int paramInt) {
    setStatusBarScrim(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setTitle(@Nullable CharSequence paramCharSequence) {
    this.collapsingTextHelper.setText(paramCharSequence);
    updateContentDescriptionFromTitle();
  }
  
  public void setTitleEnabled(boolean paramBoolean) {
    if (paramBoolean != this.collapsingTitleEnabled) {
      this.collapsingTitleEnabled = paramBoolean;
      updateContentDescriptionFromTitle();
      updateDummyView();
      requestLayout();
    } 
  }
  
  public void setVisibility(int paramInt) {
    boolean bool;
    super.setVisibility(paramInt);
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (this.statusBarScrim != null && this.statusBarScrim.isVisible() != bool)
      this.statusBarScrim.setVisible(bool, false); 
    if (this.contentScrim != null && this.contentScrim.isVisible() != bool)
      this.contentScrim.setVisible(bool, false); 
  }
  
  final void updateScrimVisibility() {
    if (this.contentScrim != null || this.statusBarScrim != null) {
      boolean bool;
      if (getHeight() + this.currentOffset < getScrimVisibleHeightTrigger()) {
        bool = true;
      } else {
        bool = false;
      } 
      setScrimsShown(bool);
    } 
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable) {
    return (super.verifyDrawable(paramDrawable) || paramDrawable == this.contentScrim || paramDrawable == this.statusBarScrim);
  }
  
  public static class LayoutParams extends FrameLayout.LayoutParams {
    public static final int COLLAPSE_MODE_OFF = 0;
    
    public static final int COLLAPSE_MODE_PARALLAX = 2;
    
    public static final int COLLAPSE_MODE_PIN = 1;
    
    private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5F;
    
    int collapseMode = 0;
    
    float parallaxMult = 0.5F;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(int param1Int1, int param1Int2, int param1Int3) {
      super(param1Int1, param1Int2, param1Int3);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.CollapsingToolbarLayout_Layout);
      this.collapseMode = typedArray.getInt(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
      setParallaxMultiplier(typedArray.getFloat(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5F));
      typedArray.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    @RequiresApi(19)
    public LayoutParams(FrameLayout.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public int getCollapseMode() {
      return this.collapseMode;
    }
    
    public float getParallaxMultiplier() {
      return this.parallaxMult;
    }
    
    public void setCollapseMode(int param1Int) {
      this.collapseMode = param1Int;
    }
    
    public void setParallaxMultiplier(float param1Float) {
      this.parallaxMult = param1Float;
    }
  }
  
  private class OffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
    public void onOffsetChanged(AppBarLayout param1AppBarLayout, int param1Int) {
      byte b;
      CollapsingToolbarLayout.this.currentOffset = param1Int;
      if (CollapsingToolbarLayout.this.lastInsets != null) {
        b = CollapsingToolbarLayout.this.lastInsets.getSystemWindowInsetTop();
      } else {
        b = 0;
      } 
      int i = CollapsingToolbarLayout.this.getChildCount();
      int j;
      for (j = 0; j < i; j++) {
        View view = CollapsingToolbarLayout.this.getChildAt(j);
        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams)view.getLayoutParams();
        ViewOffsetHelper viewOffsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(view);
        switch (layoutParams.collapseMode) {
          case 2:
            viewOffsetHelper.setTopAndBottomOffset(Math.round(-param1Int * layoutParams.parallaxMult));
            break;
          case 1:
            viewOffsetHelper.setTopAndBottomOffset(MathUtils.clamp(-param1Int, 0, CollapsingToolbarLayout.this.getMaxOffsetForPinChild(view)));
            break;
        } 
      } 
      CollapsingToolbarLayout.this.updateScrimVisibility();
      if (CollapsingToolbarLayout.this.statusBarScrim != null && b)
        ViewCompat.postInvalidateOnAnimation((View)CollapsingToolbarLayout.this); 
      j = CollapsingToolbarLayout.this.getHeight();
      i = ViewCompat.getMinimumHeight((View)CollapsingToolbarLayout.this);
      CollapsingToolbarLayout.this.collapsingTextHelper.setExpansionFraction(Math.abs(param1Int) / (j - i - b));
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\CollapsingToolbarLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */