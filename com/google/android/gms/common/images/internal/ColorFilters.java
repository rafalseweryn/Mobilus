package com.google.android.gms.common.images.internal;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public final class ColorFilters {
  public static final ColorFilter COLOR_FILTER_BW;
  
  private static final ColorMatrix zzpv;
  
  static {
    ColorMatrix colorMatrix = new ColorMatrix();
    zzpv = colorMatrix;
    colorMatrix.setSaturation(0.0F);
    COLOR_FILTER_BW = (ColorFilter)new ColorMatrixColorFilter(zzpv);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\internal\ColorFilters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */