package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.List;

@RequiresApi(26)
class MediaBrowserCompatApi26 {
  static Object createSubscriptionCallback(SubscriptionCallback paramSubscriptionCallback) {
    return new SubscriptionCallbackProxy<>(paramSubscriptionCallback);
  }
  
  public static void subscribe(Object paramObject1, String paramString, Bundle paramBundle, Object paramObject2) {
    ((MediaBrowser)paramObject1).subscribe(paramString, paramBundle, (MediaBrowser.SubscriptionCallback)paramObject2);
  }
  
  public static void unsubscribe(Object paramObject1, String paramString, Object paramObject2) {
    ((MediaBrowser)paramObject1).unsubscribe(paramString, (MediaBrowser.SubscriptionCallback)paramObject2);
  }
  
  static interface SubscriptionCallback extends MediaBrowserCompatApi21.SubscriptionCallback {
    void onChildrenLoaded(@NonNull String param1String, List<?> param1List, @NonNull Bundle param1Bundle);
    
    void onError(@NonNull String param1String, @NonNull Bundle param1Bundle);
  }
  
  static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends MediaBrowserCompatApi21.SubscriptionCallbackProxy<T> {
    SubscriptionCallbackProxy(T param1T) {
      super(param1T);
    }
    
    public void onChildrenLoaded(@NonNull String param1String, List<MediaBrowser.MediaItem> param1List, @NonNull Bundle param1Bundle) {
      MediaSessionCompat.ensureClassLoader(param1Bundle);
      ((MediaBrowserCompatApi26.SubscriptionCallback)this.mSubscriptionCallback).onChildrenLoaded(param1String, param1List, param1Bundle);
    }
    
    public void onError(@NonNull String param1String, @NonNull Bundle param1Bundle) {
      MediaSessionCompat.ensureClassLoader(param1Bundle);
      ((MediaBrowserCompatApi26.SubscriptionCallback)this.mSubscriptionCallback).onError(param1String, param1Bundle);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\MediaBrowserCompatApi26.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */