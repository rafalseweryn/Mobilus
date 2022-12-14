package android.support.design.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.BoolRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.R;
import android.support.design.internal.FlowLayout;
import android.support.design.internal.ThemeEnforcement;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

public class ChipGroup extends FlowLayout {
  @IdRes
  private int checkedId = -1;
  
  private final CheckedStateTracker checkedStateTracker = new CheckedStateTracker();
  
  @Dimension
  private int chipSpacingHorizontal;
  
  @Dimension
  private int chipSpacingVertical;
  
  @Nullable
  private OnCheckedChangeListener onCheckedChangeListener;
  
  private PassThroughHierarchyChangeListener passThroughListener = new PassThroughHierarchyChangeListener();
  
  private boolean protectFromCheckedChange = false;
  
  private boolean singleSelection;
  
  public ChipGroup(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public ChipGroup(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.chipGroupStyle);
  }
  
  public ChipGroup(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray typedArray = ThemeEnforcement.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.ChipGroup, paramInt, R.style.Widget_MaterialComponents_ChipGroup, new int[0]);
    paramInt = typedArray.getDimensionPixelOffset(R.styleable.ChipGroup_chipSpacing, 0);
    setChipSpacingHorizontal(typedArray.getDimensionPixelOffset(R.styleable.ChipGroup_chipSpacingHorizontal, paramInt));
    setChipSpacingVertical(typedArray.getDimensionPixelOffset(R.styleable.ChipGroup_chipSpacingVertical, paramInt));
    setSingleLine(typedArray.getBoolean(R.styleable.ChipGroup_singleLine, false));
    setSingleSelection(typedArray.getBoolean(R.styleable.ChipGroup_singleSelection, false));
    paramInt = typedArray.getResourceId(R.styleable.ChipGroup_checkedChip, -1);
    if (paramInt != -1)
      this.checkedId = paramInt; 
    typedArray.recycle();
    super.setOnHierarchyChangeListener(this.passThroughListener);
  }
  
  private void setCheckedId(int paramInt) {
    this.checkedId = paramInt;
    if (this.onCheckedChangeListener != null && this.singleSelection)
      this.onCheckedChangeListener.onCheckedChanged(this, paramInt); 
  }
  
  private void setCheckedStateForView(@IdRes int paramInt, boolean paramBoolean) {
    View view = findViewById(paramInt);
    if (view instanceof Chip) {
      this.protectFromCheckedChange = true;
      ((Chip)view).setChecked(paramBoolean);
      this.protectFromCheckedChange = false;
    } 
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    if (paramView instanceof Chip) {
      Chip chip = (Chip)paramView;
      if (chip.isChecked()) {
        if (this.checkedId != -1 && this.singleSelection)
          setCheckedStateForView(this.checkedId, false); 
        setCheckedId(chip.getId());
      } 
    } 
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  public void check(@IdRes int paramInt) {
    if (paramInt == this.checkedId)
      return; 
    if (this.checkedId != -1 && this.singleSelection)
      setCheckedStateForView(this.checkedId, false); 
    if (paramInt != -1)
      setCheckedStateForView(paramInt, true); 
    setCheckedId(paramInt);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (super.checkLayoutParams(paramLayoutParams) && paramLayoutParams instanceof LayoutParams) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearCheck() {
    this.protectFromCheckedChange = true;
    for (byte b = 0; b < getChildCount(); b++) {
      View view = getChildAt(b);
      if (view instanceof Chip)
        ((Chip)view).setChecked(false); 
    } 
    this.protectFromCheckedChange = false;
    setCheckedId(-1);
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return (ViewGroup.LayoutParams)new LayoutParams(-2, -2);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return (ViewGroup.LayoutParams)new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (ViewGroup.LayoutParams)new LayoutParams(paramLayoutParams);
  }
  
  @IdRes
  public int getCheckedChipId() {
    byte b;
    if (this.singleSelection) {
      b = this.checkedId;
    } else {
      b = -1;
    } 
    return b;
  }
  
  @Dimension
  public int getChipSpacingHorizontal() {
    return this.chipSpacingHorizontal;
  }
  
  @Dimension
  public int getChipSpacingVertical() {
    return this.chipSpacingVertical;
  }
  
  public boolean isSingleSelection() {
    return this.singleSelection;
  }
  
  protected void onFinishInflate() {
    super.onFinishInflate();
    if (this.checkedId != -1) {
      setCheckedStateForView(this.checkedId, true);
      setCheckedId(this.checkedId);
    } 
  }
  
  public void setChipSpacing(@Dimension int paramInt) {
    setChipSpacingHorizontal(paramInt);
    setChipSpacingVertical(paramInt);
  }
  
  public void setChipSpacingHorizontal(@Dimension int paramInt) {
    if (this.chipSpacingHorizontal != paramInt) {
      this.chipSpacingHorizontal = paramInt;
      setItemSpacing(paramInt);
      requestLayout();
    } 
  }
  
  public void setChipSpacingHorizontalResource(@DimenRes int paramInt) {
    setChipSpacingHorizontal(getResources().getDimensionPixelOffset(paramInt));
  }
  
  public void setChipSpacingResource(@DimenRes int paramInt) {
    setChipSpacing(getResources().getDimensionPixelOffset(paramInt));
  }
  
  public void setChipSpacingVertical(@Dimension int paramInt) {
    if (this.chipSpacingVertical != paramInt) {
      this.chipSpacingVertical = paramInt;
      setLineSpacing(paramInt);
      requestLayout();
    } 
  }
  
  public void setChipSpacingVerticalResource(@DimenRes int paramInt) {
    setChipSpacingVertical(getResources().getDimensionPixelOffset(paramInt));
  }
  
  @Deprecated
  public void setDividerDrawableHorizontal(Drawable paramDrawable) {
    throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
  }
  
  @Deprecated
  public void setDividerDrawableVertical(@Nullable Drawable paramDrawable) {
    throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
  }
  
  @Deprecated
  public void setFlexWrap(int paramInt) {
    throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
  }
  
  public void setOnCheckedChangeListener(OnCheckedChangeListener paramOnCheckedChangeListener) {
    this.onCheckedChangeListener = paramOnCheckedChangeListener;
  }
  
  public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener paramOnHierarchyChangeListener) {
    PassThroughHierarchyChangeListener.access$202(this.passThroughListener, paramOnHierarchyChangeListener);
  }
  
  @Deprecated
  public void setShowDividerHorizontal(int paramInt) {
    throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
  }
  
  @Deprecated
  public void setShowDividerVertical(int paramInt) {
    throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
  }
  
  public void setSingleLine(@BoolRes int paramInt) {
    setSingleLine(getResources().getBoolean(paramInt));
  }
  
  public void setSingleSelection(@BoolRes int paramInt) {
    setSingleSelection(getResources().getBoolean(paramInt));
  }
  
  public void setSingleSelection(boolean paramBoolean) {
    if (this.singleSelection != paramBoolean) {
      this.singleSelection = paramBoolean;
      clearCheck();
    } 
  }
  
  private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
    private CheckedStateTracker() {}
    
    public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
      if (ChipGroup.this.protectFromCheckedChange)
        return; 
      int i = param1CompoundButton.getId();
      if (param1Boolean) {
        if (ChipGroup.this.checkedId != -1 && ChipGroup.this.checkedId != i && ChipGroup.this.singleSelection)
          ChipGroup.this.setCheckedStateForView(ChipGroup.this.checkedId, false); 
        ChipGroup.this.setCheckedId(i);
      } else if (ChipGroup.this.checkedId == i) {
        ChipGroup.this.setCheckedId(-1);
      } 
    }
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
  }
  
  public static interface OnCheckedChangeListener {
    void onCheckedChanged(ChipGroup param1ChipGroup, @IdRes int param1Int);
  }
  
  private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
    private ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;
    
    private PassThroughHierarchyChangeListener() {}
    
    public void onChildViewAdded(View param1View1, View param1View2) {
      if (param1View1 == ChipGroup.this && param1View2 instanceof Chip) {
        if (param1View2.getId() == -1) {
          int i;
          if (Build.VERSION.SDK_INT >= 17) {
            i = View.generateViewId();
          } else {
            i = param1View2.hashCode();
          } 
          param1View2.setId(i);
        } 
        ((Chip)param1View2).setOnCheckedChangeListenerInternal(ChipGroup.this.checkedStateTracker);
      } 
      if (this.onHierarchyChangeListener != null)
        this.onHierarchyChangeListener.onChildViewAdded(param1View1, param1View2); 
    }
    
    public void onChildViewRemoved(View param1View1, View param1View2) {
      if (param1View1 == ChipGroup.this && param1View2 instanceof Chip)
        ((Chip)param1View2).setOnCheckedChangeListenerInternal((CompoundButton.OnCheckedChangeListener)null); 
      if (this.onHierarchyChangeListener != null)
        this.onHierarchyChangeListener.onChildViewRemoved(param1View1, param1View2); 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\chip\ChipGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */