package com.google.android.gms.common.images.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public final class CrossFadingDrawable extends Drawable implements Drawable.Callback {
  private int mAlpha = 0;
  
  private int mFrom;
  
  private boolean zzpl = true;
  
  private int zzpw = 0;
  
  private long zzpx;
  
  private int zzpy;
  
  private int zzpz = 255;
  
  private int zzqa;
  
  private boolean zzqb;
  
  private zzb zzqc;
  
  private Drawable zzqd;
  
  private Drawable zzqe;
  
  private boolean zzqf;
  
  private boolean zzqg;
  
  private boolean zzqh;
  
  private int zzqi;
  
  public CrossFadingDrawable(Drawable paramDrawable1, Drawable paramDrawable2) {
    this((zzb)null);
    Drawable drawable2 = paramDrawable1;
    if (paramDrawable1 == null)
      drawable2 = zza.zzcp(); 
    this.zzqd = drawable2;
    drawable2.setCallback(this);
    zzb zzb1 = this.zzqc;
    int i = zzb1.zzql;
    zzb1.zzql = drawable2.getChangingConfigurations() | i;
    Drawable drawable1 = paramDrawable2;
    if (paramDrawable2 == null)
      drawable1 = zza.zzcp(); 
    this.zzqe = drawable1;
    drawable1.setCallback(this);
    zzb zzb2 = this.zzqc;
    zzb2.zzql |= drawable1.getChangingConfigurations();
  }
  
  CrossFadingDrawable(zzb paramzzb) {
    this.zzqc = new zzb(paramzzb);
  }
  
  public final boolean canConstantState() {
    if (!this.zzqf) {
      boolean bool;
      if (this.zzqd.getConstantState() != null && this.zzqe.getConstantState() != null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.zzqg = bool;
      this.zzqf = true;
    } 
    return this.zzqg;
  }
  
  public final void draw(Canvas paramCanvas) {
    int i = this.zzpw;
    int j = 1;
    int k = 1;
    switch (i) {
      case 2:
        if (this.zzpx >= 0L) {
          float f = (float)(SystemClock.uptimeMillis() - this.zzpx) / this.zzqa;
          if (f >= 1.0F) {
            j = k;
          } else {
            j = 0;
          } 
          if (j)
            this.zzpw = 0; 
          f = Math.min(f, 1.0F);
          this.mAlpha = (int)(this.zzpy * f + 0.0F);
        } 
        break;
      case 1:
        this.zzpx = SystemClock.uptimeMillis();
        this.zzpw = 2;
        j = 0;
        break;
    } 
    k = this.mAlpha;
    boolean bool = this.zzpl;
    Drawable drawable1 = this.zzqd;
    Drawable drawable2 = this.zzqe;
    if (j != 0) {
      if (!bool || k == 0)
        drawable1.draw(paramCanvas); 
      if (k == this.zzpz) {
        drawable2.setAlpha(this.zzpz);
        drawable2.draw(paramCanvas);
      } 
      return;
    } 
    if (bool)
      drawable1.setAlpha(this.zzpz - k); 
    drawable1.draw(paramCanvas);
    if (bool)
      drawable1.setAlpha(this.zzpz); 
    if (k > 0) {
      drawable2.setAlpha(k);
      drawable2.draw(paramCanvas);
      drawable2.setAlpha(this.zzpz);
    } 
    invalidateSelf();
  }
  
  public final int getChangingConfigurations() {
    int i = super.getChangingConfigurations();
    int j = this.zzqc.mChangingConfigurations;
    return this.zzqc.zzql | i | j;
  }
  
  public final Drawable.ConstantState getConstantState() {
    if (canConstantState()) {
      this.zzqc.mChangingConfigurations = getChangingConfigurations();
      return this.zzqc;
    } 
    return null;
  }
  
  public final Drawable getEndDrawable() {
    return this.zzqe;
  }
  
  public final int getIntrinsicHeight() {
    return Math.max(this.zzqd.getIntrinsicHeight(), this.zzqe.getIntrinsicHeight());
  }
  
  public final int getIntrinsicWidth() {
    return Math.max(this.zzqd.getIntrinsicWidth(), this.zzqe.getIntrinsicWidth());
  }
  
  public final int getOpacity() {
    if (!this.zzqh) {
      this.zzqi = Drawable.resolveOpacity(this.zzqd.getOpacity(), this.zzqe.getOpacity());
      this.zzqh = true;
    } 
    return this.zzqi;
  }
  
  public final Drawable getStartDrawable() {
    return this.zzqd;
  }
  
  public final void invalidateDrawable(Drawable paramDrawable) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.invalidateDrawable(this); 
  }
  
  public final Drawable mutate() {
    if (!this.zzqb && super.mutate() == this) {
      if (!canConstantState())
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated."); 
      this.zzqd.mutate();
      this.zzqe.mutate();
      this.zzqb = true;
    } 
    return this;
  }
  
  protected final void onBoundsChange(Rect paramRect) {
    this.zzqd.setBounds(paramRect);
    this.zzqe.setBounds(paramRect);
  }
  
  public final void resetTransition() {
    this.mAlpha = 0;
    this.zzpw = 0;
    invalidateSelf();
  }
  
  public final void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.scheduleDrawable(this, paramRunnable, paramLong); 
  }
  
  public final void setAlpha(int paramInt) {
    if (this.mAlpha == this.zzpz)
      this.mAlpha = paramInt; 
    this.zzpz = paramInt;
    invalidateSelf();
  }
  
  public final void setColorFilter(ColorFilter paramColorFilter) {
    this.zzqd.setColorFilter(paramColorFilter);
    this.zzqe.setColorFilter(paramColorFilter);
  }
  
  public final void setCrossFadeEnabled(boolean paramBoolean) {
    this.zzpl = paramBoolean;
  }
  
  public final void startTransition(int paramInt) {
    this.mFrom = 0;
    this.zzpy = this.zzpz;
    this.mAlpha = 0;
    this.zzqa = paramInt;
    this.zzpw = 1;
    invalidateSelf();
  }
  
  public final void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.unscheduleDrawable(this, paramRunnable); 
  }
  
  private static final class zza extends Drawable {
    private static final zza zzqj = new zza();
    
    private static final zzb zzqk = new zzb(null);
    
    public final void draw(Canvas param1Canvas) {}
    
    public final Drawable.ConstantState getConstantState() {
      return zzqk;
    }
    
    public final int getOpacity() {
      return -2;
    }
    
    public final void setAlpha(int param1Int) {}
    
    public final void setColorFilter(ColorFilter param1ColorFilter) {}
  }
  
  static final class zzb extends Drawable.ConstantState {
    int mChangingConfigurations;
    
    int zzql;
    
    zzb(zzb param1zzb) {
      if (param1zzb != null) {
        this.mChangingConfigurations = param1zzb.mChangingConfigurations;
        this.zzql = param1zzb.zzql;
      } 
    }
    
    public final int getChangingConfigurations() {
      return this.mChangingConfigurations;
    }
    
    public final Drawable newDrawable() {
      return new CrossFadingDrawable(this);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\internal\CrossFadingDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */