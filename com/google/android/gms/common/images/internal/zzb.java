package com.google.android.gms.common.images.internal;

import android.graphics.drawable.Drawable;

final class zzb extends Drawable.ConstantState {
  private zzb() {}
  
  public final int getChangingConfigurations() {
    return 0;
  }
  
  public final Drawable newDrawable() {
    return CrossFadingDrawable.zza.zzcp();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\internal\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */