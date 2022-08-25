package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface TintableImageSourceView {
  @Nullable
  ColorStateList getSupportImageTintList();
  
  @Nullable
  PorterDuff.Mode getSupportImageTintMode();
  
  void setSupportImageTintList(@Nullable ColorStateList paramColorStateList);
  
  void setSupportImageTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\widget\TintableImageSourceView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */