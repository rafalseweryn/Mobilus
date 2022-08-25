package com.google.android.gms.common.images.internal;

import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import com.google.android.gms.common.internal.Objects;

public final class PostProcessedResourceCache extends LruCache<PostProcessedResourceCache.PostProcessedResource, Drawable> {
  public PostProcessedResourceCache() {
    super(10);
  }
  
  public static final class PostProcessedResource {
    public final int postProcessingFlags;
    
    public final int resId;
    
    public PostProcessedResource(int param1Int1, int param1Int2) {
      this.resId = param1Int1;
      this.postProcessingFlags = param1Int2;
    }
    
    public final boolean equals(Object param1Object) {
      if (!(param1Object instanceof PostProcessedResource))
        return false; 
      if (this == param1Object)
        return true; 
      param1Object = param1Object;
      return (((PostProcessedResource)param1Object).resId == this.resId && ((PostProcessedResource)param1Object).postProcessingFlags == this.postProcessingFlags);
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { Integer.valueOf(this.resId), Integer.valueOf(this.postProcessingFlags) });
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\internal\PostProcessedResourceCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */