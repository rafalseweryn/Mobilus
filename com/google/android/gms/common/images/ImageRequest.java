package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.images.internal.CrossFadingDrawable;
import com.google.android.gms.common.images.internal.ImageUtils;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;

public abstract class ImageRequest {
  protected int mLoadingPlaceholderResId = 0;
  
  protected int mNoDataPlaceholderResId = 0;
  
  protected ImageManager.OnImageLoadedListener mOnImageLoadedListener;
  
  protected int mPostProcessingFlags;
  
  protected boolean mUseNewDrawable = false;
  
  final zza zzpk;
  
  private boolean zzpl = true;
  
  private boolean zzpm = false;
  
  private boolean zzpn = true;
  
  public ImageRequest(Uri paramUri, int paramInt) {
    this.zzpk = new zza(paramUri);
    this.mNoDataPlaceholderResId = paramInt;
  }
  
  private final Drawable zza(Context paramContext, PostProcessedResourceCache paramPostProcessedResourceCache, int paramInt) {
    Resources resources = paramContext.getResources();
    if (this.mPostProcessingFlags > 0) {
      PostProcessedResourceCache.PostProcessedResource postProcessedResource = new PostProcessedResourceCache.PostProcessedResource(paramInt, this.mPostProcessingFlags);
      Drawable drawable2 = (Drawable)paramPostProcessedResourceCache.get(postProcessedResource);
      Drawable drawable1 = drawable2;
      if (drawable2 == null) {
        drawable1 = resources.getDrawable(paramInt);
        if ((this.mPostProcessingFlags & 0x1) != 0)
          drawable1 = frameDrawableInCircle(resources, drawable1); 
        paramPostProcessedResourceCache.put(postProcessedResource, drawable1);
      } 
      return drawable1;
    } 
    return resources.getDrawable(paramInt);
  }
  
  protected CrossFadingDrawable createTransitionDrawable(Drawable paramDrawable1, Drawable paramDrawable2) {
    Drawable drawable;
    if (paramDrawable1 != null) {
      drawable = paramDrawable1;
      if (paramDrawable1 instanceof CrossFadingDrawable)
        drawable = ((CrossFadingDrawable)paramDrawable1).getEndDrawable(); 
    } else {
      drawable = null;
    } 
    return new CrossFadingDrawable(drawable, paramDrawable2);
  }
  
  protected Drawable frameDrawableInCircle(Resources paramResources, Drawable paramDrawable) {
    return ImageUtils.frameDrawableInCircle(paramResources, paramDrawable);
  }
  
  protected abstract void loadImage(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  public void setCrossFadeAlwaysEnabled(boolean paramBoolean) {
    this.zzpm = paramBoolean;
    if (paramBoolean)
      setCrossFadeEnabled(true); 
  }
  
  public void setCrossFadeEnabled(boolean paramBoolean) {
    this.zzpl = paramBoolean;
  }
  
  public void setLoadingPlaceholder(int paramInt) {
    this.mLoadingPlaceholderResId = paramInt;
  }
  
  public void setLoadingPlaceholderEnabled(boolean paramBoolean) {
    this.zzpn = paramBoolean;
  }
  
  public void setNoDataPlaceholder(int paramInt) {
    this.mNoDataPlaceholderResId = paramInt;
  }
  
  public void setOnImageLoadedListener(ImageManager.OnImageLoadedListener paramOnImageLoadedListener) {
    this.mOnImageLoadedListener = paramOnImageLoadedListener;
  }
  
  public void setPostProcessingFlags(int paramInt) {
    this.mPostProcessingFlags = paramInt;
  }
  
  public void setUseNewDrawable(boolean paramBoolean) {
    this.mUseNewDrawable = paramBoolean;
  }
  
  protected boolean shouldCrossFade(boolean paramBoolean1, boolean paramBoolean2) {
    return (this.zzpl && !paramBoolean2 && (!paramBoolean1 || this.zzpm));
  }
  
  final void zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean) {
    Asserts.checkNotNull(paramBitmap);
    Bitmap bitmap = paramBitmap;
    if ((this.mPostProcessingFlags & 0x1) != 0)
      bitmap = ImageUtils.frameBitmapInCircle(paramBitmap); 
    BitmapDrawable bitmapDrawable = new BitmapDrawable(paramContext.getResources(), bitmap);
    if (this.mOnImageLoadedListener != null)
      this.mOnImageLoadedListener.onImageLoaded(this.zzpk.uri, (Drawable)bitmapDrawable, true); 
    loadImage((Drawable)bitmapDrawable, paramBoolean, false, true);
  }
  
  final void zza(Context paramContext, PostProcessedResourceCache paramPostProcessedResourceCache) {
    if (this.zzpn) {
      Drawable drawable = null;
      if (this.mLoadingPlaceholderResId != 0)
        drawable = zza(paramContext, paramPostProcessedResourceCache, this.mLoadingPlaceholderResId); 
      loadImage(drawable, false, true, false);
    } 
  }
  
  final void zza(Context paramContext, PostProcessedResourceCache paramPostProcessedResourceCache, boolean paramBoolean) {
    if (this.mNoDataPlaceholderResId != 0) {
      Drawable drawable = zza(paramContext, paramPostProcessedResourceCache, this.mNoDataPlaceholderResId);
    } else {
      paramContext = null;
    } 
    if (this.mOnImageLoadedListener != null)
      this.mOnImageLoadedListener.onImageLoaded(this.zzpk.uri, (Drawable)paramContext, false); 
    loadImage((Drawable)paramContext, paramBoolean, false, false);
  }
  
  public static final class ImageViewImageRequest extends ImageRequest {
    private WeakReference<ImageView> zzpo;
    
    public ImageViewImageRequest(ImageView param1ImageView, int param1Int) {
      super(null, param1Int);
      Asserts.checkNotNull(param1ImageView);
      this.zzpo = new WeakReference<>(param1ImageView);
    }
    
    public ImageViewImageRequest(ImageView param1ImageView, Uri param1Uri) {
      super(param1Uri, 0);
      Asserts.checkNotNull(param1ImageView);
      this.zzpo = new WeakReference<>(param1ImageView);
    }
    
    public final boolean equals(Object param1Object) {
      if (!(param1Object instanceof ImageViewImageRequest))
        return false; 
      if (this == param1Object)
        return true; 
      ImageViewImageRequest imageViewImageRequest = (ImageViewImageRequest)param1Object;
      param1Object = this.zzpo.get();
      ImageView imageView = imageViewImageRequest.zzpo.get();
      return (imageView != null && param1Object != null && Objects.equal(imageView, param1Object));
    }
    
    public final int hashCode() {
      return 0;
    }
    
    protected final void loadImage(Drawable param1Drawable, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3) {
      // Byte code:
      //   0: aload_0
      //   1: getfield zzpo : Ljava/lang/ref/WeakReference;
      //   4: invokevirtual get : ()Ljava/lang/Object;
      //   7: checkcast android/widget/ImageView
      //   10: astore #5
      //   12: aload #5
      //   14: ifnull -> 209
      //   17: iconst_0
      //   18: istore #6
      //   20: iload_3
      //   21: ifne -> 35
      //   24: iload #4
      //   26: ifne -> 35
      //   29: iconst_1
      //   30: istore #7
      //   32: goto -> 38
      //   35: iconst_0
      //   36: istore #7
      //   38: iload #7
      //   40: ifeq -> 77
      //   43: aload #5
      //   45: instanceof com/google/android/gms/common/images/internal/LoadingImageView
      //   48: ifeq -> 77
      //   51: aload #5
      //   53: checkcast com/google/android/gms/common/images/internal/LoadingImageView
      //   56: invokevirtual getLoadedNoDataPlaceholderResId : ()I
      //   59: istore #8
      //   61: aload_0
      //   62: getfield mNoDataPlaceholderResId : I
      //   65: ifeq -> 77
      //   68: iload #8
      //   70: aload_0
      //   71: getfield mNoDataPlaceholderResId : I
      //   74: if_icmpeq -> 209
      //   77: aload_0
      //   78: iload_2
      //   79: iload_3
      //   80: invokevirtual shouldCrossFade : (ZZ)Z
      //   83: istore_2
      //   84: aload_1
      //   85: astore #9
      //   87: aload_0
      //   88: getfield mUseNewDrawable : Z
      //   91: ifeq -> 110
      //   94: aload_1
      //   95: astore #9
      //   97: aload_1
      //   98: ifnull -> 110
      //   101: aload_1
      //   102: invokevirtual getConstantState : ()Landroid/graphics/drawable/Drawable$ConstantState;
      //   105: invokevirtual newDrawable : ()Landroid/graphics/drawable/Drawable;
      //   108: astore #9
      //   110: aload #9
      //   112: astore_1
      //   113: iload_2
      //   114: ifeq -> 129
      //   117: aload_0
      //   118: aload #5
      //   120: invokevirtual getDrawable : ()Landroid/graphics/drawable/Drawable;
      //   123: aload #9
      //   125: invokevirtual createTransitionDrawable : (Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)Lcom/google/android/gms/common/images/internal/CrossFadingDrawable;
      //   128: astore_1
      //   129: aload #5
      //   131: aload_1
      //   132: invokevirtual setImageDrawable : (Landroid/graphics/drawable/Drawable;)V
      //   135: aload #5
      //   137: instanceof com/google/android/gms/common/images/internal/LoadingImageView
      //   140: ifeq -> 195
      //   143: aload #5
      //   145: checkcast com/google/android/gms/common/images/internal/LoadingImageView
      //   148: astore #5
      //   150: iload #4
      //   152: ifeq -> 167
      //   155: aload_0
      //   156: getfield zzpk : Lcom/google/android/gms/common/images/ImageRequest$zza;
      //   159: getfield uri : Landroid/net/Uri;
      //   162: astore #9
      //   164: goto -> 170
      //   167: aconst_null
      //   168: astore #9
      //   170: aload #5
      //   172: aload #9
      //   174: invokevirtual setLoadedUri : (Landroid/net/Uri;)V
      //   177: iload #7
      //   179: ifeq -> 188
      //   182: aload_0
      //   183: getfield mNoDataPlaceholderResId : I
      //   186: istore #6
      //   188: aload #5
      //   190: iload #6
      //   192: invokevirtual setLoadedNoDataPlaceholderResId : (I)V
      //   195: iload_2
      //   196: ifeq -> 209
      //   199: aload_1
      //   200: checkcast com/google/android/gms/common/images/internal/CrossFadingDrawable
      //   203: sipush #250
      //   206: invokevirtual startTransition : (I)V
      //   209: return
    }
  }
  
  public static final class ListenerImageRequest extends ImageRequest {
    private WeakReference<ImageManager.OnImageLoadedListener> zzpp;
    
    public ListenerImageRequest(ImageManager.OnImageLoadedListener param1OnImageLoadedListener, Uri param1Uri) {
      super(param1Uri, 0);
      Asserts.checkNotNull(param1OnImageLoadedListener);
      this.zzpp = new WeakReference<>(param1OnImageLoadedListener);
    }
    
    public final boolean equals(Object param1Object) {
      if (!(param1Object instanceof ListenerImageRequest))
        return false; 
      if (this == param1Object)
        return true; 
      ListenerImageRequest listenerImageRequest = (ListenerImageRequest)param1Object;
      ImageManager.OnImageLoadedListener onImageLoadedListener = this.zzpp.get();
      param1Object = listenerImageRequest.zzpp.get();
      return (param1Object != null && onImageLoadedListener != null && Objects.equal(param1Object, onImageLoadedListener) && Objects.equal(listenerImageRequest.zzpk, this.zzpk));
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { this.zzpk });
    }
    
    protected final void loadImage(Drawable param1Drawable, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3) {
      if (!param1Boolean2) {
        ImageManager.OnImageLoadedListener onImageLoadedListener = this.zzpp.get();
        if (onImageLoadedListener != null)
          onImageLoadedListener.onImageLoaded(this.zzpk.uri, param1Drawable, param1Boolean3); 
      } 
    }
  }
  
  public static final class PostProcessingFlags {
    public static final int CIRCLE_CROP = 1;
  }
  
  public static final class TextViewImageRequest extends ImageRequest {
    public static final int POSITION_BOTTOM = 3;
    
    public static final int POSITION_END = 2;
    
    public static final int POSITION_START = 0;
    
    public static final int POSITION_TOP = 1;
    
    private WeakReference<TextView> zzpq;
    
    private int zzpr = -1;
    
    public TextViewImageRequest(TextView param1TextView, int param1Int1, int param1Int2) {
      super(null, param1Int2);
      Asserts.checkNotNull(param1TextView);
      boolean bool = true;
      if (param1Int1 == 0 || param1Int1 == 1 || param1Int1 == 2 || param1Int1 == 3)
        bool = false; 
      StringBuilder stringBuilder = new StringBuilder(29);
      stringBuilder.append("Invalid position: ");
      stringBuilder.append(param1Int1);
      Asserts.checkState(bool, stringBuilder.toString());
      this.zzpq = new WeakReference<>(param1TextView);
      this.zzpr = param1Int1;
    }
    
    public TextViewImageRequest(TextView param1TextView, int param1Int, Uri param1Uri) {
      super(param1Uri, 0);
      Asserts.checkNotNull(param1TextView);
      boolean bool = true;
      if (param1Int == 0 || param1Int == 1 || param1Int == 2 || param1Int == 3)
        bool = false; 
      StringBuilder stringBuilder = new StringBuilder(29);
      stringBuilder.append("Invalid position: ");
      stringBuilder.append(param1Int);
      Asserts.checkState(bool, stringBuilder.toString());
      this.zzpq = new WeakReference<>(param1TextView);
      this.zzpr = param1Int;
    }
    
    public final boolean equals(Object param1Object) {
      if (!(param1Object instanceof TextViewImageRequest))
        return false; 
      if (this == param1Object)
        return true; 
      TextViewImageRequest textViewImageRequest = (TextViewImageRequest)param1Object;
      param1Object = this.zzpq.get();
      TextView textView = textViewImageRequest.zzpq.get();
      return (textView != null && param1Object != null && Objects.equal(textView, param1Object) && Objects.equal(Integer.valueOf(textViewImageRequest.zzpr), Integer.valueOf(this.zzpr)));
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { Integer.valueOf(this.zzpr) });
    }
    
    protected final void loadImage(Drawable param1Drawable, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3) {
      TextView textView = this.zzpq.get();
      if (textView != null) {
        Drawable[] arrayOfDrawable;
        CrossFadingDrawable crossFadingDrawable1;
        CrossFadingDrawable crossFadingDrawable2;
        Drawable drawable3;
        int i = this.zzpr;
        param1Boolean1 = shouldCrossFade(param1Boolean1, param1Boolean2);
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
          arrayOfDrawable = textView.getCompoundDrawablesRelative();
        } else {
          arrayOfDrawable = textView.getCompoundDrawables();
        } 
        Drawable drawable1 = arrayOfDrawable[i];
        Drawable drawable2 = param1Drawable;
        if (param1Boolean1)
          crossFadingDrawable2 = createTransitionDrawable(drawable1, param1Drawable); 
        if (i == 0) {
          CrossFadingDrawable crossFadingDrawable = crossFadingDrawable2;
        } else {
          param1Drawable = arrayOfDrawable[0];
        } 
        if (i == 1) {
          CrossFadingDrawable crossFadingDrawable = crossFadingDrawable2;
        } else {
          drawable1 = arrayOfDrawable[1];
        } 
        if (i == 2) {
          CrossFadingDrawable crossFadingDrawable = crossFadingDrawable2;
        } else {
          drawable3 = arrayOfDrawable[2];
        } 
        if (i == 3) {
          crossFadingDrawable1 = crossFadingDrawable2;
        } else {
          crossFadingDrawable1 = crossFadingDrawable1[3];
        } 
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
          textView.setCompoundDrawablesRelativeWithIntrinsicBounds(param1Drawable, drawable1, drawable3, (Drawable)crossFadingDrawable1);
        } else {
          textView.setCompoundDrawablesWithIntrinsicBounds(param1Drawable, drawable1, drawable3, (Drawable)crossFadingDrawable1);
        } 
        if (param1Boolean1)
          crossFadingDrawable2.startTransition(250); 
      } 
    }
  }
  
  static final class zza {
    public final Uri uri;
    
    public zza(Uri param1Uri) {
      this.uri = param1Uri;
    }
    
    public final boolean equals(Object param1Object) {
      return !(param1Object instanceof zza) ? false : ((this == param1Object) ? true : Objects.equal(((zza)param1Object).uri, this.uri));
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { this.uri });
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\ImageRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */