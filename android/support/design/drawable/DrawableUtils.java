package android.support.design.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DrawableUtils {
  @Nullable
  public static PorterDuffColorFilter updateTintFilter(Drawable paramDrawable, @Nullable ColorStateList paramColorStateList, @Nullable PorterDuff.Mode paramMode) {
    return (paramColorStateList == null || paramMode == null) ? null : new PorterDuffColorFilter(paramColorStateList.getColorForState(paramDrawable.getState(), 0), paramMode);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\drawable\DrawableUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */