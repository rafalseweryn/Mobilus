package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.appcompat.R;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

class DropDownListView extends ListView {
  public static final int INVALID_POSITION = -1;
  
  public static final int NO_POSITION = -1;
  
  private ViewPropertyAnimatorCompat mClickAnimation;
  
  private boolean mDrawsInPressedState;
  
  private boolean mHijackFocus;
  
  private Field mIsChildViewEnabled;
  
  private boolean mListSelectionHidden;
  
  private int mMotionPosition;
  
  ResolveHoverRunnable mResolveHoverRunnable;
  
  private ListViewAutoScrollHelper mScrollHelper;
  
  private int mSelectionBottomPadding = 0;
  
  private int mSelectionLeftPadding = 0;
  
  private int mSelectionRightPadding = 0;
  
  private int mSelectionTopPadding = 0;
  
  private GateKeeperDrawable mSelector;
  
  private final Rect mSelectorRect = new Rect();
  
  DropDownListView(Context paramContext, boolean paramBoolean) {
    super(paramContext, null, R.attr.dropDownListViewStyle);
    this.mHijackFocus = paramBoolean;
    setCacheColorHint(0);
    try {
      this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
      this.mIsChildViewEnabled.setAccessible(true);
    } catch (NoSuchFieldException noSuchFieldException) {
      noSuchFieldException.printStackTrace();
    } 
  }
  
  private void clearPressedItem() {
    this.mDrawsInPressedState = false;
    setPressed(false);
    drawableStateChanged();
    View view = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
    if (view != null)
      view.setPressed(false); 
    if (this.mClickAnimation != null) {
      this.mClickAnimation.cancel();
      this.mClickAnimation = null;
    } 
  }
  
  private void clickPressedItem(View paramView, int paramInt) {
    performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
  }
  
  private void drawSelectorCompat(Canvas paramCanvas) {
    if (!this.mSelectorRect.isEmpty()) {
      Drawable drawable = getSelector();
      if (drawable != null) {
        drawable.setBounds(this.mSelectorRect);
        drawable.draw(paramCanvas);
      } 
    } 
  }
  
  private void positionSelectorCompat(int paramInt, View paramView) {
    Rect rect = this.mSelectorRect;
    rect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
    rect.left -= this.mSelectionLeftPadding;
    rect.top -= this.mSelectionTopPadding;
    rect.right += this.mSelectionRightPadding;
    rect.bottom += this.mSelectionBottomPadding;
    try {
      boolean bool = this.mIsChildViewEnabled.getBoolean(this);
      if (paramView.isEnabled() != bool) {
        this.mIsChildViewEnabled.set(this, Boolean.valueOf(bool ^ true));
        if (paramInt != -1)
          refreshDrawableState(); 
      } 
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException.printStackTrace();
    } 
  }
  
  private void positionSelectorLikeFocusCompat(int paramInt, View paramView) {
    boolean bool2;
    Drawable drawable = getSelector();
    boolean bool1 = true;
    if (drawable != null && paramInt != -1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (bool2)
      drawable.setVisible(false, false); 
    positionSelectorCompat(paramInt, paramView);
    if (bool2) {
      Rect rect = this.mSelectorRect;
      float f1 = rect.exactCenterX();
      float f2 = rect.exactCenterY();
      if (getVisibility() != 0)
        bool1 = false; 
      drawable.setVisible(bool1, false);
      DrawableCompat.setHotspot(drawable, f1, f2);
    } 
  }
  
  private void positionSelectorLikeTouchCompat(int paramInt, View paramView, float paramFloat1, float paramFloat2) {
    positionSelectorLikeFocusCompat(paramInt, paramView);
    Drawable drawable = getSelector();
    if (drawable != null && paramInt != -1)
      DrawableCompat.setHotspot(drawable, paramFloat1, paramFloat2); 
  }
  
  private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2) {
    this.mDrawsInPressedState = true;
    if (Build.VERSION.SDK_INT >= 21)
      drawableHotspotChanged(paramFloat1, paramFloat2); 
    if (!isPressed())
      setPressed(true); 
    layoutChildren();
    if (this.mMotionPosition != -1) {
      View view = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
      if (view != null && view != paramView && view.isPressed())
        view.setPressed(false); 
    } 
    this.mMotionPosition = paramInt;
    float f1 = paramView.getLeft();
    float f2 = paramView.getTop();
    if (Build.VERSION.SDK_INT >= 21)
      paramView.drawableHotspotChanged(paramFloat1 - f1, paramFloat2 - f2); 
    if (!paramView.isPressed())
      paramView.setPressed(true); 
    positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
    setSelectorEnabled(false);
    refreshDrawableState();
  }
  
  private void setSelectorEnabled(boolean paramBoolean) {
    if (this.mSelector != null)
      this.mSelector.setEnabled(paramBoolean); 
  }
  
  private boolean touchModeDrawsInPressedStateCompat() {
    return this.mDrawsInPressedState;
  }
  
  private void updateSelectorStateCompat() {
    Drawable drawable = getSelector();
    if (drawable != null && touchModeDrawsInPressedStateCompat() && isPressed())
      drawable.setState(getDrawableState()); 
  }
  
  protected void dispatchDraw(Canvas paramCanvas) {
    drawSelectorCompat(paramCanvas);
    super.dispatchDraw(paramCanvas);
  }
  
  protected void drawableStateChanged() {
    if (this.mResolveHoverRunnable != null)
      return; 
    super.drawableStateChanged();
    setSelectorEnabled(true);
    updateSelectorStateCompat();
  }
  
  public boolean hasFocus() {
    return (this.mHijackFocus || super.hasFocus());
  }
  
  public boolean hasWindowFocus() {
    return (this.mHijackFocus || super.hasWindowFocus());
  }
  
  public boolean isFocused() {
    return (this.mHijackFocus || super.isFocused());
  }
  
  public boolean isInTouchMode() {
    boolean bool;
    if ((this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int lookForSelectablePosition(int paramInt, boolean paramBoolean) {
    ListAdapter listAdapter = getAdapter();
    if (listAdapter == null || isInTouchMode())
      return -1; 
    int i = listAdapter.getCount();
    if (!getAdapter().areAllItemsEnabled()) {
      int j;
      if (paramBoolean) {
        paramInt = Math.max(0, paramInt);
        while (true) {
          j = paramInt;
          if (paramInt < i) {
            j = paramInt;
            if (!listAdapter.isEnabled(paramInt)) {
              paramInt++;
              continue;
            } 
          } 
          break;
        } 
      } else {
        paramInt = Math.min(paramInt, i - 1);
        while (true) {
          j = paramInt;
          if (paramInt >= 0) {
            j = paramInt;
            if (!listAdapter.isEnabled(paramInt)) {
              paramInt--;
              continue;
            } 
          } 
          break;
        } 
      } 
      return (j < 0 || j >= i) ? -1 : j;
    } 
    return (paramInt < 0 || paramInt >= i) ? -1 : paramInt;
  }
  
  public int measureHeightOfChildrenCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    paramInt2 = getListPaddingTop();
    paramInt3 = getListPaddingBottom();
    getListPaddingLeft();
    getListPaddingRight();
    int i = getDividerHeight();
    Drawable drawable = getDivider();
    ListAdapter listAdapter = getAdapter();
    if (listAdapter == null)
      return paramInt2 + paramInt3; 
    if (i <= 0 || drawable == null)
      i = 0; 
    int j = listAdapter.getCount();
    paramInt3 = paramInt2 + paramInt3;
    byte b = 0;
    int k = b;
    paramInt2 = k;
    drawable = null;
    int m = k;
    while (b < j) {
      int n = listAdapter.getItemViewType(b);
      k = m;
      if (n != m) {
        drawable = null;
        k = n;
      } 
      View view2 = listAdapter.getView(b, (View)drawable, (ViewGroup)this);
      ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
      ViewGroup.LayoutParams layoutParams1 = layoutParams2;
      if (layoutParams2 == null) {
        layoutParams1 = generateDefaultLayoutParams();
        view2.setLayoutParams(layoutParams1);
      } 
      if (layoutParams1.height > 0) {
        m = View.MeasureSpec.makeMeasureSpec(layoutParams1.height, 1073741824);
      } else {
        m = View.MeasureSpec.makeMeasureSpec(0, 0);
      } 
      view2.measure(paramInt1, m);
      view2.forceLayout();
      m = paramInt3;
      if (b > 0)
        m = paramInt3 + i; 
      paramInt3 = m + view2.getMeasuredHeight();
      if (paramInt3 >= paramInt4) {
        paramInt1 = paramInt4;
        if (paramInt5 >= 0) {
          paramInt1 = paramInt4;
          if (b > paramInt5) {
            paramInt1 = paramInt4;
            if (paramInt2 > 0) {
              paramInt1 = paramInt4;
              if (paramInt3 != paramInt4)
                paramInt1 = paramInt2; 
            } 
          } 
        } 
        return paramInt1;
      } 
      n = paramInt2;
      if (paramInt5 >= 0) {
        n = paramInt2;
        if (b >= paramInt5)
          n = paramInt3; 
      } 
      b++;
      m = k;
      View view1 = view2;
      paramInt2 = n;
    } 
    return paramInt3;
  }
  
  protected void onDetachedFromWindow() {
    this.mResolveHoverRunnable = null;
    super.onDetachedFromWindow();
  }
  
  public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getActionMasked : ()I
    //   4: istore_3
    //   5: iload_3
    //   6: tableswitch default -> 32, 1 -> 57, 2 -> 51, 3 -> 41
    //   32: iconst_0
    //   33: istore #4
    //   35: iconst_1
    //   36: istore #5
    //   38: goto -> 155
    //   41: iconst_0
    //   42: istore #4
    //   44: iload #4
    //   46: istore #5
    //   48: goto -> 155
    //   51: iconst_1
    //   52: istore #5
    //   54: goto -> 60
    //   57: iconst_0
    //   58: istore #5
    //   60: aload_1
    //   61: iload_2
    //   62: invokevirtual findPointerIndex : (I)I
    //   65: istore #6
    //   67: iload #6
    //   69: ifge -> 75
    //   72: goto -> 41
    //   75: aload_1
    //   76: iload #6
    //   78: invokevirtual getX : (I)F
    //   81: f2i
    //   82: istore_2
    //   83: aload_1
    //   84: iload #6
    //   86: invokevirtual getY : (I)F
    //   89: f2i
    //   90: istore #6
    //   92: aload_0
    //   93: iload_2
    //   94: iload #6
    //   96: invokevirtual pointToPosition : (II)I
    //   99: istore #7
    //   101: iload #7
    //   103: iconst_m1
    //   104: if_icmpne -> 113
    //   107: iconst_1
    //   108: istore #4
    //   110: goto -> 155
    //   113: aload_0
    //   114: iload #7
    //   116: aload_0
    //   117: invokevirtual getFirstVisiblePosition : ()I
    //   120: isub
    //   121: invokevirtual getChildAt : (I)Landroid/view/View;
    //   124: astore #8
    //   126: aload_0
    //   127: aload #8
    //   129: iload #7
    //   131: iload_2
    //   132: i2f
    //   133: iload #6
    //   135: i2f
    //   136: invokespecial setPressedItem : (Landroid/view/View;IFF)V
    //   139: iload_3
    //   140: iconst_1
    //   141: if_icmpne -> 32
    //   144: aload_0
    //   145: aload #8
    //   147: iload #7
    //   149: invokespecial clickPressedItem : (Landroid/view/View;I)V
    //   152: goto -> 32
    //   155: iload #5
    //   157: ifeq -> 165
    //   160: iload #4
    //   162: ifeq -> 169
    //   165: aload_0
    //   166: invokespecial clearPressedItem : ()V
    //   169: iload #5
    //   171: ifeq -> 215
    //   174: aload_0
    //   175: getfield mScrollHelper : Landroid/support/v4/widget/ListViewAutoScrollHelper;
    //   178: ifnonnull -> 193
    //   181: aload_0
    //   182: new android/support/v4/widget/ListViewAutoScrollHelper
    //   185: dup
    //   186: aload_0
    //   187: invokespecial <init> : (Landroid/widget/ListView;)V
    //   190: putfield mScrollHelper : Landroid/support/v4/widget/ListViewAutoScrollHelper;
    //   193: aload_0
    //   194: getfield mScrollHelper : Landroid/support/v4/widget/ListViewAutoScrollHelper;
    //   197: iconst_1
    //   198: invokevirtual setEnabled : (Z)Landroid/support/v4/widget/AutoScrollHelper;
    //   201: pop
    //   202: aload_0
    //   203: getfield mScrollHelper : Landroid/support/v4/widget/ListViewAutoScrollHelper;
    //   206: aload_0
    //   207: aload_1
    //   208: invokevirtual onTouch : (Landroid/view/View;Landroid/view/MotionEvent;)Z
    //   211: pop
    //   212: goto -> 231
    //   215: aload_0
    //   216: getfield mScrollHelper : Landroid/support/v4/widget/ListViewAutoScrollHelper;
    //   219: ifnull -> 231
    //   222: aload_0
    //   223: getfield mScrollHelper : Landroid/support/v4/widget/ListViewAutoScrollHelper;
    //   226: iconst_0
    //   227: invokevirtual setEnabled : (Z)Landroid/support/v4/widget/AutoScrollHelper;
    //   230: pop
    //   231: iload #5
    //   233: ireturn
  }
  
  public boolean onHoverEvent(@NonNull MotionEvent paramMotionEvent) {
    if (Build.VERSION.SDK_INT < 26)
      return super.onHoverEvent(paramMotionEvent); 
    int i = paramMotionEvent.getActionMasked();
    if (i == 10 && this.mResolveHoverRunnable == null) {
      this.mResolveHoverRunnable = new ResolveHoverRunnable();
      this.mResolveHoverRunnable.post();
    } 
    boolean bool = super.onHoverEvent(paramMotionEvent);
    if (i == 9 || i == 7) {
      i = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      if (i != -1 && i != getSelectedItemPosition()) {
        View view = getChildAt(i - getFirstVisiblePosition());
        if (view.isEnabled())
          setSelectionFromTop(i, view.getTop() - getTop()); 
        updateSelectorStateCompat();
      } 
      return bool;
    } 
    setSelection(-1);
    return bool;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 0)
      this.mMotionPosition = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()); 
    if (this.mResolveHoverRunnable != null)
      this.mResolveHoverRunnable.cancel(); 
    return super.onTouchEvent(paramMotionEvent);
  }
  
  void setListSelectionHidden(boolean paramBoolean) {
    this.mListSelectionHidden = paramBoolean;
  }
  
  public void setSelector(Drawable paramDrawable) {
    GateKeeperDrawable gateKeeperDrawable;
    if (paramDrawable != null) {
      gateKeeperDrawable = new GateKeeperDrawable(paramDrawable);
    } else {
      gateKeeperDrawable = null;
    } 
    this.mSelector = gateKeeperDrawable;
    super.setSelector((Drawable)this.mSelector);
    Rect rect = new Rect();
    if (paramDrawable != null)
      paramDrawable.getPadding(rect); 
    this.mSelectionLeftPadding = rect.left;
    this.mSelectionTopPadding = rect.top;
    this.mSelectionRightPadding = rect.right;
    this.mSelectionBottomPadding = rect.bottom;
  }
  
  private static class GateKeeperDrawable extends DrawableWrapper {
    private boolean mEnabled = true;
    
    GateKeeperDrawable(Drawable param1Drawable) {
      super(param1Drawable);
    }
    
    public void draw(Canvas param1Canvas) {
      if (this.mEnabled)
        super.draw(param1Canvas); 
    }
    
    void setEnabled(boolean param1Boolean) {
      this.mEnabled = param1Boolean;
    }
    
    public void setHotspot(float param1Float1, float param1Float2) {
      if (this.mEnabled)
        super.setHotspot(param1Float1, param1Float2); 
    }
    
    public void setHotspotBounds(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      if (this.mEnabled)
        super.setHotspotBounds(param1Int1, param1Int2, param1Int3, param1Int4); 
    }
    
    public boolean setState(int[] param1ArrayOfint) {
      return this.mEnabled ? super.setState(param1ArrayOfint) : false;
    }
    
    public boolean setVisible(boolean param1Boolean1, boolean param1Boolean2) {
      return this.mEnabled ? super.setVisible(param1Boolean1, param1Boolean2) : false;
    }
  }
  
  private class ResolveHoverRunnable implements Runnable {
    public void cancel() {
      DropDownListView.this.mResolveHoverRunnable = null;
      DropDownListView.this.removeCallbacks(this);
    }
    
    public void post() {
      DropDownListView.this.post(this);
    }
    
    public void run() {
      DropDownListView.this.mResolveHoverRunnable = null;
      DropDownListView.this.drawableStateChanged();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\widget\DropDownListView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */