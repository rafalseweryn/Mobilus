package android.support.v4.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ObjectsCompat;

@RequiresApi(28)
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
  MediaSessionManager mObject;
  
  MediaSessionManagerImplApi28(Context paramContext) {
    super(paramContext);
    this.mObject = (MediaSessionManager)paramContext.getSystemService("media_session");
  }
  
  public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl) {
    return (paramRemoteUserInfoImpl instanceof RemoteUserInfoImplApi28) ? this.mObject.isTrustedForMediaControl(((RemoteUserInfoImplApi28)paramRemoteUserInfoImpl).mObject) : false;
  }
  
  static final class RemoteUserInfoImplApi28 implements MediaSessionManager.RemoteUserInfoImpl {
    final MediaSessionManager.RemoteUserInfo mObject;
    
    RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo param1RemoteUserInfo) {
      this.mObject = param1RemoteUserInfo;
    }
    
    RemoteUserInfoImplApi28(String param1String, int param1Int1, int param1Int2) {
      this.mObject = new MediaSessionManager.RemoteUserInfo(param1String, param1Int1, param1Int2);
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof RemoteUserInfoImplApi28))
        return false; 
      param1Object = param1Object;
      return this.mObject.equals(((RemoteUserInfoImplApi28)param1Object).mObject);
    }
    
    public String getPackageName() {
      return this.mObject.getPackageName();
    }
    
    public int getPid() {
      return this.mObject.getPid();
    }
    
    public int getUid() {
      return this.mObject.getUid();
    }
    
    public int hashCode() {
      return ObjectsCompat.hash(new Object[] { this.mObject });
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\MediaSessionManagerImplApi28.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */