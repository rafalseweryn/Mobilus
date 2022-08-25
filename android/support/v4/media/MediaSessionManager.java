package android.support.v4.media;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;

public final class MediaSessionManager {
  static final boolean DEBUG = Log.isLoggable("MediaSessionManager", 3);
  
  static final String TAG = "MediaSessionManager";
  
  private static final Object sLock = new Object();
  
  private static volatile MediaSessionManager sSessionManager;
  
  MediaSessionManagerImpl mImpl;
  
  private MediaSessionManager(Context paramContext) {
    if (Build.VERSION.SDK_INT >= 28) {
      this.mImpl = new MediaSessionManagerImplApi28(paramContext);
    } else if (Build.VERSION.SDK_INT >= 21) {
      this.mImpl = new MediaSessionManagerImplApi21(paramContext);
    } else {
      this.mImpl = new MediaSessionManagerImplBase(paramContext);
    } 
  }
  
  @NonNull
  public static MediaSessionManager getSessionManager(@NonNull Context paramContext) {
    MediaSessionManager mediaSessionManager1 = sSessionManager;
    MediaSessionManager mediaSessionManager2 = mediaSessionManager1;
    if (mediaSessionManager1 == null)
      synchronized (sLock) {
        mediaSessionManager1 = sSessionManager;
        mediaSessionManager2 = mediaSessionManager1;
        if (mediaSessionManager1 == null) {
          mediaSessionManager2 = new MediaSessionManager();
          this(paramContext.getApplicationContext());
          sSessionManager = mediaSessionManager2;
          mediaSessionManager2 = sSessionManager;
        } 
      }  
    return mediaSessionManager2;
  }
  
  Context getContext() {
    return this.mImpl.getContext();
  }
  
  public boolean isTrustedForMediaControl(@NonNull RemoteUserInfo paramRemoteUserInfo) {
    if (paramRemoteUserInfo == null)
      throw new IllegalArgumentException("userInfo should not be null"); 
    return this.mImpl.isTrustedForMediaControl(paramRemoteUserInfo.mImpl);
  }
  
  static interface MediaSessionManagerImpl {
    Context getContext();
    
    boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl param1RemoteUserInfoImpl);
  }
  
  public static final class RemoteUserInfo {
    public static final String LEGACY_CONTROLLER = "android.media.session.MediaController";
    
    MediaSessionManager.RemoteUserInfoImpl mImpl;
    
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteUserInfo(android.media.session.MediaSessionManager.RemoteUserInfo param1RemoteUserInfo) {
      this.mImpl = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(param1RemoteUserInfo);
    }
    
    public RemoteUserInfo(@NonNull String param1String, int param1Int1, int param1Int2) {
      if (Build.VERSION.SDK_INT >= 28) {
        this.mImpl = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(param1String, param1Int1, param1Int2);
      } else {
        this.mImpl = new MediaSessionManagerImplBase.RemoteUserInfoImplBase(param1String, param1Int1, param1Int2);
      } 
    }
    
    public boolean equals(@Nullable Object param1Object) {
      return (this == param1Object) ? true : (!(param1Object instanceof RemoteUserInfo) ? false : this.mImpl.equals(((RemoteUserInfo)param1Object).mImpl));
    }
    
    @NonNull
    public String getPackageName() {
      return this.mImpl.getPackageName();
    }
    
    public int getPid() {
      return this.mImpl.getPid();
    }
    
    public int getUid() {
      return this.mImpl.getUid();
    }
    
    public int hashCode() {
      return this.mImpl.hashCode();
    }
  }
  
  static interface RemoteUserInfoImpl {
    String getPackageName();
    
    int getPid();
    
    int getUid();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\MediaSessionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */