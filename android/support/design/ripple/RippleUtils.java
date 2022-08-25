package android.support.design.ripple;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.ColorUtils;
import android.util.StateSet;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class RippleUtils {
  private static final int[] FOCUSED_STATE_SET;
  
  private static final int[] HOVERED_FOCUSED_STATE_SET;
  
  private static final int[] HOVERED_STATE_SET;
  
  private static final int[] PRESSED_STATE_SET;
  
  private static final int[] SELECTED_FOCUSED_STATE_SET;
  
  private static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET;
  
  private static final int[] SELECTED_HOVERED_STATE_SET;
  
  private static final int[] SELECTED_PRESSED_STATE_SET;
  
  private static final int[] SELECTED_STATE_SET;
  
  public static final boolean USE_FRAMEWORK_RIPPLE;
  
  static {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    } 
    USE_FRAMEWORK_RIPPLE = bool;
    PRESSED_STATE_SET = new int[] { 16842919 };
    HOVERED_FOCUSED_STATE_SET = new int[] { 16843623, 16842908 };
    FOCUSED_STATE_SET = new int[] { 16842908 };
    HOVERED_STATE_SET = new int[] { 16843623 };
    SELECTED_PRESSED_STATE_SET = new int[] { 16842913, 16842919 };
    SELECTED_HOVERED_FOCUSED_STATE_SET = new int[] { 16842913, 16843623, 16842908 };
    SELECTED_FOCUSED_STATE_SET = new int[] { 16842913, 16842908 };
    SELECTED_HOVERED_STATE_SET = new int[] { 16842913, 16843623 };
    SELECTED_STATE_SET = new int[] { 16842913 };
  }
  
  @NonNull
  public static ColorStateList convertToRippleDrawableColor(@Nullable ColorStateList paramColorStateList) {
    if (USE_FRAMEWORK_RIPPLE) {
      int[] arrayOfInt10 = SELECTED_STATE_SET;
      int i4 = getColorForState(paramColorStateList, SELECTED_PRESSED_STATE_SET);
      int[] arrayOfInt11 = StateSet.NOTHING;
      int i5 = getColorForState(paramColorStateList, PRESSED_STATE_SET);
      return new ColorStateList(new int[][] { arrayOfInt10, arrayOfInt11 }, new int[] { i4, i5 });
    } 
    int[] arrayOfInt3 = SELECTED_PRESSED_STATE_SET;
    int i = getColorForState(paramColorStateList, SELECTED_PRESSED_STATE_SET);
    int[] arrayOfInt4 = SELECTED_HOVERED_FOCUSED_STATE_SET;
    int k = getColorForState(paramColorStateList, SELECTED_HOVERED_FOCUSED_STATE_SET);
    int[] arrayOfInt5 = SELECTED_FOCUSED_STATE_SET;
    int m = getColorForState(paramColorStateList, SELECTED_FOCUSED_STATE_SET);
    int[] arrayOfInt6 = SELECTED_HOVERED_STATE_SET;
    int n = getColorForState(paramColorStateList, SELECTED_HOVERED_STATE_SET);
    int[] arrayOfInt2 = SELECTED_STATE_SET;
    int[] arrayOfInt7 = PRESSED_STATE_SET;
    int i1 = getColorForState(paramColorStateList, PRESSED_STATE_SET);
    int[] arrayOfInt8 = HOVERED_FOCUSED_STATE_SET;
    int i2 = getColorForState(paramColorStateList, HOVERED_FOCUSED_STATE_SET);
    int[] arrayOfInt1 = FOCUSED_STATE_SET;
    int i3 = getColorForState(paramColorStateList, FOCUSED_STATE_SET);
    int[] arrayOfInt9 = HOVERED_STATE_SET;
    int j = getColorForState(paramColorStateList, HOVERED_STATE_SET);
    return new ColorStateList(new int[][] { arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, arrayOfInt2, arrayOfInt7, arrayOfInt8, arrayOfInt1, arrayOfInt9, StateSet.NOTHING }, new int[] { i, k, m, n, 0, i1, i2, i3, j, 0 });
  }
  
  @TargetApi(21)
  @ColorInt
  private static int doubleAlpha(@ColorInt int paramInt) {
    return ColorUtils.setAlphaComponent(paramInt, Math.min(Color.alpha(paramInt) * 2, 255));
  }
  
  @ColorInt
  private static int getColorForState(@Nullable ColorStateList paramColorStateList, int[] paramArrayOfint) {
    byte b;
    if (paramColorStateList != null) {
      b = paramColorStateList.getColorForState(paramArrayOfint, paramColorStateList.getDefaultColor());
    } else {
      b = 0;
    } 
    int i = b;
    if (USE_FRAMEWORK_RIPPLE)
      i = doubleAlpha(b); 
    return i;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\ripple\RippleUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */