package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.R;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NavigationMenuPresenter implements MenuPresenter {
  private static final String STATE_ADAPTER = "android:menu:adapter";
  
  private static final String STATE_HEADER = "android:menu:header";
  
  private static final String STATE_HIERARCHY = "android:menu:list";
  
  NavigationMenuAdapter adapter;
  
  private MenuPresenter.Callback callback;
  
  LinearLayout headerLayout;
  
  ColorStateList iconTintList;
  
  private int id;
  
  Drawable itemBackground;
  
  int itemHorizontalPadding;
  
  int itemIconPadding;
  
  LayoutInflater layoutInflater;
  
  MenuBuilder menu;
  
  private NavigationMenuView menuView;
  
  final View.OnClickListener onClickListener = new View.OnClickListener() {
      public void onClick(View param1View) {
        NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView)param1View;
        NavigationMenuPresenter.this.setUpdateSuspended(true);
        MenuItemImpl menuItemImpl = navigationMenuItemView.getItemData();
        boolean bool = NavigationMenuPresenter.this.menu.performItemAction((MenuItem)menuItemImpl, NavigationMenuPresenter.this, 0);
        if (menuItemImpl != null && menuItemImpl.isCheckable() && bool)
          NavigationMenuPresenter.this.adapter.setCheckedItem(menuItemImpl); 
        NavigationMenuPresenter.this.setUpdateSuspended(false);
        NavigationMenuPresenter.this.updateMenuView(false);
      }
    };
  
  int paddingSeparator;
  
  private int paddingTopDefault;
  
  int textAppearance;
  
  boolean textAppearanceSet;
  
  ColorStateList textColor;
  
  public void addHeaderView(@NonNull View paramView) {
    this.headerLayout.addView(paramView);
    this.menuView.setPadding(0, 0, 0, this.menuView.getPaddingBottom());
  }
  
  public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl) {
    return false;
  }
  
  public void dispatchApplyWindowInsets(WindowInsetsCompat paramWindowInsetsCompat) {
    int i = paramWindowInsetsCompat.getSystemWindowInsetTop();
    if (this.paddingTopDefault != i) {
      this.paddingTopDefault = i;
      if (this.headerLayout.getChildCount() == 0)
        this.menuView.setPadding(0, this.paddingTopDefault, 0, this.menuView.getPaddingBottom()); 
    } 
    ViewCompat.dispatchApplyWindowInsets((View)this.headerLayout, paramWindowInsetsCompat);
  }
  
  public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl) {
    return false;
  }
  
  public boolean flagActionItems() {
    return false;
  }
  
  @Nullable
  public MenuItemImpl getCheckedItem() {
    return this.adapter.getCheckedItem();
  }
  
  public int getHeaderCount() {
    return this.headerLayout.getChildCount();
  }
  
  public View getHeaderView(int paramInt) {
    return this.headerLayout.getChildAt(paramInt);
  }
  
  public int getId() {
    return this.id;
  }
  
  @Nullable
  public Drawable getItemBackground() {
    return this.itemBackground;
  }
  
  public int getItemHorizontalPadding() {
    return this.itemHorizontalPadding;
  }
  
  public int getItemIconPadding() {
    return this.itemIconPadding;
  }
  
  @Nullable
  public ColorStateList getItemTextColor() {
    return this.textColor;
  }
  
  @Nullable
  public ColorStateList getItemTintList() {
    return this.iconTintList;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup) {
    if (this.menuView == null) {
      this.menuView = (NavigationMenuView)this.layoutInflater.inflate(R.layout.design_navigation_menu, paramViewGroup, false);
      if (this.adapter == null)
        this.adapter = new NavigationMenuAdapter(); 
      this.headerLayout = (LinearLayout)this.layoutInflater.inflate(R.layout.design_navigation_item_header, (ViewGroup)this.menuView, false);
      this.menuView.setAdapter(this.adapter);
    } 
    return this.menuView;
  }
  
  public View inflateHeaderView(@LayoutRes int paramInt) {
    View view = this.layoutInflater.inflate(paramInt, (ViewGroup)this.headerLayout, false);
    addHeaderView(view);
    return view;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder) {
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.menu = paramMenuBuilder;
    this.paddingSeparator = paramContext.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {
    if (this.callback != null)
      this.callback.onCloseMenu(paramMenuBuilder, paramBoolean); 
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    if (paramParcelable instanceof Bundle) {
      Bundle bundle1 = (Bundle)paramParcelable;
      SparseArray sparseArray2 = bundle1.getSparseParcelableArray("android:menu:list");
      if (sparseArray2 != null)
        this.menuView.restoreHierarchyState(sparseArray2); 
      Bundle bundle2 = bundle1.getBundle("android:menu:adapter");
      if (bundle2 != null)
        this.adapter.restoreInstanceState(bundle2); 
      SparseArray sparseArray1 = bundle1.getSparseParcelableArray("android:menu:header");
      if (sparseArray1 != null)
        this.headerLayout.restoreHierarchyState(sparseArray1); 
    } 
  }
  
  public Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    if (this.menuView != null) {
      SparseArray sparseArray = new SparseArray();
      this.menuView.saveHierarchyState(sparseArray);
      bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    } 
    if (this.adapter != null)
      bundle.putBundle("android:menu:adapter", this.adapter.createInstanceState()); 
    if (this.headerLayout != null) {
      SparseArray sparseArray = new SparseArray();
      this.headerLayout.saveHierarchyState(sparseArray);
      bundle.putSparseParcelableArray("android:menu:header", sparseArray);
    } 
    return (Parcelable)bundle;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder) {
    return false;
  }
  
  public void removeHeaderView(@NonNull View paramView) {
    this.headerLayout.removeView(paramView);
    if (this.headerLayout.getChildCount() == 0)
      this.menuView.setPadding(0, this.paddingTopDefault, 0, this.menuView.getPaddingBottom()); 
  }
  
  public void setCallback(MenuPresenter.Callback paramCallback) {
    this.callback = paramCallback;
  }
  
  public void setCheckedItem(@NonNull MenuItemImpl paramMenuItemImpl) {
    this.adapter.setCheckedItem(paramMenuItemImpl);
  }
  
  public void setId(int paramInt) {
    this.id = paramInt;
  }
  
  public void setItemBackground(@Nullable Drawable paramDrawable) {
    this.itemBackground = paramDrawable;
    updateMenuView(false);
  }
  
  public void setItemHorizontalPadding(int paramInt) {
    this.itemHorizontalPadding = paramInt;
    updateMenuView(false);
  }
  
  public void setItemIconPadding(int paramInt) {
    this.itemIconPadding = paramInt;
    updateMenuView(false);
  }
  
  public void setItemIconTintList(@Nullable ColorStateList paramColorStateList) {
    this.iconTintList = paramColorStateList;
    updateMenuView(false);
  }
  
  public void setItemTextAppearance(@StyleRes int paramInt) {
    this.textAppearance = paramInt;
    this.textAppearanceSet = true;
    updateMenuView(false);
  }
  
  public void setItemTextColor(@Nullable ColorStateList paramColorStateList) {
    this.textColor = paramColorStateList;
    updateMenuView(false);
  }
  
  public void setUpdateSuspended(boolean paramBoolean) {
    if (this.adapter != null)
      this.adapter.setUpdateSuspended(paramBoolean); 
  }
  
  public void updateMenuView(boolean paramBoolean) {
    if (this.adapter != null)
      this.adapter.update(); 
  }
  
  private static class HeaderViewHolder extends ViewHolder {
    public HeaderViewHolder(View param1View) {
      super(param1View);
    }
  }
  
  private class NavigationMenuAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
    
    private static final String STATE_CHECKED_ITEM = "android:menu:checked";
    
    private static final int VIEW_TYPE_HEADER = 3;
    
    private static final int VIEW_TYPE_NORMAL = 0;
    
    private static final int VIEW_TYPE_SEPARATOR = 2;
    
    private static final int VIEW_TYPE_SUBHEADER = 1;
    
    private MenuItemImpl checkedItem;
    
    private final ArrayList<NavigationMenuPresenter.NavigationMenuItem> items = new ArrayList<>();
    
    private boolean updateSuspended;
    
    NavigationMenuAdapter() {
      prepareMenuItems();
    }
    
    private void appendTransparentIconIfMissing(int param1Int1, int param1Int2) {
      while (param1Int1 < param1Int2) {
        ((NavigationMenuPresenter.NavigationMenuTextItem)this.items.get(param1Int1)).needsEmptyIcon = true;
        param1Int1++;
      } 
    }
    
    private void prepareMenuItems() {
      if (this.updateSuspended)
        return; 
      this.updateSuspended = true;
      this.items.clear();
      this.items.add(new NavigationMenuPresenter.NavigationMenuHeaderItem());
      int i = NavigationMenuPresenter.this.menu.getVisibleItems().size();
      int j = -1;
      byte b1 = 0;
      byte b2 = b1;
      int k;
      for (k = b2; b1 < i; k = n) {
        int m;
        byte b;
        int n;
        MenuItemImpl menuItemImpl = NavigationMenuPresenter.this.menu.getVisibleItems().get(b1);
        if (menuItemImpl.isChecked())
          setCheckedItem(menuItemImpl); 
        if (menuItemImpl.isCheckable())
          menuItemImpl.setExclusiveCheckable(false); 
        if (menuItemImpl.hasSubMenu()) {
          SubMenu subMenu = menuItemImpl.getSubMenu();
          m = j;
          b = b2;
          n = k;
          if (subMenu.hasVisibleItems()) {
            if (b1 != 0)
              this.items.add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(NavigationMenuPresenter.this.paddingSeparator, 0)); 
            this.items.add(new NavigationMenuPresenter.NavigationMenuTextItem(menuItemImpl));
            int i1 = this.items.size();
            m = subMenu.size();
            b = 0;
            int i2;
            for (i2 = b; b < m; i2 = n) {
              MenuItemImpl menuItemImpl1 = (MenuItemImpl)subMenu.getItem(b);
              n = i2;
              if (menuItemImpl1.isVisible()) {
                n = i2;
                if (i2 == 0) {
                  n = i2;
                  if (menuItemImpl1.getIcon() != null)
                    n = 1; 
                } 
                if (menuItemImpl1.isCheckable())
                  menuItemImpl1.setExclusiveCheckable(false); 
                if (menuItemImpl.isChecked())
                  setCheckedItem(menuItemImpl); 
                this.items.add(new NavigationMenuPresenter.NavigationMenuTextItem(menuItemImpl1));
              } 
              b++;
            } 
            m = j;
            b = b2;
            n = k;
            if (i2 != 0) {
              appendTransparentIconIfMissing(i1, this.items.size());
              m = j;
              b = b2;
              n = k;
            } 
          } 
        } else {
          byte b3;
          m = menuItemImpl.getGroupId();
          if (m != j) {
            k = this.items.size();
            if (menuItemImpl.getIcon() != null) {
              b3 = 1;
            } else {
              b3 = 0;
            } 
            n = k;
            if (b1 != 0) {
              n = k + 1;
              this.items.add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(NavigationMenuPresenter.this.paddingSeparator, NavigationMenuPresenter.this.paddingSeparator));
            } 
          } else {
            b3 = b2;
            n = k;
            if (b2 == 0) {
              b3 = b2;
              n = k;
              if (menuItemImpl.getIcon() != null) {
                appendTransparentIconIfMissing(k, this.items.size());
                b3 = 1;
                n = k;
              } 
            } 
          } 
          NavigationMenuPresenter.NavigationMenuTextItem navigationMenuTextItem = new NavigationMenuPresenter.NavigationMenuTextItem(menuItemImpl);
          navigationMenuTextItem.needsEmptyIcon = b3;
          this.items.add(navigationMenuTextItem);
          b = b3;
        } 
        b1++;
        j = m;
        b2 = b;
      } 
      this.updateSuspended = false;
    }
    
    public Bundle createInstanceState() {
      Bundle bundle = new Bundle();
      if (this.checkedItem != null)
        bundle.putInt("android:menu:checked", this.checkedItem.getItemId()); 
      SparseArray sparseArray = new SparseArray();
      byte b = 0;
      int i = this.items.size();
      while (b < i) {
        NavigationMenuPresenter.NavigationMenuItem navigationMenuItem = this.items.get(b);
        if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem) {
          MenuItemImpl menuItemImpl = ((NavigationMenuPresenter.NavigationMenuTextItem)navigationMenuItem).getMenuItem();
          if (menuItemImpl != null) {
            View view = menuItemImpl.getActionView();
          } else {
            navigationMenuItem = null;
          } 
          if (navigationMenuItem != null) {
            ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
            navigationMenuItem.saveHierarchyState(parcelableSparseArray);
            sparseArray.put(menuItemImpl.getItemId(), parcelableSparseArray);
          } 
        } 
        b++;
      } 
      bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
      return bundle;
    }
    
    public MenuItemImpl getCheckedItem() {
      return this.checkedItem;
    }
    
    public int getItemCount() {
      return this.items.size();
    }
    
    public long getItemId(int param1Int) {
      return param1Int;
    }
    
    public int getItemViewType(int param1Int) {
      NavigationMenuPresenter.NavigationMenuItem navigationMenuItem = this.items.get(param1Int);
      if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuSeparatorItem)
        return 2; 
      if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuHeaderItem)
        return 3; 
      if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem)
        return ((NavigationMenuPresenter.NavigationMenuTextItem)navigationMenuItem).getMenuItem().hasSubMenu() ? 1 : 0; 
      throw new RuntimeException("Unknown item type.");
    }
    
    public void onBindViewHolder(NavigationMenuPresenter.ViewHolder param1ViewHolder, int param1Int) {
      NavigationMenuPresenter.NavigationMenuSeparatorItem navigationMenuSeparatorItem;
      switch (getItemViewType(param1Int)) {
        default:
          return;
        case 2:
          navigationMenuSeparatorItem = (NavigationMenuPresenter.NavigationMenuSeparatorItem)this.items.get(param1Int);
          param1ViewHolder.itemView.setPadding(0, navigationMenuSeparatorItem.getPaddingTop(), 0, navigationMenuSeparatorItem.getPaddingBottom());
        case 1:
          ((TextView)param1ViewHolder.itemView).setText(((NavigationMenuPresenter.NavigationMenuTextItem)this.items.get(param1Int)).getMenuItem().getTitle());
        case 0:
          break;
      } 
      NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView)param1ViewHolder.itemView;
      navigationMenuItemView.setIconTintList(NavigationMenuPresenter.this.iconTintList);
      if (NavigationMenuPresenter.this.textAppearanceSet)
        navigationMenuItemView.setTextAppearance(NavigationMenuPresenter.this.textAppearance); 
      if (NavigationMenuPresenter.this.textColor != null)
        navigationMenuItemView.setTextColor(NavigationMenuPresenter.this.textColor); 
      if (NavigationMenuPresenter.this.itemBackground != null) {
        Drawable drawable = NavigationMenuPresenter.this.itemBackground.getConstantState().newDrawable();
      } else {
        param1ViewHolder = null;
      } 
      ViewCompat.setBackground((View)navigationMenuItemView, (Drawable)param1ViewHolder);
      NavigationMenuPresenter.NavigationMenuTextItem navigationMenuTextItem = (NavigationMenuPresenter.NavigationMenuTextItem)this.items.get(param1Int);
      navigationMenuItemView.setNeedsEmptyIcon(navigationMenuTextItem.needsEmptyIcon);
      navigationMenuItemView.setHorizontalPadding(NavigationMenuPresenter.this.itemHorizontalPadding);
      navigationMenuItemView.setIconPadding(NavigationMenuPresenter.this.itemIconPadding);
      navigationMenuItemView.initialize(navigationMenuTextItem.getMenuItem(), 0);
    }
    
    public NavigationMenuPresenter.ViewHolder onCreateViewHolder(ViewGroup param1ViewGroup, int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 3:
          return new NavigationMenuPresenter.HeaderViewHolder((View)NavigationMenuPresenter.this.headerLayout);
        case 2:
          return new NavigationMenuPresenter.SeparatorViewHolder(NavigationMenuPresenter.this.layoutInflater, param1ViewGroup);
        case 1:
          return new NavigationMenuPresenter.SubheaderViewHolder(NavigationMenuPresenter.this.layoutInflater, param1ViewGroup);
        case 0:
          break;
      } 
      return new NavigationMenuPresenter.NormalViewHolder(NavigationMenuPresenter.this.layoutInflater, param1ViewGroup, NavigationMenuPresenter.this.onClickListener);
    }
    
    public void onViewRecycled(NavigationMenuPresenter.ViewHolder param1ViewHolder) {
      if (param1ViewHolder instanceof NavigationMenuPresenter.NormalViewHolder)
        ((NavigationMenuItemView)param1ViewHolder.itemView).recycle(); 
    }
    
    public void restoreInstanceState(Bundle param1Bundle) {
      byte b = 0;
      int i = param1Bundle.getInt("android:menu:checked", 0);
      if (i != 0) {
        this.updateSuspended = true;
        int j = this.items.size();
        for (byte b1 = 0; b1 < j; b1++) {
          NavigationMenuPresenter.NavigationMenuItem navigationMenuItem = this.items.get(b1);
          if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem) {
            MenuItemImpl menuItemImpl = ((NavigationMenuPresenter.NavigationMenuTextItem)navigationMenuItem).getMenuItem();
            if (menuItemImpl != null && menuItemImpl.getItemId() == i) {
              setCheckedItem(menuItemImpl);
              break;
            } 
          } 
        } 
        this.updateSuspended = false;
        prepareMenuItems();
      } 
      SparseArray sparseArray = param1Bundle.getSparseParcelableArray("android:menu:action_views");
      if (sparseArray != null) {
        i = this.items.size();
        for (byte b1 = b; b1 < i; b1++) {
          NavigationMenuPresenter.NavigationMenuItem navigationMenuItem = this.items.get(b1);
          if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem) {
            MenuItemImpl menuItemImpl = ((NavigationMenuPresenter.NavigationMenuTextItem)navigationMenuItem).getMenuItem();
            if (menuItemImpl != null) {
              View view = menuItemImpl.getActionView();
              if (view != null) {
                ParcelableSparseArray parcelableSparseArray = (ParcelableSparseArray)sparseArray.get(menuItemImpl.getItemId());
                if (parcelableSparseArray != null)
                  view.restoreHierarchyState(parcelableSparseArray); 
              } 
            } 
          } 
        } 
      } 
    }
    
    public void setCheckedItem(MenuItemImpl param1MenuItemImpl) {
      if (this.checkedItem == param1MenuItemImpl || !param1MenuItemImpl.isCheckable())
        return; 
      if (this.checkedItem != null)
        this.checkedItem.setChecked(false); 
      this.checkedItem = param1MenuItemImpl;
      param1MenuItemImpl.setChecked(true);
    }
    
    public void setUpdateSuspended(boolean param1Boolean) {
      this.updateSuspended = param1Boolean;
    }
    
    public void update() {
      prepareMenuItems();
      notifyDataSetChanged();
    }
  }
  
  private static class NavigationMenuHeaderItem implements NavigationMenuItem {}
  
  private static interface NavigationMenuItem {}
  
  private static class NavigationMenuSeparatorItem implements NavigationMenuItem {
    private final int paddingBottom;
    
    private final int paddingTop;
    
    public NavigationMenuSeparatorItem(int param1Int1, int param1Int2) {
      this.paddingTop = param1Int1;
      this.paddingBottom = param1Int2;
    }
    
    public int getPaddingBottom() {
      return this.paddingBottom;
    }
    
    public int getPaddingTop() {
      return this.paddingTop;
    }
  }
  
  private static class NavigationMenuTextItem implements NavigationMenuItem {
    private final MenuItemImpl menuItem;
    
    boolean needsEmptyIcon;
    
    NavigationMenuTextItem(MenuItemImpl param1MenuItemImpl) {
      this.menuItem = param1MenuItemImpl;
    }
    
    public MenuItemImpl getMenuItem() {
      return this.menuItem;
    }
  }
  
  private static class NormalViewHolder extends ViewHolder {
    public NormalViewHolder(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, View.OnClickListener param1OnClickListener) {
      super(param1LayoutInflater.inflate(R.layout.design_navigation_item, param1ViewGroup, false));
      this.itemView.setOnClickListener(param1OnClickListener);
    }
  }
  
  private static class SeparatorViewHolder extends ViewHolder {
    public SeparatorViewHolder(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup) {
      super(param1LayoutInflater.inflate(R.layout.design_navigation_item_separator, param1ViewGroup, false));
    }
  }
  
  private static class SubheaderViewHolder extends ViewHolder {
    public SubheaderViewHolder(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup) {
      super(param1LayoutInflater.inflate(R.layout.design_navigation_item_subheader, param1ViewGroup, false));
    }
  }
  
  private static abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View param1View) {
      super(param1View);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\internal\NavigationMenuPresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */