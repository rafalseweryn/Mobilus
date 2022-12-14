package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
  static final int GENERATED_ITEM_PADDING = 4;
  
  static final int MIN_CELL_SIZE = 56;
  
  private static final String TAG = "ActionMenuView";
  
  private MenuPresenter.Callback mActionMenuPresenterCallback;
  
  private boolean mFormatItems;
  
  private int mFormatItemsWidth;
  
  private int mGeneratedItemPadding;
  
  private MenuBuilder mMenu;
  
  MenuBuilder.Callback mMenuBuilderCallback;
  
  private int mMinCellSize;
  
  OnMenuItemClickListener mOnMenuItemClickListener;
  
  private Context mPopupContext;
  
  private int mPopupTheme;
  
  private ActionMenuPresenter mPresenter;
  
  private boolean mReserveOverflow;
  
  public ActionMenuView(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public ActionMenuView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    setBaselineAligned(false);
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    this.mMinCellSize = (int)(56.0F * f);
    this.mGeneratedItemPadding = (int)(f * 4.0F);
    this.mPopupContext = paramContext;
    this.mPopupTheme = 0;
  }
  
  static int measureChildForCells(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    ActionMenuItemView actionMenuItemView;
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt3) - paramInt4, View.MeasureSpec.getMode(paramInt3));
    if (paramView instanceof ActionMenuItemView) {
      actionMenuItemView = (ActionMenuItemView)paramView;
    } else {
      actionMenuItemView = null;
    } 
    boolean bool1 = false;
    if (actionMenuItemView != null && actionMenuItemView.hasText()) {
      paramInt3 = 1;
    } else {
      paramInt3 = 0;
    } 
    paramInt4 = 2;
    if (paramInt2 > 0 && (paramInt3 == 0 || paramInt2 >= 2)) {
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt2 * paramInt1, -2147483648), i);
      int j = paramView.getMeasuredWidth();
      int k = j / paramInt1;
      paramInt2 = k;
      if (j % paramInt1 != 0)
        paramInt2 = k + 1; 
      if (paramInt3 != 0 && paramInt2 < 2)
        paramInt2 = paramInt4; 
    } else {
      paramInt2 = 0;
    } 
    boolean bool2 = bool1;
    if (!layoutParams.isOverflowButton) {
      bool2 = bool1;
      if (paramInt3 != 0)
        bool2 = true; 
    } 
    layoutParams.expandable = bool2;
    layoutParams.cellsUsed = paramInt2;
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 * paramInt2, 1073741824), i);
    return paramInt2;
  }
  
  private void onMeasureExactFormat(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getSize(paramInt2);
    paramInt1 = getPaddingLeft();
    int m = getPaddingRight();
    int n = getPaddingTop() + getPaddingBottom();
    int i1 = getChildMeasureSpec(paramInt2, n, -2);
    int i2 = j - paramInt1 + m;
    int i3 = i2 / this.mMinCellSize;
    paramInt1 = this.mMinCellSize;
    if (i3 == 0) {
      setMeasuredDimension(i2, 0);
      return;
    } 
    int i4 = this.mMinCellSize + i2 % paramInt1 / i3;
    int i5 = getChildCount();
    int i6 = 0;
    int i7 = i6;
    m = i7;
    paramInt2 = m;
    paramInt1 = paramInt2;
    j = paramInt1;
    long l = 0L;
    int i8 = paramInt1;
    paramInt1 = i3;
    int i9 = paramInt2;
    paramInt2 = k;
    k = i2;
    while (i6 < i5) {
      View view = getChildAt(i6);
      if (view.getVisibility() != 8) {
        boolean bool = view instanceof ActionMenuItemView;
        i9++;
        if (bool)
          view.setPadding(this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0); 
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        layoutParams.expanded = false;
        layoutParams.extraPixels = 0;
        layoutParams.cellsUsed = 0;
        layoutParams.expandable = false;
        layoutParams.leftMargin = 0;
        layoutParams.rightMargin = 0;
        if (bool && ((ActionMenuItemView)view).hasText()) {
          bool = true;
        } else {
          bool = false;
        } 
        layoutParams.preventEdgeOffset = bool;
        if (layoutParams.isOverflowButton) {
          i3 = 1;
        } else {
          i3 = paramInt1;
        } 
        i2 = measureChildForCells(view, i4, i3, i1, n);
        i8 = Math.max(i8, i2);
        i3 = j;
        if (layoutParams.expandable)
          i3 = j + 1; 
        if (layoutParams.isOverflowButton)
          m = 1; 
        paramInt1 -= i2;
        i7 = Math.max(i7, view.getMeasuredHeight());
        if (i2 == 1)
          l |= (1 << i6); 
        j = i3;
      } 
      i6++;
    } 
    if (m != 0 && i9 == 2) {
      i2 = 1;
    } else {
      i2 = 0;
    } 
    byte b = 0;
    n = paramInt1;
    i3 = i5;
    i6 = i1;
    for (paramInt1 = b; j > 0 && n > 0; paramInt1 = 1) {
      i5 = Integer.MAX_VALUE;
      b = 0;
      int i10 = 0;
      long l1;
      for (l1 = 0L; b < i3; l1 = l2) {
        int i11;
        long l2;
        LayoutParams layoutParams = (LayoutParams)getChildAt(b).getLayoutParams();
        if (!layoutParams.expandable) {
          i1 = i10;
          i11 = i5;
          l2 = l1;
        } else if (layoutParams.cellsUsed < i5) {
          i11 = layoutParams.cellsUsed;
          l2 = 1L << b;
          i1 = 1;
        } else {
          i1 = i10;
          i11 = i5;
          l2 = l1;
          if (layoutParams.cellsUsed == i5) {
            l2 = l1 | 1L << b;
            i1 = i10 + 1;
            i11 = i5;
          } 
        } 
        b++;
        i10 = i1;
        i5 = i11;
      } 
      l |= l1;
      if (i10 > n)
        break; 
      i1 = 0;
      while (i1 < i3) {
        long l2;
        View view = getChildAt(i1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        long l3 = (1 << i1);
        if ((l1 & l3) == 0L) {
          paramInt1 = n;
          l2 = l;
          if (layoutParams.cellsUsed == i5 + 1) {
            l2 = l | l3;
            paramInt1 = n;
          } 
        } else {
          if (i2 != 0 && layoutParams.preventEdgeOffset && n == 1)
            view.setPadding(this.mGeneratedItemPadding + i4, 0, this.mGeneratedItemPadding, 0); 
          layoutParams.cellsUsed++;
          layoutParams.expanded = true;
          paramInt1 = n - 1;
          l2 = l;
        } 
        i1++;
        n = paramInt1;
        l = l2;
      } 
    } 
    if (m == 0 && i9 == 1) {
      j = 1;
    } else {
      j = 0;
    } 
    if (n > 0 && l != 0L && (n < i9 - 1 || j != 0 || i8 > 1)) {
      float f = Long.bitCount(l);
      if (j == 0) {
        float f1;
        if ((l & 0x1L) != 0L) {
          f1 = f;
          if (!((LayoutParams)getChildAt(0).getLayoutParams()).preventEdgeOffset)
            f1 = f - 0.5F; 
        } else {
          f1 = f;
        } 
        j = i3 - 1;
        f = f1;
        if (((1 << j) & l) != 0L) {
          f = f1;
          if (!((LayoutParams)getChildAt(j).getLayoutParams()).preventEdgeOffset)
            f = f1 - 0.5F; 
        } 
      } 
      if (f > 0.0F) {
        m = (int)((n * i4) / f);
      } else {
        m = 0;
      } 
      i1 = 0;
      i8 = i3;
      while (true) {
        j = paramInt1;
        if (i1 < i8) {
          if (((1 << i1) & l) == 0L) {
            j = paramInt1;
          } else {
            View view = getChildAt(i1);
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (view instanceof ActionMenuItemView) {
              layoutParams.extraPixels = m;
              layoutParams.expanded = true;
              if (i1 == 0 && !layoutParams.preventEdgeOffset)
                layoutParams.leftMargin = -m / 2; 
              j = 1;
            } else if (layoutParams.isOverflowButton) {
              layoutParams.extraPixels = m;
              layoutParams.expanded = true;
              layoutParams.rightMargin = -m / 2;
              j = 1;
            } else {
              if (i1 != 0)
                layoutParams.leftMargin = m / 2; 
              j = paramInt1;
              if (i1 != i8 - 1) {
                layoutParams.rightMargin = m / 2;
                j = paramInt1;
              } 
            } 
          } 
          i1++;
          paramInt1 = j;
          continue;
        } 
        break;
      } 
    } else {
      j = paramInt1;
    } 
    paramInt1 = 0;
    if (j != 0)
      while (paramInt1 < i3) {
        View view = getChildAt(paramInt1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.expanded)
          view.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.cellsUsed * i4 + layoutParams.extraPixels, 1073741824), i6); 
        paramInt1++;
      }  
    if (i != 1073741824) {
      paramInt1 = i7;
    } else {
      paramInt1 = paramInt2;
    } 
    setMeasuredDimension(k, paramInt1);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (paramLayoutParams != null && paramLayoutParams instanceof LayoutParams) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void dismissPopupMenus() {
    if (this.mPresenter != null)
      this.mPresenter.dismissPopupMenus(); 
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    return false;
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    LayoutParams layoutParams = new LayoutParams(-2, -2);
    layoutParams.gravity = 16;
    return layoutParams;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    if (paramLayoutParams != null) {
      LayoutParams layoutParams;
      if (paramLayoutParams instanceof LayoutParams) {
        layoutParams = new LayoutParams((LayoutParams)paramLayoutParams);
      } else {
        layoutParams = new LayoutParams((ViewGroup.LayoutParams)layoutParams);
      } 
      if (layoutParams.gravity <= 0)
        layoutParams.gravity = 16; 
      return layoutParams;
    } 
    return generateDefaultLayoutParams();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public LayoutParams generateOverflowButtonLayoutParams() {
    LayoutParams layoutParams = generateDefaultLayoutParams();
    layoutParams.isOverflowButton = true;
    return layoutParams;
  }
  
  public Menu getMenu() {
    if (this.mMenu == null) {
      MenuPresenter.Callback callback;
      Context context = getContext();
      this.mMenu = new MenuBuilder(context);
      this.mMenu.setCallback(new MenuBuilderCallback());
      this.mPresenter = new ActionMenuPresenter(context);
      this.mPresenter.setReserveOverflow(true);
      ActionMenuPresenter actionMenuPresenter = this.mPresenter;
      if (this.mActionMenuPresenterCallback != null) {
        callback = this.mActionMenuPresenterCallback;
      } else {
        callback = new ActionMenuPresenterCallback();
      } 
      actionMenuPresenter.setCallback(callback);
      this.mMenu.addMenuPresenter((MenuPresenter)this.mPresenter, this.mPopupContext);
      this.mPresenter.setMenuView(this);
    } 
    return (Menu)this.mMenu;
  }
  
  @Nullable
  public Drawable getOverflowIcon() {
    getMenu();
    return this.mPresenter.getOverflowIcon();
  }
  
  public int getPopupTheme() {
    return this.mPopupTheme;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getWindowAnimations() {
    return 0;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected boolean hasSupportDividerBeforeChildAt(int paramInt) {
    boolean bool;
    int i = 0;
    if (paramInt == 0)
      return false; 
    View view1 = getChildAt(paramInt - 1);
    View view2 = getChildAt(paramInt);
    int j = i;
    if (paramInt < getChildCount()) {
      j = i;
      if (view1 instanceof ActionMenuChildView)
        j = false | ((ActionMenuChildView)view1).needsDividerAfter(); 
    } 
    i = j;
    if (paramInt > 0) {
      i = j;
      if (view2 instanceof ActionMenuChildView)
        bool = j | ((ActionMenuChildView)view2).needsDividerBefore(); 
    } 
    return bool;
  }
  
  public boolean hideOverflowMenu() {
    boolean bool;
    if (this.mPresenter != null && this.mPresenter.hideOverflowMenu()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void initialize(MenuBuilder paramMenuBuilder) {
    this.mMenu = paramMenuBuilder;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean invokeItem(MenuItemImpl paramMenuItemImpl) {
    return this.mMenu.performItemAction((MenuItem)paramMenuItemImpl, 0);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean isOverflowMenuShowPending() {
    boolean bool;
    if (this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOverflowMenuShowing() {
    boolean bool;
    if (this.mPresenter != null && this.mPresenter.isOverflowMenuShowing()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean isOverflowReserved() {
    return this.mReserveOverflow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (this.mPresenter != null) {
      this.mPresenter.updateMenuView(false);
      if (this.mPresenter.isOverflowMenuShowing()) {
        this.mPresenter.hideOverflowMenu();
        this.mPresenter.showOverflowMenu();
      } 
    } 
  }
  
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    dismissPopupMenus();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!this.mFormatItems) {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } 
    int i = getChildCount();
    int j = (paramInt4 - paramInt2) / 2;
    int k = getDividerWidth();
    int m = paramInt3 - paramInt1;
    paramInt1 = getPaddingRight();
    paramInt2 = getPaddingLeft();
    paramBoolean = ViewUtils.isLayoutRtl((View)this);
    paramInt1 = m - paramInt1 - paramInt2;
    paramInt2 = 0;
    paramInt4 = 0;
    paramInt3 = 0;
    while (paramInt2 < i) {
      View view = getChildAt(paramInt2);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.isOverflowButton) {
          int i3;
          int i1 = view.getMeasuredWidth();
          paramInt4 = i1;
          if (hasSupportDividerBeforeChildAt(paramInt2))
            paramInt4 = i1 + k; 
          int i2 = view.getMeasuredHeight();
          if (paramBoolean) {
            i1 = getPaddingLeft() + layoutParams.leftMargin;
            i3 = i1 + paramInt4;
          } else {
            i3 = getWidth() - getPaddingRight() - layoutParams.rightMargin;
            i1 = i3 - paramInt4;
          } 
          int i4 = j - i2 / 2;
          view.layout(i1, i4, i3, i2 + i4);
          paramInt1 -= paramInt4;
          paramInt4 = 1;
        } else {
          paramInt1 -= view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
          hasSupportDividerBeforeChildAt(paramInt2);
          paramInt3++;
        } 
      } 
      paramInt2++;
    } 
    if (i == 1 && paramInt4 == 0) {
      View view = getChildAt(0);
      paramInt1 = view.getMeasuredWidth();
      paramInt2 = view.getMeasuredHeight();
      paramInt3 = m / 2 - paramInt1 / 2;
      paramInt4 = j - paramInt2 / 2;
      view.layout(paramInt3, paramInt4, paramInt1 + paramInt3, paramInt2 + paramInt4);
      return;
    } 
    paramInt2 = paramInt3 - (paramInt4 ^ 0x1);
    if (paramInt2 > 0) {
      paramInt1 /= paramInt2;
    } else {
      paramInt1 = 0;
    } 
    paramInt4 = 0;
    paramInt3 = 0;
    int n = Math.max(0, paramInt1);
    if (paramBoolean) {
      paramInt2 = getWidth() - getPaddingRight();
      paramInt1 = paramInt3;
      while (paramInt1 < i) {
        View view = getChildAt(paramInt1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        paramInt3 = paramInt2;
        if (view.getVisibility() != 8)
          if (layoutParams.isOverflowButton) {
            paramInt3 = paramInt2;
          } else {
            paramInt3 = paramInt2 - layoutParams.rightMargin;
            paramInt4 = view.getMeasuredWidth();
            paramInt2 = view.getMeasuredHeight();
            int i1 = j - paramInt2 / 2;
            view.layout(paramInt3 - paramInt4, i1, paramInt3, paramInt2 + i1);
            paramInt3 -= paramInt4 + layoutParams.leftMargin + n;
          }  
        paramInt1++;
        paramInt2 = paramInt3;
      } 
    } else {
      paramInt2 = getPaddingLeft();
      paramInt1 = paramInt4;
      while (paramInt1 < i) {
        View view = getChildAt(paramInt1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        paramInt3 = paramInt2;
        if (view.getVisibility() != 8)
          if (layoutParams.isOverflowButton) {
            paramInt3 = paramInt2;
          } else {
            int i1 = paramInt2 + layoutParams.leftMargin;
            paramInt4 = view.getMeasuredWidth();
            paramInt2 = view.getMeasuredHeight();
            paramInt3 = j - paramInt2 / 2;
            view.layout(i1, paramInt3, i1 + paramInt4, paramInt2 + paramInt3);
            paramInt3 = i1 + paramInt4 + layoutParams.rightMargin + n;
          }  
        paramInt1++;
        paramInt2 = paramInt3;
      } 
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    boolean bool1;
    boolean bool = this.mFormatItems;
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.mFormatItems = bool1;
    if (bool != this.mFormatItems)
      this.mFormatItemsWidth = 0; 
    int i = View.MeasureSpec.getSize(paramInt1);
    if (this.mFormatItems && this.mMenu != null && i != this.mFormatItemsWidth) {
      this.mFormatItemsWidth = i;
      this.mMenu.onItemsChanged(true);
    } 
    int j = getChildCount();
    if (this.mFormatItems && j > 0) {
      onMeasureExactFormat(paramInt1, paramInt2);
    } else {
      for (i = 0; i < j; i++) {
        LayoutParams layoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
        layoutParams.rightMargin = 0;
        layoutParams.leftMargin = 0;
      } 
      super.onMeasure(paramInt1, paramInt2);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public MenuBuilder peekMenu() {
    return this.mMenu;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setExpandedActionViewsExclusive(boolean paramBoolean) {
    this.mPresenter.setExpandedActionViewsExclusive(paramBoolean);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1) {
    this.mActionMenuPresenterCallback = paramCallback;
    this.mMenuBuilderCallback = paramCallback1;
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener) {
    this.mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setOverflowIcon(@Nullable Drawable paramDrawable) {
    getMenu();
    this.mPresenter.setOverflowIcon(paramDrawable);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setOverflowReserved(boolean paramBoolean) {
    this.mReserveOverflow = paramBoolean;
  }
  
  public void setPopupTheme(@StyleRes int paramInt) {
    if (this.mPopupTheme != paramInt) {
      this.mPopupTheme = paramInt;
      if (paramInt == 0) {
        this.mPopupContext = getContext();
      } else {
        this.mPopupContext = (Context)new ContextThemeWrapper(getContext(), paramInt);
      } 
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setPresenter(ActionMenuPresenter paramActionMenuPresenter) {
    this.mPresenter = paramActionMenuPresenter;
    this.mPresenter.setMenuView(this);
  }
  
  public boolean showOverflowMenu() {
    boolean bool;
    if (this.mPresenter != null && this.mPresenter.showOverflowMenu()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static interface ActionMenuChildView {
    boolean needsDividerAfter();
    
    boolean needsDividerBefore();
  }
  
  private static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {}
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      return false;
    }
  }
  
  public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
    @ExportedProperty
    public int cellsUsed;
    
    @ExportedProperty
    public boolean expandable;
    
    boolean expanded;
    
    @ExportedProperty
    public int extraPixels;
    
    @ExportedProperty
    public boolean isOverflowButton;
    
    @ExportedProperty
    public boolean preventEdgeOffset;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.isOverflowButton = false;
    }
    
    LayoutParams(int param1Int1, int param1Int2, boolean param1Boolean) {
      super(param1Int1, param1Int2);
      this.isOverflowButton = param1Boolean;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super((ViewGroup.LayoutParams)param1LayoutParams);
      this.isOverflowButton = param1LayoutParams.isOverflowButton;
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
  }
  
  private class MenuBuilderCallback implements MenuBuilder.Callback {
    public boolean onMenuItemSelected(MenuBuilder param1MenuBuilder, MenuItem param1MenuItem) {
      boolean bool;
      if (ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(param1MenuItem)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onMenuModeChange(MenuBuilder param1MenuBuilder) {
      if (ActionMenuView.this.mMenuBuilderCallback != null)
        ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(param1MenuBuilder); 
    }
  }
  
  public static interface OnMenuItemClickListener {
    boolean onMenuItemClick(MenuItem param1MenuItem);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\ActionMenuView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */