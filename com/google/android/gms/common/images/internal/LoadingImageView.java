package com.google.android.gms.common.images.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.gms.base.R;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.images.ImageRequest;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.util.PlatformVersion;

public final class LoadingImageView extends ImageView {
  public static final int ASPECT_RATIO_ADJUST_HEIGHT = 2;
  
  public static final int ASPECT_RATIO_ADJUST_NONE = 0;
  
  public static final int ASPECT_RATIO_ADJUST_WIDTH = 1;
  
  private static ImageManager zzqm;
  
  private ImageManager.OnImageLoadedListener mOnImageLoadedListener;
  
  private int mPostProcessingFlags = 0;
  
  private boolean zzpl = true;
  
  private boolean zzpm = false;
  
  private Uri zzqn;
  
  private int zzqo = 0;
  
  private boolean zzqp = false;
  
  private int zzqq = 0;
  
  private ClipPathProvider zzqr;
  
  private int zzqs = 0;
  
  private float zzqt = 1.0F;
  
  public LoadingImageView(Context paramContext) {
    this(paramContext, (AttributeSet)null, 0);
  }
  
  public LoadingImageView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LoadingImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.LoadingImageView);
    this.zzqs = typedArray.getInt(R.styleable.LoadingImageView_imageAspectRatioAdjust, 0);
    this.zzqt = typedArray.getFloat(R.styleable.LoadingImageView_imageAspectRatio, 1.0F);
    setCircleCropEnabled(typedArray.getBoolean(R.styleable.LoadingImageView_circleCrop, false));
    typedArray.recycle();
  }
  
  private final void zzc(boolean paramBoolean) {
    if (this.mOnImageLoadedListener != null)
      this.mOnImageLoadedListener.onImageLoaded(this.zzqn, null, paramBoolean); 
  }
  
  public final void clearAspectRatioAdjust() {
    if (this.zzqs != 0) {
      this.zzqs = 0;
      requestLayout();
    } 
  }
  
  public final void clearImage() {
    loadUri((Uri)null);
    this.zzqp = true;
  }
  
  public final int getLoadedNoDataPlaceholderResId() {
    return this.zzqo;
  }
  
  public final Uri getLoadedUri() {
    return this.zzqn;
  }
  
  public final void loadUri(Uri paramUri) {
    loadUri(paramUri, 0, true, false);
  }
  
  public final void loadUri(Uri paramUri, int paramInt) {
    loadUri(paramUri, paramInt, true, false);
  }
  
  public final void loadUri(Uri paramUri, int paramInt, boolean paramBoolean) {
    loadUri(paramUri, paramInt, paramBoolean, false);
  }
  
  public final void loadUri(Uri paramUri, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = true;
    if (paramUri != null) {
      bool1 = paramUri.equals(this.zzqn);
    } else if (this.zzqn == null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (bool1) {
      if (this.zzqn != null) {
        zzc(true);
        return;
      } 
      if (this.zzqo == paramInt) {
        zzc(false);
        return;
      } 
    } 
    if (zzqm == null) {
      bool1 = getContext().getApplicationContext().getPackageName().equals("com.google.android.play.games");
      zzqm = ImageManager.create(getContext(), bool1);
    } 
    boolean bool1 = bool;
    if (!this.zzpm)
      if (this.zzqp) {
        bool1 = bool;
      } else {
        bool1 = false;
      }  
    this.zzqp = false;
    ImageRequest.ImageViewImageRequest imageViewImageRequest = new ImageRequest.ImageViewImageRequest(this, paramUri);
    imageViewImageRequest.setNoDataPlaceholder(paramInt);
    imageViewImageRequest.setCrossFadeEnabled(this.zzpl);
    imageViewImageRequest.setCrossFadeAlwaysEnabled(bool1);
    imageViewImageRequest.setLoadingPlaceholderEnabled(paramBoolean1);
    imageViewImageRequest.setPostProcessingFlags(this.mPostProcessingFlags);
    imageViewImageRequest.setOnImageLoadedListener(this.mOnImageLoadedListener);
    imageViewImageRequest.setUseNewDrawable(paramBoolean2);
    zzqm.loadImage((ImageRequest)imageViewImageRequest);
  }
  
  protected final void onDraw(Canvas paramCanvas) {
    if (this.zzqr != null)
      paramCanvas.clipPath(this.zzqr.getClipPath(getWidth(), getHeight())); 
    super.onDraw(paramCanvas);
    if (this.zzqq != 0)
      paramCanvas.drawColor(this.zzqq); 
  }
  
  protected final void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    switch (this.zzqs) {
      default:
        return;
      case 2:
        paramInt1 = getMeasuredWidth();
        paramInt2 = (int)(paramInt1 / this.zzqt);
        break;
      case 1:
        paramInt2 = getMeasuredHeight();
        paramInt1 = (int)(paramInt2 * this.zzqt);
        break;
    } 
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  public final void setCircleCropEnabled(boolean paramBoolean) {
    int i;
    if (paramBoolean) {
      i = this.mPostProcessingFlags | 0x1;
    } else {
      i = this.mPostProcessingFlags & 0xFFFFFFFE;
    } 
    this.mPostProcessingFlags = i;
  }
  
  public final void setClipPathProvider(ClipPathProvider paramClipPathProvider) {
    this.zzqr = paramClipPathProvider;
    if (!PlatformVersion.isAtLeastJellyBean())
      setLayerType(1, null); 
  }
  
  public final void setCrossFadeAlwaysEnabled(boolean paramBoolean) {
    this.zzpm = paramBoolean;
  }
  
  public final void setCrossFadeEnabled(boolean paramBoolean) {
    this.zzpl = paramBoolean;
  }
  
  public final void setImageAspectRatioAdjust(int paramInt, float paramFloat) {
    boolean bool1 = false;
    if (paramInt == 0 || paramInt == 1 || paramInt == 2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Asserts.checkState(bool2);
    boolean bool2 = bool1;
    if (paramFloat > 0.0F)
      bool2 = true; 
    Asserts.checkState(bool2);
    this.zzqs = paramInt;
    this.zzqt = paramFloat;
    requestLayout();
  }
  
  public final void setLoadedNoDataPlaceholderResId(int paramInt) {
    this.zzqo = paramInt;
  }
  
  public final void setLoadedUri(Uri paramUri) {
    this.zzqn = paramUri;
  }
  
  public final void setOnImageLoadedListener(ImageManager.OnImageLoadedListener paramOnImageLoadedListener) {
    this.mOnImageLoadedListener = paramOnImageLoadedListener;
  }
  
  public final void setTintColor(int paramInt) {
    ColorFilter colorFilter;
    this.zzqq = paramInt;
    if (this.zzqq != 0) {
      colorFilter = ColorFilters.COLOR_FILTER_BW;
    } else {
      colorFilter = null;
    } 
    setColorFilter(colorFilter);
    invalidate();
  }
  
  public final void setTintColorId(int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: ifle -> 22
    //   4: aload_0
    //   5: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   8: astore_2
    //   9: aload_2
    //   10: ifnull -> 22
    //   13: aload_2
    //   14: iload_1
    //   15: invokevirtual getColor : (I)I
    //   18: istore_1
    //   19: goto -> 24
    //   22: iconst_0
    //   23: istore_1
    //   24: aload_0
    //   25: iload_1
    //   26: invokevirtual setTintColor : (I)V
    //   29: return
  }
  
  public static interface ClipPathProvider {
    Path getClipPath(int param1Int1, int param1Int2);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\internal\LoadingImageView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */