package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
  private static final String TAG = "ActionMenuPresenter";
  
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  
  ActionButtonSubmenu mActionButtonPopup;
  
  private int mActionItemWidthLimit;
  
  private boolean mExpandedActionViewsExclusive;
  
  private int mMaxItems;
  
  private boolean mMaxItemsSet;
  
  private int mMinCellSize;
  
  int mOpenSubMenuId;
  
  OverflowMenuButton mOverflowButton;
  
  OverflowPopup mOverflowPopup;
  
  private Drawable mPendingOverflowIcon;
  
  private boolean mPendingOverflowIconSet;
  
  private ActionMenuPopupCallback mPopupCallback;
  
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
  
  OpenOverflowRunnable mPostedOpenRunnable;
  
  private boolean mReserveOverflow;
  
  private boolean mReserveOverflowSet;
  
  private View mScrapActionButtonView;
  
  private boolean mStrictWidthLimit;
  
  private int mWidthLimit;
  
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext) {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem) {
    ViewGroup viewGroup = (ViewGroup)this.mMenuView;
    if (viewGroup == null)
      return null; 
    int i = viewGroup.getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = viewGroup.getChildAt(b);
      if (view instanceof MenuView.ItemView && ((MenuView.ItemView)view).getItemData() == paramMenuItem)
        return view; 
    } 
    return null;
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView) {
    paramItemView.initialize(paramMenuItemImpl, 0);
    ActionMenuView actionMenuView = (ActionMenuView)this.mMenuView;
    ActionMenuItemView actionMenuItemView = (ActionMenuItemView)paramItemView;
    actionMenuItemView.setItemInvoker(actionMenuView);
    if (this.mPopupCallback == null)
      this.mPopupCallback = new ActionMenuPopupCallback(); 
    actionMenuItemView.setPopupCallback(this.mPopupCallback);
  }
  
  public boolean dismissPopupMenus() {
    boolean bool = hideOverflowMenu();
    return hideSubMenus() | bool;
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt) {
    return (paramViewGroup.getChildAt(paramInt) == this.mOverflowButton) ? false : super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems() {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: aload_1
    //   3: getfield mMenu : Landroid/support/v7/view/menu/MenuBuilder;
    //   6: astore_2
    //   7: iconst_0
    //   8: istore_3
    //   9: aload_2
    //   10: ifnull -> 30
    //   13: aload_1
    //   14: getfield mMenu : Landroid/support/v7/view/menu/MenuBuilder;
    //   17: invokevirtual getVisibleItems : ()Ljava/util/ArrayList;
    //   20: astore_2
    //   21: aload_2
    //   22: invokevirtual size : ()I
    //   25: istore #4
    //   27: goto -> 35
    //   30: aconst_null
    //   31: astore_2
    //   32: iconst_0
    //   33: istore #4
    //   35: aload_1
    //   36: getfield mMaxItems : I
    //   39: istore #5
    //   41: aload_1
    //   42: getfield mActionItemWidthLimit : I
    //   45: istore #6
    //   47: iconst_0
    //   48: iconst_0
    //   49: invokestatic makeMeasureSpec : (II)I
    //   52: istore #7
    //   54: aload_1
    //   55: getfield mMenuView : Landroid/support/v7/view/menu/MenuView;
    //   58: checkcast android/view/ViewGroup
    //   61: astore #8
    //   63: iconst_0
    //   64: istore #9
    //   66: iload #9
    //   68: istore #10
    //   70: iload #10
    //   72: istore #11
    //   74: iload #11
    //   76: istore #12
    //   78: iload #10
    //   80: istore #13
    //   82: iload #9
    //   84: istore #10
    //   86: iload #12
    //   88: iload #4
    //   90: if_icmpge -> 171
    //   93: aload_2
    //   94: iload #12
    //   96: invokevirtual get : (I)Ljava/lang/Object;
    //   99: checkcast android/support/v7/view/menu/MenuItemImpl
    //   102: astore #14
    //   104: aload #14
    //   106: invokevirtual requiresActionButton : ()Z
    //   109: ifeq -> 118
    //   112: iinc #10, 1
    //   115: goto -> 135
    //   118: aload #14
    //   120: invokevirtual requestsActionButton : ()Z
    //   123: ifeq -> 132
    //   126: iinc #11, 1
    //   129: goto -> 135
    //   132: iconst_1
    //   133: istore #13
    //   135: iload #5
    //   137: istore #9
    //   139: aload_1
    //   140: getfield mExpandedActionViewsExclusive : Z
    //   143: ifeq -> 161
    //   146: iload #5
    //   148: istore #9
    //   150: aload #14
    //   152: invokevirtual isActionViewExpanded : ()Z
    //   155: ifeq -> 161
    //   158: iconst_0
    //   159: istore #9
    //   161: iinc #12, 1
    //   164: iload #9
    //   166: istore #5
    //   168: goto -> 86
    //   171: iload #5
    //   173: istore #12
    //   175: aload_1
    //   176: getfield mReserveOverflow : Z
    //   179: ifeq -> 207
    //   182: iload #13
    //   184: ifne -> 201
    //   187: iload #5
    //   189: istore #12
    //   191: iload #11
    //   193: iload #10
    //   195: iadd
    //   196: iload #5
    //   198: if_icmple -> 207
    //   201: iload #5
    //   203: iconst_1
    //   204: isub
    //   205: istore #12
    //   207: iload #12
    //   209: iload #10
    //   211: isub
    //   212: istore #11
    //   214: aload_1
    //   215: getfield mActionButtonGroups : Landroid/util/SparseBooleanArray;
    //   218: astore #14
    //   220: aload #14
    //   222: invokevirtual clear : ()V
    //   225: aload_1
    //   226: getfield mStrictWidthLimit : Z
    //   229: ifeq -> 269
    //   232: iload #6
    //   234: aload_1
    //   235: getfield mMinCellSize : I
    //   238: idiv
    //   239: istore #10
    //   241: aload_1
    //   242: getfield mMinCellSize : I
    //   245: istore #13
    //   247: aload_1
    //   248: getfield mMinCellSize : I
    //   251: istore #5
    //   253: iload #6
    //   255: iload #13
    //   257: irem
    //   258: iload #10
    //   260: idiv
    //   261: iload #5
    //   263: iadd
    //   264: istore #9
    //   266: goto -> 276
    //   269: iconst_0
    //   270: istore #10
    //   272: iload #10
    //   274: istore #9
    //   276: iconst_0
    //   277: istore #5
    //   279: iload #6
    //   281: istore #13
    //   283: iload #5
    //   285: istore #15
    //   287: iload #4
    //   289: istore #6
    //   291: iload_3
    //   292: istore #4
    //   294: aload_0
    //   295: astore_1
    //   296: iload #15
    //   298: iload #6
    //   300: if_icmpge -> 849
    //   303: aload_2
    //   304: iload #15
    //   306: invokevirtual get : (I)Ljava/lang/Object;
    //   309: checkcast android/support/v7/view/menu/MenuItemImpl
    //   312: astore #16
    //   314: aload #16
    //   316: invokevirtual requiresActionButton : ()Z
    //   319: ifeq -> 446
    //   322: aload_1
    //   323: aload #16
    //   325: aload_1
    //   326: getfield mScrapActionButtonView : Landroid/view/View;
    //   329: aload #8
    //   331: invokevirtual getItemView : (Landroid/support/v7/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    //   334: astore #17
    //   336: aload_1
    //   337: getfield mScrapActionButtonView : Landroid/view/View;
    //   340: ifnonnull -> 349
    //   343: aload_1
    //   344: aload #17
    //   346: putfield mScrapActionButtonView : Landroid/view/View;
    //   349: aload_1
    //   350: getfield mStrictWidthLimit : Z
    //   353: ifeq -> 377
    //   356: iload #10
    //   358: aload #17
    //   360: iload #9
    //   362: iload #10
    //   364: iload #7
    //   366: iload #4
    //   368: invokestatic measureChildForCells : (Landroid/view/View;IIII)I
    //   371: isub
    //   372: istore #10
    //   374: goto -> 386
    //   377: aload #17
    //   379: iload #7
    //   381: iload #7
    //   383: invokevirtual measure : (II)V
    //   386: aload #17
    //   388: invokevirtual getMeasuredWidth : ()I
    //   391: istore_3
    //   392: iload #13
    //   394: iload_3
    //   395: isub
    //   396: istore #13
    //   398: iload #5
    //   400: istore #12
    //   402: iload #5
    //   404: ifne -> 410
    //   407: iload_3
    //   408: istore #12
    //   410: aload #16
    //   412: invokevirtual getGroupId : ()I
    //   415: istore #5
    //   417: iload #5
    //   419: ifeq -> 433
    //   422: aload #14
    //   424: iload #5
    //   426: iconst_1
    //   427: invokevirtual put : (IZ)V
    //   430: goto -> 433
    //   433: aload #16
    //   435: iconst_1
    //   436: invokevirtual setIsActionButton : (Z)V
    //   439: iload #4
    //   441: istore #5
    //   443: goto -> 835
    //   446: aload #16
    //   448: invokevirtual requestsActionButton : ()Z
    //   451: ifeq -> 820
    //   454: aload #16
    //   456: invokevirtual getGroupId : ()I
    //   459: istore_3
    //   460: aload #14
    //   462: iload_3
    //   463: invokevirtual get : (I)Z
    //   466: istore #18
    //   468: iload #11
    //   470: ifgt -> 478
    //   473: iload #18
    //   475: ifeq -> 501
    //   478: iload #13
    //   480: ifle -> 501
    //   483: aload_1
    //   484: getfield mStrictWidthLimit : Z
    //   487: ifeq -> 495
    //   490: iload #10
    //   492: ifle -> 501
    //   495: iconst_1
    //   496: istore #19
    //   498: goto -> 504
    //   501: iconst_0
    //   502: istore #19
    //   504: iload #19
    //   506: ifeq -> 677
    //   509: aload_1
    //   510: aload #16
    //   512: aload_1
    //   513: getfield mScrapActionButtonView : Landroid/view/View;
    //   516: aload #8
    //   518: invokevirtual getItemView : (Landroid/support/v7/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    //   521: astore #17
    //   523: aload_1
    //   524: getfield mScrapActionButtonView : Landroid/view/View;
    //   527: ifnonnull -> 536
    //   530: aload_1
    //   531: aload #17
    //   533: putfield mScrapActionButtonView : Landroid/view/View;
    //   536: aload_1
    //   537: getfield mStrictWidthLimit : Z
    //   540: ifeq -> 583
    //   543: aload #17
    //   545: iload #9
    //   547: iload #10
    //   549: iload #7
    //   551: iconst_0
    //   552: invokestatic measureChildForCells : (Landroid/view/View;IIII)I
    //   555: istore #12
    //   557: iload #10
    //   559: iload #12
    //   561: isub
    //   562: istore #4
    //   564: iload #4
    //   566: istore #10
    //   568: iload #12
    //   570: ifne -> 592
    //   573: iconst_0
    //   574: istore #19
    //   576: iload #4
    //   578: istore #10
    //   580: goto -> 592
    //   583: aload #17
    //   585: iload #7
    //   587: iload #7
    //   589: invokevirtual measure : (II)V
    //   592: aload #17
    //   594: invokevirtual getMeasuredWidth : ()I
    //   597: istore #12
    //   599: iload #13
    //   601: iload #12
    //   603: isub
    //   604: istore #13
    //   606: iload #5
    //   608: istore #4
    //   610: iload #5
    //   612: ifne -> 619
    //   615: iload #12
    //   617: istore #4
    //   619: aload_1
    //   620: getfield mStrictWidthLimit : Z
    //   623: ifeq -> 650
    //   626: iload #13
    //   628: iflt -> 637
    //   631: iconst_1
    //   632: istore #5
    //   634: goto -> 640
    //   637: iconst_0
    //   638: istore #5
    //   640: iload #19
    //   642: iload #5
    //   644: iand
    //   645: istore #19
    //   647: goto -> 681
    //   650: iload #13
    //   652: iload #4
    //   654: iadd
    //   655: ifle -> 664
    //   658: iconst_1
    //   659: istore #5
    //   661: goto -> 667
    //   664: iconst_0
    //   665: istore #5
    //   667: iload #19
    //   669: iload #5
    //   671: iand
    //   672: istore #19
    //   674: goto -> 681
    //   677: iload #5
    //   679: istore #4
    //   681: iload #19
    //   683: ifeq -> 704
    //   686: iload_3
    //   687: ifeq -> 704
    //   690: aload #14
    //   692: iload_3
    //   693: iconst_1
    //   694: invokevirtual put : (IZ)V
    //   697: iload #11
    //   699: istore #5
    //   701: goto -> 788
    //   704: iload #11
    //   706: istore #5
    //   708: iload #18
    //   710: ifeq -> 788
    //   713: aload #14
    //   715: iload_3
    //   716: iconst_0
    //   717: invokevirtual put : (IZ)V
    //   720: iconst_0
    //   721: istore #12
    //   723: iload #11
    //   725: istore #5
    //   727: iload #12
    //   729: iload #15
    //   731: if_icmpge -> 788
    //   734: aload_2
    //   735: iload #12
    //   737: invokevirtual get : (I)Ljava/lang/Object;
    //   740: checkcast android/support/v7/view/menu/MenuItemImpl
    //   743: astore_1
    //   744: iload #11
    //   746: istore #5
    //   748: aload_1
    //   749: invokevirtual getGroupId : ()I
    //   752: iload_3
    //   753: if_icmpne -> 778
    //   756: iload #11
    //   758: istore #5
    //   760: aload_1
    //   761: invokevirtual isActionButton : ()Z
    //   764: ifeq -> 773
    //   767: iload #11
    //   769: iconst_1
    //   770: iadd
    //   771: istore #5
    //   773: aload_1
    //   774: iconst_0
    //   775: invokevirtual setIsActionButton : (Z)V
    //   778: iinc #12, 1
    //   781: iload #5
    //   783: istore #11
    //   785: goto -> 723
    //   788: iload #5
    //   790: istore #11
    //   792: iload #19
    //   794: ifeq -> 803
    //   797: iload #5
    //   799: iconst_1
    //   800: isub
    //   801: istore #11
    //   803: aload #16
    //   805: iload #19
    //   807: invokevirtual setIsActionButton : (Z)V
    //   810: iconst_0
    //   811: istore #5
    //   813: iload #4
    //   815: istore #12
    //   817: goto -> 835
    //   820: aload #16
    //   822: iload #4
    //   824: invokevirtual setIsActionButton : (Z)V
    //   827: iload #5
    //   829: istore #12
    //   831: iload #4
    //   833: istore #5
    //   835: iinc #15, 1
    //   838: iload #5
    //   840: istore #4
    //   842: iload #12
    //   844: istore #5
    //   846: goto -> 294
    //   849: iconst_1
    //   850: ireturn
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup) {
    boolean bool;
    View view = paramMenuItemImpl.getActionView();
    if (view == null || paramMenuItemImpl.hasCollapsibleActionView())
      view = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup); 
    if (paramMenuItemImpl.isActionViewExpanded()) {
      bool = true;
    } else {
      bool = false;
    } 
    view.setVisibility(bool);
    ActionMenuView actionMenuView = (ActionMenuView)paramViewGroup;
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    if (!actionMenuView.checkLayoutParams(layoutParams))
      view.setLayoutParams((ViewGroup.LayoutParams)actionMenuView.generateLayoutParams(layoutParams)); 
    return view;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup) {
    MenuView menuView2 = this.mMenuView;
    MenuView menuView1 = super.getMenuView(paramViewGroup);
    if (menuView2 != menuView1)
      ((ActionMenuView)menuView1).setPresenter(this); 
    return menuView1;
  }
  
  public Drawable getOverflowIcon() {
    return (this.mOverflowButton != null) ? this.mOverflowButton.getDrawable() : (this.mPendingOverflowIconSet ? this.mPendingOverflowIcon : null);
  }
  
  public boolean hideOverflowMenu() {
    if (this.mPostedOpenRunnable != null && this.mMenuView != null) {
      ((View)this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
      this.mPostedOpenRunnable = null;
      return true;
    } 
    OverflowPopup overflowPopup = this.mOverflowPopup;
    if (overflowPopup != null) {
      overflowPopup.dismiss();
      return true;
    } 
    return false;
  }
  
  public boolean hideSubMenus() {
    if (this.mActionButtonPopup != null) {
      this.mActionButtonPopup.dismiss();
      return true;
    } 
    return false;
  }
  
  public void initForMenu(@NonNull Context paramContext, @Nullable MenuBuilder paramMenuBuilder) {
    super.initForMenu(paramContext, paramMenuBuilder);
    Resources resources = paramContext.getResources();
    ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(paramContext);
    if (!this.mReserveOverflowSet)
      this.mReserveOverflow = actionBarPolicy.showsOverflowMenuButton(); 
    if (!this.mWidthLimitSet)
      this.mWidthLimit = actionBarPolicy.getEmbeddedMenuWidthLimit(); 
    if (!this.mMaxItemsSet)
      this.mMaxItems = actionBarPolicy.getMaxActionButtons(); 
    int i = this.mWidthLimit;
    if (this.mReserveOverflow) {
      if (this.mOverflowButton == null) {
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
        if (this.mPendingOverflowIconSet) {
          this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
          this.mPendingOverflowIcon = null;
          this.mPendingOverflowIconSet = false;
        } 
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mOverflowButton.measure(j, j);
      } 
      i -= this.mOverflowButton.getMeasuredWidth();
    } else {
      this.mOverflowButton = null;
    } 
    this.mActionItemWidthLimit = i;
    this.mMinCellSize = (int)((resources.getDisplayMetrics()).density * 56.0F);
    this.mScrapActionButtonView = null;
  }
  
  public boolean isOverflowMenuShowPending() {
    return (this.mPostedOpenRunnable != null || isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing() {
    boolean bool;
    if (this.mOverflowPopup != null && this.mOverflowPopup.isShowing()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOverflowReserved() {
    return this.mReserveOverflow;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    if (!this.mMaxItemsSet)
      this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons(); 
    if (this.mMenu != null)
      this.mMenu.onItemsChanged(true); 
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState))
      return; 
    paramParcelable = paramParcelable;
    if (((SavedState)paramParcelable).openSubMenuId > 0) {
      MenuItem menuItem = this.mMenu.findItem(((SavedState)paramParcelable).openSubMenuId);
      if (menuItem != null)
        onSubMenuSelected((SubMenuBuilder)menuItem.getSubMenu()); 
    } 
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState();
    savedState.openSubMenuId = this.mOpenSubMenuId;
    return savedState;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder) {
    boolean bool = paramSubMenuBuilder.hasVisibleItems();
    boolean bool1 = false;
    if (!bool)
      return false; 
    SubMenuBuilder subMenuBuilder;
    for (subMenuBuilder = paramSubMenuBuilder; subMenuBuilder.getParentMenu() != this.mMenu; subMenuBuilder = (SubMenuBuilder)subMenuBuilder.getParentMenu());
    View view = findViewForItem(subMenuBuilder.getItem());
    if (view == null)
      return false; 
    this.mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
    int i = paramSubMenuBuilder.size();
    byte b = 0;
    while (true) {
      bool = bool1;
      if (b < i) {
        MenuItem menuItem = paramSubMenuBuilder.getItem(b);
        if (menuItem.isVisible() && menuItem.getIcon() != null) {
          bool = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    this.mActionButtonPopup = new ActionButtonSubmenu(this.mContext, paramSubMenuBuilder, view);
    this.mActionButtonPopup.setForceShowIcon(bool);
    this.mActionButtonPopup.show();
    super.onSubMenuSelected(paramSubMenuBuilder);
    return true;
  }
  
  public void onSubUiVisibilityChanged(boolean paramBoolean) {
    if (paramBoolean) {
      super.onSubMenuSelected(null);
    } else if (this.mMenu != null) {
      this.mMenu.close(false);
    } 
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean) {
    this.mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setItemLimit(int paramInt) {
    this.mMaxItems = paramInt;
    this.mMaxItemsSet = true;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView) {
    this.mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(this.mMenu);
  }
  
  public void setOverflowIcon(Drawable paramDrawable) {
    if (this.mOverflowButton != null) {
      this.mOverflowButton.setImageDrawable(paramDrawable);
    } else {
      this.mPendingOverflowIconSet = true;
      this.mPendingOverflowIcon = paramDrawable;
    } 
  }
  
  public void setReserveOverflow(boolean paramBoolean) {
    this.mReserveOverflow = paramBoolean;
    this.mReserveOverflowSet = true;
  }
  
  public void setWidthLimit(int paramInt, boolean paramBoolean) {
    this.mWidthLimit = paramInt;
    this.mStrictWidthLimit = paramBoolean;
    this.mWidthLimitSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl) {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu() {
    if (this.mReserveOverflow && !isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.getNonActionItems().isEmpty()) {
      this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, (View)this.mOverflowButton, true));
      ((View)this.mMenuView).post(this.mPostedOpenRunnable);
      super.onSubMenuSelected(null);
      return true;
    } 
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean) {
    super.updateMenuView(paramBoolean);
    ((View)this.mMenuView).requestLayout();
    MenuBuilder<MenuItemImpl> menuBuilder = this.mMenu;
    byte b = 0;
    if (menuBuilder != null) {
      ArrayList<MenuItemImpl> arrayList = this.mMenu.getActionItems();
      int j = arrayList.size();
      for (byte b1 = 0; b1 < j; b1++) {
        ActionProvider actionProvider = ((MenuItemImpl)arrayList.get(b1)).getSupportActionProvider();
        if (actionProvider != null)
          actionProvider.setSubUiVisibilityListener(this); 
      } 
    } 
    if (this.mMenu != null) {
      ArrayList arrayList = this.mMenu.getNonActionItems();
    } else {
      menuBuilder = null;
    } 
    int i = b;
    if (this.mReserveOverflow) {
      i = b;
      if (menuBuilder != null) {
        int j = menuBuilder.size();
        if (j == 1) {
          i = ((MenuItemImpl)menuBuilder.get(0)).isActionViewExpanded() ^ true;
        } else {
          i = b;
          if (j > 0)
            i = 1; 
        } 
      } 
    } 
    if (i != 0) {
      if (this.mOverflowButton == null)
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext); 
      ViewGroup viewGroup = (ViewGroup)this.mOverflowButton.getParent();
      if (viewGroup != this.mMenuView) {
        if (viewGroup != null)
          viewGroup.removeView((View)this.mOverflowButton); 
        viewGroup = (ActionMenuView)this.mMenuView;
        viewGroup.addView((View)this.mOverflowButton, (ViewGroup.LayoutParams)viewGroup.generateOverflowButtonLayoutParams());
      } 
    } else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
      ((ViewGroup)this.mMenuView).removeView((View)this.mOverflowButton);
    } 
    ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
  }
  
  private class ActionButtonSubmenu extends MenuPopupHelper {
    public ActionButtonSubmenu(Context param1Context, SubMenuBuilder param1SubMenuBuilder, View param1View) {
      super(param1Context, (MenuBuilder)param1SubMenuBuilder, param1View, false, R.attr.actionOverflowMenuStyle);
      if (!((MenuItemImpl)param1SubMenuBuilder.getItem()).isActionButton()) {
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton;
        if (ActionMenuPresenter.this.mOverflowButton == null) {
          View view = (View)ActionMenuPresenter.this.mMenuView;
        } else {
          overflowMenuButton = ActionMenuPresenter.this.mOverflowButton;
        } 
        setAnchorView((View)overflowMenuButton);
      } 
      setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    protected void onDismiss() {
      ActionMenuPresenter.this.mActionButtonPopup = null;
      ActionMenuPresenter.this.mOpenSubMenuId = 0;
      super.onDismiss();
    }
  }
  
  private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
    public ShowableListMenu getPopup() {
      ShowableListMenu showableListMenu;
      if (ActionMenuPresenter.this.mActionButtonPopup != null) {
        showableListMenu = (ShowableListMenu)ActionMenuPresenter.this.mActionButtonPopup.getPopup();
      } else {
        showableListMenu = null;
      } 
      return showableListMenu;
    }
  }
  
  private class OpenOverflowRunnable implements Runnable {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup param1OverflowPopup) {
      this.mPopup = param1OverflowPopup;
    }
    
    public void run() {
      if (ActionMenuPresenter.this.mMenu != null)
        ActionMenuPresenter.this.mMenu.changeMenuMode(); 
      View view = (View)ActionMenuPresenter.this.mMenuView;
      if (view != null && view.getWindowToken() != null && this.mPopup.tryShow())
        ActionMenuPresenter.this.mOverflowPopup = this.mPopup; 
      ActionMenuPresenter.this.mPostedOpenRunnable = null;
    }
  }
  
  private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
    private final float[] mTempPts = new float[2];
    
    public OverflowMenuButton(Context param1Context) {
      super(param1Context, (AttributeSet)null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      TooltipCompat.setTooltipText((View)this, getContentDescription());
      setOnTouchListener(new ForwardingListener((View)this) {
            public ShowableListMenu getPopup() {
              return (ShowableListMenu)((ActionMenuPresenter.this.mOverflowPopup == null) ? null : ActionMenuPresenter.this.mOverflowPopup.getPopup());
            }
            
            public boolean onForwardingStarted() {
              ActionMenuPresenter.this.showOverflowMenu();
              return true;
            }
            
            public boolean onForwardingStopped() {
              if (ActionMenuPresenter.this.mPostedOpenRunnable != null)
                return false; 
              ActionMenuPresenter.this.hideOverflowMenu();
              return true;
            }
          });
    }
    
    public boolean needsDividerAfter() {
      return false;
    }
    
    public boolean needsDividerBefore() {
      return false;
    }
    
    public boolean performClick() {
      if (super.performClick())
        return true; 
      playSoundEffect(0);
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      boolean bool = super.setFrame(param1Int1, param1Int2, param1Int3, param1Int4);
      Drawable drawable1 = getDrawable();
      Drawable drawable2 = getBackground();
      if (drawable1 != null && drawable2 != null) {
        int i = getWidth();
        param1Int2 = getHeight();
        param1Int1 = Math.max(i, param1Int2) / 2;
        int j = getPaddingLeft();
        int k = getPaddingRight();
        param1Int4 = getPaddingTop();
        param1Int3 = getPaddingBottom();
        j = (i + j - k) / 2;
        param1Int2 = (param1Int2 + param1Int4 - param1Int3) / 2;
        DrawableCompat.setHotspotBounds(drawable2, j - param1Int1, param1Int2 - param1Int1, j + param1Int1, param1Int2 + param1Int1);
      } 
      return bool;
    }
  }
  
  class null extends ForwardingListener {
    null(View param1View) {
      super(param1View);
    }
    
    public ShowableListMenu getPopup() {
      return (ShowableListMenu)((ActionMenuPresenter.this.mOverflowPopup == null) ? null : ActionMenuPresenter.this.mOverflowPopup.getPopup());
    }
    
    public boolean onForwardingStarted() {
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    public boolean onForwardingStopped() {
      if (ActionMenuPresenter.this.mPostedOpenRunnable != null)
        return false; 
      ActionMenuPresenter.this.hideOverflowMenu();
      return true;
    }
  }
  
  private class OverflowPopup extends MenuPopupHelper {
    public OverflowPopup(Context param1Context, MenuBuilder param1MenuBuilder, View param1View, boolean param1Boolean) {
      super(param1Context, param1MenuBuilder, param1View, param1Boolean, R.attr.actionOverflowMenuStyle);
      setGravity(8388613);
      setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    protected void onDismiss() {
      if (ActionMenuPresenter.this.mMenu != null)
        ActionMenuPresenter.this.mMenu.close(); 
      ActionMenuPresenter.this.mOverflowPopup = null;
      super.onDismiss();
    }
  }
  
  private class PopupPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      if (param1MenuBuilder instanceof SubMenuBuilder)
        param1MenuBuilder.getRootMenu().close(false); 
      MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
      if (callback != null)
        callback.onCloseMenu(param1MenuBuilder, param1Boolean); 
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      boolean bool = false;
      if (param1MenuBuilder == null)
        return false; 
      ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)param1MenuBuilder).getItem().getItemId();
      MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
      if (callback != null)
        bool = callback.onOpenSubMenu(param1MenuBuilder); 
      return bool;
    }
  }
  
  private static class SavedState implements Parcelable {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
        public ActionMenuPresenter.SavedState createFromParcel(Parcel param2Parcel) {
          return new ActionMenuPresenter.SavedState(param2Parcel);
        }
        
        public ActionMenuPresenter.SavedState[] newArray(int param2Int) {
          return new ActionMenuPresenter.SavedState[param2Int];
        }
      };
    
    public int openSubMenuId;
    
    SavedState() {}
    
    SavedState(Parcel param1Parcel) {
      this.openSubMenuId = param1Parcel.readInt();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.openSubMenuId);
    }
  }
  
  static final class null implements Parcelable.Creator<SavedState> {
    public ActionMenuPresenter.SavedState createFromParcel(Parcel param1Parcel) {
      return new ActionMenuPresenter.SavedState(param1Parcel);
    }
    
    public ActionMenuPresenter.SavedState[] newArray(int param1Int) {
      return new ActionMenuPresenter.SavedState[param1Int];
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\ActionMenuPresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */