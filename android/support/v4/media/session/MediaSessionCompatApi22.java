package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;

@RequiresApi(22)
class MediaSessionCompatApi22 {
  public static void setRatingType(Object paramObject, int paramInt) {
    ((MediaSession)paramObject).setRatingType(paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\session\MediaSessionCompatApi22.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */