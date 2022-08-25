package android.support.v4.media;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class MediaSessionManagerImplApi21 extends MediaSessionManagerImplBase {
  MediaSessionManagerImplApi21(Context paramContext) {
    super(paramContext);
    this.mContext = paramContext;
  }
  
  private boolean hasMediaControlPermission(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl) {
    boolean bool;
    if (getContext().checkPermission("android.permission.MEDIA_CONTENT_CONTROL", paramRemoteUserInfoImpl.getPid(), paramRemoteUserInfoImpl.getUid()) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isTrustedForMediaControl(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl) {
    return (hasMediaControlPermission(paramRemoteUserInfoImpl) || super.isTrustedForMediaControl(paramRemoteUserInfoImpl));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\MediaSessionManagerImplApi21.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */