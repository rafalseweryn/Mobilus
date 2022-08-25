package android.support.design.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.support.v7.content.res.AppCompatResources;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MaterialResources {
  @Nullable
  public static ColorStateList getColorStateList(Context paramContext, TypedArray paramTypedArray, @StyleableRes int paramInt) {
    if (paramTypedArray.hasValue(paramInt)) {
      int i = paramTypedArray.getResourceId(paramInt, 0);
      if (i != 0) {
        ColorStateList colorStateList = AppCompatResources.getColorStateList(paramContext, i);
        if (colorStateList != null)
          return colorStateList; 
      } 
    } 
    return paramTypedArray.getColorStateList(paramInt);
  }
  
  @Nullable
  public static Drawable getDrawable(Context paramContext, TypedArray paramTypedArray, @StyleableRes int paramInt) {
    if (paramTypedArray.hasValue(paramInt)) {
      int i = paramTypedArray.getResourceId(paramInt, 0);
      if (i != 0) {
        Drawable drawable = AppCompatResources.getDrawable(paramContext, i);
        if (drawable != null)
          return drawable; 
      } 
    } 
    return paramTypedArray.getDrawable(paramInt);
  }
  
  @StyleableRes
  static int getIndexWithValue(TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2) {
    return paramTypedArray.hasValue(paramInt1) ? paramInt1 : paramInt2;
  }
  
  @Nullable
  public static TextAppearance getTextAppearance(Context paramContext, TypedArray paramTypedArray, @StyleableRes int paramInt) {
    if (paramTypedArray.hasValue(paramInt)) {
      paramInt = paramTypedArray.getResourceId(paramInt, 0);
      if (paramInt != 0)
        return new TextAppearance(paramContext, paramInt); 
    } 
    return null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\resources\MaterialResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */