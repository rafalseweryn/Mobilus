package com.kyleduo.switchbutton;

import android.content.res.ColorStateList;

public class ColorUtils {
  private static final int CHECKED_ATTR = 16842912;
  
  private static final int ENABLE_ATTR = 16842910;
  
  private static final int PRESSED_ATTR = 16842919;
  
  public static ColorStateList generateBackColorWithTintColor(int paramInt) {
    int i = paramInt + 805306368;
    return new ColorStateList(new int[][] { { -16842910, 16842912 }, , { -16842910 }, , { 16842912, 16842919 }, , { -16842912, 16842919 }, , { 16842912 }, , { -16842912 },  }, new int[] { paramInt + 520093696, 268435456, i, 536870912, i, 536870912 });
  }
  
  public static ColorStateList generateThumbColorWithTintColor(int paramInt) {
    int[] arrayOfInt1 = { -16842910, 16842912 };
    int[] arrayOfInt2 = { 16842919, 16842912 };
    int[] arrayOfInt3 = { 16842912 };
    int[] arrayOfInt4 = { -16842912 };
    int i = paramInt + 1728053248;
    return new ColorStateList(new int[][] { arrayOfInt1, { -16842910 }, , { 16842919, -16842912 }, , arrayOfInt2, arrayOfInt3, arrayOfInt4 }, new int[] { paramInt + 1442840576, -4539718, i, i, paramInt | 0xFF000000, -1118482 });
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\kyleduo\switchbutton\ColorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */