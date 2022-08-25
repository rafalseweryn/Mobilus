package com.google.android.gms.common.images;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.internal.Asserts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
  public static final int PRIORITY_HIGH = 3;
  
  public static final int PRIORITY_LOW = 1;
  
  public static final int PRIORITY_MEDIUM = 2;
  
  private static final Object zzov = new Object();
  
  private static HashSet<Uri> zzow = new HashSet<>();
  
  private static ImageManager zzox;
  
  private static ImageManager zzoy;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private final ExecutorService zzoz;
  
  private final zza zzpa;
  
  private final PostProcessedResourceCache zzpb;
  
  private final Map<ImageRequest, ImageReceiver> zzpc;
  
  private final Map<Uri, ImageReceiver> zzpd;
  
  private final Map<Uri, Long> zzpe;
  
  private ImageManager(Context paramContext, boolean paramBoolean) {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.zzoz = Executors.newFixedThreadPool(4);
    if (paramBoolean) {
      this.zzpa = new zza(this.mContext);
      this.mContext.registerComponentCallbacks((ComponentCallbacks)new zzd(this.zzpa));
    } else {
      this.zzpa = null;
    } 
    this.zzpb = new PostProcessedResourceCache();
    this.zzpc = new HashMap<>();
    this.zzpd = new HashMap<>();
    this.zzpe = new HashMap<>();
  }
  
  public static ImageManager create(Context paramContext) {
    return create(paramContext, false);
  }
  
  public static ImageManager create(Context paramContext, boolean paramBoolean) {
    if (paramBoolean) {
      if (zzoy == null)
        zzoy = new ImageManager(paramContext, true); 
      return zzoy;
    } 
    if (zzox == null)
      zzox = new ImageManager(paramContext, false); 
    return zzox;
  }
  
  private final Bitmap zza(ImageRequest.zza paramzza) {
    return (this.zzpa == null) ? null : (Bitmap)this.zzpa.get(paramzza);
  }
  
  public final void loadImage(ImageView paramImageView, int paramInt) {
    loadImage(new ImageRequest.ImageViewImageRequest(paramImageView, paramInt));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri) {
    loadImage(new ImageRequest.ImageViewImageRequest(paramImageView, paramUri));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri, int paramInt) {
    ImageRequest.ImageViewImageRequest imageViewImageRequest = new ImageRequest.ImageViewImageRequest(paramImageView, paramUri);
    imageViewImageRequest.setNoDataPlaceholder(paramInt);
    loadImage(imageViewImageRequest);
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri) {
    loadImage(new ImageRequest.ListenerImageRequest(paramOnImageLoadedListener, paramUri));
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt) {
    ImageRequest.ListenerImageRequest listenerImageRequest = new ImageRequest.ListenerImageRequest(paramOnImageLoadedListener, paramUri);
    listenerImageRequest.setNoDataPlaceholder(paramInt);
    loadImage(listenerImageRequest);
  }
  
  public final void loadImage(ImageRequest paramImageRequest) {
    Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
    (new zzc(this, paramImageRequest)).run();
  }
  
  @KeepName
  private final class ImageReceiver extends ResultReceiver {
    private final Uri mUri;
    
    private final ArrayList<ImageRequest> zzpf;
    
    ImageReceiver(ImageManager this$0, Uri param1Uri) {
      super(new Handler(Looper.getMainLooper()));
      this.mUri = param1Uri;
      this.zzpf = new ArrayList<>();
    }
    
    public final void onReceiveResult(int param1Int, Bundle param1Bundle) {
      ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)param1Bundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.zzf(this.zzpg).execute(new ImageManager.zzb(this.zzpg, this.mUri, parcelFileDescriptor));
    }
    
    public final void zza(ImageRequest param1ImageRequest) {
      Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
      this.zzpf.add(param1ImageRequest);
    }
    
    public final void zzb(ImageRequest param1ImageRequest) {
      Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
      this.zzpf.remove(param1ImageRequest);
    }
    
    public final void zzco() {
      Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
      intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
      intent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.zzb(this.zzpg).sendBroadcast(intent);
    }
  }
  
  public static interface OnImageLoadedListener {
    void onImageLoaded(Uri param1Uri, Drawable param1Drawable, boolean param1Boolean);
  }
  
  private static final class zza extends LruCache<ImageRequest.zza, Bitmap> {
    public zza(Context param1Context) {
      super((int)((i * 1048576) * 0.33F));
    }
  }
  
  private final class zzb implements Runnable {
    private final Uri mUri;
    
    private final ParcelFileDescriptor zzph;
    
    public zzb(ImageManager this$0, Uri param1Uri, ParcelFileDescriptor param1ParcelFileDescriptor) {
      this.mUri = param1Uri;
      this.zzph = param1ParcelFileDescriptor;
    }
    
    public final void run() {
      String str1;
      Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
      ParcelFileDescriptor parcelFileDescriptor = this.zzph;
      boolean bool1 = false;
      boolean bool2 = false;
      Bitmap bitmap = null;
      String str2 = null;
      if (parcelFileDescriptor != null) {
        try {
          bitmap = BitmapFactory.decodeFileDescriptor(this.zzph.getFileDescriptor());
          bool1 = bool2;
        } catch (OutOfMemoryError outOfMemoryError) {
          str1 = String.valueOf(this.mUri);
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 34);
          stringBuilder.append("OOM while loading bitmap for uri: ");
          stringBuilder.append(str1);
          Log.e("ImageManager", stringBuilder.toString(), outOfMemoryError);
          bool1 = true;
          str1 = str2;
        } 
        try {
          this.zzph.close();
        } catch (IOException iOException) {
          Log.e("ImageManager", "closed failed", iOException);
        } 
      } 
      CountDownLatch countDownLatch = new CountDownLatch(1);
      ImageManager.zzg(this.zzpg).post(new ImageManager.zze(this.zzpg, this.mUri, (Bitmap)str1, bool1, countDownLatch));
      try {
        countDownLatch.await();
        return;
      } catch (InterruptedException interruptedException) {
        String str = String.valueOf(this.mUri);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 32);
        stringBuilder.append("Latch interrupted while posting ");
        stringBuilder.append(str);
        Log.w("ImageManager", stringBuilder.toString());
        return;
      } 
    }
  }
  
  private final class zzc implements Runnable {
    private final ImageRequest zzpi;
    
    public zzc(ImageManager this$0, ImageRequest param1ImageRequest) {
      this.zzpi = param1ImageRequest;
    }
    
    public final void run() {
      Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
      ImageManager.ImageReceiver imageReceiver1 = (ImageManager.ImageReceiver)ImageManager.zza(this.zzpg).get(this.zzpi);
      if (imageReceiver1 != null) {
        ImageManager.zza(this.zzpg).remove(this.zzpi);
        imageReceiver1.zzb(this.zzpi);
      } 
      ImageRequest.zza zza = this.zzpi.zzpk;
      if (zza.uri == null) {
        this.zzpi.zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg), true);
        return;
      } 
      Bitmap bitmap = ImageManager.zza(this.zzpg, zza);
      if (bitmap != null) {
        this.zzpi.zza(ImageManager.zzb(this.zzpg), bitmap, true);
        return;
      } 
      Long long_ = (Long)ImageManager.zzd(this.zzpg).get(zza.uri);
      if (long_ != null) {
        if (SystemClock.elapsedRealtime() - long_.longValue() < 3600000L) {
          this.zzpi.zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg), true);
          return;
        } 
        ImageManager.zzd(this.zzpg).remove(zza.uri);
      } 
      this.zzpi.zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg));
      ImageManager.ImageReceiver imageReceiver2 = (ImageManager.ImageReceiver)ImageManager.zze(this.zzpg).get(zza.uri);
      null = imageReceiver2;
      if (imageReceiver2 == null) {
        null = new ImageManager.ImageReceiver(this.zzpg, zza.uri);
        ImageManager.zze(this.zzpg).put(zza.uri, null);
      } 
      null.zza(this.zzpi);
      if (!(this.zzpi instanceof ImageRequest.ListenerImageRequest))
        ImageManager.zza(this.zzpg).put(this.zzpi, null); 
      synchronized (ImageManager.zzcm()) {
        if (!ImageManager.zzcn().contains(zza.uri)) {
          ImageManager.zzcn().add(zza.uri);
          null.zzco();
        } 
        return;
      } 
    }
  }
  
  private static final class zzd implements ComponentCallbacks2 {
    private final ImageManager.zza zzpa;
    
    public zzd(ImageManager.zza param1zza) {
      this.zzpa = param1zza;
    }
    
    public final void onConfigurationChanged(Configuration param1Configuration) {}
    
    public final void onLowMemory() {
      this.zzpa.evictAll();
    }
    
    public final void onTrimMemory(int param1Int) {
      if (param1Int >= 60) {
        this.zzpa.evictAll();
        return;
      } 
      if (param1Int >= 20)
        this.zzpa.trimToSize(this.zzpa.size() / 2); 
    }
  }
  
  private final class zze implements Runnable {
    private final Bitmap mBitmap;
    
    private final Uri mUri;
    
    private final CountDownLatch zzfd;
    
    private boolean zzpj;
    
    public zze(ImageManager this$0, Uri param1Uri, Bitmap param1Bitmap, boolean param1Boolean, CountDownLatch param1CountDownLatch) {
      this.mUri = param1Uri;
      this.mBitmap = param1Bitmap;
      this.zzpj = param1Boolean;
      this.zzfd = param1CountDownLatch;
    }
    
    public final void run() {
      boolean bool;
      Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
      if (this.mBitmap != null) {
        bool = true;
      } else {
        bool = false;
      } 
      if (ImageManager.zzh(this.zzpg) != null) {
        if (this.zzpj) {
          ImageManager.zzh(this.zzpg).evictAll();
          System.gc();
          this.zzpj = false;
          ImageManager.zzg(this.zzpg).post(this);
          return;
        } 
        if (bool)
          ImageManager.zzh(this.zzpg).put(new ImageRequest.zza(this.mUri), this.mBitmap); 
      } 
      null = (ImageManager.ImageReceiver)ImageManager.zze(this.zzpg).remove(this.mUri);
      if (null != null) {
        ArrayList<ImageRequest> arrayList = ImageManager.ImageReceiver.zza(null);
        int i = arrayList.size();
        for (byte b = 0; b < i; b++) {
          ImageRequest imageRequest = arrayList.get(b);
          if (bool) {
            imageRequest.zza(ImageManager.zzb(this.zzpg), this.mBitmap, false);
          } else {
            ImageManager.zzd(this.zzpg).put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
            imageRequest.zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg), false);
          } 
          if (!(imageRequest instanceof ImageRequest.ListenerImageRequest))
            ImageManager.zza(this.zzpg).remove(imageRequest); 
        } 
      } 
      this.zzfd.countDown();
      synchronized (ImageManager.zzcm()) {
        ImageManager.zzcn().remove(this.mUri);
        return;
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\ImageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */