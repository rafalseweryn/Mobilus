package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface TintAwareDrawable {
  void setTint(@ColorInt int paramInt);
  
  void setTintList(ColorStateList paramColorStateList);
  
  void setTintMode(PorterDuff.Mode paramMode);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\graphics\drawable\TintAwareDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */