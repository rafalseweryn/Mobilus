package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.design.resources.MaterialResources;
import android.support.design.ripple.RippleUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.Pools;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.DecorView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.TooltipCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@DecorView
public class TabLayout extends HorizontalScrollView {
  private static final int ANIMATION_DURATION = 300;
  
  @Dimension(unit = 0)
  static final int DEFAULT_GAP_TEXT_ICON = 8;
  
  @Dimension(unit = 0)
  private static final int DEFAULT_HEIGHT = 48;
  
  @Dimension(unit = 0)
  private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
  
  @Dimension(unit = 0)
  static final int FIXED_WRAP_GUTTER_MIN = 16;
  
  public static final int GRAVITY_CENTER = 1;
  
  public static final int GRAVITY_FILL = 0;
  
  public static final int INDICATOR_GRAVITY_BOTTOM = 0;
  
  public static final int INDICATOR_GRAVITY_CENTER = 1;
  
  public static final int INDICATOR_GRAVITY_STRETCH = 3;
  
  public static final int INDICATOR_GRAVITY_TOP = 2;
  
  private static final int INVALID_WIDTH = -1;
  
  @Dimension(unit = 0)
  private static final int MIN_INDICATOR_WIDTH = 24;
  
  public static final int MODE_FIXED = 1;
  
  public static final int MODE_SCROLLABLE = 0;
  
  @Dimension(unit = 0)
  private static final int TAB_MIN_WIDTH_MARGIN = 56;
  
  private static final Pools.Pool<Tab> tabPool = (Pools.Pool<Tab>)new Pools.SynchronizedPool(16);
  
  private AdapterChangeListener adapterChangeListener;
  
  private int contentInsetStart;
  
  private BaseOnTabSelectedListener currentVpSelectedListener;
  
  boolean inlineLabel;
  
  int mode;
  
  private TabLayoutOnPageChangeListener pageChangeListener;
  
  private PagerAdapter pagerAdapter;
  
  private DataSetObserver pagerAdapterObserver;
  
  private final int requestedTabMaxWidth;
  
  private final int requestedTabMinWidth;
  
  private ValueAnimator scrollAnimator;
  
  private final int scrollableTabMinWidth;
  
  private BaseOnTabSelectedListener selectedListener;
  
  private final ArrayList<BaseOnTabSelectedListener> selectedListeners = new ArrayList<>();
  
  private Tab selectedTab;
  
  private boolean setupViewPagerImplicitly;
  
  private final SlidingTabIndicator slidingTabIndicator;
  
  final int tabBackgroundResId;
  
  int tabGravity;
  
  ColorStateList tabIconTint;
  
  PorterDuff.Mode tabIconTintMode;
  
  int tabIndicatorAnimationDuration;
  
  boolean tabIndicatorFullWidth;
  
  int tabIndicatorGravity;
  
  int tabMaxWidth = Integer.MAX_VALUE;
  
  int tabPaddingBottom;
  
  int tabPaddingEnd;
  
  int tabPaddingStart;
  
  int tabPaddingTop;
  
  ColorStateList tabRippleColorStateList;
  
  @Nullable
  Drawable tabSelectedIndicator;
  
  int tabTextAppearance;
  
  ColorStateList tabTextColors;
  
  float tabTextMultiLineSize;
  
  float tabTextSize;
  
  private final RectF tabViewContentBounds = new RectF();
  
  private final Pools.Pool<TabView> tabViewPool = (Pools.Pool<TabView>)new Pools.SimplePool(12);
  
  private final ArrayList<Tab> tabs = new ArrayList<>();
  
  boolean unboundedRipple;
  
  ViewPager viewPager;
  
  public TabLayout(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public TabLayout(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.tabStyle);
  }
  
  public TabLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    setHorizontalScrollBarEnabled(false);
    this.slidingTabIndicator = new SlidingTabIndicator(paramContext);
    super.addView((View)this.slidingTabIndicator, 0, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -1));
    TypedArray typedArray2 = ThemeEnforcement.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.TabLayout, paramInt, R.style.Widget_Design_TabLayout, new int[] { R.styleable.TabLayout_tabTextAppearance });
    this.slidingTabIndicator.setSelectedIndicatorHeight(typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, -1));
    this.slidingTabIndicator.setSelectedIndicatorColor(typedArray2.getColor(R.styleable.TabLayout_tabIndicatorColor, 0));
    setSelectedTabIndicator(MaterialResources.getDrawable(paramContext, typedArray2, R.styleable.TabLayout_tabIndicator));
    setSelectedTabIndicatorGravity(typedArray2.getInt(R.styleable.TabLayout_tabIndicatorGravity, 0));
    setTabIndicatorFullWidth(typedArray2.getBoolean(R.styleable.TabLayout_tabIndicatorFullWidth, true));
    paramInt = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
    this.tabPaddingBottom = paramInt;
    this.tabPaddingEnd = paramInt;
    this.tabPaddingTop = paramInt;
    this.tabPaddingStart = paramInt;
    this.tabPaddingStart = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, this.tabPaddingStart);
    this.tabPaddingTop = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, this.tabPaddingTop);
    this.tabPaddingEnd = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
    this.tabPaddingBottom = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
    this.tabTextAppearance = typedArray2.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
    TypedArray typedArray1 = paramContext.obtainStyledAttributes(this.tabTextAppearance, R.styleable.TextAppearance);
    try {
      this.tabTextSize = typedArray1.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, 0);
      this.tabTextColors = MaterialResources.getColorStateList(paramContext, typedArray1, R.styleable.TextAppearance_android_textColor);
      typedArray1.recycle();
      if (typedArray2.hasValue(R.styleable.TabLayout_tabTextColor))
        this.tabTextColors = MaterialResources.getColorStateList(paramContext, typedArray2, R.styleable.TabLayout_tabTextColor); 
      if (typedArray2.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
        paramInt = typedArray2.getColor(R.styleable.TabLayout_tabSelectedTextColor, 0);
        this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), paramInt);
      } 
      this.tabIconTint = MaterialResources.getColorStateList(paramContext, typedArray2, R.styleable.TabLayout_tabIconTint);
      this.tabIconTintMode = ViewUtils.parseTintMode(typedArray2.getInt(R.styleable.TabLayout_tabIconTintMode, -1), null);
      this.tabRippleColorStateList = MaterialResources.getColorStateList(paramContext, typedArray2, R.styleable.TabLayout_tabRippleColor);
      this.tabIndicatorAnimationDuration = typedArray2.getInt(R.styleable.TabLayout_tabIndicatorAnimationDuration, 300);
      this.requestedTabMinWidth = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, -1);
      this.requestedTabMaxWidth = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, -1);
      this.tabBackgroundResId = typedArray2.getResourceId(R.styleable.TabLayout_tabBackground, 0);
      this.contentInsetStart = typedArray2.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
      this.mode = typedArray2.getInt(R.styleable.TabLayout_tabMode, 1);
      this.tabGravity = typedArray2.getInt(R.styleable.TabLayout_tabGravity, 0);
      this.inlineLabel = typedArray2.getBoolean(R.styleable.TabLayout_tabInlineLabel, false);
      this.unboundedRipple = typedArray2.getBoolean(R.styleable.TabLayout_tabUnboundedRipple, false);
      typedArray2.recycle();
      Resources resources = getResources();
      this.tabTextMultiLineSize = resources.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
      this.scrollableTabMinWidth = resources.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
      return;
    } finally {
      typedArray1.recycle();
    } 
  }
  
  private void addTabFromItemView(@NonNull TabItem paramTabItem) {
    Tab tab = newTab();
    if (paramTabItem.text != null)
      tab.setText(paramTabItem.text); 
    if (paramTabItem.icon != null)
      tab.setIcon(paramTabItem.icon); 
    if (paramTabItem.customLayout != 0)
      tab.setCustomView(paramTabItem.customLayout); 
    if (!TextUtils.isEmpty(paramTabItem.getContentDescription()))
      tab.setContentDescription(paramTabItem.getContentDescription()); 
    addTab(tab);
  }
  
  private void addTabView(Tab paramTab) {
    TabView tabView = paramTab.view;
    this.slidingTabIndicator.addView((View)tabView, paramTab.getPosition(), (ViewGroup.LayoutParams)createLayoutParamsForTabs());
  }
  
  private void addViewInternal(View paramView) {
    if (paramView instanceof TabItem) {
      addTabFromItemView((TabItem)paramView);
      return;
    } 
    throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
  }
  
  private void animateToTab(int paramInt) {
    if (paramInt == -1)
      return; 
    if (getWindowToken() == null || !ViewCompat.isLaidOut((View)this) || this.slidingTabIndicator.childrenNeedLayout()) {
      setScrollPosition(paramInt, 0.0F, true);
      return;
    } 
    int i = getScrollX();
    int j = calculateScrollXForTab(paramInt, 0.0F);
    if (i != j) {
      ensureScrollAnimator();
      this.scrollAnimator.setIntValues(new int[] { i, j });
      this.scrollAnimator.start();
    } 
    this.slidingTabIndicator.animateIndicatorToPosition(paramInt, this.tabIndicatorAnimationDuration);
  }
  
  private void applyModeAndGravity() {
    boolean bool;
    if (this.mode == 0) {
      bool = Math.max(0, this.contentInsetStart - this.tabPaddingStart);
    } else {
      bool = false;
    } 
    ViewCompat.setPaddingRelative((View)this.slidingTabIndicator, bool, 0, 0, 0);
    switch (this.mode) {
      case 1:
        this.slidingTabIndicator.setGravity(1);
        break;
      case 0:
        this.slidingTabIndicator.setGravity(8388611);
        break;
    } 
    updateTabViews(true);
  }
  
  private int calculateScrollXForTab(int paramInt, float paramFloat) {
    int i = this.mode;
    int j = 0;
    if (i == 0) {
      View view2;
      View view1 = this.slidingTabIndicator.getChildAt(paramInt);
      if (++paramInt < this.slidingTabIndicator.getChildCount()) {
        view2 = this.slidingTabIndicator.getChildAt(paramInt);
      } else {
        view2 = null;
      } 
      if (view1 != null) {
        paramInt = view1.getWidth();
      } else {
        paramInt = 0;
      } 
      if (view2 != null)
        j = view2.getWidth(); 
      i = view1.getLeft() + paramInt / 2 - getWidth() / 2;
      paramInt = (int)((paramInt + j) * 0.5F * paramFloat);
      if (ViewCompat.getLayoutDirection((View)this) == 0) {
        paramInt = i + paramInt;
      } else {
        paramInt = i - paramInt;
      } 
      return paramInt;
    } 
    return 0;
  }
  
  private void configureTab(Tab paramTab, int paramInt) {
    paramTab.setPosition(paramInt);
    this.tabs.add(paramInt, paramTab);
    int i = this.tabs.size();
    while (++paramInt < i)
      ((Tab)this.tabs.get(paramInt)).setPosition(paramInt); 
  }
  
  private static ColorStateList createColorStateList(int paramInt1, int paramInt2) {
    return new ColorStateList(new int[][] { SELECTED_STATE_SET, EMPTY_STATE_SET }, new int[] { paramInt2, paramInt1 });
  }
  
  private LinearLayout.LayoutParams createLayoutParamsForTabs() {
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
    updateTabViewLayoutParams(layoutParams);
    return layoutParams;
  }
  
  private TabView createTabView(@NonNull Tab paramTab) {
    TabView tabView1;
    if (this.tabViewPool != null) {
      tabView1 = (TabView)this.tabViewPool.acquire();
    } else {
      tabView1 = null;
    } 
    TabView tabView2 = tabView1;
    if (tabView1 == null)
      tabView2 = new TabView(getContext()); 
    tabView2.setTab(paramTab);
    tabView2.setFocusable(true);
    tabView2.setMinimumWidth(getTabMinWidth());
    if (TextUtils.isEmpty(paramTab.contentDesc)) {
      tabView2.setContentDescription(paramTab.text);
    } else {
      tabView2.setContentDescription(paramTab.contentDesc);
    } 
    return tabView2;
  }
  
  private void dispatchTabReselected(@NonNull Tab paramTab) {
    for (int i = this.selectedListeners.size() - 1; i >= 0; i--)
      ((BaseOnTabSelectedListener<Tab>)this.selectedListeners.get(i)).onTabReselected(paramTab); 
  }
  
  private void dispatchTabSelected(@NonNull Tab paramTab) {
    for (int i = this.selectedListeners.size() - 1; i >= 0; i--)
      ((BaseOnTabSelectedListener<Tab>)this.selectedListeners.get(i)).onTabSelected(paramTab); 
  }
  
  private void dispatchTabUnselected(@NonNull Tab paramTab) {
    for (int i = this.selectedListeners.size() - 1; i >= 0; i--)
      ((BaseOnTabSelectedListener<Tab>)this.selectedListeners.get(i)).onTabUnselected(paramTab); 
  }
  
  private void ensureScrollAnimator() {
    if (this.scrollAnimator == null) {
      this.scrollAnimator = new ValueAnimator();
      this.scrollAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
      this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
      this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
              TabLayout.this.scrollTo(((Integer)param1ValueAnimator.getAnimatedValue()).intValue(), 0);
            }
          });
    } 
  }
  
  @Dimension(unit = 0)
  private int getDefaultHeight() {
    boolean bool2;
    int i = this.tabs.size();
    boolean bool1 = false;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < i) {
        Tab tab = this.tabs.get(b);
        if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
          bool2 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    if (bool2 && !this.inlineLabel) {
      b = 72;
    } else {
      b = 48;
    } 
    return b;
  }
  
  private int getTabMinWidth() {
    boolean bool;
    if (this.requestedTabMinWidth != -1)
      return this.requestedTabMinWidth; 
    if (this.mode == 0) {
      bool = this.scrollableTabMinWidth;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private int getTabScrollRange() {
    return Math.max(0, this.slidingTabIndicator.getWidth() - getWidth() - getPaddingLeft() - getPaddingRight());
  }
  
  private void removeTabViewAt(int paramInt) {
    TabView tabView = (TabView)this.slidingTabIndicator.getChildAt(paramInt);
    this.slidingTabIndicator.removeViewAt(paramInt);
    if (tabView != null) {
      tabView.reset();
      this.tabViewPool.release(tabView);
    } 
    requestLayout();
  }
  
  private void setSelectedTabView(int paramInt) {
    int i = this.slidingTabIndicator.getChildCount();
    if (paramInt < i)
      for (byte b = 0; b < i; b++) {
        boolean bool2;
        View view = this.slidingTabIndicator.getChildAt(b);
        boolean bool1 = true;
        if (b == paramInt) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        view.setSelected(bool2);
        if (b == paramInt) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        view.setActivated(bool2);
      }  
  }
  
  private void setupWithViewPager(@Nullable ViewPager paramViewPager, boolean paramBoolean1, boolean paramBoolean2) {
    if (this.viewPager != null) {
      if (this.pageChangeListener != null)
        this.viewPager.removeOnPageChangeListener(this.pageChangeListener); 
      if (this.adapterChangeListener != null)
        this.viewPager.removeOnAdapterChangeListener(this.adapterChangeListener); 
    } 
    if (this.currentVpSelectedListener != null) {
      removeOnTabSelectedListener(this.currentVpSelectedListener);
      this.currentVpSelectedListener = null;
    } 
    if (paramViewPager != null) {
      this.viewPager = paramViewPager;
      if (this.pageChangeListener == null)
        this.pageChangeListener = new TabLayoutOnPageChangeListener(this); 
      this.pageChangeListener.reset();
      paramViewPager.addOnPageChangeListener(this.pageChangeListener);
      this.currentVpSelectedListener = new ViewPagerOnTabSelectedListener(paramViewPager);
      addOnTabSelectedListener(this.currentVpSelectedListener);
      PagerAdapter pagerAdapter = paramViewPager.getAdapter();
      if (pagerAdapter != null)
        setPagerAdapter(pagerAdapter, paramBoolean1); 
      if (this.adapterChangeListener == null)
        this.adapterChangeListener = new AdapterChangeListener(); 
      this.adapterChangeListener.setAutoRefresh(paramBoolean1);
      paramViewPager.addOnAdapterChangeListener(this.adapterChangeListener);
      setScrollPosition(paramViewPager.getCurrentItem(), 0.0F, true);
    } else {
      this.viewPager = null;
      setPagerAdapter((PagerAdapter)null, false);
    } 
    this.setupViewPagerImplicitly = paramBoolean2;
  }
  
  private void updateAllTabs() {
    int i = this.tabs.size();
    for (byte b = 0; b < i; b++)
      ((Tab)this.tabs.get(b)).updateView(); 
  }
  
  private void updateTabViewLayoutParams(LinearLayout.LayoutParams paramLayoutParams) {
    if (this.mode == 1 && this.tabGravity == 0) {
      paramLayoutParams.width = 0;
      paramLayoutParams.weight = 1.0F;
    } else {
      paramLayoutParams.width = -2;
      paramLayoutParams.weight = 0.0F;
    } 
  }
  
  public void addOnTabSelectedListener(@NonNull BaseOnTabSelectedListener paramBaseOnTabSelectedListener) {
    if (!this.selectedListeners.contains(paramBaseOnTabSelectedListener))
      this.selectedListeners.add(paramBaseOnTabSelectedListener); 
  }
  
  public void addTab(@NonNull Tab paramTab) {
    addTab(paramTab, this.tabs.isEmpty());
  }
  
  public void addTab(@NonNull Tab paramTab, int paramInt) {
    addTab(paramTab, paramInt, this.tabs.isEmpty());
  }
  
  public void addTab(@NonNull Tab paramTab, int paramInt, boolean paramBoolean) {
    if (paramTab.parent != this)
      throw new IllegalArgumentException("Tab belongs to a different TabLayout."); 
    configureTab(paramTab, paramInt);
    addTabView(paramTab);
    if (paramBoolean)
      paramTab.select(); 
  }
  
  public void addTab(@NonNull Tab paramTab, boolean paramBoolean) {
    addTab(paramTab, this.tabs.size(), paramBoolean);
  }
  
  public void addView(View paramView) {
    addViewInternal(paramView);
  }
  
  public void addView(View paramView, int paramInt) {
    addViewInternal(paramView);
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    addViewInternal(paramView);
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    addViewInternal(paramView);
  }
  
  public void clearOnTabSelectedListeners() {
    this.selectedListeners.clear();
  }
  
  protected Tab createTabFromPool() {
    Tab tab1 = (Tab)tabPool.acquire();
    Tab tab2 = tab1;
    if (tab1 == null)
      tab2 = new Tab(); 
    return tab2;
  }
  
  @Dimension(unit = 1)
  int dpToPx(@Dimension(unit = 0) int paramInt) {
    return Math.round((getResources().getDisplayMetrics()).density * paramInt);
  }
  
  public FrameLayout.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return generateDefaultLayoutParams();
  }
  
  public int getSelectedTabPosition() {
    byte b;
    if (this.selectedTab != null) {
      b = this.selectedTab.getPosition();
    } else {
      b = -1;
    } 
    return b;
  }
  
  @Nullable
  public Tab getTabAt(int paramInt) {
    return (paramInt < 0 || paramInt >= getTabCount()) ? null : this.tabs.get(paramInt);
  }
  
  public int getTabCount() {
    return this.tabs.size();
  }
  
  public int getTabGravity() {
    return this.tabGravity;
  }
  
  @Nullable
  public ColorStateList getTabIconTint() {
    return this.tabIconTint;
  }
  
  public int getTabIndicatorGravity() {
    return this.tabIndicatorGravity;
  }
  
  int getTabMaxWidth() {
    return this.tabMaxWidth;
  }
  
  public int getTabMode() {
    return this.mode;
  }
  
  @Nullable
  public ColorStateList getTabRippleColor() {
    return this.tabRippleColorStateList;
  }
  
  @Nullable
  public Drawable getTabSelectedIndicator() {
    return this.tabSelectedIndicator;
  }
  
  @Nullable
  public ColorStateList getTabTextColors() {
    return this.tabTextColors;
  }
  
  public boolean hasUnboundedRipple() {
    return this.unboundedRipple;
  }
  
  public boolean isInlineLabel() {
    return this.inlineLabel;
  }
  
  public boolean isTabIndicatorFullWidth() {
    return this.tabIndicatorFullWidth;
  }
  
  @NonNull
  public Tab newTab() {
    Tab tab = createTabFromPool();
    tab.parent = this;
    tab.view = createTabView(tab);
    return tab;
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (this.viewPager == null) {
      ViewParent viewParent = getParent();
      if (viewParent instanceof ViewPager)
        setupWithViewPager((ViewPager)viewParent, true, true); 
    } 
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if (this.setupViewPagerImplicitly) {
      setupWithViewPager((ViewPager)null);
      this.setupViewPagerImplicitly = false;
    } 
  }
  
  protected void onDraw(Canvas paramCanvas) {
    for (byte b = 0; b < this.slidingTabIndicator.getChildCount(); b++) {
      View view = this.slidingTabIndicator.getChildAt(b);
      if (view instanceof TabView)
        ((TabView)view).drawBackground(paramCanvas); 
    } 
    super.onDraw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokespecial getDefaultHeight : ()I
    //   5: invokevirtual dpToPx : (I)I
    //   8: aload_0
    //   9: invokevirtual getPaddingTop : ()I
    //   12: iadd
    //   13: aload_0
    //   14: invokevirtual getPaddingBottom : ()I
    //   17: iadd
    //   18: istore_3
    //   19: iload_2
    //   20: invokestatic getMode : (I)I
    //   23: istore #4
    //   25: iload #4
    //   27: ldc_w -2147483648
    //   30: if_icmpeq -> 52
    //   33: iload #4
    //   35: ifeq -> 41
    //   38: goto -> 67
    //   41: iload_3
    //   42: ldc_w 1073741824
    //   45: invokestatic makeMeasureSpec : (II)I
    //   48: istore_2
    //   49: goto -> 67
    //   52: iload_3
    //   53: iload_2
    //   54: invokestatic getSize : (I)I
    //   57: invokestatic min : (II)I
    //   60: ldc_w 1073741824
    //   63: invokestatic makeMeasureSpec : (II)I
    //   66: istore_2
    //   67: iload_1
    //   68: invokestatic getSize : (I)I
    //   71: istore_3
    //   72: iload_1
    //   73: invokestatic getMode : (I)I
    //   76: ifeq -> 108
    //   79: aload_0
    //   80: getfield requestedTabMaxWidth : I
    //   83: ifle -> 94
    //   86: aload_0
    //   87: getfield requestedTabMaxWidth : I
    //   90: istore_3
    //   91: goto -> 103
    //   94: iload_3
    //   95: aload_0
    //   96: bipush #56
    //   98: invokevirtual dpToPx : (I)I
    //   101: isub
    //   102: istore_3
    //   103: aload_0
    //   104: iload_3
    //   105: putfield tabMaxWidth : I
    //   108: aload_0
    //   109: iload_1
    //   110: iload_2
    //   111: invokespecial onMeasure : (II)V
    //   114: aload_0
    //   115: invokevirtual getChildCount : ()I
    //   118: iconst_1
    //   119: if_icmpne -> 233
    //   122: iconst_0
    //   123: istore_1
    //   124: aload_0
    //   125: iconst_0
    //   126: invokevirtual getChildAt : (I)Landroid/view/View;
    //   129: astore #5
    //   131: aload_0
    //   132: getfield mode : I
    //   135: tableswitch default -> 156, 0 -> 176, 1 -> 159
    //   156: goto -> 191
    //   159: aload #5
    //   161: invokevirtual getMeasuredWidth : ()I
    //   164: aload_0
    //   165: invokevirtual getMeasuredWidth : ()I
    //   168: if_icmpeq -> 191
    //   171: iconst_1
    //   172: istore_1
    //   173: goto -> 191
    //   176: aload #5
    //   178: invokevirtual getMeasuredWidth : ()I
    //   181: aload_0
    //   182: invokevirtual getMeasuredWidth : ()I
    //   185: if_icmpge -> 191
    //   188: goto -> 171
    //   191: iload_1
    //   192: ifeq -> 233
    //   195: iload_2
    //   196: aload_0
    //   197: invokevirtual getPaddingTop : ()I
    //   200: aload_0
    //   201: invokevirtual getPaddingBottom : ()I
    //   204: iadd
    //   205: aload #5
    //   207: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   210: getfield height : I
    //   213: invokestatic getChildMeasureSpec : (III)I
    //   216: istore_1
    //   217: aload #5
    //   219: aload_0
    //   220: invokevirtual getMeasuredWidth : ()I
    //   223: ldc_w 1073741824
    //   226: invokestatic makeMeasureSpec : (II)I
    //   229: iload_1
    //   230: invokevirtual measure : (II)V
    //   233: return
  }
  
  void populateFromPagerAdapter() {
    removeAllTabs();
    if (this.pagerAdapter != null) {
      int i = this.pagerAdapter.getCount();
      int j;
      for (j = 0; j < i; j++)
        addTab(newTab().setText(this.pagerAdapter.getPageTitle(j)), false); 
      if (this.viewPager != null && i > 0) {
        j = this.viewPager.getCurrentItem();
        if (j != getSelectedTabPosition() && j < getTabCount())
          selectTab(getTabAt(j)); 
      } 
    } 
  }
  
  protected boolean releaseFromTabPool(Tab paramTab) {
    return tabPool.release(paramTab);
  }
  
  public void removeAllTabs() {
    for (int i = this.slidingTabIndicator.getChildCount() - 1; i >= 0; i--)
      removeTabViewAt(i); 
    Iterator<Tab> iterator = this.tabs.iterator();
    while (iterator.hasNext()) {
      Tab tab = iterator.next();
      iterator.remove();
      tab.reset();
      releaseFromTabPool(tab);
    } 
    this.selectedTab = null;
  }
  
  public void removeOnTabSelectedListener(@NonNull BaseOnTabSelectedListener paramBaseOnTabSelectedListener) {
    this.selectedListeners.remove(paramBaseOnTabSelectedListener);
  }
  
  public void removeTab(Tab paramTab) {
    if (paramTab.parent != this)
      throw new IllegalArgumentException("Tab does not belong to this TabLayout."); 
    removeTabAt(paramTab.getPosition());
  }
  
  public void removeTabAt(int paramInt) {
    int i;
    if (this.selectedTab != null) {
      i = this.selectedTab.getPosition();
    } else {
      i = 0;
    } 
    removeTabViewAt(paramInt);
    Tab tab = this.tabs.remove(paramInt);
    if (tab != null) {
      tab.reset();
      releaseFromTabPool(tab);
    } 
    int j = this.tabs.size();
    for (int k = paramInt; k < j; k++)
      ((Tab)this.tabs.get(k)).setPosition(k); 
    if (i == paramInt) {
      if (this.tabs.isEmpty()) {
        tab = null;
      } else {
        tab = this.tabs.get(Math.max(0, paramInt - 1));
      } 
      selectTab(tab);
    } 
  }
  
  void selectTab(Tab paramTab) {
    selectTab(paramTab, true);
  }
  
  void selectTab(Tab paramTab, boolean paramBoolean) {
    Tab tab = this.selectedTab;
    if (tab == paramTab) {
      if (tab != null) {
        dispatchTabReselected(paramTab);
        animateToTab(paramTab.getPosition());
      } 
    } else {
      byte b;
      if (paramTab != null) {
        b = paramTab.getPosition();
      } else {
        b = -1;
      } 
      if (paramBoolean) {
        if ((tab == null || tab.getPosition() == -1) && b != -1) {
          setScrollPosition(b, 0.0F, true);
        } else {
          animateToTab(b);
        } 
        if (b != -1)
          setSelectedTabView(b); 
      } 
      this.selectedTab = paramTab;
      if (tab != null)
        dispatchTabUnselected(tab); 
      if (paramTab != null)
        dispatchTabSelected(paramTab); 
    } 
  }
  
  public void setInlineLabel(boolean paramBoolean) {
    if (this.inlineLabel != paramBoolean) {
      this.inlineLabel = paramBoolean;
      for (byte b = 0; b < this.slidingTabIndicator.getChildCount(); b++) {
        View view = this.slidingTabIndicator.getChildAt(b);
        if (view instanceof TabView)
          ((TabView)view).updateOrientation(); 
      } 
      applyModeAndGravity();
    } 
  }
  
  public void setInlineLabelResource(@BoolRes int paramInt) {
    setInlineLabel(getResources().getBoolean(paramInt));
  }
  
  @Deprecated
  public void setOnTabSelectedListener(@Nullable BaseOnTabSelectedListener paramBaseOnTabSelectedListener) {
    if (this.selectedListener != null)
      removeOnTabSelectedListener(this.selectedListener); 
    this.selectedListener = paramBaseOnTabSelectedListener;
    if (paramBaseOnTabSelectedListener != null)
      addOnTabSelectedListener(paramBaseOnTabSelectedListener); 
  }
  
  void setPagerAdapter(@Nullable PagerAdapter paramPagerAdapter, boolean paramBoolean) {
    if (this.pagerAdapter != null && this.pagerAdapterObserver != null)
      this.pagerAdapter.unregisterDataSetObserver(this.pagerAdapterObserver); 
    this.pagerAdapter = paramPagerAdapter;
    if (paramBoolean && paramPagerAdapter != null) {
      if (this.pagerAdapterObserver == null)
        this.pagerAdapterObserver = new PagerAdapterObserver(); 
      paramPagerAdapter.registerDataSetObserver(this.pagerAdapterObserver);
    } 
    populateFromPagerAdapter();
  }
  
  void setScrollAnimatorListener(Animator.AnimatorListener paramAnimatorListener) {
    ensureScrollAnimator();
    this.scrollAnimator.addListener(paramAnimatorListener);
  }
  
  public void setScrollPosition(int paramInt, float paramFloat, boolean paramBoolean) {
    setScrollPosition(paramInt, paramFloat, paramBoolean, true);
  }
  
  void setScrollPosition(int paramInt, float paramFloat, boolean paramBoolean1, boolean paramBoolean2) {
    int i = Math.round(paramInt + paramFloat);
    if (i < 0 || i >= this.slidingTabIndicator.getChildCount())
      return; 
    if (paramBoolean2)
      this.slidingTabIndicator.setIndicatorPositionFromTabPosition(paramInt, paramFloat); 
    if (this.scrollAnimator != null && this.scrollAnimator.isRunning())
      this.scrollAnimator.cancel(); 
    scrollTo(calculateScrollXForTab(paramInt, paramFloat), 0);
    if (paramBoolean1)
      setSelectedTabView(i); 
  }
  
  public void setSelectedTabIndicator(@DrawableRes int paramInt) {
    if (paramInt != 0) {
      setSelectedTabIndicator(AppCompatResources.getDrawable(getContext(), paramInt));
    } else {
      setSelectedTabIndicator((Drawable)null);
    } 
  }
  
  public void setSelectedTabIndicator(@Nullable Drawable paramDrawable) {
    if (this.tabSelectedIndicator != paramDrawable) {
      this.tabSelectedIndicator = paramDrawable;
      ViewCompat.postInvalidateOnAnimation((View)this.slidingTabIndicator);
    } 
  }
  
  public void setSelectedTabIndicatorColor(@ColorInt int paramInt) {
    this.slidingTabIndicator.setSelectedIndicatorColor(paramInt);
  }
  
  public void setSelectedTabIndicatorGravity(int paramInt) {
    if (this.tabIndicatorGravity != paramInt) {
      this.tabIndicatorGravity = paramInt;
      ViewCompat.postInvalidateOnAnimation((View)this.slidingTabIndicator);
    } 
  }
  
  @Deprecated
  public void setSelectedTabIndicatorHeight(int paramInt) {
    this.slidingTabIndicator.setSelectedIndicatorHeight(paramInt);
  }
  
  public void setTabGravity(int paramInt) {
    if (this.tabGravity != paramInt) {
      this.tabGravity = paramInt;
      applyModeAndGravity();
    } 
  }
  
  public void setTabIconTint(@Nullable ColorStateList paramColorStateList) {
    if (this.tabIconTint != paramColorStateList) {
      this.tabIconTint = paramColorStateList;
      updateAllTabs();
    } 
  }
  
  public void setTabIconTintResource(@ColorRes int paramInt) {
    setTabIconTint(AppCompatResources.getColorStateList(getContext(), paramInt));
  }
  
  public void setTabIndicatorFullWidth(boolean paramBoolean) {
    this.tabIndicatorFullWidth = paramBoolean;
    ViewCompat.postInvalidateOnAnimation((View)this.slidingTabIndicator);
  }
  
  public void setTabMode(int paramInt) {
    if (paramInt != this.mode) {
      this.mode = paramInt;
      applyModeAndGravity();
    } 
  }
  
  public void setTabRippleColor(@Nullable ColorStateList paramColorStateList) {
    if (this.tabRippleColorStateList != paramColorStateList) {
      this.tabRippleColorStateList = paramColorStateList;
      for (byte b = 0; b < this.slidingTabIndicator.getChildCount(); b++) {
        View view = this.slidingTabIndicator.getChildAt(b);
        if (view instanceof TabView)
          ((TabView)view).updateBackgroundDrawable(getContext()); 
      } 
    } 
  }
  
  public void setTabRippleColorResource(@ColorRes int paramInt) {
    setTabRippleColor(AppCompatResources.getColorStateList(getContext(), paramInt));
  }
  
  public void setTabTextColors(int paramInt1, int paramInt2) {
    setTabTextColors(createColorStateList(paramInt1, paramInt2));
  }
  
  public void setTabTextColors(@Nullable ColorStateList paramColorStateList) {
    if (this.tabTextColors != paramColorStateList) {
      this.tabTextColors = paramColorStateList;
      updateAllTabs();
    } 
  }
  
  @Deprecated
  public void setTabsFromPagerAdapter(@Nullable PagerAdapter paramPagerAdapter) {
    setPagerAdapter(paramPagerAdapter, false);
  }
  
  public void setUnboundedRipple(boolean paramBoolean) {
    if (this.unboundedRipple != paramBoolean) {
      this.unboundedRipple = paramBoolean;
      for (byte b = 0; b < this.slidingTabIndicator.getChildCount(); b++) {
        View view = this.slidingTabIndicator.getChildAt(b);
        if (view instanceof TabView)
          ((TabView)view).updateBackgroundDrawable(getContext()); 
      } 
    } 
  }
  
  public void setUnboundedRippleResource(@BoolRes int paramInt) {
    setUnboundedRipple(getResources().getBoolean(paramInt));
  }
  
  public void setupWithViewPager(@Nullable ViewPager paramViewPager) {
    setupWithViewPager(paramViewPager, true);
  }
  
  public void setupWithViewPager(@Nullable ViewPager paramViewPager, boolean paramBoolean) {
    setupWithViewPager(paramViewPager, paramBoolean, false);
  }
  
  public boolean shouldDelayChildPressedState() {
    boolean bool;
    if (getTabScrollRange() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void updateTabViews(boolean paramBoolean) {
    for (byte b = 0; b < this.slidingTabIndicator.getChildCount(); b++) {
      View view = this.slidingTabIndicator.getChildAt(b);
      view.setMinimumWidth(getTabMinWidth());
      updateTabViewLayoutParams((LinearLayout.LayoutParams)view.getLayoutParams());
      if (paramBoolean)
        view.requestLayout(); 
    } 
  }
  
  private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
    private boolean autoRefresh;
    
    public void onAdapterChanged(@NonNull ViewPager param1ViewPager, @Nullable PagerAdapter param1PagerAdapter1, @Nullable PagerAdapter param1PagerAdapter2) {
      if (TabLayout.this.viewPager == param1ViewPager)
        TabLayout.this.setPagerAdapter(param1PagerAdapter2, this.autoRefresh); 
    }
    
    void setAutoRefresh(boolean param1Boolean) {
      this.autoRefresh = param1Boolean;
    }
  }
  
  public static interface BaseOnTabSelectedListener<T extends Tab> {
    void onTabReselected(T param1T);
    
    void onTabSelected(T param1T);
    
    void onTabUnselected(T param1T);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Mode {}
  
  public static interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {}
  
  private class PagerAdapterObserver extends DataSetObserver {
    public void onChanged() {
      TabLayout.this.populateFromPagerAdapter();
    }
    
    public void onInvalidated() {
      TabLayout.this.populateFromPagerAdapter();
    }
  }
  
  private class SlidingTabIndicator extends LinearLayout {
    private final GradientDrawable defaultSelectionIndicator;
    
    private ValueAnimator indicatorAnimator;
    
    private int indicatorLeft = -1;
    
    private int indicatorRight = -1;
    
    private int layoutDirection = -1;
    
    private int selectedIndicatorHeight;
    
    private final Paint selectedIndicatorPaint;
    
    int selectedPosition = -1;
    
    float selectionOffset;
    
    SlidingTabIndicator(Context param1Context) {
      super(param1Context);
      setWillNotDraw(false);
      this.selectedIndicatorPaint = new Paint();
      this.defaultSelectionIndicator = new GradientDrawable();
    }
    
    private void calculateTabViewContentBounds(TabLayout.TabView param1TabView, RectF param1RectF) {
      int i = param1TabView.getContentWidth();
      int j = i;
      if (i < TabLayout.this.dpToPx(24))
        j = TabLayout.this.dpToPx(24); 
      i = (param1TabView.getLeft() + param1TabView.getRight()) / 2;
      j /= 2;
      param1RectF.set((i - j), 0.0F, (i + j), 0.0F);
    }
    
    private void updateIndicatorPosition() {
      byte b1;
      byte b2;
      View view = getChildAt(this.selectedPosition);
      if (view != null && view.getWidth() > 0) {
        b1 = view.getLeft();
        b2 = view.getRight();
        int i = b1;
        int j = b2;
        if (!TabLayout.this.tabIndicatorFullWidth) {
          i = b1;
          j = b2;
          if (view instanceof TabLayout.TabView) {
            calculateTabViewContentBounds((TabLayout.TabView)view, TabLayout.this.tabViewContentBounds);
            i = (int)TabLayout.this.tabViewContentBounds.left;
            j = (int)TabLayout.this.tabViewContentBounds.right;
          } 
        } 
        b2 = i;
        b1 = j;
        if (this.selectionOffset > 0.0F) {
          b2 = i;
          b1 = j;
          if (this.selectedPosition < getChildCount() - 1) {
            view = getChildAt(this.selectedPosition + 1);
            int k = view.getLeft();
            int m = view.getRight();
            b2 = k;
            b1 = m;
            if (!TabLayout.this.tabIndicatorFullWidth) {
              b2 = k;
              b1 = m;
              if (view instanceof TabLayout.TabView) {
                calculateTabViewContentBounds((TabLayout.TabView)view, TabLayout.this.tabViewContentBounds);
                b2 = (int)TabLayout.this.tabViewContentBounds.left;
                b1 = (int)TabLayout.this.tabViewContentBounds.right;
              } 
            } 
            b2 = (int)(this.selectionOffset * b2 + (1.0F - this.selectionOffset) * i);
            b1 = (int)(this.selectionOffset * b1 + (1.0F - this.selectionOffset) * j);
          } 
        } 
      } else {
        b2 = -1;
        b1 = -1;
      } 
      setIndicatorPosition(b2, b1);
    }
    
    void animateIndicatorToPosition(final int position, int param1Int2) {
      if (this.indicatorAnimator != null && this.indicatorAnimator.isRunning())
        this.indicatorAnimator.cancel(); 
      View view = getChildAt(position);
      if (view == null) {
        updateIndicatorPosition();
        return;
      } 
      final int startRight = view.getLeft();
      final int startLeft = view.getRight();
      final int finalTargetLeft = i;
      final int finalTargetRight = j;
      if (!TabLayout.this.tabIndicatorFullWidth) {
        k = i;
        m = j;
        if (view instanceof TabLayout.TabView) {
          calculateTabViewContentBounds((TabLayout.TabView)view, TabLayout.this.tabViewContentBounds);
          k = (int)TabLayout.this.tabViewContentBounds.left;
          m = (int)TabLayout.this.tabViewContentBounds.right;
        } 
      } 
      j = this.indicatorLeft;
      i = this.indicatorRight;
      if (j != k || i != m) {
        ValueAnimator valueAnimator = new ValueAnimator();
        this.indicatorAnimator = valueAnimator;
        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueAnimator.setDuration(param1Int2);
        valueAnimator.setFloatValues(new float[] { 0.0F, 1.0F });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
              public void onAnimationUpdate(ValueAnimator param2ValueAnimator) {
                float f = param2ValueAnimator.getAnimatedFraction();
                TabLayout.SlidingTabIndicator.this.setIndicatorPosition(AnimationUtils.lerp(startLeft, finalTargetLeft, f), AnimationUtils.lerp(startRight, finalTargetRight, f));
              }
            });
        valueAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
              public void onAnimationEnd(Animator param2Animator) {
                TabLayout.SlidingTabIndicator.this.selectedPosition = position;
                TabLayout.SlidingTabIndicator.this.selectionOffset = 0.0F;
              }
            });
        valueAnimator.start();
      } 
    }
    
    boolean childrenNeedLayout() {
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        if (getChildAt(b).getWidth() <= 0)
          return true; 
      } 
      return false;
    }
    
    public void draw(Canvas param1Canvas) {
      int i;
      Drawable drawable = TabLayout.this.tabSelectedIndicator;
      byte b = 0;
      if (drawable != null) {
        i = TabLayout.this.tabSelectedIndicator.getIntrinsicHeight();
      } else {
        i = 0;
      } 
      if (this.selectedIndicatorHeight >= 0)
        i = this.selectedIndicatorHeight; 
      int j = i;
      int k = b;
      switch (TabLayout.this.tabIndicatorGravity) {
        default:
          j = 0;
          k = b;
          break;
        case 3:
          j = getHeight();
          k = b;
          break;
        case 1:
          k = (getHeight() - i) / 2;
          j = (getHeight() + i) / 2;
          break;
        case 0:
          k = getHeight() - i;
          j = getHeight();
          break;
        case 2:
          break;
      } 
      if (this.indicatorLeft >= 0 && this.indicatorRight > this.indicatorLeft) {
        GradientDrawable gradientDrawable;
        if (TabLayout.this.tabSelectedIndicator != null) {
          drawable = TabLayout.this.tabSelectedIndicator;
        } else {
          gradientDrawable = this.defaultSelectionIndicator;
        } 
        Drawable drawable1 = DrawableCompat.wrap((Drawable)gradientDrawable);
        drawable1.setBounds(this.indicatorLeft, k, this.indicatorRight, j);
        if (this.selectedIndicatorPaint != null)
          if (Build.VERSION.SDK_INT == 21) {
            drawable1.setColorFilter(this.selectedIndicatorPaint.getColor(), PorterDuff.Mode.SRC_IN);
          } else {
            DrawableCompat.setTint(drawable1, this.selectedIndicatorPaint.getColor());
          }  
        drawable1.draw(param1Canvas);
      } 
      super.draw(param1Canvas);
    }
    
    float getIndicatorPosition() {
      return this.selectedPosition + this.selectionOffset;
    }
    
    protected void onLayout(boolean param1Boolean, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      super.onLayout(param1Boolean, param1Int1, param1Int2, param1Int3, param1Int4);
      if (this.indicatorAnimator != null && this.indicatorAnimator.isRunning()) {
        this.indicatorAnimator.cancel();
        long l = this.indicatorAnimator.getDuration();
        animateIndicatorToPosition(this.selectedPosition, Math.round((1.0F - this.indicatorAnimator.getAnimatedFraction()) * (float)l));
      } else {
        updateIndicatorPosition();
      } 
    }
    
    protected void onMeasure(int param1Int1, int param1Int2) {
      super.onMeasure(param1Int1, param1Int2);
      if (View.MeasureSpec.getMode(param1Int1) != 1073741824)
        return; 
      int i = TabLayout.this.mode;
      boolean bool = true;
      if (i == 1 && TabLayout.this.tabGravity == 1) {
        int j = getChildCount();
        byte b = 0;
        i = 0;
        int k;
        for (k = i; i < j; k = m) {
          View view = getChildAt(i);
          int m = k;
          if (view.getVisibility() == 0)
            m = Math.max(k, view.getMeasuredWidth()); 
          i++;
        } 
        if (k <= 0)
          return; 
        i = TabLayout.this.dpToPx(16);
        if (k * j <= getMeasuredWidth() - i * 2) {
          i = 0;
          for (byte b1 = b; b1 < j; b1++) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)getChildAt(b1).getLayoutParams();
            if (layoutParams.width != k || layoutParams.weight != 0.0F) {
              layoutParams.width = k;
              layoutParams.weight = 0.0F;
              i = 1;
            } 
          } 
        } else {
          TabLayout.this.tabGravity = 0;
          TabLayout.this.updateTabViews(false);
          i = bool;
        } 
        if (i != 0)
          super.onMeasure(param1Int1, param1Int2); 
      } 
    }
    
    public void onRtlPropertiesChanged(int param1Int) {
      super.onRtlPropertiesChanged(param1Int);
      if (Build.VERSION.SDK_INT < 23 && this.layoutDirection != param1Int) {
        requestLayout();
        this.layoutDirection = param1Int;
      } 
    }
    
    void setIndicatorPosition(int param1Int1, int param1Int2) {
      if (param1Int1 != this.indicatorLeft || param1Int2 != this.indicatorRight) {
        this.indicatorLeft = param1Int1;
        this.indicatorRight = param1Int2;
        ViewCompat.postInvalidateOnAnimation((View)this);
      } 
    }
    
    void setIndicatorPositionFromTabPosition(int param1Int, float param1Float) {
      if (this.indicatorAnimator != null && this.indicatorAnimator.isRunning())
        this.indicatorAnimator.cancel(); 
      this.selectedPosition = param1Int;
      this.selectionOffset = param1Float;
      updateIndicatorPosition();
    }
    
    void setSelectedIndicatorColor(int param1Int) {
      if (this.selectedIndicatorPaint.getColor() != param1Int) {
        this.selectedIndicatorPaint.setColor(param1Int);
        ViewCompat.postInvalidateOnAnimation((View)this);
      } 
    }
    
    void setSelectedIndicatorHeight(int param1Int) {
      if (this.selectedIndicatorHeight != param1Int) {
        this.selectedIndicatorHeight = param1Int;
        ViewCompat.postInvalidateOnAnimation((View)this);
      } 
    }
  }
  
  class null implements ValueAnimator.AnimatorUpdateListener {
    public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
      float f = param1ValueAnimator.getAnimatedFraction();
      this.this$1.setIndicatorPosition(AnimationUtils.lerp(startLeft, finalTargetLeft, f), AnimationUtils.lerp(startRight, finalTargetRight, f));
    }
  }
  
  class null extends AnimatorListenerAdapter {
    public void onAnimationEnd(Animator param1Animator) {
      this.this$1.selectedPosition = position;
      this.this$1.selectionOffset = 0.0F;
    }
  }
  
  public static class Tab {
    public static final int INVALID_POSITION = -1;
    
    private CharSequence contentDesc;
    
    private View customView;
    
    private Drawable icon;
    
    public TabLayout parent;
    
    private int position = -1;
    
    private Object tag;
    
    private CharSequence text;
    
    public TabLayout.TabView view;
    
    @Nullable
    public CharSequence getContentDescription() {
      CharSequence charSequence;
      if (this.view == null) {
        charSequence = null;
      } else {
        charSequence = this.view.getContentDescription();
      } 
      return charSequence;
    }
    
    @Nullable
    public View getCustomView() {
      return this.customView;
    }
    
    @Nullable
    public Drawable getIcon() {
      return this.icon;
    }
    
    public int getPosition() {
      return this.position;
    }
    
    @Nullable
    public Object getTag() {
      return this.tag;
    }
    
    @Nullable
    public CharSequence getText() {
      return this.text;
    }
    
    public boolean isSelected() {
      boolean bool;
      if (this.parent == null)
        throw new IllegalArgumentException("Tab not attached to a TabLayout"); 
      if (this.parent.getSelectedTabPosition() == this.position) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void reset() {
      this.parent = null;
      this.view = null;
      this.tag = null;
      this.icon = null;
      this.text = null;
      this.contentDesc = null;
      this.position = -1;
      this.customView = null;
    }
    
    public void select() {
      if (this.parent == null)
        throw new IllegalArgumentException("Tab not attached to a TabLayout"); 
      this.parent.selectTab(this);
    }
    
    @NonNull
    public Tab setContentDescription(@StringRes int param1Int) {
      if (this.parent == null)
        throw new IllegalArgumentException("Tab not attached to a TabLayout"); 
      return setContentDescription(this.parent.getResources().getText(param1Int));
    }
    
    @NonNull
    public Tab setContentDescription(@Nullable CharSequence param1CharSequence) {
      this.contentDesc = param1CharSequence;
      updateView();
      return this;
    }
    
    @NonNull
    public Tab setCustomView(@LayoutRes int param1Int) {
      return setCustomView(LayoutInflater.from(this.view.getContext()).inflate(param1Int, (ViewGroup)this.view, false));
    }
    
    @NonNull
    public Tab setCustomView(@Nullable View param1View) {
      this.customView = param1View;
      updateView();
      return this;
    }
    
    @NonNull
    public Tab setIcon(@DrawableRes int param1Int) {
      if (this.parent == null)
        throw new IllegalArgumentException("Tab not attached to a TabLayout"); 
      return setIcon(AppCompatResources.getDrawable(this.parent.getContext(), param1Int));
    }
    
    @NonNull
    public Tab setIcon(@Nullable Drawable param1Drawable) {
      this.icon = param1Drawable;
      updateView();
      return this;
    }
    
    void setPosition(int param1Int) {
      this.position = param1Int;
    }
    
    @NonNull
    public Tab setTag(@Nullable Object param1Object) {
      this.tag = param1Object;
      return this;
    }
    
    @NonNull
    public Tab setText(@StringRes int param1Int) {
      if (this.parent == null)
        throw new IllegalArgumentException("Tab not attached to a TabLayout"); 
      return setText(this.parent.getResources().getText(param1Int));
    }
    
    @NonNull
    public Tab setText(@Nullable CharSequence param1CharSequence) {
      if (TextUtils.isEmpty(this.contentDesc) && !TextUtils.isEmpty(param1CharSequence))
        this.view.setContentDescription(param1CharSequence); 
      this.text = param1CharSequence;
      updateView();
      return this;
    }
    
    void updateView() {
      if (this.view != null)
        this.view.update(); 
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface TabGravity {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface TabIndicatorGravity {}
  
  public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private int previousScrollState;
    
    private int scrollState;
    
    private final WeakReference<TabLayout> tabLayoutRef;
    
    public TabLayoutOnPageChangeListener(TabLayout param1TabLayout) {
      this.tabLayoutRef = new WeakReference<>(param1TabLayout);
    }
    
    public void onPageScrollStateChanged(int param1Int) {
      this.previousScrollState = this.scrollState;
      this.scrollState = param1Int;
    }
    
    public void onPageScrolled(int param1Int1, float param1Float, int param1Int2) {
      TabLayout tabLayout = this.tabLayoutRef.get();
      if (tabLayout != null) {
        boolean bool2;
        param1Int2 = this.scrollState;
        boolean bool1 = false;
        if (param1Int2 != 2 || this.previousScrollState == 1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        if (this.scrollState != 2 || this.previousScrollState != 0)
          bool1 = true; 
        tabLayout.setScrollPosition(param1Int1, param1Float, bool2, bool1);
      } 
    }
    
    public void onPageSelected(int param1Int) {
      TabLayout tabLayout = this.tabLayoutRef.get();
      if (tabLayout != null && tabLayout.getSelectedTabPosition() != param1Int && param1Int < tabLayout.getTabCount()) {
        boolean bool;
        if (this.scrollState == 0 || (this.scrollState == 2 && this.previousScrollState == 0)) {
          bool = true;
        } else {
          bool = false;
        } 
        tabLayout.selectTab(tabLayout.getTabAt(param1Int), bool);
      } 
    }
    
    void reset() {
      this.scrollState = 0;
      this.previousScrollState = 0;
    }
  }
  
  class TabView extends LinearLayout {
    @Nullable
    private Drawable baseBackgroundDrawable;
    
    private ImageView customIconView;
    
    private TextView customTextView;
    
    private View customView;
    
    private int defaultMaxLines = 2;
    
    private ImageView iconView;
    
    private TabLayout.Tab tab;
    
    private TextView textView;
    
    public TabView(Context param1Context) {
      super(param1Context);
      updateBackgroundDrawable(param1Context);
      ViewCompat.setPaddingRelative((View)this, TabLayout.this.tabPaddingStart, TabLayout.this.tabPaddingTop, TabLayout.this.tabPaddingEnd, TabLayout.this.tabPaddingBottom);
      setGravity(17);
      setOrientation(TabLayout.this.inlineLabel ^ true);
      setClickable(true);
      ViewCompat.setPointerIcon((View)this, PointerIconCompat.getSystemIcon(getContext(), 1002));
    }
    
    private float approximateLineWidth(Layout param1Layout, int param1Int, float param1Float) {
      return param1Layout.getLineWidth(param1Int) * param1Float / param1Layout.getPaint().getTextSize();
    }
    
    private void drawBackground(Canvas param1Canvas) {
      if (this.baseBackgroundDrawable != null) {
        this.baseBackgroundDrawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
        this.baseBackgroundDrawable.draw(param1Canvas);
      } 
    }
    
    private int getContentWidth() {
      View[] arrayOfView = new View[3];
      TextView textView = this.textView;
      byte b = 0;
      arrayOfView[0] = (View)textView;
      arrayOfView[1] = (View)this.iconView;
      arrayOfView[2] = this.customView;
      int i = arrayOfView.length;
      int j = 0;
      int k = j;
      int m;
      for (m = k; b < i; m = i2) {
        View view = arrayOfView[b];
        int n = j;
        int i1 = k;
        int i2 = m;
        if (view != null) {
          n = j;
          i1 = k;
          i2 = m;
          if (view.getVisibility() == 0) {
            if (m) {
              k = Math.min(k, view.getLeft());
            } else {
              k = view.getLeft();
            } 
            if (m != 0) {
              m = Math.max(j, view.getRight());
            } else {
              m = view.getRight();
            } 
            i2 = 1;
            i1 = k;
            n = m;
          } 
        } 
        b++;
        j = n;
        k = i1;
      } 
      return j - k;
    }
    
    private void updateBackgroundDrawable(Context param1Context) {
      LayerDrawable layerDrawable;
      if (TabLayout.this.tabBackgroundResId != 0) {
        this.baseBackgroundDrawable = AppCompatResources.getDrawable(param1Context, TabLayout.this.tabBackgroundResId);
        if (this.baseBackgroundDrawable != null && this.baseBackgroundDrawable.isStateful())
          this.baseBackgroundDrawable.setState(getDrawableState()); 
      } else {
        this.baseBackgroundDrawable = null;
      } 
      GradientDrawable gradientDrawable2 = new GradientDrawable();
      gradientDrawable2.setColor(0);
      GradientDrawable gradientDrawable1 = gradientDrawable2;
      if (TabLayout.this.tabRippleColorStateList != null) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5F);
        gradientDrawable.setColor(-1);
        ColorStateList colorStateList = RippleUtils.convertToRippleDrawableColor(TabLayout.this.tabRippleColorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
          gradientDrawable1 = gradientDrawable2;
          if (TabLayout.this.unboundedRipple)
            gradientDrawable1 = null; 
          gradientDrawable2 = gradientDrawable;
          if (TabLayout.this.unboundedRipple)
            gradientDrawable2 = null; 
          RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, (Drawable)gradientDrawable1, (Drawable)gradientDrawable2);
        } else {
          Drawable drawable = DrawableCompat.wrap((Drawable)gradientDrawable);
          DrawableCompat.setTintList(drawable, colorStateList);
          layerDrawable = new LayerDrawable(new Drawable[] { (Drawable)gradientDrawable2, drawable });
        } 
      } 
      ViewCompat.setBackground((View)this, (Drawable)layerDrawable);
      TabLayout.this.invalidate();
    }
    
    private void updateTextAndIcon(@Nullable TextView param1TextView, @Nullable ImageView param1ImageView) {
      Drawable drawable;
      CharSequence charSequence;
      if (this.tab != null && this.tab.getIcon() != null) {
        drawable = DrawableCompat.wrap(this.tab.getIcon()).mutate();
      } else {
        drawable = null;
      } 
      if (this.tab != null) {
        charSequence = this.tab.getText();
      } else {
        charSequence = null;
      } 
      if (param1ImageView != null)
        if (drawable != null) {
          param1ImageView.setImageDrawable(drawable);
          param1ImageView.setVisibility(0);
          setVisibility(0);
        } else {
          param1ImageView.setVisibility(8);
          param1ImageView.setImageDrawable(null);
        }  
      int i = TextUtils.isEmpty(charSequence) ^ true;
      if (param1TextView != null)
        if (i != 0) {
          param1TextView.setText(charSequence);
          param1TextView.setVisibility(0);
          setVisibility(0);
        } else {
          param1TextView.setVisibility(8);
          param1TextView.setText(null);
        }  
      if (param1ImageView != null) {
        boolean bool;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)param1ImageView.getLayoutParams();
        if (i != 0 && param1ImageView.getVisibility() == 0) {
          bool = TabLayout.this.dpToPx(8);
        } else {
          bool = false;
        } 
        if (TabLayout.this.inlineLabel) {
          if (bool != MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) {
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, bool);
            marginLayoutParams.bottomMargin = 0;
            param1ImageView.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
            param1ImageView.requestLayout();
          } 
        } else if (bool != marginLayoutParams.bottomMargin) {
          marginLayoutParams.bottomMargin = bool;
          MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, 0);
          param1ImageView.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
          param1ImageView.requestLayout();
        } 
      } 
      if (this.tab != null) {
        CharSequence charSequence1 = this.tab.contentDesc;
      } else {
        param1TextView = null;
      } 
      if (i != 0)
        param1TextView = null; 
      TooltipCompat.setTooltipText((View)this, (CharSequence)param1TextView);
    }
    
    protected void drawableStateChanged() {
      super.drawableStateChanged();
      int[] arrayOfInt = getDrawableState();
      Drawable drawable = this.baseBackgroundDrawable;
      byte b = 0;
      int i = b;
      if (drawable != null) {
        i = b;
        if (this.baseBackgroundDrawable.isStateful())
          i = false | this.baseBackgroundDrawable.setState(arrayOfInt); 
      } 
      if (i != 0) {
        invalidate();
        TabLayout.this.invalidate();
      } 
    }
    
    public TabLayout.Tab getTab() {
      return this.tab;
    }
    
    public void onInitializeAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(ActionBar.Tab.class.getName());
    }
    
    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      super.onInitializeAccessibilityNodeInfo(param1AccessibilityNodeInfo);
      param1AccessibilityNodeInfo.setClassName(ActionBar.Tab.class.getName());
    }
    
    public void onMeasure(int param1Int1, int param1Int2) {
      // Byte code:
      //   0: iload_1
      //   1: invokestatic getSize : (I)I
      //   4: istore_3
      //   5: iload_1
      //   6: invokestatic getMode : (I)I
      //   9: istore #4
      //   11: aload_0
      //   12: getfield this$0 : Landroid/support/design/widget/TabLayout;
      //   15: invokevirtual getTabMaxWidth : ()I
      //   18: istore #5
      //   20: iload_1
      //   21: istore #6
      //   23: iload #5
      //   25: ifle -> 57
      //   28: iload #4
      //   30: ifeq -> 42
      //   33: iload_1
      //   34: istore #6
      //   36: iload_3
      //   37: iload #5
      //   39: if_icmple -> 57
      //   42: aload_0
      //   43: getfield this$0 : Landroid/support/design/widget/TabLayout;
      //   46: getfield tabMaxWidth : I
      //   49: ldc_w -2147483648
      //   52: invokestatic makeMeasureSpec : (II)I
      //   55: istore #6
      //   57: aload_0
      //   58: iload #6
      //   60: iload_2
      //   61: invokespecial onMeasure : (II)V
      //   64: aload_0
      //   65: getfield textView : Landroid/widget/TextView;
      //   68: ifnull -> 321
      //   71: aload_0
      //   72: getfield this$0 : Landroid/support/design/widget/TabLayout;
      //   75: getfield tabTextSize : F
      //   78: fstore #7
      //   80: aload_0
      //   81: getfield defaultMaxLines : I
      //   84: istore #5
      //   86: aload_0
      //   87: getfield iconView : Landroid/widget/ImageView;
      //   90: astore #8
      //   92: iconst_1
      //   93: istore_3
      //   94: aload #8
      //   96: ifnull -> 118
      //   99: aload_0
      //   100: getfield iconView : Landroid/widget/ImageView;
      //   103: invokevirtual getVisibility : ()I
      //   106: ifne -> 118
      //   109: iconst_1
      //   110: istore_1
      //   111: fload #7
      //   113: fstore #9
      //   115: goto -> 162
      //   118: fload #7
      //   120: fstore #9
      //   122: iload #5
      //   124: istore_1
      //   125: aload_0
      //   126: getfield textView : Landroid/widget/TextView;
      //   129: ifnull -> 162
      //   132: fload #7
      //   134: fstore #9
      //   136: iload #5
      //   138: istore_1
      //   139: aload_0
      //   140: getfield textView : Landroid/widget/TextView;
      //   143: invokevirtual getLineCount : ()I
      //   146: iconst_1
      //   147: if_icmple -> 162
      //   150: aload_0
      //   151: getfield this$0 : Landroid/support/design/widget/TabLayout;
      //   154: getfield tabTextMultiLineSize : F
      //   157: fstore #9
      //   159: iload #5
      //   161: istore_1
      //   162: aload_0
      //   163: getfield textView : Landroid/widget/TextView;
      //   166: invokevirtual getTextSize : ()F
      //   169: fstore #7
      //   171: aload_0
      //   172: getfield textView : Landroid/widget/TextView;
      //   175: invokevirtual getLineCount : ()I
      //   178: istore #10
      //   180: aload_0
      //   181: getfield textView : Landroid/widget/TextView;
      //   184: invokestatic getMaxLines : (Landroid/widget/TextView;)I
      //   187: istore #5
      //   189: fload #9
      //   191: fload #7
      //   193: fcmpl
      //   194: istore #4
      //   196: iload #4
      //   198: ifne -> 212
      //   201: iload #5
      //   203: iflt -> 321
      //   206: iload_1
      //   207: iload #5
      //   209: if_icmpeq -> 321
      //   212: iload_3
      //   213: istore #5
      //   215: aload_0
      //   216: getfield this$0 : Landroid/support/design/widget/TabLayout;
      //   219: getfield mode : I
      //   222: iconst_1
      //   223: if_icmpne -> 291
      //   226: iload_3
      //   227: istore #5
      //   229: iload #4
      //   231: ifle -> 291
      //   234: iload_3
      //   235: istore #5
      //   237: iload #10
      //   239: iconst_1
      //   240: if_icmpne -> 291
      //   243: aload_0
      //   244: getfield textView : Landroid/widget/TextView;
      //   247: invokevirtual getLayout : ()Landroid/text/Layout;
      //   250: astore #8
      //   252: aload #8
      //   254: ifnull -> 288
      //   257: iload_3
      //   258: istore #5
      //   260: aload_0
      //   261: aload #8
      //   263: iconst_0
      //   264: fload #9
      //   266: invokespecial approximateLineWidth : (Landroid/text/Layout;IF)F
      //   269: aload_0
      //   270: invokevirtual getMeasuredWidth : ()I
      //   273: aload_0
      //   274: invokevirtual getPaddingLeft : ()I
      //   277: isub
      //   278: aload_0
      //   279: invokevirtual getPaddingRight : ()I
      //   282: isub
      //   283: i2f
      //   284: fcmpl
      //   285: ifle -> 291
      //   288: iconst_0
      //   289: istore #5
      //   291: iload #5
      //   293: ifeq -> 321
      //   296: aload_0
      //   297: getfield textView : Landroid/widget/TextView;
      //   300: iconst_0
      //   301: fload #9
      //   303: invokevirtual setTextSize : (IF)V
      //   306: aload_0
      //   307: getfield textView : Landroid/widget/TextView;
      //   310: iload_1
      //   311: invokevirtual setMaxLines : (I)V
      //   314: aload_0
      //   315: iload #6
      //   317: iload_2
      //   318: invokespecial onMeasure : (II)V
      //   321: return
    }
    
    public boolean performClick() {
      boolean bool = super.performClick();
      if (this.tab != null) {
        if (!bool)
          playSoundEffect(0); 
        this.tab.select();
        return true;
      } 
      return bool;
    }
    
    void reset() {
      setTab((TabLayout.Tab)null);
      setSelected(false);
    }
    
    public void setSelected(boolean param1Boolean) {
      boolean bool;
      if (isSelected() != param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      super.setSelected(param1Boolean);
      if (bool && param1Boolean && Build.VERSION.SDK_INT < 16)
        sendAccessibilityEvent(4); 
      if (this.textView != null)
        this.textView.setSelected(param1Boolean); 
      if (this.iconView != null)
        this.iconView.setSelected(param1Boolean); 
      if (this.customView != null)
        this.customView.setSelected(param1Boolean); 
    }
    
    void setTab(@Nullable TabLayout.Tab param1Tab) {
      if (param1Tab != this.tab) {
        this.tab = param1Tab;
        update();
      } 
    }
    
    final void update() {
      TabLayout.Tab tab = this.tab;
      View view1 = null;
      if (tab != null) {
        view2 = tab.getCustomView();
      } else {
        view2 = null;
      } 
      if (view2 != null) {
        ViewParent viewParent = view2.getParent();
        if (viewParent != this) {
          if (viewParent != null)
            ((ViewGroup)viewParent).removeView(view2); 
          addView(view2);
        } 
        this.customView = view2;
        if (this.textView != null)
          this.textView.setVisibility(8); 
        if (this.iconView != null) {
          this.iconView.setVisibility(8);
          this.iconView.setImageDrawable(null);
        } 
        this.customTextView = (TextView)view2.findViewById(16908308);
        if (this.customTextView != null)
          this.defaultMaxLines = TextViewCompat.getMaxLines(this.customTextView); 
        this.customIconView = (ImageView)view2.findViewById(16908294);
      } else {
        if (this.customView != null) {
          removeView(this.customView);
          this.customView = null;
        } 
        this.customTextView = null;
        this.customIconView = null;
      } 
      View view2 = this.customView;
      boolean bool1 = false;
      if (view2 == null) {
        Drawable drawable;
        if (this.iconView == null) {
          ImageView imageView = (ImageView)LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, (ViewGroup)this, false);
          addView((View)imageView, 0);
          this.iconView = imageView;
        } 
        view2 = view1;
        if (tab != null) {
          view2 = view1;
          if (tab.getIcon() != null)
            drawable = DrawableCompat.wrap(tab.getIcon()).mutate(); 
        } 
        if (drawable != null) {
          DrawableCompat.setTintList(drawable, TabLayout.this.tabIconTint);
          if (TabLayout.this.tabIconTintMode != null)
            DrawableCompat.setTintMode(drawable, TabLayout.this.tabIconTintMode); 
        } 
        if (this.textView == null) {
          TextView textView = (TextView)LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, (ViewGroup)this, false);
          addView((View)textView);
          this.textView = textView;
          this.defaultMaxLines = TextViewCompat.getMaxLines(this.textView);
        } 
        TextViewCompat.setTextAppearance(this.textView, TabLayout.this.tabTextAppearance);
        if (TabLayout.this.tabTextColors != null)
          this.textView.setTextColor(TabLayout.this.tabTextColors); 
        updateTextAndIcon(this.textView, this.iconView);
      } else if (this.customTextView != null || this.customIconView != null) {
        updateTextAndIcon(this.customTextView, this.customIconView);
      } 
      if (tab != null && !TextUtils.isEmpty(tab.contentDesc))
        setContentDescription(tab.contentDesc); 
      boolean bool2 = bool1;
      if (tab != null) {
        bool2 = bool1;
        if (tab.isSelected())
          bool2 = true; 
      } 
      setSelected(bool2);
    }
    
    final void updateOrientation() {
      setOrientation(TabLayout.this.inlineLabel ^ true);
      if (this.customTextView != null || this.customIconView != null) {
        updateTextAndIcon(this.customTextView, this.customIconView);
        return;
      } 
      updateTextAndIcon(this.textView, this.iconView);
    }
  }
  
  public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
    private final ViewPager viewPager;
    
    public ViewPagerOnTabSelectedListener(ViewPager param1ViewPager) {
      this.viewPager = param1ViewPager;
    }
    
    public void onTabReselected(TabLayout.Tab param1Tab) {}
    
    public void onTabSelected(TabLayout.Tab param1Tab) {
      this.viewPager.setCurrentItem(param1Tab.getPosition());
    }
    
    public void onTabUnselected(TabLayout.Tab param1Tab) {}
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\TabLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */