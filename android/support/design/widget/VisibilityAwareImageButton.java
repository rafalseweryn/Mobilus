package android.support.design.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.widget.ImageButton;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class VisibilityAwareImageButton extends ImageButton {
  private int userSetVisibility = getVisibility();
  
  public VisibilityAwareImageButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public VisibilityAwareImageButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public VisibilityAwareImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public final int getUserSetVisibility() {
    return this.userSetVisibility;
  }
  
  public final void internalSetVisibility(int paramInt, boolean paramBoolean) {
    super.setVisibility(paramInt);
    if (paramBoolean)
      this.userSetVisibility = paramInt; 
  }
  
  public void setVisibility(int paramInt) {
    internalSetVisibility(paramInt, true);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\VisibilityAwareImageButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */